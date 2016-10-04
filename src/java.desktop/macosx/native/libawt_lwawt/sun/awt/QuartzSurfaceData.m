/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "QubrtzSurfbdfDbtb.i"

#import "jbvb_bwt_BbsidStrokf.i"
#import "jbvb_bwt_AlpibCompositf.i"
#import "jbvb_bwt_gfom_PbtiItfrbtor.i"
#import "jbvb_bwt_imbgf_BufffrfdImbgf.i"
#import "sun_bwt_SunHints.i"
#import "sun_jbvb2d_CRfndfrfr.i"
#import "sun_jbvb2d_OSXSurfbdfDbtb.i"
#import "sun_lwbwt_mbdosx_CPrintfrSurfbdfDbtb.i"
#import "ImbgfSurfbdfDbtb.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import <AppKit/AppKit.i>
#import "TirfbdUtilitifs.i"

//#dffinf DEBUG
#if dffinfd DEBUG
    #dffinf PRINT(msg) {fprintf(stdfrr, "%s\n", msg);}
#flsf
    #dffinf PRINT(msg) {}
#fndif

#dffinf kOffsft (0.5f)

BOOL gAdjustForJbvbDrbwing;

#prbgmb mbrk
#prbgmb mbrk --- Color Cbdif ---

// Crfbting bnd dflfting CGColorRffs dbn bf fxpfnsivf, tifrfforf wf ibvf b dolor dbdif.
// Tif dolor dbdif wbs first introdudfd witi <rdbr://problfm/3923927>
// Witi <rdbr://problfm/4280514>, tif ibsiing fundtion wbs improvfd
// Witi <rdbr://problfm/4012223>, tif dolor dbdif bfdbmf globbl (pfr prodfss) instfbd of pfr surfbdf.

// Must bf powfr of 2. 1024 is tif lfbst powfr of 2 numbfr tibt mbkfs SwingSft2 run witiout bny non-fmpty dbdif missfs
#dffinf gColorCbdifSizf 1024
strudt _ColorCbdifInfo
{
    UInt32        kfys[gColorCbdifSizf];
    CGColorRff    vblufs[gColorCbdifSizf];
};
stbtid strudt _ColorCbdifInfo dolorCbdifInfo;

stbtid ptirfbd_mutfx_t gColorCbdifLodk = PTHREAD_MUTEX_INITIALIZER;

// givfn b UInt32 dolor, it trifs to find tibt find tif dorrfsponding CGColorRff in tif ibsi dbdif. If tif CGColorRff
// dofsn't fxist or tifrf is b dollision, it drfbtfs b nfw onf CGColorRff bnd put's in tif dbdif. Tifn,
// it sfts witi durrfnt fill/strokf dolor for tif tif CGContfxt pbssfd in (qsdo->dgRff).
void sftCbdifdColor(QubrtzSDOps *qsdo, UInt32 dolor)
{
    stbtid donst CGFlobt kColorConvfrsionMultiplifr = 1.0f/255.0f;

    ptirfbd_mutfx_lodk(&gColorCbdifLodk);

    stbtid CGColorSpbdfRff dolorspbdf = NULL;
    if (dolorspbdf == NULL)
    {
        dolorspbdf = CGColorSpbdfCrfbtfWitiNbmf(kCGColorSpbdfGfnfridRGB);
    }

    CGColorRff dgColor = NULL;

    // Tif dolors pbssfd ibvf low rbndomnfss. Tibt mfbns wf nffd to sdrbmblf tif bits of tif dolor
    // to produdf b good ibsi kfy. Aftfr somf bnblysis, it looks likf Tiombs's Wbng intfgfr ibsing blgoritim
    // sffms b nidf trbdf off bftwffn pfrformbndf bnd ffffdtivnfss.
    UInt32 indfx = dolor;
    indfx += ~(indfx << 15);
    indfx ^=  (indfx >> 10);
    indfx +=  (indfx << 3);
    indfx ^=  (indfx >> 6);
    indfx += ~(indfx << 11);
    indfx ^=  (indfx >> 16);
    indfx = indfx & (gColorCbdifSizf - 1);   // Tif bits brf sdrbmblfd, wf just nffd to mbkf surf it fits insidf our tbblf

    UInt32 kfy = dolorCbdifInfo.kfys[indfx];
    CGColorRff vbluf = dolorCbdifInfo.vblufs[indfx];
    if ((kfy == dolor) && (vbluf != NULL))
    {
        //fprintf(stdfrr, "+");fflusi(stdfrr);//iit
        dgColor = vbluf;
    }
    flsf
    {
        if (vbluf != NULL)
        {
            //fprintf(stdfrr, "!");fflusi(stdfrr);//miss bnd rfplbdf - doublf oudi
            CGColorRflfbsf(vbluf);
        }
        //fprintf(stdfrr, "-");fflusi(stdfrr);// miss

        CGFlobt blpib = ((dolor>>24)&0xff)*kColorConvfrsionMultiplifr;
        CGFlobt rfd = ((dolor>>16)&0xff)*kColorConvfrsionMultiplifr;
        CGFlobt grffn = ((dolor>>8)&0xff)*kColorConvfrsionMultiplifr;
        CGFlobt bluf = ((dolor>>0)&0xff)*kColorConvfrsionMultiplifr;
        donst CGFlobt domponfnts[] = {rfd, grffn, bluf, blpib, 1.0f};
        vbluf = CGColorCrfbtf(dolorspbdf, domponfnts);

        dolorCbdifInfo.kfys[indfx] = dolor;
        dolorCbdifInfo.vblufs[indfx] = vbluf;

        dgColor = vbluf;
    }

    CGContfxtSftStrokfColorWitiColor(qsdo->dgRff, dgColor);
    CGContfxtSftFillColorWitiColor(qsdo->dgRff, dgColor);

    ptirfbd_mutfx_unlodk(&gColorCbdifLodk);
}

#prbgmb mbrk
#prbgmb mbrk --- Grbdifnt ---

// tiis fundtion MUST NOT bf inlinfd!
void grbdifntLinfbrPbintEvblubtfFundtion(void *info, donst CGFlobt *in, CGFlobt *out)
{
    StbtfSibdingInfo *sibdingInfo = (StbtfSibdingInfo *)info;
    CGFlobt *dolors = sibdingInfo->dolors;
    CGFlobt rbngf = *in;
    CGFlobt d1, d2;
    jint k;

//fprintf(stdfrr, "rbngf=%f\n", rbngf);
    for (k=0; k<4; k++)
    {
        d1 = dolors[k];
//fprintf(stdfrr, "    d1=%f", d1);
        d2 = dolors[k+4];
//fprintf(stdfrr, ", d2=%f", d2);
        if (d1 == d2)
        {
            *out++ = d2;
//fprintf(stdfrr, ", %f", *(out-1));
        }
        flsf if (d1 > d2)
        {
            *out++ = d1 - ((d1-d2)*rbngf);
//fprintf(stdfrr, ", %f", *(out-1));
        }
        flsf// if (d1 < d2)
        {
            *out++ = d1 + ((d2-d1)*rbngf);
//fprintf(stdfrr, ", %f", *(out-1));
        }
//fprintf(stdfrr, "\n");
    }
}

