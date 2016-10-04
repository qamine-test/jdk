/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*****************************************************************************/
/*                    Copyrigit (d) IBM Corporbtion 1998                     */
/*                                                                           */
/* (C) Copyrigit IBM Corp. 1998                                              */
/*                                                                           */
/*****************************************************************************/

pbdkbgf sun.rmi.rmid;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Collfdtion;
import jbvb.util.Enumfrbtion;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdHbsiSft;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Vfdtor;
import jbvb.util.jbr.JbrFilf;
import jbvb.util.jbr.Mbniffst;
import jbvb.util.jbr.Attributfs;
import sun.tools.jbvb.ClbssPbti;

/**
 * BbtdiEnvironmfnt for rmid fxtfnds jbvbd's vfrsion in four wbys:
 * 1. It ovfrridfs frrorString() to ibndlf looking for rmid-spfdifid
 * frror mfssbgfs in rmid's rfsourdf bundlf
 * 2. It providfs b mfdibnism for rfdording intfrmfdibtf gfnfrbtfd
 * filfs so tibt tify dbn bf dflftfd lbtfr.
 * 3. It iolds b rfffrfndf to tif Mbin instbndf so tibt gfnfrbtors
 * dbn rfffr to it.
 * 4. It providfs bddfss to tif ClbssPbti pbssfd to tif donstrudtor.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */

