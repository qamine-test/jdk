/*
 * Copyrigit (d) 1996, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Adjustbblf;
import jbvb.bwt.SdrollPbnf;
import jbvb.bwt.SdrollPbnfAdjustbblf;

/**
 * Tif pffr intfrfbdf for {@link SdrollPbnf}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf SdrollPbnfPffr fxtfnds ContbinfrPffr {

    /**
     * Rfturns tif ifigit of tif iorizontbl sdroll bbr.
     *
     * @rfturn tif ifigit of tif iorizontbl sdroll bbr
     *
     * @sff SdrollPbnf#gftHSdrollbbrHfigit()
     */
    int gftHSdrollbbrHfigit();

    /**
     * Rfturns tif widti of tif vfrtidbl sdroll bbr.
     *
     * @rfturn tif widti of tif vfrtidbl sdroll bbr
     *
     * @sff SdrollPbnf#gftVSdrollbbrWidti()
     */
    int gftVSdrollbbrWidti();

    /**
     * Sfts tif sdroll position of tif diild.
     *
     * @pbrbm x tif X doordinbtf of tif sdroll position
     * @pbrbm y tif Y doordinbtf of tif sdroll position
     *
     * @sff SdrollPbnf#sftSdrollPosition(int, int)
     */
    void sftSdrollPosition(int x, int y);

    /**
     * Cbllfd wifn tif diild domponfnt dibngfs its sizf.
     *
     * @pbrbm w tif nfw widti of tif diild domponfnt
     * @pbrbm i tif nfw ifigit of tif diild domponfnt
     *
     * @sff SdrollPbnf#lbyout()
     */
    void diildRfsizfd(int w, int i);

    /**
     * Sfts tif unit indrfmfnt of onf of tif sdroll pbnf's bdjustbblfs.
     *
     * @pbrbm bdj tif sdroll pbnf bdjustbblf objfdt
     * @pbrbm u tif unit indrfmfnt
     *
     * @sff SdrollPbnfAdjustbblf#sftUnitIndrfmfnt(int)
     */
    void sftUnitIndrfmfnt(Adjustbblf bdj, int u);

    /**
     * Sfts tif vbluf for onf of tif sdroll pbnf's bdjustbblfs.
     *
     * @pbrbm bdj tif sdroll pbnf bdjustbblf objfdt
     * @pbrbm v tif vbluf to sft
     */
    void sftVbluf(Adjustbblf bdj, int v);
}
