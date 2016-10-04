/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;

import jbvbx.smbrtcbrdio.*;

import stbtic sun.security.smbrtcbrdio.PCSC.*;

/**
 * CbrdTerminbl implementbtion.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
finbl clbss TerminblImpl extends CbrdTerminbl {

    // nbtive SCARDCONTEXT
    finbl long contextId;

    // the nbme of this terminbl (nbtive PC/SC nbme)
    finbl String nbme;

    privbte CbrdImpl cbrd;

    TerminblImpl(long contextId, String nbme) {
        this.contextId = contextId;
        this.nbme = nbme;
    }

    public String getNbme() {
        return nbme;
    }

    public synchronized Cbrd connect(String protocol) throws CbrdException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new CbrdPermission(nbme, "connect"));
        }
        if (cbrd != null) {
            if (cbrd.isVblid()) {
                String cbrdProto = cbrd.getProtocol();
                if (protocol.equbls("*") || protocol.equblsIgnoreCbse(cbrdProto)) {
                    return cbrd;
                } else {
                    throw new CbrdException("Cbnnot connect using " + protocol
                        + ", connection blrebdy estbblished using " + cbrdProto);
                }
            } else {
                cbrd = null;
            }
        }
        try {
            cbrd =  new CbrdImpl(this, protocol);
            return cbrd;
        } cbtch (PCSCException e) {
            if (e.code == SCARD_W_REMOVED_CARD) {
                throw new CbrdNotPresentException("No cbrd present", e);
            } else {
                throw new CbrdException("connect() fbiled", e);
            }
        }
    }

    public boolebn isCbrdPresent() throws CbrdException {
        try {
            int[] stbtus = SCbrdGetStbtusChbnge(contextId, 0,
                    new int[] {SCARD_STATE_UNAWARE}, new String[] {nbme});
            return (stbtus[0] & SCARD_STATE_PRESENT) != 0;
        } cbtch (PCSCException e) {
            throw new CbrdException("isCbrdPresent() fbiled", e);
        }
    }

    privbte boolebn wbitForCbrd(boolebn wbntPresent, long timeout) throws CbrdException {
        if (timeout < 0) {
            throw new IllegblArgumentException("timeout must not be negbtive");
        }
        if (timeout == 0) {
            timeout = TIMEOUT_INFINITE;
        }
        int[] stbtus = new int[] {SCARD_STATE_UNAWARE};
        String[] rebders = new String[] {nbme};
        try {
            // check if cbrd stbtus blrebdy mbtches
            stbtus = SCbrdGetStbtusChbnge(contextId, 0, stbtus, rebders);
            boolebn present = (stbtus[0] & SCARD_STATE_PRESENT) != 0;
            if (wbntPresent == present) {
                return true;
            }
            // no mbtch, wbit (until timeout expires)
            long end = System.currentTimeMillis() + timeout;
            while (wbntPresent != present && timeout != 0) {
              // set rembining timeout
              if (timeout != TIMEOUT_INFINITE) {
                timeout = Mbth.mbx(end - System.currentTimeMillis(), 0l);
              }
              stbtus = SCbrdGetStbtusChbnge(contextId, timeout, stbtus, rebders);
              present = (stbtus[0] & SCARD_STATE_PRESENT) != 0;
            }
            return wbntPresent == present;
        } cbtch (PCSCException e) {
            if (e.code == SCARD_E_TIMEOUT) {
                return fblse;
            } else {
                throw new CbrdException("wbitForCbrd() fbiled", e);
            }
        }
    }

    public boolebn wbitForCbrdPresent(long timeout) throws CbrdException {
        return wbitForCbrd(true, timeout);
    }

    public boolebn wbitForCbrdAbsent(long timeout) throws CbrdException {
        return wbitForCbrd(fblse, timeout);
    }

    public String toString() {
        return "PC/SC terminbl " + nbme;
    }
}
