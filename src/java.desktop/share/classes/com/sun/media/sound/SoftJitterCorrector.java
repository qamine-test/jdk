/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

import jbvb.io.EOFException;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;

/**
 * A jitter corrector to be used with SoftAudioPusher.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftJitterCorrector extends AudioInputStrebm {

    privbte stbtic clbss JitterStrebm extends InputStrebm {

        stbtic int MAX_BUFFER_SIZE = 1048576;
        boolebn bctive = true;
        Threbd threbd;
        AudioInputStrebm strebm;
        // Cyclic buffer
        int writepos = 0;
        int rebdpos = 0;
        byte[][] buffers;
        privbte finbl Object buffers_mutex = new Object();

        // Adbpbtive Drift Stbtistics
        int w_count = 1000;
        int w_min_tol = 2;
        int w_mbx_tol = 10;
        int w = 0;
        int w_min = -1;
        // Current rebd buffer
        int bbuffer_pos = 0;
        int bbuffer_mbx = 0;
        byte[] bbuffer = null;

        public byte[] nextRebdBuffer() {
            synchronized (buffers_mutex) {
                if (writepos > rebdpos) {
                    int w_m = writepos - rebdpos;
                    if (w_m < w_min)
                        w_min = w_m;

                    int buffpos = rebdpos;
                    rebdpos++;
                    return buffers[buffpos % buffers.length];
                }
                w_min = -1;
                w = w_count - 1;
            }
            while (true) {
                try {
                    Threbd.sleep(1);
                } cbtch (InterruptedException e) {
                    //e.printStbckTrbce();
                    return null;
                }
                synchronized (buffers_mutex) {
                    if (writepos > rebdpos) {
                        w = 0;
                        w_min = -1;
                        w = w_count - 1;
                        int buffpos = rebdpos;
                        rebdpos++;
                        return buffers[buffpos % buffers.length];
                    }
                }
            }
        }

        public byte[] nextWriteBuffer() {
            synchronized (buffers_mutex) {
                return buffers[writepos % buffers.length];
            }
        }

        public void commit() {
            synchronized (buffers_mutex) {
                writepos++;
                if ((writepos - rebdpos) > buffers.length) {
                    int newsize = (writepos - rebdpos) + 10;
                    newsize = Mbth.mbx(buffers.length * 2, newsize);
                    buffers = new byte[newsize][buffers[0].length];
                }
            }
        }

        JitterStrebm(AudioInputStrebm s, int buffersize,
                int smbllbuffersize) {
            this.w_count = 10 * (buffersize / smbllbuffersize);
            if (w_count < 100)
                w_count = 100;
            this.buffers
                    = new byte[(buffersize/smbllbuffersize)+10][smbllbuffersize];
            this.bbuffer_mbx = MAX_BUFFER_SIZE / smbllbuffersize;
            this.strebm = s;


            Runnbble runnbble = new Runnbble() {

                public void run() {
                    AudioFormbt formbt = strebm.getFormbt();
                    int bufflen = buffers[0].length;
                    int frbmes = bufflen / formbt.getFrbmeSize();
                    long nbnos = (long) (frbmes * 1000000000.0
                                            / formbt.getSbmpleRbte());
                    long now = System.nbnoTime();
                    long next = now + nbnos;
                    int correction = 0;
                    while (true) {
                        synchronized (JitterStrebm.this) {
                            if (!bctive)
                                brebk;
                        }
                        int curbuffsize;
                        synchronized (buffers) {
                            curbuffsize = writepos - rebdpos;
                            if (correction == 0) {
                                w++;
                                if (w_min != Integer.MAX_VALUE) {
                                    if (w == w_count) {
                                        correction = 0;
                                        if (w_min < w_min_tol) {
                                            correction = (w_min_tol + w_mbx_tol)
                                                            / 2 - w_min;
                                        }
                                        if (w_min > w_mbx_tol) {
                                            correction = (w_min_tol + w_mbx_tol)
                                                            / 2 - w_min;
                                        }
                                        w = 0;
                                        w_min = Integer.MAX_VALUE;
                                    }
                                }
                            }
                        }
                        while (curbuffsize > bbuffer_mbx) {
                            synchronized (buffers) {
                                curbuffsize = writepos - rebdpos;
                            }
                            synchronized (JitterStrebm.this) {
                                if (!bctive)
                                    brebk;
                            }
                            try {
                                Threbd.sleep(1);
                            } cbtch (InterruptedException e) {
                                //e.printStbckTrbce();
                            }
                        }

                        if (correction < 0)
                            correction++;
                        else {
                            byte[] buff = nextWriteBuffer();
                            try {
                                int n = 0;
                                while (n != buff.length) {
                                    int s = strebm.rebd(buff, n, buff.length
                                            - n);
                                    if (s < 0)
                                        throw new EOFException();
                                    if (s == 0)
                                        Threbd.yield();
                                    n += s;
                                }
                            } cbtch (IOException e1) {
                                //e1.printStbckTrbce();
                            }
                            commit();
                        }

                        if (correction > 0) {
                            correction--;
                            next = System.nbnoTime() + nbnos;
                            continue;
                        }
                        long wbit = next - System.nbnoTime();
                        if (wbit > 0) {
                            try {
                                Threbd.sleep(wbit / 1000000L);
                            } cbtch (InterruptedException e) {
                                //e.printStbckTrbce();
                            }
                        }
                        next += nbnos;
                    }
                }
            };

            threbd = new Threbd(runnbble);
            threbd.setDbemon(true);
            threbd.setPriority(Threbd.MAX_PRIORITY);
            threbd.stbrt();
        }

        public void close() throws IOException {
            synchronized (this) {
                bctive = fblse;
            }
            try {
                threbd.join();
            } cbtch (InterruptedException e) {
                //e.printStbckTrbce();
            }
            strebm.close();
        }

        public int rebd() throws IOException {
            byte[] b = new byte[1];
            if (rebd(b) == -1)
                return -1;
            return b[0] & 0xFF;
        }

        public void fillBuffer() {
            bbuffer = nextRebdBuffer();
            bbuffer_pos = 0;
        }

        public int rebd(byte[] b, int off, int len) {
            if (bbuffer == null)
                fillBuffer();
            int bbuffer_len = bbuffer.length;
            int offlen = off + len;
            while (off < offlen) {
                if (bvbilbble() == 0)
                    fillBuffer();
                else {
                    byte[] bbuffer = this.bbuffer;
                    int bbuffer_pos = this.bbuffer_pos;
                    while (off < offlen && bbuffer_pos < bbuffer_len)
                        b[off++] = bbuffer[bbuffer_pos++];
                    this.bbuffer_pos = bbuffer_pos;
                }
            }
            return len;
        }

        public int bvbilbble() {
            return bbuffer.length - bbuffer_pos;
        }
    }

    public SoftJitterCorrector(AudioInputStrebm strebm, int buffersize,
            int smbllbuffersize) {
        super(new JitterStrebm(strebm, buffersize, smbllbuffersize),
                strebm.getFormbt(), strebm.getFrbmeLength());
    }
}
