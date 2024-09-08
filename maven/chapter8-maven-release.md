- What is the release plugin in Maven?
  - Allows automation of steps involved in releasing software
  - It interacts with the `release` branch of the project

- How is a project released?
  - Verify that there are no uncommitted changes on the local machine
  - Remove SNAPSHOT from the version in the `pom.xml` file
  - Make sure that project is not using any SNAPSHOT dependencies
  - Check in the modified `pom.xml` file to your source control
  - Create a source control tag of the source code
  - Build a new version of the artifact and deploy it to a repository manager
  - Increment the version in the `pom.xml` file and prepare for next cycle

- How is an artifact release using Maven's release plugin?
  - It has two goals - `prepare` and `clean`

- What is the `prepare` goal?
  - `prepare` goal - Prepares a project for release - Run `mvn release:prepare`
  - Checks if the version in the `pom.xml` file has SNASHOT in it
  - Checks if there are any uncommitted changes
  - Checks the `pom.xml` file to see if there are any SNAPSHOT dependencies (to prevent failures in release)
  - Asks user for next release version and next development version
  - Generates the release `pom` file
  - Commits the release of the `pom` file to the SCM
  - Creates a release tag for the code in the SCM
  - Updates the `pom` file for next development cycle
  - Deletes the `pom` file generated for the release
  - Submits the `pom.xml` file with the development version
  - Completes the prepare phase of the release

- What is the `clean` goal of Maven's release plugin?
  - Clean up temporary files created as part of release execution
  - Must be used only when the `prepare` goal fails

- What is the `perform` goal of Maven's release plugin?
  - Run `mvn release:perform`
  - Responsible for checking out code from the newly created tag and builds and deploys the released code into the remote repository
  - Validates that a `prepare` phase has been executed prior to running the `perform` goal
  - Checks out the released code from the SCM tag
  - Executes the goals associated with `perform` (default is deploy)

- How to configure Maven to communicate with GitHub?
  - Provide GitHub credentials in the `settings.xml` file
```xml
<servers>
  <server>
    <id>github</id>
    <username>github_username</username>
    <password>github_password</password>
  </server>
</servers>
```
