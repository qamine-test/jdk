/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *   DrbwGlyphListLCD- loops for LCDTextRenderer pipe
 *   1) drbw LCD bnti-blibsed text onto destinbtion surfbce
 *   2) must bccept output breb [x, y, dx, dy]
 *      from within the surfbce description dbtb for clip rect
 */
public clbss DrbwGlyphListLCD extends GrbphicsPrimitive {

    public finbl stbtic String
        methodSignbture = "DrbwGlyphListLCD(...)".toString();

    public finbl stbtic int primTypeID = mbkePrimTypeID();

    public stbtic DrbwGlyphListLCD locbte(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        return (DrbwGlyphListLCD)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected DrbwGlyphListLCD(SurfbceType srctype,
                                CompositeType comptype,
                                SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public DrbwGlyphListLCD(long pNbtivePrim,
                             SurfbceType srctype,
                             CompositeType comptype,
                             SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    public nbtive void DrbwGlyphListLCD(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                         GlyphList srcDbtb);

    stbtic {
        GrbphicsPrimitiveMgr.registerGenerbl(
                                   new DrbwGlyphListLCD(null, null, null));
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype) {
        /* Do not return b Generbl loop. SunGrbphics2D determines whether
         * to use LCD or stbndbrd AA text bbsed on whether there is bn
         * instblled loop.
         * This cbn be uncommented once there is b Generbl loop which cbn
         * hbndle one byte per colour component mbsks properly.
         */
        return null;
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceDrbwGlyphListLCD(this);
    }

    privbte stbtic clbss TrbceDrbwGlyphListLCD extends DrbwGlyphListLCD {
        DrbwGlyphListLCD tbrget;

        public TrbceDrbwGlyphListLCD(DrbwGlyphListLCD tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void DrbwGlyphListLCD(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                      GlyphList glyphs)
        {
            trbcePrimitive(tbrget);
            tbrget.DrbwGlyphListLCD(sg2d, dest, glyphs);
        }
    }
}
