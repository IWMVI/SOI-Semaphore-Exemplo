package controller;

import java.util.concurrent.Semaphore;

public class ThreadSemaforo extends Thread {

	private Semaphore semaforo;

	private int idCarro;
	private static int posChegada;
	private static int posSaida;

	public ThreadSemaforo(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		carroAndando();
// 		Início seção crítica.
		try {
			semaforo.acquire();
			carroEstacionando();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
// 		Fim seção crítica.
			carroSaindo();
		}
	}

	private void carroAndando() {
		// Math.random() < 0 |- 0.99999... >
		int distanciaTotal = (int) ((Math.random() * 1001) + 1000);
		int distanciaPercorrida = 0;
		int deslocamento = 100;
		int tempo = 1000;
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			System.out.println("#" + idCarro + " já andou: " + distanciaPercorrida + "m.");
		}
		posChegada++;
		System.out.println("#" + idCarro + " foi o " + posChegada + "º a chegar");
	}

	private void carroEstacionando() {
		System.out.println("#" + idCarro + " estacionou!");
		int tempo = (int) ((Math.random() * 2001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private void carroSaindo() {
		posSaida++;
		System.out.println("#" + idCarro + " foi o " + posSaida + "º a sair!");
	}
}
