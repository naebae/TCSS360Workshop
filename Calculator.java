import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calculator {
    // scalar variables
    private double x;

    private double y;

    // vector variables
    private ArrayList<Double> vector_1;

    private ArrayList<Double> vector_2;

    // matrix variables (list of row vectors)
    private ArrayList<ArrayList<Double>> matrix_1;

    private ArrayList<ArrayList<Double>> matrix_2;

    public Calculator () {
        // initializes all variables when created
        x = 0.0;
        y = 0.0;
        vector_1 = new ArrayList<>();
        vector_2 = new ArrayList<>();
        matrix_1 = new ArrayList<>();
        matrix_2 = new ArrayList<>();
    }

    /** basic stuff */
    public double addition (double x, double y) {
        return x + y;
    }
    public double subtraction (double x, double y) {
        return x - y;
    }
    public double negation (double x) {
        return x * -1;
    }
    public double multiplication (double x, double y) {
        return x * y;
    }
    public double division (double x, double y) {
        return x / y;
    }
    public double inverse (double x) {
        return 1/x;
    }
    /** powers, roots, and logarithms */
    public double power (double x, double y) {
        return Math.pow(x, y);
    }
    public double power_e (double x) {
        return Math.exp(x);
    }
    public double power_2 (double x) {
        return Math.pow(2, x);
    }
    public double power_10 (double x) {
        return Math.pow(10, x);
    }
    public double root (double x, double y) {
        return Math.pow(x, 1/y);
    }
    public double square_root (double x) {
        return Math.pow(x, 0.5);
    }
    public double logarithm (double x, double b) {
        return Math.log(x)/Math.log(b);
    }
    public double ln (double x) {
        return Math.log(x);
    }
    public double log_10 (double x) {
        return Math.log(x)/Math.log(10);
    }
    public double lg (double x) {
        return Math.log(x)/Math.log(2);
    }
    /** trigonometric and hyperbolic functions */
    public double sine (double x) {
        return Math.sin(x);
    }
    public double arcsine (double x) {
        return Math.asin(x);
    }
    public double hyperbolic_sine (double x) {
        return Math.sinh(x);
    }
    public double cosine (double x) {
        return Math.cos(x);
    }
    public double arccosine (double x) {
        return Math.acos(x);
    }
    public double hyperbolic_cosine (double x) {
        return Math.cosh(x);
    }
    public double tangent (double x) {
        return Math.tan(x);
    }
    public double arctangent (double x) {
        return Math.atan(x);
    }
    public double hyperbolic_tangent (double x) {
        return Math.tanh(x);
    }
    public double secant (double x) {
        return 1/Math.cos(x);
    }
    public double arcsecant (double x) {
        return Math.acos(1/x);
    }
    public double hyperbolic_secant (double x) {
        return 2/(power_e(x) + power_e(-1*x));
    }
    public double cosecant (double x) {
        return 1/Math.sin(x);
    }
    public double arccosecant (double x) {
        return Math.asin(1/x);
    }
    public double hyperbolic_cosecant (double x) {
        return 2/(power_e(x) - power_e(-1*x));
    }
    public double cotangent (double x) {
        return 1/Math.tan(x);
    }
    public double arccotangent (double x) {
        return Math.PI/2 - Math.atan(x);
    }
    public double hyperbolic_cotangent (double x) {
        return Math.cosh(x)/Math.sinh(x);
    }
    /** summation */
    public double summation_1(int i, int n, ArrayList<Double> f) {
        double sum = 0.0;
        while (i < n) {
            sum += f.get(i);
            i++;
        }
        return sum;
    }
    /** vectors and matricies */

    // helper method for large number crunching
    public ArrayList<ArrayList<Double>> swap(ArrayList<ArrayList<Double>> matrix, int i1, int j1, int i2, int j2) {
        double temp = matrix.get(i1).get(j1);
        matrix.get(i1).set(j1, matrix.get(i2).get(j2));
        matrix.get(i2).set(j2, temp);
        return matrix;
    }

    public double magnitude_1 (ArrayList<Double> vector) {
        double magnitude = 0.0;
        for (int i = 0; i < vector.size(); i++) {
            magnitude += Math.pow(vector.get(i), 2);
        }
        magnitude = Math.pow(magnitude, 0.5);
        return magnitude;
    }
    public double dot_product1 (ArrayList<Double> vector_1, ArrayList<Double> vector_2) {
        double dot_product = 0.0;
        for (int i = 0; i < Math.min(vector_1.size(), vector_2.size()); i++)
            dot_product += vector_1.get(i)*vector_2.get(i);
        return dot_product;
    }
    public double angle (ArrayList<Double> vector_1, ArrayList<Double> vector_2) {
        return Math.acos(dot_product1(vector_1, vector_2)/(magnitude_2(vector_1) * magnitude_2(vector_2)));
    }
    public double magnitude_2 (ArrayList<Double> vector) {
        return Math.pow(dot_product1(vector, vector), 0.5);
    }
    public double dot_product2 (double magnitude_1, double magnitude_2, double angle) {
        return magnitude_1*magnitude_2*Math.cos(angle);
    }
    public ArrayList<Double> cross_product (ArrayList<Double> vector_1, ArrayList<Double> vector_2) {
        // cross product is undefined for vectors of a higher order than R^3
        if (vector_1.size() <= 3 && vector_2.size() <= 3) {
            ArrayList<Double> cross_product = new ArrayList<>();
            // make vector_1 and vector_2 in R^3 for easier calculation
            while (vector_1.size() != 3)
                vector_1.add(0.0);
            while (vector_2.size() != 3)
                vector_2.add(0.0);
            // algebraic calculation
            cross_product.add(vector_1.get(1) * vector_2.get(2) - vector_1.get(2) * vector_2.get(1));
            cross_product.add(vector_1.get(2) * vector_2.get(0) - vector_1.get(0) * vector_2.get(2));
            cross_product.add(vector_1.get(0) * vector_2.get(1) - vector_1.get(1) * vector_2.get(0));
            return cross_product;
        }
        return null;
    }
    // interpreted as the scalar multiple of the projection of vector_1 onto vector_2
    public double scalar_projection (ArrayList<Double> vector_1, ArrayList<Double> vector_2) {
        return dot_product1(vector_1, vector_2) / magnitude_2(vector_2);
    }
    // interpreted as the vector projection of vector_1 onto vector_2
    public ArrayList<Double> vector_projection (ArrayList<Double> vector_1, ArrayList<Double> vector_2) {
        double scalar_projection = dot_product1(vector_1, vector_2) / dot_product1(vector_2, vector_2);
        ArrayList<Double> vector_projection = new ArrayList<>();
        for (int i = 0; i < vector_2.size(); i++)
            vector_projection.add(vector_2.get(i)*scalar_projection);
        return vector_projection;
    }

     // - can be used to make a vector the same lengths as another vector e.g. set_length(v_1, v_2.size()) the method
     //   will set v_1's length the same as v_2 given v_1.size() < v_2.size()
     // - does nothing in the event that v_1.size() >= v_2.size()
    public void set_length (ArrayList<Double> vector, int n) {
        if (vector.size() < n) {
            while (vector.size() < n)
                vector.add(0.0);
        }
    }

    public boolean is_Rectangular (ArrayList<ArrayList<Double>> matrix) {

        for (int i = 0; i < matrix.size(); i++) {

        }
    }
    // returns a two element array of integers where the first entry represents the number of rows and the second represents
    // the number of columns
    public ArrayList<Integer> resolve_dimensions (ArrayList<ArrayList<Double>> matrix) {
        ArrayList<Integer> dimensions = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            boolean is_zero_row = true;
            for (int j = 0; j < matrix.get(i).size(); j++)
                if (matrix.get(i).get(j) == 0.0) {
                    is_zero_row = false;
                    break;
                }
        }
    }
    // generates an N x N identity matrix
    public ArrayList<ArrayList<Double>> identity_Matrix_N (int n) {
        ArrayList<ArrayList<Double>> identityMatrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            identityMatrix.add(new ArrayList<Double>());
            for (int j = 0; j < n; j++)
                if (j != i)
                    identityMatrix.get(i).add(0.0);
                else
                    identityMatrix.get(i).add(1.0);
        }
        return identityMatrix;
    }
    public void make_square (ArrayList<ArrayList<Double>> matrix) {
        int max_size = 0;
        int min_size = matrix.get(0).size();
        // determine the longest and shortest rows in the matrix
        for (ArrayList<Double> row_vector : matrix) {
            max_size = Math.max(row_vector.size(), max_size);
            min_size = Math.min(row_vector.size(), min_size);
        }
        // check if square (longest and shortest row are the same)
        if (max_size == matrix.size() && min_size == matrix.size())
            return;
        // add zero rows if longest row is longer than the number of rows
        if (max_size > matrix.size()) {
            while (matrix.size() < max_size) {
                ArrayList<Double> row_vector = new ArrayList<>();
                while (row_vector.size() < max_size)
                    row_vector.add(0.0);
                matrix.add(row_vector);
            }
        }
        // do this if not square regardless of length (may have rows shorter than number of rows but won't affect it if
        // it's already the same length as the number of rows)
        for (ArrayList<Double> row_vector : matrix) {
            while (row_vector.size() < matrix.size())
                row_vector.add(0.0);
        }
    }
    // source: https://www.geeksforgeeks.org/java-program-to-find-the-determinant-of-a-matrix/
    public double determinant (ArrayList<ArrayList<Double>> matrix)
    {
        make_square(matrix);
        double num1, num2, det = 1,
                total = 1; // Initialize result
        int index;
        int n = matrix.size();

        // temporary array for storing row
        ArrayList<Double> temp = new ArrayList<>();

        // loop for traversing the diagonal elements
        for (int i = 0; i < n; i++) {
            index = i; // initialize the index

            // finding the index which has non zero value
            while (matrix.get(index).get(i) == 0 && index < n)
                index++;
            if (index == n) // if there is non zero element
                continue;
                // the determinant of matrix as zero

            if (index != i) {
                // loop for swapping the diagonal element row
                // and index row
                for (int j = 0; j < n; j++) {
                    swap(matrix, index, j, i, j);
                }
                // determinant sign changes when we shift
                // rows go through determinant properties
                det = (int)(det * Math.pow(-1, index - i));
            }

            // storing the values of diagonal row elements
            for (int j = 0; j < n; j++)
                temp.set(j, matrix.get(i).get(j));

            // traversing every row below the diagonal
            // element
            for (int j = i + 1; j < n; j++) {
                num1 = temp.get(i); // value of diagonal element
                num2 = matrix.get(j).get(i); // value of next row element
                // traversing every column of row
                // and multiplying to every row
                for (int k = 0; k < n; k++) {
                    // multiplying to make the diagonal
                    // element and next row element equal
                    matrix.get(j).set(k, (num1 * matrix.get(j).get(k)) - (num2 * temp.get(k)));
                }
                total = total * num1; // Det(kA)=kDet(A);
            }
        }

        // multiplying the diagonal elements to get
        // determinant
        for (int i = 0; i < n; i++) {
            det = det * matrix.get(i).get(i);
        }
        return (det / total); // Det(kA)/k=Det(A);
    }

    // source: https://www.sanfoundry.com/java-program-gaussian-elimination-algorithm/
    // returns a solution vector
    public ArrayList<Double> solve_solution (ArrayList<ArrayList<Double>> matrix, ArrayList<Double> vector) {
        make_square(matrix);
        set_length(vector, matrix.size());
        int N = vector.size();
        for (int k = 0; k < N; k++) {

            // find pivot row
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(matrix.get(i).get(k)) > Math.abs(matrix.get(max).get(k)))
                    max = i;

            // swap row in A matrix
            ArrayList<Double> temp = matrix.get(k);
            matrix.set(k, matrix.get(max));
            matrix.set(max, temp);

            // swap corresponding values in constants matrix
            double t = vector.get(k);
            vector.set(k, vector.get(max));
            vector.set(max, t);

            // pivot within A and B
            for (int i = k + 1; i < N; i++) {
                double factor = matrix.get(i).get(k) / matrix.get(k).get(k);
                vector.set(i, vector.get(i) - factor * vector.get(k));
                for (int j = k; j < N; j++)
                    matrix.get(i).set(j, matrix.get(i).get(j) - factor * matrix.get(k).get(j));
            }
        }

        // back substitution
        ArrayList<Double> solution = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += matrix.get(i).get(j) * solution.get(j);
            solution.set(i, (vector.get(i) - sum) / matrix.get(i).get(i));
        }
        return solution;
    }
    // source: https://www.sanfoundry.com/java-program-gaussian-elimination-algorithm/
    // returns a solution matrix in REF form (the last column will be the solution vector)
    public ArrayList<ArrayList<Double>> solve_REF (ArrayList<ArrayList<Double>> matrix, ArrayList<Double> vector) {
        make_square(matrix);
        set_length(vector, matrix.size());
        int N = vector.size();
        for (int k = 0; k < N; k++) {

            // find pivot row
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(matrix.get(i).get(k)) > Math.abs(matrix.get(max).get(k)))
                    max = i;

            // swap row in A matrix
            ArrayList<Double> temp = matrix.get(k);
            matrix.set(k, matrix.get(max));
            matrix.set(max, temp);

            // swap corresponding values in constants matrix
            double t = vector.get(k);
            vector.set(k, vector.get(max));
            vector.set(max, t);

            // pivot within A and B
            for (int i = k + 1; i < N; i++) {
                double factor = matrix.get(i).get(k) / matrix.get(k).get(k);
                vector.set(i, vector.get(i) - factor * vector.get(k));
                for (int j = k; j < N; j++)
                    matrix.get(i).set(j, matrix.get(i).get(j) - factor * matrix.get(k).get(j));
            }
        }
        // put it together & return the solution matrix
        matrix.add(vector);
        return matrix;
    }
    // source: https://www.sanfoundry.com/java-program-gaussian-elimination-algorithm/
    // returns a solution matrix in RREF form (the last column will be the solution vector)
    public ArrayList<ArrayList<Double>> solve_RREF (ArrayList<ArrayList<Double>> matrix, ArrayList<Double> vector) {
        // first, set matrix and vector length to be the same
        set_length(vector, matrix.size());
        // make matrix a square matrix to assist with solving
        make_square(matrix);
        // and also account for the change in the size with the vector
        set_length(vector, matrix.size());


        int N = vector.size();
        for (int k = 0; k < N; k++) {

            // find pivot row
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(matrix.get(i).get(k)) > Math.abs(matrix.get(max).get(k)))
                    max = i;

            // swap row in A matrix
            ArrayList<Double> temp = matrix.get(k);
            matrix.set(k, matrix.get(max));
            matrix.set(max, temp);

            // swap corresponding values in constants matrix
            double t = vector.get(k);
            vector.set(k, vector.get(max));
            vector.set(max, t);

            // pivot within A and B
            for (int i = k + 1; i < N; i++) {
                double factor = matrix.get(i).get(k) / matrix.get(k).get(k);
                vector.set(i, vector.get(i) - factor * vector.get(k));
                for (int j = k; j < N; j++)
                    matrix.get(i).set(j, matrix.get(i).get(j) - factor * matrix.get(k).get(j));
            }
        }
        // get the solution vector
        ArrayList<Double> solution = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += matrix.get(i).get(j) * solution.get(j);
            solution.set(i, (vector.get(i) - sum) / matrix.get(i).get(i));
        }
        // make the N x N identity matrix
        ArrayList<ArrayList<Double>> RREF_matrix = identity_Matrix_N(N);
        RREF_matrix.add(solution);
        return RREF_matrix;
    }
    public ArrayList<ArrayList<Double>> add_matricies () {

    }
    public ArrayList<ArrayList<Double>> multiply () {

    }
}
