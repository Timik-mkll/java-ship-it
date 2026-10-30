import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    List<T> filterBox = new ArrayList<>();
    private int maxWeight;
    private int actualWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        actualWeight += parcel.weight;
        if (maxWeight < actualWeight) {
            System.out.println("Превышен максимальный вес, посылка не будет добавлена в коробку!");
            actualWeight -= parcel.weight;
            return;
        }
        filterBox.add(parcel);
        System.out.println("Посылка успешно добавлена!");
    }

    public void getAllParcels() {
        for (T parcel : filterBox) {
            System.out.println(parcel.description);
        }
    }
}
