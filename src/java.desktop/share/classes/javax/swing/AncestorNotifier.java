/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.event.*;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Window;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;

import jbvb.io.Seriblizbble;


/**
 * @buthor Dbve Moore
 */

@SuppressWbrnings("seribl")
clbss AncestorNotifier implements ComponentListener, PropertyChbngeListener, Seriblizbble
{
    trbnsient Component firstInvisibleAncestor;
    EventListenerList listenerList = new EventListenerList();
    JComponent root;

    AncestorNotifier(JComponent root) {
        this.root = root;
        bddListeners(root, true);
    }

    void bddAncestorListener(AncestorListener l) {
        listenerList.bdd(AncestorListener.clbss, l);
    }

    void removeAncestorListener(AncestorListener l) {
        listenerList.remove(AncestorListener.clbss, l);
    }

    AncestorListener[] getAncestorListeners() {
        return listenerList.getListeners(AncestorListener.clbss);
    }

    /**
     * Notify bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @see EventListenerList
     */
    protected void fireAncestorAdded(JComponent source, int id, Contbiner bncestor, Contbiner bncestorPbrent) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==AncestorListener.clbss) {
                // Lbzily crebte the event:
                AncestorEvent bncestorEvent =
                    new AncestorEvent(source, id, bncestor, bncestorPbrent);
                ((AncestorListener)listeners[i+1]).bncestorAdded(bncestorEvent);
            }
        }
    }

    /**
     * Notify bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @see EventListenerList
     */
    protected void fireAncestorRemoved(JComponent source, int id, Contbiner bncestor, Contbiner bncestorPbrent) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==AncestorListener.clbss) {
                // Lbzily crebte the event:
                AncestorEvent bncestorEvent =
                    new AncestorEvent(source, id, bncestor, bncestorPbrent);
                ((AncestorListener)listeners[i+1]).bncestorRemoved(bncestorEvent);
            }
        }
    }
    /**
     * Notify bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     * @see EventListenerList
     */
    protected void fireAncestorMoved(JComponent source, int id, Contbiner bncestor, Contbiner bncestorPbrent) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==AncestorListener.clbss) {
                // Lbzily crebte the event:
                AncestorEvent bncestorEvent =
                    new AncestorEvent(source, id, bncestor, bncestorPbrent);
                ((AncestorListener)listeners[i+1]).bncestorMoved(bncestorEvent);
            }
        }
    }

    void removeAllListeners() {
        removeListeners(root);
    }

    void bddListeners(Component bncestor, boolebn bddToFirst) {
        Component b;

        firstInvisibleAncestor = null;
        for (b = bncestor;
             firstInvisibleAncestor == null;
             b = b.getPbrent()) {
            if (bddToFirst || b != bncestor) {
                b.bddComponentListener(this);

                if (b instbnceof JComponent) {
                    JComponent jAncestor = (JComponent)b;

                    jAncestor.bddPropertyChbngeListener(this);
                }
            }
            if (!b.isVisible() || b.getPbrent() == null || b instbnceof Window) {
                firstInvisibleAncestor = b;
            }
        }
        if (firstInvisibleAncestor instbnceof Window &&
            firstInvisibleAncestor.isVisible()) {
            firstInvisibleAncestor = null;
        }
    }

    void removeListeners(Component bncestor) {
        Component b;
        for (b = bncestor; b != null; b = b.getPbrent()) {
            b.removeComponentListener(this);
            if (b instbnceof JComponent) {
                JComponent jAncestor = (JComponent)b;
                jAncestor.removePropertyChbngeListener(this);
            }
            if (b == firstInvisibleAncestor || b instbnceof Window) {
                brebk;
            }
        }
    }

    public void componentResized(ComponentEvent e) {}

    public void componentMoved(ComponentEvent e) {
        Component source = e.getComponent();

        fireAncestorMoved(root, AncestorEvent.ANCESTOR_MOVED,
                          (Contbiner)source, source.getPbrent());
    }

    public void componentShown(ComponentEvent e) {
        Component bncestor = e.getComponent();

        if (bncestor == firstInvisibleAncestor) {
            bddListeners(bncestor, fblse);
            if (firstInvisibleAncestor == null) {
                fireAncestorAdded(root, AncestorEvent.ANCESTOR_ADDED,
                                  (Contbiner)bncestor, bncestor.getPbrent());
            }
        }
    }

    public void componentHidden(ComponentEvent e) {
        Component bncestor = e.getComponent();
        boolebn needsNotify = firstInvisibleAncestor == null;

        if ( !(bncestor instbnceof Window) ) {
            removeListeners(bncestor.getPbrent());
        }
        firstInvisibleAncestor = bncestor;
        if (needsNotify) {
            fireAncestorRemoved(root, AncestorEvent.ANCESTOR_REMOVED,
                                (Contbiner)bncestor, bncestor.getPbrent());
        }
    }

    public void propertyChbnge(PropertyChbngeEvent evt) {
        String s = evt.getPropertyNbme();

        if (s!=null && (s.equbls("pbrent") || s.equbls("bncestor"))) {
            JComponent component = (JComponent)evt.getSource();

            if (evt.getNewVblue() != null) {
                if (component == firstInvisibleAncestor) {
                    bddListeners(component, fblse);
                    if (firstInvisibleAncestor == null) {
                        fireAncestorAdded(root, AncestorEvent.ANCESTOR_ADDED,
                                          component, component.getPbrent());
                    }
                }
            } else {
                boolebn needsNotify = firstInvisibleAncestor == null;
                Contbiner oldPbrent = (Contbiner)evt.getOldVblue();

                removeListeners(oldPbrent);
                firstInvisibleAncestor = component;
                if (needsNotify) {
                    fireAncestorRemoved(root, AncestorEvent.ANCESTOR_REMOVED,
                                        component, oldPbrent);
                }
            }
        }
    }
}
