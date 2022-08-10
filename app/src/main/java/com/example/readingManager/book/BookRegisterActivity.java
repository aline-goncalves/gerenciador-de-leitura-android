package com.example.readingManager.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.readingManager.R;
import java.util.ArrayList;

public class BookRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editTextBookTitle;
    private EditText editTextIsbnCode;
    private EditText editTextBookAuthor;
    private EditText editTextTextPublisherCompany;
    private EditText editTextPagesAmount;

    private RadioButton radioButtonRead;
    private RadioButton radioButtonReading;
    private RadioButton radioButtonToRead;
    private RadioButton radioButtonStopped;

    private CheckBox checkBoxAction;
    private CheckBox checkBoxThriller;
    private CheckBox checkBoxRomance;
    private CheckBox checkBoxOther;
    private CheckBox checkBoxSitcom;

    private Spinner spinnerTags;

    private Book book = new Book();
    private ArrayList<String> literaryGenres = new ArrayList<>();
    private boolean isTagSelected = false;
    private int mode;
    private BookListActivity bookListActivity = new BookListActivity();
    private static int selectedPosition = -1;

    public static int FORM_FILLED = 0;
    public static final String MODE = "MODE";
    public static final String TITLE = "TITLE";
    public static final String ISBN = "ISBN";
    public static final String AUTHOR = "AUTHOR";
    public static final String PUBLISHER_COMPANY = "PUBLISHER_COMPANY";
    public static final String PAGES_AMOUNT = "PAGES_AMOUNT";
    public static final String LITERARY_GENRE = "LITERARY_GENRE";
    public static final String STATUS = "STATUS";
    public static final String TAG = "TAG";
    public static final int NEW = 1;
    public static final int EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_register);
        findComponents();
        populateSpinnerTags();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            mode = bundle.getInt(MODE, NEW);

            if (mode == NEW){
                setTitle(getString(R.string.new_book));

            }else{
                populateFieldsForEdition(bundle);
                setTitle(getString(R.string.edited_book));
            }
        }
    }

    private void populateFieldsForEdition(Bundle bundle){
        book = new Book();
        if(!bundle.isEmpty()) {
            book.setTitle(bundle.getString(TITLE));
            book.setIsbnCode(bundle.getString(ISBN));
            book.setAuthor(bundle.getString(AUTHOR));
            book.setPublisherCompany(bundle.getString(PUBLISHER_COMPANY));
            book.setPagesAmount(Integer.parseInt(bundle.getString(PAGES_AMOUNT)));
            book.setLiteraryGenres(bundle.getStringArray((LITERARY_GENRE)));
            book.setStatus(bundle.getString(STATUS));
            book.setTag(bundle.getString(TAG));

            editTextBookTitle.setText(bundle.getString(TITLE));
            editTextIsbnCode.setText(bundle.getString(ISBN));
            editTextBookAuthor.setText(bundle.getString(AUTHOR));
            editTextTextPublisherCompany.setText(bundle.getString(PUBLISHER_COMPANY));
            editTextPagesAmount.setText(bundle.getString(PAGES_AMOUNT));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_options, menu);
        return true;
    }

    public static void newBook(AppCompatActivity appCompatActivity){
        Intent intent = new Intent(appCompatActivity, BookRegisterActivity.class);
        intent.putExtra(MODE, NEW);
        appCompatActivity.startActivityForResult(intent, NEW);
    }

    public static void editBook(AppCompatActivity appCompatActivity, Book book, int position){
        Intent intent = new Intent(appCompatActivity, BookRegisterActivity.class);
        selectedPosition = position;

        intent.putExtra(MODE, EDIT);
        intent.putExtra(TITLE, book.getTitle());
        intent.putExtra(ISBN, book.getIsbnCode());
        intent.putExtra(AUTHOR, book.getAuthor());
        intent.putExtra(PUBLISHER_COMPANY, book.getPublisherCompany());
        intent.putExtra(PAGES_AMOUNT, String.valueOf(book.getPagesAmount()));
        intent.putExtra(LITERARY_GENRE, book.getLiteraryGenres());
        intent.putExtra(STATUS, book.getStatus());
        intent.putExtra(TAG, book.getTag());

        appCompatActivity.startActivityForResult(intent, EDIT);
    }

    private void findComponents(){
        editTextBookTitle = findViewById(R.id.editTextBookTitle);
        editTextIsbnCode = findViewById(R.id.editTextIsbnCode);
        editTextBookAuthor = findViewById(R.id.editTextTextBookAuthor);
        editTextTextPublisherCompany = findViewById(R.id.editTextTextPublisherCompany);
        editTextPagesAmount = findViewById(R.id.editTextPagesAmount);

        radioButtonRead = findViewById(R.id.radioButtonRead);
        radioButtonReading = findViewById(R.id.radioButtonReading);
        radioButtonToRead = findViewById(R.id.radioButtonToRead);
        radioButtonStopped = findViewById(R.id.radioButtonStopped);

        checkBoxAction = findViewById(R.id.checkBoxAction);
        checkBoxThriller = findViewById(R.id.checkBoxThriller);
        checkBoxRomance = findViewById(R.id.checkBoxRomance);
        checkBoxOther = findViewById(R.id.checkBoxOther);
        checkBoxSitcom = findViewById(R.id.checkBoxSitcom);

        spinnerTags = (Spinner) findViewById(R.id.spinnerTags);
        spinnerTags.setOnItemSelectedListener(this);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButtonRead:
                if (checked) {
                    book.setStatus(radioButtonRead.getText().toString());
                }
                    break;
            case R.id.radioButtonReading:
                if (checked) {
                    book.setStatus(radioButtonReading.getText().toString());
                }
                    break;
            case R.id.radioButtonToRead:
                if (checked) {
                    book.setStatus(radioButtonToRead.getText().toString());
                }
                break;

            case R.id.radioButtonStopped:
                if (checked) {
                    book.setStatus(radioButtonStopped.getText().toString());
                }
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkBoxAction:
                if (checked) {
                    literaryGenres.add(checkBoxAction.getText().toString());
                }
                break;
            case R.id.checkBoxRomance:
                if (checked) {
                    literaryGenres.add(checkBoxRomance.getText().toString());
                }
                break;
            case R.id.checkBoxThriller:
                if (checked) {
                    literaryGenres.add(checkBoxThriller.getText().toString());
                }
                break;

            case R.id.checkBoxSitcom:
                if (checked) {
                    literaryGenres.add(checkBoxSitcom.getText().toString());
                }
                break;

            default:
                literaryGenres.add(checkBoxOther.getText().toString());
                break;
        }
    }

    public void populateSpinnerTags(){
        Spinner spinnerTags = (Spinner) findViewById(R.id.spinnerTags);
        ArrayAdapter<CharSequence> spinnerTagsAdapter = ArrayAdapter
                .createFromResource(
                        this,
                        R.array.tags,
                        R.layout.support_simple_spinner_dropdown_item);
        spinnerTagsAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTags.setAdapter(spinnerTagsAdapter);
    }

    public void clearBookData(){
        clearBookFields();
        sendToastToTheView("Todos os campos foram limpos!");
     }

    private void submit(){
        if(!isValidData()){
            sendToastToTheView("Preencha todos os campos!");
        }

        if(isValidData() && mode == NEW) {
            startActivityForResult(setDataBookNew(), FORM_FILLED);
            clearBookFields();
            sendToastToTheView("Dados enviados com sucesso!");
        }

        if(isValidData() && mode == EDIT){
            startActivityForResult(setDataBookEdit(), FORM_FILLED);
            clearBookFields();
            sendToastToTheView("Dados enviados com sucesso!");
        }
    }

    public void callBookListActivity(View view){
        Intent intent = new Intent(this, BookListActivity.class);
        startActivity(intent);
    }

    private void clearBookFields(){
        editTextBookTitle.setText(null);
        editTextIsbnCode.setText(null);
        editTextBookAuthor.setText(null);
        editTextTextPublisherCompany.setText(null);
        editTextPagesAmount.setText(null);

        radioButtonRead.setChecked(false);
        radioButtonReading.setChecked(false);
        radioButtonToRead.setChecked(false);
        radioButtonStopped.setChecked(false);

        checkBoxAction.setChecked(false);
        checkBoxRomance.setChecked(false);
        checkBoxThriller.setChecked(false);
        checkBoxSitcom.setChecked(false);
        checkBoxOther.setChecked(false);

        spinnerTags.setSelected(false);
    }

    private void sendToastToTheView(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidData(){
        if(editTextBookTitle.getText() == null ||
                editTextIsbnCode.getText() == null ||
                editTextBookAuthor.getText() == null ||
                editTextTextPublisherCompany.getText() == null ||
                editTextPagesAmount.getText() == null) {
            return false;
        }

        if(!radioButtonRead.isChecked() &&
                !radioButtonReading.isChecked() &&
                !radioButtonToRead.isChecked() &&
                !radioButtonStopped.isChecked()){
            return false;
        }

        if(!checkBoxOther.isChecked() &&
                !checkBoxAction.isChecked() &&
                !checkBoxRomance.isChecked() &&
                !checkBoxSitcom.isChecked() &&
                !checkBoxThriller.isChecked()){
            return false;
        }

        if(!isTagSelected){
            return false;
        }

        return true;
    }

    private Intent setDataBookIntent(){
        Intent intent = new Intent(this, BookListActivity.class);

        intent.putExtra(BookListActivity.TITLE, editTextBookTitle.getText().toString());
        intent.putExtra(BookListActivity.ISBN_CODE, editTextIsbnCode.getText().toString());
        intent.putExtra(BookListActivity.AUTHOR, editTextBookAuthor.getText().toString());
        intent.putExtra(BookListActivity.PUBLISHER_COMPANY, editTextTextPublisherCompany.getText().toString());
        intent.putExtra(BookListActivity.PAGES_AMOUNT, editTextPagesAmount.getText().toString());

        FORM_FILLED = 1;

        return intent;
    }

    private Book setDataBook(){
        book.setTitle(editTextBookTitle.getText().toString());
        book.setIsbnCode(editTextIsbnCode.getText().toString());
        book.setAuthor(editTextBookAuthor.getText().toString());
        book.setPublisherCompany(editTextTextPublisherCompany.getText().toString());
        book.setPagesAmount(Integer.parseInt(editTextPagesAmount.getText().toString()));
        book.setLiteraryGenres(populateGenres(literaryGenres));

        return book;
    }

    private String[] populateGenres(ArrayList<String> literaryGenres){
        String[] genres = new String[literaryGenres.size()];
        for (int i = 0; i < literaryGenres.size(); i++) {
            genres[i] = literaryGenres.get(i);
        }

        return genres;
    }

    private Intent setDataBookNew(){
        Book book = setDataBook();
        bookListActivity.getBooks().add(book);

        Intent intent = setDataBookIntent();
        return intent;
    }

    private Intent setDataBookEdit(){
        setDataBook();
        Intent intent = setDataBookIntent();
        intent.putExtra(String.valueOf(BookListActivity.POSITION), selectedPosition);

        return intent;
    }

    private static void getDataBook(Book book){
        System.out.println("Título: " + book.getTitle());
        System.out.println("Código ISBN: " + book.getIsbnCode());
        System.out.println("Autor: " + book.getAuthor());
        System.out.println("Editora: " + book.getPublisherCompany());
        System.out.println("Quantidade de páginas: " + book.getPagesAmount());
        System.out.println("Status: " + book.getStatus());
        System.out.println("Etiqueta: " + book.getTag());
        System.out.println("Gêneros literários: ");
        for(String genre : book.getLiteraryGenres()) {
            System.out.println(genre + "\n");
        }
    }

    @Override
    public void onItemSelected(@NonNull AdapterView<?> adapterView, View view, int position, long id) {
        book.setTag(adapterView.getItemAtPosition(position).toString());
        isTagSelected=true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        isTagSelected=false;
        sendToastToTheView("Preencha todos os campos!");
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    private void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.send_data:
                submit();
                return true;

            case android.R.id.home:
                cancel();
                return true;

            case R.id.clear_data:
                clearBookData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
