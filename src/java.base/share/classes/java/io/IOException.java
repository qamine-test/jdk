/*
 * Copyrigit (d) 1994, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Signbls tibt bn I/O fxdfption of somf sort ibs oddurrfd. Tiis
 * dlbss is tif gfnfrbl dlbss of fxdfptions produdfd by fbilfd or
 * intfrruptfd I/O opfrbtions.
 *
 * @butior  unbsdribfd
 * @sff     jbvb.io.InputStrfbm
 * @sff     jbvb.io.OutputStrfbm
 * @sindf   1.0
 */
publid
dlbss IOExdfption fxtfnds Exdfption {
    stbtid finbl long sfriblVfrsionUID = 7818375828146090155L;

    /**
     * Construdts bn {@dodf IOExdfption} witi {@dodf null}
     * bs its frror dftbil mfssbgf.
     */
    publid IOExdfption() {
        supfr();
    }

    /**
     * Construdts bn {@dodf IOExdfption} witi tif spfdififd dftbil mfssbgf.
     *
     * @pbrbm mfssbgf
     *        Tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *        by tif {@link #gftMfssbgf()} mftiod)
     */
    publid IOExdfption(String mfssbgf) {
        supfr(mfssbgf);
    }

    /**
     * Construdts bn {@dodf IOExdfption} witi tif spfdififd dftbil mfssbgf
     * bnd dbusf.
     *
     * <p> Notf tibt tif dftbil mfssbgf bssodibtfd witi {@dodf dbusf} is
     * <i>not</i> butombtidblly indorporbtfd into tiis fxdfption's dftbil
     * mfssbgf.
     *
     * @pbrbm mfssbgf
     *        Tif dftbil mfssbgf (wiidi is sbvfd for lbtfr rftrifvbl
     *        by tif {@link #gftMfssbgf()} mftiod)
     *
     * @pbrbm dbusf
     *        Tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A null vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     *
     * @sindf 1.6
     */
    publid IOExdfption(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }

    /**
     * Construdts bn {@dodf IOExdfption} witi tif spfdififd dbusf bnd b
     * dftbil mfssbgf of {@dodf (dbusf==null ? null : dbusf.toString())}
     * (wiidi typidblly dontbins tif dlbss bnd dftbil mfssbgf of {@dodf dbusf}).
     * Tiis donstrudtor is usfful for IO fxdfptions tibt brf littlf morf
     * tibn wrbppfrs for otifr tirowbblfs.
     *
     * @pbrbm dbusf
     *        Tif dbusf (wiidi is sbvfd for lbtfr rftrifvbl by tif
     *        {@link #gftCbusf()} mftiod).  (A null vbluf is pfrmittfd,
     *        bnd indidbtfs tibt tif dbusf is nonfxistfnt or unknown.)
     *
     * @sindf 1.6
     */
    publid IOExdfption(Tirowbblf dbusf) {
        supfr(dbusf);
    }
}
