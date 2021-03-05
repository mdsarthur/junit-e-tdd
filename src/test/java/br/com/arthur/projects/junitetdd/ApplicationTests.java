package br.com.arthur.projects.junitetdd;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.arthur.projects.junitetdd.model.Lance;
import br.com.arthur.projects.junitetdd.model.Leilao;
import br.com.arthur.projects.junitetdd.model.Usuario;
import br.com.arthur.projects.junitetdd.service.Avaliador;

public class ApplicationTests
{
	@Test
	public void deveEntenderLancesEmOrdemCrescente()
	{
		// Cenario
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 5");
		leilao.propoe(new Lance(jose, 100.0));
		leilao.propoe(new Lance(joao, 500.0));
		leilao.propoe(new Lance(maria, 600.0));

		// Acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// Validacao
		Double menorEsperado = 100.0;
		Double maiorEsperado = 600.0;
		Double mediaEsperada = 400.0;

		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		assertEquals(mediaEsperada, leiloeiro.getMedia(), 0.00001);
	}
	
	@Test
    public void deveEntenderLancesEmOrdemDecrescente()
	{
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Playstation Portable");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorDeTodos(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorDeTodos(), 0.0001);
    }
	
    @Test
    public void deveEntenderLancesEmOrdemAleatoria()
    {
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Xbox One X");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorDeTodos(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorDeTodos(), 0.0001);
    }

	@SuppressWarnings("unused")
	@Test
	public void testaMediaDeZeroLance()
	{
		// Cenario
		Usuario paulo = new Usuario("Paulo");
		Leilao leilao = new Leilao("Iphone 12");

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(0, leiloeiro.getMedia(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance()
	{
		Usuario joao = new Usuario("Joao");
		Leilao leilao = new Leilao("Playstation 4");
		leilao.propoe(new Lance(joao, 1000.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		Double menorEsperado = 1000.0;
		Double maiorEsperado = 1000.0;
		Double mediaEsperada = 1000.0;

		assertEquals(maiorEsperado, leiloeiro.getMaiorDeTodos(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(), 0.00001);
		assertEquals(mediaEsperada, leiloeiro.getMedia(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances()
	{
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Xbox 360");

		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}
}
