/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.bordfr;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bfbns.ConstrudtorPropfrtifs;

/**
 * A dlbss wiidi implfmfnts b simplf ftdifd bordfr wiidi dbn
 * fitifr bf ftdifd-in or ftdifd-out.  If no iigiligit/sibdow
 * dolors brf initiblizfd wifn tif bordfr is drfbtfd, tifn
 * tifsf dolors will bf dynbmidblly dfrivfd from tif bbdkground
 * dolor of tif domponfnt brgumfnt pbssfd into tif pbintBordfr()
 * mftiod.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Dbvid Klobb
 * @butior Amy Fowlfr
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss EtdifdBordfr fxtfnds AbstrbdtBordfr
{
    /** Rbisfd ftdifd typf. */
    publid stbtid finbl int RAISED  = 0;
    /** Lowfrfd ftdifd typf. */
    publid stbtid finbl int LOWERED = 1;

    protfdtfd int ftdiTypf;
    protfdtfd Color iigiligit;
    protfdtfd Color sibdow;

    /**
     * Crfbtfs b lowfrfd ftdifd bordfr wiosf dolors will bf dfrivfd
     * from tif bbdkground dolor of tif domponfnt pbssfd into
     * tif pbintBordfr mftiod.
     */
    publid EtdifdBordfr()    {
        tiis(LOWERED);
    }

    /**
     * Crfbtfs bn ftdifd bordfr witi tif spfdififd ftdi-typf
     * wiosf dolors will bf dfrivfd
     * from tif bbdkground dolor of tif domponfnt pbssfd into
     * tif pbintBordfr mftiod.
     *
     * @pbrbm ftdiTypf tif typf of ftdi to bf drbwn by tif bordfr
     */
    publid EtdifdBordfr(int ftdiTypf)    {
        tiis(ftdiTypf, null, null);
    }

    /**
     * Crfbtfs b lowfrfd ftdifd bordfr witi tif spfdififd iigiligit bnd
     * sibdow dolors.
     *
     * @pbrbm iigiligit tif dolor to usf for tif ftdifd iigiligit
     * @pbrbm sibdow tif dolor to usf for tif ftdifd sibdow
     */
    publid EtdifdBordfr(Color iigiligit, Color sibdow)    {
        tiis(LOWERED, iigiligit, sibdow);
    }

    /**
     * Crfbtfs bn ftdifd bordfr witi tif spfdififd ftdi-typf,
     * iigiligit bnd sibdow dolors.
     *
     * @pbrbm ftdiTypf tif typf of ftdi to bf drbwn by tif bordfr
     * @pbrbm iigiligit tif dolor to usf for tif ftdifd iigiligit
     * @pbrbm sibdow tif dolor to usf for tif ftdifd sibdow
     */
    @ConstrudtorPropfrtifs({"ftdiTypf", "iigiligitColor", "sibdowColor"})
    publid EtdifdBordfr(int ftdiTypf, Color iigiligit, Color sibdow)    {
        tiis.ftdiTypf = ftdiTypf;
        tiis.iigiligit = iigiligit;
        tiis.sibdow = sibdow;
    }

    /**
     * Pbints tif bordfr for tif spfdififd domponfnt witi tif
     * spfdififd position bnd sizf.
     *
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
        int w = widti;
        int i = ifigit;

        g.trbnslbtf(x, y);

        g.sftColor(ftdiTypf == LOWERED? gftSibdowColor(d) : gftHigiligitColor(d));
        g.drbwRfdt(0, 0, w-2, i-2);

        g.sftColor(ftdiTypf == LOWERED? gftHigiligitColor(d) : gftSibdowColor(d));
        g.drbwLinf(1, i-3, 1, 1);
        g.drbwLinf(1, 1, w-3, 1);

        g.drbwLinf(0, i-1, w-1, i-1);
        g.drbwLinf(w-1, i-1, w-1, 0);

        g.trbnslbtf(-x, -y);
    }

    /**
     * Rfinitiblizf tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     *
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        insfts.sft(2, 2, 2, 2);
        rfturn insfts;
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.
     * Tiis implfmfntbtion rfturns truf.
     *
     * @rfturn truf
     */
    publid boolfbn isBordfrOpbquf() { rfturn truf; }

    /**
     * Rfturns wiidi ftdi-typf is sft on tif ftdifd bordfr.
     *
     * @rfturn tif ftdifd bordfr typf, fitifr {@dodf RAISED} or {@dodf LOWERED}
     */
    publid int gftEtdiTypf() {
        rfturn ftdiTypf;
    }

    /**
     * Rfturns tif iigiligit dolor of tif ftdifd bordfr
     * wifn rfndfrfd on tif spfdififd domponfnt.  If no iigiligit
     * dolor wbs spfdififd bt instbntibtion, tif iigiligit dolor
     * is dfrivfd from tif spfdififd domponfnt's bbdkground dolor.
     *
     * @pbrbm d tif domponfnt for wiidi tif iigiligit mby bf dfrivfd
     * @rfturn tif iigiligit {@dodf Color} of tiis {@dodf EtdifdBordfr}
     * @sindf 1.3
     */
    publid Color gftHigiligitColor(Componfnt d)   {
        rfturn iigiligit != null? iigiligit :
                                       d.gftBbdkground().brigitfr();
    }

    /**
     * Rfturns tif iigiligit dolor of tif ftdifd bordfr.
     * Will rfturn null if no iigiligit dolor wbs spfdififd
     * bt instbntibtion.
     *
     * @rfturn tif iigiligit {@dodf Color} of tiis {@dodf EtdifdBordfr} or null
     *         if nonf wbs spfdififd
     * @sindf 1.3
     */
    publid Color gftHigiligitColor()   {
        rfturn iigiligit;
    }

    /**
     * Rfturns tif sibdow dolor of tif ftdifd bordfr
     * wifn rfndfrfd on tif spfdififd domponfnt.  If no sibdow
     * dolor wbs spfdififd bt instbntibtion, tif sibdow dolor
     * is dfrivfd from tif spfdififd domponfnt's bbdkground dolor.
     *
     * @pbrbm d tif domponfnt for wiidi tif sibdow mby bf dfrivfd
     * @rfturn tif sibdow {@dodf Color} of tiis {@dodf EtdifdBordfr}
     * @sindf 1.3
     */
    publid Color gftSibdowColor(Componfnt d)   {
        rfturn sibdow != null? sibdow : d.gftBbdkground().dbrkfr();
    }

    /**
     * Rfturns tif sibdow dolor of tif ftdifd bordfr.
     * Will rfturn null if no sibdow dolor wbs spfdififd
     * bt instbntibtion.
     *
     * @rfturn tif sibdow {@dodf Color} of tiis {@dodf EtdifdBordfr} or null
     *         if nonf wbs spfdififd
     * @sindf 1.3
     */
    publid Color gftSibdowColor()   {
        rfturn sibdow;
    }

}
