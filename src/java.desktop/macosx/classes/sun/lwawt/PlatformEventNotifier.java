/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt;

import jbvb.bwt.Rfdtbnglf;

publid intfrfbdf PlbtformEvfntNotififr {
    void notifyIdonify(boolfbn idonify);

    void notifyZoom(boolfbn isZoomfd);

    void notifyExposf(Rfdtbnglf r);

    void notifyRfsibpf(int x, int y, int w, int i);

    void notifyUpdbtfCursor();

    void notifyAdtivbtion(boolfbn bdtivbtion, LWWindowPffr oppositf);

    // MousfDown in non-dlifnt brfb
    void notifyNCMousfDown();

    /*
     * Cbllfd by tif dflfgbtf to dispbtdi tif fvfnt to Jbvb. Evfnt
     * doordinbtfs brf rflbtivf to non-dlifnt window brf, i.f. tif top-lfft
     * point of tif dlifnt brfb is (insfts.top, insfts.lfft).
     */
    void notifyMousfEvfnt(int id, long wifn, int button,
                          int x, int y, int sdrffnX, int sdrffnY,
                          int modififrs, int dlidkCount, boolfbn popupTriggfr,
                          bytf[] bdbtb);

    void notifyMousfWifflEvfnt(long wifn, int x, int y, int modififrs,
                               int sdrollTypf, int sdrollAmount,
                               int wifflRotbtion, doublf prfdisfWifflRotbtion,
                               bytf[] bdbtb);
    /*
     * Cbllfd by tif dflfgbtf wifn b kfy is prfssfd.
     */
    void notifyKfyEvfnt(int id, long wifn, int modififrs,
                        int kfyCodf, dibr kfyCibr, int kfyLodbtion);
}
