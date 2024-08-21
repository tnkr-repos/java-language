# INDEX

- Introduction
- Ways to make an object eligble for Garbage Collection
- Methods for requesting JVM to run Garbage Collector
- Finalization

# INTRODUCTION

- In old languages like C++ programmer is responsible to create new object, and
to destroy useless objects. Usually programmers take care while creating objects
and neglect destruction of useless objects. Because of this negligence memory
may not be available for creation of new objects (total memory is filled with
useless objects only) and total application will be down with memory problems.
Hence OutOfMemoryError is very common problem in old languages like C++
- But in Java, programmer is responsible only for creation of object, and not to
destroy useless object. Garbage collector destroys useless objects, and always
runs in the background (daemon thread). Due to this the chance of Java programs
failing due to memory problems becomes very low

# WAYS TO MAKE AN OBJECT ELLIGIBLE FOR GARBAGE COLLECTION

- Even though programmer is not responsible to destroy useless objects it is
highly recommended to make a variable elligible for garbage collection if it is
no longer required
- An object is said to be elligible for garbage collection iff it does not
contain any reference variable
- The following are various ways to make an object elligible:

## NULLIFYING THE REFERENCE VARIABLE 
- If an object is no longer required then assign `null` to all its reference
variables. Then the object becomes elligible for garbage collection
```java
Student s1 = new Student();
Student s2 = new Student(); 
// no object eligible for gc
...
s1 = null;  // 1 object becomes eligible for gc
...
s2 = null;  // 2 object become elligible for gc
```

## REASSIGNING THE REFERENCE VARIABLE 
- If an object is no longer required, then reassign its reference variable to
some other object. Then old object by default eligible for garbage collection
```java
Student s1 = new Student();
Student s2 = new Student();
// no object eligible for gc
...
s1 = new Student();  // 1 object becomes eligible for gc
...
s2 = s1;  // 2 object become elligible for gc
```

## OBJECTS CREATED INSIDE A METHOD
- The objects which are created inside a method are by default eligible for
garbage collection once method completes
```java
class Test {
    public static void main(String[] args) {
        m1();
        // 2 object become elligible for bc
    }
    public static void m1() {
        Student s1 = new Student();
        Student s2 = new Student();
    }
}
```
```java
class Test {
    public static void main(String[] args) {
        Student s = m1();  // 1 object becomes eligible for gc
        // if we hold reference then instead of 2, only 1 object is gc
    }
    public static void m1() {
        Student s1 = new Student();
        Student s2 = new Student();
        return s1;
    }
}
```
```java
class Test {
    static Student s;
    public static void main(String[] args) {
        m1();  // 1 object becomes eligible for gc
    }
    public static void m1() {
        s = new Student();
        Student s1 = new Student();
    }
}
```

## ISLAND OF ISOLATION

```java
class Test {
    Test i;
    public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test();
        Test t3 = new Test();
        t1.i = t2;
        t2.i = t3;
        t3.i = t1;
        // no object eligible for gc
        t1 = null;
        t2 = null;
        t3 = null;
        // 3 object become eligible for bc
    }
}
```
- If an object doesn't contain any reference variable then by default it is
eligible for garbage collection
- If an object contains reference variables then also it might be eligible for
garbage collection (island of isolation - all references should be internal
references)

# WAYS FOR REQUESTING JVM TO RUN GARBAGE COLLECTOR

- Once we make an object eligible for garbage collection. It may not be
destroyed immediately by garbage collector. Whenever JVM runs GC, then only the
objects will be destroyed. But when exactly JVM runs GC we can't expect, and it
varies across JVMs. Instead of waiting until JVM runs GC we can request JVM to 
run GC programmatically, but whether JVM accepts our request or not there is no
guarantee. But most of the times JVM accepts our request
- The following are two ways for requesting JVM to run GC:

## BY USING SYSTEM CLASS

- System class contains a static method `gc()` for this purpose - `System.gc()`

## BY USING RUNTIME CLASS

- Java application can communicate with JVM by using Runtime object
- Runtime class present in java.lang package and it is a singleton class. We can
create Runtime object by using Runtime.getRuntime() method
```java
Runtime r = Runtime.getRuntime();
```
- Once we got Runtime object we can call the following methods on that object
    - totalMemory() - Returns number of bytes of total memory present in Heap
    - freeMemory() - Return number of bytes of memory left in Heap
    - gc() - For requesting JVM to run GC

- gc() method present in System class is a static method whereas gc() method
present in Runtime class is instance method
- It is convinient to use System class gc() method when compared with Runtime
class gc() method. With respect to performance it is highly recommended to use
Runtime class gc() method when compared with System class gc() method because
the later internally calls the former

# FINALIZATION

- Just before destroying an object GC calls finalize() method to perform cleanup
activities. Once finalize method completes automatically GC destroys the object
- Finalize method present in object class with the following declaration
`protected void finalize() throws Throwable {}`
- We can override finalize() method in our class to define our own cleanup
activities

## CASE 1

- Just before destroying an object GC calls finalize() method on the object
eligible for garbage collection. Then the corresponding class finalize() method
will be executed. For eg. if String object is eligible for garbage collection,
then String class finalize() method will be executed (which has empty 
implementation), but not Test class finalize() method

## CASE 2

- Based on our requirement we can call finalize() method explicitly. Then it
will be executed just like a normal method call and object won't be destroyed.
But if GC calls finalize() method then object will be destroyed

- ![finalize method](./GarbageCollection.java)

## CASE 3

- If we are calling finalize() method and an exception is raised while executing
it, and if it in uncaught then JVM terminates our program abnormally by raising
that exception. But if GC calls finalize() method and an uncaught exception is
raised while executing it, then JVM ignores that exception and rest of the
program will be continued normally

## CASE 4

- Even though object eligible for garbage collection multiple times but GC calls
finalize() method only once
- ![Finalize Demo](./FinalizeDemo.java)
- In the above program even though object is eligible for garbage collection
twice but GC calls finalize method only once

## CASE 5

- We can't expect exact behavior of GC. It varies across JVMs. Hence for the
following questions we can't provide exact answers
    - When exactly JVM runs GC
    - In which order GC identifies eligible objects
    - In which order GC destroys eligible objects
    - Whether GC destroys all eligible objects or not
    - What is algorithm followed by GC, etc.
- ![GC Demo](./Gc.java)
- Whenever program runs with low memory then JVM runs GC, but we can't expect
exactly at what time
- Most of the GC follow standard algorithm (Mark and Sweep Algorithm). It does
not mean every GC follows the same algorithm

## CASE 6

- The objects which are not used in our program and which are not eligible for
garbage collection. Such type of useless objects are called memory leaks
- In our program if memory leaks are present then the program will be terminated
by raising OutOfMemoryError
- Hence if an object is no longer required it is highly recommended to make that
object eligible for garbage collection
- The following are third party memory management tools to identify memory leaks
    - HP OVO
    - HP JMeter
    - JProbe
    - Patrol
    - IBM Tivoli
