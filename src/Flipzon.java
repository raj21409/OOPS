import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


interface Cart{
    MyCart AddProductToCart(int z);
    int AddProductsInDealToCart(int z, Admin a);
    void ViewCart(int z, Admin a);
    void EmptyCart(int z, Admin a);
}
class Admin{
    Scanner sc = new Scanner(System.in);
    String Username;
    String RollNo;
    double DFE = 10;
    double DFN = 0;
    double DFP = 5;
    ArrayList<Category> Categories = new ArrayList<>();
    ArrayList<Customer> Customers = new ArrayList<>();
    ArrayList<Product> DiscountedProducts = new ArrayList<>();
    ArrayList<String> PID_1 = new ArrayList<>();
    ArrayList<String> PID_2 = new ArrayList<>();
    ArrayList<Double> CPP = new ArrayList<>();
    ArrayList<Double> CPE = new ArrayList<>();
    ArrayList<Double> CPN = new ArrayList<>();

    public void setUsername(String username) {
        Username = username;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }


    public void AddCategory(Admin a){
        System.out.println("Enter Category ID: ");
        int CategoryID = sc.nextInt();
        sc.nextLine();
        int aaa = 0;
        for (Category category : this.Categories) {
            if (category.CategoryID == CategoryID) {
                System.out.println("Dear Admin, the category ID is already used!!! Please set a different and a unique category ID");
                aaa = 1;
            }
        }


        if(aaa == 0){
            System.out.println("Enter Category Name: ");
            String CategoryName = sc.nextLine();
            Category c = new Category();
            c.setCategoryName(CategoryName); c.setCategoryID(CategoryID);
            a.Categories.add(c);
            System.out.println("Adding a Product--");
            System.out.println("Product Name: ");
            String ProductName = sc.nextLine();
            System.out.println("Product ID: ");
            String ProductID = sc.nextLine();
            System.out.println("Product Price: ");
            int PriceOfProduct = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Details");
            String Details = sc.nextLine();
            Product p = new Product();
            p.setNameOfProduct(ProductName); p.setProductID(ProductID); p.setPriceOfProduct(PriceOfProduct);p.setDetails(Details);
            int x = a.Categories.indexOf(c);
            a.Categories.get(x).Products.add(p);
        }
    }

    public void DeleteCategory(){
        System.out.print("Enter name of Category: ");
        String CategoryName = sc.nextLine();
        System.out.print("Enter the Category ID: ");
        int CategoryID = sc.nextInt();
        sc.nextLine();
        int x = 0;
        for(int i = 0;i<this.Categories.size();i++){
            if(this.Categories.get(i).CategoryID == CategoryID && Objects.equals(this.Categories.get(i).CategoryName, CategoryName)){
                this.Categories.remove(i);
                x = 1;
                break;
            }
        }
        if(x==0){
            System.out.println("No such category exists");
        }
    }

    public void AddProduct() {
        int aaa = 0;
        System.out.print("Enter name of Category: ");
        String CategoryName = sc.nextLine();
        System.out.print("Enter the Category ID: ");
        int CategoryID = sc.nextInt();
        sc.nextLine();
        for (Category category : this.Categories) {
            if (category.CategoryID == CategoryID) {
                System.out.println("Adding a Product--");
                System.out.println("Product Name: ");
                String ProductName = sc.nextLine();
                System.out.println("Product ID: ");
                String ProductID = sc.nextLine();
                System.out.println("Product Price: ");
                int PriceOfProduct = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Details: ");
                String Details = sc.nextLine();
                Product p = new Product();
                p.setNameOfProduct(ProductName);
                p.setProductID(ProductID);
                p.setPriceOfProduct(PriceOfProduct);
                p.setDetails(Details);
                category.Products.add(p);
                aaa = 1;
            }
        }
        if(aaa == 0){
            Category c = new Category();
            c.setCategoryName(CategoryName); c.setCategoryID(CategoryID);
            this.Categories.add(c);
            System.out.println("Adding a Product--");
            System.out.println("Product Name: ");
            String ProductName = sc.nextLine();
            System.out.println("Product ID: ");
            String ProductID = sc.nextLine();
            System.out.println("Product Price: ");
            int PriceOfProduct = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Details: ");
            String Details = sc.nextLine();
            Product p = new Product();
            p.setNameOfProduct(ProductName); p.setProductID(ProductID); p.setPriceOfProduct(PriceOfProduct);p.setDetails(Details);
            c.Products.add(p);
        }
    }

