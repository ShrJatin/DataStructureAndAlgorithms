package SearchTree;

public class run {
    public static void main(String[] args) {

        Integer arr[] = {50,40,35,12,36,45,42,60,55,57,65};
        
        BST st = new BST();
        
        for(int i : arr){
            st.add(i);
        }
        st.display();
        st.remove(35);
        System.out.println();
        st.display();
    }
}
