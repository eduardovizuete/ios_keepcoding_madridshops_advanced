package io.keepcoding.madridshops.domain.interactors;

public interface GetIfAllShopsAreCachedInteractor {
    void executed(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached);
}
