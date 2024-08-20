# INTRODUCTION

## SERIALISATION

- The process of writing state of an object to a file
- It is the process of converting an object from Java Supported form into
either file supported form or network supported form
- Implemented by using FileOutputStream and ObjectOutputStream.writeObject()
- The output file can have any extension because in Java, file IO is based on
Unix operating system where extension is not applicable


## DESERIALISATION

- The process of reading state of an object from a file
- It is the process of converting an object from either file supported form or
network supported form into Java supported form
- Implemented by using FileInputStream and ObjectInputStream.readObject()

- ![Serialisation Example](./SerializeDemo.java)

- We can serialise only Serializable objects (the class should implement
Serializable interface present inside java.io package - It is a market
interface), otherwise we will get runtime exception saying 
`NotSerializableException`

## TRANSIENT KEYWORD

- Only applicable for variables
- At the time of serialization if we don't want to save the value of a
particular variable to meet security constraints then we should declare the
variable as `transient`. While performing serialization JVM ignores original
value of transient variables and saves default value to the file

### TRANSIENT VS STATIC

- `static` variable is not part of object state and hence it won't participate
in serialization. Due to this declaring `static` variable as `transient` is of
no use

### TRANSIENT VS FINAL

- 