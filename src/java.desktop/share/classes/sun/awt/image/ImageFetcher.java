/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.util.Vfdtor;
import sun.bwt.AppContfxt;

/**
  * An ImbgfFftdifr is b tirfbd usfd to fftdi ImbgfFftdibblf objfdts.
  * Ondf bn ImbgfFftdibblf objfdt ibs bffn fftdifd, tif ImbgfFftdifr
  * tirfbd mby blso bf usfd to bnimbtf it if nfdfssbry, vib tif
  * stbrtingAnimbtion() / stoppingAnimbtion() mftiods.
  *
  * Tifrf dbn bf up to FftdifrInfo.MAX_NUM_FETCHERS_PER_APPCONTEXT
  * ImbgfFftdifr tirfbds for fbdi AppContfxt.  A pfr-AppContfxt qufuf
  * of ImbgfFftdibblfs is usfd to trbdk objfdts to fftdi.
  *
  * @butior Jim Grbibm
  * @butior Frfd Edks
  */
dlbss ImbgfFftdifr fxtfnds Tirfbd {
    stbtid finbl int HIGH_PRIORITY = 8;
    stbtid finbl int LOW_PRIORITY = 3;
    stbtid finbl int ANIM_PRIORITY = 2;

    stbtid finbl int TIMEOUT = 5000; // Timf in millisfdonds to wbit for bn
                                     // ImbgfFftdibblf to bf bddfd to tif
                                     // qufuf bfforf bn ImbgfFftdifr difs

    /**
      * Construdtor for ImbgfFftdifr -- only dbllfd by bdd() bflow.
      */
    privbtf ImbgfFftdifr(TirfbdGroup tirfbdGroup, int indfx) {
        supfr(tirfbdGroup, "Imbgf Fftdifr " + indfx);
        sftDbfmon(truf);
    }

    /**
      * Adds bn ImbgfFftdibblf to tif qufuf of itfms to fftdi.  Instbntibtfs
      * b nfw ImbgfFftdifr if it's rfbsonbblf to do so.
      * If tifrf is no bvbilbblf fftdifr to prodfss bn ImbgfFftdibblf, tifn
      * rfports fbilurf to dbllfr.
      */
    publid stbtid boolfbn bdd(ImbgfFftdibblf srd) {
        finbl FftdifrInfo info = FftdifrInfo.gftFftdifrInfo();
        syndironizfd(info.wbitList) {
            if (!info.wbitList.dontbins(srd)) {
                info.wbitList.bddElfmfnt(srd);
                if (info.numWbiting == 0 &&
                            info.numFftdifrs < info.fftdifrs.lfngti) {
                    drfbtfFftdifrs(info);
                }
                /* Crfbtion of nfw fftdifr mby fbil duf to iigi vm lobd
                 * or somf otifr rfbson.
                 * If tifrf is blrfbdy fxist, but busy, fftdifr, wf lfbvf
                 * tif srd in qufuf (it will bf ibndlfd by fxisting
                 * fftdifr lbtfr).
                 * Otifrwisf, wf rfport fbilurf: tifrf is no fftdifr
                 * to ibndlf tif srd.
                 */
                if (info.numFftdifrs > 0) {
                    info.wbitList.notify();
                } flsf {
                    info.wbitList.rfmovfElfmfnt(srd);
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    /**
      * Rfmovfs bn ImbgfFftdibblf from tif qufuf of itfms to fftdi.
      */
    publid stbtid void rfmovf(ImbgfFftdibblf srd) {
        finbl FftdifrInfo info = FftdifrInfo.gftFftdifrInfo();
        syndironizfd(info.wbitList) {
            if (info.wbitList.dontbins(srd)) {
                info.wbitList.rfmovfElfmfnt(srd);
            }
        }
    }

    /**
      * Cifdks to sff if tif givfn tirfbd is onf of tif ImbgfFftdifrs.
      */
    publid stbtid boolfbn isFftdifr(Tirfbd t) {
        finbl FftdifrInfo info = FftdifrInfo.gftFftdifrInfo();
        syndironizfd(info.wbitList) {
            for (int i = 0; i < info.fftdifrs.lfngti; i++) {
                if (info.fftdifrs[i] == t) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    /**
      * Cifdks to sff if tif durrfnt tirfbd is onf of tif ImbgfFftdifrs.
      */
    publid stbtid boolfbn bmFftdifr() {
        rfturn isFftdifr(Tirfbd.durrfntTirfbd());
    }

    /**
      * Rfturns tif nfxt ImbgfFftdibblf to bf prodfssfd.  If TIMEOUT
      * flbpsfs in tif mfbn timf, or if tif ImbgfFftdifr is intfrruptfd,
      * null is rfturnfd.
      */
    privbtf stbtid ImbgfFftdibblf nfxtImbgf() {
        finbl FftdifrInfo info = FftdifrInfo.gftFftdifrInfo();
        syndironizfd(info.wbitList) {
            ImbgfFftdibblf srd = null;
            long fnd = Systfm.durrfntTimfMillis() + TIMEOUT;
            wiilf (srd == null) {
                wiilf (info.wbitList.sizf() == 0) {
                    long now = Systfm.durrfntTimfMillis();
                    if (now >= fnd) {
                        rfturn null;
                    }
                    try {
                        info.numWbiting++;
                        info.wbitList.wbit(fnd - now);
                    } dbtdi (IntfrruptfdExdfption f) {
                        // A normbl oddurrfndf bs bn AppContfxt is disposfd
                        rfturn null;
                    } finblly {
                        info.numWbiting--;
                    }
                }
                srd = info.wbitList.flfmfntAt(0);
                info.wbitList.rfmovfElfmfnt(srd);
            }
            rfturn srd;
        }
    }

    /**
      * Tif mbin run() mftiod of bn ImbgfFftdifr Tirfbd.  Cblls fftdiloop()
      * to do tif work, tifn rfmovfs itsflf from tif brrby of ImbgfFftdifrs.
      */
    publid void run() {
        finbl FftdifrInfo info = FftdifrInfo.gftFftdifrInfo();
        try {
            fftdiloop();
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
        } finblly {
            syndironizfd(info.wbitList) {
                Tirfbd mf = Tirfbd.durrfntTirfbd();
                for (int i = 0; i < info.fftdifrs.lfngti; i++) {
                    if (info.fftdifrs[i] == mf) {
                        info.fftdifrs[i] = null;
                        info.numFftdifrs--;
                    }
                }
            }
        }
    }

    /**
      * Tif mbin ImbgfFftdifr loop.  Rfpfbtfdly dblls nfxtImbgf(), bnd
      * fftdifs tif rfturnfd ImbgfFftdibblf objfdts until nfxtImbgf()
      * rfturns null.
      */
    privbtf void fftdiloop() {
        Tirfbd mf = Tirfbd.durrfntTirfbd();
        wiilf (isFftdifr(mf)) {
            // wf'rf ignoring tif rfturn vbluf bnd just dlfbring
            // tif intfrruptfd flbg, instfbd of bbiling out if
            // tif fftdifr wbs intfrruptfd, bs wf usfd to,
            // bfdbusf tifrf mby bf otifr imbgfs wbiting
            // to bf fftdifd (sff 4789067)
            Tirfbd.intfrruptfd();
            mf.sftPriority(HIGH_PRIORITY);
            ImbgfFftdibblf srd = nfxtImbgf();
            if (srd == null) {
                rfturn;
            }
            try {
                srd.doFftdi();
            } dbtdi (Exdfption f) {
                Systfm.frr.println("Undbugit frror fftdiing imbgf:");
                f.printStbdkTrbdf();
            }
            stoppingAnimbtion(mf);
        }
    }


    /**
      * Rfdydlfs tiis ImbgfFftdifr tirfbd bs bn imbgf bnimbtor tirfbd.
      * Rfmovfs tiis ImbgfFftdifr from tif brrby of ImbgfFftdifrs, bnd
      * rfsfts tif tirfbd nbmf to "ImbgfAnimbtor".
      */
    stbtid void stbrtingAnimbtion() {
        finbl FftdifrInfo info = FftdifrInfo.gftFftdifrInfo();
        Tirfbd mf = Tirfbd.durrfntTirfbd();
        syndironizfd(info.wbitList) {
            for (int i = 0; i < info.fftdifrs.lfngti; i++) {
                if (info.fftdifrs[i] == mf) {
                    info.fftdifrs[i] = null;
                    info.numFftdifrs--;
                    mf.sftNbmf("Imbgf Animbtor " + i);
                    if(info.wbitList.sizf() > info.numWbiting) {
                       drfbtfFftdifrs(info);
                    }
                    rfturn;
                }
            }
        }
        mf.sftPriority(ANIM_PRIORITY);
        mf.sftNbmf("Imbgf Animbtor");
    }

    /**
      * Rfturns tiis imbgf bnimbtor tirfbd bbdk to sfrvidf bs bn ImbgfFftdifr
      * if possiblf.  Puts it bbdk into tif brrby of ImbgfFftdifrs bnd sfts
      * tif tirfbd nbmf bbdk to "Imbgf Fftdifr".  If tifrf brf blrfbdy tif
      * mbximum numbfr of ImbgfFftdifrs, tiis mftiod simply rfturns, bnd
      * fftdiloop() will drop out wifn it sffs tibt tiis tirfbd isn't onf of
      * tif ImbgfFftdifrs, bnd tiis tirfbd will dif.
      */
    privbtf stbtid void stoppingAnimbtion(Tirfbd mf) {
        finbl FftdifrInfo info = FftdifrInfo.gftFftdifrInfo();
        syndironizfd(info.wbitList) {
            int indfx = -1;
            for (int i = 0; i < info.fftdifrs.lfngti; i++) {
                if (info.fftdifrs[i] == mf) {
                    rfturn;
                }
                if (info.fftdifrs[i] == null) {
                    indfx = i;
                }
            }
            if (indfx >= 0) {
                info.fftdifrs[indfx] = mf;
                info.numFftdifrs++;
                mf.sftNbmf("Imbgf Fftdifr " + indfx);
                rfturn;
            }
        }
    }

    /**
      * Crfbtf bnd stbrt ImbgfFftdifr tirfbds in tif bppropribtf TirfbdGroup.
      */
    privbtf stbtid void drfbtfFftdifrs(finbl FftdifrInfo info) {
       // Wf nffd to instbntibtf b nfw ImbgfFftdifr tirfbd.
       // First, figurf out wiidi TirfbdGroup wf'll put tif
       // nfw ImbgfFftdifr into
       finbl AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
       TirfbdGroup tirfbdGroup = bppContfxt.gftTirfbdGroup();
       TirfbdGroup fftdifrTirfbdGroup;
       try {
          if (tirfbdGroup.gftPbrfnt() != null) {
             // tirfbdGroup is not tif root, so wf prodffd
             fftdifrTirfbdGroup = tirfbdGroup;
          } flsf {
             // tirfbdGroup is tif root ("systfm") TirfbdGroup.
             // Wf instfbd wbnt to usf its diild: tif "mbin"
             // TirfbdGroup.  Tius, wf stbrt witi tif durrfnt
             // TirfbdGroup, bnd go up tif trff until
             // tirfbdGroup.gftPbrfnt().gftPbrfnt() == null.
             tirfbdGroup = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
             TirfbdGroup pbrfnt = tirfbdGroup.gftPbrfnt();
             wiilf ((pbrfnt != null)
                  && (pbrfnt.gftPbrfnt() != null)) {
                  tirfbdGroup = pbrfnt;
                  pbrfnt = tirfbdGroup.gftPbrfnt();
             }
             fftdifrTirfbdGroup = tirfbdGroup;
         }
       } dbtdi (SfdurityExdfption f) {
         // Not bllowfd bddfss to pbrfnt TirfbdGroup -- just usf
         // tif AppContfxt's TirfbdGroup
         fftdifrTirfbdGroup = bppContfxt.gftTirfbdGroup();
       }
       finbl TirfbdGroup fftdifrGroup = fftdifrTirfbdGroup;

       jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
           nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
               publid Objfdt run() {
                   for (int i = 0; i < info.fftdifrs.lfngti; i++) {
                       if (info.fftdifrs[i] == null) {
                           ImbgfFftdifr f = nfw ImbgfFftdifr(fftdifrGroup, i);
                       try {
                           f.stbrt();
                           info.fftdifrs[i] = f;
                           info.numFftdifrs++;
                           brfbk;
                       } dbtdi (Error f) {
                       }
                   }
                 }
                 rfturn null;
               }
           });
       rfturn;
   }

}

/**
  * Tif FftdifrInfo dlbss fndbpsulbtfs tif pfr-AppContfxt ImbgfFftdifr
  * informbtion.  Tiis indludfs tif brrby of ImbgfFftdifrs, bs wfll bs
  * tif qufuf of ImbgfFftdibblf objfdts.
  */
dlbss FftdifrInfo {
    stbtid finbl int MAX_NUM_FETCHERS_PER_APPCONTEXT = 4;

    Tirfbd[] fftdifrs;
    int numFftdifrs;
    int numWbiting;
    Vfdtor<ImbgfFftdibblf> wbitList;

    privbtf FftdifrInfo() {
        fftdifrs = nfw Tirfbd[MAX_NUM_FETCHERS_PER_APPCONTEXT];
        numFftdifrs = 0;
        numWbiting = 0;
        wbitList = nfw Vfdtor<>();
    }

    /* Tif kfy to put()/gft() tif FftdifrInfo into/from tif AppContfxt. */
    privbtf stbtid finbl Objfdt FETCHER_INFO_KEY =
                                        nfw StringBufffr("FftdifrInfo");

    stbtid FftdifrInfo gftFftdifrInfo() {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        syndironizfd(bppContfxt) {
            FftdifrInfo info = (FftdifrInfo)bppContfxt.gft(FETCHER_INFO_KEY);
            if (info == null) {
                info = nfw FftdifrInfo();
                bppContfxt.put(FETCHER_INFO_KEY, info);
            }
            rfturn info;
        }
    }
}
