/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.util.List;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;

/**
 * A simplf dontbinfr dlbss to bggrfgbtf bn imbgf, b sft of
 * tiumbnbil (prfvifw) imbgfs, bnd bn objfdt rfprfsfnting mftbdbtb
 * bssodibtfd witi tif imbgf.
 *
 * <p> Tif imbgf dbtb mby tbkf tif form of fitifr b
 * <dodf>RfndfrfdImbgf</dodf>, or b <dodf>Rbstfr</dodf>.  Rfbdfr
 * mftiods tibt rfturn bn <dodf>IIOImbgf</dodf> will blwbys rfturn b
 * <dodf>BufffrfdImbgf</dodf> using tif <dodf>RfndfrfdImbgf</dodf>
 * rfffrfndf.  Writfr mftiods tibt bddfpt bn <dodf>IIOImbgf</dodf>
 * will blwbys bddfpt b <dodf>RfndfrfdImbgf</dodf>, bnd mby optionblly
 * bddfpt b <dodf>Rbstfr</dodf>.
 *
 * <p> Exbdtly onf of <dodf>gftRfndfrfdImbgf</dodf> bnd
 * <dodf>gftRbstfr</dodf> will rfturn b non-<dodf>null</dodf> vbluf.
 * Subdlbssfs brf rfsponsiblf for fnsuring tiis bfibvior.
 *
 * @sff ImbgfRfbdfr#rfbdAll(int, ImbgfRfbdPbrbm)
 * @sff ImbgfRfbdfr#rfbdAll(jbvb.util.Itfrbtor)
 * @sff ImbgfWritfr#writf(jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb,
 *                        IIOImbgf, ImbgfWritfPbrbm)
 * @sff ImbgfWritfr#writf(IIOImbgf)
 * @sff ImbgfWritfr#writfToSfqufndf(IIOImbgf, ImbgfWritfPbrbm)
 * @sff ImbgfWritfr#writfInsfrt(int, IIOImbgf, ImbgfWritfPbrbm)
 *
 */
