package graphs;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src;
    int adj;
    int wt;

    Edge(int src, int adj, int wt) {
        this.src = src;
        this.adj = adj;
        this.wt = wt;
    }

    Edge(int src, int adj) {
        this.src = src;
        this.adj = adj;
        this.wt = 0;
    }

    public int compareTo(Edge o){
        return this.wt - o.wt;
    }

}

// utility class
class Pair {
    int vtx;
    String path;

    Pair(int vtx, String path) {
        this.vtx = vtx;
        this.path = path;
    }
    
}

class BiPair {
    int vtx;
    int lev;

    BiPair(int vtx, int lev) {
        this.vtx = vtx;
        this.lev = lev;
    }
}

class alPair implements Comparable<alPair>{
    int vtx;
    String path;
    int wsf;
       
    alPair(int vtx, String path, int wsf){
        this.vtx = vtx;
        this.path = path;
        this.wsf = wsf;
    }
       
    public int compareTo(alPair o){
        return this.wsf - o.wsf;
    }
}

class mstPair implements Comparable<mstPair>{
    int vtx;
    int pv;
    int wt;
       
    mstPair(int vtx, int pv, int wt){
        this.vtx = vtx;
        this.pv = pv;
        this.wt = wt;
    }
       
    public int compareTo(mstPair o){
        return this.wt - o.wt;
    }
}

// utility class

class graphList {

    ArrayList<Edge>[] graph;
    int vtx;
    int n_edge;

