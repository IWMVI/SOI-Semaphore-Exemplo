package view;

import java.util.concurrent.Semaphore;

import controller.ThreadSemaforo;

public class Principal {
	public static void main(String[] args) {

		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int i = 0; i < 10; i++) {
			Thread tCarro = new ThreadSemaforo(i, semaforo);
			tCarro.start();
		}
	}
}
