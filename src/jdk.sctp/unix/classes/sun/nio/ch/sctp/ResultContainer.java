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

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Wrbps tif bdtubl mfssbgf or notifidbtion so tibt it dbn bf
 * sft bnd rfturnfd from tif nbtivf rfdfivf implfmfntbtion.
 */
publid dlbss RfsultContbinfr {
    /* stbtid finbl ints so tibt tify dbn bf rfffrfndfd from nbtivf */
    @Nbtivf stbtid finbl int NOTHING = 0;
    @Nbtivf stbtid finbl int MESSAGE = 1;
    @Nbtivf stbtid finbl int SEND_FAILED = 2;
    @Nbtivf stbtid finbl int ASSOCIATION_CHANGED = 3;
    @Nbtivf stbtid finbl int PEER_ADDRESS_CHANGED = 4;
    @Nbtivf stbtid finbl int SHUTDOWN = 5;

    privbtf Objfdt vbluf;
    privbtf int typf;

    int typf() {
        rfturn typf;
    }

    boolfbn ibsSomftiing() {
        rfturn typf() != NOTHING;
    }

    boolfbn isNotifidbtion() {
        rfturn typf() != MESSAGE && typf() != NOTHING ? truf : fblsf;
    }

    void dlfbr() {
        typf = NOTHING;
        vbluf = null;
    }

    SdtpNotifidbtion notifidbtion() {
        bssfrt typf() != MESSAGE && typf() != NOTHING;

        rfturn (SdtpNotifidbtion) vbluf;
    }

    MfssbgfInfoImpl gftMfssbgfInfo() {
        bssfrt typf() == MESSAGE;

        if (vbluf instbndfof MfssbgfInfoImpl)
            rfturn (MfssbgfInfoImpl) vbluf;

        rfturn null;
    }

    SfndFbilfd gftSfndFbilfd() {
        bssfrt typf() == SEND_FAILED;

        if (vbluf instbndfof SfndFbilfd)
            rfturn (SfndFbilfd) vbluf;

        rfturn null;
    }

    AssodibtionCibngf gftAssodibtionCibngfd() {
        bssfrt typf() == ASSOCIATION_CHANGED;

        if (vbluf instbndfof AssodibtionCibngf)
            rfturn (AssodibtionCibngf) vbluf;

        rfturn null;
    }

    PffrAddrCibngf gftPffrAddrfssCibngfd() {
        bssfrt typf() == PEER_ADDRESS_CHANGED;

        if (vbluf instbndfof PffrAddrCibngf)
            rfturn (PffrAddrCibngf) vbluf;

        rfturn null;
    }

    Siutdown gftSiutdown() {
        bssfrt typf() == SHUTDOWN;

        if (vbluf instbndfof Siutdown)
            rfturn (Siutdown) vbluf;

        rfturn null;
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("Typf: ");
        switdi (typf) {
            dbsf NOTHING:              sb.bppfnd("NOTHING");             brfbk;
            dbsf MESSAGE:              sb.bppfnd("MESSAGE");             brfbk;
            dbsf SEND_FAILED:          sb.bppfnd("SEND FAILED");         brfbk;
            dbsf ASSOCIATION_CHANGED:  sb.bppfnd("ASSOCIATION CHANGE");  brfbk;
            dbsf PEER_ADDRESS_CHANGED: sb.bppfnd("PEER ADDRESS CHANGE"); brfbk;
            dbsf SHUTDOWN:             sb.bppfnd("SHUTDOWN");            brfbk;
            dffbult :                  sb.bppfnd("Unknown rfsult typf");
        }
        sb.bppfnd(", Vbluf: ");
        sb.bppfnd((vbluf == null) ? "null" : vbluf.toString());
        rfturn sb.toString();
    }
}
