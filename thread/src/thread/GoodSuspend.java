package thread;

/**
 * 利用wait（）和notify（）方法在应用层面实现suspend（）和resume（）
 * @author sunxiaozhe
 * @time 2018/9/5 15:34
 */
public class GoodSuspend {
    public static Object u = new Object();

    public static class ChangeObjectThread extends Thread{
        volatile boolean suspendme = false;

        public void suspendMe(){
            suspendme = true;
        }

        /**
         * 该方法被调用时，线程t1得到一个继续执行的notify（），并清除挂起标记
         */
        public void resumeMe(){
            suspendme = false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){

                synchronized (this){     //检查自己是否被挂起，若是，则执行wait方法等待，否则正常处理
                    while (suspendme)   //挂起
                        try {
                        wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                synchronized (u){
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }
    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread();
        ReadObjectThread t2 = new ReadObjectThread();

        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.suspendMe();
        System.out.println("suspend t1 sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeMe();
    }
}
