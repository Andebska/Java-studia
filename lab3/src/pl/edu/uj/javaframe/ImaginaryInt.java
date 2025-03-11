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
            result.value = (Integer) this.value + ((Double) v.value).intValue();
            result.imaginaryPart = this.imaginaryPart;
        }
        return result;
    }

    @Override
    public Value sub(Value v) {
        ImaginaryInt result = new ImaginaryInt();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Integer) this.value - (Integer) other.value;
            result.imaginaryPart = this.imaginaryPart - other.imaginaryPart;
        }
        else if(v instanceof  ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Integer) this.value - ((Double) other.value).intValue();
            result.imaginaryPart = this.imaginaryPart - (int) other.getImaginaryPart();
        }
        else if(v instanceof Int) {
            result.value = (Integer) this.value - (Integer) v.value;
            result.imaginaryPart = this.imaginaryPart;
        }
        else if(v instanceof MyDouble) {
            result.value = (Integer) this.value - ((Double) v.value).intValue();
            result.imaginaryPart = this.imaginaryPart;
        }
        return result;
    }

    @Override
    public Value mul(Value v) {
        ImaginaryInt result = new ImaginaryInt();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Integer) this.value * (Integer) other.value - this.imaginaryPart * other.imaginaryPart;
            result.imaginaryPart  = (Integer) this.value + other.imaginaryPart + this.imaginaryPart * (Integer) other.value;
        }
        else if (v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Integer) this.value * ((Double)other.value).intValue() - this.imaginaryPart * ((Double)other.getImaginaryPart()).intValue();
            result.imaginaryPart = this.imaginaryPart * ((Double)other.value).intValue() + (Integer) this.value * ((Double)other.getImaginaryPart()).intValue();
        }
        else if (v instanceof Int) {
            result.value = (Integer) this.value * (Integer) v.value;
            result.imaginaryPart = this.imaginaryPart * (Integer) v.value;
        }
        else if(v instanceof MyDouble) {
            result.value = (Integer) this.value * ((Double) v.value).intValue();
            result.imaginaryPart = this.imaginaryPart * ((Double) v.value).intValue();
        }
        return result;
    }

    @Override
    public Value div(Value v) {
        ImaginaryInt result = new ImaginaryInt();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            int denominator = (Integer) other.value * (Integer) other.value + other.imaginaryPart * other.imaginaryPart;
            if(denominator == 0) throw new ArithmeticException("Division by zero");
            result.value = (((Integer) this.value * (Integer) other.value) + (this.imaginaryPart * other.imaginaryPart)) / denominator;
            result.imaginaryPart = ((this.imaginaryPart * (Integer) other.value) - ((Integer) this.value * other.imaginaryPart)) / denominator;
        }
        else if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            double denominator = (Double) other.value * (Double) other.value + other.getImaginaryPart() * other.getImaginaryPart();
            if (denominator == 0.0) throw new ArithmeticException("Division by zero");
            result.value = (int) Math.round(((Integer) this.value * (Double) other.value + this.imaginaryPart * other.getImaginaryPart()) / denominator);
            result.imaginaryPart = (int) Math.round((this.imaginaryPart * (Double) other.value - (Integer) this.value * other.getImaginaryPart()) / denominator);
        }
        else if(v instanceof Int) {
            int denominator = (Integer) v.value;
            if (denominator == 0) throw new ArithmeticException("Division by zero");
            result.value = (Integer) this.value / denominator;
            result.imaginaryPart = this.imaginaryPart / denominator;
        }
        else if(v instanceof MyDouble) {
            double denominator = (Double) v.value;
            if (denominator == 0.0) throw new ArithmeticException("Division by zero");
            result.value = (int) Math.round((Integer) this.value / denominator);
            result.imaginaryPart = (int) Math.round(this.imaginaryPart / denominator);
        }
        return result;
    }

    @Override
    public Value pow(Value v) {
        ImaginaryInt result = new ImaginaryInt();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            double r = Math.sqrt((Integer) this.value * (Integer) this.value + this.imaginaryPart * this.imaginaryPart);
            double theta = Math.atan2(this.imaginaryPart, (Integer) this.value);
            double rAfter = Math.pow(r, (Integer) other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Integer) other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = (int) Math.round(rAfter * Math.cos(thetaAfter));
            result.imaginaryPart = (int) Math.round(rAfter * Math.sin(thetaAfter));
        }
        else if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            double r = Math.sqrt((Integer) this.value * (Integer) this.value + this.imaginaryPart * this.imaginaryPart);
            double theta = Math.atan2(this.imaginaryPart, (Integer) this.value);
            double rAfter = Math.pow(r, (Double) other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Double) other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = (int) Math.round(rAfter * Math.cos(thetaAfter));
            result.imaginaryPart = (int) Math.round(rAfter * Math.sin(thetaAfter));
        }
        else if(v instanceof Int) {
            int exponent = (Integer) v.value;
            if(exponent == 0) {
                result.value = 1;
                result.imaginaryPart = 0;
                return result;
            }
            result.value = this.value;
            result.imaginaryPart = this.imaginaryPart;
            for(int i = 1; i < Math.abs(exponent); i++) {
                result = (ImaginaryInt) result.mul(this);
            }

            if(exponent < 0) {
                int denominator = (Integer) result.value * (Integer) result.value + result.imaginaryPart * result.imaginaryPart;
                if (denominator == 0) throw new ArithmeticException("Division by zero");
                int real = (Integer) result.value;
                int imaginary = result.imaginaryPart;
                result.value = real / denominator;
                result.imaginaryPart = -imaginary / denominator;
                return result;
            }
        }
        else if(v instanceof MyDouble) {
            double exponent = (Double) v.value;
            double r = Math.sqrt((Integer) this.value * (Integer) this.value + this.imaginaryPart * this.imaginaryPart);
            double theta = Math.atan2(this.imaginaryPart, (Integer) this.value);
            double rAfter = Math.pow(r, exponent);
            double thetaAfter = theta * exponent;
            result.value = (int) Math.round(rAfter * Math.cos(thetaAfter));
            result.imaginaryPart = (int) Math.round(rAfter * Math.sin(thetaAfter));
        }
        return result;
    }

    @Override
    public boolean eq(Value v) {
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            return this.value.equals(other.value) && this.imaginaryPart == other.getImaginaryPart();
        }
        else if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            return other.value.equals(((Integer)this.value).doubleValue()) && (double) this.imaginaryPart == other.getImaginaryPart();
        }
        else if(v instanceof Int) {
            Int other = (Int) v;
            return this.imaginaryPart == 0 && this.value.equals(other.value);
        }
        else if(v instanceof MyDouble) {
            MyDouble other = (MyDouble) v;
            return this.imaginaryPart == 0 && other.value.equals(((Integer)this.value).doubleValue());
        }
        return false;
    }

    @Override
    public boolean lte(Value v) {
        throw new ArithmeticException("Inequalities of imaginary numbers are not well defined");
    }

    @Override
    public boolean gte(Value v) {
        throw new ArithmeticException("Inequalities of imaginary numbers are not well defined");
    }

    @Override
    public boolean neq(Value v) {
        return !eq(v);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ImaginaryInt otherObj = (ImaginaryInt) other;
        return this.value.equals(otherObj.value) && this.imaginaryPart == otherObj.imaginaryPart;
    }

    @Override
    public int hashCode(){
        int result = value != null ? value.hashCode() : 0;
        result =  31 * result + imaginaryPart;
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
