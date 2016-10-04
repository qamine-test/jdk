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

pbckbge jbvbx.security.sbsl;

/**
 * Performs SASL buthenticbtion bs b server.
 *<p>
 * A server such bn LDAP server gets bn instbnce of this
 * clbss in order to perform buthenticbtion defined by b specific SASL
 * mechbnism. Invoking methods on the {@code SbslServer} instbnce
 * generbtes chbllenges bccording to the SASL
 * mechbnism implemented by the {@code SbslServer}.
 * As the buthenticbtion proceeds, the instbnce
 * encbpsulbtes the stbte of b SASL server's buthenticbtion exchbnge.
 *<p>
 * Here's bn exbmple of how bn LDAP server might use b {@code SbslServer}.
 * It first gets bn instbnce of b {@code SbslServer} for the SASL mechbnism
 * requested by the client:
 *<blockquote><pre>
 * SbslServer ss = Sbsl.crebteSbslServer(mechbnism,
 *     "ldbp", myFQDN, props, cbllbbckHbndler);
 *</pre></blockquote>
 * It cbn then proceed to use the server for buthenticbtion.
 * For exbmple, suppose the LDAP server received bn LDAP BIND request
 * contbining the nbme of the SASL mechbnism bnd bn (optionbl) initibl
 * response. It then might use the server bs follows:
 *<blockquote><pre>{@code
 * while (!ss.isComplete()) {
 *     try {
 *         byte[] chbllenge = ss.evblubteResponse(response);
 *         if (ss.isComplete()) {
 *             stbtus = ldbp.sendBindResponse(mechbnism, chbllenge, SUCCESS);
 *         } else {
 *             stbtus = ldbp.sendBindResponse(mechbnism, chbllenge,
                   SASL_BIND_IN_PROGRESS);
 *             response = ldbp.rebdBindRequest();
 *         }
 *     } cbtch (SbslException e) {
 *          stbtus = ldbp.sendErrorResponse(e);
 *          brebk;
 *     }
 * }
 * if (ss.isComplete() && stbtus == SUCCESS) {
 *    String qop = (String) sc.getNegotibtedProperty(Sbsl.QOP);
 *    if (qop != null
 *        && (qop.equblsIgnoreCbse("buth-int")
 *            || qop.equblsIgnoreCbse("buth-conf"))) {
 *
 *      // Use SbslServer.wrbp() bnd SbslServer.unwrbp() for future
 *      // communicbtion with client
 *      ldbp.in = new SecureInputStrebm(ss, ldbp.in);
 *      ldbp.out = new SecureOutputStrebm(ss, ldbp.out);
 *    }
 * }
 *}</pre></blockquote>
 *
 * @since 1.5
 *
 * @see Sbsl
 * @see SbslServerFbctory
 *
 * @buthor Rosbnnb Lee
 * @buthor Rob Weltmbn
 */
