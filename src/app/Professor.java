package app;

public class Professor extends PessoaFisica {

	String areaFormacao,
			matriculaFUB;

	public Professor(String nome, String cpf, String email, String matriculaFUB, String areaFormacao) {
		super(nome, cpf, email);
		this.areaFormacao = areaFormacao;
		this.matriculaFUB = matriculaFUB;
	}

	public final String getAreaFormacao() {
		return areaFormacao;
	}

	public final String getMatriculaFUB() {
		return matriculaFUB;
	}

	public String toString() {
		String resposta = super.toString();
		resposta += "MATRICULA: " + matriculaFUB + '\n';
		resposta += "AREA DE FORMAÇÃO: " + areaFormacao + '\n';
		return resposta;
	}
}
