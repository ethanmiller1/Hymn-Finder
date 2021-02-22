if __name__ == '__main__':
    from googleapiclient.discovery import build
    import os

    # Set Environment Variable in your Windows machine
    API_KEY = os.environ.get('YouTube_API_Key')
    API_NAME = 'youtube'
    API_VERSION = 'v3'

    def findYouTubeVideo(query):
        youtubeService = build(API_NAME, API_VERSION, developerKey=API_KEY)

        request = youtubeService.search().list(
            part='snippet',
            maxResults=25,
            q=query,
            type='video',
            videoDefinition='high'
        )

        response = request.execute()

        print(response)

    findYouTubeVideo('Trinity 101')