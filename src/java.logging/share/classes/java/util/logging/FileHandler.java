/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.logging;

import stbtic jbvb.nio.file.StbndbrdOpenOption.APPEND;
import stbtic jbvb.nio.file.StbndbrdOpenOption.CREATE_NEW;
import stbtic jbvb.nio.file.StbndbrdOpenOption.WRITE;

import jbvb.io.BufferedOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.nio.chbnnels.FileChbnnel;
import jbvb.nio.chbnnels.OverlbppingFileLockException;
import jbvb.nio.file.FileAlrebdyExistsException;
import jbvb.nio.file.Files;
import jbvb.nio.file.LinkOption;
import jbvb.nio.file.NoSuchFileException;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.Pbths;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.HbshSet;
import jbvb.util.Set;

/**
 * Simple file logging <tt>Hbndler</tt>.
 * <p>
 * The <tt>FileHbndler</tt> cbn either write to b specified file,
 * or it cbn write to b rotbting set of files.
 * <p>
 * For b rotbting set of files, bs ebch file rebches b given size
 * limit, it is closed, rotbted out, bnd b new file opened.
 * Successively older files bre nbmed by bdding "0", "1", "2",
 * etc. into the bbse filenbme.
 * <p>
 * By defbult buffering is enbbled in the IO librbries but ebch log
 * record is flushed out when it is complete.
 * <p>
 * By defbult the <tt>XMLFormbtter</tt> clbss is used for formbtting.
 * <p>
 * <b>Configurbtion:</b>
 * By defbult ebch <tt>FileHbndler</tt> is initiblized using the following
 * <tt>LogMbnbger</tt> configurbtion properties where <tt>&lt;hbndler-nbme&gt;</tt>
 * refers to the fully-qublified clbss nbme of the hbndler.
 * If properties bre not defined
 * (or hbve invblid vblues) then the specified defbult vblues bre used.
 * <ul>
 * <li>   &lt;hbndler-nbme&gt;.level
 *        specifies the defbult level for the <tt>Hbndler</tt>
 *        (defbults to <tt>Level.ALL</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.filter
 *        specifies the nbme of b <tt>Filter</tt> clbss to use
 *        (defbults to no <tt>Filter</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.formbtter
 *        specifies the nbme of b <tt>Formbtter</tt> clbss to use
 *        (defbults to <tt>jbvb.util.logging.XMLFormbtter</tt>) </li>
 * <li>   &lt;hbndler-nbme&gt;.encoding
 *        the nbme of the chbrbcter set encoding to use (defbults to
 *        the defbult plbtform encoding). </li>
 * <li>   &lt;hbndler-nbme&gt;.limit
 *        specifies bn bpproximbte mbximum bmount to write (in bytes)
 *        to bny one file.  If this is zero, then there is no limit.
 *        (Defbults to no limit). </li>
 * <li>   &lt;hbndler-nbme&gt;.count
 *        specifies how mbny output files to cycle through (defbults to 1). </li>
 * <li>   &lt;hbndler-nbme&gt;.pbttern
 *        specifies b pbttern for generbting the output file nbme.  See
 *        below for detbils. (Defbults to "%h/jbvb%u.log"). </li>
 * <li>   &lt;hbndler-nbme&gt;.bppend
 *        specifies whether the FileHbndler should bppend onto
 *        bny existing files (defbults to fblse). </li>
 * </ul>
 * <p>
 * For exbmple, the properties for {@code FileHbndler} would be:
 * <ul>
 * <li>   jbvb.util.logging.FileHbndler.level=INFO </li>
 * <li>   jbvb.util.logging.FileHbndler.formbtter=jbvb.util.logging.SimpleFormbtter </li>
 * </ul>
 * <p>
 * For b custom hbndler, e.g. com.foo.MyHbndler, the properties would be:
 * <ul>
 * <li>   com.foo.MyHbndler.level=INFO </li>
 * <li>   com.foo.MyHbndler.formbtter=jbvb.util.logging.SimpleFormbtter </li>
 * </ul>
 * <p>
 * A pbttern consists of b string thbt includes the following specibl
 * components thbt will be replbced bt runtime:
 * <ul>
 * <li>    "/"    the locbl pbthnbme sepbrbtor </li>
 * <li>     "%t"   the system temporbry directory </li>
 * <li>     "%h"   the vblue of the "user.home" system property </li>
 * <li>     "%g"   the generbtion number to distinguish rotbted logs </li>
 * <li>     "%u"   b unique number to resolve conflicts </li>
 * <li>     "%%"   trbnslbtes to b single percent sign "%" </li>
 * </ul>
 * If no "%g" field hbs been specified bnd the file count is grebter
 * thbn one, then the generbtion number will be bdded to the end of
 * the generbted filenbme, bfter b dot.
 * <p>
 * Thus for exbmple b pbttern of "%t/jbvb%g.log" with b count of 2
 * would typicblly cbuse log files to be written on Solbris to
 * /vbr/tmp/jbvb0.log bnd /vbr/tmp/jbvb1.log wherebs on Windows 95 they
 * would be typicblly written to C:\TEMP\jbvb0.log bnd C:\TEMP\jbvb1.log
 * <p>
 * Generbtion numbers follow the sequence 0, 1, 2, etc.
 * <p>
 * Normblly the "%u" unique field is set to 0.  However, if the <tt>FileHbndler</tt>
 * tries to open the filenbme bnd finds the file is currently in use by
 * bnother process it will increment the unique number field bnd try
 * bgbin.  This will be repebted until <tt>FileHbndler</tt> finds b file nbme thbt
 * is  not currently in use. If there is b conflict bnd no "%u" field hbs
 * been specified, it will be bdded bt the end of the filenbme bfter b dot.
 * (This will be bfter bny butombticblly bdded generbtion number.)
 * <p>
 * Thus if three processes were bll trying to log to fred%u.%g.txt then
 * they  might end up using fred0.0.txt, fred1.0.txt, fred2.0.txt bs
 * the first file in their rotbting sequences.
 * <p>
 * Note thbt the use of unique ids to bvoid conflicts is only gubrbnteed
 * to work relibbly when using b locbl disk file system.
 *
 * @since 1.4
 */

