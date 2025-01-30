package com.bobo.knowhub.integration;

import com.bobo.knowhub.contracts.SmartContract;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Component
public class EthereumIntegration implements InitializingBean {

    @Value("${alchemy.api-url}")
    private String alchemyApiUrl;

    @Value("${ethereum.private-key}")
    private String privateKey;

    @Value("${ethereum.contract-address}")
    private String contractAddress;

    private Web3j web3j;
    private SmartContract contract;
    private Credentials credentials;

    @Override
    public void afterPropertiesSet() {
        try {
            // Initialize Web3j
            web3j = Web3j.build(new HttpService(alchemyApiUrl));

            // Initialize credentials
            credentials = Credentials.create(privateKey);

            // Initialize gas provider with higher gas limit for deployment
            ContractGasProvider gasProvider = new StaticGasProvider(
                    BigInteger.valueOf(30000000000L), // 30 Gwei
                    BigInteger.valueOf(3000000)       // Gas limit
            );

            if (contractAddress == null || contractAddress.isEmpty()) {
                // Deploy new contract
                contract = deployContract(gasProvider);
            } else {
                // Load existing contract
                contract = loadContract(gasProvider);
            }

            // Test connection
            Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
            System.out.println("Connected to Ethereum client version: " + clientVersion.getWeb3ClientVersion());
            System.out.println("Contract address: " + contract.getContractAddress());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Web3j: " + e.getMessage(), e);
        }
    }

    private SmartContract deployContract(ContractGasProvider gasProvider) throws Exception {
        System.out.println("Deploying new contract...");
        SmartContract newContract = SmartContract.deploy(
                web3j,
                credentials,
                gasProvider
        ).send();
        System.out.println("Contract deployed to: " + newContract.getContractAddress());
        return newContract;
    }

    private SmartContract loadContract(ContractGasProvider gasProvider) {
        System.out.println("Loading existing contract at: " + contractAddress);
        return SmartContract.load(
                contractAddress,
                web3j,
                credentials,
                gasProvider
        );
    }


    public Map<String, Object> getDebugInfo() {
        Map<String, Object> debug = new HashMap<>();
        try {
            // Get network info
            Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
            debug.put("clientVersion", clientVersion.getWeb3ClientVersion());

            // Get contract info
            debug.put("contractAddress", contractAddress);

            // Get account balance
            BigInteger balance = web3j.ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST)
                    .send().getBalance();
            debug.put("contractBalance", balance.toString());

            // Check if contract exists
            String code = web3j.ethGetCode(contractAddress, DefaultBlockParameterName.LATEST)
                    .send().getCode();
            debug.put("hasCode", !code.equals("0x"));

            // Get credentials info
            debug.put("credentialsAddress", credentials.getAddress());

            debug.put("status", "connected");
        } catch (Exception e) {
            debug.put("status", "error");
            debug.put("error", e.getMessage());
        }
        return debug;
    }

    public String requestVerification(Long contentId) {
        try {
            var receipt = contract.requestVerification(BigInteger.valueOf(contentId)).send();
            return receipt.getTransactionHash();
        } catch (Exception e) {
            throw new RuntimeException("Error requesting verification: " + e.getMessage(), e);
        }
    }

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

    public int getTokenBalance(Long userId) {
        try {
            BigInteger balance = contract.getTokenBalance(BigInteger.valueOf(userId)).send();
            return balance.intValue();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching token balance: " + e.getMessage(), e);
        }
    }

    public String getContractAddress() {
        return contractAddress;
    }
}