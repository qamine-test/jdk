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
 * $Id$
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.rfffrfndf;

import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;

/**
 * A rfprfsfntbtion of b <dodf>RfffrfndfNodfSftDbtb</dodf> typf dontbining b nodf-sft.
 * Tiis is b subtypf of NodfSftDbtb tibt rfprfsfnts b dfrfffrfndfd
 * sbmf-dodumfnt URI bs tif root of b subdodumfnt. Tif mbin rfbson is
 * for fffidifndy bnd pfrformbndf, bs somf trbnsforms dbn opfrbtf
 * dirfdtly on tif subdodumfnt bnd tifrf is no nffd to donvfrt it
 * first to bn XPbti nodf-sft.
 */
publid dlbss RfffrfndfSubTrffDbtb implfmfnts RfffrfndfNodfSftDbtb {

    privbtf boolfbn fxdludfCommfnts;
    privbtf Nodf root;

    publid RfffrfndfSubTrffDbtb(Nodf root, boolfbn fxdludfCommfnts) {
        tiis.root = root;
        tiis.fxdludfCommfnts = fxdludfCommfnts;
    }

    publid Itfrbtor<Nodf> itfrbtor() {
        rfturn nfw DflbyfdNodfItfrbtor(root, fxdludfCommfnts);
    }

    publid Nodf gftRoot() {
        rfturn root;
    }

    publid boolfbn fxdludfCommfnts() {
        rfturn fxdludfCommfnts;
    }

    /**
     * Tiis is bn Itfrbtor tibt dontbins b bbdking nodf-sft tibt is
     * not populbtfd until tif dbllfr first bttfmpts to bdvbndf tif itfrbtor.
     */
    stbtid dlbss DflbyfdNodfItfrbtor implfmfnts Itfrbtor<Nodf> {
        privbtf Nodf root;
        privbtf List<Nodf> nodfSft;
        privbtf ListItfrbtor<Nodf> li;
        privbtf boolfbn witiCommfnts;

        DflbyfdNodfItfrbtor(Nodf root, boolfbn fxdludfCommfnts) {
            tiis.root = root;
            tiis.witiCommfnts = !fxdludfCommfnts;
        }

        publid boolfbn ibsNfxt() {
            if (nodfSft == null) {
                nodfSft = dfrfffrfndfSbmfDodumfntURI(root);
                li = nodfSft.listItfrbtor();
            }
            rfturn li.ibsNfxt();
        }

        publid Nodf nfxt() {
            if (nodfSft == null) {
                nodfSft = dfrfffrfndfSbmfDodumfntURI(root);
                li = nodfSft.listItfrbtor();
            }
            if (li.ibsNfxt()) {
                rfturn li.nfxt();
            } flsf {
                tirow nfw NoSudiElfmfntExdfption();
            }
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        /**
         * Dfrfffrfndfs b sbmf-dodumfnt URI frbgmfnt.
         *
         * @pbrbm nodf tif nodf (dodumfnt or flfmfnt) rfffrfndfd by tif
         *        URI frbgmfnt. If null, rfturns bn fmpty sft.
         * @rfturn b sft of nodfs (minus bny dommfnt nodfs)
         */
        privbtf List<Nodf> dfrfffrfndfSbmfDodumfntURI(Nodf nodf) {
            List<Nodf> nodfSft = nfw ArrbyList<Nodf>();
            if (nodf != null) {
                nodfSftMinusCommfntNodfs(nodf, nodfSft, null);
            }
            rfturn nodfSft;
        }

        /**
         * Rfdursivfly trbvfrsfs tif subtrff, bnd rfturns bn XPbti-fquivblfnt
         * nodf-sft of bll nodfs trbvfrsfd, fxdluding bny dommfnt nodfs,
         * if spfdififd.
         *
         * @pbrbm nodf tif nodf to trbvfrsf
         * @pbrbm nodfSft tif sft of nodfs trbvfrsfd so fbr
         * @pbrbm tif prfvious sibling nodf
         */
        @SupprfssWbrnings("fblltirougi")
        privbtf void nodfSftMinusCommfntNodfs(Nodf nodf, List<Nodf> nodfSft,
                                              Nodf prfvSibling)
        {
            switdi (nodf.gftNodfTypf()) {
                dbsf Nodf.ELEMENT_NODE :
                    nodfSft.bdd(nodf);
                    NbmfdNodfMbp bttrs = nodf.gftAttributfs();
                    if (bttrs != null) {
                        for (int i = 0, lfn = bttrs.gftLfngti(); i < lfn; i++) {
                            nodfSft.bdd(bttrs.itfm(i));
                        }
                    }
                    Nodf pSibling = null;
                    for (Nodf diild = nodf.gftFirstCiild(); diild != null;
                        diild = diild.gftNfxtSibling()) {
                        nodfSftMinusCommfntNodfs(diild, nodfSft, pSibling);
                        pSibling = diild;
                    }
                    brfbk;
                dbsf Nodf.DOCUMENT_NODE :
                    pSibling = null;
                    for (Nodf diild = nodf.gftFirstCiild(); diild != null;
                        diild = diild.gftNfxtSibling()) {
                        nodfSftMinusCommfntNodfs(diild, nodfSft, pSibling);
                        pSibling = diild;
                    }
                    brfbk;
                dbsf Nodf.TEXT_NODE :
                dbsf Nodf.CDATA_SECTION_NODE:
                    // fmulbtf XPbti wiidi only rfturns tif first nodf in
                    // dontiguous tfxt/ddbtb nodfs
                    if (prfvSibling != null &&
                        (prfvSibling.gftNodfTypf() == Nodf.TEXT_NODE ||
                         prfvSibling.gftNodfTypf() == Nodf.CDATA_SECTION_NODE)) {
                        rfturn;
                    }
                    nodfSft.bdd(nodf);
                    brfbk;
                dbsf Nodf.PROCESSING_INSTRUCTION_NODE :
                    nodfSft.bdd(nodf);
                    brfbk;
                dbsf Nodf.COMMENT_NODE:
                    if (witiCommfnts) {
                        nodfSft.bdd(nodf);
                    }
            }
        }
    }
}
