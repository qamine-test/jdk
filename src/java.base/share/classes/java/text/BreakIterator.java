/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.lbng.ref.SoftReference;
import jbvb.text.spi.BrebkIterbtorProvider;
import jbvb.util.Locble;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleServiceProviderPool;


/**
 * The <code>BrebkIterbtor</code> clbss implements methods for finding
 * the locbtion of boundbries in text. Instbnces of <code>BrebkIterbtor</code>
 * mbintbin b current position bnd scbn over text
 * returning the index of chbrbcters where boundbries occur.
 * Internblly, <code>BrebkIterbtor</code> scbns text using b
 * <code>ChbrbcterIterbtor</code>, bnd is thus bble to scbn text held
 * by bny object implementing thbt protocol. A <code>StringChbrbcterIterbtor</code>
 * is used to scbn <code>String</code> objects pbssed to <code>setText</code>.
 *
 * <p>
 * You use the fbctory methods provided by this clbss to crebte
 * instbnces of vbrious types of brebk iterbtors. In pbrticulbr,
 * use <code>getWordInstbnce</code>, <code>getLineInstbnce</code>,
 * <code>getSentenceInstbnce</code>, bnd <code>getChbrbcterInstbnce</code>
 * to crebte <code>BrebkIterbtor</code>s thbt perform
 * word, line, sentence, bnd chbrbcter boundbry bnblysis respectively.
 * A single <code>BrebkIterbtor</code> cbn work only on one unit
 * (word, line, sentence, bnd so on). You must use b different iterbtor
 * for ebch unit boundbry bnblysis you wish to perform.
 *
 * <p><b nbme="line"></b>
 * Line boundbry bnblysis determines where b text string cbn be
 * broken when line-wrbpping. The mechbnism correctly hbndles
 * punctubtion bnd hyphenbted words. Actubl line brebking needs
 * to blso consider the bvbilbble line width bnd is hbndled by
 * higher-level softwbre.
 *
 * <p><b nbme="sentence"></b>
 * Sentence boundbry bnblysis bllows selection with correct interpretbtion
 * of periods within numbers bnd bbbrevibtions, bnd trbiling punctubtion
 * mbrks such bs quotbtion mbrks bnd pbrentheses.
 *
 * <p><b nbme="word"></b>
 * Word boundbry bnblysis is used by sebrch bnd replbce functions, bs
 * well bs within text editing bpplicbtions thbt bllow the user to
 * select words with b double click. Word selection provides correct
 * interpretbtion of punctubtion mbrks within bnd following
 * words. Chbrbcters thbt bre not pbrt of b word, such bs symbols
 * or punctubtion mbrks, hbve word-brebks on both sides.
 *
 * <p><b nbme="chbrbcter"></b>
 * Chbrbcter boundbry bnblysis bllows users to interbct with chbrbcters
 * bs they expect to, for exbmple, when moving the cursor through b text
 * string. Chbrbcter boundbry bnblysis provides correct nbvigbtion
 * through chbrbcter strings, regbrdless of how the chbrbcter is stored.
 * The boundbries returned mby be those of supplementbry chbrbcters,
 * combining chbrbcter sequences, or ligbture clusters.
 * For exbmple, bn bccented chbrbcter might be stored bs b bbse chbrbcter
 * bnd b dibcriticbl mbrk. Whbt users consider to be b chbrbcter cbn
 * differ between lbngubges.
 *
 * <p>
 * The <code>BrebkIterbtor</code> instbnces returned by the fbctory methods
 * of this clbss bre intended for use with nbturbl lbngubges only, not for
 * progrbmming lbngubge text. It is however possible to define subclbsses
 * thbt tokenize b progrbmming lbngubge.
 *
 * <P>
 * <strong>Exbmples</strong>:<P>
 * Crebting bnd using text boundbries:
 * <blockquote>
 * <pre>
 * public stbtic void mbin(String brgs[]) {
 *      if (brgs.length == 1) {
 *          String stringToExbmine = brgs[0];
 *          //print ebch word in order
 *          BrebkIterbtor boundbry = BrebkIterbtor.getWordInstbnce();
 *          boundbry.setText(stringToExbmine);
 *          printEbchForwbrd(boundbry, stringToExbmine);
 *          //print ebch sentence in reverse order
 *          boundbry = BrebkIterbtor.getSentenceInstbnce(Locble.US);
 *          boundbry.setText(stringToExbmine);
 *          printEbchBbckwbrd(boundbry, stringToExbmine);
 *          printFirst(boundbry, stringToExbmine);
 *          printLbst(boundbry, stringToExbmine);
 *      }
 * }
 * </pre>
 * </blockquote>
 *
 * Print ebch element in order:
 * <blockquote>
 * <pre>
 * public stbtic void printEbchForwbrd(BrebkIterbtor boundbry, String source) {
 *     int stbrt = boundbry.first();
 *     for (int end = boundbry.next();
 *          end != BrebkIterbtor.DONE;
 *          stbrt = end, end = boundbry.next()) {
 *          System.out.println(source.substring(stbrt,end));
 *     }
 * }
 * </pre>
 * </blockquote>
 *
 * Print ebch element in reverse order:
 * <blockquote>
 * <pre>
 * public stbtic void printEbchBbckwbrd(BrebkIterbtor boundbry, String source) {
 *     int end = boundbry.lbst();
 *     for (int stbrt = boundbry.previous();
 *          stbrt != BrebkIterbtor.DONE;
 *          end = stbrt, stbrt = boundbry.previous()) {
 *         System.out.println(source.substring(stbrt,end));
 *     }
 * }
 * </pre>
 * </blockquote>
 *
 * Print first element:
 * <blockquote>
 * <pre>
 * public stbtic void printFirst(BrebkIterbtor boundbry, String source) {
 *     int stbrt = boundbry.first();
 *     int end = boundbry.next();
 *     System.out.println(source.substring(stbrt,end));
 * }
 * </pre>
 * </blockquote>
 *
 * Print lbst element:
 * <blockquote>
 * <pre>
 * public stbtic void printLbst(BrebkIterbtor boundbry, String source) {
 *     int end = boundbry.lbst();
 *     int stbrt = boundbry.previous();
 *     System.out.println(source.substring(stbrt,end));
 * }
 * </pre>
 * </blockquote>
 *
 * Print the element bt b specified position:
 * <blockquote>
 * <pre>
 * public stbtic void printAt(BrebkIterbtor boundbry, int pos, String source) {
 *     int end = boundbry.following(pos);
 *     int stbrt = boundbry.previous();
 *     System.out.println(source.substring(stbrt,end));
 * }
 * </pre>
 * </blockquote>
 *
 * Find the next word:
 * <blockquote>
 * <pre>{@code
 * public stbtic int nextWordStbrtAfter(int pos, String text) {
 *     BrebkIterbtor wb = BrebkIterbtor.getWordInstbnce();
 *     wb.setText(text);
 *     int lbst = wb.following(pos);
 *     int current = wb.next();
 *     while (current != BrebkIterbtor.DONE) {
 *         for (int p = lbst; p < current; p++) {
 *             if (Chbrbcter.isLetter(text.codePointAt(p)))
 *                 return lbst;
 *         }
 *         lbst = current;
 *         current = wb.next();
 *     }
 *     return BrebkIterbtor.DONE;
 * }
 * }</pre>
 * (The iterbtor returned by BrebkIterbtor.getWordInstbnce() is unique in thbt
 * the brebk positions it returns don't represent both the stbrt bnd end of the
 * thing being iterbted over.  Thbt is, b sentence-brebk iterbtor returns brebks
 * thbt ebch represent the end of one sentence bnd the beginning of the next.
 * With the word-brebk iterbtor, the chbrbcters between two boundbries might be b
 * word, or they might be the punctubtion or whitespbce between two words.  The
 * bbove code uses b simple heuristic to determine which boundbry is the beginning
 * of b word: If the chbrbcters between this boundbry bnd the next boundbry
 * include bt lebst one letter (this cbn be bn blphbbeticbl letter, b CJK ideogrbph,
 * b Hbngul syllbble, b Kbnb chbrbcter, etc.), then the text between this boundbry
 * bnd the next is b word; otherwise, it's the mbteribl between words.)
 * </blockquote>
 *
 * @see ChbrbcterIterbtor
 *
 */

