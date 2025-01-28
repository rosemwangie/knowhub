package com.bobo.knowhub.service;

import com.bobo.knowhub.integration.EthereumIntegration;
import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.model.Verification;
import com.bobo.knowhub.database.repository.ContentRepository;
import com.bobo.knowhub.database.repository.UserRepository;
import com.bobo.knowhub.database.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationService {
    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EthereumIntegration ethereumIntegration;

    /**
     * Handles a request for verifying content.
     *
     * @param contentId ID of the content to verify.
     * @return A success message or error.
     */
    public String requestVerification(Long contentId) {
        if (!contentRepository.existsById(contentId)) {
            throw new RuntimeException("Content not found");
        }

        // Create a new verification record with a "pending" status
        Verification verification = new Verification();
        verification.setContent(contentRepository.findById(contentId).get()); // Setting the content relation
        verification.setStatus("Pending");
        verification.setVerifiedAt(null);

        verificationRepository.save(verification);

        // Optionally send a request to the Ethereum blockchain
        ethereumIntegration.requestVerification(contentId);

        return "Verification request created successfully. ID: " + verification.getVerificationId();
    }

    /**
     * Approves a verification request.
     *
     * @param verificationId ID of the verification request.
     * @param verifierId     ID of the verifier.
     * @return A success message or error.
     */
    public String approveVerification(Long verificationId, Long verifierId) {
        Verification verification = verificationRepository.findById(verificationId)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        if (!"Pending".equals(verification.getStatus())) {
            throw new RuntimeException("Verification request is not in a pending state");
        }

        if (!userRepository.existsById(verifierId)) {
            throw new RuntimeException("Verifier not found");
        }

        // Set the verifier (replace setVerifierId with setVerifier)
        Users verifier = userRepository.findById(verifierId)
                .orElseThrow(() -> new RuntimeException("Verifier not found"));
        verification.setVerifier(verifier);

        // Update verification status and timestamp
        verification.setStatus("Approved");
        verification.setVerifiedAt(LocalDateTime.now().toString());
        verificationRepository.save(verification);

        // Update content's verified status in the database
        contentRepository.findById(verification.getContent().getId()).ifPresent(content -> {
            content.setVerified(true);
            contentRepository.save(content);
        });

        // Notify the blockchain of the approval
        ethereumIntegration.approveVerification(verificationId, verifierId);

        return "Verification approved successfully. Verification ID: " + verificationId;
    }

    /**
     * Rejects a verification request.
     *
     * @param verificationId ID of the verification request.
     * @param verifierId     ID of the verifier rejecting the request.
     * @return A success message or error.
     */
    public String rejectVerification(Long verificationId, Long verifierId) {
        Verification verification = verificationRepository.findById(verificationId)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        if (!"Pending".equals(verification.getStatus())) {
            throw new RuntimeException("Verification request is not in a pending state");
        }

        if (!userRepository.existsById(verifierId)) {
            throw new RuntimeException("Verifier not found");
        }

        // Set the verifier (replace setVerifierId with setVerifier)
        Users verifier = userRepository.findById(verifierId)
                .orElseThrow(() -> new RuntimeException("Verifier not found"));
        verification.setVerifier(verifier);

        // Update verification status to "Rejected"
        verification.setStatus("Rejected");
        verification.setVerifiedAt(LocalDateTime.now().toString());
        verificationRepository.save(verification);

        return "Verification rejected successfully. Verification ID: " + verificationId;
    }
}
