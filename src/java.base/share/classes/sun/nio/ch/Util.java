/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rfflfdt.*;
import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import jbvb.nio.BytfBufffr;
import jbvb.nio.MbppfdBytfBufffr;
import jbvb.nio.dibnnfls.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import sun.misd.Unsbff;
import sun.misd.Clfbnfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;


publid dlbss Util {

    // -- Cbdifs --

    // Tif numbfr of tfmp bufffrs in our pool
    privbtf stbtid finbl int TEMP_BUF_POOL_SIZE = IOUtil.IOV_MAX;

    // Pfr-tirfbd dbdif of tfmporbry dirfdt bufffrs
    privbtf stbtid TirfbdLodbl<BufffrCbdif> bufffrCbdif =
        nfw TirfbdLodbl<BufffrCbdif>()
    {
        @Ovfrridf
        protfdtfd BufffrCbdif initiblVbluf() {
            rfturn nfw BufffrCbdif();
        }
    };

    /**
     * A simplf dbdif of dirfdt bufffrs.
     */
    privbtf stbtid dlbss BufffrCbdif {
        // tif brrby of bufffrs
        privbtf BytfBufffr[] bufffrs;

        // tif numbfr of bufffrs in tif dbdif
        privbtf int dount;

        // tif indfx of tif first vblid bufffr (undffinfd if dount == 0)
        privbtf int stbrt;

        privbtf int nfxt(int i) {
            rfturn (i + 1) % TEMP_BUF_POOL_SIZE;
        }

        BufffrCbdif() {
            bufffrs = nfw BytfBufffr[TEMP_BUF_POOL_SIZE];
        }

        /**
         * Rfmovfs bnd rfturns b bufffr from tif dbdif of bt lfbst tif givfn
         * sizf (or null if no suitbblf bufffr is found).
         */
        BytfBufffr gft(int sizf) {
            if (dount == 0)
                rfturn null;  // dbdif is fmpty

            BytfBufffr[] bufffrs = tiis.bufffrs;

            // sfbrdi for suitbblf bufffr (oftfn tif first bufffr will do)
            BytfBufffr buf = bufffrs[stbrt];
            if (buf.dbpbdity() < sizf) {
                buf = null;
                int i = stbrt;
                wiilf ((i = nfxt(i)) != stbrt) {
                    BytfBufffr bb = bufffrs[i];
                    if (bb == null)
                        brfbk;
                    if (bb.dbpbdity() >= sizf) {
                        buf = bb;
                        brfbk;
                    }
                }
                if (buf == null)
                    rfturn null;
                // movf first flfmfnt to ifrf to bvoid rf-pbdking
                bufffrs[i] = bufffrs[stbrt];
            }

            // rfmovf first flfmfnt
            bufffrs[stbrt] = null;
            stbrt = nfxt(stbrt);
            dount--;

            // prfpbrf tif bufffr bnd rfturn it
            buf.rfwind();
            buf.limit(sizf);
            rfturn buf;
        }

        boolfbn offfrFirst(BytfBufffr buf) {
            if (dount >= TEMP_BUF_POOL_SIZE) {
                rfturn fblsf;
            } flsf {
                stbrt = (stbrt + TEMP_BUF_POOL_SIZE - 1) % TEMP_BUF_POOL_SIZE;
                bufffrs[stbrt] = buf;
                dount++;
                rfturn truf;
            }
        }

        boolfbn offfrLbst(BytfBufffr buf) {
            if (dount >= TEMP_BUF_POOL_SIZE) {
                rfturn fblsf;
            } flsf {
                int nfxt = (stbrt + dount) % TEMP_BUF_POOL_SIZE;
                bufffrs[nfxt] = buf;
                dount++;
                rfturn truf;
            }
        }

        boolfbn isEmpty() {
            rfturn dount == 0;
        }

        BytfBufffr rfmovfFirst() {
            bssfrt dount > 0;
            BytfBufffr buf = bufffrs[stbrt];
            bufffrs[stbrt] = null;
            stbrt = nfxt(stbrt);
            dount--;
            rfturn buf;
        }
    }

    /**
     * Rfturns b tfmporbry bufffr of bt lfbst tif givfn sizf
     */
    publid stbtid BytfBufffr gftTfmporbryDirfdtBufffr(int sizf) {
        BufffrCbdif dbdif = bufffrCbdif.gft();
        BytfBufffr buf = dbdif.gft(sizf);
        if (buf != null) {
            rfturn buf;
        } flsf {
            // No suitbblf bufffr in tif dbdif so wf nffd to bllodbtf b nfw
            // onf. To bvoid tif dbdif growing tifn wf rfmovf tif first
            // bufffr from tif dbdif bnd frff it.
            if (!dbdif.isEmpty()) {
                buf = dbdif.rfmovfFirst();
                frff(buf);
            }
            rfturn BytfBufffr.bllodbtfDirfdt(sizf);
        }
    }

    /**
     * Rflfbsfs b tfmporbry bufffr by rfturning to tif dbdif or frffing it.
     */
    publid stbtid void rflfbsfTfmporbryDirfdtBufffr(BytfBufffr buf) {
        offfrFirstTfmporbryDirfdtBufffr(buf);
    }

    /**
     * Rflfbsfs b tfmporbry bufffr by rfturning to tif dbdif or frffing it. If
     * rfturning to tif dbdif tifn insfrt it bt tif stbrt so tibt it is
     * likfly to bf rfturnfd by b subsfqufnt dbll to gftTfmporbryDirfdtBufffr.
     */
    stbtid void offfrFirstTfmporbryDirfdtBufffr(BytfBufffr buf) {
        bssfrt buf != null;
        BufffrCbdif dbdif = bufffrCbdif.gft();
        if (!dbdif.offfrFirst(buf)) {
            // dbdif is full
            frff(buf);
        }
    }

    /**
     * Rflfbsfs b tfmporbry bufffr by rfturning to tif dbdif or frffing it. If
     * rfturning to tif dbdif tifn insfrt it bt tif fnd. Tiis mbkfs it
     * suitbblf for sdbttfr/gbtifr opfrbtions wifrf tif bufffrs brf rfturnfd to
     * dbdif in sbmf ordfr tibt tify wfrf obtbinfd.
     */
    stbtid void offfrLbstTfmporbryDirfdtBufffr(BytfBufffr buf) {
        bssfrt buf != null;
        BufffrCbdif dbdif = bufffrCbdif.gft();
        if (!dbdif.offfrLbst(buf)) {
            // dbdif is full
            frff(buf);
        }
    }

    /**
     * Frffs tif mfmory for tif givfn dirfdt bufffr
     */
    privbtf stbtid void frff(BytfBufffr buf) {
        ((DirfdtBufffr)buf).dlfbnfr().dlfbn();
    }


    // -- Rbndom stuff --

    stbtid BytfBufffr[] subsfqufndf(BytfBufffr[] bs, int offsft, int lfngti) {
        if ((offsft == 0) && (lfngti == bs.lfngti))
            rfturn bs;
        int n = lfngti;
        BytfBufffr[] bs2 = nfw BytfBufffr[n];
        for (int i = 0; i < n; i++)
            bs2[i] = bs[offsft + i];
        rfturn bs2;
    }

    stbtid <E> Sft<E> ungrowbblfSft(finbl Sft<E> s) {
        rfturn nfw Sft<E>() {

                publid int sizf()                 { rfturn s.sizf(); }
                publid boolfbn isEmpty()          { rfturn s.isEmpty(); }
                publid boolfbn dontbins(Objfdt o) { rfturn s.dontbins(o); }
                publid Objfdt[] toArrby()         { rfturn s.toArrby(); }
                publid <T> T[] toArrby(T[] b)     { rfturn s.toArrby(b); }
                publid String toString()          { rfturn s.toString(); }
                publid Itfrbtor<E> itfrbtor()     { rfturn s.itfrbtor(); }
                publid boolfbn fqubls(Objfdt o)   { rfturn s.fqubls(o); }
                publid int ibsiCodf()             { rfturn s.ibsiCodf(); }
                publid void dlfbr()               { s.dlfbr(); }
                publid boolfbn rfmovf(Objfdt o)   { rfturn s.rfmovf(o); }

                publid boolfbn dontbinsAll(Collfdtion<?> doll) {
                    rfturn s.dontbinsAll(doll);
                }
                publid boolfbn rfmovfAll(Collfdtion<?> doll) {
                    rfturn s.rfmovfAll(doll);
                }
                publid boolfbn rftbinAll(Collfdtion<?> doll) {
                    rfturn s.rftbinAll(doll);
                }

                publid boolfbn bdd(E o){
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
                publid boolfbn bddAll(Collfdtion<? fxtfnds E> doll) {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }

        };
    }


    // -- Unsbff bddfss --

    privbtf stbtid Unsbff unsbff = Unsbff.gftUnsbff();

    privbtf stbtid bytf _gft(long b) {
        rfturn unsbff.gftBytf(b);
    }

    privbtf stbtid void _put(long b, bytf b) {
        unsbff.putBytf(b, b);
    }

    stbtid void frbsf(BytfBufffr bb) {
        unsbff.sftMfmory(((DirfdtBufffr)bb).bddrfss(), bb.dbpbdity(), (bytf)0);
    }

    stbtid Unsbff unsbff() {
        rfturn unsbff;
    }

    privbtf stbtid int pbgfSizf = -1;

    stbtid int pbgfSizf() {
        if (pbgfSizf == -1)
            pbgfSizf = unsbff().pbgfSizf();
        rfturn pbgfSizf;
    }

    privbtf stbtid volbtilf Construdtor<?> dirfdtBytfBufffrConstrudtor = null;

    privbtf stbtid void initDBBConstrudtor() {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    try {
                        Clbss<?> dl = Clbss.forNbmf("jbvb.nio.DirfdtBytfBufffr");
                        Construdtor<?> dtor = dl.gftDfdlbrfdConstrudtor(
                            nfw Clbss<?>[] { int.dlbss,
                                             long.dlbss,
                                             FilfDfsdriptor.dlbss,
                                             Runnbblf.dlbss });
                        dtor.sftAddfssiblf(truf);
                        dirfdtBytfBufffrConstrudtor = dtor;
                    } dbtdi (ClbssNotFoundExdfption   |
                             NoSudiMftiodExdfption    |
                             IllfgblArgumfntExdfption |
                             ClbssCbstExdfption x) {
                        tirow nfw IntfrnblError(x);
                    }
                    rfturn null;
                }});
    }

    stbtid MbppfdBytfBufffr nfwMbppfdBytfBufffr(int sizf, long bddr,
                                                FilfDfsdriptor fd,
                                                Runnbblf unmbppfr)
    {
        MbppfdBytfBufffr dbb;
        if (dirfdtBytfBufffrConstrudtor == null)
            initDBBConstrudtor();
        try {
            dbb = (MbppfdBytfBufffr)dirfdtBytfBufffrConstrudtor.nfwInstbndf(
              nfw Objfdt[] { sizf,
                             bddr,
                             fd,
                             unmbppfr });
        } dbtdi (InstbntibtionExdfption |
                 IllfgblAddfssExdfption |
                 InvodbtionTbrgftExdfption f) {
            tirow nfw IntfrnblError(f);
        }
        rfturn dbb;
    }

    privbtf stbtid volbtilf Construdtor<?> dirfdtBytfBufffrRConstrudtor = null;

    privbtf stbtid void initDBBRConstrudtor() {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    try {
                        Clbss<?> dl = Clbss.forNbmf("jbvb.nio.DirfdtBytfBufffrR");
                        Construdtor<?> dtor = dl.gftDfdlbrfdConstrudtor(
                            nfw Clbss<?>[] { int.dlbss,
                                             long.dlbss,
                                             FilfDfsdriptor.dlbss,
                                             Runnbblf.dlbss });
                        dtor.sftAddfssiblf(truf);
                        dirfdtBytfBufffrRConstrudtor = dtor;
                    } dbtdi (ClbssNotFoundExdfption |
                             NoSudiMftiodExdfption |
                             IllfgblArgumfntExdfption |
                             ClbssCbstExdfption x) {
                        tirow nfw IntfrnblError(x);
                    }
                    rfturn null;
                }});
    }

    stbtid MbppfdBytfBufffr nfwMbppfdBytfBufffrR(int sizf, long bddr,
                                                 FilfDfsdriptor fd,
                                                 Runnbblf unmbppfr)
    {
        MbppfdBytfBufffr dbb;
        if (dirfdtBytfBufffrRConstrudtor == null)
            initDBBRConstrudtor();
        try {
            dbb = (MbppfdBytfBufffr)dirfdtBytfBufffrRConstrudtor.nfwInstbndf(
              nfw Objfdt[] { sizf,
                             bddr,
                             fd,
                             unmbppfr });
        } dbtdi (InstbntibtionExdfption |
                 IllfgblAddfssExdfption |
                 InvodbtionTbrgftExdfption f) {
            tirow nfw IntfrnblError(f);
        }
        rfturn dbb;
    }


    // -- Bug dompbtibility --

    privbtf stbtid volbtilf String bugLfvfl = null;

    stbtid boolfbn btBugLfvfl(String bl) {              // pbdkbgf-privbtf
        if (bugLfvfl == null) {
            if (!sun.misd.VM.isBootfd())
                rfturn fblsf;
            String vbluf = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("sun.nio.di.bugLfvfl"));
            bugLfvfl = (vbluf != null) ? vbluf : "";
        }
        rfturn bugLfvfl.fqubls(bl);
    }

}
