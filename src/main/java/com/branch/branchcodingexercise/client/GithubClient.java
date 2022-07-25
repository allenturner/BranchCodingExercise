package com.branch.branchcodingexercise.client;

import com.branch.branchcodingexercise.dto.response.gitlab.Repo;
import com.branch.branchcodingexercise.dto.response.gitlab.UserInfoGitlab;
import com.branch.branchcodingexercise.util.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GithubClient {
    private final WebClient client;

    public GithubClient(WebClient builder) {
        this.client = builder;
    }

    public Mono<UserInfoGitlab> getUserInfo(String userName) {
        return this.client.get().uri("/{userName}", userName)
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.NOT_FOUND), response -> Mono.error(new UserNotFoundException()))
                .bodyToMono(UserInfoGitlab.class);
    }

    public Mono<Repo[]> getUserRepos(String userName) {
        return this.client.get().uri("/{userName}/repos", userName)
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.NOT_FOUND), response -> Mono.error(new UserNotFoundException()))
                .bodyToMono(Repo[].class);
    }
}