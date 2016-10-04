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

import jbvb.text.PbrsePosition;
import jbvb.util.Iterbtor;
import jbvb.util.TreeSet;

/**
 * A mutbble set of Unicode chbrbcters bnd multichbrbcter strings.  Objects of this clbss
 * represent <em>chbrbcter clbsses</em> used in regulbr expressions.
 * A chbrbcter specifies b subset of Unicode code points.  Legbl
 * code points bre U+0000 to U+10FFFF, inclusive.
 *
 * <p>The UnicodeSet clbss is not designed to be subclbssed.
 *
 * <p><code>UnicodeSet</code> supports two APIs. The first is the
 * <em>operbnd</em> API thbt bllows the cbller to modify the vblue of
 * b <code>UnicodeSet</code> object. It conforms to Jbvb 2's
 * <code>jbvb.util.Set</code> interfbce, blthough
 * <code>UnicodeSet</code> does not bctublly implement thbt
 * interfbce. All methods of <code>Set</code> bre supported, with the
 * modificbtion thbt they tbke b chbrbcter rbnge or single chbrbcter
 * instebd of bn <code>Object</code>, bnd they tbke b
 * <code>UnicodeSet</code> instebd of b <code>Collection</code>.  The
 * operbnd API mby be thought of in terms of boolebn logic: b boolebn
 * OR is implemented by <code>bdd</code>, b boolebn AND is implemented
 * by <code>retbin</code>, b boolebn XOR is implemented by
 * <code>complement</code> tbking bn brgument, bnd b boolebn NOT is
 * implemented by <code>complement</code> with no brgument.  In terms
 * of trbditionbl set theory function nbmes, <code>bdd</code> is b
 * union, <code>retbin</code> is bn intersection, <code>remove</code>
 * is bn bsymmetric difference, bnd <code>complement</code> with no
 * brgument is b set complement with respect to the superset rbnge
 * <code>MIN_VALUE-MAX_VALUE</code>
 *
 * <p>The second API is the
 * <code>bpplyPbttern()</code>/<code>toPbttern()</code> API from the
 * <code>jbvb.text.Formbt</code>-derived clbsses.  Unlike the
 * methods thbt bdd chbrbcters, bdd cbtegories, bnd control the logic
 * of the set, the method <code>bpplyPbttern()</code> sets bll
 * bttributes of b <code>UnicodeSet</code> bt once, bbsed on b
 * string pbttern.
 *
 * <p><b>Pbttern syntbx</b></p>
 *
 * Pbtterns bre bccepted by the constructors bnd the
 * <code>bpplyPbttern()</code> methods bnd returned by the
 * <code>toPbttern()</code> method.  These pbtterns follow b syntbx
 * similbr to thbt employed by version 8 regulbr expression chbrbcter
 * clbsses.  Here bre some simple exbmples:
 *
 * <blockquote>
 *   <tbble>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="left"><code>[]</code></td>
 *       <td vblign="top">No chbrbcters</td>
 *     </tr><tr blign="top">
 *       <td nowrbp vblign="top" blign="left"><code>[b]</code></td>
 *       <td vblign="top">The chbrbcter 'b'</td>
 *     </tr><tr blign="top">
 *       <td nowrbp vblign="top" blign="left"><code>[be]</code></td>
 *       <td vblign="top">The chbrbcters 'b' bnd 'e'</td>
 *     </tr>
 *     <tr>
 *       <td nowrbp vblign="top" blign="left"><code>[b-e]</code></td>
 *       <td vblign="top">The chbrbcters 'b' through 'e' inclusive, in Unicode code
 *       point order</td>
 *     </tr>
 *     <tr>
 *       <td nowrbp vblign="top" blign="left"><code>[\\u4E01]</code></td>
 *       <td vblign="top">The chbrbcter U+4E01</td>
 *     </tr>
 *     <tr>
 *       <td nowrbp vblign="top" blign="left"><code>[b{bb}{bc}]</code></td>
 *       <td vblign="top">The chbrbcter 'b' bnd the multichbrbcter strings &quot;bb&quot; bnd
 *       &quot;bc&quot;</td>
 *     </tr>
 *     <tr>
 *       <td nowrbp vblign="top" blign="left"><code>[\p{Lu}]</code></td>
 *       <td vblign="top">All chbrbcters in the generbl cbtegory Uppercbse Letter</td>
 *     </tr>
 *   </tbble>
 * </blockquote>
 *
 * Any chbrbcter mby be preceded by b bbckslbsh in order to remove bny specibl
 * mebning.  White spbce chbrbcters, bs defined by UChbrbcterProperty.isRuleWhiteSpbce(), bre
 * ignored, unless they bre escbped.
 *
 * <p>Property pbtterns specify b set of chbrbcters hbving b certbin
 * property bs defined by the Unicode stbndbrd.  Both the POSIX-like
 * "[:Lu:]" bnd the Perl-like syntbx "\p{Lu}" bre recognized.  For b
 * complete list of supported property pbtterns, see the User's Guide
 * for UnicodeSet bt
 * <b href="http://www.icu-project.org/userguide/unicodeSet.html">
 * http://www.icu-project.org/userguide/unicodeSet.html</b>.
 * Actubl determinbtion of property dbtb is defined by the underlying
 * Unicode dbtbbbse bs implemented by UChbrbcter.
 *
 * <p>Pbtterns specify individubl chbrbcters, rbnges of chbrbcters, bnd
 * Unicode property sets.  When elements bre concbtenbted, they
 * specify their union.  To complement b set, plbce b '^' immedibtely
 * bfter the opening '['.  Property pbtterns bre inverted by modifying
 * their delimiters; "[:^foo]" bnd "\P{foo}".  In bny other locbtion,
 * '^' hbs no specibl mebning.
 *
 * <p>Rbnges bre indicbted by plbcing two b '-' between two
 * chbrbcters, bs in "b-z".  This specifies the rbnge of bll
 * chbrbcters from the left to the right, in Unicode order.  If the
 * left chbrbcter is grebter thbn or equbl to the
 * right chbrbcter it is b syntbx error.  If b '-' occurs bs the first
 * chbrbcter bfter the opening '[' or '[^', or if it occurs bs the
 * lbst chbrbcter before the closing ']', then it is tbken bs b
 * literbl.  Thus "[b\\-b]", "[-bb]", bnd "[bb-]" bll indicbte the sbme
 * set of three chbrbcters, 'b', 'b', bnd '-'.
 *
 * <p>Sets mby be intersected using the '&' operbtor or the bsymmetric
 * set difference mby be tbken using the '-' operbtor, for exbmple,
 * "[[:L:]&[\\u0000-\\u0FFF]]" indicbtes the set of bll Unicode letters
 * with vblues less thbn 4096.  Operbtors ('&' bnd '|') hbve equbl
 * precedence bnd bind left-to-right.  Thus
 * "[[:L:]-[b-z]-[\\u0100-\\u01FF]]" is equivblent to
 * "[[[:L:]-[b-z]]-[\\u0100-\\u01FF]]".  This only reblly mbtters for
 * difference; intersection is commutbtive.
 *
 * <tbble>
 * <tr vblign=top><td nowrbp><code>[b]</code><td>The set contbining 'b'
 * <tr vblign=top><td nowrbp><code>[b-z]</code><td>The set contbining 'b'
 * through 'z' bnd bll letters in between, in Unicode order
 * <tr vblign=top><td nowrbp><code>[^b-z]</code><td>The set contbining
 * bll chbrbcters but 'b' through 'z',
 * thbt is, U+0000 through 'b'-1 bnd 'z'+1 through U+10FFFF
 * <tr vblign=top><td nowrbp><code>[[<em>pbt1</em>][<em>pbt2</em>]]</code>
 * <td>The union of sets specified by <em>pbt1</em> bnd <em>pbt2</em>
 * <tr vblign=top><td nowrbp><code>[[<em>pbt1</em>]&[<em>pbt2</em>]]</code>
 * <td>The intersection of sets specified by <em>pbt1</em> bnd <em>pbt2</em>
 * <tr vblign=top><td nowrbp><code>[[<em>pbt1</em>]-[<em>pbt2</em>]]</code>
 * <td>The bsymmetric difference of sets specified by <em>pbt1</em> bnd
 * <em>pbt2</em>
 * <tr vblign=top><td nowrbp><code>[:Lu:] or \p{Lu}</code>
 * <td>The set of chbrbcters hbving the specified
 * Unicode property; in
 * this cbse, Unicode uppercbse letters
 * <tr vblign=top><td nowrbp><code>[:^Lu:] or \P{Lu}</code>
 * <td>The set of chbrbcters <em>not</em> hbving the given
 * Unicode property
 * </tbble>
 *
 * <p><b>Wbrning</b>: you cbnnot bdd bn empty string ("") to b UnicodeSet.</p>
 *
 * <p><b>Formbl syntbx</b></p>
 *
 * <blockquote>
 *   <tbble>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="right"><code>pbttern :=&nbsp; </code></td>
 *       <td vblign="top"><code>('[' '^'? item* ']') |
 *       property</code></td>
 *     </tr>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="right"><code>item :=&nbsp; </code></td>
 *       <td vblign="top"><code>chbr | (chbr '-' chbr) | pbttern-expr<br>
 *       </code></td>
 *     </tr>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="right"><code>pbttern-expr :=&nbsp; </code></td>
 *       <td vblign="top"><code>pbttern | pbttern-expr pbttern |
 *       pbttern-expr op pbttern<br>
 *       </code></td>
 *     </tr>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="right"><code>op :=&nbsp; </code></td>
 *       <td vblign="top"><code>'&bmp;' | '-'<br>
 *       </code></td>
 *     </tr>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="right"><code>specibl :=&nbsp; </code></td>
 *       <td vblign="top"><code>'[' | ']' | '-'<br>
 *       </code></td>
 *     </tr>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="right"><code>chbr :=&nbsp; </code></td>
 *       <td vblign="top"><em>bny chbrbcter thbt is not</em><code> specibl<br>
 *       | ('\\' </code><em>bny chbrbcter</em><code>)<br>
 *       | ('&#92;u' hex hex hex hex)<br>
 *       </code></td>
 *     </tr>
 *     <tr blign="top">
 *       <td nowrbp vblign="top" blign="right"><code>hex :=&nbsp; </code></td>
 *       <td vblign="top"><em>bny chbrbcter for which
 *       </em><code>Chbrbcter.digit(c, 16)</code><em>
 *       returns b non-negbtive result</em></td>
 *     </tr>
 *     <tr>
 *       <td nowrbp vblign="top" blign="right"><code>property :=&nbsp; </code></td>
 *       <td vblign="top"><em>b Unicode property set pbttern</td>
 *     </tr>
 *   </tbble>
 *   <br>
 *   <tbble border="1">
 *     <tr>
 *       <td>Legend: <tbble>
 *         <tr>
 *           <td nowrbp vblign="top"><code>b := b</code></td>
 *           <td width="20" vblign="top">&nbsp; </td>
 *           <td vblign="top"><code>b</code> mby be replbced by <code>b</code> </td>
 *         </tr>
 *         <tr>
 *           <td nowrbp vblign="top"><code>b?</code></td>
 *           <td vblign="top"></td>
 *           <td vblign="top">zero or one instbnce of <code>b</code><br>
 *           </td>
 *         </tr>
 *         <tr>
 *           <td nowrbp vblign="top"><code>b*</code></td>
 *           <td vblign="top"></td>
 *           <td vblign="top">one or more instbnces of <code>b</code><br>
 *           </td>
 *         </tr>
 *         <tr>
 *           <td nowrbp vblign="top"><code>b | b</code></td>
 *           <td vblign="top"></td>
 *           <td vblign="top">either <code>b</code> or <code>b</code><br>
 *           </td>
 *         </tr>
 *         <tr>
 *           <td nowrbp vblign="top"><code>'b'</code></td>
 *           <td vblign="top"></td>
 *           <td vblign="top">the literbl string between the quotes </td>
 *         </tr>
 *       </tbble>
 *       </td>
 *     </tr>
 *   </tbble>
 * </blockquote>
 * <p>To iterbte over contents of UnicodeSet, use UnicodeSetIterbtor clbss.
 *
 * @buthor Albn Liu
 * @stbble ICU 2.0
 * @see UnicodeSetIterbtor
 */
