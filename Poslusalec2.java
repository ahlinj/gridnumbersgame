import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Poslusalec2 implements ActionListener{

    public Gumb[][] gumbi;
    public JButton stevec;
    public JButton vsotaStevec;

    public Gumb poteze;

    public JButton izgubil;

    public JFrame okno;

    public JButton zmagal;
    public JButton[] tezavnost;
    public char[] operacije;
    public JButton trenutnaOperacija1;
    public JButton trenutnaOperacija2;
    public JButton trenutnaOperacija3;

    public Poslusalec2(Gumb[][] gumbi, JButton stevec, JButton vsotaStevec, Gumb poteze, JButton izgubil, JFrame okno, JButton zmagal, JButton[] tezavnost, char[] operacije, JButton trenutnaOperacija1, JButton trenutnaOperacija2, JButton trenutnaOperacija3) {
        this.gumbi = gumbi;
        this.stevec = stevec;
        this.vsotaStevec = vsotaStevec;
        this.poteze = poteze;
        this.izgubil = izgubil;
        this.okno = okno;
        this.zmagal = zmagal;
        this.tezavnost = tezavnost;
        this.operacije = operacije;
        this.trenutnaOperacija1 = trenutnaOperacija1;
        this.trenutnaOperacija2 = trenutnaOperacija2;
        this.trenutnaOperacija3 = trenutnaOperacija3;
    }

    int trenutnaPotezaX;
    int trenutnaPotezaY;
    int trenutnaVrednostGumba;
    int prejsnjaVrednostGumba;
    int stPotez = -2;


    @Override
    public void actionPerformed(ActionEvent e){
        Gumb gumb1 = (Gumb) e.getSource();

        //za debugat
        /*
        for (int i = 0; i < gumbi.length; i++) {
            for (int j = 0; j < gumbi.length; j++) {
                if (gumb1 == gumbi[i][j]) {
                    System.out.println("Pritisnil si gumb " + gumbi[i][j].x + "," + gumbi[i][j].y + " number: " + gumbi[i][j].number);
                }
            }
        }
         */

        //po vsaki potezi pocistimo prejsnjo polje
        for (int i = 0; i < gumbi.length; i++) {
            for (int j = 0; j < gumbi.length; j++) {
                gumbi[i][j].setBackground(Color.LIGHT_GRAY);
                gumbi[i][j].setEnabled(true);
            }
        }

        //pred vsako igro mora igralec izbrati tezavnost
        if (stPotez == -2){
            if (gumb1 == tezavnost[0]){
                poteze.number = 13;
                poteze.setText(String.valueOf(13));
                for (int i = 0; i < tezavnost.length; i++) {
                    tezavnost[i].setEnabled(false);
                }
            } else if (gumb1 == tezavnost[1]) {
                poteze.number = 9;

                poteze.setText(String.valueOf(9));
                for (int i = 0; i < tezavnost.length; i++) {
                    tezavnost[i].setEnabled(false);
                }
            }else if (gumb1 == tezavnost[2]){
                poteze.number = 5;
                poteze.setText(String.valueOf(5));
                for (int i = 0; i < tezavnost.length; i++) {
                    tezavnost[i].setEnabled(false);
                }
            }
            for (int i = 0; i < gumbi.length; i++) {
                for (int j = 0; j < gumbi.length; j++) {
                    gumbi[i][j].setEnabled(true);
                }
            }
            stPotez++;
        }



        //prikaze trenutno operacijo in se dve naslednje
        if (poteze.number != 0) {
            trenutnaOperacija1.setText("1: " + operacije[poteze.number - 1]);
        }else {
            trenutnaOperacija1.setText("1: ");
        }
        if (poteze.number > 1) {
            trenutnaOperacija2.setText("2: " + operacije[poteze.number - 2]);
        }else {
            trenutnaOperacija2.setText("2: ");
        }
        if (poteze.number > 2) {
            trenutnaOperacija3.setText("3: " +operacije[poteze.number - 3]);
        }else {
            trenutnaOperacija3.setText("3: ");
        }

        if (stPotez > -1) {
            for (int i = 0; i < gumbi.length; i++) {
                for (int j = 0; j < gumbi.length; j++) {
                    if (gumb1 == gumbi[i][j]) {
                        trenutnaPotezaX = i;        //zapomnimo si X in Y od gumba ki ga igralec klikne in
                        trenutnaPotezaY = j;
                        prejsnjaVrednostGumba = trenutnaVrednostGumba;
                        trenutnaVrednostGumba = Integer.parseInt(gumbi[i][j].getText());       //zapomnimo si vrednost gumba ki ga igralec klikne
                        gumbi[i][j].setBackground(Color.BLACK);
                        gumbi[i][j].setEnabled(false);

                        if (stPotez > 0) {      //ko klikne na prvi gumb ne izracunaj nicesar
                            if (operacije[poteze.number] == '+') {
                                gumbi[i][j].setText(String.valueOf((gumbi[i][j].number += prejsnjaVrednostGumba) % 10));
                            } else if (operacije[poteze.number] == '-') {
                                gumbi[i][j].setText(String.valueOf((gumbi[i][j].number -= prejsnjaVrednostGumba) % 10));
                                int tmpMinus = Integer.parseInt(gumbi[i][j].getText());
                                if (tmpMinus < 0) {         //ce je razlika negativna jo nastavi na minimalno vrednost (0)
                                    tmpMinus *= 0;
                                    gumbi[i][j].setText(String.valueOf(tmpMinus));
                                }
                            } else if (operacije[poteze.number] == '*') {
                                gumbi[i][j].setText(String.valueOf((gumbi[i][j].number *= prejsnjaVrednostGumba) % 10));
                            } else if (operacije[poteze.number] == '/') {
                                if (prejsnjaVrednostGumba == 0) {       //ce delimo z 0 ne spremeni nobene vrednosti
                                    continue;
                                } else {
                                    gumbi[i][j].setText(String.valueOf((gumbi[i][j].number /= prejsnjaVrednostGumba) % 10));
                                }
                            }
                        }
                        gumbi[i][j].number = Integer.parseInt(gumbi[i][j].getText());
                        trenutnaVrednostGumba = Integer.parseInt(gumbi[i][j].getText());      //shrani si spremenjeno vrednost gumba
                    }
                }
            }


            //pobarvaj stolpec in vrstico
            for (int i = 0; i < gumbi.length; i++) {
                for (int j = 0; j < gumbi.length; j++) {
                    gumbi[trenutnaPotezaX][j].setBackground(Color.DARK_GRAY);
                    gumbi[i][trenutnaPotezaY].setBackground(Color.DARK_GRAY);
                }
            }

            for (int i = 0; i < gumbi.length; i++) {
                for (int j = 0; j < gumbi.length; j++) {
                    gumbi[trenutnaPotezaX][trenutnaPotezaY].setBackground(Color.BLACK);
                }
            }

            //ce je gumb siv ga ne more kliknit
            for (int i = 0; i < gumbi.length; i++) {
                for (int j = 0; j < gumbi.length; j++) {
                    if (gumbi[i][j].getBackground().equals(Color.LIGHT_GRAY)) {
                        gumbi[i][j].setEnabled(false);
                    }
                }
            }
        }

        //po vsaki potezi updejtaj vsoto spodaj
        int n = 0;
        for (int i = 0; i < gumbi.length; i++) {
            for (int j = 0; j < gumbi.length; j++) {
                n += gumbi[i][j].number;
            }
        }
        vsotaStevec.setText(""+n);

        //ce sta imasta gumba zgoraj in spodaj enake vrednosti prikazi zmagovalni endscreen
        if (Objects.equals(stevec.getText(), vsotaStevec.getText())){
            for (int i = 0; i < gumbi.length; i++) {
                for (int j = 0; j < gumbi.length; j++) {
                    gumbi[i][j].setEnabled(false);
                }
            }
            okno.add(zmagal, BorderLayout.CENTER);
        }

        //po vsaki potezi povecaj/pomanjsaj stevilo potez
        stPotez++;
        poteze.number--;
        poteze.setText(String.valueOf(poteze.number));

        //ce mu zmanjka potez prikazi izgubljeni endscreen
        //izracunaj tudi za koliko tock je izgubil
        if (poteze.number == 0){
            for (int i = 0; i < gumbi.length; i++) {
                for (int j = 0; j < gumbi.length; j++) {
                    gumbi[i][j].setEnabled(false);
                }
            }

            int stevecKoncni = Integer.parseInt(stevec.getText());
            int vsotaStevecKoncni = Integer.parseInt(vsotaStevec.getText());
            int skupnaKoncnaVsota = stevecKoncni-vsotaStevecKoncni;
            if (skupnaKoncnaVsota > 0) {
                izgubil.setText("YOU LOST. YOU WERE "+skupnaKoncnaVsota+" POINTS OFF!");
                okno.add(izgubil, BorderLayout.CENTER);
            }else {
                skupnaKoncnaVsota *= -1;
                izgubil.setText("YOU LOST. YOU WERE "+skupnaKoncnaVsota+" POINTS OFF!");
                okno.add(izgubil, BorderLayout.CENTER);
            }
        }

    }
}
