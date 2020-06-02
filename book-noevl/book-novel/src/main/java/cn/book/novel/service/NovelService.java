package cn.book.novel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.book.comm.pojo.BookUIResult;
import cn.book.comm.pojo.Category;
import cn.book.comm.pojo.ClassifyPageVo;
import cn.book.comm.pojo.Novel;
import cn.book.novel.mapper.NovelMapper;

@Service
public class NovelService {
	@Autowired
	private NovelMapper novelMapper;
	
	public BookUIResult pageQuery(Integer page, Integer rows) {
		int start=(page-1)*rows;
		BookUIResult result = new BookUIResult();
		List<Novel> nList = novelMapper.pageQuery(start,rows);
		Integer total=novelMapper.queryCount();
		result.setRows(nList);
		result.setTotal(total);
		return result;
	}

	public Novel queryById(String novelId) {
		return novelMapper.queryById(novelId);
	}

	public List<Novel> queryByCategory(String category) {
		return novelMapper.queryByCategory(category);
	}

	/**
	 * 查询分类小说信息
	 * @return
	 */
	public List<ClassifyPageVo> queryNovelAllClassify() {
		/*List<ClassifyPageVo> pages = new ArrayList<ClassifyPageVo>();
		List<Category> categroys = novelMapper.queryCategory();
		for (Category category : categroys) {
			ClassifyPageVo vo = new ClassifyPageVo();
			vo.setCategoryId(category.getCategoryId());
			vo.setCategoryname(category.getCategoryname());
			vo.setNovelList(novelMapper.queryByCategory(category.getCategoryId()));
			pages.add(vo);
		}*/
		return novelMapper.queryNovelAllClassify();
	}

	public List<HashMap<String, Integer>> queryByClass() {
		return novelMapper.queryByClass();
	}

}
