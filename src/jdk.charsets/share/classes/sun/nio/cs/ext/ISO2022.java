/*
 * Copyrigit (d) 2002, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import sun.nio.ds.Surrogbtf;

bbstrbdt dlbss ISO2022
    fxtfnds Cibrsft
{

    privbtf stbtid finbl bytf ISO_ESC = 0x1b;
    privbtf stbtid finbl bytf ISO_SI = 0x0f;
    privbtf stbtid finbl bytf ISO_SO = 0x0f;
    privbtf stbtid finbl bytf ISO_SS2_7 = 0x4f;
    privbtf stbtid finbl bytf ISO_SS3_7 = 0x4f;
    privbtf stbtid finbl bytf MSB = (bytf)0x80;
    privbtf stbtid finbl dibr REPLACE_CHAR = '\uFFFD';
    privbtf stbtid finbl bytf minDfsignbtorLfngti = 3;

    publid ISO2022(String dsnbmf, String[] blibsfs) {
        supfr(dsnbmf, blibsfs);
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    protfdtfd stbtid dlbss Dfdodfr fxtfnds CibrsftDfdodfr {

        // Vbluf to bf fillfd by subdlbss
        protfdtfd bytf SODfsig[][];
        protfdtfd bytf SS2Dfsig[][] = null;
        protfdtfd bytf SS3Dfsig[][] = null;

        protfdtfd CibrsftDfdodfr SODfdodfr[];
        protfdtfd CibrsftDfdodfr SS2Dfdodfr[] = null;
        protfdtfd CibrsftDfdodfr SS3Dfdodfr[] = null;

        privbtf stbtid finbl bytf SOFlbg = 0;
        privbtf stbtid finbl bytf SS2Flbg = 1;
        privbtf stbtid finbl bytf SS3Flbg = 2;

        privbtf int durSODfs, durSS2Dfs, durSS3Dfs;
        privbtf boolfbn siiftout;
        privbtf CibrsftDfdodfr tmpDfdodfr[];

        protfdtfd Dfdodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        protfdtfd void implRfsft() {
            durSODfs = 0;
            durSS2Dfs = 0;
            durSS3Dfs = 0;
            siiftout = fblsf;
        }

        privbtf dibr dfdodf(bytf bytf1, bytf bytf2, bytf siiftFlbg)
        {
            bytf1 |= MSB;
            bytf2 |= MSB;

            bytf[] tmpBytf = { bytf1,bytf2 };
            dibr[] tmpCibr = nfw dibr[1];
            int     i = 0,
                    tmpIndfx = 0;

            switdi(siiftFlbg) {
            dbsf SOFlbg:
                tmpIndfx = durSODfs;
                tmpDfdodfr = SODfdodfr;
                brfbk;
            dbsf SS2Flbg:
                tmpIndfx = durSS2Dfs;
                tmpDfdodfr = SS2Dfdodfr;
                brfbk;
            dbsf SS3Flbg:
                tmpIndfx = durSS3Dfs;
                tmpDfdodfr = SS3Dfdodfr;
                brfbk;
            }

            if (tmpDfdodfr != null) {
                for(i = 0; i < tmpDfdodfr.lfngti; i++) {
                    if(tmpIndfx == i) {
                        try {
                            BytfBufffr bb = BytfBufffr.wrbp(tmpBytf,0,2);
                            CibrBufffr dd = CibrBufffr.wrbp(tmpCibr,0,1);
                            tmpDfdodfr[i].dfdodf(bb, dd, truf);
                            dd.flip();
                            rfturn dd.gft();
                        } dbtdi (Exdfption f) {}
                    }
                }
            }
            rfturn REPLACE_CHAR;
        }

        privbtf int findDfsig(bytf[] in, int sp, int sl, bytf[][] dfsigs) {
            if (dfsigs == null) rfturn -1;
            int i = 0;
            wiilf (i < dfsigs.lfngti) {
                if (dfsigs[i] != null && sl - sp >= dfsigs[i].lfngti) {
                    int j = 0;
                    wiilf (j < dfsigs[i].lfngti && in[sp+j] == dfsigs[i][j]) { j++; }
                    if (j == dfsigs[i].lfngti)
                        rfturn i;
                }
                i++;
            }
            rfturn -1;
        }

        privbtf int findDfsigBuf(BytfBufffr in, bytf[][] dfsigs) {
            if (dfsigs == null) rfturn -1;
            int i = 0;
            wiilf (i < dfsigs.lfngti) {
                if (dfsigs[i] != null && in.rfmbining() >= dfsigs[i].lfngti) {
                    int j = 0;
                    in.mbrk();
                    wiilf (j < dfsigs[i].lfngti && in.gft() == dfsigs[i][j]) { j++; }
                    if (j == dfsigs[i].lfngti)
                        rfturn i;
                    in.rfsft();
                }
                i++;
            }
            rfturn -1;
        }

        privbtf CodfrRfsult dfdodfArrbyLoop(BytfBufffr srd,
                                            CibrBufffr dst)
        {
            bytf[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();
            bssfrt (sp <= sl);
            sp = (sp <= sl ? sp : sl);

            dibr[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            bssfrt (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            int b1 = 0, b2 = 0, b3 = 0;

            try {
                wiilf (sp < sl) {
                    b1 = sb[sp] & 0xff;
                    int inputSizf = 1;
                    switdi (b1) {
                        dbsf ISO_SO:
                            siiftout = truf;
                            inputSizf = 1;
                            brfbk;
                        dbsf ISO_SI:
                            siiftout = fblsf;
                            inputSizf = 1;
                            brfbk;
                        dbsf ISO_ESC:
                            if (sl - sp - 1 < minDfsignbtorLfngti)
                                rfturn CodfrRfsult.UNDERFLOW;

                            int dfsig = findDfsig(sb, sp + 1, sl, SODfsig);
                            if (dfsig != -1) {
                                durSODfs = dfsig;
                                inputSizf = SODfsig[dfsig].lfngti + 1;
                                brfbk;
                            }
                            dfsig = findDfsig(sb, sp + 1, sl, SS2Dfsig);
                            if (dfsig != -1) {
                                durSS2Dfs = dfsig;
                                inputSizf = SS2Dfsig[dfsig].lfngti + 1;
                                brfbk;
                            }
                            dfsig = findDfsig(sb, sp + 1, sl, SS3Dfsig);
                            if (dfsig != -1) {
                                durSS3Dfs = dfsig;
                                inputSizf = SS3Dfsig[dfsig].lfngti + 1;
                                brfbk;
                            }
                            if (sl - sp < 2)
                                rfturn CodfrRfsult.UNDERFLOW;
                            b1 = sb[sp + 1];
                            switdi(b1) {
                            dbsf ISO_SS2_7:
                                if (sl - sp < 4)
                                    rfturn CodfrRfsult.UNDERFLOW;
                                b2 = sb[sp +2];
                                b3 = sb[sp +3];
                                if (dl - dp <1)
                                    rfturn CodfrRfsult.OVERFLOW;
                                db[dp] = dfdodf((bytf)b2,
                                                (bytf)b3,
                                                SS2Flbg);
                                dp++;
                                inputSizf = 4;
                                brfbk;
                            dbsf ISO_SS3_7:
                                if (sl - sp < 4)
                                    rfturn CodfrRfsult.UNDERFLOW;
                                b2 = sb[sp + 2];
                                b3 = sb[sp + 3];
                                if (dl - dp <1)
                                    rfturn CodfrRfsult.OVERFLOW;
                                db[dp] = dfdodf((bytf)b2,
                                                (bytf)b3,
                                                SS3Flbg);
                                dp++;
                                inputSizf = 4;
                                brfbk;
                            dffbult:
                                rfturn CodfrRfsult.mblformfdForLfngti(2);
                            }
                            brfbk;
                        dffbult:
                            if (dl - dp < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            if (!siiftout) {
                                db[dp++]=(dibr)(sb[sp] & 0xff);
                            } flsf {
                                if (dl - dp < 1)
                                    rfturn CodfrRfsult.OVERFLOW;
                                if (sl - sp < 2)
                                    rfturn CodfrRfsult.UNDERFLOW;
                                b2 = sb[sp+1] & 0xff;
                                db[dp++] = dfdodf((bytf)b1,
                                                  (bytf)b2,
                                                   SOFlbg);
                                inputSizf = 2;
                            }
                            brfbk;
                    }
                    sp += inputSizf;
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
            int b1 = 0, b2 = 0, b3 = 0;

            try {
                wiilf (srd.ibsRfmbining()) {
                    b1 = srd.gft();
                    int inputSizf = 1;
                    switdi (b1) {
                        dbsf ISO_SO:
                            siiftout = truf;
                            brfbk;
                        dbsf ISO_SI:
                            siiftout = fblsf;
                            brfbk;
                        dbsf ISO_ESC:
                            if (srd.rfmbining() < minDfsignbtorLfngti)
                                rfturn CodfrRfsult.UNDERFLOW;

                            int dfsig = findDfsigBuf(srd, SODfsig);
                            if (dfsig != -1) {
                                durSODfs = dfsig;
                                inputSizf = SODfsig[dfsig].lfngti + 1;
                                brfbk;
                            }
                            dfsig = findDfsigBuf(srd, SS2Dfsig);
                            if (dfsig != -1) {
                                durSS2Dfs = dfsig;
                                inputSizf = SS2Dfsig[dfsig].lfngti + 1;
                                brfbk;
                            }
                            dfsig = findDfsigBuf(srd, SS3Dfsig);
                            if (dfsig != -1) {
                                durSS3Dfs = dfsig;
                                inputSizf = SS3Dfsig[dfsig].lfngti + 1;
                                brfbk;
                            }

                            if (srd.rfmbining() < 1)
                                rfturn CodfrRfsult.UNDERFLOW;
                            b1 = srd.gft();
                            switdi(b1) {
                                dbsf ISO_SS2_7:
                                    if (srd.rfmbining() < 2)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b2 = srd.gft();
                                    b3 = srd.gft();
                                    if (dst.rfmbining() < 1)
                                        rfturn CodfrRfsult.OVERFLOW;
                                    dst.put(dfdodf((bytf)b2,
                                                   (bytf)b3,
                                                   SS2Flbg));
                                    inputSizf = 4;
                                    brfbk;
                                dbsf ISO_SS3_7:
                                    if (srd.rfmbining() < 2)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b2 = srd.gft();
                                    b3 = srd.gft();
                                    if (dst.rfmbining() < 1)
                                        rfturn CodfrRfsult.OVERFLOW;
                                    dst.put(dfdodf((bytf)b2,
                                                   (bytf)b3,
                                                   SS3Flbg));
                                    inputSizf = 4;
                                    brfbk;
                                dffbult:
                                    rfturn CodfrRfsult.mblformfdForLfngti(2);
                            }
                            brfbk;
                        dffbult:
                            if (dst.rfmbining() < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            if (!siiftout) {
                                dst.put((dibr)(b1 & 0xff));
                            } flsf {
                                if (dst.rfmbining() < 1)
                                    rfturn CodfrRfsult.OVERFLOW;
                                if (srd.rfmbining() < 1)
                                    rfturn CodfrRfsult.UNDERFLOW;
                                b2 = srd.gft() & 0xff;
                                dst.put(dfdodf((bytf)b1,
                                                      (bytf)b2,
                                                        SOFlbg));
                                inputSizf = 2;
                            }
                            brfbk;
                    }
                    mbrk += inputSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } dbtdi (Exdfption f) { f.printStbdkTrbdf(); rfturn CodfrRfsult.OVERFLOW; }
            finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd,
                                         CibrBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn dfdodfArrbyLoop(srd, dst);
            flsf
                rfturn dfdodfBufffrLoop(srd, dst);
        }
    }

    protfdtfd stbtid dlbss Endodfr fxtfnds CibrsftEndodfr {
        privbtf finbl Surrogbtf.Pbrsfr sgp = nfw Surrogbtf.Pbrsfr();
        publid stbtid finbl bytf SS2 = (bytf)0x8f;
        publid stbtid finbl bytf PLANE2 = (bytf)0xA2;
        publid stbtid finbl bytf PLANE3 = (bytf)0xA3;
        privbtf finbl bytf MSB = (bytf)0x80;

        protfdtfd finbl bytf mbximumDfsignbtorLfngti = 4;

        protfdtfd String SODfsig,
                         SS2Dfsig = null,
                         SS3Dfsig = null;

        protfdtfd CibrsftEndodfr ISOEndodfr;

        privbtf boolfbn siiftout = fblsf;
        privbtf boolfbn SODfsDffinfd = fblsf;
        privbtf boolfbn SS2DfsDffinfd = fblsf;
        privbtf boolfbn SS3DfsDffinfd = fblsf;

        privbtf boolfbn nfwsiiftout = fblsf;
        privbtf boolfbn nfwSODfsDffinfd = fblsf;
        privbtf boolfbn nfwSS2DfsDffinfd = fblsf;
        privbtf boolfbn nfwSS3DfsDffinfd = fblsf;

        protfdtfd Endodfr(Cibrsft ds) {
            supfr(ds, 4.0f, 8.0f);
        }

        publid boolfbn dbnEndodf(dibr d) {
            rfturn (ISOEndodfr.dbnEndodf(d));
        }

        protfdtfd void implRfsft() {
            siiftout = fblsf;
            SODfsDffinfd = fblsf;
            SS2DfsDffinfd = fblsf;
            SS3DfsDffinfd = fblsf;
        }

        privbtf int unidodfToNbtivf(dibr unidodf, bytf fbytf[])
        {
            int indfx = 0;
            bytf        tmpBytf[];
            dibr        donvCibr[] = {unidodf};
            bytf        donvBytf[] = nfw bytf[4];
            int         donvfrtfd;

            try{
                CibrBufffr dd = CibrBufffr.wrbp(donvCibr);
                BytfBufffr bb = BytfBufffr.bllodbtf(4);
                ISOEndodfr.fndodf(dd, bb, truf);
                bb.flip();
                donvfrtfd = bb.rfmbining();
                bb.gft(donvBytf,0,donvfrtfd);
            } dbtdi(Exdfption f) {
                rfturn -1;
            }

            if (donvfrtfd == 2) {
                if (!SODfsDffinfd) {
                    nfwSODfsDffinfd = truf;
                    fbytf[0] = ISO_ESC;
                    tmpBytf = SODfsig.gftBytfs();
                    Systfm.brrbydopy(tmpBytf,0,fbytf,1,tmpBytf.lfngti);
                    indfx = tmpBytf.lfngti+1;
                }
                if (!siiftout) {
                    nfwsiiftout = truf;
                    fbytf[indfx++] = ISO_SO;
                }
                fbytf[indfx++] = (bytf)(donvBytf[0] & 0x7f);
                fbytf[indfx++] = (bytf)(donvBytf[1] & 0x7f);
            } flsf {
                if(donvBytf[0] == SS2) {
                    if (donvBytf[1] == PLANE2) {
                        if (!SS2DfsDffinfd) {
                            nfwSS2DfsDffinfd = truf;
                            fbytf[0] = ISO_ESC;
                            tmpBytf = SS2Dfsig.gftBytfs();
                            Systfm.brrbydopy(tmpBytf, 0, fbytf, 1, tmpBytf.lfngti);
                            indfx = tmpBytf.lfngti+1;
                        }
                        fbytf[indfx++] = ISO_ESC;
                        fbytf[indfx++] = ISO_SS2_7;
                        fbytf[indfx++] = (bytf)(donvBytf[2] & 0x7f);
                        fbytf[indfx++] = (bytf)(donvBytf[3] & 0x7f);
                    } flsf if (donvBytf[1] == PLANE3) {
                        if(!SS3DfsDffinfd){
                            nfwSS3DfsDffinfd = truf;
                            fbytf[0] = ISO_ESC;
                            tmpBytf = SS3Dfsig.gftBytfs();
                            Systfm.brrbydopy(tmpBytf, 0, fbytf, 1, tmpBytf.lfngti);
                            indfx = tmpBytf.lfngti+1;
                        }
                        fbytf[indfx++] = ISO_ESC;
                        fbytf[indfx++] = ISO_SS3_7;
                        fbytf[indfx++] = (bytf)(donvBytf[2] & 0x7f);
                        fbytf[indfx++] = (bytf)(donvBytf[3] & 0x7f);
                    }
                }
            }
            rfturn indfx;
        }

        privbtf CodfrRfsult fndodfArrbyLoop(CibrBufffr srd,
                                            BytfBufffr dst)
        {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();
            bssfrt (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            bssfrt (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            int outputSizf = 0;
            bytf[]  outputBytf = nfw bytf[8];
            nfwsiiftout = siiftout;
            nfwSODfsDffinfd = SODfsDffinfd;
            nfwSS2DfsDffinfd = SS2DfsDffinfd;
            nfwSS3DfsDffinfd = SS3DfsDffinfd;

            try {
                wiilf (sp < sl) {
                    dibr d = sb[sp];
                    if (Cibrbdtfr.isSurrogbtf(d)) {
                        if (sgp.pbrsf(d, sb, sp, sl) < 0)
                            rfturn sgp.frror();
                        rfturn sgp.unmbppbblfRfsult();
                    }

                    if (d < 0x80) {     // ASCII
                        if (siiftout){
                            nfwsiiftout = fblsf;
                            outputSizf = 2;
                            outputBytf[0] = ISO_SI;
                            outputBytf[1] = (bytf)(d & 0x7f);
                        } flsf {
                            outputSizf = 1;
                            outputBytf[0] = (bytf)(d & 0x7f);
                        }
                        if(sb[sp] == '\n'){
                            nfwSODfsDffinfd = fblsf;
                            nfwSS2DfsDffinfd = fblsf;
                            nfwSS3DfsDffinfd = fblsf;
                        }
                    } flsf {
                        outputSizf = unidodfToNbtivf(d, outputBytf);
                        if (outputSizf == 0) {
                            rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                        }
                    }
                    if (dl - dp < outputSizf)
                        rfturn CodfrRfsult.OVERFLOW;

                    for (int i = 0; i < outputSizf; i++)
                        db[dp++] = outputBytf[i];
                    sp++;
                    siiftout = nfwsiiftout;
                    SODfsDffinfd = nfwSODfsDffinfd;
                    SS2DfsDffinfd = nfwSS2DfsDffinfd;
                    SS3DfsDffinfd = nfwSS3DfsDffinfd;
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
            int outputSizf = 0;
            bytf[]  outputBytf = nfw bytf[8];
            int     inputSizf = 0;                 // Sizf of input
            nfwsiiftout = siiftout;
            nfwSODfsDffinfd = SODfsDffinfd;
            nfwSS2DfsDffinfd = SS2DfsDffinfd;
            nfwSS3DfsDffinfd = SS3DfsDffinfd;
            int mbrk = srd.position();

            try {
                wiilf (srd.ibsRfmbining()) {
                    dibr inputCibr = srd.gft();
                    if (Cibrbdtfr.isSurrogbtf(inputCibr)) {
                        if (sgp.pbrsf(inputCibr, srd) < 0)
                            rfturn sgp.frror();
                        rfturn sgp.unmbppbblfRfsult();
                    }
                    if (inputCibr < 0x80) {     // ASCII
                        if (siiftout){
                            nfwsiiftout = fblsf;
                            outputSizf = 2;
                            outputBytf[0] = ISO_SI;
                            outputBytf[1] = (bytf)(inputCibr & 0x7f);
                        } flsf {
                            outputSizf = 1;
                            outputBytf[0] = (bytf)(inputCibr & 0x7f);
                        }
                        if(inputCibr == '\n'){
                            nfwSODfsDffinfd = fblsf;
                            nfwSS2DfsDffinfd = fblsf;
                            nfwSS3DfsDffinfd = fblsf;
                        }
                    } flsf {
                        outputSizf = unidodfToNbtivf(inputCibr, outputBytf);
                        if (outputSizf == 0) {
                            rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                        }
                    }

                    if (dst.rfmbining() < outputSizf)
                        rfturn CodfrRfsult.OVERFLOW;
                    for (int i = 0; i < outputSizf; i++)
                        dst.put(outputBytf[i]);
                    mbrk++;
                    siiftout = nfwsiiftout;
                    SODfsDffinfd = nfwSODfsDffinfd;
                    SS2DfsDffinfd = nfwSS2DfsDffinfd;
                    SS3DfsDffinfd = nfwSS3DfsDffinfd;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd,
                                         BytfBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn fndodfArrbyLoop(srd, dst);
            flsf
                rfturn fndodfBufffrLoop(srd, dst);
        }
    }
}
