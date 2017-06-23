package io.keepcoding.madridshops.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsFromCacheInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsFromCacheInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorCompletion;
import io.keepcoding.madridshops.domain.interactors.GetAllShopsInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractor;
import io.keepcoding.madridshops.domain.interactors.GetIfAllShopsAreCachedInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.InteractorErrorCompletion;
import io.keepcoding.madridshops.domain.interactors.SetAllShopsCachedInteractor;
import io.keepcoding.madridshops.domain.interactors.SetAllShopsCachedInteractorImpl;
import io.keepcoding.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerDAOImpl;
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

        GetIfAllShopsAreCachedInteractor getIfAllShopsAreCaachedInteractor = new GetIfAllShopsAreCachedInteractorImpl(getBaseContext());
        getIfAllShopsAreCaachedInteractor.executed(new Runnable() {
            @Override
            public void run() {
                // all cached already, no need to download things, just read from DB
                readDataFromCache();
            }
        }, new Runnable() {
            @Override
            public void run() {
                // nothing cached yet
                obtainShopsList();
            }
        });

    }

    private void readDataFromCache() {
        GetAllShopsFromCacheManager getAllShopsFromCacheManager = new GetAllShopsFromCacheManagerDAOImpl(this);
        GetAllShopsFromCacheInteractor getAllShopsFromCacheInteractor = new GetAllShopsFromCacheInteractorImpl(getAllShopsFromCacheManager);
        getAllShopsFromCacheInteractor.execute(new GetAllShopsInteractorCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                configShopsFragment(shops);
            }
        });
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

                        // TODO: persist in cached all shops
                        configShopsFragment(shops);

                        SetAllShopsCachedInteractor setAllShopsCachedInteractor = new SetAllShopsCachedInteractorImpl(getBaseContext());
                        setAllShopsCachedInteractor.execute(true);
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(@NonNull final String errorDescription) {

                    }
                }

        );
    }

    private void configShopsFragment(final Shops shops) {
        shopsFragment.setShops(shops);
        shopsFragment.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop element, int position) {
                // TODO: finish
                Navigator.navigateFromShopListActivityToShopDetailActivity(ShopsListActivity.this, element, position);
            }
        });
    }
}
