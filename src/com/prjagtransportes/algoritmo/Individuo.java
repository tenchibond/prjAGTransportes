package com.prjagtransportes.algoritmo;

import java.util.Arrays;
import java.util.Random;

public class Individuo {
	
	private int[] genes;
	private int aptidao;
	
	//Implementar a variacao nucleica: restringir o valor que pode ser alcancado pelo gene. Sem essa
	//restricao, o valor do gene poderia ser absurdamente alto, o que dependendo do problema seria 
	//impraticavel. Com a variacao nucleica, se delimita os valores randomicos, podendo facilitar a 
	//descoberta da solucao do problema. Sera implementado pela classe Algoritmo, e sera repassado
	//para a classe Individuo quando sempre quando for criar novos Individuos. Por enquanto, o valor
	//de random sera 1000.
	
	//Gera um individuo aleatorio
	public Individuo(int numGenes) {
		genes = new int[numGenes]; 
		Random r = new Random();
		for(int i = 0; i < numGenes; i++) {
			genes[i] = r.nextInt(Algoritmo.getVariacaoNucleica());
			
		}
		
		geraAptidao();
		
	}
	
	//Gera um indivíduo com genes
	public Individuo(int[] genes) {
		Random r = new Random();
		this.genes = genes;
		
		//Se for mutar, cria um gene aleatorio
		if(r.nextDouble() <= Algoritmo.getTaxaMutacao()) {
			int posAleatoria = r.nextInt(genes.length);
			for(int i = 0; i < genes.length; i++) {
				if(i == posAleatoria) {
					genes[posAleatoria] = r.nextInt(Algoritmo.getVariacaoNucleica());
					
				}
				
			}
			
			this.genes = genes;
			
		}
		
		geraAptidao();
		
	}
	
	//Gera o valor de aptidao do Individuo, sendo calculado de acordo com o retorno dado
	//pelo metodo Algoritmo.validaSolucaoDoIndividuo (Implementada, usando um random de 
	//0 a 100, enquanto nao e feita o calculo com base no problema e nas restricoes)
	private void geraAptidao() {
		
		//Random r = new Random();
		//this.aptidao = r.nextInt(100);
		this.aptidao = Algoritmo.validaSolucaoDoIndividuo(this);
		
	}
	
	public int getAptidao() {
		return aptidao;
		
	}
	
	public int[] getGenes() {
		return genes;
		
	}
	
	public String toString() {
		return "Individuo: " + Arrays.toString(this.getGenes()) + " | Aptidao: " + this.getAptidao();
		
	}
	
}
