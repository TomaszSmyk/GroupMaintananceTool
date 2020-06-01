package view;

import model.Model;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.SortedSet;

/**
 * Class that creates line chart, gathers data from Model, places it into this chart and finally puts this chart into
 * CHARTS tab
 */
public class LineChart {

    private Model model = new Model(Model.activeGroup, Model.lessonNumber);

    /**
     * updates chart by recalculating dataset.
     * @return chart ready to be placed into panel
     */
    protected static JFreeChart updateChart() {
        LineChart lineChart = new LineChart();
        XYDataset dataset = lineChart.createDataset();
        return lineChart.createChart(dataset);
    }

    /**
     * Creates dataset from series returned from createSeriesForGroup() method
     * @return this dataset
     */
    protected XYDataset createDataset() {
        SortedSet<Integer> groups = model.updateGroupNumbers();
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int elem: groups) {
            dataset.addSeries(createSeriesForGroup(elem));
        }
        return dataset;
    }

    /**
     * Creates points that later on will be connected to create line
     * @param groupNumber - it's student's group number, used to place point at proper height
     * @return serie1 which is generally collection of points
     */
    private XYSeries createSeriesForGroup(int groupNumber) {
        XYSeries series1 = new XYSeries(groupNumber);
        for (int lesson = 1; lesson <=15; lesson++) {
            series1.add(lesson, model.getPresence(groupNumber, lesson));
        }
        return series1;
    }


    /**
     * Sets various parameters, such as color, font, etc. to crate chart
     * @param dataset - generally collection of lines
     * @return chart, ready to display
     */
    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Groups Class Attendance per Lesson",
                "Lesson",
                "Present",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

//        renderer.setSeriesPaint(0, Color.RED);
//        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
//        renderer.setSeriesPaint(1, Color.BLUE);
//        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

//        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Groups Class Attendance per Lesson",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }


}
