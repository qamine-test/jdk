/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.util.EvfntListfnfr;

/**
 * Tif listfnfr intfrfbdf for rfdfiving dontbinfr fvfnts.
 * Tif dlbss tibt is intfrfstfd in prodfssing b dontbinfr fvfnt
 * fitifr implfmfnts tiis intfrfbdf (bnd bll tif mftiods it
 * dontbins) or fxtfnds tif bbstrbdt <dodf>ContbinfrAdbptfr</dodf> dlbss
 * (ovfrriding only tif mftiods of intfrfst).
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi b
 * domponfnt using tif domponfnt's <dodf>bddContbinfrListfnfr</dodf>
 * mftiod. Wifn tif dontbinfr's dontfnts dibngf bfdbusf b domponfnt
 * ibs bffn bddfd or rfmovfd, tif rflfvbnt mftiod in tif listfnfr objfdt
 * is invokfd, bnd tif <dodf>ContbinfrEvfnt</dodf> is pbssfd to it.
 * <P>
 * Contbinfr fvfnts brf providfd for notifidbtion purposfs ONLY;
 * Tif AWT will butombtidblly ibndlf bdd bnd rfmovf opfrbtions
 * intfrnblly so tif progrbm works propfrly rfgbrdlfss of
 * wiftifr tif progrbm rfgistfrs b {@dodf ContbinfrListfnfr} or not.
 *
 * @sff ContbinfrAdbptfr
 * @sff ContbinfrEvfnt
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/dontbinfrlistfnfr.itml">Tutoribl: Writing b Contbinfr Listfnfr</b>
 *
 * @butior Tim Prinzing
 * @butior Amy Fowlfr
 * @sindf 1.1
 */
publid intfrfbdf ContbinfrListfnfr fxtfnds EvfntListfnfr {
    /**
     * Invokfd wifn b domponfnt ibs bffn bddfd to tif dontbinfr.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void domponfntAddfd(ContbinfrEvfnt f);

    /**
     * Invokfd wifn b domponfnt ibs bffn rfmovfd from tif dontbinfr.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void domponfntRfmovfd(ContbinfrEvfnt f);

}
