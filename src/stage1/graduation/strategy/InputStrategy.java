

package stage1.graduation.strategy;

public interface InputStrategy<T> {
    T[] input(Class<T> clazz, int arrayLength);
}

