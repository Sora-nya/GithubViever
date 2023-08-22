package com.task.githubviewer.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubApiBranch(String name, Commit commit) {
}
