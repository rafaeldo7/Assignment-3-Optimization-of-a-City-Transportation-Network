import json, random, string, os

random.seed(42)

os.makedirs("src/main/resources", exist_ok=True)

def gen_nodes(n):
    if n <= 26:
        return list(string.ascii_uppercase)[:n]
    else:
        return [f"N{i+1}" for i in range(n)]

def gen_edges(nodes, edge_count, min_w=1, max_w=100):
    edges = set()
    max_possible = len(nodes) * (len(nodes) - 1) // 2
    edge_count = min(edge_count, max_possible)
    while len(edges) < edge_count:
        a, b = random.sample(nodes, 2)
        edges.add(tuple(sorted((a, b))))
    return [{"from": a, "to": b, "weight": random.randint(min_w, max_w)} for a, b in edges]

def make_graphs(category, count, min_nodes, max_nodes, density_factor):
    graphs = []
    for i in range(count):
        n = random.randint(min_nodes, max_nodes)
        nodes = gen_nodes(n)
        max_edges = n * (n - 1) // 2
        e = min(max_edges, int(n * density_factor))
        edges = gen_edges(nodes, e)
        graphs.append({
            "id": None,
            "category": category,
            "nodes": nodes,
            "edges": edges
        })
    return graphs

# Генерация всех категорий
graphs = []
graphs += make_graphs("small", 5, 5, 30, 2.5)
graphs += make_graphs("medium", 10, 50, 300, 2.0)
graphs += make_graphs("large", 10, 400, 1000, 1.5)
graphs += make_graphs("extra", 5, 1200, 3000, 1.2)

# Пронумеровываем id
for idx, g in enumerate(graphs, start=1):
    g["id"] = idx

data = {"graphs": graphs}

# Сохраняем файл
with open("src/main/resources/graph_input.json", "w", encoding="utf-8") as f:
    json.dump(data, f, indent=2, ensure_ascii=False)

print("✅ Файл успешно создан: src/main/resources/graph_input.json")
print(f"Всего графов: {len(graphs)}")
for cat in ["small", "medium", "large", "extra"]:
    print(f"{cat}: {len([g for g in graphs if g['category']==cat])}")
