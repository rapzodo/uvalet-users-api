# import openai
import os
import json
import requests
import thinktankclient

# Set up OpenAI API key
# openai.api_key = os.getenv("OPENAI_API_KEY")

# Read the event payload to get the PR diff
with open(os.getenv("GITHUB_EVENT_PATH")) as f:
    event = json.load(f)

# Example to extract the diff URL from the pull request event
diff_url = event['pull_request']['diff_url']

# Fetch the diff content from the URL
diff_response = requests.get(diff_url)
diff = diff_response.text

#calling thinktank
token = "eyJraWQiOiJFLlBSRC4wMUhXUkI3UzZSM0E4ViIsImFsZyI6IlJTMjU2IiwidHlwIjoiSldUIn0.eyJzdWIiOiJaMDA2TFRMIiwiaXNzIjoiaHR0cHM6Ly9vYXV0aC5pYW0udGFyZ2V0LmNvbSIsImV4cCI6MTcxODc2MzQwMywiaWF0IjoxNzE4NzM0NjAzLCJqdGkiOiJFTlQuMDQ3MTBmNWNkZTcyNGExMDljYTE2Nzg4Y2RhMzNlMzMtbCIsInNreSI6IkUuUFJELjAxSFdSQjdTNlIzQThWIiwic3V0IjoiUiIsImVpZCI6IiIsInNjbyI6ImVtYWlsLG9wZW5pZCxwcm9maWxlIiwiY2xpIjoiY2hhdHRndF9wcm9kX2ltIiwiYXNsIjoiTCIsInVzZXJuYW1lIjoiWjAwNkxUTCIsImtpZCI6IkUuUFJELjAxSFdSQjdTNlIzQThWIiwic2NvcGUiOlsiZW1haWwiLCJvcGVuaWQiLCJwcm9maWxlIl0sImFhbCI6IjEiLCJjbGllbnRfaWQiOiJjaGF0dGd0X3Byb2RfaW0iLCJkaXIiOiJjb3JwIiwibm9uY2UiOiIxMjM0In0.mjfLJXaw18bz66VAer_YWheXTl724-Cs3dmcFa7ZveVhR-iiUuFbHgIw9IKsWL9JkCPV68rj1WBNbsqIQT5qX9zhrINtgNrbY_ZPUBElpcOdJreXiw9SqlgQyMn9IWGDQuLKLLVv3sbxLwst8eI1JnI-mQi0GCRX3mmy9sBf6kBrUz7xYnK4EHEFHTohPaNz7PCxwuUpyznaYpwLcuwm0SHqG7It8e-GYsNoF0dSPdft3P833OCke6uDFFx_k6TF-SCih_U_sYaPwYfuYwTIOdCGrkJwmMXBhkJmcwKyUV51qG54YV2yBVPuxd1RJ6rj74o9iFG_C-YIKxZ3ie7uWg"

# Generate review comments using the latest OpenAI API method
# response = openai.ChatCompletion.create(
#     model="gpt-3.5-turbo",
#     messages=[
#         {"role": "system", "content": "You are a helpful assistant."},
#         {"role": "user", "content": f"Review the following code changes and provide comments:\n\n{diff}"}
#     ]
# )
response = thinktankclient.call_rest_service(token=token,payload="{model=\"gpt-3.5-turbo\",messages=[{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},{\"role\": \"user\", \"content\": f\"Review the following code changes and provide comments:\n\n{diff}\"}]\")}")
# Output the comments
comments = response.choices[0].message.content.strip()
print(comments)
