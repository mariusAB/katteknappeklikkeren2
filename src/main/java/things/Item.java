package things;

import util.Room;

public class Item extends Thing{
    private String item;
    public Item(int x, int y, int hitBox, String item, String path, Room r) {
        super(x, y, hitBox, path, r);
        this.item = item;
    }

    public void interact() {
        if (item.equals("healthPotion")) {
            super.currRoom.get().getMain().heal(40);
            super.currRoom.get().removeThing(this);
            super.hitBox = 300;
        }
    }
}