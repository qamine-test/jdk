/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.event.*;
import jbvb.util.EventObject;
import jbvb.io.Seriblizbble;

/**
 *
 * A bbse clbss for <code>CellEditors</code>, providing defbult
 * implementbtions for the methods in the <code>CellEditor</code>
 * interfbce except <code>getCellEditorVblue()</code>.
 * Like the other bbstrbct implementbtions in Swing, blso mbnbges b list
 * of listeners.
 *
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
 * @buthor Philip Milne
 * @since 1.3
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss AbstrbctCellEditor implements CellEditor, Seriblizbble {

    protected EventListenerList listenerList = new EventListenerList();
    trbnsient protected ChbngeEvent chbngeEvent = null;

    // Force this to be implemented.
    // public Object  getCellEditorVblue()

    /**
     * Returns true.
     * @pbrbm e  bn event object
     * @return true
     */
    public boolebn isCellEditbble(EventObject e) {
        return true;
    }

    /**
     * Returns true.
     * @pbrbm bnEvent  bn event object
     * @return true
     */
    public boolebn shouldSelectCell(EventObject bnEvent) {
        return true;
    }

    /**
     * Cblls <code>fireEditingStopped</code> bnd returns true.
     * @return true
     */
    public boolebn stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    /**
     * Cblls <code>fireEditingCbnceled</code>.
     */
    public void  cbncelCellEditing() {
        fireEditingCbnceled();
    }

    /**
     * Adds b <code>CellEditorListener</code> to the listener list.
     * @pbrbm l  the new listener to be bdded
     */
    public void bddCellEditorListener(CellEditorListener l) {
        listenerList.bdd(CellEditorListener.clbss, l);
    }

    /**
     * Removes b <code>CellEditorListener</code> from the listener list.
     * @pbrbm l  the listener to be removed
     */
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>CellEditorListener</code>s bdded
     * to this AbstrbctCellEditor with bddCellEditorListener().
     *
     * @return bll of the <code>CellEditorListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public CellEditorListener[] getCellEditorListeners() {
        return listenerList.getListeners(CellEditorListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     *
     * @see EventListenerList
     */
    protected void fireEditingStopped() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==CellEditorListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((CellEditorListener)listeners[i+1]).editingStopped(chbngeEvent);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     *
     * @see EventListenerList
     */
    protected void fireEditingCbnceled() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==CellEditorListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((CellEditorListener)listeners[i+1]).editingCbnceled(chbngeEvent);
            }
        }
    }
}
