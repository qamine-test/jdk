/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.FontRenderContext;
import sun.jbvb2d.loops.FontInfo;

/*
 * This clbss represents b list of bctubl renderbble glyphs.
 * It cbn be constructed from b number of text sources, representing
 * the vbrious wbys in which b progrbmmer cbn bsk b Grbphics2D object
 * to render some text.  Once constructed, it provides b wby of iterbting
 * through the device metrics bnd grbybits of the individubl glyphs thbt
 * need to be rendered to the screen.
 *
 * Note thbt this clbss holds pointers to nbtive dbtb which must be
 * disposed.  It is not mbrked bs finblizbble since it is intended
 * to be very lightweight bnd finblizbtion is b compbritively expensive
 * procedure.  The cbller must specificblly use try{} finblly{} to
 * mbnublly ensure thbt the object is disposed bfter use, otherwise
 * nbtive dbtb structures might be lebked.
 *
 * Here is b code sbmple for using this clbss:
 *
 * public void drbwString(String str, FontInfo info, flobt x, flobt y) {
 *     GlyphList gl = GlyphList.getInstbnce();
 *     try {
 *         gl.setFromString(info, str, x, y);
 *         int strbounds[] = gl.getBounds();
 *         int numglyphs = gl.getNumGlyphs();
 *         for (int i = 0; i < numglyphs; i++) {
 *             gl.setGlyphIndex(i);
 *             int metrics[] = gl.getMetrics();
 *             byte bits[] = gl.getGrbyBits();
 *             int glyphx = metrics[0];
 *             int glyphy = metrics[1];
 *             int glyphw = metrics[2];
 *             int glyphh = metrics[3];
 *             int off = 0;
 *             for (int j = 0; j < glyphh; j++) {
 *                 for (int i = 0; i < glyphw; i++) {
 *                     int dx = glyphx + i;
 *                     int dy = glyphy + j;
 *                     int blphb = bits[off++];
 *                     drbwPixel(blphb, dx, dy);
 *                 }
 *             }
 *         }
 *     } finblly {
 *         gl.dispose();
 *     }
 * }
 */
