# Esame 25-06-2019 
Progetto realizzato per la prova pratica del corso di "Programmazione ad Oggetti". Consiste in un programma che scarica un dataset in formato CSV, ne fa il parsing dei dati e mediante opportune richieste effettua flitraggi e/o statistiche sui dati. 

## Inclusi
Tutti i file sorgente (.java)

Il JavaDoc relativo al software

Questo README

# Struttura del Progetto
Il Software è suddiviso in quattro package che contengono le diverse classi:

 - **`dataset`** contiene le classi: `Dati` `Lista` `Container` e `Punto` necessarie alla modellazione del dataset 
 - **`Metadati`** contiene la classe `metadata` che caratterizza i metadati
 - **`Operazioni`** contiene le classi `Filtri` `Statistiche` `TrovaDaPunto` e `Statistics` atte a eseguire le operazioni di filtraggio e statistiche del dataset
 - **`Progetto`** contiene le classi `Controller` `Download Dataset` e `EsameApplication` che rappresentano il software vero e proprio.

# Utilizzare il Software
Il software sfrutta un web-server in locale sulla porta 8080 che prende le richieste dal client. All'avvio del programma viene scaricato il dataset in formato CSV e viene effettuato il suo parsing.

Il software può gestire fondamentalmente tre tipi di richieste:

 - Restituisce i metdati del dataset
 - Restituisce i dati del dataset (che possono essere filtrati)
 - Restituisce statistiche effettuate sui dati (che possono essere filtrati) 
 
 La rotta `/metadata`  restituisce la lista dei **metadati**  in formato JSON 
 la rotta `/data` restituisce la lista dei **dati** in formato JSON
 la rotta `/linea` con il parametro `Fid` restituisce la struttura `Dati` con il Fid desiderato
 
 **Filtri**
 
I filtri devono essere inseriti nella richiesta POST come stringa.
il formato della stringa è:
`{"operatore":{"campo": "valore"}}`
dove **Operatore** indica il tipo di filtro che si applica, **campo** il campo su cui si vuole effettuare il filtraggio e  **valore** il valore desiderato.
 I filtri sono di due tipi: condizionali o logici e si indicano con i seguenti simboli:
 
 - **condizionali**:
   - "$bt" che corrisponde al compreso (>= value <=)
   - "$lte" che corrisponde al minore o uguale (<=)
   - "$gte" che corrisponde al maggiore o uguale (>=)
 - **logici**:
    - "$And" restitusce la lista che contiene entrambi i campi selezionati con i valori desiderati
    -  "$or" restitusce la lista che contiene almeno uno dei campi selezionati con i valori desiderati  

**Esempi**
 - `{"$gte":{"latitudine":41}}` restituisce una lista con tutte le strutture che hanno la latitudine di almeno 41
 - `{"$bt":{"latitudine":[41,42]}}` restituisce una lista con tutte le strutture che hanno la latitudine compresa tra 41 e 42
 - `{"$and":{"tipo":[provincia,comune],"campo":[AV,Avellino]}}` restituisce una lista con le strutture che hanno la provincia "AV" e i comune "Avellino"

È stata implementata una funzione corrispondente alla rotta `/find` con parametri `Lat`,`Lon` e `Radius` che partendo dal punto fissato con `Lat` e `Lon` crea una circonferenza di raggio `Radius` e restituisce una lista delle strutture che sono collocate all'interno di quest'area.

La rotta `stat` con richiesta GET ed i parametri `tipo`,`campo` restituisce le statistiche eseguite sul campo `tipo` di valore `campo`

la rotta `stat` con richiesta POST con parametri `campo`,`nome` e body `body` effettua un filtraggio della lista specificato nel body e
seuccessivamente effettua le statistiche sul campo `campo` di valore `nome`; il parametro `nome` deve essere inserito solamente nel caso il tipo del campo su cui si vuole  effettuare le statistiche sia di tipo String

**Esempi**
- `/stat?tipo=provincia&campo=AV` effettua il conteggio delle ripetizioni di AV nel dataset
- `/stat?tipo=latitudine` effettua somma, media, conteggio, deviazione standard, massimo e minimo del campo latitudine

I vari metodi delle classi sono spiegati dettagliatamente nel JavaDoc. Di seguito allegata i diagrammi UML dei casi d'uso, delle classi e delle sequenze.

**Casi D'uso**

![
](https://lh3.googleusercontent.com/pSDfcqCuc2Z2H0iiQM1yRsYGMoLYF3LlDlo3pl1wErKGlkb9K8SC-kQiSq3i2eNsQ1SlHqggTAkN "d")

 
**Diagramma delle classi**
![enter image description here](https://lh3.googleusercontent.com/ShF5LJl2tAx_wl7vHN_uhMmfYnK4gJC0pYmWoxqnSO5kD-sX_MtOn-6hdBST3v_xlIaUaDhas3Xh=s15520)

**Diagramma delle sequenze**
![
](https://lh3.googleusercontent.com/Dkffv5bBHkDT0zZUe6I6TOfu6UpiwJnMnV94nX0DXzuW6cyzvV2BMHcPjumUd0kKGvEesZbo8tCp=s10000) 

> Written with [StackEdit](https://stackedit.io/).
