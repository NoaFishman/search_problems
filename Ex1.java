import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Ex1 {

    public static int num = 0; // this is a global because the recursive functions

    public static void main(String[] args) {
        String filePath = "example3.txt"; // Update with your file's path

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
                case "DFID" -> {
                    System.out.println();
                    long startTime = System.nanoTime();

                    Node n = DFID(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds


                    int cost = n.get_cost();

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
                case "A*" -> {
                    System.out.println();
                    long startTime = System.nanoTime();

                    Node n = AStar(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds


                    int cost = n.get_cost();

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
                case "IDA*" -> {
                    System.out.println();
                    long startTime = System.nanoTime();

                    Node n = IDA(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds


                    int cost = n.get_cost();

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
                case "DFBnB" -> {

                    System.out.println();
                    long startTime = System.nanoTime();

                    Node n = DFBnB(start, goalMatrix, withOpen);

                    long endTime = System.nanoTime();
                    // Calculate the elapsed time in milliseconds
                    double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to milliseconds


                    int cost = n.get_cost();

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
            }


        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error parsing the file: File format may be incorrect.");
        }

    }


    private static void printMatrix(List<List<String>> matrix) {
        for (List<String> row : matrix) {
            System.out.println(row);
        }
    }


    // BFS algorithm
    public static Node BFS(Node n, List<List<String>> goal, boolean open) {

        Queue<Node> L = new LinkedList<>();
        Hashtable<List<List<String>>, Node> Lhash = new Hashtable<>();
        Hashtable<List<List<String>>, Node> hash = new Hashtable<>();
        L.add(n);
        Lhash.put(n.getMatrix(),n);

        while(!L.isEmpty()) {

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

                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            Node newNode = new Node(currNode, temp);
                            num++; // count creating node
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+3-1)%3+1)+","+(j+1)+")";
                            newNode.add_move(x, step, "up", ((i+3-1)%3), j);
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
//                            System.out.println();
//                            printMatrix(temp);
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

                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            Node newNode = new Node(currNode, temp);
                            num++; // count creating node
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+1)%3+1)+","+(j+1)+")";
                            newNode.add_move(x, step, "down", ((i+1)%3), j);
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
//                            System.out.println();
//                            printMatrix(temp);
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

                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            Node newNode = new Node(currNode, temp);
                            num++; // count creating node
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j +1)%3+1)+")";
                            newNode.add_move(x, step, "right", i, ((j +1)%3));
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
//                            System.out.println();
//                            printMatrix(temp);
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

                        if(!hash.containsKey(temp) && !Lhash.containsKey(temp)) {
                            Node newNode = new Node(currNode, temp);
                            num++; // count creating node
                            String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+3-1)%3+1)+")";
                            newNode.add_move(x, step, "left", i, ((j +3-1)%3));
                            if (newNode.is_equals(goal)) {
                                newNode.found_goal(num);
                                return newNode;
                            }
                            L.add(newNode);
                            Lhash.put(newNode.getMatrix(), newNode);
                            hash.put(temp, newNode);
//                            System.out.println();
//                            printMatrix(temp);
                        }
                    }
                }
            }
        }
        //System.out.println("return here");
        return n;
    }


    // DFID algorithm
    public static Node DFID(Node n, List<List<String>> goal, boolean open) {

        int depth = 1;

        while(depth > 0) {
            Hashtable<List<List<String>>, Node> hash = new Hashtable<>();
            depth++;
            Node result = Limited_DFS(n, goal, depth, hash, open);
            if(result.is_equals(goal)) {
                return  result; // I need to make sure this is  ok !!!!!!!!!!!!!!
            }
        }
        return n;
    }


    // the second function of DFID algorithm
    public static Node Limited_DFS(Node n, List<List<String>> goal, int limit, Hashtable<List<List<String>>, Node> hash, boolean open) {

        if(n.is_equals(goal)) {
            n.found_goal(num);
            return n;
        }

        else if(limit == 0){
            n.set_isCutoff(true);
            return n;
        }

        else {
            List<List<String>> mat = n.getMatrix();
            hash.put(mat, n);
            boolean isCutoff = false;

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

                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
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

                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
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

                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
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

                            if (result.get_isCutoff()) {
                                isCutoff = true;
                            }
                            else if (result.is_equals(goal)) {
                                result.found_goal(num);
                                return result;
                            }
                        }
                    }
                }
            }
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


    // IDA* algorithm
    public static Node IDA(Node start, List<List<String>> goal, boolean open) {

        Stack<Node> stack = new Stack<>();
        Hashtable<List<List<String>>, Node> hash = new Hashtable<>();

        int t = h(start, goal);
        start.set_h(t);

        while(t != Integer.MAX_VALUE) {
            int minF = Integer.MAX_VALUE;
            stack.push(start);
            hash.put(start.getMatrix(), start);

            while(!stack.isEmpty()) {
                Node n = stack.pop();
                System.out.println();
                printMatrix(n.getMatrix());

                if(n.get_out()) {
                    hash.remove(n.getMatrix());
                }
                else {
                    n.mark_out();
                    stack.push(n);

                    for(int i=0; i<3; i++) {
                        for(int j=0; j<3; j++) {

                            int x;
                            for(int a=0; a<4; a++) {

                                if(a == 0) {
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
                                        g.set_h(h(g,goal));
                                        System.out.println("---------------");
                                        printMatrix(temp);
                                        System.out.println("---------------");
                                        String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+3-1)%3+1)+","+(j+1)+")";
                                        g.add_move(x, step, "up", ((i+3-1)%3), j);

                                        int fg = g.get_cost()+h(g,goal);

                                        if(fg > t) {
                                            minF = Math.min(fg, minF);
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && hash.get(temp).get_out()) {
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && !hash.get(temp).get_out()) {

                                            if(fg < hash.get(temp).get_f()) {
                                                stack.remove(hash.get(temp));
                                                hash.remove(temp);
                                            }
                                            else {
                                                continue;
                                            }
                                        }

                                        if(temp.equals(goal)) {
                                            g.found_goal(num);
                                            System.out.println("584");
                                            return g;
                                        }
                                        stack.push(g);
                                        hash.put(g.getMatrix(), g);
                                        System.out.println("added");
                                    }
                                }

                                if(a == 1) {
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
                                        g.set_h(h(g,goal));
                                        System.out.println("---------------");
                                        printMatrix(temp);
                                        System.out.println("---------------");
                                        String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+1)%3+1)+","+(j+1)+")";
                                        g.add_move(x, step, "down", ((i+1)%3), j);

                                        int fg = g.get_cost()+h(g,goal);

                                        if(fg > t) {
                                            minF = Math.min(fg, minF);
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && hash.get(temp).get_out()) {
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && !hash.get(temp).get_out()) {

                                            if(fg >= hash.get(temp).get_f()) {
                                                continue;
                                            }
                                            else {
                                                stack.remove(hash.get(temp));
                                                hash.remove(temp);
                                            }
                                        }

                                        if(g.getMatrix().equals(goal)) {
                                            g.found_goal(num);
                                            System.out.println("647");
                                            return g;
                                        }
                                        stack.push(g);
                                        hash.put(g.getMatrix(), g);
                                        System.out.println("added");
                                    }
                                }


                                if(a == 2) {
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
                                        g.set_h(h(g,goal));
                                        System.out.println("---------------");
                                        printMatrix(temp);
                                        System.out.println("---------------");
                                        String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+1)%3+1)+")";
                                        g.add_move(x, step, "right", i, ((j +1)%3));

                                        int fg = g.get_cost()+h(g,goal);

                                        if(fg > t) {
                                            minF = Math.min(fg, minF);
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && hash.get(temp).get_out()) {
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && !hash.get(temp).get_out()) {

                                            if(fg >= hash.get(temp).get_f()) {
                                                continue;
                                            }
                                            else {
                                                stack.remove(hash.get(temp));
                                                hash.remove(temp);
                                            }
                                        }

                                        if(g.getMatrix().equals(goal)) {
                                            g.found_goal(num);
                                            System.out.println("711");
                                            return g;
                                        }
                                        stack.push(g);
                                        hash.put(g.getMatrix(), g);
                                        System.out.println("added");
                                    }
                                }


                                if(a == 3) {
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
                                        g.set_h(h(g,goal));
                                        System.out.println("---------------");
                                        printMatrix(temp);
                                        System.out.println("---------------");
                                        String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+3-1)%3+1)+")";
                                        g.add_move(x, step, "left", i, ((j +3-1)%3));

                                        int fg = g.get_cost()+h(g,goal);

                                        if(fg > t) {
                                            minF = Math.min(fg, minF);
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && hash.get(temp).get_out()) {
                                            continue;
                                        }

                                        if(hash.containsKey(temp) && !hash.get(temp).get_out()) {

                                            if(fg >= hash.get(temp).get_f()) {
                                                continue;
                                            }
                                            else {
                                                stack.remove(hash.get(temp));
                                                hash.remove(temp);

                                            }
                                        }

                                        if(g.getMatrix().equals(goal)) {
                                            g.found_goal(num);
                                            System.out.println("775");
                                            return g;
                                        }
                                        stack.push(g);
                                        hash.put(g.getMatrix(), g);
                                        System.out.println("added");
                                    }
                                }
                            }
                        }
                    }
                }
                t = minF;
            }


        }
        System.out.println("790");
        return start;
    }


    // DFBnB algorithm
    public static Node DFBnB(Node start, List<List<String>> goal, boolean open) {

        Stack<Node> stack = new Stack<>();
        Hashtable<List<List<String>>, Node> hash = new Hashtable<>();

        start.set_h(h(start, goal));
        stack.push(start);
        hash.put(start.getMatrix(), start);
        int t = Integer.MAX_VALUE;
        Node result = new Node();

        while(!stack.isEmpty()) {

            if(open){
                System.out.println("Printing open list:");
                for (Node node : stack) {
                    System.out.println();
                    printMatrix(node.getMatrix());
                }
            }
            Node n = stack.pop();
            if(n.get_out()) {
                hash.remove(n.getMatrix());
            }
            else {
                n.mark_out();
                stack.push(n);
                ArrayList<Node> N = new ArrayList<>();

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
//                            Node newNode = new Node(n, temp);
//                            num++; // count creating node
                            if(!hash.containsKey(temp)) {
                                Node newNode = new Node(n, temp);
                                num++; // count creating node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+3-1)%3+1)+","+(j+1)+")";
                                newNode.add_move(x, step, "up", ((i+3-1)%3), j);
                                System.out.println();
                                printMatrix(temp);
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
//                            Node newNode = new Node(n, temp);
//                            num++; // count creating node
                            if(!hash.containsKey(temp)) {
                                Node newNode = new Node(n, temp);
                                num++; // count creating node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+((i+1)%3+1)+","+(j+1)+")";
                                newNode.add_move(x, step, "down", ((i+1)%3), j);
                                System.out.println();
                                printMatrix(temp);
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
//                            Node newNode = new Node(n, temp);
//                            num++; // count creating node
                            if(!hash.containsKey(temp)) {
                                Node newNode = new Node(n, temp);
                                num++; // count creating node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+1)%3+1)+")";
                                newNode.add_move(x, step, "right", i, ((j +1)%3));
                                System.out.println();
                                printMatrix(temp);
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
//                            Node newNode = new Node(n, temp);
//                            num++; // count creating node
                            if(!hash.containsKey(temp)) {
                                Node newNode = new Node(n, temp);
                                num++; // count creating node
                                String step = "("+(i+1)+","+(j+1)+"):"+ball+":("+(i+1)+","+((j+3-1)%3+1)+")";
                                newNode.add_move(x, step, "left", i, ((j +3-1)%3));
                                System.out.println();
                                printMatrix(temp);
                                newNode.set_h(h(newNode, goal));
                                N.add(newNode);
                            }
                        }
                    }
                }

                Collections.sort(N);
                ArrayList<Node> temp = new ArrayList<>();

                Iterator<Node> iter = N.iterator();
                while(iter.hasNext()) {
                    Node g = iter.next();
                    int f = h(g, goal) + g.get_cost();
                    if(f >= t) {
//                        // remove g and all the nodes after it from N
//                        while (iter.hasNext()) {
//                            iter.next();
//                            iter.remove();
//                        }
                        break;
                    }
                    else if(hash.containsKey(g.getMatrix()) && g.get_out()) {
//                        iter.remove(); // where the iter go ??? should I do i-1 ??????????????
                    }
                    else if(hash.containsKey(g.getMatrix()) && !g.get_out()) {
                        int fg_tag = h(hash.get(g.getMatrix()), goal) + hash.get(g.getMatrix()).get_cost();
//                        if(fg_tag <= f) {
//                            iter.remove(); // where the iter go ??? should I do i-1 ??????????????
//                        }
//                        else {
//                            stack.remove(hash.get(g.getMatrix())); // is it will be ok ???????
//                            hash.remove(g.getMatrix());
//                        }
                        if(fg_tag > f){
                            stack.remove(hash.get(g.getMatrix())); // is it will be ok ???????
                            hash.remove(g.getMatrix());
                            temp.add(g);
                        }
                    }
                    else if(g.is_equals(goal)) { // if we reached here, f(g) < t
                        t = f;
                        result = g;
                        // remove g and all the nodes after it from N
//                        while (iter.hasNext()) {
//                            iter.next();
//                            iter.remove();
//                        }
                        break;
                    }
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


    public static Node AStar(Node start, List<List<String>> goal, boolean open) {

        Hashtable<List<List<String>>, Node> openList = new Hashtable<>();
        PriorityQueue<Node> openQueue = new PriorityQueue<>();
        Hashtable<List<List<String>>, Node> closeList = new Hashtable<>();

        start.set_h(h(start, goal));

        openList.put(start.getMatrix(), start);
        openQueue.add(start);

        while(!openQueue.isEmpty()){

            if(open){
                System.out.println("Printing open list:");
                for (Node node : openQueue) {
                    System.out.println();
                    printMatrix(node.getMatrix());
                }
            }

            Node current = openQueue.poll();
            openList.remove(current.getMatrix());

            if(current.is_equals(goal)){
                return current;
            }

            closeList.put(current.getMatrix(), current);

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
                                //num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 3 - 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "up", ((i + 3 - 1) % 3), j);
                                    // System.out.println();
                                    // printMatrix(temp);
                                    return newNode;
                                }
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                if (openList.containsKey(temp)) {
                                    continue;
                                } else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                } else {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 3 - 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "up", ((i + 3 - 1) % 3), j);
//                                    System.out.println();
//                                    printMatrix(temp);
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
                                //num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "down", ((i + 1) % 3), j);
//                                    System.out.println();
//                                    printMatrix(temp);
                                    return newNode;
                                }
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                if (openList.containsKey(temp)) {
                                    continue;
                                } else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                } else {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + ((i + 1) % 3 + 1) + "," + (j + 1) + ")";
                                    newNode.add_move(x, step, "down", ((i + 1) % 3), j);
//                                    System.out.println();
//                                    printMatrix(temp);
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
                                //num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "right", i, ((j + 1) % 3));
//                                    System.out.println();
//                                    printMatrix(temp);
                                    return newNode;
                                }
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                if (openList.containsKey(temp)) {
                                    continue;
                                } else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                } else {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "right", i, ((j + 1) % 3));
//                                    System.out.println();
//                                    printMatrix(temp);
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
                                //num++; // count creating node
                                if (newNode.is_equals(goal)) {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 3 - 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "left", i, ((j + 3 - 1) % 3));
//                                    System.out.println();
//                                    printMatrix(temp);
                                    return newNode;
                                }
                                newNode.set_h(h(newNode, goal));
                                int f = newNode.get_f();
                                if (openList.containsKey(temp)) {
                                    continue;
                                } else if (closeList.containsKey(temp) && closeList.get(temp).get_f() < f) {
                                    continue;
                                } else {
                                    num++;
                                    String step = "(" + (i + 1) + "," + (j + 1) + "):" + ball + ":(" + (i + 1) + "," + ((j + 3 - 1) % 3 + 1) + ")";
                                    newNode.add_move(x, step, "left", i, ((j + 3 - 1) % 3));
//                                    System.out.println();
//                                    printMatrix(temp);
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


}
