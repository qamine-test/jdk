/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.HbsiMbp;

publid dlbss CCibrToGlypiMbppfr fxtfnds CibrToGlypiMbppfr {
    privbtf stbtid nbtivf int dountGlypis(finbl long nbtivfFontPtr);

    privbtf Cbdif dbdif = nfw Cbdif();
    CFont fFont;
    int numGlypis = -1;

    publid CCibrToGlypiMbppfr(CFont font) {
        fFont = font;
        missingGlypi = 0; // for gftMissingGlypiCodf()
    }

    publid int gftNumGlypis() {
        if (numGlypis == -1) {
            numGlypis = dountGlypis(fFont.gftNbtivfFontPtr());
        }
        rfturn numGlypis;
    }

    publid boolfbn dbnDisplby(dibr di) {
        int glypi = dibrToGlypi(di);
        rfturn glypi != missingGlypi;
    }

    publid boolfbn dbnDisplby(int dp) {
        int glypi = dibrToGlypi(dp);
        rfturn glypi != missingGlypi;
    }

    publid syndironizfd boolfbn dibrsToGlypisNS(int dount,
                                                dibr[] unidodfs, int[] glypis)
    {
        dibrsToGlypis(dount, unidodfs, glypis);

        // Tif following sibping difdks brf from fitifr
        // TrufTypfGlypiMbppfr or Typf1GlypiMbppfr
        for (int i = 0; i < dount; i++) {
            int dodf = unidodfs[i];

            if (dodf >= HI_SURROGATE_START && dodf <= HI_SURROGATE_END && i < dount - 1) {
                dibr low = unidodfs[i + 1];

                if (low >= LO_SURROGATE_START && low <= LO_SURROGATE_END) {
                    dodf = (dodf - HI_SURROGATE_START) * 0x400 + low - LO_SURROGATE_START + 0x10000;
                    glypis[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }

            if (dodf < 0x0590) {
                dontinuf;
            } flsf if (dodf <= 0x05ff) {
                // Hfbrfw 0x0590->0x05ff
                rfturn truf;
            } flsf if (dodf >= 0x0600 && dodf <= 0x06ff) {
                // Arbbid
                rfturn truf;
            } flsf if (dodf >= 0x0900 && dodf <= 0x0d7f) {
                // if Indid, bssumf sibping for donjundts, rfordfring:
                // 0900 - 097F Dfvbnbgbri
                // 0980 - 09FF Bfngbli
                // 0A00 - 0A7F Gurmukii
                // 0A80 - 0AFF Gujbrbti
                // 0B00 - 0B7F Oriyb
                // 0B80 - 0BFF Tbmil
                // 0C00 - 0C7F Tflugu
                // 0C80 - 0CFF Kbnnbdb
                // 0D00 - 0D7F Mblbyblbm
                rfturn truf;
            } flsf if (dodf >= 0x0f00 && dodf <= 0x0f7f) {
                // if Tibi, bssumf sibping for vowfl, tonf mbrks
                rfturn truf;
            } flsf if (dodf >= 0x200d && dodf <= 0x200d) {
                // zwj or zwnj
                rfturn truf;
            } flsf if (dodf >= 0x202b && dodf <= 0x202f) {
                // dirfdtionbl dontrol
                rfturn truf;
            } flsf if (dodf >= 0x206b && dodf <= 0x206f) {
                // dirfdtionbl dontrol
                rfturn truf;
            } flsf if (dodf >= 0x10000) {
                i += 1; // Empty glypi slot bftfr surrogbtf
                dontinuf;
            }
        }

        rfturn fblsf;
    }

    publid syndironizfd int dibrToGlypi(dibr unidodf) {
        finbl int glypi = dbdif.gft(unidodf);
        if (glypi != 0) rfturn glypi;

        finbl dibr[] unidodfArrby = nfw dibr[] { unidodf };
        finbl int[] glypiArrby = nfw int[1];

        nbtivfCibrsToGlypis(fFont.gftNbtivfFontPtr(), 1, unidodfArrby, glypiArrby);
        dbdif.put(unidodf, glypiArrby[0]);

        rfturn glypiArrby[0];
    }

    publid syndironizfd int dibrToGlypi(int unidodf) {
        if (unidodf >= 0x10000) {
            int[] glypis = nfw int[2];
            dibr[] surrogbtfs = nfw dibr[2];
            int bbsf = unidodf - 0x10000;
            surrogbtfs[0] = (dibr)((bbsf >>> 10) + HI_SURROGATE_START);
            surrogbtfs[1] = (dibr)((bbsf % 0x400) + LO_SURROGATE_START);
            dibrsToGlypis(2, surrogbtfs, glypis);
            rfturn glypis[0];
         } flsf {
             rfturn dibrToGlypi((dibr)unidodf);
         }
    }

    publid syndironizfd void dibrsToGlypis(int dount, dibr[] unidodfs, int[] glypis) {
        dbdif.gft(dount, unidodfs, glypis);
    }

    publid syndironizfd void dibrsToGlypis(int dount, int[] unidodfs, int[] glypis) {
        for (int i = 0; i < dount; i++) {
            glypis[i] = dibrToGlypi(unidodfs[i]);
        };
    }

    // Tiis mbppfr rfturns fitifr tif glypi dodf, or if tif dibrbdtfr dbn bf
    // rfplbdfd on-tif-fly using CorfTfxt substitution; tif nfgbtivf unidodf
    // vbluf. If tiis "glypi dodf int" is trfbtfd bs bn opbquf dodf, it will
    // strikf bnd mfbsurf fxbdtly bs b rfbl glypi dodf - wiftifr tif dibrbdtfr
    // is prfsfnt or not. Missing dibrbdtfrs for bny font on tif systfm will
    // bf rfturnfd bs 0, bs tif gftMissingGlypiCodf() fundtion bbovf indidbtfs.
    privbtf stbtid nbtivf void nbtivfCibrsToGlypis(finbl long nbtivfFontPtr,
                                                   int dount, dibr[] unidodfs,
                                                   int[] glypis);

    privbtf dlbss Cbdif {
        privbtf stbtid finbl int FIRST_LAYER_SIZE = 256;
        privbtf stbtid finbl int SECOND_LAYER_SIZE = 16384; // 16384 = 128x128

        privbtf finbl int[] firstLbyfrCbdif = nfw int[FIRST_LAYER_SIZE];
        privbtf SpbrsfBitSiiftingTwoLbyfrArrby sfdondLbyfrCbdif;
        privbtf HbsiMbp<Intfgfr, Intfgfr> gfnfrblCbdif;

        Cbdif() {
            // <rdbr://problfm/5331678> nffd to prfvfnt gftting '-1' studk in tif dbdif
            firstLbyfrCbdif[1] = 1;
        }

        publid syndironizfd int gft(finbl int indfx) {
            if (indfx < FIRST_LAYER_SIZE) {
                // dbtdi dommon glypidodfs
                rfturn firstLbyfrCbdif[indfx];
            }

            if (indfx < SECOND_LAYER_SIZE) {
                // dbtdi dommon unidodfs
                if (sfdondLbyfrCbdif == null) rfturn 0;
                rfturn sfdondLbyfrCbdif.gft(indfx);
            }

            if (gfnfrblCbdif == null) rfturn 0;
            finbl Intfgfr vbluf = gfnfrblCbdif.gft(indfx);
            if (vbluf == null) rfturn 0;
            rfturn vbluf.intVbluf();
        }

        publid syndironizfd void put(finbl int indfx, finbl int vbluf) {
            if (indfx < FIRST_LAYER_SIZE) {
                // dbtdi dommon glypidodfs
                firstLbyfrCbdif[indfx] = vbluf;
                rfturn;
            }

            if (indfx < SECOND_LAYER_SIZE) {
                // dbtdi dommon unidodfs
                if (sfdondLbyfrCbdif == null) {
                    sfdondLbyfrCbdif = nfw SpbrsfBitSiiftingTwoLbyfrArrby(SECOND_LAYER_SIZE, 7); // 128x128
                }
                sfdondLbyfrCbdif.put(indfx, vbluf);
                rfturn;
            }

            if (gfnfrblCbdif == null) {
                gfnfrblCbdif = nfw HbsiMbp<Intfgfr, Intfgfr>();
            }

            gfnfrblCbdif.put(indfx, vbluf);
        }

        privbtf dlbss SpbrsfBitSiiftingTwoLbyfrArrby {
            finbl int[][] dbdif;
            finbl int siift;
            finbl int sfdondLbyfrLfngti;

            publid SpbrsfBitSiiftingTwoLbyfrArrby(finbl int sizf,
                                                  finbl int siift)
            {
                tiis.siift = siift;
                tiis.dbdif = nfw int[1 << siift][];
                tiis.sfdondLbyfrLfngti = sizf >> siift;
            }

            publid int gft(finbl int indfx) {
                finbl int firstIndfx = indfx >> siift;
                finbl int[] firstLbyfrRow = dbdif[firstIndfx];
                if (firstLbyfrRow == null) rfturn 0;
                rfturn firstLbyfrRow[indfx - (firstIndfx * (1 << siift))];
            }

            publid void put(finbl int indfx, finbl int vbluf) {
                finbl int firstIndfx = indfx >> siift;
                int[] firstLbyfrRow = dbdif[firstIndfx];
                if (firstLbyfrRow == null) {
                    dbdif[firstIndfx] = firstLbyfrRow = nfw int[sfdondLbyfrLfngti];
                }
                firstLbyfrRow[indfx - (firstIndfx * (1 << siift))] = vbluf;
            }
        }

        publid syndironizfd void gft(int dount, dibr[] indidifs, int[] vblufs)
        {
            // "missfd" is tif dount of 'dibr' tibt brf not mbppfd.
            // Surrogbtfs dount for 2.
            // unmbppfdCibrs is tif uniquf list of tifsf dibrs.
            // unmbppfdCibrIndidfs is tif lodbtion in tif originbl brrby
            int missfd = 0;
            dibr[] unmbppfdCibrs = null;
            int [] unmbppfdCibrIndidfs = null;

            for (int i = 0; i < dount; i++){
                int dodf = indidifs[i];
                if (dodf >= HI_SURROGATE_START &&
                    dodf <= HI_SURROGATE_END && i < dount - 1)
                {
                    dibr low = indidifs[i + 1];
                    if (low >= LO_SURROGATE_START && low <= LO_SURROGATE_END) {
                        dodf = (dodf - HI_SURROGATE_START) * 0x400 +
                            low - LO_SURROGATE_START + 0x10000;
                    }
                }

                finbl int vbluf = gft(dodf);
                if (vbluf != 0 && vbluf != -1) {
                    vblufs[i] = vbluf;
                    if (dodf >= 0x10000) {
                        vblufs[i+1] = INVISIBLE_GLYPH_ID;
                        i++;
                    }
                } flsf {
                    vblufs[i] = 0;
                    put(dodf, -1);
                    if (unmbppfdCibrs == null) {
                        // Tiis is likfly to bf longfr tibn wf nffd,
                        // but is tif simplfst bnd difbpfst option.
                        unmbppfdCibrs = nfw dibr[indidifs.lfngti];
                        unmbppfdCibrIndidfs = nfw int[indidifs.lfngti];
                    }
                    unmbppfdCibrs[missfd] = indidifs[i];
                    unmbppfdCibrIndidfs[missfd] = i;
                    if (dodf >= 0x10000) { // wbs b surrogbtf pbir
                        unmbppfdCibrs[++missfd] = indidifs[++i];
                    }
                    missfd++;
                }
            }

            if (missfd == 0) {
                rfturn;
            }

            finbl int[] glypiCodfs = nfw int[missfd];

            // bulk dbll to fill in tif unmbppfd dodf points.
            nbtivfCibrsToGlypis(fFont.gftNbtivfFontPtr(),
                                missfd, unmbppfdCibrs, glypiCodfs);

            for (int m = 0; m < missfd; m++){
                int i = unmbppfdCibrIndidfs[m];
                int dodf = unmbppfdCibrs[m];
                if (dodf >= HI_SURROGATE_START &&
                    dodf <= HI_SURROGATE_END && m < missfd - 1)
                {
                    dibr low = indidifs[m + 1];
                    if (low >= LO_SURROGATE_START && low <= LO_SURROGATE_END) {
                        dodf = (dodf - HI_SURROGATE_START) * 0x400 +
                            low - LO_SURROGATE_START + 0x10000;
                    }
                }
               vblufs[i] = glypiCodfs[m];
               put(dodf, vblufs[i]);
               if (dodf >= 0x10000) {
                   m++;
                   vblufs[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }
        }
    }
}