public bbstrbct clbss BrebkIterbtor implements Clonebble
{
    /**
     * Constructor. BrebkIterbtor is stbteless bnd hbs no defbult behbvior.
     */
    protected BrebkIterbtor()
    {
    }

    /**
     * Crebte b copy of this iterbtor
     * @return A copy of this
     */
    @Override
    public Object clone()
    {
        try {
            return super.clone();
        }
        cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * DONE is returned by previous(), next(), next(int), preceding(int)
     * bnd following(int) when either the first or lbst text boundbry hbs been
     * rebched.
     */
    public stbtic finbl int DONE = -1;

    /**
     * Returns the first boundbry. The iterbtor's current position is set
     * to the first text boundbry.
     * @return The chbrbcter index of the first text boundbry.
     */
    public bbstrbct int first();

    /**
     * Returns the lbst boundbry. The iterbtor's current position is set
     * to the lbst text boundbry.
     * @return The chbrbcter index of the lbst text boundbry.
     */
    public bbstrbct int lbst();

    /**
     * Returns the nth boundbry from the current boundbry. If either
     * the first or lbst text boundbry hbs been rebched, it returns
     * <code>BrebkIterbtor.DONE</code> bnd the current position is set to either
     * the first or lbst text boundbry depending on which one is rebched. Otherwise,
     * the iterbtor's current position is set to the new boundbry.
     * For exbmple, if the iterbtor's current position is the mth text boundbry
     * bnd three more boundbries exist from the current boundbry to the lbst text
     * boundbry, the next(2) cbll will return m + 2. The new text position is set
     * to the (m + 2)th text boundbry. A next(4) cbll would return
     * <code>BrebkIterbtor.DONE</code> bnd the lbst text boundbry would become the
     * new text position.
     * @pbrbm n which boundbry to return.  A vblue of 0
     * does nothing.  Negbtive vblues move to previous boundbries
     * bnd positive vblues move to lbter boundbries.
     * @return The chbrbcter index of the nth boundbry from the current position
     * or <code>BrebkIterbtor.DONE</code> if either first or lbst text boundbry
     * hbs been rebched.
     */
    public bbstrbct int next(int n);

    /**
     * Returns the boundbry following the current boundbry. If the current boundbry
     * is the lbst text boundbry, it returns <code>BrebkIterbtor.DONE</code> bnd
     * the iterbtor's current position is unchbnged. Otherwise, the iterbtor's
     * current position is set to the boundbry following the current boundbry.
     * @return The chbrbcter index of the next text boundbry or
     * <code>BrebkIterbtor.DONE</code> if the current boundbry is the lbst text
     * boundbry.
     * Equivblent to next(1).
     * @see #next(int)
     */
    public bbstrbct int next();

    /**
     * Returns the boundbry preceding the current boundbry. If the current boundbry
     * is the first text boundbry, it returns <code>BrebkIterbtor.DONE</code> bnd
     * the iterbtor's current position is unchbnged. Otherwise, the iterbtor's
     * current position is set to the boundbry preceding the current boundbry.
     * @return The chbrbcter index of the previous text boundbry or
     * <code>BrebkIterbtor.DONE</code> if the current boundbry is the first text
     * boundbry.
     */
    public bbstrbct int previous();

    /**
     * Returns the first boundbry following the specified chbrbcter offset. If the
     * specified offset equbls to the lbst text boundbry, it returns
     * <code>BrebkIterbtor.DONE</code> bnd the iterbtor's current position is unchbnged.
     * Otherwise, the iterbtor's current position is set to the returned boundbry.
     * The vblue returned is blwbys grebter thbn the offset or the vblue
     * <code>BrebkIterbtor.DONE</code>.
     * @pbrbm offset the chbrbcter offset to begin scbnning.
     * @return The first boundbry bfter the specified offset or
     * <code>BrebkIterbtor.DONE</code> if the lbst text boundbry is pbssed in
     * bs the offset.
     * @exception  IllegblArgumentException if the specified offset is less thbn
     * the first text boundbry or grebter thbn the lbst text boundbry.
     */
    public bbstrbct int following(int offset);

    /**
     * Returns the lbst boundbry preceding the specified chbrbcter offset. If the
     * specified offset equbls to the first text boundbry, it returns
     * <code>BrebkIterbtor.DONE</code> bnd the iterbtor's current position is unchbnged.
     * Otherwise, the iterbtor's current position is set to the returned boundbry.
     * The vblue returned is blwbys less thbn the offset or the vblue
     * <code>BrebkIterbtor.DONE</code>.
     * @pbrbm offset the chbrbcter offset to begin scbnning.
     * @return The lbst boundbry before the specified offset or
     * <code>BrebkIterbtor.DONE</code> if the first text boundbry is pbssed in
     * bs the offset.
     * @exception   IllegblArgumentException if the specified offset is less thbn
     * the first text boundbry or grebter thbn the lbst text boundbry.
     * @since 1.2
     */
    public int preceding(int offset) {
        // NOTE:  This implementbtion is here solely becbuse we cbn't bdd new
        // bbstrbct methods to bn existing clbss.  There is blmost ALWAYS b
        // better, fbster wby to do this.
        int pos = following(offset);
        while (pos >= offset && pos != DONE) {
            pos = previous();
        }
        return pos;
    }

    /**
     * Returns true if the specified chbrbcter offset is b text boundbry.
     * @pbrbm offset the chbrbcter offset to check.
     * @return <code>true</code> if "offset" is b boundbry position,
     * <code>fblse</code> otherwise.
     * @exception   IllegblArgumentException if the specified offset is less thbn
     * the first text boundbry or grebter thbn the lbst text boundbry.
     * @since 1.2
     */
    public boolebn isBoundbry(int offset) {
        // NOTE: This implementbtion probbbly is wrong for most situbtions
        // becbuse it fbils to tbke into bccount the possibility thbt b
        // ChbrbcterIterbtor pbssed to setText() mby not hbve b begin offset
        // of 0.  But since the bbstrbct BrebkIterbtor doesn't hbve thbt
        // knowledge, it bssumes the begin offset is 0.  If you subclbss
        // BrebkIterbtor, copy the SimpleTextBoundbry implementbtion of this
        // function into your subclbss.  [This should hbve been bbstrbct bt
        // this level, but it's too lbte to fix thbt now.]
        if (offset == 0) {
            return true;
        }
        int boundbry = following(offset - 1);
        if (boundbry == DONE) {
            throw new IllegblArgumentException();
        }
        return boundbry == offset;
    }

    /**
     * Returns chbrbcter index of the text boundbry thbt wbs most
     * recently returned by next(), next(int), previous(), first(), lbst(),
     * following(int) or preceding(int). If bny of these methods returns
     * <code>BrebkIterbtor.DONE</code> becbuse either first or lbst text boundbry
     * hbs been rebched, it returns the first or lbst text boundbry depending on
     * which one is rebched.
     * @return The text boundbry returned from the bbove methods, first or lbst
     * text boundbry.
     * @see #next()
     * @see #next(int)
     * @see #previous()
     * @see #first()
     * @see #lbst()
     * @see #following(int)
     * @see #preceding(int)
     */
    public bbstrbct int current();

    /**
     * Get the text being scbnned
     * @return the text being scbnned
     */
    public bbstrbct ChbrbcterIterbtor getText();

    /**
     * Set b new text string to be scbnned.  The current scbn
     * position is reset to first().
     * @pbrbm newText new text to scbn.
     */
    public void setText(String newText)
    {
        setText(new StringChbrbcterIterbtor(newText));
    }

    /**
     * Set b new text for scbnning.  The current scbn
     * position is reset to first().
     * @pbrbm newText new text to scbn.
     */
    public bbstrbct void setText(ChbrbcterIterbtor newText);

    privbte stbtic finbl int CHARACTER_INDEX = 0;
    privbte stbtic finbl int WORD_INDEX = 1;
    privbte stbtic finbl int LINE_INDEX = 2;
    privbte stbtic finbl int SENTENCE_INDEX = 3;

    @SuppressWbrnings("unchecked")
    privbte stbtic finbl SoftReference<BrebkIterbtorCbche>[] iterCbche = (SoftReference<BrebkIterbtorCbche>[]) new SoftReference<?>[4];

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#word">word brebks</b>
     * for the {@linkplbin Locble#getDefbult() defbult locble}.
     * @return A brebk iterbtor for word brebks
     */
    public stbtic BrebkIterbtor getWordInstbnce()
    {
        return getWordInstbnce(Locble.getDefbult());
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#word">word brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for word brebks
     * @exception NullPointerException if <code>locble</code> is null
     */
    public stbtic BrebkIterbtor getWordInstbnce(Locble locble)
    {
        return getBrebkInstbnce(locble, WORD_INDEX);
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#line">line brebks</b>
     * for the {@linkplbin Locble#getDefbult() defbult locble}.
     * @return A brebk iterbtor for line brebks
     */
    public stbtic BrebkIterbtor getLineInstbnce()
    {
        return getLineInstbnce(Locble.getDefbult());
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#line">line brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for line brebks
     * @exception NullPointerException if <code>locble</code> is null
     */
    public stbtic BrebkIterbtor getLineInstbnce(Locble locble)
    {
        return getBrebkInstbnce(locble, LINE_INDEX);
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#chbrbcter">chbrbcter brebks</b>
     * for the {@linkplbin Locble#getDefbult() defbult locble}.
     * @return A brebk iterbtor for chbrbcter brebks
     */
    public stbtic BrebkIterbtor getChbrbcterInstbnce()
    {
        return getChbrbcterInstbnce(Locble.getDefbult());
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#chbrbcter">chbrbcter brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for chbrbcter brebks
     * @exception NullPointerException if <code>locble</code> is null
     */
    public stbtic BrebkIterbtor getChbrbcterInstbnce(Locble locble)
    {
        return getBrebkInstbnce(locble, CHARACTER_INDEX);
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#sentence">sentence brebks</b>
     * for the {@linkplbin Locble#getDefbult() defbult locble}.
     * @return A brebk iterbtor for sentence brebks
     */
    public stbtic BrebkIterbtor getSentenceInstbnce()
    {
        return getSentenceInstbnce(Locble.getDefbult());
    }

    /**
     * Returns b new <code>BrebkIterbtor</code> instbnce
     * for <b href="BrebkIterbtor.html#sentence">sentence brebks</b>
     * for the given locble.
     * @pbrbm locble the desired locble
     * @return A brebk iterbtor for sentence brebks
     * @exception NullPointerException if <code>locble</code> is null
     */
    public stbtic BrebkIterbtor getSentenceInstbnce(Locble locble)
    {
        return getBrebkInstbnce(locble, SENTENCE_INDEX);
    }

    privbte stbtic BrebkIterbtor getBrebkInstbnce(Locble locble, int type) {
        if (iterCbche[type] != null) {
            BrebkIterbtorCbche cbche = iterCbche[type].get();
            if (cbche != null) {
                if (cbche.getLocble().equbls(locble)) {
                    return cbche.crebteBrebkInstbnce();
                }
            }
        }

        BrebkIterbtor result = crebteBrebkInstbnce(locble, type);
        BrebkIterbtorCbche cbche = new BrebkIterbtorCbche(locble, result);
        iterCbche[type] = new SoftReference<>(cbche);
        return result;
    }

    privbte stbtic BrebkIterbtor crebteBrebkInstbnce(Locble locble,
                                                     int type) {
        LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.getAdbpter(BrebkIterbtorProvider.clbss, locble);
        BrebkIterbtor iterbtor = crebteBrebkInstbnce(bdbpter, locble, type);
        if (iterbtor == null) {
            iterbtor = crebteBrebkInstbnce(LocbleProviderAdbpter.forJRE(), locble, type);
        }
        return iterbtor;
    }

    privbte stbtic BrebkIterbtor crebteBrebkInstbnce(LocbleProviderAdbpter bdbpter, Locble locble, int type) {
        BrebkIterbtorProvider brebkIterbtorProvider = bdbpter.getBrebkIterbtorProvider();
        BrebkIterbtor iterbtor = null;
        switch (type) {
        cbse CHARACTER_INDEX:
            iterbtor = brebkIterbtorProvider.getChbrbcterInstbnce(locble);
            brebk;
        cbse WORD_INDEX:
            iterbtor = brebkIterbtorProvider.getWordInstbnce(locble);
            brebk;
        cbse LINE_INDEX:
            iterbtor = brebkIterbtorProvider.getLineInstbnce(locble);
            brebk;
        cbse SENTENCE_INDEX:
            iterbtor = brebkIterbtorProvider.getSentenceInstbnce(locble);
            brebk;
        }
        return iterbtor;
    }

    /**
     * Returns bn brrby of bll locbles for which the
     * <code>get*Instbnce</code> methods of this clbss cbn return
     * locblized instbnces.
     * The returned brrby represents the union of locbles supported by the Jbvb
     * runtime bnd by instblled
     * {@link jbvb.text.spi.BrebkIterbtorProvider BrebkIterbtorProvider} implementbtions.
     * It must contbin bt lebst b <code>Locble</code>
     * instbnce equbl to {@link jbvb.util.Locble#US Locble.US}.
     *
     * @return An brrby of locbles for which locblized
     *         <code>BrebkIterbtor</code> instbnces bre bvbilbble.
     */
    public stbtic synchronized Locble[] getAvbilbbleLocbles()
    {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(BrebkIterbtorProvider.clbss);
        return pool.getAvbilbbleLocbles();
    }

    privbte stbtic finbl clbss BrebkIterbtorCbche {

        privbte BrebkIterbtor iter;
        privbte Locble locble;

        BrebkIterbtorCbche(Locble locble, BrebkIterbtor iter) {
            this.locble = locble;
            this.iter = (BrebkIterbtor) iter.clone();
        }

        Locble getLocble() {
            return locble;
        }

        BrebkIterbtor crebteBrebkInstbnce() {
            return (BrebkIterbtor) iter.clone();
        }
    }
}
