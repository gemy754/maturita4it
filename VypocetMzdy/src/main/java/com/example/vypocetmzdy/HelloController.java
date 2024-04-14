package com.example.vypocetmzdy;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private TextField hrubaMzda;

    @FXML
    private TextField osobniOhodnoceniField;

    @FXML
    private Text pocetDetiText;

    @FXML
    private Text socialni;

    @FXML
    private Text zdravotni;

    @FXML
    private Text zalohaNaDan;

    @FXML
    private Text Vysledek;

    private int pocetDeti = 0;

    @FXML
    protected void initialize() {
        if (pocetDetiText != null) {
            pocetDetiText.setText(String.valueOf(pocetDeti));
        }
        osobniOhodnoceniField.setText("0");
    }

    @FXML
    protected void Vypocitej() {
        double mzda = Double.parseDouble(hrubaMzda.getText());
        double priplatky = 0; // Zatim nepouzito
        double osobniOhodnoceni = osobniOhodnoceniField.getText().isEmpty() ? 0 : Double.parseDouble(osobniOhodnoceniField.getText());

        double hrubaMzda = vypoctiHrubouMzdu(mzda, priplatky, osobniOhodnoceni);
        double socialniPojisteni = vypoctiSocialniPojisteni(hrubaMzda);
        double zdravotniPojisteni = vypoctiZdravotniPojisteni(hrubaMzda);
        double dan = vypoctiDan(hrubaMzda, pocetDeti);
        double cistaMzda = vypoctiCistouMzdu(hrubaMzda, socialniPojisteni, zdravotniPojisteni, dan);

        socialni.setText(String.valueOf(socialniPojisteni) + " Kč");
        zdravotni.setText(String.valueOf(zdravotniPojisteni) + " Kč");

        if (dan <= 0) {
            zalohaNaDan.setText("Daňový bonus: " + String.valueOf(-dan) + " Kč");
        } else {
            zalohaNaDan.setText(String.valueOf(dan) + " Kč");
        }

        Vysledek.setText(String.valueOf(cistaMzda) + " Kč");
    }

    @FXML
    protected void pridejDite() {
        pocetDeti++;
        if (pocetDetiText != null) {
            pocetDetiText.setText(String.valueOf(pocetDeti));
        }
    }

    @FXML
    protected void odeberDite() {
        if (pocetDeti > 0) {
            pocetDeti--;
            if (pocetDetiText != null) {
                pocetDetiText.setText(String.valueOf(pocetDeti));
            }
        }
    }

    private double vypoctiHrubouMzdu(double mzda, double priplatky, double osobniOhodnoceni) {
        return mzda + priplatky + osobniOhodnoceni;
    }

    private double vypoctiSocialniPojisteni(double hrubaMzda) {
        return hrubaMzda * 0.065;
    }

    private double vypoctiZdravotniPojisteni(double hrubaMzda) {
        return hrubaMzda * 0.045;
    }

    private double vypoctiDan(double hrubaMzda, int pocetDeti) {
        double zaklad = Math.ceil(hrubaMzda / 100) * 100;
        double dan = zaklad * 0.15;
        double bonus = 0;
        if (zaklad - 2320 > 0) {
            dan -= 2320;
        } else {
            dan = 0;
        }
        if (pocetDeti == 1) {
            bonus = 1267;
        } else if (pocetDeti == 2) {
            bonus = 1671;
        } else if (pocetDeti >= 3) {
            bonus = 2017;
        }
        return dan - bonus;
    }


    private double vypoctiCistouMzdu(double hrubaMzda, double socialniPojisteni, double zdravotniPojisteni, double dan) {
        return hrubaMzda - socialniPojisteni - zdravotniPojisteni - dan;
    }
}
