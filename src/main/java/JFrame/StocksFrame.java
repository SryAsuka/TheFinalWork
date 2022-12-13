package JFrame;

import model.Goods;
import model.Stocks;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StocksFrame {

    static class CheckStocks extends JPanel {
        public CheckStocks(){
            setLayout(null);
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"库存查询",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10,10,560,80);
            panel.setLayout(null);
            add(panel);

            JComboBox<String> comboBox;
            comboBox = new JComboBox<>();
            comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            comboBox.setBounds(75, 28, 90, 24);
            comboBox.addItem("货物名称");
            comboBox.addItem("仓库名称");
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

            String[] title={"编号", "货物名称", "仓库名称","库存量"};
            String[][] dates={};
            DefaultTableModel model = new DefaultTableModel(dates, title);
            JTable table = new JTable(model);
            putData(new Stocks());
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

        //放置数据
        private void putData(Stocks stocks){

        }

    }

    static class EditStocks extends JPanel{
        public EditStocks(){
        }
    }
}
