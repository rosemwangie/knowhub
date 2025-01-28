const { expect } = require("chai");

describe("MySmartContract", function () {
    let contract;

    before(async function () {
        const Contract = await ethers.getContractFactory("MySmartContract");
        contract = await Contract.deploy();
        await contract.waitForDeployment();
    });

    it("Should allow requesting verification", async function () {
        await contract.requestVerification(12345);
        const isVerified = await contract.verifiedContent(12345);
        expect(isVerified).to.equal(false);
    });

    it("Should approve verification", async function () {
        await contract.approveVerification(12345, 1);
        const isVerified = await contract.verifiedContent(12345);
        expect(isVerified).to.equal(true);
    });
});
