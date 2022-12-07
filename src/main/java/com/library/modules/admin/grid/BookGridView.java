package com.library.modules.admin.grid;

import com.library.entity.Book;
import com.library.entity.BookCategory;
import com.library.entity.User;
import com.library.modules.template.AdminTemplate;
import com.library.mvputils.BaseView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
@Route(value = "book/grid",layout = AdminTemplate.class)
public class BookGridView extends BaseView<BookGridPresenter> {

    private Grid<Book> bookGrid;
    private Grid.Column<Book> idColumn;
    private Grid.Column<Book> titleColumn;
    private Grid.Column<Book> authorColumn;
    private Grid.Column<Book> copiesColumn;
    private Grid.Column<Book> publicationDateColumn;
    private Grid.Column<Book> categoryColumn;

    private TextField idFilter;
    private TextField titleFilter;
    private TextField authorFilter;
    private ComboBox<String> categoryFilter;

    private List<BookCategory> allBookCategory;



    @Override
    protected void init() {
        setSizeFull();
        bookGrid=new Grid<>();
        bookGrid.setWidthFull();
        initializeGrid();
        setFilter();
        add(bookGrid);

    }

    private void initializeGrid() {
        allBookCategory=getPresenter().init();

        idColumn = bookGrid.addColumn(Book::getId).setHeader("Id");
        titleColumn = bookGrid.addColumn(Book::getTitle).setHeader("Title").setAutoWidth(true);
        authorColumn = bookGrid.addColumn(Book::getAuthor).setHeader("Author").setAutoWidth(true);
        copiesColumn = bookGrid.addColumn(Book::getCopiesAvailable).setHeader("Copies");
        publicationDateColumn = bookGrid.addColumn(Book::getPublicationDate).setHeader("Publication Date");
        categoryColumn = bookGrid.addColumn(book -> book.getCategory().getName()).setHeader("Category");
        bookGrid.setItems(getPresenter().getBooks());
    }

    private void setFilter(){
        idFilter=new TextField();
        idFilter.addValueChangeListener(event -> mainFilter());

        titleFilter=new TextField();
        titleFilter.addValueChangeListener(event -> mainFilter());

        authorFilter=new TextField();
        authorFilter.addValueChangeListener(event -> mainFilter());

        categoryFilter=new ComboBox<>();
        categoryFilter.setClearButtonVisible(true);
        categoryFilter.setItems(allBookCategory.stream()
                           .map(bookCategory -> bookCategory.getName()).distinct().collect(Collectors.toList()));
        categoryFilter.addValueChangeListener(event -> mainFilter());

        idFilter.setValueChangeMode(ValueChangeMode.LAZY);
        titleFilter.setValueChangeMode(ValueChangeMode.LAZY);
        authorFilter.setValueChangeMode(ValueChangeMode.LAZY);


        HeaderRow headerRow = bookGrid.appendHeaderRow();
        headerRow.getCell(idColumn).setComponent(idFilter);
        headerRow.getCell(titleColumn).setComponent(titleFilter);
        headerRow.getCell(authorColumn).setComponent(authorFilter);
        headerRow.getCell(categoryColumn).setComponent(categoryFilter);

    }

    private void mainFilter() {

        ListDataProvider<Book> dataProvider = (ListDataProvider<Book>) bookGrid.getDataProvider();
        dataProvider.addFilter(book -> {
            boolean b1=book.getId().equals(idFilter.getValue().equals("")?0l:Long.valueOf(idFilter.getValue()));
            boolean b2=book.getTitle().toLowerCase().contains(titleFilter.getValue().toLowerCase());
            boolean b3=book.getAuthor().toLowerCase().contains(authorFilter.getValue().toLowerCase());
            boolean b4=book.getCategory().getName().equals(categoryFilter.getValue());

            if(idFilter.getValue().equals("") || categoryFilter.getValue()==null){
                if(idFilter.getValue().equals("") && categoryFilter.getValue()==null){
                    return b2 && b3;
                } else if (idFilter.getValue().equals("")) {
                    return b2 && b3 && b4;
                }else{
                    return b1 && b2 && b3;
                }
            }
            return  b1 && b2 && b3 && b4;
        });
    }
}
