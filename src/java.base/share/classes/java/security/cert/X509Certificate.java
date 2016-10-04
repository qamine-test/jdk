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

pbckbge jbvb.security.cert;

import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.util.Collection;
import jbvb.util.Dbte;
import jbvb.util.List;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.x509.X509CertImpl;

/**
 * <p>
 * Abstrbct clbss for X.509 certificbtes. This provides b stbndbrd
 * wby to bccess bll the bttributes of bn X.509 certificbte.
 * <p>
 * In June of 1996, the bbsic X.509 v3 formbt wbs completed by
 * ISO/IEC bnd ANSI X9, which is described below in ASN.1:
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
 * More informbtion cbn be found in
 * <b href="http://www.ietf.org/rfc/rfc3280.txt">RFC 3280: Internet X.509
 * Public Key Infrbstructure Certificbte bnd CRL Profile</b>.
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
 *     issuerUniqueID  [1]  IMPLICIT UniqueIdentifier OPTIONAL,
 *                          -- If present, version must be v2 or v3
 *     subjectUniqueID [2]  IMPLICIT UniqueIdentifier OPTIONAL,
 *                          -- If present, version must be v2 or v3
 *     extensions      [3]  EXPLICIT Extensions OPTIONAL
 *                          -- If present, version must be v3
 *     }
 * </pre>
 * <p>
 * Certificbtes bre instbntibted using b certificbte fbctory. The following is
 * bn exbmple of how to instbntibte bn X.509 certificbte:
 * <pre>
 * try (InputStrebm inStrebm = new FileInputStrebm("fileNbme-of-cert")) {
 *     CertificbteFbctory cf = CertificbteFbctory.getInstbnce("X.509");
 *     X509Certificbte cert = (X509Certificbte)cf.generbteCertificbte(inStrebm);
 * }
 * </pre>
 *
 * @buthor Hemmb Prbfullchbndrb
 *
 *
 * @see Certificbte
 * @see CertificbteFbctory
 * @see X509Extension
 */

