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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.Mbp;
import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.AlgoritimAlrfbdyRfgistfrfdExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformBbsf64Dfdodf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformC14N;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformC14N11;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformC14N11_WitiCommfnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformC14NExdlusivf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformC14NExdlusivfWitiCommfnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformC14NWitiCommfnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformEnvflopfdSignbturf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformXPbti;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformXPbti2Filtfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions.TrbnsformXSLT;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.HflpfrNodfList;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NodfList;
import org.xml.sbx.SAXExdfption;

/**
 * Implfmfnts tif bfibviour of tif <dodf>ds:Trbnsform</dodf> flfmfnt.
 *
 * Tiis <dodf>Trbnsform</dodf>(Fbdtory) dlbss bdts bs tif Fbdtory bnd Proxy of
 * tif implfmfnting dlbss tibt supports tif fundtionblity of <b
 * irff=ittp://www.w3.org/TR/xmldsig-dorf/#sfd-TrbnsformAlg>b Trbnsform
 * blgoritim</b>.
 * Implfmfnts tif Fbdtory bnd Proxy pbttfrn for ds:Trbnsform blgoritims.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 * @sff Trbnsforms
 * @sff TrbnsformSpi
 */
publid finbl dlbss Trbnsform fxtfnds SignbturfElfmfntProxy {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(Trbnsform.dlbss.gftNbmf());

    /** All bvbilbblf Trbnsform dlbssfs brf rfgistfrfd ifrf */
    privbtf stbtid Mbp<String, Clbss<? fxtfnds TrbnsformSpi>> trbnsformSpiHbsi =
        nfw CondurrfntHbsiMbp<String, Clbss<? fxtfnds TrbnsformSpi>>();

    privbtf finbl TrbnsformSpi trbnsformSpi;

    /**
     * Gfnfrbtfs b Trbnsform objfdt tibt implfmfnts tif spfdififd
     * <dodf>Trbnsform blgoritim</dodf> URI.
     *
     * @pbrbm dod tif proxy {@link Dodumfnt}
     * @pbrbm blgoritimURI <dodf>Trbnsform blgoritim</dodf> URI rfprfsfntbtion,
     * sudi bs spfdififd in
     * <b irff=ittp://www.w3.org/TR/xmldsig-dorf/#sfd-TrbnsformAlg>Trbnsform blgoritim </b>
     * @tirows InvblidTrbnsformExdfption
     */
    publid Trbnsform(Dodumfnt dod, String blgoritimURI) tirows InvblidTrbnsformExdfption {
        tiis(dod, blgoritimURI, (NodfList)null);
    }

    /**
     * Gfnfrbtfs b Trbnsform objfdt tibt implfmfnts tif spfdififd
     * <dodf>Trbnsform blgoritim</dodf> URI.
     *
     * @pbrbm blgoritimURI <dodf>Trbnsform blgoritim</dodf> URI rfprfsfntbtion,
     * sudi bs spfdififd in
     * <b irff=ittp://www.w3.org/TR/xmldsig-dorf/#sfd-TrbnsformAlg>Trbnsform blgoritim </b>
     * @pbrbm dontfxtCiild tif diild flfmfnt of <dodf>Trbnsform</dodf> flfmfnt
     * @pbrbm dod tif proxy {@link Dodumfnt}
     * @tirows InvblidTrbnsformExdfption
     */
    publid Trbnsform(Dodumfnt dod, String blgoritimURI, Elfmfnt dontfxtCiild)
        tirows InvblidTrbnsformExdfption {
        supfr(dod);
        HflpfrNodfList dontfxtNodfs = null;

        if (dontfxtCiild != null) {
            dontfxtNodfs = nfw HflpfrNodfList();

            XMLUtils.bddRfturnToElfmfnt(dod, dontfxtNodfs);
            dontfxtNodfs.bppfndCiild(dontfxtCiild);
            XMLUtils.bddRfturnToElfmfnt(dod, dontfxtNodfs);
        }

        trbnsformSpi = initiblizfTrbnsform(blgoritimURI, dontfxtNodfs);
    }

    /**
     * Construdts {@link Trbnsform}
     *
     * @pbrbm dod tif {@link Dodumfnt} in wiidi <dodf>Trbnsform</dodf> will bf
     * plbdfd
     * @pbrbm blgoritimURI URI rfprfsfntbtion of <dodf>Trbnsform blgoritim</dodf>
     * @pbrbm dontfxtNodfs tif diild nodf list of <dodf>Trbnsform</dodf> flfmfnt
     * @tirows InvblidTrbnsformExdfption
     */
    publid Trbnsform(Dodumfnt dod, String blgoritimURI, NodfList dontfxtNodfs)
        tirows InvblidTrbnsformExdfption {
        supfr(dod);
        trbnsformSpi = initiblizfTrbnsform(blgoritimURI, dontfxtNodfs);
    }

    /**
     * @pbrbm flfmfnt <dodf>ds:Trbnsform</dodf> flfmfnt
     * @pbrbm BbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @tirows InvblidTrbnsformExdfption
     * @tirows TrbnsformbtionExdfption
     * @tirows XMLSfdurityExdfption
     */
    publid Trbnsform(Elfmfnt flfmfnt, String BbsfURI)
        tirows InvblidTrbnsformExdfption, TrbnsformbtionExdfption, XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);

        // rftrifvf Algoritim Attributf from ds:Trbnsform
        String blgoritimURI = flfmfnt.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);

        if (blgoritimURI == null || blgoritimURI.lfngti() == 0) {
            Objfdt fxArgs[] = { Constbnts._ATT_ALGORITHM, Constbnts._TAG_TRANSFORM };
            tirow nfw TrbnsformbtionExdfption("xml.WrongContfnt", fxArgs);
        }

        Clbss<? fxtfnds TrbnsformSpi> trbnsformSpiClbss = trbnsformSpiHbsi.gft(blgoritimURI);
        if (trbnsformSpiClbss == null) {
            Objfdt fxArgs[] = { blgoritimURI };
            tirow nfw InvblidTrbnsformExdfption("signbturf.Trbnsform.UnknownTrbnsform", fxArgs);
        }
        try {
            trbnsformSpi = trbnsformSpiClbss.nfwInstbndf();
        } dbtdi (InstbntibtionExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI };
            tirow nfw InvblidTrbnsformExdfption(
                "signbturf.Trbnsform.UnknownTrbnsform", fxArgs, fx
            );
        } dbtdi (IllfgblAddfssExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI };
            tirow nfw InvblidTrbnsformExdfption(
                "signbturf.Trbnsform.UnknownTrbnsform", fxArgs, fx
            );
        }
    }

    /**
     * Rfgistfrs implfmfnting dlbss of tif Trbnsform blgoritim witi blgoritimURI
     *
     * @pbrbm blgoritimURI blgoritimURI URI rfprfsfntbtion of <dodf>Trbnsform blgoritim</dodf>
     * @pbrbm implfmfntingClbss <dodf>implfmfntingClbss</dodf> tif implfmfnting
     * dlbss of {@link TrbnsformSpi}
     * @tirows AlgoritimAlrfbdyRfgistfrfdExdfption if spfdififd blgoritimURI
     * is blrfbdy rfgistfrfd
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid void rfgistfr(String blgoritimURI, String implfmfntingClbss)
        tirows AlgoritimAlrfbdyRfgistfrfdExdfption, ClbssNotFoundExdfption,
            InvblidTrbnsformExdfption {
        // brf wf blrfbdy rfgistfrfd?
        Clbss<? fxtfnds TrbnsformSpi> trbnsformSpi = trbnsformSpiHbsi.gft(blgoritimURI);
        if (trbnsformSpi != null) {
            Objfdt fxArgs[] = { blgoritimURI, trbnsformSpi };
            tirow nfw AlgoritimAlrfbdyRfgistfrfdExdfption("blgoritim.blrfbdyRfgistfrfd", fxArgs);
        }
        Clbss<? fxtfnds TrbnsformSpi> trbnsformSpiClbss =
            (Clbss<? fxtfnds TrbnsformSpi>)
                ClbssLobdfrUtils.lobdClbss(implfmfntingClbss, Trbnsform.dlbss);
        trbnsformSpiHbsi.put(blgoritimURI, trbnsformSpiClbss);
    }

    /**
     * Rfgistfrs implfmfnting dlbss of tif Trbnsform blgoritim witi blgoritimURI
     *
     * @pbrbm blgoritimURI blgoritimURI URI rfprfsfntbtion of <dodf>Trbnsform blgoritim</dodf>
     * @pbrbm implfmfntingClbss <dodf>implfmfntingClbss</dodf> tif implfmfnting
     * dlbss of {@link TrbnsformSpi}
     * @tirows AlgoritimAlrfbdyRfgistfrfdExdfption if spfdififd blgoritimURI
     * is blrfbdy rfgistfrfd
     */
    publid stbtid void rfgistfr(String blgoritimURI, Clbss<? fxtfnds TrbnsformSpi> implfmfntingClbss)
        tirows AlgoritimAlrfbdyRfgistfrfdExdfption {
        // brf wf blrfbdy rfgistfrfd?
        Clbss<? fxtfnds TrbnsformSpi> trbnsformSpi = trbnsformSpiHbsi.gft(blgoritimURI);
        if (trbnsformSpi != null) {
            Objfdt fxArgs[] = { blgoritimURI, trbnsformSpi };
            tirow nfw AlgoritimAlrfbdyRfgistfrfdExdfption("blgoritim.blrfbdyRfgistfrfd", fxArgs);
        }
        trbnsformSpiHbsi.put(blgoritimURI, implfmfntingClbss);
    }

    /**
     * Tiis mftiod rfgistfrs tif dffbult blgoritims.
     */
    publid stbtid void rfgistfrDffbultAlgoritims() {
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_BASE64_DECODE, TrbnsformBbsf64Dfdodf.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_C14N_OMIT_COMMENTS, TrbnsformC14N.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_C14N_WITH_COMMENTS, TrbnsformC14NWitiCommfnts.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_C14N11_OMIT_COMMENTS, TrbnsformC14N11.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_C14N11_WITH_COMMENTS, TrbnsformC14N11_WitiCommfnts.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, TrbnsformC14NExdlusivf.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_C14N_EXCL_WITH_COMMENTS, TrbnsformC14NExdlusivfWitiCommfnts.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_XPATH, TrbnsformXPbti.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_ENVELOPED_SIGNATURE, TrbnsformEnvflopfdSignbturf.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_XSLT, TrbnsformXSLT.dlbss
        );
        trbnsformSpiHbsi.put(
            Trbnsforms.TRANSFORM_XPATH2FILTER, TrbnsformXPbti2Filtfr.dlbss
        );
    }

    /**
     * Rfturns tif URI rfprfsfntbtion of Trbnsformbtion blgoritim
     *
     * @rfturn tif URI rfprfsfntbtion of Trbnsformbtion blgoritim
     */
    publid String gftURI() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Trbnsforms tif input, bnd gfnfrbtfs {@link XMLSignbturfInput} bs output.
     *
     * @pbrbm input input {@link XMLSignbturfInput} wiidi dbn supplifd Odtft
     * Strfbm bnd NodfSft bs Input of Trbnsformbtion
     * @rfturn tif {@link XMLSignbturfInput} dlbss bs tif rfsult of
     * trbnsformbtion
     * @tirows CbnonidblizbtionExdfption
     * @tirows IOExdfption
     * @tirows InvblidCbnonidblizfrExdfption
     * @tirows TrbnsformbtionExdfption
     */
    publid XMLSignbturfInput pfrformTrbnsform(XMLSignbturfInput input)
        tirows IOExdfption, CbnonidblizbtionExdfption,
               InvblidCbnonidblizfrExdfption, TrbnsformbtionExdfption {
        rfturn pfrformTrbnsform(input, null);
    }

    /**
     * Trbnsforms tif input, bnd gfnfrbtfs {@link XMLSignbturfInput} bs output.
     *
     * @pbrbm input input {@link XMLSignbturfInput} wiidi dbn supplifd Odtfdt
     * Strfbm bnd NodfSft bs Input of Trbnsformbtion
     * @pbrbm os wifrf to output tif rfsult of tif lbst trbnsformbtion
     * @rfturn tif {@link XMLSignbturfInput} dlbss bs tif rfsult of
     * trbnsformbtion
     * @tirows CbnonidblizbtionExdfption
     * @tirows IOExdfption
     * @tirows InvblidCbnonidblizfrExdfption
     * @tirows TrbnsformbtionExdfption
     */
    publid XMLSignbturfInput pfrformTrbnsform(
        XMLSignbturfInput input, OutputStrfbm os
    ) tirows IOExdfption, CbnonidblizbtionExdfption,
        InvblidCbnonidblizfrExdfption, TrbnsformbtionExdfption {
        XMLSignbturfInput rfsult = null;

        try {
            rfsult = trbnsformSpi.fnginfPfrformTrbnsform(input, os, tiis);
        } dbtdi (PbrsfrConfigurbtionExdfption fx) {
            Objfdt fxArgs[] = { tiis.gftURI(), "PbrsfrConfigurbtionExdfption" };
            tirow nfw CbnonidblizbtionExdfption(
                "signbturf.Trbnsform.ErrorDuringTrbnsform", fxArgs, fx);
        } dbtdi (SAXExdfption fx) {
            Objfdt fxArgs[] = { tiis.gftURI(), "SAXExdfption" };
            tirow nfw CbnonidblizbtionExdfption(
                "signbturf.Trbnsform.ErrorDuringTrbnsform", fxArgs, fx);
        }

        rfturn rfsult;
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_TRANSFORM;
    }

    /**
     * Initiblizf tif trbnsform objfdt.
     */
    privbtf TrbnsformSpi initiblizfTrbnsform(String blgoritimURI, NodfList dontfxtNodfs)
        tirows InvblidTrbnsformExdfption {

        tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ALGORITHM, blgoritimURI);

        Clbss<? fxtfnds TrbnsformSpi> trbnsformSpiClbss = trbnsformSpiHbsi.gft(blgoritimURI);
        if (trbnsformSpiClbss == null) {
            Objfdt fxArgs[] = { blgoritimURI };
            tirow nfw InvblidTrbnsformExdfption("signbturf.Trbnsform.UnknownTrbnsform", fxArgs);
        }
        TrbnsformSpi nfwTrbnsformSpi = null;
        try {
            nfwTrbnsformSpi = trbnsformSpiClbss.nfwInstbndf();
        } dbtdi (InstbntibtionExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI };
            tirow nfw InvblidTrbnsformExdfption(
                "signbturf.Trbnsform.UnknownTrbnsform", fxArgs, fx
            );
        } dbtdi (IllfgblAddfssExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI };
            tirow nfw InvblidTrbnsformExdfption(
                "signbturf.Trbnsform.UnknownTrbnsform", fxArgs, fx
            );
        }

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbtf URI \"" + blgoritimURI + "\" dlbss \""
                      + nfwTrbnsformSpi.gftClbss() + "\"");
            log.log(jbvb.util.logging.Lfvfl.FINE, "Tif NodfList is " + dontfxtNodfs);
        }

        // givf it to tif durrfnt dodumfnt
        if (dontfxtNodfs != null) {
            for (int i = 0; i < dontfxtNodfs.gftLfngti(); i++) {
                tiis.donstrudtionElfmfnt.bppfndCiild(dontfxtNodfs.itfm(i).dlonfNodf(truf));
            }
        }
        rfturn nfwTrbnsformSpi;
    }

}
