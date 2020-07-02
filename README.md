# ing-sw-2020-mandocdoc-mascheroni-morici

Membri del gruppo:
-
- Ray Oliver Mandocdoc
- Marco Mascheroni
- Filippo Morici

Requisiti implementati:
-
- Regole complete
- CLI
- GUI
- Socket
- FA: Partite multiple

Istruzioni configurazione:
-
- Server:
Configura port forward utilizzando come porta di interna la *7777* e come porta esterna la *42069*, assicurarsi che il server abbia ip statico.
- Client:
Aprire archivio jar, modificare il file connection.json cambiando il valore dell'attributo *server* con:
  - IP pubblico del router in cui é connesso il server (caso client remoto).
  - IP locale e *porta* la 7777 (caso client locale).
- Il client apre automaticamente in modalità GUI, per aprire il client in modalità CLI aggiugere a terminale il parametro *--cli*
