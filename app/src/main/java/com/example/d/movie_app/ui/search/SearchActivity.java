package com.example.d.movie_app.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.d.movie_app.R;
import com.example.d.movie_app.data_models.Movie_Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchViewInterface {

    @BindView(R.id.search_toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_results_rv)
    RecyclerView search_results_rv;

    private SearchView searchView;

    SearchPresenter searchPresenter;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    private void viewSetup(){
        setSupportActionBar(toolbar);
        search_results_rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMVP(){
        searchPresenter = new SearchPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);

        SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();

        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setQueryHint("Movie name ?");

        searchPresenter.getResultsBasedOnQuery(searchView);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        if (id == R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    @Override
    public void movieToast(String string) {
        Toast.makeText(SearchActivity.this, "Searching...", Toast.LENGTH_LONG).show();

    }

    @Override
    public void movieDisplay(Movie_Response movie_response) {

    }

    @Override
    public void movieError(String string) {

        movieToast(string);
    }
}



