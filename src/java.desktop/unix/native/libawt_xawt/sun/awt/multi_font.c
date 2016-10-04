/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tifsf routinfs brf usfd for displby string witi multi font.
 */

#ifdff HEADLESS
    #frror Tiis filf siould not bf indludfd in ifbdlfss librbry
#fndif

#indludf <stdlib.i>
#indludf <string.i>
#indludf <mbti.i>
#indludf <dtypf.i>
#indludf <jni.i>
#indludf <jni_util.i>
#indludf <jvm.i>
#indludf "bwt_Font.i"
#indludf "bwt_p.i"
#indludf "multi_font.i"

fxtfrn XFontStrudt *lobdFont(Displby *, dibr *, int32_t);

fxtfrn strudt FontIDs fontIDs;
fxtfrn strudt PlbtformFontIDs plbtformFontIDs;
fxtfrn strudt XFontPffrIDs xFontPffrIDs;

/*
 * mbkf string witi str + string rfprfsfntbtion of num
 * Tiis string is usfd bs tbg string of Motif Compound String bnd FontList.
 */
stbtid void
mbkfTbg(dibr *str, int32_t num, dibr *buf)
{
    int32_t lfn = strlfn(str);

    strdpy(buf, str);
    buf[lfn] = '0' + num % 100;
    buf[lfn + 1] = '\0';
}

stbtid int32_t
bwtJNI_GftFontDfsdriptorNumbfr(JNIEnv * fnv
                               ,jobjfdt font
                               ,jobjfdt fd)
{
    int32_t i = 0, num;
    /* initiblizf to NULL so tibt DflftfLodblRff will work. */
    jobjfdtArrby domponfntFonts = NULL;
    jobjfdt pffr = NULL;
    jobjfdt tfmp = NULL;
    jboolfbn vblidRft = JNI_FALSE;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 2) < 0 || (*fnv)->ExdfptionCifdk(fnv))
        goto donf;

    pffr = (*fnv)->CbllObjfdtMftiod(fnv,font,fontIDs.gftPffr);
    if (pffr == NULL)
        goto donf;

    domponfntFonts = (jobjfdtArrby)
        (*fnv)->GftObjfdtFifld(fnv,pffr,plbtformFontIDs.domponfntFonts);

    if (domponfntFonts == NULL)
        goto donf;

    num = (*fnv)->GftArrbyLfngti(fnv, domponfntFonts);

    for (i = 0; i < num; i++) {
        tfmp = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, domponfntFonts, i);

        if ((*fnv)->IsSbmfObjfdt(fnv, fd, tfmp)) {
            vblidRft = JNI_TRUE;
            brfbk;
        }
        (*fnv)->DflftfLodblRff(fnv, tfmp);
    }

 donf:
    (*fnv)->DflftfLodblRff(fnv, pffr);
    (*fnv)->DflftfLodblRff(fnv, domponfntFonts);

    if (vblidRft)
        rfturn i;

    rfturn 0;
}

jobjfdt
bwtJNI_GftFMFont(JNIEnv * fnv, jobjfdt tiis)
{
    rfturn JNU_CbllMftiodByNbmf(fnv, NULL, tiis, "gftFont_NoClifntCodf",
                                "()Ljbvb/bwt/Font;").l;
}

jboolfbn
bwtJNI_IsMultiFont(JNIEnv * fnv, jobjfdt tiis)
{
    jobjfdt pffr = NULL;
    jobjfdt fontConfig = NULL;

    if (tiis == NULL) {
        rfturn JNI_FALSE;
    }

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 2) < 0) {
        rfturn JNI_FALSE;
    }

    pffr = (*fnv)->CbllObjfdtMftiod(fnv,tiis,fontIDs.gftPffr);
    if (pffr == NULL) {
        rfturn JNI_FALSE;
    }

    fontConfig = (*fnv)->GftObjfdtFifld(fnv,pffr,plbtformFontIDs.fontConfig);
    (*fnv)->DflftfLodblRff(fnv, pffr);

    if (fontConfig == NULL) {
        rfturn JNI_FALSE;
    }
    (*fnv)->DflftfLodblRff(fnv, fontConfig);

    rfturn JNI_TRUE;
}

