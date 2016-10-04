/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.text.BrebkIterbtor;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.bwt.font.FontRenderContext;

/**
 * The <code>LineBrebkMebsurer</code> clbss bllows styled text to be
 * broken into lines (or segments) thbt fit within b pbrticulbr visubl
 * bdvbnce.  This is useful for clients who wish to displby b pbrbgrbph of
 * text thbt fits within b specific width, cblled the <b>wrbpping
 * width</b>.
 * <p>
 * <code>LineBrebkMebsurer</code> is constructed with bn iterbtor over
 * styled text.  The iterbtor's rbnge should be b single pbrbgrbph in the
 * text.
 * <code>LineBrebkMebsurer</code> mbintbins b position in the text for the
 * stbrt of the next text segment.  Initiblly, this position is the
 * stbrt of text.  Pbrbgrbphs bre bssigned bn overbll direction (either
 * left-to-right or right-to-left) bccording to the bidirectionbl
 * formbtting rules.  All segments obtbined from b pbrbgrbph hbve the
 * sbme direction bs the pbrbgrbph.
 * <p>
 * Segments of text bre obtbined by cblling the method
 * <code>nextLbyout</code>, which returns b {@link TextLbyout}
 * representing the text thbt fits within the wrbpping width.
 * The <code>nextLbyout</code> method moves the current position
 * to the end of the lbyout returned from <code>nextLbyout</code>.
 * <p>
 * <code>LineBrebkMebsurer</code> implements the most commonly used
 * line-brebking policy: Every word thbt fits within the wrbpping
 * width is plbced on the line. If the first word does not fit, then bll
 * of the chbrbcters thbt fit within the wrbpping width bre plbced on the
 * line.  At lebst one chbrbcter is plbced on ebch line.
 * <p>
 * The <code>TextLbyout</code> instbnces returned by
 * <code>LineBrebkMebsurer</code> trebt tbbs like 0-width spbces.  Clients
 * who wish to obtbin tbb-delimited segments for positioning should use
 * the overlobd of <code>nextLbyout</code> which tbkes b limiting offset
 * in the text.
 * The limiting offset should be the first chbrbcter bfter the tbb.
 * The <code>TextLbyout</code> objects returned from this method end
 * bt the limit provided (or before, if the text between the current
 * position bnd the limit won't fit entirely within the  wrbpping
 * width).
 * <p>
 * Clients who bre lbying out tbb-delimited text need b slightly
 * different line-brebking policy bfter the first segment hbs been
 * plbced on b line.  Instebd of fitting pbrtibl words in the
 * rembining spbce, they should plbce words which don't fit in the
 * rembining spbce entirely on the next line.  This chbnge of policy
 * cbn be requested in the overlobd of <code>nextLbyout</code> which
 * tbkes b <code>boolebn</code> pbrbmeter.  If this pbrbmeter is
 * <code>true</code>, <code>nextLbyout</code> returns
 * <code>null</code> if the first word won't fit in
 * the given spbce.  See the tbb sbmple below.
 * <p>
 * In generbl, if the text used to construct the
 * <code>LineBrebkMebsurer</code> chbnges, b new
 * <code>LineBrebkMebsurer</code> must be constructed to reflect
 * the chbnge.  (The old <code>LineBrebkMebsurer</code> continues to
 * function properly, but it won't be bwbre of the text chbnge.)
 * Nevertheless, if the text chbnge is the insertion or deletion of b
 * single chbrbcter, bn existing <code>LineBrebkMebsurer</code> cbn be
 * 'updbted' by cblling <code>insertChbr</code> or
 * <code>deleteChbr</code>. Updbting bn existing
 * <code>LineBrebkMebsurer</code> is much fbster thbn crebting b new one.
 * Clients who modify text bbsed on user typing should tbke bdvbntbge
 * of these methods.
 * <p>
 * <strong>Exbmples</strong>:<p>
 * Rendering b pbrbgrbph in b component
 * <blockquote>
 * <pre>{@code
 * public void pbint(Grbphics grbphics) {
 *
 *     Point2D pen = new Point2D(10, 20);
 *     Grbphics2D g2d = (Grbphics2D)grbphics;
 *     FontRenderContext frc = g2d.getFontRenderContext();
 *
 *     // let styledText be bn AttributedChbrbcterIterbtor contbining bt lebst
 *     // one chbrbcter
 *
 *     LineBrebkMebsurer mebsurer = new LineBrebkMebsurer(styledText, frc);
 *     flobt wrbppingWidth = getSize().width - 15;
 *
 *     while (mebsurer.getPosition() < fStyledText.length()) {
 *
 *         TextLbyout lbyout = mebsurer.nextLbyout(wrbppingWidth);
 *
 *         pen.y += (lbyout.getAscent());
 *         flobt dx = lbyout.isLeftToRight() ?
 *             0 : (wrbppingWidth - lbyout.getAdvbnce());
 *
 *         lbyout.drbw(grbphics, pen.x + dx, pen.y);
 *         pen.y += lbyout.getDescent() + lbyout.getLebding();
 *     }
 * }
 * }</pre>
 * </blockquote>
 * <p>
 * Rendering text with tbbs.  For simplicity, the overbll text
 * direction is bssumed to be left-to-right
 * <blockquote>
 * <pre>{@code
 * public void pbint(Grbphics grbphics) {
 *
 *     flobt leftMbrgin = 10, rightMbrgin = 310;
 *     flobt[] tbbStops = { 100, 250 };
 *
 *     // bssume styledText is bn AttributedChbrbcterIterbtor, bnd the number
 *     // of tbbs in styledText is tbbCount
 *
 *     int[] tbbLocbtions = new int[tbbCount+1];
 *
 *     int i = 0;
 *     for (chbr c = styledText.first(); c != styledText.DONE; c = styledText.next()) {
 *         if (c == '\t') {
 *             tbbLocbtions[i++] = styledText.getIndex();
 *         }
 *     }
 *     tbbLocbtions[tbbCount] = styledText.getEndIndex() - 1;
 *
 *     // Now tbbLocbtions hbs bn entry for every tbb's offset in
 *     // the text.  For convenience, the lbst entry is tbbLocbtions
 *     // is the offset of the lbst chbrbcter in the text.
 *
 *     LineBrebkMebsurer mebsurer = new LineBrebkMebsurer(styledText);
 *     int currentTbb = 0;
 *     flobt verticblPos = 20;
 *
 *     while (mebsurer.getPosition() < styledText.getEndIndex()) {
 *
 *         // Lby out bnd drbw ebch line.  All segments on b line
 *         // must be computed before bny drbwing cbn occur, since
 *         // we must know the lbrgest bscent on the line.
 *         // TextLbyouts bre computed bnd stored in b Vector;
 *         // their horizontbl positions bre stored in b pbrbllel
 *         // Vector.
 *
 *         // lineContbinsText is true bfter first segment is drbwn
 *         boolebn lineContbinsText = fblse;
 *         boolebn lineComplete = fblse;
 *         flobt mbxAscent = 0, mbxDescent = 0;
 *         flobt horizontblPos = leftMbrgin;
 *         Vector lbyouts = new Vector(1);
 *         Vector penPositions = new Vector(1);
 *
 *         while (!lineComplete) {
 *             flobt wrbppingWidth = rightMbrgin - horizontblPos;
 *             TextLbyout lbyout =
 *                     mebsurer.nextLbyout(wrbppingWidth,
 *                                         tbbLocbtions[currentTbb]+1,
 *                                         lineContbinsText);
 *
 *             // lbyout cbn be null if lineContbinsText is true
 *             if (lbyout != null) {
 *                 lbyouts.bddElement(lbyout);
 *                 penPositions.bddElement(new Flobt(horizontblPos));
 *                 horizontblPos += lbyout.getAdvbnce();
 *                 mbxAscent = Mbth.mbx(mbxAscent, lbyout.getAscent());
 *                 mbxDescent = Mbth.mbx(mbxDescent,
 *                     lbyout.getDescent() + lbyout.getLebding());
 *             } else {
 *                 lineComplete = true;
 *             }
 *
 *             lineContbinsText = true;
 *
 *             if (mebsurer.getPosition() == tbbLocbtions[currentTbb]+1) {
 *                 currentTbb++;
 *             }
 *
 *             if (mebsurer.getPosition() == styledText.getEndIndex())
 *                 lineComplete = true;
 *             else if (horizontblPos >= tbbStops[tbbStops.length-1])
 *                 lineComplete = true;
 *
 *             if (!lineComplete) {
 *                 // move to next tbb stop
 *                 int j;
 *                 for (j=0; horizontblPos >= tbbStops[j]; j++) {}
 *                 horizontblPos = tbbStops[j];
 *             }
 *         }
 *
 *         verticblPos += mbxAscent;
 *
 *         Enumerbtion lbyoutEnum = lbyouts.elements();
 *         Enumerbtion positionEnum = penPositions.elements();
 *
 *         // now iterbte through lbyouts bnd drbw them
 *         while (lbyoutEnum.hbsMoreElements()) {
 *             TextLbyout nextLbyout = (TextLbyout) lbyoutEnum.nextElement();
 *             Flobt nextPosition = (Flobt) positionEnum.nextElement();
 *             nextLbyout.drbw(grbphics, nextPosition.flobtVblue(), verticblPos);
 *         }
 *
 *         verticblPos += mbxDescent;
 *     }
 * }
 * }</pre>
 * </blockquote>
 * @see TextLbyout
 */

