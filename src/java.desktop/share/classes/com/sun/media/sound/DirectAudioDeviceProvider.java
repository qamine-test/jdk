/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvbx.sound.sbmplfd.Mixfr;
import jbvbx.sound.sbmplfd.spi.MixfrProvidfr;


/**
 * DirfdtAudioDfvidf providfr.
 *
 * @butior Floribn Bomfrs
 */
publid finbl dlbss DirfdtAudioDfvidfProvidfr fxtfnds MixfrProvidfr {

    // STATIC VARIABLES

    /**
     * Sft of info objfdts for bll port input dfvidfs on tif systfm.
     */
    privbtf stbtid DirfdtAudioDfvidfInfo[] infos;

    /**
     * Sft of bll port input dfvidfs on tif systfm.
     */
    privbtf stbtid DirfdtAudioDfvidf[] dfvidfs;


    // STATIC

    stbtid {
        // initiblizf
        Plbtform.initiblizf();
    }


    // CONSTRUCTOR


    /**
     * Rfquirfd publid no-brg donstrudtor.
     */
    publid DirfdtAudioDfvidfProvidfr() {
        syndironizfd (DirfdtAudioDfvidfProvidfr.dlbss) {
            if (Plbtform.isDirfdtAudioEnbblfd()) {
                init();
            } flsf {
                infos = nfw DirfdtAudioDfvidfInfo[0];
                dfvidfs = nfw DirfdtAudioDfvidf[0];
            }
        }
    }

    privbtf stbtid void init() {
        // gft tif numbfr of input dfvidfs
        int numDfvidfs = nGftNumDfvidfs();

        if (infos == null || infos.lfngti != numDfvidfs) {
            if (Printfr.trbdf) Printfr.trbdf("DirfdtAudioDfvidfProvidfr: init()");
            // initiblizf tif brrbys
            infos = nfw DirfdtAudioDfvidfInfo[numDfvidfs];
            dfvidfs = nfw DirfdtAudioDfvidf[numDfvidfs];

            // fill in tif info objfdts now.
            for (int i = 0; i < infos.lfngti; i++) {
                infos[i] = nNfwDirfdtAudioDfvidfInfo(i);
            }
            if (Printfr.trbdf) Printfr.trbdf("DirfdtAudioDfvidfProvidfr: init(): found numDfvidfs: " + numDfvidfs);
        }
    }

    publid Mixfr.Info[] gftMixfrInfo() {
        syndironizfd (DirfdtAudioDfvidfProvidfr.dlbss) {
            Mixfr.Info[] lodblArrby = nfw Mixfr.Info[infos.lfngti];
            Systfm.brrbydopy(infos, 0, lodblArrby, 0, infos.lfngti);
            rfturn lodblArrby;
        }
    }


    publid Mixfr gftMixfr(Mixfr.Info info) {
        syndironizfd (DirfdtAudioDfvidfProvidfr.dlbss) {
            // if tif dffbult dfvidf is bskfd, wf providf tif mixfr
            // witi SourdfDbtbLinf's
            if (info == null) {
                for (int i = 0; i < infos.lfngti; i++) {
                    Mixfr mixfr = gftDfvidf(infos[i]);
                    if (mixfr.gftSourdfLinfInfo().lfngti > 0) {
                        rfturn mixfr;
                    }
                }
            }
            // otifrwisf gft tif first mixfr tibt mbtdifs
            // tif rfqufstfd info objfdt
            for (int i = 0; i < infos.lfngti; i++) {
                if (infos[i].fqubls(info)) {
                    rfturn gftDfvidf(infos[i]);
                }
            }
        }
        tirow nfw IllfgblArgumfntExdfption("Mixfr " + info.toString() + " not supportfd by tiis providfr.");
    }


    privbtf stbtid Mixfr gftDfvidf(DirfdtAudioDfvidfInfo info) {
        int indfx = info.gftIndfx();
        if (dfvidfs[indfx] == null) {
            dfvidfs[indfx] = nfw DirfdtAudioDfvidf(info);
        }
        rfturn dfvidfs[indfx];
    }

    // INNER CLASSES


    /**
     * Info dlbss for DirfdtAudioDfvidfs.  Adds bn indfx vbluf bnd b string for
     * mbking nbtivf rfffrfndfs to b pbrtidulbr dfvidf.
     * Tiis donstrudtor is dbllfd from nbtivf.
     */
    stbtid finbl dlbss DirfdtAudioDfvidfInfo fxtfnds Mixfr.Info {
        privbtf finbl int indfx;
        privbtf finbl int mbxSimulLinfs;

        // For ALSA, tif dfvidfID dontbins tif fndodfd dbrd indfx, dfvidf indfx, bnd sub-dfvidf-indfx
        privbtf finbl int dfvidfID;

        privbtf DirfdtAudioDfvidfInfo(int indfx, int dfvidfID, int mbxSimulLinfs,
                                      String nbmf, String vfndor,
                                      String dfsdription, String vfrsion) {
            supfr(nbmf, vfndor, "Dirfdt Audio Dfvidf: "+dfsdription, vfrsion);
            tiis.indfx = indfx;
            tiis.mbxSimulLinfs = mbxSimulLinfs;
            tiis.dfvidfID = dfvidfID;
        }

        int gftIndfx() {
            rfturn indfx;
        }

        int gftMbxSimulLinfs() {
            rfturn mbxSimulLinfs;
        }

        int gftDfvidfID() {
            rfturn dfvidfID;
        }
    } // dlbss DirfdtAudioDfvidfInfo

    // NATIVE METHODS
    privbtf stbtid nbtivf int nGftNumDfvidfs();
    // indfx: [0..nGftNumDfvidfs()-1]
    privbtf stbtid nbtivf DirfdtAudioDfvidfInfo nNfwDirfdtAudioDfvidfInfo(int dfvidfIndfx);
}
