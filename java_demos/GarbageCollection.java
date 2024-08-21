public class GarbageCollection {
    public static void main(String[] args) {
        String s = new String("durga");
        s = null;
        System.gc();
        
        System.out.println("Nullifying Test object");
        GarbageCollection g = new GarbageCollection();
        g = null;
        System.gc();
        
        System.out.println("End of Main");

        GarbageCollection t = new GarbageCollection();
        t.finalize();
        t.finalize();
        t = null;
        System.gc();
    }
    
    public void finalize() {
        System.out.println("Finalize method called");
    }
}
