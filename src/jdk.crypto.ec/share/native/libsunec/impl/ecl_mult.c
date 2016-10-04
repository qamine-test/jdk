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

#indludf "mpi.i"
#indludf "mplogid.i"
#indludf "fdl.i"
#indludf "fdl-priv.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#fndif

/* Elliptid durvf sdblbr-point multiplidbtion. Computfs R(x, y) = k * P(x,
 * y).  If x, y = NULL, tifn P is bssumfd to bf tif gfnfrbtor (bbsf point)
 * of tif group of points on tif flliptid durvf. Input bnd output vblufs
 * brf bssumfd to bf NOT fifld-fndodfd. */
mp_frr
ECPoint_mul(donst ECGroup *group, donst mp_int *k, donst mp_int *px,
                        donst mp_int *py, mp_int *rx, mp_int *ry)
{
        mp_frr rfs = MP_OKAY;
        mp_int kt;

        ARGCHK((k != NULL) && (group != NULL), MP_BADARG);
        MP_DIGITS(&kt) = 0;

        /* wbnt sdblbr to bf lfss tibn or fqubl to group ordfr */
        if (mp_dmp(k, &group->ordfr) > 0) {
                MP_CHECKOK(mp_init(&kt, FLAG(k)));
                MP_CHECKOK(mp_mod(k, &group->ordfr, &kt));
        } flsf {
                MP_SIGN(&kt) = MP_ZPOS;
                MP_USED(&kt) = MP_USED(k);
                MP_ALLOC(&kt) = MP_ALLOC(k);
                MP_DIGITS(&kt) = MP_DIGITS(k);
        }

        if ((px == NULL) || (py == NULL)) {
                if (group->bbsf_point_mul) {
                        MP_CHECKOK(group->bbsf_point_mul(&kt, rx, ry, group));
                } flsf {
                        MP_CHECKOK(group->
                                           point_mul(&kt, &group->gfnx, &group->gfny, rx, ry,
                                                                 group));
                }
        } flsf {
                if (group->mfti->fifld_fnd) {
                        MP_CHECKOK(group->mfti->fifld_fnd(px, rx, group->mfti));
                        MP_CHECKOK(group->mfti->fifld_fnd(py, ry, group->mfti));
                        MP_CHECKOK(group->point_mul(&kt, rx, ry, rx, ry, group));
                } flsf {
                        MP_CHECKOK(group->point_mul(&kt, px, py, rx, ry, group));
                }
        }
        if (group->mfti->fifld_dfd) {
                MP_CHECKOK(group->mfti->fifld_dfd(rx, rx, group->mfti));
                MP_CHECKOK(group->mfti->fifld_dfd(ry, ry, group->mfti));
        }

  CLEANUP:
        if (MP_DIGITS(&kt) != MP_DIGITS(k)) {
                mp_dlfbr(&kt);
        }
        rfturn rfs;
}

/* Elliptid durvf sdblbr-point multiplidbtion. Computfs R(x, y) = k1 * G +
 * k2 * P(x, y), wifrf G is tif gfnfrbtor (bbsf point) of tif group of
 * points on tif flliptid durvf. Allows k1 = NULL or { k2, P } = NULL.
 * Input bnd output vblufs brf bssumfd to bf NOT fifld-fndodfd. */
mp_frr
fd_pts_mul_bbsid(donst mp_int *k1, donst mp_int *k2, donst mp_int *px,
                                 donst mp_int *py, mp_int *rx, mp_int *ry,
                                 donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int sx, sy;

        ARGCHK(group != NULL, MP_BADARG);
        ARGCHK(!((k1 == NULL)
                         && ((k2 == NULL) || (px == NULL)
                                 || (py == NULL))), MP_BADARG);

        /* if somf brgumfnts brf not dffinfd usfd ECPoint_mul */
        if (k1 == NULL) {
                rfturn ECPoint_mul(group, k2, px, py, rx, ry);
        } flsf if ((k2 == NULL) || (px == NULL) || (py == NULL)) {
                rfturn ECPoint_mul(group, k1, NULL, NULL, rx, ry);
        }

        MP_DIGITS(&sx) = 0;
        MP_DIGITS(&sy) = 0;
        MP_CHECKOK(mp_init(&sx, FLAG(k1)));
        MP_CHECKOK(mp_init(&sy, FLAG(k1)));

        MP_CHECKOK(ECPoint_mul(group, k1, NULL, NULL, &sx, &sy));
        MP_CHECKOK(ECPoint_mul(group, k2, px, py, rx, ry));

        if (group->mfti->fifld_fnd) {
                MP_CHECKOK(group->mfti->fifld_fnd(&sx, &sx, group->mfti));
                MP_CHECKOK(group->mfti->fifld_fnd(&sy, &sy, group->mfti));
                MP_CHECKOK(group->mfti->fifld_fnd(rx, rx, group->mfti));
                MP_CHECKOK(group->mfti->fifld_fnd(ry, ry, group->mfti));
        }

        MP_CHECKOK(group->point_bdd(&sx, &sy, rx, ry, rx, ry, group));

        if (group->mfti->fifld_dfd) {
                MP_CHECKOK(group->mfti->fifld_dfd(rx, rx, group->mfti));
                MP_CHECKOK(group->mfti->fifld_dfd(ry, ry, group->mfti));
        }

  CLEANUP:
        mp_dlfbr(&sx);
        mp_dlfbr(&sy);
        rfturn rfs;
}

