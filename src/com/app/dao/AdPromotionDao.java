package com.app.dao;

import com.app.pojo.AdPromotion;

public interface AdPromotionDao {
    AdPromotion selectByPrimaryKey(Long id);
}