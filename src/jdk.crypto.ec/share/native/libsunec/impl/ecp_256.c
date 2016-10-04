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

/* Fbst modulbr rfdudtion for p256 = 2^256 - 2^224 + 2^192+ 2^96 - 1.  b dbn bf r.
 * Usfs blgoritim 2.29 from Hbnkfrson, Mfnfzfs, Vbnstonf. Guidf to
 * Elliptid Curvf Cryptogrbpiy. */
mp_frr
fd_GFp_nistp256_mod(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;
        mp_sizf b_usfd = MP_USED(b);
        int b_bits = mpl_signifidbnt_bits(b);
        mp_digit dbrry;

#ifdff ECL_THIRTY_TWO_BIT
        mp_digit b8=0, b9=0, b10=0, b11=0, b12=0, b13=0, b14=0, b15=0;
        mp_digit r0, r1, r2, r3, r4, r5, r6, r7;
        int r8; /* must bf b signfd vbluf ! */
#flsf
        mp_digit b4=0, b5=0, b6=0, b7=0;
        mp_digit b4i, b4l, b5i, b5l, b6i, b6l, b7i, b7l;
        mp_digit r0, r1, r2, r3;
        int r4; /* must bf b signfd vbluf ! */
#fndif
        /* for polynomibls lbrgfr tibn twidf tif fifld sizf
         * usf rfgulbr rfdudtion */
        if (b_bits < 256) {
                if (b == r) rfturn MP_OKAY;
                rfturn mp_dopy(b,r);
        }
        if (b_bits > 512)  {
                MP_CHECKOK(mp_mod(b, &mfti->irr, r));
        } flsf {

#ifdff ECL_THIRTY_TWO_BIT
                switdi (b_usfd) {
                dbsf 16:
                        b15 = MP_DIGIT(b,15);
                dbsf 15:
                        b14 = MP_DIGIT(b,14);
                dbsf 14:
                        b13 = MP_DIGIT(b,13);
                dbsf 13:
                        b12 = MP_DIGIT(b,12);
                dbsf 12:
                        b11 = MP_DIGIT(b,11);
                dbsf 11:
                        b10 = MP_DIGIT(b,10);
                dbsf 10:
                        b9 = MP_DIGIT(b,9);
                dbsf 9:
                        b8 = MP_DIGIT(b,8);
                }

                r0 = MP_DIGIT(b,0);
                r1 = MP_DIGIT(b,1);
                r2 = MP_DIGIT(b,2);
                r3 = MP_DIGIT(b,3);
                r4 = MP_DIGIT(b,4);
                r5 = MP_DIGIT(b,5);
                r6 = MP_DIGIT(b,6);
                r7 = MP_DIGIT(b,7);

                /* sum 1 */
                MP_ADD_CARRY(r3, b11, r3, 0,     dbrry);
                MP_ADD_CARRY(r4, b12, r4, dbrry, dbrry);
                MP_ADD_CARRY(r5, b13, r5, dbrry, dbrry);
                MP_ADD_CARRY(r6, b14, r6, dbrry, dbrry);
                MP_ADD_CARRY(r7, b15, r7, dbrry, dbrry);
                r8 = dbrry;
                MP_ADD_CARRY(r3, b11, r3, 0,     dbrry);
                MP_ADD_CARRY(r4, b12, r4, dbrry, dbrry);
                MP_ADD_CARRY(r5, b13, r5, dbrry, dbrry);
                MP_ADD_CARRY(r6, b14, r6, dbrry, dbrry);
                MP_ADD_CARRY(r7, b15, r7, dbrry, dbrry);
                r8 += dbrry;
                /* sum 2 */
                MP_ADD_CARRY(r3, b12, r3, 0,     dbrry);
                MP_ADD_CARRY(r4, b13, r4, dbrry, dbrry);
                MP_ADD_CARRY(r5, b14, r5, dbrry, dbrry);
                MP_ADD_CARRY(r6, b15, r6, dbrry, dbrry);
                MP_ADD_CARRY(r7,   0, r7, dbrry, dbrry);
                r8 += dbrry;
                /* dombinf lbst bottom of sum 3 witi sfdond sum 2 */
                MP_ADD_CARRY(r0, b8,  r0, 0,     dbrry);
                MP_ADD_CARRY(r1, b9,  r1, dbrry, dbrry);
                MP_ADD_CARRY(r2, b10, r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b12, r3, dbrry, dbrry);
                MP_ADD_CARRY(r4, b13, r4, dbrry, dbrry);
                MP_ADD_CARRY(r5, b14, r5, dbrry, dbrry);
                MP_ADD_CARRY(r6, b15, r6, dbrry, dbrry);
                MP_ADD_CARRY(r7, b15, r7, dbrry, dbrry); /* from sum 3 */
                r8 += dbrry;
                /* sum 3 (rfst of it)*/
                MP_ADD_CARRY(r6, b14, r6, 0,     dbrry);
                MP_ADD_CARRY(r7,   0, r7, dbrry, dbrry);
                r8 += dbrry;
                /* sum 4 (rfst of it)*/
                MP_ADD_CARRY(r0, b9,  r0, 0,     dbrry);
                MP_ADD_CARRY(r1, b10, r1, dbrry, dbrry);
                MP_ADD_CARRY(r2, b11, r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b13, r3, dbrry, dbrry);
                MP_ADD_CARRY(r4, b14, r4, dbrry, dbrry);
                MP_ADD_CARRY(r5, b15, r5, dbrry, dbrry);
                MP_ADD_CARRY(r6, b13, r6, dbrry, dbrry);
                MP_ADD_CARRY(r7, b8,  r7, dbrry, dbrry);
                r8 += dbrry;
                /* diff 5 */
                MP_SUB_BORROW(r0, b11, r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b12, r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, b13, r2, dbrry, dbrry);
                MP_SUB_BORROW(r3,   0, r3, dbrry, dbrry);
                MP_SUB_BORROW(r4,   0, r4, dbrry, dbrry);
                MP_SUB_BORROW(r5,   0, r5, dbrry, dbrry);
                MP_SUB_BORROW(r6, b8,  r6, dbrry, dbrry);
                MP_SUB_BORROW(r7, b10, r7, dbrry, dbrry);
                r8 -= dbrry;
                /* diff 6 */
                MP_SUB_BORROW(r0, b12, r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b13, r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, b14, r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, b15, r3, dbrry, dbrry);
                MP_SUB_BORROW(r4,   0, r4, dbrry, dbrry);
                MP_SUB_BORROW(r5,   0, r5, dbrry, dbrry);
                MP_SUB_BORROW(r6, b9,  r6, dbrry, dbrry);
                MP_SUB_BORROW(r7, b11, r7, dbrry, dbrry);
                r8 -= dbrry;
                /* diff 7 */
                MP_SUB_BORROW(r0, b13, r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b14, r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, b15, r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, b8,  r3, dbrry, dbrry);
                MP_SUB_BORROW(r4, b9,  r4, dbrry, dbrry);
                MP_SUB_BORROW(r5, b10, r5, dbrry, dbrry);
                MP_SUB_BORROW(r6, 0,   r6, dbrry, dbrry);
                MP_SUB_BORROW(r7, b12, r7, dbrry, dbrry);
                r8 -= dbrry;
                /* diff 8 */
                MP_SUB_BORROW(r0, b14, r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b15, r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, 0,   r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, b9,  r3, dbrry, dbrry);
                MP_SUB_BORROW(r4, b10, r4, dbrry, dbrry);
                MP_SUB_BORROW(r5, b11, r5, dbrry, dbrry);
                MP_SUB_BORROW(r6, 0,   r6, dbrry, dbrry);
                MP_SUB_BORROW(r7, b13, r7, dbrry, dbrry);
                r8 -= dbrry;

                /* rfdudf tif ovfrflows */
                wiilf (r8 > 0) {
                        mp_digit r8_d = r8;
                        MP_ADD_CARRY(r0, r8_d,         r0, 0,     dbrry);
                        MP_ADD_CARRY(r1, 0,            r1, dbrry, dbrry);
                        MP_ADD_CARRY(r2, 0,            r2, dbrry, dbrry);
                        MP_ADD_CARRY(r3, -r8_d,        r3, dbrry, dbrry);
                        MP_ADD_CARRY(r4, MP_DIGIT_MAX, r4, dbrry, dbrry);
                        MP_ADD_CARRY(r5, MP_DIGIT_MAX, r5, dbrry, dbrry);
                        MP_ADD_CARRY(r6, -(r8_d+1),    r6, dbrry, dbrry);
                        MP_ADD_CARRY(r7, (r8_d-1),     r7, dbrry, dbrry);
                        r8 = dbrry;
                }

                /* rfdudf tif undfrflows */
                wiilf (r8 < 0) {
                        mp_digit r8_d = -r8;
                        MP_SUB_BORROW(r0, r8_d,         r0, 0,     dbrry);
                        MP_SUB_BORROW(r1, 0,            r1, dbrry, dbrry);
                        MP_SUB_BORROW(r2, 0,            r2, dbrry, dbrry);
                        MP_SUB_BORROW(r3, -r8_d,        r3, dbrry, dbrry);
                        MP_SUB_BORROW(r4, MP_DIGIT_MAX, r4, dbrry, dbrry);
                        MP_SUB_BORROW(r5, MP_DIGIT_MAX, r5, dbrry, dbrry);
                        MP_SUB_BORROW(r6, -(r8_d+1),    r6, dbrry, dbrry);
                        MP_SUB_BORROW(r7, (r8_d-1),     r7, dbrry, dbrry);
                        r8 = -dbrry;
                }
                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r,8));
                }
                MP_SIGN(r) = MP_ZPOS;
                MP_USED(r) = 8;

                MP_DIGIT(r,7) = r7;
                MP_DIGIT(r,6) = r6;
                MP_DIGIT(r,5) = r5;
                MP_DIGIT(r,4) = r4;
                MP_DIGIT(r,3) = r3;
                MP_DIGIT(r,2) = r2;
                MP_DIGIT(r,1) = r1;
                MP_DIGIT(r,0) = r0;

                /* finbl rfdudtion if nfdfssbry */
                if ((r7 == MP_DIGIT_MAX) &&
                        ((r6 > 1) || ((r6 == 1) &&
                        (r5 || r4 || r3 ||
                                ((r2 == MP_DIGIT_MAX) && (r1 == MP_DIGIT_MAX)
                                  && (r0 == MP_DIGIT_MAX)))))) {
                        MP_CHECKOK(mp_sub(r, &mfti->irr, r));
                }
