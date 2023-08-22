package com.task.githubviewer.github;

import lombok.NonNull;

import java.util.List;

public record GithubRepo(@NonNull String name, @NonNull String userLogin, @NonNull List<BranchDetails> branches) {
}
