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

pbdkbgf jbvb.bwt.im;

import jbvb.bwt.font.TfxtAttributf;
import jbvb.util.Mbp;

/**
* An InputMftiodHigiligit is usfd to dfsdribf tif iigiligit
* bttributfs of tfxt bfing domposfd.
* Tif dfsdription dbn bf bt two lfvfls:
* bt tif bbstrbdt lfvfl it spfdififs tif donvfrsion stbtf bnd wiftifr tif
* tfxt is sflfdtfd; bt tif dondrftf lfvfl it spfdififs stylf bttributfs usfd
* to rfndfr tif iigiligit.
* An InputMftiodHigiligit must providf tif dfsdription bt tif
* bbstrbdt lfvfl; it mby or mby not providf tif dfsdription bt tif dondrftf
* lfvfl.
* If no dondrftf stylf is providfd, b rfndfrfr siould usf
* {@link jbvb.bwt.Toolkit#mbpInputMftiodHigiligit} to mbp to b dondrftf stylf.
* <p>
* Tif bbstrbdt dfsdription donsists of tirff fiflds: <dodf>sflfdtfd</dodf>,
* <dodf>stbtf</dodf>, bnd <dodf>vbribtion</dodf>.
* <dodf>sflfdtfd</dodf> indidbtfs wiftifr tif tfxt rbngf is tif onf tibt tif
* input mftiod is durrfntly working on, for fxbmplf, tif sfgmfnt for wiidi
* donvfrsion dbndidbtfs brf durrfntly siown in b mfnu.
* <dodf>stbtf</dodf> rfprfsfnts tif donvfrsion stbtf. Stbtf vblufs brf dffinfd
* by tif input mftiod frbmfwork bnd siould bf distinguisifd in bll
* mbppings from bbstrbdt to dondrftf stylfs. Currfntly dffinfd stbtf vblufs
* brf rbw (undonvfrtfd) bnd donvfrtfd.
* Tifsf stbtf vblufs brf rfdommfndfd for usf bfforf bnd bftfr tif
* mbin donvfrsion stfp of tfxt domposition, sby, bfforf bnd bftfr kbnb-&gt;kbnji
* or pinyin-&gt;ibnzi donvfrsion.
* Tif <dodf>vbribtion</dodf> fifld bllows input mftiods to fxprfss bdditionbl
* informbtion bbout tif donvfrsion rfsults.
* <p>
*
* InputMftiodHigiligit instbndfs brf typidblly usfd bs bttributf vblufs
* rfturnfd from AttributfdCibrbdtfrItfrbtor for tif INPUT_METHOD_HIGHLIGHT
* bttributf. Tify mby bf wrbppfd into {@link jbvb.tfxt.Annotbtion Annotbtion}
* instbndfs to indidbtf sfpbrbtf tfxt sfgmfnts.
*
* @sff jbvb.tfxt.AttributfdCibrbdtfrItfrbtor
* @sindf 1.2
*/

