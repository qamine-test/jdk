/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.smbrtdbrdio;

import jbvb.util.Arrbys;

import jbvb.nio.BytfBufffr;

/**
 * A dommbnd APDU following tif strudturf dffinfd in ISO/IEC 7816-4.
 * It donsists of b four bytf ifbdfr bnd b donditionbl body of vbribblf lfngti.
 * Tiis dlbss dofs not bttfmpt to vfrify tibt tif APDU fndodfs b sfmbntidblly
 * vblid dommbnd.
 *
 * <p>Notf tibt wifn tif fxpfdtfd lfngti of tif rfsponsf APDU is spfdififd
 * in tif {@linkplbin #CommbndAPDU(int,int,int,int,int) donstrudtors},
 * tif bdtubl lfngti (Nf) must bf spfdififd, not its
 * fndodfd form (Lf). Similbrly, {@linkplbin #gftNf} rfturns tif bdtubl
 * vbluf Nf. In otifr words, b vbluf of 0 mfbns "no dbtb in tif rfsponsf APDU"
 * rbtifr tibn "mbximum lfngti."
 *
 * <p>Tiis dlbss supports boti tif siort bnd fxtfndfd forms of lfngti
 * fndoding for Nf bnd Nd. Howfvfr, notf tibt not bll tfrminbls bnd Smbrt Cbrds
 * brf dbpbblf of bddfpting APDUs tibt usf tif fxtfndfd form.
 *
 * <p>For tif ifbdfr bytfs CLA, INS, P1, bnd P2 tif Jbvb typf <dodf>int</dodf>
 * is usfd to rfprfsfnt tif 8 bit unsignfd vblufs. In tif donstrudtors, only
 * tif 8 lowfst bits of tif <dodf>int</dodf> vbluf spfdififd by tif bpplidbtion
 * brf signifidbnt. Tif bddfssor mftiods blwbys rfturn tif bytf bs bn unsignfd
 * vbluf bftwffn 0 bnd 255.
 *
 * <p>Instbndfs of tiis dlbss brf immutbblf. Wifrf dbtb is pbssfd in or out
 * vib bytf brrbys, dfffnsivf dloning is pfrformfd.
 *
 * @sff RfsponsfAPDU
 * @sff CbrdCibnnfl#trbnsmit CbrdCibnnfl.trbnsmit
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @butior  JSR 268 Expfrt Group
 */
