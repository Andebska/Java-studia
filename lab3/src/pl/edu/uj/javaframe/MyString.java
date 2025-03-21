package pl.edu.uj.javaframe;

public class MyString extends Value {
    @Override
    public Value create(String val) {
        MyString v = new MyString();
        v.value = val;
        return v;
    }

    @Override
    public Value add(Value v) {
        if (v instanceof MyString) {
            MyString other = (MyString) v;
            MyString result = new MyString();
            result.value = (String)this.value + other.value;
            return result;
        }
        return null;
    }

    @Override
    public Value sub(Value v) { return null; }

    @Override
    public Value mul(Value v){ return null; }

    @Override
    public Value div(Value v){ return null; }

    @Override
    public Value pow(Value v){ return null;}

    @Override
    public boolean eq(Value v) {
        if (v instanceof MyString) {
            return this.value.equals(((MyString) v).value);
        }
        return false;
    }

    @Override
    public boolean lte(Value v) {
        if (v instanceof MyString) {
            return ((String)this.value).compareTo((String) v.value) <= 0;
        }
        return false;
    }

    @Override
    public boolean gte(Value v) {
        if (v instanceof MyString) {
            return ((String)this.value).compareTo((String) v.value) >= 0;
        }
        return false;
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
        if (other instanceof MyString) {
            return this.value.equals(((MyString) other).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (value != null) ? value.hashCode() : 0;
    }

}
