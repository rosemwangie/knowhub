require("dotenv").config();

console.log("Alchemy URL:", process.env.ALCHEMY_API_URL);
console.log("Private Key Length:", process.env.PRIVATE_KEY ? process.env.PRIVATE_KEY.length : "undefined");
console.log("Etherscan API Key:", process.env.ETHERSCAN_API_KEY);
