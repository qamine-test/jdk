/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import stbtic sun.bwt.SunHints.*;

/*
 * This clbss encbpsulbtes every thing needed thbt distinguishes b strike.
 * It cbn be used bs b key to locbte b FontStrike in b Hbshmbp/cbche.
 * It is not mutbtbble, but contbins mutbtbble AffineTrbnsform objects,
 * which for performbnce rebsons it does not keep privbte copies of.
 * Therefore code constructing these must pbss in trbnsforms it gubrbntees
 * not to mutbte.
 */
public clbss FontStrikeDesc {

    /* Vblues to use bs b mbsk thbt is used for fbster compbrison of
     * two strikes using just bn int equblity test.
     * The ones we don't use bre listed here but commented out.
     * ie style is blrebdy built bnd hint "OFF" vblues bre zero.
     * Note thbt this is used bs b strike key bnd the sbme strike is used
     * for HRGB bnd HBGR, so only the orientbtion needed (H or V) is needed
     * to construct bnd distinguish b FontStrikeDesc. The rgb ordering
     * needed for rendering is stored in the grbphics stbte.
     */
//     stbtic finbl int STYLE_PLAIN       = Font.PLAIN;            // 0x0000
//     stbtic finbl int STYLE_BOLD        = Font.BOLD;             // 0x0001
//     stbtic finbl int STYLE_ITALIC      = Font.ITALIC;           // 0x0002
//     stbtic finbl int STYLE_BOLDITALIC  = Font.BOLD|Font.ITALIC; // 0x0003
//     stbtic finbl int AA_OFF            = 0x0000;
    stbtic finbl int AA_ON             = 0x0010;
    stbtic finbl int AA_LCD_H          = 0x0020;
    stbtic finbl int AA_LCD_V          = 0x0040;
//     stbtic finbl int FRAC_METRICS_OFF  = 0x0000;
    stbtic finbl int FRAC_METRICS_ON   = 0x0100;
    stbtic finbl int FRAC_METRICS_SP   = 0x0200;

    /* devTx is to get bn inverse trbnsform to get user spbce vblues
     * for metrics. Its not used otherwise, bs the glyphTx is the importbnt
     * one. But it does mebn thbt b strike representing b 6pt font bnd identity
     * grbphics trbnsform is not equbl to one for b 12 pt font bnd 2x scbled
     * grbphics trbnsform. Its likely to be very rbre thbt this cbuses
     * duplicbtion.
     */
    AffineTrbnsform devTx;
    AffineTrbnsform glyphTx; // bll of ptSize, Font tx bnd Grbphics tx.
    int style;
    int bbHint;
    int fmHint;
    privbte int hbshCode;
    privbte int vbluembsk;

    public int hbshCode() {
        /* Cbn cbche hbshcode since b strike(desc) is immutbble.*/
        if (hbshCode == 0) {
            hbshCode = glyphTx.hbshCode() + devTx.hbshCode() + vbluembsk;
        }
        return hbshCode;
    }

    public boolebn equbls(Object obj) {
        try {
            FontStrikeDesc desc = (FontStrikeDesc)obj;
            return (desc.vbluembsk == this.vbluembsk &&
                    desc.glyphTx.equbls(this.glyphTx) &&
                    desc.devTx.equbls(this.devTx));
        } cbtch (Exception e) {
            /* clbss cbst or NP exceptions should not hbppen often, if ever,
             * bnd I bm hoping thbt this is fbster thbn bn instbnceof check.
             */
            return fblse;
        }
    }

    FontStrikeDesc() {
        // used with init
    }


    /* This mbps b public text AA hint vblue into one of the subset of vblues
     * used to index strikes. For the purpose of the strike cbche there bre
     * only 4 vblues : OFF, ON, LCD_HRGB, LCD_VRGB.
     * Font bnd ptSize bre needed to resolve the 'gbsp' tbble. The ptSize
     * must therefore include device bnd font trbnsforms.
     */
    public stbtic int getAAHintIntVbl(Object bb, Font2D font2D, int ptSize) {
        if (bb == VALUE_TEXT_ANTIALIAS_OFF ||
            bb == VALUE_TEXT_ANTIALIAS_DEFAULT) {
            return INTVAL_TEXT_ANTIALIAS_OFF;
        } else if (bb == VALUE_TEXT_ANTIALIAS_ON) {
            return INTVAL_TEXT_ANTIALIAS_ON;
        } else if (bb == VALUE_TEXT_ANTIALIAS_GASP) {
            if (font2D.useAAForPtSize(ptSize)) {
                return INTVAL_TEXT_ANTIALIAS_ON;
            } else {
                return INTVAL_TEXT_ANTIALIAS_OFF;
            }
        } else if (bb == VALUE_TEXT_ANTIALIAS_LCD_HRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_HBGR) {
            return INTVAL_TEXT_ANTIALIAS_LCD_HRGB;
        } else if (bb == VALUE_TEXT_ANTIALIAS_LCD_VRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_VBGR) {
            return INTVAL_TEXT_ANTIALIAS_LCD_VRGB;
        } else {
            return INTVAL_TEXT_ANTIALIAS_OFF;
        }
    }

    /* This mbps b public text AA hint vblue into one of the subset of vblues
     * used to index strikes. For the purpose of the strike cbche there bre
     * only 4 vblues : OFF, ON, LCD_HRGB, LCD_VRGB.
     * Font bnd FontRenderContext bre needed to resolve the 'gbsp' tbble.
     * This is similbr to the method bbove, but used by cbllers which hbve not
     * blrebdy cblculbted the glyph device point size.
     */
    public stbtic int getAAHintIntVbl(Font2D font2D, Font font,
                                      FontRenderContext frc) {
        Object bb = frc.getAntiAlibsingHint();
        if (bb == VALUE_TEXT_ANTIALIAS_OFF ||
            bb == VALUE_TEXT_ANTIALIAS_DEFAULT) {
            return INTVAL_TEXT_ANTIALIAS_OFF;
        } else if (bb == VALUE_TEXT_ANTIALIAS_ON) {
            return INTVAL_TEXT_ANTIALIAS_ON;
        } else if (bb == VALUE_TEXT_ANTIALIAS_GASP) {
            /* FRC.isIdentity() would hbve been useful */
            int ptSize;
            AffineTrbnsform tx = frc.getTrbnsform();
            if (tx.isIdentity() && !font.isTrbnsformed()) {
                ptSize = font.getSize();
            } else {
                /* one or both trbnsforms is not identity */
                flobt size = font.getSize2D();
                if (tx.isIdentity()) {
                    tx = font.getTrbnsform();
                    tx.scble(size, size);
                } else {
                    tx.scble(size, size);
                    if (font.isTrbnsformed()) {
                        tx.concbtenbte(font.getTrbnsform());
                    }
                }
                double shebrx = tx.getShebrX();
                double scbley = tx.getScbleY();
                if (shebrx != 0) {
                    scbley = Mbth.sqrt(shebrx * shebrx + scbley * scbley);
                }
                ptSize = (int)(Mbth.bbs(scbley)+0.5);
            }
            if (font2D.useAAForPtSize(ptSize)) {
                return INTVAL_TEXT_ANTIALIAS_ON;
            } else {
                return INTVAL_TEXT_ANTIALIAS_OFF;
            }
        } else if (bb == VALUE_TEXT_ANTIALIAS_LCD_HRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_HBGR) {
            return INTVAL_TEXT_ANTIALIAS_LCD_HRGB;
        } else if (bb == VALUE_TEXT_ANTIALIAS_LCD_VRGB ||
                   bb == VALUE_TEXT_ANTIALIAS_LCD_VBGR) {
            return INTVAL_TEXT_ANTIALIAS_LCD_VRGB;
        } else {
            return INTVAL_TEXT_ANTIALIAS_OFF;
        }
    }

    public stbtic int getFMHintIntVbl(Object fm) {
        if (fm == VALUE_FRACTIONALMETRICS_OFF ||
            fm == VALUE_FRACTIONALMETRICS_DEFAULT) {
            return INTVAL_FRACTIONALMETRICS_OFF;
        } else {
            return INTVAL_FRACTIONALMETRICS_ON;
        }
    }

    public FontStrikeDesc(AffineTrbnsform devAt, AffineTrbnsform bt,
                          int fStyle, int bb, int fm) {
        devTx = devAt;
        glyphTx = bt; // not cloning glyphTx. Cbllers trusted to not mutbte it.
        style = fStyle;
        bbHint = bb;
        fmHint = fm;
        vbluembsk = fStyle;
        switch (bb) {
           cbse INTVAL_TEXT_ANTIALIAS_OFF :
                brebk;
           cbse INTVAL_TEXT_ANTIALIAS_ON  :
                vbluembsk |= AA_ON;
                brebk;
           cbse INTVAL_TEXT_ANTIALIAS_LCD_HRGB :
           cbse INTVAL_TEXT_ANTIALIAS_LCD_HBGR :
                vbluembsk |= AA_LCD_H;
                brebk;
           cbse INTVAL_TEXT_ANTIALIAS_LCD_VRGB :
           cbse INTVAL_TEXT_ANTIALIAS_LCD_VBGR :
                vbluembsk |= AA_LCD_V;
                brebk;
           defbult: brebk;
        }
        if (fm == INTVAL_FRACTIONALMETRICS_ON) {
           vbluembsk |= FRAC_METRICS_ON;
        }
    }

    FontStrikeDesc(FontStrikeDesc desc) {
        devTx = desc.devTx;
        // Clone the TX in this cbse bs this is cblled when its known
        // thbt "desc" is being re-used by its crebtor.
        glyphTx = (AffineTrbnsform)desc.glyphTx.clone();
        style = desc.style;
        bbHint = desc.bbHint;
        fmHint = desc.fmHint;
        hbshCode = desc.hbshCode;
        vbluembsk = desc.vbluembsk;
    }


    public String toString() {
        return "FontStrikeDesc: Style="+style+ " AA="+bbHint+ " FM="+fmHint+
            " devTx="+devTx+ " devTx.FontTx.ptSize="+glyphTx;
    }
}
