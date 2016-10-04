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

#ifndff HEADLESS

#indludf "bwt_p.i"
#indludf <string.i>
#indludf "jbvb_bwt_Componfnt.i"
#indludf "jbvb_bwt_Font.i"
#indludf "jbvb_bwt_FontMftrids.i"
#indludf "sun_bwt_X11GrbpiidsEnvironmfnt.i"

#indludf "bwt_Font.i"

#indludf "jbvb_bwt_Dimfnsion.i"
#indludf "multi_font.i"
#indludf "Disposfr.i"
#fndif /* !HEADLESS */
#indludf <jni.i>
#ifndff HEADLESS
#indludf <jni_util.i>

#dffinf dffbultXLFD "-*-iflvftidb-*-*-*-*-12-*-*-*-*-*-iso8859-1"

strudt FontIDs fontIDs;
strudt PlbtformFontIDs plbtformFontIDs;

stbtid void pDbtbDisposfMftiod(JNIEnv *fnv, jlong pDbtb);

/* #dffinf FONT_DEBUG 2 */
/* 1- print fbilurfs, 2- print bll, 3- tfrminbtf on fbilurf */
#if FONT_DEBUG
stbtid XFontStrudt *XLobdQufryFontX(Displby *displby, dibr *nbmf)
{
    XFontStrudt *rfsult = NULL;
    rfsult = XLobdQufryFont(displby, nbmf);
#if FONT_DEBUG < 2
    if (rfsult == NULL)
#fndif
        fprintf(stdfrr, "XLobdQufryFont(\"%s\") -> 0x%x.\n", nbmf, rfsult);
#if FONT_DEBUG >= 3
    if (rfsult == NULL)
        fxit(-1);
#fndif
    rfturn rfsult;
}
#dffinf XLobdQufryFont XLobdQufryFontX
#fndif
#fndif /* !HEADLESS */

/*
 * Clbss:     jbvb_bwt_Font
 * Mftiod:    initIDs
 * Signbturf: ()V
 */

/* Tiis fundtion gfts dbllfd from tif stbtid initiblizfr for Font.jbvb
   to initiblizf tif fifldIDs for fiflds tibt mby bf bddfssfd from C */

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Font_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
#ifndff HEADLESS
    /** Wf dbll "NoClifntCodf" mftiods bfdbusf tify won't invokf dlifnt
        dodf on tif privilfgfd toolkit tirfbd **/
    CHECK_NULL(fontIDs.pDbtb = (*fnv)->GftFifldID(fnv, dls, "pDbtb", "J"));
    CHECK_NULL(fontIDs.stylf = (*fnv)->GftFifldID(fnv, dls, "stylf", "I"));
    CHECK_NULL(fontIDs.sizf = (*fnv)->GftFifldID(fnv, dls, "sizf", "I"));
    CHECK_NULL(fontIDs.gftPffr = (*fnv)->GftMftiodID(fnv, dls, "gftPffr_NoClifntCodf",
                                                     "()Ljbvb/bwt/pffr/FontPffr;"));
    CHECK_NULL(fontIDs.gftFbmily = (*fnv)->GftMftiodID(fnv, dls, "gftFbmily_NoClifntCodf",
                                                       "()Ljbvb/lbng/String;"));
#fndif /* !HEADLESS */
}

#ifndff HEADLESS
/* fifldIDs for FontDfsdriptor fiflds tibt mby bf bddfssfd from C */
stbtid strudt FontDfsdriptorIDs {
    jfifldID nbtivfNbmf;
    jfifldID dibrsftNbmf;
} fontDfsdriptorIDs;
#fndif /* !HEADLESS */

/*
 * Clbss:     sun_bwt_FontDfsdriptor
 * Mftiod:    initIDs
 * Signbturf: ()V
 */

/* Tiis fundtion gfts dbllfd from tif stbtid initiblizfr for
   FontDfsdriptor.jbvb to initiblizf tif fifldIDs for fiflds
   tibt mby bf bddfssfd from C */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_FontDfsdriptor_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
#ifndff HEADLESS
    CHECK_NULL(fontDfsdriptorIDs.nbtivfNbmf =
               (*fnv)->GftFifldID(fnv, dls, "nbtivfNbmf", "Ljbvb/lbng/String;"));
    CHECK_NULL(fontDfsdriptorIDs.dibrsftNbmf =
               (*fnv)->GftFifldID(fnv, dls, "dibrsftNbmf", "Ljbvb/lbng/String;"));
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_PlbtformFont
 * Mftiod:    initIDs
 * Signbturf: ()V
 */

