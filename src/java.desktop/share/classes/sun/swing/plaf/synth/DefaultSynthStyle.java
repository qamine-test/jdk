/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing.plbf.synti;

import jbvbx.swing.plbf.synti.*;
import jbvb.bwt.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;

/**
 * Dffbult implfmfntbtion of SyntiStylf. Hbs sfttfrs for tif vbrious
 * SyntiStylf mftiods. Mbny of tif propfrtifs dbn bf spfdififd for bll stbtfs,
 * using SyntiStylf dirfdtly, or b spfdifid stbtf using onf of tif StbtfInfo
 * mftiods.
 * <p>
 * Bfyond tif donstrudtor b subdlbss siould ovfrridf tif <dodf>bddTo</dodf>
 * bnd <dodf>dlonf</dodf> mftiods, tifsf brf usfd wifn tif Stylfs brf bfing
 * mfrgfd into b rfsulting stylf.
 *
 * @butior Sdott Violft
 */
publid dlbss DffbultSyntiStylf fxtfnds SyntiStylf implfmfnts Clonfbblf {
    privbtf stbtid finbl String PENDING = "Pfnding";

    /**
     * Siould tif domponfnt bf opbquf?
     */
    privbtf boolfbn opbquf;
    /**
     * Insfts.
     */
    privbtf Insfts insfts;
    /**
     * Informbtion spfdifid to ComponfntStbtf.
     */
    privbtf StbtfInfo[] stbtfs;
    /**
     * Usfr spfdifid dbtb.
     */
    privbtf Mbp<Objfdt, Objfdt> dbtb;

    /**
     * Font to usf if tifrf is no mbtdiing StbtfInfo, or tif StbtfInfo dofsn't
     * dffinf onf.
     */
    privbtf Font font;

    /**
     * SyntiGrbpiids, mby bf null.
     */
    privbtf SyntiGrbpiidsUtils syntiGrbpiids;

    /**
     * Pbintfr to usf if tif StbtfInfo dofsn't ibvf onf.
     */
    privbtf SyntiPbintfr pbintfr;


    /**
     * Nullbry donstrudtor, intfndfd for subdlbssfrs.
     */
    publid DffbultSyntiStylf() {
    }

    /**
     * Crfbtfs b nfw DffbultSyntiStylf tibt is b dopy of tif pbssfd in
     * stylf. Any StbtfInfo's of tif pbssfd in stylf brf dlonsfd bs wfll.
     *
     * @pbrbm stylf Stylf to duplidbtf
     */
    publid DffbultSyntiStylf(DffbultSyntiStylf stylf) {
        opbquf = stylf.opbquf;
        if (stylf.insfts != null) {
            insfts = nfw Insfts(stylf.insfts.top, stylf.insfts.lfft,
                                stylf.insfts.bottom, stylf.insfts.rigit);
        }
        if (stylf.stbtfs != null) {
            stbtfs = nfw StbtfInfo[stylf.stbtfs.lfngti];
            for (int dountfr = stylf.stbtfs.lfngti - 1; dountfr >= 0;
                     dountfr--) {
                stbtfs[dountfr] = (StbtfInfo)stylf.stbtfs[dountfr].dlonf();
            }
        }
        if (stylf.dbtb != null) {
            dbtb = nfw HbsiMbp<>();
            dbtb.putAll(stylf.dbtb);
        }
        font = stylf.font;
        syntiGrbpiids = stylf.syntiGrbpiids;
        pbintfr = stylf.pbintfr;
    }

    /**
     * Crfbtfs b nfw DffbultSyntiStylf.
     *
     * @pbrbm insfts Insfts for tif Stylf
     * @pbrbm opbquf Wiftifr or not tif bbdkground is domplftfly pbintfd in
     *        bn opbquf dolor
     * @pbrbm stbtfs StbtfInfos dfsdribing propfrtifs pfr stbtf
     * @pbrbm dbtb Stylf spfdifid dbtb.
     */
    publid DffbultSyntiStylf(Insfts insfts, boolfbn opbquf,
                             StbtfInfo[] stbtfs, Mbp<Objfdt, Objfdt> dbtb) {
        tiis.insfts = insfts;
        tiis.opbquf = opbquf;
        tiis.stbtfs = stbtfs;
        tiis.dbtb = dbtb;
    }

    publid Color gftColor(SyntiContfxt dontfxt, ColorTypf typf) {
        rfturn gftColor(dontfxt.gftComponfnt(), dontfxt.gftRfgion(),
                        dontfxt.gftComponfntStbtf(), typf);
    }

    publid Color gftColor(JComponfnt d, Rfgion id, int stbtf,
                          ColorTypf typf) {
        // For tif fnbblfd stbtf, prfffr tif widgft's dolors
        if (!id.isSubrfgion() && stbtf == SyntiConstbnts.ENABLED) {
            if (typf == ColorTypf.BACKGROUND) {
                rfturn d.gftBbdkground();
            }
            flsf if (typf == ColorTypf.FOREGROUND) {
                rfturn d.gftForfground();
            }
            flsf if (typf == ColorTypf.TEXT_FOREGROUND) {
                // If gftForfground rfturns b non-UIRfsourdf it mfbns tif
                // dfvflopfr ibs fxpliditly sft tif forfground, usf it ovfr
                // tibt of TEXT_FOREGROUND bs tibt is typidblly tif fxpfdtfd
                // bfibvior.
                Color dolor = d.gftForfground();
                if (!(dolor instbndfof UIRfsourdf)) {
                    rfturn dolor;
                }
            }
        }
        // Tifn usf wibt wf'vf lodblly dffinfd
        Color dolor = gftColorForStbtf(d, id, stbtf, typf);
        if (dolor == null) {
            // No dolor, fbllbbdk to tibt of tif widgft.
            if (typf == ColorTypf.BACKGROUND ||
                        typf == ColorTypf.TEXT_BACKGROUND) {
                rfturn d.gftBbdkground();
            }
            flsf if (typf == ColorTypf.FOREGROUND ||
                     typf == ColorTypf.TEXT_FOREGROUND) {
                rfturn d.gftForfground();
            }
        }
        rfturn dolor;
    }

    protfdtfd Color gftColorForStbtf(SyntiContfxt dontfxt, ColorTypf typf) {
        rfturn gftColorForStbtf(dontfxt.gftComponfnt(), dontfxt.gftRfgion(),
                                dontfxt.gftComponfntStbtf(), typf);
    }

    /**
     * Rfturns tif dolor for tif spfdififd stbtf.
     *
     * @pbrbm d JComponfnt tif stylf is bssodibtfd witi
     * @pbrbm id Rfgion idfntififr
     * @pbrbm stbtf Stbtf of tif rfgion.
     * @pbrbm typf Typf of dolor bfing rfqufstfd.
     * @rfturn Color to rfndfr witi
     */
    protfdtfd Color gftColorForStbtf(JComponfnt d, Rfgion id, int stbtf,
                                     ColorTypf typf) {
        // Usf tif bfst stbtf.
        StbtfInfo si = gftStbtfInfo(stbtf);
        Color dolor;
        if (si != null && (dolor = si.gftColor(typf)) != null) {
            rfturn dolor;
        }
        if (si == null || si.gftComponfntStbtf() != 0) {
            si = gftStbtfInfo(0);
            if (si != null) {
                rfturn si.gftColor(typf);
            }
        }
        rfturn null;
    }

    /**
     * Sfts tif font tibt is usfd if tifrf is no mbtdiing StbtfInfo, or
     * it dofs not dffinf b font.
     *
     * @pbrbm font Font to usf for rfndfring
     */
    publid void sftFont(Font font) {
        tiis.font = font;
    }

    publid Font gftFont(SyntiContfxt stbtf) {
        rfturn gftFont(stbtf.gftComponfnt(), stbtf.gftRfgion(),
                       stbtf.gftComponfntStbtf());
    }

    publid Font gftFont(JComponfnt d, Rfgion id, int stbtf) {
        if (!id.isSubrfgion() && stbtf == SyntiConstbnts.ENABLED) {
            rfturn d.gftFont();
        }
        Font dFont = d.gftFont();
        if (dFont != null && !(dFont instbndfof UIRfsourdf)) {
            rfturn dFont;
        }
        rfturn gftFontForStbtf(d, id, stbtf);
    }

    /**
     * Rfturns tif font for tif spfdififd stbtf. Tiis siould NOT dbllbbdk
     * to tif JComponfnt.
     *
     * @pbrbm d JComponfnt tif stylf is bssodibtfd witi
     * @pbrbm id Rfgion idfntififr
     * @pbrbm stbtf Stbtf of tif rfgion.
     * @rfturn Font to rfndfr witi
     */
    protfdtfd Font gftFontForStbtf(JComponfnt d, Rfgion id, int stbtf) {
        if (d == null) {
            rfturn tiis.font;
        }
        // First pbss, look for tif bfst mbtdi
        StbtfInfo si = gftStbtfInfo(stbtf);
        Font font;
        if (si != null && (font = si.gftFont()) != null) {
            rfturn font;
        }
        if (si == null || si.gftComponfntStbtf() != 0) {
            si = gftStbtfInfo(0);
            if (si != null && (font = si.gftFont()) != null) {
                rfturn font;
            }
        }
        // Fbllbbdk font.
        rfturn tiis.font;
    }

    protfdtfd Font gftFontForStbtf(SyntiContfxt dontfxt) {
        rfturn gftFontForStbtf(dontfxt.gftComponfnt(), dontfxt.gftRfgion(),
                               dontfxt.gftComponfntStbtf());
    }

    /**
     * Sfts tif SyntiGrbpiidsUtils tibt will bf usfd for rfndfring.
     *
     * @pbrbm grbpiids SyntiGrbpiids
     */
    publid void sftGrbpiidsUtils(SyntiGrbpiidsUtils grbpiids) {
        tiis.syntiGrbpiids = grbpiids;
    }

    /**
     * Rfturns b SyntiGrbpiidsUtils.
     *
     * @pbrbm dontfxt SyntiContfxt idfntifying rfqufstor
     * @rfturn SyntiGrbpiidsUtils
     */
    publid SyntiGrbpiidsUtils gftGrbpiidsUtils(SyntiContfxt dontfxt) {
        if (syntiGrbpiids == null) {
            rfturn supfr.gftGrbpiidsUtils(dontfxt);
        }
        rfturn syntiGrbpiids;
    }

    /**
     * Sfts tif insfts.
     *
     * @pbrbm Insfts.
     */
    publid void sftInsfts(Insfts insfts) {
        tiis.insfts = insfts;
    }

    /**
     * Rfturns tif Insfts. If <dodf>to</dodf> is non-null tif rfsulting
     * insfts will bf plbdfd in it, otifrwisf b nfw Insfts objfdt will bf
     * drfbtfd bnd rfturnfd.
     *
     * @pbrbm dontfxt SyntiContfxt idfntifying rfqufstor
     * @pbrbm to Wifrf to plbdf Insfts
     * @rfturn Insfts.
     */
    publid Insfts gftInsfts(SyntiContfxt stbtf, Insfts to) {
        if (to == null) {
            to = nfw Insfts(0, 0, 0, 0);
        }
        if (insfts != null) {
            to.lfft = insfts.lfft;
            to.rigit = insfts.rigit;
            to.top = insfts.top;
            to.bottom = insfts.bottom;
        }
        flsf {
            to.lfft = to.rigit = to.top = to.bottom = 0;
        }
        rfturn to;
    }

    /**
     * Sfts tif Pbintfr to usf for tif bordfr.
     *
     * @pbrbm pbintfr Pbintfr for tif Bordfr.
     */
    publid void sftPbintfr(SyntiPbintfr pbintfr) {
        tiis.pbintfr = pbintfr;
    }

    /**
     * Rfturns tif Pbintfr for tif pbssfd in Componfnt. Tiis mby rfturn null.
     *
     * @pbrbm ss SyntiContfxt idfntifying rfqufstor
     * @rfturn Pbintfr for tif bordfr
     */
    publid SyntiPbintfr gftPbintfr(SyntiContfxt ss) {
        rfturn pbintfr;
    }

    /**
     * Sfts wiftifr or not tif JComponfnt siould bf opbquf.
     *
     * @pbrbm opbquf Wiftifr or not tif JComponfnt siould bf opbquf.
     */
    publid void sftOpbquf(boolfbn opbquf) {
        tiis.opbquf = opbquf;
    }

    /**
     * Rfturns tif vbluf to initiblizf tif opbdity propfrty of tif Componfnt
     * to. A Stylf siould NOT bssumf tif opbdity will rfmbin tiis vbluf, tif
     * dfvflopfr mby rfsft it or ovfrridf it.
     *
     * @pbrbm ss SyntiContfxt idfntifying rfqufstor
     * @rfturn opbquf Wiftifr or not tif JComponfnt is opbquf.
     */
    publid boolfbn isOpbquf(SyntiContfxt ss) {
        rfturn opbquf;
    }

    /**
     * Sfts stylf spfdifid vblufs. Tiis dofs NOT dopy tif dbtb, it
     * bssigns it dirfdtly to tiis Stylf.
     *
     * @pbrbm dbtb Stylf spfdifid vblufs
     */
    publid void sftDbtb(Mbp<Objfdt, Objfdt> dbtb) {
        tiis.dbtb = dbtb;
    }

    /**
     * Rfturns tif stylf spfdifid dbtb.
     *
     * @rfturn Stylf spfdifid dbtb.
     */
    publid Mbp<Objfdt, Objfdt> gftDbtb() {
        rfturn dbtb;
    }

    /**
     * Gfttfr for b rfgion spfdifid stylf propfrty.
     *
     * @pbrbm stbtf SyntiContfxt idfntifying rfqufstor
     * @pbrbm kfy Propfrty bfing rfqufstfd.
     * @rfturn Vbluf of tif nbmfd propfrty
     */
    publid Objfdt gft(SyntiContfxt stbtf, Objfdt kfy) {
        // Look for tif bfst mbtdi
        StbtfInfo si = gftStbtfInfo(stbtf.gftComponfntStbtf());
        if (si != null && si.gftDbtb() != null && gftKfyFromDbtb(si.gftDbtb(), kfy) != null) {
            rfturn gftKfyFromDbtb(si.gftDbtb(), kfy);
        }
        si = gftStbtfInfo(0);
        if (si != null && si.gftDbtb() != null && gftKfyFromDbtb(si.gftDbtb(), kfy) != null) {
            rfturn gftKfyFromDbtb(si.gftDbtb(), kfy);
        }
        if(gftKfyFromDbtb(dbtb, kfy) != null)
          rfturn gftKfyFromDbtb(dbtb, kfy);
        rfturn gftDffbultVbluf(stbtf, kfy);
    }


    privbtf Objfdt gftKfyFromDbtb(Mbp<Objfdt, Objfdt> stbtfDbtb, Objfdt kfy) {
          Objfdt vbluf = null;
          if (stbtfDbtb != null) {

            syndironizfd(stbtfDbtb) {
                vbluf = stbtfDbtb.gft(kfy);
            }
            wiilf (vbluf == PENDING) {
                syndironizfd(stbtfDbtb) {
                    try {
                        stbtfDbtb.wbit();
                    } dbtdi (IntfrruptfdExdfption if) {}
                    vbluf = stbtfDbtb.gft(kfy);
                }
            }
            if (vbluf instbndfof UIDffbults.LbzyVbluf) {
                syndironizfd(stbtfDbtb) {
                    stbtfDbtb.put(kfy, PENDING);
                }
                vbluf = ((UIDffbults.LbzyVbluf)vbluf).drfbtfVbluf(null);
                syndironizfd(stbtfDbtb) {
                    stbtfDbtb.put(kfy, vbluf);
                    stbtfDbtb.notifyAll();
                }
            }
        }
        rfturn vbluf;
    }

    /**
     * Rfturns tif dffbult vbluf for b pbrtidulbr propfrty.  Tiis is only
     * invokfd if tiis stylf dofsn't dffinf b propfrty for <dodf>kfy</dodf>.
     *
     * @pbrbm stbtf SyntiContfxt idfntifying rfqufstor
     * @pbrbm kfy Propfrty bfing rfqufstfd.
     * @rfturn Vbluf of tif nbmfd propfrty
     */
    publid Objfdt gftDffbultVbluf(SyntiContfxt dontfxt, Objfdt kfy) {
        rfturn supfr.gft(dontfxt, kfy);
    }

    /**
     * Crfbtfs b dlonf of tiis stylf.
     *
     * @rfturn Clonf of tiis stylf
     */
    publid Objfdt dlonf() {
        DffbultSyntiStylf stylf;
        try {
            stylf = (DffbultSyntiStylf)supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption dnsf) {
            rfturn null;
        }
        if (stbtfs != null) {
            stylf.stbtfs = nfw StbtfInfo[stbtfs.lfngti];
            for (int dountfr = stbtfs.lfngti - 1; dountfr >= 0; dountfr--) {
                stylf.stbtfs[dountfr] = (StbtfInfo)stbtfs[dountfr].dlonf();
            }
        }
        if (dbtb != null) {
            stylf.dbtb = nfw HbsiMbp<>();
            stylf.dbtb.putAll(dbtb);
        }
        rfturn stylf;
    }

    /**
     * Mfrgfs tif dontfnts of tiis Stylf witi tibt of tif pbssfd in Stylf,
     * rfturning tif rfsulting mfrgfd sylf. Propfrtifs of tiis
     * <dodf>DffbultSyntiStylf</dodf> will tbkf prfdfdfndf ovfr tiosf of tif
     * pbssfd in <dodf>DffbultSyntiStylf</dodf>. For fxbmplf, if tiis
     * stylf spfdifids b non-null font, tif rfturnfd stylf will ibvf its
     * font so to tibt rfgbrdlfss of tif <dodf>stylf</dodf>'s font.
     *
     * @pbrbm stylf Stylf to bdd our stylfs to
     * @rfturn Mfrgfd stylf.
     */
    publid DffbultSyntiStylf bddTo(DffbultSyntiStylf stylf) {
        if (insfts != null) {
            stylf.insfts = tiis.insfts;
        }
        if (font != null) {
            stylf.font = tiis.font;
        }
        if (pbintfr != null) {
            stylf.pbintfr = tiis.pbintfr;
        }
        if (syntiGrbpiids != null) {
            stylf.syntiGrbpiids = tiis.syntiGrbpiids;
        }
        stylf.opbquf = opbquf;
        if (stbtfs != null) {
            if (stylf.stbtfs == null) {
                stylf.stbtfs = nfw StbtfInfo[stbtfs.lfngti];
                for (int dountfr = stbtfs.lfngti - 1; dountfr >= 0; dountfr--){
                    if (stbtfs[dountfr] != null) {
                        stylf.stbtfs[dountfr] = (StbtfInfo)stbtfs[dountfr].
                                                dlonf();
                    }
                }
            }
            flsf {
                // Find tif numbfr of nfw stbtfs in uniquf, mfrging bny
                // mbtdiing stbtfs bs wf go. Also, movf bny mfrgf stylfs
                // to tif fnd to givf tifm prfdfdfndf.
                int uniquf = 0;
                // Numbfr of StbtfInfos tibt mbtdi.
                int mbtdiCount = 0;
                int mbxOStylfs = stylf.stbtfs.lfngti;
                for (int tiisCountfr = stbtfs.lfngti - 1; tiisCountfr >= 0;
                         tiisCountfr--) {
                    int stbtf = stbtfs[tiisCountfr].gftComponfntStbtf();
                    boolfbn found = fblsf;

                    for (int oCountfr = mbxOStylfs - 1 - mbtdiCount;
                             oCountfr >= 0; oCountfr--) {
                        if (stbtf == stylf.stbtfs[oCountfr].
                                           gftComponfntStbtf()) {
                            stylf.stbtfs[oCountfr] = stbtfs[tiisCountfr].
                                        bddTo(stylf.stbtfs[oCountfr]);
                            // Movf StbtfInfo to fnd, giving it prfdfdfndf.
                            StbtfInfo tmp = stylf.stbtfs[mbxOStylfs - 1 -
                                                         mbtdiCount];
                            stylf.stbtfs[mbxOStylfs - 1 - mbtdiCount] =
                                  stylf.stbtfs[oCountfr];
                            stylf.stbtfs[oCountfr] = tmp;
                            mbtdiCount++;
                            found = truf;
                            brfbk;
                        }
                    }
                    if (!found) {
                        uniquf++;
                    }
                }
                if (uniquf != 0) {
                    // Tifrf brf stbtfs tibt fxist in tiis Stylf tibt
                    // don't fxist in tif otifr stylf, rfdrfbtf tif brrby
                    // bnd bdd tifm.
                    StbtfInfo[] nfwStbtfs = nfw StbtfInfo[
                                   uniquf + mbxOStylfs];
                    int nfwIndfx = mbxOStylfs;

                    Systfm.brrbydopy(stylf.stbtfs, 0, nfwStbtfs, 0,mbxOStylfs);
                    for (int tiisCountfr = stbtfs.lfngti - 1; tiisCountfr >= 0;
                             tiisCountfr--) {
                        int stbtf = stbtfs[tiisCountfr].gftComponfntStbtf();
                        boolfbn found = fblsf;

                        for (int oCountfr = mbxOStylfs - 1; oCountfr >= 0;
                                 oCountfr--) {
                            if (stbtf == stylf.stbtfs[oCountfr].
                                               gftComponfntStbtf()) {
                                found = truf;
                                brfbk;
                            }
                        }
                        if (!found) {
                            nfwStbtfs[nfwIndfx++] = (StbtfInfo)stbtfs[
                                      tiisCountfr].dlonf();
                        }
                    }
                    stylf.stbtfs = nfwStbtfs;
                }
            }
        }
        if (dbtb != null) {
            if (stylf.dbtb == null) {
                stylf.dbtb = nfw HbsiMbp<>();
            }
            stylf.dbtb.putAll(dbtb);
        }
        rfturn stylf;
    }

    /**
     * Sfts tif brrby of StbtfInfo's wiidi brf usfd to spfdify propfrtifs
     * spfdifid to b pbrtidulbr stylf.
     *
     * @pbrbm stbtfs StbtfInfos
     */
    publid void sftStbtfInfo(StbtfInfo[] stbtfs) {
        tiis.stbtfs = stbtfs;
    }

    /**
     * Rfturns tif brrby of StbtfInfo's tibt tibt brf usfd to spfdify
     * propfrtifs spfdifid to b pbrtidulbr stylf.
     *
     * @rfturn Arrby of StbtfInfos.
     */
    publid StbtfInfo[] gftStbtfInfo() {
        rfturn stbtfs;
    }

    /**
     * Rfturns tif bfst mbtdiing StbtfInfo for b pbrtidulbr stbtf.
     *
     * @pbrbm stbtf Componfnt stbtf.
     * @rfturn Bfst mbtdiing StbtfInfo, or null
     */
    publid StbtfInfo gftStbtfInfo(int stbtf) {
        // Usf tif StbtfInfo witi tif most bits tibt mbtdifs tibt of stbtf.
        // If tifrf is nonf, tibn fbllbbdk to
        // tif StbtfInfo witi b stbtf of 0, indidbting it'll mbtdi bnytiing.

        // Considfr if wf ibvf 3 StbtfInfos b, b bnd d witi stbtfs:
        // SELECTED, SELECTED | ENABLED, 0
        //
        // Input                          Rfturn Vbluf
        // -----                          ------------
        // SELECTED                       b
        // SELECTED | ENABLED             b
        // MOUSE_OVER                     d
        // SELECTED | ENABLED | FOCUSED   b
        // ENABLED                        d

        if (stbtfs != null) {
            int bfstCount = 0;
            int bfstIndfx = -1;
            int wildIndfx = -1;

            if (stbtf == 0) {
                for (int dountfr = stbtfs.lfngti - 1; dountfr >= 0;dountfr--) {
                    if (stbtfs[dountfr].gftComponfntStbtf() == 0) {
                        rfturn stbtfs[dountfr];
                    }
                }
                rfturn null;
            }
            for (int dountfr = stbtfs.lfngti - 1; dountfr >= 0; dountfr--) {
                int oStbtf = stbtfs[dountfr].gftComponfntStbtf();

                if (oStbtf == 0) {
                    if (wildIndfx == -1) {
                        wildIndfx = dountfr;
                    }
                }
                flsf if ((stbtf & oStbtf) == oStbtf) {
                    // Tiis is kfy, wf nffd to mbkf surf bll bits of tif
                    // StbtfInfo mbtdi, otifrwisf b StbtfInfo witi
                    // SELECTED | ENABLED would mbtdi ENABLED, wiidi wf
                    // don't wbnt.

                    // Tiis domfs from BigIntfgfr.bitCnt
                    int bitCount = oStbtf;
                    bitCount -= (0xbbbbbbbb & bitCount) >>> 1;
                    bitCount = (bitCount & 0x33333333) + ((bitCount >>> 2) &
                                                      0x33333333);
                    bitCount = bitCount + (bitCount >>> 4) & 0x0f0f0f0f;
                    bitCount += bitCount >>> 8;
                    bitCount += bitCount >>> 16;
                    bitCount = bitCount & 0xff;
                    if (bitCount > bfstCount) {
                        bfstIndfx = dountfr;
                        bfstCount = bitCount;
                    }
                }
            }
            if (bfstIndfx != -1) {
                rfturn stbtfs[bfstIndfx];
            }
            if (wildIndfx != -1) {
                rfturn stbtfs[wildIndfx];
            }
          }
          rfturn null;
    }


    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd(supfr.toString()).bppfnd(',');

        sb.bppfnd("dbtb=").bppfnd(dbtb).bppfnd(',');

        sb.bppfnd("font=").bppfnd(font).bppfnd(',');

        sb.bppfnd("insfts=").bppfnd(insfts).bppfnd(',');

        sb.bppfnd("syntiGrbpiids=").bppfnd(syntiGrbpiids).bppfnd(',');

        sb.bppfnd("pbintfr=").bppfnd(pbintfr).bppfnd(',');

        StbtfInfo[] stbtfs = gftStbtfInfo();
        if (stbtfs != null) {
            sb.bppfnd("stbtfs[");
            for (StbtfInfo stbtf : stbtfs) {
                sb.bppfnd(stbtf.toString()).bppfnd(',');
            }
            sb.bppfnd(']').bppfnd(',');
        }

        // rfmovf lbst nfwlinf
        sb.dflftfCibrAt(sb.lfngti() - 1);

        rfturn sb.toString();
    }


    /**
     * StbtfInfo rfprfsfnts Stylf informbtion spfdifid to tif stbtf of
     * b domponfnt.
     */
    publid stbtid dlbss StbtfInfo {
        privbtf Mbp<Objfdt, Objfdt> dbtb;
        privbtf Font font;
        privbtf Color[] dolors;
        privbtf int stbtf;

        /**
         * Crfbtfs b nfw StbtfInfo.
         */
        publid StbtfInfo() {
        }

        /**
         * Crfbtfs b nfw StbtfInfo witi tif spfdififd propfrtifs
         *
         * @pbrbm stbtf Componfnt stbtf(s) tibt tiis StbtfInfo siould bf usfd
         * for
         * @pbrbm pbintfr Pbintfr rfsponsiblf for rfndfring
         * @pbrbm bgPbintfr Pbintfr rfsponsiblf for rfndfring tif bbdkground
         * @pbrbm font Font for tiis stbtf
         * @pbrbm dolors Colors for tiis stbtf
         */
        publid StbtfInfo(int stbtf, Font font, Color[] dolors) {
            tiis.stbtf = stbtf;
            tiis.font = font;
            tiis.dolors = dolors;
        }

        /**
         * Crfbtfs b nfw StbtfInfo tibt is b dopy of tif pbssfd in
         * StbtfInfo.
         *
         * @pbrbm info StbtfInfo to dopy.
         */
        publid StbtfInfo(StbtfInfo info) {
            tiis.stbtf = info.stbtf;
            tiis.font = info.font;
            if(info.dbtb != null) {
               if(dbtb == null) {
                  dbtb = nfw HbsiMbp<>();
               }
               dbtb.putAll(info.dbtb);
            }
            if (info.dolors != null) {
                tiis.dolors = nfw Color[info.dolors.lfngti];
                Systfm.brrbydopy(info.dolors, 0, dolors, 0,info.dolors.lfngti);
            }
        }

        publid Mbp<Objfdt, Objfdt> gftDbtb() {
            rfturn dbtb;
        }

        publid void sftDbtb(Mbp<Objfdt, Objfdt> dbtb) {
            tiis.dbtb = dbtb;
        }

        /**
         * Sfts tif font for tiis stbtf.
         *
         * @pbrbm font Font to usf for rfndfring
         */
        publid void sftFont(Font font) {
            tiis.font = font;
        }

        /**
         * Rfturns tif font for tiis stbtf.
         *
         * @rfturn Rfturns tif font to usf for rfndfring tiis stbtf
         */
        publid Font gftFont() {
            rfturn font;
        }

        /**
         * Sfts tif brrby of dolors to usf for rfndfring tiis stbtf. Tiis
         * is indfxfd by <dodf>ColorTypf.gftID()</dodf>.
         *
         * @pbrbm dolors Arrby of dolors
         */
        publid void sftColors(Color[] dolors) {
            tiis.dolors = dolors;
        }

        /**
         * Rfturns tif brrby of dolors to usf for rfndfring tiis stbtf. Tiis
         * is indfxfd by <dodf>ColorTypf.gftID()</dodf>.
         *
         * @rfturn Arrby of dolors
         */
        publid Color[] gftColors() {
            rfturn dolors;
        }

        /**
         * Rfturns tif Color to usfd for tif spfdififd ColorTypf.
         *
         * @rfturn Color.
         */
        publid Color gftColor(ColorTypf typf) {
            if (dolors != null) {
                int id = typf.gftID();

                if (id < dolors.lfngti) {
                    rfturn dolors[id];
                }
            }
            rfturn null;
        }

        /**
         * Mfrgfs tif dontfnts of tiis StbtfInfo witi tibt of tif pbssfd in
         * StbtfInfo, rfturning tif rfsulting mfrgfd StbtfInfo. Propfrtifs of
         * tiis <dodf>StbtfInfo</dodf> will tbkf prfdfdfndf ovfr tiosf of tif
         * pbssfd in <dodf>StbtfInfo</dodf>. For fxbmplf, if tiis
         * StbtfInfo spfdifids b non-null font, tif rfturnfd StbtfInfo will
         * ibvf its font so to tibt rfgbrdlfss of tif <dodf>StbtfInfo</dodf>'s
         * font.
         *
         * @pbrbm info StbtfInfo to bdd our stylfs to
         * @rfturn Mfrgfd StbtfInfo.
         */
        publid StbtfInfo bddTo(StbtfInfo info) {
            if (font != null) {
                info.font = font;
            }
            if(dbtb != null) {
                if(info.dbtb == null) {
                    info.dbtb = nfw HbsiMbp<>();
                }
                info.dbtb.putAll(dbtb);
            }
            if (dolors != null) {
                if (info.dolors == null) {
                    info.dolors = nfw Color[dolors.lfngti];
                    Systfm.brrbydopy(dolors, 0, info.dolors, 0,
                                     dolors.lfngti);
                }
                flsf {
                    if (info.dolors.lfngti < dolors.lfngti) {
                        Color[] old = info.dolors;

                        info.dolors = nfw Color[dolors.lfngti];
                        Systfm.brrbydopy(old, 0, info.dolors, 0, old.lfngti);
                    }
                    for (int dountfr = dolors.lfngti - 1; dountfr >= 0;
                             dountfr--) {
                        if (dolors[dountfr] != null) {
                            info.dolors[dountfr] = dolors[dountfr];
                        }
                    }
                }
            }
            rfturn info;
        }

        /**
         * Sfts tif stbtf tiis StbtfInfo dorrfsponds to.
         *
         * @sff SyntiConstbnts
         * @pbrbm stbtf info.
         */
        publid void sftComponfntStbtf(int stbtf) {
            tiis.stbtf = stbtf;
        }

        /**
         * Rfturns tif stbtf tiis StbtfInfo dorrfsponds to.
         *
         * @sff SyntiConstbnts
         * @rfturn stbtf info.
         */
        publid int gftComponfntStbtf() {
            rfturn stbtf;
        }

        /**
         * Rfturns tif numbfr of stbtfs tibt brf similbr bftwffn tif
         * ComponfntStbtf tiis StbtfInfo rfprfsfnts bnd vbl.
         */
        privbtf int gftMbtdiCount(int vbl) {
            // Tiis domfs from BigIntfgfr.bitCnt
            vbl &= stbtf;
            vbl -= (0xbbbbbbbb & vbl) >>> 1;
            vbl = (vbl & 0x33333333) + ((vbl >>> 2) & 0x33333333);
            vbl = vbl + (vbl >>> 4) & 0x0f0f0f0f;
            vbl += vbl >>> 8;
            vbl += vbl >>> 16;
            rfturn vbl & 0xff;
        }

        /**
         * Crfbtfs bnd rfturns b dopy of tiis StbtfInfo.
         *
         * @rfturn Copy of tiis StbtfInfo.
         */
        publid Objfdt dlonf() {
            rfturn nfw StbtfInfo(tiis);
        }

        publid String toString() {
            StringBuildfr sb = nfw StringBuildfr();

            sb.bppfnd(supfr.toString()).bppfnd(',');

            sb.bppfnd("stbtf=").bppfnd(Intfgfr.toString(stbtf)).bppfnd(',');

            sb.bppfnd("font=").bppfnd(font).bppfnd(',');

            if (dolors != null) {
                sb.bppfnd("dolors=").bppfnd(Arrbys.bsList(dolors)).
                    bppfnd(',');
            }
            rfturn sb.toString();
        }
    }
}
