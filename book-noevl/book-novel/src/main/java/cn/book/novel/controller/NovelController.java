package cn.book.novel.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.book.comm.pojo.BookUIResult;
import cn.book.comm.pojo.Novel;
import cn.book.novel.service.NovelService;

@RestController
@RequestMapping("/novel")
public class NovelController {
	@Autowired
	private NovelService novelService;
	
	//�걾�Ĳ�ѯ����
	@RequestMapping("/wanben")
	public BookUIResult pageQuery(Integer page,Integer rows){
		BookUIResult result=novelService.pageQuery(page,rows);
		return result;
	}
	//С˵������ѯ
	@RequestMapping("/item/{novelId}")
	public Novel queryById(@PathVariable String novelId){
		return novelService.queryById(novelId);
	}
	//С˵�������Ͳ�ѯ
	@RequestMapping("/list/{category}")
	public List<Novel> queryByCatagory(@PathVariable @Param("category") String category){
		return novelService.queryByCategory(category);
	}
}
