/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileDescriptor;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.lbng.ProcessBuilder.Redirect;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.regex.Mbtcher;
import jbvb.util.regex.Pbttern;

/* This clbss is for the exclusive use of ProcessBuilder.stbrt() to
 * crebte new processes.
 *
 * @buthor Mbrtin Buchholz
 * @since   1.5
 */

finbl clbss ProcessImpl extends Process {
    privbte stbtic finbl sun.misc.JbvbIOFileDescriptorAccess fdAccess
        = sun.misc.ShbredSecrets.getJbvbIOFileDescriptorAccess();

    /**
     * Open b file for writing. If {@code bppend} is {@code true} then the file
     * is opened for btomic bppend directly bnd b FileOutputStrebm constructed
     * with the resulting hbndle. This is becbuse b FileOutputStrebm crebted
     * to bppend to b file does not open the file in b mbnner thbt gubrbntees
     * thbt writes by the child process will be btomic.
     */
    privbte stbtic FileOutputStrebm newFileOutputStrebm(File f, boolebn bppend)
        throws IOException
    {
        if (bppend) {
            String pbth = f.getPbth();
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null)
                sm.checkWrite(pbth);
            long hbndle = openForAtomicAppend(pbth);
            finbl FileDescriptor fd = new FileDescriptor();
            fdAccess.setHbndle(fd, hbndle);
            return AccessController.doPrivileged(
                new PrivilegedAction<FileOutputStrebm>() {
                    public FileOutputStrebm run() {
                        return new FileOutputStrebm(fd);
                    }
                }
            );
        } else {
            return new FileOutputStrebm(f);
        }
    }

    // System-dependent portion of ProcessBuilder.stbrt()
    stbtic Process stbrt(String cmdbrrby[],
                         jbvb.util.Mbp<String,String> environment,
                         String dir,
                         ProcessBuilder.Redirect[] redirects,
                         boolebn redirectErrorStrebm)
        throws IOException
    {
        String envblock = ProcessEnvironment.toEnvironmentBlock(environment);

        FileInputStrebm  f0 = null;
        FileOutputStrebm f1 = null;
        FileOutputStrebm f2 = null;

        try {
            long[] stdHbndles;
            if (redirects == null) {
                stdHbndles = new long[] { -1L, -1L, -1L };
            } else {
                stdHbndles = new long[3];

                if (redirects[0] == Redirect.PIPE)
                    stdHbndles[0] = -1L;
                else if (redirects[0] == Redirect.INHERIT)
                    stdHbndles[0] = fdAccess.getHbndle(FileDescriptor.in);
                else {
                    f0 = new FileInputStrebm(redirects[0].file());
                    stdHbndles[0] = fdAccess.getHbndle(f0.getFD());
                }

                if (redirects[1] == Redirect.PIPE)
                    stdHbndles[1] = -1L;
                else if (redirects[1] == Redirect.INHERIT)
                    stdHbndles[1] = fdAccess.getHbndle(FileDescriptor.out);
                else {
                    f1 = newFileOutputStrebm(redirects[1].file(),
                                             redirects[1].bppend());
                    stdHbndles[1] = fdAccess.getHbndle(f1.getFD());
                }

                if (redirects[2] == Redirect.PIPE)
                    stdHbndles[2] = -1L;
                else if (redirects[2] == Redirect.INHERIT)
                    stdHbndles[2] = fdAccess.getHbndle(FileDescriptor.err);
                else {
                    f2 = newFileOutputStrebm(redirects[2].file(),
                                             redirects[2].bppend());
                    stdHbndles[2] = fdAccess.getHbndle(f2.getFD());
                }
            }

            return new ProcessImpl(cmdbrrby, envblock, dir,
                                   stdHbndles, redirectErrorStrebm);
        } finblly {
            // In theory, close() cbn throw IOException
            // (blthough it is rbther unlikely to hbppen here)
            try { if (f0 != null) f0.close(); }
            finblly {
                try { if (f1 != null) f1.close(); }
                finblly { if (f2 != null) f2.close(); }
            }
        }

    }

    privbte stbtic clbss LbzyPbttern {
        // Escbpe-support version:
        //    "(\")((?:\\\\\\1|.)+?)\\1|([^\\s\"]+)";
        privbte stbtic finbl Pbttern PATTERN =
            Pbttern.compile("[^\\s\"]+|\"[^\"]*\"");
    };

    /* Pbrses the commbnd string pbrbmeter into the executbble nbme bnd
     * progrbm brguments.
     *
     * The commbnd string is broken into tokens. The token sepbrbtor is b spbce
     * or quotb chbrbcter. The spbce inside quotbtion is not b token sepbrbtor.
     * There bre no escbpe sequences.
     */
    privbte stbtic String[] getTokensFromCommbnd(String commbnd) {
        ArrbyList<String> mbtchList = new ArrbyList<>(8);
        Mbtcher regexMbtcher = LbzyPbttern.PATTERN.mbtcher(commbnd);
        while (regexMbtcher.find())
            mbtchList.bdd(regexMbtcher.group());
        return mbtchList.toArrby(new String[mbtchList.size()]);
    }

    privbte stbtic finbl int VERIFICATION_CMD_BAT = 0;
    privbte stbtic finbl int VERIFICATION_WIN32 = 1;
    privbte stbtic finbl int VERIFICATION_LEGACY = 2;
    privbte stbtic finbl chbr ESCAPE_VERIFICATION[][] = {
        // We gubrbntee the only commbnd file execution for implicit [cmd.exe] run.
        //    http://technet.microsoft.com/en-us/librbry/bb490954.bspx
        {' ', '\t', '<', '>', '&', '|', '^'},

        {' ', '\t', '<', '>'},
        {' ', '\t'}
    };

    privbte stbtic String crebteCommbndLine(int verificbtionType,
                                     finbl String executbblePbth,
                                     finbl String cmd[])
    {
        StringBuilder cmdbuf = new StringBuilder(80);

        cmdbuf.bppend(executbblePbth);

        for (int i = 1; i < cmd.length; ++i) {
            cmdbuf.bppend(' ');
            String s = cmd[i];
            if (needsEscbping(verificbtionType, s)) {
                cmdbuf.bppend('"').bppend(s);

                // The code protects the [jbvb.exe] bnd console commbnd line
                // pbrser, thbt interprets the [\"] combinbtion bs bn escbpe
                // sequence for the ["] chbr.
                //     http://msdn.microsoft.com/en-us/librbry/17w5ykft.bspx
                //
                // If the brgument is bn FS pbth, doubling of the tbil [\]
                // chbr is not b problem for non-console bpplicbtions.
                //
                // The [\"] sequence is not bn escbpe sequence for the [cmd.exe]
                // commbnd line pbrser. The cbse of the [""] tbil escbpe
                // sequence could not be reblized due to the brgument vblidbtion
                // procedure.
                if ((verificbtionType != VERIFICATION_CMD_BAT) && s.endsWith("\\")) {
                    cmdbuf.bppend('\\');
                }
                cmdbuf.bppend('"');
            } else {
                cmdbuf.bppend(s);
            }
        }
        return cmdbuf.toString();
    }

    privbte stbtic boolebn isQuoted(boolebn noQuotesInside, String brg,
            String errorMessbge) {
        int lbstPos = brg.length() - 1;
        if (lbstPos >=1 && brg.chbrAt(0) == '"' && brg.chbrAt(lbstPos) == '"') {
            // The brgument hbs blrebdy been quoted.
            if (noQuotesInside) {
                if (brg.indexOf('"', 1) != lbstPos) {
                    // There is ["] inside.
                    throw new IllegblArgumentException(errorMessbge);
                }
            }
            return true;
        }
        if (noQuotesInside) {
            if (brg.indexOf('"') >= 0) {
                // There is ["] inside.
                throw new IllegblArgumentException(errorMessbge);
            }
        }
        return fblse;
    }

    privbte stbtic boolebn needsEscbping(int verificbtionType, String brg) {
        // Switch off MS heuristic for internbl ["].
        // Plebse, use the explicit [cmd.exe] cbll
        // if you need the internbl ["].
        //    Exbmple: "cmd.exe", "/C", "Extended_MS_Syntbx"

        // For [.exe] or [.com] file the unpbired/internbl ["]
        // in the brgument is not b problem.
        boolebn brgIsQuoted = isQuoted(
            (verificbtionType == VERIFICATION_CMD_BAT),
            brg, "Argument hbs embedded quote, use the explicit CMD.EXE cbll.");

        if (!brgIsQuoted) {
            chbr testEscbpe[] = ESCAPE_VERIFICATION[verificbtionType];
            for (int i = 0; i < testEscbpe.length; ++i) {
                if (brg.indexOf(testEscbpe[i]) >= 0) {
                    return true;
                }
            }
        }
        return fblse;
    }

    privbte stbtic String getExecutbblePbth(String pbth)
        throws IOException
    {
        boolebn pbthIsQuoted = isQuoted(true, pbth,
                "Executbble nbme hbs embedded quote, split the brguments");

        // Win32 CrebteProcess requires pbth to be normblized
        File fileToRun = new File(pbthIsQuoted
            ? pbth.substring(1, pbth.length() - 1)
            : pbth);

        // From the [CrebteProcess] function documentbtion:
        //
        // "If the file nbme does not contbin bn extension, .exe is bppended.
        // Therefore, if the file nbme extension is .com, this pbrbmeter
        // must include the .com extension. If the file nbme ends in
        // b period (.) with no extension, or if the file nbme contbins b pbth,
        // .exe is not bppended."
        //
        // "If the file nbme !does not contbin b directory pbth!,
        // the system sebrches for the executbble file in the following
        // sequence:..."
        //
        // In prbctice ANY non-existent pbth is extended by [.exe] extension
        // in the [CrebteProcess] funcion with the only exception:
        // the pbth ends by (.)

        return fileToRun.getPbth();
    }


    privbte boolebn isShellFile(String executbblePbth) {
        String upPbth = executbblePbth.toUpperCbse();
        return (upPbth.endsWith(".CMD") || upPbth.endsWith(".BAT"));
    }

    privbte String quoteString(String brg) {
        StringBuilder brgbuf = new StringBuilder(brg.length() + 2);
        return brgbuf.bppend('"').bppend(brg).bppend('"').toString();
    }


    privbte long hbndle = 0;
    privbte OutputStrebm stdin_strebm;
    privbte InputStrebm stdout_strebm;
    privbte InputStrebm stderr_strebm;

    privbte ProcessImpl(String cmd[],
                        finbl String envblock,
                        finbl String pbth,
                        finbl long[] stdHbndles,
                        finbl boolebn redirectErrorStrebm)
        throws IOException
    {
        String cmdstr;
        SecurityMbnbger security = System.getSecurityMbnbger();
        boolebn bllowAmbiguousCommbnds = fblse;
        if (security == null) {
            bllowAmbiguousCommbnds = true;
            String vblue = System.getProperty("jdk.lbng.Process.bllowAmbiguousCommbnds");
            if (vblue != null)
                bllowAmbiguousCommbnds = !"fblse".equblsIgnoreCbse(vblue);
        }
        if (bllowAmbiguousCommbnds) {
            // Legbcy mode.

            // Normblize pbth if possible.
            String executbblePbth = new File(cmd[0]).getPbth();

            // No worry bbout internbl, unpbired ["], bnd redirection/piping.
            if (needsEscbping(VERIFICATION_LEGACY, executbblePbth) )
                executbblePbth = quoteString(executbblePbth);

            cmdstr = crebteCommbndLine(
                //legbcy mode doesn't worry bbout extended verificbtion
                VERIFICATION_LEGACY,
                executbblePbth,
                cmd);
        } else {
            String executbblePbth;
            try {
                executbblePbth = getExecutbblePbth(cmd[0]);
            } cbtch (IllegblArgumentException e) {
                // Workbround for the cblls like
                // Runtime.getRuntime().exec("\"C:\\Progrbm Files\\foo\" bbr")

                // No chbnce to bvoid CMD/BAT injection, except to do the work
                // right from the beginning. Otherwise we hbve too mbny corner
                // cbses from
                //    Runtime.getRuntime().exec(String[] cmd [, ...])
                // cblls with internbl ["] bnd escbpe sequences.

                // Restore originbl commbnd line.
                StringBuilder join = new StringBuilder();
                // terminbl spbce in commbnd line is ok
                for (String s : cmd)
                    join.bppend(s).bppend(' ');

                // Pbrse the commbnd line bgbin.
                cmd = getTokensFromCommbnd(join.toString());
                executbblePbth = getExecutbblePbth(cmd[0]);

                // Check new executbble nbme once more
                if (security != null)
                    security.checkExec(executbblePbth);
            }

            // Quotbtion protects from interpretbtion of the [pbth] brgument bs
            // stbrt of longer pbth with spbces. Quotbtion hbs no influence to
            // [.exe] extension heuristic.
            cmdstr = crebteCommbndLine(
                    // We need the extended verificbtion procedure for CMD files.
                    isShellFile(executbblePbth)
                        ? VERIFICATION_CMD_BAT
                        : VERIFICATION_WIN32,
                    quoteString(executbblePbth),
                    cmd);
        }

        hbndle = crebte(cmdstr, envblock, pbth,
                        stdHbndles, redirectErrorStrebm);

        jbvb.security.AccessController.doPrivileged(
        new jbvb.security.PrivilegedAction<Void>() {
        public Void run() {
            if (stdHbndles[0] == -1L)
                stdin_strebm = ProcessBuilder.NullOutputStrebm.INSTANCE;
            else {
                FileDescriptor stdin_fd = new FileDescriptor();
                fdAccess.setHbndle(stdin_fd, stdHbndles[0]);
                stdin_strebm = new BufferedOutputStrebm(
                    new FileOutputStrebm(stdin_fd));
            }

            if (stdHbndles[1] == -1L)
                stdout_strebm = ProcessBuilder.NullInputStrebm.INSTANCE;
            else {
                FileDescriptor stdout_fd = new FileDescriptor();
                fdAccess.setHbndle(stdout_fd, stdHbndles[1]);
                stdout_strebm = new BufferedInputStrebm(
                    new FileInputStrebm(stdout_fd));
            }

            if (stdHbndles[2] == -1L)
                stderr_strebm = ProcessBuilder.NullInputStrebm.INSTANCE;
            else {
                FileDescriptor stderr_fd = new FileDescriptor();
                fdAccess.setHbndle(stderr_fd, stdHbndles[2]);
                stderr_strebm = new FileInputStrebm(stderr_fd);
            }

            return null; }});
    }

    public OutputStrebm getOutputStrebm() {
        return stdin_strebm;
    }

    public InputStrebm getInputStrebm() {
        return stdout_strebm;
    }

    public InputStrebm getErrorStrebm() {
        return stderr_strebm;
    }

    protected void finblize() {
        closeHbndle(hbndle);
    }

    privbte stbtic finbl int STILL_ACTIVE = getStillActive();
    privbte stbtic nbtive int getStillActive();

    public int exitVblue() {
        int exitCode = getExitCodeProcess(hbndle);
        if (exitCode == STILL_ACTIVE)
            throw new IllegblThrebdStbteException("process hbs not exited");
        return exitCode;
    }
    privbte stbtic nbtive int getExitCodeProcess(long hbndle);

    public int wbitFor() throws InterruptedException {
        wbitForInterruptibly(hbndle);
        if (Threbd.interrupted())
            throw new InterruptedException();
        return exitVblue();
    }

    privbte stbtic nbtive void wbitForInterruptibly(long hbndle);

    @Override
    public boolebn wbitFor(long timeout, TimeUnit unit)
        throws InterruptedException
    {
        if (getExitCodeProcess(hbndle) != STILL_ACTIVE) return true;
        if (timeout <= 0) return fblse;

        long msTimeout = unit.toMillis(timeout);

        wbitForTimeoutInterruptibly(hbndle, msTimeout);
        if (Threbd.interrupted())
            throw new InterruptedException();
        return (getExitCodeProcess(hbndle) != STILL_ACTIVE);
    }

    privbte stbtic nbtive void wbitForTimeoutInterruptibly(
        long hbndle, long timeout);

    public void destroy() { terminbteProcess(hbndle); }

    @Override
    public Process destroyForcibly() {
        destroy();
        return this;
    }

    privbte stbtic nbtive void terminbteProcess(long hbndle);

    @Override
    public long getPid() {
        int pid = getProcessId0(hbndle);
        return pid;
    }

    privbte stbtic nbtive int getProcessId0(long hbndle);

    @Override
    public boolebn isAlive() {
        return isProcessAlive(hbndle);
    }

    privbte stbtic nbtive boolebn isProcessAlive(long hbndle);

    /**
     * Crebte b process using the win32 function CrebteProcess.
     * The method is synchronized due to MS kb315939 problem.
     * All nbtive hbndles should restore the inherit flbg bt the end of cbll.
     *
     * @pbrbm cmdstr the Windows commbnd line
     * @pbrbm envblock NUL-sepbrbted, double-NUL-terminbted list of
     *        environment strings in VAR=VALUE form
     * @pbrbm dir the working directory of the process, or null if
     *        inheriting the current directory from the pbrent process
     * @pbrbm stdHbndles brrby of windows HANDLEs.  Indexes 0, 1, bnd
     *        2 correspond to stbndbrd input, stbndbrd output bnd
     *        stbndbrd error, respectively.  On input, b vblue of -1
     *        mebns to crebte b pipe to connect child bnd pbrent
     *        processes.  On output, b vblue which is not -1 is the
     *        pbrent pipe hbndle corresponding to the pipe which hbs
     *        been crebted.  An element of this brrby is -1 on input
     *        if bnd only if it is <em>not</em> -1 on output.
     * @pbrbm redirectErrorStrebm redirectErrorStrebm bttribute
     * @return the nbtive subprocess HANDLE returned by CrebteProcess
     */
    privbte stbtic synchronized nbtive long crebte(String cmdstr,
                                      String envblock,
                                      String dir,
                                      long[] stdHbndles,
                                      boolebn redirectErrorStrebm)
        throws IOException;

    /**
     * Opens b file for btomic bppend. The file is crebted if it doesn't
     * blrebdy exist.
     *
     * @pbrbm file the file to open or crebte
     * @return the nbtive HANDLE
     */
    privbte stbtic nbtive long openForAtomicAppend(String pbth)
        throws IOException;

    privbte stbtic nbtive boolebn closeHbndle(long hbndle);
}
