package com.example;

import com.example.algorithms.KruskalAlgorithm;
import com.example.algorithms.PrimAlgorithm;
import com.example.graph.Edge;
import com.example.graph.Graph;
import com.example.graph.Result;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Чтение input JSON из ресурсов Maven
            InputStream inputStream = Main.class.getResourceAsStream("/graph_input.json");
            if (inputStream == null) {
                System.out.println("graph_input.json не найден!");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            JSONArray inputGraphs = new JSONArray(sb.toString());
            JSONArray resultsArray = new JSONArray();

            for (int i = 0; i < inputGraphs.length(); i++) {
                JSONObject graphJson = inputGraphs.getJSONObject(i);

                // Создание графа из JSON (используй существующий конструктор или метод)
                Graph graph = Graph.createFromJson(graphJson); // <-- поправь под свой метод

                // Прим
                PrimAlgorithm primAlg = new PrimAlgorithm(); // если у тебя без аргументов
                primAlg.setGraph(graph); // если есть метод для установки графа
                Result primResult = primAlg.computeMST(); // или как у тебя называется метод

                // Крускал
                KruskalAlgorithm kruskalAlg = new KruskalAlgorithm(); // если без аргументов
                kruskalAlg.setGraph(graph); // если есть
                Result kruskalResult = kruskalAlg.computeMST(); // или execute()

                // Подготовка JSON результата
                JSONObject graphResult = new JSONObject();
                graphResult.put("graph_id", i + 1);

                JSONObject inputStats = new JSONObject();
                inputStats.put("vertices", graph.getVertices().size());
                inputStats.put("edges", graph.getEdges().size());
                graphResult.put("input_stats", inputStats);

                graphResult.put("prim", convertResult(primResult));
                graphResult.put("kruskal", convertResult(kruskalResult));

                resultsArray.put(graphResult);
            }

            JSONObject output = new JSONObject();
            output.put("results", resultsArray);

            // Запись output.json
            try (FileWriter file = new FileWriter("src/main/resources/output.json")) {
                file.write(output.toString(2));
                file.flush();
                System.out.println("Output успешно сохранён в src/main/resources/output.json");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject convertResult(Result result) {
        JSONObject json = new JSONObject();
        JSONArray edges = new JSONArray();

        List<Edge> mstEdges = result.getMstEdges(); // метод getMstEdges() должен быть у Result
        for (Edge e : mstEdges) {
            JSONObject edgeJson = new JSONObject();
            edgeJson.put("from", e.getFrom());
            edgeJson.put("to", e.getTo());
            edgeJson.put("weight", e.getWeight());
            edges.put(edgeJson);
        }

        json.put("mst_edges", edges);
        json.put("total_cost", result.getTotalCost());
        json.put("operations_count", result.getOperationsCount());

        // Время выполнения: если нет поля, можно поставить заглушку
        if (result.getExecutionTimeMs() != null) {
            json.put("execution_time_ms", result.getExecutionTimeMs());
        } else {
            json.put("execution_time_ms", 1.0); // пример
        }

        return json;
    }
}
