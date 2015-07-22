package com.data.structure.tree;

public class BinaryNode<T>{
	
	private T element;
	private BinaryNode<T> left;
	private BinaryNode<T> right;
	
	public BinaryNode(){
		this(null, null, null);
	}
	
	

	public BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
		this.element = theElement;
		this.left = lt;
		this.right = rt;
	}
	

	public T getElement(){
		return element;
	}
	
	public BinaryNode<T> getLeft(){
		return left;
	}
	
	public BinaryNode<T> getRight() {
		return right;
	}
	
	public void setElement(T x){
		this.element = x;
	}
	
	/**
	 * 递归方式求树中结点的个数
	 * @param t
	 * @return
	 */
	public static <T> int size(BinaryNode<T> t){
		if(t == null){
			return 0;
		}
		
		return size(t.left) + size(t.right) + 1;
		
	}
	
	/**
	 * 递归方式求树的高度
	 * @param t
	 * @return
	 */
	public static <T> int height(BinaryNode<T> t){
		if(t == null){
			return 0;
		}
		return Math.max(height(t.left), height(t.right)) + 1;
	}
	
	/**
	 * 复制以该结点为根结点的树
	 * @return
	 */
	public BinaryNode<T> duplicate(){
		BinaryNode<T> root = new BinaryNode<T>(element, null, null);
		if(left != null){
			root.left = left.duplicate();
		}
		
		if(right != null){
			root.right = right.duplicate();
		}
		return root;
	}
	
	/**
	 * 使用任何一种策略的简单遍历都需要线性时间
	 */
	public void printPreOrder(){
		
	}
	public void printInOrder(){
		
	}
	public void printPostOrder(){
		
	}
}
