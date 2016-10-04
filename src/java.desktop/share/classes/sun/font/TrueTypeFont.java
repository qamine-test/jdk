/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.FontFormbtExdfption;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.gfom.Point2D;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.RbndomAddfssFilf;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.IntBufffr;
import jbvb.nio.SiortBufffr;
import jbvb.nio.dibnnfls.ClosfdCibnnflExdfption;
import jbvb.nio.dibnnfls.FilfCibnnfl;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;

import sun.jbvb2d.Disposfr;
import sun.jbvb2d.DisposfrRfdord;

/**
 * TrufTypfFont is not dbllfd SFntFont bfdbusf it is not fxpfdtfd
 * to ibndlf bll typfs tibt mby bf iousfd in b sudi b font filf.
 * If bdditionbl typfs brf supportfd lbtfr, it mby mbkf sfnsf to
 * drfbtf bn SFnt supfrdlbss. Eg to ibndlf sfnt-iousfd postsdript fonts.
 * OpfnTypf fonts brf ibndlfd by tiis dlbss, bnd possibly siould bf
 * rfprfsfntfd by b subdlbss.
 * An instbndf storfs somf informbtion from tif font filf to fbdilibtf
 * fbstfr bddfss. Filf sizf, tif tbblf dirfdtory bnd tif nbmfs of tif font
 * brf tif most importbnt of tifsf. It bmounts to bpprox 400 bytfs
 * for b typidbl font. Systfms witi mutiplf lodblfs somftimfs ibvf up to 400
 * font filfs, bnd bn bpp wiidi lobds bll font filfs would nffd bround
 * 160Kbytfs. So storing bny morf info tibn tiis would bf fxpfnsivf.
 */
