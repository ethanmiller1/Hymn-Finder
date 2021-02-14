if __name__ == '__main__':
    import webbrowser
    import urllib3

    inputFileName = 'you.list'

    def ReadMultipleDataFrom(thisTextFile, thisPattern):
        inputData = []
        file = open(thisTextFile, "r")
        for iLine in file:
            if iLine.startswith(thisPattern):
                iLine = iLine.rstrip()
                # print iLine
            if ('v=') in iLine: # https://www.youtube.com/watch?v=aBcDeFGH
                iLink = iLine.split('v=')[1]
                inputData.append(iLink)
            if ('be/') in iLine: # https://youtu.be/aBcDeFGH
                iLink =  iLine.split('be/')[1]
                inputData.append(iLink)
        return inputData

    videoLinks =  ReadMultipleDataFrom(inputFileName, "https")
    # print videoLinks

    listOfVideos = "http://www.youtube.com/watch_videos?video_ids=" + ','.join(videoLinks)
    # print listOfVideos

    http = urllib3.PoolManager()
    response = http.request('GET', listOfVideos)
    playListLink = response.geturl()
    # print playListLink

    playListLink = playListLink.split('list=')[1]
    # print playListLink

    playListURL = "https://www.youtube.com/playlist?list="+playListLink+"&disable_polymer=true"
    print(playListURL)
    webbrowser.open(playListURL)