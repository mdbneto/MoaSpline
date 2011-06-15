package org.testes;

import java.text.DecimalFormat;
import org.spline.CalculaSpline;
import java.util.Arrays;

/**
 *
 * @author Moacyr Dias Baptista Neto (mdbneto@gmail.com)
 */
public class TesteSpline {
    static DecimalFormat nf = new DecimalFormat("##.00000");
    
    public static void main(String[] args){
        int n = 4;
        double x[] = {0, 1, 0.5, 1.5, 2};
        double y[] = {3, 1.8616, -0.5571, -4.1987, -9.0536};
        
        System.out.println(x[1] + " - "+ x[2]);
        Arrays.sort(x);
        System.out.println(x[1] + " - "+ x[2]);
        
        CalculaSpline.interpola(n, x, y);
        
        double a[] = CalculaSpline.getA();
        double b[] = CalculaSpline.getB();
        double c[] = CalculaSpline.getC();
        double d[] = CalculaSpline.getD();
        
        System.out.println("\nTestes da Spline \n"
                + "Resultados esperados:\n"
                + "a1 = -2.21803; b1 = -3.32705; c1 = -3.38582; d1 = 1.8616\n"
                + "a2 = 0.84775; b2 = 2.05542; c2 = -6.07705; d2 = -0.5571\n"
                + "a3 = -0.71375; b3 = -3.12605; c3 = -8.6679; d3 = -4.1987\n"
                + "a4 = 2.08403; b4 = 0.0; c4 = -10.2308; d4 = -9.0536\n\n"
                + "Resultados obtidos:\n"
                + "a1 = " + a[1] + "; b1 = " + b[1] + "; c1 = " + c[1] + "; d1 = " + d[1] + "\n"
                + "a2 = " + a[2] + "; b2 = " + b[2] + "; c2 = " + c[2] + "; d2 = " + d[2] + "\n"
                + "a3 = " + a[3] + "; b3 = " + b[3] + "; c3 = " + c[3] + "; d3 = " + d[3] + "\n"
                + "a4 = " + a[4] + "; b4 = " + b[4] + "; c4 = " + c[4] + "; d4 = " + d[4] + "\n");
        
    }
}