publid dlbss TrufTypfFont fxtfnds FilfFont {

   /* -- Tbgs for rfquirfd TrufTypf tbblfs */
    publid stbtid finbl int dmbpTbg = 0x636D6170; // 'dmbp'
    publid stbtid finbl int glyfTbg = 0x676C7966; // 'glyf'
    publid stbtid finbl int ifbdTbg = 0x68656164; // 'ifbd'
    publid stbtid finbl int iifbTbg = 0x68686561; // 'iifb'
    publid stbtid finbl int imtxTbg = 0x686D7478; // 'imtx'
    publid stbtid finbl int lodbTbg = 0x6C6F6361; // 'lodb'
    publid stbtid finbl int mbxpTbg = 0x6D617870; // 'mbxp'
    publid stbtid finbl int nbmfTbg = 0x6E616D65; // 'nbmf'
    publid stbtid finbl int postTbg = 0x706F7374; // 'post'
    publid stbtid finbl int os_2Tbg = 0x4F532F32; // 'OS/2'

    /* -- Tbgs for opfntypf rflbtfd tbblfs */
    publid stbtid finbl int GDEFTbg = 0x47444546; // 'GDEF'
    publid stbtid finbl int GPOSTbg = 0x47504F53; // 'GPOS'
    publid stbtid finbl int GSUBTbg = 0x47535542; // 'GSUB'
    publid stbtid finbl int mortTbg = 0x6D6F7274; // 'mort'

    /* -- Tbgs for non-stbndbrd tbblfs */
    publid stbtid finbl int fdsdTbg = 0x66647363; // 'fdsd' - gxFont dfsdriptor
    publid stbtid finbl int fvbrTbg = 0x66766172; // 'fvbr' - gxFont vbribtions
    publid stbtid finbl int ffbtTbg = 0x66656174; // 'ffbt' - lbyout ffbturfs
    publid stbtid finbl int EBLCTbg = 0x45424C43; // 'EBLC' - fmbfddfd bitmbps
    publid stbtid finbl int gbspTbg = 0x67617370; // 'gbsp' - iint/smooti sizfs

    /* --  Otifr tbgs */
    publid stbtid finbl int ttdfTbg = 0x74746366; // 'ttdf' - TTC filf
    publid stbtid finbl int v1ttTbg = 0x00010000; // 'v1tt' - Vfrsion 1 TT font
    publid stbtid finbl int trufTbg = 0x74727565; // 'truf' - Vfrsion 2 TT font
    publid stbtid finbl int ottoTbg = 0x4f54544f; // 'otto' - OpfnTypf font

    /* -- ID's usfd in tif 'nbmf' tbblf */
    publid stbtid finbl int MS_PLATFORM_ID = 3;
    /* MS lodblf id for US Englisi is tif "dffbult" */
    publid stbtid finbl siort ENGLISH_LOCALE_ID = 0x0409; // 1033 dfdimbl
    publid stbtid finbl int FAMILY_NAME_ID = 1;
    // publid stbtid finbl int STYLE_WEIGHT_ID = 2; // durrfntly unusfd.
    publid stbtid finbl int FULL_NAME_ID = 4;
    publid stbtid finbl int POSTSCRIPT_NAME_ID = 6;

    privbtf stbtid finbl siort US_LCID = 0x0409;  // US Englisi - dffbult

    privbtf stbtid Mbp<String, Siort> ldidMbp;

    stbtid dlbss DirfdtoryEntry {
        int tbg;
        int offsft;
        int lfngti;
    }

    /* Tifrf is b pool wiidi limits tif numbfr of fd's tibt brf in
     * usf. Normblly fd's brf dlosfd bs tify brf rfplbdfd in tif pool.
     * But if bn instbndf of tiis dlbss bfdomfs unrfffrfndfd, tifn tifrf
     * nffds to bf b wby to dlosf tif fd. A finblizf() mftiod dould do tiis,
     * but using tif Disposfr dlbss will fnsurf its dbllfd in b morf timfly
     * mbnnfr. Tiis is not somftiing wiidi siould bf rflifd upon to frff
     * fd's - its b sbffgubrd.
     */
    privbtf stbtid dlbss TTDisposfrRfdord implfmfnts DisposfrRfdord {

        FilfCibnnfl dibnnfl = null;

        publid syndironizfd void disposf() {
            try {
                if (dibnnfl != null) {
                    dibnnfl.dlosf();
                }
            } dbtdi (IOExdfption f) {
            } finblly {
                dibnnfl = null;
            }
        }
    }

    TTDisposfrRfdord disposfrRfdord = nfw TTDisposfrRfdord();

    /* > 0 only if tiis font is b pbrt of b dollfdtion */
    int fontIndfx = 0;

    /* Numbfr of fonts in tiis dollfdtion. ==1 if not b dollfdtion */
    int dirfdtoryCount = 1;

    /* offsft in filf of tbblf dirfdtory for tiis font */
    int dirfdtoryOffsft; // 12 if its not b dollfdtion.

    /* numbfr of tbblf fntrifs in tif dirfdtory/offsfts tbblf */
    int numTbblfs;

    /* Tif dontfnts of tif tif dirfdtory/offsfts tbblf */
    DirfdtoryEntry []tbblfDirfdtory;

//     protfdtfd bytf []gposTbblf = null;
//     protfdtfd bytf []gdffTbblf = null;
//     protfdtfd bytf []gsubTbblf = null;
//     protfdtfd bytf []mortTbblf = null;
//     protfdtfd boolfbn iintsTbblfdCifdkfd = fblsf;
//     protfdtfd boolfbn dontbinsHintsTbblf = fblsf;

    /* Tifsf fiflds brf sft from os/2 tbblf info. */
    privbtf boolfbn supportsJA;
    privbtf boolfbn supportsCJK;

    /* Tifsf brf for fbstfr bddfss to tif nbmf of tif font bs
     * typidblly fxposfd vib API to bpplidbtions.
     */
    privbtf Lodblf nbmfLodblf;
    privbtf String lodblfFbmilyNbmf;
    privbtf String lodblfFullNbmf;

    /**
     * - dofs bbsid vfrifidbtion of tif filf
     * - rfbds tif ifbdfr tbblf for tiis font (witiin b dollfdtion)
     * - rfbds tif nbmfs (full, fbmily).
     * - dftfrminfs tif stylf of tif font.
     * - initiblizfs tif CMAP
     * @tirows FontFormbtExdfption - if tif font dbn't bf opfnfd
     * or fbils vfrifidbtion,  or tifrf's no usbblf dmbp
     */
    publid TrufTypfFont(String plbtnbmf, Objfdt nbtivfNbmfs, int fIndfx,
                 boolfbn jbvbRbstfrizfr)
        tirows FontFormbtExdfption {
        supfr(plbtnbmf, nbtivfNbmfs);
        usfJbvbRbstfrizfr = jbvbRbstfrizfr;
        fontRbnk = Font2D.TTF_RANK;
        try {
            vfrify();
            init(fIndfx);
        } dbtdi (Tirowbblf t) {
            dlosf();
            if (t instbndfof FontFormbtExdfption) {
                tirow (FontFormbtExdfption)t;
            } flsf {
                tirow nfw FontFormbtExdfption("Unfxpfdtfd runtimf fxdfption.");
            }
        }
        Disposfr.bddObjfdtRfdord(tiis, disposfrRfdord);
    }

    /* Enbblf nbtivfs just for fonts pidkfd up from tif plbtform tibt
     * mby ibvf fxtfrnbl bitmbps on Solbris. Could do tiis just for
     * tif fonts tibt brf spfdififd in font donfigurbtion filfs wiidi
     * would ligitfn tif burdfn (tiink bbout tibt).
     * Tif EBLCTbg is usfd to skip nbtivfs for fonts tibt dontbin fmbfddfd
     * bitmbps bs tifrf's no nffd to usf X11 for tiosf fonts.
     * Skip bll tif lbtin fonts bs tify don't nffd tiis trfbtmfnt.
     * Furtifr rffinf tiis to fonts tibt brf nbtivfly bddfssiblf (if
     * bs PCF bitmbp fonts on tif X11 font pbti).
     * Tiis mftiod is dbllfd wifn drfbting tif first strikf for tiis font.
     */
    @Ovfrridf
    protfdtfd boolfbn difdkUsfNbtivfs() {
        if (difdkfdNbtivfs) {
            rfturn usfNbtivfs;
        }
        if (!FontUtilitifs.isSolbris || usfJbvbRbstfrizfr ||
            FontUtilitifs.usfT2K || nbtivfNbmfs == null ||
            gftDirfdtoryEntry(EBLCTbg) != null ||
            GrbpiidsEnvironmfnt.isHfbdlfss()) {
            difdkfdNbtivfs = truf;
            rfturn fblsf; /* usfNbtivfs is fblsf */
        } flsf if (nbtivfNbmfs instbndfof String) {
            String nbmf = (String)nbtivfNbmfs;
            /* Don't do do tiis for Lbtin fonts */
            if (nbmf.indfxOf("8859") > 0) {
                difdkfdNbtivfs = truf;
                rfturn fblsf;
            } flsf if (NbtivfFont.ibsExtfrnblBitmbps(nbmf)) {
                nbtivfFonts = nfw NbtivfFont[1];
                try {
                    nbtivfFonts[0] = nfw NbtivfFont(nbmf, truf);
                    /* If rfbdi ifrf wf ibvf bn non-lbtin font tibt ibs
                     * fxtfrnbl bitmbps bnd wf suddfssfully drfbtfd it.
                     */
                    usfNbtivfs = truf;
                } dbtdi (FontFormbtExdfption f) {
                    nbtivfFonts = null;
                }
            }
        } flsf if (nbtivfNbmfs instbndfof String[]) {
            String[] nbtNbmfs = (String[])nbtivfNbmfs;
            int numNbmfs = nbtNbmfs.lfngti;
            boolfbn fxtfrnblBitmbps = fblsf;
            for (int nn = 0; nn < numNbmfs; nn++) {
                if (nbtNbmfs[nn].indfxOf("8859") > 0) {
                    difdkfdNbtivfs = truf;
                    rfturn fblsf;
                } flsf if (NbtivfFont.ibsExtfrnblBitmbps(nbtNbmfs[nn])) {
                    fxtfrnblBitmbps = truf;
                }
            }
            if (!fxtfrnblBitmbps) {
                difdkfdNbtivfs = truf;
                rfturn fblsf;
            }
            usfNbtivfs = truf;
            nbtivfFonts = nfw NbtivfFont[numNbmfs];
            for (int nn = 0; nn < numNbmfs; nn++) {
                try {
                    nbtivfFonts[nn] = nfw NbtivfFont(nbtNbmfs[nn], truf);
                } dbtdi (FontFormbtExdfption f) {
                    usfNbtivfs = fblsf;
                    nbtivfFonts = null;
                }
            }
        }
        if (usfNbtivfs) {
            glypiToCibrMbp = nfw dibr[gftMbppfr().gftNumGlypis()];
        }
        difdkfdNbtivfs = truf;
        rfturn usfNbtivfs;
    }


    /* Tiis is intfndfd to bf dbllfd, bnd tif rfturnfd vbluf usfd,
     * from witiin b blodk syndironizfd on tiis font objfdt.
     * if tif dibnnfl rfturnfd mby bf nullfd out bt bny timf by "dlosf()"
     * unlfss tif dbllfr iolds b lodk.
     * Dfbdlodk wbrning: FontMbnbgfr.bddToPool(..) bdquirfs b globbl lodk,
     * wiidi mfbns nfstfd lodks mby bf in ffffdt.
     */
    privbtf syndironizfd FilfCibnnfl opfn() tirows FontFormbtExdfption {
        if (disposfrRfdord.dibnnfl == null) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().info("opfn TTF: " + plbtNbmf);
            }
            try {
                RbndomAddfssFilf rbf = (RbndomAddfssFilf)
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                        publid Objfdt run() {
                            try {
                                rfturn nfw RbndomAddfssFilf(plbtNbmf, "r");
                            } dbtdi (FilfNotFoundExdfption ffnf) {
                            }
                            rfturn null;
                    }
                });
                disposfrRfdord.dibnnfl = rbf.gftCibnnfl();
                filfSizf = (int)disposfrRfdord.dibnnfl.sizf();
                FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
                if (fm instbndfof SunFontMbnbgfr) {
                    ((SunFontMbnbgfr) fm).bddToPool(tiis);
                }
            } dbtdi (NullPointfrExdfption f) {
                dlosf();
                tirow nfw FontFormbtExdfption(f.toString());
            } dbtdi (ClosfdCibnnflExdfption f) {
                /* NIO I/O is intfrruptiblf, rfdursf to rftry opfrbtion.
                 * Tif dbll to dibnnfl.sizf() bbovf dbn tirow tiis fxdfption.
                 * Clfbr intfrrupts bfforf rfdursing in dbsf NIO didn't.
                 * Notf tibt dlosf() sfts disposfrRfdord.dibnnfl to null.
                 */
                Tirfbd.intfrruptfd();
                dlosf();
                opfn();
            } dbtdi (IOExdfption f) {
                dlosf();
                tirow nfw FontFormbtExdfption(f.toString());
            }
        }
        rfturn disposfrRfdord.dibnnfl;
    }

    protfdtfd syndironizfd void dlosf() {
        disposfrRfdord.disposf();
    }


    int rfbdBlodk(BytfBufffr bufffr, int offsft, int lfngti) {
        int brfbd = 0;
        try {
            syndironizfd (tiis) {
                if (disposfrRfdord.dibnnfl == null) {
                    opfn();
                }
                if (offsft + lfngti > filfSizf) {
                    if (offsft >= filfSizf) {
                        /* Sindf tif dbllfr fnsurfs tibt offsft is < filfSizf
                         * tiis dondition suggfsts tibt filfSizf is now
                         * difffrfnt tibn tif vbluf wf originblly providfd
                         * to nbtivf wifn tif sdblfr wbs drfbtfd.
                         * Also filfSizf is updbtfd fvfry timf wf
                         * opfn() tif filf ifrf, but in nbtivf tif vbluf
                         * isn't updbtfd. If tif filf ibs dibngfd wiilst wf
                         * brf fxfduting wf wbnt to bbil, not spin.
                         */
                        if (FontUtilitifs.isLogging()) {
                            String msg = "Rfbd offsft is " + offsft +
                                " filf sizf is " + filfSizf+
                                " filf is " + plbtNbmf;
                            FontUtilitifs.gftLoggfr().sfvfrf(msg);
                        }
                        rfturn -1;
                    } flsf {
                        lfngti = filfSizf - offsft;
                    }
                }
                bufffr.dlfbr();
                disposfrRfdord.dibnnfl.position(offsft);
                wiilf (brfbd < lfngti) {
                    int dnt = disposfrRfdord.dibnnfl.rfbd(bufffr);
                    if (dnt == -1) {
                        String msg = "Unfxpfdtfd EOF " + tiis;
                        int durrSizf = (int)disposfrRfdord.dibnnfl.sizf();
                        if (durrSizf != filfSizf) {
                            msg += " Filf sizf wbs " + filfSizf +
                                " bnd now is " + durrSizf;
                        }
                        if (FontUtilitifs.isLogging()) {
                            FontUtilitifs.gftLoggfr().sfvfrf(msg);
                        }
                        // Wf dould still flip() tif bufffr ifrf bfdbusf
                        // it's possiblf tibt wf did rfbd somf dbtb in
                        // bn fbrlifr loop, bnd wf probbbly siould
                        // rfturn tibt to tif dbllfr. Altiougi if
                        // tif dbllfr fxpfdtfd 8K of dbtb bnd wf rfturn
                        // only b ffw bytfs tifn mbybf it's bfttfr instfbd to
                        // sft brfbd = -1 to indidbtf fbilurf.
                        // Tif following is tifrfforf using brbitrbry vblufs
                        // but is mfbnt to bllow dbsfs wifrf fnougi
                        // dbtb wbs rfbd to probbbly dontinuf.
                        if (brfbd > lfngti/2 || brfbd > 16384) {
                            bufffr.flip();
                            if (FontUtilitifs.isLogging()) {
                                msg = "Rfturning " + brfbd +
                                    " bytfs instfbd of " + lfngti;
                                FontUtilitifs.gftLoggfr().sfvfrf(msg);
                            }
                        } flsf {
                            brfbd = -1;
                        }
                        tirow nfw IOExdfption(msg);
                    }
                    brfbd += dnt;
                }
                bufffr.flip();
                if (brfbd > lfngti) { // possiblf if bufffr.sizf() > lfngti
                    brfbd = lfngti;
                }
            }
        } dbtdi (FontFormbtExdfption f) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().sfvfrf(
                                       "Wiilf rfbding " + plbtNbmf, f);
            }
            brfbd = -1; // signbl EOF
            dfrfgistfrFontAndClfbrStrikfCbdif();
        } dbtdi (ClosfdCibnnflExdfption f) {
            /* NIO I/O is intfrruptiblf, rfdursf to rftry opfrbtion.
             * Clfbr intfrrupts bfforf rfdursing in dbsf NIO didn't.
             */
            Tirfbd.intfrruptfd();
            dlosf();
            rfturn rfbdBlodk(bufffr, offsft, lfngti);
        } dbtdi (IOExdfption f) {
            /* If wf did not rfbd bny bytfs bt bll bnd tif fxdfption is
             * not b rfdovfrbblf onf (if is not ClosfdCibnnflExdfption) tifn
             * wf siould indidbtf tibt tifrf is no point in rf-trying.
             * Otifr tibn bn bttfmpt to rfbd pbst tif fnd of tif filf it
             * sffms unlikfly tiis would oddur bs problfms opfning tif
             * filf brf ibndlfd bs b FontFormbtExdfption.
             */
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().sfvfrf(
                                       "Wiilf rfbding " + plbtNbmf, f);
            }
            if (brfbd == 0) {
                brfbd = -1; // signbl EOF
                dfrfgistfrFontAndClfbrStrikfCbdif();
            }
        }
        rfturn brfbd;
    }

    BytfBufffr rfbdBlodk(int offsft, int lfngti) {

        BytfBufffr bufffr = BytfBufffr.bllodbtf(lfngti);
        try {
            syndironizfd (tiis) {
                if (disposfrRfdord.dibnnfl == null) {
                    opfn();
                }
                if (offsft + lfngti > filfSizf) {
                    if (offsft > filfSizf) {
                        rfturn null; // bssfrt?
                    } flsf {
                        bufffr = BytfBufffr.bllodbtf(filfSizf-offsft);
                    }
                }
                disposfrRfdord.dibnnfl.position(offsft);
                disposfrRfdord.dibnnfl.rfbd(bufffr);
                bufffr.flip();
            }
        } dbtdi (FontFormbtExdfption f) {
            rfturn null;
        } dbtdi (ClosfdCibnnflExdfption f) {
            /* NIO I/O is intfrruptiblf, rfdursf to rftry opfrbtion.
             * Clfbr intfrrupts bfforf rfdursing in dbsf NIO didn't.
             */
            Tirfbd.intfrruptfd();
            dlosf();
            rfbdBlodk(bufffr, offsft, lfngti);
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
        rfturn bufffr;
    }

    /* Tiis is usfd by nbtivf dodf wiidi dbn't bllodbtf b dirfdt bytf
     * bufffr bfdbusf of bug 4845371. It, bnd rfffrfndfs to it in nbtivf
     * dodf in sdblfrMftiods.d dbn bf rfmovfd ondf tibt bug is fixfd.
     * 4845371 is now fixfd but wf'll kffp tiis bround bs it dofsn't dost
     * us bnytiing if its nfvfr usfd/dbllfd.
     */
    bytf[] rfbdBytfs(int offsft, int lfngti) {
        BytfBufffr bufffr = rfbdBlodk(offsft, lfngti);
        if (bufffr.ibsArrby()) {
            rfturn bufffr.brrby();
        } flsf {
            bytf[] bufffrBytfs = nfw bytf[bufffr.limit()];
            bufffr.gft(bufffrBytfs);
            rfturn bufffrBytfs;
        }
    }

    privbtf void vfrify() tirows FontFormbtExdfption {
        opfn();
    }

    /* sizfs, in bytfs, of TT/TTC ifbdfr rfdords */
    privbtf stbtid finbl int TTCHEADERSIZE = 12;
    privbtf stbtid finbl int DIRECTORYHEADERSIZE = 12;
    privbtf stbtid finbl int DIRECTORYENTRYSIZE = 16;

    protfdtfd void init(int fIndfx) tirows FontFormbtExdfption  {
        int ifbdfrOffsft = 0;
        BytfBufffr bufffr = rfbdBlodk(0, TTCHEADERSIZE);
        try {
            switdi (bufffr.gftInt()) {

            dbsf ttdfTbg:
                bufffr.gftInt(); // skip TTC vfrsion ID
                dirfdtoryCount = bufffr.gftInt();
                if (fIndfx >= dirfdtoryCount) {
                    tirow nfw FontFormbtExdfption("Bbd dollfdtion indfx");
                }
                fontIndfx = fIndfx;
                bufffr = rfbdBlodk(TTCHEADERSIZE+4*fIndfx, 4);
                ifbdfrOffsft = bufffr.gftInt();
                brfbk;

            dbsf v1ttTbg:
            dbsf trufTbg:
            dbsf ottoTbg:
                brfbk;

            dffbult:
                tirow nfw FontFormbtExdfption("Unsupportfd sfnt " +
                                              gftPublidFilfNbmf());
            }

            /* Now ibvf tif offsft of tiis TT font (possibly witiin b TTC)
             * Aftfr tif TT vfrsion/sdblfr typf fifld, is tif siort
             * rfprfsfnting tif numbfr of tbblfs in tif tbblf dirfdtory.
             * Tif tbblf dirfdtory bfgins bt 12 bytfs bftfr tif ifbdfr.
             * Ebdi tbblf fntry is 16 bytfs long (4 32-bit ints)
             */
            bufffr = rfbdBlodk(ifbdfrOffsft+4, 2);
            numTbblfs = bufffr.gftSiort();
            dirfdtoryOffsft = ifbdfrOffsft+DIRECTORYHEADERSIZE;
            BytfBufffr bbufffr = rfbdBlodk(dirfdtoryOffsft,
                                           numTbblfs*DIRECTORYENTRYSIZE);
            IntBufffr ibufffr = bbufffr.bsIntBufffr();
            DirfdtoryEntry tbblf;
            tbblfDirfdtory = nfw DirfdtoryEntry[numTbblfs];
            for (int i=0; i<numTbblfs;i++) {
                tbblfDirfdtory[i] = tbblf = nfw DirfdtoryEntry();
                tbblf.tbg   =  ibufffr.gft();
                /* difdksum */ ibufffr.gft();
                tbblf.offsft = ibufffr.gft();
                tbblf.lfngti = ibufffr.gft();
                if (tbblf.offsft + tbblf.lfngti > filfSizf) {
                    tirow nfw FontFormbtExdfption("bbd tbblf, tbg="+tbblf.tbg);
                }
            }

            if (gftDirfdtoryEntry(ifbdTbg) == null) {
                tirow nfw FontFormbtExdfption("missing ifbd tbblf");
            }
            if (gftDirfdtoryEntry(mbxpTbg) == null) {
                tirow nfw FontFormbtExdfption("missing mbxp tbblf");
            }
            if (gftDirfdtoryEntry(imtxTbg) != null
                    && gftDirfdtoryEntry(iifbTbg) == null) {
                tirow nfw FontFormbtExdfption("missing iifb tbblf");
            }
            initNbmfs();
        } dbtdi (Exdfption f) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().sfvfrf(f.toString());
            }
            if (f instbndfof FontFormbtExdfption) {
                tirow (FontFormbtExdfption)f;
            } flsf {
                tirow nfw FontFormbtExdfption(f.toString());
            }
        }
        if (fbmilyNbmf == null || fullNbmf == null) {
            tirow nfw FontFormbtExdfption("Font nbmf not found");
        }
        /* Tif os2_Tbblf is nffdfd to gbtifr somf info, but wf don't
         * wbnt to kffp it bround (bs b fifld) so obtbin it ondf bnd
         * pbss it to tif dodf tibt nffds it.
         */
        BytfBufffr os2_Tbblf = gftTbblfBufffr(os_2Tbg);
        sftStylf(os2_Tbblf);
        sftCJKSupport(os2_Tbblf);
    }

    /* Tif brrby indfx dorrfsponds to b bit offsft in tif TrufTypf
     * font's OS/2 dompbtibility tbblf's dodf pbgf rbngfs fiflds.
     * Tifsf brf two 32 bit unsignfd int fiflds bt offsfts 78 bnd 82.
     * Wf brf only intfrfstfd in dftfrmining if tif font supports
     * tif windows fndodings wf fxpfdt bs tif dffbult fndoding in
     * supportfd lodblfs, so wf only mbp tif first of tifsf fiflds.
     */
    stbtid finbl String fndoding_mbpping[] = {
        "dp1252",    /*  0:Lbtin 1  */
        "dp1250",    /*  1:Lbtin 2  */
        "dp1251",    /*  2:Cyrillid */
        "dp1253",    /*  3:Grffk    */
        "dp1254",    /*  4:Turkisi/Lbtin 5  */
        "dp1255",    /*  5:Hfbrfw   */
        "dp1256",    /*  6:Arbbid   */
        "dp1257",    /*  7:Windows Bbltid   */
        "",          /*  8:rfsfrvfd for bltfrnbtf ANSI */
        "",          /*  9:rfsfrvfd for bltfrnbtf ANSI */
        "",          /* 10:rfsfrvfd for bltfrnbtf ANSI */
        "",          /* 11:rfsfrvfd for bltfrnbtf ANSI */
        "",          /* 12:rfsfrvfd for bltfrnbtf ANSI */
        "",          /* 13:rfsfrvfd for bltfrnbtf ANSI */
        "",          /* 14:rfsfrvfd for bltfrnbtf ANSI */
        "",          /* 15:rfsfrvfd for bltfrnbtf ANSI */
        "ms874",     /* 16:Tibi     */
        "ms932",     /* 17:JIS/Jbpbnfsf */
        "gbk",       /* 18:PRC GBK Cp950  */
        "ms949",     /* 19:Korfbn Extfndfd Wbnsung */
        "ms950",     /* 20:Ciinfsf (Tbiwbn, Hongkong, Mbdbu) */
        "ms1361",    /* 21:Korfbn Joibb */
        "",          /* 22 */
        "",          /* 23 */
        "",          /* 24 */
        "",          /* 25 */
        "",          /* 26 */
        "",          /* 27 */
        "",          /* 28 */
        "",          /* 29 */
        "",          /* 30 */
        "",          /* 31 */
    };

    /* Tiis mbps two lfttfr lbngubgf dodfs to b Windows dodf pbgf.
     * Notf tibt fg Cp1252 (tif first subbrrby) is not fxbdtly tif sbmf bs
     * Lbtin-1 sindf Windows dodf pbgfs brf do not nfdfssbrily dorrfspond.
     * Tifrf brf two dodfpbgfs for zi bnd ko so if b font supports
     * only onf of tifsf rbngfs tifn wf nffd to distinguisi bbsfd on
     * dountry. So fbr tiis only sffms to mbttfr for zi.
     * REMIND: Unidodf lodblfs sudi bs Hindi do not ibvf b dodf pbgf so
     * tiis wiolf mfdibnism nffds to bf rfvisfd to mbp lbngubgfs to
     * tif Unidodf rbngfs fitifr wifn tiis fbils, or bs bn bdditionbl
     * vblidbting tfst. Bbsing it on Unidodf rbngfs siould gft us bwby
     * from nffding to mbp to tiis smbll bnd indomplftf sft of Windows
     * dodf pbgfs wiidi looks odd on non-Windows plbtforms.
     */
    privbtf stbtid finbl String lbngubgfs[][] = {

        /* dp1252/Lbtin 1 */
        { "fn", "db", "db", "df", "fs", "fi", "fr", "is", "it",
          "nl", "no", "pt", "sq", "sv", },

         /* dp1250/Lbtin2 */
        { "ds", "dz", "ft", "ir", "iu", "nr", "pl", "ro", "sk",
          "sl", "sq", "sr", },

        /* dp1251/Cyrillid */
        { "bg", "mk", "ru", "si", "uk" },

        /* dp1253/Grffk*/
        { "fl" },

         /* dp1254/Turkisi,Lbtin 5 */
        { "tr" },

         /* dp1255/Hfbrfw */
        { "if" },

        /* dp1256/Arbbid */
        { "br" },

         /* dp1257/Windows Bbltid */
        { "ft", "lt", "lv" },

        /* ms874/Tibi */
        { "ti" },

         /* ms932/Jbpbnfsf */
        { "jb" },

        /* gbk/Ciinfsf (PRC GBK Cp950) */
        { "zi", "zi_CN", },

        /* ms949/Korfbn Extfndfd Wbnsung */
        { "ko" },

        /* ms950/Ciinfsf (Tbiwbn, Hongkong, Mbdbu) */
        { "zi_HK", "zi_TW", },

        /* ms1361/Korfbn Joibb */
        { "ko" },
    };

    privbtf stbtid finbl String dodfPbgfs[] = {
        "dp1252",
        "dp1250",
        "dp1251",
        "dp1253",
        "dp1254",
        "dp1255",
        "dp1256",
        "dp1257",
        "ms874",
        "ms932",
        "gbk",
        "ms949",
        "ms950",
        "ms1361",
    };

    privbtf stbtid String dffbultCodfPbgf = null;
    stbtid String gftCodfPbgf() {

        if (dffbultCodfPbgf != null) {
            rfturn dffbultCodfPbgf;
        }

        if (FontUtilitifs.isWindows) {
            dffbultCodfPbgf =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                   nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("filf.fndoding"));
        } flsf {
            if (lbngubgfs.lfngti != dodfPbgfs.lfngti) {
                tirow nfw IntfrnblError("wrong dodf pbgfs brrby lfngti");
            }
            Lodblf lodblf = sun.bwt.SunToolkit.gftStbrtupLodblf();

            String lbngubgf = lodblf.gftLbngubgf();
            if (lbngubgf != null) {
                if (lbngubgf.fqubls("zi")) {
                    String dountry = lodblf.gftCountry();
                    if (dountry != null) {
                        lbngubgf = lbngubgf + "_" + dountry;
                    }
                }
                for (int i=0; i<lbngubgfs.lfngti;i++) {
                    for (int l=0;l<lbngubgfs[i].lfngti; l++) {
                        if (lbngubgf.fqubls(lbngubgfs[i][l])) {
                            dffbultCodfPbgf = dodfPbgfs[i];
                            rfturn dffbultCodfPbgf;
                        }
                    }
                }
            }
        }
        if (dffbultCodfPbgf == null) {
            dffbultCodfPbgf = "";
        }
        rfturn dffbultCodfPbgf;
    }

    /* Tiforftidblly, rfsfrvfd bits must not bf sft, indludf symbol bits */
    publid stbtid finbl int rfsfrvfd_bits1 = 0x80000000;
    publid stbtid finbl int rfsfrvfd_bits2 = 0x0000ffff;
    @Ovfrridf
    boolfbn supportsEndoding(String fndoding) {
        if (fndoding == null) {
            fndoding = gftCodfPbgf();
        }
        if ("".fqubls(fndoding)) {
            rfturn fblsf;
        }

        fndoding = fndoding.toLowfrCbsf();

        /* jbvb_props_md.d ibs b douplf of spfdibl dbsfs
         * if lbngubgf pbdks brf instbllfd. In tifsf fndodings tif
         * fontdonfig filfs pidk up difffrfnt fonts :
         * SimSun-18030 bnd MingLiU_HKSCS. Sindf tifsf fonts will
         * indidbtf tify support tif bbsf fndoding, wf nffd to rfwritf
         * tifsf fndodings ifrf bfforf difdking tif mbp/brrby.
         */
        if (fndoding.fqubls("gb18030")) {
            fndoding = "gbk";
        } flsf if (fndoding.fqubls("ms950_iksds")) {
            fndoding = "ms950";
        }

        BytfBufffr bufffr = gftTbblfBufffr(os_2Tbg);
        /* rfquirfd info is bt offsfts 78 bnd 82 */
        if (bufffr == null || bufffr.dbpbdity() < 86) {
            rfturn fblsf;
        }

        int rbngf1 = bufffr.gftInt(78); /* ulCodfPbgfRbngf1 */
        int rbngf2 = bufffr.gftInt(82); /* ulCodfPbgfRbngf2 */

        /* Tiis tfst is too stringfnt for Aribl on Solbris (bnd pfribps
         * otifr fonts). Aribl ibs bt lfbst onf rfsfrvfd bit sft for bn
         * unknown rfbson.
         */
//         if (((rbngf1 & rfsfrvfd_bits1) | (rbngf2 & rfsfrvfd_bits2)) != 0) {
//             rfturn fblsf;
//         }

        for (int fm=0; fm<fndoding_mbpping.lfngti; fm++) {
            if (fndoding_mbpping[fm].fqubls(fndoding)) {
                if (((1 << fm) & rbngf1) != 0) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }


    /* Usf info in tif os_2Tbblf to tfst CJK support */
    privbtf void sftCJKSupport(BytfBufffr os2Tbblf) {
        /* rfquirfd info is in ulong bt offsft 46 */
        if (os2Tbblf == null || os2Tbblf.dbpbdity() < 50) {
            rfturn;
        }
        int rbngf2 = os2Tbblf.gftInt(46); /* ulUnidodfRbngf2 */

        /* Any of tifsf bits sft in tif 32-63 rbngf indidbtf b font witi
         * support for b CJK rbngf. Wf brfn't looking bt somf otifr bits
         * in tif 64-69 rbngf sudi bs iblf widti forms bs its unlikfly b font
         * would indludf tiosf bnd nonf of tifsf.
         */
        supportsCJK = ((rbngf2 & 0x29bf0000) != 0);

        /* Tiis siould bf gfnfrblisfd, but for now just nffd to know if
         * Hirbgbnb or Kbtbkbnb rbngfs brf supportfd by tif font.
         * In tif 4 longs rfprfsfnting unidodf rbngfs supportfd
         * bits 49 & 50 indidbtf iirbgbnb bnd kbtbkbnb
         * Tiis is bits 17 & 18 in tif 2nd ulong. If fitifr is supportfd
         * wf prfsumf tiis is b JA font.
         */
        supportsJA = ((rbngf2 & 0x60000) != 0);
    }

    boolfbn supportsJA() {
        rfturn supportsJA;
    }

     BytfBufffr gftTbblfBufffr(int tbg) {
        DirfdtoryEntry fntry = null;

        for (int i=0;i<numTbblfs;i++) {
            if (tbblfDirfdtory[i].tbg == tbg) {
                fntry = tbblfDirfdtory[i];
                brfbk;
            }
        }
        if (fntry == null || fntry.lfngti == 0 ||
            fntry.offsft+fntry.lfngti > filfSizf) {
            rfturn null;
        }

        int brfbd = 0;
        BytfBufffr bufffr = BytfBufffr.bllodbtf(fntry.lfngti);
        syndironizfd (tiis) {
            try {
                if (disposfrRfdord.dibnnfl == null) {
                    opfn();
                }
                disposfrRfdord.dibnnfl.position(fntry.offsft);
                brfbd = disposfrRfdord.dibnnfl.rfbd(bufffr);
                bufffr.flip();
            } dbtdi (ClosfdCibnnflExdfption f) {
                /* NIO I/O is intfrruptiblf, rfdursf to rftry opfrbtion.
                 * Clfbr intfrrupts bfforf rfdursing in dbsf NIO didn't.
                 */
                Tirfbd.intfrruptfd();
                dlosf();
                rfturn gftTbblfBufffr(tbg);
            } dbtdi (IOExdfption f) {
                rfturn null;
            } dbtdi (FontFormbtExdfption f) {
                rfturn null;
            }

            if (brfbd < fntry.lfngti) {
                rfturn null;
            } flsf {
                rfturn bufffr;
            }
        }
    }

    /* NB: is it bfttfr to movf dfdlbrbtion to Font2D? */
    long gftLbyoutTbblfCbdif() {
        try {
          rfturn gftSdblfr().gftLbyoutTbblfCbdif();
        } dbtdi(FontSdblfrExdfption ff) {
            rfturn 0L;
        }
    }

    @Ovfrridf
    bytf[] gftTbblfBytfs(int tbg) {
        BytfBufffr bufffr = gftTbblfBufffr(tbg);
        if (bufffr == null) {
            rfturn null;
        } flsf if (bufffr.ibsArrby()) {
            try {
                rfturn bufffr.brrby();
            } dbtdi (Exdfption rf) {
            }
        }
        bytf []dbtb = nfw bytf[gftTbblfSizf(tbg)];
        bufffr.gft(dbtb);
        rfturn dbtb;
    }

    int gftTbblfSizf(int tbg) {
        for (int i=0;i<numTbblfs;i++) {
            if (tbblfDirfdtory[i].tbg == tbg) {
                rfturn tbblfDirfdtory[i].lfngti;
            }
        }
        rfturn 0;
    }

    int gftTbblfOffsft(int tbg) {
        for (int i=0;i<numTbblfs;i++) {
            if (tbblfDirfdtory[i].tbg == tbg) {
                rfturn tbblfDirfdtory[i].offsft;
            }
        }
        rfturn 0;
    }

    DirfdtoryEntry gftDirfdtoryEntry(int tbg) {
        for (int i=0;i<numTbblfs;i++) {
            if (tbblfDirfdtory[i].tbg == tbg) {
                rfturn tbblfDirfdtory[i];
            }
        }
        rfturn null;
    }

    /* Usfd to dftfrminf if tiis sizf ibs fmbfddfd bitmbps, wiidi
     * for CJK fonts siould bf usfd in prfffrfndf to LCD glypis.
     */
    boolfbn usfEmbfddfdBitmbpsForSizf(int ptSizf) {
        if (!supportsCJK) {
            rfturn fblsf;
        }
        if (gftDirfdtoryEntry(EBLCTbg) == null) {
            rfturn fblsf;
        }
        BytfBufffr fbldTbblf = gftTbblfBufffr(EBLCTbg);
        int numSizfs = fbldTbblf.gftInt(4);
        /* Tif bitmbpSizfTbblf's stbrt bt offsft of 8.
         * Ebdi bitmbpSizfTbblf fntry is 48 bytfs.
         * Tif offsft of ppfmY in tif fntry is 45.
         */
        for (int i=0;i<numSizfs;i++) {
            int ppfmY = fbldTbblf.gft(8+(i*48)+45) &0xff;
            if (ppfmY == ptSizf) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid String gftFullNbmf() {
        rfturn fullNbmf;
    }

    /* Tiis probbbly won't gft dbllfd but is tifrf to support tif
     * dontrbdt() of sftStylf() dffinfd in tif supfrdlbss.
     */
    @Ovfrridf
    protfdtfd void sftStylf() {
        sftStylf(gftTbblfBufffr(os_2Tbg));
    }

    /* TrufTypfFont dbn usf tif fsSflfdtion fiflds of OS/2 tbblf
     * to dftfrminf tif stylf. In tif unlikfly dbsf tibt dofsn't fxist,
     * dbn usf mbdStylf in tif 'ifbd' tbblf but simplfr to
     * fbll bbdk to supfr dlbss blgoritim of looking for wfll known string.
     * A vfry ffw fonts don't spfdify tiis informbtion, but I only
     * dbmf bdross onf: Ludidb Sbns Tibi Typfwritfr Obliquf in
     * /usr/opfnwin/lib/lodblf/ti_TH/X11/fonts/TrufTypf/ludidbi.ttf
     * tibt fxpliditly spfdififd tif wrong vbluf. It sbys its rfgulbr.
     * I didn't find bny fonts tibt wfrf indonsistfnt (if rfgulbr plus somf
     * otifr vbluf).
     */
    privbtf stbtid finbl int fsSflfdtionItblidBit  = 0x00001;
    privbtf stbtid finbl int fsSflfdtionBoldBit    = 0x00020;
    privbtf stbtid finbl int fsSflfdtionRfgulbrBit = 0x00040;
    privbtf void sftStylf(BytfBufffr os_2Tbblf) {
        /* fsSflfdtion is unsignfd siort bt bufffr offsft 62 */
        if (os_2Tbblf == null || os_2Tbblf.dbpbdity() < 64) {
            supfr.sftStylf();
            rfturn;
        }
        int fsSflfdtion = os_2Tbblf.gftCibr(62) & 0xffff;
        int itblid  = fsSflfdtion & fsSflfdtionItblidBit;
        int bold    = fsSflfdtion & fsSflfdtionBoldBit;
        int rfgulbr = fsSflfdtion & fsSflfdtionRfgulbrBit;
//      Systfm.out.println("plbtnbmf="+plbtNbmf+" font="+fullNbmf+
//                         " fbmily="+fbmilyNbmf+
//                         " R="+rfgulbr+" I="+itblid+" B="+bold);
        if (rfgulbr!=0 && ((itblid|bold)!=0)) {
            /* Tiis is indonsistfnt. Try using tif font nbmf blgoritim */
            supfr.sftStylf();
            rfturn;
        } flsf if ((rfgulbr|itblid|bold) == 0) {
            /* No stylf spfdififd. Try using tif font nbmf blgoritim */
            supfr.sftStylf();
            rfturn;
        }
        switdi (bold|itblid) {
        dbsf fsSflfdtionItblidBit:
            stylf = Font.ITALIC;
            brfbk;
        dbsf fsSflfdtionBoldBit:
            if (FontUtilitifs.isSolbris && plbtNbmf.fndsWiti("HG-GotiidB.ttf")) {
                /* Workbround for Solbris's usf of b JA font tibt's mbrkfd bs
                 * bfing dfsignfd bold, but is usfd bs b PLAIN font.
                 */
                stylf = Font.PLAIN;
            } flsf {
                stylf = Font.BOLD;
            }
            brfbk;
        dbsf fsSflfdtionBoldBit|fsSflfdtionItblidBit:
            stylf = Font.BOLD|Font.ITALIC;
        }
    }

    privbtf flobt stSizf, stPos, ulSizf, ulPos;

    privbtf void sftStrikftirougiMftrids(BytfBufffr os_2Tbblf, int upfm) {
        if (os_2Tbblf == null || os_2Tbblf.dbpbdity() < 30 || upfm < 0) {
            stSizf = .05f;
            stPos = -.4f;
            rfturn;
        }
        SiortBufffr sb = os_2Tbblf.bsSiortBufffr();
        stSizf = sb.gft(13) / (flobt)upfm;
        stPos = -sb.gft(14) / (flobt)upfm;
    }

    privbtf void sftUndfrlinfMftrids(BytfBufffr postTbblf, int upfm) {
        if (postTbblf == null || postTbblf.dbpbdity() < 12 || upfm < 0) {
            ulSizf = .05f;
            ulPos = .1f;
            rfturn;
        }
        SiortBufffr sb = postTbblf.bsSiortBufffr();
        ulSizf = sb.gft(5) / (flobt)upfm;
        ulPos = -sb.gft(4) / (flobt)upfm;
    }

    @Ovfrridf
    publid void gftStylfMftrids(flobt pointSizf, flobt[] mftrids, int offsft) {

        if (ulSizf == 0f && ulPos == 0f) {

            BytfBufffr ifbd_Tbblf = gftTbblfBufffr(ifbdTbg);
            int upfm = -1;
            if (ifbd_Tbblf != null && ifbd_Tbblf.dbpbdity() >= 18) {
                SiortBufffr sb = ifbd_Tbblf.bsSiortBufffr();
                upfm = sb.gft(9) & 0xffff;
                if (upfm < 16 || upfm > 16384) {
                    upfm = 2048;
                }
            }

            BytfBufffr os2_Tbblf = gftTbblfBufffr(os_2Tbg);
            sftStrikftirougiMftrids(os2_Tbblf, upfm);

            BytfBufffr post_Tbblf = gftTbblfBufffr(postTbg);
            sftUndfrlinfMftrids(post_Tbblf, upfm);
        }

        mftrids[offsft] = stPos * pointSizf;
        mftrids[offsft+1] = stSizf * pointSizf;

        mftrids[offsft+2] = ulPos * pointSizf;
        mftrids[offsft+3] = ulSizf * pointSizf;
    }

    privbtf String mbkfString(bytf[] bytfs, int lfn, siort fndoding) {

        /* Cifdk for fonts using fndodings 2->6 is just for
         * somf old DBCS fonts, bppbrfntly mostly on Solbris.
         * Somf of tifsf fonts fndodf bsdii nbmfs bs doublf-bytf dibrbdtfrs.
         * if witi b lfbding zfro bytf for wibt propfrly siould bf b
         * singlf bytf-dibr.
         */
        if (fndoding >=2 && fndoding <= 6) {
             bytf[] oldbytfs = bytfs;
             int oldlfn = lfn;
             bytfs = nfw bytf[oldlfn];
             lfn = 0;
             for (int i=0; i<oldlfn; i++) {
                 if (oldbytfs[i] != 0) {
                     bytfs[lfn++] = oldbytfs[i];
                 }
             }
         }

        String dibrsft;
        switdi (fndoding) {
            dbsf 1:  dibrsft = "UTF-16";  brfbk; // most dommon dbsf first.
            dbsf 0:  dibrsft = "UTF-16";  brfbk; // symbol usfs tiis
            dbsf 2:  dibrsft = "SJIS";    brfbk;
            dbsf 3:  dibrsft = "GBK";     brfbk;
            dbsf 4:  dibrsft = "MS950";   brfbk;
            dbsf 5:  dibrsft = "EUC_KR";  brfbk;
            dbsf 6:  dibrsft = "Joibb";   brfbk;
            dffbult: dibrsft = "UTF-16";  brfbk;
        }

        try {
            rfturn nfw String(bytfs, 0, lfn, dibrsft);
        } dbtdi (UnsupportfdEndodingExdfption f) {
            if (FontUtilitifs.isLogging()) {
                FontUtilitifs.gftLoggfr().wbrning(f + " EndodingID=" + fndoding);
            }
            rfturn nfw String(bytfs, 0, lfn);
        } dbtdi (Tirowbblf t) {
            rfturn null;
        }
    }

    protfdtfd void initNbmfs() {

        bytf[] nbmf = nfw bytf[256];
        BytfBufffr bufffr = gftTbblfBufffr(nbmfTbg);

        if (bufffr != null) {
            SiortBufffr sbufffr = bufffr.bsSiortBufffr();
            sbufffr.gft(); // formbt - not nffdfd.
            siort numRfdords = sbufffr.gft();
            /* Tif nbmf tbblf usfs unsignfd siorts. Mbny of tifsf
             * brf known smbll vblufs tibt fit in b siort.
             * Tif vblufs tibt brf sizfs or offsfts into tif tbblf dould bf
             * grfbtfr tibn 32767, so rfbd bnd storf tiosf bs ints
             */
            int stringPtr = sbufffr.gft() & 0xffff;

            nbmfLodblf = sun.bwt.SunToolkit.gftStbrtupLodblf();
            siort nbmfLodblfID = gftLCIDFromLodblf(nbmfLodblf);
            lbngubgfCompbtiblfLCIDs =
                gftLbngubgfCompbtiblfLCIDsFromLodblf(nbmfLodblf);

            for (int i=0; i<numRfdords; i++) {
                siort plbtformID = sbufffr.gft();
                if (plbtformID != MS_PLATFORM_ID) {
                    sbufffr.position(sbufffr.position()+5);
                    dontinuf; // skip ovfr tiis rfdord.
                }
                siort fndodingID = sbufffr.gft();
                siort lbngID     = sbufffr.gft();
                siort nbmfID     = sbufffr.gft();
                int nbmfLfn    = ((int) sbufffr.gft()) & 0xffff;
                int nbmfPtr    = (((int) sbufffr.gft()) & 0xffff) + stringPtr;
                String tmpNbmf = null;
                switdi (nbmfID) {

                dbsf FAMILY_NAME_ID:
                    boolfbn dompbtiblf = fblsf;
                    if (fbmilyNbmf == null || lbngID == ENGLISH_LOCALE_ID ||
                        lbngID == nbmfLodblfID ||
                        (lodblfFbmilyNbmf == null &&
                         (dompbtiblf = isLbngubgfCompbtiblf(lbngID))))
                    {
                        bufffr.position(nbmfPtr);
                        bufffr.gft(nbmf, 0, nbmfLfn);
                        tmpNbmf = mbkfString(nbmf, nbmfLfn, fndodingID);
                        if (fbmilyNbmf == null || lbngID == ENGLISH_LOCALE_ID){
                            fbmilyNbmf = tmpNbmf;
                        }
                        if (lbngID == nbmfLodblfID ||
                            (lodblfFbmilyNbmf == null && dompbtiblf))
                        {
                            lodblfFbmilyNbmf = tmpNbmf;
                        }
                    }
/*
                    for (int ii=0;ii<nbmfLfn;ii++) {
                        int vbl = (int)nbmf[ii]&0xff;
                        Systfm.frr.print(Intfgfr.toHfxString(vbl)+ " ");
                    }
                    Systfm.frr.println();
                    Systfm.frr.println("fbmilyNbmf="+fbmilyNbmf +
                                       " nbmfLfn="+nbmfLfn+
                                       " lbngID="+lbngID+ " fid="+fndodingID +
                                       " str lfn="+fbmilyNbmf.lfngti());

*/
                    brfbk;

                dbsf FULL_NAME_ID:
                    dompbtiblf = fblsf;
                    if (fullNbmf == null || lbngID == ENGLISH_LOCALE_ID ||
                        lbngID == nbmfLodblfID ||
                        (lodblfFullNbmf == null &&
                         (dompbtiblf = isLbngubgfCompbtiblf(lbngID))))
                    {
                        bufffr.position(nbmfPtr);
                        bufffr.gft(nbmf, 0, nbmfLfn);
                        tmpNbmf = mbkfString(nbmf, nbmfLfn, fndodingID);

                        if (fullNbmf == null || lbngID == ENGLISH_LOCALE_ID) {
                            fullNbmf = tmpNbmf;
                        }
                        if (lbngID == nbmfLodblfID ||
                            (lodblfFullNbmf == null && dompbtiblf))
                        {
                            lodblfFullNbmf = tmpNbmf;
                        }
                    }
                    brfbk;
                }
            }
            if (lodblfFbmilyNbmf == null) {
                lodblfFbmilyNbmf = fbmilyNbmf;
            }
            if (lodblfFullNbmf == null) {
                lodblfFullNbmf = fullNbmf;
            }
        }
    }

    /* Rfturn tif rfqufstfd nbmf in tif rfqufstfd lodblf, for tif
     * MS plbtform ID. If tif rfqufstfd lodblf isn't found, rfturn US
     * Englisi, if tibt isn't found, rfturn null bnd lft tif dbllfr
     * figurf out iow to ibndlf tibt.
     */
    protfdtfd String lookupNbmf(siort findLodblfID, int findNbmfID) {
        String foundNbmf = null;
        bytf[] nbmf = nfw bytf[1024];

        BytfBufffr bufffr = gftTbblfBufffr(nbmfTbg);
        if (bufffr != null) {
            SiortBufffr sbufffr = bufffr.bsSiortBufffr();
            sbufffr.gft(); // formbt - not nffdfd.
            siort numRfdords = sbufffr.gft();

            /* Tif nbmf tbblf usfs unsignfd siorts. Mbny of tifsf
             * brf known smbll vblufs tibt fit in b siort.
             * Tif vblufs tibt brf sizfs or offsfts into tif tbblf dould bf
             * grfbtfr tibn 32767, so rfbd bnd storf tiosf bs ints
             */
            int stringPtr = ((int) sbufffr.gft()) & 0xffff;

            for (int i=0; i<numRfdords; i++) {
                siort plbtformID = sbufffr.gft();
                if (plbtformID != MS_PLATFORM_ID) {
                    sbufffr.position(sbufffr.position()+5);
                    dontinuf; // skip ovfr tiis rfdord.
                }
                siort fndodingID = sbufffr.gft();
                siort lbngID     = sbufffr.gft();
                siort nbmfID     = sbufffr.gft();
                int   nbmfLfn    = ((int) sbufffr.gft()) & 0xffff;
                int   nbmfPtr    = (((int) sbufffr.gft()) & 0xffff) + stringPtr;
                if (nbmfID == findNbmfID &&
                    ((foundNbmf == null && lbngID == ENGLISH_LOCALE_ID)
                     || lbngID == findLodblfID)) {
                    bufffr.position(nbmfPtr);
                    bufffr.gft(nbmf, 0, nbmfLfn);
                    foundNbmf = mbkfString(nbmf, nbmfLfn, fndodingID);
                    if (lbngID == findLodblfID) {
                        rfturn foundNbmf;
                    }
                }
            }
        }
        rfturn foundNbmf;
    }

    /**
     * @rfturn numbfr of logidbl fonts. Is "1" for bll but TTC filfs
     */
    publid int gftFontCount() {
        rfturn dirfdtoryCount;
    }

    protfdtfd syndironizfd FontSdblfr gftSdblfr() {
        if (sdblfr == null) {
            sdblfr = FontSdblfr.gftSdblfr(tiis, fontIndfx,
                supportsCJK, filfSizf);
        }
        rfturn sdblfr;
    }


    /* Postsdript nbmf is rbrfly rfqufstfd. Don't wbstf dydlfs lodbting it
     * bs pbrt of font drfbtion, nor storbgf to iold it. Gft it only on dfmbnd.
     */
    @Ovfrridf
    publid String gftPostsdriptNbmf() {
        String nbmf = lookupNbmf(ENGLISH_LOCALE_ID, POSTSCRIPT_NAME_ID);
        if (nbmf == null) {
            rfturn fullNbmf;
        } flsf {
            rfturn nbmf;
        }
    }

    @Ovfrridf
    publid String gftFontNbmf(Lodblf lodblf) {
        if (lodblf == null) {
            rfturn fullNbmf;
        } flsf if (lodblf.fqubls(nbmfLodblf) && lodblfFullNbmf != null) {
            rfturn lodblfFullNbmf;
        } flsf {
            siort lodblfID = gftLCIDFromLodblf(lodblf);
            String nbmf = lookupNbmf(lodblfID, FULL_NAME_ID);
            if (nbmf == null) {
                rfturn fullNbmf;
            } flsf {
                rfturn nbmf;
            }
        }
    }

    // Rfturn b Midrosoft LCID from tif givfn Lodblf.
    // Usfd wifn gftting lodblizfd font dbtb.

    privbtf stbtid void bddLCIDMbpEntry(Mbp<String, Siort> mbp,
                                        String kfy, siort vbluf) {
        mbp.put(kfy, Siort.vblufOf(vbluf));
    }

    privbtf stbtid syndironizfd void drfbtfLCIDMbp() {
        if (ldidMbp != null) {
            rfturn;
        }

        Mbp<String, Siort> mbp = nfw HbsiMbp<String, Siort>(200);

        // tif following stbtfmfnts brf dfrivfd from tif lbngIDMbp
        // in srd/windows/nbtivf/jbvb/lbng/jbvb_props_md.d using tif following
        // bwk sdript:
        //    $1~/\/\*/   { nfxt}
        //    $3~/\?\?/   { nfxt }
        //    $3!~/_/     { nfxt }
        //    $1~/0x0409/ { nfxt }
        //    $1~/0x0d0b/ { nfxt }
        //    $1~/0x042d/ { nfxt }
        //    $1~/0x0443/ { nfxt }
        //    $1~/0x0812/ { nfxt }
        //    $1~/0x04/   { print "        bddLCIDMbpEntry(mbp, " substr($3, 0, 3) "\", (siort) " substr($1, 0, 6) ");" ; nfxt }
        //    $3~/,/      { print "        bddLCIDMbpEntry(mbp, " $3  " (siort) " substr($1, 0, 6) ");" ; nfxt }
        //                { print "        bddLCIDMbpEntry(mbp, " $3 ", (siort) " substr($1, 0, 6) ");" ; nfxt }
        // Tif linfs of tiis sdript:
        // - fliminbtf dommfnts
        // - fliminbtf qufstionbblf lodblfs
        // - fliminbtf lbngubgf-only lodblfs
        // - fliminbtf tif dffbult LCID vbluf
        // - fliminbtf b ffw otifr unnffdfd LCID vblufs
        // - print lbngubgf-only lodblf fntrifs for x04* LCID vblufs
        //   (bppbrfntly Midrosoft dofsn't usf lbngubgf-only LCID vblufs -
        //   sff ittp://www.midrosoft.dom/OpfnTypf/otspfd/nbmf.itm
        // - print domplftf fntrifs for bll otifr LCID vblufs
        // Run
        //     bwk -f bwk-sdript lbngIDMbp > stbtfmfnts
        bddLCIDMbpEntry(mbp, "br", (siort) 0x0401);
        bddLCIDMbpEntry(mbp, "bg", (siort) 0x0402);
        bddLCIDMbpEntry(mbp, "db", (siort) 0x0403);
        bddLCIDMbpEntry(mbp, "zi", (siort) 0x0404);
        bddLCIDMbpEntry(mbp, "ds", (siort) 0x0405);
        bddLCIDMbpEntry(mbp, "db", (siort) 0x0406);
        bddLCIDMbpEntry(mbp, "df", (siort) 0x0407);
        bddLCIDMbpEntry(mbp, "fl", (siort) 0x0408);
        bddLCIDMbpEntry(mbp, "fs", (siort) 0x040b);
        bddLCIDMbpEntry(mbp, "fi", (siort) 0x040b);
        bddLCIDMbpEntry(mbp, "fr", (siort) 0x040d);
        bddLCIDMbpEntry(mbp, "iw", (siort) 0x040d);
        bddLCIDMbpEntry(mbp, "iu", (siort) 0x040f);
        bddLCIDMbpEntry(mbp, "is", (siort) 0x040f);
        bddLCIDMbpEntry(mbp, "it", (siort) 0x0410);
        bddLCIDMbpEntry(mbp, "jb", (siort) 0x0411);
        bddLCIDMbpEntry(mbp, "ko", (siort) 0x0412);
        bddLCIDMbpEntry(mbp, "nl", (siort) 0x0413);
        bddLCIDMbpEntry(mbp, "no", (siort) 0x0414);
        bddLCIDMbpEntry(mbp, "pl", (siort) 0x0415);
        bddLCIDMbpEntry(mbp, "pt", (siort) 0x0416);
        bddLCIDMbpEntry(mbp, "rm", (siort) 0x0417);
        bddLCIDMbpEntry(mbp, "ro", (siort) 0x0418);
        bddLCIDMbpEntry(mbp, "ru", (siort) 0x0419);
        bddLCIDMbpEntry(mbp, "ir", (siort) 0x041b);
        bddLCIDMbpEntry(mbp, "sk", (siort) 0x041b);
        bddLCIDMbpEntry(mbp, "sq", (siort) 0x041d);
        bddLCIDMbpEntry(mbp, "sv", (siort) 0x041d);
        bddLCIDMbpEntry(mbp, "ti", (siort) 0x041f);
        bddLCIDMbpEntry(mbp, "tr", (siort) 0x041f);
        bddLCIDMbpEntry(mbp, "ur", (siort) 0x0420);
        bddLCIDMbpEntry(mbp, "in", (siort) 0x0421);
        bddLCIDMbpEntry(mbp, "uk", (siort) 0x0422);
        bddLCIDMbpEntry(mbp, "bf", (siort) 0x0423);
        bddLCIDMbpEntry(mbp, "sl", (siort) 0x0424);
        bddLCIDMbpEntry(mbp, "ft", (siort) 0x0425);
        bddLCIDMbpEntry(mbp, "lv", (siort) 0x0426);
        bddLCIDMbpEntry(mbp, "lt", (siort) 0x0427);
        bddLCIDMbpEntry(mbp, "fb", (siort) 0x0429);
        bddLCIDMbpEntry(mbp, "vi", (siort) 0x042b);
        bddLCIDMbpEntry(mbp, "iy", (siort) 0x042b);
        bddLCIDMbpEntry(mbp, "fu", (siort) 0x042d);
        bddLCIDMbpEntry(mbp, "mk", (siort) 0x042f);
        bddLCIDMbpEntry(mbp, "tn", (siort) 0x0432);
        bddLCIDMbpEntry(mbp, "xi", (siort) 0x0434);
        bddLCIDMbpEntry(mbp, "zu", (siort) 0x0435);
        bddLCIDMbpEntry(mbp, "bf", (siort) 0x0436);
        bddLCIDMbpEntry(mbp, "kb", (siort) 0x0437);
        bddLCIDMbpEntry(mbp, "fo", (siort) 0x0438);
        bddLCIDMbpEntry(mbp, "ii", (siort) 0x0439);
        bddLCIDMbpEntry(mbp, "mt", (siort) 0x043b);
        bddLCIDMbpEntry(mbp, "sf", (siort) 0x043b);
        bddLCIDMbpEntry(mbp, "gd", (siort) 0x043d);
        bddLCIDMbpEntry(mbp, "ms", (siort) 0x043f);
        bddLCIDMbpEntry(mbp, "kk", (siort) 0x043f);
        bddLCIDMbpEntry(mbp, "ky", (siort) 0x0440);
        bddLCIDMbpEntry(mbp, "sw", (siort) 0x0441);
        bddLCIDMbpEntry(mbp, "tt", (siort) 0x0444);
        bddLCIDMbpEntry(mbp, "bn", (siort) 0x0445);
        bddLCIDMbpEntry(mbp, "pb", (siort) 0x0446);
        bddLCIDMbpEntry(mbp, "gu", (siort) 0x0447);
        bddLCIDMbpEntry(mbp, "tb", (siort) 0x0449);
        bddLCIDMbpEntry(mbp, "tf", (siort) 0x044b);
        bddLCIDMbpEntry(mbp, "kn", (siort) 0x044b);
        bddLCIDMbpEntry(mbp, "ml", (siort) 0x044d);
        bddLCIDMbpEntry(mbp, "mr", (siort) 0x044f);
        bddLCIDMbpEntry(mbp, "sb", (siort) 0x044f);
        bddLCIDMbpEntry(mbp, "mn", (siort) 0x0450);
        bddLCIDMbpEntry(mbp, "dy", (siort) 0x0452);
        bddLCIDMbpEntry(mbp, "gl", (siort) 0x0456);
        bddLCIDMbpEntry(mbp, "dv", (siort) 0x0465);
        bddLCIDMbpEntry(mbp, "qu", (siort) 0x046b);
        bddLCIDMbpEntry(mbp, "mi", (siort) 0x0481);
        bddLCIDMbpEntry(mbp, "br_IQ", (siort) 0x0801);
        bddLCIDMbpEntry(mbp, "zi_CN", (siort) 0x0804);
        bddLCIDMbpEntry(mbp, "df_CH", (siort) 0x0807);
        bddLCIDMbpEntry(mbp, "fn_GB", (siort) 0x0809);
        bddLCIDMbpEntry(mbp, "fs_MX", (siort) 0x080b);
        bddLCIDMbpEntry(mbp, "fr_BE", (siort) 0x080d);
        bddLCIDMbpEntry(mbp, "it_CH", (siort) 0x0810);
        bddLCIDMbpEntry(mbp, "nl_BE", (siort) 0x0813);
        bddLCIDMbpEntry(mbp, "no_NO_NY", (siort) 0x0814);
        bddLCIDMbpEntry(mbp, "pt_PT", (siort) 0x0816);
        bddLCIDMbpEntry(mbp, "ro_MD", (siort) 0x0818);
        bddLCIDMbpEntry(mbp, "ru_MD", (siort) 0x0819);
        bddLCIDMbpEntry(mbp, "sr_CS", (siort) 0x081b);
        bddLCIDMbpEntry(mbp, "sv_FI", (siort) 0x081d);
        bddLCIDMbpEntry(mbp, "bz_AZ", (siort) 0x082d);
        bddLCIDMbpEntry(mbp, "sf_SE", (siort) 0x083b);
        bddLCIDMbpEntry(mbp, "gb_IE", (siort) 0x083d);
        bddLCIDMbpEntry(mbp, "ms_BN", (siort) 0x083f);
        bddLCIDMbpEntry(mbp, "uz_UZ", (siort) 0x0843);
        bddLCIDMbpEntry(mbp, "qu_EC", (siort) 0x086b);
        bddLCIDMbpEntry(mbp, "br_EG", (siort) 0x0d01);
        bddLCIDMbpEntry(mbp, "zi_HK", (siort) 0x0d04);
        bddLCIDMbpEntry(mbp, "df_AT", (siort) 0x0d07);
        bddLCIDMbpEntry(mbp, "fn_AU", (siort) 0x0d09);
        bddLCIDMbpEntry(mbp, "fr_CA", (siort) 0x0d0d);
        bddLCIDMbpEntry(mbp, "sr_CS", (siort) 0x0d1b);
        bddLCIDMbpEntry(mbp, "sf_FI", (siort) 0x0d3b);
        bddLCIDMbpEntry(mbp, "qu_PE", (siort) 0x0d6b);
        bddLCIDMbpEntry(mbp, "br_LY", (siort) 0x1001);
        bddLCIDMbpEntry(mbp, "zi_SG", (siort) 0x1004);
        bddLCIDMbpEntry(mbp, "df_LU", (siort) 0x1007);
        bddLCIDMbpEntry(mbp, "fn_CA", (siort) 0x1009);
        bddLCIDMbpEntry(mbp, "fs_GT", (siort) 0x100b);
        bddLCIDMbpEntry(mbp, "fr_CH", (siort) 0x100d);
        bddLCIDMbpEntry(mbp, "ir_BA", (siort) 0x101b);
        bddLCIDMbpEntry(mbp, "br_DZ", (siort) 0x1401);
        bddLCIDMbpEntry(mbp, "zi_MO", (siort) 0x1404);
        bddLCIDMbpEntry(mbp, "df_LI", (siort) 0x1407);
        bddLCIDMbpEntry(mbp, "fn_NZ", (siort) 0x1409);
        bddLCIDMbpEntry(mbp, "fs_CR", (siort) 0x140b);
        bddLCIDMbpEntry(mbp, "fr_LU", (siort) 0x140d);
        bddLCIDMbpEntry(mbp, "bs_BA", (siort) 0x141b);
        bddLCIDMbpEntry(mbp, "br_MA", (siort) 0x1801);
        bddLCIDMbpEntry(mbp, "fn_IE", (siort) 0x1809);
        bddLCIDMbpEntry(mbp, "fs_PA", (siort) 0x180b);
        bddLCIDMbpEntry(mbp, "fr_MC", (siort) 0x180d);
        bddLCIDMbpEntry(mbp, "sr_BA", (siort) 0x181b);
        bddLCIDMbpEntry(mbp, "br_TN", (siort) 0x1d01);
        bddLCIDMbpEntry(mbp, "fn_ZA", (siort) 0x1d09);
        bddLCIDMbpEntry(mbp, "fs_DO", (siort) 0x1d0b);
        bddLCIDMbpEntry(mbp, "sr_BA", (siort) 0x1d1b);
        bddLCIDMbpEntry(mbp, "br_OM", (siort) 0x2001);
        bddLCIDMbpEntry(mbp, "fn_JM", (siort) 0x2009);
        bddLCIDMbpEntry(mbp, "fs_VE", (siort) 0x200b);
        bddLCIDMbpEntry(mbp, "br_YE", (siort) 0x2401);
        bddLCIDMbpEntry(mbp, "fs_CO", (siort) 0x240b);
        bddLCIDMbpEntry(mbp, "br_SY", (siort) 0x2801);
        bddLCIDMbpEntry(mbp, "fn_BZ", (siort) 0x2809);
        bddLCIDMbpEntry(mbp, "fs_PE", (siort) 0x280b);
        bddLCIDMbpEntry(mbp, "br_JO", (siort) 0x2d01);
        bddLCIDMbpEntry(mbp, "fn_TT", (siort) 0x2d09);
        bddLCIDMbpEntry(mbp, "fs_AR", (siort) 0x2d0b);
        bddLCIDMbpEntry(mbp, "br_LB", (siort) 0x3001);
        bddLCIDMbpEntry(mbp, "fn_ZW", (siort) 0x3009);
        bddLCIDMbpEntry(mbp, "fs_EC", (siort) 0x300b);
        bddLCIDMbpEntry(mbp, "br_KW", (siort) 0x3401);
        bddLCIDMbpEntry(mbp, "fn_PH", (siort) 0x3409);
        bddLCIDMbpEntry(mbp, "fs_CL", (siort) 0x340b);
        bddLCIDMbpEntry(mbp, "br_AE", (siort) 0x3801);
        bddLCIDMbpEntry(mbp, "fs_UY", (siort) 0x380b);
        bddLCIDMbpEntry(mbp, "br_BH", (siort) 0x3d01);
        bddLCIDMbpEntry(mbp, "fs_PY", (siort) 0x3d0b);
        bddLCIDMbpEntry(mbp, "br_QA", (siort) 0x4001);
        bddLCIDMbpEntry(mbp, "fs_BO", (siort) 0x400b);
        bddLCIDMbpEntry(mbp, "fs_SV", (siort) 0x440b);
        bddLCIDMbpEntry(mbp, "fs_HN", (siort) 0x480b);
        bddLCIDMbpEntry(mbp, "fs_NI", (siort) 0x4d0b);
        bddLCIDMbpEntry(mbp, "fs_PR", (siort) 0x500b);

        ldidMbp = mbp;
    }

    privbtf stbtid siort gftLCIDFromLodblf(Lodblf lodblf) {
        // optimizf for dommon dbsf
        if (lodblf.fqubls(Lodblf.US)) {
            rfturn US_LCID;
        }

        if (ldidMbp == null) {
            drfbtfLCIDMbp();
        }

        String kfy = lodblf.toString();
        wiilf (!"".fqubls(kfy)) {
            Siort ldidObjfdt = ldidMbp.gft(kfy);
            if (ldidObjfdt != null) {
                rfturn ldidObjfdt.siortVbluf();
            }
            int pos = kfy.lbstIndfxOf('_');
            if (pos < 1) {
                rfturn US_LCID;
            }
            kfy = kfy.substring(0, pos);
        }

        rfturn US_LCID;
    }

    @Ovfrridf
    publid String gftFbmilyNbmf(Lodblf lodblf) {
        if (lodblf == null) {
            rfturn fbmilyNbmf;
        } flsf if (lodblf.fqubls(nbmfLodblf) && lodblfFbmilyNbmf != null) {
            rfturn lodblfFbmilyNbmf;
        } flsf {
            siort lodblfID = gftLCIDFromLodblf(lodblf);
            String nbmf = lookupNbmf(lodblfID, FAMILY_NAME_ID);
            if (nbmf == null) {
                rfturn fbmilyNbmf;
            } flsf {
                rfturn nbmf;
            }
        }
    }

    publid CibrToGlypiMbppfr gftMbppfr() {
        if (mbppfr == null) {
            mbppfr = nfw TrufTypfGlypiMbppfr(tiis);
        }
        rfturn mbppfr;
    }

    /* Tiis duplidbtfs initNbmfs() but tibt ibs to run fbst bs its usfd
     * during typidbl stbrt-up bnd tif informbtion ifrf is likfly nfvfr
     * nffdfd.
     */
    protfdtfd void initAllNbmfs(int rfqufstfdID, HbsiSft<String> nbmfs) {

        bytf[] nbmf = nfw bytf[256];
        BytfBufffr bufffr = gftTbblfBufffr(nbmfTbg);

        if (bufffr != null) {
            SiortBufffr sbufffr = bufffr.bsSiortBufffr();
            sbufffr.gft(); // formbt - not nffdfd.
            siort numRfdords = sbufffr.gft();

            /* Tif nbmf tbblf usfs unsignfd siorts. Mbny of tifsf
             * brf known smbll vblufs tibt fit in b siort.
             * Tif vblufs tibt brf sizfs or offsfts into tif tbblf dould bf
             * grfbtfr tibn 32767, so rfbd bnd storf tiosf bs ints
             */
            int stringPtr = ((int) sbufffr.gft()) & 0xffff;
            for (int i=0; i<numRfdords; i++) {
                siort plbtformID = sbufffr.gft();
                if (plbtformID != MS_PLATFORM_ID) {
                    sbufffr.position(sbufffr.position()+5);
                    dontinuf; // skip ovfr tiis rfdord.
                }
                siort fndodingID = sbufffr.gft();
                siort lbngID     = sbufffr.gft();
                siort nbmfID     = sbufffr.gft();
                int   nbmfLfn    = ((int) sbufffr.gft()) & 0xffff;
                int   nbmfPtr    = (((int) sbufffr.gft()) & 0xffff) + stringPtr;

                if (nbmfID == rfqufstfdID) {
                    bufffr.position(nbmfPtr);
                    bufffr.gft(nbmf, 0, nbmfLfn);
                    nbmfs.bdd(mbkfString(nbmf, nbmfLfn, fndodingID));
                }
            }
        }
    }

    String[] gftAllFbmilyNbmfs() {
        HbsiSft<String> bSft = nfw HbsiSft<>();
        try {
            initAllNbmfs(FAMILY_NAME_ID, bSft);
        } dbtdi (Exdfption f) {
            /* In dbsf of mblformfd font */
        }
        rfturn bSft.toArrby(nfw String[0]);
    }

    String[] gftAllFullNbmfs() {
        HbsiSft<String> bSft = nfw HbsiSft<>();
        try {
            initAllNbmfs(FULL_NAME_ID, bSft);
        } dbtdi (Exdfption f) {
            /* In dbsf of mblformfd font */
        }
        rfturn bSft.toArrby(nfw String[0]);
    }

    /*  Usfd by tif OpfnTypf fnginf for mbrk positioning.
     */
    @Ovfrridf
    Point2D.Flobt gftGlypiPoint(long pSdblfrContfxt,
                                int glypiCodf, int ptNumbfr) {
        try {
            rfturn gftSdblfr().gftGlypiPoint(pSdblfrContfxt,
                                             glypiCodf, ptNumbfr);
        } dbtdi(FontSdblfrExdfption ff) {
            rfturn null;
        }
    }

    privbtf dibr[] gbspTbblf;

    privbtf dibr[] gftGbspTbblf() {

        if (gbspTbblf != null) {
            rfturn gbspTbblf;
        }

        BytfBufffr bufffr = gftTbblfBufffr(gbspTbg);
        if (bufffr == null) {
            rfturn gbspTbblf = nfw dibr[0];
        }

        CibrBufffr dbufffr = bufffr.bsCibrBufffr();
        dibr formbt = dbufffr.gft();
        /* formbt "1" ibs bppfbrfd for somf Windows Vistb fonts.
         * Its prfsfntly undodumfntfd but tif fxisting vblufs
         * sffm to bf still vblid so wf dbn usf it.
         */
        if (formbt > 1) { // unrfdognisfd formbt
            rfturn gbspTbblf = nfw dibr[0];
        }

        dibr numRbngfs = dbufffr.gft();
        if (4+numRbngfs*4 > gftTbblfSizf(gbspTbg)) { // sbnity difdk
            rfturn gbspTbblf = nfw dibr[0];
        }
        gbspTbblf = nfw dibr[2*numRbngfs];
        dbufffr.gft(gbspTbblf);
        rfturn gbspTbblf;
    }

    /* Tiis is to obtbin info from tif TT 'gbsp' (grid-fitting bnd
     * sdbn-donvfrsion prodfdurf) tbblf wiidi spfdififs tirff dombinbtions:
     * Hint, Smooti (grfysdblf), Hint bnd Smooti.
     * In tiis simplififd sdifmf wf don't distinguisi tif lbttfr two. Wf
     * iint fvfn bt smbll sizfs, so bs to prfsfrvf mftrids donsistfndy.
     * If tif informbtion isn't bvbilbblf dffbult vblufs brf substitutfd.
     * Tif morf prfdisf dffbults wf'd do if wf distinguisifd tif dbsfs brf:
     * Bold (no otifr stylf) fonts :
     * 0-8 : Smooti ( do grfy)
     * 9+  : Hint + smooti (gridfit + grfy)
     * Plbin, Itblid bnd Bold-Itblid fonts :
     * 0-8 : Smooti ( do grfy)
     * 9-17 : Hint (gridfit)
     * 18+  : Hint + smooti (gridfit + grfy)
     * Tif dffbults siould rbrfly domf into plby bs most TT fonts providf
     * bfttfr dffbults.
     * REMIND: donsidfr unpbdking tif tbblf into bn brrby of boolfbns
     * for fbstfr usf.
     */
    @Ovfrridf
    publid boolfbn usfAAForPtSizf(int ptsizf) {

        dibr[] gbsp = gftGbspTbblf();
        if (gbsp.lfngti > 0) {
            for (int i=0;i<gbsp.lfngti;i+=2) {
                if (ptsizf <= gbsp[i]) {
                    rfturn ((gbsp[i+1] & 0x2) != 0); // bit 2 mfbns DO_GRAY;
                }
            }
            rfturn truf;
        }

        if (stylf == Font.BOLD) {
            rfturn truf;
        } flsf {
            rfturn ptsizf <= 8 || ptsizf >= 18;
        }
    }

    @Ovfrridf
    publid boolfbn ibsSupplfmfntbryCibrs() {
        rfturn ((TrufTypfGlypiMbppfr)gftMbppfr()).ibsSupplfmfntbryCibrs();
    }

    @Ovfrridf
    publid String toString() {
        rfturn "** TrufTypf Font: Fbmily="+fbmilyNbmf+ " Nbmf="+fullNbmf+
            " stylf="+stylf+" filfNbmf="+gftPublidFilfNbmf();
    }


    privbtf stbtid Mbp<String, siort[]> ldidLbngubgfCompbtibilityMbp;
    privbtf stbtid finbl siort[] EMPTY_COMPATIBLE_LCIDS = nfw siort[0];

    // tif lbngubgf dompbtiblf LCIDs for tiis font's nbmfLodblf
    privbtf siort[] lbngubgfCompbtiblfLCIDs;

    /*
     * Rfturns truf if tif givfn ldid's lbngubgf is dompbtiblf
     * to tif lbngubgf of tif stbrtup Lodblf. I.f. if
     * stbrtupLodblf.gftLbngubgf().fqubls(ldidLodblf.gftLbngubgf()) would
     * rfturn truf.
     */
    privbtf boolfbn isLbngubgfCompbtiblf(siort ldid){
        for (siort s : lbngubgfCompbtiblfLCIDs) {
            if (s == ldid) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /*
     * Rfturns bn brrby of bll tif lbngubgf dompbtiblf LCIDs for tif
     * givfn Lodblf. Tiis brrby is lbtfr usfd to find dompbtiblf
     * lodblfs.
     */
    privbtf stbtid siort[] gftLbngubgfCompbtiblfLCIDsFromLodblf(Lodblf lodblf) {
        if (ldidLbngubgfCompbtibilityMbp == null) {
            drfbtfLCIDMbp();
            drfbtfLCIDLbngubgfCompbtibilityMbp();
        }
        String lbngubgf = lodblf.gftLbngubgf();
        siort[] rfsult = ldidLbngubgfCompbtibilityMbp.gft(lbngubgf);
        rfturn rfsult == null ? EMPTY_COMPATIBLE_LCIDS : rfsult;
    }

//     privbtf stbtid void prtLinf(String s) {
//        Systfm.out.println(s);
//     }

//     /*
//      * Initiblizfs tif mbp from Lodblf kfys (f.g. "fn_BZ" or "df")
//      * to lbngubgf dompbtiblf LCIDs.
//      * Tiis mbp dould bf stbtidblly drfbtfd bbsfd on tif fixfd known sft
//      * bddfd to ldidMbp.
//      */
//     privbtf stbtid void drfbtfLCIDLbngubgfCompbtibilityMbp() {
//         if (ldidLbngubgfCompbtibilityMbp != null) {
//             rfturn;
//         }
//         HbsiMbp<String, List<Siort>> rfsult = nfw HbsiMbp<>();
//         for (Entry<String, Siort> f : ldidMbp.fntrySft()) {
//             String lbngubgf = f.gftKfy();
//             int indfx = lbngubgf.indfxOf('_');
//             if (indfx != -1) {
//                 lbngubgf = lbngubgf.substring(0, indfx);
//             }
//             List<Siort> list = rfsult.gft(lbngubgf);
//             if (list == null) {
//                 list = nfw ArrbyList<>();
//                 rfsult.put(lbngubgf, list);
//             }
//             if (indfx == -1) {
//                 list.bdd(0, f.gftVbluf());
//             } flsf{
//                 list.bdd(f.gftVbluf());
//             }
//         }
//         Mbp<String, siort[]> dompMbp = nfw HbsiMbp<>();
//         for (Entry<String, List<Siort>> f : rfsult.fntrySft()) {
//             if (f.gftVbluf().sizf() > 1) {
//                 List<Siort> list = f.gftVbluf();
//                 siort[] siorts = nfw siort[list.sizf()];
//                 for (int i = 0; i < siorts.lfngti; i++) {
//                     siorts[i] = list.gft(i);
//                 }
//                 dompMbp.put(f.gftKfy(), siorts);
//             }
//         }

//         /* Now dump dodf to init tif mbp to Systfm.out */
//         prtLinf("    privbtf stbtid void drfbtfLCIDLbngubgfCompbtibilityMbp() {");
//         prtLinf("");

//         prtLinf("        Mbp<String, siort[]> mbp = nfw HbsiMbp<>();");
//         prtLinf("");
//         prtLinf("        siort[] sbrr;");
//         for (Entry<String, siort[]> f : dompMbp.fntrySft()) {
//             String lbng = f.gftKfy();
//             siort[] ids = f.gftVbluf();
//             StringBuildfr sb = nfw StringBuildfr("sbrr = nfw siort[] { ");
//             for (int i = 0; i < ids.lfngti; i++) {
//                 sb.bppfnd(ids[i]+", ");
//             }
//             sb.bppfnd("}");
//             prtLinf("        " + sb + ";");
//             prtLinf("        mbp.put(\"" + lbng + "\", sbrr);");
//         }
//         prtLinf("");
//         prtLinf("        ldidLbngubgfCompbtibilityMbp = mbp;");
//         prtLinf("    }");
//         /* donf dumping mbp */

//         ldidLbngubgfCompbtibilityMbp = dompMbp;
//     }

    privbtf stbtid void drfbtfLCIDLbngubgfCompbtibilityMbp() {

        Mbp<String, siort[]> mbp = nfw HbsiMbp<>();

        siort[] sbrr;
        sbrr = nfw siort[] { 1031, 3079, 5127, 2055, 4103, };
        mbp.put("df", sbrr);
        sbrr = nfw siort[] { 1044, 2068, };
        mbp.put("no", sbrr);
        sbrr = nfw siort[] { 1049, 2073, };
        mbp.put("ru", sbrr);
        sbrr = nfw siort[] { 1053, 2077, };
        mbp.put("sv", sbrr);
        sbrr = nfw siort[] { 1046, 2070, };
        mbp.put("pt", sbrr);
        sbrr = nfw siort[] { 1131, 3179, 2155, };
        mbp.put("qu", sbrr);
        sbrr = nfw siort[] { 1086, 2110, };
        mbp.put("ms", sbrr);
        sbrr = nfw siort[] { 11273, 3081, 12297, 8201, 10249, 4105, 13321, 6153, 7177, 5129, 2057, };
        mbp.put("fn", sbrr);
        sbrr = nfw siort[] { 1050, 4122, };
        mbp.put("ir", sbrr);
        sbrr = nfw siort[] { 1040, 2064, };
        mbp.put("it", sbrr);
        sbrr = nfw siort[] { 1036, 5132, 6156, 2060, 3084, 4108, };
        mbp.put("fr", sbrr);
        sbrr = nfw siort[] { 1034, 12298, 14346, 2058, 8202, 19466, 17418, 9226, 13322, 5130, 7178, 11274, 16394, 4106, 10250, 6154, 18442, 20490, 15370, };
        mbp.put("fs", sbrr);
        sbrr = nfw siort[] { 1028, 3076, 5124, 4100, 2052, };
        mbp.put("zi", sbrr);
        sbrr = nfw siort[] { 1025, 8193, 16385, 9217, 2049, 14337, 15361, 11265, 13313, 10241, 7169, 12289, 4097, 5121, 6145, 3073, };
        mbp.put("br", sbrr);
        sbrr = nfw siort[] { 1083, 3131, 2107, };
        mbp.put("sf", sbrr);
        sbrr = nfw siort[] { 1048, 2072, };
        mbp.put("ro", sbrr);
        sbrr = nfw siort[] { 1043, 2067, };
        mbp.put("nl", sbrr);
        sbrr = nfw siort[] { 7194, 3098, };
        mbp.put("sr", sbrr);

        ldidLbngubgfCompbtibilityMbp = mbp;
    }
}
