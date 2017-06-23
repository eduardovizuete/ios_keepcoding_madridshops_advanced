package io.keepcoding.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorFakeImpl;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.InteractorErrorCompletion;
import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import io.keepcoding.madridshops.domain.managers.network.NetworkManager;
import io.keepcoding.madridshops.domain.model.Shop;
import io.keepcoding.madridshops.domain.model.Shops;
import io.keepcoding.madridshops.fragments.ShopsFragment;
import io.keepcoding.madridshops.navigator.Navigator;
import io.keepcoding.madridshops.views.OnElementClick;

public class ShopsListActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_list__progress_bar) ProgressBar progressBar;
    ShopsFragment shopsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);
        ButterKnife.bind(this);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_list__fragment_shop);

        obtainShopsList();

    }

    private void obtainShopsList() {
        progressBar.setVisibility(View.VISIBLE);

        NetworkManager manager = new GetAllShopsManagerImpl(this);
        GetAllShopsInteractor getAllShopsInteractor = new GetAllShopsInteractorImpl(manager);
        getAllShopsInteractor.execute(
                new GetAllShopsInteractorCompletion() {
                    @Override
                    public void completion(@NonNull final Shops shops) {
                        System.out.println("Hello hello");

                        progressBar.setVisibility(View.INVISIBLE);

                        shopsFragment.setShops(shops);
                        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
                            @Override
                            public void clickedOn(@NonNull Shop element, int position) {
                                // TODO: finish
                                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopsListActivity.this, element, position);
                            }
                        });
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(@NonNull final String errorDescription) {

                    }
                }

        );
    }
}
