package com.bobo.knowhub.controller;

import com.bobo.knowhub.database.model.Article;
import com.bobo.knowhub.dto.ArticleRequest;
import com.bobo.knowhub.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/store/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> createArticle(
            @RequestBody ArticleRequest request,
            Principal principal) {
        Article article = articleService.createArticle(request, principal.getName());
        return ResponseEntity.ok(article);
    }

    @GetMapping("/my-articles")
    public ResponseEntity<List<Article>> getMyArticles(Principal principal) {
        List<Article> articles = articleService.getUserArticles(principal.getName());
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }
}