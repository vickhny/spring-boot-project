package com.kramphub.springapplication.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kramphub.springapplication.dto.SearchElement;

@Service
public class SearchService {

	@Autowired
	UpstreamApiService upstreamApiService;

	public List<SearchElement> getSearchElements(String query, Integer size) {

		List<SearchElement> book_searchElements = upstreamApiService.getBookFromUpstreamAPI(query, size.toString());

		List<SearchElement> album_searchElements = upstreamApiService.getAlbumFromUpstreamAPI(query, size.toString());

		List<SearchElement> result = new ArrayList<SearchElement>();
		if (book_searchElements != null) {
			result.addAll(book_searchElements);
		}
		if (album_searchElements != null) {
			result.addAll(album_searchElements);
		}

		result = result.stream().sorted(Comparator.comparing(SearchElement::getTitle)).collect(Collectors.toList());

		return result;
	}

}
