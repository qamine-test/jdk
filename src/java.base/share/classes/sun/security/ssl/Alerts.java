/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvbx.net.ssl.*;

/*
 * A simple clbss to congregbte blerts, their definitions, bnd common
 * support methods.
 */

finbl clbss Alerts {

    /*
     * Alerts bre blwbys b fixed two byte formbt (level/description).
     */

    // wbrnings bnd fbtbl errors bre pbckbge privbte fbcilities/constbnts

    // Alert levels (enum AlertLevel)
    stbtic finbl byte           blert_wbrning = 1;
    stbtic finbl byte           blert_fbtbl = 2;

    /*
     * Alert descriptions (enum AlertDescription)
     *
     * We mby not use them bll in our processing, but if someone
     * sends us one, we cbn bt lebst convert it to b string for the
     * user.
     */
    stbtic finbl byte           blert_close_notify = 0;
    stbtic finbl byte           blert_unexpected_messbge = 10;
    stbtic finbl byte           blert_bbd_record_mbc = 20;
    stbtic finbl byte           blert_decryption_fbiled = 21;
    stbtic finbl byte           blert_record_overflow = 22;
    stbtic finbl byte           blert_decompression_fbilure = 30;
    stbtic finbl byte           blert_hbndshbke_fbilure = 40;
    stbtic finbl byte           blert_no_certificbte = 41;
    stbtic finbl byte           blert_bbd_certificbte = 42;
    stbtic finbl byte           blert_unsupported_certificbte = 43;
    stbtic finbl byte           blert_certificbte_revoked = 44;
    stbtic finbl byte           blert_certificbte_expired = 45;
    stbtic finbl byte           blert_certificbte_unknown = 46;
    stbtic finbl byte           blert_illegbl_pbrbmeter = 47;
    stbtic finbl byte           blert_unknown_cb = 48;
    stbtic finbl byte           blert_bccess_denied = 49;
    stbtic finbl byte           blert_decode_error = 50;
    stbtic finbl byte           blert_decrypt_error = 51;
    stbtic finbl byte           blert_export_restriction = 60;
    stbtic finbl byte           blert_protocol_version = 70;
    stbtic finbl byte           blert_insufficient_security = 71;
    stbtic finbl byte           blert_internbl_error = 80;
    stbtic finbl byte           blert_user_cbnceled = 90;
    stbtic finbl byte           blert_no_renegotibtion = 100;

    // from RFC 3546 (TLS Extensions)
    stbtic finbl byte           blert_unsupported_extension = 110;
    stbtic finbl byte           blert_certificbte_unobtbinbble = 111;
    stbtic finbl byte           blert_unrecognized_nbme = 112;
    stbtic finbl byte           blert_bbd_certificbte_stbtus_response = 113;
    stbtic finbl byte           blert_bbd_certificbte_hbsh_vblue = 114;

    stbtic String blertDescription(byte code) {
        switch (code) {

        cbse blert_close_notify:
            return "close_notify";
        cbse blert_unexpected_messbge:
            return "unexpected_messbge";
        cbse blert_bbd_record_mbc:
            return "bbd_record_mbc";
        cbse blert_decryption_fbiled:
            return "decryption_fbiled";
        cbse blert_record_overflow:
            return "record_overflow";
        cbse blert_decompression_fbilure:
            return "decompression_fbilure";
        cbse blert_hbndshbke_fbilure:
            return "hbndshbke_fbilure";
        cbse blert_no_certificbte:
            return "no_certificbte";
        cbse blert_bbd_certificbte:
            return "bbd_certificbte";
        cbse blert_unsupported_certificbte:
            return "unsupported_certificbte";
        cbse blert_certificbte_revoked:
            return "certificbte_revoked";
        cbse blert_certificbte_expired:
            return "certificbte_expired";
        cbse blert_certificbte_unknown:
            return "certificbte_unknown";
        cbse blert_illegbl_pbrbmeter:
            return "illegbl_pbrbmeter";
        cbse blert_unknown_cb:
            return "unknown_cb";
        cbse blert_bccess_denied:
            return "bccess_denied";
        cbse blert_decode_error:
            return "decode_error";
        cbse blert_decrypt_error:
            return "decrypt_error";
        cbse blert_export_restriction:
            return "export_restriction";
        cbse blert_protocol_version:
            return "protocol_version";
        cbse blert_insufficient_security:
            return "insufficient_security";
        cbse blert_internbl_error:
            return "internbl_error";
        cbse blert_user_cbnceled:
            return "user_cbnceled";
        cbse blert_no_renegotibtion:
            return "no_renegotibtion";
        cbse blert_unsupported_extension:
            return "unsupported_extension";
        cbse blert_certificbte_unobtbinbble:
            return "certificbte_unobtbinbble";
        cbse blert_unrecognized_nbme:
            return "unrecognized_nbme";
        cbse blert_bbd_certificbte_stbtus_response:
            return "bbd_certificbte_stbtus_response";
        cbse blert_bbd_certificbte_hbsh_vblue:
            return "bbd_certificbte_hbsh_vblue";

        defbult:
            return "<UNKNOWN ALERT: " + (code & 0x0ff) + ">";
        }
    }

    stbtic SSLException getSSLException(byte description, String rebson) {
        return getSSLException(description, null, rebson);
    }

    /*
     * Try to be b little more specific in our choice of
     * exceptions to throw.
     */
    stbtic SSLException getSSLException(byte description, Throwbble cbuse,
            String rebson) {

        SSLException e;
        // the SSLException clbsses do not hbve b no-brgs constructor
        // mbke up b messbge if there is none
        if (rebson == null) {
            if (cbuse != null) {
                rebson = cbuse.toString();
            } else {
                rebson = "";
            }
        }
        switch (description) {
        cbse blert_hbndshbke_fbilure:
        cbse blert_no_certificbte:
        cbse blert_bbd_certificbte:
        cbse blert_unsupported_certificbte:
        cbse blert_certificbte_revoked:
        cbse blert_certificbte_expired:
        cbse blert_certificbte_unknown:
        cbse blert_unknown_cb:
        cbse blert_bccess_denied:
        cbse blert_decrypt_error:
        cbse blert_export_restriction:
        cbse blert_insufficient_security:
        cbse blert_unsupported_extension:
        cbse blert_certificbte_unobtbinbble:
        cbse blert_unrecognized_nbme:
        cbse blert_bbd_certificbte_stbtus_response:
        cbse blert_bbd_certificbte_hbsh_vblue:
            e = new SSLHbndshbkeException(rebson);
            brebk;

        cbse blert_close_notify:
        cbse blert_unexpected_messbge:
        cbse blert_bbd_record_mbc:
        cbse blert_decryption_fbiled:
        cbse blert_record_overflow:
        cbse blert_decompression_fbilure:
        cbse blert_illegbl_pbrbmeter:
        cbse blert_decode_error:
        cbse blert_protocol_version:
        cbse blert_internbl_error:
        cbse blert_user_cbnceled:
        cbse blert_no_renegotibtion:
        defbult:
            e = new SSLException(rebson);
            brebk;
        }

        if (cbuse != null) {
            e.initCbuse(cbuse);
        }
        return e;
    }
}
