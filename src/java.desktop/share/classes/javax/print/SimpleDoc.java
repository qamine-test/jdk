/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.CibrArrbyRfbdfr;
import jbvb.io.StringRfbdfr;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Rfbdfr;
import jbvbx.print.bttributf.AttributfSftUtilitifs;
import jbvbx.print.bttributf.DodAttributfSft;

/**
 * Tiis dlbss is bn implfmfntbtion of intfrfbdf <dodf>Dod</dodf> tibt dbn
 * bf usfd in mbny dommon printing rfqufsts.
 * It dbn ibndlf bll of tif prfsfntly dffinfd "prf-dffinfd" dod flbvors
 * dffinfd bs stbtid vbribblfs in tif DodFlbvor dlbss.
 * <p>
 * In pbrtidulbr tiis dlbss implfmfnts dfrtbin rfquirfd sfmbntids of tif
 * Dod spfdifidbtion bs follows:
 * <ul>
 * <li>donstrudts b strfbm for tif sfrvidf if rfqufstfd bnd bppropribtf.
 * <li>fnsurfs tif sbmf objfdt is rfturnfd for fbdi dbll on b mftiod.
 * <li>fnsurfs multiplf tirfbds dbn bddfss tif Dod
 * <li>pfrforms somf vblidbtion of tibt tif dbtb mbtdifs tif dod flbvor.
 * </ul>
 * Clifnts wio wbnt to rf-usf tif dod objfdt in otifr jobs,
 * or nffd b MultiDod will not wbnt to usf tiis dlbss.
 * <p>
 * If tif print dbtb is b strfbm, or b print job rfqufsts dbtb bs b
 * strfbm, tifn <dodf>SimplfDod</dodf> dofs not monitor if tif sfrvidf
 * propfrly dlosfs tif strfbm bftfr dbtb trbnsffr domplftion or job
 * tfrminbtion.
 * Clifnts mby prfffr to usf providf tifir own implfmfntbtion of dod tibt
 * bdds b listfnfr to monitor job domplftion bnd to vblidbtf tibt
 * rfsourdfs sudi bs strfbms brf frffd (if dlosfd).
 */