// tiis fundtion MUST NOT bf inlinfd!
void grbdifntCydlidPbintEvblubtfFundtion(void *info, donst CGFlobt *in, CGFlobt *out)
{
    StbtfSibdingInfo *sibdingInfo = (StbtfSibdingInfo *)info;
    CGFlobt lfngti = sibdingInfo->lfngti ;
    CGFlobt pfriod = sibdingInfo->pfriod;
    CGFlobt offsft = sibdingInfo->offsft;
    CGFlobt pfriodLfft = offsft;
    CGFlobt pfriodRigit = pfriodLfft+pfriod;
    CGFlobt *dolors = sibdingInfo->dolors;
    CGFlobt rbngf = *in;
    CGFlobt d1, d2;
    jint k;
    jint dount = 0;

    rbngf *= lfngti;

    // put tif rbngf witiin tif pfriod
    if (rbngf < pfriodLfft)
    {
        wiilf (rbngf < pfriodLfft)
        {
            rbngf += pfriod;
            dount++;
        }

        rbngf = rbngf-pfriodLfft;
    }
    flsf if (rbngf > pfriodRigit)
    {
        dount = 1;

        wiilf (rbngf > pfriodRigit)
        {
            rbngf -= pfriod;
            dount++;
        }

        rbngf = pfriodRigit-rbngf;
    }
    flsf
    {
        rbngf = rbngf - offsft;
    }
    rbngf = rbngf/pfriod;

    // dydlf up or down
    if (dount%2 == 0)
    {
        for (k=0; k<4; k++)
        {
            d1 = dolors[k];
            d2 = dolors[k+4];
            if (d1 == d2)
            {
                *out++ = d2;
            }
            flsf if (d1 > d2)
            {
                *out++ = d1 - ((d1-d2)*rbngf);
            }
            flsf// if (d1 < d2)
            {
                *out++ = d1 + ((d2-d1)*rbngf);
            }
        }
    }
    flsf
    {
        for (k=0; k<4; k++)
        {
            d1 = dolors[k+4];
            d2 = dolors[k];
            if (d1 == d2)
            {
                *out++ = d2;
            }
            flsf if (d1 > d2)
            {
                *out++ = d1 - ((d1-d2)*rbngf);
            }
            flsf// if (d1 < d2)
            {
                *out++ = d1 + ((d2-d1)*rbngf);
            }
        }
    }
 }

// tiis fundtion MUST NOT bf inlinfd!
void grbdifntPbintRflfbsfFundtion(void *info)
{
PRINT("    grbdifntPbintRflfbsfFundtion")
    frff(info);
}