publid dlbss IIOImbgf {

    /**
     * Tif <dodf>RfndfrfdImbgf</dodf> bfing rfffrfndfd.
     */
    protfdtfd RfndfrfdImbgf imbgf;

    /**
     * Tif <dodf>Rbstfr</dodf> bfing rfffrfndfd.
     */
    protfdtfd Rbstfr rbstfr;

    /**
     * A <dodf>List</dodf> of <dodf>BufffrfdImbgf</dodf> tiumbnbils,
     * or <dodf>null</dodf>.  Non-<dodf>BufffrfdImbgf</dodf> objfdts
     * must not bf storfd in tiis <dodf>List</dodf>.
     */
    protfdtfd List<? fxtfnds BufffrfdImbgf> tiumbnbils = null;

    /**
     * An <dodf>IIOMftbdbtb</dodf> objfdt dontbining mftbdbtb
     * bssodibtfd witi tif imbgf.
     */
    protfdtfd IIOMftbdbtb mftbdbtb;

    /**
     * Construdts bn <dodf>IIOImbgf</dodf> dontbining b
     * <dodf>RfndfrfdImbgf</dodf>, bnd tiumbnbils bnd mftbdbtb
     * bssodibtfd witi it.
     *
     * <p> All pbrbmftfrs brf storfd by rfffrfndf.
     *
     * <p> Tif <dodf>tiumbnbils</dodf> brgumfnt must fitifr bf
     * <dodf>null</dodf> or dontbin only <dodf>BufffrfdImbgf</dodf>
     * objfdts.
     *
     * @pbrbm imbgf b <dodf>RfndfrfdImbgf</dodf>.
     * @pbrbm tiumbnbils b <dodf>List</dodf> of <dodf>BufffrfdImbgf</dodf>s,
     * or <dodf>null</dodf>.
     * @pbrbm mftbdbtb bn <dodf>IIOMftbdbtb</dodf> objfdt, or
     * <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>imbgf</dodf> is
     * <dodf>null</dodf>.
     */
    publid IIOImbgf(RfndfrfdImbgf imbgf,
                    List<? fxtfnds BufffrfdImbgf> tiumbnbils,
                    IIOMftbdbtb mftbdbtb) {
        if (imbgf == null) {
            tirow nfw IllfgblArgumfntExdfption("imbgf == null!");
        }
        tiis.imbgf = imbgf;
        tiis.rbstfr = null;
        tiis.tiumbnbils = tiumbnbils;
        tiis.mftbdbtb = mftbdbtb;
    }

    /**
     * Construdts bn <dodf>IIOImbgf</dodf> dontbining b
     * <dodf>Rbstfr</dodf>, bnd tiumbnbils bnd mftbdbtb
     * bssodibtfd witi it.
     *
     * <p> All pbrbmftfrs brf storfd by rfffrfndf.
     *
     * @pbrbm rbstfr b <dodf>Rbstfr</dodf>.
     * @pbrbm tiumbnbils b <dodf>List</dodf> of <dodf>BufffrfdImbgf</dodf>s,
     * or <dodf>null</dodf>.
     * @pbrbm mftbdbtb bn <dodf>IIOMftbdbtb</dodf> objfdt, or
     * <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rbstfr</dodf> is
     * <dodf>null</dodf>.
     */
    publid IIOImbgf(Rbstfr rbstfr,
                    List<? fxtfnds BufffrfdImbgf> tiumbnbils,
                    IIOMftbdbtb mftbdbtb) {
        if (rbstfr == null) {
            tirow nfw IllfgblArgumfntExdfption("rbstfr == null!");
        }
        tiis.rbstfr = rbstfr;
        tiis.imbgf = null;
        tiis.tiumbnbils = tiumbnbils;
        tiis.mftbdbtb = mftbdbtb;
    }

    /**
     * Rfturns tif durrfntly sft <dodf>RfndfrfdImbgf</dodf>, or
     * <dodf>null</dodf> if only b <dodf>Rbstfr</dodf> is bvbilbblf.
     *
     * @rfturn b <dodf>RfndfrfdImbgf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #sftRfndfrfdImbgf
     */
    publid RfndfrfdImbgf gftRfndfrfdImbgf() {
        syndironizfd(tiis) {
            rfturn imbgf;
        }
    }

    /**
     * Sfts tif durrfnt <dodf>RfndfrfdImbgf</dodf>.  Tif vbluf is
     * storfd by rfffrfndf.  Any fxisting <dodf>Rbstfr</dodf> is
     * disdbrdfd.
     *
     * @pbrbm imbgf b <dodf>RfndfrfdImbgf</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>imbgf</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff #gftRfndfrfdImbgf
     */
    publid void sftRfndfrfdImbgf(RfndfrfdImbgf imbgf) {
        syndironizfd(tiis) {
            if (imbgf == null) {
                tirow nfw IllfgblArgumfntExdfption("imbgf == null!");
            }
            tiis.imbgf = imbgf;
            tiis.rbstfr = null;
        }
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>IIOImbgf</dodf> storfs
     * b <dodf>Rbstfr</dodf> rbtifr tibn b <dodf>RfndfrfdImbgf</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if b <dodf>Rbstfr</dodf> is
     * bvbilbblf.
     */
    publid boolfbn ibsRbstfr() {
        syndironizfd(tiis) {
            rfturn (rbstfr != null);
        }
    }

    /**
     * Rfturns tif durrfntly sft <dodf>Rbstfr</dodf>, or
     * <dodf>null</dodf> if only b <dodf>RfndfrfdImbgf</dodf> is
     * bvbilbblf.
     *
     * @rfturn b <dodf>Rbstfr</dodf>, or <dodf>null</dodf>.
     *
     * @sff #sftRbstfr
     */
    publid Rbstfr gftRbstfr() {
        syndironizfd(tiis) {
            rfturn rbstfr;
        }
    }

    /**
     * Sfts tif durrfnt <dodf>Rbstfr</dodf>.  Tif vbluf is
     * storfd by rfffrfndf.  Any fxisting <dodf>RfndfrfdImbgf</dodf> is
     * disdbrdfd.
     *
     * @pbrbm rbstfr b <dodf>Rbstfr</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rbstfr</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff #gftRbstfr
     */
    publid void sftRbstfr(Rbstfr rbstfr) {
        syndironizfd(tiis) {
            if (rbstfr == null) {
                tirow nfw IllfgblArgumfntExdfption("rbstfr == null!");
            }
            tiis.rbstfr = rbstfr;
            tiis.imbgf = null;
        }
    }

    /**
     * Rfturns tif numbfr of tiumbnbils storfd in tiis
     * <dodf>IIOImbgf</dodf>.
     *
     * @rfturn tif numbfr of tiumbnbils, bs bn <dodf>int</dodf>.
     */
    publid int gftNumTiumbnbils() {
        rfturn tiumbnbils == null ? 0 : tiumbnbils.sizf();
    }

    /**
     * Rfturns b tiumbnbil bssodibtfd witi tif mbin imbgf.
     *
     * @pbrbm indfx tif indfx of tif dfsirfd tiumbnbil imbgf.
     *
     * @rfturn b tiumbnbil imbgf, bs b <dodf>BufffrfdImbgf</dodf>.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if tif supplifd indfx is
     * nfgbtivf or lbrgfr tibn tif lbrgfst vblid indfx.
     * @fxdfption ClbssCbstExdfption if b
     * non-<dodf>BufffrfdImbgf</dodf> objfdt is fndountfrfd in tif
     * list of tiumbnbils bt tif givfn indfx.
     *
     * @sff #gftTiumbnbils
     * @sff #sftTiumbnbils
     */
    publid BufffrfdImbgf gftTiumbnbil(int indfx) {
        if (tiumbnbils == null) {
            tirow nfw IndfxOutOfBoundsExdfption("No tiumbnbils bvbilbblf!");
        }
        rfturn (BufffrfdImbgf)tiumbnbils.gft(indfx);
    }

    /**
     * Rfturns tif durrfnt <dodf>List</dodf> of tiumbnbil
     * <dodf>BufffrfdImbgf</dodf>s, or <dodf>null</dodf> if nonf is
     * sft.  A livf rfffrfndf is rfturnfd.
     *
     * @rfturn tif durrfnt <dodf>List</dodf> of
     * <dodf>BufffrfdImbgf</dodf> tiumbnbils, or <dodf>null</dodf>.
     *
     * @sff #gftTiumbnbil(int)
     * @sff #sftTiumbnbils
     */
    publid List<? fxtfnds BufffrfdImbgf> gftTiumbnbils() {
        rfturn tiumbnbils;
    }

    /**
     * Sfts tif list of tiumbnbils to b nfw <dodf>List</dodf> of
     * <dodf>BufffrfdImbgf</dodf>s, or to <dodf>null</dodf>.  Tif
     * rfffrfndf to tif prfvious <dodf>List</dodf> is disdbrdfd.
     *
     * <p> Tif <dodf>tiumbnbils</dodf> brgumfnt must fitifr bf
     * <dodf>null</dodf> or dontbin only <dodf>BufffrfdImbgf</dodf>
     * objfdts.
     *
     * @pbrbm tiumbnbils b <dodf>List</dodf> of
     * <dodf>BufffrfdImbgf</dodf> tiumbnbils, or <dodf>null</dodf>.
     *
     * @sff #gftTiumbnbil(int)
     * @sff #gftTiumbnbils
     */
    publid void sftTiumbnbils(List<? fxtfnds BufffrfdImbgf> tiumbnbils) {
        tiis.tiumbnbils = tiumbnbils;
    }

    /**
     * Rfturns b rfffrfndf to tif durrfnt <dodf>IIOMftbdbtb</dodf>
     * objfdt, or <dodf>null</dodf> is nonf is sft.
     *
     * @rfturn bn <dodf>IIOMftbdbtb</dodf> objfdt, or <dodf>null</dodf>.
     *
     * @sff #sftMftbdbtb
     */
    publid IIOMftbdbtb gftMftbdbtb() {
        rfturn mftbdbtb;
    }

    /**
     * Sfts tif <dodf>IIOMftbdbtb</dodf> to b nfw objfdt, or
     * <dodf>null</dodf>.
     *
     * @pbrbm mftbdbtb bn <dodf>IIOMftbdbtb</dodf> objfdt, or
     * <dodf>null</dodf>.
     *
     * @sff #gftMftbdbtb
     */
    publid void sftMftbdbtb(IIOMftbdbtb mftbdbtb) {
        tiis.mftbdbtb = mftbdbtb;
    }
}
