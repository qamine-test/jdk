/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

/**
 * Undifdkfd fxdfption tirown wifn bn illfgbl dombinbtion flbgs is givfn.
 *
 * <p> Unlfss otifrwisf spfdififd, pbssing b <tt>null</tt> brgumfnt to bny
 * mftiod or donstrudtor in tiis dlbss will dbusf b {@link
 * NullPointfrExdfption} to bf tirown.
 *
 * @sindf 1.5
 */
publid dlbss IllfgblFormbtFlbgsExdfption fxtfnds IllfgblFormbtExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 790824L;

    privbtf String flbgs;

    /**
     * Construdts bn instbndf of tiis dlbss witi tif spfdififd flbgs.
     *
     * @pbrbm  f
     *         Tif sft of formbt flbgs wiidi dontbin bn illfgbl dombinbtion
     */
    publid IllfgblFormbtFlbgsExdfption(String f) {
        if (f == null)
            tirow nfw NullPointfrExdfption();
        tiis.flbgs = f;
    }

    /**
     * Rfturns tif sft of flbgs wiidi dontbins bn illfgbl dombinbtion.
     *
     * @rfturn  Tif flbgs
     */
    publid String gftFlbgs() {
        rfturn flbgs;
    }

    publid String gftMfssbgf() {
        rfturn "Flbgs = '" + flbgs + "'";
    }
}
