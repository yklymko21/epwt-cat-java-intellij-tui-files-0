# Type: Exercici Pràctic Amb Teoria (With Theory)
# Language: Catalan
# Programming Language: Java
# Tools: IntelliJ, git
# Contents: TUI, Files 

## Conceptes previs

### TUI (Terminal User Interface)

A Java podem preguntar a l'usuari que introdueixi valors per pantalla. L'usuari usarà el teclat. Un cop l'usuari ha introduit valors, els podrem guardar en variables.

Això ho aconseguim amb la classe `Scanner` i `System.in`. `System.in` (entrada des del teclat) és el contrari de `System.out` (impresió per pantalla).

Hi ha diferents tipus de `Scanner`. Per a diferenciar un tipus d'`Scanner` d'un altre, en el moment d'instanciar un objecte amb `new` li haurem de passar com a argument algun valor que sigui vàlid, i depenent del valor que li passem, tindrem un tipus d'Scanner o un altre. Per exemple, a continuació tenim dos tipus diferents de `Scanner`:

```java
Scanner scan1 = new Scanner("Programming is funny");
Scanner scan2 = new Scanner(System.in);
```

_scan1_ servirà per a escanejar l'String "Programming is funny", mentre que _scan2_ servirà per a escanejar allò que arribi des de `System.in` (des del teclat).

Els valors vàlids que podem passar quan fem `new` són aquells que venen definits per les diferents sobrecàrregues dels **constructors** de la classe. Els constructors són un tipus especial de mètodes que tenen totes les classes i que s'estudiaran amb detall a la UF4. De moment, no cal saber-ne res més, només que es poden passar arguments en el moment de crear una instància amb `new`. Ho veiem també en el següent exemple.

Per veure com funciona `Scanner` com a _tui_, posem un exemple:

```java
//Primer cal crear un objecte de tipus Scanner
//li passarem com a argument System.in
//per rebre l'entrada des del teclat
Scanner in = new Scanner(System.in)

System.out.print("Com et dius? ");
String name = in.nextLine(); //Escaneja la següent línia i la guarda en la variable name

System.out.println("El teu nom és " + name);

in.close(); //En acabar d'usar l'scanner, cal tancar-lo
```

El mètode `nextLine()` de la classe `Scanner` llegeix tota la línia introduïda per l'usuari fins que prem _Enter_. Ho retorna en forma d'`String`.

La classe `Scanner` també té altres mètodes, que enlloc de retornar un `String` retornen int, double, etc... Per exemple:

```java
Scanner in = new Scanner(System.in)

System.out.print("Quants anys tens? ");
int age = in.nextInt();

System.out.println("Tens " + age + " anys");

in.close();
```

Cal anar amb compte amb l'ús de `nextInt`, `nextDouble`, etc ja que, al contrari que `nextLine`, no salten de línia en acabar de llegir. Per tant, ens podem trobar amb comportament inesperats. Hi ha dues solucions:

1. Després de cridar un `nextInt`, `nextDouble`, etc cridar un `nextLine` (que servirà per saltar de línia) abans d'usar de nou un `nextLine` per a llegir una nova línia que introdueixi l'usuari.
2. Usar sempre `nextLine` i quan vulguem que el valor de retorn sigui, per exemple, un `int` enlloc d'un `String`, usar alguna de les diferents opcions que Java ofereix per a convertir un String a int (o a double o al que sigui que vulguem).

Amb l'enunciat de la pràctica us ofereixo un mètode a la classe `Utils` que serveix per a convertir un String a int.

**Compte**: si intenteu llegir un String que no és un enter amb `nextInt()` us donarà error. Per exemple, donarà error si l'String és "dos", però no donarà error si és "2". Així mateix, us donarà error si intenteu convertir un String a int quan l'String no és un enter. Exemples:

```java
Scanner in = new Scanner(System.in)

System.out.print("Quants anys tens? ");
int age = in.nextInt();
//Si l'usuari no escriu un número, peta
```

