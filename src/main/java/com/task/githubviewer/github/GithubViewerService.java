package com.task.githubviewer.github;

import com.task.githubviewer.github.api.GithubApiBranch;
import com.task.githubviewer.github.api.GithubApiRepo;
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
                .map(githubApiBranch -> new BranchDetails(githubApiBranch.name(),
                        githubApiBranch.commit().sha()))
                .collect(Collectors.toList());
    }

    private List<GithubApiRepo> getUserRepos(String userName) {
        String apiUrl = String.format("%s/users/%s/repos", GITHUB_API_URL, userName);
        try {
            ResponseEntity<GithubApiRepo[]> response = restTemplate.getForEntity(apiUrl, GithubApiRepo[].class);
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException("User " + userName + " not found");
        }
    }

    private List<GithubApiBranch> getUserBranches(String fullName) {
        String apiUrl = String.format("%s/repos/%s/branches", GITHUB_API_URL, fullName);
        ResponseEntity<GithubApiBranch[]> response = restTemplate.getForEntity(apiUrl, GithubApiBranch[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(response.getBody());
        } else {
            return Collections.emptyList();
        }
    }


}
