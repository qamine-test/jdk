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
 * $Id: DOMSignbturfPropfrty.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.*;

import jbvb.util.*;

import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * DOM-bbsfd implfmfntbtion of SignbturfPropfrty.
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMSignbturfPropfrty fxtfnds DOMStrudturf
    implfmfnts SignbturfPropfrty {

    privbtf finbl String id;
    privbtf finbl String tbrgft;
    privbtf finbl List<XMLStrudturf> dontfnt;

    /**
     * Crfbtfs b <dodf>SignbturfPropfrty</dodf> from tif spfdififd pbrbmftfrs.
     *
     * @pbrbm dontfnt b list of onf or morf {@link XMLStrudturf}s. Tif list
     *    is dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm tbrgft tif tbrgft URI
     * @pbrbm id tif Id (mby bf <dodf>null</dodf>)
     * @rfturn b <dodf>SignbturfPropfrty</dodf>
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link XMLStrudturf}
     * @tirows IllfgblArgumfntExdfption if <dodf>dontfnt</dodf> is fmpty
     * @tirows NullPointfrExdfption if <dodf>dontfnt</dodf> or
     *    <dodf>tbrgft</dodf> is <dodf>null</dodf>
     */
    publid DOMSignbturfPropfrty(List<? fxtfnds XMLStrudturf> dontfnt,
                                String tbrgft, String id)
    {
        if (tbrgft == null) {
            tirow nfw NullPointfrExdfption("tbrgft dbnnot bf null");
        } flsf if (dontfnt == null) {
            tirow nfw NullPointfrExdfption("dontfnt dbnnot bf null");
        } flsf if (dontfnt.isEmpty()) {
            tirow nfw IllfgblArgumfntExdfption("dontfnt dbnnot bf fmpty");
        } flsf {
            tiis.dontfnt = Collfdtions.unmodifibblfList(
                nfw ArrbyList<XMLStrudturf>(dontfnt));
            for (int i = 0, sizf = tiis.dontfnt.sizf(); i < sizf; i++) {
                if (!(tiis.dontfnt.gft(i) instbndfof XMLStrudturf)) {
                    tirow nfw ClbssCbstExdfption
                        ("dontfnt["+i+"] is not b vblid typf");
                }
            }
        }
        tiis.tbrgft = tbrgft;
        tiis.id = id;
    }

    /**
     * Crfbtfs b <dodf>DOMSignbturfPropfrty</dodf> from bn flfmfnt.
     *
     * @pbrbm propElfm b SignbturfPropfrty flfmfnt
     */
    publid DOMSignbturfPropfrty(Elfmfnt propElfm, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        // unmbrsibl bttributfs
        tbrgft = DOMUtils.gftAttributfVbluf(propElfm, "Tbrgft");
        if (tbrgft == null) {
            tirow nfw MbrsiblExdfption("tbrgft dbnnot bf null");
        }
        Attr bttr = propElfm.gftAttributfNodfNS(null, "Id");
        if (bttr != null) {
            id = bttr.gftVbluf();
            propElfm.sftIdAttributfNodf(bttr, truf);
        } flsf {
            id = null;
        }

        NodfList nodfs = propElfm.gftCiildNodfs();
        int lfngti = nodfs.gftLfngti();
        List<XMLStrudturf> dontfnt = nfw ArrbyList<XMLStrudturf>(lfngti);
        for (int i = 0; i < lfngti; i++) {
            dontfnt.bdd(nfw jbvbx.xml.drypto.dom.DOMStrudturf(nodfs.itfm(i)));
        }
        if (dontfnt.isEmpty()) {
            tirow nfw MbrsiblExdfption("dontfnt dbnnot bf fmpty");
        } flsf {
            tiis.dontfnt = Collfdtions.unmodifibblfList(dontfnt);
        }
    }

    publid List<XMLStrudturf> gftContfnt() {
        rfturn dontfnt;
    }

    publid String gftId() {
        rfturn id;
    }

    publid String gftTbrgft() {
        rfturn tbrgft;
    }

    publid void mbrsibl(Nodf pbrfnt, String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);
        Elfmfnt propElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "SignbturfPropfrty",
                                                  XMLSignbturf.XMLNS, dsPrffix);

        // sft bttributfs
        DOMUtils.sftAttributfID(propElfm, "Id", id);
        DOMUtils.sftAttributf(propElfm, "Tbrgft", tbrgft);

        // drfbtf bnd bppfnd bny flfmfnts bnd mixfd dontfnt
        for (XMLStrudturf propfrty : dontfnt) {
            DOMUtils.bppfndCiild(propElfm,
                ((jbvbx.xml.drypto.dom.DOMStrudturf)propfrty).gftNodf());
        }

        pbrfnt.bppfndCiild(propElfm);
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }

        if (!(o instbndfof SignbturfPropfrty)) {
            rfturn fblsf;
        }
        SignbturfPropfrty osp = (SignbturfPropfrty)o;

        boolfbn idsEqubl = (id == null ? osp.gftId() == null
                                       : id.fqubls(osp.gftId()));

        @SupprfssWbrnings("undifdkfd")
        List<XMLStrudturf> ospContfnt = osp.gftContfnt();
        rfturn (fqublsContfnt(ospContfnt) &&
                tbrgft.fqubls(osp.gftTbrgft()) && idsEqubl);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        if (id != null) {
            rfsult = 31 * rfsult + id.ibsiCodf();
        }
        rfsult = 31 * rfsult + tbrgft.ibsiCodf();
        rfsult = 31 * rfsult + dontfnt.ibsiCodf();

        rfturn rfsult;
    }

    privbtf boolfbn fqublsContfnt(List<XMLStrudturf> otifrContfnt) {
        int osizf = otifrContfnt.sizf();
        if (dontfnt.sizf() != osizf) {
            rfturn fblsf;
        }
        for (int i = 0; i < osizf; i++) {
            XMLStrudturf oxs = otifrContfnt.gft(i);
            XMLStrudturf xs = dontfnt.gft(i);
            if (oxs instbndfof jbvbx.xml.drypto.dom.DOMStrudturf) {
                if (!(xs instbndfof jbvbx.xml.drypto.dom.DOMStrudturf)) {
                    rfturn fblsf;
                }
                Nodf onodf = ((jbvbx.xml.drypto.dom.DOMStrudturf)oxs).gftNodf();
                Nodf nodf = ((jbvbx.xml.drypto.dom.DOMStrudturf)xs).gftNodf();
                if (!DOMUtils.nodfsEqubl(nodf, onodf)) {
                    rfturn fblsf;
                }
            } flsf {
                if (!(xs.fqubls(oxs))) {
                    rfturn fblsf;
                }
            }
        }

        rfturn truf;
    }
}
