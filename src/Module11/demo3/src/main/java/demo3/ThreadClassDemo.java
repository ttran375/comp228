package demo3;

public class ThreadClassDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Runnable hello = new DisplayMessage("Hello");
        Thread thread1 = new Thread(hello);
        thread1.setDaemon(true);
        thread1.setName("hello");
        System.out.println("Starting hello thread....");
        thread1.start();
        Runnable bye = new DisplayMessage("Goodbye");
        Thread thread2 = new Thread(bye);
        thread2.setDaemon(true);
        thread2.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Starting goodbye thread....");
        thread2.start();
        System.out.println("Starting thread3....");
        Thread thread3 = new GuessNumber(2);
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted!");
        }
        System.out.println("Starting thread 4....");
        Thread thread4 = new GuessNumber(7);
        thread4.start();
        System.out.println("main() is ending");
    }
}

class DisplayMessage implements Runnable {
    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(message);
        }
    }
}

class GuessNumber extends Thread {
    private int number;

    public GuessNumber(int number) {
        this.number = number;
    }

    public void run() {
        int counter = 0;
        int guess = 0;
        do {
            guess = (int) (Math.random() * 10 + 1);
            System.out.println(this.getName() + " guesses " +
                    guess);
            counter++;
        } while (guess != number);
        System.out.println("*** Correct!!!" + this.getName() + " in" + counter + " guesses***");
    }
}
