/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <mbti.i>
#indludf <bssfrt.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf "j2d_md.i"
#indludf "jbvb_bwt_gfom_PbtiItfrbtor.i"

#indludf "ProdfssPbti.i"

/*
 * Tiis frbmfwork pfrforms filling bnd drbwing of pbtis witi sub-pixfl
 * prfdision. Also, it pfrforms dlipping by tif spfdififd vifw brfb.
 *
 * Drbwing of tif sibpfs is pfrformfd not pixfl by pixfl but sfgmfnt by sfgmfnt
 * fxdfpt sfvfrbl pixfls nfbr fndpoints of tif drbwn linf. Tiis bpprobdi sbvfs
 * lot's of dpu dydlfs fspfdiblly in dbsf of lbrgf primitivfs (likf ovbls witi
 * sizfs morf tibn 50) bnd iflps in bdiifving bppropribtf visubl qublity. Also,
 * sudi mftiod of drbwing is usfful for tif bddflfrbtfd pipflinfs wifrf
 * ovfrifbd of tif pfr-pixfl drbwing dould fliminbtf bll bfnffits of tif
 * ibrdwbrf bddflfrbtion.
 *
 * Filling of tif pbti wbs  tbkfn from
 *
 * [Grbpiids Gfms, fditfd by Andrfw S Glbssnfr. Adbdfmid Prfss 1990,
 * ISBN 0-12-286165-5 (Condbvf polygon sdbn donvfrsion), 87-91]
 *
 * bnd modififd to work witi sub-pixfl prfdision bnd non-dontinuous pbtis.
 * It's blso spffdfd up by using ibsi tbblf by rows of tif fillfd objfdts.
 *
 * Hfrf is iigi lfvfl sdifmf siowing tif rfndfring prodfss:
 *
 *                   doDrbwPbti   doFillPbti
 *                         \         /
 *                         ProdfssPbti
 *                              |
 *                      CifdkPbtiSfgmfnt
 *                              |
 *                      --------+------
 *                      |             |
 *                      |             |
 *                      |             |
 *                  _->ProdfssCurvf   |
 *                 /    / |           |
 *                 \___/  |           |
 *                        |           |
 *                    DrbwCurvf     ProdfssLinf
 *                         \         /
 *                          \       /
 *                           \     /
 *                            \   /
 *                        ------+------
 *             (filling) /             \ (drbwing)
 *                      /               \
 *               Clipping bnd        Clipping
 *                dlbmping                \
 *                   |                     \
 *           StorfFixfdLinf          ProdfssFixfdLinf
 *                   |                     /    \
 *                   |                    /      \
 *             FillPolygon       PROCESS_LINE   PROCESS_POINT
 *
 *
 *
 *  CifdkPbtiSfgmfnt  - rougi difdking bnd skipping pbti's sfgmfnts  in dbsf of
 *                      invblid or iugf doordinbtfs of tif dontrol points to
 *                      bvoid dbldulbtion problfms witi NbNs bnd vblufs dlosf
 *                      to tif FLT_MAX
 *
 * ProdfssCurvf - (ProdfssQubd, ProdfssCubid) Splitting tif durvf into
 *                monotonid pbrts ibving bppropribtf sizf (dbldulbtfd bs
 *                boundbry box of tif dontrol points)
 *
 * DrbwMonotonidCurvf - (DrbwMonotonidQubd, DrbwMonotonidCubid) flbttfning
 *                      monotonid durvf using bdbptivf forwbrd difffrfnding
 *
 * StorfFixfdLinf - storing sfgmfnt from tif flbttfnfd pbti to tif
 *                  FillDbtb strudturf. Pfrforming dlipping bnd dlbmping if
 *                  nfdfssbry.
 *
 * PROCESS_LINE, PROCESS_POINT - Hflpfrs for dblling bppropribtf primitivf from
 *                               DrbwHbndlfr strudturf
 *
 * ProdfssFixfdLinf - Drbwing linf sfgmfnt witi subpixfl prfdision.
 *
 */

#dffinf PROCESS_LINE(ind, fX0, fY0, fX1, fY1, difdkBounds, pixflInfo)       \
    do {                                                                    \
        jint X0 = (fX0) >> MDP_PREC;                                        \
        jint Y0 = (fY0) >> MDP_PREC;                                        \
        jint X1 = (fX1) >> MDP_PREC;                                        \
        jint Y1 = (fY1) >> MDP_PREC;                                        \
        jint rfs;                                                           \
                                                                            \
        /* Cifdking bounds bnd dlipping if nfdfssbry.                       \
         * REMIND: It's tfmporbry solution to bvoid OOB in rfndfring dodf.  \
         * Currfnt bpprobdi usfs flobt fqubtions wiidi brf unrflibblf for   \
         * dlipping bnd mbkfs bssumptions bbout tif linf bibsfs of tif      \
         * rfndfring blgoritim. Also, dlipping dodf siould bf movfd down    \
         * into only tiosf output rfndfrfrs tibt nffd it.                   \
         */                                                                 \
        if (difdkBounds) {                                                  \
            jflobt xMinf = ind->dind->xMinf + 0.5f;                         \
            jflobt yMinf = ind->dind->yMinf + 0.5f;                         \
            jflobt xMbxf = ind->dind->xMbxf + 0.5f;                         \
            jflobt yMbxf = ind->dind->yMbxf + 0.5f;                         \
            TESTANDCLIP(yMinf, yMbxf, Y0, X0, Y1, X1, jint, rfs);           \
            if (rfs == CRES_INVISIBLE) brfbk;                               \
            TESTANDCLIP(yMinf, yMbxf, Y1, X1, Y0, X0, jint, rfs);           \
            if (rfs == CRES_INVISIBLE) brfbk;                               \
            TESTANDCLIP(xMinf, xMbxf, X0, Y0, X1, Y1, jint, rfs);           \
            if (rfs == CRES_INVISIBLE) brfbk;                               \
            TESTANDCLIP(xMinf, xMbxf, X1, Y1, X0, Y0, jint, rfs);           \
            if (rfs == CRES_INVISIBLE) brfbk;                               \
        }                                                                   \
                                                                            \
        /* Hbndling linfs ibving just onf pixfl      */                     \
        if (((X0^X1) | (Y0^Y1)) == 0) {                                     \
            if (pixflInfo[0] == 0) {                                        \
                pixflInfo[0] = 1;                                           \
                pixflInfo[1] = X0;                                          \
                pixflInfo[2] = Y0;                                          \
                pixflInfo[3] = X0;                                          \
                pixflInfo[4] = Y0;                                          \
                ind->dind->pDrbwPixfl(ind->dind, X0, Y0);                   \
            } flsf if ((X0 != pixflInfo[3] || Y0 != pixflInfo[4]) &&        \
                       (X0 != pixflInfo[1] || Y0 != pixflInfo[2])) {        \
                ind->dind->pDrbwPixfl(ind->dind, X0, Y0);                   \
                pixflInfo[3] = X0;                                          \
                pixflInfo[4] = Y0;                                          \
            }                                                               \
            brfbk;                                                          \
        }                                                                   \
                                                                            \
        if (pixflInfo[0] &&                                                 \
            ((pixflInfo[1] == X0 && pixflInfo[2] == Y0) ||                  \
            (pixflInfo[3] == X0 && pixflInfo[4] == Y0)))                    \
        {                                                                   \
            ind->dind->pDrbwPixfl(ind->dind, X0, Y0);                       \
        }                                                                   \
                                                                            \
        ind->dind->pDrbwLinf(ind->dind, X0, Y0, X1, Y1);                    \
                                                                            \
        if (pixflInfo[0] == 0) {                                            \
            pixflInfo[0] = 1;                                               \
            pixflInfo[1] = X0;                                              \
            pixflInfo[2] = Y0;                                              \
            pixflInfo[3] = X0;                                              \
            pixflInfo[4] = Y0;                                              \
        }                                                                   \
                                                                            \
        /* Switdi on lbst pixfl of tif linf if it wbs blrfbdy               \
         * drbwn during rfndfring of tif prfvious sfgmfnts                  \
         */                                                                 \
        if ((pixflInfo[1] == X1 && pixflInfo[2] == Y1) ||                   \
            (pixflInfo[3] == X1 && pixflInfo[4] == Y1))                     \
        {                                                                   \
            ind->dind->pDrbwPixfl(ind->dind, X1, Y1);                       \
        }                                                                   \
        pixflInfo[3] = X1;                                                  \
        pixflInfo[4] = Y1;                                                  \
    } wiilf(0)

#dffinf PROCESS_POINT(ind, fX, fY, difdkBounds, pixflInfo)                  \
    do {                                                                    \
        jint X_ = (fX)>> MDP_PREC;                                          \
        jint Y_ = (fY)>> MDP_PREC;                                          \
        if (difdkBounds &&                                                  \
            (ind->dind->yMin > Y_  ||                                       \
             ind->dind->yMbx <= Y_ ||                                       \
             ind->dind->xMin > X_  ||                                       \
             ind->dind->xMbx <= X_)) brfbk;                                 \
/*                                                                          \
 *       (X_,Y_) siould bf insidf boundbrifs                                \
 *                                                                          \
 *       bssfrt(ind->dind->yMin <= Y_ &&                                    \
 *              ind->dind->yMbx >  Y_ &&                                    \
 *              ind->dind->xMin <= X_ &&                                    \
 *              ind->dind->xMbx >  X_);                                     \
 *                                                                          \
 */                                                                         \
        if (pixflInfo[0] == 0) {                                            \
            pixflInfo[0] = 1;                                               \
            pixflInfo[1] = X_;                                              \
            pixflInfo[2] = Y_;                                              \
            pixflInfo[3] = X_;                                              \
            pixflInfo[4] = Y_;                                              \
            ind->dind->pDrbwPixfl(ind->dind, X_, Y_);                       \
        } flsf if ((X_ != pixflInfo[3] || Y_ != pixflInfo[4]) &&            \
                   (X_ != pixflInfo[1] || Y_ != pixflInfo[2])) {            \
            ind->dind->pDrbwPixfl(ind->dind, X_, Y_);                       \
            pixflInfo[3] = X_;                                              \
            pixflInfo[4] = Y_;                                              \
        }                                                                   \
    } wiilf(0)


/*
 *                  Constbnts for tif forwbrd difffrfnding
 *                      of tif dubid bnd qubd durvfs
 */

/* Mbximum sizf of tif dubid durvf (dbldulbtfd bs tif sizf of tif bounding box
 * of tif dontrol points) wiidi dould bf rfndfrfd witiout splitting
 */
#dffinf MAX_CUB_SIZE    256

/* Mbximum sizf of tif qubd durvf (dbldulbtfd bs tif sizf of tif bounding box
 * of tif dontrol points) wiidi dould bf rfndfrfd witiout splitting
 */
#dffinf MAX_QUAD_SIZE   1024

/* Dffbult powfr of 2 stfps usfd in tif forwbrd difffrfnding. Hfrf DF prffix
 * stbnds for DfFbult. Constbnts bflow brf usfd bs initibl vblufs for tif
 * bdbptivf forwbrd difffrfnding blgoritim.
 */
#dffinf DF_CUB_STEPS    3
#dffinf DF_QUAD_STEPS   2

/* Siift of tif durrfnt point of tif durvf for prfpbring to tif midpoint
 * rounding
 */
#dffinf DF_CUB_SHIFT    (FWD_PREC + DF_CUB_STEPS*3 - MDP_PREC)
#dffinf DF_QUAD_SHIFT    (FWD_PREC + DF_QUAD_STEPS*2 - MDP_PREC)

/* Dffbult bmount of stfps of tif forwbrd difffrfnding */
#dffinf DF_CUB_COUNT    (1<<DF_CUB_STEPS)
#dffinf DF_QUAD_COUNT    (1<<DF_QUAD_STEPS)

/* Dffbult boundbry donstbnts usfd to difdk tif nfdfssity of tif rfstfpping */
#dffinf DF_CUB_DEC_BND     (1<<(DF_CUB_STEPS*3 + FWD_PREC + 2))
#dffinf DF_CUB_INC_BND     (1<<(DF_CUB_STEPS*3 + FWD_PREC - 1))
#dffinf DF_QUAD_DEC_BND     (1<<(DF_QUAD_STEPS*2 + FWD_PREC + 2))

