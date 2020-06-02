package cn.book.search.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.stat.descriptive.summary.Product;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.book.comm.pojo.Novel;
import cn.book.comm.util.MapperUtil;
import cn.book.search.mapper.SearchMapper;
@Service
public class SearchService {
	@Autowired
	private SearchMapper searchMapper;
	@Autowired
	private TransportClient client;
	public void createIndex(String indexName) {
		List<Novel> nList = searchMapper.queryAll();
		try {
			
			IndicesAdminClient admin = client.admin().indices();
			if (!admin.prepareExists(indexName).get().isExists()) {
				admin.prepareCreate(indexName).get();
			}
			
			for (Novel novel : nList) {
				
				String jsonData = MapperUtil.MP.writeValueAsString
						(novel);
				client.prepareIndex(indexName, "novel",novel.getNovelId()).setSource(jsonData).get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<Novel> search(String text, Integer page, Integer rows) {
		MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(text, "novelname",
				"novelanthor");
		//按照分页查询 start=(page-1)*rows
		int start=(page-1)*rows;
		SearchResponse response = client.prepareSearch("bookindex").setQuery(query).
		setFrom(start).setSize(rows).get();
		SearchHits allHits = response.getHits();//totalHits
		//maxScore
		System.out.println("总共查询:"+allHits.totalHits);
		SearchHit[] hits = allHits.getHits();
		//循环数据之前准备一个返回的数据
		List<Novel> nList=new ArrayList<Novel>();
		for (SearchHit hit : hits) {
			//获取hits中的sourcejson字符串,解析成Novel
			String jsonData=hit.getSourceAsString();
			try{
				//解析json,转化product
				Novel novel=MapperUtil.MP.readValue(jsonData, Novel.class);
				//返回结果集封装一个product
				nList.add(novel);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return nList;
	}

}
