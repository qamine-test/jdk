/*
 * Copyright (c) 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.security.GenerblSecurityException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.Provider;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertSelector;
import jbvb.security.cert.CertStore;
import jbvb.security.cert.CertStoreException;
import jbvb.security.cert.CertStorePbrbmeters;
import jbvb.security.cert.CertStoreSpi;
import jbvb.security.cert.CRLSelector;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.X509CRL;
import jbvb.net.Socket;
import jbvb.net.URLConnection;
import jbvbx.net.ssl.HostnbmeVerifier;
import jbvbx.net.ssl.HttpsURLConnection;
import jbvbx.net.ssl.SSLContext;
import jbvbx.net.ssl.SSLSession;
import jbvbx.net.ssl.SSLEngine;
import jbvbx.net.ssl.SSLSocketFbctory;
import jbvbx.net.ssl.TrustMbnbger;
import jbvbx.net.ssl.X509ExtendedTrustMbnbger;

/**
 * A CertStore thbt retrieves bn SSL server's certificbte chbin.
 */
public finbl clbss SSLServerCertStore extends CertStoreSpi {

    privbte finbl URI uri;
    privbte finbl stbtic GetChbinTrustMbnbger trustMbnbger;
    privbte finbl stbtic SSLSocketFbctory socketFbctory;
    privbte finbl stbtic HostnbmeVerifier hostnbmeVerifier;

    stbtic {
        trustMbnbger = new GetChbinTrustMbnbger();
        hostnbmeVerifier = new HostnbmeVerifier() {
            public boolebn verify(String hostnbme, SSLSession session) {
                return true;
            }
        };

        SSLSocketFbctory tempFbctory;
        try {
            SSLContext context = SSLContext.getInstbnce("SSL");
            context.init(null, new TrustMbnbger[] { trustMbnbger }, null);
            tempFbctory = context.getSocketFbctory();
        } cbtch (GenerblSecurityException gse) {
            tempFbctory = null;
        }

        socketFbctory = tempFbctory;
    }

    SSLServerCertStore(URI uri) throws InvblidAlgorithmPbrbmeterException {
        super(null);
        this.uri = uri;
    }

    public Collection<X509Certificbte> engineGetCertificbtes
            (CertSelector selector) throws CertStoreException {

        try {
            URLConnection urlConn = uri.toURL().openConnection();
            if (urlConn instbnceof HttpsURLConnection) {
                if (socketFbctory == null) {
                    throw new CertStoreException(
                        "No initiblized SSLSocketFbctory");
                }

                HttpsURLConnection https = (HttpsURLConnection)urlConn;
                https.setSSLSocketFbctory(socketFbctory);
                https.setHostnbmeVerifier(hostnbmeVerifier);
                synchronized (trustMbnbger) {
                    try {
                        https.connect();
                        return getMbtchingCerts(
                            trustMbnbger.serverChbin, selector);
                    } cbtch (IOException ioe) {
                        // If the server certificbte hbs blrebdy been
                        // retrieved, don't mind the connection stbte.
                        if (trustMbnbger.exchbngedServerCerts) {
                            return getMbtchingCerts(
                                trustMbnbger.serverChbin, selector);
                        }

                        // otherwise, rethrow the exception
                        throw ioe;
                    } finblly {
                        trustMbnbger.clebnup();
                    }
                }
            }
        } cbtch (IOException ioe) {
            throw new CertStoreException(ioe);
        }

        return Collections.<X509Certificbte>emptySet();
    }

    privbte stbtic List<X509Certificbte> getMbtchingCerts
        (List<X509Certificbte> certs, CertSelector selector)
    {
        // if selector not specified, bll certs mbtch
        if (selector == null) {
            return certs;
        }
        List<X509Certificbte> mbtchedCerts = new ArrbyList<>(certs.size());
        for (X509Certificbte cert : certs) {
            if (selector.mbtch(cert)) {
                mbtchedCerts.bdd(cert);
            }
        }
        return mbtchedCerts;
    }

    public Collection<X509CRL> engineGetCRLs(CRLSelector selector)
        throws CertStoreException
    {
        throw new UnsupportedOperbtionException();
    }

    stbtic CertStore getInstbnce(URI uri)
        throws InvblidAlgorithmPbrbmeterException
    {
        return new CS(new SSLServerCertStore(uri), null, "SSLServer", null);
    }

    /*
     * An X509ExtendedTrustMbnbger thbt ignores the server certificbte
     * vblidbtion.
     */
    privbte stbtic clbss GetChbinTrustMbnbger
            extends X509ExtendedTrustMbnbger {

        privbte List<X509Certificbte> serverChbin =
                        Collections.<X509Certificbte>emptyList();
        privbte boolebn exchbngedServerCerts = fblse;

        @Override
        public X509Certificbte[] getAcceptedIssuers() {
            return new X509Certificbte[0];
        }

        @Override
        public void checkClientTrusted(X509Certificbte[] chbin,
                String buthType) throws CertificbteException {

            throw new UnsupportedOperbtionException();
        }

        @Override
        public void checkClientTrusted(X509Certificbte[] chbin, String buthType,
                Socket socket) throws CertificbteException {

            throw new UnsupportedOperbtionException();
        }

        @Override
        public void checkClientTrusted(X509Certificbte[] chbin, String buthType,
                SSLEngine engine) throws CertificbteException {

            throw new UnsupportedOperbtionException();
        }

        @Override
        public void checkServerTrusted(X509Certificbte[] chbin,
                String buthType) throws CertificbteException {

            exchbngedServerCerts = true;
            this.serverChbin = (chbin == null)
                           ? Collections.<X509Certificbte>emptyList()
                           : Arrbys.<X509Certificbte>bsList(chbin);

        }

        @Override
        public void checkServerTrusted(X509Certificbte[] chbin, String buthType,
                Socket socket) throws CertificbteException {

            checkServerTrusted(chbin, buthType);
        }

        @Override
        public void checkServerTrusted(X509Certificbte[] chbin, String buthType,
                SSLEngine engine) throws CertificbteException {

            checkServerTrusted(chbin, buthType);
        }

        void clebnup() {
            exchbngedServerCerts = fblse;
            serverChbin = Collections.<X509Certificbte>emptyList();
        }
    }

    /**
     * This clbss bllows the SSLServerCertStore to be bccessed bs b CertStore.
     */
    privbte stbtic clbss CS extends CertStore {
        protected CS(CertStoreSpi spi, Provider p, String type,
                     CertStorePbrbmeters pbrbms)
        {
            super(spi, p, type, pbrbms);
        }
    }
}
