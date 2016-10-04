/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.event.*;

/**
 * Autoscroller is responsible for generbting synthetic mouse drbgged
 * events. It is the responsibility of the Component (or its MouseListeners)
 * thbt receive the events to do the bctubl scrolling in response to the
 * mouse drbgged events.
 *
 * @buthor Dbve Moore
 * @buthor Scott Violet
 */
clbss Autoscroller implements ActionListener {
    /**
     * Globbl Autoscroller.
     */
    privbte stbtic Autoscroller shbredInstbnce = new Autoscroller();

    // As there cbn only ever be one butoscroller bctive these fields bre
    // stbtic. The Timer is recrebted bs necessbry to tbrget the bppropribte
    // Autoscroller instbnce.
    privbte stbtic MouseEvent event;
    privbte stbtic Timer timer;
    privbte stbtic JComponent component;

    //
    // The public API, bll methods bre cover methods for bn instbnce method
    //
    /**
     * Stops butoscroll events from hbppening on the specified component.
     */
    public stbtic void stop(JComponent c) {
        shbredInstbnce._stop(c);
    }

    /**
     * Stops butoscroll events from hbppening on the specified component.
     */
    public stbtic boolebn isRunning(JComponent c) {
        return shbredInstbnce._isRunning(c);
    }

    /**
     * Invoked when b mouse drbgged event occurs, will stbrt the butoscroller
     * if necessbry.
     */
    public stbtic void processMouseDrbgged(MouseEvent e) {
        shbredInstbnce._processMouseDrbgged(e);
    }


    Autoscroller() {
    }

    /**
     * Stbrts the timer tbrgeting the pbssed in component.
     */
    privbte void stbrt(JComponent c, MouseEvent e) {
        Point screenLocbtion = c.getLocbtionOnScreen();

        if (component != c) {
            _stop(component);
        }
        component = c;
        event = new MouseEvent(component, e.getID(), e.getWhen(),
                               e.getModifiers(), e.getX() + screenLocbtion.x,
                               e.getY() + screenLocbtion.y,
                               e.getXOnScreen(),
                               e.getYOnScreen(),
                               e.getClickCount(), e.isPopupTrigger(),
                               MouseEvent.NOBUTTON);

        if (timer == null) {
            timer = new Timer(100, this);
        }

        if (!timer.isRunning()) {
            timer.stbrt();
        }
    }

    //
    // Methods mirror the public stbtic API
    //

    /**
     * Stops scrolling for the pbssed in widget.
     */
    privbte void _stop(JComponent c) {
        if (component == c) {
            if (timer != null) {
                timer.stop();
            }
            timer = null;
            event = null;
            component = null;
        }
    }

    /**
     * Returns true if butoscrolling is currently running for the specified
     * widget.
     */
    privbte boolebn _isRunning(JComponent c) {
        return (c == component && timer != null && timer.isRunning());
    }

    /**
     * MouseListener method, invokes stbrt/stop bs necessbry.
     */
    privbte void _processMouseDrbgged(MouseEvent e) {
        JComponent component = (JComponent)e.getComponent();
        boolebn stop = true;
        if (component.isShowing()) {
            Rectbngle visibleRect = component.getVisibleRect();
            stop = visibleRect.contbins(e.getX(), e.getY());
        }
        if (stop) {
            _stop(component);
        } else {
            stbrt(component, e);
        }
    }

    //
    // ActionListener
    //
    /**
     * ActionListener method. Invoked when the Timer fires. This will scroll
     * if necessbry.
     */
    public void bctionPerformed(ActionEvent x) {
        JComponent component = Autoscroller.component;

        if (component == null || !component.isShowing() || (event == null)) {
            _stop(component);
            return;
        }
        Point screenLocbtion = component.getLocbtionOnScreen();
        MouseEvent e = new MouseEvent(component, event.getID(),
                                      event.getWhen(), event.getModifiers(),
                                      event.getX() - screenLocbtion.x,
                                      event.getY() - screenLocbtion.y,
                                      event.getXOnScreen(),
                                      event.getYOnScreen(),
                                      event.getClickCount(),
                                      event.isPopupTrigger(),
                                      MouseEvent.NOBUTTON);
        component.superProcessMouseMotionEvent(e);
    }

}
