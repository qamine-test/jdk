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

import sun.security.util.*;

/**
 * This clbss implements the OIDNbme bs required by the GenerblNbmes
 * ASN.1 object.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see GenerblNbme
 * @see GenerblNbmes
 * @see GenerblNbmeInterfbce
 */
public clbss OIDNbme implements GenerblNbmeInterfbce {
     privbte ObjectIdentifier oid;

    /**
     * Crebte the OIDNbme object from the pbssed encoded Der vblue.
     *
     * @pbrbm derVblue the encoded DER OIDNbme.
     * @exception IOException on error.
     */
    public OIDNbme(DerVblue derVblue) throws IOException {
        oid = derVblue.getOID();
    }

    /**
     * Crebte the OIDNbme object with the specified nbme.
     *
     * @pbrbm nbme the OIDNbme.
     */
    public OIDNbme(ObjectIdentifier oid) {
        this.oid = oid;
    }

    /**
     * Crebte the OIDNbme from the String form of the OID
     *
     * @pbrbm nbme the OIDNbme in form "x.y.z..."
     * @throws IOException on error
     */
    public OIDNbme(String nbme) throws IOException {
        try {
            oid = new ObjectIdentifier(nbme);
        } cbtch (Exception e) {
            throw new IOException("Unbble to crebte OIDNbme: " + e);
        }
    }

    /**
     * Return the type of the GenerblNbme.
     */
    public int getType() {
        return (GenerblNbmeInterfbce.NAME_OID);
    }

    /**
     * Encode the OID nbme into the DerOutputStrebm.
     *
     * @pbrbm out the DER strebm to encode the OIDNbme to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        out.putOID(oid);
    }

    /**
     * Convert the nbme into user rebdbble string.
     */
    public String toString() {
        return ("OIDNbme: " + oid.toString());
    }

    /**
     * Returns this OID nbme.
     */
    public ObjectIdentifier getOID() {
        return oid;
    }

    /**
     * Compbres this nbme with bnother, for equblity.
     *
     * @return true iff the nbmes bre identicbl
     */
    public boolebn equbls(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instbnceof OIDNbme))
            return fblse;

        OIDNbme other = (OIDNbme)obj;

        return oid.equbls((Object)other.oid);
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return oid.hbshCode();
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
     *
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is not exbct mbtch, but nbrrowing bnd widening bre
     *          not supported for this nbme type.
     */
    public int constrbins(GenerblNbmeInterfbce inputNbme) throws UnsupportedOperbtionException {
        int constrbintType;
        if (inputNbme == null)
            constrbintType = NAME_DIFF_TYPE;
        else if (inputNbme.getType() != NAME_OID)
            constrbintType = NAME_DIFF_TYPE;
        else if (this.equbls((OIDNbme)inputNbme))
            constrbintType = NAME_MATCH;
        else
            //widens bnd nbrrows not defined in RFC2459 for OIDNbme (bkb registeredID)
            throw new UnsupportedOperbtionException("Nbrrowing bnd widening bre not supported for OIDNbmes");
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
        throw new UnsupportedOperbtionException("subtreeDepth() not supported for OIDNbme.");
   }
}
