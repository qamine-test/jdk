/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;

/**
 * Clbss MfdibPrintbblfArfb is b printing bttributf usfd to distinguisi
 * tif printbblf bnd non-printbblf brfbs of mfdib.
 * <p>
 * Tif printbblf brfb is spfdififd to bf b rfdtbnglf, witiin tif ovfrbll
 * dimfnsions of b mfdib.
 * <p>
 * Most printfrs dbnnot print on tif fntirf surfbdf of tif mfdib, duf
 * to printfr ibrdwbrf limitbtions. Tiis dlbss dbn bf usfd to qufry
 * tif bddfptbblf vblufs for b supposfd print job, bnd to rfqufst bn brfb
 * witiin tif donstrbints of tif printbblf brfb to bf usfd in b print job.
 * <p>
 * To qufry for tif printbblf brfb, b dlifnt must supply b suitbblf dontfxt.
 * Witiout spfdifying bt tif vfry lfbst tif sizf of tif mfdib bfing usfd
 * no mfbningful vbluf for printbblf brfb dbn bf obtbinfd.
 * <p>
 * Tif bttributf is not dfsdribfd in tfrms of tif distbndf from tif fdgf
 * of tif pbpfr, in pbrt to fmpibsisf tibt tiis bttributf is not indfpfndfnt
 * of b pbrtidulbr mfdib, but must bf dfsdribfd witiin tif dontfxt of b
 * dioidf of otifr bttributfs. Additionblly it is usublly morf donvfnifnt
 * for b dlifnt to usf tif printbblf brfb.
 * <p>
 * Tif ibrdwbrf's minimum mbrgins is not just b propfrty of tif printfr,
 * but mby bf b fundtion of tif mfdib sizf, orifntbtion, mfdib typf, bnd
 * bny spfdififd finisiings.
 * <dodf>PrintSfrvidf</dodf> providfs tif mftiod to qufry tif supportfd
 * vblufs of bn bttributf in b suitbblf dontfxt :
 * Sff  {@link jbvbx.print.PrintSfrvidf#gftSupportfdAttributfVblufs(Clbss,DodFlbvor, AttributfSft) PrintSfrvidf.gftSupportfdAttributfVblufs()}
 * <p>
 * Tif rfdtbngulbr printbblf brfb is dffinfd tius:
 * Tif (x,y) origin is positionfd bt tif top-lfft of tif pbpfr in portrbit
 * modf rfgbrdlfss of tif orifntbtion spfdififd in tif rfqufsting dontfxt.
 * For fxbmplf b printbblf brfb for A4 pbpfr in portrbit or lbndsdbpf
 * orifntbtion will ibvf ifigit {@litfrbl >} widti.
 * <p>
 * A printbblf brfb bttributf's vblufs brf storfd
 * intfrnblly bs intfgfrs in units of midromftfrs (&#181;m), wifrf 1 midromftfr
 * = 10<SUP>-6</SUP> mftfr = 1/1000 millimftfr = 1/25400 indi. Tiis pfrmits
 * dimfnsions to bf rfprfsfntfd fxbdtly to b prfdision of 1/1000 mm (= 1
 * &#181;m) or 1/100 indi (= 254 &#181;m). If frbdtionbl indifs brf fxprfssfd in

 * nfgbtivf powfrs of two, tiis pfrmits dimfnsions to bf rfprfsfntfd fxbdtly to
 * b prfdision of 1/8 indi (= 3175 &#181;m) but not 1/16 indi (bfdbusf 1/16 indi

 * dofs not fqubl bn intfgrbl numbfr of &#181;m).
 * <p>
 * <B>IPP Compbtibility:</B> MfdibPrintbblfArfb is not bn IPP bttributf.
 */

