package com.example.readingManager.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
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
    private boolean isTagSelected=false;

    public static int FORM_FILLED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_register);
        findComponents();
        populateSpinnerTags();
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

    public void clearBookData(View view){
        clearBookFields(view);
        sendToastToTheView("Todos os campos foram limpos!");
     }

    @SuppressWarnings("deprecation")
    public void submit(View view){
         if(isValidData()) {
            startActivityForResult(setDataBook(view), FORM_FILLED);
            clearBookFields(view);
            sendToastToTheView("Dados enviados com sucesso!");
         }else{
            sendToastToTheView("Preencha todos os campos!");
        }
    }

    public void callBookListActivity(View view){
        Intent intent = new Intent(this, BookListActivity.class);
        startActivity(intent);
    }

    private void clearBookFields(View view){
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

    private Intent setDataBook(View view){
        Intent intent = new Intent(this, BookListActivity.class);

        book.setTitle(editTextBookTitle.getText().toString());
        book.setIsbnCode(editTextIsbnCode.getText().toString());
        book.setAuthor(editTextBookAuthor.getText().toString());
        book.setPublisherCompany(editTextTextPublisherCompany.getText().toString());
        book.setPagesAmount(Integer.parseInt(editTextPagesAmount.getText().toString()));
        book.setLiteraryGenres(literaryGenres);

        intent.putExtra(BookListActivity.TITLE, editTextBookTitle.getText().toString());
        intent.putExtra(BookListActivity.ISBN_CODE, editTextIsbnCode.getText().toString());
        intent.putExtra(BookListActivity.AUTHOR, editTextBookAuthor.getText().toString());
        intent.putExtra(BookListActivity.PUBLISHER_COMPANY, editTextTextPublisherCompany.getText().toString());
        intent.putExtra(String.valueOf(BookListActivity.PAGES_AMOUNT), Integer.parseInt(editTextPagesAmount.getText().toString()));

        FORM_FILLED = 1;

        return intent;
    }

    private void getDataBook(){
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        book.setTag(adapterView.getItemAtPosition(position).toString());
        isTagSelected=true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        isTagSelected=false;
        sendToastToTheView("Preencha todos os campos!");
    }
}
