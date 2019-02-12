package sistema_gestao.negocio;
import java.util.ArrayList;

public class Publicacao {
	private String titulo;
	private String nomeConferencia;
	private int anoPublicacao;
	private Projeto projetoPesquisa;
	private ArrayList<Usuario> autores = new ArrayList<>();
	
	public Publicacao() {
		
	}
	
	public Publicacao(String titulo, String nomeConferencia, int anoPublicacao,ArrayList<Usuario> autores) {
		super();
		this.titulo = titulo;
		this.nomeConferencia = nomeConferencia;
		this.anoPublicacao = anoPublicacao;
		this.autores = autores;
	}
	
	
	public Publicacao(String titulo, String nomeConferencia, int anoPublicacao, Projeto projetoPesquisa,
			ArrayList<Usuario> autores) {
		super();
		this.titulo = titulo;
		this.nomeConferencia = nomeConferencia;
		this.anoPublicacao = anoPublicacao;
		this.projetoPesquisa = projetoPesquisa;
		this.autores = autores;
	}
	

	@Override
	public String toString() {
		try {
			return "Título: " + titulo + "\nNome da conferência: " + nomeConferencia + "		"+ "Ano de publicação: "
				+ anoPublicacao + "\nProjeto de Pesquisa: " + projetoPesquisa.getTitulo() +"\n";
		} catch(Exception e) {
			return "Título: " + titulo + "\nNome da conferência: " + nomeConferencia + "		"+ "Ano de publicação: "
					+ anoPublicacao + "\nProjeto de Pesquisa: " + "Não possui" +"\n";
		}
		
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNomeConferencia() {
		return nomeConferencia;
	}
	public void setNomeConferencia(String nomeConferencia) {
		this.nomeConferencia = nomeConferencia;
	}
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public Projeto getProjetoPesquisa() {
		return projetoPesquisa;
	}
	
	public void setProjetoPesquisa(Projeto projetoPesquisa) {
		if(projetoPesquisa.getStatus().equals("Em andamento")) {
			this.projetoPesquisa = projetoPesquisa;
		}
		else {
			this.projetoPesquisa = null;
		}
	}

	public ArrayList<Usuario> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Usuario> autores) {
		this.autores = autores;
	}
}
