package thread;

/**
 * 守护线程的简单使用
 * @author sunxiaozhe
 * @time 2018/9/5 17:26
 */
public class DaemonDemo {
    public static class DaemonT extends Thread{
        public void run(){
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);  //将线程t设为守护线程，注意：设置守护线程必须在线程start之前
        t.start();          //
        Thread.sleep(2000);
    }
}
