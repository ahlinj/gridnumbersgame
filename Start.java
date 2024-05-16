import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Start {
    public static void main(String[] args) {
        //okno
        JFrame okno = new JFrame();
        okno.setVisible(true);
        okno.setSize(800, 600);
        okno.setTitle("More or less less is more");
        okno.setDefaultCloseOperation(3);

        //panel(gumbi)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,9));

        //zgornji gumb stevec (ciljna vrednost)
        JButton stevec = new JButton("");
        stevec.setBorderPainted(false);
        stevec.setFocusPainted(false);
        stevec.setEnabled(false);
        stevec.setBackground(Color.BLACK);
        okno.add(stevec, BorderLayout.NORTH);

        //spodnji gumb (vsota vseh cifer)
        JButton vsotaStevec = new JButton("");
        vsotaStevec.setBorderPainted(false);
        vsotaStevec.setFocusPainted(false);
        vsotaStevec.setEnabled(false);
        vsotaStevec.setBackground(Color.BLACK);
        okno.add(vsotaStevec, BorderLayout.SOUTH);

        //panel in gumbi za tezavnost
        JPanel panelTezavnosti = new JPanel();
        panelTezavnosti.setLayout(new GridLayout(3,1));
        JButton[] tezavnost = new JButton[3];
        tezavnost[0] = new Gumb(0,0,0);
        tezavnost[1] = new Gumb(0,0,0);
        tezavnost[2] = new Gumb(0,0,0);
        tezavnost[0].setText("Easy");
        tezavnost[1].setText("Medium");
        tezavnost[2].setText("Hard");
        for (int i = 0; i < tezavnost.length; i++) {
            tezavnost[i].setBackground(Color.BLACK);
            tezavnost[i].setForeground(Color.GRAY);
            panelTezavnosti.add(tezavnost[i]);
        }

        //gumb stevilo potez
        Gumb poteze = new Gumb(8, 0, 0);
        poteze.setBorderPainted(false);
        poteze.setFocusPainted(false);
        poteze.setEnabled(false);
        poteze.setText("8");
        poteze.setBackground(Color.BLACK);

        //endscreen gumba za zmago oz. poraz
        JButton izgubil = new JButton("YOU LOST!");
        izgubil.setBorderPainted(false);
        izgubil.setFocusPainted(false);
        izgubil.setBackground(Color.BLACK);
        izgubil.setForeground(Color.WHITE);

        JButton zmagal = new JButton("YOU WON!");
        zmagal.setBorderPainted(false);
        zmagal.setFocusPainted(false);
        zmagal.setBackground(Color.BLACK);
        zmagal.setForeground(Color.WHITE);

        //81 random stevil od 0 do 9
        Random rn = new Random(System.currentTimeMillis());
        int[] randomNumber = new int[81];
        for (int i = 0; i < 81; i++) {
           randomNumber[i] = rn.nextInt(10);
        }

        //vsakemu gumbu damo eno izmed zgornjih random stevil
        int vsota = 0;
        Gumb[][] gumbi = new Gumb[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gumbi[i][j] = new Gumb(randomNumber[rn.nextInt(81)],i,j);
                gumbi[i][j].setText(String.valueOf(gumbi[i][j].number));
                gumbi[i][j].setBackground(Color.LIGHT_GRAY);
                gumbi[i][j].setForeground(Color.GRAY);
                gumbi[i][j].setEnabled(false);
                panel.add(gumbi[i][j]);
                vsota += gumbi[i][j].number;
            }
        }

        //x je ali -1 ali 1
        //vsoti odstejemo/sestejemo poljubno random vrednost do 10
        int x = ((rn.nextInt(3)-1));
        if (x == 0){
            x += 1;
        }
        int y = vsota+(x*(rn.nextInt(10) + 1));

        vsotaStevec.setText(String.valueOf(vsota));
        stevec.setText(String.valueOf(y));


        //12 random stevil od 0 do 3 (4 moznosti (za vsako operacijo ena))
        int[] randomOperacija = new int[13];
        for (int i = 0; i < randomOperacija.length; i++) {
            randomOperacija[i] = rn.nextInt(4);
        }

        //glede na random stevilo dolocimo operacije za igro
        char[] operacije = new char[13];
        for (int i = 0; i < operacije.length; i++) {
            if (randomOperacija[i] == 0){
                operacije[i] = '+';
            } else if (randomOperacija[i] == 1) {
                operacije[i] = '-';
            } else if (randomOperacija[i] == 2) {
                operacije[i] = '*';
            } else if (randomOperacija[i] == 3) {
                operacije[i] = '/';
            }
        }

        //gumbi/panel za displayat trenutne operacije
        JButton trenutnaOperacija1 = new JButton("1: ");
        trenutnaOperacija1.setBorderPainted(false);
        trenutnaOperacija1.setFocusPainted(false);
        trenutnaOperacija1.setEnabled(false);
        trenutnaOperacija1.setBackground(Color.BLACK);

        JButton trenutnaOperacija2 = new JButton("2: ");
        trenutnaOperacija2.setBorderPainted(false);
        trenutnaOperacija2.setFocusPainted(false);
        trenutnaOperacija2.setEnabled(false);
        trenutnaOperacija2.setBackground(Color.BLACK);

        JButton trenutnaOperacija3 = new JButton("3: ");
        trenutnaOperacija3.setBorderPainted(false);
        trenutnaOperacija3.setFocusPainted(false);
        trenutnaOperacija3.setEnabled(false);
        trenutnaOperacija3.setBackground(Color.BLACK);

        JPanel panelDesno = new JPanel();
        panelDesno.setLayout(new GridLayout(4,1));
        panelDesno.add(poteze, 0);
        panelDesno.add(trenutnaOperacija3,1);
        panelDesno.add(trenutnaOperacija2,2);
        panelDesno.add(trenutnaOperacija1, 3);

        //dodamo poslusalca vsem gumbom
        Poslusalec2 p = new Poslusalec2(gumbi, stevec, vsotaStevec, poteze, izgubil, okno, zmagal, tezavnost, operacije, trenutnaOperacija1, trenutnaOperacija2, trenutnaOperacija3);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gumbi[i][j].addActionListener(p);
            }
        }

        for (int i = 0; i < tezavnost.length; i++) {
            tezavnost[i].addActionListener(p);
        }


        //dodamo panele na okno
        okno.add(panel);
        okno.add(panelTezavnosti, BorderLayout.WEST);
        okno.add(panelDesno, BorderLayout.EAST);
        okno.revalidate();
    }

}
