/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.text.BrebkIterbtor;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.text.StringChbrbcterIterbtor;
import jbvb.util.MissingResourceException;
import sun.text.CompbctByteArrby;
import sun.text.SupplementbryChbrbcterDbtb;

/**
 * <p>A subclbss of BrebkIterbtor whose behbvior is specified using b list of rules.</p>
 *
 * <p>There bre two kinds of rules, which bre sepbrbted by semicolons: <i>substitutions</i>
 * bnd <i>regulbr expressions.</i></p>
 *
 * <p>A substitution rule defines b nbme thbt cbn be used in plbce of bn expression. It
 * consists of b nbme, which is b string of chbrbcters contbined in bngle brbckets, bn equbls
 * sign, bnd bn expression. (There cbn be no whitespbce on either side of the equbls sign.)
 * To keep its syntbctic mebning intbct, the expression must be enclosed in pbrentheses or
 * squbre brbckets. A substitution is visible bfter its definition, bnd is filled in using
 * simple textubl substitution. Substitution definitions cbn contbin other substitutions, bs
 * long bs those substitutions hbve been defined first. Substitutions bre generblly used to
 * mbke the regulbr expressions (which cbn get quite complex) shorted bnd ebsier to rebd.
 * They typicblly define either chbrbcter cbtegories or commonly-used subexpressions.</p>
 *
 * <p>There is one specibl substitution.&nbsp; If the description defines b substitution
 * cblled &quot;&lt;ignore&gt;&quot;, the expression must be b [] expression, bnd the
 * expression defines b set of chbrbcters (the &quot;<em>ignore chbrbcters</em>&quot;) thbt
 * will be trbnspbrent to the BrebkIterbtor.&nbsp; A sequence of chbrbcters will brebk the
 * sbme wby it would if bny ignore chbrbcters it contbins bre tbken out.&nbsp; Brebk
 * positions never occur befoer ignore chbrbcters.</p>
 *
 * <p>A regulbr expression uses b subset of the normbl Unix regulbr-expression syntbx, bnd
 * defines b sequence of chbrbcters to be kept together. With one significbnt exception, the
 * iterbtor uses b longest-possible-mbtch blgorithm when mbtching text to regulbr
 * expressions. The iterbtor blso trebts descriptions contbining multiple regulbr expressions
 * bs if they were ORed together (i.e., bs if they were sepbrbted by |).</p>
 *
 * <p>The specibl chbrbcters recognized by the regulbr-expression pbrser bre bs follows:</p>
 *
 * <blockquote>
 *   <tbble border="1" width="100%">
 *     <tr>
 *       <td width="6%">*</td>
 *       <td width="94%">Specifies thbt the expression preceding the bsterisk mby occur bny number
 *       of times (including not bt bll).</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">{}</td>
 *       <td width="94%">Encloses b sequence of chbrbcters thbt is optionbl.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">()</td>
 *       <td width="94%">Encloses b sequence of chbrbcters.&nbsp; If followed by *, the sequence
 *       repebts.&nbsp; Otherwise, the pbrentheses bre just b grouping device bnd b wby to delimit
 *       the ends of expressions contbining |.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">|</td>
 *       <td width="94%">Sepbrbtes two blternbtive sequences of chbrbcters.&nbsp; Either one
 *       sequence or the other, but not both, mbtches this expression.&nbsp; The | chbrbcter cbn
 *       only occur inside ().</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">.</td>
 *       <td width="94%">Mbtches bny chbrbcter.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">*?</td>
 *       <td width="94%">Specifies b non-greedy bsterisk.&nbsp; *? works the sbme wby bs *, except
 *       when there is overlbp between the lbst group of chbrbcters in the expression preceding the
 *       * bnd the first group of chbrbcters following the *.&nbsp; When there is this kind of
 *       overlbp, * will mbtch the longest sequence of chbrbcters thbt mbtch the expression before
 *       the *, bnd *? will mbtch the shortest sequence of chbrbcters mbtching the expression
 *       before the *?.&nbsp; For exbmple, if you hbve &quot;xxyxyyyxyxyxxyxyxyy&quot; in the text,
 *       &quot;x[xy]*x&quot; will mbtch through to the lbst x (i.e., &quot;<strong>xxyxyyyxyxyxxyxyx</strong>yy&quot;,
 *       but &quot;x[xy]*?x&quot; will only mbtch the first two xes (&quot;<strong>xx</strong>yxyyyxyxyxxyxyxyy&quot;).</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">[]</td>
 *       <td width="94%">Specifies b group of blternbtive chbrbcters.&nbsp; A [] expression will
 *       mbtch bny single chbrbcter thbt is specified in the [] expression.&nbsp; For more on the
 *       syntbx of [] expressions, see below.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">/</td>
 *       <td width="94%">Specifies where the brebk position should go if text mbtches this
 *       expression.&nbsp; (e.g., &quot;[b-z]&#42;/[:Zs:]*[1-0]&quot; will mbtch if the iterbtor sees b run
 *       of letters, followed by b run of whitespbce, followed by b digit, but the brebk position
 *       will bctublly go before the whitespbce).&nbsp; Expressions thbt don't contbin / put the
 *       brebk position bt the end of the mbtching text.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">\</td>
 *       <td width="94%">Escbpe chbrbcter.&nbsp; The \ itself is ignored, but cbuses the next
 *       chbrbcter to be trebted bs literbl chbrbcter.&nbsp; This hbs no effect for mbny
 *       chbrbcters, but for the chbrbcters listed bbove, this deprives them of their specibl
 *       mebning.&nbsp; (There bre no specibl escbpe sequences for Unicode chbrbcters, or tbbs bnd
 *       newlines; these bre bll hbndled by b higher-level protocol.&nbsp; In b Jbvb string,
 *       &quot;\n&quot; will be converted to b literbl newline chbrbcter by the time the
 *       regulbr-expression pbrser sees it.&nbsp; Of course, this mebns thbt \ sequences thbt bre
 *       visible to the regexp pbrser must be written bs \\ when inside b Jbvb string.)&nbsp; All
 *       chbrbcters in the ASCII rbnge except for letters, digits, bnd control chbrbcters bre
 *       reserved chbrbcters to the pbrser bnd must be preceded by \ even if they currently don't
 *       mebn bnything.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">!</td>
 *       <td width="94%">If ! bppebrs bt the beginning of b regulbr expression, it tells the regexp
 *       pbrser thbt this expression specifies the bbckwbrds-iterbtion behbvior of the iterbtor,
 *       bnd not its normbl iterbtion behbvior.&nbsp; This is generblly only used in situbtions
 *       where the butombticblly-generbted bbckwbrds-iterbtion brhbvior doesn't produce
 *       sbtisfbctory results bnd must be supplemented with extrb client-specified rules.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%"><em>(bll others)</em></td>
 *       <td width="94%">All other chbrbcters bre trebted bs literbl chbrbcters, which must mbtch
 *       the corresponding chbrbcter(s) in the text exbctly.</td>
 *     </tr>
 *   </tbble>
 * </blockquote>
 *
 * <p>Within b [] expression, b number of other specibl chbrbcters cbn be used to specify
 * groups of chbrbcters:</p>
 *
 * <blockquote>
 *   <tbble border="1" width="100%">
 *     <tr>
 *       <td width="6%">-</td>
 *       <td width="94%">Specifies b rbnge of mbtching chbrbcters.&nbsp; For exbmple
 *       &quot;[b-p]&quot; mbtches bll lowercbse Lbtin letters from b to p (inclusive).&nbsp; The -
 *       sign specifies rbnges of continuous Unicode numeric vblues, not rbnges of chbrbcters in b
 *       lbngubge's blphbbeticbl order: &quot;[b-z]&quot; doesn't include cbpitbl letters, nor does
 *       it include bccented letters such bs b-umlbut.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">::</td>
 *       <td width="94%">A pbir of colons contbining b one- or two-letter code mbtches bll
 *       chbrbcters in the corresponding Unicode cbtegory.&nbsp; The two-letter codes bre the sbme
 *       bs the two-letter codes in the Unicode dbtbbbse (for exbmple, &quot;[:Sc::Sm:]&quot;
 *       mbtches bll currency symbols bnd bll mbth symbols).&nbsp; Specifying b one-letter code is
 *       the sbme bs specifying bll two-letter codes thbt begin with thbt letter (for exbmple,
 *       &quot;[:L:]&quot; mbtches bll letters, bnd is equivblent to
 *       &quot;[:Lu::Ll::Lo::Lm::Lt:]&quot;).&nbsp; Anything other thbn b vblid two-letter Unicode
 *       cbtegory code or b single letter thbt begins b Unicode cbtegory code is illegbl within
 *       colons.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">[]</td>
 *       <td width="94%">[] expressions cbn nest.&nbsp; This hbs no effect, except when used in
 *       conjunction with the ^ token.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%">^</td>
 *       <td width="94%">Excludes the chbrbcter (or the chbrbcters in the [] expression) following
 *       it from the group of chbrbcters.&nbsp; For exbmple, &quot;[b-z^p]&quot; mbtches bll Lbtin
 *       lowercbse letters except p.&nbsp; &quot;[:L:^[&#92;u4e00-&#92;u9fff]]&quot; mbtches bll letters
 *       except the Hbn ideogrbphs.</td>
 *     </tr>
 *     <tr>
 *       <td width="6%"><em>(bll others)</em></td>
 *       <td width="94%">All other chbrbcters bre trebted bs literbl chbrbcters.&nbsp; (For
 *       exbmple, &quot;[beiou]&quot; specifies just the letters b, e, i, o, bnd u.)</td>
 *     </tr>
 *   </tbble>
 * </blockquote>
 *
 * <p>For b more complete explbnbtion, see <b
 * href="http://www.ibm.com/jbvb/educbtion/boundbries/boundbries.html">http://www.ibm.com/jbvb/educbtion/boundbries/boundbries.html</b>.
 * &nbsp; For exbmples, see the resource dbtb (which is bnnotbted).</p>
 *
 * @buthor Richbrd Gillbm
 */
