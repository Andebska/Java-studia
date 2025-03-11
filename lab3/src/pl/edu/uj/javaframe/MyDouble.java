package pl.edu.uj.javaframe;

public class MyDouble extends Value {
    @Override
    public Value create(String val) {
        MyDouble v = new MyDouble();
        v.value = Double.parseDouble(val);
        return v;
    }

    @Override
    public Value add(Value v) {
        MyDouble result  = new MyDouble();
        if(v instanceof ImaginaryInt) {
            result.value = (Double) this.value + (Integer) v.value;
        }
        else if(v instanceof ImaginaryDouble) {
            result.value = (Double) this.value + (Double)v.value;
        }
        else if(v instanceof MyDouble) {
            result.value = (Double) this.value + Double.valueOf(v.value.toString());
        }
        else if(v instanceof Int) {
            result.value = (Double) this.value + (Integer) v.value;
        }
        return result;

    }

    @Override
    public Value sub(Value v){
        MyDouble result  = new MyDouble();
        if(v instanceof ImaginaryInt) {
            result.value = (Double) this.value - (Integer) v.value;
        }
        else if(v instanceof ImaginaryDouble) {
            result.value = (Double) this.value - (Double)v.value;
        }
        else if(v instanceof MyDouble) {
            result.value = (Double) this.value - (Double)v.value;
        }
        else if(v instanceof Int) {
            result.value = (Double) this.value - (Integer) v.value;
        }
        return result;
    }

    @Override
    public Value mul(Value v){
        MyDouble result  = new MyDouble();
        if(v instanceof ImaginaryInt) {
            result.value = (Double) this.value * (Integer) v.value;
        }
        else if(v instanceof ImaginaryDouble) {
            result.value = (Double) this.value * (Double)v.value;
        }
        else if(v instanceof MyDouble) {
            result.value = (Double) this.value * (Double)v.value;
        }
        else if(v instanceof Int) {
            result.value = (Double) this.value * (Integer) v.value;
        }
        return result;
    }

    @Override
    public Value div(Value v){
        MyDouble result  = new MyDouble();
        if(v instanceof ImaginaryInt) {
            if((Integer)v.value == 0) throw new ArithmeticException("Division by zero");
            result.value = (Double)this.value / (Integer)v.value;
        }
        else if(v instanceof ImaginaryDouble) {
            if((Double)v.value == 0.0) throw new ArithmeticException("Division by zero");
            result.value =  (Double)this.value / (Double)v.value;
        }
        else if(v instanceof Int){
            if((Integer)v.value == 0) throw new ArithmeticException("Division by zero");
            result.value = (Double)this.value / (Integer)v.value;
        }
        else if(v instanceof MyDouble) {
            if((Double)v.value == 0.0) throw new ArithmeticException("Division by zero");
            result.value = (Double)this.value / (Double)v.value;
        }
        return result;
    }

    @Override
    public Value pow(Value v){
        MyDouble result = new MyDouble();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            double r = Math.abs((Double) this.value);
            double theta = Math.atan2(0, (Double) this.value);
            double rAfter = Math.pow(r, (Integer)other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Integer)other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = rAfter * Math.cos(thetaAfter);
        }
        else if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            double r = Math.abs((Double) this.value);
            double theta = Math.atan2(0, (Double) this.value);
            double rAfter = Math.pow(r, (Double)other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Double)other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = rAfter * Math.cos(thetaAfter);
        }
        else if(v instanceof Int) {
            result.value = Math.pow((Double) this.value, (Integer) v.value);
        }
        else if(v instanceof MyDouble) {
            result.value = Math.pow((Double) this.value, (Double) v.value);
        }
        return result;
    }

    @Override
    public boolean eq(Value v){
        if(v instanceof ImaginaryInt other) {
            return this.value.equals(((Integer)other.value).doubleValue()) && other.getImaginaryPart() == 0;
        }
        else if(v instanceof ImaginaryDouble other) {
            return (this.value).equals(other.value) && other.getImaginaryPart() == 0.0;
        }
        else if(v instanceof Int) {
            return this.value.equals(((Integer)v.value).doubleValue());
        }
        else if(v instanceof MyDouble) {
            return (this.value).equals(v.value);
        }
        return false;
    }

    @Override
    public boolean lte(Value v){
        if (v instanceof MyDouble) {
            return (Double) this.value <= (Double) v.value;
        }
        else if (v instanceof Int) {
            return (Double) this.value <= (Integer) v.value;
        }
        return false;
    }

    @Override
    public boolean gte(Value v){
        if (v instanceof MyDouble) {
            return (Double) this.value >= (Double) v.value;
        }
        else if (v instanceof Int) {
            return (Double) this.value >= (Integer) v.value;
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
        MyDouble otherObj = (MyDouble) other;
        return this.value.equals(otherObj.value);
    }

    @Override
    public int hashCode(){
        return 31 * value.hashCode();
    }
}
