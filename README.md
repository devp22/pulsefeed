# ✨ Pulsefeed

**Pulsefeed** is an intelligent news summarization tool that leverages **AWS Bedrock's Titan large language model (LLM)** to extract concise insights from full-length news articles. Built with a clean **Spring Boot** backend and powered by **JSoup** for article parsing, Pulsefeed enables developers to quickly get meaningful summaries from any news URL.

---

## 🧠 Features

- 📰 **Summarizes Real News Articles** by extracting readable content from URLs
- ⚡ **Titan Model via AWS Bedrock** for high-quality text generation
- 🔐 **Secure AWS Credentials** via `.env` and dotenv-java
- 🌐 **REST API** built using Spring Boot
- 🧼 Handles messy or HTML-heavy input with clean parsing using **JSoup**

---

## 📺 Demo Output Example

![alt text](output_gif.gif)

## 🚀 Getting Started

### 🔧 Prerequisites

- Java 18+
- Maven
- AWS account with Bedrock access
- `.env` file with:

```env
AWS_ACCESS_KEY_ID=your_key
AWS_SECRET_ACCESS_KEY=your_secret
AWS_REGION=us-east-1
```

---

### 🖥️ Local Setup

#### 🔹 1. Clone the repository

```bash
git clone https://github.com/devp22/pulsefeed
cd pulsefeed
```

#### 🔹 2. Create `.env` file

Place the following at the root of the project:

```env
AWS_ACCESS_KEY_ID=...
AWS_SECRET_ACCESS_KEY=...
AWS_REGION=us-east-1
```

#### 🔹 3. Run the API

```bash
mvn spring-boot:run
```

> Server runs at: `http://localhost:8080`

---

## 🔌 API Usage

### 📥 `GET /summary?url=<news-article-url>`

Returns a 4-sentence summary of the article.

#### 🧪 Example:

```bash
curl "http://localhost:8080/summary?url=https://example.com/article123"
```

---

## 🤖 Model Details

- **Model**: Titan Text G1
- **Provider**: AWS Bedrock
- **Settings**:
  - `maxTokenCount`: 400
  - `temperature`: 0.2

---

## 📌 Limitations

- Bedrock models may **filter content** if inputs are too long, political, or ambiguous.
- Input text must be cleaned to avoid malformed HTML or script tags.

---

## 🌱 Future Enhancements

- 🧠 Use Claude or other models for comparison
- 📊 Return token usage and cost metadata
- 🌍 Host on AWS Lambda or ECS
- 💾 Save summaries to database for logging/analysis
- 🔒 Add IAM role-based auth for enterprise use

---

## 🙌 Acknowledgements

- [AWS Bedrock](https://aws.amazon.com/bedrock/)
- [Titan Foundation Model](https://aws.amazon.com/bedrock/amazon-models/titan/)
- [JSoup](https://jsoup.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)

---

## 📜 License

This project is open-source and available under the [MIT License](https://github.com/devp22/pulsefeed/blob/main/LICENSE).

---

## 💻 Author

Made with ❤️ by [Dev Patel](https://github.com/devp22)
