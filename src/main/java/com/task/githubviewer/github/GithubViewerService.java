package com.task.githubviewer.github;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubViewerService {
    private final String gitHubApiUrl = "https://api.github.com";
    private final RestTemplate restTemplate;

    public GithubViewerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<GithubRepo> getUserGithubRepos(String user) {
        List<GithubApiRepo> userRepos = getUserRepos(user);

        return userRepos.stream().map(githubApiRepo
                -> new GithubRepo(githubApiRepo.name(),
                githubApiRepo.full_name(),
                List.of()))
                .collect(Collectors.toList());
    }

    public List<GithubApiRepo> getUserRepos(String username) {
        String apiUrl = gitHubApiUrl + "/users/" + username + "/repos";

        ResponseEntity<GithubApiRepo[]> response = restTemplate.getForEntity(apiUrl, GithubApiRepo[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(response.getBody());
        } else {
            return Collections.emptyList();
        }

    }


}
