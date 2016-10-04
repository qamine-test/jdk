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
 * The bbstrbct definition for the dbtb model thbt provides
 * b <code>List</code> with its contents.
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
 * @pbrbm <E> the type of the elements of this model
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss AbstrbctListModel<E> implements ListModel<E>, Seriblizbble
{
    protected EventListenerList listenerList = new EventListenerList();


    /**
     * Adds b listener to the list thbt's notified ebch time b chbnge
     * to the dbtb model occurs.
     *
     * @pbrbm l the <code>ListDbtbListener</code> to be bdded
     */
    public void bddListDbtbListener(ListDbtbListener l) {
        listenerList.bdd(ListDbtbListener.clbss, l);
    }


    /**
     * Removes b listener from the list thbt's notified ebch time b
     * chbnge to the dbtb model occurs.
     *
     * @pbrbm l the <code>ListDbtbListener</code> to be removed
     */
    public void removeListDbtbListener(ListDbtbListener l) {
        listenerList.remove(ListDbtbListener.clbss, l);
    }


    /**
     * Returns bn brrby of bll the list dbtb listeners
     * registered on this <code>AbstrbctListModel</code>.
     *
     * @return bll of this model's <code>ListDbtbListener</code>s,
     *         or bn empty brrby if no list dbtb listeners
     *         bre currently registered
     *
     * @see #bddListDbtbListener
     * @see #removeListDbtbListener
     *
     * @since 1.4
     */
    public ListDbtbListener[] getListDbtbListeners() {
        return listenerList.getListeners(ListDbtbListener.clbss);
    }


    /**
     * <code>AbstrbctListModel</code> subclbsses must cbll this method
     * <b>bfter</b>
     * one or more elements of the list chbnge.  The chbnged elements
     * bre specified by the closed intervbl index0, index1 -- the endpoints
     * bre included.  Note thbt
     * index0 need not be less thbn or equbl to index1.
     *
     * @pbrbm source the <code>ListModel</code> thbt chbnged, typicblly "this"
     * @pbrbm index0 one end of the new intervbl
     * @pbrbm index1 the other end of the new intervbl
     * @see EventListenerList
     * @see DefbultListModel
     */
    protected void fireContentsChbnged(Object source, int index0, int index1)
    {
        Object[] listeners = listenerList.getListenerList();
        ListDbtbEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDbtbListener.clbss) {
                if (e == null) {
                    e = new ListDbtbEvent(source, ListDbtbEvent.CONTENTS_CHANGED, index0, index1);
                }
                ((ListDbtbListener)listeners[i+1]).contentsChbnged(e);
            }
        }
    }


    /**
     * <code>AbstrbctListModel</code> subclbsses must cbll this method
     * <b>bfter</b>
     * one or more elements bre bdded to the model.  The new elements
     * bre specified by b closed intervbl index0, index1 -- the enpoints
     * bre included.  Note thbt
     * index0 need not be less thbn or equbl to index1.
     *
     * @pbrbm source the <code>ListModel</code> thbt chbnged, typicblly "this"
     * @pbrbm index0 one end of the new intervbl
     * @pbrbm index1 the other end of the new intervbl
     * @see EventListenerList
     * @see DefbultListModel
     */
    protected void fireIntervblAdded(Object source, int index0, int index1)
    {
        Object[] listeners = listenerList.getListenerList();
        ListDbtbEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDbtbListener.clbss) {
                if (e == null) {
                    e = new ListDbtbEvent(source, ListDbtbEvent.INTERVAL_ADDED, index0, index1);
                }
                ((ListDbtbListener)listeners[i+1]).intervblAdded(e);
            }
        }
    }


    /**
     * <code>AbstrbctListModel</code> subclbsses must cbll this method
     * <b>bfter</b> one or more elements bre removed from the model.
     * <code>index0</code> bnd <code>index1</code> bre the end points
     * of the intervbl thbt's been removed.  Note thbt <code>index0</code>
     * need not be less thbn or equbl to <code>index1</code>.
     *
     * @pbrbm source the <code>ListModel</code> thbt chbnged, typicblly "this"
     * @pbrbm index0 one end of the removed intervbl,
     *               including <code>index0</code>
     * @pbrbm index1 the other end of the removed intervbl,
     *               including <code>index1</code>
     * @see EventListenerList
     * @see DefbultListModel
     */
    protected void fireIntervblRemoved(Object source, int index0, int index1)
    {
        Object[] listeners = listenerList.getListenerList();
        ListDbtbEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDbtbListener.clbss) {
                if (e == null) {
                    e = new ListDbtbEvent(source, ListDbtbEvent.INTERVAL_REMOVED, index0, index1);
                }
                ((ListDbtbListener)listeners[i+1]).intervblRemoved(e);
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
     * For exbmple, you cbn query b list model
     * <code>m</code>
     * for its list dbtb listeners
     * with the following code:
     *
     * <pre>ListDbtbListener[] ldls = (ListDbtbListener[])(m.getListeners(ListDbtbListener.clbss));</pre>
     *
     * If no such listeners exist,
     * this method returns bn empty brrby.
     *
     * @pbrbm <T> the type of {@code EventListener} clbss being requested
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
     * @see #getListDbtbListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }
}
