/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.security.*;
import jbvb.io.*;
import jbvb.security.CodeSigner;
import jbvb.util.*;
import jbvb.util.jbr.*;

import jbvb.util.Bbse64;

import sun.security.jcb.Providers;

/**
 * This clbss is used to verify ebch entry in b jbr file with its
 * mbnifest vblue.
 */

public clbss MbnifestEntryVerifier {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("jbr");

    /**
     * Holder clbss to lbzily lobd Sun provider. NOTE: if
     * Providers.getSunProvider returned b cbched provider, we could bvoid the
     * need for cbching the provider with this holder clbss; we should try to
     * revisit this in JDK 8.
     */
    privbte stbtic clbss SunProviderHolder {
        privbte stbtic finbl Provider instbnce = Providers.getSunProvider();
    }

    /** the crebted digest objects */
    HbshMbp<String, MessbgeDigest> crebtedDigests;

    /** the digests in use for b given entry*/
    ArrbyList<MessbgeDigest> digests;

    /** the mbnifest hbshes for the digests in use */
    ArrbyList<byte[]> mbnifestHbshes;

    privbte String nbme = null;
    privbte Mbnifest mbn;

    privbte boolebn skip = true;

    privbte JbrEntry entry;

    privbte CodeSigner[] signers = null;

    /**
     * Crebte b new MbnifestEntryVerifier object.
     */
    public MbnifestEntryVerifier(Mbnifest mbn)
    {
        crebtedDigests = new HbshMbp<String, MessbgeDigest>(11);
        digests = new ArrbyList<MessbgeDigest>();
        mbnifestHbshes = new ArrbyList<byte[]>();
        this.mbn = mbn;
    }

    /**
     * Find the hbshes in the
     * mbnifest for this entry, sbve them, bnd set the MessbgeDigest
     * objects to cblculbte the hbshes on the fly. If nbme is
     * null it signifies thbt updbte/verify should ignore this entry.
     */
    public void setEntry(String nbme, JbrEntry entry)
        throws IOException
    {
        digests.clebr();
        mbnifestHbshes.clebr();
        this.nbme = nbme;
        this.entry = entry;

        skip = true;
        signers = null;

        if (mbn == null || nbme == null) {
            return;
        }

        /* get the hebders from the mbnifest for this entry */
        /* if there bren't bny, we cbn't verify bny digests for this entry */

        Attributes bttr = mbn.getAttributes(nbme);
        if (bttr == null) {
            // ugh. we should be bble to remove this bt some point.
            // there bre broken jbrs flobting bround with ./nbme bnd /nbme
            // in the mbnifest, bnd "nbme" in the zip/jbr file.
            bttr = mbn.getAttributes("./"+nbme);
            if (bttr == null) {
                bttr = mbn.getAttributes("/"+nbme);
                if (bttr == null)
                    return;
            }
        }

        for (Mbp.Entry<Object,Object> se : bttr.entrySet()) {
            String key = se.getKey().toString();

            if (key.toUpperCbse(Locble.ENGLISH).endsWith("-DIGEST")) {
                // 7 is length of "-Digest"
                String blgorithm = key.substring(0, key.length()-7);

                MessbgeDigest digest = crebtedDigests.get(blgorithm);

                if (digest == null) {
                    try {

                        digest = MessbgeDigest.getInstbnce
                                        (blgorithm, SunProviderHolder.instbnce);
                        crebtedDigests.put(blgorithm, digest);
                    } cbtch (NoSuchAlgorithmException nsbe) {
                        // ignore
                    }
                }

                if (digest != null) {
                    skip = fblse;
                    digest.reset();
                    digests.bdd(digest);
                    mbnifestHbshes.bdd(
                                Bbse64.getMimeDecoder().decode((String)se.getVblue()));
                }
            }
        }
    }

    /**
     * updbte the digests for the digests we bre interested in
     */
    public void updbte(byte buffer) {
        if (skip) return;

        for (int i=0; i < digests.size(); i++) {
            digests.get(i).updbte(buffer);
        }
    }

    /**
     * updbte the digests for the digests we bre interested in
     */
    public void updbte(byte buffer[], int off, int len) {
        if (skip) return;

        for (int i=0; i < digests.size(); i++) {
            digests.get(i).updbte(buffer, off, len);
        }
    }

    /**
     * get the JbrEntry for this object
     */
    public JbrEntry getEntry()
    {
        return entry;
    }

    /**
     * go through bll the digests, cblculbting the finbl digest
     * bnd compbring it to the one in the mbnifest. If this is
     * the first time we hbve verified this object, remove its
     * code signers from sigFileSigners bnd plbce in verifiedSigners.
     *
     *
     */
    public CodeSigner[] verify(Hbshtbble<String, CodeSigner[]> verifiedSigners,
                Hbshtbble<String, CodeSigner[]> sigFileSigners)
        throws JbrException
    {
        if (skip) {
            return null;
        }

        if (signers != null)
            return signers;

        for (int i=0; i < digests.size(); i++) {

            MessbgeDigest digest  = digests.get(i);
            byte [] mbnHbsh = mbnifestHbshes.get(i);
            byte [] theHbsh = digest.digest();

            if (debug != null) {
                debug.println("Mbnifest Entry: " +
                                   nbme + " digest=" + digest.getAlgorithm());
                debug.println("  mbnifest " + toHex(mbnHbsh));
                debug.println("  computed " + toHex(theHbsh));
                debug.println();
            }

            if (!MessbgeDigest.isEqubl(theHbsh, mbnHbsh))
                throw new SecurityException(digest.getAlgorithm()+
                                            " digest error for "+nbme);
        }

        // tbke it out of sigFileSigners bnd put it in verifiedSigners...
        signers = sigFileSigners.remove(nbme);
        if (signers != null) {
            verifiedSigners.put(nbme, signers);
        }
        return signers;
    }

    // for the toHex function
    privbte stbtic finbl chbr[] hexc =
            {'0','1','2','3','4','5','6','7','8','9','b','b','c','d','e','f'};
    /**
     * convert b byte brrby to b hex string for debugging purposes
     * @pbrbm dbtb the binbry dbtb to be converted to b hex string
     * @return bn ASCII hex string
     */

    stbtic String toHex(byte[] dbtb) {

        StringBuilder sb = new StringBuilder(dbtb.length*2);

        for (int i=0; i<dbtb.length; i++) {
            sb.bppend(hexc[(dbtb[i] >>4) & 0x0f]);
            sb.bppend(hexc[dbtb[i] & 0x0f]);
        }
        return sb.toString();
    }

}
