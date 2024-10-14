package y111studios.buildings;

import y111studios.position.GridArea;

public abstract class MapObject {

    protected GridArea area;

    public MapObject(GridArea area) {
        if (area == null) {
            throw new IllegalArgumentException("GridArea must not be null");
        }
        this.area = area;
    }

}
