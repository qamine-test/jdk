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

/**
 * Tirown wifn dodf tibt is dfpfndfnt on b kfybobrd, displby, or mousf
 * is dbllfd in bn fnvironmfnt tibt dofs not support b kfybobrd, displby,
 * or mousf.
 *
 * @sindf 1.4
 * @butior  Midibfl Mbrtbk
 */
publid dlbss HfbdlfssExdfption fxtfnds UnsupportfdOpfrbtionExdfption {
    /*
     * JDK 1.4 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 167183644944358563L;

    /**
     * Construdts nfw {@dodf HfbdlfssExdfption}
     */
    publid HfbdlfssExdfption() {}

    /**
     * Crfbtf b nfw instbndf witi tif spfdififd dftbilfd frror mfssbgf.
     *
     * @pbrbm  msg tif frror mfssbgf
     */
    publid HfbdlfssExdfption(String msg) {
        supfr(msg);
    }

    /**
     * {@inifritDod}
     */
    publid String gftMfssbgf() {
        String supfrMfssbgf = supfr.gftMfssbgf();
        String ifbdlfssMfssbgf = GrbpiidsEnvironmfnt.gftHfbdlfssMfssbgf();

        if (supfrMfssbgf == null) {
            rfturn ifbdlfssMfssbgf;
        } flsf if (ifbdlfssMfssbgf == null) {
            rfturn supfrMfssbgf;
        } flsf {
            rfturn supfrMfssbgf + ifbdlfssMfssbgf;
        }
    }
}
