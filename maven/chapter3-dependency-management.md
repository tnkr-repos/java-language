- How are dependencies added manually to a project?
  - Download the JAR file of the dependency from its download page
  - Put the JAR file in the project's `lib/` directory or add the JAR file to
  the `CLASSPATH` of the project

- What are the problems with such an approach?
  - The JAR file can have its own dependencies, which we would need to manually
  download and add to the project as well
  - During upgradation the whole process will have to be re-done
  - JAR files will need to be added to the source control along with the source
  code so that projects can be built on other computers. This increases the
  project's size, and checkout and build time

- How does Maven provide a better approach for dependency management?
  - Maven provides a declarative dependency management approach where we specify
  the project's dependencies in `pom.xml` file, and Maven will automatically
  download those dependencies from remote repositories (for eg. Maven Central),
  provided they are not present in its local repository (otherwise those will be
  used) and hand them over to your project

- What are the problems introduced with using Maven for dependency management?
  - Sharing enterprise's artifacts still cannot be done as we would not want to
  publish them on remote repositories
  - Having to download dependencies from the internet can have its own legal and
  security concerns
  - Under heavy load on remote repositories the download speeds are reduced, and
  this will have a negative impact on Maven builds

- What is the role of an Internal Repository Manager?
  - Acts as a proxy to a remote repository
  - This allows us to cache artifacts from remote repositories resulting in
  faster artifact downloads and build performance improvements
  - Regulation of artifacts is possible as the repository manager is under the
  enterprise network and we have full control over it
  - We can push enterprise's artifacts onto the repository manager in order to
  share amongst the organisation
  - Examples - Nexus

- What is a dependency's scope?
  - Certain dependencies are required only at particular stages of development.
  For eg. JUnit is only needed during testing, and doesn't need to be bundled
  with the final production archive; MySQL database driver is only needed when
  running the application inside a container such as Tomcat, and not during
  compilation or testing
  - Maven allows us to specify when and where we need a particular dependency

- What are transitive dependencies?
  - Dependencies of a direct dependency which is present in `pom.xml` file of 
  project

- How does Maven handle transitive dependencies?
  - Handling transitive dependencies is difficult as direct dependencies might
  pull different version of the same JAR file
  - Maven uses dependency mediation to resolve version conflicts. It pulls the
  dependency that is closest to the project in the dependency tree. If this
  results in a tie, Maven will use the first-found dependency (and not the
  latest dependency - if needed then this version needs to be added to `pom.xml`
  explicitly)
  - Use the `excludes` tag to exclude a transitive dependency. It takes the
  `groupId` and `artifactId` coordinates of the dependency to be excluded
```xml
<dependencies>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>${junit.version}</version>
    <scope>test</scope>
    <exclusions>
      <exclusion>
        <groupId>org.hamcrest</groupId>
        <artifactId>hamcrest</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
</dependencies>
```

- How to view project dependency tree?
  - Use the `dependency` plugin
  - Run `mvn dependency:tree`

- What are the scopes introduced by Maven?
  - `compile` - Available in `CLASSPATH` in all phases on a project build, test
  and run (default scope)
  - `provided` - Available in `CLASSPATH` only in build and test phases, and do
  not get bundled within the generated artifact
  - `runtime` - Available in `CLASSPATH` in all but the build phase. They get
  bundled in the generated artifact and are available at runtime
  - `test` - Available in `CLASSPATH` in the test phase
  - `system` - Similar to `provided`, the difference being dependencies are not
  retrived from remote repository (instead they are hard-coded path to the file
  system from where dependencies are used)
  - `import` - Applicable only for `.pom` file dependencies. It allows including
  dependency management information from a remote `.pom` file

- How to install dependency imperatively using Maven?
  - Using the `install` plugin
  - This can be useful in cases when you have to have a dependency in order to
  continue with development, but you can't wait for the enterprise to add the
  dependency to the internal repository manager
  - Run `mvn install:install-file` 