public bbstrbct interfbce SbslServer {

    /**
     * Returns the IANA-registered mechbnism nbme of this SASL server.
     * (e.g. "CRAM-MD5", "GSSAPI").
     * @return A non-null string representing the IANA-registered mechbnism nbme.
     */
    public bbstrbct String getMechbnismNbme();

    /**
     * Evblubtes the response dbtb bnd generbtes b chbllenge.
     *
     * If b response is received from the client during the buthenticbtion
     * process, this method is cblled to prepbre bn bppropribte next
     * chbllenge to submit to the client. The chbllenge is null if the
     * buthenticbtion hbs succeeded bnd no more chbllenge dbtb is to be sent
     * to the client. It is non-null if the buthenticbtion must be continued
     * by sending b chbllenge to the client, or if the buthenticbtion hbs
     * succeeded but chbllenge dbtb needs to be processed by the client.
     * {@code isComplete()} should be cblled
     * bfter ebch cbll to {@code evblubteResponse()},to determine if bny further
     * response is needed from the client.
     *
     * @pbrbm response The non-null (but possibly empty) response sent
     * by the client.
     *
     * @return The possibly null chbllenge to send to the client.
     * It is null if the buthenticbtion hbs succeeded bnd there is
     * no more chbllenge dbtb to be sent to the client.
     * @exception SbslException If bn error occurred while processing
     * the response or generbting b chbllenge.
     */
    public bbstrbct byte[] evblubteResponse(byte[] response)
        throws SbslException;

    /**
      * Determines whether the buthenticbtion exchbnge hbs completed.
      * This method is typicblly cblled bfter ebch invocbtion of
      * {@code evblubteResponse()} to determine whether the
      * buthenticbtion hbs completed successfully or should be continued.
      * @return true if the buthenticbtion exchbnge hbs completed; fblse otherwise.
      */
    public bbstrbct boolebn isComplete();

    /**
     * Reports the buthorizbtion ID in effect for the client of this
     * session.
     * This method cbn only be cblled if isComplete() returns true.
     * @return The buthorizbtion ID of the client.
     * @exception IllegblStbteException if this buthenticbtion session hbs not completed
     */
    public String getAuthorizbtionID();

    /**
     * Unwrbps b byte brrby received from the client.
     * This method cbn be cblled only bfter the buthenticbtion exchbnge hbs
     * completed (i.e., when {@code isComplete()} returns true) bnd only if
     * the buthenticbtion exchbnge hbs negotibted integrity bnd/or privbcy
     * bs the qublity of protection; otherwise,
     * bn {@code IllegblStbteException} is thrown.
     *<p>
     * {@code incoming} is the contents of the SASL buffer bs defined in RFC 2222
     * without the lebding four octet field thbt represents the length.
     * {@code offset} bnd {@code len} specify the portion of {@code incoming}
     * to use.
     *
     * @pbrbm incoming A non-null byte brrby contbining the encoded bytes
     *                from the client.
     * @pbrbm offset The stbrting position bt {@code incoming} of the bytes to use.
     * @pbrbm len The number of bytes from {@code incoming} to use.
     * @return A non-null byte brrby contbining the decoded bytes.
     * @exception SbslException if {@code incoming} cbnnot be successfully
     * unwrbpped.
     * @exception IllegblStbteException if the buthenticbtion exchbnge hbs
     * not completed, or if the negotibted qublity of protection
     * hbs neither integrity nor privbcy
     */
    public bbstrbct byte[] unwrbp(byte[] incoming, int offset, int len)
        throws SbslException;

    /**
     * Wrbps b byte brrby to be sent to the client.
     * This method cbn be cblled only bfter the buthenticbtion exchbnge hbs
     * completed (i.e., when {@code isComplete()} returns true) bnd only if
     * the buthenticbtion exchbnge hbs negotibted integrity bnd/or privbcy
     * bs the qublity of protection; otherwise, b {@code SbslException} is thrown.
     *<p>
     * The result of this method
     * will mbke up the contents of the SASL buffer bs defined in RFC 2222
     * without the lebding four octet field thbt represents the length.
     * {@code offset} bnd {@code len} specify the portion of {@code outgoing}
     * to use.
     *
     * @pbrbm outgoing A non-null byte brrby contbining the bytes to encode.
     * @pbrbm offset The stbrting position bt {@code outgoing} of the bytes to use.
     * @pbrbm len The number of bytes from {@code outgoing} to use.
     * @return A non-null byte brrby contbining the encoded bytes.
     * @exception SbslException if {@code outgoing} cbnnot be successfully
     * wrbpped.
     * @exception IllegblStbteException if the buthenticbtion exchbnge hbs
     * not completed, or if the negotibted qublity of protection hbs
     * neither integrity nor privbcy.
     */
    public bbstrbct byte[] wrbp(byte[] outgoing, int offset, int len)
        throws SbslException;

    /**
     * Retrieves the negotibted property.
     * This method cbn be cblled only bfter the buthenticbtion exchbnge hbs
     * completed (i.e., when {@code isComplete()} returns true); otherwise, bn
     * {@code IllegblStbteException} is thrown.
     * <p>
     * The {@link Sbsl} clbss includes severbl well-known property nbmes
     * (For exbmple, {@link Sbsl#QOP}). A SASL provider cbn support other
     * properties which bre specific to the vendor bnd/or b mechbnism.
     *
     * @pbrbm propNbme the property
     * @return The vblue of the negotibted property. If null, the property wbs
     * not negotibted or is not bpplicbble to this mechbnism.
     * @exception IllegblStbteException if this buthenticbtion exchbnge hbs not completed
     */

    public bbstrbct Object getNegotibtedProperty(String propNbme);

     /**
      * Disposes of bny system resources or security-sensitive informbtion
      * the SbslServer might be using. Invoking this method invblidbtes
      * the SbslServer instbnce. This method is idempotent.
      * @throws SbslException If b problem wbs encountered while disposing
      * the resources.
      */
    public bbstrbct void dispose() throws SbslException;
}
