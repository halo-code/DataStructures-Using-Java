package com.data.structure.graph;

import java.util.LinkedList;
import java.util.List;

public class Vertex {

	public String name;
	public List<Edge> adj;
	public double dist;
	public Vertex pre;
	public int scratch;

	public Vertex(String name) {
		this.name = name;
		adj = new LinkedList<Edge>();
		reset();
	}

	public void reset() {
		dist = Graph.INFINITY;
		pre = null;
		scratch = 0;
	}
}
