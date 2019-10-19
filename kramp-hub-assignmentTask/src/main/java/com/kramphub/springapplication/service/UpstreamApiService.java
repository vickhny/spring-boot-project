package com.kramphub.springapplication.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.kramphub.springapplication.common.Constant;
import com.kramphub.springapplication.common.HTTPRestCommunicator;
import com.kramphub.springapplication.dto.AlbumWrapperDTO;
import com.kramphub.springapplication.dto.BookWrapperDTO;
import com.kramphub.springapplication.dto.SearchElement;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UpstreamApiService {

	@Autowired
	DatamanipulationService datamanipulationService;

	@Autowired
	CommonService commonService;

	@Autowired
	HTTPRestCommunicator communicator;

	@HystrixCommand(commandKey = "getBookFromUpstreamAPI", fallbackMethod = "getBookFromUpstreamAPIFallback")
	public List<SearchElement> getBookFromUpstreamAPI(String query, String size) {

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(Constant.QUERY, query.trim());
		paramMap.put(Constant.MAXRESULT, size);

		BookWrapperDTO response = communicator.get(null, commonService.buildURI(Constant.BOOK_URI, paramMap),
				BookWrapperDTO.class);

		List<SearchElement> result = datamanipulationService.getElementsFromBookWrapperDTO(response);

		return result;
	}

	@HystrixCommand(commandKey = "getAlbumFromUpstreamAPI", fallbackMethod = "getAlbumFromUpstreamAPIFallback")
	public List<SearchElement> getAlbumFromUpstreamAPI(String query, String size) {

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(Constant.TERM, query.trim());
		paramMap.put(Constant.LIMIT, size);

		Map<String, String> headers = new HashMap<>();
		headers.put(Constant.ACCEPT, MediaType.APPLICATION_JSON.toString());
		headers.put(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

		String response = communicator.get(headers, commonService.buildURI(Constant.ALBUM_URI, paramMap), String.class);

		List<SearchElement> result = datamanipulationService
				.getElementsFromAlbumWrapperDTO(commonService.convertToObject(response, AlbumWrapperDTO.class));

		return result;
	}

	public List<SearchElement> getBookFromUpstreamAPIFallback(String query, String size) {
		List<SearchElement> response = null;

		return response;
	}

	public List<SearchElement> getAlbumFromUpstreamAPIFallback(String query, String size) {
		List<SearchElement> response = null;

		return response;
	}

}
