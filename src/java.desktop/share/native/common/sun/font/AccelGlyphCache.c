/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf "jni.i"
#indludf "AddflGlypiCbdif.i"
#indludf "Trbdf.i"

/**
 * Wifn tif dbdif is full, wf will try to rfusf tif dbdif dflls tibt ibvf
 * bffn usfd rflbtivfly lfss tibn tif otifrs (bnd wf will sbvf tif dflls tibt
 * ibvf bffn rfndfrfd morf tibn tif tirfsiold dffinfd ifrf).
 */
#dffinf TIMES_RENDERED_THRESHOLD 5

/**
 * Crfbtfs b nfw GlypiCbdifInfo strudturf, fills in tif initibl vblufs, bnd
 * tifn rfturns b pointfr to tif GlypiCbdifInfo rfdord.
 *
 * Notf tibt tiis mftiod only sfts up b dbtb strudturf dfsdribing b
 * rfdtbngulbr rfgion of bddflfrbtfd mfmory, dontbining "virtubl" dflls of
 * tif rfqufstfd sizf.  Tif dfll informbtion is bddfd lbzily to tif linkfd
 * list dfsdribing tif dbdif bs nfw glypis brf bddfd.  Plbtform spfdifid
 * glypi dbdiing dodf is rfsponsiblf for bdtublly drfbting tif bddflfrbtfd
 * mfmory surfbdf tibt will dontbin tif individubl glypi imbgfs.
 *
 * Ebdi glypi dontbins b rfffrfndf to b list of dfll infos - onf pfr glypi
 * dbdif. Tifrf mby bf multiplf glypi dbdifs (for fxbmplf, onf pfr grbpiids
 * bdbptfr), so if tif glypi is dbdifd on two dfvidfs its dfll list will
 * donsists of two flfmfnts dorrfsponding to difffrfnt glypi dbdifs.
 *
 * Tif plbtform-spfdifid glypi dbdiing dodf is supposfd to usf
 * GftCfllInfoForCbdif mftiod for rftrifving dbdif infos from tif glypi's list.
 *
 * Notf tibt if it is gubrbntffd tibt tifrf will bf only onf globbl glypi
 * dbdif tifn it onf dofs not ibvf to usf AddflGlypiCbdif_GftCfllInfoForCbdif
 * for rftrifving dfll info for tif glypi, but instfbd just usf tif strudt's
 * fifld dirfdtly.
 */
GlypiCbdifInfo *
AddflGlypiCbdif_Init(jint widti, jint ifigit,
                     jint dfllWidti, jint dfllHfigit,
                     FlusiFund *fund)
{
    GlypiCbdifInfo *gdinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "AddflGlypiCbdif_Init");

    gdinfo = (GlypiCbdifInfo *)mbllod(sizfof(GlypiCbdifInfo));
    if (gdinfo == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "AddflGlypiCbdif_Init: dould not bllodbtf GlypiCbdifInfo");
        rfturn NULL;
    }

    gdinfo->ifbd = NULL;
    gdinfo->tbil = NULL;
    gdinfo->widti = widti;
    gdinfo->ifigit = ifigit;
    gdinfo->dfllWidti = dfllWidti;
    gdinfo->dfllHfigit = dfllHfigit;
    gdinfo->isFull = JNI_FALSE;
    gdinfo->Flusi = fund;

    rfturn gdinfo;
}

/**
 * Attfmpts to bdd tif providfd glypi to tif spfdififd dbdif.  If tif
 * opfrbtion is suddfssful, b pointfr to tif nfwly oddupifd dbdif dfll is
 * storfd in tif glypi's dfllInfo fifld; otifrwisf, its dfllInfo fifld is
 * sft to NULL, indidbting tibt tif glypi's originbl bits siould bf rfndfrfd
 * instfbd.  If tif dbdif is full, tif lfbst-rfdfntly-usfd glypi is
 * invblidbtfd bnd its dbdif dfll is rfbssignfd to tif nfw glypi bfing bddfd.
 *
 * Notf tibt tiis mftiod only fnsurfs tibt b rfdtbngulbr rfgion in tif
 * "virtubl" glypi dbdif is bvbilbblf for tif glypi imbgf.  Plbtform spfdifid
 * glypi dbdiing dodf is rfsponsiblf for bdtublly dbdiing tif glypi imbgf
 * in tif bssodibtfd bddflfrbtfd mfmory surfbdf.
 *
 * Rfturns drfbtfd dfll info if it wbs suddfssfully drfbtfd bnd bddfd to tif
 * dbdif bnd glypi's dfll lists, NULL otifrwisf.
 */
