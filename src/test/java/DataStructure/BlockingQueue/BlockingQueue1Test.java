package DataStructure.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class BlockingQueue1Test {

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue1<String> queue = new BlockingQueue1<>(3);

        Thread t1 = new Thread(()->{
            try{
                System.out.println(System.currentTimeMillis() + " begin");  //打印当前时间戳，表示生产者线程的开始时间。
                queue.offer("任务1");
                System.out.println(queue);
                queue.offer("任务2");
                System.out.println(queue);
                queue.offer("任务3");
                System.out.println(queue);
                queue.offer("任务4",5000);  //尝试在 5000 毫秒内将 "任务4" 添加到队列中。如果队列已满，当前线程将会等待直到队列有空间或超过5000毫秒。
                System.out.println(queue);
                System.out.println(System.currentTimeMillis() + " end");   //打印当前时间戳，表示生产者线程的结束时间。

            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        },"生产者");

        t1.start();

        Thread.sleep(2000);  //主线程在启动生产者线程后休眠2秒。
        queue.poll();  //在主线程休眠结束后，从队列中移除一个元素。这个操作有可能触发阻塞队列中的条件变量，允许等待中的生产者线程继续执行。
    }

}