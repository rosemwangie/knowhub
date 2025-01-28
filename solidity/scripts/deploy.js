const hre = require("hardhat");

async function main() {
    // Get the contract factory
    const MySmartContract = await hre.ethers.getContractFactory("MySmartContract");

    // Deploy the contract
    const contract = await MySmartContract.deploy();

    // Wait for the transaction to be mined
    const deploymentReceipt = await contract.waitForDeployment();

    // Get the deployed contract address
    const deployedAddress = contract.target;

    console.log("Contract deployed to:", deployedAddress);
    console.log("Deployment receipt:", deploymentReceipt);
}

main().catch((error) => {
    console.error(error);
    process.exitCode = 1;
});
