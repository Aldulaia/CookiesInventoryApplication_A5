/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aldulaia;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import prog24178.labs.objects.CookieInventoryItem;
import prog24178.labs.objects.Cookies;

/**
 *
 * @author Altaher Al-Dulaimi
 */
public class CookieInventoryFile extends ArrayList<CookieInventoryItem> {

    public CookieInventoryFile() {
    }

    public CookieInventoryFile(File file) {
        loadFromFile(file);
    }

    public CookieInventoryItem find(int flavourId) {

        try {
            CookieInventoryItem i = new CookieInventoryItem();
            i.cookie = Cookies.getCookie(flavourId);
            return i;
        } catch (Exception e) {

            return null;
        }

    }

    public void loadFromFile(File file) {
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String record = sc.nextLine();
                    String[] field = record.split("\\|");

                    CookieInventoryItem i = new CookieInventoryItem();

                    for (Cookies c : Cookies.values()) {
                        if (Integer.parseInt(field[0]) == c.getId()) {
                            i.cookie = c;
                            i.setQuantity(Integer.parseInt(field[1]));

                            super.add(i);
                        }
                    }

                }
                sc.close();
            } catch (Exception a) {

            }
        }

    }

    public void writeToFile(File file) {
        CookieInventoryItem i = new CookieInventoryItem();

        try {
            PrintWriter pr = new PrintWriter(file);
            for (int f = 0; f < super.size(); f++) {

                pr.print(super.get(f) + "\n");

            }
            pr.close();

        } catch (Exception a) {
        }

    }

}
