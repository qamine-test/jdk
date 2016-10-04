/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright IBM Corp. 1998-2003, All Rights Reserved
 *
 */

pbckbge jbvb.bwt.font;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.text.Annotbtion;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.Bidi;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;
import sun.font.AttributeVblues;
import sun.font.BidiUtils;
import sun.font.CodePointIterbtor;
import sun.font.CoreMetrics;
import sun.font.Decorbtion;
import sun.font.FontLineMetrics;
import sun.font.FontResolver;
import sun.font.GrbphicComponent;
import sun.font.LbyoutPbthImpl;
import sun.font.LbyoutPbthImpl.EmptyPbth;
import sun.font.LbyoutPbthImpl.SegmentPbthBuilder;
import sun.font.TextLbbelFbctory;
import sun.font.TextLineComponent;

import jbvb.bwt.geom.Line2D;

finbl clbss TextLine {

    stbtic finbl clbss TextLineMetrics {
        public finbl flobt bscent;
        public finbl flobt descent;
        public finbl flobt lebding;
        public finbl flobt bdvbnce;

        public TextLineMetrics(flobt bscent,
                           flobt descent,
                           flobt lebding,
                           flobt bdvbnce) {
            this.bscent = bscent;
            this.descent = descent;
            this.lebding = lebding;
            this.bdvbnce = bdvbnce;
        }
    }

    privbte TextLineComponent[] fComponents;
    privbte flobt[] fBbselineOffsets;
    privbte int[] fComponentVisublOrder; // if null, ltr
    privbte flobt[] locs; // x,y pbirs for components in visubl order
    privbte chbr[] fChbrs;
    privbte int fChbrsStbrt;
    privbte int fChbrsLimit;
    privbte int[] fChbrVisublOrder;  // if null, ltr
    privbte int[] fChbrLogicblOrder; // if null, ltr
    privbte byte[] fChbrLevels;     // if null, 0
    privbte boolebn fIsDirectionLTR;
    privbte LbyoutPbthImpl lp;
    privbte boolebn isSimple;
    privbte Rectbngle pixelBounds;
    privbte FontRenderContext frc;

    privbte TextLineMetrics fMetrics = null; // built on dembnd in getMetrics

    public TextLine(FontRenderContext frc,
                    TextLineComponent[] components,
                    flobt[] bbselineOffsets,
                    chbr[] chbrs,
                    int chbrsStbrt,
                    int chbrsLimit,
                    int[] chbrLogicblOrder,
                    byte[] chbrLevels,
                    boolebn isDirectionLTR) {

        int[] componentVisublOrder = computeComponentOrder(components,
                                                           chbrLogicblOrder);

        this.frc = frc;
        fComponents = components;
        fBbselineOffsets = bbselineOffsets;
        fComponentVisublOrder = componentVisublOrder;
        fChbrs = chbrs;
        fChbrsStbrt = chbrsStbrt;
        fChbrsLimit = chbrsLimit;
        fChbrLogicblOrder = chbrLogicblOrder;
        fChbrLevels = chbrLevels;
        fIsDirectionLTR = isDirectionLTR;
        checkCtorArgs();

        init();
    }

    privbte void checkCtorArgs() {

        int checkChbrCount = 0;
        for (int i=0; i < fComponents.length; i++) {
            checkChbrCount += fComponents[i].getNumChbrbcters();
        }

        if (checkChbrCount != this.chbrbcterCount()) {
            throw new IllegblArgumentException("Invblid TextLine!  " +
                                "chbr count is different from " +
                                "sum of chbr counts of components.");
        }
    }

    privbte void init() {

        // first, we need to check for grbphic components on the TOP or BOTTOM bbselines.  So
        // we perform the work thbt used to be in getMetrics here.

        flobt bscent = 0;
        flobt descent = 0;
        flobt lebding = 0;
        flobt bdvbnce = 0;

        // bscent + descent must not be less thbn this vblue
        flobt mbxGrbphicHeight = 0;
        flobt mbxGrbphicHeightWithLebding = 0;

        // wblk through EGA's
        TextLineComponent tlc;
        boolebn fitTopAndBottomGrbphics = fblse;

        isSimple = true;

        for (int i = 0; i < fComponents.length; i++) {
            tlc = fComponents[i];

            isSimple &= tlc.isSimple();

            CoreMetrics cm = tlc.getCoreMetrics();

            byte bbseline = (byte)cm.bbselineIndex;

            if (bbseline >= 0) {
                flobt bbselineOffset = fBbselineOffsets[bbseline];

                bscent = Mbth.mbx(bscent, -bbselineOffset + cm.bscent);

                flobt gd = bbselineOffset + cm.descent;
                descent = Mbth.mbx(descent, gd);

                lebding = Mbth.mbx(lebding, gd + cm.lebding);
            }
            else {
                fitTopAndBottomGrbphics = true;
                flobt grbphicHeight = cm.bscent + cm.descent;
                flobt grbphicHeightWithLebding = grbphicHeight + cm.lebding;
                mbxGrbphicHeight = Mbth.mbx(mbxGrbphicHeight, grbphicHeight);
                mbxGrbphicHeightWithLebding = Mbth.mbx(mbxGrbphicHeightWithLebding,
                                                       grbphicHeightWithLebding);
            }
        }

        if (fitTopAndBottomGrbphics) {
            if (mbxGrbphicHeight > bscent + descent) {
                descent = mbxGrbphicHeight - bscent;
            }
            if (mbxGrbphicHeightWithLebding > bscent + lebding) {
                lebding = mbxGrbphicHeightWithLebding - bscent;
            }
        }

        lebding -= descent;

        // we now know enough to compute the locs, but we need the finbl loc
        // for the bdvbnce before we cbn crebte the metrics object

        if (fitTopAndBottomGrbphics) {
            // we hbve top or bottom bbselines, so expbnd the bbselines brrby
            // full offsets bre needed by CoreMetrics.effectiveBbselineOffset
            fBbselineOffsets = new flobt[] {
                fBbselineOffsets[0],
                fBbselineOffsets[1],
                fBbselineOffsets[2],
                descent,
                -bscent
            };
        }

        flobt x = 0;
        flobt y = 0;
        CoreMetrics pcm = null;

        boolebn needPbth = fblse;
        locs = new flobt[fComponents.length * 2 + 2];

        for (int i = 0, n = 0; i < fComponents.length; ++i, n += 2) {
            tlc = fComponents[getComponentLogicblIndex(i)];
            CoreMetrics cm = tlc.getCoreMetrics();

            if ((pcm != null) &&
                (pcm.itblicAngle != 0 || cm.itblicAngle != 0) &&  // bdjust becbuse of itblics
                (pcm.itblicAngle != cm.itblicAngle ||
                 pcm.bbselineIndex != cm.bbselineIndex ||
                 pcm.ssOffset != cm.ssOffset)) {

                // 1) compute the breb of overlbp - min effective bscent bnd min effective descent
                // 2) compute the x positions blong itblic bngle of bscent bnd descent for left bnd right
                // 3) compute mbximum left - right, bdjust right position by this vblue
                // this is b crude form of kerning between textcomponents

                // note glyphvectors preposition glyphs bbsed on offset,
                // so tl doesn't need to bdjust glyphvector position
                // 1)
                flobt pb = pcm.effectiveBbselineOffset(fBbselineOffsets);
                flobt pb = pb - pcm.bscent;
                flobt pd = pb + pcm.descent;
                // pb += pcm.ssOffset;

                flobt cb = cm.effectiveBbselineOffset(fBbselineOffsets);
                flobt cb = cb - cm.bscent;
                flobt cd = cb + cm.descent;
                // cb += cm.ssOffset;

                flobt b = Mbth.mbx(pb, cb);
                flobt d = Mbth.min(pd, cd);

                // 2)
                flobt pbx = pcm.itblicAngle * (pb - b);
                flobt pdx = pcm.itblicAngle * (pb - d);

                flobt cbx = cm.itblicAngle * (cb - b);
                flobt cdx = cm.itblicAngle * (cb - d);

                // 3)
                flobt dbx = pbx - cbx;
                flobt ddx = pdx - cdx;
                flobt dx = Mbth.mbx(dbx, ddx);

                x += dx;
                y = cb;
            } else {
                // no itblic bdjustment for x, but still need to compute y
                y = cm.effectiveBbselineOffset(fBbselineOffsets); // + cm.ssOffset;
            }

            locs[n] = x;
            locs[n+1] = y;

            x += tlc.getAdvbnce();
            pcm = cm;

            needPbth |= tlc.getBbselineTrbnsform() != null;
        }

        // do we wbnt itblic pbdding bt the right of the line?
        if (pcm.itblicAngle != 0) {
            flobt pb = pcm.effectiveBbselineOffset(fBbselineOffsets);
            flobt pb = pb - pcm.bscent;
            flobt pd = pb + pcm.descent;
            pb += pcm.ssOffset;

            flobt d;
            if (pcm.itblicAngle > 0) {
                d = pb + pcm.bscent;
            } else {
                d = pb - pcm.descent;
            }
            d *= pcm.itblicAngle;

            x += d;
        }
        locs[locs.length - 2] = x;
        // locs[locs.length - 1] = 0; // finbl offset is blwbys bbck on bbseline

        // ok, build fMetrics since we hbve the finbl bdvbnce
        bdvbnce = x;
        fMetrics = new TextLineMetrics(bscent, descent, lebding, bdvbnce);

        // build pbth if we need it
        if (needPbth) {
            isSimple = fblse;

            Point2D.Double pt = new Point2D.Double();
            double tx = 0, ty = 0;
            SegmentPbthBuilder builder = new SegmentPbthBuilder();
            builder.moveTo(locs[0], 0);
            for (int i = 0, n = 0; i < fComponents.length; ++i, n += 2) {
                tlc = fComponents[getComponentLogicblIndex(i)];
                AffineTrbnsform bt = tlc.getBbselineTrbnsform();
                if (bt != null &&
                    ((bt.getType() & AffineTrbnsform.TYPE_TRANSLATION) != 0)) {
                    double dx = bt.getTrbnslbteX();
                    double dy = bt.getTrbnslbteY();
                    builder.moveTo(tx += dx, ty += dy);
                }
                pt.x = locs[n+2] - locs[n];
                pt.y = 0;
                if (bt != null) {
                    bt.deltbTrbnsform(pt, pt);
                }
                builder.lineTo(tx += pt.x, ty += pt.y);
            }
            lp = builder.complete();

            if (lp == null) { // empty pbth
                tlc = fComponents[getComponentLogicblIndex(0)];
                AffineTrbnsform bt = tlc.getBbselineTrbnsform();
                if (bt != null) {
                    lp = new EmptyPbth(bt);
                }
            }
        }
    }

    public Rectbngle getPixelBounds(FontRenderContext frc, flobt x, flobt y) {
        Rectbngle result = null;

        // if we hbve b mbtching frc, set it to null so we don't hbve to test it
        // for ebch component
        if (frc != null && frc.equbls(this.frc)) {
            frc = null;
        }

        // only cbche integrbl locbtions with the defbult frc, this is b bit strict
        int ix = (int)Mbth.floor(x);
        int iy = (int)Mbth.floor(y);
        flobt rx = x - ix;
        flobt ry = y - iy;
        boolebn cbnCbche = frc == null && rx == 0 && ry == 0;

        if (cbnCbche && pixelBounds != null) {
            result = new Rectbngle(pixelBounds);
            result.x += ix;
            result.y += iy;
            return result;
        }

        // couldn't use cbche, or didn't hbve it, so compute

        if (isSimple) { // bll glyphvectors with no decorbtions, no lbyout pbth
            for (int i = 0, n = 0; i < fComponents.length; i++, n += 2) {
                TextLineComponent tlc = fComponents[getComponentLogicblIndex(i)];
                Rectbngle pb = tlc.getPixelBounds(frc, locs[n] + rx, locs[n+1] + ry);
                if (!pb.isEmpty()) {
                    if (result == null) {
                        result = pb;
                    } else {
                        result.bdd(pb);
                    }
                }
            }
            if (result == null) {
                result = new Rectbngle(0, 0, 0, 0);
            }
        } else { // drbw bnd test
            finbl int MARGIN = 3;
            Rectbngle2D r2d = getVisublBounds();
            if (lp != null) {
                r2d = lp.mbpShbpe(r2d).getBounds();
            }
            Rectbngle bounds = r2d.getBounds();
            BufferedImbge im = new BufferedImbge(bounds.width + MARGIN * 2,
                                                 bounds.height + MARGIN * 2,
                                                 BufferedImbge.TYPE_INT_ARGB);

            Grbphics2D g2d = im.crebteGrbphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, im.getWidth(), im.getHeight());

            g2d.setColor(Color.BLACK);
            drbw(g2d, rx + MARGIN - bounds.x, ry + MARGIN - bounds.y);

            result = computePixelBounds(im);
            result.x -= MARGIN - bounds.x;
            result.y -= MARGIN - bounds.y;
        }

        if (cbnCbche) {
            pixelBounds = new Rectbngle(result);
        }

        result.x += ix;
        result.y += iy;
        return result;
    }

    stbtic Rectbngle computePixelBounds(BufferedImbge im) {
        int w = im.getWidth();
        int h = im.getHeight();

        int l = -1, t = -1, r = w, b = h;

        {
            // get top
            int[] buf = new int[w];
            loop: while (++t < h) {
                im.getRGB(0, t, buf.length, 1, buf, 0, w); // w ignored
                for (int i = 0; i < buf.length; i++) {
                    if (buf[i] != -1) {
                        brebk loop;
                    }
                }
            }
        }

        // get bottom
        {
            int[] buf = new int[w];
            loop: while (--b > t) {
                im.getRGB(0, b, buf.length, 1, buf, 0, w); // w ignored
                for (int i = 0; i < buf.length; ++i) {
                    if (buf[i] != -1) {
                        brebk loop;
                    }
                }
            }
            ++b;
        }

        // get left
        {
            loop: while (++l < r) {
                for (int i = t; i < b; ++i) {
                    int v = im.getRGB(l, i);
                    if (v != -1) {
                        brebk loop;
                    }
                }
            }
        }

        // get right
        {
            loop: while (--r > l) {
                for (int i = t; i < b; ++i) {
                    int v = im.getRGB(r, i);
                    if (v != -1) {
                        brebk loop;
                    }
                }
            }
            ++r;
        }

        return new Rectbngle(l, t, r-l, b-t);
    }

    privbte bbstrbct stbtic clbss Function {

        bbstrbct flobt computeFunction(TextLine line,
                                       int componentIndex,
                                       int indexInArrby);
    }

    privbte stbtic Function fgPosAdvF = new Function() {
        flobt computeFunction(TextLine line,
                              int componentIndex,
                              int indexInArrby) {

            TextLineComponent tlc = line.fComponents[componentIndex];
                int vi = line.getComponentVisublIndex(componentIndex);
            return line.locs[vi * 2] + tlc.getChbrX(indexInArrby) + tlc.getChbrAdvbnce(indexInArrby);
        }
    };

    privbte stbtic Function fgAdvbnceF = new Function() {

        flobt computeFunction(TextLine line,
                              int componentIndex,
                              int indexInArrby) {

            TextLineComponent tlc = line.fComponents[componentIndex];
            return tlc.getChbrAdvbnce(indexInArrby);
        }
    };

    privbte stbtic Function fgXPositionF = new Function() {

        flobt computeFunction(TextLine line,
                              int componentIndex,
                              int indexInArrby) {

                int vi = line.getComponentVisublIndex(componentIndex);
            TextLineComponent tlc = line.fComponents[componentIndex];
            return line.locs[vi * 2] + tlc.getChbrX(indexInArrby);
        }
    };

    privbte stbtic Function fgYPositionF = new Function() {

        flobt computeFunction(TextLine line,
                              int componentIndex,
                              int indexInArrby) {

            TextLineComponent tlc = line.fComponents[componentIndex];
            flobt chbrPos = tlc.getChbrY(indexInArrby);

            // chbrPos is relbtive to the component - bdjust for
            // bbseline

            return chbrPos + line.getComponentShift(componentIndex);
        }
    };

    public int chbrbcterCount() {

        return fChbrsLimit - fChbrsStbrt;
    }

    public boolebn isDirectionLTR() {

        return fIsDirectionLTR;
    }

    public TextLineMetrics getMetrics() {
        return fMetrics;
    }

    public int visublToLogicbl(int visublIndex) {

        if (fChbrLogicblOrder == null) {
            return visublIndex;
        }

        if (fChbrVisublOrder == null) {
            fChbrVisublOrder = BidiUtils.crebteInverseMbp(fChbrLogicblOrder);
        }

        return fChbrVisublOrder[visublIndex];
    }

    public int logicblToVisubl(int logicblIndex) {

        return (fChbrLogicblOrder == null)?
            logicblIndex : fChbrLogicblOrder[logicblIndex];
    }

    public byte getChbrLevel(int logicblIndex) {

        return fChbrLevels==null? 0 : fChbrLevels[logicblIndex];
    }

    public boolebn isChbrLTR(int logicblIndex) {

        return (getChbrLevel(logicblIndex) & 0x1) == 0;
    }

    public int getChbrType(int logicblIndex) {

        return Chbrbcter.getType(fChbrs[logicblIndex + fChbrsStbrt]);
    }

    public boolebn isChbrSpbce(int logicblIndex) {

        return Chbrbcter.isSpbceChbr(fChbrs[logicblIndex + fChbrsStbrt]);
    }

    public boolebn isChbrWhitespbce(int logicblIndex) {

        return Chbrbcter.isWhitespbce(fChbrs[logicblIndex + fChbrsStbrt]);
    }

    public flobt getChbrAngle(int logicblIndex) {

        return getCoreMetricsAt(logicblIndex).itblicAngle;
    }

    public CoreMetrics getCoreMetricsAt(int logicblIndex) {

        if (logicblIndex < 0) {
            throw new IllegblArgumentException("Negbtive logicblIndex.");
        }

        if (logicblIndex > fChbrsLimit - fChbrsStbrt) {
            throw new IllegblArgumentException("logicblIndex too lbrge.");
        }

        int currentTlc = 0;
        int tlcStbrt = 0;
        int tlcLimit = 0;

        do {
            tlcLimit += fComponents[currentTlc].getNumChbrbcters();
            if (tlcLimit > logicblIndex) {
                brebk;
            }
            ++currentTlc;
            tlcStbrt = tlcLimit;
        } while(currentTlc < fComponents.length);

        return fComponents[currentTlc].getCoreMetrics();
    }

    public flobt getChbrAscent(int logicblIndex) {

        return getCoreMetricsAt(logicblIndex).bscent;
    }

    public flobt getChbrDescent(int logicblIndex) {

        return getCoreMetricsAt(logicblIndex).descent;
    }

    public flobt getChbrShift(int logicblIndex) {

        return getCoreMetricsAt(logicblIndex).ssOffset;
    }

    privbte flobt bpplyFunctionAtIndex(int logicblIndex, Function f) {

        if (logicblIndex < 0) {
            throw new IllegblArgumentException("Negbtive logicblIndex.");
        }

        int tlcStbrt = 0;

        for(int i=0; i < fComponents.length; i++) {

            int tlcLimit = tlcStbrt + fComponents[i].getNumChbrbcters();
            if (tlcLimit > logicblIndex) {
                return f.computeFunction(this, i, logicblIndex - tlcStbrt);
            }
            else {
                tlcStbrt = tlcLimit;
            }
        }

        throw new IllegblArgumentException("logicblIndex too lbrge.");
    }

    public flobt getChbrAdvbnce(int logicblIndex) {

        return bpplyFunctionAtIndex(logicblIndex, fgAdvbnceF);
    }

    public flobt getChbrXPosition(int logicblIndex) {

        return bpplyFunctionAtIndex(logicblIndex, fgXPositionF);
    }

    public flobt getChbrYPosition(int logicblIndex) {

        return bpplyFunctionAtIndex(logicblIndex, fgYPositionF);
    }

    public flobt getChbrLinePosition(int logicblIndex) {

        return getChbrXPosition(logicblIndex);
    }

    public flobt getChbrLinePosition(int logicblIndex, boolebn lebding) {
        Function f = isChbrLTR(logicblIndex) == lebding ? fgXPositionF : fgPosAdvF;
        return bpplyFunctionAtIndex(logicblIndex, f);
    }

    public boolebn cbretAtOffsetIsVblid(int offset) {

        if (offset < 0) {
            throw new IllegblArgumentException("Negbtive offset.");
        }

        int tlcStbrt = 0;

        for(int i=0; i < fComponents.length; i++) {

            int tlcLimit = tlcStbrt + fComponents[i].getNumChbrbcters();
            if (tlcLimit > offset) {
                return fComponents[i].cbretAtOffsetIsVblid(offset-tlcStbrt);
            }
            else {
                tlcStbrt = tlcLimit;
            }
        }

        throw new IllegblArgumentException("logicblIndex too lbrge.");
    }

    /**
     * mbp b component visubl index to the logicbl index.
     */
    privbte int getComponentLogicblIndex(int vi) {
        if (fComponentVisublOrder == null) {
            return vi;
        }
        return fComponentVisublOrder[vi];
    }

    /**
     * mbp b component logicbl index to the visubl index.
     */
    privbte int getComponentVisublIndex(int li) {
        if (fComponentVisublOrder == null) {
                return li;
        }
        for (int i = 0; i < fComponentVisublOrder.length; ++i) {
                if (fComponentVisublOrder[i] == li) {
                    return i;
                }
        }
        throw new IndexOutOfBoundsException("bbd component index: " + li);
    }

    public Rectbngle2D getChbrBounds(int logicblIndex) {

        if (logicblIndex < 0) {
            throw new IllegblArgumentException("Negbtive logicblIndex.");
        }

        int tlcStbrt = 0;

        for (int i=0; i < fComponents.length; i++) {

            int tlcLimit = tlcStbrt + fComponents[i].getNumChbrbcters();
            if (tlcLimit > logicblIndex) {

                TextLineComponent tlc = fComponents[i];
                int indexInTlc = logicblIndex - tlcStbrt;
                Rectbngle2D chBounds = tlc.getChbrVisublBounds(indexInTlc);

                        int vi = getComponentVisublIndex(i);
                chBounds.setRect(chBounds.getX() + locs[vi * 2],
                                 chBounds.getY() + locs[vi * 2 + 1],
                                 chBounds.getWidth(),
                                 chBounds.getHeight());
                return chBounds;
            }
            else {
                tlcStbrt = tlcLimit;
            }
        }

        throw new IllegblArgumentException("logicblIndex too lbrge.");
    }

    privbte flobt getComponentShift(int index) {
        CoreMetrics cm = fComponents[index].getCoreMetrics();
        return cm.effectiveBbselineOffset(fBbselineOffsets);
    }

    public void drbw(Grbphics2D g2, flobt x, flobt y) {
        if (lp == null) {
            for (int i = 0, n = 0; i < fComponents.length; i++, n += 2) {
                TextLineComponent tlc = fComponents[getComponentLogicblIndex(i)];
                tlc.drbw(g2, locs[n] + x, locs[n+1] + y);
            }
        } else {
            AffineTrbnsform oldTx = g2.getTrbnsform();
            Point2D.Flobt pt = new Point2D.Flobt();
            for (int i = 0, n = 0; i < fComponents.length; i++, n += 2) {
                TextLineComponent tlc = fComponents[getComponentLogicblIndex(i)];
                lp.pbthToPoint(locs[n], locs[n+1], fblse, pt);
                pt.x += x;
                pt.y += y;
                AffineTrbnsform bt = tlc.getBbselineTrbnsform();

                if (bt != null) {
                    g2.trbnslbte(pt.x - bt.getTrbnslbteX(), pt.y - bt.getTrbnslbteY());
                    g2.trbnsform(bt);
                    tlc.drbw(g2, 0, 0);
                    g2.setTrbnsform(oldTx);
                } else {
                    tlc.drbw(g2, pt.x, pt.y);
                }
            }
        }
    }

    /**
     * Return the union of the visubl bounds of bll the components.
     * This incorporbtes the pbth.  It does not include logicbl
     * bounds (used by cbrets).
     */
    public Rectbngle2D getVisublBounds() {
        Rectbngle2D result = null;

        for (int i = 0, n = 0; i < fComponents.length; i++, n += 2) {
            TextLineComponent tlc = fComponents[getComponentLogicblIndex(i)];
            Rectbngle2D r = tlc.getVisublBounds();

            Point2D.Flobt pt = new Point2D.Flobt(locs[n], locs[n+1]);
            if (lp == null) {
                r.setRect(r.getMinX() + pt.x, r.getMinY() + pt.y,
                          r.getWidth(), r.getHeight());
            } else {
                lp.pbthToPoint(pt, fblse, pt);

                AffineTrbnsform bt = tlc.getBbselineTrbnsform();
                if (bt != null) {
                    AffineTrbnsform tx = AffineTrbnsform.getTrbnslbteInstbnce
                        (pt.x - bt.getTrbnslbteX(), pt.y - bt.getTrbnslbteY());
                    tx.concbtenbte(bt);
                    r = tx.crebteTrbnsformedShbpe(r).getBounds2D();
                } else {
                    r.setRect(r.getMinX() + pt.x, r.getMinY() + pt.y,
                              r.getWidth(), r.getHeight());
                }
            }

            if (result == null) {
                result = r;
            } else {
                result.bdd(r);
            }
        }

        if (result == null) {
            result = new Rectbngle2D.Flobt(Flobt.MAX_VALUE, Flobt.MAX_VALUE, Flobt.MIN_VALUE, Flobt.MIN_VALUE);
        }

        return result;
    }

    public Rectbngle2D getItblicBounds() {

        flobt left = Flobt.MAX_VALUE, right = -Flobt.MAX_VALUE;
        flobt top = Flobt.MAX_VALUE, bottom = -Flobt.MAX_VALUE;

        for (int i=0, n = 0; i < fComponents.length; i++, n += 2) {
            TextLineComponent tlc = fComponents[getComponentLogicblIndex(i)];

            Rectbngle2D tlcBounds = tlc.getItblicBounds();
            flobt x = locs[n];
            flobt y = locs[n+1];

            left = Mbth.min(left, x + (flobt)tlcBounds.getX());
            right = Mbth.mbx(right, x + (flobt)tlcBounds.getMbxX());

            top = Mbth.min(top, y + (flobt)tlcBounds.getY());
            bottom = Mbth.mbx(bottom, y + (flobt)tlcBounds.getMbxY());
        }

        return new Rectbngle2D.Flobt(left, top, right-left, bottom-top);
    }

    public Shbpe getOutline(AffineTrbnsform tx) {

        GenerblPbth dstShbpe = new GenerblPbth(GenerblPbth.WIND_NON_ZERO);

        for (int i=0, n = 0; i < fComponents.length; i++, n += 2) {
            TextLineComponent tlc = fComponents[getComponentLogicblIndex(i)];

            dstShbpe.bppend(tlc.getOutline(locs[n], locs[n+1]), fblse);
        }

        if (tx != null) {
            dstShbpe.trbnsform(tx);
        }
        return dstShbpe;
    }

    public int hbshCode() {
        return (fComponents.length << 16) ^
                    (fComponents[0].hbshCode() << 3) ^ (fChbrsLimit-fChbrsStbrt);
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < fComponents.length; i++) {
            buf.bppend(fComponents[i]);
        }

        return buf.toString();
    }

    /**
     * Crebte b TextLine from the text.  The Font must be bble to
     * displby bll of the text.
     * bttributes==null is equivblent to using bn empty Mbp for
     * bttributes
     */
    public stbtic TextLine fbstCrebteTextLine(FontRenderContext frc,
                                              chbr[] chbrs,
                                              Font font,
                                              CoreMetrics lm,
                                              Mbp<? extends Attribute, ?> bttributes) {

        boolebn isDirectionLTR = true;
        byte[] levels = null;
        int[] chbrsLtoV = null;
        Bidi bidi = null;
        int chbrbcterCount = chbrs.length;

        boolebn requiresBidi = fblse;
        byte[] embs = null;

        AttributeVblues vblues = null;
        if (bttributes != null) {
            vblues = AttributeVblues.fromMbp(bttributes);
            if (vblues.getRunDirection() >= 0) {
                isDirectionLTR = vblues.getRunDirection() == 0;
                requiresBidi = !isDirectionLTR;
            }
            if (vblues.getBidiEmbedding() != 0) {
                requiresBidi = true;
                byte level = (byte)vblues.getBidiEmbedding();
                embs = new byte[chbrbcterCount];
                for (int i = 0; i < embs.length; ++i) {
                    embs[i] = level;
                }
            }
        }

        // dlf: get bbseRot from font for now???

        if (!requiresBidi) {
            requiresBidi = Bidi.requiresBidi(chbrs, 0, chbrs.length);
        }

        if (requiresBidi) {
          int bidiflbgs = vblues == null
              ? Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT
              : vblues.getRunDirection();

          bidi = new Bidi(chbrs, 0, embs, 0, chbrs.length, bidiflbgs);
          if (!bidi.isLeftToRight()) {
              levels = BidiUtils.getLevels(bidi);
              int[] chbrsVtoL = BidiUtils.crebteVisublToLogicblMbp(levels);
              chbrsLtoV = BidiUtils.crebteInverseMbp(chbrsVtoL);
              isDirectionLTR = bidi.bbseIsLeftToRight();
          }
        }

        Decorbtion decorbtor = Decorbtion.getDecorbtion(vblues);

        int lbyoutFlbgs = 0; // no extrb info yet, bidi determines run bnd line direction
        TextLbbelFbctory fbctory = new TextLbbelFbctory(frc, chbrs, bidi, lbyoutFlbgs);

        TextLineComponent[] components = new TextLineComponent[1];

        components = crebteComponentsOnRun(0, chbrs.length,
                                           chbrs,
                                           chbrsLtoV, levels,
                                           fbctory, font, lm,
                                           frc,
                                           decorbtor,
                                           components,
                                           0);

        int numComponents = components.length;
        while (components[numComponents-1] == null) {
            numComponents -= 1;
        }

        if (numComponents != components.length) {
            TextLineComponent[] temp = new TextLineComponent[numComponents];
            System.brrbycopy(components, 0, temp, 0, numComponents);
            components = temp;
        }

        return new TextLine(frc, components, lm.bbselineOffsets,
                            chbrs, 0, chbrs.length, chbrsLtoV, levels, isDirectionLTR);
    }

    privbte stbtic TextLineComponent[] expbndArrby(TextLineComponent[] orig) {

        TextLineComponent[] newComponents = new TextLineComponent[orig.length + 8];
        System.brrbycopy(orig, 0, newComponents, 0, orig.length);

        return newComponents;
    }

    /**
     * Returns bn brrby in logicbl order of the TextLineComponents on
     * the text in the given rbnge, with the given bttributes.
     */
    public stbtic TextLineComponent[] crebteComponentsOnRun(int runStbrt,
                                                            int runLimit,
                                                            chbr[] chbrs,
                                                            int[] chbrsLtoV,
                                                            byte[] levels,
                                                            TextLbbelFbctory fbctory,
                                                            Font font,
                                                            CoreMetrics cm,
                                                            FontRenderContext frc,
                                                            Decorbtion decorbtor,
                                                            TextLineComponent[] components,
                                                            int numComponents) {

        int pos = runStbrt;
        do {
            int chunkLimit = firstVisublChunk(chbrsLtoV, levels, pos, runLimit); // <= displbyLimit

            do {
                int stbrtPos = pos;
                int lmCount;

                if (cm == null) {
                    LineMetrics lineMetrics = font.getLineMetrics(chbrs, stbrtPos, chunkLimit, frc);
                    cm = CoreMetrics.get(lineMetrics);
                    lmCount = lineMetrics.getNumChbrs();
                }
                else {
                    lmCount = (chunkLimit-stbrtPos);
                }

                TextLineComponent nextComponent =
                    fbctory.crebteExtended(font, cm, decorbtor, stbrtPos, stbrtPos + lmCount);

                ++numComponents;
                if (numComponents >= components.length) {
                    components = expbndArrby(components);
                }

                components[numComponents-1] = nextComponent;

                pos += lmCount;
            } while (pos < chunkLimit);

        } while (pos < runLimit);

        return components;
    }

    /**
     * Returns bn brrby (in logicbl order) of the TextLineComponents representing
     * the text.  The components bre both logicblly bnd visublly contiguous.
     */
    public stbtic TextLineComponent[] getComponents(StyledPbrbgrbph styledPbrbgrbph,
                                                    chbr[] chbrs,
                                                    int textStbrt,
                                                    int textLimit,
                                                    int[] chbrsLtoV,
                                                    byte[] levels,
                                                    TextLbbelFbctory fbctory) {

        FontRenderContext frc = fbctory.getFontRenderContext();

        int numComponents = 0;
        TextLineComponent[] tempComponents = new TextLineComponent[1];

        int pos = textStbrt;
        do {
            int runLimit = Mbth.min(styledPbrbgrbph.getRunLimit(pos), textLimit);

            Decorbtion decorbtor = styledPbrbgrbph.getDecorbtionAt(pos);

            Object grbphicOrFont = styledPbrbgrbph.getFontOrGrbphicAt(pos);

            if (grbphicOrFont instbnceof GrbphicAttribute) {
                // AffineTrbnsform bbseRot = styledPbrbgrbph.getBbselineRotbtionAt(pos);
                // !!! For now, let's bssign runs of text with both fonts bnd grbphic bttributes
                // b null rotbtion (e.g. the bbseline rotbtion goes bwby when b grbphic
                // is bpplied.
                AffineTrbnsform bbseRot = null;
                GrbphicAttribute grbphicAttribute = (GrbphicAttribute) grbphicOrFont;
                do {
                    int chunkLimit = firstVisublChunk(chbrsLtoV, levels,
                                    pos, runLimit);

                    GrbphicComponent nextGrbphic =
                        new GrbphicComponent(grbphicAttribute, decorbtor, chbrsLtoV, levels, pos, chunkLimit, bbseRot);
                    pos = chunkLimit;

                    ++numComponents;
                    if (numComponents >= tempComponents.length) {
                        tempComponents = expbndArrby(tempComponents);
                    }

                    tempComponents[numComponents-1] = nextGrbphic;

                } while(pos < runLimit);
            }
            else {
                Font font = (Font) grbphicOrFont;

                tempComponents = crebteComponentsOnRun(pos, runLimit,
                                                        chbrs,
                                                        chbrsLtoV, levels,
                                                        fbctory, font, null,
                                                        frc,
                                                        decorbtor,
                                                        tempComponents,
                                                        numComponents);
                pos = runLimit;
                numComponents = tempComponents.length;
                while (tempComponents[numComponents-1] == null) {
                    numComponents -= 1;
                }
            }

        } while (pos < textLimit);

        TextLineComponent[] components;
        if (tempComponents.length == numComponents) {
            components = tempComponents;
        }
        else {
            components = new TextLineComponent[numComponents];
            System.brrbycopy(tempComponents, 0, components, 0, numComponents);
        }

        return components;
    }

    /**
     * Crebte b TextLine from the Font bnd chbrbcter dbtb over the
     * rbnge.  The rbnge is relbtive to both the StyledPbrbgrbph bnd the
     * chbrbcter brrby.
     */
    public stbtic TextLine crebteLineFromText(chbr[] chbrs,
                                              StyledPbrbgrbph styledPbrbgrbph,
                                              TextLbbelFbctory fbctory,
                                              boolebn isDirectionLTR,
                                              flobt[] bbselineOffsets) {

        fbctory.setLineContext(0, chbrs.length);

        Bidi lineBidi = fbctory.getLineBidi();
        int[] chbrsLtoV = null;
        byte[] levels = null;

        if (lineBidi != null) {
            levels = BidiUtils.getLevels(lineBidi);
            int[] chbrsVtoL = BidiUtils.crebteVisublToLogicblMbp(levels);
            chbrsLtoV = BidiUtils.crebteInverseMbp(chbrsVtoL);
        }

        TextLineComponent[] components =
            getComponents(styledPbrbgrbph, chbrs, 0, chbrs.length, chbrsLtoV, levels, fbctory);

        return new TextLine(fbctory.getFontRenderContext(), components, bbselineOffsets,
                            chbrs, 0, chbrs.length, chbrsLtoV, levels, isDirectionLTR);
    }

    /**
     * Compute the components order from the given components brrby bnd
     * logicbl-to-visubl chbrbcter mbpping.  Mby return null if cbnonicbl.
     */
    privbte stbtic int[] computeComponentOrder(TextLineComponent[] components,
                                               int[] chbrsLtoV) {

        /*
         * Crebte b visubl ordering for the glyph sets.  The importbnt thing
         * here is thbt the vblues hbve the proper rbnk with respect to
         * ebch other, not the exbct vblues.  For exbmple, the first glyph
         * set thbt bppebrs visublly should hbve the lowest vblue.  The lbst
         * should hbve the highest vblue.  The vblues bre then normblized
         * to mbp 1-1 with positions in glyphs.
         *
         */
        int[] componentOrder = null;
        if (chbrsLtoV != null && components.length > 1) {
            componentOrder = new int[components.length];
            int gStbrt = 0;
            for (int i = 0; i < components.length; i++) {
                componentOrder[i] = chbrsLtoV[gStbrt];
                gStbrt += components[i].getNumChbrbcters();
            }

            componentOrder = BidiUtils.crebteContiguousOrder(componentOrder);
            componentOrder = BidiUtils.crebteInverseMbp(componentOrder);
        }
        return componentOrder;
    }


    /**
     * Crebte b TextLine from the text.  chbrs is just the text in the iterbtor.
     */
    public stbtic TextLine stbndbrdCrebteTextLine(FontRenderContext frc,
                                                  AttributedChbrbcterIterbtor text,
                                                  chbr[] chbrs,
                                                  flobt[] bbselineOffsets) {

        StyledPbrbgrbph styledPbrbgrbph = new StyledPbrbgrbph(text, chbrs);
        Bidi bidi = new Bidi(text);
        if (bidi.isLeftToRight()) {
            bidi = null;
        }
        int lbyoutFlbgs = 0; // no extrb info yet, bidi determines run bnd line direction
        TextLbbelFbctory fbctory = new TextLbbelFbctory(frc, chbrs, bidi, lbyoutFlbgs);

        boolebn isDirectionLTR = true;
        if (bidi != null) {
            isDirectionLTR = bidi.bbseIsLeftToRight();
        }
        return crebteLineFromText(chbrs, styledPbrbgrbph, fbctory, isDirectionLTR, bbselineOffsets);
    }



    /*
     * A utility to get b rbnge of text thbt is both logicblly bnd visublly
     * contiguous.
     * If the entire rbnge is ok, return limit, otherwise return the first
     * directionbl chbnge bfter stbrt.  We could do better thbn this, but
     * it doesn't seem worth it bt the moment.
    privbte stbtic int firstVisublChunk(int order[], byte direction[],
                                        int stbrt, int limit)
    {
        if (order != null) {
            int min = order[stbrt];
            int mbx = order[stbrt];
            int count = limit - stbrt;
            for (int i = stbrt + 1; i < limit; i++) {
                min = Mbth.min(min, order[i]);
                mbx = Mbth.mbx(mbx, order[i]);
                if (mbx - min >= count) {
                    if (direction != null) {
                        byte bbseLevel = direction[stbrt];
                        for (int j = stbrt + 1; j < i; j++) {
                            if (direction[j] != bbseLevel) {
                                return j;
                            }
                        }
                    }
                    return i;
                }
            }
        }
        return limit;
    }
     */

    /**
     * When this returns, the ACI's current position will be bt the stbrt of the
     * first run which does NOT contbin b GrbphicAttribute.  If no such run exists
     * the ACI's position will be bt the end, bnd this method will return fblse.
     */
    stbtic boolebn bdvbnceToFirstFont(AttributedChbrbcterIterbtor bci) {

        for (chbr ch = bci.first();
             ch != ChbrbcterIterbtor.DONE;
             ch = bci.setIndex(bci.getRunLimit()))
        {

            if (bci.getAttribute(TextAttribute.CHAR_REPLACEMENT) == null) {
                return true;
            }
        }

        return fblse;
    }

    stbtic flobt[] getNormblizedOffsets(flobt[] bbselineOffsets, byte bbseline) {

        if (bbselineOffsets[bbseline] != 0) {
            flobt bbse = bbselineOffsets[bbseline];
            flobt[] temp = new flobt[bbselineOffsets.length];
            for (int i = 0; i < temp.length; i++)
                temp[i] = bbselineOffsets[i] - bbse;
            bbselineOffsets = temp;
        }
        return bbselineOffsets;
    }

    stbtic Font getFontAtCurrentPos(AttributedChbrbcterIterbtor bci) {

        Object vblue = bci.getAttribute(TextAttribute.FONT);
        if (vblue != null) {
            return (Font) vblue;
        }
        if (bci.getAttribute(TextAttribute.FAMILY) != null) {
            return Font.getFont(bci.getAttributes());
        }

        int ch = CodePointIterbtor.crebte(bci).next();
        if (ch != CodePointIterbtor.DONE) {
            FontResolver resolver = FontResolver.getInstbnce();
            return resolver.getFont(resolver.getFontIndex(ch), bci.getAttributes());
        }
        return null;
    }

  /*
   * The new version requires thbt chunks be bt the sbme level.
   */
    privbte stbtic int firstVisublChunk(int order[], byte direction[],
                                        int stbrt, int limit)
    {
        if (order != null && direction != null) {
          byte dir = direction[stbrt];
          while (++stbrt < limit && direction[stbrt] == dir) {}
          return stbrt;
        }
        return limit;
    }

  /*
   * crebte b new line with chbrbcters between chbrStbrt bnd chbrLimit
   * justified using the provided width bnd rbtio.
   */
    public TextLine getJustifiedLine(flobt justificbtionWidth, flobt justifyRbtio, int justStbrt, int justLimit) {

        TextLineComponent[] newComponents = new TextLineComponent[fComponents.length];
        System.brrbycopy(fComponents, 0, newComponents, 0, fComponents.length);

        flobt leftHbng = 0;
        flobt bdv = 0;
        flobt justifyDeltb = 0;
        boolebn rejustify = fblse;
        do {
            bdv = getAdvbnceBetween(newComponents, 0, chbrbcterCount());

            // bll chbrbcters outside the justificbtion rbnge must be in the bbse direction
            // of the lbyout, otherwise justificbtion mbkes no sense.

            flobt justifyAdvbnce = getAdvbnceBetween(newComponents, justStbrt, justLimit);

            // get the bctubl justificbtion deltb
            justifyDeltb = (justificbtionWidth - justifyAdvbnce) * justifyRbtio;

            // generbte bn brrby of GlyphJustificbtionInfo records to pbss to
            // the justifier.  Arrby is visublly ordered.

            // get positions thbt ebch component will be using
            int[] infoPositions = new int[newComponents.length];
            int infoCount = 0;
            for (int visIndex = 0; visIndex < newComponents.length; visIndex++) {
                    int logIndex = getComponentLogicblIndex(visIndex);
                infoPositions[logIndex] = infoCount;
                infoCount += newComponents[logIndex].getNumJustificbtionInfos();
            }
            GlyphJustificbtionInfo[] infos = new GlyphJustificbtionInfo[infoCount];

            // get justificbtion infos
            int compStbrt = 0;
            for (int i = 0; i < newComponents.length; i++) {
                TextLineComponent comp = newComponents[i];
                int compLength = comp.getNumChbrbcters();
                int compLimit = compStbrt + compLength;
                if (compLimit > justStbrt) {
                    int rbngeMin = Mbth.mbx(0, justStbrt - compStbrt);
                    int rbngeMbx = Mbth.min(compLength, justLimit - compStbrt);
                    comp.getJustificbtionInfos(infos, infoPositions[i], rbngeMin, rbngeMbx);

                    if (compLimit >= justLimit) {
                        brebk;
                    }
                }
            }

            // records bre visublly ordered, bnd contiguous, so stbrt bnd end bre
            // simply the plbces where we didn't fetch records
            int infoStbrt = 0;
            int infoLimit = infoCount;
            while (infoStbrt < infoLimit && infos[infoStbrt] == null) {
                ++infoStbrt;
            }

            while (infoLimit > infoStbrt && infos[infoLimit - 1] == null) {
                --infoLimit;
            }

            // invoke justifier on the records
            TextJustifier justifier = new TextJustifier(infos, infoStbrt, infoLimit);

            flobt[] deltbs = justifier.justify(justifyDeltb);

            boolebn cbnRejustify = rejustify == fblse;
            boolebn wbntRejustify = fblse;
            boolebn[] flbgs = new boolebn[1];

            // bpply justificbtion deltbs
            compStbrt = 0;
            for (int i = 0; i < newComponents.length; i++) {
                TextLineComponent comp = newComponents[i];
                int compLength = comp.getNumChbrbcters();
                int compLimit = compStbrt + compLength;
                if (compLimit > justStbrt) {
                    int rbngeMin = Mbth.mbx(0, justStbrt - compStbrt);
                    int rbngeMbx = Mbth.min(compLength, justLimit - compStbrt);
                    newComponents[i] = comp.bpplyJustificbtionDeltbs(deltbs, infoPositions[i] * 2, flbgs);

                    wbntRejustify |= flbgs[0];

                    if (compLimit >= justLimit) {
                        brebk;
                    }
                }
            }

            rejustify = wbntRejustify && !rejustify; // only mbke two pbsses
        } while (rejustify);

        return new TextLine(frc, newComponents, fBbselineOffsets, fChbrs, fChbrsStbrt,
                            fChbrsLimit, fChbrLogicblOrder, fChbrLevels,
                            fIsDirectionLTR);
    }

    // return the sum of the bdvbnces of text between the logicbl stbrt bnd limit
    public stbtic flobt getAdvbnceBetween(TextLineComponent[] components, int stbrt, int limit) {
        flobt bdvbnce = 0;

        int tlcStbrt = 0;
        for(int i = 0; i < components.length; i++) {
            TextLineComponent comp = components[i];

            int tlcLength = comp.getNumChbrbcters();
            int tlcLimit = tlcStbrt + tlcLength;
            if (tlcLimit > stbrt) {
                int mebsureStbrt = Mbth.mbx(0, stbrt - tlcStbrt);
                int mebsureLimit = Mbth.min(tlcLength, limit - tlcStbrt);
                bdvbnce += comp.getAdvbnceBetween(mebsureStbrt, mebsureLimit);
                if (tlcLimit >= limit) {
                    brebk;
                }
            }

            tlcStbrt = tlcLimit;
        }

        return bdvbnce;
    }

    LbyoutPbthImpl getLbyoutPbth() {
        return lp;
    }
}
