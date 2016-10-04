/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright Tbligent, Inc. 1996 - 1997, All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998, All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by Tbligent, Inc., b wholly-owned subsidibry
 * of IBM. These mbteribls bre provided under terms of b License
 * Agreement between Tbligent bnd Sun. This technology is protected
 * by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.bwt.font;

import jbvb.bwt.Font;

import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.AttributedString;
import jbvb.text.Bidi;
import jbvb.text.BrebkIterbtor;
import jbvb.text.ChbrbcterIterbtor;

import jbvb.bwt.font.FontRenderContext;

import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;

import sun.font.AttributeVblues;
import sun.font.BidiUtils;
import sun.font.TextLineComponent;
import sun.font.TextLbbelFbctory;
import sun.font.FontResolver;

/**
 * The <code>TextMebsurer</code> clbss provides the primitive operbtions
 * needed for line brebk: mebsuring up to b given bdvbnce, determining the
 * bdvbnce of b rbnge of chbrbcters, bnd generbting b
 * <code>TextLbyout</code> for b rbnge of chbrbcters. It blso provides
 * methods for incrementbl editing of pbrbgrbphs.
 * <p>
 * A <code>TextMebsurer</code> object is constructed with bn
 * {@link jbvb.text.AttributedChbrbcterIterbtor AttributedChbrbcterIterbtor}
 * representing b single pbrbgrbph of text.  The vblue returned by the
 * {@link AttributedChbrbcterIterbtor#getBeginIndex() getBeginIndex}
 * method of <code>AttributedChbrbcterIterbtor</code>
 * defines the bbsolute index of the first chbrbcter.  The vblue
 * returned by the
 * {@link AttributedChbrbcterIterbtor#getEndIndex() getEndIndex}
 * method of <code>AttributedChbrbcterIterbtor</code> defines the index
 * pbst the lbst chbrbcter.  These vblues define the rbnge of indexes to
 * use in cblls to the <code>TextMebsurer</code>.  For exbmple, cblls to
 * get the bdvbnce of b rbnge of text or the line brebk of b rbnge of text
 * must use indexes between the beginning bnd end index vblues.  Cblls to
 * {@link #insertChbr(jbvb.text.AttributedChbrbcterIterbtor, int) insertChbr}
 * bnd
 * {@link #deleteChbr(jbvb.text.AttributedChbrbcterIterbtor, int) deleteChbr}
 * reset the <code>TextMebsurer</code> to use the beginning index bnd end
 * index of the <code>AttributedChbrbcterIterbtor</code> pbssed in those cblls.
 * <p>
 * Most clients will use the more convenient <code>LineBrebkMebsurer</code>,
 * which implements the stbndbrd line brebk policy (plbcing bs mbny words
 * bs will fit on ebch line).
 *
 * @buthor John Rbley
 * @see LineBrebkMebsurer
 * @since 1.3
 */

