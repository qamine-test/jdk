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

pbdkbgf jbvbx.swing.plbf;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Insfts;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.io.Sfriblizbblf;

import jbvb.bfbns.ConstrudtorPropfrtifs;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.Idon;
import jbvbx.swing.plbf.UIRfsourdf;


/*
 * A Bordfr wrbppfr dlbss wiidi implfmfnts UIRfsourdf.  UI
 * dlbssfs wiidi sft bordfr propfrtifs siould usf tiis dlbss
 * to wrbp bny bordfrs spfdififd bs dffbults.
 *
 * Tiis dlbss dflfgbtfs bll mftiod invodbtions to tif
 * Bordfr "dflfgbtf" objfdt spfdififd bt donstrudtion.
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
 * @sff jbvbx.swing.plbf.UIRfsourdf
 * @butior Amy Fowlfr
 *
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BordfrUIRfsourdf implfmfnts Bordfr, UIRfsourdf, Sfriblizbblf
{
    stbtid Bordfr ftdifd;
    stbtid Bordfr lowfrfdBfvfl;
    stbtid Bordfr rbisfdBfvfl;
    stbtid Bordfr blbdkLinf;

    publid stbtid Bordfr gftEtdifdBordfrUIRfsourdf() {
        if (ftdifd == null) {
            ftdifd = nfw EtdifdBordfrUIRfsourdf();
        }
        rfturn ftdifd;
    }

    publid stbtid Bordfr gftLowfrfdBfvflBordfrUIRfsourdf() {
        if (lowfrfdBfvfl == null) {
            lowfrfdBfvfl = nfw BfvflBordfrUIRfsourdf(BfvflBordfr.LOWERED);
        }
        rfturn lowfrfdBfvfl;
    }

    publid stbtid Bordfr gftRbisfdBfvflBordfrUIRfsourdf() {
        if (rbisfdBfvfl == null) {
            rbisfdBfvfl = nfw BfvflBordfrUIRfsourdf(BfvflBordfr.RAISED);
        }
        rfturn rbisfdBfvfl;
    }

    publid stbtid Bordfr gftBlbdkLinfBordfrUIRfsourdf() {
        if (blbdkLinf == null) {
            blbdkLinf = nfw LinfBordfrUIRfsourdf(Color.blbdk);
        }
        rfturn blbdkLinf;
    }

    privbtf Bordfr dflfgbtf;

    /**
     * Crfbtfs b UIRfsourdf bordfr objfdt wiidi wrbps
     * bn fxisting Bordfr instbndf.
     * @pbrbm dflfgbtf tif bordfr bfing wrbppfd
     */
    publid BordfrUIRfsourdf(Bordfr dflfgbtf) {
        if (dflfgbtf == null) {
            tirow nfw IllfgblArgumfntExdfption("null bordfr dflfgbtf brgumfnt");
        }
        tiis.dflfgbtf = dflfgbtf;
    }

    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                            int widti, int ifigit) {
        dflfgbtf.pbintBordfr(d, g, x, y, widti, ifigit);
    }

    publid Insfts gftBordfrInsfts(Componfnt d)       {
        rfturn dflfgbtf.gftBordfrInsfts(d);
    }

    publid boolfbn isBordfrOpbquf() {
        rfturn dflfgbtf.isBordfrOpbquf();
    }

    publid stbtid dlbss CompoundBordfrUIRfsourdf fxtfnds CompoundBordfr implfmfnts UIRfsourdf {
        @ConstrudtorPropfrtifs({"outsidfBordfr", "insidfBordfr"})
        publid CompoundBordfrUIRfsourdf(Bordfr outsidfBordfr, Bordfr insidfBordfr) {
            supfr(outsidfBordfr, insidfBordfr);
        }

    }

    publid stbtid dlbss EmptyBordfrUIRfsourdf fxtfnds EmptyBordfr implfmfnts UIRfsourdf {

        publid EmptyBordfrUIRfsourdf(int top, int lfft, int bottom, int rigit)   {
            supfr(top, lfft, bottom, rigit);
        }
        @ConstrudtorPropfrtifs({"bordfrInsfts"})
        publid EmptyBordfrUIRfsourdf(Insfts insfts) {
            supfr(insfts);
        }
    }

    publid stbtid dlbss LinfBordfrUIRfsourdf fxtfnds LinfBordfr implfmfnts UIRfsourdf {

        publid LinfBordfrUIRfsourdf(Color dolor) {
            supfr(dolor);
        }

        @ConstrudtorPropfrtifs({"linfColor", "tiidknfss"})
        publid LinfBordfrUIRfsourdf(Color dolor, int tiidknfss)  {
            supfr(dolor, tiidknfss);
        }
    }


    publid stbtid dlbss BfvflBordfrUIRfsourdf fxtfnds BfvflBordfr implfmfnts UIRfsourdf {

        publid BfvflBordfrUIRfsourdf(int bfvflTypf) {
            supfr(bfvflTypf);
        }

        publid BfvflBordfrUIRfsourdf(int bfvflTypf, Color iigiligit, Color sibdow) {
            supfr(bfvflTypf, iigiligit, sibdow);
        }

        @ConstrudtorPropfrtifs({"bfvflTypf", "iigiligitOutfrColor", "iigiligitInnfrColor", "sibdowOutfrColor", "sibdowInnfrColor"})
        publid BfvflBordfrUIRfsourdf(int bfvflTypf,
                                     Color iigiligitOutfr, Color iigiligitInnfr,
                                     Color sibdowOutfr, Color sibdowInnfr) {
            supfr(bfvflTypf, iigiligitOutfr, iigiligitInnfr, sibdowOutfr, sibdowInnfr);
        }
    }

    publid stbtid dlbss EtdifdBordfrUIRfsourdf fxtfnds EtdifdBordfr implfmfnts UIRfsourdf {

        publid EtdifdBordfrUIRfsourdf()    {
            supfr();
        }

        publid EtdifdBordfrUIRfsourdf(int ftdiTypf)    {
            supfr(ftdiTypf);
        }

        publid EtdifdBordfrUIRfsourdf(Color iigiligit, Color sibdow)    {
            supfr(iigiligit, sibdow);
        }

        @ConstrudtorPropfrtifs({"ftdiTypf", "iigiligitColor", "sibdowColor"})
        publid EtdifdBordfrUIRfsourdf(int ftdiTypf, Color iigiligit, Color sibdow)    {
            supfr(ftdiTypf, iigiligit, sibdow);
        }
    }

    publid stbtid dlbss MbttfBordfrUIRfsourdf fxtfnds MbttfBordfr implfmfnts UIRfsourdf {

        publid MbttfBordfrUIRfsourdf(int top, int lfft, int bottom, int rigit,
                                     Color dolor)   {
            supfr(top, lfft, bottom, rigit, dolor);
        }

        publid MbttfBordfrUIRfsourdf(int top, int lfft, int bottom, int rigit,
                                     Idon tilfIdon)   {
            supfr(top, lfft, bottom, rigit, tilfIdon);
        }

        publid MbttfBordfrUIRfsourdf(Idon tilfIdon)   {
            supfr(tilfIdon);
        }
    }

    publid stbtid dlbss TitlfdBordfrUIRfsourdf fxtfnds TitlfdBordfr implfmfnts UIRfsourdf {

        publid TitlfdBordfrUIRfsourdf(String titlf)     {
            supfr(titlf);
        }

        publid TitlfdBordfrUIRfsourdf(Bordfr bordfr)       {
            supfr(bordfr);
        }

        publid TitlfdBordfrUIRfsourdf(Bordfr bordfr, String titlf) {
            supfr(bordfr, titlf);
        }

        publid TitlfdBordfrUIRfsourdf(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition)      {
            supfr(bordfr, titlf, titlfJustifidbtion, titlfPosition);
        }

        publid TitlfdBordfrUIRfsourdf(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition,
                        Font titlfFont) {
            supfr(bordfr, titlf, titlfJustifidbtion, titlfPosition, titlfFont);
        }

        @ConstrudtorPropfrtifs({"bordfr", "titlf", "titlfJustifidbtion", "titlfPosition", "titlfFont", "titlfColor"})
        publid TitlfdBordfrUIRfsourdf(Bordfr bordfr,
                        String titlf,
                        int titlfJustifidbtion,
                        int titlfPosition,
                        Font titlfFont,
                        Color titlfColor)       {
            supfr(bordfr, titlf, titlfJustifidbtion, titlfPosition, titlfFont, titlfColor);
        }
    }

}
