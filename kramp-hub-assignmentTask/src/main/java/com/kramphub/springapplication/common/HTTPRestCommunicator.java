package com.kramphub.springapplication.common;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class HTTPRestCommunicator {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T get(Map<String, String> httpHeadersMap, String url, Class T) {

		HttpEntity requestEntity = getHttpEntity(null, httpHeadersMap);

		RestTemplate restTemplate = new RestTemplate();

		T responseFromAPI = null;
		try {

			ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, T);

			if (null != response && null != response.getBody()) {
				responseFromAPI = response.getBody();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return responseFromAPI;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private HttpEntity getHttpEntity(Object request, Map<String, String> httpHeadersMap) {

		HttpHeaders httpHeaders = null;

		if (!CollectionUtils.isEmpty(httpHeadersMap)) {

			httpHeaders = new HttpHeaders();

			for (Map.Entry<String, String> entry : httpHeadersMap.entrySet()) {
				httpHeaders.add(entry.getKey(), entry.getValue());
			}
		}

		HttpEntity entity = new HttpEntity(request, httpHeaders);

		return entity;
	}
}
