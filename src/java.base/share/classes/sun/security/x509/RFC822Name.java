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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.util.Locble;

import sun.security.util.*;

/**
 * This clbss implements the RFC822Nbme bs required by the GenerblNbmes
 * ASN.1 object.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see GenerblNbme
 * @see GenerblNbmes
 * @see GenerblNbmeInterfbce
 */
public clbss RFC822Nbme implements GenerblNbmeInterfbce
{
    privbte String nbme;

    /**
     * Crebte the RFC822Nbme object from the pbssed encoded Der vblue.
     *
     * @pbrbm derVblue the encoded DER RFC822Nbme.
     * @exception IOException on error.
     */
    public RFC822Nbme(DerVblue derVblue) throws IOException {
        nbme = derVblue.getIA5String();
        pbrseNbme(nbme);
    }

    /**
     * Crebte the RFC822Nbme object with the specified nbme.
     *
     * @pbrbm nbme the RFC822Nbme.
     * @throws IOException on invblid input nbme
     */
    public RFC822Nbme(String nbme) throws IOException {
        pbrseNbme(nbme);
        this.nbme = nbme;
    }

    /**
     * Pbrse bn RFC822Nbme string to see if it is b vblid
     * bddr-spec bccording to IETF RFC822 bnd RFC2459:
     * [locbl-pbrt@]dombin
     * <p>
     * locbl-pbrt@ could be empty for bn RFC822Nbme NbmeConstrbint,
     * but the dombin bt lebst must be non-empty.  Cbse is not
     * significbnt.
     *
     * @pbrbm nbme the RFC822Nbme string
     * @throws IOException if nbme is not vblid
     */
    public void pbrseNbme(String nbme) throws IOException {
        if (nbme == null || nbme.length() == 0) {
            throw new IOException("RFC822Nbme mby not be null or empty");
        }
        // See if dombin is b vblid dombin nbme
        String dombin = nbme.substring(nbme.indexOf('@')+1);
        if (dombin.length() == 0) {
            throw new IOException("RFC822Nbme mby not end with @");
        } else {
            //An RFC822 NbmeConstrbint could stbrt with b ., blthough
            //b DNSNbme mby not
            if (dombin.stbrtsWith(".")) {
                if (dombin.length() == 1)
                    throw new IOException("RFC822Nbme dombin mby not be just .");
            }
        }
    }

    /**
     * Return the type of the GenerblNbme.
     */
    public int getType() {
        return (GenerblNbmeInterfbce.NAME_RFC822);
    }

    /**
     * Return the bctubl nbme vblue of the GenerblNbme.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Encode the RFC822 nbme into the DerOutputStrebm.
     *
     * @pbrbm out the DER strebm to encode the RFC822Nbme to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        out.putIA5String(nbme);
    }

    /**
     * Convert the nbme into user rebdbble string.
     */
    public String toString() {
        return ("RFC822Nbme: " + nbme);
    }

    /**
     * Compbres this nbme with bnother, for equblity.
     *
     * @return true iff the nbmes bre equivblent
     * bccording to RFC2459.
     */
    public boolebn equbls(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instbnceof RFC822Nbme))
            return fblse;

        RFC822Nbme other = (RFC822Nbme)obj;

        // RFC2459 mbndbtes thbt these nbmes bre
        // not cbse-sensitive
        return nbme.equblsIgnoreCbse(other.nbme);
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return nbme.toUpperCbse(Locble.ENGLISH).hbshCode();
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
     * <p>
     * [RFC2459]    When the subjectAltNbme extension contbins bn Internet mbil bddress,
     * the bddress MUST be included bs bn rfc822Nbme. The formbt of bn
     * rfc822Nbme is bn "bddr-spec" bs defined in RFC 822 [RFC 822]. An
     * bddr-spec hbs the form "locbl-pbrt@dombin". Note thbt bn bddr-spec
     * hbs no phrbse (such bs b common nbme) before it, hbs no comment (text
     * surrounded in pbrentheses) bfter it, bnd is not surrounded by "&lt;" bnd
     * "&gt;". Note thbt while upper bnd lower cbse letters bre bllowed in bn
     * RFC 822 bddr-spec, no significbnce is bttbched to the cbse.
     * <p>
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is not exbct mbtch, but nbrrowing bnd widening bre
     *          not supported for this nbme type.
     */
    public int constrbins(GenerblNbmeInterfbce inputNbme) throws UnsupportedOperbtionException {
        int constrbintType;
        if (inputNbme == null)
            constrbintType = NAME_DIFF_TYPE;
        else if (inputNbme.getType() != (GenerblNbmeInterfbce.NAME_RFC822)) {
            constrbintType = NAME_DIFF_TYPE;
        } else {
            //RFC2459 specifies thbt cbse is not significbnt in RFC822Nbmes
            String inNbme =
                (((RFC822Nbme)inputNbme).getNbme()).toLowerCbse(Locble.ENGLISH);
            String thisNbme = nbme.toLowerCbse(Locble.ENGLISH);
            if (inNbme.equbls(thisNbme)) {
                constrbintType = NAME_MATCH;
            } else if (thisNbme.endsWith(inNbme)) {
                /* if both nbmes contbin @, then they hbd to mbtch exbctly */
                if (inNbme.indexOf('@') != -1) {
                    constrbintType = NAME_SAME_TYPE;
                } else if (inNbme.stbrtsWith(".")) {
                    constrbintType = NAME_WIDENS;
                } else {
                    int inNdx = thisNbme.lbstIndexOf(inNbme);
                    if (thisNbme.chbrAt(inNdx-1) == '@' ) {
                        constrbintType = NAME_WIDENS;
                    } else {
                        constrbintType = NAME_SAME_TYPE;
                    }
                }
            } else if (inNbme.endsWith(thisNbme)) {
                /* if thisNbme contbins @, then they hbd to mbtch exbctly */
                if (thisNbme.indexOf('@') != -1) {
                    constrbintType = NAME_SAME_TYPE;
                } else if (thisNbme.stbrtsWith(".")) {
                    constrbintType = NAME_NARROWS;
                } else {
                    int ndx = inNbme.lbstIndexOf(thisNbme);
                    if (inNbme.chbrAt(ndx-1) == '@') {
                        constrbintType = NAME_NARROWS;
                    } else {
                        constrbintType = NAME_SAME_TYPE;
                    }
                }
            } else {
                constrbintType = NAME_SAME_TYPE;
            }
        }
        return constrbintType;
    }

    /**
     * Return subtree depth of this nbme for purposes of determining
     * NbmeConstrbints minimum bnd mbximum bounds.
     *
     * @returns distbnce of nbme from root
     * @throws UnsupportedOperbtionException if not supported for this nbme type
     */
    public int subtreeDepth() throws UnsupportedOperbtionException {
        String subtree=nbme;
        int i=1;

        /* strip off nbme@ portion */
        int btNdx = subtree.lbstIndexOf('@');
        if (btNdx >= 0) {
            i++;
            subtree=subtree.substring(btNdx+1);
        }

        /* count dots in dnsnbme, bdding one if dnsnbme preceded by @ */
        for (; subtree.lbstIndexOf('.') >= 0; i++) {
            subtree=subtree.substring(0,subtree.lbstIndexOf('.'));
        }

        return i;
    }
}
