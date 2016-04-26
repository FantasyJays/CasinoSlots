package com.craftyn.casinoslots.classes;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;

public class OldSlotMachine {
    private SlotType type;
    private UUID ownerId;
    private String name, owner, world;
    private Boolean managed, busy = false, enabled = true, item;
    private int itemID, itemAMT;
    private ArrayList<Block> blocks;
    private Block controller, sign = null;
    private Double funds;

    /**
     * Instantiates a new slot machine, usually from the config.
     *
     * @param name The name of the slot machine.
     * @param type The type of the slot machine being created.
     * @param ownerId The UUID of the owner of the slot machine.
     * @param owner The owner of the slot machine.
     * @param world The world in which the slot machine exists.
     * @param sign The sign block
     * @param managed If it is managed or not (true or false).
     * @param blocks An ArrayList of the blocks of the slot machine.
     * @param controller The block of the controller.
     * @param funds Amount of money that the slot machine has.
     * @param item If it is an item slot or not (true or false).
     * @param itemID The item id that it accepts (should be set to 0 if false).
     * @param itemAmount The amount of the item that it takes from the player.
     */
    public OldSlotMachine(String name, SlotType type, UUID ownerId, String owner, String world, Block sign, Boolean managed, ArrayList<Block> blocks, Block controller, Double funds, Boolean item, int itemID, int itemAmount) {
        this.name = name;
        this.type = type;
        this.ownerId = ownerId;
        this.owner = owner;
        this.world = world;
        this.sign = sign;
        this.managed = managed;
        this.blocks = blocks;
        this.controller = controller;
        this.funds = funds;
        this.item = item;
        this.itemID = itemID;
        this.itemAMT = itemAmount;
    }

    /**
     * Instantiates a new slot machine, usually from a command.
     *
     * @param name The name of the slot machine as a string.
     * @param type The type of the slot machine being created as a string.
     * @param ownerId The UUID of the owner of the slot machine.
     * @param owner The owner of the slot machine as a string.
     * @param world The world in which the slot machine exists.
     * @param managed If it is managed or not (true or false).
     * @param item If it is an item slot or not (true or false).
     * @param itemId The item id that it accepts (should be set to 0 if false).
     * @param itemAmt The amount of the item that it takes from the player.
     */
    public OldSlotMachine(String name, SlotType type, UUID ownerId, String owner, String world, Boolean managed, Boolean item, int itemId, int itemAmt) {
        this.name = name;
        this.type = type;
        this.ownerId = ownerId;
        this.owner = owner;
        this.world = world;
        this.managed = managed;
        this.funds = 0.0;
        this.item = item;
        this.itemID = itemId;
        this.itemAMT = itemAmt;

        if (isManaged()) {
            enabled = false;
        }
    }

    // Returns name of slot machine
    public String getName() {
        return this.name;
    }

    /**
     * Gets the {@link SlotType} of machine this is.
     *
     * @return the {@link SlotType}
     */
    public SlotType getType() {
        return this.type;
    }

    /**
     * Gets the UUID of the Owner.
     *
     * @return the {@link UUID} of the owner.
     */
    public UUID getOwnerId() {
        return this.ownerId;
    }

    // Returns owner of slot machine
    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String name) {
        this.owner = name;
    }

    // Returns if slot machine is managed
    public Boolean isManaged() {
        return this.managed;
    }

    // Returns if slot machine is item machine, aka paid with items
    public Boolean isItem() {
        return this.item;
    }

    // Returns which item it costs
    public int getItem() {
        return this.itemID;
    }

    // Returns who many of the items it costs
    public int getItemAmount() {
        return this.itemAMT;
    }

    // Returns world name of slot machine
    public String getWorld() {
        return this.world;
    }

    // Check if slot is in use
    public Boolean isBusy() {
        return this.busy;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    // Returns available funds
    public Double getFunds() {
        return this.funds;
    }

    // Returns blocks in reel
    public ArrayList<Block> getBlocks() {
        return this.blocks;
    }

    // Returns controller block
    public Block getController() {
        return this.controller;
    }

    public Block getSign() {
        return this.sign;
    }

    // Sets reel blocks
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    // Sets controller block
    public void setController(Block c) {
        this.controller = c;

        MaterialData d = this.type.getControllerData();
        this.controller.setTypeIdAndData(d.getItemTypeId(), d.getData(), false);
    }

    public void setSign(Block sign) {
        this.sign = sign;
    }

    // Sets type
    public void setType(SlotType type) {
        this.type = type;
    }

    // Sets managed
    public void setManaged(Boolean managed) {
        this.managed = managed;
    }

    // Deposit the amount into the slot machine
    public void deposit(Double amount) {
        this.funds += amount;
    }

    // Withdraw the amount from the slot machine
    public void withdraw(Double amount) {
        this.funds -= amount;
    }

    /**
     * Sets the enabled state.
     *
     * @param enabled The new enabledment state
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Sets the use of the slot machine.
     *
     * @return Whether the machine is busy.
     */
    public boolean toggleBusy() {
        return this.busy = !this.busy;
    }
}
