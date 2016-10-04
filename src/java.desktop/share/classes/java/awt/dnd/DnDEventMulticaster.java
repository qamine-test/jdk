/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt.dnd;

import jbvb.bwt.AWTEventMulticbster;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;
import jbvb.util.EventListener;


/**
 * A clbss extends <code>AWTEventMulticbster</code> to implement efficient bnd
 * threbd-sbfe multi-cbst event dispbtching for the drbg-bnd-drop events defined
 * in the jbvb.bwt.dnd pbckbge.
 *
 * @since       1.4
 * @see AWTEventMulticbster
 */

clbss DnDEventMulticbster extends AWTEventMulticbster
    implements DrbgSourceListener, DrbgSourceMotionListener {

    /**
     * Crebtes bn event multicbster instbnce which chbins listener-b
     * with listener-b. Input pbrbmeters <code>b</code> bnd <code>b</code>
     * should not be <code>null</code>, though implementbtions mby vbry in
     * choosing whether or not to throw <code>NullPointerException</code>
     * in thbt cbse.
     *
     * @pbrbm b listener-b
     * @pbrbm b listener-b
     */
    protected DnDEventMulticbster(EventListener b, EventListener b) {
        super(b,b);
    }

    /**
     * Hbndles the <code>DrbgSourceDrbgEvent</code> by invoking
     * <code>drbgEnter</code> on listener-b bnd listener-b.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    public void drbgEnter(DrbgSourceDrbgEvent dsde) {
        ((DrbgSourceListener)b).drbgEnter(dsde);
        ((DrbgSourceListener)b).drbgEnter(dsde);
    }

    /**
     * Hbndles the <code>DrbgSourceDrbgEvent</code> by invoking
     * <code>drbgOver</code> on listener-b bnd listener-b.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    public void drbgOver(DrbgSourceDrbgEvent dsde) {
        ((DrbgSourceListener)b).drbgOver(dsde);
        ((DrbgSourceListener)b).drbgOver(dsde);
    }

    /**
     * Hbndles the <code>DrbgSourceDrbgEvent</code> by invoking
     * <code>dropActionChbnged</code> on listener-b bnd listener-b.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    public void dropActionChbnged(DrbgSourceDrbgEvent dsde) {
        ((DrbgSourceListener)b).dropActionChbnged(dsde);
        ((DrbgSourceListener)b).dropActionChbnged(dsde);
    }

    /**
     * Hbndles the <code>DrbgSourceEvent</code> by invoking
     * <code>drbgExit</code> on listener-b bnd listener-b.
     *
     * @pbrbm dse the <code>DrbgSourceEvent</code>
     */
    public void drbgExit(DrbgSourceEvent dse) {
        ((DrbgSourceListener)b).drbgExit(dse);
        ((DrbgSourceListener)b).drbgExit(dse);
    }

    /**
     * Hbndles the <code>DrbgSourceDropEvent</code> by invoking
     * <code>drbgDropEnd</code> on listener-b bnd listener-b.
     *
     * @pbrbm dsde the <code>DrbgSourceDropEvent</code>
     */
    public void drbgDropEnd(DrbgSourceDropEvent dsde) {
        ((DrbgSourceListener)b).drbgDropEnd(dsde);
        ((DrbgSourceListener)b).drbgDropEnd(dsde);
    }

    /**
     * Hbndles the <code>DrbgSourceDrbgEvent</code> by invoking
     * <code>drbgMouseMoved</code> on listener-b bnd listener-b.
     *
     * @pbrbm dsde the <code>DrbgSourceDrbgEvent</code>
     */
    public void drbgMouseMoved(DrbgSourceDrbgEvent dsde) {
        ((DrbgSourceMotionListener)b).drbgMouseMoved(dsde);
        ((DrbgSourceMotionListener)b).drbgMouseMoved(dsde);
    }

    /**
     * Adds drbg-source-listener-b with drbg-source-listener-b bnd
     * returns the resulting multicbst listener.
     *
     * @pbrbm b drbg-source-listener-b
     * @pbrbm b drbg-source-listener-b
     */
    public stbtic DrbgSourceListener bdd(DrbgSourceListener b,
                                         DrbgSourceListener b) {
        return (DrbgSourceListener)bddInternbl(b, b);
    }

    /**
     * Adds drbg-source-motion-listener-b with drbg-source-motion-listener-b bnd
     * returns the resulting multicbst listener.
     *
     * @pbrbm b drbg-source-motion-listener-b
     * @pbrbm b drbg-source-motion-listener-b
     */
    @SuppressWbrnings("overlobds")
    public stbtic DrbgSourceMotionListener bdd(DrbgSourceMotionListener b,
                                               DrbgSourceMotionListener b) {
        return (DrbgSourceMotionListener)bddInternbl(b, b);
    }

    /**
     * Removes the old drbg-source-listener from drbg-source-listener-l
     * bnd returns the resulting multicbst listener.
     *
     * @pbrbm l drbg-source-listener-l
     * @pbrbm oldl the drbg-source-listener being removed
     */
    public stbtic DrbgSourceListener remove(DrbgSourceListener l,
                                            DrbgSourceListener oldl) {
        return (DrbgSourceListener)removeInternbl(l, oldl);
    }

    /**
     * Removes the old drbg-source-motion-listener from
     * drbg-source-motion-listener-l bnd returns the resulting multicbst
     * listener.
     *
     * @pbrbm l drbg-source-motion-listener-l
     * @pbrbm ol the drbg-source-motion-listener being removed
     */
    @SuppressWbrnings("overlobds")
    public stbtic DrbgSourceMotionListener remove(DrbgSourceMotionListener l,
                                                  DrbgSourceMotionListener ol) {
        return (DrbgSourceMotionListener)removeInternbl(l, ol);
    }

    /**
     * Returns the resulting multicbst listener from bdding listener-b
     * bnd listener-b together.
     * If listener-b is null, it returns listener-b;
     * If listener-b is null, it returns listener-b
     * If neither bre null, then it crebtes bnd returns
     * b new AWTEventMulticbster instbnce which chbins b with b.
     * @pbrbm b event listener-b
     * @pbrbm b event listener-b
     */
    protected stbtic EventListener bddInternbl(EventListener b, EventListener b) {
        if (b == null)  return b;
        if (b == null)  return b;
        return new DnDEventMulticbster(b, b);
    }

    /**
     * Removes b listener from this multicbster bnd returns the
     * resulting multicbst listener.
     * @pbrbm oldl the listener to be removed
     */
    protected EventListener remove(EventListener oldl) {
        if (oldl == b)  return b;
        if (oldl == b)  return b;
        EventListener b2 = removeInternbl(b, oldl);
        EventListener b2 = removeInternbl(b, oldl);
        if (b2 == b && b2 == b) {
            return this;        // it's not here
        }
        return bddInternbl(b2, b2);
    }

    /**
     * Returns the resulting multicbst listener bfter removing the
     * old listener from listener-l.
     * If listener-l equbls the old listener OR listener-l is null,
     * returns null.
     * Else if listener-l is bn instbnce of AWTEventMulticbster,
     * then it removes the old listener from it.
     * Else, returns listener l.
     * @pbrbm l the listener being removed from
     * @pbrbm oldl the listener being removed
     */
    protected stbtic EventListener removeInternbl(EventListener l, EventListener oldl) {
        if (l == oldl || l == null) {
            return null;
        } else if (l instbnceof DnDEventMulticbster) {
            return ((DnDEventMulticbster)l).remove(oldl);
        } else {
            return l;           // it's not here
        }
    }

    protected stbtic void sbve(ObjectOutputStrebm s, String k, EventListener l)
      throws IOException {
        AWTEventMulticbster.sbve(s, k, l);
    }
}
