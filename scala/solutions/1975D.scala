import scala.collection.mutable
import scala.io.StdIn

object GraphBFS {
    
  implicit class RepeatOps(n: Int) { def repeat(block: => Unit): Unit = (0 until n).foreach { _ => block } }

  def readGraph(): (Int, Int, Array[List[Int]]) = {
    val n = StdIn.readInt()
    val Array(a, b) = StdIn.readLine().split(" ").map(_.toInt)
    val adjacencyList = Array.fill(n)(List[Int]())
    val numEdges = n - 1
    // println(n)
    for (_ <- 0 until numEdges) {
      var Array(u, v) = StdIn.readLine().split(" ").map(_.toInt)
      u = u - 1
      v = v - 1
      adjacencyList(u) = v :: adjacencyList(u)
      adjacencyList(v) = u :: adjacencyList(v)
    }
    (a - 1, b - 1, adjacencyList)
  }

  def bfs(graph: Array[List[Int]], startVertex: Int): (Array[Int], Int) = {
    val n = graph.length
    val visited = Array.fill(n){0}
    val queue = mutable.Queue[Int]()
    val distances = Array.fill(n){0}

    queue.enqueue(startVertex)
    visited(startVertex) = 1
    distances(startVertex) = 0

    var lastVisited = startVertex

    while (queue.nonEmpty) {
      val current = queue.dequeue()
      lastVisited = current

      for (neighbor <- graph(current)) {
        if (visited(neighbor) == 0) {
          queue.enqueue(neighbor)
          visited(neighbor) = 1
          distances(neighbor) = distances(current) + 1
        }
      }
    }

    (distances, lastVisited)
  }

  def main(args: Array[String]): Unit = {
    val t = readLine().trim.toInt
    t.repeat {
        val (a, b, graph) = readGraph()
        val(da, u) = bfs(graph, a);
        val(du, v) = bfs(graph, u);
        val(dv, _) = bfs(graph, v);
        val(db, _) = bfs(graph, b);
        
        val n = graph.length
        
        var out = 3 * n
        
        for (i <- 0 to (n - 1)){
            var y = da(i)
            var z = db(i)
            
            if (y % 2 != z % 2){
                y = y + 1
            }
            
            // val curr = (y.min(z)) + (n + n - max(du(i), dv(i)))
            val curr = (y.max(z)) + (n + n - 2 - (du(i).max(dv(i))))
            
            //println(List(y, z, du(i), dv(i)))
            
            out = out.min(curr)
        }
        
        println(out)
    }
  }
}
