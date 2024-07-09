import os
import json
import requests
from openai import OpenAI

# Set up OpenAI API key
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

github_token = os.getenv("GITHUB_TOKEN")
repo_full_name = os.getenv("GITHUB_REPOSITORY")
pr_number = os.getenv("GITHUB_PR_NUMBER")

# getting the repo and diff through gitHub api
def fetch_pr_diff(repo, pr, token):
    pr_diff_url = f"https://api.github.com/repos/{repo}/pulls/{pr}"
    print(f"git info:{repo},{pr},{token}")
    headers = {'Authorization': f'Bearer {token}', 'Accept': 'application/vnd.github.v3.diff'}
    diff_response = requests.get(pr_diff_url, headers=headers)
    diff_response.raise_for_status()
    return diff_response

diff_response = fetch_pr_diff(repo_full_name, pr_number, github_token)
print(f"diff response : {diff_response}")

# Fetch the diff content from the URL
diff = diff_response.text
print(f"diffs>{diff}")

def create_prompt():
    return f'''Your task is to review pull requests. Instructions:
- Provide the response in the following JSON format: {{"reviews": [{{"lineNumber":  <line_number>, "reviewComment": "<review comment>"}}]}}
- Do not give positive comments or compliments.
- Provide comments and suggestions ONLY if there is something to improve, otherwise "reviews" should be an empty array.
- Write the comment in GitHub Markdown format.
- Use the given description only for the overall context and only comment on the code.
- IMPORTANT: NEVER suggest adding comments to the code.

Review the following code diff {diff} and take the pull request title and description into account when writing the response.
```'''
prompt = create_prompt()
# Generate review comments using the latest OpenAI API method
response = client.chat.completions.create(
    model="gpt-4o",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": {prompt}}
    ]
)
comments = response.choices[0].message.content

# Post the comments to the pull request
pr_comments_url = f"https://api.github.com/repos/{repo_full_name}/issues/{pr_number}/comments"
payload = {'body': comments}
headers = {'Authorization': f'token {github_token}', 'Accept': 'application/vnd.github.v3+json'}
response = requests.post(pr_comments_url, headers=headers, json=payload)

# Debugging information
print(f"Posting comments to {pr_comments_url}")
print(f"Payload: {json.dumps(payload)}")
print(f"Response status code: {response.status_code}")
print(f"Response: {response.json()}")

# Check if the request was successful
response.raise_for_status()
print("Comment posted successfully.")

# Output the comments
# print(comments)


