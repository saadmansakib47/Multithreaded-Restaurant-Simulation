import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
class Receptionist {
    private ArrayList<Integer> availableTables;

    public Receptionist(int numTables)
    {
        availableTables = new ArrayList<>();
        for (int i = 1; i <= numTables; i++) {
            availableTables.add(i);
        }
    }

    public synchronized void assignTable(Customer customer)
    {
        if (!availableTables.isEmpty())
        {
            int assignedTable = availableTables.remove(0);
            System.out.println("Receptionist assigned Table " + assignedTable + " to Customer " + customer.getId());
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}