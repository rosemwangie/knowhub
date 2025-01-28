package com.bobo.knowhub.database.repository;
import com.bobo.knowhub.database.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}

