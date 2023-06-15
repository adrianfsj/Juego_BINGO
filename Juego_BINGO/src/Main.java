
import java.util.*;

public class Main {


    public static void main(String[] args) {
    	//Vamos con el codigo del juego
    	
    	
    	
        Scanner input=new Scanner(System.in);

        int cartones[][] ,copiaCartones[][],  numCarton, bolas[] = new int [50], numeroLineas[];

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" JUGUEMOS AL  BINGO  ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

       
         //Primero vamos a dejar que el jugador decida el numero de cartones con los que quiere jugar
         

        System.out.print("Introduce el numero de cartones con los que quieres jugar, cada uno vale 500 monedas: ");
        System.out.println();
        numCarton = input.nextInt();

        while(numCarton <= 1)
        {
            
            System.out.println("Introduce un nuevo número, debe ser 2 o mayor");
            numCarton = input.nextInt();
        }
        
        
        //Creamos los cartones vacios
        cartones = new int[numCarton][15];
        copiaCartones = new int[numCarton][15];
        numeroLineas = new int[numCarton];
        
        fillArray(cartones);
        copyArray(cartones, copiaCartones);

        for (int i = 0; i < cartones.length; i++) {
            System.out.println("Carton " + i);
            printArray(i, cartones);
        }

        throwBall(bolas,copiaCartones,cartones,numeroLineas);
    }

    
    
    
    //Numeros aleatorios para luego rellenar los cartones al azar
    static int random (int num){
        num = ((int) (1+Math.random() * 50));
        return num;
    }

    //Se ponen numeros aleatorios en los cartones
    static void fillArray(int cartones[][]) {
        int num = 0;

        boolean check = false;

    //Para comprobar la repetiicon de numeros
        for (int i = 0; i < cartones.length ; i++) {
            for (int j = 0; j < cartones[i].length ; j++) {
                do {
                    num = random(num);
                    check = checkNumber(cartones,num,i);
                }while (check == true);
                cartones[i][j] = num;
            }
            Arrays.sort(cartones[i]);
        }
    }


    
     //Copiamos los cartones en otro array llamado copiaCartones para que
     //posteriormente, cada bola que salga, si esta en el carton, se reste y se ponga a 0.
     
    static void copyArray (int cartones[][], int copiaCartones[][]){
        for (int i = 0; i < cartones.length ; i++) {
            for (int j = 0; j < cartones[0].length ; j++) {
                copiaCartones[i][j] = cartones[i][j];
            }
        }
    }

    
    
    
    
    //Para darle forma a los cartones
    
