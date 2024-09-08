- What are goals in Maven?
  - Each task (compiling source code, running unit tests, packaging artifact) in
  the build process of an application to generate an artifact (JAR or WAR file)
  is represent by a goal in Maven
  - For eg. `compile` goal is used to compile source code and place generated
  compiled class files in `target/classes` - `mvn compiler:compile`

- What are plugins in Maven?
  - Goals in Maven are packaged in plugins
  - Each plugin provides one or more goals
  - For eg. `compiler` pluging provides the `compile` goal

- What is the use of `clean` goal?
  - Remove Maven-generated temporary files and artifacts present in `target`
  - `mvn clean:clean`
  - The `clean` before `:` is the plugin, and `clean` after `:` is the goal

- NOTE - Use the `mvn help:describe -Dplugin=<plugin_name>` to get the goals
present inside a plugin along with a description

- How to modify the behavior of a plugin?
  - In the plugin section of `pom.xml` file
  - For eg. to change the Java version used to compile the code
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.1</version>
      <configuration>
        <source>1.8</source>
        <target>1.8</target>
      </configuration>
    </plugin>
  </plugins>
</build>
```

- What are lifecycles and phases?
  - Goals accomplish one task
  - Mutitple goals are executed in an orderly manner to perform complex tasks
  - These 
