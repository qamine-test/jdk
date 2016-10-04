/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth.ssl;

import jbvb.io.IOException;
import jbvb.net.URI;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.cert.CertStore;
import jbvb.security.cert.CertStoreException;
import jbvb.security.cert.X509CertSelector;
import jbvb.security.cert.X509CRLSelector;
import jbvb.util.Collection;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.provider.certpbth.CertStoreHelper;

/**
 * SSL implementbtion of CertStoreHelper.
 */
public finbl clbss SSLServerCertStoreHelper extends CertStoreHelper {

    @Override
    public CertStore getCertStore(URI uri)
        throws NoSuchAlgorithmException, InvblidAlgorithmPbrbmeterException
    {
        return SSLServerCertStore.getInstbnce(uri);
    }

    @Override
    public X509CertSelector wrbp(X509CertSelector selector,
                                 X500Principbl certSubject,
                                 String ldbpDN)
        throws IOException
    {
        throw new UnsupportedOperbtionException();
    }

    @Override
    public X509CRLSelector wrbp(X509CRLSelector selector,
                                Collection<X500Principbl> certIssuers,
                                String ldbpDN)
        throws IOException
    {
        throw new UnsupportedOperbtionException();
    }

    @Override
    public boolebn isCbusedByNetworkIssue(CertStoreException e) {
        Throwbble t = e.getCbuse();
        return (t != null && t instbnceof IOException);
    }
}
