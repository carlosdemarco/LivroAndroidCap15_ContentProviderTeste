package br.livro.android.cap15.teste;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import br.livro.android.cap14.banco.Carro.Carros;

/**
 * ListActivity que utiliza o Cursor recuperado do Content Provider
 * 'CarroProvider"
 * 
 * Uri carros: content://br.livro.android.provider.carro/carros
 * 
 * @author ricardo
 * 
 */
public class ListarCarros extends ListActivity {
	private ListAdapter adaptador;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Recupera o cursor dos contatos
		Cursor c = getContentResolver().query(Carros.CONTENT_URI, null, null, null, null);
		startManagingCursor(c);

		// Listar o nome e telefone do contato
		String[] columns = new String[] { Carros.NOME, Carros.PLACA };

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
		long id = c.getLong(c.getColumnIndexOrThrow(Carros._ID));
		String nome = c.getString(c.getColumnIndexOrThrow(Carros.NOME));
		String placa = c.getString(c.getColumnIndexOrThrow(Carros.PLACA));
		int ano = c.getInt(c.getColumnIndexOrThrow(Carros.ANO));

		Toast.makeText(this,"Carro selecionado: Id: " + id + ", Nome: " + nome + ", Placa: " + placa + ", Ano: " + ano,Toast.LENGTH_SHORT).show();
	}
}
