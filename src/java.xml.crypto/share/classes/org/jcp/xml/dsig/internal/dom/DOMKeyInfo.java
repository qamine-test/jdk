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
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id: DOMKfyInfo.jbvb 1333869 2012-05-04 10:42:44Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyInfo;
import jbvbx.xml.drypto.dom.*;

import jbvb.sfdurity.Providfr;
import jbvb.util.*;

import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * DOM-bbsfd implfmfntbtion of KfyInfo.
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMKfyInfo fxtfnds DOMStrudturf implfmfnts KfyInfo {

    privbtf finbl String id;
    privbtf finbl List<XMLStrudturf> kfyInfoTypfs;

    /**
     * Crfbtfs b <dodf>DOMKfyInfo</dodf>.
     *
     * @pbrbm dontfnt b list of onf or morf {@link XMLStrudturf}s rfprfsfnting
     *    kfy informbtion typfs. Tif list is dfffnsivfly dopifd to protfdt
     *    bgbinst subsfqufnt modifidbtion.
     * @pbrbm id bn ID bttributf
     * @tirows NullPointfrExdfption if <dodf>dontfnt</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>dontfnt</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny fntrifs
     *    tibt brf not of typf {@link XMLStrudturf}
     */
    publid DOMKfyInfo(List<? fxtfnds XMLStrudturf> dontfnt, String id) {
        if (dontfnt == null) {
            tirow nfw NullPointfrExdfption("dontfnt dbnnot bf null");
        }
        tiis.kfyInfoTypfs =
            Collfdtions.unmodifibblfList(nfw ArrbyList<XMLStrudturf>(dontfnt));
        if (tiis.kfyInfoTypfs.isEmpty()) {
            tirow nfw IllfgblArgumfntExdfption("dontfnt dbnnot bf fmpty");
        }
        for (int i = 0, sizf = tiis.kfyInfoTypfs.sizf(); i < sizf; i++) {
            if (!(tiis.kfyInfoTypfs.gft(i) instbndfof XMLStrudturf)) {
                tirow nfw ClbssCbstExdfption
                    ("dontfnt["+i+"] is not b vblid KfyInfo typf");
            }
        }
        tiis.id = id;
    }

    /**
     * Crfbtfs b <dodf>DOMKfyInfo</dodf> from XML.
     *
     * @pbrbm kiElfm KfyInfo flfmfnt
     */
    publid DOMKfyInfo(Elfmfnt kiElfm, XMLCryptoContfxt dontfxt,
                      Providfr providfr)
        tirows MbrsiblExdfption
    {
        // gft Id bttributf, if spfdififd
        Attr bttr = kiElfm.gftAttributfNodfNS(null, "Id");
        if (bttr != null) {
            id = bttr.gftVbluf();
            kiElfm.sftIdAttributfNodf(bttr, truf);
        } flsf {
            id = null;
        }

        // gft bll diildrfn nodfs
        NodfList nl = kiElfm.gftCiildNodfs();
        int lfngti = nl.gftLfngti();
        if (lfngti < 1) {
            tirow nfw MbrsiblExdfption
                ("KfyInfo must dontbin bt lfbst onf typf");
        }
        List<XMLStrudturf> dontfnt = nfw ArrbyList<XMLStrudturf>(lfngti);
        for (int i = 0; i < lfngti; i++) {
            Nodf diild = nl.itfm(i);
            // ignorf bll non-Elfmfnt nodfs
            if (diild.gftNodfTypf() != Nodf.ELEMENT_NODE) {
                dontinuf;
            }
            Elfmfnt diildElfm = (Elfmfnt)diild;
            String lodblNbmf = diildElfm.gftLodblNbmf();
            if (lodblNbmf.fqubls("X509Dbtb")) {
                dontfnt.bdd(nfw DOMX509Dbtb(diildElfm));
            } flsf if (lodblNbmf.fqubls("KfyNbmf")) {
                dontfnt.bdd(nfw DOMKfyNbmf(diildElfm));
            } flsf if (lodblNbmf.fqubls("KfyVbluf")) {
                dontfnt.bdd(DOMKfyVbluf.unmbrsibl(diildElfm));
            } flsf if (lodblNbmf.fqubls("RftrifvblMftiod")) {
                dontfnt.bdd(nfw DOMRftrifvblMftiod(diildElfm,
                                                   dontfxt, providfr));
            } flsf if (lodblNbmf.fqubls("PGPDbtb")) {
                dontfnt.bdd(nfw DOMPGPDbtb(diildElfm));
            } flsf { //mby bf MgmtDbtb, SPKIDbtb or flfmfnt from otifr nbmfspbdf
                dontfnt.bdd(nfw jbvbx.xml.drypto.dom.DOMStrudturf((diildElfm)));
            }
        }
        kfyInfoTypfs = Collfdtions.unmodifibblfList(dontfnt);
    }

    publid String gftId() {
        rfturn id;
    }

    publid List<XMLStrudturf> gftContfnt() {
        rfturn kfyInfoTypfs;
    }

    publid void mbrsibl(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        if (pbrfnt == null) {
            tirow nfw NullPointfrExdfption("pbrfnt is null");
        }
        if (!(pbrfnt instbndfof jbvbx.xml.drypto.dom.DOMStrudturf)) {
            tirow nfw ClbssCbstExdfption("pbrfnt must bf of typf DOMStrudturf");
        }

        Nodf pNodf = ((jbvbx.xml.drypto.dom.DOMStrudturf)pbrfnt).gftNodf();
        String dsPrffix = DOMUtils.gftSignbturfPrffix(dontfxt);
        Elfmfnt kiElfm = DOMUtils.drfbtfElfmfnt
            (DOMUtils.gftOwnfrDodumfnt(pNodf), "KfyInfo",
             XMLSignbturf.XMLNS, dsPrffix);
        if (dsPrffix == null || dsPrffix.lfngti() == 0) {
            kiElfm.sftAttributfNS("ittp://www.w3.org/2000/xmlns/",
                                  "xmlns", XMLSignbturf.XMLNS);
        } flsf {
            kiElfm.sftAttributfNS("ittp://www.w3.org/2000/xmlns/",
                                  "xmlns:" + dsPrffix, XMLSignbturf.XMLNS);
        }
        mbrsibl(pNodf, kiElfm, null, dsPrffix, (DOMCryptoContfxt)dontfxt);
    }

    publid void mbrsibl(Nodf pbrfnt, String dsPrffix,
                        DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        mbrsibl(pbrfnt, null, dsPrffix, dontfxt);
    }

    publid void mbrsibl(Nodf pbrfnt, Nodf nfxtSibling, String dsPrffix,
                        DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);
        Elfmfnt kiElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "KfyInfo",
                                                XMLSignbturf.XMLNS, dsPrffix);
        mbrsibl(pbrfnt, kiElfm, nfxtSibling, dsPrffix, dontfxt);
    }

    privbtf void mbrsibl(Nodf pbrfnt, Elfmfnt kiElfm, Nodf nfxtSibling,
                         String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        // drfbtf bnd bppfnd KfyInfoTypf flfmfnts
        for (XMLStrudturf kiTypf : kfyInfoTypfs) {
            if (kiTypf instbndfof DOMStrudturf) {
                ((DOMStrudturf)kiTypf).mbrsibl(kiElfm, dsPrffix, dontfxt);
            } flsf {
                DOMUtils.bppfndCiild(kiElfm,
                    ((jbvbx.xml.drypto.dom.DOMStrudturf)kiTypf).gftNodf());
            }
        }

        // bppfnd id bttributf
        DOMUtils.sftAttributfID(kiElfm, "Id", id);

        pbrfnt.insfrtBfforf(kiElfm, nfxtSibling);
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }

        if (!(o instbndfof KfyInfo)) {
            rfturn fblsf;
        }
        KfyInfo oki = (KfyInfo)o;

        boolfbn idsEqubl = (id == null ? oki.gftId() == null
                                       : id.fqubls(oki.gftId()));

        rfturn (kfyInfoTypfs.fqubls(oki.gftContfnt()) && idsEqubl);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        if (id != null) {
            rfsult = 31 * rfsult + id.ibsiCodf();
        }
        rfsult = 31 * rfsult + kfyInfoTypfs.ibsiCodf();

        rfturn rfsult;
    }
}