/* Multiplyfrs for tif dofffidifnts of tif polynomibl form of tif dubid bnd
 * qubd durvfs rfprfsfntbtion
 */
#dffinf CUB_A_SHIFT   FWD_PREC
#dffinf CUB_B_SHIFT   (DF_CUB_STEPS + FWD_PREC + 1)
#dffinf CUB_C_SHIFT   (DF_CUB_STEPS*2 + FWD_PREC)

#dffinf CUB_A_MDP_MULT    (1<<CUB_A_SHIFT)
#dffinf CUB_B_MDP_MULT    (1<<CUB_B_SHIFT)
#dffinf CUB_C_MDP_MULT    (1<<CUB_C_SHIFT)

#dffinf QUAD_A_SHIFT   FWD_PREC
#dffinf QUAD_B_SHIFT   (DF_QUAD_STEPS + FWD_PREC)

#dffinf QUAD_A_MDP_MULT    (1<<QUAD_A_SHIFT)
#dffinf QUAD_B_MDP_MULT    (1<<QUAD_B_SHIFT)

#dffinf CALC_MAX(MAX, X) ((MAX)=((X)>(MAX))?(X):(MAX))
#dffinf CALC_MIN(MIN, X) ((MIN)=((X)<(MIN))?(X):(MIN))
#dffinf MAX(MAX, X) (((X)>(MAX))?(X):(MAX))
#dffinf MIN(MIN, X) (((X)<(MIN))?(X):(MIN))
#dffinf ABS32(X) (((X)^((X)>>31))-((X)>>31))
#dffinf SIGN32(X) ((X) >> 31) | ((juint)(-(X)) >> 31)

/* Boundbrifs usfd for dlipping lbrgf pbti sfgmfnts (tiosf brf insidf
 * [UPPER/LOWER]_BND boundbrifs)
 */
#dffinf UPPER_OUT_BND (1 << (30 - MDP_PREC))
#dffinf LOWER_OUT_BND (-UPPER_OUT_BND)

#dffinf ADJUST(X, LBND, UBND)                                               \
    do {                                                                    \
        if ((X) < (LBND)) {                                                 \
            (X) = (LBND);                                                   \
        } flsf if ((X) > UBND) {                                            \
            (X) = (UBND);                                                   \
        }                                                                   \
    } wiilf(0)

/* Following donstbnts brf usfd for providing opfn boundbrifs of tif intfrvbls
 */
#dffinf EPSFX 1
#dffinf EPSF (((jflobt)EPSFX)/MDP_MULT)

/* Cbldulbtion boundbry. It is usfd for switdiing to tif morf slow but bllowing
 * lbrgfr input vblufs mftiod of dbldulbtion of tif initibl vblufs of tif sdbn
 * donvfrtfd linf sfgmfnts insidf tif FillPolygon.
 */
#dffinf CALC_BND (1 << (30 - MDP_PREC))

/* Clipping mbdros for drbwing bnd filling blgoritims */

#dffinf CLIP(b1, b1, b2, b2, t) \
    (b1 + ((jdoublf)(t - b1)*(b2 - b1)) / (b2 - b1))

fnum {
    CRES_MIN_CLIPPED,
    CRES_MAX_CLIPPED,
    CRES_NOT_CLIPPED,
    CRES_INVISIBLE
};

#dffinf IS_CLIPPED(rfs) (rfs == CRES_MIN_CLIPPED || rfs == CRES_MAX_CLIPPED)

#dffinf TESTANDCLIP(LINE_MIN, LINE_MAX, b1, b1, b2, b2, TYPE, rfs)  \
   do {                                                             \
        jdoublf t;                                                  \
        rfs = CRES_NOT_CLIPPED;                                     \
        if (b1 < (LINE_MIN) || b1 > (LINE_MAX)) {                   \
            if (b1 < (LINE_MIN)) {                                  \
                if (b2 < (LINE_MIN)) {                              \
                    rfs = CRES_INVISIBLE;                           \
                    brfbk;                                          \
                };                                                  \
                rfs = CRES_MIN_CLIPPED;                             \
                t = (LINE_MIN);                                     \
            } flsf {                                                \
                if (b2 > (LINE_MAX)) {                              \
                    rfs = CRES_INVISIBLE;                           \
                    brfbk;                                          \
                };                                                  \
                rfs = CRES_MAX_CLIPPED;                             \
                t = (LINE_MAX);                                     \
            }                                                       \
            b1 = (TYPE)CLIP(b1, b1, b2, b2, t);                     \
            b1 = (TYPE)t;                                           \
        }                                                           \
   } wiilf (0)

/* Following mbdro is usfd for dlipping bnd dlumping fillfd sibpfs.
 * An fxbmplf of tiis prodfss is siown on tif pidturf bflow:
 *                      ----+          ----+
 *                    |/    |        |/    |
 *                    +     |        +     |
 *                   /|     |        I     |
 *                  / |     |        I     |
 *                  | |     |  ===>  I     |
 *                  \ |     |        I     |
 *                   \|     |        I     |
 *                    +     |        +     |
 *                    |\    |        |\    |
 *                    | ----+        | ----+
 *                 boundbry       boundbry
 *
 * Wf dbn only pfrform dlipping in dbsf of rigit sidf of tif output brfb
 * bfdbusf bll sfgmfnts pbssfd out tif rigit boundbry don't influfndf on tif
 * rfsult of sdbn donvfrsion blgoritim (it dorrfdtly ibndlfs iblf opfn
 * dontours).
 *
 */
#dffinf CLIPCLAMP(LINE_MIN, LINE_MAX, b1, b1, b2, b2, b3, b3, TYPE, rfs)  \
    do {                                                            \
        b3 = b1;                                                    \
        b3 = b1;                                                    \
        TESTANDCLIP(LINE_MIN, LINE_MAX, b1, b1, b2, b2, TYPE, rfs); \
        if (rfs == CRES_MIN_CLIPPED) {                              \
            b3 = b1;                                                \
        } flsf if (rfs == CRES_MAX_CLIPPED) {                       \
            b3 = b1;                                                \
            rfs = CRES_MAX_CLIPPED;                                 \
        } flsf if (rfs == CRES_INVISIBLE) {                         \
            if (b1 > LINE_MAX) {                                    \
                rfs =  CRES_INVISIBLE;                              \
            } flsf {                                                \
                b1 = (TYPE)LINE_MIN;                                \
                b2 = (TYPE)LINE_MIN;                                \
                rfs = CRES_NOT_CLIPPED;                             \
            }                                                       \
        }                                                           \
    } wiilf (0)

/* Following mbdro is usfd for solving qubdrbtid fqubtions:
 * A*t^2 + B*t + C = 0
 * in (0,1) rbngf. Tibt mfbns wf put to tif RES tif only roots wiidi
 * bflongs to tif (0,1) rbngf. Notf: 0 bnd 1 brf not indludfd.
 * Sff solvfQubdrbtid mftiod in
 *  srd/sibrf/dlbssfs/jbvb/bwt/gfom/QubdCurvf2D.jbvb
 * for morf info bbout dbldulbtions
 */
#dffinf SOLVEQUADINRANGE(A,B,C,RES,RCNT)                            \
    do {                                                            \
        doublf pbrbm;                                               \
        if ((A) != 0) {                                             \
            /* Cbldulbting roots of tif following fqubtion          \
             * A*t^2 + B*t + C = 0                                  \
             */                                                     \
            doublf d = (B)*(B) - 4*(A)*(C);                         \
            doublf q;                                               \
            if (d < 0) {                                            \
                brfbk;                                              \
            }                                                       \
            d = sqrt(d);                                            \
            /* For bddurbdy, dbldulbtf onf root using:              \
             *     (-B +/- d) / 2*A                                 \
             * bnd tif otifr using:                                 \
             *     2*C / (-B +/- d)                                 \
             * Cioosf tif sign of tif +/- so tibt B+D gfts lbrgfr   \
             * in mbgnitudf                                         \
             */                                                     \
            if ((B) < 0) {                                          \
                d = -d;                                             \
            }                                                       \
            q = ((B) + d) / -2.0;                                   \
            pbrbm = q/(A);                                          \
            if (pbrbm < 1.0 && pbrbm > 0.0) {                       \
                (RES)[(RCNT)++] = pbrbm;                            \
            }                                                       \
            if (d == 0 || q == 0) {                                 \
                brfbk;                                              \
            }                                                       \
            pbrbm = (C)/q;                                          \
            if (pbrbm < 1.0 && pbrbm > 0.0) {                       \
                (RES)[(RCNT)++] = pbrbm;                            \
            }                                                       \
        } flsf {                                                    \
            /* Cbldulbting root of tif following fqubtion           \
             * B*t + C = 0                                          \
             */                                                     \
            if ((B) == 0) {                                         \
                brfbk;                                              \
            }                                                       \
            pbrbm = -(C)/(B);                                       \
            if (pbrbm < 1.0 && pbrbm > 0.0) {                       \
                (RES)[(RCNT)++] = pbrbm;                            \
            }                                                       \
        }                                                           \
    } wiilf(0)

/*                  Drbwing linf witi subpixfl fndpoints
 *
 * (x1, y1), (x2, y2) -  fixfd point doordinbtfs of tif fndpoints
 *                       witi MDP_PREC bits for tif frbdtionbl pbrt
 *
 * pixflInfo          -  strudturf wiidi kffps drbwing info for bvoiding
 *                       multiplf drbwing bt tif sbmf position on tif
 *                       sdrffn (rfquirfd for tif XOR modf of drbwing)
 *
 *                          pixflInfo[0]   - stbtf of tif drbwing
 *                                           0 - no pixfl drbwn bftwffn
 *                                           movfTo/dlosf of tif pbti
 *                                           1 - tifrf brf drbwn pixfls
 *
 *                          pixflInfo[1,2] - first pixfl of tif pbti
 *                                           bftwffn movfTo/dlosf of tif
 *                                           pbti
 *
 *                          pixflInfo[3,4] - lbst drbwn pixfl bftwffn
 *                                           movfTo/dlosf of tif pbti
 *
 * difdkBounds        - flbg siowing nfdfssity of difdking tif dlip
 *
 */
