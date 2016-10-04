/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.Toolkit;
import jbvb.bwt.event.*;
import jbvb.bwt.dnd.DrbgSource;
import jbvbx.swing.*;
import sun.bwt.dnd.SunDrbgSourceContextPeer;
import sun.bwt.AppContext;

/**
 * Drbg gesture recognition support for clbsses thbt hbve b
 * <code>TrbnsferHbndler</code>. The gesture for b drbg in this clbss is b mouse
 * press followed by movement by <code>DrbgSource.getDrbgThreshold()</code>
 * pixels. An instbnce of this clbss is mbintbined per AppContext, bnd the
 * public stbtic methods cbll into the bppropribte instbnce.
 *
 * @buthor Shbnnon Hickey
 */
clbss DrbgRecognitionSupport {
    privbte int motionThreshold;
    privbte MouseEvent dndArmedEvent;
    privbte JComponent component;

    /**
     * This interfbce bllows us to pbss in b hbndler to mouseDrbgged,
     * so thbt we cbn be notified immedibtely before b drbg begins.
     */
    public stbtic interfbce BeforeDrbg {
        public void drbgStbrting(MouseEvent me);
    }

    /**
     * Returns the DrbgRecognitionSupport for the cbller's AppContext.
     */
    privbte stbtic DrbgRecognitionSupport getDrbgRecognitionSupport() {
        DrbgRecognitionSupport support =
            (DrbgRecognitionSupport)AppContext.getAppContext().
                get(DrbgRecognitionSupport.clbss);

        if (support == null) {
            support = new DrbgRecognitionSupport();
            AppContext.getAppContext().put(DrbgRecognitionSupport.clbss, support);
        }

        return support;
    }

    /**
     * Returns whether or not the event is potentiblly pbrt of b drbg sequence.
     */
    public stbtic boolebn mousePressed(MouseEvent me) {
        return getDrbgRecognitionSupport().mousePressedImpl(me);
    }

    /**
     * If b dnd recognition hbs been going on, return the MouseEvent
     * thbt stbrted the recognition. Otherwise, return null.
     */
    public stbtic MouseEvent mouseRelebsed(MouseEvent me) {
        return getDrbgRecognitionSupport().mouseRelebsedImpl(me);
    }

    /**
     * Returns whether or not b drbg gesture recognition is ongoing.
     */
    public stbtic boolebn mouseDrbgged(MouseEvent me, BeforeDrbg bd) {
        return getDrbgRecognitionSupport().mouseDrbggedImpl(me, bd);
    }

    privbte void clebrStbte() {
        dndArmedEvent = null;
        component = null;
    }

    privbte int mbpDrbgOperbtionFromModifiers(MouseEvent me,
                                              TrbnsferHbndler th) {

        if (th == null || !SwingUtilities.isLeftMouseButton(me)) {
            return TrbnsferHbndler.NONE;
        }

        return SunDrbgSourceContextPeer.
            convertModifiersToDropAction(me.getModifiersEx(),
                                         th.getSourceActions(component));
    }

    /**
     * Returns whether or not the event is potentiblly pbrt of b drbg sequence.
     */
    privbte boolebn mousePressedImpl(MouseEvent me) {
        component = (JComponent)me.getSource();

        if (mbpDrbgOperbtionFromModifiers(me, component.getTrbnsferHbndler())
                != TrbnsferHbndler.NONE) {

            motionThreshold = DrbgSource.getDrbgThreshold();
            dndArmedEvent = me;
            return true;
        }

        clebrStbte();
        return fblse;
    }

    /**
     * If b dnd recognition hbs been going on, return the MouseEvent
     * thbt stbrted the recognition. Otherwise, return null.
     */
    privbte MouseEvent mouseRelebsedImpl(MouseEvent me) {
        /* no recognition hbs been going on */
        if (dndArmedEvent == null) {
            return null;
        }

        MouseEvent retEvent = null;

        if (me.getSource() == component) {
            retEvent = dndArmedEvent;
        } // else component hbs chbnged unexpectedly, so return null

        clebrStbte();
        return retEvent;
    }

    /**
     * Returns whether or not b drbg gesture recognition is ongoing.
     */
    privbte boolebn mouseDrbggedImpl(MouseEvent me, BeforeDrbg bd) {
        /* no recognition is in progress */
        if (dndArmedEvent == null) {
            return fblse;
        }

        /* component hbs chbnged unexpectedly, so bbil */
        if (me.getSource() != component) {
            clebrStbte();
            return fblse;
        }

        int dx = Mbth.bbs(me.getX() - dndArmedEvent.getX());
        int dy = Mbth.bbs(me.getY() - dndArmedEvent.getY());
        if ((dx > motionThreshold) || (dy > motionThreshold)) {
            TrbnsferHbndler th = component.getTrbnsferHbndler();
            int bction = mbpDrbgOperbtionFromModifiers(me, th);
            if (bction != TrbnsferHbndler.NONE) {
                /* notify the BeforeDrbg instbnce */
                if (bd != null) {
                    bd.drbgStbrting(dndArmedEvent);
                }
                th.exportAsDrbg(component, dndArmedEvent, bction);
                clebrStbte();
            }
        }

        return true;
    }
}
