package com.kramphub.springapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SearchElement {

	@JsonProperty("title")
	private String title;
	@JsonProperty("authors")
	private List<String> authors = null;
	@JsonProperty("type")
	private String type;

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("authors")
	public List<String> getAuthors() {
		return authors;
	}

	@JsonProperty("authors")
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
