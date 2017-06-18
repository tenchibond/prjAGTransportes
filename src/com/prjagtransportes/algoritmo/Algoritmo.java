package com.prjagtransportes.algoritmo;

import java.util.Arrays;
import java.util.Random;

public class Algoritmo {
	
	private static double taxaCrossover;
	private static double taxaMutacao;
	private static int variacaoNucleica;
	private static Individuo melhorIndividuoGlobal;
	
	public static Populacao novaGeracao(Populacao populacao, boolean elitismo) {
		Random r = new Random();
		//Nova populacao com o mesmo tamanho da antiga
		Populacao novaPopulacao = new Populacao(populacao.getTamPopulacao());
		
		//Se tiver elitismo, mantem o melhor individuo da populacao original
		if(elitismo) {
			novaPopulacao.setIndividuo(populacao.getIndividuo(0));
			
		}
		
		//Insere novos individuos na nova populacao, ate atingir o tamanho maximo
		while(novaPopulacao.getNumIndividuos() < novaPopulacao.getTamPopulacao()) {
			//Seleciona dois pais por torneio
			Individuo[] pais = selecaoTorneio(populacao);
			
			Individuo[] filhos = new Individuo[2];
			
			//Verifica a taxa de crossover, se sim, realiza o crossover, se nao, mantem
			//os pais selecionados para a proxima geracao
			if(r.nextDouble() <= taxaCrossover) {
				filhos = crossover(pais[1], pais[0]);
				
			} else {
				filhos[0] = new Individuo(pais[0].getGenes());
				filhos[1] = new Individuo(pais[1].getGenes());
				
			}
			
			//Adiciona filhos na nova geracao
			novaPopulacao.setIndividuo(filhos[0]);
			novaPopulacao.setIndividuo(filhos[1]);
			
		}
		
		//Ordena a populacao
		novaPopulacao.ordernaPopulacao();
		
		//Seleciona melhor individuo para a variavel melhorIndividuoGlobal
		if(populacao.getIndividuo(0).getAptidao() <= novaPopulacao.getIndividuo(0).getAptidao()) {
			melhorIndividuoGlobal = populacao.getIndividuo(0);
			
		} else {
			melhorIndividuoGlobal = novaPopulacao.getIndividuo(0);
			
		}
		
		return novaPopulacao;
		
	}
	
	//Precisa de implementacao
	public static Individuo[] crossover(Individuo individuo1, Individuo individuo2) {
		Random r = new Random();
		
		//Sorteia o ponto de corte
		int pontoCorte1 = r.nextInt((individuo1.getGenes().length/2) - 2) + 1;
		int pontoCorte2 = r.nextInt((individuo1.getGenes().length/2) - 2) + individuo1.getGenes().length/2;
		
		Individuo[] filhos = new Individuo[2];
		
		//Pega os genes do pai
		int[] genePai1 = individuo1.getGenes();
		int[] genePai2 = individuo2.getGenes();
		
		int[] geneFilho1;
		int[] geneFilho2;
		
		//Realiza o corte
		int[] geneFilho1_aux1 = Arrays.copyOfRange(genePai1, 0, pontoCorte1);
		int[] geneFilho1_aux2 = Arrays.copyOfRange(genePai2, pontoCorte1, pontoCorte2);
		int[] geneFilho1_aux3 = Arrays.copyOfRange(genePai1, pontoCorte2, genePai1.length);
		
		geneFilho1 = concat(geneFilho1_aux1, geneFilho1_aux2, geneFilho1_aux3);
		
		int[] geneFilho2_aux1 = Arrays.copyOfRange(genePai2, 0, pontoCorte1);
		int[] geneFilho2_aux2 = Arrays.copyOfRange(genePai1, pontoCorte1, pontoCorte2);
		int[] geneFilho2_aux3 = Arrays.copyOfRange(genePai2, pontoCorte2, genePai2.length);
		
		geneFilho2 = concat(geneFilho2_aux1, geneFilho2_aux2, geneFilho2_aux3);
		
		//Cria novo individuo com os genes dos pais
		filhos[0] = new Individuo(geneFilho1);
		filhos[1] = new Individuo(geneFilho2);
		
		return filhos;
		
	}
	