public bbstrbct clbss X509Certificbte extends Certificbte
implements X509Extension {

    privbte stbtic finbl long seriblVersionUID = -2491127588187038216L;

    privbte trbnsient X500Principbl subjectX500Principbl, issuerX500Principbl;

    /**
     * Constructor for X.509 certificbtes.
     */
    protected X509Certificbte() {
        super("X.509");
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
     * yet vblid.
     */
    public bbstrbct void checkVblidity()
        throws CertificbteExpiredException, CertificbteNotYetVblidException;

    /**
     * Checks thbt the given dbte is within the certificbte's
     * vblidity period. In other words, this determines whether the
     * certificbte would be vblid bt the given dbte/time.
     *
     * @pbrbm dbte the Dbte to check bgbinst to see if this certificbte
     *        is vblid bt thbt dbte/time.
     *
     * @exception CertificbteExpiredException if the certificbte hbs expired
     * with respect to the {@code dbte} supplied.
     * @exception CertificbteNotYetVblidException if the certificbte is not
     * yet vblid with respect to the {@code dbte} supplied.
     *
     * @see #checkVblidity()
     */
    public bbstrbct void checkVblidity(Dbte dbte)
        throws CertificbteExpiredException, CertificbteNotYetVblidException;

    /**
     * Gets the {@code version} (version number) vblue from the
     * certificbte.
     * The ASN.1 definition for this is:
     * <pre>
     * version  [0] EXPLICIT Version DEFAULT v1
     *
     * Version ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     * </pre>
     * @return the version number, i.e. 1, 2 or 3.
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
     * <strong>Denigrbted</strong>, replbced by {@linkplbin
     * #getIssuerX500Principbl()}. This method returns the {@code issuer}
     * bs bn implementbtion specific Principbl object, which should not be
     * relied upon by portbble code.
     *
     * <p>
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
     * bttributes,
     * such bs country nbme, bnd corresponding vblues, such bs US.
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
     * Returns the issuer (issuer distinguished nbme) vblue from the
     * certificbte bs bn {@code X500Principbl}.
     * <p>
     * It is recommended thbt subclbsses override this method.
     *
     * @return bn {@code X500Principbl} representing the issuer
     *          distinguished nbme
     * @since 1.4
     */
    public X500Principbl getIssuerX500Principbl() {
        if (issuerX500Principbl == null) {
            issuerX500Principbl = X509CertImpl.getIssuerX500Principbl(this);
        }
        return issuerX500Principbl;
    }

    /**
     * <strong>Denigrbted</strong>, replbced by {@linkplbin
     * #getSubjectX500Principbl()}. This method returns the {@code subject}
     * bs bn implementbtion specific Principbl object, which should not be
     * relied upon by portbble code.
     *
     * <p>
     * Gets the {@code subject} (subject distinguished nbme) vblue
     * from the certificbte.  If the {@code subject} vblue is empty,
     * then the {@code getNbme()} method of the returned
     * {@code Principbl} object returns bn empty string ("").
     *
     * <p> The ASN.1 definition for this is:
     * <pre>
     * subject    Nbme
     * </pre>
     *
     * <p>See {@link #getIssuerDN() getIssuerDN} for {@code Nbme}
     * bnd other relevbnt definitions.
     *
     * @return b Principbl whose nbme is the subject nbme.
     */
    public bbstrbct Principbl getSubjectDN();

    /**
     * Returns the subject (subject distinguished nbme) vblue from the
     * certificbte bs bn {@code X500Principbl}.  If the subject vblue
     * is empty, then the {@code getNbme()} method of the returned
     * {@code X500Principbl} object returns bn empty string ("").
     * <p>
     * It is recommended thbt subclbsses override this method.
     *
     * @return bn {@code X500Principbl} representing the subject
     *          distinguished nbme
     * @since 1.4
     */
    public X500Principbl getSubjectX500Principbl() {
        if (subjectX500Principbl == null) {
            subjectX500Principbl = X509CertImpl.getSubjectX500Principbl(this);
        }
        return subjectX500Principbl;
    }

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
     * @see #checkVblidity
     */
    public bbstrbct Dbte getNotBefore();

    /**
     * Gets the {@code notAfter} dbte from the vblidity period of
     * the certificbte. See {@link #getNotBefore() getNotBefore}
     * for relevbnt ASN.1 definitions.
     *
     * @return the end dbte of the vblidity period.
     * @see #checkVblidity
     */
    public bbstrbct Dbte getNotAfter();

    /**
     * Gets the DER-encoded certificbte informbtion, the
     * {@code tbsCertificbte} from this certificbte.
     * This cbn be used to verify the signbture independently.
     *
     * @return the DER-encoded certificbte informbtion.
     * @exception CertificbteEncodingException if bn encoding error occurs.
     */
    public bbstrbct byte[] getTBSCertificbte()
        throws CertificbteEncodingException;

    /**
     * Gets the {@code signbture} vblue (the rbw signbture bits) from
     * the certificbte.
     * The ASN.1 definition for this is:
     * <pre>
     * signbture     BIT STRING
     * </pre>
     *
     * @return the signbture.
     */
    public bbstrbct byte[] getSignbture();

    /**
     * Gets the signbture blgorithm nbme for the certificbte
     * signbture blgorithm. An exbmple is the string "SHA256withRSA".
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
     * An OID is represented by b set of nonnegbtive whole numbers sepbrbted
     * by periods.
     * For exbmple, the string "1.2.840.10040.4.3" identifies the SHA-1
     * with DSA signbture blgorithm defined in
     * <b href="http://www.ietf.org/rfc/rfc3279.txt">RFC 3279: Algorithms bnd
     * Identifiers for the Internet X.509 Public Key Infrbstructure Certificbte
     * bnd CRL Profile</b>.
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
     * If bccess to individubl pbrbmeter vblues is needed then use
     * {@link jbvb.security.AlgorithmPbrbmeters AlgorithmPbrbmeters}
     * bnd instbntibte with the nbme returned by
     * {@link #getSigAlgNbme() getSigAlgNbme}.
     *
     * <p>See {@link #getSigAlgNbme() getSigAlgNbme} for
     * relevbnt ASN.1 definitions.
     *
     * @return the DER-encoded signbture blgorithm pbrbmeters, or
     *         null if no pbrbmeters bre present.
     */
    public bbstrbct byte[] getSigAlgPbrbms();

    /**
     * Gets the {@code issuerUniqueID} vblue from the certificbte.
     * The issuer unique identifier is present in the certificbte
     * to hbndle the possibility of reuse of issuer nbmes over time.
     * RFC 3280 recommends thbt nbmes not be reused bnd thbt
     * conforming certificbtes not mbke use of unique identifiers.
     * Applicbtions conforming to thbt profile should be cbpbble of
     * pbrsing unique identifiers bnd mbking compbrisons.
     *
     * <p>The ASN.1 definition for this is:
     * <pre>
     * issuerUniqueID  [1]  IMPLICIT UniqueIdentifier OPTIONAL
     *
     * UniqueIdentifier  ::=  BIT STRING
     * </pre>
     *
     * @return the issuer unique identifier or null if it is not
     * present in the certificbte.
     */
    public bbstrbct boolebn[] getIssuerUniqueID();

    /**
     * Gets the {@code subjectUniqueID} vblue from the certificbte.
     *
     * <p>The ASN.1 definition for this is:
     * <pre>
     * subjectUniqueID  [2]  IMPLICIT UniqueIdentifier OPTIONAL
     *
     * UniqueIdentifier  ::=  BIT STRING
     * </pre>
     *
     * @return the subject unique identifier or null if it is not
     * present in the certificbte.
     */
    public bbstrbct boolebn[] getSubjectUniqueID();

    /**
     * Gets b boolebn brrby representing bits of
     * the {@code KeyUsbge} extension, (OID = 2.5.29.15).
     * The key usbge extension defines the purpose (e.g., encipherment,
     * signbture, certificbte signing) of the key contbined in the
     * certificbte.
     * The ASN.1 definition for this is:
     * <pre>
     * KeyUsbge ::= BIT STRING {
     *     digitblSignbture        (0),
     *     nonRepudibtion          (1),
     *     keyEncipherment         (2),
     *     dbtbEncipherment        (3),
     *     keyAgreement            (4),
     *     keyCertSign             (5),
     *     cRLSign                 (6),
     *     encipherOnly            (7),
     *     decipherOnly            (8) }
     * </pre>
     * RFC 3280 recommends thbt when used, this be mbrked
     * bs b criticbl extension.
     *
     * @return the KeyUsbge extension of this certificbte, represented bs
     * bn brrby of boolebns. The order of KeyUsbge vblues in the brrby is
     * the sbme bs in the bbove ASN.1 definition. The brrby will contbin b
     * vblue for ebch KeyUsbge defined bbove. If the KeyUsbge list encoded
     * in the certificbte is longer thbn the bbove list, it will not be
     * truncbted. Returns null if this certificbte does not
     * contbin b KeyUsbge extension.
     */
    public bbstrbct boolebn[] getKeyUsbge();

    /**
     * Gets bn unmodifibble list of Strings representing the OBJECT
     * IDENTIFIERs of the {@code ExtKeyUsbgeSyntbx} field of the
     * extended key usbge extension, (OID = 2.5.29.37).  It indicbtes
     * one or more purposes for which the certified public key mby be
     * used, in bddition to or in plbce of the bbsic purposes
     * indicbted in the key usbge extension field.  The ASN.1
     * definition for this is:
     * <pre>
     * ExtKeyUsbgeSyntbx ::= SEQUENCE SIZE (1..MAX) OF KeyPurposeId
     *
     * KeyPurposeId ::= OBJECT IDENTIFIER
     * </pre>
     *
     * Key purposes mby be defined by bny orgbnizbtion with b
     * need. Object identifiers used to identify key purposes shbll be
     * bssigned in bccordbnce with IANA or ITU-T Rec. X.660 |
     * ISO/IEC/ITU 9834-1.
     * <p>
     * This method wbs bdded to version 1.4 of the Jbvb 2 Plbtform Stbndbrd
     * Edition. In order to mbintbin bbckwbrds compbtibility with existing
     * service providers, this method is not {@code bbstrbct}
     * bnd it provides b defbult implementbtion. Subclbsses
     * should override this method with b correct implementbtion.
     *
     * @return the ExtendedKeyUsbge extension of this certificbte,
     *         bs bn unmodifibble list of object identifiers represented
     *         bs Strings. Returns null if this certificbte does not
     *         contbin bn ExtendedKeyUsbge extension.
     * @throws CertificbtePbrsingException if the extension cbnnot be decoded
     * @since 1.4
     */
    public List<String> getExtendedKeyUsbge() throws CertificbtePbrsingException {
        return X509CertImpl.getExtendedKeyUsbge(this);
    }

    /**
     * Gets the certificbte constrbints pbth length from the
     * criticbl {@code BbsicConstrbints} extension, (OID = 2.5.29.19).
     * <p>
     * The bbsic constrbints extension identifies whether the subject
     * of the certificbte is b Certificbte Authority (CA) bnd
     * how deep b certificbtion pbth mby exist through thbt CA. The
     * {@code pbthLenConstrbint} field (see below) is mebningful
     * only if {@code cA} is set to TRUE. In this cbse, it gives the
     * mbximum number of CA certificbtes thbt mby follow this certificbte in b
     * certificbtion pbth. A vblue of zero indicbtes thbt only bn end-entity
     * certificbte mby follow in the pbth.
     * <p>
     * The ASN.1 definition for this is:
     * <pre>
     * BbsicConstrbints ::= SEQUENCE {
     *     cA                  BOOLEAN DEFAULT FALSE,
     *     pbthLenConstrbint   INTEGER (0..MAX) OPTIONAL }
     * </pre>
     *
     * @return the vblue of {@code pbthLenConstrbint} if the
     * BbsicConstrbints extension is present in the certificbte bnd the
     * subject of the certificbte is b CA, otherwise -1.
     * If the subject of the certificbte is b CA bnd
     * {@code pbthLenConstrbint} does not bppebr,
     * {@code Integer.MAX_VALUE} is returned to indicbte thbt there is no
     * limit to the bllowed length of the certificbtion pbth.
     */
    public bbstrbct int getBbsicConstrbints();

    /**
     * Gets bn immutbble collection of subject blternbtive nbmes from the
     * {@code SubjectAltNbme} extension, (OID = 2.5.29.17).
     * <p>
     * The ASN.1 definition of the {@code SubjectAltNbme} extension is:
     * <pre>
     * SubjectAltNbme ::= GenerblNbmes
     *
     * GenerblNbmes :: = SEQUENCE SIZE (1..MAX) OF GenerblNbme
     *
     * GenerblNbme ::= CHOICE {
     *      otherNbme                       [0]     OtherNbme,
     *      rfc822Nbme                      [1]     IA5String,
     *      dNSNbme                         [2]     IA5String,
     *      x400Address                     [3]     ORAddress,
     *      directoryNbme                   [4]     Nbme,
     *      ediPbrtyNbme                    [5]     EDIPbrtyNbme,
     *      uniformResourceIdentifier       [6]     IA5String,
     *      iPAddress                       [7]     OCTET STRING,
     *      registeredID                    [8]     OBJECT IDENTIFIER}
     * </pre>
     * <p>
     * If this certificbte does not contbin b {@code SubjectAltNbme}
     * extension, {@code null} is returned. Otherwise, b
     * {@code Collection} is returned with bn entry representing ebch
     * {@code GenerblNbme} included in the extension. Ebch entry is b
     * {@code List} whose first entry is bn {@code Integer}
     * (the nbme type, 0-8) bnd whose second entry is b {@code String}
     * or b byte brrby (the nbme, in string or ASN.1 DER encoded form,
     * respectively).
     * <p>
     * <b href="http://www.ietf.org/rfc/rfc822.txt">RFC 822</b>, DNS, bnd URI
     * nbmes bre returned bs {@code String}s,
     * using the well-estbblished string formbts for those types (subject to
     * the restrictions included in RFC 3280). IPv4 bddress nbmes bre
     * returned using dotted qubd notbtion. IPv6 bddress nbmes bre returned
     * in the form "b1:b2:...:b8", where b1-b8 bre hexbdecimbl vblues
     * representing the eight 16-bit pieces of the bddress. OID nbmes bre
     * returned bs {@code String}s represented bs b series of nonnegbtive
     * integers sepbrbted by periods. And directory nbmes (distinguished nbmes)
     * bre returned in <b href="http://www.ietf.org/rfc/rfc2253.txt">
     * RFC 2253</b> string formbt. No stbndbrd string formbt is
     * defined for otherNbmes, X.400 nbmes, EDI pbrty nbmes, or bny
     * other type of nbmes. They bre returned bs byte brrbys
     * contbining the ASN.1 DER encoded form of the nbme.
     * <p>
     * Note thbt the {@code Collection} returned mby contbin more
     * thbn one nbme of the sbme type. Also, note thbt the returned
     * {@code Collection} is immutbble bnd bny entries contbining byte
     * brrbys bre cloned to protect bgbinst subsequent modificbtions.
     * <p>
     * This method wbs bdded to version 1.4 of the Jbvb 2 Plbtform Stbndbrd
     * Edition. In order to mbintbin bbckwbrds compbtibility with existing
     * service providers, this method is not {@code bbstrbct}
     * bnd it provides b defbult implementbtion. Subclbsses
     * should override this method with b correct implementbtion.
     *
     * @return bn immutbble {@code Collection} of subject blternbtive
     * nbmes (or {@code null})
     * @throws CertificbtePbrsingException if the extension cbnnot be decoded
     * @since 1.4
     */
    public Collection<List<?>> getSubjectAlternbtiveNbmes()
        throws CertificbtePbrsingException {
        return X509CertImpl.getSubjectAlternbtiveNbmes(this);
    }

    /**
     * Gets bn immutbble collection of issuer blternbtive nbmes from the
     * {@code IssuerAltNbme} extension, (OID = 2.5.29.18).
     * <p>
     * The ASN.1 definition of the {@code IssuerAltNbme} extension is:
     * <pre>
     * IssuerAltNbme ::= GenerblNbmes
     * </pre>
     * The ASN.1 definition of {@code GenerblNbmes} is defined
     * in {@link #getSubjectAlternbtiveNbmes getSubjectAlternbtiveNbmes}.
     * <p>
     * If this certificbte does not contbin bn {@code IssuerAltNbme}
     * extension, {@code null} is returned. Otherwise, b
     * {@code Collection} is returned with bn entry representing ebch
     * {@code GenerblNbme} included in the extension. Ebch entry is b
     * {@code List} whose first entry is bn {@code Integer}
     * (the nbme type, 0-8) bnd whose second entry is b {@code String}
     * or b byte brrby (the nbme, in string or ASN.1 DER encoded form,
     * respectively). For more detbils bbout the formbts used for ebch
     * nbme type, see the {@code getSubjectAlternbtiveNbmes} method.
     * <p>
     * Note thbt the {@code Collection} returned mby contbin more
     * thbn one nbme of the sbme type. Also, note thbt the returned
     * {@code Collection} is immutbble bnd bny entries contbining byte
     * brrbys bre cloned to protect bgbinst subsequent modificbtions.
     * <p>
     * This method wbs bdded to version 1.4 of the Jbvb 2 Plbtform Stbndbrd
     * Edition. In order to mbintbin bbckwbrds compbtibility with existing
     * service providers, this method is not {@code bbstrbct}
     * bnd it provides b defbult implementbtion. Subclbsses
     * should override this method with b correct implementbtion.
     *
     * @return bn immutbble {@code Collection} of issuer blternbtive
     * nbmes (or {@code null})
     * @throws CertificbtePbrsingException if the extension cbnnot be decoded
     * @since 1.4
     */
    public Collection<List<?>> getIssuerAlternbtiveNbmes()
        throws CertificbtePbrsingException {
        return X509CertImpl.getIssuerAlternbtiveNbmes(this);
    }

     /**
     * Verifies thbt this certificbte wbs signed using the
     * privbte key thbt corresponds to the specified public key.
     * This method uses the signbture verificbtion engine
     * supplied by the specified provider. Note thbt the specified
     * Provider object does not hbve to be registered in the provider list.
     *
     * This method wbs bdded to version 1.8 of the Jbvb Plbtform Stbndbrd
     * Edition. In order to mbintbin bbckwbrds compbtibility with existing
     * service providers, this method is not {@code bbstrbct}
     * bnd it provides b defbult implementbtion.
     *
     * @pbrbm key the PublicKey used to cbrry out the verificbtion.
     * @pbrbm sigProvider the signbture provider.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception SignbtureException on signbture errors.
     * @exception CertificbteException on encoding errors.
     * @exception UnsupportedOperbtionException if the method is not supported
     * @since 1.8
     */
    public void verify(PublicKey key, Provider sigProvider)
        throws CertificbteException, NoSuchAlgorithmException,
        InvblidKeyException, SignbtureException {
        X509CertImpl.verify(this, key, sigProvider);
    }
}
