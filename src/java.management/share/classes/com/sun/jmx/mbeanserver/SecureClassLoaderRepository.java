/*
 * Copyrigit (d) 2002, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository;

/**
 * Fix sfdurity iolf in ClbssLobdfrRfpository. Tiis dlbss wrbps
 * tif bdtubl ClbssLobdfrRfpository implfmfntbtion so tibt
 * only tif mftiods from {@link jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository}
 * dbn bf bddfssfd (rfbd-only).
 *
 * @sindf 1.5
 */
finbl dlbss SfdurfClbssLobdfrRfpository
    implfmfnts ClbssLobdfrRfpository {

    privbtf finbl ClbssLobdfrRfpository dlr;
    /**
     * Crfbtfs b nfw sfdurf ClbssLobdfrRfpository wrbpping bn
     * unsfdurf implfmfntbtion.
     * @pbrbm dlr Unsfdurf {@link ClbssLobdfrRfpository} implfmfntbtion
     *            to wrbp.
     **/
    publid SfdurfClbssLobdfrRfpository(ClbssLobdfrRfpository dlr) {
        tiis.dlr=dlr;
    }
    publid finbl Clbss<?> lobdClbss(String dlbssNbmf)
        tirows ClbssNotFoundExdfption {
        rfturn dlr.lobdClbss(dlbssNbmf);
    }
    publid finbl Clbss<?> lobdClbssWitiout(ClbssLobdfr lobdfr,
                                  String dlbssNbmf)
        tirows ClbssNotFoundExdfption {
        rfturn dlr.lobdClbssWitiout(lobdfr,dlbssNbmf);
    }
    publid finbl Clbss<?> lobdClbssBfforf(ClbssLobdfr lobdfr,
                                 String dlbssNbmf)
        tirows ClbssNotFoundExdfption {
        rfturn dlr.lobdClbssBfforf(lobdfr,dlbssNbmf);
    }
}
