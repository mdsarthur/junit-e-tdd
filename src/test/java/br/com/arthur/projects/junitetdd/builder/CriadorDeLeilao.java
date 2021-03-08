package br.com.arthur.projects.junitetdd.builder;

import br.com.arthur.projects.junitetdd.model.Lance;
import br.com.arthur.projects.junitetdd.model.Leilao;
import br.com.arthur.projects.junitetdd.model.Usuario;

public class CriadorDeLeilao
{
	private Leilao leilao;

	public CriadorDeLeilao para(String descricao)
	{
		this.leilao = new Leilao(descricao);
		return this;
	}

	public CriadorDeLeilao lance(Usuario usuario, double valor)
	{
		leilao.propoe(new Lance(usuario, valor));
		return this;
	}

	public Leilao constroi()
	{
		return leilao;
	}
}