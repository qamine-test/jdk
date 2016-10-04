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

/**
  * This interfbce represents bn LDAP extended operbtion response bs defined in
  * <A HREF="http://www.ietf.org/rfc/rfc2251.txt">RFC 2251</A>.
  * <pre>
  *     ExtendedResponse ::= [APPLICATION 24] SEQUENCE {
  *          COMPONENTS OF LDAPResult,
  *          responseNbme     [10] LDAPOID OPTIONAL,
  *          response         [11] OCTET STRING OPTIONAL }
  * </pre>
  * It comprises bn optionbl object identifier bnd bn optionbl ASN.1 BER
  * encoded vblue.
  *
  *<p>
  * The methods in this clbss cbn be used by the bpplicbtion to get low
  * level informbtion bbout the extended operbtion response. However, typicblly,
  * the bpplicbtion will be using methods specific to the clbss thbt
  * implements this interfbce. Such b clbss should hbve decoded the BER buffer
  * in the response bnd should provide methods thbt bllow the user to
  * bccess thbt dbtb in the response in b type-sbfe bnd friendly mbnner.
  *<p>
  * For exbmple, suppose the LDAP server supported b 'get time' extended operbtion.
  * It would supply GetTimeRequest bnd GetTimeResponse clbsses.
  * The GetTimeResponse clbss might look like:
  *<blockquote><pre>
  * public clbss GetTimeResponse implements ExtendedResponse {
  *     public jbvb.util.Dbte getDbte() {...};
  *     public long getTime() {...};
  *     ....
  * }
  *</pre></blockquote>
  * A progrbm would use then these clbsses bs follows:
  *<blockquote><pre>
  * GetTimeResponse resp =
  *     (GetTimeResponse) ectx.extendedOperbtion(new GetTimeRequest());
  * jbvb.util.Dbte now = resp.getDbte();
  *</pre></blockquote>
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @buthor Vincent Rybn
  *
  * @see ExtendedRequest
  * @since 1.3
  */

public interfbce ExtendedResponse extends jbvb.io.Seriblizbble {

    /**
      * Retrieves the object identifier of the response.
      * The LDAP protocol specifies thbt the response object identifier is optionbl.
      * If the server does not send it, the response will contbin no ID (i.e. null).
      *
      * @return A possibly null object identifier string representing the LDAP
      *         <tt>ExtendedResponse.responseNbme</tt> component.
      */
    public String getID();

    /**
      * Retrieves the ASN.1 BER encoded vblue of the LDAP extended operbtion
      * response. Null is returned if the vblue is bbsent from the response
      * sent by the LDAP server.
      * The result is the rbw BER bytes including the tbg bnd length of
      * the response vblue. It does not include the response OID.
      *
      * @return A possibly null byte brrby representing the ASN.1 BER encoded
      *         contents of the LDAP <tt>ExtendedResponse.response</tt>
      *         component.
      */
    public byte[] getEncodedVblue();

    //stbtic finbl long seriblVersionUID = -3320509678029180273L;
}
