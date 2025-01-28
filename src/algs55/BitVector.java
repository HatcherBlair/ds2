package algs55;

import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An efficient implementation of an extensible array of bits. Based on
 * {@link java.util.BitSet}, but provides an interface more like
 * {@code ArrayList} or {@link java.util.Vector}.
 * 
 * @author Dexter
 */
public class BitVector implements Iterable<Boolean> {
   private BitSet bitSet;
   private int size;
   
   /**
    * Constructs a {@code BitSet} with no elements.
    */
   public BitVector() {
      bitSet = new BitSet();
      size = 0;
   }

   /**
    * Sets the bit at index {@code index} to the specified {@code value}.  The
    * {@code index} parameter must be at least 0 and less than {@code size()}.
    * 
    * @param index
    *           index of the bit to set
    * @param value
    *           boolean value to set it to
    * @throws ArrayIndexOutOfBoundsException
    *            if {@code index} is out of bounds
    */
   public void set(int index, boolean value) throws ArrayIndexOutOfBoundsException {
      if (index < 0 || index >= size())
         throw new ArrayIndexOutOfBoundsException();
      bitSet.set(index, value);
   }
   
   /**
    * Returns the bit at index {@code index}. The {@code index} parameter must
    * be at least 0 and less than {@code size()}.
    * 
    * @param index
    *           index of the bit to get
    * @return the value of the bit at index {@code index}
    * @throws ArrayIndexOutOfBoundsException
    *            if {@code index} is out of bounds
    */
   public boolean get(int index) throws ArrayIndexOutOfBoundsException {
      if (index < 0 || index >= size())
         throw new ArrayIndexOutOfBoundsException();
      return bitSet.get(index);
   }

   /**
    * Returns the size (number of bits) in this {@code BitVector}.
    * @return the size (number of bits) in this {@code BitVector}
    */
   public int size() {
      return size;
   }

   /**
    * Adds a new bit to the end of the {@code BitVector}.
    * @param value the value of the bit to add
    */
   public void add(boolean value) {
      bitSet.set(size++, value);
   }
   
   /**
    * Returns an iterator over this {@code BitVector}.
    * @return an iterator over this {@code BitVector}
    * @see java.util.Iterator
    */
   public Iterator<Boolean> iterator() {
      return new Iterator<Boolean>() {
         private int next = 0;

         public boolean hasNext() {
            return next < size();
         }

         public Boolean next() {
            if (!hasNext())
               throw new NoSuchElementException();
            return new Boolean(bitSet.get(next++));
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }

   /**
    * Returns an independent deep copy of this {@code BitVector}.
    * @return an independent deep copy of this {@code BitVector}
    */
   public BitVector clone() {
      BitVector bv = new BitVector();
      Iterator<Boolean> iter = iterator();
      while (iter.hasNext())
         bv.add(iter.next());
      return bv;
   }

}


