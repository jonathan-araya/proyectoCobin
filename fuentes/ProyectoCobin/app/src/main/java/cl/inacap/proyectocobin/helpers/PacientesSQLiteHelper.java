package cl.inacap.proyectocobin.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {

    private final String sqlCreate = "CREATE TABLE " +
            "pacientes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
            ",rut TEXT" +
            ",nombre TEXT" +
            ",apellido TEXT" +
            ",fecha TEXT" +
            ",area TEXT" +
            ",sintomas INTEGER" +
            ",temperatura REAL" +
            ",tos INTEGER" +
            ",presion INTEGER)";



    public PacientesSQLiteHelper(@Nullable Context context
            , @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory
            , int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL("INSERT INTO pacientes (rut, nombre, apellido, fecha, area, sintomas, temperatura, tos, presion)" +
                " VALUES('19013317-6db','Jontan','Araia','11-11-2020','otro',1,38,1,999)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL("INSERT INTO pacientes (rut, nombre, apellido, fecha, area, sintomas, temperatura, tos, presion)" +
                " VALUES('19013317-6db','Jontan','Araia','11-11-2020','otro',1,38,1,999)");

    }
}