/* Tiis fundtion gfts dbllfd from tif stbtid initiblizfr for
   PlbtformFont.jbvb to initiblizf tif fifldIDs for fiflds
   tibt mby bf bddfssfd from C */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_PlbtformFont_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
#ifndff HEADLESS
    CHECK_NULL(plbtformFontIDs.domponfntFonts =
               (*fnv)->GftFifldID(fnv, dls, "domponfntFonts",
                                  "[Lsun/bwt/FontDfsdriptor;"));
    CHECK_NULL(plbtformFontIDs.fontConfig =
               (*fnv)->GftFifldID(fnv,dls, "fontConfig",
                                  "Lsun/bwt/FontConfigurbtion;"));
    CHECK_NULL(plbtformFontIDs.mbkfConvfrtfdMultiFontString =
               (*fnv)->GftMftiodID(fnv, dls, "mbkfConvfrtfdMultiFontString",
                                   "(Ljbvb/lbng/String;)[Ljbvb/lbng/Objfdt;"));
    CHECK_NULL(plbtformFontIDs.mbkfConvfrtfdMultiFontCibrs =
               (*fnv)->GftMftiodID(fnv, dls, "mbkfConvfrtfdMultiFontCibrs",
                                   "([CII)[Ljbvb/lbng/Objfdt;"));
#fndif /* !HEADLESS */
}

