package lista.eva2_at.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import lista.eva2_at.db.DBCarro.TablaCompras

class DBHelper(contexto: Context) : SQLiteOpenHelper(contexto, DB_NOMBRE, null, DB_VERSION) {

    companion object{
        const val DB_NOMBRE = "compras.db"
        const val DB_VERSION = 1
        const val SQL_CREACION_TABLAS = """
            CREATE TABLE ${TablaCompras.TABLA_LISTA_COMPRAS}(
            ${TablaCompras.COLUMNA_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${TablaCompras.COLUMNA_PRODUCTO} TEXT,
            ${TablaCompras.COLUMNA_COMPRADO} BOOLEAN
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREACION_TABLAS)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}
