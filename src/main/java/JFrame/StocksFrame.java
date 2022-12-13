package JFrame;

import dao.StocksDao;
import model.Goods;
import model.Stocks;
import model.User;
import utils.DbUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class StocksFrame {
    static StocksDao stocksDao = new StocksDao();
    static class CheckStocks extends JPanel {
        private  DefaultTableModel model1;
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
            model1 = (DefaultTableModel) table.getModel();
            show(new Stocks());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(20, 22, 520, 260);
            jScrollPane.setViewportView(table);
            panel1.add(jScrollPane);

            /* 事件 */
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = comboBox.getSelectedIndex();
                    if(index==0){
                        String Gname = textField.getText();
                        Stocks stocks =new Stocks();
                        stocks.setGName(Gname);
                        show(stocks);
                    }else{
                        String Wname = textField.getText();
                        Stocks stocks =new Stocks();
                        stocks.setWName(Wname);
                        show(stocks);
                    }
                }
            });
        }

        //放置数据
        private void show(Stocks stocks){
            model1.setRowCount(0);
            Connection connection = null;
            try{
                connection = DbUtil.getConnection();
                ResultSet resultSet = stocksDao.show(connection,stocks);
                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("sid"));
                    Data.add(resultSet.getString("Gname"));
                    Data.add(resultSet.getString("Wname"));
                    Data.add(resultSet.getInt("SStocks"));
                    model1.addRow(Data);
                }
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    static class EditStocks extends JPanel{
        public EditStocks(){
        }
    }
}