	private static int[] concat(int[] primeiro, int[] segundo, int[] terceiro) {
		int[] result = Arrays.copyOf(primeiro, primeiro.length + segundo.length + terceiro.length);
		System.arraycopy(segundo, 0, result, primeiro.length, segundo.length);
		System.arraycopy(terceiro, 0, result, primeiro.length+segundo.length, terceiro.length);
		return result;
		
	}
	
	public static Individuo[] selecaoTorneio(Populacao populacao) {
		Random r = new Random();
		Populacao populacaoIntermediaria = new Populacao(3);
		
		//Seleciona 3 individuos aleatoriamente na populacao
		populacaoIntermediaria.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamPopulacao())));
		populacaoIntermediaria.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamPopulacao())));
		populacaoIntermediaria.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamPopulacao())));
		
		//Ordena a populacao
		populacaoIntermediaria.ordernaPopulacao();
		
		Individuo[] pais = new Individuo[2];
		
		//Seleciona os dois melhores desta populacao
		pais[0] = populacaoIntermediaria.getIndividuo(0);
		pais[1] = populacaoIntermediaria.getIndividuo(1);
		
		return pais;
		
	}
	
	//Metodo implementado parcialmente, por enquanto ainda retorna um random entre 0 e 99
	public static int validaSolucaoDoIndividuo(Individuo individuo) {
		//Tentando fazer o trabalho com o exemplo:
		//http://www.iepg.unifei.edu.br/arnaldo/ensino/pos/mba/po/aulas/aula_08/transp.pdf
		//exemplo 01
		
		//Pegando os genes do individuo e atribuindo valores as variaveis
		int[] genesIndividuo = individuo.getGenes();
		int zMin = 0;
		int x11 = genesIndividuo[0];
		int x12 = genesIndividuo[1];
		int x13 = genesIndividuo[2];
		int x21 = genesIndividuo[3];
		int x22 = genesIndividuo[4];
		int x23 = genesIndividuo[5];
		int x31 = genesIndividuo[6];
		int x32 = genesIndividuo[7];
		int x33 = genesIndividuo[8];
		int x41 = genesIndividuo[9];
		int x42 = genesIndividuo[10];
		int x43 = genesIndividuo[11];
		
		//Calculando zMin da funcao
		
		zMin = (6*x11) + (5*x12) + (8*x13) + (13*x21) + (12*x22) + (1*x23) + (7*x31)
				+ (9*x32) + (5*x33) + (10*x41) + (6*x42) + (4*x43);
		
		//Calculo das restricoes
		
		if(!(x11 + x12 + x13 == 10))
			zMin += 10*10;
		if(!(x21 + x22 + x23 == 20))
			zMin += 20*10;
		if(!(x31 + x32 + x33 == 12))
			zMin += 12*10;
		if(!(x41 + x42 + x43 == 13))
			zMin += 13*10;
		
		if(!(x11 + x21 + x31 + x41 == 8))
			zMin += 8*10;
		if(!(x12 + x22 + x32 + x42 == 32))
			zMin += 32*10;
		if(!(x13 + x23 + x33 + x43 == 15))
			zMin += 15*10;
		
		if(x11 == 0 || x12 == 0 || x13 == 0 || x21 == 0 || x22 == 0 || x23 == 0 || x31 == 0 || x32 == 0 || x33 == 0
				|| x41 == 0 || x42 == 0 || x43 == 0) {
			zMin += 1000*1000;
		}
		
		return zMin;
		
		//Random r = new Random();
		//return r.nextInt(100);
		
	}

	public static double getTaxaCrossover() {
		return taxaCrossover;
		
	}

	public static void setTaxaCrossover(double taxaCrossover) {
		Algoritmo.taxaCrossover = taxaCrossover;
		
	}

	public static double getTaxaMutacao() {
		return taxaMutacao;
		
	}

	public static void setTaxaMutacao(double taxaMutacao) {
		Algoritmo.taxaMutacao = taxaMutacao;
		
	}
	
	public static void setVariacaoNucleica(int variacaoNucleica) {
		Algoritmo.variacaoNucleica = variacaoNucleica;
		
	}
	
	public static int getVariacaoNucleica() {
		return variacaoNucleica;
		
	}
	
	public static Individuo getMelhorIndividuoGlobal() {
		return melhorIndividuoGlobal;
		
	}

}