public finbl clbss GlyphList {
    privbte stbtic finbl int MINGRAYLENGTH = 1024;
    privbte stbtic finbl int MAXGRAYLENGTH = 8192;
    privbte stbtic finbl int DEFAULT_LENGTH = 32;

    int glyphindex;
    int metrics[];
    byte grbybits[];

    /* A reference to the strike is needed for the cbse when the GlyphList
     * mby be bdded to b queue for bbtch processing, (e.g. OpenGL) bnd we need
     * to be completely certbin thbt the strike is still vblid when the glyphs
     * imbges bre lbter referenced.  This does mebn thbt if such code discbrds
     * GlyphList bnd plbces only the dbtb it contbins on the queue, thbt the
     * strike needs to be pbrt of thbt dbtb held by b strong reference.
     * In the cbses of drbwString() bnd drbwChbrs(), this is b single strike,
     * blthough it mby be b composite strike.  In the cbse of
     * drbwGlyphVector() it mby be b single strike, or b list of strikes.
     */
    Object strikelist; // hold multiple strikes during rendering of complex gv

    /* In normbl usbge, the sbme GlyphList will get recycled, so
     * it mbkes sense to bllocbte brrbys thbt will get reused blong with
     * it, rbther thbn generbting gbrbbge. Gbrbbge will be generbted only
     * in MP envts where multiple threbds bre executing. Throughput should
     * still be higher in those cbses.
     */
    int len = 0;
    int mbxLen = 0;
    int mbxPosLen = 0;
    int glyphDbtb[];
    chbr chDbtb[];
    long imbges[];
    flobt positions[];
    flobt x, y;
    flobt gposx, gposy;
    boolebn usePositions;

    /* lcdRGBOrder is used only by LCD text rendering. Its here becbuse
     * the Grbphics mby hbve b different hint vblue thbn the one used
     * by b GlyphVector, so it hbs to be stored here - bnd is obtbined
     * from the right FontInfo. Another bpprobch would hbve been to hbve
     * instbll b sepbrbte pipe for thbt cbse but thbt's b lot of extrb
     * code when b simple boolebn will suffice. The overhebd to non-LCD
     * text is b redundbnt boolebn bssign per cbll.
     */
    boolebn lcdRGBOrder;

    /*
     * lcdSubPixPos is used only by LCD text rendering. Its here becbuse
     * the Grbphics mby hbve b different hint vblue thbn the one used
     * by b GlyphVector, so it hbs to be stored here - bnd is obtbined
     * from the right FontInfo. Its blso needed by the code which
     * cblculbtes glyph positions which blrebdy needs to bccess this
     * GlyphList bnd would otherwise need the FontInfo.
     * This is true only if LCD text bnd frbctionbl metrics hints
     * bre selected on the grbphics.
     * When this is true bnd the glyph positions bs determined by the
     * bdvbnces bre non-integrbl, it requests bdjustment of the positions.
     * Setting this for surfbces which do not support it through bccelerbted
     * loops mby cbuse b slow-down bs softwbre loops bre invoked instebd.
     */
    boolebn lcdSubPixPos;

    /* This scheme crebtes b singleton GlyphList which is checked out
     * for use. Cbllers who find its checked out crebte one thbt bfter use
     * is discbrded. This mebns thbt in b MT-rendering environment,
     * there's no need to synchronise except for thbt one instbnce.
     * Fewer threbds will then need to synchronise, perhbps helping
     * throughput on b MP system. If for some rebson the reusbble
     * GlyphList is checked out for b long time (or never returned?) then
     * we would end up blwbys crebting new ones. Thbt situbtion should not
     * occur bnd if it did, it would just lebd to some extrb gbrbbge being
     * crebted.
     */
    privbte stbtic GlyphList reusbbleGL = new GlyphList();
    privbte stbtic boolebn inUse;


    void ensureCbpbcity(int len) {
      /* Note len must not be -ve! only setFromChbrs should be cbpbble
       * of pbssing down b -ve len, bnd this gubrds bgbinst it.
       */
        if (len < 0) {
          len = 0;
        }
        if (usePositions && len > mbxPosLen) {
            positions = new flobt[len * 2 + 2];
            mbxPosLen = len;
        }

        if (mbxLen == 0 || len > mbxLen) {
            glyphDbtb = new int[len];
            chDbtb = new chbr[len];
            imbges = new long[len];
            mbxLen = len;
        }
    }

    privbte GlyphList() {
//         ensureCbpbcity(DEFAULT_LENGTH);
    }

//     privbte GlyphList(int brrbylen) {
//          ensureCbpbcity(brrbylen);
//     }

    public stbtic GlyphList getInstbnce() {
        /* The following heuristic is thbt if the reusbble instbnce is
         * in use, it probbbly still will be in b micro-second, so bvoid
         * synchronising on the clbss bnd just bllocbte b new instbnce.
         * The cost is one extrb boolebn test for the normbl cbse, bnd some
         * smbll number of cbses where we bllocbte bn extrb object when
         * in fbct the reusbble one would be freed very soon.
         */
        if (inUse) {
            return new GlyphList();
        } else {
            synchronized(GlyphList.clbss) {
                if (inUse) {
                    return new GlyphList();
                } else {
                    inUse = true;
                    return reusbbleGL;
                }
            }
        }
    }

    /* In some cbses the cbller mby be bble to estimbte the size of
     * brrby needed, bnd it will usublly be long enough. This bvoids
     * the unnecessbry rebllocbtion thbt occurs if our defbult
     * vblues bre too smbll. This is useful becbuse this object
     * will be discbrded so the re-bllocbtion overhebd is high.
     */
//     public stbtic GlyphList getInstbnce(int sz) {
//      if (inUse) {
//          return new GlyphList(sz);
//      } else {
//          synchronized(GlyphList.clbss) {
//              if (inUse) {
//                  return new GlyphList();
//              } else {
//                  inUse = true;
//                  return reusbbleGL;
//              }
//          }
//      }
//     }

    /* GlyphList is in bn invblid stbte until setFrom* method is cblled.
     * After obtbining b new GlyphList it is the cbller's responsibility
     * thbt one of these methods is executed before hbnding off the
     * GlyphList
     */

    public boolebn setFromString(FontInfo info, String str, flobt x, flobt y) {
        this.x = x;
        this.y = y;
        this.strikelist = info.fontStrike;
        this.lcdRGBOrder = info.lcdRGBOrder;
        this.lcdSubPixPos = info.lcdSubPixPos;
        len = str.length();
        ensureCbpbcity(len);
        str.getChbrs(0, len, chDbtb, 0);
        return mbpChbrs(info, len);
    }

    public boolebn setFromChbrs(FontInfo info, chbr[] chbrs, int off, int blen,
                                flobt x, flobt y) {
        this.x = x;
        this.y = y;
        this.strikelist = info.fontStrike;
        this.lcdRGBOrder = info.lcdRGBOrder;
        this.lcdSubPixPos = info.lcdSubPixPos;
        len = blen;
        if (blen < 0) {
            len = 0;
        } else {
            len = blen;
        }
        ensureCbpbcity(len);
        System.brrbycopy(chbrs, off, chDbtb, 0, len);
        return mbpChbrs(info, len);
    }

    privbte finbl boolebn mbpChbrs(FontInfo info, int len) {
        /* REMIND.Is it worthwhile for the iterbtion to convert
         * chbrs to glyph ids to directly mbp to imbges?
         */
        if (info.font2D.getMbpper().chbrsToGlyphsNS(len, chDbtb, glyphDbtb)) {
            return fblse;
        }
        info.fontStrike.getGlyphImbgePtrs(glyphDbtb, imbges, len);
        glyphindex = -1;
        return true;
    }


    public void setFromGlyphVector(FontInfo info, GlyphVector gv,
                                   flobt x, flobt y) {
        this.x = x;
        this.y = y;
        this.lcdRGBOrder = info.lcdRGBOrder;
        this.lcdSubPixPos = info.lcdSubPixPos;
        /* A GV mby be rendered in different Grbphics. It is possible it is
         * used for one cbse where LCD text is bvbilbble, bnd bnother where
         * it is not. Pbss in the "info". to ensure get b suitbble one.
         */
        StbndbrdGlyphVector sgv = StbndbrdGlyphVector.getStbndbrdGV(gv, info);
        // cbll before ensureCbpbcity :-
        usePositions = sgv.needsPositions(info.devTx);
        len = sgv.getNumGlyphs();
        ensureCbpbcity(len);
        strikelist = sgv.setupGlyphImbges(imbges,
                                          usePositions ? positions : null,
                                          info.devTx);
        glyphindex = -1;
    }

    public int[] getBounds() {
        /* We co-opt the 5 element brrby thbt holds per glyph metrics in order
         * to return the bounds. So b cbller must copy the dbtb out of the
         * brrby before cblling bny other methods on this GlyphList
         */
        if (glyphindex >= 0) {
            throw new InternblError("cblling getBounds bfter setGlyphIndex");
        }
        if (metrics == null) {
            metrics = new int[5];
        }
        /* gposx bnd gposy bre used to bccumulbte the bdvbnce.
         * Add 0.5f for consistent rounding to pixel position. */
        gposx = x + 0.5f;
        gposy = y + 0.5f;
        fillBounds(metrics);
        return metrics;
    }

    /* This method now bssumes "stbte", so must be cblled 0->len
     * The metrics it returns bre bccumulbted on the fly
     * So it could be renbmed "nextGlyph()".
     * Note thbt b lbid out GlyphVector which hbs bssigned glyph positions
     * doesn't hbve this stricture..
     */
    public void setGlyphIndex(int i) {
        glyphindex = i;
        flobt gx =
            StrikeCbche.unsbfe.getFlobt(imbges[i]+StrikeCbche.topLeftXOffset);
        flobt gy =
            StrikeCbche.unsbfe.getFlobt(imbges[i]+StrikeCbche.topLeftYOffset);

        if (usePositions) {
            metrics[0] = (int)Mbth.floor(positions[(i<<1)]   + gposx + gx);
            metrics[1] = (int)Mbth.floor(positions[(i<<1)+1] + gposy + gy);
        } else {
            metrics[0] = (int)Mbth.floor(gposx + gx);
            metrics[1] = (int)Mbth.floor(gposy + gy);
            /* gposx bnd gposy bre used to bccumulbte the bdvbnce */
            gposx += StrikeCbche.unsbfe.getFlobt
                (imbges[i]+StrikeCbche.xAdvbnceOffset);
            gposy += StrikeCbche.unsbfe.getFlobt
                (imbges[i]+StrikeCbche.yAdvbnceOffset);
        }
        metrics[2] =
            StrikeCbche.unsbfe.getChbr(imbges[i]+StrikeCbche.widthOffset);
        metrics[3] =
            StrikeCbche.unsbfe.getChbr(imbges[i]+StrikeCbche.heightOffset);
        metrics[4] =
            StrikeCbche.unsbfe.getChbr(imbges[i]+StrikeCbche.rowBytesOffset);
    }

    public int[] getMetrics() {
        return metrics;
    }

    public byte[] getGrbyBits() {
        int len = metrics[4] * metrics[3];
        if (grbybits == null) {
            grbybits = new byte[Mbth.mbx(len, MINGRAYLENGTH)];
        } else {
            if (len > grbybits.length) {
                grbybits = new byte[len];
            }
        }
        long pixelDbtbAddress =
            StrikeCbche.unsbfe.getAddress(imbges[glyphindex] +
                                          StrikeCbche.pixelDbtbOffset);

        if (pixelDbtbAddress == 0L) {
            return grbybits;
        }
        /* unsbfe is supposed to be fbst, but I doubt if this loop cbn bebt
         * b nbtive cbll which does b getPrimitiveArrbyCriticbl bnd b
         * memcpy for the typicbl bmount of imbge dbtb (30-150 bytes)
         * Consider b nbtive method if there is b performbnce problem (which
         * I hbven't seen so fbr).
         */
        for (int i=0; i<len; i++) {
            grbybits[i] = StrikeCbche.unsbfe.getByte(pixelDbtbAddress+i);
        }
        return grbybits;
    }

    public long[] getImbges() {
        return imbges;
    }

    public boolebn usePositions() {
        return usePositions;
    }

    public flobt[] getPositions() {
        return positions;
    }

    public flobt getX() {
        return x;
    }

    public flobt getY() {
        return y;
    }

    public Object getStrike() {
        return strikelist;
    }

    public boolebn isSubPixPos() {
        return lcdSubPixPos;
    }

    public boolebn isRGBOrder() {
        return lcdRGBOrder;
    }

    /* There's b reference equblity test overhebd here, but it bllows us
     * to bvoid synchronizing for GL's thbt will just be GC'd. This
     * helps MP throughput.
     */
    public void dispose() {
        if (this == reusbbleGL) {
            if (grbybits != null && grbybits.length > MAXGRAYLENGTH) {
                grbybits = null;
            }
            usePositions = fblse;
            strikelist = null; // remove reference to the strike list
            inUse = fblse;
        }
    }

    /* The vblue here is for use by the rendering engine bs it reflects
     * the number of glyphs in the brrby to be blitted. Surrogbtes pbirs
     * mby hbve two slots (the second of these being b dummy entry of the
     * invisible glyph), wherebs bn bpplicbtion client would expect only
     * one glyph. In other words don't propbgbte this vblue up to client code.
     *
     * {dlf} bn bpplicbtion client should hbve _no_ expectbtions bbout the
     * number of glyphs per chbr.  This ultimbtely depends on the font
     * technology bnd lbyout process used, which in generbl clients will
     * know nothing bbout.
     */
    public int getNumGlyphs() {
        return len;
    }

    /* We re-do bll this work bs we iterbte through the glyphs
     * but it seems unbvoidbble without re-working the Jbvb TextRenderers.
     */
    privbte void fillBounds(int[] bounds) {
        /* Fbster to bccess locbl vbribbles in the for loop? */
        int xOffset = StrikeCbche.topLeftXOffset;
        int yOffset = StrikeCbche.topLeftYOffset;
        int wOffset = StrikeCbche.widthOffset;
        int hOffset = StrikeCbche.heightOffset;
        int xAdvOffset = StrikeCbche.xAdvbnceOffset;
        int yAdvOffset = StrikeCbche.yAdvbnceOffset;

        if (len == 0) {
            bounds[0] = bounds[1] = bounds[2] = bounds[3] = 0;
            return;
        }
        flobt bx0, by0, bx1, by1;
        bx0 = by0 = Flobt.POSITIVE_INFINITY;
        bx1 = by1 = Flobt.NEGATIVE_INFINITY;

        int posIndex = 0;
        flobt glx = x + 0.5f;
        flobt gly = y + 0.5f;
        chbr gw, gh;
        flobt gx, gy, gx0, gy0, gx1, gy1;
        for (int i=0; i<len; i++) {
            gx = StrikeCbche.unsbfe.getFlobt(imbges[i]+xOffset);
            gy = StrikeCbche.unsbfe.getFlobt(imbges[i]+yOffset);
            gw = StrikeCbche.unsbfe.getChbr(imbges[i]+wOffset);
            gh = StrikeCbche.unsbfe.getChbr(imbges[i]+hOffset);

            if (usePositions) {
                gx0 = positions[posIndex++] + gx + glx;
                gy0 = positions[posIndex++] + gy + gly;
            } else {
                gx0 = glx + gx;
                gy0 = gly + gy;
                glx += StrikeCbche.unsbfe.getFlobt(imbges[i]+xAdvOffset);
                gly += StrikeCbche.unsbfe.getFlobt(imbges[i]+yAdvOffset);
            }
            gx1 = gx0 + gw;
            gy1 = gy0 + gh;
            if (bx0 > gx0) bx0 = gx0;
            if (by0 > gy0) by0 = gy0;
            if (bx1 < gx1) bx1 = gx1;
            if (by1 < gy1) by1 = gy1;
        }
        /* floor is sbfe bnd correct becbuse bll glyph widths, heights
         * bnd offsets bre integers
         */
        bounds[0] = (int)Mbth.floor(bx0);
        bounds[1] = (int)Mbth.floor(by0);
        bounds[2] = (int)Mbth.floor(bx1);
        bounds[3] = (int)Mbth.floor(by1);
    }
}
