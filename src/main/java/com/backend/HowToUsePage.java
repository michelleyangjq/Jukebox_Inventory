package com.backend;

public class HowToUsePage {

    Instruction firstPage;
    Instruction lastPage;
    boolean isEmpty;

    public HowToUsePage(){
        initHowToUSePage();
    }

    //Initializes the HowToUsePage linked list with the instructions
    public void initHowToUSePage(){
        Instruction first = new Instruction("🤗 Welcome to Jukebox's Inventory System!");
        firstPage = first;
        lastPage = first;

        Instruction view = new Instruction("👀 View the products!",
                "All products inside the inventory are listed in our homepage! \n\n" +
                        "For more information about the products, click on the 'View Details' button.");

        Instruction add = new Instruction("🙌 Add a product!",
                "Bought new products for sale? Add the product to the inventory! \n\n" +
                        "Use the 'Add Product' button to add new products to the inventory.");

        Instruction edit = new Instruction("✍️ Edit or delete a product!",
                "Once inside the product's details, you can you the 'Edit Product' to make changes to its information.\n\n" +
                        "You can also delete the product if you no longer sell them.");

        Instruction details = new Instruction("🔎 Check the details!",
                "In the 'Detailed Statistics' page, you can see a break down of our most popular products and categories.\n\n" +
                        "Choose the break down for the past week, month, or quintile.");

        Instruction stock = new Instruction("🛒 Stock products!",
                "When you are stocking products into the inventory, use the 'Stocking Mode' function!\n\n" +
                        "Simply scan the barcode with your scanner and update your products!");

        Instruction sell = new Instruction("👋 Sell products!",
                "Use the 'Selling Mode' when you are making sales!\n\n" +
                        "Scan the barcode and update the products' stock information and sales records!");

        Instruction toStock = new Instruction("☑️ Remember to check the To-Stock list!",
                "Click on the white exclamation mark in the homepage to check the To-Stock list!\n\n" +
                        "Here are the products that are OUT OF STOCK and need to be restocked soon.");

        Instruction revenue = new Instruction("💰 How much did we make?",
                "The system records the money we made everyday!\n\n" +
                        "Use the 'Check Records' page to recall the revenue on a certain sales day!");

        Instruction logOut = new Instruction("💨 Log out when you leave~",
                "Use the 'Log Out' button to log out of the system when you're done~");

        Instruction home = new Instruction("🏠 Use the logo to return home",
                "Clicking on our logo directs you back to the homepage~");

        addInstruction(view);
        addInstruction(add);
        addInstruction(edit);
        addInstruction(details);
        addInstruction(stock);
        addInstruction(sell);
        addInstruction(toStock);
        addInstruction(revenue);
        addInstruction(logOut);
        addInstruction(home);


        Instruction last = new Instruction("Let's start using the system! ✊");
        addInstruction(last);
        lastPage = last;
    }

    //Returns if the Linked List is empty
    public boolean isEmpty(){
        return isEmpty;
    }

    public Instruction getFirst(){
        return firstPage;
    }

    public Instruction getLast(){
        return  lastPage;
    }

    public void addInstruction(Instruction i){
        lastPage.setNext(i);
        i.setPrevious(lastPage);
        lastPage = i;
    }

    //no removeInstruction because the instructions are fixed
}
