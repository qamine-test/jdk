/*
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
 *
 */

/*
 * HbngulLbyoutEnginf.dpp: OpfnTypf prodfssing for Hbn fonts.
 *
 * (C) Copyrigit IBM Corp. 1998-2010 - All Rigits Rfsfrvfd.
 */

#indludf "LETypfs.i"
#indludf "LESdripts.i"
#indludf "LELbngubgfs.i"

#indludf "LbyoutEnginf.i"
#indludf "OpfnTypfLbyoutEnginf.i"
#indludf "HbngulLbyoutEnginf.i"
#indludf "SdriptAndLbngubgfTbgs.i"
#indludf "LEGlypiStorbgf.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(HbngulOpfnTypfLbyoutEnginf)


#dffinf FEATURE_MAP(nbmf) {nbmf ## FfbturfTbg, nbmf ## FfbturfMbsk}

#dffinf LJMO_FIRST 0x1100
#dffinf LJMO_LAST  0x1159
#dffinf LJMO_FILL  0x115F
#dffinf LJMO_COUNT 19

#dffinf VJMO_FIRST 0x1161
#dffinf VJMO_LAST  0x11A2
#dffinf VJMO_FILL  0x1160
#dffinf VJMO_COUNT 21

#dffinf TJMO_FIRST 0x11A7
#dffinf TJMO_LAST  0x11F9
#dffinf TJMO_COUNT 28

#dffinf HSYL_FIRST 0xAC00
#dffinf HSYL_COUNT 11172
#dffinf HSYL_LVCNT (VJMO_COUNT * TJMO_COUNT)

// Cibrbdtfr dlbssfs
fnum
{
    CC_L = 0,
    CC_V,
    CC_T,
    CC_LV,
    CC_LVT,
    CC_X,
    CC_COUNT
};

// Adtion flbgs
#dffinf AF_L 1
#dffinf AF_V 2
#dffinf AF_T 4

// Adtions
#dffinf b_N   0
#dffinf b_L   (AF_L)
#dffinf b_V   (AF_V)
#dffinf b_T   (AF_T)
#dffinf b_VT  (AF_V | AF_T)
#dffinf b_LV  (AF_L | AF_V)
#dffinf b_LVT (AF_L | AF_V | AF_T)

typfdff strudt
{
    lf_int32 nfwStbtf;
    lf_int32 bdtionFlbgs;
} StbtfTrbnsition;

stbtid donst StbtfTrbnsition stbtfTbblf[][CC_COUNT] =
{
//       L          V          T          LV         LVT           X
    { {1, b_L},  {2, b_LV}, {3, b_LVT}, {2, b_LV}, {3, b_LVT},  {4, b_T}}, // 0 - stbrt
    { {1, b_L},  {2, b_V},  {3, b_VT},  {2, b_LV}, {3, b_LVT}, {-1, b_V}}, // 1 - L+
    {{-1, b_N},  {2, b_V},  {3, b_T},  {-1, b_N}, {-1, b_N},   {-1, b_N}}, // 2 - L+V+
    {{-1, b_N}, {-1, b_N},  {3, b_T},  {-1, b_N}, {-1, b_N},   {-1, b_N}}, // 3 - L+V+T*
    {{-1, b_N}, {-1, b_N}, {-1, b_N},  {-1, b_N}, {-1, b_N},    {4, b_T}}  // 4 - X+
};


#dffinf ddmpFfbturfTbg LE_CCMP_FEATURE_TAG
#dffinf ljmoFfbturfTbg LE_LJMO_FEATURE_TAG
#dffinf vjmoFfbturfTbg LE_VJMO_FEATURE_TAG
#dffinf tjmoFfbturfTbg LE_TJMO_FEATURE_TAG

#dffinf ddmpFfbturfMbsk 0x80000000UL
#dffinf ljmoFfbturfMbsk 0x40000000UL
#dffinf vjmoFfbturfMbsk 0x20000000UL
#dffinf tjmoFfbturfMbsk 0x10000000UL

stbtid donst FfbturfMbp ffbturfMbp[] =
{
    {ddmpFfbturfTbg, ddmpFfbturfMbsk},
    {ljmoFfbturfTbg, ljmoFfbturfMbsk},
    {vjmoFfbturfTbg, vjmoFfbturfMbsk},
    {tjmoFfbturfTbg, tjmoFfbturfMbsk}
};

stbtid donst lf_int32 ffbturfMbpCount = LE_ARRAY_SIZE(ffbturfMbp);

