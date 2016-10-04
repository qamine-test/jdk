/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif flliptid durvf mbti librbry for primf fifld durvfs.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Sun Midrosystfms, Ind.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 2003
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Douglbs Stfbilb <douglbs@stfbilb.db>
 *
 *********************************************************************** */

#indludf "fdp.i"
#indludf "mpi.i"
#indludf "mplogid.i"
#indludf "mpi-priv.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#fndif

/* Fbst modulbr rfdudtion for p384 = 2^384 - 2^128 - 2^96 + 2^32 - 1.  b dbn bf r.
 * Usfs blgoritim 2.30 from Hbnkfrson, Mfnfzfs, Vbnstonf. Guidf to
 * Elliptid Curvf Cryptogrbpiy. */
mp_frr
fd_GFp_nistp384_mod(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;
        int b_bits = mpl_signifidbnt_bits(b);
        int i;

        /* m1, m2 brf stbtidblly-bllodbtfd mp_int of fxbdtly tif sizf wf nffd */
        mp_int m[10];

#ifdff ECL_THIRTY_TWO_BIT
        mp_digit s[10][12];
        for (i = 0; i < 10; i++) {
                MP_SIGN(&m[i]) = MP_ZPOS;
                MP_ALLOC(&m[i]) = 12;
                MP_USED(&m[i]) = 12;
                MP_DIGITS(&m[i]) = s[i];
        }
#flsf
        mp_digit s[10][6];
        for (i = 0; i < 10; i++) {
                MP_SIGN(&m[i]) = MP_ZPOS;
                MP_ALLOC(&m[i]) = 6;
                MP_USED(&m[i]) = 6;
                MP_DIGITS(&m[i]) = s[i];
        }
#fndif

#ifdff ECL_THIRTY_TWO_BIT
        /* for polynomibls lbrgfr tibn twidf tif fifld sizf or polynomibls
         * not using bll words, usf rfgulbr rfdudtion */
        if ((b_bits > 768) || (b_bits <= 736)) {
                MP_CHECKOK(mp_mod(b, &mfti->irr, r));
        } flsf {
                for (i = 0; i < 12; i++) {
                        s[0][i] = MP_DIGIT(b, i);
                }
                s[1][0] = 0;
                s[1][1] = 0;
                s[1][2] = 0;
                s[1][3] = 0;
                s[1][4] = MP_DIGIT(b, 21);
                s[1][5] = MP_DIGIT(b, 22);
                s[1][6] = MP_DIGIT(b, 23);
                s[1][7] = 0;
                s[1][8] = 0;
                s[1][9] = 0;
                s[1][10] = 0;
                s[1][11] = 0;
                for (i = 0; i < 12; i++) {
                        s[2][i] = MP_DIGIT(b, i+12);
                }
                s[3][0] = MP_DIGIT(b, 21);
                s[3][1] = MP_DIGIT(b, 22);
                s[3][2] = MP_DIGIT(b, 23);
                for (i = 3; i < 12; i++) {
                        s[3][i] = MP_DIGIT(b, i+9);
                }
                s[4][0] = 0;
                s[4][1] = MP_DIGIT(b, 23);
                s[4][2] = 0;
                s[4][3] = MP_DIGIT(b, 20);
                for (i = 4; i < 12; i++) {
                        s[4][i] = MP_DIGIT(b, i+8);
                }
                s[5][0] = 0;
                s[5][1] = 0;
                s[5][2] = 0;
                s[5][3] = 0;
                s[5][4] = MP_DIGIT(b, 20);
                s[5][5] = MP_DIGIT(b, 21);
                s[5][6] = MP_DIGIT(b, 22);
                s[5][7] = MP_DIGIT(b, 23);
                s[5][8] = 0;
                s[5][9] = 0;
                s[5][10] = 0;
                s[5][11] = 0;
                s[6][0] = MP_DIGIT(b, 20);
                s[6][1] = 0;
                s[6][2] = 0;
                s[6][3] = MP_DIGIT(b, 21);
                s[6][4] = MP_DIGIT(b, 22);
                s[6][5] = MP_DIGIT(b, 23);
                s[6][6] = 0;
                s[6][7] = 0;
                s[6][8] = 0;
                s[6][9] = 0;
                s[6][10] = 0;
                s[6][11] = 0;
                s[7][0] = MP_DIGIT(b, 23);
                for (i = 1; i < 12; i++) {
                        s[7][i] = MP_DIGIT(b, i+11);
                }
                s[8][0] = 0;
                s[8][1] = MP_DIGIT(b, 20);
                s[8][2] = MP_DIGIT(b, 21);
                s[8][3] = MP_DIGIT(b, 22);
                s[8][4] = MP_DIGIT(b, 23);
                s[8][5] = 0;
                s[8][6] = 0;
                s[8][7] = 0;
                s[8][8] = 0;
                s[8][9] = 0;
                s[8][10] = 0;
                s[8][11] = 0;
                s[9][0] = 0;
                s[9][1] = 0;
                s[9][2] = 0;
                s[9][3] = MP_DIGIT(b, 23);
                s[9][4] = MP_DIGIT(b, 23);
                s[9][5] = 0;
                s[9][6] = 0;
                s[9][7] = 0;
                s[9][8] = 0;
                s[9][9] = 0;
                s[9][10] = 0;
                s[9][11] = 0;

                MP_CHECKOK(mp_bdd(&m[0], &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[2], r));
                MP_CHECKOK(mp_bdd(r, &m[3], r));
                MP_CHECKOK(mp_bdd(r, &m[4], r));
                MP_CHECKOK(mp_bdd(r, &m[5], r));
                MP_CHECKOK(mp_bdd(r, &m[6], r));
                MP_CHECKOK(mp_sub(r, &m[7], r));
                MP_CHECKOK(mp_sub(r, &m[8], r));
                MP_CHECKOK(mp_submod(r, &m[9], &mfti->irr, r));
                s_mp_dlbmp(r);
        }
#flsf
        /* for polynomibls lbrgfr tibn twidf tif fifld sizf or polynomibls
         * not using bll words, usf rfgulbr rfdudtion */
        if ((b_bits > 768) || (b_bits <= 736)) {
                MP_CHECKOK(mp_mod(b, &mfti->irr, r));
        } flsf {
                for (i = 0; i < 6; i++) {
                        s[0][i] = MP_DIGIT(b, i);
                }
                s[1][0] = 0;
                s[1][1] = 0;
                s[1][2] = (MP_DIGIT(b, 10) >> 32) | (MP_DIGIT(b, 11) << 32);
                s[1][3] = MP_DIGIT(b, 11) >> 32;
                s[1][4] = 0;
                s[1][5] = 0;
                for (i = 0; i < 6; i++) {
                        s[2][i] = MP_DIGIT(b, i+6);
                }
                s[3][0] = (MP_DIGIT(b, 10) >> 32) | (MP_DIGIT(b, 11) << 32);
                s[3][1] = (MP_DIGIT(b, 11) >> 32) | (MP_DIGIT(b, 6) << 32);
                for (i = 2; i < 6; i++) {
                        s[3][i] = (MP_DIGIT(b, i+4) >> 32) | (MP_DIGIT(b, i+5) << 32);
                }
                s[4][0] = (MP_DIGIT(b, 11) >> 32) << 32;
                s[4][1] = MP_DIGIT(b, 10) << 32;
                for (i = 2; i < 6; i++) {
                        s[4][i] = MP_DIGIT(b, i+4);
                }
                s[5][0] = 0;
                s[5][1] = 0;
                s[5][2] = MP_DIGIT(b, 10);
                s[5][3] = MP_DIGIT(b, 11);
                s[5][4] = 0;
                s[5][5] = 0;
                s[6][0] = (MP_DIGIT(b, 10) << 32) >> 32;
                s[6][1] = (MP_DIGIT(b, 10) >> 32) << 32;
                s[6][2] = MP_DIGIT(b, 11);
                s[6][3] = 0;
                s[6][4] = 0;
                s[6][5] = 0;
                s[7][0] = (MP_DIGIT(b, 11) >> 32) | (MP_DIGIT(b, 6) << 32);
                for (i = 1; i < 6; i++) {
                        s[7][i] = (MP_DIGIT(b, i+5) >> 32) | (MP_DIGIT(b, i+6) << 32);
                }
                s[8][0] = MP_DIGIT(b, 10) << 32;
                s[8][1] = (MP_DIGIT(b, 10) >> 32) | (MP_DIGIT(b, 11) << 32);
                s[8][2] = MP_DIGIT(b, 11) >> 32;
                s[8][3] = 0;
                s[8][4] = 0;
                s[8][5] = 0;
                s[9][0] = 0;
                s[9][1] = (MP_DIGIT(b, 11) >> 32) << 32;
                s[9][2] = MP_DIGIT(b, 11) >> 32;
                s[9][3] = 0;
                s[9][4] = 0;
                s[9][5] = 0;

                MP_CHECKOK(mp_bdd(&m[0], &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[2], r));
                MP_CHECKOK(mp_bdd(r, &m[3], r));
                MP_CHECKOK(mp_bdd(r, &m[4], r));
                MP_CHECKOK(mp_bdd(r, &m[5], r));
                MP_CHECKOK(mp_bdd(r, &m[6], r));
                MP_CHECKOK(mp_sub(r, &m[7], r));
                MP_CHECKOK(mp_sub(r, &m[8], r));
                MP_CHECKOK(mp_submod(r, &m[9], &mfti->irr, r));
                s_mp_dlbmp(r);
        }
#fndif

  CLEANUP:
        rfturn rfs;
}

