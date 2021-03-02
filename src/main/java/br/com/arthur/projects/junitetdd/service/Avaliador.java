package br.com.arthur.projects.junitetdd.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.arthur.projects.junitetdd.model.Lance;
import br.com.arthur.projects.junitetdd.model.Leilao;

public class Avaliador
{
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	private double media = 0;
	
	public void avalia(Leilao leilao)
	{
		double total = 0;
		for (Lance lance : leilao.getLances())
		{
			if (lance.getValor() > maiorDeTodos)
			{
				maiorDeTodos = lance.getValor();
			}
			if (lance.getValor() < menorDeTodos)
			{
				menorDeTodos = lance.getValor();
			}
			total += lance.getValor();			
		}
		
		if (total == 0)
		{
            media = 0;
        }
		else
		{
			media = total / leilao.getLances().size();
		}
		
		maiores = new ArrayList<>(leilao.getLances());
		Collections.sort(maiores, Collections.reverseOrder());
	}
	
	public double getMaiorDeTodos()
	{
		return this.maiorDeTodos;
	}

	public double getMenorDeTodos()
	{
		return this.menorDeTodos;
	}

	public double getMedia()
	{
		return this.media;
	}
	
	public List<Lance> getTresMaiores()
	{
		if (maiores.size() >= 3)
		{
			return maiores.subList(0, 3);
		}
		else
		{
			return maiores.subList(0, maiores.size());
		}		
	}
}