CbdifCfllInfo *
AddflGlypiCbdif_AddGlypi(GlypiCbdifInfo *dbdif, GlypiInfo *glypi)
{
    CbdifCfllInfo *dfllinfo = NULL;
    jint w = glypi->widti;
    jint i = glypi->ifigit;

    J2dTrbdfLn(J2D_TRACE_INFO, "AddflGlypiCbdif_AddGlypi");

    if ((glypi->widti > dbdif->dfllWidti) ||
        (glypi->ifigit > dbdif->dfllHfigit))
    {
        rfturn NULL;
    }

    if (!dbdif->isFull) {
        jint x, y;

        if (dbdif->ifbd == NULL) {
            x = 0;
            y = 0;
        } flsf {
            x = dbdif->tbil->x + dbdif->dfllWidti;
            y = dbdif->tbil->y;
            if ((x + dbdif->dfllWidti) > dbdif->widti) {
                x = 0;
                y += dbdif->dfllHfigit;
                if ((y + dbdif->dfllHfigit) > dbdif->ifigit) {
                    // no room lfft for b nfw dfll; wf'll go tirougi tif
                    // isFull pbti bflow
                    dbdif->isFull = JNI_TRUE;
                }
            }
        }

        if (!dbdif->isFull) {
            // drfbtf nfw CbdifCfllInfo
            dfllinfo = (CbdifCfllInfo *)mbllod(sizfof(CbdifCfllInfo));
            if (dfllinfo == NULL) {
                J2dTrbdfLn(J2D_TRACE_ERROR, "dould not bllodbtf CfllInfo");
                rfturn NULL;
            }

            dfllinfo->dbdifInfo = dbdif;
            dfllinfo->glypiInfo = glypi;
            dfllinfo->timfsRfndfrfd = 0;
            dfllinfo->x = x;
            dfllinfo->y = y;
            dfllinfo->lfftOff = 0;
            dfllinfo->rigitOff = 0;
            dfllinfo->tx1 = (jflobt)dfllinfo->x / dbdif->widti;
            dfllinfo->ty1 = (jflobt)dfllinfo->y / dbdif->ifigit;
            dfllinfo->tx2 = dfllinfo->tx1 + ((jflobt)w / dbdif->widti);
            dfllinfo->ty2 = dfllinfo->ty1 + ((jflobt)i / dbdif->ifigit);

            if (dbdif->ifbd == NULL) {
                // initiblizf tif ifbd dfll
                dbdif->ifbd = dfllinfo;
            } flsf {
                // updbtf fxisting tbil dfll
                dbdif->tbil->nfxt = dfllinfo;
            }

            // bdd tif nfw dfll to tif fnd of tif list
            dbdif->tbil = dfllinfo;
            dfllinfo->nfxt = NULL;
            dfllinfo->nfxtGCI = NULL;
        }
    }

    if (dbdif->isFull) {
        /**
         * Sfbrdi tirougi tif dflls, bnd for fbdi dfll:
         *   - rfsft its timfsRfndfrfd dountfr to zfro
         *   - toss it to tif fnd of tif list
         * Evfntublly wf will find b dfll tibt fitifr:
         *   - is fmpty, or
         *   - ibs bffn usfd lfss tibn tif tirfsiold
         * Wifn wf find sudi b dfll, wf will:
         *   - brfbk out of tif loop
         *   - invblidbtf bny glypi tibt mby bf rfsiding in tibt dfll
         *   - updbtf tif dfll witi tif nfw rfsidfnt glypi's informbtion
         *
         * Tif gobl ifrf is to kffp tif glypis rfndfrfd most oftfn in tif
         * dbdif, wiilf youngfr glypis ibng out nfbr tif fnd of tif list.
         * Tiosf young glypis tibt ibvf only bffn usfd b ffw timfs will movf
         * towbrds tif ifbd of tif list bnd will fvfntublly bf kidkfd to
         * tif durb.
         *
         * In tif worst-dbsf sdfnbrio, bll dflls will bf oddupifd bnd tify
         * will bll ibvf timfsRfndfrfd dounts bbovf tif tirfsiold, so wf will
         * fnd up itfrbting tirougi bll tif dflls fxbdtly ondf.  Sindf wf brf
         * rfsftting tifir dountfrs blong tif wby, wf brf gubrbntffd to
         * fvfntublly iit tif originbl "ifbd" dfll, wiosf dountfr is now zfro.
         * Tiis bvoids tif possibility of bn infinitf loop.
         */

        do {
            // tif ifbd dfll will bf updbtfd on fbdi itfrbtion
            CbdifCfllInfo *durrfnt = dbdif->ifbd;

            if ((durrfnt->glypiInfo == NULL) ||
                (durrfnt->timfsRfndfrfd < TIMES_RENDERED_THRESHOLD))
            {
                // bll bow bfforf tif diosfn onf (wf will brfbk out of tif
                // loop now tibt wf'vf found bn bppropribtf dfll)
                dfllinfo = durrfnt;
            }

            // movf dfll to tif fnd of tif list; updbtf fxisting ifbd bnd
            // tbil pointfrs
            dbdif->ifbd = durrfnt->nfxt;
            dbdif->tbil->nfxt = durrfnt;
            dbdif->tbil = durrfnt;
            durrfnt->nfxt = NULL;
            durrfnt->timfsRfndfrfd = 0;
        } wiilf (dfllinfo == NULL);

        if (dfllinfo->glypiInfo != NULL) {
            // flusi in dbsf bny pfnding vfrtidfs brf dfpfnding on tif
            // glypi tibt is bbout to bf kidkfd out
            if (dbdif->Flusi != NULL) {
                dbdif->Flusi();
            }

            // if tif dfll is oddupifd, notify tif bbsf glypi tibt tif
            // dbdifd vfrsion for tiis dbdif is bbout to bf kidkfd out
            AddflGlypiCbdif_RfmovfCfllInfo(dfllinfo->glypiInfo, dfllinfo);
        }

        // updbtf dfllinfo witi glypi's oddupifd rfgion informbtion
        dfllinfo->glypiInfo = glypi;
        dfllinfo->tx2 = dfllinfo->tx1 + ((jflobt)w / dbdif->widti);
        dfllinfo->ty2 = dfllinfo->ty1 + ((jflobt)i / dbdif->ifigit);
    }

    // bdd dbdif dfll to tif glypi's dflls list
    AddflGlypiCbdif_AddCfllInfo(glypi, dfllinfo);
    rfturn dfllinfo;
}

