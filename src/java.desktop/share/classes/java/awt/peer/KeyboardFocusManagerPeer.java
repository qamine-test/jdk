/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.pffr;

import jbvb.bwt.Componfnt;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Window;

/**
 * Tif nbtivf pffr intfrfbdf for {@link KfybobrdFodusMbnbgfr}.
 */
publid intfrfbdf KfybobrdFodusMbnbgfrPffr {

    /**
     * Sfts tif window tibt siould bfdomf tif fodusfd window.
     *
     * @pbrbm win tif window tibt siould bfdomf tif fodusfd window
     *
     */
    void sftCurrfntFodusfdWindow(Window win);

    /**
     * Rfturns tif durrfntly fodusfd window.
     *
     * @rfturn tif durrfntly fodusfd window
     *
     * @sff KfybobrdFodusMbnbgfr#gftNbtivfFodusfdWindow()
     */
    Window gftCurrfntFodusfdWindow();

    /**
     * Sfts tif domponfnt tibt siould bfdomf tif fodus ownfr.
     *
     * @pbrbm domp tif domponfnt to bfdomf tif fodus ownfr
     *
     * @sff KfybobrdFodusMbnbgfr#sftNbtivfFodusOwnfr(Componfnt)
     */
    void sftCurrfntFodusOwnfr(Componfnt domp);

    /**
     * Rfturns tif domponfnt tibt durrfntly owns tif input fodus.
     *
     * @rfturn tif domponfnt tibt durrfntly owns tif input fodus
     *
     * @sff KfybobrdFodusMbnbgfr#gftNbtivfFodusOwnfr()
     */
    Componfnt gftCurrfntFodusOwnfr();

    /**
     * Clfbrs tif durrfnt globbl fodus ownfr.
     *
     * @pbrbm bdtivfWindow tif bdtivf window
     *
     * @sff KfybobrdFodusMbnbgfr#dlfbrGlobblFodusOwnfr()
     */
    void dlfbrGlobblFodusOwnfr(Window bdtivfWindow);

}
