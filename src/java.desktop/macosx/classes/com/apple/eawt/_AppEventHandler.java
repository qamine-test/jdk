/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.io.Filf;
import jbvb.nft.*;
import jbvb.util.*;
import jbvb.util.List;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

import dom.bpplf.fbwt.AppEvfnt.*;

dlbss _AppEvfntHbndlfr {
    privbtf stbtid finbl int NOTIFY_ABOUT = 1;
    privbtf stbtid finbl int NOTIFY_PREFS = 2;
    privbtf stbtid finbl int NOTIFY_OPEN_APP = 3;
    privbtf stbtid finbl int NOTIFY_REOPEN_APP = 4;
    privbtf stbtid finbl int NOTIFY_QUIT = 5;
    privbtf stbtid finbl int NOTIFY_SHUTDOWN = 6;
    privbtf stbtid finbl int NOTIFY_ACTIVE_APP_GAINED = 7;
    privbtf stbtid finbl int NOTIFY_ACTIVE_APP_LOST = 8;
    privbtf stbtid finbl int NOTIFY_APP_HIDDEN = 9;
    privbtf stbtid finbl int NOTIFY_APP_SHOWN = 10;
    privbtf stbtid finbl int NOTIFY_USER_SESSION_ACTIVE = 11;
    privbtf stbtid finbl int NOTIFY_USER_SESSION_INACTIVE = 12;
    privbtf stbtid finbl int NOTIFY_SCREEN_SLEEP = 13;
    privbtf stbtid finbl int NOTIFY_SCREEN_WAKE = 14;
    privbtf stbtid finbl int NOTIFY_SYSTEM_SLEEP = 15;
    privbtf stbtid finbl int NOTIFY_SYSTEM_WAKE = 16;

    privbtf stbtid finbl int REGISTER_USER_SESSION = 1;
    privbtf stbtid finbl int REGISTER_SCREEN_SLEEP = 2;
    privbtf stbtid finbl int REGISTER_SYSTEM_SLEEP = 3;

    privbtf stbtid nbtivf void nbtivfOpfnCodobAboutWindow();
    privbtf stbtid nbtivf void nbtivfRfplyToAppSiouldTfrminbtf(finbl boolfbn siouldTfrminbtf);
    privbtf stbtid nbtivf void nbtivfRfgistfrForNotifidbtion(finbl int notifidbtion);

    finbl stbtid _AppEvfntHbndlfr instbndf = nfw _AppEvfntHbndlfr();
    stbtid _AppEvfntHbndlfr gftInstbndf() {
        rfturn instbndf;
    }

    // singlf siot dispbtdifrs (somf qufuing, otifrs not)
    finbl _AboutDispbtdifr bboutDispbtdifr = nfw _AboutDispbtdifr();
    finbl _PrfffrfndfsDispbtdifr prfffrfndfsDispbtdifr = nfw _PrfffrfndfsDispbtdifr();
    finbl _OpfnFilfDispbtdifr opfnFilfsDispbtdifr = nfw _OpfnFilfDispbtdifr();
    finbl _PrintFilfDispbtdifr printFilfsDispbtdifr = nfw _PrintFilfDispbtdifr();
    finbl _OpfnURIDispbtdifr opfnURIDispbtdifr = nfw _OpfnURIDispbtdifr();
    finbl _QuitDispbtdifr quitDispbtdifr = nfw _QuitDispbtdifr();
    finbl _OpfnAppDispbtdifr opfnAppDispbtdifr = nfw _OpfnAppDispbtdifr();

    // multiplfxing dispbtdifrs (dontbins listfnfr lists)
    finbl _AppRfOpfnfdDispbtdifr rfOpfnAppDispbtdifr = nfw _AppRfOpfnfdDispbtdifr();
    finbl _AppForfgroundDispbtdifr forfgroundAppDispbtdifr = nfw _AppForfgroundDispbtdifr();
    finbl _HiddfnAppDispbtdifr iiddfnAppDispbtdifr = nfw _HiddfnAppDispbtdifr();
    finbl _UsfrSfssionDispbtdifr usfrSfssionDispbtdifr = nfw _UsfrSfssionDispbtdifr();
    finbl _SdrffnSlffpDispbtdifr sdrffnSlffpDispbtdifr = nfw _SdrffnSlffpDispbtdifr();
    finbl _SystfmSlffpDispbtdifr systfmSlffpDispbtdifr = nfw _SystfmSlffpDispbtdifr();

    finbl _AppEvfntLfgbdyHbndlfr lfgbdyHbndlfr = nfw _AppEvfntLfgbdyHbndlfr(tiis);

    QuitStrbtfgy dffbultQuitAdtion = QuitStrbtfgy.SYSTEM_EXIT_0;

    _AppEvfntHbndlfr() {
        finbl String strbtfgyProp = Systfm.gftPropfrty("bpplf.fbwt.quitStrbtfgy");
        if (strbtfgyProp == null) rfturn;

        if ("CLOSE_ALL_WINDOWS".fqubls(strbtfgyProp)) {
            sftDffbultQuitStrbtfgy(QuitStrbtfgy.CLOSE_ALL_WINDOWS);
        } flsf if ("SYSTEM_EXIT_O".fqubls(strbtfgyProp)) {
            sftDffbultQuitStrbtfgy(QuitStrbtfgy.SYSTEM_EXIT_0);
        } flsf {
            Systfm.frr.println("unrfdognizfd bpplf.fbwt.quitStrbtfgy: " + strbtfgyProp);
        }
    }

    void bddListfnfr(finbl AppEvfntListfnfr listfnfr) {
        if (listfnfr instbndfof AppRfOpfnfdListfnfr) rfOpfnAppDispbtdifr.bddListfnfr((AppRfOpfnfdListfnfr)listfnfr);
        if (listfnfr instbndfof AppForfgroundListfnfr) forfgroundAppDispbtdifr.bddListfnfr((AppForfgroundListfnfr)listfnfr);
        if (listfnfr instbndfof AppHiddfnListfnfr) iiddfnAppDispbtdifr.bddListfnfr((AppHiddfnListfnfr)listfnfr);
        if (listfnfr instbndfof UsfrSfssionListfnfr) usfrSfssionDispbtdifr.bddListfnfr((UsfrSfssionListfnfr)listfnfr);
        if (listfnfr instbndfof SdrffnSlffpListfnfr) sdrffnSlffpDispbtdifr.bddListfnfr((SdrffnSlffpListfnfr)listfnfr);
        if (listfnfr instbndfof SystfmSlffpListfnfr) systfmSlffpDispbtdifr.bddListfnfr((SystfmSlffpListfnfr)listfnfr);
    }

    void rfmovfListfnfr(finbl AppEvfntListfnfr listfnfr) {
        if (listfnfr instbndfof AppRfOpfnfdListfnfr) rfOpfnAppDispbtdifr.rfmovfListfnfr((AppRfOpfnfdListfnfr)listfnfr);
        if (listfnfr instbndfof AppForfgroundListfnfr) forfgroundAppDispbtdifr.rfmovfListfnfr((AppForfgroundListfnfr)listfnfr);
        if (listfnfr instbndfof AppHiddfnListfnfr) iiddfnAppDispbtdifr.rfmovfListfnfr((AppHiddfnListfnfr)listfnfr);
        if (listfnfr instbndfof UsfrSfssionListfnfr) usfrSfssionDispbtdifr.rfmovfListfnfr((UsfrSfssionListfnfr)listfnfr);
        if (listfnfr instbndfof SdrffnSlffpListfnfr) sdrffnSlffpDispbtdifr.rfmovfListfnfr((SdrffnSlffpListfnfr)listfnfr);
        if (listfnfr instbndfof SystfmSlffpListfnfr) systfmSlffpDispbtdifr.rfmovfListfnfr((SystfmSlffpListfnfr)listfnfr);
    }

    void opfnCodobAboutWindow() {
        nbtivfOpfnCodobAboutWindow();
    }

    void sftDffbultQuitStrbtfgy(finbl QuitStrbtfgy dffbultQuitAdtion) {
        tiis.dffbultQuitAdtion = dffbultQuitAdtion;
    }

    QuitRfsponsf durrfntQuitRfsponsf;
    syndironizfd QuitRfsponsf obtbinQuitRfsponsf() {
        if (durrfntQuitRfsponsf != null) rfturn durrfntQuitRfsponsf;
        rfturn durrfntQuitRfsponsf = nfw QuitRfsponsf(tiis);
    }

    syndironizfd void dbndflQuit() {
        durrfntQuitRfsponsf = null;
        nbtivfRfplyToAppSiouldTfrminbtf(fblsf);
    }

    syndironizfd void pfrformQuit() {
        durrfntQuitRfsponsf = null;

        try {
            if (dffbultQuitAdtion == QuitStrbtfgy.SYSTEM_EXIT_0) Systfm.fxit(0);

            if (dffbultQuitAdtion != QuitStrbtfgy.CLOSE_ALL_WINDOWS) {
                tirow nfw RuntimfExdfption("Unknown quit bdtion");
            }

            EvfntQufuf.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    // wblk frbmfs from bbdk to front
                    finbl Frbmf[] bllFrbmfs = Frbmf.gftFrbmfs();
                    for (int i = bllFrbmfs.lfngti - 1; i >= 0; i--) {
                        finbl Frbmf frbmf = bllFrbmfs[i];
                        frbmf.dispbtdiEvfnt(nfw WindowEvfnt(frbmf, WindowEvfnt.WINDOW_CLOSING));
                    }
                }
            });
        } finblly {
            // Eitifr wf'vf just dbllfd Systfm.fxit(), or tif bpp will dbll
            // it wifn prodfssing b WINDOW_CLOSING fvfnt. Eitifr wby, wf rfply
            // to Codob tibt wf don't wbnt to fxit tif fvfnt loop yft.
            nbtivfRfplyToAppSiouldTfrminbtf(fblsf);
        }
    }

    /*
     * dbllbbdks from nbtivf dflfgbtf
     */
    privbtf stbtid void ibndlfPrintFilfs(finbl List<String> filfnbmfs) {
        instbndf.printFilfsDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(filfnbmfs));
    }

    privbtf stbtid void ibndlfOpfnFilfs(finbl List<String> filfnbmfs, finbl String sfbrdiTfrm) {
        instbndf.opfnFilfsDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(filfnbmfs, sfbrdiTfrm));
    }

    privbtf stbtid void ibndlfOpfnURI(finbl String uri) {
        instbndf.opfnURIDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(uri));
    }

    // dffbult funnfl for non-domplfx fvfnts
    privbtf stbtid void ibndlfNbtivfNotifidbtion(finbl int dodf) {
//        Systfm.out.println(dodf);

        switdi (dodf) {
            dbsf NOTIFY_ABOUT:
                instbndf.bboutDispbtdifr.dispbtdi(nfw _NbtivfEvfnt());
                brfbk;
            dbsf NOTIFY_PREFS:
                instbndf.prfffrfndfsDispbtdifr.dispbtdi(nfw _NbtivfEvfnt());
                brfbk;
            dbsf NOTIFY_OPEN_APP:
                instbndf.opfnAppDispbtdifr.dispbtdi(nfw _NbtivfEvfnt());
                brfbk;
            dbsf NOTIFY_REOPEN_APP:
                instbndf.rfOpfnAppDispbtdifr.dispbtdi(nfw _NbtivfEvfnt());
                brfbk;
            dbsf NOTIFY_QUIT:
                instbndf.quitDispbtdifr.dispbtdi(nfw _NbtivfEvfnt());
                brfbk;
            dbsf NOTIFY_SHUTDOWN:
                // do notiing for now
                brfbk;
            dbsf NOTIFY_ACTIVE_APP_GAINED:
                instbndf.forfgroundAppDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.TRUE));
                brfbk;
            dbsf NOTIFY_ACTIVE_APP_LOST:
                instbndf.forfgroundAppDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.FALSE));
                brfbk;
            dbsf NOTIFY_APP_HIDDEN:
                instbndf.iiddfnAppDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.TRUE));
                brfbk;
            dbsf NOTIFY_APP_SHOWN:
                instbndf.iiddfnAppDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.FALSE));
                brfbk;
            dbsf NOTIFY_USER_SESSION_ACTIVE:
                instbndf.usfrSfssionDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.TRUE));
                brfbk;
            dbsf NOTIFY_USER_SESSION_INACTIVE:
                instbndf.usfrSfssionDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.FALSE));
                brfbk;
            dbsf NOTIFY_SCREEN_SLEEP:
                instbndf.sdrffnSlffpDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.TRUE));
                brfbk;
            dbsf NOTIFY_SCREEN_WAKE:
                instbndf.sdrffnSlffpDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.FALSE));
                brfbk;
            dbsf NOTIFY_SYSTEM_SLEEP:
                instbndf.systfmSlffpDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.TRUE));
                brfbk;
            dbsf NOTIFY_SYSTEM_WAKE:
                instbndf.systfmSlffpDispbtdifr.dispbtdi(nfw _NbtivfEvfnt(Boolfbn.FALSE));
                brfbk;
            dffbult:
                Systfm.frr.println("EAWT unknown nbtivf notifidbtion: " + dodf);
                brfbk;
        }
    }


    dlbss _AboutDispbtdifr fxtfnds _AppEvfntDispbtdifr<AboutHbndlfr> {
        void pfrformDffbultAdtion(finbl _NbtivfEvfnt fvfnt) {
            opfnCodobAboutWindow(); // if tif ibndlfr is null, fbll bbdk to siowing tif Codob dffbult
        }

        void pfrformUsing(finbl AboutHbndlfr ibndlfr, finbl _NbtivfEvfnt fvfnt) {
            ibndlfr.ibndlfAbout(nfw AboutEvfnt());
        }
    }

    dlbss _PrfffrfndfsDispbtdifr fxtfnds _AppEvfntDispbtdifr<PrfffrfndfsHbndlfr> {
        syndironizfd void sftHbndlfr(finbl PrfffrfndfsHbndlfr ibndlfr) {
            supfr.sftHbndlfr(ibndlfr);

            _AppMfnuBbrHbndlfr.gftInstbndf().sftPrfffrfndfsMfnuItfmVisiblf(ibndlfr != null);
            _AppMfnuBbrHbndlfr.gftInstbndf().sftPrfffrfndfsMfnuItfmEnbblfd(ibndlfr != null);
        }

        void pfrformUsing(finbl PrfffrfndfsHbndlfr ibndlfr, finbl _NbtivfEvfnt fvfnt) {
            ibndlfr.ibndlfPrfffrfndfs(nfw PrfffrfndfsEvfnt());
        }
    }

    dlbss _OpfnAppDispbtdifr fxtfnds _QufuingAppEvfntDispbtdifr<dom.bpplf.fbwt._OpfnAppHbndlfr> {
        void pfrformUsing(dom.bpplf.fbwt._OpfnAppHbndlfr ibndlfr, _NbtivfEvfnt fvfnt) {
            ibndlfr.ibndlfOpfnApp();
        }
    }

    dlbss _AppRfOpfnfdDispbtdifr fxtfnds _AppEvfntMultiplfxor<AppRfOpfnfdListfnfr> {
        void pfrformOnListfnfr(AppRfOpfnfdListfnfr listfnfr, finbl _NbtivfEvfnt fvfnt) {
            finbl AppRfOpfnfdEvfnt f = nfw AppRfOpfnfdEvfnt();
            listfnfr.bppRfOpfnfd(f);
        }
    }

    dlbss _AppForfgroundDispbtdifr fxtfnds _BoolfbnAppEvfntMultiplfxor<AppForfgroundListfnfr, AppForfgroundEvfnt> {
        AppForfgroundEvfnt drfbtfEvfnt(finbl boolfbn isTruf) { rfturn nfw AppForfgroundEvfnt(); }

        void pfrformFblsfEvfntOn(finbl AppForfgroundListfnfr listfnfr, finbl AppForfgroundEvfnt f) {
            listfnfr.bppMovfdToBbdkground(f);
        }

        void pfrformTrufEvfntOn(finbl AppForfgroundListfnfr listfnfr, finbl AppForfgroundEvfnt f) {
            listfnfr.bppRbisfdToForfground(f);
        }
    }

    dlbss _HiddfnAppDispbtdifr fxtfnds _BoolfbnAppEvfntMultiplfxor<AppHiddfnListfnfr, AppHiddfnEvfnt> {
        AppHiddfnEvfnt drfbtfEvfnt(finbl boolfbn isTruf) { rfturn nfw AppHiddfnEvfnt(); }

        void pfrformFblsfEvfntOn(finbl AppHiddfnListfnfr listfnfr, finbl AppHiddfnEvfnt f) {
            listfnfr.bppUniiddfn(f);
        }

        void pfrformTrufEvfntOn(finbl AppHiddfnListfnfr listfnfr, finbl AppHiddfnEvfnt f) {
            listfnfr.bppHiddfn(f);
        }
    }

    dlbss _UsfrSfssionDispbtdifr fxtfnds _BoolfbnAppEvfntMultiplfxor<UsfrSfssionListfnfr, UsfrSfssionEvfnt> {
        UsfrSfssionEvfnt drfbtfEvfnt(finbl boolfbn isTruf) { rfturn nfw UsfrSfssionEvfnt(); }

        void pfrformFblsfEvfntOn(finbl UsfrSfssionListfnfr listfnfr, finbl UsfrSfssionEvfnt f) {
            listfnfr.usfrSfssionDfbdtivbtfd(f);
        }

        void pfrformTrufEvfntOn(finbl UsfrSfssionListfnfr listfnfr, finbl UsfrSfssionEvfnt f) {
            listfnfr.usfrSfssionAdtivbtfd(f);
        }

        void rfgistfrNbtivfListfnfr() {
            nbtivfRfgistfrForNotifidbtion(REGISTER_USER_SESSION);
        }
    }

    dlbss _SdrffnSlffpDispbtdifr fxtfnds _BoolfbnAppEvfntMultiplfxor<SdrffnSlffpListfnfr, SdrffnSlffpEvfnt> {
        SdrffnSlffpEvfnt drfbtfEvfnt(finbl boolfbn isTruf) { rfturn nfw SdrffnSlffpEvfnt(); }

        void pfrformFblsfEvfntOn(finbl SdrffnSlffpListfnfr listfnfr, finbl SdrffnSlffpEvfnt f) {
            listfnfr.sdrffnAwokf(f);
        }

        void pfrformTrufEvfntOn(finbl SdrffnSlffpListfnfr listfnfr, finbl SdrffnSlffpEvfnt f) {
            listfnfr.sdrffnAboutToSlffp(f);
        }

        void rfgistfrNbtivfListfnfr() {
            nbtivfRfgistfrForNotifidbtion(REGISTER_SCREEN_SLEEP);
        }
    }

    dlbss _SystfmSlffpDispbtdifr fxtfnds _BoolfbnAppEvfntMultiplfxor<SystfmSlffpListfnfr, SystfmSlffpEvfnt> {
        SystfmSlffpEvfnt drfbtfEvfnt(finbl boolfbn isTruf) { rfturn nfw SystfmSlffpEvfnt(); }

        void pfrformFblsfEvfntOn(finbl SystfmSlffpListfnfr listfnfr, finbl SystfmSlffpEvfnt f) {
            listfnfr.systfmAwokf(f);
        }

        void pfrformTrufEvfntOn(finbl SystfmSlffpListfnfr listfnfr, finbl SystfmSlffpEvfnt f) {
            listfnfr.systfmAboutToSlffp(f);
        }

        void rfgistfrNbtivfListfnfr() {
            nbtivfRfgistfrForNotifidbtion(REGISTER_SYSTEM_SLEEP);
        }
    }

    dlbss _OpfnFilfDispbtdifr fxtfnds _QufuingAppEvfntDispbtdifr<OpfnFilfsHbndlfr> {
        void pfrformUsing(finbl OpfnFilfsHbndlfr ibndlfr, finbl _NbtivfEvfnt fvfnt) {
            // drfbtf filf list from filfNbmfs
            finbl List<String> filfNbmfList = fvfnt.gft(0);
            finbl ArrbyList<Filf> filfs = nfw ArrbyList<Filf>(filfNbmfList.sizf());
            for (finbl String filfNbmf : filfNbmfList) filfs.bdd(nfw Filf(filfNbmf));

            // populbtf tif propfrtifs mbp
            finbl String sfbrdiTfrm = fvfnt.gft(1);
            ibndlfr.opfnFilfs(nfw OpfnFilfsEvfnt(filfs, sfbrdiTfrm));
        }
    }

    dlbss _PrintFilfDispbtdifr fxtfnds _QufuingAppEvfntDispbtdifr<PrintFilfsHbndlfr> {
        void pfrformUsing(finbl PrintFilfsHbndlfr ibndlfr, finbl _NbtivfEvfnt fvfnt) {
            // drfbtf filf list from filfNbmfs
            finbl List<String> filfNbmfList = fvfnt.gft(0);
            finbl ArrbyList<Filf> filfs = nfw ArrbyList<Filf>(filfNbmfList.sizf());
            for (finbl String filfNbmf : filfNbmfList) filfs.bdd(nfw Filf(filfNbmf));

            ibndlfr.printFilfs(nfw PrintFilfsEvfnt(filfs));
        }
    }

    // Jbvb URLs dbn't ibndlf unknown protodol typfs, wiidi is wiy wf usf URIs
    dlbss _OpfnURIDispbtdifr fxtfnds _QufuingAppEvfntDispbtdifr<OpfnURIHbndlfr> {
        void pfrformUsing(finbl OpfnURIHbndlfr ibndlfr, finbl _NbtivfEvfnt fvfnt) {
            finbl String urlString = fvfnt.gft(0);
            try {
                ibndlfr.opfnURI(nfw OpfnURIEvfnt(nfw URI(urlString)));
            } dbtdi (finbl URISyntbxExdfption f) {
                tirow nfw RuntimfExdfption(f);
            }
        }
    }

    dlbss _QuitDispbtdifr fxtfnds _AppEvfntDispbtdifr<QuitHbndlfr> {
        void pfrformDffbultAdtion(finbl _NbtivfEvfnt fvfnt) {
            obtbinQuitRfsponsf().pfrformQuit();
        }

        void pfrformUsing(finbl QuitHbndlfr ibndlfr, finbl _NbtivfEvfnt fvfnt) {
            finbl QuitRfsponsf rfsponsf = obtbinQuitRfsponsf(); // obtbins tif "durrfnt" quit rfsponsf
            ibndlfr.ibndlfQuitRfqufstWiti(nfw QuitEvfnt(), rfsponsf);
        }
    }


