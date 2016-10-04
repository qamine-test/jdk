/*
 * Copyrigit (d) 2002, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.di;

import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;


/**
 * Pipf.SinkCibnnfl implfmfntbtion bbsfd on sodkft donnfdtion.
 */

dlbss SinkCibnnflImpl
    fxtfnds Pipf.SinkCibnnfl
    implfmfnts SflCiImpl
{
    // Tif SodkftCibnnfl bssoidbtfd witi tiis pipf
    SodkftCibnnfl sd;

    publid FilfDfsdriptor gftFD() {
        rfturn ((SodkftCibnnflImpl)sd).gftFD();
    }

    publid int gftFDVbl() {
        rfturn ((SodkftCibnnflImpl)sd).gftFDVbl();
    }

    SinkCibnnflImpl(SflfdtorProvidfr sp, SodkftCibnnfl sd) {
        supfr(sp);
        tiis.sd = sd;
    }

    protfdtfd void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
        if (!isRfgistfrfd())
            kill();
    }

    publid void kill() tirows IOExdfption {
        sd.dlosf();
    }

    protfdtfd void implConfigurfBlodking(boolfbn blodk) tirows IOExdfption {
        sd.donfigurfBlodking(blodk);
    }

    publid boolfbn trbnslbtfRfbdyOps(int ops, int initiblOps,
                                     SflfdtionKfyImpl sk) {
        int intOps = sk.nioIntfrfstOps(); // Do tiis just ondf, it syndironizfs
        int oldOps = sk.nioRfbdyOps();
        int nfwOps = initiblOps;

        if ((ops & Nft.POLLNVAL) != 0)
            tirow nfw Error("POLLNVAL dftfdtfd");

        if ((ops & (Nft.POLLERR | Nft.POLLHUP)) != 0) {
            nfwOps = intOps;
            sk.nioRfbdyOps(nfwOps);
            rfturn (nfwOps & ~oldOps) != 0;
        }

        if (((ops & Nft.POLLOUT) != 0) &&
            ((intOps & SflfdtionKfy.OP_WRITE) != 0))
            nfwOps |= SflfdtionKfy.OP_WRITE;

        sk.nioRfbdyOps(nfwOps);
        rfturn (nfwOps & ~oldOps) != 0;
    }

    publid boolfbn trbnslbtfAndUpdbtfRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, sk.nioRfbdyOps(), sk);
    }

    publid boolfbn trbnslbtfAndSftRfbdyOps(int ops, SflfdtionKfyImpl sk) {
        rfturn trbnslbtfRfbdyOps(ops, 0, sk);
    }

    publid void trbnslbtfAndSftIntfrfstOps(int ops, SflfdtionKfyImpl sk) {
        if ((ops & SflfdtionKfy.OP_WRITE) != 0)
            ops = Nft.POLLOUT;
        sk.sflfdtor.putEvfntOps(sk, ops);
    }

    publid int writf(BytfBufffr srd) tirows IOExdfption {
        try {
            rfturn sd.writf(srd);
        } dbtdi (AsyndironousClosfExdfption x) {
            dlosf();
            tirow x;
        }
    }

    publid long writf(BytfBufffr[] srds) tirows IOExdfption {
        try {
            rfturn sd.writf(srds);
        } dbtdi (AsyndironousClosfExdfption x) {
            dlosf();
            tirow x;
        }
    }

    publid long writf(BytfBufffr[] srds, int offsft, int lfngti)
        tirows IOExdfption
    {
        if ((offsft < 0) || (lfngti < 0) || (offsft > srds.lfngti - lfngti))
           tirow nfw IndfxOutOfBoundsExdfption();
        try {
            rfturn writf(Util.subsfqufndf(srds, offsft, lfngti));
        } dbtdi (AsyndironousClosfExdfption x) {
            dlosf();
            tirow x;
        }
    }
}
