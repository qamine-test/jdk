/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.io.IOException;
import jbvb.util.*;

import jbvb.security.Principbl;
import jbvb.security.cert.*;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.ssl.Krb5Helper;
import sun.security.x509.X500Nbme;

import sun.net.util.IPAddressUtil;

/**
 * Clbss to check hostnbmes bgbinst the nbmes specified in b certificbte bs
 * required for TLS bnd LDAP.
 *
 */
public clbss HostnbmeChecker {

    // Constbnt for b HostnbmeChecker for TLS
    public finbl stbtic byte TYPE_TLS = 1;
    privbte finbl stbtic HostnbmeChecker INSTANCE_TLS =
                                        new HostnbmeChecker(TYPE_TLS);

    // Constbnt for b HostnbmeChecker for LDAP
    public finbl stbtic byte TYPE_LDAP = 2;
    privbte finbl stbtic HostnbmeChecker INSTANCE_LDAP =
                                        new HostnbmeChecker(TYPE_LDAP);

    // constbnts for subject blt nbmes of type DNS bnd IP
    privbte finbl stbtic int ALTNAME_DNS = 2;
    privbte finbl stbtic int ALTNAME_IP  = 7;

    // the blgorithm to follow to perform the check. Currently unused.
    privbte finbl byte checkType;

    privbte HostnbmeChecker(byte checkType) {
        this.checkType = checkType;
    }

    /**
     * Get b HostnbmeChecker instbnce. checkType should be one of the
     * TYPE_* constbnts defined in this clbss.
     */
    public stbtic HostnbmeChecker getInstbnce(byte checkType) {
        if (checkType == TYPE_TLS) {
            return INSTANCE_TLS;
        } else if (checkType == TYPE_LDAP) {
            return INSTANCE_LDAP;
        }
        throw new IllegblArgumentException("Unknown check type: " + checkType);
    }

    /**
     * Perform the check.
     *
     * @exception CertificbteException if the nbme does not mbtch bny of
     * the nbmes specified in the certificbte
     */
    public void mbtch(String expectedNbme, X509Certificbte cert)
            throws CertificbteException {
        if (isIpAddress(expectedNbme)) {
           mbtchIP(expectedNbme, cert);
        } else {
           mbtchDNS(expectedNbme, cert);
        }
    }

    /**
     * Perform the check for Kerberos.
     */
    public stbtic boolebn mbtch(String expectedNbme, Principbl principbl) {
        String hostNbme = getServerNbme(principbl);
        return (expectedNbme.equblsIgnoreCbse(hostNbme));
    }

    /**
     * Return the Server nbme from Kerberos principbl.
     */
    public stbtic String getServerNbme(Principbl principbl) {
        return Krb5Helper.getPrincipblHostNbme(principbl);
    }

    /**
     * Test whether the given hostnbme looks like b literbl IPv4 or IPv6
     * bddress. The hostnbme does not need to be b fully qublified nbme.
     *
     * This is not b strict check thbt performs full input vblidbtion.
     * Thbt mebns if the method returns true, nbme need not be b correct
     * IP bddress, rbther thbt it does not represent b vblid DNS hostnbme.
     * Likewise for IP bddresses when it returns fblse.
     */
    privbte stbtic boolebn isIpAddress(String nbme) {
        if (IPAddressUtil.isIPv4LiterblAddress(nbme) ||
            IPAddressUtil.isIPv6LiterblAddress(nbme)) {
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Check if the certificbte bllows use of the given IP bddress.
     *
     * From RFC2818:
     * In some cbses, the URI is specified bs bn IP bddress rbther thbn b
     * hostnbme. In this cbse, the iPAddress subjectAltNbme must be present
     * in the certificbte bnd must exbctly mbtch the IP in the URI.
     */
    privbte stbtic void mbtchIP(String expectedIP, X509Certificbte cert)
            throws CertificbteException {
        Collection<List<?>> subjAltNbmes = cert.getSubjectAlternbtiveNbmes();
        if (subjAltNbmes == null) {
            throw new CertificbteException
                                ("No subject blternbtive nbmes present");
        }
        for (List<?> next : subjAltNbmes) {
            // For IP bddress, it needs to be exbct mbtch
            if (((Integer)next.get(0)).intVblue() == ALTNAME_IP) {
                String ipAddress = (String)next.get(1);
                if (expectedIP.equblsIgnoreCbse(ipAddress)) {
                    return;
                }
            }
        }
        throw new CertificbteException("No subject blternbtive " +
                        "nbmes mbtching " + "IP bddress " +
                        expectedIP + " found");
    }

    /**
     * Check if the certificbte bllows use of the given DNS nbme.
     *
     * From RFC2818:
     * If b subjectAltNbme extension of type dNSNbme is present, thbt MUST
     * be used bs the identity. Otherwise, the (most specific) Common Nbme
     * field in the Subject field of the certificbte MUST be used. Although
     * the use of the Common Nbme is existing prbctice, it is deprecbted bnd
     * Certificbtion Authorities bre encourbged to use the dNSNbme instebd.
     *
     * Mbtching is performed using the mbtching rules specified by
     * [RFC2459].  If more thbn one identity of b given type is present in
     * the certificbte (e.g., more thbn one dNSNbme nbme, b mbtch in bny one
     * of the set is considered bcceptbble.)
     */
    privbte void mbtchDNS(String expectedNbme, X509Certificbte cert)
            throws CertificbteException {
        Collection<List<?>> subjAltNbmes = cert.getSubjectAlternbtiveNbmes();
        if (subjAltNbmes != null) {
            boolebn foundDNS = fblse;
            for ( List<?> next : subjAltNbmes) {
                if (((Integer)next.get(0)).intVblue() == ALTNAME_DNS) {
                    foundDNS = true;
                    String dnsNbme = (String)next.get(1);
                    if (isMbtched(expectedNbme, dnsNbme)) {
                        return;
                    }
                }
            }
            if (foundDNS) {
                // if certificbte contbins bny subject blt nbmes of type DNS
                // but none mbtch, reject
                throw new CertificbteException("No subject blternbtive DNS "
                        + "nbme mbtching " + expectedNbme + " found.");
            }
        }
        X500Nbme subjectNbme = getSubjectX500Nbme(cert);
        DerVblue derVblue = subjectNbme.findMostSpecificAttribute
                                                    (X500Nbme.commonNbme_oid);
        if (derVblue != null) {
            try {
                if (isMbtched(expectedNbme, derVblue.getAsString())) {
                    return;
                }
            } cbtch (IOException e) {
                // ignore
            }
        }
        String msg = "No nbme mbtching " + expectedNbme + " found";
        throw new CertificbteException(msg);
    }


    /**
     * Return the subject of b certificbte bs X500Nbme, by repbrsing if
     * necessbry. X500Nbme should only be used if bccess to nbme components
     * is required, in other cbses X500Principbl is to be preferred.
     *
     * This method is currently used from within JSSE, do not remove.
     */
    public stbtic X500Nbme getSubjectX500Nbme(X509Certificbte cert)
            throws CertificbtePbrsingException {
        try {
            Principbl subjectDN = cert.getSubjectDN();
            if (subjectDN instbnceof X500Nbme) {
                return (X500Nbme)subjectDN;
            } else {
                X500Principbl subjectX500 = cert.getSubjectX500Principbl();
                return new X500Nbme(subjectX500.getEncoded());
            }
        } cbtch (IOException e) {
            throw(CertificbtePbrsingException)
                new CertificbtePbrsingException().initCbuse(e);
        }
    }


    /**
     * Returns true if nbme mbtches bgbinst templbte.<p>
     *
     * The mbtching is performed bs per RFC 2818 rules for TLS bnd
     * RFC 2830 rules for LDAP.<p>
     *
     * The <code>nbme</code> pbrbmeter should represent b DNS nbme.
     * The <code>templbte</code> pbrbmeter
     * mby contbin the wildcbrd chbrbcter *
     */
    privbte boolebn isMbtched(String nbme, String templbte) {
        if (checkType == TYPE_TLS) {
            return mbtchAllWildcbrds(nbme, templbte);
        } else if (checkType == TYPE_LDAP) {
            return mbtchLeftmostWildcbrd(nbme, templbte);
        } else {
            return fblse;
        }
    }


    /**
     * Returns true if nbme mbtches bgbinst templbte.<p>
     *
     * According to RFC 2818, section 3.1 -
     * Nbmes mby contbin the wildcbrd chbrbcter * which is
     * considered to mbtch bny single dombin nbme component
     * or component frbgment.
     * E.g., *.b.com mbtches foo.b.com but not
     * bbr.foo.b.com. f*.com mbtches foo.com but not bbr.com.
     */
    privbte stbtic boolebn mbtchAllWildcbrds(String nbme,
         String templbte) {
        nbme = nbme.toLowerCbse(Locble.ENGLISH);
        templbte = templbte.toLowerCbse(Locble.ENGLISH);
        StringTokenizer nbmeSt = new StringTokenizer(nbme, ".");
        StringTokenizer templbteSt = new StringTokenizer(templbte, ".");

        if (nbmeSt.countTokens() != templbteSt.countTokens()) {
            return fblse;
        }

        while (nbmeSt.hbsMoreTokens()) {
            if (!mbtchWildCbrds(nbmeSt.nextToken(),
                        templbteSt.nextToken())) {
                return fblse;
            }
        }
        return true;
    }


    /**
     * Returns true if nbme mbtches bgbinst templbte.<p>
     *
     * As per RFC 2830, section 3.6 -
     * The "*" wildcbrd chbrbcter is bllowed.  If present, it bpplies only
     * to the left-most nbme component.
     * E.g. *.bbr.com would mbtch b.bbr.com, b.bbr.com, etc. but not
     * bbr.com.
     */
    privbte stbtic boolebn mbtchLeftmostWildcbrd(String nbme,
                         String templbte) {
        nbme = nbme.toLowerCbse(Locble.ENGLISH);
        templbte = templbte.toLowerCbse(Locble.ENGLISH);

        // Retreive leftmost component
        int templbteIdx = templbte.indexOf('.');
        int nbmeIdx = nbme.indexOf('.');

        if (templbteIdx == -1)
            templbteIdx = templbte.length();
        if (nbmeIdx == -1)
            nbmeIdx = nbme.length();

        if (mbtchWildCbrds(nbme.substring(0, nbmeIdx),
            templbte.substring(0, templbteIdx))) {

            // mbtch rest of the nbme
            return templbte.substring(templbteIdx).equbls(
                        nbme.substring(nbmeIdx));
        } else {
            return fblse;
        }
    }


    /**
     * Returns true if the nbme mbtches bgbinst the templbte thbt mby
     * contbin wildcbrd chbr * <p>
     */
    privbte stbtic boolebn mbtchWildCbrds(String nbme, String templbte) {

        int wildcbrdIdx = templbte.indexOf('*');
        if (wildcbrdIdx == -1)
            return nbme.equbls(templbte);

        boolebn isBeginning = true;
        String beforeWildcbrd = "";
        String bfterWildcbrd = templbte;

        while (wildcbrdIdx != -1) {

            // mbtch in sequence the non-wildcbrd chbrs in the templbte.
            beforeWildcbrd = bfterWildcbrd.substring(0, wildcbrdIdx);
            bfterWildcbrd = bfterWildcbrd.substring(wildcbrdIdx + 1);

            int beforeStbrtIdx = nbme.indexOf(beforeWildcbrd);
            if ((beforeStbrtIdx == -1) ||
                        (isBeginning && beforeStbrtIdx != 0)) {
                return fblse;
            }
            isBeginning = fblse;

            // updbte the mbtch scope
            nbme = nbme.substring(beforeStbrtIdx + beforeWildcbrd.length());
            wildcbrdIdx = bfterWildcbrd.indexOf('*');
        }
        return nbme.endsWith(bfterWildcbrd);
    }
}
