/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desligarcomputador;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Junior
 */
public class Contador implements Runnable {

    private JFrame frame;
    private JLabel segundos;
    private JLabel informacoes;
    private int n;
    private boolean desligarComputador;
    private boolean interromperLoop;

    public Contador(JFrame janela, JLabel segundos, JLabel informacoes) {
        this.segundos = segundos;
        this.n = 30;
        this.frame = janela;
        this.informacoes = informacoes;
        this.desligarComputador = false;
        this.interromperLoop = false;
    }

    public void contador() throws InterruptedException {
        Thread.sleep(1000);
        this.segundos.setText(Integer.toString(n));
        this.n--;
    }
    
    public void chamarValidacao(String horasDefinidasUsuario){
        if (!Validacao.validar(horasDefinidasUsuario)) {
            try {
                this.informacoes.setText("Erro nos parametros.");
                Thread.sleep(3000);
                this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
                this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);
            }
        }
        
    }

    @Override
    public void run() {
        String comandoDesligarComputador = "shutdown -s -f";
        String leituraArquivo = Arquivo.read("arquivoHorasDesligarComputador.txt");
        String horasDefinidasUsuario[] = leituraArquivo.split("\n");
        chamarValidacao(horasDefinidasUsuario[0]);
        chamarValidacao(horasDefinidasUsuario[1]);
        try {
            for (int i = 0; i < 30; i++) {
                contador();
                if (this.interromperLoop) {
                    break;
                }
            }
            if (this.interromperLoop) {
                if (this.desligarComputador) {
                    try {
                        Runtime.getRuntime().exec(comandoDesligarComputador);
                    } catch (IOException ex) {
                        Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);

            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date hora = Calendar.getInstance().getTime();
                String horaFormatada = sdf.format(hora);
                if ((horaFormatada.compareTo(horasDefinidasUsuario[0]) >= 0) && (horaFormatada.compareTo(horasDefinidasUsuario[1]) <= 0)) {
                    try {
                        Runtime.getRuntime().exec(comandoDesligarComputador);
                    } catch (IOException ex) {
                        Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
                        this.informacoes.setText("Erro em desligar o computador.");
                        Thread.sleep(3000);
                    }
                }
                this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);
            }
        } catch (InterruptedException ex) {
            this.informacoes.setText("Erro de thread.");
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        }
    }

    public JLabel getLabel() {
        return segundos;
    }

    public void setLabel(JLabel label) {
        this.segundos = label;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public boolean isDesligarComputador() {
        return desligarComputador;
    }

    public void setDesligarComputador(boolean desligarComputador) {
        this.desligarComputador = desligarComputador;
    }

    public boolean isInterromperLoop() {
        return interromperLoop;
    }

    public void setInterromperLoop(boolean interromperLoop) {
        this.interromperLoop = interromperLoop;
    }

}
