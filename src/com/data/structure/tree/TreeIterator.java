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
	 * 测试当前位置是否指向一个对象
	 * @return
	 */
	public boolean isValid(){
		return current != null;
	}
	
	/**
	 * 获取当前位置的元素
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
