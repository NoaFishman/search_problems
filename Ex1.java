import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Ex1 {

    public static int num = 0; // this is a global because the recursive functions

    public static void main(String[] args) {
        String filePath = "input.txt"; // Update with your file's path

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Path.of(filePath));

            // Extract data based on the structure of the file
            String algorithm = lines.get(0).trim(); // First line: Algorithm name
            boolean withTime = lines.get(1).trim().equalsIgnoreCase("with time"); // Second line: Time flag
            boolean withOpen = lines.get(2).trim().equalsIgnoreCase("with open"); // Third line: Open flag


            // Fourth to sixth lines: Start state matrix
            List<List<String>> startMatrix = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String line = lines.get(3 + i).trim();
                List<String> row = List.of(line.split(","));
                startMatrix.add(row);
            }

            // Eighth to tenth lines: Goal state matrix
            List<List<String>> goalMatrix = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String line = lines.get(7 + i).trim();
                List<String> row = List.of(line.split(","));
                goalMatrix.add(row);
            }

            Node start = new Node(startMatrix);

            String fileOutPath = "output.txt"; // Specify the file path

            switch (algorithm) {
                case "BFS" -> {
                    long startTime = System.nanoTime();
                    Node n = BFS(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds

                    int cost = n.get_cost();

                    // check if there is solution or not
                    if(cost != 0){
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write(n.get_moves());
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: " + cost);
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }
                    else{
                        // there is no solution
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write("no path");
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: inf");
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }

                }
                case "DFID" -> {
                    long startTime = System.nanoTime();

                    Node n = DFID(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds

                    int cost = n.get_cost();

                    // check if there is solution or not
                    if(cost != 0){
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write(n.get_moves());
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: " + cost);
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }
                    // there is no solution
                    else{
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write("no path");
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: inf");
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }

                }
                case "A*" -> {
                    long startTime = System.nanoTime();

                    Node n = AStar(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds

                    int cost = n.get_cost();

                    // check if there is solution or not
                    if(cost != 0){
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write(n.get_moves());
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: " + cost);
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }
                    // there is no solution
                    else{
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write("no path");
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: inf");
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }
                }
                case "IDA*" -> {
                    long startTime = System.nanoTime();

                    Node n = IDA(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds

                    int cost = n.get_cost();

                    // check if there is solution or not
                    if(cost != 0){
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write(n.get_moves());
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: " + cost);
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }
                    // there is no solution
                    else{
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write("no path");
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: inf");
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }

                }
                case "DFBnB" -> {

                    long startTime = System.nanoTime();

                    Node n = DFBnB(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds


                    int cost = n.get_cost();

                    // check if there is solution or not
                    if(cost != 0){
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write(n.get_moves());
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: " + cost);
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }
                    // there is no solution
                    else{
                        try (FileWriter writer = new FileWriter(fileOutPath)) {
                            // Write content to the file
                            writer.write("no path");
                            writer.write("\nNum: " + num);
                            writer.write("\nCost: inf");
                            if (withTime) {
                                String formattedTime = String.format("%.3f", elapsedTime);
                                writer.write("\n" + formattedTime + " seconds");
                            }

                        } catch (IOException e) {
                            System.err.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                    }
                }
            }
        // errors with the file
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error parsing the file: File format may be incorrect.");
        }

    }

    // this function is printing the matrix (the board)
    private static void printMatrix(List<List<String>> matrix) {
        for (List<String> row : matrix) {
            System.out.println(row);
        }
    }

    // BFS algorithm
    public static Node BFS(Node n, List<List<String>> goal, boolean open) {

        // creating all the necessary structure
        Queue<Node> L = new LinkedList<>();
        Hashtable<List<List<String>>, Node> Lhash = new Hashtable<>();
        Hashtable<List<List<String>>, Node> hash = new Hashtable<>();

        // add the first node to the lists
        L.add(n);
        Lhash.put(n.getMatrix(),n);

        while(!L.isEmpty()) {

            // print the open list if the input said so
            if(open){
                System.out.println("Printing open list:");
                for (Node node : L) {
                    System.out.println();
                    printMatrix(node.getMatrix());
                }
            }
            Node currNode = L.poll();
            Lhash.remove(currNode.getMatrix());
            hash.put(currNode.getMatrix(), currNode);
            // run trow all the possible next steps
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int x;

                    // step up checking
                    x = currNode.step_up(i, j);
                    if(x!=0) {
                        // if possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(currNode.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get((i+3-1)%3).set(j, ball) ;
                        Node newNode = new Node(currNode, temp);
                        num++; // count creating node
                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+3-1)%3+1)+","+(j+1)+")";
                            newNode.add_move(x, step, "up", ((i+3-1)%3), j);
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            // add to the list so will be process
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
                        }
                    }

                    // step down checking
                    x = currNode.step_down(i, j);
                    if(x!=0) {
                        // if possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(currNode.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get((i+1)%3).set(j, ball);
                        Node newNode = new Node(currNode, temp);
                        num++; // count creating node
                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+1)%3+1)+","+(j+1)+")";
                            newNode.add_move(x, step, "down", ((i+1)%3), j);
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            // add to the list so will be process
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
                        }
                    }

                    // step right checking
                    x = currNode.step_right(i, j);
                    if(x!=0) {
                        // if possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(currNode.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get(i).set(((j +1)%3), ball);
                        Node newNode = new Node(currNode, temp);
                        num++; // count creating node
                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j +1)%3+1)+")";
                            newNode.add_move(x, step, "right", i, ((j +1)%3));
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            // add to the list so will be process
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
                        }
                    }

                    // step left checking
                    x = currNode.step_left(i, j);
                    if(x!=0) {
                        // if possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(currNode.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get(i).set(((j +3-1)%3), ball) ;
                        Node newNode = new Node(currNode, temp);
                        num++; // count creating node
                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+3-1)%3+1)+")";
                            newNode.add_move(x, step, "left", i, ((j +3-1)%3));
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            // add to the list so will be process
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
                        }
                    }
                }
            }
        }
        return n;
    }

    // DFID algorithm
    public static Node DFID(Node n, List<List<String>> goal, boolean open) {

        // start with 1=1
        int depth = 1;

        while(depth > 0) {
            Hashtable<List<List<String>>, Node> hash = new Hashtable<>();
            Node result = Limited_DFS(n, goal, depth, hash, open);
            depth++;
            if(result.is_equals(goal)) {
                return  result;
            }
            // stop if there is no solution
            if(num >= 362880){
                // 9! = 362880 is the max num of matrix that we can creat (each "R" is different from the other...)
                return n;
            }
        }
        return n;
    }

    // the second function of DFID algorithm
    public static Node Limited_DFS(Node n, List<List<String>> goal, int limit, Hashtable<List<List<String>>, Node> hash, boolean open) {

        // check if found the goal
        if(n.is_equals(goal)) {
            n.found_goal(num);
            return n;
        }

        // check if got to the mav depth this iteration
        else if(limit == 0){
            n.set_isCutoff(true);
            return n;
        }

        // check the next staeps
        else {
            List<List<String>> mat = n.getMatrix();
            hash.put(mat, n);
            boolean isCutoff = false;

            // run throw all the options t the next step
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    int x;
                    // step up checking
                    x = n.step_up(i, j);
                    if(x != 0) {
                        // if  possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(n.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get((i+3-1)%3).set(j, ball);
                        Node g = new Node(n, temp);
                        num++;
                        if(!hash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+3-1)%3+1)+","+(j+1)+")";
                            g.add_move(x, step, "up", ((i+3-1)%3), j);
                            Node result = Limited_DFS(g, goal, limit-1, hash, open);

                            // check if found the goal
                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
                            // check if this is the goal
                            else if (result.is_equals(goal)) {
                                result.found_goal(num);
                                return result;
                            }
                        }
                    }

                    // step down checking
                    x = n.step_down(i, j);
                    if(x != 0) {
                        // if  possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(n.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get((i+1)%3).set(j, ball);
                        Node g = new Node(n, temp);
                        num++;
                        if(!hash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+1)%3+1)+","+(j+1)+")";
                            g.add_move(x, step, "down", ((i+1)%3), j);
                            Node result = Limited_DFS(g, goal, limit-1, hash, open);
                            // check if found the goal
                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
                            // check if this is the goal
                            else if (result.is_equals(goal)) {
                                result.found_goal(num);
                                return result;
                            }
                        }
                    }

                    // step right checking
                    x = n.step_right(i, j);
                    if(x != 0) {
                        // if  possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(n.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get(i).set((j+1)%3, ball);
                        Node g = new Node(n, temp);
                        num++;
                        if(!hash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+1)%3+1)+")";
                            g.add_move(x, step, "right", i, ((j +1)%3));
                            Node result = Limited_DFS(g, goal, limit-1, hash, open);
                            // check if found the goal
                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
                            // check if this is the goal
                            else if (result.is_equals(goal)) {
                                result.found_goal(num);
                                return result;
                            }
                        }
                    }

                    // step left checking
                    x = n.step_left(i, j);
                    if(x != 0) {
                        // if  possible to do the step it will return the cost of the step and do whatever needed
                        List<List<String>> curr = new ArrayList<>(n.getMatrix());
                        List<List<String>> temp = new ArrayList<>();
                        for (List<String> row : curr) {
                            temp.add(new ArrayList<>(row)); // Copy each inner list
                        }
                        String ball = temp.get(i).get(j);
                        temp.get(i).set(j, "_");
                        temp.get(i).set((j+3-1)%3, ball);
                        Node g = new Node(n, temp);
                        num++;
                        if(!hash.containsKey(temp)) {
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+3-1)%3+1)+")";
                            g.add_move(x, step, "left", i, ((j +3-1)%3));
                            Node result = Limited_DFS(g, goal, limit-1, hash, open);
                            // check if found the goal
                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
                            // check if this is the goal
                            else if (result.is_equals(goal)) {
                                result.found_goal(num);
                                return result;
                            }
                        }
                    }
                }
            }
            // print the open list if the input said so
            if(open){
                System.out.println("Printing open list:");
                for (List<List<String>> key : hash.keySet()) {
                    printMatrix(key);
                    System.out.println();
                }
            }
            hash.remove(n.getMatrix(), n);
            if(isCutoff) {
                return n;
            }
        }
        return n;
    }

    // DFBnB algorithm
    public static Node DFBnB(Node start, List<List<String>> goal, boolean open) {

        // creating all the necessary structure
        Stack<Node> stack = new Stack<>();
        Hashtable<List<List<String>>, Node> hash = new Hashtable<>();

        // calculate the heuristic and declare the threshold to be max int
        start.set_h(h(start, goal));
        stack.push(start);
        hash.put(start.getMatrix(), start);
        int t = Integer.MAX_VALUE;
        Node result = new Node();

        while(!stack.isEmpty()) {
            // if there is no solution so after creating more nodes than the number of optional nodes we should stop
            if(num >= 362880){
                // 9! = 362880 is the max num of matrix that we can creat (each "R" is different from the other...)
                return start;
            }
            // printing the open list if the input said so
            if(open){
                System.out.println("Printing open list:");
                for (Node node : stack) {
                    System.out.println();
                    printMatrix(node.getMatrix());
                }
            }
            // take out the next node to process
            Node n = stack.pop();
            // check if already was processed
            if(n.get_out()) {
                hash.remove(n.getMatrix());
            }
            else {
                // mark as processed
                n.mark_out();
                stack.push(n);
                ArrayList<Node> N = new ArrayList<>();

                // run throw all the possible next steps
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {

                        int x;
                        // step up checking
                        x = n.step_up(i, j);
                        if(x!=0) {
                            // if possible to do the step it will return the cost of the step and do whatever needed
                            List<List<String>> curr = new ArrayList<>(n.getMatrix());
                            List<List<String>> temp = new ArrayList<>();
                            for (List<String> row : curr) {
                                temp.add(new ArrayList<>(row)); // Copy each inner list
                            }
                            String ball = temp.get(i).get(j);
                            temp.get(i).set(j, "_");
                            temp.get((i+3-1)%3).set(j, ball) ;
                            Node newNode = new Node(n, temp);
                            num++; // count creating node
                            // check if already in the hash
                            if(!hash.containsKey(temp)) {
                                // set the node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+3-1)%3+1)+","+(j+1)+")";
                                newNode.add_move(x, step, "up", ((i+3-1)%3), j);
                                newNode.set_h(h(newNode, goal));
                                N.add(newNode);
                            }
                        }

                        // step down checking
                        x = n.step_down(i, j);
                        if(x!=0) {
                            // if possible to do the step it will return the cost of the step and do whatever needed
                            List<List<String>> curr = new ArrayList<>(n.getMatrix());
                            List<List<String>> temp = new ArrayList<>();
                            for (List<String> row : curr) {
                                temp.add(new ArrayList<>(row)); // Copy each inner list
                            }
                            String ball = temp.get(i).get(j);
                            temp.get(i).set(j, "_");
                            temp.get((i+1)%3).set(j, ball) ;
                            Node newNode = new Node(n, temp);
                            num++; // count creating node
                            // check if already in the hash
                            if(!hash.containsKey(temp)) {
                                // set the node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+1)%3+1)+","+(j+1)+")";
                                newNode.add_move(x, step, "down", ((i+1)%3), j);
                                newNode.set_h(h(newNode, goal));
                                N.add(newNode);
                            }
                        }

                        // step right checking
                        x = n.step_right(i, j);
                        if(x!=0) {
                            // if possible to do the step it will return the cost of the step and do whatever needed
                            List<List<String>> curr = new ArrayList<>(n.getMatrix());
                            List<List<String>> temp = new ArrayList<>();
                            for (List<String> row : curr) {
                                temp.add(new ArrayList<>(row)); // Copy each inner list
                            }
                            String ball = temp.get(i).get(j);
                            temp.get(i).set(j, "_");
                            temp.get(i).set((j+1)%3, ball) ;
                            Node newNode = new Node(n, temp);
                            num++; // count creating node
                            // check if already in the hash
                            if(!hash.containsKey(temp)) {
                                // set the node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+1)%3+1)+")";
                                newNode.add_move(x, step, "right", i, ((j +1)%3));
                                newNode.set_h(h(newNode, goal));
                                N.add(newNode);
                            }
                        }

                        // step left checking
                        x = n.step_left(i, j);
                        if(x!=0) {
                            // if possible to do the step it will return the cost of the step and do whatever needed
                            List<List<String>> curr = new ArrayList<>(n.getMatrix());
                            List<List<String>> temp = new ArrayList<>();
                            for (List<String> row : curr) {
                                temp.add(new ArrayList<>(row)); // Copy each inner list
                            }
                            String ball = temp.get(i).get(j);
                            temp.get(i).set(j, "_");
                            temp.get(i).set((j+3-1)%3, ball) ;
                            Node newNode = new Node(n, temp);
                            num++; // count creating node
                            // check if already in the hash
                            if(!hash.containsKey(temp)) {
                                // set the node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+3-1)%3+1)+")";
                                newNode.add_move(x, step, "left", i, ((j +3-1)%3));
                                newNode.set_h(h(newNode, goal));
                                N.add(newNode);
                            }
                        }
                    }
                }

                // sort the array by the f of each node
                Collections.sort(N);
                // creat new array so instead of removing from the original I will add only the necessary nodes to the new one
                ArrayList<Node> temp = new ArrayList<>();

                Iterator<Node> iter = N.iterator();
                while(iter.hasNext()) {
                    Node g = iter.next();
                    int f = h(g, goal) + g.get_cost();
                    // if we got to f that bigger than t so this node and the next ones aren't good enough
                    if(f >= t) {
                        break;
                    }
                    // if this node is in the hash but wasn't processed
                    else if(hash.containsKey(g.getMatrix()) && !g.get_out()) {
                        int fg_tag = h(hash.get(g.getMatrix()), goal) + hash.get(g.getMatrix()).get_cost();
                        // if the f of the node that in the hash is bigger, so we will switch them
                        if(fg_tag > f){
                            stack.remove(hash.get(g.getMatrix()));
                            hash.remove(g.getMatrix());
                            temp.add(g);
                        }
                    }
                    // if we found the goal we can stop for this array because the next nodes have bigger f so if they are goal they aren't better
                    else if(g.is_equals(goal)) { // if we reached here, f(g) < t
                        t = f;
                        result = g;
                        break;
                    }
                    // iff none of the above so add this node to the new array, so we can process it later
                    else{
                        temp.add(g);
                    }
                }
                // insert N in a reverse order to stack and hash
                ListIterator<Node> reverseIter = temp.listIterator(temp.size());
                while (reverseIter.hasPrevious()) {
                    Node g = reverseIter.previous();
                    stack.push(g);
                    hash.put(g.getMatrix(), g);

                }
            }

        }
        return result;
    }

    // A* algorithm.
    public static Node AStar(Node start, List<List<String>> goal, boolean open) {

        // creating all the necessary structure
        Hashtable<List<List<String>>, Node> openList = new Hashtable<>();
        PriorityQueue<Node> openQueue = new PriorityQueue<>();
        Hashtable<List<List<String>>, Node> closeList = new Hashtable<>();

        // calculate the heuristic and add to the open list
        start.set_h(h(start, goal));

        openList.put(start.getMatrix(), start);
        openQueue.add(start);

        while(!openQueue.isEmpty()){

            // print the open list if the input said so
            if(open){
                System.out.println("Printing open list:");
                for (Node node : openQueue) {
                    System.out.println();
                    printMatrix(node.getMatrix());
                }
            }

            Node current = openQueue.poll();
            openList.remove(current.getMatrix());

            // check if we found the goal
            if(current.is_equals(goal)){
                return current;
            }

            closeList.put(current.getMatrix(), current);

            // run trow all the possible next steps
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    int x;
                    for(int a=0; a<4; a++) {
                        if(a== 0) {
                            // step up checking
                            x = current.step_up(i, j);
                            if (x != 0) {
                                // if possible to do the step it will return the cost of the step and do whatever needed
                                List<List<String>> curr = new ArrayList<>(current.getMatrix());
                                List<List<String>> temp = new ArrayList<>();
                                for (List<String> row : curr) {
                                    temp.add(new ArrayList<>(row)); // Copy each inner list
                                }
                                String ball = temp.get(i).get(j);
                                temp.get(i).set(j, "_");
                                temp.get((i + 3 - 1) % 3).set(j, ball);
                                Node newNode = new Node(current, temp);
                                num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    // set the node
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 3 - 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "up", ((i + 3 - 1) % 3), j);
                                    return newNode;
                                }
                                // calculate the node h and f
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                // check if is in the open list
                                if (openList.containsKey(temp)) {
                                    continue;
                                }
                                // check if in the close list and which one have bigger f
                                else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                }
                                // set the node and add to the open list so will be process
                                else {
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 3 - 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "up", ((i + 3 - 1) % 3), j);
                                    openList.put(temp, newNode);
                                    openQueue.add(newNode);
                                }
                            }
                        }

                        if(a==1) {
                            // step down checking
                            x = current.step_down(i, j);
                            if (x != 0) {
                                // if possible to do the step it will return the cost of the step and do whatever needed
                                List<List<String>> curr = new ArrayList<>(current.getMatrix());
                                List<List<String>> temp = new ArrayList<>();
                                for (List<String> row : curr) {
                                    temp.add(new ArrayList<>(row)); // Copy each inner list
                                }
                                String ball = temp.get(i).get(j);
                                temp.get(i).set(j, "_");
                                temp.get((i + 1) % 3).set(j, ball);
                                Node newNode = new Node(current, temp);
                                num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    // set the node
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "down", ((i + 1) % 3), j);
                                    return newNode;
                                }
                                // calculate the node h and f
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                if (openList.containsKey(temp)) {
                                    continue;
                                }
                                // check if in the close list and which one have bigger f
                                else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                }
                                // set the node and add to the open list so will be process
                                else {
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "down", ((i + 1) % 3), j);
                                    openList.put(temp, newNode);
                                    openQueue.add(newNode);
                                }
                            }
                        }

                        if(a==2) {
                            // step right checking
                            x = current.step_right(i, j);
                            if (x != 0) {
                                // if possible to do the step it will return the cost of the step and do whatever needed
                                List<List<String>> curr = new ArrayList<>(current.getMatrix());
                                List<List<String>> temp = new ArrayList<>();
                                for (List<String> row : curr) {
                                    temp.add(new ArrayList<>(row)); // Copy each inner list
                                }
                                String ball = temp.get(i).get(j);
                                temp.get(i).set(j, "_");
                                temp.get(i).set(((j + 1) % 3), ball);
                                Node newNode = new Node(current, temp);
                                num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    // set the node
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "right", i, ((j + 1) % 3));
                                    return newNode;
                                }
                                // calculate the node h and f
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                if (openList.containsKey(temp)) {
                                    continue;
                                }
                                // check if in the close list and which one have bigger f
                                else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                }
                                // set the node and add to the open list so will be process
                                else {
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "right", i, ((j + 1) % 3));
                                    openList.put(temp, newNode);
                                    openQueue.add(newNode);
                                }
                            }
                        }

                        if(a==3) {
                            // step left checking
                            x = current.step_left(i, j);
                            if (x != 0) {
                                // if possible to do the step it will return the cost of the step and do whatever needed
                                List<List<String>> curr = new ArrayList<>(current.getMatrix());
                                List<List<String>> temp = new ArrayList<>();
                                for (List<String> row : curr) {
                                    temp.add(new ArrayList<>(row)); // Copy each inner list
                                }
                                String ball = temp.get(i).get(j);
                                temp.get(i).set(j, "_");
                                temp.get(i).set(((j + 3 - 1) % 3), ball);
                                Node newNode = new Node(current, temp);
                                num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    // set the node
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 3 - 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "left", i, ((j + 3 - 1) % 3));
                                    return newNode;
                                }
                                // calculate the node h and f
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                if (openList.containsKey(temp)) {
                                    continue;
                                }
                                // check if in the close list and which one have bigger f
                                else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                }
                                // set the node and add to the open list so will be process
                                else {
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 3 - 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "left", i, ((j + 3 - 1) % 3));
                                    openList.put(temp, newNode);
                                    openQueue.add(newNode);
                                }

                            }
                        }
                    }
                }
            }
        }
        return new Node();
    }

    // my heuristic function
    public static int h(Node n, List<List<String>> goal) {

        List<List<String>> curr = new ArrayList<>(n.getMatrix());
        List<List<String>> start = new ArrayList<>();
        for (List<String> row : curr) {
            start.add(new ArrayList<>(row)); // Copy each inner list
        }

        int totalCost = 0;
        String value;
        int price;

        // doing the same for each of the colors
        for(int a=0; a<3; a++) {
            // changing the parameters by the color
            if (a == 0) {
                value = "R";
                price = 10;
            }
            else if(a == 1) {
                value = "B";
                price = 1;
            }
            else {
                value = "G";
                price = 3;
            }

            // Finding the x and y of all the ball in the goal and start matrix
            int xStart1 = 5, xStart2 = 5, yStart1= 5, yStart2= 5;
            int xGoal1 = 5, xGoal2 = 5, yGoal1 = 5, yGoal2 = 5;
            for(int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (start.get(i).get(j).equals(value)) {
                        if (xStart1 < 5 && yStart1 < 5) {
                            xStart2 = i;
                            yStart2 = j;
                        }
                        else {
                            xStart1 = i;
                            yStart1 = j;
                        }
                    }
                    if(goal.get(i).get(j).equals(value)) {
                        if (xGoal1 < 5 && yGoal1 < 5) {
                            xGoal2 = i;
                            yGoal2 = j;
                        }
                        else {
                            xGoal1 = i;
                            yGoal1 = j;
                        }
                    }
                }
            }

            int cost1, cost2;

            // calculate the estimate cost by checking if a round step is better
            int dx1 = Math.min(Math.abs(xStart1-xGoal1), 3-Math.abs(xStart1-xGoal1));
            int dy1 = Math.min(Math.abs(yStart1-yGoal1), 3-Math.abs(yStart1-yGoal1));
            int dx2 = Math.min(Math.abs(xStart2-xGoal2), 3-Math.abs(xStart2-xGoal2));
            int dy2 = Math.min(Math.abs(yStart2-yGoal2), 3-Math.abs(yStart2-yGoal2));

            cost1 = (dx1 + dy1)* price +(dx2 + dy2)* price;

            // calculate the estimate cost by checking if a round step is better
            dx1 = Math.min(Math.abs(xStart1-xGoal2), 3-Math.abs(xStart1-xGoal2));
            dy1 = Math.min(Math.abs(yStart1-yGoal2), 3-Math.abs(yStart1-yGoal2));
            dx2 = Math.min(Math.abs(xStart2-xGoal1), 3-Math.abs(xStart2-xGoal1));
            dy2 = Math.min(Math.abs(yStart2-yGoal1), 3-Math.abs(yStart2-yGoal1));

            cost2 = (dx1 + dy1)* price +(dx2 + dy2)* price;

            // choosing the better option
            totalCost = totalCost + Math.min(cost1, cost2);
        }

        return totalCost;
    }

    //IDA* algorithm
    public static Node IDA(Node start, List<List<String>> goal, boolean open){

        // calculate the heuristics and set the threshold
        int h = h(start, goal);
        start.set_h(h);
        int threshold = start.get_f();

        while(true){
            // creating the necessary structure
            Stack<Node> stack = new Stack<>();
            stack.push(start);

            // call the dfs function
            int result = dfs(stack, goal, threshold, open);
            if(result == -1){
                // we found the goal and the goal node is the first in the stack
                return stack.pop();
            }
            if(result == Integer.MAX_VALUE){
                return start;
            }
            // if there is no solution so after creating more than the possible amount of node we should stop
            if(num >= 362880){
                // 9! = 362880 is the max num of matrix that we can creat (each "R" is different from the other...)
                return start;
            }
            threshold = result;
        }
    }

    // the dfs of the IDA* algorithm
    public static int dfs(Stack<Node> stack, List<List<String>> goal, int threshold, boolean open) {
        Node current = stack.peek();

        // check if we found the goal (stopping state)
        if (current.is_equals(goal)) {
            return -1; // goal found
        }

        // defining the start threshold
        int min = Integer.MAX_VALUE;

        // run throw all the possible next steps
        for (Node neighbor : getNeighbors(current, goal)){
            // loop avoidance, if already in the stack so skip
            if(isInPath(stack, neighbor)){
                continue;
            }

            // calculate the f of the node
            int f = neighbor.get_f();
            if(f>threshold){
                min = Math.min(min, f);
                continue;
            }

            // add this node to the stack and call dfs so will be processed
            stack.push(neighbor);
            int result = dfs(stack, goal, threshold, open);
            // check if found goal
            if(result == -1){
                return -1;
            }
            min = Math.min(min, result);

            // printing the open list if the input said so
            if(open){
                System.out.println("Printing open list:");
                for (Node node : stack) {
                    System.out.println();
                    printMatrix(node.getMatrix());
                }
            }

            stack.pop();
        }
        return min;
    }

    // check if the node is already was visited (loop avoidance)
    private static boolean isInPath(Stack<Node> stack, Node neighbor) {
        for(Node node : stack){
            if(node.getMatrix().equals(neighbor.getMatrix())){
                return true;
            }
        }
        return false;
    }

    // return a list of all the neighbors of the node for the IDA* algorithm
    public static List<Node> getNeighbors(Node node, List<List<String>> goal){

        ArrayList<Node> neighbors =new ArrayList<>();

        // run throw all the possible positions
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {

                int x;
                // step up checking
                x = node.step_up(i, j);
                if(x != 0) {
                    // if  possible to do the step it will return the cost of the step and do whatever needed
                    List<List<String>> curr = new ArrayList<>(node.getMatrix());
                    List<List<String>> temp = new ArrayList<>();
                    for (List<String> row : curr) {
                        temp.add(new ArrayList<>(row)); // Copy each inner list
                    }
                    String ball = temp.get(i).get(j);
                    temp.get(i).set(j, "_");
                    temp.get((i+3-1)%3).set(j, ball);
                    Node g = new Node(node, temp);
                    num++;
                    // set node
                    g.set_h(h(g,goal));
                    String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+3-1)%3+1)+","+(j+1)+")";
                    g.add_move(x, step, "up", ((i+3-1)%3), j);

                    neighbors.add(g);
                }

                // step down checking
                x = node.step_down(i, j);
                if(x != 0) {
                    // if  possible to do the step it will return the cost of the step and do whatever needed
                    List<List<String>> curr = new ArrayList<>(node.getMatrix());
                    List<List<String>> temp = new ArrayList<>();
                    for (List<String> row : curr) {
                        temp.add(new ArrayList<>(row)); // Copy each inner list
                    }
                    String ball = temp.get(i).get(j);
                    temp.get(i).set(j, "_");
                    temp.get((i+1)%3).set(j, ball);
                    Node g = new Node(node, temp);
                    num++;
                    // set node
                    g.set_h(h(g,goal));
                    String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+1)%3+1)+","+(j+1)+")";
                    g.add_move(x, step, "down", ((i+1)%3), j);

                    neighbors.add(g);
                }


                // step right checking
                x = node.step_right(i, j);
                if(x != 0) {
                    // if  possible to do the step it will return the cost of the step and do whatever needed
                    List<List<String>> curr = new ArrayList<>(node.getMatrix());
                    List<List<String>> temp = new ArrayList<>();
                    for (List<String> row : curr) {
                        temp.add(new ArrayList<>(row)); // Copy each inner list
                    }
                    String ball = temp.get(i).get(j);
                    temp.get(i).set(j, "_");
                    temp.get(i).set((j+1)%3, ball);
                    Node g = new Node(node, temp);
                    num++;
                    // set node
                    g.set_h(h(g,goal));
                    String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+1)%3+1)+")";
                    g.add_move(x, step, "right", i, ((j +1)%3));

                    neighbors.add(g);
                }

                // step left checking
                x = node.step_left(i, j);
                if(x != 0) {
                    // if  possible to do the step it will return the cost of the step and do whatever needed
                    List<List<String>> curr = new ArrayList<>(node.getMatrix());
                    List<List<String>> temp = new ArrayList<>();
                    for (List<String> row : curr) {
                        temp.add(new ArrayList<>(row)); // Copy each inner list
                    }
                    String ball = temp.get(i).get(j);
                    temp.get(i).set(j, "_");
                    temp.get(i).set((j+3-1)%3, ball);
                    Node g = new Node(node, temp);
                    num++;
                    // set node
                    g.set_h(h(g,goal));
                    String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+3-1)%3+1)+")";
                    g.add_move(x, step, "left", i, ((j +3-1)%3));

                    neighbors.add(g);
                }
            }
        }
        return neighbors;
    }
}
