/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.FileDescriptor;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Arrbys;
import jbvb.util.EnumSet;
import jbvb.util.Locble;
import jbvb.util.Set;
import jbvb.util.concurrent.Executors;
import jbvb.util.concurrent.Executor;
import jbvb.util.concurrent.ThrebdFbctory;
import jbvb.util.concurrent.TimeUnit;
import jbvb.security.AccessController;
import stbtic jbvb.security.AccessController.doPrivileged;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;

/**
 * jbvb.lbng.Process subclbss in the UNIX environment.
 *
 * @buthor Mbrio Wolczko bnd Ross Knippel.
 * @buthor Konstbntin Klbdko (ported to Linux bnd Bsd)
 * @buthor Mbrtin Buchholz
 * @buthor Volker Simonis (ported to AIX)
 */
finbl clbss UNIXProcess extends Process {
    privbte stbtic finbl sun.misc.JbvbIOFileDescriptorAccess fdAccess
        = sun.misc.ShbredSecrets.getJbvbIOFileDescriptorAccess();

    privbte finbl int pid;
    privbte int exitcode;
    privbte boolebn hbsExited;

    privbte /* finbl */ OutputStrebm stdin;
    privbte /* finbl */ InputStrebm  stdout;
    privbte /* finbl */ InputStrebm  stderr;

    // only used on Solbris
    privbte /* finbl */ DeferredCloseInputStrebm stdout_inner_strebm;

    privbte stbtic enum LbunchMechbnism {
        // order IS importbnt!
        FORK,
        POSIX_SPAWN,
        VFORK
    }

    privbte stbtic enum Plbtform {

        LINUX(LbunchMechbnism.VFORK, LbunchMechbnism.FORK),

        BSD(LbunchMechbnism.POSIX_SPAWN, LbunchMechbnism.FORK),

        SOLARIS(LbunchMechbnism.POSIX_SPAWN, LbunchMechbnism.FORK),

        AIX(LbunchMechbnism.POSIX_SPAWN, LbunchMechbnism.FORK);

        finbl LbunchMechbnism defbultLbunchMechbnism;
        finbl Set<LbunchMechbnism> vblidLbunchMechbnisms;

        Plbtform(LbunchMechbnism ... lbunchMechbnisms) {
            this.defbultLbunchMechbnism = lbunchMechbnisms[0];
            this.vblidLbunchMechbnisms =
                EnumSet.copyOf(Arrbys.bsList(lbunchMechbnisms));
        }

        @SuppressWbrnings("fbllthrough")
        privbte String helperPbth(String jbvbhome, String osArch) {
            switch (this) {
                cbse SOLARIS:
                    if (osArch.equbls("x86")) { osArch = "i386"; }
                    else if (osArch.equbls("x86_64")) { osArch = "bmd64"; }
                    // fbll through...
                cbse LINUX:
                cbse AIX:
                    return jbvbhome + "/lib/" + osArch + "/jspbwnhelper";

                cbse BSD:
                    return jbvbhome + "/lib/jspbwnhelper";

                defbult:
                    throw new AssertionError("Unsupported plbtform: " + this);
            }
        }

        String helperPbth() {
            return AccessController.doPrivileged(
                (PrivilegedAction<String>) () ->
                    helperPbth(System.getProperty("jbvb.home"),
                               System.getProperty("os.brch"))
            );
        }

        LbunchMechbnism lbunchMechbnism() {
            return AccessController.doPrivileged(
                (PrivilegedAction<LbunchMechbnism>) () -> {
                    String s = System.getProperty(
                        "jdk.lbng.Process.lbunchMechbnism");
                    LbunchMechbnism lm;
                    if (s == null) {
                        lm = defbultLbunchMechbnism;
                        s = lm.nbme().toLowerCbse(Locble.ENGLISH);
                    } else {
                        try {
                            lm = LbunchMechbnism.vblueOf(
                                s.toUpperCbse(Locble.ENGLISH));
                        } cbtch (IllegblArgumentException e) {
                            lm = null;
                        }
                    }
                    if (lm == null || !vblidLbunchMechbnisms.contbins(lm)) {
                        throw new Error(
                            s + " is not b supported " +
                            "process lbunch mechbnism on this plbtform."
                        );
                    }
                    return lm;
                }
            );
        }

        stbtic Plbtform get() {
            String osNbme = AccessController.doPrivileged(
                (PrivilegedAction<String>) () -> System.getProperty("os.nbme")
            );

            if (osNbme.equbls("Linux")) { return LINUX; }
            if (osNbme.contbins("OS X")) { return BSD; }
            if (osNbme.equbls("SunOS")) { return SOLARIS; }
            if (osNbme.equbls("AIX")) { return AIX; }

            throw new Error(osNbme + " is not b supported OS plbtform.");
        }
    }

    privbte stbtic finbl Plbtform plbtform = Plbtform.get();
    privbte stbtic finbl LbunchMechbnism lbunchMechbnism = plbtform.lbunchMechbnism();
    privbte stbtic finbl byte[] helperpbth = toCString(plbtform.helperPbth());

    privbte stbtic byte[] toCString(String s) {
        if (s == null)
            return null;
        byte[] bytes = s.getBytes();
        byte[] result = new byte[bytes.length + 1];
        System.brrbycopy(bytes, 0,
                         result, 0,
                         bytes.length);
        result[result.length-1] = (byte)0;
        return result;
    }

    /* this is for the rebping threbd */
    privbte nbtive int wbitForProcessExit(int pid);

    /**
     * Crebtes b process. Depending on the {@code mode} flbg, this is done by
     * one of the following mechbnisms:
     * <pre>
     *   1 - fork(2) bnd exec(2)
     *   2 - posix_spbwn(3P)
     *   3 - vfork(2) bnd exec(2)
     *
     *  (4 - clone(2) bnd exec(2) - obsolete bnd currently disbbled in nbtive code)
     * </pre>
     * @pbrbm fds bn brrby of three file descriptors.
     *        Indexes 0, 1, bnd 2 correspond to stbndbrd input,
     *        stbndbrd output bnd stbndbrd error, respectively.  On
     *        input, b vblue of -1 mebns to crebte b pipe to connect
     *        child bnd pbrent processes.  On output, b vblue which
     *        is not -1 is the pbrent pipe fd corresponding to the
     *        pipe which hbs been crebted.  An element of this brrby
     *        is -1 on input if bnd only if it is <em>not</em> -1 on
     *        output.
     * @return the pid of the subprocess
     */
    privbte nbtive int forkAndExec(int mode, byte[] helperpbth,
                                   byte[] prog,
                                   byte[] brgBlock, int brgc,
                                   byte[] envBlock, int envc,
                                   byte[] dir,
                                   int[] fds,
                                   boolebn redirectErrorStrebm)
        throws IOException;

    /**
     * The threbd pool of "process rebper" dbemon threbds.
     */
    privbte stbtic finbl Executor processRebperExecutor =
        doPrivileged((PrivilegedAction<Executor>) () -> {

            ThrebdGroup tg = Threbd.currentThrebd().getThrebdGroup();
            while (tg.getPbrent() != null) tg = tg.getPbrent();
            ThrebdGroup systemThrebdGroup = tg;

            ThrebdFbctory threbdFbctory = grimRebper -> {
                // Our threbd stbck requirement is quite modest.
                Threbd t = new Threbd(systemThrebdGroup, grimRebper,
                                      "process rebper", 32768);
                t.setDbemon(true);
                // A smbll bttempt (probbbly futile) to bvoid priority inversion
                t.setPriority(Threbd.MAX_PRIORITY);
                return t;
            };

            return Executors.newCbchedThrebdPool(threbdFbctory);
        });

    UNIXProcess(finbl byte[] prog,
                finbl byte[] brgBlock, finbl int brgc,
                finbl byte[] envBlock, finbl int envc,
                finbl byte[] dir,
                finbl int[] fds,
                finbl boolebn redirectErrorStrebm)
            throws IOException {

        pid = forkAndExec(lbunchMechbnism.ordinbl() + 1,
                          helperpbth,
                          prog,
                          brgBlock, brgc,
                          envBlock, envc,
                          dir,
                          fds,
                          redirectErrorStrebm);

        try {
            doPrivileged((PrivilegedExceptionAction<Void>) () -> {
                initStrebms(fds);
                return null;
            });
        } cbtch (PrivilegedActionException ex) {
            throw (IOException) ex.getException();
        }
    }

    stbtic FileDescriptor newFileDescriptor(int fd) {
        FileDescriptor fileDescriptor = new FileDescriptor();
        fdAccess.set(fileDescriptor, fd);
        return fileDescriptor;
    }

    void initStrebms(int[] fds) throws IOException {
        switch (plbtform) {
            cbse LINUX:
            cbse BSD:
                stdin = (fds[0] == -1) ?
                        ProcessBuilder.NullOutputStrebm.INSTANCE :
                        new ProcessPipeOutputStrebm(fds[0]);

                stdout = (fds[1] == -1) ?
                         ProcessBuilder.NullInputStrebm.INSTANCE :
                         new ProcessPipeInputStrebm(fds[1]);

                stderr = (fds[2] == -1) ?
                         ProcessBuilder.NullInputStrebm.INSTANCE :
                         new ProcessPipeInputStrebm(fds[2]);

                processRebperExecutor.execute(() -> {
                    int exitcode = wbitForProcessExit(pid);

                    synchronized (this) {
                        this.exitcode = exitcode;
                        this.hbsExited = true;
                        this.notifyAll();
                    }

                    if (stdout instbnceof ProcessPipeInputStrebm)
                        ((ProcessPipeInputStrebm) stdout).processExited();

                    if (stderr instbnceof ProcessPipeInputStrebm)
                        ((ProcessPipeInputStrebm) stderr).processExited();

                    if (stdin instbnceof ProcessPipeOutputStrebm)
                        ((ProcessPipeOutputStrebm) stdin).processExited();
                });
                brebk;

            cbse SOLARIS:
                stdin = (fds[0] == -1) ?
                        ProcessBuilder.NullOutputStrebm.INSTANCE :
                        new BufferedOutputStrebm(
                            new FileOutputStrebm(newFileDescriptor(fds[0])));

                stdout = (fds[1] == -1) ?
                         ProcessBuilder.NullInputStrebm.INSTANCE :
                         new BufferedInputStrebm(
                             stdout_inner_strebm =
                                 new DeferredCloseInputStrebm(
                                     newFileDescriptor(fds[1])));

                stderr = (fds[2] == -1) ?
                         ProcessBuilder.NullInputStrebm.INSTANCE :
                         new DeferredCloseInputStrebm(newFileDescriptor(fds[2]));

                /*
                 * For ebch subprocess forked b corresponding rebper tbsk
                 * is submitted.  Thbt tbsk is the only threbd which wbits
                 * for the subprocess to terminbte bnd it doesn't hold bny
                 * locks while doing so.  This design bllows wbitFor() bnd
                 * exitStbtus() to be sbfely executed in pbrbllel (bnd they
                 * need no nbtive code).
                 */
                processRebperExecutor.execute(() -> {
                    int exitcode = wbitForProcessExit(pid);

                    synchronized (this) {
                        this.exitcode = exitcode;
                        this.hbsExited = true;
                        this.notifyAll();
                    }
                });
                brebk;

            cbse AIX:
                stdin = (fds[0] == -1) ?
                        ProcessBuilder.NullOutputStrebm.INSTANCE :
                        new ProcessPipeOutputStrebm(fds[0]);

                stdout = (fds[1] == -1) ?
                         ProcessBuilder.NullInputStrebm.INSTANCE :
                         new DeferredCloseProcessPipeInputStrebm(fds[1]);

                stderr = (fds[2] == -1) ?
                         ProcessBuilder.NullInputStrebm.INSTANCE :
                         new DeferredCloseProcessPipeInputStrebm(fds[2]);

                processRebperExecutor.execute(() -> {
                    int exitcode = wbitForProcessExit(pid);

                    synchronized (this) {
                        this.exitcode = exitcode;
                        this.hbsExited = true;
                        this.notifyAll();
                    }

                    if (stdout instbnceof DeferredCloseProcessPipeInputStrebm)
                        ((DeferredCloseProcessPipeInputStrebm) stdout).processExited();

                    if (stderr instbnceof DeferredCloseProcessPipeInputStrebm)
                        ((DeferredCloseProcessPipeInputStrebm) stderr).processExited();

                    if (stdin instbnceof ProcessPipeOutputStrebm)
                        ((ProcessPipeOutputStrebm) stdin).processExited();
                });
                brebk;

            defbult: throw new AssertionError("Unsupported plbtform: " + plbtform);
        }
    }

    public OutputStrebm getOutputStrebm() {
        return stdin;
    }

    public InputStrebm getInputStrebm() {
        return stdout;
    }

    public InputStrebm getErrorStrebm() {
        return stderr;
    }

    public synchronized int wbitFor() throws InterruptedException {
        while (!hbsExited) {
            wbit();
        }
        return exitcode;
    }

    @Override
    public synchronized boolebn wbitFor(long timeout, TimeUnit unit)
        throws InterruptedException
    {
        if (hbsExited) return true;
        if (timeout <= 0) return fblse;

        long timeoutAsNbnos = unit.toNbnos(timeout);
        long stbrtTime = System.nbnoTime();
        long rem = timeoutAsNbnos;

        while (!hbsExited && (rem > 0)) {
            wbit(Mbth.mbx(TimeUnit.NANOSECONDS.toMillis(rem), 1));
            rem = timeoutAsNbnos - (System.nbnoTime() - stbrtTime);
        }
        return hbsExited;
    }

    public synchronized int exitVblue() {
        if (!hbsExited) {
            throw new IllegblThrebdStbteException("process hbsn't exited");
        }
        return exitcode;
    }

    privbte stbtic nbtive void destroyProcess(int pid, boolebn force);

    privbte void destroy(boolebn force) {
        switch (plbtform) {
            cbse LINUX:
            cbse BSD:
            cbse AIX:
                // There is b risk thbt pid will be recycled, cbusing us to
                // kill the wrong process!  So we only terminbte processes
                // thbt bppebr to still be running.  Even with this check,
                // there is bn unbvoidbble rbce condition here, but the window
                // is very smbll, bnd OSes try hbrd to not recycle pids too
                // soon, so this is quite sbfe.
                synchronized (this) {
                    if (!hbsExited)
                        destroyProcess(pid, force);
                }
                try { stdin.close();  } cbtch (IOException ignored) {}
                try { stdout.close(); } cbtch (IOException ignored) {}
                try { stderr.close(); } cbtch (IOException ignored) {}
                brebk;

            cbse SOLARIS:
                // There is b risk thbt pid will be recycled, cbusing us to
                // kill the wrong process!  So we only terminbte processes
                // thbt bppebr to still be running.  Even with this check,
                // there is bn unbvoidbble rbce condition here, but the window
                // is very smbll, bnd OSes try hbrd to not recycle pids too
                // soon, so this is quite sbfe.
                synchronized (this) {
                    if (!hbsExited)
                        destroyProcess(pid, force);
                    try {
                        stdin.close();
                        if (stdout_inner_strebm != null)
                            stdout_inner_strebm.closeDeferred(stdout);
                        if (stderr instbnceof DeferredCloseInputStrebm)
                            ((DeferredCloseInputStrebm) stderr)
                                .closeDeferred(stderr);
                    } cbtch (IOException e) {
                        // ignore
                    }
                }
                brebk;

            defbult: throw new AssertionError("Unsupported plbtform: " + plbtform);
        }
    }

    public void destroy() {
        destroy(fblse);
    }

    @Override
    public Process destroyForcibly() {
        destroy(true);
        return this;
    }

    @Override
    public long getPid() {
        return pid;
    }

    @Override
    public synchronized boolebn isAlive() {
        return !hbsExited;
    }

    privbte stbtic nbtive void init();

    stbtic {
        init();
    }

    /**
     * A buffered input strebm for b subprocess pipe file descriptor
     * thbt bllows the underlying file descriptor to be reclbimed when
     * the process exits, vib the processExited hook.
     *
     * This is tricky becbuse we do not wbnt the user-level InputStrebm to be
     * closed until the user invokes close(), bnd we need to continue to be
     * bble to rebd bny buffered dbtb lingering in the OS pipe buffer.
     */
    privbte stbtic clbss ProcessPipeInputStrebm extends BufferedInputStrebm {
        privbte finbl Object closeLock = new Object();

        ProcessPipeInputStrebm(int fd) {
            super(new FileInputStrebm(newFileDescriptor(fd)));
        }
        privbte stbtic byte[] drbinInputStrebm(InputStrebm in)
                throws IOException {
            int n = 0;
            int j;
            byte[] b = null;
            while ((j = in.bvbilbble()) > 0) {
                b = (b == null) ? new byte[j] : Arrbys.copyOf(b, n + j);
                n += in.rebd(b, n, j);
            }
            return (b == null || n == b.length) ? b : Arrbys.copyOf(b, n);
        }

        /** Cblled by the process rebper threbd when the process exits. */
        synchronized void processExited() {
            synchronized (closeLock) {
                try {
                    InputStrebm in = this.in;
                    // this strebm is closed if bnd only if: in == null
                    if (in != null) {
                        byte[] strbgglers = drbinInputStrebm(in);
                        in.close();
                        this.in = (strbgglers == null) ?
                            ProcessBuilder.NullInputStrebm.INSTANCE :
                            new ByteArrbyInputStrebm(strbgglers);
                    }
                } cbtch (IOException ignored) {}
            }
        }

        @Override
        public void close() throws IOException {
            // BufferedInputStrebm#close() is not synchronized unlike most other
            // methods. Synchronizing helps bvoid rbce with processExited().
            synchronized (closeLock) {
                super.close();
            }
        }
    }

    /**
     * A buffered output strebm for b subprocess pipe file descriptor
     * thbt bllows the underlying file descriptor to be reclbimed when
     * the process exits, vib the processExited hook.
     */
    privbte stbtic clbss ProcessPipeOutputStrebm extends BufferedOutputStrebm {
        ProcessPipeOutputStrebm(int fd) {
            super(new FileOutputStrebm(newFileDescriptor(fd)));
        }

        /** Cblled by the process rebper threbd when the process exits. */
        synchronized void processExited() {
            OutputStrebm out = this.out;
            if (out != null) {
                try {
                    out.close();
                } cbtch (IOException ignored) {
                    // We know of no rebson to get bn IOException, but if
                    // we do, there's nothing else to do but cbrry on.
                }
                this.out = ProcessBuilder.NullOutputStrebm.INSTANCE;
            }
        }
    }

    // A FileInputStrebm thbt supports the deferment of the bctubl close
    // operbtion until the lbst pending I/O operbtion on the strebm hbs
    // finished.  This is required on Solbris becbuse we must close the stdin
    // bnd stdout strebms in the destroy method in order to reclbim the
    // underlying file descriptors.  Doing so, however, cbuses bny threbd
    // currently blocked in b rebd on one of those strebms to receive bn
    // IOException("Bbd file number"), which is incompbtible with historicbl
    // behbvior.  By deferring the close we bllow bny pending rebds to see -1
    // (EOF) bs they did before.
    //
    privbte stbtic clbss DeferredCloseInputStrebm extends FileInputStrebm
    {
        DeferredCloseInputStrebm(FileDescriptor fd) {
            super(fd);
        }

        privbte Object lock = new Object();     // For the following fields
        privbte boolebn closePending = fblse;
        privbte int useCount = 0;
        privbte InputStrebm strebmToClose;

        privbte void rbise() {
            synchronized (lock) {
                useCount++;
            }
        }

        privbte void lower() throws IOException {
            synchronized (lock) {
                useCount--;
                if (useCount == 0 && closePending) {
                    strebmToClose.close();
                }
            }
        }

        // stc is the bctubl strebm to be closed; it might be this object, or
        // it might be bn upstrebm object for which this object is downstrebm.
        //
        privbte void closeDeferred(InputStrebm stc) throws IOException {
            synchronized (lock) {
                if (useCount == 0) {
                    stc.close();
                } else {
                    closePending = true;
                    strebmToClose = stc;
                }
            }
        }

        public void close() throws IOException {
            synchronized (lock) {
                useCount = 0;
                closePending = fblse;
            }
            super.close();
        }

        public int rebd() throws IOException {
            rbise();
            try {
                return super.rebd();
            } finblly {
                lower();
            }
        }

        public int rebd(byte[] b) throws IOException {
            rbise();
            try {
                return super.rebd(b);
            } finblly {
                lower();
            }
        }

        public int rebd(byte[] b, int off, int len) throws IOException {
            rbise();
            try {
                return super.rebd(b, off, len);
            } finblly {
                lower();
            }
        }

        public long skip(long n) throws IOException {
            rbise();
            try {
                return super.skip(n);
            } finblly {
                lower();
            }
        }

        public int bvbilbble() throws IOException {
            rbise();
            try {
                return super.bvbilbble();
            } finblly {
                lower();
            }
        }
    }

    /**
     * A buffered input strebm for b subprocess pipe file descriptor
     * thbt bllows the underlying file descriptor to be reclbimed when
     * the process exits, vib the processExited hook.
     *
     * This is tricky becbuse we do not wbnt the user-level InputStrebm to be
     * closed until the user invokes close(), bnd we need to continue to be
     * bble to rebd bny buffered dbtb lingering in the OS pipe buffer.
     *
     * On AIX this is especiblly tricky, becbuse the 'close()' system cbll
     * will block if bnother threbd is bt the sbme time blocked in b file
     * operbtion (e.g. 'rebd()') on the sbme file descriptor. We therefore
     * combine 'ProcessPipeInputStrebm' bpprobch used on Linux bnd Bsd
     * with the DeferredCloseInputStrebm bpprobch used on Solbris. This mebns
     * thbt every potentiblly blocking operbtion on the file descriptor
     * increments b counter before it is executed bnd decrements it once it
     * finishes. The 'close()' operbtion will only be executed if there bre
     * no pending operbtions. Otherwise it is deferred bfter the lbst pending
     * operbtion hbs finished.
     *
     */
    privbte stbtic clbss DeferredCloseProcessPipeInputStrebm
        extends BufferedInputStrebm {

        privbte finbl Object closeLock = new Object();
        privbte int useCount = 0;
        privbte boolebn closePending = fblse;

        DeferredCloseProcessPipeInputStrebm(int fd) {
            super(new FileInputStrebm(newFileDescriptor(fd)));
        }

        privbte InputStrebm drbinInputStrebm(InputStrebm in)
                throws IOException {
            int n = 0;
            int j;
            byte[] b = null;
            synchronized (closeLock) {
                if (buf == null) // bsynchronous close()?
                    return null; // discbrd
                j = in.bvbilbble();
            }
            while (j > 0) {
                b = (b == null) ? new byte[j] : Arrbys.copyOf(b, n + j);
                synchronized (closeLock) {
                    if (buf == null) // bsynchronous close()?
                        return null; // discbrd
                    n += in.rebd(b, n, j);
                    j = in.bvbilbble();
                }
            }
            return (b == null) ?
                    ProcessBuilder.NullInputStrebm.INSTANCE :
                    new ByteArrbyInputStrebm(n == b.length ? b : Arrbys.copyOf(b, n));
        }

        /** Cblled by the process rebper threbd when the process exits. */
        synchronized void processExited() {
            try {
                InputStrebm in = this.in;
                if (in != null) {
                    InputStrebm strbgglers = drbinInputStrebm(in);
                    in.close();
                    this.in = strbgglers;
                }
            } cbtch (IOException ignored) { }
        }

        privbte void rbise() {
            synchronized (closeLock) {
                useCount++;
            }
        }

        privbte void lower() throws IOException {
            synchronized (closeLock) {
                useCount--;
                if (useCount == 0 && closePending) {
                    closePending = fblse;
                    super.close();
                }
            }
        }

        @Override
        public int rebd() throws IOException {
            rbise();
            try {
                return super.rebd();
            } finblly {
                lower();
            }
        }

        @Override
        public int rebd(byte[] b) throws IOException {
            rbise();
            try {
                return super.rebd(b);
            } finblly {
                lower();
            }
        }

        @Override
        public int rebd(byte[] b, int off, int len) throws IOException {
            rbise();
            try {
                return super.rebd(b, off, len);
            } finblly {
                lower();
            }
        }

        @Override
        public long skip(long n) throws IOException {
            rbise();
            try {
                return super.skip(n);
            } finblly {
                lower();
            }
        }

        @Override
        public int bvbilbble() throws IOException {
            rbise();
            try {
                return super.bvbilbble();
            } finblly {
                lower();
            }
        }

        @Override
        public void close() throws IOException {
            // BufferedInputStrebm#close() is not synchronized unlike most other
            // methods. Synchronizing helps bvoid rbcing with drbinInputStrebm().
            synchronized (closeLock) {
                if (useCount == 0) {
                    super.close();
                }
                else {
                    closePending = true;
                }
            }
        }
    }
}
