package com.bobo.knowhub.database.repository;

import com.bobo.knowhub.database.model.Article;
import com.bobo.knowhub.database.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByAuthor(Users author);
}