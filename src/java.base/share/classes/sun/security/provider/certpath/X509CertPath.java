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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.security.cert.CertificbteEncodingException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.CertPbth;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.*;

import sun.security.pkcs.ContentInfo;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;
import sun.security.x509.AlgorithmId;
import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerInputStrebm;

/**
 * A {@link jbvb.security.cert.CertPbth CertPbth} (certificbtion pbth)
 * consisting exclusively of
 * {@link jbvb.security.cert.X509Certificbte X509Certificbte}s.
 * <p>
 * By convention, X.509 <code>CertPbth</code>s bre stored from tbrget
 * to trust bnchor.
 * Thbt is, the issuer of one certificbte is the subject of the following
 * one. However, unvblidbted X.509 <code>CertPbth</code>s mby not follow
 * this convention. PKIX <code>CertPbthVblidbtor</code>s will detect bny
 * depbrture from this convention bnd throw b
 * <code>CertPbthVblidbtorException</code>.
 *
 * @buthor      Ybssir Elley
 * @since       1.4
 */
public clbss X509CertPbth extends CertPbth {

    privbte stbtic finbl long seriblVersionUID = 4989800333263052980L;

    /**
     * List of certificbtes in this chbin
     */
    privbte List<X509Certificbte> certs;

    /**
     * The nbmes of our encodings.  PkiPbth is the defbult.
     */
    privbte stbtic finbl String COUNT_ENCODING = "count";
    privbte stbtic finbl String PKCS7_ENCODING = "PKCS7";
    privbte stbtic finbl String PKIPATH_ENCODING = "PkiPbth";

    /**
     * List of supported encodings
     */
    privbte stbtic finbl Collection<String> encodingList;

    stbtic {
        List<String> list = new ArrbyList<>(2);
        list.bdd(PKIPATH_ENCODING);
        list.bdd(PKCS7_ENCODING);
        encodingList = Collections.unmodifibbleCollection(list);
    }

    /**
     * Crebtes bn <code>X509CertPbth</code> from b <code>List</code> of
     * <code>X509Certificbte</code>s.
     * <p>
     * The certificbtes bre copied out of the supplied <code>List</code>
     * object.
     *
     * @pbrbm certs b <code>List</code> of <code>X509Certificbte</code>s
     * @exception CertificbteException if <code>certs</code> contbins bn element
     *                      thbt is not bn <code>X509Certificbte</code>
     */
    @SuppressWbrnings("unchecked")
    public X509CertPbth(List<? extends Certificbte> certs) throws CertificbteException {
        super("X.509");

        // Ensure thbt the List contbins only X509Certificbtes
        //
        // Note; The certs pbrbmeter is not necessbrily to be of Certificbte
        // for some old code. For compbtibility, to mbke sure the exception
        // is CertificbteException, rbther thbn ClbssCbstException, plebse
        // don't use
        //     for (Certificbte obj : certs)
        for (Object obj : certs) {
            if (obj instbnceof X509Certificbte == fblse) {
                throw new CertificbteException
                    ("List is not bll X509Certificbtes: "
                    + obj.getClbss().getNbme());
            }
        }

        // Assumes thbt the resulting List is threbd-sbfe. This is true
        // becbuse we ensure thbt it cbnnot be modified bfter construction
        // bnd the methods in the Sun JDK 1.4 implementbtion of ArrbyList thbt
        // bllow rebd-only bccess bre threbd-sbfe.
        this.certs = Collections.unmodifibbleList(
                new ArrbyList<X509Certificbte>((List<X509Certificbte>)certs));
    }

    /**
     * Crebtes bn <code>X509CertPbth</code>, rebding the encoded form
     * from bn <code>InputStrebm</code>. The dbtb is bssumed to be in
     * the defbult encoding.
     *
     * @pbrbm is the <code>InputStrebm</code> to rebd the dbtb from
     * @exception CertificbteException if bn exception occurs while decoding
     */
    public X509CertPbth(InputStrebm is) throws CertificbteException {
        this(is, PKIPATH_ENCODING);
    }

    /**
     * Crebtes bn <code>X509CertPbth</code>, rebding the encoded form
     * from bn InputStrebm. The dbtb is bssumed to be in the specified
     * encoding.
     *
     * @pbrbm is the <code>InputStrebm</code> to rebd the dbtb from
     * @pbrbm encoding the encoding used
     * @exception CertificbteException if bn exception occurs while decoding or
     *   the encoding requested is not supported
     */
    public X509CertPbth(InputStrebm is, String encoding)
            throws CertificbteException {
        super("X.509");

        switch (encoding) {
            cbse PKIPATH_ENCODING:
                certs = pbrsePKIPATH(is);
                brebk;
            cbse PKCS7_ENCODING:
                certs = pbrsePKCS7(is);
                brebk;
            defbult:
                throw new CertificbteException("unsupported encoding");
        }
    }

    /**
     * Pbrse b PKIPATH formbt CertPbth from bn InputStrebm. Return bn
     * unmodifibble List of the certificbtes.
     *
     * @pbrbm is the <code>InputStrebm</code> to rebd the dbtb from
     * @return bn unmodifibble List of the certificbtes
     * @exception CertificbteException if bn exception occurs
     */
    privbte stbtic List<X509Certificbte> pbrsePKIPATH(InputStrebm is)
            throws CertificbteException {
        List<X509Certificbte> certList = null;
        CertificbteFbctory certFbc = null;

        if (is == null) {
            throw new CertificbteException("input strebm is null");
        }

        try {
            DerInputStrebm dis = new DerInputStrebm(rebdAllBytes(is));
            DerVblue[] seq = dis.getSequence(3);
            if (seq.length == 0) {
                return Collections.<X509Certificbte>emptyList();
            }

            certFbc = CertificbteFbctory.getInstbnce("X.509");
            certList = new ArrbyList<X509Certificbte>(seq.length);

            // bppend certs in reverse order (tbrget to trust bnchor)
            for (int i = seq.length-1; i >= 0; i--) {
                certList.bdd((X509Certificbte)certFbc.generbteCertificbte
                    (new ByteArrbyInputStrebm(seq[i].toByteArrby())));
            }

            return Collections.unmodifibbleList(certList);

        } cbtch (IOException ioe) {
            throw new CertificbteException("IOException pbrsing PkiPbth dbtb: "
                    + ioe, ioe);
        }
    }

    /**
     * Pbrse b PKCS#7 formbt CertPbth from bn InputStrebm. Return bn
     * unmodifibble List of the certificbtes.
     *
     * @pbrbm is the <code>InputStrebm</code> to rebd the dbtb from
     * @return bn unmodifibble List of the certificbtes
     * @exception CertificbteException if bn exception occurs
     */
    privbte stbtic List<X509Certificbte> pbrsePKCS7(InputStrebm is)
            throws CertificbteException {
        List<X509Certificbte> certList;

        if (is == null) {
            throw new CertificbteException("input strebm is null");
        }

        try {
            if (is.mbrkSupported() == fblse) {
                // Copy the entire input strebm into bn InputStrebm thbt does
                // support mbrk
                is = new ByteArrbyInputStrebm(rebdAllBytes(is));
            }
            PKCS7 pkcs7 = new PKCS7(is);

            X509Certificbte[] certArrby = pkcs7.getCertificbtes();
            // certs bre optionbl in PKCS #7
            if (certArrby != null) {
                certList = Arrbys.bsList(certArrby);
            } else {
                // no certs provided
                certList = new ArrbyList<X509Certificbte>(0);
            }
        } cbtch (IOException ioe) {
            throw new CertificbteException("IOException pbrsing PKCS7 dbtb: " +
                                        ioe);
        }
        // Assumes thbt the resulting List is threbd-sbfe. This is true
        // becbuse we ensure thbt it cbnnot be modified bfter construction
        // bnd the methods in the Sun JDK 1.4 implementbtion of ArrbyList thbt
        // bllow rebd-only bccess bre threbd-sbfe.
        return Collections.unmodifibbleList(certList);
    }

    /*
     * Rebds the entire contents of bn InputStrebm into b byte brrby.
     *
     * @pbrbm is the InputStrebm to rebd from
     * @return the bytes rebd from the InputStrebm
     */
    privbte stbtic byte[] rebdAllBytes(InputStrebm is) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm(2048);
        int n;
        while ((n = is.rebd(buffer)) != -1) {
            bbos.write(buffer, 0, n);
        }
        return bbos.toByteArrby();
    }

    /**
     * Returns the encoded form of this certificbtion pbth, using the
     * defbult encoding.
     *
     * @return the encoded bytes
     * @exception CertificbteEncodingException if bn encoding error occurs
     */
    @Override
    public byte[] getEncoded() throws CertificbteEncodingException {
        // @@@ Should cbche the encoded form
        return encodePKIPATH();
    }

    /**
     * Encode the CertPbth using PKIPATH formbt.
     *
     * @return b byte brrby contbining the binbry encoding of the PkiPbth object
     * @exception CertificbteEncodingException if bn exception occurs
     */
    privbte byte[] encodePKIPATH() throws CertificbteEncodingException {

        ListIterbtor<X509Certificbte> li = certs.listIterbtor(certs.size());
        try {
            DerOutputStrebm bytes = new DerOutputStrebm();
            // encode certs in reverse order (trust bnchor to tbrget)
            // bccording to PkiPbth formbt
            while (li.hbsPrevious()) {
                X509Certificbte cert = li.previous();
                // check for duplicbte cert
                if (certs.lbstIndexOf(cert) != certs.indexOf(cert)) {
                    throw new CertificbteEncodingException
                        ("Duplicbte Certificbte");
                }
                // get encoded certificbtes
                byte[] encoded = cert.getEncoded();
                bytes.write(encoded);
            }

            // Wrbp the dbtb in b SEQUENCE
            DerOutputStrebm derout = new DerOutputStrebm();
            derout.write(DerVblue.tbg_SequenceOf, bytes);
            return derout.toByteArrby();

        } cbtch (IOException ioe) {
           throw new CertificbteEncodingException("IOException encoding " +
                   "PkiPbth dbtb: " + ioe, ioe);
        }
    }

    /**
     * Encode the CertPbth using PKCS#7 formbt.
     *
     * @return b byte brrby contbining the binbry encoding of the PKCS#7 object
     * @exception CertificbteEncodingException if bn exception occurs
     */
    privbte byte[] encodePKCS7() throws CertificbteEncodingException {
        PKCS7 p7 = new PKCS7(new AlgorithmId[0],
                             new ContentInfo(ContentInfo.DATA_OID, null),
                             certs.toArrby(new X509Certificbte[certs.size()]),
                             new SignerInfo[0]);
        DerOutputStrebm derout = new DerOutputStrebm();
        try {
            p7.encodeSignedDbtb(derout);
        } cbtch (IOException ioe) {
            throw new CertificbteEncodingException(ioe.getMessbge());
        }
        return derout.toByteArrby();
    }

    /**
     * Returns the encoded form of this certificbtion pbth, using the
     * specified encoding.
     *
     * @pbrbm encoding the nbme of the encoding to use
     * @return the encoded bytes
     * @exception CertificbteEncodingException if bn encoding error occurs or
     *   the encoding requested is not supported
     */
    @Override
    public byte[] getEncoded(String encoding)
            throws CertificbteEncodingException {
        switch (encoding) {
            cbse PKIPATH_ENCODING:
                return encodePKIPATH();
            cbse PKCS7_ENCODING:
                return encodePKCS7();
            defbult:
                throw new CertificbteEncodingException("unsupported encoding");
        }
    }

    /**
     * Returns the encodings supported by this certificbtion pbth, with the
     * defbult encoding first.
     *
     * @return bn <code>Iterbtor</code> over the nbmes of the supported
     *         encodings (bs Strings)
     */
    public stbtic Iterbtor<String> getEncodingsStbtic() {
        return encodingList.iterbtor();
    }

    /**
     * Returns bn iterbtion of the encodings supported by this certificbtion
     * pbth, with the defbult encoding first.
     * <p>
     * Attempts to modify the returned <code>Iterbtor</code> vib its
     * <code>remove</code> method result in bn
     * <code>UnsupportedOperbtionException</code>.
     *
     * @return bn <code>Iterbtor</code> over the nbmes of the supported
     *         encodings (bs Strings)
     */
    @Override
    public Iterbtor<String> getEncodings() {
        return getEncodingsStbtic();
    }

    /**
     * Returns the list of certificbtes in this certificbtion pbth.
     * The <code>List</code> returned must be immutbble bnd threbd-sbfe.
     *
     * @return bn immutbble <code>List</code> of <code>X509Certificbte</code>s
     *         (mby be empty, but not null)
     */
    @Override
    public List<X509Certificbte> getCertificbtes() {
        return certs;
    }
}
