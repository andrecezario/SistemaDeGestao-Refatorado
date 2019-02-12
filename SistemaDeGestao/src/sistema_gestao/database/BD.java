package sistema_gestao.database;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sistema_gestao.negocio.AlunoGraduacao;
import sistema_gestao.negocio.Orientacao;
import sistema_gestao.negocio.Professor;
import sistema_gestao.negocio.Projeto;
import sistema_gestao.negocio.Publicacao;
import sistema_gestao.negocio.Usuario;

public class BD {
	public ArrayList<Projeto> projetos = new ArrayList<>();
	public ArrayList<Usuario> participantes = new ArrayList<>();
	public ArrayList<Publicacao> publicacoes = new ArrayList<>();
	public ArrayList<Orientacao> orientacoes = new ArrayList<>();

	public Projeto buscarProjeto(String titulo) {
		Projeto proj = null;
		for (Projeto p : projetos) {
			if (p.getTitulo().equals(titulo)) {
				return p;
			}
		}
		return proj;
	}
	
	public void cadastrarProjeto(Projeto p) {
		projetos.add(p);
		System.out.println("Projeto adicionado!");
	}
	
	public void cadastrarUsuario(Usuario u) {
		participantes.add(u);
		System.out.println("Colaborador adicionado!");
	}

	public Usuario buscarUsuario(String nome) {
		Usuario user = null;
		for (Usuario u : participantes) {
			if (u.getNome().equals(nome)) {
				return u;
			}
		}
		return user;
	}

	public boolean professorAlocado(Projeto p) {
		boolean resultado = false;
		for (Usuario user : p.getParticipantes()) {
			if (user.getTipo().equals("Professor")) {
				resultado = true;
			}
		}
		return resultado;
	}

	public boolean alunoIndisponivel(Usuario u) {
		boolean resultado = false;
		AlunoGraduacao aGrad = (AlunoGraduacao) u;
		int cont = 0;

		for (Projeto p : projetos) {
			if (p.getStatus().equals("Em andamento")) {

				for (Usuario user : p.getParticipantes()) {
					if (u.getId() == user.getId()) {
						cont++;
					}
				}
			}
		}

		if (cont == 2) {
			resultado = true;
		}
		return resultado;
	}

	public boolean checkInfoProj(Projeto p) {
		boolean resultado = false;
		if (p.getTitulo() != null && p.getAgencia_finaciadora() != null && p.getValor_financiado() != 0
				&& p.getData_fim() != null && p.getData_inicio() != null && p.getDescricao() != null
				&& p.getParticipantes() != null && p.getObjetivo() != null) {
			resultado = true;
		}

		return resultado;
	}

	public boolean checkPubProj(Projeto p) {
		boolean resultado = false;

		for (Publicacao pub : publicacoes) {
			if (pub.getProjetoPesquisa() == p) {
				resultado = true;
			}
		}
		return resultado;
	}

	public void addPublicacao(Publicacao p) {
		publicacoes.add(p);
		System.out.println("Publicação adicionada!");
	}
	
	public void addOrientacao(Orientacao o) {
		orientacoes.add(o);
		System.out.println("Orientação adicionada!");
	}

	public Publicacao buscarPublicacao(String titulo) {
		Publicacao pub = null;
		for (Publicacao p : publicacoes) {
			if (p.getTitulo().equals(titulo)) {
				return p;
			}
		}
		return pub;
	}

	public boolean colabProj(Usuario colab, Projeto proj) {
		boolean resultado = false;

		for (Usuario u : proj.getParticipantes()) {
			if (u.getId() == colab.getId()) {
				resultado = true;
			}
		}

		return resultado;
	}

	public boolean colabPub(Usuario colab, Publicacao pub) {
		boolean resultado = false;

		for (Usuario u : pub.getAutores()) {
			if (u.getId() == colab.getId()) {
				resultado = true;
			}
		}
		return resultado;
	}

	public boolean colabOrientacao(Professor prof, Orientacao orient) {
		boolean resultado = false;

		if (prof.getId() == orient.getOrientador().getId()) {
			resultado = true;
		}
		return resultado;

	}

