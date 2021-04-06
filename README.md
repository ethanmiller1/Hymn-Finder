# Spring Boot

## APIs

To view JSON as parsed, download the [JSON Formatter](https://chrome.google.com/webstore/detail/json-formatter/bcjindcccaagfpapjjmafapmmgkkhgoa/related?hl=en) Chrome extension.

* [http://localhost:8080/api/sermons/list](http://localhost:8080/api/sermons/list)

## Deploy to Heroku

### 1. Connect to JawsDB

* [Add JawsDB (MySQL) Hosted on Heroku](https://youtu.be/ZIYqFl6DOGQ?t=481)

1. From the Heroku Dashboard, navigate to `Resources` > `Find more add-ons`.
1. Setup a remote connection in MySQL Workbench.

![](https://i.ibb.co/BgrhJrG/image.png)

3. Run db_scripts in the new database.

### 1. Use environment variables

#### Java

1. Add a `FilterRegistrationBean` to `WebConfig.java` for Angular to circumvent CORS.
1. Add a prod spring profile in `application.yml` with Username and Password from JawsDB.
   (The Connection String will be automatically set in the `JAWSDB_URL` Environment Variable in Heroku.)
1. Create Procfile (for maven): `web: java $JAVA_OPTS -Dserver.port=$PORT -jar target/*.jar` in the project root.

#### Angular

3. Add `baseUrl: 'http://localhost:8080/'` to `environment.ts'`.
1. Add `baseUrl: 'https://unboundpreaching.herokuapp.com/'` to `environment.prod.ts`.
1. Change all services to use `environment.baseUrl`.
1. Change `outputPath` in `angular.json` to `"outputPath": "../src/main/resources/static",`
1. Run `ng build --prod`.

#### Heroku Settings

1. Navigate to `Settings` > `Reveal Config Vars` and change `SPRING_PROFILES_ACTIVE` to `prod`.
1. Enable dyno formation under `Resources` after deployment.

![](https://i.ibb.co/0FXM9TQ/image.png)

# Angular

## Deploy to Netlify

* [Angular Deployment to Netlify](https://developer.okta.com/blog/2020/05/29/angular-deployment)

```powershell
ng add @netlify-builder/deploy
ng deploy
```

### Continuous Deployment:

`Site Settings > Build & deploy > Continuous Deployment > Build settings > Base directory`: webapp

## Dependencies

Run `npm install` to download project dependencies.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Debugging Angular in IntelliJ

* [Debugging Angular Applications](https://www.youtube.com/watch?v=b2gboBOoc88)


```
# Run > Edit Configurations > Add New Configuration > JavaScript Debugger > Ensure breakpoints are detected when loading scripts
$ npm start
# Debug
```


## New APP

```bash
$ ng new webapp
# Do you want to enforce stricter type checking and stricter bundle budgets in the workspace?
$ n
# Would you like to add Angular routing?
$ n
# Which stylesheet format would you like to use?
$ CSS
```

1. Edit `index.html`
1. Edit `app.component.html`
1. Change favicon

```bash
ng generate class model/sermon
ng generate component components/sermon-list 
ng generate service services/sermon
```

### Components

```bash
ng generate component xyz
```

```bash
ng generate module accounts/accounts --routingrm -r import formsModule @angular/forms
```

Include in declarations in app.module<app-account-list></...>ng generate component account-listng generate component accounts/components/account-list --lint-fix --skip-import
	* Barrel file under components folder.
ng new angular-madlib

ng new angular-unbound-preaching

```material
ng add @angular/material
```

### Add dependency

```bash
ng add _____
```

### [Awwwards](https://www.awwwards.com/) Inspiration

* [leonard-agency](https://leonard.agency/en/) - Preloader, Navbar / Hamburger Menu
* [studio-arte](https://www.studioarte.info/) - Project Selection
* [sa-design](https://www.sa-design.co/projects/dinamica) - Cursor circle
* [anywell-facial-bar](https://anywellfacialbar.com/) - Fixed Navbar
* [the-sleepiest-app](https://www.sleepiest.com/) - App advertisement, Parallax
* [baunfire](https://www.baunfire.com/) - Hamburger Menu
* [fairer-tomorrow](https://fairertomorrow.stanford.edu/) - Music button, full screen, background
* [vobla](https://www.vobla.com/expertise/) - card animations
* [clubhouse](https://app.clubhouse.io/unboundpreaching/stories/space/9/everything) - side icon nav bar, curved header
* [Wall Street Journal](https://css-tricks.com/making-an-audio-waveform-visualizer-with-vanilla-javascript/) - audio player
* [Listening Together](https://listeningtogether.atspotify.com/) - preaching/soul-winning globe
* [Production Portugal](https://productionportugal.com/#/) - landing page animation, play video, transparent CamptonBold font with border
* [Dixie Podcast](https://dixie.secondlinethemes.com/episodes/) - audio player
* [Google Podcast](https://podcasts.google.com/feed/aHR0cHM6Ly9tZWRpYS5yc3MuY29tL3JvZG9maXJvbi9mZWVkLnhtbA?sa=X&ved=2ahUKEwjousnKwuLvAhUtA50JHSfNBcIQ9sEGegQIARAC) - Episode List
* [Disney Plus](https://www.disneyplus.com/home) - Pick Church
* [thetvdb](https://www.thetvdb.com/series/345404-show) - community art contributions

## CSS Reference

* [w3schools](https://www.w3schools.com/css/) 

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

## YouTube API Documentation

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

## Internet Archive API Documentation

* [Developers](https://blog.archive.org/developers/)
* [SearchAPI](https://archive.org/advancedsearch.php#raw)
* [Embedding video](https://archive.readme.io/)
* [Github](https://github.com/internetarchive/heritrix3.git)

### Pastor Anderson's Complete YouTube Channel From Old To New 
* [Part 1 of 11](https://archive.org/details/CompleteYouTubeChannelPart1of11)
* [Part 2 of 11](https://archive.org/details/CompleteYouTubeChannelPart2of11)
* [Part 3 of 11](https://archive.org/details/CompleteYouTubeChannelPart3of11/Pastor+Anderson's+Complete+Channel+Part+3+of+11/011++False+Prophets+are+Loved+of+this+World-GLUxLAy3E-U.mp4)
* [Part 4 of 11](https://archive.org/details/CompleteYouTubeChannelPart4of11)
* [Part 5 of 11](https://archive.org/details/CompleteYouTubeChannelPart5of11)
* [Part 6 of 11](https://archive.org/details/CompleteYouTubeChannelPart6of11)
* [Part 7 of 11](https://archive.org/details/CompleteYouTubeChannelPart7of11)
* [Part 8 of 11](https://archive.org/details/CompleteYouTubeChannelPart8of11)
* [Part 8.5 of 11](https://archive.org/details/CompleteYouTubeChannelPart8.5of11)
* [Part 9 of 11](https://archive.org/details/CompleteYouTubeChannelPart9of11)
* [Part 10 of 11](https://archive.org/details/CompleteYouTubeChannelPart10of11)
* [Part 11 of 11](https://archive.org/details/CompleteYouTubeChannelPart11of11)

## TODO: Upcominig Features

* [Upload Media](https://github.com/googleapis/google-api-python-client/blob/master/docs/media.md)

## Insert Date String in IntelliJ

Install the [Current date generator](https://plugins.jetbrains.com/plugin/9722-current-date-generator) plugin, then 
1. `settings > Other Settings` and insert `'(A) 'yyyy-MM-dd' 'HH:mm' '`
1. `Settings > Keymap > Plug-ins > Current date generator`

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
* URL (Universal Resource Locator)
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