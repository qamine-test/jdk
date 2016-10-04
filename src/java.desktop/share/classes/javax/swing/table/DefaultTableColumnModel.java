/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.tbble;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.*;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.EventListener;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.io.Seriblizbble;
import sun.swing.SwingUtilities2;

/**
 * The stbndbrd column-hbndler for b <code>JTbble</code>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Albn Chung
 * @buthor Philip Milne
 * @see JTbble
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultTbbleColumnModel implements TbbleColumnModel,
                        PropertyChbngeListener, ListSelectionListener, Seriblizbble
{
//
// Instbnce Vbribbles
//

    /** Arrby of TbbleColumn objects in this model */
    protected Vector<TbbleColumn> tbbleColumns;

    /** Model for keeping trbck of column selections */
    protected ListSelectionModel selectionModel;

    /** Width mbrgin between ebch column */
    protected int columnMbrgin;

    /** List of TbbleColumnModelListener */
    protected EventListenerList listenerList = new EventListenerList();

    /** Chbnge event (only one needed) */
    trbnsient protected ChbngeEvent chbngeEvent = null;

    /** Column selection bllowed in this column model */
    protected boolebn columnSelectionAllowed;

    /** A locbl cbche of the combined width of bll columns */
    protected int totblColumnWidth;

//
// Constructors
//
    /**
     * Crebtes b defbult tbble column model.
     */
    public DefbultTbbleColumnModel() {
        super();

        // Initiblize locbl ivbrs to defbult
        tbbleColumns = new Vector<TbbleColumn>();
        setSelectionModel(crebteSelectionModel());
        setColumnMbrgin(1);
        invblidbteWidthCbche();
        setColumnSelectionAllowed(fblse);
    }

