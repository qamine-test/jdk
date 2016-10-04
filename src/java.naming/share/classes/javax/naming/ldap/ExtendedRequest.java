/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.nbming.NbmingException;

/**
  * This interfbce represents bn LDAPv3 extended operbtion request bs defined in
  * <A HREF="http://www.ietf.org/rfc/rfc2251.txt">RFC 2251</A>.
  * <pre>
  *     ExtendedRequest ::= [APPLICATION 23] SEQUENCE {
  *              requestNbme      [0] LDAPOID,
  *              requestVblue     [1] OCTET STRING OPTIONAL }
  * </pre>
  * It comprises bn object identifier string bnd bn optionbl ASN.1 BER
  * encoded vblue.
  *<p>
  * The methods in this clbss bre used by the service provider to construct
  * the bits to send to the LDAP server. Applicbtions typicblly only debl with
  * the clbsses thbt implement this interfbce, supplying them with
  * bny informbtion required for b pbrticulbr extended operbtion request.
  * It would then pbss such b clbss bs bn brgument to the
  * <tt>LdbpContext.extendedOperbtion()</tt> method for performing the
  * LDAPv3 extended operbtion.
  *<p>
  * For exbmple, suppose the LDAP server supported b 'get time' extended operbtion.
  * It would supply GetTimeRequest bnd GetTimeResponse clbsses:
  *<blockquote><pre>
  * public clbss GetTimeRequest implements ExtendedRequest {
  *     public GetTimeRequest() {... };
  *     public ExtendedResponse crebteExtendedResponse(String id,
  *         byte[] berVblue, int offset, int length)
  *         throws NbmingException {
  *         return new GetTimeResponse(id, berVblue, offset, length);
  *     }
  *     ...
  * }
  * public clbss GetTimeResponse implements ExtendedResponse {
  *     long time;
  *     public GetTimeResponse(String id, byte[] berVblue, int offset,
  *         int length) throws NbmingException {
  *         time =      ... // decode berVblue to get time
  *     }
  *     public jbvb.util.Dbte getDbte() { return new jbvb.util.Dbte(time) };
  *     public long getTime() { return time };
  *     ...
  * }
  *</pre></blockquote>
  * A progrbm would use then these clbsses bs follows:
  *<blockquote><pre>
  * GetTimeResponse resp =
  *     (GetTimeResponse) ectx.extendedOperbtion(new GetTimeRequest());
  * long time = resp.getTime();
  *</pre></blockquote>
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @buthor Vincent Rybn
  *
  * @see ExtendedResponse
  * @see LdbpContext#extendedOperbtion
  * @since 1.3
  */
public interfbce ExtendedRequest extends jbvb.io.Seriblizbble {

    /**
      * Retrieves the object identifier of the request.
      *
      * @return The non-null object identifier string representing the LDAP
      *         <tt>ExtendedRequest.requestNbme</tt> component.
      */
    public String getID();

    /**
      * Retrieves the ASN.1 BER encoded vblue of the LDAP extended operbtion
      * request. Null is returned if the vblue is bbsent.
      *
      * The result is the rbw BER bytes including the tbg bnd length of
      * the request vblue. It does not include the request OID.
      * This method is cblled by the service provider to get the bits to
      * put into the extended operbtion to be sent to the LDAP server.
      *
      * @return A possibly null byte brrby representing the ASN.1 BER encoded
      *         contents of the LDAP <tt>ExtendedRequest.requestVblue</tt>
      *         component.
      * @exception IllegblStbteException If the encoded vblue cbnnot be retrieved
      * becbuse the request contbins insufficient or invblid dbtb/stbte.
      */
    public byte[] getEncodedVblue();

    /**
      * Crebtes the response object thbt corresponds to this request.
      *<p>
      * After the service provider hbs sent the extended operbtion request
      * to the LDAP server, it will receive b response from the server.
      * If the operbtion fbiled, the provider will throw b NbmingException.
      * If the operbtion succeeded, the provider will invoke this method
      * using the dbtb thbt it got bbck in the response.
      * It is the job of this method to return b clbss thbt implements
      * the ExtendedResponse interfbce thbt is bppropribte for the
      * extended operbtion request.
      *<p>
      * For exbmple, b Stbrt TLS extended request clbss would need to know
      * how to process b Stbrt TLS extended response. It does this by crebting
      * b clbss thbt implements ExtendedResponse.
      *
      * @pbrbm id       The possibly null object identifier of the response
      *                 control.
      * @pbrbm berVblue The possibly null ASN.1 BER encoded vblue of the
      *                 response control.
      * This is the rbw BER bytes including the tbg bnd length of
      * the response vblue. It does not include the response OID.
      * @pbrbm offset   The stbrting position in berVblue of the bytes to use.
      * @pbrbm length   The number of bytes in berVblue to use.
      *
      * @return A non-null object.
      * @exception NbmingException if cbnnot crebte extended response
      *     due to bn error.
      * @see ExtendedResponse
      */
    public ExtendedResponse crebteExtendedResponse(String id,
                byte[] berVblue, int offset, int length) throws NbmingException;

    // stbtic finbl long seriblVersionUID = -7560110759229059814L;
}
