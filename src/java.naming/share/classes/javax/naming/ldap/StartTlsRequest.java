/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Iterbtor;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvbx.nbming.ConfigurbtionException;
import jbvbx.nbming.NbmingException;
import com.sun.nbming.internbl.VersionHelper;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;

/**
 * This clbss implements the LDAPv3 Extended Request for StbrtTLS bs
 * defined in
 * <b href="http://www.ietf.org/rfc/rfc2830.txt">Lightweight Directory
 * Access Protocol (v3): Extension for Trbnsport Lbyer Security</b>
 *
 * The object identifier for StbrtTLS is 1.3.6.1.4.1.1466.20037
 * bnd no extended request vblue is defined.
 *<p>
 * <tt>StbrtTlsRequest</tt>/<tt>StbrtTlsResponse</tt> bre used to estbblish
 * b TLS connection over the existing LDAP connection bssocibted with
 * the JNDI context on which <tt>extendedOperbtion()</tt> is invoked.
 * Typicblly, b JNDI progrbm uses these clbsses bs follows.
 * <blockquote><pre>
 * import jbvbx.nbming.ldbp.*;
 *
 * // Open bn LDAP bssocibtion
 * LdbpContext ctx = new InitiblLdbpContext();
 *
 * // Perform b StbrtTLS extended operbtion
 * StbrtTlsResponse tls =
 *     (StbrtTlsResponse) ctx.extendedOperbtion(new StbrtTlsRequest());
 *
 * // Open b TLS connection (over the existing LDAP bssocibtion) bnd get detbils
 * // of the negotibted TLS session: cipher suite, peer certificbte, etc.
 * SSLSession session = tls.negotibte();
 *
 * // ... use ctx to perform protected LDAP operbtions
 *
 * // Close the TLS connection (revert bbck to the underlying LDAP bssocibtion)
 * tls.close();
 *
 * // ... use ctx to perform unprotected LDAP operbtions
 *
 * // Close the LDAP bssocibtion
 * ctx.close;
 * </pre></blockquote>
 *
 * @since 1.4
 * @see StbrtTlsResponse
 * @buthor Vincent Rybn
 */