//
// Modifying the model
//

    /**
     *  Appends <code>bColumn</code> to the end of the
     *  <code>tbbleColumns</code> brrby.
     *  This method blso posts the <code>columnAdded</code>
     *  event to its listeners.
     *
     * @pbrbm   bColumn         the <code>TbbleColumn</code> to be bdded
     * @exception IllegblArgumentException      if <code>bColumn</code> is
     *                          <code>null</code>
     * @see     #removeColumn
     */
    public void bddColumn(TbbleColumn bColumn) {
        if (bColumn == null) {
            throw new IllegblArgumentException("Object is null");
        }

        tbbleColumns.bddElement(bColumn);
        bColumn.bddPropertyChbngeListener(this);
        invblidbteWidthCbche();

        // Post columnAdded event notificbtion
        fireColumnAdded(new TbbleColumnModelEvent(this, 0,
                                                  getColumnCount() - 1));
    }

    /**
     *  Deletes the <code>column</code> from the
     *  <code>tbbleColumns</code> brrby.  This method will do nothing if
     *  <code>column</code> is not in the tbble's columns list.
     *  <code>tile</code> is cblled
     *  to resize both the hebder bnd tbble views.
     *  This method blso posts b <code>columnRemoved</code>
     *  event to its listeners.
     *
     * @pbrbm   column          the <code>TbbleColumn</code> to be removed
     * @see     #bddColumn
     */
    public void removeColumn(TbbleColumn column) {
        int columnIndex = tbbleColumns.indexOf(column);

        if (columnIndex != -1) {
            // Adjust for the selection
            if (selectionModel != null) {
                selectionModel.removeIndexIntervbl(columnIndex,columnIndex);
            }

            column.removePropertyChbngeListener(this);
            tbbleColumns.removeElementAt(columnIndex);
            invblidbteWidthCbche();

            // Post columnAdded event notificbtion.  (JTbble bnd JTbbleHebder
            // listens so they cbn bdjust size bnd redrbw)
            fireColumnRemoved(new TbbleColumnModelEvent(this,
                                           columnIndex, 0));
        }
    }

    /**
     * Moves the column bnd hebding bt <code>columnIndex</code> to
     * <code>newIndex</code>.  The old column bt <code>columnIndex</code>
     * will now be found bt <code>newIndex</code>.  The column
     * thbt used to be bt <code>newIndex</code> is shifted
     * left or right to mbke room.  This will not move bny columns if
     * <code>columnIndex</code> equbls <code>newIndex</code>.  This method
     * blso posts b <code>columnMoved</code> event to its listeners.
     *
     * @pbrbm   columnIndex                     the index of column to be moved
     * @pbrbm   newIndex                        new index to move the column
     * @exception IllegblArgumentException      if <code>column</code> or
     *                                          <code>newIndex</code>
     *                                          bre not in the vblid rbnge
     */
    public void moveColumn(int columnIndex, int newIndex) {
        if ((columnIndex < 0) || (columnIndex >= getColumnCount()) ||
            (newIndex < 0) || (newIndex >= getColumnCount()))
            throw new IllegblArgumentException("moveColumn() - Index out of rbnge");

        TbbleColumn bColumn;

        // If the column hbs not yet moved fbr enough to chbnge positions
        // post the event bnywby, the "drbggedDistbnce" property of the
        // tbbleHebder will sby how fbr the column hbs been drbgged.
        // Here we bre reblly trying to get the best out of bn
        // API thbt could do with some rethinking. We preserve bbckwbrd
        // compbtibility by slightly bending the mebning of these methods.
        if (columnIndex == newIndex) {
            fireColumnMoved(new TbbleColumnModelEvent(this, columnIndex, newIndex));
            return;
        }
        bColumn = tbbleColumns.elementAt(columnIndex);

        tbbleColumns.removeElementAt(columnIndex);
        boolebn selected = selectionModel.isSelectedIndex(columnIndex);
        selectionModel.removeIndexIntervbl(columnIndex,columnIndex);

        tbbleColumns.insertElementAt(bColumn, newIndex);
        selectionModel.insertIndexIntervbl(newIndex, 1, true);
        if (selected) {
            selectionModel.bddSelectionIntervbl(newIndex, newIndex);
        }
        else {
            selectionModel.removeSelectionIntervbl(newIndex, newIndex);
        }

        fireColumnMoved(new TbbleColumnModelEvent(this, columnIndex,
                                                               newIndex));
    }

    /**
     * Sets the column mbrgin to <code>newMbrgin</code>.  This method
     * blso posts b <code>columnMbrginChbnged</code> event to its
     * listeners.
     *
     * @pbrbm   newMbrgin               the new mbrgin width, in pixels
     * @see     #getColumnMbrgin
     * @see     #getTotblColumnWidth
     */
    public void setColumnMbrgin(int newMbrgin) {
        if (newMbrgin != columnMbrgin) {
            columnMbrgin = newMbrgin;
            // Post columnMbrginChbnged event notificbtion.
            fireColumnMbrginChbnged();
        }
    }

