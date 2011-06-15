/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.testes;

import java.text.DecimalFormat;
import org.spline.DefineCurva;
import java.util.Arrays;

/**
 *
 * @author moa
 */
public class TesteSpline2 {

    static DecimalFormat nf = new DecimalFormat("##.00");

    public static void main(String[] args) {
        int n = 4;
        double x[] = {0, 0.5, 1.0, 1.5, 2.0};
        double y[] = {3, 1.8616, -0.5571, -4.1987, -9.0536};

        DefineCurva.spline(n, x, y);

        double[] r = DefineCurva.getR();

        System.out.println("\nTestes da Spline \n");
        for (int i = 0; i < r.length; i++) {
            System.out.println("Resultados obtidos:\n"
                    + "i =  " + nf.format(i * 0.01) + "; r = " + nf.format(r[i]));
        }

    }
}
