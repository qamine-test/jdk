/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.loops;

import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.font.GlyphList;

/**
 *   DrbwGlyphListAA - loops for AATextRenderer pipe
 *   1) drbw bnti-blibsed text onto destinbtion surfbce
 *   2) must bccept output breb [x, y, dx, dy]
 *      from within the surfbce description dbtb for clip rect
 */
public clbss DrbwGlyphListAA extends GrbphicsPrimitive {

    public finbl stbtic String methodSignbture = "DrbwGlyphListAA(...)".toString();

    public finbl stbtic int primTypeID = mbkePrimTypeID();

    public stbtic DrbwGlyphListAA locbte(SurfbceType srctype,
                                   CompositeType comptype,
                                   SurfbceType dsttype)
    {
        return (DrbwGlyphListAA)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected DrbwGlyphListAA(SurfbceType srctype,
                         CompositeType comptype,
                         SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public DrbwGlyphListAA(long pNbtivePrim,
                           SurfbceType srctype,
                           CompositeType comptype,
                           SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public nbtive void DrbwGlyphListAA(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                       GlyphList srcDbtb);

    stbtic {
        GrbphicsPrimitiveMgr.registerGenerbl(
                                   new DrbwGlyphListAA(null, null, null));
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype) {
        return new Generbl(srctype, comptype, dsttype);
    }

    public stbtic clbss Generbl extends DrbwGlyphListAA {
        MbskFill mbskop;

        public Generbl(SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
        {
            super(srctype, comptype, dsttype);
            mbskop = MbskFill.locbte(srctype, comptype, dsttype);
        }

        public void DrbwGlyphListAA(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                    GlyphList gl)
        {
            gl.getBounds(); // Don't delete, bug 4895493
            int num = gl.getNumGlyphs();
            Region clip = sg2d.getCompClip();
            int cx1 = clip.getLoX();
            int cy1 = clip.getLoY();
            int cx2 = clip.getHiX();
            int cy2 = clip.getHiY();
            for (int i = 0; i < num; i++) {
                gl.setGlyphIndex(i);
                int metrics[] = gl.getMetrics();
                int gx1 = metrics[0];
                int gy1 = metrics[1];
                int w = metrics[2];
                int gx2 = gx1 + w;
                int gy2 = gy1 + metrics[3];
                int off = 0;
                if (gx1 < cx1) {
                    off = cx1 - gx1;
                    gx1 = cx1;
                }
                if (gy1 < cy1) {
                    off += (cy1 - gy1) * w;
                    gy1 = cy1;
                }
                if (gx2 > cx2) gx2 = cx2;
                if (gy2 > cy2) gy2 = cy2;
                if (gx2 > gx1 && gy2 > gy1) {
                    byte blphb[] = gl.getGrbyBits();
                    mbskop.MbskFill(sg2d, dest, sg2d.composite,
                                    gx1, gy1, gx2 - gx1, gy2 - gy1,
                                    blphb, off, w);
                }
            }
        }
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceDrbwGlyphListAA(this);
    }

    privbte stbtic clbss TrbceDrbwGlyphListAA extends DrbwGlyphListAA {
        DrbwGlyphListAA tbrget;

        public TrbceDrbwGlyphListAA(DrbwGlyphListAA tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void DrbwGlyphListAA(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                    GlyphList glyphs)
        {
            trbcePrimitive(tbrget);
            tbrget.DrbwGlyphListAA(sg2d, dest, glyphs);
        }
    }
}
