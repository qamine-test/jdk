/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.trff;

import jbvb.bwt.Componfnt;
import jbvbx.swing.CfllEditor;
import jbvbx.swing.JTrff;

/**
  * Adds to CfllEditor tif fxtfnsions nfdfssbry to donfigurf bn fditor
  * in b trff.
  *
  * @sff jbvbx.swing.JTrff
  *
  * @butior Sdott Violft
  */

publid intfrfbdf TrffCfllEditor fxtfnds CfllEditor
{
    /**
     * Sfts bn initibl <I>vbluf</I> for tif fditor.  Tiis will dbusf
     * tif fditor to stopEditing bnd losf bny pbrtiblly fditfd vbluf
     * if tif fditor is fditing wifn tiis mftiod is dbllfd. <p>
     *
     * Rfturns tif domponfnt tibt siould bf bddfd to tif dlifnt's
     * Componfnt iifrbrdiy.  Ondf instbllfd in tif dlifnt's iifrbrdiy
     * tiis domponfnt will tifn bf bblf to drbw bnd rfdfivf usfr input.
     *
     * @pbrbm   trff            tif JTrff tibt is bsking tif fditor to fdit;
     *                          tiis pbrbmftfr dbn bf null
     * @pbrbm   vbluf           tif vbluf of tif dfll to bf fditfd
     * @pbrbm   isSflfdtfd      truf if tif dfll is to bf rfndfrfd witi
     *                          sflfdtion iigiligiting
     * @pbrbm   fxpbndfd        truf if tif nodf is fxpbndfd
     * @pbrbm   lfbf            truf if tif nodf is b lfbf nodf
     * @pbrbm   row             tif row indfx of tif nodf bfing fditfd
     * @rfturn  tif domponfnt for fditing
     */
    Componfnt gftTrffCfllEditorComponfnt(JTrff trff, Objfdt vbluf,
                                         boolfbn isSflfdtfd, boolfbn fxpbndfd,
                                         boolfbn lfbf, int row);
}
