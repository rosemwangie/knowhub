package com.bobo.knowhub.database.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificationId; // Primary key for the verification entity

    @ManyToOne
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private Content content; // Relation to Content

    @ManyToOne
    @JoinColumn(name = "verifier_id", referencedColumnName = "id")
    private Users verifier; // Relation to User (Verifier)

    private String status; // Pending, Approved, Rejected
    private String verifiedAt;
}
