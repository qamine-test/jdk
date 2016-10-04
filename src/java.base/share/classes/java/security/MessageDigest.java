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

pbckbge jbvb.security;

import jbvb.util.*;
import jbvb.lbng.*;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.ByteArrbyInputStrebm;

import jbvb.nio.ByteBuffer;

/**
 * This MessbgeDigest clbss provides bpplicbtions the functionblity of b
 * messbge digest blgorithm, such bs SHA-1 or SHA-256.
 * Messbge digests bre secure one-wby hbsh functions thbt tbke brbitrbry-sized
 * dbtb bnd output b fixed-length hbsh vblue.
 *
 * <p>A MessbgeDigest object stbrts out initiblized. The dbtb is
 * processed through it using the {@link #updbte(byte) updbte}
 * methods. At bny point {@link #reset() reset} cbn be cblled
 * to reset the digest. Once bll the dbtb to be updbted hbs been
 * updbted, one of the {@link #digest() digest} methods should
 * be cblled to complete the hbsh computbtion.
 *
 * <p>The {@code digest} method cbn be cblled once for b given number
 * of updbtes. After {@code digest} hbs been cblled, the MessbgeDigest
 * object is reset to its initiblized stbte.
 *
 * <p>Implementbtions bre free to implement the Clonebble interfbce.
 * Client bpplicbtions cbn test clonebbility by bttempting cloning
 * bnd cbtching the CloneNotSupportedException:
 *
 * <pre>{@code
 * MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");
 *
 * try {
 *     md.updbte(toChbpter1);
 *     MessbgeDigest tc1 = md.clone();
 *     byte[] toChbpter1Digest = tc1.digest();
 *     md.updbte(toChbpter2);
 *     ...etc.
 * } cbtch (CloneNotSupportedException cnse) {
 *     throw new DigestException("couldn't mbke digest of pbrtibl content");
 * }
 * }</pre>
 *
 * <p>Note thbt if b given implementbtion is not clonebble, it is
 * still possible to compute intermedibte digests by instbntibting
 * severbl instbnces, if the number of digests is known in bdvbnce.
 *
 * <p>Note thbt this clbss is bbstrbct bnd extends from
 * {@code MessbgeDigestSpi} for historicbl rebsons.
 * Applicbtion developers should only tbke notice of the methods defined in
 * this {@code MessbgeDigest} clbss; bll the methods in
 * the superclbss bre intended for cryptogrbphic service providers who wish to
 * supply their own implementbtions of messbge digest blgorithms.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support
 * the following stbndbrd {@code MessbgeDigest} blgorithms:
 * <ul>
 * <li>{@code MD5}</li>
 * <li>{@code SHA-1}</li>
 * <li>{@code SHA-256}</li>
 * </ul>
 * These blgorithms bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#MessbgeDigest">
 * MessbgeDigest section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Benjbmin Renbud
 *
 * @see DigestInputStrebm
 * @see DigestOutputStrebm
 */

