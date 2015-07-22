package com.data.structure.tree;

import java.util.NoSuchElementException;

/**
 * 非递归中序遍历迭代器类
 * @author liumiao
 *
 * @param <T>
 */

public class InOrder<T> extends PostOrder<T>{

	public InOrder(BinaryTree<T> theTree) {
		super(theTree);
	}

	@Override
	public void advance() {
		
		if(s.empty()){
			if(current == null){
				throw new NoSuchElementException();
			}
			current = null;
			return;
		}
		
		StNode<T> cnodes;
		for(;;){
			cnodes = s.pop();
			if(++ cnodes.timePopped == 2){
				current = cnodes.node;
				
				if(cnodes.node.getRight() != null){
					s.push(new StNode<T>(cnodes.node.getRight()));
				}
				return;
			}
			
			//++cnode.timePopped == 1
			s.push(cnodes);
			
			if(cnodes.node.getLeft() != null){
				s.push(new StNode<T>(cnodes.node.getLeft()));
			}
			
		}
		
		
	}

}
