package JFrame;

import dao.WhDao;
import model.Goods;
import model.Wh;
import utils.DbUtil;
import utils.ToolUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class WhFrame{
    static WhDao whDao = new WhDao();
      static class CheckWh extends JPanel{
          private  DefaultTableModel model1;
          public CheckWh(){
                setLayout(null);
                JPanel panel = new JPanel();
                panel.setBorder(new TitledBorder(null,"仓库查询",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
                panel.setBounds(10,10,560,80);
                panel.setLayout(null);
                add(panel);

                JComboBox<String> comboBox;
                comboBox = new JComboBox<>();
                comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                comboBox.setBounds(75, 28, 90, 24);
                comboBox.addItem("仓库名称");
                comboBox.addItem("仓库地址");
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

                String[] title={"仓库编号", "仓库名称", "仓库类别"};
                String[][] dates={};
                DefaultTableModel model = new DefaultTableModel(dates, title);
                JTable table = new JTable(model);
                model1 = (DefaultTableModel) table.getModel();
                show(new Wh());
                JScrollPane jScrollPane = new JScrollPane();
                jScrollPane.setBounds(20, 20, 520, 260);
                jScrollPane.setViewportView(table);
                panel1.add(jScrollPane);

                /* 事件 */
                checkButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                           int index = comboBox.getSelectedIndex();
                        if(index==0){
                        String Wname = textField.getText();
                        Wh wh = new Wh();
                        wh.setWName(Wname);
                        show(wh);
                    }else{
                        String Wadd = textField.getText();
                        Wh wh = new Wh();
                        wh.setWAdd(Wadd);
                        show(wh);
                    }
                }
              });
          }
          //放置数据
          private void show(Wh wh){
              model1.setRowCount(0);
              Connection con = null;
              try {
                  con = DbUtil.getConnection();
                  ResultSet resultSet = whDao.findWh(con,wh);

                  while (resultSet.next()){
                      Vector<Serializable> Data = new Vector<>();
                      Data.add(resultSet.getString("wid"));
                      Data.add(resultSet.getString("wname"));
                      Data.add(resultSet.getString("wadd"));
                      model1.addRow(Data);
                  }
                  con.close();
              } catch (Exception e) {
                  e.printStackTrace();
                  throw new RuntimeException(e);
              }
          }
    }

    static class EditWh extends JPanel{

            private  DefaultTableModel model1;
          public EditWh(){
              setLayout(null);
              
              /* 信息显示界面 */
              JPanel panel = new JPanel();
              panel.setBorder(new TitledBorder(null,"信息显示",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
              panel.setBounds(10, 10, 560, 150);
              panel.setLayout(null);
              add(panel);

              String[] title={"仓库编号", "仓库名称", "仓库类别" };
              String[][] dates={};
              DefaultTableModel model = new DefaultTableModel(dates, title);
              JTable table = new JTable(model);
              model1 = (DefaultTableModel) table.getModel();
              show(new Wh());
              JScrollPane jScrollPane = new JScrollPane();
              jScrollPane.setBounds(10, 15, 540, 125);
              jScrollPane.setViewportView(table);
              panel.add(jScrollPane);


              /* 修改界面 */
              JPanel panel1 = new JPanel();
              panel1.setBorder(new TitledBorder(null,"仓库修改",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
              panel1.setBounds(10, 170, 270, 250);
              panel1.setLayout(null);
              add(panel1);

              JLabel label = new JLabel("所要修改的仓库名:");
              label.setFont(new Font("微软雅黑", Font.BOLD, 14));
              label.setBounds(25,48,130,24);

              JTextField modWhname = new JTextField();
              modWhname.setBounds(150, 50, 90, 24);

              JLabel label1 = new JLabel("仓库地址：");
              label1.setFont(new Font("微软雅黑", Font.BOLD, 14));
              label1.setBounds(25,108,80,24);

              JTextField modWadd = new JTextField();
              modWadd.setBounds(100, 110, 90, 24);

              JButton mod = new JButton("修改");
              mod.setFont(new Font("微软雅黑", Font.BOLD, 14));
              mod.setBounds(150,180,90,30);

              panel1.add(modWhname);
              panel1.add(modWadd);
              panel1.add(label);
              panel1.add(label1);
              panel1.add(mod);


              /* 添加界面 */
              JPanel panel2 = new JPanel();
              panel2.setBorder(new TitledBorder(null,"仓库添加",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
              panel2.setBounds(290, 170, 280, 150);
              panel2.setLayout(null);
              add(panel2);

              JLabel label2 = new JLabel("仓库编号:");
              label2.setFont(new Font("微软雅黑", Font.BOLD, 14));
              label2.setBounds(10,20,70,24);

              JTextField addWid = new JTextField();
              addWid.setBounds(80, 22, 70, 24);

              JLabel label3 = new JLabel("仓库名称:");
              label3.setFont(new Font("微软雅黑", Font.BOLD, 14));
              label3.setBounds(10,60,70,24);

              JTextField addWhname = new JTextField();
              addWhname.setBounds(80, 62, 70, 24);

              JLabel label4 = new JLabel("仓库地址：");
              label4.setFont(new Font("微软雅黑", Font.BOLD, 14));
              label4.setBounds(10,100,70,24);

              JTextField addWadd = new JTextField();
              addWadd.setBounds(80, 102, 70, 24);

              JButton add = new JButton("添加");
              add.setFont(new Font("微软雅黑", Font.BOLD, 14));
              add.setBounds(180,60,70,30);

              panel2.add(label2);
              panel2.add(addWid);
              panel2.add(label3);
              panel2.add(addWhname);
              panel2.add(label4);
              panel2.add(addWadd);
              panel2.add(add);

              /* 删除界面 */
              JPanel panel3 = new JPanel();
              panel3.setBorder(new TitledBorder(null,"仓库删除",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
              panel3.setBounds(290, 320, 280, 100);
              panel3.setLayout(null);
              add(panel3);

              JLabel label5 = new JLabel("需要删除的仓库名:");
              label5.setFont(new Font("微软雅黑", Font.BOLD, 14));
              label5.setBounds(20,20,130,24);

              JTextField deleteWname = new JTextField();
              deleteWname.setBounds(145, 22, 70, 24);

              JButton delete = new JButton("删除");
              delete.setFont(new Font("微软雅黑", Font.BOLD, 14));
              delete.setBounds(170,60,70,25);

              panel3.add(deleteWname);
              panel3.add(label5);
              panel3.add(delete);

              /* 事件 */

              //点击表格获取数据
              table.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mousePressed(MouseEvent e) {
                      int row = table.getSelectedRow();
                      String Wname = (String) table.getValueAt(row,1);
                      Wh wh = new Wh();
                      wh.setWName(Wname);
                      Connection connection = null;
                      try{
                          connection = DbUtil.getConnection();
                          ResultSet resultSet = whDao.findWh(connection,wh);
                          if (resultSet.next()){
                              modWhname.setText(resultSet.getString("wname"));
                              deleteWname.setText(resultSet.getString("wname"));
                              modWadd.setText(resultSet.getString("Wadd"));
                          }
                          connection.close();
                      }catch (Exception exception){
                          exception.printStackTrace();
                      }
                  }
              });

              //点击修改
              mod.addActionListener(e -> {
                  JOptionPane.showConfirmDialog(null, "确定修改？", "确定修改", JOptionPane.YES_NO_OPTION);
                  String Wadd = modWadd.getText();
                  String Wname = modWhname.getText();
                  if (ToolUtil.isEmpty(Wname)){
                      JOptionPane.showMessageDialog(null, "请输入要修改的仓库名");
                  }
                  if (ToolUtil.isEmpty(Wadd)){
                      JOptionPane.showMessageDialog(null, "请输入修改后的仓库类型");
                  }
                  Wh wh = new Wh();
                  wh.setWAdd(Wadd);
                  wh.setWName(Wname);
                  Connection connection = null;
                  try {
                      connection = DbUtil.getConnection();
                      int i = whDao.updateWh(connection,wh);
                      if (i == 1) {
                          JOptionPane.showMessageDialog(null, "修改成功");
                      } else {
                          JOptionPane.showMessageDialog(null, "修改失败(仓库名无法修改)");
                      }
                      connection.close();
                  }catch (Exception exception){
                      exception.printStackTrace();
                  }
                  show(new Wh());
              });

              //点击添加
              add.addActionListener(e -> {
                  String Wid = addWid.getText();
                  String Wname = addWhname.getText();
                  String Wadd = addWadd.getText();
                  if (ToolUtil.isEmpty(Wadd) || ToolUtil.isEmpty(Wid) || ToolUtil.isEmpty(Wname)){
                      JOptionPane.showMessageDialog(null, "请输入需要添加的完整内容");
                  }
                  Wh wh = new Wh();
                  wh.setWid(Wid);
                  wh.setWName(Wname);
                  wh.setWAdd(Wadd);
                  Connection connection = null;
                  try{
                      connection = DbUtil.getConnection();
                      int i = whDao.addWh(connection,wh);
                      if (i==1){
                          JOptionPane.showMessageDialog(null,"成功添加"+ Wname,"成功添加",JOptionPane.PLAIN_MESSAGE);
                      }else if (i == 2){
                          JOptionPane.showMessageDialog(null,"已经存在该仓库","添加失败",JOptionPane.ERROR_MESSAGE);
                      }else{
                          JOptionPane.showMessageDialog(null,"仓库编号重复","添加失败",JOptionPane.ERROR_MESSAGE);
                      }
                      connection.close();
                  } catch (Exception ex) {
                      throw new RuntimeException(ex);
                  }
                  show(new Wh());
              });

              //点击删除
              delete.addActionListener(e -> {
                  JOptionPane.showConfirmDialog(null, "确定删除？", "确定删除", JOptionPane.YES_NO_OPTION);
                  String Wname = deleteWname.getText();
                  if (ToolUtil.isEmpty(Wname)){
                      JOptionPane.showMessageDialog(null, "请输入需要删除的仓库名");
                  }
                  Connection connection = null;
                  try{
                      connection = DbUtil.getConnection();
                      int i = whDao.delWh(connection, Wname);
                      if (i == 1){
                          JOptionPane.showMessageDialog(null,"成功删除"+ Wname,"成功删除",JOptionPane.PLAIN_MESSAGE);
                      }else {
                          JOptionPane.showMessageDialog(null,"删除失败"+ Wname,"删除失败",JOptionPane.ERROR_MESSAGE);
                      }
                      connection.close();
                  } catch (Exception ex) {
                      throw new RuntimeException(ex);
                  }
                  show(new Wh());
              });
          }
        private void show(Wh wh){
            model1.setRowCount(0);
            Connection con = null;
            try {
                con = DbUtil.getConnection();
                ResultSet resultSet = whDao.findWh(con,wh);

                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("wid"));
                    Data.add(resultSet.getString("wname"));
                    Data.add(resultSet.getString("wadd"));
                    model1.addRow(Data);
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
