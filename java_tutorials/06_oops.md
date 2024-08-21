# DATA HIDING

- Outside person can't access our internal data directly
- Our internal data should not go out directly
- After authentication outside person can access our internal data
- For e.g. after providing proper username and password we are able to access our
  Gmail inbox information
- Even though we are valid customer of the bank we are able to access our
  account information, but not that of others'
- By declaring data member as private we can achieve data hiding
```java
public class Account {
    private double balance;
    public double getBalance() {
        // authentication
        return balance;
    }
}
```
- The main advantage is security
- It is highly recommended to declare data member as `private`

# ABSTRACTION

- Hiding internal implementation and highlighting the set of services which we
  are offering
- For e.g. through bank ATM GUI Screen bank highlights the set of services which
  they are offer without broadcasting internal implementation
- The main advantages are security, modularity in internal system improves
  maintainability of application
- By using interfaces and abstract classes we can implement abstraction

# ENCAPSULATION

- The process of binding data and corresponding methods into a single unit
- If any component follows data hiding and abstraction then such type of
  component is encapsulated component (Encapsulation = Data Hiding + Abstraction)
- The main advantages of encapsulation are same as that of data hiding and
  abstraction
- The main disadvantage of encapsulation is increase in length of code and slows
  down execution speed

## TIGHTLY ENCAPSULATED CLASS

- Every data member of the class is declared as `private`
- Irrespective of whether the class contains corresponding getter and setter
  methods, and whether these methods are declared as `public` or not
- If the parent class is not tightly encapsulated, then no child class can be
  tightly encapsulated
```java
class A {
    int x = 10;
}
class B extends A {
    private int y = 20;
}
class C extends B {
    private int z = 30;
}
```

# IS-A RELATIONSHIP

- It is also known as inheritance
- The main advantage of is-a relationship is code reusability
- By using `extends` we can implement is-a relationship
- Conclusions:
	- Whatever methods parent class has, are by default available to the child,
	  and hence using child reference we can call both parent and child class
	  methods
	- Whatever methods child has are not by default available to the parent,
	  and hence using parent reference we cannot call child class specific methods
	- Parent reference can be used to hold child object but using that reference
	  we can call parent specific methods
	- Child reference cannot be used to hold parent object
```java
class P {
    public void m1() {
        System.out.println("Parent");
    }
}
class C extends P {
    public void m2() {
        System.out.println("Child");
    }
}
class Test {
    public static void main(String[] args) {
        P p = new P();
        p.m1();
        p.m2();     // invalid - Cannot find symbol m2() in P

        C c = new C();
        c.m1();
        c.m2();

        P p1 = new C();
        p1.m1();
        p1.m2();    // invalid - Cannot find symbol m2() in P

        C c1 = new P();     // invalid - incompatible types
    }
}
```
- The most common methods which are applicable for any type of child should be
  defined in parent class
- The specific methods which are applicable for a particular child should be
  defined in the respective child class

## JAVA API

- For e.g. total Java API is implemented based on inheritance concept. The most
  common methods which are applicable for any Java object are defined in `Object`
  class and hence every class in Java is a child class of `Object` either directly
  or indirectly. So `Object` class methods are by default available to every Java
  class without rewriting. So `Object` class acts as root for all Java classes
- `Throwable` class contains the most common methods which are required for
  every `Exception` and `Error` classes. So `Throwable` class acts as root for
  Java Exception Hierarchy
```
Object
|---- String
|---- StringBuffer
|---- Throwable
|---- |---- Exception
|---- |---- |---- RuntimeException
|---- |---- |---- |---- ArithmeticException
|---- |---- |---- |---- NullPointerException
|---- |---- |---- IOException
|---- |---- Error
|---- |---- |---- OutOfMemoryError
```

## MULTIPLE INHERITANCE

- A Java class can't extend more than 1 class at a time. Hence, Multiple
  inheritance is not supported in Java with respect to classes
- Note:
	- If our class doesn't extend any other class then only our class is direct
	  child class of `Object`
    ```java
    class A {}  // Object <- A
    ```
	- If our class extends any other class then our class is indirect child
	  class of `Object` (using Multi-level inheritance)
    ```java
    class B extends A {}    // Object <- A <- B and not Object, A <- B
    ```
- This is because of Ambiguity problem (like Diamond Ambiguity)
```java
class P1 {
    public void m1();
}
class P2 {
    public void m1();
}
class A extends P1, P2 {
    // which m1() method should it inherit
}
```
- But interface can extend any number of interfaces simultaneously. Hence, Java
  provides support for Multiple inheritance with respect to interfaces
