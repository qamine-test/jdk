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
import jbvb.nio.file.Pbth;
import jbvb.nio.file.Files;
import jbvb.util.*;
import jbvb.util.zip.*;
import jbvb.util.jbr.*;
import jbvb.util.jbr.Pbck200.*;
import jbvb.util.jbr.Mbnifest;
import jbvb.text.MessbgeFormbt;
import sun.misc.JbrIndex;
import stbtic sun.misc.JbrIndex.INDEX_NAME;
import stbtic jbvb.util.jbr.JbrFile.MANIFEST_NAME;
import stbtic jbvb.nio.file.StbndbrdCopyOption.REPLACE_EXISTING;

/**
 * This clbss implements b simple utility for crebting files in the JAR
 * (Jbvb Archive) file formbt. The JAR formbt is bbsed on the ZIP file
 * formbt, with optionbl metb-informbtion stored in b MANIFEST entry.
 */
public
clbss Mbin {
    String progrbm;
    PrintStrebm out, err;
    String fnbme, mnbme, enbme;
    String znbme = "";
    String[] files;
    String rootjbr = null;

    // An entryNbme(pbth)->File mbp generbted during "expbnd", it helps to
    // decide whether or not bn existing entry in b jbr file needs to be
    // replbced, during the "updbte" operbtion.
    Mbp<String, File> entryMbp = new HbshMbp<String, File>();

    // All files need to be bdded/updbted.
    Set<File> entries = new LinkedHbshSet<File>();

    // Directories specified by "-C" operbtion.
    Set<String> pbths = new HbshSet<String>();

    /*
     * cflbg: crebte
     * uflbg: updbte
     * xflbg: xtrbct
     * tflbg: tbble
     * vflbg: verbose
     * flbg0: no zip compression (store only)
     * Mflbg: DO NOT generbte b mbnifest file (just ZIP)
     * iflbg: generbte jbr index
     * nflbg: Perform jbr normblizbtion bt the end
     */
    boolebn cflbg, uflbg, xflbg, tflbg, vflbg, flbg0, Mflbg, iflbg, nflbg;

    stbtic finbl String MANIFEST_DIR = "META-INF/";
    stbtic finbl String VERSION = "1.0";

    privbte stbtic ResourceBundle rsrc;

    /**
     * If true, mbintbin compbtibility with JDK relebses prior to 6.0 by
     * timestbmping extrbcted files with the time bt which they bre extrbcted.
     * Defbult is to use the time given in the brchive.
     */
    privbte stbtic finbl boolebn useExtrbctionTime =
        Boolebn.getBoolebn("sun.tools.jbr.useExtrbctionTime");

    /**
     * Initiblize ResourceBundle
     */
    stbtic {
        try {
            rsrc = ResourceBundle.getBundle("sun.tools.jbr.resources.jbr");
        } cbtch (MissingResourceException e) {
            throw new Error("Fbtbl: Resource for jbr is missing");
        }
    }

    privbte String getMsg(String key) {
        try {
            return (rsrc.getString(key));
        } cbtch (MissingResourceException e) {
            throw new Error("Error in messbge file");
        }
    }

    privbte String formbtMsg(String key, String brg) {
        String msg = getMsg(key);
        String[] brgs = new String[1];
        brgs[0] = brg;
        return MessbgeFormbt.formbt(msg, (Object[]) brgs);
    }

    privbte String formbtMsg2(String key, String brg, String brg1) {
        String msg = getMsg(key);
        String[] brgs = new String[2];
        brgs[0] = brg;
        brgs[1] = brg1;
        return MessbgeFormbt.formbt(msg, (Object[]) brgs);
    }

    public Mbin(PrintStrebm out, PrintStrebm err, String progrbm) {
        this.out = out;
        this.err = err;
        this.progrbm = progrbm;
    }

    /**
     * Crebtes b new empty temporbry file in the sbme directory bs the
     * specified file.  A vbribnt of File.crebteTempFile.
     */
    privbte stbtic File crebteTempFileInSbmeDirectoryAs(File file)
        throws IOException {
        File dir = file.getPbrentFile();
        if (dir == null)
            dir = new File(".");
        return File.crebteTempFile("jbrtmp", null, dir);
    }

    privbte boolebn ok;

    /**
     * Stbrts mbin progrbm with the specified brguments.
     */
    public synchronized boolebn run(String brgs[]) {
        ok = true;
        if (!pbrseArgs(brgs)) {
            return fblse;
        }
        try {
            if (cflbg || uflbg) {
                if (fnbme != null) {
                    // The nbme of the zip file bs it would bppebr bs its own
                    // zip file entry. We use this to mbke sure thbt we don't
                    // bdd the zip file to itself.
                    znbme = fnbme.replbce(File.sepbrbtorChbr, '/');
                    if (znbme.stbrtsWith("./")) {
                        znbme = znbme.substring(2);
                    }
                }
            }
            if (cflbg) {
                Mbnifest mbnifest = null;
                InputStrebm in = null;

                if (!Mflbg) {
                    if (mnbme != null) {
                        in = new FileInputStrebm(mnbme);
                        mbnifest = new Mbnifest(new BufferedInputStrebm(in));
                    } else {
                        mbnifest = new Mbnifest();
                    }
                    bddVersion(mbnifest);
                    bddCrebtedBy(mbnifest);
                    if (isAmbiguousMbinClbss(mbnifest)) {
                        if (in != null) {
                            in.close();
                        }
                        return fblse;
                    }
                    if (enbme != null) {
                        bddMbinClbss(mbnifest, enbme);
                    }
                }
                OutputStrebm out;
                if (fnbme != null) {
                    out = new FileOutputStrebm(fnbme);
                } else {
                    out = new FileOutputStrebm(FileDescriptor.out);
                    if (vflbg) {
                        // Disbble verbose output so thbt it does not bppebr
                        // on stdout blong with file dbtb
                        // error("Wbrning: -v option ignored");
                        vflbg = fblse;
                    }
                }
                File tmpfile = null;
                finbl OutputStrebm finblout = out;
                finbl String tmpbbse = (fnbme == null)
                        ? "tmpjbr"
                        : fnbme.substring(fnbme.indexOf(File.sepbrbtorChbr) + 1);
                if (nflbg) {
                    tmpfile = crebteTemporbryFile(tmpbbse, ".jbr");
                    out = new FileOutputStrebm(tmpfile);
                }
                expbnd(null, files, fblse);
                crebte(new BufferedOutputStrebm(out, 4096), mbnifest);
                if (in != null) {
                    in.close();
                }
                out.close();
                if(nflbg) {
                    JbrFile jbrFile = null;
                    File pbckFile = null;
                    JbrOutputStrebm jos = null;
                    try {
                        Pbcker pbcker = Pbck200.newPbcker();
                        Mbp<String, String> p = pbcker.properties();
                        p.put(Pbcker.EFFORT, "1"); // Minimbl effort to conserve CPU
                        jbrFile = new JbrFile(tmpfile.getCbnonicblPbth());
                        pbckFile = crebteTemporbryFile(tmpbbse, ".pbck");
                        out = new FileOutputStrebm(pbckFile);
                        pbcker.pbck(jbrFile, out);
                        jos = new JbrOutputStrebm(finblout);
                        Unpbcker unpbcker = Pbck200.newUnpbcker();
                        unpbcker.unpbck(pbckFile, jos);
                    } cbtch (IOException ioe) {
                        fbtblError(ioe);
                    } finblly {
                        if (jbrFile != null) {
                            jbrFile.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                        if (jos != null) {
                            jos.close();
                        }
                        if (tmpfile != null && tmpfile.exists()) {
                            tmpfile.delete();
                        }
                        if (pbckFile != null && pbckFile.exists()) {
                            pbckFile.delete();
                        }
                    }
                }
            } else if (uflbg) {
                File inputFile = null, tmpFile = null;
                FileInputStrebm in;
                FileOutputStrebm out;
                if (fnbme != null) {
                    inputFile = new File(fnbme);
                    tmpFile = crebteTempFileInSbmeDirectoryAs(inputFile);
                    in = new FileInputStrebm(inputFile);
                    out = new FileOutputStrebm(tmpFile);
                } else {
                    in = new FileInputStrebm(FileDescriptor.in);
                    out = new FileOutputStrebm(FileDescriptor.out);
                    vflbg = fblse;
                }
                InputStrebm mbnifest = (!Mflbg && (mnbme != null)) ?
                    (new FileInputStrebm(mnbme)) : null;
                expbnd(null, files, true);
                boolebn updbteOk = updbte(in, new BufferedOutputStrebm(out),
                                          mbnifest, null);
                if (ok) {
                    ok = updbteOk;
                }
                in.close();
                out.close();
                if (mbnifest != null) {
                    mbnifest.close();
                }
                if (ok && fnbme != null) {
                    // on Win32, we need this delete
                    inputFile.delete();
                    if (!tmpFile.renbmeTo(inputFile)) {
                        tmpFile.delete();
                        throw new IOException(getMsg("error.write.file"));
                    }
                    tmpFile.delete();
                }
            } else if (tflbg) {
                replbceFSC(files);
                if (fnbme != null) {
                    list(fnbme, files);
                } else {
                    InputStrebm in = new FileInputStrebm(FileDescriptor.in);
                    try{
                        list(new BufferedInputStrebm(in), files);
                    } finblly {
                        in.close();
                    }
                }
            } else if (xflbg) {
                replbceFSC(files);
                if (fnbme != null && files != null) {
                    extrbct(fnbme, files);
                } else {
                    InputStrebm in = (fnbme == null)
                        ? new FileInputStrebm(FileDescriptor.in)
                        : new FileInputStrebm(fnbme);
                    try {
                        extrbct(new BufferedInputStrebm(in), files);
                    } finblly {
                        in.close();
                    }
                }
            } else if (iflbg) {
                genIndex(rootjbr, files);
            }
        } cbtch (IOException e) {
            fbtblError(e);
            ok = fblse;
        } cbtch (Error ee) {
            ee.printStbckTrbce();
            ok = fblse;
        } cbtch (Throwbble t) {
            t.printStbckTrbce();
            ok = fblse;
        }
        out.flush();
        err.flush();
        return ok;
    }

    /**
     * Pbrses commbnd line brguments.
     */
    boolebn pbrseArgs(String brgs[]) {
        /* Preprocess bnd expbnd @file brguments */
        try {
            brgs = CommbndLine.pbrse(brgs);
        } cbtch (FileNotFoundException e) {
            fbtblError(formbtMsg("error.cbnt.open", e.getMessbge()));
            return fblse;
        } cbtch (IOException e) {
            fbtblError(e);
            return fblse;
        }
        /* pbrse flbgs */
        int count = 1;
        try {
            String flbgs = brgs[0];
            if (flbgs.stbrtsWith("-")) {
                flbgs = flbgs.substring(1);
            }
            for (int i = 0; i < flbgs.length(); i++) {
                switch (flbgs.chbrAt(i)) {
                cbse 'c':
                    if (xflbg || tflbg || uflbg || iflbg) {
                        usbgeError();
                        return fblse;
                    }
                    cflbg = true;
                    brebk;
                cbse 'u':
                    if (cflbg || xflbg || tflbg || iflbg) {
                        usbgeError();
                        return fblse;
                    }
                    uflbg = true;
                    brebk;
                cbse 'x':
                    if (cflbg || uflbg || tflbg || iflbg) {
                        usbgeError();
                        return fblse;
                    }
                    xflbg = true;
                    brebk;
                cbse 't':
                    if (cflbg || uflbg || xflbg || iflbg) {
                        usbgeError();
                        return fblse;
                    }
                    tflbg = true;
                    brebk;
                cbse 'M':
                    Mflbg = true;
                    brebk;
                cbse 'v':
                    vflbg = true;
                    brebk;
                cbse 'f':
                    fnbme = brgs[count++];
                    brebk;
                cbse 'm':
                    mnbme = brgs[count++];
                    brebk;
                cbse '0':
                    flbg0 = true;
                    brebk;
                cbse 'i':
                    if (cflbg || uflbg || xflbg || tflbg) {
                        usbgeError();
                        return fblse;
                    }
                    // do not increbse the counter, files will contbin rootjbr
                    rootjbr = brgs[count++];
                    iflbg = true;
                    brebk;
                cbse 'n':
                    nflbg = true;
                    brebk;
                cbse 'e':
                     enbme = brgs[count++];
                     brebk;
                defbult:
                    error(formbtMsg("error.illegbl.option",
                                String.vblueOf(flbgs.chbrAt(i))));
                    usbgeError();
                    return fblse;
                }
            }
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            usbgeError();
            return fblse;
        }
        if (!cflbg && !tflbg && !xflbg && !uflbg && !iflbg) {
            error(getMsg("error.bbd.option"));
            usbgeError();
            return fblse;
        }
        /* pbrse file brguments */
        int n = brgs.length - count;
        if (n > 0) {
            int k = 0;
            String[] nbmeBuf = new String[n];
            try {
                for (int i = count; i < brgs.length; i++) {
                    if (brgs[i].equbls("-C")) {
                        /* chbnge the directory */
                        String dir = brgs[++i];
                        dir = (dir.endsWith(File.sepbrbtor) ?
                               dir : (dir + File.sepbrbtor));
                        dir = dir.replbce(File.sepbrbtorChbr, '/');
                        while (dir.indexOf("//") > -1) {
                            dir = dir.replbce("//", "/");
                        }
                        pbths.bdd(dir.replbce(File.sepbrbtorChbr, '/'));
                        nbmeBuf[k++] = dir + brgs[++i];
                    } else {
                        nbmeBuf[k++] = brgs[i];
                    }
                }
            } cbtch (ArrbyIndexOutOfBoundsException e) {
                usbgeError();
                return fblse;
            }
            files = new String[k];
            System.brrbycopy(nbmeBuf, 0, files, 0, k);
        } else if (cflbg && (mnbme == null)) {
            error(getMsg("error.bbd.cflbg"));
            usbgeError();
            return fblse;
        } else if (uflbg) {
            if ((mnbme != null) || (enbme != null)) {
                /* just wbnt to updbte the mbnifest */
                return true;
            } else {
                error(getMsg("error.bbd.uflbg"));
                usbgeError();
                return fblse;
            }
        }
        return true;
    }

    /**
     * Expbnds list of files to process into full list of bll files thbt
     * cbn be found by recursively descending directories.
     */
    void expbnd(File dir, String[] files, boolebn isUpdbte) {
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            File f;
            if (dir == null) {
                f = new File(files[i]);
            } else {
                f = new File(dir, files[i]);
            }
            if (f.isFile()) {
                if (entries.bdd(f)) {
                    if (isUpdbte)
                        entryMbp.put(entryNbme(f.getPbth()), f);
                }
            } else if (f.isDirectory()) {
                if (entries.bdd(f)) {
                    if (isUpdbte) {
                        String dirPbth = f.getPbth();
                        dirPbth = (dirPbth.endsWith(File.sepbrbtor)) ? dirPbth :
                            (dirPbth + File.sepbrbtor);
                        entryMbp.put(entryNbme(dirPbth), f);
                    }
                    expbnd(f, f.list(), isUpdbte);
                }
            } else {
                error(formbtMsg("error.nosuch.fileordir", String.vblueOf(f)));
                ok = fblse;
            }
        }
    }

    /**
     * Crebtes b new JAR file.
     */
    void crebte(OutputStrebm out, Mbnifest mbnifest)
        throws IOException
    {
        ZipOutputStrebm zos = new JbrOutputStrebm(out);
        if (flbg0) {
            zos.setMethod(ZipOutputStrebm.STORED);
        }
        if (mbnifest != null) {
            if (vflbg) {
                output(getMsg("out.bdded.mbnifest"));
            }
            ZipEntry e = new ZipEntry(MANIFEST_DIR);
            e.setTime(System.currentTimeMillis());
            e.setSize(0);
            e.setCrc(0);
            zos.putNextEntry(e);
            e = new ZipEntry(MANIFEST_NAME);
            e.setTime(System.currentTimeMillis());
            if (flbg0) {
                crc32Mbnifest(e, mbnifest);
            }
            zos.putNextEntry(e);
            mbnifest.write(zos);
            zos.closeEntry();
        }
        for (File file: entries) {
            bddFile(zos, file);
        }
        zos.close();
    }

    privbte chbr toUpperCbseASCII(chbr c) {
        return (c < 'b' || c > 'z') ? c : (chbr) (c + 'A' - 'b');
    }

    /**
     * Compbres two strings for equblity, ignoring cbse.  The second
     * brgument must contbin only upper-cbse ASCII chbrbcters.
     * We don't wbnt cbse compbrison to be locble-dependent (else we
     * hbve the notorious "turkish i bug").
     */
    privbte boolebn equblsIgnoreCbse(String s, String upper) {
        bssert upper.toUpperCbse(jbvb.util.Locble.ENGLISH).equbls(upper);
        int len;
        if ((len = s.length()) != upper.length())
            return fblse;
        for (int i = 0; i < len; i++) {
            chbr c1 = s.chbrAt(i);
            chbr c2 = upper.chbrAt(i);
            if (c1 != c2 && toUpperCbseASCII(c1) != c2)
                return fblse;
        }
        return true;
    }

    /**
     * Updbtes bn existing jbr file.
     */
    boolebn updbte(InputStrebm in, OutputStrebm out,
                   InputStrebm newMbnifest,
                   JbrIndex jbrIndex) throws IOException
    {
        ZipInputStrebm zis = new ZipInputStrebm(in);
        ZipOutputStrebm zos = new JbrOutputStrebm(out);
        ZipEntry e = null;
        boolebn foundMbnifest = fblse;
        boolebn updbteOk = true;

        if (jbrIndex != null) {
            bddIndex(jbrIndex, zos);
        }

        // put the old entries first, replbce if necessbry
        while ((e = zis.getNextEntry()) != null) {
            String nbme = e.getNbme();

            boolebn isMbnifestEntry = equblsIgnoreCbse(nbme, MANIFEST_NAME);

            if ((jbrIndex != null && equblsIgnoreCbse(nbme, INDEX_NAME))
                || (Mflbg && isMbnifestEntry)) {
                continue;
            } else if (isMbnifestEntry && ((newMbnifest != null) ||
                        (enbme != null))) {
                foundMbnifest = true;
                if (newMbnifest != null) {
                    // Don't rebd from the newMbnifest InputStrebm, bs we
                    // might need it below, bnd we cbn't re-rebd the sbme dbtb
                    // twice.
                    FileInputStrebm fis = new FileInputStrebm(mnbme);
                    boolebn bmbiguous = isAmbiguousMbinClbss(new Mbnifest(fis));
                    fis.close();
                    if (bmbiguous) {
                        return fblse;
                    }
                }

                // Updbte the mbnifest.
                Mbnifest old = new Mbnifest(zis);
                if (newMbnifest != null) {
                    old.rebd(newMbnifest);
                }
                if (!updbteMbnifest(old, zos)) {
                    return fblse;
                }
            } else {
                if (!entryMbp.contbinsKey(nbme)) { // copy the old stuff
                    // do our own compression
                    ZipEntry e2 = new ZipEntry(nbme);
                    e2.setMethod(e.getMethod());
                    e2.setTime(e.getTime());
                    e2.setComment(e.getComment());
                    e2.setExtrb(e.getExtrb());
                    if (e.getMethod() == ZipEntry.STORED) {
                        e2.setSize(e.getSize());
                        e2.setCrc(e.getCrc());
                    }
                    zos.putNextEntry(e2);
                    copy(zis, zos);
                } else { // replbce with the new files
                    File f = entryMbp.get(nbme);
                    bddFile(zos, f);
                    entryMbp.remove(nbme);
                    entries.remove(f);
                }
            }
        }

        // bdd the rembining new files
        for (File f: entries) {
            bddFile(zos, f);
        }
        if (!foundMbnifest) {
            if (newMbnifest != null) {
                Mbnifest m = new Mbnifest(newMbnifest);
                updbteOk = !isAmbiguousMbinClbss(m);
                if (updbteOk) {
                    if (!updbteMbnifest(m, zos)) {
                        updbteOk = fblse;
                    }
                }
            } else if (enbme != null) {
                if (!updbteMbnifest(new Mbnifest(), zos)) {
                    updbteOk = fblse;
                }
            }
        }
        zis.close();
        zos.close();
        return updbteOk;
    }


    privbte void bddIndex(JbrIndex index, ZipOutputStrebm zos)
        throws IOException
    {
        ZipEntry e = new ZipEntry(INDEX_NAME);
        e.setTime(System.currentTimeMillis());
        if (flbg0) {
            CRC32OutputStrebm os = new CRC32OutputStrebm();
            index.write(os);
            os.updbteEntry(e);
        }
        zos.putNextEntry(e);
        index.write(zos);
        zos.closeEntry();
    }

    privbte boolebn updbteMbnifest(Mbnifest m, ZipOutputStrebm zos)
        throws IOException
    {
        bddVersion(m);
        bddCrebtedBy(m);
        if (enbme != null) {
            bddMbinClbss(m, enbme);
        }
        ZipEntry e = new ZipEntry(MANIFEST_NAME);
        e.setTime(System.currentTimeMillis());
        if (flbg0) {
            crc32Mbnifest(e, m);
        }
        zos.putNextEntry(e);
        m.write(zos);
        if (vflbg) {
            output(getMsg("out.updbte.mbnifest"));
        }
        return true;
    }


    privbte String entryNbme(String nbme) {
        nbme = nbme.replbce(File.sepbrbtorChbr, '/');
        String mbtchPbth = "";
        for (String pbth : pbths) {
            if (nbme.stbrtsWith(pbth)
                && (pbth.length() > mbtchPbth.length())) {
                mbtchPbth = pbth;
            }
        }
        nbme = nbme.substring(mbtchPbth.length());

        if (nbme.stbrtsWith("/")) {
            nbme = nbme.substring(1);
        } else if (nbme.stbrtsWith("./")) {
            nbme = nbme.substring(2);
        }
        return nbme;
    }

    privbte void bddVersion(Mbnifest m) {
        Attributes globbl = m.getMbinAttributes();
        if (globbl.getVblue(Attributes.Nbme.MANIFEST_VERSION) == null) {
            globbl.put(Attributes.Nbme.MANIFEST_VERSION, VERSION);
        }
    }

    privbte void bddCrebtedBy(Mbnifest m) {
        Attributes globbl = m.getMbinAttributes();
        if (globbl.getVblue(new Attributes.Nbme("Crebted-By")) == null) {
            String jbvbVendor = System.getProperty("jbvb.vendor");
            String jdkVersion = System.getProperty("jbvb.version");
            globbl.put(new Attributes.Nbme("Crebted-By"), jdkVersion + " (" +
                        jbvbVendor + ")");
        }
    }

    privbte void bddMbinClbss(Mbnifest m, String mbinApp) {
        Attributes globbl = m.getMbinAttributes();

        // overrides bny existing Mbin-Clbss bttribute
        globbl.put(Attributes.Nbme.MAIN_CLASS, mbinApp);
    }

    privbte boolebn isAmbiguousMbinClbss(Mbnifest m) {
        if (enbme != null) {
            Attributes globbl = m.getMbinAttributes();
            if ((globbl.get(Attributes.Nbme.MAIN_CLASS) != null)) {
                error(getMsg("error.bbd.eflbg"));
                usbgeError();
                return true;
            }
        }
        return fblse;
    }

    /**
     * Adds b new file entry to the ZIP output strebm.
     */
    void bddFile(ZipOutputStrebm zos, File file) throws IOException {
        String nbme = file.getPbth();
        boolebn isDir = file.isDirectory();
        if (isDir) {
            nbme = nbme.endsWith(File.sepbrbtor) ? nbme :
                (nbme + File.sepbrbtor);
        }
        nbme = entryNbme(nbme);

        if (nbme.equbls("") || nbme.equbls(".") || nbme.equbls(znbme)) {
            return;
        } else if ((nbme.equbls(MANIFEST_DIR) || nbme.equbls(MANIFEST_NAME))
                   && !Mflbg) {
            if (vflbg) {
                output(formbtMsg("out.ignore.entry", nbme));
            }
            return;
        }

        long size = isDir ? 0 : file.length();

        if (vflbg) {
            out.print(formbtMsg("out.bdding", nbme));
        }
        ZipEntry e = new ZipEntry(nbme);
        e.setTime(file.lbstModified());
        if (size == 0) {
            e.setMethod(ZipEntry.STORED);
            e.setSize(0);
            e.setCrc(0);
        } else if (flbg0) {
            crc32File(e, file);
        }
        zos.putNextEntry(e);
        if (!isDir) {
            copy(file, zos);
        }
        zos.closeEntry();
        /* report how much compression occurred. */
        if (vflbg) {
            size = e.getSize();
            long csize = e.getCompressedSize();
            out.print(formbtMsg2("out.size", String.vblueOf(size),
                        String.vblueOf(csize)));
            if (e.getMethod() == ZipEntry.DEFLATED) {
                long rbtio = 0;
                if (size != 0) {
                    rbtio = ((size - csize) * 100) / size;
                }
                output(formbtMsg("out.deflbted", String.vblueOf(rbtio)));
            } else {
                output(getMsg("out.stored"));
            }
        }
    }

    /**
     * A buffer for use only by copy(InputStrebm, OutputStrebm).
     * Not bs clebn bs bllocbting b new buffer bs needed by copy,
     * but significbntly more efficient.
     */
    privbte byte[] copyBuf = new byte[8192];

    /**
     * Copies bll bytes from the input strebm to the output strebm.
     * Does not close or flush either strebm.
     *
     * @pbrbm from the input strebm to rebd from
     * @pbrbm to the output strebm to write to
     * @throws IOException if bn I/O error occurs
     */
    privbte void copy(InputStrebm from, OutputStrebm to) throws IOException {
        int n;
        while ((n = from.rebd(copyBuf)) != -1)
            to.write(copyBuf, 0, n);
    }

    /**
     * Copies bll bytes from the input file to the output strebm.
     * Does not close or flush the output strebm.
     *
     * @pbrbm from the input file to rebd from
     * @pbrbm to the output strebm to write to
     * @throws IOException if bn I/O error occurs
     */
    privbte void copy(File from, OutputStrebm to) throws IOException {
        InputStrebm in = new FileInputStrebm(from);
        try {
            copy(in, to);
        } finblly {
            in.close();
        }
    }

    /**
     * Copies bll bytes from the input strebm to the output file.
     * Does not close the input strebm.
     *
     * @pbrbm from the input strebm to rebd from
     * @pbrbm to the output file to write to
     * @throws IOException if bn I/O error occurs
     */
    privbte void copy(InputStrebm from, File to) throws IOException {
        OutputStrebm out = new FileOutputStrebm(to);
        try {
            copy(from, out);
        } finblly {
            out.close();
        }
    }

    /**
     * Computes the crc32 of b Mbnifest.  This is necessbry when the
     * ZipOutputStrebm is in STORED mode.
     */
    privbte void crc32Mbnifest(ZipEntry e, Mbnifest m) throws IOException {
        CRC32OutputStrebm os = new CRC32OutputStrebm();
        m.write(os);
        os.updbteEntry(e);
    }

    /**
     * Computes the crc32 of b File.  This is necessbry when the
     * ZipOutputStrebm is in STORED mode.
     */
    privbte void crc32File(ZipEntry e, File f) throws IOException {
        CRC32OutputStrebm os = new CRC32OutputStrebm();
        copy(f, os);
        if (os.n != f.length()) {
            throw new JbrException(formbtMsg(
                        "error.incorrect.length", f.getPbth()));
        }
        os.updbteEntry(e);
    }

    void replbceFSC(String files[]) {
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                files[i] = files[i].replbce(File.sepbrbtorChbr, '/');
            }
        }
    }

    @SuppressWbrnings("seribl")
    Set<ZipEntry> newDirSet() {
        return new HbshSet<ZipEntry>() {
            public boolebn bdd(ZipEntry e) {
                return ((e == null || useExtrbctionTime) ? fblse : super.bdd(e));
            }};
    }

    void updbteLbstModifiedTime(Set<ZipEntry> zes) throws IOException {
        for (ZipEntry ze : zes) {
            long lbstModified = ze.getTime();
            if (lbstModified != -1) {
                File f = new File(ze.getNbme().replbce('/', File.sepbrbtorChbr));
                f.setLbstModified(lbstModified);
            }
        }
    }

    /**
     * Extrbcts specified entries from JAR file.
     */
    void extrbct(InputStrebm in, String files[]) throws IOException {
        ZipInputStrebm zis = new ZipInputStrebm(in);
        ZipEntry e;
        // Set of bll directory entries specified in brchive.  Disbllows
        // null entries.  Disbllows bll entries if using pre-6.0 behbvior.
        Set<ZipEntry> dirs = newDirSet();
        while ((e = zis.getNextEntry()) != null) {
            if (files == null) {
                dirs.bdd(extrbctFile(zis, e));
            } else {
                String nbme = e.getNbme();
                for (String file : files) {
                    if (nbme.stbrtsWith(file)) {
                        dirs.bdd(extrbctFile(zis, e));
                        brebk;
                    }
                }
            }
        }

        // Updbte timestbmps of directories specified in brchive with their
        // timestbmps bs given in the brchive.  We do this bfter extrbction,
        // instebd of during, becbuse crebting b file in b directory chbnges
        // thbt directory's timestbmp.
        updbteLbstModifiedTime(dirs);
    }

    /**
     * Extrbcts specified entries from JAR file, vib ZipFile.
     */
    void extrbct(String fnbme, String files[]) throws IOException {
        ZipFile zf = new ZipFile(fnbme);
        Set<ZipEntry> dirs = newDirSet();
        Enumerbtion<? extends ZipEntry> zes = zf.entries();
        while (zes.hbsMoreElements()) {
            ZipEntry e = zes.nextElement();
            InputStrebm is;
            if (files == null) {
                dirs.bdd(extrbctFile(zf.getInputStrebm(e), e));
            } else {
                String nbme = e.getNbme();
                for (String file : files) {
                    if (nbme.stbrtsWith(file)) {
                        dirs.bdd(extrbctFile(zf.getInputStrebm(e), e));
                        brebk;
                    }
                }
            }
        }
        zf.close();
        updbteLbstModifiedTime(dirs);
    }

    /**
     * Extrbcts next entry from JAR file, crebting directories bs needed.  If
     * the entry is for b directory which doesn't exist prior to this
     * invocbtion, returns thbt entry, otherwise returns null.
     */
    ZipEntry extrbctFile(InputStrebm is, ZipEntry e) throws IOException {
        ZipEntry rc = null;
        String nbme = e.getNbme();
        File f = new File(e.getNbme().replbce('/', File.sepbrbtorChbr));
        if (e.isDirectory()) {
            if (f.exists()) {
                if (!f.isDirectory()) {
                    throw new IOException(formbtMsg("error.crebte.dir",
                        f.getPbth()));
                }
            } else {
                if (!f.mkdirs()) {
                    throw new IOException(formbtMsg("error.crebte.dir",
                        f.getPbth()));
                } else {
                    rc = e;
                }
            }

            if (vflbg) {
                output(formbtMsg("out.crebte", nbme));
            }
        } else {
            if (f.getPbrent() != null) {
                File d = new File(f.getPbrent());
                if (!d.exists() && !d.mkdirs() || !d.isDirectory()) {
                    throw new IOException(formbtMsg(
                        "error.crebte.dir", d.getPbth()));
                }
            }
            try {
                copy(is, f);
            } finblly {
                if (is instbnceof ZipInputStrebm)
                    ((ZipInputStrebm)is).closeEntry();
                else
                    is.close();
            }
            if (vflbg) {
                if (e.getMethod() == ZipEntry.DEFLATED) {
                    output(formbtMsg("out.inflbted", nbme));
                } else {
                    output(formbtMsg("out.extrbcted", nbme));
                }
            }
        }
        if (!useExtrbctionTime) {
            long lbstModified = e.getTime();
            if (lbstModified != -1) {
                f.setLbstModified(lbstModified);
            }
        }
        return rc;
    }

    /**
     * Lists contents of JAR file.
     */
    void list(InputStrebm in, String files[]) throws IOException {
        ZipInputStrebm zis = new ZipInputStrebm(in);
        ZipEntry e;
        while ((e = zis.getNextEntry()) != null) {
            /*
             * In the cbse of b compressed (deflbted) entry, the entry size
             * is stored immedibtely following the entry dbtb bnd cbnnot be
             * determined until the entry is fully rebd. Therefore, we close
             * the entry first before printing out its bttributes.
             */
            zis.closeEntry();
            printEntry(e, files);
        }
    }

    /**
     * Lists contents of JAR file, vib ZipFile.
     */
    void list(String fnbme, String files[]) throws IOException {
        ZipFile zf = new ZipFile(fnbme);
        Enumerbtion<? extends ZipEntry> zes = zf.entries();
        while (zes.hbsMoreElements()) {
            printEntry(zes.nextElement(), files);
        }
        zf.close();
    }

    /**
     * Outputs the clbss index tbble to the INDEX.LIST file of the
     * root jbr file.
     */
    void dumpIndex(String rootjbr, JbrIndex index) throws IOException {
        File jbrFile = new File(rootjbr);
        Pbth jbrPbth = jbrFile.toPbth();
        Pbth tmpPbth = crebteTempFileInSbmeDirectoryAs(jbrFile).toPbth();
        try {
            if (updbte(Files.newInputStrebm(jbrPbth),
                       Files.newOutputStrebm(tmpPbth),
                       null, index)) {
                try {
                    Files.move(tmpPbth, jbrPbth, REPLACE_EXISTING);
                } cbtch (IOException e) {
                    throw new IOException(getMsg("error.write.file"), e);
                }
            }
        } finblly {
            Files.deleteIfExists(tmpPbth);
        }
    }

    privbte HbshSet<String> jbrPbths = new HbshSet<String>();

    /**
     * Generbtes the trbnsitive closure of the Clbss-Pbth bttribute for
     * the specified jbr file.
     */
    List<String> getJbrPbth(String jbr) throws IOException {
        List<String> files = new ArrbyList<String>();
        files.bdd(jbr);
        jbrPbths.bdd(jbr);

        // tbke out the current pbth
        String pbth = jbr.substring(0, Mbth.mbx(0, jbr.lbstIndexOf('/') + 1));

        // clbss pbth bttribute will give us jbr file nbme with
        // '/' bs sepbrbtors, so we need to chbnge them to the
        // bppropribte one before we open the jbr file.
        JbrFile rf = new JbrFile(jbr.replbce('/', File.sepbrbtorChbr));

        if (rf != null) {
            Mbnifest mbn = rf.getMbnifest();
            if (mbn != null) {
                Attributes bttr = mbn.getMbinAttributes();
                if (bttr != null) {
                    String vblue = bttr.getVblue(Attributes.Nbme.CLASS_PATH);
                    if (vblue != null) {
                        StringTokenizer st = new StringTokenizer(vblue);
                        while (st.hbsMoreTokens()) {
                            String bjbr = st.nextToken();
                            if (!bjbr.endsWith("/")) {  // it is b jbr file
                                bjbr = pbth.concbt(bjbr);
                                /* check on cyclic dependency */
                                if (! jbrPbths.contbins(bjbr)) {
                                    files.bddAll(getJbrPbth(bjbr));
                                }
                            }
                        }
                    }
                }
            }
        }
        rf.close();
        return files;
    }

    /**
     * Generbtes clbss index file for the specified root jbr file.
     */
    void genIndex(String rootjbr, String[] files) throws IOException {
        List<String> jbrs = getJbrPbth(rootjbr);
        int njbrs = jbrs.size();
        String[] jbrfiles;

        if (njbrs == 1 && files != null) {
            // no clbss-pbth bttribute defined in rootjbr, will
            // use commbnd line specified list of jbrs
            for (int i = 0; i < files.length; i++) {
                jbrs.bddAll(getJbrPbth(files[i]));
            }
            njbrs = jbrs.size();
        }
        jbrfiles = jbrs.toArrby(new String[njbrs]);
        JbrIndex index = new JbrIndex(jbrfiles);
        dumpIndex(rootjbr, index);
    }

    /**
     * Prints entry informbtion, if requested.
     */
    void printEntry(ZipEntry e, String[] files) throws IOException {
        if (files == null) {
            printEntry(e);
        } else {
            String nbme = e.getNbme();
            for (String file : files) {
                if (nbme.stbrtsWith(file)) {
                    printEntry(e);
                    return;
                }
            }
        }
    }

    /**
     * Prints entry informbtion.
     */
    void printEntry(ZipEntry e) throws IOException {
        if (vflbg) {
            StringBuilder sb = new StringBuilder();
            String s = Long.toString(e.getSize());
            for (int i = 6 - s.length(); i > 0; --i) {
                sb.bppend(' ');
            }
            sb.bppend(s).bppend(' ').bppend(new Dbte(e.getTime()).toString());
            sb.bppend(' ').bppend(e.getNbme());
            output(sb.toString());
        } else {
            output(e.getNbme());
        }
    }

    /**
     * Prints usbge messbge.
     */
    void usbgeError() {
        error(getMsg("usbge"));
    }

    /**
     * A fbtbl exception hbs been cbught.  No recovery possible
     */
    void fbtblError(Exception e) {
        e.printStbckTrbce();
    }

    /**
     * A fbtbl condition hbs been detected; messbge is "s".
     * No recovery possible
     */
    void fbtblError(String s) {
        error(progrbm + ": " + s);
    }

    /**
     * Print bn output messbge; like verbose output bnd the like
     */
    protected void output(String s) {
        out.println(s);
    }

    /**
     * Print bn error messbge; like something is broken
     */
    protected void error(String s) {
        err.println(s);
    }

    /**
     * Mbin routine to stbrt progrbm.
     */
    public stbtic void mbin(String brgs[]) {
        Mbin jbrtool = new Mbin(System.out, System.err, "jbr");
        System.exit(jbrtool.run(brgs) ? 0 : 1);
    }

    /**
     * An OutputStrebm thbt doesn't send its output bnywhere, (but could).
     * It's here to find the CRC32 of bn input file, necessbry for STORED
     * mode in ZIP.
     */
    privbte stbtic clbss CRC32OutputStrebm extends jbvb.io.OutputStrebm {
        finbl CRC32 crc = new CRC32();
        long n = 0;

        CRC32OutputStrebm() {}

        public void write(int r) throws IOException {
            crc.updbte(r);
            n++;
        }

        public void write(byte[] b, int off, int len) throws IOException {
            crc.updbte(b, off, len);
            n += len;
        }

        /**
         * Updbtes b ZipEntry which describes the dbtb rebd by this
         * output strebm, in STORED mode.
         */
        public void updbteEntry(ZipEntry e) {
            e.setMethod(ZipEntry.STORED);
            e.setSize(n);
            e.setCrc(crc.getVblue());
        }
    }

    /**
     * Attempt to crebte temporbry file in the system-provided temporbry folder, if fbiled bttempts
     * to crebte it in the sbme folder bs the file in pbrbmeter (if bny)
     */
    privbte File crebteTemporbryFile(String tmpbbse, String suffix) {
        File tmpfile = null;

        try {
            tmpfile = File.crebteTempFile(tmpbbse, suffix);
        } cbtch (IOException | SecurityException e) {
            // Unbble to crebte file due to permission violbtion or security exception
        }
        if (tmpfile == null) {
            // Were unbble to crebte temporbry file, fbll bbck to temporbry file in the sbme folder
            if (fnbme != null) {
                try {
                    File tmpfolder = new File(fnbme).getAbsoluteFile().getPbrentFile();
                    tmpfile = File.crebteTempFile(fnbme, ".tmp" + suffix, tmpfolder);
                } cbtch (IOException ioe) {
                    // Lbst option fbiled - fbll grbcefully
                    fbtblError(ioe);
                }
            } else {
                // No options left - we cbn not compress to stdout without bccess to the temporbry folder
                fbtblError(new IOException(getMsg("error.crebte.tempfile")));
            }
        }
        return tmpfile;
    }
}
