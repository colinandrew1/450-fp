package cpsc450;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import static java.lang.System.out;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PerformanceTest {

    static Graph createAdjMatrix(int n) {
        Graph graph = new AdjMatrix((2 * n) - 1);  // Total nodes (main path + limbs)
        
        if (n == 1) return graph;  // If n == 1, just return the empty graph
        
        // Main horizontal path
        for (int i = 0; i < n - 1; i++) {
            //graph.addEdge(i, i + 1);  // Add edge along the main path
            // System.out.println("added: (" + i + "," + (i + 1) + ")");
        }
        
        // Limb nodes
        int nextNode = n;  // Starting index for limb nodes
        
        for (int i = 1; i < n; i++) {
            // "Above" nodes: Create the chain
            int prevAbove = i;  // Start from the current main path node
            for (int j = 0; j < i; j++) {
                graph.addEdge(prevAbove, nextNode);
                // System.out.println("added: (" + prevAbove + "," + nextNode + ")");
                prevAbove = nextNode;  // Move to the next node in the chain
                nextNode++;
            }
            
            // "Below" nodes: Same structure as above
            int prevBelow = i;
            for (int j = 0; j < i; j++) {
                graph.addEdge(prevBelow, nextNode);
                // System.out.println("added: (" + prevBelow + "," + nextNode + ")");
                prevBelow = nextNode;  // Move to the next node in the chain
                nextNode++;
            }
        }
        return graph;
    }
    
    //     Graph graph = new AdjMatrix(n);
    //     for (int x = 0; x < n; ++x)
    //         for (int y = 0; y < n; ++y)
    //             if (x != y)
    //                 graph.addEdge(x, y); // Create a fully connected graph for this example
    //     return graph;
    // }


    static long timeFindKernelBruteForce(Graph g) throws Exception {
        long start = System.currentTimeMillis();
        Set<Integer> result = DigraphKernel.findKernelOptimized(g);    
        if(!result.isEmpty());
        long end = System.currentTimeMillis();
        return end - start;
    }


    static long timeFindKernelNaive(Graph g) throws Exception {
        long start = System.currentTimeMillis();
        DigraphKernel.findKernelNaive(g);    
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * Create a chart comparing the times of brute force vs naive kernel finding.
     */  
    static void chart(XYSeries[] series, String title, String file) throws Exception {
        XYSeriesCollection ds = new XYSeriesCollection();
        for (XYSeries s : series)
            ds.addSeries(s);
        // build the chart
        JFreeChart chart = ChartFactory.createXYLineChart(title, "vertices", "time (ms)", ds);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(220, 220, 220));
        // configure the chart
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        for (int i = 0; i < series.length; ++i) {
            renderer.setSeriesShapesVisible(i, true);
            renderer.setSeriesShapesFilled(i, true);
            renderer.setSeriesStroke(i, new BasicStroke(2.5f));
        }
        // save the result
        int width = 640; // 1024;
        int height = 480; // 768;
        File lineChart = new File(file);
        ChartUtils.saveChartAsPNG(lineChart, chart, width, height);
    }

    /**
     * Run the performance tests comparing brute force vs naive for kernel-finding.
     */  
    static void runKernelFindingPerformance() throws Exception {
        out.println("----------------------------------------------------");
        out.println(" KERNEL-FINDING PERFORMANCE: Optimized vs NAIVE");
        out.println("----------------------------------------------------");    
        int STEP = 50;
        int END = 1000; // For brute force, we limit the size to 17 vertices
        XYSeries s1a = new XYSeries("Optimized");
        XYSeries s1b = new XYSeries("Naive");    
        
        s1a.add(0, 0);
        s1b.add(0, 0);
        for (int n = STEP; n <= END; n = n + STEP) {
            Graph g = createAdjMatrix(n);
            // System.out.println(n);
            // System.out.println("---------------------------------------------------------------");
            long t1 = timeFindKernelBruteForce(g);
            long t2 = timeFindKernelNaive(g);
            s1a.add(n, t1);
            s1b.add(n, t2);
            out.println("Graph size " + n + " - Brute Force: " + t1 + " ms, Naive: " + t2 + " ms");
        }
        
        XYSeries[] series = {s1a, s1b};
        String title = "Kernel Finding Performance: Optimized vs Naive";
        chart(series, title, "kernel_finding_comparison.png");
    }

    public static void main(String[] args) throws Exception {
        runKernelFindingPerformance();
    }
}
