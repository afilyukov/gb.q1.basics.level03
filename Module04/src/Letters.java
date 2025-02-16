public class Letters {
    private String currentLocker = "A";

    public synchronized void printA(){
        for (int i = 0; i < 3; i++) {
            while(!currentLocker.equals("A")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println(currentLocker);
            currentLocker = "B";
            notifyAll();
        }
    }

    public synchronized void printB(){
        for (int i = 0; i < 3; i++) {
            while(!currentLocker.equals("B")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println(currentLocker);
            currentLocker = "C";
            notifyAll();
        }
    }

    public synchronized void printC(){
        for (int i = 0; i < 3; i++) {
            while(!currentLocker.equals("C")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println(currentLocker);
            currentLocker = "A";
            notifyAll();
        }
    }
}
