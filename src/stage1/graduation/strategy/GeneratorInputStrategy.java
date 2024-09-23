package stage1.graduation.strategy;

import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;
import java.util.Random;

public class GeneratorInputStrategy<T> implements InputStrategy<T> {
    private final Random random = new Random();

    @Override
    public T[] input(Class<T> clazz, int arrayLength) {
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);
        for (int i = 0; i < arrayLength; i++) {
            if (clazz == Bus.class) {
                array[i] = clazz.cast(new Bus.BusBuilder("Model" + random.nextInt(100), random.nextInt(1000)).setMileage(random.nextInt(200000)).build());
            } else if (clazz == User.class) {
                String name = "User" + random.nextInt(100);
                array[i] = clazz.cast(new User.UserBuilder(name).setPassword("Pass" + random.nextInt(1000)).setEmail(name.toLowerCase() + "@example.com").build());
            } else if (clazz == Student.class) {
                array[i] = clazz.cast(new Student.StudentBuilder(random.nextInt(10000)).setGroupNumber(random.nextInt(10)).setAverageMark(random.nextFloat() * 5).build());
            }
        }
        return array;
    }
}
