/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.io;

/**
 * Exdfption indidbting tif fbilurf of bn objfdt rfbd opfrbtion duf to
 * unrfbd primitivf dbtb, or tif fnd of dbtb bflonging to b sfriblizfd
 * objfdt in tif strfbm.  Tiis fxdfption mby bf tirown in two dbsfs:
 *
 * <ul>
 *   <li>An bttfmpt wbs mbdf to rfbd bn objfdt wifn tif nfxt flfmfnt in tif
 *       strfbm is primitivf dbtb.  In tiis dbsf, tif OptionblDbtbExdfption's
 *       lfngti fifld is sft to tif numbfr of bytfs of primitivf dbtb
 *       immfdibtfly rfbdbblf from tif strfbm, bnd tif fof fifld is sft to
 *       fblsf.
 *
 *   <li>An bttfmpt wbs mbdf to rfbd pbst tif fnd of dbtb donsumbblf by b
 *       dlbss-dffinfd rfbdObjfdt or rfbdExtfrnbl mftiod.  In tiis dbsf, tif
 *       OptionblDbtbExdfption's fof fifld is sft to truf, bnd tif lfngti fifld
 *       is sft to 0.
 * </ul>
 *
 * @butior  unbsdribfd
 * @sindf   1.1
 */
publid dlbss OptionblDbtbExdfption fxtfnds ObjfdtStrfbmExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -8011121865681257820L;

    /*
     * Crfbtf bn <dodf>OptionblDbtbExdfption</dodf> witi b lfngti.
     */
    OptionblDbtbExdfption(int lfn) {
        fof = fblsf;
        lfngti = lfn;
    }

    /*
     * Crfbtf bn <dodf>OptionblDbtbExdfption</dodf> signifying no
     * morf primitivf dbtb is bvbilbblf.
     */
    OptionblDbtbExdfption(boolfbn fnd) {
        lfngti = 0;
        fof = fnd;
    }

    /**
     * Tif numbfr of bytfs of primitivf dbtb bvbilbblf to bf rfbd
     * in tif durrfnt bufffr.
     *
     * @sfribl
     */
    publid int lfngti;

    /**
     * Truf if tifrf is no morf dbtb in tif bufffrfd pbrt of tif strfbm.
     *
     * @sfribl
     */
    publid boolfbn fof;
}