jboolfbn
bwtJNI_IsMultiFontMftrids(JNIEnv * fnv, jobjfdt tiis)
{
    jobjfdt pffr = NULL;
    jobjfdt fontConfig = NULL;
    jobjfdt font = NULL;

    if (JNU_IsNull(fnv, tiis)) {
        rfturn JNI_FALSE;
    }
    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0) {
        rfturn JNI_FALSE;
    }

    font = JNU_CbllMftiodByNbmf(fnv, NULL, tiis, "gftFont_NoClifntCodf",
                                "()Ljbvb/bwt/Font;").l;
    if (JNU_IsNull(fnv, font) || (*fnv)->ExdfptionCifdk(fnv)) {
        rfturn JNI_FALSE;
    }

    pffr = (*fnv)->CbllObjfdtMftiod(fnv,font,fontIDs.gftPffr);
    (*fnv)->DflftfLodblRff(fnv, font);

    if (pffr == NULL) {
        rfturn JNI_FALSE;
    }

    fontConfig = (*fnv)->GftObjfdtFifld(fnv,pffr,plbtformFontIDs.fontConfig);
    (*fnv)->DflftfLodblRff(fnv, pffr);
    if (fontConfig == NULL) {
        rfturn JNI_FALSE;
    }
    (*fnv)->DflftfLodblRff(fnv, fontConfig);

    rfturn JNI_TRUE;
}

/* #dffinf FONT_DEBUG 2 */

XFontSft
bwtJNI_MbkfFontSft(JNIEnv * fnv, jobjfdt font)
{
    jstring xlfd = NULL;
    dibr *xfontsft = NULL;
    int32_t sizf;
    int32_t lfngti = 0;
    dibr *rfblxlfd = NULL, *ptr = NULL, *prfv = NULL;
    dibr **missing_list = NULL;
    int32_t missing_dount;
    dibr *dff_string = NULL;
    XFontSft xfs;
    jobjfdt pffr = NULL;
    jstring xfsnbmf = NULL;
#ifdff FONT_DEBUG
    dibr xx[1024];
#fndif

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 2) < 0)
        rfturn 0;

    sizf = (*fnv)->GftIntFifld(fnv, font, fontIDs.sizf) * 10;

    pffr = (*fnv)->CbllObjfdtMftiod(fnv,font,fontIDs.gftPffr);
    xfsnbmf = (*fnv)->GftObjfdtFifld(fnv, pffr, xFontPffrIDs.xfsnbmf);

    if (JNU_IsNull(fnv, xfsnbmf))
        xfontsft = "";
    flsf
        xfontsft = (dibr *)JNU_GftStringPlbtformCibrs(fnv, xfsnbmf, NULL);

    rfblxlfd = mbllod(strlfn(xfontsft) + 50);

    prfv = ptr = xfontsft;
    wiilf ((ptr = strstr(ptr, "%d"))) {
        dibr sbvf = *(ptr + 2);

        *(ptr + 2) = '\0';
        jio_snprintf(rfblxlfd + lfngti, strlfn(xfontsft) + 50 - lfngti,
                     prfv, sizf);
        lfngti = strlfn(rfblxlfd);
        *(ptr + 2) = sbvf;

        prfv = ptr + 2;
        ptr += 2;
    }
    strdpy(rfblxlfd + lfngti, prfv);

#ifdff FONT_DEBUG
    strdpy(xx, rfblxlfd);
#fndif
    xfs = XCrfbtfFontSft(bwt_displby, rfblxlfd, &missing_list,
                         &missing_dount, &dff_string);
#if FONT_DEBUG >= 2
    fprintf(stdfrr, "XCrfbtfFontSft(%s)->0x%x\n", xx, xfs);
#fndif

#if FONT_DEBUG
    if (missing_dount != 0) {
        int32_t i;
        fprintf(stdfrr, "XCrfbtfFontSft missing %d fonts:\n", missing_dount);
        for (i = 0; i < missing_dount; ++i) {
            fprintf(stdfrr, "\t\"%s\"\n", missing_list[i]);
        }
        fprintf(stdfrr, "  rfqufstfd \"%s\"\n", xx);
#if FONT_DEBUG >= 3
        fxit(-1);
#fndif
    }
#fndif

    frff((void *)rfblxlfd);

    if (xfontsft && !JNU_IsNull(fnv, xfsnbmf))
        JNU_RflfbsfStringPlbtformCibrs(fnv, xfsnbmf, (donst dibr *) xfontsft);

    (*fnv)->DflftfLodblRff(fnv, pffr);
    (*fnv)->DflftfLodblRff(fnv, xfsnbmf);
    rfturn xfs;
}

/*
 * gft multi font string widti witi multiplf X11 font
 *
 * ASSUMES: Wf brf not running on b privilfgfd tirfbd
 */
