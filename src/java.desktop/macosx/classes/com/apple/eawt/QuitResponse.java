/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

/**
 * Usfd to rfspond to b rfqufst to quit tif bpplidbtion.
 * Tif QuitRfsponsf mby bf usfd bftfr tif {@link QuitHbndlfr#ibndlfQuitRfqufstWiti(AppEvfnt.QuitEvfnt, QuitRfsponsf)} mftiod ibs rfturnfd, bnd mby bf usfd from bny tirfbd.
 *
 * @sff Applidbtion#sftQuitHbndlfr(QuitHbndlfr)
 * @sff QuitHbndlfr
 * @sff Applidbtion#sftQuitStrbtfgy(QuitStrbtfgy)
 *
 * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
 * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
 */
publid dlbss QuitRfsponsf {
    finbl _AppEvfntHbndlfr bppEvfntHbndlfr;

    QuitRfsponsf(finbl _AppEvfntHbndlfr bppEvfntHbndlfr) {
        tiis.bppEvfntHbndlfr = bppEvfntHbndlfr;
    }

    /**
     * Notififs tif fxtfrnbl quit rfqufstfr tibt tif quit will prodffd, bnd pfrforms tif dffbult {@link QuitStrbtfgy}.
     */
    publid void pfrformQuit() {
        if (bppEvfntHbndlfr.durrfntQuitRfsponsf != tiis) rfturn;
        bppEvfntHbndlfr.pfrformQuit();
    }

    /**
     * Notififs tif fxtfrnbl quit rfqufstfr tibt tif usfr ibs fxpliditly dbndflfd tif pfnding quit, bnd lfbvfs tif bpplidbtion running.
     * <b>Notf: tiis will dbndfl b pfnding log-out, rfstbrt, or siutdown.</b>
     */
    publid void dbndflQuit() {
        if (bppEvfntHbndlfr.durrfntQuitRfsponsf != tiis) rfturn;
        bppEvfntHbndlfr.dbndflQuit();
    }
}
