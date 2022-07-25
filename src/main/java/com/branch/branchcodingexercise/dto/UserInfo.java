package com.branch.branchcodingexercise.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class UserInfo {
    public String user_name;
    public String display_name;
    public String avatar;
    public String geo_location;
    public String email;
    public String url;
    public String created_at;
    public ArrayList<Repo> repos = new ArrayList<>();
}