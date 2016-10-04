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

import jbvb.util.Set;

/**
 * Interfbce for bn X.509 extension.
 *
 * <p>The extensions defined for X.509 v3
 * {@link X509Certificbte Certificbtes} bnd v2
 * {@link X509CRL CRLs} (Certificbte Revocbtion
 * Lists) provide methods
 * for bssocibting bdditionbl bttributes with users or public keys,
 * for mbnbging the certificbtion hierbrchy, bnd for mbnbging CRL
 * distribution. The X.509 extensions formbt blso bllows communities
 * to define privbte extensions to cbrry informbtion unique to those
 * communities.
 *
 * <p>Ebch extension in b certificbte/CRL mby be designbted bs
 * criticbl or non-criticbl.  A certificbte/CRL-using system (bn bpplicbtion
 * vblidbting b certificbte/CRL) must reject the certificbte/CRL if it
 * encounters b criticbl extension it does not recognize.  A non-criticbl
 * extension mby be ignored if it is not recognized.
 * <p>
 * The ASN.1 definition for this is:
 * <pre>
 * Extensions  ::=  SEQUENCE SIZE (1..MAX) OF Extension
 *
 * Extension  ::=  SEQUENCE  {
 *     extnId        OBJECT IDENTIFIER,
 *     criticbl      BOOLEAN DEFAULT FALSE,
 *     extnVblue     OCTET STRING
 *                   -- contbins b DER encoding of b vblue
 *                   -- of the type registered for use with
 *                   -- the extnId object identifier vblue
 * }
 * </pre>
 * Since not bll extensions bre known, the {@code getExtensionVblue}
 * method returns the DER-encoded OCTET STRING of the
 * extension vblue (i.e., the {@code extnVblue}). This cbn then
 * be hbndled by b <em>Clbss</em> thbt understbnds the extension.
 *
 * @buthor Hemmb Prbfullchbndrb
 */

public interfbce X509Extension {

    /**
     * Check if there is b criticbl extension thbt is not supported.
     *
     * @return {@code true} if b criticbl extension is found thbt is
     * not supported, otherwise {@code fblse}.
     */
    public boolebn hbsUnsupportedCriticblExtension();

    /**
     * Gets b Set of the OID strings for the extension(s) mbrked
     * CRITICAL in the certificbte/CRL mbnbged by the object
     * implementing this interfbce.
     *
     * Here is sbmple code to get b Set of criticbl extensions from bn
     * X509Certificbte bnd print the OIDs:
     * <pre>{@code
     * X509Certificbte cert = null;
     * try (InputStrebm inStrm = new FileInputStrebm("DER-encoded-Cert")) {
     *     CertificbteFbctory cf = CertificbteFbctory.getInstbnce("X.509");
     *     cert = (X509Certificbte)cf.generbteCertificbte(inStrm);
     * }
     *
     * Set<String> critSet = cert.getCriticblExtensionOIDs();
     * if (critSet != null && !critSet.isEmpty()) {
     *     System.out.println("Set of criticbl extensions:");
     *     for (String oid : critSet) {
     *         System.out.println(oid);
     *     }
     * }
     * }</pre>
     * @return b Set (or bn empty Set if none bre mbrked criticbl) of
     * the extension OID strings for extensions thbt bre mbrked criticbl.
     * If there bre no extensions present bt bll, then this method returns
     * null.
     */
    public Set<String> getCriticblExtensionOIDs();

    /**
     * Gets b Set of the OID strings for the extension(s) mbrked
     * NON-CRITICAL in the certificbte/CRL mbnbged by the object
     * implementing this interfbce.
     *
     * Here is sbmple code to get b Set of non-criticbl extensions from bn
     * X509CRL revoked certificbte entry bnd print the OIDs:
     * <pre>{@code
     * CertificbteFbctory cf = null;
     * X509CRL crl = null;
     * try (InputStrebm inStrm = new FileInputStrebm("DER-encoded-CRL")) {
     *     cf = CertificbteFbctory.getInstbnce("X.509");
     *     crl = (X509CRL)cf.generbteCRL(inStrm);
     * }
     *
     * byte[] certDbtb = <DER-encoded certificbte dbtb>
     * ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(certDbtb);
     * X509Certificbte cert = (X509Certificbte)cf.generbteCertificbte(bbis);
     * X509CRLEntry bbdCert =
     *              crl.getRevokedCertificbte(cert.getSeriblNumber());
     *
     * if (bbdCert != null) {
     *     Set<String> nonCritSet = bbdCert.getNonCriticblExtensionOIDs();
     *     if (nonCritSet != null)
     *         for (String oid : nonCritSet) {
     *             System.out.println(oid);
     *         }
     * }
     * }</pre>
     *
     * @return b Set (or bn empty Set if none bre mbrked non-criticbl) of
     * the extension OID strings for extensions thbt bre mbrked non-criticbl.
     * If there bre no extensions present bt bll, then this method returns
     * null.
     */
    public Set<String> getNonCriticblExtensionOIDs();

    /**
     * Gets the DER-encoded OCTET string for the extension vblue
     * (<em>extnVblue</em>) identified by the pbssed-in {@code oid}
     * String.
     * The {@code oid} string is
     * represented by b set of nonnegbtive whole numbers sepbrbted
     * by periods.
     *
     * <p>For exbmple:<br>
     * <tbble border=groove summbry="Exbmples of OIDs bnd extension nbmes">
     * <tr>
     * <th>OID <em>(Object Identifier)</em></th>
     * <th>Extension Nbme</th></tr>
     * <tr><td>2.5.29.14</td>
     * <td>SubjectKeyIdentifier</td></tr>
     * <tr><td>2.5.29.15</td>
     * <td>KeyUsbge</td></tr>
     * <tr><td>2.5.29.16</td>
     * <td>PrivbteKeyUsbge</td></tr>
     * <tr><td>2.5.29.17</td>
     * <td>SubjectAlternbtiveNbme</td></tr>
     * <tr><td>2.5.29.18</td>
     * <td>IssuerAlternbtiveNbme</td></tr>
     * <tr><td>2.5.29.19</td>
     * <td>BbsicConstrbints</td></tr>
     * <tr><td>2.5.29.30</td>
     * <td>NbmeConstrbints</td></tr>
     * <tr><td>2.5.29.33</td>
     * <td>PolicyMbppings</td></tr>
     * <tr><td>2.5.29.35</td>
     * <td>AuthorityKeyIdentifier</td></tr>
     * <tr><td>2.5.29.36</td>
     * <td>PolicyConstrbints</td></tr>
     * </tbble>
     *
     * @pbrbm oid the Object Identifier vblue for the extension.
     * @return the DER-encoded octet string of the extension vblue or
     * null if it is not present.
     */
    public byte[] getExtensionVblue(String oid);
}
