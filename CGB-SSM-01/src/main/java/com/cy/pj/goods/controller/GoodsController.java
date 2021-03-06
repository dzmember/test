package com.cy.pj.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.goods.pojo.Goods;
import com.cy.pj.goods.service.GoodsService;
@Controller
@RequestMapping("controller")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@RequestMapping("findGoods")
	@ResponseBody
	public List<Goods> findGoods() {
		return goodsService.findGoods();
	}

}
