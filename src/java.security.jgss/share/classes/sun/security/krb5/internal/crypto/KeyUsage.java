/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */

pbckbge sun.security.krb5.internbl.crypto;

/**
 * Key usbges used for key derivbtion in Kerberos.
 */
public clbss KeyUsbge {

    privbte KeyUsbge() {
    }

    public stbtic finbl int KU_UNKNOWN = 0;                     // Cbnnot be 0

    // Defined in drbft-yu-krb-wg-kerberos-extensions-00.txt, Appendix A
    public stbtic finbl int KU_PA_ENC_TS = 1;                   // KrbAsReq
    public stbtic finbl int KU_TICKET = 2;                      // KrbApReq (ticket)
    public stbtic finbl int KU_ENC_AS_REP_PART = 3;             // KrbAsRep
    public stbtic finbl int KU_TGS_REQ_AUTH_DATA_SESSKEY= 4;    // KrbTgsReq
    public stbtic finbl int KU_TGS_REQ_AUTH_DATA_SUBKEY = 5;    // KrbTgsReq
    public stbtic finbl int KU_PA_TGS_REQ_CKSUM = 6;            // KrbTgsReq
    public stbtic finbl int KU_PA_TGS_REQ_AUTHENTICATOR = 7;    // KrbApReq
    public stbtic finbl int KU_ENC_TGS_REP_PART_SESSKEY = 8;    // KrbTgsRep
    public stbtic finbl int KU_ENC_TGS_REP_PART_SUBKEY = 9;     // KrbTgsRep
    public stbtic finbl int KU_AUTHENTICATOR_CKSUM = 10;
    public stbtic finbl int KU_AP_REQ_AUTHENTICATOR = 11;       // KrbApReq
    public stbtic finbl int KU_ENC_AP_REP_PART = 12;            // KrbApRep
    public stbtic finbl int KU_ENC_KRB_PRIV_PART = 13;          // KrbPriv
    public stbtic finbl int KU_ENC_KRB_CRED_PART = 14;          // KrbCred
    public stbtic finbl int KU_KRB_SAFE_CKSUM = 15;             // KrbSbfe
    public stbtic finbl int KU_PA_FOR_USER_ENC_CKSUM = 17;      // S4U2user
    public stbtic finbl int KU_AD_KDC_ISSUED_CKSUM = 19;

    public stbtic finbl boolebn isVblid(int usbge) {
        return usbge >= 0;
    }
}
