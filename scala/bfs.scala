object GraphBFS {
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
}