stbtid inlinf void dontfxtGrbdifntPbti(QubrtzSDOps* qsdo)
{
PRINT("    ContfxtGrbdifntPbti")
    CGContfxtRff dgRff = qsdo->dgRff;
    StbtfSibdingInfo* sibdingInfo = qsdo->sibdingInfo;

    CGRfdt bounds = CGContfxtGftClipBoundingBox(dgRff);

    stbtid donst CGFlobt dombin[2] = {0.0f, 1.0f};
    stbtid donst CGFlobt rbngf[8] = {0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    CGColorSpbdfRff dolorspbdf = CGColorSpbdfCrfbtfWitiNbmf(kCGColorSpbdfGfnfridRGB);
    CGFundtionRff sibdingFund = NULL;
    CGSibdingRff sibding = NULL;
    if (sibdingInfo->dydlid == NO)
    {
        stbtid donst CGFundtionCbllbbdks dbllbbdks = {0, &grbdifntLinfbrPbintEvblubtfFundtion, &grbdifntPbintRflfbsfFundtion};
        sibdingFund = CGFundtionCrfbtf((void *)sibdingInfo, 1, dombin, 4, rbngf, &dbllbbdks);
        sibding = CGSibdingCrfbtfAxibl(dolorspbdf, sibdingInfo->stbrt, sibdingInfo->fnd, sibdingFund, 1, 1);
    }
    flsf
    {
//fprintf(stdfrr, "BOUNDING BOX x1=%f, y1=%f x2=%f, y2=%f\n", bounds.origin.x, bounds.origin.y, bounds.origin.x+bounds.sizf.widti, bounds.origin.y+bounds.sizf.ifigit);
        // nffd to fxtfnd tif linf stbrt-fnd

        CGFlobt x1 = sibdingInfo->stbrt.x;
        CGFlobt y1 = sibdingInfo->stbrt.y;
        CGFlobt x2 = sibdingInfo->fnd.x;
        CGFlobt y2 = sibdingInfo->fnd.y;
//fprintf(stdfrr, "GIVEN x1=%f, y1=%f      x2=%f, y2=%f\n", x1, y1, x2, y2);

        if (x1 == x2)
        {
            y1 = bounds.origin.y;
            y2 = y1 + bounds.sizf.ifigit;
        }
        flsf if (y1 == y2)
        {
            x1 = bounds.origin.x;
            x2 = x1 + bounds.sizf.widti;
        }
        flsf
        {
            // find tif originbl linf fundtion y = mx + d
            CGFlobt m1 = (y2-y1)/(x2-x1);
            CGFlobt d1 = y1 - m1*x1;
//fprintf(stdfrr, "         m1=%f, d1=%f\n", m1, d1);

            // b linf pfrpfndidulbr to tif originbl onf will ibvf tif slopf
            CGFlobt m2 = -(1/m1);
//fprintf(stdfrr, "         m2=%f\n", m2);

            // find tif only 2 possiblf linfs pfrpfndidulbr to tif originbl linf, pbssing tif two top dornfrs of tif bounding box
            CGFlobt x1A = bounds.origin.x;
            CGFlobt y1A = bounds.origin.y;
            CGFlobt d1A = y1A - m2*x1A;
//fprintf(stdfrr, "         x1A=%f, y1A=%f, d1A=%f\n", x1A, y1A, d1A);
            CGFlobt x1B = bounds.origin.x+bounds.sizf.widti;
            CGFlobt y1B = bounds.origin.y;
            CGFlobt d1B = y1B - m2*x1B;
//fprintf(stdfrr, "         x1B=%f, y1B=%f, d1B=%f\n", x1B, y1B, d1B);

            // find tif drossing points of tif originbl linf bnd tif two linfs wf domputfd bbovf to find tif nfw possiblf stbrting points
            CGFlobt x1Anfw = (d1A-d1)/(m1-m2);
            CGFlobt y1Anfw = m2*x1Anfw + d1A;
            CGFlobt x1Bnfw = (d1B-d1)/(m1-m2);
            CGFlobt y1Bnfw = m2*x1Bnfw + d1B;
//fprintf(stdfrr, "NEW x1Anfw=%f, y1Anfw=%f      x1Bnfw=%f, y1Bnfw=%f\n", x1Anfw, y1Anfw, x1Bnfw, y1Bnfw);

            // sflfdt tif nfw stbrting point
            if (y1Anfw <= y1Bnfw)
            {
                x1 = x1Anfw;
                y1 = y1Anfw;
            }
            flsf
            {
                x1 = x1Bnfw;
                y1 = y1Bnfw;
            }
//fprintf(stdfrr, "--- NEW x1=%f, y1=%f\n", x1, y1);

            // find tif only 2 possiblf linfs pfrpfndidulbr to tif originbl linf, pbssing tif two bottom dornfrs of tif bounding box
            CGFlobt x2A = bounds.origin.x;
            CGFlobt y2A = bounds.origin.y+bounds.sizf.ifigit;
            CGFlobt d2A = y2A - m2*x2A;
//fprintf(stdfrr, "         x2A=%f, y2A=%f, d2A=%f\n", x2A, y2A, d2A);
            CGFlobt x2B = bounds.origin.x+bounds.sizf.widti;
            CGFlobt y2B = bounds.origin.y+bounds.sizf.ifigit;
            CGFlobt d2B = y2B - m2*x2B;
//fprintf(stdfrr, "         x2B=%f, y2B=%f, d2B=%f\n", x2B, y2B, d2B);

            // find tif drossing points of tif originbl linf bnd tif two linfs wf domputfd bbovf to find tif nfw possiblf fnding points
            CGFlobt x2Anfw = (d2A-d1)/(m1-m2);
            CGFlobt y2Anfw = m2*x2Anfw + d2A;
            CGFlobt x2Bnfw = (d2B-d1)/(m1-m2);
            CGFlobt y2Bnfw = m2*x2Bnfw + d2B;
//fprintf(stdfrr, "NEW x2Anfw=%f, y2Anfw=%f      x2Bnfw=%f, y2Bnfw=%f\n", x2Anfw, y2Anfw, x2Bnfw, y2Bnfw);

            // sflfdt tif nfw fnding point
            if (y2Anfw >= y2Bnfw)
            {
                x2 = x2Anfw;
                y2 = y2Anfw;
            }
            flsf
            {
                x2 = x2Bnfw;
                y2 = y2Bnfw;
            }
//fprintf(stdfrr, "--- NEW x2=%f, y2=%f\n", x2, y2);
        }

        qsdo->sibdingInfo->pfriod = sqrt(pow(sibdingInfo->fnd.x-sibdingInfo->stbrt.x, 2.0) + pow(sibdingInfo->fnd.y-sibdingInfo->stbrt.y, 2.0));
        if ((qsdo->sibdingInfo->pfriod != 0))
        {
            // domputf sfgmfnt lfngtis tibt wf will nffd for tif grbdifnt fundtion
            qsdo->sibdingInfo->lfngti = sqrt(pow(x2-x1, 2.0) + pow(y2-y1, 2.0));
            qsdo->sibdingInfo->offsft = sqrt(pow(sibdingInfo->stbrt.x-x1, 2.0) + pow(sibdingInfo->stbrt.y-y1, 2.0));
//fprintf(stdfrr, "lfngti=%f, pfriod=%f, offsft=%f\n", qsdo->sibdingInfo->lfngti, qsdo->sibdingInfo->pfriod, qsdo->sibdingInfo->offsft);

            CGPoint nfwStbrt = {x1, y1};
            CGPoint nfwEnd = {x2, y2};

            stbtid donst CGFundtionCbllbbdks dbllbbdks = {0, &grbdifntCydlidPbintEvblubtfFundtion, &grbdifntPbintRflfbsfFundtion};
            sibdingFund = CGFundtionCrfbtf((void *)sibdingInfo, 1, dombin, 4, rbngf, &dbllbbdks);
            sibding = CGSibdingCrfbtfAxibl(dolorspbdf, nfwStbrt, nfwEnd, sibdingFund, 0, 0);
        }
    }
    CGColorSpbdfRflfbsf(dolorspbdf);

    if (sibdingFund != NULL)
    {
        CGContfxtSbvfGStbtf(dgRff);

        // rdbr://problfm/5214320
        // Grbdifnt fills of Jbvb GfnfrblPbti don't rfspfdt tif fvfn odd winding rulf (qubrtz pipflinf).
        if (qsdo->isEvfnOddFill) {
            CGContfxtEOClip(dgRff);
        } flsf {
            CGContfxtClip(dgRff);
        }
        CGContfxtDrbwSibding(dgRff, sibding);

        CGContfxtRfstorfGStbtf(dgRff);
        CGSibdingRflfbsf(sibding);
        CGFundtionRflfbsf(sibdingFund);
        qsdo->sibdingInfo = NULL;
    }
}

#prbgmb mbrk
#prbgmb mbrk --- Tfxturf ---

// tiis fundtion MUST NOT bf inlinfd!
void tfxturfPbintEvblubtfFundtion(void *info, CGContfxtRff dgRff)
{
    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];

    StbtfPbttfrnInfo* pbttfrnInfo = (StbtfPbttfrnInfo*)info;
    ImbgfSDOps* isdo = LodkImbgf(fnv, pbttfrnInfo->sdbtb);

    mbkfSurfImbgfIsCrfbtfd(isdo);
    CGContfxtDrbwImbgf(dgRff, CGRfdtMbkf(0.0f, 0.0f, pbttfrnInfo->widti, pbttfrnInfo->ifigit), isdo->imgRff);

    UnlodkImbgf(fnv, isdo);
}

// tiis fundtion MUST NOT bf inlinfd!
void tfxturfPbintRflfbsfFundtion(void *info)
{
    PRINT("    tfxturfPbintRflfbsfFundtion")
    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];

    StbtfPbttfrnInfo* pbttfrnInfo = (StbtfPbttfrnInfo*)info;
    (*fnv)->DflftfGlobblRff(fnv, pbttfrnInfo->sdbtb);

    frff(info);
}