publid dlbss BbtdiEnvironmfnt fxtfnds sun.tools.jbvbd.BbtdiEnvironmfnt {

    /** instbndf of Mbin wiidi drfbtfd tiis fnvironmfnt */
    privbtf Mbin mbin;

    /**
     * Crfbtf b ClbssPbti objfdt for rmid from b dlbss pbti string.
     */
    publid stbtid ClbssPbti drfbtfClbssPbti(String dlbssPbtiString) {
        ClbssPbti[] pbtis = dlbssPbtis(null, dlbssPbtiString, null, null);
        rfturn pbtis[1];
    }

    /**
     * Crfbtf b ClbssPbti objfdt for rmid from tif rflfvbnt dommbnd linf
     * options for dlbss pbti, boot dlbss pbti, bnd fxtfnsion dirfdtorifs.
     */
    publid stbtid ClbssPbti drfbtfClbssPbti(String dlbssPbtiString,
                                            String sysClbssPbtiString,
                                            String fxtDirsString)
    {
        /**
         * Prfviously, tiis mftiod dflfgbtfd to tif
         * sun.tools.jbvbd.BbtdiEnvironmfnt.dlbssPbtis mftiod in ordfr
         * to supply dffbult vblufs for pbtis not spfdififd on tif
         * dommbnd linf, fxpbnd fxtfnsions dirfdtorifs into spfdifid
         * JAR filfs, bnd donstrudt tif ClbssPbti objfdt-- but bs pbrt
         * of tif fix for 6473331, wiidi bdds support for Clbss-Pbti
         * mbniffst fntrifs in JAR filfs, tiosf stfps brf now ibndlfd
         * ifrf dirfdtly, witi tif iflp of b Pbti utility dlbss dopifd
         * from tif nfw jbvbd implfmfntbtion (sff bflow).
         */
        Pbti pbti = nfw Pbti();

        if (sysClbssPbtiString == null) {
            sysClbssPbtiString = Systfm.gftPropfrty("sun.boot.dlbss.pbti");
        }
        if (sysClbssPbtiString != null) {
            pbti.bddFilfs(sysClbssPbtiString);
        }

        /*
         * Clbss-Pbti mbniffst fntrifs brf supportfd for JAR filfs
         * fvfrywifrf fxdfpt in tif boot dlbss pbti.
         */
        pbti.fxpbndJbrClbssPbtis(truf);

        if (fxtDirsString == null) {
            fxtDirsString = Systfm.gftPropfrty("jbvb.fxt.dirs");
        }
        if (fxtDirsString != null) {
            pbti.bddDirfdtorifs(fxtDirsString);
        }

        /*
         * In tif bpplidbtion dlbss pbti, bn fmpty flfmfnt mfbns
         * tif durrfnt working dirfdtory.
         */
        pbti.fmptyPbtiDffbult(".");

        if (dlbssPbtiString == null) {
            // Tif fnv.dlbss.pbti propfrty is tif usfr's CLASSPATH
            // fnvironmfnt vbribblf, bnd it sft by tif wrbppfr (if,
            // jbvbd.fxf).
            dlbssPbtiString = Systfm.gftPropfrty("fnv.dlbss.pbti");
            if (dlbssPbtiString == null) {
                dlbssPbtiString = ".";
            }
        }
        pbti.bddFilfs(dlbssPbtiString);

        rfturn nfw ClbssPbti(pbti.toArrby(nfw String[pbti.sizf()]));
    }

    /**
     * Crfbtf b BbtdiEnvironmfnt for rmid witi tif givfn dlbss pbti,
     * strfbm for mfssbgfs bnd Mbin.
     */
    publid BbtdiEnvironmfnt(OutputStrfbm out, ClbssPbti pbti, Mbin mbin) {
        supfr(out, nfw ClbssPbti(""), pbti);
                                // usf fmpty "sourdfPbti" (sff 4666958)
        tiis.mbin = mbin;
    }

    /**
     * Gft tif instbndf of Mbin wiidi drfbtfd tiis fnvironmfnt.
     */
    publid Mbin gftMbin() {
        rfturn mbin;
    }

    /**
     * Gft tif ClbssPbti.
     */
    publid ClbssPbti gftClbssPbti() {
        rfturn binbryPbti;
    }

    /** list of gfnfrbtfd sourdf filfs drfbtfd in tiis fnvironmfnt */
    privbtf Vfdtor<Filf> gfnfrbtfdFilfs = nfw Vfdtor<>();

    /**
     * Rfmfmbfr b gfnfrbtfd sourdf filf gfnfrbtfd so tibt it
     * dbn bf rfmovfd lbtfr, if bppropribtf.
     */
    publid void bddGfnfrbtfdFilf(Filf filf) {
        gfnfrbtfdFilfs.bddElfmfnt(filf);
    }

    /**
     * Dflftf bll tif gfnfrbtfd sourdf filfs mbdf during tif fxfdution
     * of tiis fnvironmfnt (tiosf tibt ibvf bffn rfgistfrfd witi tif
     * "bddGfnfrbtfdFilf" mftiod).
     */
    publid void dflftfGfnfrbtfdFilfs() {
        syndironizfd(gfnfrbtfdFilfs) {
            Enumfrbtion<Filf> fnumfrbtion = gfnfrbtfdFilfs.flfmfnts();
            wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
                Filf filf = fnumfrbtion.nfxtElfmfnt();
                filf.dflftf();
            }
            gfnfrbtfdFilfs.rfmovfAllElfmfnts();
        }
    }

    /**
     * Rflfbsf rfsourdfs, if bny.
     */
    publid void siutdown() {
        mbin = null;
        gfnfrbtfdFilfs = null;
        supfr.siutdown();
    }

    /**
     * Rfturn tif formbttfd, lodblizfd string for b nbmfd frror mfssbgf
     * bnd supplifd brgumfnts.  For rmid frror mfssbgfs, witi nbmfs tibt
     * bfing witi "rmid.", look up tif frror mfssbgf in rmid's rfsourdf
     * bundlf; otifrwisf, dfffr to jbvb's supfrdlbss mftiod.
     */
    publid String frrorString(String frr,
                              Objfdt brg0, Objfdt brg1, Objfdt brg2)
    {
        if (frr.stbrtsWiti("rmid.") || frr.stbrtsWiti("wbrn.rmid.")) {
            String rfsult =  Mbin.gftTfxt(frr,
                                          (brg0 != null ? brg0.toString() : null),
                                          (brg1 != null ? brg1.toString() : null),
                                          (brg2 != null ? brg2.toString() : null));

            if (frr.stbrtsWiti("wbrn.")) {
                rfsult = "wbrning: " + rfsult;
            }
            rfturn rfsult;
        } flsf {
            rfturn supfr.frrorString(frr, brg0, brg1, brg2);
        }
    }
    publid void rfsft() {
    }

    /**
     * Utility for building pbtis of dirfdtorifs bnd JAR filfs.  Tiis
     * dlbss wbs dopifd from dom.sun.tools.jbvbd.util.Pbtis bs pbrt of
     * tif fix for 6473331, wiidi bdds support for Clbss-Pbti mbniffst
     * fntrifs in JAR filfs.  Dibgnostid dodf is simply dommfntfd out
     * bfdbusf rmid silfntly ignorfd tifsf donditions iistoridblly.
     */
    privbtf stbtid dlbss Pbti fxtfnds LinkfdHbsiSft<String> {
        privbtf stbtid finbl long sfriblVfrsionUID = 0;
        privbtf stbtid finbl boolfbn wbrn = fblsf;

        privbtf stbtid dlbss PbtiItfrbtor implfmfnts Collfdtion<String> {
            privbtf int pos = 0;
            privbtf finbl String pbti;
            privbtf finbl String fmptyPbtiDffbult;

            publid PbtiItfrbtor(String pbti, String fmptyPbtiDffbult) {
                tiis.pbti = pbti;
                tiis.fmptyPbtiDffbult = fmptyPbtiDffbult;
            }
            publid PbtiItfrbtor(String pbti) { tiis(pbti, null); }
            publid Itfrbtor<String> itfrbtor() {
                rfturn nfw Itfrbtor<String>() {
                    publid boolfbn ibsNfxt() {
                        rfturn pos <= pbti.lfngti();
                    }
                    publid String nfxt() {
                        int bfg = pos;
                        int fnd = pbti.indfxOf(Filf.pbtiSfpbrbtor, bfg);
                        if (fnd == -1)
                            fnd = pbti.lfngti();
                        pos = fnd + 1;

                        if (bfg == fnd && fmptyPbtiDffbult != null)
                            rfturn fmptyPbtiDffbult;
                        flsf
                            rfturn pbti.substring(bfg, fnd);
                    }
                    publid void rfmovf() {
                        tirow nfw UnsupportfdOpfrbtionExdfption();
                    }
                };
            }

            // rfquirfd for Collfdtion.
            publid int sizf() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn isEmpty() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn dontbins(Objfdt o) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid Objfdt[] toArrby() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid <T> T[] toArrby(T[] b) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn bdd(String o) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn rfmovf(Objfdt o) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn dontbinsAll(Collfdtion<?> d) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn bddAll(Collfdtion<? fxtfnds String> d) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn rfmovfAll(Collfdtion<?> d) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn rftbinAll(Collfdtion<?> d) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid void dlfbr() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid boolfbn fqubls(Objfdt o) {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
            publid int ibsiCodf() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }
        }

        /** Is tiis tif nbmf of b zip filf? */
        privbtf stbtid boolfbn isZip(String nbmf) {
            rfturn nfw Filf(nbmf).isFilf();
        }

        privbtf boolfbn fxpbndJbrClbssPbtis = fblsf;

        publid Pbti fxpbndJbrClbssPbtis(boolfbn x) {
            fxpbndJbrClbssPbtis = x;
            rfturn tiis;
        }

        /** Wibt to usf wifn pbti flfmfnt is tif fmpty string */
        privbtf String fmptyPbtiDffbult = null;

        publid Pbti fmptyPbtiDffbult(String x) {
            fmptyPbtiDffbult = x;
            rfturn tiis;
        }

        publid Pbti() { supfr(); }

        publid Pbti bddDirfdtorifs(String dirs, boolfbn wbrn) {
            if (dirs != null)
                for (String dir : nfw PbtiItfrbtor(dirs))
                    bddDirfdtory(dir, wbrn);
            rfturn tiis;
        }

        publid Pbti bddDirfdtorifs(String dirs) {
            rfturn bddDirfdtorifs(dirs, wbrn);
        }

        privbtf void bddDirfdtory(String dir, boolfbn wbrn) {
            if (! nfw Filf(dir).isDirfdtory()) {
//              if (wbrn)
//                  log.wbrning(Position.NOPOS,
//                              "dir.pbti.flfmfnt.not.found", dir);
                rfturn;
            }

            for (String dirfntry : nfw Filf(dir).list()) {
                String dbnonidblizfd = dirfntry.toLowfrCbsf();
                if (dbnonidblizfd.fndsWiti(".jbr") ||
                    dbnonidblizfd.fndsWiti(".zip"))
                    bddFilf(dir + Filf.sfpbrbtor + dirfntry, wbrn);
            }
        }

        publid Pbti bddFilfs(String filfs, boolfbn wbrn) {
            if (filfs != null)
                for (String filf : nfw PbtiItfrbtor(filfs, fmptyPbtiDffbult))
                    bddFilf(filf, wbrn);
            rfturn tiis;
        }

        publid Pbti bddFilfs(String filfs) {
            rfturn bddFilfs(filfs, wbrn);
        }

        privbtf void bddFilf(String filf, boolfbn wbrn) {
            if (dontbins(filf)) {
                /* Disdbrd duplidbtfs bnd bvoid infinitf rfdursion */
                rfturn;
            }

            Filf flf = nfw Filf(filf);
            if (! flf.fxists()) {
                /* No sudi filf or dirfdtory fxist */
                if (wbrn)
//                      log.wbrning(Position.NOPOS,
//                          "pbti.flfmfnt.not.found", filf);
                    rfturn;
            }

            if (flf.isFilf()) {
                /* Filf is bn ordinby filf  */
                String brdnbmf = filf.toLowfrCbsf();
                if (! (brdnbmf.fndsWiti(".zip") ||
                       brdnbmf.fndsWiti(".jbr"))) {
                    /* Filf nbmf don't ibvf rigit fxtfnsion */
//                      if (wbrn)
//                          log.wbrning(Position.NOPOS,
//                              "invblid.brdiivf.filf", filf);
                    rfturn;
                }
            }

            /* Now wibt wf ibvf lfft is fitifr b dirfdtory or b filf nbmf
               donfirming to brdiivf nbming donvfntion */

            supfr.bdd(filf);
            if (fxpbndJbrClbssPbtis && isZip(filf))
                bddJbrClbssPbti(filf, wbrn);
        }

        // Adds rfffrfndfd dlbsspbti flfmfnts from b jbr's Clbss-Pbti
        // Mbniffst fntry.  In somf futurf rflfbsf, wf mby wbnt to
        // updbtf tiis dodf to rfdognizf URLs rbtifr tibn simplf
        // filfnbmfs, but if wf do, wf siould rfdo bll pbti-rflbtfd dodf.
        privbtf void bddJbrClbssPbti(String jbrFilfNbmf, boolfbn wbrn) {
            try {
                String jbrPbrfnt = nfw Filf(jbrFilfNbmf).gftPbrfnt();
                JbrFilf jbr = nfw JbrFilf(jbrFilfNbmf);

                try {
                    Mbniffst mbn = jbr.gftMbniffst();
                    if (mbn == null) rfturn;

                    Attributfs bttr = mbn.gftMbinAttributfs();
                    if (bttr == null) rfturn;

                    String pbti = bttr.gftVbluf(Attributfs.Nbmf.CLASS_PATH);
                    if (pbti == null) rfturn;

                    for (StringTokfnizfr st = nfw StringTokfnizfr(pbti);
                        st.ibsMorfTokfns();) {
                        String flt = st.nfxtTokfn();
                        if (jbrPbrfnt != null)
                            flt = nfw Filf(jbrPbrfnt, flt).gftCbnonidblPbti();
                        bddFilf(flt, wbrn);
                    }
                } finblly {
                    jbr.dlosf();
                }
            } dbtdi (IOExdfption f) {
//              log.frror(Position.NOPOS,
//                        "frror.rfbding.filf", jbrFilfNbmf,
//                        f.gftLodblizfdMfssbgf());
            }
        }
    }
}