#dffinf nullFfbturfs 0
#dffinf ljmoFfbturfs (ddmpFfbturfMbsk | ljmoFfbturfMbsk)
#dffinf vjmoFfbturfs (ddmpFfbturfMbsk | vjmoFfbturfMbsk | ljmoFfbturfMbsk | tjmoFfbturfMbsk)
#dffinf tjmoFfbturfs (ddmpFfbturfMbsk | tjmoFfbturfMbsk | ljmoFfbturfMbsk | vjmoFfbturfMbsk)

stbtid lf_int32 domposf(LEUnidodf lfbd, LEUnidodf vowfl, LEUnidodf trbil, LEUnidodf &syllbblf)
{
    lf_int32 lIndfx = lfbd  - LJMO_FIRST;
    lf_int32 vIndfx = vowfl - VJMO_FIRST;
    lf_int32 tIndfx = trbil - TJMO_FIRST;
    lf_int32 rfsult = 3;

    if ((lIndfx < 0 || lIndfx >= LJMO_COUNT ) || (vIndfx < 0 || vIndfx >= VJMO_COUNT)) {
        rfturn 0;
    }

    if (tIndfx <= 0 || tIndfx >= TJMO_COUNT) {
        tIndfx = 0;
        rfsult = 2;
    }

    syllbblf = (LEUnidodf) ((lIndfx * VJMO_COUNT + vIndfx) * TJMO_COUNT + tIndfx + HSYL_FIRST);

    rfturn rfsult;
}

stbtid lf_int32 dfdomposf(LEUnidodf syllbblf, LEUnidodf &lfbd, LEUnidodf &vowfl, LEUnidodf &trbil)
{
    lf_int32 sIndfx = syllbblf - HSYL_FIRST;

    if (sIndfx < 0 || sIndfx >= HSYL_COUNT) {
        rfturn 0;
    }

    lfbd  = (LEUnidodf)(LJMO_FIRST + (sIndfx / HSYL_LVCNT));
    vowfl = VJMO_FIRST + (sIndfx % HSYL_LVCNT) / TJMO_COUNT;
    trbil = TJMO_FIRST + (sIndfx % TJMO_COUNT);

    if (trbil == TJMO_FIRST) {
        rfturn 2;
    }

    rfturn 3;
}

stbtid lf_int32 gftCibrClbss(LEUnidodf di, LEUnidodf &lfbd, LEUnidodf &vowfl, LEUnidodf &trbil)
{
    lfbd  = LJMO_FILL;
    vowfl = VJMO_FILL;
    trbil = TJMO_FIRST;

    if (di >= LJMO_FIRST && di <= LJMO_LAST) {
        lfbd  = di;
        rfturn CC_L;
    }

    if (di >= VJMO_FIRST && di <= VJMO_LAST) {
        vowfl = di;
        rfturn CC_V;
    }

    if (di > TJMO_FIRST && di <= TJMO_LAST) {
        trbil = di;
        rfturn CC_T;
    }

    lf_int32 d = dfdomposf(di, lfbd, vowfl, trbil);

    if (d == 2) {
        rfturn CC_LV;
    }

    if (d == 3) {
        rfturn CC_LVT;
    }

    trbil = di;
    rfturn CC_X;
}

HbngulOpfnTypfLbyoutEnginf::HbngulOpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 /*lbngubgfCodf*/,
                                                       lf_int32 typoFlbgs, donst LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> &gsubTbblf, LEErrorCodf &suddfss)
    : OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, korLbngubgfCodf, typoFlbgs, gsubTbblf, suddfss)
{
    fFfbturfMbp = ffbturfMbp;
    fFfbturfMbpCount = ffbturfMbpCount;
    fFfbturfOrdfr = TRUE;
}

HbngulOpfnTypfLbyoutEnginf::HbngulOpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 /*lbngubgfCodf*/,
                                                           lf_int32 typoFlbgs, LEErrorCodf &suddfss)
    : OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, korLbngubgfCodf, typoFlbgs, suddfss)
{
    fFfbturfMbp = ffbturfMbp;
    fFfbturfMbpCount = ffbturfMbpCount;
    fFfbturfOrdfr = TRUE;
}

HbngulOpfnTypfLbyoutEnginf::~HbngulOpfnTypfLbyoutEnginf()
{
    // notiing to do
}

