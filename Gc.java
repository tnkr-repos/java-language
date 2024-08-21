public class Gc {
    static int count = 1;
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {      // GC will be called depending
            // upon the number of iterations (number of objects created)
            Gc g = new Gc();
            g = null;
        }
    }
    public void finalize() {
        // The number of times finalize() is called differs across different
        // runs for the same number of iterations
        System.out.println("finalize() method called: " + count);
        count++;
    }
}
