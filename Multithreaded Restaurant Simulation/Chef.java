import java.util.concurrent.BlockingQueue;

class Chef extends Thread
{
    private static int chefCount = 0;
    private int chefId;
    private BlockingQueue<String> orderQueue;
    private BlockingQueue<String> cookedFoodQueue;

    public Chef(BlockingQueue<String> orderQueue, BlockingQueue<String> cookedFoodQueue)
    {
        this.orderQueue = orderQueue;
        this.cookedFoodQueue = cookedFoodQueue;
        this.chefId = ++chefCount;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                String orderedDish = orderQueue.take();
                System.out.println("Chef " + chefId + " is preparing " + orderedDish);
                Thread.sleep(2000);
                cookedFoodQueue.put(orderedDish);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}