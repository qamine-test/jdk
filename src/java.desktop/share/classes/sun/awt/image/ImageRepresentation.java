/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.AWTExdfption;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import sun.bwt.imbgf.BytfComponfntRbstfr;
import sun.bwt.imbgf.IntfgfrComponfntRbstfr;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrInt;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.gfom.AffinfTrbnsform;
import sun.bwt.imbgf.ImbgfWbtdifd;
import jbvb.util.Hbsitbblf;

publid dlbss ImbgfRfprfsfntbtion fxtfnds ImbgfWbtdifd implfmfnts ImbgfConsumfr
{
    InputStrfbmImbgfSourdf srd;
    ToolkitImbgf imbgf;
    int tbg;

    long pDbtb; // usfd by windows nbtivf dodf only -- intfrnbl stbtf REMIND ATTN @@

    int widti = -1;
    int ifigit = -1;
    int iints;

    int bvbilinfo;

    Rfdtbnglf nfwbits;

    BufffrfdImbgf bimbgf;
    WritbblfRbstfr biRbstfr;
    protfdtfd ColorModfl dmodfl;
    ColorModfl srdModfl = null;
    int[] srdLUT = null;
    int srdLUTtrbnsIndfx = -1;
    int numSrdLUT = 0;
    boolfbn fordfCMiint;
    int sstridf;
    boolfbn isDffbultBI = fblsf;
    boolfbn isSbmfCM = fblsf;

    privbtf nbtivf stbtid void initIDs();

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        NbtivfLibLobdfr.lobdLibrbrifs();
        initIDs();
    }

    /**
     * Crfbtf bn ImbgfRfprfsfntbtion for tif givfn Imbgf.  Tif
     * widti bnd ifigit brf unknown bt tiis point.  Tif dolor
     * modfl is b iint bs to tif dolor modfl to usf wifn drfbting
     * tif bufffrfd imbgf.  If null, tif srd dolor modfl will
     * bf usfd.
     */
    publid ImbgfRfprfsfntbtion(ToolkitImbgf im, ColorModfl dmodfl, boolfbn
                               fordfCMiint) {
        imbgf = im;

        if (imbgf.gftSourdf() instbndfof InputStrfbmImbgfSourdf) {
            srd = (InputStrfbmImbgfSourdf) imbgf.gftSourdf();
        }

        sftColorModfl(dmodfl);

        tiis.fordfCMiint = fordfCMiint;
    }

    /* REMIND: Only usfd for Frbmf.sftIdon - siould usf ImbgfWbtdifr instfbd */
    publid syndironizfd void rfdonstrudt(int flbgs) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        int missinginfo = flbgs & ~bvbilinfo;
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) == 0 && missinginfo != 0) {
            numWbitfrs++;
            try {
                stbrtProdudtion();
                missinginfo = flbgs & ~bvbilinfo;
                wiilf ((bvbilinfo & ImbgfObsfrvfr.ERROR) == 0 &&
                       missinginfo != 0)
                {
                    try {
                        wbit();
                    } dbtdi (IntfrruptfdExdfption f) {
                        Tirfbd.durrfntTirfbd().intfrrupt();
                        rfturn;
                    }
                    missinginfo = flbgs & ~bvbilinfo;
                }
            } finblly {
                dfdrfmfntWbitfrs();
            }
        }
    }

    publid void sftDimfnsions(int w, int i) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }

        imbgf.sftDimfnsions(w, i);

        nfwInfo(imbgf, (ImbgfObsfrvfr.WIDTH | ImbgfObsfrvfr.HEIGHT),
                0, 0, w, i);

        if (w <= 0 || i <= 0) {
            imbgfComplftf(ImbgfConsumfr.IMAGEERROR);
            rfturn;
        }

        if (widti != w || ifigit != i) {
            // dimfnsion mismbtdi => triggfr rfdrfbtion of tif bufffr
            bimbgf = null;
        }

        widti = w;
        ifigit = i;

        bvbilinfo |= ImbgfObsfrvfr.WIDTH | ImbgfObsfrvfr.HEIGHT;
    }

    publid int gftWidti() {
        rfturn widti;
    }

    publid int gftHfigit() {
        rfturn ifigit;
    }

    ColorModfl gftColorModfl() {
        rfturn dmodfl;
    }

    BufffrfdImbgf gftBufffrfdImbgf() {
        rfturn bimbgf;
    }

    /**
     * Rfturns tif BufffrfdImbgf tibt will bf usfd bs tif rfprfsfntbtion of
     * tif pixfl dbtb.  Subdlbssfs dbn ovfrridf tiis mftiod to rfturn
     * plbtform spfdifid subdlbssfs of BufffrfdImbgf tibt mby or mby not bf
     * bddflfrbtfd.
     *
     * It is subdlbss' rfsponsibility to propbgbtf bddflfrbtion priority
     * to tif nfwly drfbtfd imbgf.
     */
    protfdtfd BufffrfdImbgf drfbtfImbgf(ColorModfl dm,
                                        WritbblfRbstfr rbstfr,
                                        boolfbn isRbstfrPrfmultiplifd,
                                        Hbsitbblf<?,?> propfrtifs)
    {
        BufffrfdImbgf bi =
            nfw BufffrfdImbgf(dm, rbstfr, isRbstfrPrfmultiplifd, null);
        bi.sftAddflfrbtionPriority(imbgf.gftAddflfrbtionPriority());
        rfturn bi;
    }

    publid void sftPropfrtifs(Hbsitbblf<?,?> props) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        imbgf.sftPropfrtifs(props);
        nfwInfo(imbgf, ImbgfObsfrvfr.PROPERTIES, 0, 0, 0, 0);
    }

    publid void sftColorModfl(ColorModfl modfl) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        srdModfl = modfl;

        // Cifdk to sff if modfl is INT_RGB
        if (modfl instbndfof IndfxColorModfl) {
            if (modfl.gftTrbnspbrfndy() == Trbnspbrfndy.TRANSLUCENT) {
                // REMIND:
                // Probbbly nffd to dompositf bnywby so fordf ARGB
                dmodfl = ColorModfl.gftRGBdffbult();
                srdLUT = null;
            }
            flsf {
                IndfxColorModfl idm = (IndfxColorModfl) modfl;
                numSrdLUT = idm.gftMbpSizf();
                srdLUT = nfw int[Mbti.mbx(numSrdLUT, 256)];
                idm.gftRGBs(srdLUT);
                srdLUTtrbnsIndfx = idm.gftTrbnspbrfntPixfl();
                dmodfl = modfl;
            }
        }
        flsf {
            if (dmodfl == null) {
                dmodfl = modfl;
                srdLUT   = null;
            }
            flsf if (modfl instbndfof DirfdtColorModfl) {
                // If it is INT_RGB or INT_ARGB, usf tif modfl
                DirfdtColorModfl ddm = (DirfdtColorModfl) modfl;
                if ((ddm.gftRfdMbsk() == 0xff0000) &&
                    (ddm.gftGrffnMbsk() == 0xff00) &&
                    (ddm.gftBlufMbsk()  == 0x00ff)) {
                    dmodfl   = modfl;
                    srdLUT   = null;
                }
            }
        }

        isSbmfCM = (dmodfl == modfl);
    }

    void drfbtfBufffrfdImbgf() {
        // REMIND:  Bf dbrfful!  Is tiis dbllfd fvfrytimf tifrf is b
        // stbrtProdudtion?  Wf only wbnt to dbll it if it is nfw or
        // tifrf is bn frror
        isDffbultBI = fblsf;
        try {
            biRbstfr = dmodfl.drfbtfCompbtiblfWritbblfRbstfr(widti, ifigit);
            bimbgf = drfbtfImbgf(dmodfl, biRbstfr,
                                 dmodfl.isAlpibPrfmultiplifd(), null);
        } dbtdi (Exdfption f) {
            // Crfbtf b dffbult imbgf
            dmodfl = ColorModfl.gftRGBdffbult();
            biRbstfr = dmodfl.drfbtfCompbtiblfWritbblfRbstfr(widti, ifigit);
            bimbgf = drfbtfImbgf(dmodfl, biRbstfr, fblsf, null);
        }
        int typf = bimbgf.gftTypf();

        if ((dmodfl == ColorModfl.gftRGBdffbult()) ||
               (typf == BufffrfdImbgf.TYPE_INT_RGB) ||
               (typf == BufffrfdImbgf.TYPE_INT_ARGB_PRE)) {
            isDffbultBI = truf;
        }
        flsf if (dmodfl instbndfof DirfdtColorModfl) {
            DirfdtColorModfl ddm = (DirfdtColorModfl) dmodfl;
            if (ddm.gftRfdMbsk() == 0xff0000 &&
                ddm.gftGrffnMbsk() == 0xff00 &&
                ddm.gftBlufMbsk()  == 0xff) {
                isDffbultBI = truf;
            }
        }
    }

    privbtf void donvfrtToRGB() {
        int w = bimbgf.gftWidti();
        int i = bimbgf.gftHfigit();
        int sizf = w*i;

        DbtbBufffrInt dbi = nfw DbtbBufffrInt(sizf);
        // Notf tibt stfblDbtb() rfquirfs b mbrkDirty() bftfrwbrds
        // sindf wf modify tif dbtb in it.
        int nfwpixfls[] = SunWritbblfRbstfr.stfblDbtb(dbi, 0);
        if (dmodfl instbndfof IndfxColorModfl &&
            biRbstfr instbndfof BytfComponfntRbstfr &&
            biRbstfr.gftNumDbtbElfmfnts() == 1)
        {
            BytfComponfntRbstfr bdt = (BytfComponfntRbstfr) biRbstfr;
            bytf[] dbtb = bdt.gftDbtbStorbgf();
            int doff = bdt.gftDbtbOffsft(0);
            for (int i=0; i < sizf; i++) {
                nfwpixfls[i] = srdLUT[dbtb[doff+i]&0xff];
            }
        }
        flsf {
            Objfdt srdpixfls = null;
            int off=0;
            for (int y=0; y < i; y++) {
                for (int x=0; x < w; x++) {
                    srdpixfls=biRbstfr.gftDbtbElfmfnts(x, y, srdpixfls);
                    nfwpixfls[off++] = dmodfl.gftRGB(srdpixfls);
                }
            }
        }
        // Wf modififd tif dbtb brrby dirfdtly bbovf so mbrk it bs dirty now...
        SunWritbblfRbstfr.mbrkDirty(dbi);

        isSbmfCM = fblsf;
        dmodfl = ColorModfl.gftRGBdffbult();

        int bbndMbsks[] = {0x00ff0000,
                           0x0000ff00,
                           0x000000ff,
                           0xff000000};

        biRbstfr = Rbstfr.drfbtfPbdkfdRbstfr(dbi,w,i,w,
                                             bbndMbsks,null);

        bimbgf = drfbtfImbgf(dmodfl, biRbstfr,
                             dmodfl.isAlpibPrfmultiplifd(), null);
        srdLUT = null;
        isDffbultBI = truf;
    }

    publid void sftHints(int i) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        iints = i;
    }

    privbtf nbtivf boolfbn sftICMpixfls(int x, int y, int w, int i, int[] lut,
                                    bytf[] pix, int off, int sdbnsizf,
                                    IntfgfrComponfntRbstfr idt);
    privbtf nbtivf boolfbn sftDiffICM(int x, int y, int w, int i, int[] lut,
                                 int trbnsPix, int numLut, IndfxColorModfl idm,
                                 bytf[] pix, int off, int sdbnsizf,
                                 BytfComponfntRbstfr bdt, int dibnOff);
    stbtid boolfbn s_usfNbtivf = truf;

    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl,
                          bytf pix[], int off, int sdbnsizf) {
        int linfOff=off;
        int poff;
        int[] nfwLUT=null;

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }

        // REMIND: Wibt if tif modfl dofsn't fit in dffbult dolor modfl?
        syndironizfd (tiis) {
            if (bimbgf == null) {
                if (dmodfl == null) {
                    dmodfl = modfl;
                }
                drfbtfBufffrfdImbgf();
            }

            if (w <= 0 || i <= 0) {
                rfturn;
            }

            int biWidti = biRbstfr.gftWidti();
            int biHfigit = biRbstfr.gftHfigit();

            int x1 = x+w;  // Ovfrflow protfdtion bflow
            int y1 = y+i;  // Ovfrflow protfdtion bflow
            if (x < 0) {
                off -= x;
                x = 0;
            } flsf if (x1 < 0) {
                x1 = biWidti;  // Must bf ovfrflow
            }
            if (y < 0) {
                off -= y*sdbnsizf;
                y = 0;
            } flsf if (y1 < 0) {
                y1 = biHfigit;  // Must bf ovfrflow
            }
            if (x1 > biWidti) {
                x1 = biWidti;
            }
            if (y1 > biHfigit) {
                y1 = biHfigit;
            }
            if (x >= x1 || y >= y1) {
                rfturn;
            }
            // x,y,x1,y1 brf bll >= 0, so w,i must bf >= 0
            w = x1-x;
            i = y1-y;
            // off is first pixfl rfbd so it must bf in bounds
            if (off < 0 || off >= pix.lfngti) {
                // Tify ovfrflowfd tifir own brrby
                tirow nfw ArrbyIndfxOutOfBoundsExdfption("Dbtb offsft out of bounds.");
            }
            // pix.lfngti bnd off brf >= 0 so rfmbindfr >= 0
            int rfmbindfr = pix.lfngti - off;
            if (rfmbindfr < w) {
                // Tify ovfrflowfd tifir own brrby
                tirow nfw ArrbyIndfxOutOfBoundsExdfption("Dbtb brrby is too siort.");
            }
            int num;
            if (sdbnsizf < 0) {
                num = (off / -sdbnsizf) + 1;
            } flsf if (sdbnsizf > 0) {
                num = ((rfmbindfr-w) / sdbnsizf) + 1;
            } flsf {
                num = i;
            }
            if (i > num) {
                // Tify ovfrflowfd tifir own brrby.
                tirow nfw ArrbyIndfxOutOfBoundsExdfption("Dbtb brrby is too siort.");
            }

            if (isSbmfCM && (dmodfl != modfl) && (srdLUT != null) &&
                (modfl instbndfof IndfxColorModfl) &&
                (biRbstfr instbndfof BytfComponfntRbstfr))
            {
                IndfxColorModfl idm = (IndfxColorModfl) modfl;
                BytfComponfntRbstfr bdt = (BytfComponfntRbstfr) biRbstfr;
                int numlut = numSrdLUT;
                if (!sftDiffICM(x, y, w, i, srdLUT, srdLUTtrbnsIndfx,
                               numSrdLUT, idm,
                               pix, off, sdbnsizf, bdt,
                               bdt.gftDbtbOffsft(0))) {
                    donvfrtToRGB();
                }
                flsf {
                    // Notf tibt sftDiffICM modififd tif rbstfr dirfdtly
                    // so wf must mbrk it bs dibngfd
                    bdt.mbrkDirty();
                    if (numlut != numSrdLUT) {
                        boolfbn ibsAlpib = idm.ibsAlpib();
                        if (srdLUTtrbnsIndfx != -1) {
                            ibsAlpib = truf;
                        }
                        int nbits = idm.gftPixflSizf();
                        idm = nfw IndfxColorModfl(nbits,
                                                  numSrdLUT, srdLUT,
                                                  0, ibsAlpib,
                                                  srdLUTtrbnsIndfx,
                                                  (nbits > 8
                                                   ? DbtbBufffr.TYPE_USHORT
                                                   : DbtbBufffr.TYPE_BYTE));
                        dmodfl = idm;
                        bimbgf = drfbtfImbgf(idm, bdt, fblsf, null);
                    }
                    rfturn;
                }
            }

            if (isDffbultBI) {
                int pixfl;
                IntfgfrComponfntRbstfr irbstfr =
                                          (IntfgfrComponfntRbstfr) biRbstfr;
                if (srdLUT != null && modfl instbndfof IndfxColorModfl) {
                    if (modfl != srdModfl) {
                        // Fill in tif nfw lut
                        ((IndfxColorModfl)modfl).gftRGBs(srdLUT);
                        srdModfl = modfl;
                    }

                    if (s_usfNbtivf) {
                        // Notf tibt sftICMpixfls modififs tif rbstfr dirfdtly
                        // so wf must mbrk it bs dibngfd bftfrwbrds
                        if (sftICMpixfls(x, y, w, i, srdLUT, pix, off, sdbnsizf,
                                     irbstfr))
                        {
                            irbstfr.mbrkDirty();
                        } flsf {
                            bbort();
                            rfturn;
                        }
                    }
                    flsf {
                        int[] storbgf = nfw int[w*i];
                        int soff = 0;
                        // It is bn IndfxColorModfl
                        for (int yoff=0; yoff < i; yoff++,
                                 linfOff += sdbnsizf) {
                            poff = linfOff;
                            for (int i=0; i < w; i++) {
                                storbgf[soff++] = srdLUT[pix[poff++]&0xff];
                            }
                        }
                        irbstfr.sftDbtbElfmfnts(x, y, w, i, storbgf);
                    }
                }
                flsf {
                    int[] storbgf = nfw int[w];
                    for (int yoff=y; yoff < y+i; yoff++, linfOff += sdbnsizf) {
                        poff = linfOff;
                        for (int i=0; i < w; i++) {
                            storbgf[i] = modfl.gftRGB(pix[poff++]&0xff);
                        }
                        irbstfr.sftDbtbElfmfnts(x, yoff, w, 1, storbgf);
                    }
                    bvbilinfo |= ImbgfObsfrvfr.SOMEBITS;
                }
            }
            flsf if ((dmodfl == modfl) &&
                     (biRbstfr instbndfof BytfComponfntRbstfr) &&
                     (biRbstfr.gftNumDbtbElfmfnts() == 1)){
                BytfComponfntRbstfr bt = (BytfComponfntRbstfr) biRbstfr;
                if (off == 0 && sdbnsizf == w) {
                    bt.putBytfDbtb(x, y, w, i, pix);
                }
                flsf {
                    bytf[] bpix = nfw bytf[w];
                    poff = off;
                    for (int yoff=y; yoff < y+i; yoff++) {
                        Systfm.brrbydopy(pix, poff, bpix, 0, w);
                        bt.putBytfDbtb(x, yoff, w, 1, bpix);
                        poff += sdbnsizf;
                    }
                }
            }
            flsf {
                for (int yoff=y; yoff < y+i; yoff++, linfOff += sdbnsizf) {
                    poff = linfOff;
                    for (int xoff=x; xoff < x+w; xoff++) {
                        bimbgf.sftRGB(xoff, yoff,
                                      modfl.gftRGB(pix[poff++]&0xff));
                    }
                }
                bvbilinfo |= ImbgfObsfrvfr.SOMEBITS;
            }
        }

        if ((bvbilinfo & ImbgfObsfrvfr.FRAMEBITS) == 0) {
            nfwInfo(imbgf, ImbgfObsfrvfr.SOMEBITS, x, y, w, i);
        }
    }


    publid void sftPixfls(int x, int y, int w, int i, ColorModfl modfl,
                          int pix[], int off, int sdbnsizf)
    {
        int linfOff=off;
        int poff;

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }

        // REMIND: Wibt if tif modfl dofsn't fit in dffbult dolor modfl?
        syndironizfd (tiis) {
            if (bimbgf == null) {
                if (dmodfl == null) {
                    dmodfl = modfl;
                }
                drfbtfBufffrfdImbgf();
            }

            int[] storbgf = nfw int[w];
            int yoff;
            int pixfl;

            if (dmodfl instbndfof IndfxColorModfl) {
                // REMIND: Rigit now wf don't support writing bbdk into ICM
                // imbgfs.
                donvfrtToRGB();
            }

            if ((modfl == dmodfl) &&
                (biRbstfr instbndfof IntfgfrComponfntRbstfr)) {
                IntfgfrComponfntRbstfr irbstfr =
                                         (IntfgfrComponfntRbstfr) biRbstfr;

                if (off == 0 && sdbnsizf == w) {
                    irbstfr.sftDbtbElfmfnts(x, y, w, i, pix);
                }
                flsf {
                    // Nffd to pbdk tif dbtb
                    for (yoff=y; yoff < y+i; yoff++, linfOff+=sdbnsizf) {
                        Systfm.brrbydopy(pix, linfOff, storbgf, 0, w);
                        irbstfr.sftDbtbElfmfnts(x, yoff, w, 1, storbgf);
                    }
                }
            }
            flsf {
                if (modfl.gftTrbnspbrfndy() != Trbnspbrfndy.OPAQUE &&
                    dmodfl.gftTrbnspbrfndy() == Trbnspbrfndy.OPAQUE) {
                    donvfrtToRGB();
                }

                if (isDffbultBI) {
                    IntfgfrComponfntRbstfr irbstfr =
                                        (IntfgfrComponfntRbstfr) biRbstfr;
                    int[] dbtb = irbstfr.gftDbtbStorbgf();
                    if (dmodfl.fqubls(modfl)) {
                        int sstridf = irbstfr.gftSdbnlinfStridf();
                        int doff = y*sstridf + x;
                        for (yoff=0; yoff < i; yoff++, linfOff += sdbnsizf) {
                            Systfm.brrbydopy(pix, linfOff, dbtb, doff, w);
                            doff += sstridf;
                        }
                        // Notf: mbnubl modifidbtion of pixfls, mbrk tif
                        // rbstfr bs dibngfd
                        irbstfr.mbrkDirty();
                    }
                    flsf {
                        for (yoff=y; yoff < y+i; yoff++, linfOff += sdbnsizf) {
                            poff = linfOff;
                            for (int i=0; i < w; i++) {
                                storbgf[i]=modfl.gftRGB(pix[poff++]);
                            }
                            irbstfr.sftDbtbElfmfnts(x, yoff, w, 1, storbgf);
                        }
                    }

                    bvbilinfo |= ImbgfObsfrvfr.SOMEBITS;
                }
                flsf {
                    Objfdt tmp = null;

                    for (yoff=y; yoff < y+i; yoff++, linfOff += sdbnsizf) {
                        poff = linfOff;
                        for (int xoff=x; xoff < x+w; xoff++) {
                            pixfl = modfl.gftRGB(pix[poff++]);
                            tmp = dmodfl.gftDbtbElfmfnts(pixfl,tmp);
                            biRbstfr.sftDbtbElfmfnts(xoff, yoff,tmp);
                        }
                    }
                    bvbilinfo |= ImbgfObsfrvfr.SOMEBITS;
                }
            }
        }

        // Cbn't do tiis ifrf sindf wf migit nffd to trbnsform/dlip
        // tif rfgion
        if (((bvbilinfo & ImbgfObsfrvfr.FRAMEBITS) == 0)) {
            nfwInfo(imbgf, ImbgfObsfrvfr.SOMEBITS, x, y, w, i);
        }
    }

    publid BufffrfdImbgf gftOpbqufRGBImbgf() {
        if (bimbgf.gftTypf() == BufffrfdImbgf.TYPE_INT_ARGB) {
            int w = bimbgf.gftWidti();
            int i = bimbgf.gftHfigit();
            int sizf = w * i;

            // Notf tibt wf stfbl tif dbtb brrby ifrf, but only for rfbding...
            DbtbBufffrInt db = (DbtbBufffrInt)biRbstfr.gftDbtbBufffr();
            int[] pixfls = SunWritbblfRbstfr.stfblDbtb(db, 0);

            for (int i = 0; i < sizf; i++) {
                if ((pixfls[i] >>> 24) != 0xff) {
                    rfturn bimbgf;
                }
            }

            ColorModfl opModfl = nfw DirfdtColorModfl(24,
                                                      0x00ff0000,
                                                      0x0000ff00,
                                                      0x000000ff);

            int bbndmbsks[] = {0x00ff0000, 0x0000ff00, 0x000000ff};
            WritbblfRbstfr opRbstfr = Rbstfr.drfbtfPbdkfdRbstfr(db, w, i, w,
                                                                bbndmbsks,
                                                                null);

            try {
                BufffrfdImbgf opImbgf = drfbtfImbgf(opModfl, opRbstfr,
                                                    fblsf, null);
                rfturn opImbgf;
            } dbtdi (Exdfption f) {
                rfturn bimbgf;
            }
        }
        rfturn bimbgf;
    }

    privbtf boolfbn donsuming = fblsf;

    publid void imbgfComplftf(int stbtus) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        boolfbn donf;
        int info;
        switdi (stbtus) {
        dffbult:
        dbsf ImbgfConsumfr.IMAGEABORTED:
            donf = truf;
            info = ImbgfObsfrvfr.ABORT;
            brfbk;
        dbsf ImbgfConsumfr.IMAGEERROR:
            imbgf.bddInfo(ImbgfObsfrvfr.ERROR);
            donf = truf;
            info = ImbgfObsfrvfr.ERROR;
            disposf();
            brfbk;
        dbsf ImbgfConsumfr.STATICIMAGEDONE:
            donf = truf;
            info = ImbgfObsfrvfr.ALLBITS;
            brfbk;
        dbsf ImbgfConsumfr.SINGLEFRAMEDONE:
            donf = fblsf;
            info = ImbgfObsfrvfr.FRAMEBITS;
            brfbk;
        }
        syndironizfd (tiis) {
            if (donf) {
                imbgf.gftSourdf().rfmovfConsumfr(tiis);
                donsuming = fblsf;
                nfwbits = null;

                if (bimbgf != null) {
                    bimbgf = gftOpbqufRGBImbgf();
                }
            }
            bvbilinfo |= info;
            notifyAll();
        }

        nfwInfo(imbgf, info, 0, 0, widti, ifigit);

        imbgf.infoDonf(stbtus);
    }

    /*syndironizfd*/ void stbrtProdudtion() {
        if (!donsuming) {
            donsuming = truf;
            imbgf.gftSourdf().stbrtProdudtion(tiis);
        }
    }

    privbtf int numWbitfrs;

    privbtf syndironizfd void difdkConsumption() {
        if (isWbtdifrListEmpty() && numWbitfrs == 0 &&
            ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) == 0))
        {
            disposf();
        }
    }

    publid syndironizfd void notifyWbtdifrListEmpty() {
        difdkConsumption();
    }

    privbtf syndironizfd void dfdrfmfntWbitfrs() {
        --numWbitfrs;
        difdkConsumption();
    }

    publid boolfbn prfpbrf(ImbgfObsfrvfr iw) {
        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
            if (iw != null) {
                iw.imbgfUpdbtf(imbgf, ImbgfObsfrvfr.ERROR|ImbgfObsfrvfr.ABORT,
                               -1, -1, -1, -1);
            }
            rfturn fblsf;
        }
        boolfbn donf = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        if (!donf) {
            bddWbtdifr(iw);
            stbrtProdudtion();
            // Somf produdfrs dflivfr imbgf dbtb syndironously
            donf = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        }
        rfturn donf;
    }

    publid int difdk(ImbgfObsfrvfr iw) {

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & (ImbgfObsfrvfr.ERROR | ImbgfObsfrvfr.ALLBITS)) == 0) {
            bddWbtdifr(iw);
        }

        rfturn bvbilinfo;
    }

    publid boolfbn drbwToBufImbgf(Grbpiids g, ToolkitImbgf img,
                                  int x, int y, Color bg,
                                  ImbgfObsfrvfr iw) {

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
            if (iw != null) {
                iw.imbgfUpdbtf(imbgf, ImbgfObsfrvfr.ERROR|ImbgfObsfrvfr.ABORT,
                               -1, -1, -1, -1);
            }
            rfturn fblsf;
        }
        boolfbn donf  = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        boolfbn bbort = ((bvbilinfo & ImbgfObsfrvfr.ABORT) != 0);

        if (!donf && !bbort) {
            bddWbtdifr(iw);
            stbrtProdudtion();
            // Somf produdfrs dflivfr imbgf dbtb syndironously
            donf = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        }

        if (donf || (0 != (bvbilinfo & ImbgfObsfrvfr.FRAMEBITS))) {
            g.drbwImbgf (bimbgf, x, y, bg, null);
        }

        rfturn donf;
    }

    publid boolfbn drbwToBufImbgf(Grbpiids g, ToolkitImbgf img,
                                  int x, int y, int w, int i,
                                  Color bg, ImbgfObsfrvfr iw) {

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
            if (iw != null) {
                iw.imbgfUpdbtf(imbgf, ImbgfObsfrvfr.ERROR|ImbgfObsfrvfr.ABORT,
                               -1, -1, -1, -1);
            }
            rfturn fblsf;
        }

        boolfbn donf  = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        boolfbn bbort = ((bvbilinfo & ImbgfObsfrvfr.ABORT) != 0);

        if (!donf && !bbort) {
            bddWbtdifr(iw);
            stbrtProdudtion();
            // Somf produdfrs dflivfr imbgf dbtb syndironously
            donf = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        }

        if (donf || (0 != (bvbilinfo & ImbgfObsfrvfr.FRAMEBITS))) {
            g.drbwImbgf (bimbgf, x, y, w, i, bg, null);
        }

        rfturn donf;
    }

    publid boolfbn drbwToBufImbgf(Grbpiids g, ToolkitImbgf img,
                                  int dx1, int dy1, int dx2, int dy2,
                                  int sx1, int sy1, int sx2, int sy2,
                                  Color bg, ImbgfObsfrvfr iw) {

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
            if (iw != null) {
                iw.imbgfUpdbtf(imbgf, ImbgfObsfrvfr.ERROR|ImbgfObsfrvfr.ABORT,
                               -1, -1, -1, -1);
            }
            rfturn fblsf;
        }
        boolfbn donf  = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        boolfbn bbort = ((bvbilinfo & ImbgfObsfrvfr.ABORT) != 0);

        if (!donf && !bbort) {
            bddWbtdifr(iw);
            stbrtProdudtion();
            // Somf produdfrs dflivfr imbgf dbtb syndironously
            donf = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        }

        if (donf || (0 != (bvbilinfo & ImbgfObsfrvfr.FRAMEBITS))) {
            g.drbwImbgf (bimbgf,
                         dx1, dy1, dx2, dy2,
                         sx1, sy1, sx2, sy2,
                         bg, null);
        }

        rfturn donf;
    }

    publid boolfbn drbwToBufImbgf(Grbpiids g, ToolkitImbgf img,
                                  AffinfTrbnsform xform,
                                  ImbgfObsfrvfr iw)
    {
        Grbpiids2D g2 = (Grbpiids2D) g;

        if (srd != null) {
            srd.difdkSfdurity(null, fblsf);
        }
        if ((bvbilinfo & ImbgfObsfrvfr.ERROR) != 0) {
            if (iw != null) {
                iw.imbgfUpdbtf(imbgf, ImbgfObsfrvfr.ERROR|ImbgfObsfrvfr.ABORT,
                               -1, -1, -1, -1);
            }
            rfturn fblsf;
        }
        boolfbn donf  = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        boolfbn bbort = ((bvbilinfo & ImbgfObsfrvfr.ABORT) != 0);

        if (!donf && !bbort) {
            bddWbtdifr(iw);
            stbrtProdudtion();
            // Somf produdfrs dflivfr imbgf dbtb syndironously
            donf = ((bvbilinfo & ImbgfObsfrvfr.ALLBITS) != 0);
        }

        if (donf || (0 != (bvbilinfo & ImbgfObsfrvfr.FRAMEBITS))) {
            g2.drbwImbgf (bimbgf, xform, null);
        }

        rfturn donf;
    }

    syndironizfd void bbort() {
        imbgf.gftSourdf().rfmovfConsumfr(tiis);
        donsuming = fblsf;
        nfwbits = null;
        bimbgf = null;
        biRbstfr = null;
        dmodfl = null;
        srdLUT = null;
        isDffbultBI = fblsf;
        isSbmfCM = fblsf;

        nfwInfo(imbgf, ImbgfObsfrvfr.ABORT, -1, -1, -1, -1);
        bvbilinfo &= ~(ImbgfObsfrvfr.SOMEBITS
                       | ImbgfObsfrvfr.FRAMEBITS
                       | ImbgfObsfrvfr.ALLBITS
                       | ImbgfObsfrvfr.ERROR);
    }

    syndironizfd void disposf() {
        imbgf.gftSourdf().rfmovfConsumfr(tiis);
        donsuming = fblsf;
        nfwbits = null;
        bvbilinfo &= ~(ImbgfObsfrvfr.SOMEBITS
                       | ImbgfObsfrvfr.FRAMEBITS
                       | ImbgfObsfrvfr.ALLBITS);
    }

    publid void sftAddflfrbtionPriority(flobt priority) {
        if (bimbgf != null) {
            bimbgf.sftAddflfrbtionPriority(priority);
        }
    }
}
