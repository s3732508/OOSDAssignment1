package com.sharknados.models.storage;

public class StorageSingleton {
    private static final StorageAdapter serialisation = new SerialisationStorageAdapter();

    public static StorageAdapter getStorageAdapter() {
        return serialisation;
    }
}
