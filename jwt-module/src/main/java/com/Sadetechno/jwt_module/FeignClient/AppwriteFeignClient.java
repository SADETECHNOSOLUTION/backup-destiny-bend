package com.Sadetechno.jwt_module.FeignClient;

import com.Sadetechno.jwt_module.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@FeignClient(
    name = "appwrite-api", 
    url = "${appwrite.endpoint}", 
    configuration = FeignConfig.class // <--- Links to your headers config
)
public interface AppwriteFeignClient {

    @PostMapping(value = "/databases/{dbId}/collections/{collId}/documents")
    Map<String, Object> createDocument(
        @PathVariable("dbId") String dbId,
        @PathVariable("collId") String collId,
        @RequestBody Map<String, Object> body
    );
}