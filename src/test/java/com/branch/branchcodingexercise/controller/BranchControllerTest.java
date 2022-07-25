package com.branch.branchcodingexercise.controller;

import com.branch.branchcodingexercise.dto.UserInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

/*Basic Integration Test*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BranchControllerTest {
    @Autowired
    private BranchController aBranchController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void index() {
        // we test that our controller is not null
        Assertions.assertThat(aBranchController).isNotNull();
    }

    @Test
    void findAnExistingUserTest() {
        UserInfo aUserInfo = restTemplate.getForObject("http://localhost:" + port + "/gitinfo/octocat", UserInfo.class);
        Assertions.assertThat(aUserInfo.getUser_name().equals("octocat") );
    }

    @Test
    void findANonExistingUserTest() {
        String error = restTemplate.getForObject("http://localhost:" + port + "/gitinfo/octocatBOGUS", String.class);
        Assertions.assertThat(error.equals("Sorry, User Not Found") );
    }


}