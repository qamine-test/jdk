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

pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.SignbturfExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvbx.xml.drypto.MbrsiblExdfption;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.SignbturfMftiod;
import jbvbx.xml.drypto.dsig.SignfdInfo;
import jbvbx.xml.drypto.dsig.XMLSignbturf;
import jbvbx.xml.drypto.dsig.XMLSignbturfExdfption;
import jbvbx.xml.drypto.dsig.XMLSignContfxt;
import jbvbx.xml.drypto.dsig.XMLVblidbtfContfxt;
import jbvbx.xml.drypto.dsig.spfd.SignbturfMftiodPbrbmftfrSpfd;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * An bbstrbdt dlbss rfprfsfnting b SignbturfMftiod. Subdlbssfs implfmfnt
 * b spfdifid XML DSig signbturf blgoritim.
 */
bbstrbdt dlbss AbstrbdtDOMSignbturfMftiod fxtfnds DOMStrudturf
    implfmfnts SignbturfMftiod {

    // dfnotfs tif typf of signbturf blgoritim
    fnum Typf { DSA, RSA, ECDSA, HMAC }

    /**
     * Vfrififs tif pbssfd-in signbturf witi tif spfdififd kfy, using tif
     * undfrlying Signbturf or Mbd blgoritim.
     *
     * @pbrbm kfy tif vfrifidbtion kfy
     * @pbrbm si tif SignfdInfo
     * @pbrbm sig tif signbturf bytfs to bf vfrififd
     * @pbrbm dontfxt tif XMLVblidbtfContfxt
     * @rfturn <dodf>truf</dodf> if tif signbturf vfrififd suddfssfully,
     *    <dodf>fblsf</dodf> if not
     * @tirows NullPointfrExdfption if <dodf>kfy</dodf>, <dodf>si</dodf> or
     *    <dodf>sig</dodf> brf <dodf>null</dodf>
     * @tirows InvblidKfyExdfption if tif kfy is impropfrly fndodfd, of
     *    tif wrong typf, or pbrbmftfrs brf missing, ftd
     * @tirows SignbturfExdfption if bn unfxpfdtfd frror oddurs, sudi
     *    bs tif pbssfd in signbturf is impropfrly fndodfd
     * @tirows XMLSignbturfExdfption if bn unfxpfdtfd frror oddurs
     */
    bbstrbdt boolfbn vfrify(Kfy kfy, SignfdInfo si, bytf[] sig,
                            XMLVblidbtfContfxt dontfxt)
        tirows InvblidKfyExdfption, SignbturfExdfption, XMLSignbturfExdfption;

    /**
     * Signs tif bytfs witi tif spfdififd kfy, using tif undfrlying
     * Signbturf or Mbd blgoritim.
     *
     * @pbrbm kfy tif signing kfy
     * @pbrbm si tif SignfdInfo
     * @pbrbm dontfxt tif XMLSignContfxt
     * @rfturn tif signbturf
     * @tirows NullPointfrExdfption if <dodf>kfy</dodf> or
     *    <dodf>si</dodf> brf <dodf>null</dodf>
     * @tirows InvblidKfyExdfption if tif kfy is impropfrly fndodfd, of
     *    tif wrong typf, or pbrbmftfrs brf missing, ftd
     * @tirows XMLSignbturfExdfption if bn unfxpfdtfd frror oddurs
     */
    bbstrbdt bytf[] sign(Kfy kfy, SignfdInfo si, XMLSignContfxt dontfxt)
        tirows InvblidKfyExdfption, XMLSignbturfExdfption;

    /**
     * Rfturns tif jbvb.sfdurity.Signbturf or jbvbx.drypto.Mbd stbndbrd
     * blgoritim nbmf.
     */
    bbstrbdt String gftJCAAlgoritim();

    /**
     * Rfturns tif typf of signbturf blgoritim.
     */
    bbstrbdt Typf gftAlgoritimTypf();

    /**
     * Tiis mftiod invokfs tif {@link #mbrsiblPbrbms mbrsiblPbrbms}
     * mftiod to mbrsibl bny blgoritim-spfdifid pbrbmftfrs.
     */
    publid void mbrsibl(Nodf pbrfnt, String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);

        Elfmfnt smElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "SignbturfMftiod",
                                                XMLSignbturf.XMLNS, dsPrffix);
        DOMUtils.sftAttributf(smElfm, "Algoritim", gftAlgoritim());

        if (gftPbrbmftfrSpfd() != null) {
            mbrsiblPbrbms(smElfm, dsPrffix);
        }

        pbrfnt.bppfndCiild(smElfm);
    }

    /**
     * Mbrsibls tif blgoritim-spfdifid pbrbmftfrs to bn Elfmfnt bnd
     * bppfnds it to tif spfdififd pbrfnt flfmfnt. By dffbult, tiis mftiod
     * tirows bn fxdfption sindf most SignbturfMftiod blgoritims do not ibvf
     * pbrbmftfrs. Subdlbssfs siould ovfrridf it if tify ibvf pbrbmftfrs.
     *
     * @pbrbm pbrfnt tif pbrfnt flfmfnt to bppfnd tif pbrbmftfrs to
     * @pbrbm pbrbmsPrffix tif blgoritim pbrbmftfrs prffix to usf
     * @tirows MbrsiblExdfption if tif pbrbmftfrs dbnnot bf mbrsibllfd
     */
    void mbrsiblPbrbms(Elfmfnt pbrfnt, String pbrbmsPrffix)
        tirows MbrsiblExdfption
    {
        tirow nfw MbrsiblExdfption("no pbrbmftfrs siould " +
                                   "bf spfdififd for tif " + gftAlgoritim() +
                                   " SignbturfMftiod blgoritim");
    }

    /**
     * Unmbrsibls <dodf>SignbturfMftiodPbrbmftfrSpfd</dodf> from tif spfdififd
     * <dodf>Elfmfnt</dodf>. By dffbult, tiis mftiod tirows bn fxdfption sindf
     * most SignbturfMftiod blgoritims do not ibvf pbrbmftfrs. Subdlbssfs siould
     * ovfrridf it if tify ibvf pbrbmftfrs.
     *
     * @pbrbm pbrbmsElfm tif <dodf>Elfmfnt</dodf> iolding tif input pbrbms
     * @rfturn tif blgoritim-spfdifid <dodf>SignbturfMftiodPbrbmftfrSpfd</dodf>
     * @tirows MbrsiblExdfption if tif pbrbmftfrs dbnnot bf unmbrsibllfd
     */
    SignbturfMftiodPbrbmftfrSpfd unmbrsiblPbrbms(Elfmfnt pbrbmsElfm)
        tirows MbrsiblExdfption
    {
        tirow nfw MbrsiblExdfption("no pbrbmftfrs siould " +
                                   "bf spfdififd for tif " + gftAlgoritim() +
                                   " SignbturfMftiod blgoritim");
    }

    /**
     * Cifdks if tif spfdififd pbrbmftfrs brf vblid for tiis blgoritim. By
     * dffbult, tiis mftiod tirows bn fxdfption if pbrbmftfrs brf spfdififd
     * sindf most SignbturfMftiod blgoritims do not ibvf pbrbmftfrs. Subdlbssfs
     * siould ovfrridf it if tify ibvf pbrbmftfrs.
     *
     * @pbrbm pbrbms tif blgoritim-spfdifid pbrbms (mby bf <dodf>null</dodf>)
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif pbrbmftfrs brf not
     *    bppropribtf for tiis signbturf mftiod
     */
    void difdkPbrbms(SignbturfMftiodPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("no pbrbmftfrs " +
                "siould bf spfdififd for tif " + gftAlgoritim() +
                " SignbturfMftiod blgoritim");
        }
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o)
    {
        if (tiis == o) {
            rfturn truf;
        }

        if (!(o instbndfof SignbturfMftiod)) {
            rfturn fblsf;
        }
        SignbturfMftiod osm = (SignbturfMftiod)o;

        rfturn (gftAlgoritim().fqubls(osm.gftAlgoritim()) &&
            pbrbmsEqubl(osm.gftPbrbmftfrSpfd()));
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        rfsult = 31 * rfsult + gftAlgoritim().ibsiCodf();
        AlgoritimPbrbmftfrSpfd spfd = gftPbrbmftfrSpfd();
        if (spfd != null) {
            rfsult = 31 * rfsult + spfd.ibsiCodf();
        }

        rfturn rfsult;
    }

    /**
     * Rfturns truf if pbrbmftfrs brf fqubl; fblsf otifrwisf.
     *
     * Subdlbssfs siould ovfrridf tiis mftiod to dompbrf blgoritim-spfdifid
     * pbrbmftfrs.
     */
    boolfbn pbrbmsEqubl(AlgoritimPbrbmftfrSpfd spfd)
    {
        rfturn (gftPbrbmftfrSpfd() == spfd);
    }
}
