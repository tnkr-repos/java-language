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