```java
interface A {}  // m1() declaration
interface B {}  // m1() declaration
interface C extends A, B {} // m1() declaration
class T implements C {} // implementation for m1()
```
- This is because ambiguity problem won't be possible in interfaces. Even
  though multiple method declarations will be available, but implementation will
  be unique (provided by the implementation class)
- Strictly speaking through interfaces we won't get any inheritance (this is
  because we don't have any implementation for the methods present in interfaces,
  so there is no use of reusability)

## CYCLIC INHERITANCE

- Not allowed in Java (since it is not required)

# HAS-A RELATIONSHIP

- Has-a relationship is also known as Composition/Aggregation
- There is no specific keyword to implement Has-a relation. Most of the time we
  are dependent on `new` keyword
- The main advantage of has-a relationship is reusability of code
- For eg.
```java
class Car {
    Engine e = new Engine();    // Car HAS-A Engine reference
}
class Engine {
    // Engine specific functionality
}
```

## DIFFERENCE BETWEEN COMPOSITION AND AGGREGATION

| **Composition**                                                                                                         	 | **Aggregation**                                                                                              	 |
|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| Without existing container object if there is no chance of existing contained objects                               	     | Without the existence of container object there will be a chance of existence of contained object        	     |
| For eg. University consists of several departments. Without existence of University, departments cannot exist either 	    | For eg. Department consists of several professors. Without a department, professor will still be present 	     |
| Strong association                                                                                                  	     | Weak association                                                                                         	     |
| Container object holds directly contained objects                                                                   	     | Container objects holds just references of contained objects                                             	     |

## IS-A VS HAS-A

| **IS-A**                                                                           	 | **HAS-A**                                                                                            	 |
|--------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| Total functionality of a class is required                                         	 | Part of functionality of a class is required                                                         	 |
| For eg. Person Class and Student class (Student class wants whole of Person class) 	 | For eg. Test class and Demo class (Only a small set of functionalities of Test are required in Demo) 	 |

# FUNCTION SIGNATURE

- In Java method signature consists of method's name followed by argument types
```java
public static int m1(int i, float f) {} // Signature - m1(int, float)
```
- Compiler will use method signature to resolve method calls
- Within a class two methods with the same signature are not allowed

# OVERLOADING

- Two methods are said to be overloaded iff both methods have the same name but
  different argument types
- The main advantage of overloading is reduced code complexity
- In overloading method resolution is always taken care by compiler based on
  reference type. Hence, overloading is also considered as compile time
  polymorphism / static type polymorphism / early binding

## AUTOMATIC PROMOTION IN OVERLOADING

- While resolving overloaded method if exact match method is not available then
  we won't get any compile time error immediately. First it will promote argument
  to the next level and check whether matched method is available or not. If it is
  available then it will be considered; if not available then compiler promotes
  argument once again to the next level. This process will be continued until all
  possible promotions. Still if the matched method is not available then we will
  get compile time error. The following are all possible promotions in overloading
```
byte -> short   -> int -> long -> float -> double
char            ->
```
```java
class Test {
    public void m1(int i) {
        System.out.println("int-arg");
    }
    public void m1(float i) {
        System.out.println("float-rag");
    }
}
public static void main(String[] args) {
    Test t = new Test();
    t.m1(10);   // int-arg
    t.m1(10.5f);    // float-arg
    t.m1('a');  // int-arg
    t.m1(10L);  // float-arg
    t.m1(10.5); // invalid - cannot find symbol m1(double)
}
```

## PARENT VS CLASS TYPE ARGUMENTS IN OVERLOADING

- While resolving overloaded methods compiler will always give preference to
  child type argument when compared with parent type argument
```java
class Test {
    public void m1(String s) {
        System.out.println("String version");
    }
    public void m1(Object o) {
        System.out.println("Object version");
    }
}
public static void main(String[] args) {
    Test t = new Test();
    t.m1(new Object()); // object version
    t.m1("durga");      // string version
    t.m1(null);         // string version
}
```

## DIFFERENT CLASS TYPE ARGUMENTS AT THE SAME LEVEL

- While resolving overloaded methods if different class type arguments are at
  the same level, then compiler will throw an ambiguity error. For eg. `String`
  and `StringBuffer` class are at the same level (both are direct children of
  `Object` class)
```java
class Test {
    public void m1(String s) {
        System.out.println("String version");
    }
    public void m1(StringBuffer sb) {
        System.out.println("StringBuffer version");
    }
}
public static void main(String[] args) {
    Test t = new Test();
    t.m1("durga");  // string version
    t.m1(new StringBuffer("durga"));    // StringBuffer version
    t.m1(null);     // invalid - reference to m1() is ambiguous
}
```

