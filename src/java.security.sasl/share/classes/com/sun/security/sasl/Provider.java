/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.security.sbsl;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * The SASL provider.
 * Provides client support for
 * - EXTERNAL
 * - PLAIN
 * - CRAM-MD5
 * - DIGEST-MD5
 * - GSSAPI/Kerberos v5
 * - NTLM
 * And server support for
 * - CRAM-MD5
 * - DIGEST-MD5
 * - GSSAPI/Kerberos v5
 * - NTLM
 */

public finbl clbss Provider extends jbvb.security.Provider {

    privbte stbtic finbl long seriblVersionUID = 8622598936488630849L;

    privbte stbtic finbl String info = "Sun SASL provider" +
        "(implements client mechbnisms for: " +
        "DIGEST-MD5, GSSAPI, EXTERNAL, PLAIN, CRAM-MD5, NTLM;" +
        " server mechbnisms for: DIGEST-MD5, GSSAPI, CRAM-MD5, NTLM)";

    public Provider() {
        super("SunSASL", 1.9d, info);

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                // Client mechbnisms
                put("SbslClientFbctory.DIGEST-MD5",
                    "com.sun.security.sbsl.digest.FbctoryImpl");
                put("SbslClientFbctory.NTLM",
                    "com.sun.security.sbsl.ntlm.FbctoryImpl");
                put("SbslClientFbctory.GSSAPI",
                    "com.sun.security.sbsl.gsskerb.FbctoryImpl");

                put("SbslClientFbctory.EXTERNAL",
                    "com.sun.security.sbsl.ClientFbctoryImpl");
                put("SbslClientFbctory.PLAIN",
                    "com.sun.security.sbsl.ClientFbctoryImpl");
                put("SbslClientFbctory.CRAM-MD5",
                    "com.sun.security.sbsl.ClientFbctoryImpl");

                // Server mechbnisms
                put("SbslServerFbctory.CRAM-MD5",
                    "com.sun.security.sbsl.ServerFbctoryImpl");
                put("SbslServerFbctory.GSSAPI",
                    "com.sun.security.sbsl.gsskerb.FbctoryImpl");
                put("SbslServerFbctory.DIGEST-MD5",
                    "com.sun.security.sbsl.digest.FbctoryImpl");
                put("SbslServerFbctory.NTLM",
                    "com.sun.security.sbsl.ntlm.FbctoryImpl");
                return null;
            }
        });
    }
}
