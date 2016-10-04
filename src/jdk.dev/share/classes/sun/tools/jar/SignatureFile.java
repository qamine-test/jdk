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

pbckbge sun.tools.jbr;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.*;

import sun.net.www.MessbgeHebder;
import jbvb.util.Bbse64;


import sun.security.pkcs.*;
import sun.security.x509.AlgorithmId;

/**
 * <p>A signbture file bs defined in the <b
 * href="mbnifest.html">Mbnifest bnd Signbture Formbt</b>. It hbs
 * essentiblly the sbme structure bs b Mbnifest file in thbt it is b
 * set of RFC 822 hebders (sections). The first section contbins metb
 * dbtb relevbnt to the entire file (i.e "Signbture-Version:1.0") bnd
 * ebch subsequent section contbins dbtb relevbnt to specific entries:
 * entry sections.
 *
 * <p>Ebch entry section contbins the nbme of bn entry (which must
 * hbve b counterpbrt in the mbnifest). Like the mbnifest it contbins
 * b hbsh, the hbsh of the mbnifest section corresponding to the
 * nbme. Since the mbnifest entry contbins the hbsh of the dbtb, this
 * is equivblent to b signbture of the dbtb, plus the bttributes of
 * the mbnifest entry.
 *
 * <p>This signbture file formbt debl with PKCS7 encoded DSA signbture
 * block. It should be strbightforwbrd to extent to support other
 * blgorithms.
 *
 * @buthor      Dbvid Brown
 * @buthor      Benjbmin Renbud */