## CHANGE IN ORDER OF ARGUMENTS

-
```java
class Test {
    public void m1(int i, float f) {
        System.out.println("int float version");
    }
    public void m1(float f, int i) {
        System.out.println("float int version");
    }
}
public static void main(String[] args) {
    Test t = new Test();
    t.m1(10, 10.5f);   // int float version
    t.m1(10.5f, 10);    // float int version
    t.m1(10, 10);  // invalid - ambiguity error
    t.m1(10.5f, 10.5f);  // invalid - cannot find reference to m1(float, float)
    t.m1(10.5); // invalid - cannot find symbol m1(double)
}
```

## VARIABLE ARGUMENT METHODS

- Since variable argument type came in a later version, so to provide backwards
  compatibility Java will try and match single argument with the generic type
  method rather than the variable argument method
- In general, var-arg method will get the least priority. If no other method
  matches, only then the var-arg method will get the chance (similar to the
  `default` case inside `switch` block)
```java
class Test {
    public void m1(int i) {
        System.out.println("Generic method");
    }
    public void m1(int... i) {
        System.out.println("Var-arg method");
    }
}
public static void main(String[] args) {
    Test t = new Test();
    t.m1();     // var-arg method
    t.m1(10, 20);   // var-arg method
    t.m1(10);       // General method
}
```

## REFERENCE TYPE AND RUNTIME OBJECT

- In overloading method resolution is always taken care by compiler based on
  reference type
- In overloading runtime object won't play any role

```java
class Animal {}
class Monkey extends Animal {}
class Test {
    public void m1(Animal a) {
        System.out.println("Animal version");
    }
    public void m1(Monkey m) {
        System.out.println("Monkey version");
    }
}
public static void main(String[] args) {
    Test t = new Test();
    Animal a = new Animal();
    t.m1(a);  // Animal version
    Monkey m = new Monkey();
    t.m1(m);  // Monkey version
    Animal a1 = new Monkey();
    t.m1(a1);  // Animal version
}
```

# OVERRIDING

- Whatever methods parent has are by default available to the child through
  inheritance. If child class is not satisfied with parent class implementation
  then child is allowed to redefine its method based on its requirement. This
  process is called overriding
- The parent class method which is overridden is called overridden method and the
child class method which is overriding is called overriding method
```java
class P {
    public void property() {
        System.out.println("Cash + Land + Gold");
    }
    public void marry() {
        System.out.println("Subha Laxmi");
    }
}
class C extends P {
    public void marry() {
        System.out.println("Trisha");
    }
}
class Test {
    public static void main(String[] args) {
        P p = new P();
        p.marry();      // Subha Laxmi

        C c = new C();
        c.marry();      // Trisha

        P p1 = new P();
        p1.marry();     // Trisha
    }
}
```
- In overriding method resolution is always taken care by JVM based on runtime
object, and hence overriding is also considered as runtime polymorphism / 
dynamic polymorphism / late binding

## RULES FOR OVERRIDING

