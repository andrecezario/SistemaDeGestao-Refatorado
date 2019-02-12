package sistema_gestao.negocio;

public abstract class Usuario {
	private int id;
	private String nome;
	private String email;
	private String tipo;	
	
	@Override
	public String toString() {
	return "Id: " + id + "		"+"Nome: " + nome + "\nE-mail: " + email + "		"+"Tipo:" + tipo + "\n";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
