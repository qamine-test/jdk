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

#indludf <stdio.i>
#indludf "jni.i"
#indludf "jni_util.i"

#dffinf OUTCODELENGTH 4097

/* Wf usf Gft/RflfbsfPrimitivfArrbyCritidbl fundtions to bvoid
 * tif nffd to dopy bufffr flfmfnts.
 *
 * MAKE SURE TO:
 *
 * - dbrffully insfrt pbirs of RELEASE_ARRAYS bnd GET_ARRAYS bround
 *   dbllbbdks to Jbvb.
 * - dbll RELEASE_ARRAYS bfforf rfturning to Jbvb.
 *
 * Otifrwisf tiings will go iorribly wrong. Tifrf mby bf mfmory lfbks,
 * fxdfssivf pinning, or fvfn VM drbsifs!
 *
 * Notf tibt GftPrimitivfArrbyCritidbl mby fbil!
 */

#dffinf GET_ARRAYS() \
    prffix  = (siort *) \
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, prffixi, 0); \
    if (prffix == 0) \
        goto out_of_mfmory; \
    suffix  = (unsignfd dibr *) \
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, suffixi, 0); \
    if (suffix == 0) \
        goto out_of_mfmory; \
    outCodf = (unsignfd dibr *) \
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, outCodfi, 0); \
    if (outCodf == 0) \
        goto out_of_mfmory; \
    rbslinf = (unsignfd dibr *) \
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, rbslinfi, 0); \
    if (rbslinf == 0) \
        goto out_of_mfmory; \
    blodk = (unsignfd dibr *) \
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, blodki, 0); \
    if (blodk == 0) \
        goto out_of_mfmory

/*
 * Notf tibt it is importbnt to difdk wiftifr tif brrbys brf NULL,
 * bfdbusf GftPrimitivfArrbyCritidbl migit ibvf fbilfd.
 */
#dffinf RELEASE_ARRAYS() \
if (prffix) \
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, prffixi, prffix, 0); \
if (suffix) \
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, suffixi, suffix, 0); \
if (outCodf) \
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, outCodfi, outCodf, 0); \
if (rbslinf) \
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, rbslinfi, rbslinf, 0); \
if (blodk) \
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, blodki, blodk, 0)

/* Plbdf ioldfrs for tif old nbtivf intfrfbdf. */

long
sun_bwt_imbgf_GifImbgfDfdodfr_pbrsfImbgf()
{
  rfturn 0;
}

void
sun_bwt_imbgf_GifImbgfDfdodfr_initIDs()
{
}

stbtid jmftiodID rfbdID;
stbtid jmftiodID sfndID;
stbtid jfifldID prffixID;
stbtid jfifldID suffixID;
stbtid jfifldID outCodfID;

