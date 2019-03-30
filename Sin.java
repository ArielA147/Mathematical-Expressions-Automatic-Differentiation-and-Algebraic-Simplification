import java.util.List;
import java.util.Map;

/**
 * class represents the sin expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param expression expression.
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public Sin(Double number) {
        super(number);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public Sin(int number) {
        super(number);
    }

    /**
     * Constructor.
     *
     * @param str string expression.
     */
    public Sin(String str) {
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
        return "sin(" + super.getArgument().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
//        return Math.sin(Math.toRadians(super.getArgument().evaluate(assignment)));
        try {   //tries evaluating using map.
            boolean flag = false;
            double value = super.getArgument().evaluate(assignment);
            if (value < 0) {    //all if blocks are for precision matters (in cases 0, -0.5 or 0.5 expected).
                value = (-1) * value;
                flag = true;
            }
            if (value % 180 == 0) {
                return 0.0;
            }
            if (value % 360 == 30) {
                if (flag) {
                    return (-1) * 0.5;
                }
                return 0.5;
            }
            if (value % 360 == 210) {
                if (flag) {
                    return 0.5;
                }
                return (-1) * 0.5;
            }
            double toRet = Math.sin(Math.toRadians(value));
            if (flag) {
                return (-1) * toRet;
            }
            return toRet;
        } catch (Exception ex) {
            //if fails, throws Exception.
            throw ex;
        }
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Mult(super.getArgument().differentiate(var), new Cos(super.getArgument()));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Sin(super.getArgument().simplify());
        }

    }
}
