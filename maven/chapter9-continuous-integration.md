- What is Continuous Integration?
  - Developers integrate changes to their code to a common repository (source
  control system) several times a day
  - Each committed change would result in an automatic build done by the CI
  server (which polls for new code changes) that would compile the code, run
  tests, and generate a new version of the artifact
  - On a successful build the CI server can publish the artifact to a repository
  or a test server
  - Any errors during the build process will be immediately reported to the
  development team, thus allowing developers to catch and resolve integration
  issues early in the development cycle

- What is examples of CI?
  - Jenkins - Open source CI server
  - GitLab
  - GitHub Actions
