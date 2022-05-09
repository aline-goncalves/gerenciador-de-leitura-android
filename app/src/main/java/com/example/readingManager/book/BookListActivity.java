package com.example.readingManager.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.readingManager.R;
import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ListView listViewBooks;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayAdapter<String> bookArrayAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        findComponentsFromView();
    }

    private void findComponentsFromView(){
        listViewBooks = findViewById(R.id.listViewBooks);
        setAdapterOnListView();
    }

    private void setAdapterOnListView(){
        populateAdapter();
        listViewBooks.setAdapter(bookArrayAdapter);
    }

    private void populateAdapter(){
        populateBooksList();

        for (Book book : books){
            titles.add(book.getTitle() + " - ISBN: " + book.getIsbnCode());
            System.out.println(book.getTitle() + " - ISBN: " + book.getIsbnCode() + "\n");
        }

        bookArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
    }

    private void populateBooksList(){
        Book book;
        List<String> literaryGenres = new ArrayList<>();

        book = new Book();
        book.setTitle("O Código Limpo");
        book.setIsbnCode("9788576082675");
        book.setAuthor("Robert C. Martin");
        book.setPublisherCompany("Alta Books");
        book.setPagesAmount(440);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_read));
        book.setTag(getResources().getStringArray(R.array.tags)[9]);
        books.add(book);

        book = new Book();
        book.setTitle("O Codificador Limpo");
        book.setIsbnCode("9788576086475");
        book.setAuthor("Robert C. Martin");
        book.setPublisherCompany("Alta Books");
        book.setPagesAmount(244);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_read));
        book.setTag(getResources().getStringArray(R.array.tags)[8]);
        books.add(book);

        book = new Book();
        book.setTitle("Arquitetura Limpa");
        book.setIsbnCode("9788550804606");
        book.setAuthor("Robert C. Martin");
        book.setPublisherCompany("Alta Books");
        book.setPagesAmount(415);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_to_read));
        book.setTag(getResources().getStringArray(R.array.tags)[9]);
        books.add(book);

        book = new Book();
        book.setTitle("Desenvolvimento Ágil Limpo - De Volta Às Origens");
        book.setIsbnCode("9788550815008");
        book.setAuthor("Robert C. Martin");
        book.setPublisherCompany("Alta Books");
        book.setPagesAmount(191);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_reading));
        book.setTag(getResources().getStringArray(R.array.tags)[8]);
        books.add(book);

        book = new Book();
        book.setTitle("Scrum: a arte de fazer o dobro do trabalho na metade do tempo");
        book.setIsbnCode("9788544100875");
        book.setAuthor("Jeff Sutherland");
        book.setPublisherCompany("Leya Brasil");
        book.setPagesAmount(190);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_to_read));
        book.setTag(getResources().getStringArray(R.array.tags)[14]);
        books.add(book);

        book = new Book();
        book.setTitle("Test-Driven Development: Teste e Design no Mundo Real com Java");
        book.setIsbnCode("9788566250046");
        book.setAuthor("Mauricio Aniche");
        book.setPublisherCompany("Casa do Código");
        book.setPagesAmount(194);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_to_read));
        book.setTag(getResources().getStringArray(R.array.tags)[9]);
        books.add(book);

        book = new Book();
        book.setTitle("O Homem Mais Rico da Babilônia");
        book.setIsbnCode("9788595081536");
        book.setAuthor("George Samuel Clason");
        book.setPublisherCompany("Harper Collins");
        book.setPagesAmount(160);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_reading));
        book.setTag(getResources().getStringArray(R.array.tags)[15]);
        books.add(book);

        book = new Book();
        book.setTitle("Mindset: A nova psicologia do sucesso");
        book.setIsbnCode("9788547000240");
        book.setAuthor("Carol S. Dweck");
        book.setPublisherCompany("Objetiva");
        book.setPagesAmount(312);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_read));
        book.setTag(getResources().getStringArray(R.array.tags)[8]);
        books.add(book);

        book = new Book();
        book.setTitle("Mais esperto que o Diabo: O mistério revelado da liberdade e do sucesso");
        book.setIsbnCode("9788568014004");
        book.setAuthor("Napoleon Hill");
        book.setPublisherCompany("Citadel Editora");
        book.setPagesAmount(280);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_to_read));
        book.setTag(getResources().getStringArray(R.array.tags)[8]);
        books.add(book);

        book = new Book();
        book.setTitle("A garota que não se calou");
        book.setIsbnCode("9786559240104");
        book.setAuthor("Abi Daré");
        book.setPublisherCompany("Verus");
        book.setPagesAmount(352);
        literaryGenres.add(getResources().getString(R.string.checkbox_other));
        book.setLiteraryGenres(literaryGenres);
        book.setStatus(getResources().getString(R.string.book_reading_status_reading));
        book.setTag(getResources().getStringArray(R.array.tags)[6]);
        books.add(book);
    }

    private void sendToastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println(listViewBooks.getSelectedItem().toString());
        sendToastMessage("Você selecionou o livro: " + listViewBooks.getSelectedItem().toString());

        Intent intent = new Intent(this, BookDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        sendToastMessage("Nenhum livro foi selecionado!");
    }

    public void onPointerCaptureChanged(boolean hasCapture) {}
}
