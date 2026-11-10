package com.example.myapplication.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File

internal class AppDatabase(context: Context): SQLiteOpenHelper(
    context.applicationContext,
    File(context.noBackupFilesDir, "aula_sqlite.db").absolutePath,
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(""" 
            CREATE TABLE usuarios(
            id INTEGER PRIMARY KEY,
            nome TEXT NOT NULL CHECK(length(nome) BETWEEN 3 AND 100),
            telefone TEXT NOT NULL UNIQUE,
            senha BLOB NOT NULL,
            salt BLOB NOT NULL
            );
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE contatos(
            id INTEGER PRIMARY KEY,
            usuario_id INTEGER NOT NULL,
            usuario_contato_id INTEGER NOT NULL,
            nome TEXT,
            FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
            FOREIGN KEY (usuario_contato_id) REFERENCES usuarios(id),
            
            UNIQUE(usuario_id, usuario_contato_id)
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE conversas(
            id INTEGER PRIMARY KEY,
            usuario_1_id INTEGER NOT NULL,
            usuario_2_id INTEGER NOT NULL,
            ultima_atualizacao INTEGER NOT NULL,
            FOREIGN KEY (usuario_1_id) REFERENCES usuarios(id),
            FOREIGN KEY (usuario_2_id) REFERENCES usuarios(id),
            
            UNIQUE(usuario_1_id, usuario_2_id)
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE mensagens(
            id INTEGER PRIMARY KEY,
            conversa_id INTEGER NOT NULL,
            remetente_id INTEGER NOT NULL,
            conteudo TEXT NOT NULL,
            data_envio INTEGER NOT NULL,          
            status TEXT DEFAULT 'ENTREGUE',
            
            FOREIGN KEY (conversa_id) REFERENCES conversas(id) ,
            FOREIGN KEY (remetente_id) REFERENCES usuarios(id) 
            )
        """.trimIndent())
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        TODO("Not yet implemented")
    }


}