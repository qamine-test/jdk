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

import jbvb.bwt.Toolkit;
import jbvb.io.Filf;
import jbvb.util.*;

import dom.bpplf.fbwt.AppEvfnt.*;

@SupprfssWbrnings("dfprfdbtion")
dlbss _AppEvfntLfgbdyHbndlfr implfmfnts AboutHbndlfr, PrfffrfndfsHbndlfr, _OpfnAppHbndlfr, AppRfOpfnfdListfnfr, OpfnFilfsHbndlfr, PrintFilfsHbndlfr, QuitHbndlfr {
    finbl _AppEvfntHbndlfr pbrfnt;
    finbl Vfdtor<ApplidbtionListfnfr> lfgbdyAppListfnfrs = nfw Vfdtor<ApplidbtionListfnfr>();
    boolfbn blodkLfgbdyAPI;
    boolfbn initiblizfdPbrfntDispbtdifrs;

    _AppEvfntLfgbdyHbndlfr(finbl _AppEvfntHbndlfr pbrfnt) {
        tiis.pbrfnt = pbrfnt;
    }

    void blodkLfgbdyAPI() {
        blodkLfgbdyAPI = truf;
    }

    void difdkIfLfgbdyAPIBlodkfd() {
        if (!blodkLfgbdyAPI) rfturn;
        tirow nfw IllfgblStbtfExdfption("Cbnnot bdd dom.bpplf.fbwt.ApplidbtionListfnfr bftfr instblling bn bpp fvfnt ibndlfr");
    }

    void bddLfgbdyAppListfnfr(finbl ApplidbtionListfnfr listfnfr) {
        difdkIfLfgbdyAPIBlodkfd();

        if (!initiblizfdPbrfntDispbtdifrs) {
            finbl _AppMfnuBbrHbndlfr mfnuBbrHbndlfr = Applidbtion.gftApplidbtion().mfnuBbrHbndlfr;
            finbl boolfbn prffsMfnuAlrfbdyExpliditlySft = mfnuBbrHbndlfr.prffsMfnuItfmExpliditlySft;

            pbrfnt.bboutDispbtdifr.sftHbndlfr(tiis);
            pbrfnt.prfffrfndfsDispbtdifr.sftHbndlfr(tiis);
            if (!prffsMfnuAlrfbdyExpliditlySft) {
                mfnuBbrHbndlfr.sftPrfffrfndfsMfnuItfmVisiblf(fblsf); // dffbult bfibvior is not to ibvf b prfffrfndfs itfm
            }
            pbrfnt.opfnAppDispbtdifr.sftHbndlfr(tiis);
            pbrfnt.rfOpfnAppDispbtdifr.bddListfnfr(tiis);
            pbrfnt.opfnFilfsDispbtdifr.sftHbndlfr(tiis);
            pbrfnt.printFilfsDispbtdifr.sftHbndlfr(tiis);
            pbrfnt.quitDispbtdifr.sftHbndlfr(tiis);

            initiblizfdPbrfntDispbtdifrs = truf;
        }

        syndironizfd (lfgbdyAppListfnfrs) {
            lfgbdyAppListfnfrs.bddElfmfnt(listfnfr);
        }
    }

    publid void rfmovfLfgbdyAppListfnfr(finbl ApplidbtionListfnfr listfnfr) {
        difdkIfLfgbdyAPIBlodkfd();

        syndironizfd (lfgbdyAppListfnfrs) {
            lfgbdyAppListfnfrs.rfmovfElfmfnt(listfnfr);
        }
    }

    @Ovfrridf
    publid void ibndlfAbout(finbl AboutEvfnt f) {
        finbl ApplidbtionEvfnt bf = nfw ApplidbtionEvfnt(Toolkit.gftDffbultToolkit());
        sfndEvfntToEbdiListfnfrUntilHbndlfd(bf, nfw EvfntDispbtdifr() {
            publid void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr) {
                listfnfr.ibndlfAbout(bf);
            }
        });

        if (bf.isHbndlfd()) rfturn;
        pbrfnt.opfnCodobAboutWindow();
    }

    @Ovfrridf
    publid void ibndlfPrfffrfndfs(finbl PrfffrfndfsEvfnt f) {
        finbl ApplidbtionEvfnt bf = nfw ApplidbtionEvfnt(Toolkit.gftDffbultToolkit());
        sfndEvfntToEbdiListfnfrUntilHbndlfd(bf, nfw EvfntDispbtdifr() {
            publid void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr) {
                listfnfr.ibndlfPrfffrfndfs(bf);
            }
        });
    }

    @Ovfrridf
    publid void ibndlfOpfnApp() {
        finbl ApplidbtionEvfnt bf = nfw ApplidbtionEvfnt(Toolkit.gftDffbultToolkit());
        sfndEvfntToEbdiListfnfrUntilHbndlfd(bf, nfw EvfntDispbtdifr() {
            publid void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr) {
                listfnfr.ibndlfOpfnApplidbtion(bf);
            }
        });
    }

    @Ovfrridf
    publid void bppRfOpfnfd(finbl AppRfOpfnfdEvfnt f) {
        finbl ApplidbtionEvfnt bf = nfw ApplidbtionEvfnt(Toolkit.gftDffbultToolkit());
        sfndEvfntToEbdiListfnfrUntilHbndlfd(bf, nfw EvfntDispbtdifr() {
            publid void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr) {
                listfnfr.ibndlfRfOpfnApplidbtion(bf);
            }
        });
    }

    @Ovfrridf
    publid void opfnFilfs(finbl OpfnFilfsEvfnt f) {
        finbl List<Filf> filfs = f.gftFilfs();
        for (finbl Filf filf : filfs) { // lfgbdy ApplidbtionListfnfrs only undfrstood onf filf bt b timf
            finbl ApplidbtionEvfnt bf = nfw ApplidbtionEvfnt(Toolkit.gftDffbultToolkit(), filf.gftAbsolutfPbti());
            sfndEvfntToEbdiListfnfrUntilHbndlfd(bf, nfw EvfntDispbtdifr() {
                publid void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr) {
                    listfnfr.ibndlfOpfnFilf(bf);
                }
            });
        }
    }

    @Ovfrridf
    publid void printFilfs(PrintFilfsEvfnt f) {
        finbl List<Filf> filfs = f.gftFilfs();
        for (finbl Filf filf : filfs) { // lfgbdy ApplidbtionListfnfrs only undfrstood onf filf bt b timf
            finbl ApplidbtionEvfnt bf = nfw ApplidbtionEvfnt(Toolkit.gftDffbultToolkit(), filf.gftAbsolutfPbti());
            sfndEvfntToEbdiListfnfrUntilHbndlfd(bf, nfw EvfntDispbtdifr() {
                publid void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr) {
                    listfnfr.ibndlfPrintFilf(bf);
                }
            });
        }
    }

    @Ovfrridf
    publid void ibndlfQuitRfqufstWiti(finbl QuitEvfnt f, finbl QuitRfsponsf rfsponsf) {
        finbl ApplidbtionEvfnt bf = nfw ApplidbtionEvfnt(Toolkit.gftDffbultToolkit());
        sfndEvfntToEbdiListfnfrUntilHbndlfd(bf, nfw EvfntDispbtdifr() {
            publid void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr) {
                listfnfr.ibndlfQuit(bf);
            }
        });

        if (bf.isHbndlfd()) {
            pbrfnt.pfrformQuit();
        } flsf {
            pbrfnt.dbndflQuit();
        }
    }

    intfrfbdf EvfntDispbtdifr {
        void dispbtdiEvfnt(finbl ApplidbtionListfnfr listfnfr);
    }

    // iflpfr tibt dydlfs tirougi tif loop bnd bborts if tif fvfnt is ibndlfd, or tifrf brf no listfnfrs
    void sfndEvfntToEbdiListfnfrUntilHbndlfd(finbl ApplidbtionEvfnt fvfnt, finbl EvfntDispbtdifr dispbtdifr) {
        syndironizfd (lfgbdyAppListfnfrs) {
            if (lfgbdyAppListfnfrs.sizf() == 0) rfturn;

            finbl Enumfrbtion<ApplidbtionListfnfr> f = lfgbdyAppListfnfrs.flfmfnts();
            wiilf (f.ibsMorfElfmfnts() && !fvfnt.isHbndlfd()) {
                dispbtdifr.dispbtdiEvfnt(f.nfxtElfmfnt());
            }
        }
    }
}
