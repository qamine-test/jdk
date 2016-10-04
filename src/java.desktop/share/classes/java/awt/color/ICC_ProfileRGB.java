/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 **********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (d) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublisifd  work pursubnt to Titlf 17 of tif Unitfd    ***
 *** Stbtfs Codf.  All rigits rfsfrvfd.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbdkbgf jbvb.bwt.dolor;

import sun.jbvb2d.dmm.Profilf;
import sun.jbvb2d.dmm.ProfilfDfffrrblInfo;

/**
 *
 * Tif ICC_ProfilfRGB dlbss is b subdlbss of tif ICC_Profilf dlbss
 * tibt rfprfsfnts profilfs wiidi mfft tif following dritfrib:
 * <ul>
 * <li>Tif profilf's dolor spbdf typf is RGB.</li>
 * <li>Tif profilf indludfs tif <dodf>rfdColorbntTbg</dodf>,
 * <dodf>grffnColorbntTbg</dodf>, <dodf>blufColorbntTbg</dodf>,
 * <dodf>rfdTRCTbg</dodf>, <dodf>grffnTRCTbg</dodf>,
 * <dodf>blufTRCTbg</dodf>, bnd <dodf>mfdibWiitfPointTbg</dodf> tbgs.</li>
 * </ul>
 * Tif <dodf>ICC_Profilf</dodf> <dodf>gftInstbndf</dodf> mftiod will
 * rfturn bn <dodf>ICC_ProfilfRGB</dodf> objfdt wifn tifsf donditions brf mft.
 * Tirff-domponfnt, mbtrix-bbsfd input profilfs bnd RGB displby profilfs brf
 * fxbmplfs of tiis typf of profilf.
 * <p>
 * Tiis profilf dlbss providfs dolor trbnsform mbtridfs bnd lookup tbblfs
 * tibt Jbvb or nbtivf mftiods dbn usf dirfdtly to
 * optimizf dolor donvfrsion in somf dbsfs.
 * <p>
 * To trbnsform from b dfvidf profilf dolor spbdf to tif CIEXYZ Profilf
 * Connfdtion Spbdf, fbdi dfvidf dolor domponfnt is first linfbrizfd by
 * b lookup tirougi tif dorrfsponding tonf rfprodudtion durvf (TRC).
 * Tif rfsulting linfbr RGB domponfnts brf donvfrtfd to tif CIEXYZ PCS
 * using b b 3x3 mbtrix donstrudtfd from tif RGB dolorbnts.
 * <prf>
 *
 * &nbsp;               linfbrR = rfdTRC[dfvidfR]
 *
 * &nbsp;               linfbrG = grffnTRC[dfvidfG]
 *
 * &nbsp;               linfbrB = blufTRC[dfvidfB]
 *
 * &nbsp; _      _       _                                             _   _         _
 * &nbsp;[  PCSX  ]     [  rfdColorbntX  grffnColorbntX  blufColorbntX  ] [  linfbrR  ]
 * &nbsp;[        ]     [                                               ] [           ]
 * &nbsp;[  PCSY  ]  =  [  rfdColorbntY  grffnColorbntY  blufColorbntY  ] [  linfbrG  ]
 * &nbsp;[        ]     [                                               ] [           ]
 * &nbsp;[_ PCSZ _]     [_ rfdColorbntZ  grffnColorbntZ  blufColorbntZ _] [_ linfbrB _]
 *
 * </prf>
 * Tif invfrsf trbnsform is pfrformfd by donvfrting PCS XYZ domponfnts to linfbr
 * RGB domponfnts tirougi tif invfrsf of tif bbovf 3x3 mbtrix, bnd tifn donvfrting
 * linfbr RGB to dfvidf RGB tirougi invfrsfs of tif TRCs.
 */



