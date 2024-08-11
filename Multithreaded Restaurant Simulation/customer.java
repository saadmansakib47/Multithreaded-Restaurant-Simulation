import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Customer extends Thread
{
    private static int customerCount = 0;
    private int customerId;
    private Queue<String> orderQueue;
    private Receptionist receptionist;

    public Customer(Queue<String> orderQueue, Receptionist receptionist)
    {
        this.orderQueue = orderQueue;
        this.receptionist = receptionist;
        this.customerId = ++customerCount;
    }

    @Override
    public void run()
    {
        placeOrder();
        waitForFood();
        System.out.println("Customer " + customerId + "just finished dining.");
    }

    private final Object foodServedLock = new Object();
    private boolean foodServed = false;


    private void placeOrder()
    {
        String[] dishes = {"Chicken Burger","Beef Burger", "Pizza", "Pasta", "Nachos", "Doughnut","Milkshake"};
        Random random = new Random();
        String orderedDish = dishes[random.nextInt(dishes.length)];

        System.out.println("Customer " + customerId + " is placing an order for " + orderedDish);
        orderQueue.add(orderedDish);
        serveFood();
        receptionist.assignTable(this);
    }

    private void waitForFood() {
        System.out.println("Customer " + customerId + " is waiting for the food to be served.");

        synchronized (foodServedLock) {
            while (!foodServed) {
                try
                {
                    foodServedLock.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void serveFood() {
        synchronized (foodServedLock) {
            foodServed = true;
            foodServedLock.notify();
        }
    }
}