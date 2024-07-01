import os
import json
import requests
from openai import OpenAI

# Set up OpenAI API key
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

# Read the event payload to get the PR diff
with open(os.getenv("GITHUB_EVENT_PATH")) as f:
    event = json.load(f)

# Example to extract the diff URL from the pull request event
diff_url = event['pull_request']['diff_url']

# Fetch the diff content from the URL
diff_response = requests.get(diff_url)
diff = diff_response.text
print("diffs>"diff)
# Generate review comments using the latest OpenAI API method
response = client.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": f"Review the following code changes and provide comments:\n\n{diff}"}
    ]
)

# Output the comments
comments = response.choices[0].message.content
print(comments)
