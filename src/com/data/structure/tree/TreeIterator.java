package com.data.structure.tree;

import java.util.NoSuchElementException;

public abstract class TreeIterator<T> {
	
	protected BinaryTree<T> t;
	protected BinaryNode<T> current;
	
	public TreeIterator(BinaryTree<T> theTree){
		this.t = theTree;
		current = null;
	}
	
	/**
	 * ���Ե�ǰλ���Ƿ�ָ��һ������
	 * @return
	 */
	public boolean isValid(){
		return current != null;
	}
	
	/**
	 * ��ȡ��ǰλ�õ�Ԫ��
	 * @return
	 */
	final public T retrieve(){
		if(current == null){
			throw new NoSuchElementException();
		}
		return current.getElement();
	}
	
	abstract public void first();
	abstract public void advance();

}
