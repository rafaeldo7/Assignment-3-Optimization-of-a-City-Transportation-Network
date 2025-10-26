# Assignment-3-Optimization-of-a-City-Transportation-Network
Prim's vs. Kruskal's Algorithm Comparison

This Java project implements and compares the performance of two classic algorithms for finding a Minimum Spanning Tree (MST): Prim's Algorithm and Kruskal's Algorithm.

The project reads a set of graphs from a JSON file, runs both algorithms on each graph, and then outputs detailed performance results (execution time, operation count) to a separate JSON file for analysis.

🚀 Tech Stack

    Java: The core programming language.

    Maven: Used for dependency management and building the project.

    org.json (json-java library): Used for parsing the input JSON data.

⚙️ How to Run

Prerequisites

    JDK (Java Development Kit) (e.g., OpenJDK 17+).

    Apache Maven.

Execution

The project is configured to be run using the Maven Exec Plugin.

    Ensure your input graph file (all_graphs_array.json or graph_input(1).json) is in the correct location (likely the project root or src/main/resources).

    From the project's root directory, run the following command:
    Bash

    rafael@fedora:~/MST_Assignment$ mvn exec:java -Dexec.mainClass="com.example.Main"

    After execution, an output.json file containing the results will appear in the project directory.

📁 Data Formats

Input File (e.g., graph_input(1).json)

The program expects an input JSON file that is a JSON Array [ ... ], where each element is a graph object.
JSON

[
  {
    "id": 1,
    "nodes": [
      "N0",
      "N1",
      "N2",
      "..."
    ],
    "edges": [
      {
        "from": "N7",
        "to": "N10",
        "weight": 73
      },
      // ... other edges
    ]
  },
  {
    "id": 2,
    // ...
  }
  // ... and so on for all 30 graphs
]

Output File (output.json)

The program generates a JSON file containing a results object. This object is an array where each element corresponds to an input graph and holds the performance data for both algorithms.
JSON

{
  "results": [
    {
      "prim": {
        "mst_edges": [
          // ... list of edges in the MST ...
        ],
        "total_weight": 582,
        "time_ms": 1.22,
        "operation_count": 40
      },
      "kruskal": {
        "mst_edges": [
          // ... list of edges in the MST ...
        ],
        "total_weight": 582,
        "time_ms": 0.59,
        "operation_count": 73
      },
      "graph_id": 1,
      "nodes": 14,
      "edges": 23
    },
    // ... other results
  ]
}
