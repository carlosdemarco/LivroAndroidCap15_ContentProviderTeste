package br.livro.android.cap15.teste;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * ListActivity que demonstra como utilizar uma Intent e a ação ACTION_VIEW para
 * exibir um carro
 * 
 * Uri carro id=1: content://br.livro.android.provider.carro/carros/1
 * 
 * @author ricardo
 * 
 */
public class VisualizarCarroId1 extends Activity {
	private static final String CATEGORIA = "livro";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.main);

		// Visualizar o Contato id=1
		Uri uri = Uri.parse("content://br.livro.android.provider.carro/carros/1");

		Log.i(CATEGORIA, "Visualizando o carro: " + uri);

		// Envia a mensagem ao sistema operacional
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}
}
