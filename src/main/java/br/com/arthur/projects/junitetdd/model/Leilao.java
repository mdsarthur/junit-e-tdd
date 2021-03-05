package br.com.arthur.projects.junitetdd.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao
{
	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao)
	{
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance)
	{
		if (lances.isEmpty() || podeDarLance(lance.getUsuario()))
		{
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario)
	{
		// Sem utilizar o 'not' (!)
		if (ultimoLanceDado().getUsuario().equals(usuario))
		{
			return false;
		}
		else if ((quantidadeDeLances(usuario) < 5))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private Lance ultimoLanceDado()
	{
		return lances.get(lances.size() - 1);
	}
	
	private int quantidadeDeLances(Usuario usuario)
	{
		int total = 0;
		for (Lance esteLance : lances)
		{
			if (esteLance.getUsuario().equals(usuario))
				total++;
		}
		return total;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public List<Lance> getLances()
	{
		return Collections.unmodifiableList(lances);
	}
}
