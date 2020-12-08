package com.definesys.rt;

import com.definesys.rt.Test.*;
import com.definesys.rt.util.StringUtil;
import com.definesys.rt.util.UUIDUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.plugin.util.UIUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@SpringBootTest
class RtApplicationTests {
    private String path = "E:\\definesys\\rt\\";
    @Autowired
    private DemoServiceImpl demoService;
    @Autowired
    private DemoDao dao;


//    /**
//     * 创建索引库
//     * @throws IOException
//     */
//    @Test
//    void ESTest() throws IOException {
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest("t_doc");
//        createIndexRequest.setTimeout(new TimeValue(10, TimeUnit.SECONDS));
//
//        CreateIndexResponse createIndexResponse =
//                restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//        System.out.println(createIndexResponse);
//    }
//
//    /**
//     * 给字段设置类型
//     * @throws IOException
//     */
//    @Test
//    public void ESTest5() throws IOException {
//
//        XContentBuilder mapping = XContentFactory.jsonBuilder()
//                .startObject()
//                .startObject("properties")
//                .startObject("uploaddate")
//                .field("type", "date")
//                .field("format", "yyyy-MM-dd HH:mm:ss")
//                .endObject()
//                .startObject("lastUpdateDate")
//                .field("type", "date")
//                .field("format", "yyyy-MM-dd HH:mm:ss")
//                .endObject()
//                .startObject("creationDate")
//                .field("type", "date")
//                .field("format", "yyyy-MM-dd HH:mm:ss")
//                .endObject()
//                .endObject()
//                .endObject();
//        PutMappingRequest putMapping = Requests.putMappingRequest("t_doc").type("_doc").source(mapping);
//        restHighLevelClient.indices().putMapping(putMapping,RequestOptions.DEFAULT);
//    }
//
//    //检索数据
//    @Test
//    void ESTest1() throws IOException {
//        Boolean aBoolean = esDoc.parseContent();
//        System.out.println(aBoolean);
//    }
//
//    //删除索引数据
//    @Test
//    void ESTest3() throws IOException {
//        DeleteByQueryRequest request = new DeleteByQueryRequest("t_doc");
//
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
//        request.setQuery(matchAllQueryBuilder);
//
//        BulkByScrollResponse bulk = restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
//        System.out.println(bulk.getBatches());
//
//    }
//
//    //测试删除索引
//    @Test
//    void testdeleteindex() throws IOException {
//        DeleteIndexRequest request = new DeleteIndexRequest("t_doc");
//        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
//        System.out.println(delete.isAcknowledged());
//    }
//
//
//    //导入数据
//    @Test
//    void ESTest2() throws IOException {
//        List<Map<String, Object>> list = esDoc.searchPage("lanlan", 0, 5);
//        System.out.println(list.size());
//        System.out.println("我去");
//        for (Map<String, Object> map : list) {
//            System.out.println(map.size());
//            Set<Map.Entry<String, Object>> set = map.entrySet();
//            Iterator<Map.Entry<String, Object>> iterator = set.iterator();
//            while (iterator.hasNext()){
//                Map.Entry<String, Object> next = iterator.next();
//                String key = next.getKey();
//                Object value = next.getValue();
//                System.out.println(key + " : " + value);
//            }
//        }
//    }
//    @Test
//    public void test1(){
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.get(Calendar.MONTH));
//    }
//
//
//    @Test
//    public void searchPage() throws IOException {
////        pageNo += 1;
//
//        SearchRequest searchRequest = new SearchRequest("t_doc");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        //分页
//        searchSourceBuilder.from(10);
//        searchSourceBuilder.size(5);
//        searchSourceBuilder.sort("uploaddate",SortOrder.DESC);
//
//        //做精确查询
////        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("documentname",keyword);
////        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("documentname", keyword);
////        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("documentname", keyword);
////        searchSourceBuilder.query(matchQueryBuilder);
//
//        //多条件ik分词器查询matchQuery
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("documentname","super");
//        MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("username", "super");
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(matchQueryBuilder).should(matchQueryBuilder1);
//
////        //按照上传时间进行排序
////        FieldSortBuilder sort = SortBuilders.fieldSort("uploaddate").order(SortOrder.DESC);
////
////        PageRequest page = new PageRequest(pageNo,pageSize);
////
////        //构建查询
////        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
////        //将搜索条件放入构建中
////        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
////        //将分页设置放入构建中
////        nativeSearchQueryBuilder.withPageable(page);
////        //将排序设置到构建中
////        nativeSearchQueryBuilder.withSort(sort);
////        //生成NativeSearchQueryBuilder
////        NativeSearchQuery query = nativeSearchQueryBuilder.build();
//
//        searchSourceBuilder.query(boolQueryBuilder);
//        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//
//        //进行检索
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        System.out.println(search.getHits().getTotalHits().value);
//        //解析结果
//        List<Map<String,Object>> list = new ArrayList<>();
//        for (SearchHit fields : search.getHits().getHits()) {
//            list.add(fields.getSourceAsMap());
//        }
//
//        list.forEach(System.out::println);
//
////        for (int i = 0; i < list.size(); i++) {
////            Set<Map.Entry<String, Object>> entrySet = list.get(i).entrySet();
////            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
////            while (iterator.hasNext()) {
////                Map.Entry<String, Object> next = iterator.next();
////                String key = next.getKey();
////                String name = next.getValue().getClass().getTypeName();
////
//////                System.out.println(key + ":" + name);
////                if (next.getValue().getClass().getTypeName() == "java.lang.Long") {
////                    Long value = (Long) next.getValue();
//////                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//////                    String date = sdf.format(value);
////                    String date = JsonUtil.getJsonDate(value);
////                    next.setValue(date);
////                }
////            }
//////            list.forEach(System.out::println);
//
//
//
//
//
//    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId;
    }


    public static void main(String[] args){
        for (int i = 0; i<10; i++)
            System.out.println(UUIDUtil.getIntegerUUID(6));
    }



    @Test
    public void excleTest07() throws IOException {

        Map<String,Object> map = new HashMap<>();
        List<Demo> list = new ArrayList<>(16);
        FileInputStream fileInputStream = new FileInputStream(path + "分公司全面绩效工号.xlsx");

        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < 3340; i++) {
            Row row = sheet.getRow(i);
            Cell phone = row.getCell(0);
            Cell name = row.getCell(1);
            Cell post = row.getCell(2);
            String code = StringUtil.getWholeEmployeeCode(phone.getStringCellValue());
            map.put("I_PERNR",code);
//            String demo = demoService.demo(map);
//            ObjectMapper objectMapper = new ObjectMapper();
//            RequestDTO requestDTO = objectMapper.readValue(demo, RequestDTO.class);
//            Demo demo1 = new Demo();
//            demo1.setJobNumber(phone.getStringCellValue());
//            demo1.setPhone(requestDTO.getPhone());
//            demo1.setName(name.getStringCellValue());
//            demo1.setPost(post.getStringCellValue());
//            list.add(demo1);
        }
        dao.saveAll(list);
        fileInputStream.close();
    }


    @Test
    public void excleTest08() throws IOException {

        Map<String,Object> map = new HashMap<>();
        List<Demo> list = new ArrayList<>(16);
        FileInputStream fileInputStream = new FileInputStream(path + "10月绩效工号查不出手机号员工.xlsx");

        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < 3088; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            String code = StringUtil.getWholeEmployeeCode(cell.getStringCellValue());
            map.put("I_PERNR",code);
//            String demo = demoService.demo(map);
//            ObjectMapper objectMapper = new ObjectMapper();
//            RequestDTO requestDTO = objectMapper.readValue(demo, RequestDTO.class);
//            Demo demo1 = new Demo();
//            demo1.setJobNumber(cell.getStringCellValue());
//            demo1.setPhone(requestDTO.getPhone());
//            list.add(demo1);
        }
        dao.saveAll(list);
        fileInputStream.close();
    }

    @Autowired
    private DemoService  service;

    @Test
    void json(){
        String json = service.json(21);
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(json, User.class);
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(user.getAge());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


}
