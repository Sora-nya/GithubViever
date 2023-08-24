package com.task.githubviewer.github;

import com.task.githubviewer.github.api.Commit;
import com.task.githubviewer.github.api.GithubApiBranch;
import com.task.githubviewer.github.api.GithubApiRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class GithubViewerServiceTest {
    GithubViewerService githubViewerService;

    RestTemplate mockRestTemplate;


    @BeforeEach
    void setUp() {
        mockRestTemplate = mock(RestTemplate.class);
        githubViewerService = new GithubViewerService(mockRestTemplate);

    }

    @Test
    void shouldGetUserGithubRepos() {
        //given
        String userName = "testuser";
        String getRepoUrl = String.format("https://api.github.com/users/%s/repos", userName);
        String userRepoName = "testuser/testrepo";
        String testRepo = "testrepo";
        String getBranchesUrl = String.format("https://api.github.com/repos/%s/branches", userRepoName);
        GithubApiRepo[] mockRepos = {new GithubApiRepo(testRepo,userRepoName)};
        when(mockRestTemplate.getForEntity(eq(getRepoUrl), any())).thenReturn(new ResponseEntity<>(mockRepos, HttpStatus.OK));
        String branchName = "main";
        Commit commit = new Commit("6c8a5b09a1e76fb3b2bb7e39ec45a4b47f292c04");
        GithubApiBranch[] mockBranches = {new GithubApiBranch(branchName, commit)};
        when(mockRestTemplate.getForEntity(eq(getBranchesUrl),any())).thenReturn(new ResponseEntity<>(mockBranches, HttpStatus.OK));
        //when
        List<GithubRepo> result = githubViewerService.getUserGithubRepos(userName);

        //then

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);

        GithubRepo repo = result.get(0);
        assertThat(repo.userLogin()).isEqualTo(userName);
        assertThat(repo.name()).isEqualTo(testRepo);
        assertThat(repo.branches()).hasSize(1);

        BranchDetails branch = repo.branches().get(0);
        assertThat(branch.name()).isEqualTo(branchName);
        assertThat(branch.sha()).isEqualTo(commit.sha());

        // Verify that the getForEntity methods were called
        verify(mockRestTemplate, times(1)).getForEntity(getRepoUrl, GithubApiRepo[].class);
        verify(mockRestTemplate, times(1)).getForEntity(getBranchesUrl, GithubApiBranch[].class);
    }

}