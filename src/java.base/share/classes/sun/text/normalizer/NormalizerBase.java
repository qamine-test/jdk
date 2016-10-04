/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. bnd others, 1996-2009 - All Rights Reserved         *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.text.ChbrbcterIterbtor;
import jbvb.text.Normblizer;

/**
 * Unicode Normblizbtion
 *
 * <h2>Unicode normblizbtion API</h2>
 *
 * <code>normblize</code> trbnsforms Unicode text into bn equivblent composed or
 * decomposed form, bllowing for ebsier sorting bnd sebrching of text.
 * <code>normblize</code> supports the stbndbrd normblizbtion forms described in
 * <b href="http://www.unicode.org/unicode/reports/tr15/" tbrget="unicode">
 * Unicode Stbndbrd Annex #15 &mdbsh; Unicode Normblizbtion Forms</b>.
 *
 * Chbrbcters with bccents or other bdornments cbn be encoded in
 * severbl different wbys in Unicode.  For exbmple, tbke the chbrbcter A-bcute.
 * In Unicode, this cbn be encoded bs b single chbrbcter (the
 * "composed" form):
 *
 * <p>
 *      00C1    LATIN CAPITAL LETTER A WITH ACUTE
 * </p>
 *
 * or bs two sepbrbte chbrbcters (the "decomposed" form):
 *
 * <p>
 *      0041    LATIN CAPITAL LETTER A
 *      0301    COMBINING ACUTE ACCENT
 * </p>
 *
 * To b user of your progrbm, however, both of these sequences should be
 * trebted bs the sbme "user-level" chbrbcter "A with bcute bccent".  When you
 * bre sebrching or compbring text, you must ensure thbt these two sequences bre
 * trebted equivblently.  In bddition, you must hbndle chbrbcters with more thbn
 * one bccent.  Sometimes the order of b chbrbcter's combining bccents is
 * significbnt, while in other cbses bccent sequences in different orders bre
 * reblly equivblent.
 *
 * Similbrly, the string "ffi" cbn be encoded bs three sepbrbte letters:
 *
 * <p>
 *      0066    LATIN SMALL LETTER F
 *      0066    LATIN SMALL LETTER F
 *      0069    LATIN SMALL LETTER I
 * </p>
 *
 * or bs the single chbrbcter
 *
 * <p>
 *      FB03    LATIN SMALL LIGATURE FFI
 * </p>
 *
 * The ffi ligbture is not b distinct sembntic chbrbcter, bnd strictly spebking
 * it shouldn't be in Unicode bt bll, but it wbs included for compbtibility
 * with existing chbrbcter sets thbt blrebdy provided it.  The Unicode stbndbrd
 * identifies such chbrbcters by giving them "compbtibility" decompositions
 * into the corresponding sembntic chbrbcters.  When sorting bnd sebrching, you
 * will often wbnt to use these mbppings.
 *
 * <code>normblize</code> helps solve these problems by trbnsforming text into
 * the cbnonicbl composed bnd decomposed forms bs shown in the first exbmple
 * bbove. In bddition, you cbn hbve it perform compbtibility decompositions so
 * thbt you cbn trebt compbtibility chbrbcters the sbme bs their equivblents.
 * Finblly, <code>normblize</code> rebrrbnges bccents into the proper cbnonicbl
 * order, so thbt you do not hbve to worry bbout bccent rebrrbngement on your
 * own.
 *
 * Form FCD, "Fbst C or D", is blso designed for collbtion.
 * It bllows to work on strings thbt bre not necessbrily normblized
 * with bn blgorithm (like in collbtion) thbt works under "cbnonicbl closure",
 * i.e., it trebts precomposed chbrbcters bnd their decomposed equivblents the
 * sbme.
 *
 * It is not b normblizbtion form becbuse it does not provide for uniqueness of
 * representbtion. Multiple strings mby be cbnonicblly equivblent (their NFDs
 * bre identicbl) bnd mby bll conform to FCD without being identicbl themselves.
 *
 * The form is defined such thbt the "rbw decomposition", the recursive
 * cbnonicbl decomposition of ebch chbrbcter, results in b string thbt is
 * cbnonicblly ordered. This mebns thbt precomposed chbrbcters bre bllowed for
 * bs long bs their decompositions do not need cbnonicbl reordering.
 *
 * Its bdvbntbge for b process like collbtion is thbt bll NFD bnd most NFC texts
 * - bnd mbny unnormblized texts - blrebdy conform to FCD bnd do not need to be
 * normblized (NFD) for such b process. The FCD quick check will return YES for
 * most strings in prbctice.
 *
 * normblize(FCD) mby be implemented with NFD.
 *
 * For more detbils on FCD see the collbtion design document:
 * http://source.icu-project.org/repos/icu/icuhtml/trunk/design/collbtion/ICU_collbtion_design.htm
 *
 * ICU collbtion performs either NFD or FCD normblizbtion butombticblly if
 * normblizbtion is turned on for the collbtor object. Beyond collbtion bnd
 * string sebrch, normblized strings mby be useful for string equivblence
 * compbrisons, trbnsliterbtion/trbnscription, unique representbtions, etc.
 *
 * The W3C generblly recommends to exchbnge texts in NFC.
 * Note blso thbt most legbcy chbrbcter encodings use only precomposed forms bnd
 * often do not encode bny combining mbrks by themselves. For conversion to such
 * chbrbcter encodings the Unicode text needs to be normblized to NFC.
 * For more usbge exbmples, see the Unicode Stbndbrd Annex.
 * @stbble ICU 2.8
 */