    public void DeleteProduct(){
        System.out.print("Enter the name of Category: ");
        String CategoryName = sc.nextLine();
        System.out.print("Enter the Product ID: ");
        String ProductId = sc.nextLine();
        int x = -1; int y =0;
        for(int i = 0;i<this.Categories.size();i++){
            if(Objects.equals(this.Categories.get(i).CategoryName, CategoryName)){
                x = i;
                for(int j = 0;j<this.Categories.get(i).Products.size();j++){
                    if(Objects.equals(this.Categories.get(i).Products.get(j).ProductID, ProductId)){
                        this.Categories.get(i).Products.remove(j);
                        y=1;
                    }
                }
            }
        }
        if(y==0){
            System.out.println("No such category or product exists");
        }
        if(this.Categories.get(x).Products.size() == 0 && y!=0){
            System.out.println("Would you like to enter product in this Category or remove this Category as it is empty.");
            System.out.println("Press Y to Enter Product / Press N to remove Category");
            String cc = sc.nextLine();
            if(Objects.equals(cc, "Y")){
                System.out.println("Adding a Product--");
                System.out.println("Product Name: ");
                String ProductName = sc.nextLine();
                System.out.println("Product ID: ");
                String ProductID = sc.nextLine();
                System.out.println("Product Price: ");
                int PriceOfProduct = sc.nextInt();
                sc.nextLine();
                Product p = new Product();
                p.setNameOfProduct(ProductName); p.setProductID(ProductID); p.setPriceOfProduct(PriceOfProduct);
                this.Categories.get(x).Products.add(p);
            }else if(Objects.equals(cc, "N")){
                this.Categories.remove(x);
                System.out.println("Successfully removed");
            }
        }
    }

    public void SetDiscountOnProduct(Admin a){
        System.out.println("Dear Admin give the Product ID you want to add discount for");
        System.out.print("Enter the Product ID: ");
        String PID = sc.nextLine();
        System.out.println("Enter discount for Elite, Prime and Normal customers respectively (in % terms)");
        double DFE = sc.nextFloat();
        sc.nextLine();
        double DFP = sc.nextFloat();
        sc.nextLine();
        double DFN = sc.nextFloat();
        sc.nextLine();
        if(DFP > 5){
            a.DFP = DFP;
        }
        if(DFE > 10){
            a.DFE = DFE;
        }
        if(DFN > 0){
            a.DFN = DFN;
        }

        for (Category category : this.Categories) {
            for (int j = 0; j < category.Products.size(); j++) {
                if (Objects.equals(category.Products.get(j).ProductID, PID)) {
                    a.DiscountedProducts.add(category.Products.get(j));
                }
            }

        }

    }

    public void AddGiveawayDeals(){
        System.out.println("Dear Admin give the Product IDs you want to combine and giveaway a deal for");
        System.out.print("Enter the first Product ID: ");
        this.PID_1.add(sc.nextLine());
        System.out.print("Enter the second Product ID: ");
        this.PID_2.add(sc.nextLine());
        System.out.println("Enter the combined price(Should be less than their combined price)(Elite, Prime, Normal): ");
        this.CPE.add((double) sc.nextFloat());
        sc.nextLine();
        this.CPP.add((double) sc.nextFloat());
        sc.nextLine();
        this.CPN.add((double) sc.nextFloat());
        sc.nextLine();
    }
}

class Category extends Admin{
    int CategoryID;
    String CategoryName;
    ArrayList<Product> Products = new ArrayList<>();

    public void setCategoryID(int categoryID) {
        this.CategoryID = categoryID;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }

}

class Product extends Category{
    String NameOfProduct;
    String ProductID;
    int PriceOfProduct;
    String Details;

    public void setDetails(String details) {
        Details = details;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.NameOfProduct = nameOfProduct;
    }

    public void setProductID(String productID) {
        this.ProductID = productID;
    }

    public void setPriceOfProduct(int priceOfProduct) {
        this.PriceOfProduct = priceOfProduct;
    }
}

class Customer extends Category{
    Scanner sc = new Scanner(System.in);
    String CustomerName;
    int CustomerAge;
    long CustomerPhoneNumber;
    String EmailID;
    String Password;
    String Status;
    double Balance;
    ArrayList<MyCart> MyCart = new ArrayList<>();
    ArrayList<Integer> Coupons = new ArrayList<>();

    public Customer SignUp(){
        Customer c = new Customer();
        System.out.print("Enter Name: ");
        c.CustomerName = sc.nextLine();
        System.out.print("Enter Age: ");
        c.CustomerAge = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter PhoneNumber: ");
        c.CustomerPhoneNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter EmailID: ");
        c.EmailID = sc.nextLine();
        System.out.print("Enter Password: ");
        c.Password = sc.nextLine();
        System.out.println("Successfully Registered!!");
        c.Status = "NORMAL";
        c.Balance = 1000;
        this.Customers.add(c);
        return c;
    }


    public void UpgradeStatus(){
        System.out.println("Current Status: "+ this.Status);
        if (Objects.equals(this.Status, "NORMAL")) {
            System.out.println("Which one would you like to upgrade to Prime or Elite?");
            System.out.println("Enter P for Prime or E for Elite");
            String y = sc.nextLine();
            if(Objects.equals(y, "E") && this.Balance>=300){
                this.Status = "ELITE";
                this.Balance= this.Balance - 300;
                System.out.println("New Status: "+ this.Status);
            }
            else if(Objects.equals(y, "P") && this.Balance>=200){
                this.Status = "PRIME";
                this.Balance = this.Balance - 200;
                System.out.println("New Status: "+ this.Status);
            }else{
                System.out.println("Not enough balance");
            }
        }else if(Objects.equals(this.Status, "PRIME")){
            System.out.println("Which one would you like to upgrade to Normal or Elite?");
            System.out.println("Enter N for Normal or E for Elite");
            String y = sc.nextLine();
            if(Objects.equals(y, "E") && this.Balance>=100){
                this.Status = "ELITE";
                this.Balance= this.Balance - 100;
                System.out.println("New Status: "+ this.Status);
            }
            else if(Objects.equals(y, "N")){
                this.Status = "PRIME";
                this.Balance = this.Balance + 200;
                System.out.println("New Status: "+ this.Status);
            }
        }else if(Objects.equals(this.Status, "ELITE")){
            System.out.println("Which one would you like to upgrade to Normal or Prime?");
            System.out.println("Enter N for Normal or P for Prime");
            String y = sc.nextLine();
            if(Objects.equals(y, "P")){
                this.Status = "ELITE";
                this.Balance= this.Balance + 100;
                System.out.println("New Status: "+ this.Status);
            }
            else if(Objects.equals(y, "N")){
                this.Status = "PRIME";
                this.Balance = this.Balance + 300;
                System.out.println("New Status: "+ this.Status);
            }
        }

    }


