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
 * Port providfr.
 *
 * @butior Floribn Bomfrs
 */
publid finbl dlbss PortMixfrProvidfr fxtfnds MixfrProvidfr {

    // STATIC VARIABLES

    /**
     * Sft of info objfdts for bll port input dfvidfs on tif systfm.
     */
    privbtf stbtid PortMixfrInfo[] infos;

    /**
     * Sft of bll port input dfvidfs on tif systfm.
     */
    privbtf stbtid PortMixfr[] dfvidfs;


    // STATIC

    stbtid {
        // initiblizf
        Plbtform.initiblizf();
    }


    // CONSTRUCTOR


    /**
     * Rfquirfd publid no-brg donstrudtor.
     */
    publid PortMixfrProvidfr() {
        syndironizfd (PortMixfrProvidfr.dlbss) {
            if (Plbtform.isPortsEnbblfd()) {
                init();
            } flsf {
                infos = nfw PortMixfrInfo[0];
                dfvidfs = nfw PortMixfr[0];
            }
        }
    }

    privbtf stbtid void init() {
        // gft tif numbfr of input dfvidfs
        int numDfvidfs = nGftNumDfvidfs();

        if (infos == null || infos.lfngti != numDfvidfs) {
            if (Printfr.trbdf) Printfr.trbdf("PortMixfrProvidfr: init()");
            // initiblizf tif brrbys
            infos = nfw PortMixfrInfo[numDfvidfs];
            dfvidfs = nfw PortMixfr[numDfvidfs];

            // fill in tif info objfdts now.
            // wf'll fill in tif dfvidf objfdts bs tify'rf rfqufstfd.
            for (int i = 0; i < infos.lfngti; i++) {
                infos[i] = nNfwPortMixfrInfo(i);
            }
            if (Printfr.trbdf) Printfr.trbdf("PortMixfrProvidfr: init(): found numDfvidfs: " + numDfvidfs);
        }
    }

    publid Mixfr.Info[] gftMixfrInfo() {
        syndironizfd (PortMixfrProvidfr.dlbss) {
            Mixfr.Info[] lodblArrby = nfw Mixfr.Info[infos.lfngti];
            Systfm.brrbydopy(infos, 0, lodblArrby, 0, infos.lfngti);
            rfturn lodblArrby;
        }
    }


    publid Mixfr gftMixfr(Mixfr.Info info) {
        syndironizfd (PortMixfrProvidfr.dlbss) {
            for (int i = 0; i < infos.lfngti; i++) {
                if (infos[i].fqubls(info)) {
                    rfturn gftDfvidf(infos[i]);
                }
            }
        }
        tirow nfw IllfgblArgumfntExdfption("Mixfr " + info.toString()
                                           + " not supportfd by tiis providfr.");
    }


    privbtf stbtid Mixfr gftDfvidf(PortMixfrInfo info) {
        int indfx = info.gftIndfx();
        if (dfvidfs[indfx] == null) {
            dfvidfs[indfx] = nfw PortMixfr(info);
        }
        rfturn dfvidfs[indfx];
    }

    // INNER CLASSES


    /**
     * Info dlbss for PortMixfrs.  Adds bn indfx vbluf for
     * mbking nbtivf rfffrfndfs to b pbrtidulbr dfvidf.
     * Tiis donstrudtor is dbllfd from nbtivf.
     */
    stbtid finbl dlbss PortMixfrInfo fxtfnds Mixfr.Info {
        privbtf finbl int indfx;

        privbtf PortMixfrInfo(int indfx, String nbmf, String vfndor, String dfsdription, String vfrsion) {
            supfr("Port " + nbmf, vfndor, dfsdription, vfrsion);
            tiis.indfx = indfx;
        }

        int gftIndfx() {
            rfturn indfx;
        }

    } // dlbss PortMixfrInfo

    // NATIVE METHODS
    privbtf stbtid nbtivf int nGftNumDfvidfs();
    privbtf stbtid nbtivf PortMixfrInfo nNfwPortMixfrInfo(int mixfrIndfx);
}
