package com.branch.branchcodingexercise.service;

import com.branch.branchcodingexercise.client.GithubClient;
import com.branch.branchcodingexercise.dto.UserInfo;
import com.branch.branchcodingexercise.dto.response.gitlab.Repo;
import com.branch.branchcodingexercise.dto.response.gitlab.UserInfoGitlab;
import com.branch.branchcodingexercise.util.Formatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BranchGitHubServiceImpl implements BranchService{
    GithubClient githubClient;
    Formatter formatter = new Formatter();

    @Value("${github_url}")
    private String githubUrl;


    public BranchGitHubServiceImpl(GithubClient aGithubClient) {
        this.githubClient = aGithubClient;
    }

    public UserInfo getUserInfo(String userName) {
        UserInfo aUserInfo = new UserInfo();
        // We need to block for the content here or the JVM might exit before the message is logged
        UserInfoGitlab userInfoGitlab = githubClient.getUserInfo(userName).block();

        if (userInfoGitlab != null) {
            // We could use MapStruct
            aUserInfo.setUser_name(userInfoGitlab.getLogin());
            aUserInfo.setDisplay_name(userInfoGitlab.getName());
            aUserInfo.setAvatar(String.valueOf(userInfoGitlab.getAvatar_url()));
            aUserInfo.setGeo_location(String.valueOf(userInfoGitlab.getLocation()));
            aUserInfo.setEmail(userInfoGitlab.getEmail());
            aUserInfo.setUrl(userInfoGitlab.getHtml_url());
            aUserInfo.setCreated_at(formatter.formatIsoTs(userInfoGitlab.getCreated_at()));

            Repo[] repoArray = githubClient.getUserRepos(userName).block();
            for (Repo gitRepo : repoArray) {
                com.branch.branchcodingexercise.dto.Repo repo = new com.branch.branchcodingexercise.dto.Repo();
                repo.setName(gitRepo.getName());
                repo.setUrl(String.format("%s/%s", githubUrl, gitRepo.getFull_name()));
                aUserInfo.getRepos().add(repo);
            }
        }
        return aUserInfo;
    }


}