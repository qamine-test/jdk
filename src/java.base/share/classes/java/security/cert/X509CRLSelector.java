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

pbckbge jbvb.security.cert;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.util.*;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.Debug;
import sun.security.util.DerInputStrebm;
import sun.security.x509.CRLNumberExtension;
import sun.security.x509.X500Nbme;

/**
 * A {@code CRLSelector} thbt selects {@code X509CRLs} thbt
 * mbtch bll specified criterib. This clbss is pbrticulbrly useful when
 * selecting CRLs from b {@code CertStore} to check revocbtion stbtus
 * of b pbrticulbr certificbte.
 * <p>
 * When first constructed, bn {@code X509CRLSelector} hbs no criterib
 * enbbled bnd ebch of the {@code get} methods return b defbult
 * vblue ({@code null}). Therefore, the {@link #mbtch mbtch} method
 * would return {@code true} for bny {@code X509CRL}. Typicblly,
 * severbl criterib bre enbbled (by cblling {@link #setIssuers setIssuers}
 * or {@link #setDbteAndTime setDbteAndTime}, for instbnce) bnd then the
 * {@code X509CRLSelector} is pbssed to
 * {@link CertStore#getCRLs CertStore.getCRLs} or some similbr
 * method.
 * <p>
 * Plebse refer to <b href="http://www.ietf.org/rfc/rfc3280.txt">RFC 3280:
 * Internet X.509 Public Key Infrbstructure Certificbte bnd CRL Profile</b>
 * for definitions of the X.509 CRL fields bnd extensions mentioned below.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CRLSelector
 * @see X509CRL
 *
 * @since       1.4
 * @buthor      Steve Hbnnb
 */
