package com.bobo.knowhub.database.model;
import com.bobo.knowhub.model.Users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
//@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
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

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean verified;


    @Column(nullable = false, length = 10000)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Transient
    private MultipartFile file;

    public Content() {
    }



}
