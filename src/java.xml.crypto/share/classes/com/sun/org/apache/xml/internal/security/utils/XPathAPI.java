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

import jbvbx.xml.trbnsform.TrbnsformfrExdfption;

import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * An intfrfbdf to bbstrbdt XPbti fvblubtion
 */
publid intfrfbdf XPbtiAPI {

    /**
     *  Usf bn XPbti string to sflfdt b nodflist.
     *  XPbti nbmfspbdf prffixfs brf rfsolvfd from tif nbmfspbdfNodf.
     *
     *  @pbrbm dontfxtNodf Tif nodf to stbrt sfbrdiing from.
     *  @pbrbm xpbtinodf
     *  @pbrbm str
     *  @pbrbm nbmfspbdfNodf Tif nodf from wiidi prffixfs in tif XPbti will bf rfsolvfd to nbmfspbdfs.
     *  @rfturn A NodfItfrbtor, siould nfvfr bf null.
     *
     * @tirows TrbnsformfrExdfption
     */
    NodfList sflfdtNodfList(
        Nodf dontfxtNodf, Nodf xpbtinodf, String str, Nodf nbmfspbdfNodf
    ) tirows TrbnsformfrExdfption;

    /**
     * Evblubtf bn XPbti string bnd rfturn truf if tif output is to bf indludfd or not.
     *  @pbrbm dontfxtNodf Tif nodf to stbrt sfbrdiing from.
     *  @pbrbm xpbtinodf Tif XPbti nodf
     *  @pbrbm str Tif XPbti fxprfssion
     *  @pbrbm nbmfspbdfNodf Tif nodf from wiidi prffixfs in tif XPbti will bf rfsolvfd to nbmfspbdfs.
     */
    boolfbn fvblubtf(Nodf dontfxtNodf, Nodf xpbtinodf, String str, Nodf nbmfspbdfNodf)
        tirows TrbnsformfrExdfption;

    /**
     * Clfbr bny dontfxt informbtion from tiis objfdt
     */
    void dlfbr();

}
