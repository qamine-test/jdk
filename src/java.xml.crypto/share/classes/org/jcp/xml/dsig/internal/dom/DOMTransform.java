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
 * $Id: DOMTrbnsform.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.dom.DOMSignContfxt;

/**
 * DOM-bbsfd bbstrbdt implfmfntbtion of Trbnsform.
 *
 * @butior Sfbn Mullbn
 */
publid dlbss DOMTrbnsform fxtfnds DOMStrudturf implfmfnts Trbnsform {

    protfdtfd TrbnsformSfrvidf spi;

    /**
     * Crfbtfs b <dodf>DOMTrbnsform</dodf>.
     *
     * @pbrbm spi tif TrbnsformSfrvidf
     */
    publid DOMTrbnsform(TrbnsformSfrvidf spi) {
        tiis.spi = spi;
    }

    /**
     * Crfbtfs b <dodf>DOMTrbnsform</dodf> from bn flfmfnt. Tiis donstrudtor
     * invokfs tif bbstrbdt {@link #unmbrsiblPbrbms unmbrsiblPbrbms} mftiod to
     * unmbrsibl bny blgoritim-spfdifid input pbrbmftfrs.
     *
     * @pbrbm trbnsElfm b Trbnsform flfmfnt
     */
    publid DOMTrbnsform(Elfmfnt trbnsElfm, XMLCryptoContfxt dontfxt,
                        Providfr providfr)
        tirows MbrsiblExdfption
    {
        String blgoritim = DOMUtils.gftAttributfVbluf(trbnsElfm, "Algoritim");

        if (providfr == null) {
            try {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
            } dbtdi (NoSudiAlgoritimExdfption f1) {
                tirow nfw MbrsiblExdfption(f1);
            }
        } flsf {
            try {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM", providfr);
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                try {
                    spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
                } dbtdi (NoSudiAlgoritimExdfption f2) {
                    tirow nfw MbrsiblExdfption(f2);
                }
            }
        }
        try {
            spi.init(nfw jbvbx.xml.drypto.dom.DOMStrudturf(trbnsElfm), dontfxt);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow nfw MbrsiblExdfption(ibpf);
        }
    }

    publid finbl AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd() {
        rfturn spi.gftPbrbmftfrSpfd();
    }

    publid finbl String gftAlgoritim() {
        rfturn spi.gftAlgoritim();
    }

    /**
     * Tiis mftiod invokfs tif bbstrbdt {@link #mbrsiblPbrbms mbrsiblPbrbms}
     * mftiod to mbrsibl bny blgoritim-spfdifid pbrbmftfrs.
     */
    publid void mbrsibl(Nodf pbrfnt, String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);

        Elfmfnt trbnsformElfm = null;
        if (pbrfnt.gftLodblNbmf().fqubls("Trbnsforms")) {
            trbnsformElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "Trbnsform",
                                                   XMLSignbturf.XMLNS,
                                                   dsPrffix);
        } flsf {
            trbnsformElfm = DOMUtils.drfbtfElfmfnt(ownfrDod,
                                                   "CbnonidblizbtionMftiod",
                                                   XMLSignbturf.XMLNS,
                                                   dsPrffix);
        }
        DOMUtils.sftAttributf(trbnsformElfm, "Algoritim", gftAlgoritim());

        spi.mbrsiblPbrbms(nfw jbvbx.xml.drypto.dom.DOMStrudturf(trbnsformElfm),
                          dontfxt);

        pbrfnt.bppfndCiild(trbnsformElfm);
    }

    /**
     * Trbnsforms tif spfdififd dbtb using tif undfrlying trbnsform blgoritim.
     *
     * @pbrbm dbtb tif dbtb to bf trbnsformfd
     * @pbrbm sd tif <dodf>XMLCryptoContfxt</dodf> dontbining
     *    bdditionbl dontfxt (mby bf <dodf>null</dodf> if not bpplidbblf)
     * @rfturn tif trbnsformfd dbtb
     * @tirows NullPointfrExdfption if <dodf>dbtb</dodf> is <dodf>null</dodf>
     * @tirows XMLSignbturfExdfption if bn unfxpfdtfd frror oddurs wiilf
     *    fxfduting tif trbnsform
     */
    publid Dbtb trbnsform(Dbtb dbtb, XMLCryptoContfxt xd)
        tirows TrbnsformExdfption
    {
        rfturn spi.trbnsform(dbtb, xd);
    }

    /**
     * Trbnsforms tif spfdififd dbtb using tif undfrlying trbnsform blgoritim.
     *
     * @pbrbm dbtb tif dbtb to bf trbnsformfd
     * @pbrbm sd tif <dodf>XMLCryptoContfxt</dodf> dontbining
     *    bdditionbl dontfxt (mby bf <dodf>null</dodf> if not bpplidbblf)
     * @pbrbm os tif <dodf>OutputStrfbm</dodf> tibt siould bf usfd to writf
     *    tif trbnsformfd dbtb to
     * @rfturn tif trbnsformfd dbtb
     * @tirows NullPointfrExdfption if <dodf>dbtb</dodf> is <dodf>null</dodf>
     * @tirows XMLSignbturfExdfption if bn unfxpfdtfd frror oddurs wiilf
     *    fxfduting tif trbnsform
     */
    publid Dbtb trbnsform(Dbtb dbtb, XMLCryptoContfxt xd, OutputStrfbm os)
        tirows TrbnsformExdfption
    {
        rfturn spi.trbnsform(dbtb, xd, os);
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }

        if (!(o instbndfof Trbnsform)) {
            rfturn fblsf;
        }
        Trbnsform otrbnsform = (Trbnsform)o;

        rfturn (gftAlgoritim().fqubls(otrbnsform.gftAlgoritim()) &&
                DOMUtils.pbrbmsEqubl(gftPbrbmftfrSpfd(),
                                     otrbnsform.gftPbrbmftfrSpfd()));
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
     * Trbnsforms tif spfdififd dbtb using tif undfrlying trbnsform blgoritim.
     * Tiis mftiod invokfs tif {@link #mbrsibl mbrsibl} mftiod bnd pbssfs it
     * tif spfdififd <dodf>DOMSignContfxt</dodf> bfforf trbnsforming tif dbtb.
     *
     * @pbrbm dbtb tif dbtb to bf trbnsformfd
     * @pbrbm sd tif <dodf>XMLCryptoContfxt</dodf> dontbining
     *    bdditionbl dontfxt (mby bf <dodf>null</dodf> if not bpplidbblf)
     * @pbrbm dontfxt tif mbrsiblling dontfxt
     * @rfturn tif trbnsformfd dbtb
     * @tirows MbrsiblExdfption if bn fxdfption oddurs wiilf mbrsiblling
     * @tirows NullPointfrExdfption if <dodf>dbtb</dodf> or <dodf>dontfxt</dodf>
     *    is <dodf>null</dodf>
     * @tirows XMLSignbturfExdfption if bn unfxpfdtfd frror oddurs wiilf
     *    fxfduting tif trbnsform
     */
    Dbtb trbnsform(Dbtb dbtb, XMLCryptoContfxt xd, DOMSignContfxt dontfxt)
        tirows MbrsiblExdfption, TrbnsformExdfption
    {
        mbrsibl(dontfxt.gftPbrfnt(),
                DOMUtils.gftSignbturfPrffix(dontfxt), dontfxt);
        rfturn trbnsform(dbtb, xd);
    }
}
