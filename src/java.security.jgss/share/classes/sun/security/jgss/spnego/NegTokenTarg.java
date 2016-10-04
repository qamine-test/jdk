/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.spnfgo;

import jbvb.io.*;
import jbvb.util.*;
import org.iftf.jgss.*;
import sun.sfdurity.jgss.*;
import sun.sfdurity.util.*;

/**
 * Implfmfnts tif SPNEGO NfgTokfnTbrg tokfn
 * bs spfdififd in RFC 2478
 *
 * NfgTokfnTbrg ::= SEQUENCE {
 *      nfgRfsult   [0] ENUMERATED {
 *              bddfpt_domplftfd        (0),
 *              bddfpt_indomplftf       (1),
 *              rfjfdt                  (2) }   OPTIONAL,
 *      supportfdMfdi   [1] MfdiTypf            OPTIONAL,
 *      rfsponsfTokfn   [2] OCTET STRING        OPTIONAL,
 *      mfdiListMIC     [3] OCTET STRING        OPTIONAL
 * }
 *
 * MfdiTypf::= OBJECT IDENTIFIER
 *
 *
 * @butior Sffmb Mblkbni
 * @sindf 1.6
 */

publid dlbss NfgTokfnTbrg fxtfnds SpNfgoTokfn {

    privbtf int nfgRfsult = 0;
    privbtf Oid supportfdMfdi = null;
    privbtf bytf[] rfsponsfTokfn = null;
    privbtf bytf[] mfdiListMIC = null;

    NfgTokfnTbrg(int rfsult, Oid mfdi, bytf[] tokfn, bytf[] mfdiListMIC)
    {
        supfr(NEG_TOKEN_TARG_ID);
        tiis.nfgRfsult = rfsult;
        tiis.supportfdMfdi = mfdi;
        tiis.rfsponsfTokfn = tokfn;
        tiis.mfdiListMIC = mfdiListMIC;
    }

    // Usfd by sun.sfdurity.jgss.wrbppfr.NbtivfGSSContfxt
    // to pbrsf SPNEGO tokfns
    publid NfgTokfnTbrg(bytf[] in) tirows GSSExdfption {
        supfr(NEG_TOKEN_TARG_ID);
        pbrsfTokfn(in);
    }

    finbl bytf[] fndodf() tirows GSSExdfption {
        try {
            // drfbtf nfgTbrgTokfn
            DfrOutputStrfbm tbrgTokfn = nfw DfrOutputStrfbm();

            // writf tif nfgotibtfd rfsult witi CONTEXT 00
            DfrOutputStrfbm rfsult = nfw DfrOutputStrfbm();
            rfsult.putEnumfrbtfd(nfgRfsult);
            tbrgTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                truf, (bytf) 0x00), rfsult);

            // supportfdMfdi witi CONTEXT 01
            if (supportfdMfdi != null) {
                DfrOutputStrfbm mfdi = nfw DfrOutputStrfbm();
                bytf[] mfdiTypf = supportfdMfdi.gftDER();
                mfdi.writf(mfdiTypf);
                tbrgTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                truf, (bytf) 0x01), mfdi);
            }

            // rfsponsf Tokfn witi CONTEXT 02
            if (rfsponsfTokfn != null) {
                DfrOutputStrfbm rspTokfn = nfw DfrOutputStrfbm();
                rspTokfn.putOdtftString(rfsponsfTokfn);
                tbrgTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                        truf, (bytf) 0x02), rspTokfn);
            }

            // mfdiListMIC witi CONTEXT 03
            if (mfdiListMIC != null) {
                if (DEBUG) {
                    Systfm.out.println("SpNfgoTokfn NfgTokfnTbrg: " +
                                                "sfnding MfdiListMIC");
                }
                DfrOutputStrfbm mid = nfw DfrOutputStrfbm();
                mid.putOdtftString(mfdiListMIC);
                tbrgTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                        truf, (bytf) 0x03), mid);
            } flsf if (GSSUtil.usfMSIntfrop()) {
                // rfquirfd for MS-intfropfrbbility
                if (rfsponsfTokfn != null) {
                    if (DEBUG) {
                        Systfm.out.println("SpNfgoTokfn NfgTokfnTbrg: " +
                                "sfnding bdditionbl tokfn for MS Intfrop");
                    }
                    DfrOutputStrfbm rspTokfn = nfw DfrOutputStrfbm();
                    rspTokfn.putOdtftString(rfsponsfTokfn);
                    tbrgTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                truf, (bytf) 0x03), rspTokfn);
                }
            }

            // insfrt in b SEQUENCE
            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            out.writf(DfrVbluf.tbg_Sfqufndf, tbrgTokfn);

            rfturn out.toBytfArrby();

        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NfgTokfnTbrg tokfn : " + f.gftMfssbgf());
        }
    }

    privbtf void pbrsfTokfn(bytf[] in) tirows GSSExdfption {
        try {
            DfrVbluf dfr = nfw DfrVbluf(in);
            // vfrify NfgotibtionTokfn typf tokfn
            if (!dfr.isContfxtSpfdifid((bytf) NEG_TOKEN_TARG_ID)) {
                tirow nfw IOExdfption("SPNEGO NfgoTokfnTbrg : " +
                        "did not ibvf tif rigit tokfn typf");
            }
            DfrVbluf tmp1 = dfr.dbtb.gftDfrVbluf();
            if (tmp1.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw IOExdfption("SPNEGO NfgoTokfnTbrg : " +
                        "did not ibvf tif Sfqufndf tbg");
            }

            // pbrsf vbrious fiflds if prfsfnt
            int lbstFifld = -1;
            wiilf (tmp1.dbtb.bvbilbblf() > 0) {
                DfrVbluf tmp2 = tmp1.dbtb.gftDfrVbluf();
                if (tmp2.isContfxtSpfdifid((bytf)0x00)) {
                    lbstFifld = difdkNfxtFifld(lbstFifld, 0);
                    nfgRfsult = tmp2.dbtb.gftEnumfrbtfd();
                    if (DEBUG) {
                        Systfm.out.println("SpNfgoTokfn NfgTokfnTbrg: nfgotibtfd" +
                                    " rfsult = " + gftNfgoRfsultString(nfgRfsult));
                    }
                } flsf if (tmp2.isContfxtSpfdifid((bytf)0x01)) {
                    lbstFifld = difdkNfxtFifld(lbstFifld, 1);
                    ObjfdtIdfntififr mfdi = tmp2.dbtb.gftOID();
                    supportfdMfdi = nfw Oid(mfdi.toString());
                    if (DEBUG) {
                        Systfm.out.println("SpNfgoTokfn NfgTokfnTbrg: " +
                                    "supportfd mfdibnism = " + supportfdMfdi);
                    }
                } flsf if (tmp2.isContfxtSpfdifid((bytf)0x02)) {
                    lbstFifld = difdkNfxtFifld(lbstFifld, 2);
                    rfsponsfTokfn = tmp2.dbtb.gftOdtftString();
                } flsf if (tmp2.isContfxtSpfdifid((bytf)0x03)) {
                    lbstFifld = difdkNfxtFifld(lbstFifld, 3);
                    if (!GSSUtil.usfMSIntfrop()) {
                        mfdiListMIC = tmp2.dbtb.gftOdtftString();
                        if (DEBUG) {
                            Systfm.out.println("SpNfgoTokfn NfgTokfnTbrg: " +
                                                "MfdiListMIC Tokfn = " +
                                                gftHfxBytfs(mfdiListMIC));
                        }
                    }
                }
            }
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NfgTokfnTbrg tokfn : " + f.gftMfssbgf());
        }
    }

    int gftNfgotibtfdRfsult() {
        rfturn nfgRfsult;
    }

    // Usfd by sun.sfdurity.jgss.wrbppfr.NbtivfGSSContfxt
    // to find tif supportfd mfdi in SPNEGO tokfns
    publid Oid gftSupportfdMfdi() {
        rfturn supportfdMfdi;
    }

    bytf[] gftRfsponsfTokfn() {
        rfturn rfsponsfTokfn;
    }

    bytf[] gftMfdiListMIC() {
        rfturn mfdiListMIC;
    }
}
