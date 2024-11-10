package Beans

class Path {
    var paths: List<String> = emptyList()

    constructor(paths: List<String>) {
        this.paths = paths
    }
}

data class Prim(
    val path: List<String>,
    val cost: Double
)