package cn.book.novel.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.book.comm.pojo.Category;
import cn.book.comm.pojo.ClassifyPageVo;
import cn.book.comm.pojo.Novel;

public interface NovelMapper {

	List<Novel> pageQuery(@Param("start")int start, @Param("rows")Integer rows);

	Integer queryCount();

	Novel queryById(String novelId);

	List<Novel> queryByCategory(String category);

	/*List<Novel> queryNovelClassify(String categoryId);*/

	List<Category> queryCategory();

	List<ClassifyPageVo> queryNovelAllClassify();

	List<HashMap<String, Integer>> queryByClass();

}
