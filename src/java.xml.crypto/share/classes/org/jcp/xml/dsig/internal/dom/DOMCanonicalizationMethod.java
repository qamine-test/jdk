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
 * $Id: DOMCbnonidblizbtionMftiod.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import org.w3d.dom.Elfmfnt;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;

/**
 * DOM-bbsfd bbstrbdt implfmfntbtion of CbnonidblizbtionMftiod.
 *
 * @butior Sfbn Mullbn
 */
publid dlbss DOMCbnonidblizbtionMftiod fxtfnds DOMTrbnsform
    implfmfnts CbnonidblizbtionMftiod {

    /**
     * Crfbtfs b <dodf>DOMCbnonidblizbtionMftiod</dodf>.
     *
     * @pbrbm spi TrbnsformSfrvidf
     */
    publid DOMCbnonidblizbtionMftiod(TrbnsformSfrvidf spi)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        supfr(spi);
        if (!(spi instbndfof ApbdifCbnonidblizfr) &&
                !isC14Nblg(spi.gftAlgoritim())) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "Illfgbl CbnonidblizbtionMftiod");
        }
    }

    /**
     * Crfbtfs b <dodf>DOMCbnonidblizbtionMftiod</dodf> from bn flfmfnt. Tiis
     * dtor invokfs tif bbstrbdt {@link #unmbrsiblPbrbms unmbrsiblPbrbms}
     * mftiod to unmbrsibl bny blgoritim-spfdifid input pbrbmftfrs.
     *
     * @pbrbm dmElfm b CbnonidblizbtionMftiod flfmfnt
     */
    publid DOMCbnonidblizbtionMftiod(Elfmfnt dmElfm, XMLCryptoContfxt dontfxt,
                                     Providfr providfr)
        tirows MbrsiblExdfption
    {
        supfr(dmElfm, dontfxt, providfr);
        if (!(spi instbndfof ApbdifCbnonidblizfr) &&
                !isC14Nblg(spi.gftAlgoritim())) {
            tirow nfw MbrsiblExdfption("Illfgbl CbnonidblizbtionMftiod");
        }
    }

    /**
     * Cbnonidblizfs tif spfdififd dbtb using tif undfrlying dbnonidblizbtion
     * blgoritim. Tiis is b donvfnifndf mftiod tibt is fquivblfnt to invoking
     * tif {@link #trbnsform trbnsform} mftiod.
     *
     * @pbrbm dbtb tif dbtb to bf dbnonidblizfd
     * @pbrbm xd tif <dodf>XMLCryptoContfxt</dodf> dontbining
     *     bdditionbl dontfxt (mby bf <dodf>null</dodf> if not bpplidbblf)
     * @rfturn tif dbnonidblizfd dbtb
     * @tirows NullPointfrExdfption if <dodf>dbtb</dodf> is <dodf>null</dodf>
     * @tirows TrbnsformExdfption if bn unfxpfdtfd frror oddurs wiilf
     *    dbnonidblizing tif dbtb
     */
    publid Dbtb dbnonidblizf(Dbtb dbtb, XMLCryptoContfxt xd)
        tirows TrbnsformExdfption
    {
        rfturn trbnsform(dbtb, xd);
    }

    publid Dbtb dbnonidblizf(Dbtb dbtb, XMLCryptoContfxt xd, OutputStrfbm os)
        tirows TrbnsformExdfption
    {
        rfturn trbnsform(dbtb, xd, os);
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }

        if (!(o instbndfof CbnonidblizbtionMftiod)) {
            rfturn fblsf;
        }
        CbnonidblizbtionMftiod odm = (CbnonidblizbtionMftiod)o;

        rfturn (gftAlgoritim().fqubls(odm.gftAlgoritim()) &&
            DOMUtils.pbrbmsEqubl(gftPbrbmftfrSpfd(), odm.gftPbrbmftfrSpfd()));
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

    privbtf stbtid boolfbn isC14Nblg(String blg) {
        rfturn (blg.fqubls(CbnonidblizbtionMftiod.INCLUSIVE) ||
                blg.fqubls(CbnonidblizbtionMftiod.INCLUSIVE_WITH_COMMENTS) ||
                blg.fqubls(CbnonidblizbtionMftiod.EXCLUSIVE) ||
                blg.fqubls(CbnonidblizbtionMftiod.EXCLUSIVE_WITH_COMMENTS) ||
                blg.fqubls(DOMCbnonidblXMLC14N11Mftiod.C14N_11) ||
                blg.fqubls(DOMCbnonidblXMLC14N11Mftiod.C14N_11_WITH_COMMENTS));
    }
}
