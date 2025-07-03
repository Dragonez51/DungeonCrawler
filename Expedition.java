import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Expedition implements KeyListener{
    private static JFrame frame;
    private JPanel mainPanel;
    private JPanel EventPanel;
    private JPanel ActionPanel;
    private JLabel SideStats;
    private JLabel CharacterIcon;
    private JProgressBar HP;
    private JProgressBar SP;
    private JLabel knight;
    private int third;
    private boolean up, down, left, right;
    public static Vector2 ocPosition;

//    public static BufferedImage Wall_U, Wall_D, Wall_L, Wall_R, Wall_Open_U, Wall_Open_D, Wall_Open_L, Wall_Open_R, Floor_Wood;
    public static BufferedImage Room0_0, Room0_1, Room0_2, Room0_N1;
    public static BufferedImage RoomL_0, RoomL_N1, RoomL_2, RoomL_1;
    public static BufferedImage RoomF_0, RoomF_1;
    public static BufferedImage RoomLR_0, RoomLR_1, RoomLR_2, RoomLR_N1;
    public static BufferedImage RoomLFR;
    public static BufferedImage Knight;

    private Map map;

    public Expedition(){
        up = down = left = right = true;
//        third = Math.ceilDiv((int)MapGenerator.roomOffset, 3);
//        knight = new JLabel("Something went wrong!");
//        try{
//            knight = new JLabel(new ImageIcon(ImageIO.read(new File("src/img/OC.png"))));
//        }catch(IOException e){
//            new RuntimeException("Knight image not found lmao");
//        }
//        ocPosition = new Vector2(100, 100);

        Entity player = new Entity();

        HP.setForeground(new Color(255, 0, 0));
        SP.setForeground(new Color(0, 255, 0));

        Init(player);

        updateMainStats(player);
        updateSideStats(player);

        setActionPanel(2);
    }

    private void drawMap(Map map, int roomAmount){
        MapGenerator.generateMap(map, roomAmount);

        int boundingBox = (int)MapGenerator.roomOffset*roomAmount;

        JPanel mapPanel = new MapViewer(map);
//        mapPanel.setLayout(new GridLayout(1, 1));

//        mapPanel.add(knight);

        JScrollPane scroll = new JScrollPane(mapPanel);
        scroll.setMaximumSize(mapPanel.getPreferredSize());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);
        EventPanel.setMaximumSize(mapPanel.getPreferredSize());
        EventPanel.removeAll();
        EventPanel.setLayout(new GridLayout(1,1));
        EventPanel.add(scroll);
        EventPanel.updateUI();
    }

    private void setActionPanel(int action){
        ActionPanel.removeAll();
        switch(action){
            case 1: // Fighting

                ActionPanel.setLayout(new GridLayout(1, 3));

                Button Attack = new Button("Attack");
                Attack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setActionPanel(2);
                        EventPanel.updateUI();
                    }
                });
                ActionPanel.add(Attack);

                Button Defend = new Button("Defend");
                Defend.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                ActionPanel.add(Defend);

                Button Flee = new Button("Flee");
                Flee.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                ActionPanel.add(Flee);

                break;
            case 2: // Map Movement

                ActionPanel.setLayout(new GridLayout(2, 3));

                Map map = new Map();
                drawMap(map, 100);
