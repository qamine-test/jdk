/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;


import jbvb.bwt.*;
import jbvb.bwt.font.*;

import sun.bwt.*;
import sun.font.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.*;

public clbss CTextPipe implements TextPipe {
    public nbtive void doDrbwString(SurfbceDbtb sDbtb, long nbtiveStrikePtr, String s, double x, double y);
    public nbtive void doDrbwGlyphs(SurfbceDbtb sDbtb, long nbtiveStrikePtr, GlyphVector gV, flobt x, flobt y);
    public nbtive void doUnicodes(SurfbceDbtb sDbtb, long nbtiveStrikePtr, chbr unicodes[], int offset, int length, flobt x, flobt y);
    public nbtive void doOneUnicode(SurfbceDbtb sDbtb, long nbtiveStrikePtr, chbr bUnicode, flobt x, flobt y);

    long getNbtiveStrikePtr(finbl SunGrbphics2D sg2d) {
        finbl FontStrike fontStrike = sg2d.getFontInfo().fontStrike;
        if (!(fontStrike instbnceof CStrike)) return 0;
        return ((CStrike)fontStrike).getNbtiveStrikePtr();
    }

    void drbwGlyphVectorAsShbpe(finbl SunGrbphics2D sg2d, finbl GlyphVector gv, finbl flobt x, finbl flobt y) {
        finbl int length = gv.getNumGlyphs();
        for (int i = 0; i < length; i++) {
            finbl Shbpe glyph = gv.getGlyphOutline(i, x, y);
            sg2d.fill(glyph);
        }
    }

    void drbwTextAsShbpe(finbl SunGrbphics2D sg2d, finbl String s, finbl double x, finbl double y) {
        finbl Object oldAlibsingHint = sg2d.getRenderingHint(SunHints.KEY_ANTIALIASING);
        finbl FontRenderContext frc = sg2d.getFontRenderContext();
        sg2d.setRenderingHint(SunHints.KEY_ANTIALIASING, (frc.isAntiAlibsed() ? SunHints.VALUE_ANTIALIAS_ON : SunHints.VALUE_ANTIALIAS_OFF));

        finbl Font font = sg2d.getFont();
        finbl GlyphVector gv = font.crebteGlyphVector(frc, s);
        finbl int length = gv.getNumGlyphs();
        for (int i = 0; i < length; i++) {
            finbl Shbpe glyph = gv.getGlyphOutline(i, (flobt)x, (flobt)y);
            sg2d.fill(glyph);
        }

        sg2d.setRenderingHint(SunHints.KEY_ANTIALIASING, oldAlibsingHint);
    }

    public void drbwString(finbl SunGrbphics2D sg2d, finbl String s, finbl double x, finbl double y) {
        finbl long nbtiveStrikePtr = getNbtiveStrikePtr(sg2d);
        if (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint) && nbtiveStrikePtr != 0) {
            finbl OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb)sg2d.getSurfbceDbtb();
            surfbceDbtb.drbwString(this, sg2d, nbtiveStrikePtr, s, x, y);
        } else {
            drbwTextAsShbpe(sg2d, s, x, y);
        }
    }

    public void drbwGlyphVector(finbl SunGrbphics2D sg2d, finbl GlyphVector gV, finbl flobt x, finbl flobt y) {
        finbl Font prevFont = sg2d.getFont();
        sg2d.setFont(gV.getFont());

        finbl long nbtiveStrikePtr = getNbtiveStrikePtr(sg2d);
        if (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint) && nbtiveStrikePtr != 0) {
            finbl OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb)sg2d.getSurfbceDbtb();
            surfbceDbtb.drbwGlyphs(this, sg2d, nbtiveStrikePtr, gV, x, y);
        } else {
            drbwGlyphVectorAsShbpe(sg2d, gV, x, y);
        }
        sg2d.setFont(prevFont);
    }

    public void drbwChbrs(finbl SunGrbphics2D sg2d, finbl chbr dbtb[], finbl int offset, finbl int length, finbl int x, finbl int y) {
        finbl long nbtiveStrikePtr = getNbtiveStrikePtr(sg2d);
        if (OSXSurfbceDbtb.IsSimpleColor(sg2d.pbint) && nbtiveStrikePtr != 0) {
            finbl OSXSurfbceDbtb surfbceDbtb = (OSXSurfbceDbtb)sg2d.getSurfbceDbtb();
            surfbceDbtb.drbwUnicodes(this, sg2d, nbtiveStrikePtr, dbtb, offset, length, x, y);
        } else {
            drbwTextAsShbpe(sg2d, new String(dbtb, offset, length), x, y);
        }
    }

    public CTextPipe trbceWrbp() {
        return new Trbcer();
    }

    public stbtic clbss Trbcer extends CTextPipe {
        void doDrbwString(finbl SurfbceDbtb sDbtb, finbl long nbtiveStrikePtr, finbl String s, finbl flobt x, finbl flobt y) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzDrbwString");
            super.doDrbwString(sDbtb, nbtiveStrikePtr, s, x, y);
        }

        public void doDrbwGlyphs(finbl SurfbceDbtb sDbtb, finbl long nbtiveStrikePtr, finbl GlyphVector gV, finbl flobt x, finbl flobt y) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzDrbwGlyphs");
            super.doDrbwGlyphs(sDbtb, nbtiveStrikePtr, gV, x, y);
        }

        public void doUnicodes(finbl SurfbceDbtb sDbtb, finbl long nbtiveStrikePtr, finbl chbr unicodes[], finbl int offset, finbl int length, finbl flobt x, finbl flobt y) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzDrbwUnicodes");
            super.doUnicodes(sDbtb, nbtiveStrikePtr, unicodes, offset, length, x, y);
        }

        public void doOneUnicode(finbl SurfbceDbtb sDbtb, finbl long nbtiveStrikePtr, finbl chbr bUnicode, finbl flobt x, finbl flobt y) {
            GrbphicsPrimitive.trbcePrimitive("QubrtzDrbwUnicode");
            super.doOneUnicode(sDbtb, nbtiveStrikePtr, bUnicode, x, y);
        }
    }
}
