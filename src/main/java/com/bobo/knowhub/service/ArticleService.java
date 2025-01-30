package com.bobo.knowhub.service;

import com.bobo.knowhub.database.model.Article;
import com.bobo.knowhub.database.model.Users;
import com.bobo.knowhub.dto.ArticleRequest;
import com.bobo.knowhub.database.repository.ArticleRepository;
import com.bobo.knowhub.database.repository.UserRepository;
import com.bobo.knowhub.integration.IPFSIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IPFSIntegration ipfsIntegration;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public Article createArticle(ArticleRequest request, String username) {
        try {
            // Find the author
            Users author = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Upload content to IPFS
            String ipfsHash = ipfsIntegration.uploadContent(request.getTitle(), request.getContent());

            // Create and save the article
            Article article = new Article();
            article.setTitle(request.getTitle());
            article.setContent(request.getContent());
            article.setAuthor(author);
            article.setIpfsHash(ipfsHash);
            article.setCreatedAt(LocalDateTime.now());  // Use createdAt instead of createdDate

            // Save the article
            Article savedArticle = articleRepository.save(article);

            // Reward the user with tokens
            tokenService.rewardUserForArticle(author);

            return savedArticle;
        } catch (Exception e) {
            throw new RuntimeException("Error creating article: " + e.getMessage(), e);
        }
    }

    public List<Article> getUserArticles(String username) {
        Users author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return articleRepository.findByAuthorOrderByCreatedAtDesc(author);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }
}