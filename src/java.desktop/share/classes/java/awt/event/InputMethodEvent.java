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

pbdkbgf jbvb.bwt.fvfnt;

import sun.bwt.AWTAddfssor;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Componfnt;
import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.font.TfxtHitInfo;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.CibrbdtfrItfrbtor;
import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Input mftiod fvfnts dontbin informbtion bbout tfxt tibt is bfing
 * domposfd using bn input mftiod. Wifnfvfr tif tfxt dibngfs, tif
 * input mftiod sfnds bn fvfnt. If tif tfxt domponfnt tibt's durrfntly
 * using tif input mftiod is bn bdtivf dlifnt, tif fvfnt is dispbtdifd
 * to tibt domponfnt. Otifrwisf, it is dispbtdifd to b sfpbrbtf
 * domposition window.
 *
 * <p>
 * Tif tfxt indludfd witi tif input mftiod fvfnt donsists of two pbrts:
 * dommittfd tfxt bnd domposfd tfxt. Eitifr pbrt mby bf fmpty. Tif two
 * pbrts togftifr rfplbdf bny undommittfd domposfd tfxt sfnt in prfvious fvfnts,
 * or tif durrfntly sflfdtfd dommittfd tfxt.
 * Committfd tfxt siould bf intfgrbtfd into tif tfxt domponfnt's pfrsistfnt
 * dbtb, it will not bf sfnt bgbin. Composfd tfxt mby bf sfnt rfpfbtfdly,
 * witi dibngfs to rfflfdt tif usfr's fditing opfrbtions. Committfd tfxt
 * blwbys prfdfdfs domposfd tfxt.
 *
 * @butior JbvbSoft Asib/Pbdifid
 * @sindf 1.2
 */
