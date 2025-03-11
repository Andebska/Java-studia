package pl.edu.uj.javaframe;

public class Int extends Value{
    @Override
    public Value create(String val) {
        Int v = new Int();
        v.value = Integer.parseInt(val);
        return v;
    }

    @Override
    public Value add(Value v) {
        Int result  = new Int();
        if(v instanceof Int){
            result.value = (Integer)this.value + (Integer)v.value;
        }
        else if(v instanceof MyDouble) {
            result.value = (Integer)this.value + Double.valueOf(v.value.toString()).intValue();
        }
        else if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Integer)this.value + (Integer)other.value;
        }
        else if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Integer)this.value + ((Double)other.value).intValue();
        }
        return result;
    }

    @Override
    public Value sub(Value v){
        Int result  = new Int();
        if(v instanceof ImaginaryInt) {
            result.value = (Integer)this.value - (Integer)v.value;
        }
        else if(v instanceof ImaginaryDouble) {
            result.value = (Integer)this.value - ((Double)v.value).intValue();
        }
        else if(v instanceof Int){
            result.value = (Integer)this.value - (Integer)v.value;
        }
        else if(v instanceof MyDouble) {
            result.value = (Integer)this.value - ((Double)v.value).intValue();
        }
        return result;
    }

    @Override
    public Value mul(Value v){
        Int result  = new Int();
        if(v instanceof ImaginaryInt) {
            result.value = (Integer)this.value * (Integer)v.value;
        }
        else if(v instanceof ImaginaryDouble) {
            result.value = (Integer)this.value * ((Double)v.value).intValue();
        }
        else if(v instanceof Int){
            result.value = (Integer)this.value * (Integer)v.value;
        }
        else if(v instanceof MyDouble) {
            result.value = (Integer)this.value * ((Double)v.value).intValue();
        }
        return result;
    }

    @Override
    public Value div(Value v){
        Int result  = new Int();
        if(v instanceof ImaginaryInt) {
            if((Integer)v.value == 0) throw new ArithmeticException("Division by zero");
            result.value = (Integer)this.value / (Integer)v.value;
        }
        else if(v instanceof ImaginaryDouble) {
            if((Double)v.value == 0.0) throw new ArithmeticException("Division by zero");
            result.value =  (int) Math.round((Integer)this.value / (Double)v.value);
        }
        else if(v instanceof Int){
            if((Integer)v.value == 0) throw new ArithmeticException("Division by zero");
            result.value = (Integer)this.value / (Integer)v.value;
        }
        else if(v instanceof MyDouble) {
            if((Double)v.value == 0.0) throw new ArithmeticException("Division by zero");
            result.value = (Integer)this.value / ((Double)v.value).intValue();
        }
        return result;
    }

    @Override
    public Value pow(Value v){
        Int result = new Int();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            double r = Math.abs((Integer) this.value);
            double theta = Math.atan2(0, (Integer) this.value);
            double rAfter = Math.pow(r, (Integer)other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Integer)other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = (int) Math.round(rAfter * Math.cos(thetaAfter));
        }
        else if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            double r = Math.abs((Integer) this.value);
            double theta = Math.atan2(0, (Integer) this.value);
            double rAfter = Math.pow(r, (Double)other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Double)other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = (int) Math.round(rAfter * Math.cos(thetaAfter));
        }
        else if(v instanceof Int) {
            result.value = (int) Math.pow((Integer)this.value, (Integer) v.value);
        }
        else if(v instanceof MyDouble) {
            result.value = (int) Math.pow((Integer)this.value, (Double) v.value);
        }
        return result;
    }

    @Override
    public boolean eq(Value v){
        if(v instanceof ImaginaryInt other) {
            return this.value.equals(other.value) && other.getImaginaryPart() == 0;
        }
        else if(v instanceof ImaginaryDouble other) {
            return (this.value).equals(((Double)other.value).intValue()) && other.getImaginaryPart() == 0.0;
        }
        else if(v instanceof Int) {
            return this.value.equals(v.value);
        }
        else if(v instanceof MyDouble) {
            return ((Double)this.value).equals(v.value);
        }
        return false;
    }

    @Override
    public boolean lte(Value v){
        if (v instanceof Int) {
            return (Integer) this.value <= (Integer) v.value;
        }
        else if (v instanceof MyDouble) {
            return (Double) this.value <= (Double) v.value;
        }
        return false;
    }

    @Override
    public boolean gte(Value v){
        if (v instanceof Int) {
            return (Integer) this.value >= (Integer) v.value;
        }
        else if (v instanceof MyDouble) {
            return (Double) this.value >= (Double) v.value;
        }
        return false;
    }

    @Override
    public boolean neq(Value v){
        return !eq(v);
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Int otherObj = (Int) other;
        return this.value.equals(otherObj.value);
    }

    @Override
    public int hashCode(){
        return 31 * value.hashCode();
    }
}