```java
Scanner in = new Scanner(System.in)

System.out.print("Quants anys tens? ");
int age = stringToInt(in.nextLine()); //Funció per a convertir
//Si l'usuari no escriu un número, peta
```

### Lectura i escriptura de fitxers amb FileWriter, FileReader, BufferedWriter i BufferedReader.

Nota: teniu els exemples explicats a les carpetes _files_exemple0_ i _files_exemple1_.

Amb Java podem representar un fitxer amb la classe `File`. Exemple:

```java
File file = new File("students.out");
```
On _students.out_ és el nom del fitxer. Si el fitxer es troba ubicat en un directori diferent del directori de treball (on s'executa el programa, normalment) llavors haurem d'escriure la ruta completa. Per exemple:

```java
//Per a sistemes Unix (Linux, Mac, BSD, Solaris ...)
File unixFile = new File("/home/testuser/m3/uf3/students.out");

//Per a sistemes Windows
File winFile = new File("C:\\Users\\testuser\\m3\\uf3\\students.out");
```

A un projecte IntelliJ IDEA, el directori de treball és el directori del projecte (no l'_src_, sinó l'immediatament superior a _src_).

**Com podem llegir els continguts d'un fitxer, o escriure-hi informació?**

Dependrà del tipus de fitxer. En general, tenim dos tipus de fitxers: els de **texte**, i els fitxers **binaris**. En aquesta UF3 treballarem només amb fitxers de texte.

### Primera solució: les classes FileReader i FileWriter

**Quan el texte que volem llegir o escriure és petit** podrem utilitzar les classes `FileReader` (per a llegir des d'un fitxer) o `FileWriter` (per a escriure en un fitxer). Veiem un exemple d'escriptura, i un exemple de lectura:

```java
//Exemple d'escriptura a fitxer

//Primer creem la instància de fitxer
//Pot ser un fitxer que existeixi o no (al nostre disc)
//Si no existeix i volem escriure-hi, el crearà
File fitxer = new File("output"); 

//Creem un FileWriter per a escriure-hi al fitxer
//El booleà com a segon paràmetre vol dir:
//true: allò que escribim s'afegirà als continguts ja existents
//false: allò que escribim substituirà els continguts ja existents
FileWriter fw = new FileWriter(fitxer, true);

//Executem el mètode write per a escriure aquest texte
fw.write("This file has been filled from Java");

//Finalment, cal tancar el FileWriter
fw.close();
```

```java
//Exemple de lectura des de fitxer

//Primer creem la instància de fitxer
//Pot ser un fitxer que existeixi o no (al nostre disc)
//Si no existeix i volem llegir, no podrem, donarà error
//Compte:
//Si no existeix, no donarà error quan creem la instància de File,
//donarà error quan intentem llegir del fitxer (en crear el FileReader)
File fitxer = new File("output"); //Aquí no dóna mai error

//Creem el FileReader
//Si el fitxer no existeix, aquí sí dóna error
FileReader fr = new FileReader(fitxer); 

//El mètode read() llegeix caràcter a caràcter, però retorna un int
//és el int corresponent al caràcter llegit segons a taula ASCII
//Com que llegeix caràcter a caràcter, cal fer un bucle
//El bucle acaba quan read() ja no pot llegir més caràcters (s'han acabat)
//Quan els caràcters s'han acabat, read() retorna -1

int c = fr.read(); //llegeix el primer caràcter del fitxer

//En aquest String guardarem el resultat de concatenar tots els caràcters
String contents = "";

while(c != -1) {
  //Cal fer cast de int a char i concatenem per formar l'String final
  contents += (char)c; 
  c = fr.read(); //llegim el següent caràcter
}


System.out.println(contents); //Imprimirà "This file has been filled from Java"

//Finalment, cal tancar el FileReader
fr.close();
```

**Com evitar que dóni error de lectura quan el fitxer no existeix?**

`FileReader` té un mètode que retorna _true_ si el fitxer existeix i és, efectivament, un fitxer. Què vol dir això? Amb la classe `File` també es poden representar _directoris_ (carpetes). Per tant, cal assegurar-nos que la instància de File representa un fitxer (i no un directori) i que aquest fitxer existeix. El mètode és `isFile()` i el podem verure en el següent exemple:

```java
File fitxer = new File("output"); //Aquí no dóna mai error

if(fitxer.isFile()){
   FileReader fr = new FileReader(fitxer); 

   String contents = "";

   int c = fr.read();

   while(c != -1) {
      contents += (char)c; 
      c = fr.read(); 
   }
   fr.close();
   System.out.println(contents);
}

```

**Millorem el nostre codi?** Podem millorar l'anterior codi si reescribim de la següent manera el bucle `while`:

```java
File fitxer = new File("output"); //Aquí no dóna mai error

if(fitxer.isFile()){
   FileReader fr = new FileReader(fitxer); 

   String contents = "";

   int c;

   while((c = fr.read()) != -1) {
      contents += (char)c; 
   }
   fr.close();
   System.out.println(contents);
}

```
En aquest cas, `while((c = fr.read()) != -1)` s'executa de la següent manera: primer fa el fr.read(), després assigna el resultat del fr.read() a la variable _c_, a continuació compara el valor que hi ha a la variable _c_ amb -1 (compara si són valors diferents) i finalment retorna un booleà com a resultat de la comparació. Aquest és el booleà que fa servir el while per a decidir si continua el bucle o s'atura.

**Podem encara millorar més el nostre codi?** Sí. En Java, la concatenació d'String gasta molta memòria. És a dir, en el nostra codi anterior, quan dins del bucle fem `contents += (char)c;` aquesta és una operació molt poc eficient, molt cara en recursos i no recomanable. Es pot fer si concatenem pocs i petits String, però en llegir d'un fitxer potser hi ha **molts** caràcters a concatenar. Cal buscar una altra solució.

**Solució** com a alternativa a la concatenació d'String: **usar la classe StringBuilder**. Com molt be diu el seu nom, la classe `StringBuilder` serveix per a _construir_ un String. Vegem-ho amb un exemple:

```java
    static String readFromFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        StringBuilder sb = new StringBuilder();
        int c;
        while((c = fr.read()) != -1){
            sb.append((char)c);
        }
        return sb.toString();
    }
```

**Altres problemes quan treballem amb fitxers:** A vegades passa que, malgrat el fitxer existeix, hi ha un error de disc (el disc pot tenir sectors corruptes) que fa que no puguem llegir correctament el fitxer. O a vegades, si el fitxer es troba ubicat en un NAS, un error de xarxa pot fer que no estigui disponible temporalment. Aquests tipus d'errors s'anomenen errors d'**Entrada i de Sortida** (en anglès, errors d'Input/Output, o IO). El mecanisme que té Java, i la resta de llenguatges de programació, per a tracta aquests errors (i d'altres) és a través de les **Excepcions**. Per això veureu que a la signatura de la funció s'hi afageix `throws IOException`. Les excepcions es veuran amb detall més endavant, de moment no cal conèixer com funcionen.

### Segona solució: les classes BufferedReader i BufferedWriter

**Quan el texte que volem llegir o escriure és gran** llavors no és eficient usar les classes `FileReader` (per a llegir **directament** des d'un fitxer) o `FileWriter` (per a escriure **directament** en un fitxer). Per a evitar problemes, cal usar unes classes addicionals anomenades `BufferedReader` i `BufferedWriter`. És a dir, com que `FileReader` i `FileWriter` no estan preparades per a treballar amb grans quantitats de text, necessitaran l'ajuda de `BufferedReader` i `BufferedWriter`, que són classes que actuen com una memòria _buffer_ i per tant fan més eficient la lectura i escriptura de grans quantitats de text.

Veiem un exemple d'escriptura, i un exemple de lectura:

```java
//Exemple d'escriptura a fitxer
    static void writeToFileWithBuffer(File file, String message) throws IOException{

        FileWriter fw = new FileWriter(file, true);

        BufferedWriter bufw = new BufferedWriter(fw);

        bufw.write(message);

        bufw.close();
    }
```

Com podeu veure, li passem la instància de `FileWriter` al `BufferedWriter`. Diem que `BufferedWriter` fa de _wrapper_ (embolcall) de `FileWriter`, afegint noves característiques a `FileWriter` (en aquest cas, la capacitat d'usar una memòria _buffer_ per a millorar l'eficiencia de l'escriptura de texts grans). 

```java
    static String readFromFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bufr = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = bufr.readLine()) != null){
            sb.append(line);
        }
        bufr.close();
        return sb.toString();
    }
```
En aquest cas, el `BufferedReader` ens proporciona un nou mètode (no disponible al `FileReader`) que és el `ReadLine()` i que ens permet llegir una línia del fitxer, retornant un String, o _null_ si no hi ha més línies a llegir.


# UF3 - Exercici Pràctic 0 - Enunciat

En la solució _innocent_ (que us he adjuntat en aquest enunciat a la carpeta _students_) de l'anterior UF (que imprimia llistes ordenades d'estudiants), amb el mètode `init()` es creava un array d'estudiants donant valors a les diferents propietats de cada instància de la classe `Student` (instàncies que s'assignaven a les diferents posicions de l'array, fins a omplenar-lo). Com aquests valors estan incorporats dins del codi del programa, en el mètode `init()`, diem que són valors _fixes_ en el sentit que cada cop que s'executa el programa, no canvïen. I per tant, els continguts de l'array `Student[] students` són sempre els mateixos.

Teniu el codi a la carpeta _students_, per si voleu fer un cop d'ull al mètode `init()` (que està a la classe `App`).

### Exercici 0 (40%)

Teniu el codi de partida a la carpeta _tui_lliurament_. Heu de substituir el mètode `init()` pel mètode `initTui()`. Des del mètode psvm cridarem `initTui()` enlloc de cridar `init()`.

En aquest cas he eliminat la classe _Arrays_ per fer el codi més simple. Per tant, el codi que trobareu a la carpeta _tui_lliurament_ només imprimirà la llista desordenada d'estudiants, tal qual està a l'array d'Student. No imprimirà les llistes ordenades dels estudiants tal com feia a l'anterior UF (i tal com teniu a la carpeta _students_).

El mètode `initTui()` (**que heu d'implementar**) preguntarà primer a l'usuari quants estudiants vol afegir a l'array d'estudiants, i després preguntarà a l'usuari el nom, l'edat i el número d'assignatures matriculades per a cadascun dels estudiants.

Heu d'usar la classe `Scanner` i `System.in` de manera similar als exemples explicats abans en la secció de conceptes previs.

El resultat (a banda d'imprimir la llista dels estudiants) ha de ser el següent. Amb això, crearem l'array d'estudiants amb les dades introduïdes des del teclat:

```
Number of students: 2
Number of students is: 2
---- Student number 1 ----
Enter name: Joan
Enter age: 22
Enter nº enrolled subjects: 4
---- Student number 2 ----
Enter name: Laura
Enter age: 29
Enter nº enrolled subjects: 6

```
Aquest és només un exemple. Vosaltres anireu omplint els estudiants amb els valors que vulgueu.


### Exercici 1 (35%)

En aquest exercici heu de completar el programa que trobareu a la carpeta _files_lliurament_ de manera que, a banda d'imprimir per pantalla la llista dels alumnes, també haurà de guardar la llista en un fitxer, que s'anomenarà `students.out`, i després haurà de llegir el contingut del fitxer, que també haurà d'imprimir per pantalla.

Per a guardar aquesta informació en un fitxer, heu d'usar les classes **FileWriter, FileReader** tal com s'expliquen en els conceptes previs d'aquesta pràctica, **no** heu d'usar les classes **BufferedWriter i BufferedReader**.

### Exercici 2 (25%)

En aquest exercici heu de completar el codi que falta en el programa que trobareu a la carpeta _files_lliurament2_, però ara usant també  **BufferedWriter i BufferedReader**.

