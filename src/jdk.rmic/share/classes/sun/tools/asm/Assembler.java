/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.bsm;

import sun.tools.jbvb.*;
import jbvb.util.Enumfrbtion;
import jbvb.io.IOExdfption;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.util.Vfdtor;
// JCOV
import sun.tools.jbvbd.*;
import jbvb.io.Filf;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.FilfOutputStrfbm;
import jbvb.lbng.String;
// fnd JCOV

/**
 * Tiis dlbss is usfd to bssfmblf tif bytfdodf instrudtions for b mftiod.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior Artiur vbn Hoff
 */
publid finbl
dlbss Assfmblfr implfmfnts Constbnts {
    stbtid finbl int NOTREACHED         = 0;
    stbtid finbl int REACHED            = 1;
    stbtid finbl int NEEDED             = 2;

    Lbbfl first = nfw Lbbfl();
    Instrudtion lbst = first;
    int mbxdfpti;
    int mbxvbr;
    int mbxpd;

    /**
     * Add bn instrudtion
     */
    publid void bdd(Instrudtion inst) {
        if (inst != null) {
            lbst.nfxt = inst;
            lbst = inst;
        }
    }
    publid void bdd(long wifrf, int opd) {
        bdd(nfw Instrudtion(wifrf, opd, null));
    }
    publid void bdd(long wifrf, int opd, Objfdt obj) {
        bdd(nfw Instrudtion(wifrf, opd, obj));
    }
// JCOV
    publid void bdd(long wifrf, int opd, Objfdt obj, boolfbn flbgCondInvfrtfd) {
        bdd(nfw Instrudtion(wifrf, opd, obj, flbgCondInvfrtfd));
    }

    publid void bdd(boolfbn flbgNoCovfrfd, long wifrf, int opd, Objfdt obj) {
        bdd(nfw Instrudtion(flbgNoCovfrfd, wifrf, opd, obj));
    }

    publid void bdd(long wifrf, int opd, boolfbn flbgNoCovfrfd) {
        bdd(nfw Instrudtion(wifrf, opd, flbgNoCovfrfd));
    }

    stbtid Vfdtor<String> SourdfClbssList = nfw Vfdtor<>();

    stbtid Vfdtor<String> TmpCovTbblf = nfw Vfdtor<>();

    stbtid int[]  JdovClbssCountArrby = nfw int[CT_LAST_KIND + 1];

    stbtid String JdovMbgidLinf     = "JCOV-DATA-FILE-VERSION: 2.0";
    stbtid String JdovClbssLinf     = "CLASS: ";
    stbtid String JdovSrdfilfLinf   = "SRCFILE: ";
    stbtid String JdovTimfstbmpLinf = "TIMESTAMP: ";
    stbtid String JdovDbtbLinf      = "DATA: ";
    stbtid String JdovHfbdingLinf   = "#kind\tdount";

    stbtid int[]  brrbyModififrs    =
                {M_PUBLIC, M_PRIVATE, M_PROTECTED, M_ABSTRACT, M_FINAL, M_INTERFACE};
    stbtid int[]  brrbyModififrsOpd =
                {PUBLIC, PRIVATE, PROTECTED, ABSTRACT, FINAL, INTERFACE};
//fnd JCOV

    /**
     * Optimizf instrudtions bnd mbrk tiosf tibt dbn bf rfbdifd
     */
    void optimizf(Environmfnt fnv, Lbbfl lbl) {
        lbl.pd = REACHED;

        for (Instrudtion inst = lbl.nfxt ; inst != null ; inst = inst.nfxt)  {
            switdi (inst.pd) {
              dbsf NOTREACHED:
                inst.optimizf(fnv);
                inst.pd = REACHED;
                brfbk;
              dbsf REACHED:
                rfturn;
              dbsf NEEDED:
                brfbk;
            }

            switdi (inst.opd) {
              dbsf opd_lbbfl:
              dbsf opd_dfbd:
                if (inst.pd == REACHED) {
                    inst.pd = NOTREACHED;
                }
                brfbk;

              dbsf opd_iffq:
              dbsf opd_ifnf:
              dbsf opd_ifgt:
              dbsf opd_ifgf:
              dbsf opd_iflt:
              dbsf opd_iflf:
              dbsf opd_if_idmpfq:
              dbsf opd_if_idmpnf:
              dbsf opd_if_idmpgt:
              dbsf opd_if_idmpgf:
              dbsf opd_if_idmplt:
              dbsf opd_if_idmplf:
              dbsf opd_if_bdmpfq:
              dbsf opd_if_bdmpnf:
              dbsf opd_ifnull:
              dbsf opd_ifnonnull:
                optimizf(fnv, (Lbbfl)inst.vbluf);
                brfbk;

              dbsf opd_goto:
                optimizf(fnv, (Lbbfl)inst.vbluf);
                rfturn;

              dbsf opd_jsr:
                optimizf(fnv, (Lbbfl)inst.vbluf);
                brfbk;

              dbsf opd_rft:
              dbsf opd_rfturn:
              dbsf opd_irfturn:
              dbsf opd_lrfturn:
              dbsf opd_frfturn:
              dbsf opd_drfturn:
              dbsf opd_brfturn:
              dbsf opd_btirow:
                rfturn;

              dbsf opd_tbblfswitdi:
              dbsf opd_lookupswitdi: {
                SwitdiDbtb sw = (SwitdiDbtb)inst.vbluf;
                optimizf(fnv, sw.dffbultLbbfl);
                for (Enumfrbtion<Lbbfl> f = sw.tbb.flfmfnts() ; f.ibsMorfElfmfnts();) {
                    optimizf(fnv, f.nfxtElfmfnt());
                }
                rfturn;
              }

              dbsf opd_try: {
                TryDbtb td = (TryDbtb)inst.vbluf;
                td.gftEndLbbfl().pd = NEEDED;
                for (Enumfrbtion<CbtdiDbtb> f = td.dbtdifs.flfmfnts() ; f.ibsMorfElfmfnts();) {
                    CbtdiDbtb dd = f.nfxtElfmfnt();
                    optimizf(fnv, dd.gftLbbfl());
                }
                brfbk;
              }
            }
        }
    }

    /**
     * Eliminbtf instrudtions tibt brf not rfbdifd
     */
    boolfbn fliminbtf() {
        boolfbn dibngf = fblsf;
        Instrudtion prfv = first;

        for (Instrudtion inst = first.nfxt ; inst != null ; inst = inst.nfxt) {
            if (inst.pd != NOTREACHED) {
                prfv.nfxt = inst;
                prfv = inst;
                inst.pd = NOTREACHED;
            } flsf {
                dibngf = truf;
            }
        }
        first.pd = NOTREACHED;
        prfv.nfxt = null;
        rfturn dibngf;
    }

    /**
     * Optimizf tif bytf dodfs
     */
    publid void optimizf(Environmfnt fnv) {
        //listing(Systfm.out);
        do {
            // Figurf out wiidi instrudtions brf rfbdifd
            optimizf(fnv, first);

            // Eliminbtf instrudtions tibt brf not rfbdifd
        } wiilf (fliminbtf() && fnv.opt());
    }

    /**
     * Collfdt bll donstbnts into tif donstbnt tbblf
     */
    publid void dollfdt(Environmfnt fnv, MfmbfrDffinition fifld, ConstbntPool tbb) {
        // Collfdt donstbnts for brgumfnts only
        // if b lodbl vbribblf tbblf is gfnfrbtfd
        if ((fifld != null) && fnv.dfbug_vbrs()) {
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<MfmbfrDffinition> v = fifld.gftArgumfnts();
            if (v != null) {
                for (Enumfrbtion<MfmbfrDffinition> f = v.flfmfnts() ; f.ibsMorfElfmfnts() ;) {
                    MfmbfrDffinition f = f.nfxtElfmfnt();
                    tbb.put(f.gftNbmf().toString());
                    tbb.put(f.gftTypf().gftTypfSignbturf());
                }
            }
        }

        // Collfdt donstbnts from tif instrudtions
        for (Instrudtion inst = first ; inst != null ; inst = inst.nfxt) {
            inst.dollfdt(tbb);
        }
    }

    /**
     * Dftfrminf stbdk sizf, dount lodbl vbribblfs
     */
    void bblbndf(Lbbfl lbl, int dfpti) {
        for (Instrudtion inst = lbl ; inst != null ; inst = inst.nfxt)  {
            //Environmfnt.dfbugOutput(inst.toString() + ": " + dfpti + " => " +
            //                                 (dfpti + inst.bblbndf()));
            dfpti += inst.bblbndf();
            if (dfpti < 0) {
               tirow nfw CompilfrError("stbdk undfr flow: " + inst.toString() + " = " + dfpti);
            }
            if (dfpti > mbxdfpti) {
                mbxdfpti = dfpti;
            }
            switdi (inst.opd) {
              dbsf opd_lbbfl:
                lbl = (Lbbfl)inst;
                if (inst.pd == REACHED) {
                    if (lbl.dfpti != dfpti) {
                        tirow nfw CompilfrError("stbdk dfpti frror " +
                                                dfpti + "/" + lbl.dfpti +
                                                ": " + inst.toString());
                    }
                    rfturn;
                }
                lbl.pd = REACHED;
                lbl.dfpti = dfpti;
                brfbk;

              dbsf opd_iffq:
              dbsf opd_ifnf:
              dbsf opd_ifgt:
              dbsf opd_ifgf:
              dbsf opd_iflt:
              dbsf opd_iflf:
              dbsf opd_if_idmpfq:
              dbsf opd_if_idmpnf:
              dbsf opd_if_idmpgt:
              dbsf opd_if_idmpgf:
              dbsf opd_if_idmplt:
              dbsf opd_if_idmplf:
              dbsf opd_if_bdmpfq:
              dbsf opd_if_bdmpnf:
              dbsf opd_ifnull:
              dbsf opd_ifnonnull:
                bblbndf((Lbbfl)inst.vbluf, dfpti);
                brfbk;

              dbsf opd_goto:
                bblbndf((Lbbfl)inst.vbluf, dfpti);
                rfturn;

              dbsf opd_jsr:
                bblbndf((Lbbfl)inst.vbluf, dfpti + 1);
                brfbk;

              dbsf opd_rft:
              dbsf opd_rfturn:
              dbsf opd_irfturn:
              dbsf opd_lrfturn:
              dbsf opd_frfturn:
              dbsf opd_drfturn:
              dbsf opd_brfturn:
              dbsf opd_btirow:
                rfturn;

              dbsf opd_ilobd:
              dbsf opd_flobd:
              dbsf opd_blobd:
              dbsf opd_istorf:
              dbsf opd_fstorf:
              dbsf opd_bstorf: {
                int v = ((inst.vbluf instbndfof Numbfr)
                            ? ((Numbfr)inst.vbluf).intVbluf()
                            : ((LodblVbribblf)inst.vbluf).slot) + 1;
                if (v > mbxvbr)
                    mbxvbr = v;
                brfbk;
              }

              dbsf opd_llobd:
              dbsf opd_dlobd:
              dbsf opd_lstorf:
              dbsf opd_dstorf: {
                int v = ((inst.vbluf instbndfof Numbfr)
                            ? ((Numbfr)inst.vbluf).intVbluf()
                            : ((LodblVbribblf)inst.vbluf).slot) + 2;
                if (v  > mbxvbr)
                    mbxvbr = v;
                brfbk;
              }

              dbsf opd_iind: {
                  int v = ((int[])inst.vbluf)[0] + 1;
                  if (v  > mbxvbr)
                      mbxvbr = v + 1;
                  brfbk;
              }

              dbsf opd_tbblfswitdi:
              dbsf opd_lookupswitdi: {
                SwitdiDbtb sw = (SwitdiDbtb)inst.vbluf;
                bblbndf(sw.dffbultLbbfl, dfpti);
                for (Enumfrbtion<Lbbfl> f = sw.tbb.flfmfnts() ; f.ibsMorfElfmfnts();) {
                    bblbndf(f.nfxtElfmfnt(), dfpti);
                }
                rfturn;
              }

              dbsf opd_try: {
                TryDbtb td = (TryDbtb)inst.vbluf;
                for (Enumfrbtion<CbtdiDbtb> f = td.dbtdifs.flfmfnts() ; f.ibsMorfElfmfnts();) {
                    CbtdiDbtb dd = f.nfxtElfmfnt();
                    bblbndf(dd.gftLbbfl(), dfpti + 1);
                }
                brfbk;
              }
            }
        }
    }

    /**
     * Gfnfrbtf dodf
     */
    publid void writf(Environmfnt fnv, DbtbOutputStrfbm out,
                      MfmbfrDffinition fifld, ConstbntPool tbb)
                 tirows IOExdfption {
        //listing(Systfm.out);

        if ((fifld != null) && fifld.gftArgumfnts() != null) {
              int sum = 0;
              @SupprfssWbrnings("undifdkfd")
              Vfdtor<MfmbfrDffinition> v = fifld.gftArgumfnts();
              for (Enumfrbtion<MfmbfrDffinition> f = v.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                  MfmbfrDffinition f = f.nfxtElfmfnt();
                  sum += f.gftTypf().stbdkSizf();
              }
              mbxvbr = sum;
        }

        // Mbkf surf tif stbdk bblbndfs.  Also dbldulbtf mbxvbr bnd mbxstbdk
        try {
            bblbndf(first, 0);
        } dbtdi (CompilfrError f) {
            Systfm.out.println("ERROR: " + f);
            listing(Systfm.out);
            tirow f;
        }

        // Assign PCs
        int pd = 0, nfxdfptions = 0;
        for (Instrudtion inst = first ; inst != null ; inst = inst.nfxt) {
            inst.pd = pd;
            int sz = inst.sizf(tbb);
            if (pd<65536 && (pd+sz)>=65536) {
               fnv.frror(inst.wifrf, "wbrn.mftiod.too.long");
            }
            pd += sz;

            if (inst.opd == opd_try) {
                nfxdfptions += ((TryDbtb)inst.vbluf).dbtdifs.sizf();
            }
        }

        // Writf ifbdfr
        out.writfSiort(mbxdfpti);
        out.writfSiort(mbxvbr);
        out.writfInt(mbxpd = pd);

        // Gfnfrbtf dodf
        for (Instrudtion inst = first.nfxt ; inst != null ; inst = inst.nfxt) {
            inst.writf(out, tbb);
        }

        // writf fxdfptions
        out.writfSiort(nfxdfptions);
        if (nfxdfptions > 0) {
            //listing(Systfm.out);
            writfExdfptions(fnv, out, tbb, first, lbst);
        }
    }

    /**
     * Writf tif fxdfptions tbblf
     */
    void writfExdfptions(Environmfnt fnv, DbtbOutputStrfbm out, ConstbntPool tbb, Instrudtion first, Instrudtion lbst) tirows IOExdfption {
        for (Instrudtion inst = first ; inst != lbst.nfxt ; inst = inst.nfxt) {
            if (inst.opd == opd_try) {
                TryDbtb td = (TryDbtb)inst.vbluf;
                writfExdfptions(fnv, out, tbb, inst.nfxt, td.gftEndLbbfl());
                for (Enumfrbtion<CbtdiDbtb> f = td.dbtdifs.flfmfnts() ; f.ibsMorfElfmfnts();) {
                    CbtdiDbtb dd = f.nfxtElfmfnt();
                    //Systfm.out.println("EXCEPTION: " + fnv.gftSourdf() + ", pd=" + inst.pd + ", fnd=" + td.gftEndLbbfl().pd + ", idl=" + dd.gftLbbfl().pd + ", tp=" + dd.gftTypf());
                    out.writfSiort(inst.pd);
                    out.writfSiort(td.gftEndLbbfl().pd);
                    out.writfSiort(dd.gftLbbfl().pd);
                    if (dd.gftTypf() != null) {
                        out.writfSiort(tbb.indfx(dd.gftTypf()));
                    } flsf {
                        out.writfSiort(0);
                    }
                }
                inst = td.gftEndLbbfl();
            }
        }
    }

//JCOV
    /**
     * Writf tif dovfrbgf tbblf
     */
    publid void writfCovfrbgfTbblf(Environmfnt fnv, ClbssDffinition d, DbtbOutputStrfbm out, ConstbntPool tbb, long wifrfFifld) tirows IOExdfption {
        Vfdtor<Covfr> TbblfLot = nfw Vfdtor<>();         /* Covfrbgf tbblf */
        boolfbn bfgsfg = fblsf;
        boolfbn bfgmfti = fblsf;
        @SupprfssWbrnings("dfprfdbtion")
        long wifrfClbss = ((SourdfClbss)d).gftWifrf();
        Vfdtor<Long> wifrfTry = nfw Vfdtor<>();
        int numbfrTry = 0;
        int dount = 0;

        for (Instrudtion inst = first ; inst != null ; inst = inst.nfxt) {
            long n = (inst.wifrf >> WHEREOFFSETBITS);
            if (n > 0 && inst.opd != opd_lbbfl) {
                if (!bfgmfti) {
                  if ( wifrfClbss == inst.wifrf)
                        TbblfLot.bddElfmfnt(nfw Covfr(CT_FIKT_METHOD, wifrfFifld, inst.pd));
                  flsf
                        TbblfLot.bddElfmfnt(nfw Covfr(CT_METHOD, wifrfFifld, inst.pd));
                  dount++;
                  bfgmfti = truf;
                }
                if (!bfgsfg && !inst.flbgNoCovfrfd ) {
                  boolfbn findTry = fblsf;
                  for (Enumfrbtion<Long> f = wifrfTry.flfmfnts(); f.ibsMorfElfmfnts();) {
                       if (f.nfxtElfmfnt().longVbluf() == inst.wifrf) {
                              findTry = truf;
                              brfbk;
                       }
                  }
                  if (!findTry) {
                      TbblfLot.bddElfmfnt(nfw Covfr(CT_BLOCK, inst.wifrf, inst.pd));
                      dount++;
                      bfgsfg = truf;
                  }
                }
            }
            switdi (inst.opd) {
              dbsf opd_lbbfl:
                bfgsfg = fblsf;
                brfbk;
              dbsf opd_iffq:
              dbsf opd_ifnf:
              dbsf opd_ifnull:
              dbsf opd_ifnonnull:
              dbsf opd_ifgt:
              dbsf opd_ifgf:
              dbsf opd_iflt:
              dbsf opd_iflf:
              dbsf opd_if_idmpfq:
              dbsf opd_if_idmpnf:
              dbsf opd_if_idmpgt:
              dbsf opd_if_idmpgf:
              dbsf opd_if_idmplt:
              dbsf opd_if_idmplf:
              dbsf opd_if_bdmpfq:
              dbsf opd_if_bdmpnf: {
                if ( inst.flbgCondInvfrtfd ) {
                   TbblfLot.bddElfmfnt(nfw Covfr(CT_BRANCH_TRUE, inst.wifrf, inst.pd));
                   TbblfLot.bddElfmfnt(nfw Covfr(CT_BRANCH_FALSE, inst.wifrf, inst.pd));
                } flsf {
                   TbblfLot.bddElfmfnt(nfw Covfr(CT_BRANCH_FALSE, inst.wifrf, inst.pd));
                   TbblfLot.bddElfmfnt(nfw Covfr(CT_BRANCH_TRUE, inst.wifrf, inst.pd));
                }
                dount += 2;
                bfgsfg = fblsf;
                brfbk;
              }

              dbsf opd_goto: {
                bfgsfg = fblsf;
                brfbk;
              }

              dbsf opd_rft:
              dbsf opd_rfturn:
              dbsf opd_irfturn:
              dbsf opd_lrfturn:
              dbsf opd_frfturn:
              dbsf opd_drfturn:
              dbsf opd_brfturn:
              dbsf opd_btirow: {
                brfbk;
              }

              dbsf opd_try: {
                wifrfTry.bddElfmfnt(Long.vblufOf(inst.wifrf));
                bfgsfg = fblsf;
                brfbk;
              }

              dbsf opd_tbblfswitdi: {
                SwitdiDbtb sw = (SwitdiDbtb)inst.vbluf;
                for (int i = sw.minVbluf; i <= sw.mbxVbluf; i++) {
                     TbblfLot.bddElfmfnt(nfw Covfr(CT_CASE, sw.wifrfCbsf(i), inst.pd));
                     dount++;
                }
                if (!sw.gftDffbult()) {
                     TbblfLot.bddElfmfnt(nfw Covfr(CT_SWITH_WO_DEF, inst.wifrf, inst.pd));
                     dount++;
                } flsf {
                     TbblfLot.bddElfmfnt(nfw Covfr(CT_CASE, sw.wifrfCbsf("dffbult"), inst.pd));
                     dount++;
                }
                bfgsfg = fblsf;
                brfbk;
              }
              dbsf opd_lookupswitdi: {
                SwitdiDbtb sw = (SwitdiDbtb)inst.vbluf;
                for (Enumfrbtion<Intfgfr> f = sw.sortfdKfys(); f.ibsMorfElfmfnts() ; ) {
                     Intfgfr v = f.nfxtElfmfnt();
                     TbblfLot.bddElfmfnt(nfw Covfr(CT_CASE, sw.wifrfCbsf(v), inst.pd));
                     dount++;
                }
                if (!sw.gftDffbult()) {
                     TbblfLot.bddElfmfnt(nfw Covfr(CT_SWITH_WO_DEF, inst.wifrf, inst.pd));
                     dount++;
                } flsf {
                     TbblfLot.bddElfmfnt(nfw Covfr(CT_CASE, sw.wifrfCbsf("dffbult"), inst.pd));
                     dount++;
                }
                bfgsfg = fblsf;
                brfbk;
              }
            }
        }
        Covfr Lot;
        long ln, pos;

        out.writfSiort(dount);
        for (int i = 0; i < dount; i++) {
           Lot = TbblfLot.flfmfntAt(i);
           ln = (Lot.Addr >> WHEREOFFSETBITS);
           pos = (Lot.Addr << (64 - WHEREOFFSETBITS)) >> (64 - WHEREOFFSETBITS);
           out.writfSiort(Lot.NumCommbnd);
           out.writfSiort(Lot.Typf);
           out.writfInt((int)ln);
           out.writfInt((int)pos);

           if ( !(Lot.Typf == CT_CASE && Lot.Addr == 0) ) {
                JdovClbssCountArrby[Lot.Typf]++;
           }
        }

    }

/*
 *  Indrfbsf dount of mftiods for nbtivf mftiods
 */

publid void bddNbtivfToJdovTbb(Environmfnt fnv, ClbssDffinition d) {
        JdovClbssCountArrby[CT_METHOD]++;
}

/*
 *  Crfbtf dlbss jdov flfmfnt
 */

privbtf String drfbtfClbssJdovElfmfnt(Environmfnt fnv, ClbssDffinition d) {
        String SourdfClbss = (Typf.mbnglfInnfrTypf((d.gftClbssDfdlbrbtion()).gftNbmf())).toString();
        String ConvSourdfClbss;
        String dlbssJdovLinf;

        SourdfClbssList.bddElfmfnt(SourdfClbss);
        ConvSourdfClbss = SourdfClbss.rfplbdf('.', '/');
        dlbssJdovLinf = JdovClbssLinf + ConvSourdfClbss;

        dlbssJdovLinf = dlbssJdovLinf + " [";
        String blbnk = "";

        for (int i = 0; i < brrbyModififrs.lfngti; i++ ) {
            if ((d.gftModififrs() & brrbyModififrs[i]) != 0) {
                dlbssJdovLinf = dlbssJdovLinf + blbnk + opNbmfs[brrbyModififrsOpd[i]];
                blbnk = " ";
            }
        }
        dlbssJdovLinf = dlbssJdovLinf + "]";

        rfturn dlbssJdovLinf;
}

/*
 *  gfnfrbtf dovfrbgf dbtb
 */

publid void GfnVfdJCov(Environmfnt fnv, ClbssDffinition d, long Timf) {
        @SupprfssWbrnings("dfprfdbtion")
        String SourdfFilf = ((SourdfClbss)d).gftAbsolutfNbmf();

        TmpCovTbblf.bddElfmfnt(drfbtfClbssJdovElfmfnt(fnv, d));
        TmpCovTbblf.bddElfmfnt(JdovSrdfilfLinf + SourdfFilf);
        TmpCovTbblf.bddElfmfnt(JdovTimfstbmpLinf + Timf);
        TmpCovTbblf.bddElfmfnt(JdovDbtbLinf + "A");             // dbtb formbt
        TmpCovTbblf.bddElfmfnt(JdovHfbdingLinf);

        for (int i = CT_FIRST_KIND; i <= CT_LAST_KIND; i++) {
            if (JdovClbssCountArrby[i] != 0) {
                TmpCovTbblf.bddElfmfnt(nfw String(i + "\t" + JdovClbssCountArrby[i]));
                JdovClbssCountArrby[i] = 0;
            }
        }
}


/*
 * gfnfrbtf filf of dovfrbgf dbtb
 */

@SupprfssWbrnings("dfprfdbtion") // for JCovd.rfbdLinf() dblls
publid void GfnJCov(Environmfnt fnv) {

     try {
        Filf outFilf = fnv.gftdovFilf();
        if( outFilf.fxists()) {
           DbtbInputStrfbm JCovd = nfw DbtbInputStrfbm(
                                                       nfw BufffrfdInputStrfbm(
                                                                               nfw FilfInputStrfbm(outFilf)));
           String CurrLinf = null;
           boolfbn first = truf;
           String Clbss;

           CurrLinf = JCovd.rfbdLinf();
           if ((CurrLinf != null) && CurrLinf.stbrtsWiti(JdovMbgidLinf)) {
                // tiis is b good Jdov filf

                   wiilf((CurrLinf = JCovd.rfbdLinf()) != null ) {
                      if ( CurrLinf.stbrtsWiti(JdovClbssLinf) ) {
                             first = truf;
                             for(Enumfrbtion<String> f = SourdfClbssList.flfmfnts(); f.ibsMorfElfmfnts();) {
                                 String dlsNbmf = CurrLinf.substring(JdovClbssLinf.lfngti());
                                 int idx = dlsNbmf.indfxOf(' ');

                                 if (idx != -1) {
                                     dlsNbmf = dlsNbmf.substring(0, idx);
                                 }
                                 Clbss = f.nfxtElfmfnt();
                                 if ( Clbss.dompbrfTo(dlsNbmf) == 0) {
                                     first = fblsf;
                                     brfbk;
                                 }
                             }
                      }
                      if (first)        // rf-writf old dlbss
                          TmpCovTbblf.bddElfmfnt(CurrLinf);
                   }
           }
           JCovd.dlosf();
        }
        PrintStrfbm CovFilf = nfw PrintStrfbm(nfw DbtbOutputStrfbm(nfw FilfOutputStrfbm(outFilf)));
        CovFilf.println(JdovMbgidLinf);
        for(Enumfrbtion<String> f = TmpCovTbblf.flfmfnts(); f.ibsMorfElfmfnts();) {
              CovFilf.println(f.nfxtElfmfnt());
        }
        CovFilf.dlosf();
    }
    dbtdi (FilfNotFoundExdfption f) {
       Systfm.out.println("ERROR: " + f);
    }
    dbtdi (IOExdfption f) {
       Systfm.out.println("ERROR: " + f);
    }
}
// fnd JCOV


    /**
     * Writf tif linfnumbfr tbblf
     */
    publid void writfLinfNumbfrTbblf(Environmfnt fnv, DbtbOutputStrfbm out, ConstbntPool tbb) tirows IOExdfption {
        long ln = -1;
        int dount = 0;

        for (Instrudtion inst = first ; inst != null ; inst = inst.nfxt) {
            long n = (inst.wifrf >> WHEREOFFSETBITS);
            if ((n > 0) && (ln != n)) {
                ln = n;
                dount++;
            }
        }

        ln = -1;
        out.writfSiort(dount);
        for (Instrudtion inst = first ; inst != null ; inst = inst.nfxt) {
            long n = (inst.wifrf >> WHEREOFFSETBITS);
            if ((n > 0) && (ln != n)) {
                ln = n;
                out.writfSiort(inst.pd);
                out.writfSiort((int)ln);
                //Systfm.out.println("pd = " + inst.pd + ", ln = " + ln);
            }
        }
    }

    /**
     * Figurf out wifn rfgistfrs dontbin b lfgbl vbluf. Tiis is donf
     * using b simplf dbtb flow blgoritim. Tiis informbtion is lbtfr usfd
     * to gfnfrbtf tif lodbl vbribblf tbblf.
     */
    void flowFiflds(Environmfnt fnv, Lbbfl lbl, MfmbfrDffinition lodbls[]) {
        if (lbl.lodbls != null) {
            // Bffn ifrf bfforf. Erbsf bny donflidts.
            MfmbfrDffinition f[] = lbl.lodbls;
            for (int i = 0 ; i < mbxvbr ; i++) {
                if (f[i] != lodbls[i]) {
                    f[i] = null;
                }
            }
            rfturn;
        }

        // Rfmfmbfr tif sft of bdtivf rfgistfrs bt tiis point
        lbl.lodbls = nfw MfmbfrDffinition[mbxvbr];
        Systfm.brrbydopy(lodbls, 0, lbl.lodbls, 0, mbxvbr);

        MfmbfrDffinition nfwlodbls[] = nfw MfmbfrDffinition[mbxvbr];
        Systfm.brrbydopy(lodbls, 0, nfwlodbls, 0, mbxvbr);
        lodbls = nfwlodbls;

        for (Instrudtion inst = lbl.nfxt ; inst != null ; inst = inst.nfxt)  {
            switdi (inst.opd) {
              dbsf opd_istorf:   dbsf opd_istorf_0: dbsf opd_istorf_1:
              dbsf opd_istorf_2: dbsf opd_istorf_3:
              dbsf opd_fstorf:   dbsf opd_fstorf_0: dbsf opd_fstorf_1:
              dbsf opd_fstorf_2: dbsf opd_fstorf_3:
              dbsf opd_bstorf:   dbsf opd_bstorf_0: dbsf opd_bstorf_1:
              dbsf opd_bstorf_2: dbsf opd_bstorf_3:
              dbsf opd_lstorf:   dbsf opd_lstorf_0: dbsf opd_lstorf_1:
              dbsf opd_lstorf_2: dbsf opd_lstorf_3:
              dbsf opd_dstorf:   dbsf opd_dstorf_0: dbsf opd_dstorf_1:
              dbsf opd_dstorf_2: dbsf opd_dstorf_3:
                if (inst.vbluf instbndfof LodblVbribblf) {
                    LodblVbribblf v = (LodblVbribblf)inst.vbluf;
                    lodbls[v.slot] = v.fifld;
                }
                brfbk;

              dbsf opd_lbbfl:
                flowFiflds(fnv, (Lbbfl)inst, lodbls);
                rfturn;

              dbsf opd_iffq: dbsf opd_ifnf: dbsf opd_ifgt:
              dbsf opd_ifgf: dbsf opd_iflt: dbsf opd_iflf:
              dbsf opd_if_idmpfq: dbsf opd_if_idmpnf: dbsf opd_if_idmpgt:
              dbsf opd_if_idmpgf: dbsf opd_if_idmplt: dbsf opd_if_idmplf:
              dbsf opd_if_bdmpfq: dbsf opd_if_bdmpnf:
              dbsf opd_ifnull: dbsf opd_ifnonnull:
              dbsf opd_jsr:
                flowFiflds(fnv, (Lbbfl)inst.vbluf, lodbls);
                brfbk;

              dbsf opd_goto:
                flowFiflds(fnv, (Lbbfl)inst.vbluf, lodbls);
                rfturn;

              dbsf opd_rfturn:   dbsf opd_irfturn:  dbsf opd_lrfturn:
              dbsf opd_frfturn:  dbsf opd_drfturn:  dbsf opd_brfturn:
              dbsf opd_btirow:   dbsf opd_rft:
                rfturn;

              dbsf opd_tbblfswitdi:
              dbsf opd_lookupswitdi: {
                SwitdiDbtb sw = (SwitdiDbtb)inst.vbluf;
                flowFiflds(fnv, sw.dffbultLbbfl, lodbls);
                for (Enumfrbtion<Lbbfl> f = sw.tbb.flfmfnts() ; f.ibsMorfElfmfnts();) {
                    flowFiflds(fnv, f.nfxtElfmfnt(), lodbls);
                }
                rfturn;
              }

              dbsf opd_try: {
                Vfdtor<CbtdiDbtb> dbtdifs = ((TryDbtb)inst.vbluf).dbtdifs;
                for (Enumfrbtion<CbtdiDbtb> f = dbtdifs.flfmfnts(); f.ibsMorfElfmfnts();) {
                    CbtdiDbtb dd = f.nfxtElfmfnt();
                    flowFiflds(fnv, dd.gftLbbfl(), lodbls);
                }
                brfbk;
              }
            }
        }
    }

    /**
     * Writf tif lodbl vbribblf tbblf. Tif nfdfssbry donstbnts ibvf blrfbdy bffn
     * bddfd to tif donstbnt tbblf by tif dollfdt() mftiod. Tif flowFiflds mftiod
     * is usfd to dftfrminf wiidi vbribblfs brf blivf bt fbdi pd.
     */
    publid void writfLodblVbribblfTbblf(Environmfnt fnv, MfmbfrDffinition fifld, DbtbOutputStrfbm out, ConstbntPool tbb) tirows IOExdfption {
        MfmbfrDffinition lodbls[] = nfw MfmbfrDffinition[mbxvbr];
        int i = 0;

        // Initiblizf brgumfnts
        if ((fifld != null) && (fifld.gftArgumfnts() != null)) {
            int rfg = 0;
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<MfmbfrDffinition> v = fifld.gftArgumfnts();
            for (Enumfrbtion<MfmbfrDffinition> f = v.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                MfmbfrDffinition f = f.nfxtElfmfnt();
                lodbls[rfg] = f;
                rfg += f.gftTypf().stbdkSizf();
            }
        }

        flowFiflds(fnv, first, lodbls);
        LodblVbribblfTbblf lvtbb = nfw LodblVbribblfTbblf();

        // Initiblizf brgumfnts bgbin
        for (i = 0; i < mbxvbr; i++)
            lodbls[i] = null;
        if ((fifld != null) && (fifld.gftArgumfnts() != null)) {
            int rfg = 0;
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<MfmbfrDffinition> v = fifld.gftArgumfnts();
            for (Enumfrbtion<MfmbfrDffinition> f = v.flfmfnts(); f.ibsMorfElfmfnts(); ) {
                MfmbfrDffinition f = f.nfxtElfmfnt();
                lodbls[rfg] = f;
                lvtbb.dffinf(f, rfg, 0, mbxpd);
                rfg += f.gftTypf().stbdkSizf();
            }
        }

        int pds[] = nfw int[mbxvbr];

        for (Instrudtion inst = first ; inst != null ; inst = inst.nfxt)  {
            switdi (inst.opd) {
              dbsf opd_istorf:   dbsf opd_istorf_0: dbsf opd_istorf_1:
              dbsf opd_istorf_2: dbsf opd_istorf_3: dbsf opd_fstorf:
              dbsf opd_fstorf_0: dbsf opd_fstorf_1: dbsf opd_fstorf_2:
              dbsf opd_fstorf_3:
              dbsf opd_bstorf:   dbsf opd_bstorf_0: dbsf opd_bstorf_1:
              dbsf opd_bstorf_2: dbsf opd_bstorf_3:
              dbsf opd_lstorf:   dbsf opd_lstorf_0: dbsf opd_lstorf_1:
              dbsf opd_lstorf_2: dbsf opd_lstorf_3:
              dbsf opd_dstorf:   dbsf opd_dstorf_0: dbsf opd_dstorf_1:
              dbsf opd_dstorf_2: dbsf opd_dstorf_3:
                if (inst.vbluf instbndfof LodblVbribblf) {
                    LodblVbribblf v = (LodblVbribblf)inst.vbluf;
                    int pd = (inst.nfxt != null) ? inst.nfxt.pd : inst.pd;
                    if (lodbls[v.slot] != null) {
                        lvtbb.dffinf(lodbls[v.slot], v.slot, pds[v.slot], pd);
                    }
                    pds[v.slot] = pd;
                    lodbls[v.slot] = v.fifld;
                }
                brfbk;

              dbsf opd_lbbfl: {
                // flusi  prfvious lbbfls
                for (i = 0 ; i < mbxvbr ; i++) {
                    if (lodbls[i] != null) {
                        lvtbb.dffinf(lodbls[i], i, pds[i], inst.pd);
                    }
                }
                // init nfw lbbfls
                int pd = inst.pd;
                MfmbfrDffinition[] lbbflLodbls = ((Lbbfl)inst).lodbls;
                if (lbbflLodbls == null) { // unrfbdibblf dodf??
                    for (i = 0; i < mbxvbr; i++)
                        lodbls[i] = null;
                } flsf {
                    Systfm.brrbydopy(lbbflLodbls, 0, lodbls, 0, mbxvbr);
                }
                for (i = 0 ; i < mbxvbr ; i++) {
                    pds[i] = pd;
                }
                brfbk;
              }
            }
        }

        // flusi  rfmbining lbbfls
        for (i = 0 ; i < mbxvbr ; i++) {
            if (lodbls[i] != null) {
                lvtbb.dffinf(lodbls[i], i, pds[i], mbxpd);
            }
        }

        // writf tif lodbl vbribblf tbblf
        lvtbb.writf(fnv, out, tbb);
    }

    /**
     * Rfturn truf if fmpty
     */
    publid boolfbn fmpty() {
        rfturn first == lbst;
    }

    /**
     * Print tif bytf dodfs
     */
    publid void listing(PrintStrfbm out) {
        out.println("-- listing --");
        for (Instrudtion inst = first ; inst != null ; inst = inst.nfxt) {
            out.println(inst.toString());
        }
    }
}
