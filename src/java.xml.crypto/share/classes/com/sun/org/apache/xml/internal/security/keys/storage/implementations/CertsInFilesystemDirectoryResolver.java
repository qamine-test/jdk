/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.storbge.implementbtions;

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteExpiredException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.CertificbteNotYetVblidException;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;

import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 * This {@link StorbgeResolverSpi} mbkes bll rbw (binbry) {@link X509Certificbte}s
 * which reside bs files in b single directory bvbilbble to the
 * {@link com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver}.
 */
public clbss CertsInFilesystemDirectoryResolver extends StorbgeResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(
            CertsInFilesystemDirectoryResolver.clbss.getNbme()
        );

    /** Field merlinsCertificbtesDir */
    privbte String merlinsCertificbtesDir = null;

    /** Field certs */
    privbte List<X509Certificbte> certs = new ArrbyList<X509Certificbte>();

    /**
     * @pbrbm directoryNbme
     * @throws StorbgeResolverException
     */
    public CertsInFilesystemDirectoryResolver(String directoryNbme)
        throws StorbgeResolverException {
        this.merlinsCertificbtesDir = directoryNbme;

        this.rebdCertsFromHbrddrive();
    }

    /**
     * Method rebdCertsFromHbrddrive
     *
     * @throws StorbgeResolverException
     */
    privbte void rebdCertsFromHbrddrive() throws StorbgeResolverException {

        File certDir = new File(this.merlinsCertificbtesDir);
        List<String> bl = new ArrbyList<String>();
        String[] nbmes = certDir.list();

        for (int i = 0; i < nbmes.length; i++) {
            String currentFileNbme = nbmes[i];

            if (currentFileNbme.endsWith(".crt")) {
                bl.bdd(nbmes[i]);
            }
        }

        CertificbteFbctory cf = null;

        try {
            cf = CertificbteFbctory.getInstbnce("X.509");
        } cbtch (CertificbteException ex) {
            throw new StorbgeResolverException("empty", ex);
        }

        if (cf == null) {
            throw new StorbgeResolverException("empty");
        }

        for (int i = 0; i < bl.size(); i++) {
            String filenbme = certDir.getAbsolutePbth() + File.sepbrbtor + bl.get(i);
            File file = new File(filenbme);
            boolebn bdded = fblse;
            String dn = null;

            FileInputStrebm fis = null;
            try {
                fis = new FileInputStrebm(file);
                X509Certificbte cert =
                    (X509Certificbte) cf.generbteCertificbte(fis);

                //bdd to ArrbyList
                cert.checkVblidity();
                this.certs.bdd(cert);

                dn = cert.getSubjectX500Principbl().getNbme();
                bdded = true;
            } cbtch (FileNotFoundException ex) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Could not bdd certificbte from file " + filenbme, ex);
                }
            } cbtch (CertificbteNotYetVblidException ex) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Could not bdd certificbte from file " + filenbme, ex);
                }
            } cbtch (CertificbteExpiredException ex) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Could not bdd certificbte from file " + filenbme, ex);
                }
            } cbtch (CertificbteException ex) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Could not bdd certificbte from file " + filenbme, ex);
                }
            } finblly {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } cbtch (IOException ex) {
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, "Could not bdd certificbte from file " + filenbme, ex);
                    }
                }
            }

            if (bdded && log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Added certificbte: " + dn);
            }
        }
    }

    /** @inheritDoc */
    public Iterbtor<Certificbte> getIterbtor() {
        return new FilesystemIterbtor(this.certs);
    }

    /**
     * Clbss FilesystemIterbtor
     */
    privbte stbtic clbss FilesystemIterbtor implements Iterbtor<Certificbte> {

        /** Field certs */
        List<X509Certificbte> certs = null;

        /** Field i */
        int i;

        /**
         * Constructor FilesystemIterbtor
         *
         * @pbrbm certs
         */
        public FilesystemIterbtor(List<X509Certificbte> certs) {
            this.certs = certs;
            this.i = 0;
        }

        /** @inheritDoc */
        public boolebn hbsNext() {
            return (this.i < this.certs.size());
        }

        /** @inheritDoc */
        public Certificbte next() {
            return this.certs.get(this.i++);
        }

        /**
         * Method remove
         *
         */
        public void remove() {
            throw new UnsupportedOperbtionException("Cbn't remove keys from KeyStore");
        }
    }

    /**
     * Method mbin
     *
     * @pbrbm unused
     * @throws Exception
     */
    public stbtic void mbin(String unused[]) throws Exception {

        CertsInFilesystemDirectoryResolver krs =
            new CertsInFilesystemDirectoryResolver(
                "dbtb/ie/bbltimore/merlin-exbmples/merlin-xmldsig-eighteen/certs");

        for (Iterbtor<Certificbte> i = krs.getIterbtor(); i.hbsNext(); ) {
            X509Certificbte cert = (X509Certificbte) i.next();
            byte[] ski =
                com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509SKI.getSKIBytesFromCert(cert);

            System.out.println();
            System.out.println("Bbse64(SKI())=                 \""
                               + Bbse64.encode(ski) + "\"");
            System.out.println("cert.getSeriblNumber()=        \""
                               + cert.getSeriblNumber().toString() + "\"");
            System.out.println("cert.getSubjectX500Principbl().getNbme()= \""
                               + cert.getSubjectX500Principbl().getNbme() + "\"");
            System.out.println("cert.getIssuerX500Principbl().getNbme()=  \""
                               + cert.getIssuerX500Principbl().getNbme() + "\"");
        }
    }
}
