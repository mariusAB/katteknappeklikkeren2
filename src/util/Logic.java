package util;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import weapons.Staff;

public class Logic {
    private Katteknappeklikkeren2 k;
    private Thing main;
    private Set<Integer> keys;
    private Room currRoom = new Room(this);
    private List<Thing> toRemove = new ArrayList<Thing>();

    public Logic(Katteknappeklikkeren2 k) {
        this.k = k;
        main = new Thing(0,0,10, true, currRoom);
        main.setPath("img/icon.png");
        k.addImage(main);
        currRoom.addThing(main);
        main.setSpeed(7);
    }

    public void tick() {
        Iterator<Thing> iterator = currRoom.getThings().iterator();
        while (iterator.hasNext()) {
            Thing t = iterator.next();
            t.tick(k.getWidth(), k.getHeight());
            k.addImage(t);
        }
        Iterator<Thing> iterator2 = currRoom.getObstacles().iterator();
        while (iterator2.hasNext()) {
            Thing t = iterator2.next();
            k.addImage(t);
        }
        for (Thing t : toRemove) {
            currRoom.removeThing(t);
        }
        toRemove.clear();
        if (keys != null) {
            if (keys.contains(KeyEvent.VK_A)) {
                main.moveX(-main.getSpeed(), k.getWidth(), k.getHeight());
                main.setPath("img/back.png");
            }
            if (keys.contains(KeyEvent.VK_D)) {
                main.moveX(main.getSpeed(), k.getWidth(), k.getHeight());
                main.setPath("img/icon.png");
            }
            if (keys.contains(KeyEvent.VK_W)) {
                main.moveY(-main.getSpeed(), k.getWidth(), k.getHeight());
            }
            if (keys.contains(KeyEvent.VK_S)) {
                main.moveY(main.getSpeed(), k.getWidth(), k.getHeight());
            }
        }
    }

    public Room getRoom() {
        return currRoom;
    }

    public Thing getMain() {
        return main;
    }

    public void toMid() {
        main.setX(k.getSize().width / 2);
        main.setY(k.getSize().height / 2);
    }

    public void mouseClicked(int x, int y) {
        int vx = x - main.getX();
        int vy = y - main.getY();
        Staff s = new Staff(currRoom);
        if (vx == 0 && vy == 0) {
            vx = 1;
        }
        s.use(main.getX(), main.getY(), vx, vy);
    }

    public void keys(Set<Integer> keys) {
        this.keys = keys;
    }

    public void removeThing(Thing thing) {
        toRemove.add(thing);
    }

    public void resize(int prevWidth, int prevHeight, int width, int height) {
        for (Thing thing : currRoom.getThings()) {
            thing.resize(prevWidth, prevHeight, width, height);
        }
    }
}
