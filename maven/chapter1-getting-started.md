- What are the features of Maven?
  - Convention over Configuration - Maven emphasises on sensible defaults to
  reduce the number of decisions to be made. It saves time and also results in a
  simpler end product
  - Archetypes - Predefined project templates which contain all of the folder
  and files, that can be used to generate new projects
  - Plugins - Used to augment and customise Maven's functionality
  - Declarative Dependency Management - Instead of downloading dependencies
  manually and keeping track of their versions, we can just declare the project
  dependencies in `pom.xml` file, and Maven downloads them automatically
  - Standardized directory structure - Maven suggests where different parts of a
  project (source code, test code, configuration files) should reside

- What are the components of Maven?
  - Maven SCM - Provides a common API to interact with several source control
  systems
  - Maven Wagon - Allows Maven to interact with different transfer protocols so
  as to retrieve dependencies
  - Maven Doxia - Content generation framework used to generate static / dynamic
  content for documentation and reporting purposes