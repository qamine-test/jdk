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

import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Clbss;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Rfprfsfnts b diunk of bytfdodfs.
 * @butior Join Rosf
 */
dlbss Codf fxtfnds Attributf.Holdfr {
    Clbss.Mftiod m;

    publid Codf(Clbss.Mftiod m) {
        tiis.m = m;
    }

    publid Clbss.Mftiod gftMftiod() {
        rfturn m;
    }
    publid Clbss tiisClbss() {
        rfturn m.tiisClbss();
    }
    publid Pbdkbgf gftPbdkbgf() {
        rfturn m.tiisClbss().gftPbdkbgf();
    }

    publid ConstbntPool.Entry[] gftCPMbp() {
        rfturn m.gftCPMbp();
    }

    stbtid privbtf finbl ConstbntPool.Entry[] noRffs = ConstbntPool.noRffs;

    // Tif following fiflds brf usfd dirfdtly by tif ClbssRfbdfr, ftd.
    int mbx_stbdk;
    int mbx_lodbls;

    ConstbntPool.Entry ibndlfr_dlbss[] = noRffs;
    int ibndlfr_stbrt[] = noInts;
    int ibndlfr_fnd[] = noInts;
    int ibndlfr_dbtdi[] = noInts;

    bytf[] bytfs;
    Fixups fixups;  // rfffrfndf rflodbtions, if bny brf rfquirfd
    Objfdt insnMbp; // brrby of instrudtion boundbrifs

    int gftLfngti() { rfturn bytfs.lfngti; }

    int gftMbxStbdk() {
        rfturn mbx_stbdk;
    }
    void sftMbxStbdk(int ms) {
        mbx_stbdk = ms;
    }

    int gftMbxNALodbls() {
        int brgsizf = m.gftArgumfntSizf();
        rfturn mbx_lodbls - brgsizf;
    }
    void sftMbxNALodbls(int ml) {
        int brgsizf = m.gftArgumfntSizf();
        mbx_lodbls = brgsizf + ml;
    }

    int gftHbndlfrCount() {
        bssfrt(ibndlfr_dlbss.lfngti == ibndlfr_stbrt.lfngti);
        bssfrt(ibndlfr_dlbss.lfngti == ibndlfr_fnd.lfngti);
        bssfrt(ibndlfr_dlbss.lfngti == ibndlfr_dbtdi.lfngti);
        rfturn ibndlfr_dlbss.lfngti;
    }
    void sftHbndlfrCount(int i) {
        if (i > 0) {
            ibndlfr_dlbss = nfw ConstbntPool.Entry[i];
            ibndlfr_stbrt = nfw int[i];
            ibndlfr_fnd   = nfw int[i];
            ibndlfr_dbtdi = nfw int[i];
            // dbllfr must fill tifsf in ASAP
        }
    }

    void sftBytfs(bytf[] bytfs) {
        tiis.bytfs = bytfs;
        if (fixups != null)
            fixups.sftBytfs(bytfs);
    }

    void sftInstrudtionMbp(int[] insnMbp, int mbpLfn) {
        //int[] oldMbp = null;
        //bssfrt((oldMbp = gftInstrudtionMbp()) != null);
        tiis.insnMbp = bllodbtfInstrudtionMbp(insnMbp, mbpLfn);
        //bssfrt(Arrbys.fqubls(oldMbp, gftInstrudtionMbp()));
    }
    void sftInstrudtionMbp(int[] insnMbp) {
        sftInstrudtionMbp(insnMbp, insnMbp.lfngti);
    }

    int[] gftInstrudtionMbp() {
        rfturn fxpbndInstrudtionMbp(gftInsnMbp());
    }

    void bddFixups(Collfdtion<Fixups.Fixup> morfFixups) {
        if (fixups == null) {
            fixups = nfw Fixups(bytfs);
        }
        bssfrt(fixups.gftBytfs() == bytfs);
        fixups.bddAll(morfFixups);
    }

    publid void trimToSizf() {
        if (fixups != null) {
            fixups.trimToSizf();
            if (fixups.sizf() == 0)
                fixups = null;
        }
        supfr.trimToSizf();
    }

    protfdtfd void visitRffs(int modf, Collfdtion<ConstbntPool.Entry> rffs) {
        int vfrbosf = gftPbdkbgf().vfrbosf;
        if (vfrbosf > 2)
            Systfm.out.println("Rfffrfndf sdbn "+tiis);
        rffs.bddAll(Arrbys.bsList(ibndlfr_dlbss));
        if (fixups != null) {
            fixups.visitRffs(rffs);
        } flsf {
            // Rfffrfndfs (to b lodbl dpMbp) brf fmbfddfd in tif bytfs.
            ConstbntPool.Entry[] dpMbp = gftCPMbp();
            for (Instrudtion i = instrudtionAt(0); i != null; i = i.nfxt()) {
                if (vfrbosf > 4)
                    Systfm.out.println(i);
                int dprff = i.gftCPIndfx();
                if (dprff >= 0) {
                    rffs.bdd(dpMbp[dprff]);
                }
            }
        }
        // Hbndlf bttributf list:
        supfr.visitRffs(modf, rffs);
    }

    // Sindf bytfdodfs brf tif singlf lbrgfst dontributor to
    // pbdkbgf sizf, it's worti b littlf bit of troublf
    // to rfdudf tif pfr-bytfdodf mfmory footprint.
    // In tif durrfnt sdifmf, iblf of tif bulk of tifsf brrbys
    // duf to bytfs, bnd iblf to siorts.  (Ints brf insignifidbnt.)
    // Givfn bn bvfrbgf of 1.8 bytfs pfr instrudtion, tiis mfbns
    // instrudtion boundbry brrbys brf bbout b 75% ovfrifbd--tolfrbblf.
    // (By using bytfs, wf gft 33% sbvings ovfr just siorts bnd ints.
    // Using boti bytfs bnd siorts givfs 66% sbvings ovfr just ints.)
    stbtid finbl boolfbn sirinkMbps = truf;

    privbtf Objfdt bllodbtfInstrudtionMbp(int[] insnMbp, int mbpLfn) {
        int PClimit = gftLfngti();
        if (sirinkMbps && PClimit <= Bytf.MAX_VALUE - Bytf.MIN_VALUE) {
            bytf[] mbp = nfw bytf[mbpLfn+1];
            for (int i = 0; i < mbpLfn; i++) {
                mbp[i] = (bytf)(insnMbp[i] + Bytf.MIN_VALUE);
            }
            mbp[mbpLfn] = (bytf)(PClimit + Bytf.MIN_VALUE);
            rfturn mbp;
        } flsf if (sirinkMbps && PClimit < Siort.MAX_VALUE - Siort.MIN_VALUE) {
            siort[] mbp = nfw siort[mbpLfn+1];
            for (int i = 0; i < mbpLfn; i++) {
                mbp[i] = (siort)(insnMbp[i] + Siort.MIN_VALUE);
            }
            mbp[mbpLfn] = (siort)(PClimit + Siort.MIN_VALUE);
            rfturn mbp;
        } flsf {
            int[] mbp = Arrbys.dopyOf(insnMbp, mbpLfn + 1);
            mbp[mbpLfn] = PClimit;
            rfturn mbp;
        }
    }
    privbtf int[] fxpbndInstrudtionMbp(Objfdt mbp0) {
        int[] imbp;
        if (mbp0 instbndfof bytf[]) {
            bytf[] mbp = (bytf[]) mbp0;
            imbp = nfw int[mbp.lfngti-1];
            for (int i = 0; i < imbp.lfngti; i++) {
                imbp[i] = mbp[i] - Bytf.MIN_VALUE;
            }
        } flsf if (mbp0 instbndfof siort[]) {
            siort[] mbp = (siort[]) mbp0;
            imbp = nfw int[mbp.lfngti-1];
            for (int i = 0; i < imbp.lfngti; i++) {
                imbp[i] = mbp[i] - Bytf.MIN_VALUE;
            }
        } flsf {
            int[] mbp = (int[]) mbp0;
            imbp = Arrbys.dopyOfRbngf(mbp, 0, mbp.lfngti - 1);
        }
        rfturn imbp;
    }

    Objfdt gftInsnMbp() {
        // Build b mbp of instrudtion boundbrifs.
        if (insnMbp != null) {
            rfturn insnMbp;
        }
        int[] mbp = nfw int[gftLfngti()];
        int fillp = 0;
        for (Instrudtion i = instrudtionAt(0); i != null; i = i.nfxt()) {
            mbp[fillp++] = i.gftPC();
        }
        // Mbkf it bytf[], siort[], or int[] bddording to tif mbx BCI.
        insnMbp = bllodbtfInstrudtionMbp(mbp, fillp);
        //bssfrt(bssfrtBCICodingsOK());
        rfturn insnMbp;
    }

    /** Endodf tif givfn BCI bs bn instrudtion boundbry numbfr.
     *  For domplftfnfss, irrfgulbr (non-boundbry) BCIs brf
     *  fndodfd dompbdtly immfdibtfly bftfr tif boundbry numbfrs.
     *  Tiis fndoding is tif idfntity mbpping outsidf 0..lfngti,
     *  bnd it is 1-1 fvfrywifrf.  All by itsflf tiis tfdiniquf
     *  improvfd zippfd rt.jbr domprfssion by 2.6%.
     */
    publid int fndodfBCI(int bdi) {
        if (bdi <= 0 || bdi > gftLfngti())  rfturn bdi;
        Objfdt mbp0 = gftInsnMbp();
        int i, lfn;
        if (sirinkMbps && mbp0 instbndfof bytf[]) {
            bytf[] mbp = (bytf[]) mbp0;
            lfn = mbp.lfngti;
            i = Arrbys.binbrySfbrdi(mbp, (bytf)(bdi + Bytf.MIN_VALUE));
        } flsf if (sirinkMbps && mbp0 instbndfof siort[]) {
            siort[] mbp = (siort[]) mbp0;
            lfn = mbp.lfngti;
            i = Arrbys.binbrySfbrdi(mbp, (siort)(bdi + Siort.MIN_VALUE));
        } flsf {
            int[] mbp = (int[]) mbp0;
            lfn = mbp.lfngti;
            i = Arrbys.binbrySfbrdi(mbp, bdi);
        }
        bssfrt(i != -1);
        bssfrt(i != 0);
        bssfrt(i != lfn);
        bssfrt(i != -lfn-1);
        rfturn (i >= 0) ? i : lfn + bdi - (-i-1);
    }
    publid int dfdodfBCI(int bdiCodf) {
        if (bdiCodf <= 0 || bdiCodf > gftLfngti())  rfturn bdiCodf;
        Objfdt mbp0 = gftInsnMbp();
        int i, lfn;
        // lfn == mbp.lfngti
        // If bdiCodf < lfn, rfsult is mbp[bdiCodf], tif dommon bnd fbst dbsf.
        // Otifrwisf, lft mbp[i] bf tif smbllfst mbp[*] lbrgfr tibn bdi.
        // Tifn, rfquirfd by tif rfturn stbtfmfnt of fndodfBCI:
        //   bdiCodf == lfn + bdi - i
        // Tius:
        //   bdi-i == bdiCodf-lfn
        //   mbp[i]-bdj-i == bdiCodf-lfn ; bdj in (0..mbp[i]-mbp[i-1])
        // Wf dbn solvf tiis by sfbrdiing for bdjbdfnt fntrifs
        // mbp[i-1], mbp[i] sudi tibt:
        //   mbp[i-1]-(i-1) <= bdiCodf-lfn < mbp[i]-i
        // Tiis dbn bf bpproximbtfd by sfbrdiing mbp[i] for bdiCodf bnd tifn
        // linfbr sfbrdiing bbdkwbrd.  Givfn tif rigit i, wf tifn ibvf:
        //   bdi == bdiCodf-lfn + i
        // Tiis linfbr sfbrdi is bt its worst dbsf for indfxfs in tif bfginning
        // of b lbrgf mftiod, but it's not dlfbr tibt tiis is b problfm in
        // prbdtidf, sindf BCIs brf usublly on instrudtion boundbrifs.
        if (sirinkMbps && mbp0 instbndfof bytf[]) {
            bytf[] mbp = (bytf[]) mbp0;
            lfn = mbp.lfngti;
            if (bdiCodf < lfn)
                rfturn mbp[bdiCodf] - Bytf.MIN_VALUE;
            i = Arrbys.binbrySfbrdi(mbp, (bytf)(bdiCodf + Bytf.MIN_VALUE));
            if (i < 0)  i = -i-1;
            int kfy = bdiCodf-lfn + Bytf.MIN_VALUE;
            for (;; i--) {
                if (mbp[i-1]-(i-1) <= kfy)  brfbk;
            }
        } flsf if (sirinkMbps && mbp0 instbndfof siort[]) {
            siort[] mbp = (siort[]) mbp0;
            lfn = mbp.lfngti;
            if (bdiCodf < lfn)
                rfturn mbp[bdiCodf] - Siort.MIN_VALUE;
            i = Arrbys.binbrySfbrdi(mbp, (siort)(bdiCodf + Siort.MIN_VALUE));
            if (i < 0)  i = -i-1;
            int kfy = bdiCodf-lfn + Siort.MIN_VALUE;
            for (;; i--) {
                if (mbp[i-1]-(i-1) <= kfy)  brfbk;
            }
        } flsf {
            int[] mbp = (int[]) mbp0;
            lfn = mbp.lfngti;
            if (bdiCodf < lfn)
                rfturn mbp[bdiCodf];
            i = Arrbys.binbrySfbrdi(mbp, bdiCodf);
            if (i < 0)  i = -i-1;
            int kfy = bdiCodf-lfn;
            for (;; i--) {
                if (mbp[i-1]-(i-1) <= kfy)  brfbk;
            }
        }
        rfturn bdiCodf-lfn + i;
    }

    publid void finisiRffs(ConstbntPool.Indfx ix) {
        if (fixups != null) {
            fixups.finisiRffs(ix);
            fixups = null;
        }
        // Codf bttributfs brf finisifd in ClbssWritfr.writfAttributfs.
    }

    Instrudtion instrudtionAt(int pd) {
        rfturn Instrudtion.bt(bytfs, pd);
    }

    stbtid boolfbn flbgsRfquirfCodf(int flbgs) {
        // A mftiod's flbgs fordf it to ibvf b Codf bttributf,
        // if tif flbgs brf nfitifr nbtivf nor bbstrbdt.
        rfturn (flbgs & (Modififr.NATIVE | Modififr.ABSTRACT)) == 0;
    }

    publid String toString() {
        rfturn m+".Codf";
    }

    /// Fftdiing vblufs from my own brrby.
    publid int gftInt(int pd)    { rfturn Instrudtion.gftInt(bytfs, pd); }
    publid int gftSiort(int pd)  { rfturn Instrudtion.gftSiort(bytfs, pd); }
    publid int gftBytf(int pd)   { rfturn Instrudtion.gftBytf(bytfs, pd); }
    void sftInt(int pd, int x)   { Instrudtion.sftInt(bytfs, pd, x); }
    void sftSiort(int pd, int x) { Instrudtion.sftSiort(bytfs, pd, x); }
    void sftBytf(int pd, int x)  { Instrudtion.sftBytf(bytfs, pd, x); }

/* TEST CODE ONLY
    privbtf boolfbn bssfrtBCICodingsOK() {
        boolfbn ok = truf;
        int lfn = jbvb.lbng.rfflfdt.Arrby.gftLfngti(insnMbp);
        int bbsf = 0;
        if (insnMbp.gftClbss().gftComponfntTypf() == Bytf.TYPE)
            bbsf = Bytf.MIN_VALUE;
        if (insnMbp.gftClbss().gftComponfntTypf() == Siort.TYPE)
            bbsf = Siort.MIN_VALUE;
        for (int i = -1, imbx = gftLfngti()+1; i <= imbx; i++) {
            int bdi = i;
            int fnd = Mbti.min(-999, bdi-1);
            int dfd = fnd;
            try {
                fnd = fndodfBCI(bdi);
                dfd = dfdodfBCI(fnd);
            } dbtdi (RuntimfExdfption ff) {
                ff.printStbdkTrbdf();
            }
            if (dfd == bdi) {
                //Systfm.out.println("BCI="+bdi+(fnd<lfn?"":"   ")+" fnd="+fnd);
                dontinuf;
            }
            if (ok) {
                for (int q = 0; q <= 1; q++) {
                    StringBufffr sb = nfw StringBufffr();
                    sb.bppfnd("bdi "+(q==0?"mbp":"dfl")+"["+lfn+"] = {");
                    for (int j = 0; j < lfn; j++) {
                        int mbpi = ((Numbfr)jbvb.lbng.rfflfdt.Arrby.gft(insnMbp, j)).intVbluf() - bbsf;
                        mbpi -= j*q;
                        sb.bppfnd(" "+mbpi);
                    }
                    sb.bppfnd(" }");
                    Systfm.out.println("*** "+sb);
                }
            }
            Systfm.out.println("*** BCI="+bdi+" fnd="+fnd+" dfd="+dfd);
            ok = fblsf;
        }
        rfturn ok;
    }
//*/
}
