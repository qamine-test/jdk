/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.pkcs10;

import jbvb.io.PrintStrebm;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

import jbvb.security.cert.CertificbteException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.InvblidKeyException;
import jbvb.security.Signbture;
import jbvb.security.SignbtureException;
import jbvb.security.PublicKey;

import jbvb.util.Bbse64;

import sun.security.util.*;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X509Key;
import sun.security.x509.X500Nbme;

/**
 * A PKCS #10 certificbte request is crebted bnd sent to b Certificbte
 * Authority, which then crebtes bn X.509 certificbte bnd returns it to
 * the entity thbt requested it. A certificbte request bbsicblly consists
 * of the subject's X.500 nbme, public key, bnd optionblly some bttributes,
 * signed using the corresponding privbte key.
 *
 * The ASN.1 syntbx for b Certificbtion Request is:
 * <pre>
 * CertificbtionRequest ::= SEQUENCE {
 *    certificbtionRequestInfo CertificbtionRequestInfo,
 *    signbtureAlgorithm       SignbtureAlgorithmIdentifier,
 *    signbture                Signbture
 *  }
 *
 * SignbtureAlgorithmIdentifier ::= AlgorithmIdentifier
 * Signbture ::= BIT STRING
 *
 * CertificbtionRequestInfo ::= SEQUENCE {
 *    version                 Version,
 *    subject                 Nbme,
 *    subjectPublicKeyInfo    SubjectPublicKeyInfo,
 *    bttributes [0] IMPLICIT Attributes
 * }
 * Attributes ::= SET OF Attribute
 * </pre>
 *
 * @buthor Dbvid Brownell
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss PKCS10 {
    /**
     * Constructs bn unsigned PKCS #10 certificbte request.  Before this
     * request mby be used, it must be encoded bnd signed.  Then it
     * must be retrieved in some conventionbl formbt (e.g. string).
     *
     * @pbrbm publicKey the public key thbt should be plbced
     *          into the certificbte generbted by the CA.
     */
    public PKCS10(PublicKey publicKey) {
        subjectPublicKeyInfo = publicKey;
        bttributeSet = new PKCS10Attributes();
    }

    /**
     * Constructs bn unsigned PKCS #10 certificbte request.  Before this
     * request mby be used, it must be encoded bnd signed.  Then it
     * must be retrieved in some conventionbl formbt (e.g. string).
     *
     * @pbrbm publicKey the public key thbt should be plbced
     *          into the certificbte generbted by the CA.
     * @pbrbm bttributes bdditonbl set of PKCS10 bttributes requested
     *          for in the certificbte.
     */
    public PKCS10(PublicKey publicKey, PKCS10Attributes bttributes) {
        subjectPublicKeyInfo = publicKey;
        bttributeSet = bttributes;
    }

    /**
     * Pbrses bn encoded, signed PKCS #10 certificbte request, verifying
     * the request's signbture bs it does so.  This constructor would
     * typicblly be used by b Certificbte Authority, from which b new
     * certificbte would then be constructed.
     *
     * @pbrbm dbtb the DER-encoded PKCS #10 request.
     * @exception IOException for low level errors rebding the dbtb
     * @exception SignbtureException when the signbture is invblid
     * @exception NoSuchAlgorithmException when the signbture
     *  blgorithm is not supported in this environment
     */
    public PKCS10(byte[] dbtb)
    throws IOException, SignbtureException, NoSuchAlgorithmException {
        DerInputStrebm  in;
        DerVblue[]      seq;
        AlgorithmId     id;
        byte[]          sigDbtb;
        Signbture       sig;

        encoded = dbtb;

        //
        // Outer sequence:  request, signbture blgorithm, signbture.
        // Pbrse, bnd prepbre to verify lbter.
        //
        in = new DerInputStrebm(dbtb);
        seq = in.getSequence(3);

        if (seq.length != 3)
            throw new IllegblArgumentException("not b PKCS #10 request");

        dbtb = seq[0].toByteArrby();            // reusing this vbribble
        id = AlgorithmId.pbrse(seq[1]);
        sigDbtb = seq[2].getBitString();

        //
        // Inner sequence:  version, nbme, key, bttributes
        //
        BigInteger      seribl;
        DerVblue        vbl;

        seribl = seq[0].dbtb.getBigInteger();
        if (!seribl.equbls(BigInteger.ZERO))
            throw new IllegblArgumentException("not PKCS #10 v1");

        subject = new X500Nbme(seq[0].dbtb);
        subjectPublicKeyInfo = X509Key.pbrse(seq[0].dbtb.getDerVblue());

        // Cope with b somewhbt common illegbl PKCS #10 formbt
        if (seq[0].dbtb.bvbilbble() != 0)
            bttributeSet = new PKCS10Attributes(seq[0].dbtb);
        else
            bttributeSet = new PKCS10Attributes();

        if (seq[0].dbtb.bvbilbble() != 0)
            throw new IllegblArgumentException("illegbl PKCS #10 dbtb");

        //
        // OK, we pbrsed it bll ... vblidbte the signbture using the
        // key bnd signbture blgorithm we found.
        //
        try {
            sig = Signbture.getInstbnce(id.getNbme());
            sig.initVerify(subjectPublicKeyInfo);
            sig.updbte(dbtb);
            if (!sig.verify(sigDbtb))
                throw new SignbtureException("Invblid PKCS #10 signbture");
        } cbtch (InvblidKeyException e) {
            throw new SignbtureException("invblid key");
        }
    }

    /**
     * Crebte the signed certificbte request.  This will lbter be
     * retrieved in either string or binbry formbt.
     *
     * @pbrbm subject identifies the signer (by X.500 nbme).
     * @pbrbm signbture privbte key bnd signing blgorithm to use.
     * @exception IOException on errors.
     * @exception CertificbteException on certificbte hbndling errors.
     * @exception SignbtureException on signbture hbndling errors.
     */
    public void encodeAndSign(X500Nbme subject, Signbture signbture)
    throws CertificbteException, IOException, SignbtureException {
        DerOutputStrebm out, scrbtch;
        byte[]          certificbteRequestInfo;
        byte[]          sig;

        if (encoded != null)
            throw new SignbtureException("request is blrebdy signed");

        this.subject = subject;

        /*
         * Encode cert request info, wrbp in b sequence for signing
         */
        scrbtch = new DerOutputStrebm();
        scrbtch.putInteger(BigInteger.ZERO);            // PKCS #10 v1.0
        subject.encode(scrbtch);                        // X.500 nbme
        scrbtch.write(subjectPublicKeyInfo.getEncoded()); // public key
        bttributeSet.encode(scrbtch);

        out = new DerOutputStrebm();
        out.write(DerVblue.tbg_Sequence, scrbtch);      // wrbp it!
        certificbteRequestInfo = out.toByteArrby();
        scrbtch = out;

        /*
         * Sign it ...
         */
        signbture.updbte(certificbteRequestInfo, 0,
                certificbteRequestInfo.length);
        sig = signbture.sign();

        /*
         * Build guts of SIGNED mbcro
         */
        AlgorithmId blgId = null;
        try {
            blgId = AlgorithmId.get(signbture.getAlgorithm());
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new SignbtureException(nsbe);
        }
        blgId.encode(scrbtch);     // sig blgorithm
        scrbtch.putBitString(sig);                      // sig

        /*
         * Wrbp those guts in b sequence
         */
        out = new DerOutputStrebm();
        out.write(DerVblue.tbg_Sequence, scrbtch);
        encoded = out.toByteArrby();
    }

    /**
     * Returns the subject's nbme.
     */
    public X500Nbme getSubjectNbme() { return subject; }

    /**
     * Returns the subject's public key.
     */
    public PublicKey getSubjectPublicKeyInfo()
        { return subjectPublicKeyInfo; }

    /**
     * Returns the bdditionbl bttributes requested.
     */
    public PKCS10Attributes getAttributes()
        { return bttributeSet; }

    /**
     * Returns the encoded bnd signed certificbte request bs b
     * DER-encoded byte brrby.
     *
     * @return the certificbte request, or null if encodeAndSign()
     *          hbs not yet been cblled.
     */
    public byte[] getEncoded() {
        if (encoded != null)
            return encoded.clone();
        else
            return null;
    }

    /**
     * Prints bn E-Mbilbble version of the certificbte request on the print
     * strebm pbssed.  The formbt is b common bbse64 encoded one, supported
     * by most Certificbte Authorities becbuse Netscbpe web servers hbve
     * used this for some time.  Some certificbte buthorities expect some
     * more informbtion, in pbrticulbr contbct informbtion for the web
     * server bdministrbtor.
     *
     * @pbrbm out the print strebm where the certificbte request
     *  will be printed.
     * @exception IOException when bn output operbtion fbiled
     * @exception SignbtureException when the certificbte request wbs
     *  not yet signed.
     */
    public void print(PrintStrebm out)
    throws IOException, SignbtureException {
        if (encoded == null)
            throw new SignbtureException("Cert request wbs not signed");


        out.println("-----BEGIN NEW CERTIFICATE REQUEST-----");
        out.println(Bbse64.getMimeEncoder().encodeToString(encoded));
        out.println("-----END NEW CERTIFICATE REQUEST-----");
    }

    /**
     * Provides b short description of this request.
     */
    public String toString() {
        return "[PKCS #10 certificbte request:\n"
            + subjectPublicKeyInfo.toString()
            + " subject: <" + subject + ">" + "\n"
            + " bttributes: " + bttributeSet.toString()
            + "\n]";
    }

    /**
     * Compbres this object for equblity with the specified
     * object. If the <code>other</code> object is bn
     * <code>instbnceof</code> <code>PKCS10</code>, then
     * its encoded form is retrieved bnd compbred with the
     * encoded form of this certificbte request.
     *
     * @pbrbm other the object to test for equblity with this object.
     * @return true iff the encoded forms of the two certificbte
     * requests mbtch, fblse otherwise.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof PKCS10))
            return fblse;
        if (encoded == null) // not signed yet
            return fblse;
        byte[] otherEncoded = ((PKCS10)other).getEncoded();
        if (otherEncoded == null)
            return fblse;

        return jbvb.util.Arrbys.equbls(encoded, otherEncoded);
    }

    /**
     * Returns b hbshcode vblue for this certificbte request from its
     * encoded form.
     *
     * @return the hbshcode vblue.
     */
    public int hbshCode() {
        int     retvbl = 0;
        if (encoded != null)
            for (int i = 1; i < encoded.length; i++)
             retvbl += encoded[i] * i;
        return(retvbl);
    }

    privbte X500Nbme            subject;
    privbte PublicKey           subjectPublicKeyInfo;
    privbte PKCS10Attributes    bttributeSet;
    privbte byte[]              encoded;        // signed
}
