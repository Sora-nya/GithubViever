package com.task.githubviewer.github.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubApiBranch(String name, Commit commit) {
}
