package br.com.livraria.bean;

import br.com.livraria.dao.LivroDAO;
import br.com.livraria.entity.Livro;
import br.com.livraria.entity.Venda;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class VendaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private LivroDAO livroDAO;
    private BarChartModel barModel;

    public BarChartModel getBarModel() {
        return barModel;
    }

    @PostConstruct
    public void  init() {
        getVendasModel();
    }

    public List<Venda> getVendas() {
        List<Livro> livros = livroDAO.getAll();
        List<Venda> vendas = new ArrayList<>();

        Random randon = new Random(Calendar.getInstance().getTimeInMillis());

        for (Livro livro : livros) {
            Integer quantidade = randon.nextInt(500);
            vendas.add(new Venda(livro, quantidade));
        }
        return vendas;
    }

    public void getVendasModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setLabel("Vendas dos Livros");

        List<Venda> vendaList = getVendas();
        dataSet.setData(vendaList.parallelStream().map(v -> v.getQtd()).collect(Collectors.toList()));

        Random randomColor = new Random(Calendar.getInstance().getTimeInMillis());

        List<String> bgColor = new ArrayList<>();
        for (int i = 0; i < vendaList.size(); i++) {
            Integer color1 = randomColor.nextInt(100) + 155;
            Integer color2 = randomColor.nextInt(100) + 155;
            Integer color3 = randomColor.nextInt(100) + 155;
            Double opacidade = randomColor.nextDouble() / 2 + 0.3;
            bgColor.add("rgba(" + color1 + "," + color2 + "," + color3 + "," + String.format("%.2f", opacidade) + ")");
        }
        dataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();

        for (int i = 0; i < vendaList.size(); i++) {
            Integer color1 = randomColor.nextInt(100) + 155;
            Integer color2 = randomColor.nextInt(155);  
            Integer color3 = randomColor.nextInt(100) + 80;
            Double opacidade = randomColor.nextDouble();
            borderColor.add("rgba(" + color1 + "," + color2 + "," + color3 + "," + String.format("%.2f", opacidade) + ")");
        }
        dataSet.setBorderColor(borderColor);
        dataSet.setBorderWidth(1);

        data.addChartDataSet(dataSet);
        data.setLabels(vendaList.parallelStream().map(v -> v.getLivro().getTitulo()).collect(Collectors.toList()));

        barModel.setData(data);
    }
}
