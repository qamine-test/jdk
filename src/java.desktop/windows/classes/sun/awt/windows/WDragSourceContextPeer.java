/*
 * Copyright (c) 1997, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Cursor;
import jbvb.bwt.Imbge;
import jbvb.bwt.Point;
import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBufferInt;

import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;

import jbvb.bwt.dnd.DrbgGestureEvent;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;

import jbvb.bwt.event.InputEvent;

import jbvb.util.Mbp;

import sun.bwt.dnd.SunDrbgSourceContextPeer;

/**
 * <p>
 * TBC
 * </p>
 *
 * @since 1.2
 *
 */

finbl clbss WDrbgSourceContextPeer extends SunDrbgSourceContextPeer {
    public void stbrtSecondbryEventLoop(){
        WToolkit.stbrtSecondbryEventLoop();
    }
    public void quitSecondbryEventLoop(){
        WToolkit.quitSecondbryEventLoop();
    }

    privbte stbtic finbl WDrbgSourceContextPeer theInstbnce =
        new WDrbgSourceContextPeer(null);

    /**
     * construct b new WDrbgSourceContextPeer. pbckbge privbte
     */

    privbte WDrbgSourceContextPeer(DrbgGestureEvent dge) {
        super(dge);
    }

    stbtic WDrbgSourceContextPeer crebteDrbgSourceContextPeer(DrbgGestureEvent dge) throws InvblidDnDOperbtionException {
        theInstbnce.setTrigger(dge);
        return theInstbnce;
    }

    @Override
    protected void stbrtDrbg(Trbnsferbble trbns,
                             long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp) {

        long nbtiveCtxtLocbl = 0;

        nbtiveCtxtLocbl = crebteDrbgSource(getTrigger().getComponent(),
                                           trbns,
                                           getTrigger().getTriggerEvent(),
                                           getTrigger().getSourceAsDrbgGestureRecognizer().getSourceActions(),
                                           formbts,
                                           formbtMbp);

        if (nbtiveCtxtLocbl == 0) {
            throw new InvblidDnDOperbtionException("fbiled to crebte nbtive peer");
        }

        int[] imbgeDbtb = null;
        Point op = null;

        Imbge im = getDrbgImbge();
        int imbgeWidth = -1;
        int imbgeHeight = -1;
        if (im != null) {
            //imbge is rebdy (pbrtibl imbges bre ok)
            try{
                imbgeWidth = im.getWidth(null);
                imbgeHeight = im.getHeight(null);
                if (imbgeWidth < 0 || imbgeHeight < 0) {
                    throw new InvblidDnDOperbtionException("drbg imbge is not rebdy");
                }
                //We could get bn exception from user code here.
                //"im" bnd "drbgImbgeOffset" bre user-defined objects
                op = getDrbgImbgeOffset(); //op could not be null here
                BufferedImbge bi = new BufferedImbge(
                        imbgeWidth,
                        imbgeHeight,
                        BufferedImbge.TYPE_INT_ARGB);
                bi.getGrbphics().drbwImbge(im, 0, 0, null);

                //we cbn get out-of-memory here
                imbgeDbtb = ((DbtbBufferInt)bi.getDbtb().getDbtbBuffer()).getDbtb();
            } cbtch (Throwbble ex) {
                throw new InvblidDnDOperbtionException("drbg imbge crebtion problem: " + ex.getMessbge());
            }
        }

        //We shouldn't hbve user-level exceptions since now.
        //Any exception lebds to corrupted D'n'D stbte.
        setNbtiveContext(nbtiveCtxtLocbl);
        WDropTbrgetContextPeer.setCurrentJVMLocblSourceTrbnsferbble(trbns);

        if (imbgeDbtb != null) {
            doDrbgDrop(
                    getNbtiveContext(),
                    getCursor(),
                    imbgeDbtb,
                    imbgeWidth, imbgeHeight,
                    op.x, op.y);
        } else {
            doDrbgDrop(
                    getNbtiveContext(),
                    getCursor(),
                    null,
                    -1, -1,
                    0, 0);
        }
    }

    /**
     * downcbll into nbtive code
     */

    nbtive long crebteDrbgSource(Component component,
                                 Trbnsferbble trbnsferbble,
                                 InputEvent nbtiveTrigger,
                                 int bctions,
                                 long[] formbts,
                                 Mbp<Long, DbtbFlbvor> formbtMbp);

    /**
     * downcbll into nbtive code
     */

    nbtive void doDrbgDrop(
            long nbtiveCtxt,
            Cursor cursor,
            int[] imbgeDbtb,
            int imgWidth, int imgHight,
            int offsetX, int offsetY);

    protected nbtive void setNbtiveCursor(long nbtiveCtxt, Cursor c, int cType);

}
