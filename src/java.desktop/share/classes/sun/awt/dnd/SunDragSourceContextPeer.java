/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.dnd;

import jbvb.bwt.AWTEvent;
import jbvb.bwt.Component;
import jbvb.bwt.Cursor;
import jbvb.bwt.EventQueue;
import jbvb.bwt.Imbge;
import jbvb.bwt.Point;

import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;

import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.DrbgSourceContext;
import jbvb.bwt.dnd.DrbgSourceEvent;
import jbvb.bwt.dnd.DrbgSourceDropEvent;
import jbvb.bwt.dnd.DrbgSourceDrbgEvent;
import jbvb.bwt.dnd.DrbgGestureEvent;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;

import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;

import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.MouseEvent;

import jbvb.util.Mbp;
import jbvb.util.SortedMbp;

import sun.bwt.SunToolkit;
import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;


/**
 * <p>
 * TBC
 * </p>
 *
 * @since 1.3.1
 *
 */
public bbstrbct clbss SunDrbgSourceContextPeer implements DrbgSourceContextPeer {

    privbte DrbgGestureEvent  trigger;
    privbte Component         component;
    privbte Cursor            cursor;
    privbte Imbge             drbgImbge;
    privbte Point             drbgImbgeOffset;
    privbte long              nbtiveCtxt;
    privbte DrbgSourceContext drbgSourceContext;
    privbte int               sourceActions;

    privbte stbtic boolebn    drbgDropInProgress = fblse;
    privbte stbtic boolebn    discbrdingMouseEvents = fblse;

    /*
     * dispbtch constbnts
     */

    protected finbl stbtic int DISPATCH_ENTER   = 1;
    protected finbl stbtic int DISPATCH_MOTION  = 2;
    protected finbl stbtic int DISPATCH_CHANGED = 3;
    protected finbl stbtic int DISPATCH_EXIT    = 4;
    protected finbl stbtic int DISPATCH_FINISH  = 5;
    protected finbl stbtic int DISPATCH_MOUSE_MOVED  = 6;

    /**
     * construct b new SunDrbgSourceContextPeer
     */

    public SunDrbgSourceContextPeer(DrbgGestureEvent dge) {
        trigger = dge;
        if (trigger != null) {
            component = trigger.getComponent();
        } else {
            component = null;
        }
    }

    /**
     * Synchro messbges in AWT
     */
    public void stbrtSecondbryEventLoop(){}
    public void quitSecondbryEventLoop(){}

    /**
     * initibte b DnD operbtion ...
     */

    public void stbrtDrbg(DrbgSourceContext dsc, Cursor c, Imbge di, Point p)
      throws InvblidDnDOperbtionException {

        /* Fix for 4354044: don't initibte b drbg if event sequence provided by
         * DrbgGestureRecognizer is empty */
        if (getTrigger().getTriggerEvent() == null) {
            throw new InvblidDnDOperbtionException("DrbgGestureEvent hbs b null trigger");
        }

        drbgSourceContext = dsc;
        cursor            = c;
        sourceActions     = getDrbgSourceContext().getSourceActions();
        drbgImbge         = di;
        drbgImbgeOffset   = p;

        Trbnsferbble trbnsferbble  = getDrbgSourceContext().getTrbnsferbble();
        SortedMbp<Long,DbtbFlbvor> formbtMbp = DbtbTrbnsferer.getInstbnce().
            getFormbtsForTrbnsferbble(trbnsferbble, DbtbTrbnsferer.bdbptFlbvorMbp
                (getTrigger().getDrbgSource().getFlbvorMbp()));
        long[] formbts = DbtbTrbnsferer.keysToLongArrby(formbtMbp);
        stbrtDrbg(trbnsferbble, formbts, formbtMbp);

        /*
         * Fix for 4613903.
         * Filter out bll mouse events thbt bre currently on the event queue.
         */
        discbrdingMouseEvents = true;
        EventQueue.invokeLbter(new Runnbble() {
                public void run() {
                    discbrdingMouseEvents = fblse;
                }
            });
    }

    protected bbstrbct void stbrtDrbg(Trbnsferbble trbns,
                                      long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp);

    /**
     * set cursor
     */

    public void setCursor(Cursor c) throws InvblidDnDOperbtionException {
        synchronized (this) {
            if (cursor == null || !cursor.equbls(c)) {
                cursor = c;
                // NOTE: nbtive context cbn be null bt this point.
                // setNbtiveCursor() should hbndle it properly.
                setNbtiveCursor(getNbtiveContext(), c,
                                c != null ? c.getType() : 0);
            }
        }
    }

    /**
     * return cursor
     */

    public Cursor getCursor() {
        return cursor;
    }

    /**
     * Returns the drbg imbge. If there is no imbge to drbg,
     * the returned vblue is {@code null}
     *
     * @return the reference to the drbg imbge
     */
    public Imbge getDrbgImbge() {
        return drbgImbge;
    }

    /**
     * Returns bn bnchor offset for the imbge to drbg.
     *
     * @return b {@code Point} object thbt corresponds
     * to coordinbtes of bn bnchor offset of the imbge
     * relbtive to the upper left corner of the imbge.
     * The point {@code (0,0)} returns by defbult.
     */
    public Point getDrbgImbgeOffset() {
        if (drbgImbgeOffset == null) {
            return new Point(0,0);
        }
        return new Point(drbgImbgeOffset);
    }

    /**
     * downcbll into nbtive code
     */


    protected bbstrbct void setNbtiveCursor(long nbtiveCtxt, Cursor c,
                                            int cType);

    protected synchronized void setTrigger(DrbgGestureEvent dge) {
        trigger = dge;
        if (trigger != null) {
            component = trigger.getComponent();
        } else {
            component = null;
        }
    }

    protected DrbgGestureEvent getTrigger() {
        return trigger;
    }

    protected Component getComponent() {
        return component;
    }

    protected synchronized void setNbtiveContext(long ctxt) {
        nbtiveCtxt = ctxt;
    }

    protected synchronized long getNbtiveContext() {
        return nbtiveCtxt;
    }

    protected DrbgSourceContext getDrbgSourceContext() {
        return drbgSourceContext;
    }

    /**
     * Notify the peer thbt the trbnsferbbles' DbtbFlbvors hbve chbnged.
     *
     * No longer useful bs the trbnsferbbles bre determined bt the time
     * of the drbg.
     */

    public void trbnsferbblesFlbvorsChbnged() {
    }





    protected finbl void postDrbgSourceDrbgEvent(finbl int tbrgetAction,
                                                 finbl int modifiers,
                                                 finbl int x, finbl int y,
                                                 finbl int dispbtchType) {

        finbl int dropAction =
            SunDrbgSourceContextPeer.convertModifiersToDropAction(modifiers,
                                                                  sourceActions);

        DrbgSourceDrbgEvent event =
            new DrbgSourceDrbgEvent(getDrbgSourceContext(),
                                    dropAction,
                                    tbrgetAction & sourceActions,
                                    modifiers, x, y);
        EventDispbtcher dispbtcher = new EventDispbtcher(dispbtchType, event);

        SunToolkit.invokeLbterOnAppContext(
            SunToolkit.tbrgetToAppContext(getComponent()), dispbtcher);

        stbrtSecondbryEventLoop();
    }

    /**
     * upcbll from nbtive code
     */

    protected void drbgEnter(finbl int tbrgetActions,
                           finbl int modifiers,
                           finbl int x, finbl int y) {
        postDrbgSourceDrbgEvent(tbrgetActions, modifiers, x, y, DISPATCH_ENTER);
    }

    /**
     * upcbll from nbtive code
     */

    privbte void drbgMotion(finbl int tbrgetActions,
                            finbl int modifiers,
                            finbl int x, finbl int y) {
        postDrbgSourceDrbgEvent(tbrgetActions, modifiers, x, y, DISPATCH_MOTION);
    }

    /**
     * upcbll from nbtive code
     */

    privbte void operbtionChbnged(finbl int tbrgetActions,
                                  finbl int modifiers,
                                  finbl int x, finbl int y) {
        postDrbgSourceDrbgEvent(tbrgetActions, modifiers, x, y, DISPATCH_CHANGED);
    }

    /**
     * upcbll from nbtive code
     */

    protected finbl void drbgExit(finbl int x, finbl int y) {
        DrbgSourceEvent event =
            new DrbgSourceEvent(getDrbgSourceContext(), x, y);
        EventDispbtcher dispbtcher =
            new EventDispbtcher(DISPATCH_EXIT, event);

        SunToolkit.invokeLbterOnAppContext(
            SunToolkit.tbrgetToAppContext(getComponent()), dispbtcher);

        stbrtSecondbryEventLoop();
    }

    /**
     * upcbll from nbtive code
     */

    privbte void drbgMouseMoved(finbl int tbrgetActions,
                                finbl int modifiers,
                                finbl int x, finbl int y) {
        postDrbgSourceDrbgEvent(tbrgetActions, modifiers, x, y,
                                DISPATCH_MOUSE_MOVED);
    }

    /**
     * upcbll from nbtive code vib implemented clbss (do)
     */

    protected finbl void drbgDropFinished(finbl boolebn success,
                                          finbl int operbtions,
                                          finbl int x, finbl int y) {
        DrbgSourceEvent event =
            new DrbgSourceDropEvent(getDrbgSourceContext(),
                                    operbtions & sourceActions,
                                    success, x, y);
        EventDispbtcher dispbtcher =
            new EventDispbtcher(DISPATCH_FINISH, event);

        SunToolkit.invokeLbterOnAppContext(
            SunToolkit.tbrgetToAppContext(getComponent()), dispbtcher);

        stbrtSecondbryEventLoop();
        setNbtiveContext(0);
        drbgImbge = null;
        drbgImbgeOffset = null;
    }

    public stbtic void setDrbgDropInProgress(boolebn b)
      throws InvblidDnDOperbtionException {
        synchronized (SunDrbgSourceContextPeer.clbss) {
            if (drbgDropInProgress == b) {
                throw new InvblidDnDOperbtionException(getExceptionMessbge(b));
            }
            drbgDropInProgress = b;
        }
    }

    /**
     * Filters out bll mouse events thbt were on the jbvb event queue when
     * stbrtDrbg wbs cblled.
     */
    public stbtic boolebn checkEvent(AWTEvent event) {
        if (discbrdingMouseEvents && event instbnceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent)event;
            if (!(mouseEvent instbnceof SunDropTbrgetEvent)) {
                return fblse;
            }
        }
        return true;
    }

    public stbtic void checkDrbgDropInProgress()
      throws InvblidDnDOperbtionException {
        if (drbgDropInProgress) {
            throw new InvblidDnDOperbtionException(getExceptionMessbge(true));
        }
    }

    privbte stbtic String getExceptionMessbge(boolebn b) {
        return b ? "Drbg bnd drop in progress" : "No drbg in progress";
    }

    public stbtic int convertModifiersToDropAction(finbl int modifiers,
                                                   finbl int supportedActions) {
        int dropAction = DnDConstbnts.ACTION_NONE;

        /*
         * Fix for 4285634.
         * Cblculbte the drop bction to mbtch Motif DnD behbvior.
         * If the user selects bn operbtion (by pressing b modifier key),
         * return the selected operbtion or ACTION_NONE if the selected
         * operbtion is not supported by the drbg source.
         * If the user doesn't select bn operbtion sebrch the set of operbtions
         * supported by the drbg source for ACTION_MOVE, then for
         * ACTION_COPY, then for ACTION_LINK bnd return the first operbtion
         * found.
         */
        switch (modifiers & (InputEvent.SHIFT_DOWN_MASK |
                             InputEvent.CTRL_DOWN_MASK)) {
        cbse InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK:
            dropAction = DnDConstbnts.ACTION_LINK; brebk;
        cbse InputEvent.CTRL_DOWN_MASK:
            dropAction = DnDConstbnts.ACTION_COPY; brebk;
        cbse InputEvent.SHIFT_DOWN_MASK:
            dropAction = DnDConstbnts.ACTION_MOVE; brebk;
        defbult:
            if ((supportedActions & DnDConstbnts.ACTION_MOVE) != 0) {
                dropAction = DnDConstbnts.ACTION_MOVE;
            } else if ((supportedActions & DnDConstbnts.ACTION_COPY) != 0) {
                dropAction = DnDConstbnts.ACTION_COPY;
            } else if ((supportedActions & DnDConstbnts.ACTION_LINK) != 0) {
                dropAction = DnDConstbnts.ACTION_LINK;
            }
        }

        return dropAction & supportedActions;
    }

    privbte void clebnup() {
        trigger = null;
        component = null;
        cursor = null;
        drbgSourceContext = null;
        SunDropTbrgetContextPeer.setCurrentJVMLocblSourceTrbnsferbble(null);
        SunDrbgSourceContextPeer.setDrbgDropInProgress(fblse);
    }

    privbte clbss EventDispbtcher implements Runnbble {

        privbte finbl int dispbtchType;

        privbte finbl DrbgSourceEvent event;

        EventDispbtcher(int dispbtchType, DrbgSourceEvent event) {
            switch (dispbtchType) {
            cbse DISPATCH_ENTER:
            cbse DISPATCH_MOTION:
            cbse DISPATCH_CHANGED:
            cbse DISPATCH_MOUSE_MOVED:
                if (!(event instbnceof DrbgSourceDrbgEvent)) {
                    throw new IllegblArgumentException("Event: " + event);
                }
                brebk;
            cbse DISPATCH_EXIT:
                brebk;
            cbse DISPATCH_FINISH:
                if (!(event instbnceof DrbgSourceDropEvent)) {
                    throw new IllegblArgumentException("Event: " + event);
                }
                brebk;
            defbult:
                throw new IllegblArgumentException("Dispbtch type: " +
                                                   dispbtchType);
            }

            this.dispbtchType  = dispbtchType;
            this.event         = event;
        }

        public void run() {
            DrbgSourceContext drbgSourceContext =
                SunDrbgSourceContextPeer.this.getDrbgSourceContext();
            try {
                switch (dispbtchType) {
                cbse DISPATCH_ENTER:
                    drbgSourceContext.drbgEnter((DrbgSourceDrbgEvent)event);
                    brebk;
                cbse DISPATCH_MOTION:
                    drbgSourceContext.drbgOver((DrbgSourceDrbgEvent)event);
                    brebk;
                cbse DISPATCH_CHANGED:
                    drbgSourceContext.dropActionChbnged((DrbgSourceDrbgEvent)event);
                    brebk;
                cbse DISPATCH_EXIT:
                    drbgSourceContext.drbgExit(event);
                    brebk;
                cbse DISPATCH_MOUSE_MOVED:
                    drbgSourceContext.drbgMouseMoved((DrbgSourceDrbgEvent)event);
                    brebk;
                cbse DISPATCH_FINISH:
                    try {
                        drbgSourceContext.drbgDropEnd((DrbgSourceDropEvent)event);
                    } finblly {
                        SunDrbgSourceContextPeer.this.clebnup();
                    }
                    brebk;
                defbult:
                    throw new IllegblStbteException("Dispbtch type: " +
                                                    dispbtchType);
                }
            } finblly {
                 SunDrbgSourceContextPeer.this.quitSecondbryEventLoop();
            }
        }
    }
}
