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
	
	//完本的查询功能
	@RequestMapping("/wanben")
	public BookUIResult pageQuery(Integer page,Integer rows){
		BookUIResult result=novelService.pageQuery(page,rows);
		return result;
	}
	//小说单本查询
	@RequestMapping("/item/{novelId}")
	public Novel queryById(@PathVariable String novelId){
		return novelService.queryById(novelId);
	}
	//小说按照类型查询
	@RequestMapping("/list/{category}")
	public List<Novel> queryByCatagory(@PathVariable @Param("category") String category){
		return novelService.queryByCategory(category);
	}
}
