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
 * --------------
 * RangeInfo.java
 * --------------
 * (C) Copyright 2000-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: RangeInfo.java,v 1.5 2005/03/04 11:45:15 mungady Exp $
 *
 * Changes (from 18-Sep-2001)
 * --------------------------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 15-Nov-2001 : Moved to package com.jrefinery.data.* (DG);
 *               Updated Javadoc comments (DG);
 * 22-Apr-2002 : Added getValueRange() method (DG);
 * 17-Nov-2004 : Replaced getMinimumRangeValue() --> getRangeLowerBound(),
 *               getMaximumRangeValue() --> getRangeUpperBound(),
 *               getValueRange() --> getRangeBounds().
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 *
 */

package data;

/**
 * An interface (optional) that can be implemented by a dataset to assist in 
 * determining the minimum and maximum values.  See also {@link DomainInfo}.
 */
public interface RangeInfo {

    /**
     * Returns the minimum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The minimum value.
     */
    public double getRangeLowerBound(boolean includeInterval);

    /**
     * Returns the maximum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The maximum value.
     */
    public double getRangeUpperBound(boolean includeInterval);

    /**
     * Returns the range of the values in this dataset's range.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The range.
     */
    public Range getRangeBounds(boolean includeInterval);

}