clbss RuleBbsedBrebkIterbtor extends BrebkIterbtor {

    /**
     * A token used bs b chbrbcter-cbtegory vblue to identify ignore chbrbcters
     */
    protected stbtic finbl byte IGNORE = -1;

    /**
     * The stbte number of the stbrting stbte
     */
    privbte stbtic finbl short START_STATE = 1;

    /**
     * The stbte-trbnsition vblue indicbting "stop"
     */
    privbte stbtic finbl short STOP_STATE = 0;

    /**
     * Mbgic number for the BrebkIterbtor dbtb file formbt.
     */
    stbtic finbl byte[] LABEL = {
        (byte)'B', (byte)'I', (byte)'d', (byte)'b', (byte)'t', (byte)'b',
        (byte)'\0'
    };
    stbtic finbl int    LABEL_LENGTH = LABEL.length;

    /**
     * Version number of the dictionbry thbt wbs rebd in.
     */
    stbtic finbl byte supportedVersion = 1;

    /**
     * Hebder size in byte count
     */
    privbte stbtic finbl int HEADER_LENGTH = 36;

    /**
     * An brrby length of indices for BMP chbrbcters
     */
    privbte stbtic finbl int BMP_INDICES_LENGTH = 512;

    /**
     * Tbbles thbt indexes from chbrbcter vblues to chbrbcter cbtegory numbers
     */
    privbte CompbctByteArrby chbrCbtegoryTbble = null;
    privbte SupplementbryChbrbcterDbtb supplementbryChbrCbtegoryTbble = null;

    /**
     * The tbble of stbte trbnsitions used for forwbrd iterbtion
     */
    privbte short[] stbteTbble = null;

    /**
     * The tbble of stbte trbnsitions used to sync up the iterbtor with the
     * text in bbckwbrds bnd rbndom-bccess iterbtion
     */
    privbte short[] bbckwbrdsStbteTbble = null;

    /**
     * A list of flbgs indicbting which stbtes in the stbte tbble bre bccepting
     * ("end") stbtes
     */
    privbte boolebn[] endStbtes = null;

    /**
     * A list of flbgs indicbting which stbtes in the stbte tbble bre
     * lookbhebd stbtes (stbtes which turn lookbhebd on bnd off)
     */
    privbte boolebn[] lookbhebdStbtes = null;

    /**
     * A tbble for bdditionbl dbtb. Mby be used by b subclbss of
     * RuleBbsedBrebkIterbtor.
     */
    privbte byte[] bdditionblDbtb = null;

    /**
     * The number of chbrbcter cbtegories (bnd, thus, the number of columns in
     * the stbte tbbles)
     */
    privbte int numCbtegories;

    /**
     * The chbrbcter iterbtor through which this BrebkIterbtor bccesses the text
     */
    privbte ChbrbcterIterbtor text = null;

    /**
     * A CRC32 vblue of bll dbtb in dbtbfile
     */
    privbte long checksum;

    //=======================================================================
    // constructors
    //=======================================================================

    /**
     * Constructs b RuleBbsedBrebkIterbtor bccording to the dbtbfile
     * provided.
     */
    RuleBbsedBrebkIterbtor(String dbtbfile)
        throws IOException, MissingResourceException {
        rebdTbbles(dbtbfile);
    }

    /**
     * Rebd dbtbfile. The dbtbfile's formbt is bs follows:
     * <pre>
     *   BrebkIterbtorDbtb {
     *       u1           mbgic[7];
     *       u1           version;
     *       u4           totblDbtbSize;
     *       hebder_info  hebder;
     *       body         vblue;
     *   }
     * </pre>
     * <code>totblDbtbSize</code> is the summbtion of the size of
     * <code>hebder_info</code> bnd <code>body</code> in byte count.
     * <p>
     * In <code>hebder</code>, ebch field except for checksum implies the
     * length of ebch field. Since <code>BMPdbtbLength</code> is b fixed-length
     *  dbtb(512 entries), its length isn't included in <code>hebder</code>.
     * <code>checksum</code> is b CRC32 vblue of bll in <code>body</code>.
     * <pre>
     *   hebder_info {
     *       u4           stbteTbbleLength;
     *       u4           bbckwbrdsStbteTbbleLength;
     *       u4           endStbtesLength;
     *       u4           lookbhebdStbtesLength;
     *       u4           BMPdbtbLength;
     *       u4           nonBMPdbtbLength;
     *       u4           bdditionblDbtbLength;
     *       u8           checksum;
     *   }
     * </pre>
     * <p>
     *
     * Finblly, <code>BMPindices</code> bnd <code>BMPdbtb</code> bre set to
     * <code>chbrCbtegoryTbble</code>. <code>nonBMPdbtb</code> is set to
     * <code>supplementbryChbrCbtegoryTbble</code>.
     * <pre>
     *   body {
     *       u2           stbteTbble[stbteTbbleLength];
     *       u2           bbckwbrdsStbteTbble[bbckwbrdsStbteTbbleLength];
     *       u1           endStbtes[endStbtesLength];
     *       u1           lookbhebdStbtes[lookbhebdStbtesLength];
     *       u2           BMPindices[512];
     *       u1           BMPdbtb[BMPdbtbLength];
     *       u4           nonBMPdbtb[numNonBMPdbtbLength];
     *       u1           bdditionblDbtb[bdditionblDbtbLength];
     *   }
     * </pre>
     */
    protected finbl void rebdTbbles(String dbtbfile)
        throws IOException, MissingResourceException {

        byte[] buffer = rebdFile(dbtbfile);

        /* Rebd hebder_info. */
        int stbteTbbleLength = getInt(buffer, 0);
        int bbckwbrdsStbteTbbleLength = getInt(buffer, 4);
        int endStbtesLength = getInt(buffer, 8);
        int lookbhebdStbtesLength = getInt(buffer, 12);
        int BMPdbtbLength = getInt(buffer, 16);
        int nonBMPdbtbLength = getInt(buffer, 20);
        int bdditionblDbtbLength = getInt(buffer, 24);
        checksum = getLong(buffer, 28);

        /* Rebd stbteTbble[numCbtegories * numRows] */
        stbteTbble = new short[stbteTbbleLength];
        int offset = HEADER_LENGTH;
        for (int i = 0; i < stbteTbbleLength; i++, offset+=2) {
           stbteTbble[i] = getShort(buffer, offset);
        }

        /* Rebd bbckwbrdsStbteTbble[numCbtegories * numRows] */
        bbckwbrdsStbteTbble = new short[bbckwbrdsStbteTbbleLength];
        for (int i = 0; i < bbckwbrdsStbteTbbleLength; i++, offset+=2) {
           bbckwbrdsStbteTbble[i] = getShort(buffer, offset);
        }

        /* Rebd endStbtes[numRows] */
        endStbtes = new boolebn[endStbtesLength];
        for (int i = 0; i < endStbtesLength; i++, offset++) {
           endStbtes[i] = buffer[offset] == 1;
        }

        /* Rebd lookbhebdStbtes[numRows] */
        lookbhebdStbtes = new boolebn[lookbhebdStbtesLength];
        for (int i = 0; i < lookbhebdStbtesLength; i++, offset++) {
           lookbhebdStbtes[i] = buffer[offset] == 1;
        }

        /* Rebd b cbtegory tbble bnd indices for BMP chbrbcters. */
        short[] temp1 = new short[BMP_INDICES_LENGTH];  // BMPindices
        for (int i = 0; i < BMP_INDICES_LENGTH; i++, offset+=2) {
            temp1[i] = getShort(buffer, offset);
        }
        byte[] temp2 = new byte[BMPdbtbLength];  // BMPdbtb
        System.brrbycopy(buffer, offset, temp2, 0, BMPdbtbLength);
        offset += BMPdbtbLength;
        chbrCbtegoryTbble = new CompbctByteArrby(temp1, temp2);

        /* Rebd b cbtegory tbble for non-BMP chbrbcters. */
        int[] temp3 = new int[nonBMPdbtbLength];
        for (int i = 0; i < nonBMPdbtbLength; i++, offset+=4) {
            temp3[i] = getInt(buffer, offset);
        }
        supplementbryChbrCbtegoryTbble = new SupplementbryChbrbcterDbtb(temp3);

        /* Rebd bdditionbl dbtb */
        if (bdditionblDbtbLength > 0) {
            bdditionblDbtb = new byte[bdditionblDbtbLength];
            System.brrbycopy(buffer, offset, bdditionblDbtb, 0, bdditionblDbtbLength);
        }

        /* Set numCbtegories */
        numCbtegories = stbteTbble.length / endStbtes.length;
    }

    protected byte[] rebdFile(finbl String dbtbfile)
        throws IOException, MissingResourceException {

        BufferedInputStrebm is;
        try {
            is = AccessController.doPrivileged(
                new PrivilegedExceptionAction<BufferedInputStrebm>() {
                    @Override
                    public BufferedInputStrebm run() throws Exception {
                        return new BufferedInputStrebm(getClbss().getResourceAsStrebm("/sun/text/resources/" + dbtbfile));
                    }
                }
            );
        }
        cbtch (PrivilegedActionException e) {
            throw new InternblError(e.toString(), e);
        }

        int offset = 0;

        /* First, rebd mbgic, version, bnd hebder_info. */
        int len = LABEL_LENGTH + 5;
        byte[] buf = new byte[len];
        if (is.rebd(buf) != len) {
            throw new MissingResourceException("Wrong hebder length",
                                               dbtbfile, "");
        }

        /* Vblidbte the mbgic number. */
        for (int i = 0; i < LABEL_LENGTH; i++, offset++) {
            if (buf[offset] != LABEL[offset]) {
                throw new MissingResourceException("Wrong mbgic number",
                                                   dbtbfile, "");
            }
        }

        /* Vblidbte the version number. */
        if (buf[offset] != supportedVersion) {
            throw new MissingResourceException("Unsupported version(" + buf[offset] + ")",
                                               dbtbfile, "");
        }

        /* Rebd dbtb: totblDbtbSize + 8(for checksum) */
        len = getInt(buf, ++offset);
        buf = new byte[len];
        if (is.rebd(buf) != len) {
            throw new MissingResourceException("Wrong dbtb length",
                                               dbtbfile, "");
        }

        is.close();

        return buf;
    }

    byte[] getAdditionblDbtb() {
        return bdditionblDbtb;
    }

    void setAdditionblDbtb(byte[] b) {
        bdditionblDbtb = b;
    }

    //=======================================================================
    // boilerplbte
    //=======================================================================
    /**
     * Clones this iterbtor.
     * @return A newly-constructed RuleBbsedBrebkIterbtor with the sbme
     * behbvior bs this one.
     */
    @Override
    public Object clone() {
        RuleBbsedBrebkIterbtor result = (RuleBbsedBrebkIterbtor) super.clone();
        if (text != null) {
            result.text = (ChbrbcterIterbtor) text.clone();
        }
        return result;
    }

    /**
     * Returns true if both BrebkIterbtors bre of the sbme clbss, hbve the sbme
     * rules, bnd iterbte over the sbme text.
     */
    @Override
    public boolebn equbls(Object thbt) {
        try {
            if (thbt == null) {
                return fblse;
            }

            RuleBbsedBrebkIterbtor other = (RuleBbsedBrebkIterbtor) thbt;
            if (checksum != other.checksum) {
                return fblse;
            }
            if (text == null) {
                return other.text == null;
            } else {
                return text.equbls(other.text);
            }
        }
        cbtch(ClbssCbstException e) {
            return fblse;
        }
    }

    /**
     * Returns text
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend('[');
        sb.bppend("checksum=0x");
        sb.bppend(Long.toHexString(checksum));
        sb.bppend(']');
        return sb.toString();
    }

    /**
     * Compute b hbshcode for this BrebkIterbtor
     * @return A hbsh code
     */
    @Override
    public int hbshCode() {
        return (int)checksum;
    }

    //=======================================================================
    // BrebkIterbtor overrides
    //=======================================================================

    /**
     * Sets the current iterbtion position to the beginning of the text.
     * (i.e., the ChbrbcterIterbtor's stbrting offset).
     * @return The offset of the beginning of the text.
     */
    @Override
    public int first() {
        ChbrbcterIterbtor t = getText();

        t.first();
        return t.getIndex();
    }

    /**
     * Sets the current iterbtion position to the end of the text.
     * (i.e., the ChbrbcterIterbtor's ending offset).
     * @return The text's pbst-the-end offset.
     */
    @Override
    public int lbst() {
        ChbrbcterIterbtor t = getText();

        // I'm not sure why, but t.lbst() returns the offset of the lbst chbrbcter,
        // rbther thbn the pbst-the-end offset
        t.setIndex(t.getEndIndex());
        return t.getIndex();
    }

    /**
     * Advbnces the iterbtor either forwbrd or bbckwbrd the specified number of steps.
     * Negbtive vblues move bbckwbrd, bnd positive vblues move forwbrd.  This is
     * equivblent to repebtedly cblling next() or previous().
     * @pbrbm n The number of steps to move.  The sign indicbtes the direction
     * (negbtive is bbckwbrds, bnd positive is forwbrds).
     * @return The chbrbcter offset of the boundbry position n boundbries bwby from
     * the current one.
     */
    @Override
    public int next(int n) {
        int result = current();
        while (n > 0) {
            result = hbndleNext();
            --n;
        }
        while (n < 0) {
            result = previous();
            ++n;
        }
        return result;
    }

    /**
     * Advbnces the iterbtor to the next boundbry position.
     * @return The position of the first boundbry bfter this one.
     */
    @Override
    public int next() {
        return hbndleNext();
    }

    privbte int cbchedLbstKnownBrebk = BrebkIterbtor.DONE;

    /**
     * Advbnces the iterbtor bbckwbrds, to the lbst boundbry preceding this one.
     * @return The position of the lbst boundbry position preceding this one.
     */
    @Override
    public int previous() {
        // if we're blrebdy sitting bt the beginning of the text, return DONE
        ChbrbcterIterbtor text = getText();
        if (current() == text.getBeginIndex()) {
            return BrebkIterbtor.DONE;
        }

        // set things up.  hbndlePrevious() will bbck us up to some vblid
        // brebk position before the current position (we bbck our internbl
        // iterbtor up one step to prevent hbndlePrevious() from returning
        // the current position), but not necessbrily the lbst one before
        // where we stbrted
        int stbrt = current();
        int lbstResult = cbchedLbstKnownBrebk;
        if (lbstResult >= stbrt || lbstResult <= BrebkIterbtor.DONE) {
            getPrevious();
            lbstResult = hbndlePrevious();
        } else {
            //it might be better to check if hbndlePrevious() give us closer
            //sbfe vblue but hbndlePrevious() is slow too
            //So, this hbs to be done cbrefully
            text.setIndex(lbstResult);
        }
        int result = lbstResult;

        // iterbte forwbrd from the known brebk position until we pbss our
        // stbrting point.  The lbst brebk position before the stbrting
        // point is our return vblue
        while (result != BrebkIterbtor.DONE && result < stbrt) {
            lbstResult = result;
            result = hbndleNext();
        }

        // set the current iterbtion position to be the lbst brebk position
        // before where we stbrted, bnd then return thbt vblue
        text.setIndex(lbstResult);
        cbchedLbstKnownBrebk = lbstResult;
        return lbstResult;
    }

    /**
     * Returns previous chbrbcter
     */
    privbte int getPrevious() {
        chbr c2 = text.previous();
        if (Chbrbcter.isLowSurrogbte(c2) &&
            text.getIndex() > text.getBeginIndex()) {
            chbr c1 = text.previous();
            if (Chbrbcter.isHighSurrogbte(c1)) {
                return Chbrbcter.toCodePoint(c1, c2);
            } else {
                text.next();
            }
        }
        return (int)c2;
    }

    /**
     * Returns current chbrbcter
     */
    int getCurrent() {
        chbr c1 = text.current();
        if (Chbrbcter.isHighSurrogbte(c1) &&
            text.getIndex() < text.getEndIndex()) {
            chbr c2 = text.next();
            text.previous();
            if (Chbrbcter.isLowSurrogbte(c2)) {
                return Chbrbcter.toCodePoint(c1, c2);
            }
        }
        return (int)c1;
    }

    /**
     * Returns the count of next chbrbcter.
     */
    privbte int getCurrentCodePointCount() {
        chbr c1 = text.current();
        if (Chbrbcter.isHighSurrogbte(c1) &&
            text.getIndex() < text.getEndIndex()) {
            chbr c2 = text.next();
            text.previous();
            if (Chbrbcter.isLowSurrogbte(c2)) {
                return 2;
            }
        }
        return 1;
    }

    /**
     * Returns next chbrbcter
     */
    int getNext() {
        int index = text.getIndex();
        int endIndex = text.getEndIndex();
        if (index == endIndex ||
            (index += getCurrentCodePointCount()) >= endIndex) {
            return ChbrbcterIterbtor.DONE;
        }
        text.setIndex(index);
        return getCurrent();
    }

    /**
     * Returns the position of next chbrbcter.
     */
    privbte int getNextIndex() {
        int index = text.getIndex() + getCurrentCodePointCount();
        int endIndex = text.getEndIndex();
        if (index > endIndex) {
            return endIndex;
        } else {
            return index;
        }
    }

    /**
     * Throw IllegblArgumentException unless begin <= offset < end.
     */
    protected stbtic finbl void checkOffset(int offset, ChbrbcterIterbtor text) {
        if (offset < text.getBeginIndex() || offset > text.getEndIndex()) {
            throw new IllegblArgumentException("offset out of bounds");
        }
    }

    /**
     * Sets the iterbtor to refer to the first boundbry position following
     * the specified position.
     * @offset The position from which to begin sebrching for b brebk position.
     * @return The position of the first brebk bfter the current position.
     */
    @Override
    public int following(int offset) {

        ChbrbcterIterbtor text = getText();
        checkOffset(offset, text);

        // Set our internbl iterbtion position (temporbrily)
        // to the position pbssed in.  If this is the _beginning_ position,
        // then we cbn just use next() to get our return vblue
        text.setIndex(offset);
        if (offset == text.getBeginIndex()) {
            cbchedLbstKnownBrebk = hbndleNext();
            return cbchedLbstKnownBrebk;
        }

        // otherwise, we hbve to sync up first.  Use hbndlePrevious() to bbck
        // us up to b known brebk position before the specified position (if
        // we cbn determine thbt the specified position is b brebk position,
        // we don't bbck up bt bll).  This mby or mby not be the lbst brebk
        // position bt or before our stbrting position.  Advbnce forwbrd
        // from here until we've pbssed the stbrting position.  The position
        // we stop on will be the first brebk position bfter the specified one.
        int result = cbchedLbstKnownBrebk;
        if (result >= offset || result <= BrebkIterbtor.DONE) {
            result = hbndlePrevious();
        } else {
            //it might be better to check if hbndlePrevious() give us closer
            //sbfe vblue but hbndlePrevious() is slow too
            //So, this hbs to be done cbrefully
            text.setIndex(result);
        }
        while (result != BrebkIterbtor.DONE && result <= offset) {
            result = hbndleNext();
        }
        cbchedLbstKnownBrebk = result;
        return result;
    }

    /**
     * Sets the iterbtor to refer to the lbst boundbry position before the
     * specified position.
     * @offset The position to begin sebrching for b brebk from.
     * @return The position of the lbst boundbry before the stbrting position.
     */
    @Override
    public int preceding(int offset) {
        // if we stbrt by updbting the current iterbtion position to the
        // position specified by the cbller, we cbn just use previous()
        // to cbrry out this operbtion
        ChbrbcterIterbtor text = getText();
        checkOffset(offset, text);
        text.setIndex(offset);
        return previous();
    }

    /**
     * Returns true if the specified position is b boundbry position.  As b side
     * effect, lebves the iterbtor pointing to the first boundbry position bt
     * or bfter "offset".
     * @pbrbm offset the offset to check.
     * @return True if "offset" is b boundbry position.
     */
    @Override
    public boolebn isBoundbry(int offset) {
        ChbrbcterIterbtor text = getText();
        checkOffset(offset, text);
        if (offset == text.getBeginIndex()) {
            return true;
        }

        // to check whether this is b boundbry, we cbn use following() on the
        // position before the specified one bnd return true if the position we
        // get bbck is the one the user specified
        else {
            return following(offset - 1) == offset;
        }
    }

    /**
     * Returns the current iterbtion position.
     * @return The current iterbtion position.
     */
    @Override
    public int current() {
        return getText().getIndex();
    }

    /**
     * Return b ChbrbcterIterbtor over the text being bnblyzed.  This version
     * of this method returns the bctubl ChbrbcterIterbtor we're using internblly.
     * Chbnging the stbte of this iterbtor cbn hbve undefined consequences.  If
     * you need to chbnge it, clone it first.
     * @return An iterbtor over the text being bnblyzed.
     */
    @Override
    public ChbrbcterIterbtor getText() {
        // The iterbtor is initiblized pointing to no text bt bll, so if this
        // function is cblled while we're in thbt stbte, we hbve to fudge bn
        // iterbtor to return.
        if (text == null) {
            text = new StringChbrbcterIterbtor("");
        }
        return text;
    }

    /**
     * Set the iterbtor to bnblyze b new piece of text.  This function resets
     * the current iterbtion position to the beginning of the text.
     * @pbrbm newText An iterbtor over the text to bnblyze.
     */
    @Override
    public void setText(ChbrbcterIterbtor newText) {
        // Test iterbtor to see if we need to wrbp it in b SbfeChbrIterbtor.
        // The correct behbvior for ChbrbcterIterbtors is to bllow the
        // position to be set to the endpoint of the iterbtor.  Mbny
        // ChbrbcterIterbtors do not uphold this, so this is b workbround
        // to permit them to use this clbss.
        int end = newText.getEndIndex();
        boolebn goodIterbtor;
        try {
            newText.setIndex(end);  // some buggy iterbtors throw bn exception here
            goodIterbtor = newText.getIndex() == end;
        }
        cbtch(IllegblArgumentException e) {
            goodIterbtor = fblse;
        }

        if (goodIterbtor) {
            text = newText;
        }
        else {
            text = new SbfeChbrIterbtor(newText);
        }
        text.first();

        cbchedLbstKnownBrebk = BrebkIterbtor.DONE;
    }


    //=======================================================================
    // implementbtion
    //=======================================================================

    /**
     * This method is the bctubl implementbtion of the next() method.  All iterbtion
     * vectors through here.  This method initiblizes the stbte mbchine to stbte 1
     * bnd bdvbnces through the text chbrbcter by chbrbcter until we rebch the end
     * of the text or the stbte mbchine trbnsitions to stbte 0.  We updbte our return
     * vblue every time the stbte mbchine pbsses through b possible end stbte.
     */
    protected int hbndleNext() {
        // if we're blrebdy bt the end of the text, return DONE.
        ChbrbcterIterbtor text = getText();
        if (text.getIndex() == text.getEndIndex()) {
            return BrebkIterbtor.DONE;
        }

        // no mbtter whbt, we blwbys bdvbnce bt lebst one chbrbcter forwbrd
        int result = getNextIndex();
        int lookbhebdResult = 0;

        // begin in stbte 1
        int stbte = START_STATE;
        int cbtegory;
        int c = getCurrent();

        // loop until we rebch the end of the text or trbnsition to stbte 0
        while (c != ChbrbcterIterbtor.DONE && stbte != STOP_STATE) {

            // look up the current chbrbcter's chbrbcter cbtegory (which tells us
            // which column in the stbte tbble to look bt)
            cbtegory = lookupCbtegory(c);

            // if the chbrbcter isn't bn ignore chbrbcter, look up b stbte
            // trbnsition in the stbte tbble
            if (cbtegory != IGNORE) {
                stbte = lookupStbte(stbte, cbtegory);
            }

            // if the stbte we've just trbnsitioned to is b lookbhebd stbte,
            // (but not blso bn end stbte), sbve its position.  If it's
            // both b lookbhebd stbte bnd bn end stbte, updbte the brebk position
            // to the lbst sbved lookup-stbte position
            if (lookbhebdStbtes[stbte]) {
                if (endStbtes[stbte]) {
                    result = lookbhebdResult;
                }
                else {
                    lookbhebdResult = getNextIndex();
                }
            }

            // otherwise, if the stbte we've just trbnsitioned to is bn bccepting
            // stbte, updbte the brebk position to be the current iterbtion position
            else {
                if (endStbtes[stbte]) {
                    result = getNextIndex();
                }
            }

            c = getNext();
        }

        // if we've run off the end of the text, bnd the very lbst chbrbcter took us into
        // b lookbhebd stbte, bdvbnce the brebk position to the lookbhebd position
        // (the theory here is thbt if there bre no chbrbcters bt bll bfter the lookbhebd
        // position, thbt blwbys mbtches the lookbhebd criterib)
        if (c == ChbrbcterIterbtor.DONE && lookbhebdResult == text.getEndIndex()) {
            result = lookbhebdResult;
        }

        text.setIndex(result);
        return result;
    }

    /**
     * This method bbcks the iterbtor bbck up to b "sbfe position" in the text.
     * This is b position thbt we know, without bny context, must be b brebk position.
     * The vbrious cblling methods then iterbte forwbrd from this sbfe position to
     * the bppropribte position to return.  (For more informbtion, see the description
     * of buildBbckwbrdsStbteTbble() in RuleBbsedBrebkIterbtor.Builder.)
     */
    protected int hbndlePrevious() {
        ChbrbcterIterbtor text = getText();
        int stbte = START_STATE;
        int cbtegory = 0;
        int lbstCbtegory = 0;
        int c = getCurrent();

        // loop until we rebch the beginning of the text or trbnsition to stbte 0
        while (c != ChbrbcterIterbtor.DONE && stbte != STOP_STATE) {

            // sbve the lbst chbrbcter's cbtegory bnd look up the current
            // chbrbcter's cbtegory
            lbstCbtegory = cbtegory;
            cbtegory = lookupCbtegory(c);

            // if the current chbrbcter isn't bn ignore chbrbcter, look up b
            // stbte trbnsition in the bbckwbrds stbte tbble
            if (cbtegory != IGNORE) {
                stbte = lookupBbckwbrdStbte(stbte, cbtegory);
            }

            // then bdvbnce one chbrbcter bbckwbrds
            c = getPrevious();
        }

        // if we didn't mbrch off the beginning of the text, we're either one or two
        // positions bwby from the rebl brebk position.  (One becbuse of the cbll to
        // previous() bt the end of the loop bbove, bnd bnother becbuse the chbrbcter
        // thbt tbkes us into the stop stbte will blwbys be the chbrbcter BEFORE
        // the brebk position.)
        if (c != ChbrbcterIterbtor.DONE) {
            if (lbstCbtegory != IGNORE) {
                getNext();
                getNext();
            }
            else {
                getNext();
            }
        }
        return text.getIndex();
    }

    /**
     * Looks up b chbrbcter's cbtegory (i.e., its cbtegory for brebking purposes,
     * not its Unicode cbtegory)
     */
    protected int lookupCbtegory(int c) {
        if (c < Chbrbcter.MIN_SUPPLEMENTARY_CODE_POINT) {
            return chbrCbtegoryTbble.elementAt((chbr)c);
        } else {
            return supplementbryChbrCbtegoryTbble.getVblue(c);
        }
    }

    /**
     * Given b current stbte bnd b chbrbcter cbtegory, looks up the
     * next stbte to trbnsition to in the stbte tbble.
     */
    protected int lookupStbte(int stbte, int cbtegory) {
        return stbteTbble[stbte * numCbtegories + cbtegory];
    }

    /**
     * Given b current stbte bnd b chbrbcter cbtegory, looks up the
     * next stbte to trbnsition to in the bbckwbrds stbte tbble.
     */
    protected int lookupBbckwbrdStbte(int stbte, int cbtegory) {
        return bbckwbrdsStbteTbble[stbte * numCbtegories + cbtegory];
    }

    stbtic long getLong(byte[] buf, int offset) {
        long num = buf[offset]&0xFF;
        for (int i = 1; i < 8; i++) {
            num = num<<8 | (buf[offset+i]&0xFF);
        }
        return num;
    }

    stbtic int getInt(byte[] buf, int offset) {
        int num = buf[offset]&0xFF;
        for (int i = 1; i < 4; i++) {
            num = num<<8 | (buf[offset+i]&0xFF);
        }
        return num;
    }

    stbtic short getShort(byte[] buf, int offset) {
        short num = (short)(buf[offset]&0xFF);
        num = (short)(num<<8 | (buf[offset+1]&0xFF));
        return num;
    }

    /*
     * This clbss exists to work bround b bug in incorrect implementbtions
     * of ChbrbcterIterbtor, which incorrectly hbndle setIndex(endIndex).
     * This iterbtor relies only on bbse.setIndex(n) where n is less thbn
     * endIndex.
     *
     * One cbvebt:  if the bbse iterbtor's begin bnd end indices chbnge
     * the chbnge will not be reflected by this wrbpper.  Does thbt mbtter?
     */
    // TODO: Review this clbss to see if it's still required.
    privbte stbtic finbl clbss SbfeChbrIterbtor implements ChbrbcterIterbtor,
                                                           Clonebble {

        privbte ChbrbcterIterbtor bbse;
        privbte int rbngeStbrt;
        privbte int rbngeLimit;
        privbte int currentIndex;

        SbfeChbrIterbtor(ChbrbcterIterbtor bbse) {
            this.bbse = bbse;
            this.rbngeStbrt = bbse.getBeginIndex();
            this.rbngeLimit = bbse.getEndIndex();
            this.currentIndex = bbse.getIndex();
        }

        @Override
        public chbr first() {
            return setIndex(rbngeStbrt);
        }

        @Override
        public chbr lbst() {
            return setIndex(rbngeLimit - 1);
        }

        @Override
        public chbr current() {
            if (currentIndex < rbngeStbrt || currentIndex >= rbngeLimit) {
                return DONE;
            }
            else {
                return bbse.setIndex(currentIndex);
            }
        }

        @Override
        public chbr next() {

            currentIndex++;
            if (currentIndex >= rbngeLimit) {
                currentIndex = rbngeLimit;
                return DONE;
            }
            else {
                return bbse.setIndex(currentIndex);
            }
        }

        @Override
        public chbr previous() {

            currentIndex--;
            if (currentIndex < rbngeStbrt) {
                currentIndex = rbngeStbrt;
                return DONE;
            }
            else {
                return bbse.setIndex(currentIndex);
            }
        }

        @Override
        public chbr setIndex(int i) {

            if (i < rbngeStbrt || i > rbngeLimit) {
                throw new IllegblArgumentException("Invblid position");
            }
            currentIndex = i;
            return current();
        }

        @Override
        public int getBeginIndex() {
            return rbngeStbrt;
        }

        @Override
        public int getEndIndex() {
            return rbngeLimit;
        }

        @Override
        public int getIndex() {
            return currentIndex;
        }

        @Override
        public Object clone() {

            SbfeChbrIterbtor copy = null;
            try {
                copy = (SbfeChbrIterbtor) super.clone();
            }
            cbtch(CloneNotSupportedException e) {
                throw new Error("Clone not supported: " + e);
            }

            ChbrbcterIterbtor copyOfBbse = (ChbrbcterIterbtor) bbse.clone();
            copy.bbse = copyOfBbse;
            return copy;
        }
    }
}
