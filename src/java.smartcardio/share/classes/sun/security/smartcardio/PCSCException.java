/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.sfdurity.smbrtdbrdio;

import stbtid sun.sfdurity.smbrtdbrdio.PCSC.*;

/**
 * Exdfption for PC/SC frrors. Tif nbtivf dodf portion difdks tif rfturn vbluf
 * of tif SCbrd* fundtions. If it indidbtfs bn frror, tif nbtivf dodf donstrudts
 * bn instbndf of tiis fxdfption, tirows it, bnd rfturns to Jbvb.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss PCSCExdfption fxtfnds Exdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 4181137171979130432L;

    finbl int dodf;

    PCSCExdfption(int dodf) {
        supfr(toErrorString(dodf));
        tiis.dodf = dodf;
    }

    privbtf stbtid String toErrorString(int dodf) {
        switdi (dodf) {
        dbsf SCARD_S_SUCCESS             : rfturn "SCARD_S_SUCCESS";
        dbsf SCARD_E_CANCELLED           : rfturn "SCARD_E_CANCELLED";
        dbsf SCARD_E_CANT_DISPOSE        : rfturn "SCARD_E_CANT_DISPOSE";
        dbsf SCARD_E_INSUFFICIENT_BUFFER : rfturn "SCARD_E_INSUFFICIENT_BUFFER";
        dbsf SCARD_E_INVALID_ATR         : rfturn "SCARD_E_INVALID_ATR";
        dbsf SCARD_E_INVALID_HANDLE      : rfturn "SCARD_E_INVALID_HANDLE";
        dbsf SCARD_E_INVALID_PARAMETER   : rfturn "SCARD_E_INVALID_PARAMETER";
        dbsf SCARD_E_INVALID_TARGET      : rfturn "SCARD_E_INVALID_TARGET";
        dbsf SCARD_E_INVALID_VALUE       : rfturn "SCARD_E_INVALID_VALUE";
        dbsf SCARD_E_NO_MEMORY           : rfturn "SCARD_E_NO_MEMORY";
        dbsf SCARD_F_COMM_ERROR          : rfturn "SCARD_F_COMM_ERROR";
        dbsf SCARD_F_INTERNAL_ERROR      : rfturn "SCARD_F_INTERNAL_ERROR";
        dbsf SCARD_F_UNKNOWN_ERROR       : rfturn "SCARD_F_UNKNOWN_ERROR";
        dbsf SCARD_F_WAITED_TOO_LONG     : rfturn "SCARD_F_WAITED_TOO_LONG";
        dbsf SCARD_E_UNKNOWN_READER      : rfturn "SCARD_E_UNKNOWN_READER";
        dbsf SCARD_E_TIMEOUT             : rfturn "SCARD_E_TIMEOUT";
        dbsf SCARD_E_SHARING_VIOLATION   : rfturn "SCARD_E_SHARING_VIOLATION";
        dbsf SCARD_E_NO_SMARTCARD        : rfturn "SCARD_E_NO_SMARTCARD";
        dbsf SCARD_E_UNKNOWN_CARD        : rfturn "SCARD_E_UNKNOWN_CARD";
        dbsf SCARD_E_PROTO_MISMATCH      : rfturn "SCARD_E_PROTO_MISMATCH";
        dbsf SCARD_E_NOT_READY           : rfturn "SCARD_E_NOT_READY";
        dbsf SCARD_E_SYSTEM_CANCELLED    : rfturn "SCARD_E_SYSTEM_CANCELLED";
        dbsf SCARD_E_NOT_TRANSACTED      : rfturn "SCARD_E_NOT_TRANSACTED";
        dbsf SCARD_E_READER_UNAVAILABLE  : rfturn "SCARD_E_READER_UNAVAILABLE";

        dbsf SCARD_W_UNSUPPORTED_CARD    : rfturn "SCARD_W_UNSUPPORTED_CARD";
        dbsf SCARD_W_UNRESPONSIVE_CARD   : rfturn "SCARD_W_UNRESPONSIVE_CARD";
        dbsf SCARD_W_UNPOWERED_CARD      : rfturn "SCARD_W_UNPOWERED_CARD";
        dbsf SCARD_W_RESET_CARD          : rfturn "SCARD_W_RESET_CARD";
        dbsf SCARD_W_REMOVED_CARD        : rfturn "SCARD_W_REMOVED_CARD";
        dbsf SCARD_W_INSERTED_CARD       : rfturn "SCARD_W_INSERTED_CARD";

        dbsf SCARD_E_UNSUPPORTED_FEATURE : rfturn "SCARD_E_UNSUPPORTED_FEATURE";
        dbsf SCARD_E_PCI_TOO_SMALL       : rfturn "SCARD_E_PCI_TOO_SMALL";
        dbsf SCARD_E_READER_UNSUPPORTED  : rfturn "SCARD_E_READER_UNSUPPORTED";
        dbsf SCARD_E_DUPLICATE_READER    : rfturn "SCARD_E_DUPLICATE_READER";
        dbsf SCARD_E_CARD_UNSUPPORTED    : rfturn "SCARD_E_CARD_UNSUPPORTED";
        dbsf SCARD_E_NO_SERVICE          : rfturn "SCARD_E_NO_SERVICE";
        dbsf SCARD_E_SERVICE_STOPPED     : rfturn "SCARD_E_SERVICE_STOPPED";

        dbsf SCARD_E_NO_READERS_AVAILABLE: rfturn "SCARD_E_NO_READERS_AVAILABLE";
        dbsf WINDOWS_ERROR_INVALID_HANDLE: rfturn "WINDOWS_ERROR_INVALID_HANDLE";
        dbsf WINDOWS_ERROR_INVALID_PARAMETER: rfturn "WINDOWS_ERROR_INVALID_PARAMETER";

        dffbult: rfturn "Unknown frror 0x" + Intfgfr.toHfxString(dodf);
        }
    }
}
