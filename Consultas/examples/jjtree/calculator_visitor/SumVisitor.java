public class SumVisitor extends CalculatorVisitorAdapter {
  public int sum;
  public Object visit(ASTOperand operand, Object data) {
    sum += Integer.parseInt(operand.image);
    return super.visit(operand, data);
  }
}
