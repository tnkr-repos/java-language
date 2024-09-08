- How to generate documentation using Maven?
  - Using `site` lifecycle provided by Maven
  - Run `mvn clean site` to use Maven's site plugin to generate project's
  documentation directory `target/site` containing html, css and images

- What are the various elements used for documentation purposes?
  - Use `<description>` to provide a description of the project
  - `<mailingList>` - Different mailing lists associated with the project
  - `<license>` - Includes the project's license
```xml
<!-- pom.xml -->
<name>Getting Started with Maven</name>
<url>https://apress.com</url>
<description>
  This project acts as a starter project for the "Introducing Maven" book
</description>
<mailingLists>
  <mailingList>
    <name>GSWM Developer List</name>
    <subscribe>gswm-dev-subscribe@press.com</subscribe>
    <unsubscribe>gswm-dev-unsubscribe@press.com</unsubscribe>
    <post>developer@press.com</post>
  </mailingList>
</mailingLists>
<licenses>
  <license>
    <name>Apache License, Version 2.0</name>
    <url>https://www.apache.org/license/LICENSE-2.0.txt</url>
  </license>
</licenses>
```

- What is the preferred technique to generate documentation for large projects?
  - `pom.xml` file can become bloated and hard-to-maintain if all information
  was present inside of it. Also branding changes have to be made rather than
  the default Maven styling
  - 
