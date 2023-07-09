import java.util.Scanner;
import java.util.Random;

class A{
    int a = 0;
    int plen = 0;
    String q;
    float ch = 0;
}

interface disp{
    static void display(){

    }
}

class category{
    String catname;
    int catid;
    category(String catname, int catid){
        this.catname = catname;
        this.catid = catid;
    }

    static boolean check(category[] c, int n, String a, int b){
        for(int i= 0; i < n; i++){
            if(c[i].catname.equalsIgnoreCase(a)){
                System.out.println("Category with name already exists.");
                return false;
            }
            else if(c[i].catid == b){
                System.out.println("Category with id already exists.");
                return false;
            }
        }
        return true;
    }
    

    static product[] deletecat(category[] c, int n, String a, int b,product[] p, int m, A need){
        product[] p1 = new product[50];
        int num = 0;
        for(int i= 0; i < n; i++){
            if(c[i].catname.equalsIgnoreCase(a) && c[i].catid == b){
                for(int j = i; j<n-1; j++){
                    c[j] = c[j+1];
                }
                for(int k = 0; k < m; k++){
                    if((int)(p[k].proid) != b){
                        p1[num] = p[k];
                        num += 1;
                        need.plen += 1;
                    }
                }
                need.a = 1;
                return p1;
            }
        }
        need.a = 0;
        return p;
    }

    static int checkproductnum (product[] p, int n, int catid){
        int count = 0;
        for(int i = 0; i < n; i++){
            if((int)(p[i].proid) == catid){
                count += 1;
            }
        }
        return count;
    }
}

class product implements disp{
    String proname;
    float proid,proprice,ndis,edis,pdis;
    String procontains;
    int proquan;
    public product(String proname,float proid,float proprice,String procontains,int proquan){
        this.proname = proname;
        this.proid = proid;
        this.proprice = proprice;
        this.procontains = procontains;
        this.proquan = proquan;
        this.ndis = 0;
        this.pdis = 0;
        this.edis = 0;
    }

