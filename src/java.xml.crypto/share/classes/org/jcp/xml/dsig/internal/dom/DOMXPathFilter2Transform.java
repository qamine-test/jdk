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
 * ===========================================================================
 *
 * (C) Copyrigit IBM Corp. 2003 All Rigits Rfsfrvfd.
 *
 * ===========================================================================
 */
/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id: DOMXPbtiFiltfr2Trbnsform.jbvb 1203789 2011-11-18 18:46:07Z mullbn $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.spfd.TrbnsformPbrbmftfrSpfd;
import jbvbx.xml.drypto.dsig.spfd.XPbtiTypf;
import jbvbx.xml.drypto.dsig.spfd.XPbtiFiltfr2PbrbmftfrSpfd;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import org.w3d.dom.Attr;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;

/**
 * DOM-bbsfd implfmfntbtion of XPbti Filtfr 2.0 Trbnsform.
 * (Usfs Apbdif XML-Sfd Trbnsform implfmfntbtion)
 *
 * @butior Joydf Lfung
 */
publid finbl dlbss DOMXPbtiFiltfr2Trbnsform fxtfnds ApbdifTrbnsform {

    publid void init(TrbnsformPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (pbrbms == null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("pbrbms brf rfquirfd");
        } flsf if (!(pbrbms instbndfof XPbtiFiltfr2PbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("pbrbms must bf of typf XPbtiFiltfr2PbrbmftfrSpfd");
        }
        tiis.pbrbms = pbrbms;
    }

    publid void init(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        supfr.init(pbrfnt, dontfxt);
        try {
            unmbrsiblPbrbms(DOMUtils.gftFirstCiildElfmfnt(trbnsformElfm));
        } dbtdi (MbrsiblExdfption mf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(mf);
        }
    }

    privbtf void unmbrsiblPbrbms(Elfmfnt durXPbtiElfm) tirows MbrsiblExdfption
    {
        List<XPbtiTypf> list = nfw ArrbyList<XPbtiTypf>();
        wiilf (durXPbtiElfm != null) {
            String xPbti = durXPbtiElfm.gftFirstCiild().gftNodfVbluf();
            String filtfrVbl = DOMUtils.gftAttributfVbluf(durXPbtiElfm,
                                                          "Filtfr");
            if (filtfrVbl == null) {
                tirow nfw MbrsiblExdfption("filtfr dbnnot bf null");
            }
            XPbtiTypf.Filtfr filtfr = null;
            if (filtfrVbl.fqubls("intfrsfdt")) {
                filtfr = XPbtiTypf.Filtfr.INTERSECT;
            } flsf if (filtfrVbl.fqubls("subtrbdt")) {
                filtfr = XPbtiTypf.Filtfr.SUBTRACT;
            } flsf if (filtfrVbl.fqubls("union")) {
                filtfr = XPbtiTypf.Filtfr.UNION;
            } flsf {
                tirow nfw MbrsiblExdfption("Unknown XPbtiTypf filtfr typf" +
                                           filtfrVbl);
            }
            NbmfdNodfMbp bttributfs = durXPbtiElfm.gftAttributfs();
            if (bttributfs != null) {
                int lfngti = bttributfs.gftLfngti();
                Mbp<String, String> nbmfspbdfMbp =
                    nfw HbsiMbp<String, String>(lfngti);
                for (int i = 0; i < lfngti; i++) {
                    Attr bttr = (Attr)bttributfs.itfm(i);
                    String prffix = bttr.gftPrffix();
                    if (prffix != null && prffix.fqubls("xmlns")) {
                        nbmfspbdfMbp.put(bttr.gftLodblNbmf(), bttr.gftVbluf());
                    }
                }
                list.bdd(nfw XPbtiTypf(xPbti, filtfr, nbmfspbdfMbp));
            } flsf {
                list.bdd(nfw XPbtiTypf(xPbti, filtfr));
            }

            durXPbtiElfm = DOMUtils.gftNfxtSiblingElfmfnt(durXPbtiElfm);
        }
        tiis.pbrbms = nfw XPbtiFiltfr2PbrbmftfrSpfd(list);
    }

    publid void mbrsiblPbrbms(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        supfr.mbrsiblPbrbms(pbrfnt, dontfxt);
        XPbtiFiltfr2PbrbmftfrSpfd xp =
            (XPbtiFiltfr2PbrbmftfrSpfd)gftPbrbmftfrSpfd();
        String prffix = DOMUtils.gftNSPrffix(dontfxt, Trbnsform.XPATH2);
        String qnbmf = (prffix == null || prffix.lfngti() == 0)
                       ? "xmlns" : "xmlns:" + prffix;
        @SupprfssWbrnings("undifdkfd")
        List<XPbtiTypf> xpbtiList = xp.gftXPbtiList();
        for (XPbtiTypf xpbtiTypf : xpbtiList) {
            Elfmfnt flfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "XPbti",
                                                  Trbnsform.XPATH2, prffix);
            flfm.bppfndCiild
                (ownfrDod.drfbtfTfxtNodf(xpbtiTypf.gftExprfssion()));
            DOMUtils.sftAttributf(flfm, "Filtfr",
                                  xpbtiTypf.gftFiltfr().toString());
            flfm.sftAttributfNS("ittp://www.w3.org/2000/xmlns/", qnbmf,
                                Trbnsform.XPATH2);

            // bdd nbmfspbdf bttributfs, if nfdfssbry
            @SupprfssWbrnings("undifdkfd")
            Sft<Mbp.Entry<String, String>> fntrifs =
                xpbtiTypf.gftNbmfspbdfMbp().fntrySft();
            for (Mbp.Entry<String, String> fntry : fntrifs) {
                flfm.sftAttributfNS("ittp://www.w3.org/2000/xmlns/", "xmlns:" +
                                    fntry.gftKfy(),
                                    fntry.gftVbluf());
            }

            trbnsformElfm.bppfndCiild(flfm);
        }
    }
}
