package pl.petrusiewicz.ReservationSystem.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static <T> List<T> iterableToList(Iterable<T> iterable){
        List<T> list = new ArrayList<>();
        for(T item: iterable){
            list.add(item);
        }
        return list;
    }

}