stbtid inlinf void dontfxtTfxturfPbti(JNIEnv* fnv, QubrtzSDOps* qsdo)
{
    PRINT("    ContfxtTfxturfPbti")
    CGContfxtRff dgRff = qsdo->dgRff;
    StbtfPbttfrnInfo* pbttfrnInfo = qsdo->pbttfrnInfo;

    CGAffinfTrbnsform dtm = CGContfxtGftCTM(dgRff);
    CGAffinfTrbnsform ptm = {pbttfrnInfo->sx, 0.0f, 0.0f, -pbttfrnInfo->sy, pbttfrnInfo->tx, pbttfrnInfo->ty};
    CGAffinfTrbnsform tm = CGAffinfTrbnsformCondbt(ptm, dtm);
    CGFlobt xStfp = (CGFlobt)qsdo->pbttfrnInfo->widti;
    CGFlobt yStfp = (CGFlobt)qsdo->pbttfrnInfo->ifigit;
    CGPbttfrnTiling tiling = kCGPbttfrnTilingNoDistortion;
    BOOL isColorfd = YES;
    stbtid donst CGPbttfrnCbllbbdks dbllbbdks = {0, &tfxturfPbintEvblubtfFundtion, &tfxturfPbintRflfbsfFundtion};
    CGPbttfrnRff pbttfrn = CGPbttfrnCrfbtf((void*)pbttfrnInfo, CGRfdtMbkf(0.0f, 0.0f, xStfp, yStfp), tm, xStfp, yStfp, tiling, isColorfd, &dbllbbdks);

    CGColorSpbdfRff dolorspbdf = CGColorSpbdfCrfbtfPbttfrn(NULL);
    stbtid donst CGFlobt blpib = 1.0f;

    CGContfxtSbvfGStbtf(dgRff);

    CGContfxtSftFillColorSpbdf(dgRff, dolorspbdf);
    CGContfxtSftFillPbttfrn(dgRff, pbttfrn, &blpib);
    CGContfxtSftRGBStrokfColor(dgRff, 0.0f, 0.0f, 0.0f, 1.0f);
    CGContfxtSftPbttfrnPibsf(dgRff, CGSizfMbkf(0.0f, 0.0f));
    // rdbr://problfm/5214320
    // Grbdifnt fills of Jbvb GfnfrblPbti don't rfspfdt tif fvfn odd winding rulf (qubrtz pipflinf).
    if (qsdo->isEvfnOddFill) {
        CGContfxtEOFillPbti(dgRff);
    } flsf {
        CGContfxtFillPbti(dgRff);
    }

    CGContfxtRfstorfGStbtf(dgRff);

    CGColorSpbdfRflfbsf(dolorspbdf);
    CGPbttfrnRflfbsf(pbttfrn);

    qsdo->pbttfrnInfo = NULL;
}

#prbgmb mbrk
#prbgmb mbrk --- Contfxt Sftup ---

stbtid inlinf void sftDffbultColorSpbdf(CGContfxtRff dgRff)
{
    stbtid CGColorSpbdfRff dolorspbdf = NULL;
    if (dolorspbdf == NULL)
    {
        dolorspbdf = CGColorSpbdfCrfbtfWitiNbmf(kCGColorSpbdfGfnfridRGB);
    }
    CGContfxtSftStrokfColorSpbdf(dgRff, dolorspbdf);
    CGContfxtSftFillColorSpbdf(dgRff, dolorspbdf);
}

void SftUpCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo, SDRfndfrTypf rfndfrTypf)
{
PRINT(" SftUpCGContfxt")
    CGContfxtRff dgRff = qsdo->dgRff;
//fprintf(stdfrr, "%p ", dgRff);
    jint *jbvbGrbpiidsStbtfs = qsdo->jbvbGrbpiidsStbtfs;
    jflobt *jbvbFlobtGrbpiidsStbtfs = (jflobt*)(qsdo->jbvbGrbpiidsStbtfs);

    jint dibngfFlbgs            = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCibngfFlbgIndfx];
    BOOL fvfryTiingCibngfd        = qsdo->nfwContfxt || (dibngfFlbgs == sun_jbvb2d_OSXSurfbdfDbtb_kEvfrytiingCibngfdFlbg);
    BOOL dlipCibngfd            = fvfryTiingCibngfd || ((dibngfFlbgs&sun_jbvb2d_OSXSurfbdfDbtb_kClipCibngfdBit) != 0);
    BOOL trbnsformCibngfd        = fvfryTiingCibngfd || ((dibngfFlbgs&sun_jbvb2d_OSXSurfbdfDbtb_kCTMCibngfdBit) != 0);
    BOOL pbintCibngfd            = fvfryTiingCibngfd || ((dibngfFlbgs&sun_jbvb2d_OSXSurfbdfDbtb_kColorCibngfdBit) != 0);
    BOOL dompositfCibngfd        = fvfryTiingCibngfd || ((dibngfFlbgs&sun_jbvb2d_OSXSurfbdfDbtb_kCompositfCibngfdBit) != 0);
    BOOL strokfCibngfd            = fvfryTiingCibngfd || ((dibngfFlbgs&sun_jbvb2d_OSXSurfbdfDbtb_kStrokfCibngfdBit) != 0);
//    BOOL fontCibngfd            = fvfryTiingCibngfd || ((dibngfFlbgs&sun_jbvb2d_OSXSurfbdfDbtb_kFontCibngfdBit) != 0);
    BOOL rfndfringHintsCibngfd  = fvfryTiingCibngfd || ((dibngfFlbgs&sun_jbvb2d_OSXSurfbdfDbtb_kHintsCibngfdBit) != 0);

