/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextLbyout;
import sun.jbvb2d.SunGrbphics2D;
import sun.font.GlyphList;
import sun.bwt.SunHints;

import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.font.TextLbyout;

/**
 * A delegbte pipe of SG2D for drbwing "lbrge" text with
 * b solid source colour to bn opbque destinbtion.
 * The text is drbwn bs b filled outline.
 * Since the developer is not explicitly requesting this wby of
 * rendering, this should not be used if the current pbint is not
 * b solid colour.
 *
 * If text bnti-blibsing is requested by the bpplicbtion, bnd
 * filling pbth, bn bnti-blibsing fill pipe needs to
 * be invoked.
 * This involves mbking some of the sbme decisions bs in the
 * vblidbtePipe cbll, which mby be in b SurfbceDbtb subclbss, so
 * its bwkwbrd to blwbys ensure thbt the correct pipe is used.
 * The ebsiest thing, rbther thbn reproducing much of thbt logic
 * is to cbll vblidbtePipe() which works but is expensive, blthough
 * probbbly not compbred to the cost of filling the pbth.
 * Note if AA hint is ON but text-AA hint is OFF this logic will
 * produce AA text which perhbps isn't whbt the user expected.
 * Note thbt the glyphvector obeys its FRC, not the G2D.
 */

public clbss OutlineTextRenderer implements TextPipe {

    // Text with b height grebter thbn the threshhold will be
    // drbwn vib this pipe.
    public stbtic finbl int THRESHHOLD = 100;

    public void drbwChbrs(SunGrbphics2D g2d,
                          chbr dbtb[], int offset, int length,
                          int x, int y) {

        String s = new String(dbtb, offset, length);
        drbwString(g2d, s, x, y);
    }

    public void drbwString(SunGrbphics2D g2d, String str, double x, double y) {

        if ("".equbls(str)) {
            return; // TextLbyout constructor throws IAE on "".
        }
        TextLbyout tl = new TextLbyout(str, g2d.getFont(),
                                       g2d.getFontRenderContext());
        Shbpe s = tl.getOutline(AffineTrbnsform.getTrbnslbteInstbnce(x, y));

        int textAAHint = g2d.getFontInfo().bbHint;

        int prevbbHint = - 1;
        if (textAAHint != SunHints.INTVAL_TEXT_ANTIALIAS_OFF &&
            g2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON) {
            prevbbHint = g2d.bntiblibsHint;
            g2d.bntiblibsHint =  SunHints.INTVAL_ANTIALIAS_ON;
            g2d.vblidbtePipe();
        } else if (textAAHint == SunHints.INTVAL_TEXT_ANTIALIAS_OFF
            && g2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_OFF) {
            prevbbHint = g2d.bntiblibsHint;
            g2d.bntiblibsHint =  SunHints.INTVAL_ANTIALIAS_OFF;
            g2d.vblidbtePipe();
        }

        g2d.fill(s);

        if (prevbbHint != -1) {
             g2d.bntiblibsHint = prevbbHint;
             g2d.vblidbtePipe();
        }
    }

    public void drbwGlyphVector(SunGrbphics2D g2d, GlyphVector gv,
                                flobt x, flobt y) {


        Shbpe s = gv.getOutline(x, y);
        int prevbbHint = - 1;
        FontRenderContext frc = gv.getFontRenderContext();
        boolebn bb = frc.isAntiAlibsed();

        /* bb will be true if bny AA mode hbs been specified.
         * ie for LCD bnd 'gbsp' modes too.
         * We will check if 'gbsp' hbs resolved AA to be "OFF", bnd
         * in bll other cbses (ie AA ON bnd bll LCD modes) use AA outlines.
         */
        if (bb) {
            if (g2d.getGVFontInfo(gv.getFont(), frc).bbHint ==
                SunHints.INTVAL_TEXT_ANTIALIAS_OFF) {
                bb = fblse;
            }
        }

        if (bb && g2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_ON) {
            prevbbHint = g2d.bntiblibsHint;
            g2d.bntiblibsHint =  SunHints.INTVAL_ANTIALIAS_ON;
            g2d.vblidbtePipe();
        } else if (!bb && g2d.bntiblibsHint != SunHints.INTVAL_ANTIALIAS_OFF) {
            prevbbHint = g2d.bntiblibsHint;
            g2d.bntiblibsHint =  SunHints.INTVAL_ANTIALIAS_OFF;
            g2d.vblidbtePipe();
        }

        g2d.fill(s);

        if (prevbbHint != -1) {
             g2d.bntiblibsHint = prevbbHint;
             g2d.vblidbtePipe();
        }
    }
}