    static boolean check(product[] p,category[] c, int m, int n, String a, float b,int d){
        for(int k = 0; k < n; k++){
            if(c[k].catid == d){
                if(c[k].catid == (int)b){
                    for(int i= 0; i < m; i++){
                        if(p[i].proname.equalsIgnoreCase(a)){
                            System.out.println("Product with this name already exists.");
                            return false;
                        }
                        else if(p[i].proid == b){
                            System.out.println("Product with this id already exists.");
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        System.out.println("Category/Product id invalid.");
        return false;
    }

    static int deletepro(product[] p,category[] c, int m, int n, String a, float b,int d,A need){
        for(int k = 0; k < n; k++){
            if(c[k].catid == d && c[k].catid == (int)b){
                for(int i= 0; i < m; i++){
                    if(p[i].proname.equalsIgnoreCase(a) && p[i].proid == b){
                        for(int j = i; j<m-1; j++){
                            p[j] = p[j+1];
                        }
                        need.q = c[k].catname;
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    static int prodeal (product[] p, int n, float id){
        for(int i = 0; i < n; i++){
            if(p[i].proid == id){
                return i;
            }
        }
        return -1;
    }

    static void discount(product[] p, int n, float pid ,float dis1, float dis2, float dis3){
        for(int i = 0; i < n; i++){
            if(p[i].proid == pid){
                p[i].ndis = dis1;
                p[i].pdis = dis2;
                p[i].edis = dis3;
                return;
            }
        }
    }

    static float getprice(product[] p, int n, float pid){
        for(int i = 0; i < n; i++){
            if(p[i].proid == pid){
                return p[i].proprice;
            }
        }
        return -1;
    }

    static void display(product[] p, int n, float proid){
        for(int i = 0; i<n ; i++){
            if(p[i].proid == proid){
                System.out.println("Product name: " + p[i].proname);
                System.out.println("Product id: " + p[i].proid);
                System.out.println("Product price: Rs." + p[i].proprice);
                System.out.println("Product contains: " + p[i].procontains);
                System.out.println("Product Quantity in stock: " + p[i].proquan);
                System.out.println();
            }
        }
    }    


    static void display(product[] p, int n){
        for(int i = 0; i<n ; i++){
            System.out.println("Product " + (i+1) + " :");
            System.out.println("Product name: " + p[i].proname);
            System.out.println("Product id: " + p[i].proid);
            System.out.println("Product price: Rs." + p[i].proprice);
            System.out.println("Product contains: " + p[i].procontains);
            System.out.println("Product Quantity in stock: " + p[i].proquan);
            System.out.println();
        }   
    }
    
    static void updateproductcount(product[] cart, int n, int[] pqty ,product[] p,int pronum, giveawaydeal[] gc, int gcn, giveawaydeal[] g, int gn){
        for(int i = 0; i<n; i++){
            for(int j = 0; j < pronum; j++){
                if(cart[i].proid == p[j].proid){
                    p[j].proquan -= pqty[i];
                }
            }
        }

        for(int i = 0; i<gcn; i++){
            for(int j = 0; j < gn; j++){
                if(gc[i].product1 == g[j].product1 && gc[i].product2 == g[j].product2){
                    product pp1 = product.quanset(p,pronum,g[j].product1);
                    product pp2 = product.quanset(p,pronum,g[j].product2);
                    pp1.proquan -= 1;
                    pp2.proquan -= 1;
                }
            }
        }
        return;
    }

    static int productquantity (product[] p, int pronum, float id){
        for(int i = 0; i < pronum; i++){
            if(p[i].proid == id){
                return p[i].proquan;
            }
        }
        return 0;
    }

    static product quanset (product[] p, int pronum, float id){
        for(int i = 0; i < pronum; i++){
            if(p[i].proid == id){
                return p[i];
            }
        }
        return p[0];
    }
}

class giveawaydeal implements disp{
    float product1;
    float product2;
    float price;

    public giveawaydeal(float product1,float product2,float price){
        this.product1 = product1;
        this.product2 = product2;
        this.price = price;
    }

    static int checkprod (giveawaydeal[] g, int n, float id){
        for(int i =0; i<n; i++){
            if(g[i].product1==id || g[i].product2 == id){
                return 1;
            }
        }
        return 0;
    }

    static int deletedeal(giveawaydeal[] g, int n, float id){
        int count = 0;
        for(int i= 0; i < n; i++){
            if(g[i].product1==id || g[i].product2 == id){
                for(int j = i; j<n-1; j++){
                    g[j] = g[j+1];
                }
                count += 1;
            }
        }
        return count;
    }

    static void display(giveawaydeal[] g, int num, product[] p, int n){
        for(int i = 0; i<num; i++){
            System.out.println("Deal " + (i+1));
            System.out.println("Product 1: ");
            product.display(p, n, g[i].product1);
            System.out.println("Product 2: ");
            product.display(p, n, g[i].product2);
            System.out.println("Combined Price for the products in deal: " + g[i].price);
        }
    }
}

class giveawaydeal1 extends giveawaydeal{
    
    public giveawaydeal1(){
        super(0,0,0);
    }

    static int deletedeal(giveawaydeal[] g, int n, int id){
        int count = 0;
        for(int i= 0; i < n; i++){
            if((int)(g[i].product1)==id || (int)(g[i].product2)==id){
                for(int j = i; j<n-1; j++){
                    g[j] = g[j+1];
                }
                count += 1;
            }
        }
        return count;
    }
}

interface maxnum{
    static int maxval(){
        return 0;
    }
    
}

class customer implements maxnum{
    String name,email,pass;
    int age, bal;
    String status;
    long phone;
    product[] cart;
    int cartnum, coupnum;
    int[] cartqty;
    int[] coupon;
    giveawaydeal[] deal;
    int dealnum;

    public customer(String name, int age, long phone, String email, String pass){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
        this.status = "normal";
        this.bal = 1000;
        this.cart= new product[100];
        this.cartnum = 0;
        this.cartqty = new int[100];
        this.coupon = new int[50];
        this.coupnum = 0;
        this.deal = new giveawaydeal[50];
        this.dealnum = 0;
    }

    static boolean checknotexist(customer[] c, int n, String name, String email, long phone){
        for(int i = 0; i < n; i++){
            if(c[i].name.equalsIgnoreCase(name) && c[i].email.equalsIgnoreCase(email) && c[i].phone == phone){
                return false;                
            }
        }
        return true;
    }

    static int login(customer[] c, int n, String name, String email, String pass){
        for(int i = 0; i < n; i++){
            if(c[i].name.equalsIgnoreCase(name) && c[i].email.equalsIgnoreCase(email) && c[i].pass.equals(pass)){
                return i;                
            }
        }
        return -1;
    }

    static int available (product[] p, int n, float id){
        for (int i = 0; i < n; i++){
            if(p[i].proid == id){
                return i;
            }
        }
        return -1;
    }

    static void displaycart(product[] p, int n,int[] q){
        for(int i = 0; i<n ; i++){
            System.out.println("Product " + (i+1) + " :");
            System.out.println("Product name: " + p[i].proname);
            System.out.println("Product id: " + p[i].proid);
            System.out.println("Product price: " + p[i].proprice);
            System.out.println("Product contains: " + p[i].procontains);
            System.out.println("Quantity: " + q[i]);
            System.out.println("Total Cost: " + p[i].proprice*q[i]);
            System.out.println();
        }   
    }

    static float totalcost (product[] p, int n, int[] q, float disc, String cat, giveawaydeal[] g, int gn, A need){
        float money = 0;
        need.ch = 0;
        for(int i = 0; i < n; i++){
            if(cat.equalsIgnoreCase("normal") && p[i].ndis != 0){
                disc = Math.max(disc, ((p[i].ndis)/100));
            }
            else if(cat.equalsIgnoreCase("prime") && p[i].pdis != 0){
                disc = Math.max(disc, ((p[i].pdis)/100));
            }
            else if(cat.equalsIgnoreCase("normal") && p[i].edis != 0){
                disc = Math.max(disc, ((p[i].edis)/100));
            }
            need.ch += (p[i].proprice*q[i]);
            money += (p[i].proprice*q[i])*(1-disc);
        }
        for(int i = 0; i < gn; i++){
            need.ch += g[i].price;
            money += g[i].price;
        }
        return money;
    }

    static void displaycoupons(int[] arr, int n){
        System.out.println("Available Coupons: ");
        for(int i = 0; i<n; i++){
            System.out.println(arr[i] + "% discount.");
        }
    }

    static int maxval(int[] arr){
        int max = arr[0];
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }

    static void deletecoupon(int[] arr, int n, int m){
        for(int i= 0; i < n; i++){
            if(arr[i] == m){
                for(int j = i; j<n-1; j++){
                    arr[j] = arr[j+1];
                }
                return;
            }
        }
    }

    static boolean checkavailability(product[] p, int pronum, product[] pc, int pcnum, giveawaydeal[] g, int givenum, giveawaydeal[] gc, int gcnum, int[] q){
        for(int i = 0; i<pcnum; i++){
            for(int j = 0; j < pronum; j++){
                if(pc[i].proid == p[j].proid){
                    if((p[j].proquan - q[i]) < 0){
                        return false;
                    }
                }
            }
        }

        for(int i = 0; i<gcnum; i++){
            for(int j = 0; j < givenum; j++){
                if(gc[i].product1 == g[j].product1 && gc[i].product2 == g[j].product2){
                    int p1 = product.productquantity(p, pronum, g[j].product1);
                    int p2 = product.productquantity(p, pronum, g[j].product2);
                    if(p1 < 1 || p2 < 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

public class Assignment2 {
    public static void main(String[] args){
        A need = new A();
        Scanner input = new Scanner (System.in);
        Scanner input1 = new Scanner (System.in);
        System.out.print("Set Admin Username: ");
        String adminuser = input1.nextLine();
        System.out.print("Set Admin Password: ");
        String adminpass = input1.nextLine();
        category[] cat = new category[50];
        product[] prod = new product[50];
        giveawaydeal[] give = new giveawaydeal[50];
        customer[] cust = new customer[50];
        int catnum = 0,pronum = 0, givenum = 0,custnum =0;
        Random random = new Random();
        while(true){
            System.out.println("\nWELCOME TO FLIPZON\n");
            System.out.println("(1) Enter as Admin");
            System.out.println("(2) Explore the Product Catalog");
            System.out.println("(3) Show Available Deals");
            System.out.println("(4) Enter as Customer");
            System.out.println("(5) Exit the Application");

            int choice = input.nextInt();

            if(choice == 1){
                while(true){
                    System.out.print("Admin Username: ");
                    String username = input1.nextLine();
                    System.out.print("Admin Password (Case Sensitive): ");
                    String password = input1.nextLine();
                    if(username.equalsIgnoreCase(adminuser) && password.equals(adminpass)){
                        System.out.println("\nWelcome " + adminuser);
                        break;
                    }
                    else{
                        System.out.println("Error: Invalid Username or Password!! Please Try Again!");
                    }
                }

                while(true){
                    System.out.println("\nPlease choose any one of the following actions:\n");
                    System.out.println("1) Add category");
                    System.out.println("2) Delete category");
                    System.out.println("3) Add Product");
                    System.out.println("4) Delete Product");
                    System.out.println("5) Set Discount on Product");
                    System.out.println("6) Add giveaway deal");
                    System.out.println("7) Back");

                    int adchoice = input.nextInt();

                    if (adchoice == 1){
                        System.out.print("Category name: ");
                        String catname = input1.nextLine();
                        System.out.print("Category id: ");
                        int catid = input.nextInt();
                        if(category.check(cat,catnum,catname,catid)){
                            cat[catnum] = new category(catname, catid);
                            catnum += 1;
                            System.out.println("Category Created.");

                            System.out.println("Add a product.");
                            System.out.print("Product name: ");
                            String proname = input1.nextLine();
                            System.out.print("Product id: ");
                            float proid = input.nextFloat();
                            System.out.print("Product Quantity: ");
                            int proquan = input.nextInt();
                            System.out.print("Product price(in Rs.): ");
                            float proprice = input.nextFloat();
                            System.out.print("Product contains: ");
                            String procontains = input1.nextLine();
                            if(product.check(prod,cat, pronum, catnum, proname, proid,catid)){
                                prod[pronum] = new product(proname, proid, proprice, procontains, proquan);
                                pronum += 1;
                                System.out.println("Product added.");
                            }
                            else{
                                prod = category.deletecat(cat,catnum,catname,catid,prod,pronum,need);
                                pronum = need.plen;
                                need.plen = 0;
                                catnum -= 1;
                                System.out.println("Unable to create category due to invalid details, please try again!");
                            }
                        }
                    }

                    else if (adchoice == 2){
                        if(catnum>0){
                            System.out.print("Category name: ");
                            String catname = input1.nextLine();
                            System.out.print("Category id: ");
                            int catid = input.nextInt();
                            prod = category.deletecat(cat,catnum,catname,catid,prod,pronum,need);
                            pronum = need.plen;
                            need.plen = 0;
                            
                            if(need.a == 1){
                                int x = giveawaydeal1.deletedeal(give, givenum, catid);
                                givenum -= x;
                                System.out.println("Deals related to Products in Category id " + catid + " (if any) deleted.");
                                System.out.println("Category Deleted.");
                                catnum -= 1;
                            }
                            else{
                                System.out.println("Invalid Details.");
                            }
                        }
                        else{
                            System.out.println("No categories to delete!");
                        }
                    }

                    else if (adchoice == 3){
                        System.out.print("Category id: ");
                        int catid = input.nextInt();
                        System.out.print("Product name: ");
                        String proname = input1.nextLine();
                        System.out.print("Product id: ");
                        float proid = input.nextFloat();
                        System.out.print("Product Quantity: ");
                        int proquan = input.nextInt();
                        System.out.print("Product price: ");
                        float proprice = input.nextFloat();
                        System.out.print("Product contains: ");
                        String procontains = input1.nextLine();
                        if(product.check(prod,cat, pronum, catnum, proname, proid,catid)){
                            prod[pronum] = new product(proname, proid, proprice, procontains,proquan);
                            pronum += 1;
                            System.out.println("Product added.");
                        }
                    }

                    else if (adchoice == 4){
                        System.out.print("Category id: ");
                        int catid = input.nextInt();
                        System.out.print("Product name: ");
                        String proname = input1.nextLine();
                        System.out.print("Product id: ");
                        float proid = input.nextFloat();
                        int x = product.deletepro(prod, cat, pronum, catnum, proname, proid, catid,need);
                        if(x == 1){
                            pronum -= 1;
                            System.out.println("Product deleted.");
                            int z = giveawaydeal.checkprod(give, givenum, proid);
                            if(z == 1){
                                int w = giveawaydeal.deletedeal(give, givenum,proid);
                                givenum -= w;
                                System.out.println("Deals related to Product id " + proid + " (if any) deleted.");
                            }
                            int y = category.checkproductnum(prod, pronum, catid);
                            if(y == 0){
                                System.out.println("No products left in the category.\nDeleting the Category!");
                                prod = category.deletecat(cat,catnum,need.q,catid,prod,pronum,need);
                                pronum = need.plen;
                                need.plen = 0;
                                
                                if(need.a == 1){
                                    System.out.println("Category Deleted.");
                                    catnum -= 1;
                                }
                            }
                        }
                        else{
                            System.out.println("Invalid Details!");
                        }
                    }

                    else if (adchoice == 5){
                        System.out.println("Give product id you want to add discount for: ");
                        System.out.print("Product id: ");
                        float pid = input.nextFloat();
                        int x = product.prodeal(prod, pronum, pid);
                        if(x > -1){
                            System.out.print("Discount for normal customers(in %): ");
                            float dis1 = input.nextFloat();
                            System.out.print("Discount for Prime customers(in %): ");
                            float dis2 = input.nextFloat();
                            System.out.print("Discount for Elite customers(in %): ");
                            float dis3 = input.nextFloat();
                            product.discount(prod, pronum, pid, dis1, dis2, dis3);
                            System.out.println("Discount deal set!");
                        }
                        else{
                            System.out.println("Product with this id not found.");
                        }
                    }

                    else if (adchoice == 6){
                        System.out.println("Giveaway Deal.");
                        System.out.print("Product 1 id: ");
                        float pid1 = input.nextFloat();
                        System.out.print("Product 2 id: ");
                        float pid2 = input.nextFloat();
                        int x = product.prodeal(prod, pronum, pid1);
                        int y = product.prodeal(prod, pronum, pid2);
                        if(x > -1 && y > -1){
                            System.out.print("combined price: ");
                            float price = input.nextFloat();
                            float p2 = product.getprice(prod, pronum, pid2);
                            float p1 = product.getprice(prod, pronum, pid1);
                            if(p1+p2 > price){
                                System.out.println("Giveaway Deal Set.");
                                give[givenum] = new giveawaydeal(pid1,pid2,price);
                                givenum += 1;
                            }
                            else{
                                System.out.println("Deal price not less than combined price!");
                            }
                        }
                        else{
                            System.out.println("Invalid id for product(s)");
                        }
                    }

                    else if (adchoice == 7){
                        break;
                    }

                    else {
                        System.out.println("Invalid Choice!");
                    }
                    
                }
            }

            else if(choice == 2){
                if(pronum>0){
                    product.display(prod, pronum);
               }
               else{
                System.out.println("No Products to display!");
               }
            }

            else if(choice == 3){
                if(givenum > 0){
                    giveawaydeal.display(give, givenum, prod, pronum);
                }
                else{
                    System.out.println("No deals available for now.");
                }
            }

            else if(choice == 4){
                while(true){
                    System.out.println("(1) Sign up");
                    System.out.println("(2) Log in");
                    System.out.println("(3) Back");
                    
                    int cchoice = input.nextInt();

                    if(cchoice == 1){
                        System.out.print("Name: ");
                        String custname = input1.nextLine();
                        System.out.print("Age: ");
                        int custage = input.nextInt();
                        System.out.print("Phone no.: ");
                        long custphone = input.nextLong();
                        System.out.print("Email: ");
                        String custemail = input1.nextLine();
                        System.out.print("Password: ");
                        String custpass = input1.nextLine();
                        if(customer.checknotexist(cust, custnum, custname, custemail, custphone)){
                            cust[custnum] = new customer(custname, custage, custphone, custemail, custpass);
                            custnum += 1;
                            System.out.println("Customer " + custname + " successfully registered.");
                        }
                        else{
                            System.out.println("Customer already exists. Please Login instead of Signup!");
                        }
                    }

                    else if(cchoice == 2){
                        if(custnum > 0){
                            System.out.print("Name: ");
                            String custname = input1.nextLine();
                            System.out.print("Email: ");
                            String custemail = input1.nextLine();
                            System.out.print("Password (Case Sensitive): ");
                            String custpass = input1.nextLine();
                            int x = customer.login(cust, custnum, custname, custemail, custpass);
                            if(x > -1){
                                while(true){
                                    System.out.println("\nWelcome " + custname);
                                    System.out.println();
                                    System.out.println("1) browse products");
                                    System.out.println("2) browse deals");
                                    System.out.println("3) add a product to cart");
                                    System.out.println("4) add products in deal to cart");
                                    System.out.println("5) view coupons");
                                    System.out.println("6) check account balance");
                                    System.out.println("7) view cart");
                                    System.out.println("8) empty cart");
                                    System.out.println("9) checkout cart");
                                    System.out.println("10) upgrade customer status");
                                    System.out.println("11) Add amount to wallet");
                                    System.out.println("12) back");
                                    int custchoice = input.nextInt();
                                    if(custchoice == 1){
                                        if(pronum > 0){
                                            product.display(prod, pronum);
                                        }
                                        else{
                                            System.out.println("No Products to display!");
                                        }
                                    }

                                    else if(custchoice == 2){
                                        if(givenum > 0){
                                            giveawaydeal.display(give, givenum, prod, pronum);
                                        }
                                        else{
                                            System.out.println("No Deals available to display!");
                                        }
                                    }

                                    else if(custchoice == 3){
                                        if(pronum > 0){
                                            System.out.print("Product id: ");
                                            float id = input.nextFloat();

                                            System.out.print("Quantity: ");
                                            int qty = input.nextInt();
                                            int o = customer.available(prod, pronum, id);
                                            if(o > -1){
                                                if(prod[o].proquan - qty >= 0){
                                                    cust[x].cart[cust[x].cartnum] = prod[o];
                                                    cust[x].cartqty[cust[x].cartnum] = qty;
                                                    cust[x].cartnum += 1;
                                                    System.out.println("Product added to cart.");
                                                }
                                                else{
                                                    if(qty == 1){
                                                        System.out.println("Sorry, the product is out of stock!");
                                                    }
                                                    else{
                                                        System.out.println("Sorry, the product is not available in the quantity you need..");
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Invalid Product id!");
                                            }
                                        }
                                        else{
                                            System.out.println("No products available to add.");
                                        }
                                        
                                    }

                                    else if(custchoice == 4){
                                        if(givenum > 0){
                                            System.out.println("Deal number you want to add?: ");
                                            int dealadd = input.nextInt();
                                            if(dealadd <= givenum && dealadd > 0){
                                                cust[x].deal[cust[x].dealnum] = give[dealadd-1];
                                                cust[x].dealnum += 1;
                                                System.out.println("Deal added to cart!");
                                            }
                                            else{
                                                System.out.println("Invalid deal number.");
                                            }
                                        }
                                        else{
                                            System.out.println("No Deals available to add to cart!");
                                        }
                                    }

                                    else if(custchoice == 5){
                                        if(cust[x].coupnum > 0){
                                            cust[x].displaycoupons(cust[x].coupon, cust[x].coupnum);
                                        }
                                        else{
                                            System.out.println("No available coupons for your account!");
                                        }
                                    }

                                    else if(custchoice == 6){
                                        System.out.println("Current Account Balance : Rs." + cust[x].bal);
                                    }

                                    else if(custchoice == 7){
                                        if(cust[x].cartnum > 0 || cust[x].dealnum > 0){
                                            customer.displaycart(cust[x].cart, cust[x].cartnum, cust[x].cartqty);
                                            if(cust[x].dealnum > 0){
                                                System.out.println("Deals in cart: ");
                                                giveawaydeal.display(cust[x].deal, cust[x].dealnum, prod, pronum);
                                            }
                                        }
                                        else{
                                            System.out.println("cart empty!");
                                        }
                                    }

                                    else if(custchoice == 8){
                                        if(cust[x].cartnum > 0){
                                            System.out.println("Cart Emptied!");
                                            cust[x].cartnum = 0;
                                            cust[x].cart = new product[100];
                                            cust[x].deal = new giveawaydeal[50];
                                            cust[x].dealnum = 0;
                                        }
                                        else{
                                            System.out.println("Cart already empty!");
                                            cust[x].cartnum = 0;
                                        }
                                    }

                                    else if(custchoice == 9){
                                        if(cust[x].cartnum > 0 || cust[x].dealnum > 0){
                                            if(customer.checkavailability(prod, pronum, cust[x].cart, cust[x].cartnum, give, givenum, cust[x].deal, cust[x].dealnum, cust[x].cartqty)){
                                                float charge = 0;
                                                double delcharge = 0;
                                                String delday = "days";
                                                if(cust[x].status.equalsIgnoreCase("normal")){
                                                    float di = 0;
                                                    if(cust[x].coupnum>0){
                                                        int dis = customer.maxval(cust[x].coupon) ;
                                                        di = (Math.max((int)di, dis));
                                                        if((int)di == dis){
                                                            customer.deletecoupon(cust[x].coupon, cust[x].coupnum , dis);
                                                            cust[x].coupnum -= 1;
                                                        }
                                                    }
                                                    di = di/100;
                                                    charge = customer.totalcost(cust[x].cart, cust[x].cartnum, cust[x].cartqty,di,"normal", cust[x].deal, cust[x].dealnum,need);
                                                    delcharge = 100 + 0.05*need.ch;
                                                    delday = "7-10 days";
                                                }
                                                else if(cust[x].status.equalsIgnoreCase("prime")){
                                                    float di = 5;
                                                    if(cust[x].coupnum>0){
                                                        int dis = customer.maxval(cust[x].coupon);
                                                        di = (Math.max((int)di, dis));
                                                        if((int)di == dis){
                                                            customer.deletecoupon(cust[x].coupon, cust[x].coupnum , dis);
                                                            cust[x].coupnum -= 1;
                                                        }
                                                    }
                                                    di = di/100;
                                                    charge = customer.totalcost(cust[x].cart, cust[x].cartnum, cust[x].cartqty,di,"prime", cust[x].deal, cust[x].dealnum,need);
                                                    delcharge = 100 + 0.02*need.ch;
                                                    delday = "3-6 days";
                                                }
                                                else if(cust[x].status.equalsIgnoreCase("elite")){
                                                    float di = 10;
                                                    if(cust[x].coupnum>0){
                                                        int dis = customer.maxval(cust[x].coupon);
                                                        di = (Math.max((int)di, dis));
                                                        if((int)di == dis){
                                                            customer.deletecoupon(cust[x].coupon, cust[x].coupnum , dis);
                                                            cust[x].coupnum -= 1;
                                                        }
                                                    }
                                                    di = di/100;
                                                    charge = customer.totalcost(cust[x].cart, cust[x].cartnum, cust[x].cartqty,di,"elite", cust[x].deal, cust[x].dealnum,need);
                                                    delcharge = 100;
                                                    delday = "2 days";
                                                }

                                                if((charge+delcharge <= cust[x].bal)){
                                                    System.out.println("Proceeding to checkout. Deatils: ");
                                                    if(cust[x].cartnum>0){
                                                        System.out.println("Products in cart: \n");
                                                        customer.displaycart(cust[x].cart, cust[x].cartnum, cust[x].cartqty);
                                                    }
                                                    if(cust[x].dealnum>0){
                                                        System.out.println("Deals in cart: \n");
                                                        giveawaydeal.display(cust[x].deal, cust[x].dealnum, prod, pronum);
                                                    }
                                                    System.out.println("Total cost {Products + Deals} before applying discounts: Rs."+need.ch);
                                                    System.out.println("Total Cost {Products + Deals} after applying discount : Rs." + charge);
                                                    System.out.println("No discount is applied on giveaway deals!..");
                                                    System.out.println("Delivery Charge: Rs." + delcharge);
                                                    System.out.println("Net Payable Amount: Rs." + (charge+delcharge));
                                                    System.out.println("Order Placed. Your order will be delivered within " + delday);
                                                    product.updateproductcount(cust[x].cart, cust[x].cartnum, cust[x].cartqty,prod,pronum,cust[x].deal,cust[x].dealnum, give, givenum);
                                                    cust[x].bal -= (charge+delcharge);
                                                    cust[x].cartnum = 0;
                                                    cust[x].cart = new product[100];
                                                    cust[x].deal = new giveawaydeal[50];
                                                    cust[x].dealnum = 0;
                                                    delcharge = 0;
                                                    need.ch = 0;
                                                    if(cust[x].status.equalsIgnoreCase("prime")){
                                                        int u = random.nextInt(1,3);
                                                        System.out.println(u + " Coupons added to your account!");
                                                        for(int i = 0; i < u; i++){
                                                            int co = random.nextInt(5,16);
                                                            cust[x].coupon[cust[x].coupnum] = co;
                                                            cust[x].coupnum += 1;
                                                        }
                                                        
                                                    }
                                                    else if(cust[x].status.equalsIgnoreCase("elite")){
                                                        int u = random.nextInt(2,5);
                                                        System.out.println(u + " Coupons added to your account!");
                                                        for(int i = 0; i < u; i++){
                                                            int co = random.nextInt(5,16);
                                                            cust[x].coupon[cust[x].coupnum] = co;
                                                            cust[x].coupnum += 1;
                                                        }
                                                        
                                                    }
                                                }
                                                else{
                                                    System.out.println("Insufficient Balance in your account!! Please try again!");
                                                }
                                            }
                                            
                                        }
                                        else{
                                            System.out.println("No product(s) or deal(s) present in cart!");
                                        }
                                    }

                                    else if(custchoice == 10){
                                        System.out.println("Current Status : " + cust[x].status);
                                        if(cust[x].status.equalsIgnoreCase("elite")){
                                            System.out.println("Already at highest status. Can't Upgrade!");
                                        }
                                        else{
                                            System.out.println("Once purchased, money won't be refunded!\nCost of Prime membership: Rs. 200\nCost of Elite membership: Rs. 300");
                                            System.out.print("Upgrade to: ");
                                            String upto = input.next();
                                            if(cust[x].status.equalsIgnoreCase("normal") && upto.equalsIgnoreCase("prime")){
                                                if(cust[x].bal>200){
                                                    cust[x].bal -= 200;
                                                    cust[x].status = "prime";
                                                    System.out.println("Status updated to Prime");
                                                }
                                            }
                                            else if(cust[x].status.equalsIgnoreCase("normal") && upto.equalsIgnoreCase("elite")){
                                                if(cust[x].bal>300){
                                                    cust[x].bal -= 300;
                                                    cust[x].status = "elite";
                                                    System.out.println("Status updated to Elite");
                                                }
                                            }

                                            else if(cust[x].status.equalsIgnoreCase("prime") && upto.equalsIgnoreCase("elite")){
                                                if(cust[x].bal>300){
                                                    cust[x].bal -= 300;
                                                    cust[x].status = "elite";
                                                    System.out.println("Status updated to Elite");
                                                }
                                            }

                                            else if(cust[x].status.equalsIgnoreCase("prime") && upto.equalsIgnoreCase("normal")){
                                                System.out.println("Can't go backward from prime to normal!");
                                            }

                                            else if(cust[x].status.equalsIgnoreCase(upto)){
                                                System.out.println("Already a " + cust[x].status + " member!");
                                            }

                                            else{
                                                System.out.println("invalid choice of status.");
                                            }

                                        }
                                    }

                                    else if(custchoice == 11){
                                        System.out.print("Enter Amount to add: ");
                                        int addbal = input.nextInt();
                                        if(addbal>=0){
                                            cust[x].bal += addbal;
                                            System.out.println("Balance added!\nCurrent Balance = "+cust[x].bal);
                                        }
                                        else{
                                            System.out.println("Enter a valid amount");
                                        }
                                    }

                                    else if(custchoice == 12){
                                        System.out.println("Bye " + custname);
                                        System.out.println("Logging out.");
                                        break;
                                    }

                                    else{
                                        System.out.println("Invalid choice. Try again!");
                                    }

                                } 
                            }
                            else{
                                System.out.println("Invalid details. try again");
                            }
                        }
                        else{
                            System.out.println("No existing customers in database. Please Signup first!");
                        }
                    }

                    else if(cchoice ==3){
                        break;
                    }

                    else{
                        System.out.println("Invalid choice! Try again.");
                    }
                
                }
            }

            else if(choice == 5){
                System.out.println("Thanks for using the application.");
                break;
            }

            else{
                System.out.println("Invalid Choice!");
            }
        }
    }
}