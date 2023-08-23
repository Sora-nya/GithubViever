package com.task.githubviewer.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubApiRepo(String name, @JsonProperty("full_name") String fullName) {

}
