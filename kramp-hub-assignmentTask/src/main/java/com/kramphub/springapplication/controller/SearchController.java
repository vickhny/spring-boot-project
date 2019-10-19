package com.kramphub.springapplication.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kramphub.springapplication.dto.SearchElement;
import com.kramphub.springapplication.exception.SearchServiceException;
import com.kramphub.springapplication.exception.InvalidInputException;
import com.kramphub.springapplication.exception.ResourceNotFoundException;
import com.kramphub.springapplication.service.SearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/search")
@Api(tags = "Get books and album related to the input term", produces = "application/json")
public class SearchController {

	@Autowired
	SearchService searchService;

	/**
	 * API to get maximum of 5 books and maximum of 5 albums that are related to the
	 * input term.
	 * 
	 * @throws ResourceNotFoundException
	 * @throws InvalidInputException
	 * @throws SearchServiceException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@GetMapping(value = "/term", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get books and album related to the input term", notes = "API to get maximum of 5 books and maximum of 5 albums that are related to the input term.", response = Map.class)
	public ResponseEntity<List<SearchElement>> getElements(
			@ApiParam(required = true) @RequestParam(required = true) String query,
			@ApiParam(required = false, defaultValue = "5") @RequestParam(required = false, defaultValue = "5") Integer size)
			throws SearchServiceException, ResourceNotFoundException, InvalidInputException

	{

		if (StringUtils.isEmpty(query)) {
			throw new InvalidInputException("Invalid input param!!");
		}

		try {

			List<SearchElement> response = searchService.getSearchElements(query, size);

			if (CollectionUtils.isEmpty(response)) {
				throw new ResourceNotFoundException("Result not found!!");
			}

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (SearchServiceException e) {
			throw new SearchServiceException("Internal Server Exception!!");
		}

	}

}
