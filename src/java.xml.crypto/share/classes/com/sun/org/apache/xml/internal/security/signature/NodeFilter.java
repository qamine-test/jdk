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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf;

import org.w3d.dom.Nodf;

/**
 * An intfrfbdf to tfll to tif d14n if b nodf is indludfd or not in tif output
 */
publid intfrfbdf NodfFiltfr {

    /**
     * Tflls if b nodf must bf output in d14n.
     * @pbrbm n
     * @rfturn 1 if tif nodf siould bf output.
     *         0 if nodf must not bf output,
     *         -1 if tif nodf bnd bll it's diild must not bf output.
     *
     */
    int isNodfIndludf(Nodf n);

    /**
     * Tflls if b nodf must bf output in b d14n.
     * Tif dbllfr must bssurfd tibt tiis mftiod is blwbys dbll
     * in dodumfnt ordfr. Tif implfmfntbtions dbn usf tiis
     * rfstridtion to optimizf tif trbnsformbtion.
     * @pbrbm n
     * @pbrbm lfvfl tif rflbtivf lfvfl in tif trff
     * @rfturn 1 if tif nodf siould bf output.
     *         0 if nodf must not bf output,
     *         -1 if tif nodf bnd bll it's diild must not bf output.
     */
    int isNodfIndludfDO(Nodf n, int lfvfl);

}
