package com.data.structure.tree;

import java.util.NoSuchElementException;
import java.util.Stack;

public class PreOrder<T> extends TreeIterator<T> {

	private Stack<BinaryNode<T>> s;

	public PreOrder(BinaryTree<T> theTree) {
		super(theTree);
		this.s = new Stack<BinaryNode<T>>();
		s.push(theTree.getRoot());
		
	}

	@Override
	public void first() {

		s.clear();

		if (t.getRoot() != null) {
			s.push(t.getRoot());
			advance();
		}
	}

	@Override
	public void advance() {

		if (s.isEmpty()) {
			if (current == null) {
				throw new NoSuchElementException();
			}
			current = null;
			return;
		}

		current = s.pop();
		if (current.getRight() != null) {
			s.push(current.getRight());
		}
		if (current.getLeft() != null) {
			s.push(current.getLeft());
		}
	}

}
