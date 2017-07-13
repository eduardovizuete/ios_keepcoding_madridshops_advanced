package io.keepcoding.madridshops.domain.interactors;

public interface GetIfAllActivitiesAreCachedInteractor {
    void execute(Runnable onAllActivitiesAreCached, Runnable onAllActivitiesAreNotCached);
}
