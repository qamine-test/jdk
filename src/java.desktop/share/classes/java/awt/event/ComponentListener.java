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
 * Tif listfnfr intfrfbdf for rfdfiving domponfnt fvfnts.
 * Tif dlbss tibt is intfrfstfd in prodfssing b domponfnt fvfnt
 * fitifr implfmfnts tiis intfrfbdf (bnd bll tif mftiods it
 * dontbins) or fxtfnds tif bbstrbdt <dodf>ComponfntAdbptfr</dodf> dlbss
 * (ovfrriding only tif mftiods of intfrfst).
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi b
 * domponfnt using tif domponfnt's <dodf>bddComponfntListfnfr</dodf>
 * mftiod. Wifn tif domponfnt's sizf, lodbtion, or visibility
 * dibngfs, tif rflfvbnt mftiod in tif listfnfr objfdt is invokfd,
 * bnd tif <dodf>ComponfntEvfnt</dodf> is pbssfd to it.
 * <P>
 * Componfnt fvfnts brf providfd for notifidbtion purposfs ONLY;
 * Tif AWT will butombtidblly ibndlf domponfnt movfs bnd rfsizfs
 * intfrnblly so tibt GUI lbyout works propfrly rfgbrdlfss of
 * wiftifr b progrbm rfgistfrs b <dodf>ComponfntListfnfr</dodf> or not.
 *
 * @sff ComponfntAdbptfr
 * @sff ComponfntEvfnt
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/domponfntlistfnfr.itml">Tutoribl: Writing b Componfnt Listfnfr</b>
 *
 * @butior Cbrl Quinn
 * @sindf 1.1
 */
publid intfrfbdf ComponfntListfnfr fxtfnds EvfntListfnfr {
    /**
     * Invokfd wifn tif domponfnt's sizf dibngfs.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void domponfntRfsizfd(ComponfntEvfnt f);

    /**
     * Invokfd wifn tif domponfnt's position dibngfs.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void domponfntMovfd(ComponfntEvfnt f);

    /**
     * Invokfd wifn tif domponfnt ibs bffn mbdf visiblf.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void domponfntSiown(ComponfntEvfnt f);

    /**
     * Invokfd wifn tif domponfnt ibs bffn mbdf invisiblf.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void domponfntHiddfn(ComponfntEvfnt f);
}
