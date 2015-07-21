package com.data.structure.graph;

/**
 * ʵ����camparable�ӿڵ�·��
 * 
 * @author liumiao
 * 
 */
public class Path implements Comparable<Path> {

	public Vertex dest;
	public double cost;

	public Path(Vertex dest, double cost) {
		super();
		this.dest = dest;
		this.cost = cost;
	}

	@Override
	public int compareTo(Path p) {
		return this.cost < p.cost ? -1 : (this.cost > p.cost ? 1 : 0);
	}

}
