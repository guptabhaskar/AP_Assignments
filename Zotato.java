package com.company;

import java.util.*;

interface Common{
    void displayReward();
    void displayBalance();
}

class User implements Common{
    private String name;
    private float wallet;
    private float reward;
    private String address;
    private ArrayList<String> orders;
    final private float deliveryFee;
    final private float discount;
    private ArrayList<Item> order;
    private ArrayList<Restaurant> rlist;

    User(String name, String address){
        this.name = name;
        this.wallet = 1000;
        this.orders = new ArrayList<>();
        this.address = address;
        this.deliveryFee = 40;
        this.discount = 0;
        this.order = new ArrayList<>();
        this.rlist = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public ArrayList<String> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<String> orders) {
        this.orders = orders;
    }

    public float getReward() {
        return reward;
    }

    public void setReward(float reward) {
        this.reward = reward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDeliveryFee() {
        return deliveryFee;
    }

    public float getDiscount() {
        return discount;
    }

    public void printOrders(Scanner input) {
        System.out.println("Total Orders: " + orders.size());
        if(orders.size()<=10) {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println(orders.get(i));
            }
        }
        else {
            for (int i = orders.size()-10; i < orders.size(); i++) {
                System.out.println(orders.get(i));
            }
        }
    }

    @Override
    public void displayReward() {
        System.out.println("Reward Points: " + this.getReward());
    }

    @Override
    public void displayBalance() {
        System.out.println(this.getName() + " (" + this.getClass().getSimpleName() + "), " + this.getAddress() + ", " + this.getWallet() + "/-");
    }

    public ArrayList<Item> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Item> order) {
        this.order = order;
    }

    public void addItem(Item i, int quantity) {
        Item temp = i;
        temp.setQuantity(quantity);
        this.order.add(temp);
    }

    public void clearOrder() {
        this.order.clear();
    }

    public ArrayList<Restaurant> getRlist() {
        return rlist;
    }

    public void setRlist(ArrayList<Restaurant> rlist) {
        this.rlist = rlist;
    }
}

class EliteUser extends User{
    final private float deliveryFee;
    final private float discount;

    EliteUser(String name, String address){
        super(name, address);
        this.deliveryFee = 0;
        this.discount = 50;
    }

    public float getDiscount() {
        return discount;
    }

    public float getDeliveryFee() {
        return deliveryFee;
    }
}

class SpecialUser extends User{
    final private float deliveryFee;
    final private float discount;

    SpecialUser(String name, String address){
        super(name, address);
        this.deliveryFee = 20;
        this.discount = 25;
    }

    public float getDeliveryFee() {
        return deliveryFee;
    }

    public float getDiscount() {
        return discount;
    }
}

class Restaurant implements Common{
    private String name;
    private ArrayList<Item> menu;
    private float reward;
    private float balance;
    private String address;
    final private int restaurantId;
    private int id;
    protected float discount;
    protected float moreDiscount;

    Restaurant(String name, String address, int rid) {
        this.name = name;
        this.reward = 0;
        this.balance = 0;
        this.address = address;
        this.id = 1;
        this.restaurantId = rid;
        menu = new ArrayList<>();
        this.discount = 0;
        this.moreDiscount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Item> menu) {
        this.menu = menu;
    }

    public float getReward() {
        return reward;
    }

