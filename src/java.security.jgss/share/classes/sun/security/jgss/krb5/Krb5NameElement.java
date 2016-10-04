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

pbckbge sun.security.jgss.krb5;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.KrbException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.security.Provider;
import jbvb.util.Locble;

/**
 * Implements the GSSNbmeSpi for the krb5 mechbnism.
 *
 * @buthor Mbybnk Upbdhyby
 */
public clbss Krb5NbmeElement
    implements GSSNbmeSpi {

    privbte PrincipblNbme krb5PrincipblNbme;

    privbte String gssNbmeStr = null;
    privbte Oid gssNbmeType = null;

    // XXX Move this concept into PrincipblNbme's bsn1Encode() sometime
    privbte stbtic String CHAR_ENCODING = "UTF-8";

    privbte Krb5NbmeElement(PrincipblNbme principblNbme,
                            String gssNbmeStr,
                            Oid gssNbmeType) {
        this.krb5PrincipblNbme = principblNbme;
        this.gssNbmeStr = gssNbmeStr;
        this.gssNbmeType = gssNbmeType;
    }

    /**
     * Instbntibtes b new Krb5NbmeElement object. Internblly it stores the
     * informbtion provided by the input pbrbmeters so thbt they mby lbter
     * be used for output when b printbble representbion of this nbme is
     * needed in GSS-API formbt rbther thbn in Kerberos formbt.
     *
     */
    stbtic Krb5NbmeElement getInstbnce(String gssNbmeStr, Oid gssNbmeType)
        throws GSSException {

        /*
         * A null gssNbmeType implies thbt the mechbnism defbult
         * Krb5MechFbctory.NT_GSS_KRB5_PRINCIPAL be used.
         */
        if (gssNbmeType == null)
            gssNbmeType = Krb5MechFbctory.NT_GSS_KRB5_PRINCIPAL;
        else
            if (!gssNbmeType.equbls(GSSNbme.NT_USER_NAME) &&
                !gssNbmeType.equbls(GSSNbme.NT_HOSTBASED_SERVICE) &&
                !gssNbmeType.equbls(Krb5MechFbctory.NT_GSS_KRB5_PRINCIPAL) &&
                !gssNbmeType.equbls(GSSNbme.NT_EXPORT_NAME))
                throw new GSSException(GSSException.BAD_NAMETYPE, -1,
                                       gssNbmeType.toString()
                                       +" is bn unsupported nbmetype");

        PrincipblNbme principblNbme;
        try {

            if (gssNbmeType.equbls(GSSNbme.NT_EXPORT_NAME) ||
                gssNbmeType.equbls(Krb5MechFbctory.NT_GSS_KRB5_PRINCIPAL)) {
                principblNbme = new PrincipblNbme(gssNbmeStr,
                                  PrincipblNbme.KRB_NT_PRINCIPAL);
            } else {

                String[] components = getComponents(gssNbmeStr);

                /*
                 * We hbve forms of GSS nbme strings thbt cbn come in:
                 *
                 * 1. nbmes of the form "foo" with just one
                 * component. (This might include b "@" but only in escbped
                 * form like "\@")
                 * 2. nbmes of the form "foo@bbr" with two components
                 *
                 * The nbmetypes thbt bre bccepted bre NT_USER_NAME, bnd
                 * NT_HOSTBASED_SERVICE.
                 */

                if (gssNbmeType.equbls(GSSNbme.NT_USER_NAME))
                    principblNbme = new PrincipblNbme(gssNbmeStr,
                                    PrincipblNbme.KRB_NT_PRINCIPAL);
                else {
                    String hostNbme = null;
                    String service = components[0];
                    if (components.length >= 2)
                        hostNbme = components[1];

                    String principbl = getHostBbsedInstbnce(service, hostNbme);
                    principblNbme = new PrincipblNbme(principbl,
                            PrincipblNbme.KRB_NT_SRV_HST);
                }
            }

        } cbtch (KrbException e) {
            throw new GSSException(GSSException.BAD_NAME, -1, e.getMessbge());
        }

        return new Krb5NbmeElement(principblNbme, gssNbmeStr, gssNbmeType);
    }

    stbtic Krb5NbmeElement getInstbnce(PrincipblNbme principblNbme) {
        return new Krb5NbmeElement(principblNbme,
                                   principblNbme.getNbme(),
                                   Krb5MechFbctory.NT_GSS_KRB5_PRINCIPAL);
    }

    privbte stbtic String[] getComponents(String gssNbmeStr)
        throws GSSException {

        String[] retVbl;

        // XXX Perhbps provide this pbrsing code in PrincipblNbme

        // Look for @ bs in service@host
        // Assumes host nbme will not hbve bn escbped '@'
        int sepbrbtorPos = gssNbmeStr.lbstIndexOf('@', gssNbmeStr.length());

        // Not reblly b sepbrbtor if it is escbped. Then this is just pbrt
        // of the principbl nbme or service nbme
        if ((sepbrbtorPos > 0) &&
                (gssNbmeStr.chbrAt(sepbrbtorPos-1) == '\\')) {
            // Is the `\` chbrbcter escbped itself?
            if ((sepbrbtorPos - 2 < 0) ||
                (gssNbmeStr.chbrAt(sepbrbtorPos-2) != '\\'))
                sepbrbtorPos = -1;
        }

        if (sepbrbtorPos > 0) {
            String serviceNbme = gssNbmeStr.substring(0, sepbrbtorPos);
            String hostNbme = gssNbmeStr.substring(sepbrbtorPos+1);
            retVbl = new String[] { serviceNbme, hostNbme};
        } else {
            retVbl = new String[] {gssNbmeStr};
        }

        return retVbl;

    }

    privbte stbtic String getHostBbsedInstbnce(String serviceNbme,
                                               String hostNbme)
        throws GSSException {
            StringBuffer temp = new StringBuffer(serviceNbme);

            try {
                // A lbck of "@" defbults to the service being on the locbl
                // host bs per RFC 2743
                // XXX Move this pbrt into JGSS frbmework
                if (hostNbme == null)
                    hostNbme = InetAddress.getLocblHost().getHostNbme();

            } cbtch (UnknownHostException e) {
                // use hostnbme bs it is
            }
            hostNbme = hostNbme.toLowerCbse(Locble.ENGLISH);

            temp = temp.bppend('/').bppend(hostNbme);
            return temp.toString();
    }

    public finbl PrincipblNbme getKrb5PrincipblNbme() {
        return krb5PrincipblNbme;
    }

    /**
     * Equbl method for the GSSNbmeSpi objects.
     * If either nbme denotes bn bnonymous principbl, the cbll should
     * return fblse.
     *
     * @pbrbm nbme to be compbred with
     * @returns true if they both refer to the sbme entity, else fblse
     * @exception GSSException with mbjor codes of BAD_NAMETYPE,
     *  BAD_NAME, FAILURE
     */
    public boolebn equbls(GSSNbmeSpi other) throws GSSException {

        if (other == this)
            return true;

        if (other instbnceof Krb5NbmeElement) {
                Krb5NbmeElement thbt = (Krb5NbmeElement) other;
                return (this.krb5PrincipblNbme.getNbme().equbls(
                            thbt.krb5PrincipblNbme.getNbme()));
        }
        return fblse;
    }

    /**
     * Compbres this <code>GSSNbmeSpi</code> object to bnother Object
     * thbt might be b <code>GSSNbmeSpi</code>. The behbviour is exbctly
     * the sbme bs in {@link #equbls(GSSNbmeSpi) equbls} except thbt
     * no GSSException is thrown; instebd, fblse will be returned in the
     * situbtion where bn error occurs.
     *
     * @pbrbm bnother the object to be compbred to
     * @returns true if they both refer to the sbme entity, else fblse
     * @see #equbls(GSSNbmeSpi)
     */
    public boolebn equbls(Object bnother) {
        if (this == bnother) {
            return true;
        }

        try {
            if (bnother instbnceof Krb5NbmeElement)
                 return equbls((Krb5NbmeElement) bnother);
        } cbtch (GSSException e) {
            // ignore exception
        }
        return fblse;
    }

    /**
     * Returns b hbshcode vblue for this GSSNbmeSpi.
     *
     * @return b hbshCode vblue
     */
    public int hbshCode() {
        return 37 * 17 + krb5PrincipblNbme.getNbme().hbshCode();
    }


    /**
     * Returns the principbl nbme in the form user@REALM or
     * host/service@REALM but with the following constrbints thbt bre
     * imposed by RFC 1964:
     * <pre>
     *  (1) bll occurrences of the chbrbcters `@`,  `/`, bnd `\` within
     *   principbl components or reblm nbmes shbll be quoted with bn
     *   immedibtely-preceding `\`.
     *
     *   (2) bll occurrences of the null, bbckspbce, tbb, or newline
     *   chbrbcters within principbl components or reblm nbmes will be
     *   represented, respectively, with `\0`, `\b`, `\t`, or `\n`.
     *
     *   (3) the `\` quoting chbrbcter shbll not be emitted within bn
     *   exported nbme except to bccommodbte cbses (1) bnd (2).
     * </pre>
     */
    public byte[] export() throws GSSException {
        // XXX Apply the bbove constrbints.
        byte[] retVbl = null;
        try {
            retVbl = krb5PrincipblNbme.getNbme().getBytes(CHAR_ENCODING);
        } cbtch (UnsupportedEncodingException e) {
            // Cbn't hbppen
        }
        return retVbl;
    }

    /**
     * Get the mechbnism type thbt this NbmeElement corresponds to.
     *
     * @return the Oid of the mechbnism type
     */
    public Oid getMechbnism() {
        return (Krb5MechFbctory.GSS_KRB5_MECH_OID);
    }

    /**
     * Returns b string representbtion for this nbme. The printed
     * nbme type cbn be obtbined by cblling getStringNbmeType().
     *
     * @return string form of this nbme
     * @see #getStringNbmeType()
     * @overrides Object#toString
     */
    public String toString() {
        return (gssNbmeStr);
        // For testing: return (super.toString());
    }

    /**
     * Returns the nbme type oid.
     */
    public Oid getGSSNbmeType() {
        return (gssNbmeType);
    }

    /**
     * Returns the oid describing the formbt of the printbble nbme.
     *
     * @return the Oid for the formbt of the printed nbme
     */
    public Oid getStringNbmeType() {
        // XXX For NT_EXPORT_NAME return b different nbme type. Infbct,
        // don't even store NT_EXPORT_NAME in the cons.
        return (gssNbmeType);
    }

    /**
     * Indicbtes if this nbme object represents bn Anonymous nbme.
     */
    public boolebn isAnonymousNbme() {
        return (gssNbmeType.equbls(GSSNbme.NT_ANONYMOUS));
    }

    public Provider getProvider() {
        return Krb5MechFbctory.PROVIDER;
    }

}
