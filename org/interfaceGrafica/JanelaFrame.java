package org.interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.spline.DefineCurva;

/**
 *
 * @author Moacyr Dias Baptista Neto (mdbneto@gmail.com)
 * A obra MoaSpline de Moacyr Dias Baptista Neto foi licenciada com uma Licença Creative Commons - Atribuição - Uso Não Comercial - Partilha nos Mesmos Termos 3.0 Não Adaptada.
 */
public class JanelaFrame extends JFrame {

    private ImagemPanel imagemPanel;
    public static Image fundo;
        public static JFileChooser escolheArquivo;

    public JanelaFrame() {
        setTitle("MoaSpline - Trabalho de Cálculo Numérico");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuBar.add(Opcoes());

        //imagem de fundo
        fundo = (new ImageIcon(getClass().getResource("/img/fundo_branco.jpg"))).getImage();
//        fundo = (new ImageIcon("C:\\Users\\moa\\Pictures\\splineFotos\\juliana_paes_tigresa_100grana.jpg")).getImage();

        imagemPanel = new ImagemPanel();
        getContentPane().add(imagemPanel, BorderLayout.SOUTH);
        pack();
    }

    private JMenu Opcoes() {
        JMenu menu = new JMenu("Opções");
        menu.add(colocarImagem());
        menu.add(limparPontos());
        menu.add(calculaSpline());
        return menu;
    }

    //seleção do menu que limpa os pontos
    public JMenuItem limparPontos() {
        JMenuItem item = new JMenuItem("Limpar Pontos");
        class MenuItemListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                imagemPanel.n = 0;
                imagemPanel.x = null;
                imagemPanel.y = null;
                imagemPanel.desenhaSpline = false;
                imagemPanel.repaint();
            }
        }
        ActionListener listener = new MenuItemListener();
        item.addActionListener(listener);
        return item;
    }

    //seleção do menu que calcula a spline
    public JMenuItem calculaSpline() {
        JMenuItem item = new JMenuItem("Calcular Spline");
        class MenuItemListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                //inicializa os vetores
                int n = imagemPanel.n;
                double[] x = new double[n + 1];
                double[] y = new double[n + 1];
                //transforma para double
                for (int i = 0; i <= imagemPanel.n; i++) {
                    x[i] = (((double) imagemPanel.x[i]) / 100);
                    y[i] = ((((double) imagemPanel.y[i] - 600) / -100));
                }
                //chama o método da classe DefineCurva
                DefineCurva.spline(n - 1, x, y);

                //devolve um vetor r
                double[] r = DefineCurva.getR();

                double[] x2 = new double[r.length];
                x2[0] = x[0] * 100;
                //faz operação inversa e prenche x2   
//                System.out.println("\nSpline");
                for (int i = 0; i < r.length; i++) {
                    r[i] = (r[i] * (-100) + 600);
//                    System.out.println("X:" + x2[i] + "  R:" + r[i]);
                    if (i < r.length - 1) {
                        x2[i + 1] = x2[i] + 1;
                    }
                }
                //passa os paramentros necessários para o imagemPanel e dá um repaint
                imagemPanel.xSpline = x2;
                imagemPanel.ySpline = r;
                imagemPanel.desenhaSpline = true;
                imagemPanel.repaint();
            }
        }
        ActionListener listener = new MenuItemListener();
        item.addActionListener(listener);
        return item;
    }

    //seleção do menu que abre o buscador de imagem
    public JMenuItem colocarImagem() {
        JMenuItem item = new JMenuItem("Trocar Imagem");
        class MenuItemListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                if(escolheArquivo == null){
                    escolheArquivo = new JFileChooser();
                }
                int result = escolheArquivo. showOpenDialog(null);
                if (result == escolheArquivo.CANCEL_OPTION) {                    
                }else{
                    String caminho = escolheArquivo.getSelectedFile().getPath();                    
                    caminho = caminho.replace("\\", "\\\\");
                    System.out.println(caminho);
                    fundo = (new ImageIcon(caminho).getImage());
                }
                imagemPanel.repaint();
            }
        }
        ActionListener listener = new MenuItemListener();
        item.addActionListener(listener);
        return item;
    }
}
