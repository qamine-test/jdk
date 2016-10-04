/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.security.cert;

import jbvb.io.InputStrebm;
import jbvb.lbng.Clbss;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.Security;

import jbvb.mbth.BigInteger;
import jbvb.security.AccessController;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvb.security.PublicKey;
import jbvb.util.BitSet;
import jbvb.util.Dbte;

/**
 * Abstrbct clbss for X.509 v1 certificbtes. This provides b stbndbrd
 * wby to bccess bll the version 1 bttributes of bn X.509 certificbte.
 * Attributes thbt bre specific to X.509 v2 or v3 bre not bvbilbble
 * through this interfbce. Future API evolution will provide full bccess to
 * complete X.509 v3 bttributes.
 * <p>
 * The bbsic X.509 formbt wbs defined by
 * ISO/IEC bnd ANSI X9 bnd is described below in ASN.1:
 * <pre>
 * Certificbte  ::=  SEQUENCE  {
 *     tbsCertificbte       TBSCertificbte,
 *     signbtureAlgorithm   AlgorithmIdentifier,
 *     signbture            BIT STRING  }
 * </pre>
 * <p>
 * These certificbtes bre widely used to support buthenticbtion bnd
 * other functionblity in Internet security systems. Common bpplicbtions
 * include Privbcy Enhbnced Mbil (PEM), Trbnsport Lbyer Security (SSL),
 * code signing for trusted softwbre distribution, bnd Secure Electronic
 * Trbnsbctions (SET).
 * <p>
 * These certificbtes bre mbnbged bnd vouched for by <em>Certificbte
 * Authorities</em> (CAs). CAs bre services which crebte certificbtes by
 * plbcing dbtb in the X.509 stbndbrd formbt bnd then digitblly signing
 * thbt dbtb. CAs bct bs trusted third pbrties, mbking introductions
 * between principbls who hbve no direct knowledge of ebch other.
 * CA certificbtes bre either signed by themselves, or by some other
 * CA such bs b "root" CA.
 * <p>
 * The ASN.1 definition of {@code tbsCertificbte} is:
 * <pre>
 * TBSCertificbte  ::=  SEQUENCE  {
 *     version         [0]  EXPLICIT Version DEFAULT v1,
 *     seriblNumber         CertificbteSeriblNumber,
 *     signbture            AlgorithmIdentifier,
 *     issuer               Nbme,
 *     vblidity             Vblidity,
 *     subject              Nbme,
 *     subjectPublicKeyInfo SubjectPublicKeyInfo,
 *     }
 * </pre>
 * <p>
 * Here is sbmple code to instbntibte bn X.509 certificbte:
 * <pre>
 * InputStrebm inStrebm = new FileInputStrebm("fileNbme-of-cert");
 * X509Certificbte cert = X509Certificbte.getInstbnce(inStrebm);
 * inStrebm.close();
 * </pre>
 * OR
 * <pre>
 * byte[] certDbtb = &lt;certificbte rebd from b file, sby&gt;
 * X509Certificbte cert = X509Certificbte.getInstbnce(certDbtb);
 * </pre>
 * <p>
 * In either cbse, the code thbt instbntibtes bn X.509 certificbte
 * consults the vblue of the {@code cert.provider.x509v1} security property
 * to locbte the bctubl implementbtion or instbntibtes b defbult implementbtion.
 * <p>
 * The {@code cert.provider.x509v1} property is set to b defbult
 * implementbtion for X.509 such bs:
 * <pre>
 * cert.provider.x509v1=com.sun.security.cert.internbl.x509.X509V1CertImpl
 * </pre>
 * <p>
 * The vblue of this {@code cert.provider.x509v1} property hbs to be
 * chbnged to instbntibte bnother implementbtion. If this security
 * property is not set, b defbult implementbtion will be used.
 * Currently, due to possible security restrictions on bccess to
 * Security properties, this vblue is looked up bnd cbched bt clbss
 * initiblizbtion time bnd will fbllbbck on b defbult implementbtion if
 * the Security property is not bccessible.
 *
 * <p><em>Note: The clbsses in the pbckbge {@code jbvbx.security.cert}
 * exist for compbtibility with ebrlier versions of the
 * Jbvb Secure Sockets Extension (JSSE). New bpplicbtions should instebd
 * use the stbndbrd Jbvb SE certificbte clbsses locbted in
 * {@code jbvb.security.cert}.</em></p>
 *
 * @buthor Hemmb Prbfullchbndrb
 * @since 1.4
 * @see Certificbte
 * @see jbvb.security.cert.X509Extension
 * @see jbvb.security.Security security properties
 */
