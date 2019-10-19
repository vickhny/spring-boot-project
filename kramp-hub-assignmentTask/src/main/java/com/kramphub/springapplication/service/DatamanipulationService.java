package com.kramphub.springapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kramphub.springapplication.dto.AlbumWrapperDTO;
import com.kramphub.springapplication.dto.BookWrapperDTO;
import com.kramphub.springapplication.dto.Item;
import com.kramphub.springapplication.dto.Result;
import com.kramphub.springapplication.dto.SearchElement;

@Service
public class DatamanipulationService {

	public List<SearchElement> getElementsFromBookWrapperDTO(BookWrapperDTO bookWrapperDTO) {

		List<SearchElement> searchElementList = new ArrayList<SearchElement>();

		for (Item item : bookWrapperDTO.getItems()) {

			SearchElement searchElement = new SearchElement();
			searchElement.setTitle(item.getVolumeInfo() != null ? item.getVolumeInfo().getTitle() : "Not Available!!");

			if (searchElement.getAuthors() == null) {
				searchElement.setAuthors(new ArrayList<>());
			}
			if (item.getVolumeInfo() != null && item.getVolumeInfo().getAuthors() != null) {
				searchElement.getAuthors().addAll(item.getVolumeInfo().getAuthors());
			}

			searchElement.setType("Book");

			searchElementList.add(searchElement);
		}

		return searchElementList;

	}

	public List<SearchElement> getElementsFromAlbumWrapperDTO(AlbumWrapperDTO albumWrapperDTO) {

		List<SearchElement> searchElementList = new ArrayList<SearchElement>();

		for (Result result : albumWrapperDTO.getResults()) {

			SearchElement searchElement = new SearchElement();
			searchElement.setTitle(result.getTrackName());

			if (searchElement.getAuthors() == null) {
				searchElement.setAuthors(new ArrayList<>());
			}
			searchElement.getAuthors().add(result.getArtistName());
			searchElement.setType("Album");

			searchElementList.add(searchElement);
		}

		return searchElementList;

	}

}
