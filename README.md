# GitHub Viewer API

GitHub Viewer is a user-friendly API that lets you effortlessly fetch valuable information about GitHub repositories owned by users. Built with Java 17 and Spring, this API is designed to provide accurate responses, easy integration, and compatibility with modern standards.

## Features

- **User-Focused:** Retrieve crucial details about GitHub repositories for a given user.
- **Efficient:** Developed using Java 17 and Spring, ensuring performance and reliability.
- **Accurate Responses:** Get accurate repository information, including names, owners, and branch details.
- **Error Handling:** Comprehensive error handling for different scenarios, delivering informative messages.
- **Flexible Media Type:** Accepts "application/json" for easy integration and clear JSON responses.

## Installation

To run the GitHub Viewer API locally, follow these steps:

1. **Clone the repository:**

```bash
git clone https://github.com/Sora-nya/GithubViever.git
```

2. **Navigate to the project directory:**

```bash
cd GithubViever
```


3. **Run the application:**

```
./mvnw spring-boot:run
```

## API Usage

### List User Repositories

Endpoint: `/api/v1/repos/{username}`

To list non-fork repositories for a GitHub user, make a GET request with the following headers:

- `Accept: application/json`

Example Request:

`GET /api/v1/repos/octocat
Accept: application/json
`

Example Response:
```json
[
    {
        "name": "hello-world",
        "userLogin": "octocat",
        "branches": [
            {
                "name": "main",
                "sha": "6dcb09b5b57875f334f61aebed695e2e4193db5e"
            },
            ...
        ]
    },
    ...
]
```
## Error Codes

User Not Found

Example Request:

```bash
GET /api/repositories/nonexistentuser
Accept: application/json
```
Example Response:

```json
{
    "status": 404,
    "message": "User not found"
}
```

Unsupported Media Type
Example Request:

```bash
GET /api/repositories/octocat
Accept: application/xml
```
Example Response:

```json
{
    "status": 406,
    "message": "Unsupported media type"
}