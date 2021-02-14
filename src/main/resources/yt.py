if __name__ == '__main__':
    from googleapiclient.discovery import build
    import os

    # Set Environment Variable in your Windows machine
    api_key = os.environ.get('YouTube_API_Key')

    youtube = build('youtube', 'v3', developerKey=api_key)

    request = youtube.channels().list(
        part='statistics',
        forUsername='schafer5'
    )

    response = request.execute()

    print(response)