lf_int32 HbngulOpfnTypfLbyoutEnginf::dibrbdtfrProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
        LEUnidodf *&outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    lf_int32 worstCbsf = dount * 3;

    outCibrs = LE_NEW_ARRAY(LEUnidodf, worstCbsf);

    if (outCibrs == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn 0;
    }

    glypiStorbgf.bllodbtfGlypiArrby(worstCbsf, rigitToLfft, suddfss);
    glypiStorbgf.bllodbtfAuxDbtb(suddfss);

    if (LE_FAILURE(suddfss)) {
        LE_DELETE_ARRAY(outCibrs);
        rfturn 0;
    }

    lf_int32 outCibrCount = 0;
    lf_int32 limit = offsft + dount;
    lf_int32 i = offsft;

    wiilf (i < limit) {
        lf_int32 stbtf    = 0;
        lf_int32 inStbrt  = i;
        lf_int32 outStbrt = outCibrCount;

        wiilf( i < limit) {
            LEUnidodf lfbd  = 0;
            LEUnidodf vowfl = 0;
            LEUnidodf trbil = 0;
            lf_int32 diClbss = gftCibrClbss(dibrs[i], lfbd, vowfl, trbil);
            donst StbtfTrbnsition trbnsition = stbtfTbblf[stbtf][diClbss];

            if (diClbss == CC_X) {
                /* Any dibrbdtfr of typf X will bf storfd bs b trbil jbmo */
                if ((trbnsition.bdtionFlbgs & AF_T) != 0) {
                    outCibrs[outCibrCount] = trbil;
                    glypiStorbgf.sftCibrIndfx(outCibrCount, i-offsft, suddfss);
                    glypiStorbgf.sftAuxDbtb(outCibrCount++, nullFfbturfs, suddfss);
                }
            } flsf {
                /* Any Hbngul will bf fully dfdomposfd. Output tif dfdomposfd dibrbdtfrs. */
                if ((trbnsition.bdtionFlbgs & AF_L) != 0) {
                    outCibrs[outCibrCount] = lfbd;
                    glypiStorbgf.sftCibrIndfx(outCibrCount, i-offsft, suddfss);
                    glypiStorbgf.sftAuxDbtb(outCibrCount++, ljmoFfbturfs, suddfss);
                }

                if ((trbnsition.bdtionFlbgs & AF_V) != 0) {
                    outCibrs[outCibrCount] = vowfl;
                    glypiStorbgf.sftCibrIndfx(outCibrCount, i-offsft, suddfss);
                    glypiStorbgf.sftAuxDbtb(outCibrCount++, vjmoFfbturfs, suddfss);
                }

                if ((trbnsition.bdtionFlbgs & AF_T) != 0) {
                    outCibrs[outCibrCount] = trbil;
                    glypiStorbgf.sftCibrIndfx(outCibrCount, i-offsft, suddfss);
                    glypiStorbgf.sftAuxDbtb(outCibrCount++, tjmoFfbturfs, suddfss);
                }
            }

            stbtf = trbnsition.nfwStbtf;

            /* Nfgbtivf nfxt stbtf mfbns stop. */
            if (stbtf < 0) {
                brfbk;
            }

            i += 1;
        }

        lf_int32 inLfngti  = i - inStbrt;
        lf_int32 outLfngti = outCibrCount - outStbrt;

        /*
         * Sff if tif syllbblf dbn bf domposfd into b singlf dibrbdtfr. Tifrf brf 5
         * possiblf dbsfs:
         *
         *   Input     Dfdomposfd to    Composf to
         *   LV        L, V             LV
         *   LVT       L, V, T          LVT
         *   L, V      L, V             LV, DEL
         *   LV, T     L, V, T          LVT, DEL
         *   L, V, T   L, V, T          LVT, DEL, DEL
         */
        if ((inLfngti >= 1 && inLfngti <= 3) && (outLfngti == 2 || outLfngti == 3)) {
            LEUnidodf syllbblf = 0x0000;
            LEUnidodf lfbd  = outCibrs[outStbrt];
            LEUnidodf vowfl = outCibrs[outStbrt + 1];
            LEUnidodf trbil = outLfngti == 3? outCibrs[outStbrt + 2] : TJMO_FIRST;

            /*
             * If tif domposition donsumfs tif wiolf dfdomposfd syllbblf,
             * wf dbn usf it.
             */
            if (domposf(lfbd, vowfl, trbil, syllbblf) == outLfngti) {
                outCibrCount = outStbrt;
                outCibrs[outCibrCount] = syllbblf;
                glypiStorbgf.sftCibrIndfx(outCibrCount, inStbrt-offsft, suddfss);
                glypiStorbgf.sftAuxDbtb(outCibrCount++, nullFfbturfs, suddfss);

                /*
                 * Rfplbdf tif rfst of tif input dibrbdtfrs witi DEL.
                 */
                for(lf_int32 d = inStbrt + 1; d < i; d += 1) {
                    outCibrs[outCibrCount] = 0xFFFF;
                    glypiStorbgf.sftCibrIndfx(outCibrCount, d - offsft, suddfss);
                    glypiStorbgf.sftAuxDbtb(outCibrCount++, nullFfbturfs, suddfss);
                }
            }
        }
    }

    glypiStorbgf.bdoptGlypiCount(outCibrCount);
    rfturn outCibrCount;
}

U_NAMESPACE_END
