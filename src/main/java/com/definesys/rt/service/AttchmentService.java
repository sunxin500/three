package com.definesys.rt.service;

import com.definesys.rt.dto.UploadResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: youchuang.shi
 * @since: 2020/5/13 15:47
 * @history: 1.2020/5/13 created by youchuang.shi
 */
@Service
@FeignClient(name = /*StaticProperties.KPM_SERVICE*/"service", url = "http://10.1.113.32:32687/ucm-io-api/")
public interface AttchmentService {
    /**
     * 上传
     *
     * @param file
     * @param collectionId
     * @return
     */
    @PostMapping(value = "/files/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadResponseDTO upload(@RequestPart("file") MultipartFile file,
                             @RequestParam(value = "collectionId") String collectionId);

    /**
     * 下载
     *
     * @param dId
     * @param fileName
     */
    @GetMapping("/files/download")
    ResponseEntity<byte[]> download(@RequestParam(value = "dId") String dId,
                                    @RequestParam(value = "fileName") String fileName);


    /**
     * 下载
     *
     * @param dId
     * @param fileName
     */
    @GetMapping("/files/preview")
    ResponseEntity<byte[]> preview(@RequestParam(value = "dId") String dId,
                                    @RequestParam(value = "fileName") String fileName);
}