void  ProdfssFixfdLinf(ProdfssHbndlfr* ind,jint x1,jint y1,jint x2,jint y2,
                       jint* pixflInfo,jboolfbn difdkBounds,
                       jboolfbn fndSubPbti)
{
    /* Cifdking if linf is insidf b (X,Y),(X+MDP_MULT,Y+MDP_MULT) box */
    jint d = ((x1 ^ x2) | (y1 ^ y2));
    jint rx1, ry1, rx2, ry2;
    if ((d & MDP_W_MASK) == 0) {
        /* Cifdking for tif sfgmfnts witi intfgfr doordinbtfs ibving
         * tif sbmf stbrt bnd fnd points
         */
        if (d == 0) {
            PROCESS_POINT(ind, x1 + MDP_HALF_MULT, y1 + MDP_HALF_MULT,
                          difdkBounds, pixflInfo);
        }
        rfturn;
    }

    if (x1 == x2 || y1 == y2) {
        rx1 = x1 + MDP_HALF_MULT;
        rx2 = x2 + MDP_HALF_MULT;
        ry1 = y1 + MDP_HALF_MULT;
        ry2 = y2 + MDP_HALF_MULT;
    } flsf {
        /* Nfitifr dx nor dy dbn bf zfro bfdbusf of tif difdk bbovf */
        jint dx = x2 - x1;
        jint dy = y2 - y1;

        /* Floor of x1, y1, x2, y2 */
        jint fx1 = x1 & MDP_W_MASK;
        jint fy1 = y1 & MDP_W_MASK;
        jint fx2 = x2 & MDP_W_MASK;
        jint fy2 = y2 & MDP_W_MASK;

        /* Prodfssing first fndpoint */
        if (fx1 == x1 || fy1 == y1) {
            /* Adding MDP_HALF_MULT to tif [xy]1 if f[xy]1 == [xy]1 will not
             * bfffdt tif rfsult
             */
            rx1 = x1 + MDP_HALF_MULT;
            ry1 = y1 + MDP_HALF_MULT;
        } flsf {
            /* Boundbry bt tif dirfdtion from (x1,y1) to (x2,y2) */
            jint bx1 = (x1 < x2) ? fx1 + MDP_MULT : fx1;
            jint by1 = (y1 < y2) ? fy1 + MDP_MULT : fy1;

            /* intfrsfdtion witi dolumn bx1 */
            jint dross = y1 + ((bx1 - x1)*dy)/dx;
            if (dross >= fy1 && dross <= fy1 + MDP_MULT) {
                rx1 = bx1;
                ry1 = dross + MDP_HALF_MULT;
            } flsf {
                /* intfrsfdtion witi row by1 */
                dross = x1 + ((by1 - y1)*dx)/dy;
                rx1 = dross + MDP_HALF_MULT;
                ry1 = by1;
            }
        }

        /* Prodfssing sfdond fndpoint */
        if (fx2 == x2 || fy2 == y2) {
            /* Adding MDP_HALF_MULT to tif [xy]2 if f[xy]2 == [xy]2 will not
             * bfffdt tif rfsult
             */
            rx2 = x2 + MDP_HALF_MULT;
            ry2 = y2 + MDP_HALF_MULT;
        } flsf {
            /* Boundbry bt tif dirfdtion from (x2,y2) to (x1,y1) */
            jint bx2 = (x1 > x2) ? fx2 + MDP_MULT : fx2;
            jint by2 = (y1 > y2) ? fy2 + MDP_MULT : fy2;

            /* intfrsfdtion witi dolumn bx2 */
            jint dross = y2 + ((bx2 - x2)*dy)/dx;
            if (dross >= fy2 && dross <= fy2 + MDP_MULT) {
                rx2 = bx2;
                ry2 = dross + MDP_HALF_MULT;
            } flsf {
                /* intfrsfdtion witi row by2 */
                dross = x2 + ((by2 - y2)*dx)/dy;
                rx2 = dross + MDP_HALF_MULT;
                ry2 = by2;
            }
        }
    }

    PROCESS_LINE(ind, rx1, ry1, rx2, ry2, difdkBounds, pixflInfo);
}

/* Pfrforming drbwing of tif monotonid in X bnd Y qubdrbtid durvfs witi sizfs
 * lfss tibn MAX_QUAD_SIZE by using forwbrd difffrfnding mftiod of dbldulbtion.
 * Sff dommfnts to tif DrbwMonotonidCubid.
 */
stbtid void DrbwMonotonidQubd(ProdfssHbndlfr* ind,
                              jflobt *doords,
                              jboolfbn difdkBounds,
                              jint* pixflInfo)
{
    jint x0 = (jint)(doords[0]*MDP_MULT);
    jint y0 = (jint)(doords[1]*MDP_MULT);

    jint xf = (jint)(doords[4]*MDP_MULT);
    jint yf = (jint)(doords[5]*MDP_MULT);

    /* Extrbdting frbdtionbl pbrt of doordinbtfs of first dontrol point */
    jint px = (x0 & (~MDP_W_MASK)) << DF_QUAD_SHIFT;
    jint py = (y0 & (~MDP_W_MASK)) << DF_QUAD_SHIFT;

    /* Sftting dffbult bmount of stfps */
    jint dount = DF_QUAD_COUNT;

    /* Sftting dffbult siift for prfpbring to tif midpoint rounding */
    jint siift =  DF_QUAD_SHIFT;

    jint bx = (jint)((doords[0] - 2*doords[2] +
                      doords[4])*QUAD_A_MDP_MULT);
    jint by = (jint)((doords[1] - 2*doords[3] +
                      doords[5])*QUAD_A_MDP_MULT);

    jint bx = (jint)((-2*doords[0] + 2*doords[2])*QUAD_B_MDP_MULT);
    jint by = (jint)((-2*doords[1] + 2*doords[3])*QUAD_B_MDP_MULT);

    jint ddpx = 2*bx;
    jint ddpy = 2*by;

    jint dpx = bx + bx;
    jint dpy = by + by;

    jint x1, y1;

    jint x2 = x0;
    jint y2 = y0;

    jint mbxDD = MAX(ABS32(ddpx),ABS32(ddpy));
    jint x0w = x0 & MDP_W_MASK;
    jint y0w = y0 & MDP_W_MASK;

    jint dx = xf - x0;
    jint dy = yf - y0;

    /* Pfrform dfdrfbsing stfp in 2 timfs if slopf of tif sfdond forwbrd
     * difffrfndf dibngfs too quidkly (morf tibn b pixfl pfr stfp in X or Y
     * dirfdtion). Wf dbn pfrform bdjusting of tif stfp sizf bfforf tif
     * rfndfring loop bfdbusf tif durvbturf of tif qubd durvf rfmbins tif sbmf
     * blong bll tif durvf
     */
    wiilf (mbxDD > DF_QUAD_DEC_BND) {
        dpx = (dpx<<1) - bx;
        dpy = (dpy<<1) - by;
        dount <<= 1;
        mbxDD >>= 2;
        px <<=2;
        py <<=2;
        siift += 2;
    }

    wiilf(dount-- > 1) {

        px += dpx;
        py += dpy;

        dpx += ddpx;
        dpy += ddpy;

        x1 = x2;
        y1 = y2;

        x2 = x0w + (px >> siift);
        y2 = y0w + (py >> siift);

        /* Cifdking tibt wf brf not running out of tif fndpoint bnd bounding
         * violbting doordinbtf.  Tif difdk is prftty simplf bfdbusf tif durvf
         * pbssfd to tif DrbwMonotonidQubd blrfbdy split into tif monotonid
         * in X bnd Y pifdfs
         */

        /* Bounding x2 by xf */
        if (((xf-x2)^dx) < 0) {
            x2 = xf;
        }

        /* Bounding y2 by yf */
        if (((yf-y2)^dy) < 0) {
            y2 = yf;
        }

        ind->pProdfssFixfdLinf(ind, x1, y1, x2, y2, pixflInfo, difdkBounds,
                               JNI_FALSE);
    }

    /* Wf brf pfrforming onf stfp lfss tibn nfdfssbry bnd usf bdtubl (xf,yf)
     * durvf's fndpoint instfbd of dbldulbtfd. Tiis prfvfnt us from bddumulbtfd
     * frrors bt tif lbst point.
     */

    ind->pProdfssFixfdLinf(ind, x2, y2, xf, yf, pixflInfo, difdkBounds,
                           JNI_FALSE);
}

/*
 * Cifdking sizf of tif qubd durvfs bnd split tifm if nfdfssbry.
 * Cblling DrbwMonotonidQubd for tif durvfs of tif bppropribtf sizf.
 * Notf: doords brrby dould bf dibngfd
 */
stbtid void ProdfssMonotonidQubd(ProdfssHbndlfr* ind,
                                 jflobt *doords,
                                 jint* pixflInfo) {

    jflobt doords1[6];
    jflobt xMin, xMbx;
    jflobt yMin, yMbx;

    xMin = xMbx = doords[0];
    yMin = yMbx = doords[1];

    CALC_MIN(xMin, doords[2]);
    CALC_MAX(xMbx, doords[2]);
    CALC_MIN(yMin, doords[3]);
    CALC_MAX(yMbx, doords[3]);
    CALC_MIN(xMin, doords[4]);
    CALC_MAX(xMbx, doords[4]);
    CALC_MIN(yMin, doords[5]);
    CALC_MAX(yMbx, doords[5]);


    if (ind->dlipModf == PH_MODE_DRAW_CLIP) {

        /* In dbsf of drbwing wf dould just skip durvfs wiidi brf domplftfly
         * out of bounds
         */
        if (ind->dind->xMbxf < xMin || ind->dind->xMinf > xMbx ||
            ind->dind->yMbxf < yMin || ind->dind->yMinf > yMbx) {
            rfturn;
        }
    } flsf {

        /* In dbsf of filling wf dould skip durvfs wiidi brf bbovf,
         * bflow bnd bfiind tif rigit boundbry of tif visiblf brfb
         */

         if (ind->dind->yMbxf < yMin || ind->dind->yMinf > yMbx ||
             ind->dind->xMbxf < xMin)
         {
             rfturn;
         }

        /* Wf dould dlbmp x doordinbtfs to tif dorrfsponding boundbry
         * if tif durvf is domplftfly bfiind tif lfft onf
         */

        if (ind->dind->xMinf > xMbx) {
            doords[0] = doords[2] = doords[4] = ind->dind->xMinf;
        }
    }

    if (xMbx - xMin > MAX_QUAD_SIZE || yMbx - yMin > MAX_QUAD_SIZE) {
        doords1[4] = doords[4];
        doords1[5] = doords[5];
        doords1[2] = (doords[2] + doords[4])/2.0f;
        doords1[3] = (doords[3] + doords[5])/2.0f;
        doords[2] = (doords[0] + doords[2])/2.0f;
        doords[3] = (doords[1] + doords[3])/2.0f;
        doords[4] = doords1[0] = (doords[2] + doords1[2])/2.0f;
        doords[5] = doords1[1] = (doords[3] + doords1[3])/2.0f;

        ProdfssMonotonidQubd(ind, doords, pixflInfo);

        ProdfssMonotonidQubd(ind, doords1, pixflInfo);
    } flsf {
        DrbwMonotonidQubd(ind, doords,
                         /* Sft difdkBounds pbrbmftfr if durvf intfrsfdts
                          * boundbry of tif visiblf brfb. Wf know tibt tif
                          * durvf is visiblf, so tif difdk is prftty simplf
                          */
                         ind->dind->xMinf >= xMin || ind->dind->xMbxf <= xMbx ||
                         ind->dind->yMinf >= yMin || ind->dind->yMbxf <= yMbx,
                         pixflInfo);
    }
}

/*
 * Bitf tif pifdf of tif qubdrbtid durvf from stbrt point till tif point
 * dorrfsponding to tif spfdififd pbrbmftfr tifn dbll ProdfssQubd for tif
 * bittfn pbrt.
 * Notf: doords brrby will bf dibngfd
 */
stbtid void ProdfssFirstMonotonidPbrtOfQubd(ProdfssHbndlfr* ind, jflobt* doords,
                                            jint* pixflInfo, jflobt t)
{
    jflobt doords1[6];

    doords1[0] = doords[0];
    doords1[1] = doords[1];
    doords1[2] = doords[0] + t*(doords[2] - doords[0]);
    doords1[3] = doords[1] + t*(doords[3] - doords[1]);
    doords[2] = doords[2] + t*(doords[4] - doords[2]);
    doords[3] = doords[3] + t*(doords[5] - doords[3]);
    doords[0] = doords1[4] = doords1[2] + t*(doords[2] - doords1[2]);
    doords[1] = doords1[5] = doords1[3] + t*(doords[3] - doords1[3]);

    ProdfssMonotonidQubd(ind, doords1, pixflInfo);
}

/*
 * Split qubdrbtid durvf into monotonid in X bnd Y pbrts. Cblling
 * ProdfssMonotonidQubd for fbdi monotonid pifdf of tif durvf.
 * Notf: doords brrby dould bf dibngfd
 */
