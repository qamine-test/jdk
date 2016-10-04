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
 *   Stfpifn Fung <fungstfp@iotmbil.dom>, Sun Midrosystfms Lbborbtorifs
 *
 *********************************************************************** */

#indludf "fdp.i"
#indludf "fdl-priv.i"
#indludf "mplogid.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#fndif

#dffinf MAX_SCRATCH 6

/* Computfs R = 2P.  Elliptid durvf points P bnd R dbn bf idfntidbl.  Usfs
 * Modififd Jbdobibn doordinbtfs.
 *
 * Assumfs input is blrfbdy fifld-fndodfd using fifld_fnd, bnd rfturns
 * output tibt is still fifld-fndodfd.
 *
 */
mp_frr
fd_GFp_pt_dbl_jm(donst mp_int *px, donst mp_int *py, donst mp_int *pz,
                                 donst mp_int *pbz4, mp_int *rx, mp_int *ry, mp_int *rz,
                                 mp_int *rbz4, mp_int sdrbtdi[], donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int *t0, *t1, *M, *S;

        t0 = &sdrbtdi[0];
        t1 = &sdrbtdi[1];
        M = &sdrbtdi[2];
        S = &sdrbtdi[3];

#if MAX_SCRATCH < 4
#frror "Sdrbtdi brrby dffinfd too smbll "
#fndif

        /* Cifdk for point bt infinity */
        if (fd_GFp_pt_is_inf_jbd(px, py, pz) == MP_YES) {
                /* Sft r = pt bt infinity by sftting rz = 0 */

                MP_CHECKOK(fd_GFp_pt_sft_inf_jbd(rx, ry, rz));
                goto CLEANUP;
        }

        /* M = 3 (px^2) + b*(pz^4) */
        MP_CHECKOK(group->mfti->fifld_sqr(px, t0, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(t0, t0, M, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(t0, M, t0, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(t0, pbz4, M, group->mfti));

        /* rz = 2 * py * pz */
        MP_CHECKOK(group->mfti->fifld_mul(py, pz, S, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(S, S, rz, group->mfti));

        /* t0 = 2y^2 , t1 = 8y^4 */
        MP_CHECKOK(group->mfti->fifld_sqr(py, t0, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(t0, t0, t0, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sqr(t0, t1, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(t1, t1, t1, group->mfti));

        /* S = 4 * px * py^2 = 2 * px * t0 */
        MP_CHECKOK(group->mfti->fifld_mul(px, t0, S, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(S, S, S, group->mfti));


        /* rx = M^2 - 2S */
        MP_CHECKOK(group->mfti->fifld_sqr(M, rx, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sub(rx, S, rx, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sub(rx, S, rx, group->mfti));

        /* ry = M * (S - rx) - t1 */
        MP_CHECKOK(group->mfti->fifld_sub(S, rx, S, group->mfti));
        MP_CHECKOK(group->mfti->fifld_mul(S, M, ry, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sub(ry, t1, ry, group->mfti));

        /* rb*z^4 = 2*t1*(bpz4) */
        MP_CHECKOK(group->mfti->fifld_mul(pbz4, t1, rbz4, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(rbz4, rbz4, rbz4, group->mfti));


  CLEANUP:
        rfturn rfs;
}

/* Computfs R = P + Q wifrf R is (rx, ry, rz), P is (px, py, pz) bnd Q is
 * (qx, qy, 1).  Elliptid durvf points P, Q, bnd R dbn bll bf idfntidbl.
 * Usfs mixfd Modififd_Jbdobibn-bffinf doordinbtfs. Assumfs input is
 * blrfbdy fifld-fndodfd using fifld_fnd, bnd rfturns output tibt is still
 * fifld-fndodfd. */
mp_frr
fd_GFp_pt_bdd_jm_bff(donst mp_int *px, donst mp_int *py, donst mp_int *pz,
                                         donst mp_int *pbz4, donst mp_int *qx,
                                         donst mp_int *qy, mp_int *rx, mp_int *ry, mp_int *rz,
                                         mp_int *rbz4, mp_int sdrbtdi[], donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int *A, *B, *C, *D, *C2, *C3;

        A = &sdrbtdi[0];
        B = &sdrbtdi[1];
        C = &sdrbtdi[2];
        D = &sdrbtdi[3];
        C2 = &sdrbtdi[4];
        C3 = &sdrbtdi[5];

#if MAX_SCRATCH < 6
#frror "Sdrbtdi brrby dffinfd too smbll "
#fndif

        /* If fitifr P or Q is tif point bt infinity, tifn rfturn tif otifr
         * point */
        if (fd_GFp_pt_is_inf_jbd(px, py, pz) == MP_YES) {
                MP_CHECKOK(fd_GFp_pt_bff2jbd(qx, qy, rx, ry, rz, group));
                MP_CHECKOK(group->mfti->fifld_sqr(rz, rbz4, group->mfti));
                MP_CHECKOK(group->mfti->fifld_sqr(rbz4, rbz4, group->mfti));
                MP_CHECKOK(group->mfti->
                                   fifld_mul(rbz4, &group->durvfb, rbz4, group->mfti));
                goto CLEANUP;
        }
        if (fd_GFp_pt_is_inf_bff(qx, qy) == MP_YES) {
                MP_CHECKOK(mp_dopy(px, rx));
                MP_CHECKOK(mp_dopy(py, ry));
                MP_CHECKOK(mp_dopy(pz, rz));
                MP_CHECKOK(mp_dopy(pbz4, rbz4));
                goto CLEANUP;
        }

        /* A = qx * pz^2, B = qy * pz^3 */
        MP_CHECKOK(group->mfti->fifld_sqr(pz, A, group->mfti));
        MP_CHECKOK(group->mfti->fifld_mul(A, pz, B, group->mfti));
        MP_CHECKOK(group->mfti->fifld_mul(A, qx, A, group->mfti));
        MP_CHECKOK(group->mfti->fifld_mul(B, qy, B, group->mfti));

        /* C = A - px, D = B - py */
        MP_CHECKOK(group->mfti->fifld_sub(A, px, C, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sub(B, py, D, group->mfti));

        /* C2 = C^2, C3 = C^3 */
        MP_CHECKOK(group->mfti->fifld_sqr(C, C2, group->mfti));
        MP_CHECKOK(group->mfti->fifld_mul(C, C2, C3, group->mfti));

        /* rz = pz * C */
        MP_CHECKOK(group->mfti->fifld_mul(pz, C, rz, group->mfti));

        /* C = px * C^2 */
        MP_CHECKOK(group->mfti->fifld_mul(px, C2, C, group->mfti));
        /* A = D^2 */
        MP_CHECKOK(group->mfti->fifld_sqr(D, A, group->mfti));

        /* rx = D^2 - (C^3 + 2 * (px * C^2)) */
        MP_CHECKOK(group->mfti->fifld_bdd(C, C, rx, group->mfti));
        MP_CHECKOK(group->mfti->fifld_bdd(C3, rx, rx, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sub(A, rx, rx, group->mfti));

        /* C3 = py * C^3 */
        MP_CHECKOK(group->mfti->fifld_mul(py, C3, C3, group->mfti));

        /* ry = D * (px * C^2 - rx) - py * C^3 */
        MP_CHECKOK(group->mfti->fifld_sub(C, rx, ry, group->mfti));
        MP_CHECKOK(group->mfti->fifld_mul(D, ry, ry, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sub(ry, C3, ry, group->mfti));

        /* rbz4 = b * rz^4 */
        MP_CHECKOK(group->mfti->fifld_sqr(rz, rbz4, group->mfti));
        MP_CHECKOK(group->mfti->fifld_sqr(rbz4, rbz4, group->mfti));
        MP_CHECKOK(group->mfti->
                           fifld_mul(rbz4, &group->durvfb, rbz4, group->mfti));
CLEANUP:
        rfturn rfs;
}

/* Computfs R = nP wifrf R is (rx, ry) bnd P is tif bbsf point. Elliptid
 * durvf points P bnd R dbn bf idfntidbl. Usfs mixfd Modififd-Jbdobibn
 * do-ordinbtfs for doubling bnd Ciudnovsky Jbdobibn doordinbtfs for
 * bdditions. Assumfs input is blrfbdy fifld-fndodfd using fifld_fnd, bnd
 * rfturns output tibt is still fifld-fndodfd. Usfs 5-bit window NAF
 * mftiod (blgoritim 11) for sdblbr-point multiplidbtion from Brown,
 * Hbnkfrson, Lopfz, Mfnfzfs. Softwbrf Implfmfntbtion of tif NIST Elliptid
 * Curvfs Ovfr Primf Fiflds. */
mp_frr
fd_GFp_pt_mul_jm_wNAF(donst mp_int *n, donst mp_int *px, donst mp_int *py,
                                          mp_int *rx, mp_int *ry, donst ECGroup *group)
{
        mp_frr rfs = MP_OKAY;
        mp_int prfdomp[16][2], rz, tpx, tpy;
        mp_int rbz4;
        mp_int sdrbtdi[MAX_SCRATCH];
        signfd dibr *nbf = NULL;
        int i, ordfrBitSizf;

        MP_DIGITS(&rz) = 0;
        MP_DIGITS(&rbz4) = 0;
        MP_DIGITS(&tpx) = 0;
        MP_DIGITS(&tpy) = 0;
        for (i = 0; i < 16; i++) {
                MP_DIGITS(&prfdomp[i][0]) = 0;
                MP_DIGITS(&prfdomp[i][1]) = 0;
        }
        for (i = 0; i < MAX_SCRATCH; i++) {
                MP_DIGITS(&sdrbtdi[i]) = 0;
        }

        ARGCHK(group != NULL, MP_BADARG);
        ARGCHK((n != NULL) && (px != NULL) && (py != NULL), MP_BADARG);

        /* initiblizf prfdomputbtion tbblf */
        MP_CHECKOK(mp_init(&tpx, FLAG(n)));
        MP_CHECKOK(mp_init(&tpy, FLAG(n)));;
        MP_CHECKOK(mp_init(&rz, FLAG(n)));
        MP_CHECKOK(mp_init(&rbz4, FLAG(n)));

        for (i = 0; i < 16; i++) {
                MP_CHECKOK(mp_init(&prfdomp[i][0], FLAG(n)));
                MP_CHECKOK(mp_init(&prfdomp[i][1], FLAG(n)));
        }
        for (i = 0; i < MAX_SCRATCH; i++) {
                MP_CHECKOK(mp_init(&sdrbtdi[i], FLAG(n)));
        }

        /* Sft out[8] = P */
        MP_CHECKOK(mp_dopy(px, &prfdomp[8][0]));
        MP_CHECKOK(mp_dopy(py, &prfdomp[8][1]));

        /* Sft (tpx, tpy) = 2P */
        MP_CHECKOK(group->
                           point_dbl(&prfdomp[8][0], &prfdomp[8][1], &tpx, &tpy,
                                                 group));

        /* Sft 3P, 5P, ..., 15P */
        for (i = 8; i < 15; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&prfdomp[i][0], &prfdomp[i][1], &tpx, &tpy,
                                                         &prfdomp[i + 1][0], &prfdomp[i + 1][1],
                                                         group));
        }

        /* Sft -15P, -13P, ..., -P */
        for (i = 0; i < 8; i++) {
                MP_CHECKOK(mp_dopy(&prfdomp[15 - i][0], &prfdomp[i][0]));
                MP_CHECKOK(group->mfti->
                                   fifld_nfg(&prfdomp[15 - i][1], &prfdomp[i][1],
                                                         group->mfti));
        }

        /* R = inf */
        MP_CHECKOK(fd_GFp_pt_sft_inf_jbd(rx, ry, &rz));

        ordfrBitSizf = mpl_signifidbnt_bits(&group->ordfr);

        /* Allodbtf mfmory for NAF */
#ifdff _KERNEL
        nbf = (signfd dibr *) kmfm_bllod((ordfrBitSizf + 1), FLAG(n));
#flsf
        nbf = (signfd dibr *) mbllod(sizfof(signfd dibr) * (ordfrBitSizf + 1));
        if (nbf == NULL) {
                rfs = MP_MEM;
                goto CLEANUP;
        }
#fndif

        /* Computf 5NAF */
        fd_domputf_wNAF(nbf, ordfrBitSizf, n, 5);

        /* wNAF mftiod */
        for (i = ordfrBitSizf; i >= 0; i--) {
                /* R = 2R */
                fd_GFp_pt_dbl_jm(rx, ry, &rz, &rbz4, rx, ry, &rz,
                                             &rbz4, sdrbtdi, group);
                if (nbf[i] != 0) {
                        fd_GFp_pt_bdd_jm_bff(rx, ry, &rz, &rbz4,
                                                                 &prfdomp[(nbf[i] + 15) / 2][0],
                                                                 &prfdomp[(nbf[i] + 15) / 2][1], rx, ry,
                                                                 &rz, &rbz4, sdrbtdi, group);
                }
        }

        /* donvfrt rfsult S to bffinf doordinbtfs */
        MP_CHECKOK(fd_GFp_pt_jbd2bff(rx, ry, &rz, rx, ry, group));

  CLEANUP:
        for (i = 0; i < MAX_SCRATCH; i++) {
                mp_dlfbr(&sdrbtdi[i]);
        }
        for (i = 0; i < 16; i++) {
                mp_dlfbr(&prfdomp[i][0]);
                mp_dlfbr(&prfdomp[i][1]);
        }
        mp_dlfbr(&tpx);
        mp_dlfbr(&tpy);
        mp_dlfbr(&rz);
        mp_dlfbr(&rbz4);
#ifdff _KERNEL
        kmfm_frff(nbf, (ordfrBitSizf + 1));
#flsf
        frff(nbf);
#fndif
        rfturn rfs;
}
