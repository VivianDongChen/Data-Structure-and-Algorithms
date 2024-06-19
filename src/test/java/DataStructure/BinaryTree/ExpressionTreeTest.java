package DataStructure.BinaryTree;

import org.junit.jupiter.api.Test;
import org.springframework.aop.scope.ScopedProxyUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {
    
    @Test
    void test1(){
        String[] tokens = {"2","1","-","3","*"};
        ExpressionTree.TreeNode root = new ExpressionTree().contructExpressionTree(tokens);
        ArrayList<String> result = new ArrayList<>();
        postTraversal(result, root);
        assertArrayEquals(tokens,result.toArray());
    }

    @Test
    void test2(){
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        ExpressionTree.TreeNode root = new ExpressionTree().contructExpressionTree(tokens);
        ArrayList<String> result = new ArrayList<>();
        postTraversal(result, root);
        assertArrayEquals(tokens,result.toArray());
    }




    static void postTraversal(ArrayList<String> list, ExpressionTree.TreeNode node){
        if(node == null){
            return;
        }

        postTraversal(list, node.left);
        postTraversal(list, node.right);
        list.add(node.val);
    }

}