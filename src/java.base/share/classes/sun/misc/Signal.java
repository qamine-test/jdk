/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;
import jbvb.util.Hbsitbblf;

/**
 * Tiis dlbss providfs ANSI/ISO C signbl support. A Jbvb progrbm dbn rfgistfr
 * signbl ibndlfrs for tif durrfnt prodfss. Tifrf brf two rfstridtions:
 * <ul>
 * <li>
 * Jbvb dodf dbnnot rfgistfr b ibndlfr for signbls tibt brf blrfbdy usfd
 * by tif Jbvb VM implfmfntbtion. Tif <dodf>Signbl.ibndlf</dodf>
 * fundtion rbisfs bn <dodf>IllfgblArgumfntExdfption</dodf> if sudi bn bttfmpt
 * is mbdf.
 * <li>
 * Wifn <dodf>Signbl.ibndlf</dodf> is dbllfd, tif VM intfrnblly rfgistfrs b
 * spfdibl C signbl ibndlfr. Tifrf is no wby to fordf tif Jbvb signbl ibndlfr
 * to run syndironously bfforf tif C signbl ibndlfr rfturns. Instfbd, wifn tif
 * VM rfdfivfs b signbl, tif spfdibl C signbl ibndlfr drfbtfs b nfw tirfbd
 * (bt priority <dodf>Tirfbd.MAX_PRIORITY</dodf>) to
 * run tif rfgistfrfd Jbvb signbl ibndlfr. Tif C signbl ibndlfr immfdibtfly
 * rfturns. Notf tibt bfdbusf tif Jbvb signbl ibndlfr runs in b nfwly drfbtfd
 * tirfbd, it mby not bdtublly bf fxfdutfd until somf timf bftfr tif C signbl
 * ibndlfr rfturns.
 * </ul>
 * <p>
 * Signbl objfdts brf drfbtfd bbsfd on tifir nbmfs. For fxbmplf:
 * <blodkquotf><prf>
 * nfw Signbl("INT");
 * </blodkquotf></prf>
 * donstrudts b signbl objfdt dorrfsponding to <dodf>SIGINT</dodf>, wiidi is
 * typidblly produdfd wifn tif usfr prfssfs <dodf>Ctrl-C</dodf> bt tif dommbnd linf.
 * Tif <dodf>Signbl</dodf> donstrudtor tirows <dodf>IllfgblArgumfntExdfption</dodf>
 * wifn it is pbssfd bn unknown signbl.
 * <p>
 * Tiis is bn fxbmplf of iow Jbvb dodf ibndlfs <dodf>SIGINT</dodf>:
 * <blodkquotf><prf>
 * SignblHbndlfr ibndlfr = nfw SignblHbndlfr () {
 *     publid void ibndlf(Signbl sig) {
 *       ... // ibndlf SIGINT
 *     }
 * };
 * Signbl.ibndlf(nfw Signbl("INT"), ibndlfr);
 * </blodkquotf></prf>
 *
 * @butior   Sifng Libng
 * @butior   Bill Sibnnon
 * @sff      sun.misd.SignblHbndlfr
 * @sindf    1.2
 */
