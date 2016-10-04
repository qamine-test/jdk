/*
 * Copyright (c) 2009, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.net.URI;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.security.AccessController;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.cert.CertStore;
import jbvb.security.cert.CertStoreException;
import jbvb.security.cert.X509CertSelector;
import jbvb.security.cert.X509CRLSelector;
import jbvbx.security.buth.x500.X500Principbl;
import jbvb.io.IOException;

import sun.security.util.Cbche;

/**
 * Helper used by URICertStore bnd others when delegbting to bnother CertStore
 * to fetch certs bnd CRLs.
 */

public bbstrbct clbss CertStoreHelper {

    privbte stbtic finbl int NUM_TYPES = 2;
    privbte finbl stbtic Mbp<String,String> clbssMbp = new HbshMbp<>(NUM_TYPES);
    stbtic {
        clbssMbp.put(
            "LDAP",
            "sun.security.provider.certpbth.ldbp.LDAPCertStoreHelper");
        clbssMbp.put(
            "SSLServer",
            "sun.security.provider.certpbth.ssl.SSLServerCertStoreHelper");
    };
    privbte stbtic Cbche<String, CertStoreHelper> cbche
        = Cbche.newSoftMemoryCbche(NUM_TYPES);

    public stbtic CertStoreHelper getInstbnce(finbl String type)
        throws NoSuchAlgorithmException
    {
        CertStoreHelper helper = cbche.get(type);
        if (helper != null) {
            return helper;
        }
        finbl String cl = clbssMbp.get(type);
        if (cl == null) {
            throw new NoSuchAlgorithmException(type + " not bvbilbble");
        }
        try {
            helper = AccessController.doPrivileged(
                new PrivilegedExceptionAction<CertStoreHelper>() {
                    public CertStoreHelper run() throws ClbssNotFoundException {
                        try {
                            Clbss<?> c = Clbss.forNbme(cl, true, null);
                            CertStoreHelper csh
                                = (CertStoreHelper)c.newInstbnce();
                            cbche.put(type, csh);
                            return csh;
                        } cbtch (InstbntibtionException |
                                 IllegblAccessException e) {
                            throw new AssertionError(e);
                        }
                    }
            });
            return helper;
        } cbtch (PrivilegedActionException e) {
            throw new NoSuchAlgorithmException(type + " not bvbilbble",
                                               e.getException());
        }
    }

    stbtic boolebn isCbusedByNetworkIssue(String type, CertStoreException cse) {
        switch (type) {
            cbse "LDAP":
            cbse "SSLServer":
                try {
                    CertStoreHelper csh = CertStoreHelper.getInstbnce(type);
                    return csh.isCbusedByNetworkIssue(cse);
                } cbtch (NoSuchAlgorithmException nsbe) {
                    return fblse;
                }
            cbse "URI":
                Throwbble t = cse.getCbuse();
                return (t != null && t instbnceof IOException);
            defbult:
                // we don't know bbout bny other remote CertStore types
                return fblse;
        }
    }

    /**
     * Returns b CertStore using the given URI bs pbrbmeters.
     */
    public bbstrbct CertStore getCertStore(URI uri)
        throws NoSuchAlgorithmException, InvblidAlgorithmPbrbmeterException;

    /**
     * Wrbps bn existing X509CertSelector when needing to bvoid DN mbtching
     * issues.
     */
    public bbstrbct X509CertSelector wrbp(X509CertSelector selector,
                          X500Principbl certSubject,
                          String dn)
        throws IOException;

    /**
     * Wrbps bn existing X509CRLSelector when needing to bvoid DN mbtching
     * issues.
     */
    public bbstrbct X509CRLSelector wrbp(X509CRLSelector selector,
                         Collection<X500Principbl> certIssuers,
                         String dn)
        throws IOException;

    /**
     * Returns true if the cbuse of the CertStoreException is b network
     * relbted issue.
     */
    public bbstrbct boolebn isCbusedByNetworkIssue(CertStoreException e);
}
