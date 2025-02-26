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
 * ----------------------
 * DefaultKeyedValue.java
 * ----------------------
 * (C) Copyright 2002-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: DefaultKeyedValue.java,v 1.6 2005/05/19 10:34:07 mungady Exp $
 *
 * Changes:
 * --------
 * 31-Oct-2002 : Version 1 (DG);
 * 13-Mar-2003 : Added equals() method, and implemented Serializable (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.base (DG);
 * 15-Sep-2004 : Added PublicCloneable interface (DG);
 *
 */

package data;

import java.io.Serializable;

import org.jfree.util.PublicCloneable;

/**
 * A (key, value) pair.  This class provides a default implementation 
 * of the {@link KeyedValue} interface.
 */
public class DefaultKeyedValue implements KeyedValue, 
                                          Cloneable, PublicCloneable, 
                                          Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -7388924517460437712L;
    
    /** The key. */
    private Comparable key;

    /** The value. */
    private Number value;

    /**
     * Creates a new (key, value) item.
     *
     * @param key  the key (should be immutable).
     * @param value  the value (<code>null</code> permitted).
     */
    public DefaultKeyedValue(Comparable key, Number value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key.
     *
     * @return The key.
     */
    public Comparable getKey() {
        return this.key;
    }

    /**
     * Returns the value.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     *
     * @param value  the value (<code>null</code> permitted).
     */
    public synchronized void setValue(Number value) {
        this.value = value;
    }

    /**
     * Tests this key-value pair for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultKeyedValue)) {
            return false;
        }
        // TODO: modify this so that we check for equality with any KeyedValue
        // rather than specifically a DefaultKeyedValue
        DefaultKeyedValue that = (DefaultKeyedValue) obj;
        
        // TODO: the following checks for null should be handled in a utility 
        // method
        if (this.key != null ? !this.key.equals(that.key) : that.key != null) {
            return false;
        }
        if (this.value != null 
                ? !this.value.equals(that.value) : that.key != null) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = (this.key != null ? this.key.hashCode() : 0);
        result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
        return result;
    }

    /**
     * Returns a clone.  It is assumed that both the key and value are 
     * immutable objects, so only the references are cloned, not the objects 
     * themselves.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException Not thrown by this class, but 
     *         subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
        DefaultKeyedValue clone = (DefaultKeyedValue) super.clone();
        return clone;
    }

}