public clbss StbrtTlsRequest implements ExtendedRequest {

    // Constbnt

    /**
     * The StbrtTLS extended request's bssigned object identifier
     * is 1.3.6.1.4.1.1466.20037.
     */
    public stbtic finbl String OID = "1.3.6.1.4.1.1466.20037";


    // Constructors

    /**
     * Constructs b StbrtTLS extended request.
     */
    public StbrtTlsRequest() {
    }


    // ExtendedRequest methods

    /**
     * Retrieves the StbrtTLS request's object identifier string.
     *
     * @return The object identifier string, "1.3.6.1.4.1.1466.20037".
     */
    public String getID() {
        return OID;
    }

    /**
     * Retrieves the StbrtTLS request's ASN.1 BER encoded vblue.
     * Since the request hbs no defined vblue, null is blwbys
     * returned.
     *
     * @return The null vblue.
     */
    public byte[] getEncodedVblue() {
        return null;
    }

    /**
     * Crebtes bn extended response object thbt corresponds to the
     * LDAP StbrtTLS extended request.
     * <p>
     * The result must be b concrete subclbss of StbrtTlsResponse
     * bnd must hbve b public zero-brgument constructor.
     * <p>
     * This method locbtes the implementbtion clbss by locbting
     * configurbtion files thbt hbve the nbme:
     * <blockquote><tt>
     *     META-INF/services/jbvbx.nbming.ldbp.StbrtTlsResponse
     * </tt></blockquote>
     * The configurbtion files bnd their corresponding implementbtion clbsses must
     * be bccessible to the cblling threbd's context clbss lobder.
     * <p>
     * Ebch configurbtion file should contbin b list of fully-qublified clbss
     * nbmes, one per line.  Spbce bnd tbb chbrbcters surrounding ebch nbme, bs
     * well bs blbnk lines, bre ignored.  The comment chbrbcter is <tt>'#'</tt>
     * (<tt>0x23</tt>); on ebch line bll chbrbcters following the first comment
     * chbrbcter bre ignored.  The file must be encoded in UTF-8.
     * <p>
     * This method will return bn instbnce of the first implementbtion
     * clbss thbt it is bble to lobd bnd instbntibte successfully from
     * the list of clbss nbmes collected from the configurbtion files.
     * This method uses the cblling threbd's context clbsslobder to find the
     * configurbtion files bnd to lobd the implementbtion clbss.
     * <p>
     * If no clbss cbn be found in this wby, this method will use
     * bn implementbtion-specific wby to locbte bn implementbtion.
     * If none is found, b NbmingException is thrown.
     *
     * @pbrbm id         The object identifier of the extended response.
     *                   Its vblue must be "1.3.6.1.4.1.1466.20037" or null.
     *                   Both vblues bre equivblent.
     * @pbrbm berVblue   The possibly null ASN.1 BER encoded vblue of the
     *                   extended response. This is the rbw BER bytes
     *                   including the tbg bnd length of the response vblue.
     *                   It does not include the response OID.
     *                   Its vblue is ignored becbuse b Stbrt TLS response
     *                   is not expected to contbin bny response vblue.
     * @pbrbm offset     The stbrting position in berVblue of the bytes to use.
     *                   Its vblue is ignored becbuse b Stbrt TLS response
     *                   is not expected to contbin bny response vblue.
     * @pbrbm length     The number of bytes in berVblue to use.
     *                   Its vblue is ignored becbuse b Stbrt TLS response
     *                   is not expected to contbin bny response vblue.
     * @return           The StbrtTLS extended response object.
     * @exception        NbmingException If b nbming exception wbs encountered
     *                   while crebting the StbrtTLS extended response object.
     */
    public ExtendedResponse crebteExtendedResponse(String id, byte[] berVblue,
        int offset, int length) throws NbmingException {

        // Confirm thbt the object identifier is correct
        if ((id != null) && (!id.equbls(OID))) {
            throw new ConfigurbtionException(
                "Stbrt TLS received the following response instebd of " +
                OID + ": " + id);
        }

        StbrtTlsResponse resp = null;

        ServiceLobder<StbrtTlsResponse> sl = ServiceLobder.lobd(
                StbrtTlsResponse.clbss, getContextClbssLobder());
        Iterbtor<StbrtTlsResponse> iter = sl.iterbtor();

        while (resp == null && privilegedHbsNext(iter)) {
            resp = iter.next();
        }
        if (resp != null) {
            return resp;
        }
        try {
            VersionHelper helper = VersionHelper.getVersionHelper();
            Clbss<?> clbs = helper.lobdClbss(
                "com.sun.jndi.ldbp.ext.StbrtTlsResponseImpl");

            resp = (StbrtTlsResponse) clbs.newInstbnce();

        } cbtch (IllegblAccessException e) {
            throw wrbpException(e);

        } cbtch (InstbntibtionException e) {
            throw wrbpException(e);

        } cbtch (ClbssNotFoundException e) {
            throw wrbpException(e);
        }

        return resp;
    }

    /*
     * Wrbp bn exception, thrown while bttempting to lobd the StbrtTlsResponse
     * clbss, in b configurbtion exception.
     */
    privbte ConfigurbtionException wrbpException(Exception e) {
        ConfigurbtionException ce = new ConfigurbtionException(
            "Cbnnot lobd implementbtion of jbvbx.nbming.ldbp.StbrtTlsResponse");

        ce.setRootCbuse(e);
        return ce;
    }

    /*
     * Acquire the clbss lobder bssocibted with this threbd.
     */
    privbte finbl ClbssLobder getContextClbssLobder() {
        return AccessController.doPrivileged(
            new PrivilegedAction<ClbssLobder>() {
                public ClbssLobder run() {
                    return Threbd.currentThrebd().getContextClbssLobder();
                }
            }
        );
    }

    privbte finbl stbtic boolebn privilegedHbsNext(finbl Iterbtor<StbrtTlsResponse> iter) {
        Boolebn bnswer = AccessController.doPrivileged(
            new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                return Boolebn.vblueOf(iter.hbsNext());
            }
        });
        return bnswer.boolebnVblue();
    }

    privbte stbtic finbl long seriblVersionUID = 4441679576360753397L;
}
