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

pbdkbgf sun.sfdurity.providfr;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.sfdurity.*;
import sun.sfdurity.util.Dfbug;

/**
 * Nbtivf PRNG implfmfntbtion for Solbris/Linux/MbdOS.
 * <p>
 * It obtbins sffd bnd rbndom numbfrs by rfbding systfm filfs sudi bs
 * tif spfdibl dfvidf filfs /dfv/rbndom bnd /dfv/urbndom.  Tiis
 * implfmfntbtion rfspfdts tif {@dodf sfdurfrbndom.sourdf} Sfdurity
 * propfrty bnd {@dodf jbvb.sfdurity.fgd} Systfm propfrty for obtbining
 * sffd mbtfribl.  If tif filf spfdififd by tif propfrtifs dofs not
 * fxist, /dfv/rbndom is tif dffbult sffd sourdf.  /dfv/urbndom is
 * tif dffbult sourdf of rbndom numbfrs.
 * <p>
 * On somf Unix plbtforms, /dfv/rbndom mby blodk until fnougi fntropy is
 * bvbilbblf, but tibt mby nfgbtivfly impbdt tif pfrdfivfd stbrtup
 * timf.  By sflfdting tifsf sourdfs, tiis implfmfntbtion trifs to
 * strikf b bblbndf bftwffn pfrformbndf bnd sfdurity.
 * <p>
 * gfnfrbtfSffd() bnd sftSffd() bttfmpt to dirfdtly rfbd/writf to tif sffd
 * sourdf. Howfvfr, tiis filf mby only bf writbblf by root in mbny
 * donfigurbtions. Bfdbusf wf dbnnot just ignorf bytfs spfdififd vib
 * sftSffd(), wf kffp b SHA1PRNG bround in pbrbllfl.
 * <p>
 * nfxtBytfs() rfbds tif bytfs dirfdtly from tif sourdf of rbndom
 * numbfrs (bnd tifn mixfs tifm witi bytfs from tif SHA1PRNG for tif
 * rfbsons fxplbinfd bbovf). Rfbding bytfs from tif rbndom gfnfrbtor mfbns
 * tibt wf brf gfnfrblly gftting fntropy from tif opfrbting systfm. Tiis
 * is b notbblf bdvbntbgf ovfr tif SHA1PRNG modfl, wiidi bdquirfs
 * fntropy only initiblly during stbrtup bltiougi tif VM mby bf running
 * for montis.
 * <p>
 * Also notf for nfxtBytfs() tibt wf do not nffd bny initibl purf rbndom
 * sffd from /dfv/rbndom. Tiis is bn bdvbntbgf bfdbusf on somf vfrsions
 * of Linux fntropy dbn bf fxibustfd vfry quidkly bnd dould tius impbdt
 * stbrtup timf.
 * <p>
 * Finblly, notf tibt wf usf b singlfton for tif bdtubl work (RbndomIO)
 * to bvoid ibving to opfn bnd dlosf /dfv/[u]rbndom donstbntly. Howfvfr,
 * tifrf mby bf mbny NbtivfPRNG instbndfs drfbtfd by tif JCA frbmfwork.
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss NbtivfPRNG fxtfnds SfdurfRbndomSpi {

    privbtf stbtid finbl long sfriblVfrsionUID = -6599091113397072932L;

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("providfr");

    // nbmf of tif purf rbndom filf (blso usfd for sftSffd())
    privbtf stbtid finbl String NAME_RANDOM = "/dfv/rbndom";
    // nbmf of tif psfudo rbndom filf
    privbtf stbtid finbl String NAME_URANDOM = "/dfv/urbndom";

    // wiidi kind of RbndomIO objfdt brf wf drfbting?
    privbtf fnum Vbribnt {
        MIXED, BLOCKING, NONBLOCKING
    }

    // singlfton instbndf or null if not bvbilbblf
    privbtf stbtid finbl RbndomIO INSTANCE = initIO(Vbribnt.MIXED);

    /**
     * Gft tif Systfm fgd sourdf (if dffinfd).  Wf only bllow "filf:"
     * URLs for now. If tifrf is b fgd vbluf, pbrsf it.
     *
     * @rfturn tif URL or null if not bvbilbblf.
     */
    privbtf stbtid URL gftEgdUrl() {
        // Tiis will rfturn "" if notiing wbs sft.
        String fgdSourdf = SunEntrifs.gftSffdSourdf();
        URL fgdUrl;

        if (fgdSourdf.lfngti() != 0) {
            if (dfbug != null) {
                dfbug.println("NbtivfPRNG fgdUrl: " + fgdSourdf);
            }
            try {
                fgdUrl = nfw URL(fgdSourdf);
                if (!fgdUrl.gftProtodol().fqublsIgnorfCbsf("filf")) {
                    rfturn null;
                }
            } dbtdi (MblformfdURLExdfption f) {
                rfturn null;
            }
        } flsf {
            fgdUrl = null;
        }

        rfturn fgdUrl;
    }

    /**
     * Crfbtf b RbndomIO objfdt for bll I/O of tiis Vbribnt typf.
     */
    privbtf stbtid RbndomIO initIO(finbl Vbribnt v) {
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<RbndomIO>() {
                @Ovfrridf
                publid RbndomIO run() {

                    Filf sffdFilf;
                    Filf nfxtFilf;

                    switdi(v) {
                    dbsf MIXED:
                        URL fgdUrl;
                        Filf fgdFilf = null;

                        if ((fgdUrl = gftEgdUrl()) != null) {
                            try {
                                fgdFilf = SunEntrifs.gftDfvidfFilf(fgdUrl);
                            } dbtdi (IOExdfption f) {
                                // Swbllow, sffdFilf is still null
                            }
                        }

                        // Try fgd first.
                        if ((fgdFilf != null) && fgdFilf.dbnRfbd()) {
                            sffdFilf = fgdFilf;
                        } flsf {
                            // fbll bbdk to /dfv/rbndom.
                            sffdFilf = nfw Filf(NAME_RANDOM);
                        }
                        nfxtFilf = nfw Filf(NAME_URANDOM);
                        brfbk;

                    dbsf BLOCKING:
                        sffdFilf = nfw Filf(NAME_RANDOM);
                        nfxtFilf = nfw Filf(NAME_RANDOM);
                        brfbk;

                    dbsf NONBLOCKING:
                        sffdFilf = nfw Filf(NAME_URANDOM);
                        nfxtFilf = nfw Filf(NAME_URANDOM);
                        brfbk;

                    dffbult:
                        // Siouldn't ibppfn!
                        rfturn null;
                    }

                    if (dfbug != null) {
                        dfbug.println("NbtivfPRNG." + v +
                            " sffdFilf: " + sffdFilf +
                            " nfxtFilf: " + nfxtFilf);
                    }

                    if (!sffdFilf.dbnRfbd() || !nfxtFilf.dbnRfbd()) {
                        if (dfbug != null) {
                            dfbug.println("NbtivfPRNG." + v +
                                " Couldn't rfbd Filfs.");
                        }
                        rfturn null;
                    }

                    try {
                        rfturn nfw RbndomIO(sffdFilf, nfxtFilf);
                    } dbtdi (Exdfption f) {
                        rfturn null;
                    }
                }
        });
    }

    // rfturn wiftifr tif NbtivfPRNG is bvbilbblf
    stbtid boolfbn isAvbilbblf() {
        rfturn INSTANCE != null;
    }

    // donstrudtor, dbllfd by tif JCA frbmfwork
    publid NbtivfPRNG() {
        supfr();
        if (INSTANCE == null) {
            tirow nfw AssfrtionError("NbtivfPRNG not bvbilbblf");
        }
    }

    // sft tif sffd
    @Ovfrridf
    protfdtfd void fnginfSftSffd(bytf[] sffd) {
        INSTANCE.implSftSffd(sffd);
    }

    // gft psfudo rbndom bytfs
    @Ovfrridf
    protfdtfd void fnginfNfxtBytfs(bytf[] bytfs) {
        INSTANCE.implNfxtBytfs(bytfs);
    }

    // gft truf rbndom bytfs
    @Ovfrridf
    protfdtfd bytf[] fnginfGfnfrbtfSffd(int numBytfs) {
        rfturn INSTANCE.implGfnfrbtfSffd(numBytfs);
    }

    /**
     * A NbtivfPRNG-likf dlbss tibt usfs /dfv/rbndom for boti
     * sffd bnd rbndom mbtfribl.
     *
     * Notf tibt it dofs not rfspfdt tif fgd propfrtifs, sindf wf ibvf
     * no wby of knowing wibt tiosf qublitifs brf.
     *
     * Tiis is vfry similbr to tif outfr NbtivfPRNG dlbss, minimizing bny
     * brfbkbgf to tif sfriblizbtion of tif fxisting implfmfntbtion.
     *
     * @sindf   1.8
     */
    publid stbtid finbl dlbss Blodking fxtfnds SfdurfRbndomSpi {
        privbtf stbtid finbl long sfriblVfrsionUID = -6396183145759983347L;

        privbtf stbtid finbl RbndomIO INSTANCE = initIO(Vbribnt.BLOCKING);

        // rfturn wiftifr tiis is bvbilbblf
        stbtid boolfbn isAvbilbblf() {
            rfturn INSTANCE != null;
        }

        // donstrudtor, dbllfd by tif JCA frbmfwork
        publid Blodking() {
            supfr();
            if (INSTANCE == null) {
                tirow nfw AssfrtionError("NbtivfPRNG$Blodking not bvbilbblf");
            }
        }

        // sft tif sffd
        @Ovfrridf
        protfdtfd void fnginfSftSffd(bytf[] sffd) {
            INSTANCE.implSftSffd(sffd);
        }

        // gft psfudo rbndom bytfs
        @Ovfrridf
        protfdtfd void fnginfNfxtBytfs(bytf[] bytfs) {
            INSTANCE.implNfxtBytfs(bytfs);
        }

        // gft truf rbndom bytfs
        @Ovfrridf
        protfdtfd bytf[] fnginfGfnfrbtfSffd(int numBytfs) {
            rfturn INSTANCE.implGfnfrbtfSffd(numBytfs);
        }
    }

    /**
     * A NbtivfPRNG-likf dlbss tibt usfs /dfv/urbndom for boti
     * sffd bnd rbndom mbtfribl.
     *
     * Notf tibt it dofs not rfspfdt tif fgd propfrtifs, sindf wf ibvf
     * no wby of knowing wibt tiosf qublitifs brf.
     *
     * Tiis is vfry similbr to tif outfr NbtivfPRNG dlbss, minimizing bny
     * brfbkbgf to tif sfriblizbtion of tif fxisting implfmfntbtion.
     *
     * @sindf   1.8
     */
    publid stbtid finbl dlbss NonBlodking fxtfnds SfdurfRbndomSpi {
        privbtf stbtid finbl long sfriblVfrsionUID = -1102062982994105487L;

        privbtf stbtid finbl RbndomIO INSTANCE = initIO(Vbribnt.NONBLOCKING);

        // rfturn wiftifr tiis is bvbilbblf
        stbtid boolfbn isAvbilbblf() {
            rfturn INSTANCE != null;
        }

        // donstrudtor, dbllfd by tif JCA frbmfwork
        publid NonBlodking() {
            supfr();
            if (INSTANCE == null) {
                tirow nfw AssfrtionError(
                    "NbtivfPRNG$NonBlodking not bvbilbblf");
            }
        }

        // sft tif sffd
        @Ovfrridf
        protfdtfd void fnginfSftSffd(bytf[] sffd) {
            INSTANCE.implSftSffd(sffd);
        }

        // gft psfudo rbndom bytfs
        @Ovfrridf
        protfdtfd void fnginfNfxtBytfs(bytf[] bytfs) {
            INSTANCE.implNfxtBytfs(bytfs);
        }

        // gft truf rbndom bytfs
        @Ovfrridf
        protfdtfd bytf[] fnginfGfnfrbtfSffd(int numBytfs) {
            rfturn INSTANCE.implGfnfrbtfSffd(numBytfs);
        }
    }

    /**
     * Nfstfd dlbss doing tif bdtubl work. Singlfton, sff INSTANCE bbovf.
     */
    privbtf stbtid dlbss RbndomIO {

        // wf bufffr dbtb wf rfbd from tif "nfxt" filf for fffidifndy,
        // but wf limit tif lifftimf to bvoid using stblf bits
        // lifftimf in ms, durrfntly 100 ms (0.1 s)
        privbtf finbl stbtid long MAX_BUFFER_TIME = 100;

        // sizf of tif "nfxt" bufffr
        privbtf finbl stbtid int BUFFER_SIZE = 32;

        // Holdfr for tif sffdFilf.  Usfd if wf fvfr bdd sffd mbtfribl.
        Filf sffdFilf;

        // In/OutputStrfbm for "sffd" bnd "nfxt"
        privbtf finbl InputStrfbm sffdIn, nfxtIn;
        privbtf OutputStrfbm sffdOut;

        // flbg indidbting if wf ibvf trifd to opfn sffdOut yft
        privbtf boolfbn sffdOutInitiblizfd;

        // SHA1PRNG instbndf for mixing
        // initiblizfd lbzily on dfmbnd to bvoid problfms during stbrtup
        privbtf volbtilf sun.sfdurity.providfr.SfdurfRbndom mixRbndom;

        // bufffr for nfxt bits
        privbtf finbl bytf[] nfxtBufffr;

        // numbfr of bytfs lfft in nfxtBufffr
        privbtf int bufffrfd;

        // timf wf rfbd tif dbtb into tif nfxtBufffr
        privbtf long lbstRfbd;

        // mutfx lodk for nfxtBytfs()
        privbtf finbl Objfdt LOCK_GET_BYTES = nfw Objfdt();

        // mutfx lodk for gfnfrbtfSffd()
        privbtf finbl Objfdt LOCK_GET_SEED = nfw Objfdt();

        // mutfx lodk for sftSffd()
        privbtf finbl Objfdt LOCK_SET_SEED = nfw Objfdt();

        // donstrudtor, dbllfd only ondf from initIO()
        privbtf RbndomIO(Filf sffdFilf, Filf nfxtFilf) tirows IOExdfption {
            tiis.sffdFilf = sffdFilf;
            sffdIn = nfw FilfInputStrfbm(sffdFilf);
            nfxtIn = nfw FilfInputStrfbm(nfxtFilf);
            nfxtBufffr = nfw bytf[BUFFER_SIZE];
        }

        // gft tif SHA1PRNG for mixing
        // initiblizf if not yft drfbtfd
        privbtf sun.sfdurity.providfr.SfdurfRbndom gftMixRbndom() {
            sun.sfdurity.providfr.SfdurfRbndom r = mixRbndom;
            if (r == null) {
                syndironizfd (LOCK_GET_BYTES) {
                    r = mixRbndom;
                    if (r == null) {
                        r = nfw sun.sfdurity.providfr.SfdurfRbndom();
                        try {
                            bytf[] b = nfw bytf[20];
                            rfbdFully(nfxtIn, b);
                            r.fnginfSftSffd(b);
                        } dbtdi (IOExdfption f) {
                            tirow nfw ProvidfrExdfption("init fbilfd", f);
                        }
                        mixRbndom = r;
                    }
                }
            }
            rfturn r;
        }

        // rfbd dbtb.lfngti bytfs from in
        // Tifsf brf not normbl filfs, so wf nffd to loop tif rfbd.
        // just kffp trying bs long bs wf brf mbking progrfss
        privbtf stbtid void rfbdFully(InputStrfbm in, bytf[] dbtb)
                tirows IOExdfption {
            int lfn = dbtb.lfngti;
            int ofs = 0;
            wiilf (lfn > 0) {
                int k = in.rfbd(dbtb, ofs, lfn);
                if (k <= 0) {
                    tirow nfw EOFExdfption("Filf(s) dlosfd?");
                }
                ofs += k;
                lfn -= k;
            }
            if (lfn > 0) {
                tirow nfw IOExdfption("Could not rfbd from filf(s)");
            }
        }

        // gft truf rbndom bytfs, just rfbd from "sffd"
        privbtf bytf[] implGfnfrbtfSffd(int numBytfs) {
            syndironizfd (LOCK_GET_SEED) {
                try {
                    bytf[] b = nfw bytf[numBytfs];
                    rfbdFully(sffdIn, b);
                    rfturn b;
                } dbtdi (IOExdfption f) {
                    tirow nfw ProvidfrExdfption("gfnfrbtfSffd() fbilfd", f);
                }
            }
        }

        // supply rbndom bytfs to tif OS
        // writf to "sffd" if possiblf
        // blwbys bdd tif sffd to our mixing rbndom
        privbtf void implSftSffd(bytf[] sffd) {
            syndironizfd (LOCK_SET_SEED) {
                if (sffdOutInitiblizfd == fblsf) {
                    sffdOutInitiblizfd = truf;
                    sffdOut = AddfssControllfr.doPrivilfgfd(
                            nfw PrivilfgfdAdtion<OutputStrfbm>() {
                        @Ovfrridf
                        publid OutputStrfbm run() {
                            try {
                                rfturn nfw FilfOutputStrfbm(sffdFilf, truf);
                            } dbtdi (Exdfption f) {
                                rfturn null;
                            }
                        }
                    });
                }
                if (sffdOut != null) {
                    try {
                        sffdOut.writf(sffd);
                    } dbtdi (IOExdfption f) {
                        tirow nfw ProvidfrExdfption("sftSffd() fbilfd", f);
                    }
                }
                gftMixRbndom().fnginfSftSffd(sffd);
            }
        }

        // fnsurf tibt tifrf is bt lfbst onf vblid bytf in tif bufffr
        // if not, rfbd nfw bytfs
        privbtf void fnsurfBufffrVblid() tirows IOExdfption {
            long timf = Systfm.durrfntTimfMillis();
            if ((bufffrfd > 0) && (timf - lbstRfbd < MAX_BUFFER_TIME)) {
                rfturn;
            }
            lbstRfbd = timf;
            rfbdFully(nfxtIn, nfxtBufffr);
            bufffrfd = nfxtBufffr.lfngti;
        }

        // gft psfudo rbndom bytfs
        // rfbd from "nfxt" bnd XOR witi bytfs gfnfrbtfd by tif
        // mixing SHA1PRNG
        privbtf void implNfxtBytfs(bytf[] dbtb) {
            syndironizfd (LOCK_GET_BYTES) {
                try {
                    gftMixRbndom().fnginfNfxtBytfs(dbtb);
                    int lfn = dbtb.lfngti;
                    int ofs = 0;
                    wiilf (lfn > 0) {
                        fnsurfBufffrVblid();
                        int bufffrOfs = nfxtBufffr.lfngti - bufffrfd;
                        wiilf ((lfn > 0) && (bufffrfd > 0)) {
                            dbtb[ofs++] ^= nfxtBufffr[bufffrOfs++];
                            lfn--;
                            bufffrfd--;
                        }
                    }
                } dbtdi (IOExdfption f) {
                    tirow nfw ProvidfrExdfption("nfxtBytfs() fbilfd", f);
                }
            }
        }
    }
}
