import java.lang.reflect.*;

class Student {
    public String getName() {
        return null;
    }
    public int getMarks() {
        return 10;
    }
}

class Test {
    public static void main(String[] args) throws Exception {
        int count = 0;
        Class c = Class.forName("Student");
        Method[] m = c.getDeclaredMethods();
        for(Method m1: m) {
            count++;
            System.out.println(m1.getName());
        }
        System.out.println("The number of methods: " + count); 

        Student s1 = new Student();
        Class c1 = s1.getClass();   // get corresponding Class object for s1
        Student s2 = new Student();
        Class c2 = s2.getClass();   // get corresponding Class object for s2
        System.out.println(c1 == c2);   // true
    }
}