public clbss X509CRLSelector implements CRLSelector {

    stbtic {
        CertPbthHelperImpl.initiblize();
    }

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    privbte HbshSet<Object> issuerNbmes;
    privbte HbshSet<X500Principbl> issuerX500Principbls;
    privbte BigInteger minCRL;
    privbte BigInteger mbxCRL;
    privbte Dbte dbteAndTime;
    privbte X509Certificbte certChecking;
    privbte long skew = 0;

    /**
     * Crebtes bn {@code X509CRLSelector}. Initiblly, no criterib bre set
     * so bny {@code X509CRL} will mbtch.
     */
    public X509CRLSelector() {}

    /**
     * Sets the issuerNbmes criterion. The issuer distinguished nbme in the
     * {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes. If {@code null}, bny issuer distinguished nbme
     * will do.
     * <p>
     * This method bllows the cbller to specify, with b single method cbll,
     * the complete set of issuer nbmes which {@code X509CRLs} mby contbin.
     * The specified vblue replbces the previous vblue for the issuerNbmes
     * criterion.
     * <p>
     * The {@code nbmes} pbrbmeter (if not {@code null}) is b
     * {@code Collection} of {@code X500Principbl}s.
     * <p>
     * Note thbt the {@code nbmes} pbrbmeter cbn contbin duplicbte
     * distinguished nbmes, but they mby be removed from the
     * {@code Collection} of nbmes returned by the
     * {@link #getIssuers getIssuers} method.
     * <p>
     * Note thbt b copy is performed on the {@code Collection} to
     * protect bgbinst subsequent modificbtions.
     *
     * @pbrbm issuers b {@code Collection} of X500Principbls
     *   (or {@code null})
     * @see #getIssuers
     * @since 1.5
     */
    public void setIssuers(Collection<X500Principbl> issuers) {
        if ((issuers == null) || issuers.isEmpty()) {
            issuerNbmes = null;
            issuerX500Principbls = null;
        } else {
            // clone
            issuerX500Principbls = new HbshSet<X500Principbl>(issuers);
            issuerNbmes = new HbshSet<Object>();
            for (X500Principbl p : issuerX500Principbls) {
                issuerNbmes.bdd(p.getEncoded());
            }
        }
    }

    /**
     * <strong>Note:</strong> use {@linkplbin #setIssuers(Collection)} instebd
     * or only specify the byte brrby form of distinguished nbmes when using
     * this method. See {@link #bddIssuerNbme(String)} for more informbtion.
     * <p>
     * Sets the issuerNbmes criterion. The issuer distinguished nbme in the
     * {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes. If {@code null}, bny issuer distinguished nbme
     * will do.
     * <p>
     * This method bllows the cbller to specify, with b single method cbll,
     * the complete set of issuer nbmes which {@code X509CRLs} mby contbin.
     * The specified vblue replbces the previous vblue for the issuerNbmes
     * criterion.
     * <p>
     * The {@code nbmes} pbrbmeter (if not {@code null}) is b
     * {@code Collection} of nbmes. Ebch nbme is b {@code String}
     * or b byte brrby representing b distinguished nbme (in
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b> or
     * ASN.1 DER encoded form, respectively). If {@code null} is supplied
     * bs the vblue for this brgument, no issuerNbmes check will be performed.
     * <p>
     * Note thbt the {@code nbmes} pbrbmeter cbn contbin duplicbte
     * distinguished nbmes, but they mby be removed from the
     * {@code Collection} of nbmes returned by the
     * {@link #getIssuerNbmes getIssuerNbmes} method.
     * <p>
     * If b nbme is specified bs b byte brrby, it should contbin b single DER
     * encoded distinguished nbme, bs defined in X.501. The ASN.1 notbtion for
     * this structure is bs follows.
     * <pre>{@code
     * Nbme ::= CHOICE {
     *   RDNSequence }
     *
     * RDNSequence ::= SEQUENCE OF RelbtiveDistinguishedNbme
     *
     * RelbtiveDistinguishedNbme ::=
     *   SET SIZE (1 .. MAX) OF AttributeTypeAndVblue
     *
     * AttributeTypeAndVblue ::= SEQUENCE {
     *   type     AttributeType,
     *   vblue    AttributeVblue }
     *
     * AttributeType ::= OBJECT IDENTIFIER
     *
     * AttributeVblue ::= ANY DEFINED BY AttributeType
     * ....
     * DirectoryString ::= CHOICE {
     *       teletexString           TeletexString (SIZE (1..MAX)),
     *       printbbleString         PrintbbleString (SIZE (1..MAX)),
     *       universblString         UniversblString (SIZE (1..MAX)),
     *       utf8String              UTF8String (SIZE (1.. MAX)),
     *       bmpString               BMPString (SIZE (1..MAX)) }
     * }</pre>
     * <p>
     * Note thbt b deep copy is performed on the {@code Collection} to
     * protect bgbinst subsequent modificbtions.
     *
     * @pbrbm nbmes b {@code Collection} of nbmes (or {@code null})
     * @throws IOException if b pbrsing error occurs
     * @see #getIssuerNbmes
     */
    public void setIssuerNbmes(Collection<?> nbmes) throws IOException {
        if (nbmes == null || nbmes.size() == 0) {
            issuerNbmes = null;
            issuerX500Principbls = null;
        } else {
            HbshSet<Object> tempNbmes = cloneAndCheckIssuerNbmes(nbmes);
            // Ensure thbt we either set both of these or neither
            issuerX500Principbls = pbrseIssuerNbmes(tempNbmes);
            issuerNbmes = tempNbmes;
        }
    }

    /**
     * Adds b nbme to the issuerNbmes criterion. The issuer distinguished
     * nbme in the {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes.
     * <p>
     * This method bllows the cbller to bdd b nbme to the set of issuer nbmes
     * which {@code X509CRLs} mby contbin. The specified nbme is bdded to
     * bny previous vblue for the issuerNbmes criterion.
     * If the specified nbme is b duplicbte, it mby be ignored.
     *
     * @pbrbm issuer the issuer bs X500Principbl
     * @since 1.5
     */
    public void bddIssuer(X500Principbl issuer) {
        bddIssuerNbmeInternbl(issuer.getEncoded(), issuer);
    }

    /**
     * <strong>Denigrbted</strong>, use
     * {@linkplbin #bddIssuer(X500Principbl)} or
     * {@linkplbin #bddIssuerNbme(byte[])} instebd. This method should not be
     * relied on bs it cbn fbil to mbtch some CRLs becbuse of b loss of
     * encoding informbtion in the RFC 2253 String form of some distinguished
     * nbmes.
     * <p>
     * Adds b nbme to the issuerNbmes criterion. The issuer distinguished
     * nbme in the {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes.
     * <p>
     * This method bllows the cbller to bdd b nbme to the set of issuer nbmes
     * which {@code X509CRLs} mby contbin. The specified nbme is bdded to
     * bny previous vblue for the issuerNbmes criterion.
     * If the specified nbme is b duplicbte, it mby be ignored.
     *
     * @pbrbm nbme the nbme in RFC 2253 form
     * @throws IOException if b pbrsing error occurs
     */
    public void bddIssuerNbme(String nbme) throws IOException {
        bddIssuerNbmeInternbl(nbme, new X500Nbme(nbme).bsX500Principbl());
    }

    /**
     * Adds b nbme to the issuerNbmes criterion. The issuer distinguished
     * nbme in the {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes.
     * <p>
     * This method bllows the cbller to bdd b nbme to the set of issuer nbmes
     * which {@code X509CRLs} mby contbin. The specified nbme is bdded to
     * bny previous vblue for the issuerNbmes criterion. If the specified nbme
     * is b duplicbte, it mby be ignored.
     * If b nbme is specified bs b byte brrby, it should contbin b single DER
     * encoded distinguished nbme, bs defined in X.501. The ASN.1 notbtion for
     * this structure is bs follows.
     * <p>
     * The nbme is provided bs b byte brrby. This byte brrby should contbin
     * b single DER encoded distinguished nbme, bs defined in X.501. The ASN.1
     * notbtion for this structure bppebrs in the documentbtion for
     * {@link #setIssuerNbmes setIssuerNbmes(Collection nbmes)}.
     * <p>
     * Note thbt the byte brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm nbme b byte brrby contbining the nbme in ASN.1 DER encoded form
     * @throws IOException if b pbrsing error occurs
     */
    public void bddIssuerNbme(byte[] nbme) throws IOException {
        // clone becbuse byte brrbys bre modifibble
        bddIssuerNbmeInternbl(nbme.clone(), new X500Nbme(nbme).bsX500Principbl());
    }

    /**
     * A privbte method thbt bdds b nbme (String or byte brrby) to the
     * issuerNbmes criterion. The issuer distinguished
     * nbme in the {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes.
     *
     * @pbrbm nbme the nbme in string or byte brrby form
     * @pbrbm principbl the nbme in X500Principbl form
     * @throws IOException if b pbrsing error occurs
     */
    privbte void bddIssuerNbmeInternbl(Object nbme, X500Principbl principbl) {
        if (issuerNbmes == null) {
            issuerNbmes = new HbshSet<Object>();
        }
        if (issuerX500Principbls == null) {
            issuerX500Principbls = new HbshSet<X500Principbl>();
        }
        issuerNbmes.bdd(nbme);
        issuerX500Principbls.bdd(principbl);
    }

    /**
     * Clone bnd check bn brgument of the form pbssed to
     * setIssuerNbmes. Throw bn IOException if the brgument is mblformed.
     *
     * @pbrbm nbmes b {@code Collection} of nbmes. Ebch entry is b
     *              String or b byte brrby (the nbme, in string or ASN.1
     *              DER encoded form, respectively). {@code null} is
     *              not bn bcceptbble vblue.
     * @return b deep copy of the specified {@code Collection}
     * @throws IOException if b pbrsing error occurs
     */
    privbte stbtic HbshSet<Object> cloneAndCheckIssuerNbmes(Collection<?> nbmes)
        throws IOException
    {
        HbshSet<Object> nbmesCopy = new HbshSet<Object>();
        Iterbtor<?> i = nbmes.iterbtor();
        while (i.hbsNext()) {
            Object nbmeObject = i.next();
            if (!(nbmeObject instbnceof byte []) &&
                !(nbmeObject instbnceof String))
                throw new IOException("nbme not byte brrby or String");
            if (nbmeObject instbnceof byte [])
                nbmesCopy.bdd(((byte []) nbmeObject).clone());
            else
                nbmesCopy.bdd(nbmeObject);
        }
        return(nbmesCopy);
    }

    /**
     * Clone bn brgument of the form pbssed to setIssuerNbmes.
     * Throw b RuntimeException if the brgument is mblformed.
     * <p>
     * This method wrbps cloneAndCheckIssuerNbmes, chbnging bny IOException
     * into b RuntimeException. This method should be used when the object being
     * cloned hbs blrebdy been checked, so there should never be bny exceptions.
     *
     * @pbrbm nbmes b {@code Collection} of nbmes. Ebch entry is b
     *              String or b byte brrby (the nbme, in string or ASN.1
     *              DER encoded form, respectively). {@code null} is
     *              not bn bcceptbble vblue.
     * @return b deep copy of the specified {@code Collection}
     * @throws RuntimeException if b pbrsing error occurs
     */
    privbte stbtic HbshSet<Object> cloneIssuerNbmes(Collection<Object> nbmes) {
        try {
            return cloneAndCheckIssuerNbmes(nbmes);
        } cbtch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Pbrse bn brgument of the form pbssed to setIssuerNbmes,
     * returning b Collection of issuerX500Principbls.
     * Throw bn IOException if the brgument is mblformed.
     *
     * @pbrbm nbmes b {@code Collection} of nbmes. Ebch entry is b
     *              String or b byte brrby (the nbme, in string or ASN.1
     *              DER encoded form, respectively). <Code>Null</Code> is
     *              not bn bcceptbble vblue.
     * @return b HbshSet of issuerX500Principbls
     * @throws IOException if b pbrsing error occurs
     */
    privbte stbtic HbshSet<X500Principbl> pbrseIssuerNbmes(Collection<Object> nbmes)
    throws IOException {
        HbshSet<X500Principbl> x500Principbls = new HbshSet<X500Principbl>();
        for (Iterbtor<Object> t = nbmes.iterbtor(); t.hbsNext(); ) {
            Object nbmeObject = t.next();
            if (nbmeObject instbnceof String) {
                x500Principbls.bdd(new X500Nbme((String)nbmeObject).bsX500Principbl());
            } else {
                try {
                    x500Principbls.bdd(new X500Principbl((byte[])nbmeObject));
                } cbtch (IllegblArgumentException e) {
                    throw (IOException)new IOException("Invblid nbme").initCbuse(e);
                }
            }
        }
        return x500Principbls;
    }

    /**
     * Sets the minCRLNumber criterion. The {@code X509CRL} must hbve b
     * CRL number extension whose vblue is grebter thbn or equbl to the
     * specified vblue. If {@code null}, no minCRLNumber check will be
     * done.
     *
     * @pbrbm minCRL the minimum CRL number bccepted (or {@code null})
     */
    public void setMinCRLNumber(BigInteger minCRL) {
        this.minCRL = minCRL;
    }

    /**
     * Sets the mbxCRLNumber criterion. The {@code X509CRL} must hbve b
     * CRL number extension whose vblue is less thbn or equbl to the
     * specified vblue. If {@code null}, no mbxCRLNumber check will be
     * done.
     *
     * @pbrbm mbxCRL the mbximum CRL number bccepted (or {@code null})
     */
    public void setMbxCRLNumber(BigInteger mbxCRL) {
        this.mbxCRL = mbxCRL;
    }

    /**
     * Sets the dbteAndTime criterion. The specified dbte must be
     * equbl to or lbter thbn the vblue of the thisUpdbte component
     * of the {@code X509CRL} bnd ebrlier thbn the vblue of the
     * nextUpdbte component. There is no mbtch if the {@code X509CRL}
     * does not contbin b nextUpdbte component.
     * If {@code null}, no dbteAndTime check will be done.
     * <p>
     * Note thbt the {@code Dbte} supplied here is cloned to protect
     * bgbinst subsequent modificbtions.
     *
     * @pbrbm dbteAndTime the {@code Dbte} to mbtch bgbinst
     *                    (or {@code null})
     * @see #getDbteAndTime
     */
    public void setDbteAndTime(Dbte dbteAndTime) {
        if (dbteAndTime == null)
            this.dbteAndTime = null;
        else
            this.dbteAndTime = new Dbte(dbteAndTime.getTime());
        this.skew = 0;
    }

    /**
     * Sets the dbteAndTime criterion bnd bllows for the specified clock skew
     * (in milliseconds) when checking bgbinst the vblidity period of the CRL.
     */
    void setDbteAndTime(Dbte dbteAndTime, long skew) {
        this.dbteAndTime =
            (dbteAndTime == null ? null : new Dbte(dbteAndTime.getTime()));
        this.skew = skew;
    }

    /**
     * Sets the certificbte being checked. This is not b criterion. Rbther,
     * it is optionbl informbtion thbt mby help b {@code CertStore}
     * find CRLs thbt would be relevbnt when checking revocbtion for the
     * specified certificbte. If {@code null} is specified, then no
     * such optionbl informbtion is provided.
     *
     * @pbrbm cert the {@code X509Certificbte} being checked
     *             (or {@code null})
     * @see #getCertificbteChecking
     */
    public void setCertificbteChecking(X509Certificbte cert) {
        certChecking = cert;
    }

    /**
     * Returns the issuerNbmes criterion. The issuer distinguished
     * nbme in the {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes. If the vblue returned is {@code null}, bny
     * issuer distinguished nbme will do.
     * <p>
     * If the vblue returned is not {@code null}, it is b
     * unmodifibble {@code Collection} of {@code X500Principbl}s.
     *
     * @return bn unmodifibble {@code Collection} of nbmes
     *   (or {@code null})
     * @see #setIssuers
     * @since 1.5
     */
    public Collection<X500Principbl> getIssuers() {
        if (issuerX500Principbls == null) {
            return null;
        }
        return Collections.unmodifibbleCollection(issuerX500Principbls);
    }

    /**
     * Returns b copy of the issuerNbmes criterion. The issuer distinguished
     * nbme in the {@code X509CRL} must mbtch bt lebst one of the specified
     * distinguished nbmes. If the vblue returned is {@code null}, bny
     * issuer distinguished nbme will do.
     * <p>
     * If the vblue returned is not {@code null}, it is b
     * {@code Collection} of nbmes. Ebch nbme is b {@code String}
     * or b byte brrby representing b distinguished nbme (in RFC 2253 or
     * ASN.1 DER encoded form, respectively).  Note thbt the
     * {@code Collection} returned mby contbin duplicbte nbmes.
     * <p>
     * If b nbme is specified bs b byte brrby, it should contbin b single DER
     * encoded distinguished nbme, bs defined in X.501. The ASN.1 notbtion for
     * this structure is given in the documentbtion for
     * {@link #setIssuerNbmes setIssuerNbmes(Collection nbmes)}.
     * <p>
     * Note thbt b deep copy is performed on the {@code Collection} to
     * protect bgbinst subsequent modificbtions.
     *
     * @return b {@code Collection} of nbmes (or {@code null})
     * @see #setIssuerNbmes
     */
    public Collection<Object> getIssuerNbmes() {
        if (issuerNbmes == null) {
            return null;
        }
        return cloneIssuerNbmes(issuerNbmes);
    }

    /**
     * Returns the minCRLNumber criterion. The {@code X509CRL} must hbve b
     * CRL number extension whose vblue is grebter thbn or equbl to the
     * specified vblue. If {@code null}, no minCRLNumber check will be done.
     *
     * @return the minimum CRL number bccepted (or {@code null})
     */
    public BigInteger getMinCRL() {
        return minCRL;
    }

    /**
     * Returns the mbxCRLNumber criterion. The {@code X509CRL} must hbve b
     * CRL number extension whose vblue is less thbn or equbl to the
     * specified vblue. If {@code null}, no mbxCRLNumber check will be
     * done.
     *
     * @return the mbximum CRL number bccepted (or {@code null})
     */
    public BigInteger getMbxCRL() {
        return mbxCRL;
    }

    /**
     * Returns the dbteAndTime criterion. The specified dbte must be
     * equbl to or lbter thbn the vblue of the thisUpdbte component
     * of the {@code X509CRL} bnd ebrlier thbn the vblue of the
     * nextUpdbte component. There is no mbtch if the
     * {@code X509CRL} does not contbin b nextUpdbte component.
     * If {@code null}, no dbteAndTime check will be done.
     * <p>
     * Note thbt the {@code Dbte} returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return the {@code Dbte} to mbtch bgbinst (or {@code null})
     * @see #setDbteAndTime
     */
    public Dbte getDbteAndTime() {
        if (dbteAndTime == null)
            return null;
        return (Dbte) dbteAndTime.clone();
    }

    /**
     * Returns the certificbte being checked. This is not b criterion. Rbther,
     * it is optionbl informbtion thbt mby help b {@code CertStore}
     * find CRLs thbt would be relevbnt when checking revocbtion for the
     * specified certificbte. If the vblue returned is {@code null}, then
     * no such optionbl informbtion is provided.
     *
     * @return the certificbte being checked (or {@code null})
     * @see #setCertificbteChecking
     */
    public X509Certificbte getCertificbteChecking() {
        return certChecking;
    }

    /**
     * Returns b printbble representbtion of the {@code X509CRLSelector}.
     *
     * @return b {@code String} describing the contents of the
     *         {@code X509CRLSelector}.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("X509CRLSelector: [\n");
        if (issuerNbmes != null) {
            sb.bppend("  IssuerNbmes:\n");
            Iterbtor<Object> i = issuerNbmes.iterbtor();
            while (i.hbsNext())
                sb.bppend("    " + i.next() + "\n");
        }
        if (minCRL != null)
            sb.bppend("  minCRLNumber: " + minCRL + "\n");
        if (mbxCRL != null)
            sb.bppend("  mbxCRLNumber: " + mbxCRL + "\n");
        if (dbteAndTime != null)
            sb.bppend("  dbteAndTime: " + dbteAndTime + "\n");
        if (certChecking != null)
            sb.bppend("  Certificbte being checked: " + certChecking + "\n");
        sb.bppend("]");
        return sb.toString();
    }

    /**
     * Decides whether b {@code CRL} should be selected.
     *
     * @pbrbm crl the {@code CRL} to be checked
     * @return {@code true} if the {@code CRL} should be selected,
     *         {@code fblse} otherwise
     */
    public boolebn mbtch(CRL crl) {
        if (!(crl instbnceof X509CRL)) {
            return fblse;
        }
        X509CRL xcrl = (X509CRL)crl;

        /* mbtch on issuer nbme */
        if (issuerNbmes != null) {
            X500Principbl issuer = xcrl.getIssuerX500Principbl();
            Iterbtor<X500Principbl> i = issuerX500Principbls.iterbtor();
            boolebn found = fblse;
            while (!found && i.hbsNext()) {
                if (i.next().equbls(issuer)) {
                    found = true;
                }
            }
            if (!found) {
                if (debug != null) {
                    debug.println("X509CRLSelector.mbtch: issuer DNs "
                        + "don't mbtch");
                }
                return fblse;
            }
        }

        if ((minCRL != null) || (mbxCRL != null)) {
            /* Get CRL number extension from CRL */
            byte[] crlNumExtVbl = xcrl.getExtensionVblue("2.5.29.20");
            if (crlNumExtVbl == null) {
                if (debug != null) {
                    debug.println("X509CRLSelector.mbtch: no CRLNumber");
                }
            }
            BigInteger crlNum;
            try {
                DerInputStrebm in = new DerInputStrebm(crlNumExtVbl);
                byte[] encoded = in.getOctetString();
                CRLNumberExtension crlNumExt =
                    new CRLNumberExtension(Boolebn.FALSE, encoded);
                crlNum = crlNumExt.get(CRLNumberExtension.NUMBER);
            } cbtch (IOException ex) {
                if (debug != null) {
                    debug.println("X509CRLSelector.mbtch: exception in "
                        + "decoding CRL number");
                }
                return fblse;
            }

            /* mbtch on minCRLNumber */
            if (minCRL != null) {
                if (crlNum.compbreTo(minCRL) < 0) {
                    if (debug != null) {
                        debug.println("X509CRLSelector.mbtch: CRLNumber too smbll");
                    }
                    return fblse;
                }
            }

            /* mbtch on mbxCRLNumber */
            if (mbxCRL != null) {
                if (crlNum.compbreTo(mbxCRL) > 0) {
                    if (debug != null) {
                        debug.println("X509CRLSelector.mbtch: CRLNumber too lbrge");
                    }
                    return fblse;
                }
            }
        }


        /* mbtch on dbteAndTime */
        if (dbteAndTime != null) {
            Dbte crlThisUpdbte = xcrl.getThisUpdbte();
            Dbte nextUpdbte = xcrl.getNextUpdbte();
            if (nextUpdbte == null) {
                if (debug != null) {
                    debug.println("X509CRLSelector.mbtch: nextUpdbte null");
                }
                return fblse;
            }
            Dbte nowPlusSkew = dbteAndTime;
            Dbte nowMinusSkew = dbteAndTime;
            if (skew > 0) {
                nowPlusSkew = new Dbte(dbteAndTime.getTime() + skew);
                nowMinusSkew = new Dbte(dbteAndTime.getTime() - skew);
            }
            if (nowMinusSkew.bfter(nextUpdbte)
                || nowPlusSkew.before(crlThisUpdbte)) {
                if (debug != null) {
                    debug.println("X509CRLSelector.mbtch: updbte out of rbnge");
                }
                return fblse;
            }
        }

        return true;
    }

    /**
     * Returns b copy of this object.
     *
     * @return the copy
     */
    public Object clone() {
        try {
            X509CRLSelector copy = (X509CRLSelector)super.clone();
            if (issuerNbmes != null) {
                copy.issuerNbmes =
                        new HbshSet<Object>(issuerNbmes);
                copy.issuerX500Principbls =
                        new HbshSet<X500Principbl>(issuerX500Principbls);
            }
            return copy;
        } cbtch (CloneNotSupportedException e) {
            /* Cbnnot hbppen */
            throw new InternblError(e.toString(), e);
        }
    }
}
