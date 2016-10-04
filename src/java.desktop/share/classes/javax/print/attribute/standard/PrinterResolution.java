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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.RfsolutionSyntbx;
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss PrintfrRfsolution is b printing bttributf dlbss tibt spfdififs bn
 * fxbdt rfsolution supportfd by b printfr or to bf usfd for b print job.
 * Tiis bttributf bssumfs tibt printfrs ibvf b smbll sft of dfvidf rfsolutions
 * bt wiidi tify dbn opfrbtf rbtifr tibn b dontinuum.
 * <p>
 * PrintfrRfsolution is usfd in multiplf wbys:
 * <OL TYPE=1>
 * <LI>
 * Wifn b dlifnt sfbrdifs looking for b printfr tibt supports tif dlifnt's
 * dfsirfd rfsolution fxbdtly (no morf, no lfss), tif dlifnt spfdififs
 * bn instbndf of dlbss PrintfrRfsolution indidbting tif fxbdt rfsolution tif
 * dlifnt wbnts. Only printfrs supporting tibt fxbdt rfsolution will mbtdi tif
 * sfbrdi.
 *
 * <LI>
 * Wifn b dlifnt nffds to print b job using tif dlifnt's dfsirfd rfsolution
 * fxbdtly (no morf, no lfss), tif dlifnt spfdififs bn instbndf of dlbss
 * PrintfrRfsolution bs bn bttributf of tif Print Job. Tiis will fbil if tif
 * Print Job dofsn't support tibt fxbdt rfsolution, bnd Fidflity is sft to
 * truf.
 * </OL>
 * If b dlifnt wbnts to lodbtf b printfr supporting b rfsolution
 * grfbtfr tibn somf rfquirfd minimum, tifn it mby bf nfdfssbry to fxdludf
 * tiis bttributf from b lookup rfqufst bnd to dirfdtly qufry tif sft of
 * supportfd rfsolutions, bnd spfdify tif onf tibt most dlosfly mffts
 * tif dlifnt's rfquirfmfnts.
 * In somf dbsfs tiis mby bf morf simply bdiifvfd by spfdifying b
 * PrintQublity bttributf wiidi oftfn dontrols rfsolution.
 * <P>
 * <B>IPP Compbtibility:</B> Tif informbtion nffdfd to donstrudt bn IPP
 * <CODE>"printfr-rfsolution"</CODE> bttributf dbn bf obtbinfd by dblling
 * mftiods on tif PrintfrRfsolution objfdt. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid finbl dlbss PrintfrRfsolution    fxtfnds RfsolutionSyntbx
        implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 13090306561090558L;

    /**
     * Construdt b nfw printfr rfsolution bttributf from tif givfn itfms.
     *
     * @pbrbm  drossFffdRfsolution
     *     Cross fffd dirfdtion rfsolution.
     * @pbrbm  fffdRfsolution
     *     Fffd dirfdtion rfsolution.
     * @pbrbm  units
     *    Unit donvfrsion fbdtor, f.g. <dodf>RfsolutionSyntbx.DPI</CODE>
     * or <dodf>RfsolutionSyntbx.DPCM</CODE>.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if {@dodf drossFffdRfsolution < 1} or
     *     {@dodf fffdRfsolution < 1} or {@dodf units < 1}.
     */
    publid PrintfrRfsolution(int drossFffdRfsolution, int fffdRfsolution,
                             int units) {
        supfr (drossFffdRfsolution, fffdRfsolution, units);
    }

    /**
     * Rfturns wiftifr tiis printfr rfsolution bttributf is fquivblfnt to tif
     * pbssfd in objfdt. To bf fquivblfnt, bll of tif following donditions
     * must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss PrintfrRfsolution.
     * <LI>
     * Tiis bttributf's dross fffd dirfdtion rfsolution is fqubl to
     * <CODE>objfdt</CODE>'s dross fffd dirfdtion rfsolution.
     * <LI>
     * Tiis bttributf's fffd dirfdtion rfsolution is fqubl to
     * <CODE>objfdt</CODE>'s fffd dirfdtion rfsolution.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis printfr
     *          rfsolution bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls (objfdt) &&
                objfdt instbndfof PrintfrRfsolution);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrRfsolution, tif dbtfgory is dlbss PrintfrRfsolution itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrRfsolution.dlbss;
                }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrRfsolution, tif
     * dbtfgory nbmf is <CODE>"printfr-rfsolution"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-rfsolution";
    }

}
