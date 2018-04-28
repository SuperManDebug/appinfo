package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.app.pojo.*;
import com.app.service.AppVersionService;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.app.service.AppCategoryService;
import com.app.service.AppInfoService;
import com.app.service.DataDictionaryService;
import com.app.tools.Pages;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/AppCategory")
public class AppCategoryController {

	@Resource
	private AppCategoryService appCategoryService;

	@Resource
	private DataDictionaryService dataDictionaryService;

	@Resource
	private AppInfoService appInfoService;

	@Resource
	private AppVersionService appVersionService;

	@RequestMapping(value = "/appLevenOneList")
	public String appList(String opt,String softwarename,AppInfo appInfo,Integer pageIndex,Model model) {
		Pages pages = new Pages(5);
		pages.initPage(appInfoService.getTotalCount(appInfo));
		if(pageIndex!=null){
			pages.setCurrentPage(pageIndex);
		}

		List<AppInfo> appInfoList =  appInfoService.getAppInfoList(appInfo,pages.first(),pages.showCount());
		List<DataDictionary> flatFormList = dataDictionaryService.getDataDictionaryList("APP_FLATFORM");
		List<DataDictionary> statusList = dataDictionaryService.getDataDictionaryList("APP_STATUS");
		List<AppCategory> categoryLevel1List = appCategoryService.getAppLeveOneList();
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;
		for (AppCategory appCategory : categoryLevel1List) {
			categoryLevel2List = appCategoryService.getAppLeveTwoList(appCategory.getId());
			for (AppCategory category : categoryLevel2List) {
				categoryLevel3List = appCategoryService.getAppLeveTwoList(category.getId());
			}
		}
		if (categoryLevel1List != null && flatFormList != null) {
			model.addAttribute("appInfoList", appInfoList);
			model.addAttribute("categoryLevel1List", categoryLevel1List);
			model.addAttribute("categoryLevel2List", categoryLevel2List);
			model.addAttribute("categoryLevel3List", categoryLevel3List);
			model.addAttribute("flatFormList", flatFormList);
			model.addAttribute("statusList", statusList);
			model.addAttribute("pages", pages);
			model.addAttribute("softwarename", softwarename);
		}

		if (opt.equals("dev")){
			return "developer/appinfolist";
		}
		return "backend/applist";

	}

	@RequestMapping("/categorylevellist")
	@ResponseBody
	public List<AppCategory> appLevens(Long pid){
		List<AppCategory> categoryLevel2List  = appCategoryService.getAppLeveTwoList(pid);
		return categoryLevel2List;
	}

	@RequestMapping("/datadictionarylist")
	@ResponseBody
	public List<DataDictionary>  datadictionarylist(String tcode){
		List<DataDictionary> flatFormList = dataDictionaryService.getDataDictionaryList(tcode);
		return flatFormList;
	}

	@RequestMapping("/appCheck")
	public String appCheck(Long aid,Long vid,Model model){
		AppInfo appInfo = appInfoService.getAppInfo(aid);
		AppVersion appVersion = appVersionService.getAppVersionById(vid);
		System.out.println(appInfo.getLogopicpath());
		model.addAttribute("appInfo",appInfo);
		model.addAttribute("appVersion",appVersion);
		return "backend/appcheck";
	}

	@RequestMapping(value="/checksave")
	public String checksave(Long id,Model model,String status){
		if (appInfoService.upAppType(id,status)>0){
			return "redirect:/AppCategory/appLevenOneList";
		}
		AppInfo appInfo = appInfoService.getAppInfo(id);
		model.addAttribute("appInfo",appInfo);
		return "backend/appcheck";
	}

	@RequestMapping("/appversionadd")
	public String appversionadd(Long id,Model model){
		model.addAttribute("appVersionList",appVersionService.getAppVersionList(id));
		model.addAttribute("appid",id);
		return "developer/appversionadd";
	}

