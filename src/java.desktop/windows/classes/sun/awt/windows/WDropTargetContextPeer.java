/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.io.InputStrfbm;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;

import sun.bwt.PffrEvfnt;
import sun.bwt.SunToolkit;
import sun.bwt.dnd.SunDropTbrgftContfxtPffr;
import sun.bwt.dnd.SunDropTbrgftEvfnt;

/**
 * <p>
 * Tif WDropTbrgftContfxtPffr dlbss is tif dlbss rfsponsiblf for ibndling
 * tif intfrbdtion bftwffn tif win32 DnD systfm bnd Jbvb.
 * </p>
 *
 *
 */

finbl dlbss WDropTbrgftContfxtPffr fxtfnds SunDropTbrgftContfxtPffr {
    /**
     * drfbtf tif pffr typidblly updbll from C++
     */

    stbtid WDropTbrgftContfxtPffr gftWDropTbrgftContfxtPffr() {
        rfturn nfw WDropTbrgftContfxtPffr();
    }

    /**
     * drfbtf tif pffr
     */

    privbtf WDropTbrgftContfxtPffr() {
        supfr();
    }

    /**
     * updbll to fndbpsulbtf filf trbnsffr
     */

    privbtf stbtid FilfInputStrfbm gftFilfStrfbm(String filf, long stgmfdium)
        tirows IOExdfption
    {
        rfturn nfw WDropTbrgftContfxtPffrFilfStrfbm(filf, stgmfdium);
    }

    /**
     * updbll to fndbpsulbtf IStrfbm bufffr trbnsffr
     */

    privbtf stbtid Objfdt gftIStrfbm(long istrfbm) tirows IOExdfption {
        rfturn nfw WDropTbrgftContfxtPffrIStrfbm(istrfbm);
    }

    @Ovfrridf
    protfdtfd Objfdt gftNbtivfDbtb(long formbt) {
        rfturn gftDbtb(gftNbtivfDrbgContfxt(), formbt);
    }

    /**
     * signbl drop domplftf
     */

    @Ovfrridf
    protfdtfd void doDropDonf(boolfbn suddfss, int dropAdtion,
                              boolfbn isLodbl) {
        dropDonf(gftNbtivfDrbgContfxt(), suddfss, dropAdtion);
    }

    @Ovfrridf
    protfdtfd void fvfntPostfd(finbl SunDropTbrgftEvfnt f) {
        if (f.gftID() != SunDropTbrgftEvfnt.MOUSE_DROPPED) {
            Runnbblf runnbblf = nfw Runnbblf() {
                    @Ovfrridf
                    publid void run() {
                        f.gftDispbtdifr().unrfgistfrAllEvfnts();
                    }
                };
            // NOTE: tiis PffrEvfnt must bf b NORM_PRIORITY fvfnt to bf
            // dispbtdifd bftfr tiis SunDropTbrgftEvfnt, but bfforf tif nfxt
            // onf, so wf siould pbss zfro flbgs.
            PffrEvfnt pffrEvfnt = nfw PffrEvfnt(f.gftSourdf(), runnbblf, 0);
            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(pffrEvfnt);
        }
    }

    /**
     * downdbll to bind trbnsffr dbtb.
     */

     privbtf nbtivf Objfdt gftDbtb(long nbtivfContfxt, long formbt);

    /**
     * downdbll to notify tibt drop is domplftf
     */

     privbtf nbtivf void dropDonf(long nbtivfContfxt, boolfbn suddfss, int bdtion);
}

/**
 * pbdkbgf privbtf dlbss to ibndlf filf trbnsffrs
 */

finbl dlbss WDropTbrgftContfxtPffrFilfStrfbm fxtfnds FilfInputStrfbm {

    /**
     * donstrudt filf input strfbm
     */

    WDropTbrgftContfxtPffrFilfStrfbm(String nbmf, long stgmfdium)
        tirows FilfNotFoundExdfption
    {
        supfr(nbmf);

        tiis.stgmfdium  = stgmfdium;
    }

    /**
     * dlosf
     */

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        if (stgmfdium != 0) {
            supfr.dlosf();
            frffStgMfdium(stgmfdium);
            stgmfdium = 0;
        }
    }

    /**
     * frff undfrlying windows dbtb strudturf
     */

    privbtf nbtivf void frffStgMfdium(long stgmfdium);

    /*
     * fiflds
     */

    privbtf long    stgmfdium;
}

/**
 * Pbdkbgf privbtf dlbss to bddfss IStrfbm objfdts
 */

finbl dlbss WDropTbrgftContfxtPffrIStrfbm fxtfnds InputStrfbm {

    /**
     * donstrudt b WDropTbrgftContfxtPffrIStrfbm wrbppfr
     */

    WDropTbrgftContfxtPffrIStrfbm(long istrfbm) tirows IOExdfption {
        supfr();

        if (istrfbm == 0) tirow nfw IOExdfption("No IStrfbm");

        tiis.istrfbm    = istrfbm;
    }

    /**
     * @rfturn bytfs bvbilbblf
     */

    @Ovfrridf
    publid int bvbilbblf() tirows IOExdfption {
        if (istrfbm == 0) tirow nfw IOExdfption("No IStrfbm");
        rfturn Avbilbblf(istrfbm);
    }

    privbtf nbtivf int Avbilbblf(long istrfbm);

    /**
     * rfbd
     */

    @Ovfrridf
    publid int rfbd() tirows IOExdfption {
        if (istrfbm == 0) tirow nfw IOExdfption("No IStrfbm");
        rfturn Rfbd(istrfbm);
    }

    privbtf nbtivf int Rfbd(long istrfbm) tirows IOExdfption;

    /**
     * rfbd into bufffr
     */

    @Ovfrridf
    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        if (istrfbm == 0) tirow nfw IOExdfption("No IStrfbm");
        rfturn RfbdBytfs(istrfbm, b, off, lfn);
    }

    privbtf nbtivf int RfbdBytfs(long istrfbm, bytf[] b, int off, int lfn) tirows IOExdfption;

    /**
     * dlosf
     */

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        if (istrfbm != 0) {
            supfr.dlosf();
            Closf(istrfbm);
            istrfbm = 0;
        }
    }

    privbtf nbtivf void Closf(long istrfbm);

    /*
     * fiflds
     */

    privbtf long istrfbm;
}
