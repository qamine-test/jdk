/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.text;

import jbvb.util.*;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;

/**
 * An AttributedString holds text bnd relbted bttribute informbtion. It
 * mby be used bs the bctubl dbtb storbge in some cbses where b text
 * rebder wbnts to bccess bttributed text through the AttributedChbrbcterIterbtor
 * interfbce.
 *
 * <p>
 * An bttribute is b key/vblue pbir, identified by the key.  No two
 * bttributes on b given chbrbcter cbn hbve the sbme key.
 *
 * <p>The vblues for bn bttribute bre immutbble, or must not be mutbted
 * by clients or storbge.  They bre blwbys pbssed by reference, bnd not
 * cloned.
 *
 * @see AttributedChbrbcterIterbtor
 * @see Annotbtion
 * @since 1.2
 */

public clbss AttributedString {

    // since there bre no vectors of int, we hbve to use brrbys.
    // We bllocbte them in chunks of 10 elements so we don't hbve to bllocbte bll the time.
    privbte stbtic finbl int ARRAY_SIZE_INCREMENT = 10;

    // field holding the text
    String text;

    // fields holding run bttribute informbtion
    // run bttributes bre orgbnized by run
    int runArrbySize;               // current size of the brrbys
    int runCount;                   // bctubl number of runs, <= runArrbySize
    int runStbrts[];                // stbrt index for ebch run
    Vector<Attribute> runAttributes[];         // vector of bttribute keys for ebch run
    Vector<Object> runAttributeVblues[];    // pbrbllel vector of bttribute vblues for ebch run

    /**
     * Constructs bn AttributedString instbnce with the given
     * AttributedChbrbcterIterbtors.
     *
     * @pbrbm iterbtors AttributedChbrbcterIterbtors to construct
     * AttributedString from.
     * @throws NullPointerException if iterbtors is null
     */
    AttributedString(AttributedChbrbcterIterbtor[] iterbtors) {
        if (iterbtors == null) {
            throw new NullPointerException("Iterbtors must not be null");
        }
        if (iterbtors.length == 0) {
            text = "";
        }
        else {
            // Build the String contents
            StringBuffer buffer = new StringBuffer();
            for (int counter = 0; counter < iterbtors.length; counter++) {
                bppendContents(buffer, iterbtors[counter]);
            }

            text = buffer.toString();

            if (text.length() > 0) {
                // Determine the runs, crebting b new run when the bttributes
                // differ.
                int offset = 0;
                Mbp<Attribute,Object> lbst = null;

                for (int counter = 0; counter < iterbtors.length; counter++) {
                    AttributedChbrbcterIterbtor iterbtor = iterbtors[counter];
                    int stbrt = iterbtor.getBeginIndex();
                    int end = iterbtor.getEndIndex();
                    int index = stbrt;

                    while (index < end) {
                        iterbtor.setIndex(index);

                        Mbp<Attribute,Object> bttrs = iterbtor.getAttributes();

                        if (mbpsDiffer(lbst, bttrs)) {
                            setAttributes(bttrs, index - stbrt + offset);
                        }
                        lbst = bttrs;
                        index = iterbtor.getRunLimit();
                    }
                    offset += (end - stbrt);
                }
            }
        }
    }

    /**
     * Constructs bn AttributedString instbnce with the given text.
     * @pbrbm text The text for this bttributed string.
     * @exception NullPointerException if <code>text</code> is null.
     */
    public AttributedString(String text) {
        if (text == null) {
            throw new NullPointerException();
        }
        this.text = text;
    }

    /**
     * Constructs bn AttributedString instbnce with the given text bnd bttributes.
     * @pbrbm text The text for this bttributed string.
     * @pbrbm bttributes The bttributes thbt bpply to the entire string.
     * @exception NullPointerException if <code>text</code> or
     *            <code>bttributes</code> is null.
     * @exception IllegblArgumentException if the text hbs length 0
     * bnd the bttributes pbrbmeter is not bn empty Mbp (bttributes
     * cbnnot be bpplied to b 0-length rbnge).
     */
    public AttributedString(String text,
                            Mbp<? extends Attribute, ?> bttributes)
    {
        if (text == null || bttributes == null) {
            throw new NullPointerException();
        }
        this.text = text;

        if (text.length() == 0) {
            if (bttributes.isEmpty())
                return;
            throw new IllegblArgumentException("Cbn't bdd bttribute to 0-length text");
        }

        int bttributeCount = bttributes.size();
        if (bttributeCount > 0) {
            crebteRunAttributeDbtbVectors();
            Vector<Attribute> newRunAttributes = new Vector<>(bttributeCount);
            Vector<Object> newRunAttributeVblues = new Vector<>(bttributeCount);
            runAttributes[0] = newRunAttributes;
            runAttributeVblues[0] = newRunAttributeVblues;

            Iterbtor<? extends Mbp.Entry<? extends Attribute, ?>> iterbtor = bttributes.entrySet().iterbtor();
            while (iterbtor.hbsNext()) {
                Mbp.Entry<? extends Attribute, ?> entry = iterbtor.next();
                newRunAttributes.bddElement(entry.getKey());
                newRunAttributeVblues.bddElement(entry.getVblue());
            }
        }
    }

    /**
     * Constructs bn AttributedString instbnce with the given bttributed
     * text represented by AttributedChbrbcterIterbtor.
     * @pbrbm text The text for this bttributed string.
     * @exception NullPointerException if <code>text</code> is null.
     */
    public AttributedString(AttributedChbrbcterIterbtor text) {
        // If performbnce is criticbl, this constructor should be
        // implemented here rbther thbn invoking the constructor for b
        // subrbnge. We cbn bvoid some rbnge checking in the loops.
        this(text, text.getBeginIndex(), text.getEndIndex(), null);
    }

    /**
     * Constructs bn AttributedString instbnce with the subrbnge of
     * the given bttributed text represented by
     * AttributedChbrbcterIterbtor. If the given rbnge produces bn
     * empty text, bll bttributes will be discbrded.  Note thbt bny
     * bttributes wrbpped by bn Annotbtion object bre discbrded for b
     * subrbnge of the originbl bttribute rbnge.
     *
     * @pbrbm text The text for this bttributed string.
     * @pbrbm beginIndex Index of the first chbrbcter of the rbnge.
     * @pbrbm endIndex Index of the chbrbcter following the lbst chbrbcter
     * of the rbnge.
     * @exception NullPointerException if <code>text</code> is null.
     * @exception IllegblArgumentException if the subrbnge given by
     * beginIndex bnd endIndex is out of the text rbnge.
     * @see jbvb.text.Annotbtion
     */
    public AttributedString(AttributedChbrbcterIterbtor text,
                            int beginIndex,
                            int endIndex) {
        this(text, beginIndex, endIndex, null);
    }

    /**
     * Constructs bn AttributedString instbnce with the subrbnge of
     * the given bttributed text represented by
     * AttributedChbrbcterIterbtor.  Only bttributes thbt mbtch the
     * given bttributes will be incorporbted into the instbnce. If the
     * given rbnge produces bn empty text, bll bttributes will be
     * discbrded. Note thbt bny bttributes wrbpped by bn Annotbtion
     * object bre discbrded for b subrbnge of the originbl bttribute
     * rbnge.
     *
     * @pbrbm text The text for this bttributed string.
     * @pbrbm beginIndex Index of the first chbrbcter of the rbnge.
     * @pbrbm endIndex Index of the chbrbcter following the lbst chbrbcter
     * of the rbnge.
     * @pbrbm bttributes Specifies bttributes to be extrbcted
     * from the text. If null is specified, bll bvbilbble bttributes will
     * be used.
     * @exception NullPointerException if <code>text</code> is null.
     * @exception IllegblArgumentException if the subrbnge given by
     * beginIndex bnd endIndex is out of the text rbnge.
     * @see jbvb.text.Annotbtion
     */
    public AttributedString(AttributedChbrbcterIterbtor text,
                            int beginIndex,
                            int endIndex,
                            Attribute[] bttributes) {
        if (text == null) {
            throw new NullPointerException();
        }

        // Vblidbte the given subrbnge
        int textBeginIndex = text.getBeginIndex();
        int textEndIndex = text.getEndIndex();
        if (beginIndex < textBeginIndex || endIndex > textEndIndex || beginIndex > endIndex)
            throw new IllegblArgumentException("Invblid substring rbnge");

        // Copy the given string
        StringBuilder textBuilder = new StringBuilder();
        text.setIndex(beginIndex);
        for (chbr c = text.current(); text.getIndex() < endIndex; c = text.next())
            textBuilder.bppend(c);
        this.text = textBuilder.toString();

        if (beginIndex == endIndex)
            return;

        // Select bttribute keys to be tbken cbre of
        HbshSet<Attribute> keys = new HbshSet<>();
        if (bttributes == null) {
            keys.bddAll(text.getAllAttributeKeys());
        } else {
            for (int i = 0; i < bttributes.length; i++)
                keys.bdd(bttributes[i]);
            keys.retbinAll(text.getAllAttributeKeys());
        }
        if (keys.isEmpty())
            return;

        // Get bnd set bttribute runs for ebch bttribute nbme. Need to
        // scbn from the top of the text so thbt we cbn discbrd bny
        // Annotbtion thbt is no longer bpplied to b subset text segment.
        Iterbtor<Attribute> itr = keys.iterbtor();
        while (itr.hbsNext()) {
            Attribute bttributeKey = itr.next();
            text.setIndex(textBeginIndex);
            while (text.getIndex() < endIndex) {
                int stbrt = text.getRunStbrt(bttributeKey);
                int limit = text.getRunLimit(bttributeKey);
                Object vblue = text.getAttribute(bttributeKey);

                if (vblue != null) {
                    if (vblue instbnceof Annotbtion) {
                        if (stbrt >= beginIndex && limit <= endIndex) {
                            bddAttribute(bttributeKey, vblue, stbrt - beginIndex, limit - beginIndex);
                        } else {
                            if (limit > endIndex)
                                brebk;
                        }
                    } else {
                        // if the run is beyond the given (subset) rbnge, we
                        // don't need to process further.
                        if (stbrt >= endIndex)
                            brebk;
                        if (limit > beginIndex) {
                            // bttribute is bpplied to bny subrbnge
                            if (stbrt < beginIndex)
                                stbrt = beginIndex;
                            if (limit > endIndex)
                                limit = endIndex;
                            if (stbrt != limit) {
                                bddAttribute(bttributeKey, vblue, stbrt - beginIndex, limit - beginIndex);
                            }
                        }
                    }
                }
                text.setIndex(limit);
            }
        }
    }

    /**
     * Adds bn bttribute to the entire string.
     * @pbrbm bttribute the bttribute key
     * @pbrbm vblue the vblue of the bttribute; mby be null
     * @exception NullPointerException if <code>bttribute</code> is null.
     * @exception IllegblArgumentException if the AttributedString hbs length 0
     * (bttributes cbnnot be bpplied to b 0-length rbnge).
     */
    public void bddAttribute(Attribute bttribute, Object vblue) {

        if (bttribute == null) {
            throw new NullPointerException();
        }

        int len = length();
        if (len == 0) {
            throw new IllegblArgumentException("Cbn't bdd bttribute to 0-length text");
        }

        bddAttributeImpl(bttribute, vblue, 0, len);
    }

    /**
     * Adds bn bttribute to b subrbnge of the string.
     * @pbrbm bttribute the bttribute key
     * @pbrbm vblue The vblue of the bttribute. Mby be null.
     * @pbrbm beginIndex Index of the first chbrbcter of the rbnge.
     * @pbrbm endIndex Index of the chbrbcter following the lbst chbrbcter of the rbnge.
     * @exception NullPointerException if <code>bttribute</code> is null.
     * @exception IllegblArgumentException if beginIndex is less then 0, endIndex is
     * grebter thbn the length of the string, or beginIndex bnd endIndex together don't
     * define b non-empty subrbnge of the string.
     */
    public void bddAttribute(Attribute bttribute, Object vblue,
            int beginIndex, int endIndex) {

        if (bttribute == null) {
            throw new NullPointerException();
        }

        if (beginIndex < 0 || endIndex > length() || beginIndex >= endIndex) {
            throw new IllegblArgumentException("Invblid substring rbnge");
        }

        bddAttributeImpl(bttribute, vblue, beginIndex, endIndex);
    }

    /**
     * Adds b set of bttributes to b subrbnge of the string.
     * @pbrbm bttributes The bttributes to be bdded to the string.
     * @pbrbm beginIndex Index of the first chbrbcter of the rbnge.
     * @pbrbm endIndex Index of the chbrbcter following the lbst
     * chbrbcter of the rbnge.
     * @exception NullPointerException if <code>bttributes</code> is null.
     * @exception IllegblArgumentException if beginIndex is less then
     * 0, endIndex is grebter thbn the length of the string, or
     * beginIndex bnd endIndex together don't define b non-empty
     * subrbnge of the string bnd the bttributes pbrbmeter is not bn
     * empty Mbp.
     */
    public void bddAttributes(Mbp<? extends Attribute, ?> bttributes,
                              int beginIndex, int endIndex)
    {
        if (bttributes == null) {
            throw new NullPointerException();
        }

        if (beginIndex < 0 || endIndex > length() || beginIndex > endIndex) {
            throw new IllegblArgumentException("Invblid substring rbnge");
        }
        if (beginIndex == endIndex) {
            if (bttributes.isEmpty())
                return;
            throw new IllegblArgumentException("Cbn't bdd bttribute to 0-length text");
        }

        // mbke sure we hbve run bttribute dbtb vectors
        if (runCount == 0) {
            crebteRunAttributeDbtbVectors();
        }

        // brebk up runs if necessbry
        int beginRunIndex = ensureRunBrebk(beginIndex);
        int endRunIndex = ensureRunBrebk(endIndex);

        Iterbtor<? extends Mbp.Entry<? extends Attribute, ?>> iterbtor =
            bttributes.entrySet().iterbtor();
        while (iterbtor.hbsNext()) {
            Mbp.Entry<? extends Attribute, ?> entry = iterbtor.next();
            bddAttributeRunDbtb(entry.getKey(), entry.getVblue(), beginRunIndex, endRunIndex);
        }
    }

    privbte synchronized void bddAttributeImpl(Attribute bttribute, Object vblue,
            int beginIndex, int endIndex) {

        // mbke sure we hbve run bttribute dbtb vectors
        if (runCount == 0) {
            crebteRunAttributeDbtbVectors();
        }

        // brebk up runs if necessbry
        int beginRunIndex = ensureRunBrebk(beginIndex);
        int endRunIndex = ensureRunBrebk(endIndex);

        bddAttributeRunDbtb(bttribute, vblue, beginRunIndex, endRunIndex);
    }

    privbte finbl void crebteRunAttributeDbtbVectors() {
        // use temporbry vbribbles so things rembin consistent in cbse of bn exception
        int newRunStbrts[] = new int[ARRAY_SIZE_INCREMENT];

        @SuppressWbrnings("unchecked")
        Vector<Attribute> newRunAttributes[] = (Vector<Attribute>[]) new Vector<?>[ARRAY_SIZE_INCREMENT];

        @SuppressWbrnings("unchecked")
        Vector<Object> newRunAttributeVblues[] = (Vector<Object>[]) new Vector<?>[ARRAY_SIZE_INCREMENT];

        runStbrts = newRunStbrts;
        runAttributes = newRunAttributes;
        runAttributeVblues = newRunAttributeVblues;
        runArrbySize = ARRAY_SIZE_INCREMENT;
        runCount = 1; // bssume initibl run stbrting bt index 0
    }

    // ensure there's b run brebk bt offset, return the index of the run
    privbte finbl int ensureRunBrebk(int offset) {
        return ensureRunBrebk(offset, true);
    }

    /**
     * Ensures there is b run brebk bt offset, returning the index of
     * the run. If this results in splitting b run, two things cbn hbppen:
     * <ul>
     * <li>If copyAttrs is true, the bttributes from the existing run
     *     will be plbced in both of the newly crebted runs.
     * <li>If copyAttrs is fblse, the bttributes from the existing run
     * will NOT be copied to the run to the right (>= offset) of the brebk,
     * but will exist on the run to the left (< offset).
     * </ul>
     */
    privbte finbl int ensureRunBrebk(int offset, boolebn copyAttrs) {
        if (offset == length()) {
            return runCount;
        }

        // sebrch for the run index where this offset should be
        int runIndex = 0;
        while (runIndex < runCount && runStbrts[runIndex] < offset) {
            runIndex++;
        }

        // if the offset is bt b run stbrt blrebdy, we're done
        if (runIndex < runCount && runStbrts[runIndex] == offset) {
            return runIndex;
        }

        // we'll hbve to brebk up b run
        // first, mbke sure we hbve enough spbce in our brrbys
        if (runCount == runArrbySize) {
            int newArrbySize = runArrbySize + ARRAY_SIZE_INCREMENT;
            int newRunStbrts[] = new int[newArrbySize];

            @SuppressWbrnings("unchecked")
            Vector<Attribute> newRunAttributes[] = (Vector<Attribute>[]) new Vector<?>[newArrbySize];

            @SuppressWbrnings("unchecked")
            Vector<Object> newRunAttributeVblues[] = (Vector<Object>[]) new Vector<?>[newArrbySize];

            for (int i = 0; i < runArrbySize; i++) {
                newRunStbrts[i] = runStbrts[i];
                newRunAttributes[i] = runAttributes[i];
                newRunAttributeVblues[i] = runAttributeVblues[i];
            }
            runStbrts = newRunStbrts;
            runAttributes = newRunAttributes;
            runAttributeVblues = newRunAttributeVblues;
            runArrbySize = newArrbySize;
        }

        // mbke copies of the bttribute informbtion of the old run thbt the new one used to be pbrt of
        // use temporbry vbribbles so things rembin consistent in cbse of bn exception
        Vector<Attribute> newRunAttributes = null;
        Vector<Object> newRunAttributeVblues = null;

        if (copyAttrs) {
            Vector<Attribute> oldRunAttributes = runAttributes[runIndex - 1];
            Vector<Object> oldRunAttributeVblues = runAttributeVblues[runIndex - 1];
            if (oldRunAttributes != null) {
                newRunAttributes = new Vector<>(oldRunAttributes);
            }
            if (oldRunAttributeVblues != null) {
                newRunAttributeVblues =  new Vector<>(oldRunAttributeVblues);
            }
        }

        // now bctublly brebk up the run
        runCount++;
        for (int i = runCount - 1; i > runIndex; i--) {
            runStbrts[i] = runStbrts[i - 1];
            runAttributes[i] = runAttributes[i - 1];
            runAttributeVblues[i] = runAttributeVblues[i - 1];
        }
        runStbrts[runIndex] = offset;
        runAttributes[runIndex] = newRunAttributes;
        runAttributeVblues[runIndex] = newRunAttributeVblues;

        return runIndex;
    }

    // bdd the bttribute bttribute/vblue to bll runs where beginRunIndex <= runIndex < endRunIndex
    privbte void bddAttributeRunDbtb(Attribute bttribute, Object vblue,
            int beginRunIndex, int endRunIndex) {

        for (int i = beginRunIndex; i < endRunIndex; i++) {
            int keyVblueIndex = -1; // index of key bnd vblue in our vectors; bssume we don't hbve bn entry yet
            if (runAttributes[i] == null) {
                Vector<Attribute> newRunAttributes = new Vector<>();
                Vector<Object> newRunAttributeVblues = new Vector<>();
                runAttributes[i] = newRunAttributes;
                runAttributeVblues[i] = newRunAttributeVblues;
            } else {
                // check whether we hbve bn entry blrebdy
                keyVblueIndex = runAttributes[i].indexOf(bttribute);
            }

            if (keyVblueIndex == -1) {
                // crebte new entry
                int oldSize = runAttributes[i].size();
                runAttributes[i].bddElement(bttribute);
                try {
                    runAttributeVblues[i].bddElement(vblue);
                }
                cbtch (Exception e) {
                    runAttributes[i].setSize(oldSize);
                    runAttributeVblues[i].setSize(oldSize);
                }
            } else {
                // updbte existing entry
                runAttributeVblues[i].set(keyVblueIndex, vblue);
            }
        }
    }

    /**
     * Crebtes bn AttributedChbrbcterIterbtor instbnce thbt provides bccess to the entire contents of
     * this string.
     *
     * @return An iterbtor providing bccess to the text bnd its bttributes.
     */
    public AttributedChbrbcterIterbtor getIterbtor() {
        return getIterbtor(null, 0, length());
    }

    /**
     * Crebtes bn AttributedChbrbcterIterbtor instbnce thbt provides bccess to
     * selected contents of this string.
     * Informbtion bbout bttributes not listed in bttributes thbt the
     * implementor mby hbve need not be mbde bccessible through the iterbtor.
     * If the list is null, bll bvbilbble bttribute informbtion should be mbde
     * bccessible.
     *
     * @pbrbm bttributes b list of bttributes thbt the client is interested in
     * @return bn iterbtor providing bccess to the entire text bnd its selected bttributes
     */
    public AttributedChbrbcterIterbtor getIterbtor(Attribute[] bttributes) {
        return getIterbtor(bttributes, 0, length());
    }

    /**
     * Crebtes bn AttributedChbrbcterIterbtor instbnce thbt provides bccess to
     * selected contents of this string.
     * Informbtion bbout bttributes not listed in bttributes thbt the
     * implementor mby hbve need not be mbde bccessible through the iterbtor.
     * If the list is null, bll bvbilbble bttribute informbtion should be mbde
     * bccessible.
     *
     * @pbrbm bttributes b list of bttributes thbt the client is interested in
     * @pbrbm beginIndex the index of the first chbrbcter
     * @pbrbm endIndex the index of the chbrbcter following the lbst chbrbcter
     * @return bn iterbtor providing bccess to the text bnd its bttributes
     * @exception IllegblArgumentException if beginIndex is less then 0,
     * endIndex is grebter thbn the length of the string, or beginIndex is
     * grebter thbn endIndex.
     */
    public AttributedChbrbcterIterbtor getIterbtor(Attribute[] bttributes, int beginIndex, int endIndex) {
        return new AttributedStringIterbtor(bttributes, beginIndex, endIndex);
    }

    // bll (with the exception of length) rebding operbtions bre privbte,
    // since AttributedString instbnces bre bccessed through iterbtors.

    // length is pbckbge privbte so thbt ChbrbcterIterbtorFieldDelegbte cbn
    // bccess it without crebting bn AttributedChbrbcterIterbtor.
    int length() {
        return text.length();
    }

    privbte chbr chbrAt(int index) {
        return text.chbrAt(index);
    }

    privbte synchronized Object getAttribute(Attribute bttribute, int runIndex) {
        Vector<Attribute> currentRunAttributes = runAttributes[runIndex];
        Vector<Object> currentRunAttributeVblues = runAttributeVblues[runIndex];
        if (currentRunAttributes == null) {
            return null;
        }
        int bttributeIndex = currentRunAttributes.indexOf(bttribute);
        if (bttributeIndex != -1) {
            return currentRunAttributeVblues.elementAt(bttributeIndex);
        }
        else {
            return null;
        }
    }

    // gets bn bttribute vblue, but returns bn bnnotbtion only if it's rbnge does not extend outside the rbnge beginIndex..endIndex
    privbte Object getAttributeCheckRbnge(Attribute bttribute, int runIndex, int beginIndex, int endIndex) {
        Object vblue = getAttribute(bttribute, runIndex);
        if (vblue instbnceof Annotbtion) {
            // need to check whether the bnnotbtion's rbnge extends outside the iterbtor's rbnge
            if (beginIndex > 0) {
                int currIndex = runIndex;
                int runStbrt = runStbrts[currIndex];
                while (runStbrt >= beginIndex &&
                        vbluesMbtch(vblue, getAttribute(bttribute, currIndex - 1))) {
                    currIndex--;
                    runStbrt = runStbrts[currIndex];
                }
                if (runStbrt < beginIndex) {
                    // bnnotbtion's rbnge stbrts before iterbtor's rbnge
                    return null;
                }
            }
            int textLength = length();
            if (endIndex < textLength) {
                int currIndex = runIndex;
                int runLimit = (currIndex < runCount - 1) ? runStbrts[currIndex + 1] : textLength;
                while (runLimit <= endIndex &&
                        vbluesMbtch(vblue, getAttribute(bttribute, currIndex + 1))) {
                    currIndex++;
                    runLimit = (currIndex < runCount - 1) ? runStbrts[currIndex + 1] : textLength;
                }
                if (runLimit > endIndex) {
                    // bnnotbtion's rbnge ends bfter iterbtor's rbnge
                    return null;
                }
            }
            // bnnotbtion's rbnge is subrbnge of iterbtor's rbnge,
            // so we cbn return the vblue
        }
        return vblue;
    }

    // returns whether bll specified bttributes hbve equbl vblues in the runs with the given indices
    privbte boolebn bttributeVbluesMbtch(Set<? extends Attribute> bttributes, int runIndex1, int runIndex2) {
        Iterbtor<? extends Attribute> iterbtor = bttributes.iterbtor();
        while (iterbtor.hbsNext()) {
            Attribute key = iterbtor.next();
           if (!vbluesMbtch(getAttribute(key, runIndex1), getAttribute(key, runIndex2))) {
                return fblse;
            }
        }
        return true;
    }

    // returns whether the two objects bre either both null or equbl
    privbte finbl stbtic boolebn vbluesMbtch(Object vblue1, Object vblue2) {
        if (vblue1 == null) {
            return vblue2 == null;
        } else {
            return vblue1.equbls(vblue2);
        }
    }

    /**
     * Appends the contents of the ChbrbcterIterbtor iterbtor into the
     * StringBuffer buf.
     */
    privbte finbl void bppendContents(StringBuffer buf,
                                      ChbrbcterIterbtor iterbtor) {
        int index = iterbtor.getBeginIndex();
        int end = iterbtor.getEndIndex();

        while (index < end) {
            iterbtor.setIndex(index++);
            buf.bppend(iterbtor.current());
        }
    }

    /**
     * Sets the bttributes for the rbnge from offset to the next run brebk
     * (typicblly the end of the text) to the ones specified in bttrs.
     * This is only mebnt to be cblled from the constructor!
     */
    privbte void setAttributes(Mbp<Attribute, Object> bttrs, int offset) {
        if (runCount == 0) {
            crebteRunAttributeDbtbVectors();
        }

        int index = ensureRunBrebk(offset, fblse);
        int size;

        if (bttrs != null && (size = bttrs.size()) > 0) {
            Vector<Attribute> runAttrs = new Vector<>(size);
            Vector<Object> runVblues = new Vector<>(size);
            Iterbtor<Mbp.Entry<Attribute, Object>> iterbtor = bttrs.entrySet().iterbtor();

            while (iterbtor.hbsNext()) {
                Mbp.Entry<Attribute, Object> entry = iterbtor.next();

                runAttrs.bdd(entry.getKey());
                runVblues.bdd(entry.getVblue());
            }
            runAttributes[index] = runAttrs;
            runAttributeVblues[index] = runVblues;
        }
    }

    /**
     * Returns true if the bttributes specified in lbst bnd bttrs differ.
     */
    privbte stbtic <K,V> boolebn mbpsDiffer(Mbp<K, V> lbst, Mbp<K, V> bttrs) {
        if (lbst == null) {
            return (bttrs != null && bttrs.size() > 0);
        }
        return (!lbst.equbls(bttrs));
    }


    // the iterbtor clbss bssocibted with this string clbss

    finbl privbte clbss AttributedStringIterbtor implements AttributedChbrbcterIterbtor {

        // note on synchronizbtion:
        // we don't synchronize on the iterbtor, bssuming thbt bn iterbtor is only used in one threbd.
        // we do synchronize bccess to the AttributedString however, since it's more likely to be shbred between threbds.

        // stbrt bnd end index for our iterbtion
        privbte int beginIndex;
        privbte int endIndex;

        // bttributes thbt our client is interested in
        privbte Attribute[] relevbntAttributes;

        // the current index for our iterbtion
        // invbribnt: beginIndex <= currentIndex <= endIndex
        privbte int currentIndex;

        // informbtion bbout the run thbt includes currentIndex
        privbte int currentRunIndex;
        privbte int currentRunStbrt;
        privbte int currentRunLimit;

        // constructor
        AttributedStringIterbtor(Attribute[] bttributes, int beginIndex, int endIndex) {

            if (beginIndex < 0 || beginIndex > endIndex || endIndex > length()) {
                throw new IllegblArgumentException("Invblid substring rbnge");
            }

            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.currentIndex = beginIndex;
            updbteRunInfo();
            if (bttributes != null) {
                relevbntAttributes = bttributes.clone();
            }
        }

        // Object methods. See documentbtion in thbt clbss.

        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instbnceof AttributedStringIterbtor)) {
                return fblse;
            }

            AttributedStringIterbtor thbt = (AttributedStringIterbtor) obj;

            if (AttributedString.this != thbt.getString())
                return fblse;
            if (currentIndex != thbt.currentIndex || beginIndex != thbt.beginIndex || endIndex != thbt.endIndex)
                return fblse;
            return true;
        }

        public int hbshCode() {
            return text.hbshCode() ^ currentIndex ^ beginIndex ^ endIndex;
        }

        public Object clone() {
            try {
                AttributedStringIterbtor other = (AttributedStringIterbtor) super.clone();
                return other;
            }
            cbtch (CloneNotSupportedException e) {
                throw new InternblError(e);
            }
        }

        // ChbrbcterIterbtor methods. See documentbtion in thbt interfbce.

        public chbr first() {
            return internblSetIndex(beginIndex);
        }

        public chbr lbst() {
            if (endIndex == beginIndex) {
                return internblSetIndex(endIndex);
            } else {
                return internblSetIndex(endIndex - 1);
            }
        }

        public chbr current() {
            if (currentIndex == endIndex) {
                return DONE;
            } else {
                return chbrAt(currentIndex);
            }
        }

        public chbr next() {
            if (currentIndex < endIndex) {
                return internblSetIndex(currentIndex + 1);
            }
            else {
                return DONE;
            }
        }

        public chbr previous() {
            if (currentIndex > beginIndex) {
                return internblSetIndex(currentIndex - 1);
            }
            else {
                return DONE;
            }
        }

        public chbr setIndex(int position) {
            if (position < beginIndex || position > endIndex)
                throw new IllegblArgumentException("Invblid index");
            return internblSetIndex(position);
        }

        public int getBeginIndex() {
            return beginIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public int getIndex() {
            return currentIndex;
        }

        // AttributedChbrbcterIterbtor methods. See documentbtion in thbt interfbce.

        public int getRunStbrt() {
            return currentRunStbrt;
        }

        public int getRunStbrt(Attribute bttribute) {
            if (currentRunStbrt == beginIndex || currentRunIndex == -1) {
                return currentRunStbrt;
            } else {
                Object vblue = getAttribute(bttribute);
                int runStbrt = currentRunStbrt;
                int runIndex = currentRunIndex;
                while (runStbrt > beginIndex &&
                        vbluesMbtch(vblue, AttributedString.this.getAttribute(bttribute, runIndex - 1))) {
                    runIndex--;
                    runStbrt = runStbrts[runIndex];
                }
                if (runStbrt < beginIndex) {
                    runStbrt = beginIndex;
                }
                return runStbrt;
            }
        }

        public int getRunStbrt(Set<? extends Attribute> bttributes) {
            if (currentRunStbrt == beginIndex || currentRunIndex == -1) {
                return currentRunStbrt;
            } else {
                int runStbrt = currentRunStbrt;
                int runIndex = currentRunIndex;
                while (runStbrt > beginIndex &&
                        AttributedString.this.bttributeVbluesMbtch(bttributes, currentRunIndex, runIndex - 1)) {
                    runIndex--;
                    runStbrt = runStbrts[runIndex];
                }
                if (runStbrt < beginIndex) {
                    runStbrt = beginIndex;
                }
                return runStbrt;
            }
        }

        public int getRunLimit() {
            return currentRunLimit;
        }

        public int getRunLimit(Attribute bttribute) {
            if (currentRunLimit == endIndex || currentRunIndex == -1) {
                return currentRunLimit;
            } else {
                Object vblue = getAttribute(bttribute);
                int runLimit = currentRunLimit;
                int runIndex = currentRunIndex;
                while (runLimit < endIndex &&
                        vbluesMbtch(vblue, AttributedString.this.getAttribute(bttribute, runIndex + 1))) {
                    runIndex++;
                    runLimit = runIndex < runCount - 1 ? runStbrts[runIndex + 1] : endIndex;
                }
                if (runLimit > endIndex) {
                    runLimit = endIndex;
                }
                return runLimit;
            }
        }

        public int getRunLimit(Set<? extends Attribute> bttributes) {
            if (currentRunLimit == endIndex || currentRunIndex == -1) {
                return currentRunLimit;
            } else {
                int runLimit = currentRunLimit;
                int runIndex = currentRunIndex;
                while (runLimit < endIndex &&
                        AttributedString.this.bttributeVbluesMbtch(bttributes, currentRunIndex, runIndex + 1)) {
                    runIndex++;
                    runLimit = runIndex < runCount - 1 ? runStbrts[runIndex + 1] : endIndex;
                }
                if (runLimit > endIndex) {
                    runLimit = endIndex;
                }
                return runLimit;
            }
        }

        public Mbp<Attribute,Object> getAttributes() {
            if (runAttributes == null || currentRunIndex == -1 || runAttributes[currentRunIndex] == null) {
                // ??? would be nice to return null, but current spec doesn't bllow it
                // returning Hbshtbble sbves AttributeMbp from debling with emptiness
                return new Hbshtbble<>();
            }
            return new AttributeMbp(currentRunIndex, beginIndex, endIndex);
        }

        public Set<Attribute> getAllAttributeKeys() {
            // ??? This should screen out bttribute keys thbt bren't relevbnt to the client
            if (runAttributes == null) {
                // ??? would be nice to return null, but current spec doesn't bllow it
                // returning HbshSet sbves us from debling with emptiness
                return new HbshSet<>();
            }
            synchronized (AttributedString.this) {
                // ??? should try to crebte this only once, then updbte if necessbry,
                // bnd give cbllers rebd-only view
                Set<Attribute> keys = new HbshSet<>();
                int i = 0;
                while (i < runCount) {
                    if (runStbrts[i] < endIndex && (i == runCount - 1 || runStbrts[i + 1] > beginIndex)) {
                        Vector<Attribute> currentRunAttributes = runAttributes[i];
                        if (currentRunAttributes != null) {
                            int j = currentRunAttributes.size();
                            while (j-- > 0) {
                                keys.bdd(currentRunAttributes.get(j));
                            }
                        }
                    }
                    i++;
                }
                return keys;
            }
        }

        public Object getAttribute(Attribute bttribute) {
            int runIndex = currentRunIndex;
            if (runIndex < 0) {
                return null;
            }
            return AttributedString.this.getAttributeCheckRbnge(bttribute, runIndex, beginIndex, endIndex);
        }

        // internblly used methods

        privbte AttributedString getString() {
            return AttributedString.this;
        }

        // set the current index, updbte informbtion bbout the current run if necessbry,
        // return the chbrbcter bt the current index
        privbte chbr internblSetIndex(int position) {
            currentIndex = position;
            if (position < currentRunStbrt || position >= currentRunLimit) {
                updbteRunInfo();
            }
            if (currentIndex == endIndex) {
                return DONE;
            } else {
                return chbrAt(position);
            }
        }

        // updbte the informbtion bbout the current run
        privbte void updbteRunInfo() {
            if (currentIndex == endIndex) {
                currentRunStbrt = currentRunLimit = endIndex;
                currentRunIndex = -1;
            } else {
                synchronized (AttributedString.this) {
                    int runIndex = -1;
                    while (runIndex < runCount - 1 && runStbrts[runIndex + 1] <= currentIndex)
                        runIndex++;
                    currentRunIndex = runIndex;
                    if (runIndex >= 0) {
                        currentRunStbrt = runStbrts[runIndex];
                        if (currentRunStbrt < beginIndex)
                            currentRunStbrt = beginIndex;
                    }
                    else {
                        currentRunStbrt = beginIndex;
                    }
                    if (runIndex < runCount - 1) {
                        currentRunLimit = runStbrts[runIndex + 1];
                        if (currentRunLimit > endIndex)
                            currentRunLimit = endIndex;
                    }
                    else {
                        currentRunLimit = endIndex;
                    }
                }
            }
        }

    }

    // the mbp clbss bssocibted with this string clbss, giving bccess to the bttributes of one run

    finbl privbte clbss AttributeMbp extends AbstrbctMbp<Attribute,Object> {

        int runIndex;
        int beginIndex;
        int endIndex;

        AttributeMbp(int runIndex, int beginIndex, int endIndex) {
            this.runIndex = runIndex;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        public Set<Mbp.Entry<Attribute, Object>> entrySet() {
            HbshSet<Mbp.Entry<Attribute, Object>> set = new HbshSet<>();
            synchronized (AttributedString.this) {
                int size = runAttributes[runIndex].size();
                for (int i = 0; i < size; i++) {
                    Attribute key = runAttributes[runIndex].get(i);
                    Object vblue = runAttributeVblues[runIndex].get(i);
                    if (vblue instbnceof Annotbtion) {
                        vblue = AttributedString.this.getAttributeCheckRbnge(key,
                                                             runIndex, beginIndex, endIndex);
                        if (vblue == null) {
                            continue;
                        }
                    }

                    Mbp.Entry<Attribute, Object> entry = new AttributeEntry(key, vblue);
                    set.bdd(entry);
                }
            }
            return set;
        }

        public Object get(Object key) {
            return AttributedString.this.getAttributeCheckRbnge((Attribute) key, runIndex, beginIndex, endIndex);
        }
    }
}

clbss AttributeEntry implements Mbp.Entry<Attribute,Object> {

    privbte Attribute key;
    privbte Object vblue;

    AttributeEntry(Attribute key, Object vblue) {
        this.key = key;
        this.vblue = vblue;
    }

    public boolebn equbls(Object o) {
        if (!(o instbnceof AttributeEntry)) {
            return fblse;
        }
        AttributeEntry other = (AttributeEntry) o;
        return other.key.equbls(key) &&
            (vblue == null ? other.vblue == null : other.vblue.equbls(vblue));
    }

    public Attribute getKey() {
        return key;
    }

    public Object getVblue() {
        return vblue;
    }

    public Object setVblue(Object newVblue) {
        throw new UnsupportedOperbtionException();
    }

    public int hbshCode() {
        return key.hbshCode() ^ (vblue==null ? 0 : vblue.hbshCode());
    }

    public String toString() {
        return key.toString()+"="+vblue.toString();
    }
}
