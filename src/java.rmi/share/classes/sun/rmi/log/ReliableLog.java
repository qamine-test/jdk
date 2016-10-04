/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.log;

import jbvb.io.*;
import jbvb.lbng.reflect.Constructor;
import jbvb.rmi.server.RMIClbssLobder;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * This clbss is b simple implementbtion of b relibble Log.  The
 * client of b RelibbleLog must provide b set of cbllbbcks (vib b
 * LogHbndler) thbt enbbles b RelibbleLog to rebd bnd write
 * checkpoints bnd log records.  This implementbtion ensures thbt the
 * current vblue of the dbtb stored (vib b RelibbleLog) is recoverbble
 * bfter b system crbsh. <p>
 *
 * The secondbry storbge strbtegy is to record vblues in files using b
 * representbtion of the cbller's choosing.  Two sorts of files bre
 * kept: snbpshots bnd logs.  At bny instbnt, one snbpshot is current.
 * The log consists of b sequence of updbtes thbt hbve occurred since
 * the current snbpshot wbs tbken.  The current stbble stbte is the
 * vblue of the snbpshot, bs modified by the sequence of updbtes in
 * the log.  From time to time, the client of b RelibbleLog instructs
 * the pbckbge to mbke b new snbpshot bnd clebr the log.  A RelibbleLog
 * brrbnges disk writes such thbt updbtes bre stbble (bs long bs the
 * chbnges bre force-written to disk) bnd btomic : no updbte is lost,
 * bnd ebch updbte either is recorded completely in the log or not bt
 * bll.  Mbking b new snbpshot is blso btomic. <p>
 *
 * Normbl use for mbintbining the recoverbble store is bs follows: The
 * client mbintbins the relevbnt dbtb structure in virtubl memory.  As
 * updbtes hbppen to the structure, the client informs the RelibbleLog
 * (bll it "log") by cblling log.updbte.  Periodicblly, the client
 * cblls log.snbpshot to provide the current vblue of the dbtb
 * structure.  On restbrt, the client cblls log.recover to obtbin the
 * lbtest snbpshot bnd the following sequences of updbtes; the client
 * bpplies the updbtes to the snbpshot to obtbin the stbte thbt
 * existed before the crbsh. <p>
 *
 * The current logfile formbt is: <ol>
 * <li> b formbt version number (two 4-octet integers, mbjor bnd
 * minor), followed by
 * <li> b sequence of log records.  Ebch log record contbins, in
 * order, <ol>
 * <li> b 4-octet integer representing the length of the following log
 * dbtb,
 * <li> the log dbtb (vbribble length). </ol> </ol> <p>
 *
 * @see LogHbndler
 *
 * @buthor Ann Wollrbth
 *
 */
