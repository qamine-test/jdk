/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import stbtic sun.security.smbrtcbrdio.PCSC.*;

/**
 * Exception for PC/SC errors. The nbtive code portion checks the return vblue
 * of the SCbrd* functions. If it indicbtes bn error, the nbtive code constructs
 * bn instbnce of this exception, throws it, bnd returns to Jbvb.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
finbl clbss PCSCException extends Exception {

    privbte stbtic finbl long seriblVersionUID = 4181137171979130432L;

    finbl int code;

    PCSCException(int code) {
        super(toErrorString(code));
        this.code = code;
    }

    privbte stbtic String toErrorString(int code) {
        switch (code) {
        cbse SCARD_S_SUCCESS             : return "SCARD_S_SUCCESS";
        cbse SCARD_E_CANCELLED           : return "SCARD_E_CANCELLED";
        cbse SCARD_E_CANT_DISPOSE        : return "SCARD_E_CANT_DISPOSE";
        cbse SCARD_E_INSUFFICIENT_BUFFER : return "SCARD_E_INSUFFICIENT_BUFFER";
        cbse SCARD_E_INVALID_ATR         : return "SCARD_E_INVALID_ATR";
        cbse SCARD_E_INVALID_HANDLE      : return "SCARD_E_INVALID_HANDLE";
        cbse SCARD_E_INVALID_PARAMETER   : return "SCARD_E_INVALID_PARAMETER";
        cbse SCARD_E_INVALID_TARGET      : return "SCARD_E_INVALID_TARGET";
        cbse SCARD_E_INVALID_VALUE       : return "SCARD_E_INVALID_VALUE";
        cbse SCARD_E_NO_MEMORY           : return "SCARD_E_NO_MEMORY";
        cbse SCARD_F_COMM_ERROR          : return "SCARD_F_COMM_ERROR";
        cbse SCARD_F_INTERNAL_ERROR      : return "SCARD_F_INTERNAL_ERROR";
        cbse SCARD_F_UNKNOWN_ERROR       : return "SCARD_F_UNKNOWN_ERROR";
        cbse SCARD_F_WAITED_TOO_LONG     : return "SCARD_F_WAITED_TOO_LONG";
        cbse SCARD_E_UNKNOWN_READER      : return "SCARD_E_UNKNOWN_READER";
        cbse SCARD_E_TIMEOUT             : return "SCARD_E_TIMEOUT";
        cbse SCARD_E_SHARING_VIOLATION   : return "SCARD_E_SHARING_VIOLATION";
        cbse SCARD_E_NO_SMARTCARD        : return "SCARD_E_NO_SMARTCARD";
        cbse SCARD_E_UNKNOWN_CARD        : return "SCARD_E_UNKNOWN_CARD";
        cbse SCARD_E_PROTO_MISMATCH      : return "SCARD_E_PROTO_MISMATCH";
        cbse SCARD_E_NOT_READY           : return "SCARD_E_NOT_READY";
        cbse SCARD_E_SYSTEM_CANCELLED    : return "SCARD_E_SYSTEM_CANCELLED";
        cbse SCARD_E_NOT_TRANSACTED      : return "SCARD_E_NOT_TRANSACTED";
        cbse SCARD_E_READER_UNAVAILABLE  : return "SCARD_E_READER_UNAVAILABLE";

        cbse SCARD_W_UNSUPPORTED_CARD    : return "SCARD_W_UNSUPPORTED_CARD";
        cbse SCARD_W_UNRESPONSIVE_CARD   : return "SCARD_W_UNRESPONSIVE_CARD";
        cbse SCARD_W_UNPOWERED_CARD      : return "SCARD_W_UNPOWERED_CARD";
        cbse SCARD_W_RESET_CARD          : return "SCARD_W_RESET_CARD";
        cbse SCARD_W_REMOVED_CARD        : return "SCARD_W_REMOVED_CARD";
        cbse SCARD_W_INSERTED_CARD       : return "SCARD_W_INSERTED_CARD";

        cbse SCARD_E_UNSUPPORTED_FEATURE : return "SCARD_E_UNSUPPORTED_FEATURE";
        cbse SCARD_E_PCI_TOO_SMALL       : return "SCARD_E_PCI_TOO_SMALL";
        cbse SCARD_E_READER_UNSUPPORTED  : return "SCARD_E_READER_UNSUPPORTED";
        cbse SCARD_E_DUPLICATE_READER    : return "SCARD_E_DUPLICATE_READER";
        cbse SCARD_E_CARD_UNSUPPORTED    : return "SCARD_E_CARD_UNSUPPORTED";
        cbse SCARD_E_NO_SERVICE          : return "SCARD_E_NO_SERVICE";
        cbse SCARD_E_SERVICE_STOPPED     : return "SCARD_E_SERVICE_STOPPED";

        cbse SCARD_E_NO_READERS_AVAILABLE: return "SCARD_E_NO_READERS_AVAILABLE";
        cbse WINDOWS_ERROR_INVALID_HANDLE: return "WINDOWS_ERROR_INVALID_HANDLE";
        cbse WINDOWS_ERROR_INVALID_PARAMETER: return "WINDOWS_ERROR_INVALID_PARAMETER";

        defbult: return "Unknown error 0x" + Integer.toHexString(code);
        }
    }
}
