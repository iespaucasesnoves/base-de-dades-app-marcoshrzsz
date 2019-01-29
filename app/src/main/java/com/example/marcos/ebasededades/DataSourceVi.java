package com.example.marcos.ebasededades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.solver.widgets.Helper;

import java.util.ArrayList;
import java.util.List;

public class DataSourceVi {

    private SQLiteDatabase db;
    private HelperVi dbHelp;

    private String[] allColumsVi = {
            HelperVi.COLUMN_ID,
            HelperVi.COLUMN_NOMVI,
            HelperVi.COLUMN_ANADA,
            HelperVi.COLUMN_LLOC,
            HelperVi.COLUMN_GRADUACIO,
            HelperVi.COLUMN_DATA,
            HelperVi.COLUMN_COMENTARI,
            HelperVi.COLUMN__IDBODEGA,
            HelperVi.COLUMN__IDDENOMINACIO,
            HelperVi.COLUMN_PREU,
            HelperVi.COLUMN_VALOLFATIVA,
            HelperVi.COLUMN_VALGUSTATIVA,
            HelperVi.COLUMN_VALVISUAL,
            HelperVi.COLUMN_NOTA,
            HelperVi.COLUMN_FOTO,
            HelperVi.COLUMN__TIPUS
    };

    public DataSourceVi (Context context){
        dbHelp = new HelperVi(context);
    }

    public void open(){
        db = dbHelp.getWritableDatabase();
    }

    public void close(){
        dbHelp.close();
    }

    public Vi createVi(Vi vi) {

        ContentValues values = new ContentValues();
        values.put(HelperVi.COLUMN_NOMVI, vi.getNomVi());
        values.put(HelperVi.COLUMN_ANADA, vi.getAnada());
        values.put(HelperVi.COLUMN_TIPUS, vi.getTipus());
        values.put(HelperVi.COLUMN_LLOC, vi.getLloc());
        values.put(HelperVi.COLUMN_GRADUACIO, vi.getGraduacio());
        values.put(HelperVi.COLUMN_DATA, String.valueOf(vi.getData()));
        values.put(HelperVi.COLUMN_COMENTARI, vi.getComentari());
        values.put(HelperVi.COLUMN_IDBODEGA, vi.getIdBodega());
        values.put(HelperVi.COLUMN_IDDENOMINACIO,vi.getIdDenominacio());
        values.put(HelperVi.COLUMN_PREU,vi.getPreu());
        values.put(HelperVi.COLUMN_VALOLFATIVA,vi.getValOlfativa());
        values.put(HelperVi.COLUMN_VALGUSTATIVA,vi.getValGustativa());
        values.put(HelperVi.COLUMN_VALVISUAL,vi.getValVisual());
        values.put(HelperVi.COLUMN_NOTA,vi.getNota() );
        values.put(HelperVi.COLUMN_FOTO,vi.getFoto() );
        long insertId = db.insert(HelperVi.TABLE_VI, null,values);
        vi.setId(insertId);
        return vi;
    }

    public boolean updateVi(Vi vi) {

        ContentValues values = new ContentValues();
        long id=vi.getId();
        values.put(HelperVi.COLUMN_NOMVI, vi.getNomVi());
        values.put(HelperVi.COLUMN_ANADA, vi.getAnada());
        values.put(HelperVi.COLUMN_LLOC, vi.getLloc());
        values.put(HelperVi.COLUMN_TIPUS, vi.getTipus());
        values.put(HelperVi.COLUMN_GRADUACIO, vi.getGraduacio());
        values.put(HelperVi.COLUMN_DATA, String.valueOf(vi.getData()));
        values.put(HelperVi.COLUMN_COMENTARI, vi.getComentari());
        values.put(HelperVi.COLUMN_IDBODEGA, vi.getIdBodega());
        values.put(HelperVi.COLUMN_IDDENOMINACIO,vi.getIdDenominacio());
        values.put(HelperVi.COLUMN_PREU,vi.getPreu());
        values.put(HelperVi.COLUMN_VALOLFATIVA,vi.getValOlfativa());
        values.put(HelperVi.COLUMN_VALGUSTATIVA,vi.getValGustativa());
        values.put(HelperVi.COLUMN_VALVISUAL,vi.getValVisual());
        values.put(HelperVi.COLUMN_NOTA,vi.getNota() );
        values.put(HelperVi.COLUMN_FOTO,vi.getFoto() );
        return db.update(HelperVi.TABLE_VI, values,HelperVi.COLUMN_ID + "=" + id, null) > 0;
    }

    public void deleteVi(Vi vi) {
        long id = vi.getId();
        db.delete(HelperVi.TABLE_VI, HelperVi.COLUMN_ID + " = " + id, null);
    }
    public Vi getVi(long id) {
        Vi vi;
        Cursor cursor = db.query(HelperVi.TABLE_VI,
                allColumsVi, HelperVi.COLUMN_ID + " = " + id, null,
                null, null, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            vi = cursorToVi(cursor);
        } else { vi=new Vi(); } // id=-1 no trobat
        cursor.close();
        return vi;
    }

    public List<Vi> getAllVi(){

        List <Vi> vins = new ArrayList<>();

        Cursor cursor = db.query(HelperVi.TABLE_VI, allColumsVi, null, null, null,null, HelperVi.COLUMN_DATA + "DESC");

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            Vi vi = cursorToVi(cursor);
            vins.add(vi);
            cursor.moveToNext();

        }

        cursor.close();
        return vins;

    }

    private Vi cursorToVi(Cursor cursor){

        Vi vi= new Vi();
        vi.setId(cursor.getLong(0));
        vi.setNomVi(cursor.getString(1));
        vi.setAnada(cursor.getString(2));
        vi.setLloc(cursor.getString(3));
        vi.setGraduacio(cursor.getString(4));
        vi.setData(cursor.getString(5));
        vi.setComentari(cursor.getString(6));
        vi.setIdBodega(cursor.getLong(7));
        vi.setIdDenominacio(cursor.getLong(8));
        vi.setPreu(cursor.getFloat(9));
        vi.setValOlfativa(cursor.getString(10));
        vi.setValGustativa(cursor.getString(11));
        vi.setValVisual(cursor.getString(12));
        vi.setNota(cursor.getInt(13));
        vi.setFoto(cursor.getString(14));
        return vi;

    }


}
