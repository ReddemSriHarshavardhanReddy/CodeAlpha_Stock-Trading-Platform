import java.util.HashMap;
import java.util.Scanner;
        
class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Portfolio {
    HashMap<String, Integer> holdings = new HashMap<>();
    double balance;

    Portfolio(double balance) {
        this.balance = balance;
    }

    public void buyStock(String stockName, int quantity, double price) {
        double cost = price * quantity;
        if (balance >= cost) {
            holdings.put(stockName, holdings.getOrDefault(stockName, 0) + quantity);
            balance -= cost;
            System.out.println("Bought " + quantity + " shares of " + stockName + " at Rs/-" + price);
        } else {
            System.out.println("Insufficient balance to complete the purchase.");
        }
    }

    public void sellStock(String stockName, int quantity, double price) {
        if (holdings.containsKey(stockName) && holdings.get(stockName) >= quantity) {
            holdings.put(stockName, holdings.get(stockName) - quantity);
            balance += price * quantity;
            System.out.println("Sold " + quantity + " shares of " + stockName + " at Rs/-" + price);
        } else {
            System.out.println("You don't own enough shares of " + stockName);
        }
    }

    public void viewPortfolio() {
        System.out.println("Your portfolio:");
        for (String stockName : holdings.keySet()) {
            System.out.println(stockName + ": " + holdings.get(stockName) + " shares");
        }
        System.out.println("Available Balance: Rs/-" + balance);
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Simulated stock market data
        HashMap<String, Stock> market = new HashMap<>();
        market.put("APPLE", new Stock("APPLE", 15000.00));
        market.put("GOOGLE", new Stock("GOOGLE", 2800.00));
        market.put("TESLA", new Stock("TESLA", 7500.00));

        // User's portfolio
        Portfolio portfolio = new Portfolio(5000.00); // Initial balance of $5000

        System.out.println("Welcome to the Stock Trading Platform!");
        
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    // View market data
                    System.out.println("\nMarket Data:");
                    for (String stockName : market.keySet()) {
                        Stock stock = market.get(stockName);
                        System.out.println(stock.getName() + ": $" + stock.getPrice());
                    }
                    break;

                case 2:
                    // Buy stock
                    System.out.print("Enter stock symbol: ");
                    String buyStockName = sc.next().toUpperCase();
                    if (market.containsKey(buyStockName)) {
                        Stock stock = market.get(buyStockName);
                        System.out.print("Enter quantity: ");
                        int buyQuantity = sc.nextInt();
                        portfolio.buyStock(buyStockName, buyQuantity, stock.getPrice());
                    } else {
                        System.out.println("Stock not found in the market.");
                    }
                    break;

                case 3:
                    // Sell stock
                    System.out.print("Enter stock symbol: ");
                    String sellStockName = sc.next().toUpperCase();
                    if (market.containsKey(sellStockName)) {
                        Stock stock = market.get(sellStockName);
                        System.out.print("Enter quantity: ");
                        int sellQuantity = sc.nextInt();
                        portfolio.sellStock(sellStockName, sellQuantity, stock.getPrice());
                    } else {
                        System.out.println("Stock not found in the market.");
                    }
                    break;

                case 4:
                    // View portfolio
                    portfolio.viewPortfolio();
                    break;

                case 5:
                    // Exit the platform
                    System.out.println("Thank you for using the Stock Trading Platform. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");

                    sc.close();
            }
           
        }
        
    }
}

