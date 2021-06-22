public class PostorderSumVisitor extends CalculatorVisitorAdapter {
  public int sum;
  public Object visit(ASTOperand operand, Object data) {
    Object obj = super.visit(operand, data);
    sum += Integer.parseInt(operand.image);
    return obj;
  }
}
