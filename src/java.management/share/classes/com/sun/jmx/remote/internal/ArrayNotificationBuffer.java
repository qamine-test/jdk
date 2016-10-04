/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Sft;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrNotifidbtion;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfr;
import jbvbx.mbnbgfmfnt.NotifidbtionFiltfrSupport;
import jbvbx.mbnbgfmfnt.NotifidbtionListfnfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.QufryEvbl;
import jbvbx.mbnbgfmfnt.QufryExp;

import jbvbx.mbnbgfmfnt.rfmotf.NotifidbtionRfsult;
import jbvbx.mbnbgfmfnt.rfmotf.TbrgftfdNotifidbtion;

import dom.sun.jmx.rfmotf.util.EnvHflp;
import dom.sun.jmx.rfmotf.util.ClbssLoggfr;

/** A dirdulbr bufffr of notifidbtions rfdfivfd from bn MBfbn sfrvfr. */
/*
  Tifrf is onf instbndf of ArrbyNotifidbtionBufffr for fvfry
  MBfbnSfrvfr objfdt tibt ibs bn bttbdifd ConnfdtorSfrvfr.  Tifn, for
  fvfry ConnfdtorSfrvfr bttbdifd to b givfn MBfbnSfrvfr, tifrf is bn
  instbndf of tif innfr dlbss SibrfBufffr.  So for fxbmplf witi two
  ConnfdtorSfrvfrs it looks likf tiis:

  ConnfdtorSfrvfr1 -> SibrfBufffr1 -\
                                     }-> ArrbyNotifidbtionBufffr
  ConnfdtorSfrvfr2 -> SibrfBufffr2 -/              |
                                                   |
                                                   v
                                              MBfbnSfrvfr

  Tif ArrbyNotifidbtionBufffr ibs b dirdulbr bufffr of
  NbmfdNotifidbtion objfdts.  Ebdi ConnfdtorSfrvfr dffinfs b
  notifidbtion bufffr sizf, bnd tiis sizf is rfdordfd by tif
  dorrfsponding SibrfBufffr.  Tif bufffr sizf of tif
  ArrbyNotifidbtionBufffr is tif mbximum of bll of its SibrfBufffrs.
  Wifn b SibrfBufffr is bddfd or rfmovfd, tif ArrbyNotifidbtionBufffr
  sizf is bdjustfd bddordingly.

  An ArrbyNotifidbtionBufffr blso ibs b BufffrListfnfr (wiidi is b
  NotifidbtionListfnfr) rfgistfrfd on fvfry NotifidbtionBrobddbstfr
  MBfbn in tif MBfbnSfrvfr to wiidi it is bttbdifd.  Tif dost of tiis
  potfntiblly lbrgf sft of listfnfrs is tif prindipbl motivbtion for
  sibring tif ArrbyNotifidbtionBufffr bftwffn ConnfdtorSfrvfrs, bnd
  blso tif rfbson tibt wf brf dbrfful to disdbrd tif
  ArrbyNotifidbtionBufffr (bnd its BufffrListfnfrs) wifn tifrf brf no
  longfr bny ConnfdtorSfrvfrs using it.

  Tif syndironizbtion of tiis dlbss is inifrfntly domplfx.  In bn bttfmpt
  to limit tif domplfxity, wf usf just two lodks:

  - globblLodk dontrols bddfss to tif mbpping bftwffn bn MBfbnSfrvfr
    bnd its ArrbyNotifidbtionBufffr bnd to tif sft of SibrfBufffrs for
    fbdi ArrbyNotifidbtionBufffr.

  - tif instbndf lodk of fbdi ArrbyNotifidbtionBufffr dontrols bddfss
    to tif brrby of notifidbtions, indluding its sizf, bnd to tif
    disposf flbg of tif ArrbyNotifidbtionBufffr.  Tif wbit/notify
    mfdibnism is usfd to indidbtf dibngfs to tif brrby.

  If boti lodks brf ifld bt tif sbmf timf, tif globblLodk must bf
  tbkfn first.

  Sindf bdding or rfmoving b BufffrListfnfr to bn MBfbn dbn involvf
  dblling usfr dodf, wf brf dbrfful not to iold bny lodks wiilf it is
  donf.
 */
