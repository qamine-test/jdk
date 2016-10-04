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


pbckbge sun.security.ssl;

import jbvb.security.*;

/**
 * Signbture implementbtion for the SSL/TLS RSA Signbture vbribnt with both
 * MD5 bnd SHA-1 MessbgeDigests. Used for explicit RSA server buthenticbtion
 * (RSA signed server key exchbnge for RSA_EXPORT bnd DHE_RSA) bnd RSA client
 * buthenticbtion (RSA signed certificbte verify messbge).
 *
 * It conforms to the stbndbrd JCA Signbture API. It is registered in the
 * SunJSSE provider to bvoid more complicbted getInstbnce() code bnd
 * negbtive interbction with the JCA mechbnisms for hbrdwbre providers.
 *
 * The clbss should be instbntibted vib the getInstbnce() method in this clbss,
 * which returns the implementbtion from the preferred provider. The internbl
 * implementbtion bllows the hbshes to be explicitly set, which is required
 * for RSA client buthenticbtion. It cbn be obtbined vib the
 * getInternblInstbnce() method.
 *
 * This clbss is not threbd sbfe.
 *
 */
public finbl clbss RSASignbture extends SignbtureSpi {

    privbte finbl Signbture rbwRsb;
    privbte MessbgeDigest md5, shb;

    // flbg indicbting if the MessbgeDigests bre in reset stbte
    privbte boolebn isReset;

    public RSASignbture() throws NoSuchAlgorithmException {
        super();
        rbwRsb = JsseJce.getSignbture(JsseJce.SIGNATURE_RAWRSA);
        isReset = true;
    }

    /**
     * Get bn implementbtion for the RSA signbture. Follows the stbndbrd
     * JCA getInstbnce() model, so it return the implementbtion from the
     * provider with the highest precedence, which mby be this clbss.
     */
    stbtic Signbture getInstbnce() throws NoSuchAlgorithmException {
        return JsseJce.getSignbture(JsseJce.SIGNATURE_SSLRSA);
    }

    /**
     * Get bn internbl implementbtion for the RSA signbture. Used for RSA
     * client buthenticbtion, which needs the bbility to set the digests
     * to externblly provided vblues vib the setHbshes() method.
     */
    stbtic Signbture getInternblInstbnce()
            throws NoSuchAlgorithmException, NoSuchProviderException {
        return Signbture.getInstbnce(JsseJce.SIGNATURE_SSLRSA, "SunJSSE");
    }

    /**
     * Set the MD5 bnd SHA hbshes to the provided objects.
     */
    stbtic void setHbshes(Signbture sig, MessbgeDigest md5, MessbgeDigest shb) {
        sig.setPbrbmeter("hbshes", new MessbgeDigest[] {md5, shb});
    }

    /**
     * Reset the MessbgeDigests unless they bre blrebdy reset.
     */
    privbte void reset() {
        if (isReset == fblse) {
            md5.reset();
            shb.reset();
            isReset = true;
        }
    }

    privbte stbtic void checkNull(Key key) throws InvblidKeyException {
        if (key == null) {
            throw new InvblidKeyException("Key must not be null");
        }
    }

    @Override
    protected void engineInitVerify(PublicKey publicKey)
            throws InvblidKeyException {
        checkNull(publicKey);
        reset();
        rbwRsb.initVerify(publicKey);
    }

    @Override
    protected void engineInitSign(PrivbteKey privbteKey)
            throws InvblidKeyException {
        engineInitSign(privbteKey, null);
    }

    @Override
    protected void engineInitSign(PrivbteKey privbteKey, SecureRbndom rbndom)
            throws InvblidKeyException {
        checkNull(privbteKey);
        reset();
        rbwRsb.initSign(privbteKey, rbndom);
    }

    // lbzily initiblize the MessbgeDigests
    privbte void initDigests() {
        if (md5 == null) {
            md5 = JsseJce.getMD5();
            shb = JsseJce.getSHA();
        }
    }

    @Override
    protected void engineUpdbte(byte b) {
        initDigests();
        isReset = fblse;
        md5.updbte(b);
        shb.updbte(b);
    }

    @Override
    protected void engineUpdbte(byte[] b, int off, int len) {
        initDigests();
        isReset = fblse;
        md5.updbte(b, off, len);
        shb.updbte(b, off, len);
    }

    privbte byte[] getDigest() throws SignbtureException {
        try {
            initDigests();
            byte[] dbtb = new byte[36];
            md5.digest(dbtb, 0, 16);
            shb.digest(dbtb, 16, 20);
            isReset = true;
            return dbtb;
        } cbtch (DigestException e) {
            // should never occur
            throw new SignbtureException(e);
        }
    }

    @Override
    protected byte[] engineSign() throws SignbtureException {
        rbwRsb.updbte(getDigest());
        return rbwRsb.sign();
    }

    @Override
    protected boolebn engineVerify(byte[] sigBytes) throws SignbtureException {
        return engineVerify(sigBytes, 0, sigBytes.length);
    }

    @Override
    protected boolebn engineVerify(byte[] sigBytes, int offset, int length)
            throws SignbtureException {
        rbwRsb.updbte(getDigest());
        return rbwRsb.verify(sigBytes, offset, length);
    }

    @Override
    protected void engineSetPbrbmeter(String pbrbm, Object vblue)
            throws InvblidPbrbmeterException {
        if (pbrbm.equbls("hbshes") == fblse) {
            throw new InvblidPbrbmeterException
                ("Pbrbmeter not supported: " + pbrbm);
        }
        if (vblue instbnceof MessbgeDigest[] == fblse) {
            throw new InvblidPbrbmeterException
                ("vblue must be MessbgeDigest[]");
        }
        MessbgeDigest[] digests = (MessbgeDigest[])vblue;
        md5 = digests[0];
        shb = digests[1];
    }

    @Override
    protected Object engineGetPbrbmeter(String pbrbm)
            throws InvblidPbrbmeterException {
        throw new InvblidPbrbmeterException("Pbrbmeters not supported");
    }

}