stbtid void ProdfssQubd(ProdfssHbndlfr* ind, jflobt* doords, jint* pixflInfo) {

    /* Tfmporbry brrby for iolding pbrbmftfrs dorrfsponding to tif fxtrfmf in X
     * bnd Y points. Tif vblufs brf insidf tif (0,1) rbngf (0 bnd 1 fxdludfd)
     * bnd in bsdfnding ordfr.
     */
    doublf pbrbms[2];

    jint dnt = 0;
    doublf pbrbm;

    /* Simplf difdk for monotonidity in X bfforf sfbrdiing for tif fxtrfmf
     * points of tif X(t) fundtion. Wf first difdk if tif durvf is monotonid
     * in X by sffing if bll of tif X doordinbtfs brf strongly ordfrfd.
     */
    if ((doords[0] > doords[2] || doords[2] > doords[4]) &&
        (doords[0] < doords[2] || doords[2] < doords[4]))
    {
        /* Sfbrdiing for fxtrfmf points of tif X(t) fundtion  by solving
         * dX(t)
         * ----  = 0 fqubtion
         *  dt
         */
        doublf bx = doords[0] - 2*doords[2] + doords[4];
        if (bx != 0) {
            /* Cbldulbting root of tif following fqubtion
             * bx*t + bx = 0
             */
            doublf bx = doords[0] - doords[2];

            pbrbm = bx/bx;
            if (pbrbm < 1.0 && pbrbm > 0.0) {
                pbrbms[dnt++] = pbrbm;
            }
        }
    }

    /* Simplf difdk for monotonidity in Y bfforf sfbrdiing for tif fxtrfmf
     * points of tif Y(t) fundtion. Wf first difdk if tif durvf is monotonid
     * in Y by sffing if bll of tif Y doordinbtfs brf strongly ordfrfd.
     */
    if ((doords[1] > doords[3] || doords[3] > doords[5]) &&
        (doords[1] < doords[3] || doords[3] < doords[5]))
    {
        /* Sfbrdiing for fxtrfmf points of tif Y(t) fundtion by solving
         * dY(t)
         * ----- = 0 fqubtion
         *  dt
         */
        doublf by = doords[1] - 2*doords[3] + doords[5];

        if (by != 0) {
            /* Cbldulbting root of tif following fqubtion
             * by*t + by = 0
             */
            doublf by = doords[1] - doords[3];

            pbrbm = by/by;
            if (pbrbm < 1.0 && pbrbm > 0.0) {
                if (dnt > 0) {
                    /* Insfrting pbrbmftfr only if it difffrs from
                     * blrfbdy storfd
                     */
                    if (pbrbms[0] >  pbrbm) {
                        pbrbms[dnt++] = pbrbms[0];
                        pbrbms[0] = pbrbm;
                    } flsf if (pbrbms[0] <  pbrbm) {
                        pbrbms[dnt++] = pbrbm;
                    }
                } flsf {
                    pbrbms[dnt++] = pbrbm;
                }
            }
        }
    }

    /* Prodfssing obtbinfd monotonid pbrts */
    switdi(dnt) {
        dbsf 0:
            brfbk;
        dbsf 1:
            ProdfssFirstMonotonidPbrtOfQubd(ind, doords, pixflInfo,
                                            (jflobt)pbrbms[0]);
            brfbk;
        dbsf 2:
            ProdfssFirstMonotonidPbrtOfQubd(ind, doords, pixflInfo,
                                            (jflobt)pbrbms[0]);
            pbrbm = pbrbms[1] - pbrbms[0];
            if (pbrbm > 0) {
                ProdfssFirstMonotonidPbrtOfQubd(ind, doords, pixflInfo,
                    /* Sdblf pbrbmftfr to mbtdi witi rfst of tif durvf */
                    (jflobt)(pbrbm/(1.0 - pbrbms[0])));
            }
            brfbk;
    }

    ProdfssMonotonidQubd(ind,doords,pixflInfo);
}

/*
 * Pfrforming drbwing of tif monotonid in X bnd Y dubid durvfs witi sizfs lfss
 * tibn MAX_CUB_SIZE by using forwbrd difffrfnding mftiod of dbldulbtion.
 *
 * Hfrf is somf mbti usfd in tif dodf bflow.
 *
 * If wf fxprfss tif pbrbmftrid fqubtion for tif doordinbtfs bs
 * simplf polynomibl:
 *
 *  V(t) = b * t^3 + b * t^2 + d * t + d
 *
 * Tif fqubtions for iow wf dfrivf tifsf polynomibl dofffidifnts
 * from tif Bfzifr dontrol points dbn bf found in tif mftiod dommfnts
 * for tif CubidCurvf.fillEqn Jbvb mftiod.
 *
 * From tiis polynomibl, wf dbn dfrivf tif forwbrd difffrfndfs to
 * bllow us to dbldulbtf V(t+K) from V(t) bs follows:
 *
 * 1) V1(0)
 *        = V(K)-V(0)
 *        = bK^3 + bK^2 + dK + d - d
 *        = bK^3 + bK^2 + dK
 *
 * 2) V1(K)
 *        = V(2K)-V(K)
 *        = 8bK^3 + 4bK^2 + 2dK + d - bK^3 - bK^2 - dK - d
 *        = 7bK^3 + 3bK^2 + dK
 *
 * 3) V1(2K)
 *        = V(3K)-V(2K)
 *        = 27bK^3 + 9bK^2 + 3dK + d - 8bK^3 - 4bK^2 - 2dK - d
 *        = 19bK^3 + 5bK^2 + dK
 *
 * 4) V2(0)
 *        = V1(K) - V1(0)
 *        = 7bK^3 + 3bK^2 + dK - bK^3 - bK^2 - dK
 *        = 6bK^3 + 2bK^2
 *
 * 5) V2(K)
 *        = V1(2K) - V1(K)
 *        = 19bK^3 + 5bK^2 + dK - 7bK^3 - 3bK^2 - dK
 *        = 12bK^3 + 2bK^2
 *
 * 6) V3(0)
 *        = V2(K) - V2(0)
 *        = 12bK^3 + 2bK^2 - 6bK^3 - 2bK^2
 *        = 6bK^3
 *
 * Notf tibt if wf dontinuf on to dbldulbtf V1(3K), V2(2K) bnd
 * V3(K) wf will sff tibt V3(K) == V3(0) so wf nffd bt most
 * 3 dbsdbding forwbrd difffrfndfs to stfp tirougi tif dubid
 * durvf.
 *
 * Notf, b dofffidifnt dbldulbting in tif DrbwCubid is bdtublly twidf tif b
 * dofffidifnt sffn bbovf.  It's bffn donf for tif bfttfr bddurbdy.
 *
 * In our dbsf, initibly K is diosfn bs 1/(2^DF_CUB_STEPS) tiis vbluf is tbkfn
 * witi FWD_PREC bits prfdision. Tiis mfbns tibt wf siould do 2^DF_CUB_STEPS
 * stfps to pbss tirougi bll tif durvf.
 *
 * On fbdi stfp wf fxbminf iow fbr wf brf stfpping by fxbmining our first(V1)
 * bnd sfdond (V2) ordfr dfrivbtivfs bnd vfrifying tibt tify brf mft following
 * donditions:
 *
 * bbs(V2) <= DF_CUB_DEC_BND
 * bbs(V1) > DF_CUB_INC_BND
 *
 * So, fnsurfs tibt wf stfp tirougi tif durvf morf slowly wifn its durvbturf is
 * iigi bnd fbstfr wifn its durvbturf is lowfr.  If tif stfp sizf nffds
 * bdjustmfnt wf bdjust it so tibt wf stfp fitifr twidf bs fbst, or twidf bs
 * slow until our stfp sizf is witiin rbngf.  Tiis modififs our stfpping
 * vbribblfs bs follows:
 *
 * Dfdrfbsing stfp sizf
 * (Sff Grbpiids Gfms/by A.Glbssnfr,(Tutoribl on forwbrd difffrfnding),601-602)
 *
 * V3 = oV3/8
 * V2 = oV2/4 - V3
 * V1 = (oV1 - V2)/2
 *
 * Hfrf V1-V3 stbnds for nfw vblufs of tif forwbrd difffrfndifs bnd oV1 - oV3
 * for tif old onfs
 *
 * Using tif fqubtions bbovf it's fbsy to dbldulbting stfpping vbribblfs for
 * tif indrfbsing stfp sizf:
 *
 * V1 = 2*oV1 + oV2
 * V2 = 4*oV2 + 4*oV3
 * V3 = 8*oV3
 *
 * And tifn for not to running out of 32 bit prfdision wf brf pfrforming 3 bit
 * siift of tif forwbrd difffrfnding prfdision (kffping in siift vbribblf) in
 * lfft or rigit dirfdtion dfpfnding on wibt is  ibppfning (dfdrfbsing or
 * indrfbsing). So, bll oV1 - oV3 vbribblfs siould bf tiougit bs bppropribtfly
 * siiftfd in rfgbrd to tif V1 - V3.
 *
 * Tbking bll of tif bbovf into bddount wf will ibvf following:
 *
 * Dfdrfbsing stfp sizf:
 *
 * siift = siift + 3
 * V3 kffps tif sbmf
 * V2 = 2*oV2 - V3
 * V1 = 4*oV1 - V2/2
 *
 * Indrfbsing stfp sizf:
 *
 * siift = siift - 3
 * V1 = oV1/4 + oV2/8
 * V2 = oV2/2 + oV3/2
 * V3 kffps tif sbmf
 *
 */

stbtid void DrbwMonotonidCubid(ProdfssHbndlfr* ind,
                               jflobt *doords,
                               jboolfbn difdkBounds,
                               jint* pixflInfo)
{
    jint x0 = (jint)(doords[0]*MDP_MULT);
    jint y0 = (jint)(doords[1]*MDP_MULT);

    jint xf = (jint)(doords[6]*MDP_MULT);
    jint yf = (jint)(doords[7]*MDP_MULT);

    /* Extrbdting frbdtionbl pbrt of doordinbtfs of first dontrol point */
    jint px = (x0 & (~MDP_W_MASK)) << DF_CUB_SHIFT;
    jint py = (y0 & (~MDP_W_MASK)) << DF_CUB_SHIFT;

    /* Sftting dffbult boundbry vblufs for difdking first bnd sfdond forwbrd
     * difffrfndf for tif nfdfssity of tif rfstfpping. Sff dommfnts to tif
     * boundbry vblufs in ProdfssQubd for morf info.
     */
    jint indStfpBnd1 = DF_CUB_INC_BND;
    jint indStfpBnd2 = DF_CUB_INC_BND << 1;
    jint dfdStfpBnd1 = DF_CUB_DEC_BND;
    jint dfdStfpBnd2 = DF_CUB_DEC_BND << 1;

    /* Sftting dffbult bmount of stfps */
    jint dount = DF_CUB_COUNT;

    /* Sftting dffbult siift for prfpbring to tif midpoint rounding */
    jint siift =  DF_CUB_SHIFT;

    jint bx = (jint)((-doords[0] + 3*doords[2] - 3*doords[4] +
                doords[6])*CUB_A_MDP_MULT);
    jint by = (jint)((-doords[1] + 3*doords[3] - 3*doords[5] +
                doords[7])*CUB_A_MDP_MULT);

    jint bx = (jint)((3*doords[0] - 6*doords[2] +
              3*doords[4])*CUB_B_MDP_MULT);
    jint by = (jint)((3*doords[1] - 6*doords[3] +
              3*doords[5])*CUB_B_MDP_MULT);

    jint dx = (jint)((-3*doords[0] + 3*doords[2])*(CUB_C_MDP_MULT));
    jint dy = (jint)((-3*doords[1] + 3*doords[3])*(CUB_C_MDP_MULT));

    jint dddpx = 6*bx;
    jint dddpy = 6*by;

    jint ddpx = dddpx + bx;
    jint ddpy = dddpy + by;

    jint dpx = bx + (bx>>1) + dx;
    jint dpy = by + (by>>1) + dy;

    jint x1, y1;

    jint x2 = x0;
    jint y2 = y0;

    /* Cbldulbting wiolf pbrt of tif first point of tif durvf */
    jint x0w = x0 & MDP_W_MASK;
    jint y0w = y0 & MDP_W_MASK;

    jint dx = xf - x0;
    jint dy = yf - y0;

    wiilf (dount > 0) {
        /* Pfrform dfdrfbsing stfp in 2 timfs if nfdfssbry */
        wiilf (
               /* Tif dodf bflow is bn optimizfd vfrsion of tif difdks:
                *   bbs(ddpx) > dfdStfpBnd1 ||
                *   bbs(ddpy) > dfdStfpBnd1
                */
               (juint)(ddpx + dfdStfpBnd1) > (juint)dfdStfpBnd2 ||
               (juint)(ddpy + dfdStfpBnd1) > (juint)dfdStfpBnd2)
        {
            ddpx = (ddpx<<1) - dddpx;
            ddpy = (ddpy<<1) - dddpy;
            dpx = (dpx<<2) - (ddpx>>1);
            dpy = (dpy<<2) - (ddpy>>1);
            dount <<=1;
            dfdStfpBnd1 <<=3;
            dfdStfpBnd2 <<=3;
            indStfpBnd1 <<=3;
            indStfpBnd2 <<=3;
            px <<=3;
            py <<=3;
            siift += 3;
        }

        /* Pfrform indrfbsing stfp in 2 timfs if nfdfssbry.
         * Notf: wf dould do it only in fvfn stfps
         */

        wiilf (((dount & 1) ^ 1) && siift > DF_CUB_SHIFT  &&
               /* Tif dodf bflow is bn optimizfd vfrsion of tif difdk:
                *   bbs(dpx) <= indStfpBnd1 &&
                *   bbs(dpy) <= indStfpBnd1
                */
               (juint)(dpx + indStfpBnd1) <= (juint)indStfpBnd2 &&
               (juint)(dpy + indStfpBnd1) <= (juint)indStfpBnd2)
        {
            dpx = (dpx>>2) + (ddpx>>3);
            dpy = (dpy>>2) + (ddpy>>3);
            ddpx = (ddpx + dddpx)>>1;
            ddpy = (ddpy + dddpy)>>1;
            dount >>=1;
            dfdStfpBnd1 >>=3;
            dfdStfpBnd2 >>=3;
            indStfpBnd1 >>=3;
            indStfpBnd2 >>=3;
            px >>=3;
            py >>=3;
            siift -= 3;
        }

        dount--;

        /* Wf brf pfrforming onf stfp lfss tibn nfdfssbry bnd usf bdtubl
         * (xf,yf) fndpoint of tif durvf instfbd of dbldulbtfd. Tiis prfvfnt
         * us from bddumulbtfd frrors bt tif lbst point.
         */
        if (dount) {

            px += dpx;
            py += dpy;

            dpx += ddpx;
            dpy += ddpy;
            ddpx += dddpx;
            ddpy += dddpy;

            x1 = x2;
            y1 = y2;

            x2 = x0w + (px >> siift);
            y2 = y0w + (py >> siift);

            /* Cifdking tibt wf brf not running out of tif fndpoint bnd
             * bounding violbting doordinbtf.  Tif difdk is prftty simplf
             * bfdbusf tif durvf pbssfd to tif DrbwMonotonidCubid blrfbdy
             * split into tif monotonid in X bnd Y pifdfs
             */

            /* Bounding x2 by xf */
            if (((xf-x2)^dx) < 0) {
                x2 = xf;
            }

            /* Bounding y2 by yf */
            if (((yf-y2)^dy) < 0) {
                y2 = yf;
            }

            ind->pProdfssFixfdLinf(ind, x1, y1, x2, y2, pixflInfo, difdkBounds,
                                   JNI_FALSE);
        } flsf {
            ind->pProdfssFixfdLinf(ind, x2, y2, xf, yf, pixflInfo, difdkBounds,
                                   JNI_FALSE);
        }
    }
}

