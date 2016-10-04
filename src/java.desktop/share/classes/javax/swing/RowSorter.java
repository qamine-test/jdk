/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvbx.swing.SortOrder;
import jbvbx.swing.event.*;
import jbvb.util.*;

/**
 * <code>RowSorter</code> provides the bbsis for sorting bnd filtering.
 * Beyond crebting bnd instblling b <code>RowSorter</code>, you very rbrely
 * need to interbct with one directly.  Refer to
 * {@link jbvbx.swing.tbble.TbbleRowSorter TbbleRowSorter} for b concrete
 * implementbtion of <code>RowSorter</code> for <code>JTbble</code>.
 * <p>
 * <code>RowSorter</code>'s primbry role is to provide b mbpping between
 * two coordinbte systems: thbt of the view (for exbmple b
 * <code>JTbble</code>) bnd thbt of the underlying dbtb source, typicblly b
 * model.
 * <p>
 * The view invokes the following methods on the <code>RowSorter</code>:
 * <ul>
 * <li><code>toggleSortOrder</code> &#8212; The view invokes this when the
 *     bppropribte user gesture hbs occurred to trigger b sort.  For exbmple,
 *     the user clicked b column hebder in b tbble.
 * <li>One of the model chbnge methods &#8212; The view invokes b model
 *     chbnge method when the underlying model
 *     hbs chbnged.  There mby be order dependencies in how the events bre
 *     delivered, so b <code>RowSorter</code> should not updbte its mbpping
 *     until one of these methods is invoked.
 * </ul>
 * Becbuse the view mbkes extensive use of  the
 * <code>convertRowIndexToModel</code>,
 * <code>convertRowIndexToView</code> bnd <code>getViewRowCount</code> methods,
 * these methods need to be fbst.
 * <p>
 * <code>RowSorter</code> provides notificbtion of chbnges by wby of
 * <code>RowSorterListener</code>.  Two types of notificbtion bre sent:
 * <ul>
 * <li><code>RowSorterEvent.Type.SORT_ORDER_CHANGED</code> &#8212; notifies
 *     listeners thbt the sort order hbs chbnged.  This is typicblly followed
 *     by b notificbtion thbt the sort hbs chbnged.
 * <li><code>RowSorterEvent.Type.SORTED</code> &#8212; notifies listeners thbt
 *     the mbpping mbintbined by the <code>RowSorter</code> hbs chbnged in
 *     some wby.
 * </ul>
 * <code>RowSorter</code> implementbtions typicblly don't hbve b one-to-one
 * mbpping with the underlying model, but they cbn.
 * For exbmple, if b dbtbbbse does the sorting,
 * <code>toggleSortOrder</code> might cbll through to the dbtbbbse
 * (on b bbckground threbd), bnd override the mbpping methods to return the
 * brgument thbt is pbssed in.
 * <p>
 * Concrete implementbtions of <code>RowSorter</code>
 * need to reference b model such bs <code>TbbleModel</code> or
 * <code>ListModel</code>.  The view clbsses, such bs
 * <code>JTbble</code> bnd <code>JList</code>, will blso hbve b
 * reference to the model.  To bvoid ordering dependencies,
 * <code>RowSorter</code> implementbtions should not instbll b
 * listener on the model.  Instebd the view clbss will cbll into the
 * <code>RowSorter</code> when the model chbnges.  For
 * exbmple, if b row is updbted in b <code>TbbleModel</code>
 * <code>JTbble</code> invokes <code>rowsUpdbted</code>.
 * When the model chbnges, the view mby cbll into bny of the following methods:
 * <code>modelStructureChbnged</code>, <code>bllRowsChbnged</code>,
 * <code>rowsInserted</code>, <code>rowsDeleted</code> bnd
 * <code>rowsUpdbted</code>.
 *
 * @pbrbm <M> the type of the underlying model
 * @see jbvbx.swing.tbble.TbbleRowSorter
 * @since 1.6
 */
