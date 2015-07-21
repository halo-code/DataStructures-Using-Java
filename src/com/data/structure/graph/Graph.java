package com.data.structure.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 邻接表方式表示的图
 * 
 * @author liumiao
 * 
 */
public class Graph {

	public static final double INFINITY = Double.MAX_VALUE;

	public Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

	/**
	 * 获取一个顶点
	 * 
	 * @param name
	 *            顶点的名称
	 * @return 顶点
	 */
	public Vertex getVertex(String name) {
		Vertex v = vertexMap.get(name);
		if (v == null) {
			v = new Vertex(name);
			vertexMap.put(name, v);
		}
		return v;
	}

	/**
	 * 添加一个边
	 * 
	 * @param sourceName
	 *            起始顶点的名称
	 * @param destName
	 *            目的顶点的名称
	 * @param cost
	 *            代价
	 */
	public void addEdge(String sourceName, String destName, double cost) {
		Vertex source = getVertex(sourceName);
		Vertex dest = getVertex(destName);
		source.adj.add(new Edge(dest, cost));
	}

	/**
	 * 递归的打印路径
	 * 
	 * @param dest
	 */
	private void printPath(Vertex dest) {
		if (dest.pre != null) {
			printPath(dest.pre);
			System.out.println("to");
		}
		System.out.println(dest.name);
	}

	/**
	 * 打印名称为destName的顶点的路径
	 * 
	 * @param destName
	 */
	public void printPath(String destName) {
		Vertex v = vertexMap.get(destName);
		if (v == null) {
			throw new NoSuchElementException();
		} else if (v.dist == Graph.INFINITY) {
			System.out.println(destName + "is unreachable");
		} else {
			printPath(v);
			System.out.println();
		}
	}

	private void clearAll() {
		for (Vertex v : vertexMap.values()) {
			v.reset();
		}
	}

	/**
	 * 无权最短路径计算 广度优先搜索按层处理每层的顶点 队列
	 * 
	 * @param startName
	 *            开始顶点的名字
	 */
	public void unwight(String startName) {
		clearAll();
		Vertex start = vertexMap.get(startName);
		if (start == null) {
			throw new NoSuchElementException();
		} else {

			Queue<Vertex> queue = new LinkedList<Vertex>();
			start.dist = 0;
			queue.add(start);

			while (!queue.isEmpty()) {

				Vertex vertex = queue.remove();

				for (Edge e : vertex.adj) {
					Vertex v = e.dest;
					// 只对没有计算过路径的顶点赋值即可，这里是重点，也是不同于其他路径求解的地方
					if (v.dist == Graph.INFINITY) {
						v.dist = vertex.dist + 1;
						v.pre = vertex;
						queue.add(v);
					}
				}
			}

		}
	}

	/**
	 * 非负权值的最短路径问题，也就是Dijkstra算法 利用优先队列 理论支持，每个顶点只做一次眼球
	 * 
	 * @param startName
	 */
	public void dijkstra(String startName) {
		Vertex start = vertexMap.get(startName);
		if (start == null) {
			throw new NoSuchElementException();
		} else {
			// 这里为什么存储的是path，而不是顶点
			PriorityQueue<Path> queue = new PriorityQueue<Path>();
			queue.add(new Path(start, 0));
			int nodeSeen = 0;

			while (!queue.isEmpty() && nodeSeen < vertexMap.size()) {

				Path path = queue.remove();
				Vertex v = path.dest;

				if (v.scratch == 1) {
					// 已经被当做过眼球
					continue;
				}

				v.scratch = 1;
				nodeSeen++;

				for (Edge e : v.adj) {
					Vertex dest = e.dest;
					double tmpCost = e.cost + v.dist;
					if (tmpCost < dest.dist) {
						dest.dist = tmpCost;
						dest.pre = v;
						queue.add(new Path(dest, dest.dist));
					}
				}

			}
		}
	}

	/**
	 * 有向无环图的最短路径 拓扑排序 有负权值的边仍然正确 scratch记录的是入度
	 * @throws GraphException 
	 */
	public void acyclic(String startName) throws GraphException {
		Vertex start = vertexMap.get(startName);
		if (start == null) {
			throw new NoSuchElementException();
		}

		else {
			clearAll();
			Queue<Vertex> q = new LinkedList<Vertex>();
			start.dist = 0;

			// 计算入度
			Collection<Vertex> vertexSet = vertexMap.values();
			for (Vertex v : vertexSet) {
				for (Edge e : v.adj) {
					e.dest.scratch++;
				}
			}

			// 把入度为0的节点放入队列中
			for (Vertex v : vertexSet) {
				if (v.scratch == 0) {
					q.add(v);
				}
			}

			int iterations = 0;
			for (iterations = 0; !q.isEmpty(); iterations++) {
				Vertex v = q.remove();

				for (Edge e : v.adj) {
					Vertex dest = e.dest;
					double cost = e.cost;

					if (--dest.scratch == 0) {
						q.add(dest);
					}

					
					//处理不在联通分量里面的时候，因为它的入度也是0
					if (v.dist == Graph.INFINITY) {
						continue;
					}

					if (dest.dist > v.dist + cost) {
						dest.dist = v.dist + cost;
						dest.pre = v;
					}
				}
			}

			if (iterations != vertexSet.size()) {
				throw new GraphException("Graph has a cycle");
			}

		}
	}

	private class GraphException extends Exception {

		public GraphException(String name) {
			super(name);
		}

	}

}