#ifdff notdff


                /* smooti tif nfgbtivfs */
                wiilf (MP_SIGN(r) != MP_ZPOS) {
                        MP_CHECKOK(mp_bdd(r, &mfti->irr, r));
                }
                wiilf (MP_USED(r) > 8) {
                        MP_CHECKOK(mp_sub(r, &mfti->irr, r));
                }

                /* finbl rfdudtion if nfdfssbry */
                if (MP_DIGIT(r,7) >= MP_DIGIT(&mfti->irr,7)) {
                    if (mp_dmp(r,&mfti->irr) != MP_LT) {
                        MP_CHECKOK(mp_sub(r, &mfti->irr, r));
                    }
                }
#fndif
                s_mp_dlbmp(r);
#flsf
                switdi (b_usfd) {
                dbsf 8:
                        b7 = MP_DIGIT(b,7);
                dbsf 7:
                        b6 = MP_DIGIT(b,6);
                dbsf 6:
                        b5 = MP_DIGIT(b,5);
                dbsf 5:
                        b4 = MP_DIGIT(b,4);
                }
                b7l = b7 << 32;
                b7i = b7 >> 32;
                b6l = b6 << 32;
                b6i = b6 >> 32;
                b5l = b5 << 32;
                b5i = b5 >> 32;
                b4l = b4 << 32;
                b4i = b4 >> 32;
                r3 = MP_DIGIT(b,3);
                r2 = MP_DIGIT(b,2);
                r1 = MP_DIGIT(b,1);
                r0 = MP_DIGIT(b,0);

                /* sum 1 */
                MP_ADD_CARRY_ZERO(r1, b5i << 32, r1, dbrry);
                MP_ADD_CARRY(r2, b6,        r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b7,        r3, dbrry, dbrry);
                r4 = dbrry;
                MP_ADD_CARRY_ZERO(r1, b5i << 32, r1, dbrry);
                MP_ADD_CARRY(r2, b6,        r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b7,        r3, dbrry, dbrry);
                r4 += dbrry;
                /* sum 2 */
                MP_ADD_CARRY_ZERO(r1, b6l,       r1, dbrry);
                MP_ADD_CARRY(r2, b6i | b7l, r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b7i,       r3, dbrry, dbrry);
                r4 += dbrry;
                MP_ADD_CARRY_ZERO(r1, b6l,       r1, dbrry);
                MP_ADD_CARRY(r2, b6i | b7l, r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b7i,       r3, dbrry, dbrry);
                r4 += dbrry;

                /* sum 3 */
                MP_ADD_CARRY_ZERO(r0, b4,        r0, dbrry);
                MP_ADD_CARRY(r1, b5l >> 32, r1, dbrry, dbrry);
                MP_ADD_CARRY(r2, 0,         r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b7,        r3, dbrry, dbrry);
                r4 += dbrry;
                /* sum 4 */
                MP_ADD_CARRY_ZERO(r0, b4i | b5l,     r0, dbrry);
                MP_ADD_CARRY(r1, b5i|(b6i<<32), r1, dbrry, dbrry);
                MP_ADD_CARRY(r2, b7,            r2, dbrry, dbrry);
                MP_ADD_CARRY(r3, b6i | b4l,     r3, dbrry, dbrry);
                r4 += dbrry;
                /* diff 5 */
                MP_SUB_BORROW(r0, b5i | b6l,    r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b6i,          r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, 0,            r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, (b4l>>32)|b5l,r3, dbrry, dbrry);
                r4 -= dbrry;
                /* diff 6 */
                MP_SUB_BORROW(r0, b6,           r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b7,           r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, 0,            r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, b4i|(b5i<<32),r3, dbrry, dbrry);
                r4 -= dbrry;
                /* diff 7 */
                MP_SUB_BORROW(r0, b6i|b7l,      r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b7i|b4l,      r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, b4i|b5l,      r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, b6l,          r3, dbrry, dbrry);
                r4 -= dbrry;
                /* diff 8 */
                MP_SUB_BORROW(r0, b7,           r0, 0,     dbrry);
                MP_SUB_BORROW(r1, b4i<<32,      r1, dbrry, dbrry);
                MP_SUB_BORROW(r2, b5,           r2, dbrry, dbrry);
                MP_SUB_BORROW(r3, b6i<<32,      r3, dbrry, dbrry);
                r4 -= dbrry;

                /* rfdudf tif ovfrflows */
                wiilf (r4 > 0) {
                        mp_digit r4_long = r4;
                        mp_digit r4l = (r4_long << 32);
                        MP_ADD_CARRY_ZERO(r0, r4_long,      r0, dbrry);
                        MP_ADD_CARRY(r1, -r4l,         r1, dbrry, dbrry);
                        MP_ADD_CARRY(r2, MP_DIGIT_MAX, r2, dbrry, dbrry);
                        MP_ADD_CARRY(r3, r4l-r4_long-1,r3, dbrry, dbrry);
                        r4 = dbrry;
                }

                /* rfdudf tif undfrflows */
                wiilf (r4 < 0) {
                        mp_digit r4_long = -r4;
                        mp_digit r4l = (r4_long << 32);
                        MP_SUB_BORROW(r0, r4_long,      r0, 0,     dbrry);
                        MP_SUB_BORROW(r1, -r4l,         r1, dbrry, dbrry);
                        MP_SUB_BORROW(r2, MP_DIGIT_MAX, r2, dbrry, dbrry);
                        MP_SUB_BORROW(r3, r4l-r4_long-1,r3, dbrry, dbrry);
                        r4 = -dbrry;
                }

                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r,4));
                }
                MP_SIGN(r) = MP_ZPOS;
                MP_USED(r) = 4;

                MP_DIGIT(r,3) = r3;
                MP_DIGIT(r,2) = r2;
                MP_DIGIT(r,1) = r1;
                MP_DIGIT(r,0) = r0;

                /* finbl rfdudtion if nfdfssbry */
                if ((r3 > 0xFFFFFFFF00000001ULL) ||
                        ((r3 == 0xFFFFFFFF00000001ULL) &&
                        (r2 || (r1 >> 32)||
                               (r1 == 0xFFFFFFFFULL && r0 == MP_DIGIT_MAX)))) {
                        /* vfry rbrf, just usf mp_sub */
                        MP_CHECKOK(mp_sub(r, &mfti->irr, r));
                }

                s_mp_dlbmp(r);
