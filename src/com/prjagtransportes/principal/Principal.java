package com.prjagtransportes.principal;

import com.prjagtransportes.algoritmo.Algoritmo;
import com.prjagtransportes.algoritmo.Populacao;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		//Primeiro Teste, classe Individuo
		Individuo i1 = new Individuo(7);
		System.out.println(i1);
		
		Individuo iAux;
		
		for(int i = 0; i < 1000; i++) {
			iAux = new Individuo(i1.getGenes());
			System.out.println(iAux);
			
		}
		**/
		
		/**
		//Segundo Teste, classe Populacao
		Populacao p1 = new Populacao(7, 100);
		System.out.println("Populacao sem ordenacao por Aptidao:");
		System.out.println(p1);
		p1.ondernaPopulacao();
		System.out.println("Populacao ordenada por Aptidao:");
		System.out.println(p1);
		**/
		
		/**
		//Terceiro Teste, classe Algoritmo
		Populacao p1 = new Populacao(7, 100);
		p1.ordernaPopulacao();
		System.out.println("Geracao 1:\n" + p1);
		Populacao np = Algoritmo.novaGeracao(p1, true);
		System.out.println("Geracao 2:\n" + np);
		Populacao np_2 = Algoritmo.novaGeracao(np, true);
		System.out.println("Geracao 3:\n" + np_2);
		**/
		
		/**
		//Quarto teste, implementacao de crossover, taxa de mutacao
		Algoritmo.setTaxaCrossover(0.60);
		Algoritmo.setTaxaMutacao(0.30);
		
		Populacao p1 = new Populacao(7, 100);
		p1.ordernaPopulacao();
		System.out.println("Geracao 1:\n" + p1);
		Populacao np = Algoritmo.novaGeracao(p1, true);
		System.out.println("Geracao 2:\n" + np);
		Populacao np_2 = Algoritmo.novaGeracao(np, true);
		System.out.println("Geracao 3:\n" + np_2);
		**/
		
		/**
		//Quinto teste, implementacao do validaSolucaoDoIndividuo pela classe Algoritmo, parte 1
		Algoritmo.setTaxaCrossover(0.60);
		Algoritmo.setTaxaMutacao(0.30);
		
		Populacao p1 = new Populacao(12, 100);
		p1.ordernaPopulacao();
		System.out.println("Geracao 1:\n" + p1);
		Populacao np = Algoritmo.novaGeracao(p1, true);
		System.out.println("Geracao 2:\n" + np);
		Populacao np_2 = Algoritmo.novaGeracao(np, true);
		System.out.println("Geracao 3:\n" + np_2);
		**/
		
		/**
		//Sexto teste, implementacao do validaSolucaoDoIndividuo pela classe Algoritmo, parte 2: metodo
		//de ordenacao ordena do menor para o maior, testando com a verificacao de solucao; verificacao
		//de tempo de execucao; verificacao de melhor solucao
		
		long tempoInicio = System.currentTimeMillis();
		
		Algoritmo.setTaxaCrossover(0.60);
		Algoritmo.setTaxaMutacao(0.30);
		Algoritmo.setVariacaoNucleica(10);
		
		Populacao p1 = new Populacao(12, 1000);
		p1.ordernaPopulacao();
		
		int controle = 0;
		Populacao busca;
		while (controle < 1000) {
			busca = Algoritmo.novaGeracao(p1, false);
			System.out.println("Geracao " + (controle+1) + ":\n" + busca);
			controle++;
			
		}
		
		System.out.println(Algoritmo.getMelhorIndividuoGlobal());
		System.out.println("Tempo total: " + (System.currentTimeMillis() - tempoInicio)/1000);
		**/
		
		//Teste final, algortimo setado para encontrar o melhor individiuo gerando 1000 individiduos
		//por população, durante gerando 1000 geracoes. O teste se realiza em cinco vezes, a fim de termos
		//os melhores individuos dessas amostras
		
		long tempoInicioGlobal = System.currentTimeMillis();
		
		Algoritmo.setTaxaCrossover(0.60);
		Algoritmo.setTaxaMutacao(0.30);
		Algoritmo.setVariacaoNucleica(10);
		
		for(int i = 0; i < 10; i++) {
			long tempoInicioTeste = System.currentTimeMillis();
			
			Populacao p = new Populacao(12, 1000);
			p.ordernaPopulacao();
			int controle = 0;
			
			while(controle < 100) {
				p = Algoritmo.novaGeracao(p, false);
				controle++;
				
			}
			
			System.out.println("Melhor individuo do teste " + (i+1) + ": " + Algoritmo.getMelhorIndividuoGlobal());
			System.out.println("Tempo do teste " + (i+1) + ": " + (System.currentTimeMillis()-tempoInicioTeste) + "ms");
			
		}
		
		System.out.println("Tempo total do teste: " + (System.currentTimeMillis() - tempoInicioGlobal)/1000);
		
	}

}
