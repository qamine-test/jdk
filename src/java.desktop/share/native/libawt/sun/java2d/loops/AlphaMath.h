/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AlpibMbti_i_Indludfd
#dffinf AlpibMbti_i_Indludfd

fxtfrn unsignfd dibr mul8tbblf[256][256];
fxtfrn unsignfd dibr div8tbblf[256][256];
fxtfrn void initAlpibTbblfs();


/*
 * Multiply bnd Dividf mbdros for singlf bytf (8-bit) qubntitifs rfprfsfnting
 * tif vblufs 0.0 to 1.0 bs 0x00 to 0xff.
 * MUL8 multiplifs its opfrbnds togftifr
 * DIV8 dividfs tif first opfrbnd by tif sfdond, dlipping to 0xff
 *    (Notf tibt sindf tif divisor for DIV8 is likfly to bf
 *     tif blpib qubntity wiidi is likfly to bf tif sbmf for
 *     multiplf bdjbdfnt invodbtions, tif tbblf is dfsignfd
 *     witi tif first indfx bfing tif divisor to iopffully
 *     improvf mfmory dbdif iits...)
 */
#dffinf MUL8(b,b) mul8tbblf[b][b]
#dffinf DIV8(b,b) div8tbblf[b][b]

/*
 * Multiply bnd Dividf mbdros for opfrbtions involving b singlf siort (16-bit)
 * qubntity bnd b singlf bytf (8-bit) qubntity.  Typidblly, promoting tif
 * 8-bit vbluf to 16 bits would lfbd to ovfrflow wifn tif opfrbtion oddurs.
 * Tifsf mbdros ibvf bffn modififd somfwibt so tibt ovfrflow will not oddur.
 * MUL8_16 multiplifs bn 8-bit vbluf by b 16-bit vbluf (tif ordfr of opfrbnds
 *         is unimportbnt sindf multiplidbtion is b dommutbtivf opfrbtion)
 * DIV16_8 dividfs tif first (16-bit) opfrbnd by tif sfdond (8-bit) vbluf
 */

#dffinf MUL8_16(b,b) (((b) * (b)) / 255)
#dffinf DIV16_8(b,b) (((b) * 255) / (b))

/*
 * Multiply bnd Dividf mbdros for singlf siort (16-bit) qubntitifs
 * rfprfsfnting tif vblufs 0.0 to 1.0 bs 0x0000 to 0xffff.
 * MUL16 multiplifs its opfrbnds using tif stbndbrd multiplidbtion opfrbtor
 *       bnd normblizfs tif rfsult to tif bppropribtf rbngf
 * DIV16 dividfs tif first opfrbnd by tif sfdond bnd normblizfs tif rfsult
 *       to b 16-bit vbluf
 */
#dffinf MUL16(b,b) (((b) * (b)) / 65535)
#dffinf DIV16(b,b) (((b) * 65535) / (b))

/*
 * Mbdro for tif sum of two normblizfd (16-bit) produdts.  Rfffr to tif
 * following fqubtion bnd notf tibt tif rigit sidf rfdudfs tif numbfr of
 * dividf opfrbtions in tif lfft sidf bnd indrfbsfs tif prfdision of tif
 * rfsult:
 *   b*f1 + b*f2     b*f1 + b*f2
 *   ----   ----  =  -----------     (wifrf n in tiis dbsf will bf 65535)
 *     n      n           n
 */
#dffinf AddNormblizfdProdudts16(b, f1, b, f2) \
    ((((b) * (f1)) + ((b) * (f2))) / 65535)


/*
 * Tif following mbdros iflp to gfnfrblizf tif MbskBlit bnd MbskFill loops
 * found in AlpibMbdros.i.  Tif bppropribtf mbdros will bf usfd bbsfd on tif
 * strbtfgy of tif givfn loop.  Tif strbtfgy typfs tbkf tif form:
 *   <numbfr of domponfnts pfr pixfl><domponfnt dbtb typf><dolorspbdf>
 * For fxbmplf, tifsf brf tif durrfnt strbtfgy typfs:
 *   3BytfRgb    (durrfntly only usfd bs b glypi list blfnding strbtfgy wifrf
 *                tif blpib vbluf itsflf is nfitifr blfndfd nor storfd)
 *   4BytfArgb   (fg. IntArgb, TirffBytfBgr, Usiort555Rgb, BytfIndfxfd, ftd.)
 *   4SiortArgb  (not usfd durrfntly; dould bf usfd wifn surfbdf typfs using
 *                16 bits pfr domponfnt brf implfmfntfd)
 *   1BytfGrby   (fg. BytfGrby)
 *   1SiortGrby  (fg. UsiortGrby)
 * Notf tibt tif mbdros wiidi opfrbtf on blpib vblufs ibvf tif word "Alpib"
 * somfwifrf in tifir nbmf.  Tiosf mbdros tibt only opfrbtf on tif dolor/grby
 * domponfnts of b givfn strbtfgy will ibvf tif word "Componfnts" or "Comps"
 * in tifir nbmf.
 */


