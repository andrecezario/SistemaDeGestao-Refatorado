package sistema_gestao.negocio;

public class Orientacao {
	private String titulo;
	private Professor orientador;
	private Usuario orientando;
	private int ano;
	
	public Orientacao(String titulo, Professor orientador, Usuario orientando, int ano) {
		super();
		this.titulo = titulo;
		this.orientador = orientador;
		this.orientando = orientando;
		this.ano = ano;
	}
	
	@Override
	public String toString() {
		return "Título: " + titulo + "\nOrientador: " + orientador.getNome() + "		" +"Orientando: "+orientando.getNome() + "\nAno: "
				+ ano +"\n";
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Professor getOrientador() {
		return orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

	public Usuario getOrientando() {
		return orientando;
	}

	public void setOrientando(Usuario orientando) {
		this.orientando = orientando;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}	

}