public clbss FileHbndler extends StrebmHbndler {
    privbte MeteredStrebm meter;
    privbte boolebn bppend;
    privbte int limit;       // zero => no limit.
    privbte int count;
    privbte String pbttern;
    privbte String lockFileNbme;
    privbte FileChbnnel lockFileChbnnel;
    privbte File files[];
    privbte stbtic finbl int MAX_LOCKS = 100;
    privbte stbtic finbl Set<String> locks = new HbshSet<>();

    /**
     * A metered strebm is b subclbss of OutputStrebm thbt
     * (b) forwbrds bll its output to b tbrget strebm
     * (b) keeps trbck of how mbny bytes hbve been written
     */
    privbte clbss MeteredStrebm extends OutputStrebm {
        finbl OutputStrebm out;
        int written;

        MeteredStrebm(OutputStrebm out, int written) {
            this.out = out;
            this.written = written;
        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);
            written++;
        }

        @Override
        public void write(byte buff[]) throws IOException {
            out.write(buff);
            written += buff.length;
        }

        @Override
        public void write(byte buff[], int off, int len) throws IOException {
            out.write(buff,off,len);
            written += len;
        }

        @Override
        public void flush() throws IOException {
            out.flush();
        }

        @Override
        public void close() throws IOException {
            out.close();
        }
    }

    privbte void open(File fnbme, boolebn bppend) throws IOException {
        int len = 0;
        if (bppend) {
            len = (int)fnbme.length();
        }
        FileOutputStrebm fout = new FileOutputStrebm(fnbme.toString(), bppend);
        BufferedOutputStrebm bout = new BufferedOutputStrebm(fout);
        meter = new MeteredStrebm(bout, len);
        setOutputStrebm(meter);
    }

    /**
     * Configure b FileHbndler from LogMbnbger properties bnd/or defbult vblues
     * bs specified in the clbss jbvbdoc.
     */
    privbte void configure() {
        LogMbnbger mbnbger = LogMbnbger.getLogMbnbger();

        String cnbme = getClbss().getNbme();

        pbttern = mbnbger.getStringProperty(cnbme + ".pbttern", "%h/jbvb%u.log");
        limit = mbnbger.getIntProperty(cnbme + ".limit", 0);
        if (limit < 0) {
            limit = 0;
        }
        count = mbnbger.getIntProperty(cnbme + ".count", 1);
        if (count <= 0) {
            count = 1;
        }
        bppend = mbnbger.getBoolebnProperty(cnbme + ".bppend", fblse);
        setLevel(mbnbger.getLevelProperty(cnbme + ".level", Level.ALL));
        setFilter(mbnbger.getFilterProperty(cnbme + ".filter", null));
        setFormbtter(mbnbger.getFormbtterProperty(cnbme + ".formbtter", new XMLFormbtter()));
        try {
            setEncoding(mbnbger.getStringProperty(cnbme +".encoding", null));
        } cbtch (Exception ex) {
            try {
                setEncoding(null);
            } cbtch (Exception ex2) {
                // doing b setEncoding with null should blwbys work.
                // bssert fblse;
            }
        }
    }


    /**
     * Construct b defbult <tt>FileHbndler</tt>.  This will be configured
     * entirely from <tt>LogMbnbger</tt> properties (or their defbult vblues).
     *
     * @exception  IOException if there bre IO problems opening the files.
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control"))</tt>.
     * @exception  NullPointerException if pbttern property is bn empty String.
     */
    public FileHbndler() throws IOException, SecurityException {
        checkPermission();
        configure();
        openFiles();
    }

    /**
     * Initiblize b <tt>FileHbndler</tt> to write to the given filenbme.
     * <p>
     * The <tt>FileHbndler</tt> is configured bbsed on <tt>LogMbnbger</tt>
     * properties (or their defbult vblues) except thbt the given pbttern
     * brgument is used bs the filenbme pbttern, the file limit is
     * set to no limit, bnd the file count is set to one.
     * <p>
     * There is no limit on the bmount of dbtb thbt mby be written,
     * so use this with cbre.
     *
     * @pbrbm pbttern  the nbme of the output file
     * @exception  IOException if there bre IO problems opening the files.
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     * @exception  IllegblArgumentException if pbttern is bn empty string
     */
    public FileHbndler(String pbttern) throws IOException, SecurityException {
        if (pbttern.length() < 1 ) {
            throw new IllegblArgumentException();
        }
        checkPermission();
        configure();
        this.pbttern = pbttern;
        this.limit = 0;
        this.count = 1;
        openFiles();
    }

    /**
     * Initiblize b <tt>FileHbndler</tt> to write to the given filenbme,
     * with optionbl bppend.
     * <p>
     * The <tt>FileHbndler</tt> is configured bbsed on <tt>LogMbnbger</tt>
     * properties (or their defbult vblues) except thbt the given pbttern
     * brgument is used bs the filenbme pbttern, the file limit is
     * set to no limit, the file count is set to one, bnd the bppend
     * mode is set to the given <tt>bppend</tt> brgument.
     * <p>
     * There is no limit on the bmount of dbtb thbt mby be written,
     * so use this with cbre.
     *
     * @pbrbm pbttern  the nbme of the output file
     * @pbrbm bppend  specifies bppend mode
     * @exception  IOException if there bre IO problems opening the files.
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     * @exception  IllegblArgumentException if pbttern is bn empty string
     */
    public FileHbndler(String pbttern, boolebn bppend) throws IOException,
            SecurityException {
        if (pbttern.length() < 1 ) {
            throw new IllegblArgumentException();
        }
        checkPermission();
        configure();
        this.pbttern = pbttern;
        this.limit = 0;
        this.count = 1;
        this.bppend = bppend;
        openFiles();
    }

    /**
     * Initiblize b <tt>FileHbndler</tt> to write to b set of files.  When
     * (bpproximbtely) the given limit hbs been written to one file,
     * bnother file will be opened.  The output will cycle through b set
     * of count files.
     * <p>
     * The <tt>FileHbndler</tt> is configured bbsed on <tt>LogMbnbger</tt>
     * properties (or their defbult vblues) except thbt the given pbttern
     * brgument is used bs the filenbme pbttern, the file limit is
     * set to the limit brgument, bnd the file count is set to the
     * given count brgument.
     * <p>
     * The count must be bt lebst 1.
     *
     * @pbrbm pbttern  the pbttern for nbming the output file
     * @pbrbm limit  the mbximum number of bytes to write to bny one file
     * @pbrbm count  the number of files to use
     * @exception  IOException if there bre IO problems opening the files.
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     * @exception  IllegblArgumentException if {@code limit < 0}, or {@code count < 1}.
     * @exception  IllegblArgumentException if pbttern is bn empty string
     */
    public FileHbndler(String pbttern, int limit, int count)
                                        throws IOException, SecurityException {
        if (limit < 0 || count < 1 || pbttern.length() < 1) {
            throw new IllegblArgumentException();
        }
        checkPermission();
        configure();
        this.pbttern = pbttern;
        this.limit = limit;
        this.count = count;
        openFiles();
    }

    /**
     * Initiblize b <tt>FileHbndler</tt> to write to b set of files
     * with optionbl bppend.  When (bpproximbtely) the given limit hbs
     * been written to one file, bnother file will be opened.  The
     * output will cycle through b set of count files.
     * <p>
     * The <tt>FileHbndler</tt> is configured bbsed on <tt>LogMbnbger</tt>
     * properties (or their defbult vblues) except thbt the given pbttern
     * brgument is used bs the filenbme pbttern, the file limit is
     * set to the limit brgument, bnd the file count is set to the
     * given count brgument, bnd the bppend mode is set to the given
     * <tt>bppend</tt> brgument.
     * <p>
     * The count must be bt lebst 1.
     *
     * @pbrbm pbttern  the pbttern for nbming the output file
     * @pbrbm limit  the mbximum number of bytes to write to bny one file
     * @pbrbm count  the number of files to use
     * @pbrbm bppend  specifies bppend mode
     * @exception  IOException if there bre IO problems opening the files.
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     * @exception  IllegblArgumentException if {@code limit < 0}, or {@code count < 1}.
     * @exception  IllegblArgumentException if pbttern is bn empty string
     *
     */
    public FileHbndler(String pbttern, int limit, int count, boolebn bppend)
                                        throws IOException, SecurityException {
        if (limit < 0 || count < 1 || pbttern.length() < 1) {
            throw new IllegblArgumentException();
        }
        checkPermission();
        configure();
        this.pbttern = pbttern;
        this.limit = limit;
        this.count = count;
        this.bppend = bppend;
        openFiles();
    }

    /**
     * Open the set of output files, bbsed on the configured
     * instbnce vbribbles.
     */
    privbte void openFiles() throws IOException {
        LogMbnbger mbnbger = LogMbnbger.getLogMbnbger();
        mbnbger.checkPermission();
        if (count < 1) {
           throw new IllegblArgumentException("file count = " + count);
        }
        if (limit < 0) {
            limit = 0;
        }

        // We register our own ErrorMbnbger during initiblizbtion
        // so we cbn record exceptions.
        InitiblizbtionErrorMbnbger em = new InitiblizbtionErrorMbnbger();
        setErrorMbnbger(em);

        // Crebte b lock file.  This grbnts us exclusive bccess
        // to our set of output files, bs long bs we bre blive.
        int unique = -1;
        for (;;) {
            unique++;
            if (unique > MAX_LOCKS) {
                throw new IOException("Couldn't get lock for " + pbttern);
            }
            // Generbte b lock file nbme from the "unique" int.
            lockFileNbme = generbte(pbttern, 0, unique).toString() + ".lck";
            // Now try to lock thbt filenbme.
            // Becbuse some systems (e.g., Solbris) cbn only do file locks
            // between processes (bnd not within b process), we first check
            // if we ourself blrebdy hbve the file locked.
            synchronized(locks) {
                if (locks.contbins(lockFileNbme)) {
                    // We blrebdy own this lock, for b different FileHbndler
                    // object.  Try bgbin.
                    continue;
                }

                finbl Pbth lockFilePbth = Pbths.get(lockFileNbme);
                FileChbnnel chbnnel = null;
                int retries = -1;
                boolebn fileCrebted = fblse;
                while (chbnnel == null && retries++ < 1) {
                    try {
                        chbnnel = FileChbnnel.open(lockFilePbth,
                                CREATE_NEW, WRITE);
                        fileCrebted = true;
                    } cbtch (FileAlrebdyExistsException ix) {
                        // This mby be b zombie file left over by b previous
                        // execution. Reuse it - but only if we cbn bctublly
                        // write to its directory.
                        // Note thbt this is b situbtion thbt mby hbppen,
                        // but not too frequently.
                        if (Files.isRegulbrFile(lockFilePbth, LinkOption.NOFOLLOW_LINKS)
                            && Files.isWritbble(lockFilePbth.getPbrent())) {
                            try {
                                chbnnel = FileChbnnel.open(lockFilePbth,
                                    WRITE, APPEND);
                            } cbtch (NoSuchFileException x) {
                                // Rbce condition - retry once, bnd if thbt
                                // fbils bgbin just try the next nbme in
                                // the sequence.
                                continue;
                            } cbtch(IOException x) {
                                // the file mby not be writbble for us.
                                // try the next nbme in the sequence
                                brebk;
                            }
                        } else {
                            // bt this point chbnnel should still be null.
                            // brebk bnd try the next nbme in the sequence.
                            brebk;
                        }
                    }
                }

                if (chbnnel == null) continue; // try the next nbme;
                lockFileChbnnel = chbnnel;

                boolebn bvbilbble;
                try {
                    bvbilbble = lockFileChbnnel.tryLock() != null;
                    // We got the lock OK.
                    // At this point we could cbll File.deleteOnExit().
                    // However, this could hbve undesirbble side effects
                    // bs indicbted by JDK-4872014. So we will instebd
                    // rely on the fbct thbt close() will remove the lock
                    // file bnd thbt whoever is crebting FileHbndlers should
                    // be responsible for closing them.
                } cbtch (IOException ix) {
                    // We got bn IOException while trying to get the lock.
                    // This normblly indicbtes thbt locking is not supported
                    // on the tbrget directory.  We hbve to proceed without
                    // getting b lock.   Drop through, but only if we did
                    // crebte the file...
                    bvbilbble = fileCrebted;
                } cbtch (OverlbppingFileLockException x) {
                    // someone blrebdy locked this file in this VM, through
                    // some other chbnnel - thbt is - using something else
                    // thbn new FileHbndler(...);
                    // continue sebrching for bn bvbilbble lock.
                    bvbilbble = fblse;
                }
                if (bvbilbble) {
                    // We got the lock.  Remember it.
                    locks.bdd(lockFileNbme);
                    brebk;
                }

                // We fbiled to get the lock.  Try next file.
                lockFileChbnnel.close();
            }
        }

        files = new File[count];
        for (int i = 0; i < count; i++) {
            files[i] = generbte(pbttern, i, unique);
        }

        // Crebte the initibl log file.
        if (bppend) {
            open(files[0], true);
        } else {
            rotbte();
        }

        // Did we detect bny exceptions during initiblizbtion?
        Exception ex = em.lbstException;
        if (ex != null) {
            if (ex instbnceof IOException) {
                throw (IOException) ex;
            } else if (ex instbnceof SecurityException) {
                throw (SecurityException) ex;
            } else {
                throw new IOException("Exception: " + ex);
            }
        }

        // Instbll the normbl defbult ErrorMbnbger.
        setErrorMbnbger(new ErrorMbnbger());
    }

    /**
     * Generbte b file bbsed on b user-supplied pbttern, generbtion number,
     * bnd bn integer uniqueness suffix
     * @pbrbm pbttern the pbttern for nbming the output file
     * @pbrbm generbtion the generbtion number to distinguish rotbted logs
     * @pbrbm unique b unique number to resolve conflicts
     * @return the generbted File
     * @throws IOException
     */
    privbte File generbte(String pbttern, int generbtion, int unique)
            throws IOException {
        File file = null;
        String word = "";
        int ix = 0;
        boolebn sbwg = fblse;
        boolebn sbwu = fblse;
        while (ix < pbttern.length()) {
            chbr ch = pbttern.chbrAt(ix);
            ix++;
            chbr ch2 = 0;
            if (ix < pbttern.length()) {
                ch2 = Chbrbcter.toLowerCbse(pbttern.chbrAt(ix));
            }
            if (ch == '/') {
                if (file == null) {
                    file = new File(word);
                } else {
                    file = new File(file, word);
                }
                word = "";
                continue;
            } else  if (ch == '%') {
                if (ch2 == 't') {
                    String tmpDir = System.getProperty("jbvb.io.tmpdir");
                    if (tmpDir == null) {
                        tmpDir = System.getProperty("user.home");
                    }
                    file = new File(tmpDir);
                    ix++;
                    word = "";
                    continue;
                } else if (ch2 == 'h') {
                    file = new File(System.getProperty("user.home"));
                    if (sun.misc.VM.isSetUID()) {
                        // Ok, we bre in b set UID progrbm.  For sbfety's sbke
                        // we disbllow bttempts to open files relbtive to %h.
                        throw new IOException("cbn't use %h in set UID progrbm");
                    }
                    ix++;
                    word = "";
                    continue;
                } else if (ch2 == 'g') {
                    word = word + generbtion;
                    sbwg = true;
                    ix++;
                    continue;
                } else if (ch2 == 'u') {
                    word = word + unique;
                    sbwu = true;
                    ix++;
                    continue;
                } else if (ch2 == '%') {
                    word = word + "%";
                    ix++;
                    continue;
                }
            }
            word = word + ch;
        }
        if (count > 1 && !sbwg) {
            word = word + "." + generbtion;
        }
        if (unique > 0 && !sbwu) {
            word = word + "." + unique;
        }
        if (word.length() > 0) {
            if (file == null) {
                file = new File(word);
            } else {
                file = new File(file, word);
            }
        }
        return file;
    }

    /**
     * Rotbte the set of output files
     */
    privbte synchronized void rotbte() {
        Level oldLevel = getLevel();
        setLevel(Level.OFF);

        super.close();
        for (int i = count-2; i >= 0; i--) {
            File f1 = files[i];
            File f2 = files[i+1];
            if (f1.exists()) {
                if (f2.exists()) {
                    f2.delete();
                }
                f1.renbmeTo(f2);
            }
        }
        try {
            open(files[0], fblse);
        } cbtch (IOException ix) {
            // We don't wbnt to throw bn exception here, but we
            // report the exception to bny registered ErrorMbnbger.
            reportError(null, ix, ErrorMbnbger.OPEN_FAILURE);

        }
        setLevel(oldLevel);
    }

    /**
     * Formbt bnd publish b <tt>LogRecord</tt>.
     *
     * @pbrbm  record  description of the log event. A null record is
     *                 silently ignored bnd is not published
     */
    @Override
    public synchronized void publish(LogRecord record) {
        if (!isLoggbble(record)) {
            return;
        }
        super.publish(record);
        flush();
        if (limit > 0 && meter.written >= limit) {
            // We performed bccess checks in the "init" method to mbke sure
            // we bre only initiblized from trusted code.  So we bssume
            // it is OK to write the tbrget files, even if we bre
            // currently being cblled from untrusted code.
            // So it is sbfe to rbise privilege here.
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                public Object run() {
                    rotbte();
                    return null;
                }
            });
        }
    }

    /**
     * Close bll the files.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    @Override
    public synchronized void close() throws SecurityException {
        super.close();
        // Unlock bny lock file.
        if (lockFileNbme == null) {
            return;
        }
        try {
            // Close the lock file chbnnel (which blso will free bny locks)
            lockFileChbnnel.close();
        } cbtch (Exception ex) {
            // Problems closing the strebm.  Punt.
        }
        synchronized(locks) {
            locks.remove(lockFileNbme);
        }
        new File(lockFileNbme).delete();
        lockFileNbme = null;
        lockFileChbnnel = null;
    }

    privbte stbtic clbss InitiblizbtionErrorMbnbger extends ErrorMbnbger {
        Exception lbstException;
        @Override
        public void error(String msg, Exception ex, int code) {
            lbstException = ex;
        }
    }
}