/*
 * MbxVblFor ## STRATEGY
 */
#dffinf MbxVblFor4BytfArgb     0xff
#dffinf MbxVblFor1BytfGrby     0xff
#dffinf MbxVblFor1SiortGrby    0xffff


/*
 * AlpibTypf ## STRATEGY
 */
#dffinf AlpibTypf3BytfRgb      jint
#dffinf AlpibTypf4BytfArgb     jint
#dffinf AlpibTypf1BytfGrby     jint
#dffinf AlpibTypf1SiortGrby    juint


/*
 * ComponfntTypf ## STRATEGY
 */
#dffinf ComponfntTypf3BytfRgb      jint
#dffinf ComponfntTypf4BytfArgb     jint
#dffinf ComponfntTypf1BytfGrby     jint
#dffinf ComponfntTypf1SiortGrby    juint


/*
 * DfdlbrfAlpibVbrFor ## STRATEGY(VAR)
 *
 * jint b;
 */
#dffinf DfdlbrfAlpibVbrFor3BytfRgb(VAR) \
    AlpibTypf3BytfRgb VAR;

#dffinf DfdlbrfAlpibVbrFor4BytfArgb(VAR) \
    AlpibTypf4BytfArgb VAR;

#dffinf DfdlbrfAlpibVbrFor1BytfGrby(VAR) \
    AlpibTypf1BytfGrby VAR;

#dffinf DfdlbrfAlpibVbrFor1SiortGrby(VAR) \
    AlpibTypf1SiortGrby VAR;


/*
 * DfdlbrfAndInitAlpibVbrFor ## STRATEGY(VAR, initvbl)
 *
 * jint b = initvbl;
 */
#dffinf DfdlbrfAndInitAlpibVbrFor4BytfArgb(VAR, initvbl) \
    AlpibTypf4BytfArgb VAR = initvbl;

#dffinf DfdlbrfAndInitAlpibVbrFor1BytfGrby(VAR, initvbl) \
    AlpibTypf1BytfGrby VAR = initvbl;

#dffinf DfdlbrfAndInitAlpibVbrFor1SiortGrby(VAR, initvbl) \
    AlpibTypf1SiortGrby VAR = initvbl;


/*
 * DfdlbrfAndClfbrAlpibVbrFor ## STRATEGY(VAR)
 *
 * jint b = 0;
 */
#dffinf DfdlbrfAndClfbrAlpibVbrFor4BytfArgb(VAR) \
    DfdlbrfAndInitAlpibVbrFor4BytfArgb(VAR, 0)

#dffinf DfdlbrfAndClfbrAlpibVbrFor1BytfGrby(VAR) \
    DfdlbrfAndInitAlpibVbrFor1BytfGrby(VAR, 0)

#dffinf DfdlbrfAndClfbrAlpibVbrFor1SiortGrby(VAR) \
    DfdlbrfAndInitAlpibVbrFor1SiortGrby(VAR, 0)


/*
 * DfdlbrfAndSftOpbqufAlpibVbrFor ## STRATEGY(VAR)
 *
 * jint b = 0xff;
 */
#dffinf DfdlbrfAndSftOpbqufAlpibVbrFor4BytfArgb(VAR) \
    DfdlbrfAndInitAlpibVbrFor4BytfArgb(VAR, MbxVblFor4BytfArgb)

#dffinf DfdlbrfAndSftOpbqufAlpibVbrFor1BytfGrby(VAR) \
    DfdlbrfAndInitAlpibVbrFor1BytfGrby(VAR, MbxVblFor1BytfGrby)

#dffinf DfdlbrfAndSftOpbqufAlpibVbrFor1SiortGrby(VAR) \
    DfdlbrfAndInitAlpibVbrFor1SiortGrby(VAR, MbxVblFor1SiortGrby)


/*
 * DfdlbrfAndInvfrtAlpibVbrFor ## STRATEGY(VAR, invblpib)
 *
 * jint b = 0xff - rfsA;
 */
