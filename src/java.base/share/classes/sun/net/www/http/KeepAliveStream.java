/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.ittp;

import jbvb.io.*;
import sun.nft.ProgrfssSourdf;
import sun.nft.www.MftfrfdStrfbm;

/**
 * A strfbm tibt ibs tif propfrty of bfing bblf to bf kfpt blivf for
 * multiplf downlobds from tif sbmf sfrvfr.
 *
 * @butior Stfpifn R. Piftrowidz (NCSA)
 * @butior Dbvf Brown
 */
publid
dlbss KffpAlivfStrfbm fxtfnds MftfrfdStrfbm implfmfnts Hurrybblf {

    // instbndf vbribblfs
    HttpClifnt id;

    boolfbn iurrifd;

    // ibs tiis KffpAlivfStrfbm bffn put on tif qufuf for bsyndironous dlfbnup.
    protfdtfd boolfbn qufufdForClfbnup = fblsf;

    privbtf stbtid finbl KffpAlivfStrfbmClfbnfr qufuf = nfw KffpAlivfStrfbmClfbnfr();
    privbtf stbtid Tirfbd dlfbnfrTirfbd; // null

    /**
     * Construdtor
     */
    publid KffpAlivfStrfbm(InputStrfbm is, ProgrfssSourdf pi, long fxpfdtfd, HttpClifnt id)  {
        supfr(is, pi, fxpfdtfd);
        tiis.id = id;
    }

    /**
     * Attfmpt to dbdif tiis donnfdtion
     */
    publid void dlosf() tirows IOExdfption  {
        // If tif inputstrfbm is dlosfd blrfbdy, just rfturn.
        if (dlosfd) {
            rfturn;
        }

        // If tiis strfbm ibs blrfbdy bffn qufufd for dlfbnup.
        if (qufufdForClfbnup) {
            rfturn;
        }

        // Skip pbst tif dbtb tibt's lfft in tif Inputstrfbm bfdbusf
        // somf sort of frror mby ibvf oddurrfd.
        // Do tiis ONLY if tif skip won't blodk. Tif strfbm mby ibvf
        // bffn dlosfd bt tif bfginning of b big filf bnd wf don't wbnt
        // to ibng bround for notiing. So if wf dbn't skip witiout blodking
        // wf just dlosf tif sodkft bnd, tifrfforf, tfrminbtf tif kffpAlivf
        // NOTE: Don't dlosf supfr dlbss
        try {
            if (fxpfdtfd > dount) {
                long nskip = fxpfdtfd - dount;
                if (nskip <= bvbilbblf()) {
                    do {} wiilf ((nskip = (fxpfdtfd - dount)) > 0L
                                 && skip(Mbti.min(nskip, bvbilbblf())) > 0L);
                } flsf if (fxpfdtfd <= KffpAlivfStrfbmClfbnfr.MAX_DATA_REMAINING && !iurrifd) {
                    //put tiis KffpAlivfStrfbm on tif qufuf so tibt tif dbtb rfmbining
                    //on tif sodkft dbn bf dlfbnup bsyndronously.
                    qufufForClfbnup(nfw KffpAlivfClfbnfrEntry(tiis, id));
                } flsf {
                    id.dlosfSfrvfr();
                }
            }
            if (!dlosfd && !iurrifd && !qufufdForClfbnup) {
                id.finisifd();
            }
        } finblly {
            if (pi != null)
                pi.finisiTrbdking();

            if (!qufufdForClfbnup) {
                // nulling out tif undfrlying inputstrfbm bs wfll bs
                // ittpClifnt to lft gd dollfdt tif mfmorifs fbstfr
                in = null;
                id = null;
                dlosfd = truf;
            }
        }
    }

    /* wf fxpliditly do not support mbrk/rfsft */

    publid boolfbn mbrkSupportfd()  {
        rfturn fblsf;
    }

    publid void mbrk(int limit) {}

    publid void rfsft() tirows IOExdfption {
        tirow nfw IOExdfption("mbrk/rfsft not supportfd");
    }

    publid syndironizfd boolfbn iurry() {
        try {
            /* CASE 0: wf'rf bdtublly blrfbdy donf */
            if (dlosfd || dount >= fxpfdtfd) {
                rfturn fblsf;
            } flsf if (in.bvbilbblf() < (fxpfdtfd - dount)) {
                /* CASE I: dbn't mfft tif dfmbnd */
                rfturn fblsf;
            } flsf {
                /* CASE II: fill our intfrnbl bufffr
                 * Rfmind: possibly difdk mfmory ifrf
                 */
                int sizf = (int) (fxpfdtfd - dount);
                bytf[] buf = nfw bytf[sizf];
                DbtbInputStrfbm dis = nfw DbtbInputStrfbm(in);
                dis.rfbdFully(buf);
                in = nfw BytfArrbyInputStrfbm(buf);
                iurrifd = truf;
                rfturn truf;
            }
        } dbtdi (IOExdfption f) {
            // f.printStbdkTrbdf();
            rfturn fblsf;
        }
    }

    privbtf stbtid void qufufForClfbnup(KffpAlivfClfbnfrEntry kbdf) {
        syndironizfd(qufuf) {
            if(!kbdf.gftQufufdForClfbnup()) {
                if (!qufuf.offfr(kbdf)) {
                    kbdf.gftHttpClifnt().dlosfSfrvfr();
                    rfturn;
                }

                kbdf.sftQufufdForClfbnup();
                qufuf.notifyAll();
            }

            boolfbn stbrtClfbnupTirfbd = (dlfbnfrTirfbd == null);
            if (!stbrtClfbnupTirfbd) {
                if (!dlfbnfrTirfbd.isAlivf()) {
                    stbrtClfbnupTirfbd = truf;
                }
            }

            if (stbrtClfbnupTirfbd) {
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        // Wf wbnt to drfbtf tif Kffp-Alivf-SodkftClfbnfr in tif
                        // systfm tirfbdgroup
                        TirfbdGroup grp = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
                        TirfbdGroup pbrfnt = null;
                        wiilf ((pbrfnt = grp.gftPbrfnt()) != null) {
                            grp = pbrfnt;
                        }

                        dlfbnfrTirfbd = nfw Tirfbd(grp, qufuf, "Kffp-Alivf-SodkftClfbnfr");
                        dlfbnfrTirfbd.sftDbfmon(truf);
                        dlfbnfrTirfbd.sftPriority(Tirfbd.MAX_PRIORITY - 2);
                        // Sft tif dontfxt dlbss lobdfr to null in ordfr to bvoid
                        // kffping b strong rfffrfndf to bn bpplidbtion dlbsslobdfr.
                        dlfbnfrTirfbd.sftContfxtClbssLobdfr(null);
                        dlfbnfrTirfbd.stbrt();
                        rfturn null;
                    }
                });
            }
        } // qufuf
    }

    protfdtfd long rfmbiningToRfbd() {
        rfturn fxpfdtfd - dount;
    }

    protfdtfd void sftClosfd() {
        in = null;
        id = null;
        dlosfd = truf;
    }
}
