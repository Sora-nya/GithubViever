package com.task.githubviewer.rest;

import com.task.githubviewer.github.GithubRepo;
import com.task.githubviewer.github.GithubViewerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repos")
public class GitHubViewerController {

    private final GithubViewerService githubViewerService;

    public GitHubViewerController(GithubViewerService githubViewerService) {
        this.githubViewerService = githubViewerService;
    }

    @GetMapping(value = "/{userName}")
    ResponseEntity<List<GithubRepo>> getAllUserGithubRepos(@RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
                                                           @PathVariable String userName) {
        if (!acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new NotAcceptableException("Unsupported media type: " + acceptHeader + " not accepted");
        }
        List<GithubRepo> githubRepos = githubViewerService.getUserGithubRepos(userName);
        return ResponseEntity.ok(githubRepos);
    }


}
