- What are Maven archetypes?
  - Project templates that allow users to generate new projects
  
- Why use Maven archetypes?
  - No needs to generate the folders and create `pom.xml` from scratch
  - Enfources consistency beyond Maven's standard directory structure

- How to use Maven in-built archetypes?
  - Maven provides archetype plugin with goals to create new archetypes and
  generate projects from existing archetypes
  - Run `mvn archetype:generate` to view and select an archetype for use
  - For eg. `maven-archetype-webapp` is used to generate a web application -
  `mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp`, which
  provides a minimal `pom.xml` and a directory structure

- What are some embedded web servers?
  - Tomcat, Jetty

- How to create an Archetype?
  - If using a previous project to get a template, remove the `target` directory
  and other resources (IDE specific files)
  - Create the folder structure of choice
  - Run `mvn archetype:create-from-project` to create archetype at location
  `target/generated-sources/archetype`
  - Move the newly created archtetype into the new project directory (move the
  `src` directory and `pom.xml` file)
  - Run `mvn clean install` inside the base archetype directory

- How to use a generated archetype?
  - Install the archetype to be used
  - Run `mvn archetype:generate -DarchetypeCatalog=local`
  -
