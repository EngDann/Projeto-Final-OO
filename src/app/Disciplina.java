package app;

import java.util.List;

public class Disciplina {
    private String nome,
            codigo;

    public Disciplina(String nome, String codigo) {

    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String toString() {
        String resposta = "DISCIPLINA: " + nome + "\n" + "CODIGO DA DISCIPLINA: " + codigo + "\n";
        return resposta;
    }
}