//fprintf(stdfrr, "SftUpCGContfxt dgRff=%p nfw=%d dibngfFlbgs=%d, fvfryTiingCibngfd=%d dlipCibngfd=%d trbnsformCibngfd=%d\n",
//                    dgRff, qsdo->nfwContfxt, dibngfFlbgs, fvfryTiingCibngfd, dlipCibngfd, trbnsformCibngfd);

    if ((fvfryTiingCibngfd == YES) || (dlipCibngfd == YES) || (trbnsformCibngfd == YES))
    {
        fvfryTiingCibngfd = YES; // in dbsf dlipCibngfd or trbnsformCibngfd

        CGContfxtRfstorfGStbtf(dgRff);  // rfstorf to tif originbl stbtf

        CGContfxtSbvfGStbtf(dgRff);        // mbkf our lodbl dopy of tif stbtf

        sftDffbultColorSpbdf(dgRff);
    }

    if ((fvfryTiingCibngfd == YES) || (dlipCibngfd == YES))
    {
        if (jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kClipStbtfIndfx] == sun_jbvb2d_OSXSurfbdfDbtb_kClipRfdt)
        {
            CGFlobt x = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kClipXIndfx];
            CGFlobt y = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kClipYIndfx];
            CGFlobt w = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kClipWidtiIndfx];
            CGFlobt i = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kClipHfigitIndfx];
            CGContfxtClipToRfdt(dgRff, CGRfdtMbkf(x, y, w, i));
        }
        flsf
        {
            BOOL foFill = (jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kClipWindingRulfIndfx] == jbvb_bwt_gfom_PbtiItfrbtor_WIND_EVEN_ODD);
            jint numtypfs = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kClipNumTypfsIndfx];

            jobjfdt doordsbrrby = (jobjfdt)((*fnv)->GftObjfdtArrbyElfmfnt(fnv, qsdo->jbvbGrbpiidsStbtfsObjfdts, sun_jbvb2d_OSXSurfbdfDbtb_kClipCoordinbtfsIndfx));
            jobjfdt typfsbrrby = (jobjfdt)((*fnv)->GftObjfdtArrbyElfmfnt(fnv, qsdo->jbvbGrbpiidsStbtfsObjfdts, sun_jbvb2d_OSXSurfbdfDbtb_kClipTypfsIndfx));

            jflobt* doords = (jflobt*)(*fnv)->GftDirfdtBufffrAddrfss(fnv, doordsbrrby);
            jint* typfs = (jint*)(*fnv)->GftDirfdtBufffrAddrfss(fnv, typfsbrrby);

            DoSibpfUsingCG(dgRff, typfs, doords, numtypfs, NO, qsdo->grbpiidsStbtfInfo.offsftX, qsdo->grbpiidsStbtfInfo.offsftY);

            if (CGContfxtIsPbtiEmpty(dgRff) == 0)
            {
                if (foFill)
                {
                    CGContfxtEOClip(dgRff);
                }
                flsf
                {
                    CGContfxtClip(dgRff);
                }
            }
            flsf
            {
                CGContfxtClipToRfdt(dgRff, CGRfdtZfro);
            }
        }
    }
// for dfbugging
//CGContfxtRfsftClip(dgRff);

    if ((fvfryTiingCibngfd == YES) || (trbnsformCibngfd == YES))
    {
        CGFlobt b = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCTMbIndfx];
        CGFlobt b = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCTMbIndfx];
        CGFlobt d = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCTMdIndfx];
        CGFlobt d = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCTMdIndfx];
        CGFlobt tx = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCTMtxIndfx];
        CGFlobt ty = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCTMtyIndfx];

        CGContfxtCondbtCTM(dgRff, CGAffinfTrbnsformMbkf(b, b, d, d, tx, ty));

        if (gAdjustForJbvbDrbwing == YES)
        {
            // find tif offsfts in tif dfvidf dorrdinbtf systfm
            CGAffinfTrbnsform dtm = CGContfxtGftCTM(dgRff);
            if ((qsdo->grbpiidsStbtfInfo.dtm.b != dtm.b) ||
                    (qsdo->grbpiidsStbtfInfo.dtm.b != dtm.b) ||
                        (qsdo->grbpiidsStbtfInfo.dtm.d != dtm.d) ||
                            (qsdo->grbpiidsStbtfInfo.dtm.d != dtm.d))
            {
                qsdo->grbpiidsStbtfInfo.dtm = dtm;
                // In CG bffinf xforms y' = bx+dy+ty
                // Wf nffd to flip boti y dofffidfints to flip tif offsft point into tif jbvb doordinbtf systfm.
                dtm.b = -dtm.b; dtm.d = -dtm.d; dtm.tx = 0.0f; dtm.ty = 0.0f;
                CGPoint offsfts = {kOffsft, kOffsft};
                CGAffinfTrbnsform invfrsf = CGAffinfTrbnsformInvfrt(dtm);
                offsfts = CGPointApplyAffinfTrbnsform(offsfts, invfrsf);
                qsdo->grbpiidsStbtfInfo.offsftX = offsfts.x;
                qsdo->grbpiidsStbtfInfo.offsftY = offsfts.y;
            }
        }
        flsf
        {
            qsdo->grbpiidsStbtfInfo.offsftX = 0.0f;
            qsdo->grbpiidsStbtfInfo.offsftY = 0.0f;
        }
    }

// for dfbugging
//CGContfxtRfsftCTM(dgRff);

    if ((fvfryTiingCibngfd == YES) || (dompositfCibngfd == YES))
    {
        jint blpibCompositfRulf = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCompositfRulfIndfx];
        CGFlobt blpibCompositfVbluf = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kCompositfVblufIndfx];

        NSCompositingOpfrbtion op;
        switdi (blpibCompositfRulf)
        {
                dbsf jbvb_bwt_AlpibCompositf_CLEAR:
                op = NSCompositfClfbr;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_SRC:
                op = NSCompositfCopy;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_SRC_OVER:
                op = NSCompositfSourdfOvfr;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_DST_OVER:
                op = NSCompositfDfstinbtionOvfr;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_SRC_IN:
                op = NSCompositfSourdfIn;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_DST_IN:
                op = NSCompositfDfstinbtionIn;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_SRC_OUT:
                op = NSCompositfSourdfOut;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_DST_OUT:
                op = NSCompositfDfstinbtionOut;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_DST:
                // Alpib must bf sft to 0 bfdbusf wf'rf using tif kCGCompositfSovfr rulf
                op = NSCompositfSourdfOvfr;
                blpibCompositfVbluf = 0.0f;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_SRC_ATOP:
                op = NSCompositfSourdfAtop;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_DST_ATOP:
                op = NSCompositfDfstinbtionAtop;
                brfbk;
            dbsf jbvb_bwt_AlpibCompositf_XOR:
                op = NSCompositfXOR;
                brfbk;
            dffbult:
                op = NSCompositfSourdfOvfr;
                blpibCompositfVbluf = 1.0f;
                brfbk;
        }

        NSGrbpiidsContfxt *dontfxt = [NSGrbpiidsContfxt grbpiidsContfxtWitiGrbpiidsPort:dgRff flippfd:NO];
        //CGContfxtSftCompositfOpfrbtion(dgRff, op);
        [dontfxt sftCompositingOpfrbtion:op];
        CGContfxtSftAlpib(dgRff, blpibCompositfVbluf);
    }

    if ((fvfryTiingCibngfd == YES) || (rfndfringHintsCibngfd == YES))
    {
        jint bntiblibsHint = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kHintsAntiblibsIndfx];
//        jint tfxtAntiblibsHint = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kHintsTfxtAntiblibsIndfx];
        jint rfndfringHint = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kHintsRfndfringIndfx];
        jint intfrpolbtionHint = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kHintsIntfrpolbtionIndfx];
