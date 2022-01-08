package graphs;

import java.io.*;


public class dfs {

    public static void main(String[] args) throws IOException {

        Reader.init(System.in);

        int vtx = Reader.nextint(), edges = Reader.nextint();

        graphList gr = new graphList(vtx, edges);

        for(int i = 0; i < edges; i++){
            int v1 = Reader.nextint(), v2 = Reader.nextint(), wt = Reader.nextint();
            gr.addundirect(v1, v2, wt);
        }

        boolean visit[] = new boolean[vtx];
        gr.hamilton(0, visit, "" + 0, 1);
        
    }

}
