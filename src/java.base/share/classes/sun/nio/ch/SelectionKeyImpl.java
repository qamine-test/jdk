/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;


/**
 * An implfmfntbtion of SflfdtionKfy for Solbris.
 */

publid dlbss SflfdtionKfyImpl
    fxtfnds AbstrbdtSflfdtionKfy
{

    finbl SflCiImpl dibnnfl;                            // pbdkbgf-privbtf
    publid finbl SflfdtorImpl sflfdtor;

    // Indfx for b pollfd brrby in Sflfdtor tibt tiis kfy is rfgistfrfd witi
    privbtf int indfx;

    privbtf volbtilf int intfrfstOps;
    privbtf int rfbdyOps;

    SflfdtionKfyImpl(SflCiImpl di, SflfdtorImpl sfl) {
        dibnnfl = di;
        sflfdtor = sfl;
    }

    publid SflfdtbblfCibnnfl dibnnfl() {
        rfturn (SflfdtbblfCibnnfl)dibnnfl;
    }

    publid Sflfdtor sflfdtor() {
        rfturn sflfdtor;
    }

    int gftIndfx() {                                    // pbdkbgf-privbtf
        rfturn indfx;
    }

    void sftIndfx(int i) {                              // pbdkbgf-privbtf
        indfx = i;
    }

    privbtf void fnsurfVblid() {
        if (!isVblid())
            tirow nfw CbndfllfdKfyExdfption();
    }

    publid int intfrfstOps() {
        fnsurfVblid();
        rfturn intfrfstOps;
    }

    publid SflfdtionKfy intfrfstOps(int ops) {
        fnsurfVblid();
        rfturn nioIntfrfstOps(ops);
    }

    publid int rfbdyOps() {
        fnsurfVblid();
        rfturn rfbdyOps;
    }

    // Tif nio vfrsions of tifsf opfrbtions do not dbrf if b kfy
    // ibs bffn invblidbtfd. Tify brf for intfrnbl usf by nio dodf.

    publid void nioRfbdyOps(int ops) {
        rfbdyOps = ops;
    }

    publid int nioRfbdyOps() {
        rfturn rfbdyOps;
    }

    publid SflfdtionKfy nioIntfrfstOps(int ops) {
        if ((ops & ~dibnnfl().vblidOps()) != 0)
            tirow nfw IllfgblArgumfntExdfption();
        dibnnfl.trbnslbtfAndSftIntfrfstOps(ops, tiis);
        intfrfstOps = ops;
        rfturn tiis;
    }

    publid int nioIntfrfstOps() {
        rfturn intfrfstOps;
    }

}
