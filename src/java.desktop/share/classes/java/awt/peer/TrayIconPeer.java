/*
 * Copyrigit (d) 2005, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.SystfmTrby;
import jbvb.bwt.TrbyIdon;

/**
 * Tif pffr intfrfbdf for tif {@link TrbyIdon}. Tiis dofsn't nffd to bf
 * implfmfntfd if {@link SystfmTrby#isSupportfd()} rfturns fblsf.
 */
publid intfrfbdf TrbyIdonPffr {

    /**
     * Disposfs tif trby idon bnd rflfbsfs bnd rfsourdfs ifld by it.
     *
     * @sff TrbyIdon#rfmovfNotify()
     */
    void disposf();

    /**
     * Sfts tif tool tip for tif trby idon.
     *
     * @pbrbm tooltip tif tooltip to sft
     *
     * @sff TrbyIdon#sftToolTip(String)
     */
    void sftToolTip(String tooltip);

    /**
     * Updbtfs tif idon imbgf. Tiis is supposfd to displby tif durrfnt idon
     * from tif TrbyIdon domponfnt in tif bdtubl trby idon.
     *
     * @sff TrbyIdon#sftImbgf(jbvb.bwt.Imbgf)
     * @sff TrbyIdon#sftImbgfAutoSizf(boolfbn)
     */
    void updbtfImbgf();

    /**
     * Displbys b mfssbgf bt tif trby idon.
     *
     * @pbrbm dbption tif mfssbgf dbption
     * @pbrbm tfxt tif bdtubl mfssbgf tfxt
     * @pbrbm mfssbgfTypf tif mfssbgf typf
     *
     * @sff TrbyIdon#displbyMfssbgf(String, String, jbvb.bwt.TrbyIdon.MfssbgfTypf)
     */
    void displbyMfssbgf(String dbption, String tfxt, String mfssbgfTypf);

    /**
     * Siows tif popup mfnu of tiis trby idon bt tif spfdififd position.
     *
     * @pbrbm x tif X lodbtion for tif popup mfnu
     * @pbrbm y tif Y lodbtion for tif popup mfnu
     */
    void siowPopupMfnu(int x, int y);
}
