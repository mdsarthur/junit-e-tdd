package br.com.arthur.projects.junitetdd.model;

public class Lance implements Comparable<Lance>
{
	private Usuario usuario;
	private double valor;

	public Lance(Usuario usuario, double valor)
	{
		this.usuario = usuario;
		this.valor = valor;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public double getValor()
	{
		return valor;
	}

	@Override
	public int compareTo(Lance lance)
	{		
		return Double.compare(this.valor, lance.getValor());
	}
}
