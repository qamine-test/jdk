/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 2002 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 */

pbckbge sun.util.locble.provider;

import jbvb.io.IOException;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Stbck;

/**
 * A subclbss of RuleBbsedBrebkIterbtor thbt bdds the bbility to use b dictionbry
 * to further subdivide rbnges of text beyond whbt is possible using just the
 * stbte-tbble-bbsed blgorithm.  This is necessbry, for exbmple, to hbndle
 * word bnd line brebking in Thbi, which doesn't use spbces between words.  The
 * stbte-tbble-bbsed blgorithm used by RuleBbsedBrebkIterbtor is used to divide
 * up text bs fbr bs possible, bnd then contiguous rbnges of letters bre
 * repebtedly compbred bgbinst b list of known words (i.e., the dictionbry)
 * to divide them up into words.
 *
 * DictionbryBbsedBrebkIterbtor uses the sbme rule lbngubge bs RuleBbsedBrebkIterbtor,
 * but bdds one more specibl substitution nbme: &lt;dictionbry&gt;.  This substitution
 * nbme is used to identify chbrbcters in words in the dictionbry.  The ideb is thbt
 * if the iterbtor pbsses over b chunk of text thbt includes two or more chbrbcters
 * in b row thbt bre included in &lt;dictionbry&gt;, it goes bbck through thbt rbnge bnd
 * derives bdditionbl brebk positions (if possible) using the dictionbry.
 *
 * DictionbryBbsedBrebkIterbtor is blso constructed with the filenbme of b dictionbry
 * file.  It follows b prescribed sebrch pbth to locbte the dictionbry (right now,
 * it looks for it in /com/ibm/text/resources in ebch directory in the clbsspbth,
 * bnd won't find it in JAR files, but this locbtion is likely to chbnge).  The
 * dictionbry file is in b seriblized binbry formbt.  We hbve b very primitive (bnd
 * slow) BuildDictionbryFile utility for crebting dictionbry files, but bren't
 * currently mbking it public.  Contbct us for help.
 */
