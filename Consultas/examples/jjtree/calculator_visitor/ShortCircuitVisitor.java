public class ShortCircuitVisitor extends CalculatorVisitorAdapter {
  public Object visit(ASTOperand operand, Object data) {
    // do some work
    return data;
  }
}
