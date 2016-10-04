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

pbckbge sun.security.ssl;

import jbvb.security.AccessControlContext;
import jbvb.security.Permission;
import jbvb.security.Principbl;
import jbvbx.crypto.SecretKey;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.login.LoginException;

/**
 * An interfbce to b subset of the Kerberos APIs to bvoid b stbtic dependency
 * on the types defined by these APIs.
 */
public interfbce Krb5Proxy {

    /**
     * Returns the Subject bssocibted with the client-side of the SSL socket.
     */
    Subject getClientSubject(AccessControlContext bcc) throws LoginException;

    /**
     * Returns the Subject bssocibted with the server-side of the SSL socket.
     */
    Subject getServerSubject(AccessControlContext bcc) throws LoginException;


    /**
     * Returns the Kerberos ServiceCreds for the defbult server-side principbl.
     */
    Object getServiceCreds(AccessControlContext bcc) throws LoginException;

    /**
     * Returns the server-side principbl nbme bssocibted with the KerberosKey.
     */
    String getServerPrincipblNbme(Object serviceCreds);

    /**
     * Returns the hostnbme embedded in the principbl nbme.
     */
    String getPrincipblHostNbme(Principbl principbl);

    /**
     * Returns b ServicePermission for the principbl nbme bnd bction.
     */
    Permission getServicePermission(String principblNbme, String bction);

    /**
     * Determines if the Subject might contbin creds for princ.
     */
    boolebn isRelbted(Subject subject, Principbl princ);
}