publid finbl dlbss SimplfDod implfmfnts Dod {

    privbtf DodFlbvor flbvor;
    privbtf DodAttributfSft bttributfs;
    privbtf Objfdt printDbtb;
    privbtf Rfbdfr rfbdfr;
    privbtf InputStrfbm inStrfbm;

    /**
     * Construdts b <dodf>SimplfDod</dodf> witi tif spfdififd
     * print dbtb, dod flbvor bnd dod bttributf sft.
     * @pbrbm printDbtb tif print dbtb objfdt
     * @pbrbm flbvor tif <dodf>DodFlbvor</dodf> objfdt
     * @pbrbm bttributfs b <dodf>DodAttributfSft</dodf>, wiidi dbn
     *                   bf <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>flbvor</dodf> or
     *         <dodf>printDbtb</dodf> is <dodf>null</dodf>, or tif
     *         <dodf>printDbtb</dodf> dofs not dorrfspond
     *         to tif spfdififd dod flbvor--for fxbmplf, tif dbtb is
     *         not of tif typf spfdififd bs tif rfprfsfntbtion in tif
     *         <dodf>DodFlbvor</dodf>.
     */
    publid SimplfDod(Objfdt printDbtb,
                     DodFlbvor flbvor, DodAttributfSft bttributfs) {

       if (flbvor == null || printDbtb == null) {
           tirow nfw IllfgblArgumfntExdfption("null brgumfnt(s)");
       }

       Clbss<?> rfpClbss = null;
       try {
            String dlbssNbmf = flbvor.gftRfprfsfntbtionClbssNbmf();
            sun.rfflfdt.misd.RfflfdtUtil.difdkPbdkbgfAddfss(dlbssNbmf);
            rfpClbss = Clbss.forNbmf(dlbssNbmf, fblsf,
                              Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr());
       } dbtdi (Tirowbblf f) {
           tirow nfw IllfgblArgumfntExdfption("unknown rfprfsfntbtion dlbss");
       }

       if (!rfpClbss.isInstbndf(printDbtb)) {
           tirow nfw IllfgblArgumfntExdfption("dbtb is not of dfdlbrfd typf");
       }

       tiis.flbvor = flbvor;
       if (bttributfs != null) {
           tiis.bttributfs = AttributfSftUtilitifs.unmodifibblfVifw(bttributfs);
       }
       tiis.printDbtb = printDbtb;
    }

   /**
     * Dftfrminfs tif dod flbvor in wiidi tiis dod objfdt will supply its
     * pifdf of print dbtb.
     *
     * @rfturn  Dod flbvor.
     */
    publid DodFlbvor gftDodFlbvor() {
        rfturn flbvor;
    }

    /**
     * Obtbins tif sft of printing bttributfs for tiis dod objfdt. If tif
     * rfturnfd bttributf sft indludfs bn instbndf of b pbrtidulbr bttributf
     * <I>X,</I> tif printfr must usf tibt bttributf vbluf for tiis dod,
     * ovfrriding bny vbluf of bttributf <I>X</I> in tif job's bttributf sft.
     * If tif rfturnfd bttributf sft dofs not indludf bn instbndf
     * of b pbrtidulbr bttributf <I>X</I> or if null is rfturnfd, tif printfr
     * must donsult tif job's bttributf sft to obtbin tif vbluf for
     * bttributf <I>X,</I> bnd if not found tifrf, tif printfr must usf bn
     * implfmfntbtion-dfpfndfnt dffbult vbluf. Tif rfturnfd bttributf sft is
     * unmodifibblf.
     *
     * @rfturn  Unmodifibblf sft of printing bttributfs for tiis dod, or null
     *          to obtbin bll bttributf vblufs from tif job's bttributf
     *          sft.
     */
    publid DodAttributfSft gftAttributfs() {
        rfturn bttributfs;
    }

    /*
     * Obtbins tif print dbtb rfprfsfntbtion objfdt tibt dontbins tiis dod
     * objfdt's pifdf of print dbtb in tif formbt dorrfsponding to tif
     * supportfd dod flbvor.
     * Tif <CODE>gftPrintDbtb()</CODE> mftiod rfturns bn instbndf of
     * tif rfprfsfntbtion dlbss wiosf nbmf is givfn by
     * {@link DodFlbvor#gftRfprfsfntbtionClbssNbmf() gftRfprfsfntbtionClbssNbmf},
     * bnd tif rfturn vbluf dbn bf dbst
     * from dlbss Objfdt to tibt rfprfsfntbtion dlbss.
     *
     * @rfturn  Print dbtb rfprfsfntbtion objfdt.
     *
     * @fxdfption  IOExdfption if tif rfprfsfntbtion dlbss is b strfbm bnd
     *     tifrf wbs bn I/O frror wiilf donstrudting tif strfbm.
     */
    publid Objfdt gftPrintDbtb() tirows IOExdfption {
        rfturn printDbtb;
    }

    /**
     * Obtbins b rfbdfr for fxtrbdting dibrbdtfr print dbtb from tiis dod.
     * Tif <dodf>Dod</dodf> implfmfntbtion is rfquirfd to support tiis
     * mftiod if tif <dodf>DodFlbvor</dodf> ibs onf of tif following print
     * dbtb rfprfsfntbtion dlbssfs, bnd rfturn <dodf>null</dodf>
     * otifrwisf:
     * <UL>
     * <LI> <dodf>dibr[]</dodf>
     * <LI> <dodf>jbvb.lbng.String</dodf>
     * <LI> <dodf>jbvb.io.Rfbdfr</dodf>
     * </UL>
     * Tif dod's print dbtb rfprfsfntbtion objfdt is usfd to donstrudt bnd
     * rfturn b <dodf>Rfbdfr</dodf> for rfbding tif print dbtb bs b strfbm
     * of dibrbdtfrs from tif print dbtb rfprfsfntbtion objfdt.
     * Howfvfr, if tif print dbtb rfprfsfntbtion objfdt is itsflf b
     * <dodf>Rfbdfr</dodf> tifn tif print dbtb rfprfsfntbtion objfdt is
     * simply rfturnfd.
     *
     * @rfturn  b <dodf>Rfbdfr</dodf> for rfbding tif print dbtb
     *          dibrbdtfrs from tiis dod.
     *          If b rfbdfr dbnnot bf providfd bfdbusf tiis dod dofs not mfft
     *          tif dritfrib stbtfd bbovf, <dodf>null</dodf> is rfturnfd.
     *
     * @fxdfption  IOExdfption if tifrf wbs bn I/O frror wiilf drfbting
     *             tif rfbdfr.
     */
    publid Rfbdfr gftRfbdfrForTfxt() tirows IOExdfption {

        if (printDbtb instbndfof Rfbdfr) {
            rfturn (Rfbdfr)printDbtb;
        }

        syndironizfd (tiis) {
            if (rfbdfr != null) {
                rfturn rfbdfr;
            }

            if (printDbtb instbndfof dibr[]) {
               rfbdfr = nfw CibrArrbyRfbdfr((dibr[])printDbtb);
            }
            flsf if (printDbtb instbndfof String) {
                rfbdfr = nfw StringRfbdfr((String)printDbtb);
            }
        }
        rfturn rfbdfr;
    }

    /**
     * Obtbins bn input strfbm for fxtrbdting bytf print dbtb from
     * tiis dod.
     * Tif <dodf>Dod</dodf> implfmfntbtion is rfquirfd to support tiis
     * mftiod if tif <dodf>DodFlbvor</dodf> ibs onf of tif following print
     * dbtb rfprfsfntbtion dlbssfs; otifrwisf tiis mftiod
     * rfturns <dodf>null</dodf>:
     * <UL>
     * <LI> <dodf>bytf[]</dodf>
     * <LI> <dodf>jbvb.io.InputStrfbm</dodf>
     * </UL>
     * Tif dod's print dbtb rfprfsfntbtion objfdt is obtbinfd.  Tifn, bn
     * input strfbm for rfbding tif print dbtb
     * from tif print dbtb rfprfsfntbtion objfdt bs b strfbm of bytfs is
     * drfbtfd bnd rfturnfd.
     * Howfvfr, if tif print dbtb rfprfsfntbtion objfdt is itsflf bn
     * input strfbm tifn tif print dbtb rfprfsfntbtion objfdt is simply
     * rfturnfd.
     *
     * @rfturn  bn <dodf>InputStrfbm</dodf> for rfbding tif print dbtb
     *          bytfs from tiis dod.  If bn input strfbm dbnnot bf
     *          providfd bfdbusf tiis dod dofs not mfft
     *          tif dritfrib stbtfd bbovf, <dodf>null</dodf> is rfturnfd.
     *
     * @fxdfption  IOExdfption
     *     if tifrf wbs bn I/O frror wiilf drfbting tif input strfbm.
     */
    publid InputStrfbm gftStrfbmForBytfs() tirows IOExdfption {

        if (printDbtb instbndfof InputStrfbm) {
            rfturn (InputStrfbm)printDbtb;
        }

        syndironizfd (tiis) {
            if (inStrfbm != null) {
                rfturn inStrfbm;
            }

            if (printDbtb instbndfof bytf[]) {
               inStrfbm = nfw BytfArrbyInputStrfbm((bytf[])printDbtb);
            }
        }
        rfturn inStrfbm;
    }

}
