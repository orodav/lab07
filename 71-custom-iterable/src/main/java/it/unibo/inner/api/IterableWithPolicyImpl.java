package it.unibo.inner.api;

//import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private T[] constructorElement;
    private Predicate<T> predicate;

    @Override
    public Iterator<T> iterator(){
        return new ArrayIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) { 
        this.predicate = filter;
        }
    
    public IterableWithPolicyImpl(T[] constructorElement){

        this.constructorElement = constructorElement;
        
        predIterableWithPolicyImpl(constructorElement, predicate);
            Predicate<T> myPred = new Predicate<T>(){
            @Override
            public boolean test(T t){
                return true;
            }
        };
       this.predicate = myPred;
    }

    public void predIterableWithPolicyImpl(T[] constructorElement, Predicate<T> predicate){
        this.constructorElement = constructorElement;
        this.predicate = predicate;
    }

    class ArrayIterator implements Iterator<T> {
        
        private int index = 0;

        @Override
        public boolean hasNext() {
            while(index < constructorElement.length && !predicate.test(constructorElement[index])){
                index++;
            }
            return index < constructorElement.length;
        }
        @Override
        public T next() {
            if(!hasNext()){
               throw new NoSuchElementException();
            }
            return (T)constructorElement[index++];
        }
    }
}

    
    
        


