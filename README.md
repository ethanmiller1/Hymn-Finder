# Sermon Finder

## Get started with YouTube API inside Python

Ensure you have [Python](https://www.python.org/downloads/windows/ "Python Releases for Windows") installed, then:

``` bash
# Activate command prompt if using powershell
$ cmd
# Create a virtual environment
$ python -m venv venv
# Activate your virtual environment
$ cd venv/Scripts && activate && cd ../..
# Install dependencies to venv
$ pip install -r requirements.txt
# Run the program
$ python src/main/resources/ytsearch.py
```

Videos:
* [Python YouTube API Tutorial](https://www.youtube.com/watch?v=th5_9woFJmk)
* [Hiding Passwords and Secret Keys in Environment Variables](https://www.youtube.com/watch?v=IolxqkL7cD8)

## Setup Virtual Environment in IntelliJ

File > Project Structure > SDKs > Add New SDK > `...\venv\Scripts\python.exe`

# Notes

Hyper
Text
Transfer
Protocol
(http)
secure
(https)
(interviewee missed this question)

* TCP Network (exchange bytes with one thing, peer to peer)
* UDP (now that I have a connection, how do I request something, and you give me something back?)

All about request and response.

Client (Browser, JSOUP, etc) sends request to server, and what comes back is a response.

### Client Request
* URI (Universal Resource Identifier)
* URI (Universal Resource Locator)
* Verb (GET, POST, UPDATE (PATCH), PUT (Replace), DELETE, OPTIONS)
* Headers
* Body (optional)

Idempotent (If I do the things, and then I do the thing again, I should get the same results)

### Server Response
* Headers
* Body
* Status Code

* 200s - success
* 300s - not here/moved
* 400s - your fault
* 500s - my fault