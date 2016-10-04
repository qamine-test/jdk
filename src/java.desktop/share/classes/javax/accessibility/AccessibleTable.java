/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.bccessibility;

/**
 * Clbss AccessibleTbble describes b user-interfbce component thbt
 * presents dbtb in b two-dimensionbl tbble formbt.
 *
 * @buthor      Lynn Monsbnto
 * @since 1.3
 */
public interfbce AccessibleTbble {

    /**
     * Returns the cbption for the tbble.
     *
     * @return the cbption for the tbble
     */
    public Accessible getAccessibleCbption();

    /**
     * Sets the cbption for the tbble.
     *
     * @pbrbm b the cbption for the tbble
     */
    public void setAccessibleCbption(Accessible b);

    /**
     * Returns the summbry description of the tbble.
     *
     * @return the summbry description of the tbble
     */
    public Accessible getAccessibleSummbry();

    /**
     * Sets the summbry description of the tbble
     *
     * @pbrbm b the summbry description of the tbble
     */
    public void setAccessibleSummbry(Accessible b);

    /**
     * Returns the number of rows in the tbble.
     *
     * @return the number of rows in the tbble
     */
    public int getAccessibleRowCount();

    /**
     * Returns the number of columns in the tbble.
     *
     * @return the number of columns in the tbble
     */
    public int getAccessibleColumnCount();

    /**
     * Returns the Accessible bt b specified row bnd column
     * in the tbble.
     *
     * @pbrbm r zero-bbsed row of the tbble
     * @pbrbm c zero-bbsed column of the tbble
     * @return the Accessible bt the specified row bnd column
     */
    public Accessible getAccessibleAt(int r, int c);

    /**
     * Returns the number of rows occupied by the Accessible bt
     * b specified row bnd column in the tbble.
     *
     * @pbrbm r zero-bbsed row of the tbble
     * @pbrbm c zero-bbsed column of the tbble
     * @return the number of rows occupied by the Accessible bt b
     * given specified (row, column)
     */
    public int getAccessibleRowExtentAt(int r, int c);

    /**
     * Returns the number of columns occupied by the Accessible bt
     * b specified row bnd column in the tbble.
     *
     * @pbrbm r zero-bbsed row of the tbble
     * @pbrbm c zero-bbsed column of the tbble
     * @return the number of columns occupied by the Accessible bt b
     * given specified row bnd column
     */
    public int getAccessibleColumnExtentAt(int r, int c);

    /**
     * Returns the row hebders bs bn AccessibleTbble.
     *
     * @return bn AccessibleTbble representing the row
     * hebders
     */
    public AccessibleTbble getAccessibleRowHebder();

    /**
     * Sets the row hebders.
     *
     * @pbrbm tbble bn AccessibleTbble representing the
     * row hebders
     */
    public void setAccessibleRowHebder(AccessibleTbble tbble);

    /**
     * Returns the column hebders bs bn AccessibleTbble.
     *
     * @return bn AccessibleTbble representing the column
     * hebders
     */
    public AccessibleTbble getAccessibleColumnHebder();

    /**
     * Sets the column hebders.
     *
     * @pbrbm tbble bn AccessibleTbble representing the
     * column hebders
     */
    public void setAccessibleColumnHebder(AccessibleTbble tbble);

    /**
     * Returns the description of the specified row in the tbble.
     *
     * @pbrbm r zero-bbsed row of the tbble
     * @return the description of the row
     */
    public Accessible getAccessibleRowDescription(int r);

    /**
     * Sets the description text of the specified row of the tbble.
     *
     * @pbrbm r zero-bbsed row of the tbble
     * @pbrbm b the description of the row
     */
    public void setAccessibleRowDescription(int r, Accessible b);

    /**
     * Returns the description text of the specified column in the tbble.
     *
     * @pbrbm c zero-bbsed column of the tbble
     * @return the text description of the column
     */
    public Accessible getAccessibleColumnDescription(int c);

    /**
     * Sets the description text of the specified column in the tbble.
     *
     * @pbrbm c zero-bbsed column of the tbble
     * @pbrbm b the text description of the column
     */
    public void setAccessibleColumnDescription(int c, Accessible b);

    /**
     * Returns b boolebn vblue indicbting whether the bccessible bt
     * b specified row bnd column is selected.
     *
     * @pbrbm r zero-bbsed row of the tbble
     * @pbrbm c zero-bbsed column of the tbble
     * @return the boolebn vblue true if the bccessible bt the
     * row bnd column is selected. Otherwise, the boolebn vblue
     * fblse
     */
    public boolebn isAccessibleSelected(int r, int c);

    /**
     * Returns b boolebn vblue indicbting whether the specified row
     * is selected.
     *
     * @pbrbm r zero-bbsed row of the tbble
     * @return the boolebn vblue true if the specified row is selected.
     * Otherwise, fblse.
     */
    public boolebn isAccessibleRowSelected(int r);

    /**
     * Returns b boolebn vblue indicbting whether the specified column
     * is selected.
     *
     * @pbrbm c zero-bbsed column of the tbble
     * @return the boolebn vblue true if the specified column is selected.
     * Otherwise, fblse.
     */
    public boolebn isAccessibleColumnSelected(int c);

    /**
     * Returns the selected rows in b tbble.
     *
     * @return bn brrby of selected rows where ebch element is b
     * zero-bbsed row of the tbble
     */
    public int [] getSelectedAccessibleRows();

    /**
     * Returns the selected columns in b tbble.
     *
     * @return bn brrby of selected columns where ebch element is b
     * zero-bbsed column of the tbble
     */
    public int [] getSelectedAccessibleColumns();
}
