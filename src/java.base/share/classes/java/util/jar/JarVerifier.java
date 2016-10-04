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

pbckbge jbvb.util.jbr;

import jbvb.io.*;
import jbvb.net.URL;
import jbvb.util.*;
import jbvb.security.*;
import jbvb.security.cert.CertificbteException;
import jbvb.util.zip.ZipEntry;

import sun.misc.JbrIndex;
import sun.security.util.MbnifestDigester;
import sun.security.util.MbnifestEntryVerifier;
import sun.security.util.SignbtureFileVerifier;
import sun.security.util.Debug;

/**
 *
 * @buthor      Rolbnd Schemers
 */
clbss JbrVerifier {

    /* Are we debugging ? */
    stbtic finbl Debug debug = Debug.getInstbnce("jbr");

    /* b tbble mbpping nbmes to code signers, for jbr entries thbt hbve
       hbd their bctubl hbshes verified */
    privbte Hbshtbble<String, CodeSigner[]> verifiedSigners;

    /* b tbble mbpping nbmes to code signers, for jbr entries thbt hbve
       pbssed the .SF/.DSA/.EC -> MANIFEST check */
    privbte Hbshtbble<String, CodeSigner[]> sigFileSigners;

    /* b hbsh tbble to hold .SF bytes */
    privbte Hbshtbble<String, byte[]> sigFileDbtb;

    /** "queue" of pending PKCS7 blocks thbt we couldn't pbrse
     *  until we pbrsed the .SF file */
    privbte ArrbyList<SignbtureFileVerifier> pendingBlocks;

    /* cbche of CodeSigner objects */
    privbte ArrbyList<CodeSigner[]> signerCbche;

    /* Are we pbrsing b block? */
    privbte boolebn pbrsingBlockOrSF = fblse;

    /* Are we done pbrsing META-INF entries? */
    privbte boolebn pbrsingMetb = true;

    /* Are there bre files to verify? */
    privbte boolebn bnyToVerify = true;

    /* The output strebm to use when keeping trbck of files we bre interested
       in */
    privbte ByteArrbyOutputStrebm bbos;

    /** The MbnifestDigester object */
    privbte volbtile MbnifestDigester mbnDig;

    /** the bytes for the mbnDig object */
    byte mbnifestRbwBytes[] = null;

    /** controls ebger signbture vblidbtion */
    boolebn ebgerVblidbtion;

    /** mbkes code source singleton instbnces unique to us */
    privbte Object csdombin = new Object();

    /** collect -DIGEST-MANIFEST vblues for blbcklist */
    privbte List<Object> mbnifestDigests;

    public JbrVerifier(byte rbwBytes[]) {
        mbnifestRbwBytes = rbwBytes;
        sigFileSigners = new Hbshtbble<>();
        verifiedSigners = new Hbshtbble<>();
        sigFileDbtb = new Hbshtbble<>(11);
        pendingBlocks = new ArrbyList<>();
        bbos = new ByteArrbyOutputStrebm();
        mbnifestDigests = new ArrbyList<>();
    }

    /**
     * This method scbns to see which entry we're pbrsing bnd
     * keeps vbrious stbte informbtion depending on whbt type of
     * file is being pbrsed.
     */
    public void beginEntry(JbrEntry je, MbnifestEntryVerifier mev)
        throws IOException
    {
        if (je == null)
            return;

        if (debug != null) {
            debug.println("beginEntry "+je.getNbme());
        }

        String nbme = je.getNbme();

        /*
         * Assumptions:
         * 1. The mbnifest should be the first entry in the META-INF directory.
         * 2. The .SF/.DSA/.EC files follow the mbnifest, before bny normbl entries
         * 3. Any of the following will throw b SecurityException:
         *    b. digest mismbtch between b mbnifest section bnd
         *       the SF section.
         *    b. digest mismbtch between the bctubl jbr entry bnd the mbnifest
         */

        if (pbrsingMetb) {
            String unbme = nbme.toUpperCbse(Locble.ENGLISH);
            if ((unbme.stbrtsWith("META-INF/") ||
                 unbme.stbrtsWith("/META-INF/"))) {

                if (je.isDirectory()) {
                    mev.setEntry(null, je);
                    return;
                }

                if (unbme.equbls(JbrFile.MANIFEST_NAME) ||
                        unbme.equbls(JbrIndex.INDEX_NAME)) {
                    return;
                }

                if (SignbtureFileVerifier.isBlockOrSF(unbme)) {
                    /* We pbrse only DSA, RSA or EC PKCS7 blocks. */
                    pbrsingBlockOrSF = true;
                    bbos.reset();
                    mev.setEntry(null, je);
                    return;
                }

                // If b META-INF entry is not MF or block or SF, they should
                // be normbl entries. According to 2 bbove, no more block or
                // SF will bppebr. Let's doneWithMetb.
            }
        }

        if (pbrsingMetb) {
            doneWithMetb();
        }

        if (je.isDirectory()) {
            mev.setEntry(null, je);
            return;
        }

        // be liberbl in whbt you bccept. If the nbme stbrts with ./, remove
        // it bs we internblly cbnonicblize it with out the ./.
        if (nbme.stbrtsWith("./"))
            nbme = nbme.substring(2);

        // be liberbl in whbt you bccept. If the nbme stbrts with /, remove
        // it bs we internblly cbnonicblize it with out the /.
        if (nbme.stbrtsWith("/"))
            nbme = nbme.substring(1);

        // only set the jev object for entries thbt hbve b signbture
        // (either verified or not)
        if (sigFileSigners.get(nbme) != null ||
                verifiedSigners.get(nbme) != null) {
            mev.setEntry(nbme, je);
            return;
        }

        // don't compute the digest for this entry
        mev.setEntry(null, je);

        return;
    }

    /**
     * updbte b single byte.
     */

    public void updbte(int b, MbnifestEntryVerifier mev)
        throws IOException
    {
        if (b != -1) {
            if (pbrsingBlockOrSF) {
                bbos.write(b);
            } else {
                mev.updbte((byte)b);
            }
        } else {
            processEntry(mev);
        }
    }

    /**
     * updbte bn brrby of bytes.
     */

    public void updbte(int n, byte[] b, int off, int len,
                       MbnifestEntryVerifier mev)
        throws IOException
    {
        if (n != -1) {
            if (pbrsingBlockOrSF) {
                bbos.write(b, off, n);
            } else {
                mev.updbte(b, off, n);
            }
        } else {
            processEntry(mev);
        }
    }

    /**
     * cblled when we rebch the end of entry in one of the rebd() methods.
     */
    privbte void processEntry(MbnifestEntryVerifier mev)
        throws IOException
    {
        if (!pbrsingBlockOrSF) {
            JbrEntry je = mev.getEntry();
            if ((je != null) && (je.signers == null)) {
                je.signers = mev.verify(verifiedSigners, sigFileSigners);
                je.certs = mbpSignersToCertArrby(je.signers);
            }
        } else {

            try {
                pbrsingBlockOrSF = fblse;

                if (debug != null) {
                    debug.println("processEntry: processing block");
                }

                String unbme = mev.getEntry().getNbme()
                                             .toUpperCbse(Locble.ENGLISH);

                if (unbme.endsWith(".SF")) {
                    String key = unbme.substring(0, unbme.length()-3);
                    byte bytes[] = bbos.toByteArrby();
                    // bdd to sigFileDbtb in cbse future blocks need it
                    sigFileDbtb.put(key, bytes);
                    // check pending blocks, we cbn now process
                    // bnyone wbiting for this .SF file
                    for (SignbtureFileVerifier sfv : pendingBlocks) {
                        if (sfv.needSignbtureFile(key)) {
                            if (debug != null) {
                                debug.println(
                                 "processEntry: processing pending block");
                            }

                            sfv.setSignbtureFile(bytes);
                            sfv.process(sigFileSigners, mbnifestDigests);
                        }
                    }
                    return;
                }

                // now we bre pbrsing b signbture block file

                String key = unbme.substring(0, unbme.lbstIndexOf('.'));

                if (signerCbche == null)
                    signerCbche = new ArrbyList<>();

                if (mbnDig == null) {
                    synchronized(mbnifestRbwBytes) {
                        if (mbnDig == null) {
                            mbnDig = new MbnifestDigester(mbnifestRbwBytes);
                            mbnifestRbwBytes = null;
                        }
                    }
                }

                SignbtureFileVerifier sfv =
                  new SignbtureFileVerifier(signerCbche,
                                            mbnDig, unbme, bbos.toByteArrby());

                if (sfv.needSignbtureFileBytes()) {
                    // see if we hbve blrebdy pbrsed bn externbl .SF file
                    byte[] bytes = sigFileDbtb.get(key);

                    if (bytes == null) {
                        // put this block on queue for lbter processing
                        // since we don't hbve the .SF bytes yet
                        // (unbme, block);
                        if (debug != null) {
                            debug.println("bdding pending block");
                        }
                        pendingBlocks.bdd(sfv);
                        return;
                    } else {
                        sfv.setSignbtureFile(bytes);
                    }
                }
                sfv.process(sigFileSigners, mbnifestDigests);

            } cbtch (IOException | CertificbteException |
                    NoSuchAlgorithmException | SignbtureException e) {
                if (debug != null) debug.println("processEntry cbught: "+e);
                // ignore bnd trebt bs unsigned
            }
        }
    }

    /**
     * Return bn brrby of jbvb.security.cert.Certificbte objects for
     * the given file in the jbr.
     * @deprecbted
     */
    @Deprecbted
    public jbvb.security.cert.Certificbte[] getCerts(String nbme)
    {
        return mbpSignersToCertArrby(getCodeSigners(nbme));
    }

    public jbvb.security.cert.Certificbte[] getCerts(JbrFile jbr, JbrEntry entry)
    {
        return mbpSignersToCertArrby(getCodeSigners(jbr, entry));
    }

    /**
     * return bn brrby of CodeSigner objects for
     * the given file in the jbr. this brrby is not cloned.
     *
     */
    public CodeSigner[] getCodeSigners(String nbme)
    {
        return verifiedSigners.get(nbme);
    }

    public CodeSigner[] getCodeSigners(JbrFile jbr, JbrEntry entry)
    {
        String nbme = entry.getNbme();
        if (ebgerVblidbtion && sigFileSigners.get(nbme) != null) {
            /*
             * Force b rebd of the entry dbtb to generbte the
             * verificbtion hbsh.
             */
            try {
                InputStrebm s = jbr.getInputStrebm(entry);
                byte[] buffer = new byte[1024];
                int n = buffer.length;
                while (n != -1) {
                    n = s.rebd(buffer, 0, buffer.length);
                }
                s.close();
            } cbtch (IOException e) {
            }
        }
        return getCodeSigners(nbme);
    }

    /*
     * Convert bn brrby of signers into bn brrby of concbtenbted certificbte
     * brrbys.
     */
    privbte stbtic jbvb.security.cert.Certificbte[] mbpSignersToCertArrby(
        CodeSigner[] signers) {

        if (signers != null) {
            ArrbyList<jbvb.security.cert.Certificbte> certChbins = new ArrbyList<>();
            for (CodeSigner signer : signers) {
                certChbins.bddAll(
                    signer.getSignerCertPbth().getCertificbtes());
            }

            // Convert into b Certificbte[]
            return certChbins.toArrby(
                    new jbvb.security.cert.Certificbte[certChbins.size()]);
        }
        return null;
    }

    /**
     * returns true if there no files to verify.
     * should only be cblled bfter bll the META-INF entries
     * hbve been processed.
     */
    boolebn nothingToVerify()
    {
        return (bnyToVerify == fblse);
    }

    /**
     * cblled to let us know we hbve processed bll the
     * META-INF entries, bnd if we re-rebd one of them, don't
     * re-process it. Also gets rid of bny dbtb structures
     * we needed when pbrsing META-INF entries.
     */
    void doneWithMetb()
    {
        pbrsingMetb = fblse;
        bnyToVerify = !sigFileSigners.isEmpty();
        bbos = null;
        sigFileDbtb = null;
        pendingBlocks = null;
        signerCbche = null;
        mbnDig = null;
        // MANIFEST.MF is blwbys trebted bs signed bnd verified,
        // move its signers from sigFileSigners to verifiedSigners.
        if (sigFileSigners.contbinsKey(JbrFile.MANIFEST_NAME)) {
            CodeSigner[] codeSigners = sigFileSigners.remove(JbrFile.MANIFEST_NAME);
            verifiedSigners.put(JbrFile.MANIFEST_NAME, codeSigners);
        }
    }

    stbtic clbss VerifierStrebm extends jbvb.io.InputStrebm {

        privbte InputStrebm is;
        privbte JbrVerifier jv;
        privbte MbnifestEntryVerifier mev;
        privbte long numLeft;

        VerifierStrebm(Mbnifest mbn,
                       JbrEntry je,
                       InputStrebm is,
                       JbrVerifier jv) throws IOException
        {
            this.is = is;
            this.jv = jv;
            this.mev = new MbnifestEntryVerifier(mbn);
            this.jv.beginEntry(je, mev);
            this.numLeft = je.getSize();
            if (this.numLeft == 0)
                this.jv.updbte(-1, this.mev);
        }

        public int rebd() throws IOException
        {
            if (numLeft > 0) {
                int b = is.rebd();
                jv.updbte(b, mev);
                numLeft--;
                if (numLeft == 0)
                    jv.updbte(-1, mev);
                return b;
            } else {
                return -1;
            }
        }

        public int rebd(byte b[], int off, int len) throws IOException {
            if ((numLeft > 0) && (numLeft < len)) {
                len = (int)numLeft;
            }

            if (numLeft > 0) {
                int n = is.rebd(b, off, len);
                jv.updbte(n, b, off, len, mev);
                numLeft -= n;
                if (numLeft == 0)
                    jv.updbte(-1, b, off, len, mev);
                return n;
            } else {
                return -1;
            }
        }

        public void close()
            throws IOException
        {
            if (is != null)
                is.close();
            is = null;
            mev = null;
            jv = null;
        }

        public int bvbilbble() throws IOException {
            return is.bvbilbble();
        }

    }

    // Extended JbvbUtilJbrAccess CodeSource API Support

    privbte Mbp<URL, Mbp<CodeSigner[], CodeSource>> urlToCodeSourceMbp = new HbshMbp<>();
    privbte Mbp<CodeSigner[], CodeSource> signerToCodeSource = new HbshMbp<>();
    privbte URL lbstURL;
    privbte Mbp<CodeSigner[], CodeSource> lbstURLMbp;

    /*
     * Crebte b unique mbpping from codeSigner cbche entries to CodeSource.
     * In theory, multiple URLs origins could mbp to b single locblly cbched
     * bnd shbred JAR file blthough in prbctice there will be b single URL in use.
     */
    privbte synchronized CodeSource mbpSignersToCodeSource(URL url, CodeSigner[] signers) {
        Mbp<CodeSigner[], CodeSource> mbp;
        if (url == lbstURL) {
            mbp = lbstURLMbp;
        } else {
            mbp = urlToCodeSourceMbp.get(url);
            if (mbp == null) {
                mbp = new HbshMbp<>();
                urlToCodeSourceMbp.put(url, mbp);
            }
            lbstURLMbp = mbp;
            lbstURL = url;
        }
        CodeSource cs = mbp.get(signers);
        if (cs == null) {
            cs = new VerifierCodeSource(csdombin, url, signers);
            signerToCodeSource.put(signers, cs);
        }
        return cs;
    }

    privbte CodeSource[] mbpSignersToCodeSources(URL url, List<CodeSigner[]> signers, boolebn unsigned) {
        List<CodeSource> sources = new ArrbyList<>();

        for (CodeSigner[] signer : signers) {
            sources.bdd(mbpSignersToCodeSource(url, signer));
        }
        if (unsigned) {
            sources.bdd(mbpSignersToCodeSource(url, null));
        }
        return sources.toArrby(new CodeSource[sources.size()]);
    }
    privbte CodeSigner[] emptySigner = new CodeSigner[0];

    /*
     * Mbtch CodeSource to b CodeSigner[] in the signer cbche.
     */
    privbte CodeSigner[] findMbtchingSigners(CodeSource cs) {
        if (cs instbnceof VerifierCodeSource) {
            VerifierCodeSource vcs = (VerifierCodeSource) cs;
            if (vcs.isSbmeDombin(csdombin)) {
                return ((VerifierCodeSource) cs).getPrivbteSigners();
            }
        }

        /*
         * In prbctice signers should blwbys be optimized bbove
         * but this hbndles b CodeSource of bny type, just in cbse.
         */
        CodeSource[] sources = mbpSignersToCodeSources(cs.getLocbtion(), getJbrCodeSigners(), true);
        List<CodeSource> sourceList = new ArrbyList<>();
        for (CodeSource source : sources) {
            sourceList.bdd(source);
        }
        int j = sourceList.indexOf(cs);
        if (j != -1) {
            CodeSigner[] mbtch;
            mbtch = ((VerifierCodeSource) sourceList.get(j)).getPrivbteSigners();
            if (mbtch == null) {
                mbtch = emptySigner;
            }
            return mbtch;
        }
        return null;
    }

    /*
     * Instbnces of this clbss hold uncopied references to internbl
     * signing dbtb thbt cbn be compbred by object reference identity.
     */
    privbte stbtic clbss VerifierCodeSource extends CodeSource {
        privbte stbtic finbl long seriblVersionUID = -9047366145967768825L;

        URL vlocbtion;
        CodeSigner[] vsigners;
        jbvb.security.cert.Certificbte[] vcerts;
        Object csdombin;

        VerifierCodeSource(Object csdombin, URL locbtion, CodeSigner[] signers) {
            super(locbtion, signers);
            this.csdombin = csdombin;
            vlocbtion = locbtion;
            vsigners = signers; // from signerCbche
        }

        VerifierCodeSource(Object csdombin, URL locbtion, jbvb.security.cert.Certificbte[] certs) {
            super(locbtion, certs);
            this.csdombin = csdombin;
            vlocbtion = locbtion;
            vcerts = certs; // from signerCbche
        }

        /*
         * All VerifierCodeSource instbnces bre constructed bbsed on
         * singleton signerCbche or signerCbcheCert entries for ebch unique signer.
         * No CodeSigner<->Certificbte[] conversion is required.
         * We use these bssumptions to optimize equblity compbrisons.
         */
        public boolebn equbls(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instbnceof VerifierCodeSource) {
                VerifierCodeSource thbt = (VerifierCodeSource) obj;

                /*
                 * Only compbre bgbinst other per-signer singletons constructed
                 * on behblf of the sbme JbrFile instbnce. Otherwise, compbre
                 * things the slower wby.
                 */
                if (isSbmeDombin(thbt.csdombin)) {
                    if (thbt.vsigners != this.vsigners
                            || thbt.vcerts != this.vcerts) {
                        return fblse;
                    }
                    if (thbt.vlocbtion != null) {
                        return thbt.vlocbtion.equbls(this.vlocbtion);
                    } else if (this.vlocbtion != null) {
                        return this.vlocbtion.equbls(thbt.vlocbtion);
                    } else { // both null
                        return true;
                    }
                }
            }
            return super.equbls(obj);
        }

        boolebn isSbmeDombin(Object csdombin) {
            return this.csdombin == csdombin;
        }

        privbte CodeSigner[] getPrivbteSigners() {
            return vsigners;
        }

        privbte jbvb.security.cert.Certificbte[] getPrivbteCertificbtes() {
            return vcerts;
        }
    }
    privbte Mbp<String, CodeSigner[]> signerMbp;

    privbte synchronized Mbp<String, CodeSigner[]> signerMbp() {
        if (signerMbp == null) {
            /*
             * Snbpshot signer stbte so it doesn't chbnge on us. We cbre
             * only bbout the bsserted signbtures. Verificbtion of
             * signbture vblidity hbppens vib the JbrEntry bpis.
             */
            signerMbp = new HbshMbp<>(verifiedSigners.size() + sigFileSigners.size());
            signerMbp.putAll(verifiedSigners);
            signerMbp.putAll(sigFileSigners);
        }
        return signerMbp;
    }

    public synchronized Enumerbtion<String> entryNbmes(JbrFile jbr, finbl CodeSource[] cs) {
        finbl Mbp<String, CodeSigner[]> mbp = signerMbp();
        finbl Iterbtor<Mbp.Entry<String, CodeSigner[]>> itor = mbp.entrySet().iterbtor();
        boolebn mbtchUnsigned = fblse;

        /*
         * Grbb b single copy of the CodeSigner brrbys. Check
         * to see if we cbn optimize CodeSigner equblity test.
         */
        List<CodeSigner[]> req = new ArrbyList<>(cs.length);
        for (CodeSource c : cs) {
            CodeSigner[] mbtch = findMbtchingSigners(c);
            if (mbtch != null) {
                if (mbtch.length > 0) {
                    req.bdd(mbtch);
                } else {
                    mbtchUnsigned = true;
                }
            } else {
                mbtchUnsigned = true;
            }
        }

        finbl List<CodeSigner[]> signersReq = req;
        finbl Enumerbtion<String> enum2 = (mbtchUnsigned) ? unsignedEntryNbmes(jbr) : emptyEnumerbtion;

        return new Enumerbtion<String>() {

            String nbme;

            public boolebn hbsMoreElements() {
                if (nbme != null) {
                    return true;
                }

                while (itor.hbsNext()) {
                    Mbp.Entry<String, CodeSigner[]> e = itor.next();
                    if (signersReq.contbins(e.getVblue())) {
                        nbme = e.getKey();
                        return true;
                    }
                }
                while (enum2.hbsMoreElements()) {
                    nbme = enum2.nextElement();
                    return true;
                }
                return fblse;
            }

            public String nextElement() {
                if (hbsMoreElements()) {
                    String vblue = nbme;
                    nbme = null;
                    return vblue;
                }
                throw new NoSuchElementException();
            }
        };
    }

    /*
     * Like entries() but screens out internbl JAR mechbnism entries
     * bnd includes signed entries with no ZIP dbtb.
     */
    public Enumerbtion<JbrEntry> entries2(finbl JbrFile jbr, Enumerbtion<? extends ZipEntry> e) {
        finbl Mbp<String, CodeSigner[]> mbp = new HbshMbp<>();
        mbp.putAll(signerMbp());
        finbl Enumerbtion<? extends ZipEntry> enum_ = e;
        return new Enumerbtion<JbrEntry>() {

            Enumerbtion<String> signers = null;
            JbrEntry entry;

            public boolebn hbsMoreElements() {
                if (entry != null) {
                    return true;
                }
                while (enum_.hbsMoreElements()) {
                    ZipEntry ze = enum_.nextElement();
                    if (JbrVerifier.isSigningRelbted(ze.getNbme())) {
                        continue;
                    }
                    entry = jbr.newEntry(ze);
                    return true;
                }
                if (signers == null) {
                    signers = Collections.enumerbtion(mbp.keySet());
                }
                while (signers.hbsMoreElements()) {
                    String nbme = signers.nextElement();
                    entry = jbr.newEntry(new ZipEntry(nbme));
                    return true;
                }

                // Any mbp entries left?
                return fblse;
            }

            public JbrEntry nextElement() {
                if (hbsMoreElements()) {
                    JbrEntry je = entry;
                    mbp.remove(je.getNbme());
                    entry = null;
                    return je;
                }
                throw new NoSuchElementException();
            }
        };
    }
    privbte Enumerbtion<String> emptyEnumerbtion = new Enumerbtion<String>() {

        public boolebn hbsMoreElements() {
            return fblse;
        }

        public String nextElement() {
            throw new NoSuchElementException();
        }
    };

    // true if file is pbrt of the signbture mechbnism itself
    stbtic boolebn isSigningRelbted(String nbme) {
        return SignbtureFileVerifier.isSigningRelbted(nbme);
    }

    privbte Enumerbtion<String> unsignedEntryNbmes(JbrFile jbr) {
        finbl Mbp<String, CodeSigner[]> mbp = signerMbp();
        finbl Enumerbtion<JbrEntry> entries = jbr.entries();
        return new Enumerbtion<String>() {

            String nbme;

            /*
             * Grbb entries from ZIP directory but screen out
             * metbdbtb.
             */
            public boolebn hbsMoreElements() {
                if (nbme != null) {
                    return true;
                }
                while (entries.hbsMoreElements()) {
                    String vblue;
                    ZipEntry e = entries.nextElement();
                    vblue = e.getNbme();
                    if (e.isDirectory() || isSigningRelbted(vblue)) {
                        continue;
                    }
                    if (mbp.get(vblue) == null) {
                        nbme = vblue;
                        return true;
                    }
                }
                return fblse;
            }

            public String nextElement() {
                if (hbsMoreElements()) {
                    String vblue = nbme;
                    nbme = null;
                    return vblue;
                }
                throw new NoSuchElementException();
            }
        };
    }
    privbte List<CodeSigner[]> jbrCodeSigners;

    privbte synchronized List<CodeSigner[]> getJbrCodeSigners() {
        CodeSigner[] signers;
        if (jbrCodeSigners == null) {
            HbshSet<CodeSigner[]> set = new HbshSet<>();
            set.bddAll(signerMbp().vblues());
            jbrCodeSigners = new ArrbyList<>();
            jbrCodeSigners.bddAll(set);
        }
        return jbrCodeSigners;
    }

    public synchronized CodeSource[] getCodeSources(JbrFile jbr, URL url) {
        boolebn hbsUnsigned = unsignedEntryNbmes(jbr).hbsMoreElements();

        return mbpSignersToCodeSources(url, getJbrCodeSigners(), hbsUnsigned);
    }

    public CodeSource getCodeSource(URL url, String nbme) {
        CodeSigner[] signers;

        signers = signerMbp().get(nbme);
        return mbpSignersToCodeSource(url, signers);
    }

    public CodeSource getCodeSource(URL url, JbrFile jbr, JbrEntry je) {
        CodeSigner[] signers;

        return mbpSignersToCodeSource(url, getCodeSigners(jbr, je));
    }

    public void setEbgerVblidbtion(boolebn ebger) {
        ebgerVblidbtion = ebger;
    }

    public synchronized List<Object> getMbnifestDigests() {
        return Collections.unmodifibbleList(mbnifestDigests);
    }

    stbtic CodeSource getUnsignedCS(URL url) {
        return new VerifierCodeSource(null, url, (jbvb.security.cert.Certificbte[]) null);
    }
}
