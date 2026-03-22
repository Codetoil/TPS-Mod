package io.codetoil.tpsmod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Dimension {
    private static final List<Dimension> CACHE = new ArrayList<>();
    public final Integer ID;
    public final String NAMESPACE;
    public final String PATH;

    private Dimension(int id) {
        this.ID = id;
        this.NAMESPACE = null;
        this.PATH = null;
    }
    private Dimension(String namespace, String path) {
        this.ID = null;
        this.NAMESPACE = namespace;
        this.PATH = path;
    }

    public static Dimension of(int id) {
        Optional<Dimension> dimension = CACHE.stream()
                .filter(d -> Integer.valueOf(id).equals(d.ID))
                .findFirst();
        if (dimension.isPresent()) {
            return dimension.get();
        } else {
            Dimension dimension1 = new Dimension(id);
            CACHE.add(dimension1);
            return dimension1;
        }
    }

    public static Dimension of(String namespace, String path) {
        Optional<Dimension> dimension = CACHE.stream()
                .filter(d -> Objects.equals(d.NAMESPACE, namespace) && Objects.equals(d.PATH, path))
                .findFirst();
        if (dimension.isPresent()) {
            return dimension.get();
        } else {
            Dimension dimension1 = new Dimension(namespace, path);
            CACHE.add(dimension1);
            return dimension1;
        }
    }

    public static void removeFromCache(Dimension dimension) {
        CACHE.remove(dimension);
    }

    public static void cleanCache() {
        CACHE.clear();
    }


}