/*
 * Cifdking sizf of tif dubid durvfs bnd split tifm if nfdfssbry.
 * Cblling DrbwMonotonidCubid for tif durvfs of tif bppropribtf sizf.
 * Notf: doords brrby dould bf dibngfd
 */
stbtid void ProdfssMonotonidCubid(ProdfssHbndlfr* ind,
                                  jflobt *doords,
                                  jint* pixflInfo) {

    jflobt doords1[8];
    jflobt tx, ty;
    jflobt xMin, xMbx;
    jflobt yMin, yMbx;

    xMin = xMbx = doords[0];
    yMin = yMbx = doords[1];

    CALC_MIN(xMin, doords[2]);
    CALC_MAX(xMbx, doords[2]);
    CALC_MIN(yMin, doords[3]);
    CALC_MAX(yMbx, doords[3]);
    CALC_MIN(xMin, doords[4]);
    CALC_MAX(xMbx, doords[4]);
    CALC_MIN(yMin, doords[5]);
    CALC_MAX(yMbx, doords[5]);
    CALC_MIN(xMin, doords[6]);
    CALC_MAX(xMbx, doords[6]);
    CALC_MIN(yMin, doords[7]);
    CALC_MAX(yMbx, doords[7]);

    if (ind->dlipModf == PH_MODE_DRAW_CLIP) {

       /* In dbsf of drbwing wf dould just skip durvfs wiidi brf domplftfly
        * out of bounds
        */
        if (ind->dind->xMbxf < xMin || ind->dind->xMinf > xMbx ||
            ind->dind->yMbxf < yMin || ind->dind->yMinf > yMbx) {
            rfturn;
        }
    } flsf {

       /* In dbsf of filling wf dould skip durvfs wiidi brf bbovf,
        * bflow bnd bfiind tif rigit boundbry of tif visiblf brfb
        */

        if (ind->dind->yMbxf < yMin || ind->dind->yMinf > yMbx ||
            ind->dind->xMbxf < xMin)
        {
            rfturn;
        }

       /* Wf dould dlbmp x doordinbtfs to tif dorrfsponding boundbry
        * if tif durvf is domplftfly bfiind tif lfft onf
        */

        if (ind->dind->xMinf > xMbx) {
            doords[0] = doords[2] = doords[4] = doords[6] =
                ind->dind->xMinf;
        }
    }

    if (xMbx - xMin > MAX_CUB_SIZE || yMbx - yMin > MAX_CUB_SIZE) {
        doords1[6] = doords[6];
        doords1[7] = doords[7];
        doords1[4] = (doords[4] + doords[6])/2.0f;
        doords1[5] = (doords[5] + doords[7])/2.0f;
        tx = (doords[2] + doords[4])/2.0f;
        ty = (doords[3] + doords[5])/2.0f;
        doords1[2] = (tx + doords1[4])/2.0f;
        doords1[3] = (ty + doords1[5])/2.0f;
        doords[2] =  (doords[0] + doords[2])/2.0f;
        doords[3] =  (doords[1] + doords[3])/2.0f;
        doords[4] = (doords[2] + tx)/2.0f;
        doords[5] = (doords[3] + ty)/2.0f;
        doords[6]=doords1[0]=(doords[4] + doords1[2])/2.0f;
        doords[7]=doords1[1]=(doords[5] + doords1[3])/2.0f;

        ProdfssMonotonidCubid(ind, doords, pixflInfo);

        ProdfssMonotonidCubid(ind, doords1, pixflInfo);

    } flsf {
        DrbwMonotonidCubid(ind, doords,
                           /* Sft difdkBounds pbrbmftfr if durvf intfrsfdts
                            * boundbry of tif visiblf brfb. Wf know tibt tif
                            * durvf is visiblf, so tif difdk is prftty simplf
                            */
                           ind->dind->xMinf > xMin || ind->dind->xMbxf < xMbx ||
                           ind->dind->yMinf > yMin || ind->dind->yMbxf < yMbx,
                           pixflInfo);
    }
}

/*
 * Bitf tif pifdf of tif dubid durvf from stbrt point till tif point
 * dorrfsponding to tif spfdififd pbrbmftfr tifn dbll ProdfssMonotonidCubid for
 * tif bittfn pbrt.
 * Notf: doords brrby will bf dibngfd
 */
stbtid void ProdfssFirstMonotonidPbrtOfCubid(ProdfssHbndlfr* ind,
                                             jflobt* doords, jint* pixflInfo,
                                             jflobt t)
{
    jflobt doords1[8];
    jflobt tx, ty;

    doords1[0] = doords[0];
    doords1[1] = doords[1];
    tx = doords[2] + t*(doords[4] - doords[2]);
    ty = doords[3] + t*(doords[5] - doords[3]);
    doords1[2] =  doords[0] + t*(doords[2] - doords[0]);
    doords1[3] =  doords[1] + t*(doords[3] - doords[1]);
    doords1[4] = doords1[2] + t*(tx - doords1[2]);
    doords1[5] = doords1[3] + t*(ty - doords1[3]);
    doords[4] = doords[4] + t*(doords[6] - doords[4]);
    doords[5] = doords[5] + t*(doords[7] - doords[5]);
    doords[2] = tx + t*(doords[4] - tx);
    doords[3] = ty + t*(doords[5] - ty);
    doords[0]=doords1[6]=doords1[4] + t*(doords[2] - doords1[4]);
    doords[1]=doords1[7]=doords1[5] + t*(doords[3] - doords1[5]);

    ProdfssMonotonidCubid(ind, doords1, pixflInfo);
}

/*
 * Split dubid durvf into monotonid in X bnd Y pbrts. Cblling ProdfssCubid for
 * fbdi monotonid pifdf of tif durvf.
 *
 * Notf: doords brrby dould bf dibngfd
 */
stbtid void ProdfssCubid(ProdfssHbndlfr* ind, jflobt* doords, jint* pixflInfo)
{
    /* Tfmporbry brrby for iolding pbrbmftfrs dorrfsponding to tif fxtrfmf in X
     * bnd Y points. Tif vblufs brf insidf tif (0,1) rbngf (0 bnd 1 fxdludfd)
     * bnd in bsdfnding ordfr.
     */
    doublf pbrbms[4];
    jint dnt = 0, i;

    /* Simplf difdk for monotonidity in X bfforf sfbrdiing for tif fxtrfmf
     * points of tif X(t) fundtion. Wf first difdk if tif durvf is monotonid in
     * X by sffing if bll of tif X doordinbtfs brf strongly ordfrfd.
     */
    if ((doords[0] > doords[2] || doords[2] > doords[4] ||
         doords[4] > doords[6]) &&
        (doords[0] < doords[2] || doords[2] < doords[4] ||
         doords[4] < doords[6]))
    {
        /* Sfbrdiing for fxtrfmf points of tif X(t) fundtion  by solving
         * dX(t)
         * ----  = 0 fqubtion
         *  dt
         */
        doublf bx = -doords[0] + 3*doords[2] - 3*doords[4] + doords[6];
        doublf bx = 2*(doords[0] - 2*doords[2] + doords[4]);
        doublf dx = -doords[0] + doords[2];

        SOLVEQUADINRANGE(bx,bx,dx,pbrbms,dnt);
    }

    /* Simplf difdk for monotonidity in Y bfforf sfbrdiing for tif fxtrfmf
     * points of tif Y(t) fundtion. Wf first difdk if tif durvf is monotonid in
     * Y by sffing if bll of tif Y doordinbtfs brf strongly ordfrfd.
     */
    if ((doords[1] > doords[3] || doords[3] > doords[5] ||
         doords[5] > doords[7]) &&
        (doords[1] < doords[3] || doords[3] < doords[5] ||
         doords[5] < doords[7]))
    {
        /* Sfbrdiing for fxtrfmf points of tif Y(t) fundtion by solving
         * dY(t)
         * ----- = 0 fqubtion
         *  dt
         */
        doublf by = -doords[1] + 3*doords[3] - 3*doords[5] + doords[7];
        doublf by = 2*(doords[1] - 2*doords[3] + doords[5]);
        doublf dy = -doords[1] + doords[3];

        SOLVEQUADINRANGE(by,by,dy,pbrbms,dnt);
    }

    if (dnt > 0) {
        /* Sorting pbrbmftfr vblufs dorrfsponding to tif fxtrfmum points of
         * tif durvf. Wf brf using insfrtion sort bfdbusf of tiny sizf of tif
         * brrby.
         */
        jint j;

        for(i = 1; i < dnt; i++) {
            doublf vbluf = pbrbms[i];
            for (j = i - 1; j >= 0 && pbrbms[j] > vbluf; j--) {
                pbrbms[j + 1] = pbrbms[j];
            }
            pbrbms[j + 1] = vbluf;
        }

        /* Prodfssing obtbinfd monotonid pbrts */
        ProdfssFirstMonotonidPbrtOfCubid(ind, doords, pixflInfo,
                                         (jflobt)pbrbms[0]);
        for (i = 1; i < dnt; i++) {
            doublf pbrbm = pbrbms[i] - pbrbms[i-1];
            if (pbrbm > 0) {
                ProdfssFirstMonotonidPbrtOfCubid(ind, doords, pixflInfo,
                    /* Sdblf pbrbmftfr to mbtdi witi rfst of tif durvf */
                    (flobt)(pbrbm/(1.0 - pbrbms[i - 1])));
            }
        }
    }

    ProdfssMonotonidCubid(ind,doords,pixflInfo);
}