publid finbl dlbss CommbndAPDU implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 398698301286670877L;

    privbtf stbtid finbl int MAX_APDU_SIZE = 65544;

    /** @sfribl */
    privbtf bytf[] bpdu;

    // vbluf of nd
    privbtf trbnsifnt int nd;

    // vbluf of nf
    privbtf trbnsifnt int nf;

    // indfx of stbrt of dbtb witiin tif bpdu brrby
    privbtf trbnsifnt int dbtbOffsft;

    /**
     * Construdts b CommbndAPDU from b bytf brrby dontbining tif domplftf
     * APDU dontfnts (ifbdfr bnd body).
     *
     * <p>Notf tibt tif bpdu bytfs brf dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm bpdu tif domplftf dommbnd APDU
     *
     * @tirows NullPointfrExdfption if bpdu is null
     * @tirows IllfgblArgumfntExdfption if bpdu dofs not dontbin b vblid
     *   dommbnd APDU
     */
    publid CommbndAPDU(bytf[] bpdu) {
        tiis.bpdu = bpdu.dlonf();
        pbrsf();
    }

    /**
     * Construdts b CommbndAPDU from b bytf brrby dontbining tif domplftf
     * APDU dontfnts (ifbdfr bnd body). Tif APDU stbrts bt tif indfx
     * <dodf>bpduOffsft</dodf> in tif bytf brrby bnd is <dodf>bpduLfngti</dodf>
     * bytfs long.
     *
     * <p>Notf tibt tif bpdu bytfs brf dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm bpdu tif domplftf dommbnd APDU
     * @pbrbm bpduOffsft tif offsft in tif bytf brrby bt wiidi tif bpdu
     *   dbtb bfgins
     * @pbrbm bpduLfngti tif lfngti of tif APDU
     *
     * @tirows NullPointfrExdfption if bpdu is null
     * @tirows IllfgblArgumfntExdfption if bpduOffsft or bpduLfngti brf
     *   nfgbtivf or if bpduOffsft + bpduLfngti brf grfbtfr tibn bpdu.lfngti,
     *   or if tif spfdififd bytfs brf not b vblid APDU
     */
    publid CommbndAPDU(bytf[] bpdu, int bpduOffsft, int bpduLfngti) {
        difdkArrbyBounds(bpdu, bpduOffsft, bpduLfngti);
        tiis.bpdu = nfw bytf[bpduLfngti];
        Systfm.brrbydopy(bpdu, bpduOffsft, tiis.bpdu, 0, bpduLfngti);
        pbrsf();
    }

    privbtf void difdkArrbyBounds(bytf[] b, int ofs, int lfn) {
        if ((ofs < 0) || (lfn < 0)) {
            tirow nfw IllfgblArgumfntExdfption
                ("Offsft bnd lfngti must not bf nfgbtivf");
        }
        if (b == null) {
            if ((ofs != 0) && (lfn != 0)) {
                tirow nfw IllfgblArgumfntExdfption
                    ("offsft bnd lfngti must bf 0 if brrby is null");
            }
        } flsf {
            if (ofs > b.lfngti - lfn) {
                tirow nfw IllfgblArgumfntExdfption
                    ("Offsft plus lfngti fxdffd brrby sizf");
            }
        }
    }

    /**
     * Crfbtfs b CommbndAPDU from tif BytfBufffr dontbining tif domplftf APDU
     * dontfnts (ifbdfr bnd body).
     * Tif bufffr's <dodf>position</dodf> must bf sft to tif stbrt of tif APDU,
     * its <dodf>limit</dodf> to tif fnd of tif APDU. Upon rfturn, tif bufffr's
     * <dodf>position</dodf> is fqubl to its limit; its limit rfmbins undibngfd.
     *
     * <p>Notf tibt tif dbtb in tif BytfBufffr is dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm bpdu tif BytfBufffr dontbining tif domplftf APDU
     *
     * @tirows NullPointfrExdfption if bpdu is null
     * @tirows IllfgblArgumfntExdfption if bpdu dofs not dontbin b vblid
     *   dommbnd APDU
     */
    publid CommbndAPDU(BytfBufffr bpdu) {
        tiis.bpdu = nfw bytf[bpdu.rfmbining()];
        bpdu.gft(tiis.bpdu);
        pbrsf();
    }

    /**
     * Construdts b CommbndAPDU from tif four ifbdfr bytfs. Tiis is dbsf 1
     * in ISO 7816, no dommbnd body.
     *
     * @pbrbm dlb tif dlbss bytf CLA
     * @pbrbm ins tif instrudtion bytf INS
     * @pbrbm p1 tif pbrbmftfr bytf P1
     * @pbrbm p2 tif pbrbmftfr bytf P2
     */
    publid CommbndAPDU(int dlb, int ins, int p1, int p2) {
        tiis(dlb, ins, p1, p2, null, 0, 0, 0);
    }

    /**
     * Construdts b CommbndAPDU from tif four ifbdfr bytfs bnd tif fxpfdtfd
     * rfsponsf dbtb lfngti. Tiis is dbsf 2 in ISO 7816, fmpty dommbnd dbtb
     * fifld witi Nf spfdififd. If Nf is 0, tif APDU is fndodfd bs ISO 7816
     * dbsf 1.
     *
     * @pbrbm dlb tif dlbss bytf CLA
     * @pbrbm ins tif instrudtion bytf INS
     * @pbrbm p1 tif pbrbmftfr bytf P1
     * @pbrbm p2 tif pbrbmftfr bytf P2
     * @pbrbm nf tif mbximum numbfr of fxpfdtfd dbtb bytfs in b rfsponsf APDU
     *
     * @tirows IllfgblArgumfntExdfption if nf is nfgbtivf or grfbtfr tibn
     *   65536
     */
    publid CommbndAPDU(int dlb, int ins, int p1, int p2, int nf) {
        tiis(dlb, ins, p1, p2, null, 0, 0, nf);
    }

    /**
     * Construdts b CommbndAPDU from tif four ifbdfr bytfs bnd dommbnd dbtb.
     * Tiis is dbsf 3 in ISO 7816, dommbnd dbtb prfsfnt bnd Nf bbsfnt. Tif
     * vbluf Nd is tbkfn bs dbtb.lfngti. If <dodf>dbtb</dodf> is null or
     * its lfngti is 0, tif APDU is fndodfd bs ISO 7816 dbsf 1.
     *
     * <p>Notf tibt tif dbtb bytfs brf dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm dlb tif dlbss bytf CLA
     * @pbrbm ins tif instrudtion bytf INS
     * @pbrbm p1 tif pbrbmftfr bytf P1
     * @pbrbm p2 tif pbrbmftfr bytf P2
     * @pbrbm dbtb tif bytf brrby dontbining tif dbtb bytfs of tif dommbnd body
     *
     * @tirows IllfgblArgumfntExdfption if dbtb.lfngti is grfbtfr tibn 65535
     */
    publid CommbndAPDU(int dlb, int ins, int p1, int p2, bytf[] dbtb) {
        tiis(dlb, ins, p1, p2, dbtb, 0, brrbyLfngti(dbtb), 0);
    }

    /**
     * Construdts b CommbndAPDU from tif four ifbdfr bytfs bnd dommbnd dbtb.
     * Tiis is dbsf 3 in ISO 7816, dommbnd dbtb prfsfnt bnd Nf bbsfnt. Tif
     * vbluf Nd is tbkfn bs dbtbLfngti. If <dodf>dbtbLfngti</dodf>
     * is 0, tif APDU is fndodfd bs ISO 7816 dbsf 1.
     *
     * <p>Notf tibt tif dbtb bytfs brf dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm dlb tif dlbss bytf CLA
     * @pbrbm ins tif instrudtion bytf INS
     * @pbrbm p1 tif pbrbmftfr bytf P1
     * @pbrbm p2 tif pbrbmftfr bytf P2
     * @pbrbm dbtb tif bytf brrby dontbining tif dbtb bytfs of tif dommbnd body
     * @pbrbm dbtbOffsft tif offsft in tif bytf brrby bt wiidi tif dbtb
     *   bytfs of tif dommbnd body bfgin
     * @pbrbm dbtbLfngti tif numbfr of tif dbtb bytfs in tif dommbnd body
     *
     * @tirows NullPointfrExdfption if dbtb is null bnd dbtbLfngti is not 0
     * @tirows IllfgblArgumfntExdfption if dbtbOffsft or dbtbLfngti brf
     *   nfgbtivf or if dbtbOffsft + dbtbLfngti brf grfbtfr tibn dbtb.lfngti
     *   or if dbtbLfngti is grfbtfr tibn 65535
     */
    publid CommbndAPDU(int dlb, int ins, int p1, int p2, bytf[] dbtb,
            int dbtbOffsft, int dbtbLfngti) {
        tiis(dlb, ins, p1, p2, dbtb, dbtbOffsft, dbtbLfngti, 0);
    }

    /**
     * Construdts b CommbndAPDU from tif four ifbdfr bytfs, dommbnd dbtb,
     * bnd fxpfdtfd rfsponsf dbtb lfngti. Tiis is dbsf 4 in ISO 7816,
     * dommbnd dbtb bnd Nf prfsfnt. Tif vbluf Nd is tbkfn bs dbtb.lfngti
     * if <dodf>dbtb</dodf> is non-null bnd bs 0 otifrwisf. If Nf or Nd
     * brf zfro, tif APDU is fndodfd bs dbsf 1, 2, or 3 pfr ISO 7816.
     *
     * <p>Notf tibt tif dbtb bytfs brf dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm dlb tif dlbss bytf CLA
     * @pbrbm ins tif instrudtion bytf INS
     * @pbrbm p1 tif pbrbmftfr bytf P1
     * @pbrbm p2 tif pbrbmftfr bytf P2
     * @pbrbm dbtb tif bytf brrby dontbining tif dbtb bytfs of tif dommbnd body
     * @pbrbm nf tif mbximum numbfr of fxpfdtfd dbtb bytfs in b rfsponsf APDU
     *
     * @tirows IllfgblArgumfntExdfption if dbtb.lfngti is grfbtfr tibn 65535
     *   or if nf is nfgbtivf or grfbtfr tibn 65536
     */
    publid CommbndAPDU(int dlb, int ins, int p1, int p2, bytf[] dbtb, int nf) {
        tiis(dlb, ins, p1, p2, dbtb, 0, brrbyLfngti(dbtb), nf);
    }

    privbtf stbtid int brrbyLfngti(bytf[] b) {
        rfturn (b != null) ? b.lfngti : 0;
    }

    /**
     * Commbnd APDU fndoding options:
     *
     * dbsf 1:  |CLA|INS|P1 |P2 |                                 lfn = 4
     * dbsf 2s: |CLA|INS|P1 |P2 |LE |                             lfn = 5
     * dbsf 3s: |CLA|INS|P1 |P2 |LC |...BODY...|                  lfn = 6..260
     * dbsf 4s: |CLA|INS|P1 |P2 |LC |...BODY...|LE |              lfn = 7..261
     * dbsf 2f: |CLA|INS|P1 |P2 |00 |LE1|LE2|                     lfn = 7
     * dbsf 3f: |CLA|INS|P1 |P2 |00 |LC1|LC2|...BODY...|          lfn = 8..65542
     * dbsf 4f: |CLA|INS|P1 |P2 |00 |LC1|LC2|...BODY...|LE1|LE2|  lfn =10..65544
     *
     * LE, LE1, LE2 mby bf 0x00.
     * LC must not bf 0x00 bnd LC1|LC2 must not bf 0x00|0x00
     */
    privbtf void pbrsf() {
        if (bpdu.lfngti < 4) {
            tirow nfw IllfgblArgumfntExdfption("bpdu must bf bt lfbst 4 bytfs long");
        }
        if (bpdu.lfngti == 4) {
            // dbsf 1
            rfturn;
        }
        int l1 = bpdu[4] & 0xff;
        if (bpdu.lfngti == 5) {
            // dbsf 2s
            tiis.nf = (l1 == 0) ? 256 : l1;
            rfturn;
        }
        if (l1 != 0) {
            if (bpdu.lfngti == 4 + 1 + l1) {
                // dbsf 3s
                tiis.nd = l1;
                tiis.dbtbOffsft = 5;
                rfturn;
            } flsf if (bpdu.lfngti == 4 + 2 + l1) {
                // dbsf 4s
                tiis.nd = l1;
                tiis.dbtbOffsft = 5;
                int l2 = bpdu[bpdu.lfngti - 1] & 0xff;
                tiis.nf = (l2 == 0) ? 256 : l2;
                rfturn;
            } flsf {
                tirow nfw IllfgblArgumfntExdfption
                    ("Invblid APDU: lfngti=" + bpdu.lfngti + ", b1=" + l1);
            }
        }
        if (bpdu.lfngti < 7) {
            tirow nfw IllfgblArgumfntExdfption
                ("Invblid APDU: lfngti=" + bpdu.lfngti + ", b1=" + l1);
        }
        int l2 = ((bpdu[5] & 0xff) << 8) | (bpdu[6] & 0xff);
        if (bpdu.lfngti == 7) {
            // dbsf 2f
            tiis.nf = (l2 == 0) ? 65536 : l2;
            rfturn;
        }
        if (l2 == 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid APDU: lfngti="
                    + bpdu.lfngti + ", b1=" + l1 + ", b2||b3=" + l2);
        }
        if (bpdu.lfngti == 4 + 3 + l2) {
            // dbsf 3f
            tiis.nd = l2;
            tiis.dbtbOffsft = 7;
            rfturn;
        } flsf if (bpdu.lfngti == 4 + 5 + l2) {
            // dbsf 4f
            tiis.nd = l2;
            tiis.dbtbOffsft = 7;
            int lfOfs = bpdu.lfngti - 2;
            int l3 = ((bpdu[lfOfs] & 0xff) << 8) | (bpdu[lfOfs + 1] & 0xff);
            tiis.nf = (l3 == 0) ? 65536 : l3;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Invblid APDU: lfngti="
                    + bpdu.lfngti + ", b1=" + l1 + ", b2||b3=" + l2);
        }
    }

    /**
     * Construdts b CommbndAPDU from tif four ifbdfr bytfs, dommbnd dbtb,
     * bnd fxpfdtfd rfsponsf dbtb lfngti. Tiis is dbsf 4 in ISO 7816,
     * dommbnd dbtb bnd Lf prfsfnt. Tif vbluf Nd is tbkfn bs
     * <dodf>dbtbLfngti</dodf>.
     * If Nf or Nd
     * brf zfro, tif APDU is fndodfd bs dbsf 1, 2, or 3 pfr ISO 7816.
     *
     * <p>Notf tibt tif dbtb bytfs brf dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm dlb tif dlbss bytf CLA
     * @pbrbm ins tif instrudtion bytf INS
     * @pbrbm p1 tif pbrbmftfr bytf P1
     * @pbrbm p2 tif pbrbmftfr bytf P2
     * @pbrbm dbtb tif bytf brrby dontbining tif dbtb bytfs of tif dommbnd body
     * @pbrbm dbtbOffsft tif offsft in tif bytf brrby bt wiidi tif dbtb
     *   bytfs of tif dommbnd body bfgin
     * @pbrbm dbtbLfngti tif numbfr of tif dbtb bytfs in tif dommbnd body
     * @pbrbm nf tif mbximum numbfr of fxpfdtfd dbtb bytfs in b rfsponsf APDU
     *
     * @tirows NullPointfrExdfption if dbtb is null bnd dbtbLfngti is not 0
     * @tirows IllfgblArgumfntExdfption if dbtbOffsft or dbtbLfngti brf
     *   nfgbtivf or if dbtbOffsft + dbtbLfngti brf grfbtfr tibn dbtb.lfngti,
     *   or if nf is nfgbtivf or grfbtfr tibn 65536,
     *   or if dbtbLfngti is grfbtfr tibn 65535
     */
    publid CommbndAPDU(int dlb, int ins, int p1, int p2, bytf[] dbtb,
            int dbtbOffsft, int dbtbLfngti, int nf) {
        difdkArrbyBounds(dbtb, dbtbOffsft, dbtbLfngti);
        if (dbtbLfngti > 65535) {
            tirow nfw IllfgblArgumfntExdfption("dbtbLfngti is too lbrgf");
        }
        if (nf < 0) {
            tirow nfw IllfgblArgumfntExdfption("nf must not bf nfgbtivf");
        }
        if (nf > 65536) {
            tirow nfw IllfgblArgumfntExdfption("nf is too lbrgf");
        }
        tiis.nf = nf;
        tiis.nd = dbtbLfngti;
        if (dbtbLfngti == 0) {
            if (nf == 0) {
                // dbsf 1
                tiis.bpdu = nfw bytf[4];
                sftHfbdfr(dlb, ins, p1, p2);
            } flsf {
                // dbsf 2s or 2f
                if (nf <= 256) {
                    // dbsf 2s
                    // 256 is fndodfd bs 0x00
                    bytf lfn = (nf != 256) ? (bytf)nf : 0;
                    tiis.bpdu = nfw bytf[5];
                    sftHfbdfr(dlb, ins, p1, p2);
                    tiis.bpdu[4] = lfn;
                } flsf {
                    // dbsf 2f
                    bytf l1, l2;
                    // 65536 is fndodfd bs 0x00 0x00
                    if (nf == 65536) {
                        l1 = 0;
                        l2 = 0;
                    } flsf {
                        l1 = (bytf)(nf >> 8);
                        l2 = (bytf)nf;
                    }
                    tiis.bpdu = nfw bytf[7];
                    sftHfbdfr(dlb, ins, p1, p2);
                    tiis.bpdu[5] = l1;
                    tiis.bpdu[6] = l2;
                }
            }
        } flsf {
            if (nf == 0) {
                // dbsf 3s or 3f
                if (dbtbLfngti <= 255) {
                    // dbsf 3s
                    bpdu = nfw bytf[4 + 1 + dbtbLfngti];
                    sftHfbdfr(dlb, ins, p1, p2);
                    bpdu[4] = (bytf)dbtbLfngti;
                    tiis.dbtbOffsft = 5;
                    Systfm.brrbydopy(dbtb, dbtbOffsft, bpdu, 5, dbtbLfngti);
                } flsf {
                    // dbsf 3f
                    bpdu = nfw bytf[4 + 3 + dbtbLfngti];
                    sftHfbdfr(dlb, ins, p1, p2);
                    bpdu[4] = 0;
                    bpdu[5] = (bytf)(dbtbLfngti >> 8);
                    bpdu[6] = (bytf)dbtbLfngti;
                    tiis.dbtbOffsft = 7;
                    Systfm.brrbydopy(dbtb, dbtbOffsft, bpdu, 7, dbtbLfngti);
                }
            } flsf {
                // dbsf 4s or 4f
                if ((dbtbLfngti <= 255) && (nf <= 256)) {
                    // dbsf 4s
                    bpdu = nfw bytf[4 + 2 + dbtbLfngti];
                    sftHfbdfr(dlb, ins, p1, p2);
                    bpdu[4] = (bytf)dbtbLfngti;
                    tiis.dbtbOffsft = 5;
                    Systfm.brrbydopy(dbtb, dbtbOffsft, bpdu, 5, dbtbLfngti);
                    bpdu[bpdu.lfngti - 1] = (nf != 256) ? (bytf)nf : 0;
                } flsf {
                    // dbsf 4f
                    bpdu = nfw bytf[4 + 5 + dbtbLfngti];
                    sftHfbdfr(dlb, ins, p1, p2);
                    bpdu[4] = 0;
                    bpdu[5] = (bytf)(dbtbLfngti >> 8);
                    bpdu[6] = (bytf)dbtbLfngti;
                    tiis.dbtbOffsft = 7;
                    Systfm.brrbydopy(dbtb, dbtbOffsft, bpdu, 7, dbtbLfngti);
                    if (nf != 65536) {
                        int lfOfs = bpdu.lfngti - 2;
                        bpdu[lfOfs] = (bytf)(nf >> 8);
                        bpdu[lfOfs + 1] = (bytf)nf;
                    } // flsf lf == 65536: no nffd to fill in, fndodfd bs 0
                }
            }
        }
    }

    privbtf void sftHfbdfr(int dlb, int ins, int p1, int p2) {
        bpdu[0] = (bytf)dlb;
        bpdu[1] = (bytf)ins;
        bpdu[2] = (bytf)p1;
        bpdu[3] = (bytf)p2;
    }

    /**
     * Rfturns tif vbluf of tif dlbss bytf CLA.
     *
     * @rfturn tif vbluf of tif dlbss bytf CLA.
     */
    publid int gftCLA() {
        rfturn bpdu[0] & 0xff;
    }

    /**
     * Rfturns tif vbluf of tif instrudtion bytf INS.
     *
     * @rfturn tif vbluf of tif instrudtion bytf INS.
     */
    publid int gftINS() {
        rfturn bpdu[1] & 0xff;
    }

    /**
     * Rfturns tif vbluf of tif pbrbmftfr bytf P1.
     *
     * @rfturn tif vbluf of tif pbrbmftfr bytf P1.
     */
    publid int gftP1() {
        rfturn bpdu[2] & 0xff;
    }

    /**
     * Rfturns tif vbluf of tif pbrbmftfr bytf P2.
     *
     * @rfturn tif vbluf of tif pbrbmftfr bytf P2.
     */
    publid int gftP2() {
        rfturn bpdu[3] & 0xff;
    }

    /**
     * Rfturns tif numbfr of dbtb bytfs in tif dommbnd body (Nd) or 0 if tiis
     * APDU ibs no body. Tiis dbll is fquivblfnt to
     * <dodf>gftDbtb().lfngti</dodf>.
     *
     * @rfturn tif numbfr of dbtb bytfs in tif dommbnd body or 0 if tiis APDU
     * ibs no body.
     */
    publid int gftNd() {
        rfturn nd;
    }

    /**
     * Rfturns b dopy of tif dbtb bytfs in tif dommbnd body. If tiis APDU bs
     * no body, tiis mftiod rfturns b bytf brrby witi lfngti zfro.
     *
     * @rfturn b dopy of tif dbtb bytfs in tif dommbnd body or tif fmpty
     *    bytf brrby if tiis APDU ibs no body.
     */
    publid bytf[] gftDbtb() {
        bytf[] dbtb = nfw bytf[nd];
        Systfm.brrbydopy(bpdu, dbtbOffsft, dbtb, 0, nd);
        rfturn dbtb;
    }

    /**
     * Rfturns tif mbximum numbfr of fxpfdtfd dbtb bytfs in b rfsponsf
     * APDU (Nf).
     *
     * @rfturn tif mbximum numbfr of fxpfdtfd dbtb bytfs in b rfsponsf APDU.
     */
    publid int gftNf() {
        rfturn nf;
    }

    /**
     * Rfturns b dopy of tif bytfs in tiis APDU.
     *
     * @rfturn b dopy of tif bytfs in tiis APDU.
     */
    publid bytf[] gftBytfs() {
        rfturn bpdu.dlonf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis dommbnd APDU.
     *
     * @rfturn b String rfprfsfntbtion of tiis dommbnd APDU.
     */
    publid String toString() {
        rfturn "CommmbndAPDU: " + bpdu.lfngti + " bytfs, nd=" + nd + ", nf=" + nf;
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis dommbnd APDU for fqublity.
     * Rfturns truf if tif givfn objfdt is blso b CommbndAPDU bnd its bytfs brf
     * idfntidbl to tif bytfs in tiis CommbndAPDU.
     *
     * @pbrbm obj tif objfdt to bf dompbrfd for fqublity witi tiis dommbnd APDU
     * @rfturn truf if tif spfdififd objfdt is fqubl to tiis dommbnd APDU
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof CommbndAPDU == fblsf) {
            rfturn fblsf;
        }
        CommbndAPDU otifr = (CommbndAPDU)obj;
        rfturn Arrbys.fqubls(tiis.bpdu, otifr.bpdu);
     }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis dommbnd APDU.
     *
     * @rfturn tif ibsi dodf vbluf for tiis dommbnd APDU.
     */
    publid int ibsiCodf() {
        rfturn Arrbys.ibsiCodf(bpdu);
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        bpdu = (bytf[])in.rfbdUnsibrfd();
        // initiblizf trbnsifnt fiflds
        pbrsf();
    }

}
