/* Oscar Hedblad
 * PID: o3415424
 * COP 3503, Fall 2013
 * Assignment #2 - GenericBST.java
 * 
 * DESCRIPTION: A basic binary search tree implementation that supports the insertion and
 * deletion operations that was given to us. The program will create a BST dependent
 * on the input given. It will then follow the BST rules and traverse through the tree, 
 * printing the correct node as it goes. 
 * This is now a generic BST, which supports the use
 * of any type of data. Below the actual BST there are two methods that return the 
 * difficulty rating and time spent working on this assignment. 
 * */

/* Import packages; however, the packages were never used */
import java.io.*;
import java.util.*;

/* Class that creates a Node. I modified this class to extend the comparableTo() 
 * function and accept any type (anyT). */
class Node<anyT extends Comparable<anyT>> 
{
    anyT data;
    // Nodes have a left and a right child
    Node<anyT> left, right;

    Node(anyT data)
    {
        this.data = data;
    }
}
/* Public class that now accepts any data type (anyT) and extends the comparableTo()
 * function. This class is the creation of the BST */
public class GenericBST<anyT extends Comparable<anyT>>
{
    // Creates the root of the BST
    private Node<anyT> root;

    public void insert(anyT data)
    {
        root = insert(root, data);
    }
/* Private class that inserts nodes to the correct spot on the BST */ 
    private Node<anyT> insert(Node<anyT> root, anyT data)
    {
        if (root == null)
        {
            return new Node<anyT>(data);
        }
        // If data is < root.data then value gets placed as left child.
        else if (data.compareTo(root.data) < 0)
        {
            root.left = insert(root.left, data);
        }
        // If data > root.data then value gets placed as right child.
        else if (data.compareTo(root.data) > 0)
        {
            root.right = insert(root.right, data);
        }
        else
        {
            // Stylistically, I have this here to explicitly state that we are
            // disallowing insertion of duplicate values.
            ;
        }

        return root;
    }
    
    // Delete function that calls the other (real) delete function.
    public void delete(anyT data)
    {
        root = delete(root, data);
    }
/* This method deletes the "correct" nodes that gets passed into the BST */
    private Node<anyT> delete(Node<anyT> root, anyT data)
    {
        if (root == null)
        {
            return null;
        }
        // If data < root.data then the left child gets deleted.
        else if (data.compareTo(root.data) < 0)
        {
            root.left = delete(root.left, data);
        }
        // If data > root.data then the right child gets deleted.
        else if (data.compareTo(root.data) > 0)
        {
            root.right = delete(root.right, data);
        }
        else
        {
            // If root.left and root.right are empty, return NULL
            if (root.left == null && root.right == null)
            {
                return null;
            }
            // If root.right is empty, return root.left
            else if (root.right == null)
            {
                return root.left;
            }
            // If root.left is empty, return root.right
            else if (root.left == null)
            {
                return root.right;
            }
            else
            {
                root.data = findMax(root.left);
                root.left = delete(root.left, root.data);
            }
        }

        return root;
    }

    // This method assumes root is non-null, since this is only called by
    // delete() on the left subtree, and only when that subtree is non-empty.
    private anyT findMax(Node<anyT> root)
    {
        while (root.right != null)
        {
            root = root.right;
        }

        return root.data;
    }

    // Returns true if the value is contained in the BST, false otherwise.
    public boolean contains(anyT data)
    {
        return contains(root, data);
    }
/* A boolean (true/false) method that checks if the value being passed is present */
    private boolean contains(Node<anyT> root, anyT data)
    {
        if (root == null)
        {
            return false;
        }
        // If data < root.data then is calls the recursive contains-function again, 
        // but with left child.
        else if (data.compareTo(root.data) < 0)
        {
            return contains(root.left, data);
        }
        // If data > root.data then is calls the recursive contains-function again, 
        // but with right child.
        else if (data.compareTo(root.data) > 0)
        {
            return contains(root.right, data);
        }
        else
        {
            return true;
        }
    }
    // Prints the "In-order Traversal:" then calls the other (real) inorder function
    public void inorder()
    {
        System.out.print("In-order Traversal:");
        inorder(root);
        System.out.println();
    }
    // This gets called and follows the BST rules for inorder, 
    // which is LEFT, PRINT, RIGHT
    private void inorder(Node<anyT> root)
    {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(" " + root.data);
        inorder(root.right);
    }
    // Prints the "Pre-order Traversal:" then calls the other (real) preorder function
    public void preorder()
    {
        System.out.print("Pre-order Traversal:");
        preorder(root);
        System.out.println();
    }
    // This gets called and follows the BST rules for preorder, 
    // which is PRINT, LEFT, RIGHT
    private void preorder(Node<anyT> root)
    {
        if (root == null)
            return;

        System.out.print(" " + root.data);
        preorder(root.left);
        preorder(root.right);
    }
    // Prints the "Post-order Traversal:" then calls the other (real) postorder function
    public void postorder()
    {
        System.out.print("Post-order Traversal:");
        postorder(root);
        System.out.println();
    }
    // This gets called and follows the BST rules for preorder, 
    // which is LEFT, RIGHT, PRINT
    private void postorder(Node<anyT> root)
    {
        if (root == null)
            return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(" " + root.data);
    }
/*     MAIN METHOD. I know that the main method is not going to be used but I still added 
 * the <Integer> cast to the GenericBST below, which removed the small warning/errors 
 * that was occurring. */
    public static void main(String [] args)
    {
        GenericBST<Integer> myTree = new GenericBST<Integer>();

        for (int i = 0; i < 5; i++)
        {
            int r = (int)(Math.random() * 100) + 1;
            System.out.println("Inserting " + r + "...");
            myTree.insert(r);
        }

        myTree.inorder();
        myTree.preorder();
        myTree.postorder();
    }
    /* Method that returns the difficulty rating that I found this assignment to correspond to.
     * I had a hard time to understand BST's in CS1 because of poor instructions, so in my
     * opinion this assignment was a bit difficult but very helpful! */
    public static double difficultyRating(){
        return 4.1;
    }
    /* Method that returns the rough estimate of the number of hours I spent working on
     * this assignment. Because of my poor prior knowledge of BST's it took me a little
     * extra time. */
    public static double hoursSpent(){
        return 3.2;
    }
}