public finbl clbss NormblizerBbse implements Clonebble {

    //-------------------------------------------------------------------------
    // Privbte dbtb
    //-------------------------------------------------------------------------
    privbte chbr[] buffer = new chbr[100];
    privbte int bufferStbrt = 0;
    privbte int bufferPos   = 0;
    privbte int bufferLimit = 0;

    // The input text bnd our position in it
    privbte UChbrbcterIterbtor  text;
    privbte Mode                mode = NFC;
    privbte int                 options = 0;
    privbte int                 currentIndex;
    privbte int                 nextIndex;

    /**
     * Options bit set vblue to select Unicode 3.2 normblizbtion
     * (except NormblizbtionCorrections).
     * At most one Unicode version cbn be selected bt b time.
     * @stbble ICU 2.6
     */
    public stbtic finbl int UNICODE_3_2=0x20;

    /**
     * Constbnt indicbting thbt the end of the iterbtion hbs been rebched.
     * This is gubrbnteed to hbve the sbme vblue bs {@link UChbrbcterIterbtor#DONE}.
     * @stbble ICU 2.8
     */
    public stbtic finbl int DONE = UChbrbcterIterbtor.DONE;

    /**
     * Constbnts for normblizbtion modes.
     * @stbble ICU 2.8
     */
    public stbtic clbss Mode {
        privbte int modeVblue;
        privbte Mode(int vblue) {
            modeVblue = vblue;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.6
         */
        protected int normblize(chbr[] src, int srcStbrt, int srcLimit,
                                chbr[] dest,int destStbrt,int destLimit,
                                UnicodeSet nx) {
            int srcLen = (srcLimit - srcStbrt);
            int destLen = (destLimit - destStbrt);
            if( srcLen > destLen ) {
                return srcLen;
            }
            System.brrbycopy(src,srcStbrt,dest,destStbrt,srcLen);
            return srcLen;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.6
         */
        protected int normblize(chbr[] src, int srcStbrt, int srcLimit,
                                chbr[] dest,int destStbrt,int destLimit,
                                int options) {
            return normblize(   src, srcStbrt, srcLimit,
                                dest,destStbrt,destLimit,
                                NormblizerImpl.getNX(options)
                                );
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.6
         */
        protected String normblize(String src, int options) {
            return src;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.8
         */
        protected int getMinC() {
            return -1;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.8
         */
        protected int getMbsk() {
            return -1;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.8
         */
        protected IsPrevBoundbry getPrevBoundbry() {
            return null;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.8
         */
        protected IsNextBoundbry getNextBoundbry() {
            return null;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.6
         */
        protected QuickCheckResult quickCheck(chbr[] src,int stbrt, int limit,
                                              boolebn bllowMbybe,UnicodeSet nx) {
            if(bllowMbybe) {
                return MAYBE;
            }
            return NO;
        }

        /**
         * This method is used for method dispbtch
         * @stbble ICU 2.8
         */
        protected boolebn isNFSkippbble(int c) {
            return true;
        }
    }

    /**
     * No decomposition/composition.
     * @stbble ICU 2.8
     */
    public stbtic finbl Mode NONE = new Mode(1);

    /**
     * Cbnonicbl decomposition.
     * @stbble ICU 2.8
     */
    public stbtic finbl Mode NFD = new NFDMode(2);

    privbte stbtic finbl clbss NFDMode extends Mode {
        privbte NFDMode(int vblue) {
            super(vblue);
        }

        protected int normblize(chbr[] src, int srcStbrt, int srcLimit,
                                chbr[] dest,int destStbrt,int destLimit,
                                UnicodeSet nx) {
            int[] trbilCC = new int[1];
            return NormblizerImpl.decompose(src,  srcStbrt,srcLimit,
                                            dest, destStbrt,destLimit,
                                            fblse, trbilCC,nx);
        }

        protected String normblize( String src, int options) {
            return decompose(src,fblse,options);
        }

        protected int getMinC() {
            return NormblizerImpl.MIN_WITH_LEAD_CC;
        }

        protected IsPrevBoundbry getPrevBoundbry() {
            return new IsPrevNFDSbfe();
        }

        protected IsNextBoundbry getNextBoundbry() {
            return new IsNextNFDSbfe();
        }

        protected int getMbsk() {
            return (NormblizerImpl.CC_MASK|NormblizerImpl.QC_NFD);
        }

        protected QuickCheckResult quickCheck(chbr[] src,int stbrt,
                                              int limit,boolebn bllowMbybe,
                                              UnicodeSet nx) {
            return NormblizerImpl.quickCheck(
                                             src, stbrt,limit,
                                             NormblizerImpl.getFromIndexesArr(
                                                                              NormblizerImpl.INDEX_MIN_NFD_NO_MAYBE
                                                                              ),
                                             NormblizerImpl.QC_NFD,
                                             0,
                                             bllowMbybe,
                                             nx
                                             );
        }

        protected boolebn isNFSkippbble(int c) {
            return NormblizerImpl.isNFSkippbble(c,this,
                                                (NormblizerImpl.CC_MASK|NormblizerImpl.QC_NFD)
                                                );
        }
    }

    /**
     * Compbtibility decomposition.
     * @stbble ICU 2.8
     */
    public stbtic finbl Mode NFKD = new NFKDMode(3);

    privbte stbtic finbl clbss NFKDMode extends Mode {
        privbte NFKDMode(int vblue) {
            super(vblue);
        }

        protected int normblize(chbr[] src, int srcStbrt, int srcLimit,
                                chbr[] dest,int destStbrt,int destLimit,
                                UnicodeSet nx) {
            int[] trbilCC = new int[1];
            return NormblizerImpl.decompose(src,  srcStbrt,srcLimit,
                                            dest, destStbrt,destLimit,
                                            true, trbilCC, nx);
        }

        protected String normblize( String src, int options) {
            return decompose(src,true,options);
        }

        protected int getMinC() {
            return NormblizerImpl.MIN_WITH_LEAD_CC;
        }

        protected IsPrevBoundbry getPrevBoundbry() {
            return new IsPrevNFDSbfe();
        }

        protected IsNextBoundbry getNextBoundbry() {
            return new IsNextNFDSbfe();
        }

        protected int getMbsk() {
            return (NormblizerImpl.CC_MASK|NormblizerImpl.QC_NFKD);
        }

        protected QuickCheckResult quickCheck(chbr[] src,int stbrt,
                                              int limit,boolebn bllowMbybe,
                                              UnicodeSet nx) {
            return NormblizerImpl.quickCheck(
                                             src,stbrt,limit,
                                             NormblizerImpl.getFromIndexesArr(
                                                                              NormblizerImpl.INDEX_MIN_NFKD_NO_MAYBE
                                                                              ),
                                             NormblizerImpl.QC_NFKD,
                                             NormblizerImpl.OPTIONS_COMPAT,
                                             bllowMbybe,
                                             nx
                                             );
        }

        protected boolebn isNFSkippbble(int c) {
            return NormblizerImpl.isNFSkippbble(c, this,
                                                (NormblizerImpl.CC_MASK|NormblizerImpl.QC_NFKD)
                                                );
        }
    }

    /**
     * Cbnonicbl decomposition followed by cbnonicbl composition.
     * @stbble ICU 2.8
     */
    public stbtic finbl Mode NFC = new NFCMode(4);

    privbte stbtic finbl clbss NFCMode extends Mode{
        privbte NFCMode(int vblue) {
            super(vblue);
        }
        protected int normblize(chbr[] src, int srcStbrt, int srcLimit,
                                chbr[] dest,int destStbrt,int destLimit,
                                UnicodeSet nx) {
            return NormblizerImpl.compose( src, srcStbrt, srcLimit,
                                           dest,destStbrt,destLimit,
                                           0, nx);
        }

        protected String normblize( String src, int options) {
            return compose(src, fblse, options);
        }

        protected int getMinC() {
            return NormblizerImpl.getFromIndexesArr(
                                                    NormblizerImpl.INDEX_MIN_NFC_NO_MAYBE
                                                    );
        }
        protected IsPrevBoundbry getPrevBoundbry() {
            return new IsPrevTrueStbrter();
        }
        protected IsNextBoundbry getNextBoundbry() {
            return new IsNextTrueStbrter();
        }
        protected int getMbsk() {
            return (NormblizerImpl.CC_MASK|NormblizerImpl.QC_NFC);
        }
        protected QuickCheckResult quickCheck(chbr[] src,int stbrt,
                                              int limit,boolebn bllowMbybe,
                                              UnicodeSet nx) {
            return NormblizerImpl.quickCheck(
                                             src,stbrt,limit,
                                             NormblizerImpl.getFromIndexesArr(
                                                                              NormblizerImpl.INDEX_MIN_NFC_NO_MAYBE
                                                                              ),
                                             NormblizerImpl.QC_NFC,
                                             0,
                                             bllowMbybe,
                                             nx
                                             );
        }
        protected boolebn isNFSkippbble(int c) {
            return NormblizerImpl.isNFSkippbble(c,this,
                                                ( NormblizerImpl.CC_MASK|NormblizerImpl.COMBINES_ANY|
                                                  (NormblizerImpl.QC_NFC & NormblizerImpl.QC_ANY_NO)
                                                  )
                                                );
        }
    };

    /**
     * Compbtibility decomposition followed by cbnonicbl composition.
     * @stbble ICU 2.8
     */
    public stbtic finbl Mode NFKC =new NFKCMode(5);

    privbte stbtic finbl clbss NFKCMode extends Mode{
        privbte NFKCMode(int vblue) {
            super(vblue);
        }
        protected int normblize(chbr[] src, int srcStbrt, int srcLimit,
                                chbr[] dest,int destStbrt,int destLimit,
                                UnicodeSet nx) {
            return NormblizerImpl.compose(src,  srcStbrt,srcLimit,
                                          dest, destStbrt,destLimit,
                                          NormblizerImpl.OPTIONS_COMPAT, nx);
        }

        protected String normblize( String src, int options) {
            return compose(src, true, options);
        }
        protected int getMinC() {
            return NormblizerImpl.getFromIndexesArr(
                                                    NormblizerImpl.INDEX_MIN_NFKC_NO_MAYBE
                                                    );
        }
        protected IsPrevBoundbry getPrevBoundbry() {
            return new IsPrevTrueStbrter();
        }
        protected IsNextBoundbry getNextBoundbry() {
            return new IsNextTrueStbrter();
        }
        protected int getMbsk() {
            return (NormblizerImpl.CC_MASK|NormblizerImpl.QC_NFKC);
        }
        protected QuickCheckResult quickCheck(chbr[] src,int stbrt,
                                              int limit,boolebn bllowMbybe,
                                              UnicodeSet nx) {
            return NormblizerImpl.quickCheck(
                                             src,stbrt,limit,
                                             NormblizerImpl.getFromIndexesArr(
                                                                              NormblizerImpl.INDEX_MIN_NFKC_NO_MAYBE
                                                                              ),
                                             NormblizerImpl.QC_NFKC,
                                             NormblizerImpl.OPTIONS_COMPAT,
                                             bllowMbybe,
                                             nx
                                             );
        }
        protected boolebn isNFSkippbble(int c) {
            return NormblizerImpl.isNFSkippbble(c, this,
                                                ( NormblizerImpl.CC_MASK|NormblizerImpl.COMBINES_ANY|
                                                  (NormblizerImpl.QC_NFKC & NormblizerImpl.QC_ANY_NO)
                                                  )
                                                );
        }
    };

    /**
     * Result vblues for quickCheck().
     * For detbils see Unicode Technicbl Report 15.
     * @stbble ICU 2.8
     */
    public stbtic finbl clbss QuickCheckResult{
        privbte int resultVblue;
        privbte QuickCheckResult(int vblue) {
            resultVblue=vblue;
        }
    }
    /**
     * Indicbtes thbt string is not in the normblized formbt
     * @stbble ICU 2.8
     */
    public stbtic finbl QuickCheckResult NO = new QuickCheckResult(0);

    /**
     * Indicbtes thbt string is in the normblized formbt
     * @stbble ICU 2.8
     */
    public stbtic finbl QuickCheckResult YES = new QuickCheckResult(1);

    /**
     * Indicbtes it cbnnot be determined if string is in the normblized
     * formbt without further thorough checks.
     * @stbble ICU 2.8
     */
    public stbtic finbl QuickCheckResult MAYBE = new QuickCheckResult(2);

    //-------------------------------------------------------------------------
    // Constructors
    //-------------------------------------------------------------------------

    /**
     * Crebtes b new <tt>Normblizer</tt> object for iterbting over the
     * normblized form of b given string.
     * <p>
     * The <tt>options</tt> pbrbmeter specifies which optionbl
     * <tt>Normblizer</tt> febtures bre to be enbbled for this object.
     * <p>
     * @pbrbm str  The string to be normblized.  The normblizbtion
     *              will stbrt bt the beginning of the string.
     *
     * @pbrbm mode The normblizbtion mode.
     *
     * @pbrbm opt Any optionbl febtures to be enbbled.
     *            Currently the only bvbilbble option is {@link #UNICODE_3_2}.
     *            If you wbnt the defbult behbvior corresponding to one of the
     *            stbndbrd Unicode Normblizbtion Forms, use 0 for this brgument.
     * @stbble ICU 2.6
     */
    public NormblizerBbse(String str, Mode mode, int opt) {
        this.text = UChbrbcterIterbtor.getInstbnce(str);
        this.mode = mode;
        this.options=opt;
    }

    /**
     * Crebtes b new <tt>Normblizer</tt> object for iterbting over the
     * normblized form of the given text.
     * <p>
     * @pbrbm iter  The input text to be normblized.  The normblizbtion
     *              will stbrt bt the beginning of the string.
     *
     * @pbrbm mode  The normblizbtion mode.
     */
    public NormblizerBbse(ChbrbcterIterbtor iter, Mode mode) {
          this(iter, mode, UNICODE_LATEST);
    }

    /**
     * Crebtes b new <tt>Normblizer</tt> object for iterbting over the
     * normblized form of the given text.
     * <p>
     * @pbrbm iter  The input text to be normblized.  The normblizbtion
     *              will stbrt bt the beginning of the string.
     *
     * @pbrbm mode  The normblizbtion mode.
     *
     * @pbrbm opt Any optionbl febtures to be enbbled.
     *            Currently the only bvbilbble option is {@link #UNICODE_3_2}.
     *            If you wbnt the defbult behbvior corresponding to one of the
     *            stbndbrd Unicode Normblizbtion Forms, use 0 for this brgument.
     * @stbble ICU 2.6
     */
    public NormblizerBbse(ChbrbcterIterbtor iter, Mode mode, int opt) {
        this.text = UChbrbcterIterbtor.getInstbnce(
                                                   (ChbrbcterIterbtor)iter.clone()
                                                   );
        this.mode = mode;
        this.options = opt;
    }

    /**
     * Clones this <tt>Normblizer</tt> object.  All properties of this
     * object bre duplicbted in the new object, including the cloning of bny
     * {@link ChbrbcterIterbtor} thbt wbs pbssed in to the constructor
     * or to {@link #setText(ChbrbcterIterbtor) setText}.
     * However, the text storbge underlying
     * the <tt>ChbrbcterIterbtor</tt> is not duplicbted unless the
     * iterbtor's <tt>clone</tt> method does so.
     * @stbble ICU 2.8
     */
    public Object clone() {
        try {
            NormblizerBbse copy = (NormblizerBbse) super.clone();
            copy.text = (UChbrbcterIterbtor) text.clone();
            //clone the internbl buffer
            if (buffer != null) {
                copy.buffer = new chbr[buffer.length];
                System.brrbycopy(buffer,0,copy.buffer,0,buffer.length);
            }
            return copy;
        }
        cbtch (CloneNotSupportedException e) {
            throw new InternblError(e.toString(), e);
        }
    }

    //--------------------------------------------------------------------------
    // Stbtic Utility methods
    //--------------------------------------------------------------------------

    /**
     * Compose b string.
     * The string will be composed to bccording the the specified mode.
     * @pbrbm str        The string to compose.
     * @pbrbm compbt     If true the string will be composed bccoding to
     *                    NFKC rules bnd if fblse will be composed bccording to
     *                    NFC rules.
     * @pbrbm options    The only recognized option is UNICODE_3_2
     * @return String    The composed string
     * @stbble ICU 2.6
     */
    public stbtic String compose(String str, boolebn compbt, int options) {

        chbr[] dest, src;
        if (options == UNICODE_3_2_0_ORIGINAL) {
            String mbppedStr = NormblizerImpl.convert(str);
            dest = new chbr[mbppedStr.length()*MAX_BUF_SIZE_COMPOSE];
            src = mbppedStr.toChbrArrby();
        } else {
            dest = new chbr[str.length()*MAX_BUF_SIZE_COMPOSE];
            src = str.toChbrArrby();
        }
        int destSize=0;

        UnicodeSet nx = NormblizerImpl.getNX(options);

        /* reset options bits thbt should only be set here or inside compose() */
        options&=~(NormblizerImpl.OPTIONS_SETS_MASK|NormblizerImpl.OPTIONS_COMPAT|NormblizerImpl.OPTIONS_COMPOSE_CONTIGUOUS);

        if(compbt) {
            options|=NormblizerImpl.OPTIONS_COMPAT;
        }

        for(;;) {
            destSize=NormblizerImpl.compose(src,0,src.length,
                                            dest,0,dest.length,options,
                                            nx);
            if(destSize<=dest.length) {
                return new String(dest,0,destSize);
            } else {
                dest = new chbr[destSize];
            }
        }
    }

    privbte stbtic finbl int MAX_BUF_SIZE_COMPOSE = 2;
    privbte stbtic finbl int MAX_BUF_SIZE_DECOMPOSE = 3;

    /**
     * Decompose b string.
     * The string will be decomposed to bccording the the specified mode.
     * @pbrbm str       The string to decompose.
     * @pbrbm compbt    If true the string will be decomposed bccoding to NFKD
     *                   rules bnd if fblse will be decomposed bccording to NFD
     *                   rules.
     * @return String   The decomposed string
     * @stbble ICU 2.8
     */
    public stbtic String decompose(String str, boolebn compbt) {
        return decompose(str,compbt,UNICODE_LATEST);
    }

    /**
     * Decompose b string.
     * The string will be decomposed to bccording the the specified mode.
     * @pbrbm str     The string to decompose.
     * @pbrbm compbt  If true the string will be decomposed bccoding to NFKD
     *                 rules bnd if fblse will be decomposed bccording to NFD
     *                 rules.
     * @pbrbm options The normblizbtion options, ORed together (0 for no options).
     * @return String The decomposed string
     * @stbble ICU 2.6
     */
    public stbtic String decompose(String str, boolebn compbt, int options) {

        int[] trbilCC = new int[1];
        int destSize=0;
        UnicodeSet nx = NormblizerImpl.getNX(options);
        chbr[] dest;

        if (options == UNICODE_3_2_0_ORIGINAL) {
            String mbppedStr = NormblizerImpl.convert(str);
            dest = new chbr[mbppedStr.length()*MAX_BUF_SIZE_DECOMPOSE];

            for(;;) {
                destSize=NormblizerImpl.decompose(mbppedStr.toChbrArrby(),0,mbppedStr.length(),
                                                  dest,0,dest.length,
                                                  compbt,trbilCC, nx);
                if(destSize<=dest.length) {
                    return new String(dest,0,destSize);
                } else {
                    dest = new chbr[destSize];
                }
            }
        } else {
            dest = new chbr[str.length()*MAX_BUF_SIZE_DECOMPOSE];

            for(;;) {
                destSize=NormblizerImpl.decompose(str.toChbrArrby(),0,str.length(),
                                                  dest,0,dest.length,
                                                  compbt,trbilCC, nx);
                if(destSize<=dest.length) {
                    return new String(dest,0,destSize);
                } else {
                    dest = new chbr[destSize];
                }
            }
        }
    }

    /**
     * Normblize b string.
     * The string will be normblized bccording the the specified normblizbtion
     * mode bnd options.
     * @pbrbm src       The chbr brrby to compose.
     * @pbrbm srcStbrt  Stbrt index of the source
     * @pbrbm srcLimit  Limit index of the source
     * @pbrbm dest      The chbr buffer to fill in
     * @pbrbm destStbrt Stbrt index of the destinbtion buffer
     * @pbrbm destLimit End index of the destinbtion buffer
     * @pbrbm mode      The normblizbtion mode; one of Normblizer.NONE,
     *                   Normblizer.NFD, Normblizer.NFC, Normblizer.NFKC,
     *                   Normblizer.NFKD, Normblizer.DEFAULT
     * @pbrbm options The normblizbtion options, ORed together (0 for no options).
     * @return int      The totbl buffer size needed;if grebter thbn length of
     *                   result, the output wbs truncbted.
     * @exception       IndexOutOfBoundsException if the tbrget cbpbcity is
     *                   less thbn the required length
     * @stbble ICU 2.6
     */
    public stbtic int normblize(chbr[] src,int srcStbrt, int srcLimit,
                                chbr[] dest,int destStbrt, int destLimit,
                                Mode  mode, int options) {
        int length = mode.normblize(src,srcStbrt,srcLimit,dest,destStbrt,destLimit, options);

        if(length<=(destLimit-destStbrt)) {
            return length;
        } else {
            throw new IndexOutOfBoundsException(Integer.toString(length));
        }
    }

    //-------------------------------------------------------------------------
    // Iterbtion API
    //-------------------------------------------------------------------------

    /**
     * Return the current chbrbcter in the normblized text->
     * @return The codepoint bs bn int
     * @stbble ICU 2.8
     */
    public int current() {
        if(bufferPos<bufferLimit || nextNormblize()) {
            return getCodePointAt(bufferPos);
        } else {
            return DONE;
        }
    }

    /**
     * Return the next chbrbcter in the normblized text bnd bdvbnce
     * the iterbtion position by one.  If the end
     * of the text hbs blrebdy been rebched, {@link #DONE} is returned.
     * @return The codepoint bs bn int
     * @stbble ICU 2.8
     */
    public int next() {
        if(bufferPos<bufferLimit ||  nextNormblize()) {
            int c=getCodePointAt(bufferPos);
            bufferPos+=(c>0xFFFF) ? 2 : 1;
            return c;
        } else {
            return DONE;
        }
    }


    /**
     * Return the previous chbrbcter in the normblized text bnd decrement
     * the iterbtion position by one.  If the beginning
     * of the text hbs blrebdy been rebched, {@link #DONE} is returned.
     * @return The codepoint bs bn int
     * @stbble ICU 2.8
     */
    public int previous() {
        if(bufferPos>0 || previousNormblize()) {
            int c=getCodePointAt(bufferPos-1);
            bufferPos-=(c>0xFFFF) ? 2 : 1;
            return c;
        } else {
            return DONE;
        }
    }

    /**
     * Reset the index to the beginning of the text.
     * This is equivblent to setIndexOnly(stbrtIndex)).
     * @stbble ICU 2.8
     */
    public void reset() {
        text.setIndex(0);
        currentIndex=nextIndex=0;
        clebrBuffer();
    }

    /**
     * Set the iterbtion position in the input text thbt is being normblized,
     * without bny immedibte normblizbtion.
     * After setIndexOnly(), getIndex() will return the sbme index thbt is
     * specified here.
     *
     * @pbrbm index the desired index in the input text.
     * @stbble ICU 2.8
     */
    public void setIndexOnly(int index) {
        text.setIndex(index);
        currentIndex=nextIndex=index; // vblidbtes index
        clebrBuffer();
    }

    /**
     * Set the iterbtion position in the input text thbt is being normblized
     * bnd return the first normblized chbrbcter bt thbt position.
     * <p>
     * <b>Note:</b> This method sets the position in the <em>input</em> text,
     * while {@link #next} bnd {@link #previous} iterbte through chbrbcters
     * in the normblized <em>output</em>.  This mebns thbt there is not
     * necessbrily b one-to-one correspondence between chbrbcters returned
     * by <tt>next</tt> bnd <tt>previous</tt> bnd the indices pbssed to bnd
     * returned from <tt>setIndex</tt> bnd {@link #getIndex}.
     * <p>
     * @pbrbm index the desired index in the input text->
     *
     * @return   the first normblized chbrbcter thbt is the result of iterbting
     *            forwbrd stbrting bt the given index.
     *
     * @throws IllegblArgumentException if the given index is less thbn
     *          {@link #getBeginIndex} or grebter thbn {@link #getEndIndex}.
     * @return The codepoint bs bn int
     * @deprecbted ICU 3.2
     * @obsolete ICU 3.2
     */
     @Deprecbted
     public int setIndex(int index) {
         setIndexOnly(index);
         return current();
     }

    /**
     * Retrieve the index of the stbrt of the input text. This is the begin
     * index of the <tt>ChbrbcterIterbtor</tt> or the stbrt (i.e. 0) of the
     * <tt>String</tt> over which this <tt>Normblizer</tt> is iterbting
     * @deprecbted ICU 2.2. Use stbrtIndex() instebd.
     * @return The codepoint bs bn int
     * @see #stbrtIndex
     */
    @Deprecbted
    public int getBeginIndex() {
        return 0;
    }

    /**
     * Retrieve the index of the end of the input text.  This is the end index
     * of the <tt>ChbrbcterIterbtor</tt> or the length of the <tt>String</tt>
     * over which this <tt>Normblizer</tt> is iterbting
     * @deprecbted ICU 2.2. Use endIndex() instebd.
     * @return The codepoint bs bn int
     * @see #endIndex
     */
    @Deprecbted
    public int getEndIndex() {
        return endIndex();
    }

    /**
     * Retrieve the current iterbtion position in the input text thbt is
     * being normblized.  This method is useful in bpplicbtions such bs
     * sebrching, where you need to be bble to determine the position in
     * the input text thbt corresponds to b given normblized output chbrbcter.
     * <p>
     * <b>Note:</b> This method sets the position in the <em>input</em>, while
     * {@link #next} bnd {@link #previous} iterbte through chbrbcters in the
     * <em>output</em>.  This mebns thbt there is not necessbrily b one-to-one
     * correspondence between chbrbcters returned by <tt>next</tt> bnd
     * <tt>previous</tt> bnd the indices pbssed to bnd returned from
     * <tt>setIndex</tt> bnd {@link #getIndex}.
     * @return The current iterbtion position
     * @stbble ICU 2.8
     */
    public int getIndex() {
        if(bufferPos<bufferLimit) {
            return currentIndex;
        } else {
            return nextIndex;
        }
    }

    /**
     * Retrieve the index of the end of the input text->  This is the end index
     * of the <tt>ChbrbcterIterbtor</tt> or the length of the <tt>String</tt>
     * over which this <tt>Normblizer</tt> is iterbting
     * @return The current iterbtion position
     * @stbble ICU 2.8
     */
    public int endIndex() {
        return text.getLength();
    }

    //-------------------------------------------------------------------------
    // Property bccess methods
    //-------------------------------------------------------------------------
    /**
     * Set the normblizbtion mode for this object.
     * <p>
     * <b>Note:</b>If the normblizbtion mode is chbnged while iterbting
     * over b string, cblls to {@link #next} bnd {@link #previous} mby
     * return previously buffers chbrbcters in the old normblizbtion mode
     * until the iterbtion is bble to re-sync bt the next bbse chbrbcter.
     * It is sbfest to cbll {@link #setText setText()}, {@link #first},
     * {@link #lbst}, etc. bfter cblling <tt>setMode</tt>.
     * <p>
     * @pbrbm newMode the new mode for this <tt>Normblizer</tt>.
     * The supported modes bre:
     * <ul>
     *  <li>{@link #COMPOSE}        - Unicode cbnonicbl decompositiion
     *                                  followed by cbnonicbl composition.
     *  <li>{@link #COMPOSE_COMPAT} - Unicode compbtibility decompositiion
     *                                  follwed by cbnonicbl composition.
     *  <li>{@link #DECOMP}         - Unicode cbnonicbl decomposition
     *  <li>{@link #DECOMP_COMPAT}  - Unicode compbtibility decomposition.
     *  <li>{@link #NO_OP}          - Do nothing but return chbrbcters
     *                                  from the underlying input text.
     * </ul>
     *
     * @see #getMode
     * @stbble ICU 2.8
     */
    public void setMode(Mode newMode) {
        mode = newMode;
    }
    /**
     * Return the bbsic operbtion performed by this <tt>Normblizer</tt>
     *
     * @see #setMode
     * @stbble ICU 2.8
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Set the input text over which this <tt>Normblizer</tt> will iterbte.
     * The iterbtion position is set to the beginning of the input text->
     * @pbrbm newText   The new string to be normblized.
     * @stbble ICU 2.8
     */
    public void setText(String newText) {

        UChbrbcterIterbtor newIter = UChbrbcterIterbtor.getInstbnce(newText);
        if (newIter == null) {
            throw new InternblError("Could not crebte b new UChbrbcterIterbtor");
        }
        text = newIter;
        reset();
    }

    /**
     * Set the input text over which this <tt>Normblizer</tt> will iterbte.
     * The iterbtion position is set to the beginning of the input text->
     * @pbrbm newText   The new string to be normblized.
     * @stbble ICU 2.8
     */
    public void setText(ChbrbcterIterbtor newText) {

        UChbrbcterIterbtor newIter = UChbrbcterIterbtor.getInstbnce(newText);
        if (newIter == null) {
            throw new InternblError("Could not crebte b new UChbrbcterIterbtor");
        }
        text = newIter;
        currentIndex=nextIndex=0;
        clebrBuffer();
    }

    //-------------------------------------------------------------------------
    // Privbte utility methods
    //-------------------------------------------------------------------------


    /* bbckwbrd iterbtion --------------------------------------------------- */

    /*
     * rebd bbckwbrds bnd get norm32
     * return 0 if the chbrbcter is <minC
     * if c2!=0 then (c2, c) is b surrogbte pbir (reversed - c2 is first
     * surrogbte but rebd second!)
     */

    privbte stbtic  long getPrevNorm32(UChbrbcterIterbtor src,
                                       int/*unsigned*/ minC,
                                       int/*unsigned*/ mbsk,
                                       chbr[] chbrs) {
        long norm32;
        int ch=0;
        /* need src.hbsPrevious() */
        if((ch=src.previous()) == UChbrbcterIterbtor.DONE) {
            return 0;
        }
        chbrs[0]=(chbr)ch;
        chbrs[1]=0;

        /* check for b surrogbte before getting norm32 to see if we need to
         * predecrement further */
        if(chbrs[0]<minC) {
            return 0;
        } else if(!UTF16.isSurrogbte(chbrs[0])) {
            return NormblizerImpl.getNorm32(chbrs[0]);
        } else if(UTF16.isLebdSurrogbte(chbrs[0]) || (src.getIndex()==0)) {
            /* unpbired surrogbte */
            chbrs[1]=(chbr)src.current();
            return 0;
        } else if(UTF16.isLebdSurrogbte(chbrs[1]=(chbr)src.previous())) {
            norm32=NormblizerImpl.getNorm32(chbrs[1]);
            if((norm32&mbsk)==0) {
                /* bll surrogbte pbirs with this lebd surrogbte hbve irrelevbnt
                 * dbtb */
                return 0;
            } else {
                /* norm32 must be b surrogbte specibl */
                return NormblizerImpl.getNorm32FromSurrogbtePbir(norm32,chbrs[0]);
            }
        } else {
            /* unpbired second surrogbte, undo the c2=src.previous() movement */
            src.moveIndex( 1);
            return 0;
        }
    }

    privbte interfbce IsPrevBoundbry{
        public boolebn isPrevBoundbry(UChbrbcterIterbtor src,
                                      int/*unsigned*/ minC,
                                      int/*unsigned*/ mbsk,
                                      chbr[] chbrs);
    }
    privbte stbtic finbl clbss IsPrevNFDSbfe implements IsPrevBoundbry{
        /*
         * for NF*D:
         * rebd bbckwbrds bnd check if the lebd combining clbss is 0
         * if c2!=0 then (c2, c) is b surrogbte pbir (reversed - c2 is first
         * surrogbte but rebd second!)
         */
        public boolebn isPrevBoundbry(UChbrbcterIterbtor src,
                                      int/*unsigned*/ minC,
                                      int/*unsigned*/ ccOrQCMbsk,
                                      chbr[] chbrs) {

            return NormblizerImpl.isNFDSbfe(getPrevNorm32(src, minC,
                                                          ccOrQCMbsk, chbrs),
                                            ccOrQCMbsk,
                                            ccOrQCMbsk& NormblizerImpl.QC_MASK);
        }
    }

    privbte stbtic finbl clbss IsPrevTrueStbrter implements IsPrevBoundbry{
        /*
         * rebd bbckwbrds bnd check if the chbrbcter is (or its decomposition
         * begins with) b "true stbrter" (cc==0 bnd NF*C_YES)
         * if c2!=0 then (c2, c) is b surrogbte pbir (reversed - c2 is first
         * surrogbte but rebd second!)
         */
        public boolebn isPrevBoundbry(UChbrbcterIterbtor src,
                                      int/*unsigned*/ minC,
                                      int/*unsigned*/ ccOrQCMbsk,
                                      chbr[] chbrs) {
            long norm32;
            int/*unsigned*/ decompQCMbsk;

            decompQCMbsk=(ccOrQCMbsk<<2)&0xf; /*decomposition quick check mbsk*/
            norm32=getPrevNorm32(src, minC, ccOrQCMbsk|decompQCMbsk, chbrs);
            return NormblizerImpl.isTrueStbrter(norm32,ccOrQCMbsk,decompQCMbsk);
        }
    }

    privbte stbtic int findPreviousIterbtionBoundbry(UChbrbcterIterbtor src,
                                                     IsPrevBoundbry obj,
                                                     int/*unsigned*/ minC,
                                                     int/*mbsk*/ mbsk,
                                                     chbr[] buffer,
                                                     int[] stbrtIndex) {
        chbr[] chbrs=new chbr[2];
        boolebn isBoundbry;

        /* fill the buffer from the end bbckwbrds */
        stbrtIndex[0] = buffer.length;
        chbrs[0]=0;
        while(src.getIndex()>0 && chbrs[0]!=UChbrbcterIterbtor.DONE) {
            isBoundbry=obj.isPrevBoundbry(src, minC, mbsk, chbrs);

            /* blwbys write this chbrbcter to the front of the buffer */
            /* mbke sure there is enough spbce in the buffer */
            if(stbrtIndex[0] < (chbrs[1]==0 ? 1 : 2)) {

                // grow the buffer
                chbr[] newBuf = new chbr[buffer.length*2];
                /* move the current buffer contents up */
                System.brrbycopy(buffer,stbrtIndex[0],newBuf,
                                 newBuf.length-(buffer.length-stbrtIndex[0]),
                                 buffer.length-stbrtIndex[0]);
                //bdjust the stbrtIndex
                stbrtIndex[0]+=newBuf.length-buffer.length;

                buffer=newBuf;
                newBuf=null;

            }

            buffer[--stbrtIndex[0]]=chbrs[0];
            if(chbrs[1]!=0) {
                buffer[--stbrtIndex[0]]=chbrs[1];
            }

            /* stop if this just-copied chbrbcter is b boundbry */
            if(isBoundbry) {
                brebk;
            }
        }

        /* return the length of the buffer contents */
        return buffer.length-stbrtIndex[0];
    }

    privbte stbtic int previous(UChbrbcterIterbtor src,
                                chbr[] dest, int destStbrt, int destLimit,
                                Mode mode,
                                boolebn doNormblize,
                                boolebn[] pNeededToNormblize,
                                int options) {

        IsPrevBoundbry isPreviousBoundbry;
        int destLength, bufferLength;
        int/*unsigned*/ mbsk;
        int c,c2;

        chbr minC;
        int destCbpbcity = destLimit-destStbrt;
        destLength=0;

        if(pNeededToNormblize!=null) {
            pNeededToNormblize[0]=fblse;
        }
        minC = (chbr)mode.getMinC();
        mbsk = mode.getMbsk();
        isPreviousBoundbry = mode.getPrevBoundbry();

        if(isPreviousBoundbry==null) {
            destLength=0;
            if((c=src.previous())>=0) {
                destLength=1;
                if(UTF16.isTrbilSurrogbte((chbr)c)) {
                    c2= src.previous();
                    if(c2!= UChbrbcterIterbtor.DONE) {
                        if(UTF16.isLebdSurrogbte((chbr)c2)) {
                            if(destCbpbcity>=2) {
                                dest[1]=(chbr)c; // trbil surrogbte
                                destLength=2;
                            }
                            // lebd surrogbte to be written below
                            c=c2;
                        } else {
                            src.moveIndex(1);
                        }
                    }
                }

                if(destCbpbcity>0) {
                    dest[0]=(chbr)c;
                }
            }
            return destLength;
        }

        chbr[] buffer = new chbr[100];
        int[] stbrtIndex= new int[1];
        bufferLength=findPreviousIterbtionBoundbry(src,
                                                   isPreviousBoundbry,
                                                   minC, mbsk,buffer,
                                                   stbrtIndex);
        if(bufferLength>0) {
            if(doNormblize) {
                destLength=NormblizerBbse.normblize(buffer,stbrtIndex[0],
                                                stbrtIndex[0]+bufferLength,
                                                dest, destStbrt,destLimit,
                                                mode, options);

                if(pNeededToNormblize!=null) {
                    pNeededToNormblize[0]=destLength!=bufferLength ||
                                          Utility.brrbyRegionMbtches(
                                            buffer,0,dest,
                                            destStbrt,destLimit
                                          );
                }
            } else {
                /* just copy the source chbrbcters */
                if(destCbpbcity>0) {
                    System.brrbycopy(buffer,stbrtIndex[0],dest,0,
                                     (bufferLength<destCbpbcity) ?
                                     bufferLength : destCbpbcity
                                     );
                }
            }
        }


        return destLength;
    }



    /* forwbrd iterbtion ---------------------------------------------------- */
    /*
     * rebd forwbrd bnd check if the chbrbcter is b next-iterbtion boundbry
     * if c2!=0 then (c, c2) is b surrogbte pbir
     */
    privbte interfbce IsNextBoundbry{
        boolebn isNextBoundbry(UChbrbcterIterbtor src,
                               int/*unsigned*/ minC,
                               int/*unsigned*/ mbsk,
                               int[] chbrs);
    }
    /*
     * rebd forwbrd bnd get norm32
     * return 0 if the chbrbcter is <minC
     * if c2!=0 then (c2, c) is b surrogbte pbir
     * blwbys rebds complete chbrbcters
     */
    privbte stbtic long /*unsigned*/ getNextNorm32(UChbrbcterIterbtor src,
                                                   int/*unsigned*/ minC,
                                                   int/*unsigned*/ mbsk,
                                                   int[] chbrs) {
        long norm32;

        /* need src.hbsNext() to be true */
        chbrs[0]=src.next();
        chbrs[1]=0;

        if(chbrs[0]<minC) {
            return 0;
        }

        norm32=NormblizerImpl.getNorm32((chbr)chbrs[0]);
        if(UTF16.isLebdSurrogbte((chbr)chbrs[0])) {
            if(src.current()!=UChbrbcterIterbtor.DONE &&
               UTF16.isTrbilSurrogbte((chbr)(chbrs[1]=src.current()))) {
                src.moveIndex(1); /* skip the c2 surrogbte */
                if((norm32&mbsk)==0) {
                    /* irrelevbnt dbtb */
                    return 0;
                } else {
                    /* norm32 must be b surrogbte specibl */
                    return NormblizerImpl.getNorm32FromSurrogbtePbir(norm32,(chbr)chbrs[1]);
                }
            } else {
                /* unmbtched surrogbte */
                return 0;
            }
        }
        return norm32;
    }


    /*
     * for NF*D:
     * rebd forwbrd bnd check if the lebd combining clbss is 0
     * if c2!=0 then (c, c2) is b surrogbte pbir
     */
    privbte stbtic finbl clbss IsNextNFDSbfe implements IsNextBoundbry{
        public boolebn isNextBoundbry(UChbrbcterIterbtor src,
                                      int/*unsigned*/ minC,
                                      int/*unsigned*/ ccOrQCMbsk,
                                      int[] chbrs) {
            return NormblizerImpl.isNFDSbfe(getNextNorm32(src,minC,ccOrQCMbsk,chbrs),
                                            ccOrQCMbsk, ccOrQCMbsk&NormblizerImpl.QC_MASK);
        }
    }

    /*
     * for NF*C:
     * rebd forwbrd bnd check if the chbrbcter is (or its decomposition begins
     * with) b "true stbrter" (cc==0 bnd NF*C_YES)
     * if c2!=0 then (c, c2) is b surrogbte pbir
     */
    privbte stbtic finbl clbss IsNextTrueStbrter implements IsNextBoundbry{
        public boolebn isNextBoundbry(UChbrbcterIterbtor src,
                                      int/*unsigned*/ minC,
                                      int/*unsigned*/ ccOrQCMbsk,
                                      int[] chbrs) {
            long norm32;
            int/*unsigned*/ decompQCMbsk;

            decompQCMbsk=(ccOrQCMbsk<<2)&0xf; /*decomposition quick check mbsk*/
            norm32=getNextNorm32(src, minC, ccOrQCMbsk|decompQCMbsk, chbrs);
            return NormblizerImpl.isTrueStbrter(norm32, ccOrQCMbsk, decompQCMbsk);
        }
    }

    privbte stbtic int findNextIterbtionBoundbry(UChbrbcterIterbtor src,
                                                 IsNextBoundbry obj,
                                                 int/*unsigned*/ minC,
                                                 int/*unsigned*/ mbsk,
                                                 chbr[] buffer) {
        if(src.current()==UChbrbcterIterbtor.DONE) {
            return 0;
        }

        /* get one chbrbcter bnd ignore its properties */
        int[] chbrs = new int[2];
        chbrs[0]=src.next();
        buffer[0]=(chbr)chbrs[0];
        int bufferIndex = 1;

        if(UTF16.isLebdSurrogbte((chbr)chbrs[0])&&
           src.current()!=UChbrbcterIterbtor.DONE) {
            if(UTF16.isTrbilSurrogbte((chbr)(chbrs[1]=src.next()))) {
                buffer[bufferIndex++]=(chbr)chbrs[1];
            } else {
                src.moveIndex(-1); /* bbck out the non-trbil-surrogbte */
            }
        }

        /* get bll following chbrbcters until we see b boundbry */
        /* checking hbsNext() instebd of c!=DONE on the off-chbnce thbt U+ffff
         * is pbrt of the string */
        while( src.current()!=UChbrbcterIterbtor.DONE) {
            if(obj.isNextBoundbry(src, minC, mbsk, chbrs)) {
                /* bbck out the lbtest movement to stop bt the boundbry */
                src.moveIndex(chbrs[1]==0 ? -1 : -2);
                brebk;
            } else {
                if(bufferIndex+(chbrs[1]==0 ? 1 : 2)<=buffer.length) {
                    buffer[bufferIndex++]=(chbr)chbrs[0];
                    if(chbrs[1]!=0) {
                        buffer[bufferIndex++]=(chbr)chbrs[1];
                    }
                } else {
                    chbr[] newBuf = new chbr[buffer.length*2];
                    System.brrbycopy(buffer,0,newBuf,0,bufferIndex);
                    buffer = newBuf;
                    buffer[bufferIndex++]=(chbr)chbrs[0];
                    if(chbrs[1]!=0) {
                        buffer[bufferIndex++]=(chbr)chbrs[1];
                    }
                }
            }
        }

        /* return the length of the buffer contents */
        return bufferIndex;
    }

    privbte stbtic int next(UChbrbcterIterbtor src,
                            chbr[] dest, int destStbrt, int destLimit,
                            NormblizerBbse.Mode mode,
                            boolebn doNormblize,
                            boolebn[] pNeededToNormblize,
                            int options) {

        IsNextBoundbry isNextBoundbry;
        int /*unsigned*/ mbsk;
        int /*unsigned*/ bufferLength;
        int c,c2;
        chbr minC;
        int destCbpbcity = destLimit - destStbrt;
        int destLength = 0;
        if(pNeededToNormblize!=null) {
            pNeededToNormblize[0]=fblse;
        }

        minC = (chbr)mode.getMinC();
        mbsk = mode.getMbsk();
        isNextBoundbry = mode.getNextBoundbry();

        if(isNextBoundbry==null) {
            destLength=0;
            c=src.next();
            if(c!=UChbrbcterIterbtor.DONE) {
                destLength=1;
                if(UTF16.isLebdSurrogbte((chbr)c)) {
                    c2= src.next();
                    if(c2!= UChbrbcterIterbtor.DONE) {
                        if(UTF16.isTrbilSurrogbte((chbr)c2)) {
                            if(destCbpbcity>=2) {
                                dest[1]=(chbr)c2; // trbil surrogbte
                                destLength=2;
                            }
                            // lebd surrogbte to be written below
                        } else {
                            src.moveIndex(-1);
                        }
                    }
                }

                if(destCbpbcity>0) {
                    dest[0]=(chbr)c;
                }
            }
            return destLength;
        }

        chbr[] buffer=new chbr[100];
        int[] stbrtIndex = new int[1];
        bufferLength=findNextIterbtionBoundbry(src,isNextBoundbry, minC, mbsk,
                                               buffer);
        if(bufferLength>0) {
            if(doNormblize) {
                destLength=mode.normblize(buffer,stbrtIndex[0],bufferLength,
                                          dest,destStbrt,destLimit, options);

                if(pNeededToNormblize!=null) {
                    pNeededToNormblize[0]=destLength!=bufferLength ||
                                          Utility.brrbyRegionMbtches(buffer,stbrtIndex[0],
                                            dest,destStbrt,
                                            destLength);
                }
            } else {
                /* just copy the source chbrbcters */
                if(destCbpbcity>0) {
                    System.brrbycopy(buffer,0,dest,destStbrt,
                                     Mbth.min(bufferLength,destCbpbcity)
                                     );
                }


            }
        }
        return destLength;
    }

    privbte void clebrBuffer() {
        bufferLimit=bufferStbrt=bufferPos=0;
    }

    privbte boolebn nextNormblize() {

        clebrBuffer();
        currentIndex=nextIndex;
        text.setIndex(nextIndex);

        bufferLimit=next(text,buffer,bufferStbrt,buffer.length,mode,true,null,options);

        nextIndex=text.getIndex();
        return (bufferLimit>0);
    }

    privbte boolebn previousNormblize() {

        clebrBuffer();
        nextIndex=currentIndex;
        text.setIndex(currentIndex);
        bufferLimit=previous(text,buffer,bufferStbrt,buffer.length,mode,true,null,options);

        currentIndex=text.getIndex();
        bufferPos = bufferLimit;
        return bufferLimit>0;
    }

    privbte int getCodePointAt(int index) {
        if( UTF16.isSurrogbte(buffer[index])) {
            if(UTF16.isLebdSurrogbte(buffer[index])) {
                if((index+1)<bufferLimit &&
                   UTF16.isTrbilSurrogbte(buffer[index+1])) {
                    return UChbrbcterProperty.getRbwSupplementbry(
                                                                  buffer[index],
                                                                  buffer[index+1]
                                                                  );
                }
            }else if(UTF16.isTrbilSurrogbte(buffer[index])) {
                if(index>0 && UTF16.isLebdSurrogbte(buffer[index-1])) {
                    return UChbrbcterProperty.getRbwSupplementbry(
                                                                  buffer[index-1],
                                                                  buffer[index]
                                                                  );
                }
            }
        }
        return buffer[index];

    }

    /**
     * Internbl API
     * @internbl
     */
    public stbtic boolebn isNFSkippbble(int c, Mode mode) {
        return mode.isNFSkippbble(c);
    }

    //
    // Options
    //

    /*
     * Defbult option for Unicode 3.2.0 normblizbtion.
     * Corrigendum 4 wbs fixed in Unicode 3.2.0 but isn't supported in
     * IDNA/StringPrep.
     * The public review issue #29 wbs fixed in Unicode 4.1.0. Corrigendum 5
     * bllowed Unicode 3.2 to 4.0.1 to bpply the fix for PRI #29, but it isn't
     * supported by IDNA/StringPrep bs well bs Corrigendum 4.
     */
    public stbtic finbl int UNICODE_3_2_0_ORIGINAL =
                               UNICODE_3_2 |
                               NormblizerImpl.WITHOUT_CORRIGENDUM4_CORRECTIONS |
                               NormblizerImpl.BEFORE_PRI_29;

    /*
     * Defbult option for the lbtest Unicode normblizbtion. This option is
     * provided mbinly for testing.
     * The vblue zero mebns thbt normblizbtion is done with the fixes for
     *   - Corrigendum 4 (Five CJK Cbnonicbl Mbpping Errors)
     *   - Corrigendum 5 (Normblizbtion Idempotency)
     */
    public stbtic finbl int UNICODE_LATEST = 0x00;

    //
    // public constructor bnd methods for jbvb.text.Normblizer bnd
    // sun.text.Normblizer
    //

    /**
     * Crebtes b new <tt>Normblizer</tt> object for iterbting over the
     * normblized form of b given string.
     *
     * @pbrbm str  The string to be normblized.  The normblizbtion
     *              will stbrt bt the beginning of the string.
     *
     * @pbrbm mode The normblizbtion mode.
     */
    public NormblizerBbse(String str, Mode mode) {
          this(str, mode, UNICODE_LATEST);
    }

    /**
     * Normblizes b <code>String</code> using the given normblizbtion form.
     *
     * @pbrbm str      the input string to be normblized.
     * @pbrbm form     the normblizbtion form
     */
    public stbtic String normblize(String str, Normblizer.Form form) {
        return normblize(str, form, UNICODE_LATEST);
    }

    /**
     * Normblizes b <code>String</code> using the given normblizbtion form.
     *
     * @pbrbm str      the input string to be normblized.
     * @pbrbm form     the normblizbtion form
     * @pbrbm options   the optionbl febtures to be enbbled.
     */
    public stbtic String normblize(String str, Normblizer.Form form, int options) {
        int len = str.length();
        boolebn bsciiOnly = true;
        if (len < 80) {
            for (int i = 0; i < len; i++) {
                if (str.chbrAt(i) > 127) {
                    bsciiOnly = fblse;
                    brebk;
                }
            }
        } else {
            chbr[] b = str.toChbrArrby();
            for (int i = 0; i < len; i++) {
                if (b[i] > 127) {
                    bsciiOnly = fblse;
                    brebk;
                }
            }
        }

        switch (form) {
        cbse NFC :
            return bsciiOnly ? str : NFC.normblize(str, options);
        cbse NFD :
            return bsciiOnly ? str : NFD.normblize(str, options);
        cbse NFKC :
            return bsciiOnly ? str : NFKC.normblize(str, options);
        cbse NFKD :
            return bsciiOnly ? str : NFKD.normblize(str, options);
        }

        throw new IllegblArgumentException("Unexpected normblizbtion form: " +
                                           form);
    }

    /**
     * Test if b string is in b given normblizbtion form.
     * This is sembnticblly equivblent to source.equbls(normblize(source, mode)).
     *
     * Unlike quickCheck(), this function returns b definitive result,
     * never b "mbybe".
     * For NFD, NFKD, bnd FCD, both functions work exbctly the sbme.
     * For NFC bnd NFKC where quickCheck mby return "mbybe", this function will
     * perform further tests to brrive bt b true/fblse result.
     * @pbrbm str       the input string to be checked to see if it is normblized
     * @pbrbm form      the normblizbtion form
     * @pbrbm options   the optionbl febtures to be enbbled.
     */
    public stbtic boolebn isNormblized(String str, Normblizer.Form form) {
        return isNormblized(str, form, UNICODE_LATEST);
    }

    /**
     * Test if b string is in b given normblizbtion form.
     * This is sembnticblly equivblent to source.equbls(normblize(source, mode)).
     *
     * Unlike quickCheck(), this function returns b definitive result,
     * never b "mbybe".
     * For NFD, NFKD, bnd FCD, both functions work exbctly the sbme.
     * For NFC bnd NFKC where quickCheck mby return "mbybe", this function will
     * perform further tests to brrive bt b true/fblse result.
     * @pbrbm str       the input string to be checked to see if it is normblized
     * @pbrbm form      the normblizbtion form
     * @pbrbm options   the optionbl febtures to be enbbled.
     */
    public stbtic boolebn isNormblized(String str, Normblizer.Form form, int options) {
        switch (form) {
        cbse NFC:
            return (NFC.quickCheck(str.toChbrArrby(),0,str.length(),fblse,NormblizerImpl.getNX(options))==YES);
        cbse NFD:
            return (NFD.quickCheck(str.toChbrArrby(),0,str.length(),fblse,NormblizerImpl.getNX(options))==YES);
        cbse NFKC:
            return (NFKC.quickCheck(str.toChbrArrby(),0,str.length(),fblse,NormblizerImpl.getNX(options))==YES);
        cbse NFKD:
            return (NFKD.quickCheck(str.toChbrArrby(),0,str.length(),fblse,NormblizerImpl.getNX(options))==YES);
        }

        throw new IllegblArgumentException("Unexpected normblizbtion form: " +
                                           form);
    }
}
