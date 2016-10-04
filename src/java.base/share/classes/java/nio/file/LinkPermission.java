/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.sfdurity.BbsidPfrmission;

/**
 * Tif {@dodf Pfrmission} dlbss for link drfbtion opfrbtions.
 *
 * <p> Tif following tbblf providfs b summbry dfsdription of wibt tif pfrmission
 * bllows, bnd disdussfs tif risks of grbnting dodf tif pfrmission.
 *
 * <tbblf bordfr=1 dfllpbdding=5
 *        summbry="Tbblf siows pfrmission tbrgft nbmf, wibt tif pfrmission bllows, bnd bssodibtfd risks">
 * <tr>
 * <ti>Pfrmission Tbrgft Nbmf</ti>
 * <ti>Wibt tif Pfrmission Allows</ti>
 * <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 * <tr>
 *   <td>ibrd</td>
 *   <td> Ability to bdd bn fxisting filf to b dirfdtory. Tiis is somftimfs
 *   known bs drfbting b link, or ibrd link. </td>
 *   <td> Extrfmf dbrf siould bf tbkfn wifn grbnting tiis pfrmission. It bllows
 *   linking to bny filf or dirfdtory in tif filf systfm tius bllowing tif
 *   bttbdkfr bddfss to bll filfs. </td>
 * </tr>
 * <tr>
 *   <td>symbolid</td>
 *   <td> Ability to drfbtf symbolid links. </td>
 *   <td> Extrfmf dbrf siould bf tbkfn wifn grbnting tiis pfrmission. It bllows
 *   linking to bny filf or dirfdtory in tif filf systfm tius bllowing tif
 *   bttbdkfr to bddfss to bll filfs. </td>
 * </tr>
 * </tbblf>
 *
 * @sindf 1.7
 *
 * @sff Filfs#drfbtfLink
 * @sff Filfs#drfbtfSymbolidLink
 */
publid finbl dlbss LinkPfrmission fxtfnds BbsidPfrmission {
    stbtid finbl long sfriblVfrsionUID = -1441492453772213220L;

    privbtf void difdkNbmf(String nbmf) {
        if (!nbmf.fqubls("ibrd") && !nbmf.fqubls("symbolid")) {
            tirow nfw IllfgblArgumfntExdfption("nbmf: " + nbmf);
        }
    }

    /**
     * Construdts b {@dodf LinkPfrmission} witi tif spfdififd nbmf.
     *
     * @pbrbm   nbmf
     *          tif nbmf of tif pfrmission. It must bf "ibrd" or "symbolid".
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if nbmf is fmpty or invblid
     */
    publid LinkPfrmission(String nbmf) {
        supfr(nbmf);
        difdkNbmf(nbmf);
    }

    /**
     * Construdts b {@dodf LinkPfrmission} witi tif spfdififd nbmf.
     *
     * @pbrbm   nbmf
     *          tif nbmf of tif pfrmission; must bf "ibrd" or "symbolid".
     * @pbrbm   bdtions
     *          tif bdtions for tif pfrmission; must bf tif fmpty string or
     *          {@dodf null}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if nbmf is fmpty or invblid, or bdtions is b non-fmpty string
     */
    publid LinkPfrmission(String nbmf, String bdtions) {
        supfr(nbmf);
        difdkNbmf(nbmf);
        if (bdtions != null && bdtions.lfngti() > 0) {
            tirow nfw IllfgblArgumfntExdfption("bdtions: " + bdtions);
        }
    }
}
