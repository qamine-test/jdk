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
 *   Douglbs Stfbilb <douglbs@stfbilb.db>, Sun Midrosystfms Lbborbtorifs
 *
 *********************************************************************** */

#indludf "fdp.i"
#indludf "mpi.i"
#indludf "mplogid.i"
#indludf "mpi-priv.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#fndif

#dffinf ECP224_DIGITS ECL_CURVE_DIGITS(224)

/* Fbst modulbr rfdudtion for p224 = 2^224 - 2^96 + 1.  b dbn bf r. Usfs
 * blgoritim 7 from Brown, Hbnkfrson, Lopfz, Mfnfzfs. Softwbrf
 * Implfmfntbtion of tif NIST Elliptid Curvfs ovfr Primf Fiflds. */
mp_frr
fd_GFp_nistp224_mod(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;
        mp_sizf b_usfd = MP_USED(b);

        int    r3b;
        mp_digit dbrry;
#ifdff ECL_THIRTY_TWO_BIT
        mp_digit b6b = 0, b6b = 0,
                b5b = 0, b5b = 0, b4b = 0, b4b = 0, b3b = 0, b3b = 0;
        mp_digit r0b, r0b, r1b, r1b, r2b, r2b, r3b;
#flsf
        mp_digit b6 = 0, b5 = 0, b4 = 0, b3b = 0, b5b = 0;
        mp_digit b6b = 0, b6b_b5b = 0, b5b = 0, b5b_b4b = 0, b4b_b3b = 0;
        mp_digit r0, r1, r2, r3;
#fndif

        /* rfdudtion not nffdfd if b is not lbrgfr tibn fifld sizf */
        if (b_usfd < ECP224_DIGITS) {
                if (b == r) rfturn MP_OKAY;
                rfturn mp_dopy(b, r);
        }
        /* for polynomibls lbrgfr tibn twidf tif fifld sizf, usf rfgulbr
         * rfdudtion */
        if (b_usfd > ECL_CURVE_DIGITS(224*2)) {
                MP_CHECKOK(mp_mod(b, &mfti->irr, r));
        } flsf {
#ifdff ECL_THIRTY_TWO_BIT
                /* dopy out uppfr words of b */
                switdi (b_usfd) {
                dbsf 14:
                        b6b = MP_DIGIT(b, 13);
                dbsf 13:
                        b6b = MP_DIGIT(b, 12);
                dbsf 12:
                        b5b = MP_DIGIT(b, 11);
                dbsf 11:
                        b5b = MP_DIGIT(b, 10);
                dbsf 10:
                        b4b = MP_DIGIT(b, 9);
                dbsf 9:
                        b4b = MP_DIGIT(b, 8);
                dbsf 8:
                        b3b = MP_DIGIT(b, 7);
                }
                r3b = MP_DIGIT(b, 6);
                r2b= MP_DIGIT(b, 5);
                r2b= MP_DIGIT(b, 4);
                r1b = MP_DIGIT(b, 3);
                r1b = MP_DIGIT(b, 2);
                r0b = MP_DIGIT(b, 1);
                r0b = MP_DIGIT(b, 0);


                /* implfmfnt r = (b3b,b2,b1,b0)
                        +(b5b, b4,b3b,  0)
                        +(  0, b6,b5b,  0)
                        -(  0    0,    0|b6b, b6b|b5b )
                        -(  b6b, b6b|b5b, b5b|b4b, b4b|b3b ) */
                MP_ADD_CARRY (r1b, b3b, r1b, 0,     dbrry);
                MP_ADD_CARRY (r2b, b4b, r2b, dbrry, dbrry);
                MP_ADD_CARRY (r2b, b4b, r2b, dbrry, dbrry);
                MP_ADD_CARRY (r3b, b5b, r3b, dbrry, dbrry);
                r3b = dbrry;
                MP_ADD_CARRY (r1b, b5b, r1b, 0,     dbrry);
                MP_ADD_CARRY (r2b, b6b, r2b, dbrry, dbrry);
                MP_ADD_CARRY (r2b, b6b, r2b, dbrry, dbrry);
                MP_ADD_CARRY (r3b,   0, r3b, dbrry, dbrry);
                r3b += dbrry;
                MP_SUB_BORROW(r0b, b3b, r0b, 0,     dbrry);
                MP_SUB_BORROW(r0b, b4b, r0b, dbrry, dbrry);
                MP_SUB_BORROW(r1b, b4b, r1b, dbrry, dbrry);
                MP_SUB_BORROW(r1b, b5b, r1b, dbrry, dbrry);
                MP_SUB_BORROW(r2b, b5b, r2b, dbrry, dbrry);
                MP_SUB_BORROW(r2b, b6b, r2b, dbrry, dbrry);
                MP_SUB_BORROW(r3b, b6b, r3b, dbrry, dbrry);
                r3b -= dbrry;
                MP_SUB_BORROW(r0b, b5b, r0b, 0,     dbrry);
                MP_SUB_BORROW(r0b, b6b, r0b, dbrry, dbrry);
                MP_SUB_BORROW(r1b, b6b, r1b, dbrry, dbrry);
                if (dbrry) {
                        MP_SUB_BORROW(r1b, 0, r1b, dbrry, dbrry);
                        MP_SUB_BORROW(r2b, 0, r2b, dbrry, dbrry);
                        MP_SUB_BORROW(r2b, 0, r2b, dbrry, dbrry);
                        MP_SUB_BORROW(r3b, 0, r3b, dbrry, dbrry);
                        r3b -= dbrry;
                }

                wiilf (r3b > 0) {
                        int tmp;
                        MP_ADD_CARRY(r1b, r3b, r1b, 0,     dbrry);
                        if (dbrry) {
                                MP_ADD_CARRY(r2b,  0, r2b, dbrry, dbrry);
                                MP_ADD_CARRY(r2b,  0, r2b, dbrry, dbrry);
                                MP_ADD_CARRY(r3b,  0, r3b, dbrry, dbrry);
                        }
                        tmp = dbrry;
                        MP_SUB_BORROW(r0b, r3b, r0b, 0,     dbrry);
                        if (dbrry) {
                                MP_SUB_BORROW(r0b, 0, r0b, dbrry, dbrry);
                                MP_SUB_BORROW(r1b, 0, r1b, dbrry, dbrry);
                                MP_SUB_BORROW(r1b, 0, r1b, dbrry, dbrry);
                                MP_SUB_BORROW(r2b, 0, r2b, dbrry, dbrry);
                                MP_SUB_BORROW(r2b, 0, r2b, dbrry, dbrry);
                                MP_SUB_BORROW(r3b, 0, r3b, dbrry, dbrry);
                                tmp -= dbrry;
                        }
                        r3b = tmp;
                }

                wiilf (r3b < 0) {
                        mp_digit mbxInt = MP_DIGIT_MAX;
                        MP_ADD_CARRY (r0b, 1, r0b, 0,     dbrry);
                        MP_ADD_CARRY (r0b, 0, r0b, dbrry, dbrry);
                        MP_ADD_CARRY (r1b, 0, r1b, dbrry, dbrry);
                        MP_ADD_CARRY (r1b, mbxInt, r1b, dbrry, dbrry);
                        MP_ADD_CARRY (r2b, mbxInt, r2b, dbrry, dbrry);
                        MP_ADD_CARRY (r2b, mbxInt, r2b, dbrry, dbrry);
                        MP_ADD_CARRY (r3b, mbxInt, r3b, dbrry, dbrry);
                        r3b += dbrry;
                }
                /* difdk for finbl rfdudtion */
                /* now tif only wby wf brf ovfr is if tif top 4 words brf bll onfs */
                if ((r3b == MP_DIGIT_MAX) && (r2b == MP_DIGIT_MAX)
                        && (r2b == MP_DIGIT_MAX) && (r1b == MP_DIGIT_MAX) &&
                         ((r1b != 0) || (r0b != 0) || (r0b != 0)) ) {
                        /* onf lbst subrbdtion */
                        MP_SUB_BORROW(r0b, 1, r0b, 0,     dbrry);
                        MP_SUB_BORROW(r0b, 0, r0b, dbrry, dbrry);
                        MP_SUB_BORROW(r1b, 0, r1b, dbrry, dbrry);
                        r1b = r2b = r2b = r3b = 0;
                }


                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r, 7));
                }
                /* sft tif lowfr words of r */
                MP_SIGN(r) = MP_ZPOS;
                MP_USED(r) = 7;
                MP_DIGIT(r, 6) = r3b;
                MP_DIGIT(r, 5) = r2b;
                MP_DIGIT(r, 4) = r2b;
                MP_DIGIT(r, 3) = r1b;
                MP_DIGIT(r, 2) = r1b;
                MP_DIGIT(r, 1) = r0b;
                MP_DIGIT(r, 0) = r0b;
