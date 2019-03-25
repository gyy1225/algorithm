package A;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BellmanFord {


        public static void main(String[] args) {
            List<Vertex> list = getTestData();
            // 设置第0个顶点为起始点
            Vertex source = list.get(0);
            source.setDistance(0);
            // 贝文曼福德算法
            boolean flag = bellmanFord(list, source);
            // 打印结果
            System.out.println("是否存在解决方案：" + flag);
            for (int i = 0; i < list.size(); i++) {
                Vertex vertex = list.get(i);
                System.out.println(vertex.toString());
            }
            list=getSolution(list);
            for (int i = 0; i < list.size(); i++) {
                Vertex vertex = list.get(i);
                System.out.print(vertex.getId()+"  ");
            }
        }

        public static boolean bellmanFord(List<Vertex> list, Vertex source) {
            // 1. 将所有边添加到一个队列中
            LinkedList<Edge> queue = new LinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                queue.addAll(list.get(i).getNeighbors());
            }
            // 2. 需要执行 (V-1)*E 次松弛操作
            for (int i = 1; i < list.size(); i++) {
                for (Edge edge : queue) {
                    relax(edge);
                }
            }
            // 3. 验证是否存在权重为负数的环路
            for (Edge edge : queue) {
                Vertex u = edge.getStart();
                Vertex v = edge.getEnd();
                // 对于边 (u, v)，如果 v.distance > u.distance + weight，则说明是负数环路
                if (v.getDistance() > u.getDistance() + edge.getWeight()) {
                    return false;
                }
            }
            return true;
        }

        public static void relax(Edge edge) {
            Vertex start = edge.getStart();
            Vertex end = edge.getEnd();
            int distance = start.getDistance() + edge.getWeight();
            if (end.getDistance() > distance) {
                end.setDistance(distance);
                end.setParent(start);
            }
        }

        public static List<Vertex> getTestData() {
            Vertex s = new Vertex('s');
            Vertex t = new Vertex('t');
            Vertex x = new Vertex('x');
            Vertex y = new Vertex('y');
            Vertex z = new Vertex('z');
            s.addNeighbor(t, 6); // s->t : 6
            s.addNeighbor(y, 7); // s->y : 7
            t.addNeighbor(x, 5); // t->x : 5
            t.addNeighbor(y, 8); // t->y : 8
            t.addNeighbor(z, -4); // t->z : -4
            x.addNeighbor(t, -2); // x->t : -2
            y.addNeighbor(x, -3); // y->x : -3
            y.addNeighbor(z, 9); // y->z : 9
            z.addNeighbor(s, 2); // z->s : 2
            z.addNeighbor(x, 7); // z->x : 7
            LinkedList<Vertex> list = new LinkedList<>();
            list.add(s);
            list.add(t);
            list.add(x);
            list.add(y);
            list.add(z);
            return list;
        }

        public static List<Vertex> getSolution(List<Vertex> list){
            List<Vertex> solution=new ArrayList<>();

            Iterator<Vertex> iterator = list.iterator();
            while(iterator.hasNext()){
                Vertex v = iterator.next();
                if(v.getId()=='s') {
                    solution.add(v);
                    iterator.remove();   //注意这个地方
                }
            }
           /* for (Vertex v :list) {
                if (v.getId()=='s') {
                    solution.add(v);
                    list.remove(v);
                }
            }*/

           for (int i=1;i<=list.size();i++) {
               //   while(iterator.hasNext()){
               //    Vertex v = iterator.next();
               for (Vertex v : list) {
                   if (v.getParent().getId() == solution.get(i - 1).getId()) {
                       solution.add(v);
                   }
               }
           }
           /* for (Vertex v :list) {
                if (v.getParent().getId()==solution.get(1).getId()) {
                    solution.add(v);
                }
            }*/
            return solution;
        }
    }


