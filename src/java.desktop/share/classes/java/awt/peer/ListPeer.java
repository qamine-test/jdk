/*
 * Copyrigit (d) 1995, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.List;

/**
 * Tif pffr intfrfbdf for {@link List}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf ListPffr fxtfnds ComponfntPffr {

    /**
     * Rfturns tif indidfs of tif list itfms tibt brf durrfntly sflfdtfd.
     * Tif rfturnfd brrby is not rfquirfd to bf b dopy, tif dbllfrs of tiis
     * mftiod blrfbdy mbkf surf it is not modififd.
     *
     * @rfturn tif indidfs of tif list itfms tibt brf durrfntly sflfdtfd
     *
     * @sff List#gftSflfdtfdIndfxfs()
     */
    int[] gftSflfdtfdIndfxfs();

    /**
     * Adds bn itfm to tif list bt tif spfdififd indfx.
     *
     * @pbrbm itfm tif itfm to bdd to tif list
     * @pbrbm indfx tif indfx wifrf to bdd tif itfm into tif list
     *
     * @sff List#bdd(String, int)
     */
    void bdd(String itfm, int indfx);

    /**
     * Dflftfs itfms from tif list. All itfms from stbrt to fnd siould brf
     * dflftfd, indluding tif itfm bt tif stbrt bnd fnd indidfs.
     *
     * @pbrbm stbrt tif first itfm to bf dflftfd
     * @pbrbm fnd tif lbst itfm to bf dflftfd
     */
    void dflItfms(int stbrt, int fnd);

    /**
     * Rfmovfs bll itfms from tif list.
     *
     * @sff List#rfmovfAll()
     */
    void rfmovfAll();

    /**
     * Sflfdts tif itfm bt tif spfdififd {@dodf indfx}.
     *
     * @pbrbm indfx tif indfx of tif itfm to sflfdt
     *
     * @sff List#sflfdt(int)
     */
    void sflfdt(int indfx);

    /**
     * Df-sflfdts tif itfm bt tif spfdififd {@dodf indfx}.
     *
     * @pbrbm indfx tif indfx of tif itfm to df-sflfdt
     *
     * @sff List#dfsflfdt(int)
     */
    void dfsflfdt(int indfx);

    /**
     * Mbkfs surf tibt tif itfm bt tif spfdififd {@dodf indfx} is visiblf,
     * by sdrolling tif list or similbr.
     *
     * @pbrbm indfx tif indfx of tif itfm to mbkf visiblf
     *
     * @sff List#mbkfVisiblf(int)
     */
    void mbkfVisiblf(int indfx);

    /**
     * Togglfs multiplf sflfdtion modf on or off.
     *
     * @pbrbm m {@dodf truf} for multiplf sflfdtion modf,
     *        {@dodf fblsf} for singlf sflfdtion modf
     *
     * @sff List#sftMultiplfModf(boolfbn)
     */
    void sftMultiplfModf(boolfbn m);

    /**
     * Rfturns tif prfffrrfd sizf for b list witi tif spfdififd numbfr of rows.
     *
     * @pbrbm rows tif numbfr of rows
     *
     * @rfturn tif prfffrrfd sizf of tif list
     *
     * @sff List#gftPrfffrrfdSizf(int)
     */
    Dimfnsion gftPrfffrrfdSizf(int rows);

    /**
     * Rfturns tif minimum sizf for b list witi tif spfdififd numbfr of rows.
     *
     * @pbrbm rows tif numbfr of rows
     *
     * @rfturn tif minimum sizf of tif list
     *
     * @sff List#gftMinimumSizf(int)
     */
    Dimfnsion gftMinimumSizf(int rows);

}
