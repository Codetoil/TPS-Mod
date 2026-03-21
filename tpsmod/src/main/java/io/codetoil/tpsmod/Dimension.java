package io.codetoil.tpsmod;

public class Dimension {
    public final Integer ID;
    public final String NAMESPACE;
    public final String PATH;

    public Dimension(int id) {
        this.ID = id;
        this.NAMESPACE = null;
        this.PATH = null;
    }
    public Dimension(String namespace, String path) {
        this.ID = null;
        this.NAMESPACE = namespace;
        this.PATH = path;
    }
}
