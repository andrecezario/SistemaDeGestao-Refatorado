package sistema_gestao.negocio;
import java.util.ArrayList;

public class Projeto {
	private String titulo;
	private String status;
	private String data_inicio;
	private String data_fim;
	private String agencia_finaciadora;
	private double valor_financiado;
	private String objetivo;
	private String descricao;
	private ArrayList<Usuario> participantes = new ArrayList<>();
	
	public Projeto() {
		this.status = "Em elaboração";
	}
	
	public Projeto(String titulo, String data_inicio, String data_fim, String agencia_finaciadora,
			double valor_financiado, String objetivo, String descricao) {
		super();
		this.titulo = titulo;
		this.status = "Em elaboração";
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.agencia_finaciadora = agencia_finaciadora;
		this.valor_financiado = valor_financiado;
		this.objetivo = objetivo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
return "Título: " + titulo + "		"+"Status: " + status + "\nData de início: " + data_inicio + "		"+ "Data de fim: "
				+ data_fim + "\nAgência finaciadora: " + agencia_finaciadora + "		"+ "Valor financiado: R$" + valor_financiado
				+ "\nObjetivo: " + objetivo + "		"+"Descrição: " + descricao +"\n";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getData_fim() {
		return data_fim;
	}
	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}
	public String getAgencia_finaciadora() {
		return agencia_finaciadora;
	}
	public void setAgencia_finaciadora(String agencia_finaciadora) {
		this.agencia_finaciadora = agencia_finaciadora;
	}
	public double getValor_financiado() {
		return valor_financiado;
	}
	public void setValor_financiado(double valor_financiado) {
		this.valor_financiado = valor_financiado;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ArrayList<Usuario> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(ArrayList<Usuario> participantes) {
		this.participantes = participantes;
	}
}
