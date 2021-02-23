# Angular

## Dependencies

Run `npm install` to download project dependencies.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## New APP

```bash
$ ng new webapp
# Do you want to enforce stricter type checking and stricter bundle budgets in the workspace?
$ y
# Would you like to add Angular routing?
$ n
# Which stylesheet format would you like to use?
$ CSS
```

1. Edit `index.html`
1. Edit `app.component.html`
1. Change favicon

```bash
ng generate components/component sermon-list
```

### Components

```bash
ng generate component xyz
```

```bash
ng generate module accounts/accounts --routingrm -r import formsModule @angular/forms
```

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

### Setup Virtual Environment in IntelliJ

File > Project Structure > SDKs > Add New SDK > `...\venv\Scripts\python.exe`

## Create Local MySQL Database

Install MySQL and MySQL Workbench

``` bash
choco install -y mysql
choco install -y mysql.workbench
```

#### Ensure MySQL Service is running.

![](https://i.ibb.co/ccZ0F7N/image.png)

#### Setup New Connection > Leave Username as root and Password empty.

![](https://i.ibb.co/P9tbr3G/image.png)

#### Select the "Schemas" tab in the Navigator, right-click and select "Create Schema..."

![](https://i.ibb.co/jg9HzKj/image.png)

Name it "sermondb". Alternatively simply run the following query and then refresh the schemas.

```sql
create schema `sermondb` ;
```

* [Hibernate-CRUD-App](https://github.com/ethanimproving/Hibernate-CRUD-App)
* [Movie API Drill](https://github.com/ethanimproving/mad/blob/master/src/main/java/org/improving/mad/entity/Movie.java)
* [Bootcamp Assessment Challenge](https://github.com/ethanimproving/Bootcamp-Assessment-Challenge/tree/master/src/main)

## Client Secrets

```bash
git update-index --skip-worktree src/main/resources/client_secret.txt
```

## Documentation

* [Google Python API Client](https://github.com/googleapis/google-api-python-client)
    * [YouTube Data API v3](http://googleapis.github.io/google-api-python-client/docs/dyn/youtube_v3.html)
    * [Search Sandbox](https://developers.google.com/youtube/v3/docs/search/list?apix=true&apix_params=%7B%22part%22%3A%5B%22snippet%22%5D%2C%22maxResults%22%3A2%2C%22q%22%3A%22Trinity%20101%22%2C%22type%22%3A%5B%22video%22%5D%2C%22videoDefinition%22%3A%22high%22%7D)
* [Google Java API Client Services](https://github.com/googleapis/google-api-java-client-services)
    * [YouTube Data API v3 Client Library](https://github.com/googleapis/google-api-java-client-services/tree/master/clients/google-api-services-youtube/v3)
* [YouTube API](https://developers.google.com/youtube/v3)
* [Google Developers Console](https://console.developers.google.com/)

## Videos:
* [Python YouTube API Tutorial](https://www.youtube.com/watch?v=th5_9woFJmk)
* [Hiding Passwords and Secret Keys in Environment Variables](https://www.youtube.com/watch?v=IolxqkL7cD8)
* [Extract YouTube Video Details in Python](https://www.youtube.com/watch?v=i_5xPDX-erE)

## TODO: Upcominig Features

* [Upload Media](https://github.com/googleapis/google-api-python-client/blob/master/docs/media.md)

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