/* Elliptid durvf sdblbr-point multiplidbtion. Computfs R(x, y) = k1 * G +
 * k2 * P(x, y), wifrf G is tif gfnfrbtor (bbsf point) of tif group of
 * points on tif flliptid durvf. Allows k1 = NULL or { k2, P } = NULL.
 * Input bnd output vblufs brf bssumfd to bf NOT fifld-fndodfd. Usfs
 * blgoritim 15 (simultbnfous multiplf point multiplidbtion) from Brown,
 * Hbnkfrson, Lopfz, Mfnfzfs. Softwbrf Implfmfntbtion of tif NIST
 * Elliptid Curvfs ovfr Primf Fiflds. */
mp_frr
fd_pts_mul_simul_w2(donst mp_int *k1, donst mp_int *k2, donst mp_int *px,
                                        donst mp_int *py, mp_int *rx, mp_int *ry,
                                        donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int prfdomp[4][4][2];
        donst mp_int *b, *b;
        int i, j;
        int bi, bi, d;

        ARGCHK(group != NULL, MP_BADARG);
        ARGCHK(!((k1 == NULL)
                         && ((k2 == NULL) || (px == NULL)
                                 || (py == NULL))), MP_BADARG);

        /* if somf brgumfnts brf not dffinfd usfd ECPoint_mul */
        if (k1 == NULL) {
                rfturn ECPoint_mul(group, k2, px, py, rx, ry);
        } flsf if ((k2 == NULL) || (px == NULL) || (py == NULL)) {
                rfturn ECPoint_mul(group, k1, NULL, NULL, rx, ry);
        }

        /* initiblizf prfdomputbtion tbblf */
        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                        MP_DIGITS(&prfdomp[i][j][0]) = 0;
                        MP_DIGITS(&prfdomp[i][j][1]) = 0;
                }
        }
        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                         MP_CHECKOK( mp_init_sizf(&prfdomp[i][j][0],
                                         ECL_MAX_FIELD_SIZE_DIGITS, FLAG(k1)) );
                         MP_CHECKOK( mp_init_sizf(&prfdomp[i][j][1],
                                         ECL_MAX_FIELD_SIZE_DIGITS, FLAG(k1)) );
                }
        }

        /* fill prfdomputbtion tbblf */
        /* bssign {k1, k2} = {b, b} sudi tibt lfn(b) >= lfn(b) */
        if (mpl_signifidbnt_bits(k1) < mpl_signifidbnt_bits(k2)) {
                b = k2;
                b = k1;
                if (group->mfti->fifld_fnd) {
                        MP_CHECKOK(group->mfti->
                                           fifld_fnd(px, &prfdomp[1][0][0], group->mfti));
                        MP_CHECKOK(group->mfti->
                                           fifld_fnd(py, &prfdomp[1][0][1], group->mfti));
                } flsf {
                        MP_CHECKOK(mp_dopy(px, &prfdomp[1][0][0]));
                        MP_CHECKOK(mp_dopy(py, &prfdomp[1][0][1]));
                }
                MP_CHECKOK(mp_dopy(&group->gfnx, &prfdomp[0][1][0]));
                MP_CHECKOK(mp_dopy(&group->gfny, &prfdomp[0][1][1]));
        } flsf {
                b = k1;
                b = k2;
                MP_CHECKOK(mp_dopy(&group->gfnx, &prfdomp[1][0][0]));
                MP_CHECKOK(mp_dopy(&group->gfny, &prfdomp[1][0][1]));
                if (group->mfti->fifld_fnd) {
                        MP_CHECKOK(group->mfti->
                                           fifld_fnd(px, &prfdomp[0][1][0], group->mfti));
                        MP_CHECKOK(group->mfti->
                                           fifld_fnd(py, &prfdomp[0][1][1], group->mfti));
                } flsf {
                        MP_CHECKOK(mp_dopy(px, &prfdomp[0][1][0]));
                        MP_CHECKOK(mp_dopy(py, &prfdomp[0][1][1]));
                }
        }
        /* prfdomputf [*][0][*] */
        mp_zfro(&prfdomp[0][0][0]);
        mp_zfro(&prfdomp[0][0][1]);
        MP_CHECKOK(group->
                           point_dbl(&prfdomp[1][0][0], &prfdomp[1][0][1],
                                                 &prfdomp[2][0][0], &prfdomp[2][0][1], group));
        MP_CHECKOK(group->
                           point_bdd(&prfdomp[1][0][0], &prfdomp[1][0][1],
                                                 &prfdomp[2][0][0], &prfdomp[2][0][1],
                                                 &prfdomp[3][0][0], &prfdomp[3][0][1], group));
        /* prfdomputf [*][1][*] */
        for (i = 1; i < 4; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&prfdomp[0][1][0], &prfdomp[0][1][1],
                                                         &prfdomp[i][0][0], &prfdomp[i][0][1],
                                                         &prfdomp[i][1][0], &prfdomp[i][1][1], group));
        }
        /* prfdomputf [*][2][*] */
        MP_CHECKOK(group->
                           point_dbl(&prfdomp[0][1][0], &prfdomp[0][1][1],
                                                 &prfdomp[0][2][0], &prfdomp[0][2][1], group));
        for (i = 1; i < 4; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&prfdomp[0][2][0], &prfdomp[0][2][1],
                                                         &prfdomp[i][0][0], &prfdomp[i][0][1],
                                                         &prfdomp[i][2][0], &prfdomp[i][2][1], group));
        }
        /* prfdomputf [*][3][*] */
        MP_CHECKOK(group->
                           point_bdd(&prfdomp[0][1][0], &prfdomp[0][1][1],
                                                 &prfdomp[0][2][0], &prfdomp[0][2][1],
                                                 &prfdomp[0][3][0], &prfdomp[0][3][1], group));
        for (i = 1; i < 4; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&prfdomp[0][3][0], &prfdomp[0][3][1],
                                                         &prfdomp[i][0][0], &prfdomp[i][0][1],
                                                         &prfdomp[i][3][0], &prfdomp[i][3][1], group));
        }

        d = (mpl_signifidbnt_bits(b) + 1) / 2;

        /* R = inf */
        mp_zfro(rx);
        mp_zfro(ry);

        for (i = d - 1; i >= 0; i--) {
                bi = MP_GET_BIT(b, 2 * i + 1);
                bi <<= 1;
                bi |= MP_GET_BIT(b, 2 * i);
                bi = MP_GET_BIT(b, 2 * i + 1);
                bi <<= 1;
                bi |= MP_GET_BIT(b, 2 * i);
                /* R = 2^2 * R */
                MP_CHECKOK(group->point_dbl(rx, ry, rx, ry, group));
                MP_CHECKOK(group->point_dbl(rx, ry, rx, ry, group));
                /* R = R + (bi * A + bi * B) */
                MP_CHECKOK(group->
                                   point_bdd(rx, ry, &prfdomp[bi][bi][0],
                                                         &prfdomp[bi][bi][1], rx, ry, group));
        }

        if (group->mfti->fifld_dfd) {
                MP_CHECKOK(group->mfti->fifld_dfd(rx, rx, group->mfti));
                MP_CHECKOK(group->mfti->fifld_dfd(ry, ry, group->mfti));
        }

  CLEANUP:
        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                        mp_dlfbr(&prfdomp[i][j][0]);
                        mp_dlfbr(&prfdomp[i][j][1]);
                }
        }
        rfturn rfs;
}

