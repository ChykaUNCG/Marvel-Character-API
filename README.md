# Marvel Character API

The Marvel Character API is a Spring Boot REST API for storing and managing Marvel characters. The application uses PostgreSQL through Neon for persistent data storage and is deployed on Render using Docker.

## Character Entity

Each Marvel character contains the following attributes:

| Attribute | Type | Rules |
|---|---|---|
| characterId | Long | Unique and automatically generated |
| name | String | Required and cannot be blank |
| description | String | Required and cannot be blank |
| universe | String | Stores the character's universe |
| role | String | Stores the character's role |

## API Endpoints

Base path:

`/api/characters`

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/api/characters` | Get all characters | 200 OK |
| GET | `/api/characters/{id}` | Get a character by ID | 200 OK |
| POST | `/api/characters` | Create a new character | 201 Created |
| PUT | `/api/characters/{id}` | Update an existing character | 200 OK |
| DELETE | `/api/characters/{id}` | Delete a character | 204 No Content |
| GET | `/api/characters?universe=Marvel` | Filter characters by universe | 200 OK |
| GET | `/api/characters?name=spider` | Search characters by partial name, case-insensitive | 200 OK |

A request for an ID that does not exist returns `404 Not Found`.

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

## Deployed API

The API is deployed on Render:

https://marvel-character-api.onrender.com/api/characters

Because this application uses Render's free instance, the service may spin down after periods of inactivity. The first request after inactivity may take up to approximately one minute while the service starts again.

## Technologies Used

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL
- Neon
- Docker
- Render