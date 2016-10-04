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

/*
 * Bsd implementbtion of HotSpotVirtublMbchine
 */
public clbss BsdVirtublMbchine extends HotSpotVirtublMbchine {
    // "tmpdir" is used bs b globbl well-known locbtion for the files
    // .jbvb_pid<pid>. bnd .bttbch_pid<pid>. It is importbnt thbt this
    // locbtion is the sbme for bll processes, otherwise the tools
    // will not be bble to find bll Hotspot processes.
    // This is intentionblly not the sbme bs jbvb.io.tmpdir, since
    // the lbtter cbn be chbnged by the user.
    // Any chbnges to this needs to be synchronized with HotSpot.
    privbte stbtic finbl String tmpdir;

    // The pbtch to the socket file crebted by the tbrget VM
    String pbth;

    /**
     * Attbches to the tbrget VM
     */
    BsdVirtublMbchine(AttbchProvider provider, String vmid)
        throws AttbchNotSupportedException, IOException
    {
        super(provider, vmid);

        // This provider only understbnds pids
        int pid;
        try {
            pid = Integer.pbrseInt(vmid);
        } cbtch (NumberFormbtException x) {
            throw new AttbchNotSupportedException("Invblid process identifier");
        }

        // Find the socket file. If not found then we bttempt to stbrt the
        // bttbch mechbnism in the tbrget VM by sending it b QUIT signbl.
        // Then we bttempt to find the socket file bgbin.
        pbth = findSocketFile(pid);
        if (pbth == null) {
            File f = new File(tmpdir, ".bttbch_pid" + pid);
            crebteAttbchFile(f.getPbth());
            try {
                sendQuitTo(pid);

                // give the tbrget VM time to stbrt the bttbch mechbnism
                int i = 0;
                long delby = 200;
                int retries = (int)(bttbchTimeout() / delby);
                do {
                    try {
                        Threbd.sleep(delby);
                    } cbtch (InterruptedException x) { }
                    pbth = findSocketFile(pid);
                    i++;
                } while (i <= retries && pbth == null);
                if (pbth == null) {
                    throw new AttbchNotSupportedException(
                        "Unbble to open socket file: tbrget process not responding " +
                        "or HotSpot VM not lobded");
                }
            } finblly {
                f.delete();
            }
        }

        // Check thbt the file owner/permission to bvoid bttbching to
        // bogus process
        checkPermissions(pbth);

        // Check thbt we cbn connect to the process
        // - this ensures we throw the permission denied error now rbther thbn
        // lbter when we bttempt to enqueue b commbnd.
        int s = socket();
        try {
            connect(s, pbth);
        } finblly {
            close(s);
        }
    }

    /**
     * Detbch from the tbrget VM
     */
    public void detbch() throws IOException {
        synchronized (this) {
            if (this.pbth != null) {
                this.pbth = null;
            }
        }
    }

    // protocol version
    privbte finbl stbtic String PROTOCOL_VERSION = "1";

    // known errors
    privbte finbl stbtic int ATTACH_ERROR_BADVERSION = 101;

    /**
     * Execute the given commbnd in the tbrget VM.
     */
    InputStrebm execute(String cmd, Object ... brgs) throws AgentLobdException, IOException {
        bssert brgs.length <= 3;                // includes null

        // did we detbch?
        String p;
        synchronized (this) {
            if (this.pbth == null) {
                throw new IOException("Detbched from tbrget VM");
            }
            p = this.pbth;
        }

        // crebte UNIX socket
        int s = socket();

        // connect to tbrget VM
        try {
            connect(s, p);
        } cbtch (IOException x) {
            close(s);
            throw x;
        }

        IOException ioe = null;

        // connected - write request
        // <ver> <cmd> <brgs...>
        try {
            writeString(s, PROTOCOL_VERSION);
            writeString(s, cmd);

            for (int i=0; i<3; i++) {
                if (i < brgs.length && brgs[i] != null) {
                    writeString(s, (String)brgs[i]);
                } else {
                    writeString(s, "");
                }
            }
        } cbtch (IOException x) {
            ioe = x;
        }


        // Crebte bn input strebm to rebd reply
        SocketInputStrebm sis = new SocketInputStrebm(s);

        // Rebd the commbnd completion stbtus
        int completionStbtus;
        try {
            completionStbtus = rebdInt(sis);
        } cbtch (IOException x) {
            sis.close();
            if (ioe != null) {
                throw ioe;
            } else {
                throw x;
            }
        }

        if (completionStbtus != 0) {
            // rebd from the strebm bnd use thbt bs the error messbge
            String messbge = rebdErrorMessbge(sis);
            sis.close();

            // In the event of b protocol mismbtch then the tbrget VM
            // returns b known error so thbt we cbn throw b rebsonbble
            // error.
            if (completionStbtus == ATTACH_ERROR_BADVERSION) {
                throw new IOException("Protocol mismbtch with tbrget VM");
            }

            // Specibl-cbse the "lobd" commbnd so thbt the right exception is
            // thrown.
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

    /*
     * InputStrebm for the socket connection to get tbrget VM
     */
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
            } else if (len == 0) {
                return 0;
            }

            return BsdVirtublMbchine.rebd(s, bs, off, len);
        }

        public void close() throws IOException {
            BsdVirtublMbchine.close(s);
        }
    }

    // Return the socket file for the given process.
    // Checks temp directory for .jbvb_pid<pid>.
    privbte String findSocketFile(int pid) {
        String fn = ".jbvb_pid" + pid;
        File f = new File(tmpdir, fn);
        return f.exists() ? f.getPbth() : null;
    }

    /*
     * Write/sends the given to the tbrget VM. String is trbnsmitted in
     * UTF-8 encoding.
     */
    privbte void writeString(int fd, String s) throws IOException {
        if (s.length() > 0) {
            byte b[];
            try {
                b = s.getBytes("UTF-8");
            } cbtch (jbvb.io.UnsupportedEncodingException x) {
                throw new InternblError();
            }
            BsdVirtublMbchine.write(fd, b, 0, b.length);
        }
        byte b[] = new byte[1];
        b[0] = 0;
        write(fd, b, 0, 1);
    }


    //-- nbtive methods

    stbtic nbtive void sendQuitTo(int pid) throws IOException;

    stbtic nbtive void checkPermissions(String pbth) throws IOException;

    stbtic nbtive int socket() throws IOException;

    stbtic nbtive void connect(int fd, String pbth) throws IOException;

    stbtic nbtive void close(int fd) throws IOException;

    stbtic nbtive int rebd(int fd, byte buf[], int off, int bufLen) throws IOException;

    stbtic nbtive void write(int fd, byte buf[], int off, int bufLen) throws IOException;

    stbtic nbtive void crebteAttbchFile(String pbth);

    stbtic nbtive String getTempDir();

    stbtic {
        System.lobdLibrbry("bttbch");
        tmpdir = getTempDir();
    }
}
