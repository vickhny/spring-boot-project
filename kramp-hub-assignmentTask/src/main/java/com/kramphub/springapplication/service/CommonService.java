package com.kramphub.springapplication.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommonService {

	public String buildURI(String url, Map<String, String> mapToConvert) {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		for (Entry<String, String> entry : mapToConvert.entrySet()) {
			params.add(entry.getKey(), entry.getValue());
		}

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https").host(url).queryParams(params)
				.build();

		return uriComponents.toUriString().trim();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T convertToObject(String objectData, Class T) {

		ObjectMapper mapper = new ObjectMapper();

		T parsedObject = null;

		try {

			parsedObject = (T) mapper.readValue(objectData, T);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parsedObject;
	}

}
