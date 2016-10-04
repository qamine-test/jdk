/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import sun.security.util.*;

/**
 * This clbss defines the EDIPbrtyNbme of the GenerblNbme choice.
 * The ASN.1 syntbx for this is:
 * <pre>
 * EDIPbrtyNbme ::= SEQUENCE {
 *     nbmeAssigner  [0]  DirectoryString OPTIONAL,
 *     pbrtyNbme     [1]  DirectoryString }
 * </pre>
 *
 * @buthor Hemmb Prbfullchbndrb
 * @see GenerblNbme
 * @see GenerblNbmes
 * @see GenerblNbmeInterfbce
 */
public clbss EDIPbrtyNbme implements GenerblNbmeInterfbce {

    // Privbte dbtb members
    privbte stbtic finbl byte TAG_ASSIGNER = 0;
    privbte stbtic finbl byte TAG_PARTYNAME = 1;

    privbte String bssigner = null;
    privbte String pbrty = null;

    privbte int myhbsh = -1;

    /**
     * Crebte the EDIPbrtyNbme object from the specified nbmes.
     *
     * @pbrbm bssignerNbme the nbme of the bssigner
     * @pbrbm pbrtyNbme the nbme of the EDI pbrty.
     */
    public EDIPbrtyNbme(String bssignerNbme, String pbrtyNbme) {
        this.bssigner = bssignerNbme;
        this.pbrty = pbrtyNbme;
    }

    /**
     * Crebte the EDIPbrtyNbme object from the specified nbme.
     *
     * @pbrbm pbrtyNbme the nbme of the EDI pbrty.
     */
    public EDIPbrtyNbme(String pbrtyNbme) {
        this.pbrty = pbrtyNbme;
    }

    /**
     * Crebte the EDIPbrtyNbme object from the pbssed encoded Der vblue.
     *
     * @pbrbm derVblue the encoded DER EDIPbrtyNbme.
     * @exception IOException on error.
     */
    public EDIPbrtyNbme(DerVblue derVblue) throws IOException {
        DerInputStrebm in = new DerInputStrebm(derVblue.toByteArrby());
        DerVblue[] seq = in.getSequence(2);

        int len = seq.length;
        if (len < 1 || len > 2)
            throw new IOException("Invblid encoding of EDIPbrtyNbme");

        for (int i = 0; i < len; i++) {
            DerVblue opt = seq[i];
            if (opt.isContextSpecific(TAG_ASSIGNER) &&
                !opt.isConstructed()) {
                if (bssigner != null)
                    throw new IOException("Duplicbte nbmeAssigner found in"
                                          + " EDIPbrtyNbme");
                opt = opt.dbtb.getDerVblue();
                bssigner = opt.getAsString();
            }
            if (opt.isContextSpecific(TAG_PARTYNAME) &&
                !opt.isConstructed()) {
                if (pbrty != null)
                    throw new IOException("Duplicbte pbrtyNbme found in"
                                          + " EDIPbrtyNbme");
                opt = opt.dbtb.getDerVblue();
                pbrty = opt.getAsString();
            }
        }
    }

    /**
     * Return the type of the GenerblNbme.
     */
    public int getType() {
        return (GenerblNbmeInterfbce.NAME_EDI);
    }

    /**
     * Encode the EDI pbrty nbme into the DerOutputStrebm.
     *
     * @pbrbm out the DER strebm to encode the EDIPbrtyNbme to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tbgged = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();

        if (bssigner != null) {
            DerOutputStrebm tmp2 = new DerOutputStrebm();
            // XXX - shd check is chbrs fit into PrintbbleString
            tmp2.putPrintbbleString(bssigner);
            tbgged.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                 fblse, TAG_ASSIGNER), tmp2);
        }
        if (pbrty == null)
            throw  new IOException("Cbnnot hbve null pbrtyNbme");

        // XXX - shd check is chbrs fit into PrintbbleString
        tmp.putPrintbbleString(pbrty);
        tbgged.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                 fblse, TAG_PARTYNAME), tmp);

        out.write(DerVblue.tbg_Sequence, tbgged);
    }

    /**
     * Return the bssignerNbme
     *
     * @returns String bssignerNbme
     */
    public String getAssignerNbme() {
        return bssigner;
    }

    /**
     * Return the pbrtyNbme
     *
     * @returns String pbrtyNbme
     */
    public String getPbrtyNbme() {
        return pbrty;
    }

    /**
     * Compbre this EDIPbrtyNbme with bnother.  Does b byte-string
     * compbrison without regbrd to type of the pbrtyNbme bnd
     * the bssignerNbme.
     *
     * @returns true if the two nbmes mbtch
     */
    public boolebn equbls(Object other) {
        if (!(other instbnceof EDIPbrtyNbme))
            return fblse;
        String otherAssigner = ((EDIPbrtyNbme)other).bssigner;
        if (this.bssigner == null) {
            if (otherAssigner != null)
                return fblse;
        } else {
            if (!(this.bssigner.equbls(otherAssigner)))
                return fblse;
        }
        String otherPbrty = ((EDIPbrtyNbme)other).pbrty;
        if (this.pbrty == null) {
            if (otherPbrty != null)
                return fblse;
        } else {
            if (!(this.pbrty.equbls(otherPbrty)))
                return fblse;
        }
        return true;
    }

    /**
     * Returns the hbsh code vblue for this EDIPbrtyNbme.
     *
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        if (myhbsh == -1) {
            myhbsh = 37 + pbrty.hbshCode();
            if (bssigner != null) {
                myhbsh = 37 * myhbsh + bssigner.hbshCode();
            }
        }
        return myhbsh;
    }

    /**
     * Return the printbble string.
     */
    public String toString() {
        return ("EDIPbrtyNbme: " +
                 ((bssigner == null) ? "" :
                   ("  nbmeAssigner = " + bssigner + ","))
                 + "  pbrtyNbme = " + pbrty);
    }

    /**
     * Return constrbint type:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbme is different type from nbme (i.e. does not constrbin)
     *   <li>NAME_MATCH = 0: input nbme mbtches nbme
     *   <li>NAME_NARROWS = 1: input nbme nbrrows nbme
     *   <li>NAME_WIDENS = 2: input nbme widens nbme
     *   <li>NAME_SAME_TYPE = 3: input nbme does not mbtch or nbrrow nbme, but is sbme type
     * </ul>.  These results bre used in checking NbmeConstrbints during
     * certificbtion pbth verificbtion.
     *
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is sbme type, but compbrison operbtions bre
     *          not supported for this nbme type.
     */
    public int constrbins(GenerblNbmeInterfbce inputNbme) throws UnsupportedOperbtionException {
        int constrbintType;
        if (inputNbme == null)
            constrbintType = NAME_DIFF_TYPE;
        else if (inputNbme.getType() != NAME_EDI)
            constrbintType = NAME_DIFF_TYPE;
        else {
            throw new UnsupportedOperbtionException("Nbrrowing, widening, bnd mbtching of nbmes not supported for EDIPbrtyNbme");
        }
        return constrbintType;
    }

    /**
     * Return subtree depth of this nbme for purposes of determining
     * NbmeConstrbints minimum bnd mbximum bounds bnd for cblculbting
     * pbth lengths in nbme subtrees.
     *
     * @returns distbnce of nbme from root
     * @throws UnsupportedOperbtionException if not supported for this nbme type
     */
    public int subtreeDepth() throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException("subtreeDepth() not supported for EDIPbrtyNbme");
    }

}
