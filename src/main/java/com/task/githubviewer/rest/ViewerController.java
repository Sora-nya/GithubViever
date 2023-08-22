package com.task.githubviewer.rest;

import com.task.githubviewer.github.GithubRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/viewer")
public class ViewerController {

    @GetMapping("/{username}")
    ResponseEntity<List<GithubRepo>> getAllUserGithubRepos() {
        return  null;
    }


}
