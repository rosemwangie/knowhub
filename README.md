# Decentralized Knowledge Sharing Platform

### Overview    ...in progress

- **Introduction**
- **Features**
- **Technologies used**
- **Architecture**
- **Setup and Installation**
- **Running the Application**
- **API Endpoints**
- **Security**
- **Testing**

### Presentation

[knowhub.mp4](..%2F..%2FDownloads%2Fknowhub.mp4)


### Introduction
The Decentralized Knowledge Sharing Platform is a facility for secure, transparent, self-sovereign creation, sharing, and access of information by users. In other words, it looks toward blockchain technology to verify content and decentralized storage systems such as IPFS for the integrity of data and to incentivize high-quality contributions

### Features

- **User Management**
- **Content creation and sharing**
- **Blockchain Integration**
- **Incentive System**
- **Search and Discovery**
- **Analytics Dashboard**

### Tech Tools

- **Backend** 
  - **Java, Spring Boot**
- **Database**: 
  - PostgreSQL
- **Frontend**: 
  - Next.js
  - Tailwind CSS
- **Blockchain**: 
  - Solidity for smart contracts
- **Decentralized Storage**
  - IPFS(InterPlanetary File System)
- **Machine Learning**
  - TensorFlow
- **Cloud Services**
  - ****
- **CI/CD**
  - GitHub actions
- **Containerization**
  - Docker

### Architecture

### Setup

#### Clone the Repo

#### Setup Environment Variables

```yaml

```

### Build and Run the App
**Backend**

**1.Build the project**
```agsl
---
```

**2.Run**
```agsl
mvn spring-boot::run
```

```agsl
npm run dev
```

```agsl
npx hardhat node
```

```agsl
ipfs daemon
```
```agsl
npx hardhat run script/deploy.js --network localhost
```


**Frontend**

**0.Navigate to the client dir**
```agsl
cd client
```
**1.Install dependencies**
```agsl
cd client
```
**2.Run the app**
```agsl
cd client
```

### API Endpoint
#### User Management 

- Register User: `Post /api/auth/register`
  - Req body:
    ```json
    {
    "username": "testuser",
    "email": "testuser@example.com",
    "password":"password123"
    }
    ```
    

### Security

### Testing

**Unit Tests**

**Integration Tests**

**API Testing with Postman**



