package com.bobo.knowhub.integration;

import com.bobo.knowhub.contracts.MySmartContract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@Component
public class EthereumIntegration {

    private final MySmartContract contract;

    @Value("${ethereum.contract-address}") // Inject from application.properties or environment variable
    private String contractAddress;

    public EthereumIntegration(
            @Value("${alchemy.api-url}") String alchemyApiUrl,
            @Value("${ethereum.private-key}") String privateKey,
            @Value("${ethereum.contract-address}") String contractAddress) {
        Web3j web3j = Web3j.build(new HttpService(alchemyApiUrl));
        Credentials credentials = Credentials.create(privateKey);
        ContractGasProvider gasProvider = new DefaultGasProvider();
        this.contract = MySmartContract.load(contractAddress, web3j, credentials, gasProvider);
    }

    /**
     * Request content verification on the blockchain.
     *
     * @param contentId ID of the content to verify.
     * @return Transaction hash.
     */
    public String requestVerification(Long contentId) {
        try {
            var receipt = contract.requestVerification(BigInteger.valueOf(contentId)).send();
            return receipt.getTransactionHash();
        } catch (Exception e) {
            throw new RuntimeException("Error requesting verification: " + e.getMessage(), e);
        }
    }

    /**
     * Approve a content verification request.
     *
     * @param verificationId ID of the verification.
     * @param verifierId     ID of the verifier.
     * @return Transaction hash.
     */
    public String approveVerification(Long verificationId, Long verifierId) {
        try {
            var receipt = contract.approveVerification(
                    BigInteger.valueOf(verificationId),
                    BigInteger.valueOf(verifierId)
            ).send();
            return receipt.getTransactionHash();
        } catch (Exception e) {
            throw new RuntimeException("Error approving verification: " + e.getMessage(), e);
        }
    }

    /**
     * Transfer tokens between users.
     *
     * @param senderId   ID of the sender.
     * @param receiverId ID of the receiver.
     * @param amount     Amount of tokens to transfer.
     * @return Transaction hash.
     */
    public String transferTokens(Long senderId, Long receiverId, int amount) {
        try {
            var receipt = contract.transferTokens(
                    BigInteger.valueOf(senderId),
                    BigInteger.valueOf(receiverId),
                    BigInteger.valueOf(amount)
            ).send();
            return receipt.getTransactionHash();
        } catch (Exception e) {
            throw new RuntimeException("Error transferring tokens: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieve a user's token balance.
     *
     * @param userId ID of the user.
     * @return Token balance.
     */
    public int getTokenBalance(Long userId) {
        try {
            BigInteger balance = contract.getTokenBalance(BigInteger.valueOf(userId)).send();
            return balance.intValue();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching token balance: " + e.getMessage(), e);
        }
    }
}