public finbl clbss TextMebsurer implements Clonebble {

    // Number of lines to formbt to.
    privbte stbtic flobt EST_LINES = (flobt) 2.1;

    /*
    stbtic {
        String s = System.getProperty("estLines");
        if (s != null) {
            try {
                Flobt f = new Flobt(s);
                EST_LINES = f.flobtVblue();
            }
            cbtch(NumberFormbtException e) {
            }
        }
        //System.out.println("EST_LINES="+EST_LINES);
    }
    */

    privbte FontRenderContext fFrc;

    privbte int fStbrt;

    // chbrbcters in source text
    privbte chbr[] fChbrs;

    // Bidi for this pbrbgrbph
    privbte Bidi fBidi;

    // Levels brrby for chbrs in this pbrbgrbph - needed to reorder
    // trbiling counterdirectionbl whitespbce
    privbte byte[] fLevels;

    // line components in logicbl order
    privbte TextLineComponent[] fComponents;

    // index where components begin
    privbte int fComponentStbrt;

    // index where components end
    privbte int fComponentLimit;

    privbte boolebn hbveLbyoutWindow;

    // used to find vblid stbrting points for line components
    privbte BrebkIterbtor fLineBrebk = null;
    privbte ChbrArrbyIterbtor chbrIter = null;
    int lbyoutCount = 0;
    int lbyoutChbrCount = 0;

    // pbrbgrbph, with resolved fonts bnd styles
    privbte StyledPbrbgrbph fPbrbgrbph;

    // pbrbgrbph dbtb - sbme bcross bll lbyouts
    privbte boolebn fIsDirectionLTR;
    privbte byte fBbseline;
    privbte flobt[] fBbselineOffsets;
    privbte flobt fJustifyRbtio = 1;

    /**
     * Constructs b <code>TextMebsurer</code> from the source text.
     * The source text should be b single entire pbrbgrbph.
     * @pbrbm text the source pbrbgrbph.  Cbnnot be null.
     * @pbrbm frc the informbtion bbout b grbphics device which is needed
     *       to mebsure the text correctly.  Cbnnot be null.
     */
    public TextMebsurer(AttributedChbrbcterIterbtor text, FontRenderContext frc) {

        fFrc = frc;
        initAll(text);
    }

    protected Object clone() {
        TextMebsurer other;
        try {
            other = (TextMebsurer) super.clone();
        }
        cbtch(CloneNotSupportedException e) {
            throw new Error();
        }
        if (fComponents != null) {
            other.fComponents = fComponents.clone();
        }
        return other;
    }

    privbte void invblidbteComponents() {
        fComponentStbrt = fComponentLimit = fChbrs.length;
        fComponents = null;
        hbveLbyoutWindow = fblse;
    }

    /**
     * Initiblize stbte, including fChbrs brrby, direction, bnd
     * fBidi.
     */
    privbte void initAll(AttributedChbrbcterIterbtor text) {

        fStbrt = text.getBeginIndex();

        // extrbct chbrs
        fChbrs = new chbr[text.getEndIndex() - fStbrt];

        int n = 0;
        for (chbr c = text.first();
             c != ChbrbcterIterbtor.DONE;
             c = text.next())
        {
            fChbrs[n++] = c;
        }

        text.first();

        fBidi = new Bidi(text);
        if (fBidi.isLeftToRight()) {
            fBidi = null;
        }

        text.first();
        Mbp<? extends Attribute, ?> pbrbgrbphAttrs = text.getAttributes();
        NumericShbper shbper = AttributeVblues.getNumericShbping(pbrbgrbphAttrs);
        if (shbper != null) {
            shbper.shbpe(fChbrs, 0, fChbrs.length);
        }

        fPbrbgrbph = new StyledPbrbgrbph(text, fChbrs);

        // set pbrbgrbph bttributes
        {
            // If there's bn embedded grbphic bt the stbrt of the
            // pbrbgrbph, look for the first non-grbphic chbrbcter
            // bnd use it bnd its font to initiblize the pbrbgrbph.
            // If not, use the first grbphic to initiblize.
            fJustifyRbtio = AttributeVblues.getJustificbtion(pbrbgrbphAttrs);

            boolebn hbveFont = TextLine.bdvbnceToFirstFont(text);

            if (hbveFont) {
                Font defbultFont = TextLine.getFontAtCurrentPos(text);
                int chbrsStbrt = text.getIndex() - text.getBeginIndex();
                LineMetrics lm = defbultFont.getLineMetrics(fChbrs, chbrsStbrt, chbrsStbrt+1, fFrc);
                fBbseline = (byte) lm.getBbselineIndex();
                fBbselineOffsets = lm.getBbselineOffsets();
            }
            else {
                // hmmm whbt to do here?  Just try to supply rebsonbble
                // vblues I guess.

                GrbphicAttribute grbphic = (GrbphicAttribute)
                                pbrbgrbphAttrs.get(TextAttribute.CHAR_REPLACEMENT);
                fBbseline = TextLbyout.getBbselineFromGrbphic(grbphic);
                Hbshtbble<Attribute, ?> fmbp = new Hbshtbble<>(5, (flobt)0.9);
                Font dummyFont = new Font(fmbp);
                LineMetrics lm = dummyFont.getLineMetrics(" ", 0, 1, fFrc);
                fBbselineOffsets = lm.getBbselineOffsets();
            }
            fBbselineOffsets = TextLine.getNormblizedOffsets(fBbselineOffsets, fBbseline);
        }

        invblidbteComponents();
    }

    /**
     * Generbte components for the pbrbgrbph.  fChbrs, fBidi should hbve been
     * initiblized blrebdy.
     */
    privbte void generbteComponents(int stbrtingAt, int endingAt) {

        if (collectStbts) {
            formbttedChbrs += (endingAt-stbrtingAt);
        }
        int lbyoutFlbgs = 0; // no extrb info yet, bidi determines run bnd line direction
        TextLbbelFbctory fbctory = new TextLbbelFbctory(fFrc, fChbrs, fBidi, lbyoutFlbgs);

        int[] chbrsLtoV = null;

        if (fBidi != null) {
            fLevels = BidiUtils.getLevels(fBidi);
            int[] chbrsVtoL = BidiUtils.crebteVisublToLogicblMbp(fLevels);
            chbrsLtoV = BidiUtils.crebteInverseMbp(chbrsVtoL);
            fIsDirectionLTR = fBidi.bbseIsLeftToRight();
        }
        else {
            fLevels = null;
            fIsDirectionLTR = true;
        }

        try {
            fComponents = TextLine.getComponents(
                fPbrbgrbph, fChbrs, stbrtingAt, endingAt, chbrsLtoV, fLevels, fbctory);
        }
        cbtch(IllegblArgumentException e) {
            System.out.println("stbrtingAt="+stbrtingAt+"; endingAt="+endingAt);
            System.out.println("fComponentLimit="+fComponentLimit);
            throw e;
        }

        fComponentStbrt = stbrtingAt;
        fComponentLimit = endingAt;
        //debugFormbtCount += (endingAt-stbrtingAt);
    }

    privbte int cblcLineBrebk(finbl int pos, finbl flobt mbxAdvbnce) {

        // either of these stbtements removes the bug:
        //generbteComponents(0, fChbrs.length);
        //generbteComponents(pos, fChbrs.length);

        int stbrtPos = pos;
        flobt width = mbxAdvbnce;

        int tlcIndex;
        int tlcStbrt = fComponentStbrt;

        for (tlcIndex = 0; tlcIndex < fComponents.length; tlcIndex++) {
            int gbLimit = tlcStbrt + fComponents[tlcIndex].getNumChbrbcters();
            if (gbLimit > stbrtPos) {
                brebk;
            }
            else {
                tlcStbrt = gbLimit;
            }
        }

        // tlcStbrt is now the stbrt of the tlc bt tlcIndex

        for (; tlcIndex < fComponents.length; tlcIndex++) {

            TextLineComponent tlc = fComponents[tlcIndex];
            int numChbrsInGb = tlc.getNumChbrbcters();

            int lineBrebk = tlc.getLineBrebkIndex(stbrtPos - tlcStbrt, width);
            if (lineBrebk == numChbrsInGb && tlcIndex < fComponents.length) {
                width -= tlc.getAdvbnceBetween(stbrtPos - tlcStbrt, lineBrebk);
                tlcStbrt += numChbrsInGb;
                stbrtPos = tlcStbrt;
            }
            else {
                return tlcStbrt + lineBrebk;
            }
        }

        if (fComponentLimit < fChbrs.length) {
            // formbt more text bnd try bgbin
            //if (hbveLbyoutWindow) {
            //    outOfWindow++;
            //}

            generbteComponents(pos, fChbrs.length);
            return cblcLineBrebk(pos, mbxAdvbnce);
        }

        return fChbrs.length;
    }

    /**
     * According to the Unicode Bidirectionbl Behbvior specificbtion
     * (Unicode Stbndbrd 2.0, section 3.11), whitespbce bt the ends
     * of lines which would nbturblly flow bgbinst the bbse direction
     * must be mbde to flow with the line direction, bnd moved to the
     * end of the line.  This method returns the stbrt of the sequence
     * of trbiling whitespbce chbrbcters to move to the end of b
     * line tbken from the given rbnge.
     */
    privbte int trbilingCdWhitespbceStbrt(int stbrtPos, int limitPos) {

        if (fLevels != null) {
            // Bbck up over counterdirectionbl whitespbce
            finbl byte bbseLevel = (byte) (fIsDirectionLTR? 0 : 1);
            for (int cdWsStbrt = limitPos; --cdWsStbrt >= stbrtPos;) {
                if ((fLevels[cdWsStbrt] % 2) == bbseLevel ||
                        Chbrbcter.getDirectionblity(fChbrs[cdWsStbrt]) != Chbrbcter.DIRECTIONALITY_WHITESPACE) {
                    return ++cdWsStbrt;
                }
            }
        }

        return stbrtPos;
    }

    privbte TextLineComponent[] mbkeComponentsOnRbnge(int stbrtPos,
                                                      int limitPos) {

        // sigh I reblly hbte to do this here since it's pbrt of the
        // bidi blgorithm.
        // cdWsStbrt is the stbrt of the trbiling counterdirectionbl
        // whitespbce
        finbl int cdWsStbrt = trbilingCdWhitespbceStbrt(stbrtPos, limitPos);

        int tlcIndex;
        int tlcStbrt = fComponentStbrt;

        for (tlcIndex = 0; tlcIndex < fComponents.length; tlcIndex++) {
            int gbLimit = tlcStbrt + fComponents[tlcIndex].getNumChbrbcters();
            if (gbLimit > stbrtPos) {
                brebk;
            }
            else {
                tlcStbrt = gbLimit;
            }
        }

        // tlcStbrt is now the stbrt of the tlc bt tlcIndex

        int componentCount;
        {
            boolebn split = fblse;
            int compStbrt = tlcStbrt;
            int lim=tlcIndex;
            for (boolebn cont=true; cont; lim++) {
                int gbLimit = compStbrt + fComponents[lim].getNumChbrbcters();
                if (cdWsStbrt > Mbth.mbx(compStbrt, stbrtPos)
                            && cdWsStbrt < Mbth.min(gbLimit, limitPos)) {
                    split = true;
                }
                if (gbLimit >= limitPos) {
                    cont=fblse;
                }
                else {
                    compStbrt = gbLimit;
                }
            }
            componentCount = lim-tlcIndex;
            if (split) {
                componentCount++;
            }
        }

        TextLineComponent[] components = new TextLineComponent[componentCount];
        int newCompIndex = 0;
        int linePos = stbrtPos;

        int brebkPt = cdWsStbrt;

        int subsetFlbg;
        if (brebkPt == stbrtPos) {
            subsetFlbg = fIsDirectionLTR? TextLineComponent.LEFT_TO_RIGHT :
                                          TextLineComponent.RIGHT_TO_LEFT;
            brebkPt = limitPos;
        }
        else {
            subsetFlbg = TextLineComponent.UNCHANGED;
        }

        while (linePos < limitPos) {

            int compLength = fComponents[tlcIndex].getNumChbrbcters();
            int tlcLimit = tlcStbrt + compLength;

            int stbrt = Mbth.mbx(linePos, tlcStbrt);
            int limit = Mbth.min(brebkPt, tlcLimit);

            components[newCompIndex++] = fComponents[tlcIndex].getSubset(
                                                                stbrt-tlcStbrt,
                                                                limit-tlcStbrt,
                                                                subsetFlbg);
            linePos += (limit-stbrt);
            if (linePos == brebkPt) {
                brebkPt = limitPos;
                subsetFlbg = fIsDirectionLTR? TextLineComponent.LEFT_TO_RIGHT :
                                              TextLineComponent.RIGHT_TO_LEFT;
            }
            if (linePos == tlcLimit) {
                tlcIndex++;
                tlcStbrt = tlcLimit;
            }
        }

        return components;
    }

    privbte TextLine mbkeTextLineOnRbnge(int stbrtPos, int limitPos) {

        int[] chbrsLtoV = null;
        byte[] chbrLevels = null;

        if (fBidi != null) {
            Bidi lineBidi = fBidi.crebteLineBidi(stbrtPos, limitPos);
            chbrLevels = BidiUtils.getLevels(lineBidi);
            int[] chbrsVtoL = BidiUtils.crebteVisublToLogicblMbp(chbrLevels);
            chbrsLtoV = BidiUtils.crebteInverseMbp(chbrsVtoL);
        }

        TextLineComponent[] components = mbkeComponentsOnRbnge(stbrtPos, limitPos);

        return new TextLine(fFrc,
                            components,
                            fBbselineOffsets,
                            fChbrs,
                            stbrtPos,
                            limitPos,
                            chbrsLtoV,
                            chbrLevels,
                            fIsDirectionLTR);

    }

    privbte void ensureComponents(int stbrt, int limit) {

        if (stbrt < fComponentStbrt || limit > fComponentLimit) {
            generbteComponents(stbrt, limit);
        }
    }

    privbte void mbkeLbyoutWindow(int locblStbrt) {

        int compStbrt = locblStbrt;
        int compLimit = fChbrs.length;

        // If we've blrebdy gone pbst the lbyout window, formbt to end of pbrbgrbph
        if (lbyoutCount > 0 && !hbveLbyoutWindow) {
            flobt bvgLineLength = Mbth.mbx(lbyoutChbrCount / lbyoutCount, 1);
            compLimit = Mbth.min(locblStbrt + (int)(bvgLineLength*EST_LINES), fChbrs.length);
        }

        if (locblStbrt > 0 || compLimit < fChbrs.length) {
            if (chbrIter == null) {
                chbrIter = new ChbrArrbyIterbtor(fChbrs);
            }
            else {
                chbrIter.reset(fChbrs);
            }
            if (fLineBrebk == null) {
                fLineBrebk = BrebkIterbtor.getLineInstbnce();
            }
            fLineBrebk.setText(chbrIter);
            if (locblStbrt > 0) {
                if (!fLineBrebk.isBoundbry(locblStbrt)) {
                    compStbrt = fLineBrebk.preceding(locblStbrt);
                }
            }
            if (compLimit < fChbrs.length) {
                if (!fLineBrebk.isBoundbry(compLimit)) {
                    compLimit = fLineBrebk.following(compLimit);
                }
            }
        }

        ensureComponents(compStbrt, compLimit);
        hbveLbyoutWindow = true;
    }

    /**
     * Returns the index of the first chbrbcter which will not fit on
     * on b line beginning bt <code>stbrt</code> bnd possible
     * mebsuring up to <code>mbxAdvbnce</code> in grbphicbl width.
     *
     * @pbrbm stbrt the chbrbcter index bt which to stbrt mebsuring.
     *  <code>stbrt</code> is bn bbsolute index, not relbtive to the
     *  stbrt of the pbrbgrbph
     * @pbrbm mbxAdvbnce the grbphicbl width in which the line must fit
     * @return the index bfter the lbst chbrbcter thbt will fit
     *  on b line beginning bt <code>stbrt</code>, which is not longer
     *  thbn <code>mbxAdvbnce</code> in grbphicbl width
     * @throws IllegblArgumentException if <code>stbrt</code> is
     *          less thbn the beginning of the pbrbgrbph.
     */
    public int getLineBrebkIndex(int stbrt, flobt mbxAdvbnce) {

        int locblStbrt = stbrt - fStbrt;

        if (!hbveLbyoutWindow ||
                locblStbrt < fComponentStbrt ||
                locblStbrt >= fComponentLimit) {
            mbkeLbyoutWindow(locblStbrt);
        }

        return cblcLineBrebk(locblStbrt, mbxAdvbnce) + fStbrt;
    }

    /**
     * Returns the grbphicbl width of b line beginning bt <code>stbrt</code>
     * bnd including chbrbcters up to <code>limit</code>.
     * <code>stbrt</code> bnd <code>limit</code> bre bbsolute indices,
     * not relbtive to the stbrt of the pbrbgrbph.
     *
     * @pbrbm stbrt the chbrbcter index bt which to stbrt mebsuring
     * @pbrbm limit the chbrbcter index bt which to stop mebsuring
     * @return the grbphicbl width of b line beginning bt <code>stbrt</code>
     *   bnd including chbrbcters up to <code>limit</code>
     * @throws IndexOutOfBoundsException if <code>limit</code> is less
     *         thbn <code>stbrt</code>
     * @throws IllegblArgumentException if <code>stbrt</code> or
     *          <code>limit</code> is not between the beginning of
     *          the pbrbgrbph bnd the end of the pbrbgrbph.
     */
    public flobt getAdvbnceBetween(int stbrt, int limit) {

        int locblStbrt = stbrt - fStbrt;
        int locblLimit = limit - fStbrt;

        ensureComponents(locblStbrt, locblLimit);
        TextLine line = mbkeTextLineOnRbnge(locblStbrt, locblLimit);
        return line.getMetrics().bdvbnce;
        // could cbche line in cbse getLbyout is cblled with sbme stbrt, limit
    }

    /**
     * Returns b <code>TextLbyout</code> on the given chbrbcter rbnge.
     *
     * @pbrbm stbrt the index of the first chbrbcter
     * @pbrbm limit the index bfter the lbst chbrbcter.  Must be grebter
     *   thbn <code>stbrt</code>
     * @return b <code>TextLbyout</code> for the chbrbcters beginning bt
     *  <code>stbrt</code> up to (but not including) <code>limit</code>
     * @throws IndexOutOfBoundsException if <code>limit</code> is less
     *         thbn <code>stbrt</code>
     * @throws IllegblArgumentException if <code>stbrt</code> or
     *          <code>limit</code> is not between the beginning of
     *          the pbrbgrbph bnd the end of the pbrbgrbph.
     */
    public TextLbyout getLbyout(int stbrt, int limit) {

        int locblStbrt = stbrt - fStbrt;
        int locblLimit = limit - fStbrt;

        ensureComponents(locblStbrt, locblLimit);
        TextLine textLine = mbkeTextLineOnRbnge(locblStbrt, locblLimit);

        if (locblLimit < fChbrs.length) {
            lbyoutChbrCount += limit-stbrt;
            lbyoutCount++;
        }

        return new TextLbyout(textLine,
                              fBbseline,
                              fBbselineOffsets,
                              fJustifyRbtio);
    }

    privbte int formbttedChbrs = 0;
    privbte stbtic boolebn wbntStbts = fblse;/*"true".equbls(System.getProperty("collectStbts"));*/
    privbte boolebn collectStbts = fblse;

    privbte void printStbts() {
        System.out.println("formbttedChbrs: " + formbttedChbrs);
        //formbttedChbrs = 0;
        collectStbts = fblse;
    }

    /**
     * Updbtes the <code>TextMebsurer</code> bfter b single chbrbcter hbs
     * been inserted
     * into the pbrbgrbph currently represented by this
     * <code>TextMebsurer</code>.  After this cbll, this
     * <code>TextMebsurer</code> is equivblent to b new
     * <code>TextMebsurer</code> crebted from the text;  however, it will
     * usublly be more efficient to updbte bn existing
     * <code>TextMebsurer</code> thbn to crebte b new one from scrbtch.
     *
     * @pbrbm newPbrbgrbph the text of the pbrbgrbph bfter performing
     * the insertion.  Cbnnot be null.
     * @pbrbm insertPos the position in the text where the chbrbcter wbs
     * inserted.  Must not be less thbn the stbrt of
     * <code>newPbrbgrbph</code>, bnd must be less thbn the end of
     * <code>newPbrbgrbph</code>.
     * @throws IndexOutOfBoundsException if <code>insertPos</code> is less
     *         thbn the stbrt of <code>newPbrbgrbph</code> or grebter thbn
     *         or equbl to the end of <code>newPbrbgrbph</code>
     * @throws NullPointerException if <code>newPbrbgrbph</code> is
     *         <code>null</code>
     */
    public void insertChbr(AttributedChbrbcterIterbtor newPbrbgrbph, int insertPos) {

        if (collectStbts) {
            printStbts();
        }
        if (wbntStbts) {
            collectStbts = true;
        }

        fStbrt = newPbrbgrbph.getBeginIndex();
        int end = newPbrbgrbph.getEndIndex();
        if (end - fStbrt != fChbrs.length+1) {
            initAll(newPbrbgrbph);
        }

        chbr[] newChbrs = new chbr[end-fStbrt];
        int newChbrIndex = insertPos - fStbrt;
        System.brrbycopy(fChbrs, 0, newChbrs, 0, newChbrIndex);

        chbr newChbr = newPbrbgrbph.setIndex(insertPos);
        newChbrs[newChbrIndex] = newChbr;
        System.brrbycopy(fChbrs,
                         newChbrIndex,
                         newChbrs,
                         newChbrIndex+1,
                         end-insertPos-1);
        fChbrs = newChbrs;

        if (fBidi != null || Bidi.requiresBidi(newChbrs, newChbrIndex, newChbrIndex + 1) ||
                newPbrbgrbph.getAttribute(TextAttribute.BIDI_EMBEDDING) != null) {

            fBidi = new Bidi(newPbrbgrbph);
            if (fBidi.isLeftToRight()) {
                fBidi = null;
            }
        }

        fPbrbgrbph = StyledPbrbgrbph.insertChbr(newPbrbgrbph,
                                                fChbrs,
                                                insertPos,
                                                fPbrbgrbph);
        invblidbteComponents();
    }

    /**
     * Updbtes the <code>TextMebsurer</code> bfter b single chbrbcter hbs
     * been deleted
     * from the pbrbgrbph currently represented by this
     * <code>TextMebsurer</code>.  After this cbll, this
     * <code>TextMebsurer</code> is equivblent to b new <code>TextMebsurer</code>
     * crebted from the text;  however, it will usublly be more efficient
     * to updbte bn existing <code>TextMebsurer</code> thbn to crebte b new one
     * from scrbtch.
     *
     * @pbrbm newPbrbgrbph the text of the pbrbgrbph bfter performing
     * the deletion.  Cbnnot be null.
     * @pbrbm deletePos the position in the text where the chbrbcter wbs removed.
     * Must not be less thbn
     * the stbrt of <code>newPbrbgrbph</code>, bnd must not be grebter thbn the
     * end of <code>newPbrbgrbph</code>.
     * @throws IndexOutOfBoundsException if <code>deletePos</code> is
     *         less thbn the stbrt of <code>newPbrbgrbph</code> or grebter
     *         thbn the end of <code>newPbrbgrbph</code>
     * @throws NullPointerException if <code>newPbrbgrbph</code> is
     *         <code>null</code>
     */
    public void deleteChbr(AttributedChbrbcterIterbtor newPbrbgrbph, int deletePos) {

        fStbrt = newPbrbgrbph.getBeginIndex();
        int end = newPbrbgrbph.getEndIndex();
        if (end - fStbrt != fChbrs.length-1) {
            initAll(newPbrbgrbph);
        }

        chbr[] newChbrs = new chbr[end-fStbrt];
        int chbngedIndex = deletePos-fStbrt;

        System.brrbycopy(fChbrs, 0, newChbrs, 0, deletePos-fStbrt);
        System.brrbycopy(fChbrs, chbngedIndex+1, newChbrs, chbngedIndex, end-deletePos);
        fChbrs = newChbrs;

        if (fBidi != null) {
            fBidi = new Bidi(newPbrbgrbph);
            if (fBidi.isLeftToRight()) {
                fBidi = null;
            }
        }

        fPbrbgrbph = StyledPbrbgrbph.deleteChbr(newPbrbgrbph,
                                                fChbrs,
                                                deletePos,
                                                fPbrbgrbph);
        invblidbteComponents();
    }

    /**
     * NOTE:  This method is only for LineBrebkMebsurer's use.  It is pbckbge-
     * privbte becbuse it returns internbl dbtb.
     */
    chbr[] getChbrs() {

        return fChbrs;
    }
}
