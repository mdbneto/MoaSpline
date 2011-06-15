package org.interfaceGrafica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import javax.swing.JPanel;

/**
 *
 * @author Moacyr Dias Baptista Neto (mdbneto@gmail.com)
 * A obra MoaSpline de Moacyr Dias Baptista Neto foi licenciada com uma Licença Creative Commons - Atribuição - Uso Não Comercial - Partilha nos Mesmos Termos 3.0 Não Adaptada.
 */
public class ImagemPanel extends JPanel {

    static DecimalFormat nf = new DecimalFormat("#0.00");
    //tamanho do panel
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    //posições dos pontos na tela
    public double[] x;
    public double[] y;
    //número de pontos setados
    int n = 0;
    //posição do mouse
    private Integer posMouseX = 0;
    private Integer posMouseY = 0;
    //vetores da spline
    public double[] xSpline;
    public double[] ySpline;
    public boolean desenhaSpline = false;
    //imagem de fundo
    private static Image fundo;

    public ImagemPanel() {
        //faz o cursor virar uma seta dentro do panel
        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        //define o tamanho do panel
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        //cria um ouvinte (listener) para o mouse
        MouseHandler handler = new MouseHandler();
        this.addMouseMotionListener(handler);
        this.addMouseListener(handler);

        //adiciona o panel de imagem de fundo

    }

    //classe abstrata de ouvinte do mouse
    private class MouseHandler implements MouseMotionListener, MouseListener {

        @Override
        public void mouseDragged(MouseEvent event) {
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            //Calibragem
//            System.out.println("Mouse clicou. Pos x:"
//                    + nf.format((((double) event.getX()) / 100)) + "; " + event.getX() + " Pos y: " + nf.format((((double) event.getY() - 600) / -100))
//                    + "; " + event.getY());
            if (n == 0) {
                x = new double[800];
                y = new double[600];
            }
            posMouseX = event.getX();
            posMouseY = event.getY();
            x[n] = event.getX();
            y[n] = event.getY();
//            System.out.println("\nPontos");
//            for (int i = 0; i <= n; i++) {
//                System.out.println("X = " + x[i] + "; Y = " + y[i]);
//            }
            n++;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    //método chamdo no repaint()
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        fundo = JanelaFrame.fundo;
        g2.drawImage(fundo, 0, 1, new Color(255, 255, 255), null);
        g2.setColor(Color.red);
        //desenha os pontos selecionados pelo mouse
        for (int i = 0; i < n; i++) {
            if (x[i] != 0 & y[i] != 0) {
                g2.draw(new Ellipse2D.Double(x[i], y[i], 4, 4));
            }
        }
        //desenha a spline
        if (desenhaSpline) {
            g2.setColor(Color.orange);
            g2.setStroke(new BasicStroke(3));
            for (int i = 0; i < xSpline.length-1; i++) {
//                 g2.draw(new Ellipse2D.Double(xSpline[i], ySpline[i], 2, 2));
//                g2.draw(new Line2D.Double(xSpline[i], ySpline[i], 2, 2));
                if (ySpline[i + 1] != 600) {
                    g2.draw(new Line2D.Double(xSpline[i], ySpline[i], xSpline[i + 1], ySpline[i + 1]));
                }
            }
        }

    }
}
