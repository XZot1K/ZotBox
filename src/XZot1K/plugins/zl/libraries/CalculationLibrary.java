package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;

import java.util.Random;

public class CalculationLibrary
{

    private ZotLib plugin = Manager.getPlugin();
    private Random random;

    public CalculationLibrary()
    {
        random = new Random();
    }

    /*
    The "calculateFormula" method will allow you to feed it a String formula and it will calculate it
    based on the variables and symbols it knows.
    */
    public double calculateFormula(String formula)
    {
        return new Object()
        {
            int pos = -1, ch;

            void nextChar()
            {
                ch = (++pos < formula.length()) ? formula.charAt(pos) : -1;
            }

            boolean eat(int charToEat)
            {
                while (ch == ' ')
                {
                    nextChar();
                }
                if (ch == charToEat)
                {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse()
            {
                nextChar();
                double x = parseExpression();
                if (pos < formula.length())
                    throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression()
            {
                double x = parseTerm();
                for (; ; )
                {
                    if (eat('+'))
                        x += parseTerm();
                    else if (eat('-'))
                        x -= parseTerm();
                    else
                        return x;
                }
            }

            double parseTerm()
            {
                double x = parseFactor();
                for (; ; )
                {
                    if (eat('*'))
                        x *= parseFactor();
                    else if (eat('/'))
                        x /= parseFactor();
                    else
                        return x;
                }
            }

            double parseFactor()
            {
                if (eat('+'))
                    return parseFactor();
                if (eat('-'))
                    return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('('))
                {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.')
                {
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                    {
                        nextChar();
                    }
                    x = Double.parseDouble(formula.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z')
                {
                    while (ch >= 'a' && ch <= 'z')
                    {
                        nextChar();
                    }
                    String func = formula.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func)
                    {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else
                {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^'))
                    x = Math.pow(x, parseFactor());

                return x;
            }

        }.parse();
    }

    // The "getRandomInt" method will generate a random integer between the minimum and maximum number you feed it.
    public int getRandomInt(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }

    /*
     The "getRandomDouble" method will generate a random decimal value between the minimum and maximum number you feed it,
     Much like the "getRandomMethod" method.
      */
    public static double getRandomDouble(double min, double max)
    {
        return (Math.random() * (max - min)) + min;
    }

    /*
    The "isChance" method allows you to feed it a "percentage of happening" and a "maximum percentage". It will then generate a random
    integer between 0 and the "maximum percentage" you fed it. Once it has this integer it will then check if the integer
    is less than or equal to the "percentage of happening" that you fed it, if it is not less than or equal to the "percentage of happening"
    then it will return false otherwise it will return true.
     */
    public boolean isChance(int percentageOfHappening, int maximumPercentage)
    {
        int chance = (int) (Math.random() * maximumPercentage);
        return chance < percentageOfHappening;
    }

}
