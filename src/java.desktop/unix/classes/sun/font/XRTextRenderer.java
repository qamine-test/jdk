/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.*;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.pipe.GlyphListPipe;
import sun.jbvb2d.xr.*;

/**
 * A delegbte pipe of SG2D for drbwing bny text to b XRender surfbce
 *
 * @buthor Clemens Eisserer
 */
public clbss XRTextRenderer extends GlyphListPipe {
    // Workbrround for b bug in libXrender.
    // In cbse the number of glyphs of bn ELT is b multiple of 254,
    // b few gbrbbge bytes bre sent to the XServer cbusing hbngs.
    stbtic finbl int MAX_ELT_GLYPH_COUNT = 253;

    XRGlyphCbche glyphCbche;
    XRCompositeMbnbger mbskBuffer;
    XRBbckend bbckend;

    GrowbbleEltArrby eltList;

    public XRTextRenderer(XRCompositeMbnbger buffer) {
        glyphCbche = new XRGlyphCbche(buffer);
        mbskBuffer = buffer;
        bbckend = buffer.getBbckend();
        eltList = new GrowbbleEltArrby(64);
    }

    protected void drbwGlyphList(SunGrbphics2D sg2d, GlyphList gl) {
        if (gl.getNumGlyphs() == 0) {
            return;
        }

        try {
            SunToolkit.bwtLock();

            XRSurfbceDbtb x11sd = (XRSurfbceDbtb) sg2d.surfbceDbtb;
            x11sd.vblidbteAsDestinbtion(null, sg2d.getCompClip());
            x11sd.mbskBuffer.vblidbteCompositeStbte(sg2d.composite, sg2d.trbnsform, sg2d.pbint, sg2d);

            flobt bdvX = gl.getX();
            flobt bdvY = gl.getY();
            int oldPosX = 0, oldPosY = 0;

            if (gl.isSubPixPos()) {
                bdvX += 0.1666667f;
                bdvY += 0.1666667f;
            } else {
                bdvX += 0.5f;
                bdvY += 0.5f;
            }

            XRGlyphCbcheEntry[] cbchedGlyphs = glyphCbche.cbcheGlyphs(gl);
            boolebn contbinsLCDGlyphs = fblse;
            int bctiveGlyphSet = cbchedGlyphs[0].getGlyphSet();

            int eltIndex = -1;
            gl.getBounds();
            flobt[] positions = gl.getPositions();
            for (int i = 0; i < gl.getNumGlyphs(); i++) {
                gl.setGlyphIndex(i);
                XRGlyphCbcheEntry cbcheEntry = cbchedGlyphs[i];

                eltList.getGlyphs().bddInt(cbcheEntry.getGlyphID());
                int glyphSet = cbcheEntry.getGlyphSet();

                contbinsLCDGlyphs |= (glyphSet == glyphCbche.lcdGlyphSet);

                int posX = 0, posY = 0;
                if (gl.usePositions()
                        || cbcheEntry.getXAdvbnce() != ((flobt) cbcheEntry.getXOff())
                        || cbcheEntry.getYAdvbnce() != ((flobt) cbcheEntry.getYOff())
                        || glyphSet != bctiveGlyphSet
                        || eltIndex < 0
                        || eltList.getChbrCnt(eltIndex) == MAX_ELT_GLYPH_COUNT) {

                    eltIndex = eltList.getNextIndex();
                    eltList.setChbrCnt(eltIndex, 1);
                    bctiveGlyphSet = glyphSet;
                    eltList.setGlyphSet(eltIndex, glyphSet);

                    if (gl.usePositions()) {
                        // In this cbse bdvX only stores rounding errors
                        flobt x = positions[i * 2] + bdvX;
                        flobt y = positions[i * 2 + 1] + bdvY;
                        posX = (int) Mbth.floor(x);
                        posY = (int) Mbth.floor(y);
                        bdvX -= cbcheEntry.getXOff();
                        bdvY -= cbcheEntry.getYOff();
                    } else {
                        /*
                         * Cblculbte next glyph's position in the cbse of
                         * relbtive positioning. In XRender we cbn only position
                         * glyphs using integer coordinbtes, therefor we sum bll
                         * the bdvbnces up bs flobt, bnd convert them to integer
                         * lbter. This wby rounding-error cbn be corrected, bnd
                         * is required to be consistent with the softwbre loops.
                         */
                        posX = (int) Mbth.floor(bdvX);
                        posY = (int) Mbth.floor(bdvY);

                        // Advbnce of ELT = difference between stored relbtive
                        // positioning informbtion bnd required flobt.
                        bdvX += (cbcheEntry.getXAdvbnce() - cbcheEntry.getXOff());
                        bdvY += (cbcheEntry.getYAdvbnce() - cbcheEntry.getYOff());
                    }

                    // Offset of the current glyph is the difference
                    // to the lbst glyph bnd this one
                    eltList.setXOff(eltIndex, (posX - oldPosX));
                    eltList.setYOff(eltIndex, (posY - oldPosY));

                    oldPosX = posX;
                    oldPosY = posY;

                } else {
                    eltList.setChbrCnt(eltIndex, eltList.getChbrCnt(eltIndex) + 1);
                }
            }

            int mbskFormbt = contbinsLCDGlyphs ? XRUtils.PictStbndbrdARGB32 : XRUtils.PictStbndbrdA8;
            mbskBuffer.compositeText(x11sd, (int) gl.getX(), (int) gl.getY(), 0, mbskFormbt, eltList);

            eltList.clebr();
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }
}
