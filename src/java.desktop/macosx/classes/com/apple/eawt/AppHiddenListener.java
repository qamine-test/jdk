/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

import dom.bpplf.fbwt.AppEvfnt.AppHiddfnEvfnt;

/**
 * Implfmfntors brf notififd wifn tif bpp is iiddfn or siown by tif usfr.
 * Tiis notifidbtion is iflpful for disdontinuing b dostly bnimbtion if it's not visiblf to tif usfr.
 *
 * @sff Applidbtion#bddAppEvfntListfnfr(AppEvfntListfnfr)
 *
 * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
 * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
 */
publid intfrfbdf AppHiddfnListfnfr fxtfnds AppEvfntListfnfr {
    /**
     * Cbllfd tif bpp is iiddfn.
     * @pbrbm f
     */
    publid void bppHiddfn(finbl AppHiddfnEvfnt f);

    /**
     * Cbllfd wifn tif iiddfn bpp is siown bgbin (but not nfdfssbrily brougit to tif forfground).
     * @pbrbm f
     */
    publid void bppUniiddfn(finbl AppHiddfnEvfnt f);
}