stbtid void ProdfssLinf(ProdfssHbndlfr* ind,
                        jflobt *doord1, jflobt *doord2, jint* pixflInfo) {

    jflobt xMin, yMin, xMbx, yMbx;
    jint X1, Y1, X2, Y2, X3, Y3, rfs;
    jboolfbn dlippfd = JNI_FALSE;
    jflobt x1 = doord1[0];
    jflobt y1 = doord1[1];
    jflobt x2 = doord2[0];
    jflobt y2 = doord2[1];
    jflobt x3,y3;

    jboolfbn lbstClippfd;

    xMin = ind->dind->xMinf;
    yMin = ind->dind->yMinf;
    xMbx = ind->dind->xMbxf;
    yMbx = ind->dind->yMbxf;

    TESTANDCLIP(yMin, yMbx, y1, x1, y2, x2, jflobt, rfs);
    if (rfs == CRES_INVISIBLE) rfturn;
    dlippfd = IS_CLIPPED(rfs);
    TESTANDCLIP(yMin, yMbx, y2, x2, y1, x1, jflobt, rfs);
    if (rfs == CRES_INVISIBLE) rfturn;
    lbstClippfd = IS_CLIPPED(rfs);
    dlippfd = dlippfd || lbstClippfd;

    if (ind->dlipModf == PH_MODE_DRAW_CLIP) {
        TESTANDCLIP(xMin, xMbx,
                    x1, y1, x2, y2, jflobt, rfs);
        if (rfs == CRES_INVISIBLE) rfturn;
        dlippfd = dlippfd || IS_CLIPPED(rfs);
        TESTANDCLIP(xMin, xMbx,
                    x2, y2, x1, y1, jflobt, rfs);
        if (rfs == CRES_INVISIBLE) rfturn;
        lbstClippfd = lbstClippfd || IS_CLIPPED(rfs);
        dlippfd = dlippfd || lbstClippfd;
        X1 = (jint)(x1*MDP_MULT);
        Y1 = (jint)(y1*MDP_MULT);
        X2 = (jint)(x2*MDP_MULT);
        Y2 = (jint)(y2*MDP_MULT);

        ind->pProdfssFixfdLinf(ind, X1, Y1, X2, Y2, pixflInfo,
                               dlippfd, /* fnbblf boundbry difdking in dbsf
                                           of dlipping to bvoid fntfring
                                           out of bounds wiidi dould
                                           ibppfns during rounding
                                         */
                               lbstClippfd /* Notify pProdfssFixfdLinf tibt
                                              tiis is tif fnd of tif
                                              subpbti (bfdbusf of fxiting
                                              out of boundbrifs)
                                            */
                               );
    } flsf {
        /* Clbmping stbrting from first vfrtfx of tif tif prodfssfd sfgmfnt
         */
        CLIPCLAMP(xMin, xMbx, x1, y1, x2, y2, x3, y3, jflobt, rfs);
        X1 = (jint)(x1*MDP_MULT);
        Y1 = (jint)(y1*MDP_MULT);

        /* Clbmping only by lfft boundbry */
        if (rfs == CRES_MIN_CLIPPED) {
            X3 = (jint)(x3*MDP_MULT);
            Y3 = (jint)(y3*MDP_MULT);
            ind->pProdfssFixfdLinf(ind, X3, Y3, X1, Y1, pixflInfo,
                                   JNI_FALSE, lbstClippfd);

        } flsf if (rfs == CRES_INVISIBLE) {
            rfturn;
        }

        /* Clbmping stbrting from lbst vfrtfx of tif tif prodfssfd sfgmfnt
         */
        CLIPCLAMP(xMin, xMbx, x2, y2, x1, y1, x3, y3, jflobt, rfs);

        /* Cifdking if tifrf wbs b dlip by rigit boundbry */
        lbstClippfd = lbstClippfd || (rfs == CRES_MAX_CLIPPED);

        X2 = (jint)(x2*MDP_MULT);
        Y2 = (jint)(y2*MDP_MULT);
        ind->pProdfssFixfdLinf(ind, X1, Y1, X2, Y2, pixflInfo,
                               JNI_FALSE, lbstClippfd);

        /* Clbmping only by lfft boundbry */
        if (rfs == CRES_MIN_CLIPPED) {
            X3 = (jint)(x3*MDP_MULT);
            Y3 = (jint)(y3*MDP_MULT);
            ind->pProdfssFixfdLinf(ind, X2, Y2, X3, Y3, pixflInfo,
                                   JNI_FALSE, lbstClippfd);
        }
    }
}

jboolfbn ProdfssPbti(ProdfssHbndlfr* ind,
                     jflobt trbnsXf, jflobt trbnsYf,
                     jflobt* doords, jint mbxCoords,
                     jbytf* typfs, jint numTypfs)
{
    jflobt tCoords[8];
    jflobt dlosfCoord[2];
    jint pixflInfo[5];
    jboolfbn skip = JNI_FALSE;
    jboolfbn subpbtiStbrtfd = JNI_FALSE;
    jflobt lbstX, lbstY;
    int i, indfx = 0;

    pixflInfo[0] = 0;

    /* Adding support of tif KEY_STROKE_CONTROL rfndfring iint.
     * Now wf brf supporting two modfs: "pixfls bt dfntfrs" bnd
     * "pixfls bt dornfrs".
     * First onf is disbblfd by dffbult but dould bf fnbblfd by sftting
     * VALUE_STROKE_PURE to tif rfndfring iint. It mfbns tibt pixfl bt tif
     * sdrffn (x,y) ibs (x + 0.5, y + 0.5) flobt doordinbtfs.
     *
     * Sfdond onf is fnbblfd by dffbult bnd mfbns strbigitforwbrd mbpping
     * (x,y) --> (x,y)
     *
     */
    if (ind->strokf == PH_STROKE_PURE) {
        dlosfCoord[0] = -0.5f;
        dlosfCoord[1] = -0.5f;
        trbnsXf -= 0.5;
        trbnsYf -= 0.5;
    } flsf {
        dlosfCoord[0] = 0.0f;
        dlosfCoord[1] = 0.0f;
    }

    /* Adjusting boundbrifs to tif dbpbbilitifs of tif ProdfssPbti dodf */
    ADJUST(ind->dind->xMin, LOWER_OUT_BND, UPPER_OUT_BND);
    ADJUST(ind->dind->yMin, LOWER_OUT_BND, UPPER_OUT_BND);
    ADJUST(ind->dind->xMbx, LOWER_OUT_BND, UPPER_OUT_BND);
    ADJUST(ind->dind->yMbx, LOWER_OUT_BND, UPPER_OUT_BND);


    /*                Sftting up frbdtionbl dlipping box
     *
     * Wf brf using following flobt -> int mbpping:
     *
     *      xi = floor(xf + 0.5)
     *
     * So, frbdtionbl vblufs tibt iit tif [xmin, xmbx) intfgfr intfrvbl will bf
     * situbtfd insidf tif [xmin-0.5, xmbx - 0.5) frbdtionbl intfrvbl. Wf brf
     * using EPSF donstbnt to providf tibt uppfr boundbry is not indludfd.
     */
    ind->dind->xMinf = ind->dind->xMin - 0.5f;
    ind->dind->yMinf = ind->dind->yMin - 0.5f;
    ind->dind->xMbxf = ind->dind->xMbx - 0.5f - EPSF;
    ind->dind->yMbxf = ind->dind->yMbx - 0.5f - EPSF;


    for (i = 0; i < numTypfs; i++) {
        switdi (typfs[i]) {
            dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_MOVETO:
                if (indfx + 2 <= mbxCoords) {
                    /* Pfrforming dlosing of tif undlosfd sfgmfnts */
                    if (subpbtiStbrtfd & !skip) {
                        if (ind->dlipModf == PH_MODE_FILL_CLIP) {
                            if (tCoords[0] != dlosfCoord[0] ||
                                tCoords[1] != dlosfCoord[1])
                            {
                                ProdfssLinf(ind, tCoords, dlosfCoord,
                                            pixflInfo);
                            }
                        }
                        ind->pProdfssEndSubPbti(ind);
                    }

                    tCoords[0] = doords[indfx++] + trbnsXf;
                    tCoords[1] = doords[indfx++] + trbnsYf;

                    /* Cifdking SEG_MOVETO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Skipping nfxt pbti sfgmfnt in
                     * dbsf of invblid dbtb.
                     */

                    if (tCoords[0] < UPPER_BND &&
                        tCoords[0] > LOWER_BND &&
                        tCoords[1] < UPPER_BND &&
                        tCoords[1] > LOWER_BND)
                    {
                        subpbtiStbrtfd = JNI_TRUE;
                        skip = JNI_FALSE;
                        dlosfCoord[0] = tCoords[0];
                        dlosfCoord[1] = tCoords[1];
                    } flsf {
                        skip = JNI_TRUE;
                    }
                } flsf {
                    rfturn JNI_FALSE;
                }
                brfbk;
            dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_LINETO:
                if (indfx + 2 <= mbxCoords) {
                    lbstX = tCoords[2] = doords[indfx++] + trbnsXf;
                    lbstY = tCoords[3] = doords[indfx++] + trbnsYf;

                    /* Cifdking SEG_LINETO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf  of invblid dbtb. If sfgmfnt is skippfd its
                     * fndpoint (if vblid) is usfd to bfgin nfw subpbti.
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = dlosfCoord[0] = lbstX;
                            tCoords[1] = dlosfCoord[1] = lbstY;
                            subpbtiStbrtfd = JNI_TRUE;
                            skip = JNI_FALSE;
                        } flsf {
                            ProdfssLinf(ind, tCoords, tCoords + 2,
                                        pixflInfo);
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                } flsf {
                    rfturn JNI_FALSE;
                }
                brfbk;
            dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_QUADTO:
                if (indfx + 4 <= mbxCoords) {
                    tCoords[2] = doords[indfx++] + trbnsXf;
                    tCoords[3] = doords[indfx++] + trbnsYf;
                    lbstX = tCoords[4] = doords[indfx++] + trbnsXf;
                    lbstY = tCoords[5] = doords[indfx++] + trbnsYf;

                    /* Cifdking SEG_QUADTO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf  of invblid fndpoints's dbtb.  Equivblfnt to
                     * tif SEG_LINETO if fndpoint doordinbtfs brf vblid but
                     * tifrf brf invblid dbtb bmong otifr doordinbtfs
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = dlosfCoord[0] = lbstX;
                            tCoords[1] = dlosfCoord[1] = lbstY;
                            subpbtiStbrtfd = JNI_TRUE;
                            skip = JNI_FALSE;
                        } flsf {
                            if (tCoords[2] < UPPER_BND &&
                                tCoords[2] > LOWER_BND &&
                                tCoords[3] < UPPER_BND &&
                                tCoords[3] > LOWER_BND)
                            {
                                ProdfssQubd(ind, tCoords, pixflInfo);
                            } flsf {
                                ProdfssLinf(ind, tCoords,
                                            tCoords + 4, pixflInfo);
                            }
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                } flsf {
                    rfturn JNI_FALSE;
                }
                brfbk;
            dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CUBICTO:
                    if (indfx + 6 <= mbxCoords) {
                    tCoords[2] = doords[indfx++] + trbnsXf;
                    tCoords[3] = doords[indfx++] + trbnsYf;
                    tCoords[4] = doords[indfx++] + trbnsXf;
                    tCoords[5] = doords[indfx++] + trbnsYf;
                    lbstX = tCoords[6] = doords[indfx++] + trbnsXf;
                    lbstY = tCoords[7] = doords[indfx++] + trbnsYf;

                    /* Cifdking SEG_CUBICTO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf  of invblid fndpoints's dbtb.  Equivblfnt to
                     * tif SEG_LINETO if fndpoint doordinbtfs brf vblid but
                     * tifrf brf invblid dbtb bmong otifr doordinbtfs
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = dlosfCoord[0] = tCoords[6];
                            tCoords[1] = dlosfCoord[1] = tCoords[7];
                            subpbtiStbrtfd = JNI_TRUE;
                            skip = JNI_FALSE;
                        } flsf {
                            if (tCoords[2] < UPPER_BND &&
                                tCoords[2] > LOWER_BND &&
                                tCoords[3] < UPPER_BND &&
                                tCoords[3] > LOWER_BND &&
                                tCoords[4] < UPPER_BND &&
                                tCoords[4] > LOWER_BND &&
                                tCoords[5] < UPPER_BND &&
                                tCoords[5] > LOWER_BND)
                            {
                                ProdfssCubid(ind, tCoords, pixflInfo);
                            } flsf {
                                ProdfssLinf(ind, tCoords, tCoords + 6,
                                            pixflInfo);
                            }
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                } flsf {
                    rfturn JNI_FALSE;
                }
                brfbk;
            dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CLOSE:
                if (subpbtiStbrtfd && !skip) {
                    skip = JNI_FALSE;
                    if (tCoords[0] != dlosfCoord[0] ||
                        tCoords[1] != dlosfCoord[1])
                    {
                        ProdfssLinf(ind, tCoords, dlosfCoord, pixflInfo);
                        /* Storing lbst pbti's point for using in
                         * following sfgmfnts witiout initibl movfTo
                         */
                        tCoords[0] = dlosfCoord[0];
                        tCoords[1] = dlosfCoord[1];
                    }

                    ind->pProdfssEndSubPbti(ind);
                }

                brfbk;
        }
    }

    /* Pfrforming dlosing of tif undlosfd sfgmfnts */
    if (subpbtiStbrtfd & !skip) {
        if (ind->dlipModf == PH_MODE_FILL_CLIP) {
            if (tCoords[0] != dlosfCoord[0] ||
                tCoords[1] != dlosfCoord[1])
            {
                ProdfssLinf(ind, tCoords, dlosfCoord,
                            pixflInfo);
            }
        }
        ind->pProdfssEndSubPbti(ind);
    }

    rfturn JNI_TRUE;
}

