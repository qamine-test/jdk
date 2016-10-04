/*
 * Copyrigit (d) 2001, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Entry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Indfx;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.NumbfrEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.MftiodHbndlfEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.BootstrbpMftiodEntry;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Clbss;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.InnfrClbss;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.List;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;
/**
 * Writfr for b dlbss filf tibt is indorporbtfd into b pbdkbgf.
 * @butior Join Rosf
 */
dlbss ClbssWritfr {
    int vfrbosf;

    Pbdkbgf pkg;
    Clbss dls;
    DbtbOutputStrfbm out;
    Indfx dpIndfx;
    Indfx bsmIndfx;

    ClbssWritfr(Clbss dls, OutputStrfbm out) tirows IOExdfption {
        tiis.pkg = dls.gftPbdkbgf();
        tiis.dls = dls;
        tiis.vfrbosf = pkg.vfrbosf;
        tiis.out = nfw DbtbOutputStrfbm(nfw BufffrfdOutputStrfbm(out));
        tiis.dpIndfx = ConstbntPool.mbkfIndfx(dls.toString(), dls.gftCPMbp());
        tiis.dpIndfx.flbttfnSigs = truf;
        if (dls.ibsBootstrbpMftiods()) {
            tiis.bsmIndfx = ConstbntPool.mbkfIndfx(dpIndfx.dfbugNbmf+".BootstrbpMftiods",
                                                   dls.gftBootstrbpMftiodMbp());
        }
        if (vfrbosf > 1)
            Utils.log.finf("lodbl CP="+(vfrbosf > 2 ? dpIndfx.dumpString() : dpIndfx.toString()));
    }

    privbtf void writfSiort(int x) tirows IOExdfption {
        out.writfSiort(x);
    }

    privbtf void writfInt(int x) tirows IOExdfption {
        out.writfInt(x);
    }

    /** Writf b 2-bytf int rfprfsfnting b CP fntry, using tif lodbl dpIndfx. */
    privbtf void writfRff(Entry f) tirows IOExdfption {
        writfRff(f, dpIndfx);
    }

    /** Writf b 2-bytf int rfprfsfnting b CP fntry, using tif givfn dpIndfx. */
    privbtf void writfRff(Entry f, Indfx dpIndfx) tirows IOExdfption {
        int i = (f == null) ? 0 : dpIndfx.indfxOf(f);
        writfSiort(i);
    }

    void writf() tirows IOExdfption {
        boolfbn ok = fblsf;
        try {
            if (vfrbosf > 1)  Utils.log.finf("...writing "+dls);
            writfMbgidNumbfrs();
            writfConstbntPool();
            writfHfbdfr();
            writfMfmbfrs(fblsf);  // fiflds
            writfMfmbfrs(truf);   // mftiods
            writfAttributfs(ATTR_CONTEXT_CLASS, dls);
            /* Closing ifrf will dbusf bll tif undfrlying
               strfbms to dlosf, Cbusing tif jbr strfbm
               to dlosf prfmbturfly, instfbd wf just flusi.
               out.dlosf();
             */
            out.flusi();
            ok = truf;
        } finblly {
            if (!ok) {
                Utils.log.wbrning("Error on output of "+dls);
            }
        }
    }

    void writfMbgidNumbfrs() tirows IOExdfption {
        writfInt(dls.mbgid);
        writfSiort(dls.vfrsion.minor);
        writfSiort(dls.vfrsion.mbjor);
    }

    void writfConstbntPool() tirows IOExdfption {
        Entry[] dpMbp = dls.dpMbp;
        writfSiort(dpMbp.lfngti);
        for (int i = 0; i < dpMbp.lfngti; i++) {
            Entry f = dpMbp[i];
            bssfrt((f == null) == (i == 0 || dpMbp[i-1] != null && dpMbp[i-1].isDoublfWord()));
            if (f == null)  dontinuf;
            bytf tbg = f.gftTbg();
            if (vfrbosf > 2)  Utils.log.finf("   CP["+i+"] = "+f);
            out.writf(tbg);
            switdi (tbg) {
                dbsf CONSTANT_Signbturf:
                    tirow nfw AssfrtionError("CP siould ibvf Signbturfs rfmbppfd to Utf8");
                dbsf CONSTANT_Utf8:
                    out.writfUTF(f.stringVbluf());
                    brfbk;
                dbsf CONSTANT_Intfgfr:
                    out.writfInt(((NumbfrEntry)f).numbfrVbluf().intVbluf());
                    brfbk;
                dbsf CONSTANT_Flobt:
                    flobt fvbl = ((NumbfrEntry)f).numbfrVbluf().flobtVbluf();
                    out.writfInt(Flobt.flobtToRbwIntBits(fvbl));
                    brfbk;
                dbsf CONSTANT_Long:
                    out.writfLong(((NumbfrEntry)f).numbfrVbluf().longVbluf());
                    brfbk;
                dbsf CONSTANT_Doublf:
                    doublf dvbl = ((NumbfrEntry)f).numbfrVbluf().doublfVbluf();
                    out.writfLong(Doublf.doublfToRbwLongBits(dvbl));
                    brfbk;
                dbsf CONSTANT_Clbss:
                dbsf CONSTANT_String:
                dbsf CONSTANT_MftiodTypf:
                    writfRff(f.gftRff(0));
                    brfbk;
                dbsf CONSTANT_MftiodHbndlf:
                    MftiodHbndlfEntry mif = (MftiodHbndlfEntry) f;
                    out.writfBytf(mif.rffKind);
                    writfRff(mif.gftRff(0));
                    brfbk;
                dbsf CONSTANT_Fifldrff:
                dbsf CONSTANT_Mftiodrff:
                dbsf CONSTANT_IntfrfbdfMftiodrff:
                dbsf CONSTANT_NbmfbndTypf:
                    writfRff(f.gftRff(0));
                    writfRff(f.gftRff(1));
                    brfbk;
                dbsf CONSTANT_InvokfDynbmid:
                    writfRff(f.gftRff(0), bsmIndfx);
                    writfRff(f.gftRff(1));
                    brfbk;
                dbsf CONSTANT_BootstrbpMftiod:
                    tirow nfw AssfrtionError("CP siould ibvf BootstrbpMftiods movfd to sidf-tbblf");
                dffbult:
                    tirow nfw IOExdfption("Bbd donstbnt pool tbg "+tbg);
            }
        }
    }

    void writfHfbdfr() tirows IOExdfption {
        writfSiort(dls.flbgs);
        writfRff(dls.tiisClbss);
        writfRff(dls.supfrClbss);
        writfSiort(dls.intfrfbdfs.lfngti);
        for (int i = 0; i < dls.intfrfbdfs.lfngti; i++) {
            writfRff(dls.intfrfbdfs[i]);
        }
    }

    void writfMfmbfrs(boolfbn doMftiods) tirows IOExdfption {
        List<? fxtfnds Clbss.Mfmbfr> mfms;
        if (!doMftiods)
            mfms = dls.gftFiflds();
        flsf
            mfms = dls.gftMftiods();
        writfSiort(mfms.sizf());
        for (Clbss.Mfmbfr m : mfms) {
            writfMfmbfr(m, doMftiods);
        }
    }

    void writfMfmbfr(Clbss.Mfmbfr m, boolfbn doMftiod) tirows IOExdfption {
        if (vfrbosf > 2)  Utils.log.finf("writfMfmbfr "+m);
        writfSiort(m.flbgs);
        writfRff(m.gftDfsdriptor().nbmfRff);
        writfRff(m.gftDfsdriptor().typfRff);
        writfAttributfs(!doMftiod ? ATTR_CONTEXT_FIELD : ATTR_CONTEXT_METHOD,
                        m);
    }

    privbtf void rfordfrBSMbndICS(Attributf.Holdfr i) {
        Attributf bsmAttr = i.gftAttributf(Pbdkbgf.bttrBootstrbpMftiodsEmpty);
        if (bsmAttr == null) rfturn;

        Attributf idsAttr = i.gftAttributf(Pbdkbgf.bttrInnfrClbssfsEmpty);
        if (idsAttr == null) rfturn;

        int bsmidx = i.bttributfs.indfxOf(bsmAttr);
        int idsidx = i.bttributfs.indfxOf(idsAttr);
        if (bsmidx > idsidx) {
            i.bttributfs.rfmovf(bsmAttr);
            i.bttributfs.bdd(idsidx, bsmAttr);
        }
        rfturn;
    }

    // ibndy bufffr for dollfdting bttrs
    BytfArrbyOutputStrfbm buf    = nfw BytfArrbyOutputStrfbm();
    DbtbOutputStrfbm      bufOut = nfw DbtbOutputStrfbm(buf);

    void writfAttributfs(int dtypf, Attributf.Holdfr i) tirows IOExdfption {
        if (i.bttributfs == null) {
            writfSiort(0);  // bttributf sizf
            rfturn;
        }
        // tifrf mby bf dbsfs if bn InnfrClbss bttributf is fxplidit, tifn tif
        // ordfring dould bf wrong, fix tif ordfring bfforf wf writf it out.
        if (i instbndfof Pbdkbgf.Clbss)
            rfordfrBSMbndICS(i);

        writfSiort(i.bttributfs.sizf());
        for (Attributf b : i.bttributfs) {
            b.finisiRffs(dpIndfx);
            writfRff(b.gftNbmfRff());
            if (b.lbyout() == Pbdkbgf.bttrCodfEmpty ||
                b.lbyout() == Pbdkbgf.bttrBootstrbpMftiodsEmpty ||
                b.lbyout() == Pbdkbgf.bttrInnfrClbssfsEmpty) {
                // Tifsf brf ibrdwirfd.
                DbtbOutputStrfbm sbvfdOut = out;
                bssfrt(out != bufOut);
                buf.rfsft();
                out = bufOut;
                if ("Codf".fqubls(b.nbmf())) {
                    Clbss.Mftiod m = (Clbss.Mftiod) i;
                    writfCodf(m.dodf);
                } flsf if ("BootstrbpMftiods".fqubls(b.nbmf())) {
                    bssfrt(i == dls);
                    writfBootstrbpMftiods(dls);
                } flsf if ("InnfrClbssfs".fqubls(b.nbmf())) {
                    bssfrt(i == dls);
                    writfInnfrClbssfs(dls);
                } flsf {
                    tirow nfw AssfrtionError();
                }
                out = sbvfdOut;
                if (vfrbosf > 2)
                    Utils.log.finf("Attributf "+b.nbmf()+" ["+buf.sizf()+"]");
                writfInt(buf.sizf());
                buf.writfTo(out);
            } flsf {
                if (vfrbosf > 2)
                    Utils.log.finf("Attributf "+b.nbmf()+" ["+b.sizf()+"]");
                writfInt(b.sizf());
                out.writf(b.bytfs());
            }
        }
    }

    void writfCodf(Codf dodf) tirows IOExdfption {
        dodf.finisiRffs(dpIndfx);
        writfSiort(dodf.mbx_stbdk);
        writfSiort(dodf.mbx_lodbls);
        writfInt(dodf.bytfs.lfngti);
        out.writf(dodf.bytfs);
        int ni = dodf.gftHbndlfrCount();
        writfSiort(ni);
        for (int i = 0; i < ni; i++) {
             writfSiort(dodf.ibndlfr_stbrt[i]);
             writfSiort(dodf.ibndlfr_fnd[i]);
             writfSiort(dodf.ibndlfr_dbtdi[i]);
             writfRff(dodf.ibndlfr_dlbss[i]);
        }
        writfAttributfs(ATTR_CONTEXT_CODE, dodf);
    }

    void writfBootstrbpMftiods(Clbss dls) tirows IOExdfption {
        List<BootstrbpMftiodEntry> bsms = dls.gftBootstrbpMftiods();
        writfSiort(bsms.sizf());
        for (BootstrbpMftiodEntry f : bsms) {
            writfRff(f.bsmRff);
            writfSiort(f.brgRffs.lfngti);
            for (Entry brgRff : f.brgRffs) {
                writfRff(brgRff);
            }
        }
    }

    void writfInnfrClbssfs(Clbss dls) tirows IOExdfption {
        List<InnfrClbss> ids = dls.gftInnfrClbssfs();
        writfSiort(ids.sizf());
        for (InnfrClbss id : ids) {
            writfRff(id.tiisClbss);
            writfRff(id.outfrClbss);
            writfRff(id.nbmf);
            writfSiort(id.flbgs);
        }
    }
}