- In overriding method names and argument types must match (method signatures
must be same)
- In overriding return types must be same but this rule is applicable until 1.4v
only. From 1.5v onwards we can take co-variant return types. According to this,
child class method's return type need not be same as parent method return type,
its child type is also allowed
    - Covariant return type concept is applicable only for object types but not
    for primitive types (`double` in parent class and `int` in child class, or
    similar won't work)
```java
class P {
    public Object m1() {
        return null;
    }
}
class C extends P {
    public String m1() {
        return null;  // valid in >= 1.5v; invalid <= 1.4v
    }
}
```
| **Parent Class Method Return type** 	| Object                            	| Number                      	|
|-------------------------------------	|-----------------------------------	|-----------------------------	|
| **Child Class Method Return type**  	| Object, String, StringBuffer, ... 	| Number, Integer, Float, ... 	|


- Parent class private methods are not available to the child and hence
overriding concept is not applicable for private methods
    - Based on our requirement we can define exactly same private method in
    child class. It is valid, but this is not overriding
```java
class P {
    private void m1() {}
}
class C extends P {
    private void m1() {}  // valid - not overriding
}
```

- We can't override parent class final methods in child classes. If we try to do
so we will get compile time error. But we can override non-final parent methods
as final in child class
```java
class P {
    public final void m1() {}
    public void m2() {}
}
class C extends P {
    public void m1() {}  // cannot override m1() in P it is final
    public final void m2() {}  // valid
}
```

- Parent class abstract methods we should override in child class to provide
implementation
```java
abstract class P {
    public abstract void m1() {}
}
class C extends P {
    public void m1() {}
}
```

- We can override non-abstract methods as abstract. The main advantage of this
approach is we can stop the availability of parent method implementation in the
next child classes
```java
class P {
    public void m1() {}
}
abstract class C extends P {
    public abstract void m1() {}
}
```

- In overriding the following modifiers won't keep any restriction - strictfp,
native, synchronized

- While overriding we can't reduce scope of access modifier, but we can increase
the scope
```java
class P {
    public void m1() {}
    protected void m2() {}
}
class C extends P {
    void m1() {}  // m1() in C cannot override m1() in P - public to default
    public void m2() {} // valid - increasing scope from protected to public
}
```

### OVERRIDING AND EXCEPTION HANDLING

- Except `RuntimeException` and `Error` classes and their respective subclasses
all others are checked exceptions. `RuntimeException` and `Error` classes and
their respective subclasses are unchecked exceptions
- If child class method throws any checked exception, compulsory the parent
class method should throw the same checked exception or its parent otherwise we
will get compile time error. But there are no restrictions for unchecked
exceptions
```java
// CASE 1
class P {
    public void m1() throws Exception {} 
}
class C extends P {
    public void m1() {} // valid - no exception thrown by child class
}

// CASE 2
class P {
    public void m1() {}
}
class C extends P {
    public void m1() throws Exception {}  // invalid - child throws checked
    // exception, but parent method does not throw it or its parent
}

// CASE 3
class P {
    public void m1() throws Exception {}
}
class C extends P {
    public void m1() throws IOException {} // valid - child throws checked
    // exception, and parent method throws its parent exception
}


// CASE 4
class P {
    public void m1() throws IOException {}
}
class C extends P {
    public void m1() throws Exception {} // invalid - child throws checked
    // exception but parent method doesn't throw the same or parent exception
}


// CASE 5
class P {
    public void m1() throws IOException {}
}
class C extends P {
    public void m1() throws FileNotFoundException, EOFException {} // valid - 
    // child method throws checked exception and parent method throws parent
}

// CASE 6
class P {
    public void m1() throws IOException {}
}
class C extends P {
    public void m1() throws EOFException, InterruptedException {} // invalid -
    // child throws checked exceptions and parent method throws parent exception
    // of only one of the checked exceptions
}

// CASE 7
class P {
    public void m1() throws IOException {}
}
class C extends P {
    public void m1() throws ArithmeticException, NullPointerException {} // 
    // valid - child method does not throw any checked exception
}
```

## OVERRIDING WITH RESPECT TO STATIC METHODS

1. We can't override a static method as non-static otherwise we will get compile
time error
```java
class P {
    public static void m1() {}
}
class C extends P {
    public void m1() {} // invalid - m1() in C cannot override m1() in P
}
```
2. We can't override a non-static method as static
```java
class P {
    public void m1() {}
}
class C extends P {
    public static void m1() {} // invalid - m1() in C cannot override m1() in P
}
```
3. If both parent and child class methods are `static` then we won't get any
compile time error. It seems overridding concept is applicable for static
methods, but it is not overridding, rather it is method hiding
```java
class P {
    public static void m1() {}
}
class C extends P {
    public static void m1() {}
}
```

# METHOD HIDING

- All rules of method hiding are exactly same as overridding except the follwing
differences:
| **Method Hiding**                                                                    	| **Overridding**                                                                 	|
|--------------------------------------------------------------------------------------	|---------------------------------------------------------------------------------	|
| Both parent and child class methods should be static                                 	| Both parent and child class methods should be non-static                        	|
| Compiler is responsible for method resolution based on reference type                	| JVM is responsible for method resolution based on runtime object                	|
| Its is also known as compile-time polymorphism / static polymorphism / early binding 	| Its is also known as runtime polymorphism / dynamic polymorphism / late binding 	|
```java
class P {
    public static void m1() {
        System.out.println("Parent");
    }
}
class C extends P {
    public static void m1() {
        System.out.println("Child");
    }
}
class Test {
    public static void main(String[] args) {
        P p = new P();
        p.m1();  // Parent

        C c = new C();
        c.m1();  // Child

        P p1 = new C();
        p1.m1();  // Parent (would be Child if methods were non-static as
        // overridding concept would apply instead of method hiding)
    }
}
```
- If both parent and child class methods are non-static then it will become
overridding. In this case output would be `Parent Child Child`

## OVERRIDDING WITH RESPECT TO VAR ARG METHODS

- 

# COUPLING

- The degree of dependency between the components is called coupling
- If dependency is more then it is considered as tightly coupling and if it is
less then it is considered as loosely coupling
```java
// tight coupling
class A {
    static int i = B.j;
}
class B {
    static int j = C.k;
}
class C {
    static int k = D.m1();
}
class D {
    public static int m1() {
        return 10;
    }
}
```
- The above components are said to be tightly coupled with each other because
dependency between the components is more.
- Tightly coupling is not a good programming practice because it has several
disadvantages:
    1. Without affecting remaining components we can't modify any component
    2. Suppresses code reusability
    3. Reduces maintainability of the application
- Hence, we have to maintain dependency between the components as less as
possible. Loosely coupling is a good programming practice

# COHESION

- For every component a clear well-defined functionality is defined then that
component is said to follow high cohesion
- ![Low vs High Cohesion](./images/image.png)
- High cohesion is always a good programming practice because it has several
advantages
    1. Without affecting remaining components we can modify any component, thus
    easier enhancements
    2. Promotes code reusability (wherever validation is required we can use the
    same validateServlet without rewriting)
    3. It improves maintainibility of the application
- Loosely coupling and high cohesion are good programming practices

# OBJECT TYPE CASTING

- Parent reference can be used to hold child object (`Object o = new 
String("durga")`)
- Interface reference can be used to hold implemented class object (`Runnable r
= new Thread()`)
- `A b = (C) d`
    1. A - class or interface name
    2. B - name of reference variable
    3. C - class or interface name
    4. D - Reference variable name

## COMPILE TIME CHECKING (I)

- The type of `D` and `C` must have some relation either child-to-parent, or
parent-to-child or same type, otherwise we will get compile time error saying
inconvertible types
```java
Object o = new String("durga")
StringBuffer sb = (StringBuffer)o;  // valid - StringBuffer and Object have
// parent-to-child relation

String s = new String("durga");
StringBuffer sb = (StringBuffer)s;  // invalid - incovertible types
```

## COMPILE TIME CHECKING (II)

- `C` must be either same or derived type of `A` otherwise we will get compile
time error saying incompatible types
```java
Object o = new String("durga");
StringBuffer sb = (StringBuffer)o;  // valid - StringBuffer and StringBuffer are
// same

Object o = new String("durga");
StringBuffer sb = (String)o;  // invalid - incompatible types
```

## RUNTIME CHECKING

- Runtime object type of `d` must be either same or derived type of `C`
otherwise we will get RuntimeException saying `ClassCastException`
```java
Object o = new String("durga");
StringBuffer sb = (StringBuffer)o; // invalid - ClassCastException
// o has Runtime type of String(), and not Object()

Object o = new String("durga");
Object o1 = (String)o;  // valid - Runtime type of o is String() which is same
// (String)
```

## CONCLUSIONS

1. Strictly speaking through type casting we are not creating any new object. We
are providing another type of reference variable for an existing object, i.e. we
are performing type casting but not object casting
```java
String s = new String("durga");
Object o = (Object)s;
// New result - Object o = new String("durga")

Integer i = new Integer(10);
Number n = (Number)i;  // Number n = new Integer(10);
Object o = (Object)n;  // Object o = new Integer(10);
System.out.println(i == n);  // true
System.out.println(n == o);  // true
```
2. For e.g. A <- B <- C
```java
C c = new C();
B b = new C();  // (B)c
A a = new C();  // (A)((B)c)
```
3. Parent reference can be used to hold child object but using that reference we
can't call child specific methods, and we can call only the methods available in
parent class
```java
C c = new C();
c.m1();  // valid
c.m2();  // valid
((P)c).m1();  // valid - P p = new C(); p.m1(); -> Calling parent method from 
            // parent reference
((P)c).m2();  // invalid - P p = new C(); p.m2(); -> Calling child method from
            // parent reference
```







# STATIC CONTROL FLOW

- Whenever we are executing a Java class the following sequence of steps will be
executed as a part of static control flow
    1. Identification of static members from top to bottom [1-6]
    2. Execution of static variable assignments and static blocks from top to
    bottom [7-12]
    3. Execution of `main` method [13-15]
```java
class Base {
    static int i = 10;                           // 1 - static int i; 7 - i = 10
    static {                                     // 2 - static {}
        m1();                                    // 8
        System.out.println("First static block");// 10
    }
    public static void main(String[] args) {     // 3 - static main(String[])
        m1();                                    // 13
        System.out.println("Main method");       // 15
    }
    public static void m1() {                    // 4 - static m1()
        System.out.println(j);                   // 9, 14
    }
    static {                                     // 5 - static {}
        System.out.println("Second static block");// 11
    }
    static int j = 20;                          // 6 - static int j; 12 - j = 20
}
```

## READ INDIRECTLY WRITE ONLY

- Inside a static block if we are trying to read a variable the read operation
is called direct read
- If we are calling a method and within that method if we are trying to read a
variable that read operation is called indirect read
```java
class Test {
    static int i = 10;
    static {
        m1();
        System.out.println(i);  // direct read
    }
    public static void m1() {
        System.out.println(i);  // indirect read
    }
}
```
- If a variable is just identified by JVM and original value is not yet assigned
then the variable is said to be in read-indirectly-write-only state (RIWO)
- If a variable is in RIWO state then we can't perform direct read but we can
perform indirect read
- If we are trying to read directly we will get compile time error saying
"Illegal forward reference"


# CONSTRUCTORS

- Once we create an object compulsory we should perform initialisation then only
the object is in a position to respond properly (since JVM will provide default
values for instance variables)
- Its possible (but not recommended) to perform initialisation at the time of
declaration of instance variables (initialisation inside a instance block also
suffers from same issue)
```java
class Student {
    // these default values will initialise all objects with garbage values
    // hence not recommended
    String name = "durga";
    int roll_no = 101;
}
```
- Whenever we are creating an object the constructor will be executed
automatically to perform initialisation of the object. Hence the main purpose of
constructor is initialisation of object and not creation of an object
```java
class Student {
    String name;
    int roll_no;
    Student(String name, int rollno) {
        this.name = name;
        this.rollno = rollno;
    }
    public static void main(String[] args) {
        Student s1 = new Student("durga", 101);
        Student s2 = new Student("Ravi", 102);
    }
}
```

## DIFFERENCE BETWEEN CONSTRUCTOR AND INSTANCE BLOCK

- Other than initialisation if we want to perform any activity for every object
creation then we should go for instance block (like updating one entry in the
database for every object creation or incrementing count value for every object
creation)
- Both constructor and instance block will be executed for every object creation
but instance block first followed by constructor
```java
// Print number of objects created for a class
class Test {
    static int count = 0;
    {
        count++;
    }
    Test() {}
    Test(int i) {}
    Test(double d) {}
    public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test(10);
        Test t3 = new Test(19.3);
        System.out.println("The number of objects created = " + Test.count);
    }
}
```
- Putting `count++` inside each constructor can work, but its not recommended

## RULES FOR WRITING CONSTRUCTOR

- Name of the class and that of the constructor must be same
- Return type concept is not applicable for constructor even `void` also
    - By mistake if we are trying to declare return type for the constructor
    then we won't get any compile time error because compiler treats it like a
    method (this is legal but not recommended)
    ```java
    class Test {
        void Test() {}  // It is method and not constructor
    }
    public static void main(String[] args) {
        Test t = new Test();
        t.Test();
    }
    ```
- The only applicable modifiers for constructors are `public`, `private`,
`default` and `protected`. If we are trying to use any other modifier we will
get compile time error
```java
class Test {
    static Test() {}  // modifier static not allowed here
}
```

## DEFAULT CONSTRUCTOR

- Compiler is responsible for generating default constructor (and not JVM)
- If we are not writing any constructor then only compiler will generate default
constructor. Hence every class in Java contains constructor (either default
constructor provided by compiler or customised constructor explicitly provided
by programmer, but not both simultaneously)


### PROTOTYPE OF DEFAULT CONSTRUCTOR

- It is always a no-argument constructor
- The access modifier of default constructor is exactly same as access modifier
of class. This rule is applicable for `public` and `default` (since we can't
apply `private` and `protected` access modifiers for top level classes)
- It contains only 1 line -`super()` - It is a no argument call to Super class
constructor

### COMPILER GENERATED CODE FOR DEFAULT CONSTRUCTORS

```java
// CASE 1
// programmer's code
class Test {}
// compiler's code
class Test {
    Test() {  // default constructor created by compiler
        super();  // default constructor contains only 1 line of code - super()
    }
}

// CASE 2
// programmer's code
public class Test {}
// compiler's code
public class Test {
    public Test() { // default constructor has same access modifier as that of
                    // class
        super();  // default constructor contains only 1 line of code - super()
    }
}

// CASE 3
// programmer's code
public class Test {
    void Test() {}
}
// compiler's code
public class Test {
    public Test() {
        super();
    }
    void Test() {}
}
```

- The first line inside our constructor should be either `super()` or `this()`,
and if we are not writing anything then compiler will always place `super()`
```java
// CASE 4
// programmer's code
class Test {
    Test() {}
}
// compiler's code
class Test {
    Test() {
        super();
    }
}

// CASE 5
// programmer's code
class Test {
    Test(int i) {
        super();
    }
}
// compiler's code
class Test {
    Test(int i) {
        super();
    }
}

// CASE 6
// programmer's code
class Test {
    Test() {
        this(10);
    }
    Test(int i) {}
}
// compiler's code
class Test {
    Test() {
        this(10);
    }
    Test(int i) {
        super();
    }
}
```

- We can take `super()` or `this()` only in first line of constructor. If we
trying to take anywhere else we will get compile time error
```java
class Test {
    Test() {
        System.out.println("Constructor");
        super();  // invalid - must be first statement in constructor
    }
}
```

- Within a constructor we can take either `super()` or `this()` but not both
simulatenously
```java
class Test {
    Test() {
        super();
        this();  // invalid - must be first statement in constructor
    }
}
```

- We can use `super()` or `this()` only inside constructor. If we are trying to
use outside of constructor we will get compile time error. This is because we
can call a constructor directly from another constructor only
```java
class Test {
    public void m1() {
        super();  // invalid - must be first statement in constructor
        System.out.println("Hello");
    }
}
```

- Difference between `super()` and `this()` and `super` and `this`
| **super() and this()**                                                         	| **super and this**                                                            	|
|--------------------------------------------------------------------------------	|-------------------------------------------------------------------------------	|
| These are constructor calls to call Super class and current class constructors 	| These are keywords to refer to Super class and current class instance members 	|
| We can use only in constructors as first line                                  	| We can use anywhere except static area                                        	|
| We can use only once in constructor                                            	| We can use any number of times                                                	|
```java
class P {
    int x = 100;
}
class C extends P {
    int x = 200;
    public void m1() {
        System.out.println(super.x);  // 100 - super.x = Parent class P.x
        System.out.println(this.x);  // 200 - this.x = Current class C.x
    }
    public static void main(String[] args) {
        System.out.println(super.hashCode());  // invalid - cannot reference
        // non-static super from a static area
    }
}
```

## OVERLOADED CONSTRUCTORS

- Within a class we can declare multiple constructors and all these constructors
having same name but different type of arguments. Hence all these constructors
are considered as overloaded constructors. 
- Hence overloading concept is applicable for constructors
```java
class Test {
    Test() {
        this(10);
        System.out.println("no-args");
    }
    Test(int i) {
        this(10.5);
        System.out.println("int-arg");
    }
    Test(double d) {
        System.out.println("double-arg");
    }
    public static void main(String[] args) {
        Test t1 = new Test();  // double-arg int-arg no-arg
        Test t2 = new Test(10); // double-arg int-arg
        Test t3 = new Test(10.5);  // double-arg
        Test t4 = new Test(10L);  // double-arg
    }
}
```
- For constructors inheritence and overridding concepts are not applicable
- Every class in Java including abstract class can contain constructors but
interface cannot contain constructors. This is because a constructor is
responsible for initialisation of an object (in further detail, its resposible
for initialising instance members of a class). An interface can contain only
static members and no instance variables

### RECURSIVE CONSTRUCTOR CALL

- Recursive method call is a runtime exception saying StackOverflowError. But in
our program if there is a chance of recursive constructor invocation then the
code won't compile and we will get compile time error
```java
class Test {
    public static void m1() {
        m2();
    }
    public static void m2() {
        m1();
    }
    public static void main(String[] args) {
        // if this line in commented then our code will compile and run fine
        m1();  // invalid - StackOverflowException due to filling of call stack
        System.out.println("Hello");
    }
}

class Pest {
    Pest() {
        this(10);
    }
    Pest(int i) {
        this();
    }
    public static void main(String[] args) {
        // compile time error
        System.out.println("Hello");  // invalid - recursive call to constructor
    }
}
```

### CONSTRUCTORS AND INHERITENCE

- If parent class contains any argument constructors then while writing child
classes we have to take special care with respect to constructors
- Whenever we are writing any argument constructor it is highly recommended to
write no-argument constructor also
```java
// CASE 1
// programmer's code
class P {}
class C extends P {}
// compiler's code
class P {
    P() {
        super();
    }
}
class C extends P {
    C() {
        super();  // calling no-arg constructor of parent class P() - valid
    }
}

// CASE 2
// programmer's code
class P {
    P() {}
}
class C extends P {}
// compiler's code
class P {
    P() {
        super();
    }
}
class C extends P {
    C() {
        super();  // calling no-arg constructor of parent class P() - valid
    }
}

// CASE 3
// programmer's code
class P {
    P(int i) {}
}
class C extends P {}  // invalid - cannot find P() in P
// compiler's code
class P {
    P(int i) {
        super();
    }
}
class C extends P {
    C() {
        super();  // calling no-arg constructor of parent class P() - none
        // hence invalid
    }
}
```

### CONSTRUCTORS AND EXCEPTION HANDLING

- For a checked exception, if a method `throws` a checked exception, it is the
responsibility of the caller of that method to handle the checked exception.
This handling can be done in two ways - using `try-catch`, or using `throws`
```java
m1() throws IOException {}

// valid caller - handles checked exception throws by called method using try
// catch
m2() {
    try {
        m1();
    } catch IOException {}
}

// valid caller - handles checked exception thrown by called method using throws
m3() throws IOException {
    m1();
}
```
- If parent class constructors throws any checked exception compulsory child
class constructor should throw the same checked exception or its parent,
otherwise the code won't compile
```java
// programmer's code
class P {
    P() throws IOException {}
}
class C extends P {}
// compiler's code
class P {
    P() throws IOException {
        super();
    }
}
class C extends P {
    C() {
        super();  // this is the caller to the no-arg parent class constructor
        // the caller should be responsible for handling the checked exception
        // since it does not hence this program gives compile time error
        // invalid - unreported exception IOException
    }
}

// SOLUTION TO THE PARENT THROWING A CHECKED EXCEPTION
// Using try catch won't work because
class P {
    P() throws IOException {}
}
class C extends P {
    C() {
        try {  // invalid - first line of constructor should be super() or
               // this()
            super();
        }
        catch IOException {}
    }
}

// Using throws is the only option
// programmer's code
class P {
    P() throws IOException {}
}
class C extends P {
    C() throws IOException {} // Exception | Throwable also work as they are
                              // parent classes of IOException
}
// compiler's code
class P {
    P() throws IOException {
        super();
    }
}
class C extends P {
    C() throws IOException {
        super();  // valid - calling no-arg constructor of parent class P()
                  // and handling the checked exception using throws
    }
}
```

## SUMMARY OF CONSTRUCTOR

- The main purpose of constructor is to create an object - invalid
- The main purpose of constructor is to perform initialisation of an object - 
valid
- The name of the constructor need not be same as the class name - invalid
- Return type concept is applicable for constructors but only void - invalid
- We can apply any modifier for constructor - invalid
- Default constructor is constructed by JVM - invalid
- Compiler is responsible to generate default constructor - valid
- Compiler will always generate default constructor - invalid
- If we are not writing no-arg constructor then compiler will generate a 
default constructor - invalid
- Every no-arg constructor is a default constructor - invalid
- Default constructor is always no-arg constructor - valid
- First line inside a constructor should be `super()` or `this()`. If we are not
writing anything then compiler will generate `this()` - invalid
- For constructors both overloading and overridding concepts are applicable -
invalid
- For constructors inheritence concept is applicable but not overridding -
invalid
- Only concrete classes can contain constructor, but abstract classes cannot -
invalid
- Interface can contain constructors - invalid
- Recursive constructor invocation is a runtime exception - invalid
- If parent class constructor throws some checked exception then compulsory
child class constructor should throw the same checked exception or its child -
invalid

# SINGLETON CLASS

- Any Java class for which we are allowed to create only one object. For eg.
`Runtime`

## ADVANTAGE

- If several people have same requirement then it is not recommended to create
separate object for every requirement. We have to create only one object and we
can reuse same object for every similar requirement so that performance and
memory utilisation will be improved
- This is the main use of private constructor. This is because we want to
restrict object creation, so we make the constructor of the class inaccessible.
To create objects for singleton class we use factory method
```java
Runtime r1 = Runtime.getRuntime();
Runtime r2 = Runtime.getRuntime();
```

## CREATION OF SINGLETON CLASSES

1. By using private constructor, private static variable and public factory
method
- Runtime class is internally implemented using this approach. 
```java
class Test {
    private static Test t = new Test();
    private Test() {}
    public static Test getTest() {
        return t;
    }
}
// any references created will refer to the one common static object present
Test t1 = Test.getTest();
Test t2 = Test.getTest();
```

2. Using another approach by creating object in the factory method (whenever we
get the request for object creation the first time)
```java
class Test {
    private static Test t = null;
    private Test() {}
    public static Test getTest() {
        if (t == null) {
            t = new Test();
        }
        return t;
    }
}
// the object gets created when t1 calls the factory method (and not before it)
Test t1 = Test.getTest();
// t2 refers to the same object being pointed to by t1
Test t2 = Test.getTest();
```

# EXTRAS

- Class is not final but we are not allowed to create child classes. How is this
possible?
- By declaring every constructor as private we can restrict child class creation
```java
class P {
    private P() {}
}
// for this class if we try to create a child class
class C extends P {
    C() {}
    // the constructor of child class will have super() - which refers to
    // no-argument constructor of the parent class - which is inaccessible
    // since it is private
}
```
