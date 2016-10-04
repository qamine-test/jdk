/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import jbvb.util.dondurrfnt.*;
import dom.sun.nio.filf.SfnsitivityWbtdiEvfntModififr;

/**
 * Simplf WbtdiSfrvidf implfmfntbtion tibt usfs pfriodid tbsks to poll
 * rfgistfrfd dirfdtorifs for dibngfs.  Tiis implfmfntbtion is for usf on
 * opfrbting systfms tibt do not ibvf nbtivf filf dibngf notifidbtion support.
 */

dlbss PollingWbtdiSfrvidf
    fxtfnds AbstrbdtWbtdiSfrvidf
{
    // mbp of rfgistrbtions
    privbtf finbl Mbp<Objfdt,PollingWbtdiKfy> mbp =
        nfw HbsiMbp<Objfdt,PollingWbtdiKfy>();

    // usfd to fxfdutf tif pfriodid tbsks tibt poll for dibngfs
    privbtf finbl SdifdulfdExfdutorSfrvidf sdifdulfdExfdutor;

    PollingWbtdiSfrvidf() {
        // TBD: Mbkf tif numbfr of tirfbds donfigurbblf
        sdifdulfdExfdutor = Exfdutors
            .nfwSinglfTirfbdSdifdulfdExfdutor(nfw TirfbdFbdtory() {
                 @Ovfrridf
                 publid Tirfbd nfwTirfbd(Runnbblf r) {
                     Tirfbd t = nfw Tirfbd(r);
                     t.sftDbfmon(truf);
                     rfturn t;
                 }});
    }

    /**
     * Rfgistfr tif givfn filf witi tiis wbtdi sfrvidf
     */
    @Ovfrridf
    WbtdiKfy rfgistfr(finbl Pbti pbti,
                      WbtdiEvfnt.Kind<?>[] fvfnts,
                      WbtdiEvfnt.Modififr... modififrs)
         tirows IOExdfption
    {
        // difdk fvfnts - CCE will bf tirown if tifrf brf invblid flfmfnts
        finbl Sft<WbtdiEvfnt.Kind<?>> fvfntSft =
            nfw HbsiSft<WbtdiEvfnt.Kind<?>>(fvfnts.lfngti);
        for (WbtdiEvfnt.Kind<?> fvfnt: fvfnts) {
            // stbndbrd fvfnts
            if (fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_CREATE ||
                fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY ||
                fvfnt == StbndbrdWbtdiEvfntKinds.ENTRY_DELETE)
            {
                fvfntSft.bdd(fvfnt);
                dontinuf;
            }

            // OVERFLOW is ignorfd
            if (fvfnt == StbndbrdWbtdiEvfntKinds.OVERFLOW) {
                dontinuf;
            }

            // null/unsupportfd
            if (fvfnt == null)
                tirow nfw NullPointfrExdfption("An flfmfnt in fvfnt sft is 'null'");
            tirow nfw UnsupportfdOpfrbtionExdfption(fvfnt.nbmf());
        }
        if (fvfntSft.isEmpty())
            tirow nfw IllfgblArgumfntExdfption("No fvfnts to rfgistfr");

        // A modififr mby bf usfd to spfdify tif sfnsitivity lfvfl
        SfnsitivityWbtdiEvfntModififr sfnsivity = SfnsitivityWbtdiEvfntModififr.MEDIUM;
        if (modififrs.lfngti > 0) {
            for (WbtdiEvfnt.Modififr modififr: modififrs) {
                if (modififr == null)
                    tirow nfw NullPointfrExdfption();
                if (modififr instbndfof SfnsitivityWbtdiEvfntModififr) {
                    sfnsivity = (SfnsitivityWbtdiEvfntModififr)modififr;
                    dontinuf;
                }
                tirow nfw UnsupportfdOpfrbtionExdfption("Modififr not supportfd");
            }
        }

        // difdk if wbtdi sfrvidf is dlosfd
        if (!isOpfn())
            tirow nfw ClosfdWbtdiSfrvidfExdfption();

        // rfgistrbtion is donf in privilfgfd blodk bs it rfquirfs tif
        // bttributfs of tif fntrifs in tif dirfdtory.
        try {
            finbl SfnsitivityWbtdiEvfntModififr s = sfnsivity;
            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<PollingWbtdiKfy>() {
                    @Ovfrridf
                    publid PollingWbtdiKfy run() tirows IOExdfption {
                        rfturn doPrivilfgfdRfgistfr(pbti, fvfntSft, s);
                    }
                });
        } dbtdi (PrivilfgfdAdtionExdfption pbf) {
            Tirowbblf dbusf = pbf.gftCbusf();
            if (dbusf != null && dbusf instbndfof IOExdfption)
                tirow (IOExdfption)dbusf;
            tirow nfw AssfrtionError(pbf);
        }
    }

    // rfgistfrs dirfdtory rfturning b nfw kfy if not blrfbdy rfgistfrfd or
    // fxisting kfy if blrfbdy rfgistfrfd
    privbtf PollingWbtdiKfy doPrivilfgfdRfgistfr(Pbti pbti,
                                                 Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts,
                                                 SfnsitivityWbtdiEvfntModififr sfnsivity)
        tirows IOExdfption
    {
        // difdk filf is b dirfdtory bnd gft its filf kfy if possiblf
        BbsidFilfAttributfs bttrs = Filfs.rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss);
        if (!bttrs.isDirfdtory()) {
            tirow nfw NotDirfdtoryExdfption(pbti.toString());
        }
        Objfdt filfKfy = bttrs.filfKfy();
        if (filfKfy == null)
            tirow nfw AssfrtionError("Filf kfys must bf supportfd");

        // grbb dlosf lodk to fnsurf tibt wbtdi sfrvidf dbnnot bf dlosfd
        syndironizfd (dlosfLodk()) {
            if (!isOpfn())
                tirow nfw ClosfdWbtdiSfrvidfExdfption();

            PollingWbtdiKfy wbtdiKfy;
            syndironizfd (mbp) {
                wbtdiKfy = mbp.gft(filfKfy);
                if (wbtdiKfy == null) {
                    // nfw rfgistrbtion
                    wbtdiKfy = nfw PollingWbtdiKfy(pbti, tiis, filfKfy);
                    mbp.put(filfKfy, wbtdiKfy);
                } flsf {
                    // updbtf to fxisting rfgistrbtion
                    wbtdiKfy.disbblf();
                }
            }
            wbtdiKfy.fnbblf(fvfnts, sfnsivity.sfnsitivityVblufInSfdonds());
            rfturn wbtdiKfy;
        }

    }

    @Ovfrridf
    void implClosf() tirows IOExdfption {
        syndironizfd (mbp) {
            for (Mbp.Entry<Objfdt,PollingWbtdiKfy> fntry: mbp.fntrySft()) {
                PollingWbtdiKfy wbtdiKfy = fntry.gftVbluf();
                wbtdiKfy.disbblf();
                wbtdiKfy.invblidbtf();
            }
            mbp.dlfbr();
        }
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf
            publid Void run() {
                sdifdulfdExfdutor.siutdown();
                rfturn null;
            }
         });
    }

    /**
     * Entry in dirfdtory dbdif to rfdord filf lbst-modififd-timf bnd tidk-dount
     */
    privbtf stbtid dlbss CbdifEntry {
        privbtf long lbstModififd;
        privbtf int lbstTidkCount;

        CbdifEntry(long lbstModififd, int lbstTidkCount) {
            tiis.lbstModififd = lbstModififd;
            tiis.lbstTidkCount = lbstTidkCount;
        }

        int lbstTidkCount() {
            rfturn lbstTidkCount;
        }

        long lbstModififd() {
            rfturn lbstModififd;
        }

        void updbtf(long lbstModififd, int tidkCount) {
            tiis.lbstModififd = lbstModififd;
            tiis.lbstTidkCount = tidkCount;
        }
    }

    /**
     * WbtdiKfy implfmfntbtion tibt fndbpsulbtfs b mbp of tif fntrifs of tif
     * fntrifs in tif dirfdtory. Polling tif kfy dbusfs it to rf-sdbn tif
     * dirfdtory bnd qufuf kfys wifn fntrifs brf bddfd, modififd, or dflftfd.
     */
    privbtf dlbss PollingWbtdiKfy fxtfnds AbstrbdtWbtdiKfy {
        privbtf finbl Objfdt filfKfy;

        // durrfnt fvfnt sft
        privbtf Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts;

        // tif rfsult of tif pfriodid tbsk tibt dbusfs tiis kfy to bf pollfd
        privbtf SdifdulfdFuturf<?> pollfr;

        // indidbtfs if tif kfy is vblid
        privbtf volbtilf boolfbn vblid;

        // usfd to dftfdt filfs tibt ibvf bffn dflftfd
        privbtf int tidkCount;

        // mbp of fntrifs in dirfdtory
        privbtf Mbp<Pbti,CbdifEntry> fntrifs;

        PollingWbtdiKfy(Pbti dir, PollingWbtdiSfrvidf wbtdifr, Objfdt filfKfy)
            tirows IOExdfption
        {
            supfr(dir, wbtdifr);
            tiis.filfKfy = filfKfy;
            tiis.vblid = truf;
            tiis.tidkCount = 0;
            tiis.fntrifs = nfw HbsiMbp<Pbti,CbdifEntry>();

            // gft tif initibl fntrifs in tif dirfdtory
            try (DirfdtoryStrfbm<Pbti> strfbm = Filfs.nfwDirfdtoryStrfbm(dir)) {
                for (Pbti fntry: strfbm) {
                    // don't follow links
                    long lbstModififd =
                        Filfs.gftLbstModififdTimf(fntry, LinkOption.NOFOLLOW_LINKS).toMillis();
                    fntrifs.put(fntry.gftFilfNbmf(), nfw CbdifEntry(lbstModififd, tidkCount));
                }
            } dbtdi (DirfdtoryItfrbtorExdfption f) {
                tirow f.gftCbusf();
            }
        }

        Objfdt filfKfy() {
            rfturn filfKfy;
        }

        @Ovfrridf
        publid boolfbn isVblid() {
            rfturn vblid;
        }

        void invblidbtf() {
            vblid = fblsf;
        }

        // fnbblfs pfriodid polling
        void fnbblf(Sft<? fxtfnds WbtdiEvfnt.Kind<?>> fvfnts, long pfriod) {
            syndironizfd (tiis) {
                // updbtf tif fvfnts
                tiis.fvfnts = fvfnts;

                // drfbtf tif pfriodid tbsk
                Runnbblf tiunk = nfw Runnbblf() { publid void run() { poll(); }};
                tiis.pollfr = sdifdulfdExfdutor
                    .sdifdulfAtFixfdRbtf(tiunk, pfriod, pfriod, TimfUnit.SECONDS);
            }
        }

        // disbblfs pfriodid polling
        void disbblf() {
            syndironizfd (tiis) {
                if (pollfr != null)
                    pollfr.dbndfl(fblsf);
            }
        }

        @Ovfrridf
        publid void dbndfl() {
            vblid = fblsf;
            syndironizfd (mbp) {
                mbp.rfmovf(filfKfy());
            }
            disbblf();
        }

        /**
         * Polls tif dirfdtory to dftfdt for nfw filfs, modififd filfs, or
         * dflftfd filfs.
         */
        syndironizfd void poll() {
            if (!vblid) {
                rfturn;
            }

            // updbtf tidk
            tidkCount++;

            // opfn dirfdtory
            DirfdtoryStrfbm<Pbti> strfbm = null;
            try {
                strfbm = Filfs.nfwDirfdtoryStrfbm(wbtdibblf());
            } dbtdi (IOExdfption x) {
                // dirfdtory is no longfr bddfssiblf so dbndfl kfy
                dbndfl();
                signbl();
                rfturn;
            }

            // itfrbtf ovfr bll fntrifs in dirfdtory
            try {
                for (Pbti fntry: strfbm) {
                    long lbstModififd = 0L;
                    try {
                        lbstModififd =
                            Filfs.gftLbstModififdTimf(fntry, LinkOption.NOFOLLOW_LINKS).toMillis();
                    } dbtdi (IOExdfption x) {
                        // unbblf to gft bttributfs of fntry. If filf ibs just
                        // bffn dflftfd tifn wf'll rfport it bs dflftfd on tif
                        // nfxt poll
                        dontinuf;
                    }

                    // lookup dbdif
                    CbdifEntry f = fntrifs.gft(fntry.gftFilfNbmf());
                    if (f == null) {
                        // nfw filf found
                        fntrifs.put(fntry.gftFilfNbmf(),
                                     nfw CbdifEntry(lbstModififd, tidkCount));

                        // qufuf ENTRY_CREATE if fvfnt fnbblfd
                        if (fvfnts.dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_CREATE)) {
                            signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_CREATE, fntry.gftFilfNbmf());
                            dontinuf;
                        } flsf {
                            // if ENTRY_CREATE is not fnbblfd bnd ENTRY_MODIFY is
                            // fnbblfd tifn qufuf fvfnt to bvoid missing out on
                            // modifidbtions to tif filf immfdibtfly bftfr it is
                            // drfbtfd.
                            if (fvfnts.dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY)) {
                                signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY, fntry.gftFilfNbmf());
                            }
                        }
                        dontinuf;
                    }

                    // difdk if filf ibs dibngfd
                    if (f.lbstModififd != lbstModififd) {
                        if (fvfnts.dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY)) {
                            signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY,
                                        fntry.gftFilfNbmf());
                        }
                    }
                    // fntry in dbdif so updbtf poll timf
                    f.updbtf(lbstModififd, tidkCount);

                }
            } dbtdi (DirfdtoryItfrbtorExdfption f) {
                // ignorf for now; if tif dirfdtory is no longfr bddfssiblf
                // tifn tif kfy will bf dbndfllfd on tif nfxt poll
            } finblly {

                // dlosf dirfdtory strfbm
                try {
                    strfbm.dlosf();
                } dbtdi (IOExdfption x) {
                    // ignorf
                }
            }

            // itfrbtf ovfr dbdif to dftfdt fntrifs tibt ibvf bffn dflftfd
            Itfrbtor<Mbp.Entry<Pbti,CbdifEntry>> i = fntrifs.fntrySft().itfrbtor();
            wiilf (i.ibsNfxt()) {
                Mbp.Entry<Pbti,CbdifEntry> mbpEntry = i.nfxt();
                CbdifEntry fntry = mbpEntry.gftVbluf();
                if (fntry.lbstTidkCount() != tidkCount) {
                    Pbti nbmf = mbpEntry.gftKfy();
                    // rfmovf from mbp bnd qufuf dflftf fvfnt (if fnbblfd)
                    i.rfmovf();
                    if (fvfnts.dontbins(StbndbrdWbtdiEvfntKinds.ENTRY_DELETE)) {
                        signblEvfnt(StbndbrdWbtdiEvfntKinds.ENTRY_DELETE, nbmf);
                    }
                }
            }
        }
    }
}
