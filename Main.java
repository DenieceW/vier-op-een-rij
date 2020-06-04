package com.vieropeenrij;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        char[][] speelrek = new char[7][7];

        //initieren/invullen van het speelrek
        for (int rij = 0; rij < speelrek.length; rij++){
            for(int kolom = 0; kolom < speelrek.length; kolom++){
                speelrek[rij][kolom] = ' ';
            }
        }

        int input; //voor het omzetten van de letters naar nummers zodat de steen in het juiste kolom wordt geworpen

       //random bepalen welke speler begint
        char spelersKleur;
        int random = (int) ( Math.random() * 2 + 1);
        if(random == 1){
            spelersKleur = 'R';
        }else
            spelersKleur = 'G';

        label:
        while (true) {

            System.out.print("Kan speler " + spelersKleur + " aangeven in welke kolom zij haar steen wilt gooien: ");
            String userInput = reader.nextLine().toLowerCase();

//omzetten van letters naar nummers zodat het in het juiste kolom wordt geworpen
                switch (userInput) {
                    case "a":
                        input = 0;
                        break;
                    case "b":
                        input = 1;
                        break;
                    case "c":
                        input = 2;
                        break;
                    case "d":
                        input = 3;
                        break;
                    case "e":
                        input = 4;
                        break;
                    case "f":
                        input = 5;
                        break;
                    case "g":
                        input = 6;
                        break;
                    default:
                        break label;
                }

                if(inworpSpeler(speelrek,input,spelersKleur)){
                    if(verticaalVierOpEenRij(speelrek)) {
                        displaySpeelrek(speelrek);
                        System.out.println(spelersKleur + " is de winnaar");
                        break;
                    }
                    if (horizontaalVierOpEenRij(speelrek)){
                        displaySpeelrek(speelrek);
                        System.out.println(spelersKleur + " is de winnaar");
                        break;
                        }

                }
            displaySpeelrek(speelrek);
            spelersKleur = wisselSpeler(spelersKleur);
        }
    }

    public static void displaySpeelrek(char[][] speelrek){
        //voor printen van de eerste rij letters
        for (int rij = 0; rij < 1; rij++) {
            for(int kolom = 0; kolom < speelrek.length; kolom++){
                System.out.print((char)('A' + kolom));
                System.out.print(" ");
            }
        }
        //voor printen van de kolommen en rijen
        for (int rij = 1; rij < speelrek.length; rij++){
            System.out.println("");
            for (int kolom = 0; kolom < speelrek[rij].length; kolom++){
                System.out.print("" + speelrek[rij][kolom] + "|"); //dit zorgt ervoor dat de grid ingevuld kan worden
            }
            //printen van de nummers
           System.out.print((speelrek.length) - rij);
        }
        System.out.println("");
        System.out.print("- - - - - - -");
        System.out.println("");
    }

    public static boolean inworpSpeler(char[][] speelrek, int inputKolom, char speler){
        boolean worp = false;

        //checken of kolom al vol zit
        if(speelrek[1][inputKolom] != ' '){
            System.out.println("Deze kolom is al vol!");
            return false;
        }

        //hier wordt de worp van de speler in het speelrek gezet als kolom niet vol is
        for (int rij = speelrek.length-1; rij >= 0; rij--) {
            if(speelrek[rij][inputKolom] == ' '){
                speelrek[rij][inputKolom] = speler;
                return true;
            }
        }
        return worp;
    }

    public static char wisselSpeler(char kleurSpeler){
        if(kleurSpeler == 'R'){
            kleurSpeler = 'G';
        } else {
            kleurSpeler = 'R';
        }
        return kleurSpeler;
    }


    public static boolean horizontaalVierOpEenRij(char[][] speelRek){
        boolean winnaar = false;
        for(int rij = 1; rij < speelRek.length; rij++){
            for(int kolom = 0; kolom < speelRek[rij].length -3; kolom++){
                if(speelRek[rij][kolom] != ' '  && speelRek[rij][kolom] == speelRek[rij][kolom + 1] &&
                        speelRek[rij][kolom] == speelRek[rij][kolom + 2] && speelRek[rij][kolom] == speelRek[rij][kolom + 3]){
                    System.out.println("Je hebt vier op een rij!");
                    return true;
                }
            }
        }
        return winnaar;
    }

    public static boolean verticaalVierOpEenRij(char[][] speelRek){
        boolean winnaar = false;
        for(int rij = 1; rij < speelRek.length -3; rij++){
            for(int kolom = 0; kolom < speelRek[rij].length; kolom++){
                if(speelRek[rij][kolom] != ' '  && speelRek[rij][kolom] == speelRek[rij + 1][kolom] &&
                        speelRek[rij][kolom] == speelRek[rij + 2][kolom] && speelRek[rij][kolom] == speelRek[rij + 3][kolom]){
                    System.out.println("Je hebt vier op een rij!");
                    return true;
                }
            }
        }
        return winnaar;
    }
}
