package pl.edu.uj.javaframe;

public class ImaginaryDouble extends MyDouble {
    private double imaginaryPart;

    @Override
    public Value create(String val) {
        String[] numParts = val.split("i");
        this.value = Double.parseDouble(numParts[0]);
        if (numParts.length > 1) {
            this.imaginaryPart = Double.parseDouble(numParts[1]);
        } else {
            this.imaginaryPart = 0.0;
        }

        return this;
    }

    @Override
    public Value add(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();
        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Double) this.value + (Double) other.value;
            result.imaginaryPart = this.imaginaryPart + other.imaginaryPart;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Double) this.value + (Integer) other.value;
            result.imaginaryPart = this.imaginaryPart + other.getImaginaryPart();
        } else if (v instanceof MyDouble) {
            result.value = (Double) this.value + (Double) v.value;
            result.imaginaryPart = this.imaginaryPart;
        } else if (v instanceof Int) {
            result.value = (Double) this.value + (Integer) v.value;
            result.imaginaryPart = this.imaginaryPart;
        }
        return result;
    }

    @Override
    public Value sub(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();
        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Double) this.value - (Double) other.value;
            result.imaginaryPart = this.imaginaryPart - other.imaginaryPart;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Double) this.value - (Integer) other.value;
            result.imaginaryPart = this.imaginaryPart - other.getImaginaryPart();
        } else if (v instanceof MyDouble) {
            result.value = (Double) this.value - (Double) v.value;
            result.imaginaryPart = this.imaginaryPart;
        } else if (v instanceof Int) {
            result.value = (Double) this.value - (Integer) v.value;
            result.imaginaryPart = this.imaginaryPart;
        }
        return result;
    }

    @Override
    public Value mul(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();
        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            result.value = (Double) this.value * (Double) other.value - this.imaginaryPart * other.imaginaryPart;
            result.imaginaryPart = this.imaginaryPart * (Double) other.value + (Double) this.value * other.imaginaryPart;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            result.value = (Double) this.value * (Integer) other.value - this.imaginaryPart * other.getImaginaryPart();
            result.imaginaryPart = this.imaginaryPart * (Integer) other.value + (Double) this.value * other.getImaginaryPart();
        } else if (v instanceof MyDouble) {
            result.value = (Double) this.value * (Double) v.value;
            result.imaginaryPart = this.imaginaryPart * (Double) v.value;
        } else if (v instanceof Int) {
            result.value = (Double) this.value * (Integer) v.value;
            result.imaginaryPart = this.imaginaryPart * (Integer) v.value;
        }
        return result;
    }


    @Override
    public Value div(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();
        if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            double denominator = (Double) other.value * (Double) other.value + other.imaginaryPart * other.imaginaryPart;
            if(denominator == 0.0) throw new ArithmeticException("Division by zero");
            result.value =  ((Double) this.value * (Double) other.value + this.imaginaryPart * other.imaginaryPart) / denominator;
            result.imaginaryPart = (this.imaginaryPart * (Double) other.value - (Double) this.value * other.imaginaryPart) / denominator;
        }
        else if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            double denominator = (Integer) other.value * (Integer) other.value + other.getImaginaryPart() * other.getImaginaryPart();
            if (denominator == 0) throw new ArithmeticException("Division by zero");
            result.value = ((Double) this.value * (Integer) other.value + this.imaginaryPart * other.getImaginaryPart()) / denominator;
            result.imaginaryPart = (this.imaginaryPart * (Integer) other.value - (Double) this.value * other.getImaginaryPart()) / denominator;
        }
        else if(v instanceof Int) {
            int denominator = (Integer) v.value;
            if (denominator == 0) throw new ArithmeticException("Division by zero");
            result.value = (Double) this.value / denominator;
            result.imaginaryPart = this.imaginaryPart / denominator;
        }
        else if(v instanceof MyDouble) {
            double denominator = (Double) v.value;
            if (denominator == 0.0) throw new ArithmeticException("Division by zero");
            result.value = (Double) this.value / denominator;
            result.imaginaryPart = this.imaginaryPart / denominator;
        }
        return result;
    }

    @Override
    public Value pow(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();
        if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            double r = Math.sqrt((Double) this.value * (Double) this.value + this.imaginaryPart * this.imaginaryPart);
            double theta = Math.atan2(this.imaginaryPart, (Double) this.value);
            double rAfter = Math.pow(r, (Integer) other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Integer) other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = rAfter * Math.cos(thetaAfter);
            result.imaginaryPart = rAfter * Math.sin(thetaAfter);
        }
        else if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            double r = Math.sqrt((Double) this.value * (Double) this.value + this.imaginaryPart * this.imaginaryPart);
            double theta = Math.atan2(this.imaginaryPart, (Double) this.value);
            double rAfter = Math.pow(r, (Double) other.value) * Math.exp(-other.getImaginaryPart() * theta);
            double thetaAfter = (Double) other.value * theta + other.getImaginaryPart() * Math.log(r);
            result.value = rAfter * Math.cos(thetaAfter);
            result.imaginaryPart = rAfter * Math.sin(thetaAfter);
        }
        else if(v instanceof Int) {
            int exponent = (Integer) v.value;
            if(exponent == 0) {
                result.value = 1.0;
                result.imaginaryPart = 0.0;
                return result;
            }
            result.value = this.value;
            result.imaginaryPart = this.imaginaryPart;
            for(int i = 1; i < Math.abs(exponent); i++) {
                result = (ImaginaryDouble) result.mul(this);
            }

            if(exponent < 0) {
                double denominator = (Double) result.value * (Double) result.value + result.imaginaryPart * result.imaginaryPart;
                if (denominator == 0.0) throw new ArithmeticException("Division by zero");
                double real = (Double) result.value;
                double imaginary = result.imaginaryPart;
                result.value = real / denominator;
                result.imaginaryPart = -imaginary / denominator;
                return result;
            }
        }
        else if(v instanceof MyDouble) {
            double exponent = (Double) v.value;
            double r = Math.sqrt((Double) this.value * (Double) this.value + this.imaginaryPart * this.imaginaryPart);
            double theta = Math.atan2(this.imaginaryPart, (Double) this.value);
            double rAfter = Math.pow(r, exponent);
            double thetaAfter = theta * exponent;
            result.value = rAfter * Math.cos(thetaAfter);
            result.imaginaryPart = rAfter * Math.sin(thetaAfter);
        }
        return result;
    }

    @Override
    public boolean eq(Value v) {
        if(v instanceof ImaginaryDouble) {
            ImaginaryDouble other = (ImaginaryDouble) v;
            return this.value.equals(other.value) && this.imaginaryPart == other.getImaginaryPart();
        }
        else if(v instanceof ImaginaryInt) {
            ImaginaryInt other = (ImaginaryInt) v;
            return this.value.equals(((Integer)other.value).doubleValue()) && this.imaginaryPart == other.getImaginaryPart();
        }
        else if(v instanceof MyDouble) {
            MyDouble other = (MyDouble) v;
            return this.imaginaryPart == 0.0 && this.value.equals(other.value);
        }
        else if(v instanceof Int) {
            Int other = (Int) v;
            return this.imaginaryPart == 0.0 && this.value.equals(((Integer)other.value).doubleValue());
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
        ImaginaryDouble otherObj = (ImaginaryDouble) other;
        return Double.compare((double) this.value, (double) otherObj.value) == 0 && Double.compare(this.imaginaryPart, otherObj.imaginaryPart) == 0;
    }

    @Override
    public int hashCode(){
        int result = value != null ? value.hashCode() : 0;
        result =  31 * result + (int) imaginaryPart;
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
