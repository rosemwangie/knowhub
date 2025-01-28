/** @type import('hardhat/config').HardhatUserConfig */
require("@nomicfoundation/hardhat-ethers");
require("@nomiclabs/hardhat-etherscan");
require("dotenv").config();

module.exports = {
  solidity: "0.8.18",
  paths: {
    sources: "./contracts", // This should match the folder containing .sol files
    artifacts: "./artifacts", // Default path for generated artifacts
  },
  networks: {
    sepolia: {
      url: process.env.ALCHEMY_API_URL, // Use Alchemy API URL
      accounts: [`0x${process.env.PRIVATE_KEY}`], // Add private key
    },
  },
  etherscan: {
    apiKey: process.env.ETHERSCAN_API_KEY, // Etherscan API key
  },
};
//
// console.log("DEBUG: Alchemy URL:", process.env.ALCHEMY_API_URL);
// console.log("DEBUG: Private Key Length:", process.env.PRIVATE_KEY ? process.env.PRIVATE_KEY.length : "undefined");
// console.log("DEBUG: Etherscan API Key:", process.env.ETHERSCAN_API_KEY);
// console.log("Contracts Path:", "./contracts");
// console.log("Artifacts Path:", "./artifacts");