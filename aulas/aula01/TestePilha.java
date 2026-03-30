package aulas.aula01;

public class TestePilha {
	public static long tempoInicial = 0;
	public static long tempoFinal = 0;
	public static long qtdInteracoes = 100000;
	public static void testeDuplicacao(){	
		System.out.println("Duplicação");	
		PilhaArray pp=new PilhaArray(1,0);

		System.out.println("inserindo");
		tempoInicial = System.currentTimeMillis();
		for(int f=0;f<qtdInteracoes;f++){		  
		  pp.push(f);
		}
		tempoFinal = System.currentTimeMillis();
		System.out.println("Execução em milisegundos: " + (tempoFinal - tempoInicial));

		System.out.println("retirando");
		tempoInicial = System.currentTimeMillis();
		for(int f=0;f<qtdInteracoes;f++){
			pp.pop();
		}
		tempoFinal = System.currentTimeMillis();
		System.out.println("Execução em milisegundos: " + (tempoFinal - tempoInicial));
	}

	public static void testeIncremental(){	
		System.out.println("Incremental");
		PilhaArray pp=new PilhaArray(1,100);

		System.out.println("inserindo");
		tempoInicial = System.currentTimeMillis();
		for(int f=0;f<qtdInteracoes;f++){		  
		  pp.push(f);
		}
		tempoFinal = System.currentTimeMillis();
		System.out.println("Execução em milisegundos: " + (tempoFinal - tempoInicial));

		System.out.println("retirando");
		tempoInicial = System.currentTimeMillis();
		for(int f=0;f<qtdInteracoes;f++){
			pp.pop();
		}
		tempoFinal = System.currentTimeMillis();
		System.out.println("Execução em milisegundos: " + (tempoFinal - tempoInicial));
	}

	public static void main(String[] args) {		
		TestePilha.testeDuplicacao();
		TestePilha.testeIncremental();
	}
}