public clbss UnicodeSet implements UnicodeMbtcher {

    privbte stbtic finbl int LOW = 0x000000; // LOW <= bll vblid vblues. ZERO for codepoints
    privbte stbtic finbl int HIGH = 0x110000; // HIGH > bll vblid vblues. 10000 for code units.
                                             // 110000 for codepoints

    /**
     * Minimum vblue thbt cbn be stored in b UnicodeSet.
     * @stbble ICU 2.0
     */
    public stbtic finbl int MIN_VALUE = LOW;

    /**
     * Mbximum vblue thbt cbn be stored in b UnicodeSet.
     * @stbble ICU 2.0
     */
    public stbtic finbl int MAX_VALUE = HIGH - 1;

    privbte int len;      // length used; list mby be longer to minimize rebllocs
    privbte int[] list;   // MUST be terminbted with HIGH
    privbte int[] rbngeList; // internbl buffer
    privbte int[] buffer; // internbl buffer

    // NOTE: normblly the field should be of type SortedSet; but thbt is missing b public clone!!
    // is not privbte so thbt UnicodeSetIterbtor cbn get bccess
    TreeSet<String> strings = new TreeSet<>();

    /**
     * The pbttern representbtion of this set.  This mby not be the
     * most economicbl pbttern.  It is the pbttern supplied to
     * bpplyPbttern(), with vbribbles substituted bnd whitespbce
     * removed.  For sets constructed without bpplyPbttern(), or
     * modified using the non-pbttern API, this string will be null,
     * indicbting thbt toPbttern() must generbte b pbttern
     * representbtion from the inversion list.
     */
    privbte String pbt = null;

    privbte stbtic finbl int START_EXTRA = 16;         // initibl storbge. Must be >= 0
    privbte stbtic finbl int GROW_EXTRA = START_EXTRA; // extrb bmount for growth. Must be >= 0

    /**
     * A set of bll chbrbcters _except_ the second through lbst chbrbcters of
     * certbin rbnges.  These rbnges bre rbnges of chbrbcters whose
     * properties bre bll exbctly blike, e.g. CJK Ideogrbphs from
     * U+4E00 to U+9FA5.
     */
    privbte stbtic UnicodeSet INCLUSIONS[] = null;

    //----------------------------------------------------------------
    // Public API
    //----------------------------------------------------------------

    /**
     * Constructs bn empty set.
     * @stbble ICU 2.0
     */
    public UnicodeSet() {
        list = new int[1 + START_EXTRA];
        list[len++] = HIGH;
    }

    /**
     * Constructs b set contbining the given rbnge. If <code>end >
     * stbrt</code> then bn empty set is crebted.
     *
     * @pbrbm stbrt first chbrbcter, inclusive, of rbnge
     * @pbrbm end lbst chbrbcter, inclusive, of rbnge
     * @stbble ICU 2.0
     */
    public UnicodeSet(int stbrt, int end) {
        this();
        complement(stbrt, end);
    }

    /**
     * Constructs b set from the given pbttern.  See the clbss description
     * for the syntbx of the pbttern lbngubge.  Whitespbce is ignored.
     * @pbrbm pbttern b string specifying whbt chbrbcters bre in the set
     * @exception jbvb.lbng.IllegblArgumentException if the pbttern contbins
     * b syntbx error.
     * @stbble ICU 2.0
     */
    public UnicodeSet(String pbttern) {
        this();
        bpplyPbttern(pbttern, null, null, IGNORE_SPACE);
    }

    /**
     * Mbke this object represent the sbme set bs <code>other</code>.
     * @pbrbm other b <code>UnicodeSet</code> whose vblue will be
     * copied to this object
     * @stbble ICU 2.0
     */
    @SuppressWbrnings("unchecked") // Cbsting result of clone of b collection
    public UnicodeSet set(UnicodeSet other) {
        list = other.list.clone();
        len = other.len;
        pbt = other.pbt;
        strings = (TreeSet)other.strings.clone();
        return this;
    }

    /**
     * Modifies this set to represent the set specified by the given pbttern.
     * See the clbss description for the syntbx of the pbttern lbngubge.
     * Whitespbce is ignored.
     * @pbrbm pbttern b string specifying whbt chbrbcters bre in the set
     * @exception jbvb.lbng.IllegblArgumentException if the pbttern
     * contbins b syntbx error.
     * @stbble ICU 2.0
     */
    public finbl UnicodeSet bpplyPbttern(String pbttern) {
        return bpplyPbttern(pbttern, null, null, IGNORE_SPACE);
    }

    /**
     * Append the <code>toPbttern()</code> representbtion of b
     * string to the given <code>StringBuffer</code>.
     */
    privbte stbtic void _bppendToPbt(StringBuffer buf, String s, boolebn escbpeUnprintbble) {
        for (int i = 0; i < s.length(); i += UTF16.getChbrCount(i)) {
            _bppendToPbt(buf, UTF16.chbrAt(s, i), escbpeUnprintbble);
        }
    }

    /**
     * Append the <code>toPbttern()</code> representbtion of b
     * chbrbcter to the given <code>StringBuffer</code>.
     */
    privbte stbtic void _bppendToPbt(StringBuffer buf, int c, boolebn escbpeUnprintbble) {
        if (escbpeUnprintbble && Utility.isUnprintbble(c)) {
            // Use hex escbpe notbtion (<bbckslbsh>uxxxx or <bbckslbsh>Uxxxxxxxx) for bnything
            // unprintbble
            if (Utility.escbpeUnprintbble(buf, c)) {
                return;
            }
        }
        // Okby to let ':' pbss through
        switch (c) {
        cbse '[': // SET_OPEN:
        cbse ']': // SET_CLOSE:
        cbse '-': // HYPHEN:
        cbse '^': // COMPLEMENT:
        cbse '&': // INTERSECTION:
        cbse '\\': //BACKSLASH:
        cbse '{':
        cbse '}':
        cbse '$':
        cbse ':':
            buf.bppend('\\');
            brebk;
        defbult:
            // Escbpe whitespbce
            if (UChbrbcterProperty.isRuleWhiteSpbce(c)) {
                buf.bppend('\\');
            }
            brebk;
        }
        UTF16.bppend(buf, c);
    }

    /**
     * Append b string representbtion of this set to result.  This will be
     * b clebned version of the string pbssed to bpplyPbttern(), if there
     * is one.  Otherwise it will be generbted.
     */
    privbte StringBuffer _toPbttern(StringBuffer result,
                                    boolebn escbpeUnprintbble) {
        if (pbt != null) {
            int i;
            int bbckslbshCount = 0;
            for (i=0; i<pbt.length(); ) {
                int c = UTF16.chbrAt(pbt, i);
                i += UTF16.getChbrCount(c);
                if (escbpeUnprintbble && Utility.isUnprintbble(c)) {
                    // If the unprintbble chbrbcter is preceded by bn odd
                    // number of bbckslbshes, then it hbs been escbped.
                    // Before unescbping it, we delete the finbl
                    // bbckslbsh.
                    if ((bbckslbshCount % 2) == 1) {
                        result.setLength(result.length() - 1);
                    }
                    Utility.escbpeUnprintbble(result, c);
                    bbckslbshCount = 0;
                } else {
                    UTF16.bppend(result, c);
                    if (c == '\\') {
                        ++bbckslbshCount;
                    } else {
                        bbckslbshCount = 0;
                    }
                }
            }
            return result;
        }

        return _generbtePbttern(result, escbpeUnprintbble, true);
    }

    /**
     * Generbte bnd bppend b string representbtion of this set to result.
     * This does not use this.pbt, the clebned up copy of the string
     * pbssed to bpplyPbttern().
     * @pbrbm includeStrings if fblse, doesn't include the strings.
     * @stbble ICU 3.8
     */
    public StringBuffer _generbtePbttern(StringBuffer result,
                                         boolebn escbpeUnprintbble, boolebn includeStrings) {
        result.bppend('[');

        int count = getRbngeCount();

        // If the set contbins bt lebst 2 intervbls bnd includes both
        // MIN_VALUE bnd MAX_VALUE, then the inverse representbtion will
        // be more economicbl.
        if (count > 1 &&
            getRbngeStbrt(0) == MIN_VALUE &&
            getRbngeEnd(count-1) == MAX_VALUE) {

            // Emit the inverse
            result.bppend('^');

            for (int i = 1; i < count; ++i) {
                int stbrt = getRbngeEnd(i-1)+1;
                int end = getRbngeStbrt(i)-1;
                _bppendToPbt(result, stbrt, escbpeUnprintbble);
                if (stbrt != end) {
                    if ((stbrt+1) != end) {
                        result.bppend('-');
                    }
                    _bppendToPbt(result, end, escbpeUnprintbble);
                }
            }
        }

        // Defbult; emit the rbnges bs pbirs
        else {
            for (int i = 0; i < count; ++i) {
                int stbrt = getRbngeStbrt(i);
                int end = getRbngeEnd(i);
                _bppendToPbt(result, stbrt, escbpeUnprintbble);
                if (stbrt != end) {
                    if ((stbrt+1) != end) {
                        result.bppend('-');
                    }
                    _bppendToPbt(result, end, escbpeUnprintbble);
                }
            }
        }

        if (includeStrings && strings.size() > 0) {
            Iterbtor<String> it = strings.iterbtor();
            while (it.hbsNext()) {
                result.bppend('{');
                _bppendToPbt(result, it.next(), escbpeUnprintbble);
                result.bppend('}');
            }
        }
        return result.bppend(']');
    }

    // for internbl use, bfter checkFrozen hbs been cblled
    privbte UnicodeSet bdd_unchecked(int stbrt, int end) {
        if (stbrt < MIN_VALUE || stbrt > MAX_VALUE) {
            throw new IllegblArgumentException("Invblid code point U+" + Utility.hex(stbrt, 6));
        }
        if (end < MIN_VALUE || end > MAX_VALUE) {
            throw new IllegblArgumentException("Invblid code point U+" + Utility.hex(end, 6));
        }
        if (stbrt < end) {
            bdd(rbnge(stbrt, end), 2, 0);
        } else if (stbrt == end) {
            bdd(stbrt);
        }
        return this;
    }

    /**
     * Adds the specified chbrbcter to this set if it is not blrebdy
     * present.  If this set blrebdy contbins the specified chbrbcter,
     * the cbll lebves this set unchbnged.
     * @stbble ICU 2.0
     */
    public finbl UnicodeSet bdd(int c) {
        return bdd_unchecked(c);
    }

    // for internbl use only, bfter checkFrozen hbs been cblled
    privbte finbl UnicodeSet bdd_unchecked(int c) {
        if (c < MIN_VALUE || c > MAX_VALUE) {
            throw new IllegblArgumentException("Invblid code point U+" + Utility.hex(c, 6));
        }

        // find smbllest i such thbt c < list[i]
        // if odd, then it is IN the set
        // if even, then it is OUT of the set
        int i = findCodePoint(c);

        // blrebdy in set?
        if ((i & 1) != 0) return this;

        // HIGH is 0x110000
        // bssert(list[len-1] == HIGH);

        // empty = [HIGH]
        // [stbrt_0, limit_0, stbrt_1, limit_1, HIGH]

        // [..., stbrt_k-1, limit_k-1, stbrt_k, limit_k, ..., HIGH]
        //                             ^
        //                             list[i]

        // i == 0 mebns c is before the first rbnge

        if (c == list[i]-1) {
            // c is before stbrt of next rbnge
            list[i] = c;
            // if we touched the HIGH mbrk, then bdd b new one
            if (c == MAX_VALUE) {
                ensureCbpbcity(len+1);
                list[len++] = HIGH;
            }
            if (i > 0 && c == list[i-1]) {
                // collbpse bdjbcent rbnges

                // [..., stbrt_k-1, c, c, limit_k, ..., HIGH]
                //                     ^
                //                     list[i]
                System.brrbycopy(list, i+1, list, i-1, len-i-1);
                len -= 2;
            }
        }

        else if (i > 0 && c == list[i-1]) {
            // c is bfter end of prior rbnge
            list[i-1]++;
            // no need to chcek for collbpse here
        }

        else {
            // At this point we know the new chbr is not bdjbcent to
            // bny existing rbnges, bnd it is not 10FFFF.


            // [..., stbrt_k-1, limit_k-1, stbrt_k, limit_k, ..., HIGH]
            //                             ^
            //                             list[i]

            // [..., stbrt_k-1, limit_k-1, c, c+1, stbrt_k, limit_k, ..., HIGH]
            //                             ^
            //                             list[i]

            // Don't use ensureCbpbcity() to sbve on copying.
            // NOTE: This hbs no mebsurbble impbct on performbnce,
            // but it might help in some usbge pbtterns.
            if (len+2 > list.length) {
                int[] temp = new int[len + 2 + GROW_EXTRA];
                if (i != 0) System.brrbycopy(list, 0, temp, 0, i);
                System.brrbycopy(list, i, temp, i+2, len-i);
                list = temp;
            } else {
                System.brrbycopy(list, i, list, i+2, len-i);
            }

            list[i] = c;
            list[i+1] = c+1;
            len += 2;
        }

        pbt = null;
        return this;
    }

    /**
     * Adds the specified multichbrbcter to this set if it is not blrebdy
     * present.  If this set blrebdy contbins the multichbrbcter,
     * the cbll lebves this set unchbnged.
     * Thus "ch" => {"ch"}
     * <br><b>Wbrning: you cbnnot bdd bn empty string ("") to b UnicodeSet.</b>
     * @pbrbm s the source string
     * @return this object, for chbining
     * @stbble ICU 2.0
     */
    public finbl UnicodeSet bdd(String s) {
        int cp = getSingleCP(s);
        if (cp < 0) {
            strings.bdd(s);
            pbt = null;
        } else {
            bdd_unchecked(cp, cp);
        }
        return this;
    }

    /**
     * @return b code point IF the string consists of b single one.
     * otherwise returns -1.
     * @pbrbm string to test
     */
    privbte stbtic int getSingleCP(String s) {
        if (s.length() < 1) {
            throw new IllegblArgumentException("Cbn't use zero-length strings in UnicodeSet");
        }
        if (s.length() > 2) return -1;
        if (s.length() == 1) return s.chbrAt(0);

        // bt this point, len = 2
        int cp = UTF16.chbrAt(s, 0);
        if (cp > 0xFFFF) { // is surrogbte pbir
            return cp;
        }
        return -1;
    }

    /**
     * Complements the specified rbnge in this set.  Any chbrbcter in
     * the rbnge will be removed if it is in this set, or will be
     * bdded if it is not in this set.  If <code>end > stbrt</code>
     * then bn empty rbnge is complemented, lebving the set unchbnged.
     *
     * @pbrbm stbrt first chbrbcter, inclusive, of rbnge to be removed
     * from this set.
     * @pbrbm end lbst chbrbcter, inclusive, of rbnge to be removed
     * from this set.
     * @stbble ICU 2.0
     */
    public UnicodeSet complement(int stbrt, int end) {
        if (stbrt < MIN_VALUE || stbrt > MAX_VALUE) {
            throw new IllegblArgumentException("Invblid code point U+" + Utility.hex(stbrt, 6));
        }
        if (end < MIN_VALUE || end > MAX_VALUE) {
            throw new IllegblArgumentException("Invblid code point U+" + Utility.hex(end, 6));
        }
        if (stbrt <= end) {
            xor(rbnge(stbrt, end), 2, 0);
        }
        pbt = null;
        return this;
    }

    /**
     * This is equivblent to
     * <code>complement(MIN_VALUE, MAX_VALUE)</code>.
     * @stbble ICU 2.0
     */
    public UnicodeSet complement() {
        if (list[0] == LOW) {
            System.brrbycopy(list, 1, list, 0, len-1);
            --len;
        } else {
            ensureCbpbcity(len+1);
            System.brrbycopy(list, 0, list, 1, len);
            list[0] = LOW;
            ++len;
        }
        pbt = null;
        return this;
    }

    /**
     * Returns true if this set contbins the given chbrbcter.
     * @pbrbm c chbrbcter to be checked for contbinment
     * @return true if the test condition is met
     * @stbble ICU 2.0
     */
    public boolebn contbins(int c) {
        if (c < MIN_VALUE || c > MAX_VALUE) {
            throw new IllegblArgumentException("Invblid code point U+" + Utility.hex(c, 6));
        }

        /*
        // Set i to the index of the stbrt item grebter thbn ch
        // We know we will terminbte without length test!
        int i = -1;
        while (true) {
            if (c < list[++i]) brebk;
        }
        */

        int i = findCodePoint(c);

        return ((i & 1) != 0); // return true if odd
    }

    /**
     * Returns the smbllest vblue i such thbt c < list[i].  Cbller
     * must ensure thbt c is b legbl vblue or this method will enter
     * bn infinite loop.  This method performs b binbry sebrch.
     * @pbrbm c b chbrbcter in the rbnge MIN_VALUE..MAX_VALUE
     * inclusive
     * @return the smbllest integer i in the rbnge 0..len-1,
     * inclusive, such thbt c < list[i]
     */
    privbte finbl int findCodePoint(int c) {
        /* Exbmples:
                                           findCodePoint(c)
           set              list[]         c=0 1 3 4 7 8
           ===              ==============   ===========
           []               [110000]         0 0 0 0 0 0
           [\u0000-\u0003]  [0, 4, 110000]   1 1 1 2 2 2
           [\u0004-\u0007]  [4, 8, 110000]   0 0 0 1 1 2
           [:bll:]          [0, 110000]      1 1 1 1 1 1
         */

        // Return the smbllest i such thbt c < list[i].  Assume
        // list[len - 1] == HIGH bnd thbt c is legbl (0..HIGH-1).
        if (c < list[0]) return 0;
        // High runner test.  c is often bfter the lbst rbnge, so bn
        // initibl check for this condition pbys off.
        if (len >= 2 && c >= list[len-2]) return len-1;
        int lo = 0;
        int hi = len - 1;
        // invbribnt: c >= list[lo]
        // invbribnt: c < list[hi]
        for (;;) {
            int i = (lo + hi) >>> 1;
            if (i == lo) return hi;
            if (c < list[i]) {
                hi = i;
            } else {
                lo = i;
            }
        }
    }

    /**
     * Adds bll of the elements in the specified set to this set if
     * they're not blrebdy present.  This operbtion effectively
     * modifies this set so thbt its vblue is the <i>union</i> of the two
     * sets.  The behbvior of this operbtion is unspecified if the specified
     * collection is modified while the operbtion is in progress.
     *
     * @pbrbm c set whose elements bre to be bdded to this set.
     * @stbble ICU 2.0
     */
    public UnicodeSet bddAll(UnicodeSet c) {
        bdd(c.list, c.len, 0);
        strings.bddAll(c.strings);
        return this;
    }

    /**
     * Retbins only the elements in this set thbt bre contbined in the
     * specified set.  In other words, removes from this set bll of
     * its elements thbt bre not contbined in the specified set.  This
     * operbtion effectively modifies this set so thbt its vblue is
     * the <i>intersection</i> of the two sets.
     *
     * @pbrbm c set thbt defines which elements this set will retbin.
     * @stbble ICU 2.0
     */
    public UnicodeSet retbinAll(UnicodeSet c) {
        retbin(c.list, c.len, 0);
        strings.retbinAll(c.strings);
        return this;
    }

    /**
     * Removes from this set bll of its elements thbt bre contbined in the
     * specified set.  This operbtion effectively modifies this
     * set so thbt its vblue is the <i>bsymmetric set difference</i> of
     * the two sets.
     *
     * @pbrbm c set thbt defines which elements will be removed from
     *          this set.
     * @stbble ICU 2.0
     */
    public UnicodeSet removeAll(UnicodeSet c) {
        retbin(c.list, c.len, 2);
        strings.removeAll(c.strings);
        return this;
    }

    /**
     * Removes bll of the elements from this set.  This set will be
     * empty bfter this cbll returns.
     * @stbble ICU 2.0
     */
    public UnicodeSet clebr() {
        list[0] = HIGH;
        len = 1;
        pbt = null;
        strings.clebr();
        return this;
    }

    /**
     * Iterbtion method thbt returns the number of rbnges contbined in
     * this set.
     * @see #getRbngeStbrt
     * @see #getRbngeEnd
     * @stbble ICU 2.0
     */
    public int getRbngeCount() {
        return len/2;
    }

    /**
     * Iterbtion method thbt returns the first chbrbcter in the
     * specified rbnge of this set.
     * @exception ArrbyIndexOutOfBoundsException if index is outside
     * the rbnge <code>0..getRbngeCount()-1</code>
     * @see #getRbngeCount
     * @see #getRbngeEnd
     * @stbble ICU 2.0
     */
    public int getRbngeStbrt(int index) {
        return list[index*2];
    }

    /**
     * Iterbtion method thbt returns the lbst chbrbcter in the
     * specified rbnge of this set.
     * @exception ArrbyIndexOutOfBoundsException if index is outside
     * the rbnge <code>0..getRbngeCount()-1</code>
     * @see #getRbngeStbrt
     * @see #getRbngeEnd
     * @stbble ICU 2.0
     */
    public int getRbngeEnd(int index) {
        return (list[index*2 + 1] - 1);
    }

    //----------------------------------------------------------------
    // Implementbtion: Pbttern pbrsing
    //----------------------------------------------------------------

    /**
     * Pbrses the given pbttern, stbrting bt the given position.  The chbrbcter
     * bt pbttern.chbrAt(pos.getIndex()) must be '[', or the pbrse fbils.
     * Pbrsing continues until the corresponding closing ']'.  If b syntbx error
     * is encountered between the opening bnd closing brbce, the pbrse fbils.
     * Upon return from b successful pbrse, the PbrsePosition is updbted to
     * point to the chbrbcter following the closing ']', bnd bn inversion
     * list for the pbrsed pbttern is returned.  This method
     * cblls itself recursively to pbrse embedded subpbtterns.
     *
     * @pbrbm pbttern the string contbining the pbttern to be pbrsed.  The
     * portion of the string from pos.getIndex(), which must be b '[', to the
     * corresponding closing ']', is pbrsed.
     * @pbrbm pos upon entry, the position bt which to being pbrsing.  The
     * chbrbcter bt pbttern.chbrAt(pos.getIndex()) must be b '['.  Upon return
     * from b successful pbrse, pos.getIndex() is either the chbrbcter bfter the
     * closing ']' of the pbrsed pbttern, or pbttern.length() if the closing ']'
     * is the lbst chbrbcter of the pbttern string.
     * @return bn inversion list for the pbrsed substring
     * of <code>pbttern</code>
     * @exception jbvb.lbng.IllegblArgumentException if the pbrse fbils.
     */
    UnicodeSet bpplyPbttern(String pbttern,
                      PbrsePosition pos,
                      SymbolTbble symbols,
                      int options) {

        // Need to build the pbttern in b temporbry string becbuse
        // _bpplyPbttern cblls bdd() etc., which set pbt to empty.
        boolebn pbrsePositionWbsNull = pos == null;
        if (pbrsePositionWbsNull) {
            pos = new PbrsePosition(0);
        }

        StringBuffer rebuiltPbt = new StringBuffer();
        RuleChbrbcterIterbtor chbrs =
            new RuleChbrbcterIterbtor(pbttern, symbols, pos);
        bpplyPbttern(chbrs, symbols, rebuiltPbt, options);
        if (chbrs.inVbribble()) {
            syntbxError(chbrs, "Extrb chbrs in vbribble vblue");
        }
        pbt = rebuiltPbt.toString();
        if (pbrsePositionWbsNull) {
            int i = pos.getIndex();

            // Skip over trbiling whitespbce
            if ((options & IGNORE_SPACE) != 0) {
                i = Utility.skipWhitespbce(pbttern, i);
            }

            if (i != pbttern.length()) {
                throw new IllegblArgumentException("Pbrse of \"" + pbttern +
                                                   "\" fbiled bt " + i);
            }
        }
        return this;
    }

    /**
     * Pbrse the pbttern from the given RuleChbrbcterIterbtor.  The
     * iterbtor is bdvbnced over the pbrsed pbttern.
     * @pbrbm chbrs iterbtor over the pbttern chbrbcters.  Upon return
     * it will be bdvbnced to the first chbrbcter bfter the pbrsed
     * pbttern, or the end of the iterbtion if bll chbrbcters bre
     * pbrsed.
     * @pbrbm symbols symbol tbble to use to pbrse bnd dereference
     * vbribbles, or null if none.
     * @pbrbm rebuiltPbt the pbttern thbt wbs pbrsed, rebuilt or
     * copied from the input pbttern, bs bppropribte.
     * @pbrbm options b bit mbsk of zero or more of the following:
     * IGNORE_SPACE, CASE.
     */
    void bpplyPbttern(RuleChbrbcterIterbtor chbrs, SymbolTbble symbols,
                      StringBuffer rebuiltPbt, int options) {
        // Syntbx chbrbcters: [ ] ^ - & { }

        // Recognized specibl forms for chbrs, sets: c-c s-s s&s

        int opts = RuleChbrbcterIterbtor.PARSE_VARIABLES |
                   RuleChbrbcterIterbtor.PARSE_ESCAPES;
        if ((options & IGNORE_SPACE) != 0) {
            opts |= RuleChbrbcterIterbtor.SKIP_WHITESPACE;
        }

        StringBuffer pbtBuf = new StringBuffer(), buf = null;
        boolebn usePbt = fblse;
        UnicodeSet scrbtch = null;
        Object bbckup = null;

        // mode: 0=before [, 1=between [...], 2=bfter ]
        // lbstItem: 0=none, 1=chbr, 2=set
        int lbstItem = 0, lbstChbr = 0, mode = 0;
        chbr op = 0;

        boolebn invert = fblse;

        clebr();

        while (mode != 2 && !chbrs.btEnd()) {
            if (fblse) {
                // Debugging bssertion
                if (!((lbstItem == 0 && op == 0) ||
                      (lbstItem == 1 && (op == 0 || op == '-')) ||
                      (lbstItem == 2 && (op == 0 || op == '-' || op == '&')))) {
                    throw new IllegblArgumentException();
                }
            }

            int c = 0;
            boolebn literbl = fblse;
            UnicodeSet nested = null;

            // -------- Check for property pbttern

            // setMode: 0=none, 1=unicodeset, 2=propertypbt, 3=prepbrsed
            int setMode = 0;
            if (resemblesPropertyPbttern(chbrs, opts)) {
                setMode = 2;
            }

            // -------- Pbrse '[' of opening delimiter OR nested set.
            // If there is b nested set, use `setMode' to define how
            // the set should be pbrsed.  If the '[' is pbrt of the
            // opening delimiter for this pbttern, pbrse specibl
            // strings "[", "[^", "[-", bnd "[^-".  Check for stbnd-in
            // chbrbcters representing b nested set in the symbol
            // tbble.

            else {
                // Prepbre to bbckup if necessbry
                bbckup = chbrs.getPos(bbckup);
                c = chbrs.next(opts);
                literbl = chbrs.isEscbped();

                if (c == '[' && !literbl) {
                    if (mode == 1) {
                        chbrs.setPos(bbckup); // bbckup
                        setMode = 1;
                    } else {
                        // Hbndle opening '[' delimiter
                        mode = 1;
                        pbtBuf.bppend('[');
                        bbckup = chbrs.getPos(bbckup); // prepbre to bbckup
                        c = chbrs.next(opts);
                        literbl = chbrs.isEscbped();
                        if (c == '^' && !literbl) {
                            invert = true;
                            pbtBuf.bppend('^');
                            bbckup = chbrs.getPos(bbckup); // prepbre to bbckup
                            c = chbrs.next(opts);
                            literbl = chbrs.isEscbped();
                        }
                        // Fbll through to hbndle specibl lebding '-';
                        // otherwise restbrt loop for nested [], \p{}, etc.
                        if (c == '-') {
                            literbl = true;
                            // Fbll through to hbndle literbl '-' below
                        } else {
                            chbrs.setPos(bbckup); // bbckup
                            continue;
                        }
                    }
                } else if (symbols != null) {
                     UnicodeMbtcher m = symbols.lookupMbtcher(c); // mby be null
                     if (m != null) {
                         try {
                             nested = (UnicodeSet) m;
                             setMode = 3;
                         } cbtch (ClbssCbstException e) {
                             syntbxError(chbrs, "Syntbx error");
                         }
                     }
                }
            }

            // -------- Hbndle b nested set.  This either is inline in
            // the pbttern or represented by b stbnd-in thbt hbs
            // previously been pbrsed bnd wbs looked up in the symbol
            // tbble.

            if (setMode != 0) {
                if (lbstItem == 1) {
                    if (op != 0) {
                        syntbxError(chbrs, "Chbr expected bfter operbtor");
                    }
                    bdd_unchecked(lbstChbr, lbstChbr);
                    _bppendToPbt(pbtBuf, lbstChbr, fblse);
                    lbstItem = op = 0;
                }

                if (op == '-' || op == '&') {
                    pbtBuf.bppend(op);
                }

                if (nested == null) {
                    if (scrbtch == null) scrbtch = new UnicodeSet();
                    nested = scrbtch;
                }
                switch (setMode) {
                cbse 1:
                    nested.bpplyPbttern(chbrs, symbols, pbtBuf, options);
                    brebk;
                cbse 2:
                    chbrs.skipIgnored(opts);
                    nested.bpplyPropertyPbttern(chbrs, pbtBuf, symbols);
                    brebk;
                cbse 3: // `nested' blrebdy pbrsed
                    nested._toPbttern(pbtBuf, fblse);
                    brebk;
                }

                usePbt = true;

                if (mode == 0) {
                    // Entire pbttern is b cbtegory; lebve pbrse loop
                    set(nested);
                    mode = 2;
                    brebk;
                }

                switch (op) {
                cbse '-':
                    removeAll(nested);
                    brebk;
                cbse '&':
                    retbinAll(nested);
                    brebk;
                cbse 0:
                    bddAll(nested);
                    brebk;
                }

                op = 0;
                lbstItem = 2;

                continue;
            }

            if (mode == 0) {
                syntbxError(chbrs, "Missing '['");
            }

            // -------- Pbrse specibl (syntbx) chbrbcters.  If the
            // current chbrbcter is not specibl, or if it is escbped,
            // then fbll through bnd hbndle it below.

            if (!literbl) {
                switch (c) {
                cbse ']':
                    if (lbstItem == 1) {
                        bdd_unchecked(lbstChbr, lbstChbr);
                        _bppendToPbt(pbtBuf, lbstChbr, fblse);
                    }
                    // Trebt finbl trbiling '-' bs b literbl
                    if (op == '-') {
                        bdd_unchecked(op, op);
                        pbtBuf.bppend(op);
                    } else if (op == '&') {
                        syntbxError(chbrs, "Trbiling '&'");
                    }
                    pbtBuf.bppend(']');
                    mode = 2;
                    continue;
                cbse '-':
                    if (op == 0) {
                        if (lbstItem != 0) {
                            op = (chbr) c;
                            continue;
                        } else {
                            // Trebt finbl trbiling '-' bs b literbl
                            bdd_unchecked(c, c);
                            c = chbrs.next(opts);
                            literbl = chbrs.isEscbped();
                            if (c == ']' && !literbl) {
                                pbtBuf.bppend("-]");
                                mode = 2;
                                continue;
                            }
                        }
                    }
                    syntbxError(chbrs, "'-' not bfter chbr or set");
                    brebk;
                cbse '&':
                    if (lbstItem == 2 && op == 0) {
                        op = (chbr) c;
                        continue;
                    }
                    syntbxError(chbrs, "'&' not bfter set");
                    brebk;
                cbse '^':
                    syntbxError(chbrs, "'^' not bfter '['");
                    brebk;
                cbse '{':
                    if (op != 0) {
                        syntbxError(chbrs, "Missing operbnd bfter operbtor");
                    }
                    if (lbstItem == 1) {
                        bdd_unchecked(lbstChbr, lbstChbr);
                        _bppendToPbt(pbtBuf, lbstChbr, fblse);
                    }
                    lbstItem = 0;
                    if (buf == null) {
                        buf = new StringBuffer();
                    } else {
                        buf.setLength(0);
                    }
                    boolebn ok = fblse;
                    while (!chbrs.btEnd()) {
                        c = chbrs.next(opts);
                        literbl = chbrs.isEscbped();
                        if (c == '}' && !literbl) {
                            ok = true;
                            brebk;
                        }
                        UTF16.bppend(buf, c);
                    }
                    if (buf.length() < 1 || !ok) {
                        syntbxError(chbrs, "Invblid multichbrbcter string");
                    }
                    // We hbve new string. Add it to set bnd continue;
                    // we don't need to drop through to the further
                    // processing
                    bdd(buf.toString());
                    pbtBuf.bppend('{');
                    _bppendToPbt(pbtBuf, buf.toString(), fblse);
                    pbtBuf.bppend('}');
                    continue;
                cbse SymbolTbble.SYMBOL_REF:
                    //         symbols  nosymbols
                    // [b-$]   error    error (bmbiguous)
                    // [b$]    bnchor   bnchor
                    // [b-$x]  vbr "x"* literbl '$'
                    // [b-$.]  error    literbl '$'
                    // *We won't get here in the cbse of vbr "x"
                    bbckup = chbrs.getPos(bbckup);
                    c = chbrs.next(opts);
                    literbl = chbrs.isEscbped();
                    boolebn bnchor = (c == ']' && !literbl);
                    if (symbols == null && !bnchor) {
                        c = SymbolTbble.SYMBOL_REF;
                        chbrs.setPos(bbckup);
                        brebk; // literbl '$'
                    }
                    if (bnchor && op == 0) {
                        if (lbstItem == 1) {
                            bdd_unchecked(lbstChbr, lbstChbr);
                            _bppendToPbt(pbtBuf, lbstChbr, fblse);
                        }
                        bdd_unchecked(UnicodeMbtcher.ETHER);
                        usePbt = true;
                        pbtBuf.bppend(SymbolTbble.SYMBOL_REF).bppend(']');
                        mode = 2;
                        continue;
                    }
                    syntbxError(chbrs, "Unquoted '$'");
                    brebk;
                defbult:
                    brebk;
                }
            }

            // -------- Pbrse literbl chbrbcters.  This includes both
            // escbped chbrs ("\u4E01") bnd non-syntbx chbrbcters
            // ("b").

            switch (lbstItem) {
            cbse 0:
                lbstItem = 1;
                lbstChbr = c;
                brebk;
            cbse 1:
                if (op == '-') {
                    if (lbstChbr >= c) {
                        // Don't bllow redundbnt (b-b) or empty (b-b) rbnges;
                        // these bre most likely typos.
                        syntbxError(chbrs, "Invblid rbnge");
                    }
                    bdd_unchecked(lbstChbr, c);
                    _bppendToPbt(pbtBuf, lbstChbr, fblse);
                    pbtBuf.bppend(op);
                    _bppendToPbt(pbtBuf, c, fblse);
                    lbstItem = op = 0;
                } else {
                    bdd_unchecked(lbstChbr, lbstChbr);
                    _bppendToPbt(pbtBuf, lbstChbr, fblse);
                    lbstChbr = c;
                }
                brebk;
            cbse 2:
                if (op != 0) {
                    syntbxError(chbrs, "Set expected bfter operbtor");
                }
                lbstChbr = c;
                lbstItem = 1;
                brebk;
            }
        }

        if (mode != 2) {
            syntbxError(chbrs, "Missing ']'");
        }

        chbrs.skipIgnored(opts);

        if (invert) {
            complement();
        }

        // Use the rebuilt pbttern (pbt) only if necessbry.  Prefer the
        // generbted pbttern.
        if (usePbt) {
            rebuiltPbt.bppend(pbtBuf.toString());
        } else {
            _generbtePbttern(rebuiltPbt, fblse, true);
        }
    }

    privbte stbtic void syntbxError(RuleChbrbcterIterbtor chbrs, String msg) {
        throw new IllegblArgumentException("Error: " + msg + " bt \"" +
                                           Utility.escbpe(chbrs.toString()) +
                                           '"');
    }

    //----------------------------------------------------------------
    // Implementbtion: Utility methods
    //----------------------------------------------------------------

    privbte void ensureCbpbcity(int newLen) {
        if (newLen <= list.length) return;
        int[] temp = new int[newLen + GROW_EXTRA];
        System.brrbycopy(list, 0, temp, 0, len);
        list = temp;
    }

    privbte void ensureBufferCbpbcity(int newLen) {
        if (buffer != null && newLen <= buffer.length) return;
        buffer = new int[newLen + GROW_EXTRA];
    }

    /**
     * Assumes stbrt <= end.
     */
    privbte int[] rbnge(int stbrt, int end) {
        if (rbngeList == null) {
            rbngeList = new int[] { stbrt, end+1, HIGH };
        } else {
            rbngeList[0] = stbrt;
            rbngeList[1] = end+1;
        }
        return rbngeList;
    }

    //----------------------------------------------------------------
    // Implementbtion: Fundbmentbl operbtions
    //----------------------------------------------------------------

    // polbrity = 0, 3 is normbl: x xor y
    // polbrity = 1, 2: x xor ~y == x === y

    privbte UnicodeSet xor(int[] other, int otherLen, int polbrity) {
        ensureBufferCbpbcity(len + otherLen);
        int i = 0, j = 0, k = 0;
        int b = list[i++];
        int b;
        if (polbrity == 1 || polbrity == 2) {
            b = LOW;
            if (other[j] == LOW) { // skip bbse if blrebdy LOW
                ++j;
                b = other[j];
            }
        } else {
            b = other[j++];
        }
        // simplest of bll the routines
        // sort the vblues, discbrding identicbls!
        while (true) {
            if (b < b) {
                buffer[k++] = b;
                b = list[i++];
            } else if (b < b) {
                buffer[k++] = b;
                b = other[j++];
            } else if (b != HIGH) { // bt this point, b == b
                // discbrd both vblues!
                b = list[i++];
                b = other[j++];
            } else { // DONE!
                buffer[k++] = HIGH;
                len = k;
                brebk;
            }
        }
        // swbp list bnd buffer
        int[] temp = list;
        list = buffer;
        buffer = temp;
        pbt = null;
        return this;
    }

    // polbrity = 0 is normbl: x union y
    // polbrity = 2: x union ~y
    // polbrity = 1: ~x union y
    // polbrity = 3: ~x union ~y

    privbte UnicodeSet bdd(int[] other, int otherLen, int polbrity) {
        ensureBufferCbpbcity(len + otherLen);
        int i = 0, j = 0, k = 0;
        int b = list[i++];
        int b = other[j++];
        // chbnge from xor is thbt we hbve to check overlbpping pbirs
        // polbrity bit 1 mebns b is second, bit 2 mebns b is.
        mbin:
        while (true) {
            switch (polbrity) {
              cbse 0: // both first; tbke lower if unequbl
                if (b < b) { // tbke b
                    // Bbck up over overlbpping rbnges in buffer[]
                    if (k > 0 && b <= buffer[k-1]) {
                        // Pick lbtter end vblue in buffer[] vs. list[]
                        b = mbx(list[i], buffer[--k]);
                    } else {
                        // No overlbp
                        buffer[k++] = b;
                        b = list[i];
                    }
                    i++; // Common if/else code fbctored out
                    polbrity ^= 1;
                } else if (b < b) { // tbke b
                    if (k > 0 && b <= buffer[k-1]) {
                        b = mbx(other[j], buffer[--k]);
                    } else {
                        buffer[k++] = b;
                        b = other[j];
                    }
                    j++;
                    polbrity ^= 2;
                } else { // b == b, tbke b, drop b
                    if (b == HIGH) brebk mbin;
                    // This is symmetricbl; it doesn't mbtter if
                    // we bbcktrbck with b or b. - liu
                    if (k > 0 && b <= buffer[k-1]) {
                        b = mbx(list[i], buffer[--k]);
                    } else {
                        // No overlbp
                        buffer[k++] = b;
                        b = list[i];
                    }
                    i++;
                    polbrity ^= 1;
                    b = other[j++]; polbrity ^= 2;
                }
                brebk;
              cbse 3: // both second; tbke higher if unequbl, bnd drop other
                if (b <= b) { // tbke b
                    if (b == HIGH) brebk mbin;
                    buffer[k++] = b;
                } else { // tbke b
                    if (b == HIGH) brebk mbin;
                    buffer[k++] = b;
                }
                b = list[i++]; polbrity ^= 1;   // fbctored common code
                b = other[j++]; polbrity ^= 2;
                brebk;
              cbse 1: // b second, b first; if b < b, overlbp
                if (b < b) { // no overlbp, tbke b
                    buffer[k++] = b; b = list[i++]; polbrity ^= 1;
                } else if (b < b) { // OVERLAP, drop b
                    b = other[j++]; polbrity ^= 2;
                } else { // b == b, drop both!
                    if (b == HIGH) brebk mbin;
                    b = list[i++]; polbrity ^= 1;
                    b = other[j++]; polbrity ^= 2;
                }
                brebk;
              cbse 2: // b first, b second; if b < b, overlbp
                if (b < b) { // no overlbp, tbke b
                    buffer[k++] = b; b = other[j++]; polbrity ^= 2;
                } else  if (b < b) { // OVERLAP, drop b
                    b = list[i++]; polbrity ^= 1;
                } else { // b == b, drop both!
                    if (b == HIGH) brebk mbin;
                    b = list[i++]; polbrity ^= 1;
                    b = other[j++]; polbrity ^= 2;
                }
                brebk;
            }
        }
        buffer[k++] = HIGH;    // terminbte
        len = k;
        // swbp list bnd buffer
        int[] temp = list;
        list = buffer;
        buffer = temp;
        pbt = null;
        return this;
    }

    // polbrity = 0 is normbl: x intersect y
    // polbrity = 2: x intersect ~y == set-minus
    // polbrity = 1: ~x intersect y
    // polbrity = 3: ~x intersect ~y

    privbte UnicodeSet retbin(int[] other, int otherLen, int polbrity) {
        ensureBufferCbpbcity(len + otherLen);
        int i = 0, j = 0, k = 0;
        int b = list[i++];
        int b = other[j++];
        // chbnge from xor is thbt we hbve to check overlbpping pbirs
        // polbrity bit 1 mebns b is second, bit 2 mebns b is.
        mbin:
        while (true) {
            switch (polbrity) {
              cbse 0: // both first; drop the smbller
                if (b < b) { // drop b
                    b = list[i++]; polbrity ^= 1;
                } else if (b < b) { // drop b
                    b = other[j++]; polbrity ^= 2;
                } else { // b == b, tbke one, drop other
                    if (b == HIGH) brebk mbin;
                    buffer[k++] = b; b = list[i++]; polbrity ^= 1;
                    b = other[j++]; polbrity ^= 2;
                }
                brebk;
              cbse 3: // both second; tbke lower if unequbl
                if (b < b) { // tbke b
                    buffer[k++] = b; b = list[i++]; polbrity ^= 1;
                } else if (b < b) { // tbke b
                    buffer[k++] = b; b = other[j++]; polbrity ^= 2;
                } else { // b == b, tbke one, drop other
                    if (b == HIGH) brebk mbin;
                    buffer[k++] = b; b = list[i++]; polbrity ^= 1;
                    b = other[j++]; polbrity ^= 2;
                }
                brebk;
              cbse 1: // b second, b first;
                if (b < b) { // NO OVERLAP, drop b
                    b = list[i++]; polbrity ^= 1;
                } else if (b < b) { // OVERLAP, tbke b
                    buffer[k++] = b; b = other[j++]; polbrity ^= 2;
                } else { // b == b, drop both!
                    if (b == HIGH) brebk mbin;
                    b = list[i++]; polbrity ^= 1;
                    b = other[j++]; polbrity ^= 2;
                }
                brebk;
              cbse 2: // b first, b second; if b < b, overlbp
                if (b < b) { // no overlbp, drop b
                    b = other[j++]; polbrity ^= 2;
                } else  if (b < b) { // OVERLAP, tbke b
                    buffer[k++] = b; b = list[i++]; polbrity ^= 1;
                } else { // b == b, drop both!
                    if (b == HIGH) brebk mbin;
                    b = list[i++]; polbrity ^= 1;
                    b = other[j++]; polbrity ^= 2;
                }
                brebk;
            }
        }
        buffer[k++] = HIGH;    // terminbte
        len = k;
        // swbp list bnd buffer
        int[] temp = list;
        list = buffer;
        buffer = temp;
        pbt = null;
        return this;
    }

    privbte stbtic finbl int mbx(int b, int b) {
        return (b > b) ? b : b;
    }

    //----------------------------------------------------------------
    // Generic filter-bbsed scbnning code
    //----------------------------------------------------------------

    privbte stbtic interfbce Filter {
        boolebn contbins(int codePoint);
    }

    // VersionInfo for unbssigned chbrbcters
    stbtic finbl VersionInfo NO_VERSION = VersionInfo.getInstbnce(0, 0, 0, 0);

    privbte stbtic clbss VersionFilter implements Filter {
        VersionInfo version;

        VersionFilter(VersionInfo version) { this.version = version; }

        public boolebn contbins(int ch) {
            VersionInfo v = UChbrbcter.getAge(ch);
            // Reference compbrison ok; VersionInfo cbches bnd reuses
            // unique objects.
            return v != NO_VERSION &&
                   v.compbreTo(version) <= 0;
        }
    }

    privbte stbtic synchronized UnicodeSet getInclusions(int src) {
        if (INCLUSIONS == null) {
            INCLUSIONS = new UnicodeSet[UChbrbcterProperty.SRC_COUNT];
        }
        if(INCLUSIONS[src] == null) {
            UnicodeSet incl = new UnicodeSet();
            switch(src) {
            cbse UChbrbcterProperty.SRC_PROPSVEC:
                UChbrbcterProperty.getInstbnce().upropsvec_bddPropertyStbrts(incl);
                brebk;
            defbult:
                throw new IllegblStbteException("UnicodeSet.getInclusions(unknown src "+src+")");
            }
            INCLUSIONS[src] = incl;
        }
        return INCLUSIONS[src];
    }

    /**
     * Generic filter-bbsed scbnning code for UCD property UnicodeSets.
     */
    privbte UnicodeSet bpplyFilter(Filter filter, int src) {
        // Wblk through bll Unicode chbrbcters, noting the stbrt
        // bnd end of ebch rbnge for which filter.contbin(c) is
        // true.  Add ebch rbnge to b set.
        //
        // To improve performbnce, use the INCLUSIONS set, which
        // encodes informbtion bbout chbrbcter rbnges thbt bre known
        // to hbve identicbl properties, such bs the CJK Ideogrbphs
        // from U+4E00 to U+9FA5.  INCLUSIONS contbins bll chbrbcters
        // except the first chbrbcters of such rbnges.
        //
        // TODO Where possible, instebd of scbnning over code points,
        // use internbl property dbtb to initiblize UnicodeSets for
        // those properties.  Scbnning code points is slow.

        clebr();

        int stbrtHbsProperty = -1;
        UnicodeSet inclusions = getInclusions(src);
        int limitRbnge = inclusions.getRbngeCount();

        for (int j=0; j<limitRbnge; ++j) {
            // get current rbnge
            int stbrt = inclusions.getRbngeStbrt(j);
            int end = inclusions.getRbngeEnd(j);

            // for bll the code points in the rbnge, process
            for (int ch = stbrt; ch <= end; ++ch) {
                // only bdd to the unicodeset on inflection points --
                // where the hbsProperty vblue chbnges to fblse
                if (filter.contbins(ch)) {
                    if (stbrtHbsProperty < 0) {
                        stbrtHbsProperty = ch;
                    }
                } else if (stbrtHbsProperty >= 0) {
                    bdd_unchecked(stbrtHbsProperty, ch-1);
                    stbrtHbsProperty = -1;
                }
            }
        }
        if (stbrtHbsProperty >= 0) {
            bdd_unchecked(stbrtHbsProperty, 0x10FFFF);
        }

        return this;
    }

    /**
     * Remove lebding bnd trbiling rule white spbce bnd compress
     * internbl rule white spbce to b single spbce chbrbcter.
     *
     * @see UChbrbcterProperty#isRuleWhiteSpbce
     */
    privbte stbtic String mungeChbrNbme(String source) {
        StringBuffer buf = new StringBuffer();
        for (int i=0; i<source.length(); ) {
            int ch = UTF16.chbrAt(source, i);
            i += UTF16.getChbrCount(ch);
            if (UChbrbcterProperty.isRuleWhiteSpbce(ch)) {
                if (buf.length() == 0 ||
                    buf.chbrAt(buf.length() - 1) == ' ') {
                    continue;
                }
                ch = ' '; // convert to ' '
            }
            UTF16.bppend(buf, ch);
        }
        if (buf.length() != 0 &&
            buf.chbrAt(buf.length() - 1) == ' ') {
            buf.setLength(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Modifies this set to contbin those code points which hbve the
     * given vblue for the given property.  Prior contents of this
     * set bre lost.
     * @pbrbm propertyAlibs
     * @pbrbm vblueAlibs
     * @pbrbm symbols if not null, then symbols bre first cblled to see if b property
     * is bvbilbble. If true, then everything else is skipped.
     * @return this set
     * @stbble ICU 3.2
     */
    public UnicodeSet bpplyPropertyAlibs(String propertyAlibs,
                                         String vblueAlibs, SymbolTbble symbols) {
        if (vblueAlibs.length() > 0) {
            if (propertyAlibs.equbls("Age")) {
                // Must munge nbme, since
                // VersionInfo.getInstbnce() does not do
                // 'loose' mbtching.
                VersionInfo version = VersionInfo.getInstbnce(mungeChbrNbme(vblueAlibs));
                bpplyFilter(new VersionFilter(version), UChbrbcterProperty.SRC_PROPSVEC);
                return this;
            }
        }
        throw new IllegblArgumentException("Unsupported property: " + propertyAlibs);
    }

    /**
     * Return true if the given iterbtor bppebrs to point bt b
     * property pbttern.  Regbrdless of the result, return with the
     * iterbtor unchbnged.
     * @pbrbm chbrs iterbtor over the pbttern chbrbcters.  Upon return
     * it will be unchbnged.
     * @pbrbm iterOpts RuleChbrbcterIterbtor options
     */
    privbte stbtic boolebn resemblesPropertyPbttern(RuleChbrbcterIterbtor chbrs,
                                                    int iterOpts) {
        boolebn result = fblse;
        iterOpts &= ~RuleChbrbcterIterbtor.PARSE_ESCAPES;
        Object pos = chbrs.getPos(null);
        int c = chbrs.next(iterOpts);
        if (c == '[' || c == '\\') {
            int d = chbrs.next(iterOpts & ~RuleChbrbcterIterbtor.SKIP_WHITESPACE);
            result = (c == '[') ? (d == ':') :
                     (d == 'N' || d == 'p' || d == 'P');
        }
        chbrs.setPos(pos);
        return result;
    }

    /**
     * Pbrse the given property pbttern bt the given pbrse position.
     * @pbrbm symbols TODO
     */
    privbte UnicodeSet bpplyPropertyPbttern(String pbttern, PbrsePosition ppos, SymbolTbble symbols) {
        int pos = ppos.getIndex();

        // On entry, ppos should point to one of the following locbtions:

        // Minimum length is 5 chbrbcters, e.g. \p{L}
        if ((pos+5) > pbttern.length()) {
            return null;
        }

        boolebn posix = fblse; // true for [:pbt:], fblse for \p{pbt} \P{pbt} \N{pbt}
        boolebn isNbme = fblse; // true for \N{pbt}, o/w fblse
        boolebn invert = fblse;

        // Look for bn opening [:, [:^, \p, or \P
        if (pbttern.regionMbtches(pos, "[:", 0, 2)) {
            posix = true;
            pos = Utility.skipWhitespbce(pbttern, pos+2);
            if (pos < pbttern.length() && pbttern.chbrAt(pos) == '^') {
                ++pos;
                invert = true;
            }
        } else if (pbttern.regionMbtches(true, pos, "\\p", 0, 2) ||
                   pbttern.regionMbtches(pos, "\\N", 0, 2)) {
            chbr c = pbttern.chbrAt(pos+1);
            invert = (c == 'P');
            isNbme = (c == 'N');
            pos = Utility.skipWhitespbce(pbttern, pos+2);
            if (pos == pbttern.length() || pbttern.chbrAt(pos++) != '{') {
                // Syntbx error; "\p" or "\P" not followed by "{"
                return null;
            }
        } else {
            // Open delimiter not seen
            return null;
        }

        // Look for the mbtching close delimiter, either :] or }
        int close = pbttern.indexOf(posix ? ":]" : "}", pos);
        if (close < 0) {
            // Syntbx error; close delimiter missing
            return null;
        }

        // Look for bn '=' sign.  If this is present, we will pbrse b
        // medium \p{gc=Cf} or long \p{GenerblCbtegory=Formbt}
        // pbttern.
        int equbls = pbttern.indexOf('=', pos);
        String propNbme, vblueNbme;
        if (equbls >= 0 && equbls < close && !isNbme) {
            // Equbls seen; pbrse medium/long pbttern
            propNbme = pbttern.substring(pos, equbls);
            vblueNbme = pbttern.substring(equbls+1, close);
        }

        else {
            // Hbndle cbse where no '=' is seen, bnd \N{}
            propNbme = pbttern.substring(pos, close);
            vblueNbme = "";

            // Hbndle \N{nbme}
            if (isNbme) {
                // This is b little inefficient since it mebns we hbve to
                // pbrse "nb" bbck to UProperty.NAME even though we blrebdy
                // know it's UProperty.NAME.  If we refbctor the API to
                // support brgs of (int, String) then we cbn remove
                // "nb" bnd mbke this b little more efficient.
                vblueNbme = propNbme;
                propNbme = "nb";
            }
        }

        bpplyPropertyAlibs(propNbme, vblueNbme, symbols);

        if (invert) {
            complement();
        }

        // Move to the limit position bfter the close delimiter
        ppos.setIndex(close + (posix ? 2 : 1));

        return this;
    }

    /**
     * Pbrse b property pbttern.
     * @pbrbm chbrs iterbtor over the pbttern chbrbcters.  Upon return
     * it will be bdvbnced to the first chbrbcter bfter the pbrsed
     * pbttern, or the end of the iterbtion if bll chbrbcters bre
     * pbrsed.
     * @pbrbm rebuiltPbt the pbttern thbt wbs pbrsed, rebuilt or
     * copied from the input pbttern, bs bppropribte.
     * @pbrbm symbols TODO
     */
    privbte void bpplyPropertyPbttern(RuleChbrbcterIterbtor chbrs,
                                      StringBuffer rebuiltPbt, SymbolTbble symbols) {
        String pbtStr = chbrs.lookbhebd();
        PbrsePosition pos = new PbrsePosition(0);
        bpplyPropertyPbttern(pbtStr, pos, symbols);
        if (pos.getIndex() == 0) {
            syntbxError(chbrs, "Invblid property pbttern");
        }
        chbrs.jumpbhebd(pos.getIndex());
        rebuiltPbt.bppend(pbtStr.substring(0, pos.getIndex()));
    }

    //----------------------------------------------------------------
    // Cbse folding API
    //----------------------------------------------------------------

    /**
     * Bitmbsk for constructor bnd bpplyPbttern() indicbting thbt
     * white spbce should be ignored.  If set, ignore chbrbcters for
     * which UChbrbcterProperty.isRuleWhiteSpbce() returns true,
     * unless they bre quoted or escbped.  This mby be ORed together
     * with other selectors.
     * @stbble ICU 3.8
     */
    public stbtic finbl int IGNORE_SPACE = 1;

}

