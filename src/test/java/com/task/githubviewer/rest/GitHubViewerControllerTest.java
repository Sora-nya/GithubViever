package com.task.githubviewer.rest;

import com.task.githubviewer.github.GithubRepo;
import com.task.githubviewer.github.GithubViewerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitConfig
@WebMvcTest(GitHubViewerController.class)
@ContextConfiguration(classes = MvcTestConfiguration.class)
class GitHubViewerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GithubViewerService gitHubViewerService;

    @Test
    public void testGetAllUserGithubRepos() throws Exception {

        //given
        String userName = "testuser";
        List<GithubRepo> mockRepos = new ArrayList<>();
        when(gitHubViewerService.getUserGithubRepos(userName)).thenReturn(mockRepos);
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/repos/{userName}", userName)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        //then
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockRepos.size()));
    }
}