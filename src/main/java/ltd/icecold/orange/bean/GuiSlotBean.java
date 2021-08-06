package ltd.icecold.orange.bean;

import java.util.List;

public class GuiSlotBean {
    private int x;
    private int y;
    private ItemInGui itemInGui;
    public static class ItemInGui{
        private String name;
        private String material;
        private int amount;
        private int durability;
        private List<String> lore;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getDurability() {
            return durability;
        }

        public void setDurability(int durability) {
            this.durability = durability;
        }

        public List<String> getLore() {
            return lore;
        }

        public void setLore(List<String> lore) {
            this.lore = lore;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ItemInGui getItemInGui() {
        return itemInGui;
    }

    public void setItemInGui(ItemInGui itemInGui) {
        this.itemInGui = itemInGui;
    }
}