    public void MakePayment(Admin a, int z, int l, ArrayList<Integer> ll) {
        double TotalCost = 0;
        double Discount = 0;
        double ProductPrice = 0;
        a.Customers.get(z).Coupons.sort(Collections.reverseOrder());
        int ee = 0;
        if(l == 0){
            for(int s = 0; s<a.Customers.get(z).MyCart.size();s++){
                for (int i = 0; i < a.Customers.get(z).MyCart.get(s).ProductID.size(); i++) {
                    for(int j = 0;j<a.Categories.size();j++){
                        for(int k =0;k<a.Categories.get(j).Products.size();k++){
                            if(Objects.equals(a.Categories.get(j).Products.get(k).ProductID, a.Customers.get(z).MyCart.get(s).ProductID.get(i))){
                                {
                                    System.out.println("Product Name: "+a.Categories.get(j).Products.get(k).NameOfProduct);
                                    System.out.println("Product ID: "+a.Categories.get(j).Products.get(k).ProductID);
                                    System.out.println("Product Price: "+a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct);
                                    System.out.println("Product Details: ");
                                    System.out.println(a.Categories.get(j).Products.get(k).Details);
                                    ProductPrice+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct;
                                    System.out.println();
                                    if(a.DiscountedProducts.contains(a.Categories.get(j).Products.get(k))){
                                        if(Objects.equals(a.Customers.get(z).Status, "ELITE")){
                                            double x1 = -1;
                                            if(a.Customers.get(z).Coupons.size()>0){
                                                if(a.Customers.get(z).Coupons.get(ee)>a.DFE){
                                                    x1 = a.DFE;
                                                    a.DFE = a.Customers.get(z).Coupons.get(ee);
                                                    a.Customers.get(z).Coupons.remove(ee);
                                                }
                                            }
                                            Discount+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*a.DFE*0.01);
                                            TotalCost+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFE);
                                            if(x1 != -1){
                                                a.DFE = x1;
                                            }
                                        }else if(Objects.equals(a.Customers.get(z).Status, "PRIME")){
                                            double x1 = -1;
                                            if(a.Customers.get(z).Coupons.size()>0){
                                                if(a.Customers.get(z).Coupons.get(ee)>a.DFP){
                                                    x1 = a.DFP;
                                                    a.DFP = a.Customers.get(z).Coupons.get(ee);
                                                    a.Customers.get(z).Coupons.remove(ee);
                                                }
                                            }
                                            Discount+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFP);
                                            TotalCost+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFP);
                                            if(x1 != -1){
                                                a.DFP = x1;
                                            }
                                        }else if(Objects.equals(a.Customers.get(z).Status, "NORMAL")){
                                            double x1 = -1;
                                            if(a.Customers.get(z).Coupons.size()>0){
                                                if(a.Customers.get(z).Coupons.get(ee)>a.DFN){
                                                    x1 = a.DFN;
                                                    a.DFN = a.Customers.get(z).Coupons.get(ee);
                                                    a.Customers.get(z).Coupons.remove(ee);
                                                }
                                            }
                                            Discount+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFN;
                                            TotalCost+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFN);
                                            if(x1 != -1){
                                                a.DFE = x1;
                                            }
                                        }
                                    }else{
                                        if(Objects.equals(this.Status, "ELITE")){
                                            int x = 10;
                                            if(a.Customers.get(z).Coupons.size()>0){
                                                if(a.Customers.get(z).Coupons.get(ee)>10){
                                                    x = a.Customers.get(z).Coupons.get(ee);
                                                    a.Customers.get(z).Coupons.remove(ee);
                                                }
                                            }
                                            Discount+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x;
                                            TotalCost+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x);
                                        }else if(Objects.equals(a.Customers.get(z).Status, "PRIME")){
                                            int x = 5;
                                            if(a.Customers.get(z).Coupons.size()>0){
                                                if(a.Customers.get(z).Coupons.get(ee)>5){
                                                    x = a.Customers.get(z).Coupons.get(ee);
                                                    a.Customers.get(z).Coupons.remove(ee);
                                                }
                                            }
                                            Discount+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x;
                                            TotalCost+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x);
                                        }else if(Objects.equals(a.Customers.get(z).Status, "NORMAL")){
                                            int x = 0;
                                            if(a.Customers.get(z).Coupons.size()>0){
                                                if(a.Customers.get(z).Coupons.get(ee)>0){
                                                    x = a.Customers.get(z).Coupons.get(ee);
                                                    a.Customers.get(z).Coupons.remove(ee);
                                                }
                                            }
                                            Discount+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x;
                                            TotalCost+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(Objects.equals(this.Status, "ELITE")){
                System.out.println("Discount: "+Discount);
                System.out.println("delivery Charges: "+100);
                System.out.println("Total Cost: "+(TotalCost + 100));
                if(this.Balance>=(TotalCost + 100)){
                    System.out.println("Your order is successfully placed");
                    System.out.println();
                    System.out.println("Your order will be delivered within 2 days");
                    this.Balance = this.Balance - TotalCost - 100;
                    a.Customers.get(z).MyCart.clear();
                    if(TotalCost+100>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }
                }else{
                    System.out.println("Insufficient Balance");
                    System.out.println();
                }
            }else if(Objects.equals(this.Status, "PRIME")){
                System.out.println("Discount: "+Discount);
                System.out.println("delivery Charges: "+(ProductPrice*0.02 + 100));
                System.out.println("Total Cost: "+(TotalCost + ProductPrice*0.02 + 100));
                if(this.Balance>=(TotalCost + ProductPrice*0.02 + 100)){
                    System.out.println("Your order is successfully placed");
                    System.out.println("Your order will be delivered within 3-6 days");
                    System.out.println();
                    this.Balance = this.Balance - ((TotalCost + ProductPrice*0.02 + 100));
                    a.Customers.get(z).MyCart.clear();
                    if((TotalCost + ProductPrice*0.02 + 100)>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }
                }else{
                    System.out.println("Insufficient Balance");
                    System.out.println();
                }
            }else{
                System.out.println("Discount: "+Discount);
                System.out.println("delivery Charges: "+(ProductPrice*0.05 + 100));
                System.out.println("Total Cost: "+(TotalCost + ProductPrice*0.05 + 100));
                if(this.Balance>=(TotalCost + ProductPrice*0.05 + 100)){
                    System.out.println("Your order is successfully placed");
                    System.out.println("Your order will be delivered within 8-10 days");
                    System.out.println();
                    this.Balance = this.Balance - ((TotalCost + ProductPrice*0.05 + 100));
                    a.Customers.get(z).MyCart.clear();
                    if((TotalCost + ProductPrice*0.05 + 100)>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }

                }else{
                    System.out.println("Insufficient Balance");
                    System.out.println();
                }
            }
        }else if(l==1){
            if(Objects.equals(this.Status, "ELITE")){
                int TotalCost1 = 0;
                for (Integer integer : ll) {
                    TotalCost1 += a.CPE.get(integer);
                }

                    System.out.println("Total Cost of Deals: "+ TotalCost1);
                    System.out.println("Delivery Charges: "+100);
                    System.out.println("Total Cost: "+ (TotalCost1+100));
                    if(this.Balance>=TotalCost1+100){
                        System.out.println("Your order is placed successfully");
                        System.out.println("Your order will be delivered within 2 days");
                        System.out.println();
                        this.Balance -= TotalCost1 + 100;
                        a.Customers.get(z).MyCart.clear();
                        if((TotalCost1 + 100)>=5000){
                            int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                            System.out.println("You have got "+randomNum+" coupons: ");
                            for(int i = 0;i<randomNum;i++){
                                int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                                System.out.println(randomNum1+"%");
                                this.Coupons.add(randomNum1);
                            }
                        }
                    }else{
                        System.out.println("Insufficient Balance");
                    }

            }else if(Objects.equals(this.Status, "PRIME")){
                int TotalCost1 = 0;
                for (Integer integer : ll) {
                    TotalCost1 += a.CPP.get(integer);
                }
                System.out.println("Total Cost of Deals: "+ TotalCost1);
                System.out.println("Delivery Charges: "+(100+TotalCost1*0.02));
                System.out.println("Total Cost: "+ (TotalCost1+100+TotalCost1*0.02));
                if(this.Balance>=TotalCost1+100+TotalCost1*0.02){
                    System.out.println("Your order is placed successfully");
                    System.out.println("Your order will be delivered within 3-6 days");
                    System.out.println();
                    this.Balance -= TotalCost1 + 100 + TotalCost1*0.02;
                    a.Customers.get(z).MyCart.clear();
                    if((TotalCost1 + TotalCost1*0.02 + 100)>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }
                }else{
                    System.out.println("Insufficient Balance");
                }

            }else if(Objects.equals(this.Status, "NORMAL")){
                int TotalCost1 = 0;
                for (Integer integer : ll) {
                    TotalCost1 += a.CPN.get(integer);
                }
                System.out.println("Total Cost of Deals: "+ TotalCost1);
                System.out.println("Delivery Charges: "+(100+TotalCost1*0.05));
                System.out.println("Total Cost: "+ (TotalCost1+100+TotalCost1*0.05));
                if(this.Balance>=TotalCost1+100+TotalCost1*0.05){
                    System.out.println("Your order is placed successfully");
                    System.out.println("Your order will be delivered within 8-10 days");
                    System.out.println();
                    this.Balance -= TotalCost1 + 100 + TotalCost1*0.05;
                    a.Customers.get(z).MyCart.clear();
                    if((TotalCost1 + TotalCost1*0.05 + 100)>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }
                }else{
                    System.out.println("Insufficient Balance");
                }

            }
        }else if(l==2){
            if(Objects.equals(this.Status, "ELITE")){
                int Discount2 = 0;
                int TotalCost2 = 0;
                for (Integer integer : ll) {
                    TotalCost2 += a.CPE.get(integer);
                }
                for(int s = 0; s<a.Customers.get(z).MyCart.size();s++){
                    for (int i = 0; i < a.Customers.get(z).MyCart.get(s).ProductID.size(); i++) {
                        for(int j = 0;j<a.Categories.size();j++){
                            for(int k =0;k<a.Categories.get(j).Products.size();k++){
                                if(Objects.equals(a.Categories.get(j).Products.get(k).ProductID, a.Customers.get(z).MyCart.get(s).ProductID.get(i))){
                                    {
                                        System.out.println("Product Name: "+a.Categories.get(j).Products.get(k).NameOfProduct);
                                        System.out.println("Product ID: "+a.Categories.get(j).Products.get(k).ProductID);
                                        System.out.println("Product Price: "+a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct);
                                        System.out.println("Product Details: ");
                                        System.out.println(a.Categories.get(j).Products.get(k).Details);
                                        System.out.println();
                                        if(a.DiscountedProducts.contains(a.Categories.get(j).Products.get(k))){
                                            if(Objects.equals(a.Customers.get(z).Status, "ELITE")){
                                                double x1 = -1;
                                                if(a.Customers.get(z).Coupons.size()>0){
                                                    if(a.Customers.get(z).Coupons.get(ee)>a.DFE){
                                                        x1 = a.DFE;
                                                        a.DFE = a.Customers.get(z).Coupons.get(ee);
                                                        a.Customers.get(z).Coupons.remove(ee);
                                                    }
                                                }
                                                Discount2+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*a.DFE*0.01);
                                                TotalCost2+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFE);
                                                if(x1!=-1){
                                                    a.DFE = x1;
                                                }
                                            }
                                        }else{
                                            if(Objects.equals(this.Status, "ELITE")){
                                                double x = 10;
                                                if(a.Customers.get(z).Coupons.size()>0){
                                                    if(a.Customers.get(z).Coupons.get(ee)>10){
                                                        x = a.Customers.get(z).Coupons.get(ee);
                                                        a.Customers.get(z).Coupons.remove(ee);
                                                    }
                                                }
                                                Discount2+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x;
                                                TotalCost2+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("Discount: " +Discount2);
                System.out.println("Delivery Charges: "+100);
                System.out.println("Total Cost: "+TotalCost2+100);
                if(this.Balance>=TotalCost2+100){
                    System.out.println("Your order is placed successfully");
                    System.out.println("Your order will be delivered within 2 days");
                    System.out.println();
                    this.Balance -= TotalCost2 + 100;
                    a.Customers.get(z).MyCart.clear();
                    if((TotalCost2 + 100)>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }
                }else{
                    System.out.println("Insufficient Balance");
                }
            }
            if(Objects.equals(this.Status, "PRIME")){
                int Discount2 = 0;
                int TotalCost2 = 0;
                int ProductPrice2 = 0;
                for (Integer integer : ll) {
                    TotalCost2 += a.CPP.get(integer);
                    ProductPrice2 += a.CPP.get(integer);
                }
                for(int s = 0; s<a.Customers.get(z).MyCart.size();s++){
                    for (int i = 0; i < a.Customers.get(z).MyCart.get(s).ProductID.size(); i++) {
                        for(int j = 0;j<a.Categories.size();j++){
                            for(int k =0;k<a.Categories.get(j).Products.size();k++){
                                if(Objects.equals(a.Categories.get(j).Products.get(k).ProductID, a.Customers.get(z).MyCart.get(s).ProductID.get(i))){
                                    {
                                        System.out.println("Product Name: "+a.Categories.get(j).Products.get(k).NameOfProduct);
                                        System.out.println("Product ID: "+a.Categories.get(j).Products.get(k).ProductID);
                                        System.out.println("Product Price: "+a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct);
                                        ProductPrice2+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct;
                                        System.out.println();
                                        if(a.DiscountedProducts.contains(a.Categories.get(j).Products.get(k))){
                                            if(Objects.equals(a.Customers.get(z).Status, "PRIME")) {
                                                double x1 = -1;
                                                if(a.Customers.get(z).Coupons.size()>0){
                                                    if(a.Customers.get(z).Coupons.get(ee)>a.DFP){
                                                        x1 = a.DFP;
                                                        a.DFP = a.Customers.get(z).Coupons.get(ee);
                                                        a.Customers.get(z).Coupons.remove(ee);
                                                    }
                                                }
                                                Discount2 += (a.Categories.get(j).Products.get(k).PriceOfProduct * a.Customers.get(z).MyCart.get(s).NoOfProduct * 0.01 * a.DFP);
                                                TotalCost2 += (a.Categories.get(j).Products.get(k).PriceOfProduct * a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct * a.Customers.get(z).MyCart.get(s).NoOfProduct * 0.01 * a.DFP);
                                                if(x1!=-1){
                                                    a.DFP = x1;
                                                }
                                            }
                                        }else{
                                            if(Objects.equals(a.Customers.get(z).Status, "PRIME")){
                                                double x = 5;
                                                if(a.Customers.get(z).Coupons.size()>0){
                                                    if(a.Customers.get(z).Coupons.get(ee)>5){
                                                        x = a.Customers.get(z).Coupons.get(ee);
                                                        a.Customers.get(z).Coupons.remove(ee);
                                                    }
                                                }
                                                Discount2+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x;
                                                TotalCost2+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("Discount: " +Discount2);
                System.out.println("Delivery Charges: "+(100+0.02*ProductPrice2));
                System.out.println("Total Cost: "+(TotalCost2+100+0.02*ProductPrice2));
                if(this.Balance>=(TotalCost2+100+0.02*ProductPrice2)){
                    System.out.println("Your order is placed successfully");
                    System.out.println("Your order will be delivered within 3-6 days");
                    System.out.println();
                    this.Balance -= (TotalCost2+100+0.02*ProductPrice2);
                    a.Customers.get(z).MyCart.clear();
                    if((TotalCost2+100+0.02*ProductPrice2)>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }
                }else{
                    System.out.println("Insufficient Balance");
                }
            }
            if(Objects.equals(this.Status, "NORMAL")){
                int Discount2 = 0;
                int TotalCost2 = 0;
                int ProductPrice2 = 0;
                for (Integer integer : ll) {
                    TotalCost2 += a.CPN.get(integer);
                    ProductPrice2 += a.CPN.get(integer);
                }
                for(int s = 0; s<a.Customers.get(z).MyCart.size();s++){
                    for (int i = 0; i < a.Customers.get(z).MyCart.get(s).ProductID.size(); i++) {
                        for(int j = 0;j<a.Categories.size();j++){
                            for(int k =0;k<a.Categories.get(j).Products.size();k++){
                                if(Objects.equals(a.Categories.get(j).Products.get(k).ProductID, a.Customers.get(z).MyCart.get(s).ProductID.get(i))){
                                    {
                                        System.out.println("Product Name: "+a.Categories.get(j).Products.get(k).NameOfProduct);
                                        System.out.println("Product ID: "+a.Categories.get(j).Products.get(k).ProductID);
                                        System.out.println("Product Price: "+a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct);
                                        ProductPrice2+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct;
                                        System.out.println();
                                        if(a.DiscountedProducts.contains(a.Categories.get(j).Products.get(k))){
                                            if(Objects.equals(a.Customers.get(z).Status, "NORMAL")){
                                                double x1 = -1;
                                                if(a.Customers.get(z).Coupons.size()>0){
                                                    if(a.Customers.get(z).Coupons.get(ee)>a.DFN){
                                                        x1 = a.DFN;
                                                        a.DFN = a.Customers.get(z).Coupons.get(ee);
                                                        a.Customers.get(z).Coupons.remove(ee);
                                                    }
                                                }
                                                Discount2+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFN;
                                                TotalCost2+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*a.DFN);
                                                if(x1!=-1){
                                                    a.DFN = x1;
                                                }
                                            }
                                        }else{
                                            if(Objects.equals(a.Customers.get(z).Status, "NORMAL")){
                                                double x = 0;
                                                if(a.Customers.get(z).Coupons.size()>0){
                                                    if(a.Customers.get(z).Coupons.get(ee)>a.DFE){
                                                        x = a.Customers.get(z).Coupons.get(ee);
                                                        a.Customers.get(z).Coupons.remove(ee);
                                                    }
                                                }
                                                Discount2+=a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x;
                                                TotalCost2+=(a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct - a.Categories.get(j).Products.get(k).PriceOfProduct*a.Customers.get(z).MyCart.get(s).NoOfProduct*0.01*x);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("Discount: " +Discount2);
                System.out.println("Delivery Charges: "+(100+0.05*ProductPrice2));
                System.out.println("Total Cost: "+(TotalCost2+100+0.05*ProductPrice2));
                if(this.Balance>=(TotalCost2+100+0.05*ProductPrice2)){
                    System.out.println("Your order is placed successfully");
                    System.out.println("Your order will be delivered within 8-10 days");
                    System.out.println();
                    this.Balance -= (TotalCost2+100+0.05*ProductPrice2);
                    a.Customers.get(z).MyCart.clear();
                    if((TotalCost2+100+0.05*ProductPrice2)>=5000){
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                        System.out.println("You have got "+randomNum+" coupons: ");
                        for(int i = 0;i<randomNum;i++){
                            int randomNum1 = ThreadLocalRandom.current().nextInt(5, 16);
                            System.out.println(randomNum1+"%");
                            this.Coupons.add(randomNum1);
                        }
                    }
                }else{
                    System.out.println("Insufficient Balance");
                }
            }
        }
    }
}

class MyCart extends Customer implements Cart{
    int NoOfProduct = 0;
    ArrayList<String> ProductID = new ArrayList<>();

    public MyCart AddProductToCart(int z){
        System.out.print("Enter the product ID: ");
        String PID = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int Quantity = sc.nextInt();
        sc.nextLine();
        MyCart x = new MyCart();
        x.ProductID.add(PID);
        x.NoOfProduct=Quantity;
        System.out.println("Items successfully added to cart");
        return x;
    }

    public int AddProductsInDealToCart(int z, Admin a){
        if(a.PID_1.size()==0){
            System.out.println("No deals yet");
        }else{
            System.out.println("All the available deals are as follows: ");
            for(int i =0;i<a.PID_1.size();i++){
                if(Objects.equals(a.Customers.get(i).Status, "ELITE")){
                    System.out.println("Deal "+(i+1)+":");
                    System.out.println(a.PID_1.get(i)+" and "+a.PID_2.get(i)+" at "+a.CPE.get(i));
                }else if(Objects.equals(a.Customers.get(i).Status, "PRIME")){
                    System.out.println("Deal "+(i+1)+":");
                    System.out.println(a.PID_1.get(i)+" and "+a.PID_2.get(i)+" at "+a.CPP.get(i));
                }else if(Objects.equals(a.Customers.get(i).Status, "NORMAL")){
                    System.out.println("Deal "+(i+1)+":");
                    System.out.println(a.PID_1.get(i)+" and "+a.PID_2.get(i)+" at "+a.CPN.get(i));
                }
            }
            System.out.print("Enter your choice: ");
            int x = sc.nextInt();
            sc.nextLine();
            MyCart m = new MyCart();
            m.ProductID.add(a.PID_1.get(x-1));
            m.ProductID.add(a.PID_2.get(x-1));
            a.Customers.get(z).MyCart.add(m);
            System.out.println("Successfully Done");
            System.out.println();
            return x-1;
        }

    return -1;
    }

    public void ViewCart(int z, Admin a){
        if(a.Customers.get(z).MyCart.size()==0){
            System.out.println("Cart is Empty now!!");
        }else{
            for(int i = 0;i<a.Customers.get(z).MyCart.size();i++){
                System.out.print("Product "+(i+1)+": ");
                System.out.println(a.Customers.get(z).MyCart.get(i).ProductID.get(0));
            }
        }
    }

    public void EmptyCart(int z,Admin a){
        if(a.Customers.get(z).MyCart.size()==0){
            System.out.println("Cart is already empty!!");
        }else{
            a.Customers.get(z).MyCart.clear();
            System.out.println("Cart is empty now");
        }
    }
}


public class Flipzon {
    public static void main(String[] args) {
        Admin a = new Admin();
        a.setRollNo("2021536");
        a.setUsername("Beff Jezos");
        Scanner sc = new Scanner(System.in);
        int in1 = 0;
        while (in1 != 5) {
            System.out.println("Welcome to FLIPZON");
            System.out.println("""
                    1) Enter as Admin
                    2) Explore the Product Catalog
                    3) Show Available Deals
                    4) Enter as Customer
                    5) Exit the Application""");
            in1 = sc.nextInt();
            sc.nextLine();
            if (in1 == 1) {
                System.out.println("Dear Admin, ");
                System.out.println("Please enter your username and password");
                System.out.print("Username: ");
                String AdminName = sc.nextLine();
                System.out.print("Password: ");
                String AdminPassword = sc.nextLine();
                if(Objects.equals(AdminPassword, "2021536") && (Objects.equals(AdminName, "Beff Jezos") || Objects.equals(AdminName, "Gill Bates"))){
                    int in5 = 0;
                    while(in5!=7){
                        System.out.println();
                        System.out.println("""
                                1) Add category
                                2) Delete category
                                3) Add Product
                                4) Delete Product
                                5) Set Discount on Product
                                6) Add giveaway deal
                                7) Back""");
                        in5 = sc.nextInt();
                        sc.nextLine();

                        if(in5 == 1){
                            a.AddCategory(a);

                        }else if(in5 ==2){
                            a.DeleteCategory();

                        }else if(in5 ==3){
                            a.AddProduct();

                        }else if(in5 ==4){
                            a.DeleteProduct();

                        }else if(in5 ==5){
                            a.SetDiscountOnProduct(a);

                        }else if(in5 ==6){
                            a.AddGiveawayDeals();
                        }
                    }
                }else{
                    System.out.println("Wrong inputs");
                }
            }
            if (in1 == 2) {
                if(a.Categories.size()==0){
                    System.out.println("No products available");
                }
                for(int i = 0; i<a.Categories.size();i++){
                    for(int j = 0;j<a.Categories.get(i).Products.size();j++){
                        System.out.println("Name: "+a.Categories.get(i).Products.get(j).NameOfProduct);
                        System.out.println("ID: "+a.Categories.get(i).Products.get(j).ProductID);
                        System.out.println("Price: "+a.Categories.get(i).Products.get(j).PriceOfProduct);
                        System.out.println();
                    }
                }
            }
            if (in1 == 3) {
                if(a.PID_1.size()==0){
                    System.out.println("No deals yet");
                }
                for(int i = 0;i<a.PID_1.size();i++){
                    System.out.println("Product 1: "+ a.PID_1.get(i));
                    System.out.println("Product 2: "+ a.PID_2.get(i));
                    System.out.println("Price of Elite members: "+ a.CPE.get(i));
                    System.out.println("Price of Prime members: "+ a.CPP.get(i));
                    System.out.println("Price of Normal members: "+ a.CPN.get(i));
                }

            }
            if (in1 == 4) {
                int in2 = 0;
                while(in2!=3){
                    System.out.println("""
                            1) Sign up
                            2) Log in
                            3) Back""");
                    in2 = sc.nextInt();
                    sc.nextLine();
                    if (in2 == 1) {
                        Customer c = new Customer();
                        c = c.SignUp();
                        a.Customers.add(c);
                    } else if (in2 == 2) {

                        System.out.print("Enter your name: ");
                        String CustomerName = sc.nextLine();
                        System.out.print("Enter password: ");
                        String Password = sc.nextLine();
                        int vv = -1;
                        if(vv==-1){
                            int v = -1;
                            for (int i = 0; i < a.Customers.size(); i++) {
                                if (Objects.equals(a.Customers.get(i).CustomerName, CustomerName) && Objects.equals(a.Customers.get(i).Password, Password)) {
                                    vv = 1;
                                    v = i;
                                    System.out.println("Successfully logged in!");
                                    System.out.println("Welcome " + CustomerName);
                                    System.out.println();
                                    int in3 = 0;
                                    ArrayList<Integer> ll = new ArrayList<>();
                                    int trigger = -1;
                                    while (in3 != 12) {
                                        System.out.println("""
                                            1) browse products
                                            2) browse deals
                                            3) add a product to cart
                                            4) add products in deal to cart
                                            5) view coupons
                                            6) check account balance
                                            7) view cart
                                            8) empty cart
                                            9) checkout cart
                                            10) upgrade customer status
                                            11) Add amount to wallet
                                            12) back""");
                                        in3 = sc.nextInt();
                                        sc.nextLine();
                                        if (in3 == 1) {
                                            for(int k = 0; k<a.Categories.size();k++){
                                                for(int j = 0;j<a.Categories.get(k).Products.size();j++){
                                                    System.out.println("Name: "+a.Categories.get(k).Products.get(j).NameOfProduct);
                                                    System.out.println("ID: "+a.Categories.get(k).Products.get(j).ProductID);
                                                    System.out.println("Price: "+a.Categories.get(k).Products.get(j).PriceOfProduct);
                                                    System.out.println();
                                                }
                                            }

                                        } else if (in3 == 2) {
                                            if(a.PID_1.size()==0){
                                                System.out.println("No deals yet");
                                            }
                                            for(int gg = 0;gg<a.PID_1.size();gg++){
                                                System.out.println("Product 1: "+ a.PID_1.get(gg));
                                                System.out.println("Product 2: "+ a.PID_2.get(gg));
                                                System.out.println("Price of Elite members: "+ a.CPE.get(gg));
                                                System.out.println("Price of Prime members: "+ a.CPP.get(gg));
                                                System.out.println("Price of Normal members: "+ a.CPN.get(gg));
                                            }

                                        } else if (in3 == 3) {
                                            MyCart mm = new MyCart();
                                            mm = mm.AddProductToCart(v);
                                            a.Customers.get(v).MyCart.add(mm);
                                            trigger = 0;

                                        } else if (in3 == 4) {
                                            if(trigger == -1){
                                                MyCart mm = new MyCart();
                                                ll.add(mm.AddProductsInDealToCart(v,a));
                                                trigger = 1;
                                            }else if(trigger == 0){
                                                MyCart mm = new MyCart();
                                                ll.add(mm.AddProductsInDealToCart(v,a));
                                                trigger = 2;
                                            }


                                        } else if (in3 == 5) {
                                            if(a.Customers.get(v).Coupons.size()==0){
                                                System.out.println("No coupons");
                                            }
                                            for(int c = 0;c<a.Customers.get(v).Coupons.size();c++){
                                                System.out.println("Coupon "+(i+1)+": ");
                                                System.out.print(a.Customers.get(v).Coupons.get(i));
                                                System.out.println();
                                            }

                                        } else if (in3 == 6) {
                                            System.out.println("Balance: " + a.Customers.get(v).Balance);

                                        } else if (in3 == 7) {
                                            MyCart mm = new MyCart();
                                            mm.ViewCart(v,a);

                                        } else if (in3 == 8) {
                                            MyCart mm = new MyCart();
                                            mm.EmptyCart(v,a);

                                        } else if (in3 == 9) {
                                            if(trigger == 0){
                                                a.Customers.get(v).MakePayment(a, v,0,ll);
                                            }else if(trigger == 1){
                                                a.Customers.get(v).MakePayment(a, v,1,ll);
                                            }else{
                                                a.Customers.get(v).MakePayment(a, v,2,ll);
                                            }

                                        } else if (in3 == 10) {
                                            a.Customers.get(v).UpgradeStatus();

                                        } else if (in3 == 11) {
                                            System.out.print("Enter the amount to add: ");
                                            double x = sc.nextFloat();
                                            a.Customers.get(v).Balance+=x;
                                            System.out.println("Successfully added");
                                            System.out.println();
                                        }
                                    }
                                }
                            }
                        }if(vv!=1){
                            System.out.println("Wrong inputs");
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}
