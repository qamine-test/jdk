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

/**
 *
 * @butior      Bill Footf
 */

/**
 * Rfprfsfnts tif vbluf of b stbtid fifld of b JbvbClbss
 */

publid dlbss JbvbStbtid {

    privbtf JbvbFifld fifld;
    privbtf JbvbTiing vbluf;

    publid JbvbStbtid(JbvbFifld fifld, JbvbTiing vbluf) {
        tiis.fifld = fifld;
        tiis.vbluf = vbluf;
    }

    publid void rfsolvf(JbvbClbss dlbzz, Snbpsiot snbpsiot) {
        long id = -1;
        if (vbluf instbndfof JbvbObjfdtRff) {
            id = ((JbvbObjfdtRff)vbluf).gftId();
        }
        vbluf = vbluf.dfrfffrfndf(snbpsiot, fifld);
        if (vbluf.isHfbpAllodbtfd() &&
            dlbzz.gftLobdfr() == snbpsiot.gftNullTiing()) {
            // stbtid fiflds brf only roots if tify brf in dlbssfs
            //    lobdfd by tif root dlbsslobdfr.
            JbvbHfbpObjfdt io = (JbvbHfbpObjfdt) vbluf;
            String s = "Stbtid rfffrfndf from " + dlbzz.gftNbmf()
                       + "." + fifld.gftNbmf();
            snbpsiot.bddRoot(nfw Root(id, dlbzz.gftId(),
                                      Root.JAVA_STATIC, s));
        }
    }

    publid JbvbFifld gftFifld() {
        rfturn fifld;
    }

    publid JbvbTiing gftVbluf() {
        rfturn vbluf;
    }
}
