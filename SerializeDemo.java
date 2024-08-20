import java.io.*;

class Dog implements Serializable {
    int i = 10;
    int j = 20;
}

class SerializeDemo {
    public static void main(String[] args) throws Exception {
        Dog d1 = new Dog();
        
		// Serialisation
        FileOutputStream fos = new FileOutputStream("abc.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(d1);
		}
        // Deserialisation
        FileInputStream fis = new FileInputStream("abc.ser");
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
			Dog d2 = (Dog)ois.readObject();

			System.out.println(d2.i + "..." + d2.j);
		}
    }
}
