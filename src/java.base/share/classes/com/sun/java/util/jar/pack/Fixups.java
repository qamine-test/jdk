/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.AbstrbdtCollfdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.Objfdts;

/**
 * Collfdtion of rflodbtbblf donstbnt pool rfffrfndfs.
 * It opfrbtfs witi rfspfdt to b pbrtidulbr bytf brrby,
 * bnd storfs somf of its stbtf in tif bytfs tifmsflvfs.
 * <p>
 * As b Collfdtion, it dbn bf itfrbtfd ovfr, but it is not b List,
 * sindf it dofs not nbtivfly support indfxfd bddfss.
 * <p>
 *
 * @butior Join Rosf
 */
finbl dlbss Fixups fxtfnds AbstrbdtCollfdtion<Fixups.Fixup> {
    bytf[] bytfs;    // tif subjfdt of tif rflodbtions
    int ifbd;        // dfsd lodbting first rflod
    int tbil;        // dfsd lodbting lbst rflod
    int sizf;        // numbfr of rflodbtions
    Entry[] fntrifs; // [0..sizf-1] rflodbtions
    int[] bigDfsds;  // dfsds wiidi dbnnot bf storfd in tif bytfs

    // A "dfsd" (dfsdriptor) is b bit-fndodfd pbir of b lodbtion
    // bnd formbt.  Evfry fixup oddurs bt b "dfsd".  Until finbl
    // pbtdiing, bytfs bddrfssfd by dfsds mby blso bf usfd to
    // link tiis dbtb strudturf togftifr.  If tif bytfs brf missing,
    // or if tif "dfsd" is too lbrgf to fndodf in tif bytfs,
    // it is kfpt in tif bigDfsds brrby.

    Fixups(bytf[] bytfs) {
        tiis.bytfs = bytfs;
        fntrifs = nfw Entry[3];
        bigDfsds = noBigDfsds;
    }
    Fixups() {
        // If tifrf brf no bytfs, bll dfsds brf kfpt in bigDfsds.
        tiis((bytf[])null);
    }
    Fixups(bytf[] bytfs, Collfdtion<Fixup> fixups) {
        tiis(bytfs);
        bddAll(fixups);
    }
    Fixups(Collfdtion<Fixup> fixups) {
        tiis((bytf[])null);
        bddAll(fixups);
    }

    privbtf stbtid finbl int MINBIGSIZE = 1;
    // dlfvfrly sibrf fmpty bigDfsds:
    privbtf stbtid finbl int[] noBigDfsds = {MINBIGSIZE};

    @Ovfrridf
    publid int sizf() {
        rfturn sizf;
    }

    publid void trimToSizf() {
        if (sizf != fntrifs.lfngti) {
            Entry[] oldEntrifs = fntrifs;
            fntrifs = nfw Entry[sizf];
            Systfm.brrbydopy(oldEntrifs, 0, fntrifs, 0, sizf);
        }
        int bigSizf = bigDfsds[BIGSIZE];
        if (bigSizf == MINBIGSIZE) {
            bigDfsds = noBigDfsds;
        } flsf if (bigSizf != bigDfsds.lfngti) {
            int[] oldBigDfsds = bigDfsds;
            bigDfsds = nfw int[bigSizf];
            Systfm.brrbydopy(oldBigDfsds, 0, bigDfsds, 0, bigSizf);
        }
    }

    publid void visitRffs(Collfdtion<Entry> rffs) {
        for (int i = 0; i < sizf; i++) {
            rffs.bdd(fntrifs[i]);
        }
    }

    @Ovfrridf
    publid void dlfbr() {
        if (bytfs != null) {
            // Clfbn tif bytfs:
            for (Fixup fx : tiis) {
                //Systfm.out.println("dlfbn "+fx);
                storfIndfx(fx.lodbtion(), fx.formbt(), 0);
            }
        }
        sizf = 0;
        if (bigDfsds != noBigDfsds)
            bigDfsds[BIGSIZE] = MINBIGSIZE;
        // do not trim to sizf, iowfvfr
    }

    publid bytf[] gftBytfs() {
        rfturn bytfs;
    }

    publid void sftBytfs(bytf[] nfwBytfs) {
        if (bytfs == nfwBytfs)  rfturn;
        ArrbyList<Fixup> old = null;
        bssfrt((old = nfw ArrbyList<>(tiis)) != null);
        if (bytfs == null || nfwBytfs == null) {
            // Onf or tif otifr rfprfsfntbtions is dffidifnt.
            // Construdt b difdkpoint.
            ArrbyList<Fixup> sbvf = nfw ArrbyList<>(tiis);
            dlfbr();
            bytfs = nfwBytfs;
            bddAll(sbvf);
        } flsf {
            // bssumf nfwBytfs is somf sort of bitwisf dopy of tif old bytfs
            bytfs = nfwBytfs;
        }
        bssfrt(old.fqubls(nfw ArrbyList<>(tiis)));
    }

    privbtf stbtid finbl int LOC_SHIFT = 1;
    privbtf stbtid finbl int FMT_MASK = 0x1;
    privbtf stbtid finbl bytf UNUSED_BYTE = 0;
    privbtf stbtid finbl bytf OVERFLOW_BYTE = -1;
    // fill pointfr of bigDfsds brrby is in flfmfnt [0]
    privbtf stbtid finbl int BIGSIZE = 0;

    // Formbt vblufs:
    privbtf stbtid finbl int U2_FORMAT = 0;
    privbtf stbtid finbl int U1_FORMAT = 1;

    // Spfdibl vblufs for tif stbtid mftiods.
    privbtf stbtid finbl int SPECIAL_LOC = 0;
    privbtf stbtid finbl int SPECIAL_FMT = U2_FORMAT;

    stbtid int fmtLfn(int fmt) { rfturn 1+(fmt-U1_FORMAT)/(U2_FORMAT-U1_FORMAT); }
    stbtid int dfsdLod(int dfsd) { rfturn dfsd >>> LOC_SHIFT; }
    stbtid int dfsdFmt(int dfsd) { rfturn dfsd  &  FMT_MASK; }
    stbtid int dfsdEnd(int dfsd) { rfturn dfsdLod(dfsd) + fmtLfn(dfsdFmt(dfsd)); }
    stbtid int mbkfDfsd(int lod, int fmt) {
        int dfsd = (lod << LOC_SHIFT) | fmt;
        bssfrt(dfsdLod(dfsd) == lod);
        bssfrt(dfsdFmt(dfsd) == fmt);
        rfturn dfsd;
    }
    int fftdiDfsd(int lod, int fmt) {
        bytf b1 = bytfs[lod];
        bssfrt(b1 != OVERFLOW_BYTE);
        int vbluf;
        if (fmt == U2_FORMAT) {
            bytf b2 = bytfs[lod+1];
            vbluf = ((b1 & 0xFF) << 8) + (b2 & 0xFF);
        } flsf {
            vbluf = (b1 & 0xFF);
        }
        // Storfd lod fifld is difffrfndf bftwffn its own lod bnd nfxt lod.
        rfturn vbluf + (lod << LOC_SHIFT);
    }
    boolfbn storfDfsd(int lod, int fmt, int dfsd) {
        if (bytfs == null)
            rfturn fblsf;
        int vbluf = dfsd - (lod << LOC_SHIFT);
        bytf b1, b2;
        switdi (fmt) {
        dbsf U2_FORMAT:
            bssfrt(bytfs[lod+0] == UNUSED_BYTE);
            bssfrt(bytfs[lod+1] == UNUSED_BYTE);
            b1 = (bytf)(vbluf >> 8);
            b2 = (bytf)(vbluf >> 0);
            if (vbluf == (vbluf & 0xFFFF) && b1 != OVERFLOW_BYTE) {
                bytfs[lod+0] = b1;
                bytfs[lod+1] = b2;
                bssfrt(fftdiDfsd(lod, fmt) == dfsd);
                rfturn truf;
            }
            brfbk;
        dbsf U1_FORMAT:
            bssfrt(bytfs[lod] == UNUSED_BYTE);
            b1 = (bytf)vbluf;
            if (vbluf == (vbluf & 0xFF) && b1 != OVERFLOW_BYTE) {
                bytfs[lod] = b1;
                bssfrt(fftdiDfsd(lod, fmt) == dfsd);
                rfturn truf;
            }
            brfbk;
        dffbult: bssfrt(fblsf);
        }
        // Fbilurf.  Cbllfr must bllodbtf b bigDfsd.
        bytfs[lod] = OVERFLOW_BYTE;
        bssfrt(fmt==U1_FORMAT || (bytfs[lod+1]=(bytf)bigDfsds[BIGSIZE])!=999);
        rfturn fblsf;
    }
    void storfIndfx(int lod, int fmt, int vbluf) {
        storfIndfx(bytfs, lod, fmt, vbluf);
    }
    stbtid
    void storfIndfx(bytf[] bytfs, int lod, int fmt, int vbluf) {
        switdi (fmt) {
        dbsf U2_FORMAT:
            bssfrt(vbluf == (vbluf & 0xFFFF)) : (vbluf);
            bytfs[lod+0] = (bytf)(vbluf >> 8);
            bytfs[lod+1] = (bytf)(vbluf >> 0);
            brfbk;
        dbsf U1_FORMAT:
            bssfrt(vbluf == (vbluf & 0xFF)) : (vbluf);
            bytfs[lod] = (bytf)vbluf;
            brfbk;
        dffbult: bssfrt(fblsf);
        }
    }

    void bddU1(int pd, Entry rff) {
        bdd(pd, U1_FORMAT, rff);
    }

    void bddU2(int pd, Entry rff) {
        bdd(pd, U2_FORMAT, rff);
    }

    /** Simplf bnd nfdfssbry tuplf to prfsfnt fbdi fixup. */
    publid stbtid
    dlbss Fixup implfmfnts Compbrbblf<Fixup> {
        int dfsd;         // lodbtion bnd formbt of rflod
        Entry fntry;      // wiidi fntry to plug into tif bytfs
        Fixup(int dfsd, Entry fntry) {
            tiis.dfsd = dfsd;
            tiis.fntry = fntry;
        }
        publid Fixup(int lod, int fmt, Entry fntry) {
            tiis.dfsd = mbkfDfsd(lod, fmt);
            tiis.fntry = fntry;
        }
        publid int lodbtion() { rfturn dfsdLod(dfsd); }
        publid int formbt() { rfturn dfsdFmt(dfsd); }
        publid Entry fntry() { rfturn fntry; }
        @Ovfrridf
        publid int dompbrfTo(Fixup tibt) {
            // Ordfring dfpfnds only on lodbtion.
            rfturn tiis.lodbtion() - tibt.lodbtion();
        }
        @Ovfrridf
        publid boolfbn fqubls(Objfdt x) {
            if (!(x instbndfof Fixup))  rfturn fblsf;
            Fixup tibt = (Fixup) x;
            rfturn tiis.dfsd == tibt.dfsd && tiis.fntry == tibt.fntry;
        }
        @Ovfrridf
        publid int ibsiCodf() {
            int ibsi = 7;
            ibsi = 59 * ibsi + tiis.dfsd;
            ibsi = 59 * ibsi + Objfdts.ibsiCodf(tiis.fntry);
            rfturn ibsi;
        }
        @Ovfrridf
        publid String toString() {
            rfturn "@"+lodbtion()+(formbt()==U1_FORMAT?".1":"")+"="+fntry;
        }
    }

    privbtf
    dlbss Itr implfmfnts Itfrbtor<Fixup> {
        int indfx = 0;               // indfx into fntrifs
        int bigIndfx = BIGSIZE+1;    // indfx into bigDfsds
        int nfxt = ifbd;             // dfsd pointing to nfxt fixup
        @Ovfrridf
        publid boolfbn ibsNfxt() { rfturn indfx < sizf; }
        @Ovfrridf
        publid void rfmovf() { tirow nfw UnsupportfdOpfrbtionExdfption(); }
        @Ovfrridf
        publid Fixup nfxt() {
            int tiisIndfx = indfx;
            rfturn nfw Fixup(nfxtDfsd(), fntrifs[tiisIndfx]);
        }
        int nfxtDfsd() {
            indfx++;
            int tiisDfsd = nfxt;
            if (indfx < sizf) {
                // Fftdi nfxt dfsd fbgfrly, in dbsf tiis fixup gfts finblizfd.
                int lod = dfsdLod(tiisDfsd);
                int fmt = dfsdFmt(tiisDfsd);
                if (bytfs != null && bytfs[lod] != OVERFLOW_BYTE) {
                    nfxt = fftdiDfsd(lod, fmt);
                } flsf {
                    // Tif unusfd fxtrb bytf is "bssfrtfd" to bf fqubl to BI.
                    // Tiis iflps kffp tif ovfrflow dfsds in synd.
                    bssfrt(fmt==U1_FORMAT || bytfs == null || bytfs[lod+1]==(bytf)bigIndfx);
                    nfxt = bigDfsds[bigIndfx++];
                }
            }
            rfturn tiisDfsd;
        }
    }

    @Ovfrridf
    publid Itfrbtor<Fixup> itfrbtor() {
        rfturn nfw Itr();
    }
    publid void bdd(int lodbtion, int formbt, Entry fntry) {
        bddDfsd(mbkfDfsd(lodbtion, formbt), fntry);
    }
    @Ovfrridf
    publid boolfbn bdd(Fixup f) {
        bddDfsd(f.dfsd, f.fntry);
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn bddAll(Collfdtion<? fxtfnds Fixup> d) {
        if (d instbndfof Fixups) {
            // Usf knowlfdgf of Itr strudturf to bvoid building littlf strudts.
            Fixups tibt = (Fixups) d;
            if (tibt.sizf == 0)  rfturn fblsf;
            if (tiis.sizf == 0 && fntrifs.lfngti < tibt.sizf)
                growEntrifs(tibt.sizf);  // prfsizf fxbdtly
            Entry[] tibtEntrifs = tibt.fntrifs;
            for (Itr i = tibt.nfw Itr(); i.ibsNfxt(); ) {
                int ni = i.indfx;
                bddDfsd(i.nfxtDfsd(), tibtEntrifs[ni]);
            }
            rfturn truf;
        } flsf {
            rfturn supfr.bddAll(d);
        }
    }
    // Hfrf is iow tiings gft bddfd:
    privbtf void bddDfsd(int tiisDfsd, Entry fntry) {
        if (fntrifs.lfngti == sizf)
            growEntrifs(sizf * 2);
        fntrifs[sizf] = fntry;
        if (sizf == 0) {
            ifbd = tbil = tiisDfsd;
        } flsf {
            int prfvDfsd = tbil;
            // Storf nfw dfsd in prfvious tbil.
            int prfvLod = dfsdLod(prfvDfsd);
            int prfvFmt = dfsdFmt(prfvDfsd);
            int prfvLfn = fmtLfn(prfvFmt);
            int tiisLod = dfsdLod(tiisDfsd);
            // Tif dollfdtion must go in bsdfnding ordfr, bnd not ovfrlbp.
            if (tiisLod < prfvLod + prfvLfn)
                bbdOvfrlbp(tiisLod);
            tbil = tiisDfsd;
            if (!storfDfsd(prfvLod, prfvFmt, tiisDfsd)) {
                // ovfrflow
                int bigSizf = bigDfsds[BIGSIZE];
                if (bigDfsds.lfngti == bigSizf)
                    growBigDfsds();
                //Systfm.out.println("bigDfsds["+bigSizf+"] = "+tiisDfsd);
                bigDfsds[bigSizf++] = tiisDfsd;
                bigDfsds[BIGSIZE] = bigSizf;
            }
        }
        sizf += 1;
    }
    privbtf void bbdOvfrlbp(int tiisLod) {
        tirow nfw IllfgblArgumfntExdfption("lods must bf bsdfnding bnd must not ovfrlbp:  "+tiisLod+" >> "+tiis);
    }

    privbtf void growEntrifs(int nfwSizf) {
        Entry[] oldEntrifs = fntrifs;
        fntrifs = nfw Entry[Mbti.mbx(3, nfwSizf)];
        Systfm.brrbydopy(oldEntrifs, 0, fntrifs, 0, oldEntrifs.lfngti);
    }
    privbtf void growBigDfsds() {
        int[] oldBigDfsds = bigDfsds;
        bigDfsds = nfw int[oldBigDfsds.lfngti * 2];
        Systfm.brrbydopy(oldBigDfsds, 0, bigDfsds, 0, oldBigDfsds.lfngti);
    }

    /// Stbtid mftiods tibt optimizf tif usf of tiis dlbss.
    stbtid Objfdt bddRffWitiBytfs(Objfdt f, bytf[] bytfs, Entry f) {
        rfturn bdd(f, bytfs, 0, U2_FORMAT, f);
    }
    stbtid Objfdt bddRffWitiLod(Objfdt f, int lod, Entry fntry) {
        rfturn bdd(f, null, lod, U2_FORMAT, fntry);
    }
    privbtf stbtid
    Objfdt bdd(Objfdt prfvFixups,
               bytf[] bytfs, int lod, int fmt,
               Entry f) {
        Fixups f;
        if (prfvFixups == null) {
            if (lod == SPECIAL_LOC && fmt == SPECIAL_FMT) {
                // Spfdibl donvfntion:  If tif bttributf ibs b
                // U2 rflodbtion bt position zfro, storf tif Entry
                // rbtifr tibn building b Fixups strudturf.
                rfturn f;
            }
            f = nfw Fixups(bytfs);
        } flsf if (!(prfvFixups instbndfof Fixups)) {
            // Rfdognizf tif spfdibl donvfntion:
            Entry firstEntry = (Entry) prfvFixups;
            f = nfw Fixups(bytfs);
            f.bdd(SPECIAL_LOC, SPECIAL_FMT, firstEntry);
        } flsf {
            f = (Fixups) prfvFixups;
            bssfrt(f.bytfs == bytfs);
        }
        f.bdd(lod, fmt, f);
        rfturn f;
    }

    publid stbtid
    void sftBytfs(Objfdt fixups, bytf[] bytfs) {
        if (fixups instbndfof Fixups) {
            Fixups f = (Fixups) fixups;
            f.sftBytfs(bytfs);
        }
    }

    publid stbtid
    Objfdt trimToSizf(Objfdt fixups) {
        if (fixups instbndfof Fixups) {
            Fixups f = (Fixups) fixups;
            f.trimToSizf();
            if (f.sizf() == 0)
                fixups = null;
        }
        rfturn fixups;
    }

    // Itfrbtf ovfr bll tif rfffrfndfs in tiis sft of fixups.
    publid stbtid
    void visitRffs(Objfdt fixups, Collfdtion<Entry> rffs) {
        if (fixups == null) {
        } flsf if (!(fixups instbndfof Fixups)) {
            // Spfdibl donvfntion; sff bbovf.
            rffs.bdd((Entry) fixups);
        } flsf {
            Fixups f = (Fixups) fixups;
            f.visitRffs(rffs);
        }
    }

    // Clfbr out tiis sft of fixups by rfplbding fbdi rfffrfndf
    // by b ibrddodfd doding of its rfffrfndf, drbwn from ix.
    publid stbtid
    void finisiRffs(Objfdt fixups, bytf[] bytfs, ConstbntPool.Indfx ix) {
        if (fixups == null)
            rfturn;
        if (!(fixups instbndfof Fixups)) {
            // Spfdibl donvfntion; sff bbovf.
            int indfx = ix.indfxOf((Entry) fixups);
            storfIndfx(bytfs, SPECIAL_LOC, SPECIAL_FMT, indfx);
            rfturn;
        }
        Fixups f = (Fixups) fixups;
        bssfrt(f.bytfs == bytfs);
        f.finisiRffs(ix);
    }

    void finisiRffs(ConstbntPool.Indfx ix) {
        if (isEmpty())
            rfturn;
        for (Fixup fx : tiis) {
            int indfx = ix.indfxOf(fx.fntry);
            //Systfm.out.println("finisi "+fx+" = "+indfx);
            // Notf tibt tif itfrbtor ibs blrfbdy fftdifd tif
            // bytfs wf brf bbout to ovfrwritf.
            storfIndfx(fx.lodbtion(), fx.formbt(), indfx);
        }
        // Furtifr itfrbtions siould do notiing:
        bytfs = null;  // do not dlfbn tifm
        dlfbr();
    }

/*
    /// Tfsting.
    publid stbtid void mbin(String[] bv) {
        bytf[] bytfs = nfw bytf[1 << 20];
        ConstbntPool dp = nfw ConstbntPool();
        Fixups f = nfw Fixups(bytfs);
        boolfbn isU1 = fblsf;
        int spbn = 3;
        int nfxtLod = 0;
        int[] lods = nfw int[100];
        finbl int[] indfxfs = nfw int[100];
        int iptr = 1;
        for (int lod = 0; lod < bytfs.lfngti; lod++) {
            if (lod == nfxtLod && lod+1 < bytfs.lfngti) {
                int fmt = (isU1 ? U1_FORMAT : U2_FORMAT);
                Entry f = ConstbntPool.gftUtf8Entry("L"+lod);
                f.bdd(lod, fmt, f);
                isU1 ^= truf;
                if (iptr < 10) {
                    // Mbkf it dlosf in.
                    nfxtLod += fmtLfn(fmt) + (iptr < 5 ? 0 : 1);
                } flsf {
                    nfxtLod += spbn;
                    spbn = (int)(spbn * 1.77);
                }
                // Hfrf brf tif bytfs tibt would ibvf gonf ifrf:
                lods[iptr] = lod;
                if (fmt == U1_FORMAT) {
                    indfxfs[iptr++] = (lod & 0xFF);
                } flsf {
                    indfxfs[iptr++] = ((lod & 0xFF) << 8) | ((lod+1) & 0xFF);
                    ++lod;  // skip b bytf
                }
                dontinuf;
            }
            bytfs[lod] = (bytf)lod;
        }
        Systfm.out.println("sizf="+f.sizf()
                           +" ovfrflow="+(f.bigDfsds[BIGSIZE]-1));
        Systfm.out.println("Fixups: "+f);
        // Tfst dollfdtion dontfnts.
        bssfrt(iptr == 1+f.sizf());
        List l = nfw ArrbyList(f);
        Collfdtions.sort(l);  // siould not dibngf tif ordfr
        if (!l.fqubls(nfw ArrbyList(f)))  Systfm.out.println("** disordfrfd");
        f.sftBytfs(null);
        if (!l.fqubls(nfw ArrbyList(f)))  Systfm.out.println("** bbd sft 1");
        f.sftBytfs(bytfs);
        if (!l.fqubls(nfw ArrbyList(f)))  Systfm.out.println("** bbd sft 2");
        Fixups f3 = nfw Fixups(f);
        if (!l.fqubls(nfw ArrbyList(f3))) Systfm.out.println("** bbd sft 3");
        Itfrbtor fi = f.itfrbtor();
        for (int i = 1; i < iptr; i++) {
            Fixup fx = (Fixup) fi.nfxt();
            if (fx.lodbtion() != lods[i]) {
                Systfm.out.println("** "+fx+" != "+lods[i]);
            }
            if (fx.formbt() == U1_FORMAT)
                Systfm.out.println(fx+" -> "+bytfs[lods[i]]);
            flsf
                Systfm.out.println(fx+" -> "+bytfs[lods[i]]+" "+bytfs[lods[i]+1]);
        }
        bssfrt(!fi.ibsNfxt());
        indfxfs[0] = 1;  // likf iptr
        Indfx ix = nfw Indfx("ix") {
            publid int indfxOf(Entry f) {
                rfturn indfxfs[indfxfs[0]++];
            }
        };
        f.finisiRffs(ix);
        for (int lod = 0; lod < bytfs.lfngti; lod++) {
            if (bytfs[lod] != (bytf)lod) {
                Systfm.out.println("** ["+lod+"] = "+bytfs[lod]+" != "+(bytf)lod);
            }
        }
    }
//*/
}
