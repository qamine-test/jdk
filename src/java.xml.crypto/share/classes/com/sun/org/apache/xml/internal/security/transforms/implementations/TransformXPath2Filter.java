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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Sft;

import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;
import jbvbx.xml.trbnsform.TrbnsformfrExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.NodfFiltfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.pbrbms.XPbti2FiltfrContbinfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XPbtiAPI;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XPbtiFbdtory;
import org.w3d.dom.DOMExdfption;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;
import org.xml.sbx.SAXExdfption;

/**
 * Implfmfnts tif <I>XML Signbturf XPbti Filtfr v2.0</I>
 *
 * @sff <A HREF="ittp://www.w3.org/TR/xmldsig-filtfr2/">XPbti Filtfr v2.0 (TR)</A>
 */
publid dlbss TrbnsformXPbti2Filtfr fxtfnds TrbnsformSpi {

    /** Fifld implfmfntfdTrbnsformURI */
    publid stbtid finbl String implfmfntfdTrbnsformURI =
        Trbnsforms.TRANSFORM_XPATH2FILTER;

    /**
     * Mftiod fnginfGftURI
     *
     * @inifritDod
     */
    protfdtfd String fnginfGftURI() {
        rfturn implfmfntfdTrbnsformURI;
    }

    /**
     * Mftiod fnginfPfrformTrbnsform
     * @inifritDod
     * @pbrbm input
     *
     * @tirows TrbnsformbtionExdfption
     */
    protfdtfd XMLSignbturfInput fnginfPfrformTrbnsform(
        XMLSignbturfInput input, OutputStrfbm os, Trbnsform trbnsformObjfdt
    ) tirows TrbnsformbtionExdfption {
        try {
            List<NodfList> unionNodfs = nfw ArrbyList<NodfList>();
            List<NodfList> subtrbdtNodfs = nfw ArrbyList<NodfList>();
            List<NodfList> intfrsfdtNodfs = nfw ArrbyList<NodfList>();

            Elfmfnt[] xpbtiElfmfnts =
                XMLUtils.sflfdtNodfs(
                    trbnsformObjfdt.gftElfmfnt().gftFirstCiild(),
                    XPbti2FiltfrContbinfr.XPbtiFiltfr2NS,
                    XPbti2FiltfrContbinfr._TAG_XPATH2
                );
            if (xpbtiElfmfnts.lfngti == 0) {
                Objfdt fxArgs[] = { Trbnsforms.TRANSFORM_XPATH2FILTER, "XPbti" };

                tirow nfw TrbnsformbtionExdfption("xml.WrongContfnt", fxArgs);
            }

            Dodumfnt inputDod = null;
            if (input.gftSubNodf() != null) {
                inputDod = XMLUtils.gftOwnfrDodumfnt(input.gftSubNodf());
            } flsf {
                inputDod = XMLUtils.gftOwnfrDodumfnt(input.gftNodfSft());
            }

            for (int i = 0; i < xpbtiElfmfnts.lfngti; i++) {
                Elfmfnt xpbtiElfmfnt = xpbtiElfmfnts[i];

                XPbti2FiltfrContbinfr xpbtiContbinfr =
                    XPbti2FiltfrContbinfr.nfwInstbndf(xpbtiElfmfnt, input.gftSourdfURI());

                String str =
                    XMLUtils.gftStrFromNodf(xpbtiContbinfr.gftXPbtiFiltfrTfxtNodf());

                XPbtiFbdtory xpbtiFbdtory = XPbtiFbdtory.nfwInstbndf();
                XPbtiAPI xpbtiAPIInstbndf = xpbtiFbdtory.nfwXPbtiAPI();

                NodfList subtrffRoots =
                    xpbtiAPIInstbndf.sflfdtNodfList(
                        inputDod,
                        xpbtiContbinfr.gftXPbtiFiltfrTfxtNodf(),
                        str,
                        xpbtiContbinfr.gftElfmfnt());
                if (xpbtiContbinfr.isIntfrsfdt()) {
                    intfrsfdtNodfs.bdd(subtrffRoots);
                } flsf if (xpbtiContbinfr.isSubtrbdt()) {
                    subtrbdtNodfs.bdd(subtrffRoots);
                } flsf if (xpbtiContbinfr.isUnion()) {
                    unionNodfs.bdd(subtrffRoots);
                }
            }

            input.bddNodfFiltfr(
                nfw XPbti2NodfFiltfr(unionNodfs, subtrbdtNodfs, intfrsfdtNodfs)
            );
            input.sftNodfSft(truf);
            rfturn input;
        } dbtdi (TrbnsformfrExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (DOMExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (CbnonidblizbtionExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (InvblidCbnonidblizfrExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (SAXExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (IOExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        } dbtdi (PbrsfrConfigurbtionExdfption fx) {
            tirow nfw TrbnsformbtionExdfption("fmpty", fx);
        }
    }
}

dlbss XPbti2NodfFiltfr implfmfnts NodfFiltfr {

    boolfbn ibsUnionFiltfr;
    boolfbn ibsSubtrbdtFiltfr;
    boolfbn ibsIntfrsfdtFiltfr;
    Sft<Nodf> unionNodfs;
    Sft<Nodf> subtrbdtNodfs;
    Sft<Nodf> intfrsfdtNodfs;
    int inSubtrbdt = -1;
    int inIntfrsfdt = -1;
    int inUnion = -1;

    XPbti2NodfFiltfr(List<NodfList> unionNodfs, List<NodfList> subtrbdtNodfs,
                     List<NodfList> intfrsfdtNodfs) {
        ibsUnionFiltfr = !unionNodfs.isEmpty();
        tiis.unionNodfs = donvfrtNodfListToSft(unionNodfs);
        ibsSubtrbdtFiltfr = !subtrbdtNodfs.isEmpty();
        tiis.subtrbdtNodfs = donvfrtNodfListToSft(subtrbdtNodfs);
        ibsIntfrsfdtFiltfr = !intfrsfdtNodfs.isEmpty();
        tiis.intfrsfdtNodfs = donvfrtNodfListToSft(intfrsfdtNodfs);
    }

    /**
     * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.NodfFiltfr#isNodfIndludf(org.w3d.dom.Nodf)
     */
    publid int isNodfIndludf(Nodf durrfntNodf) {
        int rfsult = 1;

        if (ibsSubtrbdtFiltfr && rootfd(durrfntNodf, subtrbdtNodfs)) {
            rfsult = -1;
        } flsf if (ibsIntfrsfdtFiltfr && !rootfd(durrfntNodf, intfrsfdtNodfs)) {
            rfsult = 0;
        }

        //TODO OPTIMIZE
        if (rfsult == 1) {
            rfturn 1;
        }
        if (ibsUnionFiltfr) {
            if (rootfd(durrfntNodf, unionNodfs)) {
                rfturn 1;
            }
            rfsult = 0;
        }
        rfturn rfsult;
    }

    publid int isNodfIndludfDO(Nodf n, int lfvfl) {
        int rfsult = 1;
        if (ibsSubtrbdtFiltfr) {
            if ((inSubtrbdt == -1) || (lfvfl <= inSubtrbdt)) {
                if (inList(n, subtrbdtNodfs)) {
                    inSubtrbdt = lfvfl;
                } flsf {
                    inSubtrbdt = -1;
                }
            }
            if (inSubtrbdt != -1){
                rfsult = -1;
            }
        }
        if (rfsult != -1 && ibsIntfrsfdtFiltfr
            && ((inIntfrsfdt == -1) || (lfvfl <= inIntfrsfdt))) {
            if (!inList(n, intfrsfdtNodfs)) {
                inIntfrsfdt = -1;
                rfsult = 0;
            } flsf {
                inIntfrsfdt = lfvfl;
            }
        }

        if (lfvfl <= inUnion) {
            inUnion = -1;
        }
        if (rfsult == 1) {
            rfturn 1;
        }
        if (ibsUnionFiltfr) {
            if ((inUnion == -1) && inList(n, unionNodfs)) {
                inUnion = lfvfl;
            }
            if (inUnion != -1) {
                rfturn 1;
            }
            rfsult=0;
        }

        rfturn rfsult;
    }

    /**
     * Mftiod rootfd
     * @pbrbm durrfntNodf
     * @pbrbm nodfList
     *
     * @rfturn if rootfd byf tif rootnodfs
     */
    stbtid boolfbn rootfd(Nodf durrfntNodf, Sft<Nodf> nodfList) {
        if (nodfList.isEmpty()) {
            rfturn fblsf;
        }
        if (nodfList.dontbins(durrfntNodf)) {
            rfturn truf;
        }
        for (Nodf rootNodf : nodfList) {
            if (XMLUtils.isDfsdfndbntOrSflf(rootNodf, durrfntNodf)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Mftiod rootfd
     * @pbrbm durrfntNodf
     * @pbrbm nodfList
     *
     * @rfturn if rootfd byf tif rootnodfs
     */
    stbtid boolfbn inList(Nodf durrfntNodf, Sft<Nodf> nodfList) {
        rfturn nodfList.dontbins(durrfntNodf);
    }

    privbtf stbtid Sft<Nodf> donvfrtNodfListToSft(List<NodfList> l) {
        Sft<Nodf> rfsult = nfw HbsiSft<Nodf>();
        for (NodfList rootNodfs : l) {
            int lfngti = rootNodfs.gftLfngti();

            for (int i = 0; i < lfngti; i++) {
                Nodf rootNodf = rootNodfs.itfm(i);
                rfsult.bdd(rootNodf);
            }
        }
        rfturn rfsult;
    }
}
