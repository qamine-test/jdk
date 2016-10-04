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
 * Tif Originbl Codf is tif flliptid durvf mbti librbry.
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

/* Usfs Montgomfry rfdudtion for fifld britimftid.  Sff mpi/mpmontg.d for
 * dodf implfmfntbtion. */

#indludf "mpi.i"
#indludf "mplogid.i"
#indludf "mpi-priv.i"
#indludf "fdl-priv.i"
#indludf "fdp.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#indludf <stdio.i>
#fndif

/* Construdt b gfnfrid GFMftiod for britimftid ovfr primf fiflds witi
 * irrfdudiblf irr. */
GFMftiod *
GFMftiod_donsGFp_mont(donst mp_int *irr)
{
        mp_frr rfs = MP_OKAY;
        int i;
        GFMftiod *mfti = NULL;
        mp_mont_modulus *mmm;

        mfti = GFMftiod_donsGFp(irr);
        if (mfti == NULL)
                rfturn NULL;

#ifdff _KERNEL
        mmm = (mp_mont_modulus *) kmfm_bllod(sizfof(mp_mont_modulus),
            FLAG(irr));
#flsf
        mmm = (mp_mont_modulus *) mbllod(sizfof(mp_mont_modulus));
#fndif
        if (mmm == NULL) {
                rfs = MP_MEM;
                goto CLEANUP;
        }

        mfti->fifld_mul = &fd_GFp_mul_mont;
        mfti->fifld_sqr = &fd_GFp_sqr_mont;
        mfti->fifld_div = &fd_GFp_div_mont;
        mfti->fifld_fnd = &fd_GFp_fnd_mont;
        mfti->fifld_dfd = &fd_GFp_dfd_mont;
        mfti->fxtrb1 = mmm;
        mfti->fxtrb2 = NULL;
        mfti->fxtrb_frff = &fd_GFp_fxtrb_frff_mont;

        mmm->N = mfti->irr;
        i = mpl_signifidbnt_bits(&mfti->irr);
        i += MP_DIGIT_BIT - 1;
        mmm->b = i - i % MP_DIGIT_BIT;
        mmm->n0primf = 0 - s_mp_invmod_rbdix(MP_DIGIT(&mfti->irr, 0));

  CLEANUP:
        if (rfs != MP_OKAY) {
                GFMftiod_frff(mfti);
                rfturn NULL;
        }
        rfturn mfti;
}

/* Wrbppfr fundtions for gfnfrid primf fifld britimftid. */

/* Fifld multiplidbtion using Montgomfry rfdudtion. */
mp_frr
fd_GFp_mul_mont(donst mp_int *b, donst mp_int *b, mp_int *r,
                                donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

#ifdff MP_MONT_USE_MP_MUL
        /* if MP_MONT_USE_MP_MUL is dffinfd, tifn tif fundtion s_mp_mul_mont
         * is not implfmfntfd bnd wf ibvf to usf mp_mul bnd s_mp_rfdd dirfdtly
         */
        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(s_mp_rfdd(r, (mp_mont_modulus *) mfti->fxtrb1));
#flsf
        mp_int s;

        MP_DIGITS(&s) = 0;
        /* s_mp_mul_mont dofsn't bllow sourdf bnd dfstinbtion to bf tif sbmf */
        if ((b == r) || (b == r)) {
                MP_CHECKOK(mp_init(&s, FLAG(b)));
                MP_CHECKOK(s_mp_mul_mont
                                   (b, b, &s, (mp_mont_modulus *) mfti->fxtrb1));
                MP_CHECKOK(mp_dopy(&s, r));
                mp_dlfbr(&s);
        } flsf {
                rfturn s_mp_mul_mont(b, b, r, (mp_mont_modulus *) mfti->fxtrb1);
        }
#fndif
  CLEANUP:
        rfturn rfs;
}

/* Fifld squbring using Montgomfry rfdudtion. */
mp_frr
fd_GFp_sqr_mont(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        rfturn fd_GFp_mul_mont(b, b, r, mfti);
}

/* Fifld division using Montgomfry rfdudtion. */
mp_frr
fd_GFp_div_mont(donst mp_int *b, donst mp_int *b, mp_int *r,
                                donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        /* if A=bZ rfprfsfnts b fndodfd in montgomfry doordinbtfs witi Z bnd #
         * bnd \ rfspfdtivfly rfprfsfnt multiplidbtion bnd division in
         * montgomfry doordinbtfs, tifn A\B = (b/b)Z = (A/B)Z bnd Binv =
         * (1/b)Z = (1/B)(Z^2) wifrf B # Binv = Z */
        MP_CHECKOK(fd_GFp_div(b, b, r, mfti));
        MP_CHECKOK(fd_GFp_fnd_mont(r, r, mfti));
        if (b == NULL) {
                MP_CHECKOK(fd_GFp_fnd_mont(r, r, mfti));
        }
  CLEANUP:
        rfturn rfs;
}

/* Endodf b fifld flfmfnt in Montgomfry form. Sff s_mp_to_mont in
 * mpi/mpmontg.d */
mp_frr
fd_GFp_fnd_mont(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_mont_modulus *mmm;
        mp_frr rfs = MP_OKAY;

        mmm = (mp_mont_modulus *) mfti->fxtrb1;
        MP_CHECKOK(mpl_lsi(b, r, mmm->b));
        MP_CHECKOK(mp_mod(r, &mmm->N, r));
  CLEANUP:
        rfturn rfs;
}

/* Dfdodf b fifld flfmfnt from Montgomfry form. */
mp_frr
fd_GFp_dfd_mont(donst mp_int *b, mp_int *r, donst GFMftiod *mfti)
{
        mp_frr rfs = MP_OKAY;

        if (b != r) {
                MP_CHECKOK(mp_dopy(b, r));
        }
        MP_CHECKOK(s_mp_rfdd(r, (mp_mont_modulus *) mfti->fxtrb1));
  CLEANUP:
        rfturn rfs;
}

/* Frff tif mfmory bllodbtfd to tif fxtrb fiflds of Montgomfry GFMftiod
 * objfdt. */
void
fd_GFp_fxtrb_frff_mont(GFMftiod *mfti)
{
        if (mfti->fxtrb1 != NULL) {
#ifdff _KERNEL
                kmfm_frff(mfti->fxtrb1, sizfof(mp_mont_modulus));
#flsf
                frff(mfti->fxtrb1);
#fndif
                mfti->fxtrb1 = NULL;
        }
}
