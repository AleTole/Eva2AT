package lista.eva2_at.db

import android.content.ContentValues
import lista.eva2_at.db.DBCarro.TablaCompras

// Esta clase ComprasDao que se encarga de interactuar con la base de datos.
class ComprasDao(val db: DBHelper) {

    // Función que devuelve todas las compras de la base de datos.
    fun finAll():List<Compra>{
        // Realizamos una consulta a la base de datos.
        val cursor = db.readableDatabase.query(
            TablaCompras.TABLA_LISTA_COMPRAS,
            null,
            "",
            null,
            null,
            null,
            "${TablaCompras.COLUMNA_COMPRADO} ASC"
        )
        // Creamos una lista mutable para almacenar las compras.
        val lista = mutableListOf<Compra>()
        // Iteramos sobre los resultados de la consulta.
        while (cursor.moveToNext()) {
            // Obtenemos los valores de cada columna.
            val id          = cursor.getInt(cursor.getColumnIndexOrThrow(TablaCompras.COLUMNA_ID) )
            val producto   = cursor.getString(cursor.getColumnIndexOrThrow(TablaCompras.COLUMNA_PRODUCTO) )
            val comprado   = cursor.getInt(cursor.getColumnIndexOrThrow(TablaCompras.COLUMNA_COMPRADO) )
            // Creamos un objeto Compra con los valores obtenidos.
            val compra = Compra(id, producto, comprado > 0)
            // Añadimos la compra a la lista.
            lista.add(compra)
        }
        // Cerramos el cursor.
        cursor.close()
        // Devolvemos la lista de compras.
        return lista
    }

    // Esta función inserta una nueva compra en la base de datos.
    fun insertar(compra: Compra):Long {
        // Creamos un ContentValues con los valores de la compra.
        val valores = ContentValues().apply {
            put(TablaCompras.COLUMNA_PRODUCTO, compra.producto)
            put(TablaCompras.COLUMNA_COMPRADO, compra.comprado)
        }
        // Insertamos los valores en la base de datos y devolvemos el ID de la fila insertada.
        return db.writableDatabase.insert(TablaCompras.TABLA_LISTA_COMPRAS, null, valores)
    }

    // Esta función elimina una compra de la base de datos por su ID.
    fun eliminar(id: Int) {
        db.writableDatabase.delete(TablaCompras.TABLA_LISTA_COMPRAS, "${TablaCompras.COLUMNA_ID} = ?", arrayOf(id.toString()))
    }

    // Esta función elimina todas las compras de la base de datos.
    fun eliminarTodos() {
        db.writableDatabase.delete(TablaCompras.TABLA_LISTA_COMPRAS, null, null)
    }

    // Esta función actualiza una compra en la base de datos.
    fun actualizar(compra: Compra) {
        val contentValues = ContentValues()
        contentValues.put(TablaCompras.COLUMNA_PRODUCTO, compra.producto)
        contentValues.put(TablaCompras.COLUMNA_COMPRADO, if (compra.comprado) 1 else 0)

        db.writableDatabase.update(TablaCompras.TABLA_LISTA_COMPRAS, contentValues, "${TablaCompras.COLUMNA_ID} = ?", arrayOf(compra.id.toString()))
    }
}

