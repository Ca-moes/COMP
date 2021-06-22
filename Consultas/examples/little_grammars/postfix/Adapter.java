public class Adapter implements PostfixVisitor {
  public Object visit(SimpleNode node, Object data) {
    return node.childrenAccept(this, data);
  } 
  public Object visit(ASTExpression node, Object data){
    return node.childrenAccept(this, data);
  } 
  public Object visit(ASTFactor node, Object data){
    return node.childrenAccept(this, data);
  } 
  public Object visit(ASTTerm node, Object data){
    return node.childrenAccept(this, data);
  } 
  public Object visit(ASTOperator node, Object data){
    return node.childrenAccept(this, data);
  } 
}
