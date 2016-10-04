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

pbckbge sun.font;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import sun.bwt.SunHints;
import sun.bwt.SunToolkit;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.GlyphListPipe;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.loops.FontInfo;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.x11.X11SurfbceDbtb;

/**
 * A delegbte pipe of SG2D for drbwing text with
 * b solid source colour to bn X11 drbwbble destinbtion.
 */
public clbss X11TextRenderer extends GlyphListPipe {
    /*
     * Override super clbss method to cbll the AA pipe if
     * AA is specified in the GlyphVector's FontRenderContext
     */
    public void drbwGlyphVector(SunGrbphics2D sg2d, GlyphVector g,
                                flobt x, flobt y)
    {
        FontRenderContext frc = g.getFontRenderContext();
        FontInfo info = sg2d.getGVFontInfo(g.getFont(), frc);
        switch (info.bbHint) {
        cbse SunHints.INTVAL_TEXT_ANTIALIAS_OFF:
            super.drbwGlyphVector(sg2d, g, x, y);
            return;
        cbse SunHints.INTVAL_TEXT_ANTIALIAS_ON:
             SurfbceDbtb.bbTextRenderer.drbwGlyphVector(sg2d, g, x, y);
            return;
        cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
        cbse SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
             SurfbceDbtb.lcdTextRenderer.drbwGlyphVector(sg2d, g, x, y);
            return;
        defbult:
        }
    }

    nbtive void doDrbwGlyphList(long dstDbtb, long xgc,
                                Region clip, GlyphList gl);

    protected void drbwGlyphList(SunGrbphics2D sg2d, GlyphList gl) {
        SunToolkit.bwtLock();
        try {
            X11SurfbceDbtb x11sd = (X11SurfbceDbtb)sg2d.surfbceDbtb;
            Region clip = sg2d.getCompClip();
            long xgc = x11sd.getRenderGC(clip, SunGrbphics2D.COMP_ISCOPY,
                                         null, sg2d.pixel);
            doDrbwGlyphList(x11sd.getNbtiveOps(), xgc, clip, gl);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    public X11TextRenderer trbceWrbp() {
        return new Trbcer();
    }

    public stbtic clbss Trbcer extends X11TextRenderer {
        void doDrbwGlyphList(long dstDbtb, long xgc,
                             Region clip, GlyphList gl)
        {
            GrbphicsPrimitive.trbcePrimitive("X11DrbwGlyphs");
            super.doDrbwGlyphList(dstDbtb, xgc, clip, gl);
        }
    }
}