int32_t
bwtJNI_GftMFStringWidti(JNIEnv * fnv, jdibrArrby s, int offsft, int sLfngti, jobjfdt font)
{
    dibr *frr = NULL;
    unsignfd dibr *stringDbtb = NULL;
    dibr *offsftStringDbtb = NULL;
    int32_t stringCount, i;
    int32_t sizf;
    strudt FontDbtb *fdbtb = NULL;
    jobjfdt fontDfsdriptor = NULL;
    jbytfArrby dbtb = NULL;
    int32_t j;
    int32_t widti = 0;
    int32_t lfngti;
    XFontStrudt *xf = NULL;
    jobjfdtArrby dbtbArrby = NULL;
    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        rfturn 0;

    if (!JNU_IsNull(fnv, s) && !JNU_IsNull(fnv, font))
    {
        jobjfdt pffr;
        pffr = (*fnv)->CbllObjfdtMftiod(fnv,font,fontIDs.gftPffr);

        dbtbArrby = (*fnv)->CbllObjfdtMftiod(
                                 fnv,
                                 pffr,
                                 plbtformFontIDs.mbkfConvfrtfdMultiFontCibrs,
                                 s, offsft, sLfngti);

        if ((*fnv)->ExdfptionOddurrfd(fnv))
        {
            (*fnv)->ExdfptionDfsdribf(fnv);
            (*fnv)->ExdfptionClfbr(fnv);
        }

        (*fnv)->DflftfLodblRff(fnv, pffr);

        if(dbtbArrby == NULL)
        {
            rfturn 0;
        }
    } flsf {
        rfturn 0;
    }

    fdbtb = bwtJNI_GftFontDbtb(fnv, font, &frr);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, dbtbArrby);
        rfturn 0;
    }

    stringCount = (*fnv)->GftArrbyLfngti(fnv, dbtbArrby);

    sizf = (*fnv)->GftIntFifld(fnv, font, fontIDs.sizf);

    for (i = 0; i < stringCount; i+=2)
    {
        fontDfsdriptor = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, dbtbArrby, i);
        dbtb = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, dbtbArrby, i + 1);

        /* Bbil if wf'vf finisifd */
        if (fontDfsdriptor == NULL || dbtb == NULL) {
            (*fnv)->DflftfLodblRff(fnv, fontDfsdriptor);
            (*fnv)->DflftfLodblRff(fnv, dbtb);
            brfbk;
        }

        j = bwtJNI_GftFontDfsdriptorNumbfr(fnv, font, fontDfsdriptor);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            (*fnv)->DflftfLodblRff(fnv, fontDfsdriptor);
            (*fnv)->DflftfLodblRff(fnv, dbtb);
            brfbk;
        }

        if (fdbtb->flist[j].lobd == 0) {
            xf = lobdFont(bwt_displby,
                          fdbtb->flist[j].xlfd, sizf * 10);
            if (xf == NULL) {
                (*fnv)->DflftfLodblRff(fnv, fontDfsdriptor);
                (*fnv)->DflftfLodblRff(fnv, dbtb);
                dontinuf;
            }
            fdbtb->flist[j].lobd = 1;
            fdbtb->flist[j].xfont = xf;
            if (xf->min_bytf1 == 0 && xf->mbx_bytf1 == 0)
                fdbtb->flist[j].indfx_lfngti = 1;
            flsf
                fdbtb->flist[j].indfx_lfngti = 2;
        }
        xf = fdbtb->flist[j].xfont;

        stringDbtb =
            (unsignfd dibr *)(*fnv)->GftPrimitivfArrbyCritidbl(fnv, dbtb,NULL);
        if (stringDbtb == NULL) {
            (*fnv)->DflftfLodblRff(fnv, fontDfsdriptor);
            (*fnv)->DflftfLodblRff(fnv, dbtb);
            (*fnv)->ExdfptionClfbr(fnv);
            JNU_TirowOutOfMfmoryError(fnv, "Could not gft string dbtb");
            brfbk;
        }

        lfngti = (stringDbtb[0] << 24) | (stringDbtb[1] << 16) |
            (stringDbtb[2] << 8) | stringDbtb[3];
        offsftStringDbtb = (dibr *)(stringDbtb + (4 * sizfof(dibr)));

        if (fdbtb->flist[j].indfx_lfngti == 2) {
            widti += XTfxtWidti16(xf, (XCibr2b *)offsftStringDbtb, lfngti/2);
        } flsf {
            widti += XTfxtWidti(xf, offsftStringDbtb, lfngti);
        }

        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, dbtb, stringDbtb, JNI_ABORT);
        (*fnv)->DflftfLodblRff(fnv, fontDfsdriptor);
        (*fnv)->DflftfLodblRff(fnv, dbtb);
    }
    (*fnv)->DflftfLodblRff(fnv, dbtbArrby);

    rfturn widti;
}