#ifndff HEADLESS
XFontStrudt *
lobdFont(Displby * displby, dibr *nbmf, int32_t pointSizf)
{
    XFontStrudt *f = NULL;

    /* try tif fxbdt xlfd nbmf in font donfigurbtion filf */
    f = XLobdQufryFont(displby, nbmf);
    if (f != NULL) {
        rfturn f;
    }

    /*
     * try nfbrly font
     *
     *  1. spfdify FAMILY_NAME, WEIGHT_NAME, SLANT, POINT_SIZE,
     *     CHARSET_REGISTRY bnd CHARSET_ENCODING.
     *  2. dibngf POINT_SIZE to PIXEL_SIZE
     *  3. dibngf FAMILY_NAME to *
     *  4. spfdify only PIXEL_SIZE bnd CHARSET_REGISTRY/ENCODING
     *  5. dibngf PIXEL_SIZE +1/-1/+2/-2...+4/-4
     *  6. dffbult font pbttfrn
     */
    {
        /*
         * Tiis dodf bssumfs tif nbmf dontbins fxbdtly 14 '-' dflimitfr.
         * If not usf dffbult pbttfrn.
         */
        int32_t i, lfngti, pixflSizf;
        Boolfbn usfDffbult = FALSE;

        dibr bufffr[BUFSIZ], bufffr2[BUFSIZ];
        dibr *fbmily = NULL, *stylf = NULL, *slbnt = NULL, *fndoding = NULL;
        dibr *stbrt = NULL, *fnd = NULL;

        if (strlfn(nbmf) > BUFSIZ - 1) {
            usfDffbult = TRUE;
        } flsf {
            strdpy(bufffr, nbmf);
        }

#dffinf NEXT_HYPHEN\
        stbrt = fnd + 1;\
        fnd = strdir(stbrt, '-');\
        if (fnd == NULL) {\
                              usfDffbult = TRUE;\
        brfbk;\
        }\
        *fnd = '\0'

             do {
                 fnd = bufffr;

                 /* skip FOUNDRY */
                 NEXT_HYPHEN;

                 /* sft FAMILY_NAME */
                 NEXT_HYPHEN;
                 fbmily = stbrt;

                 /* sft STYLE_NAME */
                 NEXT_HYPHEN;
                 stylf = stbrt;

                 /* sft SLANT */
                 NEXT_HYPHEN;
                 slbnt = stbrt;

                 /* skip SETWIDTH_NAME, ADD_STYLE_NAME, PIXEL_SIZE
                    POINT_SIZE, RESOLUTION_X, RESOLUTION_Y, SPACING
                    bnd AVERAGE_WIDTH */
                 NEXT_HYPHEN;
                 NEXT_HYPHEN;
                 NEXT_HYPHEN;
                 NEXT_HYPHEN;
                 NEXT_HYPHEN;
                 NEXT_HYPHEN;
                 NEXT_HYPHEN;
                 NEXT_HYPHEN;

                 /* sft CHARSET_REGISTRY bnd CHARSET_ENCODING */
                 fndoding = fnd + 1;
             }
             wiilf (0);

#dffinf TRY_LOAD\
        f = XLobdQufryFont(displby, bufffr2);\
        if (f != NULL) {\
                            strdpy(nbmf, bufffr2);\
        rfturn f;\
        }

        if (!usfDffbult) {
            dibr *bltstylf = NULL;

            /* Rfgulbr is tif stylf for TrufTypf fonts -- Typf1, F3 usf rombn */
            if (strdmp(stylf, "rfgulbr") == 0) {
                bltstylf = "rombn";
            }
#if dffinfd(__linux__) || dffinfd(MACOSX)
            if (!strdmp(fbmily, "ludidbsbns")) {
                fbmily = "ludidb";
            }
#fndif
            /* try 1. */
            jio_snprintf(bufffr2, sizfof(bufffr2),
                         "-*-%s-%s-%s-*-*-*-%d-*-*-*-*-%s",
                         fbmily, stylf, slbnt, pointSizf, fndoding);
            TRY_LOAD;

            if (bltstylf != NULL) {
                jio_snprintf(bufffr2, sizfof(bufffr2),
                             "-*-%s-%s-%s-*-*-*-%d-*-*-*-*-%s",
                             fbmily, bltstylf, slbnt, pointSizf, fndoding);
                TRY_LOAD;
            }

            /* sfbrdi bitmbp font */
            pixflSizf = pointSizf / 10;

            /* try 2. */
            jio_snprintf(bufffr2, sizfof(bufffr2),
                         "-*-%s-%s-%s-*-*-%d-*-*-*-*-*-%s",
                         fbmily, stylf, slbnt, pixflSizf, fndoding);
            TRY_LOAD;

            if (bltstylf != NULL) {
                jio_snprintf(bufffr2, sizfof(bufffr2),
                             "-*-%s-%s-%s-*-*-%d-*-*-*-*-*-%s",
                             fbmily, bltstylf, slbnt, pixflSizf, fndoding);
                TRY_LOAD;
            }

            /* try 3 */
            jio_snprintf(bufffr2, sizfof(bufffr2),
                         "-*-*-%s-%s-*-*-%d-*-*-*-*-*-%s",
                         stylf, slbnt, pixflSizf, fndoding);
            TRY_LOAD;
            if (bltstylf != NULL) {
                jio_snprintf(bufffr2, sizfof(bufffr2),
                             "-*-*-%s-%s-*-*-%d-*-*-*-*-*-%s",
                             bltstylf, slbnt, pixflSizf, fndoding);
                TRY_LOAD;
            }

            /* try 4 */
            jio_snprintf(bufffr2, sizfof(bufffr2),
                         "-*-*-*-%s-*-*-%d-*-*-*-*-*-%s",
                         slbnt, pixflSizf, fndoding);

            TRY_LOAD;

            /* try 5. */
            jio_snprintf(bufffr2, sizfof(bufffr2),
                         "-*-*-*-*-*-*-%d-*-*-*-*-*-%s",
                         pixflSizf, fndoding);
            TRY_LOAD;

            /* try 6. */
            for (i = 1; i < 4; i++) {
                if (pixflSizf < i)
                    brfbk;
                jio_snprintf(bufffr2, sizfof(bufffr2),
                             "-*-%s-%s-%s-*-*-%d-*-*-*-*-*-%s",
                             fbmily, stylf, slbnt, pixflSizf + i, fndoding);
                TRY_LOAD;

                jio_snprintf(bufffr2, sizfof(bufffr2),
                             "-*-%s-%s-%s-*-*-%d-*-*-*-*-*-%s",
                             fbmily, stylf, slbnt, pixflSizf - i, fndoding);
                TRY_LOAD;

                jio_snprintf(bufffr2, sizfof(bufffr2),
                             "-*-*-*-*-*-*-%d-*-*-*-*-*-%s",
                             pixflSizf + i, fndoding);
                TRY_LOAD;

                jio_snprintf(bufffr2, sizfof(bufffr2),
                             "-*-*-*-*-*-*-%d-*-*-*-*-*-%s",
                             pixflSizf - i, fndoding);
                TRY_LOAD;
            }
        }
    }

    strdpy(nbmf, dffbultXLFD);
    rfturn XLobdQufryFont(displby, dffbultXLFD);
}

