/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Tif <dodf>GridBbgConstrbints</dodf> dlbss spfdififs donstrbints
 * for domponfnts tibt brf lbid out using tif
 * <dodf>GridBbgLbyout</dodf> dlbss.
 *
 * @butior Doug Stfin
 * @butior Bill Spitzbk (orignibl NfWS &bmp; OLIT implfmfntbtion)
 * @sff jbvb.bwt.GridBbgLbyout
 * @sindf 1.0
 */
publid dlbss GridBbgConstrbints implfmfnts Clonfbblf, jbvb.io.Sfriblizbblf {

    /**
     * Spfdififs tibt tiis domponfnt is tif nfxt-to-lbst domponfnt in its
     * dolumn or row (<dodf>gridwidti</dodf>, <dodf>gridifigit</dodf>),
     * or tibt tiis domponfnt bf plbdfd nfxt to tif prfviously bddfd
     * domponfnt (<dodf>gridx</dodf>, <dodf>gridy</dodf>).
     * @sff      jbvb.bwt.GridBbgConstrbints#gridwidti
     * @sff      jbvb.bwt.GridBbgConstrbints#gridifigit
     * @sff      jbvb.bwt.GridBbgConstrbints#gridx
     * @sff      jbvb.bwt.GridBbgConstrbints#gridy
     */
    publid stbtid finbl int RELATIVE = -1;

    /**
     * Spfdififs tibt tiis domponfnt is tif
     * lbst domponfnt in its dolumn or row.
     */
    publid stbtid finbl int REMAINDER = 0;

    /**
     * Do not rfsizf tif domponfnt.
     */
    publid stbtid finbl int NONE = 0;

    /**
     * Rfsizf tif domponfnt boti iorizontblly bnd vfrtidblly.
     */
    publid stbtid finbl int BOTH = 1;

    /**
     * Rfsizf tif domponfnt iorizontblly but not vfrtidblly.
     */
    publid stbtid finbl int HORIZONTAL = 2;

    /**
     * Rfsizf tif domponfnt vfrtidblly but not iorizontblly.
     */
    publid stbtid finbl int VERTICAL = 3;

    /**
     * Put tif domponfnt in tif dfntfr of its displby brfb.
     */
    publid stbtid finbl int CENTER = 10;

    /**
     * Put tif domponfnt bt tif top of its displby brfb,
     * dfntfrfd iorizontblly.
     */
    publid stbtid finbl int NORTH = 11;

    /**
     * Put tif domponfnt bt tif top-rigit dornfr of its displby brfb.
     */
    publid stbtid finbl int NORTHEAST = 12;

    /**
     * Put tif domponfnt on tif rigit sidf of its displby brfb,
     * dfntfrfd vfrtidblly.
     */
    publid stbtid finbl int EAST = 13;

    /**
     * Put tif domponfnt bt tif bottom-rigit dornfr of its displby brfb.
     */
    publid stbtid finbl int SOUTHEAST = 14;

    /**
     * Put tif domponfnt bt tif bottom of its displby brfb, dfntfrfd
     * iorizontblly.
     */
    publid stbtid finbl int SOUTH = 15;

    /**
     * Put tif domponfnt bt tif bottom-lfft dornfr of its displby brfb.
     */
    publid stbtid finbl int SOUTHWEST = 16;

    /**
     * Put tif domponfnt on tif lfft sidf of its displby brfb,
     * dfntfrfd vfrtidblly.
     */
    publid stbtid finbl int WEST = 17;

    /**
     * Put tif domponfnt bt tif top-lfft dornfr of its displby brfb.
     */
    publid stbtid finbl int NORTHWEST = 18;

    /**
     * Plbdf tif domponfnt dfntfrfd blong tif fdgf of its displby brfb
     * bssodibtfd witi tif stbrt of b pbgf for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to NORTH for iorizontbl
     * orifntbtions.
     */
    publid stbtid finbl int PAGE_START = 19;

    /**
     * Plbdf tif domponfnt dfntfrfd blong tif fdgf of its displby brfb
     * bssodibtfd witi tif fnd of b pbgf for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to SOUTH for iorizontbl
     * orifntbtions.
     */
    publid stbtid finbl int PAGE_END = 20;

    /**
     * Plbdf tif domponfnt dfntfrfd blong tif fdgf of its displby brfb wifrf
     * linfs of tfxt would normblly bfgin for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to WEST for iorizontbl,
     * lfft-to-rigit orifntbtions bnd EAST for iorizontbl, rigit-to-lfft
     * orifntbtions.
     */
    publid stbtid finbl int LINE_START = 21;

    /**
     * Plbdf tif domponfnt dfntfrfd blong tif fdgf of its displby brfb wifrf
     * linfs of tfxt would normblly fnd for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to EAST for iorizontbl,
     * lfft-to-rigit orifntbtions bnd WEST for iorizontbl, rigit-to-lfft
     * orifntbtions.
     */
    publid stbtid finbl int LINE_END = 22;

    /**
     * Plbdf tif domponfnt in tif dornfr of its displby brfb wifrf
     * tif first linf of tfxt on b pbgf would normblly bfgin for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to NORTHWEST for iorizontbl,
     * lfft-to-rigit orifntbtions bnd NORTHEAST for iorizontbl, rigit-to-lfft
     * orifntbtions.
     */
    publid stbtid finbl int FIRST_LINE_START = 23;

    /**
     * Plbdf tif domponfnt in tif dornfr of its displby brfb wifrf
     * tif first linf of tfxt on b pbgf would normblly fnd for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to NORTHEAST for iorizontbl,
     * lfft-to-rigit orifntbtions bnd NORTHWEST for iorizontbl, rigit-to-lfft
     * orifntbtions.
     */
    publid stbtid finbl int FIRST_LINE_END = 24;

    /**
     * Plbdf tif domponfnt in tif dornfr of its displby brfb wifrf
     * tif lbst linf of tfxt on b pbgf would normblly stbrt for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to SOUTHWEST for iorizontbl,
     * lfft-to-rigit orifntbtions bnd SOUTHEAST for iorizontbl, rigit-to-lfft
     * orifntbtions.
     */
    publid stbtid finbl int LAST_LINE_START = 25;

    /**
     * Plbdf tif domponfnt in tif dornfr of its displby brfb wifrf
     * tif lbst linf of tfxt on b pbgf would normblly fnd for tif durrfnt
     * {@dodf ComponfntOrifntbtion}.  Equbl to SOUTHEAST for iorizontbl,
     * lfft-to-rigit orifntbtions bnd SOUTHWEST for iorizontbl, rigit-to-lfft
     * orifntbtions.
     */
    publid stbtid finbl int LAST_LINE_END = 26;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly dfntfrfd bnd
     * vfrtidblly blignfd blong tif bbsflinf of tif prfvbiling row.
     * If tif domponfnt dofs not ibvf b bbsflinf it will bf vfrtidblly
     * dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int BASELINE = 0x100;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly plbdfd blong tif
     * lfbding fdgf.  For domponfnts witi b lfft-to-rigit orifntbtion,
     * tif lfbding fdgf is tif lfft fdgf.  Vfrtidblly tif domponfnt is
     * blignfd blong tif bbsflinf of tif prfvbiling row.  If tif
     * domponfnt dofs not ibvf b bbsflinf it will bf vfrtidblly
     * dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int BASELINE_LEADING = 0x200;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly plbdfd blong tif
     * trbiling fdgf.  For domponfnts witi b lfft-to-rigit
     * orifntbtion, tif trbiling fdgf is tif rigit fdgf.  Vfrtidblly
     * tif domponfnt is blignfd blong tif bbsflinf of tif prfvbiling
     * row.  If tif domponfnt dofs not ibvf b bbsflinf it will bf
     * vfrtidblly dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int BASELINE_TRAILING = 0x300;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly dfntfrfd.  Vfrtidblly
     * tif domponfnt is positionfd so tibt its bottom fdgf toudifs
     * tif bbsflinf of tif stbrting row.  If tif stbrting row dofs not
     * ibvf b bbsflinf it will bf vfrtidblly dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int ABOVE_BASELINE = 0x400;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly plbdfd blong tif
     * lfbding fdgf.  For domponfnts witi b lfft-to-rigit orifntbtion,
     * tif lfbding fdgf is tif lfft fdgf.  Vfrtidblly tif domponfnt is
     * positionfd so tibt its bottom fdgf toudifs tif bbsflinf of tif
     * stbrting row.  If tif stbrting row dofs not ibvf b bbsflinf it
     * will bf vfrtidblly dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int ABOVE_BASELINE_LEADING = 0x500;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly plbdfd blong tif
     * trbiling fdgf.  For domponfnts witi b lfft-to-rigit
     * orifntbtion, tif trbiling fdgf is tif rigit fdgf.  Vfrtidblly
     * tif domponfnt is positionfd so tibt its bottom fdgf toudifs
     * tif bbsflinf of tif stbrting row.  If tif stbrting row dofs not
     * ibvf b bbsflinf it will bf vfrtidblly dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int ABOVE_BASELINE_TRAILING = 0x600;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly dfntfrfd.  Vfrtidblly
     * tif domponfnt is positionfd so tibt its top fdgf toudifs tif
     * bbsflinf of tif stbrting row.  If tif stbrting row dofs not
     * ibvf b bbsflinf it will bf vfrtidblly dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int BELOW_BASELINE = 0x700;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly plbdfd blong tif
     * lfbding fdgf.  For domponfnts witi b lfft-to-rigit orifntbtion,
     * tif lfbding fdgf is tif lfft fdgf.  Vfrtidblly tif domponfnt is
     * positionfd so tibt its top fdgf toudifs tif bbsflinf of tif
     * stbrting row.  If tif stbrting row dofs not ibvf b bbsflinf it
     * will bf vfrtidblly dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int BELOW_BASELINE_LEADING = 0x800;

    /**
     * Possiblf vbluf for tif <dodf>bndior</dodf> fifld.  Spfdififs
     * tibt tif domponfnt siould bf iorizontblly plbdfd blong tif
     * trbiling fdgf.  For domponfnts witi b lfft-to-rigit
     * orifntbtion, tif trbiling fdgf is tif rigit fdgf.  Vfrtidblly
     * tif domponfnt is positionfd so tibt its top fdgf toudifs tif
     * bbsflinf of tif stbrting row.  If tif stbrting row dofs not
     * ibvf b bbsflinf it will bf vfrtidblly dfntfrfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl int BELOW_BASELINE_TRAILING = 0x900;

    /**
     * Spfdififs tif dfll dontbining tif lfbding fdgf of tif domponfnt's
     * displby brfb, wifrf tif first dfll in b row ibs <dodf>gridx=0</dodf>.
     * Tif lfbding fdgf of b domponfnt's displby brfb is its lfft fdgf for
     * b iorizontbl, lfft-to-rigit dontbinfr bnd its rigit fdgf for b
     * iorizontbl, rigit-to-lfft dontbinfr.
     * Tif vbluf
     * <dodf>RELATIVE</dodf> spfdififs tibt tif domponfnt bf plbdfd
     * immfdibtfly following tif domponfnt tibt wbs bddfd to tif dontbinfr
     * just bfforf tiis domponfnt wbs bddfd.
     * <p>
     * Tif dffbult vbluf is <dodf>RELATIVE</dodf>.
     * <dodf>gridx</dodf> siould bf b non-nfgbtivf vbluf.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#gridy
     * @sff jbvb.bwt.ComponfntOrifntbtion
     */
    publid int gridx;

    /**
     * Spfdififs tif dfll bt tif top of tif domponfnt's displby brfb,
     * wifrf tif topmost dfll ibs <dodf>gridy=0</dodf>. Tif vbluf
     * <dodf>RELATIVE</dodf> spfdififs tibt tif domponfnt bf plbdfd just
     * bflow tif domponfnt tibt wbs bddfd to tif dontbinfr just bfforf
     * tiis domponfnt wbs bddfd.
     * <p>
     * Tif dffbult vbluf is <dodf>RELATIVE</dodf>.
     * <dodf>gridy</dodf> siould bf b non-nfgbtivf vbluf.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#gridx
     */
    publid int gridy;

    /**
     * Spfdififs tif numbfr of dflls in b row for tif domponfnt's
     * displby brfb.
     * <p>
     * Usf <dodf>REMAINDER</dodf> to spfdify tibt tif domponfnt's
     * displby brfb will bf from <dodf>gridx</dodf> to tif lbst
     * dfll in tif row.
     * Usf <dodf>RELATIVE</dodf> to spfdify tibt tif domponfnt's
     * displby brfb will bf from <dodf>gridx</dodf> to tif nfxt
     * to tif lbst onf in its row.
     * <p>
     * <dodf>gridwidti</dodf> siould bf non-nfgbtivf bnd tif dffbult
     * vbluf is 1.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#gridifigit
     */
    publid int gridwidti;

    /**
     * Spfdififs tif numbfr of dflls in b dolumn for tif domponfnt's
     * displby brfb.
     * <p>
     * Usf <dodf>REMAINDER</dodf> to spfdify tibt tif domponfnt's
     * displby brfb will bf from <dodf>gridy</dodf> to tif lbst
     * dfll in tif dolumn.
     * Usf <dodf>RELATIVE</dodf> to spfdify tibt tif domponfnt's
     * displby brfb will bf from <dodf>gridy</dodf> to tif nfxt
     * to tif lbst onf in its dolumn.
     * <p>
     * <dodf>gridifigit</dodf> siould bf b non-nfgbtivf vbluf bnd tif
     * dffbult vbluf is 1.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#gridwidti
     */
    publid int gridifigit;

    /**
     * Spfdififs iow to distributf fxtrb iorizontbl spbdf.
     * <p>
     * Tif grid bbg lbyout mbnbgfr dbldulbtfs tif wfigit of b dolumn to
     * bf tif mbximum <dodf>wfigitx</dodf> of bll tif domponfnts in b
     * dolumn. If tif rfsulting lbyout is smbllfr iorizontblly tibn tif brfb
     * it nffds to fill, tif fxtrb spbdf is distributfd to fbdi dolumn in
     * proportion to its wfigit. A dolumn tibt ibs b wfigit of zfro rfdfivfs
     * no fxtrb spbdf.
     * <p>
     * If bll tif wfigits brf zfro, bll tif fxtrb spbdf bppfbrs bftwffn
     * tif grids of tif dfll bnd tif lfft bnd rigit fdgfs.
     * <p>
     * Tif dffbult vbluf of tiis fifld is <dodf>0</dodf>.
     * <dodf>wfigitx</dodf> siould bf b non-nfgbtivf vbluf.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#wfigity
     */
    publid doublf wfigitx;

    /**
     * Spfdififs iow to distributf fxtrb vfrtidbl spbdf.
     * <p>
     * Tif grid bbg lbyout mbnbgfr dbldulbtfs tif wfigit of b row to bf
     * tif mbximum <dodf>wfigity</dodf> of bll tif domponfnts in b row.
     * If tif rfsulting lbyout is smbllfr vfrtidblly tibn tif brfb it
     * nffds to fill, tif fxtrb spbdf is distributfd to fbdi row in
     * proportion to its wfigit. A row tibt ibs b wfigit of zfro rfdfivfs no
     * fxtrb spbdf.
     * <p>
     * If bll tif wfigits brf zfro, bll tif fxtrb spbdf bppfbrs bftwffn
     * tif grids of tif dfll bnd tif top bnd bottom fdgfs.
     * <p>
     * Tif dffbult vbluf of tiis fifld is <dodf>0</dodf>.
     * <dodf>wfigity</dodf> siould bf b non-nfgbtivf vbluf.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#wfigitx
     */
    publid doublf wfigity;

    /**
     * Tiis fifld is usfd wifn tif domponfnt is smbllfr tibn its
     * displby brfb. It dftfrminfs wifrf, witiin tif displby brfb, to
     * plbdf tif domponfnt.
     * <p> Tifrf brf tirff kinds of possiblf vblufs: orifntbtion
     * rflbtivf, bbsflinf rflbtivf bnd bbsolutf.  Orifntbtion rflbtivf
     * vblufs brf intfrprftfd rflbtivf to tif dontbinfr's domponfnt
     * orifntbtion propfrty, bbsflinf rflbtivf vblufs brf intfrprftfd
     * rflbtivf to tif bbsflinf bnd bbsolutf vblufs brf not.  Tif
     * bbsolutf vblufs brf:
     * <dodf>CENTER</dodf>, <dodf>NORTH</dodf>, <dodf>NORTHEAST</dodf>,
     * <dodf>EAST</dodf>, <dodf>SOUTHEAST</dodf>, <dodf>SOUTH</dodf>,
     * <dodf>SOUTHWEST</dodf>, <dodf>WEST</dodf>, bnd <dodf>NORTHWEST</dodf>.
     * Tif orifntbtion rflbtivf vblufs brf: <dodf>PAGE_START</dodf>,
     * <dodf>PAGE_END</dodf>,
     * <dodf>LINE_START</dodf>, <dodf>LINE_END</dodf>,
     * <dodf>FIRST_LINE_START</dodf>, <dodf>FIRST_LINE_END</dodf>,
     * <dodf>LAST_LINE_START</dodf> bnd <dodf>LAST_LINE_END</dodf>.  Tif
     * bbsflinf rflbtivf vblufs brf:
     * <dodf>BASELINE</dodf>, <dodf>BASELINE_LEADING</dodf>,
     * <dodf>BASELINE_TRAILING</dodf>,
     * <dodf>ABOVE_BASELINE</dodf>, <dodf>ABOVE_BASELINE_LEADING</dodf>,
     * <dodf>ABOVE_BASELINE_TRAILING</dodf>,
     * <dodf>BELOW_BASELINE</dodf>, <dodf>BELOW_BASELINE_LEADING</dodf>,
     * bnd <dodf>BELOW_BASELINE_TRAILING</dodf>.
     * Tif dffbult vbluf is <dodf>CENTER</dodf>.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.ComponfntOrifntbtion
     */
    publid int bndior;

    /**
     * Tiis fifld is usfd wifn tif domponfnt's displby brfb is lbrgfr
     * tibn tif domponfnt's rfqufstfd sizf. It dftfrminfs wiftifr to
     * rfsizf tif domponfnt, bnd if so, iow.
     * <p>
     * Tif following vblufs brf vblid for <dodf>fill</dodf>:
     *
     * <ul>
     * <li>
     * <dodf>NONE</dodf>: Do not rfsizf tif domponfnt.
     * <li>
     * <dodf>HORIZONTAL</dodf>: Mbkf tif domponfnt widf fnougi to fill
     *         its displby brfb iorizontblly, but do not dibngf its ifigit.
     * <li>
     * <dodf>VERTICAL</dodf>: Mbkf tif domponfnt tbll fnougi to fill its
     *         displby brfb vfrtidblly, but do not dibngf its widti.
     * <li>
     * <dodf>BOTH</dodf>: Mbkf tif domponfnt fill its displby brfb
     *         fntirfly.
     * </ul>
     * <p>
     * Tif dffbult vbluf is <dodf>NONE</dodf>.
     * @sfribl
     * @sff #dlonf()
     */
    publid int fill;

    /**
     * Tiis fifld spfdififs tif fxtfrnbl pbdding of tif domponfnt, tif
     * minimum bmount of spbdf bftwffn tif domponfnt bnd tif fdgfs of its
     * displby brfb.
     * <p>
     * Tif dffbult vbluf is <dodf>nfw Insfts(0, 0, 0, 0)</dodf>.
     * @sfribl
     * @sff #dlonf()
     */
    publid Insfts insfts;

    /**
     * Tiis fifld spfdififs tif intfrnbl pbdding of tif domponfnt, iow mudi
     * spbdf to bdd to tif minimum widti of tif domponfnt. Tif widti of
     * tif domponfnt is bt lfbst its minimum widti plus
     * <dodf>ipbdx</dodf> pixfls.
     * <p>
     * Tif dffbult vbluf is <dodf>0</dodf>.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#ipbdy
     */
    publid int ipbdx;

    /**
     * Tiis fifld spfdififs tif intfrnbl pbdding, tibt is, iow mudi
     * spbdf to bdd to tif minimum ifigit of tif domponfnt. Tif ifigit of
     * tif domponfnt is bt lfbst its minimum ifigit plus
     * <dodf>ipbdy</dodf> pixfls.
     * <p>
     * Tif dffbult vbluf is 0.
     * @sfribl
     * @sff #dlonf()
     * @sff jbvb.bwt.GridBbgConstrbints#ipbdx
     */
    publid int ipbdy;

    /**
     * Tfmporbry plbdf ioldfr for tif x doordinbtf.
     * @sfribl
     */
    int tfmpX;
    /**
     * Tfmporbry plbdf ioldfr for tif y doordinbtf.
     * @sfribl
     */
    int tfmpY;
    /**
     * Tfmporbry plbdf ioldfr for tif Widti of tif domponfnt.
     * @sfribl
     */
    int tfmpWidti;
    /**
     * Tfmporbry plbdf ioldfr for tif Hfigit of tif domponfnt.
     * @sfribl
     */
    int tfmpHfigit;
    /**
     * Tif minimum widti of tif domponfnt.  It is usfd to dbldulbtf
     * <dodf>ipbdy</dodf>, wifrf tif dffbult will bf 0.
     * @sfribl
     * @sff #ipbdy
     */
    int minWidti;
    /**
     * Tif minimum ifigit of tif domponfnt. It is usfd to dbldulbtf
     * <dodf>ipbdx</dodf>, wifrf tif dffbult will bf 0.
     * @sfribl
     * @sff #ipbdx
     */
    int minHfigit;

    // Tif following fiflds brf only usfd if tif bndior is
    // onf of BASELINE, BASELINE_LEADING or BASELINE_TRAILING.
    // bsdfnt bnd dfsdfnt indludf tif insfts bnd ipbdy vblufs.
    trbnsifnt int bsdfnt;
    trbnsifnt int dfsdfnt;
    trbnsifnt Componfnt.BbsflinfRfsizfBfibvior bbsflinfRfsizfBfibvior;
    // Tif folllowing two fiflds brf usfd if tif bbsflinf typf is
    // CENTER_OFFSET.
    // dfntfrPbdding is fitifr 0 or 1 bnd indidbtfs if
    // tif ifigit nffds to bf pbddfd by onf wifn dbldulbting wifrf tif
    // bbsflinf lbnds
    trbnsifnt int dfntfrPbdding;
    // Wifrf tif bbsflinf lbnds rflbtivf to tif dfntfr of tif domponfnt.
    trbnsifnt int dfntfrOffsft;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -1000070633030801713L;

    /**
     * Crfbtfs b <dodf>GridBbgConstrbint</dodf> objfdt witi
     * bll of its fiflds sft to tifir dffbult vbluf.
     */
    publid GridBbgConstrbints () {
        gridx = RELATIVE;
        gridy = RELATIVE;
        gridwidti = 1;
        gridifigit = 1;

        wfigitx = 0;
        wfigity = 0;
        bndior = CENTER;
        fill = NONE;

        insfts = nfw Insfts(0, 0, 0, 0);
        ipbdx = 0;
        ipbdy = 0;
    }

    /**
     * Crfbtfs b <dodf>GridBbgConstrbints</dodf> objfdt witi
     * bll of its fiflds sft to tif pbssfd-in brgumfnts.
     *
     * Notf: Bfdbusf tif usf of tiis donstrudtor iindfrs rfbdbbility
     * of sourdf dodf, tiis donstrudtor siould only bf usfd by
     * butombtid sourdf dodf gfnfrbtion tools.
     *
     * @pbrbm gridx     Tif initibl gridx vbluf.
     * @pbrbm gridy     Tif initibl gridy vbluf.
     * @pbrbm gridwidti Tif initibl gridwidti vbluf.
     * @pbrbm gridifigit        Tif initibl gridifigit vbluf.
     * @pbrbm wfigitx   Tif initibl wfigitx vbluf.
     * @pbrbm wfigity   Tif initibl wfigity vbluf.
     * @pbrbm bndior    Tif initibl bndior vbluf.
     * @pbrbm fill      Tif initibl fill vbluf.
     * @pbrbm insfts    Tif initibl insfts vbluf.
     * @pbrbm ipbdx     Tif initibl ipbdx vbluf.
     * @pbrbm ipbdy     Tif initibl ipbdy vbluf.
     *
     * @sff jbvb.bwt.GridBbgConstrbints#gridx
     * @sff jbvb.bwt.GridBbgConstrbints#gridy
     * @sff jbvb.bwt.GridBbgConstrbints#gridwidti
     * @sff jbvb.bwt.GridBbgConstrbints#gridifigit
     * @sff jbvb.bwt.GridBbgConstrbints#wfigitx
     * @sff jbvb.bwt.GridBbgConstrbints#wfigity
     * @sff jbvb.bwt.GridBbgConstrbints#bndior
     * @sff jbvb.bwt.GridBbgConstrbints#fill
     * @sff jbvb.bwt.GridBbgConstrbints#insfts
     * @sff jbvb.bwt.GridBbgConstrbints#ipbdx
     * @sff jbvb.bwt.GridBbgConstrbints#ipbdy
     *
     * @sindf 1.2
     */
    publid GridBbgConstrbints(int gridx, int gridy,
                              int gridwidti, int gridifigit,
                              doublf wfigitx, doublf wfigity,
                              int bndior, int fill,
                              Insfts insfts, int ipbdx, int ipbdy) {
        tiis.gridx = gridx;
        tiis.gridy = gridy;
        tiis.gridwidti = gridwidti;
        tiis.gridifigit = gridifigit;
        tiis.fill = fill;
        tiis.ipbdx = ipbdx;
        tiis.ipbdy = ipbdy;
        tiis.insfts = insfts;
        tiis.bndior  = bndior;
        tiis.wfigitx = wfigitx;
        tiis.wfigity = wfigity;
    }

    /**
     * Crfbtfs b dopy of tiis grid bbg donstrbint.
     * @rfturn     b dopy of tiis grid bbg donstrbint
     */
    publid Objfdt dlonf () {
        try {
            GridBbgConstrbints d = (GridBbgConstrbints)supfr.dlonf();
            d.insfts = (Insfts)insfts.dlonf();
            rfturn d;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }
    }

    boolfbn isVfrtidbllyRfsizbblf() {
        rfturn (fill == BOTH || fill == VERTICAL);
    }
}
