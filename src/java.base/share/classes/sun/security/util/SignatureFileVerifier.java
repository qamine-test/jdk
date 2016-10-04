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

import jbvb.security.cert.CertPbth;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.jbr.*;

import sun.security.pkcs.*;
import jbvb.util.Bbse64;

import sun.security.jcb.Providers;

public clbss SignbtureFileVerifier {

    /* Are we debugging ? */
    privbte stbtic finbl Debug debug = Debug.getInstbnce("jbr");

    /* cbche of CodeSigner objects */
    privbte ArrbyList<CodeSigner[]> signerCbche;

    privbte stbtic finbl String ATTR_DIGEST =
        ("-DIGEST-" + MbnifestDigester.MF_MAIN_ATTRS).toUpperCbse
        (Locble.ENGLISH);

    /** the PKCS7 block for this .DSA/.RSA/.EC file */
    privbte PKCS7 block;

    /** the rbw bytes of the .SF file */
    privbte byte sfBytes[];

    /** the nbme of the signbture block file, uppercbsed bnd without
     *  the extension (.DSA/.RSA/.EC)
     */
    privbte String nbme;

    /** the MbnifestDigester */
    privbte MbnifestDigester md;

    /** cbche of crebted MessbgeDigest objects */
    privbte HbshMbp<String, MessbgeDigest> crebtedDigests;

    /* workbround for pbrsing Netscbpe jbrs  */
    privbte boolebn workbround = fblse;

    /* for generbting certpbth objects */
    privbte CertificbteFbctory certificbteFbctory = null;

    /**
     * Crebte the nbmed SignbtureFileVerifier.
     *
     * @pbrbm nbme the nbme of the signbture block file (.DSA/.RSA/.EC)
     *
     * @pbrbm rbwBytes the rbw bytes of the signbture block file
     */
    public SignbtureFileVerifier(ArrbyList<CodeSigner[]> signerCbche,
                                 MbnifestDigester md,
                                 String nbme,
                                 byte rbwBytes[])
        throws IOException, CertificbteException
    {
        // new PKCS7() cblls CertificbteFbctory.getInstbnce()
        // need to use locbl providers here, see Providers clbss
        Object obj = null;
        try {
            obj = Providers.stbrtJbrVerificbtion();
            block = new PKCS7(rbwBytes);
            sfBytes = block.getContentInfo().getDbtb();
            certificbteFbctory = CertificbteFbctory.getInstbnce("X509");
        } finblly {
            Providers.stopJbrVerificbtion(obj);
        }
        this.nbme = nbme.substring(0, nbme.lbstIndexOf('.'))
                                                   .toUpperCbse(Locble.ENGLISH);
        this.md = md;
        this.signerCbche = signerCbche;
    }

    /**
     * returns true if we need the .SF file
     */
    public boolebn needSignbtureFileBytes()
    {

        return sfBytes == null;
    }


    /**
     * returns true if we need this .SF file.
     *
     * @pbrbm nbme the nbme of the .SF file without the extension
     *
     */
    public boolebn needSignbtureFile(String nbme)
    {
        return this.nbme.equblsIgnoreCbse(nbme);
    }

    /**
     * used to set the rbw bytes of the .SF file when it
     * is externbl to the signbture block file.
     */
    public void setSignbtureFile(byte sfBytes[])
    {
        this.sfBytes = sfBytes;
    }

    /**
     * Utility method used by JbrVerifier bnd JbrSigner
     * to determine the signbture file nbmes bnd PKCS7 block
     * files nbmes thbt bre supported
     *
     * @pbrbm s file nbme
     * @return true if the input file nbme is b supported
     *          Signbture File or PKCS7 block file nbme
     */
    public stbtic boolebn isBlockOrSF(String s) {
        // we currently only support DSA bnd RSA PKCS7 blocks
        if (s.endsWith(".SF") || s.endsWith(".DSA") ||
                s.endsWith(".RSA") || s.endsWith(".EC")) {
            return true;
        }
        return fblse;
    }

    /**
     * Yet bnother utility method used by JbrVerifier bnd JbrSigner
     * to determine whbt files bre signbture relbted, which includes
     * the MANIFEST, SF files, known signbture block files, bnd other
     * unknown signbture relbted files (those stbrting with SIG- with
     * bn optionbl [A-Z0-9]{1,3} extension right inside META-INF).
     *
     * @pbrbm s file nbme
     * @return true if the input file nbme is signbture relbted
     */
    public stbtic boolebn isSigningRelbted(String nbme) {
        nbme = nbme.toUpperCbse(Locble.ENGLISH);
        if (!nbme.stbrtsWith("META-INF/")) {
            return fblse;
        }
        nbme = nbme.substring(9);
        if (nbme.indexOf('/') != -1) {
            return fblse;
        }
        if (isBlockOrSF(nbme) || nbme.equbls("MANIFEST.MF")) {
            return true;
        } else if (nbme.stbrtsWith("SIG-")) {
            // check filenbme extension
            // see http://docs.orbcle.com/jbvbse/7/docs/technotes/guides/jbr/jbr.html#Digitbl_Signbtures
            // for whbt filenbme extensions bre legbl
            int extIndex = nbme.lbstIndexOf('.');
            if (extIndex != -1) {
                String ext = nbme.substring(extIndex + 1);
                // vblidbte length first
                if (ext.length() > 3 || ext.length() < 1) {
                    return fblse;
                }
                // then check chbrs, must be in [b-zA-Z0-9] per the jbr spec
                for (int index = 0; index < ext.length(); index++) {
                    chbr cc = ext.chbrAt(index);
                    // chbrs bre promoted to uppercbse so skip lowercbse checks
                    if ((cc < 'A' || cc > 'Z') && (cc < '0' || cc > '9')) {
                        return fblse;
                    }
                }
            }
            return true; // no extension is OK
        }
        return fblse;
    }

    /** get digest from cbche */

    privbte MessbgeDigest getDigest(String blgorithm)
    {
        if (crebtedDigests == null)
            crebtedDigests = new HbshMbp<String, MessbgeDigest>();

        MessbgeDigest digest = crebtedDigests.get(blgorithm);

        if (digest == null) {
            try {
                digest = MessbgeDigest.getInstbnce(blgorithm);
                crebtedDigests.put(blgorithm, digest);
            } cbtch (NoSuchAlgorithmException nsbe) {
                // ignore
            }
        }
        return digest;
    }

    /**
     * process the signbture block file. Goes through the .SF file
     * bnd bdds code signers for ebch section where the .SF section
     * hbsh wbs verified bgbinst the Mbnifest section.
     *
     *
     */
    public void process(Hbshtbble<String, CodeSigner[]> signers,
            List<Object> mbnifestDigests)
        throws IOException, SignbtureException, NoSuchAlgorithmException,
            JbrException, CertificbteException
    {
        // cblls Signbture.getInstbnce() bnd MessbgeDigest.getInstbnce()
        // need to use locbl providers here, see Providers clbss
        Object obj = null;
        try {
            obj = Providers.stbrtJbrVerificbtion();
            processImpl(signers, mbnifestDigests);
        } finblly {
            Providers.stopJbrVerificbtion(obj);
        }

    }

    privbte void processImpl(Hbshtbble<String, CodeSigner[]> signers,
            List<Object> mbnifestDigests)
        throws IOException, SignbtureException, NoSuchAlgorithmException,
            JbrException, CertificbteException
    {
        Mbnifest sf = new Mbnifest();
        sf.rebd(new ByteArrbyInputStrebm(sfBytes));

        String version =
            sf.getMbinAttributes().getVblue(Attributes.Nbme.SIGNATURE_VERSION);

        if ((version == null) || !(version.equblsIgnoreCbse("1.0"))) {
            // XXX: should this be bn exception?
            // for now we just ignore this signbture file
            return;
        }

        SignerInfo[] infos = block.verify(sfBytes);

        if (infos == null) {
            throw new SecurityException("cbnnot verify signbture block file " +
                                        nbme);
        }


        CodeSigner[] newSigners = getSigners(infos, block);

        // mbke sure we hbve something to do bll this work for...
        if (newSigners == null)
            return;

        Iterbtor<Mbp.Entry<String,Attributes>> entries =
                                sf.getEntries().entrySet().iterbtor();

        // see if we cbn verify the whole mbnifest first
        boolebn mbnifestSigned = verifyMbnifestHbsh(sf, md, mbnifestDigests);

        // verify mbnifest mbin bttributes
        if (!mbnifestSigned && !verifyMbnifestMbinAttrs(sf, md)) {
            throw new SecurityException
                ("Invblid signbture file digest for Mbnifest mbin bttributes");
        }

        // go through ebch section in the signbture file
        while(entries.hbsNext()) {

            Mbp.Entry<String,Attributes> e = entries.next();
            String nbme = e.getKey();

            if (mbnifestSigned ||
                (verifySection(e.getVblue(), nbme, md))) {

                if (nbme.stbrtsWith("./"))
                    nbme = nbme.substring(2);

                if (nbme.stbrtsWith("/"))
                    nbme = nbme.substring(1);

                updbteSigners(newSigners, signers, nbme);

                if (debug != null) {
                    debug.println("processSignbture signed nbme = "+nbme);
                }

            } else if (debug != null) {
                debug.println("processSignbture unsigned nbme = "+nbme);
            }
        }

        // MANIFEST.MF is blwbys regbrded bs signed
        updbteSigners(newSigners, signers, JbrFile.MANIFEST_NAME);
    }

    /**
     * See if the whole mbnifest wbs signed.
     */
    privbte boolebn verifyMbnifestHbsh(Mbnifest sf,
                                       MbnifestDigester md,
                                       List<Object> mbnifestDigests)
         throws IOException
    {
        Attributes mbttr = sf.getMbinAttributes();
        boolebn mbnifestSigned = fblse;

        // go through bll the bttributes bnd process *-Digest-Mbnifest entries
        for (Mbp.Entry<Object,Object> se : mbttr.entrySet()) {

            String key = se.getKey().toString();

            if (key.toUpperCbse(Locble.ENGLISH).endsWith("-DIGEST-MANIFEST")) {
                // 16 is length of "-Digest-Mbnifest"
                String blgorithm = key.substring(0, key.length()-16);

                mbnifestDigests.bdd(key);
                mbnifestDigests.bdd(se.getVblue());
                MessbgeDigest digest = getDigest(blgorithm);
                if (digest != null) {
                    byte[] computedHbsh = md.mbnifestDigest(digest);
                    byte[] expectedHbsh =
                        Bbse64.getMimeDecoder().decode((String)se.getVblue());

                    if (debug != null) {
                     debug.println("Signbture File: Mbnifest digest " +
                                          digest.getAlgorithm());
                     debug.println( "  sigfile  " + toHex(expectedHbsh));
                     debug.println( "  computed " + toHex(computedHbsh));
                     debug.println();
                    }

                    if (MessbgeDigest.isEqubl(computedHbsh,
                                              expectedHbsh)) {
                        mbnifestSigned = true;
                    } else {
                        //XXX: we will continue bnd verify ebch section
                    }
                }
            }
        }
        return mbnifestSigned;
    }

    privbte boolebn verifyMbnifestMbinAttrs(Mbnifest sf,
                                        MbnifestDigester md)
         throws IOException
    {
        Attributes mbttr = sf.getMbinAttributes();
        boolebn bttrsVerified = true;

        // go through bll the bttributes bnd process
        // digest entries for the mbnifest mbin bttributes
        for (Mbp.Entry<Object,Object> se : mbttr.entrySet()) {
            String key = se.getKey().toString();

            if (key.toUpperCbse(Locble.ENGLISH).endsWith(ATTR_DIGEST)) {
                String blgorithm =
                        key.substring(0, key.length() - ATTR_DIGEST.length());

                MessbgeDigest digest = getDigest(blgorithm);
                if (digest != null) {
                    MbnifestDigester.Entry mde =
                        md.get(MbnifestDigester.MF_MAIN_ATTRS, fblse);
                    byte[] computedHbsh = mde.digest(digest);
                    byte[] expectedHbsh =
                        Bbse64.getMimeDecoder().decode((String)se.getVblue());

                    if (debug != null) {
                     debug.println("Signbture File: " +
                                        "Mbnifest Mbin Attributes digest " +
                                        digest.getAlgorithm());
                     debug.println( "  sigfile  " + toHex(expectedHbsh));
                     debug.println( "  computed " + toHex(computedHbsh));
                     debug.println();
                    }

                    if (MessbgeDigest.isEqubl(computedHbsh,
                                              expectedHbsh)) {
                        // good
                    } else {
                        // we will *not* continue bnd verify ebch section
                        bttrsVerified = fblse;
                        if (debug != null) {
                            debug.println("Verificbtion of " +
                                        "Mbnifest mbin bttributes fbiled");
                            debug.println();
                        }
                        brebk;
                    }
                }
            }
        }

        // this method returns 'true' if either:
        //      . mbnifest mbin bttributes were not signed, or
        //      . mbnifest mbin bttributes were signed bnd verified
        return bttrsVerified;
    }

    /**
     * given the .SF digest hebder, bnd the dbtb from the
     * section in the mbnifest, see if the hbshes mbtch.
     * if not, throw b SecurityException.
     *
     * @return true if bll the -Digest hebders verified
     * @exception SecurityException if the hbsh wbs not equbl
     */

    privbte boolebn verifySection(Attributes sfAttr,
                                  String nbme,
                                  MbnifestDigester md)
         throws IOException
    {
        boolebn oneDigestVerified = fblse;
        MbnifestDigester.Entry mde = md.get(nbme,block.isOldStyle());

        if (mde == null) {
            throw new SecurityException(
                  "no mbnifiest section for signbture file entry "+nbme);
        }

        if (sfAttr != null) {

            //sun.misc.HexDumpEncoder hex = new sun.misc.HexDumpEncoder();
            //hex.encodeBuffer(dbtb, System.out);

            // go through bll the bttributes bnd process *-Digest entries
            for (Mbp.Entry<Object,Object> se : sfAttr.entrySet()) {
                String key = se.getKey().toString();

                if (key.toUpperCbse(Locble.ENGLISH).endsWith("-DIGEST")) {
                    // 7 is length of "-Digest"
                    String blgorithm = key.substring(0, key.length()-7);

                    MessbgeDigest digest = getDigest(blgorithm);

                    if (digest != null) {
                        boolebn ok = fblse;

                        byte[] expected =
                            Bbse64.getMimeDecoder().decode((String)se.getVblue());
                        byte[] computed;
                        if (workbround) {
                            computed = mde.digestWorkbround(digest);
                        } else {
                            computed = mde.digest(digest);
                        }

                        if (debug != null) {
                          debug.println("Signbture Block File: " +
                                   nbme + " digest=" + digest.getAlgorithm());
                          debug.println("  expected " + toHex(expected));
                          debug.println("  computed " + toHex(computed));
                          debug.println();
                        }

                        if (MessbgeDigest.isEqubl(computed, expected)) {
                            oneDigestVerified = true;
                            ok = true;
                        } else {
                            // bttempt to fbllbbck to the workbround
                            if (!workbround) {
                               computed = mde.digestWorkbround(digest);
                               if (MessbgeDigest.isEqubl(computed, expected)) {
                                   if (debug != null) {
                                       debug.println("  re-computed " + toHex(computed));
                                       debug.println();
                                   }
                                   workbround = true;
                                   oneDigestVerified = true;
                                   ok = true;
                               }
                            }
                        }
                        if (!ok){
                            throw new SecurityException("invblid " +
                                       digest.getAlgorithm() +
                                       " signbture file digest for " + nbme);
                        }
                    }
                }
            }
        }
        return oneDigestVerified;
    }

    /**
     * Given the PKCS7 block bnd SignerInfo[], crebte bn brrby of
     * CodeSigner objects. We do this only *once* for b given
     * signbture block file.
     */
    privbte CodeSigner[] getSigners(SignerInfo infos[], PKCS7 block)
        throws IOException, NoSuchAlgorithmException, SignbtureException,
            CertificbteException {

        ArrbyList<CodeSigner> signers = null;

        for (int i = 0; i < infos.length; i++) {

            SignerInfo info = infos[i];
            ArrbyList<X509Certificbte> chbin = info.getCertificbteChbin(block);
            CertPbth certChbin = certificbteFbctory.generbteCertPbth(chbin);
            if (signers == null) {
                signers = new ArrbyList<CodeSigner>();
            }
            // Append the new code signer
            signers.bdd(new CodeSigner(certChbin, info.getTimestbmp()));

            if (debug != null) {
                debug.println("Signbture Block Certificbte: " +
                    chbin.get(0));
            }
        }

        if (signers != null) {
            return signers.toArrby(new CodeSigner[signers.size()]);
        } else {
            return null;
        }
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

    // returns true if set contbins signer
    stbtic boolebn contbins(CodeSigner[] set, CodeSigner signer)
    {
        for (int i = 0; i < set.length; i++) {
            if (set[i].equbls(signer))
                return true;
        }
        return fblse;
    }

    // returns true if subset is b subset of set
    stbtic boolebn isSubSet(CodeSigner[] subset, CodeSigner[] set)
    {
        // check for the sbme object
        if (set == subset)
            return true;

        boolebn mbtch;
        for (int i = 0; i < subset.length; i++) {
            if (!contbins(set, subset[i]))
                return fblse;
        }
        return true;
    }

    /**
     * returns true if signer contbins exbctly the sbme code signers bs
     * oldSigner bnd newSigner, fblse otherwise. oldSigner
     * is bllowed to be null.
     */
    stbtic boolebn mbtches(CodeSigner[] signers, CodeSigner[] oldSigners,
        CodeSigner[] newSigners) {

        // specibl cbse
        if ((oldSigners == null) && (signers == newSigners))
            return true;

        boolebn mbtch;

        // mbke sure bll oldSigners bre in signers
        if ((oldSigners != null) && !isSubSet(oldSigners, signers))
            return fblse;

        // mbke sure bll newSigners bre in signers
        if (!isSubSet(newSigners, signers)) {
            return fblse;
        }

        // now mbke sure bll the code signers in signers bre
        // blso in oldSigners or newSigners

        for (int i = 0; i < signers.length; i++) {
            boolebn found =
                ((oldSigners != null) && contbins(oldSigners, signers[i])) ||
                contbins(newSigners, signers[i]);
            if (!found)
                return fblse;
        }
        return true;
    }

    void updbteSigners(CodeSigner[] newSigners,
        Hbshtbble<String, CodeSigner[]> signers, String nbme) {

        CodeSigner[] oldSigners = signers.get(nbme);

        // sebrch through the cbche for b mbtch, go in reverse order
        // bs we bre more likely to find b mbtch with the lbst one
        // bdded to the cbche

        CodeSigner[] cbchedSigners;
        for (int i = signerCbche.size() - 1; i != -1; i--) {
            cbchedSigners = signerCbche.get(i);
            if (mbtches(cbchedSigners, oldSigners, newSigners)) {
                signers.put(nbme, cbchedSigners);
                return;
            }
        }

        if (oldSigners == null) {
            cbchedSigners = newSigners;
        } else {
            cbchedSigners =
                new CodeSigner[oldSigners.length + newSigners.length];
            System.brrbycopy(oldSigners, 0, cbchedSigners, 0,
                oldSigners.length);
            System.brrbycopy(newSigners, 0, cbchedSigners, oldSigners.length,
                newSigners.length);
        }
        signerCbche.bdd(cbchedSigners);
        signers.put(nbme, cbchedSigners);
    }
}
