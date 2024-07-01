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

diff_response = fetch_pr_diff(repo_full_name, pr_number, github_token)

# Fetch the diff content from the URL
print(f"diff response : {diff_response}")
diff = diff_response.text
print(f"diffs>{diff}")
# Generate review comments using the latest OpenAI API method
response = client.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": f"Review the following code changes and provide comments:\n\n{diff}"}
    ]
)
comments = response.choices[0].message.content

# Output the comments
print(comments)

# getting the repo and diff through gitHub api
def fetch_pr_diff(repo, pr, token):
    pr_diff_url = f"https://api.github.com/repos/{repo}/pulls/{pr}"
    print(f"git info:{repo},{pr},{token}")
    headers = {'Authorization': f'token {token}', 'Accept': 'application/vnd.github.v3.diff'}
    diff_response = requests.get(pr_diff_url, headers=headers)
    diff_response.raise_for_status()
    return diff_response







