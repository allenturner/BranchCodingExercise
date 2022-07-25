package com.branch.branchcodingexercise.controller;

import com.branch.branchcodingexercise.dto.UserInfo;
import com.branch.branchcodingexercise.service.BranchGitHubServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;

@Controller
@Validated
@Slf4j
public class BranchController {

    private BranchGitHubServiceImpl aBranchGitHubServiceImpl;

    public BranchController(BranchGitHubServiceImpl aBranchGitHubServiceImpl) {
        this.aBranchGitHubServiceImpl = aBranchGitHubServiceImpl;
    }

    @GetMapping(value = "/gitinfo/{userName}")
    public @ResponseBody UserInfo getGitinfo(@PathVariable("userName") @NotBlank String userName) {
        return aBranchGitHubServiceImpl.getUserInfo(userName);

    }
}