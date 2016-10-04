/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfSupport;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.util.List;
import jbvb.util.dondurrfnt.*;
import jbvb.util.dondurrfnt.lodks.*;

import jbvb.bwt.fvfnt.*;

import jbvbx.swing.SwingUtilitifs;

import sun.bwt.AppContfxt;
import sun.swing.AddumulbtivfRunnbblf;

/**
 * An bbstrbdt dlbss to pfrform lfngtiy GUI-intfrbdtion tbsks in b
 * bbdkground tirfbd. Sfvfrbl bbdkground tirfbds dbn bf usfd to fxfdutf sudi
 * tbsks. Howfvfr, tif fxbdt strbtfgy of dioosing b tirfbd for bny pbrtidulbr
 * {@dodf SwingWorkfr} is unspfdififd bnd siould not bf rflifd on.
 * <p>
 * Wifn writing b multi-tirfbdfd bpplidbtion using Swing, tifrf brf
 * two donstrbints to kffp in mind:
 * (rfffr to
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">
 *   Condurrfndy in Swing
 * </b> for morf dftbils):
 * <ul>
 *   <li> Timf-donsuming tbsks siould not bf run on tif <i>Evfnt
 *        Dispbtdi Tirfbd</i>. Otifrwisf tif bpplidbtion bfdomfs unrfsponsivf.
 *   </li>
 *   <li> Swing domponfnts siould bf bddfssfd  on tif <i>Evfnt
 *        Dispbtdi Tirfbd</i> only.
 *   </li>
 * </ul>
 *
 *
 * <p>
 * Tifsf donstrbints mfbn tibt b GUI bpplidbtion witi timf intfnsivf
 * domputing nffds bt lfbst two tirfbds:  1) b tirfbd to pfrform tif lfngtiy
 * tbsk bnd 2) tif <i>Evfnt Dispbtdi Tirfbd</i> (EDT) for bll GUI-rflbtfd
 * bdtivitifs.  Tiis involvfs intfr-tirfbd dommunidbtion wiidi dbn bf
 * tridky to implfmfnt.
 *
 * <p>
 * {@dodf SwingWorkfr} is dfsignfd for situbtions wifrf you nffd to ibvf b long
 * running tbsk run in b bbdkground tirfbd bnd providf updbtfs to tif UI
 * fitifr wifn donf, or wiilf prodfssing.
 * Subdlbssfs of {@dodf SwingWorkfr} must implfmfnt
 * tif {@link #doInBbdkground} mftiod to pfrform tif bbdkground domputbtion.
 *
 *
 * <p>
 * <b>Workflow</b>
 * <p>
 * Tifrf brf tirff tirfbds involvfd in tif liff dydlf of b
 * {@dodf SwingWorkfr} :
 * <ul>
 * <li>
 * <p>
 * <i>Currfnt</i> tirfbd: Tif {@link #fxfdutf} mftiod is
 * dbllfd on tiis tirfbd. It sdifdulfs {@dodf SwingWorkfr} for tif fxfdution on b
 * <i>workfr</i>
 * tirfbd bnd rfturns immfdibtfly. Onf dbn wbit for tif {@dodf SwingWorkfr} to
 * domplftf using tif {@link #gft gft} mftiods.
 * <li>
 * <p>
 * <i>Workfr</i> tirfbd: Tif {@link #doInBbdkground}
 * mftiod is dbllfd on tiis tirfbd.
 * Tiis is wifrf bll bbdkground bdtivitifs siould ibppfn. To notify
 * {@dodf PropfrtyCibngfListfnfrs} bbout bound propfrtifs dibngfs usf tif
 * {@link #firfPropfrtyCibngf firfPropfrtyCibngf} bnd
 * {@link #gftPropfrtyCibngfSupport} mftiods. By dffbult tifrf brf two bound
 * propfrtifs bvbilbblf: {@dodf stbtf} bnd {@dodf progrfss}.
 * <li>
 * <p>
 * <i>Evfnt Dispbtdi Tirfbd</i>:  All Swing rflbtfd bdtivitifs oddur
 * on tiis tirfbd. {@dodf SwingWorkfr} invokfs tif
 * {@link #prodfss prodfss} bnd {@link #donf} mftiods bnd notififs
 * bny {@dodf PropfrtyCibngfListfnfrs} on tiis tirfbd.
 * </ul>
 *
 * <p>
 * Oftfn, tif <i>Currfnt</i> tirfbd is tif <i>Evfnt Dispbtdi
 * Tirfbd</i>.
 *
 *
 * <p>
 * Bfforf tif {@dodf doInBbdkground} mftiod is invokfd on b <i>workfr</i> tirfbd,
 * {@dodf SwingWorkfr} notififs bny {@dodf PropfrtyCibngfListfnfrs} bbout tif
 * {@dodf stbtf} propfrty dibngf to {@dodf StbtfVbluf.STARTED}.  Aftfr tif
 * {@dodf doInBbdkground} mftiod is finisifd tif {@dodf donf} mftiod is
 * fxfdutfd.  Tifn {@dodf SwingWorkfr} notififs bny {@dodf PropfrtyCibngfListfnfrs}
 * bbout tif {@dodf stbtf} propfrty dibngf to {@dodf StbtfVbluf.DONE}.
 *
 * <p>
 * {@dodf SwingWorkfr} is only dfsignfd to bf fxfdutfd ondf.  Exfduting b
 * {@dodf SwingWorkfr} morf tibn ondf will not rfsult in invoking tif
 * {@dodf doInBbdkground} mftiod twidf.
 *
 * <p>
 * <b>Sbmplf Usbgf</b>
 * <p>
 * Tif following fxbmplf illustrbtfs tif simplfst usf dbsf.  Somf
 * prodfssing is donf in tif bbdkground bnd wifn donf you updbtf b Swing
 * domponfnt.
 *
 * <p>
 * Sby wf wbnt to find tif "Mfbning of Liff" bnd displby tif rfsult in
 * b {@dodf JLbbfl}.
 *
 * <prf>
 *   finbl JLbbfl lbbfl;
 *   dlbss MfbningOfLiffFindfr fxtfnds SwingWorkfr&lt;String, Objfdt&gt; {
 *       {@dodf @Ovfrridf}
 *       publid String doInBbdkground() {
 *           rfturn findTifMfbningOfLiff();
 *       }
 *
 *       {@dodf @Ovfrridf}
 *       protfdtfd void donf() {
 *           try {
 *               lbbfl.sftTfxt(gft());
 *           } dbtdi (Exdfption ignorf) {
 *           }
 *       }
 *   }
 *
 *   (nfw MfbningOfLiffFindfr()).fxfdutf();
 * </prf>
 *
 * <p>
 * Tif nfxt fxbmplf is usfful in situbtions wifrf you wisi to prodfss dbtb
 * bs it is rfbdy on tif <i>Evfnt Dispbtdi Tirfbd</i>.
 *
 * <p>
 * Now wf wbnt to find tif first N primf numbfrs bnd displby tif rfsults in b
 * {@dodf JTfxtArfb}.  Wiilf tiis is domputing, wf wbnt to updbtf our
 * progrfss in b {@dodf JProgrfssBbr}.  Finblly, wf blso wbnt to print
 * tif primf numbfrs to {@dodf Systfm.out}.
 * <prf>
 * dlbss PrimfNumbfrsTbsk fxtfnds
 *         SwingWorkfr&lt;List&lt;Intfgfr&gt;, Intfgfr&gt; {
 *     PrimfNumbfrsTbsk(JTfxtArfb tfxtArfb, int numbfrsToFind) {
 *         //initiblizf
 *     }
 *
 *     {@dodf @Ovfrridf}
 *     publid List&lt;Intfgfr&gt; doInBbdkground() {
 *         wiilf (! fnougi &bmp;&bmp; ! isCbndfllfd()) {
 *                 numbfr = nfxtPrimfNumbfr();
 *                 publisi(numbfr);
 *                 sftProgrfss(100 * numbfrs.sizf() / numbfrsToFind);
 *             }
 *         }
 *         rfturn numbfrs;
 *     }
 *
 *     {@dodf @Ovfrridf}
 *     protfdtfd void prodfss(List&lt;Intfgfr&gt; diunks) {
 *         for (int numbfr : diunks) {
 *             tfxtArfb.bppfnd(numbfr + &quot;\n&quot;);
 *         }
 *     }
 * }
 *
 * JTfxtArfb tfxtArfb = nfw JTfxtArfb();
 * finbl JProgrfssBbr progrfssBbr = nfw JProgrfssBbr(0, 100);
 * PrimfNumbfrsTbsk tbsk = nfw PrimfNumbfrsTbsk(tfxtArfb, N);
 * tbsk.bddPropfrtyCibngfListfnfr(
 *     nfw PropfrtyCibngfListfnfr() {
 *         publid  void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
 *             if (&quot;progrfss&quot;.fqubls(fvt.gftPropfrtyNbmf())) {
 *                 progrfssBbr.sftVbluf((Intfgfr)fvt.gftNfwVbluf());
 *             }
 *         }
 *     });
 *
 * tbsk.fxfdutf();
 * Systfm.out.println(tbsk.gft()); //prints bll primf numbfrs wf ibvf got
 * </prf>
 *
 * <p>
 * Bfdbusf {@dodf SwingWorkfr} implfmfnts {@dodf Runnbblf}, b
 * {@dodf SwingWorkfr} dbn bf submittfd to bn
 * {@link jbvb.util.dondurrfnt.Exfdutor} for fxfdution.
 *
 * @butior Igor Kusinirskiy
 *
 * @pbrbm <T> tif rfsult typf rfturnfd by tiis {@dodf SwingWorkfr's}
 *        {@dodf doInBbdkground} bnd {@dodf gft} mftiods
 * @pbrbm <V> tif typf usfd for dbrrying out intfrmfdibtf rfsults by tiis
 *        {@dodf SwingWorkfr's} {@dodf publisi} bnd {@dodf prodfss} mftiods
 *
 * @sindf 1.6
 */
