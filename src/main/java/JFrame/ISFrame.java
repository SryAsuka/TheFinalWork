package JFrame;

import model.IStocks;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ISFrame {

    static class CheckIS extends JPanel {
        public CheckIS(){
            setLayout(null);
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"入库查询",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10,10,560,80);
            panel.setLayout(null);
            add(panel);

            JComboBox<String> comboBox;
            comboBox = new JComboBox<>();
            comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            comboBox.setBounds(75, 28, 90, 24);
            comboBox.addItem("货物名称");
            comboBox.addItem("仓库名称");
            comboBox.addItem("入库人员");
            comboBox.addItem("入库时间");
            int index = comboBox.getSelectedIndex();
            panel.add(comboBox);

            JTextField textField = new JTextField();
            textField.setBounds(185, 28, 146, 24);
            panel.add(textField);

            JButton checkButton = new JButton("查询");
            checkButton.setBounds(390, 28, 81, 25);
            panel.add(checkButton);

            JPanel panel1 = new JPanel();
            panel1.setBorder(new TitledBorder(null,"查询结果",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel1.setBounds(10, 105, 560, 300);
            panel1.setLayout(null);
            add(panel1);

            String[] title={"入库编号", "货物名称", "仓库名称","入库量","入库管理员","入库时间"};
            String[][] dates={};
            DefaultTableModel model = new DefaultTableModel(dates, title);
            JTable table = new JTable(model);
            putData(new IStocks());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(20, 22, 520, 260);
            jScrollPane.setViewportView(table);
            panel1.add(jScrollPane);

            /* 事件 */
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if(index==0){
                        String bookName = textField.getText();
//                        Book book = new Book();
//                        book.setBookName(bookName);
//                        putDates(book);
                    }else{
                        String authorName = textField.getText();
//                        Book book = new Book();
//                        book.setAuthor(authorName);
//                        putDates(book);
                    }
                }
            });



        }


    }

    static class EditIS extends JPanel {
        public EditIS(){
            setLayout(null);

            /* 信息显示界面 */
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"信息显示",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10, 10, 560, 150);
            panel.setLayout(null);
            add(panel);

            String[] title={"入库编号", "货物名称", "仓库名称","入库量","入库管理员","入库时间"};
            String[][] dates={};
            DefaultTableModel model = new DefaultTableModel(dates, title);
            JTable table = new JTable(model);
            putData(new IStocks());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(10, 15, 540, 125);
            jScrollPane.setViewportView(table);
            panel.add(jScrollPane);




            /* 添加界面 */
            JPanel panel2 = new JPanel();
            panel2.setBorder(new TitledBorder(null,"入库添加",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel2.setBounds(10, 170, 280, 250);
            panel2.setLayout(null);
            add(panel2);

            JLabel label2 = new JLabel("入库编号:");
            label2.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label2.setBounds(10,20,70,24);

            JTextField addIid = new JTextField();
            addIid.setBounds(80, 22, 70, 24);

            JLabel label3 = new JLabel("货物名称:");
            label3.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label3.setBounds(10,60,70,24);

            JTextField addGname = new JTextField();
            addGname.setBounds(80, 62, 70, 24);

            JLabel label4 = new JLabel("仓库名称：");
            label4.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label4.setBounds(10,100,70,24);

            JTextField addWname = new JTextField();
            addWname.setBounds(80, 102, 70, 24);

            JLabel label6 = new JLabel("   入库量：");
            label6.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label6.setBounds(10,140,70,24);

            JTextField addIS = new JTextField();
            addIS.setBounds(80, 142, 70, 24);

            JLabel label1 = new JLabel("入库人员：");
            label1.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label1.setBounds(10,180,70,24);

            JTextField addUname = new JTextField();
            addUname.setBounds(80, 182, 70, 24);

            JButton add = new JButton("添加");
            add.setFont(new Font("微软雅黑", Font.BOLD, 14));
            add.setBounds(180,200,70,30);

            panel2.add(label1);
            panel2.add(label2);
            panel2.add(label3);
            panel2.add(label6);
            panel2.add(label4);
            panel2.add(addGname);
            panel2.add(addIid);
            panel2.add(addWname);
            panel2.add(addIS);
            panel2.add(addUname);
            panel2.add(add);

            /* 删除界面 */
            JPanel panel3 = new JPanel();
            panel3.setBorder(new TitledBorder(null,"入库删除",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel3.setBounds(290, 170, 280, 250);
            panel3.setLayout(null);
            add(panel3);

            JLabel label5 = new JLabel("需要删除的仓库名:");
            label5.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label5.setBounds(20,50,130,24);

            JTextField deleteGname = new JTextField();
            deleteGname.setBounds(145, 52, 70, 24);

            JLabel label = new JLabel("需要删除的货物名:");
            label.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label.setBounds(20,100,130,24);

            JTextField deleteWname = new JTextField();
            deleteWname.setBounds(145, 102, 70, 24);


            JButton delete = new JButton("删除");
            delete.setFont(new Font("微软雅黑", Font.BOLD, 14));
            delete.setBounds(170,180,70,25);

            panel3.add(deleteWname);
            panel3.add(deleteGname);
            panel3.add(label);
            panel3.add(label5);
            panel3.add(delete);
        }
    }


    //放置数据
    private static void putData(IStocks iStocks){

    }

}
