package com.example.githubcontroller.controller;


import com.example.githubcontroller.model.GitRepo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GitHubController {
    @GetMapping("/list")
    public List<GitRepo> listarRepositorios() {
        Map<String, String> env = System.getenv();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ env.get("GITHUB_ACCESS_TOKEN"));
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GitRepo>> repos = restTemplate.exchange(
                "https://api.github.com/user/repos",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<GitRepo>>() {});
        List<GitRepo> body = repos.getBody();
        return  body;
    }
}
