package cn.book.novel.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.book.comm.constVar.Const;
import cn.book.comm.pojo.ClassifyPageVo;
import cn.book.comm.pojo.SysResult;
import cn.book.novel.service.NovelService;

@RestController
@RequestMapping("novel/manage")
public class NewNovelController {
	@Autowired
	private NovelService novelService;
	
	@RequestMapping("queryAll")
	public SysResult queryNovelAllByClassify(){
		try {
			List<ClassifyPageVo> pages = novelService.queryNovelAllClassify();
			SysResult result = new SysResult(Const.Success,"��ѯ�ɹ�",pages);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return SysResult.build(Const.Failed, "", null);
		}
	}
	
	/**
	 * ��ѯС˵���еķ�������
	 * @return
	 */
	@RequestMapping("queryByClass")
	public SysResult queryByClass(){
		try {
			List<HashMap<String,Integer>> classData = novelService.queryByClass();
			SysResult result = new SysResult(Const.Success,"��ѯ�ɹ�",classData);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return SysResult.build(Const.Failed, "", null);
		}
	}
}
