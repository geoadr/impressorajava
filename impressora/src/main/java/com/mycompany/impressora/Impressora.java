/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.impressora;
/**
 *
 * @author GeoDantas
 */
import java.util.*;

class Doc {
    private int id;
    private String tipo;
    private int pag;

    public Doc(int id, String tipo, int pag) {
        this.id = id;
        this.tipo = tipo;
        this.pag = pag;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPag() {
        return pag;
    }

    @Override
    public String toString() {
        return "[id: " + id + ", tipo: " + tipo + ", páginas: " + pag + "]";
    }
}

class FilaImpressao {
    private Deque<Doc> fila = new ArrayDeque<>();
    private Random random = new Random();
    private Set<Integer> idsGerados = new HashSet<>();
    private final String[] TIPOS = {"PDF", "TXT", "JPG", "PNG", "DOCX", "XLSX"};

    private int gerarIdUnico() {
        int id;
        do {
            id = random.nextInt(1000) + 1;
        } while (idsGerados.contains(id));
        idsGerados.add(id);
        return id;
    }

    private Doc gerarDocumentoAleatorio() {
        int id = gerarIdUnico();
        String tipo = TIPOS[random.nextInt(TIPOS.length)];
        int pag = random.nextInt(100) + 1;
        return new Doc(id, tipo, pag);
    }

    public void adicionar_documento_normal() {
        Doc d = gerarDocumentoAleatorio();
        fila.addLast(d);
        System.out.println("documento adicionado no final: " + d);
    }

    public void adicionar_documento_urgente() {
        Doc d = gerarDocumentoAleatorio();
        fila.addFirst(d);
        System.out.println("documento urgente adicionado no início: " + d);
    }

    public Doc processar_proximo() {
        if (fila.isEmpty()) {
            System.out.println("fila vazia");
            return null;
        }
        Doc d = fila.removeFirst();
        System.out.println("processando início: " + d);
        return d;
    }

    public Doc processar_ultimo() {
        if (fila.isEmpty()) {
            System.out.println("fila vazia");
            return null;
        }
        Doc d = fila.removeLast();
        System.out.println("processando final: " + d);
        return d;
    }

    public void visualizar_fila() {
        if (fila.isEmpty()) {
            System.out.println("fila vazia");
            return;
        }
        System.out.println("\nfila de Impressão");
        for (Doc d : fila) {
            System.out.println(d);
        }
        System.out.println(".........................\n");
    }

    public boolean fila_vazia() {
        return fila.isEmpty();
    }

    public int total_documentos() {
        return fila.size();
    }

    public int total_pag() {
        int total = 0;
        for (Doc d : fila) {
            total += d.getPag();
        }
        return total;
    }
}

public class Impressora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FilaImpressao fila = new FilaImpressao();
        int opcao;

        do {
            System.out.println("\n menu");
            System.out.println("1 documento normal");
            System.out.println("2 documento urgente");
            System.out.println("3 próximo documento");
            System.out.println("4 ultimo documento");
            System.out.println("5 visualizar fila");
            System.out.println("6 total de documentos");
            System.out.println("7 total de páginas");
            System.out.println("0 encerrar");
            System.out.print("escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    fila.adicionar_documento_normal();
                    break;
                case 2:
                    fila.adicionar_documento_urgente();
                    break;
                case 3:
                    fila.processar_proximo();
                    break;
                case 4:
                    fila.processar_ultimo();
                    break;
                case 5:
                    fila.visualizar_fila();
                    break;
                case 6:
                    System.out.println("total de documentos: " + fila.total_documentos());
                    break;
                case 7:
                    System.out.println("total de páginas: " + fila.total_pag());
                    break;
                case 0:
                    System.out.println("finalizando");
                    break;
                default:
                    System.out.println("opção invalida");
            }

        } while (opcao != 0);

        sc.close();
    }
}
