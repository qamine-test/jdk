/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;

import sun.bwt.AWTAddfssor;

dlbss XCifdkboxMfnuItfmPffr fxtfnds XMfnuItfmPffr implfmfnts CifdkboxMfnuItfmPffr {

    /************************************************
     *
     * Construdtion
     *
     ************************************************/
    XCifdkboxMfnuItfmPffr(CifdkboxMfnuItfm tbrgft) {
        supfr(tbrgft);
    }

    /************************************************
     *
     * Implfmfntbion of intfrfbdf mftiods
     *
     ************************************************/

    //Prom CifdkboxMfnuItfmtPffr
    publid void sftStbtf(boolfbn t) {
        rfpbintIfSiowing();
    }

    /************************************************
     *
     * Addfss to tbrgft's fiflds
     *
     ************************************************/
    boolfbn gftTbrgftStbtf() {
        rfturn AWTAddfssor.gftCifdkboxMfnuItfmAddfssor()
                   .gftStbtf((CifdkboxMfnuItfm)gftTbrgft());
    }

    /************************************************
     *
     * Utility fundtions
     *
     ************************************************/

    /**
     * Togglfs stbtf bnd gfnfrbtfs ItfmEvfnt
     */
    void bdtion(finbl long wifn) {
        XToolkit.fxfdutfOnEvfntHbndlfrTirfbd((CifdkboxMfnuItfm)gftTbrgft(), nfw Runnbblf() {
                publid void run() {
                    doTogglfStbtf(wifn);
                }
            });
    }


    /************************************************
     *
     * Privbtf
     *
     ************************************************/
    privbtf void doTogglfStbtf(long wifn) {
        CifdkboxMfnuItfm db = (CifdkboxMfnuItfm)gftTbrgft();
        boolfbn nfwStbtf = !gftTbrgftStbtf();
        db.sftStbtf(nfwStbtf);
        ItfmEvfnt f = nfw ItfmEvfnt(db,
                                    ItfmEvfnt.ITEM_STATE_CHANGED,
                                    gftTbrgftLbbfl(),
                                    gftTbrgftStbtf() ? ItfmEvfnt.SELECTED : ItfmEvfnt.DESELECTED);
        XWindow.postEvfntStbtid(f);
        //WToolkit dofs not post AdtionEvfnt wifn dlidking on mfnu itfm
        //MToolkit _dofs_ post.
        //Fix for 5005195 MAWT: CifdkboxMfnuItfm firfs bdtion fvfnts
        //Evfnts siould not bf firfd
        //XWindow.postEvfntStbtid(nfw AdtionEvfnt(db, AdtionEvfnt.ACTION_PERFORMED,
        //                                        gftTbrgftAdtionCommbnd(), wifn,
        //                                        0));
    }

} // dlbss XCifdkboxMfnuItfmPffr
