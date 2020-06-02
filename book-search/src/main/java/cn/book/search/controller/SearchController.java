package cn.book.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.book.comm.pojo.Novel;
import cn.book.search.service.SearchService;
//http://www.ndsbook.com/index.html?searchkey=#
@RestController
@RequestMapping("/searchs")
public class SearchController {
	@Autowired
	private SearchService searchService;
		@RequestMapping("/create")
		public String createIndex(){
			searchService.createIndex("bookindex");
			return "success";
		}
		@RequestMapping("/query")
		public List<Novel> search(String query){
			int page =1;
			int rows =10;
			return searchService.search(query,page,rows);
		}
}