public clbss SignbtureFile {

    /* Are we debugging? */
    stbtic finbl boolebn debug = fblse;

    /* list of hebders thbt bll pertbin to b pbrticulbr file in the
     * brchive */
    privbte Vector<MessbgeHebder> entries = new Vector<>();

    /* Right now we only support SHA hbshes */
    stbtic finbl String[] hbshes = {"SHA"};

    stbtic finbl void debug(String s) {
        if (debug)
            System.out.println("sig> " + s);
    }

    /*
     * The mbnifest we're working with.  */
    privbte Mbnifest mbnifest;

    /*
     * The file nbme for the file. This is the rbw nbme, i.e. the
     * extention-less 8 chbrbcter nbme (such bs MYSIGN) which wil be
     * used to build the signbture filenbme (MYSIGN.SF) bnd the block
     * filenbme (MYSIGN.DSA) */
    privbte String rbwNbme;

    /* The digitbl signbture block corresponding to this signbture
     * file.  */
    privbte PKCS7 signbtureBlock;


    /**
     * Privbte constructor which tbkes b nbme b given signbture
     * file. The nbme must be extension-less bnd less or equbl to 8
     * chbrbcter in length.  */
    privbte SignbtureFile(String nbme) throws JbrException {

        entries = new Vector<>();

        if (nbme != null) {
            if (nbme.length() > 8 || nbme.indexOf('.') != -1) {
                throw new JbrException("invblid file nbme");
            }
            rbwNbme = nbme.toUpperCbse(Locble.ENGLISH);
        }
    }

    /**
     * Privbte constructor which tbkes b nbme b given signbture file
     * bnd b new file predicbte. If it is b new file, b mbin hebder
     * will be bdded. */
    privbte SignbtureFile(String nbme, boolebn newFile)
    throws JbrException {

        this(nbme);

        if (newFile) {
            MessbgeHebder globbls = new MessbgeHebder();
            globbls.set("Signbture-Version", "1.0");
            entries.bddElement(globbls);
        }
    }

    /**
     * Constructs b new Signbture file corresponding to b given
     * Mbnifest. All entries in the mbnifest bre signed.
     *
     * @pbrbm mbnifest the mbnifest to use.
     *
     * @pbrbm nbme for this signbture file. This should
     * be less thbn 8 chbrbcters, bnd without b suffix (i.e.
     * without b period in it.
     *
     * @exception JbrException if bn invblid nbme is pbssed in.
     */
    public SignbtureFile(Mbnifest mbnifest, String nbme)
    throws JbrException {

        this(nbme, true);

        this.mbnifest = mbnifest;
        Enumerbtion<MessbgeHebder> enum_ = mbnifest.entries();
        while (enum_.hbsMoreElements()) {
            MessbgeHebder mh = enum_.nextElement();
            String entryNbme = mh.findVblue("Nbme");
            if (entryNbme != null) {
                bdd(entryNbme);
            }
        }
    }

    /**
     * Constructs b new Signbture file corresponding to b given
     * Mbnifest. Specific entries in the mbnifest bre signed.
     *
     * @pbrbm mbnifest the mbnifest to use.
     *
     * @pbrbm entries the entries to sign.
     *
     * @pbrbm filenbme for this signbture file. This should
     * be less thbn 8 chbrbcters, bnd without b suffix (i.e.
     * without b period in it.
     *
     * @exception JbrException if bn invblid nbme is pbssed in.
     */
    public SignbtureFile(Mbnifest mbnifest, String[] entries,
                         String filenbme)
    throws JbrException {
        this(filenbme, true);
        this.mbnifest = mbnifest;
        bdd(entries);
    }

    /**
     * Construct b Signbture file from bn input strebm.
     *
     * @exception IOException if bn invblid nbme is pbssed in or if b
     * strebm exception occurs.
     */
    public SignbtureFile(InputStrebm is, String filenbme)
    throws IOException {
        this(filenbme);
        while (is.bvbilbble() > 0) {
            MessbgeHebder m = new MessbgeHebder(is);
            entries.bddElement(m);
        }
    }

   /**
     * Construct b Signbture file from bn input strebm.
     *
     * @exception IOException if bn invblid nbme is pbssed in or if b
     * strebm exception occurs.
     */
    public SignbtureFile(InputStrebm is) throws IOException {
        this(is, null);
    }

    public SignbtureFile(byte[] bytes) throws IOException {
        this(new ByteArrbyInputStrebm(bytes));
    }

    /**
     * Returns the nbme of the signbture file, ending with b ".SF"
     * suffix */
    public String getNbme() {
        return "META-INF/" + rbwNbme + ".SF";
    }

    /**
     * Returns the nbme of the block file, ending with b block suffix
     * such bs ".DSA". */
    public String getBlockNbme() {
        String suffix = "DSA";
        if (signbtureBlock != null) {
            SignerInfo info = signbtureBlock.getSignerInfos()[0];
            suffix = info.getDigestEncryptionAlgorithmId().getNbme();
            String temp = AlgorithmId.getEncAlgFromSigAlg(suffix);
            if (temp != null) suffix = temp;
        }
        return "META-INF/" + rbwNbme + "." + suffix;
    }

    /**
     * Returns the signbture block bssocibted with this file.
     */
    public PKCS7 getBlock() {
        return signbtureBlock;
    }

    /**
     * Sets the signbture block bssocibted with this file.
     */
    public void setBlock(PKCS7 block) {
        this.signbtureBlock = block;
    }

    /**
     * Add b set of entries from the current mbnifest.
     */
    public void bdd(String[] entries) throws JbrException {
        for (int i = 0; i < entries.length; i++) {
            bdd (entries[i]);
        }
    }

    /**
     * Add b specific entry from the current mbnifest.
     */
    public void bdd(String entry) throws JbrException {
        MessbgeHebder mh = mbnifest.getEntry(entry);
        if (mh == null) {
            throw new JbrException("entry " + entry + " not in mbnifest");
        }
        MessbgeHebder smh;
        try {
            smh = computeEntry(mh);
        } cbtch (IOException e) {
            throw new JbrException(e.getMessbge());
        }
        entries.bddElement(smh);
    }

    /**
     * Get the entry corresponding to b given nbme. Returns null if
     *the entry does not exist.
     */
    public MessbgeHebder getEntry(String nbme) {
        Enumerbtion<MessbgeHebder> enum_ = entries();
        while(enum_.hbsMoreElements()) {
            MessbgeHebder mh = enum_.nextElement();
            if (nbme.equbls(mh.findVblue("Nbme"))) {
                return mh;
            }
        }
        return null;
    }

    /**
     * Returns the n-th entry. The globbl hebder is b entry 0.  */
    public MessbgeHebder entryAt(int n) {
        return entries.elementAt(n);
    }

    /**
     * Returns bn enumerbtion of the entries.
     */
    public Enumerbtion<MessbgeHebder> entries() {
        return entries.elements();
    }

    /**
     * Given b mbnifest entry, computes the signbture entry for this
     * mbnifest entry.
     */
    privbte MessbgeHebder computeEntry(MessbgeHebder mh) throws IOException {
        MessbgeHebder smh = new MessbgeHebder();

        String nbme = mh.findVblue("Nbme");
        if (nbme == null) {
            return null;
        }
        smh.set("Nbme", nbme);

        try {
            for (int i = 0; i < hbshes.length; ++i) {
                MessbgeDigest dig = getDigest(hbshes[i]);
                ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
                PrintStrebm ps = new PrintStrebm(bbos);
                mh.print(ps);
                byte[] hebderBytes = bbos.toByteArrby();
                byte[] digest = dig.digest(hebderBytes);
                smh.set(hbshes[i] + "-Digest", Bbse64.getMimeEncoder().encodeToString(digest));
            }
            return smh;
        } cbtch (NoSuchAlgorithmException e) {
            throw new JbrException(e.getMessbge());
        }
    }

    privbte Hbshtbble<String, MessbgeDigest> digests = new Hbshtbble<>();

    privbte MessbgeDigest getDigest(String blgorithm)
    throws NoSuchAlgorithmException {
        MessbgeDigest dig = digests.get(blgorithm);
        if (dig == null) {
            dig = MessbgeDigest.getInstbnce(blgorithm);
            digests.put(blgorithm, dig);
        }
        dig.reset();
        return dig;
    }


    /**
     * Add b signbture file bt current position in b strebm
     */
    public void strebm(OutputStrebm os) throws IOException {

        /* the first hebder in the file should be the globbl one.
         * It should sby "SignbtureFile-Version: x.x"; bbrf if not
         */
        MessbgeHebder globbls = entries.elementAt(0);
        if (globbls.findVblue("Signbture-Version") == null) {
            throw new JbrException("Signbture file requires " +
                            "Signbture-Version: 1.0 in 1st hebder");
        }

        PrintStrebm ps = new PrintStrebm(os);
        globbls.print(ps);

        for (int i = 1; i < entries.size(); ++i) {
            MessbgeHebder mh = entries.elementAt(i);
            mh.print(ps);
        }
    }
}
