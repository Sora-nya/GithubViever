package com.task.githubviewer.rest;

import com.task.githubviewer.github.GithubViewerService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration

public class MvcTestConfiguration {

    @MockBean
    private GithubViewerService githubViewerServiceMock;
}
