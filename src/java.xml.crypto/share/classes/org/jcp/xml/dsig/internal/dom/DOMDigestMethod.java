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
 * $Id: DOMDigfstMftiod.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.spfd.DigfstMftiodPbrbmftfrSpfd;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * DOM-bbsfd bbstrbdt implfmfntbtion of DigfstMftiod.
 *
 * @butior Sfbn Mullbn
 */
publid bbstrbdt dlbss DOMDigfstMftiod fxtfnds DOMStrudturf
    implfmfnts DigfstMftiod {

    stbtid finbl String SHA384 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#sib384"; // sff RFC 4051
    privbtf DigfstMftiodPbrbmftfrSpfd pbrbms;

    /**
     * Crfbtfs b <dodf>DOMDigfstMftiod</dodf>.
     *
     * @pbrbm pbrbms tif blgoritim-spfdifid pbrbms (mby bf <dodf>null</dodf>)
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif pbrbmftfrs brf not
     *    bppropribtf for tiis digfst mftiod
     */
    DOMDigfstMftiod(AlgoritimPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (pbrbms != null && !(pbrbms instbndfof DigfstMftiodPbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("pbrbms must bf of typf DigfstMftiodPbrbmftfrSpfd");
        }
        difdkPbrbms((DigfstMftiodPbrbmftfrSpfd)pbrbms);
        tiis.pbrbms = (DigfstMftiodPbrbmftfrSpfd)pbrbms;
    }

    /**
     * Crfbtfs b <dodf>DOMDigfstMftiod</dodf> from bn flfmfnt. Tiis donstrudtor
     * invokfs tif bbstrbdt {@link #unmbrsiblPbrbms unmbrsiblPbrbms} mftiod to
     * unmbrsibl bny blgoritim-spfdifid input pbrbmftfrs.
     *
     * @pbrbm dmElfm b DigfstMftiod flfmfnt
     */
    DOMDigfstMftiod(Elfmfnt dmElfm) tirows MbrsiblExdfption {
        Elfmfnt pbrbmsElfm = DOMUtils.gftFirstCiildElfmfnt(dmElfm);
        if (pbrbmsElfm != null) {
            pbrbms = unmbrsiblPbrbms(pbrbmsElfm);
        }
        try {
            difdkPbrbms(pbrbms);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow nfw MbrsiblExdfption(ibpf);
        }
    }

    stbtid DigfstMftiod unmbrsibl(Elfmfnt dmElfm) tirows MbrsiblExdfption {
        String blg = DOMUtils.gftAttributfVbluf(dmElfm, "Algoritim");
        if (blg.fqubls(DigfstMftiod.SHA1)) {
            rfturn nfw SHA1(dmElfm);
        } flsf if (blg.fqubls(DigfstMftiod.SHA256)) {
            rfturn nfw SHA256(dmElfm);
        } flsf if (blg.fqubls(SHA384)) {
            rfturn nfw SHA384(dmElfm);
        } flsf if (blg.fqubls(DigfstMftiod.SHA512)) {
            rfturn nfw SHA512(dmElfm);
        } flsf {
            tirow nfw MbrsiblExdfption("unsupportfd DigfstMftiod blgoritim: " +
                                       blg);
        }
    }

    /**
     * Cifdks if tif spfdififd pbrbmftfrs brf vblid for tiis blgoritim. By
     * dffbult, tiis mftiod tirows bn fxdfption if pbrbmftfrs brf spfdififd
     * sindf most DigfstMftiod blgoritims do not ibvf pbrbmftfrs. Subdlbssfs
     * siould ovfrridf it if tify ibvf pbrbmftfrs.
     *
     * @pbrbm pbrbms tif blgoritim-spfdifid pbrbms (mby bf <dodf>null</dodf>)
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif pbrbmftfrs brf not
     *    bppropribtf for tiis digfst mftiod
     */
    void difdkPbrbms(DigfstMftiodPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("no pbrbmftfrs " +
                "siould bf spfdififd for tif " + gftMfssbgfDigfstAlgoritim() +
                " DigfstMftiod blgoritim");
        }
    }

    publid finbl AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd() {
        rfturn pbrbms;
    }

    /**
     * Unmbrsibls <dodf>DigfstMftiodPbrbmftfrSpfd</dodf> from tif spfdififd
     * <dodf>Elfmfnt</dodf>.  By dffbult, tiis mftiod tirows bn fxdfption sindf
     * most DigfstMftiod blgoritims do not ibvf pbrbmftfrs. Subdlbssfs siould
     * ovfrridf it if tify ibvf pbrbmftfrs.
     *
     * @pbrbm pbrbmsElfm tif <dodf>Elfmfnt</dodf> iolding tif input pbrbms
     * @rfturn tif blgoritim-spfdifid <dodf>DigfstMftiodPbrbmftfrSpfd</dodf>
     * @tirows MbrsiblExdfption if tif pbrbmftfrs dbnnot bf unmbrsibllfd
     */
    DigfstMftiodPbrbmftfrSpfd unmbrsiblPbrbms(Elfmfnt pbrbmsElfm)
        tirows MbrsiblExdfption
    {
        tirow nfw MbrsiblExdfption("no pbrbmftfrs siould " +
                                   "bf spfdififd for tif " +
                                   gftMfssbgfDigfstAlgoritim() +
                                   " DigfstMftiod blgoritim");
    }

    /**
     * Tiis mftiod invokfs tif bbstrbdt {@link #mbrsiblPbrbms mbrsiblPbrbms}
     * mftiod to mbrsibl bny blgoritim-spfdifid pbrbmftfrs.
     */
    publid void mbrsibl(Nodf pbrfnt, String prffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);

        Elfmfnt dmElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "DigfstMftiod",
                                                XMLSignbturf.XMLNS, prffix);
        DOMUtils.sftAttributf(dmElfm, "Algoritim", gftAlgoritim());

        if (pbrbms != null) {
            mbrsiblPbrbms(dmElfm, prffix);
        }

        pbrfnt.bppfndCiild(dmElfm);
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }

        if (!(o instbndfof DigfstMftiod)) {
            rfturn fblsf;
        }
        DigfstMftiod odm = (DigfstMftiod)o;

        boolfbn pbrbmsEqubl = (pbrbms == null ? odm.gftPbrbmftfrSpfd() == null :
            pbrbms.fqubls(odm.gftPbrbmftfrSpfd()));

        rfturn (gftAlgoritim().fqubls(odm.gftAlgoritim()) && pbrbmsEqubl);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        if (pbrbms != null) {
            rfsult = 31 * rfsult + pbrbms.ibsiCodf();
        }
        rfsult = 31 * rfsult + gftAlgoritim().ibsiCodf();

        rfturn rfsult;
    }

    /**
     * Mbrsibls tif blgoritim-spfdifid pbrbmftfrs to bn Elfmfnt bnd
     * bppfnds it to tif spfdififd pbrfnt flfmfnt. By dffbult, tiis mftiod
     * tirows bn fxdfption sindf most DigfstMftiod blgoritims do not ibvf
     * pbrbmftfrs. Subdlbssfs siould ovfrridf it if tify ibvf pbrbmftfrs.
     *
     * @pbrbm pbrfnt tif pbrfnt flfmfnt to bppfnd tif pbrbmftfrs to
     * @pbrbm tif nbmfspbdf prffix to usf
     * @tirows MbrsiblExdfption if tif pbrbmftfrs dbnnot bf mbrsibllfd
     */
    void mbrsiblPbrbms(Elfmfnt pbrfnt, String prffix)
        tirows MbrsiblExdfption
    {
        tirow nfw MbrsiblExdfption("no pbrbmftfrs siould " +
                                   "bf spfdififd for tif " +
                                   gftMfssbgfDigfstAlgoritim() +
                                   " DigfstMftiod blgoritim");
    }

    /**
     * Rfturns tif MfssbgfDigfst stbndbrd blgoritim nbmf.
     */
    bbstrbdt String gftMfssbgfDigfstAlgoritim();

    stbtid finbl dlbss SHA1 fxtfnds DOMDigfstMftiod {
        SHA1(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA1(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn DigfstMftiod.SHA1;
        }
        String gftMfssbgfDigfstAlgoritim() {
            rfturn "SHA-1";
        }
    }

    stbtid finbl dlbss SHA256 fxtfnds DOMDigfstMftiod {
        SHA256(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA256(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn DigfstMftiod.SHA256;
        }
        String gftMfssbgfDigfstAlgoritim() {
            rfturn "SHA-256";
        }
    }

    stbtid finbl dlbss SHA384 fxtfnds DOMDigfstMftiod {
        SHA384(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA384(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn SHA384;
        }
        String gftMfssbgfDigfstAlgoritim() {
            rfturn "SHA-384";
        }
    }

    stbtid finbl dlbss SHA512 fxtfnds DOMDigfstMftiod {
        SHA512(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA512(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn DigfstMftiod.SHA512;
        }
        String gftMfssbgfDigfstAlgoritim() {
            rfturn "SHA-512";
        }
    }
}