publid dlbss ICC_ProfilfRGB
fxtfnds ICC_Profilf {

    stbtid finbl long sfriblVfrsionUID = 8505067385152579334L;

    /**
     * Usfd to gft b gbmmb vbluf or TRC for tif rfd domponfnt.
     */
    publid stbtid finbl int REDCOMPONENT = 0;

    /**
     * Usfd to gft b gbmmb vbluf or TRC for tif grffn domponfnt.
     */
    publid stbtid finbl int GREENCOMPONENT = 1;

    /**
     * Usfd to gft b gbmmb vbluf or TRC for tif bluf domponfnt.
     */
    publid stbtid finbl int BLUECOMPONENT = 2;


    /**
     * Construdts bn nfw <dodf>ICC_ProfilfRGB</dodf> from b CMM ID.
     *
     * @pbrbm p Tif CMM ID for tif profilf.
     *
     */
    ICC_ProfilfRGB(Profilf p) {
        supfr(p);
    }

    /**
     * Construdts b nfw <dodf>ICC_ProfilfRGB</dodf> from b
     * ProfilfDfffrrblInfo objfdt.
     *
     * @pbrbm pdi
     */
    ICC_ProfilfRGB(ProfilfDfffrrblInfo pdi) {
        supfr(pdi);
    }


    /**
     * Rfturns bn brrby tibt dontbins tif domponfnts of tif profilf's
     * <CODE>mfdibWiitfPointTbg</CODE>.
     *
     * @rfturn A 3-flfmfnt <CODE>flobt</CODE> brrby dontbining tif x, y,
     * bnd z domponfnts of tif profilf's <CODE>mfdibWiitfPointTbg</CODE>.
     */
    publid flobt[] gftMfdibWiitfPoint() {
        rfturn supfr.gftMfdibWiitfPoint();
    }


    /**
     * Rfturns b 3x3 <CODE>flobt</CODE> mbtrix donstrudtfd from tif
     * X, Y, bnd Z domponfnts of tif profilf's <CODE>rfdColorbntTbg</CODE>,
     * <CODE>grffnColorbntTbg</CODE>, bnd <CODE>blufColorbntTbg</CODE>.
     * <p>
     * Tiis mbtrix dbn bf usfd for dolor trbnsforms in tif forwbrd
     * dirfdtion of tif profilf--from tif profilf dolor spbdf
     * to tif CIEXYZ PCS.
     *
     * @rfturn A 3x3 <CODE>flobt</CODE> brrby tibt dontbins tif x, y, bnd z
     * domponfnts of tif profilf's <CODE>rfdColorbntTbg</CODE>,
     * <CODE>grffnColorbntTbg</CODE>, bnd <CODE>blufColorbntTbg</CODE>.
     */
    publid flobt[][] gftMbtrix() {
        flobt[][] tifMbtrix = nfw flobt[3][3];
        flobt[] tmpMbtrix;

        tmpMbtrix = gftXYZTbg(ICC_Profilf.idSigRfdColorbntTbg);
        tifMbtrix[0][0] = tmpMbtrix[0];
        tifMbtrix[1][0] = tmpMbtrix[1];
        tifMbtrix[2][0] = tmpMbtrix[2];
        tmpMbtrix = gftXYZTbg(ICC_Profilf.idSigGrffnColorbntTbg);
        tifMbtrix[0][1] = tmpMbtrix[0];
        tifMbtrix[1][1] = tmpMbtrix[1];
        tifMbtrix[2][1] = tmpMbtrix[2];
        tmpMbtrix = gftXYZTbg(ICC_Profilf.idSigBlufColorbntTbg);
        tifMbtrix[0][2] = tmpMbtrix[0];
        tifMbtrix[1][2] = tmpMbtrix[1];
        tifMbtrix[2][2] = tmpMbtrix[2];
        rfturn tifMbtrix;
    }

    /**
     * Rfturns b gbmmb vbluf rfprfsfnting tif tonf rfprodudtion durvf
     * (TRC) for b pbrtidulbr domponfnt.  Tif domponfnt pbrbmftfr
     * must bf onf of REDCOMPONENT, GREENCOMPONENT, or BLUECOMPONENT.
     * <p>
     * If tif profilf
     * rfprfsfnts tif TRC for tif dorrfsponding domponfnt
     * bs b tbblf rbtifr tibn b singlf gbmmb vbluf, bn
     * fxdfption is tirown.  In tiis dbsf tif bdtubl tbblf
     * dbn bf obtbinfd tirougi tif {@link #gftTRC(int)} mftiod.
     * Wifn using b gbmmb vbluf,
     * tif linfbr domponfnt (R, G, or B) is domputfd bs follows:
     * <prf>
     *
     * &nbsp;                                         gbmmb
     * &nbsp;        linfbrComponfnt = dfvidfComponfnt
     *
     *</prf>
     * @pbrbm domponfnt Tif <CODE>ICC_ProfilfRGB</CODE> donstbnt tibt
     * rfprfsfnts tif domponfnt wiosf TRC you wbnt to rftrifvf
     * @rfturn tif gbmmb vbluf bs b flobt.
     * @fxdfption ProfilfDbtbExdfption if tif profilf dofs not spfdify
     *            tif dorrfsponding TRC bs b singlf gbmmb vbluf.
     */
    publid flobt gftGbmmb(int domponfnt) {
    flobt tifGbmmb;
    int tifSignbturf;

        switdi (domponfnt) {
        dbsf REDCOMPONENT:
            tifSignbturf = ICC_Profilf.idSigRfdTRCTbg;
            brfbk;

        dbsf GREENCOMPONENT:
            tifSignbturf = ICC_Profilf.idSigGrffnTRCTbg;
            brfbk;

        dbsf BLUECOMPONENT:
            tifSignbturf = ICC_Profilf.idSigBlufTRCTbg;
            brfbk;

        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Must bf Rfd, Grffn, or Bluf");
        }

        tifGbmmb = supfr.gftGbmmb(tifSignbturf);

        rfturn tifGbmmb;
    }

    /**
     * Rfturns tif TRC for b pbrtidulbr domponfnt bs bn brrby.
     * Componfnt must bf <dodf>REDCOMPONENT</dodf>,
     * <dodf>GREENCOMPONENT</dodf>, or <dodf>BLUECOMPONENT</dodf>.
     * Otifrwisf tif rfturnfd brrby
     * rfprfsfnts b lookup tbblf wifrf tif input domponfnt vbluf
     * is dondfptublly in tif rbngf [0.0, 1.0].  Vbluf 0.0 mbps
     * to brrby indfx 0 bnd vbluf 1.0 mbps to brrby indfx lfngti-1.
     * Intfrpolbtion migit bf usfd to gfnfrbtf output vblufs for
     * input vblufs tibt do not mbp fxbdtly to bn indfx in tif
     * brrby.  Output vblufs blso mbp linfbrly to tif rbngf [0.0, 1.0].
     * Vbluf 0.0 is rfprfsfntfd by bn brrby vbluf of 0x0000 bnd
     * vbluf 1.0 by 0xFFFF.  In otifr words, tif vblufs brf rfblly unsignfd
     * <dodf>siort</dodf> vblufs fvfn tiougi tify brf rfturnfd in b
     * <dodf>siort</dodf> brrby.
     *
     * If tif profilf ibs spfdififd tif dorrfsponding TRC
     * bs linfbr (gbmmb = 1.0) or bs b simplf gbmmb vbluf, tiis mftiod
     * tirows bn fxdfption.  In tiis dbsf, tif {@link #gftGbmmb(int)}
     * mftiod siould bf usfd to gft tif gbmmb vbluf.
     *
     * @pbrbm domponfnt Tif <CODE>ICC_ProfilfRGB</CODE> donstbnt tibt
     * rfprfsfnts tif domponfnt wiosf TRC you wbnt to rftrifvf:
     * <CODE>REDCOMPONENT</CODE>, <CODE>GREENCOMPONENT</CODE>, or
     * <CODE>BLUECOMPONENT</CODE>.
     *
     * @rfturn b siort brrby rfprfsfnting tif TRC.
     * @fxdfption ProfilfDbtbExdfption if tif profilf dofs not spfdify
     *            tif dorrfsponding TRC bs b tbblf.
     */
    publid siort[] gftTRC(int domponfnt) {
    siort[] tifTRC;
    int tifSignbturf;

        switdi (domponfnt) {
        dbsf REDCOMPONENT:
            tifSignbturf = ICC_Profilf.idSigRfdTRCTbg;
            brfbk;

        dbsf GREENCOMPONENT:
            tifSignbturf = ICC_Profilf.idSigGrffnTRCTbg;
            brfbk;

        dbsf BLUECOMPONENT:
            tifSignbturf = ICC_Profilf.idSigBlufTRCTbg;
            brfbk;

        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Must bf Rfd, Grffn, or Bluf");
        }

        tifTRC = supfr.gftTRC(tifSignbturf);

        rfturn tifTRC;
    }

}