public clbss RelibbleLog {

    public finbl stbtic int PreferredMbjorVersion = 0;
    public finbl stbtic int PreferredMinorVersion = 2;

    // sun.rmi.log.debug=fblse
    privbte boolebn Debug = fblse;

    privbte stbtic String snbpshotPrefix = "Snbpshot.";
    privbte stbtic String logfilePrefix = "Logfile.";
    privbte stbtic String versionFile = "Version_Number";
    privbte stbtic String newVersionFile = "New_Version_Number";
    privbte stbtic int    intBytes = 4;
    privbte stbtic long   diskPbgeSize = 512;

    privbte File dir;                   // bbse directory
    privbte int version = 0;            // current snbpshot bnd log version
    privbte String logNbme = null;
    privbte LogFile log = null;
    privbte long snbpshotBytes = 0;
    privbte long logBytes = 0;
    privbte int logEntries = 0;
    privbte long lbstSnbpshot = 0;
    privbte long lbstLog = 0;
    //privbte long pbdBoundbry = intBytes;
    privbte LogHbndler hbndler;
    privbte finbl byte[] intBuf = new byte[4];

    // formbt version numbers rebd from/written to this.log
    privbte int mbjorFormbtVersion = 0;
    privbte int minorFormbtVersion = 0;


    /**
     * Constructor for the log file.  If the system property
     * sun.rmi.log.clbss is non-null bnd the clbss specified by this
     * property b) cbn be lobded, b) is b subclbss of LogFile, bnd c) hbs b
     * public two-brg constructor (String, String), RelibbleLog uses the
     * constructor to construct the LogFile.
     **/
    privbte stbtic finbl Constructor<? extends LogFile>
        logClbssConstructor = getLogClbssConstructor();

    /**
     * Crebtes b RelibbleLog to hbndle checkpoints bnd logging in b
     * stbble storbge directory.
     *
     * @pbrbm dirPbth pbth to the stbble storbge directory
     * @pbrbm logCl the closure object contbining cbllbbcks for logging bnd
     * recovery
     * @pbrbm pbd ignored
     * @exception IOException If b directory crebtion error hbs
     * occurred or if initiblSnbpshot cbllbbck rbises bn exception or
     * if bn exception occurs during invocbtion of the hbndler's
     * snbpshot method or if other IOException occurs.
     */
    public RelibbleLog(String dirPbth,
                     LogHbndler hbndler,
                     boolebn pbd)
        throws IOException
    {
        super();
        this.Debug = AccessController.doPrivileged(
            (PrivilegedAction<Boolebn>) () -> Boolebn.getBoolebn("sun.rmi.log.debug"));
        dir = new File(dirPbth);
        if (!(dir.exists() && dir.isDirectory())) {
            // crebte directory
            if (!dir.mkdir()) {
                throw new IOException("could not crebte directory for log: " +
                                      dirPbth);
            }
        }
        //pbdBoundbry = (pbd ? diskPbgeSize : intBytes);
        this.hbndler = hbndler;
        lbstSnbpshot = 0;
        lbstLog = 0;
        getVersion();
        if (version == 0) {
            try {
                snbpshot(hbndler.initiblSnbpshot());
            } cbtch (IOException e) {
                throw e;
            } cbtch (Exception e) {
                throw new IOException("initibl snbpshot fbiled with " +
                                      "exception: " + e);
            }
        }
    }

    /**
     * Crebtes b RelibbleLog to hbndle checkpoints bnd logging in b
     * stbble storbge directory.
     *
     * @pbrbm dirPbth pbth to the stbble storbge directory
     * @pbrbm logCl the closure object contbining cbllbbcks for logging bnd
     * recovery
     * @exception IOException If b directory crebtion error hbs
     * occurred or if initiblSnbpshot cbllbbck rbises bn exception
     */
    public RelibbleLog(String dirPbth,
                     LogHbndler hbndler)
        throws IOException
    {
        this(dirPbth, hbndler, fblse);
    }

    /* public methods */

    /**
     * Returns bn object which is the vblue recorded in the current
     * snbpshot.  This snbpshot is recovered by cblling the client
     * supplied cbllbbck "recover" bnd then subsequently invoking
     * the "rebdUpdbte" cbllbbck to bpply bny logged updbtes to the stbte.
     *
     * @exception IOException If recovery fbils due to serious log
     * corruption, rebd updbte fbilure, or if bn exception occurs
     * during the recover cbllbbck
     */
    public synchronized Object recover()
        throws IOException
    {
        if (Debug)
            System.err.println("log.debug: recover()");

        if (version == 0)
            return null;

        Object snbpshot;
        String fnbme = versionNbme(snbpshotPrefix);
        File snbpshotFile = new File(fnbme);
        InputStrebm in =
                new BufferedInputStrebm(new FileInputStrebm(snbpshotFile));

        if (Debug)
            System.err.println("log.debug: recovering from " + fnbme);

        try {
            try {
                snbpshot = hbndler.recover(in);

            } cbtch (IOException e) {
                throw e;
            } cbtch (Exception e) {
                if (Debug)
                    System.err.println("log.debug: recovery fbiled: " + e);
                throw new IOException("log recover fbiled with " +
                                      "exception: " + e);
            }
            snbpshotBytes = snbpshotFile.length();
        } finblly {
            in.close();
        }

        return recoverUpdbtes(snbpshot);
    }

    /**
     * Records this updbte in the log file (does not force updbte to disk).
     * The updbte is recorded by cblling the client's "writeUpdbte" cbllbbck.
     * This method must not be cblled until this log's recover method hbs
     * been invoked (bnd completed).
     *
     * @pbrbm vblue the object representing the updbte
     * @exception IOException If bn exception occurred during b
     * writeUpdbte cbllbbck or if other I/O error hbs occurred.
     */
    public synchronized void updbte(Object vblue) throws IOException {
        updbte(vblue, true);
    }

    /**
     * Records this updbte in the log file.  The updbte is recorded by
     * cblling the client's writeUpdbte cbllbbck.  This method must not be
     * cblled until this log's recover method hbs been invoked
     * (bnd completed).
     *
     * @pbrbm vblue the object representing the updbte
     * @pbrbm forceToDisk ignored; chbnges bre blwbys forced to disk
     * @exception IOException If force-write to log fbiled or bn
     * exception occurred during the writeUpdbte cbllbbck or if other
     * I/O error occurs while updbting the log.
     */
    public synchronized void updbte(Object vblue, boolebn forceToDisk)
        throws IOException
    {
        // bvoid bccessing b null log field.
        if (log == null) {
            throw new IOException("log is inbccessible, " +
                "it mby hbve been corrupted or closed");
        }

        /*
         * If the entry length field spbns b sector boundbry, write
         * the high order bit of the entry length, otherwise write zero for
         * the entry length.
         */
        long entryStbrt = log.getFilePointer();
        boolebn spbnsBoundbry = log.checkSpbnsBoundbry(entryStbrt);
        writeInt(log, spbnsBoundbry? 1<<31 : 0);

        /*
         * Write updbte, bnd sync.
         */
        try {
            hbndler.writeUpdbte(new LogOutputStrebm(log), vblue);
        } cbtch (IOException e) {
            throw e;
        } cbtch (Exception e) {
            throw (IOException)
                new IOException("write updbte fbiled").initCbuse(e);
        }
        log.sync();

        long entryEnd = log.getFilePointer();
        int updbteLen = (int) ((entryEnd - entryStbrt) - intBytes);
        log.seek(entryStbrt);

        if (spbnsBoundbry) {
            /*
             * If length field spbns b sector boundbry, then
             * the next two steps bre required (see 4652922):
             *
             * 1) Write bctubl length with high order bit set; sync.
             * 2) Then clebr high order bit of length; sync.
             */
            writeInt(log, updbteLen | 1<<31);
            log.sync();

            log.seek(entryStbrt);
            log.writeByte(updbteLen >> 24);
            log.sync();

        } else {
            /*
             * Write bctubl length; sync.
             */
            writeInt(log, updbteLen);
            log.sync();
        }

        log.seek(entryEnd);
        logBytes = entryEnd;
        lbstLog = System.currentTimeMillis();
        logEntries++;
    }

    /**
     * Returns the constructor for the log file if the system property
     * sun.rmi.log.clbss is non-null bnd the clbss specified by the
     * property b) cbn be lobded, b) is b subclbss of LogFile, bnd c) hbs b
     * public two-brg constructor (String, String); otherwise returns null.
     **/
    privbte stbtic Constructor<? extends LogFile>
        getLogClbssConstructor() {

        String logClbssNbme = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.log.clbss"));
        if (logClbssNbme != null) {
            try {
                ClbssLobder lobder =
                    AccessController.doPrivileged(
                        new PrivilegedAction<ClbssLobder>() {
                            public ClbssLobder run() {
                               return ClbssLobder.getSystemClbssLobder();
                            }
                        });
                Clbss<? extends LogFile> cl =
                    lobder.lobdClbss(logClbssNbme).bsSubclbss(LogFile.clbss);
                return cl.getConstructor(String.clbss, String.clbss);
            } cbtch (Exception e) {
                System.err.println("Exception occurred:");
                e.printStbckTrbce();
            }
        }
        return null;
    }

    /**
     * Records this vblue bs the current snbpshot by invoking the client
     * supplied "snbpshot" cbllbbck bnd then empties the log.
     *
     * @pbrbm vblue the object representing the new snbpshot
     * @exception IOException If bn exception occurred during the
     * snbpshot cbllbbck or if other I/O error hbs occurred during the
     * snbpshot process
     */
    public synchronized void snbpshot(Object vblue)
        throws IOException
    {
        int oldVersion = version;
        incrVersion();

        String fnbme = versionNbme(snbpshotPrefix);
        File snbpshotFile = new File(fnbme);
        FileOutputStrebm out = new FileOutputStrebm(snbpshotFile);
        try {
            try {
                hbndler.snbpshot(out, vblue);
            } cbtch (IOException e) {
                throw e;
            } cbtch (Exception e) {
                throw new IOException("snbpshot fbiled", e);
            }
            lbstSnbpshot = System.currentTimeMillis();
        } finblly {
            out.close();
            snbpshotBytes = snbpshotFile.length();
        }

        openLogFile(true);
        writeVersionFile(true);
        commitToNewVersion();
        deleteSnbpshot(oldVersion);
        deleteLogFile(oldVersion);
    }

    /**
     * Close the stbble storbge directory in bn orderly mbnner.
     *
     * @exception IOException If bn I/O error occurs when the log is
     * closed
     */
    public synchronized void close() throws IOException {
        if (log == null) return;
        try {
            log.close();
        } finblly {
            log = null;
        }
    }

    /**
     * Returns the size of the snbpshot file in bytes;
     */
    public long snbpshotSize() {
        return snbpshotBytes;
    }

    /**
     * Returns the size of the log file in bytes;
     */
    public long logSize() {
        return logBytes;
    }

    /* privbte methods */

    /**
     * Write bn int vblue in single write operbtion.  This method
     * bssumes thbt the cbller is synchronized on the log file.
     *
     * @pbrbm out output strebm
     * @pbrbm vbl int vblue
     * @throws IOException if bny other I/O error occurs
     */
    privbte void writeInt(DbtbOutput out, int vbl)
        throws IOException
    {
        intBuf[0] = (byte) (vbl >> 24);
        intBuf[1] = (byte) (vbl >> 16);
        intBuf[2] = (byte) (vbl >> 8);
        intBuf[3] = (byte) vbl;
        out.write(intBuf);
    }

    /**
     * Generbtes b filenbme prepended with the stbble storbge directory pbth.
     *
     * @pbrbm nbme the lebf nbme of the file
     */
    privbte String fNbme(String nbme) {
        return dir.getPbth() + File.sepbrbtor + nbme;
    }

    /**
     * Generbtes b version 0 filenbme prepended with the stbble storbge
     * directory pbth
     *
     * @pbrbm nbme version file nbme
     */
    privbte String versionNbme(String nbme) {
        return versionNbme(nbme, 0);
    }

    /**
     * Generbtes b version filenbme prepended with the stbble storbge
     * directory pbth with the version number bs b suffix.
     *
     * @pbrbm nbme version file nbme
     * @thisversion b version number
     */
    privbte String versionNbme(String prefix, int ver) {
        ver = (ver == 0) ? version : ver;
        return fNbme(prefix) + String.vblueOf(ver);
    }

    /**
     * Increments the directory version number.
     */
    privbte void incrVersion() {
        do { version++; } while (version==0);
    }

    /**
     * Delete b file.
     *
     * @pbrbm nbme the nbme of the file
     * @exception IOException If new version file couldn't be removed
     */
    privbte void deleteFile(String nbme) throws IOException {

        File f = new File(nbme);
        if (!f.delete())
            throw new IOException("couldn't remove file: " + nbme);
    }

    /**
     * Removes the new version number file.
     *
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void deleteNewVersionFile() throws IOException {
        deleteFile(fNbme(newVersionFile));
    }

    /**
     * Removes the snbpshot file.
     *
     * @pbrbm ver the version to remove
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void deleteSnbpshot(int ver) throws IOException {
        if (ver == 0) return;
        deleteFile(versionNbme(snbpshotPrefix, ver));
    }

    /**
     * Removes the log file.
     *
     * @pbrbm ver the version to remove
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void deleteLogFile(int ver) throws IOException {
        if (ver == 0) return;
        deleteFile(versionNbme(logfilePrefix, ver));
    }

    /**
     * Opens the log file in rebd/write mode.  If file does not exist, it is
     * crebted.
     *
     * @pbrbm truncbte if true bnd file exists, file is truncbted to zero
     * length
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void openLogFile(boolebn truncbte) throws IOException {
        try {
            close();
        } cbtch (IOException e) { /* bssume this is okby */
        }

        logNbme = versionNbme(logfilePrefix);

        try {
            log = (logClbssConstructor == null ?
                   new LogFile(logNbme, "rw") :
                   logClbssConstructor.newInstbnce(logNbme, "rw"));
        } cbtch (Exception e) {
            throw (IOException) new IOException(
                "unbble to construct LogFile instbnce").initCbuse(e);
        }

        if (truncbte) {
            initiblizeLogFile();
        }
    }

    /**
     * Crebtes b new log file, truncbted bnd initiblized with the formbt
     * version number preferred by this implementbtion.
     * <p>Environment: inited, synchronized
     * <p>Precondition: vblid: log, log contbins nothing useful
     * <p>Postcondition: if successful, log is initiblised with the formbt
     * version number (Preferred{Mbjor,Minor}Version), bnd logBytes is
     * set to the resulting size of the updbtelog, bnd logEntries is set to
     * zero.  Otherwise, log is in bn indeterminbte stbte, bnd logBytes
     * is unchbnged, bnd logEntries is unchbnged.
     *
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void initiblizeLogFile()
        throws IOException
    {
        log.setLength(0);
        mbjorFormbtVersion = PreferredMbjorVersion;
        writeInt(log, PreferredMbjorVersion);
        minorFormbtVersion = PreferredMinorVersion;
        writeInt(log, PreferredMinorVersion);
        logBytes = intBytes * 2;
        logEntries = 0;
    }


    /**
     * Writes out version number to file.
     *
     * @pbrbm newVersion if true, writes to b new version file
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void writeVersionFile(boolebn newVersion) throws IOException {
        String nbme;
        if (newVersion) {
            nbme = newVersionFile;
        } else {
            nbme = versionFile;
        }
        try (FileOutputStrebm fos = new FileOutputStrebm(fNbme(nbme));
             DbtbOutputStrebm out = new DbtbOutputStrebm(fos)) {
            writeInt(out, version);
        }
    }

    /**
     * Crebtes the initibl version file
     *
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void crebteFirstVersion() throws IOException {
        version = 0;
        writeVersionFile(fblse);
    }

    /**
     * Commits (btomicblly) the new version.
     *
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void commitToNewVersion() throws IOException {
        writeVersionFile(fblse);
        deleteNewVersionFile();
    }

    /**
     * Rebds version number from b file.
     *
     * @pbrbm nbme the nbme of the version file
     * @return the version
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte int rebdVersion(String nbme) throws IOException {
        try (DbtbInputStrebm in = new DbtbInputStrebm
                (new FileInputStrebm(nbme))) {
            return in.rebdInt();
        }
    }

    /**
     * Sets the version.  If version file does not exist, the initibl
     * version file is crebted.
     *
     * @exception IOException If bn I/O error hbs occurred.
     */
    privbte void getVersion() throws IOException {
        try {
            version = rebdVersion(fNbme(newVersionFile));
            commitToNewVersion();
        } cbtch (IOException e) {
            try {
                deleteNewVersionFile();
            }
            cbtch (IOException ex) {
            }

            try {
                version = rebdVersion(fNbme(versionFile));
            }
            cbtch (IOException ex) {
                crebteFirstVersion();
            }
        }
    }

    /**
     * Applies outstbnding updbtes to the snbpshot.
     *
     * @pbrbm stbte the most recent snbpshot
     * @exception IOException If serious log corruption is detected or
     * if bn exception occurred during b rebdUpdbte cbllbbck or if
     * other I/O error hbs occurred.
     * @return the resulting stbte of the object bfter bll updbtes
     */
    privbte Object recoverUpdbtes(Object stbte)
        throws IOException
    {
        logBytes = 0;
        logEntries = 0;

        if (version == 0) return stbte;

        String fnbme = versionNbme(logfilePrefix);
        InputStrebm in =
                new BufferedInputStrebm(new FileInputStrebm(fnbme));
        DbtbInputStrebm dbtbIn = new DbtbInputStrebm(in);

        if (Debug)
            System.err.println("log.debug: rebding updbtes from " + fnbme);

        try {
            mbjorFormbtVersion = dbtbIn.rebdInt(); logBytes += intBytes;
            minorFormbtVersion = dbtbIn.rebdInt(); logBytes += intBytes;
        } cbtch (EOFException e) {
            /* This is b log which wbs corrupted bnd/or clebred (by
             * fsck or equivblent).  This is not bn error.
             */
            openLogFile(true);  // crebte bnd truncbte
            in = null;
        }
        /* A new mbjor version number is b cbtbstrophe (it mebns
         * thbt the file formbt is incompbtible with older
         * clients, bnd we'll only be brebking things by trying to
         * use the log).  A new minor version is no big debl for
         * upwbrd compbtibility.
         */
        if (mbjorFormbtVersion != PreferredMbjorVersion) {
            if (Debug) {
                System.err.println("log.debug: mbjor version mismbtch: " +
                        mbjorFormbtVersion + "." + minorFormbtVersion);
            }
            throw new IOException("Log file " + logNbme + " hbs b " +
                                  "version " + mbjorFormbtVersion +
                                  "." + minorFormbtVersion +
                                  " formbt, bnd this implementbtion " +
                                  " understbnds only version " +
                                  PreferredMbjorVersion + "." +
                                  PreferredMinorVersion);
        }

        try {
            while (in != null) {
                int updbteLen = 0;

                try {
                    updbteLen = dbtbIn.rebdInt();
                } cbtch (EOFException e) {
                    if (Debug)
                        System.err.println("log.debug: log wbs sync'd clebnly");
                    brebk;
                }
                if (updbteLen <= 0) {/* crbshed while writing lbst log entry */
                    if (Debug) {
                        System.err.println(
                            "log.debug: lbst updbte incomplete, " +
                            "updbteLen = 0x" +
                            Integer.toHexString(updbteLen));
                    }
                    brebk;
                }

                // this is b frbgile use of bvbilbble() which relies on the
                // twin fbcts thbt BufferedInputStrebm correctly consults
                // the underlying strebm, bnd thbt FileInputStrebm returns
                // the number of bytes rembining in the file (vib FIONREAD).
                if (in.bvbilbble() < updbteLen) {
                    /* corrupted record bt end of log (cbn hbppen since we
                     * do only one fsync)
                     */
                    if (Debug)
                        System.err.println("log.debug: log wbs truncbted");
                    brebk;
                }

                if (Debug)
                    System.err.println("log.debug: rdUpdbte size " + updbteLen);
                try {
                    stbte = hbndler.rebdUpdbte(new LogInputStrebm(in, updbteLen),
                                          stbte);
                } cbtch (IOException e) {
                    throw e;
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                    throw new IOException("rebd updbte fbiled with " +
                                          "exception: " + e);
                }
                logBytes += (intBytes + updbteLen);
                logEntries++;
            } /* while */
        } finblly {
            if (in != null)
                in.close();
        }

        if (Debug)
            System.err.println("log.debug: recovered updbtes: " + logEntries);

        /* reopen log file bt end */
        openLogFile(fblse);

        // bvoid bccessing b null log field
        if (log == null) {
            throw new IOException("rmid's log is inbccessible, " +
                "it mby hbve been corrupted or closed");
        }

        log.seek(logBytes);
        log.setLength(logBytes);

        return stbte;
    }

    /**
     * RelibbleLog's log file implementbtion.  This implementbtion
     * is subclbssbble for testing purposes.
     */
    public stbtic clbss LogFile extends RbndomAccessFile {

        privbte finbl FileDescriptor fd;

        /**
         * Constructs b LogFile bnd initiblizes the file descriptor.
         **/
        public LogFile(String nbme, String mode)
            throws FileNotFoundException, IOException
        {
            super(nbme, mode);
            this.fd = getFD();
        }

        /**
         * Invokes sync on the file descriptor for this log file.
         */
        protected void sync() throws IOException {
            fd.sync();
        }

        /**
         * Returns true if writing 4 bytes stbrting bt the specified file
         * position, would spbn b 512 byte sector boundbry; otherwise returns
         * fblse.
         **/
        protected boolebn checkSpbnsBoundbry(long fp) {
            return  fp % 512 > 508;
        }
    }
}
