import jdk.jfr.Event;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Expedition {
    private JPanel mainPanel;
    private JPanel EventPanel;
    private JPanel ActionPanel;
    private JLabel SideStats;
    private JLabel CharacterIcon;
    private JProgressBar HP;
    private JProgressBar SP;

    private Map map;

    public Expedition(){
        Entity player = new Entity();

        HP.setForeground(new Color(255, 0, 0));
        SP.setForeground(new Color(0, 255, 0));

        Init(player);

        updateMainStats(player);
        updateSideStats(player);

        setActionPanel(1);
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
//                        setActionPanel(2);
//                        map = new Map();
//                        new MapGenerator(map, 20);
//                        map.printAll();

                        Graphics canva = EventPanel.getGraphics();

                        canva.create();

                        canva.drawString("ala ma kota", 20, 50);

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

                ActionPanel.setLayout(new GridLayout(2, 2));

                try{

                    JButton Up = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Up.png"))));
                    Up.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            setActionPanel(3);
                        }
                    });
                    ActionPanel.add(Up);

                    JButton Down = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Down.png"))));
                    Down.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    ActionPanel.add(Down);

                    JButton Left = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Left.png"))));
                    Left.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    ActionPanel.add(Left);

                    JButton Right = new JButton(new ImageIcon(ImageIO.read(new File("src/img/Arrow_Right.png"))));
                    Right.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

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
            default:
                new ErrorDialog("argument 'action' out of bounds in setAction Panel");
                break;
        }
        ActionPanel.updateUI();
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Expedition");
        frame.setContentPane(new Expedition().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}