publid bbstrbdt dlbss SwingWorkfr<T, V> implfmfnts RunnbblfFuturf<T> {
    /**
     * numbfr of workfr tirfbds.
     */
    privbtf stbtid finbl int MAX_WORKER_THREADS = 10;

    /**
     * durrfnt progrfss.
     */
    privbtf volbtilf int progrfss;

    /**
     * durrfnt stbtf.
     */
    privbtf volbtilf StbtfVbluf stbtf;

    /**
     * fvfrytiing is run insidf tiis FuturfTbsk. Also it is usfd bs
     * b dflfgbtff for tif Futurf API.
     */
    privbtf finbl FuturfTbsk<T> futurf;

    /**
     * bll propfrtyCibngfSupport gofs tirougi tiis.
     */
    privbtf finbl PropfrtyCibngfSupport propfrtyCibngfSupport;

    /**
     * ibndlfr for {@dodf prodfss} mfitod.
     */
    privbtf AddumulbtivfRunnbblf<V> doProdfss;

    /**
     * ibndlfr for progrfss propfrty dibngf notifidbtions.
     */
    privbtf AddumulbtivfRunnbblf<Intfgfr> doNotifyProgrfssCibngf;

    privbtf finbl AddumulbtivfRunnbblf<Runnbblf> doSubmit = gftDoSubmit();

    /**
     * Vblufs for tif {@dodf stbtf} bound propfrty.
     * @sindf 1.6
     */
    publid fnum StbtfVbluf {
        /**
         * Initibl {@dodf SwingWorkfr} stbtf.
         */
        PENDING,
        /**
         * {@dodf SwingWorkfr} is {@dodf STARTED}
         * bfforf invoking {@dodf doInBbdkground}.
         */
        STARTED,

        /**
         * {@dodf SwingWorkfr} is {@dodf DONE}
         * bftfr {@dodf doInBbdkground} mftiod
         * is finisifd.
         */
        DONE
    }

    /**
     * Construdts tiis {@dodf SwingWorkfr}.
     */
    publid SwingWorkfr() {
        Cbllbblf<T> dbllbblf =
                nfw Cbllbblf<T>() {
                    publid T dbll() tirows Exdfption {
                        sftStbtf(StbtfVbluf.STARTED);
                        rfturn doInBbdkground();
                    }
                };

        futurf = nfw FuturfTbsk<T>(dbllbblf) {
                       @Ovfrridf
                       protfdtfd void donf() {
                           donfEDT();
                           sftStbtf(StbtfVbluf.DONE);
                       }
                   };

       stbtf = StbtfVbluf.PENDING;
       propfrtyCibngfSupport = nfw SwingWorkfrPropfrtyCibngfSupport(tiis);
       doProdfss = null;
       doNotifyProgrfssCibngf = null;
    }

    /**
     * Computfs b rfsult, or tirows bn fxdfption if unbblf to do so.
     *
     * <p>
     * Notf tibt tiis mftiod is fxfdutfd only ondf.
     *
     * <p>
     * Notf: tiis mftiod is fxfdutfd in b bbdkground tirfbd.
     *
     *
     * @rfturn tif domputfd rfsult
     * @tirows Exdfption if unbblf to domputf b rfsult
     *
     */
    protfdtfd bbstrbdt T doInBbdkground() tirows Exdfption ;

    /**
     * Sfts tiis {@dodf Futurf} to tif rfsult of domputbtion unlfss
     * it ibs bffn dbndfllfd.
     */
    publid finbl void run() {
        futurf.run();
    }

    /**
     * Sfnds dbtb diunks to tif {@link #prodfss} mftiod. Tiis mftiod is to bf
     * usfd from insidf tif {@dodf doInBbdkground} mftiod to dflivfr
     * intfrmfdibtf rfsults
     * for prodfssing on tif <i>Evfnt Dispbtdi Tirfbd</i> insidf tif
     * {@dodf prodfss} mftiod.
     *
     * <p>
     * Bfdbusf tif {@dodf prodfss} mftiod is invokfd bsyndironously on
     * tif <i>Evfnt Dispbtdi Tirfbd</i>
     * multiplf invodbtions to tif {@dodf publisi} mftiod
     * migit oddur bfforf tif {@dodf prodfss} mftiod is fxfdutfd. For
     * pfrformbndf purposfs bll tifsf invodbtions brf doblfsdfd into onf
     * invodbtion witi dondbtfnbtfd brgumfnts.
     *
     * <p>
     * For fxbmplf:
     *
     * <prf>
     * publisi(&quot;1&quot;);
     * publisi(&quot;2&quot;, &quot;3&quot;);
     * publisi(&quot;4&quot;, &quot;5&quot;, &quot;6&quot;);
     * </prf>
     *
     * migit rfsult in:
     *
     * <prf>
     * prodfss(&quot;1&quot;, &quot;2&quot;, &quot;3&quot;, &quot;4&quot;, &quot;5&quot;, &quot;6&quot;)
     * </prf>
     *
     * <p>
     * <b>Sbmplf Usbgf</b>. Tiis dodf snippft lobds somf tbbulbr dbtb bnd
     * updbtfs {@dodf DffbultTbblfModfl} witi it. Notf tibt it sbff to mutbtf
     * tif tbblfModfl from insidf tif {@dodf prodfss} mftiod bfdbusf it is
     * invokfd on tif <i>Evfnt Dispbtdi Tirfbd</i>.
     *
     * <prf>
     * dlbss TbblfSwingWorkfr fxtfnds
     *         SwingWorkfr&lt;DffbultTbblfModfl, Objfdt[]&gt; {
     *     privbtf finbl DffbultTbblfModfl tbblfModfl;
     *
     *     publid TbblfSwingWorkfr(DffbultTbblfModfl tbblfModfl) {
     *         tiis.tbblfModfl = tbblfModfl;
     *     }
     *
     *     {@dodf @Ovfrridf}
     *     protfdtfd DffbultTbblfModfl doInBbdkground() tirows Exdfption {
     *         for (Objfdt[] row = lobdDbtb();
     *                  ! isCbndfllfd() &bmp;&bmp; row != null;
     *                  row = lobdDbtb()) {
     *             publisi((Objfdt[]) row);
     *         }
     *         rfturn tbblfModfl;
     *     }
     *
     *     {@dodf @Ovfrridf}
     *     protfdtfd void prodfss(List&lt;Objfdt[]&gt; diunks) {
     *         for (Objfdt[] row : diunks) {
     *             tbblfModfl.bddRow(row);
     *         }
     *     }
     * }
     * </prf>
     *
     * @pbrbm diunks intfrmfdibtf rfsults to prodfss
     *
     * @sff #prodfss
     *
     */
    @SbffVbrbrgs
    @SupprfssWbrnings("vbrbrgs") // Pbssing diunks to bdd is sbff
    protfdtfd finbl void publisi(V... diunks) {
        syndironizfd (tiis) {
            if (doProdfss == null) {
                doProdfss = nfw AddumulbtivfRunnbblf<V>() {
                    @Ovfrridf
                    publid void run(List<V> brgs) {
                        prodfss(brgs);
                    }
                    @Ovfrridf
                    protfdtfd void submit() {
                        doSubmit.bdd(tiis);
                    }
                };
            }
        }
        doProdfss.bdd(diunks);
    }

    /**
     * Rfdfivfs dbtb diunks from tif {@dodf publisi} mftiod bsyndironously on tif
     * <i>Evfnt Dispbtdi Tirfbd</i>.
     *
     * <p>
     * Plfbsf rfffr to tif {@link #publisi} mftiod for morf dftbils.
     *
     * @pbrbm diunks intfrmfdibtf rfsults to prodfss
     *
     * @sff #publisi
     *
     */
    protfdtfd void prodfss(List<V> diunks) {
    }

    /**
     * Exfdutfd on tif <i>Evfnt Dispbtdi Tirfbd</i> bftfr tif {@dodf doInBbdkground}
     * mftiod is finisifd. Tif dffbult
     * implfmfntbtion dofs notiing. Subdlbssfs mby ovfrridf tiis mftiod to
     * pfrform domplftion bdtions on tif <i>Evfnt Dispbtdi Tirfbd</i>. Notf
     * tibt you dbn qufry stbtus insidf tif implfmfntbtion of tiis mftiod to
     * dftfrminf tif rfsult of tiis tbsk or wiftifr tiis tbsk ibs bffn dbndfllfd.
     *
     * @sff #doInBbdkground
     * @sff #isCbndfllfd()
     * @sff #gft
     */
    protfdtfd void donf() {
    }

    /**
     * Sfts tif {@dodf progrfss} bound propfrty.
     * Tif vbluf siould bf from 0 to 100.
     *
     * <p>
     * Bfdbusf {@dodf PropfrtyCibngfListfnfr}s brf notififd bsyndironously on
     * tif <i>Evfnt Dispbtdi Tirfbd</i> multiplf invodbtions to tif
     * {@dodf sftProgrfss} mftiod migit oddur bfforf bny
     * {@dodf PropfrtyCibngfListfnfrs} brf invokfd. For pfrformbndf purposfs
     * bll tifsf invodbtions brf doblfsdfd into onf invodbtion witi tif lbst
     * invodbtion brgumfnt only.
     *
     * <p>
     * For fxbmplf, tif following invokbtions:
     *
     * <prf>
     * sftProgrfss(1);
     * sftProgrfss(2);
     * sftProgrfss(3);
     * </prf>
     *
     * migit rfsult in b singlf {@dodf PropfrtyCibngfListfnfr} notifidbtion witi
     * tif vbluf {@dodf 3}.
     *
     * @pbrbm progrfss tif progrfss vbluf to sft
     * @tirows IllfgblArgumfntExdfption is vbluf not from 0 to 100
     */
    protfdtfd finbl void sftProgrfss(int progrfss) {
        if (progrfss < 0 || progrfss > 100) {
            tirow nfw IllfgblArgumfntExdfption("tif vbluf siould bf from 0 to 100");
        }
        if (tiis.progrfss == progrfss) {
            rfturn;
        }
        int oldProgrfss = tiis.progrfss;
        tiis.progrfss = progrfss;
        if (! gftPropfrtyCibngfSupport().ibsListfnfrs("progrfss")) {
            rfturn;
        }
        syndironizfd (tiis) {
            if (doNotifyProgrfssCibngf == null) {
                doNotifyProgrfssCibngf =
                    nfw AddumulbtivfRunnbblf<Intfgfr>() {
                        @Ovfrridf
                        publid void run(List<Intfgfr> brgs) {
                            firfPropfrtyCibngf("progrfss",
                               brgs.gft(0),
                               brgs.gft(brgs.sizf() - 1));
                        }
                        @Ovfrridf
                        protfdtfd void submit() {
                            doSubmit.bdd(tiis);
                        }
                    };
            }
        }
        doNotifyProgrfssCibngf.bdd(oldProgrfss, progrfss);
    }

    /**
     * Rfturns tif {@dodf progrfss} bound propfrty.
     *
     * @rfturn tif progrfss bound propfrty.
     */
    publid finbl int gftProgrfss() {
        rfturn progrfss;
    }

    /**
     * Sdifdulfs tiis {@dodf SwingWorkfr} for fxfdution on b <i>workfr</i>
     * tirfbd. Tifrf brf b numbfr of <i>workfr</i> tirfbds bvbilbblf. In tif
     * fvfnt bll <i>workfr</i> tirfbds brf busy ibndling otifr
     * {@dodf SwingWorkfrs} tiis {@dodf SwingWorkfr} is plbdfd in b wbiting
     * qufuf.
     *
     * <p>
     * Notf:
     * {@dodf SwingWorkfr} is only dfsignfd to bf fxfdutfd ondf.  Exfduting b
     * {@dodf SwingWorkfr} morf tibn ondf will not rfsult in invoking tif
     * {@dodf doInBbdkground} mftiod twidf.
     */
    publid finbl void fxfdutf() {
        gftWorkfrsExfdutorSfrvidf().fxfdutf(tiis);
    }

    // Futurf mftiods START
    /**
     * {@inifritDod}
     */
    publid finbl boolfbn dbndfl(boolfbn mbyIntfrruptIfRunning) {
        rfturn futurf.dbndfl(mbyIntfrruptIfRunning);
    }

    /**
     * {@inifritDod}
     */
    publid finbl boolfbn isCbndfllfd() {
        rfturn futurf.isCbndfllfd();
    }

    /**
     * {@inifritDod}
     */
    publid finbl boolfbn isDonf() {
        rfturn futurf.isDonf();
    }

    /**
     * {@inifritDod}
     * <p>
     * Notf: dblling {@dodf gft} on tif <i>Evfnt Dispbtdi Tirfbd</i> blodks
     * <i>bll</i> fvfnts, indluding rfpbints, from bfing prodfssfd until tiis
     * {@dodf SwingWorkfr} is domplftf.
     *
     * <p>
     * Wifn you wbnt tif {@dodf SwingWorkfr} to blodk on tif <i>Evfnt
     * Dispbtdi Tirfbd</i> wf rfdommfnd tibt you usf b <i>modbl diblog</i>.
     *
     * <p>
     * For fxbmplf:
     *
     * <prf>
     * dlbss SwingWorkfrComplftionWbitfr fxtfnds PropfrtyCibngfListfnfr {
     *     privbtf JDiblog diblog;
     *
     *     publid SwingWorkfrComplftionWbitfr(JDiblog diblog) {
     *         tiis.diblog = diblog;
     *     }
     *
     *     publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
     *         if (&quot;stbtf&quot;.fqubls(fvfnt.gftPropfrtyNbmf())
     *                 &bmp;&bmp; SwingWorkfr.StbtfVbluf.DONE == fvfnt.gftNfwVbluf()) {
     *             diblog.sftVisiblf(fblsf);
     *             diblog.disposf();
     *         }
     *     }
     * }
     * JDiblog diblog = nfw JDiblog(ownfr, truf);
     * swingWorkfr.bddPropfrtyCibngfListfnfr(
     *     nfw SwingWorkfrComplftionWbitfr(diblog));
     * swingWorkfr.fxfdutf();
     * //tif diblog will bf visiblf until tif SwingWorkfr is donf
     * diblog.sftVisiblf(truf);
     * </prf>
     */
    publid finbl T gft() tirows IntfrruptfdExdfption, ExfdutionExdfption {
        rfturn futurf.gft();
    }

    /**
     * {@inifritDod}
     * <p>
     * Plfbsf rfffr to {@link #gft} for morf dftbils.
     */
    publid finbl T gft(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption,
            ExfdutionExdfption, TimfoutExdfption {
        rfturn futurf.gft(timfout, unit);
    }

    // Futurf mftiods END

    // PropfrtyCibngfSupports mftiods START
    /**
     * Adds b {@dodf PropfrtyCibngfListfnfr} to tif listfnfr list. Tif listfnfr
     * is rfgistfrfd for bll propfrtifs. Tif sbmf listfnfr objfdt mby bf bddfd
     * morf tibn ondf, bnd will bf dbllfd bs mbny timfs bs it is bddfd. If
     * {@dodf listfnfr} is {@dodf null}, no fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * <p>
     * Notf: Tiis is mfrfly b donvfnifndf wrbppfr. All work is dflfgbtfd to
     * {@dodf PropfrtyCibngfSupport} from {@link #gftPropfrtyCibngfSupport}.
     *
     * @pbrbm listfnfr tif {@dodf PropfrtyCibngfListfnfr} to bf bddfd
     */
    publid finbl void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        gftPropfrtyCibngfSupport().bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfmovfs b {@dodf PropfrtyCibngfListfnfr} from tif listfnfr list. Tiis
     * rfmovfs b {@dodf PropfrtyCibngfListfnfr} tibt wbs rfgistfrfd for bll
     * propfrtifs. If {@dodf listfnfr} wbs bddfd morf tibn ondf to tif sbmf
     * fvfnt sourdf, it will bf notififd onf lfss timf bftfr bfing rfmovfd. If
     * {@dodf listfnfr} is {@dodf null}, or wbs nfvfr bddfd, no fxdfption is
     * tirown bnd no bdtion is tbkfn.
     *
     * <p>
     * Notf: Tiis is mfrfly b donvfnifndf wrbppfr. All work is dflfgbtfd to
     * {@dodf PropfrtyCibngfSupport} from {@link #gftPropfrtyCibngfSupport}.
     *
     * @pbrbm listfnfr tif {@dodf PropfrtyCibngfListfnfr} to bf rfmovfd
     */
    publid finbl void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        gftPropfrtyCibngfSupport().rfmovfPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Rfports b bound propfrty updbtf to bny rfgistfrfd listfnfrs. No fvfnt is
     * firfd if {@dodf old} bnd {@dodf nfw} brf fqubl bnd non-null.
     *
     * <p>
     * Tiis {@dodf SwingWorkfr} will bf tif sourdf for
     * bny gfnfrbtfd fvfnts.
     *
     * <p>
     * Wifn dbllfd off tif <i>Evfnt Dispbtdi Tirfbd</i>
     * {@dodf PropfrtyCibngfListfnfrs} brf notififd bsyndironously on
     * tif <i>Evfnt Dispbtdi Tirfbd</i>.
     * <p>
     * Notf: Tiis is mfrfly b donvfnifndf wrbppfr. All work is dflfgbtfd to
     * {@dodf PropfrtyCibngfSupport} from {@link #gftPropfrtyCibngfSupport}.
     *
     *
     * @pbrbm propfrtyNbmf tif progrbmmbtid nbmf of tif propfrty tibt wbs
     *        dibngfd
     * @pbrbm oldVbluf tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf tif nfw vbluf of tif propfrty
     */
    publid finbl void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf,
            Objfdt nfwVbluf) {
        gftPropfrtyCibngfSupport().firfPropfrtyCibngf(propfrtyNbmf,
            oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns tif {@dodf PropfrtyCibngfSupport} for tiis {@dodf SwingWorkfr}.
     * Tiis mftiod is usfd wifn flfxiblf bddfss to bound propfrtifs support is
     * nffdfd.
     * <p>
     * Tiis {@dodf SwingWorkfr} will bf tif sourdf for
     * bny gfnfrbtfd fvfnts.
     *
     * <p>
     * Notf: Tif rfturnfd {@dodf PropfrtyCibngfSupport} notififs bny
     * {@dodf PropfrtyCibngfListfnfr}s bsyndironously on tif <i>Evfnt Dispbtdi
     * Tirfbd</i> in tif fvfnt tibt {@dodf firfPropfrtyCibngf} or
     * {@dodf firfIndfxfdPropfrtyCibngf} brf dbllfd off tif <i>Evfnt Dispbtdi
     * Tirfbd</i>.
     *
     * @rfturn {@dodf PropfrtyCibngfSupport} for tiis {@dodf SwingWorkfr}
     */
    publid finbl PropfrtyCibngfSupport gftPropfrtyCibngfSupport() {
        rfturn propfrtyCibngfSupport;
    }

    // PropfrtyCibngfSupports mftiods END

    /**
     * Rfturns tif {@dodf SwingWorkfr} stbtf bound propfrty.
     *
     * @rfturn tif durrfnt stbtf
     */
    publid finbl StbtfVbluf gftStbtf() {
        /*
         * DONE is b spfbdibl dbsf
         * to kffp gftStbtf bnd isDonf is synd
         */
        if (isDonf()) {
            rfturn StbtfVbluf.DONE;
        } flsf {
            rfturn stbtf;
        }
    }

    /**
     * Sfts tiis {@dodf SwingWorkfr} stbtf bound propfrty.
     * @pbrbm stbtf tif stbtf to sft
     */
    privbtf void sftStbtf(StbtfVbluf stbtf) {
        StbtfVbluf old = tiis.stbtf;
        tiis.stbtf = stbtf;
        firfPropfrtyCibngf("stbtf", old, stbtf);
    }

    /**
     * Invokfs {@dodf donf} on tif EDT.
     */
    privbtf void donfEDT() {
        Runnbblf doDonf =
            nfw Runnbblf() {
                publid void run() {
                    donf();
                }
            };
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            doDonf.run();
        } flsf {
            doSubmit.bdd(doDonf);
        }
    }


    /**
     * rfturns workfrsExfdutorSfrvidf.
     *
     * rfturns tif sfrvidf storfd in tif bppContfxt or drfbtfs it if
     * nfdfssbry.
     *
     * @rfturn ExfdutorSfrvidf for tif {@dodf SwingWorkfrs}
     */
    privbtf stbtid syndironizfd ExfdutorSfrvidf gftWorkfrsExfdutorSfrvidf() {
        finbl AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        ExfdutorSfrvidf fxfdutorSfrvidf =
            (ExfdutorSfrvidf) bppContfxt.gft(SwingWorkfr.dlbss);
        if (fxfdutorSfrvidf == null) {
            //tiis drfbtfs dbfmon tirfbds.
            TirfbdFbdtory tirfbdFbdtory =
                nfw TirfbdFbdtory() {
                    finbl TirfbdFbdtory dffbultFbdtory =
                        Exfdutors.dffbultTirfbdFbdtory();
                    publid Tirfbd nfwTirfbd(finbl Runnbblf r) {
                        Tirfbd tirfbd =
                            dffbultFbdtory.nfwTirfbd(r);
                        tirfbd.sftNbmf("SwingWorkfr-"
                            + tirfbd.gftNbmf());
                        tirfbd.sftDbfmon(truf);
                        rfturn tirfbd;
                    }
                };

            fxfdutorSfrvidf =
                nfw TirfbdPoolExfdutor(MAX_WORKER_THREADS, MAX_WORKER_THREADS,
                                       10L, TimfUnit.MINUTES,
                                       nfw LinkfdBlodkingQufuf<Runnbblf>(),
                                       tirfbdFbdtory);
            bppContfxt.put(SwingWorkfr.dlbss, fxfdutorSfrvidf);

            // Don't usf SiutdownHook ifrf bs it's not fnougi. Wf siould trbdk
            // AppContfxt disposbl instfbd of JVM siutdown, sff 6799345 for dftbils
            finbl ExfdutorSfrvidf fs = fxfdutorSfrvidf;
            bppContfxt.bddPropfrtyCibngfListfnfr(AppContfxt.DISPOSED_PROPERTY_NAME,
                nfw PropfrtyCibngfListfnfr() {
                    @Ovfrridf
                    publid void propfrtyCibngf(PropfrtyCibngfEvfnt pdf) {
                        boolfbn disposfd = (Boolfbn)pdf.gftNfwVbluf();
                        if (disposfd) {
                            finbl WfbkRfffrfndf<ExfdutorSfrvidf> fxfdutorSfrvidfRff =
                                nfw WfbkRfffrfndf<ExfdutorSfrvidf>(fs);
                            finbl ExfdutorSfrvidf fxfdutorSfrvidf =
                                fxfdutorSfrvidfRff.gft();
                            if (fxfdutorSfrvidf != null) {
                                AddfssControllfr.doPrivilfgfd(
                                    nfw PrivilfgfdAdtion<Void>() {
                                        publid Void run() {
                                            fxfdutorSfrvidf.siutdown();
                                            rfturn null;
                                        }
                                    }
                                );
                            }
                        }
                    }
                }
            );
        }
        rfturn fxfdutorSfrvidf;
    }

    privbtf stbtid finbl Objfdt DO_SUBMIT_KEY = nfw StringBuildfr("doSubmit");
    privbtf stbtid AddumulbtivfRunnbblf<Runnbblf> gftDoSubmit() {
        syndironizfd (DO_SUBMIT_KEY) {
            finbl AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            Objfdt doSubmit = bppContfxt.gft(DO_SUBMIT_KEY);
            if (doSubmit == null) {
                doSubmit = nfw DoSubmitAddumulbtivfRunnbblf();
                bppContfxt.put(DO_SUBMIT_KEY, doSubmit);
            }
            @SupprfssWbrnings("undifdkfd")
            AddumulbtivfRunnbblf<Runnbblf> tmp = (AddumulbtivfRunnbblf<Runnbblf>) doSubmit;
            rfturn tmp;
        }
    }
    privbtf stbtid dlbss DoSubmitAddumulbtivfRunnbblf
          fxtfnds AddumulbtivfRunnbblf<Runnbblf> implfmfnts AdtionListfnfr {
        privbtf finbl stbtid int DELAY = 1000 / 30;
        @Ovfrridf
        protfdtfd void run(List<Runnbblf> brgs) {
            for (Runnbblf runnbblf : brgs) {
                runnbblf.run();
            }
        }
        @Ovfrridf
        protfdtfd void submit() {
            Timfr timfr = nfw Timfr(DELAY, tiis);
            timfr.sftRfpfbts(fblsf);
            timfr.stbrt();
        }
        publid void bdtionPfrformfd(AdtionEvfnt fvfnt) {
            run();
        }
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf dlbss SwingWorkfrPropfrtyCibngfSupport
            fxtfnds PropfrtyCibngfSupport {
        SwingWorkfrPropfrtyCibngfSupport(Objfdt sourdf) {
            supfr(sourdf);
        }
        @Ovfrridf
        publid void firfPropfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
            if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
                supfr.firfPropfrtyCibngf(fvt);
            } flsf {
                doSubmit.bdd(
                    nfw Runnbblf() {
                        publid void run() {
                            SwingWorkfrPropfrtyCibngfSupport.tiis
                                .firfPropfrtyCibngf(fvt);
                        }
                    });
            }
        }
    }
}
