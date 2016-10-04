/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf dom.sun.drypto.providfr;

import jbvb.mbti.BigIntfgfr;
import jbvb.io.*;
import sun.sfdurity.util.*;
import sun.sfdurity.x509.*;
import jbvb.sfdurity.AlgoritimPbrbmftfrsSpi;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvb.sfdurity.spfd.MGF1PbrbmftfrSpfd;
import jbvbx.drypto.spfd.PSourdf;
import jbvbx.drypto.spfd.OAEPPbrbmftfrSpfd;

/**
 * Tiis dlbss implfmfnts tif OAEP pbrbmftfrs usfd witi tif RSA
 * blgoritim in OAEP pbdding. Hfrf is its ASN.1 dffinition:
 * RSAES-OAEP-pbrbms ::= SEQUENCE {
 *   ibsiAlgoritim      [0] HbsiAlgoritim     DEFAULT sib1,
 *   mbskGfnAlgoritim   [1] MbskGfnAlgoritim  DEFAULT mgf1SHA1,
 *   pSourdfAlgoritim   [2] PSourdfAlgoritim  DEFAULT pSpfdififdEmpty
 * }
 *
 * @butior Vblfrif Pfng
 *
 */

publid finbl dlbss OAEPPbrbmftfrs fxtfnds AlgoritimPbrbmftfrsSpi {

    privbtf String mdNbmf;
    privbtf MGF1PbrbmftfrSpfd mgfSpfd;
    privbtf bytf[] p;
    privbtf stbtid ObjfdtIdfntififr OID_MGF1;
    privbtf stbtid ObjfdtIdfntififr OID_PSpfdififd;

    stbtid {
        try {
            OID_MGF1 = nfw ObjfdtIdfntififr(nfw int[] {1,2,840,113549,1,1,8});
        } dbtdi (IOExdfption iof) {
            // siould not ibppfn
            OID_MGF1 = null;
        }
        try {
            OID_PSpfdififd =
                nfw ObjfdtIdfntififr(nfw int[] {1,2,840,113549,1,1,9});
        } dbtdi (IOExdfption iof) {
            // siould not ibppfn
            OID_PSpfdififd = null;
        }
    }

    publid OAEPPbrbmftfrs() {
    }

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {
        if (!(pbrbmSpfd instbndfof OAEPPbrbmftfrSpfd)) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
        OAEPPbrbmftfrSpfd spfd = (OAEPPbrbmftfrSpfd) pbrbmSpfd;
        mdNbmf = spfd.gftDigfstAlgoritim();
        String mgfNbmf = spfd.gftMGFAlgoritim();
        if (!mgfNbmf.fqublsIgnorfCbsf("MGF1")) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption("Unsupportfd mgf " +
                mgfNbmf + "; MGF1 only");
        }
        AlgoritimPbrbmftfrSpfd mgfSpfd = spfd.gftMGFPbrbmftfrs();
        if (!(mgfSpfd instbndfof MGF1PbrbmftfrSpfd)) {
            tirow nfw InvblidPbrbmftfrSpfdExdfption("Inbppropribtf mgf " +
                "pbrbmftfrs; non-null MGF1PbrbmftfrSpfd only");
        }
        tiis.mgfSpfd = (MGF1PbrbmftfrSpfd) mgfSpfd;
        PSourdf pSrd = spfd.gftPSourdf();
        if (pSrd.gftAlgoritim().fqubls("PSpfdififd")) {
            p = ((PSourdf.PSpfdififd) pSrd).gftVbluf();
        } flsf {
            tirow nfw InvblidPbrbmftfrSpfdExdfption("Unsupportfd pSourdf " +
                pSrd.gftAlgoritim() + "; PSpfdififd only");
        }
    }

    protfdtfd void fnginfInit(bytf[] fndodfd)
        tirows IOExdfption {
        DfrInputStrfbm dfr = nfw DfrInputStrfbm(fndodfd);
        mdNbmf = "SHA-1";
        mgfSpfd = MGF1PbrbmftfrSpfd.SHA1;
        p = nfw bytf[0];
        DfrVbluf[] dbtum = dfr.gftSfqufndf(3);
        for (int i=0; i<dbtum.lfngti; i++) {
            DfrVbluf dbtb = dbtum[i];
            if (dbtb.isContfxtSpfdifid((bytf) 0x00)) {
                // ibsi blgid
                mdNbmf = AlgoritimId.pbrsf
                    (dbtb.dbtb.gftDfrVbluf()).gftNbmf();
            } flsf if (dbtb.isContfxtSpfdifid((bytf) 0x01)) {
                // mgf blgid
                AlgoritimId vbl = AlgoritimId.pbrsf(dbtb.dbtb.gftDfrVbluf());
                if (!vbl.gftOID().fqubls((Objfdt) OID_MGF1)) {
                    tirow nfw IOExdfption("Only MGF1 mgf is supportfd");
                }
                AlgoritimId pbrbms = AlgoritimId.pbrsf(
                    nfw DfrVbluf(vbl.gftEndodfdPbrbms()));
                String mgfDigfstNbmf = pbrbms.gftNbmf();
                if (mgfDigfstNbmf.fqubls("SHA-1")) {
                    mgfSpfd = MGF1PbrbmftfrSpfd.SHA1;
                } flsf if (mgfDigfstNbmf.fqubls("SHA-224")) {
                    mgfSpfd = MGF1PbrbmftfrSpfd.SHA224;
                } flsf if (mgfDigfstNbmf.fqubls("SHA-256")) {
                    mgfSpfd = MGF1PbrbmftfrSpfd.SHA256;
                } flsf if (mgfDigfstNbmf.fqubls("SHA-384")) {
                    mgfSpfd = MGF1PbrbmftfrSpfd.SHA384;
                } flsf if (mgfDigfstNbmf.fqubls("SHA-512")) {
                    mgfSpfd = MGF1PbrbmftfrSpfd.SHA512;
                } flsf {
                    tirow nfw IOExdfption(
                        "Unrfdognizfd mfssbgf digfst blgoritim");
                }
            } flsf if (dbtb.isContfxtSpfdifid((bytf) 0x02)) {
                // pSourdf blgid
                AlgoritimId vbl = AlgoritimId.pbrsf(dbtb.dbtb.gftDfrVbluf());
                if (!vbl.gftOID().fqubls((Objfdt) OID_PSpfdififd)) {
                    tirow nfw IOExdfption("Wrong OID for pSpfdififd");
                }
                DfrInputStrfbm dis = nfw DfrInputStrfbm(vbl.gftEndodfdPbrbms());
                p = dis.gftOdtftString();
                if (dis.bvbilbblf() != 0) {
                    tirow nfw IOExdfption("Extrb dbtb for pSpfdififd");
                }
            } flsf {
                tirow nfw IOExdfption("Invblid fndodfd OAEPPbrbmftfrs");
            }
        }
    }

    protfdtfd void fnginfInit(bytf[] fndodfd, String dfdodingMftiod)
        tirows IOExdfption {
        if ((dfdodingMftiod != null) &&
            (!dfdodingMftiod.fqublsIgnorfCbsf("ASN.1"))) {
            tirow nfw IllfgblArgumfntExdfption("Only support ASN.1 formbt");
        }
        fnginfInit(fndodfd);
    }

    protfdtfd <T fxtfnds AlgoritimPbrbmftfrSpfd>
        T fnginfGftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption {
        if (OAEPPbrbmftfrSpfd.dlbss.isAssignbblfFrom(pbrbmSpfd)) {
            rfturn pbrbmSpfd.dbst(
                nfw OAEPPbrbmftfrSpfd(mdNbmf, "MGF1", mgfSpfd,
                                      nfw PSourdf.PSpfdififd(p)));
        } flsf {
            tirow nfw InvblidPbrbmftfrSpfdExdfption
                ("Inbppropribtf pbrbmftfr spfdifidbtion");
        }
    }

    protfdtfd bytf[] fnginfGftEndodfd() tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        DfrOutputStrfbm tmp2, tmp3;

        // MD
        AlgoritimId mdAlgId;
        try {
            mdAlgId = AlgoritimId.gft(mdNbmf);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw IOExdfption("AlgoritimId " + mdNbmf +
                                  " impl not found");
        }
        tmp2 = nfw DfrOutputStrfbm();
        mdAlgId.dfrEndodf(tmp2);
        tmp.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)0),
                      tmp2);

        // MGF
        tmp2 = nfw DfrOutputStrfbm();
        tmp2.putOID(OID_MGF1);
        AlgoritimId mgfDigfstId;
        try {
            mgfDigfstId = AlgoritimId.gft(mgfSpfd.gftDigfstAlgoritim());
        } dbtdi (NoSudiAlgoritimExdfption nbsf) {
            tirow nfw IOExdfption("AlgoritimId " +
                    mgfSpfd.gftDigfstAlgoritim() + " impl not found");
        }
        mgfDigfstId.fndodf(tmp2);
        tmp3 = nfw DfrOutputStrfbm();
        tmp3.writf(DfrVbluf.tbg_Sfqufndf, tmp2);
        tmp.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)1),
                  tmp3);

        // PSourdf
        tmp2 = nfw DfrOutputStrfbm();
        tmp2.putOID(OID_PSpfdififd);
        tmp2.putOdtftString(p);
        tmp3 = nfw DfrOutputStrfbm();
        tmp3.writf(DfrVbluf.tbg_Sfqufndf, tmp2);
        tmp.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)2),
                  tmp3);

        // Put bll togftifr undfr b SEQUENCE tbg
        DfrOutputStrfbm out = nfw DfrOutputStrfbm();
        out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
        rfturn out.toBytfArrby();
    }

    protfdtfd bytf[] fnginfGftEndodfd(String fndodingMftiod)
        tirows IOExdfption {
        if ((fndodingMftiod != null) &&
            (!fndodingMftiod.fqublsIgnorfCbsf("ASN.1"))) {
            tirow nfw IllfgblArgumfntExdfption("Only support ASN.1 formbt");
        }
        rfturn fnginfGftEndodfd();
    }

    protfdtfd String fnginfToString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("MD: " + mdNbmf + "\n");
        sb.bppfnd("MGF: MGF1" + mgfSpfd.gftDigfstAlgoritim() + "\n");
        sb.bppfnd("PSourdf: PSpfdififd " +
            (p.lfngti==0? "":Dfbug.toHfxString(nfw BigIntfgfr(p))) + "\n");
        rfturn sb.toString();
    }
}