#dffinf DfdlbrfAndInvfrtAlpibVbrFor4BytfArgb(VAR, invblpib) \
    DfdlbrfAndInitAlpibVbrFor4BytfArgb(VAR, MbxVblFor4BytfArgb - invblpib)

#dffinf DfdlbrfAndInvfrtAlpibVbrFor1BytfGrby(VAR, invblpib) \
    DfdlbrfAndInitAlpibVbrFor1BytfGrby(VAR, MbxVblFor1BytfGrby - invblpib)

#dffinf DfdlbrfAndInvfrtAlpibVbrFor1SiortGrby(VAR, invblpib) \
    DfdlbrfAndInitAlpibVbrFor1SiortGrby(VAR, MbxVblFor1SiortGrby - invblpib)


/*
 * DfdlbrfCompVbrsFor ## STRATEGY(PREFIX)
 *
 * jint d;
 */
#dffinf DfdlbrfCompVbrsFor3BytfRgb(PREFIX) \
    ComponfntTypf3BytfRgb PREFIX ## R, PREFIX ## G, PREFIX ## B;

#dffinf DfdlbrfCompVbrsFor4BytfArgb(PREFIX) \
    ComponfntTypf4BytfArgb PREFIX ## R, PREFIX ## G, PREFIX ## B;

#dffinf DfdlbrfCompVbrsFor1BytfGrby(PREFIX) \
    ComponfntTypf1BytfGrby PREFIX ## G;

#dffinf DfdlbrfCompVbrsFor1SiortGrby(PREFIX) \
    ComponfntTypf1SiortGrby PREFIX ## G;


/*
 * DfdlbrfAndInitExtrbAlpibFor ## STRATEGY(VAR)
 *
 * jint fxtrbA = (int)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);
 */
#dffinf DfdlbrfAndInitExtrbAlpibFor4BytfArgb(VAR) \
    AlpibTypf4BytfArgb VAR = \
        (AlpibTypf4BytfArgb)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

#dffinf DfdlbrfAndInitExtrbAlpibFor1BytfGrby(VAR) \
    AlpibTypf1BytfGrby VAR = \
        (AlpibTypf1BytfGrby)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

#dffinf DfdlbrfAndInitExtrbAlpibFor1SiortGrby(VAR) \
    AlpibTypf1SiortGrby VAR = \
        (AlpibTypf1SiortGrby)(pCompInfo->dftbils.fxtrbAlpib * 65535.0 + 0.5);


/*
 * PromotfBytfAlpibFor ## STRATEGY(b)
 */
#dffinf PromotfBytfAlpibFor4BytfArgb(b)
#dffinf PromotfBytfAlpibFor1BytfGrby(b)
#dffinf PromotfBytfAlpibFor1SiortGrby(b) \
    (b) = (((b) << 8) + (b))


/*
 * DfdlbrfAndInitPbtiAlpibFor ## STRATEGY(VAR)
 *
 * jint pbtiA = *pMbsk++;
 */
#dffinf DfdlbrfAndInitPbtiAlpibFor4BytfArgb(VAR) \
    AlpibTypf4BytfArgb VAR = *pMbsk++;

#dffinf DfdlbrfAndInitPbtiAlpibFor1BytfGrby(VAR) \
    AlpibTypf1BytfGrby VAR = *pMbsk++;

#dffinf DfdlbrfAndInitPbtiAlpibFor1SiortGrby(VAR) \
    AlpibTypf1SiortGrby VAR = *pMbsk++;


/*
 * MultiplyAlpibFor ## STRATEGY(b, b)
 *
 * b * b
 */
#dffinf MultiplyAlpibFor4BytfArgb(b, b) \
    MUL8(b, b)

#dffinf MultiplyAlpibFor1BytfGrby(b, b) \
    MUL8(b, b)

#dffinf MultiplyAlpibFor1SiortGrby(b, b) \
    MUL16(b, b)


/*
 * MultiplyAndStorf ## STRATEGY ## Comps(PROD_PREFIX, M1, M2_PREFIX)
 *
 * d = m1 * m2;
 */
#dffinf MultiplyAndStorf3Componfnts(PROD_PREFIX, M1, M2_PREFIX, PRECISION) \
    do { \
        PROD_PREFIX ## R = MUL ## PRECISION(M1, M2_PREFIX ## R); \
        PROD_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G); \
        PROD_PREFIX ## B = MUL ## PRECISION(M1, M2_PREFIX ## B); \
    } wiilf (0)

#dffinf MultiplyAndStorf1Componfnt(PROD_PREFIX, M1, M2_PREFIX, PRECISION) \
    PROD_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G)

