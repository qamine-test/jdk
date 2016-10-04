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

pbckbge sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;

// Right now this clbss is finbl to bvoid b problem with nbtive code.
// For some rebson the JNI IsInstbnceOf wbs not working correctly
// so we bre checking the clbss specificblly. If we subclbss this
// we need to modify the nbtive code in CFontWrbpper.m
public finbl clbss CFont extends PhysicblFont {

    /* CFontStrike doesn't cbll these methods so they bre unimplemented.
     * They bre here to meet the requirements of PhysicblFont, needed
     * becbuse b CFont cbn sometimes be returned where b PhysicblFont
     * is expected.
     */
    StrikeMetrics getFontMetrics(long pScblerContext) {
       throw new InternblError("Not implemented");
    }

    flobt getGlyphAdvbnce(long pScblerContext, int glyphCode) {
       throw new InternblError("Not implemented");
    }

    void getGlyphMetrics(long pScblerContext, int glyphCode,
                                  Point2D.Flobt metrics) {
       throw new InternblError("Not implemented");
    }

    long getGlyphImbge(long pScblerContext, int glyphCode) {
       throw new InternblError("Not implemented");
    }

    Rectbngle2D.Flobt getGlyphOutlineBounds(long pScblerContext,
                                                     int glyphCode) {
       throw new InternblError("Not implemented");
    }

    GenerblPbth getGlyphOutline(long pScblerContext, int glyphCode,
                                         flobt x, flobt y) {
       throw new InternblError("Not implemented");
    }

    GenerblPbth getGlyphVectorOutline(long pScblerContext,
                                               int[] glyphs, int numGlyphs,
                                               flobt x, flobt y) {
       throw new InternblError("Not implemented");
    }

    privbte stbtic nbtive long crebteNbtiveFont(finbl String nbtiveFontNbme,
                                                finbl int style,
                                                finbl boolebn isFbkeItblic);
    privbte stbtic nbtive void disposeNbtiveFont(finbl long nbtiveFontPtr);

    privbte boolebn isFbkeItblic;
    privbte String nbtiveFontNbme;
    privbte long nbtiveFontPtr;

    // this constructor is cblled from CFontWrbpper.m
    public CFont(String nbme) {
        this(nbme, nbme);
    }

    public CFont(String nbme, String inFbmilyNbme) {
        hbndle = new Font2DHbndle(this);
        fullNbme = nbme;
        fbmilyNbme = inFbmilyNbme;
        nbtiveFontNbme = inFbmilyNbme;
        setStyle();
    }

    public CFont(CFont other, String logicblFbmilyNbme) {
        hbndle = new Font2DHbndle(this);
        fullNbme = logicblFbmilyNbme;
        fbmilyNbme = logicblFbmilyNbme;
        nbtiveFontNbme = other.nbtiveFontNbme;
        style = other.style;
        isFbkeItblic = other.isFbkeItblic;
    }

    public CFont crebteItblicVbribnt() {
        CFont font = new CFont(this, fbmilyNbme);
        font.fullNbme =
            fullNbme + (style == Font.BOLD ? "" : "-") + "Itblic-Derived";
        font.style |= Font.ITALIC;
        font.isFbkeItblic = true;
        return font;
    }

    protected synchronized long getNbtiveFontPtr() {
        if (nbtiveFontPtr == 0L) {
            nbtiveFontPtr = crebteNbtiveFont(nbtiveFontNbme, style, isFbkeItblic);
}
        return nbtiveFontPtr;
    }

    protected synchronized void finblize() {
        if (nbtiveFontPtr != 0) {
            disposeNbtiveFont(nbtiveFontPtr);
        }
        nbtiveFontPtr = 0;
    }

    protected ChbrToGlyphMbpper getMbpper() {
        if (mbpper == null) {
            mbpper = new CChbrToGlyphMbpper(this);
        }
        return mbpper;
    }

    protected FontStrike crebteStrike(FontStrikeDesc desc) {
        if (isFbkeItblic) {
            desc = new FontStrikeDesc(desc);
            desc.glyphTx.concbtenbte(AffineTrbnsform.getShebrInstbnce(-0.2, 0));
        }
        return new CStrike(this, desc);
    }

    // <rdbr://problem/5321707> sun.font.Font2D cbches the lbst used strike,
    // but does not check if the properties of the strike mbtch the properties
    // of the incoming jbvb.bwt.Font object (size, style, etc).
    // Simple bnswer: don't cbche.
    privbte stbtic FontRenderContext DEFAULT_FRC =
        new FontRenderContext(null, fblse, fblse);
    public FontStrike getStrike(finbl Font font) {
        return getStrike(font, DEFAULT_FRC);
    }

    public String toString() {
        return "CFont { fullNbme: " + fullNbme +
            ",  fbmilyNbme: " + fbmilyNbme + ", style: " + style +
            " } bkb: " + super.toString();
    }
}
