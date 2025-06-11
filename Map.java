import java.util.Random;

public class Map {

    private MapRoom root;

    private boolean hit = false;

    public Map() { root = null; }

    public void addStartingRooms(MapRoom firstRoom, MapRoom secondRoom)
    {
        root = firstRoom;
        Random rand = new Random();
        int random = rand.nextInt(3);
        switch (random){
            case 0:
                if(firstRoom.isDoorF()){
                    root.setFront(secondRoom);
                } else if (firstRoom.isDoorL()) {
                    root.setLeft(secondRoom);
                } else if (firstRoom.isDoorR()) {
                    root.setRight(secondRoom);
                }
                break;
            case 1:
                if(firstRoom.isDoorL()){
                    root.setLeft(secondRoom);
                } else if (firstRoom.isDoorF()) {
                    root.setFront(secondRoom);
                } else if (firstRoom.isDoorR()) {
                    root.setRight(secondRoom);
                }
                break;
            case 2:
                if(firstRoom.isDoorR()){
                    root.setRight(secondRoom);
                } else if (firstRoom.isDoorL()) {
                    root.setLeft(secondRoom);
                } else if (firstRoom.isDoorF()) {
                    root.setFront(secondRoom);
                }
                break;
            default:
                new RuntimeException("random out of bounds!");
                break;
        }
//        if(random == 1){
//
//        }
    }

    public void add(MapRoom next)
    {

        if (root == null)
        {
            root = next;
            return;
        }

        MapRoom parent = null;
        MapRoom child = root;

        while (child!=null)
        {
            parent = child;

            if (child.isDoorL())
            {
                child = child.getLeft();
            }
            else if (child.isDoorF())
            {
                child = child.getFront();
            }
            else
            {
                child = child.getRight();
            }
        }

        if (parent.isDoorL())
        {
            parent.setLeft(next);
            next.setParent(parent);
            parent.setDoorL(false);
        }
        else if (parent.isDoorF())
        {
            parent.setFront(next);
            next.setParent(parent);
            parent.setDoorF(false);
        }
        else
        {
            parent.setRight(next);
            next.setParent(parent);
            parent.setDoorR(false);
        }
    }

    public void addAfter(MapRoom parent, MapRoom child, char door)
    {
        if (door == 'L' || door == 'l')
        {
            parent.setLeft(child);
            parent.setDoorL(false);
        }
        else if (door == 'F' || door == 'f')
        {
            parent.setFront(child);
            parent.setDoorF(false);
        }
        else if (door == 'R' || door == 'r')
        {
            parent.setRight(child);
            parent.setDoorR(false);
        }
        else
        {
            System.out.println("Wrong door as parameter");
        }

        child.setParent(parent);
    }

    public void spawnAll(float scale) { spawnAll(this.root, scale); }

    private void spawnAll(MapRoom node, float scale)
    {
        if (node == null) { return; }

//        GameObject clone = Instantiate(node.room.roomRef, node.position, new Quaternion(0, 0, 0, 1));
//        clone.transform.Rotate(Vector3.down, -90 * node.rotation);
//        clone.transform.localScale = new Vector3(scale, scale, scale);

        spawnAll(node.getLeft(), scale);
        spawnAll(node.getFront(), scale);
        spawnAll(node.getRight(), scale);

    }

    private MapRoom comparePositionsRec(MapRoom node, Vector2 position, float offset)
    {
        if (node == null) { return null; }

        comparePositionsRec(node.getLeft(), position, offset);
        comparePositionsRec(node.getFront(), position, offset);
        comparePositionsRec(node.getRight(), position, offset);

        Vector2 temp = node.getPosition();
        if ((position.x >= temp.x - offset && position.x <= temp.x + offset)
                &&
                (position.y >= temp.y - offset && position.y <= temp.y + offset))
        {
            hit = true;
            return null;
        }

        return null;

    }
    public boolean comparePositionsRec(Vector2 position, float offset)
    {
        comparePositionsRec(root, position, offset);

        if (hit)
        {
            hit = false;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void backtrack(Room room0, float roomOffset)
    {
        backtrack(root.getFront(), roomOffset, room0);
    }
    private void backtrack(MapRoom node, float roomOffset, Room room0)
    {
        if (node == null) { return; }

        backtrack(node.getLeft(), roomOffset, room0);
        backtrack(node.getFront(), roomOffset, room0);
        backtrack(node.getRight(), roomOffset, room0);

        if (node.isDoorL())
        {
            int rotation = node.getRotation();
            Vector2 position = new Vector2();
            if (rotation == 0)
            {
                position = new Vector2(node.getPosition().x - roomOffset, node.getPosition().y);
            }
            else if (rotation == 1)
            {
                position = new Vector2(node.getPosition().x, node.getPosition().y + roomOffset);
            }
            else if (rotation == 2 || rotation == -2)
            {
                position = new Vector2(node.getPosition().x + roomOffset, node.getPosition().y);
            }
            else if (rotation == -1)
            {
                position = new Vector2(node.getPosition().x, node.getPosition().y - roomOffset);
            }
            else { System.out.println("rotation out of bounds!"); }

            boolean compare = comparePositionsRec(position, 0.01f);
            if (!compare)
            {
                MapRoom endRoom = new MapRoom(room0, position, rotation - 1);
                node.setLeft(endRoom);
                node.setDoorL(false);
            }
        }

        if (node.isDoorF())
        {
            int rotation = node.getRotation();
            Vector2 position = new Vector2();
            if (rotation == 0)
            {
                position = new Vector2(node.getPosition().x, node.getPosition().y + roomOffset);
            }
            else if (rotation == 1)
            {
                position = new Vector2(node.getPosition().x + roomOffset, node.getPosition().y);
            }
            else if (rotation == 2 || rotation == -2)
            {
                position = new Vector2(node.getPosition().x, node.getPosition().y - roomOffset);
            }
            else if (rotation == -1)
            {
                position = new Vector2(node.getPosition().x - roomOffset, node.getPosition().y);
            }
            else { System.out.println("rotation out of bounds!"); }

            boolean compare = comparePositionsRec(position, 0.01f);
            if (!compare)
            {
                MapRoom endRoom = new MapRoom(room0, position, rotation);
                node.setFront(endRoom);
                node.setDoorF(false);
            }
        }

        if (node.isDoorR())
        {
            int rotation = node.getRotation();
            Vector2 position = new Vector2();
            if (rotation == 0)
            {
                position = new Vector2(node.getPosition().x + roomOffset, node.getPosition().y);
            }
            else if (rotation == 1)
            {
                position = new Vector2(node.getPosition().x, node.getPosition().y - roomOffset);
            }
            else if (rotation == 2 || rotation == -2)
            {
                position = new Vector2(node.getPosition().x - roomOffset, node.getPosition().y);
            }
            else if (rotation == -1)
            {
                position = new Vector2(node.getPosition().x, node.getPosition().y + roomOffset);
            }
            else { System.out.println("rotation out of bounds!"); }

            boolean compare = comparePositionsRec(position, 0.01f);
            if (!compare)
            {
                MapRoom endRoom = new MapRoom(room0, position, rotation + 1);
                node.setRight(endRoom);
                node.setDoorR(false);
            }
        }
    }

    public void printAll()
    {
        printAll(root);
    }

    private void printAll(MapRoom node)
    {
        if (node == null) { return; }

        System.out.println(node);
//        System.out.println(node.getLeft());

        printAll(node.getLeft());
        printAll(node.getFront());
        printAll(node.getRight());

    }
}