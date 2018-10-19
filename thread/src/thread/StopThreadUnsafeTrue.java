package thread;

/**
 *  线程终止正确代码
 * @author sunxiaozhe
 * @time 2018/9/4 21:15
 */
public class StopThreadUnsafeTrue {
    public static User u = new User();
    public static void main(String[] args) throws InterruptedException {
        new ReadObbjectThread().start();
        while (true){
            Thread t  =new ChangeObjectThread();
            t.start();
            Thread.sleep(5);
            ((ChangeObjectThread) t).stopMe();
        }
    }

    public static class ChangeObjectThread extends Thread{
        volatile boolean stopme = false;
        public void stopMe(){
            stopme = true;
        }
        @Override
        public void run() {
            while (true){
                if (stopme){
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u){
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObbjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if (u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }
    private static class User {
        private int id;
        private String name;
        public User(){
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
