package com.bobo.knowhub.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.2.
 */
@SuppressWarnings("rawtypes")
public class SmartContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600436106100625760003560e01c8063015d15f8146100675780630c020f081461008357806349afc6e51461009f5780639105d748146100cf578063911cdec8146100ff578063ab2c4d011461011b575b...\n";

    private static String librariesLinkedBinary;

    public static final String FUNC_APPROVEVERIFICATION = "approveVerification";

    public static final String FUNC_GETTOKENBALANCE = "getTokenBalance";

    public static final String FUNC_REQUESTVERIFICATION = "requestVerification";

    public static final String FUNC_TRANSFERTOKENS = "transferTokens";

    public static final Event TOKENSTRANSFERRED_EVENT = new Event("TokensTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VERIFICATIONAPPROVED_EVENT = new Event("VerificationApproved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VERIFICATIONREQUESTED_EVENT = new Event("VerificationRequested", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected SmartContract(String contractAddress, Web3j web3j, Credentials credentials,
                            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SmartContract(String contractAddress, Web3j web3j, Credentials credentials,
                            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SmartContract(String contractAddress, Web3j web3j,
                            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SmartContract(String contractAddress, Web3j web3j,
                            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<TokensTransferredEventResponse> getTokensTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TOKENSTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<TokensTransferredEventResponse> responses = new ArrayList<TokensTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokensTransferredEventResponse typedResponse = new TokensTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.senderId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.receiverId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TokensTransferredEventResponse getTokensTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TOKENSTRANSFERRED_EVENT, log);
        TokensTransferredEventResponse typedResponse = new TokensTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.senderId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.receiverId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<TokensTransferredEventResponse> tokensTransferredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTokensTransferredEventFromLog(log));
    }

    public Flowable<TokensTransferredEventResponse> tokensTransferredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENSTRANSFERRED_EVENT));
        return tokensTransferredEventFlowable(filter);
    }

    public static List<VerificationApprovedEventResponse> getVerificationApprovedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VERIFICATIONAPPROVED_EVENT, transactionReceipt);
        ArrayList<VerificationApprovedEventResponse> responses = new ArrayList<VerificationApprovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VerificationApprovedEventResponse typedResponse = new VerificationApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.verificationId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.verifierId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VerificationApprovedEventResponse getVerificationApprovedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VERIFICATIONAPPROVED_EVENT, log);
        VerificationApprovedEventResponse typedResponse = new VerificationApprovedEventResponse();
        typedResponse.log = log;
        typedResponse.verificationId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.verifierId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<VerificationApprovedEventResponse> verificationApprovedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVerificationApprovedEventFromLog(log));
    }

    public Flowable<VerificationApprovedEventResponse> verificationApprovedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VERIFICATIONAPPROVED_EVENT));
        return verificationApprovedEventFlowable(filter);
    }

    public static List<VerificationRequestedEventResponse> getVerificationRequestedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VERIFICATIONREQUESTED_EVENT, transactionReceipt);
        ArrayList<VerificationRequestedEventResponse> responses = new ArrayList<VerificationRequestedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VerificationRequestedEventResponse typedResponse = new VerificationRequestedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.contentId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VerificationRequestedEventResponse getVerificationRequestedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VERIFICATIONREQUESTED_EVENT, log);
        VerificationRequestedEventResponse typedResponse = new VerificationRequestedEventResponse();
        typedResponse.log = log;
        typedResponse.contentId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<VerificationRequestedEventResponse> verificationRequestedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVerificationRequestedEventFromLog(log));
    }

    public Flowable<VerificationRequestedEventResponse> verificationRequestedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VERIFICATIONREQUESTED_EVENT));
        return verificationRequestedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approveVerification(BigInteger verificationId,
            BigInteger verifierId) {
        final Function function = new Function(
                FUNC_APPROVEVERIFICATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(verificationId), 
                new org.web3j.abi.datatypes.generated.Uint256(verifierId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getTokenBalance(BigInteger userId) {
        final Function function = new Function(FUNC_GETTOKENBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(userId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> requestVerification(BigInteger contentId) {
        final Function function = new Function(
                FUNC_REQUESTVERIFICATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(contentId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferTokens(BigInteger senderId,
            BigInteger receiverId, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFERTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(senderId), 
                new org.web3j.abi.datatypes.generated.Uint256(receiverId), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SmartContract load(String contractAddress, Web3j web3j, Credentials credentials,
                                     BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SmartContract load(String contractAddress, Web3j web3j,
                                     TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SmartContract load(String contractAddress, Web3j web3j, Credentials credentials,
                                     ContractGasProvider contractGasProvider) {
        return new SmartContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SmartContract load(String contractAddress, Web3j web3j,
                                     TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SmartContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SmartContract> deploy(Web3j web3j, Credentials credentials,
                                                   ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SmartContract.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SmartContract> deploy(Web3j web3j, Credentials credentials,
                                                   BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SmartContract.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SmartContract> deploy(Web3j web3j,
                                                   TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SmartContract.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SmartContract> deploy(Web3j web3j,
                                                   TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SmartContract.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

//    public static void linkLibraries(List<Contract.LinkReference> references) {
//        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
//    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class TokensTransferredEventResponse extends BaseEventResponse {
        public BigInteger senderId;

        public BigInteger receiverId;

        public BigInteger amount;
    }

    public static class VerificationApprovedEventResponse extends BaseEventResponse {
        public BigInteger verificationId;

        public BigInteger verifierId;
    }

    public static class VerificationRequestedEventResponse extends BaseEventResponse {
        public BigInteger contentId;
    }
}
