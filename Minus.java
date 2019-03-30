import java.util.List;
import java.util.Map;

/**
 * class represents the minus expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public class Minus extends BinaryExpression implements Expression {


    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Minus(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return list of the variables that in the power.
     */
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {
        return "(" + super.toStringLeft() + " - " + super.toStringRight() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(super.getMyLeftArgument().assign(var, expression),
                super.getMyRightArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getMyLeftArgument().evaluate(assignment) - super.getMyRightArgument().evaluate(assignment);
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        if (super.getVariables().contains(var)) {
            return new Minus(super.getMyLeftArgument().differentiate(var),
                    super.getMyRightArgument().differentiate(var));
        }
        return new Num(0);
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression right = super.getMyRightArgument().simplify();
        Expression left = super.getMyLeftArgument().simplify();

        if (checkValue(left, 0.0)) {
            return new Neg(super.getMyRightArgument());
        } else if (checkValue(right, 0.0)) {
            return super.getMyLeftArgument();
        } else if (right.toString().equals(left.toString())) {
            return new Num(0);
        } else {
            try {
                return new Num(left.evaluate() - right.evaluate());
            } catch (Exception ex) {
                return new Minus(left, right);
            }
        }
    }
}
