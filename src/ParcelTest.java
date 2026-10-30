import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelTest {
    private static FragileParcel fragileParcel = new FragileParcel("Ваза", 10, "Дмитровская", 3);
    private static PerishableParcel expiredPerishableParcel = new PerishableParcel("Каравай", 5, "Ивановское", 1, 3);
    private static StandartParcel standartParcel = new StandartParcel("Чайник", 3, "Рублёвка", 17);
    private static ParcelBox<StandartParcel> standartParcelParcelBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileParcelParcelBox = new ParcelBox<>(50);
    private static ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>(150);
    StandartParcel bigStandartParcel = new StandartParcel("Камаз", 14800, "Подмосковье", 2);

    @Test
    public void shouldCost6ForStandartWhenWeight3() {
        assertEquals(6, standartParcel.calculateDeliveryCost(), "Неправильно рассчитана цена для стандартной посылки.");
    }

    @Test
    public void shouldCost40ForFragileWhenWeight10() {
        assertEquals(40, fragileParcel.calculateDeliveryCost(), "Неправильно рассчитана цена для хрупкой посылки.");
    }

    @Test
    public void shouldCost15ForPerishableWhenWeight5() {
        assertEquals(15, expiredPerishableParcel.calculateDeliveryCost(), "Неправильно рассчитана цена для " +
                "скоропортящейся посылки.");
    }

    @Test
    public void shouldBeExpiredWhen8() {
        assertTrue(expiredPerishableParcel.isExpired(8));
    }

    @Test
    public void shouldNotBeExpiredWhen2() {
        assertFalse(expiredPerishableParcel.isExpired(2));
    }

    @Test
    public void shouldBeAddToBoxWhenItsLessThan100() {
        standartParcelParcelBox.addParcel(standartParcel);
        assertTrue(1 == standartParcelParcelBox.filterBox.size());
    }

    @Test
    public void shouldNotBeAddToBoxWhenItsGreaterThan100() {
        standartParcelParcelBox.addParcel(bigStandartParcel);
        assertFalse(2 == standartParcelParcelBox.filterBox.size());
    }
}
