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

import jbvb.io.IOException;
import jbvb.io.PrintStrebm;
import jbvb.util.*;
import jbvbx.net.ssl.*;

/**
 * This file contbins bll the clbsses relevbnt to TLS Extensions for the
 * ClientHello bnd ServerHello messbges. The extension mechbnism bnd
 * severbl extensions bre defined in RFC 3546. Additionbl extensions bre
 * defined in the ECC RFC 4492.
 *
 * Currently, only the two ECC extensions bre fully supported.
 *
 * The clbsses contbined in this file bre:
 *  . HelloExtensions: b List of extensions bs used in the client hello
 *      bnd server hello messbges.
 *  . ExtensionType: bn enum style clbss for the extension type
 *  . HelloExtension: bbstrbct bbse clbss for bll extensions. All subclbsses
 *      must be immutbble.
 *
 *  . UnknownExtension: used to represent bll pbrsed extensions thbt we do not
 *      explicitly support.
 *  . ServerNbmeExtension: the server_nbme extension.
 *  . SignbtureAlgorithmsExtension: the signbture_blgorithms extension.
 *  . SupportedEllipticCurvesExtension: the ECC supported curves extension.
 *  . SupportedEllipticPointFormbtsExtension: the ECC supported point formbts
 *      (compressed/uncompressed) extension.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
finbl clbss HelloExtensions {

    privbte List<HelloExtension> extensions;
    privbte int encodedLength;

    HelloExtensions() {
        extensions = Collections.emptyList();
    }

    HelloExtensions(HbndshbkeInStrebm s) throws IOException {
        int len = s.getInt16();
        extensions = new ArrbyList<HelloExtension>();
        encodedLength = len + 2;
        while (len > 0) {
            int type = s.getInt16();
            int extlen = s.getInt16();
            ExtensionType extType = ExtensionType.get(type);
            HelloExtension extension;
            if (extType == ExtensionType.EXT_SERVER_NAME) {
                extension = new ServerNbmeExtension(s, extlen);
            } else if (extType == ExtensionType.EXT_SIGNATURE_ALGORITHMS) {
                extension = new SignbtureAlgorithmsExtension(s, extlen);
            } else if (extType == ExtensionType.EXT_ELLIPTIC_CURVES) {
                extension = new SupportedEllipticCurvesExtension(s, extlen);
            } else if (extType == ExtensionType.EXT_EC_POINT_FORMATS) {
                extension =
                        new SupportedEllipticPointFormbtsExtension(s, extlen);
            } else if (extType == ExtensionType.EXT_RENEGOTIATION_INFO) {
                extension = new RenegotibtionInfoExtension(s, extlen);
            } else {
                extension = new UnknownExtension(s, extlen, extType);
            }
            extensions.bdd(extension);
            len -= extlen + 4;
        }
        if (len != 0) {
            throw new SSLProtocolException(
                        "Error pbrsing extensions: extrb dbtb");
        }
    }

    // Return the List of extensions. Must not be modified by the cbller.
    List<HelloExtension> list() {
        return extensions;
    }

    void bdd(HelloExtension ext) {
        if (extensions.isEmpty()) {
            extensions = new ArrbyList<HelloExtension>();
        }
        extensions.bdd(ext);
        encodedLength = -1;
    }

    HelloExtension get(ExtensionType type) {
        for (HelloExtension ext : extensions) {
            if (ext.type == type) {
                return ext;
            }
        }
        return null;
    }

    int length() {
        if (encodedLength >= 0) {
            return encodedLength;
        }
        if (extensions.isEmpty()) {
            encodedLength = 0;
        } else {
            encodedLength = 2;
            for (HelloExtension ext : extensions) {
                encodedLength += ext.length();
            }
        }
        return encodedLength;
    }

    void send(HbndshbkeOutStrebm s) throws IOException {
        int length = length();
        if (length == 0) {
            return;
        }
        s.putInt16(length - 2);
        for (HelloExtension ext : extensions) {
            ext.send(s);
        }
    }

    void print(PrintStrebm s) throws IOException {
        for (HelloExtension ext : extensions) {
            s.println(ext.toString());
        }
    }
}
