package view;

import model.Model;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;

import java.awt.*;
import java.util.SortedSet;


public class LineChart {

    private Model model = new Model(Model.activeGroup, Model.lessonNumber);

    public static JFreeChart updateChart() {
        LineChart lineChart = new LineChart();
        XYDataset dataset = lineChart.createDataset();
        return lineChart.createChart(dataset);
    }

    protected XYDataset createDataset() {
        SortedSet<Integer> groups = model.updateGroupNumbers();
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int elem: groups) {
            dataset.addSeries(createSeriesForGroup(elem));
        }
        return dataset;
    }

    private XYSeries createSeriesForGroup(int groupNumber) {
        XYSeries series1 = new XYSeries(groupNumber);
        for (int lesson = 1; lesson <=15; lesson++) {
            series1.add(lesson, model.getPresence(groupNumber, lesson));
        }

        return series1;
    }



    protected JFreeChart createChart(final XYDataset dataset) {

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

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Groups Class Attendance per Lesson",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }


}
