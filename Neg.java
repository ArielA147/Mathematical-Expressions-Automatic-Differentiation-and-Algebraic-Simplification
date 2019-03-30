import java.util.List;
import java.util.Map;

/**
 * class represents the negative expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param expression expression.
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public Neg(Double number) {
        super(number);
    }

    /**
     * Constructor.
     *
     * @param number number expression.
     */
    public Neg(int number) {
        super(number);
    }

    /**
     * Constructor.
     *
     * @param str string expression.
     */
    public Neg(String str) {
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
        // if the neg expression already got minus before and gets another minus- need to make it disappear/
        try {
            if (super.getArgument().evaluate() <= 0) {
                return new Num(super.getArgument().evaluate() * -1).toString();
            }
        } catch (Exception ex) {
            boolean flag = false; // just to fill the catch - do nothing !
        }
        return "(-" + super.getArgument().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -1 * super.getArgument().evaluate(assignment);
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        if (super.getVariables().contains(var)) {
            if (super.getArgument().toString().equals(var)) {
                return new Neg(super.getArgument().differentiate(var));
            }
        }
        return new Num(0);
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        try {
            return new Num(-1 * super.getArgument().evaluate());
        } catch (Exception except) {
            boolean flag = false; // just to fill the catch - do nothing !
        }
        return new Neg(super.getArgument().simplify());
    }
}
