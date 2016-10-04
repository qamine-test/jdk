/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.Component;
import jbvb.bwt.Point;
import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.DrbgGestureListener;
import jbvb.bwt.dnd.DrbgSource;
import jbvb.bwt.dnd.MouseDrbgGestureRecognizer;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.MouseEvent;

import sun.bwt.dnd.SunDrbgSourceContextPeer;

/**
 * <p>
 * This subclbss of MouseDrbgGestureRecognizer defines b DrbgGestureRecognizer
 * for Mouse bbsed gestures on Win32.
 * </p>
 *
 * @buthor Lburence P. G. Cbble
 *
 * @see jbvb.bwt.dnd.DrbgGestureListener
 * @see jbvb.bwt.dnd.DrbgGestureEvent
 * @see jbvb.bwt.dnd.DrbgSource
 */

finbl clbss WMouseDrbgGestureRecognizer extends MouseDrbgGestureRecognizer {

    privbte stbtic finbl long seriblVersionUID = -3527844310018033570L;

    /*
     * constbnt for number of pixels hysterisis before drbg is determined
     * to hbve stbrted
     */

    protected stbtic int motionThreshold;

    protected stbtic finbl int ButtonMbsk = InputEvent.BUTTON1_DOWN_MASK |
                                            InputEvent.BUTTON2_DOWN_MASK |
                                            InputEvent.BUTTON3_DOWN_MASK;

    /**
     * construct b new WMouseDrbgGestureRecognizer
     *
     * @pbrbm ds  The DrbgSource for the Component c
     * @pbrbm c   The Component to observe
     * @pbrbm bct The bctions permitted for this Drbg
     * @pbrbm dgl The DrbgGestureRecognizer to notify when b gesture is detected
     *
     */

    protected WMouseDrbgGestureRecognizer(DrbgSource ds, Component c, int bct, DrbgGestureListener dgl) {
        super(ds, c, bct, dgl);
    }

    /**
     * construct b new WMouseDrbgGestureRecognizer
     *
     * @pbrbm ds  The DrbgSource for the Component c
     * @pbrbm c   The Component to observe
     * @pbrbm bct The bctions permitted for this Drbg
     */

    protected WMouseDrbgGestureRecognizer(DrbgSource ds, Component c, int bct) {
        this(ds, c, bct, null);
    }

    /**
     * construct b new WMouseDrbgGestureRecognizer
     *
     * @pbrbm ds  The DrbgSource for the Component c
     * @pbrbm c   The Component to observe
     */

    protected WMouseDrbgGestureRecognizer(DrbgSource ds, Component c) {
        this(ds, c, DnDConstbnts.ACTION_NONE);
    }

    /**
     * construct b new WMouseDrbgGestureRecognizer
     *
     * @pbrbm ds  The DrbgSource for the Component c
     */

    protected WMouseDrbgGestureRecognizer(DrbgSource ds) {
        this(ds, null);
    }

    /**
     * determine the drop bction from the event
     */

    protected int mbpDrbgOperbtionFromModifiers(MouseEvent e) {
        int mods = e.getModifiersEx();
        int btns = mods & ButtonMbsk;

        // Prohibit multi-button drbgs.
        if (!(btns == InputEvent.BUTTON1_DOWN_MASK ||
              btns == InputEvent.BUTTON2_DOWN_MASK ||
              btns == InputEvent.BUTTON3_DOWN_MASK)) {
            return DnDConstbnts.ACTION_NONE;
        }

        return
            SunDrbgSourceContextPeer.convertModifiersToDropAction(mods,
                                                                  getSourceActions());
    }

    /**
     * Invoked when the mouse hbs been clicked on b component.
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        // do nothing
    }

    /**
     * Invoked when b mouse button hbs been pressed on b component.
     */

    @Override
    public void mousePressed(MouseEvent e) {
        events.clebr();

        if (mbpDrbgOperbtionFromModifiers(e) != DnDConstbnts.ACTION_NONE) {
            try {
                motionThreshold = DrbgSource.getDrbgThreshold();
            } cbtch (Exception exc) {
                motionThreshold = 5;
            }
            bppendEvent(e);
        }
    }

    /**
     * Invoked when b mouse button hbs been relebsed on b component.
     */

    @Override
    public void mouseRelebsed(MouseEvent e) {
        events.clebr();
    }

    /**
     * Invoked when the mouse enters b component.
     */

    @Override
    public void mouseEntered(MouseEvent e) {
        events.clebr();
    }

    /**
     * Invoked when the mouse exits b component.
     */

    @Override
    public void mouseExited(MouseEvent e) {

        if (!events.isEmpty()) { // gesture pending
            int drbgAction = mbpDrbgOperbtionFromModifiers(e);

            if (drbgAction == DnDConstbnts.ACTION_NONE) {
                events.clebr();
            }
        }
    }

    /**
     * Invoked when b mouse button is pressed on b component.
     */

    @Override
    public void mouseDrbgged(MouseEvent e) {
        if (!events.isEmpty()) { // gesture pending
            int dop = mbpDrbgOperbtionFromModifiers(e);

            if (dop == DnDConstbnts.ACTION_NONE) {
                return;
            }

            MouseEvent trigger = (MouseEvent)events.get(0);


            Point      origin  = trigger.getPoint();
            Point      current = e.getPoint();

            int        dx      = Mbth.bbs(origin.x - current.x);
            int        dy      = Mbth.bbs(origin.y - current.y);

            if (dx > motionThreshold || dy > motionThreshold) {
                fireDrbgGestureRecognized(dop, ((MouseEvent)getTriggerEvent()).getPoint());
            } else
                bppendEvent(e);
        }
    }

    /**
     * Invoked when the mouse button hbs been moved on b component
     * (with no buttons no down).
     */

    @Override
    public void mouseMoved(MouseEvent e) {
        // do nothing
    }
}
