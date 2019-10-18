/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desligarcomputador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Junior
 */
public class Arquivo {
    public static String read(String nomeArq) {
        String conteudo = "";
        try {
            FileReader arq = new FileReader(nomeArq);
            BufferedReader buffer = new BufferedReader(arq);
            String linha = "";
            try {
                linha = buffer.readLine();
                while (linha != null) {
                    conteudo += linha + "\n";
                    linha = buffer.readLine();
                }
                arq.close();
            } catch (IOException e) {
                conteudo = "Erro: Não foi possivel ler o arquivo.";
            }
        } catch (FileNotFoundException e) {
                conteudo = "Erro: Arquivo não encontrado.";
        }
        return conteudo;
    }
}
