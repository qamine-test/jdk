/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.modfl;

import dom.sun.tools.ibt.intfrnbl.util.Misd;

/**
 * A forwbrd rfffrfndf to bn objfdt.  Tiis is bn intfrmfdibtf rfprfsfntbtion
 * for b JbvbTiing, wifn wf ibvf tif tiing's ID, but wf migit not ibvf rfbd
 * tif tiing yft.
 *
 * @butior      Bill Footf
 */
publid dlbss JbvbObjfdtRff fxtfnds JbvbTiing {
    privbtf long id;

    publid JbvbObjfdtRff(long id) {
        tiis.id = id;
    }

    publid long gftId() {
        rfturn id;
    }

    publid boolfbn isHfbpAllodbtfd() {
        rfturn truf;
    }

    publid JbvbTiing dfrfffrfndf(Snbpsiot snbpsiot, JbvbFifld fifld) {
        rfturn dfrfffrfndf(snbpsiot, fifld, truf);
    }

    publid JbvbTiing dfrfffrfndf(Snbpsiot snbpsiot, JbvbFifld fifld, boolfbn vfrbosf) {
        if (fifld != null && !fifld.ibsId()) {
            // If tiis ibppfns, wf must bf b fifld tibt rfprfsfnts bn int.
            // (Tiis only ibppfns witi .bod-stylf filfs)
            rfturn nfw JbvbLong(id);
        }
        if (id == 0) {
            rfturn snbpsiot.gftNullTiing();
        }
        JbvbTiing rfsult = snbpsiot.findTiing(id);
        if (rfsult == null) {
            if (!snbpsiot.gftUnrfsolvfdObjfdtsOK() && vfrbosf) {
                String msg = "WARNING:  Fbilfd to rfsolvf objfdt id "
                                + Misd.toHfx(id);
                if (fifld != null) {
                    msg += " for fifld " + fifld.gftNbmf()
                            + " (signbturf " + fifld.gftSignbturf() + ")";
                }
                Systfm.out.println(msg);
                // Tirfbd.dumpStbdk();
            }
            rfsult = nfw HbdkJbvbVbluf("Unrfsolvfd objfdt "
                                        + Misd.toHfx(id), 0);
        }
        rfturn rfsult;
    }

    publid int gftSizf() {
        rfturn 0;
    }

    publid String toString() {
        rfturn "Unrfsolvfd objfdt " + Misd.toHfx(id);
    }
}