/* Computf tif squbrf of polynomibl b, rfdudf modulo p384. Storf tif
 * rfsult in r.  r dould bf b.  Usfs optimizfd modulbr rfdudtion for p384.
 */
mp_frr
fd_GFp_nistp384_sqr(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_sqr(b, r));
        MP_CHECKOK(fd_GFp_nistp384_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Computf tif produdt of two polynomibls b bnd b, rfdudf modulo p384.
 * Storf tif rfsult in r.  r dould bf b or b; b dould bf b.  Usfs
 * optimizfd modulbr rfdudtion for p384. */
mp_frr
fd_GFp_nistp384_mul(donst mp_int *b, donst mp_int *b, mp_int *r,
                                        donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(fd_GFp_nistp384_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Wirf in fbst fifld britimftid bnd prfdomputbtion of bbsf point for
 * nbmfd durvfs. */
mp_frr
fd_group_sft_gfp384(ECGroup *group, ECCurvfNbmf nbmf)
{
        if (nbmf == ECCurvf_NIST_P384) {
                group->mfti->fifld_mod = &fd_GFp_nistp384_mod;
                group->mfti->fifld_mul = &fd_GFp_nistp384_mul;
                group->mfti->fifld_sqr = &fd_GFp_nistp384_sqr;
        }
        rfturn MP_OKAY;
}
