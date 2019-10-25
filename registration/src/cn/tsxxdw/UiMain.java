package cn.tsxxdw;
//练习网格布局

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UiMain implements ActionListener {
    Register register = new Register();
    JTextField t1;
    JTextField t2;
    JFrame jf;

    public UiMain() {
        jf = new JFrame("java");
        JLabel jl = new JLabel("机器码");
        t1 = new JTextField(30);
        JLabel j2 = new JLabel("注册码");
        t2 = new JTextField(30);
        JButton b1 = new JButton("注册");
        b1.addActionListener(this);
        JPanel jp = new JPanel();
        jp.add(jl);
        jp.add(t1);
        jp.add(j2);
        jp.add(t2);
        jp.add(b1);
        jf.add(jp);
        jf.setSize(400, 400);//宽 高
        jf.setLocation(700, 300);
        jf.setVisible(true);   //显示按钮
    }

    public static void main(String[] args) {
        new UiMain();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("aaza");
        String name = e.getActionCommand();//返回与此动作相关的命令字符串
        if (name.equals("注册")) {
            String cpuId = t1.getText();
            String result = register.getResult(cpuId);
            t2.setText(result);

        }
    }
}


