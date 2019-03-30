import java.util.List;
import java.util.Map;

/**
 * class represents the division expressions.
 *
 * @author ariel alexi
 * @version 10.05.2018
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Div(String leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return list of the varibles that are in the division.
     */
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {

        return "(" + super.toStringLeft() + " / " + super.toStringRight() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(super.getMyLeftArgument().assign(var, expression),
                super.getMyRightArgument().assign(var, expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        double right = super.getMyRightArgument().evaluate(assignment);
        double left = super.getMyLeftArgument().evaluate(assignment);

        if (right == 0.0) {
            throw new RuntimeException("there was a division by zero :" + this.toString());
        }

        return left / right;
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Div(
                new Minus(
                        new Mult(super.getMyLeftArgument().differentiate(var), super.getMyRightArgument()),
                        new Mult(super.getMyRightArgument().differentiate(var), super.getMyLeftArgument())),
                new Pow(super.getMyRightArgument(), new Num(2)));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression left = super.getMyLeftArgument().simplify();
        Expression right = super.getMyRightArgument().simplify();
        try {
            if (left.evaluate() == 0.0) {
                return new Num(0.0);
            }
        } catch (Exception ex) {
            boolean flag = false; // just to fill the catch - do nothing !
        }
        try {
            if (checkValue(right, 1)) {
                return left;
            }
        } catch (Exception ex) {
            boolean flag = false; // just to fill the catch - do nothing !
        }

        try {
            if (right.evaluate() == left.evaluate()) {
                return new Num(1.0);
            }
        } catch (Exception ex) {
            boolean flag = false; // just to fill the catch - do nothing !
        }

        try {
            if (right.toString().equals(left.toString())) {
                return new Num(1.0);
            }
        } catch (Exception ex) {
            boolean flag = false; // just to fill the catch - do nothing !
        }

        try {
            return new Num(left.evaluate() / right.evaluate());
        } catch (Exception ex) {
            return new Div(left, right);
        }
    }
}
