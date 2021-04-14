package com.amaral.inventory;

import com.amaral.entity.Inventory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless // Makes the class an EJB bean
@Remote(InventoryService.class) //
public class InventoryServiceImpl implements InventoryService {
    @PersistenceContext // Injects EntityManager into EJB client

    /* Represents PersistenceContext
     * Used to create/remove persistent entity instances,
     * find entities by their primary key, and to query over entities */
    private EntityManager em;

    @Override
    public void clearList() {
        Query deleteFromInventory = em.createNamedQuery("Inventory.clearAll");
        deleteFromInventory.executeUpdate();
    } // END OF clearList

    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList =  em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();
        return inventoryList
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    } // END OF getInventoryList

    @Override
    public void addToInventory(Inventory inventory) {
        em.persist(inventory);
    } // END OF addToInventory

    @Override
    public void removeFromInventory(Inventory inventory) {
        Inventory correspondingInventory = em.createNamedQuery("Inventory.getByName", Inventory.class)
                .setParameter("name", inventory.getName())
                .getSingleResult();
        em.remove(correspondingInventory);
    } // END OF removeFromInventory
} // END OF class
