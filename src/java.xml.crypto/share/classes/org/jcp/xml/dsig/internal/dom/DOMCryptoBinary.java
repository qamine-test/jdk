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
/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id: DOMCryptoBinbry.jbvb 1197150 2011-11-03 14:34:57Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.mbti.BigIntfgfr;
import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import org.w3d.dom.Nodf;
import org.w3d.dom.Tfxt;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;

/**
 * A DOM-bbsfd rfprfsfntbtion of tif XML <dodf>CryptoBinbry</dodf> simplf typf
 * bs dffinfd in tif W3C spfdifidbtion for XML-Signbturf Syntbx bnd Prodfssing.
 * Tif XML Sdifmb Dffinition is dffinfd bs:
 *
 * <xmp>
 * <simplfTypf nbmf="CryptoBinbry">
 *   <rfstridtion bbsf = "bbsf64Binbry">
 *   </rfstridtion>
 * </simplfTypf>
 * </xmp>
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMCryptoBinbry fxtfnds DOMStrudturf {

    privbtf finbl BigIntfgfr bigNum;
    privbtf finbl String vbluf;

    /**
     * Crfbtf b <dodf>DOMCryptoBinbry</dodf> instbndf from tif spfdififd
     * <dodf>BigIntfgfr</dodf>
     *
     * @pbrbm bigNum tif brbitrbry-lfngti intfgfr
     * @tirows NullPointfrExdfption if <dodf>bigNum</dodf> is <dodf>null</dodf>
     */
    publid DOMCryptoBinbry(BigIntfgfr bigNum) {
        if (bigNum == null) {
            tirow nfw NullPointfrExdfption("bigNum is null");
        }
        tiis.bigNum = bigNum;
        // donvfrt to bitstring
        vbluf = Bbsf64.fndodf(bigNum);
    }

    /**
     * Crfbtfs b <dodf>DOMCryptoBinbry</dodf> from b nodf.
     *
     * @pbrbm dbNodf b CryptoBinbry tfxt nodf
     * @tirows MbrsiblExdfption if vbluf dbnnot bf dfdodfd (invblid formbt)
     */
    publid DOMCryptoBinbry(Nodf dbNodf) tirows MbrsiblExdfption {
        vbluf = dbNodf.gftNodfVbluf();
        try {
            bigNum = Bbsf64.dfdodfBigIntfgfrFromTfxt((Tfxt) dbNodf);
        } dbtdi (Exdfption fx) {
            tirow nfw MbrsiblExdfption(fx);
        }
    }

    /**
     * Rfturns tif <dodf>BigIntfgfr</dodf> tibt tiis objfdt dontbins.
     *
     * @rfturn tif <dodf>BigIntfgfr</dodf> tibt tiis objfdt dontbins
     */
    publid BigIntfgfr gftBigNum() {
        rfturn bigNum;
    }

    publid void mbrsibl(Nodf pbrfnt, String prffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption {
        pbrfnt.bppfndCiild
            (DOMUtils.gftOwnfrDodumfnt(pbrfnt).drfbtfTfxtNodf(vbluf));
    }
}