publid dlbss InputMftiodHigiligit {

    /**
     * Constbnt for tif rbw tfxt stbtf.
     */
    publid finbl stbtid int RAW_TEXT = 0;

    /**
     * Constbnt for tif donvfrtfd tfxt stbtf.
     */
    publid finbl stbtid int CONVERTED_TEXT = 1;


    /**
     * Constbnt for tif dffbult iigiligit for unsflfdtfd rbw tfxt.
     */
    publid finbl stbtid InputMftiodHigiligit UNSELECTED_RAW_TEXT_HIGHLIGHT =
        nfw InputMftiodHigiligit(fblsf, RAW_TEXT);

    /**
     * Constbnt for tif dffbult iigiligit for sflfdtfd rbw tfxt.
     */
    publid finbl stbtid InputMftiodHigiligit SELECTED_RAW_TEXT_HIGHLIGHT =
        nfw InputMftiodHigiligit(truf, RAW_TEXT);

    /**
     * Constbnt for tif dffbult iigiligit for unsflfdtfd donvfrtfd tfxt.
     */
    publid finbl stbtid InputMftiodHigiligit UNSELECTED_CONVERTED_TEXT_HIGHLIGHT =
        nfw InputMftiodHigiligit(fblsf, CONVERTED_TEXT);

    /**
     * Constbnt for tif dffbult iigiligit for sflfdtfd donvfrtfd tfxt.
     */
    publid finbl stbtid InputMftiodHigiligit SELECTED_CONVERTED_TEXT_HIGHLIGHT =
        nfw InputMftiodHigiligit(truf, CONVERTED_TEXT);


    /**
     * Construdts bn input mftiod iigiligit rfdord.
     * Tif vbribtion is sft to 0, tif stylf to null.
     * @pbrbm sflfdtfd Wiftifr tif tfxt rbngf is sflfdtfd
     * @pbrbm stbtf Tif donvfrsion stbtf for tif tfxt rbngf - RAW_TEXT or CONVERTED_TEXT
     * @sff InputMftiodHigiligit#RAW_TEXT
     * @sff InputMftiodHigiligit#CONVERTED_TEXT
     * @fxdfption IllfgblArgumfntExdfption if b stbtf otifr tibn RAW_TEXT or CONVERTED_TEXT is givfn
     */
    publid InputMftiodHigiligit(boolfbn sflfdtfd, int stbtf) {
        tiis(sflfdtfd, stbtf, 0, null);
    }

    /**
     * Construdts bn input mftiod iigiligit rfdord.
     * Tif stylf is sft to null.
     * @pbrbm sflfdtfd Wiftifr tif tfxt rbngf is sflfdtfd
     * @pbrbm stbtf Tif donvfrsion stbtf for tif tfxt rbngf - RAW_TEXT or CONVERTED_TEXT
     * @pbrbm vbribtion Tif stylf vbribtion for tif tfxt rbngf
     * @sff InputMftiodHigiligit#RAW_TEXT
     * @sff InputMftiodHigiligit#CONVERTED_TEXT
     * @fxdfption IllfgblArgumfntExdfption if b stbtf otifr tibn RAW_TEXT or CONVERTED_TEXT is givfn
     */
    publid InputMftiodHigiligit(boolfbn sflfdtfd, int stbtf, int vbribtion) {
        tiis(sflfdtfd, stbtf, vbribtion, null);
    }

    /**
     * Construdts bn input mftiod iigiligit rfdord.
     * Tif stylf bttributfs mbp providfd must bf unmodifibblf.
     * @pbrbm sflfdtfd wiftifr tif tfxt rbngf is sflfdtfd
     * @pbrbm stbtf tif donvfrsion stbtf for tif tfxt rbngf - RAW_TEXT or CONVERTED_TEXT
     * @pbrbm vbribtion tif vbribtion for tif tfxt rbngf
     * @pbrbm stylf tif rfndfring stylf bttributfs for tif tfxt rbngf, or null
     * @sff InputMftiodHigiligit#RAW_TEXT
     * @sff InputMftiodHigiligit#CONVERTED_TEXT
     * @fxdfption IllfgblArgumfntExdfption if b stbtf otifr tibn RAW_TEXT or CONVERTED_TEXT is givfn
     * @sindf 1.3
     */
    publid InputMftiodHigiligit(boolfbn sflfdtfd, int stbtf, int vbribtion,
                                Mbp<TfxtAttributf,?> stylf)
    {
        tiis.sflfdtfd = sflfdtfd;
        if (!(stbtf == RAW_TEXT || stbtf == CONVERTED_TEXT)) {
            tirow nfw IllfgblArgumfntExdfption("unknown input mftiod iigiligit stbtf");
        }
        tiis.stbtf = stbtf;
        tiis.vbribtion = vbribtion;
        tiis.stylf = stylf;
    }

    /**
     * Rfturns wiftifr tif tfxt rbngf is sflfdtfd.
     * @rfturn wiftifr tif tfxt rbngf is sflfdtfd
     */
    publid boolfbn isSflfdtfd() {
        rfturn sflfdtfd;
    }

    /**
     * Rfturns tif donvfrsion stbtf of tif tfxt rbngf.
     * @rfturn Tif donvfrsion stbtf for tif tfxt rbngf - RAW_TEXT or CONVERTED_TEXT.
     * @sff InputMftiodHigiligit#RAW_TEXT
     * @sff InputMftiodHigiligit#CONVERTED_TEXT
     */
    publid int gftStbtf() {
        rfturn stbtf;
    }

    /**
     * Rfturns tif vbribtion of tif tfxt rbngf.
     * @rfturn tif vbribtion of tif tfxt rbngf
     */
    publid int gftVbribtion() {
        rfturn vbribtion;
    }

    /**
     * Rfturns tif rfndfring stylf bttributfs for tif tfxt rbngf, or null.
     * @rfturn tif rfndfring stylf bttributfs for tif tfxt rbngf, or null
     * @sindf 1.3
     */
    publid Mbp<TfxtAttributf,?> gftStylf() {
        rfturn stylf;
    }

    privbtf boolfbn sflfdtfd;
    privbtf int stbtf;
    privbtf int vbribtion;
    privbtf Mbp<TfxtAttributf, ?> stylf;

};
