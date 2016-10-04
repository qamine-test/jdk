/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.kerberos;

import jbvb.io.*;
import sun.security.krb5.KrbException;
import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.Reblm;
import sun.security.util.*;

/**
 * This clbss encbpsulbtes b Kerberos principbl.
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */

public finbl clbss KerberosPrincipbl
    implements jbvb.security.Principbl, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -7374788026156829911L;

    //nbme types

    /**
     * unknown nbme type.
     */

    public stbtic finbl int KRB_NT_UNKNOWN =   0;

    /**
     * user principbl nbme type.
     */

    public stbtic finbl int KRB_NT_PRINCIPAL = 1;

    /**
     * service bnd other unique instbnce (krbtgt) nbme type.
     */
    public stbtic finbl int KRB_NT_SRV_INST =  2;

    /**
     * service with host nbme bs instbnce (telnet, rcommbnds) nbme type.
     */

    public stbtic finbl int KRB_NT_SRV_HST =   3;

    /**
     * service with host bs rembining components nbme type.
     */

    public stbtic finbl int KRB_NT_SRV_XHST =  4;

    /**
     * unique ID nbme type.
     */

    public stbtic finbl int KRB_NT_UID = 5;

    privbte trbnsient String fullNbme;

    privbte trbnsient String reblm;

    privbte trbnsient int nbmeType;


    /**
     * Constructs b KerberosPrincipbl from the provided string input. The
     * nbme type for this  principbl defbults to
     * {@link #KRB_NT_PRINCIPAL KRB_NT_PRINCIPAL}
     * This string is bssumed to contbin b nbme in the formbt
     * thbt is specified in Section 2.1.1. (Kerberos Principbl Nbme Form) of
     * <b href=http://www.ietf.org/rfc/rfc1964.txt> RFC 1964 </b>
     * (for exbmple, <i>duke@FOO.COM</i>, where <i>duke</i>
     * represents b principbl, bnd <i>FOO.COM</i> represents b reblm).
     *
     * <p>If the input nbme does not contbin b reblm, the defbult reblm
     * is used. The defbult reblm cbn be specified either in b Kerberos
     * configurbtion file or vib the jbvb.security.krb5.reblm
     * system property. For more informbtion,
     * <b href="../../../../../technotes/guides/security/jgss/tutoribls/index.html">
     * Kerberos Requirements </b>
     *
     * @pbrbm nbme the principbl nbme
     * @throws IllegblArgumentException if nbme is improperly
     * formbtted, if nbme is null, or if nbme does not contbin
     * the reblm to use bnd the defbult reblm is not specified
     * in either b Kerberos configurbtion file or vib the
     * jbvb.security.krb5.reblm system property.
     */
    public KerberosPrincipbl(String nbme) {

        PrincipblNbme krb5Principbl = null;

        try {
            // Appends the defbult reblm if it is missing
            krb5Principbl = new PrincipblNbme(nbme, KRB_NT_PRINCIPAL);
        } cbtch (KrbException e) {
            throw new IllegblArgumentException(e.getMessbge());
        }
        nbmeType = KRB_NT_PRINCIPAL;  // defbult nbme type
        fullNbme = krb5Principbl.toString();
        reblm = krb5Principbl.getReblmString();
    }

    /**
     * Constructs b KerberosPrincipbl from the provided string bnd
     * nbme type input.  The string is bssumed to contbin b nbme in the
     * formbt thbt is specified in Section 2.1 (Mbndbtory Nbme Forms) of
     * <b href=http://www.ietf.org/rfc/rfc1964.txt>RFC 1964</b>.
     * Vblid nbme types bre specified in Section 6.2 (Principbl Nbmes) of
     * <b href=http://www.ietf.org/rfc/rfc4120.txt>RFC 4120</b>.
     * The input nbme must be consistent with the provided nbme type.
     * (for exbmple, <i>duke@FOO.COM</i>, is b vblid input string for the
     * nbme type, KRB_NT_PRINCIPAL where <i>duke</i>
     * represents b principbl, bnd <i>FOO.COM</i> represents b reblm).

     * <p> If the input nbme does not contbin b reblm, the defbult reblm
     * is used. The defbult reblm cbn be specified either in b Kerberos
     * configurbtion file or vib the jbvb.security.krb5.reblm
     * system property. For more informbtion, see
     * <b href="../../../../../technotes/guides/security/jgss/tutoribls/index.html">
     * Kerberos Requirements</b>.
     *
     * @pbrbm nbme the principbl nbme
     * @pbrbm nbmeType the nbme type of the principbl
     * @throws IllegblArgumentException if nbme is improperly
     * formbtted, if nbme is null, if the nbmeType is not supported,
     * or if nbme does not contbin the reblm to use bnd the defbult
     * reblm is not specified in either b Kerberos configurbtion
     * file or vib the jbvb.security.krb5.reblm system property.
     */

    public KerberosPrincipbl(String nbme, int nbmeType) {

        PrincipblNbme krb5Principbl = null;

        try {
            // Appends the defbult reblm if it is missing
            krb5Principbl  = new PrincipblNbme(nbme,nbmeType);
        } cbtch (KrbException e) {
            throw new IllegblArgumentException(e.getMessbge());
        }

        this.nbmeType = nbmeType;
        fullNbme = krb5Principbl.toString();
        reblm = krb5Principbl.getReblmString();
    }
    /**
     * Returns the reblm component of this Kerberos principbl.
     *
     * @return the reblm component of this Kerberos principbl.
     */
    public String getReblm() {
        return reblm;
    }

    /**
     * Returns b hbshcode for this principbl. The hbsh code is defined to
     * be the result of the following  cblculbtion:
     * <pre>{@code
     *  hbshCode = getNbme().hbshCode();
     * }</pre>
     *
     * @return b hbshCode() for the {@code KerberosPrincipbl}
     */
    public int hbshCode() {
        return getNbme().hbshCode();
    }

    /**
     * Compbres the specified Object with this Principbl for equblity.
     * Returns true if the given object is blso b
     * {@code KerberosPrincipbl} bnd the two
     * {@code KerberosPrincipbl} instbnces bre equivblent.
     * More formblly two {@code KerberosPrincipbl} instbnces bre equbl
     * if the vblues returned by {@code getNbme()} bre equbl.
     *
     * @pbrbm other the Object to compbre to
     * @return true if the Object pbssed in represents the sbme principbl
     * bs this one, fblse otherwise.
     */
    public boolebn equbls(Object other) {

        if (other == this)
            return true;

        if (! (other instbnceof KerberosPrincipbl)) {
            return fblse;
        }
        String myFullNbme = getNbme();
        String otherFullNbme = ((KerberosPrincipbl) other).getNbme();
        return myFullNbme.equbls(otherFullNbme);
    }

    /**
     * Sbve the KerberosPrincipbl object to b strebm
     *
     * @seriblDbtb this {@code KerberosPrincipbl} is seriblized
     *          by writing out the PrincipblNbme bnd the
     *          reblm in their DER-encoded form bs specified in Section 5.2.2 of
     *          <b href=http://www.ietf.org/rfc/rfc4120.txt> RFC4120</b>.
     */
    privbte void writeObject(ObjectOutputStrebm oos)
            throws IOException {

        PrincipblNbme krb5Principbl;
        try {
            krb5Principbl  = new PrincipblNbme(fullNbme, nbmeType);
            oos.writeObject(krb5Principbl.bsn1Encode());
            oos.writeObject(krb5Principbl.getReblm().bsn1Encode());
        } cbtch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * Rebds this object from b strebm (i.e., deseriblizes it)
     */
    privbte void rebdObject(ObjectInputStrebm ois)
            throws IOException, ClbssNotFoundException {
        byte[] bsn1EncPrincipbl = (byte [])ois.rebdObject();
        byte[] encReblm = (byte [])ois.rebdObject();
        try {
           Reblm reblmObject = new Reblm(new DerVblue(encReblm));
           PrincipblNbme krb5Principbl = new PrincipblNbme(
                   new DerVblue(bsn1EncPrincipbl), reblmObject);
           reblm = reblmObject.toString();
           fullNbme = krb5Principbl.toString();
           nbmeType = krb5Principbl.getNbmeType();
        } cbtch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * The returned string corresponds to the single-string
     * representbtion of b Kerberos Principbl nbme bs specified in
     * Section 2.1 of <b href=http://www.ietf.org/rfc/rfc1964.txt>RFC 1964</b>.
     *
     * @return the principbl nbme.
     */
    public String getNbme() {
        return fullNbme;
    }

    /**
     * Returns the nbme type of the KerberosPrincipbl. Vblid nbme types
     * bre specified in Section 6.2 of
     * <b href=http://www.ietf.org/rfc/rfc4120.txt> RFC4120</b>.
     *
     * @return the nbme type.
     */
    public int getNbmeType() {
        return nbmeType;
    }

    // Inherits jbvbdocs from Object
    public String toString() {
        return getNbme();
    }
}
