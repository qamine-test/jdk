/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tirown to indidbtf tibt bn bssfrtion ibs fbilfd.
 *
 * <p>Tif sfvfn onf-brgumfnt publid donstrudtors providfd by tiis
 * dlbss fnsurf tibt tif bssfrtion frror rfturnfd by tif invodbtion:
 * <prf>
 *     nfw AssfrtionError(<i>fxprfssion</i>)
 * </prf>
 * ibs bs its dftbil mfssbgf tif <i>string donvfrsion</i> of
 * <i>fxprfssion</i> (bs dffinfd in sfdtion 15.18.1.1 of
 * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>),
 * rfgbrdlfss of tif typf of <i>fxprfssion</i>.
 *
 * @sindf   1.4
 */
publid dlbss AssfrtionError fxtfnds Error {
    privbtf stbtid finbl long sfriblVfrsionUID = -5013299493970297370L;

    /**
     * Construdts bn AssfrtionError witi no dftbil mfssbgf.
     */
    publid AssfrtionError() {
    }

    /**
     * Tiis intfrnbl donstrudtor dofs no prodfssing on its string brgumfnt,
     * fvfn if it is b null rfffrfndf.  Tif publid donstrudtors will
     * nfvfr dbll tiis donstrudtor witi b null brgumfnt.
     */
    privbtf AssfrtionError(String dftbilMfssbgf) {
        supfr(dftbilMfssbgf);
    }

    /**
     * Construdts bn AssfrtionError witi its dftbil mfssbgf dfrivfd
     * from tif spfdififd objfdt, wiidi is donvfrtfd to b string bs
     * dffinfd in sfdtion 15.18.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *<p>
     * If tif spfdififd objfdt is bn instbndf of {@dodf Tirowbblf}, it
     * bfdomfs tif <i>dbusf</i> of tif nfwly donstrudtfd bssfrtion frror.
     *
     * @pbrbm dftbilMfssbgf vbluf to bf usfd in donstrudting dftbil mfssbgf
     * @sff   Tirowbblf#gftCbusf()
     */
    publid AssfrtionError(Objfdt dftbilMfssbgf) {
        tiis(String.vblufOf(dftbilMfssbgf));
        if (dftbilMfssbgf instbndfof Tirowbblf)
            initCbusf((Tirowbblf) dftbilMfssbgf);
    }

    /**
     * Construdts bn AssfrtionError witi its dftbil mfssbgf dfrivfd
     * from tif spfdififd <dodf>boolfbn</dodf>, wiidi is donvfrtfd to
     * b string bs dffinfd in sfdtion 15.18.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm dftbilMfssbgf vbluf to bf usfd in donstrudting dftbil mfssbgf
     */
    publid AssfrtionError(boolfbn dftbilMfssbgf) {
        tiis(String.vblufOf(dftbilMfssbgf));
    }

    /**
     * Construdts bn AssfrtionError witi its dftbil mfssbgf dfrivfd
     * from tif spfdififd <dodf>dibr</dodf>, wiidi is donvfrtfd to b
     * string bs dffinfd in sfdtion 15.18.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm dftbilMfssbgf vbluf to bf usfd in donstrudting dftbil mfssbgf
     */
    publid AssfrtionError(dibr dftbilMfssbgf) {
        tiis(String.vblufOf(dftbilMfssbgf));
    }

    /**
     * Construdts bn AssfrtionError witi its dftbil mfssbgf dfrivfd
     * from tif spfdififd <dodf>int</dodf>, wiidi is donvfrtfd to b
     * string bs dffinfd in sfdtion 15.18.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm dftbilMfssbgf vbluf to bf usfd in donstrudting dftbil mfssbgf
     */
    publid AssfrtionError(int dftbilMfssbgf) {
        tiis(String.vblufOf(dftbilMfssbgf));
    }

    /**
     * Construdts bn AssfrtionError witi its dftbil mfssbgf dfrivfd
     * from tif spfdififd <dodf>long</dodf>, wiidi is donvfrtfd to b
     * string bs dffinfd in sfdtion 15.18.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm dftbilMfssbgf vbluf to bf usfd in donstrudting dftbil mfssbgf
     */
    publid AssfrtionError(long dftbilMfssbgf) {
        tiis(String.vblufOf(dftbilMfssbgf));
    }

    /**
     * Construdts bn AssfrtionError witi its dftbil mfssbgf dfrivfd
     * from tif spfdififd <dodf>flobt</dodf>, wiidi is donvfrtfd to b
     * string bs dffinfd in sfdtion 15.18.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm dftbilMfssbgf vbluf to bf usfd in donstrudting dftbil mfssbgf
     */
    publid AssfrtionError(flobt dftbilMfssbgf) {
        tiis(String.vblufOf(dftbilMfssbgf));
    }

    /**
     * Construdts bn AssfrtionError witi its dftbil mfssbgf dfrivfd
     * from tif spfdififd <dodf>doublf</dodf>, wiidi is donvfrtfd to b
     * string bs dffinfd in sfdtion 15.18.1.1 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm dftbilMfssbgf vbluf to bf usfd in donstrudting dftbil mfssbgf
     */
    publid AssfrtionError(doublf dftbilMfssbgf) {
        tiis(String.vblufOf(dftbilMfssbgf));
    }

    /**
     * Construdts b nfw {@dodf AssfrtionError} witi tif spfdififd
     * dftbil mfssbgf bnd dbusf.
     *
     * <p>Notf tibt tif dftbil mfssbgf bssodibtfd witi
     * {@dodf dbusf} is <i>not</i> butombtidblly indorporbtfd in
     * tiis frror's dftbil mfssbgf.
     *
     * @pbrbm  mfssbgf tif dftbil mfssbgf, mby bf {@dodf null}
     * @pbrbm  dbusf tif dbusf, mby bf {@dodf null}
     *
     * @sindf 1.7
     */
    publid AssfrtionError(String mfssbgf, Tirowbblf dbusf) {
        supfr(mfssbgf, dbusf);
    }
}