/* TODO Add difdking of tif rfsult of tif mbllod/rfbllod fundtions to ibndlf
 * out of mfmory frror bnd don't lfbk fbrlifr bllodbtfd dbtb
 */


#dffinf ALLOC(ptr, typf, n) \
    ptr = (typf *)mbllod((n)*sizfof(typf))
#dffinf REALLOC(ptr, typf, n) \
    ptr = (typf *)rfbllod(ptr, (n)*sizfof(typf))


strudt _Edgf;

typfdff strudt _Point {
    jint x;
    jint y;
    jboolfbn lbstPoint;
    strudt _Point* prfv;
    strudt _Point* nfxt;
    strudt _Point* nfxtByY;
    jboolfbn fndSL;
    strudt _Edgf* fdgf;
} Point;


typfdff strudt _Edgf {
    jint          x;
    jint          dx;
    Point*        p;
    jint          dir;
    strudt _Edgf* prfv;
    strudt _Edgf* nfxt;
} Edgf;

/* Sizf of tif dffbult bufffr in tif FillDbtb strudturf. Tiis bufffr is
 * rfplbdfd witi ifbp bllodbtfd in dbsf of lbrgf pbtis.
 */
#dffinf DF_MAX_POINT 256

/* Following strudturf bddumulbtfs points of tif non-dontinuous flbttfnfd
 * pbti during itfrbtion tirougi tif origin pbti's sfgmfnts . Tif fnd
 * of tif fbdi subpbti is mbrkfd bs lbstPoint flbg sft bt tif lbst point
 */

typfdff strudt {
    Point   *plgPnts;
    Point   dfPlgPnts[DF_MAX_POINT];
    jint    plgSizf;
    jint    plgMbx;
    jint    plgYMin;
    jint    plgYMbx;
} FillDbtb;

#dffinf FD_INIT(PTR)                                                        \
    do {                                                                    \
        (PTR)->plgPnts = (PTR)->dfPlgPnts;                                  \
        (PTR)->plgSizf = 0;                                                 \
        (PTR)->plgMbx = DF_MAX_POINT;                                       \
    } wiilf(0)

#dffinf FD_ADD_POINT(PTR, X, Y, LASTPT)                                     \
    do {                                                                    \
        Point* _pnts = (PTR)->plgPnts;                                      \
        jint _sizf = (PTR)->plgSizf;                                        \
        if (_sizf >= (PTR)->plgMbx) {                                       \
            jint nfwMbx = (PTR)->plgMbx*2;                                  \
            if ((PTR)->plgPnts == (PTR)->dfPlgPnts) {                       \
                (PTR)->plgPnts = (Point*)mbllod(nfwMbx*sizfof(Point));      \
                mfmdpy((PTR)->plgPnts, _pnts, _sizf*sizfof(Point));         \
            } flsf {                                                        \
                (PTR)->plgPnts = (Point*)rfbllod(                           \
                    _pnts, nfwMbx*sizfof(Point));                           \
            }                                                               \
            _pnts = (PTR)->plgPnts;                                         \
            (PTR)->plgMbx = nfwMbx;                                         \
        }                                                                   \
        _pnts += _sizf;                                                     \
        _pnts->x = X;                                                       \
        _pnts->y = Y;                                                       \
        _pnts->lbstPoint = LASTPT;                                          \
        if (_sizf) {                                                        \
            if ((PTR)->plgYMin > Y) (PTR)->plgYMin = Y;                     \
            if ((PTR)->plgYMbx < Y) (PTR)->plgYMbx = Y;                     \
        } flsf {                                                            \
            (PTR)->plgYMin = Y;                                             \
            (PTR)->plgYMbx = Y;                                             \
        }                                                                   \
        (PTR)->plgSizf = _sizf + 1;                                         \
    } wiilf(0)


#dffinf FD_FREE_POINTS(PTR)                                                 \
    do {                                                                    \
        if ((PTR)->plgPnts != (PTR)->dfPlgPnts) {                           \
            frff((PTR)->plgPnts);                                           \
        }                                                                   \
    } wiilf(0)

#dffinf FD_IS_EMPTY(PTR) (!((PTR)->plgSizf))

#dffinf FD_IS_ENDED(PTR) ((PTR)->plgPnts[(PTR)->plgSizf - 1].lbstPoint)

#dffinf FD_SET_ENDED(PTR)                                                   \
    do {                                                                    \
        (PTR)->plgPnts[(PTR)->plgSizf - 1].lbstPoint = JNI_TRUE;            \
    } wiilf(0)

#dffinf PFD(HND) ((FillDbtb*)(HND)->pDbtb)

/* Bubblf sorting in tif bsdfnding ordfr of tif linkfd list. Tiis
 * implfmfntbtion stops prodfssing tif list if tifrf wfrf no dibngfs during tif
 * prfvious pbss.
 *
 * LIST - ptr to tif ptr to tif first flfmfnt of tif list
 * ETYPE - typf of tif flfmfnt in tif list
 * NEXT - bddfssor to tif nfxt fifld in tif list flfmfnt
 * GET_LKEY - bddfssor to tif kfy of tif list flfmfnt
 */
#dffinf LBUBBLE_SORT(LIST, ETYPE, NEXT, GET_LKEY)                           \
    do {                                                                    \
        ETYPE *p, *q, *r, *s = NULL, *tfmp ;                                \
        jint wbsSwbp = 1;                                                   \
        /* r prfdfdfs p bnd s points to tif nodf up to wiidi dompbrisons    \
         * brf to bf mbdf */                                                \
        wiilf ( s != NEXT(*LIST) && wbsSwbp) {                              \
            r = p = *LIST;                                                  \
            q = NEXT(p);                                                    \
            wbsSwbp = 0;                                                    \
            wiilf ( p != s ) {                                              \
                if (GET_LKEY(p) >= GET_LKEY(q)) {                           \
                    wbsSwbp = 1;                                            \
                    if ( p == *LIST ) {                                     \
                        tfmp = NEXT(q);                                     \
                        NEXT(q) = p ;                                       \
                        NEXT(p) = tfmp ;                                    \
                        *LIST = q ;                                         \
                        r = q ;                                             \
                    } flsf {                                                \
                        tfmp = NEXT(q);                                     \
                        NEXT(q) = p ;                                       \
                        NEXT(p) = tfmp ;                                    \
                        NEXT(r) = q ;                                       \
                        r = q ;                                             \
                    }                                                       \
                } flsf {                                                    \
                    r = p ;                                                 \
                    p = NEXT(p);                                            \
                }                                                           \
                q = NEXT(p);                                                \
                if ( q == s ) s = p ;                                       \
            }                                                               \
        }                                                                   \
    } wiilf(0);

/* Addfssors for tif Edgf strudturf to work witi LBUBBLE_SORT */
#dffinf GET_ACTIVE_KEY(b) (b->x)
#dffinf GET_ACTIVE_NEXT(b) ((b)->nfxt)

/* TODO: Implfmfnt stbdk/ifbp bllodbtion tfdiniquf for bdtivf fdgfs
 */
#dffinf DELETE_ACTIVE(ifbd,pnt)                                     \
do {                                                                \
    Edgf *prfvp = pnt->prfv;                                        \
    Edgf *nfxtp = pnt->nfxt;                                        \
    if (prfvp) {                                                    \
        prfvp->nfxt = nfxtp;                                        \
    } flsf {                                                        \
        ifbd = nfxtp;                                               \
    }                                                               \
    if (nfxtp) {                                                    \
        nfxtp->prfv = prfvp;                                        \
    }                                                               \
} wiilf(0);

#dffinf INSERT_ACTIVE(ifbd,pnt,dy)                                  \
do {                                                                \
    Point *np = pnt->nfxt;                                          \
    Edgf *nf = bdtivf + nbdt;                                       \
    if (pnt->y == np->y) {                                          \
        /* Skipping iorizontbl sfgmfnts */                          \
        brfbk;                                                      \
    } flsf {                                                        \
        jint dX = np->x - pnt->x;                                   \
        jint dY = np->y - pnt->y;                                   \
        jint dy;                                                    \
        if (pnt->y < np->y) {                                       \
            nf->dir = -1;                                           \
            nf->p = pnt;                                            \
            nf->x = pnt->x;                                         \
            dy = dy - pnt->y;                                       \
        } flsf { /* pnt->y > np->y */                               \
            nf->dir = 1;                                            \
            nf->p = np;                                             \
            nf->x = np->x;                                          \
            dy = dy - np->y;                                        \
        }                                                           \
                                                                    \
        /* Wf nffd to worry only bbout dX bfdbusf dY is in        */\
        /* dfnominbtor bnd bbs(dy) < MDP_MULT (dy is b first      */\
        /* sdbnlinf of tif sdbn donvfrtfd sfgmfnt bnd wf subtrbdt */\
        /* y doordinbtf of tif nfbrfst sfgmfnt's fnd from it to   */\
        /* obtbin dy)                                             */\
        if (ABS32(dX) > CALC_BND) {                                 \
            nf->dx = (jint)((((jdoublf)dX)*MDP_MULT)/dY);           \
            nf->x += (jint)((((jdoublf)dX)*dy)/dY);                 \
        } flsf {                                                    \
            nf->dx = ((dX)<<MDP_PREC)/dY;                           \
            nf->x += (dX*dy)/dY;                                    \
        }                                                           \
    }                                                               \
    nf->nfxt = ifbd;                                                \
    nf->prfv = NULL;                                                \
    if (ifbd) {                                                     \
        ifbd->prfv = nf;                                            \
    }                                                               \
    ifbd = bdtivf + nbdt;                                           \
    pnt->fdgf = ifbd;                                               \
    nbdt++;                                                         \
} wiilf(0);

