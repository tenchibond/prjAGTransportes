package com.prjagtransportes.algoritmo;

public class Populacao {
	
	private Individuo[] individuos;
	private int tamPopulacao;
	
	//Cria uma populacao aleatoria de individuos
	public Populacao(int numGenes, int tamPopulacao) {
		this.tamPopulacao = tamPopulacao;
		individuos = new Individuo[this.tamPopulacao];
		for(int i = 0; i < this.tamPopulacao; i++) {
			individuos[i] = new Individuo(numGenes);
			
		}
		
	}
	
	//Cria uma populacao com individuos sem genes, serao compostos posteriormente
	public Populacao(int tamPopulacao) {
		this.tamPopulacao = tamPopulacao;
		individuos = new Individuo[this.tamPopulacao];
		for(int i = 0; i < this.tamPopulacao; i++) {
			individuos[i] = null;
			
		}
		
	}
	
	//Coloca um individuo em determinada posicao na populacao
	public void setIndividuo(Individuo individuo, int posicao) {
		individuos[posicao] = individuo;
		
	}
	
	//Coloca um individuo na primeia posicao disponivel na populacao
	public void setIndividuo(Individuo individuo) {
		for(int i = 0; i < this.individuos.length; i++) {
			if(individuos[i] == null) {
				individuos[i] = individuo;
				break;
			}
			
		}
		
	}
	
	//Verifica se algum individuo da populacao tem a solucao. Precisa da implementacao de
	//Algoritmo.validaSolucaoDoIndividuo, caso seja igual ao valor maximo (100), ela e a solucao
	//do problema. O metodo se encontra implementado, mais ainda gerando um random de 0 a 100
	//enquanto nao ha o calculo correto.
	public boolean temSolucao() {
		Individuo individuoSolucao = null;
		int valorSolucao = 1;
		
		//Provisoriamente, o if dentro do for validara um valor qualquer, porem, quando implementada
		//o metodo acima, a condicao do if sera alterada.
		for(int i = 0; i < this.individuos.length; i++) {
			if(Algoritmo.validaSolucaoDoIndividuo(individuos[i]) == valorSolucao) {
				individuoSolucao = individuos[i];
				break;
				
			}
			
		}
		
		if(individuoSolucao == null) { 
			return false;
			
		} else {
			return true;
			
		}
		
	}
	
	//Ordena a populacao pelo valor da aptidao de cada individuo, do maior para o menor. Com isso se
	//obtem o melhor individuo acessando a primeira posicao do array, bem como os tenho ordenados caso
	//necessite.
	public void ordernaPopulacao() {
		boolean trocou = true;
		while(trocou) {
			trocou = false;
			for(int i = 0; i < individuos.length - 1; i++) {
				if(individuos[i].getAptidao() > individuos[i + 1].getAptidao()) {
					Individuo aux = individuos[i];
					individuos[i] = individuos[i+1];
					individuos[i+1] = aux;
					trocou = true;
				}
				
			}
			
		}
		
	}
	
	//Resgata o total de individuos nao-nulos existentes na populacao
	public int getNumIndividuos() {
		int qtd = 0;
		for(int i = 0; i < individuos.length; i++) {
			if(individuos[i] != null) {
				qtd++;
				
			}
			
		}
		
		return qtd;
		
	}
	
	public int getTamPopulacao() {
		return tamPopulacao;
		
	}
	
	public Individuo getIndividuo(int pos) {
		return individuos[pos];
		
	}
	
	public String toString() {
		String stringPopulacao = "";
		for(int i = 0; i < individuos.length; i++) {
			if(individuos[i] == null) {
				stringPopulacao += "*** INDIVIDUO NULO ***";
				
			} else {
				stringPopulacao += individuos[i].toString() + "\n";
				
			}
			
		}
		
		return stringPopulacao;
		
	}

}
