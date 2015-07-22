package com.data.structure.tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LevelOrder<T> extends TreeIterator<T> {

	private Queue<BinaryNode<T>> q;

	public LevelOrder(BinaryTree<T> theTree) {
		super(theTree);
		q = new LinkedList<BinaryNode<T>>();
		q.add(theTree.getRoot());
	}

	@Override
	public void first() {

		q.clear();

		if (t.getRoot() != null) {
			q.add(t.getRoot());
			advance();
		}

	}

	@Override
	public void advance() {

		if (q.isEmpty()) {
			if (current == null) {
				throw new NoSuchElementException();
			}
			current = null;
			return;
		}

		current = q.remove();
		if (current.getLeft() != null) {
			q.add(current.getLeft());
		}
		if (current.getRight() != null) {
			q.add(current.getRight());
		}
	}

}