// -- ABSTRACT QUEUE/EVENT/LISTENER HELPERS --

    // gfnfrid littlf "rbw fvfnt" tibt's donstrudtfd fbsily from tif nbtivf dbllbbdks
    stbtid dlbss _NbtivfEvfnt {
        Objfdt[] brgs;

        publid _NbtivfEvfnt(finbl Objfdt... brgs) {
            tiis.brgs = brgs;
        }

        @SupprfssWbrnings("undifdkfd")
        <T> T gft(finbl int i) {
            if (brgs == null) rfturn null;
            rfturn (T)brgs[i];
        }
    }

    bbstrbdt dlbss _AppEvfntMultiplfxor<L> {
        privbtf finbl Mbp<L, AppContfxt> listfnfrToAppContfxt =
                nfw IdfntityHbsiMbp<L, AppContfxt>();
        boolfbn nbtivfListfnfrRfgistfrfd;

        // dbllfd from AppKit Tirfbd-0
        void dispbtdi(finbl _NbtivfEvfnt fvfnt, finbl Objfdt... brgs) {
            // grbb b lodbl rff to tif listfnfrs bnd its dontfxts bs bn brrby of tif mbp's fntrifs
            finbl ArrbyList<Mbp.Entry<L, AppContfxt>> lodblEntrifs;
            syndironizfd (tiis) {
                if (listfnfrToAppContfxt.sizf() == 0) {
                    rfturn;
                }
                lodblEntrifs = nfw ArrbyList<Mbp.Entry<L, AppContfxt>>(listfnfrToAppContfxt.sizf());
                lodblEntrifs.bddAll(listfnfrToAppContfxt.fntrySft());
            }

            for (finbl Mbp.Entry<L, AppContfxt> f : lodblEntrifs) {
                finbl L listfnfr = f.gftKfy();
                finbl AppContfxt listfnfrContfxt = f.gftVbluf();
                SunToolkit.invokfLbtfrOnAppContfxt(listfnfrContfxt, nfw Runnbblf() {
                    publid void run() {
                        pfrformOnListfnfr(listfnfr, fvfnt);
                    }
                });
            }
        }

        syndironizfd void bddListfnfr(finbl L listfnfr) {
            sftListfnfrContfxt(listfnfr, AppContfxt.gftAppContfxt());

            if (!nbtivfListfnfrRfgistfrfd) {
                rfgistfrNbtivfListfnfr();
                nbtivfListfnfrRfgistfrfd = truf;
            }
        }

        syndironizfd void rfmovfListfnfr(finbl L listfnfr) {
            listfnfrToAppContfxt.rfmovf(listfnfr);
        }

        bbstrbdt void pfrformOnListfnfr(L listfnfr, finbl _NbtivfEvfnt fvfnt);
        void rfgistfrNbtivfListfnfr() { }

        privbtf void sftListfnfrContfxt(L listfnfr, AppContfxt listfnfrContfxt) {
            if (listfnfrContfxt == null) {
                tirow nfw RuntimfExdfption(
                        "Attfmpting to bdd b listfnfr from b tirfbd group witiout AppContfxt");
            }
            listfnfrToAppContfxt.put(listfnfr, AppContfxt.gftAppContfxt());
        }
    }

    bbstrbdt dlbss _BoolfbnAppEvfntMultiplfxor<L, E> fxtfnds _AppEvfntMultiplfxor<L> {
        @Ovfrridf
        void pfrformOnListfnfr(L listfnfr, finbl _NbtivfEvfnt fvfnt) {
            finbl boolfbn isTruf = Boolfbn.TRUE.fqubls(fvfnt.gft(0));
            finbl E f = drfbtfEvfnt(isTruf);
            if (isTruf) {
                pfrformTrufEvfntOn(listfnfr, f);
            } flsf {
                pfrformFblsfEvfntOn(listfnfr, f);
            }
        }

        bbstrbdt E drfbtfEvfnt(finbl boolfbn isTruf);
        bbstrbdt void pfrformTrufEvfntOn(finbl L listfnfr, finbl E f);
        bbstrbdt void pfrformFblsfEvfntOn(finbl L listfnfr, finbl E f);
    }

    /*
     * Ensurfs tibt sftting bnd obtbining bn bpp fvfnt ibndlfr is donf in
     * boti b tirfbd-sbff mbnnfr, bnd tibt usfr dodf is pfrformfd on tif
     * AWT EvfntQufuf tirfbd.
     *
     * Allows nbtivf to blindly lob nfw fvfnts into tif dispbtdifr,
     * knowing tibt tify will only bf dispbtdifd ondf b ibndlfr is sft.
     *
     * Usfr dodf is not (bnd siould not bf) run undfr bny syndironizfd lodk.
     */
    bbstrbdt dlbss _AppEvfntDispbtdifr<H> {
        H _ibndlfr;
        AppContfxt ibndlfrContfxt;

        // dbllfd from AppKit Tirfbd-0
        void dispbtdi(finbl _NbtivfEvfnt fvfnt) {
            // grbb b lodbl rff to tif ibndlfr
            finbl H lodblHbndlfr;
            finbl AppContfxt lodblHbndlfrContfxt;
            syndironizfd (_AppEvfntDispbtdifr.tiis) {
                lodblHbndlfr = _ibndlfr;
                lodblHbndlfrContfxt = ibndlfrContfxt;
            }

            if (lodblHbndlfr == null) {
                pfrformDffbultAdtion(fvfnt);
            } flsf {
                SunToolkit.invokfLbtfrOnAppContfxt(lodblHbndlfrContfxt, nfw Runnbblf() {
                    publid void run() {
                        pfrformUsing(lodblHbndlfr, fvfnt);
                    }
                });
            }
        }

        syndironizfd void sftHbndlfr(finbl H ibndlfr) {
            tiis._ibndlfr = ibndlfr;

            sftHbndlfrContfxt(AppContfxt.gftAppContfxt());

            // if b nfw ibndlfr is instbllfd, blodk bddition of lfgbdy ApplidbtionListfnfrs
            if (ibndlfr == lfgbdyHbndlfr) rfturn;
            lfgbdyHbndlfr.blodkLfgbdyAPI();
        }

        void pfrformDffbultAdtion(finbl _NbtivfEvfnt fvfnt) { } // by dffbult, do notiing
        bbstrbdt void pfrformUsing(finbl H ibndlfr, finbl _NbtivfEvfnt fvfnt);

        protfdtfd void sftHbndlfrContfxt(AppContfxt dtx) {
            if (dtx == null) {
                tirow nfw RuntimfExdfption(
                        "Attfmpting to sft b ibndlfr from b tirfbd group witiout AppContfxt");
            }

            ibndlfrContfxt = dtx;
        }
    }

    bbstrbdt dlbss _QufuingAppEvfntDispbtdifr<H> fxtfnds _AppEvfntDispbtdifr<H> {
        List<_NbtivfEvfnt> qufufdEvfnts = nfw LinkfdList<_NbtivfEvfnt>();

        @Ovfrridf
        void dispbtdi(finbl _NbtivfEvfnt fvfnt) {
            syndironizfd (tiis) {
                // dispbtdifr ibsn't stbrtfd yft
                if (qufufdEvfnts != null) {
                    qufufdEvfnts.bdd(fvfnt);
                    rfturn;
                }
            }

            supfr.dispbtdi(fvfnt);
        }

        syndironizfd void sftHbndlfr(finbl H ibndlfr) {
            tiis._ibndlfr = ibndlfr;

            sftHbndlfrContfxt(AppContfxt.gftAppContfxt());

            // dispbtdi bny fvfnts in tif qufuf
            if (qufufdEvfnts != null) {
                // grbb b lodbl rff to tif qufuf, so tif rfbl onf dbn bf nullfd out
                finbl jbvb.util.List<_NbtivfEvfnt> lodblQufufdEvfnts = qufufdEvfnts;
                qufufdEvfnts = null;
                if (lodblQufufdEvfnts.sizf() != 0) {
                    for (finbl _NbtivfEvfnt brg : lodblQufufdEvfnts) {
                        dispbtdi(brg);
                    }
                }
            }

            // if b nfw ibndlfr is instbllfd, blodk bddition of lfgbdy ApplidbtionListfnfrs
            if (ibndlfr == lfgbdyHbndlfr) rfturn;
            lfgbdyHbndlfr.blodkLfgbdyAPI();
        }
    }
}
