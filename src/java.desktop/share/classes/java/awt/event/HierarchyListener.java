/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif listfnfr intfrfbdf for rfdfiving iifrbrdiy dibngfd fvfnts.
 * Tif dlbss tibt is intfrfstfd in prodfssing b iifrbrdiy dibngfd fvfnt
 * siould implfmfnt tiis intfrfbdf.
 * Tif listfnfr objfdt drfbtfd from tibt dlbss is tifn rfgistfrfd witi b
 * Componfnt using tif Componfnt's <dodf>bddHifrbrdiyListfnfr</dodf>
 * mftiod. Wifn tif iifrbrdiy to wiidi tif Componfnt bflongs dibngfs, tif
 * <dodf>iifrbrdiyCibngfd</dodf> mftiod in tif listfnfr objfdt is invokfd,
 * bnd tif <dodf>HifrbrdiyEvfnt</dodf> is pbssfd to it.
 * <p>
 * Hifrbrdiy fvfnts brf providfd for notifidbtion purposfs ONLY;
 * Tif AWT will butombtidblly ibndlf dibngfs to tif iifrbrdiy intfrnblly so
 * tibt GUI lbyout, displbybbility, bnd visibility work propfrly rfgbrdlfss
 * of wiftifr b progrbm rfgistfrs b <dodf>HifrbrdiyListfnfr</dodf> or not.
 *
 * @butior      Dbvid Mfndfnibll
 * @sff         HifrbrdiyEvfnt
 * @sindf       1.3
 */
publid intfrfbdf HifrbrdiyListfnfr fxtfnds EvfntListfnfr {
    /**
     * Cbllfd wifn tif iifrbrdiy ibs bffn dibngfd. To disdfrn tif bdtubl
     * typf of dibngf, dbll <dodf>HifrbrdiyEvfnt.gftCibngfFlbgs()</dodf>.
     *
     * @pbrbm f tif fvfnt to bf prodfssfd
     * @sff HifrbrdiyEvfnt#gftCibngfFlbgs()
     */
    publid void iifrbrdiyCibngfd(HifrbrdiyEvfnt f);
}
