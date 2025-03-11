import java.util.Random;

public class Hive {
    private final Object passage1 = new Object();
    private final Object passage2 = new Object();
    private boolean passage1Busy = false;
    private boolean passage2Busy = false;
    private final Random random = new Random();

    public int tryToEnter(int beeId) throws InterruptedException {
        synchronized (passage1) {
            if (!passage1Busy) {
                passage1Busy = true;
                System.out.println("Pszczoła " + beeId + " wlatuje pod przelot 1");
                return 1;
            }
        }

        System.out.println("Pszczoła " + beeId + " przelot 1 zajęty, sprawdza przelot 2");
        Thread.sleep(300);

        synchronized (passage2) {
            if(!passage2Busy) {
                passage2Busy = true;
                System.out.println("Pszczoła " + beeId + " wlatuje pod przelot 2");
                return 2;
            } else {
                boolean waitOnPassage2 = random.nextBoolean();
                if (waitOnPassage2) {
                    System.out.println("Pszczoła " + beeId + " przelot 2 zajęty, oczekuję przelot 2");
                    while(passage2Busy) {
                        passage2.wait();
                    }
                    passage2Busy = true;
                    System.out.println("Pszczoła " + beeId + " wlatuje pod przelot 2");
                    return 2;
                } else {
                    System.out.println("Pszczoła " + beeId + " przelot 2 zajęty, sprawdzam przelot 1");
                    synchronized (passage1) {
                        if (!passage1Busy) {
                            passage1Busy = true;
                            System.out.println("Pszczoła " + beeId + " wlatuje pod przelot 1");
                            return 1;
                        } else {
                            System.out.println("Pszczoła " + beeId + " przelot 1 zajęty, oczekuję przelot 1");
                            while(passage1Busy) {
                                passage1.wait();
                            }
                            passage1Busy = true;
                            System.out.println("Pszczoła " + beeId + " wlatuje pod przelot 1");
                            return 1;
                        }
                    }
                }
            }
        }
    }

    public void leaveHive(int passage, int beeId) {
        if (passage == 1) {
            synchronized (passage1) {
                passage1Busy = false;
                System.out.println("Pszczoła " + beeId + " wylatuje z przelotu 1");
                passage1.notifyAll();
            }
        } else if (passage == 2) {
            synchronized (passage2) {
                passage2Busy = false;
                System.out.println("Pszczoła " + beeId + " wylatuje z przelotu 2");
                passage2.notifyAll();
            }
        }
    }
}
