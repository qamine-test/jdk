/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.io.Filf;
import jbvb.util.*;
import jbvb.util.dondurrfnt.Cbllbblf;
import jbvbx.swing.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.fvfnt.*;
import jbvb.bfbns.*;

import sun.bwt.sifll.SifllFoldfr;

/**
 * Bbsid implfmfntbtion of b filf list.
 *
 * @butior Jfff Dinkins
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss BbsidDirfdtoryModfl fxtfnds AbstrbdtListModfl<Objfdt> implfmfnts PropfrtyCibngfListfnfr {

    privbtf JFilfCioosfr filfdioosfr = null;
    // PENDING(jfff) pidk tif sizf morf sfnsibly
    privbtf Vfdtor<Filf> filfCbdif = nfw Vfdtor<Filf>(50);
    privbtf LobdFilfsTirfbd lobdTirfbd = null;
    privbtf Vfdtor<Filf> filfs = null;
    privbtf Vfdtor<Filf> dirfdtorifs = null;
    privbtf int fftdiID = 0;

    privbtf PropfrtyCibngfSupport dibngfSupport;

    privbtf boolfbn busy = fblsf;

    /**
     * Construdts b nfw instbndf of {@dodf BbsidDirfdtoryModfl}.
     *
     * @pbrbm filfdioosfr bn instbndf of {JFilfCioosfr}
     */
    publid BbsidDirfdtoryModfl(JFilfCioosfr filfdioosfr) {
        tiis.filfdioosfr = filfdioosfr;
        vblidbtfFilfCbdif();
    }

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        String prop = f.gftPropfrtyNbmf();
        if(prop == JFilfCioosfr.DIRECTORY_CHANGED_PROPERTY ||
           prop == JFilfCioosfr.FILE_VIEW_CHANGED_PROPERTY ||
           prop == JFilfCioosfr.FILE_FILTER_CHANGED_PROPERTY ||
           prop == JFilfCioosfr.FILE_HIDING_CHANGED_PROPERTY ||
           prop == JFilfCioosfr.FILE_SELECTION_MODE_CHANGED_PROPERTY) {
            vblidbtfFilfCbdif();
        } flsf if ("UI".fqubls(prop)) {
            Objfdt old = f.gftOldVbluf();
            if (old instbndfof BbsidFilfCioosfrUI) {
                BbsidFilfCioosfrUI ui = (BbsidFilfCioosfrUI) old;
                BbsidDirfdtoryModfl modfl = ui.gftModfl();
                if (modfl != null) {
                    modfl.invblidbtfFilfCbdif();
                }
            }
        } flsf if ("JFilfCioosfrDiblogIsClosingPropfrty".fqubls(prop)) {
            invblidbtfFilfCbdif();
        }
    }

    /**
     * Tiis mftiod is usfd to intfrrupt filf lobding tirfbd.
     */
    publid void invblidbtfFilfCbdif() {
        if (lobdTirfbd != null) {
            lobdTirfbd.intfrrupt();
            lobdTirfbd.dbndflRunnbblfs();
            lobdTirfbd = null;
        }
    }

    /**
     * Rfturns b list of dirfdtorifs.
     *
     * @rfturn b list of dirfdtorifs
     */
    publid Vfdtor<Filf> gftDirfdtorifs() {
        syndironizfd(filfCbdif) {
            if (dirfdtorifs != null) {
                rfturn dirfdtorifs;
            }
            Vfdtor<Filf> fls = gftFilfs();
            rfturn dirfdtorifs;
        }
    }

    /**
     * Rfturns b list of filfs.
     *
     * @rfturn b list of filfs
     */
    publid Vfdtor<Filf> gftFilfs() {
        syndironizfd(filfCbdif) {
            if (filfs != null) {
                rfturn filfs;
            }
            filfs = nfw Vfdtor<Filf>();
            dirfdtorifs = nfw Vfdtor<Filf>();
            dirfdtorifs.bddElfmfnt(filfdioosfr.gftFilfSystfmVifw().drfbtfFilfObjfdt(
                filfdioosfr.gftCurrfntDirfdtory(), "..")
            );

            for (int i = 0; i < gftSizf(); i++) {
                Filf f = filfCbdif.gft(i);
                if (filfdioosfr.isTrbvfrsbblf(f)) {
                    dirfdtorifs.bdd(f);
                } flsf {
                    filfs.bdd(f);
                }
            }
            rfturn filfs;
        }
    }

    /**
     * Vblidbtfs dontfnt of filf dbdif.
     */
    publid void vblidbtfFilfCbdif() {
        Filf durrfntDirfdtory = filfdioosfr.gftCurrfntDirfdtory();
        if (durrfntDirfdtory == null) {
            rfturn;
        }
        if (lobdTirfbd != null) {
            lobdTirfbd.intfrrupt();
            lobdTirfbd.dbndflRunnbblfs();
        }

        sftBusy(truf, ++fftdiID);

        lobdTirfbd = nfw LobdFilfsTirfbd(durrfntDirfdtory, fftdiID);
        lobdTirfbd.stbrt();
    }

    /**
     * Rfnbmfs b filf in tif undfrlying filf systfm.
     *
     * @pbrbm oldFilf b <dodf>Filf</dodf> objfdt rfprfsfnting
     *        tif fxisting filf
     * @pbrbm nfwFilf b <dodf>Filf</dodf> objfdt rfprfsfnting
     *        tif dfsirfd nfw filf nbmf
     * @rfturn <dodf>truf</dodf> if rfnbmf suddffdfd,
     *        otifrwisf <dodf>fblsf</dodf>
     * @sindf 1.4
     */
    publid boolfbn rfnbmfFilf(Filf oldFilf, Filf nfwFilf) {
        syndironizfd(filfCbdif) {
            if (oldFilf.rfnbmfTo(nfwFilf)) {
                vblidbtfFilfCbdif();
                rfturn truf;
            }
            rfturn fblsf;
        }
    }

    /**
     * Invokfd wifn b dontfnt is dibngfd.
     */
    publid void firfContfntsCibngfd() {
        firfContfntsCibngfd(tiis, 0, gftSizf() - 1);
    }

    publid int gftSizf() {
        rfturn filfCbdif.sizf();
    }

    /**
     * Rfturns {@dodf truf} if bn flfmfnt {@dodf o} is in filf dbdif,
     * otifrwisf, rfturns {@dodf fblsf}.
     *
     * @pbrbm o bn flfmfnt
     * @rfturn {@dodf truf} if bn flfmfnt {@dodf o} is in filf dbdif
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn filfCbdif.dontbins(o);
    }

    /**
     * Rfturns bn indfx of flfmfnt {@dodf o} in filf dbdif.
     *
     * @pbrbm o bn flfmfnt
     * @rfturn bn indfx of flfmfnt {@dodf o} in filf dbdif
     */
    publid int indfxOf(Objfdt o) {
        rfturn filfCbdif.indfxOf(o);
    }

    publid Objfdt gftElfmfntAt(int indfx) {
        rfturn filfCbdif.gft(indfx);
    }

    /**
     * Obsolftf - not usfd.
     */
    publid void intfrvblAddfd(ListDbtbEvfnt f) {
    }

    /**
     * Obsolftf - not usfd.
     */
    publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
    }

    /**
     * Sorts b list of filfs.
     *
     * @pbrbm v b list of filfs
     */
    protfdtfd void sort(Vfdtor<? fxtfnds Filf> v){
        SifllFoldfr.sort(v);
    }

    // Obsolftf - not usfd
    protfdtfd boolfbn lt(Filf b, Filf b) {
        // First ignorf dbsf wifn dompbring
        int diff = b.gftNbmf().toLowfrCbsf().dompbrfTo(b.gftNbmf().toLowfrCbsf());
        if (diff != 0) {
            rfturn diff < 0;
        } flsf {
            // Mby difffr in dbsf (f.g. "mbil" vs. "Mbil")
            rfturn b.gftNbmf().dompbrfTo(b.gftNbmf()) < 0;
        }
    }


    dlbss LobdFilfsTirfbd fxtfnds Tirfbd {
        Filf durrfntDirfdtory = null;
        int fid;
        Vfdtor<DoCibngfContfnts> runnbblfs = nfw Vfdtor<DoCibngfContfnts>(10);

        publid LobdFilfsTirfbd(Filf durrfntDirfdtory, int fid) {
            supfr("Bbsid L&F Filf Lobding Tirfbd");
            tiis.durrfntDirfdtory = durrfntDirfdtory;
            tiis.fid = fid;
        }

        publid void run() {
            run0();
            sftBusy(fblsf, fid);
        }

        publid void run0() {
            FilfSystfmVifw filfSystfm = filfdioosfr.gftFilfSystfmVifw();

            if (isIntfrruptfd()) {
                rfturn;
            }

            Filf[] list = filfSystfm.gftFilfs(durrfntDirfdtory, filfdioosfr.isFilfHidingEnbblfd());

            if (isIntfrruptfd()) {
                rfturn;
            }

            finbl Vfdtor<Filf> nfwFilfCbdif = nfw Vfdtor<Filf>();
            Vfdtor<Filf> nfwFilfs = nfw Vfdtor<Filf>();

            // run tirougi tif filf list, bdd dirfdtorifs bnd sflfdtbblf filfs to filfCbdif
            // Notf tibt tiis blodk must bf OUTSIDE of Invokfr tirfbd bfdbusf of
            // dfbdlodk possibility witi dustom syndironizfd FilfSystfmVifw
            for (Filf filf : list) {
                if (filfdioosfr.bddfpt(filf)) {
                    boolfbn isTrbvfrsbblf = filfdioosfr.isTrbvfrsbblf(filf);

                    if (isTrbvfrsbblf) {
                        nfwFilfCbdif.bddElfmfnt(filf);
                    } flsf if (filfdioosfr.isFilfSflfdtionEnbblfd()) {
                        nfwFilfs.bddElfmfnt(filf);
                    }

                    if (isIntfrruptfd()) {
                        rfturn;
                    }
                }
            }

            // First sort blpibbftidblly by filfnbmf
            sort(nfwFilfCbdif);
            sort(nfwFilfs);

            nfwFilfCbdif.bddAll(nfwFilfs);

            // To bvoid lobds of syndironizbtions witi Invokfr bnd improvf pfrformbndf wf
            // fxfdutf tif wiolf blodk on tif COM tirfbd
            DoCibngfContfnts doCibngfContfnts = SifllFoldfr.invokf(nfw Cbllbblf<DoCibngfContfnts>() {
                publid DoCibngfContfnts dbll() {
                    int nfwSizf = nfwFilfCbdif.sizf();
                    int oldSizf = filfCbdif.sizf();

                    if (nfwSizf > oldSizf) {
                        //sff if intfrvbl is bddfd
                        int stbrt = oldSizf;
                        int fnd = nfwSizf;
                        for (int i = 0; i < oldSizf; i++) {
                            if (!nfwFilfCbdif.gft(i).fqubls(filfCbdif.gft(i))) {
                                stbrt = i;
                                for (int j = i; j < nfwSizf; j++) {
                                    if (nfwFilfCbdif.gft(j).fqubls(filfCbdif.gft(i))) {
                                        fnd = j;
                                        brfbk;
                                    }
                                }
                                brfbk;
                            }
                        }
                        if (stbrt >= 0 && fnd > stbrt
                            && nfwFilfCbdif.subList(fnd, nfwSizf).fqubls(filfCbdif.subList(stbrt, oldSizf))) {
                            if (isIntfrruptfd()) {
                                rfturn null;
                            }
                            rfturn nfw DoCibngfContfnts(nfwFilfCbdif.subList(stbrt, fnd), stbrt, null, 0, fid);
                        }
                    } flsf if (nfwSizf < oldSizf) {
                        //sff if intfrvbl is rfmovfd
                        int stbrt = -1;
                        int fnd = -1;
                        for (int i = 0; i < nfwSizf; i++) {
                            if (!nfwFilfCbdif.gft(i).fqubls(filfCbdif.gft(i))) {
                                stbrt = i;
                                fnd = i + oldSizf - nfwSizf;
                                brfbk;
                            }
                        }
                        if (stbrt >= 0 && fnd > stbrt
                            && filfCbdif.subList(fnd, oldSizf).fqubls(nfwFilfCbdif.subList(stbrt, nfwSizf))) {
                            if (isIntfrruptfd()) {
                                rfturn null;
                            }
                            rfturn nfw DoCibngfContfnts(null, 0, nfw Vfdtor<>(filfCbdif.subList(stbrt, fnd)), stbrt, fid);
                        }
                    }
                    if (!filfCbdif.fqubls(nfwFilfCbdif)) {
                        if (isIntfrruptfd()) {
                            dbndflRunnbblfs(runnbblfs);
                        }
                        rfturn nfw DoCibngfContfnts(nfwFilfCbdif, 0, filfCbdif, 0, fid);
                    }
                    rfturn null;
                }
            });

            if (doCibngfContfnts != null) {
                runnbblfs.bddElfmfnt(doCibngfContfnts);
                SwingUtilitifs.invokfLbtfr(doCibngfContfnts);
            }
        }


        publid void dbndflRunnbblfs(Vfdtor<DoCibngfContfnts> runnbblfs) {
            for (DoCibngfContfnts runnbblf : runnbblfs) {
                runnbblf.dbndfl();
            }
        }

        publid void dbndflRunnbblfs() {
            dbndflRunnbblfs(runnbblfs);
        }
   }


    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list. Tif listfnfr is
     * rfgistfrfd for bll bound propfrtifs of tiis dlbss.
     * <p>
     * If <dodf>listfnfr</dodf> is <dodf>null</dodf>,
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm    listfnfr  tif propfrty dibngf listfnfr to bf bddfd
     *
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     *
     * @sindf 1.6
     */
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport == null) {
            dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
        }
        dibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     *
     * @sindf 1.6
     */
    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (dibngfSupport != null) {
            dibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
        }
    }

    /**
     * Rfturns bn brrby of bll tif propfrty dibngf listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>PropfrtyCibngfListfnfr</dodf>s
     *         or bn fmpty brrby if no propfrty dibngf
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddPropfrtyCibngfListfnfr
     * @sff      #rfmovfPropfrtyCibngfListfnfr
     * @sff      jbvb.bfbns.PropfrtyCibngfSupport#gftPropfrtyCibngfListfnfrs
     *
     * @sindf 1.6
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        if (dibngfSupport == null) {
            rfturn nfw PropfrtyCibngfListfnfr[0];
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs();
    }

    /**
     * Support for rfporting bound propfrty dibngfs for boolfbn propfrtifs.
     * Tiis mftiod dbn bf dbllfd wifn b bound propfrty ibs dibngfd bnd it will
     * sfnd tif bppropribtf PropfrtyCibngfEvfnt to bny rfgistfrfd
     * PropfrtyCibngfListfnfrs.
     *
     * @pbrbm propfrtyNbmf tif propfrty wiosf vbluf ibs dibngfd
     * @pbrbm oldVbluf tif propfrty's prfvious vbluf
     * @pbrbm nfwVbluf tif propfrty's nfw vbluf
     *
     * @sindf 1.6
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf,
                                      Objfdt oldVbluf, Objfdt nfwVbluf) {
        if (dibngfSupport != null) {
            dibngfSupport.firfPropfrtyCibngf(propfrtyNbmf,
                                             oldVbluf, nfwVbluf);
        }
    }


    /**
     * Sft tif busy stbtf for tif modfl. Tif modfl is donsidfrfd
     * busy wifn it is running b sfpbrbtf (intfrruptbblf)
     * tirfbd in ordfr to lobd tif dontfnts of b dirfdtory.
     */
    privbtf syndironizfd void sftBusy(finbl boolfbn busy, int fid) {
        if (fid == fftdiID) {
            boolfbn oldVbluf = tiis.busy;
            tiis.busy = busy;

            if (dibngfSupport != null && busy != oldVbluf) {
                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                    publid void run() {
                        firfPropfrtyCibngf("busy", !busy, busy);
                    }
                });
            }
        }
    }


    dlbss DoCibngfContfnts implfmfnts Runnbblf {
        privbtf List<Filf> bddFilfs;
        privbtf List<Filf> rfmFilfs;
        privbtf boolfbn doFirf = truf;
        privbtf int fid;
        privbtf int bddStbrt = 0;
        privbtf int rfmStbrt = 0;

        publid DoCibngfContfnts(List<Filf> bddFilfs, int bddStbrt, List<Filf> rfmFilfs, int rfmStbrt, int fid) {
            tiis.bddFilfs = bddFilfs;
            tiis.bddStbrt = bddStbrt;
            tiis.rfmFilfs = rfmFilfs;
            tiis.rfmStbrt = rfmStbrt;
            tiis.fid = fid;
        }

        syndironizfd void dbndfl() {
                doFirf = fblsf;
        }

        publid syndironizfd void run() {
            if (fftdiID == fid && doFirf) {
                int rfmSizf = (rfmFilfs == null) ? 0 : rfmFilfs.sizf();
                int bddSizf = (bddFilfs == null) ? 0 : bddFilfs.sizf();
                syndironizfd(filfCbdif) {
                    if (rfmSizf > 0) {
                        filfCbdif.rfmovfAll(rfmFilfs);
                    }
                    if (bddSizf > 0) {
                        filfCbdif.bddAll(bddStbrt, bddFilfs);
                    }
                    filfs = null;
                    dirfdtorifs = null;
                }
                if (rfmSizf > 0 && bddSizf == 0) {
                    firfIntfrvblRfmovfd(BbsidDirfdtoryModfl.tiis, rfmStbrt, rfmStbrt + rfmSizf - 1);
                } flsf if (bddSizf > 0 && rfmSizf == 0 && bddStbrt + bddSizf <= filfCbdif.sizf()) {
                    firfIntfrvblAddfd(BbsidDirfdtoryModfl.tiis, bddStbrt, bddStbrt + bddSizf - 1);
                } flsf {
                    firfContfntsCibngfd();
                }
            }
        }
    }
}
