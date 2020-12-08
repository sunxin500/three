package com.definesys.rt.service.Impl;

import com.alibaba.fastjson.JSON;
import com.definesys.rt.bean.TDocument;
import com.definesys.rt.dao.TDocumentDao;
import com.definesys.rt.service.ESDocService;
import com.definesys.rt.util.JsonUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 通过条件检索文档
 */
@Service
public class ESDocServiceImpl implements ESDocService {

    private static final Logger logger =LoggerFactory.getLogger(ESDocService.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private TDocumentDao dao;

    // 查询数据导入索引中
    @Override
    public Boolean parseContent() throws IOException {

        List<TDocument> documents = dao.findAll();

        //把查询的数据放入es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for (int i = 0;i<documents.size();i++){
            bulkRequest.add(
                    new IndexRequest("t_doc")
                            .source(JSON.toJSONString(documents.get(i)), XContentType.JSON));
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulk.hasFailures();
    }

    @Override
    public void deleteByQuery() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest("t_doc");
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        request.setQuery(matchAllQueryBuilder);
        restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
    }

    //通过条件进行检索数据
    @Override
    public List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {
//        pageNo += 1;

        SearchRequest searchRequest = new SearchRequest("t_doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        if (pageNo == 0) {
            searchSourceBuilder.from(pageNo);
        } else {
            searchSourceBuilder.from(pageNo*pageSize);
        }
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.sort("uploaddate",SortOrder.DESC);

        //做精确查询
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("documentname",keyword);
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("documentname", keyword);
//        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("documentname", keyword);
//        searchSourceBuilder.query(matchQueryBuilder);

        //多条件ik分词器查询matchQuery
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("documentname",keyword);
        MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("username", keyword);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(matchQueryBuilder).should(matchQueryBuilder1);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //进行检索
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(search.getHits().getTotalHits().value);

        //解析结果
        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit fields : search.getHits().getHits()) {
            list.add(fields.getSourceAsMap());
        }
        return list;
    }

    //通过条件进行检索数据
    @Override
    public int searchTotal(String keyword, int pageNo, int pageSize) throws IOException {
//        pageNo += 1;

        SearchRequest searchRequest = new SearchRequest("t_doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        //根据上传时间排序
//        searchSourceBuilder.sort("uploaddate",SortOrder.DESC);

        //做精确查询
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("documentname",keyword);
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("documentname", keyword);
//        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("documentname", keyword);
//        searchSourceBuilder.query(matchQueryBuilder);

        //多条件ik分词器查询matchQuery
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("documentname",keyword);
        MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("username", keyword);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(matchQueryBuilder).should(matchQueryBuilder1);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //进行检索
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        long total = search.getHits().getTotalHits().value;

        return (int) total;
    }
}
