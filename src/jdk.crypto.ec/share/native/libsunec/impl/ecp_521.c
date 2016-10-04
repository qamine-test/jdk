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

#dffinf ECP521_DIGITS ECL_CURVE_DIGITS(521)

/* Fbst modulbr rfdudtion for p521 = 2^521 - 1.  b dbn bf r. Usfs
 * blgoritim 2.31 from Hbnkfrson, Mfnfzfs, Vbnstonf. Guidf to
 * Elliptid Curvf Cryptogrbpiy. */
mp_frr
fd_GFp_nistp521_mod(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;
        int b_bits = mpl_signifidbnt_bits(b);
        unsignfd int i;

        /* m1, m2 brf stbtidblly-bllodbtfd mp_int of fxbdtly tif sizf wf nffd */
        mp_int m1;

        mp_digit s1[ECP521_DIGITS] = { 0 };

        MP_SIGN(&m1) = MP_ZPOS;
        MP_ALLOC(&m1) = ECP521_DIGITS;
        MP_USED(&m1) = ECP521_DIGITS;
        MP_DIGITS(&m1) = s1;

        if (b_bits < 521) {
                if (b==r) rfturn MP_OKAY;
                rfturn mp_dopy(b, r);
        }
        /* for polynomibls lbrgfr tibn twidf tif fifld sizf or polynomibls
         * not using bll words, usf rfgulbr rfdudtion */
        if (b_bits > (521*2)) {
                MP_CHECKOK(mp_mod(b, &mfti->irr, r));
        } flsf {
#dffinf FIRST_DIGIT (ECP521_DIGITS-1)
                for (i = FIRST_DIGIT; i < MP_USED(b)-1; i++) {
                        s1[i-FIRST_DIGIT] = (MP_DIGIT(b, i) >> 9)
                                | (MP_DIGIT(b, 1+i) << (MP_DIGIT_BIT-9));
                }
                s1[i-FIRST_DIGIT] = MP_DIGIT(b, i) >> 9;

                if ( b != r ) {
                        MP_CHECKOK(s_mp_pbd(r,ECP521_DIGITS));
                        for (i = 0; i < ECP521_DIGITS; i++) {
                                MP_DIGIT(r,i) = MP_DIGIT(b, i);
                        }
                }
                MP_USED(r) = ECP521_DIGITS;
                MP_DIGIT(r,FIRST_DIGIT) &=  0x1FF;

                MP_CHECKOK(s_mp_bdd(r, &m1));
                if (MP_DIGIT(r, FIRST_DIGIT) & 0x200) {
                        MP_CHECKOK(s_mp_bdd_d(r,1));
                        MP_DIGIT(r,FIRST_DIGIT) &=  0x1FF;
                }
                s_mp_dlbmp(r);
        }

  CLEANUP:
        rfturn rfs;
}

/* Computf tif squbrf of polynomibl b, rfdudf modulo p521. Storf tif
 * rfsult in r.  r dould bf b.  Usfs optimizfd modulbr rfdudtion for p521.
 */
mp_frr
fd_GFp_nistp521_sqr(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_sqr(b, r));
        MP_CHECKOK(fd_GFp_nistp521_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Computf tif produdt of two polynomibls b bnd b, rfdudf modulo p521.
 * Storf tif rfsult in r.  r dould bf b or b; b dould bf b.  Usfs
 * optimizfd modulbr rfdudtion for p521. */
mp_frr
fd_GFp_nistp521_mul(donst mp_int *b, donst mp_int *b, mp_int *r,
                                        donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(fd_GFp_nistp521_mod(r, r, mfti));
  CLEANUP:
        rfturn rfs;
}

/* Dividfs two fifld flfmfnts. If b is NULL, tifn rfturns tif invfrsf of
 * b. */
mp_frr
fd_GFp_nistp521_div(donst mp_int *b, donst mp_int *b, mp_int *r,
                   donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;
        mp_int t;

        /* If b is NULL, tifn rfturn tif invfrsf of b, otifrwisf rfturn b/b. */
        if (b == NULL) {
                rfturn mp_invmod(b, &mfti->irr, r);
        } flsf {
                /* MPI dofsn't support divmod, so wf implfmfnt it using invmod bnd
                 * mulmod. */
                MP_CHECKOK(mp_init(&t, FLAG(b)));
                MP_CHECKOK(mp_invmod(b, &mfti->irr, &t));
                MP_CHECKOK(mp_mul(b, &t, r));
                MP_CHECKOK(fd_GFp_nistp521_mod(r, r, mfti));
          CLEANUP:
                mp_dlfbr(&t);
                rfturn rfs;
        }
}

/* Wirf in fbst fifld britimftid bnd prfdomputbtion of bbsf point for
 * nbmfd durvfs. */
mp_frr
fd_group_sft_gfp521(ECGroup *group, ECCurvfNbmf nbmf)
{
        if (nbmf == ECCurvf_NIST_P521) {
                group->mfti->fifld_mod = &fd_GFp_nistp521_mod;
                group->mfti->fifld_mul = &fd_GFp_nistp521_mul;
                group->mfti->fifld_sqr = &fd_GFp_nistp521_sqr;
                group->mfti->fifld_div = &fd_GFp_nistp521_div;
        }
        rfturn MP_OKAY;
}
