package reproductor;

import java.io.File;



import javazoom.jlgui.basicplayer.BasicPlayer;

public class Reproductor {
	private String ruta;
	
	public String getRuta(){
		return ruta;
	
	}
	public BasicPlayer player;

	public Reproductor(String ruta) {
		super();
		this.ruta = ruta;
		this.player = new BasicPlayer();
	}

	public Reproductor() {
		player = new BasicPlayer();
	}

	public void coge(String y) {
	}

	public void Play() throws Exception {
		player.play();
	}

	public void AbrirFichero(String ruta) throws Exception {
		player.open(new File(ruta));
		player.play();
	}

	public void Pausa() throws Exception {
		player.pause();
	}

	public BasicPlayer getPlayer() {
		return player;
	}

	public void setPlayer(BasicPlayer player) {
		this.player = player;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void Continuar() throws Exception {
		player.resume();
	}

	public void Stop() throws Exception {
		player.stop();
	}

	
}