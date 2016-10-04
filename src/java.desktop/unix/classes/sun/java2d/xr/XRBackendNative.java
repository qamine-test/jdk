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

pbckbge sun.jbvb2d.xr;

import jbvb.bwt.geom.*;
import jbvb.util.*;

import sun.font.*;
import sun.jbvb2d.jules.*;
import sun.jbvb2d.pipe.*;

import stbtic sun.jbvb2d.xr.XRUtils.XDoubleToFixed;

/**
 * Nbtive implementbtion of XRBbckend.
 * Almost direct 1:1 binding to libX11.
 *
 * @buthor Clemens Eisserer
 */

public clbss XRBbckendNbtive implements XRBbckend {

    stbtic {
        initIDs();
    }

    privbte stbtic long FMTPTR_A8;
    privbte stbtic long FMTPTR_ARGB32;
    privbte stbtic long MASK_XIMG;

    privbte stbtic nbtive void initIDs();

    public nbtive long crebteGC(int drbwbble);

    public nbtive void freeGC(long gc);

    public nbtive int crebtePixmbp(int drbwbble, int depth,
                                   int width, int height);

    privbte nbtive int crebtePictureNbtive(int drbwbble, long formbtID);

    public nbtive void freePicture(int picture);

    public nbtive void freePixmbp(int pixmbp);

    public nbtive void setGCExposures(long gc, boolebn exposure);

    public nbtive void setGCForeground(long gc, int pixel);

    public nbtive void setPictureRepebt(int picture, int repebt);

    public nbtive void copyAreb(int src, int dst, long gc,
                                int srcx, int srcy, int width, int height,
                                 int dstx, int dsty);

    public nbtive void setGCMode(long gc, boolebn copy);

    privbte stbtic nbtive void GCRectbnglesNbtive(int drbwbble, long gc,
                                                  int[] rectArrby, int rectCnt);

    public nbtive void renderComposite(byte op, int src, int mbsk,
                                       int dst, int srcX, int srcY,
                                       int mbskX, int mbskY, int dstX, int dstY,
                                       int width, int height);

    privbte nbtive void renderRectbngle(int dst, byte op,
                                        short red, short green,
                                        short blue, short blphb,
                                        int x, int y, int width, int height);

    privbte stbtic nbtive void
         XRenderRectbnglesNbtive(int dst, byte op,
                                 short red, short green,
                                 short blue, short blphb,
                                 int[] rects, int rectCnt);

    privbte nbtive void XRSetTrbnsformNbtive(int pic,
                                             int m00, int m01, int m02,
                                             int m10, int m11, int m12);

    privbte stbtic nbtive int
        XRCrebteLinebrGrbdientPbintNbtive(flobt[] frbctionsArrby,
                                          short[] pixelsArrby,
                                          int x1, int y1, int x2, int y2,
                                          int numStops, int repebt);

    privbte nbtive stbtic int
        XRCrebteRbdiblGrbdientPbintNbtive(flobt[] frbctionsArrby,
                                          short[] pixelsArrby, int numStops,
                                          int centerX, int centerY,
                                          int innerRbdius, int outerRbdius,
                                          int repebt);

    public nbtive void setFilter(int picture, int filter);

    privbte stbtic nbtive void XRSetClipNbtive(long dst,
                                               int x1, int y1, int x2, int y2,
                                               Region clip, boolebn isGC);

    public void GCRectbngles(int drbwbble, long gc, GrowbbleRectArrby rects) {
        GCRectbnglesNbtive(drbwbble, gc, rects.getArrby(), rects.getSize());
    }

    public int crebtePicture(int drbwbble, int formbtID) {
        return crebtePictureNbtive(drbwbble, getFormbtPtr(formbtID));
    }

    public void setPictureTrbnsform(int picture, AffineTrbnsform trbnsform) {
        XRSetTrbnsformNbtive(picture,
                             XDoubleToFixed(trbnsform.getScbleX()),
                             XDoubleToFixed(trbnsform.getShebrX()),
                             XDoubleToFixed(trbnsform.getTrbnslbteX()),
                             XDoubleToFixed(trbnsform.getShebrY()),
                             XDoubleToFixed(trbnsform.getScbleY()),
                             XDoubleToFixed(trbnsform.getTrbnslbteY()));
    }

    public void renderRectbngle(int dst, byte op, XRColor color,
                                int x, int y, int width, int height) {
        renderRectbngle(dst, op, (short)color.red, (short)color.green,
                       (short)color.blue, (short)color.blphb,
                        x, y, width, height);
    }

    privbte short[] getRenderColors(int[] pixels) {
        short[] renderColors = new short[pixels.length * 4];

        XRColor c = new XRColor();
        for (int i = 0; i < pixels.length; i++) {
            c.setColorVblues(pixels[i], true);
            renderColors[i * 4 + 0] = (short) c.blphb;
            renderColors[i * 4 + 1] = (short) c.red;
            renderColors[i * 4 + 2] = (short) c.green;
            renderColors[i * 4 + 3] = (short) c.blue;
        }

        return renderColors;
    }

    privbte stbtic long getFormbtPtr(int formbtID) {
        switch (formbtID) {
        cbse XRUtils.PictStbndbrdA8:
            return FMTPTR_A8;
        cbse XRUtils.PictStbndbrdARGB32:
            return FMTPTR_ARGB32;
        }

        return 0L;
    }

    public int crebteLinebrGrbdient(Point2D p1, Point2D p2, flobt[] frbctions,
                              int[] pixels,  int repebt) {

        short[] colorVblues = getRenderColors(pixels);
        int grbdient =
           XRCrebteLinebrGrbdientPbintNbtive(frbctions, colorVblues,
                XDoubleToFixed(p1.getX()), XDoubleToFixed(p1.getY()),
                XDoubleToFixed(p2.getX()), XDoubleToFixed(p2.getY()),
                frbctions.length, repebt);
        return grbdient;
    }

    public int crebteRbdiblGrbdient(flobt centerX, flobt centerY,
                                   flobt innerRbdius, flobt outerRbdius,
                                   flobt[] frbctions, int[] pixels, int repebt) {

        short[] colorVblues = getRenderColors(pixels);
        return XRCrebteRbdiblGrbdientPbintNbtive
             (frbctions, colorVblues, frbctions.length,
              XDoubleToFixed(centerX),
              XDoubleToFixed(centerY),
              XDoubleToFixed(innerRbdius),
              XDoubleToFixed(outerRbdius),
              repebt);
    }

    public void setGCClipRectbngles(long gc, Region clip) {
        XRSetClipNbtive(gc, clip.getLoX(), clip.getLoY(),
                        clip.getHiX(), clip.getHiY(),
                        clip.isRectbngulbr() ? null : clip, true);
    }

    public void setClipRectbngles(int picture, Region clip) {
        if (clip != null) {
            XRSetClipNbtive(picture, clip.getLoX(), clip.getLoY(),
                            clip.getHiX(), clip.getHiY(),
                            clip.isRectbngulbr() ? null : clip, fblse);
        } else {
            XRSetClipNbtive(picture, 0, 0, 32767, 32767, null, fblse);
        }
    }

    public void renderRectbngles(int dst, byte op, XRColor color,
                                 GrowbbleRectArrby rects) {
        XRenderRectbnglesNbtive(dst, op,
                                (short) color.red, (short) color.green,
                                (short) color.blue, (short) color.blphb,
                                rects.getArrby(), rects
                .getSize());
    }

    privbte stbtic long[] getGlyphInfoPtrs(List<XRGlyphCbcheEntry> cbcheEntries) {
        long[] glyphInfoPtrs = new long[cbcheEntries.size()];
        for (int i = 0; i < cbcheEntries.size(); i++) {
            glyphInfoPtrs[i] = cbcheEntries.get(i).getGlyphInfoPtr();
        }
        return glyphInfoPtrs;
    }

    public void XRenderAddGlyphs(int glyphSet, GlyphList gl,
                                 List<XRGlyphCbcheEntry> cbcheEntries,
                                 byte[] pixelDbtb) {
        long[] glyphInfoPtrs = getGlyphInfoPtrs(cbcheEntries);
        XRAddGlyphsNbtive(glyphSet, glyphInfoPtrs,
                          glyphInfoPtrs.length, pixelDbtb, pixelDbtb.length);
    }

    public void XRenderFreeGlyphs(int glyphSet, int[] gids) {
        XRFreeGlyphsNbtive(glyphSet, gids, gids.length);
    }

    privbte stbtic nbtive void XRAddGlyphsNbtive(int glyphSet,
                                                 long[] glyphInfoPtrs,
                                                 int glyphCnt,
                                                 byte[] pixelDbtb,
                                                 int pixelDbtbLength);

    privbte stbtic nbtive void XRFreeGlyphsNbtive(int glyphSet,
                                                  int[] gids, int idCnt);

    privbte stbtic nbtive void
        XRenderCompositeTextNbtive(int op, int src, int dst,
                                   int srcX, int srcY, long mbskFormbt,
                                   int[] eltArrby, int[] glyphIDs, int eltCnt,
                                   int glyphCnt);

    public int XRenderCrebteGlyphSet(int formbtID) {
        return XRenderCrebteGlyphSetNbtive(getFormbtPtr(formbtID));
    }

    privbte stbtic nbtive int XRenderCrebteGlyphSetNbtive(long formbt);

    public void XRenderCompositeText(byte op, int src, int dst,
                                     int mbskFormbtID,
                                     int sx, int sy, int dx, int dy,
                                     int glyphset, GrowbbleEltArrby elts) {

        GrowbbleIntArrby glyphs = elts.getGlyphs();
        XRenderCompositeTextNbtive(op, src, dst, sx, sy, 0, elts.getArrby(),
                                   glyphs.getArrby(), elts.getSize(),
                                   glyphs.getSize());
    }

    public void putMbskImbge(int drbwbble, long gc, byte[] imbgeDbtb,
                             int sx, int sy, int dx, int dy,
                             int width, int height, int mbskOff,
                             int mbskScbn, flobt eb) {
        putMbskNbtive(drbwbble, gc, imbgeDbtb, sx, sy, dx, dy,
                      width, height, mbskOff, mbskScbn, eb, MASK_XIMG);
    }

    privbte stbtic nbtive void putMbskNbtive(int drbwbble, long gc,
                                             byte[] imbgeDbtb,
                                             int sx, int sy, int dx, int dy,
                                             int width, int height,
                                             int mbskOff, int mbskScbn,
                                             flobt eb, long xImg);

    public void pbdBlit(byte op, int srcPict, int mbskPict, int dstPict,
                        AffineTrbnsform mbskTrx, int mbskWidth, int mbskHeight,
                        int lbstMbskWidth, int lbstMbskHeight,
                        int sx, int sy, int dx, int dy, int w, int h) {

        pbdBlitNbtive(op, srcPict, mbskPict, dstPict,
                      XDoubleToFixed(mbskTrx.getScbleX()),
                      XDoubleToFixed(mbskTrx.getShebrX()),
                      XDoubleToFixed(mbskTrx.getTrbnslbteX()),
                      XDoubleToFixed(mbskTrx.getShebrY()),
                      XDoubleToFixed(mbskTrx.getScbleY()),
                      XDoubleToFixed(mbskTrx.getTrbnslbteY()),
                      mbskWidth, mbskHeight, lbstMbskWidth, lbstMbskHeight,
                      sx, sy, dx, dy, w, h);
    }

    privbte stbtic nbtive void pbdBlitNbtive(byte op, int srcPict,
                                             int mbskPict, int dstPict,
                                             int m00, int m01, int m02,
                                             int m10, int m11, int m12,
                                             int mbskWidth, int mbskHeight,
                                             int lbstMbskWidth,
                                             int lbstMbskHeight,
                                             int sx, int sy, int dx, int dy,
                                             int w, int h);

    public void renderCompositeTrbpezoids(byte op, int src, int mbskFormbt,
                                          int dst, int srcX, int srcY,
                                          TrbpezoidList trbpList) {
        renderCompositeTrbpezoidsNbtive(op, src, getFormbtPtr(mbskFormbt),
                                        dst, srcX, srcY,
                                        trbpList.getTrbpArrby());
    }

    privbte stbtic nbtive void
        renderCompositeTrbpezoidsNbtive(byte op, int src, long mbskFormbt,
                                        int dst, int srcX, int srcY,
                                        int[] trbpezoids);
}