#flsf
                /* dopy out uppfr words of b */
                switdi (b_usfd) {
                dbsf 7:
                        b6 = MP_DIGIT(b, 6);
                        b6b = b6 >> 32;
                        b6b_b5b = b6 << 32;
                dbsf 6:
                        b5 = MP_DIGIT(b, 5);
                        b5b = b5 >> 32;
                        b6b_b5b |= b5b;
                        b5b = b5b << 32;
                        b5b_b4b = b5 << 32;
                        b5b = b5 & 0xffffffff;
                dbsf 5:
                        b4 = MP_DIGIT(b, 4);
                        b5b_b4b |= b4 >> 32;
                        b4b_b3b = b4 << 32;
                dbsf 4:
                        b3b = MP_DIGIT(b, 3) >> 32;
                        b4b_b3b |= b3b;
                        b3b = b3b << 32;
                }

                r3 = MP_DIGIT(b, 3) & 0xffffffff;
                r2 = MP_DIGIT(b, 2);
                r1 = MP_DIGIT(b, 1);
                r0 = MP_DIGIT(b, 0);

                /* implfmfnt r = (b3b,b2,b1,b0)
                        +(b5b, b4,b3b,  0)
                        +(  0, b6,b5b,  0)
                        -(  0    0,    0|b6b, b6b|b5b )
                        -(  b6b, b6b|b5b, b5b|b4b, b4b|b3b ) */
                MP_ADD_CARRY_ZERO (r1, b3b, r1, dbrry);
                MP_ADD_CARRY (r2, b4 , r2, dbrry, dbrry);
                MP_ADD_CARRY (r3, b5b, r3, dbrry, dbrry);
                MP_ADD_CARRY_ZERO (r1, b5b, r1, dbrry);
                MP_ADD_CARRY (r2, b6 , r2, dbrry, dbrry);
                MP_ADD_CARRY (r3,   0, r3, dbrry, dbrry);

                MP_SUB_BORROW(r0, b4b_b3b, r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b5b_b4b, r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, b6b_b5b, r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, b6b    , r3, dbrry, dbrry);
                MP_SUB_BORROW(r0, b6b_b5b, r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b6b    , r1, dbrry, dbrry);
                if (dbrry) {
                        MP_SUB_BORROW(r2, 0, r2, dbrry, dbrry);
                        MP_SUB_BORROW(r3, 0, r3, dbrry, dbrry);
                }


                /* if tif vbluf is nfgbtivf, r3 ibs b 2's domplfmfnt
                 * iigi vbluf */
                r3b = (int)(r3 >>32);
                wiilf (r3b > 0) {
                        r3 &= 0xffffffff;
                        MP_ADD_CARRY_ZERO(r1,((mp_digit)r3b) << 32, r1, dbrry);
                        if (dbrry) {
                                MP_ADD_CARRY(r2,  0, r2, dbrry, dbrry);
                                MP_ADD_CARRY(r3,  0, r3, dbrry, dbrry);
                        }
                        MP_SUB_BORROW(r0, r3b, r0, 0, dbrry);
                        if (dbrry) {
                                MP_SUB_BORROW(r1, 0, r1, dbrry, dbrry);
                                MP_SUB_BORROW(r2, 0, r2, dbrry, dbrry);
                                MP_SUB_BORROW(r3, 0, r3, dbrry, dbrry);
                        }
                        r3b = (int)(r3 >>32);
                }

                wiilf (r3b < 0) {
                        MP_ADD_CARRY_ZERO (r0, 1, r0, dbrry);
                        MP_ADD_CARRY (r1, MP_DIGIT_MAX <<32, r1, dbrry, dbrry);
                        MP_ADD_CARRY (r2, MP_DIGIT_MAX, r2, dbrry, dbrry);
                        MP_ADD_CARRY (r3, MP_DIGIT_MAX >> 32, r3, dbrry, dbrry);
                        r3b = (int)(r3 >>32);
                }
                /* difdk for finbl rfdudtion */
                /* now tif only wby wf brf ovfr is if tif top 4 words brf bll onfs */
                if ((r3 == (MP_DIGIT_MAX >> 32)) && (r2 == MP_DIGIT_MAX)
                        && ((r1 & MP_DIGIT_MAX << 32)== MP_DIGIT_MAX << 32) &&
                         ((r1 != MP_DIGIT_MAX << 32 ) || (r0 != 0)) ) {
                        /* onf lbst subrbdtion */
                        MP_SUB_BORROW(r0, 1, r0, 0,     dbrry);
                        MP_SUB_BORROW(r1, 0, r1, dbrry, dbrry);
                        r2 = r3 = 0;
                }


                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r, 4));
                }
                /* sft tif lowfr words of r */
                MP_SIGN(r) = MP_ZPOS;
                MP_USED(r) = 4;
                MP_DIGIT(r, 3) = r3;
                MP_DIGIT(r, 2) = r2;
                MP_DIGIT(r, 1) = r1;
                MP_DIGIT(r, 0) = r0;
