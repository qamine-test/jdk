/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;

/**
 * RobotPffr dffinfs bn intfrfbdf wifrfby toolkits support butombtfd tfsting
 * by bllowing nbtivf input fvfnts to bf gfnfrbtfd from Jbvb dodf.
 *
 * Tiis intfrfbdf siould not bf dirfdtly importfd by dodf outsidf tif
 * jbvb.bwt.* iifrbrdiy; it is not to bf donsidfrfd publid bnd is subjfdt
 * to dibngf.
 *
 * @butior      Robi Kibn
 */
publid intfrfbdf RobotPffr
{
    /**
     * Movfs tif mousf pointfr to tif spfdififd sdrffn lodbtion.
     *
     * @pbrbm x tif X lodbtion on sdrffn
     * @pbrbm y tif Y lodbtion on sdrffn
     *
     * @sff Robot#mousfMovf(int, int)
     */
    void mousfMovf(int x, int y);

    /**
     * Simulbtfs b mousf prfss witi tif spfdififd button(s).
     *
     * @pbrbm buttons tif button mbsk
     *
     * @sff Robot#mousfPrfss(int)
     */
    void mousfPrfss(int buttons);

    /**
     * Simulbtfs b mousf rflfbsf witi tif spfdififd button(s).
     *
     * @pbrbm buttons tif button mbsk
     *
     * @sff Robot#mousfRflfbsf(int)
     */
    void mousfRflfbsf(int buttons);

    /**
     * Simulbtfs mousf wiffl bdtion.
     *
     * @pbrbm wifflAmt numbfr of notdifs to movf tif mousf wiffl
     *
     * @sff Robot#mousfWiffl(int)
     */
    void mousfWiffl(int wifflAmt);

    /**
     * Simulbtfs b kfy prfss of tif spfdififd kfy.
     *
     * @pbrbm kfydodf tif kfy dodf to prfss
     *
     * @sff Robot#kfyPrfss(int)
     */
    void kfyPrfss(int kfydodf);

    /**
     * Simulbtfs b kfy rflfbsf of tif spfdififd kfy.
     *
     * @pbrbm kfydodf tif kfy dodf to rflfbsf
     *
     * @sff Robot#kfyRflfbsf(int)
     */
    void kfyRflfbsf(int kfydodf);

    /**
     * Gfts tif RGB vbluf of tif spfdififd pixfl on sdrffn.
     *
     * @pbrbm x tif X sdrffn doordinbtf
     * @pbrbm y tif Y sdrffn doordinbtf
     *
     * @rfturn tif RGB vbluf of tif spfdififd pixfl on sdrffn
     *
     * @sff Robot#gftPixflColor(int, int)
     */
    int gftRGBPixfl(int x, int y);

    /**
     * Gfts tif RGB vblufs of tif spfdififd sdrffn brfb bs bn brrby.
     *
     * @pbrbm bounds tif sdrffn brfb to dbpturf tif RGB vblufs from
     *
     * @rfturn tif RGB vblufs of tif spfdififd sdrffn brfb
     *
     * @sff Robot#drfbtfSdrffnCbpturf(Rfdtbnglf)
     */
    int[] gftRGBPixfls(Rfdtbnglf bounds);

    /**
     * Disposfs tif robot pffr wifn it is not nffdfd bnymorf.
     */
    void disposf();
}