	@RequestMapping(value = "/addversionsave",method = RequestMethod.POST)
	public String addversionsave(AppVersion appVersion, MultipartFile a_downloadlink, HttpSession session,Model model){
		//上传根路径
		String path = session.getServletContext().getRealPath("statics/uploadfiles");
		String filename = a_downloadlink.getOriginalFilename(); //获取文件名
		File file = new File(path,filename);
		DevUser devUser = (DevUser)session.getAttribute("devUser");
		appVersion.setCreatedby(devUser.getId());  //创建者
		appVersion.setApkfilename(filename);
		if(!a_downloadlink.isEmpty()) {
			appVersion.setDownloadlink("statics/uploadfiles/" + filename);
		}
		appVersion.setApklocpath(file.getAbsolutePath());   //绝对路径
		int follow = appVersionService.insAppVersion(appVersion);
			follow += appInfoService.upAppInfoVid(appVersion.getAppid());
		if(follow>1){
			try {
				a_downloadlink.transferTo(file);
				return "redirect:/AppCategory/appLevenOneList?opt=dev";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("fileUploadError","上传失败,请尝试重新上传!");
		return "developer/appversionadd";
	}

	@RequestMapping(value="/appversionmodify")
	public String appversionmodify(Long vid,Long aid,Model model){
		model.addAttribute("appVersionList",appVersionService.getAppVersionList(aid));
		model.addAttribute("appVersion",appVersionService.getAppVersionById(vid));
		return "developer/appversionmodify";
	}

	@RequestMapping(value= "/appversionmodifysave",method = RequestMethod.POST)
	public String appversionmodifysave(AppVersion appVersion){
		int follow = appVersionService.upAppVersion(appVersion);
		if(follow>0){
			return "redirect:/AppCategory/appLevenOneList?opt=dev";
		}
		return "developer/appversionmodify";
	}

	@RequestMapping(value="/apkexist")
	@ResponseBody
	public String apkexist(String APKName){
		if (!APKName.equals("")&&APKName!=null) {
			AppInfo appinfo = appInfoService.getCheckName(APKName);
			if (appinfo != null) {
				return "exist";
			}
			return "noexist";
		}
		return "empty";
	}

	@RequestMapping(value="/appinfoaddsave",method = RequestMethod.POST)
	public String appinfoaddsave(AppInfo appInfo,MultipartFile a_logopicpath,HttpSession session,Model model){
		//上传根路径
		String path = session.getServletContext().getRealPath("statics/uploadfiles");
		String filename = a_logopicpath.getOriginalFilename(); //获取文件名
		File file = new File(path,filename);
		DevUser devUser = (DevUser)session.getAttribute("devUser");
		appInfo.setCreatedby(devUser.getId());  //创建者
		appInfo.setDevid(devUser.getId());  //devId
		appInfo.setLogolocpath(file.getAbsolutePath());
		if (!a_logopicpath.isEmpty()) {
			appInfo.setLogopicpath("statics/uploadfiles/"+filename);
		}
		Integer follow = appInfoService.addAppInfo(appInfo);
		if (follow>0){
			try {
				if (!file.exists()){
					a_logopicpath.transferTo(file);
				}
				return "redirect:/AppCategory/appLevenOneList?opt=dev";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("fileUploadError","上传失败!");
		return "developer/appinfoadd";
	}

	@RequestMapping(value ="{appId}/{saleSwitch}/sale")
	@ResponseBody
	public String sale(@PathVariable Long appId,@PathVariable String saleSwitch){
		if(saleSwitch.equals("open")) {
			int follow = appInfoService.upAppType(appId, "4");
			if(follow>0)
				return "success";
		}else if(saleSwitch.equals("close")){
			int follow = appInfoService.upAppType(appId, "5");
			if(follow>0)
				return "success";

		}
		return "failed";
	}

	@RequestMapping("/delapp")
	@ResponseBody
	public String delapp(Long id){
		AppInfo appInfo = appInfoService.getAppInfo(id);
		if (!id.equals("")) {
			int follow = appInfoService.delAppInfo(id);
			if(appInfo.getVersionid()!=null) {
				appVersionService.delAppVersion(id);
			}
			System.out.println(follow);
			if (follow > 0) {
				return "true";
			}
			return "false";
		}
		return "notexist";
	}

	@RequestMapping("/appinfomodify")
	public String appinfomodify(Long id,Model model){
		AppInfo appInfo = appInfoService.getAppInfo(id);
		if(appInfo!=null) {
			model.addAttribute("appInfo", appInfo);
		}
		return "developer/appinfomodify";
	}

	@RequestMapping("/delfile")
	@ResponseBody
	public String delfile(Long id,String flag){
		if(flag.equals("logo")){   //删除logo
			AppInfo appInfo = appInfoService.getAppInfo(id);
			File file = new File(appInfo.getLogolocpath());
			if(file.exists()){   //判断是否存在
				file.delete(); //删除
				if(appInfoService.upAppInfoLogo(id)>0) {
					return "success";
				}
			}
		}
		if(flag.equals("apk")){  //删除apk
			AppVersion appVersion = appVersionService.getAppVersionById(id);
			File file = new File(appVersion.getApklocpath());
			if(file.exists()){
				System.err.println(1);
				file.delete();
				if(appVersionService.upAppVersionloc(id)>0) {
					return "success";
				}
			}
		}
		return "failed";
	}

	@RequestMapping(value="/appinfomodifysave",method = RequestMethod.POST)
	public String appinfomodifysave(Integer status,AppInfo appInfo, MultipartFile attach, HttpSession session, HttpServletRequest request){
		//上传根路径
		String path = session.getServletContext().getRealPath(request.getContextPath() + "/statics/uploadfiles");
		String filename = attach.getOriginalFilename(); //获取文件名
		File file = new File(path, filename);
		DevUser devUser = (DevUser) session.getAttribute("devUser");
		appInfo.setModifyby(devUser.getId());
		appInfo.setLogolocpath(file.getAbsolutePath());
		if (!attach.isEmpty()) {
			appInfo.setLogopicpath("statics/uploadfiles/" + filename);
		}
		int follow = appInfoService.upAppInfo(appInfo);
		if(status!=null) {
			follow = appInfoService.upAppType(appInfo.getId(),"1");
		}
		if (follow > 0) {
			try {
				attach.transferTo(file);
				return "redirect:/AppCategory/appLevenOneList?opt=dev";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/AppCategory/appinfomodify?id="+appInfo.getId();
	}

	@RequestMapping("/appview")
	public String appview(Long id,Model model){
		AppInfo appInfo = appInfoService.getAppInfo(id);
		List<AppVersion> appVersionList = appVersionService.getAppVersionList(id);
		model.addAttribute("appInfo",appInfo);
		model.addAttribute("appVersionList",appVersionList);
		return "developer/appinfoview";
	}

}
