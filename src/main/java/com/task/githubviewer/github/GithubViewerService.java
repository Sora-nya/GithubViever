package com.task.githubviewer.github;

import com.task.githubviewer.rest.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubViewerService {
    private static final String GITHUB_API_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    public GithubViewerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<GithubRepo> getUserGithubRepos(String userName) {
        List<GithubApiRepo> userRepos = getUserRepos(userName);
        return userRepos.stream()
                .map(githubApiRepo
                        -> new GithubRepo(githubApiRepo.name(),
                        extractUserLogin(githubApiRepo.fullName()),
                        extractBranches(githubApiRepo))
                )
                .collect(Collectors.toList());
    }

    private static String extractUserLogin(String fullName) {
        return fullName.split("/")[0];
    }

    private List<BranchDetails> extractBranches(GithubApiRepo githubApiRepo) {
        return getUserBranches(githubApiRepo.fullName()).stream()
                .map(gitHubApiBranch -> new BranchDetails(gitHubApiBranch.name(),
                        gitHubApiBranch.commit().sha()))
                .collect(Collectors.toList());
    }

    public List<GithubApiRepo> getUserRepos(String userName) {
        String apiUrl = String.format("%S/users/%S/repos", GITHUB_API_URL, userName);
        try {
            ResponseEntity<GithubApiRepo[]> response = restTemplate.getForEntity(apiUrl, GithubApiRepo[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException("User " + userName + " not found");
        }
    }

    List<GitHubApiBranch> getUserBranches(String fullName) {
        String apiUrl = String.format("%S/repos/%S/branches", GITHUB_API_URL, fullName);
        ResponseEntity<GitHubApiBranch[]> response = restTemplate.getForEntity(apiUrl, GitHubApiBranch[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(response.getBody());
        } else {
            return Collections.emptyList();
        }
    }


}
