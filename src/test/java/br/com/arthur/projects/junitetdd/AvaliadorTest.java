package br.com.arthur.projects.junitetdd;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.arthur.projects.junitetdd.builder.CriadorDeLeilao;
import br.com.arthur.projects.junitetdd.model.Lance;
import br.com.arthur.projects.junitetdd.model.Leilao;
import br.com.arthur.projects.junitetdd.model.Usuario;
import br.com.arthur.projects.junitetdd.service.Avaliador;

public class AvaliadorTest
{
    private Avaliador leiloeiro;
    private Usuario maria;
    private Usuario jose;
    private Usuario joao;

    @Before
    public void setUp()
    {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente()
    {
    	// parte 1: cenario
        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation 3 Novo")
            .lance(joao, 250)
            .lance(jose, 300)
            .lance(maria, 400)
            .constroi();

        // parte 2: acao
        leiloeiro.avalia(leilao);

        // parte 3: validacao
        assertThat(leiloeiro.getMenorDeTodos(), equalTo(250.0));
        assertThat(leiloeiro.getMaiorDeTodos(), equalTo(400.0));
        //assertEquals(400.0, leiloeiro.getMaiorDeTodos(), 0.00001);
        //assertEquals(250.0, leiloeiro.getMenorDeTodos(), 0.00001);
    }

	@Test
	public void deveEntenderLancesEmOrdemDecrescente()
	{
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation Portable");

		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 100.0));

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

		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(maria, 700.0));
		leilao.propoe(new Lance(joao, 630.0));
		leilao.propoe(new Lance(maria, 230.0));

		leiloeiro.avalia(leilao);
		
		assertEquals(700.0, leiloeiro.getMaiorDeTodos(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorDeTodos(), 0.0001);
	}

	@SuppressWarnings("unused")
	//@Test
	public void testaMediaDeZeroLance()
	{		
		Usuario paulo = new Usuario("Paulo");
		Leilao leilao = new Leilao("Iphone 12");

		leiloeiro.avalia(leilao);

		assertEquals(0, leiloeiro.getMedia(), 0.0001);
	}

    @Test
    public void deveEntenderLeilaoComApenasUmLance()
    {
        Leilao leilao = new CriadorDeLeilao()
        .para("Playstation 3 Novo")
        .lance(joao, 1000)
        .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(1000.0, leiloeiro.getMaiorDeTodos(), 0.00001);
        assertEquals(1000.0, leiloeiro.getMenorDeTodos(), 0.00001);
    }

	@Test
	public void deveEncontrarOsTresMaioresLances()
	{
		Leilao leilao2 = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.constroi();

		leiloeiro.avalia(leilao2);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado()
	{
	    try
	    {
	        Leilao leilao = new CriadorDeLeilao()
	            .para("Playstation 3 Novo")
	            .constroi();

	        leiloeiro.avalia(leilao);
	        // Se a excecao nao for lancada, falhar o teste
	        Assert.fail();
	    }
	    catch(RuntimeException e)
	    {
	        
	    }
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDadoNovo()
	{
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
	
}
