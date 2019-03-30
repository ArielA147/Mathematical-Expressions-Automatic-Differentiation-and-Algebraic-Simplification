import java.util.List;
import java.util.Map;

/**
 * class represents the multiple expressions.
 *
 * @version 10.05.2018
 * @author ariel alexi
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(Expression leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(double leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(Expression leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(double leftArgument, double rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(String leftArgument, Expression rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(Expression leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(String leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(double leftArgument, String rightArgument) {
        super(leftArgument, rightArgument);
    }

    /**
     * Constructor.
     *
     * @param leftArgument  left argument expression.
     * @param rightArgument right argument expression.
     */
    public Mult(String leftArgument, double rightArgument) {
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
        return "(" + super.toStringLeft() + " * " + super.toStringRight() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(super.getMyLeftArgument().assign(var, expression), super.getMyRightArgument().assign(var,
                expression));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getMyLeftArgument().evaluate(assignment) * super.getMyRightArgument().evaluate(assignment);
    }

    /**
     * @param var - variable object
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String var) {

        return new Plus(new Mult(super.getMyLeftArgument().differentiate(var),
                super.getMyRightArgument()),
                new Mult(super.getMyRightArgument().differentiate(var), super.getMyLeftArgument()));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {

        Expression right = super.getMyRightArgument().simplify();
        Expression left = super.getMyLeftArgument().simplify();

        if (super.checkValue(left, 0.0) || super.checkValue(right, 0.0)) {
//            System.out.println("there i 0 in mult");
            return new Num(0.0);
        }

        if (super.checkValue(right, 1.0)) {
            return left;
        }

        if (super.checkValue(left, 1.0)) {
            return right;
        }

        if (right.getVariables().size() == 0.0 && left.getVariables().size() == 0.0) {
            try {
                return new Num(right.evaluate() * left.evaluate());
            } catch (Exception ex) {
                boolean flag = false; // just to fill the catch - do nothing !
            }
        }

        if (right.getVariables().size() == 0.0 && left.getVariables().size() != 0.0) {
            try {
                return new Mult(new Num(right.evaluate()), left);
            } catch (Exception ex) {
                boolean flag = false; // just to fill the catch - do nothing !
            }
        }

        if (right.getVariables().size() != 0 && left.getVariables().size() == 0) {
            try {
                return new Mult(right, new Num(left.evaluate()));
            } catch (Exception ex) {
                boolean flag = false; // just to fill the catch - do nothing !
            }
        }

        // there is no simplify for the expression
        return this;

    }

}
