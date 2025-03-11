package pl.edu.uj.javaframe;

public class ImaginaryInt extends Int {
    private int imaginaryPart;

    @Override
    public Value create(String val) {
        String[] numParts = val.split("i");
        this.value = Integer.parseInt(numParts[0]);
        if(numParts.length > 1) {
            this.imaginaryPart = Integer.parseInt(numParts[1]);
        }
        else {
            this.imaginaryPart = 0;
        }

        return this;
    }

    @Override
    public Value add(Value v) {
        ImaginaryInt result = new ImaginaryInt();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Integer) this.value + (Integer) other.value;
            result.imaginaryPart = this.imaginaryPart + other.imaginaryPart;
        }
        else if(v instanceof  ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Integer) this.value + ((Double) other.value).intValue();
            result.imaginaryPart = this.imaginaryPart + (int) other.getImaginaryPart();
        }
        else if(v instanceof Int) {
            result.value = (Integer) this.value + (Integer) v.value;
            result.imaginaryPart = this.imaginaryPart;
        }
        else if(v instanceof MyDouble) {
            result.value = (int) ((Integer) this.value + (Double) v.value);
            result.imaginaryPart = this.imaginaryPart;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.value + "i" + imaginaryPart;
    }

    public int getImaginaryPart() {
        return imaginaryPart;
    }
}
