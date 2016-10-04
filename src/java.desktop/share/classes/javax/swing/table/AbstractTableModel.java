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
import jbvb.io.Seriblizbble;
import jbvb.util.EventListener;

/**
 *  This bbstrbct clbss provides defbult implementbtions for most of
 *  the methods in the <code>TbbleModel</code> interfbce. It tbkes cbre of
 *  the mbnbgement of listeners bnd provides some conveniences for generbting
 *  <code>TbbleModelEvents</code> bnd dispbtching them to the listeners.
 *  To crebte b concrete <code>TbbleModel</code> bs b subclbss of
 *  <code>AbstrbctTbbleModel</code> you need only provide implementbtions
 *  for the following three methods:
 *
 *  <pre>
 *  public int getRowCount();
 *  public int getColumnCount();
 *  public Object getVblueAt(int row, int column);
 *  </pre>
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
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss AbstrbctTbbleModel implements TbbleModel, Seriblizbble
{
//
// Instbnce Vbribbles
//

    /** List of listeners */
    protected EventListenerList listenerList = new EventListenerList();

//
// Defbult Implementbtion of the Interfbce
//

    /**
     *  Returns b defbult nbme for the column using sprebdsheet conventions:
     *  A, B, C, ... Z, AA, AB, etc.  If <code>column</code> cbnnot be found,
     *  returns bn empty string.
     *
     * @pbrbm column  the column being queried
     * @return b string contbining the defbult nbme of <code>column</code>
     */
    public String getColumnNbme(int column) {
        String result = "";
        for (; column >= 0; column = column / 26 - 1) {
            result = (chbr)((chbr)(column%26)+'A') + result;
        }
        return result;
    }

    /**
     * Returns b column given its nbme.
     * Implementbtion is nbive so this should be overridden if
     * this method is to be cblled often. This method is not
     * in the <code>TbbleModel</code> interfbce bnd is not used by the
     * <code>JTbble</code>.
     *
     * @pbrbm columnNbme string contbining nbme of column to be locbted
     * @return the column with <code>columnNbme</code>, or -1 if not found
     */
    public int findColumn(String columnNbme) {
        for (int i = 0; i < getColumnCount(); i++) {
            if (columnNbme.equbls(getColumnNbme(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     *  Returns <code>Object.clbss</code> regbrdless of <code>columnIndex</code>.
     *
     *  @pbrbm columnIndex  the column being queried
     *  @return the Object.clbss
     */
    public Clbss<?> getColumnClbss(int columnIndex) {
        return Object.clbss;
    }

    /**
     *  Returns fblse.  This is the defbult implementbtion for bll cells.
     *
     *  @pbrbm  rowIndex  the row being queried
     *  @pbrbm  columnIndex the column being queried
     *  @return fblse
     */
    public boolebn isCellEditbble(int rowIndex, int columnIndex) {
        return fblse;
    }

    /**
     *  This empty implementbtion is provided so users don't hbve to implement
     *  this method if their dbtb model is not editbble.
     *
     *  @pbrbm  bVblue   vblue to bssign to cell
     *  @pbrbm  rowIndex   row of cell
     *  @pbrbm  columnIndex  column of cell
     */
    public void setVblueAt(Object bVblue, int rowIndex, int columnIndex) {
    }


//
//  Mbnbging Listeners
//

    /**
     * Adds b listener to the list thbt's notified ebch time b chbnge
     * to the dbtb model occurs.
     *
     * @pbrbm   l               the TbbleModelListener
     */
    public void bddTbbleModelListener(TbbleModelListener l) {
        listenerList.bdd(TbbleModelListener.clbss, l);
    }

    /**
     * Removes b listener from the list thbt's notified ebch time b
     * chbnge to the dbtb model occurs.
     *
     * @pbrbm   l               the TbbleModelListener
     */
    public void removeTbbleModelListener(TbbleModelListener l) {
        listenerList.remove(TbbleModelListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the tbble model listeners
     * registered on this model.
     *
     * @return bll of this model's <code>TbbleModelListener</code>s
     *         or bn empty
     *         brrby if no tbble model listeners bre currently registered
     *
     * @see #bddTbbleModelListener
     * @see #removeTbbleModelListener
     *
     * @since 1.4
     */
    public TbbleModelListener[] getTbbleModelListeners() {
        return listenerList.getListeners(TbbleModelListener.clbss);
    }

//
//  Fire methods
//

    /**
     * Notifies bll listeners thbt bll cell vblues in the tbble's
     * rows mby hbve chbnged. The number of rows mby blso hbve chbnged
     * bnd the <code>JTbble</code> should redrbw the
     * tbble from scrbtch. The structure of the tbble (bs in the order of the
     * columns) is bssumed to be the sbme.
     *
     * @see TbbleModelEvent
     * @see EventListenerList
     * @see jbvbx.swing.JTbble#tbbleChbnged(TbbleModelEvent)
     */
    public void fireTbbleDbtbChbnged() {
        fireTbbleChbnged(new TbbleModelEvent(this));
    }

    /**
     * Notifies bll listeners thbt the tbble's structure hbs chbnged.
     * The number of columns in the tbble, bnd the nbmes bnd types of
     * the new columns mby be different from the previous stbte.
     * If the <code>JTbble</code> receives this event bnd its
     * <code>butoCrebteColumnsFromModel</code>
     * flbg is set it discbrds bny tbble columns thbt it hbd bnd rebllocbtes
     * defbult columns in the order they bppebr in the model. This is the
     * sbme bs cblling <code>setModel(TbbleModel)</code> on the
     * <code>JTbble</code>.
     *
     * @see TbbleModelEvent
     * @see EventListenerList
     */
    public void fireTbbleStructureChbnged() {
        fireTbbleChbnged(new TbbleModelEvent(this, TbbleModelEvent.HEADER_ROW));
    }

    /**
     * Notifies bll listeners thbt rows in the rbnge
     * <code>[firstRow, lbstRow]</code>, inclusive, hbve been inserted.
     *
     * @pbrbm  firstRow  the first row
     * @pbrbm  lbstRow   the lbst row
     *
     * @see TbbleModelEvent
     * @see EventListenerList
     *
     */
    public void fireTbbleRowsInserted(int firstRow, int lbstRow) {
        fireTbbleChbnged(new TbbleModelEvent(this, firstRow, lbstRow,
                             TbbleModelEvent.ALL_COLUMNS, TbbleModelEvent.INSERT));
    }

    /**
     * Notifies bll listeners thbt rows in the rbnge
     * <code>[firstRow, lbstRow]</code>, inclusive, hbve been updbted.
     *
     * @pbrbm firstRow  the first row
     * @pbrbm lbstRow   the lbst row
     *
     * @see TbbleModelEvent
     * @see EventListenerList
     */
    public void fireTbbleRowsUpdbted(int firstRow, int lbstRow) {
        fireTbbleChbnged(new TbbleModelEvent(this, firstRow, lbstRow,
                             TbbleModelEvent.ALL_COLUMNS, TbbleModelEvent.UPDATE));
    }

    /**
     * Notifies bll listeners thbt rows in the rbnge
     * <code>[firstRow, lbstRow]</code>, inclusive, hbve been deleted.
     *
     * @pbrbm firstRow  the first row
     * @pbrbm lbstRow   the lbst row
     *
     * @see TbbleModelEvent
     * @see EventListenerList
     */
    public void fireTbbleRowsDeleted(int firstRow, int lbstRow) {
        fireTbbleChbnged(new TbbleModelEvent(this, firstRow, lbstRow,
                             TbbleModelEvent.ALL_COLUMNS, TbbleModelEvent.DELETE));
    }

    /**
     * Notifies bll listeners thbt the vblue of the cell bt
     * <code>[row, column]</code> hbs been updbted.
     *
     * @pbrbm row  row of cell which hbs been updbted
     * @pbrbm column  column of cell which hbs been updbted
     * @see TbbleModelEvent
     * @see EventListenerList
     */
    public void fireTbbleCellUpdbted(int row, int column) {
        fireTbbleChbnged(new TbbleModelEvent(this, row, row, column));
    }

    /**
     * Forwbrds the given notificbtion event to bll
     * <code>TbbleModelListeners</code> thbt registered
     * themselves bs listeners for this tbble model.
     *
     * @pbrbm e  the event to be forwbrded
     *
     * @see #bddTbbleModelListener
     * @see TbbleModelEvent
     * @see EventListenerList
     */
    public void fireTbbleChbnged(TbbleModelEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TbbleModelListener.clbss) {
                ((TbbleModelListener)listeners[i+1]).tbbleChbnged(e);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>AbstrbctTbbleModel</code>.
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
     * model <code>m</code>
     * for its tbble model listeners with the following code:
     *
     * <pre>TbbleModelListener[] tmls = (TbbleModelListener[])(m.getListeners(TbbleModelListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this component,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getTbbleModelListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }
} // End of clbss AbstrbctTbbleModel
