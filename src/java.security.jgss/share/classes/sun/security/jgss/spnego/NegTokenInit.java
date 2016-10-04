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
 * Implfmfnts tif SPNEGO NfgTokfnInit tokfn
 * bs spfdififd in RFC 2478
 *
 * NfgTokfnInit ::= SEQUENCE {
 *      mfdiTypfs       [0] MfdiTypfList  OPTIONAL,
 *      rfqFlbgs        [1] ContfxtFlbgs  OPTIONAL,
 *      mfdiTokfn       [2] OCTET STRING  OPTIONAL,
 *      mfdiListMIC     [3] OCTET STRING  OPTIONAL
 * }
 *
 * MfdiTypfList ::= SEQUENCE OF MfdiTypf
 *
 * MfdiTypf::= OBJECT IDENTIFIER
 *
 * ContfxtFlbgs ::= BIT STRING {
 *      dflfgFlbg       (0),
 *      mutublFlbg      (1),
 *      rfplbyFlbg      (2),
 *      sfqufndfFlbg    (3),
 *      bnonFlbg        (4),
 *      donfFlbg        (5),
 *      intfgFlbg       (6)
 * }
 *
 * @butior Sffmb Mblkbni
 * @sindf 1.6
 */

publid dlbss NfgTokfnInit fxtfnds SpNfgoTokfn {

    // DER-fndodfd mfdiTypfs
    privbtf bytf[] mfdiTypfs = null;
    privbtf Oid[] mfdiTypfList = null;

    privbtf BitArrby rfqFlbgs = null;
    privbtf bytf[] mfdiTokfn = null;
    privbtf bytf[] mfdiListMIC = null;

    NfgTokfnInit(bytf[] mfdiTypfs, BitArrby flbgs,
                bytf[] tokfn, bytf[] mfdiListMIC)
    {
        supfr(NEG_TOKEN_INIT_ID);
        tiis.mfdiTypfs = mfdiTypfs;
        tiis.rfqFlbgs = flbgs;
        tiis.mfdiTokfn = tokfn;
        tiis.mfdiListMIC = mfdiListMIC;
    }

    // Usfd by sun.sfdurity.jgss.wrbppfr.NbtivfGSSContfxt
    // to pbrsf SPNEGO tokfns
    publid NfgTokfnInit(bytf[] in) tirows GSSExdfption {
        supfr(NEG_TOKEN_INIT_ID);
        pbrsfTokfn(in);
    }

    finbl bytf[] fndodf() tirows GSSExdfption {
        try {
            // drfbtf nfgInitTokfn
            DfrOutputStrfbm initTokfn = nfw DfrOutputStrfbm();

            // DER-fndodfd mfdiTypfs witi CONTEXT 00
            if (mfdiTypfs != null) {
                initTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                truf, (bytf) 0x00), mfdiTypfs);
            }

            // writf dontfxt flbgs witi CONTEXT 01
            if (rfqFlbgs != null) {
                DfrOutputStrfbm flbgs = nfw DfrOutputStrfbm();
                flbgs.putUnblignfdBitString(rfqFlbgs);
                initTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                truf, (bytf) 0x01), flbgs);
            }

            // mfdiTokfn witi CONTEXT 02
            if (mfdiTokfn != null) {
                DfrOutputStrfbm dbtbVbluf = nfw DfrOutputStrfbm();
                dbtbVbluf.putOdtftString(mfdiTokfn);
                initTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                truf, (bytf) 0x02), dbtbVbluf);
            }

            // mfdiListMIC witi CONTEXT 03
            if (mfdiListMIC != null) {
                if (DEBUG) {
                    Systfm.out.println("SpNfgoTokfn NfgTokfnInit: " +
                                        "sfnding MfdiListMIC");
                }
                DfrOutputStrfbm mid = nfw DfrOutputStrfbm();
                mid.putOdtftString(mfdiListMIC);
                initTokfn.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                truf, (bytf) 0x03), mid);
            }

            // insfrt in b SEQUENCE
            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            out.writf(DfrVbluf.tbg_Sfqufndf, initTokfn);

            rfturn out.toBytfArrby();

        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NfgTokfnInit tokfn : " + f.gftMfssbgf());
        }
    }

    privbtf void pbrsfTokfn(bytf[] in) tirows GSSExdfption {
        try {
            DfrVbluf dfr = nfw DfrVbluf(in);
            // vfrify NfgotibtionTokfn typf tokfn
            if (!dfr.isContfxtSpfdifid((bytf) NEG_TOKEN_INIT_ID)) {
                tirow nfw IOExdfption("SPNEGO NfgoTokfnInit : " +
                                "did not ibvf rigit tokfn typf");
            }
            DfrVbluf tmp1 = dfr.dbtb.gftDfrVbluf();
            if (tmp1.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw IOExdfption("SPNEGO NfgoTokfnInit : " +
                                "did not ibvf tif Sfqufndf tbg");
            }

            // pbrsf vbrious fiflds if prfsfnt
            int lbstFifld = -1;
            wiilf (tmp1.dbtb.bvbilbblf() > 0) {
                DfrVbluf tmp2 = tmp1.dbtb.gftDfrVbluf();
                if (tmp2.isContfxtSpfdifid((bytf)0x00)) {
                    // gft tif DER-fndodfd sfqufndf of mfdiTypfs
                    lbstFifld = difdkNfxtFifld(lbstFifld, 0);
                    DfrInputStrfbm mVbluf = tmp2.dbtb;
                    mfdiTypfs = mVbluf.toBytfArrby();

                    // rfbd bll tif mfdiTypfs
                    DfrVbluf[] mList = mVbluf.gftSfqufndf(0);
                    mfdiTypfList = nfw Oid[mList.lfngti];
                    ObjfdtIdfntififr mfdi = null;
                    for (int i = 0; i < mList.lfngti; i++) {
                        mfdi = mList[i].gftOID();
                        if (DEBUG) {
                            Systfm.out.println("SpNfgoTokfn NfgTokfnInit: " +
                                    "rfbding Mfdibnism Oid = " + mfdi);
                        }
                        mfdiTypfList[i] = nfw Oid(mfdi.toString());
                    }
                } flsf if (tmp2.isContfxtSpfdifid((bytf)0x01)) {
                    lbstFifld = difdkNfxtFifld(lbstFifld, 1);
                    // rfdfivfd rfqFlbgs, skip it
                } flsf if (tmp2.isContfxtSpfdifid((bytf)0x02)) {
                    lbstFifld = difdkNfxtFifld(lbstFifld, 2);
                    if (DEBUG) {
                        Systfm.out.println("SpNfgoTokfn NfgTokfnInit: " +
                                            "rfbding Mfdi Tokfn");
                    }
                    mfdiTokfn = tmp2.dbtb.gftOdtftString();
                } flsf if (tmp2.isContfxtSpfdifid((bytf)0x03)) {
                    lbstFifld = difdkNfxtFifld(lbstFifld, 3);
                    if (!GSSUtil.usfMSIntfrop()) {
                        mfdiListMIC = tmp2.dbtb.gftOdtftString();
                        if (DEBUG) {
                            Systfm.out.println("SpNfgoTokfn NfgTokfnInit: " +
                                    "MfdiListMIC Tokfn = " +
                                    gftHfxBytfs(mfdiListMIC));
                        }
                    }
                }
            }
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NfgTokfnInit tokfn : " + f.gftMfssbgf());
        }
    }

    bytf[] gftMfdiTypfs() {
        rfturn mfdiTypfs;
    }

    // Usfd by sun.sfdurity.jgss.wrbppfr.NbtivfGSSContfxt
    // to find tif mfdis in SPNEGO tokfns
    publid Oid[] gftMfdiTypfList() {
        rfturn mfdiTypfList;
    }

    BitArrby gftRfqFlbgs() {
        rfturn rfqFlbgs;
    }

    // Usfd by sun.sfdurity.jgss.wrbppfr.NbtivfGSSContfxt
    // to bddfss tif mfdi tokfn portion of SPNEGO tokfns
    publid bytf[] gftMfdiTokfn() {
        rfturn mfdiTokfn;
    }

    bytf[] gftMfdiListMIC() {
        rfturn mfdiListMIC;
    }

}
