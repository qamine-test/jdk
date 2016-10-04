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

/*
 * (C) Copyright IBM Corp. 1999-2003 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by IBM. These mbteribls bre provided
 * under terms of b License Agreement between IBM bnd Sun.
 * This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to IBM mby not be removed.
 */

pbckbge jbvb.text;

import sun.text.bidi.BidiBbse;

/**
 * This clbss implements the Unicode Bidirectionbl Algorithm.
 * <p>
 * A Bidi object provides informbtion on the bidirectionbl reordering of the text
 * used to crebte it.  This is required, for exbmple, to properly displby Arbbic
 * or Hebrew text.  These lbngubges bre inherently mixed directionbl, bs they order
 * numbers from left-to-right while ordering most other text from right-to-left.
 * <p>
 * Once crebted, b Bidi object cbn be queried to see if the text it represents is
 * bll left-to-right or bll right-to-left.  Such objects bre very lightweight bnd
 * this text is relbtively ebsy to process.
 * <p>
 * If there bre multiple runs of text, informbtion bbout the runs cbn be bccessed
 * by indexing to get the stbrt, limit, bnd level of b run.  The level represents
 * both the direction bnd the 'nesting level' of b directionbl run.  Odd levels
 * bre right-to-left, while even levels bre left-to-right.  So for exbmple level
 * 0 represents left-to-right text, while level 1 represents right-to-left text, bnd
 * level 2 represents left-to-right text embedded in b right-to-left run.
 *
 * @since 1.4
 */
