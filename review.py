import os
import json
import requests
from openai import OpenAI

# Set up OpenAI API key
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

github_token = os.getenv("TEMP_GIT_TOKEN")
repo_full_name = os.getenv("GITHUB_REPOSITORY")


with open(os.getenv("GITHUB_EVENT_PATH")) as f:
    event = json.load(f)


# Read the event payload to get the PR diff
pr_number = event['pull_request']['number']

# getting the repo and diff through gitHub api
def fetch_pr_diff(repo, pr, token):
    pr_diff_url = f"https://api.github.com/repos/{repo}/pulls/{pr}"
    print(f"git info:{repo},{pr},{token}")
    headers = {'Authorization': f'token {token}', 'Accept': 'application/vnd.github.v3.diff'}
    diff_response = requests.get(pr_diff_url, headers=headers)
    diff_response.raise_for_status()
    return diff_response

diff_response = fetch_pr_diff(repo_full_name, pr_number, "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")
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








