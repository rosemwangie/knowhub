package com.bobo.knowhub.service;

import com.bobo.knowhub.database.model.Article;
import com.bobo.knowhub.database.model.Users;
import com.bobo.knowhub.dto.ArticleRequest;
import com.bobo.knowhub.database.repository.ArticleRepository;
import com.bobo.knowhub.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    public Article createArticle(ArticleRequest request, String username) {
        Users author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setAuthor(author);

        return articleRepository.save(article);
    }

    public List<Article> getUserArticles(String username) {
        Users author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return articleRepository.findByAuthor(author);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }
}
