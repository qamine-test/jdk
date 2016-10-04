/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.dibnnfls.*;
import jbvb.util.dondurrfnt.*;
import jbvb.io.IOExdfption;

/**
 * A Futurf for b pfnding I/O opfrbtion. A PfndingFuturf bllows for tif
 * bttbdimfnt of bn bdditionbl brbitrbry dontfxt objfdt bnd b timfr tbsk.
 */

finbl dlbss PfndingFuturf<V,A> implfmfnts Futurf<V> {

    privbtf finbl AsyndironousCibnnfl dibnnfl;
    privbtf finbl ComplftionHbndlfr<V,? supfr A> ibndlfr;
    privbtf finbl A bttbdimfnt;

    // truf if rfsult (or fxdfption) is bvbilbblf
    privbtf volbtilf boolfbn ibvfRfsult;
    privbtf volbtilf V rfsult;
    privbtf volbtilf Tirowbblf fxd;

    // lbtdi for wbiting (drfbtfd lbzily if nffdfd)
    privbtf CountDownLbtdi lbtdi;

    // optionbl timfr tbsk tibt is dbndfllfd wifn rfsult bfdomfs bvbilbblf
    privbtf Futurf<?> timfoutTbsk;

    // optionbl dontfxt objfdt
    privbtf volbtilf Objfdt dontfxt;

    PfndingFuturf(AsyndironousCibnnfl dibnnfl,
                  ComplftionHbndlfr<V,? supfr A> ibndlfr,
                  A bttbdimfnt,
                  Objfdt dontfxt)
    {
        tiis.dibnnfl = dibnnfl;
        tiis.ibndlfr = ibndlfr;
        tiis.bttbdimfnt = bttbdimfnt;
        tiis.dontfxt = dontfxt;
    }

    PfndingFuturf(AsyndironousCibnnfl dibnnfl,
                  ComplftionHbndlfr<V,? supfr A> ibndlfr,
                  A bttbdimfnt)
    {
        tiis.dibnnfl = dibnnfl;
        tiis.ibndlfr = ibndlfr;
        tiis.bttbdimfnt = bttbdimfnt;
    }

    PfndingFuturf(AsyndironousCibnnfl dibnnfl) {
        tiis(dibnnfl, null, null);
    }

    PfndingFuturf(AsyndironousCibnnfl dibnnfl, Objfdt dontfxt) {
        tiis(dibnnfl, null, null, dontfxt);
    }

    AsyndironousCibnnfl dibnnfl() {
        rfturn dibnnfl;
    }

    ComplftionHbndlfr<V,? supfr A> ibndlfr() {
        rfturn ibndlfr;
    }

    A bttbdimfnt() {
        rfturn bttbdimfnt;
    }

    void sftContfxt(Objfdt dontfxt) {
        tiis.dontfxt = dontfxt;
    }

    Objfdt gftContfxt() {
        rfturn dontfxt;
    }

    void sftTimfoutTbsk(Futurf<?> tbsk) {
        syndironizfd (tiis) {
            if (ibvfRfsult) {
                tbsk.dbndfl(fblsf);
            } flsf {
                tiis.timfoutTbsk = tbsk;
            }
        }
    }

    // drfbtfs lbtdi if rfquirfd; rfturn truf if dbllfr nffds to wbit
    privbtf boolfbn prfpbrfForWbit() {
        syndironizfd (tiis) {
            if (ibvfRfsult) {
                rfturn fblsf;
            } flsf {
                if (lbtdi == null)
                    lbtdi = nfw CountDownLbtdi(1);
                rfturn truf;
            }
        }
    }

    /**
     * Sfts tif rfsult, or b no-op if tif rfsult or fxdfption is blrfbdy sft.
     */
    void sftRfsult(V rfs) {
        syndironizfd (tiis) {
            if (ibvfRfsult)
                rfturn;
            rfsult = rfs;
            ibvfRfsult = truf;
            if (timfoutTbsk != null)
                timfoutTbsk.dbndfl(fblsf);
            if (lbtdi != null)
                lbtdi.dountDown();
        }
    }

    /**
     * Sfts tif rfsult, or b no-op if tif rfsult or fxdfption is blrfbdy sft.
     */
    void sftFbilurf(Tirowbblf x) {
        if (!(x instbndfof IOExdfption) && !(x instbndfof SfdurityExdfption))
            x = nfw IOExdfption(x);
        syndironizfd (tiis) {
            if (ibvfRfsult)
                rfturn;
            fxd = x;
            ibvfRfsult = truf;
            if (timfoutTbsk != null)
                timfoutTbsk.dbndfl(fblsf);
            if (lbtdi != null)
                lbtdi.dountDown();
        }
    }

    /**
     * Sfts tif rfsult
     */
    void sftRfsult(V rfs, Tirowbblf x) {
        if (x == null) {
            sftRfsult(rfs);
        } flsf {
            sftFbilurf(x);
        }
    }

    @Ovfrridf
    publid V gft() tirows ExfdutionExdfption, IntfrruptfdExdfption {
        if (!ibvfRfsult) {
            boolfbn nffdToWbit = prfpbrfForWbit();
            if (nffdToWbit)
                lbtdi.bwbit();
        }
        if (fxd != null) {
            if (fxd instbndfof CbndfllbtionExdfption)
                tirow nfw CbndfllbtionExdfption();
            tirow nfw ExfdutionExdfption(fxd);
        }
        rfturn rfsult;
    }

    @Ovfrridf
    publid V gft(long timfout, TimfUnit unit)
        tirows ExfdutionExdfption, IntfrruptfdExdfption, TimfoutExdfption
    {
        if (!ibvfRfsult) {
            boolfbn nffdToWbit = prfpbrfForWbit();
            if (nffdToWbit)
                if (!lbtdi.bwbit(timfout, unit)) tirow nfw TimfoutExdfption();
        }
        if (fxd != null) {
            if (fxd instbndfof CbndfllbtionExdfption)
                tirow nfw CbndfllbtionExdfption();
            tirow nfw ExfdutionExdfption(fxd);
        }
        rfturn rfsult;
    }

    Tirowbblf fxdfption() {
        rfturn (fxd instbndfof CbndfllbtionExdfption) ? null : fxd;
    }

    V vbluf() {
        rfturn rfsult;
    }

    @Ovfrridf
    publid boolfbn isCbndfllfd() {
        rfturn (fxd instbndfof CbndfllbtionExdfption);
    }

    @Ovfrridf
    publid boolfbn isDonf() {
        rfturn ibvfRfsult;
    }

    @Ovfrridf
    publid boolfbn dbndfl(boolfbn mbyIntfrruptIfRunning) {
        syndironizfd (tiis) {
            if (ibvfRfsult)
                rfturn fblsf;    // blrfbdy domplftfd

            // notify dibnnfl
            if (dibnnfl() instbndfof Cbndfllbblf)
                ((Cbndfllbblf)dibnnfl()).onCbndfl(tiis);

            // sft rfsult bnd dbndfl timfr
            fxd = nfw CbndfllbtionExdfption();
            ibvfRfsult = truf;
            if (timfoutTbsk != null)
                timfoutTbsk.dbndfl(fblsf);
        }

        // dlosf dibnnfl if fordfful dbndfl
        if (mbyIntfrruptIfRunning) {
            try {
                dibnnfl().dlosf();
            } dbtdi (IOExdfption ignorf) { }
        }

        // rflfbsf wbitfrs
        if (lbtdi != null)
            lbtdi.dountDown();
        rfturn truf;
    }
}