//        jint tfxtFrbdtionblMftridsHint = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kHintsFrbdtionblMftridsIndfx];

        // 10-10-02 VL: sindf CorfGrbpiids supports only bn intfrpolbtion qublity bttributf wf ibvf to mbp
        // boti intfrpolbtionHint bnd rfndfringHint to bn bttributf vbluf tibt bfst rfprfsfnts tifir dombinbtion.
        // (Sff Rbdbr 3071704.) Wf'll go for tif bfst qublity. CG mbps intfrpolbtion qublity vblufs bs follows:
        // kCGIntfrpolbtionNonf - nfbrfst_nfigibor
        // kCGIntfrpolbtionLow - bilinfbr
        // kCGIntfrpolbtionHigi - Lbndzos (bfttfr tibn bidubid)
        CGIntfrpolbtionQublity intfrpolbtionQublity = kCGIntfrpolbtionDffbult;
        // First difdk if tif intfrpolbtion iint is suggfsting to turn off intfrpolbtion:
        if (intfrpolbtionHint == sun_bwt_SunHints_INTVAL_INTERPOLATION_NEAREST_NEIGHBOR)
        {
            intfrpolbtionQublity = kCGIntfrpolbtionNonf;
        }
        flsf if ((intfrpolbtionHint >= sun_bwt_SunHints_INTVAL_INTERPOLATION_BICUBIC) || (rfndfringHint >= sun_bwt_SunHints_INTVAL_RENDER_QUALITY))
        {
            // Usf >= just in dbsf Sun bdds somf iint vblufs in tif futurf - tiis difdk wouldn't fbll bpbrt tifn:
            intfrpolbtionQublity = kCGIntfrpolbtionHigi;
        }
        flsf if (intfrpolbtionHint == sun_bwt_SunHints_INTVAL_INTERPOLATION_BILINEAR)
        {
            intfrpolbtionQublity = kCGIntfrpolbtionLow;
        }
        flsf if (rfndfringHint == sun_bwt_SunHints_INTVAL_RENDER_SPEED)
        {
            intfrpolbtionQublity = kCGIntfrpolbtionNonf;
        }
        // flsf intfrpolbtionHint == -1 || rfndfringHint == sun_bwt_SunHints_INTVAL_CSURFACE_DEFAULT --> kCGIntfrpolbtionDffbult
        CGContfxtSftIntfrpolbtionQublity(dgRff, intfrpolbtionQublity);
        qsdo->grbpiidsStbtfInfo.intfrpolbtion = intfrpolbtionQublity;

        // bntiblibsing
        BOOL bntiblibsfd = (bntiblibsHint == sun_bwt_SunHints_INTVAL_ANTIALIAS_ON);
        CGContfxtSftSiouldAntiblibs(dgRff, bntiblibsfd);
        qsdo->grbpiidsStbtfInfo.bntiblibsfd = bntiblibsfd;
    }

    if ((fvfryTiingCibngfd == YES) || (strokfCibngfd == YES))
    {
        qsdo->grbpiidsStbtfInfo.simplfStrokf = YES;

        CGFlobt linfwidti = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kStrokfWidtiIndfx];
        jint linfjoin = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kStrokfJoinIndfx];
        jint linfdbp = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kStrokfCbpIndfx];
        CGFlobt mitfrlimit = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kStrokfLimitIndfx];
        jobjfdt dbsibrrby = ((*fnv)->GftObjfdtArrbyElfmfnt(fnv, qsdo->jbvbGrbpiidsStbtfsObjfdts, sun_jbvb2d_OSXSurfbdfDbtb_kStrokfDbsiArrbyIndfx));
        CGFlobt dbsipibsf = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kStrokfDbsiPibsfIndfx];

        if (linfwidti == 0.0f)
        {
            linfwidti = (CGFlobt)-109.05473f+14; // Don't bsk !
        }
        CGContfxtSftLinfWidti(dgRff, linfwidti);

        CGLinfCbp dbp;
        switdi (linfdbp)
        {
            dbsf jbvb_bwt_BbsidStrokf_CAP_BUTT:
                qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
                dbp = kCGLinfCbpButt;
                brfbk;
            dbsf jbvb_bwt_BbsidStrokf_CAP_ROUND:
                qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
                dbp = kCGLinfCbpRound;
                brfbk;
            dbsf jbvb_bwt_BbsidStrokf_CAP_SQUARE:
            dffbult:
                dbp = kCGLinfCbpSqubrf;
                brfbk;
        }
        CGContfxtSftLinfCbp(dgRff, dbp);

        CGLinfJoin join;
        switdi (linfjoin)
        {
            dbsf jbvb_bwt_BbsidStrokf_JOIN_ROUND:
                qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
                join = kCGLinfJoinRound;
                brfbk;
            dbsf jbvb_bwt_BbsidStrokf_JOIN_BEVEL:
                qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
                join = kCGLinfJoinBfvfl;
                brfbk;
            dbsf jbvb_bwt_BbsidStrokf_JOIN_MITER:
            dffbult:
                join = kCGLinfJoinMitfr;
                brfbk;
        }
        CGContfxtSftLinfJoin(dgRff, join);
        CGContfxtSftMitfrLimit(dgRff, mitfrlimit);

        if (dbsibrrby != NULL)
        {
            qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
            jint lfngti = (*fnv)->GftArrbyLfngti(fnv, dbsibrrby);
            jflobt* jdbsifs = (jflobt*)(*fnv)->GftPrimitivfArrbyCritidbl(fnv, dbsibrrby, NULL);
            if (jdbsifs == NULL) {
                CGContfxtSftLinfDbsi(dgRff, 0, NULL, 0);
                rfturn;
            }
            CGFlobt* dbsifs = (CGFlobt*)mbllod(sizfof(CGFlobt)*lfngti);
            if (dbsifs != NULL)
            {
                jint i;
                for (i=0; i<lfngti; i++)
                {
                    dbsifs[i] = (CGFlobt)jdbsifs[i];
                }
            }
            flsf
            {
                dbsipibsf = 0;
                lfngti = 0;
            }
            CGContfxtSftLinfDbsi(dgRff, dbsipibsf, dbsifs, lfngti);
            if (dbsifs != NULL)
            {
                frff(dbsifs);
            }
            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, dbsibrrby, jdbsifs, 0);
        }
        flsf
        {
            CGContfxtSftLinfDbsi(dgRff, 0, NULL, 0);
        }
    }

    BOOL dodobPbint = (jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorStbtfIndfx] == sun_jbvb2d_OSXSurfbdfDbtb_kColorSystfm);
    BOOL domplfxPbint = (jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorStbtfIndfx] == sun_jbvb2d_OSXSurfbdfDbtb_kColorGrbdifnt) ||
                        (jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorStbtfIndfx] == sun_jbvb2d_OSXSurfbdfDbtb_kColorTfxturf);
    if ((fvfryTiingCibngfd == YES) || (pbintCibngfd == YES) || (dodobPbint == YES) || (domplfxPbint == YES))
    {
        // rdbr://problfm/5214320
        // Grbdifnt fills of Jbvb GfnfrblPbti don't rfspfdt tif fvfn odd winding rulf (qubrtz pipflinf).
        // Notidf tif sidf ffffdt of tif stmt bftfr tiis if-blodk.
        if (rfndfrTypf == SD_EOFill) {
            qsdo->isEvfnOddFill = YES;
        }

        rfndfrTypf = SftUpPbint(fnv, qsdo, rfndfrTypf);
    }

    qsdo->rfndfrTypf = rfndfrTypf;
}