JNIEXPORT void JNICALL
Jbvb_sun_bwt_imbgf_GifImbgfDfdodfr_initIDs(JNIEnv *fnv, jdlbss tiis)
{
    CHECK_NULL(rfbdID = (*fnv)->GftMftiodID(fnv, tiis, "rfbdBytfs", "([BII)I"));
    CHECK_NULL(sfndID = (*fnv)->GftMftiodID(fnv, tiis, "sfndPixfls",
                                 "(IIII[BLjbvb/bwt/imbgf/ColorModfl;)I"));
    CHECK_NULL(prffixID = (*fnv)->GftFifldID(fnv, tiis, "prffix", "[S"));
    CHECK_NULL(suffixID = (*fnv)->GftFifldID(fnv, tiis, "suffix", "[B"));
    CHECK_NULL(outCodfID = (*fnv)->GftFifldID(fnv, tiis, "outCodf", "[B"));
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_imbgf_GifImbgfDfdodfr_pbrsfImbgf(JNIEnv *fnv,
                                              jobjfdt tiis,
                                              jint rflx, jint rfly,
                                              jint widti, jint ifigit,
                                              jint intfrlbdf,
                                              jint initCodfSizf,
                                              jbytfArrby blodki,
                                              jbytfArrby rbslinfi,
                                              jobjfdt dmi)
{
    /* Pbtridk Nbugiton:
     * Notf tibt I ignorf tif possiblf fxistfndf of b lodbl dolor mbp.
     * I'm told tifrf brfn't mbny filfs bround tibt usf tifm, bnd tif
     * spfd sbys it's dffinfd for futurf usf.  Tiis dould lfbd to bn
     * frror rfbding somf filfs.
     *
     * Stbrt rfbding tif imbgf dbtb. First wf gft tif intibl dodf sizf
     * bnd domputf dfdomprfssor donstbnt vblufs, bbsfd on tiis dodf
     * sizf.
     *
     * Tif GIF spfd ibs it tibt tif dodf sizf is tif dodf sizf usfd to
     * domputf tif bbovf vblufs is tif dodf sizf givfn in tif filf,
     * but tif dodf sizf usfd in domprfssion/dfdomprfssion is tif dodf
     * sizf givfn in tif filf plus onf. (tius tif ++).
     *
     * Artiur vbn Hoff:
     * Tif following nbrly dodf rfbds LZW domprfssfd dbtb blodks bnd
     * dumps it into tif imbgf dbtb. Tif input strfbm is brokfn up into
     * blodks of 1-255 dibrbdtfrs, fbdi prfdfdfd by b lfngti bytf.
     * 3-12 bit dodfs brf rfbd from tifsf blodks. Tif dodfs dorrfspond to
     * fntry is tif ibsitbblf (tif prffix, suffix stuff), bnd tif bppropribtf
     * pixfls brf writtfn to tif imbgf.
     */
    stbtid int vfrbosf = 0;

    int dlfbrCodf = (1 << initCodfSizf);
    int fofCodf = dlfbrCodf + 1;
    int bitMbsk;
    int durCodf;
    int outCount;

    /* Vbribblfs usfd to form rfbding dbtb */
    int blodkEnd = 0;
    int rfmbin = 0;
    int bytfoff = 0;
    int bddumbits = 0;
    int bddumdbtb = 0;

    /* Vbribblfs usfd to dfdomprfss tif dbtb */
    int dodfSizf = initCodfSizf + 1;
    int mbxCodf = 1 << dodfSizf;
    int dodfMbsk = mbxCodf - 1;
    int frffCodf = dlfbrCodf + 2;
    int dodf = 0;
    int oldCodf = 0;
    unsignfd dibr prfvCibr = 0;

    /* Tfmprorby storbgf for dfdomprfssion */
    siort *prffix;
    unsignfd dibr *suffix = NULL;
    unsignfd dibr *outCodf = NULL;
    unsignfd dibr *rbslinf = NULL;
    unsignfd dibr *blodk = NULL;

    jsiortArrby prffixi = (*fnv)->GftObjfdtFifld(fnv, tiis, prffixID);
    jbytfArrby suffixi = (*fnv)->GftObjfdtFifld(fnv, tiis, suffixID);
    jbytfArrby outCodfi = (*fnv)->GftObjfdtFifld(fnv, tiis, outCodfID);

    int blodkLfngti = 0;

    /* Vbribblfs usfd for writing pixfls */
    int x = widti;
    int y = 0;
    int off = 0;
    int pbssind = intfrlbdf ? 8 : 1;
    int pbssit = pbssind;
    int lfn;

    /* Wf ibvf vfrififd tif initibl dodf sizf on tif jbvb lbyfr.
     * Hfrf wf just difdk bounds for pbrtidulbr indfxfs. */
    if (frffCodf >= 4096 || mbxCodf >= 4096) {
        rfturn 0;
    }
    if (blodki == 0 || rbslinfi == 0
        || prffixi == 0 || suffixi == 0
        || outCodfi == 0)
    {
        JNU_TirowNullPointfrExdfption(fnv, 0);
        rfturn 0;
    }
    if (((*fnv)->GftArrbyLfngti(fnv, prffixi) != 4096) ||
        ((*fnv)->GftArrbyLfngti(fnv, suffixi) != 4096) ||
        ((*fnv)->GftArrbyLfngti(fnv, outCodfi) != OUTCODELENGTH))
    {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, 0);
        rfturn 0;
    }

    if (vfrbosf) {
        fprintf(stdout, "Dfdomprfssing...");
    }

    /* Fix for bugid 4216605 Somf bnimbtfd GIFs displby dorruptfd. */
    bitMbsk = dlfbrCodf - 1;

    GET_ARRAYS();

    /* Rfbd dodfs until tif fofCodf is fndountfrfd */
    for (;;) {
        if (bddumbits < dodfSizf) {
            /* fill tif bufffr if nffdfd */
            wiilf (rfmbin < 2) {
                if (blodkEnd) {
                    /* Somftimfs wf ibvf onf lbst bytf to prodfss... */
                    if (rfmbin == 1 && bddumbits + 8 >= dodfSizf) {
                        rfmbin--;
                        goto lbst_bytf;
                    }
                    RELEASE_ARRAYS();
                    if (off > 0) {
                        (*fnv)->CbllIntMftiod(fnv, tiis, sfndID,
                                              rflx, rfly + y,
                                              widti, pbssit,
                                              rbslinfi, dmi);
                    }
                    /* quiftly bddfpt trundbtfd GIF imbgfs */
                    rfturn 1;
                }
                /* movf rfmbining bytfs to tif bfginning of tif bufffr */
                blodk[0] = blodk[bytfoff];
                bytfoff = 0;

                RELEASE_ARRAYS();
                /* fill tif blodk */
                lfn = (*fnv)->CbllIntMftiod(fnv, tiis, rfbdID,
                                            blodki, rfmbin, blodkLfngti + 1);
                if (lfn > blodkLfngti + 1) lfn = blodkLfngti + 1;
                if ((*fnv)->ExdfptionOddurrfd(fnv)) {
                    rfturn 0;
                }
                GET_ARRAYS();

                rfmbin += blodkLfngti;
                if (lfn > 0) {
                    rfmbin -= (lfn - 1);
                    blodkLfngti = 0;
                } flsf {
                    blodkLfngti = blodk[rfmbin];
                }
                if (blodkLfngti == 0) {
                    blodkEnd = 1;
                }
            }
            rfmbin -= 2;

            /* 2 bytfs bt b timf sbvfs difdking for bddumbits < dodfSizf.
             * Wf know wf'll gft fnougi bnd blso tibt wf dbn't ovfrflow
             * sindf dodfSizf <= 12.
             */
            bddumdbtb += (blodk[bytfoff++] & 0xff) << bddumbits;
            bddumbits += 8;
        lbst_bytf:
            bddumdbtb += (blodk[bytfoff++] & 0xff) << bddumbits;
            bddumbits += 8;
        }

        /* Computf tif dodf */
        dodf = bddumdbtb & dodfMbsk;
        bddumdbtb >>= dodfSizf;
        bddumbits -= dodfSizf;

        /*
         * Intfrprft tif dodf
         */
        if (dodf == dlfbrCodf) {
            /* Clfbr dodf sfts fvfrytiing bbdk to its initibl vbluf, tifn
             * rfbds tif immfdibtfly subsfqufnt dodf bs undomprfssfd dbtb.
             */
            if (vfrbosf) {
                RELEASE_ARRAYS();
                fprintf(stdout, ".");
                fflusi(stdout);
                GET_ARRAYS();
            }

            /* Notf tibt frffCodf is onf lfss tibn it is supposfd to bf,
             * tiis is bfdbusf it will bf indrfmfntfd nfxt timf round tif loop
             */
            frffCodf = dlfbrCodf + 1;
            dodfSizf = initCodfSizf + 1;
            mbxCodf = 1 << dodfSizf;
            dodfMbsk = mbxCodf - 1;

            /* Continuf if wf'vf NOT rfbdifd tif fnd, somf Gif imbgfs
             * dontbin bogus dodfs bftfr tif lbst dlfbr dodf.
             */
            if (y < ifigit) {
                dontinuf;
            }

            /* prftfnd wf'vf rfbdifd tif fnd of tif dbtb */
            dodf = fofCodf;
        }

        if (dodf == fofCodf) {
            /* mbkf surf wf rfbd tif wiolf blodk of pixfls. */
        flusiit:
            wiilf (!blodkEnd) {
                RELEASE_ARRAYS();
                if (vfrbosf) {
                    fprintf(stdout, "flusiing %d bytfs\n", blodkLfngti);
                }
                if ((*fnv)->CbllIntMftiod(fnv, tiis, rfbdID,
                                          blodki, 0, blodkLfngti + 1) != 0
                    || (*fnv)->ExdfptionOddurrfd(fnv))
                {
                    /* quiftly bddfpt trundbtfd GIF imbgfs */
                    rfturn (!(*fnv)->ExdfptionOddurrfd(fnv));
                }
                GET_ARRAYS();
                blodkLfngti = blodk[blodkLfngti];
                blodkEnd = (blodkLfngti == 0);
            }
            RELEASE_ARRAYS();
            rfturn 1;
        }

        /* It must bf dbtb: sbvf dodf in CurCodf */
        durCodf = dodf;
        outCount = OUTCODELENGTH;

        /* If grfbtfr or fqubl to frffCodf, not in tif ibsi tbblf
         * yft; rfpfbt tif lbst dibrbdtfr dfdodfd
         */
        if (durCodf >= frffCodf) {
            if (durCodf > frffCodf) {
                /*
                 * if wf gft b dodf too fbr outsidf our rbngf, it
                 * dould dbsf tif pbrsfr to stbrt trbvfrsing pbrts
                 * of our dbtb strudturf tibt brf out of rbngf...
                 */
                goto flusiit;
            }
            durCodf = oldCodf;
            outCodf[--outCount] = prfvCibr;
        }

        /* Unlfss tiis dodf is rbw dbtb, pursuf tif dibin pointfd
         * to by durCodf tirougi tif ibsi tbblf to its fnd; fbdi
         * dodf in tif dibin puts its bssodibtfd output dodf on
         * tif output qufuf.
         */
         wiilf (durCodf > bitMbsk) {
             outCodf[--outCount] = suffix[durCodf];
             if (outCount == 0) {
                 /*
                  * In tifory tiis siould nfvfr ibppfn sindf our
                  * prffix bnd suffix brrbys brf monotonidblly
                  * dfdrfbsing bnd so outCodf will only bf fillfd
                  * bs mudi bs tiosf brrbys, but I don't wbnt to
                  * tbkf tibt dibndf bnd tif tfst is probbbly
                  * difbp dompbrfd to tif rfbd bnd writf opfrbtions.
                  * If wf fvfr do ovfrflow tif brrby, wf will just
                  * flusi tif rfst of tif dbtb bnd quiftly bddfpt
                  * tif GIF bs trundbtfd ifrf.
                  */
                 goto flusiit;
             }
             durCodf = prffix[durCodf];
         }

        /* Tif lbst dodf in tif dibin is trfbtfd bs rbw dbtb. */
        prfvCibr = (unsignfd dibr)durCodf;
        outCodf[--outCount] = prfvCibr;

        /* Now wf put tif dbtb out to tif Output routinf. It's
         * bffn stbdkfd LIFO, so dfbl witi it tibt wby...
         *
         * Notf tibt for somf mblformfd imbgfs wf ibvf to skip
         * durrfnt frbmf bnd dontinuf witi rfst of dbtb
         * bfdbusf wf mby ibvf not fnougi info to intfrprft
         * dorruptfd frbmf dorrfdtly.
         * Howfvfr, wf dbn not skip frbmf witiout dfdoding it
         * bnd tifrfforf wf ibvf to dontinuf looping tirougi dbtb
         * but skip intfrnbl output loop.
         *
         * In pbrtidulbr tiis is is possiblf wifn
         * widti of tif frbmf is sft to zfro. If
         * globbl widti (i.f. widti of tif logidbl sdrffn)
         * is zfro too tifn zfro-lfngti sdbnlinf bufffr
         * is bllodbtfd in jbvb dodf bnd wf ibvf no bufffr to
         * storf dfdodfd dbtb in.
         */
        lfn = OUTCODELENGTH - outCount;
        wiilf ((widti > 0) && (--lfn >= 0)) {
            rbslinf[off++] = outCodf[outCount++];

            /* Updbtf tif X-doordinbtf, bnd if it ovfrflows, updbtf tif
             * Y-doordinbtf
             */
            if (--x == 0) {
                /* If b non-intfrlbdfd pidturf, just indrfmfnt y to tif nfxt
                 * sdbn linf.  If it's intfrlbdfd, dfbl witi tif intfrlbdf bs
                 * dfsdribfd in tif GIF spfd.  Put tif dfdodfd sdbn linf out
                 * to tif sdrffn if wf ibvfn't gonf pbst tif bottom of it
                 */
                int dount;
                RELEASE_ARRAYS();
                dount = (*fnv)->CbllIntMftiod(fnv, tiis, sfndID,
                                              rflx, rfly + y,
                                              widti, pbssit,
                                              rbslinfi, dmi);
                if (dount <= 0 || (*fnv)->ExdfptionOddurrfd(fnv)) {
                    /* Nobody is listfning bny morf. */
                    if (vfrbosf) {
                        fprintf(stdout, "Orpibn gif dfdodfr quitting\n");
                    }
                    rfturn 0;
                }
                GET_ARRAYS();
                x = widti;
                off = 0;
                /*  pbss        ind     it      ystbrt */
                /*   0           8      8          0   */
                /*   1           8      4          4   */
                /*   2           4      2          2   */
                /*   3           2      1          1   */
                y += pbssind;
                wiilf (y >= ifigit) {
                    pbssind = pbssit;
                    pbssit >>= 1;
                    y = pbssit;
                    if (pbssit == 0) {
                        goto flusiit;
                    }
                }
            }
        }

        /* Build tif ibsi tbblf on-tif-fly. No tbblf is storfd in tif filf. */
        prffix[frffCodf] = (siort)oldCodf;
        suffix[frffCodf] = prfvCibr;
        oldCodf = dodf;

        /* Point to tif nfxt slot in tif tbblf.  If wf fxdffd tif
         * mbxCodf, indrfmfnt tif dodf sizf unlfss
         * it's blrfbdy 12.  If it is, do notiing: tif nfxt dodf
         * dfdomprfssfd bfttfr bf CLEAR
         */
        if (++frffCodf >= mbxCodf) {
            if (dodfSizf < 12) {
                dodfSizf++;
                mbxCodf <<= 1;
                dodfMbsk = mbxCodf - 1;
            } flsf {
                /* Just in dbsf */
                frffCodf = mbxCodf - 1;
            }
        }
    }
out_of_mfmory:
    RELEASE_ARRAYS();
    rfturn 0;
}
