import java.util.concurrent.*;

public class RestaurantSimulation {
    public static void main(String[] args) {
        int numTables = 5;
        int numChefs = 2;
        int numWaiters = 3;
        int numCustomers = 10;

        BlockingQueue<String> orderQueue = new LinkedBlockingQueue<>();
        BlockingQueue<String> cookedFoodQueue = new LinkedBlockingQueue<>();
        Receptionist receptionist = new Receptionist(numTables);

        // Create thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(numCustomers + numChefs + numWaiters);

        try {
            // Submit customers, chefs, and waiters to the executor service
            for (int i = 0; i < numCustomers; i++) {
                executorService.submit(new Customer(orderQueue, receptionist));
            }

            for (int i = 0; i < numChefs; i++) {
                executorService.submit(new Chef(orderQueue, cookedFoodQueue));
            }

            for (int i = 0; i < numWaiters; i++) {
                executorService.submit(new Waiter(cookedFoodQueue));
            }

            // Shutdown the executor service to stop accepting new tasks
            executorService.shutdown();

            // Await termination of all threads
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}