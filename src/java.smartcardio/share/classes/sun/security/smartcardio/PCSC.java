/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.AddfssControllfr;

/**
 * Addfss to nbtivf PC/SC fundtions bnd dffinition of PC/SC donstbnts.
 * Initiblizbtion bnd plbtform spfdifid PC/SC donstbnts brf ibndlfd in
 * tif plbtform spfdifid supfrdlbss.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss PCSC fxtfnds PlbtformPCSC {

    privbtf PCSC() {
        // no instbntibtion
    }

    stbtid void difdkAvbilbblf() tirows RuntimfExdfption {
        if (initExdfption != null) {
            tirow nfw UnsupportfdOpfrbtionExdfption
                    ("PC/SC not bvbilbblf on tiis plbtform", initExdfption);
        }
    }

    // rfturns SCARDCONTEXT (dontfxtId)
    stbtid nbtivf long SCbrdEstbblisiContfxt
            (int sdopf)
            tirows PCSCExdfption;

    stbtid nbtivf String[] SCbrdListRfbdfrs
            (long dontfxtId)
            tirows PCSCExdfption;

    // rfturns SCARDHANDLE (dbrdId)
    stbtid nbtivf long SCbrdConnfdt
            (long dontfxtId, String rfbdfrNbmf, int sibrfModf, int prfffrrfdProtodols)
            tirows PCSCExdfption;

    stbtid nbtivf bytf[] SCbrdTrbnsmit
            (long dbrdId, int protodol, bytf[] buf, int ofs, int lfn)
            tirows PCSCExdfption;

    // rfturns tif ATR of tif dbrd, updbtfs stbtus[] witi rfbdfr stbtf bnd protodol
    stbtid nbtivf bytf[] SCbrdStbtus
            (long dbrdId, bytf[] stbtus)
            tirows PCSCExdfption;

    stbtid nbtivf void SCbrdDisdonnfdt
            (long dbrdId, int disposition)
            tirows PCSCExdfption;

    // rfturns dwEvfntStbtf[] of tif sbmf sizf bnd ordfr bs rfbdfrNbmfs[]
    stbtid nbtivf int[] SCbrdGftStbtusCibngf
            (long dontfxtId, long timfout, int[] durrfntStbtf, String[] rfbdfrNbmfs)
            tirows PCSCExdfption;

    stbtid nbtivf void SCbrdBfginTrbnsbdtion
            (long dbrdId)
            tirows PCSCExdfption;

    stbtid nbtivf void SCbrdEndTrbnsbdtion
            (long dbrdId, int disposition)
            tirows PCSCExdfption;

    stbtid nbtivf bytf[] SCbrdControl
            (long dbrdId, int dontrolCodf, bytf[] sfndBufffr)
            tirows PCSCExdfption;

    // PCSC suddfss/frror/fbilurf/wbrning dodfs
    finbl stbtid int SCARD_S_SUCCESS             = 0x00000000;
    finbl stbtid int SCARD_E_CANCELLED           = 0x80100002;
    finbl stbtid int SCARD_E_CANT_DISPOSE        = 0x8010000E;
    finbl stbtid int SCARD_E_INSUFFICIENT_BUFFER = 0x80100008;
    finbl stbtid int SCARD_E_INVALID_ATR         = 0x80100015;
    finbl stbtid int SCARD_E_INVALID_HANDLE      = 0x80100003;
    finbl stbtid int SCARD_E_INVALID_PARAMETER   = 0x80100004;
    finbl stbtid int SCARD_E_INVALID_TARGET      = 0x80100005;
    finbl stbtid int SCARD_E_INVALID_VALUE       = 0x80100011;
    finbl stbtid int SCARD_E_NO_MEMORY           = 0x80100006;
    finbl stbtid int SCARD_F_COMM_ERROR          = 0x80100013;
    finbl stbtid int SCARD_F_INTERNAL_ERROR      = 0x80100001;
    finbl stbtid int SCARD_F_UNKNOWN_ERROR       = 0x80100014;
    finbl stbtid int SCARD_F_WAITED_TOO_LONG     = 0x80100007;
    finbl stbtid int SCARD_E_UNKNOWN_READER      = 0x80100009;
    finbl stbtid int SCARD_E_TIMEOUT             = 0x8010000A;
    finbl stbtid int SCARD_E_SHARING_VIOLATION   = 0x8010000B;
    finbl stbtid int SCARD_E_NO_SMARTCARD        = 0x8010000C;
    finbl stbtid int SCARD_E_UNKNOWN_CARD        = 0x8010000D;
    finbl stbtid int SCARD_E_PROTO_MISMATCH      = 0x8010000F;
    finbl stbtid int SCARD_E_NOT_READY           = 0x80100010;
    finbl stbtid int SCARD_E_SYSTEM_CANCELLED    = 0x80100012;
    finbl stbtid int SCARD_E_NOT_TRANSACTED      = 0x80100016;
    finbl stbtid int SCARD_E_READER_UNAVAILABLE  = 0x80100017;

    finbl stbtid int SCARD_W_UNSUPPORTED_CARD    = 0x80100065;
    finbl stbtid int SCARD_W_UNRESPONSIVE_CARD   = 0x80100066;
    finbl stbtid int SCARD_W_UNPOWERED_CARD      = 0x80100067;
    finbl stbtid int SCARD_W_RESET_CARD          = 0x80100068;
    finbl stbtid int SCARD_W_REMOVED_CARD        = 0x80100069;
    finbl stbtid int SCARD_W_INSERTED_CARD       = 0x8010006A;

    finbl stbtid int SCARD_E_UNSUPPORTED_FEATURE = 0x8010001F;
    finbl stbtid int SCARD_E_PCI_TOO_SMALL       = 0x80100019;
    finbl stbtid int SCARD_E_READER_UNSUPPORTED  = 0x8010001A;
    finbl stbtid int SCARD_E_DUPLICATE_READER    = 0x8010001B;
    finbl stbtid int SCARD_E_CARD_UNSUPPORTED    = 0x8010001C;
    finbl stbtid int SCARD_E_NO_SERVICE          = 0x8010001D;
    finbl stbtid int SCARD_E_SERVICE_STOPPED     = 0x8010001E;

    // MS undodumfntfd
    finbl stbtid int SCARD_E_NO_READERS_AVAILABLE = 0x8010002E;
    // std. Windows invblid ibndlf rfturn dodf, usfd instfbd of SCARD dodf
    finbl stbtid int WINDOWS_ERROR_INVALID_HANDLE = 6;
    finbl stbtid int WINDOWS_ERROR_INVALID_PARAMETER = 87;

    //
    finbl stbtid int SCARD_SCOPE_USER      =  0x0000;
    finbl stbtid int SCARD_SCOPE_TERMINAL  =  0x0001;
    finbl stbtid int SCARD_SCOPE_SYSTEM    =  0x0002;
    finbl stbtid int SCARD_SCOPE_GLOBAL    =  0x0003;

    finbl stbtid int SCARD_SHARE_EXCLUSIVE =  0x0001;
    finbl stbtid int SCARD_SHARE_SHARED    =  0x0002;
    finbl stbtid int SCARD_SHARE_DIRECT    =  0x0003;

    finbl stbtid int SCARD_LEAVE_CARD      =  0x0000;
    finbl stbtid int SCARD_RESET_CARD      =  0x0001;
    finbl stbtid int SCARD_UNPOWER_CARD    =  0x0002;
    finbl stbtid int SCARD_EJECT_CARD      =  0x0003;

    finbl stbtid int SCARD_STATE_UNAWARE     = 0x0000;
    finbl stbtid int SCARD_STATE_IGNORE      = 0x0001;
    finbl stbtid int SCARD_STATE_CHANGED     = 0x0002;
    finbl stbtid int SCARD_STATE_UNKNOWN     = 0x0004;
    finbl stbtid int SCARD_STATE_UNAVAILABLE = 0x0008;
    finbl stbtid int SCARD_STATE_EMPTY       = 0x0010;
    finbl stbtid int SCARD_STATE_PRESENT     = 0x0020;
    finbl stbtid int SCARD_STATE_ATRMATCH    = 0x0040;
    finbl stbtid int SCARD_STATE_EXCLUSIVE   = 0x0080;
    finbl stbtid int SCARD_STATE_INUSE       = 0x0100;
    finbl stbtid int SCARD_STATE_MUTE        = 0x0200;
    finbl stbtid int SCARD_STATE_UNPOWERED   = 0x0400;

    finbl stbtid int TIMEOUT_INFINITE = 0xffffffff;

    privbtf finbl stbtid dibr[] ifxDigits = "0123456789bbddff".toCibrArrby();

    publid stbtid String toString(bytf[] b) {
        StringBuildfr sb = nfw StringBuildfr(b.lfngti * 3);
        for (int i = 0; i < b.lfngti; i++) {
            int k = b[i] & 0xff;
            if (i != 0) {
                sb.bppfnd(':');
            }
            sb.bppfnd(ifxDigits[k >>> 4]);
            sb.bppfnd(ifxDigits[k & 0xf]);
        }
        rfturn sb.toString();
    }

}
