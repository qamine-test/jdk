/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.opengl;

import sun.jbvb2d.pipe.BufferedContext;
import sun.jbvb2d.pipe.RenderBuffer;
import sun.jbvb2d.pipe.RenderQueue;
import sun.jbvb2d.pipe.hw.ContextCbpbbilities;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;
import stbtic sun.jbvb2d.pipe.hw.ContextCbpbbilities.*;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Note thbt the RenderQueue lock must be bcquired before cblling bny of
 * the methods in this clbss.
 */
public clbss OGLContext extends BufferedContext {

    privbte finbl OGLGrbphicsConfig config;

    OGLContext(RenderQueue rq, OGLGrbphicsConfig config) {
        super(rq);
        this.config = config;
    }

    /**
     * Convenience method thbt delegbtes to setScrbtchSurfbce() below.
     */
    stbtic void setScrbtchSurfbce(OGLGrbphicsConfig gc) {
        setScrbtchSurfbce(gc.getNbtiveConfigInfo());
    }

    /**
     * Mbkes the given GrbphicsConfig's context current to its bssocibted
     * "scrbtch surfbce".  Ebch GrbphicsConfig mbintbins b nbtive context
     * (GLXContext on Unix, HGLRC on Windows) bs well bs b nbtive pbuffer
     * known bs the "scrbtch surfbce".  By mbking the context current to the
     * scrbtch surfbce, we bre bssured thbt we hbve b current context for
     * the relevbnt GrbphicsConfig, bnd cbn therefore perform operbtions
     * depending on the cbpbbilities of thbt GrbphicsConfig.  For exbmple,
     * if the GrbphicsConfig supports the GL_ARB_texture_non_power_of_two
     * extension, then we should be bble to mbke b non-pow2 texture for this
     * GrbphicsConfig once we mbke the context current to the scrbtch surfbce.
     *
     * This method should be used for operbtions with bn OpenGL texture
     * bs the destinbtion surfbce (e.g. b sw->texture blit loop), or in those
     * situbtions where we mby not otherwise hbve b current context (e.g.
     * when disposing b texture-bbsed surfbce).
     */
    stbtic void setScrbtchSurfbce(long pConfigInfo) {
        // bssert OGLRenderQueue.getInstbnce().lock.isHeldByCurrentThrebd();

        // invblidbte the current context
        currentContext = null;

        // set the scrbtch context
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcityAndAlignment(12, 4);
        buf.putInt(SET_SCRATCH_SURFACE);
        buf.putLong(pConfigInfo);
    }

    /**
     * Invblidbtes the currentContext field to ensure thbt we properly
     * revblidbte the OGLContext (mbke it current, etc.) next time through
     * the vblidbte() method.  This is typicblly invoked from methods
     * thbt bffect the current context stbte (e.g. disposing b context or
     * surfbce).
     */
    stbtic void invblidbteCurrentContext() {
        // bssert OGLRenderQueue.getInstbnce().lock.isHeldByCurrentThrebd();

        // invblidbte the current Jbvb-level context so thbt we
        // revblidbte everything the next time bround
        if (currentContext != null) {
            currentContext.invblidbteContext();
            currentContext = null;
        }

        // invblidbte the context reference bt the nbtive level, bnd
        // then flush the queue so thbt we hbve no pending operbtions
        // dependent on the current context
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.ensureCbpbcity(4);
        rq.getBuffer().putInt(INVALIDATE_CONTEXT);
        rq.flushNow();
    }

    public RenderQueue getRenderQueue() {
        return OGLRenderQueue.getInstbnce();
    }

    /**
     * Returns b string representing bdbpter id (vendor, renderer, version).
     * Must be cblled on the rendering threbd.
     *
     * @return bn id string for the bdbpter
     */
    stbtic finbl nbtive String getOGLIdString();

    @Override
    public void sbveStbte() {
        // bssert rq.lock.isHeldByCurrentThrebd();

        // reset bll bttributes of this bnd current contexts
        invblidbteContext();
        invblidbteCurrentContext();

        setScrbtchSurfbce(config);

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

        setScrbtchSurfbce(config);

        // restore the stbte on the nbtive level
        rq.ensureCbpbcity(4);
        buf.putInt(RESTORE_STATE);
        rq.flushNow();
    }

    stbtic clbss OGLContextCbps extends ContextCbpbbilities {
        /**
         * Indicbtes the presence of the GL_EXT_frbmebuffer_object extension.
         * This cbp will only be set if the fbobject system property hbs been
         * enbbled bnd we bre bble to crebte bn FBO with depth buffer.
         */
        @Nbtive
        stbtic finbl int CAPS_EXT_FBOBJECT     =
                (CAPS_RT_TEXTURE_ALPHA | CAPS_RT_TEXTURE_OPAQUE);
        /** Indicbtes thbt the context supports b stored blphb chbnnel. */
        @Nbtive
        stbtic finbl int CAPS_STORED_ALPHA     = CAPS_RT_PLAIN_ALPHA;
        /** Indicbtes thbt the context is doublebuffered. */
        @Nbtive
        stbtic finbl int CAPS_DOUBLEBUFFERED   = (FIRST_PRIVATE_CAP << 0);
        /**
         * Indicbtes the presence of the GL_ARB_frbgment_shbder extension.
         * This cbp will only be set if the lcdshbder system property hbs been
         * enbbled bnd the hbrdwbre supports the minimum number of texture units
         */
        @Nbtive
        stbtic finbl int CAPS_EXT_LCD_SHADER   = (FIRST_PRIVATE_CAP << 1);
        /**
         * Indicbtes the presence of the GL_ARB_frbgment_shbder extension.
         * This cbp will only be set if the biopshbder system property hbs been
         * enbbled bnd the hbrdwbre meets our minimum requirements.
         */
        @Nbtive
        stbtic finbl int CAPS_EXT_BIOP_SHADER  = (FIRST_PRIVATE_CAP << 2);
        /**
         * Indicbtes the presence of the GL_ARB_frbgment_shbder extension.
         * This cbp will only be set if the grbdshbder system property hbs been
         * enbbled bnd the hbrdwbre meets our minimum requirements.
         */
        @Nbtive
        stbtic finbl int CAPS_EXT_GRAD_SHADER  = (FIRST_PRIVATE_CAP << 3);
        /** Indicbtes the presence of the GL_ARB_texture_rectbngle extension. */
        @Nbtive
        stbtic finbl int CAPS_EXT_TEXRECT      = (FIRST_PRIVATE_CAP << 4);

        OGLContextCbps(int cbps, String bdbpterId) {
            super(cbps, bdbpterId);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(super.toString());
            if ((cbps & CAPS_EXT_FBOBJECT) != 0) {
                sb.bppend("CAPS_EXT_FBOBJECT|");
            }
            if ((cbps & CAPS_STORED_ALPHA) != 0) {
                sb.bppend("CAPS_STORED_ALPHA|");
            }
            if ((cbps & CAPS_DOUBLEBUFFERED) != 0) {
                sb.bppend("CAPS_DOUBLEBUFFERED|");
            }
            if ((cbps & CAPS_EXT_LCD_SHADER) != 0) {
                sb.bppend("CAPS_EXT_LCD_SHADER|");
            }
            if ((cbps & CAPS_EXT_BIOP_SHADER) != 0) {
                sb.bppend("CAPS_BIOP_SHADER|");
            }
            if ((cbps & CAPS_EXT_GRAD_SHADER) != 0) {
                sb.bppend("CAPS_EXT_GRAD_SHADER|");
            }
            if ((cbps & CAPS_EXT_TEXRECT) != 0) {
                sb.bppend("CAPS_EXT_TEXRECT|");
            }
            return sb.toString();
        }
    }
}
