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
	 * �ݹ鷽ʽ�����н��ĸ���
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
	 * �ݹ鷽ʽ�����ĸ߶�
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
	 * �����Ըý��Ϊ��������
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
	 * ʹ���κ�һ�ֲ��Եļ򵥱�������Ҫ����ʱ��
	 */
	public void printPreOrder(){
		
	}
	public void printInOrder(){
		
	}
	public void printPostOrder(){
		
	}
}