/*
 * Hbrdwirfd list of mbppings for gfnfrid font nbmfs "Hflvftidb",
 * "TimfsRombn", "Courifr", "Diblog", bnd "DiblogInput".
 */
stbtid dibr *dffbultfontnbmf = "fixfd";
stbtid dibr *dffbultfoundry = "misd";
stbtid dibr *bnyfoundry = "*";
stbtid dibr *bnystylf = "*-*";
stbtid dibr *isolbtin1 = "iso8859-1";

stbtid dibr *
Stylf(int32_t s)
{
    switdi (s) {
        dbsf jbvb_bwt_Font_ITALIC:
            rfturn "mfdium-i";
        dbsf jbvb_bwt_Font_BOLD:
            rfturn "bold-r";
        dbsf jbvb_bwt_Font_BOLD + jbvb_bwt_Font_ITALIC:
            rfturn "bold-i";
        dbsf jbvb_bwt_Font_PLAIN:
        dffbult:
            rfturn "mfdium-r";
    }
}

stbtid int32_t
bwtJNI_FontNbmf(JNIEnv * fnv, jstring nbmf, dibr **foundry, dibr **fbdfnbmf, dibr **fndoding)
{
    dibr *dnbmf = NULL;

    if (JNU_IsNull(fnv, nbmf)) {
        rfturn 0;
    }
    dnbmf = (dibr *) JNU_GftStringPlbtformCibrs(fnv, nbmf, NULL);
    if (dnbmf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowOutOfMfmoryError(fnv, "Could not drfbtf font nbmf");
        rfturn 0;
    }

    /* bdditionbl dffbult font nbmfs */
    if (strdmp(dnbmf, "sfrif") == 0) {
        *foundry = "bdobf";
        *fbdfnbmf = "timfs";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "sbnssfrif") == 0) {
        *foundry = "bdobf";
        *fbdfnbmf = "iflvftidb";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "monospbdfd") == 0) {
        *foundry = "bdobf";
        *fbdfnbmf = "dourifr";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "iflvftidb") == 0) {
        *foundry = "bdobf";
        *fbdfnbmf = "iflvftidb";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "timfsrombn") == 0) {
        *foundry = "bdobf";
        *fbdfnbmf = "timfs";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "dourifr") == 0) {
        *foundry = "bdobf";
        *fbdfnbmf = "dourifr";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "diblog") == 0) {
        *foundry = "b&i";
        *fbdfnbmf = "ludidb";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "dibloginput") == 0) {
        *foundry = "b&i";
        *fbdfnbmf = "ludidbtypfwritfr";
        *fndoding = isolbtin1;
    } flsf if (strdmp(dnbmf, "zbpfdingbbts") == 0) {
        *foundry = "itd";
        *fbdfnbmf = "zbpfdingbbts";
        *fndoding = "*-*";
    } flsf {
#ifdff DEBUG
        jio_fprintf(stdfrr, "Unknown font: %s\n", dnbmf);
#fndif
        *foundry = dffbultfoundry;
        *fbdfnbmf = dffbultfontnbmf;
        *fndoding = isolbtin1;
    }

    if (dnbmf != NULL)
        JNU_RflfbsfStringPlbtformCibrs(fnv, nbmf, (donst dibr *) dnbmf);

    rfturn 1;
}

