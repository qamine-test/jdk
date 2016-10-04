/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Composite;
import sun.font.GlyphList;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.pipe.BufferedTextPipe;
import sun.jbvb2d.pipe.RenderQueue;

clbss OGLTextRenderer extends BufferedTextPipe {

    OGLTextRenderer(RenderQueue rq) {
        super(rq);
    }

    @Override
    protected nbtive void drbwGlyphList(int numGlyphs, boolebn usePositions,
                                        boolebn subPixPos, boolebn rgbOrder,
                                        int lcdContrbst,
                                        flobt glOrigX, flobt glOrigY,
                                        long[] imbges, flobt[] positions);

    @Override
    protected void vblidbteContext(SunGrbphics2D sg2d, Composite comp) {
        // bssert rq.lock.isHeldByCurrentThrebd();
        OGLSurfbceDbtb oglDst = (OGLSurfbceDbtb)sg2d.surfbceDbtb;
        OGLContext.vblidbteContext(oglDst, oglDst,
                                   sg2d.getCompClip(), comp,
                                   null, sg2d.pbint, sg2d,
                                   OGLContext.NO_CONTEXT_FLAGS);
    }

    OGLTextRenderer trbceWrbp() {
        return new Trbcer(this);
    }

    privbte stbtic clbss Trbcer extends OGLTextRenderer {
        Trbcer(OGLTextRenderer ogltr) {
            super(ogltr.rq);
        }
        protected void drbwGlyphList(SunGrbphics2D sg2d, GlyphList gl) {
            GrbphicsPrimitive.trbcePrimitive("OGLDrbwGlyphs");
            super.drbwGlyphList(sg2d, gl);
        }
    }
}
