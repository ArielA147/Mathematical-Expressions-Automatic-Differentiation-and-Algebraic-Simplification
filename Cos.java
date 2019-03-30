import java.util.List;
import java.util.Map;

/**
 * class represents the cos expressions.
 *
 * @version 10.05.2018
 * @author ariel alexi
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param expression expression.
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public Cos(int number) {
        super(number);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public Cos(Double number) {
        super(number);
    }

    /**
     * Constructor.
     *
     * @param str string expression.
     */
    public Cos(String str) {
        super(str);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * @return string furmula of plus
     */
    public String toString() {
        return "cos(" + super.getArgument().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.getArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        try {
            double value = super.getArgument().evaluate(assignment);
            if (value % 180 == 90 || value % 180 == -90) {
                return 0.0;
            }
            if (value % 360 == 60 || value % 360 == -60) {
                return 0.5;
            }
            if (value % 360 == 240 || value % 360 == -240) {
                return (-1) * 0.5;
            }
            return Math.cos(Math.toRadians(value));
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        if (super.getVariables().contains(var)) {
            if (super.getArgument().toString().equals(var)) {
                return new Mult(super.getArgument().differentiate(var), new Neg(new Sin(super.getArgument())));
            }
        }
        return new Num(0);
    }


    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Cos(super.getArgument().simplify());
        }
    }
}