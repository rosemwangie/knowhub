package com.bobo.knowhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Entity
@ToString
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_seq")
    @SequenceGenerator(name = "content_seq", sequenceName = "content_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Long contentId;
    private String description;
    private String fileHash;
    private Long creatorId;
    private String createdAt;
    private boolean verified;

    @Column(nullable = false, length = 10000)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Content() {
    }

}
