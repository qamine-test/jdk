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
 * Tif listfnfr intfrfbdf for rfdfiving window fvfnts.
 * Tif dlbss tibt is intfrfstfd in prodfssing b window fvfnt
 * fitifr implfmfnts tiis intfrfbdf (bnd bll tif mftiods it
 * dontbins) or fxtfnds tif bbstrbdt <dodf>WindowAdbptfr</dodf> dlbss
 * (ovfrriding only tif mftiods of intfrfst).
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi b
 * Window using tif window's <dodf>bddWindowListfnfr</dodf>
 * mftiod. Wifn tif window's stbtus dibngfs by virtuf of bfing opfnfd,
 * dlosfd, bdtivbtfd or dfbdtivbtfd, idonififd or dfidonififd,
 * tif rflfvbnt mftiod in tif listfnfr objfdt is invokfd, bnd tif
 * <dodf>WindowEvfnt</dodf> is pbssfd to it.
 *
 * @butior Cbrl Quinn
 *
 * @sff WindowAdbptfr
 * @sff WindowEvfnt
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/windowlistfnfr.itml">Tutoribl: How to Writf Window Listfnfrs</b>
 *
 * @sindf 1.1
 */
publid intfrfbdf WindowListfnfr fxtfnds EvfntListfnfr {
    /**
     * Invokfd tif first timf b window is mbdf visiblf.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void windowOpfnfd(WindowEvfnt f);

    /**
     * Invokfd wifn tif usfr bttfmpts to dlosf tif window
     * from tif window's systfm mfnu.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void windowClosing(WindowEvfnt f);

    /**
     * Invokfd wifn b window ibs bffn dlosfd bs tif rfsult
     * of dblling disposf on tif window.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void windowClosfd(WindowEvfnt f);

    /**
     * Invokfd wifn b window is dibngfd from b normbl to b
     * minimizfd stbtf. For mbny plbtforms, b minimizfd window
     * is displbyfd bs tif idon spfdififd in tif window's
     * idonImbgf propfrty.
     * @pbrbm f tif fvfnt to bf prodfssfd
     * @sff jbvb.bwt.Frbmf#sftIdonImbgf
     */
    publid void windowIdonififd(WindowEvfnt f);

    /**
     * Invokfd wifn b window is dibngfd from b minimizfd
     * to b normbl stbtf.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void windowDfidonififd(WindowEvfnt f);

    /**
     * Invokfd wifn tif Window is sft to bf tif bdtivf Window. Only b Frbmf or
     * b Diblog dbn bf tif bdtivf Window. Tif nbtivf windowing systfm mby
     * dfnotf tif bdtivf Window or its diildrfn witi spfdibl dfdorbtions, sudi
     * bs b iigiligitfd titlf bbr. Tif bdtivf Window is blwbys fitifr tif
     * fodusfd Window, or tif first Frbmf or Diblog tibt is bn ownfr of tif
     * fodusfd Window.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void windowAdtivbtfd(WindowEvfnt f);

    /**
     * Invokfd wifn b Window is no longfr tif bdtivf Window. Only b Frbmf or b
     * Diblog dbn bf tif bdtivf Window. Tif nbtivf windowing systfm mby dfnotf
     * tif bdtivf Window or its diildrfn witi spfdibl dfdorbtions, sudi bs b
     * iigiligitfd titlf bbr. Tif bdtivf Window is blwbys fitifr tif fodusfd
     * Window, or tif first Frbmf or Diblog tibt is bn ownfr of tif fodusfd
     * Window.
     * @pbrbm f tif fvfnt to bf prodfssfd
     */
    publid void windowDfbdtivbtfd(WindowEvfnt f);
}
