package sistema_gestao.ui;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import sistema_gestao.database.BD;
import sistema_gestao.negocio.AlunoDoutorado;
import sistema_gestao.negocio.AlunoGraduacao;
import sistema_gestao.negocio.AlunoMestrado;
import sistema_gestao.negocio.Orientacao;
import sistema_gestao.negocio.Pesquisador;
import sistema_gestao.negocio.Professor;
import sistema_gestao.negocio.Projeto;
import sistema_gestao.negocio.Publicacao;
import sistema_gestao.negocio.Usuario;

public class Main {
	static BD banco = new BD();

	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		int id = 1;
		
		while(true) {
			try {
				System.out.print("------------------------------\n"
						+"Sistema de produtividade acadêmica\n"
						+ "------------------------------\n"
						+ "1 - Alocar participantes\n"
						+ "2 - Alteração de status\n"
						+ "3 - Cadastrar produção acadêmica\n"
						+ "4 - Associar publicações\n"
						+ "5 - Consultar colaborador\n"
						+ "6 - Consultar projeto\n"
						+ "7 - Relatório de produções\n"
						+ "8 - Cadastrar colaborador\n"
						+ "9 - Cadastrar projeto\n");
				int opcao = scanner.nextInt();
				
				switch(opcao) {
					case 1:
						System.out.print("------------------------------\n"
								+ "	"+"Alocar participantes\n"
								+ "------------------------------\n");
						System.out.println("Digite o título do projeto: ");
						scanner.nextLine();
						String titulo = scanner.nextLine();
						Projeto proj = banco.buscarProjeto(titulo);
						if(proj!=null) {
							if(proj.getStatus().equals("Em elaboração")) {
					
								System.out.println("Digite o nome do usuário: ");
								String nome = scanner.nextLine();
								Usuario user = banco.buscarUsuario(nome);
									
								if(banco.buscarUsuario(nome)!=null) {
									if(user.getTipo().equals("Aluno graduação")) {
										if(!banco.alunoIndisponivel(user)) {
											System.out.println("Aluno já participa de dois projetos em andamento.");
										}
										else {
											proj.getParticipantes().add(user);
											System.out.println("Participante alocado!");
										}
									}
									else {
										proj.getParticipantes().add(user);
										System.out.println("Participante alocado!");
									}
										
								}
								else {
									System.out.println("Usuário não encontrado!");
								}
							}
							
							else {
								System.out.println("Não é possível alocar participantes, projeto não está em 'em elaboração'");
							}
						}
						
						else {
							System.out.println("Projeto não encontrado!");
						}
						
						break;
						
					case 2:
						System.out.print("------------------------------\n"
								+ "	"+"Alterar status\n"
								+ "------------------------------\n");
						System.out.println("Digite o título do projeto: ");
						scanner.nextLine();
						String title = scanner.nextLine();
						Projeto projeto = banco.buscarProjeto(title);
						
						if(projeto!=null) {
							String statusAtual = projeto.getStatus();
							if(banco.professorAlocado(banco.buscarProjeto(title))) {
								if(statusAtual.equals("Em elaboração")) {
									System.out.println("Alterar de Em elaboração -> Em andamento\n"
											+ "1-SIM\n2-NÃO");
									int op = scanner.nextInt();
									
									if(op==1) {
										if(banco.checkInfoProj(projeto)) {
											projeto.setStatus("Em andamento");
											System.out.println("Alteração de status concluída!");
										}
										else {
											System.out.println("Todas as informações básicas devem estar preenchidas.");
										}
									}
								}
								else if(statusAtual.equals("Em andamento")) {
									System.out.println("Alterar de Em andamento -> Concluído\n"
											+ "1-SIM\n2-NÃO");
									int op = scanner.nextInt();
									
									if(op==1) {
										if(banco.checkPubProj(projeto)) {
											projeto.setStatus("Concluído");
											System.out.println("Alteração de status concluída!");
										}
										else {
											System.out.println("Projeto deve ter alguma publicação associada.");
										}
									}
								}
							}
							else {
								System.out.println("Projeto não possui professor alocado, não é possível realizar alterações de status.");
							}
						}
						
						else {
							System.out.println("Projeto não encontrado!");
						}
						break;
						
					case 3:
						System.out.print("------------------------------\n"
								+ ""+"Inserir produção acadêmica\n"
								+ "------------------------------\n");
						System.out.println("Digite o tipo de produção\n"
								+ "1 - Publicação\n"
								+ "2 - Orientação");
						int op = scanner.nextInt();
						
						if(op==1) {
							System.out.println("Digite o título da publicação: ");
							scanner.nextLine();
							String tit = scanner.nextLine();
							
							System.out.println("Digite o nome da conferência: ");
							String nomeConferencia = scanner.nextLine();
							
							System.out.println("Digite o ano da publicação: ");
							int anoPublicacao = scanner.nextInt();
							
							System.out.println("Digite o nº de autores: ");
							int numAutores = scanner.nextInt();
							
							ArrayList<Usuario> autores = new ArrayList<>();
							for(int i=0;i<numAutores;i++) {
								System.out.println("Digite o nome do autor:");
								String nomeAutor = scanner.next();
								Usuario autor = banco.buscarUsuario(nomeAutor);
								if(autor!=null) {
									autores.add(autor);
								}
								else {
									System.out.println("Autor não encontrado!");
								}					
							}
							Publicacao pub =  new Publicacao(tit, nomeConferencia, anoPublicacao,autores);
							
							banco.addPublicacao(pub);
							
						}
						else if(op==2) {
							System.out.println("Digite o título da orientação: ");
							scanner.nextLine();
							String tit = scanner.nextLine();
							
							System.out.println("Digite o nome do orientador: ");
							//scanner.nextLine();
							String nomeOrientador = scanner.nextLine();
							if(banco.buscarUsuario(nomeOrientador)!=null) {
								if(banco.buscarUsuario(nomeOrientador).getTipo().equals("Professor")) {
							
									System.out.println("Digite o nome do orientando: ");
									//scanner.nextLine();
									String nomeOrientando = scanner.nextLine();
									
									System.out.println("Digite o ano da publicação: ");
									int ano = scanner.nextInt();
									
									Orientacao ori = new Orientacao(tit,(Professor)banco.buscarUsuario(nomeOrientador),banco.buscarUsuario(nomeOrientando),ano);
									banco.addOrientacao(ori);
								}
								
								else {
									System.out.println("O orientador deve ser um professor!");
								}
							} else {
								System.out.println("Usuário não encontrado!");
							}
							
						
						}
					
						break;
						
					case 4:
						System.out.print("------------------------------\n"
								+ ""+"Associar projeto a publicação\n"
								+ "------------------------------\n");
						System.out.println("Digite o título da publicação: ");
						scanner.nextLine();
						String titPub = scanner.nextLine();
						Publicacao publi = banco.buscarPublicacao(titPub);
						
						if(publi!=null) {
							System.out.println("Digite o título do projeto: ");
							//scanner.nextLine();
							String titProj = scanner.nextLine();
							Projeto projetoPesquisa = banco.buscarProjeto(titProj);
							
							if(projetoPesquisa!=null) {
								if(projetoPesquisa.getStatus().equals("Em andamento")) {
									publi.setProjetoPesquisa(projetoPesquisa);
									System.out.println("Projeto associado à publicação!");
								}
								else {
									System.out.println("Não é possível associar este projeto! Projeto não está em andamento.");
								}
							}
							else {
								System.out.println("Projeto não encontrado!");
							}
						}
						else {
							System.out.println("Publicação não encontrada!");
						}			
						break;
						
					case 5:
						System.out.print("------------------------------\n"
								+"Consultar colaborador\n"
								+ "------------------------------\n");
						System.out.println("Digite o nome do colaborador: ");
						scanner.nextLine();
						String nomeColab = scanner.nextLine();
						
						Usuario colab = banco.buscarUsuario(nomeColab);
						
						if(colab!=null) {
							System.out.print("------------------------------\n"
									+ ""+"Dados do colaborador\n"
									+ "------------------------------\n");
							System.out.println("Nome: "+colab.getNome()+"\nE-mail: "+colab.getEmail()+"\n");
							banco.sortColabProj(colab);
							banco.sortColabProducao(colab);
						}
						
						else {
							System.out.println("Colaborador não encontrado!");
						}
						
						break;
						
					case 6:	
						System.out.print("------------------------------\n"
								+ "	"+"Consultar projeto\n"
								+ "------------------------------\n");
						System.out.println("Digite o título do projeto: ");
						scanner.nextLine();
						String tituloProj = scanner.nextLine();
						Projeto proje = banco.buscarProjeto(tituloProj);
						
						if(proje!=null) {
							System.out.println(proje);
							System.out.println("COLABORADORES");
							int cont = 1;
							for(Usuario u : proje.getParticipantes()) {
								System.out.println("Participante "+cont);
								System.out.println(u);
								cont++;
							}
							
							banco.sortProjPub(proje);
						}
						
						else {
							System.out.println("Projeto não encontrado!");
						}
					
						break;
						
					case 7:
						System.out.print("------------------------------\n"
								+ ""+"Relatório de produção acadêmica\n"
								+ "------------------------------\n");
						banco.relatorioProducao();
						break;
						
					case 8:
						System.out.print("------------------------------\n"
								+"Cadastrar colaborador\n"
								+ "------------------------------\n");
						System.out.println("Digite o nome: ");
						scanner.nextLine();
						String nome = scanner.nextLine();
						
						System.out.println("Digite o e-mail: ");
						String email = scanner.next();
					
						System.out.println("Digite o tipo de colaborador\n"
								+ "1 - Professor\n"
								+ "2 - Aluno de graduação\n"
								+ "3 - Aluno de mestrado\n"
								+ "4 - Aluno de doutorado\n"
								+ "5 - Pesquisador");
						int type = scanner.nextInt();
						
						switch(type) {
							case 1:
								System.out.println("Digite o siape: ");
								int siape = scanner.nextInt();
								
								Professor prof = new Professor();
								prof.setNome(nome);
								prof.setId(id);
								prof.setSiape(siape);
								prof.setTipo("Professor");
								prof.setEmail(email);
								banco.cadastrarUsuario(prof);
								
								id++;
								break;
								
							case 2:
								System.out.println("Digite a matrícula: ");
								int matricula = scanner.nextInt();
								
								AlunoGraduacao aGrad = new AlunoGraduacao();
								aGrad.setNome(nome);
								aGrad.setId(id);
								aGrad.setMatricula(matricula);
								aGrad.setTipo("Aluno de graduação");
								aGrad.setEmail(email);
								banco.cadastrarUsuario(aGrad);
								
								id++;
								break;
								
							case 3:
								System.out.println("Digite a matrícula: ");
								matricula = scanner.nextInt();
								
								AlunoMestrado aMest = new AlunoMestrado();
								aMest.setNome(nome);
								aMest.setId(id);
								aMest.setMatricula(matricula);
								aMest.setTipo("Aluno de mestrado");
								aMest.setEmail(email);
								banco.cadastrarUsuario(aMest);
								
								id++;
								break;
								
							case 4:
								System.out.println("Digite a matrícula: ");
								matricula = scanner.nextInt();
								
								AlunoDoutorado aDot = new AlunoDoutorado();
								aDot.setNome(nome);
								aDot.setId(id);
								aDot.setMatricula(matricula);
								aDot.setTipo("Aluno de doutorado");
								aDot.setEmail(email);
								banco.cadastrarUsuario(aDot);
								
								id++;
								break;
								
							case 5:
								System.out.println("Digite o código: ");
								int cod = scanner.nextInt();
								
								Pesquisador pesq = new Pesquisador();
								pesq.setNome(nome);
								pesq.setId(id);
								pesq.setCodigo(cod);
								pesq.setTipo("Pesquisador");
								pesq.setEmail(email);
								banco.cadastrarUsuario(pesq);
								
								id++;
								break;
								
							
						}
						
						break;
					case 9:
						System.out.print("------------------------------\n"
								+"Cadastrar projeto\n"
								+ "------------------------------\n");
						System.out.println("Digite o título: ");
						scanner.nextLine();
						String titu = scanner.nextLine();
						String status = "Em elaboração";
						System.out.println("Digite a data de início (dd/mm/aaaa): ");
						String data_inicio = scanner.nextLine();
						System.out.println("Digite a data de fim (dd/mm/aaaa): ");
						String data_fim = scanner.nextLine();
						System.out.println("Digite a agência financiadora: ");
						String agencia_finaciadora = scanner.nextLine();
						System.out.println("Digite o valor financiado: ");
						String valor = 	scanner.next
								();
						double valor_financiado = Double.parseDouble(valor);
						System.out.println("Digite o objetivo: ");
						scanner.nextLine();
						String objetivo = scanner.nextLine();
						
						System.out.println("Digite a descrição: ");
						String descricao = scanner.nextLine();
	
						Projeto pro = new Projeto(titu,data_inicio,data_fim,agencia_finaciadora,
								valor_financiado, objetivo, descricao);
						banco.cadastrarProjeto(pro);
						break;
						
				}
				
			} catch (Exception e) {
				System.out.println("Ocorreu algum um erro!");
			}
		}
	}

}