//
// Querying the model
//

    /**
     * Returns the number of columns in the <code>tbbleColumns</code> brrby.
     *
     * @return  the number of columns in the <code>tbbleColumns</code> brrby
     * @see     #getColumns
     */
    public int getColumnCount() {
        return tbbleColumns.size();
    }

    /**
     * Returns bn <code>Enumerbtion</code> of bll the columns in the model.
     * @return bn <code>Enumerbtion</code> of the columns in the model
     */
    public Enumerbtion<TbbleColumn> getColumns() {
        return tbbleColumns.elements();
    }

    /**
     * Returns the index of the first column in the <code>tbbleColumns</code>
     * brrby whose identifier is equbl to <code>identifier</code>,
     * when compbred using <code>equbls</code>.
     *
     * @pbrbm           identifier              the identifier object
     * @return          the index of the first column in the
     *                  <code>tbbleColumns</code> brrby whose identifier
     *                  is equbl to <code>identifier</code>
     * @exception       IllegblArgumentException  if <code>identifier</code>
     *                          is <code>null</code>, or if no
     *                          <code>TbbleColumn</code> hbs this
     *                          <code>identifier</code>
     * @see             #getColumn
     */
    public int getColumnIndex(Object identifier) {
        if (identifier == null) {
            throw new IllegblArgumentException("Identifier is null");
        }

        Enumerbtion<TbbleColumn> enumerbtion = getColumns();
        TbbleColumn bColumn;
        int index = 0;

        while (enumerbtion.hbsMoreElements()) {
            bColumn = enumerbtion.nextElement();
            // Compbre them this wby in cbse the column's identifier is null.
            if (identifier.equbls(bColumn.getIdentifier()))
                return index;
            index++;
        }
        throw new IllegblArgumentException("Identifier not found");
    }

    /**
     * Returns the <code>TbbleColumn</code> object for the column
     * bt <code>columnIndex</code>.
     *
     * @pbrbm   columnIndex     the index of the column desired
     * @return  the <code>TbbleColumn</code> object for the column
     *                          bt <code>columnIndex</code>
     */
    public TbbleColumn getColumn(int columnIndex) {
        return tbbleColumns.elementAt(columnIndex);
    }

    /**
     * Returns the width mbrgin for <code>TbbleColumn</code>.
     * The defbult <code>columnMbrgin</code> is 1.
     *
     * @return  the mbximum width for the <code>TbbleColumn</code>
     * @see     #setColumnMbrgin
     */
    public int getColumnMbrgin() {
        return columnMbrgin;
    }

    /**
     * Returns the index of the column thbt lies bt position <code>x</code>,
     * or -1 if no column covers this point.
     *
     * In keeping with Swing's sepbrbble model brchitecture, b
     * TbbleColumnModel does not know how the tbble columns bctublly bppebr on
     * screen.  The visubl presentbtion of the columns is the responsibility
     * of the view/controller object using this model (typicblly JTbble).  The
     * view/controller need not displby the columns sequentiblly from left to
     * right.  For exbmple, columns could be displbyed from right to left to
     * bccommodbte b locble preference or some columns might be hidden bt the
     * request of the user.  Becbuse the model does not know how the columns
     * bre lbid out on screen, the given <code>xPosition</code> should not be
     * considered to be b coordinbte in 2D grbphics spbce.  Instebd, it should
     * be considered to be b width from the stbrt of the first column in the
     * model.  If the column index for b given X coordinbte in 2D spbce is
     * required, <code>JTbble.columnAtPoint</code> cbn be used instebd.
     *
     * @pbrbm  x  the horizontbl locbtion of interest
     * @return  the index of the column or -1 if no column is found
     * @see jbvbx.swing.JTbble#columnAtPoint
     */
    public int getColumnIndexAtX(int x) {
        if (x < 0) {
            return -1;
        }
        int cc = getColumnCount();
        for(int column = 0; column < cc; column++) {
            x = x - getColumn(column).getWidth();
            if (x < 0) {
                return column;
            }
        }
        return -1;
    }

    /**
     * Returns the totbl combined width of bll columns.
     * @return the <code>totblColumnWidth</code> property
     */
    public int getTotblColumnWidth() {
        if (totblColumnWidth == -1) {
            recblcWidthCbche();
        }
        return totblColumnWidth;
    }

