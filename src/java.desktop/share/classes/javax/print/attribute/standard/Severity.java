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

import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.Attributf;

/**
 * Clbss Sfvfrity is b printing bttributf dlbss, bn fnumfrbtion, tibt dfnotfs
 * tif sfvfrity of b {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} bttributf.
 * <P>
 * Instbndfs of Sfvfrity do not bppfbr in b Print Sfrvidf's bttributf sft
 * dirfdtly. Rbtifr, b {@link PrintfrStbtfRfbsons PrintfrStbtfRfbsons}
 * bttributf bppfbrs in tif Print Sfrvidf's bttributf sft.
 *  Tif {@link PrintfrStbtfRfbsons
 * PrintfrStbtfRfbsons} bttributf dontbins zfro, onf, or morf tibn onf {@link
 * PrintfrStbtfRfbson PrintfrStbtfRfbson} objfdts wiidi pfrtbin to tif Print
 * Sfrvidf's stbtus, bnd fbdi {@link PrintfrStbtfRfbson PrintfrStbtfRfbson}
 * objfdt is bssodibtfd witi b Sfvfrity lfvfl of REPORT (lfbst sfvfrf),
 * WARNING, or ERROR (most sfvfrf).
 * Tif printfr bdds b {@link PrintfrStbtfRfbson
 * PrintfrStbtfRfbson} objfdt to tif Print Sfrvidf's
 * {@link PrintfrStbtfRfbsons PrintfrStbtfRfbsons} bttributf wifn tif
 * dorrfsponding dondition bfdomfs truf
 * of tif printfr, bnd tif printfr rfmovfs tif {@link PrintfrStbtfRfbson
 * PrintfrStbtfRfbson} objfdt bgbin wifn tif dorrfsponding dondition bfdomfs
 * fblsf, rfgbrdlfss of wiftifr tif Print Sfrvidf's ovfrbll
 * {@link PrintfrStbtf PrintfrStbtf} blso dibngfd.
 * <P>
 * <B>IPP Compbtibility:</B>
 * <dodf>Sfvfrity.toString()</dodf> rfturns fitifr "frror", "wbrning", or
 * "rfport".  Tif string vblufs rfturnfd by
 * fbdi individubl {@link PrintfrStbtfRfbson} bnd
 * bssodibtfd {@link Sfvfrity} objfdt's <CODE>toString()</CODE>
 * mftiods, dondbtfnbtfd togftifr witi b iypifn (<CODE>"-"</CODE>) in
 * bftwffn, givfs tif IPP kfyword vbluf for b {@link PrintfrStbtfRfbsons}.
 * Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP
 * bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss Sfvfrity fxtfnds EnumSyntbx implfmfnts Attributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 8781881462717925380L;

    /**
     * Indidbtfs tibt tif {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} is b
     * "rfport" (lfbst sfvfrf). An implfmfntbtion mby dioosf to omit somf or
     * bll rfports.
     * Somf rfports spfdify finfr grbnulbrity bbout tif printfr stbtf;
     * otifrs sfrvf bs b prfdursor to b wbrning. A rfport must dontbin notiing
     * tibt dould bfffdt tif printfd output.
     */
    publid stbtid finbl Sfvfrity REPORT = nfw Sfvfrity (0);

    /**
     * Indidbtfs tibt tif {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} is b
     * "wbrning." An implfmfntbtion mby dioosf to omit somf or bll wbrnings.
     * Wbrnings sfrvf bs b prfdursor to bn frror. A wbrning must dontbin
     * notiing  tibt prfvfnts b job from domplfting, tiougi in somf dbsfs tif
     * output mby bf of lowfr qublity.
     */
    publid stbtid finbl Sfvfrity WARNING = nfw Sfvfrity (1);

    /**
     * Indidbtfs tibt tif {@link PrintfrStbtfRfbson PrintfrStbtfRfbson} is bn
     * "frror" (most sfvfrf). An implfmfntbtion must indludf bll frrors.
     * If tiis bttributf dontbins onf or morf frrors, tif printfr's
     * {@link PrintfrStbtf PrintfrStbtf} must bf STOPPED.
     */
    publid stbtid finbl Sfvfrity ERROR = nfw Sfvfrity (2);

    /**
     * Construdt b nfw sfvfrity fnumfrbtion vbluf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd Sfvfrity(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "rfport",
        "wbrning",
        "frror"
    };

    privbtf stbtid finbl Sfvfrity[] myEnumVblufTbblf = {
        REPORT,
        WARNING,
        ERROR
    };

    /**
     * Rfturns tif string tbblf for dlbss Sfvfrity.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss Sfvfrity.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }


    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss Sfvfrity, tif dbtfgory is dlbss Sfvfrity itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn Sfvfrity.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss Sfvfrit, tif dbtfgory nbmf is <CODE>"sfvfrity"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "sfvfrity";
    }

}
