import java.util.List;
import java.util.Map;

/**
 * class represents the plus expressions.
 *
 * @version 10.05.2018
 * @author ariel alexi
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Plus(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {
        return "(" + super.toStringLeft() + " + " + super.toStringRight() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(super.getMyLeftArgument().assign(var, expression),
                super.getMyRightArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getMyLeftArgument().evaluate(assignment) + super.getMyRightArgument().evaluate(assignment);
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        if (super.getVariables().contains(var)) {
            return new Plus(super.getMyLeftArgument().differentiate(var),
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
            return right;
        }
        if (checkValue(right, 0.0)) {
            return left;
        }

        // checking if there are no variables in one of the expressions :
        if (right.getVariables().size() == 0 && left.getVariables().size() == 0) {
            try {
                return new Num(right.evaluate() + left.evaluate()).simplify();
            } catch (Exception ex) {
                boolean flag = false; // just to fill the catch - do nothing !
            }
        }

        if (right.getVariables().size() == 0 && left.getVariables().size() != 0) {
            try {
                return new Plus(new Num(right.evaluate()).simplify(), left).simplify();
            } catch (Exception ex) {
                boolean flag = false; // just to fill the catch - do nothing !
            }
        }

        if (right.getVariables().size() != 0 && left.getVariables().size() == 0) {
            try {
                return new Plus(right, new Num(left.evaluate()));
            } catch (Exception ex) {
                return this;
            }
        }

        // there is no simplify for the expression
        return this;
    }
}
