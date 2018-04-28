package com.app.tools;

public class Pages {

	private Integer currentPage = 1; // 起始页
	private Integer pageSize; // 每页显示条数
	private Integer pageCount; // 总条数
	private Integer totalNum; // 总页数

	/**
	 * 初始化
	 * 
	 * @param count
	 */
	public void initPage(int count) {
		this.pageCount = count;
		totalNum = pageCount % pageSize == 0 ? pageCount / pageSize
				: (pageCount / pageSize) + 1;
		//临界值处理
		if (currentPage < 1) {
			currentPage = 1;
		}

		if (currentPage > totalNum) {
			currentPage = totalNum;
		}
	}

	/*public boolean lastPage() {
		// 临界值处理
		if (currentPage < 1) {
			currentPage = 1;
			return false;
		}

		if (currentPage > totalNum) {
			currentPage = totalNum;
			return false;
		}
		return true;
	}*/

	public int first() {
		return (currentPage - 1) * pageSize;
	}

	public int showCount() {
		return pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Pages(int pageSize) {
		super();
		this.pageSize = pageSize;
	}

	public Pages() {
		super();
	}
}