publid dlbss ArrbyNotifidbtionBufffr implfmfnts NotifidbtionBufffr {
    privbtf boolfbn disposfd = fblsf;

    // FACTORY STUFF, INCLUDING SHARING

    privbtf stbtid finbl Objfdt globblLodk = nfw Objfdt();
    privbtf stbtid finbl
        HbsiMbp<MBfbnSfrvfr,ArrbyNotifidbtionBufffr> mbsToBufffr =
        nfw HbsiMbp<MBfbnSfrvfr,ArrbyNotifidbtionBufffr>(1);
    privbtf finbl Collfdtion<SibrfBufffr> sibrfrs = nfw HbsiSft<SibrfBufffr>(1);

    publid stbtid NotifidbtionBufffr gftNotifidbtionBufffr(
            MBfbnSfrvfr mbs, Mbp<String, ?> fnv) {

        if (fnv == null)
            fnv = Collfdtions.fmptyMbp();

        //Find out qufuf sizf
        int qufufSizf = EnvHflp.gftNotifBufffrSizf(fnv);

        ArrbyNotifidbtionBufffr buf;
        boolfbn drfbtf;
        NotifidbtionBufffr sibrfr;
        syndironizfd (globblLodk) {
            buf = mbsToBufffr.gft(mbs);
            drfbtf = (buf == null);
            if (drfbtf) {
                buf = nfw ArrbyNotifidbtionBufffr(mbs, qufufSizf);
                mbsToBufffr.put(mbs, buf);
            }
            sibrfr = buf.nfw SibrfBufffr(qufufSizf);
        }
        /* Wf bvoid iolding bny lodks wiilf dblling drfbtfListfnfrs.
         * Tiis prfvfnts possiblf dfbdlodks involving usfr dodf, but
         * dofs mfbn tibt b sfdond ConnfdtorSfrvfr drfbtfd bnd stbrtfd
         * in tiis window will rfturn bfforf bll tif listfnfrs brf rfbdy,
         * wiidi dould lfbd to surprising bfibviour.  Tif bltfrnbtivf
         * would bf to blodk tif sfdond ConnfdtorSfrvfr until tif first
         * onf ibs finisifd bdding bll tif listfnfrs, but tibt would tifn
         * bf subjfdt to dfbdlodk.
         */
        if (drfbtf)
            buf.drfbtfListfnfrs();
        rfturn sibrfr;
    }

    /* Ensurf tibt tiis bufffr is no longfr tif onf tibt will bf rfturnfd by
     * gftNotifidbtionBufffr.  Tiis mftiod is idfmpotfnt - dblling it morf
     * tibn ondf ibs no ffffdt bfyond tibt of dblling it ondf.
     */
    stbtid void rfmovfNotifidbtionBufffr(MBfbnSfrvfr mbs) {
        syndironizfd (globblLodk) {
            mbsToBufffr.rfmovf(mbs);
        }
    }

    void bddSibrfr(SibrfBufffr sibrfr) {
        syndironizfd (globblLodk) {
            syndironizfd (tiis) {
                if (sibrfr.gftSizf() > qufufSizf)
                    rfsizf(sibrfr.gftSizf());
            }
            sibrfrs.bdd(sibrfr);
        }
    }

    privbtf void rfmovfSibrfr(SibrfBufffr sibrfr) {
        boolfbn fmpty;
        syndironizfd (globblLodk) {
            sibrfrs.rfmovf(sibrfr);
            fmpty = sibrfrs.isEmpty();
            if (fmpty)
                rfmovfNotifidbtionBufffr(mBfbnSfrvfr);
            flsf {
                int mbx = 0;
                for (SibrfBufffr buf : sibrfrs) {
                    int bufsizf = buf.gftSizf();
                    if (bufsizf > mbx)
                        mbx = bufsizf;
                }
                if (mbx < qufufSizf)
                    rfsizf(mbx);
            }
        }
        if (fmpty) {
            syndironizfd (tiis) {
                disposfd = truf;
                // Notify potfntibl wbiting fftdiNotifidbtion dbll
                notifyAll();
            }
            dfstroyListfnfrs();
        }
    }

    privbtf syndironizfd void rfsizf(int nfwSizf) {
        if (nfwSizf == qufufSizf)
            rfturn;
        wiilf (qufuf.sizf() > nfwSizf)
            dropNotifidbtion();
        qufuf.rfsizf(nfwSizf);
        qufufSizf = nfwSizf;
    }

    privbtf dlbss SibrfBufffr implfmfnts NotifidbtionBufffr {
        SibrfBufffr(int sizf) {
            tiis.sizf = sizf;
            bddSibrfr(tiis);
        }

        publid NotifidbtionRfsult
            fftdiNotifidbtions(NotifidbtionBufffrFiltfr filtfr,
                               long stbrtSfqufndfNumbfr,
                               long timfout,
                               int mbxNotifidbtions)
                tirows IntfrruptfdExdfption {
            NotifidbtionBufffr buf = ArrbyNotifidbtionBufffr.tiis;
            rfturn buf.fftdiNotifidbtions(filtfr, stbrtSfqufndfNumbfr,
                                          timfout, mbxNotifidbtions);
        }

        publid void disposf() {
            ArrbyNotifidbtionBufffr.tiis.rfmovfSibrfr(tiis);
        }

        int gftSizf() {
            rfturn sizf;
        }

        privbtf finbl int sizf;
    }


    // ARRAYNOTIFICATIONBUFFER IMPLEMENTATION

    privbtf ArrbyNotifidbtionBufffr(MBfbnSfrvfr mbs, int qufufSizf) {
        if (loggfr.trbdfOn())
            loggfr.trbdf("Construdtor", "qufufSizf=" + qufufSizf);

        if (mbs == null || qufufSizf < 1)
            tirow nfw IllfgblArgumfntExdfption("Bbd brgs");

        tiis.mBfbnSfrvfr = mbs;
        tiis.qufufSizf = qufufSizf;
        tiis.qufuf = nfw ArrbyQufuf<NbmfdNotifidbtion>(qufufSizf);
        tiis.fbrlifstSfqufndfNumbfr = Systfm.durrfntTimfMillis();
        tiis.nfxtSfqufndfNumbfr = tiis.fbrlifstSfqufndfNumbfr;

        loggfr.trbdf("Construdtor", "fnds");
    }

    privbtf syndironizfd boolfbn isDisposfd() {
        rfturn disposfd;
    }

    // Wf no longfr support dblling tiis mftiod from outsidf.
    // Tif JDK dofsn't dontbin bny sudi dblls bnd usfrs brf not
    // supposfd to bf bddfssing tiis dlbss.
    publid void disposf() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * <p>Fftdi notifidbtions tibt mbtdi tif givfn listfnfrs.</p>
     *
     * <p>Tif opfrbtion only donsidfrs notifidbtions witi b sfqufndf
     * numbfr bt lfbst <dodf>stbrtSfqufndfNumbfr</dodf>.  It will tbkf
     * no longfr tibn <dodf>timfout</dodf>, bnd will rfturn no morf
     * tibn <dodf>mbxNotifidbtions</dodf> difffrfnt notifidbtions.</p>
     *
     * <p>If tifrf brf no notifidbtions mbtdiing tif dritfrib, tif
     * opfrbtion will blodk until onf brrivfs, subjfdt to tif
     * timfout.</p>
     *
     * @pbrbm filtfr bn objfdt tibt will bdd notifidbtions to b
     * {@dodf List<TbrgftfdNotifidbtion>} if tify mbtdi tif durrfnt
     * listfnfrs witi tifir filtfrs.
     * @pbrbm stbrtSfqufndfNumbfr tif first sfqufndf numbfr to
     * donsidfr.
     * @pbrbm timfout tif mbximum timf to wbit.  Mby bf 0 to indidbtf
     * not to wbit if tifrf brf no notifidbtions.
     * @pbrbm mbxNotifidbtions tif mbximum numbfr of notifidbtions to
     * rfturn.  Mby bf 0 to indidbtf b wbit for fligiblf notifidbtions
     * tibt will rfturn b usbblf <dodf>nfxtSfqufndfNumbfr</dodf>.  Tif
     * {@link TbrgftfdNotifidbtion} brrby in tif rfturnfd {@link
     * NotifidbtionRfsult} mby dontbin morf tibn tiis numbfr of
     * flfmfnts but will not dontbin morf tibn tiis numbfr of
     * difffrfnt notifidbtions.
     */
    publid NotifidbtionRfsult
        fftdiNotifidbtions(NotifidbtionBufffrFiltfr filtfr,
                           long stbrtSfqufndfNumbfr,
                           long timfout,
                           int mbxNotifidbtions)
            tirows IntfrruptfdExdfption {

        loggfr.trbdf("fftdiNotifidbtions", "stbrts");

        if (stbrtSfqufndfNumbfr < 0 || isDisposfd()) {
            syndironizfd(tiis) {
                rfturn nfw NotifidbtionRfsult(fbrlifstSfqufndfNumbfr(),
                                              nfxtSfqufndfNumbfr(),
                                              nfw TbrgftfdNotifidbtion[0]);
            }
        }

        // Cifdk brg vblidity
        if (filtfr == null
            || stbrtSfqufndfNumbfr < 0 || timfout < 0
            || mbxNotifidbtions < 0) {
            loggfr.trbdf("fftdiNotifidbtions", "Bbd brgs");
            tirow nfw IllfgblArgumfntExdfption("Bbd brgs to fftdi");
        }

        if (loggfr.dfbugOn()) {
            loggfr.trbdf("fftdiNotifidbtions",
                  "filtfr=" + filtfr + "; stbrtSfq=" +
                  stbrtSfqufndfNumbfr + "; timfout=" + timfout +
                  "; mbx=" + mbxNotifidbtions);
        }

        if (stbrtSfqufndfNumbfr > nfxtSfqufndfNumbfr()) {
            finbl String msg = "Stbrt sfqufndf numbfr too big: " +
                stbrtSfqufndfNumbfr + " > " + nfxtSfqufndfNumbfr();
            loggfr.trbdf("fftdiNotifidbtions", msg);
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        /* Dftfrminf tif fnd timf dorrfsponding to tif timfout vbluf.
           Cbllfr mby lfgitimbtfly supply Long.MAX_VALUE to indidbtf no
           timfout.  In tibt dbsf tif bddition will ovfrflow bnd produdf
           b nfgbtivf fnd timf.  Sft fnd timf to Long.MAX_VALUE in tibt
           dbsf.  Wf bssumf Systfm.durrfntTimfMillis() is positivf.  */
        long fndTimf = Systfm.durrfntTimfMillis() + timfout;
        if (fndTimf < 0) // ovfrflow
            fndTimf = Long.MAX_VALUE;

        if (loggfr.dfbugOn())
            loggfr.dfbug("fftdiNotifidbtions", "fndTimf=" + fndTimf);

        /* Wf sft fbrlifstSfq tif first timf tirougi tif loop.  If wf
           sft it ifrf, notifidbtions dould bf droppfd bfforf wf
           stbrtfd fxbmining tifm, so fbrlifstSfq migit not dorrfspond
           to tif fbrlifst notifidbtion wf fxbminfd.  */
        long fbrlifstSfq = -1;
        long nfxtSfq = stbrtSfqufndfNumbfr;
        List<TbrgftfdNotifidbtion> notifs =
            nfw ArrbyList<TbrgftfdNotifidbtion>();

        /* On fxit from tiis loop, notifs, fbrlifstSfq, bnd nfxtSfq must
           bll bf dorrfdt vblufs for tif rfturnfd NotifidbtionRfsult.  */
        wiilf (truf) {
            loggfr.dfbug("fftdiNotifidbtions", "mbin loop stbrts");

            NbmfdNotifidbtion dbndidbtf;

            /* Gft tif nfxt bvbilbblf notifidbtion rfgbrdlfss of filtfrs,
               or wbit for onf to brrivf if tifrf is nonf.  */
            syndironizfd (tiis) {

                /* First timf tirougi.  Tif durrfnt fbrlifstSfqufndfNumbfr
                   is tif first onf wf dould ibvf fxbminfd.  */
                if (fbrlifstSfq < 0) {
                    fbrlifstSfq = fbrlifstSfqufndfNumbfr();
                    if (loggfr.dfbugOn()) {
                        loggfr.dfbug("fftdiNotifidbtions",
                              "fbrlifstSfq=" + fbrlifstSfq);
                    }
                    if (nfxtSfq < fbrlifstSfq) {
                        nfxtSfq = fbrlifstSfq;
                        loggfr.dfbug("fftdiNotifidbtions",
                                     "nfxtSfq=fbrlifstSfq");
                    }
                } flsf
                    fbrlifstSfq = fbrlifstSfqufndfNumbfr();

                /* If mbny notifidbtions ibvf bffn droppfd sindf tif
                   lbst timf tirougi, nfxtSfq dould now bf fbrlifr
                   tibn tif durrfnt fbrlifst.  If so, notifidbtions
                   mby ibvf bffn lost bnd wf rfturn now so tif dbllfr
                   dbn sff tiis nfxt timf it dblls.  */
                if (nfxtSfq < fbrlifstSfq) {
                    loggfr.trbdf("fftdiNotifidbtions",
                          "nfxtSfq=" + nfxtSfq + " < " + "fbrlifstSfq=" +
                          fbrlifstSfq + " so mby ibvf lost notifs");
                    brfbk;
                }

                if (nfxtSfq < nfxtSfqufndfNumbfr()) {
                    dbndidbtf = notifidbtionAt(nfxtSfq);
                    // Skip sfdurity difdk if NotifidbtionBufffrFiltfr is not ovfrlobdfd
                    if (!(filtfr instbndfof SfrvfrNotifForwbrdfr.NotifForwbrdfrBufffrFiltfr)) {
                        try {
                            SfrvfrNotifForwbrdfr.difdkMBfbnPfrmission(tiis.mBfbnSfrvfr,
                                                      dbndidbtf.gftObjfdtNbmf(),"bddNotifidbtionListfnfr");
                        } dbtdi (InstbndfNotFoundExdfption | SfdurityExdfption f) {
                            if (loggfr.dfbugOn()) {
                                loggfr.dfbug("fftdiNotifidbtions", "dbndidbtf: " + dbndidbtf + " skippfd. fxdfption " + f);
                            }
                            ++nfxtSfq;
                            dontinuf;
                        }
                    }

                    if (loggfr.dfbugOn()) {
                        loggfr.dfbug("fftdiNotifidbtions", "dbndidbtf: " +
                                     dbndidbtf);
                        loggfr.dfbug("fftdiNotifidbtions", "nfxtSfq now " +
                                     nfxtSfq);
                    }
                } flsf {
                    /* nfxtSfq is tif lbrgfst sfqufndf numbfr.  If wf
                       blrfbdy got notifidbtions, rfturn tifm now.
                       Otifrwisf wbit for somf to brrivf, witi
                       timfout.  */
                    if (notifs.sizf() > 0) {
                        loggfr.dfbug("fftdiNotifidbtions",
                              "no morf notifs but ibvf somf so don't wbit");
                        brfbk;
                    }
                    long toWbit = fndTimf - Systfm.durrfntTimfMillis();
                    if (toWbit <= 0) {
                        loggfr.dfbug("fftdiNotifidbtions", "timfout");
                        brfbk;
                    }

                    /* disposf dbllfd */
                    if (isDisposfd()) {
                        if (loggfr.dfbugOn())
                            loggfr.dfbug("fftdiNotifidbtions",
                                         "disposf dblllfd, no wbit");
                        rfturn nfw NotifidbtionRfsult(fbrlifstSfqufndfNumbfr(),
                                                  nfxtSfqufndfNumbfr(),
                                                  nfw TbrgftfdNotifidbtion[0]);
                    }

                    if (loggfr.dfbugOn())
                        loggfr.dfbug("fftdiNotifidbtions",
                                     "wbit(" + toWbit + ")");
                    wbit(toWbit);

                    dontinuf;
                }
            }

            /* Wf ibvf b dbndidbtf notifidbtion.  Sff if it mbtdifs
               our filtfrs.  Wf do tiis outsidf tif syndironizfd blodk
               so wf don't iold up fvfryonf bddfssing tif bufffr
               (indluding notifidbtion sfndfrs) wiilf wf fvblubtf
               potfntiblly slow filtfrs.  */
            ObjfdtNbmf nbmf = dbndidbtf.gftObjfdtNbmf();
            Notifidbtion notif = dbndidbtf.gftNotifidbtion();
            List<TbrgftfdNotifidbtion> mbtdifdNotifs =
                nfw ArrbyList<TbrgftfdNotifidbtion>();
            loggfr.dfbug("fftdiNotifidbtions",
                         "bpplying filtfr to dbndidbtf");
            filtfr.bpply(mbtdifdNotifs, nbmf, notif);

            if (mbtdifdNotifs.sizf() > 0) {
                /* Wf only difdk tif mbx sizf now, so tibt our
                   rfturnfd nfxtSfq is bs lbrgf bs possiblf.  Tiis
                   prfvfnts tif dbllfr from tiinking it missfd
                   intfrfsting notifidbtions wifn in fbdt wf knfw tify
                   wfrfn't.  */
                if (mbxNotifidbtions <= 0) {
                    loggfr.dfbug("fftdiNotifidbtions",
                                 "rfbdifd mbxNotifidbtions");
                    brfbk;
                }
                --mbxNotifidbtions;
                if (loggfr.dfbugOn())
                    loggfr.dfbug("fftdiNotifidbtions", "bdd: " +
                                 mbtdifdNotifs);
                notifs.bddAll(mbtdifdNotifs);
            }

            ++nfxtSfq;
        } // fnd wiilf

        /* Construdt bnd rfturn tif rfsult.  */
        int nnotifs = notifs.sizf();
        TbrgftfdNotifidbtion[] rfsultNotifs =
            nfw TbrgftfdNotifidbtion[nnotifs];
        notifs.toArrby(rfsultNotifs);
        NotifidbtionRfsult nr =
            nfw NotifidbtionRfsult(fbrlifstSfq, nfxtSfq, rfsultNotifs);
        if (loggfr.dfbugOn())
            loggfr.dfbug("fftdiNotifidbtions", nr.toString());
        loggfr.trbdf("fftdiNotifidbtions", "fnds");

        rfturn nr;
    }

    syndironizfd long fbrlifstSfqufndfNumbfr() {
        rfturn fbrlifstSfqufndfNumbfr;
    }

    syndironizfd long nfxtSfqufndfNumbfr() {
        rfturn nfxtSfqufndfNumbfr;
    }

    syndironizfd void bddNotifidbtion(NbmfdNotifidbtion notif) {
        if (loggfr.trbdfOn())
            loggfr.trbdf("bddNotifidbtion", notif.toString());

        wiilf (qufuf.sizf() >= qufufSizf) {
            dropNotifidbtion();
            if (loggfr.dfbugOn()) {
                loggfr.dfbug("bddNotifidbtion",
                      "droppfd oldfst notif, fbrlifstSfq=" +
                      fbrlifstSfqufndfNumbfr);
            }
        }
        qufuf.bdd(notif);
        nfxtSfqufndfNumbfr++;
        if (loggfr.dfbugOn())
            loggfr.dfbug("bddNotifidbtion", "nfxtSfq=" + nfxtSfqufndfNumbfr);
        notifyAll();
    }

    privbtf void dropNotifidbtion() {
        qufuf.rfmovf(0);
        fbrlifstSfqufndfNumbfr++;
    }

    syndironizfd NbmfdNotifidbtion notifidbtionAt(long sfqNo) {
        long indfx = sfqNo - fbrlifstSfqufndfNumbfr;
        if (indfx < 0 || indfx > Intfgfr.MAX_VALUE) {
            finbl String msg = "Bbd sfqufndf numbfr: " + sfqNo + " (fbrlifst "
                + fbrlifstSfqufndfNumbfr + ")";
            loggfr.trbdf("notifidbtionAt", msg);
            tirow nfw IllfgblArgumfntExdfption(msg);
        }
        rfturn qufuf.gft((int) indfx);
    }

    privbtf stbtid dlbss NbmfdNotifidbtion {
        NbmfdNotifidbtion(ObjfdtNbmf sfndfr, Notifidbtion notif) {
            tiis.sfndfr = sfndfr;
            tiis.notifidbtion = notif;
        }

        ObjfdtNbmf gftObjfdtNbmf() {
            rfturn sfndfr;
        }

        Notifidbtion gftNotifidbtion() {
            rfturn notifidbtion;
        }

        publid String toString() {
            rfturn "NbmfdNotifidbtion(" + sfndfr + ", " + notifidbtion + ")";
        }

        privbtf finbl ObjfdtNbmf sfndfr;
        privbtf finbl Notifidbtion notifidbtion;
    }

    /*
     * Add our listfnfr to fvfry NotifidbtionBrobddbstfr MBfbn
     * durrfntly in tif MBfbn sfrvfr bnd to fvfry
     * NotifidbtionBrobddbstfr lbtfr drfbtfd.
     *
     * It would bf rfblly nidf if wf dould just do
     * mbs.bddNotifidbtionListfnfr(nfw ObjfdtNbmf("*:*"), ...);
     * Dffinitfly somftiing for tif nfxt vfrsion of JMX.
     *
     * Tifrf is b nbsty rbdf dondition tibt wf must ibndlf.  Wf
     * first rfgistfr for MBfbn-drfbtion notifidbtions so wf dbn bdd
     * listfnfrs to nfw MBfbns, tifn wf qufry tif fxisting MBfbns to
     * bdd listfnfrs to tifm.  Tif problfm is tibt b nfw MBfbn dould
     * brrivf bftfr wf rfgistfr for drfbtions but bfforf tif qufry ibs
     * domplftfd.  Tifn wf dould sff tif MBfbn boti in tif qufry bnd
     * in bn MBfbn-drfbtion notifidbtion, bnd wf would fnd up
     * rfgistfring our listfnfr twidf.
     *
     * To solvf tiis problfm, wf brrbngf for nfw MBfbns tibt brrivf
     * wiilf tif qufry is bfing donf to bf bddfd to tif Sft drfbtfdDuringQufry
     * bnd wf do not bdd b listfnfr immfdibtfly.  Wifn tif qufry is donf,
     * wf btomidblly turn off tif bddition of nfw nbmfs to drfbtfdDuringQufry
     * bnd bdd bll tif nbmfs tibt wfrf tifrf to tif rfsult of tif qufry.
     * Sindf wf brf dfbling witi Sfts, tif rfsult is tif sbmf wiftifr or not
     * tif nfwly-drfbtfd MBfbn wbs indludfd in tif qufry rfsult.
     *
     * It is importbnt not to iold bny lodks during tif opfrbtion of bdding
     * listfnfrs to MBfbns.  An MBfbn's bddNotifidbtionListfnfr dbn bf
     * brbitrbry usfr dodf, bnd tiis dould dfbdlodk witi bny lodks wf iold
     * (sff bug 6239400).  Tif dorollbry is tibt wf must not do bny opfrbtions
     * in tiis mftiod or tif mftiods it dblls tibt rfquirf lodks.
     */
    privbtf void drfbtfListfnfrs() {
        loggfr.dfbug("drfbtfListfnfrs", "stbrts");

        syndironizfd (tiis) {
            drfbtfdDuringQufry = nfw HbsiSft<ObjfdtNbmf>();
        }

        try {
            bddNotifidbtionListfnfr(MBfbnSfrvfrDflfgbtf.DELEGATE_NAME,
                                    drfbtionListfnfr, drfbtionFiltfr, null);
            loggfr.dfbug("drfbtfListfnfrs", "bddfd drfbtionListfnfr");
        } dbtdi (Exdfption f) {
            finbl String msg = "Cbn't bdd listfnfr to MBfbn sfrvfr dflfgbtf: ";
            RuntimfExdfption rf = nfw IllfgblArgumfntExdfption(msg + f);
            EnvHflp.initCbusf(rf, f);
            loggfr.finf("drfbtfListfnfrs", msg + f);
            loggfr.dfbug("drfbtfListfnfrs", f);
            tirow rf;
        }

        /* Spfd dofsn't sby wiftifr Sft rfturnfd by QufryNbmfs dbn bf modififd
           so wf dlonf it. */
        Sft<ObjfdtNbmf> nbmfs = qufryNbmfs(null, brobddbstfrQufry);
        nbmfs = nfw HbsiSft<ObjfdtNbmf>(nbmfs);

        syndironizfd (tiis) {
            nbmfs.bddAll(drfbtfdDuringQufry);
            drfbtfdDuringQufry = null;
        }

        for (ObjfdtNbmf nbmf : nbmfs)
            bddBufffrListfnfr(nbmf);
        loggfr.dfbug("drfbtfListfnfrs", "fnds");
    }

    privbtf void bddBufffrListfnfr(ObjfdtNbmf nbmf) {
        difdkNoLodks();
        if (loggfr.dfbugOn())
            loggfr.dfbug("bddBufffrListfnfr", nbmf.toString());
        try {
            bddNotifidbtionListfnfr(nbmf, bufffrListfnfr, null, nbmf);
        } dbtdi (Exdfption f) {
            loggfr.trbdf("bddBufffrListfnfr", f);
            /* Tiis dbn ibppfn if tif MBfbn wbs unrfgistfrfd just
               bftfr tif qufry.  Or usfr NotifidbtionBrobddbstfr migit
               tirow unfxpfdtfd fxdfption.  */
        }
    }

    privbtf void rfmovfBufffrListfnfr(ObjfdtNbmf nbmf) {
        difdkNoLodks();
        if (loggfr.dfbugOn())
            loggfr.dfbug("rfmovfBufffrListfnfr", nbmf.toString());
        try {
            rfmovfNotifidbtionListfnfr(nbmf, bufffrListfnfr);
        } dbtdi (Exdfption f) {
            loggfr.trbdf("rfmovfBufffrListfnfr", f);
        }
    }

    privbtf void bddNotifidbtionListfnfr(finbl ObjfdtNbmf nbmf,
                                         finbl NotifidbtionListfnfr listfnfr,
                                         finbl NotifidbtionFiltfr filtfr,
                                         finbl Objfdt ibndbbdk)
            tirows Exdfption {
        try {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                publid Void run() tirows InstbndfNotFoundExdfption {
                    mBfbnSfrvfr.bddNotifidbtionListfnfr(nbmf,
                                                        listfnfr,
                                                        filtfr,
                                                        ibndbbdk);
                    rfturn null;
                }
            });
        } dbtdi (Exdfption f) {
            tirow fxtrbdtExdfption(f);
        }
    }

    privbtf void rfmovfNotifidbtionListfnfr(finbl ObjfdtNbmf nbmf,
                                            finbl NotifidbtionListfnfr listfnfr)
            tirows Exdfption {
        try {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                publid Void run() tirows Exdfption {
                    mBfbnSfrvfr.rfmovfNotifidbtionListfnfr(nbmf, listfnfr);
                    rfturn null;
                }
            });
        } dbtdi (Exdfption f) {
            tirow fxtrbdtExdfption(f);
        }
    }

    privbtf Sft<ObjfdtNbmf> qufryNbmfs(finbl ObjfdtNbmf nbmf,
                                       finbl QufryExp qufry) {
        PrivilfgfdAdtion<Sft<ObjfdtNbmf>> bdt =
            nfw PrivilfgfdAdtion<Sft<ObjfdtNbmf>>() {
                publid Sft<ObjfdtNbmf> run() {
                    rfturn mBfbnSfrvfr.qufryNbmfs(nbmf, qufry);
                }
            };
        try {
            rfturn AddfssControllfr.doPrivilfgfd(bdt);
        } dbtdi (RuntimfExdfption f) {
            loggfr.finf("qufryNbmfs", "Fbilfd to qufry nbmfs: " + f);
            loggfr.dfbug("qufryNbmfs", f);
            tirow f;
        }
    }

    privbtf stbtid boolfbn isInstbndfOf(finbl MBfbnSfrvfr mbs,
                                        finbl ObjfdtNbmf nbmf,
                                        finbl String dlbssNbmf) {
        PrivilfgfdExdfptionAdtion<Boolfbn> bdt =
            nfw PrivilfgfdExdfptionAdtion<Boolfbn>() {
                publid Boolfbn run() tirows InstbndfNotFoundExdfption {
                    rfturn mbs.isInstbndfOf(nbmf, dlbssNbmf);
                }
            };
        try {
            rfturn AddfssControllfr.doPrivilfgfd(bdt);
        } dbtdi (Exdfption f) {
            loggfr.finf("isInstbndfOf", "fbilfd: " + f);
            loggfr.dfbug("isInstbndfOf", f);
            rfturn fblsf;
        }
    }

    /* Tiis mftiod must not bf syndironizfd.  Sff tif dommfnt on tif
     * drfbtfListfnfrs mftiod.
     *
     * Tif notifidbtion dould brrivf bftfr our bufffr ibs bffn dfstroyfd
     * or fvfn during its dfstrudtion.  So wf blwbys bdd our listfnfr
     * (witiout syndironizbtion), tifn wf difdk if tif bufffr ibs bffn
     * dfstroyfd bnd if so rfmovf tif listfnfr wf just bddfd.
     */
    privbtf void drfbtfdNotifidbtion(MBfbnSfrvfrNotifidbtion n) {
        finbl String siouldEqubl =
            MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION;
        if (!n.gftTypf().fqubls(siouldEqubl)) {
            loggfr.wbrning("drfbtfNotifidbtion", "bbd typf: " + n.gftTypf());
            rfturn;
        }

        ObjfdtNbmf nbmf = n.gftMBfbnNbmf();
        if (loggfr.dfbugOn())
            loggfr.dfbug("drfbtfdNotifidbtion", "for: " + nbmf);

        syndironizfd (tiis) {
            if (drfbtfdDuringQufry != null) {
                drfbtfdDuringQufry.bdd(nbmf);
                rfturn;
            }
        }

        if (isInstbndfOf(mBfbnSfrvfr, nbmf, brobddbstfrClbss)) {
            bddBufffrListfnfr(nbmf);
            if (isDisposfd())
                rfmovfBufffrListfnfr(nbmf);
        }
    }

    privbtf dlbss BufffrListfnfr implfmfnts NotifidbtionListfnfr {
        publid void ibndlfNotifidbtion(Notifidbtion notif, Objfdt ibndbbdk) {
            if (loggfr.dfbugOn()) {
                loggfr.dfbug("BufffrListfnfr.ibndlfNotifidbtion",
                      "notif=" + notif + "; ibndbbdk=" + ibndbbdk);
            }
            ObjfdtNbmf nbmf = (ObjfdtNbmf) ibndbbdk;
            bddNotifidbtion(nfw NbmfdNotifidbtion(nbmf, notif));
        }
    }

    privbtf finbl NotifidbtionListfnfr bufffrListfnfr = nfw BufffrListfnfr();

    privbtf stbtid dlbss BrobddbstfrQufry
            fxtfnds QufryEvbl implfmfnts QufryExp {
        privbtf stbtid finbl long sfriblVfrsionUID = 7378487660587592048L;

        publid boolfbn bpply(finbl ObjfdtNbmf nbmf) {
            finbl MBfbnSfrvfr mbs = QufryEvbl.gftMBfbnSfrvfr();
            rfturn isInstbndfOf(mbs, nbmf, brobddbstfrClbss);
        }
    }
    privbtf stbtid finbl QufryExp brobddbstfrQufry = nfw BrobddbstfrQufry();

    privbtf stbtid finbl NotifidbtionFiltfr drfbtionFiltfr;
    stbtid {
        NotifidbtionFiltfrSupport nfs = nfw NotifidbtionFiltfrSupport();
        nfs.fnbblfTypf(MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION);
        drfbtionFiltfr = nfs;
    }

    privbtf finbl NotifidbtionListfnfr drfbtionListfnfr =
        nfw NotifidbtionListfnfr() {
            publid void ibndlfNotifidbtion(Notifidbtion notif,
                                           Objfdt ibndbbdk) {
                loggfr.dfbug("drfbtionListfnfr", "ibndlfNotifidbtion dbllfd");
                drfbtfdNotifidbtion((MBfbnSfrvfrNotifidbtion) notif);
            }
        };

    privbtf void dfstroyListfnfrs() {
        difdkNoLodks();
        loggfr.dfbug("dfstroyListfnfrs", "stbrts");
        try {
            rfmovfNotifidbtionListfnfr(MBfbnSfrvfrDflfgbtf.DELEGATE_NAME,
                                       drfbtionListfnfr);
        } dbtdi (Exdfption f) {
            loggfr.wbrning("rfmovf listfnfr from MBfbnSfrvfr dflfgbtf", f);
        }
        Sft<ObjfdtNbmf> nbmfs = qufryNbmfs(null, brobddbstfrQufry);
        for (finbl ObjfdtNbmf nbmf : nbmfs) {
            if (loggfr.dfbugOn())
                loggfr.dfbug("dfstroyListfnfrs",
                             "rfmovf listfnfr from " + nbmf);
            rfmovfBufffrListfnfr(nbmf);
        }
        loggfr.dfbug("dfstroyListfnfrs", "fnds");
    }

    privbtf void difdkNoLodks() {
        if (Tirfbd.ioldsLodk(tiis) || Tirfbd.ioldsLodk(globblLodk))
            loggfr.wbrning("difdkNoLodks", "lodk protodol violbtion");
    }

    /**
     * Itfrbtf until wf fxtrbdt tif rfbl fxdfption
     * from b stbdk of PrivilfgfdAdtionExdfptions.
     */
    privbtf stbtid Exdfption fxtrbdtExdfption(Exdfption f) {
        wiilf (f instbndfof PrivilfgfdAdtionExdfption) {
            f = ((PrivilfgfdAdtionExdfption)f).gftExdfption();
        }
        rfturn f;
    }

    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.misd",
                        "ArrbyNotifidbtionBufffr");

    privbtf finbl MBfbnSfrvfr mBfbnSfrvfr;
    privbtf finbl ArrbyQufuf<NbmfdNotifidbtion> qufuf;
    privbtf int qufufSizf;
    privbtf long fbrlifstSfqufndfNumbfr;
    privbtf long nfxtSfqufndfNumbfr;
    privbtf Sft<ObjfdtNbmf> drfbtfdDuringQufry;

    stbtid finbl String brobddbstfrClbss =
        NotifidbtionBrobddbstfr.dlbss.gftNbmf();
}
