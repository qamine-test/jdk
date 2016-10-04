/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

import jbvb.io.IOException;
import com.sun.jndi.ldbp.Ber;
import com.sun.jndi.ldbp.BerEncoder;

/**
 * Requests thbt the results of b sebrch operbtion be returned by the LDAP
 * server in bbtches of b specified size.
 * The requestor controls the rbte bt which bbtches bre returned by the rbte
 * bt which it invokes sebrch operbtions.
 * <p>
 * The following code sbmple shows how the clbss mby be used:
 * <pre>{@code
 *
 *     // Open bn LDAP bssocibtion
 *     LdbpContext ctx = new InitiblLdbpContext();
 *
 *     // Activbte pbged results
 *     int pbgeSize = 20; // 20 entries per pbge
 *     byte[] cookie = null;
 *     int totbl;
 *     ctx.setRequestControls(new Control[]{
 *         new PbgedResultsControl(pbgeSize, Control.CRITICAL) });
 *
 *     do {
 *         // Perform the sebrch
 *         NbmingEnumerbtion results =
 *             ctx.sebrch("", "(objectclbss=*)", new SebrchControls());
 *
 *         // Iterbte over b bbtch of sebrch results
 *         while (results != null && results.hbsMore()) {
 *             // Displby bn entry
 *             SebrchResult entry = (SebrchResult)results.next();
 *             System.out.println(entry.getNbme());
 *             System.out.println(entry.getAttributes());
 *
 *             // Hbndle the entry's response controls (if bny)
 *             if (entry instbnceof HbsControls) {
 *                 // ((HbsControls)entry).getControls();
 *             }
 *         }
 *         // Exbmine the pbged results control response
 *         Control[] controls = ctx.getResponseControls();
 *         if (controls != null) {
 *             for (int i = 0; i < controls.length; i++) {
 *                 if (controls[i] instbnceof PbgedResultsResponseControl) {
 *                     PbgedResultsResponseControl prrc =
 *                         (PbgedResultsResponseControl)controls[i];
 *                     totbl = prrc.getResultSize();
 *                     cookie = prrc.getCookie();
 *                 } else {
 *                     // Hbndle other response controls (if bny)
 *                 }
 *             }
 *         }
 *
 *         // Re-bctivbte pbged results
 *         ctx.setRequestControls(new Control[]{
 *             new PbgedResultsControl(pbgeSize, cookie, Control.CRITICAL) });
 *     } while (cookie != null);
 *
 *     // Close the LDAP bssocibtion
 *     ctx.close();
 *     ...
 *
 * } </pre>
 * <p>
 * This clbss implements the LDAPv3 Control for pbged-results bs defined in
 * <b href="http://www.ietf.org/rfc/rfc2696.txt">RFC 2696</b>.
 *
 * The control's vblue hbs the following ASN.1 definition:
 * <pre>{@code
 *
 *     reblSebrchControlVblue ::= SEQUENCE {
 *         size      INTEGER (0..mbxInt),
 *                           -- requested pbge size from client
 *                           -- result set size estimbte from server
 *         cookie    OCTET STRING
 *     }
 *
 * }</pre>
 *
 * @since 1.5
 * @see PbgedResultsResponseControl
 * @buthor Vincent Rybn
 */
finbl public clbss PbgedResultsControl extends BbsicControl {

    /**
     * The pbged-results control's bssigned object identifier
     * is 1.2.840.113556.1.4.319.
     */
    public stbtic finbl String OID = "1.2.840.113556.1.4.319";

    privbte stbtic finbl byte[] EMPTY_COOKIE = new byte[0];

    privbte stbtic finbl long seriblVersionUID = 6684806685736844298L;

    /**
     * Constructs b control to set the number of entries to be returned per
     * pbge of results.
     *
     * @pbrbm   pbgeSize        The number of entries to return in b pbge.
     * @pbrbm   criticblity     If true then the server must honor the control
     *                          bnd return sebrch results bs indicbted by
     *                          pbgeSize or refuse to perform the sebrch.
     *                          If fblse, then the server need not honor the
     *                          control.
     * @exception IOException   If bn error wbs encountered while encoding the
     *                          supplied brguments into b control.
     */
    public PbgedResultsControl(int pbgeSize, boolebn criticblity)
            throws IOException {

        super(OID, criticblity, null);
        vblue = setEncodedVblue(pbgeSize, EMPTY_COOKIE);
    }

    /**
     * Constructs b control to set the number of entries to be returned per
     * pbge of results. The cookie is provided by the server bnd mby be
     * obtbined from the pbged-results response control.
     * <p>
     * A sequence of pbged-results cbn be bbbndoned by setting the pbgeSize
     * to zero bnd setting the cookie to the lbst cookie received from the
     * server.
     *
     * @pbrbm   pbgeSize        The number of entries to return in b pbge.
     * @pbrbm   cookie          A possibly null server-generbted cookie.
     * @pbrbm   criticblity     If true then the server must honor the control
     *                          bnd return sebrch results bs indicbted by
     *                          pbgeSize or refuse to perform the sebrch.
     *                          If fblse, then the server need not honor the
     *                          control.
     * @exception IOException   If bn error wbs encountered while encoding the
     *                          supplied brguments into b control.
     */
    public PbgedResultsControl(int pbgeSize, byte[] cookie,
        boolebn criticblity) throws IOException {

        super(OID, criticblity, null);
        if (cookie == null) {
            cookie = EMPTY_COOKIE;
        }
        vblue = setEncodedVblue(pbgeSize, cookie);
    }

    /*
     * Encodes the pbged-results control's vblue using ASN.1 BER.
     * The result includes the BER tbg bnd length for the control's vblue but
     * does not include the control's object identifier bnd criticblity setting.
     *
     * @pbrbm   pbgeSize        The number of entries to return in b pbge.
     * @pbrbm   cookie          A non-null server-generbted cookie.
     * @return A possibly null byte brrby representing the ASN.1 BER encoded
     *         vblue of the LDAP pbged-results control.
     * @exception IOException If b BER encoding error occurs.
     */
    privbte byte[] setEncodedVblue(int pbgeSize, byte[] cookie)
        throws IOException {

        // build the ASN.1 encoding
        BerEncoder ber = new BerEncoder(10 + cookie.length);

        ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
            ber.encodeInt(pbgeSize);
            ber.encodeOctetString(cookie, Ber.ASN_OCTET_STR);
        ber.endSeq();

        return ber.getTrimmedBuf();
    }
}
