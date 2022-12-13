package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;


public class UserAdminFrame extends JFrame {

    private JFrame jFrame;
    private JPanel jPanel;
    CardLayout cardLayout = new CardLayout();
    public UserAdminFrame(){
        jFrame = new JFrame("普通管理员界面");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600,500);
        jFrame.setLocationRelativeTo(null);                 //窗口设置中央，对有ImagePanelPanel面板的窗口无效
        jFrame.setLayout(null);                             //布局不受限制
        jFrame.setResizable(false);                         //禁用最大化窗口,无法更改
        jFrame.setVisible(true);                            //可视化

        /* 菜单栏 */
        JMenuBar menuBar = new JMenuBar();

        JMenu whMenu = new JMenu("仓库");
        JMenu goodsMenu = new JMenu("货物");
        JMenu stocksMenu = new JMenu("库存");
        JMenu isMenu = new JMenu("入库");
        JMenu osMenu = new JMenu("出库");
        JMenu exitMenu = new JMenu("退出");

        JMenuItem whIt = new JMenuItem("仓库查询");
        JMenuItem gIt = new JMenuItem("货物查询");
        JMenuItem sIt = new JMenuItem("库存查询");
        JMenuItem iIt = new JMenuItem("入库查询");
        JMenuItem iIt_1 = new JMenuItem("入库管理");
        JMenuItem oIt = new JMenuItem("出库查询");
        JMenuItem oIt_1 = new JMenuItem("出库管理");

        whMenu.add(whIt);
        goodsMenu.add(gIt);
        stocksMenu.add(sIt);
        isMenu.add(iIt);
        isMenu.add(iIt_1);
        osMenu.add(oIt);
        osMenu.add(oIt_1);

        menuBar.add(whMenu);
        menuBar.add(goodsMenu);
        menuBar.add(stocksMenu);
        menuBar.add(isMenu);
        menuBar.add(osMenu);
        menuBar.add(exitMenu);
        jFrame.setJMenuBar(menuBar);

        /* panel面板栏 */
        jPanel = new JPanel(null);
        //jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        jFrame.setContentPane(jPanel);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,600,600);
        jPanel.add(panel);
        panel.setLayout(cardLayout);

        WhFrame.CheckWh checkWh = new WhFrame.CheckWh();
        GoodsFrame.CheckGoods checkGoods = new GoodsFrame.CheckGoods();
        StocksFrame.CheckStocks checkStocks = new StocksFrame.CheckStocks();
        ISFrame.CheckIS checkIS = new ISFrame.CheckIS();
        ISFrame.EditIS editIS = new ISFrame.EditIS();
        OSFrame.CheckOS checkOS =new OSFrame.CheckOS();
        OSFrame.EditOS editOS = new OSFrame.EditOS();


        panel.add(checkWh,"CheckWh");
        panel.add(checkGoods,"CheckGoods");
        panel.add(checkStocks,"CheckStocks");
        panel.add(checkIS,"CheckIS");
        panel.add(editIS,"EditIS");
        panel.add(checkOS,"CheckOS");
        panel.add(editOS,"EditOS");

        /* 修改Windows图标 */
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/picture/icon.png")));
        jFrame.setIconImage(imageIcon.getImage());


        /* 事件 */
        exitMenu.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "欢迎再次使用");
                jFrame.dispose();
            }
        });

        whIt.addActionListener(e -> cardLayout.show(panel,"CheckWh"));
        gIt.addActionListener(e -> cardLayout.show(panel,"CheckGoods"));
        sIt.addActionListener(e -> cardLayout.show(panel,"CheckStocks"));
        iIt.addActionListener(e -> cardLayout.show(panel,"CheckIS"));
        iIt_1.addActionListener(e -> cardLayout.show(panel,"EditIS"));
        oIt.addActionListener(e -> cardLayout.show(panel,"CheckOS"));
        oIt_1.addActionListener(e ->cardLayout.show(panel,"EditOS"));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater( ()-> new UserAdminFrame());
    }
}

