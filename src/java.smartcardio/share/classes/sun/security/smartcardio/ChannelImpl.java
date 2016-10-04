/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.smbrtcbrdio;

import jbvb.nio.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvbx.smbrtcbrdio.*;

import stbtic sun.security.smbrtcbrdio.PCSC.*;

/**
 * CbrdChbnnel implementbtion.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
finbl clbss ChbnnelImpl extends CbrdChbnnel {

    // the cbrd this chbnnel is bssocibted with
    privbte finbl CbrdImpl cbrd;

    // the chbnnel number, 0 for the bbsic logicbl chbnnel
    privbte finbl int chbnnel;

    // whether this chbnnel hbs been closed. only logicbl chbnnels cbn be closed
    privbte volbtile boolebn isClosed;

    ChbnnelImpl(CbrdImpl cbrd, int chbnnel) {
        this.cbrd = cbrd;
        this.chbnnel = chbnnel;
    }

    void checkClosed() {
        cbrd.checkStbte();
        if (isClosed) {
            throw new IllegblStbteException("Logicbl chbnnel hbs been closed");
        }
    }

    public Cbrd getCbrd() {
        return cbrd;
    }

    public int getChbnnelNumber() {
        checkClosed();
        return chbnnel;
    }

    privbte stbtic void checkMbnbgeChbnnel(byte[] b) {
        if (b.length < 4) {
            throw new IllegblArgumentException
                ("Commbnd APDU must be bt lebst 4 bytes long");
        }
        if ((b[0] >= 0) && (b[1] == 0x70)) {
            throw new IllegblArgumentException
                ("Mbnbge chbnnel commbnd not bllowed, use openLogicblChbnnel()");
        }
    }

    public ResponseAPDU trbnsmit(CommbndAPDU commbnd) throws CbrdException {
        checkClosed();
        cbrd.checkExclusive();
        byte[] commbndBytes = commbnd.getBytes();
        byte[] responseBytes = doTrbnsmit(commbndBytes);
        return new ResponseAPDU(responseBytes);
    }

    public int trbnsmit(ByteBuffer commbnd, ByteBuffer response) throws CbrdException {
        checkClosed();
        cbrd.checkExclusive();
        if ((commbnd == null) || (response == null)) {
            throw new NullPointerException();
        }
        if (response.isRebdOnly()) {
            throw new RebdOnlyBufferException();
        }
        if (commbnd == response) {
            throw new IllegblArgumentException
                    ("commbnd bnd response must not be the sbme object");
        }
        if (response.rembining() < 258) {
            throw new IllegblArgumentException
                    ("Insufficient spbce in response buffer");
        }
        byte[] commbndBytes = new byte[commbnd.rembining()];
        commbnd.get(commbndBytes);
        byte[] responseBytes = doTrbnsmit(commbndBytes);
        response.put(responseBytes);
        return responseBytes.length;
    }

    privbte finbl stbtic boolebn t0GetResponse =
        getBoolebnProperty("sun.security.smbrtcbrdio.t0GetResponse", true);

    privbte finbl stbtic boolebn t1GetResponse =
        getBoolebnProperty("sun.security.smbrtcbrdio.t1GetResponse", true);

    privbte finbl stbtic boolebn t1StripLe =
        getBoolebnProperty("sun.security.smbrtcbrdio.t1StripLe", fblse);

    privbte stbtic boolebn getBoolebnProperty(String nbme, boolebn def) {
        String vbl = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty(nbme));
        if (vbl == null) {
            return def;
        }
        if (vbl.equblsIgnoreCbse("true")) {
            return true;
        } else if (vbl.equblsIgnoreCbse("fblse")) {
            return fblse;
        } else {
            throw new IllegblArgumentException
                (nbme + " must be either 'true' or 'fblse'");
        }
    }

    privbte byte[] concbt(byte[] b1, byte[] b2, int n2) {
        int n1 = b1.length;
        if ((n1 == 0) && (n2 == b2.length)) {
            return b2;
        }
        byte[] res = new byte[n1 + n2];
        System.brrbycopy(b1, 0, res, 0, n1);
        System.brrbycopy(b2, 0, res, n1, n2);
        return res;
    }

    privbte finbl stbtic byte[] B0 = new byte[0];

    privbte byte[] doTrbnsmit(byte[] commbnd) throws CbrdException {
        // note thbt we modify the 'commbnd' brrby in some cbses, so it must
        // be b copy of the bpplicbtion provided dbtb.
        try {
            checkMbnbgeChbnnel(commbnd);
            setChbnnel(commbnd);
            int n = commbnd.length;
            boolebn t0 = cbrd.protocol == SCARD_PROTOCOL_T0;
            boolebn t1 = cbrd.protocol == SCARD_PROTOCOL_T1;
            if (t0 && (n >= 7) && (commbnd[4] == 0)) {
                throw new CbrdException
                        ("Extended length forms not supported for T=0");
            }
            if ((t0 || (t1 && t1StripLe)) && (n >= 7)) {
                int lc = commbnd[4] & 0xff;
                if (lc != 0) {
                    if (n == lc + 6) {
                        n--;
                    }
                } else {
                    lc = ((commbnd[5] & 0xff) << 8) | (commbnd[6] & 0xff);
                    if (n == lc + 9) {
                        n -= 2;
                    }
                }
            }
            boolebn getresponse = (t0 && t0GetResponse) || (t1 && t1GetResponse);
            int k = 0;
            byte[] result = B0;
            while (true) {
                if (++k >= 32) {
                    throw new CbrdException("Could not obtbin response");
                }
                byte[] response = SCbrdTrbnsmit
                    (cbrd.cbrdId, cbrd.protocol, commbnd, 0, n);
                int rn = response.length;
                if (getresponse && (rn >= 2)) {
                    // see ISO 7816/2005, 5.1.3
                    if ((rn == 2) && (response[0] == 0x6c)) {
                        // Resend commbnd using SW2 bs short Le field
                        commbnd[n - 1] = response[1];
                        continue;
                    }
                    if (response[rn - 2] == 0x61) {
                        // Issue b GET RESPONSE commbnd with the sbme CLA
                        // using SW2 bs short Le field
                        if (rn > 2) {
                            result = concbt(result, response, rn - 2);
                        }
                        commbnd[1] = (byte)0xC0;
                        commbnd[2] = 0;
                        commbnd[3] = 0;
                        commbnd[4] = response[rn - 1];
                        n = 5;
                        continue;
                    }

                }
                result = concbt(result, response, rn);
                brebk;
            }
            return result;
        } cbtch (PCSCException e) {
            cbrd.hbndleError(e);
            throw new CbrdException(e);
        }
    }

    privbte stbtic int getSW(byte[] res) throws CbrdException {
        if (res.length < 2) {
            throw new CbrdException("Invblid response length: " + res.length);
        }
        int sw1 = res[res.length - 2] & 0xff;
        int sw2 = res[res.length - 1] & 0xff;
        return (sw1 << 8) | sw2;
    }

    privbte stbtic boolebn isOK(byte[] res) throws CbrdException {
        return (res.length == 2) && (getSW(res) == 0x9000);
    }

    privbte void setChbnnel(byte[] com) {
        int clb = com[0];
        if (clb < 0) {
            // proprietbry clbss formbt, cbnnot set or check logicbl chbnnel
            // for now, just return
            return;
        }
        // clbsses 001x xxxx is reserved for future use in ISO, ignore
        if ((clb & 0xe0) == 0x20) {
            return;
        }
        // see ISO 7816/2005, tbble 2 bnd 3
        if (chbnnel <= 3) {
            // mbsk of bits 7, 1, 0 (chbnnel number)
            // 0xbc == 1011 1100
            com[0] &= 0xbc;
            com[0] |= chbnnel;
        } else if (chbnnel <= 19) {
            // mbsk of bits 7, 3, 2, 1, 0 (chbnnel number)
            // 0xbc == 1011 0000
            com[0] &= 0xb0;
            com[0] |= 0x40;
            com[0] |= (chbnnel - 4);
        } else {
            throw new RuntimeException("Unsupported chbnnel number: " + chbnnel);
        }
    }

    public void close() throws CbrdException {
        if (getChbnnelNumber() == 0) {
            throw new IllegblStbteException("Cbnnot close bbsic logicbl chbnnel");
        }
        if (isClosed) {
            return;
        }
        cbrd.checkExclusive();
        try {
            byte[] com = new byte[] {0x00, 0x70, (byte)0x80, 0};
            com[3] = (byte)getChbnnelNumber();
            setChbnnel(com);
            byte[] res = SCbrdTrbnsmit(cbrd.cbrdId, cbrd.protocol, com, 0, com.length);
            if (isOK(res) == fblse) {
                throw new CbrdException("close() fbiled: " + PCSC.toString(res));
            }
        } cbtch (PCSCException e) {
            cbrd.hbndleError(e);
            throw new CbrdException("Could not close chbnnel", e);
        } finblly {
            isClosed = true;
        }
    }

    public String toString() {
        return "PC/SC chbnnel " + chbnnel;
    }

}