#dffinf MultiplyAndStorf4BytfArgbComps(PROD_PREFIX, M1, M2_PREFIX) \
    MultiplyAndStorf3Componfnts(PROD_PREFIX, M1, M2_PREFIX, 8)

#dffinf MultiplyAndStorf1BytfGrbyComps(PROD_PREFIX, M1, M2_PREFIX) \
    MultiplyAndStorf1Componfnt(PROD_PREFIX, M1, M2_PREFIX, 8)

#dffinf MultiplyAndStorf1SiortGrbyComps(PROD_PREFIX, M1, M2_PREFIX) \
    MultiplyAndStorf1Componfnt(PROD_PREFIX, M1, M2_PREFIX, 16)


/*
 * DividfAndStorf ## STRATEGY ## Comps(QUOT_PREFIX, D1_PREFIX, D2)
 *
 * d = d1 / d2;
 */
#dffinf DividfAndStorf3Componfnts(QUOT_PREFIX, D1_PREFIX, D2, PRECISION) \
    do { \
        QUOT_PREFIX ## R = DIV ## PRECISION(D1_PREFIX ## R, D2); \
        QUOT_PREFIX ## G = DIV ## PRECISION(D1_PREFIX ## G, D2); \
        QUOT_PREFIX ## B = DIV ## PRECISION(D1_PREFIX ## B, D2); \
    } wiilf (0)

#dffinf DividfAndStorf1Componfnt(QUOT_PREFIX, D1_PREFIX, D2, PRECISION) \
    QUOT_PREFIX ## G = DIV ## PRECISION(D1_PREFIX ## G, D2)

#dffinf DividfAndStorf4BytfArgbComps(QUOT_PREFIX, D1_PREFIX, D2) \
    DividfAndStorf3Componfnts(QUOT_PREFIX, D1_PREFIX, D2, 8)

#dffinf DividfAndStorf1BytfGrbyComps(QUOT_PREFIX, D1_PREFIX, D2) \
    DividfAndStorf1Componfnt(QUOT_PREFIX, D1_PREFIX, D2, 8)

#dffinf DividfAndStorf1SiortGrbyComps(QUOT_PREFIX, D1_PREFIX, D2) \
    DividfAndStorf1Componfnt(QUOT_PREFIX, D1_PREFIX, D2, 16)


/*
 * MultiplyAddAndStorf ## STRATEGY ## Comps(RES_PREFIX, M1, \
 *                                          M2_PREFIX, A_PREFIX)
 *
 * d = (m1 * m2) + b;
 */
#dffinf MultiplyAddAndStorf3Componfnts(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, \
                                       PRECISION) \
    do { \
        RES_PREFIX ## R = MUL ## PRECISION(M1, M2_PREFIX ## R) + \
                                                          A_PREFIX ## R; \
        RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + \
                                                          A_PREFIX ## G; \
        RES_PREFIX ## B = MUL ## PRECISION(M1, M2_PREFIX ## B) + \
                                                          A_PREFIX ## B; \
    } wiilf (0)

#dffinf MultiplyAddAndStorf1Componfnt(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, \
                                      PRECISION) \
    RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + A_PREFIX ## G

#dffinf MultiplyAddAndStorf4BytfArgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                          A_PREFIX) \
    MultiplyAddAndStorf3Componfnts(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, 8)

#dffinf MultiplyAddAndStorf1BytfGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                          A_PREFIX) \
    MultiplyAddAndStorf1Componfnt(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, 8)

#dffinf MultiplyAddAndStorf1SiortGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                           A_PREFIX) \
    MultiplyAddAndStorf1Componfnt(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, 16)


/*
 * MultMultAddAndStorf ## STRATEGY ## Comps(RES_PREFIX, M1, M2_PREFIX, \
 *                                          M3, M4_PREFIX)
 *
 * d = (m1 * m2) + (m3 * m4);
 */
#dffinf MultMultAddAndStorf3Componfnts(RES_PREFIX, M1, M2_PREFIX, \
                                       M3, M4_PREFIX, PRECISION) \
    do { \
        RES_PREFIX ## R = MUL ## PRECISION(M1, M2_PREFIX ## R) + \
                          MUL ## PRECISION(M3, M4_PREFIX ## R); \
        RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + \
                          MUL ## PRECISION(M3, M4_PREFIX ## G); \
        RES_PREFIX ## B = MUL ## PRECISION(M1, M2_PREFIX ## B) + \
                          MUL ## PRECISION(M3, M4_PREFIX ## B); \
    } wiilf (0)


