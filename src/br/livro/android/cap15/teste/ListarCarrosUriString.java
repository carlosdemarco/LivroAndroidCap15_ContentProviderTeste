package br.livro.android.cap15.teste;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * ListActivity que utiliza o Cursor recuperado do Content Provider
 * 'CarroProvider"
 * 
 * Uri carros: content://br.livro.android.provider.carro/carros
 * 
 * @author ricardo
 * 
 */
public class ListarCarrosUriString extends ListActivity {
	private ListAdapter adaptador;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Recupera o cursor dos contatos
		Cursor c = getContentResolver().query(Uri.parse("content://br.livro.android.provider.carro/carros"), null,null, null, null);
		startManagingCursor(c);

		// Listar o nome e telefone do contato
		String[] columns = new String[] { "nome", "placa" };

		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		// layout nativo para simplificar
		int layoutNativo = android.R.layout.simple_list_item_2;

		// Informa o adapter para ligar os valores ao XML da View
		adaptador = new SimpleCursorAdapter(this, layoutNativo, c, columns, to);

		setListAdapter(adaptador);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long itemId) {
		super.onListItemClick(l, v, position, itemId);

		// Recupera o cursor na posição selecionada
		Cursor c = (Cursor) adaptador.getItem(position);

		// Recupera o Nome e Telefone
		long id = c.getLong(c.getColumnIndexOrThrow("_id"));
		String nome = c.getString(c.getColumnIndexOrThrow("nome"));
		String placa = c.getString(c.getColumnIndexOrThrow("placa"));
		int ano = c.getInt(c.getColumnIndexOrThrow("ano"));

		Toast.makeText(this,"Carro selecionado: Id: " + id + ", Nome: " + nome + ", Placa: " + placa + ", Ano: " + ano,
				Toast.LENGTH_SHORT).show();
	}
}
