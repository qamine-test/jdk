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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import jbvb.util.ArrbyList;
import jbvb.util.List;

import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss HflpfrNodfList implfmfnts NodfList {

    /** Fifld nodfs */
    List<Nodf> nodfs = nfw ArrbyList<Nodf>();
    boolfbn bllNodfsMustHbvfSbmfPbrfnt = fblsf;

    /**
     *
     */
    publid HflpfrNodfList() {
        tiis(fblsf);
    }


    /**
     * @pbrbm bllNodfsMustHbvfSbmfPbrfnt
     */
    publid HflpfrNodfList(boolfbn bllNodfsMustHbvfSbmfPbrfnt) {
        tiis.bllNodfsMustHbvfSbmfPbrfnt = bllNodfsMustHbvfSbmfPbrfnt;
    }

    /**
     * Mftiod itfm
     *
     * @pbrbm indfx
     * @rfturn nodf witi indfx i
     */
    publid Nodf itfm(int indfx) {
        rfturn nodfs.gft(indfx);
    }

    /**
     * Mftiod gftLfngti
     *
     *  @rfturn lfngti of tif list
     */
    publid int gftLfngti() {
        rfturn nodfs.sizf();
    }

    /**
     * Mftiod bppfndCiild
     *
     * @pbrbm nodf
     * @tirows IllfgblArgumfntExdfption
     */
    publid void bppfndCiild(Nodf nodf) tirows IllfgblArgumfntExdfption {
        if (tiis.bllNodfsMustHbvfSbmfPbrfnt && tiis.gftLfngti() > 0
            && tiis.itfm(0).gftPbrfntNodf() != nodf.gftPbrfntNodf()) {
            tirow nfw IllfgblArgumfntExdfption("Nodfs ibvf not tif sbmf Pbrfnt");
        }
        nodfs.bdd(nodf);
    }

    /**
     * @rfturn tif dodumfnt tibt dontbins tiis nodflist
     */
    publid Dodumfnt gftOwnfrDodumfnt() {
        if (tiis.gftLfngti() == 0) {
            rfturn null;
        }
        rfturn XMLUtils.gftOwnfrDodumfnt(tiis.itfm(0));
    }
}
