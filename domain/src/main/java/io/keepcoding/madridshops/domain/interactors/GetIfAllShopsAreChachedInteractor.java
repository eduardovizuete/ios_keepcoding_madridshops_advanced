package io.keepcoding.madridshops.domain.interactors;

public interface GetIfAllShopsAreChachedInteractor {
    void executed(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached);
}