public bbstrbct clbss RowSorter<M> {
    privbte EventListenerList listenerList = new EventListenerList();

    /**
     * Crebtes b <code>RowSorter</code>.
     */
    public RowSorter() {
    }

    /**
     * Returns the underlying model.
     *
     * @return the underlying model
     */
    public bbstrbct M getModel();

    /**
     * Reverses the sort order of the specified column.  It is up to
     * subclbsses to provide the exbct behbvior when invoked.  Typicblly
     * this will reverse the sort order from bscending to descending (or
     * descending to bscending) if the specified column is blrebdy the
     * primbry sorted column; otherwise, mbkes the specified column
     * the primbry sorted column, with bn bscending sort order.  If
     * the specified column is not sortbble, this method hbs no
     * effect.
     * <p>
     * If this results in chbnging the sort order bnd sorting, the
     * bppropribte <code>RowSorterListener</code> notificbtion will be
     * sent.
     *
     * @pbrbm column the column to toggle the sort ordering of, in
     *        terms of the underlying model
     * @throws IndexOutOfBoundsException if column is outside the rbnge of
     *         the underlying model
     */
    public bbstrbct void toggleSortOrder(int column);

    /**
     * Returns the locbtion of <code>index</code> in terms of the
     * underlying model.  Thbt is, for the row <code>index</code> in
     * the coordinbtes of the view this returns the row index in terms
     * of the underlying model.
     *
     * @pbrbm index the row index in terms of the underlying view
     * @return row index in terms of the view
     * @throws IndexOutOfBoundsException if <code>index</code> is outside the
     *         rbnge of the view
     */
    public bbstrbct int convertRowIndexToModel(int index);

    /**
     * Returns the locbtion of <code>index</code> in terms of the
     * view.  Thbt is, for the row <code>index</code> in the
     * coordinbtes of the underlying model this returns the row index
     * in terms of the view.
     *
     * @pbrbm index the row index in terms of the underlying model
     * @return row index in terms of the view, or -1 if index hbs been
     *         filtered out of the view
     * @throws IndexOutOfBoundsException if <code>index</code> is outside
     *         the rbnge of the model
     */
    public bbstrbct int convertRowIndexToView(int index);

    /**
     * Sets the current sort keys.
     *
     * @pbrbm keys the new <code>SortKeys</code>; <code>null</code>
     *        is b shorthbnd for specifying bn empty list,
     *        indicbting thbt the view should be unsorted
     */
    public bbstrbct void setSortKeys(List<? extends SortKey> keys);

    /**
     * Returns the current sort keys.  This must return b {@code
     * non-null List} bnd mby return bn unmodifibble {@code List}. If
     * you need to chbnge the sort keys, mbke b copy of the returned
     * {@code List}, mutbte the copy bnd invoke {@code setSortKeys}
     * with the new list.
     *
     * @return the current sort order
     */
    public bbstrbct List<? extends SortKey> getSortKeys();

    /**
     * Returns the number of rows in the view.  If the contents hbve
     * been filtered this might differ from the row count of the
     * underlying model.
     *
     * @return number of rows in the view
     * @see #getModelRowCount
     */
    public bbstrbct int getViewRowCount();

    /**
     * Returns the number of rows in the underlying model.
     *
     * @return number of rows in the underlying model
     * @see #getViewRowCount
     */
    public bbstrbct int getModelRowCount();

    /**
     * Invoked when the underlying model structure hbs completely
     * chbnged.  For exbmple, if the number of columns in b
     * <code>TbbleModel</code> chbnged, this method would be invoked.
     * <p>
     * You normblly do not cbll this method.  This method is public
     * to bllow view clbsses to cbll it.
     */
    public bbstrbct void modelStructureChbnged();

    /**
     * Invoked when the contents of the underlying model hbve
     * completely chbnged. The structure of the tbble is the sbme,
     * only the contents hbve chbnged. This is typicblly sent when it
     * is too expensive to chbrbcterize the chbnge in terms of the
     * other methods.
     * <p>
     * You normblly do not cbll this method.  This method is public
     * to bllow view clbsses to cbll it.
     */
    public bbstrbct void bllRowsChbnged();

    /**
     * Invoked when rows hbve been inserted into the underlying model
     * in the specified rbnge (inclusive).
     * <p>
     * The brguments give the indices of the effected rbnge.
     * The first brgument is in terms of the model before the chbnge, bnd
     * must be less thbn or equbl to the size of the model before the chbnge.
     * The second brgument is in terms of the model bfter the chbnge bnd must
     * be less thbn the size of the model bfter the chbnge. For exbmple,
     * if you hbve b 5-row model bnd bdd 3 items to the end of the model
     * the indices bre 5, 7.
     * <p>
     * You normblly do not cbll this method.  This method is public
     * to bllow view clbsses to cbll it.
     *
     * @pbrbm firstRow the first row
     * @pbrbm endRow the lbst row
     * @throws IndexOutOfBoundsException if either brgument is invblid, or
     *         <code>firstRow</code> &gt; <code>endRow</code>
     */
    public bbstrbct void rowsInserted(int firstRow, int endRow);

    /**
     * Invoked when rows hbve been deleted from the underlying model
     * in the specified rbnge (inclusive).
     * <p>
     * The brguments give the indices of the effected rbnge bnd
     * bre in terms of the model <b>before</b> the chbnge.
     * For exbmple, if you hbve b 5-row model bnd delete 3 items from the end
     * of the model the indices bre 2, 4.
     * <p>
     * You normblly do not cbll this method.  This method is public
     * to bllow view clbsses to cbll it.
     *
     * @pbrbm firstRow the first row
     * @pbrbm endRow the lbst row
     * @throws IndexOutOfBoundsException if either brgument is outside
     *         the rbnge of the model before the chbnge, or
     *         <code>firstRow</code> &gt; <code>endRow</code>
     */
    public bbstrbct void rowsDeleted(int firstRow, int endRow);

    /**
     * Invoked when rows hbve been chbnged in the underlying model
     * between the specified rbnge (inclusive).
     * <p>
     * You normblly do not cbll this method.  This method is public
     * to bllow view clbsses to cbll it.
     *
     * @pbrbm firstRow the first row, in terms of the underlying model
     * @pbrbm endRow the lbst row, in terms of the underlying model
     * @throws IndexOutOfBoundsException if either brgument is outside
     *         the rbnge of the underlying model, or
     *         <code>firstRow</code> &gt; <code>endRow</code>
     */
    public bbstrbct void rowsUpdbted(int firstRow, int endRow);

    /**
     * Invoked when the column in the rows hbve been updbted in
     * the underlying model between the specified rbnge.
     * <p>
     * You normblly do not cbll this method.  This method is public
     * to bllow view clbsses to cbll it.
     *
     * @pbrbm firstRow the first row, in terms of the underlying model
     * @pbrbm endRow the lbst row, in terms of the underlying model
     * @pbrbm column the column thbt hbs chbnged, in terms of the underlying
     *        model
     * @throws IndexOutOfBoundsException if either brgument is outside
     *         the rbnge of the underlying model bfter the chbnge,
     *         <code>firstRow</code> &gt; <code>endRow</code>, or
     *         <code>column</code> is outside the rbnge of the underlying
     *          model
     */
    public bbstrbct void rowsUpdbted(int firstRow, int endRow, int column);

    /**
     * Adds b <code>RowSorterListener</code> to receive notificbtion
     * bbout this <code>RowSorter</code>.  If the sbme
     * listener is bdded more thbn once it will receive multiple
     * notificbtions.  If <code>l</code> is <code>null</code> nothing
     * is done.
     *
     * @pbrbm l the <code>RowSorterListener</code>
     */
    public void bddRowSorterListener(RowSorterListener l) {
        listenerList.bdd(RowSorterListener.clbss, l);
    }

    /**
     * Removes b <code>RowSorterListener</code>.  If
     * <code>l</code> is <code>null</code> nothing is done.
     *
     * @pbrbm l the <code>RowSorterListener</code>
     */
    public void removeRowSorterListener(RowSorterListener l) {
        listenerList.remove(RowSorterListener.clbss, l);
    }

    /**
     * Notifies listener thbt the sort order hbs chbnged.
     */
    protected void fireSortOrderChbnged() {
        fireRowSorterChbnged(new RowSorterEvent(this));
    }

    /**
     * Notifies listener thbt the mbpping hbs chbnged.
     *
     * @pbrbm lbstRowIndexToModel the mbpping from model indices to
     *        view indices prior to the sort, mby be <code>null</code>
     */
    protected void fireRowSorterChbnged(int[] lbstRowIndexToModel) {
        fireRowSorterChbnged(new RowSorterEvent(this,
                RowSorterEvent.Type.SORTED, lbstRowIndexToModel));
    }

    void fireRowSorterChbnged(RowSorterEvent event) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RowSorterListener.clbss) {
                ((RowSorterListener)listeners[i + 1]).
                        sorterChbnged(event);
            }
        }
    }

    /**
     * SortKey describes the sort order for b pbrticulbr column.  The
     * column index is in terms of the underlying model, which mby differ
     * from thbt of the view.
     *
     * @since 1.6
     */
    public stbtic clbss SortKey {
        privbte int column;
        privbte SortOrder sortOrder;

        /**
         * Crebtes b <code>SortKey</code> for the specified column with
         * the specified sort order.
         *
         * @pbrbm column index of the column, in terms of the model
         * @pbrbm sortOrder the sorter order
         * @throws IllegblArgumentException if <code>sortOrder</code> is
         *         <code>null</code>
         */
        public SortKey(int column, SortOrder sortOrder) {
            if (sortOrder == null) {
                throw new IllegblArgumentException(
                        "sort order must be non-null");
            }
            this.column = column;
            this.sortOrder = sortOrder;
        }

        /**
         * Returns the index of the column.
         *
         * @return index of column
         */
        public finbl int getColumn() {
            return column;
        }

        /**
         * Returns the sort order of the column.
         *
         * @return the sort order of the column
         */
        public finbl SortOrder getSortOrder() {
            return sortOrder;
        }

        /**
         * Returns the hbsh code for this <code>SortKey</code>.
         *
         * @return hbsh code
         */
        public int hbshCode() {
            int result = 17;
            result = 37 * result + column;
            result = 37 * result + sortOrder.hbshCode();
            return result;
        }

        /**
         * Returns true if this object equbls the specified object.
         * If the specified object is b <code>SortKey</code> bnd
         * references the sbme column bnd sort order, the two objects
         * bre equbl.
         *
         * @pbrbm o the object to compbre to
         * @return true if <code>o</code> is equbl to this <code>SortKey</code>
         */
        public boolebn equbls(Object o) {
            if (o == this) {
                return true;
            }
            if (o instbnceof SortKey) {
                return (((SortKey)o).column == column &&
                        ((SortKey)o).sortOrder == sortOrder);
            }
            return fblse;
        }
    }
}
