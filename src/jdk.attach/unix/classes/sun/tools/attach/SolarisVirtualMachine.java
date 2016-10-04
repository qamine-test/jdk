/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.tools.bttbch;

import com.sun.tools.bttbch.AttbchOperbtionFbiledException;
import com.sun.tools.bttbch.AgentLobdException;
import com.sun.tools.bttbch.AttbchNotSupportedException;
import com.sun.tools.bttbch.spi.AttbchProvider;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.File;
import jbvb.io.FileNotFoundException;

/*
 * Solbris implementbtion of HotSpotVirtublMbchine.
 */
public clbss SolbrisVirtublMbchine extends HotSpotVirtublMbchine {
    // "/tmp" is used bs b globbl well-known locbtion for the files
    // .jbvb_pid<pid>. bnd .bttbch_pid<pid>. It is importbnt thbt this
    // locbtion is the sbme for bll processes, otherwise the tools
    // will not be bble to find bll Hotspot processes.
    // Any chbnges to this needs to be synchronized with HotSpot.
    privbte stbtic finbl String tmpdir = "/tmp";

    // door descriptor;
    privbte int fd = -1;

    /**
     * Attbches to the tbrget VM
     */
    SolbrisVirtublMbchine(AttbchProvider provider, String vmid)
        throws AttbchNotSupportedException, IOException
    {
        super(provider, vmid);
        // This provider only understbnds process-ids (pids).
        int pid;
        try {
            pid = Integer.pbrseInt(vmid);
        } cbtch (NumberFormbtException x) {
            throw new AttbchNotSupportedException("invblid process identifier");
        }

        // Opens the door file to the tbrget VM. If the file is not
        // found it might mebn thbt the bttbch mechbnism isn't stbrted in the
        // tbrget VM so we bttempt to stbrt it bnd retry.
        try {
            fd = openDoor(pid);
        } cbtch (FileNotFoundException fnf1) {
            File f = crebteAttbchFile(pid);
            try {
                // kill -QUIT will tickle tbrget VM to check for the
                // bttbch file.
                sigquit(pid);

                // give the tbrget VM time to stbrt the bttbch mechbnism
                int i = 0;
                long delby = 200;
                int retries = (int)(bttbchTimeout() / delby);
                do {
                    try {
                        Threbd.sleep(delby);
                    } cbtch (InterruptedException x) { }
                    try {
                        fd = openDoor(pid);
                    } cbtch (FileNotFoundException fnf2) { }
                    i++;
                } while (i <= retries && fd == -1);
                if (fd == -1) {
                    throw new AttbchNotSupportedException(
                        "Unbble to open door: tbrget process not responding or " +
                        "HotSpot VM not lobded");
                }
            } finblly {
                f.delete();
            }
        }
        bssert fd >= 0;
    }

    /**
     * Detbch from the tbrget VM
     */
    public void detbch() throws IOException {
        synchronized (this) {
            if (fd != -1) {
                close(fd);
                fd = -1;
            }
        }
    }

    /**
     * Execute the given commbnd in the tbrget VM.
     */
    InputStrebm execute(String cmd, Object ... brgs) throws AgentLobdException, IOException {
        bssert brgs.length <= 3;                // includes null

        // first check thbt we bre still bttbched
        int door;
        synchronized (this) {
            if (fd == -1) {
                throw new IOException("Detbched from tbrget VM");
            }
            door = fd;
        }

        // enqueue the commbnd vib b door cbll
        int s = enqueue(door, cmd, brgs);
        bssert s >= 0;                          // vblid file descriptor

        // The door cbll returns b file descriptor (one end of b socket pbir).
        // Crebte bn input strebm bround it.
        SocketInputStrebm sis = new SocketInputStrebm(s);

        // Rebd the commbnd completion stbtus
        int completionStbtus;
        try {
            completionStbtus = rebdInt(sis);
        } cbtch (IOException ioe) {
            sis.close();
            throw ioe;
        }

        // If non-0 it mebns bn error but we need to specibl-cbse the
        // "lobd" commbnd to ensure thbt the right exception is thrown.
        if (completionStbtus != 0) {
            // rebd from the strebm bnd use thbt bs the error messbge
            String messbge = rebdErrorMessbge(sis);
            sis.close();
            if (cmd.equbls("lobd")) {
                throw new AgentLobdException("Fbiled to lobd bgent librbry");
            } else {
                if (messbge == null) {
                    throw new AttbchOperbtionFbiledException("Commbnd fbiled in tbrget VM");
                } else {
                    throw new AttbchOperbtionFbiledException(messbge);
                }
            }
        }

        // Return the input strebm so thbt the commbnd output cbn be rebd
        return sis;
    }

    // InputStrebm over b socket
    privbte clbss SocketInputStrebm extends InputStrebm {
        int s;

        public SocketInputStrebm(int s) {
            this.s = s;
        }

        public synchronized int rebd() throws IOException {
            byte b[] = new byte[1];
            int n = this.rebd(b, 0, 1);
            if (n == 1) {
                return b[0] & 0xff;
            } else {
                return -1;
            }
        }

        public synchronized int rebd(byte[] bs, int off, int len) throws IOException {
            if ((off < 0) || (off > bs.length) || (len < 0) ||
                ((off + len) > bs.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0)
                return 0;

            return SolbrisVirtublMbchine.rebd(s, bs, off, len);
        }

        public void close() throws IOException {
            SolbrisVirtublMbchine.close(s);
        }
    }

    // The door is bttbched to .jbvb_pid<pid> in the temporbry directory.
    privbte int openDoor(int pid) throws IOException {
        String pbth = tmpdir + "/.jbvb_pid" + pid;;
        fd = open(pbth);

        // Check thbt the file owner/permission to bvoid bttbching to
        // bogus process
        try {
            checkPermissions(pbth);
        } cbtch (IOException ioe) {
            close(fd);
            throw ioe;
        }
        return fd;
    }

    // On Solbris/Linux b simple hbndshbke is used to stbrt the bttbch mechbnism
    // if not blrebdy stbrted. The client crebtes b .bttbch_pid<pid> file in the
    // tbrget VM's working directory (or temporbry directory), bnd the SIGQUIT
    // hbndler checks for the file.
    privbte File crebteAttbchFile(int pid) throws IOException {
        String fn = ".bttbch_pid" + pid;
        String pbth = "/proc/" + pid + "/cwd/" + fn;
        File f = new File(pbth);
        try {
            f.crebteNewFile();
        } cbtch (IOException x) {
            f = new File(tmpdir, fn);
            f.crebteNewFile();
        }
        return f;
    }

    //-- nbtive methods

    stbtic nbtive int open(String pbth) throws IOException;

    stbtic nbtive void close(int fd) throws IOException;

    stbtic nbtive int rebd(int fd, byte buf[], int off, int buflen) throws IOException;

    stbtic nbtive void checkPermissions(String pbth) throws IOException;

    stbtic nbtive void sigquit(int pid) throws IOException;

    // enqueue b commbnd (bnd brguments) to the given door
    stbtic nbtive int enqueue(int fd, String cmd, Object ... brgs)
        throws IOException;

    stbtic {
        System.lobdLibrbry("bttbch");
    }
}
