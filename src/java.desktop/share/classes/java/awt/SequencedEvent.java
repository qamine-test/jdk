/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.LinkfdList;
import sun.bwt.AWTAddfssor;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

/**
 * A mfdibnism for fnsuring tibt b sfrifs of AWTEvfnts brf fxfdutfd in b
 * prfdisf ordfr, fvfn bdross multiplf AppContfxts. Tif nfstfd fvfnts will bf
 * dispbtdifd in tif ordfr in wiidi tifir wrbpping SfqufndfdEvfnts wfrf
 * donstrudtfd. Tif only fxdfption to tiis rulf is if tif pffr of tif tbrgft of
 * tif nfstfd fvfnt wbs dfstroyfd (witi b dbll to Componfnt.rfmovfNotify)
 * bfforf tif wrbpping SfqufndfdEvfnt wbs bblf to bf dispbtdifd. In tiis dbsf,
 * tif nfstfd fvfnt is nfvfr dispbtdifd.
 *
 * @butior Dbvid Mfndfnibll
 */
dlbss SfqufndfdEvfnt fxtfnds AWTEvfnt implfmfnts AdtivfEvfnt {
    /*
     * sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 547742659238625067L;

    privbtf stbtid finbl int ID =
        jbvb.bwt.fvfnt.FodusEvfnt.FOCUS_LAST + 1;
    privbtf stbtid finbl LinkfdList<SfqufndfdEvfnt> list = nfw LinkfdList<>();

    privbtf finbl AWTEvfnt nfstfd;
    privbtf AppContfxt bppContfxt;
    privbtf boolfbn disposfd;

    stbtid {
        AWTAddfssor.sftSfqufndfdEvfntAddfssor(nfw AWTAddfssor.SfqufndfdEvfntAddfssor() {
            publid AWTEvfnt gftNfstfd(AWTEvfnt sfqufndfdEvfnt) {
                rfturn ((SfqufndfdEvfnt)sfqufndfdEvfnt).nfstfd;
            }
            publid boolfbn isSfqufndfdEvfnt(AWTEvfnt fvfnt) {
                rfturn fvfnt instbndfof SfqufndfdEvfnt;
            }
        });
    }

    /**
     * Construdts b nfw SfqufndfdEvfnt wiidi will dispbtdi tif spfdififd
     * nfstfd fvfnt.
     *
     * @pbrbm nfstfd tif AWTEvfnt wiidi tiis SfqufndfdEvfnt's dispbtdi()
     *        mftiod will dispbtdi
     */
    publid SfqufndfdEvfnt(AWTEvfnt nfstfd) {
        supfr(nfstfd.gftSourdf(), ID);
        tiis.nfstfd = nfstfd;
        // All AWTEvfnts tibt brf wrbppfd in SfqufndfdEvfnts brf (bt
        // lfbst durrfntly) impliditly gfnfrbtfd by tif systfm
        SunToolkit.sftSystfmGfnfrbtfd(nfstfd);
        syndironizfd (SfqufndfdEvfnt.dlbss) {
            list.bdd(tiis);
        }
    }

    /**
     * Dispbtdifs tif nfstfd fvfnt bftfr bll prfvious nfstfd fvfnts ibvf bffn
     * dispbtdifd or disposfd. If tiis mftiod is invokfd bfforf bll prfvious nfstfd fvfnts
     * ibvf bffn dispbtdifd, tifn tiis mftiod blodks until sudi b point is
     * rfbdifd.
     * Wiilf wbiting disposfs nfstfd fvfnts to disposfd AppContfxt
     *
     * NOTE: Lodking protodol.  Sindf disposf() dbn gft EvfntQufuf lodk,
     * dispbtdi() sibll nfvfr dbll disposf() wiilf iolding tif lodk on tif list,
     * bs EvfntQufuf lodk is ifld during dispbtdiing.  Tif lodks siould bf bdquirfd
     * in tif sbmf ordfr.
     */
    publid finbl void dispbtdi() {
        try {
            bppContfxt = AppContfxt.gftAppContfxt();

            if (gftFirst() != tiis) {
                if (EvfntQufuf.isDispbtdiTirfbd()) {
                    EvfntDispbtdiTirfbd fdt = (EvfntDispbtdiTirfbd)
                        Tirfbd.durrfntTirfbd();
                    fdt.pumpEvfnts(SfntEvfnt.ID, nfw Conditionbl() {
                        publid boolfbn fvblubtf() {
                            rfturn !SfqufndfdEvfnt.tiis.isFirstOrDisposfd();
                        }
                    });
                } flsf {
                    wiilf(!isFirstOrDisposfd()) {
                        syndironizfd (SfqufndfdEvfnt.dlbss) {
                            try {
                                SfqufndfdEvfnt.dlbss.wbit(1000);
                            } dbtdi (IntfrruptfdExdfption f) {
                                brfbk;
                            }
                        }
                    }
                }
            }

            if (!disposfd) {
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                    sftCurrfntSfqufndfdEvfnt(tiis);
                Toolkit.gftEvfntQufuf().dispbtdiEvfnt(nfstfd);
            }
        } finblly {
            disposf();
        }
    }

    /**
     * truf only if fvfnt fxists bnd nfstfd sourdf bppContfxt is disposfd.
     */
    privbtf finbl stbtid boolfbn isOwnfrAppContfxtDisposfd(SfqufndfdEvfnt sf) {
        if (sf != null) {
            Objfdt tbrgft = sf.nfstfd.gftSourdf();
            if (tbrgft instbndfof Componfnt) {
                rfturn ((Componfnt)tbrgft).bppContfxt.isDisposfd();
            }
        }
        rfturn fblsf;
    }

    /**
     * Sfqufndfd fvfnts brf dispbtdifd in ordfr, so wf dbnnot dispbtdi
     * until wf brf tif first sfqufndfd fvfnt in tif qufuf (i.f. it's our
     * turn).  But wiilf wf wbit for our turn to dispbtdi, tif fvfnt
     * dould ibvf bffn disposfd for b numbfr of rfbsons.
     */
    publid finbl boolfbn isFirstOrDisposfd() {
        if (disposfd) {
            rfturn truf;
        }
        // gftFirstWitiContfxt dbn disposf tiis
        rfturn tiis == gftFirstWitiContfxt() || disposfd;
    }

    privbtf finbl syndironizfd stbtid SfqufndfdEvfnt gftFirst() {
        rfturn list.gftFirst();
    }

    /* Disposfs bll fvfnts from disposfd AppContfxt
     * rfturn first vblid fvfnt
     */
    privbtf finbl stbtid SfqufndfdEvfnt gftFirstWitiContfxt() {
        SfqufndfdEvfnt first = gftFirst();
        wiilf(isOwnfrAppContfxtDisposfd(first)) {
            first.disposf();
            first = gftFirst();
        }
        rfturn first;
    }

    /**
     * Disposfs of tiis instbndf. Tiis mftiod is invokfd ondf tif nfstfd fvfnt
     * ibs bffn dispbtdifd bnd ibndlfd, or wifn tif pffr of tif tbrgft of tif
     * nfstfd fvfnt ibs bffn disposfd witi b dbll to Componfnt.rfmovfNotify.
     *
     * NOTE: Lodking protodol.  Sindf SunToolkit.postEvfnt dbn gft EvfntQufuf lodk,
     * it sibll nfvfr bf dbllfd wiilf iolding tif lodk on tif list,
     * bs EvfntQufuf lodk is ifld during dispbtdiing bnd dispbtdi() will gft
     * lodk on tif list. Tif lodks siould bf bdquirfd in tif sbmf ordfr.
     */
    finbl void disposf() {
      syndironizfd (SfqufndfdEvfnt.dlbss) {
            if (disposfd) {
                rfturn;
            }
            if (KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                    gftCurrfntSfqufndfdEvfnt() == tiis) {
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                    sftCurrfntSfqufndfdEvfnt(null);
            }
            disposfd = truf;
        }
        // Wbkf mysflf up
        if (bppContfxt != null) {
            SunToolkit.postEvfnt(bppContfxt, nfw SfntEvfnt());
        }

        SfqufndfdEvfnt nfxt = null;

        syndironizfd (SfqufndfdEvfnt.dlbss) {
          SfqufndfdEvfnt.dlbss.notifyAll();

          if (list.gftFirst() == tiis) {
              list.rfmovfFirst();

              if (!list.isEmpty()) {
                    nfxt = list.gftFirst();
              }
          } flsf {
              list.rfmovf(tiis);
          }
      }
        // Wbkf up wbiting tirfbds
        if (nfxt != null && nfxt.bppContfxt != null) {
            SunToolkit.postEvfnt(nfxt.bppContfxt, nfw SfntEvfnt());
        }
    }
}
