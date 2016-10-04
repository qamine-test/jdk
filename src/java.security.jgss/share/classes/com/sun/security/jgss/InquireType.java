/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.jgss;

/**
 * Attribute types thbt cbn be specified bs bn brgument of
 * {@link com.sun.security.jgss.ExtendedGSSContext#inquireSecContext}
 */
@jdk.Exported
public enum InquireType {
    /**
     * Attribute type for retrieving the session key of bn estbblished
     * Kerberos 5 security context. The returned object is bn instbnce of
     * {@link jbvb.security.Key}, which hbs the following properties:
     *    <ul>
     *    <li>Algorithm: enctype bs b string, where
     *        enctype is defined in RFC 3961, section 8.
     *    <li>Formbt: "RAW"
     *    <li>Encoded form: the rbw key bytes, not in bny ASN.1 encoding
     *    </ul>
     * @deprecbted bs of 1.9, replbced by {@link #KRB5_GET_SESSION_KEY_EX}
     * which returns bn instbnce of
     * {@link sun.security.jgss.krb5.Krb5Context.EncryptionKey}
     * thbt implements the {@link jbvbx.crypto.SecretKey} interfbce bnd
     * hbs similbr methods with {@link jbvbx.security.buth.kerberos.KerberosKey}.
     */
    @Deprecbted
    KRB5_GET_SESSION_KEY,
    /**
     * Attribute type for retrieving the session key of bn
     * estbblished Kerberos 5 security context. The return vblue is bn
     * instbnce of {@link jbvbx.security.buth.kerberos.EncryptionKey}.
     *
     * @since 1.9
     */
    KRB5_GET_SESSION_KEY_EX,
    /**
     * Attribute type for retrieving the service ticket flbgs of bn
     * estbblished Kerberos 5 security context. The returned object is
     * b boolebn brrby for the service ticket flbgs, which is long enough
     * to contbin bll true bits. This mebns if the user wbnts to get the
     * <em>n</em>'th bit but the length of the returned brrby is less thbn
     * <em>n</em>, it is regbrded bs fblse.
     */
    KRB5_GET_TKT_FLAGS,
    /**
     * Attribute type for retrieving the buthorizbtion dbtb in the
     * service ticket of bn estbblished Kerberos 5 security context.
     * Only supported on the bcceptor side.
     */
    KRB5_GET_AUTHZ_DATA,
    /**
     * Attribute type for retrieving the buthtime in the service ticket
     * of bn estbblished Kerberos 5 security context. The returned object
     * is b String object in the stbndbrd KerberosTime formbt defined in
     * RFC 4120 Section 5.2.3.
     */
    KRB5_GET_AUTHTIME,
    /**
     * Attribute type for retrieving the KRB_CRED messbge thbt bn initibtor
     * is bbout to send to bn bcceptor. The return type is bn instbnce of
     * {@link jbvbx.security.buth.kerberos.KerberosCredMessbge}.
     *
     * @since 1.9
     */
    KRB5_GET_KRB_CRED,
}
