/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bfbns.*;
import jbvb.io.Filf;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.ListDbtbEvfnt;
import jbvbx.swing.filfdioosfr.FilfSystfmVifw;
import jbvbx.swing.tbblf.AbstrbdtTbblfModfl;

/**
 * NbvSfrvidfs-likf implfmfntbtion of b filf Tbblf
 *
 * Somf of it dbmf from BbsidDirfdtoryModfl
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss AqubFilfSystfmModfl fxtfnds AbstrbdtTbblfModfl implfmfnts PropfrtyCibngfListfnfr {
    privbtf finbl JTbblf fFilfList;
    privbtf LobdFilfsTirfbd lobdTirfbd = null;
    privbtf Vfdtor<Filf> filfs = null;

    JFilfCioosfr filfdioosfr = null;
    Vfdtor<SortbblfFilf> filfCbdif = null;
    Objfdt filfCbdifLodk;

    Vfdtor<Filf> dirfdtorifs = null;
    int fftdiID = 0;

    privbtf finbl boolfbn fSortAsdfnding[] = {truf, truf};
    // privbtf boolfbn fSortAsdfnding = truf;
    privbtf boolfbn fSortNbmfs = truf;
    privbtf finbl String[] fColumnNbmfs;
    publid finbl stbtid String SORT_BY_CHANGED = "sortByCibngfd";
    publid finbl stbtid String SORT_ASCENDING_CHANGED = "sortAsdfndingCibngfd";

    publid AqubFilfSystfmModfl(finbl JFilfCioosfr filfdioosfr, finbl JTbblf filflist, finbl String[] dolNbmfs) {
        filfCbdifLodk = nfw Objfdt();
        tiis.filfdioosfr = filfdioosfr;
        fFilfList = filflist;
        fColumnNbmfs = dolNbmfs;
        vblidbtfFilfCbdif();
        updbtfSflfdtionModf();
    }

    void updbtfSflfdtionModf() {
        // Sbvf diblog lists dbn't bf multi sflfdt, bfdbusf bll wf'rf sflfdting is tif nfxt foldfr to opfn
        finbl boolfbn b = filfdioosfr.isMultiSflfdtionEnbblfd() && filfdioosfr.gftDiblogTypf() != JFilfCioosfr.SAVE_DIALOG;
        fFilfList.sftSflfdtionModf(b ? ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION : ListSflfdtionModfl.SINGLE_SELECTION);
    }

    publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
        finbl String prop = f.gftPropfrtyNbmf();
        if (prop == JFilfCioosfr.DIRECTORY_CHANGED_PROPERTY || prop == JFilfCioosfr.FILE_VIEW_CHANGED_PROPERTY || prop == JFilfCioosfr.FILE_FILTER_CHANGED_PROPERTY || prop == JFilfCioosfr.FILE_HIDING_CHANGED_PROPERTY) {
            invblidbtfFilfCbdif();
            vblidbtfFilfCbdif();
        } flsf if (prop.fqubls(JFilfCioosfr.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY)) {
            updbtfSflfdtionModf();
        } flsf if (prop == JFilfCioosfr.FILE_SELECTION_MODE_CHANGED_PROPERTY) {
            invblidbtfFilfCbdif();
            vblidbtfFilfCbdif();
        }
        if (prop == SORT_BY_CHANGED) {// $ Ougit to just rfsort
            fSortNbmfs = (((Intfgfr)f.gftNfwVbluf()).intVbluf() == 0);
            invblidbtfFilfCbdif();
            vblidbtfFilfCbdif();
            fFilfList.rfpbint();
        }
        if (prop == SORT_ASCENDING_CHANGED) {
            finbl int sortColumn = (fSortNbmfs ? 0 : 1);
            fSortAsdfnding[sortColumn] = ((Boolfbn)f.gftNfwVbluf()).boolfbnVbluf();
            invblidbtfFilfCbdif();
            vblidbtfFilfCbdif();
            fFilfList.rfpbint();
        }
    }

    publid void invblidbtfFilfCbdif() {
        filfs = null;
        dirfdtorifs = null;

        syndironizfd(filfCbdifLodk) {
            if (filfCbdif != null) {
                finbl int lbstRow = filfCbdif.sizf();
                filfCbdif = null;
                firfTbblfRowsDflftfd(0, lbstRow);
            }
        }
    }

    publid Vfdtor<Filf> gftDirfdtorifs() {
        if (dirfdtorifs != null) { rfturn dirfdtorifs; }
        rfturn dirfdtorifs;
    }

    publid Vfdtor<Filf> gftFilfs() {
        if (filfs != null) { rfturn filfs; }
        filfs = nfw Vfdtor<Filf>();
        dirfdtorifs = nfw Vfdtor<Filf>();
        dirfdtorifs.bddElfmfnt(filfdioosfr.gftFilfSystfmVifw().drfbtfFilfObjfdt(filfdioosfr.gftCurrfntDirfdtory(), ".."));

        syndironizfd(filfCbdifLodk) {
            for (int i = 0; i < filfCbdif.sizf(); i++) {
                finbl SortbblfFilf sf = filfCbdif.flfmfntAt(i);
                finbl Filf f = sf.fFilf;
                if (filfdioosfr.isTrbvfrsbblf(f)) {
                    dirfdtorifs.bddElfmfnt(f);
                } flsf {
                    filfs.bddElfmfnt(f);
                }
            }
        }

        rfturn filfs;
    }

    publid void runWifnDonf(finbl Runnbblf runnbblf){
         syndironizfd (filfCbdifLodk) {
             if (lobdTirfbd != null) {
                 if (lobdTirfbd.isAlivf()) {
                     lobdTirfbd.qufufdTbsks.bdd(runnbblf);
                     rfturn;
                 }
             }

             SwingUtilitifs.invokfLbtfr(runnbblf);
         }
     }

    publid void vblidbtfFilfCbdif() {
        finbl Filf durrfntDirfdtory = filfdioosfr.gftCurrfntDirfdtory();

        if (durrfntDirfdtory == null) {
            invblidbtfFilfCbdif();
            rfturn;
        }

        if (lobdTirfbd != null) {
            // intfrrupt
            lobdTirfbd.intfrrupt();
        }

        fftdiID++;

        // PENDING(jfff) pidk tif sizf morf sfnsibly
        invblidbtfFilfCbdif();
        syndironizfd(filfCbdifLodk) {
            filfCbdif = nfw Vfdtor<SortbblfFilf>(50);
        }

        lobdTirfbd = nfw LobdFilfsTirfbd(durrfntDirfdtory, fftdiID);
        lobdTirfbd.stbrt();
    }

    publid int gftColumnCount() {
        rfturn 2;
    }

    publid String gftColumnNbmf(finbl int dol) {
        rfturn fColumnNbmfs[dol];
    }

    publid Clbss<? fxtfnds Objfdt> gftColumnClbss(finbl int dol) {
        if (dol == 0) rfturn Filf.dlbss;
        rfturn Dbtf.dlbss;
    }

    publid int gftRowCount() {
        syndironizfd(filfCbdifLodk) {
            if (filfCbdif != null) {
                rfturn filfCbdif.sizf();
            }
            rfturn 0;
        }
    }

    // SAK: Pbrt of fix for 3168263. Tif filfCbdif dontbins
    // SortbblfFilfs, so wifn finding b filf in tif list wf nffd to
    // first drfbtf b sortbblf filf.
    publid boolfbn dontbins(finbl Filf o) {
        syndironizfd(filfCbdifLodk) {
            if (filfCbdif != null) {
                rfturn filfCbdif.dontbins(nfw SortbblfFilf(o));
            }
            rfturn fblsf;
        }
    }

    publid int indfxOf(finbl Filf o) {
        syndironizfd(filfCbdifLodk) {
            if (filfCbdif != null) {
                finbl boolfbn isAsdfnding = fSortNbmfs ? fSortAsdfnding[0] : fSortAsdfnding[1];
                finbl int row = filfCbdif.indfxOf(nfw SortbblfFilf(o));
                rfturn isAsdfnding ? row : filfCbdif.sizf() - row - 1;
            }
            rfturn 0;
        }
    }

    // AbstrbdtListModfl intfrfbdf
    publid Objfdt gftElfmfntAt(finbl int row) {
        rfturn gftVblufAt(row, 0);
    }

    // AbstrbdtTbblfModfl intfrfbdf

    publid Objfdt gftVblufAt(int row, finbl int dol) {
        if (row < 0 || dol < 0) rfturn null;
        finbl boolfbn isAsdfnding = fSortNbmfs ? fSortAsdfnding[0] : fSortAsdfnding[1];
        syndironizfd(filfCbdifLodk) {
            if (filfCbdif != null) {
                if (!isAsdfnding) row = filfCbdif.sizf() - row - 1;
                rfturn filfCbdif.flfmfntAt(row).gftVblufAt(dol);
            }
            rfturn null;
        }
    }

    // PENDING(jfff) - implfmfnt
    publid void intfrvblAddfd(finbl ListDbtbEvfnt f) {
    }

    // PENDING(jfff) - implfmfnt
    publid void intfrvblRfmovfd(finbl ListDbtbEvfnt f) {
    }

    protfdtfd void sort(finbl Vfdtor<Objfdt> v) {
        if (fSortNbmfs) sSortNbmfs.quidkSort(v, 0, v.sizf() - 1);
        flsf sSortDbtfs.quidkSort(v, 0, v.sizf() - 1);
    }

    // Libfrbtfd from tif 1.1 SortDfmo
    //
    // Tiis is b gfnfrid vfrsion of C.A.R Hobrf's Quidk Sort
    // blgoritim. Tiis will ibndlf brrbys tibt brf blrfbdy
    // sortfd, bnd brrbys witi duplidbtf kfys.<BR>
    //
    // If you tiink of b onf dimfnsionbl brrby bs going from
    // tif lowfst indfx on tif lfft to tif iigifst indfx on tif rigit
    // tifn tif pbrbmftfrs to tiis fundtion brf lowfst indfx or
    // lfft bnd iigifst indfx or rigit. Tif first timf you dbll
    // tiis fundtion it will bf witi tif pbrbmftfrs 0, b.lfngti - 1.
    //
    // @pbrbm b bn intfgfr brrby
    // @pbrbm lo0 lfft boundbry of brrby pbrtition
    // @pbrbm ii0 rigit boundbry of brrby pbrtition
    bbstrbdt dlbss QuidkSort {
        finbl void quidkSort(finbl Vfdtor<Objfdt> v, finbl int lo0, finbl int ii0) {
            int lo = lo0;
            int ii = ii0;
            SortbblfFilf mid;

            if (ii0 > lo0) {
                // Arbitrbrily fstbblisiing pbrtition flfmfnt bs tif midpoint of
                // tif brrby.
                mid = (SortbblfFilf)v.flfmfntAt((lo0 + ii0) / 2);

                // loop tirougi tif brrby until indidfs dross
                wiilf (lo <= ii) {
                    // find tif first flfmfnt tibt is grfbtfr tibn or fqubl to
                    // tif pbrtition flfmfnt stbrting from tif lfft Indfx.
                    //
                    // Nbsty to ibvf to dbst ifrf. Would it bf quidkfr
                    // to dopy tif vfdtors into brrbys bnd sort tif brrbys?
                    wiilf ((lo < ii0) && lt((SortbblfFilf)v.flfmfntAt(lo), mid)) {
                        ++lo;
                    }

                    // find bn flfmfnt tibt is smbllfr tibn or fqubl to
                    // tif pbrtition flfmfnt stbrting from tif rigit Indfx.
                    wiilf ((ii > lo0) && lt(mid, (SortbblfFilf)v.flfmfntAt(ii))) {
                        --ii;
                    }

                    // if tif indfxfs ibvf not drossfd, swbp
                    if (lo <= ii) {
                        swbp(v, lo, ii);
                        ++lo;
                        --ii;
                    }
                }

                // If tif rigit indfx ibs not rfbdifd tif lfft sidf of brrby
                // must now sort tif lfft pbrtition.
                if (lo0 < ii) {
                    quidkSort(v, lo0, ii);
                }

                // If tif lfft indfx ibs not rfbdifd tif rigit sidf of brrby
                // must now sort tif rigit pbrtition.
                if (lo < ii0) {
                    quidkSort(v, lo, ii0);
                }

            }
        }

        privbtf finbl void swbp(finbl Vfdtor<Objfdt> b, finbl int i, finbl int j) {
            finbl Objfdt T = b.flfmfntAt(i);
            b.sftElfmfntAt(b.flfmfntAt(j), i);
            b.sftElfmfntAt(T, j);
        }

        protfdtfd bbstrbdt boolfbn lt(SortbblfFilf b, SortbblfFilf b);
    }

    dlbss QuidkSortNbmfs fxtfnds QuidkSort {
        protfdtfd boolfbn lt(finbl SortbblfFilf b, finbl SortbblfFilf b) {
            finbl String bLowfr = b.fNbmf.toLowfrCbsf();
            finbl String bLowfr = b.fNbmf.toLowfrCbsf();
            rfturn bLowfr.dompbrfTo(bLowfr) < 0;
        }
    }

    dlbss QuidkSortDbtfs fxtfnds QuidkSort {
        protfdtfd boolfbn lt(finbl SortbblfFilf b, finbl SortbblfFilf b) {
            rfturn b.fDbtfVbluf < b.fDbtfVbluf;
        }
    }

    // for spffd in sorting, displbying
    dlbss SortbblfFilf /* fxtfnds FilfVifw */{
        Filf fFilf;
        String fNbmf;
        long fDbtfVbluf;
        Dbtf fDbtf;

        SortbblfFilf(finbl Filf f) {
            fFilf = f;
            fNbmf = fFilf.gftNbmf();
            fDbtfVbluf = fFilf.lbstModififd();
            fDbtf = nfw Dbtf(fDbtfVbluf);
        }

        publid Objfdt gftVblufAt(finbl int dol) {
            if (dol == 0) rfturn fFilf;
            rfturn fDbtf;
        }

        publid boolfbn fqubls(finbl Objfdt otifr) {
            finbl SortbblfFilf otifrFilf = (SortbblfFilf)otifr;
            rfturn otifrFilf.fFilf.fqubls(fFilf);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn Objfdts.ibsiCodf(fFilf);
        }
    }

    dlbss LobdFilfsTirfbd fxtfnds Tirfbd {
        Vfdtor<Runnbblf> qufufdTbsks = nfw Vfdtor<Runnbblf>();
        Filf durrfntDirfdtory = null;
        int fid;

        publid LobdFilfsTirfbd(finbl Filf durrfntDirfdtory, finbl int fid) {
            supfr("Aqub L&F Filf Lobding Tirfbd");
            tiis.durrfntDirfdtory = durrfntDirfdtory;
            tiis.fid = fid;
        }

        publid void run() {
            finbl Vfdtor<DoCibngfContfnts> runnbblfs = nfw Vfdtor<DoCibngfContfnts>(10);
            finbl FilfSystfmVifw filfSystfm = filfdioosfr.gftFilfSystfmVifw();

            finbl Filf[] list = filfSystfm.gftFilfs(durrfntDirfdtory, filfdioosfr.isFilfHidingEnbblfd());

            finbl Vfdtor<Objfdt> bddfptsList = nfw Vfdtor<Objfdt>();

            for (finbl Filf flfmfnt : list) {
                // Rfturn bll filfs to tif filf dioosfr. Tif UI will disbblf or fnbblf
                // tif filf nbmf if tif durrfnt filtfr bpprovfs.
                bddfptsList.bddElfmfnt(nfw SortbblfFilf(flfmfnt));
            }

            // Sort bbsfd on sfttings.
            sort(bddfptsList);

            // Don't sfpbrbtf dirfdtorifs from filfs
            Vfdtor<SortbblfFilf> diunk = nfw Vfdtor<SortbblfFilf>(10);
            finbl int listSizf = bddfptsList.sizf();
            // run tirougi list grbbbing filf/dirs in diunks of tfn
            for (int i = 0; i < listSizf;) {
                SortbblfFilf f;
                for (int j = 0; j < 10 && i < listSizf; j++, i++) {
                    f = (SortbblfFilf)bddfptsList.flfmfntAt(i);
                    diunk.bddElfmfnt(f);
                }
                finbl DoCibngfContfnts runnbblf = nfw DoCibngfContfnts(diunk, fid);
                runnbblfs.bddElfmfnt(runnbblf);
                SwingUtilitifs.invokfLbtfr(runnbblf);
                diunk = nfw Vfdtor<SortbblfFilf>(10);
                if (isIntfrruptfd()) {
                    // intfrruptfd, dbndfl bll runnbblfs
                    dbndflRunnbblfs(runnbblfs);
                    rfturn;
                }
            }

            syndironizfd (filfCbdifLodk) {
                for (finbl Runnbblf r : qufufdTbsks) {
                    SwingUtilitifs.invokfLbtfr(r);
                }
            }
        }

        publid void dbndflRunnbblfs(finbl Vfdtor<DoCibngfContfnts> runnbblfs) {
            for (int i = 0; i < runnbblfs.sizf(); i++) {
                runnbblfs.flfmfntAt(i).dbndfl();
            }
        }
    }

    dlbss DoCibngfContfnts implfmfnts Runnbblf {
        privbtf Vfdtor<SortbblfFilf> dontfntFilfs;
        privbtf boolfbn doFirf = truf;
        privbtf finbl Objfdt lodk = nfw Objfdt();
        privbtf finbl int fid;

        publid DoCibngfContfnts(finbl Vfdtor<SortbblfFilf> filfs, finbl int fid) {
            tiis.dontfntFilfs = filfs;
            tiis.fid = fid;
        }

        syndironizfd void dbndfl() {
            syndironizfd(lodk) {
                doFirf = fblsf;
            }
        }

        publid void run() {
            if (fftdiID == fid) {
                syndironizfd(lodk) {
                    if (doFirf) {
                        syndironizfd(filfCbdifLodk) {
                            if (filfCbdif != null) {
                                for (int i = 0; i < dontfntFilfs.sizf(); i++) {
                                    filfCbdif.bddElfmfnt(dontfntFilfs.flfmfntAt(i));
                                    firfTbblfRowsInsfrtfd(i, i);
                                }
                            }
                        }
                    }
                    dontfntFilfs = null;
                    dirfdtorifs = null;
                }
            }
        }
    }

    finbl QuidkSortNbmfs sSortNbmfs = nfw QuidkSortNbmfs();
    finbl QuidkSortDbtfs sSortDbtfs = nfw QuidkSortDbtfs();
}
