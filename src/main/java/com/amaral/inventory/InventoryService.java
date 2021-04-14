package com.amaral.inventory;

import com.amaral.entity.Inventory;

import java.util.List;

public interface InventoryService {
    void clearList();
    List<Inventory> getInventoryList();
    void addToInventory(Inventory inventory);
    void removeFromInventory(Inventory inventory);
} // END OF interface