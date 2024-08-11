import java.util.concurrent.BlockingQueue;

class Waiter extends Thread
{
    private static int waiterCount = 0;
    private int waiterId;
    private BlockingQueue<String> cookedFoodQueue;

    public Waiter(BlockingQueue<String> cookedFoodQueue)
    {
        this.cookedFoodQueue = cookedFoodQueue;
        this.waiterId = ++waiterCount;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                String servedDish = cookedFoodQueue.take();
                System.out.println("Waiter " + waiterId + " is serving " + servedDish);
                Thread.sleep(100);

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}