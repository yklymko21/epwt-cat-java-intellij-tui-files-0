package cat.ilg.m3.uf3;
import java.util.Scanner;
import static cat.ilg.m3.uf3.Utils.stringToInt;
public class App {

    public static void main(String[] args) {

        //Creem l'array d'estudiants:
        Student students[] = initTui();

        //Imprimeix tots els estudiants
        printStudents(students, "Llista desordenada:");


    }

    //Mètode que imprimeix la llista d'estudiants
    //Delega la creació de l'String al mètode
    //buildStudentListString
    static void printStudents(Student [] students, String message){
        System.out.println(buildStudentListString(students, message));
    }

    //Mètode que genera un String amb la llista d'estudiants
    static String buildStudentListString(Student [] students, String message){
        // StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("\n**** ");
        sb.append(message);
        sb.append("\n");
        for(Student s : students){
            sb.append(s.toString());
            sb.append("\n");
        }
        return sb.toString(); // Devolvemos el resultado
    }

    static public Student[] initTui() {

        //Inicialment suposem que no tenim cap estudiant
        Student students[] = new Student[0];

        //Ara, hem de preguntar quants estudiants volem tenir

        // Creamos Scanner inicial de preguntas
        Scanner in = new Scanner(System.in);
        System.out.print("Number of students: ");
        String numberOfStudentsStr = in.nextLine().trim();
        int numberOfStudents = stringToInt(numberOfStudentsStr);
        System.out.println("Number of students is: " + numberOfStudents);

        //Si el número d'estudiants és més gran que 0
        //preguntarem el nom, edat i subjects de cada estudiant

        // Siempre cuando sea mayor que 0 el numero pasado
        if(numberOfStudents > 0) {

            students = new Student[numberOfStudents];

            // Recorreremos el bucle sacando siempre preguntas
           for(int i = 0; i < numberOfStudents; i++){

               Student newStudent = new Student();

               // Nombre Estudiante
               System.out.println("El nombre de estudiante es: ");
               newStudent.name = in.nextLine();

               // Edad de estudiante
               System.out.println("La edad del estudiante es: ");
               newStudent.age = stringToInt(in.nextLine());

               // Assignaturas a las que ha suscrito
               System.out.println("A cuantas assignaturas se ha inscrito?");
               newStudent.amountOfEnrolledSubjects = stringToInt(in.nextLine());

               // Datos que añade al Array de Estudents
               students[i] = newStudent;
            }
        }
        in.close();
        return students; // Devolvemos el Array de Students
    }
}

