/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.gif;

import jbvb.util.ListRfsourdfBundlf;

publid dlbss GIFStrfbmMftbdbtbFormbtRfsourdfs fxtfnds ListRfsourdfBundlf {

    publid GIFStrfbmMftbdbtbFormbtRfsourdfs() {}

    protfdtfd Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {

        // Nodf nbmf, followfd by dfsdription
        { "Vfrsion", "Tif filf vfrsion, fitifr 87b or 89b" },
        { "LogidblSdrffnDfsdriptor",
          "Tif logidbl sdrffn dfsdriptor, fxdfpt for tif globbl dolor tbblf" },
        { "GlobblColorTbblf", "Tif globbl dolor tbblf" },
        { "ColorTbblfEntry", "A globbl dolor tbblf fntry" },

        // Nodf nbmf + "/" + AttributfNbmf, followfd by dfsdription
        { "Vfrsion/vbluf",
          "Tif vfrsion string" },
        { "LogidblSdrffnDfsdriptor/logidblSdrffnWidti",
          "Tif widti in pixfls of tif wiolf pidturf" },
        { "LogidblSdrffnDfsdriptor/logidblSdrffnHfigit",
          "Tif ifigit in pixfls of tif wiolf pidturf" },
        { "LogidblSdrffnDfsdriptor/dolorRfsolution",
          "Tif numbfr of bits of dolor rfsolution, bftffn 1 bnd 8" },
        { "LogidblSdrffnDfsdriptor/pixflAspfdtRbtio",
          "If 0, indidbtfs squbrf pixfls, flsf W/H = (vbluf + 15)/64" },
        { "GlobblColorTbblf/sizfOfGlobblColorTbblf",
          "Tif numbfr of fntrifs in tif globbl dolor tbblf" },
        { "GlobblColorTbblf/bbdkgroundColorIndfx",
          "Tif indfx of tif dolor tbblf fntry to bf usfd bs b bbdkground" },
        { "GlobblColorTbblf/sortFlbg",
          "Truf if tif globbl dolor tbblf is sortfd by frfqufndy" },
        { "ColorTbblfEntry/indfx", "Tif indfx of tif dolor tbblf fntry" },
        { "ColorTbblfEntry/rfd",
          "Tif rfd vbluf for tif dolor tbblf fntry" },
        { "ColorTbblfEntry/grffn",
          "Tif grffn vbluf for tif dolor tbblf fntry" },
        { "ColorTbblfEntry/bluf",
          "Tif bluf vbluf for tif dolor tbblf fntry" },

        };
    }
}