    static void printArray(int i, int arrayImprimir[][]) {

        for (int j = 0; j < arrayImprimir[0].length; j++) {
            System.out.print(arrayImprimir[i][j] + " ");
            if ((j + 1) % 10 == 0 || (j + 1) % 10 == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }
    
    
    

    
     // Comprueba que el numero generado de forma aleatoria no se repita en el carton.
     //
     // carton  contiene el nº de cartones y los numeros en el.
     // num es la variable del numero del carton que se va a añadir al carton.
     // nCarton  para recorrer el carton
     // devuelve el booleano para que se sepa que el numero esta repetido en el carton y lo descarte.
    
    static boolean checkNumber (int carton[][],int num,int nCarton){
    	

        boolean repetido = false;

        
        for (int i = 0; i < carton[nCarton].length ; i++) {
            if (num == carton[nCarton][i]) {
                repetido = true;
            }
        }
        return repetido;
    }

    
     // Ahora el codigo para sacar bolas del bingo, aleatorias y no se pueden repetir
     //
     // Guardamos las bolas que han salido y el numero de bolas
      
     
     //  arraybolas es el array donde estan guardados los numeros de las bolas.
     //  arrayCopiaCarton es la copia del carton/es del array original.
       
     //  numeroLineas es la variable utilizada para comprobar el numero de lineas comprobadas que hay en el carton.
     

    static void throwBall (int arraybolas [],int arrayCopiaCarton[][], int cartones[][],int numeroLineas[]){
        boolean checkBola = false, bingo = false, linea = false;
        int num = 0,contador = 0, contadorLinea = 0;

        do {
            for (int i = 0 ; i < arraybolas.length ; i++) {

                do {
                    num = random(num);
                    checkBola = checkBall(arraybolas, num);
                } while (checkBola == true);
                arraybolas[i] = num;
                contador++;
                System.out.println();
                System.out.println("Sale la bola " + num);
                System.out.println();
                restaNumeroCopiaArray(num, arrayCopiaCarton);
                for (int j = 0; j < cartones.length; j++) {
                    linea = checkLinea(j, arrayCopiaCarton, cartones, numeroLineas);
                    if (linea == true) {
                        numeroLineas[j] += 1;
                    }
                    if (numeroLineas[j] > 2){
                        bingo = checkBingo(j,arrayCopiaCarton,cartones);
                        if (bingo == true){
                            i = arraybolas.length - 1;
                            j = cartones.length - 1;
                        }
                    }
                }
            }
        }while (bingo == false);
        System.out.println();
        System.out.println();
        System.out.println("Estas son las " + contador + " bolas que se han sacado:");
        System.out.println();
        for (int i = 0; i < arraybolas.length ; i++) {
            if (arraybolas[i] != 0) {
                System.out.printf("%3d", arraybolas[i]);
                if ((i + 1) % 10 == 0) {
                    System.out.println();
                }
            }
        }
        System.out.println("         FIN DEL JUEGO");
    }

    
     // De nuevo comprobamos la repeticion, ahora probamos si la bola es repetida o no, si se repite se anula esa 
     // tirada y se saca otra 
     //  cheakArray variable utilizada para comprobar el array que se llame en la funcion.
     //  num es el numero que se va a comprobar.
     //  devuelve el booleano que comprueba si la bola esta repetida u no.
  
    static boolean checkBall (int cheakArray [], int num){
        boolean repetido = false;

        for (int i = 0; i <cheakArray.length ; i++) {
            if (num == cheakArray[i]) {
                repetido = true;
            }
        }
        return repetido;
    }

    
     // Ahora hay que comprobar cuando hay lineas y cuando hay bingo en los cartones que se han elegido jugar
     
    static void restaNumeroCopiaArray (int numeroBola, int copiaCarton[][]) {
        boolean linea = false;

        for (int i = 0; i < copiaCarton.length; i++) {
            for (int j = 0; j < copiaCarton[i].length; j++) {
                if (numeroBola == copiaCarton[i][j]) {
                    copiaCarton[i][j] = 0;
                }
            }
        }
    }

    
     // Esta funcion nos prueba si hay bingo.
     // Para ello, comprobamos si la suma de los numeros del carton = 1500 y si lo es, es que hay bingo.
    
    static boolean checkBingo(int i, int copiaCarton[][], int cartones[][]) {
        boolean bingo = false;
        int controlador = 0;

        for (int j = 0; j < copiaCarton[i].length; j++) {
            controlador = controlador + copiaCarton[i][j];
        }

        if (controlador == 1500) {
            bingo = true;
            System.out.println();
            System.out.printf("Enhorabuena, el carton " + i + " tiene bingo ");
            System.out.println();
            System.out.println();
            printArray(i,cartones);
        }
        return bingo;
    }

    
     
     // Ahora comprobamos si hay lineas
     // Para ello, comprobamos cada 5 numeros si la suma de ellos es = 0 y si el resultado es 0 es que hay linea.
      
     // Ponemos los numeros de la linea a 100 para que la linea no se vuelva a comprobar. Aumentamos en el array
     // numeroLineas +1 en la posicion que nos marque la variable i para que cuando sea mayor o igual que 2 no cante
     // la linea y cante directamente el bingo.
     
      
      //numeroLineas variable utilizada para comprobar el numero de lineas de los cartones y si son el ganador.
      //la funcion devuelve la comprobacion de las lineas para ver si el carton comprobado hace linea u bingo.
     
    static boolean checkLinea (int i,int copiaCartones[][], int cartones[][],int numeroLineas[]) {
        boolean checkLinea = false;
        int linea = 0;


        linea = copiaCartones[i][0] + copiaCartones[i][1] + copiaCartones[i][2] + copiaCartones[i][3] + copiaCartones[i][4];
        if (linea == 0) {
            checkLinea = true;
            copiaCartones[i][0] = 100;
            copiaCartones[i][1] = 100;
            copiaCartones[i][2] = 100;
            copiaCartones[i][3] = 100;
            copiaCartones[i][4] = 100;

        } else {
            linea = copiaCartones[i][5] + copiaCartones[i][6] + copiaCartones[i][7] + copiaCartones[i][8] + copiaCartones[i][9];
            if (linea == 0) {
                checkLinea = true;
                copiaCartones[i][5] = 100;
                copiaCartones[i][6] = 100;
                copiaCartones[i][7] = 100;
                copiaCartones[i][8] = 100;
                copiaCartones[i][9] = 100;

            } else {
                linea = copiaCartones[i][10] + copiaCartones[i][11] + copiaCartones[i][12] + copiaCartones[i][13] + copiaCartones[i][14];
                if (linea == 0) {
                    checkLinea = true;
                    copiaCartones[i][10] = 100;
                    copiaCartones[i][11] = 100;
                    copiaCartones[i][12] = 100;
                    copiaCartones[i][13] = 100;
                    copiaCartones[i][14] = 100;

                }
            }
        }
        if (numeroLineas[i] < 2) {
            if (checkLinea == true) {
                System.out.println();
                System.out.println("Hay linea en el carton " + i);

                printArray(i,cartones);

                System.out.println();
            }
        }
        return checkLinea;
        
    }
    
}
    