package com.zgq.plainarticle.rest;

import cn.hutool.json.JSONUtil;
import com.zgq.plainarticle.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateHelper {

    @Autowired
    private RestTemplate restTemplate;

    public <T> Response<T> postByJson(String url, Object body, ParameterizedTypeReference<Response<T>> parameterizedTypeReference) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity(JSONUtil.toJsonStr(body), headers);

        Response<T> result = restTemplate.exchange(url, HttpMethod.POST, formEntity, parameterizedTypeReference).getBody();
        return result;
    }

    public <T> Response<T> postByJson(String url, Object body) {

        ParameterizedTypeReference<Response<T>> parameterizedTypeReference = new ParameterizedTypeReference<Response<T>>() {};

        return postByJson(url, body, parameterizedTypeReference);
    }

    public <T> Class<T> getForObject(String url, T responseType) {

        return null;
    }
}
