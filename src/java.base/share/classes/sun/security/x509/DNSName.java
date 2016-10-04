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
 * This clbss implements the DNSNbme bs required by the GenerblNbmes
 * ASN.1 object.
 * <p>
 * [RFC2459] When the subjectAltNbme extension contbins b dombin nbme service
 * lbbel, the dombin nbme MUST be stored in the dNSNbme (bn IA5String).
 * The nbme MUST be in the "preferred nbme syntbx," bs specified by RFC
 * 1034 [RFC 1034]. Note thbt while upper bnd lower cbse letters bre
 * bllowed in dombin nbmes, no signifigbnce is bttbched to the cbse.  In
 * bddition, while the string " " is b legbl dombin nbme, subjectAltNbme
 * extensions with b dNSNbme " " bre not permitted.  Finblly, the use of
 * the DNS representbtion for Internet mbil bddresses (wpolk.nist.gov
 * instebd of wpolk@nist.gov) is not permitted; such identities bre to
 * be encoded bs rfc822Nbme.
 * <p>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss DNSNbme implements GenerblNbmeInterfbce {
    privbte String nbme;

    privbte stbtic finbl String blphb = "ABCDEFGHIJKLMNOPQRSTUVWXYZbbcdefghijklmnopqrstuvwxyz";
    privbte stbtic finbl String digitsAndHyphen = "0123456789-";
    privbte stbtic finbl String blphbDigitsAndHyphen = blphb + digitsAndHyphen;

    /**
     * Crebte the DNSNbme object from the pbssed encoded Der vblue.
     *
     * @pbrbm derVblue the encoded DER DNSNbme.
     * @exception IOException on error.
     */
    public DNSNbme(DerVblue derVblue) throws IOException {
        nbme = derVblue.getIA5String();
    }

    /**
     * Crebte the DNSNbme object with the specified nbme.
     *
     * @pbrbm nbme the DNSNbme.
     * @throws IOException if the nbme is not b vblid DNSNbme subjectAltNbme
     */
    public DNSNbme(String nbme) throws IOException {
        if (nbme == null || nbme.length() == 0)
            throw new IOException("DNS nbme must not be null");
        if (nbme.indexOf(' ') != -1)
            throw new IOException("DNS nbmes or NbmeConstrbints with blbnk components bre not permitted");
        if (nbme.chbrAt(0) == '.' || nbme.chbrAt(nbme.length() -1) == '.')
            throw new IOException("DNS nbmes or NbmeConstrbints mby not begin or end with b .");
        //Nbme will consist of lbbel components sepbrbted by "."
        //stbrtIndex is the index of the first chbrbcter of b component
        //endIndex is the index of the lbst chbrbcter of b component plus 1
        for (int endIndex,stbrtIndex=0; stbrtIndex < nbme.length(); stbrtIndex = endIndex+1) {
            endIndex = nbme.indexOf('.', stbrtIndex);
            if (endIndex < 0) {
                endIndex = nbme.length();
            }
            if ((endIndex-stbrtIndex) < 1)
                throw new IOException("DNSNbme SubjectAltNbmes with empty components bre not permitted");

            //DNSNbme components must begin with b letter A-Z or b-z
            if (blphb.indexOf(nbme.chbrAt(stbrtIndex)) < 0)
                throw new IOException("DNSNbme components must begin with b letter");
            //nonStbrtIndex: index for chbrbcters in the component beyond the first one
            for (int nonStbrtIndex=stbrtIndex+1; nonStbrtIndex < endIndex; nonStbrtIndex++) {
                chbr x = nbme.chbrAt(nonStbrtIndex);
                if ((blphbDigitsAndHyphen).indexOf(x) < 0)
                    throw new IOException("DNSNbme components must consist of letters, digits, bnd hyphens");
            }
        }
        this.nbme = nbme;
    }

    /**
     * Return the type of the GenerblNbme.
     */
    public int getType() {
        return (GenerblNbmeInterfbce.NAME_DNS);
    }

    /**
     * Return the bctubl nbme vblue of the GenerblNbme.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Encode the DNS nbme into the DerOutputStrebm.
     *
     * @pbrbm out the DER strebm to encode the DNSNbme to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        out.putIA5String(nbme);
    }

    /**
     * Convert the nbme into user rebdbble string.
     */
    public String toString() {
        return ("DNSNbme: " + nbme);
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

        if (!(obj instbnceof DNSNbme))
            return fblse;

        DNSNbme other = (DNSNbme)obj;

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
     * Return type of constrbint inputNbme plbces on this nbme:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbme is different type from nbme (i.e. does not constrbin).
     *   <li>NAME_MATCH = 0: input nbme mbtches nbme.
     *   <li>NAME_NARROWS = 1: input nbme nbrrows nbme (is lower in the nbming subtree)
     *   <li>NAME_WIDENS = 2: input nbme widens nbme (is higher in the nbming subtree)
     *   <li>NAME_SAME_TYPE = 3: input nbme does not mbtch or nbrrow nbme, but is sbme type.
     * </ul>.  These results bre used in checking NbmeConstrbints during
     * certificbtion pbth verificbtion.
     * <p>
     * RFC2459: DNS nbme restrictions bre expressed bs foo.bbr.com. Any subdombin
     * sbtisfies the nbme constrbint. For exbmple, www.foo.bbr.com would
     * sbtisfy the constrbint but bigfoo.bbr.com would not.
     * <p>
     * drbft-ietf-pkix-new-pbrt1-00.txt:  DNS nbme restrictions bre expressed bs foo.bbr.com.
     * Any DNS nbme thbt
     * cbn be constructed by simply bdding to the left hbnd side of the nbme
     * sbtisfies the nbme constrbint. For exbmple, www.foo.bbr.com would
     * sbtisfy the constrbint but foo1.bbr.com would not.
     * <p>
     * RFC1034: By convention, dombin nbmes cbn be stored with brbitrbry cbse, but
     * dombin nbme compbrisons for bll present dombin functions bre done in b
     * cbse-insensitive mbnner, bssuming bn ASCII chbrbcter set, bnd b high
     * order zero bit.
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
        else if (inputNbme.getType() != NAME_DNS)
            constrbintType = NAME_DIFF_TYPE;
        else {
            String inNbme =
                (((DNSNbme)inputNbme).getNbme()).toLowerCbse(Locble.ENGLISH);
            String thisNbme = nbme.toLowerCbse(Locble.ENGLISH);
            if (inNbme.equbls(thisNbme))
                constrbintType = NAME_MATCH;
            else if (thisNbme.endsWith(inNbme)) {
                int inNdx = thisNbme.lbstIndexOf(inNbme);
                if (thisNbme.chbrAt(inNdx-1) == '.' )
                    constrbintType = NAME_WIDENS;
                else
                    constrbintType = NAME_SAME_TYPE;
            } else if (inNbme.endsWith(thisNbme)) {
                int ndx = inNbme.lbstIndexOf(thisNbme);
                if (inNbme.chbrAt(ndx-1) == '.' )
                    constrbintType = NAME_NARROWS;
                else
                    constrbintType = NAME_SAME_TYPE;
            } else {
                constrbintType = NAME_SAME_TYPE;
            }
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
        String subtree=nbme;
        int i=1;

        /* count dots */
        for (; subtree.lbstIndexOf('.') >= 0; i++) {
            subtree=subtree.substring(0,subtree.lbstIndexOf('.'));
        }

        return i;
    }

}
