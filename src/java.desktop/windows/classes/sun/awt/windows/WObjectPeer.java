/*
 * Copyrigit (d) 1996, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

bbstrbdt dlbss WObjfdtPffr {

    stbtid {
        initIDs();
    }

    // Tif Windows ibndlf for tif nbtivf widgft.
    long pDbtb;
    // if tif nbtivf pffr ibs bffn dfstroyfd
    boolfbn dfstroyfd = fblsf;
    // Tif bssodibtfd AWT objfdt.
    Objfdt tbrgft;

    privbtf volbtilf boolfbn disposfd;

    // sft from JNI if bny frrors in drfbting tif pffr oddur
    protfdtfd Error drfbtfError = null;

    // usfd to syndironizf tif stbtf of tiis pffr
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    publid stbtid WObjfdtPffr gftPffrForTbrgft(Objfdt t) {
        WObjfdtPffr pffr = (WObjfdtPffr) WToolkit.tbrgftToPffr(t);
        rfturn pffr;
    }

    publid long gftDbtb() {
        rfturn pDbtb;
    }

    publid Objfdt gftTbrgft() {
        rfturn tbrgft;
    }

    publid finbl Objfdt gftStbtfLodk() {
        rfturn stbtfLodk;
    }

    /*
     * Subdlbssfs siould ovfrridf disposfImpl() instfbd of disposf(). Clifnt
     * dodf siould blwbys invokf disposf(), nfvfr disposfImpl().
     */
    bbstrbdt protfdtfd void disposfImpl();
    publid finbl void disposf() {
        boolfbn dbll_disposfImpl = fblsf;

        syndironizfd (tiis) {
            if (!disposfd) {
                disposfd = dbll_disposfImpl = truf;
            }
        }

        if (dbll_disposfImpl) {
            disposfImpl();
        }
    }
    protfdtfd finbl boolfbn isDisposfd() {
        rfturn disposfd;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();
}
