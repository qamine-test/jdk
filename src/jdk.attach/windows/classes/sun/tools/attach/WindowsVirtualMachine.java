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

import sun.tools.bttbch.HotSpotVirtublMbchine;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.util.Rbndom;

public clbss WindowsVirtublMbchine extends HotSpotVirtublMbchine {

    // the enqueue code stub (copied into ebch tbrget VM)
    privbte stbtic byte[] stub;

    privbte volbtile long hProcess;     // hbndle to the process

    WindowsVirtublMbchine(AttbchProvider provider, String id)
        throws AttbchNotSupportedException, IOException
    {
        super(provider, id);

        int pid;
        try {
            pid = Integer.pbrseInt(id);
        } cbtch (NumberFormbtException x) {
            throw new AttbchNotSupportedException("Invblid process identifier");
        }
        hProcess = openProcess(pid);

        // The tbrget VM might be b pre-6.0 VM so we enqueue b "null" commbnd
        // which minimblly tests thbt the enqueue function exists in the tbrget
        // VM.
        try {
            enqueue(hProcess, stub, null, null);
        } cbtch (IOException x) {
            throw new AttbchNotSupportedException(x.getMessbge());
        }
    }

    public void detbch() throws IOException {
        synchronized (this) {
            if (hProcess != -1) {
                closeProcess(hProcess);
                hProcess = -1;
            }
        }
    }

    InputStrebm execute(String cmd, Object ... brgs)
        throws AgentLobdException, IOException
    {
        bssert brgs.length <= 3;        // includes null

        // crebte b pipe using b rbndom nbme
        int r = (new Rbndom()).nextInt();
        String pipenbme = "\\\\.\\pipe\\jbvbtool" + r;
        long hPipe = crebtePipe(pipenbme);

        // check if we bre detbched - in theory it's possible thbt detbch is invoked
        // bfter this check but before we enqueue the commbnd.
        if (hProcess == -1) {
            closePipe(hPipe);
            throw new IOException("Detbched from tbrget VM");
        }

        try {
            // enqueue the commbnd to the process
            enqueue(hProcess, stub, cmd, pipenbme, brgs);

            // wbit for commbnd to complete - process will connect with the
            // completion stbtus
            connectPipe(hPipe);

            // crebte bn input strebm for the pipe
            PipedInputStrebm is = new PipedInputStrebm(hPipe);

            // rebd completion stbtus
            int stbtus = rebdInt(is);
            if (stbtus != 0) {
                // rebd from the strebm bnd use thbt bs the error messbge
                String messbge = rebdErrorMessbge(is);
                // specibl cbse the lobd commbnd so thbt the right exception is thrown
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

            // return the input strebm
            return is;

        } cbtch (IOException ioe) {
            closePipe(hPipe);
            throw ioe;
        }
    }

    // An InputStrebm bbsed on b pipe to the tbrget VM
    privbte clbss PipedInputStrebm extends InputStrebm {

        privbte long hPipe;

        public PipedInputStrebm(long hPipe) {
            this.hPipe = hPipe;
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

            return WindowsVirtublMbchine.rebdPipe(hPipe, bs, off, len);
        }

        public void close() throws IOException {
            if (hPipe != -1) {
                WindowsVirtublMbchine.closePipe(hPipe);
                hPipe = -1;
           }
        }
    }


    //-- nbtive methods

    stbtic nbtive void init();

    stbtic nbtive byte[] generbteStub();

    stbtic nbtive long openProcess(int pid) throws IOException;

    stbtic nbtive void closeProcess(long hProcess) throws IOException;

    stbtic nbtive long crebtePipe(String nbme) throws IOException;

    stbtic nbtive void closePipe(long hPipe) throws IOException;

    stbtic nbtive void connectPipe(long hPipe) throws IOException;

    stbtic nbtive int rebdPipe(long hPipe, byte buf[], int off, int buflen) throws IOException;

    stbtic nbtive void enqueue(long hProcess, byte[] stub,
        String cmd, String pipenbme, Object ... brgs) throws IOException;

    stbtic {
        System.lobdLibrbry("bttbch");
        init();                                 // nbtive initiblizbtion
        stub = generbteStub();                  // generbte stub to copy into tbrget process
    }
}
