package app;

public class Aluno extends PessoaFisica {

	private String matricula, curso;

	public Aluno(String nome, String cpf, String email, String matricula, String curso) {
		super(nome, cpf, email);
		this.matricula = matricula;
		this.curso = curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getCurso() {
		return curso;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Destruindo objeto: " + this);
	}

	@Override
	public String toString() {
		String resposta = super.toString();
		resposta += "MATRICULA: " + matricula + '\n';
		resposta += "CURSO: " + curso + '\n';
		return resposta;
	}
}
