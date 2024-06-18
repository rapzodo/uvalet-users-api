<<<<<<< migrating-openai
import os
import json
import requests
from openai import OpenAI

client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

# Set up OpenAI API key
=======
# import openai
import os
import json
import requests
import thinktankclient

# Set up OpenAI API key
# openai.api_key = os.getenv("OPENAI_API_KEY")
>>>>>>> master

# Read the event payload to get the PR diff
with open(os.getenv("GITHUB_EVENT_PATH")) as f:
    event = json.load(f)

# Example to extract the diff URL from the pull request event
diff_url = event['pull_request']['diff_url']

# Fetch the diff content from the URL
diff_response = requests.get(diff_url)
diff = diff_response.text

<<<<<<< migrating-openai
# Generate review comments using the latest OpenAI API method
response = client.chat.completions.create(model="gpt-3.5-turbo",
messages=[
    {"role": "system", "content": "You are a helpful assistant."},
    {"role": "user", "content": f"Review the following code changes and provide comments:\n\n{diff}"}
])
=======
#calling thinktank
token = "eyJraWQiOiJFLlBSRC4wMUhXUkI3UzZSM0E4ViIsImFsZyI6IlJTMjU2IiwidHlwIjoiSldUIn0.eyJzdWIiOiJaMDA2TFRMIiwiaXNzIjoiaHR0cHM6Ly9vYXV0aC5pYW0udGFyZ2V0LmNvbSIsImV4cCI6MTcxODY4NDQ1NCwiaWF0IjoxNzE4NjU1NjU0LCJqdGkiOiJFTlQuM2MzMDM4MWJhZGFhNDlmZmJjNjRkYzNiOWNjYzIzOGUtbCIsInNreSI6IkUuUFJELjAxSFdSQjdTNlIzQThWIiwic3V0IjoiUiIsImVpZCI6IiIsInNjbyI6ImVtYWlsLG9wZW5pZCxwcm9maWxlIiwiY2xpIjoiY2hhdHRndF9wcm9kX2ltIiwiYXNsIjoiTCIsInVzZXJuYW1lIjoiWjAwNkxUTCIsImtpZCI6IkUuUFJELjAxSFdSQjdTNlIzQThWIiwic2NvcGUiOlsiZW1haWwiLCJvcGVuaWQiLCJwcm9maWxlIl0sImFhbCI6IjEiLCJjbGllbnRfaWQiOiJjaGF0dGd0X3Byb2RfaW0iLCJkaXIiOiJjb3JwIiwibm9uY2UiOiIxMjM0In0.ATcNoPBfZQgGEBNpGXAToMJ2219u-RkpuPJHZe_D1j7GJhwT-_mpCmmMsMBDzoSd4N_PjkWYZEIbR2wrxI4Tn5get10pyW8EsBEfEhkBSnCGj5R_31GQmw_jRHlRF5-c6iSiERY3iS0cDM51Ij-KBtvihZ21jCwcuUWgt32t7nmONrfSRN7E5-kESHDZNkk9F4dWvMzzckFUtzj0xaMzgfBY30b_0Ic3RpCnh0TYYDdcygeLvzF_doRTRPUgixi2Z7bmece_qe1xT8R-lUfvtcqzAwUZ70rQeDROyLhMkQQk6Ye3pCJ-xA4FsS3AljkL0kM62FyU36gkUIpIpzd79Q"
>>>>>>> master

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
