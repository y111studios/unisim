package y111studios.buildings;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import y111studios.buildings.premade_variants.AccomodationVariant;
import y111studios.buildings.premade_variants.VariantProperties;
import y111studios.buildings.premade_variants.CateringVariant;
import y111studios.buildings.premade_variants.RecreationVariant;
import y111studios.buildings.premade_variants.TeachingVariant;
import y111studios.position.GridArea;
import y111studios.position.GridPosition;
import y111studios.utils.UnreachableException;

public final class BuildingFactory {

    private BuildingFactory() {
        // Prevent instatiation of Factory class
    }

    /**
     * A map of the constructors for each building class. The key is the class of the building and
     * the value is the constructor of the building. The constructor used takes a {@Link GridArea}
     * as an argument but can be adjusted to use a different constructor by modifying the
     * implementation of the {@link #tryRegisterConstructor(Class)} method.
     */
    private static final Map<Class<? extends Building>, Constructor<? extends Building>> CONSTRUCTORS;

    /**
     * At startup, try to register the constructors of the building classes. This is done to ensure
     * that the constructors are correctly defined at the start rather than during runtime.
     */
    static {
        CONSTRUCTORS = new HashMap<>(4);
        try {
            tryRegisterConstructor(AccomodationVariant.values()[0].getBuildingClass());
            tryRegisterConstructor(CateringVariant.values()[0].getBuildingClass());
            tryRegisterConstructor(RecreationVariant.values()[0].getBuildingClass());
            tryRegisterConstructor(TeachingVariant.values()[0].getBuildingClass());
        } catch (UnreachableException e) {
            // This should not happen, as the constructors should be defined in the classes
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Try to register the constructor of the building class. This is done to ensure that each
     * building class has a constructor that takes a {@Link GridArea} as an argument. If the
     * constructor is not defined, an exception is thrown.
     * 
     * @param buildingClass the subclass of {@link Building} to register the constructor for
     * 
     * @throws UnreachableException if the constructor is not defined in the class
     */
    private static void tryRegisterConstructor(Class<? extends Building> buildingClass) {
        Constructor<? extends Building> constructor;
        try {
            // Get the constructor of the building class
            constructor = buildingClass.getConstructor(GridArea.class);
        } catch (NoSuchMethodException e) {
            // This should not happen, as the constructor should be defined in the class
            throw new UnreachableException("Constructor undefined", e);
        } catch (SecurityException e) {
            // Issue that can arise from constructor access permissions. i.e. private / protected
            // constructors
            throw new UnreachableException("Constructor cannot be accessed", e);
        }
        // Register the constructor to the map
        CONSTRUCTORS.put(buildingClass, constructor);
    }

    /**
     * Create a new building instance of the specified variant at the specified position.
     * 
     * <p>
     * The variant must be implement the {@link VariantProperties} interface and the position must
     * not be null.
     * </p>
     * 
     * @param <V> the type of the variant
     * @param variant the variant of the building
     * @param position the position of the building
     * @return a new instance of the building
     * 
     * @throws IllegalArgumentException if the variant or position is null
     */
    public static <V extends Enum<V> & VariantProperties> Building createBuilding(V variant,
            GridPosition position) {
        if (variant == null) {
            throw new IllegalArgumentException("BuildingProperties must not be null");
        }
        if (position == null) {
            throw new IllegalArgumentException("GridPosition must not be null");
        }
        // Get the appropriate constructor for the building class
        Constructor<? extends Building> constructor = CONSTRUCTORS.get(variant.getBuildingClass());
        // Generate the area for the constructor argument
        GridArea area = new GridArea(position, variant.getWidth(), variant.getHeight());
        try {
            // Return the new instance of the Building
            return constructor.newInstance(area);
        } catch (Exception e) {
            // This should not happen, as the constructor should be defined in the class
            e.printStackTrace();
            throw new UnreachableException("Constructors defined at startup should be correct");
        }
    }

}
