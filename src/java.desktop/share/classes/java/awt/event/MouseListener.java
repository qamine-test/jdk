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
 * Tif listfnfr intfrfbdf for rfdfiving "intfrfsting" mousf fvfnts
 * (prfss, rflfbsf, dlidk, fntfr, bnd fxit) on b domponfnt.
 * (To trbdk mousf movfs bnd mousf drbgs, usf tif
 * <dodf>MousfMotionListfnfr</dodf>.)
 * <P>
 * Tif dlbss tibt is intfrfstfd in prodfssing b mousf fvfnt
 * fitifr implfmfnts tiis intfrfbdf (bnd bll tif mftiods it
 * dontbins) or fxtfnds tif bbstrbdt <dodf>MousfAdbptfr</dodf> dlbss
 * (ovfrriding only tif mftiods of intfrfst).
 * <P>
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi b
 * domponfnt using tif domponfnt's <dodf>bddMousfListfnfr</dodf>
 * mftiod. A mousf fvfnt is gfnfrbtfd wifn tif mousf is prfssfd, rflfbsfd
 * dlidkfd (prfssfd bnd rflfbsfd). A mousf fvfnt is blso gfnfrbtfd wifn
 * tif mousf dursor fntfrs or lfbvfs b domponfnt. Wifn b mousf fvfnt
 * oddurs, tif rflfvbnt mftiod in tif listfnfr objfdt is invokfd, bnd
 * tif <dodf>MousfEvfnt</dodf> is pbssfd to it.
 *
 * @butior Cbrl Quinn
 *
 * @sff MousfAdbptfr
 * @sff MousfEvfnt
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/mousflistfnfr.itml">Tutoribl: Writing b Mousf Listfnfr</b>
 *
 * @sindf 1.1
 */
publid intfrfbdf MousfListfnfr fxtfnds EvfntListfnfr {

    /**
     * Invokfd wifn tif mousf button ibs bffn dlidkfd (prfssfd
     * bnd rflfbsfd) on b domponfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void mousfClidkfd(MousfEvfnt f);

    /**
     * Invokfd wifn b mousf button ibs bffn prfssfd on b domponfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void mousfPrfssfd(MousfEvfnt f);

    /**
     * Invokfd wifn b mousf button ibs bffn rflfbsfd on b domponfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void mousfRflfbsfd(MousfEvfnt f);

    /**
     * Invokfd wifn tif mousf fntfrs b domponfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void mousfEntfrfd(MousfEvfnt f);

    /**
     * Invokfd wifn tif mousf fxits b domponfnt.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void mousfExitfd(MousfEvfnt f);
}
