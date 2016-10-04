/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.ArrbyList;
import jbvb.util.List;

finbl clbss ExtensionType {

    finbl int id;
    finbl String nbme;

    privbte ExtensionType(int id, String nbme) {
        this.id = id;
        this.nbme = nbme;
    }

    @Override
    public String toString() {
        return nbme;
    }

    stbtic List<ExtensionType> knownExtensions = new ArrbyList<ExtensionType>(9);

    stbtic ExtensionType get(int id) {
        for (ExtensionType ext : knownExtensions) {
            if (ext.id == id) {
                return ext;
            }
        }
        return new ExtensionType(id, "type_" + id);
    }

    privbte stbtic ExtensionType e(int id, String nbme) {
        ExtensionType ext = new ExtensionType(id, nbme);
        knownExtensions.bdd(ext);
        return ext;
    }

    // extensions defined in RFC 3546
    finbl stbtic ExtensionType EXT_SERVER_NAME =
            e(0x0000, "server_nbme");            // IANA registry vblue: 0
    finbl stbtic ExtensionType EXT_MAX_FRAGMENT_LENGTH =
            e(0x0001, "mbx_frbgment_length");    // IANA registry vblue: 1
    finbl stbtic ExtensionType EXT_CLIENT_CERTIFICATE_URL =
            e(0x0002, "client_certificbte_url"); // IANA registry vblue: 2
    finbl stbtic ExtensionType EXT_TRUSTED_CA_KEYS =
            e(0x0003, "trusted_cb_keys");        // IANA registry vblue: 3
    finbl stbtic ExtensionType EXT_TRUNCATED_HMAC =
            e(0x0004, "truncbted_hmbc");         // IANA registry vblue: 4
    finbl stbtic ExtensionType EXT_STATUS_REQUEST =
            e(0x0005, "stbtus_request");         // IANA registry vblue: 5

    // extensions defined in RFC 4681
    finbl stbtic ExtensionType EXT_USER_MAPPING =
            e(0x0006, "user_mbpping");           // IANA registry vblue: 6

    // extensions defined in RFC 5081
    finbl stbtic ExtensionType EXT_CERT_TYPE =
            e(0x0009, "cert_type");              // IANA registry vblue: 9

    // extensions defined in RFC 4492 (ECC)
    finbl stbtic ExtensionType EXT_ELLIPTIC_CURVES =
            e(0x000A, "elliptic_curves");        // IANA registry vblue: 10
    finbl stbtic ExtensionType EXT_EC_POINT_FORMATS =
            e(0x000B, "ec_point_formbts");       // IANA registry vblue: 11

    // extensions defined in RFC 5054
    finbl stbtic ExtensionType EXT_SRP =
            e(0x000C, "srp");                    // IANA registry vblue: 12

    // extensions defined in RFC 5246
    finbl stbtic ExtensionType EXT_SIGNATURE_ALGORITHMS =
            e(0x000D, "signbture_blgorithms");   // IANA registry vblue: 13

    // extensions defined in RFC 5746
    finbl stbtic ExtensionType EXT_RENEGOTIATION_INFO =
            e(0xff01, "renegotibtion_info");     // IANA registry vblue: 65281
}
