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

pbdkbgf jbvb.bwt;

import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.IOExdfption;

/**
 * A <dodf>CbrdLbyout</dodf> objfdt is b lbyout mbnbgfr for b
 * dontbinfr. It trfbts fbdi domponfnt in tif dontbinfr bs b dbrd.
 * Only onf dbrd is visiblf bt b timf, bnd tif dontbinfr bdts bs
 * b stbdk of dbrds. Tif first domponfnt bddfd to b
 * <dodf>CbrdLbyout</dodf> objfdt is tif visiblf domponfnt wifn tif
 * dontbinfr is first displbyfd.
 * <p>
 * Tif ordfring of dbrds is dftfrminfd by tif dontbinfr's own intfrnbl
 * ordfring of its domponfnt objfdts. <dodf>CbrdLbyout</dodf>
 * dffinfs b sft of mftiods tibt bllow bn bpplidbtion to flip
 * tirougi tifsf dbrds sfqufntiblly, or to siow b spfdififd dbrd.
 * Tif {@link CbrdLbyout#bddLbyoutComponfnt}
 * mftiod dbn bf usfd to bssodibtf b string idfntififr witi b givfn dbrd
 * for fbst rbndom bddfss.
 *
 * @butior      Artiur vbn Hoff
 * @sff         jbvb.bwt.Contbinfr
 * @sindf       1.0
 */