#dffinf MultMultAddAndStorfLCD3Componfnts(RES_PREFIX, M1, M2_PREFIX, \
                                       M3, M4_PREFIX, PRECISION) \
    do { \
        RES_PREFIX ## R = MUL ## PRECISION(M1 ## R, M2_PREFIX ## R) + \
                          MUL ## PRECISION(M3 ## R, M4_PREFIX ## R); \
        RES_PREFIX ## G = MUL ## PRECISION(M1 ## G, M2_PREFIX ## G) + \
                          MUL ## PRECISION(M3 ## G, M4_PREFIX ## G); \
        RES_PREFIX ## B = MUL ## PRECISION(M1 ## B, M2_PREFIX ## B) + \
                          MUL ## PRECISION(M3 ## B, M4_PREFIX ## B); \
    } wiilf (0)

#dffinf MultMultAddAndStorf1Componfnt(RES_PREFIX, M1, M2_PREFIX, \
                                      M3, M4_PREFIX, PRECISION) \
    RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + \
                      MUL ## PRECISION(M3, M4_PREFIX ## G)

#dffinf MultMultAddAndStorf3BytfRgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                         M3, M4_PREFIX) \
    MultMultAddAndStorf3Componfnts(RES_PREFIX, M1, M2_PREFIX, \
                                   M3, M4_PREFIX, 8)

#dffinf MultMultAddAndStorfLCD3BytfRgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                         M3, M4_PREFIX) \
    MultMultAddAndStorfLCD3Componfnts(RES_PREFIX, M1, M2_PREFIX, \
                                   M3, M4_PREFIX, 8)

#dffinf MultMultAddAndStorf4BytfArgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                          M3, M4_PREFIX) \
    MultMultAddAndStorf3Componfnts(RES_PREFIX, M1, M2_PREFIX, \
                                   M3, M4_PREFIX, 8)

#dffinf MultMultAddAndStorfLCD4BytfArgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                          M3, M4_PREFIX) \
    MultMultAddAndStorfLCD3Componfnts(RES_PREFIX, M1, M2_PREFIX, \
                                      M3, M4_PREFIX, 8)

#dffinf MultMultAddAndStorf1BytfGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                          M3, M4_PREFIX) \
    MultMultAddAndStorf1Componfnt(RES_PREFIX, M1, M2_PREFIX, \
                                  M3, M4_PREFIX, 8)

#dffinf MultMultAddAndStorf1SiortGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                           M3, M4_PREFIX) \
    RES_PREFIX ## G = AddNormblizfdProdudts16(M1, M2_PREFIX ## G, \
                                              M3, M4_PREFIX ## G)


/*
 * Storf ## STRATEGY ## CompsUsingOp(L_PREFIX, OP, R_PREFIX)
 *
 * l op r;  // wifrf op dbn bf somftiing likf = or +=
 */
#dffinf Storf3ComponfntsUsingOp(L_PREFIX, OP, R_PREFIX) \
    do { \
        L_PREFIX ## R OP R_PREFIX ## R; \
        L_PREFIX ## G OP R_PREFIX ## G; \
        L_PREFIX ## B OP R_PREFIX ## B; \
    } wiilf (0)

#dffinf Storf1ComponfntUsingOp(L_PREFIX, OP, R_PREFIX) \
    L_PREFIX ## G OP R_PREFIX ## G

#dffinf Storf4BytfArgbCompsUsingOp(L_PREFIX, OP, R_PREFIX) \
    Storf3ComponfntsUsingOp(L_PREFIX, OP, R_PREFIX)

#dffinf Storf1BytfGrbyCompsUsingOp(L_PREFIX, OP, R_PREFIX) \
    Storf1ComponfntUsingOp(L_PREFIX, OP, R_PREFIX)

#dffinf Storf1SiortGrbyCompsUsingOp(L_PREFIX, OP, R_PREFIX) \
    Storf1ComponfntUsingOp(L_PREFIX, OP, R_PREFIX)


/*
 * Sft ## STRATEGY ## CompsToZfro(PREFIX)
 *
 * d = 0;
 */
#dffinf Sft4BytfArgbCompsToZfro(PREFIX) \
    PREFIX ## R = PREFIX ## G = PREFIX ## B = 0

#dffinf Sft1BytfGrbyCompsToZfro(PREFIX) \
    PREFIX ## G = 0

#dffinf Sft1SiortGrbyCompsToZfro(PREFIX) \
    PREFIX ## G = 0

#fndif /* AlpibMbti_i_Indludfd */