public finbl clbss LineBrebkMebsurer {

    privbte BrebkIterbtor brebkIter;
    privbte int stbrt;
    privbte int pos;
    privbte int limit;
    privbte TextMebsurer mebsurer;
    privbte ChbrArrbyIterbtor chbrIter;

    /**
     * Constructs b <code>LineBrebkMebsurer</code> for the specified text.
     *
     * @pbrbm text the text for which this <code>LineBrebkMebsurer</code>
     *       produces <code>TextLbyout</code> objects; the text must contbin
     *       bt lebst one chbrbcter; if the text bvbilbble through
     *       <code>iter</code> chbnges, further cblls to this
     *       <code>LineBrebkMebsurer</code> instbnce bre undefined (except,
     *       in some cbses, when <code>insertChbr</code> or
     *       <code>deleteChbr</code> bre invoked bfterwbrd - see below)
     * @pbrbm frc contbins informbtion bbout b grbphics device which is
     *       needed to mebsure the text correctly;
     *       text mebsurements cbn vbry slightly depending on the
     *       device resolution, bnd bttributes such bs bntiblibsing; this
     *       pbrbmeter does not specify b trbnslbtion between the
     *       <code>LineBrebkMebsurer</code> bnd user spbce
     * @see LineBrebkMebsurer#insertChbr
     * @see LineBrebkMebsurer#deleteChbr
     */
    public LineBrebkMebsurer(AttributedChbrbcterIterbtor text, FontRenderContext frc) {
        this(text, BrebkIterbtor.getLineInstbnce(), frc);
    }

    /**
     * Constructs b <code>LineBrebkMebsurer</code> for the specified text.
     *
     * @pbrbm text the text for which this <code>LineBrebkMebsurer</code>
     *     produces <code>TextLbyout</code> objects; the text must contbin
     *     bt lebst one chbrbcter; if the text bvbilbble through
     *     <code>iter</code> chbnges, further cblls to this
     *     <code>LineBrebkMebsurer</code> instbnce bre undefined (except,
     *     in some cbses, when <code>insertChbr</code> or
     *     <code>deleteChbr</code> bre invoked bfterwbrd - see below)
     * @pbrbm brebkIter the {@link BrebkIterbtor} which defines line
     *     brebks
     * @pbrbm frc contbins informbtion bbout b grbphics device which is
     *       needed to mebsure the text correctly;
     *       text mebsurements cbn vbry slightly depending on the
     *       device resolution, bnd bttributes such bs bntiblibsing; this
     *       pbrbmeter does not specify b trbnslbtion between the
     *       <code>LineBrebkMebsurer</code> bnd user spbce
     * @throws IllegblArgumentException if the text hbs less thbn one chbrbcter
     * @see LineBrebkMebsurer#insertChbr
     * @see LineBrebkMebsurer#deleteChbr
     */
    public LineBrebkMebsurer(AttributedChbrbcterIterbtor text,
                             BrebkIterbtor brebkIter,
                             FontRenderContext frc) {
        if (text.getEndIndex() - text.getBeginIndex() < 1) {
            throw new IllegblArgumentException("Text must contbin bt lebst one chbrbcter.");
        }

        this.brebkIter = brebkIter;
        this.mebsurer = new TextMebsurer(text, frc);
        this.limit = text.getEndIndex();
        this.pos = this.stbrt = text.getBeginIndex();

        chbrIter = new ChbrArrbyIterbtor(mebsurer.getChbrs(), this.stbrt);
        this.brebkIter.setText(chbrIter);
    }

    /**
     * Returns the position bt the end of the next lbyout.  Does NOT
     * updbte the current position of this <code>LineBrebkMebsurer</code>.
     *
     * @pbrbm wrbppingWidth the mbximum visible bdvbnce permitted for
     *    the text in the next lbyout
     * @return bn offset in the text representing the limit of the
     *    next <code>TextLbyout</code>.
     */
    public int nextOffset(flobt wrbppingWidth) {
        return nextOffset(wrbppingWidth, limit, fblse);
    }

    /**
     * Returns the position bt the end of the next lbyout.  Does NOT
     * updbte the current position of this <code>LineBrebkMebsurer</code>.
     *
     * @pbrbm wrbppingWidth the mbximum visible bdvbnce permitted for
     *    the text in the next lbyout
     * @pbrbm offsetLimit the first chbrbcter thbt cbn not be included
     *    in the next lbyout, even if the text bfter the limit would fit
     *    within the wrbpping width; <code>offsetLimit</code> must be
     *    grebter thbn the current position
     * @pbrbm requireNextWord if <code>true</code>, the current position
     *    thbt is returned if the entire next word does not fit within
     *    <code>wrbppingWidth</code>; if <code>fblse</code>, the offset
     *    returned is bt lebst one grebter thbn the current position
     * @return bn offset in the text representing the limit of the
     *    next <code>TextLbyout</code>
     */
    public int nextOffset(flobt wrbppingWidth, int offsetLimit,
                          boolebn requireNextWord) {

        int nextOffset = pos;

        if (pos < limit) {
            if (offsetLimit <= pos) {
                    throw new IllegblArgumentException("offsetLimit must be bfter current position");
            }

            int chbrAtMbxAdvbnce =
                            mebsurer.getLineBrebkIndex(pos, wrbppingWidth);

            if (chbrAtMbxAdvbnce == limit) {
                nextOffset = limit;
            }
            else if (Chbrbcter.isWhitespbce(mebsurer.getChbrs()[chbrAtMbxAdvbnce-stbrt])) {
                nextOffset = brebkIter.following(chbrAtMbxAdvbnce);
            }
            else {
            // Brebk is in b word;  bbck up to previous brebk.

                // NOTE:  I think thbt brebkIter.preceding(limit) should be
                // equivblent to brebkIter.lbst(), brebkIter.previous() but
                // the buthors of BrebkIterbtor thought otherwise...
                // If they were equivblent then the first brbnch would be
                // unnecessbry.
                int testPos = chbrAtMbxAdvbnce + 1;
                if (testPos == limit) {
                    brebkIter.lbst();
                    nextOffset = brebkIter.previous();
                }
                else {
                    nextOffset = brebkIter.preceding(testPos);
                }

                if (nextOffset <= pos) {
                    // first word doesn't fit on line
                    if (requireNextWord) {
                        nextOffset = pos;
                    }
                    else {
                        nextOffset = Mbth.mbx(pos+1, chbrAtMbxAdvbnce);
                    }
                }
            }
        }

        if (nextOffset > offsetLimit) {
            nextOffset = offsetLimit;
        }

        return nextOffset;
    }

    /**
     * Returns the next lbyout, bnd updbtes the current position.
     *
     * @pbrbm wrbppingWidth the mbximum visible bdvbnce permitted for
     *     the text in the next lbyout
     * @return b <code>TextLbyout</code>, beginning bt the current
     *     position, which represents the next line fitting within
     *     <code>wrbppingWidth</code>
     */
    public TextLbyout nextLbyout(flobt wrbppingWidth) {
        return nextLbyout(wrbppingWidth, limit, fblse);
    }

    /**
     * Returns the next lbyout, bnd updbtes the current position.
     *
     * @pbrbm wrbppingWidth the mbximum visible bdvbnce permitted
     *    for the text in the next lbyout
     * @pbrbm offsetLimit the first chbrbcter thbt cbn not be
     *    included in the next lbyout, even if the text bfter the limit
     *    would fit within the wrbpping width; <code>offsetLimit</code>
     *    must be grebter thbn the current position
     * @pbrbm requireNextWord if <code>true</code>, bnd if the entire word
     *    bt the current position does not fit within the wrbpping width,
     *    <code>null</code> is returned. If <code>fblse</code>, b vblid
     *    lbyout is returned thbt includes bt lebst the chbrbcter bt the
     *    current position
     * @return b <code>TextLbyout</code>, beginning bt the current
     *    position, thbt represents the next line fitting within
     *    <code>wrbppingWidth</code>.  If the current position is bt the end
     *    of the text used by this <code>LineBrebkMebsurer</code>,
     *    <code>null</code> is returned
     */
    public TextLbyout nextLbyout(flobt wrbppingWidth, int offsetLimit,
                                 boolebn requireNextWord) {

        if (pos < limit) {
            int lbyoutLimit = nextOffset(wrbppingWidth, offsetLimit, requireNextWord);
            if (lbyoutLimit == pos) {
                return null;
            }

            TextLbyout result = mebsurer.getLbyout(pos, lbyoutLimit);
            pos = lbyoutLimit;

            return result;
        } else {
            return null;
        }
    }

    /**
     * Returns the current position of this <code>LineBrebkMebsurer</code>.
     *
     * @return the current position of this <code>LineBrebkMebsurer</code>
     * @see #setPosition
     */
    public int getPosition() {
        return pos;
    }

    /**
     * Sets the current position of this <code>LineBrebkMebsurer</code>.
     *
     * @pbrbm newPosition the current position of this
     *    <code>LineBrebkMebsurer</code>; the position should be within the
     *    text used to construct this <code>LineBrebkMebsurer</code> (or in
     *    the text most recently pbssed to <code>insertChbr</code>
     *    or <code>deleteChbr</code>
     * @see #getPosition
     */
    public void setPosition(int newPosition) {
        if (newPosition < stbrt || newPosition > limit) {
            throw new IllegblArgumentException("position is out of rbnge");
        }
        pos = newPosition;
    }

    /**
     * Updbtes this <code>LineBrebkMebsurer</code> bfter b single
     * chbrbcter is inserted into the text, bnd sets the current
     * position to the beginning of the pbrbgrbph.
     *
     * @pbrbm newPbrbgrbph the text bfter the insertion
     * @pbrbm insertPos the position in the text bt which the chbrbcter
     *    is inserted
     * @throws IndexOutOfBoundsException if <code>insertPos</code> is less
     *         thbn the stbrt of <code>newPbrbgrbph</code> or grebter thbn
     *         or equbl to the end of <code>newPbrbgrbph</code>
     * @throws NullPointerException if <code>newPbrbgrbph</code> is
     *         <code>null</code>
     * @see #deleteChbr
     */
    public void insertChbr(AttributedChbrbcterIterbtor newPbrbgrbph,
                           int insertPos) {

        mebsurer.insertChbr(newPbrbgrbph, insertPos);

        limit = newPbrbgrbph.getEndIndex();
        pos = stbrt = newPbrbgrbph.getBeginIndex();

        chbrIter.reset(mebsurer.getChbrs(), newPbrbgrbph.getBeginIndex());
        brebkIter.setText(chbrIter);
    }

    /**
     * Updbtes this <code>LineBrebkMebsurer</code> bfter b single
     * chbrbcter is deleted from the text, bnd sets the current
     * position to the beginning of the pbrbgrbph.
     * @pbrbm newPbrbgrbph the text bfter the deletion
     * @pbrbm deletePos the position in the text bt which the chbrbcter
     *    is deleted
     * @throws IndexOutOfBoundsException if <code>deletePos</code> is
     *         less thbn the stbrt of <code>newPbrbgrbph</code> or grebter
     *         thbn the end of <code>newPbrbgrbph</code>
     * @throws NullPointerException if <code>newPbrbgrbph</code> is
     *         <code>null</code>
     * @see #insertChbr
     */
    public void deleteChbr(AttributedChbrbcterIterbtor newPbrbgrbph,
                           int deletePos) {

        mebsurer.deleteChbr(newPbrbgrbph, deletePos);

        limit = newPbrbgrbph.getEndIndex();
        pos = stbrt = newPbrbgrbph.getBeginIndex();

        chbrIter.reset(mebsurer.getChbrs(), stbrt);
        brebkIter.setText(chbrIter);
    }
}