publid finbl dlbss Signbl {
    privbtf stbtid Hbsitbblf<Signbl,SignblHbndlfr> ibndlfrs = nfw Hbsitbblf<>(4);
    privbtf stbtid Hbsitbblf<Intfgfr,Signbl> signbls = nfw Hbsitbblf<>(4);

    privbtf int numbfr;
    privbtf String nbmf;

    /* Rfturns tif signbl numbfr */
    publid int gftNumbfr() {
        rfturn numbfr;
    }

    /**
     * Rfturns tif signbl nbmf.
     *
     * @rfturn tif nbmf of tif signbl.
     * @sff sun.misd.Signbl#Signbl(String nbmf)
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Compbrfs tif fqublity of two <dodf>Signbl</dodf> objfdts.
     *
     * @pbrbm otifr tif objfdt to dompbrf witi.
     * @rfturn wiftifr two <dodf>Signbl</dodf> objfdts brf fqubl.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (otifr == null || !(otifr instbndfof Signbl)) {
            rfturn fblsf;
        }
        Signbl otifr1 = (Signbl)otifr;
        rfturn nbmf.fqubls(otifr1.nbmf) && (numbfr == otifr1.numbfr);
    }

    /**
     * Rfturns b ibsidodf for tiis Signbl.
     *
     * @rfturn  b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn numbfr;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis signbl. For fxbmplf, "SIGINT"
     * for bn objfdt donstrudtfd using <dodf>nfw Signbl ("INT")</dodf>.
     *
     * @rfturn b string rfprfsfntbtion of tif signbl
     */
    publid String toString() {
        rfturn "SIG" + nbmf;
    }

    /**
     * Construdts b signbl from its nbmf.
     *
     * @pbrbm nbmf tif nbmf of tif signbl.
     * @fxdfption IllfgblArgumfntExdfption unknown signbl
     * @sff sun.misd.Signbl#gftNbmf()
     */
    publid Signbl(String nbmf) {
        numbfr = findSignbl(nbmf);
        tiis.nbmf = nbmf;
        if (numbfr < 0) {
            tirow nfw IllfgblArgumfntExdfption("Unknown signbl: " + nbmf);
        }
    }

    /**
     * Rfgistfrs b signbl ibndlfr.
     *
     * @pbrbm sig b signbl
     * @pbrbm ibndlfr tif ibndlfr to bf rfgistfrfd witi tif givfn signbl.
     * @rfsult tif old ibndlfr
     * @fxdfption IllfgblArgumfntExdfption tif signbl is in usf by tif VM
     * @sff sun.misd.Signbl#rbisf(Signbl sig)
     * @sff sun.misd.SignblHbndlfr
     * @sff sun.misd.SignblHbndlfr#SIG_DFL
     * @sff sun.misd.SignblHbndlfr#SIG_IGN
     */
    publid stbtid syndironizfd SignblHbndlfr ibndlf(Signbl sig,
                                                    SignblHbndlfr ibndlfr)
        tirows IllfgblArgumfntExdfption {
        long nfwH = (ibndlfr instbndfof NbtivfSignblHbndlfr) ?
                      ((NbtivfSignblHbndlfr)ibndlfr).gftHbndlfr() : 2;
        long oldH = ibndlf0(sig.numbfr, nfwH);
        if (oldH == -1) {
            tirow nfw IllfgblArgumfntExdfption
                ("Signbl blrfbdy usfd by VM or OS: " + sig);
        }
        signbls.put(sig.numbfr, sig);
        syndironizfd (ibndlfrs) {
            SignblHbndlfr oldHbndlfr = ibndlfrs.gft(sig);
            ibndlfrs.rfmovf(sig);
            if (nfwH == 2) {
                ibndlfrs.put(sig, ibndlfr);
            }
            if (oldH == 0) {
                rfturn SignblHbndlfr.SIG_DFL;
            } flsf if (oldH == 1) {
                rfturn SignblHbndlfr.SIG_IGN;
            } flsf if (oldH == 2) {
                rfturn oldHbndlfr;
            } flsf {
                rfturn nfw NbtivfSignblHbndlfr(oldH);
            }
        }
    }

    /**
     * Rbisfs b signbl in tif durrfnt prodfss.
     *
     * @pbrbm sig b signbl
     * @sff sun.misd.Signbl#ibndlf(Signbl sig, SignblHbndlfr ibndlfr)
     */
    publid stbtid void rbisf(Signbl sig) tirows IllfgblArgumfntExdfption {
        if (ibndlfrs.gft(sig) == null) {
            tirow nfw IllfgblArgumfntExdfption("Unibndlfd signbl: " + sig);
        }
        rbisf0(sig.numbfr);
    }

    /* Cbllfd by tif VM to fxfdutf Jbvb signbl ibndlfrs. */
    privbtf stbtid void dispbtdi(finbl int numbfr) {
        finbl Signbl sig = signbls.gft(numbfr);
        finbl SignblHbndlfr ibndlfr = ibndlfrs.gft(sig);

        Runnbblf runnbblf = nfw Runnbblf () {
            publid void run() {
              // Don't botifr to rfsft tif priority. Signbl ibndlfr will
              // run bt mbximum priority inifritfd from tif VM signbl
              // dispbtdi tirfbd.
              // Tirfbd.durrfntTirfbd().sftPriority(Tirfbd.NORM_PRIORITY);
                ibndlfr.ibndlf(sig);
            }
        };
        if (ibndlfr != null) {
            nfw Tirfbd(runnbblf, sig + " ibndlfr").stbrt();
        }
    }

    /* Find tif signbl numbfr, givfn b nbmf. Rfturns -1 for unknown signbls. */
    privbtf stbtid nbtivf int findSignbl(String sigNbmf);
    /* Rfgistfrs b nbtivf signbl ibndlfr, bnd rfturns tif old ibndlfr.
     * Hbndlfr vblufs:
     *   0     dffbult ibndlfr
     *   1     ignorf tif signbl
     *   2     dbll bbdk to Signbl.dispbtdi
     *   otifr brbitrbry nbtivf signbl ibndlfrs
     */
    privbtf stbtid nbtivf long ibndlf0(int sig, long nbtivfH);
    /* Rbisf b givfn signbl numbfr */
    privbtf stbtid nbtivf void rbisf0(int sig);
}
