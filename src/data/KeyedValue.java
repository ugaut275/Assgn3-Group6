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
 * ---------------
 * KeyedValue.java
 * ---------------
 * (C) Copyright 2002-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: KeyedValue.java,v 1.3 2005/03/29 12:57:16 mungady Exp $
 *
 * Changes:
 * --------
 * 31-Oct-2002 : Version 1 (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.base (DG);
 *
 */

package data;

/**
 * A (key, value) pair.
 *
 * @see DefaultKeyedValue
 */
public interface KeyedValue extends Value {

    /**
     * Returns the key associated with the value.  The key returned by this 
     * method should be immutable.
     *
     * @return The key.
     */
    public Comparable getKey();

}