# Marvel Character Gallery & API

## Project Description

The Marvel Character Gallery is a Spring Boot web application that allows users to view, search, add, edit, and delete Marvel characters.

The application was originally developed as a REST API and has been expanded to include a web interface using Spring MVC and FreeMarker templates.

The application uses PostgreSQL through Neon for persistent data storage and is deployed on Render using Docker.

## Technologies Used

* Java 25
* Spring Boot
* Spring MVC
* Spring Web
* Spring Data JPA
* Spring Validation
* FreeMarker
* HTML
* CSS
* PostgreSQL
* Neon
* Docker
* Render

## MVC Website Features

The website provides the following functionality:

* View all Marvel characters in a gallery.
* Search for characters by name.
* Filter characters by universe.
* View individual character details.
* Add new characters using a form.
* Edit existing character information.
* Delete characters.
* Display a friendly error page when a character cannot be found.
* Navigate between pages using a shared header and footer.

The website uses FreeMarker templates to dynamically display character information retrieved from the database.

## MVC Website Routes

| Method | Route                     | Description                           |
| ------ | ------------------------- | ------------------------------------- |
| GET    | `/`                       | Display the character gallery         |
| GET    | `/characters/{id}`        | Display character details             |
| GET    | `/about`                  | Display the About page                |
| GET    | `/characters/new`         | Display the Add Character form        |
| POST   | `/characters`             | Save a new character                  |
| GET    | `/characters/{id}/edit`   | Display the Edit Character form       |
| POST   | `/characters/{id}`        | Save changes to an existing character |
| POST   | `/characters/{id}/delete` | Delete a character                    |

### Search and Filtering

Users can search for characters by name or filter characters by universe.

Examples:

* `/?name=spider`
* `/?universe=Marvel`

Character searches are case-insensitive.

## FreeMarker Templates

The website uses the following FreeMarker templates:

| Template              | Purpose                                    |
| --------------------- | ------------------------------------------ |
| `layout.ftlh`         | Shared page layout, navigation, and footer |
| `index.ftlh`          | Character gallery and search               |
| `details.ftlh`        | Individual character details               |
| `about.ftlh`          | About page                                 |
| `character-form.ftlh` | Add and edit character forms               |
| `error.ftlh`          | Friendly character-not-found page          |

The application uses a shared FreeMarker layout to maintain consistent styling across pages.

CSS files and character images are stored in the `src/main/resources/static` directory.

## Character Entity

Each Marvel character contains the following attributes:

| Attribute   | Type   | Rules                              |
| ----------- | ------ | ---------------------------------- |
| characterId | Long   | Unique and automatically generated |
| name        | String | Required and cannot be blank       |
| description | String | Required and cannot be blank       |
| universe    | String | Stores the character's universe    |
| role        | String | Stores the character's role        |

## REST API Endpoints

The original REST API remains available.

Base path: `/api/characters`

| Method | Endpoint                          | Description                       | Success Status |
| ------ | --------------------------------- | --------------------------------- | -------------- |
| GET    | `/api/characters`                 | Get all characters                | 200 OK         |
| GET    | `/api/characters/{id}`            | Get a character by ID             | 200 OK         |
| POST   | `/api/characters`                 | Create a new character            | 201 Created    |
| PUT    | `/api/characters/{id}`            | Update an existing character      | 200 OK         |
| DELETE | `/api/characters/{id}`            | Delete a character                | 204 No Content |
| GET    | `/api/characters?universe=Marvel` | Filter characters by universe     | 200 OK         |
| GET    | `/api/characters?name=spider`     | Search characters by partial name | 200 OK         |

A REST API request for a character ID that does not exist returns `404 Not Found`.

POST or PUT requests with a missing or blank `name` or `description` return `400 Bad Request`.

## Example POST Request

```json
{
  "name": "Spider-Man",
  "description": "A superhero with spider-like abilities.",
  "universe": "Marvel",
  "role": "Hero"
}
```

## Example PUT Request

```json
{
  "name": "Spider-Man",
  "description": "A Marvel superhero with spider-like powers and agility.",
  "universe": "Marvel",
  "role": "Avenger"
}
```

## Application Architecture

The application separates REST API functionality from MVC website functionality.

**MarvelCharacterController.java**

* Handles REST API requests.
* Uses `@RestController`.
* Provides JSON responses.

**MarvelCharacterPageController.java**

* Handles website requests.
* Uses `@Controller`.
* Returns FreeMarker templates.

**MarvelCharacterService.java**

* Contains the application's business logic.
* Provides character operations for both controllers.

**MarvelCharacterRepository.java**

* Handles database operations using Spring Data JPA.

**MarvelCharacter.java**

* Defines the character entity and its attributes.

Both controllers use the same service and database.

## Running the Application Locally

1. Clone the GitHub repository.
2. Open the project in VS Code.
3. Configure the PostgreSQL database connection.
4. Run the Spring Boot application.
5. Open the application in a web browser.

Local website:

`http://localhost:8080/`

Local REST API:

`http://localhost:8080/api/characters`

## Deployment

The application is deployed on Render.

Deployed application:

https://marvel-character-api.onrender.com/

REST API:

https://marvel-character-api.onrender.com/api/characters

The application uses Neon PostgreSQL for persistent data storage.

Because Render's free instance may spin down after periods of inactivity, the first request may take approximately one minute to load.

## Assignment 4 — FreeMarker MVC

This assignment expands the original Marvel Character REST API by implementing a server-rendered website using Spring MVC and FreeMarker.

The project demonstrates:

* Separation of REST and MVC controllers.
* Dynamic HTML rendering with FreeMarker.
* Reusable page layouts.
* Database-backed character management.
* Search and filtering.
* Form handling for creating and editing characters.
* Post/Redirect/Get after form submissions.
* Friendly error handling.
* Integration of an MVC website with an existing REST API.
