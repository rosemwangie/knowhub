package com.bobo.knowhub.database.repository;

import com.bobo.knowhub.database.model.Article;
import com.bobo.knowhub.database.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Find articles by author ordered by created date
    List<Article> findByAuthorOrderByCreatedAtDesc(Users author);

    // Find all articles ordered by created date
    List<Article> findAllByOrderByCreatedAtDesc();
}