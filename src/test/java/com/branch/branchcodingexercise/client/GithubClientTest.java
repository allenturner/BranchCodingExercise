package com.branch.branchcodingexercise.client;

import com.branch.branchcodingexercise.dto.response.gitlab.UserInfoGitlab;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
class GithubClientTest {

    private final MockWebServer mockWebServer = new MockWebServer();
    private final GithubClient apiCaller = new GithubClient(WebClient.create(mockWebServer.url("https://api.github.com/users").toString()));

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getUserInfo() throws IOException {
        String resourceName = "/User.json";
        InputStream inputStream = GithubClientTest.class.getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }

        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
        String data = new String(bdata, StandardCharsets.UTF_8);

        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(data)
        );
        UserInfoGitlab response = apiCaller.getUserInfo("octocat").block();
        assertThat(response.getAvatar_url(), is("https://avatars.githubusercontent.com/u/583231?v=4"));
        assertThat(response.getLogin(), is("octocat"));

    }
}