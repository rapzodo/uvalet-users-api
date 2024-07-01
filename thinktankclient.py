import requests

def call_rest_service(token, payload):
    # Set the headers for the request
    headers = {
        'Authorization': f'Bearer {token}',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
    url = "https://thinktank.prod.target.com/v1/chat/completions"
    token = "eyJraWQiOiJFLlBSRC4wMUhXUkI3UzZSM0E4ViIsImFsZyI6IlJTMjU2IiwidHlwIjoiSldUIn0.eyJzdWIiOiJaMDA2TFRMIiwiaXNzIjoiaHR0cHM6Ly9vYXV0aC5pYW0udGFyZ2V0LmNvbSIsImV4cCI6MTcxODY4NDQ1NCwiaWF0IjoxNzE4NjU1NjU0LCJqdGkiOiJFTlQuM2MzMDM4MWJhZGFhNDlmZmJjNjRkYzNiOWNjYzIzOGUtbCIsInNreSI6IkUuUFJELjAxSFdSQjdTNlIzQThWIiwic3V0IjoiUiIsImVpZCI6IiIsInNjbyI6ImVtYWlsLG9wZW5pZCxwcm9maWxlIiwiY2xpIjoiY2hhdHRndF9wcm9kX2ltIiwiYXNsIjoiTCIsInVzZXJuYW1lIjoiWjAwNkxUTCIsImtpZCI6IkUuUFJELjAxSFdSQjdTNlIzQThWIiwic2NvcGUiOlsiZW1haWwiLCJvcGVuaWQiLCJwcm9maWxlIl0sImFhbCI6IjEiLCJjbGllbnRfaWQiOiJjaGF0dGd0X3Byb2RfaW0iLCJkaXIiOiJjb3JwIiwibm9uY2UiOiIxMjM0In0.ATcNoPBfZQgGEBNpGXAToMJ2219u-RkpuPJHZe_D1j7GJhwT-_mpCmmMsMBDzoSd4N_PjkWYZEIbR2wrxI4Tn5get10pyW8EsBEfEhkBSnCGj5R_31GQmw_jRHlRF5-c6iSiERY3iS0cDM51Ij-KBtvihZ21jCwcuUWgt32t7nmONrfSRN7E5-kESHDZNkk9F4dWvMzzckFUtzj0xaMzgfBY30b_0Ic3RpCnh0TYYDdcygeLvzF_doRTRPUgixi2Z7bmece_qe1xT8R-lUfvtcqzAwUZ70rQeDROyLhMkQQk6Ye3pCJ-xA4FsS3AljkL0kM62FyU36gkUIpIpzd79Q"
    # Make the request to the REST service
    response = requests.post(url, json=payload, headers=headers)

    # Check if the request was successful
    if response.status_code == 200:
        # Parse the JSON response
        response_data = response.json()
        return response_data
    else:
        # Handle the error
        response.raise_for_status()