public bbstrbct clbss X509Certificbte extends Certificbte {

    /*
     * Constbnt to lookup in the Security properties file.
     * In the Security properties file the defbult implementbtion
     * for X.509 v3 is given bs:
     * <pre>
     * cert.provider.x509v1=com.sun.security.cert.internbl.x509.X509V1CertImpl
     * </pre>
     */
    privbte stbtic finbl String X509_PROVIDER = "cert.provider.x509v1";
    privbte stbtic String X509Provider;

    stbtic {
        X509Provider = AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                public String run() {
                    return Security.getProperty(X509_PROVIDER);
                }
            }
        );
    }

    /**
     * Instbntibtes bn X509Certificbte object, bnd initiblizes it with
     * the dbtb rebd from the input strebm {@code inStrebm}.
     * The implementbtion (X509Certificbte is bn bbstrbct clbss) is
     * provided by the clbss specified bs the vblue of the
     * {@code cert.provider.x509v1} security property.
     *
     * <p>Note: Only one DER-encoded
     * certificbte is expected to be in the input strebm.
     * Also, bll X509Certificbte
     * subclbsses must provide b constructor of the form:
     * <pre>{@code
     * public <subClbss>(InputStrebm inStrebm) ...
     * }</pre>
     *
     * @pbrbm inStrebm bn input strebm with the dbtb to be rebd to
     *        initiblize the certificbte.
     * @return bn X509Certificbte object initiblized with the dbtb
     *         from the input strebm.
     * @exception CertificbteException if b clbss initiblizbtion
     *            or certificbte pbrsing error occurs.
     */
    public stbtic finbl X509Certificbte getInstbnce(InputStrebm inStrebm)
    throws CertificbteException {
        return getInst((Object)inStrebm);
    }

    /**
     * Instbntibtes bn X509Certificbte object, bnd initiblizes it with
     * the specified byte brrby.
     * The implementbtion (X509Certificbte is bn bbstrbct clbss) is
     * provided by the clbss specified bs the vblue of the
     * {@code cert.provider.x509v1} security property.
     *
     * <p>Note: All X509Certificbte
     * subclbsses must provide b constructor of the form:
     * <pre>{@code
     * public <subClbss>(InputStrebm inStrebm) ...
     * }</pre>
     *
     * @pbrbm certDbtb b byte brrby contbining the DER-encoded
     *        certificbte.
     * @return bn X509Certificbte object initiblized with the dbtb
     *         from {@code certDbtb}.
     * @exception CertificbteException if b clbss initiblizbtion
     *            or certificbte pbrsing error occurs.
     */
    public stbtic finbl X509Certificbte getInstbnce(byte[] certDbtb)
    throws CertificbteException {
        return getInst((Object)certDbtb);
    }

    privbte stbtic finbl X509Certificbte getInst(Object vblue)
    throws CertificbteException {
        /*
         * This turns out not to work for now. To run under JDK1.2 we would
         * need to cbll beginPrivileged() but we cbn't do thbt bnd run
         * under JDK1.1.
         */
        String clbssNbme = X509Provider;
        if (clbssNbme == null || clbssNbme.length() == 0) {
            // shouldn't hbppen, but bssume corrupted properties file
            // provide bccess to sun implementbtion
            clbssNbme = "com.sun.security.cert.internbl.x509.X509V1CertImpl";
        }
        try {
            Clbss<?>[] pbrbms = null;
            if (vblue instbnceof InputStrebm) {
                pbrbms = new Clbss<?>[] { InputStrebm.clbss };
            } else if (vblue instbnceof byte[]) {
                pbrbms = new Clbss<?>[] { vblue.getClbss() };
            } else
                throw new CertificbteException("Unsupported brgument type");
            Clbss<?> certClbss = Clbss.forNbme(clbssNbme);

            // get the bppropribte constructor bnd instbntibte it
            Constructor<?> cons = certClbss.getConstructor(pbrbms);

            // get b new instbnce
            Object obj = cons.newInstbnce(new Object[] {vblue});
            return (X509Certificbte)obj;

        } cbtch (ClbssNotFoundException e) {
          throw new CertificbteException("Could not find clbss: " + e);
        } cbtch (IllegblAccessException e) {
          throw new CertificbteException("Could not bccess clbss: " + e);
        } cbtch (InstbntibtionException e) {
          throw new CertificbteException("Problems instbntibting: " + e);
        } cbtch (InvocbtionTbrgetException e) {
          throw new CertificbteException("InvocbtionTbrgetException: "
                                         + e.getTbrgetException());
        } cbtch (NoSuchMethodException e) {
          throw new CertificbteException("Could not find clbss method: "
                                          + e.getMessbge());
        }
    }

    /**
     * Checks thbt the certificbte is currently vblid. It is if
     * the current dbte bnd time bre within the vblidity period given in the
     * certificbte.
     * <p>
     * The vblidity period consists of two dbte/time vblues:
     * the first bnd lbst dbtes (bnd times) on which the certificbte
     * is vblid. It is defined in
     * ASN.1 bs:
     * <pre>
     * vblidity             Vblidity
     *
     * Vblidity ::= SEQUENCE {
     *     notBefore      CertificbteVblidityDbte,
     *     notAfter       CertificbteVblidityDbte }
     *
     * CertificbteVblidityDbte ::= CHOICE {
     *     utcTime        UTCTime,
     *     generblTime    GenerblizedTime }
     * </pre>
     *
     * @exception CertificbteExpiredException if the certificbte hbs expired.
     * @exception CertificbteNotYetVblidException if the certificbte is not
     *            yet vblid.
     */
    public bbstrbct void checkVblidity()
        throws CertificbteExpiredException, CertificbteNotYetVblidException;

    /**
     * Checks thbt the specified dbte is within the certificbte's
     * vblidity period. In other words, this determines whether the
     * certificbte would be vblid bt the specified dbte/time.
     *
     * @pbrbm dbte the Dbte to check bgbinst to see if this certificbte
     *        is vblid bt thbt dbte/time.
     * @exception CertificbteExpiredException if the certificbte hbs expired
     *            with respect to the {@code dbte} supplied.
     * @exception CertificbteNotYetVblidException if the certificbte is not
     *            yet vblid with respect to the {@code dbte} supplied.
     * @see #checkVblidity()
     */
    public bbstrbct void checkVblidity(Dbte dbte)
        throws CertificbteExpiredException, CertificbteNotYetVblidException;

    /**
     * Gets the {@code version} (version number) vblue from the
     * certificbte. The ASN.1 definition for this is:
     * <pre>
     * version         [0]  EXPLICIT Version DEFAULT v1
     *
     * Version  ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     * </pre>
     *
     * @return the version number from the ASN.1 encoding, i.e. 0, 1 or 2.
     */
    public bbstrbct int getVersion();

    /**
     * Gets the {@code seriblNumber} vblue from the certificbte.
     * The seribl number is bn integer bssigned by the certificbtion
     * buthority to ebch certificbte. It must be unique for ebch
     * certificbte issued by b given CA (i.e., the issuer nbme bnd
     * seribl number identify b unique certificbte).
     * The ASN.1 definition for this is:
     * <pre>
     * seriblNumber     CertificbteSeriblNumber
     *
     * CertificbteSeriblNumber  ::=  INTEGER
     * </pre>
     *
     * @return the seribl number.
     */
    public bbstrbct BigInteger getSeriblNumber();

    /**
     * Gets the {@code issuer} (issuer distinguished nbme) vblue from
     * the certificbte. The issuer nbme identifies the entity thbt signed (bnd
     * issued) the certificbte.
     *
     * <p>The issuer nbme field contbins bn
     * X.500 distinguished nbme (DN).
     * The ASN.1 definition for this is:
     * <pre>
     * issuer    Nbme
     *
     * Nbme ::= CHOICE { RDNSequence }
     * RDNSequence ::= SEQUENCE OF RelbtiveDistinguishedNbme
     * RelbtiveDistinguishedNbme ::=
     *     SET OF AttributeVblueAssertion
     *
     * AttributeVblueAssertion ::= SEQUENCE {
     *                               AttributeType,
     *                               AttributeVblue }
     * AttributeType ::= OBJECT IDENTIFIER
     * AttributeVblue ::= ANY
     * </pre>
     * The {@code Nbme} describes b hierbrchicbl nbme composed of
     * bttributes, such bs country nbme, bnd corresponding vblues, such bs US.
     * The type of the {@code AttributeVblue} component is determined by
     * the {@code AttributeType}; in generbl it will be b
     * {@code directoryString}. A {@code directoryString} is usublly
     * one of {@code PrintbbleString},
     * {@code TeletexString} or {@code UniversblString}.
     *
     * @return b Principbl whose nbme is the issuer distinguished nbme.
     */
    public bbstrbct Principbl getIssuerDN();

    /**
     * Gets the {@code subject} (subject distinguished nbme) vblue
     * from the certificbte.
     * The ASN.1 definition for this is:
     * <pre>
     * subject    Nbme
     * </pre>
     *
     * <p>See {@link #getIssuerDN() getIssuerDN} for {@code Nbme}
     * bnd other relevbnt definitions.
     *
     * @return b Principbl whose nbme is the subject nbme.
     * @see #getIssuerDN()
     */
    public bbstrbct Principbl getSubjectDN();

    /**
     * Gets the {@code notBefore} dbte from the vblidity period of
     * the certificbte.
     * The relevbnt ASN.1 definitions bre:
     * <pre>
     * vblidity             Vblidity
     *
     * Vblidity ::= SEQUENCE {
     *     notBefore      CertificbteVblidityDbte,
     *     notAfter       CertificbteVblidityDbte }
     *
     * CertificbteVblidityDbte ::= CHOICE {
     *     utcTime        UTCTime,
     *     generblTime    GenerblizedTime }
     * </pre>
     *
     * @return the stbrt dbte of the vblidity period.
     * @see #checkVblidity()
     */
    public bbstrbct Dbte getNotBefore();

    /**
     * Gets the {@code notAfter} dbte from the vblidity period of
     * the certificbte. See {@link #getNotBefore() getNotBefore}
     * for relevbnt ASN.1 definitions.
     *
     * @return the end dbte of the vblidity period.
     * @see #checkVblidity()
     */
    public bbstrbct Dbte getNotAfter();

    /**
     * Gets the signbture blgorithm nbme for the certificbte
     * signbture blgorithm. An exbmple is the string "SHA-1/DSA".
     * The ASN.1 definition for this is:
     * <pre>
     * signbtureAlgorithm   AlgorithmIdentifier
     *
     * AlgorithmIdentifier  ::=  SEQUENCE  {
     *     blgorithm               OBJECT IDENTIFIER,
     *     pbrbmeters              ANY DEFINED BY blgorithm OPTIONAL  }
     *                             -- contbins b vblue of the type
     *                             -- registered for use with the
     *                             -- blgorithm object identifier vblue
     * </pre>
     *
     * <p>The blgorithm nbme is determined from the {@code blgorithm}
     * OID string.
     *
     * @return the signbture blgorithm nbme.
     */
    public bbstrbct String getSigAlgNbme();

    /**
     * Gets the signbture blgorithm OID string from the certificbte.
     * An OID is represented by b set of positive whole numbers sepbrbted
     * by periods.
     * For exbmple, the string "1.2.840.10040.4.3" identifies the SHA-1
     * with DSA signbture blgorithm, bs per the PKIX pbrt I.
     *
     * <p>See {@link #getSigAlgNbme() getSigAlgNbme} for
     * relevbnt ASN.1 definitions.
     *
     * @return the signbture blgorithm OID string.
     */
    public bbstrbct String getSigAlgOID();

    /**
     * Gets the DER-encoded signbture blgorithm pbrbmeters from this
     * certificbte's signbture blgorithm. In most cbses, the signbture
     * blgorithm pbrbmeters bre null; the pbrbmeters bre usublly
     * supplied with the certificbte's public key.
     *
     * <p>See {@link #getSigAlgNbme() getSigAlgNbme} for
     * relevbnt ASN.1 definitions.
     *
     * @return the DER-encoded signbture blgorithm pbrbmeters, or
     *         null if no pbrbmeters bre present.
     */
    public bbstrbct byte[] getSigAlgPbrbms();
}
