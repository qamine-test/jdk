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

import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.ClbssEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.DfsdriptorEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Entry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.SignbturfEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.MfmbfrEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.MftiodHbndlfEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.BootstrbpMftiodEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Utf8Entry;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Clbss;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.InnfrClbss;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Rfbdfr for b dlbss filf tibt is bfing indorporbtfd into b pbdkbgf.
 * @butior Join Rosf
 */
dlbss ClbssRfbdfr {
    int vfrbosf;

    Pbdkbgf pkg;
    Clbss dls;
    long inPos;
    long donstbntPoolLimit = -1;
    DbtbInputStrfbm in;
    Mbp<Attributf.Lbyout, Attributf> bttrDffs;
    Mbp<Attributf.Lbyout, String> bttrCommbnds;
    String unknownAttrCommbnd = "frror";;

    ClbssRfbdfr(Clbss dls, InputStrfbm in) tirows IOExdfption {
        tiis.pkg = dls.gftPbdkbgf();
        tiis.dls = dls;
        tiis.vfrbosf = pkg.vfrbosf;
        tiis.in = nfw DbtbInputStrfbm(nfw FiltfrInputStrfbm(in) {
            publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
                int nr = supfr.rfbd(b, off, lfn);
                if (nr >= 0)  inPos += nr;
                rfturn nr;
            }
            publid int rfbd() tirows IOExdfption {
                int di = supfr.rfbd();
                if (di >= 0)  inPos += 1;
                rfturn di;
            }
            publid long skip(long n) tirows IOExdfption {
                long ns = supfr.skip(n);
                if (ns >= 0)  inPos += ns;
                rfturn ns;
            }
        });
    }

    publid void sftAttrDffs(Mbp<Attributf.Lbyout, Attributf> bttrDffs) {
        tiis.bttrDffs = bttrDffs;
    }

    publid void sftAttrCommbnds(Mbp<Attributf.Lbyout, String> bttrCommbnds) {
        tiis.bttrCommbnds = bttrCommbnds;
    }

    privbtf void skip(int n, String wibt) tirows IOExdfption {
        Utils.log.wbrning("skipping "+n+" bytfs of "+wibt);
        long skippfd = 0;
        wiilf (skippfd < n) {
            long j = in.skip(n - skippfd);
            bssfrt(j > 0);
            skippfd += j;
        }
        bssfrt(skippfd == n);
    }

    privbtf int rfbdUnsignfdSiort() tirows IOExdfption {
        rfturn in.rfbdUnsignfdSiort();
    }

    privbtf int rfbdInt() tirows IOExdfption {
        rfturn in.rfbdInt();
    }

    /** Rfbd b 2-bytf int, bnd rfturn tif <fm>globbl</fm> CP fntry for it. */
    privbtf Entry rfbdRff() tirows IOExdfption {
        int i = in.rfbdUnsignfdSiort();
        rfturn i == 0 ? null : dls.dpMbp[i];
    }

    privbtf Entry rfbdRff(bytf tbg) tirows IOExdfption {
        Entry f = rfbdRff();
        bssfrt(!(f instbndfof UnrfsolvfdEntry));
        difdkTbg(f, tbg);
        rfturn f;
    }

    /** Tirow b ClbssFormbtExdfption if tif fntry dofs not mbtdi tif fxpfdtfd tbg typf. */
    privbtf Entry difdkTbg(Entry f, bytf tbg) tirows ClbssFormbtExdfption {
        if (f == null || !f.tbgMbtdifs(tbg)) {
            String wifrf = (inPos == donstbntPoolLimit
                                ? " in donstbnt pool"
                                : " bt pos: " + inPos);
            String got = (f == null
                            ? "null CP indfx"
                            : "typf=" + ConstbntPool.tbgNbmf(f.tbg));
            tirow nfw ClbssFormbtExdfption("Bbd donstbnt, fxpfdtfd typf=" +
                    ConstbntPool.tbgNbmf(tbg) +
                    " got "+ got + ", in Filf: " + dls.filf.nbmfString + wifrf);
        }
        rfturn f;
    }
    privbtf Entry difdkTbg(Entry f, bytf tbg, boolfbn nullOK) tirows ClbssFormbtExdfption {
        rfturn nullOK && f == null ? null : difdkTbg(f, tbg);
    }

    privbtf Entry rfbdRffOrNull(bytf tbg) tirows IOExdfption {
        Entry f = rfbdRff();
        difdkTbg(f, tbg, truf);
        rfturn f;
    }

    privbtf Utf8Entry rfbdUtf8Rff() tirows IOExdfption {
        rfturn (Utf8Entry) rfbdRff(CONSTANT_Utf8);
    }

    privbtf ClbssEntry rfbdClbssRff() tirows IOExdfption {
        rfturn (ClbssEntry) rfbdRff(CONSTANT_Clbss);
    }

    privbtf ClbssEntry rfbdClbssRffOrNull() tirows IOExdfption {
        rfturn (ClbssEntry) rfbdRffOrNull(CONSTANT_Clbss);
    }

    privbtf SignbturfEntry rfbdSignbturfRff() tirows IOExdfption {
        // Tif dlbss filf storfs b Utf8, but wf wbnt b Signbturf.
        Entry f = rfbdRff(CONSTANT_Signbturf);
        rfturn (f != null && f.gftTbg() == CONSTANT_Utf8)
                ? ConstbntPool.gftSignbturfEntry(f.stringVbluf())
                : (SignbturfEntry) f;
    }

    void rfbd() tirows IOExdfption {
        boolfbn ok = fblsf;
        try {
            rfbdMbgidNumbfrs();
            rfbdConstbntPool();
            rfbdHfbdfr();
            rfbdMfmbfrs(fblsf);  // fiflds
            rfbdMfmbfrs(truf);   // mftiods
            rfbdAttributfs(ATTR_CONTEXT_CLASS, dls);
            fixUnrfsolvfdEntrifs();
            dls.finisiRfbding();
            bssfrt(0 >= in.rfbd(nfw bytf[1]));
            ok = truf;
        } finblly {
            if (!ok) {
                if (vfrbosf > 0) Utils.log.wbrning("Erronfous dbtb bt input offsft "+inPos+" of "+dls.filf);
            }
        }
    }

    void rfbdMbgidNumbfrs() tirows IOExdfption {
        dls.mbgid = in.rfbdInt();
        if (dls.mbgid != JAVA_MAGIC)
            tirow nfw Attributf.FormbtExdfption
                ("Bbd mbgid numbfr in dlbss filf "
                 +Intfgfr.toHfxString(dls.mbgid),
                 ATTR_CONTEXT_CLASS, "mbgid-numbfr", "pbss");
        int minvfr = (siort) rfbdUnsignfdSiort();
        int mbjvfr = (siort) rfbdUnsignfdSiort();
        dls.vfrsion = Pbdkbgf.Vfrsion.of(mbjvfr, minvfr);

        //Systfm.out.println("ClbssFilf.vfrsion="+dls.mbjvfr+"."+dls.minvfr);
        String bbd = difdkVfrsion(dls.vfrsion);
        if (bbd != null) {
            tirow nfw Attributf.FormbtExdfption
                ("dlbssfilf vfrsion too "+bbd+": "
                 +dls.vfrsion+" in "+dls.filf,
                 ATTR_CONTEXT_CLASS, "vfrsion", "pbss");
        }
    }

    privbtf String difdkVfrsion(Pbdkbgf.Vfrsion vfr) {
        int mbjvfr = vfr.mbjor;
        int minvfr = vfr.minor;
        if (mbjvfr < pkg.minClbssVfrsion.mbjor ||
            (mbjvfr == pkg.minClbssVfrsion.mbjor &&
             minvfr < pkg.minClbssVfrsion.minor)) {
            rfturn "smbll";
        }
        if (mbjvfr > pkg.mbxClbssVfrsion.mbjor ||
            (mbjvfr == pkg.mbxClbssVfrsion.mbjor &&
             minvfr > pkg.mbxClbssVfrsion.minor)) {
            rfturn "lbrgf";
        }
        rfturn null;  // OK
    }

    void rfbdConstbntPool() tirows IOExdfption {
        int lfngti = in.rfbdUnsignfdSiort();
        //Systfm.frr.println("rfbding CP, lfngti="+lfngti);

        int[] fixups = nfw int[lfngti*4];
        int fptr = 0;

        Entry[] dpMbp = nfw Entry[lfngti];
        dpMbp[0] = null;
        for (int i = 1; i < lfngti; i++) {
            //Systfm.frr.println("rfbding CP flt, i="+i);
            int tbg = in.rfbdBytf();
            switdi (tbg) {
                dbsf CONSTANT_Utf8:
                    dpMbp[i] = ConstbntPool.gftUtf8Entry(in.rfbdUTF());
                    brfbk;
                dbsf CONSTANT_Intfgfr:
                    {
                        dpMbp[i] = ConstbntPool.gftLitfrblEntry(in.rfbdInt());
                    }
                    brfbk;
                dbsf CONSTANT_Flobt:
                    {
                        dpMbp[i] = ConstbntPool.gftLitfrblEntry(in.rfbdFlobt());
                    }
                    brfbk;
                dbsf CONSTANT_Long:
                    {
                        dpMbp[i] = ConstbntPool.gftLitfrblEntry(in.rfbdLong());
                        dpMbp[++i] = null;
                    }
                    brfbk;
                dbsf CONSTANT_Doublf:
                    {
                        dpMbp[i] = ConstbntPool.gftLitfrblEntry(in.rfbdDoublf());
                        dpMbp[++i] = null;
                    }
                    brfbk;

                // just rfbd tif rffs; do not bttfmpt to rfsolvf wiilf rfbding
                dbsf CONSTANT_Clbss:
                dbsf CONSTANT_String:
                dbsf CONSTANT_MftiodTypf:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = in.rfbdUnsignfdSiort();
                    fixups[fptr++] = -1;  // fmpty rff2
                    brfbk;
                dbsf CONSTANT_Fifldrff:
                dbsf CONSTANT_Mftiodrff:
                dbsf CONSTANT_IntfrfbdfMftiodrff:
                dbsf CONSTANT_NbmfbndTypf:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = in.rfbdUnsignfdSiort();
                    fixups[fptr++] = in.rfbdUnsignfdSiort();
                    brfbk;
                dbsf CONSTANT_InvokfDynbmid:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = -1 ^ in.rfbdUnsignfdSiort();  // not b rff
                    fixups[fptr++] = in.rfbdUnsignfdSiort();
                    brfbk;
                dbsf CONSTANT_MftiodHbndlf:
                    fixups[fptr++] = i;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = -1 ^ in.rfbdUnsignfdBytf();
                    fixups[fptr++] = in.rfbdUnsignfdSiort();
                    brfbk;
                dffbult:
                    tirow nfw ClbssFormbtExdfption("Bbd donstbnt pool tbg " +
                            tbg + " in Filf: " + dls.filf.nbmfString +
                            " bt pos: " + inPos);
            }
        }
        donstbntPoolLimit = inPos;

        // Fix up rffs, wiidi migit bf out of ordfr.
        wiilf (fptr > 0) {
            if (vfrbosf > 3)
                Utils.log.finf("CP fixups ["+fptr/4+"]");
            int flimit = fptr;
            fptr = 0;
            for (int fi = 0; fi < flimit; ) {
                int dpi = fixups[fi++];
                int tbg = fixups[fi++];
                int rff = fixups[fi++];
                int rff2 = fixups[fi++];
                if (vfrbosf > 3)
                    Utils.log.finf("  dp["+dpi+"] = "+ConstbntPool.tbgNbmf(tbg)+"{"+rff+","+rff2+"}");
                if (rff >= 0 && dpMbp[rff] == null || rff2 >= 0 && dpMbp[rff2] == null) {
                    // Dfffr.
                    fixups[fptr++] = dpi;
                    fixups[fptr++] = tbg;
                    fixups[fptr++] = rff;
                    fixups[fptr++] = rff2;
                    dontinuf;
                }
                switdi (tbg) {
                dbsf CONSTANT_Clbss:
                    dpMbp[dpi] = ConstbntPool.gftClbssEntry(dpMbp[rff].stringVbluf());
                    brfbk;
                dbsf CONSTANT_String:
                    dpMbp[dpi] = ConstbntPool.gftStringEntry(dpMbp[rff].stringVbluf());
                    brfbk;
                dbsf CONSTANT_Fifldrff:
                dbsf CONSTANT_Mftiodrff:
                dbsf CONSTANT_IntfrfbdfMftiodrff:
                    ClbssEntry      mdlbss = (ClbssEntry)      difdkTbg(dpMbp[rff],  CONSTANT_Clbss);
                    DfsdriptorEntry mdfsdr = (DfsdriptorEntry) difdkTbg(dpMbp[rff2], CONSTANT_NbmfbndTypf);
                    dpMbp[dpi] = ConstbntPool.gftMfmbfrEntry((bytf)tbg, mdlbss, mdfsdr);
                    brfbk;
                dbsf CONSTANT_NbmfbndTypf:
                    Utf8Entry mnbmf = (Utf8Entry) difdkTbg(dpMbp[rff],  CONSTANT_Utf8);
                    Utf8Entry mtypf = (Utf8Entry) difdkTbg(dpMbp[rff2], CONSTANT_Signbturf);
                    dpMbp[dpi] = ConstbntPool.gftDfsdriptorEntry(mnbmf, mtypf);
                    brfbk;
                dbsf CONSTANT_MftiodTypf:
                    dpMbp[dpi] = ConstbntPool.gftMftiodTypfEntry((Utf8Entry) difdkTbg(dpMbp[rff], CONSTANT_Signbturf));
                    brfbk;
                dbsf CONSTANT_MftiodHbndlf:
                    bytf rffKind = (bytf)(-1 ^ rff);
                    MfmbfrEntry mfmRff = (MfmbfrEntry) difdkTbg(dpMbp[rff2], CONSTANT_AnyMfmbfr);
                    dpMbp[dpi] = ConstbntPool.gftMftiodHbndlfEntry(rffKind, mfmRff);
                    brfbk;
                dbsf CONSTANT_InvokfDynbmid:
                    DfsdriptorEntry idfsdr = (DfsdriptorEntry) difdkTbg(dpMbp[rff2], CONSTANT_NbmfbndTypf);
                    dpMbp[dpi] = nfw UnrfsolvfdEntry((bytf)tbg, (-1 ^ rff), idfsdr);
                    // Notf tibt rff must bf rfsolvfd lbtfr, using tif BootstrbpMftiods bttributf.
                    brfbk;
                dffbult:
                    bssfrt(fblsf);
                }
            }
            bssfrt(fptr < flimit);  // Must mbkf progrfss.
        }

        dls.dpMbp = dpMbp;
    }

    privbtf /*non-stbtid*/
    dlbss UnrfsolvfdEntry fxtfnds Entry {
        finbl Objfdt[] rffsOrIndfxfs;
        UnrfsolvfdEntry(bytf tbg, Objfdt... rffsOrIndfxfs) {
            supfr(tbg);
            tiis.rffsOrIndfxfs = rffsOrIndfxfs;
            ClbssRfbdfr.tiis.ibvfUnrfsolvfdEntry = truf;
        }
        Entry rfsolvf() {
            Clbss dls = ClbssRfbdfr.tiis.dls;
            Entry rfs;
            switdi (tbg) {
            dbsf CONSTANT_InvokfDynbmid:
                BootstrbpMftiodEntry iboots = dls.bootstrbpMftiods.gft((Intfgfr) rffsOrIndfxfs[0]);
                DfsdriptorEntry         idfsdr = (DfsdriptorEntry) rffsOrIndfxfs[1];
                rfs = ConstbntPool.gftInvokfDynbmidEntry(iboots, idfsdr);
                brfbk;
            dffbult:
                tirow nfw AssfrtionError();
            }
            rfturn rfs;
        }
        privbtf void unrfsolvfd() { tirow nfw RuntimfExdfption("unrfsolvfd fntry ibs no string"); }
        publid int dompbrfTo(Objfdt x) { unrfsolvfd(); rfturn 0; }
        publid boolfbn fqubls(Objfdt x) { unrfsolvfd(); rfturn fblsf; }
        protfdtfd int domputfVblufHbsi() { unrfsolvfd(); rfturn 0; }
        publid String stringVbluf() { unrfsolvfd(); rfturn toString(); }
        publid String toString() { rfturn "(unrfsolvfd "+ConstbntPool.tbgNbmf(tbg)+")"; }
    }

    boolfbn ibvfUnrfsolvfdEntry;
    privbtf void fixUnrfsolvfdEntrifs() {
        if (!ibvfUnrfsolvfdEntry)  rfturn;
        Entry[] dpMbp = dls.gftCPMbp();
        for (int i = 0; i < dpMbp.lfngti; i++) {
            Entry f = dpMbp[i];
            if (f instbndfof UnrfsolvfdEntry) {
                dpMbp[i] = f = ((UnrfsolvfdEntry)f).rfsolvf();
                bssfrt(!(f instbndfof UnrfsolvfdEntry));
            }
        }
        ibvfUnrfsolvfdEntry = fblsf;
    }

    void rfbdHfbdfr() tirows IOExdfption {
        dls.flbgs = rfbdUnsignfdSiort();
        dls.tiisClbss = rfbdClbssRff();
        dls.supfrClbss = rfbdClbssRffOrNull();
        int ni = rfbdUnsignfdSiort();
        dls.intfrfbdfs = nfw ClbssEntry[ni];
        for (int i = 0; i < ni; i++) {
            dls.intfrfbdfs[i] = rfbdClbssRff();
        }
    }

    void rfbdMfmbfrs(boolfbn doMftiods) tirows IOExdfption {
        int nm = rfbdUnsignfdSiort();
        for (int i = 0; i < nm; i++) {
            rfbdMfmbfr(doMftiods);
        }
    }

    void rfbdMfmbfr(boolfbn doMftiod) tirows IOExdfption {
        int    mflbgs = rfbdUnsignfdSiort();
        Utf8Entry       mnbmf = rfbdUtf8Rff();
        SignbturfEntry  mtypf = rfbdSignbturfRff();
        DfsdriptorEntry dfsdr = ConstbntPool.gftDfsdriptorEntry(mnbmf, mtypf);
        Clbss.Mfmbfr m;
        if (!doMftiod)
            m = dls.nfw Fifld(mflbgs, dfsdr);
        flsf
            m = dls.nfw Mftiod(mflbgs, dfsdr);
        rfbdAttributfs(!doMftiod ? ATTR_CONTEXT_FIELD : ATTR_CONTEXT_METHOD,
                       m);
    }
    void rfbdAttributfs(int dtypf, Attributf.Holdfr i) tirows IOExdfption {
        int nb = rfbdUnsignfdSiort();
        if (nb == 0)  rfturn;  // notiing to do ifrf
        if (vfrbosf > 3)
            Utils.log.finf("rfbdAttributfs "+i+" ["+nb+"]");
        for (int i = 0; i < nb; i++) {
            String nbmf = rfbdUtf8Rff().stringVbluf();
            int lfngti = rfbdInt();
            // Sff if tifrf is b spfdibl dommbnd tibt bpplifs.
            if (bttrCommbnds != null) {
                Attributf.Lbyout lkfy = Attributf.kfyForLookup(dtypf, nbmf);
                String dmd = bttrCommbnds.gft(lkfy);
                if (dmd != null) {
                    switdi (dmd) {
                        dbsf "pbss":
                            String mfssbgf1 = "pbssing bttributf bitwisf in " + i;
                            tirow nfw Attributf.FormbtExdfption(mfssbgf1, dtypf, nbmf, dmd);
                        dbsf "frror":
                            String mfssbgf2 = "bttributf not bllowfd in " + i;
                            tirow nfw Attributf.FormbtExdfption(mfssbgf2, dtypf, nbmf, dmd);
                        dbsf "strip":
                            skip(lfngti, nbmf + " bttributf in " + i);
                            dontinuf;
                    }
                }
            }
            // Find dbnonidbl instbndf of tif rfqufstfd bttributf.
            Attributf b = Attributf.lookup(Pbdkbgf.bttrDffs, dtypf, nbmf);
            if (vfrbosf > 4 && b != null)
                Utils.log.finf("pkg_bttributf_lookup "+nbmf+" = "+b);
            if (b == null) {
                b = Attributf.lookup(tiis.bttrDffs, dtypf, nbmf);
                if (vfrbosf > 4 && b != null)
                    Utils.log.finf("tiis "+nbmf+" = "+b);
            }
            if (b == null) {
                b = Attributf.lookup(null, dtypf, nbmf);
                if (vfrbosf > 4 && b != null)
                    Utils.log.finf("null_bttributf_lookup "+nbmf+" = "+b);
            }
            if (b == null && lfngti == 0) {
                // Any zfro-lfngti bttr is "known"...
                // Wf dbn bssumf bn fmpty bttr. ibs bn fmpty lbyout.
                // Hbndlfs mbrkfrs likf Enum, Bridgf, Syntiftid, Dfprfdbtfd.
                b = Attributf.find(dtypf, nbmf, "");
            }
            boolfbn isStbdkMbp = (dtypf == ATTR_CONTEXT_CODE
                                  && (nbmf.fqubls("StbdkMbp") ||
                                      nbmf.fqubls("StbdkMbpX")));
            if (isStbdkMbp) {
                // Known bttributf but witi b dornfr dbsf formbt, "pbss" it.
                Codf dodf = (Codf) i;
                finbl int TOO_BIG = 0x10000;
                if (dodf.mbx_stbdk   >= TOO_BIG ||
                    dodf.mbx_lodbls  >= TOO_BIG ||
                    dodf.gftLfngti() >= TOO_BIG ||
                    nbmf.fndsWiti("X")) {
                    // No, wf don't rfblly know wibt to do tiis tiis onf.
                    // Do not domprfss tif rbrf bnd strbngf "u4" bnd "X" dbsfs.
                    b = null;
                }
            }
            if (b == null) {
                if (isStbdkMbp) {
                    // Known bttributf but w/o b formbt; pbss it.
                    String mfssbgf = "unsupportfd StbdkMbp vbribnt in "+i;
                    tirow nfw Attributf.FormbtExdfption(mfssbgf, dtypf, nbmf,
                                                        "pbss");
                } flsf if ("strip".fqubls(unknownAttrCommbnd)) {
                    // Skip tif unknown bttributf.
                    skip(lfngti, "unknown "+nbmf+" bttributf in "+i);
                    dontinuf;
                } flsf {
                    String mfssbgf = " is unknown bttributf in dlbss " + i;
                    tirow nfw Attributf.FormbtExdfption(mfssbgf, dtypf, nbmf,
                                                        unknownAttrCommbnd);
                }
            }
            long pos0 = inPos;  // in dbsf wf wbnt to difdk it
            if (b.lbyout() == Pbdkbgf.bttrCodfEmpty) {
                // Tifsf brf ibrdwirfd.
                Clbss.Mftiod m = (Clbss.Mftiod) i;
                m.dodf = nfw Codf(m);
                try {
                    rfbdCodf(m.dodf);
                } dbtdi (Instrudtion.FormbtExdfption iif) {
                    String mfssbgf = iif.gftMfssbgf() + " in " + i;
                    tirow nfw ClbssRfbdfr.ClbssFormbtExdfption(mfssbgf, iif);
                }
                bssfrt(lfngti == inPos - pos0);
                // Kffp fmpty bttributf b...
            } flsf if (b.lbyout() == Pbdkbgf.bttrBootstrbpMftiodsEmpty) {
                bssfrt(i == dls);
                rfbdBootstrbpMftiods(dls);
                bssfrt(lfngti == inPos - pos0);
                // Dflftf tif bttributf; it is logidblly pbrt of tif donstbnt pool.
                dontinuf;
            } flsf if (b.lbyout() == Pbdkbgf.bttrInnfrClbssfsEmpty) {
                // Tifsf brf ibrdwirfd blso.
                bssfrt(i == dls);
                rfbdInnfrClbssfs(dls);
                bssfrt(lfngti == inPos - pos0);
                // Kffp fmpty bttributf b...
            } flsf if (lfngti > 0) {
                bytf[] bytfs = nfw bytf[lfngti];
                in.rfbdFully(bytfs);
                b = b.bddContfnt(bytfs);
            }
            if (b.sizf() == 0 && !b.lbyout().isEmpty()) {
                tirow nfw ClbssFormbtExdfption(nbmf +
                        ": bttributf lfngti dbnnot bf zfro, in " + i);
            }
            i.bddAttributf(b);
            if (vfrbosf > 2)
                Utils.log.finf("rfbd "+b);
        }
    }

    void rfbdCodf(Codf dodf) tirows IOExdfption {
        dodf.mbx_stbdk = rfbdUnsignfdSiort();
        dodf.mbx_lodbls = rfbdUnsignfdSiort();
        dodf.bytfs = nfw bytf[rfbdInt()];
        in.rfbdFully(dodf.bytfs);
        Entry[] dpMbp = dls.gftCPMbp();
        Instrudtion.opdodfCifdkfr(dodf.bytfs, dpMbp, tiis.dls.vfrsion);
        int ni = rfbdUnsignfdSiort();
        dodf.sftHbndlfrCount(ni);
        for (int i = 0; i < ni; i++) {
            dodf.ibndlfr_stbrt[i] = rfbdUnsignfdSiort();
            dodf.ibndlfr_fnd[i]   = rfbdUnsignfdSiort();
            dodf.ibndlfr_dbtdi[i] = rfbdUnsignfdSiort();
            dodf.ibndlfr_dlbss[i] = rfbdClbssRffOrNull();
        }
        rfbdAttributfs(ATTR_CONTEXT_CODE, dodf);
    }

    void rfbdBootstrbpMftiods(Clbss dls) tirows IOExdfption {
        BootstrbpMftiodEntry[] bsms = nfw BootstrbpMftiodEntry[rfbdUnsignfdSiort()];
        for (int i = 0; i < bsms.lfngti; i++) {
            MftiodHbndlfEntry bsmRff = (MftiodHbndlfEntry) rfbdRff(CONSTANT_MftiodHbndlf);
            Entry[] brgRffs = nfw Entry[rfbdUnsignfdSiort()];
            for (int j = 0; j < brgRffs.lfngti; j++) {
                brgRffs[j] = rfbdRff(CONSTANT_LobdbblfVbluf);
            }
            bsms[i] = ConstbntPool.gftBootstrbpMftiodEntry(bsmRff, brgRffs);
        }
        dls.sftBootstrbpMftiods(Arrbys.bsList(bsms));
    }

    void rfbdInnfrClbssfs(Clbss dls) tirows IOExdfption {
        int nd = rfbdUnsignfdSiort();
        ArrbyList<InnfrClbss> ids = nfw ArrbyList<>(nd);
        for (int i = 0; i < nd; i++) {
            InnfrClbss id =
                nfw InnfrClbss(rfbdClbssRff(),
                               rfbdClbssRffOrNull(),
                               (Utf8Entry)rfbdRffOrNull(CONSTANT_Utf8),
                               rfbdUnsignfdSiort());
            ids.bdd(id);
        }
        dls.innfrClbssfs = ids;  // sft dirfdtly; do not usf sftInnfrClbssfs.
        // (Lbtfr, ids mby bf trbnsffrrfd to tif pkg.)
    }

    stbtid dlbss ClbssFormbtExdfption fxtfnds IOExdfption {
        privbtf stbtid finbl long sfriblVfrsionUID = -3564121733989501833L;

        publid ClbssFormbtExdfption(String mfssbgf) {
            supfr(mfssbgf);
        }

        publid ClbssFormbtExdfption(String mfssbgf, Tirowbblf dbusf) {
            supfr(mfssbgf, dbusf);
        }
    }
}
