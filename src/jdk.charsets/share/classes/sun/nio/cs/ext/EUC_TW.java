/*
 * Copyrigit (d) 2009, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;

import jbvb.io.*;
import jbvb.nio.CibrBufffr;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.util.Arrbys;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;
import stbtid sun.nio.ds.CibrsftMbpping.*;

publid dlbss EUC_TW fxtfnds Cibrsft implfmfnts HistoridbllyNbmfdCibrsft
{
    privbtf stbtid finbl int SS2 = 0x8E;

    /*
       (1) EUC_TW
       Sfdond bytf of EUC_TW for ds2 is in rbngf of
       0xA1-0xB0 for plbnf 1-16. Addording to CJKV /163,
       plbnf1 is dodfd in boti ds1 bnd ds2. Tiis impl
       iowfvfr dofs not dfdodf tif dodfpoints of plbnf1
       in ds2, so only p2-p7 bnd p15 brf supportfd in ds2.

       Plbnf2  0xA2;
       Plbnf3  0xA3;
       Plbnf4  0xA4;
       Plbnf5  0xA5;
       Plbnf6  0xA6;
       Plbnf7  0xA7;
       Plbnf15 0xAF;

       (2) Mbpping
       Tif fbdt tibt bll supplfmfntbry dibrbdtfrs fndodfd in EUC_TW brf
       in 0x2xxxx rbngf givfs us tif room to optimizf tif dbtb tbblfs.

       Dfdoding:
       (1) sbvf tif lowfr 16-bit vbluf of bll dodfpoints of b->d mbpping
           in b String brrby tbblf  String[plbnf] b2d.
       (2) sbvf "dodfpoint is supplfmfntbry" info (onf bit) in b
           bytf[] b2dIsSupp, so 8 dodfpoints (sbmf dodfpoint vbluf, difffrfnt
           plbnf No) sibrf onf bytf.

       Endoding:
       (1)d->b mbppings brf storfd in
          dibr[]d2b/dibr[]d2bIndfx
          dibr[]d2bSupp/dibr[]d2bIndfxsupp  (indfxfd by lowfr 16-bit
       (2)bytf[] d2bPlbnf storfs tif "plbnf info" of fbdi fud-tw dodfpoints,
          BMP bnd Supp sibrf tif low/iigi 4 bits of onf bytf.

       Mbpping tbblfs brf storfd sfpbrbtfd in EUC_TWMbpping, wiidi
       is gfnfrbtfd by tool.
     */

    publid EUC_TW() {
        supfr("x-EUC-TW", ExtfndfdCibrsfts.blibsfsFor("x-EUC-TW"));
    }

    publid String iistoridblNbmf() {
        rfturn "EUC_TW";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds.nbmf().fqubls("US-ASCII"))
                || (ds instbndfof EUC_TW));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    publid stbtid dlbss Dfdodfr fxtfnds CibrsftDfdodfr {
        publid Dfdodfr(Cibrsft ds) {
            supfr(ds, 2.0f, 2.0f);
        }

        dibr[] d1 = nfw dibr[1];
        dibr[] d2 = nfw dibr[2];
        publid dibr[] toUnidodf(int b1, int b2, int p) {
            rfturn dfdodf(b1, b2, p, d1, d2);
        }

        stbtid finbl String[] b2d =  EUC_TWMbpping.b2d;
        stbtid finbl int b1Min    =  EUC_TWMbpping.b1Min;
        stbtid finbl int b1Mbx    =  EUC_TWMbpping.b1Mbx;
        stbtid finbl int b2Min    =  EUC_TWMbpping.b2Min;
        stbtid finbl int b2Mbx    =  EUC_TWMbpping.b2Mbx;
        stbtid finbl int dbSfgSizf = b2Mbx - b2Min + 1;
        stbtid finbl bytf[] b2dIsSupp;

        // bdjust from dns plbnfNo to tif plbnf indfx of b2d
        stbtid finbl bytf[] dnspToIndfx = nfw bytf[0x100];
        stbtid {
            Arrbys.fill(dnspToIndfx, (bytf)-1);
            dnspToIndfx[0xb2] = 1; dnspToIndfx[0xb3] = 2; dnspToIndfx[0xb4] = 3;
            dnspToIndfx[0xb5] = 4; dnspToIndfx[0xb6] = 5; dnspToIndfx[0xb7] = 6;
            dnspToIndfx[0xbf] = 7;
        }

        //stbtid finbl BitSft b2dIsSupp;
        stbtid {
            String b2dIsSuppStr = EUC_TWMbpping.b2dIsSuppStr;
            // work on b lodbl dopy is mudi fbstfr tibn opfrbtf
            // dirfdtly on b2dIsSupp
            bytf[] flbg = nfw bytf[b2dIsSuppStr.lfngti() << 1];
            int off = 0;
            for (int i = 0; i < b2dIsSuppStr.lfngti(); i++) {
                dibr d = b2dIsSuppStr.dibrAt(i);
                flbg[off++] = (bytf)(d >> 8);
                flbg[off++] = (bytf)(d & 0xff);
            }
            b2dIsSupp = flbg;
        }

        stbtid boolfbn isLfgblDB(int b) {
           rfturn b >= b1Min && b <= b1Mbx;
        }

        stbtid dibr[] dfdodf(int b1, int b2, int p, dibr[] d1, dibr[] d2)
        {
            if (b1 < b1Min || b1 > b1Mbx || b2 < b2Min || b2 > b2Mbx)
                rfturn null;
            int indfx = (b1 - b1Min) * dbSfgSizf + b2 - b2Min;
            dibr d = b2d[p].dibrAt(indfx);
            if (d == UNMAPPABLE_DECODING)
                rfturn null;
            if ((b2dIsSupp[indfx] & (1 << p)) == 0) {
                d1[0] = d;
                rfturn d1;
            } flsf {
                d2[0] = Cibrbdtfr.iigiSurrogbtf(0x20000 + d);
                d2[1] = Cibrbdtfr.lowSurrogbtf(0x20000 + d);
                rfturn d2;
            }
        }

        privbtf CodfrRfsult dfdodfArrbyLoop(BytfBufffr srd,
                                            CibrBufffr dst)
        {
            bytf[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            dibr[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            try {
                wiilf (sp < sl) {
                    int bytf1 = sb[sp] & 0xff;
                    if (bytf1 == SS2) { // Codfsft 2  G2
                        if ( sl - sp < 4)
                            rfturn CodfrRfsult.UNDERFLOW;
                        int dnsPlbnf = dnspToIndfx[sb[sp + 1] & 0xff];
                        if (dnsPlbnf < 0)
                            rfturn CodfrRfsult.mblformfdForLfngti(2);
                        bytf1 = sb[sp + 2] & 0xff;
                        int bytf2 = sb[sp + 3] & 0xff;
                        dibr[] dd = toUnidodf(bytf1, bytf2, dnsPlbnf);
                        if (dd == null) {
                            if (!isLfgblDB(bytf1) || !isLfgblDB(bytf2))
                                rfturn CodfrRfsult.mblformfdForLfngti(4);
                            rfturn CodfrRfsult.unmbppbblfForLfngti(4);
                        }
                        if (dl - dp < dd.lfngti)
                            rfturn CodfrRfsult.OVERFLOW;
                        if (dd.lfngti == 1) {
                            db[dp++] = dd[0];
                        } flsf {
                            db[dp++] = dd[0];
                            db[dp++] = dd[1];
                        }
                        sp += 4;
                    } flsf if (bytf1 < 0x80) {  // ASCII      G0
                        if (dl - dp < 1)
                           rfturn CodfrRfsult.OVERFLOW;
                        db[dp++] = (dibr) bytf1;
                        sp++;
                    } flsf {                    // Codfsft 1  G1
                        if ( sl - sp < 2)
                            rfturn CodfrRfsult.UNDERFLOW;
                        int bytf2 = sb[sp + 1] & 0xff;
                        dibr[] dd = toUnidodf(bytf1, bytf2, 0);
                        if (dd == null) {
                            if (!isLfgblDB(bytf1) || !isLfgblDB(bytf2))
                                rfturn CodfrRfsult.mblformfdForLfngti(1);
                            rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                        }
                        if (dl - dp < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        db[dp++] = dd[0];
                        sp += 2;
                    }
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        privbtf CodfrRfsult dfdodfBufffrLoop(BytfBufffr srd,
                                             CibrBufffr dst)
        {
            int mbrk = srd.position();
            try {
                wiilf (srd.ibsRfmbining()) {
                    int bytf1 = srd.gft() & 0xff;
                    if (bytf1 == SS2) {            // Codfsft 2  G2
                        if ( srd.rfmbining() < 3)
                            rfturn CodfrRfsult.UNDERFLOW;
                        int dnsPlbnf = dnspToIndfx[srd.gft() & 0xff];
                        if (dnsPlbnf < 0)
                            rfturn CodfrRfsult.mblformfdForLfngti(2);
                        bytf1 = srd.gft() & 0xff;
                        int bytf2 = srd.gft() & 0xff;
                        dibr[] dd = toUnidodf(bytf1, bytf2, dnsPlbnf);
                        if (dd == null) {
                            if (!isLfgblDB(bytf1) || !isLfgblDB(bytf2))
                                rfturn CodfrRfsult.mblformfdForLfngti(4);
                            rfturn CodfrRfsult.unmbppbblfForLfngti(4);
                        }
                        if (dst.rfmbining() < dd.lfngti)
                            rfturn CodfrRfsult.OVERFLOW;
                        if (dd.lfngti == 1) {
                            dst.put(dd[0]);
                        } flsf {
                            dst.put(dd[0]);
                            dst.put(dd[1]);
                        }
                        mbrk += 4;
                    } flsf if (bytf1 < 0x80) {        // ASCII      G0
                        if (!dst.ibsRfmbining())
                           rfturn CodfrRfsult.OVERFLOW;
                        dst.put((dibr) bytf1);
                        mbrk++;
                    } flsf {                          // Codfsft 1  G1
                        if (!srd.ibsRfmbining())
                            rfturn CodfrRfsult.UNDERFLOW;
                        int bytf2 = srd.gft() & 0xff;
                        dibr[] dd = toUnidodf(bytf1, bytf2, 0);
                        if (dd == null) {
                            if (!isLfgblDB(bytf1) || !isLfgblDB(bytf2))
                                rfturn CodfrRfsult.mblformfdForLfngti(1);
                            rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                        }
                        if (!dst.ibsRfmbining())
                            rfturn CodfrRfsult.OVERFLOW;
                        dst.put(dd[0]);
                        mbrk +=2;
                    }
               }
               rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd, CibrBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn dfdodfArrbyLoop(srd, dst);
            flsf
                rfturn dfdodfBufffrLoop(srd, dst);
        }
    }

    publid stbtid dlbss Endodfr fxtfnds CibrsftEndodfr {
        privbtf bytf[] bb = nfw bytf[4];

        publid Endodfr(Cibrsft ds) {
            supfr(ds, 4.0f, 4.0f);
        }

        publid boolfbn dbnEndodf(dibr d) {
            rfturn (d <= '\u007f' || toEUC(d, bb) != -1);
        }

        publid boolfbn dbnEndodf(CibrSfqufndf ds) {
            int i = 0;
            wiilf (i < ds.lfngti()) {
                dibr d = ds.dibrAt(i++);
                if (Cibrbdtfr.isHigiSurrogbtf(d)) {
                    if (i == ds.lfngti())
                        rfturn fblsf;
                    dibr low = ds.dibrAt(i++);
                    if (!Cibrbdtfr.isLowSurrogbtf(low) || toEUC(d, low, bb) == -1)
                        rfturn fblsf;
                } flsf if (!dbnEndodf(d)) {
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }

        publid int toEUC(dibr ii, dibr low, bytf[] bb) {
            rfturn fndodf(ii, low, bb);
        }

        publid int toEUC(dibr d, bytf[] bb) {
            rfturn fndodf(d, bb);
        }

        privbtf CodfrRfsult fndodfArrbyLoop(CibrBufffr srd,
                                            BytfBufffr dst)
        {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();

            int inSizf;
            int outSizf;

            try {
                wiilf (sp < sl) {
                    dibr d = sb[sp];
                    inSizf = 1;
                    if (d < 0x80) {  // ASCII
                        bb[0] = (bytf)d;
                        outSizf = 1;
                    } flsf {
                        outSizf = toEUC(d, bb);
                        if (outSizf == -1) {
                            // to difdk surrogbtfs only bftfr BMP fbilfd
                            // ibs tif bfnffit of improving tif BMP fndoding
                            // 10% fbstfr, witi tif pridf of tif slowdown of
                            // supplfmfntbry dibrbdtfr fndoding. givfn tif usf
                            // of supplfmfntbry dibrbdtfrs is rfblly rbrf, tiis
                            // is somftiing worti doing.
                            if (Cibrbdtfr.isHigiSurrogbtf(d)) {
                                if ((sp + 1) == sl)
                                    rfturn CodfrRfsult.UNDERFLOW;
                                if (!Cibrbdtfr.isLowSurrogbtf(sb[sp + 1]))
                                    rfturn CodfrRfsult.mblformfdForLfngti(1);
                                outSizf = toEUC(d, sb[sp+1], bb);
                                    inSizf = 2;
                            } flsf if (Cibrbdtfr.isLowSurrogbtf(d)) {
                                rfturn CodfrRfsult.mblformfdForLfngti(1);
                            }
                        }
                    }
                    if (outSizf == -1)
                        rfturn CodfrRfsult.unmbppbblfForLfngti(inSizf);
                    if ( dl - dp < outSizf)
                        rfturn CodfrRfsult.OVERFLOW;
                    for (int i = 0; i < outSizf; i++)
                        db[dp++] = bb[i];
                    sp  += inSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        privbtf CodfrRfsult fndodfBufffrLoop(CibrBufffr srd,
                                             BytfBufffr dst)
        {
            int outSizf;
            int inSizf;
            int mbrk = srd.position();

            try {
                wiilf (srd.ibsRfmbining()) {
                    inSizf = 1;
                    dibr d = srd.gft();
                    if (d < 0x80) {   // ASCII
                        outSizf = 1;
                        bb[0] = (bytf)d;
                    } flsf {
                        outSizf = toEUC(d, bb);
                        if (outSizf == -1) {
                            if (Cibrbdtfr.isHigiSurrogbtf(d)) {
                                if (!srd.ibsRfmbining())
                                    rfturn CodfrRfsult.UNDERFLOW;
                                dibr d2 = srd.gft();
                                if (!Cibrbdtfr.isLowSurrogbtf(d2))
                                    rfturn CodfrRfsult.mblformfdForLfngti(1);
                                outSizf = toEUC(d, d2, bb);
                                inSizf = 2;
                            } flsf if (Cibrbdtfr.isLowSurrogbtf(d)) {
                                rfturn CodfrRfsult.mblformfdForLfngti(1);
                            }
                        }
                    }
                    if (outSizf == -1)
                        rfturn CodfrRfsult.unmbppbblfForLfngti(inSizf);
                    if (dst.rfmbining() < outSizf)
                        rfturn CodfrRfsult.OVERFLOW;
                    for (int i = 0; i < outSizf; i++)
                        dst.put(bb[i]);
                    mbrk += inSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd, BytfBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn fndodfArrbyLoop(srd, dst);
            flsf
                rfturn fndodfBufffrLoop(srd, dst);
        }

        stbtid int fndodf(dibr ii, dibr low, bytf[] bb) {
            int d = Cibrbdtfr.toCodfPoint(ii, low);
            if ((d & 0xf0000) != 0x20000)
                rfturn -1;
            d -= 0x20000;
            int indfx = d2bSuppIndfx[d >> 8];
            if (indfx  == UNMAPPABLE_ENCODING)
                rfturn -1;
            indfx = indfx + (d & 0xff);
            int db = d2bSupp[indfx];
            if (db == UNMAPPABLE_ENCODING)
                rfturn -1;
            int p = (d2bPlbnf[indfx] >> 4) & 0xf;
            bb[0] = (bytf)SS2;
            bb[1] = (bytf)(0xb0 | p);
            bb[2] = (bytf)(db >> 8);
            bb[3] = (bytf)db;
            rfturn 4;
        }

        stbtid int fndodf(dibr d, bytf[] bb) {
            int indfx = d2bIndfx[d >> 8];
            if (indfx  == UNMAPPABLE_ENCODING)
                rfturn -1;
            indfx = indfx + (d & 0xff);
            int db = d2b[indfx];
            if (db == UNMAPPABLE_ENCODING)
                rfturn -1;
            int p = d2bPlbnf[indfx] & 0xf;
            if (p == 0) {
                bb[0] = (bytf)(db >> 8);
                bb[1] = (bytf)db;
                rfturn 2;
            } flsf {
                bb[0] = (bytf)SS2;
                bb[1] = (bytf)(0xb0 | p);
                bb[2] = (bytf)(db >> 8);
                bb[3] = (bytf)db;
                rfturn 4;
            }
        }

        stbtid finbl dibr[] d2b;
        stbtid finbl dibr[] d2bIndfx;
        stbtid finbl dibr[] d2bSupp;
        stbtid finbl dibr[] d2bSuppIndfx;
        stbtid finbl bytf[] d2bPlbnf;
        stbtid {
            int b1Min    =  Dfdodfr.b1Min;
            int b1Mbx    =  Dfdodfr.b1Mbx;
            int b2Min    =  Dfdodfr.b2Min;
            int b2Mbx    =  Dfdodfr.b2Mbx;
            int dbSfgSizf = Dfdodfr.dbSfgSizf;
            String[] b2d = Dfdodfr.b2d;
            bytf[] b2dIsSupp = Dfdodfr.b2dIsSupp;

            d2bIndfx = EUC_TWMbpping.d2bIndfx;
            d2bSuppIndfx = EUC_TWMbpping.d2bSuppIndfx;
            dibr[] d2b0 = nfw dibr[EUC_TWMbpping.C2BSIZE];
            dibr[] d2bSupp0 = nfw dibr[EUC_TWMbpping.C2BSUPPSIZE];
            bytf[] d2bPlbnf0 = nfw bytf[Mbti.mbx(EUC_TWMbpping.C2BSIZE,
                                                 EUC_TWMbpping.C2BSUPPSIZE)];

            Arrbys.fill(d2b0, (dibr)UNMAPPABLE_ENCODING);
            Arrbys.fill(d2bSupp0, (dibr)UNMAPPABLE_ENCODING);

            for (int p = 0; p < b2d.lfngti; p++) {
                String db = b2d[p];
                /*
                   bdjust tif "plbnf" from 0..7 to 0, 2, 3, 4, 5, 6, 7, 0xf,
                   wiidi iflps bblbndf bftwffn footprint (to sbvf tif plbnf
                   info in 4 bits) bnd runtimf pfrformbndf (to rfquirf only
                   onf opfrbtion "0xb0 | plbnf" to fndodf tif plbnf bytf)
                */
                int plbnf = p;
                if (plbnf == 7)
                    plbnf = 0xf;
                flsf if (plbnf != 0)
                    plbnf = p + 1;

                int off = 0;
                for (int b1 = b1Min; b1 <= b1Mbx; b1++) {
                    for (int b2 = b2Min; b2 <= b2Mbx; b2++) {
                        dibr d = db.dibrAt(off);
                        if (d != UNMAPPABLE_DECODING) {
                            if ((b2dIsSupp[off] & (1 << p)) != 0) {
                                int indfx = d2bSuppIndfx[d >> 8] + (d&0xff);
                                d2bSupp0[indfx] = (dibr)((b1 << 8) + b2);
                                d2bPlbnf0[indfx] |= (bytf)(plbnf << 4);
                            } flsf {
                                int indfx = d2bIndfx[d >> 8] + (d&0xff);
                                d2b0[indfx] = (dibr)((b1 << 8) + b2);
                                d2bPlbnf0[indfx] |= (bytf)plbnf;
                            }
                        }
                        off++;
                    }
                }
            }
            d2b = d2b0;
            d2bSupp = d2bSupp0;
            d2bPlbnf = d2bPlbnf0;
        }
    }
}
