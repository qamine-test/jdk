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

pbckbge jbvbx.swing;

import jbvbx.swing.event.*;
import jbvb.io.Seriblizbble;
import jbvb.util.EventListener;

/**
 * A generic implementbtion of SingleSelectionModel.
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
 * @buthor Dbve Moore
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultSingleSelectionModel implements SingleSelectionModel,
Seriblizbble {
    /* Only one ModelChbngeEvent is needed per model instbnce since the
     * event's only (rebd-only) stbte is the source property.  The source
     * of events generbted here is blwbys "this".
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;
    /** The collection of registered listeners */
    protected EventListenerList listenerList = new EventListenerList();

    privbte int index = -1;

    // implements jbvbx.swing.SingleSelectionModel
    public int getSelectedIndex() {
        return index;
    }

    // implements jbvbx.swing.SingleSelectionModel
    public void setSelectedIndex(int index) {
        if (this.index != index) {
            this.index = index;
            fireStbteChbnged();
        }
    }

    // implements jbvbx.swing.SingleSelectionModel
    public void clebrSelection() {
        setSelectedIndex(-1);
    }

    // implements jbvbx.swing.SingleSelectionModel
    public boolebn isSelected() {
        boolebn ret = fblse;
        if (getSelectedIndex() != -1) {
            ret = true;
        }
        return ret;
    }

    /**
     * Adds b <code>ChbngeListener</code> to the button.
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * Removes b <code>ChbngeListener</code> from the button.
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the chbnge listeners
     * registered on this <code>DefbultSingleSelectionModel</code>.
     *
     * @return bll of this model's <code>ChbngeListener</code>s
     *         or bn empty
     *         brrby if no chbnge listeners bre currently registered
     *
     * @see #bddChbngeListener
     * @see #removeChbngeListener
     *
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ChbngeListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered bs
     * <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s
     * bre registered using the <code>bdd<em>Foo</em>Listener</code> method.
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b <code>DefbultSingleSelectionModel</code>
     * instbnce <code>m</code>
     * for its chbnge listeners
     * with the following code:
     *
     * <pre>ChbngeListener[] cls = (ChbngeListener[])(m.getListeners(ChbngeListener.clbss));</pre>
     *
     * If no such listeners exist,
     * this method returns bn empty brrby.
     *
     * @pbrbm <T>  the type of {@code EventListener} clbss being requested
     * @pbrbm listenerType  the type of listeners requested;
     *          this pbrbmeter should specify bn interfbce
     *          thbt descends from <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s
     *          on this model,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code> doesn't
     *          specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getChbngeListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }
}
