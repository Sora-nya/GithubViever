package com.task.githubviewer.rest;

import com.task.githubviewer.github.GithubRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/viewer")
public class ViewerController {

    @GetMapping(value = "/{username}")
    ResponseEntity<List<GithubRepo>> getAllUserGithubRepos(@RequestHeader(HttpHeaders.ACCEPT) String acceptHeader) {
        if (!acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new NotAcceptableException("Unsupported media type: " + acceptHeader + " not accepted");
        }

        return  null;
    }


}
