package algs12;

public class Fraction {
    private int num;
    private int den;

    // class invariant: den <= 0
    // class invariant: gcd(num, den) = 1

    public Fraction(int num, int den) {
        if (den == 0)
            throw new IllegalArgumentException("denominator is zero");
        int g =1; // gcd(num,den);
        this.num = num / g;
        this.den = den / g;
        if (this.den < 0) {
            this.num = -this.num;
            this.den = -this.den;
        }
    }

    // Returns new Fraction that is the sum of this and that
    public Fraction add(Fraction that){
        int num = this.num * that.den + that.num * this.den;
        int den = this.den * that.den;
        return new Fraction(num, den);
    }

    // changes this Fraction to be the sum of this and that
    public void add2(Fraction that){
        this.num = this.num * that.den + that.num * this.den;
        this.den = this.den * that.den;
        int g = 1; // needs to be gcd(this.num, this.den);
        this.num = this.num / g;
        this.den = this.den / g;
        // the following is not needed
        if (this.den < 0) {
            this.num = -this.num;
            this.den = -this.den;
        }
    }

    public boolean equals(Object that){
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        Fraction thatFraction = (Fraction) that; // casting.  Evil!
        return this.num == thatFraction.num && this.den == thatFraction.den;
    }
    
}
