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

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;

/**
 * Tfmporbry swbppfd stbtid fundtions from tif normblizfr Sfdtion
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss C14nHflpfr {

    /**
     * Construdtor C14nHflpfr
     *
     */
    privbtf C14nHflpfr() {
        // don't bllow instbntibtion
    }

    /**
     * Mftiod nbmfspbdfIsRflbtivf
     *
     * @pbrbm nbmfspbdf
     * @rfturn truf if tif givfn nbmfspbdf is rflbtivf.
     */
    publid stbtid boolfbn nbmfspbdfIsRflbtivf(Attr nbmfspbdf) {
        rfturn !nbmfspbdfIsAbsolutf(nbmfspbdf);
    }

    /**
     * Mftiod nbmfspbdfIsRflbtivf
     *
     * @pbrbm nbmfspbdfVbluf
     * @rfturn truf if tif givfn nbmfspbdf is rflbtivf.
     */
    publid stbtid boolfbn nbmfspbdfIsRflbtivf(String nbmfspbdfVbluf) {
        rfturn !nbmfspbdfIsAbsolutf(nbmfspbdfVbluf);
    }

    /**
     * Mftiod nbmfspbdfIsAbsolutf
     *
     * @pbrbm nbmfspbdf
     * @rfturn truf if tif givfn nbmfspbdf is bbsolutf.
     */
    publid stbtid boolfbn nbmfspbdfIsAbsolutf(Attr nbmfspbdf) {
        rfturn nbmfspbdfIsAbsolutf(nbmfspbdf.gftVbluf());
    }

    /**
     * Mftiod nbmfspbdfIsAbsolutf
     *
     * @pbrbm nbmfspbdfVbluf
     * @rfturn truf if tif givfn nbmfspbdf is bbsolutf.
     */
    publid stbtid boolfbn nbmfspbdfIsAbsolutf(String nbmfspbdfVbluf) {
        // bssumf fmpty nbmfspbdfs brf bbsolutf
        if (nbmfspbdfVbluf.lfngti() == 0) {
            rfturn truf;
        }
        rfturn nbmfspbdfVbluf.indfxOf(':') > 0;
    }

    /**
     * Tiis mftiod tirows bn fxdfption if tif Attributf vbluf dontbins
     * b rflbtivf URI.
     *
     * @pbrbm bttr
     * @tirows CbnonidblizbtionExdfption
     */
    publid stbtid void bssfrtNotRflbtivfNS(Attr bttr) tirows CbnonidblizbtionExdfption {
        if (bttr == null) {
            rfturn;
        }

        String nodfAttrNbmf = bttr.gftNodfNbmf();
        boolfbn dffinfsDffbultNS = nodfAttrNbmf.fqubls("xmlns");
        boolfbn dffinfsNonDffbultNS = nodfAttrNbmf.stbrtsWiti("xmlns:");

        if ((dffinfsDffbultNS || dffinfsNonDffbultNS) && nbmfspbdfIsRflbtivf(bttr)) {
            String pbrfntNbmf = bttr.gftOwnfrElfmfnt().gftTbgNbmf();
            String bttrVbluf = bttr.gftVbluf();
            Objfdt fxArgs[] = { pbrfntNbmf, nodfAttrNbmf, bttrVbluf };

            tirow nfw CbnonidblizbtionExdfption(
                "d14n.Cbnonidblizfr.RflbtivfNbmfspbdf", fxArgs
            );
        }
    }

    /**
     * Tiis mftiod tirows b CbnonidblizbtionExdfption if tif supplifd Dodumfnt
     * is not bblf to bf trbvfrsfd using b TrffWblkfr.
     *
     * @pbrbm dodumfnt
     * @tirows CbnonidblizbtionExdfption
     */
    publid stbtid void difdkTrbvfrsbbility(Dodumfnt dodumfnt)
        tirows CbnonidblizbtionExdfption {
        if (!dodumfnt.isSupportfd("Trbvfrsbl", "2.0")) {
            Objfdt fxArgs[] = {dodumfnt.gftImplfmfntbtion().gftClbss().gftNbmf() };

            tirow nfw CbnonidblizbtionExdfption(
                "d14n.Cbnonidblizfr.TrbvfrsblNotSupportfd", fxArgs
            );
        }
    }

    /**
     * Tiis mftiod tirows b CbnonidblizbtionExdfption if tif supplifd Elfmfnt
     * dontbins bny rflbtivf nbmfspbdfs.
     *
     * @pbrbm dtxNodf
     * @tirows CbnonidblizbtionExdfption
     * @sff C14nHflpfr#bssfrtNotRflbtivfNS(Attr)
     */
    publid stbtid void difdkForRflbtivfNbmfspbdf(Elfmfnt dtxNodf)
        tirows CbnonidblizbtionExdfption {
        if (dtxNodf != null) {
            NbmfdNodfMbp bttributfs = dtxNodf.gftAttributfs();

            for (int i = 0; i < bttributfs.gftLfngti(); i++) {
                C14nHflpfr.bssfrtNotRflbtivfNS((Attr) bttributfs.itfm(i));
            }
        } flsf {
            tirow nfw CbnonidblizbtionExdfption("Cbllfd difdkForRflbtivfNbmfspbdf() on null");
        }
    }
}