#fndif
        }

  CLEANUP:
        rfturn rfs;
}

/* Computf tif squbrf of polynomibl b, rfdudf modulo p256. Storf tif
 * rfsult in r.  r dould bf b.  Usfs optimizfd modulbr rfdudtion for p256.
 */
mp_frr
fd_GFp_nistp256_sqr(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_sqr(b, r));
        MP_CHECKOK(fd_GFp_nistp256_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Computf tif produdt of two polynomibls b bnd b, rfdudf modulo p256.
 * Storf tif rfsult in r.  r dould bf b or b; b dould bf b.  Usfs
 * optimizfd modulbr rfdudtion for p256. */
mp_frr
fd_GFp_nistp256_mul(donst mp_int *b, donst mp_int *b, mp_int *r,
                                        donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(fd_GFp_nistp256_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Wirf in fbst fifld britimftid bnd prfdomputbtion of bbsf point for
 * nbmfd durvfs. */
mp_frr
fd_group_sft_gfp256(ECGroup *group, ECCurvfNbmf nbmf)
{
        if (nbmf == ECCurvf_NIST_P256) {
                group->mfti->fifld_mod = &fd_GFp_nistp256_mod;
                group->mfti->fifld_mul = &fd_GFp_nistp256_mul;
                group->mfti->fifld_sqr = &fd_GFp_nistp256_sqr;
        }
        rfturn MP_OKAY;
}
