import pl.edu.uj.javaframe.*;

public class Main {
    public static void main(String[] args) {

        ImaginaryInt im1 = (ImaginaryInt) new ImaginaryInt().create("10i4");
        ImaginaryInt im2 = (ImaginaryInt) new ImaginaryInt().create("3i5");
        ImaginaryInt im5 = (ImaginaryInt) new  ImaginaryInt().create(("10i4"));
        ImaginaryDouble im3 = (ImaginaryDouble) new ImaginaryDouble().create("7i1");
        ImaginaryDouble im4 = (ImaginaryDouble) new ImaginaryDouble().create("6");
        ImaginaryDouble im6 = (ImaginaryDouble) new  ImaginaryDouble().create("3i5");
        Int i1 = (Int) new Int().create("9");
        Int i2 = (Int) new Int().create("2");
        MyDouble d1 = (MyDouble) new MyDouble().create("5");
        MyDouble d2 = (MyDouble) new MyDouble().create("7");

        im1.add(im2).print(); System.out.print("  ");
        im1.add(im3).print(); System.out.print("  ");
        im3.add(im4).print();System.out.print("  ");
        im3.add(i1).print(); System.out.print("  ");
        d1.add(d2).print(); System.out.println();

        im1.sub(im3).print(); System.out.print("  ");
        im4.sub(d2).print(); System.out.print("  ");
        d2.sub(d1).print(); System.out.println();

        im1.mul(im2).print(); System.out.print("  ");
        im2.mul(im4).print(); System.out.print("  ");
        im3.mul(i2).print(); System.out.println();

        im1.div(im2).print(); System.out.print("  ");
        im3.div(im4).print(); System.out.print("  ");
        im4.div(im3).print(); System.out.print("  ");
        i1.div(i2).print(); System.out.println();

        im1.pow(im2).print(); System.out.print("  ");
        im1.pow(d1).print(); System.out.print("  ");
        im3.pow(i1).print(); System.out.print("  ");
        i1.pow(i2).print(); System.out.println();

        System.out.print(im6.eq(im2) + "  ");
        System.out.print(im2.eq(im3) + "  "); System.out.println();

        System.out.println(i2.lte(i1));
        System.out.println(d1.gte(d2));

        System.out.print(im6.neq(im2) + "  ");
        System.out.print(im2.neq(im3)); System.out.println();

        System.out.print(im1.equals(im5) + "  ");
        System.out.println(im2.equals(im5));

        System.out.println(im1.hashCode());
    }
}