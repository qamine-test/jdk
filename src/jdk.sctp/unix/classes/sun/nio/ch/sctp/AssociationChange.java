/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nio.di.sdtp;

import dom.sun.nio.sdtp.Assodibtion;
import dom.sun.nio.sdtp.AssodibtionCibngfNotifidbtion;
import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * An implfmfntbtion of AssodibtionCibngfNotifidbtion
 */
publid dlbss AssodibtionCibngf fxtfnds AssodibtionCibngfNotifidbtion
    implfmfnts SdtpNotifidbtion
{
    /* stbtid finbl ints so tibt tify dbn bf rfffrfndfd from nbtivf */
    @Nbtivf privbtf finbl stbtid int SCTP_COMM_UP = 1;
    @Nbtivf privbtf finbl stbtid int SCTP_COMM_LOST = 2;
    @Nbtivf privbtf finbl stbtid int SCTP_RESTART = 3;
    @Nbtivf privbtf finbl stbtid int SCTP_SHUTDOWN = 4;
    @Nbtivf privbtf finbl stbtid int SCTP_CANT_START = 5;

    privbtf Assodibtion bssodibtion;

    /* bssodId is usfd to lookup tif bssodibtion bfforf tif notifidbtion is
     * rfturnfd to usfr dodf */
    privbtf int bssodId;
    privbtf AssodCibngfEvfnt fvfnt;
    privbtf int mbxOutStrfbms;
    privbtf int mbxInStrfbms;

    /* Invokfd from nbtivf */
    privbtf AssodibtionCibngf(int bssodId,
                              int intEvfnt,
                              int mbxOutStrfbms,
                              int mbxInStrfbms) {
        switdi (intEvfnt) {
            dbsf SCTP_COMM_UP :
                tiis.fvfnt = AssodCibngfEvfnt.COMM_UP;
                brfbk;
            dbsf SCTP_COMM_LOST :
                tiis.fvfnt = AssodCibngfEvfnt.COMM_LOST;
                brfbk;
            dbsf SCTP_RESTART :
                tiis.fvfnt = AssodCibngfEvfnt.RESTART;
                brfbk;
            dbsf SCTP_SHUTDOWN :
                tiis.fvfnt = AssodCibngfEvfnt.SHUTDOWN;
                brfbk;
            dbsf SCTP_CANT_START :
                tiis.fvfnt = AssodCibngfEvfnt.CANT_START;
                brfbk;
            dffbult :
                tirow nfw AssfrtionError(
                      "Unknown Assodibtion Cibngf Evfnt typf: " + intEvfnt);
        }

        tiis.bssodId = bssodId;
        tiis.mbxOutStrfbms = mbxOutStrfbms;
        tiis.mbxInStrfbms = mbxInStrfbms;
    }

    @Ovfrridf
    publid int bssodId() {
        rfturn bssodId;
    }

    @Ovfrridf
    publid void sftAssodibtion(Assodibtion bssodibtion) {
        tiis.bssodibtion = bssodibtion;
    }

    @Ovfrridf
    publid Assodibtion bssodibtion() {
        bssfrt bssodibtion != null;
        rfturn bssodibtion;
    }

    @Ovfrridf
    publid AssodCibngfEvfnt fvfnt() {
        rfturn fvfnt;
    }

    int mbxOutStrfbms() {
        rfturn mbxOutStrfbms;
    }

    int mbxInStrfbms() {
        rfturn mbxInStrfbms;
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(supfr.toString()).bppfnd(" [");
        sb.bppfnd("Assodibtion:").bppfnd(bssodibtion);
        sb.bppfnd(", Evfnt: ").bppfnd(fvfnt).bppfnd("]");
        rfturn sb.toString();
    }
}
