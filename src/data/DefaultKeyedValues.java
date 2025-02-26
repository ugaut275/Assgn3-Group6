/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -----------------------
 * DefaultKeyedValues.java
 * -----------------------
 * (C) Copyright 2002-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: DefaultKeyedValues.java,v 1.9 2005/07/01 14:43:50 mungady Exp $
 *
 * Changes:
 * --------
 * 31-Oct-2002 : Version 1 (DG);
 * 11-Feb-2003 : Fixed bug in getValue(key) method for unrecognised key (DG);
 * 05-Mar-2003 : Added methods to sort stored data 'by key' or 'by value' (DG);
 * 13-Mar-2003 : Implemented Serializable (DG);
 * 08-Apr-2003 : Modified removeValue(Comparable) method to fix bug 717049 (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 27-Aug-2003 : Moved SortOrder from org.jfree.data --> org.jfree.util (DG);
 * 09-Feb-2004 : Modified getIndex() method - see bug report 893256 (DG);
 * 15-Sep-2004 : Updated clone() method and added PublicCloneable 
 *               interface (DG);
 * 25-Nov-2004 : Small update to the clone() implementation (DG);
 * 24-Feb-2005 : Added methods addValue(Comparable, double) and 
 *               setValue(Comparable, double) for convenience (DG);
 *
 */

package data;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.SortOrder;

/**
 * An ordered list of (key, value) items.  This class provides a default 
 * implementation of the {@link KeyedValues} interface.
 */
public class DefaultKeyedValues implements KeyedValues, 
                                           Cloneable, PublicCloneable, 
                                           Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 8468154364608194797L;
    
    /** Storage for the data. */
    private List data;

    /**
     * Creates a new collection (initially empty).
     */
    public DefaultKeyedValues() {
        this.data = new java.util.ArrayList();
    }

    /**
     * Returns the number of items (values) in the collection.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.data.size();
    }

    /**
     * Returns a value.
     *
     * @param item  the item of interest (zero-based index).
     *
     * @return The value.
     * 
     * @throws IndexOutOfBoundsException if <code>item</code> is out of bounds.
     */
    public Number getValue(int item) {
        Number result = null;
        KeyedValue kval = (KeyedValue) this.data.get(item);
        if (kval != null) {
            result = kval.getValue();
        }
        return result;
    }

    /**
     * Returns a key.
     *
     * @param index  the item index (zero-based).
     *
     * @return The row key.
     * 
     * @throws IndexOutOfBoundsException if <code>item</code> is out of bounds.
     */
    public Comparable getKey(int index) {
        Comparable result = null;
        KeyedValue item = (KeyedValue) this.data.get(index);
        if (item != null) {
            result = item.getKey();
        }
        return result;
    }

    /**
     * Returns the index for a given key.
     *
     * @param key  the key.
     *
     * @return The index, or <code>-1</code> if the key is unrecognised.
     */
    public int getIndex(Comparable key) {
        int i = 0;
        Iterator iterator = this.data.iterator();
        while (iterator.hasNext()) {
            KeyedValue kv = (KeyedValue) iterator.next();
            if (kv.getKey().equals(key)) {
                return i;
            }
            i++;
        }
        return -1;  // key not found
    }

    /**
     * Returns the keys for the values in the collection.
     *
     * @return The keys (never <code>null</code>).
     */
    public List getKeys() {
        List result = new java.util.ArrayList();
        Iterator iterator = this.data.iterator();
        while (iterator.hasNext()) {
            KeyedValue kv = (KeyedValue) iterator.next();
            result.add(kv.getKey());
        }
        return result;
    }

    /**
     * Returns the value for a given key.
     *
     * @param key  the key.
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @throws UnknownKeyException if the key is not recognised.
     */
    public Number getValue(Comparable key) {
        int index = getIndex(key);
        if (index < 0) {
            throw new UnknownKeyException("Key not found: " + key);
        }
        return getValue(index);
    }

    /**
     * Updates an existing value, or adds a new value to the collection.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value.
     */
    public void addValue(Comparable key, double value) {
        addValue(key, new Double(value)); 
    }
    
    /**
     * Adds a new value to the collection, or updates an existing value.
     * This method passes control directly to the 
     * {@link #setValue(Comparable, Number)} method.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public void addValue(Comparable key, Number value) {
        setValue(key, value);
    }

    /**
     * Updates an existing value, or adds a new value to the collection.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value.
     */
    public void setValue(Comparable key, double value) {
        setValue(key, new Double(value));   
    }
    
    /**
     * Updates an existing value, or adds a new value to the collection.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public void setValue(Comparable key, Number value) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        int keyIndex = getIndex(key);
        if (keyIndex >= 0) {
            DefaultKeyedValue kv = (DefaultKeyedValue) this.data.get(keyIndex);
            kv.setValue(value);
        }
        else {
            KeyedValue kv = new DefaultKeyedValue(key, value);
            this.data.add(kv);
        }
    }

    /**
     * Removes a value from the collection.
     *
     * @param index  the index of the item to remove.
     */
    public void removeValue(int index) {
        this.data.remove(index);
    }

    /**
     * Removes a value from the collection.  If there is no item with the 
     * specified key, this method does nothing.
     *
     * @param key  the item key.
     */
    public void removeValue(Comparable key) {
        int index = getIndex(key);
        if (index >= 0) {
            removeValue(index);
        }
    }

    /**
     * Sorts the items in the list by key.
     *
     * @param order  the sort order (<code>null</code> not permitted).
     */
    public void sortByKeys(SortOrder order) {
        Comparator comparator = new KeyedValueComparator(
            KeyedValueComparatorType.BY_KEY, order
        );
        Collections.sort(this.data, comparator);
    }

    /**
     * Sorts the items in the list by value.  If the list contains 
     * <code>null</code> values, they will sort to the end of the list, 
     * irrespective of the sort order.
     *
     * @param order  the sort order (<code>null</code> not permitted).
     */
    public void sortByValues(SortOrder order) {
        Comparator comparator = new KeyedValueComparator(
            KeyedValueComparatorType.BY_VALUE, order
        );
        Collections.sort(this.data, comparator);
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof KeyedValues)) {
            return false;
        }

        KeyedValues that = (KeyedValues) obj;
        int count = getItemCount();
        if (count != that.getItemCount()) {
            return false;
        }

        for (int i = 0; i < count; i++) {
            Comparable k1 = getKey(i);
            Comparable k2 = that.getKey(i);
            if (!k1.equals(k2)) {
                return false;
            }
            Number v1 = getValue(i);
            Number v2 = that.getValue(i);
            if (v1 == null) {
                if (v2 != null) {
                    return false;
                }
            }
            else {
                if (!v1.equals(v2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return (this.data != null ? this.data.hashCode() : 0);
    }

    /**
     * Returns a clone.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses might.
     */
    public Object clone() throws CloneNotSupportedException {
        DefaultKeyedValues clone = (DefaultKeyedValues) super.clone();
        clone.data = (List) ObjectUtilities.deepClone(this.data);
        return clone;    
    }
    
}