    public void setReward(float reward) {
        this.reward = reward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setMoreDiscount(float moreDiscount) {
        this.moreDiscount = moreDiscount;
    }

    @Override
    public void displayReward(){
        System.out.println("Reward Points: " + this.getReward());
    }

    @Override
    public void displayBalance() {
        System.out.println(this.getName() + " (" + this.getClass().getSimpleName() + "), " + this.getAddress() + ", " + this.getBalance() + "/-");
    }

    public void addItem(Scanner input) {
        System.out.println("Enter food item details\nFood name:");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("Item Price:");
        float price = input.nextFloat();
        System.out.println("Item Quantity:");
        int quantity = input.nextInt();
        System.out.println("Item Category:");
        input.nextLine();
        String s = input.nextLine();
        System.out.println("Offer:");
        float discount = input.nextFloat();
        ArrayList<Item> temp = this.getMenu();
        temp.add(new Item(this.getId(), name, price, quantity, s, discount));
        System.out.println(this.getId() + " " + name + " " + price + " " + quantity + " " + discount + "% Off " + s);
        this.setId(this.getId()+1);
        this.setMenu(temp);
    }

    public void editItem(Scanner input) {
        System.out.println("Choose item by code");
        for(int i = 0; i< menu.size(); i++) {
            System.out.println((i+1) + " " + this.name + " - " + menu.get(i).getName() + " "
                    + menu.get(i).getPrice() + " " + menu.get(i).getQuantity() + " " +
                    menu.get(i).getOffer() + "% off " + menu.get(i).getCategory());
        }
        int in = input.nextInt();
        in -= 1;
        System.out.println("Choose an attribute to edit: \n" +
                           "   1) Name\n"+
                           "   2) Price\n"+
                           "   3) Quantity\n"+
                           "   4) Category\n"+
                           "   5) Offer");
        int in1 = input.nextInt();
        switch (in1) {
            case 1 -> {
                System.out.println("Enter the new name - ");
                input.nextLine();
                String name = input.nextLine();
                menu.get(in).setName(name);
            }
            case 2 -> {
                System.out.println("Enter the new price - ");
                float price = input.nextFloat();
                menu.get(in).setPrice(price);
            }
            case 3 -> {
                System.out.println("Enter the new quantity - ");
                int quantity = input.nextInt();
                menu.get(in).setQuantity(quantity);
            }
            case 4 -> {
                System.out.println("Enter the new category - ");
                input.nextLine();
                String category = input.nextLine();
                menu.get(in).setCategory(category);
            }
            case 5 -> {
                System.out.println("Enter the new offer - ");
                float offer = input.nextFloat();
                menu.get(in).setOffer(offer);
            }
        }
    }

    public void editDiscount(Scanner input) {
            System.out.println("You can't edit overall bill discount. Try another query.");
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public float getDiscount() {
        return discount;
    }

    public float getMoreDiscount() {
        return moreDiscount;
    }

    public float getRewardVal(float value) {
        return ((int)value/100)*5;
    }
}

class FastFoodRestaurant extends Restaurant{
    FastFoodRestaurant(String name, String address, int rid) {
        super(name, address, rid);
    }

    @Override
    public void editDiscount(Scanner input) {
        System.out.println("Enter offer on total value: ");
        float in = input.nextFloat();
        super.setDiscount(in);
    }

    @Override
    public float getRewardVal(float val){
        return ((int)val/150)*10;
    }
}

class AuthenticRestaurant extends Restaurant{

    AuthenticRestaurant(String name, String address, int rid) {
        super(name, address, rid);
        this.discount = 0;
        this.moreDiscount = 50;
    }

    @Override
    public void editDiscount(Scanner input) {
        System.out.println("Enter offer on total value: ");
        float in = input.nextFloat();
        setDiscount(in);
    }

    @Override
    public float getRewardVal(float val){
        return ((int)val/200)*25;
    }
}

class Item{
    final private int id;
    private String name;
    private float price;
    private int quantity;
    private String category;
    private float offer;

    Item(int id, String name, float price, int quantity, String category, float offer){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public float getOffer() {
        return offer;
    }

    public void setOffer(float offer) {
        this.offer = offer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

class Zotato {
    private HashMap<Integer, Restaurant> restaurants;
    private HashMap<Integer, User> users;
    private float transactionFee;
    private float deliveryFee;

    Zotato(){
        restaurants = new HashMap<>();
        users = new HashMap<>();
        this.transactionFee = 0;
        this.deliveryFee = 0;
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
    }

    public float getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    void initialize(){
        User u = new EliteUser("Ram", "Sarita Vihar");
        users.put(1, u);
        u = new EliteUser("Sam", "Jasola Vihar");
        users.put(2, u);
        u = new SpecialUser("Tim", "Sangam Vihar");
        users.put(3, u);
        u = new User("Kim", "Badarpur");
        users.put(4, u);
        u = new User("Jim", "Govind Puri");
        users.put(5, u);
        Restaurant r = new AuthenticRestaurant("Shah", "Sarita Vihar", 1);
        restaurants.put(1, r);
        r = new Restaurant("Ravi's", "Jasola Vihar", 2);
        restaurants.put(2, r);
        r = new AuthenticRestaurant("The Chinese", "Badarpur", 3);
        restaurants.put(3, r);
        r = new FastFoodRestaurant("Wang's", "Sangam Vihar", 4);
        restaurants.put(4, r);
        r = new Restaurant("Paradise", "Ghanta Vihar", 5);
        restaurants.put(5, r);
    }

    //Query 1
    public void asRestaurant(Scanner input) {
        chooseRestaurant();
        int in = input.nextInt();
        int in1 = -1;
        while(in1!=5) {
            System.out.println("Welcome " + restaurants.get(in).getName());
            System.out.println(
                                "    1) Add item\n" +
                                "    2) Edit item\n" +
                                "    3) Print Rewards\n" +
                                "    4) Discount on bill value\n" +
                                "    5) Exit"
                              );
            in1 = input.nextInt();
            Common c = restaurants.get(in);
            switch (in1) {
                case 1 -> restaurants.get(in).addItem(input);
                case 2 -> restaurants.get(in).editItem(input);
                case 3 -> c.displayReward();
                case 4 -> restaurants.get(in).editDiscount(input);
            }
        }
    }

    private void chooseRestaurant() {
        System.out.println("Choose Restaurant:");
        for(Map.Entry<Integer, Restaurant> set : restaurants.entrySet())
        {
            System.out.println("    "+ set.getKey() + ") " + set.getValue().getName() + " (" + set.getValue().getClass().getSimpleName() + ")");
        }
    }

    //Query 2
    public void asUser(Scanner input) {
        for(Map.Entry<Integer, User> set : users.entrySet())
        {
            System.out.println("    " + set.getKey() + ") " + set.getValue().getName() + " (" + set.getValue().getClass().getSimpleName() + ")");
        }
        int in = input.nextInt();
        Common c=users.get(in);
        int in1 = -1;
        while(in1!=5){
            System.out.println("Welcome " + users.get(in).getName());
            System.out.println(
                    "Customer Menu \n" +
                            "1) Select Restaurant \n" +
                            "2) Checkout Cart \n" +
                            "3) Reward Won \n" +
                            "4) Print the recent orders \n" +
                            "5) Exit"
            );
            in1 = input.nextInt();
            switch (in1) {
                case 1 -> selectRestaurant(input, in);
                case 2 -> checkoutCart(input, in);
                case 3 -> c.displayReward();    // Done
                case 4 -> users.get(in).printOrders(input);     // Done
            }
        }
    }

    //Used for Query 1
    private void selectRestaurant(Scanner input, int in) {
        chooseRestaurant();
        int r = input.nextInt();
        System.out.println("Choose item by code");
        for(int i = 0; i< restaurants.get(r).getMenu().size(); i++) {
            System.out.println((i+1) + " " + restaurants.get(r).getName() + " - " + restaurants.get(r).getMenu().get(i).getName() + " "
                    + restaurants.get(r).getMenu().get(i).getPrice() + " " + restaurants.get(r).getMenu().get(i).getQuantity() + " " +
                    restaurants.get(r).getMenu().get(i).getOffer() + "% off " + restaurants.get(r).getMenu().get(i).getCategory());
        }
        int itemNo;
        itemNo = input.nextInt();
        System.out.println("Enter item quantity: ");
        int quantity;
        quantity = input.nextInt();
        ArrayList<Item> tempOrder = users.get(in).getOrder();
        ArrayList<Restaurant> rlist = users.get(in).getRlist();
        if(restaurants.get(r).getMenu().get(itemNo-1).getQuantity()>=quantity) {
            Item temp1 = restaurants.get(r).getMenu().get(itemNo-1);
            Item temp = new Item(temp1.getId(), temp1.getName(), temp1.getPrice(), temp1.getQuantity(), temp1.getCategory(), temp1.getOffer());
            Restaurant rtemp = restaurants.get(r);
            temp.setQuantity(quantity);
            tempOrder.add(temp);
            rlist.add(rtemp);
            users.get(in).setOrder(tempOrder);
            users.get(in).setRlist(rlist);
            System.out.println("Item added to cart.");
        }
        else {
            System.out.println("Decrease the quantity.");
        }

    }

    //Used in Query 2
    private void checkoutCart(Scanner input, int in) {
        if(users.get(in).getRlist().size()==0) {
            System.out.println("Add items first.");
        }
        else {
            Restaurant id1 = users.get(in).getRlist().get(0);
            boolean flag=true;
            for(int i = 1; i < users.get(in).getRlist().size(); i++) {
                if(users.get(in).getRlist().get(i)!=id1) {
                    System.out.println("Cart contains items from different restaurants. Cart is cleared so add items again. ");
                    users.get(in).getRlist().clear();
                    users.get(in).getOrder().clear();
                    flag = false;
                }
            }
            if(flag) {
                System.out.println("Items in Cart - ");
                float value = 0;
                int totalq = 0;
                for (int i = 0; i < users.get(in).getOrder().size(); i++) {
                    System.out.println(users.get(in).getRlist().get(i).getName() + " - " + users.get(in).getOrder().get(i).getName() + " "
                            + users.get(in).getOrder().get(i).getPrice() + " " + users.get(in).getOrder().get(i).getQuantity() + " " +
                            users.get(in).getOrder().get(i).getOffer() + "% off " + users.get(in).getOrder().get(i).getCategory());
                    value += (users.get(in).getOrder().get(i).getPrice()*users.get(in).getOrder().get(i).getQuantity())*((100-users.get(in).getOrder().get(i).getOffer())/100.00);
                    totalq+= users.get(in).getOrder().get(i).getQuantity();
                }
                value = (float) (value*(100-id1.getDiscount())/100.00);
                if(value>100) {
                    value -= id1.getMoreDiscount();
                }
                if(value>200) {
                    value -= users.get(in).getDiscount();
                }
                if(value>users.get(in).getWallet()) {
                    System.out.println("Add more money.");
                }
                else {
                    transactionFee += (1.00 / 100.00) * value;
                    value+= users.get(in).getDeliveryFee();
                    deliveryFee += users.get(in).getDeliveryFee();
                    System.out.println("Delivery Charge - " + users.get(in).getDeliveryFee() + "/-");
                    System.out.println("Total Order Value - INR " + value + "/-");
                    System.out.println("    1) Proceed to checkout");
                    int in1 = input.nextInt();
                    if (in1 == 1) {
                        float rewardsGiven = id1.getRewardVal(value);
                        users.get(in).setWallet(users.get(in).getWallet() - value);
                        id1.setReward(id1.getReward() + rewardsGiven);
                        users.get(in).setReward(users.get(in).getReward() + rewardsGiven);
                        users.get(in).setWallet(users.get(in).getWallet() + rewardsGiven);
                        System.out.println(totalq + " items bought successfully for INR " + value + "/-");
                        users.get(in).getRlist().clear();
                        users.get(in).getOrder().clear();
                    }
                }
            }
        }
    }

    //Query 3
    public void displayUsers(Scanner input) {
        System.out.println("    1) Customer List \n" +
                           "    2) Restaurant List");
        int in = input.nextInt();
        int in1;
        if(in == 1) {
            for(Map.Entry<Integer, User> set : users.entrySet())
            {
                System.out.println("    " + set.getKey() + ") " + set.getValue().getName() + " (" + set.getValue().getClass().getSimpleName() + ")");
            }
            in1 = input.nextInt();
            Common c = users.get(in1);
            c.displayBalance();
        }
        else if(in == 2) {
            for(Map.Entry<Integer, Restaurant> set : restaurants.entrySet())
            {
                System.out.println("    " + set.getKey() + ") " + set.getValue().getName() + " (" + set.getValue().getClass().getSimpleName() + ")");
            }
            in1 = input.nextInt();
            Common c = restaurants.get(in1);
            c.displayBalance();
        }
        else{
            System.out.println("Not Valid... Try Again.");
        }
    }

    //Query 4
    public void displayCompanyDetails(Scanner input) {
        System.out.println("Total Company Balance - INR " + this.getTransactionFee() + "/-");
        System.out.println("Total Delivery Charges Collected - INR " + this.getDeliveryFee() + "/-");
    }
}

public class Main {
    public static void main(String[] args) {
	// write your code here
        Zotato z = new Zotato();
        z.initialize(); //Hardcoded Inputs
        Scanner input = new Scanner(System.in);
        int in = -1;
        while(in!=5){
            System.out.println(
                    "Welcome to Zotato:\n" +
                            "    1) Enter as Restaurant Owner \n" +
                            "    2) Enter as Customer \n" +
                            "    3) Check User Details \n" +
                            "    4) Company Account Details \n" +
                            "    5) Exit"
                              );
            in = input.nextInt();
            switch (in) {
                case 1 -> z.asRestaurant(input);
                case 2 -> z.asUser(input);
                case 3 -> z.displayUsers(input);
                case 4 -> z.displayCompanyDetails(input);
            }
        }
    }
}
