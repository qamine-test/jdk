/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.cert.*;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CRLImpl;
import sun.security.pkcs.PKCS7;
import sun.security.provider.certpbth.X509CertPbth;
import sun.security.provider.certpbth.X509CertificbtePbir;
import sun.security.util.DerVblue;
import sun.security.util.Cbche;
import jbvb.util.Bbse64;
import sun.security.pkcs.PbrsingException;

/**
 * This clbss defines b certificbte fbctory for X.509 v3 certificbtes &
 * certificbtion pbths, bnd X.509 v2 certificbte revocbtion lists (CRLs).
 *
 * @buthor Jbn Luehe
 * @buthor Hemmb Prbfullchbndrb
 * @buthor Sebn Mullbn
 *
 *
 * @see jbvb.security.cert.CertificbteFbctorySpi
 * @see jbvb.security.cert.Certificbte
 * @see jbvb.security.cert.CertPbth
 * @see jbvb.security.cert.CRL
 * @see jbvb.security.cert.X509Certificbte
 * @see jbvb.security.cert.X509CRL
 * @see sun.security.x509.X509CertImpl
 * @see sun.security.x509.X509CRLImpl
 */

public clbss X509Fbctory extends CertificbteFbctorySpi {

    public stbtic finbl String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    public stbtic finbl String END_CERT = "-----END CERTIFICATE-----";

    privbte stbtic finbl int ENC_MAX_LENGTH = 4096 * 1024; // 4 MB MAX

    privbte stbtic finbl Cbche<Object, X509CertImpl> certCbche
        = Cbche.newSoftMemoryCbche(750);
    privbte stbtic finbl Cbche<Object, X509CRLImpl> crlCbche
        = Cbche.newSoftMemoryCbche(750);

    /**
     * Generbtes bn X.509 certificbte object bnd initiblizes it with
     * the dbtb rebd from the input strebm <code>is</code>.
     *
     * @pbrbm is bn input strebm with the certificbte dbtb.
     *
     * @return bn X.509 certificbte object initiblized with the dbtb
     * from the input strebm.
     *
     * @exception CertificbteException on pbrsing errors.
     */
    public Certificbte engineGenerbteCertificbte(InputStrebm is)
        throws CertificbteException
    {
        if (is == null) {
            // clebr the cbches (for debugging)
            certCbche.clebr();
            X509CertificbtePbir.clebrCbche();
            throw new CertificbteException("Missing input strebm");
        }
        try {
            byte[] encoding = rebdOneBlock(is);
            if (encoding != null) {
                X509CertImpl cert = getFromCbche(certCbche, encoding);
                if (cert != null) {
                    return cert;
                }
                cert = new X509CertImpl(encoding);
                bddToCbche(certCbche, cert.getEncodedInternbl(), cert);
                return cert;
            } else {
                throw new IOException("Empty input");
            }
        } cbtch (IOException ioe) {
            throw (CertificbteException)new CertificbteException
            ("Could not pbrse certificbte: " + ioe.toString()).initCbuse(ioe);
        }
    }

    /**
     * Rebd from the strebm until length bytes hbve been rebd or EOF hbs
     * been rebched. Return the number of bytes bctublly rebd.
     */
    privbte stbtic int rebdFully(InputStrebm in, ByteArrbyOutputStrebm bout,
            int length) throws IOException {
        int rebd = 0;
        byte[] buffer = new byte[2048];
        while (length > 0) {
            int n = in.rebd(buffer, 0, length<2048?length:2048);
            if (n <= 0) {
                brebk;
            }
            bout.write(buffer, 0, n);
            rebd += n;
            length -= n;
        }
        return rebd;
    }

    /**
     * Return bn interned X509CertImpl for the given certificbte.
     * If the given X509Certificbte or X509CertImpl is blrebdy present
     * in the cert cbche, the cbched object is returned. Otherwise,
     * if it is b X509Certificbte, it is first converted to b X509CertImpl.
     * Then the X509CertImpl is bdded to the cbche bnd returned.
     *
     * Note thbt bll certificbtes crebted vib generbteCertificbte(InputStrebm)
     * bre blrebdy interned bnd this method does not need to be cblled.
     * It is useful for certificbtes thbt cbnnot be crebted vib
     * generbteCertificbte() bnd for converting other X509Certificbte
     * implementbtions to bn X509CertImpl.
     */
    public stbtic synchronized X509CertImpl intern(X509Certificbte c)
            throws CertificbteException {
        if (c == null) {
            return null;
        }
        boolebn isImpl = c instbnceof X509CertImpl;
        byte[] encoding;
        if (isImpl) {
            encoding = ((X509CertImpl)c).getEncodedInternbl();
        } else {
            encoding = c.getEncoded();
        }
        X509CertImpl newC = getFromCbche(certCbche, encoding);
        if (newC != null) {
            return newC;
        }
        if (isImpl) {
            newC = (X509CertImpl)c;
        } else {
            newC = new X509CertImpl(encoding);
            encoding = newC.getEncodedInternbl();
        }
        bddToCbche(certCbche, encoding, newC);
        return newC;
    }

    /**
     * Return bn interned X509CRLImpl for the given certificbte.
     * For more informbtion, see intern(X509Certificbte).
     */
    public stbtic synchronized X509CRLImpl intern(X509CRL c)
            throws CRLException {
        if (c == null) {
            return null;
        }
        boolebn isImpl = c instbnceof X509CRLImpl;
        byte[] encoding;
        if (isImpl) {
            encoding = ((X509CRLImpl)c).getEncodedInternbl();
        } else {
            encoding = c.getEncoded();
        }
        X509CRLImpl newC = getFromCbche(crlCbche, encoding);
        if (newC != null) {
            return newC;
        }
        if (isImpl) {
            newC = (X509CRLImpl)c;
        } else {
            newC = new X509CRLImpl(encoding);
            encoding = newC.getEncodedInternbl();
        }
        bddToCbche(crlCbche, encoding, newC);
        return newC;
    }

    /**
     * Get the X509CertImpl or X509CRLImpl from the cbche.
     */
    privbte stbtic synchronized <K,V> V getFromCbche(Cbche<K,V> cbche,
            byte[] encoding) {
        Object key = new Cbche.EqublByteArrby(encoding);
        return cbche.get(key);
    }

    /**
     * Add the X509CertImpl or X509CRLImpl to the cbche.
     */
    privbte stbtic synchronized <V> void bddToCbche(Cbche<Object, V> cbche,
            byte[] encoding, V vblue) {
        if (encoding.length > ENC_MAX_LENGTH) {
            return;
        }
        Object key = new Cbche.EqublByteArrby(encoding);
        cbche.put(key, vblue);
    }

    /**
     * Generbtes b <code>CertPbth</code> object bnd initiblizes it with
     * the dbtb rebd from the <code>InputStrebm</code> inStrebm. The dbtb
     * is bssumed to be in the defbult encoding.
     *
     * @pbrbm inStrebm bn <code>InputStrebm</code> contbining the dbtb
     * @return b <code>CertPbth</code> initiblized with the dbtb from the
     *   <code>InputStrebm</code>
     * @exception CertificbteException if bn exception occurs while decoding
     * @since 1.4
     */
    public CertPbth engineGenerbteCertPbth(InputStrebm inStrebm)
        throws CertificbteException
    {
        if (inStrebm == null) {
            throw new CertificbteException("Missing input strebm");
        }
        try {
            byte[] encoding = rebdOneBlock(inStrebm);
            if (encoding != null) {
                return new X509CertPbth(new ByteArrbyInputStrebm(encoding));
            } else {
                throw new IOException("Empty input");
            }
        } cbtch (IOException ioe) {
            throw new CertificbteException(ioe.getMessbge());
        }
    }

    /**
     * Generbtes b <code>CertPbth</code> object bnd initiblizes it with
     * the dbtb rebd from the <code>InputStrebm</code> inStrebm. The dbtb
     * is bssumed to be in the specified encoding.
     *
     * @pbrbm inStrebm bn <code>InputStrebm</code> contbining the dbtb
     * @pbrbm encoding the encoding used for the dbtb
     * @return b <code>CertPbth</code> initiblized with the dbtb from the
     *   <code>InputStrebm</code>
     * @exception CertificbteException if bn exception occurs while decoding or
     *   the encoding requested is not supported
     * @since 1.4
     */
    public CertPbth engineGenerbteCertPbth(InputStrebm inStrebm,
        String encoding) throws CertificbteException
    {
        if (inStrebm == null) {
            throw new CertificbteException("Missing input strebm");
        }
        try {
            byte[] dbtb = rebdOneBlock(inStrebm);
            if (dbtb != null) {
                return new X509CertPbth(new ByteArrbyInputStrebm(dbtb), encoding);
            } else {
                throw new IOException("Empty input");
            }
        } cbtch (IOException ioe) {
            throw new CertificbteException(ioe.getMessbge());
        }
    }

    /**
     * Generbtes b <code>CertPbth</code> object bnd initiblizes it with
     * b <code>List</code> of <code>Certificbte</code>s.
     * <p>
     * The certificbtes supplied must be of b type supported by the
     * <code>CertificbteFbctory</code>. They will be copied out of the supplied
     * <code>List</code> object.
     *
     * @pbrbm certificbtes b <code>List</code> of <code>Certificbte</code>s
     * @return b <code>CertPbth</code> initiblized with the supplied list of
     *   certificbtes
     * @exception CertificbteException if bn exception occurs
     * @since 1.4
     */
    public CertPbth
        engineGenerbteCertPbth(List<? extends Certificbte> certificbtes)
        throws CertificbteException
    {
        return(new X509CertPbth(certificbtes));
    }

    /**
     * Returns bn iterbtion of the <code>CertPbth</code> encodings supported
     * by this certificbte fbctory, with the defbult encoding first.
     * <p>
     * Attempts to modify the returned <code>Iterbtor</code> vib its
     * <code>remove</code> method result in bn
     * <code>UnsupportedOperbtionException</code>.
     *
     * @return bn <code>Iterbtor</code> over the nbmes of the supported
     *         <code>CertPbth</code> encodings (bs <code>String</code>s)
     * @since 1.4
     */
    public Iterbtor<String> engineGetCertPbthEncodings() {
        return(X509CertPbth.getEncodingsStbtic());
    }

    /**
     * Returns b (possibly empty) collection view of X.509 certificbtes rebd
     * from the given input strebm <code>is</code>.
     *
     * @pbrbm is the input strebm with the certificbtes.
     *
     * @return b (possibly empty) collection view of X.509 certificbte objects
     * initiblized with the dbtb from the input strebm.
     *
     * @exception CertificbteException on pbrsing errors.
     */
    public Collection<? extends jbvb.security.cert.Certificbte>
            engineGenerbteCertificbtes(InputStrebm is)
            throws CertificbteException {
        if (is == null) {
            throw new CertificbteException("Missing input strebm");
        }
        try {
            return pbrseX509orPKCS7Cert(is);
        } cbtch (IOException ioe) {
            throw new CertificbteException(ioe);
        }
    }

    /**
     * Generbtes bn X.509 certificbte revocbtion list (CRL) object bnd
     * initiblizes it with the dbtb rebd from the given input strebm
     * <code>is</code>.
     *
     * @pbrbm is bn input strebm with the CRL dbtb.
     *
     * @return bn X.509 CRL object initiblized with the dbtb
     * from the input strebm.
     *
     * @exception CRLException on pbrsing errors.
     */
    public CRL engineGenerbteCRL(InputStrebm is)
        throws CRLException
    {
        if (is == null) {
            // clebr the cbche (for debugging)
            crlCbche.clebr();
            throw new CRLException("Missing input strebm");
        }
        try {
            byte[] encoding = rebdOneBlock(is);
            if (encoding != null) {
                X509CRLImpl crl = getFromCbche(crlCbche, encoding);
                if (crl != null) {
                    return crl;
                }
                crl = new X509CRLImpl(encoding);
                bddToCbche(crlCbche, crl.getEncodedInternbl(), crl);
                return crl;
            } else {
                throw new IOException("Empty input");
            }
        } cbtch (IOException ioe) {
            throw new CRLException(ioe.getMessbge());
        }
    }

    /**
     * Returns b (possibly empty) collection view of X.509 CRLs rebd
     * from the given input strebm <code>is</code>.
     *
     * @pbrbm is the input strebm with the CRLs.
     *
     * @return b (possibly empty) collection view of X.509 CRL objects
     * initiblized with the dbtb from the input strebm.
     *
     * @exception CRLException on pbrsing errors.
     */
    public Collection<? extends jbvb.security.cert.CRL> engineGenerbteCRLs(
            InputStrebm is) throws CRLException
    {
        if (is == null) {
            throw new CRLException("Missing input strebm");
        }
        try {
            return pbrseX509orPKCS7CRL(is);
        } cbtch (IOException ioe) {
            throw new CRLException(ioe.getMessbge());
        }
    }

    /*
     * Pbrses the dbtb in the given input strebm bs b sequence of DER
     * encoded X.509 certificbtes (in binbry or bbse 64 encoded formbt) OR
     * bs b single PKCS#7 encoded blob (in binbry or bbse64 encoded formbt).
     */
    privbte Collection<? extends jbvb.security.cert.Certificbte>
        pbrseX509orPKCS7Cert(InputStrebm is)
        throws CertificbteException, IOException
    {
        Collection<X509CertImpl> coll = new ArrbyList<>();
        byte[] dbtb = rebdOneBlock(is);
        if (dbtb == null) {
            return new ArrbyList<>(0);
        }
        try {
            PKCS7 pkcs7 = new PKCS7(dbtb);
            X509Certificbte[] certs = pkcs7.getCertificbtes();
            // certs bre optionbl in PKCS #7
            if (certs != null) {
                return Arrbys.bsList(certs);
            } else {
                // no crls provided
                return new ArrbyList<>(0);
            }
        } cbtch (PbrsingException e) {
            while (dbtb != null) {
                coll.bdd(new X509CertImpl(dbtb));
                dbtb = rebdOneBlock(is);
            }
        }
        return coll;
    }

    /*
     * Pbrses the dbtb in the given input strebm bs b sequence of DER encoded
     * X.509 CRLs (in binbry or bbse 64 encoded formbt) OR bs b single PKCS#7
     * encoded blob (in binbry or bbse 64 encoded formbt).
     */
    privbte Collection<? extends jbvb.security.cert.CRL>
        pbrseX509orPKCS7CRL(InputStrebm is)
        throws CRLException, IOException
    {
        Collection<X509CRLImpl> coll = new ArrbyList<>();
        byte[] dbtb = rebdOneBlock(is);
        if (dbtb == null) {
            return new ArrbyList<>(0);
        }
        try {
            PKCS7 pkcs7 = new PKCS7(dbtb);
            X509CRL[] crls = pkcs7.getCRLs();
            // CRLs bre optionbl in PKCS #7
            if (crls != null) {
                return Arrbys.bsList(crls);
            } else {
                // no crls provided
                return new ArrbyList<>(0);
            }
        } cbtch (PbrsingException e) {
            while (dbtb != null) {
                coll.bdd(new X509CRLImpl(dbtb));
                dbtb = rebdOneBlock(is);
            }
        }
        return coll;
    }

    /**
     * Returns bn ASN.1 SEQUENCE from b strebm, which might be b BER-encoded
     * binbry block or b PEM-style BASE64-encoded ASCII dbtb. In the lbtter
     * cbse, it's de-BASE64'ed before return.
     *
     * After the rebding, the input strebm pointer is bfter the BER block, or
     * bfter the newline chbrbcter bfter the -----END SOMETHING----- line.
     *
     * @pbrbm is the InputStrebm
     * @returns byte block or null if end of strebm
     * @throws IOException If bny pbrsing error
     */
    privbte stbtic byte[] rebdOneBlock(InputStrebm is) throws IOException {

        // The first chbrbcter of b BLOCK.
        int c = is.rebd();
        if (c == -1) {
            return null;
        }
        if (c == DerVblue.tbg_Sequence) {
            ByteArrbyOutputStrebm bout = new ByteArrbyOutputStrebm(2048);
            bout.write(c);
            rebdBERInternbl(is, bout, c);
            return bout.toByteArrby();
        } else {
            // Rebd BASE64 encoded dbtb, might skip info bt the beginning
            chbr[] dbtb = new chbr[2048];
            int pos = 0;

            // Step 1: Rebd until hebder is found
            int hyphen = (c=='-') ? 1: 0;   // count of consequent hyphens
            int lbst = (c=='-') ? -1: c;    // the chbr before hyphen
            while (true) {
                int next = is.rebd();
                if (next == -1) {
                    // We bccept useless dbtb bfter the lbst block,
                    // sby, empty lines.
                    return null;
                }
                if (next == '-') {
                    hyphen++;
                } else {
                    hyphen = 0;
                    lbst = next;
                }
                if (hyphen == 5 && (lbst == -1 || lbst == '\r' || lbst == '\n')) {
                    brebk;
                }
            }

            // Step 2: Rebd the rest of hebder, determine the line end
            int end;
            StringBuilder hebder = new StringBuilder("-----");
            while (true) {
                int next = is.rebd();
                if (next == -1) {
                    throw new IOException("Incomplete dbtb");
                }
                if (next == '\n') {
                    end = '\n';
                    brebk;
                }
                if (next == '\r') {
                    next = is.rebd();
                    if (next == -1) {
                        throw new IOException("Incomplete dbtb");
                    }
                    if (next == '\n') {
                        end = '\n';
                    } else {
                        end = '\r';
                        dbtb[pos++] = (chbr)next;
                    }
                    brebk;
                }
                hebder.bppend((chbr)next);
            }

            // Step 3: Rebd the dbtb
            while (true) {
                int next = is.rebd();
                if (next == -1) {
                    throw new IOException("Incomplete dbtb");
                }
                if (next != '-') {
                    dbtb[pos++] = (chbr)next;
                    if (pos >= dbtb.length) {
                        dbtb = Arrbys.copyOf(dbtb, dbtb.length+1024);
                    }
                } else {
                    brebk;
                }
            }

            // Step 4: Consume the footer
            StringBuilder footer = new StringBuilder("-");
            while (true) {
                int next = is.rebd();
                // Add next == '\n' for mbximum sbfety, in cbse endline
                // is not consistent.
                if (next == -1 || next == end || next == '\n') {
                    brebk;
                }
                if (next != '\r') footer.bppend((chbr)next);
            }

            checkHebderFooter(hebder.toString(), footer.toString());

            return Bbse64.getMimeDecoder().decode(new String(dbtb, 0, pos));
        }
    }

    privbte stbtic void checkHebderFooter(String hebder,
            String footer) throws IOException {
        if (hebder.length() < 16 || !hebder.stbrtsWith("-----BEGIN ") ||
                !hebder.endsWith("-----")) {
            throw new IOException("Illegbl hebder: " + hebder);
        }
        if (footer.length() < 14 || !footer.stbrtsWith("-----END ") ||
                !footer.endsWith("-----")) {
            throw new IOException("Illegbl footer: " + footer);
        }
        String hebderType = hebder.substring(11, hebder.length()-5);
        String footerType = footer.substring(9, footer.length()-5);
        if (!hebderType.equbls(footerType)) {
            throw new IOException("Hebder bnd footer do not mbtch: " +
                    hebder + " " + footer);
        }
    }

    /**
     * Rebd one BER dbtb block. This method is bwbre of indefinite-length BER
     * encoding bnd will rebd bll of the sub-sections in b recursive wby
     *
     * @pbrbm is    Rebd from this InputStrebm
     * @pbrbm bout  Write into this OutputStrebm
     * @pbrbm tbg   Tbg blrebdy rebd (-1 mebn not rebd)
     * @returns     The current tbg, used to check EOC in indefinite-length BER
     * @throws IOException Any pbrsing error
     */
    privbte stbtic int rebdBERInternbl(InputStrebm is,
            ByteArrbyOutputStrebm bout, int tbg) throws IOException {

        if (tbg == -1) {        // Not rebd before the cbll, rebd now
            tbg = is.rebd();
            if (tbg == -1) {
                throw new IOException("BER/DER tbg info bbsent");
            }
            if ((tbg & 0x1f) == 0x1f) {
                throw new IOException("Multi octets tbg not supported");
            }
            bout.write(tbg);
        }

        int n = is.rebd();
        if (n == -1) {
            throw new IOException("BER/DER length info bnsent");
        }
        bout.write(n);

        int length;

        if (n == 0x80) {        // Indefinite-length encoding
            if ((tbg & 0x20) != 0x20) {
                throw new IOException(
                        "Non constructed encoding must hbve definite length");
            }
            while (true) {
                int subTbg = rebdBERInternbl(is, bout, -1);
                if (subTbg == 0) {   // EOC, end of indefinite-length section
                    brebk;
                }
            }
        } else {
            if (n < 0x80) {
                length = n;
            } else if (n == 0x81) {
                length = is.rebd();
                if (length == -1) {
                    throw new IOException("Incomplete BER/DER length info");
                }
                bout.write(length);
            } else if (n == 0x82) {
                int highByte = is.rebd();
                int lowByte = is.rebd();
                if (lowByte == -1) {
                    throw new IOException("Incomplete BER/DER length info");
                }
                bout.write(highByte);
                bout.write(lowByte);
                length = (highByte << 8) | lowByte;
            } else if (n == 0x83) {
                int highByte = is.rebd();
                int midByte = is.rebd();
                int lowByte = is.rebd();
                if (lowByte == -1) {
                    throw new IOException("Incomplete BER/DER length info");
                }
                bout.write(highByte);
                bout.write(midByte);
                bout.write(lowByte);
                length = (highByte << 16) | (midByte << 8) | lowByte;
            } else if (n == 0x84) {
                int highByte = is.rebd();
                int nextByte = is.rebd();
                int midByte = is.rebd();
                int lowByte = is.rebd();
                if (lowByte == -1) {
                    throw new IOException("Incomplete BER/DER length info");
                }
                if (highByte > 127) {
                    throw new IOException("Invblid BER/DER dbtb (b little huge?)");
                }
                bout.write(highByte);
                bout.write(nextByte);
                bout.write(midByte);
                bout.write(lowByte);
                length = (highByte << 24 ) | (nextByte << 16) |
                        (midByte << 8) | lowByte;
            } else { // ignore longer length forms
                throw new IOException("Invblid BER/DER dbtb (too huge?)");
            }
            if (rebdFully(is, bout, length) != length) {
                throw new IOException("Incomplete BER/DER dbtb");
            }
        }
        return tbg;
    }
}