    graphList(int vtx, int n_edge) {
        graph = new ArrayList[vtx];

        this.vtx = vtx;
        this.n_edge = n_edge;

        for (int i = 0; i < vtx; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    void adddirect(int vertex, int neighbour, int wt) {
        graph[vertex].add(new Edge(vertex, neighbour, wt));
    }
    void adddirect(int vertex, int neighbour) {
        graph[vertex].add(new Edge(vertex, neighbour));
    }
    void addundirect(int vertex, int neighbour, int wt) {
        graph[vertex].add(new Edge(vertex, neighbour, wt));
        graph[neighbour].add(new Edge(neighbour, vertex, wt));
    }

    // DFS***
    private void getcomp(ArrayList<Edge> l, int src, boolean visit[], ArrayList<Integer> csf) {

        csf.add(src);
        visit[src] = true;
        for (Edge e : l) {
            if (!visit[e.adj]) {
                getcomp(graph[e.adj], e.adj, visit, csf);
            }
        }
    }

    boolean get_connected_vertex(int src) {
        boolean visit[] = new boolean[vtx];
        ArrayList<Integer> l = new ArrayList<>();
        getcomp(graph[src], src, visit, l);
        System.out.println(l);
        return true;
    }

    void hamilton(int src, boolean visit[], String psf, int count) {
        if (count == graph.length) {
            for (Edge e : graph[src]) {
                if (e.adj == 0) {
                    System.out.println(psf + "*");
                    return;
                }
            }

            System.out.println(psf + ".");
            return;
        }

        visit[src] = true;
        for (Edge e : graph[src]) {
            if (!visit[e.adj]) {
                hamilton(e.adj, visit, psf + e.adj, count + 1);
            }
        }
        visit[src] = false;
    }
    
    private void topo(int src, boolean visit[], stack<Integer> st){     

        visit[src] = true;
        for(Edge e : graph[src]){
            if(!visit[e.adj]){
                topo(e.adj, visit, st);
            }
        }
        st.push(src);
    }
    void toposort(){
        boolean visit[] = new boolean[vtx];
        stack<Integer> st = new stack<>();
        for(int i = 0; i < vtx; i++){
            if(!visit[i]){
                topo(i, visit, st);
            }
        }
        st.display();
        while(st.size() > 0){
            System.out.println(st.pop());
        }
    }

    
    // end DFS***

    // BFS st**
    void bfs(int src) {
        boolean visit[] = new boolean[vtx];
        Queuell<Pair> q = new Queuell<>();
        q.enqueue(new Pair(src, "" + src));

        while (!q.isempty()) {
            Pair val = q.dequeue();
            if (!visit[val.vtx]) {
                System.out.println(val.vtx + "@" + val.path);
                visit[val.vtx] = true;

                for (Edge e : graph[val.vtx]) {
                    if (!visit[e.adj]) {
                        q.enqueue(new Pair(e.adj, val.path + e.adj));
                    }
                }
            }
        }
    }

    boolean ispointcyclic(int src, boolean visit[]) {
        Queuell<Integer> q = new Queuell<>();
        q.enqueue(src);

        while (!q.isempty()) {
            int val = q.dequeue();
            if (visit[val])
                return true;

            if (!visit[val]) {
                visit[val] = true;

                for (Edge e : graph[val]) {
                    if (!visit[e.adj]) {
                        q.enqueue(e.adj);
                    }
                }
            }
        }
        return false;
    }

    boolean pointbipart(int src, int visit[]) {

        Queuell<BiPair> q = new Queuell<>();
        q.enqueue(new BiPair(src, 0));

        while (!q.isempty()) {
            BiPair val = q.dequeue();

            if (visit[val.vtx] != -1) {
                if (visit[val.vtx] == val.lev) {
                    return true;
                } else {
                    return false;
                }

            } else {
                visit[val.vtx] = val.lev;

                for (Edge e : graph[val.vtx]) {

                    if (visit[e.adj] == -1) {
                        q.enqueue(new BiPair(e.adj, val.lev + 1));
                    }
                }
            }
        }

        return true;
    }

    // BFS end***

    int [] DJ(int src){

        int weights[] = new int[vtx];
        String[] Path = new String[vtx];

        PriorityQueue <alPair> pq = new PriorityQueue<>();
        boolean visit[] = new boolean[vtx];

        pq.add(new alPair(src, "" + 0, 0));

        while(!pq.isEmpty()){
            alPair val = pq.poll();

            if(!visit[val.vtx]){

                visit[val.vtx] = true;
                weights[val.vtx] = val.wsf;
                Path[val.vtx] = val.path;

                for(Edge e: graph[val.vtx]){
                    if(!visit[e.adj]){
                        pq.add(new alPair(e.adj, val.path + e.adj, val.wsf + e.wt ));
                    }
                }
            }
        }

        // return path;
        return weights;


    }

    Edge [] prim(){

        boolean visit[] = new boolean[vtx];
        Edge mst [] = new Edge[vtx-1];
      
        PriorityQueue<mstPair> pq = new PriorityQueue<>();

        pq.add(new mstPair(0, -1, 0));
        
        int j = 0;
        while(pq.size()>0){
            
            mstPair val = pq.poll();
            
            if(!visit[val.vtx]){
                if(val.pv >= 0){
                    mst[j] = new Edge(val.pv, val.vtx, val.wt);
                    System.out.println("[" + val.vtx + "-" + val.pv + "@" + val.wt + "]");
                    j++;
                    
                }
                visit[val.vtx] = true;

                for(Edge e: graph[val.vtx]){
                    if(!visit[e.adj]){
                        pq.add(new mstPair(e.adj, val.vtx, e.wt));
                    }
                }
            }
        }
        mstprint(mst);
        return mst;
    }




    // union find strt*******

    int find(int v, int parent[]){
        if(parent[v] == -1)
            return v;
        return find(parent[v], parent);
    }

    void union(int e1, int e2, int parent[]){
        int f = find(e1, parent);
        int t = find(e2, parent);
        parent[f] = t;
        
        // for(int i : parent){
        //     System.out.print(i + " ");
        // }
        // System.out.println();
    }

    boolean iscyclic(Edge mst[], int parent[], int size){
        for(int i = 0; i < size+1; i++){

            int e1 = find(mst[i].src,parent);
            int e2 = find(mst[i].adj,parent);
            
            if(e1 == e2)
                return false;    

            union(e1, e2, parent);
            
        }

        return true;
    }

    // unionfind ened*******

    Edge [] krushkal(Edge edge_list []){

        int parent[] = new int[vtx];

        Edge mst[] = new Edge[vtx-1];
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for(Edge e : edge_list){
            pq.add(e);
        }

        for(int i = 0; i < vtx; i++){
            parent[i] = -1;
        }

        int j = 0;
        while(j < n_edge && !pq.isEmpty()){

            Edge e = pq.remove();

            int s = find(e.src, parent);
            int d = find(e.adj, parent);

            if(s != d){
                union(s,d, parent);
                mst[j] = e;
                j++;
            }
        }

        mstprint(mst);
        return mst;
    }

    void mstprint(Edge mst[]){
        for(Edge e : mst){
            System.out.println(e.src + "-->"+ e.adj  + "@" + e.wt);
        }
    }

    void display() {
        System.err.println();
        for (ArrayList<Edge> a1 : graph) {
            System.out.print("[");
            for (Edge e : a1) {
                System.out.print("[" + e.src + "-->" + e.adj + " " + e.wt + "], ");
            }
            System.out.println("]");
        }
    }
}
