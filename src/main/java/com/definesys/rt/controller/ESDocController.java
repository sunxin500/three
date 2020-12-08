package com.definesys.rt.controller;

import com.definesys.rt.service.ESDocService;
import com.definesys.rt.util.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "检索报告")
public class ESDocController {

    @Autowired
    private ESDocService service;

    /**
     * 跟新表数据
     * @return
     * @throws IOException
     */
    @GetMapping("/getDoc")
    public Message<Boolean> getDoc() throws IOException {
        //先删除索引数据
        //再重新添加数据
        service.deleteByQuery();
        return MessageUtil.success(service.parseContent());
    }

    /**
     * 获取数据总数
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    @GetMapping("/searchTotal")
    public Message<Integer> searchTotal(String keyword,int pageNo,int pageSize) throws IOException {
        int total = service.searchTotal(keyword, pageNo, pageSize);
        return MessageUtil.success(total);
    }

    /**
     * 条件查询数据
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    @GetMapping("/searchDoc")
    public Message<List<Map<String,Object>>> searchDoc(String keyword,int pageNo,int pageSize) throws IOException {
        List<Map<String, Object>> lists = service.searchPage(keyword, pageNo, pageSize);
        //将long类型时间戳转换成时间类型
        for (int i = 0; i < lists.size(); i++) {
            Iterator<Map.Entry<String, Object>> iterator = lists.get(i).entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                if (next.getValue().getClass().getTypeName() == "java.lang.Long") {
                    Long value = (Long) next.getValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
                    String date = sdf.format(value);
                    next.setValue(date);
                }
            }
        }
        lists.forEach(System.out::println);
        return MessageUtil.success(lists);
    }

}
