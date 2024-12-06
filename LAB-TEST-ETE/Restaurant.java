class Orders {
    public int[] orders = new int[15]; 
    public int count = 0;             
    public int addIndex = 0;          
    public int pickIndex = 0;         
    public synchronized void addOrder(int order) {
        while (count == orders.length) { 
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        orders[addIndex] = order;          
        addIndex = (addIndex + 1) % orders.length; 
        count++;                           
        notifyAll();                       
    }
    public synchronized int pickOrder() {
        while (count == 0) { 
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int order = orders[pickIndex];          
        pickIndex = (pickIndex + 1) % orders.length; 
        count--;                                
        notifyAll();                         
        return order;
    }
}

class Chef implements Runnable {
    public Orders Orders;

    public Chef(Orders Orders) {
        this.Orders = Orders;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) { 
            int order = Orders.pickOrder();
            System.out.println("Chef: Preparing order " + order);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Chef: Order " + order + " prepared");
        }
    }
}

class Waiter implements Runnable {
    public Orders Orders;

    public Waiter(Orders Orders) {
        this.Orders = Orders;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 15; i++) { 
            int order = Orders.pickOrder();
            System.out.println("Waiter: Delivering order " + order);
            try {
                Thread.sleep(800); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Waiter: Order " + order + " delivered");
        }
    }
}

public class Restaurant {
    public static void main(String[] args) {
        Orders Orders = new Orders();

        Thread chefThread = new Thread(new Chef(Orders));
        Thread waiterThread = new Thread(new Waiter(Orders));

        chefThread.start();
        waiterThread.start();

        for (int i = 1; i <= 15; i++) {
            Orders.addOrder(i);
            System.out.println("Main: Added order " + i);
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
