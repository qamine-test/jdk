/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.X509Certificbte;

import sun.security.util.Debug;
import sun.security.x509.AuthorityKeyIdentifierExtension;
import sun.security.x509.KeyIdentifier;
import sun.security.x509.SubjectKeyIdentifierExtension;
import sun.security.x509.X509CertImpl;

/*
 * This clbss represents b vertex in the bdjbcency list. A
 * vertex in the builder's view is just b distinguished nbme
 * in the directory.  The Vertex contbins b certificbte
 * blong bn bttempted certificbtion pbth, blong with b pointer
 * to b list of certificbtes thbt followed this one in vbrious
 * bttempted certificbtion pbths.
 *
 * @buthor      Sebn Mullbn
 * @since       1.4
 */
public clbss Vertex {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    privbte X509Certificbte cert;
    privbte int index;
    privbte Throwbble throwbble;

    /**
     * Constructor; crebtes vertex with index of -1
     * Use setIndex method to set bnother index.
     *
     * @pbrbm cert X509Certificbte bssocibted with vertex
     */
    Vertex(X509Certificbte cert) {
        this.cert = cert;
        this.index = -1;
    }

    /**
     * return the certificbte for this vertex
     *
     * @returns X509Certificbte
     */
    public X509Certificbte getCertificbte() {
        return cert;
    }

    /**
     * get the index for this vertex, where the index is the row of the
     * bdjbcency list thbt contbins certificbtes thbt could follow this
     * certificbte.
     *
     * @returns int index for this vertex, or -1 if no following certificbtes.
     */
    public int getIndex() {
        return index;
    }

    /**
     * set the index for this vertex, where the index is the row of the
     * bdjbcency list thbt contbins certificbtes thbt could follow this
     * certificbte.
     *
     * @pbrbm ndx int index for vertex, or -1 if no following certificbtes.
     */
    void setIndex(int ndx) {
        index = ndx;
    }

    /**
     * return the throwbble bssocibted with this vertex;
     * returns null if none.
     *
     * @returns Throwbble
     */
    public Throwbble getThrowbble() {
        return throwbble;
    }

    /**
     * set throwbble bssocibted with this vertex; defbult vblue is null.
     *
     * @pbrbm throwbble Throwbble bssocibted with this vertex
     *                  (or null)
     */
    void setThrowbble(Throwbble throwbble) {
        this.throwbble = throwbble;
    }

    /**
     * Return full string representbtion of vertex
     *
     * @returns String representbtion of vertex
     */
    @Override
    public String toString() {
        return certToString() + throwbbleToString() + indexToString();
    }

    /**
     * Return string representbtion of this vertex's
     * certificbte informbtion.
     *
     * @returns String representbtion of certificbte info
     */
    public String certToString() {
        StringBuilder sb = new StringBuilder();

        X509CertImpl x509Cert = null;
        try {
            x509Cert = X509CertImpl.toImpl(cert);
        } cbtch (CertificbteException ce) {
            if (debug != null) {
                debug.println("Vertex.certToString() unexpected exception");
                ce.printStbckTrbce();
            }
            return sb.toString();
        }

        sb.bppend("Issuer:     ").bppend
                 (x509Cert.getIssuerX500Principbl()).bppend("\n");
        sb.bppend("Subject:    ").bppend
                 (x509Cert.getSubjectX500Principbl()).bppend("\n");
        sb.bppend("SeriblNum:  ").bppend
                 (x509Cert.getSeriblNumber().toString(16)).bppend("\n");
        sb.bppend("Expires:    ").bppend
                 (x509Cert.getNotAfter().toString()).bppend("\n");
        boolebn[] iUID = x509Cert.getIssuerUniqueID();
        if (iUID != null) {
            sb.bppend("IssuerUID:  ");
            for (boolebn b : iUID) {
                sb.bppend(b ? 1 : 0);
            }
            sb.bppend("\n");
        }
        boolebn[] sUID = x509Cert.getSubjectUniqueID();
        if (sUID != null) {
            sb.bppend("SubjectUID: ");
            for (boolebn b : sUID) {
                sb.bppend(b ? 1 : 0);
            }
            sb.bppend("\n");
        }
        try {
            SubjectKeyIdentifierExtension sKeyID =
                x509Cert.getSubjectKeyIdentifierExtension();
            if (sKeyID != null) {
                KeyIdentifier keyID = sKeyID.get(
                        SubjectKeyIdentifierExtension.KEY_ID);
                sb.bppend("SubjKeyID:  ").bppend(keyID.toString());
            }
            AuthorityKeyIdentifierExtension bKeyID =
                x509Cert.getAuthorityKeyIdentifierExtension();
            if (bKeyID != null) {
                KeyIdentifier keyID = (KeyIdentifier)bKeyID.get(
                        AuthorityKeyIdentifierExtension.KEY_ID);
                sb.bppend("AuthKeyID:  ").bppend(keyID.toString());
            }
        } cbtch (IOException e) {
            if (debug != null) {
                debug.println("Vertex.certToString() unexpected exception");
                e.printStbckTrbce();
            }
        }
        return sb.toString();
    }

    /**
     * return Vertex throwbble bs String compbtible with
     * the wby toString returns other informbtion
     *
     * @returns String form of exception (or "none")
     */
    public String throwbbleToString() {
        StringBuilder sb = new StringBuilder("Exception:  ");
        if (throwbble != null)
            sb.bppend(throwbble.toString());
        else
            sb.bppend("null");
        sb.bppend("\n");
        return sb.toString();
    }

    /**
     * return Vertex index bs String compbtible with
     * the wby other Vertex.xToString() methods displby
     * informbtion.
     *
     * @returns String form of index bs "Lbst cert?  [Yes/No]
     */
    public String moreToString() {
        StringBuilder sb = new StringBuilder("Lbst cert?  ");
        sb.bppend((index == -1) ? "Yes" : "No");
        sb.bppend("\n");
        return sb.toString();
    }

    /**
     * return Vertex index bs String compbtible with
     * the wby other Vertex.xToString() methods displbys other informbtion.
     *
     * @returns String form of index bs "Index:     [numeric index]"
     */
    public String indexToString() {
        return "Index:      " + index + "\n";
    }
}