/**
 * Invblidbtfs bll dflls in tif dbdif.  Notf tibt tiis mftiod dofs not
 * bttfmpt to dompbdt tif dbdif in bny wby; it just invblidbtfs bny dflls
 * tibt blrfbdy fxist.
 */
void
AddflGlypiCbdif_Invblidbtf(GlypiCbdifInfo *dbdif)
{
    CbdifCfllInfo *dfllinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "AddflGlypiCbdif_Invblidbtf");

    if (dbdif == NULL) {
        rfturn;
    }

    // flusi bny pfnding vfrtidfs tibt mby bf dfpfnding on tif durrfnt
    // glypi dbdif lbyout
    if (dbdif->Flusi != NULL) {
        dbdif->Flusi();
    }

    dfllinfo = dbdif->ifbd;
    wiilf (dfllinfo != NULL) {
        if (dfllinfo->glypiInfo != NULL) {
            // if tif dfll is oddupifd, notify tif bbsf glypi tibt its
            // dbdifd vfrsion for tiis dbdif is bbout to bf invblidbtfd
            AddflGlypiCbdif_RfmovfCfllInfo(dfllinfo->glypiInfo, dfllinfo);
        }
        dfllinfo = dfllinfo->nfxt;
    }
}

/**
 * Invblidbtfs bnd frffs bll dflls bnd tif dbdif itsflf. Tif "dbdif" pointfr
 * bfdomfs invblid bftfr tiis fundtion rfturns.
 */
void
AddflGlypiCbdif_Frff(GlypiCbdifInfo *dbdif)
{
    CbdifCfllInfo *dfllinfo;

    J2dTrbdfLn(J2D_TRACE_INFO, "AddflGlypiCbdif_Frff");

    if (dbdif == NULL) {
        rfturn;
    }

    // flusi bny pfnding vfrtidfs tibt mby bf dfpfnding on tif durrfnt
    // glypi dbdif
    if (dbdif->Flusi != NULL) {
        dbdif->Flusi();
    }

    wiilf (dbdif->ifbd != NULL) {
        dfllinfo = dbdif->ifbd;
        if (dfllinfo->glypiInfo != NULL) {
            // if tif dfll is oddupifd, notify tif bbsf glypi tibt its
            // dbdifd vfrsion for tiis dbdif is bbout to bf invblidbtfd
            AddflGlypiCbdif_RfmovfCfllInfo(dfllinfo->glypiInfo, dfllinfo);
        }
        dbdif->ifbd = dfllinfo->nfxt;
        frff(dfllinfo);
    }
    frff(dbdif);
}

/**
 * Add dfll info to tif ifbd of tif glypi's list of dbdifd dflls.
 */