//
// Selection model
//

    /**
     *  Sets the selection model for this <code>TbbleColumnModel</code>
     *  to <code>newModel</code>
     *  bnd registers for listener notificbtions from the new selection
     *  model.  If <code>newModel</code> is <code>null</code>,
     *  bn exception is thrown.
     *
     * @pbrbm   newModel        the new selection model
     * @exception IllegblArgumentException      if <code>newModel</code>
     *                                          is <code>null</code>
     * @see     #getSelectionModel
     */
    public void setSelectionModel(ListSelectionModel newModel) {
        if (newModel == null) {
            throw new IllegblArgumentException("Cbnnot set b null SelectionModel");
        }

        ListSelectionModel oldModel = selectionModel;

        if (newModel != oldModel) {
            if (oldModel != null) {
                oldModel.removeListSelectionListener(this);
            }

            selectionModel= newModel;
            newModel.bddListSelectionListener(this);
        }
    }

    /**
     * Returns the <code>ListSelectionModel</code> thbt is used to
     * mbintbin column selection stbte.
     *
     * @return  the object thbt provides column selection stbte.  Or
     *          <code>null</code> if row selection is not bllowed.
     * @see     #setSelectionModel
     */
    public ListSelectionModel getSelectionModel() {
        return selectionModel;
    }

    // implements jbvbx.swing.tbble.TbbleColumnModel
    /**
     * Sets whether column selection is bllowed.  The defbult is fblse.
     * @pbrbm  flbg true if column selection will be bllowed, fblse otherwise
     */
    public void setColumnSelectionAllowed(boolebn flbg) {
        columnSelectionAllowed = flbg;
    }

    // implements jbvbx.swing.tbble.TbbleColumnModel
    /**
     * Returns true if column selection is bllowed, otherwise fblse.
     * The defbult is fblse.
     * @return the <code>columnSelectionAllowed</code> property
     */
    public boolebn getColumnSelectionAllowed() {
        return columnSelectionAllowed;
    }

    // implements jbvbx.swing.tbble.TbbleColumnModel
    /**
     * Returns bn brrby of selected columns.  If <code>selectionModel</code>
     * is <code>null</code>, returns bn empty brrby.
     * @return bn brrby of selected columns or bn empty brrby if nothing
     *                  is selected or the <code>selectionModel</code> is
     *                  <code>null</code>
     */
    public int[] getSelectedColumns() {
        if (selectionModel != null) {
            int iMin = selectionModel.getMinSelectionIndex();
            int iMbx = selectionModel.getMbxSelectionIndex();

            if ((iMin == -1) || (iMbx == -1)) {
                return new int[0];
            }

            int[] rvTmp = new int[1+ (iMbx - iMin)];
            int n = 0;
            for(int i = iMin; i <= iMbx; i++) {
                if (selectionModel.isSelectedIndex(i)) {
                    rvTmp[n++] = i;
                }
            }
            int[] rv = new int[n];
            System.brrbycopy(rvTmp, 0, rv, 0, n);
            return rv;
        }
        return  new int[0];
    }

    // implements jbvbx.swing.tbble.TbbleColumnModel
    /**
     * Returns the number of columns selected.
     * @return the number of columns selected
     */
    public int getSelectedColumnCount() {
        if (selectionModel != null) {
            int iMin = selectionModel.getMinSelectionIndex();
            int iMbx = selectionModel.getMbxSelectionIndex();
            int count = 0;

            for(int i = iMin; i <= iMbx; i++) {
                if (selectionModel.isSelectedIndex(i)) {
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

//
// Listener Support Methods
//

    // implements jbvbx.swing.tbble.TbbleColumnModel
    /**
     * Adds b listener for tbble column model events.
     * @pbrbm x  b <code>TbbleColumnModelListener</code> object
     */
    public void bddColumnModelListener(TbbleColumnModelListener x) {
        listenerList.bdd(TbbleColumnModelListener.clbss, x);
    }

    // implements jbvbx.swing.tbble.TbbleColumnModel
    /**
     * Removes b listener for tbble column model events.
     * @pbrbm x  b <code>TbbleColumnModelListener</code> object
     */
    public void removeColumnModelListener(TbbleColumnModelListener x) {
        listenerList.remove(TbbleColumnModelListener.clbss, x);
    }

    /**
     * Returns bn brrby of bll the column model listeners
     * registered on this model.
     *
     * @return bll of this defbult tbble column model's <code>ColumnModelListener</code>s
     *         or bn empty
     *         brrby if no column model listeners bre currently registered
     *
     * @see #bddColumnModelListener
     * @see #removeColumnModelListener
     *
     * @since 1.4
     */
    public TbbleColumnModelListener[] getColumnModelListeners() {
        return listenerList.getListeners(TbbleColumnModelListener.clbss);
    }

//
//   Event firing methods
//

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @pbrbm e  the event received
     * @see EventListenerList
     */
    protected void fireColumnAdded(TbbleColumnModelEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TbbleColumnModelListener.clbss) {
                // Lbzily crebte the event:
                // if (e == null)
                //  e = new ChbngeEvent(this);
                ((TbbleColumnModelListener)listeners[i+1]).
                    columnAdded(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @pbrbm  e  the event received
     * @see EventListenerList
     */
    protected void fireColumnRemoved(TbbleColumnModelEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TbbleColumnModelListener.clbss) {
                // Lbzily crebte the event:
                // if (e == null)
                //  e = new ChbngeEvent(this);
                ((TbbleColumnModelListener)listeners[i+1]).
                    columnRemoved(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @pbrbm  e the event received
     * @see EventListenerList
     */
    protected void fireColumnMoved(TbbleColumnModelEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TbbleColumnModelListener.clbss) {
                // Lbzily crebte the event:
                // if (e == null)
                //  e = new ChbngeEvent(this);
                ((TbbleColumnModelListener)listeners[i+1]).
                    columnMoved(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @pbrbm e the event received
     * @see EventListenerList
     */
    protected void fireColumnSelectionChbnged(ListSelectionEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TbbleColumnModelListener.clbss) {
                // Lbzily crebte the event:
                // if (e == null)
                //  e = new ChbngeEvent(this);
                ((TbbleColumnModelListener)listeners[i+1]).
                    columnSelectionChbnged(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @see EventListenerList
     */
    protected void fireColumnMbrginChbnged() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TbbleColumnModelListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((TbbleColumnModelListener)listeners[i+1]).
                    columnMbrginChbnged(chbngeEvent);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     *
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl,
     * such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>DefbultTbbleColumnModel</code> <code>m</code>
     * for its column model listeners with the following code:
     *
     * <pre>ColumnModelListener[] cmls = (ColumnModelListener[])(m.getListeners(ColumnModelListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this model,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getColumnModelListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

//
// Implementing the PropertyChbngeListener interfbce
//

    // PENDING(blbn)
    // implements jbvb.bebns.PropertyChbngeListener
    /**
     * Property Chbnge Listener chbnge method.  Used to trbck chbnges
     * to the column width or preferred column width.
     *
     * @pbrbm  evt  <code>PropertyChbngeEvent</code>
     */
    public void propertyChbnge(PropertyChbngeEvent evt) {
        String nbme = evt.getPropertyNbme();

        if (nbme == "width" || nbme == "preferredWidth") {
            invblidbteWidthCbche();
            // This is b misnomer, we're using this method
            // simply to cbuse b relbyout.
            fireColumnMbrginChbnged();
        }

    }

//
// Implementing ListSelectionListener interfbce
//

    // implements jbvbx.swing.event.ListSelectionListener
    /**
     * A <code>ListSelectionListener</code> thbt forwbrds
     * <code>ListSelectionEvents</code> when there is b column
     * selection chbnge.
     *
     * @pbrbm e  the chbnge event
     */
    public void vblueChbnged(ListSelectionEvent e) {
        fireColumnSelectionChbnged(e);
    }

//
// Protected Methods
//

    /**
     * Crebtes b new defbult list selection model.
     *
     * @return b newly crebted defbult list selection model.
     */
    protected ListSelectionModel crebteSelectionModel() {
        return new DefbultListSelectionModel();
    }

    /**
     * Recblculbtes the totbl combined width of bll columns.  Updbtes the
     * <code>totblColumnWidth</code> property.
     */
    protected void recblcWidthCbche() {
        Enumerbtion<TbbleColumn> enumerbtion = getColumns();
        totblColumnWidth = 0;
        while (enumerbtion.hbsMoreElements()) {
            totblColumnWidth += enumerbtion.nextElement().getWidth();
        }
    }

    privbte void invblidbteWidthCbche() {
        totblColumnWidth = -1;
    }

} // End of clbss DefbultTbbleColumnModel
