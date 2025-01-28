package com.bobo.knowhub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenTransferRequest {
    private Long senderId;
    private Long receiverId;
    private int amount;
}
