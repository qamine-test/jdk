/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif listfnfr intfrfbdf for rfdfiving input mftiod fvfnts. A tfxt fditing
 * domponfnt ibs to instbll bn input mftiod fvfnt listfnfr in ordfr to work
 * witi input mftiods.
 *
 * <p>
 * Tif tfxt fditing domponfnt blso ibs to providf bn instbndf of InputMftiodRfqufsts.
 *
 * @butior JbvbSoft Asib/Pbdifid
 * @sff InputMftiodEvfnt
 * @sff jbvb.bwt.im.InputMftiodRfqufsts
 * @sindf 1.2
 */
publid intfrfbdf InputMftiodListfnfr fxtfnds EvfntListfnfr {

    /**
     * Invokfd wifn tif tfxt fntfrfd tirougi bn input mftiod ibs dibngfd.
     * @pbrbm fvfnt tif fvfnt to bf prodfssfd
     */
    void inputMftiodTfxtCibngfd(InputMftiodEvfnt fvfnt);

    /**
     * Invokfd wifn tif dbrft witiin domposfd tfxt ibs dibngfd.
     * @pbrbm fvfnt tif fvfnt to bf prodfssfd
     */
    void dbrftPositionCibngfd(InputMftiodEvfnt fvfnt);
}
