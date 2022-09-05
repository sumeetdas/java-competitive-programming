package sumeetdas.javacp.math;

public class Math {
    private Math () {}

    /**
     * Recursive function of exponentiation is just an implementation of definition.
     * Complexity  - O(N) where N is exponent.
     * 
     * Fast exponentiation's complexity is O(lg N)
     * link: https://en.wikipedia.org/wiki/Exponentiation_by_squaring"
     * 
     * Modular exponentiation is similar.
     * link: https://en.wikipedia.org/wiki/Modular_exponentiation
     * This implementation is the fast version of this algorithm with a complexity of O(lg N) also
     */
    public static int recursiveExponentiation(int base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;

        return recursiveExponentiation(base, exponent - 1) * base;
    }

    public static int fastRecursiveExponentiation(int base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;

        final int resultOnHalfExponent = fastRecursiveExponentiation(base, exponent / 2);
        if ((exponent % 2) == 0)
            return resultOnHalfExponent * resultOnHalfExponent;
        else
            return resultOnHalfExponent * resultOnHalfExponent * base;

    }

    public static int fastRecursiveExponentiationModulo(int base, int exponent, int mod) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;

        final int resultOnHalfExponent = fastRecursiveExponentiationModulo(base, exponent / 2, mod);
        if ((exponent % 2) == 0)
            return (resultOnHalfExponent * resultOnHalfExponent) % mod;
        else
            return (((resultOnHalfExponent * resultOnHalfExponent) % mod) * base) % mod;
    }
}