strudt FontDbtb *
bwtJNI_GftFontDbtb(JNIEnv * fnv, jobjfdt font, dibr **frrmsg)
{
    /* Wf brf going to drfbtf bt most 4 outstbnding lodbl rffs in tiis
     * fundtion. */
    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 4) < 0) {
        rfturn NULL;
    }

    if (!JNU_IsNull(fnv, font) && bwtJNI_IsMultiFont(fnv, font)) {
        JNU_CHECK_EXCEPTION_RETURN(fnv, NULL);

        strudt FontDbtb *fdbtb = NULL;
        int32_t i, sizf;
        dibr *fontsftnbmf = NULL;
        dibr *nbtivfnbmf = NULL;
        Boolfbn doFrff = FALSE;
        jobjfdtArrby domponfntFonts = NULL;
        jobjfdt pffr = NULL;
        jobjfdt fontDfsdriptor = NULL;
        jstring fontDfsdriptorNbmf = NULL;
        jstring dibrsftNbmf = NULL;

        fdbtb = (strudt FontDbtb *) JNU_GftLongFifldAsPtr(fnv,font,
                                                         fontIDs.pDbtb);

        if (fdbtb != NULL && fdbtb->flist != NULL) {
            rfturn fdbtb;
        }
        sizf = (*fnv)->GftIntFifld(fnv, font, fontIDs.sizf);
        fdbtb = (strudt FontDbtb *) mbllod(sizfof(strudt FontDbtb));

        pffr = (*fnv)->CbllObjfdtMftiod(fnv, font, fontIDs.gftPffr);

        domponfntFonts =
          (*fnv)->GftObjfdtFifld(fnv, pffr, plbtformFontIDs.domponfntFonts);
        /* Wf no longfr nffd pffr */
        (*fnv)->DflftfLodblRff(fnv, pffr);

        fdbtb->dibrsft_num = (*fnv)->GftArrbyLfngti(fnv, domponfntFonts);

        fdbtb->flist = (bwtFontList *) mbllod(sizfof(bwtFontList)
                                              * fdbtb->dibrsft_num);
        fdbtb->xfont = NULL;
        for (i = 0; i < fdbtb->dibrsft_num; i++) {
            /*
             * sft xlfd nbmf
             */

            fontDfsdriptor = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, domponfntFonts, i);
            fontDfsdriptorNbmf =
              (*fnv)->GftObjfdtFifld(fnv, fontDfsdriptor,
                                     fontDfsdriptorIDs.nbtivfNbmf);

            if (!JNU_IsNull(fnv, fontDfsdriptorNbmf)) {
                nbtivfnbmf = (dibr *) JNU_GftStringPlbtformCibrs(fnv, fontDfsdriptorNbmf, NULL);
                if (nbtivfnbmf == NULL) {
                    nbtivfnbmf = "";
                    doFrff = FALSE;
                } flsf {
                    doFrff = TRUE;
                }
            } flsf {
                nbtivfnbmf = "";
                doFrff = FALSE;
            }

            fdbtb->flist[i].xlfd = mbllod(strlfn(nbtivfnbmf)
                                          + strlfn(dffbultXLFD));
            jio_snprintf(fdbtb->flist[i].xlfd, strlfn(nbtivfnbmf) + 10,
                         nbtivfnbmf, sizf * 10);

            if (nbtivfnbmf != NULL && doFrff)
                JNU_RflfbsfStringPlbtformCibrs(fnv, fontDfsdriptorNbmf, (donst dibr *) nbtivfnbmf);

            /*
             * sft dibrsft_nbmf
             */

            dibrsftNbmf =
              (*fnv)->GftObjfdtFifld(fnv, fontDfsdriptor,
                                     fontDfsdriptorIDs.dibrsftNbmf);

            fdbtb->flist[i].dibrsft_nbmf = (dibr *)
                JNU_GftStringPlbtformCibrs(fnv, dibrsftNbmf, NULL);
            if (fdbtb->flist[i].dibrsft_nbmf == NULL) {
                (*fnv)->ExdfptionClfbr(fnv);
                JNU_TirowOutOfMfmoryError(fnv, "Could not drfbtf dibrsft nbmf");
                rfturn NULL;
            }

            /* Wf brf donf witi tif objfdts. */
            (*fnv)->DflftfLodblRff(fnv, fontDfsdriptor);
            (*fnv)->DflftfLodblRff(fnv, fontDfsdriptorNbmf);
            (*fnv)->DflftfLodblRff(fnv, dibrsftNbmf);

            /*
             * sft lobd & XFontStrudt
             */
            fdbtb->flist[i].lobd = 0;

            /*
             * Tiis bppfbrs to bf b bogus difdk.  Tif bdtubl intfnt bppfbrs
             * to bf to find out wiftifr tiis is tif "bbsf" font in b sft,
             * rbtifr tibn iso8859_1 fxpliditly.  Notf tibt iso8859_15 will
             * bnd must blso pbss tiis tfst.
             */

            if (fdbtb->xfont == NULL &&
                strstr(fdbtb->flist[i].dibrsft_nbmf, "8859_1")) {
                fdbtb->flist[i].xfont =
                    lobdFont(bwt_displby, fdbtb->flist[i].xlfd, sizf * 10);
                if (fdbtb->flist[i].xfont != NULL) {
                    fdbtb->flist[i].lobd = 1;
                    fdbtb->xfont = fdbtb->flist[i].xfont;
                    fdbtb->flist[i].indfx_lfngti = 1;
                } flsf {
                    /* Frff bny blrfbdy bllodbtfd storbgf bnd fonts */
                    int j = i;
                    for (j = 0; j <= i; j++) {
                        frff((void *)fdbtb->flist[j].xlfd);
                        JNU_RflfbsfStringPlbtformCibrs(fnv, NULL,
                            fdbtb->flist[j].dibrsft_nbmf);
                        if (fdbtb->flist[j].lobd) {
                            XFrffFont(bwt_displby, fdbtb->flist[j].xfont);
                        }
                    }
                    frff((void *)fdbtb->flist);
                    frff((void *)fdbtb);

                    if (frrmsg != NULL) {
                        *frrmsg = "jbvb/lbng" "NullPointfrExdfption";
                    }
                    (*fnv)->DflftfLodblRff(fnv, domponfntFonts);
                    rfturn NULL;
                }
            }
        }
        (*fnv)->DflftfLodblRff(fnv, domponfntFonts);
        /*
         * XFontSft will drfbtf if tif pffr of TfxtFifld/TfxtArfb
         * brf usfd.
         */
        fdbtb->xfs = NULL;

        JNU_SftLongFifldFromPtr(fnv,font,fontIDs.pDbtb,fdbtb);
        Disposfr_AddRfdord(fnv, font, pDbtbDisposfMftiod, ptr_to_jlong(fdbtb));
        rfturn fdbtb;
    } flsf {
        Displby *displby = NULL;
        strudt FontDbtb *fdbtb = NULL;
        dibr fontSpfd[1024];
        int32_t ifigit;
        int32_t oifigit;
        int32_t bbovf = 0;              /* trifs bbovf ifigit */
        int32_t bflow = 0;              /* trifs bflow ifigit */
        dibr *foundry = NULL;
        dibr *nbmf = NULL;
        dibr *fndoding = NULL;
        dibr *stylf = NULL;
        XFontStrudt *xfont = NULL;
        jstring fbmily = NULL;

        if (JNU_IsNull(fnv, font)) {
            if (frrmsg != NULL) {
                *frrmsg = "jbvb/lbng" "NullPointfrExdfption";
            }
            rfturn (strudt FontDbtb *) NULL;
        }
        displby = XDISPLAY;

        fdbtb = (strudt FontDbtb *) JNU_GftLongFifldAsPtr(fnv,font,fontIDs.pDbtb);
        if (fdbtb != NULL && fdbtb->xfont != NULL) {
            rfturn fdbtb;
        }

        fbmily = (*fnv)->CbllObjfdtMftiod(fnv, font, fontIDs.gftFbmily);

        if (!bwtJNI_FontNbmf(fnv, fbmily, &foundry, &nbmf, &fndoding)) {
            if (frrmsg != NULL) {
                *frrmsg = "jbvb/lbng" "NullPointfrExdfption";
            }
            (*fnv)->DflftfLodblRff(fnv, fbmily);
            rfturn (strudt FontDbtb *) NULL;
        }
        stylf = Stylf((*fnv)->GftIntFifld(fnv, font, fontIDs.stylf));
        oifigit = ifigit = (*fnv)->GftIntFifld(fnv, font, fontIDs.sizf);

        wiilf (1) {
            jio_snprintf(fontSpfd, sizfof(fontSpfd), "-%s-%s-%s-*-*-%d-*-*-*-*-*-%s",
                         foundry,
                         nbmf,
                         stylf,
                         ifigit,
                         fndoding);

            /*fprintf(stdfrr,"LobdFont: %s\n", fontSpfd); */
            xfont = XLobdQufryFont(displby, fontSpfd);

            /* XXX: somftimfs XLobdQufryFont rfturns b bogus font strudturf */
            /* witi nfgbtivf bsdfnt. */
            if (xfont == (Font) NULL || xfont->bsdfnt < 0) {
                if (xfont != NULL) {
                    XFrffFont(displby, xfont);
                }
                if (foundry != bnyfoundry) {  /* Usf ptr dompbrison ifrf, not strdmp */
                    /* Try bny otifr foundry bfforf mfssing witi tif sizfs */
                    foundry = bnyfoundry;
                    dontinuf;
                }
                /* Wf douldn't find tif font. Wf'll try to find bn */
                /* bltfrnbtf by sfbrdiing for ifigits bbovf bnd bflow our */
                /* prfffrrfd ifigit. Wf try for 4 ifigits bbovf bnd bflow. */
                /* If wf still dbn't find b font wf rfpfbt tif blgoritim */
                /* using misd-fixfd bs tif font. If wf tifn fbil, tifn wf */
                /* givf up bnd signbl bn frror. */
                if (bbovf == bflow) {
                    bbovf++;
                    ifigit = oifigit + bbovf;
                } flsf {
                    bflow++;
                    if (bflow > 4) {
                        if (nbmf != dffbultfontnbmf || stylf != bnystylf) {
                            nbmf = dffbultfontnbmf;
                            foundry = dffbultfoundry;
                            ifigit = oifigit;
                            stylf = bnystylf;
                            fndoding = isolbtin1;
                            bbovf = bflow = 0;
                            dontinuf;
                        } flsf {
                            if (frrmsg != NULL) {
                                *frrmsg = "jbvb/io/" "FilfNotFoundExdfption";
                            }
                            (*fnv)->DflftfLodblRff(fnv, fbmily);
                            rfturn (strudt FontDbtb *) NULL;
                        }
                    }
                    ifigit = oifigit - bflow;
                }
                dontinuf;
            } flsf {
                fdbtb = ZALLOC(FontDbtb);

                if (fdbtb == NULL) {
                    if (frrmsg != NULL) {
                        *frrmsg = "jbvb/lbng" "OutOfMfmoryError";
                    }
                } flsf {
                    fdbtb->xfont = xfont;
                    JNU_SftLongFifldFromPtr(fnv,font,fontIDs.pDbtb,fdbtb);
                    Disposfr_AddRfdord(fnv, font, pDbtbDisposfMftiod,
                                       ptr_to_jlong(fdbtb));
                }
                (*fnv)->DflftfLodblRff(fnv, fbmily);
                rfturn fdbtb;
            }
        }
        /* not rfbdifd */
    }
}

