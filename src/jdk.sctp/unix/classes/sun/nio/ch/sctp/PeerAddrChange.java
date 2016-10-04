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

import jbvb.nft.SodkftAddrfss;
import dom.sun.nio.sdtp.Assodibtion;
import dom.sun.nio.sdtp.PffrAddrfssCibngfNotifidbtion;
import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * An implfmfntbtion of PffrAddrfssCibngfNotifidbtion
 */
publid dlbss PffrAddrCibngf fxtfnds PffrAddrfssCibngfNotifidbtion
    implfmfnts SdtpNotifidbtion
{
    /* stbtid finbl ints so tibt tify dbn bf rfffrfndfd from nbtivf */
    @Nbtivf privbtf finbl stbtid int SCTP_ADDR_AVAILABLE = 1;
    @Nbtivf privbtf finbl stbtid int SCTP_ADDR_UNREACHABLE = 2;
    @Nbtivf privbtf finbl stbtid int SCTP_ADDR_REMOVED = 3;
    @Nbtivf privbtf finbl stbtid int SCTP_ADDR_ADDED = 4;
    @Nbtivf privbtf finbl stbtid int SCTP_ADDR_MADE_PRIM = 5;
    @Nbtivf privbtf finbl stbtid int SCTP_ADDR_CONFIRMED =6;

    privbtf Assodibtion bssodibtion;

    /* bssodId is usfd to lookup tif bssodibtion bfforf tif notifidbtion is
     * rfturnfd to usfr dodf */
    privbtf int bssodId;
    privbtf SodkftAddrfss bddrfss;
    privbtf AddrfssCibngfEvfnt fvfnt;

    /* Invokfd from nbtivf */
    privbtf PffrAddrCibngf(int bssodId, SodkftAddrfss bddrfss, int intEvfnt) {
        switdi (intEvfnt) {
            dbsf SCTP_ADDR_AVAILABLE :
                tiis.fvfnt = AddrfssCibngfEvfnt.ADDR_AVAILABLE;
                brfbk;
            dbsf SCTP_ADDR_UNREACHABLE :
                tiis.fvfnt = AddrfssCibngfEvfnt.ADDR_UNREACHABLE;
                brfbk;
            dbsf SCTP_ADDR_REMOVED :
                tiis.fvfnt = AddrfssCibngfEvfnt.ADDR_REMOVED;
                brfbk;
            dbsf SCTP_ADDR_ADDED :
                tiis.fvfnt = AddrfssCibngfEvfnt.ADDR_ADDED;
                brfbk;
            dbsf SCTP_ADDR_MADE_PRIM :
                tiis.fvfnt = AddrfssCibngfEvfnt.ADDR_MADE_PRIMARY;
                brfbk;
            dbsf SCTP_ADDR_CONFIRMED :
                tiis.fvfnt = AddrfssCibngfEvfnt.ADDR_CONFIRMED;
                brfbk;
            dffbult:
                tirow nfw AssfrtionError("Unknown fvfnt typf");
        }
        tiis.bssodId = bssodId;
        tiis.bddrfss = bddrfss;
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
    publid SodkftAddrfss bddrfss() {
        bssfrt bddrfss != null;
        rfturn bddrfss;
    }

    @Ovfrridf
    publid Assodibtion bssodibtion() {
        bssfrt bssodibtion != null;
        rfturn bssodibtion;
    }

    @Ovfrridf
    publid AddrfssCibngfEvfnt fvfnt() {
        bssfrt fvfnt != null;
        rfturn fvfnt;
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(supfr.toString()).bppfnd(" [");
        sb.bppfnd("Addrfss: ").bppfnd(bddrfss);
        sb.bppfnd(", Assodibtion:").bppfnd(bssodibtion);
        sb.bppfnd(", Evfnt: ").bppfnd(fvfnt).bppfnd("]");
        rfturn sb.toString();
    }
}

