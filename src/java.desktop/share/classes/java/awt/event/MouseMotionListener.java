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
 * Tif listfnfr intfrfbdf for rfdfiving mousf motion fvfnts on b domponfnt.
 * (For dlidks bnd otifr mousf fvfnts, usf tif <dodf>MousfListfnfr</dodf>.)
 * <P>
 * Tif dlbss tibt is intfrfstfd in prodfssing b mousf motion fvfnt
 * fitifr implfmfnts tiis intfrfbdf (bnd bll tif mftiods it
 * dontbins) or fxtfnds tif bbstrbdt <dodf>MousfMotionAdbptfr</dodf> dlbss
 * (ovfrriding only tif mftiods of intfrfst).
 * <P>
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi b
 * domponfnt using tif domponfnt's <dodf>bddMousfMotionListfnfr</dodf>
 * mftiod. A mousf motion fvfnt is gfnfrbtfd wifn tif mousf is movfd
 * or drbggfd. (Mbny sudi fvfnts will bf gfnfrbtfd). Wifn b mousf motion fvfnt
 * oddurs, tif rflfvbnt mftiod in tif listfnfr objfdt is invokfd, bnd
 * tif <dodf>MousfEvfnt</dodf> is pbssfd to it.
 *
 * @butior Amy Fowlfr
 *
 * @sff MousfMotionAdbptfr
 * @sff MousfEvfnt
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/mousfmotionlistfnfr.itml">Tutoribl: Writing b Mousf Motion Listfnfr</b>
 *
 * @sindf 1.1
 */
publid intfrfbdf MousfMotionListfnfr fxtfnds EvfntListfnfr {

    /**
     * Invokfd wifn b mousf button is prfssfd on b domponfnt bnd tifn
     * drbggfd.  <dodf>MOUSE_DRAGGED</dodf> fvfnts will dontinuf to bf
     * dflivfrfd to tif domponfnt wifrf tif drbg originbtfd until tif
     * mousf button is rflfbsfd (rfgbrdlfss of wiftifr tif mousf position
     * is witiin tif bounds of tif domponfnt).
     * <p>
     * Duf to plbtform-dfpfndfnt Drbg&bmp;Drop implfmfntbtions,
     * <dodf>MOUSE_DRAGGED</dodf> fvfnts mby not bf dflivfrfd during b nbtivf
     * Drbg&bmp;Drop opfrbtion.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void mousfDrbggfd(MousfEvfnt f);

    /**
     * Invokfd wifn tif mousf dursor ibs bffn movfd onto b domponfnt
     * but no buttons ibvf bffn pusifd.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void mousfMovfd(MousfEvfnt f);

}
