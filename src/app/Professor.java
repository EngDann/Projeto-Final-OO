package app;

public class Professor extends PessoaFisica {

	private String areaFormacao, matriculaFUB;

	public Professor(String nome, String cpf, String email, String matriculaFUB, String areaFormacao) {
		super(nome, cpf, email);
		this.areaFormacao = areaFormacao;
		this.matriculaFUB = matriculaFUB;
	}

	public String getAreaFormacao() {
		return areaFormacao;
	}

	public String getMatriculaFUB() {
		return matriculaFUB;
	}

	@Override
	public String toString() {
		String resposta = super.toString();
		resposta += "MATRICULA: " + matriculaFUB + '\n';
		resposta += "AREA DE FORMAÇÃO: " + areaFormacao + '\n';
		return resposta;
	}
}