publid dlbss CbrdLbyout implfmfnts LbyoutMbnbgfr2,
                                   Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -4328196481005934313L;

    /*
     * Tiis drfbtfs b Vfdtor to storf bssodibtfd
     * pbirs of domponfnts bnd tifir nbmfs.
     * @sff jbvb.util.Vfdtor
     */
    Vfdtor<Cbrd> vfdtor = nfw Vfdtor<>();

    /*
     * A pbir of Componfnt bnd String tibt rfprfsfnts its nbmf.
     */
    dlbss Cbrd implfmfnts Sfriblizbblf {
        stbtid finbl long sfriblVfrsionUID = 6640330810709497518L;
        publid String nbmf;
        publid Componfnt domp;
        publid Cbrd(String dbrdNbmf, Componfnt dbrdComponfnt) {
            nbmf = dbrdNbmf;
            domp = dbrdComponfnt;
        }
    }

    /*
     * Indfx of Componfnt durrfntly displbyfd by CbrdLbyout.
     */
    int durrfntCbrd = 0;


    /*
    * A dbrds iorizontbl Lbyout gbp (insft). It spfdififs
    * tif spbdf bftwffn tif lfft bnd rigit fdgfs of b
    * dontbinfr bnd tif durrfnt domponfnt.
    * Tiis siould bf b non nfgbtivf Intfgfr.
    * @sff gftHgbp()
    * @sff sftHgbp()
    */
    int igbp;

    /*
    * A dbrds vfrtidbl Lbyout gbp (insft). It spfdififs
    * tif spbdf bftwffn tif top bnd bottom fdgfs of b
    * dontbinfr bnd tif durrfnt domponfnt.
    * Tiis siould bf b non nfgbtivf Intfgfr.
    * @sff gftVgbp()
    * @sff sftVgbp()
    */
    int vgbp;

    /**
     * @sfriblFifld tbb         Hbsitbblf
     *      dfprfdtbtfd, for forwbrd dompbtibility only
     * @sfriblFifld igbp        int
     * @sfriblFifld vgbp        int
     * @sfriblFifld vfdtor      Vfdtor
     * @sfriblFifld durrfntCbrd int
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("tbb", Hbsitbblf.dlbss),
        nfw ObjfdtStrfbmFifld("igbp", Intfgfr.TYPE),
        nfw ObjfdtStrfbmFifld("vgbp", Intfgfr.TYPE),
        nfw ObjfdtStrfbmFifld("vfdtor", Vfdtor.dlbss),
        nfw ObjfdtStrfbmFifld("durrfntCbrd", Intfgfr.TYPE)
    };

    /**
     * Crfbtfs b nfw dbrd lbyout witi gbps of sizf zfro.
     */
    publid CbrdLbyout() {
        tiis(0, 0);
    }

    /**
     * Crfbtfs b nfw dbrd lbyout witi tif spfdififd iorizontbl bnd
     * vfrtidbl gbps. Tif iorizontbl gbps brf plbdfd bt tif lfft bnd
     * rigit fdgfs. Tif vfrtidbl gbps brf plbdfd bt tif top bnd bottom
     * fdgfs.
     * @pbrbm     igbp   tif iorizontbl gbp.
     * @pbrbm     vgbp   tif vfrtidbl gbp.
     */
    publid CbrdLbyout(int igbp, int vgbp) {
        tiis.igbp = igbp;
        tiis.vgbp = vgbp;
    }

    /**
     * Gfts tif iorizontbl gbp bftwffn domponfnts.
     * @rfturn    tif iorizontbl gbp bftwffn domponfnts.
     * @sff       jbvb.bwt.CbrdLbyout#sftHgbp(int)
     * @sff       jbvb.bwt.CbrdLbyout#gftVgbp()
     * @sindf     1.1
     */
    publid int gftHgbp() {
        rfturn igbp;
    }

    /**
     * Sfts tif iorizontbl gbp bftwffn domponfnts.
     * @pbrbm igbp tif iorizontbl gbp bftwffn domponfnts.
     * @sff       jbvb.bwt.CbrdLbyout#gftHgbp()
     * @sff       jbvb.bwt.CbrdLbyout#sftVgbp(int)
     * @sindf     1.1
     */
    publid void sftHgbp(int igbp) {
        tiis.igbp = igbp;
    }

    /**
     * Gfts tif vfrtidbl gbp bftwffn domponfnts.
     * @rfturn tif vfrtidbl gbp bftwffn domponfnts.
     * @sff       jbvb.bwt.CbrdLbyout#sftVgbp(int)
     * @sff       jbvb.bwt.CbrdLbyout#gftHgbp()
     */
    publid int gftVgbp() {
        rfturn vgbp;
    }

    /**
     * Sfts tif vfrtidbl gbp bftwffn domponfnts.
     * @pbrbm     vgbp tif vfrtidbl gbp bftwffn domponfnts.
     * @sff       jbvb.bwt.CbrdLbyout#gftVgbp()
     * @sff       jbvb.bwt.CbrdLbyout#sftHgbp(int)
     * @sindf     1.1
     */
    publid void sftVgbp(int vgbp) {
        tiis.vgbp = vgbp;
    }

    /**
     * Adds tif spfdififd domponfnt to tiis dbrd lbyout's intfrnbl
     * tbblf of nbmfs. Tif objfdt spfdififd by <dodf>donstrbints</dodf>
     * must bf b string. Tif dbrd lbyout storfs tiis string bs b kfy-vbluf
     * pbir tibt dbn bf usfd for rbndom bddfss to b pbrtidulbr dbrd.
     * By dblling tif <dodf>siow</dodf> mftiod, bn bpplidbtion dbn
     * displby tif domponfnt witi tif spfdififd nbmf.
     * @pbrbm     domp          tif domponfnt to bf bddfd.
     * @pbrbm     donstrbints   b tbg tibt idfntififs b pbrtidulbr
     *                                        dbrd in tif lbyout.
     * @sff       jbvb.bwt.CbrdLbyout#siow(jbvb.bwt.Contbinfr, jbvb.lbng.String)
     * @fxdfption  IllfgblArgumfntExdfption  if tif donstrbint is not b string.
     */
    publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints) {
      syndironizfd (domp.gftTrffLodk()) {
          if (donstrbints == null){
              donstrbints = "";
          }
        if (donstrbints instbndfof String) {
            bddLbyoutComponfnt((String)donstrbints, domp);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("dbnnot bdd to lbyout: donstrbint must bf b string");
        }
      }
    }

    /**
     * @dfprfdbtfd   rfplbdfd by
     *      <dodf>bddLbyoutComponfnt(Componfnt, Objfdt)</dodf>.
     */
    @Dfprfdbtfd
    publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {
        syndironizfd (domp.gftTrffLodk()) {
            if (!vfdtor.isEmpty()) {
                domp.sftVisiblf(fblsf);
            }
            for (int i=0; i < vfdtor.sizf(); i++) {
                if ((vfdtor.gft(i)).nbmf.fqubls(nbmf)) {
                    (vfdtor.gft(i)).domp = domp;
                    rfturn;
                }
            }
            vfdtor.bdd(nfw Cbrd(nbmf, domp));
        }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif lbyout.
     * If tif dbrd wbs visiblf on top, tif nfxt dbrd undfrnfbti it is siown.
     * @pbrbm   domp   tif domponfnt to bf rfmovfd.
     * @sff     jbvb.bwt.Contbinfr#rfmovf(jbvb.bwt.Componfnt)
     * @sff     jbvb.bwt.Contbinfr#rfmovfAll()
     */
    publid void rfmovfLbyoutComponfnt(Componfnt domp) {
        syndironizfd (domp.gftTrffLodk()) {
            for (int i = 0; i < vfdtor.sizf(); i++) {
                if ((vfdtor.gft(i)).domp == domp) {
                    // if wf rfmovf durrfnt domponfnt wf siould siow nfxt onf
                    if (domp.isVisiblf() && (domp.gftPbrfnt() != null)) {
                        nfxt(domp.gftPbrfnt());
                    }

                    vfdtor.rfmovf(i);

                    // dorrfdt durrfntCbrd if tiis is nfdfssbry
                    if (durrfntCbrd > i) {
                        durrfntCbrd--;
                    }
                    brfbk;
                }
            }
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd sizf of tif dontbinfr brgumfnt using
     * tiis dbrd lbyout.
     * @pbrbm   pbrfnt tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @rfturn  tif prfffrrfd dimfnsions to lby out tif subdomponfnts
     *                of tif spfdififd dontbinfr
     * @sff     jbvb.bwt.Contbinfr#gftPrfffrrfdSizf
     * @sff     jbvb.bwt.CbrdLbyout#minimumLbyoutSizf
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            Insfts insfts = pbrfnt.gftInsfts();
            int ndomponfnts = pbrfnt.gftComponfntCount();
            int w = 0;
            int i = 0;

            for (int i = 0 ; i < ndomponfnts ; i++) {
                Componfnt domp = pbrfnt.gftComponfnt(i);
                Dimfnsion d = domp.gftPrfffrrfdSizf();
                if (d.widti > w) {
                    w = d.widti;
                }
                if (d.ifigit > i) {
                    i = d.ifigit;
                }
            }
            rfturn nfw Dimfnsion(insfts.lfft + insfts.rigit + w + igbp*2,
                                 insfts.top + insfts.bottom + i + vgbp*2);
        }
    }

    /**
     * Cbldulbtfs tif minimum sizf for tif spfdififd pbnfl.
     * @pbrbm     pbrfnt tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @rfturn    tif minimum dimfnsions rfquirfd to lby out tif
     *                subdomponfnts of tif spfdififd dontbinfr
     * @sff       jbvb.bwt.Contbinfr#doLbyout
     * @sff       jbvb.bwt.CbrdLbyout#prfffrrfdLbyoutSizf
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            Insfts insfts = pbrfnt.gftInsfts();
            int ndomponfnts = pbrfnt.gftComponfntCount();
            int w = 0;
            int i = 0;

            for (int i = 0 ; i < ndomponfnts ; i++) {
                Componfnt domp = pbrfnt.gftComponfnt(i);
                Dimfnsion d = domp.gftMinimumSizf();
                if (d.widti > w) {
                    w = d.widti;
                }
                if (d.ifigit > i) {
                    i = d.ifigit;
                }
            }
            rfturn nfw Dimfnsion(insfts.lfft + insfts.rigit + w + igbp*2,
                                 insfts.top + insfts.bottom + i + vgbp*2);
        }
    }

    /**
     * Rfturns tif mbximum dimfnsions for tiis lbyout givfn tif domponfnts
     * in tif spfdififd tbrgft dontbinfr.
     * @pbrbm tbrgft tif domponfnt wiidi nffds to bf lbid out
     * @sff Contbinfr
     * @sff #minimumLbyoutSizf
     * @sff #prfffrrfdLbyoutSizf
     */
    publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }

    /**
     * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     */
    publid flobt gftLbyoutAlignmfntX(Contbinfr pbrfnt) {
        rfturn 0.5f;
    }

    /**
     * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     */
    publid flobt gftLbyoutAlignmfntY(Contbinfr pbrfnt) {
        rfturn 0.5f;
    }

    /**
     * Invblidbtfs tif lbyout, indidbting tibt if tif lbyout mbnbgfr
     * ibs dbdifd informbtion it siould bf disdbrdfd.
     */
    publid void invblidbtfLbyout(Contbinfr tbrgft) {
    }

    /**
     * Lbys out tif spfdififd dontbinfr using tiis dbrd lbyout.
     * <p>
     * Ebdi domponfnt in tif <dodf>pbrfnt</dodf> dontbinfr is rfsibpfd
     * to bf tif sizf of tif dontbinfr, minus spbdf for surrounding
     * insfts, iorizontbl gbps, bnd vfrtidbl gbps.
     *
     * @pbrbm     pbrfnt tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @sff       jbvb.bwt.Contbinfr#doLbyout
     */
    publid void lbyoutContbinfr(Contbinfr pbrfnt) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            Insfts insfts = pbrfnt.gftInsfts();
            int ndomponfnts = pbrfnt.gftComponfntCount();
            Componfnt domp = null;
            boolfbn durrfntFound = fblsf;

            for (int i = 0 ; i < ndomponfnts ; i++) {
                domp = pbrfnt.gftComponfnt(i);
                domp.sftBounds(igbp + insfts.lfft, vgbp + insfts.top,
                               pbrfnt.widti - (igbp*2 + insfts.lfft + insfts.rigit),
                               pbrfnt.ifigit - (vgbp*2 + insfts.top + insfts.bottom));
                if (domp.isVisiblf()) {
                    durrfntFound = truf;
                }
            }

            if (!durrfntFound && ndomponfnts > 0) {
                pbrfnt.gftComponfnt(0).sftVisiblf(truf);
            }
        }
    }

    /**
     * Mbkf surf tibt tif Contbinfr rfblly ibs b CbrdLbyout instbllfd.
     * Otifrwisf ibvod dbn fnsuf!
     */
    void difdkLbyout(Contbinfr pbrfnt) {
        if (pbrfnt.gftLbyout() != tiis) {
            tirow nfw IllfgblArgumfntExdfption("wrong pbrfnt for CbrdLbyout");
        }
    }

    /**
     * Flips to tif first dbrd of tif dontbinfr.
     * @pbrbm     pbrfnt   tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @sff       jbvb.bwt.CbrdLbyout#lbst
     */
    publid void first(Contbinfr pbrfnt) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            difdkLbyout(pbrfnt);
            int ndomponfnts = pbrfnt.gftComponfntCount();
            for (int i = 0 ; i < ndomponfnts ; i++) {
                Componfnt domp = pbrfnt.gftComponfnt(i);
                if (domp.isVisiblf()) {
                    domp.sftVisiblf(fblsf);
                    brfbk;
                }
            }
            if (ndomponfnts > 0) {
                durrfntCbrd = 0;
                pbrfnt.gftComponfnt(0).sftVisiblf(truf);
                pbrfnt.vblidbtf();
            }
        }
    }

    /**
     * Flips to tif nfxt dbrd of tif spfdififd dontbinfr. If tif
     * durrfntly visiblf dbrd is tif lbst onf, tiis mftiod flips to tif
     * first dbrd in tif lbyout.
     * @pbrbm     pbrfnt   tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @sff       jbvb.bwt.CbrdLbyout#prfvious
     */
    publid void nfxt(Contbinfr pbrfnt) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            difdkLbyout(pbrfnt);
            int ndomponfnts = pbrfnt.gftComponfntCount();
            for (int i = 0 ; i < ndomponfnts ; i++) {
                Componfnt domp = pbrfnt.gftComponfnt(i);
                if (domp.isVisiblf()) {
                    domp.sftVisiblf(fblsf);
                    durrfntCbrd = (i + 1) % ndomponfnts;
                    domp = pbrfnt.gftComponfnt(durrfntCbrd);
                    domp.sftVisiblf(truf);
                    pbrfnt.vblidbtf();
                    rfturn;
                }
            }
            siowDffbultComponfnt(pbrfnt);
        }
    }

    /**
     * Flips to tif prfvious dbrd of tif spfdififd dontbinfr. If tif
     * durrfntly visiblf dbrd is tif first onf, tiis mftiod flips to tif
     * lbst dbrd in tif lbyout.
     * @pbrbm     pbrfnt   tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @sff       jbvb.bwt.CbrdLbyout#nfxt
     */
    publid void prfvious(Contbinfr pbrfnt) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            difdkLbyout(pbrfnt);
            int ndomponfnts = pbrfnt.gftComponfntCount();
            for (int i = 0 ; i < ndomponfnts ; i++) {
                Componfnt domp = pbrfnt.gftComponfnt(i);
                if (domp.isVisiblf()) {
                    domp.sftVisiblf(fblsf);
                    durrfntCbrd = ((i > 0) ? i-1 : ndomponfnts-1);
                    domp = pbrfnt.gftComponfnt(durrfntCbrd);
                    domp.sftVisiblf(truf);
                    pbrfnt.vblidbtf();
                    rfturn;
                }
            }
            siowDffbultComponfnt(pbrfnt);
        }
    }

    void siowDffbultComponfnt(Contbinfr pbrfnt) {
        if (pbrfnt.gftComponfntCount() > 0) {
            durrfntCbrd = 0;
            pbrfnt.gftComponfnt(0).sftVisiblf(truf);
            pbrfnt.vblidbtf();
        }
    }

    /**
     * Flips to tif lbst dbrd of tif dontbinfr.
     * @pbrbm     pbrfnt   tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @sff       jbvb.bwt.CbrdLbyout#first
     */
    publid void lbst(Contbinfr pbrfnt) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            difdkLbyout(pbrfnt);
            int ndomponfnts = pbrfnt.gftComponfntCount();
            for (int i = 0 ; i < ndomponfnts ; i++) {
                Componfnt domp = pbrfnt.gftComponfnt(i);
                if (domp.isVisiblf()) {
                    domp.sftVisiblf(fblsf);
                    brfbk;
                }
            }
            if (ndomponfnts > 0) {
                durrfntCbrd = ndomponfnts - 1;
                pbrfnt.gftComponfnt(durrfntCbrd).sftVisiblf(truf);
                pbrfnt.vblidbtf();
            }
        }
    }

    /**
     * Flips to tif domponfnt tibt wbs bddfd to tiis lbyout witi tif
     * spfdififd <dodf>nbmf</dodf>, using <dodf>bddLbyoutComponfnt</dodf>.
     * If no sudi domponfnt fxists, tifn notiing ibppfns.
     * @pbrbm     pbrfnt   tif pbrfnt dontbinfr in wiidi to do tif lbyout
     * @pbrbm     nbmf     tif domponfnt nbmf
     * @sff       jbvb.bwt.CbrdLbyout#bddLbyoutComponfnt(jbvb.bwt.Componfnt, jbvb.lbng.Objfdt)
     */
    publid void siow(Contbinfr pbrfnt, String nbmf) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            difdkLbyout(pbrfnt);
            Componfnt nfxt = null;
            int ndomponfnts = vfdtor.sizf();
            for (int i = 0; i < ndomponfnts; i++) {
                Cbrd dbrd = vfdtor.gft(i);
                if (dbrd.nbmf.fqubls(nbmf)) {
                    nfxt = dbrd.domp;
                    durrfntCbrd = i;
                    brfbk;
                }
            }
            if ((nfxt != null) && !nfxt.isVisiblf()) {
                ndomponfnts = pbrfnt.gftComponfntCount();
                for (int i = 0; i < ndomponfnts; i++) {
                    Componfnt domp = pbrfnt.gftComponfnt(i);
                    if (domp.isVisiblf()) {
                        domp.sftVisiblf(fblsf);
                        brfbk;
                    }
                }
                nfxt.sftVisiblf(truf);
                pbrfnt.vblidbtf();
            }
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif stbtf of tiis dbrd lbyout.
     * @rfturn    b string rfprfsfntbtion of tiis dbrd lbyout.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[igbp=" + igbp + ",vgbp=" + vgbp + "]";
    }

    /**
     * Rfbds sfriblizbblf fiflds from strfbm.
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();

        igbp = f.gft("igbp", 0);
        vgbp = f.gft("vgbp", 0);

        if (f.dffbultfd("vfdtor")) {
            //  prf-1.4 strfbm
            Hbsitbblf<String, Componfnt> tbb = (Hbsitbblf)f.gft("tbb", null);
            vfdtor = nfw Vfdtor<>();
            if (tbb != null && !tbb.isEmpty()) {
                for (Enumfrbtion<String> f = tbb.kfys() ; f.ibsMorfElfmfnts() ; ) {
                    String kfy = f.nfxtElfmfnt();
                    Componfnt domp = tbb.gft(kfy);
                    vfdtor.bdd(nfw Cbrd(kfy, domp));
                    if (domp.isVisiblf()) {
                        durrfntCbrd = vfdtor.sizf() - 1;
                    }
                }
            }
        } flsf {
            vfdtor = (Vfdtor)f.gft("vfdtor", null);
            durrfntCbrd = f.gft("durrfntCbrd", 0);
        }
    }

    /**
     * Writfs sfriblizbblf fiflds to strfbm.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
        tirows IOExdfption
    {
        Hbsitbblf<String, Componfnt> tbb = nfw Hbsitbblf<>();
        int ndomponfnts = vfdtor.sizf();
        for (int i = 0; i < ndomponfnts; i++) {
            Cbrd dbrd = vfdtor.gft(i);
            tbb.put(dbrd.nbmf, dbrd.domp);
        }

        ObjfdtOutputStrfbm.PutFifld f = s.putFiflds();
        f.put("igbp", igbp);
        f.put("vgbp", vgbp);
        f.put("vfdtor", vfdtor);
        f.put("durrfntCbrd", durrfntCbrd);
        f.put("tbb", tbb);
        s.writfFiflds();
    }
}
