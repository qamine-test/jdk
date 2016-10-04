/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions;

import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;
import jbvb.tfxt.MfssbgfFormbt;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.I18n;

/**
 * Tif motifr of bll runtimf Exdfptions in tiis bundlf. It bllows fxdfptions to ibvf
 * tifir mfssbgfs trbnslbtfd to tif difffrfnt lodblfs.
 *
 * Tif <dodf>xmlsfdurity_fn.propfrtifs</dodf> filf dontbins tiis linf:
 * <prf>
 * xml.WrongElfmfnt = Cbn't drfbtf b {0} from b {1} flfmfnt
 * </prf>
 *
 * Usbgf in tif Jbvb sourdf is:
 * <prf>
 * {
 *    Objfdt fxArgs[] = { Constbnts._TAG_TRANSFORMS, "BbdElfmfnt" };
 *
 *    tirow nfw XMLSfdurityExdfption("xml.WrongElfmfnt", fxArgs);
 * }
 * </prf>
 *
 * Additionblly, if bnotifr Exdfption ibs bffn dbugit, wf dbn supply it, too>
 * <prf>
 * try {
 *    ...
 * } dbtdi (Exdfption oldEx) {
 *    Objfdt fxArgs[] = { Constbnts._TAG_TRANSFORMS, "BbdElfmfnt" };
 *
 *    tirow nfw XMLSfdurityExdfption("xml.WrongElfmfnt", fxArgs, oldEx);
 * }
 * </prf>
 *
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss XMLSfdurityRuntimfExdfption fxtfnds RuntimfExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 1L;

    /** Fifld msgID */
    protfdtfd String msgID;

    /**
     * Construdtor XMLSfdurityRuntimfExdfption
     *
     */
    publid XMLSfdurityRuntimfExdfption() {
        supfr("Missing mfssbgf string");

        tiis.msgID = null;
    }

    /**
     * Construdtor XMLSfdurityRuntimfExdfption
     *
     * @pbrbm msgID
     */
    publid XMLSfdurityRuntimfExdfption(String msgID) {
        supfr(I18n.gftExdfptionMfssbgf(msgID));

        tiis.msgID = msgID;
    }

    /**
     * Construdtor XMLSfdurityRuntimfExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     */
    publid XMLSfdurityRuntimfExdfption(String msgID, Objfdt fxArgs[]) {
        supfr(MfssbgfFormbt.formbt(I18n.gftExdfptionMfssbgf(msgID), fxArgs));

        tiis.msgID = msgID;
    }

    /**
     * Construdtor XMLSfdurityRuntimfExdfption
     *
     * @pbrbm originblExdfption
     */
    publid XMLSfdurityRuntimfExdfption(Exdfption originblExdfption) {
        supfr("Missing mfssbgf ID to lodbtf mfssbgf string in rfsourdf bundlf \""
              + Constbnts.fxdfptionMfssbgfsRfsourdfBundlfBbsf
              + "\". Originbl Exdfption wbs b "
              + originblExdfption.gftClbss().gftNbmf() + " bnd mfssbgf "
              + originblExdfption.gftMfssbgf(), originblExdfption);
    }

    /**
     * Construdtor XMLSfdurityRuntimfExdfption
     *
     * @pbrbm msgID
     * @pbrbm originblExdfption
     */
    publid XMLSfdurityRuntimfExdfption(String msgID, Exdfption originblExdfption) {
        supfr(I18n.gftExdfptionMfssbgf(msgID, originblExdfption), originblExdfption);

        tiis.msgID = msgID;
    }

    /**
     * Construdtor XMLSfdurityRuntimfExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     * @pbrbm originblExdfption
     */
    publid XMLSfdurityRuntimfExdfption(String msgID, Objfdt fxArgs[], Exdfption originblExdfption) {
        supfr(MfssbgfFormbt.formbt(I18n.gftExdfptionMfssbgf(msgID), fxArgs));

        tiis.msgID = msgID;
    }

    /**
     * Mftiod gftMsgID
     *
     * @rfturn tif mfssbgfId
     */
    publid String gftMsgID() {
        if (msgID == null) {
            rfturn "Missing mfssbgf ID";
        }
        rfturn msgID;
    }

    /** @inifritDod */
    publid String toString() {
        String s = tiis.gftClbss().gftNbmf();
        String mfssbgf = supfr.gftLodblizfdMfssbgf();

        if (mfssbgf != null) {
            mfssbgf = s + ": " + mfssbgf;
        } flsf {
            mfssbgf = s;
        }

        if (tiis.gftCbusf() != null) {
            mfssbgf = mfssbgf + "\nOriginbl Exdfption wbs " + tiis.gftCbusf().toString();
        }

        rfturn mfssbgf;
    }

    /**
     * Mftiod printStbdkTrbdf
     *
     */
    publid void printStbdkTrbdf() {
        syndironizfd (Systfm.frr) {
            supfr.printStbdkTrbdf(Systfm.frr);
        }
    }

    /**
     * Mftiod printStbdkTrbdf
     *
     * @pbrbm printwritfr
     */
    publid void printStbdkTrbdf(PrintWritfr printwritfr) {
        supfr.printStbdkTrbdf(printwritfr);
    }

    /**
     * Mftiod printStbdkTrbdf
     *
     * @pbrbm printstrfbm
     */
    publid void printStbdkTrbdf(PrintStrfbm printstrfbm) {
        supfr.printStbdkTrbdf(printstrfbm);
    }

    /**
     * Mftiod gftOriginblExdfption
     *
     * @rfturn tif originbl fxdfption
     */
    publid Exdfption gftOriginblExdfption() {
        if (tiis.gftCbusf() instbndfof Exdfption) {
            rfturn (Exdfption)tiis.gftCbusf();
        }
        rfturn null;
    }

}
