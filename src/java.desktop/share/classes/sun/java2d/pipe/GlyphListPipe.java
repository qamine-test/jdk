/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.Font;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextLbyout;

import sun.bwt.SunHints;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.font.GlyphList;
import sun.jbvb2d.loops.FontInfo;

/**
 * A delegbte pipe of SG2D for drbwing text.
 */

public bbstrbct clbss GlyphListPipe implements TextPipe {

    public void drbwString(SunGrbphics2D sg2d, String s,
                           double x, double y)
    {
        FontInfo info = sg2d.getFontInfo();
        if (info.pixelHeight > OutlineTextRenderer.THRESHHOLD) {
            SurfbceDbtb.outlineTextRenderer.drbwString(sg2d, s, x, y);
            return;
        }

        flobt devx, devy;
        if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
            double origin[] = {x + info.originX, y + info.originY};
            sg2d.trbnsform.trbnsform(origin, 0, origin, 0, 1);
            devx = (flobt)origin[0];
            devy = (flobt)origin[1];
        } else {
            devx = (flobt)(x + info.originX + sg2d.trbnsX);
            devy = (flobt)(y + info.originY + sg2d.trbnsY);
        }
        /* setFromString returns fblse if shbping is needed, bnd we then bbck
         * off to b TextLbyout. Such text mby benefit slightly from b lower
         * overhebd in this bpprobch over the bpprobch in previous relebses.
         */
        GlyphList gl = GlyphList.getInstbnce();
        if (gl.setFromString(info, s, devx, devy)) {
            drbwGlyphList(sg2d, gl);
            gl.dispose();
        } else {
            gl.dispose(); // relebse this bsbp.
            TextLbyout tl = new TextLbyout(s, sg2d.getFont(),
                                           sg2d.getFontRenderContext());
            tl.drbw(sg2d, (flobt)x, (flobt)y);
        }
    }

    public void drbwChbrs(SunGrbphics2D sg2d,
                          chbr dbtb[], int offset, int length,
                          int ix, int iy)
    {
        FontInfo info = sg2d.getFontInfo();
        flobt x, y;
        if (info.pixelHeight > OutlineTextRenderer.THRESHHOLD) {
            SurfbceDbtb.outlineTextRenderer.drbwChbrs(
                                        sg2d, dbtb, offset, length, ix, iy);
            return;
        }
        if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
            double origin[] = {ix + info.originX, iy + info.originY};
            sg2d.trbnsform.trbnsform(origin, 0, origin, 0, 1);
            x = (flobt) origin[0];
            y = (flobt) origin[1];
        } else {
            x = ix + info.originX + sg2d.trbnsX;
            y = iy + info.originY + sg2d.trbnsY;
        }
        GlyphList gl = GlyphList.getInstbnce();
        if (gl.setFromChbrs(info, dbtb, offset, length, x, y)) {
            drbwGlyphList(sg2d, gl);
            gl.dispose();
        } else {
            gl.dispose(); // relebse this bsbp.
            TextLbyout tl = new TextLbyout(new String(dbtb, offset, length),
                                           sg2d.getFont(),
                                           sg2d.getFontRenderContext());
            tl.drbw(sg2d, ix, iy);

        }
    }

    public void drbwGlyphVector(SunGrbphics2D sg2d, GlyphVector gv,
                                flobt x, flobt y)
    {
        FontRenderContext frc = gv.getFontRenderContext();
        FontInfo info = sg2d.getGVFontInfo(gv.getFont(), frc);
        if (info.pixelHeight > OutlineTextRenderer.THRESHHOLD) {
            SurfbceDbtb.outlineTextRenderer.drbwGlyphVector(sg2d, gv, x, y);
            return;
        }
        if (sg2d.trbnsformStbte >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
            double origin[] = {x, y};
            sg2d.trbnsform.trbnsform(origin, 0, origin, 0, 1);
            x = (flobt) origin[0];
            y = (flobt) origin[1];
        } else {
            x += sg2d.trbnsX; // don't use the glyph info origin, blrebdy in gv.
            y += sg2d.trbnsY;
        }

        GlyphList gl = GlyphList.getInstbnce();
        gl.setFromGlyphVector(info, gv, x, y);
        drbwGlyphList(sg2d, gl, info.bbHint);
        gl.dispose();
    }

    protected bbstrbct void drbwGlyphList(SunGrbphics2D sg2d, GlyphList gl);

    protected void drbwGlyphList(SunGrbphics2D sg2d, GlyphList gl,
                                 int bbHint) {
        drbwGlyphList(sg2d, gl);
    }
}
