package com.data.structure.graph;

public class Edge {

	public Vertex dest;
	public double cost;

	public Edge(Vertex dest, double cost) {
		super();
		this.dest = dest;
		this.cost = cost;
	}

}
