package com.example.ejercicio2_3_pm01;

import androidx.appcompat.app.AppCompatActivity;import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import android.os.Bundle;

import com.example.ejercicio2_3_pm01.transacciones.Transacciones;

public class ListaImagenes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagenes);

        ListView simpleGridView = (ListView) findViewById(R.id.listView);

        SQLiteConexion help = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        ArrayList<Fotografia> listaImagenes = new ArrayList<>();
        Cursor c= help.getAll();

        int i=0;
        if(c.getCount()>0)
        {
            c.moveToFirst();
            while(c.isAfterLast()==false)
            {

                byte[] bytes=c.getBlob(c.getColumnIndexOrThrow(Transacciones.blobImagen));
                String descripcion=c.getString(c.getColumnIndexOrThrow(Transacciones.descripcion));

                Fotografia fotografia = new Fotografia(BitmapFactory.decodeByteArray(bytes, 0, bytes.length), descripcion);
                listaImagenes.add(fotografia);
                c.moveToNext();
                i++;
            }

            Adaptador myAdapter=new Adaptador(this,R.layout.grid_view_items,listaImagenes);
            simpleGridView.setAdapter(myAdapter);
        }


    }
}