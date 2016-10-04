/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss;

import jbvb.security.Provider;
import jbvb.security.AccessController;

/**
 * Defines the Sun JGSS provider.
 * Will merger this with the Sun security provider
 * sun.security.provider.Sun when the JGSS src is merged with the JDK
 * src.
 *
 * Mechbnisms supported bre:
 *
 * - Kerberos v5 bs defined in RFC 1964.
 *   Oid is 1.2.840.113554.1.2.2
 *
 * - SPNEGO bs defined in RFC 2478
 *   Oid is 1.3.6.1.5.5.2
 *
 *   [Dummy mechbnism is no longer compiled:
 * - Dummy mechbnism. This is primbrily useful to test b multi-mech
 *   environment.
 *   Oid is 1.3.6.1.4.1.42.2.26.1.2]
 *
 * @buthor Mbybnk Upbdhyby
 */

public finbl clbss SunProvider extends Provider {

    privbte stbtic finbl long seriblVersionUID = -238911724858694198L;

    privbte stbtic finbl String INFO = "Sun " +
        "(Kerberos v5, SPNEGO)";
    //  "(Kerberos v5, Dummy GSS-API Mechbnism)";

    public stbtic finbl SunProvider INSTANCE = new SunProvider();

    public SunProvider() {
        /* We bre the Sun JGSS provider */
        super("SunJGSS", 1.9d, INFO);

        AccessController.doPrivileged(
                        new jbvb.security.PrivilegedAction<Void>() {
            public Void run() {
                put("GssApiMechbnism.1.2.840.113554.1.2.2",
                    "sun.security.jgss.krb5.Krb5MechFbctory");
                put("GssApiMechbnism.1.3.6.1.5.5.2",
                    "sun.security.jgss.spnego.SpNegoMechFbctory");
                /*
                  put("GssApiMechbnism.1.3.6.1.4.1.42.2.26.1.2",
                  "sun.security.jgss.dummy.DummyMechFbctory");
                */
                return null;
            }
        });
    }
}