publid dlbss InputMftiodEvfnt fxtfnds AWTEvfnt {

    /**
     * Sfribl Vfrsion ID.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 4727190874778922661L;

    /**
     * Mbrks tif first intfgfr id for tif rbngf of input mftiod fvfnt ids.
     */
    @Nbtivf publid stbtid finbl int INPUT_METHOD_FIRST = 1100;

    /**
     * Tif fvfnt typf indidbting dibngfd input mftiod tfxt. Tiis fvfnt is
     * gfnfrbtfd by input mftiods wiilf prodfssing input.
     */
    @Nbtivf publid stbtid finbl int INPUT_METHOD_TEXT_CHANGED = INPUT_METHOD_FIRST;

    /**
     * Tif fvfnt typf indidbting b dibngfd insfrtion point in input mftiod tfxt.
     * Tiis fvfnt is
     * gfnfrbtfd by input mftiods wiilf prodfssing input if only tif dbrft dibngfd.
     */
    @Nbtivf publid stbtid finbl int CARET_POSITION_CHANGED = INPUT_METHOD_FIRST + 1;

    /**
     * Mbrks tif lbst intfgfr id for tif rbngf of input mftiod fvfnt ids.
     */
    @Nbtivf publid stbtid finbl int INPUT_METHOD_LAST = INPUT_METHOD_FIRST + 1;

    /**
     * Tif timf stbmp tibt indidbtfs wifn tif fvfnt wbs drfbtfd.
     *
     * @sfribl
     * @sff #gftWifn
     * @sindf 1.4
     */
    long wifn;

    // Tfxt objfdt
    privbtf trbnsifnt AttributfdCibrbdtfrItfrbtor tfxt;
    privbtf trbnsifnt int dommittfdCibrbdtfrCount;
    privbtf trbnsifnt TfxtHitInfo dbrft;
    privbtf trbnsifnt TfxtHitInfo visiblfPosition;

    /**
     * Construdts bn <dodf>InputMftiodEvfnt</dodf> witi tif spfdififd
     * sourdf domponfnt, typf, timf, tfxt, dbrft, bnd visiblfPosition.
     * <p>
     * Tif offsfts of dbrft bnd visiblfPosition brf rflbtivf to tif durrfnt
     * domposfd tfxt; tibt is, tif domposfd tfxt witiin <dodf>tfxt</dodf>
     * if tiis is bn <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt,
     * tif domposfd tfxt witiin tif <dodf>tfxt</dodf> of tif
     * prfdfding <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt otifrwisf.
     * <p>Notf tibt pbssing in bn invblid <dodf>id</dodf> rfsults in
     * unspfdififd bfibvior. Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf tif objfdt wifrf tif fvfnt originbtfd
     * @pbrbm id tif fvfnt typf
     * @pbrbm wifn b long intfgfr tibt spfdififs tif timf tif fvfnt oddurrfd
     * @pbrbm tfxt tif dombinfd dommittfd bnd domposfd tfxt,
     *      dommittfd tfxt first; must bf <dodf>null</dodf>
     *      wifn tif fvfnt typf is <dodf>CARET_POSITION_CHANGED</dodf>;
     *      mby bf <dodf>null</dodf> for
     *      <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> if tifrf's no
     *      dommittfd or domposfd tfxt
     * @pbrbm dommittfdCibrbdtfrCount tif numbfr of dommittfd
     *      dibrbdtfrs in tif tfxt
     * @pbrbm dbrft tif dbrft (b.k.b. insfrtion point);
     *      <dodf>null</dodf> if tifrf's no dbrft witiin durrfnt
     *      domposfd tfxt
     * @pbrbm visiblfPosition tif position tibt's most importbnt
     *      to bf visiblf; <dodf>null</dodf> if tifrf's no
     *      rfdommfndbtion for b visiblf position witiin durrfnt
     *      domposfd tfxt
     * @tirows IllfgblArgumfntExdfption if <dodf>id</dodf> is not
     *      in tif rbngf
     *      <dodf>INPUT_METHOD_FIRST</dodf>..<dodf>INPUT_METHOD_LAST</dodf>;
     *      or if id is <dodf>CARET_POSITION_CHANGED</dodf> bnd
     *      <dodf>tfxt</dodf> is not <dodf>null</dodf>;
     *      or if <dodf>dommittfdCibrbdtfrCount</dodf> is not in tif rbngf
     *      <dodf>0</dodf>..<dodf>(tfxt.gftEndIndfx() - tfxt.gftBfginIndfx())</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     *
     * @sindf 1.4
     */
    publid InputMftiodEvfnt(Componfnt sourdf, int id, long wifn,
            AttributfdCibrbdtfrItfrbtor tfxt, int dommittfdCibrbdtfrCount,
            TfxtHitInfo dbrft, TfxtHitInfo visiblfPosition) {
        supfr(sourdf, id);
        if (id < INPUT_METHOD_FIRST || id > INPUT_METHOD_LAST) {
            tirow nfw IllfgblArgumfntExdfption("id outsidf of vblid rbngf");
        }

        if (id == CARET_POSITION_CHANGED && tfxt != null) {
            tirow nfw IllfgblArgumfntExdfption("tfxt must bf null for CARET_POSITION_CHANGED");
        }

        tiis.wifn = wifn;
        tiis.tfxt = tfxt;
        int tfxtLfngti = 0;
        if (tfxt != null) {
            tfxtLfngti = tfxt.gftEndIndfx() - tfxt.gftBfginIndfx();
        }

        if (dommittfdCibrbdtfrCount < 0 || dommittfdCibrbdtfrCount > tfxtLfngti) {
            tirow nfw IllfgblArgumfntExdfption("dommittfdCibrbdtfrCount outsidf of vblid rbngf");
        }
        tiis.dommittfdCibrbdtfrCount = dommittfdCibrbdtfrCount;

        tiis.dbrft = dbrft;
        tiis.visiblfPosition = visiblfPosition;
   }

    /**
     * Construdts bn <dodf>InputMftiodEvfnt</dodf> witi tif spfdififd
     * sourdf domponfnt, typf, tfxt, dbrft, bnd visiblfPosition.
     * <p>
     * Tif offsfts of dbrft bnd visiblfPosition brf rflbtivf to tif durrfnt
     * domposfd tfxt; tibt is, tif domposfd tfxt witiin <dodf>tfxt</dodf>
     * if tiis is bn <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt,
     * tif domposfd tfxt witiin tif <dodf>tfxt</dodf> of tif
     * prfdfding <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt otifrwisf.
     * Tif timf stbmp for tiis fvfnt is initiblizfd by invoking
     * {@link jbvb.bwt.EvfntQufuf#gftMostRfdfntEvfntTimf()}.
     * <p>Notf tibt pbssing in bn invblid <dodf>id</dodf> rfsults in
     * unspfdififd bfibvior. Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf tif objfdt wifrf tif fvfnt originbtfd
     * @pbrbm id tif fvfnt typf
     * @pbrbm tfxt tif dombinfd dommittfd bnd domposfd tfxt,
     *      dommittfd tfxt first; must bf <dodf>null</dodf>
     *      wifn tif fvfnt typf is <dodf>CARET_POSITION_CHANGED</dodf>;
     *      mby bf <dodf>null</dodf> for
     *      <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> if tifrf's no
     *      dommittfd or domposfd tfxt
     * @pbrbm dommittfdCibrbdtfrCount tif numbfr of dommittfd
     *      dibrbdtfrs in tif tfxt
     * @pbrbm dbrft tif dbrft (b.k.b. insfrtion point);
     *      <dodf>null</dodf> if tifrf's no dbrft witiin durrfnt
     *      domposfd tfxt
     * @pbrbm visiblfPosition tif position tibt's most importbnt
     *      to bf visiblf; <dodf>null</dodf> if tifrf's no
     *      rfdommfndbtion for b visiblf position witiin durrfnt
     *      domposfd tfxt
     * @tirows IllfgblArgumfntExdfption if <dodf>id</dodf> is not
     *      in tif rbngf
     *      <dodf>INPUT_METHOD_FIRST</dodf>..<dodf>INPUT_METHOD_LAST</dodf>;
     *      or if id is <dodf>CARET_POSITION_CHANGED</dodf> bnd
     *      <dodf>tfxt</dodf> is not <dodf>null</dodf>;
     *      or if <dodf>dommittfdCibrbdtfrCount</dodf> is not in tif rbngf
     *      <dodf>0</dodf>..<dodf>(tfxt.gftEndIndfx() - tfxt.gftBfginIndfx())</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     */
    publid InputMftiodEvfnt(Componfnt sourdf, int id,
            AttributfdCibrbdtfrItfrbtor tfxt, int dommittfdCibrbdtfrCount,
            TfxtHitInfo dbrft, TfxtHitInfo visiblfPosition) {
        tiis(sourdf, id,
                gftMostRfdfntEvfntTimfForSourdf(sourdf),
                tfxt, dommittfdCibrbdtfrCount,
                dbrft, visiblfPosition);
    }

    /**
     * Construdts bn <dodf>InputMftiodEvfnt</dodf> witi tif
     * spfdififd sourdf domponfnt, typf, dbrft, bnd visiblfPosition.
     * Tif tfxt is sft to <dodf>null</dodf>,
     * <dodf>dommittfdCibrbdtfrCount</dodf> to 0.
     * <p>
     * Tif offsfts of <dodf>dbrft</dodf> bnd <dodf>visiblfPosition</dodf>
     * brf rflbtivf to tif durrfnt domposfd tfxt; tibt is,
     * tif domposfd tfxt witiin tif <dodf>tfxt</dodf> of tif
     * prfdfding <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt if tif
     * fvfnt bfing donstrudtfd bs b <dodf>CARET_POSITION_CHANGED</dodf> fvfnt.
     * For bn <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt witiout tfxt,
     * <dodf>dbrft</dodf> bnd <dodf>visiblfPosition</dodf> must bf
     * <dodf>null</dodf>.
     * Tif timf stbmp for tiis fvfnt is initiblizfd by invoking
     * {@link jbvb.bwt.EvfntQufuf#gftMostRfdfntEvfntTimf()}.
     * <p>Notf tibt pbssing in bn invblid <dodf>id</dodf> rfsults in
     * unspfdififd bfibvior. Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf tif objfdt wifrf tif fvfnt originbtfd
     * @pbrbm id tif fvfnt typf
     * @pbrbm dbrft tif dbrft (b.k.b. insfrtion point);
     *      <dodf>null</dodf> if tifrf's no dbrft witiin durrfnt
     *      domposfd tfxt
     * @pbrbm visiblfPosition tif position tibt's most importbnt
     *      to bf visiblf; <dodf>null</dodf> if tifrf's no
     *      rfdommfndbtion for b visiblf position witiin durrfnt
     *      domposfd tfxt
     * @tirows IllfgblArgumfntExdfption if <dodf>id</dodf> is not
     *      in tif rbngf
     *      <dodf>INPUT_METHOD_FIRST</dodf>..<dodf>INPUT_METHOD_LAST</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     */
    publid InputMftiodEvfnt(Componfnt sourdf, int id, TfxtHitInfo dbrft,
            TfxtHitInfo visiblfPosition) {
        tiis(sourdf, id,
                gftMostRfdfntEvfntTimfForSourdf(sourdf),
                null, 0, dbrft, visiblfPosition);
    }

    /**
     * Gfts tif dombinfd dommittfd bnd domposfd tfxt.
     * Cibrbdtfrs from indfx 0 to indfx <dodf>gftCommittfdCibrbdtfrCount() - 1</dodf> brf dommittfd
     * tfxt, tif rfmbining dibrbdtfrs brf domposfd tfxt.
     *
     * @rfturn tif tfxt.
     * Alwbys null for CARET_POSITION_CHANGED;
     * mby bf null for INPUT_METHOD_TEXT_CHANGED if tifrf's no domposfd or dommittfd tfxt.
     */
    publid AttributfdCibrbdtfrItfrbtor gftTfxt() {
        rfturn tfxt;
    }

    /**
     * Gfts tif numbfr of dommittfd dibrbdtfrs in tif tfxt.
     * @rfturn tif numbfr of dommittfd dibrbdtfrs in tif tfxt
     */
    publid int gftCommittfdCibrbdtfrCount() {
        rfturn dommittfdCibrbdtfrCount;
    }

    /**
     * Gfts tif dbrft.
     * <p>
     * Tif offsft of tif dbrft is rflbtivf to tif durrfnt
     * domposfd tfxt; tibt is, tif domposfd tfxt witiin gftTfxt()
     * if tiis is bn <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt,
     * tif domposfd tfxt witiin gftTfxt() of tif
     * prfdfding <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt otifrwisf.
     *
     * @rfturn tif dbrft (b.k.b. insfrtion point).
     * Null if tifrf's no dbrft witiin durrfnt domposfd tfxt.
     */
    publid TfxtHitInfo gftCbrft() {
        rfturn dbrft;
    }

    /**
     * Gfts tif position tibt's most importbnt to bf visiblf.
     * <p>
     * Tif offsft of tif visiblf position is rflbtivf to tif durrfnt
     * domposfd tfxt; tibt is, tif domposfd tfxt witiin gftTfxt()
     * if tiis is bn <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt,
     * tif domposfd tfxt witiin gftTfxt() of tif
     * prfdfding <dodf>INPUT_METHOD_TEXT_CHANGED</dodf> fvfnt otifrwisf.
     *
     * @rfturn tif position tibt's most importbnt to bf visiblf.
     * Null if tifrf's no rfdommfndbtion for b visiblf position witiin durrfnt domposfd tfxt.
     */
    publid TfxtHitInfo gftVisiblfPosition() {
        rfturn visiblfPosition;
    }

    /**
     * Consumfs tiis fvfnt so tibt it will not bf prodfssfd
     * in tif dffbult mbnnfr by tif sourdf wiidi originbtfd it.
     */
    publid void donsumf() {
        donsumfd = truf;
    }

    /**
     * Rfturns wiftifr or not tiis fvfnt ibs bffn donsumfd.
     * @sff #donsumf
     */
    publid boolfbn isConsumfd() {
        rfturn donsumfd;
    }

    /**
     * Rfturns tif timf stbmp of wifn tiis fvfnt oddurrfd.
     *
     * @rfturn tiis fvfnt's timfstbmp
     * @sindf 1.4
     */
    publid long gftWifn() {
      rfturn wifn;
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     * It dontbins tif fvfnt ID in tfxt form, tif dibrbdtfrs of tif
     * dommittfd bnd domposfd tfxt
     * sfpbrbtfd by "+", tif numbfr of dommittfd dibrbdtfrs,
     * tif dbrft, bnd tif visiblf position.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf INPUT_METHOD_TEXT_CHANGED:
              typfStr = "INPUT_METHOD_TEXT_CHANGED";
              brfbk;
          dbsf CARET_POSITION_CHANGED:
              typfStr = "CARET_POSITION_CHANGED";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }

        String tfxtString;
        if (tfxt == null) {
            tfxtString = "no tfxt";
        } flsf {
            StringBuildfr tfxtBufffr = nfw StringBuildfr("\"");
            int dommittfdCibrbdtfrCount = tiis.dommittfdCibrbdtfrCount;
            dibr d = tfxt.first();
            wiilf (dommittfdCibrbdtfrCount-- > 0) {
                tfxtBufffr.bppfnd(d);
                d = tfxt.nfxt();
            }
            tfxtBufffr.bppfnd("\" + \"");
            wiilf (d != CibrbdtfrItfrbtor.DONE) {
                tfxtBufffr.bppfnd(d);
                d = tfxt.nfxt();
            }
            tfxtBufffr.bppfnd("\"");
            tfxtString = tfxtBufffr.toString();
        }

        String dountString = dommittfdCibrbdtfrCount + " dibrbdtfrs dommittfd";

        String dbrftString;
        if (dbrft == null) {
            dbrftString = "no dbrft";
        } flsf {
            dbrftString = "dbrft: " + dbrft.toString();
        }

        String visiblfPositionString;
        if (visiblfPosition == null) {
            visiblfPositionString = "no visiblf position";
        } flsf {
            visiblfPositionString = "visiblf position: " + visiblfPosition.toString();
        }

        rfturn typfStr + ", " + tfxtString + ", " + dountString + ", " + dbrftString + ", " + visiblfPositionString;
    }

    /**
     * Initiblizfs tif <dodf>wifn</dodf> fifld if it is not prfsfnt in tif
     * objfdt input strfbm. In tibt dbsf, tif fifld will bf initiblizfd by
     * invoking {@link jbvb.bwt.EvfntQufuf#gftMostRfdfntEvfntTimf()}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows ClbssNotFoundExdfption, IOExdfption {
        s.dffbultRfbdObjfdt();
        if (wifn == 0) {
            wifn = gftMostRfdfntEvfntTimfForSourdf(tiis.sourdf);
        }
    }

    /**
     * Gft tif most rfdfnt fvfnt timf in tif {@dodf EvfntQufuf} wiidi tif {@dodf sourdf}
     * bflongs to.
     *
     * @pbrbm sourdf tif sourdf of tif fvfnt
     * @fxdfption  IllfgblArgumfntExdfption  if sourdf is null.
     * @rfturn most rfdfnt fvfnt timf in tif {@dodf EvfntQufuf}
     */
    privbtf stbtid long gftMostRfdfntEvfntTimfForSourdf(Objfdt sourdf) {
        if (sourdf == null) {
            // tirow tif IllfgblArgumfntExdfption to donform to EvfntObjfdt spfd
            tirow nfw IllfgblArgumfntExdfption("null sourdf");
        }
        AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(sourdf);
        EvfntQufuf fvfntQufuf = SunToolkit.gftSystfmEvfntQufufImplPP(bppContfxt);
        rfturn AWTAddfssor.gftEvfntQufufAddfssor().gftMostRfdfntEvfntTimf(fvfntQufuf);
    }
}
