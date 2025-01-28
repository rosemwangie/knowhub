package com.bobo.knowhub.controller;

import com.bobo.knowhub.dto.ApproveVerificationDTO;
import com.bobo.knowhub.service.VerificationService;
import com.bobo.knowhub.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    /**
     * Requests content verification.
     *
     * @param contentId The ID of the content to be verified.
     * @return ApiResponse with verification request status.
     */
    @PostMapping("/request")
    public ApiResponse<String> requestVerification(@RequestBody String contentId) {
        Long contentIdLong = Long.parseLong(contentId);
        String result = verificationService.requestVerification(contentIdLong);
        return ApiResponse.success(HttpStatus.OK.value(), "Verification request submitted", result);
    }

    /**
     * Approves the content verification.
     *
     * @param request The approval request containing verificationId and verifierId.
     * @return ApiResponse with approval status.
     */
    @PostMapping("/approve")
    public ApiResponse<String> approveVerification(@RequestBody ApproveVerificationDTO request) {
        String result = verificationService.approveVerification(request.getVerificationId(), request.getVerifierId());
        return ApiResponse.success(HttpStatus.OK.value(), "Verification approved", result);
    }

    /**
     * Rejects the content verification.
     *
     * @param request The rejection request containing verificationId and verifierId.
     * @return ApiResponse with rejection status.
     */
    @PostMapping("/reject")
    public ApiResponse<String> rejectVerification(@RequestBody ApproveVerificationDTO request) {
        String result = verificationService.rejectVerification(request.getVerificationId(), request.getVerifierId());
        return ApiResponse.success(HttpStatus.OK.value(), "Verification rejected", result);
    }
}