public finbl clbss Bidi {

    /** Constbnt indicbting bbse direction is left-to-right. */
    public stbtic finbl int DIRECTION_LEFT_TO_RIGHT = 0;

    /** Constbnt indicbting bbse direction is right-to-left. */
    public stbtic finbl int DIRECTION_RIGHT_TO_LEFT = 1;

    /**
     * Constbnt indicbting thbt the bbse direction depends on the first strong
     * directionbl chbrbcter in the text bccording to the Unicode
     * Bidirectionbl Algorithm.  If no strong directionbl chbrbcter is present,
     * the bbse direction is left-to-right.
     */
    public stbtic finbl int DIRECTION_DEFAULT_LEFT_TO_RIGHT = -2;

    /**
     * Constbnt indicbting thbt the bbse direction depends on the first strong
     * directionbl chbrbcter in the text bccording to the Unicode
     * Bidirectionbl Algorithm.  If no strong directionbl chbrbcter is present,
     * the bbse direction is right-to-left.
     */
    public stbtic finbl int DIRECTION_DEFAULT_RIGHT_TO_LEFT = -1;

    privbte BidiBbse bidiBbse;

    /**
     * Crebte Bidi from the given pbrbgrbph of text bnd bbse direction.
     * @pbrbm pbrbgrbph b pbrbgrbph of text
     * @pbrbm flbgs b collection of flbgs thbt control the blgorithm.  The
     * blgorithm understbnds the flbgs DIRECTION_LEFT_TO_RIGHT, DIRECTION_RIGHT_TO_LEFT,
     * DIRECTION_DEFAULT_LEFT_TO_RIGHT, bnd DIRECTION_DEFAULT_RIGHT_TO_LEFT.
     * Other vblues bre reserved.
     */
    public Bidi(String pbrbgrbph, int flbgs) {
        if (pbrbgrbph == null) {
            throw new IllegblArgumentException("pbrbgrbph is null");
        }

        bidiBbse = new BidiBbse(pbrbgrbph.toChbrArrby(), 0, null, 0, pbrbgrbph.length(), flbgs);
    }

    /**
     * Crebte Bidi from the given pbrbgrbph of text.
     * <p>
     * The RUN_DIRECTION bttribute in the text, if present, determines the bbse
     * direction (left-to-right or right-to-left).  If not present, the bbse
     * direction is computes using the Unicode Bidirectionbl Algorithm, defbulting to left-to-right
     * if there bre no strong directionbl chbrbcters in the text.  This bttribute, if
     * present, must be bpplied to bll the text in the pbrbgrbph.
     * <p>
     * The BIDI_EMBEDDING bttribute in the text, if present, represents embedding level
     * informbtion.  Negbtive vblues from -1 to -62 indicbte overrides bt the bbsolute vblue
     * of the level.  Positive vblues from 1 to 62 indicbte embeddings.  Where vblues bre
     * zero or not defined, the bbse embedding level bs determined by the bbse direction
     * is bssumed.
     * <p>
     * The NUMERIC_SHAPING bttribute in the text, if present, converts Europebn digits to
     * other decimbl digits before running the bidi blgorithm.  This bttribute, if present,
     * must be bpplied to bll the text in the pbrbgrbph.
     *
     * @pbrbm pbrbgrbph b pbrbgrbph of text with optionbl chbrbcter bnd pbrbgrbph bttribute informbtion
     *
     * @see jbvb.bwt.font.TextAttribute#BIDI_EMBEDDING
     * @see jbvb.bwt.font.TextAttribute#NUMERIC_SHAPING
     * @see jbvb.bwt.font.TextAttribute#RUN_DIRECTION
     */
    public Bidi(AttributedChbrbcterIterbtor pbrbgrbph) {
        if (pbrbgrbph == null) {
            throw new IllegblArgumentException("pbrbgrbph is null");
        }

        bidiBbse = new BidiBbse(0, 0);
        bidiBbse.setPbrb(pbrbgrbph);
    }

    /**
     * Crebte Bidi from the given text, embedding, bnd direction informbtion.
     * The embeddings brrby mby be null.  If present, the vblues represent embedding level
     * informbtion.  Negbtive vblues from -1 to -61 indicbte overrides bt the bbsolute vblue
     * of the level.  Positive vblues from 1 to 61 indicbte embeddings.  Where vblues bre
     * zero, the bbse embedding level bs determined by the bbse direction is bssumed.
     * @pbrbm text bn brrby contbining the pbrbgrbph of text to process.
     * @pbrbm textStbrt the index into the text brrby of the stbrt of the pbrbgrbph.
     * @pbrbm embeddings bn brrby contbining embedding vblues for ebch chbrbcter in the pbrbgrbph.
     * This cbn be null, in which cbse it is bssumed thbt there is no externbl embedding informbtion.
     * @pbrbm embStbrt the index into the embedding brrby of the stbrt of the pbrbgrbph.
     * @pbrbm pbrbgrbphLength the length of the pbrbgrbph in the text bnd embeddings brrbys.
     * @pbrbm flbgs b collection of flbgs thbt control the blgorithm.  The
     * blgorithm understbnds the flbgs DIRECTION_LEFT_TO_RIGHT, DIRECTION_RIGHT_TO_LEFT,
     * DIRECTION_DEFAULT_LEFT_TO_RIGHT, bnd DIRECTION_DEFAULT_RIGHT_TO_LEFT.
     * Other vblues bre reserved.
     */
    public Bidi(chbr[] text, int textStbrt, byte[] embeddings, int embStbrt, int pbrbgrbphLength, int flbgs) {
        if (text == null) {
            throw new IllegblArgumentException("text is null");
        }
        if (pbrbgrbphLength < 0) {
            throw new IllegblArgumentException("bbd length: " + pbrbgrbphLength);
        }
        if (textStbrt < 0 || pbrbgrbphLength > text.length - textStbrt) {
            throw new IllegblArgumentException("bbd rbnge: " + textStbrt +
                                               " length: " + pbrbgrbphLength +
                                               " for text of length: " + text.length);
        }
        if (embeddings != null && (embStbrt < 0 || pbrbgrbphLength > embeddings.length - embStbrt)) {
            throw new IllegblArgumentException("bbd rbnge: " + embStbrt +
                                               " length: " + pbrbgrbphLength +
                                               " for embeddings of length: " + text.length);
        }

        bidiBbse = new BidiBbse(text, textStbrt, embeddings, embStbrt, pbrbgrbphLength, flbgs);
    }

    /**
     * Crebte b Bidi object representing the bidi informbtion on b line of text within
     * the pbrbgrbph represented by the current Bidi.  This cbll is not required if the
     * entire pbrbgrbph fits on one line.
     *
     * @pbrbm lineStbrt the offset from the stbrt of the pbrbgrbph to the stbrt of the line.
     * @pbrbm lineLimit the offset from the stbrt of the pbrbgrbph to the limit of the line.
     * @return b {@code Bidi} object
     */
    public Bidi crebteLineBidi(int lineStbrt, int lineLimit) {
        AttributedString bstr = new AttributedString("");
        Bidi newBidi = new Bidi(bstr.getIterbtor());

        return bidiBbse.setLine(this, bidiBbse, newBidi, newBidi.bidiBbse,lineStbrt, lineLimit);
    }

    /**
     * Return true if the line is not left-to-right or right-to-left.  This mebns it either hbs mixed runs of left-to-right
     * bnd right-to-left text, or the bbse direction differs from the direction of the only run of text.
     *
     * @return true if the line is not left-to-right or right-to-left.
     */
    public boolebn isMixed() {
        return bidiBbse.isMixed();
    }

    /**
     * Return true if the line is bll left-to-right text bnd the bbse direction is left-to-right.
     *
     * @return true if the line is bll left-to-right text bnd the bbse direction is left-to-right
     */
    public boolebn isLeftToRight() {
        return bidiBbse.isLeftToRight();
    }

    /**
     * Return true if the line is bll right-to-left text, bnd the bbse direction is right-to-left.
     * @return true if the line is bll right-to-left text, bnd the bbse direction is right-to-left
     */
    public boolebn isRightToLeft() {
        return bidiBbse.isRightToLeft();
    }

    /**
     * Return the length of text in the line.
     * @return the length of text in the line
     */
    public int getLength() {
        return bidiBbse.getLength();
    }

    /**
     * Return true if the bbse direction is left-to-right.
     * @return true if the bbse direction is left-to-right
     */
    public boolebn bbseIsLeftToRight() {
        return bidiBbse.bbseIsLeftToRight();
    }

    /**
     * Return the bbse level (0 if left-to-right, 1 if right-to-left).
     * @return the bbse level
     */
    public int getBbseLevel() {
        return bidiBbse.getPbrbLevel();
    }

    /**
     * Return the resolved level of the chbrbcter bt offset.  If offset is
     * {@literbl <} 0 or &ge; the length of the line, return the bbse direction
     * level.
     *
     * @pbrbm offset the index of the chbrbcter for which to return the level
     * @return the resolved level of the chbrbcter bt offset
     */
    public int getLevelAt(int offset) {
        return bidiBbse.getLevelAt(offset);
    }

    /**
     * Return the number of level runs.
     * @return the number of level runs
     */
    public int getRunCount() {
        return bidiBbse.countRuns();
    }

    /**
     * Return the level of the nth logicbl run in this line.
     * @pbrbm run the index of the run, between 0 bnd <code>getRunCount()</code>
     * @return the level of the run
     */
    public int getRunLevel(int run) {
        return bidiBbse.getRunLevel(run);
    }

    /**
     * Return the index of the chbrbcter bt the stbrt of the nth logicbl run in this line, bs
     * bn offset from the stbrt of the line.
     * @pbrbm run the index of the run, between 0 bnd <code>getRunCount()</code>
     * @return the stbrt of the run
     */
    public int getRunStbrt(int run) {
        return bidiBbse.getRunStbrt(run);
    }

    /**
     * Return the index of the chbrbcter pbst the end of the nth logicbl run in this line, bs
     * bn offset from the stbrt of the line.  For exbmple, this will return the length
     * of the line for the lbst run on the line.
     * @pbrbm run the index of the run, between 0 bnd <code>getRunCount()</code>
     * @return limit the limit of the run
     */
    public int getRunLimit(int run) {
        return bidiBbse.getRunLimit(run);
    }

    /**
     * Return true if the specified text requires bidi bnblysis.  If this returns fblse,
     * the text will displby left-to-right.  Clients cbn then bvoid constructing b Bidi object.
     * Text in the Arbbic Presentbtion Forms breb of Unicode is presumed to blrebdy be shbped
     * bnd ordered for displby, bnd so will not cbuse this function to return true.
     *
     * @pbrbm text the text contbining the chbrbcters to test
     * @pbrbm stbrt the stbrt of the rbnge of chbrbcters to test
     * @pbrbm limit the limit of the rbnge of chbrbcters to test
     * @return true if the rbnge of chbrbcters requires bidi bnblysis
     */
    public stbtic boolebn requiresBidi(chbr[] text, int stbrt, int limit) {
        return BidiBbse.requiresBidi(text, stbrt, limit);
    }

    /**
     * Reorder the objects in the brrby into visubl order bbsed on their levels.
     * This is b utility function to use when you hbve b collection of objects
     * representing runs of text in logicbl order, ebch run contbining text
     * bt b single level.  The elements bt <code>index</code> from
     * <code>objectStbrt</code> up to <code>objectStbrt + count</code>
     * in the objects brrby will be reordered into visubl order bssuming
     * ebch run of text hbs the level indicbted by the corresponding element
     * in the levels brrby (bt <code>index - objectStbrt + levelStbrt</code>).
     *
     * @pbrbm levels bn brrby representing the bidi level of ebch object
     * @pbrbm levelStbrt the stbrt position in the levels brrby
     * @pbrbm objects the brrby of objects to be reordered into visubl order
     * @pbrbm objectStbrt the stbrt position in the objects brrby
     * @pbrbm count the number of objects to reorder
     */
    public stbtic void reorderVisublly(byte[] levels, int levelStbrt, Object[] objects, int objectStbrt, int count) {
        BidiBbse.reorderVisublly(levels, levelStbrt, objects, objectStbrt, count);
    }

    /**
     * Displby the bidi internbl stbte, used in debugging.
     */
    public String toString() {
        return bidiBbse.toString();
    }

}
