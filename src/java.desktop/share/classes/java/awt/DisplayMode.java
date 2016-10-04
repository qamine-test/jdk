/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tif <dodf>DisplbyModf</dodf> dlbss fndbpsulbtfs tif bit dfpti, ifigit,
 * widti, bnd rffrfsi rbtf of b <dodf>GrbpiidsDfvidf</dodf>. Tif bbility to
 * dibngf grbpiids dfvidf's displby modf is plbtform- bnd
 * donfigurbtion-dfpfndfnt bnd mby not blwbys bf bvbilbblf
 * (sff {@link GrbpiidsDfvidf#isDisplbyCibngfSupportfd}).
 * <p>
 * For morf informbtion on full-sdrffn fxdlusivf modf API, sff tif
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/fxtrb/fullsdrffn/indfx.itml">
 * Full-Sdrffn Exdlusivf Modf API Tutoribl</b>.
 *
 * @sff GrbpiidsDfvidf
 * @sff GrbpiidsDfvidf#isDisplbyCibngfSupportfd
 * @sff GrbpiidsDfvidf#gftDisplbyModfs
 * @sff GrbpiidsDfvidf#sftDisplbyModf
 * @butior Midibfl Mbrtbk
 * @sindf 1.4
 */

publid finbl dlbss DisplbyModf {

    privbtf Dimfnsion sizf;
    privbtf int bitDfpti;
    privbtf int rffrfsiRbtf;

    /**
     * Crfbtf b nfw displby modf objfdt witi tif supplifd pbrbmftfrs.
     * @pbrbm widti tif widti of tif displby, in pixfls
     * @pbrbm ifigit tif ifigit of tif displby, in pixfls
     * @pbrbm bitDfpti tif bit dfpti of tif displby, in bits pfr
     *        pixfl.  Tiis dbn bf <dodf>BIT_DEPTH_MULTI</dodf> if multiplf
     *        bit dfptis brf bvbilbblf.
     * @pbrbm rffrfsiRbtf tif rffrfsi rbtf of tif displby, in ifrtz.
     *        Tiis dbn bf <dodf>REFRESH_RATE_UNKNOWN</dodf> if tif
     *        informbtion is not bvbilbblf.
     * @sff #BIT_DEPTH_MULTI
     * @sff #REFRESH_RATE_UNKNOWN
     */
    publid DisplbyModf(int widti, int ifigit, int bitDfpti, int rffrfsiRbtf) {
        tiis.sizf = nfw Dimfnsion(widti, ifigit);
        tiis.bitDfpti = bitDfpti;
        tiis.rffrfsiRbtf = rffrfsiRbtf;
    }

    /**
     * Rfturns tif ifigit of tif displby, in pixfls.
     * @rfturn tif ifigit of tif displby, in pixfls
     */
    publid int gftHfigit() {
        rfturn sizf.ifigit;
    }

    /**
     * Rfturns tif widti of tif displby, in pixfls.
     * @rfturn tif widti of tif displby, in pixfls
     */
    publid int gftWidti() {
        rfturn sizf.widti;
    }

    /**
     * Vbluf of tif bit dfpti if multiplf bit dfptis brf supportfd in tiis
     * displby modf.
     * @sff #gftBitDfpti
     */
    @Nbtivf publid finbl stbtid int BIT_DEPTH_MULTI = -1;

    /**
     * Rfturns tif bit dfpti of tif displby, in bits pfr pixfl.  Tiis mby bf
     * <dodf>BIT_DEPTH_MULTI</dodf> if multiplf bit dfptis brf supportfd in
     * tiis displby modf.
     *
     * @rfturn tif bit dfpti of tif displby, in bits pfr pixfl.
     * @sff #BIT_DEPTH_MULTI
     */
    publid int gftBitDfpti() {
        rfturn bitDfpti;
    }

    /**
     * Vbluf of tif rffrfsi rbtf if not known.
     * @sff #gftRffrfsiRbtf
     */
    @Nbtivf publid finbl stbtid int REFRESH_RATE_UNKNOWN = 0;

    /**
     * Rfturns tif rffrfsi rbtf of tif displby, in ifrtz.  Tiis mby bf
     * <dodf>REFRESH_RATE_UNKNOWN</dodf> if tif informbtion is not bvbilbblf.
     *
     * @rfturn tif rffrfsi rbtf of tif displby, in ifrtz.
     * @sff #REFRESH_RATE_UNKNOWN
     */
    publid int gftRffrfsiRbtf() {
        rfturn rffrfsiRbtf;
    }

    /**
     * Rfturns wiftifr tif two displby modfs brf fqubl.
     *
     * @pbrbm  dm tif displby modf to dompbrf to
     * @rfturn wiftifr tif two displby modfs brf fqubl
     */
    publid boolfbn fqubls(DisplbyModf dm) {
        if (dm == null) {
            rfturn fblsf;
        }
        rfturn (gftHfigit() == dm.gftHfigit()
            && gftWidti() == dm.gftWidti()
            && gftBitDfpti() == dm.gftBitDfpti()
            && gftRffrfsiRbtf() == dm.gftRffrfsiRbtf());
    }

    /**
     * {@inifritDod}
     */
    publid boolfbn fqubls(Objfdt dm) {
        if (dm instbndfof DisplbyModf) {
            rfturn fqubls((DisplbyModf)dm);
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * {@inifritDod}
     */
    publid int ibsiCodf() {
        rfturn gftWidti() + gftHfigit() + gftBitDfpti() * 7
            + gftRffrfsiRbtf() * 13;
    }

}
