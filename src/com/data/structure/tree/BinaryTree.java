package com.data.structure.tree;

public class BinaryTree<T> {

	private BinaryNode<T> root;
	public BinaryTree() {
		root = null;
	}
	public BinaryTree(T rootItem) {
		this.root = new BinaryNode<T>(rootItem, null, null);
	}
	
	
	public BinaryNode<T> getRoot() {
		return root;
	}
	
	public int size(){
		return BinaryNode.size(root);
	}
	
	public int height(){
		return BinaryNode.height(root);
	}
	
	public void printPreOrder(){
		if(root != null){
			root.printPreOrder();
		}
	}
	
	public void printInOrder(){
		if(root != null){
			root.printInOrder();
		}
	}
	
	public void printPostOrder(){
		if(root != null){
			root.printPostOrder();
		}
	}
	
	public void makeEmpty(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void merge(T rootItem, BinaryTree<T> t1, BinaryTree<T> t2){
		//���ﲢ���Ǽ򵥵�root = new BinaryNode<T>(rootItem, t1, t2);��ô�򵥣���Ϊ����Ҫ���Ǻϲ�֮�������ԭ���������ǹ������
		if(t1.root == t2.root && t1.root != null){
			throw new IllegalArgumentException();
		}
		root = new BinaryNode<T>(rootItem, t1.root, t2.root);
		
		if(this != t1){
			t1.root = null;
		}
		
		if(this != t2){
			t2.root = null;
		}
	}
	
	
}
