import os
import json
import requests
from openai import OpenAI

# Set up OpenAI API key
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))



with open(os.getenv("GITHUB_EVENT_PATH")) as f:
    event = json.load(f)


github_token = os.getenv("GITHUB_TOKEN")
repo_full_name = os.getenv("GITHUB_REPOSITORY")
# Read the event payload to get the PR diff
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
# Generate review comments using the latest OpenAI API method
response = client.chat.completions.create(
    model="gpt-4o",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": f"Review the following code changes and provide comments:\n\n{diff}"}
    ]
)
comments = response.choices[0].message.content

# Output the comments
print(comments)