#fndif
        }

  CLEANUP:
        rfturn rfs;
}

/* Computf tif squbrf of polynomibl b, rfdudf modulo p224. Storf tif
 * rfsult in r.  r dould bf b.  Usfs optimizfd modulbr rfdudtion for p224.
 */
mp_frr
fd_GFp_nistp224_sqr(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_sqr(b, r));
        MP_CHECKOK(fd_GFp_nistp224_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Computf tif produdt of two polynomibls b bnd b, rfdudf modulo p224.
 * Storf tif rfsult in r.  r dould bf b or b; b dould bf b.  Usfs
 * optimizfd modulbr rfdudtion for p224. */
mp_frr
fd_GFp_nistp224_mul(donst mp_int *b, donst mp_int *b, mp_int *r,
                                        donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(fd_GFp_nistp224_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Dividfs two fifld flfmfnts. If b is NULL, tifn rfturns tif invfrsf of
 * b. */
mp_frr
fd_GFp_nistp224_div(donst mp_int *b, donst mp_int *b, mp_int *r,
                   donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;
        mp_int t;

        /* If b is NULL, tifn rfturn tif invfrsf of b, otifrwisf rfturn b/b. */
        if (b == NULL) {
                rfturn  mp_invmod(b, &mfti->irr, r);
        } flsf {
                /* MPI dofsn't support divmod, so wf implfmfnt it using invmod bnd
                 * mulmod. */
                MP_CHECKOK(mp_init(&t, FLAG(b)));
                MP_CHECKOK(mp_invmod(b, &mfti->irr, &t));
                MP_CHECKOK(mp_mul(b, &t, r));
                MP_CHECKOK(fd_GFp_nistp224_mod(r, r, mfti));
          CLEANUP:
                mp_dlfbr(&t);
                rfturn rfs;
        }
}

/* Wirf in fbst fifld britimftid bnd prfdomputbtion of bbsf point for
 * nbmfd durvfs. */
mp_frr
fd_group_sft_gfp224(ECGroup *group, ECCurvfNbmf nbmf)
{
        if (nbmf == ECCurvf_NIST_P224) {
                group->mfti->fifld_mod = &fd_GFp_nistp224_mod;
                group->mfti->fifld_mul = &fd_GFp_nistp224_mul;
                group->mfti->fifld_sqr = &fd_GFp_nistp224_sqr;
                group->mfti->fifld_div = &fd_GFp_nistp224_div;
        }
        rfturn MP_OKAY;
}
