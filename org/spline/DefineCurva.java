package org.spline;

import java.text.DecimalFormat;

/**
 *
 * @author Moacyr Dias Baptista Neto (mdbneto@gmail.com)
 * A obra MoaSpline de Moacyr Dias Baptista Neto foi licenciada com uma Licença Creative Commons - Atribuição - Uso Não Comercial - Partilha nos Mesmos Termos 3.0 Não Adaptada.
 */
public class DefineCurva {
    static DecimalFormat nf = new DecimalFormat("#0.00");
    static double[] r;
    
    public static void spline(int n, double x[], double y[]){
        
        
        final int T = (int)((x[n]-x[0])*100);
        r = new double[T];
        int k = 0;
        
        CalculaSpline.interpola(n, x, y);
        
        double[] a = CalculaSpline.getA();        
        double[] b = CalculaSpline.getB();
        double[] c = CalculaSpline.getC();
        double[] d = CalculaSpline.getD();
        
//        System.out.println("Valores DefineCurva");
        for(int i = 1; i<=n; i++){
            for(double j = x[i-1]; j <= x[i]; j = j + 0.01){
                if(j > x[i]-0.02){
                    break;
                }
                r[k]  = a[i]*Math.pow(j-x[i], 3);
                r[k] += b[i]*Math.pow(j-x[i], 2);
                r[k] += c[i]*(j-x[i]);
                r[k] += d[i];
//                System.out.println("x = " + nf.format(j) + " y = " + nf.format(r[k]));
                k++;
            }
        }        
        
    }

    public static double[] getR() {
        return r;
    }        
    
}
