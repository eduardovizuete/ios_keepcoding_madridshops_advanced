package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerCompletion;
import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import io.keepcoding.madridshops.domain.managers.network.ManagerErrorCompletion;
import io.keepcoding.madridshops.domain.managers.network.NetworkManager;
import io.keepcoding.madridshops.domain.managers.network.entities.ShopEntity;

public class GetAllShopsInteractorImpl implements GetAllShopsInteractor {
    private NetworkManager networkManager;


    public GetAllShopsInteractorImpl(@NonNull final NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void execute(@NonNull GetAllShopsInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        if (this.networkManager == null) {
            if (onError != null) {
                throw new IllegalStateException("Network manager can't be null");
            } else {
                onError.onError("");
            }
        }
        this.networkManager.getShopsFromServer(new GetAllShopsManagerCompletion() {
            @Override
            public void completion(@NonNull List<ShopEntity> shopEntities) {
                Log.d("SHOP", shopEntities.toString());
            }
        }, new ManagerErrorCompletion() {
            @Override
            public void onError(@NonNull String errorDescription) {

            }
        });
    }
}
