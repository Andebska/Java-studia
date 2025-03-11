package pl.edu.uj.javaframe;

public class ImaginaryDouble extends MyDouble{
    private double imaginaryPart;

    @Override
    public Value create(String val) {
        String[] numParts = val.split("i");
        this.value = Double.parseDouble(numParts[0]);
        if(numParts.length > 1) {
            this.imaginaryPart = Double.parseDouble(numParts[1]);
        }
        else {
            this.imaginaryPart = 0.0;
        }

        return this;
    }

    @Override
    public Value add(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();
        if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Double) this.value + (Double) other.value;
            result.imaginaryPart = this.imaginaryPart + other.imaginaryPart;
        }
        else if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Double) this.value + ((Integer) other.value).doubleValue();
            result.imaginaryPart = this.imaginaryPart + other.getImaginaryPart();
        }
        else if(v instanceof MyDouble) {
            result.value = (Double) this.value + (Double) v.value;
            result.imaginaryPart = this.imaginaryPart;
        }
        else if(v instanceof Int) {
            result.value = (Double) this.value + ((Integer) v.value).doubleValue();
            result.imaginaryPart = this.imaginaryPart;
        }
        return result;
    }

    @Override
    public String toString() {
        return value + "i" + imaginaryPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }
}
