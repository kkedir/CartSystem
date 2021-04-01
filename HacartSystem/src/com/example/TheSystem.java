package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;


public abstract class TheSystem {
   
private HashMap<String, Item> itemCollection;
TheSystem() {
	itemCollection = new HashMap<String, Item>();
	if (getClass().getSimpleName().equals("AppSystem")) {
		try {
			FileReader file = new FileReader(new File("C:\\Users\\korme\\OneDrive\\Desktop\\resources\\sample.txt"));
			//FileReader file = new FileReader(new File("sample.txt"));
			Scanner filescan = new Scanner(file);
			while (filescan.hasNextLine()) {
				String[] itemInfo = filescan.nextLine().split("  ");
				Item it = new Item();
				it.setItemName(itemInfo[0]);
				it.setItemDesc(itemInfo[1]);
				it.setItemPrice(Double.valueOf(itemInfo[2]));
				it.setAvailableQuantity(Integer.valueOf(itemInfo[3]));
				itemCollection.put(it.getItemName(), it);
			}
			filescan.close();

		} catch (FileNotFoundException ex) {
			System.err.println("Something went wrong and could not load item list file.");
			ex.printStackTrace();

		}

	}
}
    
    public HashMap<String, Item> getItemCollection(){
        return itemCollection;
    }
    
    public Boolean checkAvailability(Item item) {
        if (item == null || item.getQuantity() >= item.getAvailableQuantity()) {
            System.out.println("System is unable to add " + item.getItemName() + " to the cart. System only has " +
                    item.getAvailableQuantity() + item.getItemName() + "s.");
            return false;
        }
        return true;
    }
    
    public Boolean add(Item item) {
        if (item == null) {
            return false;
        }
        if (itemCollection.containsKey(item.getItemName())) {
            item.setQuantity(item.getQuantity() + 1);
            return true;
        } else {
            itemCollection.put(item.getItemName(), item);
            return true;
        }
    }

    public Item remove(String itemName) {
        if (!itemCollection.containsKey(itemName)) {
            return null;
        } else {
            return itemCollection.remove(itemName);
        }
    }

    public void setItemCollection(HashMap<String, Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public abstract void display();
}