	public void sortColabProducao(Usuario colab) {
		ArrayList<Publicacao> pubsColab = new ArrayList<>();
		// Publicações
		for (Publicacao pub : publicacoes) {
			if (colabPub(colab, pub)) {
				pubsColab.add(pub);
			}
		}

		// Ordenar por ano
		int n = pubsColab.size();

		for (int i = 0; i < n - 1; i++) {

			for (int j = 0; j < n - i - 1; j++) {
				Publicacao pubAtual = pubsColab.get(j);
				Publicacao pubProximo = pubsColab.get(j + 1);

				int anoAtual = pubAtual.getAnoPublicacao();
				int anoProx = pubProximo.getAnoPublicacao();

				if (anoAtual < anoProx) {
					pubsColab.set(j, pubProximo);
					pubsColab.set(j + 1, pubAtual);
				}
			}
		}

		System.out.println("LISTA DE PRODUÇÕES ACADÊMICAS");
		int cont = 1;
		System.out.println("PUBLICAÇÕES");
		for (Publicacao p : pubsColab) {
			System.out.println("Publicação " + cont);
			cont++;
			System.out.println(p);
		}

		// Orientações
		if (colab.getTipo().equals("Professor")) {
			System.out.println("ORIENTAÇÕES");
			ArrayList<Orientacao> orientsColab = new ArrayList<>();

			for (Orientacao ori : orientacoes) {
				if (colabOrientacao((Professor) colab, ori)) {
					orientsColab.add(ori);
				}
			}

			// Ordenar por ano
			n = orientsColab.size();

			for (int i = 0; i < n - 1; i++) {

				for (int j = 0; j < n - i - 1; j++) {
					Orientacao oriAtual = orientsColab.get(j);
					Orientacao oriProximo = orientsColab.get(j + 1);

					int anoAtual = oriAtual.getAno();
					int anoProx = oriProximo.getAno();

					if (anoAtual < anoProx) {
						orientsColab.set(j, oriProximo);
						orientsColab.set(j + 1, oriAtual);
					}
				}
			}

			cont = 1;
			for (Orientacao o : orientsColab) {
				System.out.println("Orientação " + cont);
				System.out.println(o);
				cont++;
			}

		}
	}

	public void sortColabProj(Usuario colab) throws ParseException {
		ArrayList<Projeto> projetosColab = new ArrayList<>();

		for (Projeto p : projetos) {
			if (colabProj(colab, p)) {
				projetosColab.add(p);
			}
		}

		// Ordenar por data de termino
		int n = projetosColab.size();

		for (int i = 0; i < n - 1; i++) {

			for (int j = 0; j < n - i - 1; j++) {
				Projeto projAtual = projetosColab.get(j);
				Projeto projProximo = projetosColab.get(j + 1);

				String txtDataAtual = projAtual.getData_fim();
				String txtDataProx = projProximo.getData_fim();
				String formato = "dd/MM/yyyy";

				Date dataAtual = new SimpleDateFormat(formato).parse(txtDataAtual);
				Date dateProx = new SimpleDateFormat(formato).parse(txtDataProx);

				if (dataAtual.compareTo(dateProx) == -1) {
					projetosColab.set(j, projProximo);
					projetosColab.set(j + 1, projAtual);
				}
			}
		}
		System.out.println("LISTA DE PROJETOS:");
		int cont = 1;
		for (Projeto p : projetosColab) {
			System.out.println("Projeto " + cont);
			System.out.println(p);
			cont++;
		}
	}
	
	
	public boolean projPub(Projeto proj, Publicacao pub) {
		boolean resultado = false;

		for (Publicacao p : publicacoes) {
			if(p.getProjetoPesquisa()!=null) {
				if (p.getProjetoPesquisa().equals(proj)) {
					resultado = true;
				}
			}
		}
		return resultado;
		
	}
	
	public void sortProjPub(Projeto proj) {
		ArrayList<Publicacao> pubsProj = new ArrayList<>();
		
		for (Publicacao pub : publicacoes) {
			if ( projPub(proj, pub)) {
				pubsProj.add(pub);
			}
		}

		// Ordenar por ano
		int n = pubsProj.size();

		for (int i = 0; i < n - 1; i++) {

			for (int j = 0; j < n - i - 1; j++) {
				Publicacao pubAtual = pubsProj.get(j);
				Publicacao pubProximo = pubsProj.get(j + 1);

				int anoAtual = pubAtual.getAnoPublicacao();
				int anoProx = pubProximo.getAnoPublicacao();

				if (anoAtual < anoProx) {
					pubsProj.set(j, pubProximo);
					pubsProj.set(j + 1, pubAtual);
				}
			}
		}

		System.out.println("LISTA DE PUBLICAÇÕES:");
		int cont = 1;
		for (Publicacao p : pubsProj) {
			System.out.println("Publicação " + cont);
			System.out.println(p);
			cont++;
		}
	}
	
	public void relatorioProducao() {
		int contElab = 0;
		int contAnd = 0;
		int contConc = 0;
		
		for(Projeto p : projetos) {
			if(p.getStatus().equals("Em elaboração")) {
				contElab++;
			}
			else if(p.getStatus().equals("Em andamento")) {
				contAnd++;
			}
			else if(p.getStatus().equals("Concluído")) {
				contConc++;
			}
		}
		System.out.println("Nº de colaboradores: "+participantes.size());
		System.out.println("Nº de projetos em elaboração: "+contElab);
		System.out.println("Nº de projetos em andamento: "+contAnd);
		System.out.println("Nº de projetos concluídos: "+contConc);
		System.out.println("Nº de publicações: "+publicacoes.size());
		System.out.println("Nº de orientações: "+orientacoes.size());
		
	}

}