void FillPolygon(ProdfssHbndlfr* ind,
                 jint fillRulf) {
    jint k, y, xl, xr;
    jint drbwing;
    Edgf* bdtivfList, *bdtivf;
    Edgf* durEdgf, *prfvEdgf;
    jint nbdt;
    jint n;
    Point* pt, *durpt, *fpt;
    Point** yHbsi;
    Point** durHbsi;
    jint rigitBnd = ind->dind->xMbx - 1;
    FillDbtb* pfd = (FillDbtb*)(ind->pDbtb);
    jint yMin = pfd->plgYMin;
    jint yMbx = pfd->plgYMbx;
    jint ibsiSizf = ((yMbx - yMin)>>MDP_PREC) + 4;

    /* Bfdbusf of support of tif KEY_STROKE_CONTROL iint wf brf pfrforming
     * siift of tif doordinbtfs bt tif iigifr lfvfl
     */
    jint ibsiOffsft = ((yMin - 1) & MDP_W_MASK);

// TODO drfbting lists using fbkf first flfmfnt to bvoid spfdibl dbsing of
// tif first flfmfnt in tif list (wiidi otifrwisf siould bf pfrformfd in fbdi
// list opfrbtion)

    /* Winding dountfr */
    jint dountfr;

    /* Cbldulbting mbsk to bf bpplifd to tif winding dountfr */
    jint dountfrMbsk =
        (fillRulf == jbvb_bwt_gfom_PbtiItfrbtor_WIND_NON_ZERO)? -1:1;
    pt = pfd->plgPnts;
    n = pfd->plgSizf;

    if (n <=1) rfturn;

    ALLOC(yHbsi, Point*, ibsiSizf);
    for (k = 0; k < ibsiSizf; k++) {
        yHbsi[k] = NULL;
    }

    ALLOC(bdtivf, Edgf, n);

    /* Crfbting doublf linkfd list (prfv, nfxt links) dfsdribing pbti ordfr bnd
     * ibsi tbblf witi points wiidi fbll bftwffn sdbnlinfs. nfxtByY link is
     * usfd for tif points wiidi brf bftwffn sbmf sdbnlinfs. Sdbnlinfs brf
     * pbssfd tirougi tif dfntfrs of tif pixfls.
     */
    durpt = pt;
    durpt->prfv = NULL;
    fpt = pt + n - 1;
    for (durpt = pt; durpt != fpt; durpt++) {
        Point* nfxtpt = durpt + 1;
        durHbsi =  yHbsi + ((durpt->y - ibsiOffsft - 1) >> MDP_PREC);
        durpt->nfxtByY = *durHbsi;
        *durHbsi = durpt;
        durpt->nfxt = nfxtpt;
        nfxtpt->prfv = durpt;
        durpt->fdgf = NULL;
    }

    durHbsi =  yHbsi + ((fpt->y - ibsiOffsft - 1) >> MDP_PREC);
    fpt->nfxtByY = *durHbsi;
    *durHbsi = fpt;
    fpt->nfxt = NULL;
    fpt->fdgf = NULL;
    nbdt = 0;

    bdtivfList = NULL;
    for (y=ibsiOffsft + MDP_MULT,k = 0;
         y<=yMbx && k < ibsiSizf; y += MDP_MULT, k++)
    {
        for(pt = yHbsi[k];pt; pt=pt->nfxtByY) {
            /* pt->y siould bf insidf ibsifd intfrvbl
             * bssfrt(y-MDP_MULT <= pt->y && pt->y < y);
             */
            if (pt->prfv && !pt->prfv->lbstPoint) {
                if (pt->prfv->fdgf && pt->prfv->y <= y) {
                    DELETE_ACTIVE(bdtivfList, pt->prfv->fdgf);
                    pt->prfv->fdgf = NULL;
                } flsf  if (pt->prfv->y > y) {
                    INSERT_ACTIVE(bdtivfList, pt->prfv, y);
                }
            }

            if (!pt->lbstPoint && pt->nfxt) {
                if (pt->fdgf && pt->nfxt->y <= y) {
                    DELETE_ACTIVE(bdtivfList, pt->fdgf);
                    pt->fdgf = NULL;
                } flsf if (pt->nfxt->y > y) {
                    INSERT_ACTIVE(bdtivfList, pt, y);
                }
            }
        }

        if (!bdtivfList) dontinuf;

        /* Wf dould not usf O(N) Rbdix sort ifrf bfdbusf in most dbsfs list of
         * fdgfs blmost sortfd. So, bubblf sort (O(N^2))is working mudi
         * bfttfr. Notf, in dbsf of brrby of fdgfs Sifll sort is morf
         * fffidifnt.
         */
        LBUBBLE_SORT((&bdtivfList), Edgf, GET_ACTIVE_NEXT, GET_ACTIVE_KEY);

        /* Corrfdtion of tif bbdk links in tif doublf linkfd fdgf list */
        durEdgf=bdtivfList;
        prfvEdgf = NULL;
        wiilf (durEdgf) {
            durEdgf->prfv = prfvEdgf;
            prfvEdgf = durEdgf;
            durEdgf = durEdgf->nfxt;
        }

        xl = xr = ind->dind->xMin;
        durEdgf = bdtivfList;
        dountfr = 0;
        drbwing = 0;
        for(;durEdgf; durEdgf = durEdgf->nfxt) {
            dountfr += durEdgf->dir;
            if ((dountfr & dountfrMbsk) && !drbwing) {
                xl = (durEdgf->x + MDP_MULT - 1)>>MDP_PREC;
                drbwing = 1;
            }

            if (!(dountfr & dountfrMbsk) && drbwing) {
                xr = (durEdgf->x - 1)>>MDP_PREC;
                if (xl <= xr) {
                    ind->dind->pDrbwSdbnlinf(ind->dind, xl, xr, y >> MDP_PREC);
                }
                drbwing = 0;
            }

            durEdgf->x += durEdgf->dx;
        }

        /* Pfrforming drbwing till tif rigit boundbry (for dorrfdt rfndfring
         * sibpfs dlippfd bt tif rigit sidf)
         */
        if (drbwing && xl <= rigitBnd) {
            ind->dind->pDrbwSdbnlinf(ind->dind, xl, rigitBnd, y >> MDP_PREC);
        }
    }
    frff(bdtivf);
    frff(yHbsi);
}



void  StorfFixfdLinf(ProdfssHbndlfr* ind,jint x1,jint y1,jint x2,jint y2,
                     jint* pixflInfo,jboolfbn difdkBounds,
                     jboolfbn fndSubPbti)  {
    FillDbtb* pfd;
    jint outXMin, outXMbx, outYMin, outYMbx;
    jint x3, y3, rfs;

    /* Tifrf is no nffd to round linf doordinbtfs to tif forwbrd difffrfnding
     * prfdision bnymorf. Sudi b rounding wbs usfd for prfvfnting tif durvf go
     * out tif fndpoint (tiis somftimfs dofs not iflp). Tif problfm wbs fixfd
     * in tif forwbrd difffrfnding loops.
     */

    if (difdkBounds) {
        jboolfbn lbstClippfd = JNI_FALSE;

        /* Tiis fundtion is usfd only for filling sibpfs, so tifrf is no
         * difdk for tif typf of dlipping
         */
        outXMin = (jint)(ind->dind->xMinf * MDP_MULT);
        outXMbx = (jint)(ind->dind->xMbxf * MDP_MULT);
        outYMin = (jint)(ind->dind->yMinf * MDP_MULT);
        outYMbx = (jint)(ind->dind->yMbxf * MDP_MULT);

        TESTANDCLIP(outYMin, outYMbx, y1, x1, y2, x2, jint, rfs);
        if (rfs == CRES_INVISIBLE) rfturn;
        TESTANDCLIP(outYMin, outYMbx, y2, x2, y1, x1, jint, rfs);
        if (rfs == CRES_INVISIBLE) rfturn;
        lbstClippfd = IS_CLIPPED(rfs);

        /* Clbmping stbrting from first vfrtfx of tif tif prodfssfd sfgmfnt */
        CLIPCLAMP(outXMin, outXMbx, x1, y1, x2, y2, x3, y3, jint, rfs);

        /* Clbmping only by lfft boundbry */
        if (rfs == CRES_MIN_CLIPPED) {
            StorfFixfdLinf(ind, x3, y3, x1, y1, pixflInfo,
                           JNI_FALSE, lbstClippfd);

        } flsf if (rfs == CRES_INVISIBLE) {
            rfturn;
        }

        /* Clbmping stbrting from lbst vfrtfx of tif tif prodfssfd sfgmfnt */
        CLIPCLAMP(outXMin, outXMbx, x2, y2, x1, y1, x3, y3, jint, rfs);

        /* Cifdking if tifrf wbs b dlip by rigit boundbry */
        lbstClippfd = lbstClippfd || (rfs == CRES_MAX_CLIPPED);

        StorfFixfdLinf(ind, x1, y1, x2, y2, pixflInfo,
                         JNI_FALSE, lbstClippfd);

        /* Clbmping only by lfft boundbry */
        if (rfs == CRES_MIN_CLIPPED) {
            StorfFixfdLinf(ind, x2, y2, x3, y3, pixflInfo,
                           JNI_FALSE, lbstClippfd);
        }

        rfturn;
    }
    pfd = (FillDbtb*)(ind->pDbtb);

    /* Adding first point of tif linf only in dbsf of fmpty or just finisifd
     * pbti
     */
    if (FD_IS_EMPTY(pfd) || FD_IS_ENDED(pfd)) {
        FD_ADD_POINT(pfd, x1, y1, JNI_FALSE);
    }

    FD_ADD_POINT(pfd, x2, y2, JNI_FALSE);

    if (fndSubPbti) {
        FD_SET_ENDED(pfd);
    }
}


stbtid void fndSubPbti(ProdfssHbndlfr* ind) {
    FillDbtb* pfd = (FillDbtb*)(ind->pDbtb);
    if (!FD_IS_EMPTY(pfd)) {
        FD_SET_ENDED(pfd);
    }
}

stbtid void stubEndSubPbti(ProdfssHbndlfr* ind) {
}

jboolfbn doFillPbti(DrbwHbndlfr* dind,
                    jint trbnsX, jint trbnsY,
                    jflobt* doords, jint mbxCoords,
                    jbytf* typfs, jint numTypfs,
                    PHStrokf strokf, jint fillRulf)
{
    jint rfs;

    FillDbtb fillDbtb;

    ProdfssHbndlfr ind =
    {
        &StorfFixfdLinf,
        &fndSubPbti,
        NULL,
        PH_STROKE_DEFAULT,
        PH_MODE_FILL_CLIP,
        NULL
    };

    /* Initiblizbtion of tif following fiflds in tif dfdlbrbtion of tif ind
     * bbovf dbusfs wbrnings on sun studio dompilfr witi  -xd99=%nonf option
     * bpplifd (tiis option mfbns domplibndf witi C90 stbndbrd instfbd of C99)
     */
    ind.dind = dind;
    ind.pDbtb = &fillDbtb;
    ind.strokf = strokf;

    FD_INIT(&fillDbtb);
    rfs = ProdfssPbti(&ind, (jflobt)trbnsX, (jflobt)trbnsY,
                      doords, mbxCoords, typfs, numTypfs);
    if (!rfs) {
        FD_FREE_POINTS(&fillDbtb);
        rfturn JNI_FALSE;
    }
    FillPolygon(&ind, fillRulf);
    FD_FREE_POINTS(&fillDbtb);
    rfturn JNI_TRUE;
}

jboolfbn doDrbwPbti(DrbwHbndlfr* dind,
                    void (*pProdfssEndSubPbti)(ProdfssHbndlfr*),
                    jint trbnsX, jint trbnsY,
                    jflobt* doords, jint mbxCoords,
                    jbytf* typfs, jint numTypfs, PHStrokf strokf)
{
    ProdfssHbndlfr ind =
    {
        &ProdfssFixfdLinf,
        NULL,
        NULL,
        PH_STROKE_DEFAULT,
        PH_MODE_DRAW_CLIP,
        NULL
    };

    /* Initiblizbtion of tif following fiflds in tif dfdlbrbtion of tif ind
     * bbovf dbusfs wbrnings on sun studio dompilfr witi  -xd99=%nonf option
     * bpplifd (tiis option mfbns domplibndf witi C90 stbndbrd instfbd of C99)
     */
    ind.dind = dind;
    ind.strokf = strokf;

    ind.pProdfssEndSubPbti = (pProdfssEndSubPbti == NULL)?
        stubEndSubPbti : pProdfssEndSubPbti;
    rfturn ProdfssPbti(&ind, (jflobt)trbnsX, (jflobt)trbnsY, doords, mbxCoords,
                       typfs, numTypfs);
}
