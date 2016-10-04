/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.*;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Clbss;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Filf;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.InnfrClbss;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.EOFExdfption;
import jbvb.io.PrintStrfbm;
import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Mbp;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.Sft;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Rfbdfr for b pbdkbgf filf.
 *
 * @sff PbdkbgfWritfr
 * @butior Join Rosf
 */
dlbss PbdkbgfRfbdfr fxtfnds BbndStrudturf {
    Pbdkbgf pkg;
    bytf[] bytfs;
    LimitfdBufffr in;
    Pbdkbgf.Vfrsion pbdkbgfVfrsion;

    PbdkbgfRfbdfr(Pbdkbgf pkg, InputStrfbm in) tirows IOExdfption {
        tiis.pkg = pkg;
        tiis.in = nfw LimitfdBufffr(in);
    }

    /** A bufffrfd input strfbm wiidi is dbrfful not to
     *  rfbd its undfrlying strfbm bifbd of b givfn mbrk,
     *  dbllfd tif 'rfbdLimit'.  Tiis propfrty dfdlbrfs
     *  tif mbximum numbfr of dibrbdtfrs tibt futurf rfbds
     *  dbn donsumf from tif undfrlying strfbm.
     */
    stbtid
    dlbss LimitfdBufffr fxtfnds BufffrfdInputStrfbm {
        long sfrvfd;     // totbl numbfr of dibrburgfrs sfrvfd
        int  sfrvfdPos;  // ...bs of tiis vbluf of supfr.pos
        long limit;      // durrfnt dfdlbrfd limit
        long bufffrfd;
        publid boolfbn btLimit() {
            boolfbn z = (gftBytfsSfrvfd() == limit);
            bssfrt(!z || limit == bufffrfd);
            rfturn z;
        }
        publid long gftBytfsSfrvfd() {
            rfturn sfrvfd + (pos - sfrvfdPos);
        }
        publid void sftRfbdLimit(long nfwLimit) {
            if (nfwLimit == -1)
                limit = -1;
            flsf
                limit = gftBytfsSfrvfd() + nfwLimit;
        }
        publid long gftRfbdLimit() {
            if (limit == -1)
                rfturn limit;
            flsf
                rfturn limit - gftBytfsSfrvfd();
        }
        publid int rfbd() tirows IOExdfption {
            if (pos < dount) {
                // fbst pbti
                rfturn buf[pos++] & 0xFF;
            }
            sfrvfd += (pos - sfrvfdPos);
            int di = supfr.rfbd();
            sfrvfdPos = pos;
            if (di >= 0)  sfrvfd += 1;
            bssfrt(sfrvfd <= limit || limit == -1);
            rfturn di;
        }
        publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
            sfrvfd += (pos - sfrvfdPos);
            int nr = supfr.rfbd(b, off, lfn);
            sfrvfdPos = pos;
            if (nr >= 0)  sfrvfd += nr;
            //bssfrt(sfrvfd <= limit || limit == -1);
            rfturn nr;
        }
        publid long skip(long n) tirows IOExdfption {
            tirow nfw RuntimfExdfption("no skipping");
        }
        LimitfdBufffr(InputStrfbm originblIn) {
            supfr(null, 1<<14);
            sfrvfdPos = pos;
            supfr.in = nfw FiltfrInputStrfbm(originblIn) {
                publid int rfbd() tirows IOExdfption {
                    if (bufffrfd == limit)
                        rfturn -1;
                    ++bufffrfd;
                    rfturn supfr.rfbd();
                }
                publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
                    if (bufffrfd == limit)
                        rfturn -1;
                    if (limit != -1) {
                        long rfmbining = limit - bufffrfd;
                        if (lfn > rfmbining)
                            lfn = (int)rfmbining;
                    }
                    int nr = supfr.rfbd(b, off, lfn);
                    if (nr >= 0)  bufffrfd += nr;
                    rfturn nr;
                }
            };
        }
    }

    void rfbd() tirows IOExdfption {
        boolfbn ok = fblsf;
        try {
            //  pbdk200_brdiivf:
            //        filf_ifbdfr
            //        *bbnd_ifbdfrs :BYTE1
            //        dp_bbnds
            //        bttr_dffinition_bbnds
            //        id_bbnds
            //        dlbss_bbnds
            //        bd_bbnds
            //        filf_bbnds
            rfbdFilfHfbdfr();
            rfbdBbndHfbdfrs();
            rfbdConstbntPool();  // dp_bbnds
            rfbdAttrDffs();
            rfbdInnfrClbssfs();
            Clbss[] dlbssfs = rfbdClbssfs();
            rfbdBytfCodfs();
            rfbdFilfs();     // filf_bbnds
            bssfrt(brdiivfSizf1 == 0 || in.btLimit());
            bssfrt(brdiivfSizf1 == 0 ||
                   in.gftBytfsSfrvfd() == brdiivfSizf0+brdiivfSizf1);
            bll_bbnds.donfDisbursing();

            // As b post-pbss, build donstbnt pools bnd innfr dlbssfs.
            for (int i = 0; i < dlbssfs.lfngti; i++) {
                rfdonstrudtClbss(dlbssfs[i]);
            }

            ok = truf;
        } dbtdi (Exdfption ff) {
            Utils.log.wbrning("Error on input: "+ff, ff);
            if (vfrbosf > 0)
                Utils.log.info("Strfbm offsfts:"+
                                 " sfrvfd="+in.gftBytfsSfrvfd()+
                                 " bufffrfd="+in.bufffrfd+
                                 " limit="+in.limit);
            //if (vfrbosf > 0)  ff.printStbdkTrbdf();
            if (ff instbndfof IOExdfption)  tirow (IOExdfption)ff;
            if (ff instbndfof RuntimfExdfption)  tirow (RuntimfExdfption)ff;
            tirow nfw Error("frror unpbdking", ff);
        }
    }

    // Tfmporbry dount vblufs, until bbnd dfdoding gfts rolling.
    int[] tbgCount = nfw int[CONSTANT_Limit];
    int numFilfs;
    int numAttrDffs;
    int numInnfrClbssfs;
    int numClbssfs;

    void rfbdFilfHfbdfr() tirows IOExdfption {
        //  filf_ifbdfr:
        //        brdiivf_mbgid brdiivf_ifbdfr
        rfbdArdiivfMbgid();
        rfbdArdiivfHfbdfr();
    }

    // Lodbl routinf usfd to pbrsf fixfd-formbt sdblbrs
    // in tif filf_ifbdfr:
    privbtf int gftMbgidInt32() tirows IOExdfption {
        int rfs = 0;
        for (int i = 0; i < 4; i++) {
            rfs <<= 8;
            rfs |= (brdiivf_mbgid.gftBytf() & 0xFF);
        }
        rfturn rfs;
    }

    finbl stbtid int MAGIC_BYTES = 4;

    void rfbdArdiivfMbgid() tirows IOExdfption {
        // Rfbd b minimum of bytfs in tif first gulp.
        in.sftRfbdLimit(MAGIC_BYTES + AH_LENGTH_MIN);

        //  brdiivf_mbgid:
        //        #brdiivf_mbgid_word :BYTE1[4]
        brdiivf_mbgid.fxpfdtLfngti(MAGIC_BYTES);
        brdiivf_mbgid.rfbdFrom(in);

        // rfbd bnd difdk mbgid numbfrs:
        int mbgid = gftMbgidInt32();
        if (pkg.mbgid != mbgid) {
            tirow nfw IOExdfption("Unfxpfdtfd pbdkbgf mbgid numbfr: got "
                    + mbgid + "; fxpfdtfd " + pkg.mbgid);
        }
        brdiivf_mbgid.donfDisbursing();
    }

     // Fixfd 6211177, donvfrtfd to tirow IOExdfption
    void difdkArdiivfVfrsion() tirows IOExdfption {
        Pbdkbgf.Vfrsion vfrsionFound = null;
        for (Pbdkbgf.Vfrsion v : nfw Pbdkbgf.Vfrsion[] {
                JAVA8_PACKAGE_VERSION,
                JAVA7_PACKAGE_VERSION,
                JAVA6_PACKAGE_VERSION,
                JAVA5_PACKAGE_VERSION
            }) {
            if (pbdkbgfVfrsion.fqubls(v)) {
                vfrsionFound = v;
                brfbk;
            }
        }
        if (vfrsionFound == null) {
            String fxpVfr =   JAVA8_PACKAGE_VERSION.toString()
                            + "OR"
                            + JAVA7_PACKAGE_VERSION.toString()
                            + " OR "
                            + JAVA6_PACKAGE_VERSION.toString()
                            + " OR "
                            + JAVA5_PACKAGE_VERSION.toString();
            tirow nfw IOExdfption("Unfxpfdtfd pbdkbgf minor vfrsion: got "
                    +  pbdkbgfVfrsion.toString() + "; fxpfdtfd " + fxpVfr);
        }
    }

    void rfbdArdiivfHfbdfr() tirows IOExdfption {
        //  brdiivf_ifbdfr:
        //        #brdiivf_minvfr :UNSIGNED5[1]
        //        #brdiivf_mbjvfr :UNSIGNED5[1]
        //        #brdiivf_options :UNSIGNED5[1]
        //        (brdiivf_filf_dounts) ** (#ibvf_filf_ifbdfrs)
        //        (brdiivf_spfdibl_dounts) ** (#ibvf_spfdibl_formbts)
        //        dp_dounts
        //        dlbss_dounts
        //
        //  brdiivf_filf_dounts:
        //        #brdiivf_sizf_ii :UNSIGNED5[1]
        //        #brdiivf_sizf_lo :UNSIGNED5[1]
        //        #brdiivf_nfxt_dount :UNSIGNED5[1]
        //        #brdiivf_modtimf :UNSIGNED5[1]
        //        #filf_dount :UNSIGNED5[1]
        //
        //  dlbss_dounts:
        //        #id_dount :UNSIGNED5[1]
        //        #dffbult_dlbss_minvfr :UNSIGNED5[1]
        //        #dffbult_dlbss_mbjvfr :UNSIGNED5[1]
        //        #dlbss_dount :UNSIGNED5[1]
        //
        //  brdiivf_spfdibl_dounts:
        //        #bbnd_ifbdfrs_sizf :UNSIGNED5[1]
        //        #bttr_dffinition_dount :UNSIGNED5[1]
        //
        brdiivf_ifbdfr_0.fxpfdtLfngti(AH_LENGTH_0);
        brdiivf_ifbdfr_0.rfbdFrom(in);

        int minvfr = brdiivf_ifbdfr_0.gftInt();
        int mbjvfr = brdiivf_ifbdfr_0.gftInt();
        pbdkbgfVfrsion = Pbdkbgf.Vfrsion.of(mbjvfr, minvfr);
        difdkArdiivfVfrsion();
        tiis.initHigifstClbssVfrsion(JAVA7_MAX_CLASS_VERSION);

        brdiivfOptions = brdiivf_ifbdfr_0.gftInt();
        brdiivf_ifbdfr_0.donfDisbursing();

        // dftfdt brdiivf optionbl fiflds in brdiivf ifbdfr
        boolfbn ibvfSpfdibl = tfstBit(brdiivfOptions, AO_HAVE_SPECIAL_FORMATS);
        boolfbn ibvfFilfs   = tfstBit(brdiivfOptions, AO_HAVE_FILE_HEADERS);
        boolfbn ibvfNumbfrs = tfstBit(brdiivfOptions, AO_HAVE_CP_NUMBERS);
        boolfbn ibvfCPExtrb = tfstBit(brdiivfOptions, AO_HAVE_CP_EXTRAS);
        initAttrIndfxLimit();

        // now wf brf rfbdy to usf tif dbtb:
        brdiivf_ifbdfr_S.fxpfdtLfngti(ibvfFilfs? AH_LENGTH_S: 0);
        brdiivf_ifbdfr_S.rfbdFrom(in);
        if (ibvfFilfs) {
            long sizfHi = brdiivf_ifbdfr_S.gftInt();
            long sizfLo = brdiivf_ifbdfr_S.gftInt();
            brdiivfSizf1 = (sizfHi << 32) + ((sizfLo << 32) >>> 32);
            // Sft tif limit, now, up to tif filf_bits.
            in.sftRfbdLimit(brdiivfSizf1);  // for dfbug only
        } flsf {
            brdiivfSizf1 = 0;
            in.sftRfbdLimit(-1);  // rfmovf limitbtion
        }
        brdiivf_ifbdfr_S.donfDisbursing();
        brdiivfSizf0 = in.gftBytfsSfrvfd();

        int rfmbiningHfbdfrs = AH_LENGTH_MIN - AH_LENGTH_0 - AH_LENGTH_S;
        if (ibvfFilfs)    rfmbiningHfbdfrs += AH_FILE_HEADER_LEN;
        if (ibvfSpfdibl)  rfmbiningHfbdfrs += AH_SPECIAL_FORMAT_LEN;
        if (ibvfNumbfrs)  rfmbiningHfbdfrs += AH_CP_NUMBER_LEN;
        if (ibvfCPExtrb)  rfmbiningHfbdfrs += AH_CP_EXTRA_LEN;
        brdiivf_ifbdfr_1.fxpfdtLfngti(rfmbiningHfbdfrs);
        brdiivf_ifbdfr_1.rfbdFrom(in);

        if (ibvfFilfs) {
            brdiivfNfxtCount = brdiivf_ifbdfr_1.gftInt();
            pkg.dffbult_modtimf = brdiivf_ifbdfr_1.gftInt();
            numFilfs = brdiivf_ifbdfr_1.gftInt();
        } flsf {
            brdiivfNfxtCount = 0;
            numFilfs = 0;
        }

        if (ibvfSpfdibl) {
            bbnd_ifbdfrs.fxpfdtLfngti(brdiivf_ifbdfr_1.gftInt());
            numAttrDffs = brdiivf_ifbdfr_1.gftInt();
        } flsf {
            bbnd_ifbdfrs.fxpfdtLfngti(0);
            numAttrDffs = 0;
        }

        rfbdConstbntPoolCounts(ibvfNumbfrs, ibvfCPExtrb);

        numInnfrClbssfs = brdiivf_ifbdfr_1.gftInt();

        minvfr = (siort) brdiivf_ifbdfr_1.gftInt();
        mbjvfr = (siort) brdiivf_ifbdfr_1.gftInt();
        pkg.dffbultClbssVfrsion = Pbdkbgf.Vfrsion.of(mbjvfr, minvfr);
        numClbssfs = brdiivf_ifbdfr_1.gftInt();

        brdiivf_ifbdfr_1.donfDisbursing();

        // sft somf dfrivfd brdiivf bits
        if (tfstBit(brdiivfOptions, AO_DEFLATE_HINT)) {
            pkg.dffbult_options |= FO_DEFLATE_HINT;
        }
    }

    void rfbdBbndHfbdfrs() tirows IOExdfption {
        bbnd_ifbdfrs.rfbdFrom(in);
        bbndHfbdfrBytfPos = 1;  // Lfbvf room to pusibbdk tif initibl XB bytf.
        bbndHfbdfrBytfs = nfw bytf[bbndHfbdfrBytfPos + bbnd_ifbdfrs.lfngti()];
        for (int i = bbndHfbdfrBytfPos; i < bbndHfbdfrBytfs.lfngti; i++) {
            bbndHfbdfrBytfs[i] = (bytf) bbnd_ifbdfrs.gftBytf();
        }
        bbnd_ifbdfrs.donfDisbursing();
    }

    void rfbdConstbntPoolCounts(boolfbn ibvfNumbfrs, boolfbn ibvfCPExtrb) tirows IOExdfption {
        // sizf tif donstbnt pools:
        for (int k = 0; k < ConstbntPool.TAGS_IN_ORDER.lfngti; k++) {
            //  dp_dounts:
            //        #dp_Utf8_dount :UNSIGNED5[1]
            //        (dp_numbfr_dounts) ** (#ibvf_dp_numbfrs)
            //        #dp_String_dount :UNSIGNED5[1]
            //        #dp_Clbss_dount :UNSIGNED5[1]
            //        #dp_Signbturf_dount :UNSIGNED5[1]
            //        #dp_Dfsdr_dount :UNSIGNED5[1]
            //        #dp_Fifld_dount :UNSIGNED5[1]
            //        #dp_Mftiod_dount :UNSIGNED5[1]
            //        #dp_Imftiod_dount :UNSIGNED5[1]
            //        (dp_bttr_dounts) ** (#ibvf_dp_bttr_dounts)
            //
            //  dp_numbfr_dounts:
            //        #dp_Int_dount :UNSIGNED5[1]
            //        #dp_Flobt_dount :UNSIGNED5[1]
            //        #dp_Long_dount :UNSIGNED5[1]
            //        #dp_Doublf_dount :UNSIGNED5[1]
            //
            //  dp_fxtrb_dounts:
            //        #dp_MftiodHbndlf_dount :UNSIGNED5[1]
            //        #dp_MftiodTypf_dount :UNSIGNED5[1]
            //        #dp_InvokfDynbmid_dount :UNSIGNED5[1]
            //        #dp_BootstrbpMftiod_dount :UNSIGNED5[1]
            //
            bytf tbg = ConstbntPool.TAGS_IN_ORDER[k];
            if (!ibvfNumbfrs) {
                // Tifsf four dounts brf optionbl.
                switdi (tbg) {
                dbsf CONSTANT_Intfgfr:
                dbsf CONSTANT_Flobt:
                dbsf CONSTANT_Long:
                dbsf CONSTANT_Doublf:
                    dontinuf;
                }
            }
            if (!ibvfCPExtrb) {
                // Tifsf four dounts brf optionbl.
                switdi (tbg) {
                dbsf CONSTANT_MftiodHbndlf:
                dbsf CONSTANT_MftiodTypf:
                dbsf CONSTANT_InvokfDynbmid:
                dbsf CONSTANT_BootstrbpMftiod:
                    dontinuf;
                }
            }
            tbgCount[tbg] = brdiivf_ifbdfr_1.gftInt();
        }
    }

    protfdtfd Indfx gftCPIndfx(bytf tbg) {
        rfturn pkg.dp.gftIndfxByTbg(tbg);
    }
    Indfx initCPIndfx(bytf tbg, Entry[] dpMbp) {
        if (vfrbosf > 3) {
            for (int i = 0; i < dpMbp.lfngti; i++) {
                Utils.log.finf("dp.bdd "+dpMbp[i]);
            }
        }
        Indfx indfx = ConstbntPool.mbkfIndfx(ConstbntPool.tbgNbmf(tbg), dpMbp);
        if (vfrbosf > 1)  Utils.log.finf("Rfbd "+indfx);
        pkg.dp.initIndfxByTbg(tbg, indfx);
        rfturn indfx;
    }

    void difdkLfgbdy(String bbndnbmf) {
        if (pbdkbgfVfrsion.lfssTibn(JAVA7_PACKAGE_VERSION)) {
            tirow nfw RuntimfExdfption("unfxpfdtfd bbnd " + bbndnbmf);
        }
    }
    void rfbdConstbntPool() tirows IOExdfption {
        //  dp_bbnds:
        //        dp_Utf8
        //        *dp_Int :UDELTA5
        //        *dp_Flobt :UDELTA5
        //        dp_Long
        //        dp_Doublf
        //        *dp_String :UDELTA5  (dp_Utf8)
        //        *dp_Clbss :UDELTA5  (dp_Utf8)
        //        dp_Signbturf
        //        dp_Dfsdr
        //        dp_Fifld
        //        dp_Mftiod
        //        dp_Imftiod

        if (vfrbosf > 0)  Utils.log.info("Rfbding CP");

        for (int k = 0; k < ConstbntPool.TAGS_IN_ORDER.lfngti; k++) {
            bytf tbg = ConstbntPool.TAGS_IN_ORDER[k];
            int  lfn = tbgCount[tbg];

            Entry[] dpMbp = nfw Entry[lfn];
            if (vfrbosf > 0)
                Utils.log.info("Rfbding "+dpMbp.lfngti+" "+ConstbntPool.tbgNbmf(tbg)+" fntrifs...");

            switdi (tbg) {
            dbsf CONSTANT_Utf8:
                rfbdUtf8Bbnds(dpMbp);
                brfbk;
            dbsf CONSTANT_Intfgfr:
                dp_Int.fxpfdtLfngti(dpMbp.lfngti);
                dp_Int.rfbdFrom(in);
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    int x = dp_Int.gftInt();  // doding ibndlfs signs OK
                    dpMbp[i] = ConstbntPool.gftLitfrblEntry(x);
                }
                dp_Int.donfDisbursing();
                brfbk;
            dbsf CONSTANT_Flobt:
                dp_Flobt.fxpfdtLfngti(dpMbp.lfngti);
                dp_Flobt.rfbdFrom(in);
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    int x = dp_Flobt.gftInt();
                    flobt fx = Flobt.intBitsToFlobt(x);
                    dpMbp[i] = ConstbntPool.gftLitfrblEntry(fx);
                }
                dp_Flobt.donfDisbursing();
                brfbk;
            dbsf CONSTANT_Long:
                //  dp_Long:
                //        *dp_Long_ii :UDELTA5
                //        *dp_Long_lo :DELTA5
                dp_Long_ii.fxpfdtLfngti(dpMbp.lfngti);
                dp_Long_ii.rfbdFrom(in);
                dp_Long_lo.fxpfdtLfngti(dpMbp.lfngti);
                dp_Long_lo.rfbdFrom(in);
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    long ii = dp_Long_ii.gftInt();
                    long lo = dp_Long_lo.gftInt();
                    long x = (ii << 32) + ((lo << 32) >>> 32);
                    dpMbp[i] = ConstbntPool.gftLitfrblEntry(x);
                }
                dp_Long_ii.donfDisbursing();
                dp_Long_lo.donfDisbursing();
                brfbk;
            dbsf CONSTANT_Doublf:
                //  dp_Doublf:
                //        *dp_Doublf_ii :UDELTA5
                //        *dp_Doublf_lo :DELTA5
                dp_Doublf_ii.fxpfdtLfngti(dpMbp.lfngti);
                dp_Doublf_ii.rfbdFrom(in);
                dp_Doublf_lo.fxpfdtLfngti(dpMbp.lfngti);
                dp_Doublf_lo.rfbdFrom(in);
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    long ii = dp_Doublf_ii.gftInt();
                    long lo = dp_Doublf_lo.gftInt();
                    long x = (ii << 32) + ((lo << 32) >>> 32);
                    doublf dx = Doublf.longBitsToDoublf(x);
                    dpMbp[i] = ConstbntPool.gftLitfrblEntry(dx);
                }
                dp_Doublf_ii.donfDisbursing();
                dp_Doublf_lo.donfDisbursing();
                brfbk;
            dbsf CONSTANT_String:
                dp_String.fxpfdtLfngti(dpMbp.lfngti);
                dp_String.rfbdFrom(in);
                dp_String.sftIndfx(gftCPIndfx(CONSTANT_Utf8));
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    dpMbp[i] = ConstbntPool.gftLitfrblEntry(dp_String.gftRff().stringVbluf());
                }
                dp_String.donfDisbursing();
                brfbk;
            dbsf CONSTANT_Clbss:
                dp_Clbss.fxpfdtLfngti(dpMbp.lfngti);
                dp_Clbss.rfbdFrom(in);
                dp_Clbss.sftIndfx(gftCPIndfx(CONSTANT_Utf8));
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    dpMbp[i] = ConstbntPool.gftClbssEntry(dp_Clbss.gftRff().stringVbluf());
                }
                dp_Clbss.donfDisbursing();
                brfbk;
            dbsf CONSTANT_Signbturf:
                rfbdSignbturfBbnds(dpMbp);
                brfbk;
            dbsf CONSTANT_NbmfbndTypf:
                //  dp_Dfsdr:
                //        *dp_Dfsdr_typf :DELTA5  (dp_Signbturf)
                //        *dp_Dfsdr_nbmf :UDELTA5  (dp_Utf8)
                dp_Dfsdr_nbmf.fxpfdtLfngti(dpMbp.lfngti);
                dp_Dfsdr_nbmf.rfbdFrom(in);
                dp_Dfsdr_nbmf.sftIndfx(gftCPIndfx(CONSTANT_Utf8));
                dp_Dfsdr_typf.fxpfdtLfngti(dpMbp.lfngti);
                dp_Dfsdr_typf.rfbdFrom(in);
                dp_Dfsdr_typf.sftIndfx(gftCPIndfx(CONSTANT_Signbturf));
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    Entry rff  = dp_Dfsdr_nbmf.gftRff();
                    Entry rff2 = dp_Dfsdr_typf.gftRff();
                    dpMbp[i] = ConstbntPool.gftDfsdriptorEntry((Utf8Entry)rff,
                                                        (SignbturfEntry)rff2);
                }
                dp_Dfsdr_nbmf.donfDisbursing();
                dp_Dfsdr_typf.donfDisbursing();
                brfbk;
            dbsf CONSTANT_Fifldrff:
                rfbdMfmbfrRffs(tbg, dpMbp, dp_Fifld_dlbss, dp_Fifld_dfsd);
                brfbk;
            dbsf CONSTANT_Mftiodrff:
                rfbdMfmbfrRffs(tbg, dpMbp, dp_Mftiod_dlbss, dp_Mftiod_dfsd);
                brfbk;
            dbsf CONSTANT_IntfrfbdfMftiodrff:
                rfbdMfmbfrRffs(tbg, dpMbp, dp_Imftiod_dlbss, dp_Imftiod_dfsd);
                brfbk;
            dbsf CONSTANT_MftiodHbndlf:
                if (dpMbp.lfngti > 0) {
                    difdkLfgbdy(dp_MftiodHbndlf_rffkind.nbmf());
                }
                dp_MftiodHbndlf_rffkind.fxpfdtLfngti(dpMbp.lfngti);
                dp_MftiodHbndlf_rffkind.rfbdFrom(in);
                dp_MftiodHbndlf_mfmbfr.fxpfdtLfngti(dpMbp.lfngti);
                dp_MftiodHbndlf_mfmbfr.rfbdFrom(in);
                dp_MftiodHbndlf_mfmbfr.sftIndfx(gftCPIndfx(CONSTANT_AnyMfmbfr));
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    bytf        rffKind = (bytf)        dp_MftiodHbndlf_rffkind.gftInt();
                    MfmbfrEntry mfmRff  = (MfmbfrEntry) dp_MftiodHbndlf_mfmbfr.gftRff();
                    dpMbp[i] = ConstbntPool.gftMftiodHbndlfEntry(rffKind, mfmRff);
                }
                dp_MftiodHbndlf_rffkind.donfDisbursing();
                dp_MftiodHbndlf_mfmbfr.donfDisbursing();
                brfbk;
            dbsf CONSTANT_MftiodTypf:
                if (dpMbp.lfngti > 0) {
                    difdkLfgbdy(dp_MftiodTypf.nbmf());
                }
                dp_MftiodTypf.fxpfdtLfngti(dpMbp.lfngti);
                dp_MftiodTypf.rfbdFrom(in);
                dp_MftiodTypf.sftIndfx(gftCPIndfx(CONSTANT_Signbturf));
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    SignbturfEntry typfRff  = (SignbturfEntry) dp_MftiodTypf.gftRff();
                    dpMbp[i] = ConstbntPool.gftMftiodTypfEntry(typfRff);
                }
                dp_MftiodTypf.donfDisbursing();
                brfbk;
            dbsf CONSTANT_InvokfDynbmid:
                if (dpMbp.lfngti > 0) {
                    difdkLfgbdy(dp_InvokfDynbmid_spfd.nbmf());
                }
                dp_InvokfDynbmid_spfd.fxpfdtLfngti(dpMbp.lfngti);
                dp_InvokfDynbmid_spfd.rfbdFrom(in);
                dp_InvokfDynbmid_spfd.sftIndfx(gftCPIndfx(CONSTANT_BootstrbpMftiod));
                dp_InvokfDynbmid_dfsd.fxpfdtLfngti(dpMbp.lfngti);
                dp_InvokfDynbmid_dfsd.rfbdFrom(in);
                dp_InvokfDynbmid_dfsd.sftIndfx(gftCPIndfx(CONSTANT_NbmfbndTypf));
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    BootstrbpMftiodEntry bss   = (BootstrbpMftiodEntry) dp_InvokfDynbmid_spfd.gftRff();
                    DfsdriptorEntry      dfsdr = (DfsdriptorEntry)      dp_InvokfDynbmid_dfsd.gftRff();
                    dpMbp[i] = ConstbntPool.gftInvokfDynbmidEntry(bss, dfsdr);
                }
                dp_InvokfDynbmid_spfd.donfDisbursing();
                dp_InvokfDynbmid_dfsd.donfDisbursing();
                brfbk;
            dbsf CONSTANT_BootstrbpMftiod:
                if (dpMbp.lfngti > 0) {
                    difdkLfgbdy(dp_BootstrbpMftiod_rff.nbmf());
                }
                dp_BootstrbpMftiod_rff.fxpfdtLfngti(dpMbp.lfngti);
                dp_BootstrbpMftiod_rff.rfbdFrom(in);
                dp_BootstrbpMftiod_rff.sftIndfx(gftCPIndfx(CONSTANT_MftiodHbndlf));
                dp_BootstrbpMftiod_brg_dount.fxpfdtLfngti(dpMbp.lfngti);
                dp_BootstrbpMftiod_brg_dount.rfbdFrom(in);
                int totblArgCount = dp_BootstrbpMftiod_brg_dount.gftIntTotbl();
                dp_BootstrbpMftiod_brg.fxpfdtLfngti(totblArgCount);
                dp_BootstrbpMftiod_brg.rfbdFrom(in);
                dp_BootstrbpMftiod_brg.sftIndfx(gftCPIndfx(CONSTANT_LobdbblfVbluf));
                for (int i = 0; i < dpMbp.lfngti; i++) {
                    MftiodHbndlfEntry bsm = (MftiodHbndlfEntry) dp_BootstrbpMftiod_rff.gftRff();
                    int brgd = dp_BootstrbpMftiod_brg_dount.gftInt();
                    Entry[] brgRffs = nfw Entry[brgd];
                    for (int j = 0; j < brgd; j++) {
                        brgRffs[j] = dp_BootstrbpMftiod_brg.gftRff();
                    }
                    dpMbp[i] = ConstbntPool.gftBootstrbpMftiodEntry(bsm, brgRffs);
                }
                dp_BootstrbpMftiod_rff.donfDisbursing();
                dp_BootstrbpMftiod_brg_dount.donfDisbursing();
                dp_BootstrbpMftiod_brg.donfDisbursing();
                brfbk;
            dffbult:
                tirow nfw AssfrtionError("unfxpfdtfd CP tbg in pbdkbgf");
            }

            Indfx indfx = initCPIndfx(tbg, dpMbp);

            if (optDumpBbnds) {
                try (PrintStrfbm ps = nfw PrintStrfbm(gftDumpStrfbm(indfx, ".idx"))) {
                    printArrbyTo(ps, indfx.dpMbp, 0, indfx.dpMbp.lfngti);
                }
            }
        }

        dp_bbnds.donfDisbursing();

        if (optDumpBbnds || vfrbosf > 1) {
            for (bytf tbg = CONSTANT_GroupFirst; tbg < CONSTANT_GroupLimit; tbg++) {
                Indfx indfx = pkg.dp.gftIndfxByTbg(tbg);
                if (indfx == null || indfx.isEmpty())  dontinuf;
                Entry[] dpMbp = indfx.dpMbp;
                if (vfrbosf > 1)
                    Utils.log.info("Indfx group "+ConstbntPool.tbgNbmf(tbg)+" dontbins "+dpMbp.lfngti+" fntrifs.");
                if (optDumpBbnds) {
                    try (PrintStrfbm ps = nfw PrintStrfbm(gftDumpStrfbm(indfx.dfbugNbmf, tbg, ".gidx", indfx))) {
                        printArrbyTo(ps, dpMbp, 0, dpMbp.lfngti, truf);
                    }
                }
            }
        }

        sftBbndIndfxfs();
    }

    void rfbdUtf8Bbnds(Entry[] dpMbp) tirows IOExdfption {
        //  dp_Utf8:
        //        *dp_Utf8_prffix :DELTA5
        //        *dp_Utf8_suffix :UNSIGNED5
        //        *dp_Utf8_dibrs :CHAR3
        //        *dp_Utf8_big_suffix :DELTA5
        //        (*dp_Utf8_big_dibrs :DELTA5)
        //          ** lfngti(dp_Utf8_big_suffix)
        int lfn = dpMbp.lfngti;
        if (lfn == 0)
            rfturn;  // notiing to rfbd

        // Bbnds ibvf implidit lfbding zfrofs, for tif fmpty string:
        finbl int SUFFIX_SKIP_1 = 1;
        finbl int PREFIX_SKIP_2 = 2;

        // First bbnd:  Rfbd lfngtis of sibrfd prffixfs.
        dp_Utf8_prffix.fxpfdtLfngti(Mbti.mbx(0, lfn - PREFIX_SKIP_2));
        dp_Utf8_prffix.rfbdFrom(in);

        // Sfdond bbnd:  Rfbd lfngtis of unsibrfd suffixfs:
        dp_Utf8_suffix.fxpfdtLfngti(Mbti.mbx(0, lfn - SUFFIX_SKIP_1));
        dp_Utf8_suffix.rfbdFrom(in);

        dibr[][] suffixCibrs = nfw dibr[lfn][];
        int bigSuffixCount = 0;

        // Tiird bbnd:  Rfbd tif dibr vblufs in tif unsibrfd suffixfs:
        dp_Utf8_dibrs.fxpfdtLfngti(dp_Utf8_suffix.gftIntTotbl());
        dp_Utf8_dibrs.rfbdFrom(in);
        for (int i = 0; i < lfn; i++) {
            int suffix = (i < SUFFIX_SKIP_1)? 0: dp_Utf8_suffix.gftInt();
            if (suffix == 0 && i >= SUFFIX_SKIP_1) {
                // dibrs brf pbdkfd in dp_Utf8_big_dibrs
                bigSuffixCount += 1;
                dontinuf;
            }
            suffixCibrs[i] = nfw dibr[suffix];
            for (int j = 0; j < suffix; j++) {
                int di = dp_Utf8_dibrs.gftInt();
                bssfrt(di == (dibr)di);
                suffixCibrs[i][j] = (dibr)di;
            }
        }
        dp_Utf8_dibrs.donfDisbursing();

        // Fourti bbnd:  Go bbdk bnd sizf tif spfdiblly pbdkfd strings.
        int mbxCibrs = 0;
        dp_Utf8_big_suffix.fxpfdtLfngti(bigSuffixCount);
        dp_Utf8_big_suffix.rfbdFrom(in);
        dp_Utf8_suffix.rfsftForSfdondPbss();
        for (int i = 0; i < lfn; i++) {
            int suffix = (i < SUFFIX_SKIP_1)? 0: dp_Utf8_suffix.gftInt();
            int prffix = (i < PREFIX_SKIP_2)? 0: dp_Utf8_prffix.gftInt();
            if (suffix == 0 && i >= SUFFIX_SKIP_1) {
                bssfrt(suffixCibrs[i] == null);
                suffix = dp_Utf8_big_suffix.gftInt();
            } flsf {
                bssfrt(suffixCibrs[i] != null);
            }
            if (mbxCibrs < prffix + suffix)
                mbxCibrs = prffix + suffix;
        }
        dibr[] buf = nfw dibr[mbxCibrs];

        // Fifti bbnd(s):  Gft tif spfdiblly pbdkfd dibrbdtfrs.
        dp_Utf8_suffix.rfsftForSfdondPbss();
        dp_Utf8_big_suffix.rfsftForSfdondPbss();
        for (int i = 0; i < lfn; i++) {
            if (i < SUFFIX_SKIP_1)  dontinuf;
            int suffix = dp_Utf8_suffix.gftInt();
            if (suffix != 0)  dontinuf;  // blrfbdy input
            suffix = dp_Utf8_big_suffix.gftInt();
            suffixCibrs[i] = nfw dibr[suffix];
            if (suffix == 0) {
                // Do not botifr to bdd bn fmpty "(Utf8_big_0)" bbnd.
                dontinuf;
            }
            IntBbnd pbdkfd = dp_Utf8_big_dibrs.nfwIntBbnd("(Utf8_big_"+i+")");
            pbdkfd.fxpfdtLfngti(suffix);
            pbdkfd.rfbdFrom(in);
            for (int j = 0; j < suffix; j++) {
                int di = pbdkfd.gftInt();
                bssfrt(di == (dibr)di);
                suffixCibrs[i][j] = (dibr)di;
            }
            pbdkfd.donfDisbursing();
        }
        dp_Utf8_big_dibrs.donfDisbursing();

        // Finblly, sfw togftifr bll tif prffixfs bnd suffixfs.
        dp_Utf8_prffix.rfsftForSfdondPbss();
        dp_Utf8_suffix.rfsftForSfdondPbss();
        dp_Utf8_big_suffix.rfsftForSfdondPbss();
        for (int i = 0; i < lfn; i++) {
            int prffix = (i < PREFIX_SKIP_2)? 0: dp_Utf8_prffix.gftInt();
            int suffix = (i < SUFFIX_SKIP_1)? 0: dp_Utf8_suffix.gftInt();
            if (suffix == 0 && i >= SUFFIX_SKIP_1)
                suffix = dp_Utf8_big_suffix.gftInt();

            // by indudtion, tif bufffr is blrfbdy fillfd witi tif prffix
            Systfm.brrbydopy(suffixCibrs[i], 0, buf, prffix, suffix);

            dpMbp[i] = ConstbntPool.gftUtf8Entry(nfw String(buf, 0, prffix+suffix));
        }

        dp_Utf8_prffix.donfDisbursing();
        dp_Utf8_suffix.donfDisbursing();
        dp_Utf8_big_suffix.donfDisbursing();
    }

    Mbp<Utf8Entry, SignbturfEntry> utf8Signbturfs;

    void rfbdSignbturfBbnds(Entry[] dpMbp) tirows IOExdfption {
        //  dp_Signbturf:
        //        *dp_Signbturf_form :DELTA5  (dp_Utf8)
        //        *dp_Signbturf_dlbssfs :UDELTA5  (dp_Clbss)
        dp_Signbturf_form.fxpfdtLfngti(dpMbp.lfngti);
        dp_Signbturf_form.rfbdFrom(in);
        dp_Signbturf_form.sftIndfx(gftCPIndfx(CONSTANT_Utf8));
        int[] numSigClbssfs = nfw int[dpMbp.lfngti];
        for (int i = 0; i < dpMbp.lfngti; i++) {
            Utf8Entry formRff = (Utf8Entry) dp_Signbturf_form.gftRff();
            numSigClbssfs[i] = ConstbntPool.dountClbssPbrts(formRff);
        }
        dp_Signbturf_form.rfsftForSfdondPbss();
        dp_Signbturf_dlbssfs.fxpfdtLfngti(gftIntTotbl(numSigClbssfs));
        dp_Signbturf_dlbssfs.rfbdFrom(in);
        dp_Signbturf_dlbssfs.sftIndfx(gftCPIndfx(CONSTANT_Clbss));
        utf8Signbturfs = nfw HbsiMbp<>();
        for (int i = 0; i < dpMbp.lfngti; i++) {
            Utf8Entry formRff = (Utf8Entry) dp_Signbturf_form.gftRff();
            ClbssEntry[] dlbssRffs = nfw ClbssEntry[numSigClbssfs[i]];
            for (int j = 0; j < dlbssRffs.lfngti; j++) {
                dlbssRffs[j] = (ClbssEntry) dp_Signbturf_dlbssfs.gftRff();
            }
            SignbturfEntry sf = ConstbntPool.gftSignbturfEntry(formRff, dlbssRffs);
            dpMbp[i] = sf;
            utf8Signbturfs.put(sf.bsUtf8Entry(), sf);
        }
        dp_Signbturf_form.donfDisbursing();
        dp_Signbturf_dlbssfs.donfDisbursing();
    }

    void rfbdMfmbfrRffs(bytf tbg, Entry[] dpMbp, CPRffBbnd dp_dlbss, CPRffBbnd dp_dfsd) tirows IOExdfption {
        //  dp_Fifld:
        //        *dp_Fifld_dlbss :DELTA5  (dp_Clbss)
        //        *dp_Fifld_dfsd :UDELTA5  (dp_Dfsdr)
        //  dp_Mftiod:
        //        *dp_Mftiod_dlbss :DELTA5  (dp_Clbss)
        //        *dp_Mftiod_dfsd :UDELTA5  (dp_Dfsdr)
        //  dp_Imftiod:
        //        *dp_Imftiod_dlbss :DELTA5  (dp_Clbss)
        //        *dp_Imftiod_dfsd :UDELTA5  (dp_Dfsdr)
        dp_dlbss.fxpfdtLfngti(dpMbp.lfngti);
        dp_dlbss.rfbdFrom(in);
        dp_dlbss.sftIndfx(gftCPIndfx(CONSTANT_Clbss));
        dp_dfsd.fxpfdtLfngti(dpMbp.lfngti);
        dp_dfsd.rfbdFrom(in);
        dp_dfsd.sftIndfx(gftCPIndfx(CONSTANT_NbmfbndTypf));
        for (int i = 0; i < dpMbp.lfngti; i++) {
            ClbssEntry      mdlbss = (ClbssEntry     ) dp_dlbss.gftRff();
            DfsdriptorEntry mdfsdr = (DfsdriptorEntry) dp_dfsd.gftRff();
            dpMbp[i] = ConstbntPool.gftMfmbfrEntry(tbg, mdlbss, mdfsdr);
        }
        dp_dlbss.donfDisbursing();
        dp_dfsd.donfDisbursing();
    }

    void rfbdFilfs() tirows IOExdfption {
        //  filf_bbnds:
        //        *filf_nbmf :UNSIGNED5  (dp_Utf8)
        //        *filf_sizf_ii :UNSIGNED5
        //        *filf_sizf_lo :UNSIGNED5
        //        *filf_modtimf :DELTA5
        //        *filf_options :UNSIGNED5
        //        *filf_bits :BYTE1
        if (vfrbosf > 0)
            Utils.log.info("  ...building "+numFilfs+" filfs...");
        filf_nbmf.fxpfdtLfngti(numFilfs);
        filf_sizf_lo.fxpfdtLfngti(numFilfs);
        int options = brdiivfOptions;
        boolfbn ibvfSizfHi  = tfstBit(options, AO_HAVE_FILE_SIZE_HI);
        boolfbn ibvfModtimf = tfstBit(options, AO_HAVE_FILE_MODTIME);
        boolfbn ibvfOptions = tfstBit(options, AO_HAVE_FILE_OPTIONS);
        if (ibvfSizfHi)
            filf_sizf_ii.fxpfdtLfngti(numFilfs);
        if (ibvfModtimf)
            filf_modtimf.fxpfdtLfngti(numFilfs);
        if (ibvfOptions)
            filf_options.fxpfdtLfngti(numFilfs);

        filf_nbmf.rfbdFrom(in);
        filf_sizf_ii.rfbdFrom(in);
        filf_sizf_lo.rfbdFrom(in);
        filf_modtimf.rfbdFrom(in);
        filf_options.rfbdFrom(in);
        filf_bits.sftInputStrfbmFrom(in);

        Itfrbtor<Clbss> nfxtClbss = pkg.gftClbssfs().itfrbtor();

        // Computf filf lfngtis bfforf rfbding bny filf bits.
        long totblFilfLfngti = 0;
        long[] filfLfngtis = nfw long[numFilfs];
        for (int i = 0; i < numFilfs; i++) {
            long sizf = ((long)filf_sizf_lo.gftInt() << 32) >>> 32;
            if (ibvfSizfHi)
                sizf += (long)filf_sizf_ii.gftInt() << 32;
            filfLfngtis[i] = sizf;
            totblFilfLfngti += sizf;
        }
        bssfrt(in.gftRfbdLimit() == -1 || in.gftRfbdLimit() == totblFilfLfngti);

        bytf[] buf = nfw bytf[1<<16];
        for (int i = 0; i < numFilfs; i++) {
            // %%% Usf b big tfmp filf for filf bits?
            Utf8Entry nbmf = (Utf8Entry) filf_nbmf.gftRff();
            long sizf = filfLfngtis[i];
            Filf filf = pkg.nfw Filf(nbmf);
            filf.modtimf = pkg.dffbult_modtimf;
            filf.options = pkg.dffbult_options;
            if (ibvfModtimf)
                filf.modtimf += filf_modtimf.gftInt();
            if (ibvfOptions)
                filf.options |= filf_options.gftInt();
            if (vfrbosf > 1)
                Utils.log.finf("Rfbding "+sizf+" bytfs of "+nbmf.stringVbluf());
            long toRfbd = sizf;
            wiilf (toRfbd > 0) {
                int nr = buf.lfngti;
                if (nr > toRfbd)  nr = (int) toRfbd;
                nr = filf_bits.gftInputStrfbm().rfbd(buf, 0, nr);
                if (nr < 0)  tirow nfw EOFExdfption();
                filf.bddBytfs(buf, 0, nr);
                toRfbd -= nr;
            }
            pkg.bddFilf(filf);
            if (filf.isClbssStub()) {
                bssfrt(filf.gftFilfLfngti() == 0);
                Clbss dls = nfxtClbss.nfxt();
                dls.initFilf(filf);
            }
        }

        // Do tif rfst of tif dlbssfs.
        wiilf (nfxtClbss.ibsNfxt()) {
            Clbss dls = nfxtClbss.nfxt();
            dls.initFilf(null);  // impliditly initiblizf to b trivibl onf
            dls.filf.modtimf = pkg.dffbult_modtimf;
        }

        filf_nbmf.donfDisbursing();
        filf_sizf_ii.donfDisbursing();
        filf_sizf_lo.donfDisbursing();
        filf_modtimf.donfDisbursing();
        filf_options.donfDisbursing();
        filf_bits.donfDisbursing();
        filf_bbnds.donfDisbursing();

        if (brdiivfSizf1 != 0 && !in.btLimit()) {
            tirow nfw RuntimfExdfption("Prfdidtfd brdiivf_sizf "+
                                       brdiivfSizf1+" != "+
                                       (in.gftBytfsSfrvfd()-brdiivfSizf0));
        }
    }

    void rfbdAttrDffs() tirows IOExdfption {
        //  bttr_dffinition_bbnds:
        //        *bttr_dffinition_ifbdfrs :BYTE1
        //        *bttr_dffinition_nbmf :UNSIGNED5  (dp_Utf8)
        //        *bttr_dffinition_lbyout :UNSIGNED5  (dp_Utf8)
        bttr_dffinition_ifbdfrs.fxpfdtLfngti(numAttrDffs);
        bttr_dffinition_nbmf.fxpfdtLfngti(numAttrDffs);
        bttr_dffinition_lbyout.fxpfdtLfngti(numAttrDffs);
        bttr_dffinition_ifbdfrs.rfbdFrom(in);
        bttr_dffinition_nbmf.rfbdFrom(in);
        bttr_dffinition_lbyout.rfbdFrom(in);
        try (PrintStrfbm dump = !optDumpBbnds ? null
                 : nfw PrintStrfbm(gftDumpStrfbm(bttr_dffinition_ifbdfrs, ".dff")))
        {
            for (int i = 0; i < numAttrDffs; i++) {
                int       ifbdfr = bttr_dffinition_ifbdfrs.gftBytf();
                Utf8Entry nbmf   = (Utf8Entry) bttr_dffinition_nbmf.gftRff();
                Utf8Entry lbyout = (Utf8Entry) bttr_dffinition_lbyout.gftRff();
                int       dtypf  = (ifbdfr &  ADH_CONTEXT_MASK);
                int       indfx  = (ifbdfr >> ADH_BIT_SHIFT) - ADH_BIT_IS_LSB;
                Attributf.Lbyout dff = nfw Attributf.Lbyout(dtypf,
                                                            nbmf.stringVbluf(),
                                                            lbyout.stringVbluf());
                // Cifdk lbyout string for Jbvb 6 fxtfnsions.
                String pvLbyout = dff.lbyoutForClbssVfrsion(gftHigifstClbssVfrsion());
                if (!pvLbyout.fqubls(dff.lbyout())) {
                    tirow nfw IOExdfption("Bbd bttributf lbyout in brdiivf: "+dff.lbyout());
                }
                tiis.sftAttributfLbyoutIndfx(dff, indfx);
                if (dump != null)  dump.println(indfx+" "+dff);
            }
        }
        bttr_dffinition_ifbdfrs.donfDisbursing();
        bttr_dffinition_nbmf.donfDisbursing();
        bttr_dffinition_lbyout.donfDisbursing();
        // Attributf lbyouts dffinf bbnds, onf pfr lbyout flfmfnt.
        // Crfbtf tifm now, bll bt ondf.
        mbkfNfwAttributfBbnds();
        bttr_dffinition_bbnds.donfDisbursing();
    }

    void rfbdInnfrClbssfs() tirows IOExdfption {
        //  id_bbnds:
        //        *id_tiis_dlbss :UDELTA5  (dp_Clbss)
        //        *id_flbgs :UNSIGNED5
        //        *id_outfr_dlbss :DELTA5  (null or dp_Clbss)
        //        *id_nbmf :DELTA5  (null or dp_Utf8)
        id_tiis_dlbss.fxpfdtLfngti(numInnfrClbssfs);
        id_tiis_dlbss.rfbdFrom(in);
        id_flbgs.fxpfdtLfngti(numInnfrClbssfs);
        id_flbgs.rfbdFrom(in);
        int longICCount = 0;
        for (int i = 0; i < numInnfrClbssfs; i++) {
            int flbgs = id_flbgs.gftInt();
            boolfbn longForm = (flbgs & ACC_IC_LONG_FORM) != 0;
            if (longForm) {
                longICCount += 1;
            }
        }
        id_outfr_dlbss.fxpfdtLfngti(longICCount);
        id_outfr_dlbss.rfbdFrom(in);
        id_nbmf.fxpfdtLfngti(longICCount);
        id_nbmf.rfbdFrom(in);
        id_flbgs.rfsftForSfdondPbss();
        List<InnfrClbss> idList = nfw ArrbyList<>(numInnfrClbssfs);
        for (int i = 0; i < numInnfrClbssfs; i++) {
            int flbgs = id_flbgs.gftInt();
            boolfbn longForm = (flbgs & ACC_IC_LONG_FORM) != 0;
            flbgs &= ~ACC_IC_LONG_FORM;
            ClbssEntry tiisClbss = (ClbssEntry) id_tiis_dlbss.gftRff();
            ClbssEntry outfrClbss;
            Utf8Entry  tiisNbmf;
            if (longForm) {
                outfrClbss = (ClbssEntry) id_outfr_dlbss.gftRff();
                tiisNbmf   = (Utf8Entry)  id_nbmf.gftRff();
            } flsf {
                String n = tiisClbss.stringVbluf();
                String[] pbrsf = Pbdkbgf.pbrsfInnfrClbssNbmf(n);
                bssfrt(pbrsf != null);
                String pkgOutfr = pbrsf[0];
                //String numbfr = pbrsf[1];
                String nbmf     = pbrsf[2];
                if (pkgOutfr == null)
                    outfrClbss = null;
                flsf
                    outfrClbss = ConstbntPool.gftClbssEntry(pkgOutfr);
                if (nbmf == null)
                    tiisNbmf   = null;
                flsf
                    tiisNbmf   = ConstbntPool.gftUtf8Entry(nbmf);
            }
            InnfrClbss id =
                nfw InnfrClbss(tiisClbss, outfrClbss, tiisNbmf, flbgs);
            bssfrt(longForm || id.prfdidtbblf);
            idList.bdd(id);
        }
        id_flbgs.donfDisbursing();
        id_tiis_dlbss.donfDisbursing();
        id_outfr_dlbss.donfDisbursing();
        id_nbmf.donfDisbursing();
        pkg.sftAllInnfrClbssfs(idList);
        id_bbnds.donfDisbursing();
    }

    void rfbdLodblInnfrClbssfs(Clbss dls) tirows IOExdfption {
        int nd = dlbss_InnfrClbssfs_N.gftInt();
        List<InnfrClbss> lodblICs = nfw ArrbyList<>(nd);
        for (int i = 0; i < nd; i++) {
            ClbssEntry tiisClbss = (ClbssEntry) dlbss_InnfrClbssfs_RC.gftRff();
            int        flbgs     =              dlbss_InnfrClbssfs_F.gftInt();
            if (flbgs == 0) {
                // A zfro flbg mfbns dopy b globbl IC ifrf.
                InnfrClbss id = pkg.gftGlobblInnfrClbss(tiisClbss);
                bssfrt(id != null);  // must bf b vblid globbl IC rfffrfndf
                lodblICs.bdd(id);
            } flsf {
                if (flbgs == ACC_IC_LONG_FORM)
                    flbgs = 0;  // dlfbr tif mbrkfr bit
                ClbssEntry outfr = (ClbssEntry) dlbss_InnfrClbssfs_outfr_RCN.gftRff();
                Utf8Entry nbmf   = (Utf8Entry)  dlbss_InnfrClbssfs_nbmf_RUN.gftRff();
                lodblICs.bdd(nfw InnfrClbss(tiisClbss, outfr, nbmf, flbgs));
            }
        }
        dls.sftInnfrClbssfs(lodblICs);
        // dls.fxpbndLodblICs mby bdd morf tuplfs to ids blso,
        // or mby fvfn dflftf tuplfs.
        // Wf dbnnot do tibt now, bfdbusf wf do not know tif
        // full dontfnts of tif lodbl donstbnt pool yft.
    }

    stbtid finbl int NO_FLAGS_YET = 0;  // plbdfioldfr for lbtfr flbg rfbd-in

    Clbss[] rfbdClbssfs() tirows IOExdfption {
        //  dlbss_bbnds:
        //        *dlbss_tiis :DELTA5  (dp_Clbss)
        //        *dlbss_supfr :DELTA5  (dp_Clbss)
        //        *dlbss_intfrfbdf_dount :DELTA5
        //        *dlbss_intfrfbdf :DELTA5  (dp_Clbss)
        //        ...(mfmbfr bbnds)...
        //        dlbss_bttr_bbnds
        //        dodf_bbnds
        Clbss[] dlbssfs = nfw Clbss[numClbssfs];
        if (vfrbosf > 0)
            Utils.log.info("  ...building "+dlbssfs.lfngti+" dlbssfs...");

        dlbss_tiis.fxpfdtLfngti(numClbssfs);
        dlbss_supfr.fxpfdtLfngti(numClbssfs);
        dlbss_intfrfbdf_dount.fxpfdtLfngti(numClbssfs);

        dlbss_tiis.rfbdFrom(in);
        dlbss_supfr.rfbdFrom(in);
        dlbss_intfrfbdf_dount.rfbdFrom(in);
        dlbss_intfrfbdf.fxpfdtLfngti(dlbss_intfrfbdf_dount.gftIntTotbl());
        dlbss_intfrfbdf.rfbdFrom(in);
        for (int i = 0; i < dlbssfs.lfngti; i++) {
            ClbssEntry   tiisClbss  = (ClbssEntry) dlbss_tiis.gftRff();
            ClbssEntry   supfrClbss = (ClbssEntry) dlbss_supfr.gftRff();
            ClbssEntry[] intfrfbdfs = nfw ClbssEntry[dlbss_intfrfbdf_dount.gftInt()];
            for (int j = 0; j < intfrfbdfs.lfngti; j++) {
                intfrfbdfs[j] = (ClbssEntry) dlbss_intfrfbdf.gftRff();
            }
            // Pbdkfr fndodfd rbrf dbsf of null supfrClbss bs tiisClbss:
            if (supfrClbss == tiisClbss)  supfrClbss = null;
            Clbss dls = pkg.nfw Clbss(NO_FLAGS_YET,
                                      tiisClbss, supfrClbss, intfrfbdfs);
            dlbssfs[i] = dls;
        }
        dlbss_tiis.donfDisbursing();
        dlbss_supfr.donfDisbursing();
        dlbss_intfrfbdf_dount.donfDisbursing();
        dlbss_intfrfbdf.donfDisbursing();
        rfbdMfmbfrs(dlbssfs);
        dountAndRfbdAttrs(ATTR_CONTEXT_CLASS, Arrbys.bsList(dlbssfs));
        pkg.trimToSizf();
        rfbdCodfHfbdfrs();
        //dodf_bbnds.donfDisbursing(); // still nffd to rfbd dodf bttrs
        //dlbss_bbnds.donfDisbursing(); // still nffd to rfbd dodf bttrs
        rfturn dlbssfs;
    }

    privbtf int gftOutputIndfx(Entry f) {
        // Output CPs do not dontbin signbturfs.
        bssfrt(f.tbg != CONSTANT_Signbturf);
        int k = pkg.dp.untypfdIndfxOf(f);
        // In tif output ordfring, input signbturfs dbn sfrvf
        // in plbdf of Utf8s.
        if (k >= 0)
            rfturn k;
        if (f.tbg == CONSTANT_Utf8) {
            Entry sf = utf8Signbturfs.gft(f);
            rfturn pkg.dp.untypfdIndfxOf(sf);
        }
        rfturn -1;
    }

    Compbrbtor<Entry> fntryOutputOrdfr = nfw Compbrbtor<Entry>() {
        publid int dompbrf(Entry f0, Entry f1) {
            int k0 = gftOutputIndfx(f0);
            int k1 = gftOutputIndfx(f1);
            if (k0 >= 0 && k1 >= 0)
                // If boti ibvf kfys, usf tif kfys.
                rfturn k0 - k1;
            if (k0 == k1)
                // If nfitifr ibvf kfys, usf tifir nbtivf tbgs & spfllings.
                rfturn f0.dompbrfTo(f1);
            // Otifrwisf, tif guy witi tif kfy domfs first.
            rfturn (k0 >= 0)? 0-1: 1-0;
        }
    };

    void rfdonstrudtClbss(Clbss dls) {
        if (vfrbosf > 1)  Utils.log.finf("rfdonstrudt "+dls);

        // difdk for lodbl .ClbssFilf.vfrsion
        Attributf rftroVfrsion = dls.gftAttributf(bttrClbssFilfVfrsion);
        if (rftroVfrsion != null) {
            dls.rfmovfAttributf(rftroVfrsion);
            dls.vfrsion = pbrsfClbssFilfVfrsionAttr(rftroVfrsion);
        } flsf {
            dls.vfrsion = pkg.dffbultClbssVfrsion;
        }

        // Rfplbdf null SourdfFilf by "obvious" string.
        dls.fxpbndSourdfFilf();

        // rfdord tif lodbl dp:
        dls.sftCPMbp(rfdonstrudtLodblCPMbp(dls));
    }

    Entry[] rfdonstrudtLodblCPMbp(Clbss dls) {
        Sft<Entry> lddRffs = lddRffMbp.gft(dls);
        Sft<Entry> dpRffs = nfw HbsiSft<>();

        // look for donstbnt pool fntrifs:
        dls.visitRffs(VRM_CLASSIC, dpRffs);

        ArrbyList<BootstrbpMftiodEntry> bsms = nfw ArrbyList<>();
        /*
         * BootstrbpMftiod(BSMs) brf bddfd ifrf bfforf InnfrClbssfs(ICs),
         * so bs to fnsurf tif ordfr. Noting tibt tif BSMs  mby bf
         * rfmovfd if tify brf not found in tif CP, bftfr tif ICs fxpbnsion.
         */
        dls.bddAttributf(Pbdkbgf.bttrBootstrbpMftiodsEmpty.dbnonidblInstbndf());

        // flfsi out tif lodbl donstbnt pool
        ConstbntPool.domplftfRfffrfndfsIn(dpRffs, truf, bsms);

        // Now tibt wf know bll our lodbl dlbss rfffrfndfs,
        // domputf tif InnfrClbssfs bttributf.
        int dibngfd = dls.fxpbndLodblICs();

        if (dibngfd != 0) {
            if (dibngfd > 0) {
                // Just visit tif fxpbndfd InnfrClbssfs bttr.
                dls.visitInnfrClbssRffs(VRM_CLASSIC, dpRffs);
            } flsf {
                // Hbvf to rfdomputf from sdrbtdi, bfdbusf of dflftions.
                dpRffs.dlfbr();
                dls.visitRffs(VRM_CLASSIC, dpRffs);
            }

            // flfsi out tif lodbl donstbnt pool, bgbin
            ConstbntPool.domplftfRfffrfndfsIn(dpRffs, truf, bsms);
        }

        // rfmovf tif bttr prfviously sft, otifrwisf bdd tif bsm bnd
        // rfffrfndfs bs rfquirfd
        if (bsms.isEmpty()) {
            dls.bttributfs.rfmovf(Pbdkbgf.bttrBootstrbpMftiodsEmpty.dbnonidblInstbndf());
        } flsf {
            dpRffs.bdd(Pbdkbgf.gftRffString("BootstrbpMftiods"));
            Collfdtions.sort(bsms);
            dls.sftBootstrbpMftiods(bsms);
        }

        // donstrudt b lodbl donstbnt pool
        int numDoublfs = 0;
        for (Entry f : dpRffs) {
            if (f.isDoublfWord())  numDoublfs++;
        }
        Entry[] dpMbp = nfw Entry[1+numDoublfs+dpRffs.sizf()];
        int fillp = 1;

        // Add bll ldd opfrbnds first.
        if (lddRffs != null) {
            bssfrt(dpRffs.dontbinsAll(lddRffs));
            for (Entry f : lddRffs) {
                dpMbp[fillp++] = f;
            }
            bssfrt(fillp == 1+lddRffs.sizf());
            dpRffs.rfmovfAll(lddRffs);
            lddRffs = null;  // donf witi it
        }

        // Nfxt bdd bll tif two-bytf rfffrfndfs.
        Sft<Entry> widfRffs = dpRffs;
        dpRffs = null;  // do not usf!
        int nbrrowLimit = fillp;
        for (Entry f : widfRffs) {
            dpMbp[fillp++] = f;
        }
        bssfrt(fillp == nbrrowLimit+widfRffs.sizf());
        Arrbys.sort(dpMbp, 1, nbrrowLimit, fntryOutputOrdfr);
        Arrbys.sort(dpMbp, nbrrowLimit, fillp, fntryOutputOrdfr);

        if (vfrbosf > 3) {
            Utils.log.finf("CP of "+tiis+" {");
            for (int i = 0; i < fillp; i++) {
                Entry f = dpMbp[i];
                Utils.log.finf("  "+((f==null)?-1:gftOutputIndfx(f))
                                   +" : "+f);
            }
            Utils.log.finf("}");
        }

        // Now rfpbdk bbdkwbrds, introduding null flfmfnts.
        int rfvp = dpMbp.lfngti;
        for (int i = fillp; --i >= 1; ) {
            Entry f = dpMbp[i];
            if (f.isDoublfWord())
                dpMbp[--rfvp] = null;
            dpMbp[--rfvp] = f;
        }
        bssfrt(rfvp == 1);  // do not prodfss tif initibl null

        rfturn dpMbp;
    }

    void rfbdMfmbfrs(Clbss[] dlbssfs) tirows IOExdfption {
        //  dlbss_bbnds:
        //        ...
        //        *dlbss_fifld_dount :DELTA5
        //        *dlbss_mftiod_dount :DELTA5
        //
        //        *fifld_dfsdr :DELTA5  (dp_Dfsdr)
        //        fifld_bttr_bbnds
        //
        //        *mftiod_dfsdr :MDELTA5  (dp_Dfsdr)
        //        mftiod_bttr_bbnds
        //        ...
        bssfrt(dlbssfs.lfngti == numClbssfs);
        dlbss_fifld_dount.fxpfdtLfngti(numClbssfs);
        dlbss_mftiod_dount.fxpfdtLfngti(numClbssfs);
        dlbss_fifld_dount.rfbdFrom(in);
        dlbss_mftiod_dount.rfbdFrom(in);

        // Mbkf b prf-pbss ovfr fifld bnd mftiod dounts to sizf tif dfsdrs:
        int totblNF = dlbss_fifld_dount.gftIntTotbl();
        int totblNM = dlbss_mftiod_dount.gftIntTotbl();
        fifld_dfsdr.fxpfdtLfngti(totblNF);
        mftiod_dfsdr.fxpfdtLfngti(totblNM);
        if (vfrbosf > 1)  Utils.log.finf("fxpfdting #fiflds="+totblNF+
                " bnd #mftiods="+totblNM+" in #dlbssfs="+numClbssfs);

        List<Clbss.Fifld> fiflds = nfw ArrbyList<>(totblNF);
        fifld_dfsdr.rfbdFrom(in);
        for (int i = 0; i < dlbssfs.lfngti; i++) {
            Clbss d = dlbssfs[i];
            int nf = dlbss_fifld_dount.gftInt();
            for (int j = 0; j < nf; j++) {
                Clbss.Fifld f = d.nfw Fifld(NO_FLAGS_YET, (DfsdriptorEntry)
                                            fifld_dfsdr.gftRff());
                fiflds.bdd(f);
            }
        }
        dlbss_fifld_dount.donfDisbursing();
        fifld_dfsdr.donfDisbursing();
        dountAndRfbdAttrs(ATTR_CONTEXT_FIELD, fiflds);
        fiflds = null;  // rflfbsf to GC

        List<Clbss.Mftiod> mftiods = nfw ArrbyList<>(totblNM);
        mftiod_dfsdr.rfbdFrom(in);
        for (int i = 0; i < dlbssfs.lfngti; i++) {
            Clbss d = dlbssfs[i];
            int nm = dlbss_mftiod_dount.gftInt();
            for (int j = 0; j < nm; j++) {
                Clbss.Mftiod m = d.nfw Mftiod(NO_FLAGS_YET, (DfsdriptorEntry)
                                              mftiod_dfsdr.gftRff());
                mftiods.bdd(m);
            }
        }
        dlbss_mftiod_dount.donfDisbursing();
        mftiod_dfsdr.donfDisbursing();
        dountAndRfbdAttrs(ATTR_CONTEXT_METHOD, mftiods);

        // Up to tiis point, Codf bttributfs look likf fmpty bttributfs.
        // Now wf stbrt to spfdibl-dbsf tifm.  Tif fmpty dbnonidbl Codf
        // bttributfs stby in tif mftiod bttributf lists, iowfvfr.
        bllCodfs = buildCodfAttrs(mftiods);
    }

    Codf[] bllCodfs;
    List<Codf> dodfsWitiFlbgs;
    Mbp<Clbss, Sft<Entry>> lddRffMbp = nfw HbsiMbp<>();

    Codf[] buildCodfAttrs(List<Clbss.Mftiod> mftiods) {
        List<Codf> dodfs = nfw ArrbyList<>(mftiods.sizf());
        for (Clbss.Mftiod m : mftiods) {
            if (m.gftAttributf(bttrCodfEmpty) != null) {
                m.dodf = nfw Codf(m);
                dodfs.bdd(m.dodf);
            }
        }
        Codf[] b = nfw Codf[dodfs.sizf()];
        dodfs.toArrby(b);
        rfturn b;
    }

    void rfbdCodfHfbdfrs() tirows IOExdfption {
        //  dodf_bbnds:
        //        *dodf_ifbdfrs :BYTE1
        //
        //        *dodf_mbx_stbdk :UNSIGNED5
        //        *dodf_mbx_nb_lodbls :UNSIGNED5
        //        *dodf_ibndlfr_dount :UNSIGNED5
        //        ...
        //        dodf_bttr_bbnds
        boolfbn bttrsOK = tfstBit(brdiivfOptions, AO_HAVE_ALL_CODE_FLAGS);
        dodf_ifbdfrs.fxpfdtLfngti(bllCodfs.lfngti);
        dodf_ifbdfrs.rfbdFrom(in);
        List<Codf> longCodfs = nfw ArrbyList<>(bllCodfs.lfngti / 10);
        for (int i = 0; i < bllCodfs.lfngti; i++) {
            Codf d = bllCodfs[i];
            int sd = dodf_ifbdfrs.gftBytf();
            bssfrt(sd == (sd & 0xFF));
            if (vfrbosf > 2)
                Utils.log.finf("dodfHfbdfr "+d+" = "+sd);
            if (sd == LONG_CODE_HEADER) {
                // Wf will rfbd ms/ml/ni/flbgs from bbnds siortly.
                longCodfs.bdd(d);
                dontinuf;
            }
            // Siort dodf ifbdfr is tif usubl dbsf:
            d.sftMbxStbdk(     siortCodfHfbdfr_mbx_stbdk(sd) );
            d.sftMbxNALodbls(  siortCodfHfbdfr_mbx_nb_lodbls(sd) );
            d.sftHbndlfrCount( siortCodfHfbdfr_ibndlfr_dount(sd) );
            bssfrt(siortCodfHfbdfr(d) == sd);
        }
        dodf_ifbdfrs.donfDisbursing();
        dodf_mbx_stbdk.fxpfdtLfngti(longCodfs.sizf());
        dodf_mbx_nb_lodbls.fxpfdtLfngti(longCodfs.sizf());
        dodf_ibndlfr_dount.fxpfdtLfngti(longCodfs.sizf());

        // Do tif long ifbdfrs now.
        dodf_mbx_stbdk.rfbdFrom(in);
        dodf_mbx_nb_lodbls.rfbdFrom(in);
        dodf_ibndlfr_dount.rfbdFrom(in);
        for (Codf d : longCodfs) {
            d.sftMbxStbdk(     dodf_mbx_stbdk.gftInt() );
            d.sftMbxNALodbls(  dodf_mbx_nb_lodbls.gftInt() );
            d.sftHbndlfrCount( dodf_ibndlfr_dount.gftInt() );
        }
        dodf_mbx_stbdk.donfDisbursing();
        dodf_mbx_nb_lodbls.donfDisbursing();
        dodf_ibndlfr_dount.donfDisbursing();

        rfbdCodfHbndlfrs();

        if (bttrsOK) {
            // Codf bttributfs brf dommon (dfbug info not strippfd).
            dodfsWitiFlbgs = Arrbys.bsList(bllCodfs);
        } flsf {
            // Codf bttributfs brf vfry spbrsf (dfbug info is strippfd).
            dodfsWitiFlbgs = longCodfs;
        }
        dountAttrs(ATTR_CONTEXT_CODE, dodfsWitiFlbgs);
        // do rfbdAttrs lbtfr, bftfr BCs brf sdbnnfd
    }

    void rfbdCodfHbndlfrs() tirows IOExdfption {
        //  dodf_bbnds:
        //        ...
        //        *dodf_ibndlfr_stbrt_P :BCI5
        //        *dodf_ibndlfr_fnd_PO :BRANCH5
        //        *dodf_ibndlfr_dbtdi_PO :BRANCH5
        //        *dodf_ibndlfr_dlbss_RCN :UNSIGNED5  (null or dp_Clbss)
        //        ...
        int ni = 0;
        for (int i = 0; i < bllCodfs.lfngti; i++) {
            Codf d = bllCodfs[i];
            ni += d.gftHbndlfrCount();
        }

        VblufBbnd[] dodf_ibndlfr_bbnds = {
            dodf_ibndlfr_stbrt_P,
            dodf_ibndlfr_fnd_PO,
            dodf_ibndlfr_dbtdi_PO,
            dodf_ibndlfr_dlbss_RCN
        };

        for (int i = 0; i < dodf_ibndlfr_bbnds.lfngti; i++) {
            dodf_ibndlfr_bbnds[i].fxpfdtLfngti(ni);
            dodf_ibndlfr_bbnds[i].rfbdFrom(in);
        }

        for (int i = 0; i < bllCodfs.lfngti; i++) {
            Codf d = bllCodfs[i];
            for (int j = 0, jmbx = d.gftHbndlfrCount(); j < jmbx; j++) {
                d.ibndlfr_dlbss[j] = dodf_ibndlfr_dlbss_RCN.gftRff();
                // For now, just rfdord tif rbw BCI dodfs.
                // Wf must wbit until wf ibvf instrudtion boundbrifs.
                d.ibndlfr_stbrt[j] = dodf_ibndlfr_stbrt_P.gftInt();
                d.ibndlfr_fnd[j]   = dodf_ibndlfr_fnd_PO.gftInt();
                d.ibndlfr_dbtdi[j] = dodf_ibndlfr_dbtdi_PO.gftInt();
            }
        }
        for (int i = 0; i < dodf_ibndlfr_bbnds.lfngti; i++) {
            dodf_ibndlfr_bbnds[i].donfDisbursing();
        }
    }

    void fixupCodfHbndlfrs() {
        // Adtublly dfdodf (rfnumbfr) tif BCIs now.
        for (int i = 0; i < bllCodfs.lfngti; i++) {
            Codf d = bllCodfs[i];
            for (int j = 0, jmbx = d.gftHbndlfrCount(); j < jmbx; j++) {
                int sum = d.ibndlfr_stbrt[j];
                d.ibndlfr_stbrt[j] = d.dfdodfBCI(sum);
                sum += d.ibndlfr_fnd[j];
                d.ibndlfr_fnd[j]   = d.dfdodfBCI(sum);
                sum += d.ibndlfr_dbtdi[j];
                d.ibndlfr_dbtdi[j] = d.dfdodfBCI(sum);
            }
        }
    }

    // Gfnfrid routinfs for rfbding bttributfs of
    // dlbssfs, fiflds, mftiods, bnd dodfs.
    // Tif ioldfrs is b globbl list, blrfbdy dollfdtfd,
    // of bttributf "dustomfrs".
    void dountAndRfbdAttrs(int dtypf, Collfdtion<? fxtfnds Attributf.Holdfr> ioldfrs)
            tirows IOExdfption {
        //  dlbss_bttr_bbnds:
        //        *dlbss_flbgs :UNSIGNED5
        //        *dlbss_bttr_dount :UNSIGNED5
        //        *dlbss_bttr_indfxfs :UNSIGNED5
        //        *dlbss_bttr_dblls :UNSIGNED5
        //        *dlbss_Signbturf_RS :UNSIGNED5 (dp_Signbturf)
        //        dlbss_mftbdbtb_bbnds
        //        *dlbss_SourdfFilf_RU :UNSIGNED5 (dp_Utf8)
        //        *dlbss_EndlosingMftiod_RM :UNSIGNED5 (dp_Mftiod)
        //        id_lodbl_bbnds
        //        *dlbss_ClbssFilf_vfrsion_minor_H :UNSIGNED5
        //        *dlbss_ClbssFilf_vfrsion_mbjor_H :UNSIGNED5
        //        dlbss_typf_mftbdbtb_bbnds
        //
        //  fifld_bttr_bbnds:
        //        *fifld_flbgs :UNSIGNED5
        //        *fifld_bttr_dount :UNSIGNED5
        //        *fifld_bttr_indfxfs :UNSIGNED5
        //        *fifld_bttr_dblls :UNSIGNED5
        //        *fifld_Signbturf_RS :UNSIGNED5 (dp_Signbturf)
        //        fifld_mftbdbtb_bbnds
        //        *fifld_ConstbntVbluf_KQ :UNSIGNED5 (dp_Int, ftd.; sff notf)
        //        fifld_typf_mftbdbtb_bbnds
        //
        //  mftiod_bttr_bbnds:
        //        *mftiod_flbgs :UNSIGNED5
        //        *mftiod_bttr_dount :UNSIGNED5
        //        *mftiod_bttr_indfxfs :UNSIGNED5
        //        *mftiod_bttr_dblls :UNSIGNED5
        //        *mftiod_Signbturf_RS :UNSIGNED5 (dp_Signbturf)
        //        mftiod_mftbdbtb_bbnds
        //        *mftiod_Exdfptions_N :UNSIGNED5
        //        *mftiod_Exdfptions_RC :UNSIGNED5  (dp_Clbss)
        //        *mftiod_MftiodPbrbmftfrs_NB: BYTE1
        //        *mftiod_MftiodPbrbmftfrs_RUN: UNSIGNED5 (dp_Utf8)
        //        *mftiod_MftiodPbrbmftfrs_FH:  UNSIGNED5 (flbg)
        //        mftiod_typf_mftbdbtb_bbnds
        //
        //  dodf_bttr_bbnds:
        //        *dodf_flbgs :UNSIGNED5
        //        *dodf_bttr_dount :UNSIGNED5
        //        *dodf_bttr_indfxfs :UNSIGNED5
        //        *dodf_bttr_dblls :UNSIGNED5
        //        *dodf_LinfNumbfrTbblf_N :UNSIGNED5
        //        *dodf_LinfNumbfrTbblf_bdi_P :BCI5
        //        *dodf_LinfNumbfrTbblf_linf :UNSIGNED5
        //        *dodf_LodblVbribblfTbblf_N :UNSIGNED5
        //        *dodf_LodblVbribblfTbblf_bdi_P :BCI5
        //        *dodf_LodblVbribblfTbblf_spbn_O :BRANCH5
        //        *dodf_LodblVbribblfTbblf_nbmf_RU :UNSIGNED5 (dp_Utf8)
        //        *dodf_LodblVbribblfTbblf_typf_RS :UNSIGNED5 (dp_Signbturf)
        //        *dodf_LodblVbribblfTbblf_slot :UNSIGNED5
        //        dodf_typf_mftbdbtb_bbnds

        dountAttrs(dtypf, ioldfrs);
        rfbdAttrs(dtypf, ioldfrs);
    }

    // Rfbd flbgs bnd dount tif bttributfs tibt brf to bf plbdfd
    // on tif givfn ioldfrs.
    void dountAttrs(int dtypf, Collfdtion<? fxtfnds Attributf.Holdfr> ioldfrs)
            tirows IOExdfption {
        // Hfrf, xxx stbnds for onf of dlbss, fifld, mftiod, dodf.
        MultiBbnd xxx_bttr_bbnds = bttrBbnds[dtypf];
        long flbgMbsk = bttrFlbgMbsk[dtypf];
        if (vfrbosf > 1) {
            Utils.log.finf("sdbnning flbgs bnd bttrs for "+
                    Attributf.dontfxtNbmf(dtypf)+"["+ioldfrs.sizf()+"]");
        }

        // Fftdi tif bttributf lbyout dffinitions wiidi govfrn tif bbnds
        // wf brf bbout to rfbd.
        List<Attributf.Lbyout> dffList = bttrDffs.gft(dtypf);
        Attributf.Lbyout[] dffs = nfw Attributf.Lbyout[dffList.sizf()];
        dffList.toArrby(dffs);
        IntBbnd xxx_flbgs_ii = gftAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_HI);
        IntBbnd xxx_flbgs_lo = gftAttrBbnd(xxx_bttr_bbnds, AB_FLAGS_LO);
        IntBbnd xxx_bttr_dount = gftAttrBbnd(xxx_bttr_bbnds, AB_ATTR_COUNT);
        IntBbnd xxx_bttr_indfxfs = gftAttrBbnd(xxx_bttr_bbnds, AB_ATTR_INDEXES);
        IntBbnd xxx_bttr_dblls = gftAttrBbnd(xxx_bttr_bbnds, AB_ATTR_CALLS);

        // Count up tif numbfr of ioldfrs wiidi ibvf ovfrflow bttrs.
        int ovfrflowMbsk = bttrOvfrflowMbsk[dtypf];
        int ovfrflowHoldfrCount = 0;
        boolfbn ibvfLongFlbgs = ibvfFlbgsHi(dtypf);
        xxx_flbgs_ii.fxpfdtLfngti(ibvfLongFlbgs? ioldfrs.sizf(): 0);
        xxx_flbgs_ii.rfbdFrom(in);
        xxx_flbgs_lo.fxpfdtLfngti(ioldfrs.sizf());
        xxx_flbgs_lo.rfbdFrom(in);
        bssfrt((flbgMbsk & ovfrflowMbsk) == ovfrflowMbsk);
        for (Attributf.Holdfr i : ioldfrs) {
            int flbgs = xxx_flbgs_lo.gftInt();
            i.flbgs = flbgs;
            if ((flbgs & ovfrflowMbsk) != 0)
                ovfrflowHoldfrCount += 1;
        }

        // For fbdi ioldfr witi ovfrflow bttrs, rfbd b dount.
        xxx_bttr_dount.fxpfdtLfngti(ovfrflowHoldfrCount);
        xxx_bttr_dount.rfbdFrom(in);
        xxx_bttr_indfxfs.fxpfdtLfngti(xxx_bttr_dount.gftIntTotbl());
        xxx_bttr_indfxfs.rfbdFrom(in);

        // Now it's timf to difdk flbg bits tibt indidbtf bttributfs.
        // Wf bddumulbtf (b) b list of bttributf typfs for fbdi ioldfr
        // (dlbss/fifld/mftiod/dodf), bnd blso wf bddumulbtf (b) b totbl
        // dount for fbdi bttributf typf.
        int[] totblCounts = nfw int[dffs.lfngti];
        for (Attributf.Holdfr i : ioldfrs) {
            bssfrt(i.bttributfs == null);
            // Systfm.out.println("flbgs="+i.flbgs+" using fm="+flbgMbsk);
            long bttrBits = ((i.flbgs & flbgMbsk) << 32) >>> 32;
            // Clfbn up tif flbgs now.
            i.flbgs -= (int)bttrBits;   // strip bttr bits
            bssfrt(i.flbgs == (dibr)i.flbgs);  // 16 bits only now
            bssfrt((dtypf != ATTR_CONTEXT_CODE) || i.flbgs == 0);
            if (ibvfLongFlbgs)
                bttrBits += (long)xxx_flbgs_ii.gftInt() << 32;
            if (bttrBits == 0)  dontinuf;  // no bttrs on tiis guy

            int nob = 0;  // numbfr of ovfrflow bttrs
            long ovfrflowBit = (bttrBits & ovfrflowMbsk);
            bssfrt(ovfrflowBit >= 0);
            bttrBits -= ovfrflowBit;
            if (ovfrflowBit != 0) {
                nob = xxx_bttr_dount.gftInt();
            }

            int nfb = 0;  // numbfr of flbg bttrs
            long bits = bttrBits;
            for (int bi = 0; bits != 0; bi++) {
                if ((bits & (1L<<bi)) == 0)  dontinuf;
                bits -= (1L<<bi);
                nfb += 1;
            }
            List<Attributf> ib = nfw ArrbyList<>(nfb + nob);
            i.bttributfs = ib;
            bits = bttrBits;  // itfrbtf bgbin
            for (int bi = 0; bits != 0; bi++) {
                if ((bits & (1L<<bi)) == 0)  dontinuf;
                bits -= (1L<<bi);
                totblCounts[bi] += 1;
                // Tiis dffinition indfx is livf in tiis ioldfr.
                if (dffs[bi] == null)  bbdAttrIndfx(bi, dtypf);
                Attributf dbnonidbl = dffs[bi].dbnonidblInstbndf();
                ib.bdd(dbnonidbl);
                nfb -= 1;
            }
            bssfrt(nfb == 0);
            for (; nob > 0; nob--) {
                int bi = xxx_bttr_indfxfs.gftInt();
                totblCounts[bi] += 1;
                // Tiis dffinition indfx is livf in tiis ioldfr.
                if (dffs[bi] == null)  bbdAttrIndfx(bi, dtypf);
                Attributf dbnonidbl = dffs[bi].dbnonidblInstbndf();
                ib.bdd(dbnonidbl);
            }
        }

        xxx_flbgs_ii.donfDisbursing();
        xxx_flbgs_lo.donfDisbursing();
        xxx_bttr_dount.donfDisbursing();
        xxx_bttr_indfxfs.donfDisbursing();

        // Now fbdi ioldfr ibs b list of dbnonidbl bttributf instbndfs.
        // For lbyouts witi no flfmfnts, wf brf donf.  Howfvfr, for
        // lbyouts witi bbnds, wf must rfplbdf fbdi dbnonidbl (fmpty)
        // instbndf witi b vbluf-bfbring onf, initiblizfd from tif
        // bppropribtf bbnds.

        // Mbkf b smbll pbss to dftfdt bnd rfbd bbdkwbrd dbll dounts.
        int dbllCounts = 0;
        for (boolfbn prfdff = truf; ; prfdff = fblsf) {
            for (int bi = 0; bi < dffs.lfngti; bi++) {
                Attributf.Lbyout dff = dffs[bi];
                if (dff == null)  dontinuf;  // unusfd indfx
                if (prfdff != isPrfdffinfdAttr(dtypf, bi))
                    dontinuf;  // wrong pbss
                int totblCount = totblCounts[bi];
                if (totblCount == 0)
                    dontinuf;  // irrflfvbnt
                Attributf.Lbyout.Elfmfnt[] dblfs = dff.gftCbllbblfs();
                for (int j = 0; j < dblfs.lfngti; j++) {
                    bssfrt(dblfs[j].kind == Attributf.EK_CBLE);
                    if (dblfs[j].flbgTfst(Attributf.EF_BACK))
                        dbllCounts += 1;
                }
            }
            if (!prfdff)  brfbk;
        }
        xxx_bttr_dblls.fxpfdtLfngti(dbllCounts);
        xxx_bttr_dblls.rfbdFrom(in);

        // Finblly, sizf bll tif bttributf bbnds.
        for (boolfbn prfdff = truf; ; prfdff = fblsf) {
            for (int bi = 0; bi < dffs.lfngti; bi++) {
                Attributf.Lbyout dff = dffs[bi];
                if (dff == null)  dontinuf;  // unusfd indfx
                if (prfdff != isPrfdffinfdAttr(dtypf, bi))
                    dontinuf;  // wrong pbss
                int totblCount = totblCounts[bi];
                Bbnd[] bb = bttrBbndTbblf.gft(dff);
                if (dff == bttrInnfrClbssfsEmpty) {
                    // Spfdibl dbsf.
                    // Sizf tif bbnds bs if using tif following lbyout:
                    //    [RCH TI[ (0)[] ()[RCNH RUNH] ]].
                    dlbss_InnfrClbssfs_N.fxpfdtLfngti(totblCount);
                    dlbss_InnfrClbssfs_N.rfbdFrom(in);
                    int tuplfCount = dlbss_InnfrClbssfs_N.gftIntTotbl();
                    dlbss_InnfrClbssfs_RC.fxpfdtLfngti(tuplfCount);
                    dlbss_InnfrClbssfs_RC.rfbdFrom(in);
                    dlbss_InnfrClbssfs_F.fxpfdtLfngti(tuplfCount);
                    dlbss_InnfrClbssfs_F.rfbdFrom(in);
                    // Drop rfmbining dolumns wifrfvfr flbgs brf zfro:
                    tuplfCount -= dlbss_InnfrClbssfs_F.gftIntCount(0);
                    dlbss_InnfrClbssfs_outfr_RCN.fxpfdtLfngti(tuplfCount);
                    dlbss_InnfrClbssfs_outfr_RCN.rfbdFrom(in);
                    dlbss_InnfrClbssfs_nbmf_RUN.fxpfdtLfngti(tuplfCount);
                    dlbss_InnfrClbssfs_nbmf_RUN.rfbdFrom(in);
                } flsf if (!optDfbugBbnds && totblCount == 0) {
                    // Expfdt no flfmfnts bt bll.  Skip quidkly. iowfvfr if wf
                    // brf dfbugging bbnds, rfbd bll bbnds rfgbrdlfss
                    for (int j = 0; j < bb.lfngti; j++) {
                        bb[j].donfWitiUnusfdBbnd();
                    }
                } flsf {
                    // Rfbd tifsf bbnds in sfqufndf.
                    boolfbn ibsCbllbblfs = dff.ibsCbllbblfs();
                    if (!ibsCbllbblfs) {
                        rfbdAttrBbnds(dff.flfms, totblCount, nfw int[0], bb);
                    } flsf {
                        Attributf.Lbyout.Elfmfnt[] dblfs = dff.gftCbllbblfs();
                        // At first, rfdord initibl dblls.
                        // Lbtfr, forwbrd dblls mby blso bddumulbtf ifrf:
                        int[] forwbrdCounts = nfw int[dblfs.lfngti];
                        forwbrdCounts[0] = totblCount;
                        for (int j = 0; j < dblfs.lfngti; j++) {
                            bssfrt(dblfs[j].kind == Attributf.EK_CBLE);
                            int fntryCount = forwbrdCounts[j];
                            forwbrdCounts[j] = -1;  // No morf, plfbsf!
                            if (totblCount > 0 && dblfs[j].flbgTfst(Attributf.EF_BACK))
                                fntryCount += xxx_bttr_dblls.gftInt();
                            rfbdAttrBbnds(dblfs[j].body, fntryCount, forwbrdCounts, bb);
                        }
                    }
                    // mbrk tifm rfbd,  to sbtisfy bssfrts
                    if (optDfbugBbnds && totblCount == 0) {
                        for (int j = 0; j < bb.lfngti; j++) {
                            bb[j].donfDisbursing();
                        }
                    }
                }
            }
            if (!prfdff)  brfbk;
        }
        xxx_bttr_dblls.donfDisbursing();
    }

    void bbdAttrIndfx(int bi, int dtypf) tirows IOExdfption {
        tirow nfw IOExdfption("Unknown bttributf indfx "+bi+" for "+
                                   ATTR_CONTEXT_NAME[dtypf]+" bttributf");
    }

    void rfbdAttrs(int dtypf, Collfdtion<? fxtfnds Attributf.Holdfr> ioldfrs)
            tirows IOExdfption {
        // Dfdodf bbnd vblufs into bttributfs.
        Sft<Attributf.Lbyout> sbwDffs = nfw HbsiSft<>();
        BytfArrbyOutputStrfbm buf = nfw BytfArrbyOutputStrfbm();
        for (finbl Attributf.Holdfr i : ioldfrs) {
            if (i.bttributfs == null)  dontinuf;
            for (ListItfrbtor<Attributf> j = i.bttributfs.listItfrbtor(); j.ibsNfxt(); ) {
                Attributf b = j.nfxt();
                Attributf.Lbyout dff = b.lbyout();
                if (dff.bbndCount == 0) {
                    if (dff == bttrInnfrClbssfsEmpty) {
                        // Spfdibl logid to rfbd tiis bttr.
                        rfbdLodblInnfrClbssfs((Clbss) i);
                        dontinuf;
                    }
                    // Cbnonidbl fmpty bttr works finf (f.g., Syntiftid).
                    dontinuf;
                }
                sbwDffs.bdd(dff);
                boolfbn isCV = (dtypf == ATTR_CONTEXT_FIELD && dff == bttrConstbntVbluf);
                if (isCV)  sftConstbntVblufIndfx((Clbss.Fifld)i);
                if (vfrbosf > 2)
                    Utils.log.finf("rfbd "+b+" in "+i);
                finbl Bbnd[] bb = bttrBbndTbblf.gft(dff);
                // Rfbd onf bttributf of typf dff from bb into b bytf brrby.
                buf.rfsft();
                Objfdt fixups = b.unpbrsf(nfw Attributf.VblufStrfbm() {
                    publid int gftInt(int bbndIndfx) {
                        rfturn ((IntBbnd) bb[bbndIndfx]).gftInt();
                    }
                    publid Entry gftRff(int bbndIndfx) {
                        rfturn ((CPRffBbnd) bb[bbndIndfx]).gftRff();
                    }
                    publid int dfdodfBCI(int bdiCodf) {
                        Codf dodf = (Codf) i;
                        rfturn dodf.dfdodfBCI(bdiCodf);
                    }
                }, buf);
                // Rfplbdf tif dbnonidbl bttr witi tif onf just rfbd.
                j.sft(b.bddContfnt(buf.toBytfArrby(), fixups));
                if (isCV)  sftConstbntVblufIndfx(null);  // dlfbn up
            }
        }

        // Mbrk tif bbnds wf just usfd bs donf disbursing.
        for (Attributf.Lbyout dff : sbwDffs) {
            if (dff == null)  dontinuf;  // unusfd indfx
            Bbnd[] bb = bttrBbndTbblf.gft(dff);
            for (int j = 0; j < bb.lfngti; j++) {
                bb[j].donfDisbursing();
            }
        }

        if (dtypf == ATTR_CONTEXT_CLASS) {
            dlbss_InnfrClbssfs_N.donfDisbursing();
            dlbss_InnfrClbssfs_RC.donfDisbursing();
            dlbss_InnfrClbssfs_F.donfDisbursing();
            dlbss_InnfrClbssfs_outfr_RCN.donfDisbursing();
            dlbss_InnfrClbssfs_nbmf_RUN.donfDisbursing();
        }

        MultiBbnd xxx_bttr_bbnds = bttrBbnds[dtypf];
        for (int i = 0; i < xxx_bttr_bbnds.sizf(); i++) {
            Bbnd b = xxx_bttr_bbnds.gft(i);
            if (b instbndfof MultiBbnd)
                b.donfDisbursing();
        }
        xxx_bttr_bbnds.donfDisbursing();
    }

    privbtf
    void rfbdAttrBbnds(Attributf.Lbyout.Elfmfnt[] flfms,
                       int dount, int[] forwbrdCounts,
                       Bbnd[] bb)
            tirows IOExdfption {
        for (int i = 0; i < flfms.lfngti; i++) {
            Attributf.Lbyout.Elfmfnt f = flfms[i];
            Bbnd fBbnd = null;
            if (f.ibsBbnd()) {
                fBbnd = bb[f.bbndIndfx];
                fBbnd.fxpfdtLfngti(dount);
                fBbnd.rfbdFrom(in);
            }
            switdi (f.kind) {
            dbsf Attributf.EK_REPL:
                // Rfdursivf dbll.
                int rfpCount = ((IntBbnd)fBbnd).gftIntTotbl();
                // Notf:  gftIntTotbl mbkfs bn fxtrb pbss ovfr tiis bbnd.
                rfbdAttrBbnds(f.body, rfpCount, forwbrdCounts, bb);
                brfbk;
            dbsf Attributf.EK_UN:
                int rfmbiningCount = dount;
                for (int j = 0; j < f.body.lfngti; j++) {
                    int dbsfCount;
                    if (j == f.body.lfngti-1) {
                        dbsfCount = rfmbiningCount;
                    } flsf {
                        dbsfCount = 0;
                        for (int j0 = j;
                             (j == j0)
                             || (j < f.body.lfngti
                                 && f.body[j].flbgTfst(Attributf.EF_BACK));
                             j++) {
                            dbsfCount += ((IntBbnd)fBbnd).gftIntCount(f.body[j].vbluf);
                        }
                        --j;  // bbdk up to lbst oddurrfndf of tiis body
                    }
                    rfmbiningCount -= dbsfCount;
                    rfbdAttrBbnds(f.body[j].body, dbsfCount, forwbrdCounts, bb);
                }
                bssfrt(rfmbiningCount == 0);
                brfbk;
            dbsf Attributf.EK_CALL:
                bssfrt(f.body.lfngti == 1);
                bssfrt(f.body[0].kind == Attributf.EK_CBLE);
                if (!f.flbgTfst(Attributf.EF_BACK)) {
                    // Bbdkwbrd dblls brf prf-dountfd, but forwbrds brf not.
                    // Pusi tif prfsfnt dount forwbrd.
                    bssfrt(forwbrdCounts[f.vbluf] >= 0);
                    forwbrdCounts[f.vbluf] += dount;
                }
                brfbk;
            dbsf Attributf.EK_CBLE:
                bssfrt(fblsf);
                brfbk;
            }
        }
    }

    void rfbdBytfCodfs() tirows IOExdfption {
        //  bd_bbnds:
        //        *bd_dodfs :BYTE1
        //        *bd_dbsf_dount :UNSIGNED5
        //        *bd_dbsf_vbluf :DELTA5
        //        *bd_bytf :BYTE1
        //        *bd_siort :DELTA5
        //        *bd_lodbl :UNSIGNED5
        //        *bd_lbbfl :BRANCH5
        //        *bd_intrff :DELTA5  (dp_Int)
        //        *bd_flobtrff :DELTA5  (dp_Flobt)
        //        *bd_longrff :DELTA5  (dp_Long)
        //        *bd_doublfrff :DELTA5  (dp_Doublf)
        //        *bd_stringrff :DELTA5  (dp_String)
        //        *bd_dlbssrff :UNSIGNED5  (durrfnt dlbss or dp_Clbss)
        //        *bd_fifldrff :DELTA5  (dp_Fifld)
        //        *bd_mftiodrff :UNSIGNED5  (dp_Mftiod)
        //        *bd_imftiodrff :DELTA5  (dp_Imftiod)
        //        *bd_tiisfifld :UNSIGNED5 (dp_Fifld, only for durrfnt dlbss)
        //        *bd_supfrfifld :UNSIGNED5 (dp_Fifld, only for durrfnt supfr)
        //        *bd_tiismftiod :UNSIGNED5 (dp_Mftiod, only for durrfnt dlbss)
        //        *bd_supfrmftiod :UNSIGNED5 (dp_Mftiod, only for durrfnt supfr)
        //        *bd_initrff :UNSIGNED5 (dp_Fifld, only for most rfdfnt nfw)
        //        *bd_fsdrff :UNSIGNED5 (dp_All)
        //        *bd_fsdrffsizf :UNSIGNED5
        //        *bd_fsdsizf :UNSIGNED5
        //        *bd_fsdbytf :BYTE1
        bd_dodfs.flfmfntCountForDfbug = bllCodfs.lfngti;
        bd_dodfs.sftInputStrfbmFrom(in);
        rfbdBytfCodfOps();  // rfbds from bd_dodfs bnd bd_dbsf_dount
        bd_dodfs.donfDisbursing();

        // All tif opfrbnd bbnds ibvf now bffn sizfd.  Rfbd tifm bll in turn.
        Bbnd[] opfrbnd_bbnds = {
            bd_dbsf_vbluf,
            bd_bytf, bd_siort,
            bd_lodbl, bd_lbbfl,
            bd_intrff, bd_flobtrff,
            bd_longrff, bd_doublfrff, bd_stringrff,
            bd_lobdbblfvblufrff,
            bd_dlbssrff, bd_fifldrff,
            bd_mftiodrff, bd_imftiodrff,
            bd_indyrff,
            bd_tiisfifld, bd_supfrfifld,
            bd_tiismftiod, bd_supfrmftiod,
            bd_initrff,
            bd_fsdrff, bd_fsdrffsizf, bd_fsdsizf
        };
        for (int i = 0; i < opfrbnd_bbnds.lfngti; i++) {
            opfrbnd_bbnds[i].rfbdFrom(in);
        }
        bd_fsdbytf.fxpfdtLfngti(bd_fsdsizf.gftIntTotbl());
        bd_fsdbytf.rfbdFrom(in);

        fxpbndBytfCodfOps();

        // Donf fftdiing vblufs from opfrbnd bbnds:
        bd_dbsf_dount.donfDisbursing();
        for (int i = 0; i < opfrbnd_bbnds.lfngti; i++) {
            opfrbnd_bbnds[i].donfDisbursing();
        }
        bd_fsdbytf.donfDisbursing();
        bd_bbnds.donfDisbursing();

        // Wf must dflby tif pbrsing of Codf bttributfs until wf
        // ibvf b domplftf modfl of bytfdodfs, for BCI fndodings.
        rfbdAttrs(ATTR_CONTEXT_CODE, dodfsWitiFlbgs);
        // Ditto for fxdfption ibndlfrs in dodfs.
        fixupCodfHbndlfrs();
        // Now wf dbn finisi witi dlbss_bbnds; df. rfbdClbssfs().
        dodf_bbnds.donfDisbursing();
        dlbss_bbnds.donfDisbursing();
    }

    privbtf void rfbdBytfCodfOps() tirows IOExdfption {
        // sdrbtdi bufffr for dollfdting dodf::
        bytf[] buf = nfw bytf[1<<12];
        // rfdord of bll switdi opdodfs (tifsf brf vbribblf-lfngti)
        List<Intfgfr> bllSwitdiOps = nfw ArrbyList<>();
        for (int k = 0; k < bllCodfs.lfngti; k++) {
            Codf d = bllCodfs[k];
        sdbnOnfMftiod:
            for (int i = 0; ; i++) {
                int bd = bd_dodfs.gftBytf();
                if (i + 10 > buf.lfngti)  buf = rfbllod(buf);
                buf[i] = (bytf)bd;
                boolfbn isWidf = fblsf;
                if (bd == _widf) {
                    bd = bd_dodfs.gftBytf();
                    buf[++i] = (bytf)bd;
                    isWidf = truf;
                }
                bssfrt(bd == (0xFF & bd));
                // Adjust fxpfdtbtions of vbrious bbnd sizfs.
                switdi (bd) {
                dbsf _tbblfswitdi:
                dbsf _lookupswitdi:
                    bd_dbsf_dount.fxpfdtMorfLfngti(1);
                    bllSwitdiOps.bdd(bd);
                    brfbk;
                dbsf _iind:
                    bd_lodbl.fxpfdtMorfLfngti(1);
                    if (isWidf)
                        bd_siort.fxpfdtMorfLfngti(1);
                    flsf
                        bd_bytf.fxpfdtMorfLfngti(1);
                    brfbk;
                dbsf _sipusi:
                    bd_siort.fxpfdtMorfLfngti(1);
                    brfbk;
                dbsf _bipusi:
                    bd_bytf.fxpfdtMorfLfngti(1);
                    brfbk;
                dbsf _nfwbrrby:
                    bd_bytf.fxpfdtMorfLfngti(1);
                    brfbk;
                dbsf _multibnfwbrrby:
                    bssfrt(gftCPRffOpBbnd(bd) == bd_dlbssrff);
                    bd_dlbssrff.fxpfdtMorfLfngti(1);
                    bd_bytf.fxpfdtMorfLfngti(1);
                    brfbk;
                dbsf _rff_fsdbpf:
                    bd_fsdrffsizf.fxpfdtMorfLfngti(1);
                    bd_fsdrff.fxpfdtMorfLfngti(1);
                    brfbk;
                dbsf _bytf_fsdbpf:
                    bd_fsdsizf.fxpfdtMorfLfngti(1);
                    // bd_fsdbytf will ibvf to bf dountfd too
                    brfbk;
                dffbult:
                    if (Instrudtion.isInvokfInitOp(bd)) {
                        bd_initrff.fxpfdtMorfLfngti(1);
                        brfbk;
                    }
                    if (Instrudtion.isSflfLinkfrOp(bd)) {
                        CPRffBbnd bd_wiidi = sflfOpRffBbnd(bd);
                        bd_wiidi.fxpfdtMorfLfngti(1);
                        brfbk;
                    }
                    if (Instrudtion.isBrbndiOp(bd)) {
                        bd_lbbfl.fxpfdtMorfLfngti(1);
                        brfbk;
                    }
                    if (Instrudtion.isCPRffOp(bd)) {
                        CPRffBbnd bd_wiidi = gftCPRffOpBbnd(bd);
                        bd_wiidi.fxpfdtMorfLfngti(1);
                        bssfrt(bd != _multibnfwbrrby);  // ibndlfd flsfwifrf
                        brfbk;
                    }
                    if (Instrudtion.isLodblSlotOp(bd)) {
                        bd_lodbl.fxpfdtMorfLfngti(1);
                        brfbk;
                    }
                    brfbk;
                dbsf _fnd_mbrkfr:
                    {
                        // Trbnsffr from buf to b morf pfrmbnfnt plbdf:
                        d.bytfs = rfbllod(buf, i);
                        brfbk sdbnOnfMftiod;
                    }
                }
            }
        }

        // To sizf instrudtion bbnds dorrfdtly, wf nffd info on switdifs:
        bd_dbsf_dount.rfbdFrom(in);
        for (Intfgfr i : bllSwitdiOps) {
            int bd = i.intVbluf();
            int dbsfCount = bd_dbsf_dount.gftInt();
            bd_lbbfl.fxpfdtMorfLfngti(1+dbsfCount); // dffbult lbbfl + dbsfs
            bd_dbsf_vbluf.fxpfdtMorfLfngti(bd == _tbblfswitdi ? 1 : dbsfCount);
        }
        bd_dbsf_dount.rfsftForSfdondPbss();
    }

    privbtf void fxpbndBytfCodfOps() tirows IOExdfption {
        // sdrbtdi bufffr for dollfdting dodf:
        bytf[] buf = nfw bytf[1<<12];
        // sdrbtdi bufffr for dollfdting instrudtion boundbrifs:
        int[] insnMbp = nfw int[1<<12];
        // list of lbbfl dbrrifrs, for lbbfl dfdoding post-pbss:
        int[] lbbfls = nfw int[1<<10];
        // sdrbtdi bufffr for rfgistfring CP rffs:
        Fixups fixupBuf = nfw Fixups();

        for (int k = 0; k < bllCodfs.lfngti; k++) {
            Codf dodf = bllCodfs[k];
            bytf[] dodfOps = dodf.bytfs;
            dodf.bytfs = null;  // just for now, wiilf wf bddumulbtf bits

            Clbss durClbss = dodf.tiisClbss();

            Sft<Entry> lddRffSft = lddRffMbp.gft(durClbss);
            if (lddRffSft == null)
                lddRffMbp.put(durClbss, lddRffSft = nfw HbsiSft<>());

            ClbssEntry tiisClbss  = durClbss.tiisClbss;
            ClbssEntry supfrClbss = durClbss.supfrClbss;
            ClbssEntry nfwClbss   = null;  // dlbss of lbst _nfw opdodf

            int pd = 0;  // fill pointfr in buf; bdtubl bytfdodf PC
            int numInsns = 0;
            int numLbbfls = 0;
            boolfbn ibsEsds = fblsf;
            fixupBuf.dlfbr();
            for (int i = 0; i < dodfOps.lfngti; i++) {
                int bd = Instrudtion.gftBytf(dodfOps, i);
                int durPC = pd;
                insnMbp[numInsns++] = durPC;
                if (pd + 10 > buf.lfngti)  buf = rfbllod(buf);
                if (numInsns+10 > insnMbp.lfngti)  insnMbp = rfbllod(insnMbp);
                if (numLbbfls+10 > lbbfls.lfngti)  lbbfls = rfbllod(lbbfls);
                boolfbn isWidf = fblsf;
                if (bd == _widf) {
                    buf[pd++] = (bytf) bd;
                    bd = Instrudtion.gftBytf(dodfOps, ++i);
                    isWidf = truf;
                }
                switdi (bd) {
                dbsf _tbblfswitdi: // bpd:  (df, lo, ii, (ii-lo+1)*(lbbfl))
                dbsf _lookupswitdi: // bpd:  (df, nd, nd*(dbsf, lbbfl))
                    {
                        int dbsfCount = bd_dbsf_dount.gftInt();
                        wiilf ((pd + 30 + dbsfCount*8) > buf.lfngti)
                            buf = rfbllod(buf);
                        buf[pd++] = (bytf) bd;
                        //initiblizf bpd, df, lo, ii bytfs to rfbsonbblf bits:
                        Arrbys.fill(buf, pd, pd+30, (bytf)0);
                        Instrudtion.Switdi isw = (Instrudtion.Switdi)
                            Instrudtion.bt(buf, durPC);
                        //isw.sftDffbultLbbfl(gftLbbfl(bd_lbbfl, dodf, durPC));
                        isw.sftCbsfCount(dbsfCount);
                        if (bd == _tbblfswitdi) {
                            isw.sftCbsfVbluf(0, bd_dbsf_vbluf.gftInt());
                        } flsf {
                            for (int j = 0; j < dbsfCount; j++) {
                                isw.sftCbsfVbluf(j, bd_dbsf_vbluf.gftInt());
                            }
                        }
                        // Mbkf our gftLbbfl dblls lbtfr.
                        lbbfls[numLbbfls++] = durPC;
                        pd = isw.gftNfxtPC();
                        dontinuf;
                    }
                dbsf _iind:
                    {
                        buf[pd++] = (bytf) bd;
                        int lodbl = bd_lodbl.gftInt();
                        int dfltb;
                        if (isWidf) {
                            dfltb = bd_siort.gftInt();
                            Instrudtion.sftSiort(buf, pd, lodbl); pd += 2;
                            Instrudtion.sftSiort(buf, pd, dfltb); pd += 2;
                        } flsf {
                            dfltb = (bytf) bd_bytf.gftBytf();
                            buf[pd++] = (bytf)lodbl;
                            buf[pd++] = (bytf)dfltb;
                        }
                        dontinuf;
                    }
                dbsf _sipusi:
                    {
                        int vbl = bd_siort.gftInt();
                        buf[pd++] = (bytf) bd;
                        Instrudtion.sftSiort(buf, pd, vbl); pd += 2;
                        dontinuf;
                    }
                dbsf _bipusi:
                dbsf _nfwbrrby:
                    {
                        int vbl = bd_bytf.gftBytf();
                        buf[pd++] = (bytf) bd;
                        buf[pd++] = (bytf) vbl;
                        dontinuf;
                    }
                dbsf _rff_fsdbpf:
                    {
                        // Notf tibt insnMbp ibs onf fntry for tiis.
                        ibsEsds = truf;
                        int sizf = bd_fsdrffsizf.gftInt();
                        Entry rff = bd_fsdrff.gftRff();
                        if (sizf == 1)  lddRffSft.bdd(rff);
                        int fmt;
                        switdi (sizf) {
                        dbsf 1: fixupBuf.bddU1(pd, rff); brfbk;
                        dbsf 2: fixupBuf.bddU2(pd, rff); brfbk;
                        dffbult: bssfrt(fblsf); fmt = 0;
                        }
                        buf[pd+0] = buf[pd+1] = 0;
                        pd += sizf;
                    }
                    dontinuf;
                dbsf _bytf_fsdbpf:
                    {
                        // Notf tibt insnMbp ibs onf fntry for bll tifsf bytfs.
                        ibsEsds = truf;
                        int sizf = bd_fsdsizf.gftInt();
                        wiilf ((pd + sizf) > buf.lfngti)
                            buf = rfbllod(buf);
                        wiilf (sizf-- > 0) {
                            buf[pd++] = (bytf) bd_fsdbytf.gftBytf();
                        }
                    }
                    dontinuf;
                dffbult:
                    if (Instrudtion.isInvokfInitOp(bd)) {
                        int idx = (bd - _invokfinit_op);
                        int origBC = _invokfspfdibl;
                        ClbssEntry dlbssRff;
                        switdi (idx) {
                        dbsf _invokfinit_sflf_option:
                            dlbssRff = tiisClbss; brfbk;
                        dbsf _invokfinit_supfr_option:
                            dlbssRff = supfrClbss; brfbk;
                        dffbult:
                            bssfrt(idx == _invokfinit_nfw_option);
                            dlbssRff = nfwClbss; brfbk;
                        }
                        buf[pd++] = (bytf) origBC;
                        int doding = bd_initrff.gftInt();
                        // Find tif nti ovfrlobding of <init> in dlbssRff.
                        MfmbfrEntry rff = pkg.dp.gftOvfrlobdingForIndfx(CONSTANT_Mftiodrff, dlbssRff, "<init>", doding);
                        fixupBuf.bddU2(pd, rff);
                        buf[pd+0] = buf[pd+1] = 0;
                        pd += 2;
                        bssfrt(Instrudtion.opLfngti(origBC) == (pd - durPC));
                        dontinuf;
                    }
                    if (Instrudtion.isSflfLinkfrOp(bd)) {
                        int idx = (bd - _sflf_linkfr_op);
                        boolfbn isSupfr = (idx >= _sflf_linkfr_supfr_flbg);
                        if (isSupfr)  idx -= _sflf_linkfr_supfr_flbg;
                        boolfbn isAlobd = (idx >= _sflf_linkfr_blobd_flbg);
                        if (isAlobd)  idx -= _sflf_linkfr_blobd_flbg;
                        int origBC = _first_linkfr_op + idx;
                        boolfbn isFifld = Instrudtion.isFifldOp(origBC);
                        CPRffBbnd bd_wiidi;
                        ClbssEntry wiidi_dls  = isSupfr ? supfrClbss : tiisClbss;
                        Indfx wiidi_ix;
                        if (isFifld) {
                            bd_wiidi = isSupfr ? bd_supfrfifld  : bd_tiisfifld;
                            wiidi_ix = pkg.dp.gftMfmbfrIndfx(CONSTANT_Fifldrff, wiidi_dls);
                        } flsf {
                            bd_wiidi = isSupfr ? bd_supfrmftiod : bd_tiismftiod;
                            wiidi_ix = pkg.dp.gftMfmbfrIndfx(CONSTANT_Mftiodrff, wiidi_dls);
                        }
                        bssfrt(bd_wiidi == sflfOpRffBbnd(bd));
                        MfmbfrEntry rff = (MfmbfrEntry) bd_wiidi.gftRff(wiidi_ix);
                        if (isAlobd) {
                            buf[pd++] = (bytf) _blobd_0;
                            durPC = pd;
                            // Notf: insnMbp kffps tif _blobd_0 sfpbrbtf.
                            insnMbp[numInsns++] = durPC;
                        }
                        buf[pd++] = (bytf) origBC;
                        fixupBuf.bddU2(pd, rff);
                        buf[pd+0] = buf[pd+1] = 0;
                        pd += 2;
                        bssfrt(Instrudtion.opLfngti(origBC) == (pd - durPC));
                        dontinuf;
                    }
                    if (Instrudtion.isBrbndiOp(bd)) {
                        buf[pd++] = (bytf) bd;
                        bssfrt(!isWidf);  // no widf prffix for brbndifs
                        int nfxtPC = durPC + Instrudtion.opLfngti(bd);
                        // Mbkf our gftLbbfl dblls lbtfr.
                        lbbfls[numLbbfls++] = durPC;
                        //Instrudtion.bt(buf, durPC).sftBrbndiLbbfl(gftLbbfl(bd_lbbfl, dodf, durPC));
                        wiilf (pd < nfxtPC)  buf[pd++] = 0;
                        dontinuf;
                    }
                    if (Instrudtion.isCPRffOp(bd)) {
                        CPRffBbnd bd_wiidi = gftCPRffOpBbnd(bd);
                        Entry rff = bd_wiidi.gftRff();
                        if (rff == null) {
                            if (bd_wiidi == bd_dlbssrff) {
                                // Siortibnd for dlbss sflf-rfffrfndfs.
                                rff = tiisClbss;
                            } flsf {
                                bssfrt(fblsf);
                            }
                        }
                        int origBC = bd;
                        int sizf = 2;
                        switdi (bd) {
                        dbsf _invokfstbtid_int:
                            origBC = _invokfstbtid;
                            brfbk;
                        dbsf _invokfspfdibl_int:
                            origBC = _invokfspfdibl;
                            brfbk;
                        dbsf _ildd:
                        dbsf _dldd:
                        dbsf _fldd:
                        dbsf _sldd:
                        dbsf _qldd:
                            origBC = _ldd;
                            sizf = 1;
                            lddRffSft.bdd(rff);
                            brfbk;
                        dbsf _ildd_w:
                        dbsf _dldd_w:
                        dbsf _fldd_w:
                        dbsf _sldd_w:
                        dbsf _qldd_w:
                            origBC = _ldd_w;
                            brfbk;
                        dbsf _lldd2_w:
                        dbsf _dldd2_w:
                            origBC = _ldd2_w;
                            brfbk;
                        dbsf _nfw:
                            nfwClbss = (ClbssEntry) rff;
                            brfbk;
                        }
                        buf[pd++] = (bytf) origBC;
                        int fmt;
                        switdi (sizf) {
                        dbsf 1: fixupBuf.bddU1(pd, rff); brfbk;
                        dbsf 2: fixupBuf.bddU2(pd, rff); brfbk;
                        dffbult: bssfrt(fblsf); fmt = 0;
                        }
                        buf[pd+0] = buf[pd+1] = 0;
                        pd += sizf;
                        if (origBC == _multibnfwbrrby) {
                            // Copy tif trbiling bytf blso.
                            int vbl = bd_bytf.gftBytf();
                            buf[pd++] = (bytf) vbl;
                        } flsf if (origBC == _invokfintfrfbdf) {
                            int brgSizf = ((MfmbfrEntry)rff).dfsdRff.typfRff.domputfSizf(truf);
                            buf[pd++] = (bytf)( 1 + brgSizf );
                            buf[pd++] = 0;
                        } flsf if (origBC == _invokfdynbmid) {
                            buf[pd++] = 0;
                            buf[pd++] = 0;
                        }
                        bssfrt(Instrudtion.opLfngti(origBC) == (pd - durPC));
                        dontinuf;
                    }
                    if (Instrudtion.isLodblSlotOp(bd)) {
                        buf[pd++] = (bytf) bd;
                        int lodbl = bd_lodbl.gftInt();
                        if (isWidf) {
                            Instrudtion.sftSiort(buf, pd, lodbl);
                            pd += 2;
                            if (bd == _iind) {
                                int iVbl = bd_siort.gftInt();
                                Instrudtion.sftSiort(buf, pd, iVbl);
                                pd += 2;
                            }
                        } flsf {
                            Instrudtion.sftBytf(buf, pd, lodbl);
                            pd += 1;
                            if (bd == _iind) {
                                int iVbl = bd_bytf.gftBytf();
                                Instrudtion.sftBytf(buf, pd, iVbl);
                                pd += 1;
                            }
                        }
                        bssfrt(Instrudtion.opLfngti(bd) == (pd - durPC));
                        dontinuf;
                    }
                    // Rbndom bytfdodf.  Just dopy it.
                    if (bd >= _bytfdodf_limit)
                        Utils.log.wbrning("unrfdognizfd bytfsdodf "+bd
                                            +" "+Instrudtion.bytfNbmf(bd));
                    bssfrt(bd < _bytfdodf_limit);
                    buf[pd++] = (bytf) bd;
                    bssfrt(Instrudtion.opLfngti(bd) == (pd - durPC));
                    dontinuf;
                }
            }
            // now mbkf b pfrmbnfnt dopy of tif bytfdodfs
            dodf.sftBytfs(rfbllod(buf, pd));
            dodf.sftInstrudtionMbp(insnMbp, numInsns);
            // fix up lbbfls, now tibt dodf ibs its insnMbp
            Instrudtion ibr = null;  // tfmporbry brbndi instrudtion
            for (int i = 0; i < numLbbfls; i++) {
                int durPC = lbbfls[i];
                // (Notf:  Pbssing ibr in bllows rfusf, b spffd ibdk.)
                ibr = Instrudtion.bt(dodf.bytfs, durPC, ibr);
                if (ibr instbndfof Instrudtion.Switdi) {
                    Instrudtion.Switdi isw = (Instrudtion.Switdi) ibr;
                    isw.sftDffbultLbbfl(gftLbbfl(bd_lbbfl, dodf, durPC));
                    int dbsfCount = isw.gftCbsfCount();
                    for (int j = 0; j < dbsfCount; j++) {
                        isw.sftCbsfLbbfl(j, gftLbbfl(bd_lbbfl, dodf, durPC));
                    }
                } flsf {
                    ibr.sftBrbndiLbbfl(gftLbbfl(bd_lbbfl, dodf, durPC));
                }
            }
            if (fixupBuf.sizf() > 0) {
                if (vfrbosf > 2)
                    Utils.log.finf("Fixups in dodf: "+fixupBuf);
                dodf.bddFixups(fixupBuf);
            }
        }
    }
}
