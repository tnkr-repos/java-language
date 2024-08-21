public class FinalizeDemo {
    static FinalizeDemo s;
    public static void main(String[] args) throws InterruptedException {
        FinalizeDemo finalizeDemo = new FinalizeDemo();
        System.out.println(finalizeDemo.hashCode());    // 100
        finalizeDemo = null;
        System.gc();                                    // finalize called
        Thread.sleep(5000);
        System.out.println(s.hashCode());               // 100
        s = null;
        Thread.sleep(10000);
        System.out.println("end of main");              // end of main
    }
    public void finalize() {
        System.out.println("finalize called");
        s = this;
    }
}
