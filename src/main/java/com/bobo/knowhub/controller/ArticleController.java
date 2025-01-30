package com.bobo.knowhub.controller;

import com.bobo.knowhub.database.model.Article;
import com.bobo.knowhub.database.repository.ArticleRepository;
import com.bobo.knowhub.dto.ArticleRequest;
import com.bobo.knowhub.service.ArticleService;
import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/store/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private IPFS ipfs;

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

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/ipfs/{hash}")
    public ResponseEntity<String> getArticleContent(@PathVariable String hash) {
        try {
            Multihash filePointer = Multihash.fromBase58(hash);
            byte[] fileContents = ipfs.cat(filePointer);
            return ResponseEntity.ok(new String(fileContents));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Article content not found");
        }
    }

    private String getIPFSGatewayUrl(String hash) {
        return "http://localhost:8080/ipfs/" + hash;
    }
}