import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{

    public static int time = 0;
    private final List<List<String>> matrix;
    private String moves;
    private int cost;
    private int num;
    private boolean isGoal;
    private boolean isCutoff;
    private boolean out;
    private String lastStep;
    private int heuristic;
    private int last_x;
    private int last_y;
    private final int time_created;

    // constructor
    public Node(List<List<String>> mat) {
        matrix = new ArrayList<>(mat);
        moves = "";
        cost = 0;
        num =0;
        isGoal = false;
        isCutoff = false;
        out = false;
        lastStep = "";
        heuristic = 0;
        time++;
        time_created = time;

    }

    // constructor
    public Node() {
        matrix = new ArrayList<>();
        moves = "";
        cost = 0;
        num =0;
        isGoal = false;
        isCutoff = false;
        out = false;
        lastStep = "";
        heuristic = 0;
        time++;
        time_created = time;
    }

    // constructor
    public Node(Node n, List<List<String>> mat) {
        matrix = new ArrayList<>(mat);
        moves = n.moves;
        cost = n.cost;
        num =0;
        isGoal = false;
        isCutoff = false;
        out = false;
        lastStep = "";
        heuristic = 0;
        time++;
        time_created = time;
    }

    // return the node matrix
    public List<List<String>> getMatrix(){
        return this.matrix;
    }

    // Increasing the cost and adding a step to the string after a step was made
    public void add_move(int price, String step, String where, int x, int y) {
        this.lastStep = where;
        this.cost = cost+price;
        // save the location and name of the step, so we won't do the oposite
        last_x = x;
        last_y = y;
        if(moves.length()<2) {
            moves = moves + step;
        }
        else {
            moves = moves + "--" + step;
        }
    }

    // checking if it is possible to do step up and return the cost of the step
    public int step_up(int x, int y) {
        if(this.lastStep.equals("down") && last_x==x && last_y==y) {
            return 0;
        }

        if (this.matrix.get(x).get(y).equals("X") || this.matrix.get(x).get(y).equals("_")) {
            return 0;
        }
        else if(this.matrix.get(x).get(y).equals("R") && this.matrix.get((x+3-1)%3).get(y).equals("_")) {
            return 10;
        }
        else if(this.matrix.get(x).get(y).equals("G")&& this.matrix.get((x+3-1)%3).get(y).equals("_")) {
            return 3;
        }
        else if(this.matrix.get(x).get(y).equals("B")&& this.matrix.get((x+3-1)%3).get(y).equals("_")) {
            return 1;
        }
        return 0;
    }
    // checking if it is possible to do step down and return the cost of the step
    public int step_down(int x, int y) {
        if(this.lastStep.equals("up") && last_x==x && last_y==y) {
            return 0;
        }
        if (this.matrix.get(x).get(y).equals("X") || this.matrix.get(x).get(y).equals("_")) {
            return 0;
        }
        else if(this.matrix.get(x).get(y).equals("R") && this.matrix.get((x +1)%3).get(y).equals("_")) {
            return 10;
        }
        else if(this.matrix.get(x).get(y).equals("G")&& this.matrix.get((x +1)%3).get(y).equals("_")) {
            return 3;
        }
        else if(this.matrix.get(x).get(y).equals("B")&& this.matrix.get((x +1)%3).get(y).equals("_")) {
            return 1;
        }
        return 0;
    }
    // checking if it is possible to do step right and return the cost of the step
    public int step_right(int x, int y) {
        if(this.lastStep.equals("left") && last_x==x && last_y==y) {
            return 0;
        }
        if (this.matrix.get(x).get(y).equals("X") || this.matrix.get(x).get(y).equals("_")) {
            return 0;
        }
        else if(this.matrix.get(x).get(y).equals("R") && this.matrix.get(x).get((y+1)%3).equals("_")) {
            return 10;
        }
        else if(this.matrix.get(x).get(y).equals("G")&& this.matrix.get(x).get((y+1)%3).equals("_")) {
            return 3;
        }
        else if(this.matrix.get(x).get(y).equals("B")&& this.matrix.get(x).get((y+1)%3).equals("_")) {
            return 1;
        }
        return 0;
    }
    // checking if it is possible to do step left and return the cost of the step
    public int step_left(int x, int y) {
        if(this.lastStep.equals("right") && last_x==x && last_y==y) {
            return 0;
        }
        if (this.matrix.get(x).get(y).equals("X") || this.matrix.get(x).get(y).equals("_")) {
            return 0;
        }
        else if(this.matrix.get(x).get(y).equals("R") && this.matrix.get(x).get((y+3-1)%3).equals("_")) {
            return 10;
        }
        else if(this.matrix.get(x).get(y).equals("G")&& this.matrix.get(x).get((y+3-1)%3).equals("_")) {
            return 3;
        }
        else if(this.matrix.get(x).get(y).equals("B")&& this.matrix.get(x).get((y+3-1)%3).equals("_")) {
            return 1;
        }
        return 0;
    }

    // check if the matrix in the node equals to the matrix the function got
    public boolean is_equals(List<List<String>> other) {
        return this.matrix.equals(other);
    }

    // when the goal was found, so we insert the value of num and change the isGoal to be true
    public void found_goal(int n) {
        num = n;
        isGoal = true;
    }

    // return the moves string
    public String get_moves() {
        return this.moves;
    }

    // return the cost
    public int get_cost() {
        return this.cost;
    }

    // return the value of isCutoff
    public boolean get_isCutoff() {
        return isCutoff;
    }

    // change the value of isCutoff
    public void set_isCutoff(boolean temp) {
        this.isCutoff = temp;
    }

    // return the value of out
    public boolean get_out() {
        return out;
    }

    // mark the node an out
    public void mark_out() {
        this.out = true;
    }

    // return the f value (g+h)
    public int get_f() {
        return this.cost +this.heuristic;
    }

    @Override
    public int compareTo(Node other){
        // compare by f value
        int ans = Integer.compare(this.get_f(), other.get_f());
        // if the f values are the same so compare by the time that was created
        if(ans == 0){
            ans = Integer.compare(this.time_created, other.time_created);
        }
        return ans;
    }

    // set the heuristic value
    public void set_h(int h) {
        heuristic = h;
    }

}
