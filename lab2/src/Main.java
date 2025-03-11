import java.util.HashSet;

import java.io.IOException;
import java.util.function.Consumer;
import pl.edu.uj.javaframe.*;

public class Main {

    public static void main(String[] args) {

//      INSTRUKCJE DO ĆWICZEŃ I
//
//      Dopisz metodę print w klasie DataFrame wypisującą zawartość dataframe
//      df.print();
//
//      Dodaj klasę ImaginaryDouble oraz ImaginaryInt, które dziedziczą po odpowiednio klasach Int i Double . Powinne one być tworzone ze Stringów postaci "<CzęśćRzeczywissta>i<CzęśćUrojona>", np "12i3".
//
//      Nadpisz metody create i add w klasach ImaginaryDoubgle oraz ImaginaryInt. Pamiętaj, że do liczb urojonych możemy też dodawać inne typy (np. Int, Double).
//      Przetestuj wyniki operacji:
//                ImaginaryInt.create("12i4").add(Int.create("10"))
//                Int.create("10").add(ImaginaryInt.create("12i4")))
//                ImaginaryInt.create("12i4").add(ImaginaryInt.create("10i10"))
//
//      Nadpisz metodę public String toString() w kasach ImaginaryDouble i ImaginaryInt, tak aby poprawnie wyświetlała liczby urojone do postaci Stringa w formacie wskazanym wcześniej.
//
//      Poniżej Przykładowy kod, któ©y powinien działać po dopisaniu odpowiednich klas:


         MyDataFrame df = new MyDataFrame(new Class[] {ImaginaryDouble.class, Int.class}, new String[] {"kol1","kol2"});

         df.addRow(new String[]{"12i3","23"});
         df.addRow(new String[]{"1244i4","23"});
         df.addRow(new String[]{"1244i6","2553"});
         df.addRow(new String[]{"12i9","2300"});

         ImaginaryDouble im1 = (ImaginaryDouble) new ImaginaryDouble().create("12i3");
         ImaginaryDouble im2 = (ImaginaryDouble) new ImaginaryDouble().create("5i3");
         ImaginaryDouble im3 = (ImaginaryDouble) new ImaginaryDouble().create("5");
         MyDouble re = (MyDouble) new MyDouble().create("5");

         im1.print();
         System.out.println();
         im2.print();
         System.out.println();
         re.add(im2).print();
         System.out.println();
         System.out.println("------------------------------");
         df.print();



    }


}