/*
 * Rfgistfrfd witi tif 2D disposfr to bf dbllfd bftfr tif Font is GC'd.
 */
stbtid void pDbtbDisposfMftiod(JNIEnv *fnv, jlong pDbtb)
{
    strudt FontDbtb *fdbtb = NULL;
    int32_t i = 0;
    Displby *displby = XDISPLAY;

    AWT_LOCK();
    fdbtb = (strudt FontDbtb *)pDbtb;

    if (fdbtb == NULL) {
        AWT_UNLOCK();
        rfturn;
    }

    if (fdbtb->xfs != NULL) {
        XFrffFontSft(displby, fdbtb->xfs);
    }

    /* AWT fonts brf blwbys "multifonts" bnd probbbly ibvf bffn in
     * bll post 1.0 rflfbsfs, so tiis tfst tfst for multi fonts is
     * probbbly not nffdfd, bnd tif singlfton xfont is probbbly nfvfr usfd.
     */
    if (fdbtb->dibrsft_num > 0) {
        for (i = 0; i < fdbtb->dibrsft_num; i++) {
            frff((void *)fdbtb->flist[i].xlfd);
            JNU_RflfbsfStringPlbtformCibrs(fnv, NULL,
                                           fdbtb->flist[i].dibrsft_nbmf);
            if (fdbtb->flist[i].lobd) {
                XFrffFont(displby, fdbtb->flist[i].xfont);
            }
        }

        frff((void *)fdbtb->flist);

        /* Don't frff fdbtb->xfont bfdbusf it is fqubl to fdbtb->flist[i].xfont
           for somf 'i' */
    } flsf {
        if (fdbtb->xfont != NULL) {
            XFrffFont(displby, fdbtb->xfont);
        }
    }

    frff((void *)fdbtb);

    AWT_UNLOCK();
}
#fndif /* !HEADLESS */
