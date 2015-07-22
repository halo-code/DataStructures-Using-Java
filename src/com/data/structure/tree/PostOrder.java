package com.data.structure.tree;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * �ǵݹ���������������
 * 
 * @author liumiao
 * 
 * @param <T>
 */
public class PostOrder<T> extends TreeIterator<T> {

	protected Stack<StNode<T>> s;

	static class StNode<T> {

		public StNode(BinaryNode<T> node) {
			super();
			this.node = node;
			this.timePopped = 0;
		}

		BinaryNode<T> node;
		int timePopped;
	}

	public PostOrder(BinaryTree<T> theTree) {
		super(theTree);
		s = new Stack<StNode<T>>();
		s.push(new StNode<>(t.getRoot()));
	}

	@Override
	public void first() {
		s.clear();

		if (t.getRoot() != null) {
			s.push(new StNode<>(t.getRoot()));
			advance();
		}
	}

	/**
	 * �ǵݹ�ĺ������ �����γ�ջ���Ƿ��ʸý��
	 * 
	 */
	@Override
	public void advance() {
		if (s.isEmpty()) {
			if (current == null) {
				throw new NoSuchElementException();
			}
			current = null;
			return;
		}

		StNode<T> cnodes;
		for (;;) {
			cnodes = s.pop();
			if (++cnodes.timePopped == 3) {
				current = cnodes.node;
				return;
			}
			// ��ѹ��ȥ
			s.push(cnodes);
			if (cnodes.timePopped == 1) {
				if (cnodes.node.getLeft() != null) {
					s.push(new StNode<T>(cnodes.node.getLeft()));
				}
			} else {
				// cnodes.timePopped == 2
				if (cnodes.node.getRight() != null) {
					s.push(new StNode<T>(cnodes.node.getRight()));
				}
			}
		}
	}

}