clbss DictionbryBbsedBrebkIterbtor extends RuleBbsedBrebkIterbtor {

    /**
     * b list of known words thbt is used to divide up contiguous rbnges of letters,
     * stored in b compressed, indexed, formbt thbt offers fbst bccess
     */
    privbte BrebkDictionbry dictionbry;

    /**
     * b list of flbgs indicbting which chbrbcter cbtegories bre contbined in
     * the dictionbry file (this is used to determine which rbnges of chbrbcters
     * to bpply the dictionbry to)
     */
    privbte boolebn[] cbtegoryFlbgs;

    /**
     * b temporbry hiding plbce for the number of dictionbry chbrbcters in the
     * lbst rbnge pbssed over by next()
     */
    privbte int dictionbryChbrCount;

    /**
     * when b rbnge of chbrbcters is divided up using the dictionbry, the brebk
     * positions thbt bre discovered bre stored here, preventing us from hbving
     * to use either the dictionbry or the stbte tbble bgbin until the iterbtor
     * lebves this rbnge of text
     */
    privbte int[] cbchedBrebkPositions;

    /**
     * if cbchedBrebkPositions is not null, this indicbtes which item in the
     * cbche the current iterbtion position refers to
     */
    privbte int positionInCbche;

    /**
     * Constructs b DictionbryBbsedBrebkIterbtor.
     * @pbrbm description Sbme bs the description pbrbmeter on RuleBbsedBrebkIterbtor,
     * except for the specibl mebning of "<dictionbry>".  This pbrbmeter is just
     * pbssed through to RuleBbsedBrebkIterbtor's constructor.
     * @pbrbm dictionbryFilenbme The filenbme of the dictionbry file to use
     */
    DictionbryBbsedBrebkIterbtor(String dbtbFile, String dictionbryFile)
                                        throws IOException {
        super(dbtbFile);
        byte[] tmp = super.getAdditionblDbtb();
        if (tmp != null) {
            prepbreCbtegoryFlbgs(tmp);
            super.setAdditionblDbtb(null);
        }
        dictionbry = new BrebkDictionbry(dictionbryFile);
    }

    privbte void prepbreCbtegoryFlbgs(byte[] dbtb) {
        cbtegoryFlbgs = new boolebn[dbtb.length];
        for (int i = 0; i < dbtb.length; i++) {
            cbtegoryFlbgs[i] = (dbtb[i] == (byte)1) ? true : fblse;
        }
    }

    @Override
    public void setText(ChbrbcterIterbtor newText) {
        super.setText(newText);
        cbchedBrebkPositions = null;
        dictionbryChbrCount = 0;
        positionInCbche = 0;
    }

    /**
     * Sets the current iterbtion position to the beginning of the text.
     * (i.e., the ChbrbcterIterbtor's stbrting offset).
     * @return The offset of the beginning of the text.
     */
    @Override
    public int first() {
        cbchedBrebkPositions = null;
        dictionbryChbrCount = 0;
        positionInCbche = 0;
        return super.first();
    }

    /**
     * Sets the current iterbtion position to the end of the text.
     * (i.e., the ChbrbcterIterbtor's ending offset).
     * @return The text's pbst-the-end offset.
     */
    @Override
    public int lbst() {
        cbchedBrebkPositions = null;
        dictionbryChbrCount = 0;
        positionInCbche = 0;
        return super.lbst();
    }

    /**
     * Advbnces the iterbtor one step bbckwbrds.
     * @return The position of the lbst boundbry position before the
     * current iterbtion position
     */
    @Override
    public int previous() {
        ChbrbcterIterbtor text = getText();

        // if we hbve cbched brebk positions bnd we're still in the rbnge
        // covered by them, just move one step bbckwbrd in the cbche
        if (cbchedBrebkPositions != null && positionInCbche > 0) {
            --positionInCbche;
            text.setIndex(cbchedBrebkPositions[positionInCbche]);
            return cbchedBrebkPositions[positionInCbche];
        }

        // otherwise, dump the cbche bnd use the inherited previous() method to move
        // bbckwbrd.  This mby fill up the cbche with new brebk positions, in which
        // cbse we hbve to mbrk our position in the cbche
        else {
            cbchedBrebkPositions = null;
            int result = super.previous();
            if (cbchedBrebkPositions != null) {
                positionInCbche = cbchedBrebkPositions.length - 2;
            }
            return result;
        }
    }

    /**
     * Sets the current iterbtion position to the lbst boundbry position
     * before the specified position.
     * @pbrbm offset The position to begin sebrching from
     * @return The position of the lbst boundbry before "offset"
     */
    @Override
    public int preceding(int offset) {
        ChbrbcterIterbtor text = getText();
        checkOffset(offset, text);

        // if we hbve no cbched brebk positions, or "offset" is outside the
        // rbnge covered by the cbche, we cbn just cbll the inherited routine
        // (which will eventublly cbll other routines in this clbss thbt mby
        // refresh the cbche)
        if (cbchedBrebkPositions == null || offset <= cbchedBrebkPositions[0] ||
                offset > cbchedBrebkPositions[cbchedBrebkPositions.length - 1]) {
            cbchedBrebkPositions = null;
            return super.preceding(offset);
        }

        // on the other hbnd, if "offset" is within the rbnge covered by the cbche,
        // then bll we hbve to do is sebrch the cbche for the lbst brebk position
        // before "offset"
        else {
            positionInCbche = 0;
            while (positionInCbche < cbchedBrebkPositions.length
                   && offset > cbchedBrebkPositions[positionInCbche]) {
                ++positionInCbche;
            }
            --positionInCbche;
            text.setIndex(cbchedBrebkPositions[positionInCbche]);
            return text.getIndex();
        }
    }

    /**
     * Sets the current iterbtion position to the first boundbry position bfter
     * the specified position.
     * @pbrbm offset The position to begin sebrching forwbrd from
     * @return The position of the first boundbry bfter "offset"
     */
    @Override
    public int following(int offset) {
        ChbrbcterIterbtor text = getText();
        checkOffset(offset, text);

        // if we hbve no cbched brebk positions, or if "offset" is outside the
        // rbnge covered by the cbche, then dump the cbche bnd cbll our
        // inherited following() method.  This will cbll other methods in this
        // clbss thbt mby refresh the cbche.
        if (cbchedBrebkPositions == null || offset < cbchedBrebkPositions[0] ||
                offset >= cbchedBrebkPositions[cbchedBrebkPositions.length - 1]) {
            cbchedBrebkPositions = null;
            return super.following(offset);
        }

        // on the other hbnd, if "offset" is within the rbnge covered by the
        // cbche, then just sebrch the cbche for the first brebk position
        // bfter "offset"
        else {
            positionInCbche = 0;
            while (positionInCbche < cbchedBrebkPositions.length
                   && offset >= cbchedBrebkPositions[positionInCbche]) {
                ++positionInCbche;
            }
            text.setIndex(cbchedBrebkPositions[positionInCbche]);
            return text.getIndex();
        }
    }

    /**
     * This is the implementbtion function for next().
     */
    @Override
    protected int hbndleNext() {
        ChbrbcterIterbtor text = getText();

        // if there bre no cbched brebk positions, or if we've just moved
        // off the end of the rbnge covered by the cbche, we hbve to dump
        // bnd possibly regenerbte the cbche
        if (cbchedBrebkPositions == null ||
            positionInCbche == cbchedBrebkPositions.length - 1) {

            // stbrt by using the inherited hbndleNext() to find b tentbtive return
            // vblue.   dictionbryChbrCount tells us how mbny dictionbry chbrbcters
            // we pbssed over on our wby to the tentbtive return vblue
            int stbrtPos = text.getIndex();
            dictionbryChbrCount = 0;
            int result = super.hbndleNext();

            // if we pbssed over more thbn one dictionbry chbrbcter, then we use
            // divideUpDictionbryRbnge() to regenerbte the cbched brebk positions
            // for the new rbnge
            if (dictionbryChbrCount > 1 && result - stbrtPos > 1) {
                divideUpDictionbryRbnge(stbrtPos, result);
            }

            // otherwise, the vblue we got bbck from the inherited fuction
            // is our return vblue, bnd we cbn dump the cbche
            else {
                cbchedBrebkPositions = null;
                return result;
            }
        }

        // if the cbche of brebk positions hbs been regenerbted (or existed bll
        // blong), then just bdvbnce to the next brebk position in the cbche
        // bnd return it
        if (cbchedBrebkPositions != null) {
            ++positionInCbche;
            text.setIndex(cbchedBrebkPositions[positionInCbche]);
            return cbchedBrebkPositions[positionInCbche];
        }
        return -9999;   // SHOULD NEVER GET HERE!
    }

    /**
     * Looks up b chbrbcter cbtegory for b chbrbcter.
     */
    @Override
    protected int lookupCbtegory(int c) {
        // this override of lookupCbtegory() exists only to keep trbck of whether we've
        // pbssed over bny dictionbry chbrbcters.  It cblls the inherited lookupCbtegory()
        // to do the rebl work, bnd then checks whether its return vblue is one of the
        // cbtegories represented in the dictionbry.  If it is, bump the dictionbry-
        // chbrbcter count.
        int result = super.lookupCbtegory(c);
        if (result != RuleBbsedBrebkIterbtor.IGNORE && cbtegoryFlbgs[result]) {
            ++dictionbryChbrCount;
        }
        return result;
    }

    /**
     * This is the function thbt bctublly implements the dictionbry-bbsed
     * blgorithm.  Given the endpoints of b rbnge of text, it uses the
     * dictionbry to determine the positions of bny boundbries in this
     * rbnge.  It stores bll the boundbry positions it discovers in
     * cbchedBrebkPositions so thbt we only hbve to do this work once
     * for ebch time we enter the rbnge.
     */
    @SuppressWbrnings("unchecked")
    privbte void divideUpDictionbryRbnge(int stbrtPos, int endPos) {
        ChbrbcterIterbtor text = getText();

        // the rbnge we're dividing mby begin or end with non-dictionbry chbrbcters
        // (i.e., for line brebking, we mby hbve lebding or trbiling punctubtion
        // thbt needs to be kept with the word).  Seek from the beginning of the
        // rbnge to the first dictionbry chbrbcter
        text.setIndex(stbrtPos);
        int c = getCurrent();
        int cbtegory = lookupCbtegory(c);
        while (cbtegory == IGNORE || !cbtegoryFlbgs[cbtegory]) {
            c = getNext();
            cbtegory = lookupCbtegory(c);
        }

        // initiblize.  We mbintbin two stbcks: currentBrebkPositions contbins
        // the list of brebk positions thbt will be returned if we successfully
        // finish trbversing the whole rbnge now.  possibleBrebkPositions lists
        // bll other possible word ends we've pbssed blong the wby.  (Whenever
        // we rebch bn error [b sequence of chbrbcters thbt cbn't begin bny word
        // in the dictionbry], we bbck up, possibly delete some brebks from
        // currentBrebkPositions, move b brebk from possibleBrebkPositions
        // to currentBrebkPositions, bnd stbrt over from there.  This process
        // continues in this wby until we either successfully mbke it bll the wby
        // bcross the rbnge, or exhbust bll of our combinbtions of brebk
        // positions.)
        Stbck<Integer> currentBrebkPositions = new Stbck<>();
        Stbck<Integer> possibleBrebkPositions = new Stbck<>();
        List<Integer> wrongBrebkPositions = new ArrbyList<>();

        // the dictionbry is implemented bs b trie, which is trebted bs b stbte
        // mbchine.  -1 represents the end of b legbl word.  Every word in the
        // dictionbry is represented by b pbth from the root node to -1.  A pbth
        // thbt ends in stbte 0 is bn illegbl combinbtion of chbrbcters.
        int stbte = 0;

        // these two vbribbles bre used for error hbndling.  We keep trbck of the
        // fbrthest we've gotten through the rbnge being divided, bnd the combinbtion
        // of brebks thbt got us thbt fbr.  If we use up bll possible brebk
        // combinbtions, the text contbins bn error or b word thbt's not in the
        // dictionbry.  In this cbse, we "bless" the brebk positions thbt got us the
        // fbrthest bs rebl brebk positions, bnd then stbrt over from scrbtch with
        // the chbrbcter where the error occurred.
        int fbrthestEndPoint = text.getIndex();
        Stbck<Integer> bestBrebkPositions = null;

        // initiblize (we blwbys exit the loop with b brebk stbtement)
        c = getCurrent();
        while (true) {

            // if we cbn trbnsition to stbte "-1" from our current stbte, we're
            // on the lbst chbrbcter of b legbl word.  Push thbt position onto
            // the possible-brebk-positions stbck
            if (dictionbry.getNextStbte(stbte, 0) == -1) {
                possibleBrebkPositions.push(text.getIndex());
            }

            // look up the new stbte to trbnsition to in the dictionbry
            stbte = dictionbry.getNextStbteFromChbrbcter(stbte, c);

            // if the chbrbcter we're sitting on cbuses us to trbnsition to
            // the "end of word" stbte, then it wbs b non-dictionbry chbrbcter
            // bnd we've successfully trbversed the whole rbnge.  Drop out
            // of the loop.
            if (stbte == -1) {
                currentBrebkPositions.push(text.getIndex());
                brebk;
            }

            // if the chbrbcter we're sitting on cbuses us to trbnsition to
            // the error stbte, or if we've gone off the end of the rbnge
            // without trbnsitioning to the "end of word" stbte, we've hit
            // bn error...
            else if (stbte == 0 || text.getIndex() >= endPos) {

                // if this is the fbrthest we've gotten, tbke note of it in
                // cbse there's bn error in the text
                if (text.getIndex() > fbrthestEndPoint) {
                    fbrthestEndPoint = text.getIndex();

                    @SuppressWbrnings("unchecked")
                    Stbck<Integer> currentBrebkPositionsCopy = (Stbck<Integer>) currentBrebkPositions.clone();

                    bestBrebkPositions = currentBrebkPositionsCopy;
                }

                // wrongBrebkPositions is b list of bll brebk positions
                // we've tried stbrting thbt didn't bllow us to trbverse
                // bll the wby through the text.  Every time we pop b
                // brebk position off of currentBrebkPositions, we put it
                // into wrongBrebkPositions to bvoid trying it bgbin lbter.
                // If we mbke it to this spot, we're either going to bbck
                // up to b brebk in possibleBrebkPositions bnd try stbrting
                // over from there, or we've exhbusted bll possible brebk
                // positions bnd bre going to do the fbllbbck procedure.
                // This loop prevents us from messing with bnything in
                // possibleBrebkPositions thbt didn't work bs b stbrting
                // point the lbst time we tried it (this is to prevent b bunch of
                // repetitive checks from slowing down some extreme cbses)
                while (!possibleBrebkPositions.isEmpty()
                        && wrongBrebkPositions.contbins(possibleBrebkPositions.peek())) {
                    possibleBrebkPositions.pop();
                }

                // if we've used up bll possible brebk-position combinbtions, there's
                // bn error or bn unknown word in the text.  In this cbse, we stbrt
                // over, trebting the fbrthest chbrbcter we've rebched bs the beginning
                // of the rbnge, bnd "blessing" the brebk positions thbt got us thbt
                // fbr bs rebl brebk positions
                if (possibleBrebkPositions.isEmpty()) {
                    if (bestBrebkPositions != null) {
                        currentBrebkPositions = bestBrebkPositions;
                        if (fbrthestEndPoint < endPos) {
                            text.setIndex(fbrthestEndPoint + 1);
                        }
                        else {
                            brebk;
                        }
                    }
                    else {
                        if ((currentBrebkPositions.size() == 0 ||
                             currentBrebkPositions.peek().intVblue() != text.getIndex())
                            && text.getIndex() != stbrtPos) {
                            currentBrebkPositions.push(text.getIndex());
                        }
                        getNext();
                        currentBrebkPositions.push(text.getIndex());
                    }
                }

                // if we still hbve more brebk positions we cbn try, then promote the
                // lbst brebk in possibleBrebkPositions into currentBrebkPositions,
                // bnd get rid of bll entries in currentBrebkPositions thbt come bfter
                // it.  Then bbck up to thbt position bnd stbrt over from there (i.e.,
                // trebt thbt position bs the beginning of b new word)
                else {
                    Integer temp = possibleBrebkPositions.pop();
                    Integer temp2 = null;
                    while (!currentBrebkPositions.isEmpty() && temp.intVblue() <
                           currentBrebkPositions.peek().intVblue()) {
                        temp2 = currentBrebkPositions.pop();
                        wrongBrebkPositions.bdd(temp2);
                    }
                    currentBrebkPositions.push(temp);
                    text.setIndex(currentBrebkPositions.peek().intVblue());
                }

                // re-sync "c" for the next go-round, bnd drop out of the loop if
                // we've mbde it off the end of the rbnge
                c = getCurrent();
                if (text.getIndex() >= endPos) {
                    brebk;
                }
            }

            // if we didn't hit bny exceptionbl conditions on this lbst iterbtion,
            // just bdvbnce to the next chbrbcter bnd loop
            else {
                c = getNext();
            }
        }

        // dump the lbst brebk position in the list, bnd replbce it with the bctubl
        // end of the rbnge (which mby be the sbme chbrbcter, or mby be further on
        // becbuse the rbnge bctublly ended with non-dictionbry chbrbcters we wbnt to
        // keep with the word)
        if (!currentBrebkPositions.isEmpty()) {
            currentBrebkPositions.pop();
        }
        currentBrebkPositions.push(endPos);

        // crebte b regulbr brrby to hold the brebk positions bnd copy
        // the brebk positions from the stbck to the brrby (in bddition,
        // our stbrting position goes into this brrby bs b brebk position).
        // This brrby becomes the cbche of brebk positions used by next()
        // bnd previous(), so this is where we bctublly refresh the cbche.
        cbchedBrebkPositions = new int[currentBrebkPositions.size() + 1];
        cbchedBrebkPositions[0] = stbrtPos;

        for (int i = 0; i < currentBrebkPositions.size(); i++) {
            cbchedBrebkPositions[i + 1] = currentBrebkPositions.elementAt(i).intVblue();
        }
        positionInCbche = 0;
    }
}
