
## To run this project

You will need the following software:

1. At least JDK 11.
2. Gradle 7.4.2

---

## Libraries Used

The following libraries were used on this project.

1. Lombok library used for logging.
2. Used WebFlux for performance
3. Did not use Mapstruct because I ran out of time, same for open api and Swagger documentation

##Running the application

1. Start as a regular SpringBoot application
2. Once is running, test the end point URI: curl -get localhost:8080/gitinfo/octocat

##Design Considerations
1. Service is an interface, with only one Implementation for Github, other implementations could be Gitlab etc
2. Returned created_at defaults to New York time
3. Did have any issues calling Github, so no caching was enabled
4. App configurations can be extended to use Gitlab and other systems


##Tests
1. All are Unit tests, excepting BranchController test which is an integration tests and makes actual calls to GitHub