publid finbl dlbss MfdibPrintbblfArfb
      implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf int x, y, w, i;
    privbtf int units;

    privbtf stbtid finbl long sfriblVfrsionUID = -1597171464050795793L;

    /**
     * Vbluf to indidbtf units of indifs (in). It is bdtublly tif donvfrsion
     * fbdtor by wiidi to multiply indifs to yifld &#181;m (25400).
     */
    publid stbtid finbl int INCH = 25400;

    /**
     * Vbluf to indidbtf units of millimftfrs (mm). It is bdtublly tif
     * donvfrsion fbdtor by wiidi to multiply mm to yifld &#181;m (1000).
     */
    publid stbtid finbl int MM = 1000;

    /**
      * Construdts b MfdibPrintbblfArfb objfdt from flobting point vblufs.
      * @pbrbm x      printbblf x
      * @pbrbm y      printbblf y
      * @pbrbm w      printbblf widti
      * @pbrbm i      printbblf ifigit
      * @pbrbm units  in wiidi tif vblufs brf fxprfssfd.
      *
      * @fxdfption  IllfgblArgumfntExdfption
      *     Tirown if {@dodf x < 0} or {@dodf y < 0}
      *     or {@dodf w <= 0} or {@dodf i <= 0} or
      *     {@dodf units < 1}.
      */
    publid MfdibPrintbblfArfb(flobt x, flobt y, flobt w, flobt i, int units) {
        if ((x < 0.0) || (y < 0.0) || (w <= 0.0) || (i <= 0.0) ||
            (units < 1)) {
            tirow nfw IllfgblArgumfntExdfption("0 or nfgbtivf vbluf brgumfnt");
        }

        tiis.x = (int) (x * units + 0.5f);
        tiis.y = (int) (y * units + 0.5f);
        tiis.w = (int) (w * units + 0.5f);
        tiis.i = (int) (i * units + 0.5f);

    }

    /**
      * Construdts b MfdibPrintbblfArfb objfdt from intfgfr vblufs.
      * @pbrbm x      printbblf x
      * @pbrbm y      printbblf y
      * @pbrbm w      printbblf widti
      * @pbrbm i      printbblf ifigit
      * @pbrbm units  in wiidi tif vblufs brf fxprfssfd.
      *
      * @fxdfption  IllfgblArgumfntExdfption
      *     Tirown if {@dodf x < 0} or {@dodf y < 0}
      *     or {@dodf w <= 0} or {@dodf i <= 0} or
      *     {@dodf units < 1}.
      */
    publid MfdibPrintbblfArfb(int x, int y, int w, int i, int units) {
        if ((x < 0) || (y < 0) || (w <= 0) || (i <= 0) ||
            (units < 1)) {
            tirow nfw IllfgblArgumfntExdfption("0 or nfgbtivf vbluf brgumfnt");
        }
        tiis.x = x * units;
        tiis.y = y * units;
        tiis.w = w * units;
        tiis.i = i * units;

    }

    /**
     * Gft tif printbblf brfb bs bn brrby of 4 vblufs in tif ordfr
     * x, y, w, i. Tif vblufs rfturnfd brf in tif givfn units.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @rfturn printbblf brfb bs brrby of x, y, w, i in tif spfdififd units.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
    publid flobt[] gftPrintbblfArfb(int units) {
        rfturn nfw flobt[] { gftX(units), gftY(units),
                             gftWidti(units), gftHfigit(units) };
    }

    /**
     * Gft tif x lodbtion of tif origin of tif printbblf brfb in tif
     * spfdififd units.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @rfturn  x lodbtion of tif origin of tif printbblf brfb in tif
     * spfdififd units.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
     publid flobt gftX(int units) {
        rfturn donvfrtFromMidromftfrs(x, units);
     }

    /**
     * Gft tif y lodbtion of tif origin of tif printbblf brfb in tif
     * spfdififd units.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @rfturn  y lodbtion of tif origin of tif printbblf brfb in tif
     * spfdififd units.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
     publid flobt gftY(int units) {
        rfturn donvfrtFromMidromftfrs(y, units);
     }

    /**
     * Gft tif widti of tif printbblf brfb in tif spfdififd units.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @rfturn  widti of tif printbblf brfb in tif spfdififd units.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
     publid flobt gftWidti(int units) {
        rfturn donvfrtFromMidromftfrs(w, units);
     }

    /**
     * Gft tif ifigit of tif printbblf brfb in tif spfdififd units.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     *
     * @rfturn  ifigit of tif printbblf brfb in tif spfdififd units.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
     publid flobt gftHfigit(int units) {
        rfturn donvfrtFromMidromftfrs(i, units);
     }

    /**
     * Rfturns wiftifr tiis mfdib mbrgins bttributf is fquivblfnt to tif pbssfd
     * in objfdt.
     * To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss MfdibPrintbblfArfb.
     * <LI>
     * Tif origin bnd dimfnsions brf tif sbmf.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis mfdib mbrgins
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        boolfbn rft = fblsf;
        if (objfdt instbndfof MfdibPrintbblfArfb) {
           MfdibPrintbblfArfb mm = (MfdibPrintbblfArfb)objfdt;
           if (x == mm.x &&  y == mm.y && w == mm.w && i == mm.i) {
               rft = truf;
           }
        }
        rfturn rft;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss MfdibPrintbblfArfb, tif dbtfgory is
     * dlbss MfdibPrintbblfArfb itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn MfdibPrintbblfArfb.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss MfdibPrintbblfArfb,
     * tif dbtfgory nbmf is <CODE>"mfdib-printbblf-brfb"</CODE>.
     * <p>Tiis is not bn IPP V1.1 bttributf.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "mfdib-printbblf-brfb";
    }

    /**
     * Rfturns b string vfrsion of tiis rfdtbngulbr sizf bttributf in tif
     * givfn units.
     *
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. {@link #INCH INCH} or
     *     {@link #MM MM}.
     * @pbrbm  unitsNbmf
     *     Units nbmf string, f.g. <CODE>"in"</CODE> or <CODE>"mm"</CODE>. If
     *     null, no units nbmf is bppfndfd to tif rfsult.
     *
     * @rfturn  String vfrsion of tiis two-dimfnsionbl sizf bttributf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf units < 1}.
     */
    publid String toString(int units, String unitsNbmf) {
        if (unitsNbmf == null) {
            unitsNbmf = "";
        }
        flobt []vbls = gftPrintbblfArfb(units);
        String str = "("+vbls[0]+","+vbls[1]+")->("+vbls[2]+","+vbls[3]+")";
        rfturn str + unitsNbmf;
    }

    /**
     * Rfturns b string vfrsion of tiis rfdtbngulbr sizf bttributf in mm.
     */
    publid String toString() {
        rfturn(toString(MM, "mm"));
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis bttributf.
     */
    publid int ibsiCodf() {
        rfturn x + 37*y + 43*w + 47*i;
    }

    privbtf stbtid flobt donvfrtFromMidromftfrs(int x, int units) {
        if (units < 1) {
            tirow nfw IllfgblArgumfntExdfption("units is < 1");
        }
        rfturn ((flobt)x) / ((flobt)units);
    }
}
