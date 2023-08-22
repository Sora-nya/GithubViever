package com.task.githubviewer.github;

import lombok.NonNull;

public record BranchDetails(@NonNull String name, @NonNull String sha) {
}
