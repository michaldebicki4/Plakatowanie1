package com.michaldebicki;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        int n,k;
        Element[] list;
        Blok[] input;

        int result = 0;



        try {
            InputStream inputStream = new FileInputStream("pla10a.in");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());  //n- ilość budynków
            //System.out.println(n);
            input = new Blok[n]; // Blok - tablica do porównywania wysokości?
            list = new Element[n + 2]; // Element to taka że jest 2 więcej żeby się nie wypierdalała tablica

            //tworzenie tablicy z wartościami
            for (int i = 0; i < n; ++i) {
                line = br.readLine();
                st = new StringTokenizer(line);
                st.nextToken();
                input[i] = new Blok(); // tablica ze wszystkimi danymi wejściowymi
                input[i].h = Integer.parseInt(st.nextToken()); //wybieramy tylko wysokości

                //System.out.println("Wysokości : " + input[i].h);

                input[i].liczbaBlokow = i + 1;

                //System.out.println("Ilość wartości: " + input[i].liczbaBlokow);
            }
            for (int i = 0; i <= n+1; ++i) {
                list[i] = new Element(); //dla każdego miejsca w tabeli robimy nowy element
            }
            list[0].lewaKrawedz=0; //ustawiamy brzegowe żeby tablica się nie wywalała
            list[0].prawaKrawedz=1;
            list[0].wartosc=(-1);
            for (int i = 1; i <= n; ++i){
                list[i].lewaKrawedz=i-1;
                list[i].prawaKrawedz=i+1;
                list[i].wartosc=input[i-1].h;
            }
            list[n+1].lewaKrawedz=n;
            list[n+1].prawaKrawedz=n+1;
            list[n+1].wartosc=(-1);

//            for (int i = 0; i <= n+1; i++) {
//                //System.out.println(list[i].wartosc);
//                //System.out.println(list[i].lewaKrawedz);
//                //System.out.println(list[i].prawaKrawedz);
//            }
            Arrays.sort(input);
//            for (int i = 0; i < n; i++) {
//                //System.out.println("Ilość wartości: " + input[i].liczbaBlokow);
//                //System.out.println("Wysokości : " + input[i].h);
//            }
            for (int i = 0; i < n ; ++i){
                k = input[i].liczbaBlokow;
//                System.out.println("list"+ k +".val : " + list[k].wartosc);
//                System.out.println("nx_val(" + k+") : " + list[list[k].prawaKrawedz].wartosc);
//                System.out.println("----");
//                if (list[k].wartosc != nx_val(k)) result++;
//                del(k);
                if (list[k].wartosc != list[list[k].prawaKrawedz].wartosc) {
                    result++;
//                    System.out.println("usuwanko");
                }
                //System.out.println("----");
                list[list[k].prawaKrawedz].lewaKrawedz=list[k].lewaKrawedz;
                list[list[k].lewaKrawedz].prawaKrawedz=list[k].prawaKrawedz;
            }
            System.out.println(result);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    static class Element{
        public int wartosc;
        public int lewaKrawedz;
        public int prawaKrawedz;
    }

    static class Blok implements Comparable<Blok>{
        public int liczbaBlokow;   //liczba bloków
        public int h;              //wysokość wszystkich bloków

        public int compareTo(Blok b){
            if (h == b.h)
                return liczbaBlokow - b.liczbaBlokow;
            else
                return b.h - h;
        }
    }

//    void del(int g){
//        list[list[g].next].prev=list[g].prev;
//        list[list[g].prev].next=list[g].next;
//    }


}