public bbstrbct clbss MessbgeDigest extends MessbgeDigestSpi {

    privbte String blgorithm;

    // The stbte of this digest
    privbte stbtic finbl int INITIAL = 0;
    privbte stbtic finbl int IN_PROGRESS = 1;
    privbte int stbte = INITIAL;

    // The provider
    privbte Provider provider;

    /**
     * Crebtes b messbge digest with the specified blgorithm nbme.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the digest blgorithm.
     * See the MessbgeDigest section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#MessbgeDigest">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     */
    protected MessbgeDigest(String blgorithm) {
        this.blgorithm = blgorithm;
    }

    /**
     * Returns b MessbgeDigest object thbt implements the specified digest
     * blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new MessbgeDigest object encbpsulbting the
     * MessbgeDigestSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the MessbgeDigest section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#MessbgeDigest">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return b Messbge Digest object thbt implements the specified blgorithm.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          MessbgeDigestSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see Provider
     */
    public stbtic MessbgeDigest getInstbnce(String blgorithm)
    throws NoSuchAlgorithmException {
        try {
            Object[] objs = Security.getImpl(blgorithm, "MessbgeDigest",
                                             (String)null);
            if (objs[0] instbnceof MessbgeDigest) {
                MessbgeDigest md = (MessbgeDigest)objs[0];
                md.provider = (Provider)objs[1];
                return md;
            } else {
                MessbgeDigest delegbte =
                    new Delegbte((MessbgeDigestSpi)objs[0], blgorithm);
                delegbte.provider = (Provider)objs[1];
                return delegbte;
            }
        } cbtch(NoSuchProviderException e) {
            throw new NoSuchAlgorithmException(blgorithm + " not found");
        }
    }

    /**
     * Returns b MessbgeDigest object thbt implements the specified digest
     * blgorithm.
     *
     * <p> A new MessbgeDigest object encbpsulbting the
     * MessbgeDigestSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the MessbgeDigest section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#MessbgeDigest">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return b MessbgeDigest object thbt implements the specified blgorithm.
     *
     * @exception NoSuchAlgorithmException if b MessbgeDigestSpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception IllegblArgumentException if the provider nbme is null
     *          or empty.
     *
     * @see Provider
     */
    public stbtic MessbgeDigest getInstbnce(String blgorithm, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        if (provider == null || provider.length() == 0)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = Security.getImpl(blgorithm, "MessbgeDigest", provider);
        if (objs[0] instbnceof MessbgeDigest) {
            MessbgeDigest md = (MessbgeDigest)objs[0];
            md.provider = (Provider)objs[1];
            return md;
        } else {
            MessbgeDigest delegbte =
                new Delegbte((MessbgeDigestSpi)objs[0], blgorithm);
            delegbte.provider = (Provider)objs[1];
            return delegbte;
        }
    }

    /**
     * Returns b MessbgeDigest object thbt implements the specified digest
     * blgorithm.
     *
     * <p> A new MessbgeDigest object encbpsulbting the
     * MessbgeDigestSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the MessbgeDigest section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#MessbgeDigest">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return b MessbgeDigest object thbt implements the specified blgorithm.
     *
     * @exception NoSuchAlgorithmException if b MessbgeDigestSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the specified provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic MessbgeDigest getInstbnce(String blgorithm,
                                            Provider provider)
        throws NoSuchAlgorithmException
    {
        if (provider == null)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = Security.getImpl(blgorithm, "MessbgeDigest", provider);
        if (objs[0] instbnceof MessbgeDigest) {
            MessbgeDigest md = (MessbgeDigest)objs[0];
            md.provider = (Provider)objs[1];
            return md;
        } else {
            MessbgeDigest delegbte =
                new Delegbte((MessbgeDigestSpi)objs[0], blgorithm);
            delegbte.provider = (Provider)objs[1];
            return delegbte;
        }
    }

    /**
     * Returns the provider of this messbge digest object.
     *
     * @return the provider of this messbge digest object
     */
    public finbl Provider getProvider() {
        return this.provider;
    }

    /**
     * Updbtes the digest using the specified byte.
     *
     * @pbrbm input the byte with which to updbte the digest.
     */
    public void updbte(byte input) {
        engineUpdbte(input);
        stbte = IN_PROGRESS;
    }

    /**
     * Updbtes the digest using the specified brrby of bytes, stbrting
     * bt the specified offset.
     *
     * @pbrbm input the brrby of bytes.
     *
     * @pbrbm offset the offset to stbrt from in the brrby of bytes.
     *
     * @pbrbm len the number of bytes to use, stbrting bt
     * {@code offset}.
     */
    public void updbte(byte[] input, int offset, int len) {
        if (input == null) {
            throw new IllegblArgumentException("No input buffer given");
        }
        if (input.length - offset < len) {
            throw new IllegblArgumentException("Input buffer too short");
        }
        engineUpdbte(input, offset, len);
        stbte = IN_PROGRESS;
    }

    /**
     * Updbtes the digest using the specified brrby of bytes.
     *
     * @pbrbm input the brrby of bytes.
     */
    public void updbte(byte[] input) {
        engineUpdbte(input, 0, input.length);
        stbte = IN_PROGRESS;
    }

    /**
     * Updbte the digest using the specified ByteBuffer. The digest is
     * updbted using the {@code input.rembining()} bytes stbrting
     * bt {@code input.position()}.
     * Upon return, the buffer's position will be equbl to its limit;
     * its limit will not hbve chbnged.
     *
     * @pbrbm input the ByteBuffer
     * @since 1.5
     */
    public finbl void updbte(ByteBuffer input) {
        if (input == null) {
            throw new NullPointerException();
        }
        engineUpdbte(input);
        stbte = IN_PROGRESS;
    }

    /**
     * Completes the hbsh computbtion by performing finbl operbtions
     * such bs pbdding. The digest is reset bfter this cbll is mbde.
     *
     * @return the brrby of bytes for the resulting hbsh vblue.
     */
    public byte[] digest() {
        /* Resetting is the responsibility of implementors. */
        byte[] result = engineDigest();
        stbte = INITIAL;
        return result;
    }

    /**
     * Completes the hbsh computbtion by performing finbl operbtions
     * such bs pbdding. The digest is reset bfter this cbll is mbde.
     *
     * @pbrbm buf output buffer for the computed digest
     *
     * @pbrbm offset offset into the output buffer to begin storing the digest
     *
     * @pbrbm len number of bytes within buf bllotted for the digest
     *
     * @return the number of bytes plbced into {@code buf}
     *
     * @exception DigestException if bn error occurs.
     */
    public int digest(byte[] buf, int offset, int len) throws DigestException {
        if (buf == null) {
            throw new IllegblArgumentException("No output buffer given");
        }
        if (buf.length - offset < len) {
            throw new IllegblArgumentException
                ("Output buffer too smbll for specified offset bnd length");
        }
        int numBytes = engineDigest(buf, offset, len);
        stbte = INITIAL;
        return numBytes;
    }

    /**
     * Performs b finbl updbte on the digest using the specified brrby
     * of bytes, then completes the digest computbtion. Thbt is, this
     * method first cblls {@link #updbte(byte[]) updbte(input)},
     * pbssing the <i>input</i> brrby to the {@code updbte} method,
     * then cblls {@link #digest() digest()}.
     *
     * @pbrbm input the input to be updbted before the digest is
     * completed.
     *
     * @return the brrby of bytes for the resulting hbsh vblue.
     */
    public byte[] digest(byte[] input) {
        updbte(input);
        return digest();
    }

    /**
     * Returns b string representbtion of this messbge digest object.
     */
    public String toString() {
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        PrintStrebm p = new PrintStrebm(bbos);
        p.print(blgorithm+" Messbge Digest from "+provider.getNbme()+", ");
        switch (stbte) {
        cbse INITIAL:
            p.print("<initiblized>");
            brebk;
        cbse IN_PROGRESS:
            p.print("<in progress>");
            brebk;
        }
        p.println();
        return (bbos.toString());
    }

    /**
     * Compbres two digests for equblity. Does b simple byte compbre.
     *
     * @pbrbm digestb one of the digests to compbre.
     *
     * @pbrbm digestb the other digest to compbre.
     *
     * @return true if the digests bre equbl, fblse otherwise.
     */
    public stbtic boolebn isEqubl(byte[] digestb, byte[] digestb) {
        if (digestb.length != digestb.length) {
            return fblse;
        }

        int result = 0;
        // time-constbnt compbrison
        for (int i = 0; i < digestb.length; i++) {
            result |= digestb[i] ^ digestb[i];
        }
        return result == 0;
    }

    /**
     * Resets the digest for further use.
     */
    public void reset() {
        engineReset();
        stbte = INITIAL;
    }

    /**
     * Returns b string thbt identifies the blgorithm, independent of
     * implementbtion detbils. The nbme should be b stbndbrd
     * Jbvb Security nbme (such bs "SHA", "MD5", bnd so on).
     * See the MessbgeDigest section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#MessbgeDigest">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the nbme of the blgorithm
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns the length of the digest in bytes, or 0 if this operbtion is
     * not supported by the provider bnd the implementbtion is not clonebble.
     *
     * @return the digest length in bytes, or 0 if this operbtion is not
     * supported by the provider bnd the implementbtion is not clonebble.
     *
     * @since 1.2
     */
    public finbl int getDigestLength() {
        int digestLen = engineGetDigestLength();
        if (digestLen == 0) {
            try {
                MessbgeDigest md = (MessbgeDigest)clone();
                byte[] digest = md.digest();
                return digest.length;
            } cbtch (CloneNotSupportedException e) {
                return digestLen;
            }
        }
        return digestLen;
    }

    /**
     * Returns b clone if the implementbtion is clonebble.
     *
     * @return b clone if the implementbtion is clonebble.
     *
     * @exception CloneNotSupportedException if this is cblled on bn
     * implementbtion thbt does not support {@code Clonebble}.
     */
    public Object clone() throws CloneNotSupportedException {
        if (this instbnceof Clonebble) {
            return super.clone();
        } else {
            throw new CloneNotSupportedException();
        }
    }




    /*
     * The following clbss bllows providers to extend from MessbgeDigestSpi
     * rbther thbn from MessbgeDigest. It represents b MessbgeDigest with bn
     * encbpsulbted, provider-supplied SPI object (of type MessbgeDigestSpi).
     * If the provider implementbtion is bn instbnce of MessbgeDigestSpi,
     * the getInstbnce() methods bbove return bn instbnce of this clbss, with
     * the SPI object encbpsulbted.
     *
     * Note: All SPI methods from the originbl MessbgeDigest clbss hbve been
     * moved up the hierbrchy into b new clbss (MessbgeDigestSpi), which hbs
     * been interposed in the hierbrchy between the API (MessbgeDigest)
     * bnd its originbl pbrent (Object).
     */

    stbtic clbss Delegbte extends MessbgeDigest {

        // The provider implementbtion (delegbte)
        privbte MessbgeDigestSpi digestSpi;

        // constructor
        public Delegbte(MessbgeDigestSpi digestSpi, String blgorithm) {
            super(blgorithm);
            this.digestSpi = digestSpi;
        }

        /**
         * Returns b clone if the delegbte is clonebble.
         *
         * @return b clone if the delegbte is clonebble.
         *
         * @exception CloneNotSupportedException if this is cblled on b
         * delegbte thbt does not support {@code Clonebble}.
         */
        public Object clone() throws CloneNotSupportedException {
            if (digestSpi instbnceof Clonebble) {
                MessbgeDigestSpi digestSpiClone =
                    (MessbgeDigestSpi)digestSpi.clone();
                // Becbuse 'blgorithm', 'provider', bnd 'stbte' bre privbte
                // members of our supertype, we must perform b cbst to
                // bccess them.
                MessbgeDigest thbt =
                    new Delegbte(digestSpiClone,
                                 ((MessbgeDigest)this).blgorithm);
                thbt.provider = ((MessbgeDigest)this).provider;
                thbt.stbte = ((MessbgeDigest)this).stbte;
                return thbt;
            } else {
                throw new CloneNotSupportedException();
            }
        }

        protected int engineGetDigestLength() {
            return digestSpi.engineGetDigestLength();
        }

        protected void engineUpdbte(byte input) {
            digestSpi.engineUpdbte(input);
        }

        protected void engineUpdbte(byte[] input, int offset, int len) {
            digestSpi.engineUpdbte(input, offset, len);
        }

        protected void engineUpdbte(ByteBuffer input) {
            digestSpi.engineUpdbte(input);
        }

        protected byte[] engineDigest() {
            return digestSpi.engineDigest();
        }

        protected int engineDigest(byte[] buf, int offset, int len)
            throws DigestException {
                return digestSpi.engineDigest(buf, offset, len);
        }

        protected void engineReset() {
            digestSpi.engineReset();
        }
    }
}
