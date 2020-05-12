# m3-uf3-ep0

## Conceptes previs

### TUI (Terminal User Interface)

A Java podem preguntar a l'usuari que introdueixi valors per pantalla. L'usuari usarà el teclat. Un cop l'usuari ha introduit valors, els podem guardar en variables.

Això ho aconseguim amb la classe `Scanner` i `System.in`. `System.in` (entrada des del teclat) és el contrari de `System.out` (impresió per pantalla).

Hi ha diferents tipus de `Scanner`. Per a diferenciar un tipus d'`Scanner` d'un altre, en el moment d'instanciar un objecte amb `new` li haurem de passar com a argument algun valor que sigui vàlid. Per exemple, a continuació tenim dos tipus diferents de `Scanner`:

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
String name = in.nextLine();

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

**Compte**: si intenteu llegir un String que no és un int amb `nextInt()` us donarà error. Així mateix, us donarà error si intenteu convertir un String a int quan l'String no és un enter. Exemples:

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

# UF3 - Exercici Pràctic 0 - Enunciat

En la solució _innocent_ de l'anterior UF (que imprimia llistes ordenades d'estudiants), amb el mètode `init()` es creava un array d'estudiants donant valors a les diferents propietats de cada instància de la classe `Student` (instàncies que es s'assignaven a les diferents posicions de l'array, fins a omplenar-lo). Com aquests valors estan incorporats dins del codi del programa, en el mètode `init()`, diem que són valors _fixes_ en el sentit que cada cop que s'executa el programa, no canvïen. I per tant, els continguts de l'array `Student[] students` són sempre els mateixos.

Teniu el codi a la carpeta _innocent_, per si voleu fer un cop d'ull al mètode `init()` (que està a la classe `App`).

### Exercici 0 (40%)

Teniu el codi de partida a la carpeta _tui_. Heu de substituir el mètode `init()` pel mètode `initTui()`. Des del mètode psvm cridarem `initTui()` enlloc de cridar `init()`.

El mètode `initTui()` preguntarà primer a l'usuari quants estudiants vol afegir a l'array d'estudiants, i després preguntarà a l'usuari el nom, l'edat i el número d'assignatures matriculades per a cadascun dels estudiants.

Heu d'usar la classe `Scanner` i `System.in` de manera similar als exemples anteriors.

El resultat (a banda d'imprimir les llistes ordenades dels estudiants) ha de ser el següent. Amb això, crearem l'array d'estudiants amb les dades introduïdes des del teclat:

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


### Exercici 1 (60%)

En aquest exercici heu de modificar el programa resultant de l'exercici 0 anterior d'aquesta mateixa pràctica de manera que, a banda d'imprimir per pantalla les llistes dels alumnes, també haurà de guardar les llistes en un fitxer, que s'anomenarà students.out

És a dir, en acabar l'exercici 0 i també aquest exercici 1, el vostre programa haurà de:

* Preguntar per pantalla la informació necessària per a omplir l'array d'estudiants (cosa que ja heu fet a l'exercici 0). L'usuari entrarà aquesta informació per teclat.
* Imprimir per pantalla les llistes dels estudiants (això ja ho teniu fet en el codi que s'adjunta en aquest enunciat, i que és el codi que vau fer a la UF2).
* Guardar en un fitxer `students.out` les llistes d'estudiants tal qual surten impreses per pantalla (això és el que heu de fer de nou en aquest exercici).

Per a guardar aquesta informació en un fitxer, heu d'usar les classes FileWriter, FileReader, BufferedWriter i BufferedReader tal com s'expliquen en els conceptes previs d'aquesta pràctica.

