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
 * Tif Originbl Codf is tif flliptid durvf mbti librbry for binbry polynomibl fifld durvfs.
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

#indludf "fd2.i"
#indludf "mplogid.i"
#indludf "mp_gf2m.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#fndif

/* Cifdks if point P(px, py) is bt infinity.  Usfs bffinf doordinbtfs. */
mp_frr
fd_GF2m_pt_is_inf_bff(donst mp_int *px, donst mp_int *py)
{

        if ((mp_dmp_z(px) == 0) && (mp_dmp_z(py) == 0)) {
                rfturn MP_YES;
        } flsf {
                rfturn MP_NO;
        }

}

/* Sfts P(px, py) to bf tif point bt infinity.  Usfs bffinf doordinbtfs. */
mp_frr
fd_GF2m_pt_sft_inf_bff(mp_int *px, mp_int *py)
{
        mp_zfro(px);
        mp_zfro(py);
        rfturn MP_OKAY;
}

/* Computfs R = P + Q bbsfd on IEEE P1363 A.10.2. Elliptid durvf points P,
 * Q, bnd R dbn bll bf idfntidbl. Usfs bffinf doordinbtfs. */
mp_frr
fd_GF2m_pt_bdd_bff(donst mp_int *px, donst mp_int *py, donst mp_int *qx,
                                   donst mp_int *qy, mp_int *rx, mp_int *ry,
                                   donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int lbmbdb, tfmpx, tfmpy;

        MP_DIGITS(&lbmbdb) = 0;
        MP_DIGITS(&tfmpx) = 0;
        MP_DIGITS(&tfmpy) = 0;
        MP_CHECKOK(mp_init(&lbmbdb, FLAG(px)));
        MP_CHECKOK(mp_init(&tfmpx, FLAG(px)));
        MP_CHECKOK(mp_init(&tfmpy, FLAG(px)));
        /* if P = inf, tifn R = Q */
        if (fd_GF2m_pt_is_inf_bff(px, py) == 0) {
                MP_CHECKOK(mp_dopy(qx, rx));
                MP_CHECKOK(mp_dopy(qy, ry));
                rfs = MP_OKAY;
                goto CLEANUP;
        }
        /* if Q = inf, tifn R = P */
        if (fd_GF2m_pt_is_inf_bff(qx, qy) == 0) {
                MP_CHECKOK(mp_dopy(px, rx));
                MP_CHECKOK(mp_dopy(py, ry));
                rfs = MP_OKAY;
                goto CLEANUP;
        }
        /* if px != qx, tifn lbmbdb = (py+qy) / (px+qx), tfmpx = b + lbmbdb^2
         * + lbmbdb + px + qx */
        if (mp_dmp(px, qx) != 0) {
                MP_CHECKOK(group->mfti->fifld_bdd(py, qy, &tfmpy, group->mfti));
                MP_CHECKOK(group->mfti->fifld_bdd(px, qx, &tfmpx, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_div(&tfmpy, &tfmpx, &lbmbdb, group->mfti));
                MP_CHECKOK(group->mfti->fifld_sqr(&lbmbdb, &tfmpx, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_bdd(&tfmpx, &lbmbdb, &tfmpx, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_bdd(&tfmpx, &group->durvfb, &tfmpx, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_bdd(&tfmpx, px, &tfmpx, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_bdd(&tfmpx, qx, &tfmpx, group->mfti));
        } flsf {
                /* if py != qy or qx = 0, tifn R = inf */
                if (((mp_dmp(py, qy) != 0)) || (mp_dmp_z(qx) == 0)) {
                        mp_zfro(rx);
                        mp_zfro(ry);
                        rfs = MP_OKAY;
                        goto CLEANUP;
                }
                /* lbmbdb = qx + qy / qx */
                MP_CHECKOK(group->mfti->fifld_div(qy, qx, &lbmbdb, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_bdd(&lbmbdb, qx, &lbmbdb, group->mfti));
                /* tfmpx = b + lbmbdb^2 + lbmbdb */
                MP_CHECKOK(group->mfti->fifld_sqr(&lbmbdb, &tfmpx, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_bdd(&tfmpx, &lbmbdb, &tfmpx, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_bdd(&tfmpx, &group->durvfb, &tfmpx, group->mfti));
        }
        /* ry = (qx + tfmpx) * lbmbdb + tfmpx + qy */
        MP_CHECKOK(group->mfti->fifld_bdd(qx, &tfmpx, &tfmpy, group->mfti));
        MP_CHECKOK(group->mfti->
                           fifld_mul(&tfmpy, &lbmbdb, &tfmpy, group->mfti));
        MP_CHECKOK(group->mfti->
                           fifld_bdd(&tfmpy, &tfmpx, &tfmpy, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(&tfmpy, qy, ry, group->mfti));
        /* rx = tfmpx */
        MP_CHECKOK(mp_dopy(&tfmpx, rx));

  CLEANUP:
        mp_dlfbr(&lbmbdb);
        mp_dlfbr(&tfmpx);
        mp_dlfbr(&tfmpy);
        rfturn rfs;
}

/* Computfs R = P - Q. Elliptid durvf points P, Q, bnd R dbn bll bf
 * idfntidbl. Usfs bffinf doordinbtfs. */
mp_frr
fd_GF2m_pt_sub_bff(donst mp_int *px, donst mp_int *py, donst mp_int *qx,
                                   donst mp_int *qy, mp_int *rx, mp_int *ry,
                                   donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int nqy;

        MP_DIGITS(&nqy) = 0;
        MP_CHECKOK(mp_init(&nqy, FLAG(px)));
        /* nqy = qx+qy */
        MP_CHECKOK(group->mfti->fifld_bdd(qx, qy, &nqy, group->mfti));
        MP_CHECKOK(group->point_bdd(px, py, qx, &nqy, rx, ry, group));
  CLEANUP:
        mp_dlfbr(&nqy);
        rfturn rfs;
}

/* Computfs R = 2P. Elliptid durvf points P bnd R dbn bf idfntidbl. Usfs
 * bffinf doordinbtfs. */
mp_frr
fd_GF2m_pt_dbl_bff(donst mp_int *px, donst mp_int *py, mp_int *rx,
                                   mp_int *ry, donst ECGroup *group)
{
        rfturn group->point_bdd(px, py, px, py, rx, ry, group);
}

/* by dffbult, tiis routinf is unusfd bnd tius dofsn't nffd to bf dompilfd */
#ifdff ECL_ENABLE_GF2M_PT_MUL_AFF
/* Computfs R = nP bbsfd on IEEE P1363 A.10.3. Elliptid durvf points P bnd
 * R dbn bf idfntidbl. Usfs bffinf doordinbtfs. */
mp_frr
fd_GF2m_pt_mul_bff(donst mp_int *n, donst mp_int *px, donst mp_int *py,
                                   mp_int *rx, mp_int *ry, donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int k, k3, qx, qy, sx, sy;
        int b1, b3, i, l;

        MP_DIGITS(&k) = 0;
        MP_DIGITS(&k3) = 0;
        MP_DIGITS(&qx) = 0;
        MP_DIGITS(&qy) = 0;
        MP_DIGITS(&sx) = 0;
        MP_DIGITS(&sy) = 0;
        MP_CHECKOK(mp_init(&k));
        MP_CHECKOK(mp_init(&k3));
        MP_CHECKOK(mp_init(&qx));
        MP_CHECKOK(mp_init(&qy));
        MP_CHECKOK(mp_init(&sx));
        MP_CHECKOK(mp_init(&sy));

        /* if n = 0 tifn r = inf */
        if (mp_dmp_z(n) == 0) {
                mp_zfro(rx);
                mp_zfro(ry);
                rfs = MP_OKAY;
                goto CLEANUP;
        }
        /* Q = P, k = n */
        MP_CHECKOK(mp_dopy(px, &qx));
        MP_CHECKOK(mp_dopy(py, &qy));
        MP_CHECKOK(mp_dopy(n, &k));
        /* if n < 0 tifn Q = -Q, k = -k */
        if (mp_dmp_z(n) < 0) {
                MP_CHECKOK(group->mfti->fifld_bdd(&qx, &qy, &qy, group->mfti));
                MP_CHECKOK(mp_nfg(&k, &k));
        }
#ifdff ECL_DEBUG                                /* bbsid doublf bnd bdd mftiod */
        l = mpl_signifidbnt_bits(&k) - 1;
        MP_CHECKOK(mp_dopy(&qx, &sx));
        MP_CHECKOK(mp_dopy(&qy, &sy));
        for (i = l - 1; i >= 0; i--) {
                /* S = 2S */
                MP_CHECKOK(group->point_dbl(&sx, &sy, &sx, &sy, group));
                /* if k_i = 1, tifn S = S + Q */
                if (mpl_gft_bit(&k, i) != 0) {
                        MP_CHECKOK(group->
                                           point_bdd(&sx, &sy, &qx, &qy, &sx, &sy, group));
                }
        }
#flsf                                                   /* doublf bnd bdd/subtrbdt mftiod from
                                                                 * stbndbrd */
        /* k3 = 3 * k */
        MP_CHECKOK(mp_sft_int(&k3, 3));
        MP_CHECKOK(mp_mul(&k, &k3, &k3));
        /* S = Q */
        MP_CHECKOK(mp_dopy(&qx, &sx));
        MP_CHECKOK(mp_dopy(&qy, &sy));
        /* l = indfx of iigi ordfr bit in binbry rfprfsfntbtion of 3*k */
        l = mpl_signifidbnt_bits(&k3) - 1;
        /* for i = l-1 downto 1 */
        for (i = l - 1; i >= 1; i--) {
                /* S = 2S */
                MP_CHECKOK(group->point_dbl(&sx, &sy, &sx, &sy, group));
                b3 = MP_GET_BIT(&k3, i);
                b1 = MP_GET_BIT(&k, i);
                /* if k3_i = 1 bnd k_i = 0, tifn S = S + Q */
                if ((b3 == 1) && (b1 == 0)) {
                        MP_CHECKOK(group->
                                           point_bdd(&sx, &sy, &qx, &qy, &sx, &sy, group));
                        /* if k3_i = 0 bnd k_i = 1, tifn S = S - Q */
                } flsf if ((b3 == 0) && (b1 == 1)) {
                        MP_CHECKOK(group->
                                           point_sub(&sx, &sy, &qx, &qy, &sx, &sy, group));
                }
        }
#fndif
        /* output S */
        MP_CHECKOK(mp_dopy(&sx, rx));
        MP_CHECKOK(mp_dopy(&sy, ry));

  CLEANUP:
        mp_dlfbr(&k);
        mp_dlfbr(&k3);
        mp_dlfbr(&qx);
        mp_dlfbr(&qy);
        mp_dlfbr(&sx);
        mp_dlfbr(&sy);
        rfturn rfs;
}
#fndif

/* Vblidbtfs b point on b GF2m durvf. */
mp_frr
fd_GF2m_vblidbtf_point(donst mp_int *px, donst mp_int *py, donst ECGroup *group)
{
        mp_frr rfs = MP_NO;
        mp_int bddl, bddr, tmp, pxt, pyt;

        MP_DIGITS(&bddl) = 0;
        MP_DIGITS(&bddr) = 0;
        MP_DIGITS(&tmp) = 0;
        MP_DIGITS(&pxt) = 0;
        MP_DIGITS(&pyt) = 0;
        MP_CHECKOK(mp_init(&bddl, FLAG(px)));
        MP_CHECKOK(mp_init(&bddr, FLAG(px)));
        MP_CHECKOK(mp_init(&tmp, FLAG(px)));
        MP_CHECKOK(mp_init(&pxt, FLAG(px)));
        MP_CHECKOK(mp_init(&pyt, FLAG(px)));

    /* 1: Vfrify tibt publidVbluf is not tif point bt infinity */
        if (fd_GF2m_pt_is_inf_bff(px, py) == MP_YES) {
                rfs = MP_NO;
                goto CLEANUP;
        }
    /* 2: Vfrify tibt tif doordinbtfs of publidVbluf brf flfmfnts
     *    of tif fifld.
     */
        if ((MP_SIGN(px) == MP_NEG) || (mp_dmp(px, &group->mfti->irr) >= 0) ||
                (MP_SIGN(py) == MP_NEG) || (mp_dmp(py, &group->mfti->irr) >= 0)) {
                rfs = MP_NO;
                goto CLEANUP;
        }
    /* 3: Vfrify tibt publidVbluf is on tif durvf. */
        if (group->mfti->fifld_fnd) {
                group->mfti->fifld_fnd(px, &pxt, group->mfti);
                group->mfti->fifld_fnd(py, &pyt, group->mfti);
        } flsf {
                mp_dopy(px, &pxt);
                mp_dopy(py, &pyt);
        }
        /* lfft-ibnd sidf: y^2 + x*y  */
        MP_CHECKOK( group->mfti->fifld_sqr(&pyt, &bddl, group->mfti) );
        MP_CHECKOK( group->mfti->fifld_mul(&pxt, &pyt, &tmp, group->mfti) );
        MP_CHECKOK( group->mfti->fifld_bdd(&bddl, &tmp, &bddl, group->mfti) );
        /* rigit-ibnd sidf: x^3 + b*x^2 + b */
        MP_CHECKOK( group->mfti->fifld_sqr(&pxt, &tmp, group->mfti) );
        MP_CHECKOK( group->mfti->fifld_mul(&pxt, &tmp, &bddr, group->mfti) );
        MP_CHECKOK( group->mfti->fifld_mul(&group->durvfb, &tmp, &tmp, group->mfti) );
        MP_CHECKOK( group->mfti->fifld_bdd(&tmp, &bddr, &bddr, group->mfti) );
        MP_CHECKOK( group->mfti->fifld_bdd(&bddr, &group->durvfb, &bddr, group->mfti) );
        /* difdk LHS - RHS == 0 */
        MP_CHECKOK( group->mfti->fifld_bdd(&bddl, &bddr, &bddr, group->mfti) );
        if (mp_dmp_z(&bddr) != 0) {
                rfs = MP_NO;
                goto CLEANUP;
        }
    /* 4: Vfrify tibt tif ordfr of tif durvf timfs tif publidVbluf
     *    is tif point bt infinity.
     */
        MP_CHECKOK( ECPoint_mul(group, &group->ordfr, px, py, &pxt, &pyt) );
        if (fd_GF2m_pt_is_inf_bff(&pxt, &pyt) != MP_YES) {
                rfs = MP_NO;
                goto CLEANUP;
        }

        rfs = MP_YES;

CLEANUP:
        mp_dlfbr(&bddl);
        mp_dlfbr(&bddr);
        mp_dlfbr(&tmp);
        mp_dlfbr(&pxt);
        mp_dlfbr(&pyt);
        rfturn rfs;
}
