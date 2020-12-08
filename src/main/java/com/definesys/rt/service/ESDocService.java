package com.definesys.rt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 快速检索文件
 */
public interface ESDocService {

    /**
     * 查找数据放入库中
     * @return
     */
    public Boolean parseContent() throws IOException;


    /**
     * 删除库中数据
     */
    public void deleteByQuery() throws IOException;


    /**
     * 通过关键词检索文件
     * @param keyword 关键词
     * @param pageNo 页码
     * @param pageSize  每页几条数据
     * @return
     */
    public List<Map<String,Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException;


    /**
     * 通过关键字检索文件的总数
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    public int searchTotal(String keyword, int pageNo, int pageSize) throws IOException;
}
