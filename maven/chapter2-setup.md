- How to setup Maven?
  - Download the compressed archive
  - Extract it and add the `bin/` directory to PATH

- How to test Maven installation?
  - Run `mvn -v` to get the version number of installed Maven

- How to get help regarding commands in Maven?
  - Run `mvn -h`

- Where are user-specific configurations provided to Maven?
  - Using `settings.xml` file
  - Maven looks for this file at both
    - in the `conf/` directory of Maven's installation (global setting)
    - in the `.m2/` directory in user's home directory (user setting - takes
    precendence over global setting)

- What are settings of Maven that can be tweaked?
  - `localRepository` - Location where local copies of plugins and dependencies
  are stored\
  - `servers` - In case Maven needs to interact with Git servers, build servers,
  and remote repository servers (you can specify the credentials needed to
  connect to the servers as well)
  - `mirrors` - Specify alternate locations for downloading dependencies from
  remote repositories (useful in enterprise where the internal network mirrors a
  public repository for security reasons)
  - `proxies` - Contain HTTP proxy information needed to connect to the internet

- How to setup a proxy?
  - To allow Maven to install dependencies from the internet without being
  interfered by enterprise's HTTP proxies to restrict access to the Internet
```xml
<proxies>
  <proxy>
    <id>companyProxy</id>
    <active>true</active>
    <protocol>http</protocol>
    <host>proxy.company.com</proxy>
    <port>8080</port>
    <username>proxyusername</username>
    <password>proxypassword</password>
  </proxy>
</proxies>
```

- How to secure passwords written in `settings.xml` file?
  - Encrypt the password (in plain text) using a master password
  - Run `mvn -emp <master_password>` to get an encrypted version of the password
  and paste the output in `.m2/settings-security.xml` file
```xml
<settingsSecurity>
  <master>{...}</master>
</settingsSecurity>
```
  - Run `mvn -ep <proxypassword>` to get the encrypted version of the password
  and replace the clear text password in `settings.xml` with this

