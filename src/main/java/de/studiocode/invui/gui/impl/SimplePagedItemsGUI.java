package de.studiocode.invui.gui.impl;

import de.studiocode.invui.gui.SlotElement;
import de.studiocode.invui.gui.SlotElement.ItemSlotElement;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@link PagedGUI} that is filled with {@link Item}s.
 *
 * @see GUIBuilder
 * @see SimplePagedGUIsGUI
 */
public class SimplePagedItemsGUI extends PagedGUI {
    
    private List<Item> items;
    
    public SimplePagedItemsGUI(int width, int height, int... itemListSlots) {
        this(width, height, new ArrayList<>(), itemListSlots);
    }
    
    public SimplePagedItemsGUI(int width, int height, List<Item> items, int... itemListSlots) {
        super(width, height, false, itemListSlots);
        this.items = items;
        
        update();
    }
    
    @Override
    public int getPageAmount() {
        return (int) Math.ceil((double) items.size() / (double) getItemListSlots().length);
    }
    
    @Override
    protected List<SlotElement> getPageElements(int page) {
        int length = getItemListSlots().length;
        int from = page * length;
        int to = Math.min(from + length, items.size());
        
        return items.subList(from, to).stream().map(ItemSlotElement::new).collect(Collectors.toList());
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
        update();
    }
    
}
