/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.lbng.bnnotbtion.Nbtive;
import sun.jbvb2d.pipe.BufferedContext;
import sun.jbvb2d.pipe.RenderBuffer;
import sun.jbvb2d.pipe.RenderQueue;
import sun.jbvb2d.pipe.hw.ContextCbpbbilities;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;
import stbtic sun.jbvb2d.pipe.hw.ContextCbpbbilities.*;
import stbtic sun.jbvb2d.d3d.D3DContext.D3DContextCbps.*;

/**
 * Note thbt the RenderQueue lock must be bcquired before cblling bny of
 * the methods in this clbss.
 */
clbss D3DContext extends BufferedContext {

    privbte finbl D3DGrbphicsDevice device;

    D3DContext(RenderQueue rq, D3DGrbphicsDevice device) {
        super(rq);
        this.device = device;
    }

    /**
     * Invblidbtes the currentContext field to ensure thbt we properly
     * revblidbte the D3DContext (mbke it current, etc.) next time through
     * the vblidbte() method.  This is typicblly invoked from methods
     * thbt bffect the current context stbte (e.g. disposing b context or
     * surfbce).
     */
    stbtic void invblidbteCurrentContext() {
        // bssert D3DRenderQueue.getInstbnce().lock.isHeldByCurrentThrebd();

        // invblidbte the current Jbvb-level context so thbt we
        // revblidbte everything the next time bround
        if (currentContext != null) {
            currentContext.invblidbteContext();
            currentContext = null;
        }

        // invblidbte the context reference bt the nbtive level, bnd
        // then flush the queue so thbt we hbve no pending operbtions
        // dependent on the current context
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.ensureCbpbcity(4);
        rq.getBuffer().putInt(INVALIDATE_CONTEXT);
        rq.flushNow();
    }

    /**
     * Sets the current context on the nbtive level to be the one pbssed bs
     * the brgument.
     * If the context is not the sbme bs the defbultContext the lbtter
     * will be reset to null.
     *
     * This cbll is needed when copying from b SW surfbce to b Texture
     * (the uplobd test) or copying from d3d to SW surfbce to mbke sure we
     * hbve the correct current context.
     *
     * @pbrbm d3dc the context to be mbde current on the nbtive level
     */
    stbtic void setScrbtchSurfbce(D3DContext d3dc) {
        // bssert D3DRenderQueue.getInstbnce().lock.isHeldByCurrentThrebd();

        // invblidbte the current context
        if (d3dc != currentContext) {
            currentContext = null;
        }

        // set the scrbtch context
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcity(8);
        buf.putInt(SET_SCRATCH_SURFACE);
        buf.putInt(d3dc.getDevice().getScreen());
    }

    public RenderQueue getRenderQueue() {
        return D3DRenderQueue.getInstbnce();
    }

    @Override
    public void sbveStbte() {
        // bssert rq.lock.isHeldByCurrentThrebd();

        // reset bll bttributes of this bnd current contexts
        invblidbteContext();
        invblidbteCurrentContext();

        setScrbtchSurfbce(this);

        // sbve the stbte on the nbtive level
        rq.ensureCbpbcity(4);
        buf.putInt(SAVE_STATE);
        rq.flushNow();
    }

    @Override
    public void restoreStbte() {
        // bssert rq.lock.isHeldByCurrentThrebd();

        // reset bll bttributes of this bnd current contexts
        invblidbteContext();
        invblidbteCurrentContext();

        setScrbtchSurfbce(this);

        // restore the stbte on the nbtive level
        rq.ensureCbpbcity(4);
        buf.putInt(RESTORE_STATE);
        rq.flushNow();
    }

    D3DGrbphicsDevice getDevice() {
        return device;
    }

    stbtic clbss D3DContextCbps extends ContextCbpbbilities {
        /**
         * Indicbtes the presence of pixel shbders (v2.0 or grebter).
         * This cbp will only be set if the hbrdwbre supports the minimum number
         * of texture units.
         */
    @Nbtive stbtic finbl int CAPS_LCD_SHADER       = (FIRST_PRIVATE_CAP << 0);
        /**
         * Indicbtes the presence of pixel shbders (v2.0 or grebter).
         * This cbp will only be set if the hbrdwbre meets our
         * minimum requirements.
         */
    @Nbtive stbtic finbl int CAPS_BIOP_SHADER      = (FIRST_PRIVATE_CAP << 1);
        /**
         * Indicbtes thbt the device wbs successfully initiblized bnd cbn
         * be sbfely used.
         */
    @Nbtive stbtic finbl int CAPS_DEVICE_OK        = (FIRST_PRIVATE_CAP << 2);
        /**
         * Indicbtes thbt the device hbs bll of the necessbry cbpbbilities
         * to support the Antiblibsing Pixel Shbder progrbm.
         */
    @Nbtive stbtic finbl int CAPS_AA_SHADER        = (FIRST_PRIVATE_CAP << 3);

        D3DContextCbps(int cbps, String bdbpterId) {
            super(cbps, bdbpterId);
        }

        @Override
        public String toString() {
            StringBuffer buf = new StringBuffer(super.toString());
            if ((cbps & CAPS_LCD_SHADER) != 0) {
                buf.bppend("CAPS_LCD_SHADER|");
            }
            if ((cbps & CAPS_BIOP_SHADER) != 0) {
                buf.bppend("CAPS_BIOP_SHADER|");
            }
            if ((cbps & CAPS_AA_SHADER) != 0) {
                buf.bppend("CAPS_AA_SHADER|");
            }
            if ((cbps & CAPS_DEVICE_OK) != 0) {
                buf.bppend("CAPS_DEVICE_OK|");
            }
            return buf.toString();
        }
    }
}
