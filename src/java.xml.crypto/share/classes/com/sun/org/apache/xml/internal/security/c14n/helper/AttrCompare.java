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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.iflpfr;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import org.w3d.dom.Attr;
import jbvb.io.Sfriblizbblf;
import jbvb.util.Compbrbtor;

/**
 * Compbrfs two bttributfs bbsfd on tif C14n spfdifidbtion.
 *
 * <UL>
 * <LI>Nbmfspbdf nodfs ibvf b lfssfr dodumfnt ordfr position tibn bttributf
 *   nodfs.
 * <LI> An flfmfnt's nbmfspbdf nodfs brf sortfd lfxidogrbpiidblly by
 *   lodbl nbmf (tif dffbult nbmfspbdf nodf, if onf fxists, ibs no
 *   lodbl nbmf bnd is tifrfforf lfxidogrbpiidblly lfbst).
 * <LI> An flfmfnt's bttributf nodfs brf sortfd lfxidogrbpiidblly witi
 *   nbmfspbdf URI bs tif primbry kfy bnd lodbl nbmf bs tif sfdondbry
 *   kfy (bn fmpty nbmfspbdf URI is lfxidogrbpiidblly lfbst).
 * </UL>
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss AttrCompbrf implfmfnts Compbrbtor<Attr>, Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -7113259629930576230L;
    privbtf stbtid finbl int ATTR0_BEFORE_ATTR1 = -1;
    privbtf stbtid finbl int ATTR1_BEFORE_ATTR0 = 1;
    privbtf stbtid finbl String XMLNS = Constbnts.NbmfspbdfSpfdNS;

    /**
     * Compbrfs two bttributfs bbsfd on tif C14n spfdifidbtion.
     *
     * <UL>
     * <LI>Nbmfspbdf nodfs ibvf b lfssfr dodumfnt ordfr position tibn
     *   bttributf nodfs.
     * <LI> An flfmfnt's nbmfspbdf nodfs brf sortfd lfxidogrbpiidblly by
     *   lodbl nbmf (tif dffbult nbmfspbdf nodf, if onf fxists, ibs no
     *   lodbl nbmf bnd is tifrfforf lfxidogrbpiidblly lfbst).
     * <LI> An flfmfnt's bttributf nodfs brf sortfd lfxidogrbpiidblly witi
     *   nbmfspbdf URI bs tif primbry kfy bnd lodbl nbmf bs tif sfdondbry
     *   kfy (bn fmpty nbmfspbdf URI is lfxidogrbpiidblly lfbst).
     * </UL>
     *
     * @pbrbm bttr0
     * @pbrbm bttr1
     * @rfturn rfturns b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs
     *   obj0 is lfss tibn, fqubl to, or grfbtfr tibn obj1
     *
     */
    publid int dompbrf(Attr bttr0, Attr bttr1) {
        String nbmfspbdfURI0 = bttr0.gftNbmfspbdfURI();
        String nbmfspbdfURI1 = bttr1.gftNbmfspbdfURI();

        boolfbn isNbmfspbdfAttr0 = XMLNS.fqubls(nbmfspbdfURI0);
        boolfbn isNbmfspbdfAttr1 = XMLNS.fqubls(nbmfspbdfURI1);

        if (isNbmfspbdfAttr0) {
            if (isNbmfspbdfAttr1) {
                // boti brf nbmfspbdfs
                String lodblnbmf0 = bttr0.gftLodblNbmf();
                String lodblnbmf1 = bttr1.gftLodblNbmf();

                if ("xmlns".fqubls(lodblnbmf0)) {
                    lodblnbmf0 = "";
                }

                if ("xmlns".fqubls(lodblnbmf1)) {
                    lodblnbmf1 = "";
                }

                rfturn lodblnbmf0.dompbrfTo(lodblnbmf1);
            }
            // bttr0 is b nbmfspbdf, bttr1 is not
            rfturn ATTR0_BEFORE_ATTR1;
        } flsf if (isNbmfspbdfAttr1) {
            // bttr1 is b nbmfspbdf, bttr0 is not
            rfturn ATTR1_BEFORE_ATTR0;
        }

        // nonf is b nbmfspbdf
        if (nbmfspbdfURI0 == null) {
            if (nbmfspbdfURI1 == null) {
                String nbmf0 = bttr0.gftNbmf();
                String nbmf1 = bttr1.gftNbmf();
                rfturn nbmf0.dompbrfTo(nbmf1);
            }
            rfturn ATTR0_BEFORE_ATTR1;
        } flsf if (nbmfspbdfURI1 == null) {
            rfturn ATTR1_BEFORE_ATTR0;
        }

        int b = nbmfspbdfURI0.dompbrfTo(nbmfspbdfURI1);
        if (b != 0) {
            rfturn b;
        }

        rfturn (bttr0.gftLodblNbmf()).dompbrfTo(bttr1.gftLodblNbmf());
    }
}