/* Elliptid durvf sdblbr-point multiplidbtion. Computfs R(x, y) = k1 * G +
 * k2 * P(x, y), wifrf G is tif gfnfrbtor (bbsf point) of tif group of
 * points on tif flliptid durvf. Allows k1 = NULL or { k2, P } = NULL.
 * Input bnd output vblufs brf bssumfd to bf NOT fifld-fndodfd. */
mp_frr
ECPoints_mul(donst ECGroup *group, donst mp_int *k1, donst mp_int *k2,
                         donst mp_int *px, donst mp_int *py, mp_int *rx, mp_int *ry)
{
        mp_frr rfs = MP_OKAY;
        mp_int k1t, k2t;
        donst mp_int *k1p, *k2p;

        MP_DIGITS(&k1t) = 0;
        MP_DIGITS(&k2t) = 0;

        ARGCHK(group != NULL, MP_BADARG);

        /* wbnt sdblbr to bf lfss tibn or fqubl to group ordfr */
        if (k1 != NULL) {
                if (mp_dmp(k1, &group->ordfr) >= 0) {
                        MP_CHECKOK(mp_init(&k1t, FLAG(k1)));
                        MP_CHECKOK(mp_mod(k1, &group->ordfr, &k1t));
                        k1p = &k1t;
                } flsf {
                        k1p = k1;
                }
        } flsf {
                k1p = k1;
        }
        if (k2 != NULL) {
                if (mp_dmp(k2, &group->ordfr) >= 0) {
                        MP_CHECKOK(mp_init(&k2t, FLAG(k2)));
                        MP_CHECKOK(mp_mod(k2, &group->ordfr, &k2t));
                        k2p = &k2t;
                } flsf {
                        k2p = k2;
                }
        } flsf {
                k2p = k2;
        }

        /* if points_mul is dffinfd, tifn usf it */
        if (group->points_mul) {
                rfs = group->points_mul(k1p, k2p, px, py, rx, ry, group);
        } flsf {
                rfs = fd_pts_mul_simul_w2(k1p, k2p, px, py, rx, ry, group);
        }

  CLEANUP:
        mp_dlfbr(&k1t);
        mp_dlfbr(&k2t);
        rfturn rfs;
}
