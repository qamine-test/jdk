/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.bddfssibility;


/**
 * <P>Tif AddfssiblfTfxtSfqufndf providfs informbtion bbout
 * b dontiguous sfqufndf of tfxt.
 *
 * @sff Addfssiblf
 * @sff Addfssiblf#gftAddfssiblfContfxt
 * @sff AddfssiblfContfxt
 * @sff AddfssiblfContfxt#gftAddfssiblfTfxt
 * @sff AddfssiblfAttributfSfqufndf
 *
 * @butior       Lynn Monsbnto
 */

/**
 * Tiis dlbss dollfdts togftifr kfy dftbils of b spbn of tfxt.  It
 * is usfd by implfmfntors of tif dlbss <dodf>AddfssiblfExtfndfdTfxt</dodf> in
 * ordfr to rfturn tif rfqufstfd triplft of b <dodf>String</dodf>, bnd tif
 * stbrt bnd fnd indidifs/offsfts into b lbrgfr body of tfxt tibt tif
 * <dodf>String</dodf> domfs from.
 *
 * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt
 */
publid dlbss AddfssiblfTfxtSfqufndf {

    /** Tif stbrt indfx of tif tfxt sfqufndf */
    publid int stbrtIndfx;

    /** Tif fnd indfx of tif tfxt sfqufndf */
    publid int fndIndfx;

    /** Tif tfxt */
    publid String tfxt;

    /**
     * Construdts bn <dodf>AddfssiblfTfxtSfqufndf</dodf> witi tif givfn
     * pbrbmftfrs.
     *
     * @pbrbm stbrt tif bfginning indfx of tif spbn of tfxt
     * @pbrbm fnd tif fnding indfx of tif spbn of tfxt
     * @pbrbm txt tif <dodf>String</dodf> sibrfd by tiis tfxt spbn
     *
     * @sindf 1.6
     */
    publid AddfssiblfTfxtSfqufndf(int stbrt, int fnd, String txt) {
        stbrtIndfx = stbrt;
        fndIndfx = fnd;
        tfxt = txt;
    }
};
