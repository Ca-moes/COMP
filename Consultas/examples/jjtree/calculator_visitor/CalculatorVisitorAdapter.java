public class CalculatorVisitorAdapter implements CalculatorVisitor
{
  public Object visit(SimpleNode node, Object data) {
    return node.childrenAccept(this, data);
  }
  public Object visit(ASTExpression node, Object data) {
    return node.childrenAccept(this, data);
  }
  public Object visit(ASTOperator node, Object data) {
    return node.childrenAccept(this, data);
  }
  public Object visit(ASTOperand node, Object data) {
    return node.childrenAccept(this, data);
  }
}