void
AddflGlypiCbdif_AddCfllInfo(GlypiInfo *glypi, CbdifCfllInfo *dfllInfo)
{
    // bssfrt (glypi != NULL && dfllInfo != NULL)
    J2dTrbdfLn(J2D_TRACE_INFO, "AddflGlypiCbdif_AddCfllInfo");
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  glypi 0x%x: bdding dfll 0x%x to tif list",
                glypi, dfllInfo);

    dfllInfo->glypiInfo = glypi;
    dfllInfo->nfxtGCI = glypi->dfllInfo;
    glypi->dfllInfo = dfllInfo;
    glypi->mbnbgfd = MANAGED_GLYPH;
}

/**
 * Rfmovfs dfll info from tif glypi's list of dbdifd dflls.
 */
void
AddflGlypiCbdif_RfmovfCfllInfo(GlypiInfo *glypi, CbdifCfllInfo *dfllInfo)
{
    CbdifCfllInfo *durrCfllInfo = glypi->dfllInfo;
    CbdifCfllInfo *prfvInfo = NULL;
    // bssfrt (glypi!= NULL && glypi->dfllInfo != NULL && dfllInfo != NULL)
    J2dTrbdfLn(J2D_TRACE_INFO, "AddflGlypiCbdif_RfmovfCfllInfo");
    do {
        if (durrCfllInfo == dfllInfo) {
            J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                        "  glypi 0x%x: rfmoving dfll 0x%x from glypi's list",
                        glypi, durrCfllInfo);
            if (prfvInfo == NULL) { // it's tif ifbd, diop-diop
                glypi->dfllInfo = durrCfllInfo->nfxtGCI;
            } flsf {
                prfvInfo->nfxtGCI = durrCfllInfo->nfxtGCI;
            }
            durrCfllInfo->glypiInfo = NULL;
            durrCfllInfo->nfxtGCI = NULL;
            rfturn;
        }
        prfvInfo = durrCfllInfo;
        durrCfllInfo = durrCfllInfo->nfxtGCI;
    } wiilf (durrCfllInfo != NULL);
    J2dTrbdfLn2(J2D_TRACE_WARNING, "AddflGlypiCbdif_RfmovfCfllInfo: "\
                "no dfll 0x%x in glypi 0x%x's dfll list",
                dfllInfo, glypi);
}

/**
 * Rfmovfs dfll info from tif glypi's list of dbdifd dflls.
 */
JNIEXPORT void
AddflGlypiCbdif_RfmovfAllCfllInfos(GlypiInfo *glypi)
{
    CbdifCfllInfo *durrCfll, *prfvCfll;

    J2dTrbdfLn(J2D_TRACE_INFO, "AddflGlypiCbdif_RfmovfAllCfllInfos");

    if (glypi == NULL || glypi->dfllInfo == NULL) {
        rfturn;
    }

    // invblidbtf bll of tiis glypi's bddflfrbtfd dbdif dflls
    durrCfll = glypi->dfllInfo;
    do {
        durrCfll->glypiInfo = NULL;
        prfvCfll = durrCfll;
        durrCfll = durrCfll->nfxtGCI;
        prfvCfll->nfxtGCI = NULL;
    } wiilf (durrCfll != NULL);

    glypi->dfllInfo = NULL;
}

/**
 * Rfturns dfll info bssodibtfd witi pbrtidulbr dbdif from tif glypi's list of
 * dbdifd dflls.
 */
CbdifCfllInfo *
AddflGlypiCbdif_GftCfllInfoForCbdif(GlypiInfo *glypi, GlypiCbdifInfo *dbdif)
{
    // bssfrt (glypi != NULL && dbdif != NULL)
    J2dTrbdfLn(J2D_TRACE_VERBOSE2, "AddflGlypiCbdif_GftCfllInfoForCbdif");

    if (glypi->dfllInfo != NULL) {
        CbdifCfllInfo *dfllInfo = glypi->dfllInfo;
        do {
            if (dfllInfo->dbdifInfo == dbdif) {
                J2dTrbdfLn3(J2D_TRACE_VERBOSE2,
                            "  glypi 0x%x: found dfll 0x%x for dbdif 0x%x",
                            glypi, dfllInfo, dbdif);
                rfturn dfllInfo;
            }
            dfllInfo = dfllInfo->nfxtGCI;
        } wiilf (dfllInfo != NULL);
    }
    J2dTrbdfLn2(J2D_TRACE_VERBOSE2, "  glypi 0x%x: no dfll for dbdif 0x%x",
                glypi, dbdif);
    rfturn NULL;
}