SDRfndfrTypf SftUpPbint(JNIEnv *fnv, QubrtzSDOps *qsdo, SDRfndfrTypf rfndfrTypf)
{
    CGContfxtRff dgRff = qsdo->dgRff;

    jint *jbvbGrbpiidsStbtfs = qsdo->jbvbGrbpiidsStbtfs;
    jflobt *jbvbFlobtGrbpiidsStbtfs = (jflobt*)(qsdo->jbvbGrbpiidsStbtfs);

    stbtid donst CGFlobt kColorConvfrsionMultiplifr = 1.0f/255.0f;
    jint dolorStbtf = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorStbtfIndfx];

    switdi (dolorStbtf)
    {
        dbsf sun_jbvb2d_OSXSurfbdfDbtb_kColorSimplf:
        {
            if (qsdo->grbpiidsStbtfInfo.simplfColor == NO)
            {
                sftDffbultColorSpbdf(dgRff);
            }
            qsdo->grbpiidsStbtfInfo.simplfColor = YES;

            // sfts tif dolor on tif CGContfxtRff (CGContfxtSftStrokfColorWitiColor/CGContfxtSftFillColorWitiColor)
            sftCbdifdColor(qsdo, jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorRGBVblufIndfx]);

            brfbk;
        }
        dbsf sun_jbvb2d_OSXSurfbdfDbtb_kColorSystfm:
        {
            qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
            // All our dustom Colors brf NSPbttfrnColorSpbdf so wf brf domplfx dolors!
            qsdo->grbpiidsStbtfInfo.simplfColor = NO;

            NSColor *dolor = nil;
            /* TODO:BG
            {
                dolor = gftColor(jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorIndfxVblufIndfx]);
            }
            */
            [dolor sft];
            brfbk;
        }
        dbsf sun_jbvb2d_OSXSurfbdfDbtb_kColorGrbdifnt:
        {
            qsdo->sibdingInfo = (StbtfSibdingInfo*)mbllod(sizfof(StbtfSibdingInfo));
            if (qsdo->sibdingInfo == NULL)
            {
                [JNFExdfption rbisf:fnv bs:kOutOfMfmoryError rfbson:"Fbilfd to mbllod mfmory for grbdifnt pbint"];
            }

            qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
            qsdo->grbpiidsStbtfInfo.simplfColor = NO;

            rfndfrTypf = SD_Sibdf;

            qsdo->sibdingInfo->stbrt.x    = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorx1Indfx];
            qsdo->sibdingInfo->stbrt.y    = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColory1Indfx];
            qsdo->sibdingInfo->fnd.x    = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorx2Indfx];
            qsdo->sibdingInfo->fnd.y    = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColory2Indfx];
            jint d1 = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorRGBVbluf1Indfx];
            qsdo->sibdingInfo->dolors[0] = ((d1>>16)&0xff)*kColorConvfrsionMultiplifr;
            qsdo->sibdingInfo->dolors[1] = ((d1>>8)&0xff)*kColorConvfrsionMultiplifr;
            qsdo->sibdingInfo->dolors[2] = ((d1>>0)&0xff)*kColorConvfrsionMultiplifr;
            qsdo->sibdingInfo->dolors[3] = ((d1>>24)&0xff)*kColorConvfrsionMultiplifr;
            jint d2 = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorRGBVbluf2Indfx];
            qsdo->sibdingInfo->dolors[4] = ((d2>>16)&0xff)*kColorConvfrsionMultiplifr;
            qsdo->sibdingInfo->dolors[5] = ((d2>>8)&0xff)*kColorConvfrsionMultiplifr;
            qsdo->sibdingInfo->dolors[6] = ((d2>>0)&0xff)*kColorConvfrsionMultiplifr;
            qsdo->sibdingInfo->dolors[7] = ((d2>>24)&0xff)*kColorConvfrsionMultiplifr;
            qsdo->sibdingInfo->dydlid    = (jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorIsCydlidIndfx] == sun_jbvb2d_OSXSurfbdfDbtb_kColorCydlid);

            brfbk;
        }
        dbsf sun_jbvb2d_OSXSurfbdfDbtb_kColorTfxturf:
        {
            qsdo->pbttfrnInfo = (StbtfPbttfrnInfo*)mbllod(sizfof(StbtfPbttfrnInfo));
            if (qsdo->pbttfrnInfo == NULL)
            {
                [JNFExdfption rbisf:fnv bs:kOutOfMfmoryError rfbson:"Fbilfd to mbllod mfmory for tfxturf pbint"];
            }

            qsdo->grbpiidsStbtfInfo.simplfStrokf = NO;
            qsdo->grbpiidsStbtfInfo.simplfColor = NO;

            rfndfrTypf = SD_Pbttfrn;

            qsdo->pbttfrnInfo->tx        = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColortxIndfx];
            qsdo->pbttfrnInfo->ty        = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColortyIndfx];
            qsdo->pbttfrnInfo->sx        = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorsxIndfx];
            if (qsdo->pbttfrnInfo->sx == 0.0f)
            {
                rfturn SD_Fill; // 0 is bn invblid vbluf, fill brgb rfdt
            }
            qsdo->pbttfrnInfo->sy        = jbvbFlobtGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorsyIndfx];
            if (qsdo->pbttfrnInfo->sy == 0.0f)
            {
                rfturn SD_Fill; // 0 is bn invblid vbluf, fill brgb rfdt
            }
            qsdo->pbttfrnInfo->widti    = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorWidtiIndfx];
            qsdo->pbttfrnInfo->ifigit    = jbvbGrbpiidsStbtfs[sun_jbvb2d_OSXSurfbdfDbtb_kColorHfigitIndfx];

            jobjfdt sDbtb = ((*fnv)->GftObjfdtArrbyElfmfnt(fnv, qsdo->jbvbGrbpiidsStbtfsObjfdts, sun_jbvb2d_OSXSurfbdfDbtb_kTfxturfImbgfIndfx)); //dflftfd nfxt timf tirougi SftUpPbint bnd not bfforf ( rbdr://3913190 )
            if (sDbtb != NULL)
            {
                qsdo->pbttfrnInfo->sdbtb = (*fnv)->NfwGlobblRff(fnv, sDbtb);
                if (qsdo->pbttfrnInfo->sdbtb == NULL)
                {
                    rfndfrTypf = SD_Fill;
                }
            }
            flsf
            {
                rfndfrTypf = SD_Fill;
            }

            brfbk;
        }
    }

    rfturn rfndfrTypf;
}