//                map.printAll();

                try{

                    frame.addKeyListener(this);

                    JButton Up = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Up.png"))));
                    Up.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            go('U');
                        }
                    });
                    ActionPanel.add(new JLabel());
                    ActionPanel.add(Up);
                    ActionPanel.add(new JLabel());

                    JButton Left = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Left.png"))));
                    Left.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            go('L');
                        }
                    });
                    ActionPanel.add(Left);

                    JButton Down = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Down.png"))));
                    Down.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            go('D');
                        }
                    });
                    ActionPanel.add(Down);

                    JButton Right = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Right.png"))));
                    Right.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            go('R');
                        }
                    });
                    ActionPanel.add(Right);
                }catch(IOException e){
                    new ErrorDialog("Source image not found!");
                }

                break;
            case 3: // Treasure

                ActionPanel.setLayout(new GridLayout(1, 3));

                Button Open = new Button("Open");
                Open.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setActionPanel(1);
                    }
                });
                ActionPanel.add(Open);

                Button Identify = new Button("Identify");
                Identify.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                ActionPanel.add(Identify);

                Button Leave = new Button("Leave");
                Leave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                ActionPanel.add(Leave);

                break;
            case 4:

                try{
                    EventPanel.removeAll();

                    EventPanel.setLayout(new GridLayout(3,2));

                    JPanel viewPanel;
                    for(int i=0; i<3; i++) {
                        viewPanel = new JPanel();
                        BoxLayout layout = new BoxLayout(viewPanel, BoxLayout.X_AXIS);
                        viewPanel.setLayout(layout);
                        for (int j = 0; j < 3; j++) {
                            viewPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("src/img/WoodenPlanks.png")))));
                        }

                        EventPanel.add(viewPanel);
                    }

                }catch(IOException e){

                }
                break;
            default:
                new ErrorDialog("argument 'action' out of bounds in setAction Panel");
                break;
        }
        ActionPanel.updateUI();
    }

    private void go(char side){
        if(side == 'U'){
            ocPosition.y-=MapGenerator.roomOffset;
        }else if(side == 'D'){
            ocPosition.y+=MapGenerator.roomOffset;
        }else if(side == 'L'){
            ocPosition.x-=MapGenerator.roomOffset;
        }else if(side == 'R'){
            ocPosition.x+=MapGenerator.roomOffset;
        }
        EventPanel.updateUI();
    }

    private void updateMainStats(Entity player){
        HP.setValue(player.getHP());
        SP.setMaximum(player.getMaxSP());
    }

    private void Init(Entity player){
        HP.setMaximum(player.getMaxHP());
        SP.setValue(player.getSP());
    }

    private void updateSideStats(Entity player){
        SideStats.setText("Str: " + player.getStrength() + " | Agi: " + player.getAgility() + " | Int: " +player.getIntelligence() + " | Defense: " +player.getDefense());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_UP && up){
            go('U');
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && left){
            go('L');
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && down){
            go('D');
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && right){
            go('R');
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {

        try{

            Expedition.Knight = ImageIO.read(new File("src/img/OC.png"));

            Expedition.Room0_0 = ImageIO.read(new File("src/img/Room0_0.png"));
            Expedition.Room0_1 = ImageIO.read(new File("src/img/Room0_1.png"));
            Expedition.Room0_2 = ImageIO.read(new File("src/img/Room0_2.png"));
            Expedition.Room0_N1 = ImageIO.read(new File("src/img/Room0_-1.png"));

            Expedition.RoomL_0 = ImageIO.read(new File("src/img/RoomL_0.png"));
            Expedition.RoomL_N1 = ImageIO.read(new File("src/img/RoomL_-1.png"));
            Expedition.RoomL_2 = ImageIO.read(new File("src/img/RoomL_2.png"));
            Expedition.RoomL_1 = ImageIO.read(new File("src/img/RoomL_1.png"));

            Expedition.RoomF_0 = ImageIO.read(new File("src/img/RoomF_0.png"));
            Expedition.RoomF_1 = ImageIO.read(new File("src/img/RoomF_1.png"));

            Expedition.RoomLR_0 = ImageIO.read(new File("src/img/RoomLR_0.png"));
            Expedition.RoomLR_1 = ImageIO.read(new File("src/img/RoomLR_1.png"));
            Expedition.RoomLR_2 = ImageIO.read(new File("src/img/RoomLR_2.png"));
            Expedition.RoomLR_N1 = ImageIO.read(new File("src/img/RoomLR_-1.png"));

            Expedition.RoomLFR = ImageIO.read(new File("src/img/RoomLFR.png"));

        }catch(IOException e){
            throw new RuntimeException(e);
        }

        Expedition.ocPosition = new Vector2(80, 40);

        JFrame frame = new JFrame("Expedition");
        Expedition.frame = frame;
        frame.setContentPane(new Expedition().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.pack();
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.requestFocus();
    }
}