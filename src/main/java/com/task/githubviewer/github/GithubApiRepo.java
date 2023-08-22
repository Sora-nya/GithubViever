package com.task.githubviewer.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubApiRepo(String name, String full_name) {
}