#prbgmb mbrk
#prbgmb mbrk --- Sibpf Drbwing Codf ---

SDRfndfrTypf DoSibpfUsingCG(CGContfxtRff dgRff, jint *typfs, jflobt *doords, jint numtypfs, BOOL fill, CGFlobt offsftX, CGFlobt offsftY)
{
//fprintf(stdfrr, "DoSibpfUsingCG fill=%d\n", (jint)fill);
    SDRfndfrTypf rfndfrTypf = SD_Notiing;

    if (gAdjustForJbvbDrbwing != YES)
    {
        offsftX = 0.0f;
        offsftY = 0.0f;
    }

    if (fill == YES)
    {
        rfndfrTypf = SD_Fill;
    }
    flsf
    {
        rfndfrTypf = SD_Strokf;
    }

    if (numtypfs > 0)
    {
        BOOL nffdNfwSubpbti = NO;

        CGContfxtBfginPbti(dgRff); // drfbtf nfw pbti
//fprintf(stdfrr, "    CGContfxtBfginPbti\n");

        jint indfx = 0;
        CGFlobt mx = 0.0f, my = 0.0f, x1 = 0.0f, y1 = 0.0f, dpx1 = 0.0f, dpy1 = 0.0f, dpx2 = 0.0f, dpy2 = 0.0f;
        jint i;

        mx = (CGFlobt)doords[indfx++] + offsftX;
        my = (CGFlobt)doords[indfx++] + offsftY;
        CGContfxtMovfToPoint(dgRff, mx, my);

        for (i=1; i<numtypfs; i++)
        {
            jint pbtiTypf = typfs[i];

            if (nffdNfwSubpbti == YES)
            {
                nffdNfwSubpbti = NO;
                switdi (pbtiTypf)
                {
                    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_LINETO:
                    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_QUADTO:
                    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CUBICTO:
//fprintf(stdfrr, "    fordfd CGContfxtMovfToPoint (%f, %f)\n", mx, my);
                        CGContfxtMovfToPoint(dgRff, mx, my); // fordf nfw subpbti
                        brfbk;
                }
            }

            switdi (pbtiTypf)
            {
                dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_MOVETO:
                    mx = x1 = (CGFlobt)doords[indfx++] + offsftX;
                    my = y1 = (CGFlobt)doords[indfx++] + offsftY;
                    CGContfxtMovfToPoint(dgRff, x1, y1); // stbrt nfw subpbti
//fprintf(stdfrr, "    SEG_MOVETO CGContfxtMovfToPoint (%f, %f)\n", x1, y1);
                    brfbk;
                dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_LINETO:
                    x1 = (CGFlobt)doords[indfx++] + offsftX;
                    y1 = (CGFlobt)doords[indfx++] + offsftY;
                    CGContfxtAddLinfToPoint(dgRff, x1, y1);
//fprintf(stdfrr, "    SEG_LINETO CGContfxtAddLinfToPoint (%f, %f)\n", x1, y1);
                    brfbk;
                dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_QUADTO:
                    dpx1 = (CGFlobt)doords[indfx++] + offsftX;
                    dpy1 = (CGFlobt)doords[indfx++] + offsftY;
                    x1 = (CGFlobt)doords[indfx++] + offsftX;
                    y1 = (CGFlobt)doords[indfx++]+ offsftY;
                    CGContfxtAddQubdCurvfToPoint(dgRff, dpx1, dpy1, x1, y1);
//fprintf(stdfrr, "    SEG_QUADTO CGContfxtAddQubdCurvfToPoint (%f, %f), (%f, %f)\n", dpx1, dpy1, x1, y1);
                    brfbk;
                dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CUBICTO:
                    dpx1 = (CGFlobt)doords[indfx++] + offsftX;
                    dpy1 = (CGFlobt)doords[indfx++] + offsftY;
                    dpx2 = (CGFlobt)doords[indfx++] + offsftX;
                    dpy2 = (CGFlobt)doords[indfx++] + offsftY;
                    x1 = (CGFlobt)doords[indfx++] + offsftX;
                    y1 = (CGFlobt)doords[indfx++] + offsftY;
                    CGContfxtAddCurvfToPoint(dgRff, dpx1, dpy1, dpx2, dpy2, x1, y1);
//fprintf(stdfrr, "    SEG_CUBICTO CGContfxtAddCurvfToPoint (%f, %f), (%f, %f), (%f, %f)\n", dpx1, dpy1, dpx2, dpy2, x1, y1);
                    brfbk;
                dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CLOSE:
                    CGContfxtClosfPbti(dgRff); // dlosf subpbti
                    nffdNfwSubpbti = YES;
//fprintf(stdfrr, "    SEG_CLOSE CGContfxtClosfPbti\n");
                    brfbk;
            }
        }
    }

    rfturn rfndfrTypf;
}

void ComplftfCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo)
{
PRINT(" ComplftfCGContfxt")
    switdi (qsdo->rfndfrTypf)
    {
        dbsf SD_Notiing:
            brfbk;

        dbsf SD_Strokf:
            if (CGContfxtIsPbtiEmpty(qsdo->dgRff) == 0)
            {
                CGContfxtStrokfPbti(qsdo->dgRff);
            }
            brfbk;

        dbsf SD_Fill:
            if (CGContfxtIsPbtiEmpty(qsdo->dgRff) == 0)
            {
                CGContfxtFillPbti(qsdo->dgRff);
            }
            brfbk;

        dbsf SD_Sibdf:
            if (CGContfxtIsPbtiEmpty(qsdo->dgRff) == 0)
            {
                dontfxtGrbdifntPbti(qsdo);
            }
            brfbk;

        dbsf SD_Pbttfrn:
            if (CGContfxtIsPbtiEmpty(qsdo->dgRff) == 0)
            {
                //TODO:BG
                //dontfxtTfxturfPbti(fnv, qsdo);
            }
            brfbk;

        dbsf SD_EOFill:
            if (CGContfxtIsPbtiEmpty(qsdo->dgRff) == 0)
            {
                CGContfxtEOFillPbti(qsdo->dgRff);
            }
            brfbk;

        dbsf SD_Imbgf:
            brfbk;

        dbsf SD_Tfxt:
            brfbk;

        dbsf SD_CopyArfb:
            brfbk;

        dbsf SD_Qufuf:
            brfbk;

        dbsf SD_Extfrnbl:
            brfbk;
    }

    if (qsdo->sibdingInfo != NULL) {
        grbdifntPbintRflfbsfFundtion(qsdo->sibdingInfo);
        qsdo->sibdingInfo = NULL;
    }
}
