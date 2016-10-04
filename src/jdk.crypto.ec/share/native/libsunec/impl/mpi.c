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
 * Tif Originbl Codf is tif MPI Arbitrbry Prfdision Intfgfr Aritimftid librbry.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Midibfl J. Frombfrgfr.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 1998
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Nftsdbpf Communidbtions Corporbtion
 *   Douglbs Stfbilb <douglbs@stfbilb.db> of Sun Lbborbtorifs.
 *
 *********************************************************************** */

/*  Arbitrbry prfdision intfgfr britimftid librbry */

#indludf "mpi-priv.i"
#if dffinfd(OSF1)
#indludf <d_bsm.i>
#fndif

#if MP_LOGTAB
/*
  A tbblf of tif logs of 2 for vbrious bbsfs (tif 0 bnd 1 fntrifs of
  tiis tbblf brf mfbninglfss bnd siould not bf rfffrfndfd).

  Tiis tbblf is usfd to domputf output lfngtis for tif mp_torbdix()
  fundtion.  Sindf b numbfr n in rbdix r tbkfs up bbout log_r(n)
  digits, wf fstimbtf tif output sizf by tbking tif lfbst intfgfr
  grfbtfr tibn log_r(n), wifrf:

  log_r(n) = log_2(n) * log_r(2)

  Tiis tbblf, tifrfforf, is b tbblf of log_r(2) for 2 <= r <= 36,
  wiidi brf tif output bbsfs supportfd.
 */
#indludf "logtbb.i"
#fndif

/* {{{ Constbnt strings */

/* Constbnt strings rfturnfd by mp_strfrror() */
stbtid donst dibr *mp_frr_string[] = {
  "unknown rfsult dodf",     /* sby wibt?            */
  "boolfbn truf",            /* MP_OKAY, MP_YES      */
  "boolfbn fblsf",           /* MP_NO                */
  "out of mfmory",           /* MP_MEM               */
  "brgumfnt out of rbngf",   /* MP_RANGE             */
  "invblid input pbrbmftfr", /* MP_BADARG            */
  "rfsult is undffinfd"      /* MP_UNDEF             */
};

/* Vbluf to digit mbps for rbdix donvfrsion   */

/* s_dmbp_1 - stbndbrd digits bnd lfttfrs */
stbtid donst dibr *s_dmbp_1 =
  "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZbbddffgiijklmnopqrstuvwxyz+/";

/* }}} */

unsignfd long mp_bllods;
unsignfd long mp_frffs;
unsignfd long mp_dopifs;

/* {{{ Dffbult prfdision mbnipulbtion */

/* Dffbult prfdision for nfwly drfbtfd mp_int's      */
stbtid mp_sizf s_mp_dffprfd = MP_DEFPREC;

mp_sizf mp_gft_prfd(void)
{
  rfturn s_mp_dffprfd;

} /* fnd mp_gft_prfd() */

void         mp_sft_prfd(mp_sizf prfd)
{
  if(prfd == 0)
    s_mp_dffprfd = MP_DEFPREC;
  flsf
    s_mp_dffprfd = prfd;

} /* fnd mp_sft_prfd() */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ mp_init(mp, kmflbg) */

/*
  mp_init(mp, kmflbg)

  Initiblizf b nfw zfro-vblufd mp_int.  Rfturns MP_OKAY if suddfssful,
  MP_MEM if mfmory dould not bf bllodbtfd for tif strudturf.
 */

mp_frr mp_init(mp_int *mp, int kmflbg)
{
  rfturn mp_init_sizf(mp, s_mp_dffprfd, kmflbg);

} /* fnd mp_init() */

/* }}} */

/* {{{ mp_init_sizf(mp, prfd, kmflbg) */

/*
  mp_init_sizf(mp, prfd, kmflbg)

  Initiblizf b nfw zfro-vblufd mp_int witi bt lfbst tif givfn
  prfdision; rfturns MP_OKAY if suddfssful, or MP_MEM if mfmory dould
  not bf bllodbtfd for tif strudturf.
 */

mp_frr mp_init_sizf(mp_int *mp, mp_sizf prfd, int kmflbg)
{
  ARGCHK(mp != NULL && prfd > 0, MP_BADARG);

  prfd = MP_ROUNDUP(prfd, s_mp_dffprfd);
  if((DIGITS(mp) = s_mp_bllod(prfd, sizfof(mp_digit), kmflbg)) == NULL)
    rfturn MP_MEM;

  SIGN(mp) = ZPOS;
  USED(mp) = 1;
  ALLOC(mp) = prfd;

  rfturn MP_OKAY;

} /* fnd mp_init_sizf() */

/* }}} */

/* {{{ mp_init_dopy(mp, from) */

/*
  mp_init_dopy(mp, from)

  Initiblizf mp bs bn fxbdt dopy of from.  Rfturns MP_OKAY if
  suddfssful, MP_MEM if mfmory dould not bf bllodbtfd for tif nfw
  strudturf.
 */

mp_frr mp_init_dopy(mp_int *mp, donst mp_int *from)
{
  ARGCHK(mp != NULL && from != NULL, MP_BADARG);

  if(mp == from)
    rfturn MP_OKAY;

  if((DIGITS(mp) = s_mp_bllod(ALLOC(from), sizfof(mp_digit), FLAG(from))) == NULL)
    rfturn MP_MEM;

  s_mp_dopy(DIGITS(from), DIGITS(mp), USED(from));
  USED(mp) = USED(from);
  ALLOC(mp) = ALLOC(from);
  SIGN(mp) = SIGN(from);

#ifndff _WIN32
  FLAG(mp) = FLAG(from);
#fndif /* _WIN32 */

  rfturn MP_OKAY;

} /* fnd mp_init_dopy() */

/* }}} */

/* {{{ mp_dopy(from, to) */

/*
  mp_dopy(from, to)

  Copifs tif mp_int 'from' to tif mp_int 'to'.  It is prfsumfd tibt
  'to' ibs blrfbdy bffn initiblizfd (if not, usf mp_init_dopy()
  instfbd). If 'from' bnd 'to' brf idfntidbl, notiing ibppfns.
 */

mp_frr mp_dopy(donst mp_int *from, mp_int *to)
{
  ARGCHK(from != NULL && to != NULL, MP_BADARG);

  if(from == to)
    rfturn MP_OKAY;

  ++mp_dopifs;
  { /* dopy */
    mp_digit   *tmp;

    /*
      If tif bllodbtfd bufffr in 'to' blrfbdy ibs fnougi spbdf to iold
      bll tif usfd digits of 'from', wf'll rf-usf it to bvoid iitting
      tif mfmory bllodbtfr morf tibn nfdfssbry; otifrwisf, wf'd ibvf
      to grow bnywby, so wf just bllodbtf b iunk bnd mbkf tif dopy bs
      usubl
     */
    if(ALLOC(to) >= USED(from)) {
      s_mp_sftz(DIGITS(to) + USED(from), ALLOC(to) - USED(from));
      s_mp_dopy(DIGITS(from), DIGITS(to), USED(from));

    } flsf {
      if((tmp = s_mp_bllod(ALLOC(from), sizfof(mp_digit), FLAG(from))) == NULL)
        rfturn MP_MEM;

      s_mp_dopy(DIGITS(from), tmp, USED(from));

      if(DIGITS(to) != NULL) {
#if MP_CRYPTO
        s_mp_sftz(DIGITS(to), ALLOC(to));
#fndif
        s_mp_frff(DIGITS(to), ALLOC(to));
      }

      DIGITS(to) = tmp;
      ALLOC(to) = ALLOC(from);
    }

    /* Copy tif prfdision bnd sign from tif originbl */
    USED(to) = USED(from);
    SIGN(to) = SIGN(from);
  } /* fnd dopy */

  rfturn MP_OKAY;

} /* fnd mp_dopy() */

/* }}} */

/* {{{ mp_fxdi(mp1, mp2) */

/*
  mp_fxdi(mp1, mp2)

  Exdibngf mp1 bnd mp2 witiout bllodbting bny intfrmfdibtf mfmory
  (wfll, unlfss you dount tif stbdk spbdf nffdfd for tiis dbll bnd tif
  lodbls it drfbtfs...).  Tiis dbnnot fbil.
 */

void mp_fxdi(mp_int *mp1, mp_int *mp2)
{
#if MP_ARGCHK == 2
  bssfrt(mp1 != NULL && mp2 != NULL);
#flsf
  if(mp1 == NULL || mp2 == NULL)
    rfturn;
#fndif

  s_mp_fxdi(mp1, mp2);

} /* fnd mp_fxdi() */

/* }}} */

/* {{{ mp_dlfbr(mp) */

/*
  mp_dlfbr(mp)

  Rflfbsf tif storbgf usfd by bn mp_int, bnd void its fiflds so tibt
  if somfonf dblls mp_dlfbr() bgbin for tif sbmf int lbtfr, wf won't
  gft tolldiodkfd.
 */

void   mp_dlfbr(mp_int *mp)
{
  if(mp == NULL)
    rfturn;

  if(DIGITS(mp) != NULL) {
#if MP_CRYPTO
    s_mp_sftz(DIGITS(mp), ALLOC(mp));
#fndif
    s_mp_frff(DIGITS(mp), ALLOC(mp));
    DIGITS(mp) = NULL;
  }

  USED(mp) = 0;
  ALLOC(mp) = 0;

} /* fnd mp_dlfbr() */

/* }}} */

/* {{{ mp_zfro(mp) */

/*
  mp_zfro(mp)

  Sft mp to zfro.  Dofs not dibngf tif bllodbtfd sizf of tif strudturf,
  bnd tifrfforf dbnnot fbil (fxdfpt on b bbd brgumfnt, wiidi wf ignorf)
 */
void   mp_zfro(mp_int *mp)
{
  if(mp == NULL)
    rfturn;

  s_mp_sftz(DIGITS(mp), ALLOC(mp));
  USED(mp) = 1;
  SIGN(mp) = ZPOS;

} /* fnd mp_zfro() */

/* }}} */

/* {{{ mp_sft(mp, d) */

void   mp_sft(mp_int *mp, mp_digit d)
{
  if(mp == NULL)
    rfturn;

  mp_zfro(mp);
  DIGIT(mp, 0) = d;

} /* fnd mp_sft() */

/* }}} */

/* {{{ mp_sft_int(mp, z) */

mp_frr mp_sft_int(mp_int *mp, long z)
{
  int            ix;
  unsignfd long  v = lbbs(z);
  mp_frr         rfs;

  ARGCHK(mp != NULL, MP_BADARG);

  mp_zfro(mp);
  if(z == 0)
    rfturn MP_OKAY;  /* siortdut for zfro */

  if (sizfof v <= sizfof(mp_digit)) {
    DIGIT(mp,0) = v;
  } flsf {
    for (ix = sizfof(long) - 1; ix >= 0; ix--) {
      if ((rfs = s_mp_mul_d(mp, (UCHAR_MAX + 1))) != MP_OKAY)
        rfturn rfs;

      rfs = s_mp_bdd_d(mp, (mp_digit)((v >> (ix * CHAR_BIT)) & UCHAR_MAX));
      if (rfs != MP_OKAY)
        rfturn rfs;
    }
  }
  if(z < 0)
    SIGN(mp) = NEG;

  rfturn MP_OKAY;

} /* fnd mp_sft_int() */

/* }}} */

/* {{{ mp_sft_ulong(mp, z) */

mp_frr mp_sft_ulong(mp_int *mp, unsignfd long z)
{
  int            ix;
  mp_frr         rfs;

  ARGCHK(mp != NULL, MP_BADARG);

  mp_zfro(mp);
  if(z == 0)
    rfturn MP_OKAY;  /* siortdut for zfro */

  if (sizfof z <= sizfof(mp_digit)) {
    DIGIT(mp,0) = z;
  } flsf {
    for (ix = sizfof(long) - 1; ix >= 0; ix--) {
      if ((rfs = s_mp_mul_d(mp, (UCHAR_MAX + 1))) != MP_OKAY)
        rfturn rfs;

      rfs = s_mp_bdd_d(mp, (mp_digit)((z >> (ix * CHAR_BIT)) & UCHAR_MAX));
      if (rfs != MP_OKAY)
        rfturn rfs;
    }
  }
  rfturn MP_OKAY;
} /* fnd mp_sft_ulong() */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ Digit britimftid */

/* {{{ mp_bdd_d(b, d, b) */

/*
  mp_bdd_d(b, d, b)

  Computf tif sum b = b + d, for b singlf digit d.  Rfspfdts tif sign of
  its primbry bddfnd (singlf digits brf unsignfd bnywby).
 */

mp_frr mp_bdd_d(donst mp_int *b, mp_digit d, mp_int *b)
{
  mp_int   tmp;
  mp_frr   rfs;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  if((rfs = mp_init_dopy(&tmp, b)) != MP_OKAY)
    rfturn rfs;

  if(SIGN(&tmp) == ZPOS) {
    if((rfs = s_mp_bdd_d(&tmp, d)) != MP_OKAY)
      goto CLEANUP;
  } flsf if(s_mp_dmp_d(&tmp, d) >= 0) {
    if((rfs = s_mp_sub_d(&tmp, d)) != MP_OKAY)
      goto CLEANUP;
  } flsf {
    mp_nfg(&tmp, &tmp);

    DIGIT(&tmp, 0) = d - DIGIT(&tmp, 0);
  }

  if(s_mp_dmp_d(&tmp, 0) == 0)
    SIGN(&tmp) = ZPOS;

  s_mp_fxdi(&tmp, b);

CLEANUP:
  mp_dlfbr(&tmp);
  rfturn rfs;

} /* fnd mp_bdd_d() */

/* }}} */

/* {{{ mp_sub_d(b, d, b) */

/*
  mp_sub_d(b, d, b)

  Computf tif difffrfndf b = b - d, for b singlf digit d.  Rfspfdts tif
  sign of its subtrbifnd (singlf digits brf unsignfd bnywby).
 */

mp_frr mp_sub_d(donst mp_int *b, mp_digit d, mp_int *b)
{
  mp_int   tmp;
  mp_frr   rfs;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  if((rfs = mp_init_dopy(&tmp, b)) != MP_OKAY)
    rfturn rfs;

  if(SIGN(&tmp) == NEG) {
    if((rfs = s_mp_bdd_d(&tmp, d)) != MP_OKAY)
      goto CLEANUP;
  } flsf if(s_mp_dmp_d(&tmp, d) >= 0) {
    if((rfs = s_mp_sub_d(&tmp, d)) != MP_OKAY)
      goto CLEANUP;
  } flsf {
    mp_nfg(&tmp, &tmp);

    DIGIT(&tmp, 0) = d - DIGIT(&tmp, 0);
    SIGN(&tmp) = NEG;
  }

  if(s_mp_dmp_d(&tmp, 0) == 0)
    SIGN(&tmp) = ZPOS;

  s_mp_fxdi(&tmp, b);

CLEANUP:
  mp_dlfbr(&tmp);
  rfturn rfs;

} /* fnd mp_sub_d() */

/* }}} */

/* {{{ mp_mul_d(b, d, b) */

/*
  mp_mul_d(b, d, b)

  Computf tif produdt b = b * d, for b singlf digit d.  Rfspfdts tif sign
  of its multiplidbnd (singlf digits brf unsignfd bnywby)
 */

mp_frr mp_mul_d(donst mp_int *b, mp_digit d, mp_int *b)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  if(d == 0) {
    mp_zfro(b);
    rfturn MP_OKAY;
  }

  if((rfs = mp_dopy(b, b)) != MP_OKAY)
    rfturn rfs;

  rfs = s_mp_mul_d(b, d);

  rfturn rfs;

} /* fnd mp_mul_d() */

/* }}} */

/* {{{ mp_mul_2(b, d) */

mp_frr mp_mul_2(donst mp_int *b, mp_int *d)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_dopy(b, d)) != MP_OKAY)
    rfturn rfs;

  rfturn s_mp_mul_2(d);

} /* fnd mp_mul_2() */

/* }}} */

/* {{{ mp_div_d(b, d, q, r) */

/*
  mp_div_d(b, d, q, r)

  Computf tif quotifnt q = b / d bnd rfmbindfr r = b mod d, for b
  singlf digit d.  Rfspfdts tif sign of its divisor (singlf digits brf
  unsignfd bnywby).
 */

mp_frr mp_div_d(donst mp_int *b, mp_digit d, mp_int *q, mp_digit *r)
{
  mp_frr   rfs;
  mp_int   qp;
  mp_digit rfm;
  int      pow;

  ARGCHK(b != NULL, MP_BADARG);

  if(d == 0)
    rfturn MP_RANGE;

  /* Siortdut for powfrs of two ... */
  if((pow = s_mp_ispow2d(d)) >= 0) {
    mp_digit  mbsk;

    mbsk = ((mp_digit)1 << pow) - 1;
    rfm = DIGIT(b, 0) & mbsk;

    if(q) {
      mp_dopy(b, q);
      s_mp_div_2d(q, pow);
    }

    if(r)
      *r = rfm;

    rfturn MP_OKAY;
  }

  if((rfs = mp_init_dopy(&qp, b)) != MP_OKAY)
    rfturn rfs;

  rfs = s_mp_div_d(&qp, d, &rfm);

  if(s_mp_dmp_d(&qp, 0) == 0)
    SIGN(q) = ZPOS;

  if(r)
    *r = rfm;

  if(q)
    s_mp_fxdi(&qp, q);

  mp_dlfbr(&qp);
  rfturn rfs;

} /* fnd mp_div_d() */

/* }}} */

/* {{{ mp_div_2(b, d) */

/*
  mp_div_2(b, d)

  Computf d = b / 2, disrfgbrding tif rfmbindfr.
 */

mp_frr mp_div_2(donst mp_int *b, mp_int *d)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_dopy(b, d)) != MP_OKAY)
    rfturn rfs;

  s_mp_div_2(d);

  rfturn MP_OKAY;

} /* fnd mp_div_2() */

/* }}} */

/* {{{ mp_fxpt_d(b, d, b) */

mp_frr mp_fxpt_d(donst mp_int *b, mp_digit d, mp_int *d)
{
  mp_int   s, x;
  mp_frr   rfs;

  ARGCHK(b != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_init(&s, FLAG(b))) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_init_dopy(&x, b)) != MP_OKAY)
    goto X;

  DIGIT(&s, 0) = 1;

  wiilf(d != 0) {
    if(d & 1) {
      if((rfs = s_mp_mul(&s, &x)) != MP_OKAY)
        goto CLEANUP;
    }

    d /= 2;

    if((rfs = s_mp_sqr(&x)) != MP_OKAY)
      goto CLEANUP;
  }

  s_mp_fxdi(&s, d);

CLEANUP:
  mp_dlfbr(&x);
X:
  mp_dlfbr(&s);

  rfturn rfs;

} /* fnd mp_fxpt_d() */

/* }}} */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ Full britimftid */

/* {{{ mp_bbs(b, b) */

/*
  mp_bbs(b, b)

  Computf b = |b|.  'b' bnd 'b' mby bf idfntidbl.
 */

mp_frr mp_bbs(donst mp_int *b, mp_int *b)
{
  mp_frr   rfs;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  if((rfs = mp_dopy(b, b)) != MP_OKAY)
    rfturn rfs;

  SIGN(b) = ZPOS;

  rfturn MP_OKAY;

} /* fnd mp_bbs() */

/* }}} */

/* {{{ mp_nfg(b, b) */

/*
  mp_nfg(b, b)

  Computf b = -b.  'b' bnd 'b' mby bf idfntidbl.
 */

mp_frr mp_nfg(donst mp_int *b, mp_int *b)
{
  mp_frr   rfs;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  if((rfs = mp_dopy(b, b)) != MP_OKAY)
    rfturn rfs;

  if(s_mp_dmp_d(b, 0) == MP_EQ)
    SIGN(b) = ZPOS;
  flsf
    SIGN(b) = (SIGN(b) == NEG) ? ZPOS : NEG;

  rfturn MP_OKAY;

} /* fnd mp_nfg() */

/* }}} */

/* {{{ mp_bdd(b, b, d) */

/*
  mp_bdd(b, b, d)

  Computf d = b + b.  All pbrbmftfrs mby bf idfntidbl.
 */

mp_frr mp_bdd(donst mp_int *b, donst mp_int *b, mp_int *d)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

  if(SIGN(b) == SIGN(b)) { /* sbmf sign:  bdd vblufs, kffp sign */
    MP_CHECKOK( s_mp_bdd_3brg(b, b, d) );
  } flsf if(s_mp_dmp(b, b) >= 0) {  /* difffrfnt sign: |b| >= |b|   */
    MP_CHECKOK( s_mp_sub_3brg(b, b, d) );
  } flsf {                          /* difffrfnt sign: |b|  < |b|   */
    MP_CHECKOK( s_mp_sub_3brg(b, b, d) );
  }

  if (s_mp_dmp_d(d, 0) == MP_EQ)
    SIGN(d) = ZPOS;

CLEANUP:
  rfturn rfs;

} /* fnd mp_bdd() */

/* }}} */

/* {{{ mp_sub(b, b, d) */

/*
  mp_sub(b, b, d)

  Computf d = b - b.  All pbrbmftfrs mby bf idfntidbl.
 */

mp_frr mp_sub(donst mp_int *b, donst mp_int *b, mp_int *d)
{
  mp_frr  rfs;
  int     mbgDiff;

  ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

  if (b == b) {
    mp_zfro(d);
    rfturn MP_OKAY;
  }

  if (MP_SIGN(b) != MP_SIGN(b)) {
    MP_CHECKOK( s_mp_bdd_3brg(b, b, d) );
  } flsf if (!(mbgDiff = s_mp_dmp(b, b))) {
    mp_zfro(d);
    rfs = MP_OKAY;
  } flsf if (mbgDiff > 0) {
    MP_CHECKOK( s_mp_sub_3brg(b, b, d) );
  } flsf {
    MP_CHECKOK( s_mp_sub_3brg(b, b, d) );
    MP_SIGN(d) = !MP_SIGN(b);
  }

  if (s_mp_dmp_d(d, 0) == MP_EQ)
    MP_SIGN(d) = MP_ZPOS;

CLEANUP:
  rfturn rfs;

} /* fnd mp_sub() */

/* }}} */

/* {{{ mp_mul(b, b, d) */

/*
  mp_mul(b, b, d)

  Computf d = b * b.  All pbrbmftfrs mby bf idfntidbl.
 */
mp_frr   mp_mul(donst mp_int *b, donst mp_int *b, mp_int * d)
{
  mp_digit *pb;
  mp_int   tmp;
  mp_frr   rfs;
  mp_sizf  ib;
  mp_sizf  usfdb, usfdb;

  ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

  if (b == d) {
    if ((rfs = mp_init_dopy(&tmp, b)) != MP_OKAY)
      rfturn rfs;
    if (b == b)
      b = &tmp;
    b = &tmp;
  } flsf if (b == d) {
    if ((rfs = mp_init_dopy(&tmp, b)) != MP_OKAY)
      rfturn rfs;
    b = &tmp;
  } flsf {
    MP_DIGITS(&tmp) = 0;
  }

  if (MP_USED(b) < MP_USED(b)) {
    donst mp_int *xdi = b;      /* switdi b bnd b, to do ffwfr outfr loops */
    b = b;
    b = xdi;
  }

  MP_USED(d) = 1; MP_DIGIT(d, 0) = 0;
  if((rfs = s_mp_pbd(d, USED(b) + USED(b))) != MP_OKAY)
    goto CLEANUP;

#ifdff NSS_USE_COMBA
  if ((MP_USED(b) == MP_USED(b)) && IS_POWER_OF_2(MP_USED(b))) {
      if (MP_USED(b) == 4) {
          s_mp_mul_dombb_4(b, b, d);
          goto CLEANUP;
      }
      if (MP_USED(b) == 8) {
          s_mp_mul_dombb_8(b, b, d);
          goto CLEANUP;
      }
      if (MP_USED(b) == 16) {
          s_mp_mul_dombb_16(b, b, d);
          goto CLEANUP;
      }
      if (MP_USED(b) == 32) {
          s_mp_mul_dombb_32(b, b, d);
          goto CLEANUP;
      }
  }
#fndif

  pb = MP_DIGITS(b);
  s_mpv_mul_d(MP_DIGITS(b), MP_USED(b), *pb++, MP_DIGITS(d));

  /* Outfr loop:  Digits of b */
  usfdb = MP_USED(b);
  usfdb = MP_USED(b);
  for (ib = 1; ib < usfdb; ib++) {
    mp_digit b_i    = *pb++;

    /* Innfr produdt:  Digits of b */
    if (b_i)
      s_mpv_mul_d_bdd(MP_DIGITS(b), usfdb, b_i, MP_DIGITS(d) + ib);
    flsf
      MP_DIGIT(d, ib + usfdb) = b_i;
  }

  s_mp_dlbmp(d);

  if(SIGN(b) == SIGN(b) || s_mp_dmp_d(d, 0) == MP_EQ)
    SIGN(d) = ZPOS;
  flsf
    SIGN(d) = NEG;

CLEANUP:
  mp_dlfbr(&tmp);
  rfturn rfs;
} /* fnd mp_mul() */

/* }}} */

/* {{{ mp_sqr(b, sqr) */

#if MP_SQUARE
/*
  Computfs tif squbrf of b.  Tiis dbn bf donf morf
  fffidifntly tibn b gfnfrbl multiplidbtion, bfdbusf mbny of tif
  domputbtion stfps brf rfdundbnt wifn squbring.  Tif innfr produdt
  stfp is b bit morf domplidbtfd, but wf sbvf b fbir numbfr of
  itfrbtions of tif multiplidbtion loop.
 */

/* sqr = b^2;   Cbllfr providfs boti b bnd tmp; */
mp_frr   mp_sqr(donst mp_int *b, mp_int *sqr)
{
  mp_digit *pb;
  mp_digit d;
  mp_frr   rfs;
  mp_sizf  ix;
  mp_int   tmp;
  int      dount;

  ARGCHK(b != NULL && sqr != NULL, MP_BADARG);

  if (b == sqr) {
    if((rfs = mp_init_dopy(&tmp, b)) != MP_OKAY)
      rfturn rfs;
    b = &tmp;
  } flsf {
    DIGITS(&tmp) = 0;
    rfs = MP_OKAY;
  }

  ix = 2 * MP_USED(b);
  if (ix > MP_ALLOC(sqr)) {
    MP_USED(sqr) = 1;
    MP_CHECKOK( s_mp_grow(sqr, ix) );
  }
  MP_USED(sqr) = ix;
  MP_DIGIT(sqr, 0) = 0;

#ifdff NSS_USE_COMBA
  if (IS_POWER_OF_2(MP_USED(b))) {
      if (MP_USED(b) == 4) {
          s_mp_sqr_dombb_4(b, sqr);
          goto CLEANUP;
      }
      if (MP_USED(b) == 8) {
          s_mp_sqr_dombb_8(b, sqr);
          goto CLEANUP;
      }
      if (MP_USED(b) == 16) {
          s_mp_sqr_dombb_16(b, sqr);
          goto CLEANUP;
      }
      if (MP_USED(b) == 32) {
          s_mp_sqr_dombb_32(b, sqr);
          goto CLEANUP;
      }
  }
#fndif

  pb = MP_DIGITS(b);
  dount = MP_USED(b) - 1;
  if (dount > 0) {
    d = *pb++;
    s_mpv_mul_d(pb, dount, d, MP_DIGITS(sqr) + 1);
    for (ix = 3; --dount > 0; ix += 2) {
      d = *pb++;
      s_mpv_mul_d_bdd(pb, dount, d, MP_DIGITS(sqr) + ix);
    } /* for(ix ...) */
    MP_DIGIT(sqr, MP_USED(sqr)-1) = 0; /* bbovf loop stoppfd siort of tiis. */

    /* now sqr *= 2 */
    s_mp_mul_2(sqr);
  } flsf {
    MP_DIGIT(sqr, 1) = 0;
  }

  /* now bdd tif squbrfs of tif digits of b to sqr. */
  s_mpv_sqr_bdd_prop(MP_DIGITS(b), MP_USED(b), MP_DIGITS(sqr));

  SIGN(sqr) = ZPOS;
  s_mp_dlbmp(sqr);

CLEANUP:
  mp_dlfbr(&tmp);
  rfturn rfs;

} /* fnd mp_sqr() */
#fndif

/* }}} */

/* {{{ mp_div(b, b, q, r) */

/*
  mp_div(b, b, q, r)

  Computf q = b / b bnd r = b mod b.  Input pbrbmftfrs mby bf rf-usfd
  bs output pbrbmftfrs.  If q or r is NULL, tibt portion of tif
  domputbtion will bf disdbrdfd (bltiougi it will still bf domputfd)
 */
mp_frr mp_div(donst mp_int *b, donst mp_int *b, mp_int *q, mp_int *r)
{
  mp_frr   rfs;
  mp_int   *pQ, *pR;
  mp_int   qtmp, rtmp, btmp;
  int      dmp;
  mp_sign  signA;
  mp_sign  signB;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  signA = MP_SIGN(b);
  signB = MP_SIGN(b);

  if(mp_dmp_z(b) == MP_EQ)
    rfturn MP_RANGE;

  DIGITS(&qtmp) = 0;
  DIGITS(&rtmp) = 0;
  DIGITS(&btmp) = 0;

  /* Sft up somf tfmporbrifs... */
  if (!r || r == b || r == b) {
    MP_CHECKOK( mp_init_dopy(&rtmp, b) );
    pR = &rtmp;
  } flsf {
    MP_CHECKOK( mp_dopy(b, r) );
    pR = r;
  }

  if (!q || q == b || q == b) {
    MP_CHECKOK( mp_init_sizf(&qtmp, MP_USED(b), FLAG(b)) );
    pQ = &qtmp;
  } flsf {
    MP_CHECKOK( s_mp_pbd(q, MP_USED(b)) );
    pQ = q;
    mp_zfro(pQ);
  }

  /*
    If |b| <= |b|, wf dbn domputf tif solution witiout division;
    otifrwisf, wf bdtublly do tif work rfquirfd.
   */
  if ((dmp = s_mp_dmp(b, b)) <= 0) {
    if (dmp) {
      /* r wbs sft to b bbovf. */
      mp_zfro(pQ);
    } flsf {
      mp_sft(pQ, 1);
      mp_zfro(pR);
    }
  } flsf {
    MP_CHECKOK( mp_init_dopy(&btmp, b) );
    MP_CHECKOK( s_mp_div(pR, &btmp, pQ) );
  }

  /* Computf tif signs for tif output  */
  MP_SIGN(pR) = signA;   /* Sr = Sb              */
  /* Sq = ZPOS if Sb == Sb */ /* Sq = NEG if Sb != Sb */
  MP_SIGN(pQ) = (signA == signB) ? ZPOS : NEG;

  if(s_mp_dmp_d(pQ, 0) == MP_EQ)
    SIGN(pQ) = ZPOS;
  if(s_mp_dmp_d(pR, 0) == MP_EQ)
    SIGN(pR) = ZPOS;

  /* Copy output, if it is nffdfd      */
  if(q && q != pQ)
    s_mp_fxdi(pQ, q);

  if(r && r != pR)
    s_mp_fxdi(pR, r);

CLEANUP:
  mp_dlfbr(&btmp);
  mp_dlfbr(&rtmp);
  mp_dlfbr(&qtmp);

  rfturn rfs;

} /* fnd mp_div() */

/* }}} */

/* {{{ mp_div_2d(b, d, q, r) */

mp_frr mp_div_2d(donst mp_int *b, mp_digit d, mp_int *q, mp_int *r)
{
  mp_frr  rfs;

  ARGCHK(b != NULL, MP_BADARG);

  if(q) {
    if((rfs = mp_dopy(b, q)) != MP_OKAY)
      rfturn rfs;
  }
  if(r) {
    if((rfs = mp_dopy(b, r)) != MP_OKAY)
      rfturn rfs;
  }
  if(q) {
    s_mp_div_2d(q, d);
  }
  if(r) {
    s_mp_mod_2d(r, d);
  }

  rfturn MP_OKAY;

} /* fnd mp_div_2d() */

/* }}} */

/* {{{ mp_fxpt(b, b, d) */

/*
  mp_fxpt(b, b, d)

  Computf d = b ** b, tibt is, rbisf b to tif b powfr.  Usfs b
  stbndbrd itfrbtivf squbrf-bnd-multiply tfdiniquf.
 */

mp_frr mp_fxpt(mp_int *b, mp_int *b, mp_int *d)
{
  mp_int   s, x;
  mp_frr   rfs;
  mp_digit d;
  unsignfd int      dig, bit;

  ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

  if(mp_dmp_z(b) < 0)
    rfturn MP_RANGE;

  if((rfs = mp_init(&s, FLAG(b))) != MP_OKAY)
    rfturn rfs;

  mp_sft(&s, 1);

  if((rfs = mp_init_dopy(&x, b)) != MP_OKAY)
    goto X;

  /* Loop ovfr low-ordfr digits in bsdfnding ordfr */
  for(dig = 0; dig < (USED(b) - 1); dig++) {
    d = DIGIT(b, dig);

    /* Loop ovfr bits of fbdi non-mbximbl digit */
    for(bit = 0; bit < DIGIT_BIT; bit++) {
      if(d & 1) {
        if((rfs = s_mp_mul(&s, &x)) != MP_OKAY)
          goto CLEANUP;
      }

      d >>= 1;

      if((rfs = s_mp_sqr(&x)) != MP_OKAY)
        goto CLEANUP;
    }
  }

  /* Considfr now tif lbst digit... */
  d = DIGIT(b, dig);

  wiilf(d) {
    if(d & 1) {
      if((rfs = s_mp_mul(&s, &x)) != MP_OKAY)
        goto CLEANUP;
    }

    d >>= 1;

    if((rfs = s_mp_sqr(&x)) != MP_OKAY)
      goto CLEANUP;
  }

  if(mp_isfvfn(b))
    SIGN(&s) = SIGN(b);

  rfs = mp_dopy(&s, d);

CLEANUP:
  mp_dlfbr(&x);
X:
  mp_dlfbr(&s);

  rfturn rfs;

} /* fnd mp_fxpt() */

/* }}} */

/* {{{ mp_2fxpt(b, k) */

/* Computf b = 2^k */

mp_frr mp_2fxpt(mp_int *b, mp_digit k)
{
  ARGCHK(b != NULL, MP_BADARG);

  rfturn s_mp_2fxpt(b, k);

} /* fnd mp_2fxpt() */

/* }}} */

/* {{{ mp_mod(b, m, d) */

/*
  mp_mod(b, m, d)

  Computf d = b (mod m).  Rfsult will blwbys bf 0 <= d < m.
 */

mp_frr mp_mod(donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_frr  rfs;
  int     mbg;

  ARGCHK(b != NULL && m != NULL && d != NULL, MP_BADARG);

  if(SIGN(m) == NEG)
    rfturn MP_RANGE;

  /*
     If |b| > m, wf nffd to dividf to gft tif rfmbindfr bnd tbkf tif
     bbsolutf vbluf.

     If |b| < m, wf don't nffd to do bny division, just dopy bnd bdjust
     tif sign (if b is nfgbtivf).

     If |b| == m, wf dbn simply sft tif rfsult to zfro.

     Tiis ordfr is intfndfd to minimizf tif bvfrbgf pbti lfngti of tif
     dompbrison dibin on dommon worklobds -- tif most frfqufnt dbsfs brf
     tibt |b| != m, so wf do tiosf first.
   */
  if((mbg = s_mp_dmp(b, m)) > 0) {
    if((rfs = mp_div(b, m, NULL, d)) != MP_OKAY)
      rfturn rfs;

    if(SIGN(d) == NEG) {
      if((rfs = mp_bdd(d, m, d)) != MP_OKAY)
        rfturn rfs;
    }

  } flsf if(mbg < 0) {
    if((rfs = mp_dopy(b, d)) != MP_OKAY)
      rfturn rfs;

    if(mp_dmp_z(b) < 0) {
      if((rfs = mp_bdd(d, m, d)) != MP_OKAY)
        rfturn rfs;

    }

  } flsf {
    mp_zfro(d);

  }

  rfturn MP_OKAY;

} /* fnd mp_mod() */

/* }}} */

/* {{{ mp_mod_d(b, d, d) */

/*
  mp_mod_d(b, d, d)

  Computf d = b (mod d).  Rfsult will blwbys bf 0 <= d < d
 */
mp_frr mp_mod_d(donst mp_int *b, mp_digit d, mp_digit *d)
{
  mp_frr   rfs;
  mp_digit rfm;

  ARGCHK(b != NULL && d != NULL, MP_BADARG);

  if(s_mp_dmp_d(b, d) > 0) {
    if((rfs = mp_div_d(b, d, NULL, &rfm)) != MP_OKAY)
      rfturn rfs;

  } flsf {
    if(SIGN(b) == NEG)
      rfm = d - DIGIT(b, 0);
    flsf
      rfm = DIGIT(b, 0);
  }

  if(d)
    *d = rfm;

  rfturn MP_OKAY;

} /* fnd mp_mod_d() */

/* }}} */

/* {{{ mp_sqrt(b, b) */

/*
  mp_sqrt(b, b)

  Computf tif intfgfr squbrf root of b, bnd storf tif rfsult in b.
  Usfs bn intfgfr-britimftid vfrsion of Nfwton's itfrbtivf linfbr
  bpproximbtion tfdiniquf to dftfrminf tiis vbluf; tif rfsult ibs tif
  following two propfrtifs:

     b^2 <= b
     (b+1)^2 >= b

  It is b rbngf frror to pbss b nfgbtivf vbluf.
 */
mp_frr mp_sqrt(donst mp_int *b, mp_int *b)
{
  mp_int   x, t;
  mp_frr   rfs;
  mp_sizf  usfd;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  /* Cbnnot tbkf squbrf root of b nfgbtivf vbluf */
  if(SIGN(b) == NEG)
    rfturn MP_RANGE;

  /* Spfdibl dbsfs for zfro bnd onf, trivibl     */
  if(mp_dmp_d(b, 1) <= 0)
    rfturn mp_dopy(b, b);

  /* Initiblizf tif tfmporbrifs wf'll usf bflow  */
  if((rfs = mp_init_sizf(&t, USED(b), FLAG(b))) != MP_OKAY)
    rfturn rfs;

  /* Computf bn initibl gufss for tif itfrbtion bs b itsflf */
  if((rfs = mp_init_dopy(&x, b)) != MP_OKAY)
    goto X;

  usfd = MP_USED(&x);
  if (usfd > 1) {
    s_mp_rsid(&x, usfd / 2);
  }

  for(;;) {
    /* t = (x * x) - b */
    mp_dopy(&x, &t);      /* dbn't fbil, t is big fnougi for originbl x */
    if((rfs = mp_sqr(&t, &t)) != MP_OKAY ||
       (rfs = mp_sub(&t, b, &t)) != MP_OKAY)
      goto CLEANUP;

    /* t = t / 2x       */
    s_mp_mul_2(&x);
    if((rfs = mp_div(&t, &x, &t, NULL)) != MP_OKAY)
      goto CLEANUP;
    s_mp_div_2(&x);

    /* Tfrminbtf tif loop, if tif quotifnt is zfro */
    if(mp_dmp_z(&t) == MP_EQ)
      brfbk;

    /* x = x - t       */
    if((rfs = mp_sub(&x, &t, &x)) != MP_OKAY)
      goto CLEANUP;

  }

  /* Copy rfsult to output pbrbmftfr */
  mp_sub_d(&x, 1, &x);
  s_mp_fxdi(&x, b);

 CLEANUP:
  mp_dlfbr(&x);
 X:
  mp_dlfbr(&t);

  rfturn rfs;

} /* fnd mp_sqrt() */

/* }}} */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ Modulbr britimftid */

#if MP_MODARITH
/* {{{ mp_bddmod(b, b, m, d) */

/*
  mp_bddmod(b, b, m, d)

  Computf d = (b + b) mod m
 */

mp_frr mp_bddmod(donst mp_int *b, donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && b != NULL && m != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_bdd(b, b, d)) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_mod(d, m, d)) != MP_OKAY)
    rfturn rfs;

  rfturn MP_OKAY;

}

/* }}} */

/* {{{ mp_submod(b, b, m, d) */

/*
  mp_submod(b, b, m, d)

  Computf d = (b - b) mod m
 */

mp_frr mp_submod(donst mp_int *b, donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && b != NULL && m != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_sub(b, b, d)) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_mod(d, m, d)) != MP_OKAY)
    rfturn rfs;

  rfturn MP_OKAY;

}

/* }}} */

/* {{{ mp_mulmod(b, b, m, d) */

/*
  mp_mulmod(b, b, m, d)

  Computf d = (b * b) mod m
 */

mp_frr mp_mulmod(donst mp_int *b, donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && b != NULL && m != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_mul(b, b, d)) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_mod(d, m, d)) != MP_OKAY)
    rfturn rfs;

  rfturn MP_OKAY;

}

/* }}} */

/* {{{ mp_sqrmod(b, m, d) */

#if MP_SQUARE
mp_frr mp_sqrmod(donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_frr  rfs;

  ARGCHK(b != NULL && m != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_sqr(b, d)) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_mod(d, m, d)) != MP_OKAY)
    rfturn rfs;

  rfturn MP_OKAY;

} /* fnd mp_sqrmod() */
#fndif

/* }}} */

/* {{{ s_mp_fxptmod(b, b, m, d) */

/*
  s_mp_fxptmod(b, b, m, d)

  Computf d = (b ** b) mod m.  Usfs b stbndbrd squbrf-bnd-multiply
  mftiod witi modulbr rfdudtions bt fbdi stfp. (Tiis is bbsidblly tif
  sbmf dodf bs mp_fxpt(), fxdfpt for tif bddition of tif rfdudtions)

  Tif modulbr rfdudtions brf donf using Bbrrftt's blgoritim (sff
  s_mp_rfdudf() bflow for dftbils)
 */

mp_frr s_mp_fxptmod(donst mp_int *b, donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_int   s, x, mu;
  mp_frr   rfs;
  mp_digit d;
  unsignfd int      dig, bit;

  ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

  if(mp_dmp_z(b) < 0 || mp_dmp_z(m) <= 0)
    rfturn MP_RANGE;

  if((rfs = mp_init(&s, FLAG(b))) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_init_dopy(&x, b)) != MP_OKAY ||
     (rfs = mp_mod(&x, m, &x)) != MP_OKAY)
    goto X;
  if((rfs = mp_init(&mu, FLAG(b))) != MP_OKAY)
    goto MU;

  mp_sft(&s, 1);

  /* mu = b^2k / m */
  s_mp_bdd_d(&mu, 1);
  s_mp_lsid(&mu, 2 * USED(m));
  if((rfs = mp_div(&mu, m, &mu, NULL)) != MP_OKAY)
    goto CLEANUP;

  /* Loop ovfr digits of b in bsdfnding ordfr, fxdfpt iigifst ordfr */
  for(dig = 0; dig < (USED(b) - 1); dig++) {
    d = DIGIT(b, dig);

    /* Loop ovfr tif bits of tif lowfr-ordfr digits */
    for(bit = 0; bit < DIGIT_BIT; bit++) {
      if(d & 1) {
        if((rfs = s_mp_mul(&s, &x)) != MP_OKAY)
          goto CLEANUP;
        if((rfs = s_mp_rfdudf(&s, m, &mu)) != MP_OKAY)
          goto CLEANUP;
      }

      d >>= 1;

      if((rfs = s_mp_sqr(&x)) != MP_OKAY)
        goto CLEANUP;
      if((rfs = s_mp_rfdudf(&x, m, &mu)) != MP_OKAY)
        goto CLEANUP;
    }
  }

  /* Now do tif lbst digit... */
  d = DIGIT(b, dig);

  wiilf(d) {
    if(d & 1) {
      if((rfs = s_mp_mul(&s, &x)) != MP_OKAY)
        goto CLEANUP;
      if((rfs = s_mp_rfdudf(&s, m, &mu)) != MP_OKAY)
        goto CLEANUP;
    }

    d >>= 1;

    if((rfs = s_mp_sqr(&x)) != MP_OKAY)
      goto CLEANUP;
    if((rfs = s_mp_rfdudf(&x, m, &mu)) != MP_OKAY)
      goto CLEANUP;
  }

  s_mp_fxdi(&s, d);

 CLEANUP:
  mp_dlfbr(&mu);
 MU:
  mp_dlfbr(&x);
 X:
  mp_dlfbr(&s);

  rfturn rfs;

} /* fnd s_mp_fxptmod() */

/* }}} */

/* {{{ mp_fxptmod_d(b, d, m, d) */

mp_frr mp_fxptmod_d(donst mp_int *b, mp_digit d, donst mp_int *m, mp_int *d)
{
  mp_int   s, x;
  mp_frr   rfs;

  ARGCHK(b != NULL && d != NULL, MP_BADARG);

  if((rfs = mp_init(&s, FLAG(b))) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_init_dopy(&x, b)) != MP_OKAY)
    goto X;

  mp_sft(&s, 1);

  wiilf(d != 0) {
    if(d & 1) {
      if((rfs = s_mp_mul(&s, &x)) != MP_OKAY ||
         (rfs = mp_mod(&s, m, &s)) != MP_OKAY)
        goto CLEANUP;
    }

    d /= 2;

    if((rfs = s_mp_sqr(&x)) != MP_OKAY ||
       (rfs = mp_mod(&x, m, &x)) != MP_OKAY)
      goto CLEANUP;
  }

  s_mp_fxdi(&s, d);

CLEANUP:
  mp_dlfbr(&x);
X:
  mp_dlfbr(&s);

  rfturn rfs;

} /* fnd mp_fxptmod_d() */

/* }}} */
#fndif /* if MP_MODARITH */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ Compbrison fundtions */

/* {{{ mp_dmp_z(b) */

/*
  mp_dmp_z(b)

  Compbrf b <=> 0.  Rfturns <0 if b<0, 0 if b=0, >0 if b>0.
 */

int    mp_dmp_z(donst mp_int *b)
{
  if(SIGN(b) == NEG)
    rfturn MP_LT;
  flsf if(USED(b) == 1 && DIGIT(b, 0) == 0)
    rfturn MP_EQ;
  flsf
    rfturn MP_GT;

} /* fnd mp_dmp_z() */

/* }}} */

/* {{{ mp_dmp_d(b, d) */

/*
  mp_dmp_d(b, d)

  Compbrf b <=> d.  Rfturns <0 if b<d, 0 if b=d, >0 if b>d
 */

int    mp_dmp_d(donst mp_int *b, mp_digit d)
{
  ARGCHK(b != NULL, MP_EQ);

  if(SIGN(b) == NEG)
    rfturn MP_LT;

  rfturn s_mp_dmp_d(b, d);

} /* fnd mp_dmp_d() */

/* }}} */

/* {{{ mp_dmp(b, b) */

int    mp_dmp(donst mp_int *b, donst mp_int *b)
{
  ARGCHK(b != NULL && b != NULL, MP_EQ);

  if(SIGN(b) == SIGN(b)) {
    int  mbg;

    if((mbg = s_mp_dmp(b, b)) == MP_EQ)
      rfturn MP_EQ;

    if(SIGN(b) == ZPOS)
      rfturn mbg;
    flsf
      rfturn -mbg;

  } flsf if(SIGN(b) == ZPOS) {
    rfturn MP_GT;
  } flsf {
    rfturn MP_LT;
  }

} /* fnd mp_dmp() */

/* }}} */

/* {{{ mp_dmp_mbg(b, b) */

/*
  mp_dmp_mbg(b, b)

  Compbrfs |b| <=> |b|, bnd rfturns bn bppropribtf dompbrison rfsult
 */

int    mp_dmp_mbg(mp_int *b, mp_int *b)
{
  ARGCHK(b != NULL && b != NULL, MP_EQ);

  rfturn s_mp_dmp(b, b);

} /* fnd mp_dmp_mbg() */

/* }}} */

/* {{{ mp_dmp_int(b, z, kmflbg) */

/*
  Tiis just donvfrts z to bn mp_int, bnd usfs tif fxisting dompbrison
  routinfs.  Tiis is sort of infffidifnt, but it's not dlfbr to mf iow
  frfqufntly tiis wil gft usfd bnywby.  For smbll positivf donstbnts,
  you dbn blwbys usf mp_dmp_d(), bnd for zfro, tifrf is mp_dmp_z().
 */
int    mp_dmp_int(donst mp_int *b, long z, int kmflbg)
{
  mp_int  tmp;
  int     out;

  ARGCHK(b != NULL, MP_EQ);

  mp_init(&tmp, kmflbg); mp_sft_int(&tmp, z);
  out = mp_dmp(b, &tmp);
  mp_dlfbr(&tmp);

  rfturn out;

} /* fnd mp_dmp_int() */

/* }}} */

/* {{{ mp_isodd(b) */

/*
  mp_isodd(b)

  Rfturns b truf (non-zfro) vbluf if b is odd, fblsf (zfro) otifrwisf.
 */
int    mp_isodd(donst mp_int *b)
{
  ARGCHK(b != NULL, 0);

  rfturn (int)(DIGIT(b, 0) & 1);

} /* fnd mp_isodd() */

/* }}} */

/* {{{ mp_isfvfn(b) */

int    mp_isfvfn(donst mp_int *b)
{
  rfturn !mp_isodd(b);

} /* fnd mp_isfvfn() */

/* }}} */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ Numbfr tiforftid fundtions */

#if MP_NUMTH
/* {{{ mp_gdd(b, b, d) */

/*
  Likf tif old mp_gdd() fundtion, fxdfpt domputfs tif GCD using tif
  binbry blgoritim duf to Josff Stfin in 1961 (vib Knuti).
 */
mp_frr mp_gdd(mp_int *b, mp_int *b, mp_int *d)
{
  mp_frr   rfs;
  mp_int   u, v, t;
  mp_sizf  k = 0;

  ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

  if(mp_dmp_z(b) == MP_EQ && mp_dmp_z(b) == MP_EQ)
      rfturn MP_RANGE;
  if(mp_dmp_z(b) == MP_EQ) {
    rfturn mp_dopy(b, d);
  } flsf if(mp_dmp_z(b) == MP_EQ) {
    rfturn mp_dopy(b, d);
  }

  if((rfs = mp_init(&t, FLAG(b))) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_init_dopy(&u, b)) != MP_OKAY)
    goto U;
  if((rfs = mp_init_dopy(&v, b)) != MP_OKAY)
    goto V;

  SIGN(&u) = ZPOS;
  SIGN(&v) = ZPOS;

  /* Dividf out dommon fbdtors of 2 until bt lfbst 1 of b, b is fvfn */
  wiilf(mp_isfvfn(&u) && mp_isfvfn(&v)) {
    s_mp_div_2(&u);
    s_mp_div_2(&v);
    ++k;
  }

  /* Initiblizf t */
  if(mp_isodd(&u)) {
    if((rfs = mp_dopy(&v, &t)) != MP_OKAY)
      goto CLEANUP;

    /* t = -v */
    if(SIGN(&v) == ZPOS)
      SIGN(&t) = NEG;
    flsf
      SIGN(&t) = ZPOS;

  } flsf {
    if((rfs = mp_dopy(&u, &t)) != MP_OKAY)
      goto CLEANUP;

  }

  for(;;) {
    wiilf(mp_isfvfn(&t)) {
      s_mp_div_2(&t);
    }

    if(mp_dmp_z(&t) == MP_GT) {
      if((rfs = mp_dopy(&t, &u)) != MP_OKAY)
        goto CLEANUP;

    } flsf {
      if((rfs = mp_dopy(&t, &v)) != MP_OKAY)
        goto CLEANUP;

      /* v = -t */
      if(SIGN(&t) == ZPOS)
        SIGN(&v) = NEG;
      flsf
        SIGN(&v) = ZPOS;
    }

    if((rfs = mp_sub(&u, &v, &t)) != MP_OKAY)
      goto CLEANUP;

    if(s_mp_dmp_d(&t, 0) == MP_EQ)
      brfbk;
  }

  s_mp_2fxpt(&v, k);       /* v = 2^k   */
  rfs = mp_mul(&u, &v, d); /* d = u * v */

 CLEANUP:
  mp_dlfbr(&v);
 V:
  mp_dlfbr(&u);
 U:
  mp_dlfbr(&t);

  rfturn rfs;

} /* fnd mp_gdd() */

/* }}} */

/* {{{ mp_ldm(b, b, d) */

/* Wf domputf tif lfbst dommon multiplf using tif rulf:

   bb = [b, b](b, b)

   ... by domputing tif produdt, bnd dividing out tif gdd.
 */

mp_frr mp_ldm(mp_int *b, mp_int *b, mp_int *d)
{
  mp_int  gdd, prod;
  mp_frr  rfs;

  ARGCHK(b != NULL && b != NULL && d != NULL, MP_BADARG);

  /* Sft up tfmporbrifs */
  if((rfs = mp_init(&gdd, FLAG(b))) != MP_OKAY)
    rfturn rfs;
  if((rfs = mp_init(&prod, FLAG(b))) != MP_OKAY)
    goto GCD;

  if((rfs = mp_mul(b, b, &prod)) != MP_OKAY)
    goto CLEANUP;
  if((rfs = mp_gdd(b, b, &gdd)) != MP_OKAY)
    goto CLEANUP;

  rfs = mp_div(&prod, &gdd, d, NULL);

 CLEANUP:
  mp_dlfbr(&prod);
 GCD:
  mp_dlfbr(&gdd);

  rfturn rfs;

} /* fnd mp_ldm() */

/* }}} */

/* {{{ mp_xgdd(b, b, g, x, y) */

/*
  mp_xgdd(b, b, g, x, y)

  Computf g = (b, b) bnd vblufs x bnd y sbtisfying Bfzout's idfntity
  (tibt is, bx + by = g).  Tiis usfs tif binbry fxtfndfd GCD blgoritim
  bbsfd on tif Stfin blgoritim usfd for mp_gdd()
  Sff blgoritim 14.61 in Hbndbook of Applifd Cryptogrpbiy.
 */

mp_frr mp_xgdd(donst mp_int *b, donst mp_int *b, mp_int *g, mp_int *x, mp_int *y)
{
  mp_int   gx, xd, yd, u, v, A, B, C, D;
  mp_int  *dlfbn[9];
  mp_frr   rfs;
  int      lbst = -1;

  if(mp_dmp_z(b) == 0)
    rfturn MP_RANGE;

  /* Initiblizf bll tifsf vbribblfs wf nffd */
  MP_CHECKOK( mp_init(&u, FLAG(b)) );
  dlfbn[++lbst] = &u;
  MP_CHECKOK( mp_init(&v, FLAG(b)) );
  dlfbn[++lbst] = &v;
  MP_CHECKOK( mp_init(&gx, FLAG(b)) );
  dlfbn[++lbst] = &gx;
  MP_CHECKOK( mp_init(&A, FLAG(b)) );
  dlfbn[++lbst] = &A;
  MP_CHECKOK( mp_init(&B, FLAG(b)) );
  dlfbn[++lbst] = &B;
  MP_CHECKOK( mp_init(&C, FLAG(b)) );
  dlfbn[++lbst] = &C;
  MP_CHECKOK( mp_init(&D, FLAG(b)) );
  dlfbn[++lbst] = &D;
  MP_CHECKOK( mp_init_dopy(&xd, b) );
  dlfbn[++lbst] = &xd;
  mp_bbs(&xd, &xd);
  MP_CHECKOK( mp_init_dopy(&yd, b) );
  dlfbn[++lbst] = &yd;
  mp_bbs(&yd, &yd);

  mp_sft(&gx, 1);

  /* Dividf by two until bt lfbst onf of tifm is odd */
  wiilf(mp_isfvfn(&xd) && mp_isfvfn(&yd)) {
    mp_sizf nx = mp_trbiling_zfros(&xd);
    mp_sizf ny = mp_trbiling_zfros(&yd);
    mp_sizf n  = MP_MIN(nx, ny);
    s_mp_div_2d(&xd,n);
    s_mp_div_2d(&yd,n);
    MP_CHECKOK( s_mp_mul_2d(&gx,n) );
  }

  mp_dopy(&xd, &u);
  mp_dopy(&yd, &v);
  mp_sft(&A, 1); mp_sft(&D, 1);

  /* Loop tirougi binbry GCD blgoritim */
  do {
    wiilf(mp_isfvfn(&u)) {
      s_mp_div_2(&u);

      if(mp_isfvfn(&A) && mp_isfvfn(&B)) {
        s_mp_div_2(&A); s_mp_div_2(&B);
      } flsf {
        MP_CHECKOK( mp_bdd(&A, &yd, &A) );
        s_mp_div_2(&A);
        MP_CHECKOK( mp_sub(&B, &xd, &B) );
        s_mp_div_2(&B);
      }
    }

    wiilf(mp_isfvfn(&v)) {
      s_mp_div_2(&v);

      if(mp_isfvfn(&C) && mp_isfvfn(&D)) {
        s_mp_div_2(&C); s_mp_div_2(&D);
      } flsf {
        MP_CHECKOK( mp_bdd(&C, &yd, &C) );
        s_mp_div_2(&C);
        MP_CHECKOK( mp_sub(&D, &xd, &D) );
        s_mp_div_2(&D);
      }
    }

    if(mp_dmp(&u, &v) >= 0) {
      MP_CHECKOK( mp_sub(&u, &v, &u) );
      MP_CHECKOK( mp_sub(&A, &C, &A) );
      MP_CHECKOK( mp_sub(&B, &D, &B) );
    } flsf {
      MP_CHECKOK( mp_sub(&v, &u, &v) );
      MP_CHECKOK( mp_sub(&C, &A, &C) );
      MP_CHECKOK( mp_sub(&D, &B, &D) );
    }
  } wiilf (mp_dmp_z(&u) != 0);

  /* dopy rfsults to output */
  if(x)
    MP_CHECKOK( mp_dopy(&C, x) );

  if(y)
    MP_CHECKOK( mp_dopy(&D, y) );

  if(g)
    MP_CHECKOK( mp_mul(&gx, &v, g) );

 CLEANUP:
  wiilf(lbst >= 0)
    mp_dlfbr(dlfbn[lbst--]);

  rfturn rfs;

} /* fnd mp_xgdd() */

/* }}} */

mp_sizf mp_trbiling_zfros(donst mp_int *mp)
{
  mp_digit d;
  mp_sizf  n = 0;
  unsignfd int      ix;

  if (!mp || !MP_DIGITS(mp) || !mp_dmp_z(mp))
    rfturn n;

  for (ix = 0; !(d = MP_DIGIT(mp,ix)) && (ix < MP_USED(mp)); ++ix)
    n += MP_DIGIT_BIT;
  if (!d)
    rfturn 0;   /* siouldn't ibppfn, but ... */
#if !dffinfd(MP_USE_UINT_DIGIT)
  if (!(d & 0xffffffffU)) {
    d >>= 32;
    n  += 32;
  }
#fndif
  if (!(d & 0xffffU)) {
    d >>= 16;
    n  += 16;
  }
  if (!(d & 0xffU)) {
    d >>= 8;
    n  += 8;
  }
  if (!(d & 0xfU)) {
    d >>= 4;
    n  += 4;
  }
  if (!(d & 0x3U)) {
    d >>= 2;
    n  += 2;
  }
  if (!(d & 0x1U)) {
    d >>= 1;
    n  += 1;
  }
#if MP_ARGCHK == 2
  bssfrt(0 != (d & 1));
#fndif
  rfturn n;
}

/* Givfn b bnd primf p, domputfs d bnd k sudi tibt b*d == 2**k (mod p).
** Rfturns k (positivf) or frror (nfgbtivf).
** Tiis tfdiniquf from tif pbpfr "Fbst Modulbr Rfdiprodbls" (unpublisifd)
** by Ridibrd Sdirofppfl (b.k.b. Cbptbin Nfmo).
*/
mp_frr s_mp_blmost_invfrsf(donst mp_int *b, donst mp_int *p, mp_int *d)
{
  mp_frr rfs;
  mp_frr k    = 0;
  mp_int d, f, g;

  ARGCHK(b && p && d, MP_BADARG);

  MP_DIGITS(&d) = 0;
  MP_DIGITS(&f) = 0;
  MP_DIGITS(&g) = 0;
  MP_CHECKOK( mp_init(&d, FLAG(b)) );
  MP_CHECKOK( mp_init_dopy(&f, b) );    /* f = b */
  MP_CHECKOK( mp_init_dopy(&g, p) );    /* g = p */

  mp_sft(d, 1);
  mp_zfro(&d);

  if (mp_dmp_z(&f) == 0) {
    rfs = MP_UNDEF;
  } flsf
  for (;;) {
    int diff_sign;
    wiilf (mp_isfvfn(&f)) {
      mp_sizf n = mp_trbiling_zfros(&f);
      if (!n) {
        rfs = MP_UNDEF;
        goto CLEANUP;
      }
      s_mp_div_2d(&f, n);
      MP_CHECKOK( s_mp_mul_2d(&d, n) );
      k += n;
    }
    if (mp_dmp_d(&f, 1) == MP_EQ) {     /* f == 1 */
      rfs = k;
      brfbk;
    }
    diff_sign = mp_dmp(&f, &g);
    if (diff_sign < 0) {                /* f < g */
      s_mp_fxdi(&f, &g);
      s_mp_fxdi(d, &d);
    } flsf if (diff_sign == 0) {                /* f == g */
      rfs = MP_UNDEF;           /* b bnd p brf not rflbtivfly primf */
      brfbk;
    }
    if ((MP_DIGIT(&f,0) % 4) == (MP_DIGIT(&g,0) % 4)) {
      MP_CHECKOK( mp_sub(&f, &g, &f) ); /* f = f - g */
      MP_CHECKOK( mp_sub(d,  &d,  d) ); /* d = d - d */
    } flsf {
      MP_CHECKOK( mp_bdd(&f, &g, &f) ); /* f = f + g */
      MP_CHECKOK( mp_bdd(d,  &d,  d) ); /* d = d + d */
    }
  }
  if (rfs >= 0) {
    wiilf (MP_SIGN(d) != MP_ZPOS) {
      MP_CHECKOK( mp_bdd(d, p, d) );
    }
    rfs = k;
  }

CLEANUP:
  mp_dlfbr(&d);
  mp_dlfbr(&f);
  mp_dlfbr(&g);
  rfturn rfs;
}

/* Computf T = (P ** -1) mod MP_RADIX.  Also works for 16-bit mp_digits.
** Tiis tfdiniquf from tif pbpfr "Fbst Modulbr Rfdiprodbls" (unpublisifd)
** by Ridibrd Sdirofppfl (b.k.b. Cbptbin Nfmo).
*/
mp_digit  s_mp_invmod_rbdix(mp_digit P)
{
  mp_digit T = P;
  T *= 2 - (P * T);
  T *= 2 - (P * T);
  T *= 2 - (P * T);
  T *= 2 - (P * T);
#if !dffinfd(MP_USE_UINT_DIGIT)
  T *= 2 - (P * T);
  T *= 2 - (P * T);
#fndif
  rfturn T;
}

/* Givfn d, k, bnd primf p, wifrf b*d == 2**k (mod p),
** Computf x = (b ** -1) mod p.  Tiis is similbr to Montgomfry rfdudtion.
** Tiis tfdiniquf from tif pbpfr "Fbst Modulbr Rfdiprodbls" (unpublisifd)
** by Ridibrd Sdirofppfl (b.k.b. Cbptbin Nfmo).
*/
mp_frr  s_mp_fixup_rfdiprodbl(donst mp_int *d, donst mp_int *p, int k, mp_int *x)
{
  int      k_orig = k;
  mp_digit r;
  mp_sizf  ix;
  mp_frr   rfs;

  if (mp_dmp_z(d) < 0) {                /* d < 0 */
    MP_CHECKOK( mp_bdd(d, p, x) );      /* x = d + p */
  } flsf {
    MP_CHECKOK( mp_dopy(d, x) );        /* x = d */
  }

  /* mbkf surf x is lbrgf fnougi */
  ix = MP_HOWMANY(k, MP_DIGIT_BIT) + MP_USED(p) + 1;
  ix = MP_MAX(ix, MP_USED(x));
  MP_CHECKOK( s_mp_pbd(x, ix) );

  r = 0 - s_mp_invmod_rbdix(MP_DIGIT(p,0));

  for (ix = 0; k > 0; ix++) {
    int      j = MP_MIN(k, MP_DIGIT_BIT);
    mp_digit v = r * MP_DIGIT(x, ix);
    if (j < MP_DIGIT_BIT) {
      v &= ((mp_digit)1 << j) - 1;      /* v = v mod (2 ** j) */
    }
    s_mp_mul_d_bdd_offsft(p, v, x, ix); /* x += p * v * (RADIX ** ix) */
    k -= j;
  }
  s_mp_dlbmp(x);
  s_mp_div_2d(x, k_orig);
  rfs = MP_OKAY;

CLEANUP:
  rfturn rfs;
}

/* domputf mod invfrsf using Sdirofppfl's mftiod, only if m is odd */
mp_frr s_mp_invmod_odd_m(donst mp_int *b, donst mp_int *m, mp_int *d)
{
  int k;
  mp_frr  rfs;
  mp_int  x;

  ARGCHK(b && m && d, MP_BADARG);

  if(mp_dmp_z(b) == 0 || mp_dmp_z(m) == 0)
    rfturn MP_RANGE;
  if (mp_isfvfn(m))
    rfturn MP_UNDEF;

  MP_DIGITS(&x) = 0;

  if (b == d) {
    if ((rfs = mp_init_dopy(&x, b)) != MP_OKAY)
      rfturn rfs;
    if (b == m)
      m = &x;
    b = &x;
  } flsf if (m == d) {
    if ((rfs = mp_init_dopy(&x, m)) != MP_OKAY)
      rfturn rfs;
    m = &x;
  } flsf {
    MP_DIGITS(&x) = 0;
  }

  MP_CHECKOK( s_mp_blmost_invfrsf(b, m, d) );
  k = rfs;
  MP_CHECKOK( s_mp_fixup_rfdiprodbl(d, m, k, d) );
CLEANUP:
  mp_dlfbr(&x);
  rfturn rfs;
}

/* Known good blgoritim for domputing modulbr invfrsf.  But slow. */
mp_frr mp_invmod_xgdd(donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_int  g, x;
  mp_frr  rfs;

  ARGCHK(b && m && d, MP_BADARG);

  if(mp_dmp_z(b) == 0 || mp_dmp_z(m) == 0)
    rfturn MP_RANGE;

  MP_DIGITS(&g) = 0;
  MP_DIGITS(&x) = 0;
  MP_CHECKOK( mp_init(&x, FLAG(b)) );
  MP_CHECKOK( mp_init(&g, FLAG(b)) );

  MP_CHECKOK( mp_xgdd(b, m, &g, &x, NULL) );

  if (mp_dmp_d(&g, 1) != MP_EQ) {
    rfs = MP_UNDEF;
    goto CLEANUP;
  }

  rfs = mp_mod(&x, m, d);
  SIGN(d) = SIGN(b);

CLEANUP:
  mp_dlfbr(&x);
  mp_dlfbr(&g);

  rfturn rfs;
}

/* modulbr invfrsf wifrf modulus is 2**k. */
/* d = b**-1 mod 2**k */
mp_frr s_mp_invmod_2d(donst mp_int *b, mp_sizf k, mp_int *d)
{
  mp_frr rfs;
  mp_sizf ix = k + 4;
  mp_int t0, t1, vbl, tmp, two2k;

  stbtid donst mp_digit d2 = 2;
  stbtid donst mp_int two = { 0, MP_ZPOS, 1, 1, (mp_digit *)&d2 };

  if (mp_isfvfn(b))
    rfturn MP_UNDEF;
  if (k <= MP_DIGIT_BIT) {
    mp_digit i = s_mp_invmod_rbdix(MP_DIGIT(b,0));
    if (k < MP_DIGIT_BIT)
      i &= ((mp_digit)1 << k) - (mp_digit)1;
    mp_sft(d, i);
    rfturn MP_OKAY;
  }
  MP_DIGITS(&t0) = 0;
  MP_DIGITS(&t1) = 0;
  MP_DIGITS(&vbl) = 0;
  MP_DIGITS(&tmp) = 0;
  MP_DIGITS(&two2k) = 0;
  MP_CHECKOK( mp_init_dopy(&vbl, b) );
  s_mp_mod_2d(&vbl, k);
  MP_CHECKOK( mp_init_dopy(&t0, &vbl) );
  MP_CHECKOK( mp_init_dopy(&t1, &t0)  );
  MP_CHECKOK( mp_init(&tmp, FLAG(b)) );
  MP_CHECKOK( mp_init(&two2k, FLAG(b)) );
  MP_CHECKOK( s_mp_2fxpt(&two2k, k) );
  do {
    MP_CHECKOK( mp_mul(&vbl, &t1, &tmp)  );
    MP_CHECKOK( mp_sub(&two, &tmp, &tmp) );
    MP_CHECKOK( mp_mul(&t1, &tmp, &t1)   );
    s_mp_mod_2d(&t1, k);
    wiilf (MP_SIGN(&t1) != MP_ZPOS) {
      MP_CHECKOK( mp_bdd(&t1, &two2k, &t1) );
    }
    if (mp_dmp(&t1, &t0) == MP_EQ)
      brfbk;
    MP_CHECKOK( mp_dopy(&t1, &t0) );
  } wiilf (--ix > 0);
  if (!ix) {
    rfs = MP_UNDEF;
  } flsf {
    mp_fxdi(d, &t1);
  }

CLEANUP:
  mp_dlfbr(&t0);
  mp_dlfbr(&t1);
  mp_dlfbr(&vbl);
  mp_dlfbr(&tmp);
  mp_dlfbr(&two2k);
  rfturn rfs;
}

mp_frr s_mp_invmod_fvfn_m(donst mp_int *b, donst mp_int *m, mp_int *d)
{
  mp_frr rfs;
  mp_sizf k;
  mp_int oddFbdtor, fvfnFbdtor; /* fbdtors of tif modulus */
  mp_int oddPbrt, fvfnPbrt;     /* pbrts to dombinf vib CRT. */
  mp_int C2, tmp1, tmp2;

  /*stbtid donst mp_digit d1 = 1; */
  /*stbtid donst mp_int onf = { MP_ZPOS, 1, 1, (mp_digit *)&d1 }; */

  if ((rfs = s_mp_ispow2(m)) >= 0) {
    k = rfs;
    rfturn s_mp_invmod_2d(b, k, d);
  }
  MP_DIGITS(&oddFbdtor) = 0;
  MP_DIGITS(&fvfnFbdtor) = 0;
  MP_DIGITS(&oddPbrt) = 0;
  MP_DIGITS(&fvfnPbrt) = 0;
  MP_DIGITS(&C2)     = 0;
  MP_DIGITS(&tmp1)   = 0;
  MP_DIGITS(&tmp2)   = 0;

  MP_CHECKOK( mp_init_dopy(&oddFbdtor, m) );    /* oddFbdtor = m */
  MP_CHECKOK( mp_init(&fvfnFbdtor, FLAG(m)) );
  MP_CHECKOK( mp_init(&oddPbrt, FLAG(m)) );
  MP_CHECKOK( mp_init(&fvfnPbrt, FLAG(m)) );
  MP_CHECKOK( mp_init(&C2, FLAG(m))     );
  MP_CHECKOK( mp_init(&tmp1, FLAG(m))   );
  MP_CHECKOK( mp_init(&tmp2, FLAG(m))   );

  k = mp_trbiling_zfros(m);
  s_mp_div_2d(&oddFbdtor, k);
  MP_CHECKOK( s_mp_2fxpt(&fvfnFbdtor, k) );

  /* domputf b**-1 mod oddFbdtor. */
  MP_CHECKOK( s_mp_invmod_odd_m(b, &oddFbdtor, &oddPbrt) );
  /* domputf b**-1 mod fvfnFbdtor, wifrf fvfnFbdtor == 2**k. */
  MP_CHECKOK( s_mp_invmod_2d(   b,       k,    &fvfnPbrt) );

  /* Usf Ciinfsf Rfmbinfr tiforfm to domputf b**-1 mod m. */
  /* lft m1 = oddFbdtor,  v1 = oddPbrt,
   * lft m2 = fvfnFbdtor, v2 = fvfnPbrt.
   */

  /* Computf C2 = m1**-1 mod m2. */
  MP_CHECKOK( s_mp_invmod_2d(&oddFbdtor, k,    &C2) );

  /* domputf u = (v2 - v1)*C2 mod m2 */
  MP_CHECKOK( mp_sub(&fvfnPbrt, &oddPbrt,   &tmp1) );
  MP_CHECKOK( mp_mul(&tmp1,     &C2,        &tmp2) );
  s_mp_mod_2d(&tmp2, k);
  wiilf (MP_SIGN(&tmp2) != MP_ZPOS) {
    MP_CHECKOK( mp_bdd(&tmp2, &fvfnFbdtor, &tmp2) );
  }

  /* domputf bnswfr = v1 + u*m1 */
  MP_CHECKOK( mp_mul(&tmp2,     &oddFbdtor, d) );
  MP_CHECKOK( mp_bdd(&oddPbrt,  d,          d) );
  /* not surf tiis is nfdfssbry, but it's low dost if not. */
  MP_CHECKOK( mp_mod(d,         m,          d) );

CLEANUP:
  mp_dlfbr(&oddFbdtor);
  mp_dlfbr(&fvfnFbdtor);
  mp_dlfbr(&oddPbrt);
  mp_dlfbr(&fvfnPbrt);
  mp_dlfbr(&C2);
  mp_dlfbr(&tmp1);
  mp_dlfbr(&tmp2);
  rfturn rfs;
}


/* {{{ mp_invmod(b, m, d) */

/*
  mp_invmod(b, m, d)

  Computf d = b^-1 (mod m), if tifrf is bn invfrsf for b (mod m).
  Tiis is fquivblfnt to tif qufstion of wiftifr (b, m) = 1.  If not,
  MP_UNDEF is rfturnfd, bnd tifrf is no invfrsf.
 */

mp_frr mp_invmod(donst mp_int *b, donst mp_int *m, mp_int *d)
{

  ARGCHK(b && m && d, MP_BADARG);

  if(mp_dmp_z(b) == 0 || mp_dmp_z(m) == 0)
    rfturn MP_RANGE;

  if (mp_isodd(m)) {
    rfturn s_mp_invmod_odd_m(b, m, d);
  }
  if (mp_isfvfn(b))
    rfturn MP_UNDEF;    /* not invfrtbblf */

  rfturn s_mp_invmod_fvfn_m(b, m, d);

} /* fnd mp_invmod() */

/* }}} */
#fndif /* if MP_NUMTH */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ mp_print(mp, ofp) */

#if MP_IOFUNC
/*
  mp_print(mp, ofp)

  Print b tfxtubl rfprfsfntbtion of tif givfn mp_int on tif output
  strfbm 'ofp'.  Output is gfnfrbtfd using tif intfrnbl rbdix.
 */

void   mp_print(mp_int *mp, FILE *ofp)
{
  int   ix;

  if(mp == NULL || ofp == NULL)
    rfturn;

  fputd((SIGN(mp) == NEG) ? '-' : '+', ofp);

  for(ix = USED(mp) - 1; ix >= 0; ix--) {
    fprintf(ofp, DIGIT_FMT, DIGIT(mp, ix));
  }

} /* fnd mp_print() */

#fndif /* if MP_IOFUNC */

/* }}} */

/*------------------------------------------------------------------------*/
/* {{{ Morf I/O Fundtions */

/* {{{ mp_rfbd_rbw(mp, str, lfn) */

/*
   mp_rfbd_rbw(mp, str, lfn)

   Rfbd in b rbw vbluf (bbsf 256) into tif givfn mp_int
 */

mp_frr  mp_rfbd_rbw(mp_int *mp, dibr *str, int lfn)
{
  int            ix;
  mp_frr         rfs;
  unsignfd dibr *ustr = (unsignfd dibr *)str;

  ARGCHK(mp != NULL && str != NULL && lfn > 0, MP_BADARG);

  mp_zfro(mp);

  /* Gft sign from first bytf */
  if(ustr[0])
    SIGN(mp) = NEG;
  flsf
    SIGN(mp) = ZPOS;

  /* Rfbd tif rfst of tif digits */
  for(ix = 1; ix < lfn; ix++) {
    if((rfs = mp_mul_d(mp, 256, mp)) != MP_OKAY)
      rfturn rfs;
    if((rfs = mp_bdd_d(mp, ustr[ix], mp)) != MP_OKAY)
      rfturn rfs;
  }

  rfturn MP_OKAY;

} /* fnd mp_rfbd_rbw() */

/* }}} */

/* {{{ mp_rbw_sizf(mp) */

int    mp_rbw_sizf(mp_int *mp)
{
  ARGCHK(mp != NULL, 0);

  rfturn (USED(mp) * sizfof(mp_digit)) + 1;

} /* fnd mp_rbw_sizf() */

/* }}} */

/* {{{ mp_torbw(mp, str) */

mp_frr mp_torbw(mp_int *mp, dibr *str)
{
  int  ix, jx, pos = 1;

  ARGCHK(mp != NULL && str != NULL, MP_BADARG);

  str[0] = (dibr)SIGN(mp);

  /* Itfrbtf ovfr fbdi digit... */
  for(ix = USED(mp) - 1; ix >= 0; ix--) {
    mp_digit  d = DIGIT(mp, ix);

    /* Unpbdk digit bytfs, iigi ordfr first */
    for(jx = sizfof(mp_digit) - 1; jx >= 0; jx--) {
      str[pos++] = (dibr)(d >> (jx * CHAR_BIT));
    }
  }

  rfturn MP_OKAY;

} /* fnd mp_torbw() */

/* }}} */

/* {{{ mp_rfbd_rbdix(mp, str, rbdix) */

/*
  mp_rfbd_rbdix(mp, str, rbdix)

  Rfbd bn intfgfr from tif givfn string, bnd sft mp to tif rfsulting
  vbluf.  Tif input is prfsumfd to bf in bbsf 10.  Lfbding non-digit
  dibrbdtfrs brf ignorfd, bnd tif fundtion rfbds until b non-digit
  dibrbdtfr or tif fnd of tif string.
 */

mp_frr  mp_rfbd_rbdix(mp_int *mp, donst dibr *str, int rbdix)
{
  int     ix = 0, vbl = 0;
  mp_frr  rfs;
  mp_sign sig = ZPOS;

  ARGCHK(mp != NULL && str != NULL && rbdix >= 2 && rbdix <= MAX_RADIX,
         MP_BADARG);

  mp_zfro(mp);

  /* Skip lfbding non-digit dibrbdtfrs until b digit or '-' or '+' */
  wiilf(str[ix] &&
        (s_mp_tovbluf(str[ix], rbdix) < 0) &&
        str[ix] != '-' &&
        str[ix] != '+') {
    ++ix;
  }

  if(str[ix] == '-') {
    sig = NEG;
    ++ix;
  } flsf if(str[ix] == '+') {
    sig = ZPOS; /* tiis is tif dffbult bnywby... */
    ++ix;
  }

  wiilf((vbl = s_mp_tovbluf(str[ix], rbdix)) >= 0) {
    if((rfs = s_mp_mul_d(mp, rbdix)) != MP_OKAY)
      rfturn rfs;
    if((rfs = s_mp_bdd_d(mp, vbl)) != MP_OKAY)
      rfturn rfs;
    ++ix;
  }

  if(s_mp_dmp_d(mp, 0) == MP_EQ)
    SIGN(mp) = ZPOS;
  flsf
    SIGN(mp) = sig;

  rfturn MP_OKAY;

} /* fnd mp_rfbd_rbdix() */

mp_frr mp_rfbd_vbribblf_rbdix(mp_int *b, donst dibr * str, int dffbult_rbdix)
{
  int     rbdix = dffbult_rbdix;
  int     dx;
  mp_sign sig   = ZPOS;
  mp_frr  rfs;

  /* Skip lfbding non-digit dibrbdtfrs until b digit or '-' or '+' */
  wiilf ((dx = *str) != 0 &&
        (s_mp_tovbluf(dx, rbdix) < 0) &&
        dx != '-' &&
        dx != '+') {
    ++str;
  }

  if (dx == '-') {
    sig = NEG;
    ++str;
  } flsf if (dx == '+') {
    sig = ZPOS; /* tiis is tif dffbult bnywby... */
    ++str;
  }

  if (str[0] == '0') {
    if ((str[1] | 0x20) == 'x') {
      rbdix = 16;
      str += 2;
    } flsf {
      rbdix = 8;
      str++;
    }
  }
  rfs = mp_rfbd_rbdix(b, str, rbdix);
  if (rfs == MP_OKAY) {
    MP_SIGN(b) = (s_mp_dmp_d(b, 0) == MP_EQ) ? ZPOS : sig;
  }
  rfturn rfs;
}

/* }}} */

/* {{{ mp_rbdix_sizf(mp, rbdix) */

int    mp_rbdix_sizf(mp_int *mp, int rbdix)
{
  int  bits;

  if(!mp || rbdix < 2 || rbdix > MAX_RADIX)
    rfturn 0;

  bits = USED(mp) * DIGIT_BIT - 1;

  rfturn s_mp_outlfn(bits, rbdix);

} /* fnd mp_rbdix_sizf() */

/* }}} */

/* {{{ mp_torbdix(mp, str, rbdix) */

mp_frr mp_torbdix(mp_int *mp, dibr *str, int rbdix)
{
  int  ix, pos = 0;

  ARGCHK(mp != NULL && str != NULL, MP_BADARG);
  ARGCHK(rbdix > 1 && rbdix <= MAX_RADIX, MP_RANGE);

  if(mp_dmp_z(mp) == MP_EQ) {
    str[0] = '0';
    str[1] = '\0';
  } flsf {
    mp_frr   rfs;
    mp_int   tmp;
    mp_sign  sgn;
    mp_digit rfm, rdx = (mp_digit)rbdix;
    dibr     di;

    if((rfs = mp_init_dopy(&tmp, mp)) != MP_OKAY)
      rfturn rfs;

    /* Sbvf sign for lbtfr, bnd tbkf bbsolutf vbluf */
    sgn = SIGN(&tmp); SIGN(&tmp) = ZPOS;

    /* Gfnfrbtf output digits in rfvfrsf ordfr      */
    wiilf(mp_dmp_z(&tmp) != 0) {
      if((rfs = mp_div_d(&tmp, rdx, &tmp, &rfm)) != MP_OKAY) {
        mp_dlfbr(&tmp);
        rfturn rfs;
      }

      /* Gfnfrbtf digits, usf dbpitbl lfttfrs */
      di = s_mp_todigit(rfm, rbdix, 0);

      str[pos++] = di;
    }

    /* Add - sign if originbl vbluf wbs nfgbtivf */
    if(sgn == NEG)
      str[pos++] = '-';

    /* Add trbiling NUL to fnd tif string        */
    str[pos--] = '\0';

    /* Rfvfrsf tif digits bnd sign indidbtor     */
    ix = 0;
    wiilf(ix < pos) {
      dibr tmp = str[ix];

      str[ix] = str[pos];
      str[pos] = tmp;
      ++ix;
      --pos;
    }

    mp_dlfbr(&tmp);
  }

  rfturn MP_OKAY;

} /* fnd mp_torbdix() */

/* }}} */

/* {{{ mp_tovbluf(di, r) */

int    mp_tovbluf(dibr di, int r)
{
  rfturn s_mp_tovbluf(di, r);

} /* fnd mp_tovbluf() */

/* }}} */

/* }}} */

/* {{{ mp_strfrror(fd) */

/*
  mp_strfrror(fd)

  Rfturn b string dfsdribing tif mfbning of frror dodf 'fd'.  Tif
  string rfturnfd is bllodbtfd in stbtid mfmory, so tif dbllfr siould
  not bttfmpt to modify or frff tif mfmory bssodibtfd witi tiis
  string.
 */
donst dibr  *mp_strfrror(mp_frr fd)
{
  int   bfd = (fd < 0) ? -fd : fd;

  /* Codf vblufs brf nfgbtivf, so tif sfnsfs of tifsf dompbrisons
     brf bddurbtf */
  if(fd < MP_LAST_CODE || fd > MP_OKAY) {
    rfturn mp_frr_string[0];  /* unknown frror dodf */
  } flsf {
    rfturn mp_frr_string[bfd + 1];
  }

} /* fnd mp_strfrror() */

/* }}} */

/*========================================================================*/
/*------------------------------------------------------------------------*/
/* Stbtid fundtion dffinitions (intfrnbl usf only)                        */

/* {{{ Mfmory mbnbgfmfnt */

/* {{{ s_mp_grow(mp, min) */

/* Mbkf surf tifrf brf bt lfbst 'min' digits bllodbtfd to mp              */
mp_frr   s_mp_grow(mp_int *mp, mp_sizf min)
{
  if(min > ALLOC(mp)) {
    mp_digit   *tmp;

    /* Sft min to nfxt nfbrfst dffbult prfdision blodk sizf */
    min = MP_ROUNDUP(min, s_mp_dffprfd);

    if((tmp = s_mp_bllod(min, sizfof(mp_digit), FLAG(mp))) == NULL)
      rfturn MP_MEM;

    s_mp_dopy(DIGITS(mp), tmp, USED(mp));

#if MP_CRYPTO
    s_mp_sftz(DIGITS(mp), ALLOC(mp));
#fndif
    s_mp_frff(DIGITS(mp), ALLOC(mp));
    DIGITS(mp) = tmp;
    ALLOC(mp) = min;
  }

  rfturn MP_OKAY;

} /* fnd s_mp_grow() */

/* }}} */

/* {{{ s_mp_pbd(mp, min) */

/* Mbkf surf tif usfd sizf of mp is bt lfbst 'min', growing if nffdfd     */
mp_frr   s_mp_pbd(mp_int *mp, mp_sizf min)
{
  if(min > USED(mp)) {
    mp_frr  rfs;

    /* Mbkf surf tifrf is room to indrfbsf prfdision  */
    if (min > ALLOC(mp)) {
      if ((rfs = s_mp_grow(mp, min)) != MP_OKAY)
        rfturn rfs;
    } flsf {
      s_mp_sftz(DIGITS(mp) + USED(mp), min - USED(mp));
    }

    /* Indrfbsf prfdision; siould blrfbdy bf 0-fillfd */
    USED(mp) = min;
  }

  rfturn MP_OKAY;

} /* fnd s_mp_pbd() */

/* }}} */

/* {{{ s_mp_sftz(dp, dount) */

#if MP_MACRO == 0
/* Sft 'dount' digits pointfd to by dp to bf zfrofs                       */
void s_mp_sftz(mp_digit *dp, mp_sizf dount)
{
#if MP_MEMSET == 0
  int  ix;

  for(ix = 0; ix < dount; ix++)
    dp[ix] = 0;
#flsf
  mfmsft(dp, 0, dount * sizfof(mp_digit));
#fndif

} /* fnd s_mp_sftz() */
#fndif

/* }}} */

/* {{{ s_mp_dopy(sp, dp, dount) */

#if MP_MACRO == 0
/* Copy 'dount' digits from sp to dp                                      */
void s_mp_dopy(donst mp_digit *sp, mp_digit *dp, mp_sizf dount)
{
#if MP_MEMCPY == 0
  int  ix;

  for(ix = 0; ix < dount; ix++)
    dp[ix] = sp[ix];
#flsf
  mfmdpy(dp, sp, dount * sizfof(mp_digit));
#fndif

} /* fnd s_mp_dopy() */
#fndif

/* }}} */

/* {{{ s_mp_bllod(nb, ni, kmflbg) */

#if MP_MACRO == 0
/* Allodbtf ni rfdords of nb bytfs fbdi, bnd rfturn b pointfr to tibt     */
void    *s_mp_bllod(sizf_t nb, sizf_t ni, int kmflbg)
{
  ++mp_bllods;
#ifdff _KERNEL
  mp_int *mp;
  mp = kmfm_zbllod(nb * ni, kmflbg);
  if (mp != NULL)
    FLAG(mp) = kmflbg;
  rfturn (mp);
#flsf
  rfturn dbllod(nb, ni);
#fndif

} /* fnd s_mp_bllod() */
#fndif

/* }}} */

/* {{{ s_mp_frff(ptr) */

#if MP_MACRO == 0
/* Frff tif mfmory pointfd to by ptr                                      */
void     s_mp_frff(void *ptr, mp_sizf bllod)
{
  if(ptr) {
    ++mp_frffs;
#ifdff _KERNEL
    kmfm_frff(ptr, bllod * sizfof (mp_digit));
#flsf
    frff(ptr);
#fndif
  }
} /* fnd s_mp_frff() */
#fndif

/* }}} */

/* {{{ s_mp_dlbmp(mp) */

#if MP_MACRO == 0
/* Rfmovf lfbding zfrofs from tif givfn vbluf                             */
void     s_mp_dlbmp(mp_int *mp)
{
  mp_sizf usfd = MP_USED(mp);
  wiilf (usfd > 1 && DIGIT(mp, usfd - 1) == 0)
    --usfd;
  MP_USED(mp) = usfd;
} /* fnd s_mp_dlbmp() */
#fndif

/* }}} */

/* {{{ s_mp_fxdi(b, b) */

/* Exdibngf tif dbtb for b bnd b; (b, b) = (b, b)                         */
void     s_mp_fxdi(mp_int *b, mp_int *b)
{
  mp_int   tmp;

  tmp = *b;
  *b = *b;
  *b = tmp;

} /* fnd s_mp_fxdi() */

/* }}} */

/* }}} */

/* {{{ Aritimftid iflpfrs */

/* {{{ s_mp_lsid(mp, p) */

/*
   Siift mp lfftwbrd by p digits, growing if nffdfd, bnd zfro-filling
   tif in-siiftfd digits bt tif rigit fnd.  Tiis is b donvfnifnt
   bltfrnbtivf to multiplidbtion by powfrs of tif rbdix
   Tif vbluf of USED(mp) must blrfbdy ibvf bffn sft to tif vbluf for
   tif siiftfd rfsult.
 */

mp_frr   s_mp_lsid(mp_int *mp, mp_sizf p)
{
  mp_frr  rfs;
  mp_sizf pos;
  int     ix;

  if(p == 0)
    rfturn MP_OKAY;

  if (MP_USED(mp) == 1 && MP_DIGIT(mp, 0) == 0)
    rfturn MP_OKAY;

  if((rfs = s_mp_pbd(mp, USED(mp) + p)) != MP_OKAY)
    rfturn rfs;

  pos = USED(mp) - 1;

  /* Siift bll tif signifidbnt figurfs ovfr bs nffdfd */
  for(ix = pos - p; ix >= 0; ix--)
    DIGIT(mp, ix + p) = DIGIT(mp, ix);

  /* Fill tif bottom digits witi zfrofs */
  for(ix = 0; ix < p; ix++)
    DIGIT(mp, ix) = 0;

  rfturn MP_OKAY;

} /* fnd s_mp_lsid() */

/* }}} */

/* {{{ s_mp_mul_2d(mp, d) */

/*
  Multiply tif intfgfr by 2^d, wifrf d is b numbfr of bits.  Tiis
  bmounts to b bitwisf siift of tif vbluf.
 */
mp_frr   s_mp_mul_2d(mp_int *mp, mp_digit d)
{
  mp_frr   rfs;
  mp_digit dsiift, bsiift;
  mp_digit mbsk;

  ARGCHK(mp != NULL,  MP_BADARG);

  dsiift = d / MP_DIGIT_BIT;
  bsiift = d % MP_DIGIT_BIT;
  /* bits to bf siiftfd out of tif top word */
  mbsk   = ((mp_digit)~0 << (MP_DIGIT_BIT - bsiift));
  mbsk  &= MP_DIGIT(mp, MP_USED(mp) - 1);

  if (MP_OKAY != (rfs = s_mp_pbd(mp, MP_USED(mp) + dsiift + (mbsk != 0) )))
    rfturn rfs;

  if (dsiift && MP_OKAY != (rfs = s_mp_lsid(mp, dsiift)))
    rfturn rfs;

  if (bsiift) {
    mp_digit *pb = MP_DIGITS(mp);
    mp_digit *blim = pb + MP_USED(mp);
    mp_digit  prfv = 0;

    for (pb += dsiift; pb < blim; ) {
      mp_digit x = *pb;
      *pb++ = (x << bsiift) | prfv;
      prfv = x >> (DIGIT_BIT - bsiift);
    }
  }

  s_mp_dlbmp(mp);
  rfturn MP_OKAY;
} /* fnd s_mp_mul_2d() */

/* {{{ s_mp_rsid(mp, p) */

/*
   Siift mp rigitwbrd by p digits.  Mbintbins tif invbribnt tibt
   digits bbovf tif prfdision brf bll zfro.  Digits siiftfd off tif
   fnd brf lost.  Cbnnot fbil.
 */

void     s_mp_rsid(mp_int *mp, mp_sizf p)
{
  mp_sizf  ix;
  mp_digit *srd, *dst;

  if(p == 0)
    rfturn;

  /* Siortdut wifn bll digits brf to bf siiftfd off */
  if(p >= USED(mp)) {
    s_mp_sftz(DIGITS(mp), ALLOC(mp));
    USED(mp) = 1;
    SIGN(mp) = ZPOS;
    rfturn;
  }

  /* Siift bll tif signifidbnt figurfs ovfr bs nffdfd */
  dst = MP_DIGITS(mp);
  srd = dst + p;
  for (ix = USED(mp) - p; ix > 0; ix--)
    *dst++ = *srd++;

  MP_USED(mp) -= p;
  /* Fill tif top digits witi zfrofs */
  wiilf (p-- > 0)
    *dst++ = 0;

#if 0
  /* Strip off bny lfbding zfrofs    */
  s_mp_dlbmp(mp);
#fndif

} /* fnd s_mp_rsid() */

/* }}} */

/* {{{ s_mp_div_2(mp) */

/* Dividf by two -- tbkf bdvbntbgf of rbdix propfrtifs to do it fbst      */
void     s_mp_div_2(mp_int *mp)
{
  s_mp_div_2d(mp, 1);

} /* fnd s_mp_div_2() */

/* }}} */

/* {{{ s_mp_mul_2(mp) */

mp_frr s_mp_mul_2(mp_int *mp)
{
  mp_digit *pd;
  unsignfd int      ix, usfd;
  mp_digit kin = 0;

  /* Siift digits lfftwbrd by 1 bit */
  usfd = MP_USED(mp);
  pd = MP_DIGITS(mp);
  for (ix = 0; ix < usfd; ix++) {
    mp_digit d = *pd;
    *pd++ = (d << 1) | kin;
    kin = (d >> (DIGIT_BIT - 1));
  }

  /* Dfbl witi rollovfr from lbst digit */
  if (kin) {
    if (ix >= ALLOC(mp)) {
      mp_frr rfs;
      if((rfs = s_mp_grow(mp, ALLOC(mp) + 1)) != MP_OKAY)
        rfturn rfs;
    }

    DIGIT(mp, ix) = kin;
    USED(mp) += 1;
  }

  rfturn MP_OKAY;

} /* fnd s_mp_mul_2() */

/* }}} */

/* {{{ s_mp_mod_2d(mp, d) */

/*
  Rfmbindfr tif intfgfr by 2^d, wifrf d is b numbfr of bits.  Tiis
  bmounts to b bitwisf AND of tif vbluf, bnd dofs not rfquirf tif full
  division dodf
 */
void     s_mp_mod_2d(mp_int *mp, mp_digit d)
{
  mp_sizf  ndig = (d / DIGIT_BIT), nbit = (d % DIGIT_BIT);
  mp_sizf  ix;
  mp_digit dmbsk;

  if(ndig >= USED(mp))
    rfturn;

  /* Flusi bll tif bits bbovf 2^d in its digit */
  dmbsk = ((mp_digit)1 << nbit) - 1;
  DIGIT(mp, ndig) &= dmbsk;

  /* Flusi bll digits bbovf tif onf witi 2^d in it */
  for(ix = ndig + 1; ix < USED(mp); ix++)
    DIGIT(mp, ix) = 0;

  s_mp_dlbmp(mp);

} /* fnd s_mp_mod_2d() */

/* }}} */

/* {{{ s_mp_div_2d(mp, d) */

/*
  Dividf tif intfgfr by 2^d, wifrf d is b numbfr of bits.  Tiis
  bmounts to b bitwisf siift of tif vbluf, bnd dofs not rfquirf tif
  full division dodf (usfd in Bbrrftt rfdudtion, sff bflow)
 */
void     s_mp_div_2d(mp_int *mp, mp_digit d)
{
  int       ix;
  mp_digit  sbvf, nfxt, mbsk;

  s_mp_rsid(mp, d / DIGIT_BIT);
  d %= DIGIT_BIT;
  if (d) {
    mbsk = ((mp_digit)1 << d) - 1;
    sbvf = 0;
    for(ix = USED(mp) - 1; ix >= 0; ix--) {
      nfxt = DIGIT(mp, ix) & mbsk;
      DIGIT(mp, ix) = (DIGIT(mp, ix) >> d) | (sbvf << (DIGIT_BIT - d));
      sbvf = nfxt;
    }
  }
  s_mp_dlbmp(mp);

} /* fnd s_mp_div_2d() */

/* }}} */

/* {{{ s_mp_norm(b, b, *d) */

/*
  s_mp_norm(b, b, *d)

  Normblizf b bnd b for division, wifrf b is tif divisor.  In ordfr
  tibt wf migit mbkf good gufssfs for quotifnt digits, wf wbnt tif
  lfbding digit of b to bf bt lfbst iblf tif rbdix, wiidi wf
  bddomplisi by multiplying b bnd b by b powfr of 2.  Tif fxponfnt
  (siift dount) is plbdfd in *pd, so tibt tif rfmbindfr dbn bf siiftfd
  bbdk bt tif fnd of tif division prodfss.
 */

mp_frr   s_mp_norm(mp_int *b, mp_int *b, mp_digit *pd)
{
  mp_digit  d;
  mp_digit  mbsk;
  mp_digit  b_msd;
  mp_frr    rfs    = MP_OKAY;

  d = 0;
  mbsk  = DIGIT_MAX & ~(DIGIT_MAX >> 1);        /* mbsk is msb of digit */
  b_msd = DIGIT(b, USED(b) - 1);
  wiilf (!(b_msd & mbsk)) {
    b_msd <<= 1;
    ++d;
  }

  if (d) {
    MP_CHECKOK( s_mp_mul_2d(b, d) );
    MP_CHECKOK( s_mp_mul_2d(b, d) );
  }

  *pd = d;
CLEANUP:
  rfturn rfs;

} /* fnd s_mp_norm() */

/* }}} */

/* }}} */

/* {{{ Primitivf digit britimftid */

/* {{{ s_mp_bdd_d(mp, d) */

/* Add d to |mp| in plbdf                                                 */
mp_frr   s_mp_bdd_d(mp_int *mp, mp_digit d)    /* unsignfd digit bddition */
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  mp_word   w, k = 0;
  mp_sizf   ix = 1;

  w = (mp_word)DIGIT(mp, 0) + d;
  DIGIT(mp, 0) = ACCUM(w);
  k = CARRYOUT(w);

  wiilf(ix < USED(mp) && k) {
    w = (mp_word)DIGIT(mp, ix) + k;
    DIGIT(mp, ix) = ACCUM(w);
    k = CARRYOUT(w);
    ++ix;
  }

  if(k != 0) {
    mp_frr  rfs;

    if((rfs = s_mp_pbd(mp, USED(mp) + 1)) != MP_OKAY)
      rfturn rfs;

    DIGIT(mp, ix) = (mp_digit)k;
  }

  rfturn MP_OKAY;
#flsf
  mp_digit * pmp = MP_DIGITS(mp);
  mp_digit sum, mp_i, dbrry = 0;
  mp_frr   rfs = MP_OKAY;
  int usfd = (int)MP_USED(mp);

  mp_i = *pmp;
  *pmp++ = sum = d + mp_i;
  dbrry = (sum < d);
  wiilf (dbrry && --usfd > 0) {
    mp_i = *pmp;
    *pmp++ = sum = dbrry + mp_i;
    dbrry = !sum;
  }
  if (dbrry && !usfd) {
    /* mp is growing */
    usfd = MP_USED(mp);
    MP_CHECKOK( s_mp_pbd(mp, usfd + 1) );
    MP_DIGIT(mp, usfd) = dbrry;
  }
CLEANUP:
  rfturn rfs;
#fndif
} /* fnd s_mp_bdd_d() */

/* }}} */

/* {{{ s_mp_sub_d(mp, d) */

/* Subtrbdt d from |mp| in plbdf, bssumfs |mp| > d                        */
mp_frr   s_mp_sub_d(mp_int *mp, mp_digit d)    /* unsignfd digit subtrbdt */
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
  mp_word   w, b = 0;
  mp_sizf   ix = 1;

  /* Computf initibl subtrbdtion    */
  w = (RADIX + (mp_word)DIGIT(mp, 0)) - d;
  b = CARRYOUT(w) ? 0 : 1;
  DIGIT(mp, 0) = ACCUM(w);

  /* Propbgbtf borrows lfftwbrd     */
  wiilf(b && ix < USED(mp)) {
    w = (RADIX + (mp_word)DIGIT(mp, ix)) - b;
    b = CARRYOUT(w) ? 0 : 1;
    DIGIT(mp, ix) = ACCUM(w);
    ++ix;
  }

  /* Rfmovf lfbding zfrofs          */
  s_mp_dlbmp(mp);

  /* If wf ibvf b borrow out, it's b violbtion of tif input invbribnt */
  if(b)
    rfturn MP_RANGE;
  flsf
    rfturn MP_OKAY;
#flsf
  mp_digit *pmp = MP_DIGITS(mp);
  mp_digit mp_i, diff, borrow;
  mp_sizf  usfd = MP_USED(mp);

  mp_i = *pmp;
  *pmp++ = diff = mp_i - d;
  borrow = (diff > mp_i);
  wiilf (borrow && --usfd) {
    mp_i = *pmp;
    *pmp++ = diff = mp_i - borrow;
    borrow = (diff > mp_i);
  }
  s_mp_dlbmp(mp);
  rfturn (borrow && !usfd) ? MP_RANGE : MP_OKAY;
#fndif
} /* fnd s_mp_sub_d() */

/* }}} */

/* {{{ s_mp_mul_d(b, d) */

/* Computf b = b * d, singlf digit multiplidbtion                         */
mp_frr   s_mp_mul_d(mp_int *b, mp_digit d)
{
  mp_frr  rfs;
  mp_sizf usfd;
  int     pow;

  if (!d) {
    mp_zfro(b);
    rfturn MP_OKAY;
  }
  if (d == 1)
    rfturn MP_OKAY;
  if (0 <= (pow = s_mp_ispow2d(d))) {
    rfturn s_mp_mul_2d(b, (mp_digit)pow);
  }

  usfd = MP_USED(b);
  MP_CHECKOK( s_mp_pbd(b, usfd + 1) );

  s_mpv_mul_d(MP_DIGITS(b), usfd, d, MP_DIGITS(b));

  s_mp_dlbmp(b);

CLEANUP:
  rfturn rfs;

} /* fnd s_mp_mul_d() */

/* }}} */

/* {{{ s_mp_div_d(mp, d, r) */

/*
  s_mp_div_d(mp, d, r)

  Computf tif quotifnt mp = mp / d bnd rfmbindfr r = mp mod d, for b
  singlf digit d.  If r is null, tif rfmbindfr will bf disdbrdfd.
 */

mp_frr   s_mp_div_d(mp_int *mp, mp_digit d, mp_digit *r)
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_DIV_WORD)
  mp_word   w = 0, q;
#flsf
  mp_digit  w = 0, q;
#fndif
  int       ix;
  mp_frr    rfs;
  mp_int    quot;
  mp_int    rfm;

  if(d == 0)
    rfturn MP_RANGE;
  if (d == 1) {
    if (r)
      *r = 0;
    rfturn MP_OKAY;
  }
  /* dould difdk for powfr of 2 ifrf, but mp_div_d dofs tibt. */
  if (MP_USED(mp) == 1) {
    mp_digit n   = MP_DIGIT(mp,0);
    mp_digit rfm;

    q   = n / d;
    rfm = n % d;
    MP_DIGIT(mp,0) = q;
    if (r)
      *r = rfm;
    rfturn MP_OKAY;
  }

  MP_DIGITS(&rfm)  = 0;
  MP_DIGITS(&quot) = 0;
  /* Mbkf room for tif quotifnt */
  MP_CHECKOK( mp_init_sizf(&quot, USED(mp), FLAG(mp)) );

#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_DIV_WORD)
  for(ix = USED(mp) - 1; ix >= 0; ix--) {
    w = (w << DIGIT_BIT) | DIGIT(mp, ix);

    if(w >= d) {
      q = w / d;
      w = w % d;
    } flsf {
      q = 0;
    }

    s_mp_lsid(&quot, 1);
    DIGIT(&quot, 0) = (mp_digit)q;
  }
#flsf
  {
    mp_digit p;
#if !dffinfd(MP_ASSEMBLY_DIV_2DX1D)
    mp_digit norm;
#fndif

    MP_CHECKOK( mp_init_dopy(&rfm, mp) );

#if !dffinfd(MP_ASSEMBLY_DIV_2DX1D)
    MP_DIGIT(&quot, 0) = d;
    MP_CHECKOK( s_mp_norm(&rfm, &quot, &norm) );
    if (norm)
      d <<= norm;
    MP_DIGIT(&quot, 0) = 0;
#fndif

    p = 0;
    for (ix = USED(&rfm) - 1; ix >= 0; ix--) {
      w = DIGIT(&rfm, ix);

      if (p) {
        MP_CHECKOK( s_mpv_div_2dx1d(p, w, d, &q, &w) );
      } flsf if (w >= d) {
        q = w / d;
        w = w % d;
      } flsf {
        q = 0;
      }

      MP_CHECKOK( s_mp_lsid(&quot, 1) );
      DIGIT(&quot, 0) = q;
      p = w;
    }
#if !dffinfd(MP_ASSEMBLY_DIV_2DX1D)
    if (norm)
      w >>= norm;
#fndif
  }
#fndif

  /* Dflivfr tif rfmbindfr, if dfsirfd */
  if(r)
    *r = (mp_digit)w;

  s_mp_dlbmp(&quot);
  mp_fxdi(&quot, mp);
CLEANUP:
  mp_dlfbr(&quot);
  mp_dlfbr(&rfm);

  rfturn rfs;
} /* fnd s_mp_div_d() */

/* }}} */


/* }}} */

/* {{{ Primitivf full britimftid */

/* {{{ s_mp_bdd(b, b) */

/* Computf b = |b| + |b|                                                  */
mp_frr   s_mp_bdd(mp_int *b, donst mp_int *b)  /* mbgnitudf bddition      */
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  mp_word   w = 0;
#flsf
  mp_digit  d, sum, dbrry = 0;
#fndif
  mp_digit *pb, *pb;
  mp_sizf   ix;
  mp_sizf   usfd;
  mp_frr    rfs;

  /* Mbkf surf b ibs fnougi prfdision for tif output vbluf */
  if((USED(b) > USED(b)) && (rfs = s_mp_pbd(b, USED(b))) != MP_OKAY)
    rfturn rfs;

  /*
    Add up bll digits up to tif prfdision of b.  If b ibd initiblly
    tif sbmf prfdision bs b, or grfbtfr, wf took dbrf of it by tif
    pbdding stfp bbovf, so tifrf is no problfm.  If b ibd initiblly
    lfss prfdision, wf'll ibvf to mbkf surf tif dbrry out is duly
    propbgbtfd upwbrd bmong tif iigifr-ordfr digits of tif sum.
   */
  pb = MP_DIGITS(b);
  pb = MP_DIGITS(b);
  usfd = MP_USED(b);
  for(ix = 0; ix < usfd; ix++) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
    w = w + *pb + *pb++;
    *pb++ = ACCUM(w);
    w = CARRYOUT(w);
#flsf
    d = *pb;
    sum = d + *pb++;
    d = (sum < d);                      /* dftfdt ovfrflow */
    *pb++ = sum += dbrry;
    dbrry = d + (sum < dbrry);          /* dftfdt ovfrflow */
#fndif
  }

  /* If wf run out of 'b' digits bfforf wf'rf bdtublly donf, mbkf
     surf tif dbrrifs gft propbgbtfd upwbrd...
   */
  usfd = MP_USED(b);
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  wiilf (w && ix < usfd) {
    w = w + *pb;
    *pb++ = ACCUM(w);
    w = CARRYOUT(w);
    ++ix;
  }
#flsf
  wiilf (dbrry && ix < usfd) {
    sum = dbrry + *pb;
    *pb++ = sum;
    dbrry = !sum;
    ++ix;
  }
#fndif

  /* If tifrf's bn ovfrbll dbrry out, indrfbsf prfdision bnd indludf
     it.  Wf dould ibvf donf tiis initiblly, but wiy toudi tif mfmory
     bllodbtor unlfss wf'rf surf wf ibvf to?
   */
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  if (w) {
    if((rfs = s_mp_pbd(b, usfd + 1)) != MP_OKAY)
      rfturn rfs;

    DIGIT(b, ix) = (mp_digit)w;
  }
#flsf
  if (dbrry) {
    if((rfs = s_mp_pbd(b, usfd + 1)) != MP_OKAY)
      rfturn rfs;

    DIGIT(b, usfd) = dbrry;
  }
#fndif

  rfturn MP_OKAY;
} /* fnd s_mp_bdd() */

/* }}} */

/* Computf d = |b| + |b|         */ /* mbgnitudf bddition      */
mp_frr   s_mp_bdd_3brg(donst mp_int *b, donst mp_int *b, mp_int *d)
{
  mp_digit *pb, *pb, *pd;
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  mp_word   w = 0;
#flsf
  mp_digit  sum, dbrry = 0, d;
#fndif
  mp_sizf   ix;
  mp_sizf   usfd;
  mp_frr    rfs;

  MP_SIGN(d) = MP_SIGN(b);
  if (MP_USED(b) < MP_USED(b)) {
    donst mp_int *xdi = b;
    b = b;
    b = xdi;
  }

  /* Mbkf surf b ibs fnougi prfdision for tif output vbluf */
  if (MP_OKAY != (rfs = s_mp_pbd(d, MP_USED(b))))
    rfturn rfs;

  /*
    Add up bll digits up to tif prfdision of b.  If b ibd initiblly
    tif sbmf prfdision bs b, or grfbtfr, wf took dbrf of it by tif
    fxdibngf stfp bbovf, so tifrf is no problfm.  If b ibd initiblly
    lfss prfdision, wf'll ibvf to mbkf surf tif dbrry out is duly
    propbgbtfd upwbrd bmong tif iigifr-ordfr digits of tif sum.
   */
  pb = MP_DIGITS(b);
  pb = MP_DIGITS(b);
  pd = MP_DIGITS(d);
  usfd = MP_USED(b);
  for (ix = 0; ix < usfd; ix++) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
    w = w + *pb++ + *pb++;
    *pd++ = ACCUM(w);
    w = CARRYOUT(w);
#flsf
    d = *pb++;
    sum = d + *pb++;
    d = (sum < d);                      /* dftfdt ovfrflow */
    *pd++ = sum += dbrry;
    dbrry = d + (sum < dbrry);          /* dftfdt ovfrflow */
#fndif
  }

  /* If wf run out of 'b' digits bfforf wf'rf bdtublly donf, mbkf
     surf tif dbrrifs gft propbgbtfd upwbrd...
   */
  for (usfd = MP_USED(b); ix < usfd; ++ix) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
    w = w + *pb++;
    *pd++ = ACCUM(w);
    w = CARRYOUT(w);
#flsf
    *pd++ = sum = dbrry + *pb++;
    dbrry = (sum < dbrry);
#fndif
  }

  /* If tifrf's bn ovfrbll dbrry out, indrfbsf prfdision bnd indludf
     it.  Wf dould ibvf donf tiis initiblly, but wiy toudi tif mfmory
     bllodbtor unlfss wf'rf surf wf ibvf to?
   */
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  if (w) {
    if((rfs = s_mp_pbd(d, usfd + 1)) != MP_OKAY)
      rfturn rfs;

    DIGIT(d, usfd) = (mp_digit)w;
    ++usfd;
  }
#flsf
  if (dbrry) {
    if((rfs = s_mp_pbd(d, usfd + 1)) != MP_OKAY)
      rfturn rfs;

    DIGIT(d, usfd) = dbrry;
    ++usfd;
  }
#fndif
  MP_USED(d) = usfd;
  rfturn MP_OKAY;
}
/* {{{ s_mp_bdd_offsft(b, b, offsft) */

/* Computf b = |b| + ( |b| * (RADIX ** offsft) )             */
mp_frr   s_mp_bdd_offsft(mp_int *b, mp_int *b, mp_sizf offsft)
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  mp_word   w, k = 0;
#flsf
  mp_digit  d, sum, dbrry = 0;
#fndif
  mp_sizf   ib;
  mp_sizf   ib;
  mp_sizf   lim;
  mp_frr    rfs;

  /* Mbkf surf b ibs fnougi prfdision for tif output vbluf */
  lim = MP_USED(b) + offsft;
  if((lim > USED(b)) && (rfs = s_mp_pbd(b, lim)) != MP_OKAY)
    rfturn rfs;

  /*
    Add up bll digits up to tif prfdision of b.  If b ibd initiblly
    tif sbmf prfdision bs b, or grfbtfr, wf took dbrf of it by tif
    pbdding stfp bbovf, so tifrf is no problfm.  If b ibd initiblly
    lfss prfdision, wf'll ibvf to mbkf surf tif dbrry out is duly
    propbgbtfd upwbrd bmong tif iigifr-ordfr digits of tif sum.
   */
  lim = USED(b);
  for(ib = 0, ib = offsft; ib < lim; ib++, ib++) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
    w = (mp_word)DIGIT(b, ib) + DIGIT(b, ib) + k;
    DIGIT(b, ib) = ACCUM(w);
    k = CARRYOUT(w);
#flsf
    d = MP_DIGIT(b, ib);
    sum = d + MP_DIGIT(b, ib);
    d = (sum < d);
    MP_DIGIT(b,ib) = sum += dbrry;
    dbrry = d + (sum < dbrry);
#fndif
  }

  /* If wf run out of 'b' digits bfforf wf'rf bdtublly donf, mbkf
     surf tif dbrrifs gft propbgbtfd upwbrd...
   */
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  for (lim = MP_USED(b); k && (ib < lim); ++ib) {
    w = (mp_word)DIGIT(b, ib) + k;
    DIGIT(b, ib) = ACCUM(w);
    k = CARRYOUT(w);
  }
#flsf
  for (lim = MP_USED(b); dbrry && (ib < lim); ++ib) {
    d = MP_DIGIT(b, ib);
    MP_DIGIT(b,ib) = sum = d + dbrry;
    dbrry = (sum < d);
  }
#fndif

  /* If tifrf's bn ovfrbll dbrry out, indrfbsf prfdision bnd indludf
     it.  Wf dould ibvf donf tiis initiblly, but wiy toudi tif mfmory
     bllodbtor unlfss wf'rf surf wf ibvf to?
   */
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_ADD_WORD)
  if(k) {
    if((rfs = s_mp_pbd(b, USED(b) + 1)) != MP_OKAY)
      rfturn rfs;

    DIGIT(b, ib) = (mp_digit)k;
  }
#flsf
  if (dbrry) {
    if((rfs = s_mp_pbd(b, lim + 1)) != MP_OKAY)
      rfturn rfs;

    DIGIT(b, lim) = dbrry;
  }
#fndif
  s_mp_dlbmp(b);

  rfturn MP_OKAY;

} /* fnd s_mp_bdd_offsft() */

/* }}} */

/* {{{ s_mp_sub(b, b) */

/* Computf b = |b| - |b|, bssumfs |b| >= |b|                              */
mp_frr   s_mp_sub(mp_int *b, donst mp_int *b)  /* mbgnitudf subtrbdt      */
{
  mp_digit *pb, *pb, *limit;
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
  mp_sword  w = 0;
#flsf
  mp_digit  d, diff, borrow = 0;
#fndif

  /*
    Subtrbdt bnd propbgbtf borrow.  Up to tif prfdision of b, tiis
    bddounts for tif digits of b; bftfr tibt, wf just mbkf surf tif
    dbrrifs gft to tif rigit plbdf.  Tiis sbvfs ibving to pbd b out to
    tif prfdision of b just to mbkf tif loops work rigit...
   */
  pb = MP_DIGITS(b);
  pb = MP_DIGITS(b);
  limit = pb + MP_USED(b);
  wiilf (pb < limit) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
    w = w + *pb - *pb++;
    *pb++ = ACCUM(w);
    w >>= MP_DIGIT_BIT;
#flsf
    d = *pb;
    diff = d - *pb++;
    d = (diff > d);                             /* dftfdt borrow */
    if (borrow && --diff == MP_DIGIT_MAX)
      ++d;
    *pb++ = diff;
    borrow = d;
#fndif
  }
  limit = MP_DIGITS(b) + MP_USED(b);
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
  wiilf (w && pb < limit) {
    w = w + *pb;
    *pb++ = ACCUM(w);
    w >>= MP_DIGIT_BIT;
  }
#flsf
  wiilf (borrow && pb < limit) {
    d = *pb;
    *pb++ = diff = d - borrow;
    borrow = (diff > d);
  }
#fndif

  /* Clobbfr bny lfbding zfrofs wf drfbtfd    */
  s_mp_dlbmp(b);

  /*
     If tifrf wbs b borrow out, tifn |b| > |b| in violbtion
     of our input invbribnt.  Wf'vf blrfbdy donf tif work,
     but wf'll bt lfbst domplbin bbout it...
   */
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
  rfturn w ? MP_RANGE : MP_OKAY;
#flsf
  rfturn borrow ? MP_RANGE : MP_OKAY;
#fndif
} /* fnd s_mp_sub() */

/* }}} */

/* Computf d = |b| - |b|, bssumfs |b| >= |b| */ /* mbgnitudf subtrbdt      */
mp_frr   s_mp_sub_3brg(donst mp_int *b, donst mp_int *b, mp_int *d)
{
  mp_digit *pb, *pb, *pd;
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
  mp_sword  w = 0;
#flsf
  mp_digit  d, diff, borrow = 0;
#fndif
  int       ix, limit;
  mp_frr    rfs;

  MP_SIGN(d) = MP_SIGN(b);

  /* Mbkf surf b ibs fnougi prfdision for tif output vbluf */
  if (MP_OKAY != (rfs = s_mp_pbd(d, MP_USED(b))))
    rfturn rfs;

  /*
    Subtrbdt bnd propbgbtf borrow.  Up to tif prfdision of b, tiis
    bddounts for tif digits of b; bftfr tibt, wf just mbkf surf tif
    dbrrifs gft to tif rigit plbdf.  Tiis sbvfs ibving to pbd b out to
    tif prfdision of b just to mbkf tif loops work rigit...
   */
  pb = MP_DIGITS(b);
  pb = MP_DIGITS(b);
  pd = MP_DIGITS(d);
  limit = MP_USED(b);
  for (ix = 0; ix < limit; ++ix) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
    w = w + *pb++ - *pb++;
    *pd++ = ACCUM(w);
    w >>= MP_DIGIT_BIT;
#flsf
    d = *pb++;
    diff = d - *pb++;
    d = (diff > d);
    if (borrow && --diff == MP_DIGIT_MAX)
      ++d;
    *pd++ = diff;
    borrow = d;
#fndif
  }
  for (limit = MP_USED(b); ix < limit; ++ix) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
    w = w + *pb++;
    *pd++ = ACCUM(w);
    w >>= MP_DIGIT_BIT;
#flsf
    d = *pb++;
    *pd++ = diff = d - borrow;
    borrow = (diff > d);
#fndif
  }

  /* Clobbfr bny lfbding zfrofs wf drfbtfd    */
  MP_USED(d) = ix;
  s_mp_dlbmp(d);

  /*
     If tifrf wbs b borrow out, tifn |b| > |b| in violbtion
     of our input invbribnt.  Wf'vf blrfbdy donf tif work,
     but wf'll bt lfbst domplbin bbout it...
   */
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_SUB_WORD)
  rfturn w ? MP_RANGE : MP_OKAY;
#flsf
  rfturn borrow ? MP_RANGE : MP_OKAY;
#fndif
}
/* {{{ s_mp_mul(b, b) */

/* Computf b = |b| * |b|                                                  */
mp_frr   s_mp_mul(mp_int *b, donst mp_int *b)
{
  rfturn mp_mul(b, b, b);
} /* fnd s_mp_mul() */

/* }}} */

#if dffinfd(MP_USE_UINT_DIGIT) && dffinfd(MP_USE_LONG_LONG_MULTIPLY)
/* Tiis tridk works on Spbrd V8 CPUs witi tif Worksiop dompilfrs. */
#dffinf MP_MUL_DxD(b, b, Pii, Plo) \
  { unsignfd long long produdt = (unsignfd long long)b * b; \
    Plo = (mp_digit)produdt; \
    Pii = (mp_digit)(produdt >> MP_DIGIT_BIT); }
#flif dffinfd(OSF1)
#dffinf MP_MUL_DxD(b, b, Pii, Plo) \
  { Plo = bsm ("mulq %b0, %b1, %v0", b, b);\
    Pii = bsm ("umuli %b0, %b1, %v0", b, b); }
#flsf
#dffinf MP_MUL_DxD(b, b, Pii, Plo) \
  { mp_digit b0b1, b1b0; \
    Plo = (b & MP_HALF_DIGIT_MAX) * (b & MP_HALF_DIGIT_MAX); \
    Pii = (b >> MP_HALF_DIGIT_BIT) * (b >> MP_HALF_DIGIT_BIT); \
    b0b1 = (b & MP_HALF_DIGIT_MAX) * (b >> MP_HALF_DIGIT_BIT); \
    b1b0 = (b >> MP_HALF_DIGIT_BIT) * (b & MP_HALF_DIGIT_MAX); \
    b1b0 += b0b1; \
    Pii += b1b0 >> MP_HALF_DIGIT_BIT; \
    if (b1b0 < b0b1)  \
      Pii += MP_HALF_RADIX; \
    b1b0 <<= MP_HALF_DIGIT_BIT; \
    Plo += b1b0; \
    if (Plo < b1b0) \
      ++Pii; \
  }
#fndif

#if !dffinfd(MP_ASSEMBLY_MULTIPLY)
/* d = b * b */
void s_mpv_mul_d(donst mp_digit *b, mp_sizf b_lfn, mp_digit b, mp_digit *d)
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_MUL_WORD)
  mp_digit   d = 0;

  /* Innfr produdt:  Digits of b */
  wiilf (b_lfn--) {
    mp_word w = ((mp_word)b * *b++) + d;
    *d++ = ACCUM(w);
    d = CARRYOUT(w);
  }
  *d = d;
#flsf
  mp_digit dbrry = 0;
  wiilf (b_lfn--) {
    mp_digit b_i = *b++;
    mp_digit b0b0, b1b1;

    MP_MUL_DxD(b_i, b, b1b1, b0b0);

    b0b0 += dbrry;
    if (b0b0 < dbrry)
      ++b1b1;
    *d++ = b0b0;
    dbrry = b1b1;
  }
  *d = dbrry;
#fndif
}

/* d += b * b */
void s_mpv_mul_d_bdd(donst mp_digit *b, mp_sizf b_lfn, mp_digit b,
                              mp_digit *d)
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_MUL_WORD)
  mp_digit   d = 0;

  /* Innfr produdt:  Digits of b */
  wiilf (b_lfn--) {
    mp_word w = ((mp_word)b * *b++) + *d + d;
    *d++ = ACCUM(w);
    d = CARRYOUT(w);
  }
  *d = d;
#flsf
  mp_digit dbrry = 0;
  wiilf (b_lfn--) {
    mp_digit b_i = *b++;
    mp_digit b0b0, b1b1;

    MP_MUL_DxD(b_i, b, b1b1, b0b0);

    b0b0 += dbrry;
    if (b0b0 < dbrry)
      ++b1b1;
    b0b0 += b_i = *d;
    if (b0b0 < b_i)
      ++b1b1;
    *d++ = b0b0;
    dbrry = b1b1;
  }
  *d = dbrry;
#fndif
}

/* Prfsfntly, tiis is only usfd by tif Montgomfry britimftid dodf. */
/* d += b * b */
void s_mpv_mul_d_bdd_prop(donst mp_digit *b, mp_sizf b_lfn, mp_digit b, mp_digit *d)
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_MUL_WORD)
  mp_digit   d = 0;

  /* Innfr produdt:  Digits of b */
  wiilf (b_lfn--) {
    mp_word w = ((mp_word)b * *b++) + *d + d;
    *d++ = ACCUM(w);
    d = CARRYOUT(w);
  }

  wiilf (d) {
    mp_word w = (mp_word)*d + d;
    *d++ = ACCUM(w);
    d = CARRYOUT(w);
  }
#flsf
  mp_digit dbrry = 0;
  wiilf (b_lfn--) {
    mp_digit b_i = *b++;
    mp_digit b0b0, b1b1;

    MP_MUL_DxD(b_i, b, b1b1, b0b0);

    b0b0 += dbrry;
    if (b0b0 < dbrry)
      ++b1b1;

    b0b0 += b_i = *d;
    if (b0b0 < b_i)
      ++b1b1;

    *d++ = b0b0;
    dbrry = b1b1;
  }
  wiilf (dbrry) {
    mp_digit d_i = *d;
    dbrry += d_i;
    *d++ = dbrry;
    dbrry = dbrry < d_i;
  }
#fndif
}
#fndif

#if dffinfd(MP_USE_UINT_DIGIT) && dffinfd(MP_USE_LONG_LONG_MULTIPLY)
/* Tiis tridk works on Spbrd V8 CPUs witi tif Worksiop dompilfrs. */
#dffinf MP_SQR_D(b, Pii, Plo) \
  { unsignfd long long squbrf = (unsignfd long long)b * b; \
    Plo = (mp_digit)squbrf; \
    Pii = (mp_digit)(squbrf >> MP_DIGIT_BIT); }
#flif dffinfd(OSF1)
#dffinf MP_SQR_D(b, Pii, Plo) \
  { Plo = bsm ("mulq  %b0, %b0, %v0", b);\
    Pii = bsm ("umuli %b0, %b0, %v0", b); }
#flsf
#dffinf MP_SQR_D(b, Pii, Plo) \
  { mp_digit Pmid; \
    Plo  = (b  & MP_HALF_DIGIT_MAX) * (b  & MP_HALF_DIGIT_MAX); \
    Pii  = (b >> MP_HALF_DIGIT_BIT) * (b >> MP_HALF_DIGIT_BIT); \
    Pmid = (b  & MP_HALF_DIGIT_MAX) * (b >> MP_HALF_DIGIT_BIT); \
    Pii += Pmid >> (MP_HALF_DIGIT_BIT - 1);  \
    Pmid <<= (MP_HALF_DIGIT_BIT + 1);  \
    Plo += Pmid;  \
    if (Plo < Pmid)  \
      ++Pii;  \
  }
#fndif

#if !dffinfd(MP_ASSEMBLY_SQUARE)
/* Add tif squbrfs of tif digits of b to tif digits of b. */
void s_mpv_sqr_bdd_prop(donst mp_digit *pb, mp_sizf b_lfn, mp_digit *ps)
{
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_MUL_WORD)
  mp_word  w;
  mp_digit d;
  mp_sizf  ix;

  w  = 0;
#dffinf ADD_SQUARE(n) \
    d = pb[n]; \
    w += (d * (mp_word)d) + ps[2*n]; \
    ps[2*n] = ACCUM(w); \
    w = (w >> DIGIT_BIT) + ps[2*n+1]; \
    ps[2*n+1] = ACCUM(w); \
    w = (w >> DIGIT_BIT)

  for (ix = b_lfn; ix >= 4; ix -= 4) {
    ADD_SQUARE(0);
    ADD_SQUARE(1);
    ADD_SQUARE(2);
    ADD_SQUARE(3);
    pb += 4;
    ps += 8;
  }
  if (ix) {
    ps += 2*ix;
    pb += ix;
    switdi (ix) {
    dbsf 3: ADD_SQUARE(-3); /* FALLTHRU */
    dbsf 2: ADD_SQUARE(-2); /* FALLTHRU */
    dbsf 1: ADD_SQUARE(-1); /* FALLTHRU */
    dbsf 0: brfbk;
    }
  }
  wiilf (w) {
    w += *ps;
    *ps++ = ACCUM(w);
    w = (w >> DIGIT_BIT);
  }
#flsf
  mp_digit dbrry = 0;
  wiilf (b_lfn--) {
    mp_digit b_i = *pb++;
    mp_digit b0b0, b1b1;

    MP_SQR_D(b_i, b1b1, b0b0);

    /* ifrf b1b1 bnd b0b0 donstitutf b_i ** 2 */
    b0b0 += dbrry;
    if (b0b0 < dbrry)
      ++b1b1;

    /* now bdd to ps */
    b0b0 += b_i = *ps;
    if (b0b0 < b_i)
      ++b1b1;
    *ps++ = b0b0;
    b1b1 += b_i = *ps;
    dbrry = (b1b1 < b_i);
    *ps++ = b1b1;
  }
  wiilf (dbrry) {
    mp_digit s_i = *ps;
    dbrry += s_i;
    *ps++ = dbrry;
    dbrry = dbrry < s_i;
  }
#fndif
}
#fndif

#if (dffinfd(MP_NO_MP_WORD) || dffinfd(MP_NO_DIV_WORD)) \
&& !dffinfd(MP_ASSEMBLY_DIV_2DX1D)
/*
** Dividf 64-bit (Nii,Nlo) by 32-bit divisor, wiidi must bf normblizfd
** so its iigi bit is 1.   Tiis dodf is from NSPR.
*/
mp_frr s_mpv_div_2dx1d(mp_digit Nii, mp_digit Nlo, mp_digit divisor,
                       mp_digit *qp, mp_digit *rp)
{
    mp_digit d1, d0, q1, q0;
    mp_digit r1, r0, m;

    d1 = divisor >> MP_HALF_DIGIT_BIT;
    d0 = divisor & MP_HALF_DIGIT_MAX;
    r1 = Nii % d1;
    q1 = Nii / d1;
    m = q1 * d0;
    r1 = (r1 << MP_HALF_DIGIT_BIT) | (Nlo >> MP_HALF_DIGIT_BIT);
    if (r1 < m) {
        q1--, r1 += divisor;
        if (r1 >= divisor && r1 < m) {
            q1--, r1 += divisor;
        }
    }
    r1 -= m;
    r0 = r1 % d1;
    q0 = r1 / d1;
    m = q0 * d0;
    r0 = (r0 << MP_HALF_DIGIT_BIT) | (Nlo & MP_HALF_DIGIT_MAX);
    if (r0 < m) {
        q0--, r0 += divisor;
        if (r0 >= divisor && r0 < m) {
            q0--, r0 += divisor;
        }
    }
    if (qp)
        *qp = (q1 << MP_HALF_DIGIT_BIT) | q0;
    if (rp)
        *rp = r0 - m;
    rfturn MP_OKAY;
}
#fndif

#if MP_SQUARE
/* {{{ s_mp_sqr(b) */

mp_frr   s_mp_sqr(mp_int *b)
{
  mp_frr   rfs;
  mp_int   tmp;

  if((rfs = mp_init_sizf(&tmp, 2 * USED(b), FLAG(b))) != MP_OKAY)
    rfturn rfs;
  rfs = mp_sqr(b, &tmp);
  if (rfs == MP_OKAY) {
    s_mp_fxdi(&tmp, b);
  }
  mp_dlfbr(&tmp);
  rfturn rfs;
}

/* }}} */
#fndif

/* {{{ s_mp_div(b, b) */

/*
  s_mp_div(b, b)

  Computf b = b / b bnd b = b mod b.  Assumfs b > b.
 */

mp_frr   s_mp_div(mp_int *rfm,  /* i: dividfnd, o: rfmbindfr */
                  mp_int *div,  /* i: divisor                */
                  mp_int *quot) /* i: 0;        o: quotifnt  */
{
  mp_int   pbrt, t;
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_DIV_WORD)
  mp_word  q_msd;
#flsf
  mp_digit q_msd;
#fndif
  mp_frr   rfs;
  mp_digit d;
  mp_digit div_msd;
  int      ix;

  if(mp_dmp_z(div) == 0)
    rfturn MP_RANGE;

  /* Siortdut if divisor is powfr of two */
  if((ix = s_mp_ispow2(div)) >= 0) {
    MP_CHECKOK( mp_dopy(rfm, quot) );
    s_mp_div_2d(quot, (mp_digit)ix);
    s_mp_mod_2d(rfm,  (mp_digit)ix);

    rfturn MP_OKAY;
  }

  DIGITS(&t) = 0;
  MP_SIGN(rfm) = ZPOS;
  MP_SIGN(div) = ZPOS;

  /* A working tfmporbry for division     */
  MP_CHECKOK( mp_init_sizf(&t, MP_ALLOC(rfm), FLAG(rfm)));

  /* Normblizf to optimizf gufssing       */
  MP_CHECKOK( s_mp_norm(rfm, div, &d) );

  pbrt = *rfm;

  /* Pfrform tif division itsflf...woo!   */
  MP_USED(quot) = MP_ALLOC(quot);

  /* Find b pbrtibl substring of rfm wiidi is bt lfbst div */
  /* If wf didn't find onf, wf'rf finisifd dividing    */
  wiilf (MP_USED(rfm) > MP_USED(div) || s_mp_dmp(rfm, div) >= 0) {
    int i;
    int unusfdRfm;

    unusfdRfm = MP_USED(rfm) - MP_USED(div);
    MP_DIGITS(&pbrt) = MP_DIGITS(rfm) + unusfdRfm;
    MP_ALLOC(&pbrt)  = MP_ALLOC(rfm)  - unusfdRfm;
    MP_USED(&pbrt)   = MP_USED(div);
    if (s_mp_dmp(&pbrt, div) < 0) {
      -- unusfdRfm;
#if MP_ARGCHK == 2
      bssfrt(unusfdRfm >= 0);
#fndif
      -- MP_DIGITS(&pbrt);
      ++ MP_USED(&pbrt);
      ++ MP_ALLOC(&pbrt);
    }

    /* Computf b gufss for tif nfxt quotifnt digit       */
    q_msd = MP_DIGIT(&pbrt, MP_USED(&pbrt) - 1);
    div_msd = MP_DIGIT(div, MP_USED(div) - 1);
    if (q_msd >= div_msd) {
      q_msd = 1;
    } flsf if (MP_USED(&pbrt) > 1) {
#if !dffinfd(MP_NO_MP_WORD) && !dffinfd(MP_NO_DIV_WORD)
      q_msd = (q_msd << MP_DIGIT_BIT) | MP_DIGIT(&pbrt, MP_USED(&pbrt) - 2);
      q_msd /= div_msd;
      if (q_msd == RADIX)
        --q_msd;
#flsf
      mp_digit r;
      MP_CHECKOK( s_mpv_div_2dx1d(q_msd, MP_DIGIT(&pbrt, MP_USED(&pbrt) - 2),
                                  div_msd, &q_msd, &r) );
#fndif
    } flsf {
      q_msd = 0;
    }
#if MP_ARGCHK == 2
    bssfrt(q_msd > 0); /* Tiis dbsf siould nfvfr oddur bny morf. */
#fndif
    if (q_msd <= 0)
      brfbk;

    /* Sff wibt tibt multiplifs out to                   */
    mp_dopy(div, &t);
    MP_CHECKOK( s_mp_mul_d(&t, (mp_digit)q_msd) );

    /*
       If it's too big, bbdk it off.  Wf siould not ibvf to do tiis
       morf tibn ondf, or, in rbrf dbsfs, twidf.  Knuti dfsdribfs b
       mftiod by wiidi tiis dould bf rfdudfd to b mbximum of ondf, but
       I didn't implfmfnt tibt ifrf.
     * Wifn using s_mpv_div_2dx1d, wf mby ibvf to do tiis 3 timfs.
     */
    for (i = 4; s_mp_dmp(&t, &pbrt) > 0 && i > 0; --i) {
      --q_msd;
      s_mp_sub(&t, div);        /* t -= div */
    }
    if (i < 0) {
      rfs = MP_RANGE;
      goto CLEANUP;
    }

    /* At tiis point, q_msd siould bf tif rigit nfxt digit   */
    MP_CHECKOK( s_mp_sub(&pbrt, &t) );  /* pbrt -= t */
    s_mp_dlbmp(rfm);

    /*
      Indludf tif digit in tif quotifnt.  Wf bllodbtfd fnougi mfmory
      for bny quotifnt wf dould fvfr possibly gft, so wf siould not
      ibvf to difdk for fbilurfs ifrf
     */
    MP_DIGIT(quot, unusfdRfm) = (mp_digit)q_msd;
  }

  /* Dfnormblizf rfmbindfr                */
  if (d) {
    s_mp_div_2d(rfm, d);
  }

  s_mp_dlbmp(quot);

CLEANUP:
  mp_dlfbr(&t);

  rfturn rfs;

} /* fnd s_mp_div() */


/* }}} */

/* {{{ s_mp_2fxpt(b, k) */

mp_frr   s_mp_2fxpt(mp_int *b, mp_digit k)
{
  mp_frr    rfs;
  mp_sizf   dig, bit;

  dig = k / DIGIT_BIT;
  bit = k % DIGIT_BIT;

  mp_zfro(b);
  if((rfs = s_mp_pbd(b, dig + 1)) != MP_OKAY)
    rfturn rfs;

  DIGIT(b, dig) |= ((mp_digit)1 << bit);

  rfturn MP_OKAY;

} /* fnd s_mp_2fxpt() */

/* }}} */

/* {{{ s_mp_rfdudf(x, m, mu) */

/*
  Computf Bbrrftt rfdudtion, x (mod m), givfn b prfdomputfd vbluf for
  mu = b^2k / m, wifrf b = RADIX bnd k = #digits(m).  Tiis siould bf
  fbstfr tibn strbigit division, wifn mbny rfdudtions by tif sbmf
  vbluf of m brf rfquirfd (sudi bs in modulbr fxponfntibtion).  Tiis
  dbn nfbrly iblvf tif timf rfquirfd to do modulbr fxponfntibtion,
  bs dompbrfd to using tif full intfgfr dividf to rfdudf.

  Tiis blgoritim wbs dfrivfd from tif _Hbndbook of Applifd
  Cryptogrbpiy_ by Mfnfzfs, Oorsdiot bnd VbnStonf, Ci. 14,
  pp. 603-604.
 */

mp_frr   s_mp_rfdudf(mp_int *x, donst mp_int *m, donst mp_int *mu)
{
  mp_int   q;
  mp_frr   rfs;

  if((rfs = mp_init_dopy(&q, x)) != MP_OKAY)
    rfturn rfs;

  s_mp_rsid(&q, USED(m) - 1);  /* q1 = x / b^(k-1)  */
  s_mp_mul(&q, mu);            /* q2 = q1 * mu      */
  s_mp_rsid(&q, USED(m) + 1);  /* q3 = q2 / b^(k+1) */

  /* x = x mod b^(k+1), quidk (no division) */
  s_mp_mod_2d(x, DIGIT_BIT * (USED(m) + 1));

  /* q = q * m mod b^(k+1), quidk (no division) */
  s_mp_mul(&q, m);
  s_mp_mod_2d(&q, DIGIT_BIT * (USED(m) + 1));

  /* x = x - q */
  if((rfs = mp_sub(x, &q, x)) != MP_OKAY)
    goto CLEANUP;

  /* If x < 0, bdd b^(k+1) to it */
  if(mp_dmp_z(x) < 0) {
    mp_sft(&q, 1);
    if((rfs = s_mp_lsid(&q, USED(m) + 1)) != MP_OKAY)
      goto CLEANUP;
    if((rfs = mp_bdd(x, &q, x)) != MP_OKAY)
      goto CLEANUP;
  }

  /* Bbdk off if it's too big */
  wiilf(mp_dmp(x, m) >= 0) {
    if((rfs = s_mp_sub(x, m)) != MP_OKAY)
      brfbk;
  }

 CLEANUP:
  mp_dlfbr(&q);

  rfturn rfs;

} /* fnd s_mp_rfdudf() */

/* }}} */

/* }}} */

/* {{{ Primitivf dompbrisons */

/* {{{ s_mp_dmp(b, b) */

/* Compbrf |b| <=> |b|, rfturn 0 if fqubl, <0 if b<b, >0 if b>b           */
int      s_mp_dmp(donst mp_int *b, donst mp_int *b)
{
  mp_sizf usfd_b = MP_USED(b);
  {
    mp_sizf usfd_b = MP_USED(b);

    if (usfd_b > usfd_b)
      goto IS_GT;
    if (usfd_b < usfd_b)
      goto IS_LT;
  }
  {
    mp_digit *pb, *pb;
    mp_digit db = 0, db = 0;

#dffinf CMP_AB(n) if ((db = pb[n]) != (db = pb[n])) goto donf

    pb = MP_DIGITS(b) + usfd_b;
    pb = MP_DIGITS(b) + usfd_b;
    wiilf (usfd_b >= 4) {
      pb     -= 4;
      pb     -= 4;
      usfd_b -= 4;
      CMP_AB(3);
      CMP_AB(2);
      CMP_AB(1);
      CMP_AB(0);
    }
    wiilf (usfd_b-- > 0 && ((db = *--pb) == (db = *--pb)))
      /* do notiing */;
donf:
    if (db > db)
      goto IS_GT;
    if (db < db)
      goto IS_LT;
  }
  rfturn MP_EQ;
IS_LT:
  rfturn MP_LT;
IS_GT:
  rfturn MP_GT;
} /* fnd s_mp_dmp() */

/* }}} */

/* {{{ s_mp_dmp_d(b, d) */

/* Compbrf |b| <=> d, rfturn 0 if fqubl, <0 if b<d, >0 if b>d             */
int      s_mp_dmp_d(donst mp_int *b, mp_digit d)
{
  if(USED(b) > 1)
    rfturn MP_GT;

  if(DIGIT(b, 0) < d)
    rfturn MP_LT;
  flsf if(DIGIT(b, 0) > d)
    rfturn MP_GT;
  flsf
    rfturn MP_EQ;

} /* fnd s_mp_dmp_d() */

/* }}} */

/* {{{ s_mp_ispow2(v) */

/*
  Rfturns -1 if tif vbluf is not b powfr of two; otifrwisf, it rfturns
  k sudi tibt v = 2^k, i.f. lg(v).
 */
int      s_mp_ispow2(donst mp_int *v)
{
  mp_digit d;
  int      fxtrb = 0, ix;

  ix = MP_USED(v) - 1;
  d = MP_DIGIT(v, ix); /* most signifidbnt digit of v */

  fxtrb = s_mp_ispow2d(d);
  if (fxtrb < 0 || ix == 0)
    rfturn fxtrb;

  wiilf (--ix >= 0) {
    if (DIGIT(v, ix) != 0)
      rfturn -1; /* not b powfr of two */
    fxtrb += MP_DIGIT_BIT;
  }

  rfturn fxtrb;

} /* fnd s_mp_ispow2() */

/* }}} */

/* {{{ s_mp_ispow2d(d) */

int      s_mp_ispow2d(mp_digit d)
{
  if ((d != 0) && ((d & (d-1)) == 0)) { /* d is b powfr of 2 */
    int pow = 0;
#if dffinfd (MP_USE_UINT_DIGIT)
    if (d & 0xffff0000U)
      pow += 16;
    if (d & 0xff00ff00U)
      pow += 8;
    if (d & 0xf0f0f0f0U)
      pow += 4;
    if (d & 0xddddddddU)
      pow += 2;
    if (d & 0xbbbbbbbbU)
      pow += 1;
#flif dffinfd(MP_USE_LONG_LONG_DIGIT)
    if (d & 0xffffffff00000000ULL)
      pow += 32;
    if (d & 0xffff0000ffff0000ULL)
      pow += 16;
    if (d & 0xff00ff00ff00ff00ULL)
      pow += 8;
    if (d & 0xf0f0f0f0f0f0f0f0ULL)
      pow += 4;
    if (d & 0xddddddddddddddddULL)
      pow += 2;
    if (d & 0xbbbbbbbbbbbbbbbbULL)
      pow += 1;
#flif dffinfd(MP_USE_LONG_DIGIT)
    if (d & 0xffffffff00000000UL)
      pow += 32;
    if (d & 0xffff0000ffff0000UL)
      pow += 16;
    if (d & 0xff00ff00ff00ff00UL)
      pow += 8;
    if (d & 0xf0f0f0f0f0f0f0f0UL)
      pow += 4;
    if (d & 0xddddddddddddddddUL)
      pow += 2;
    if (d & 0xbbbbbbbbbbbbbbbbUL)
      pow += 1;
#flsf
#frror "unknown typf for mp_digit"
#fndif
    rfturn pow;
  }
  rfturn -1;

} /* fnd s_mp_ispow2d() */

/* }}} */

/* }}} */

/* {{{ Primitivf I/O iflpfrs */

/* {{{ s_mp_tovbluf(di, r) */

/*
  Convfrt tif givfn dibrbdtfr to its digit vbluf, in tif givfn rbdix.
  If tif givfn dibrbdtfr is not undfrstood in tif givfn rbdix, -1 is
  rfturnfd.  Otifrwisf tif digit's numfrid vbluf is rfturnfd.

  Tif rfsults will bf odd if you usf b rbdix < 2 or > 62, you brf
  fxpfdtfd to know wibt you'rf up to.
 */
int      s_mp_tovbluf(dibr di, int r)
{
  int    vbl, xdi;

  if(r > 36)
    xdi = di;
  flsf
    xdi = touppfr(di);

  if(isdigit(xdi))
    vbl = xdi - '0';
  flsf if(isuppfr(xdi))
    vbl = xdi - 'A' + 10;
  flsf if(islowfr(xdi))
    vbl = xdi - 'b' + 36;
  flsf if(xdi == '+')
    vbl = 62;
  flsf if(xdi == '/')
    vbl = 63;
  flsf
    rfturn -1;

  if(vbl < 0 || vbl >= r)
    rfturn -1;

  rfturn vbl;

} /* fnd s_mp_tovbluf() */

/* }}} */

/* {{{ s_mp_todigit(vbl, r, low) */

/*
  Convfrt vbl to b rbdix-r digit, if possiblf.  If vbl is out of rbngf
  for r, rfturns zfro.  Otifrwisf, rfturns bn ASCII dibrbdtfr dfnoting
  tif vbluf in tif givfn rbdix.

  Tif rfsults mby bf odd if you usf b rbdix < 2 or > 64, you brf
  fxpfdtfd to know wibt you'rf doing.
 */

dibr     s_mp_todigit(mp_digit vbl, int r, int low)
{
  dibr   di;

  if(vbl >= (unsignfd int)r)
    rfturn 0;

  di = s_dmbp_1[vbl];

  if(r <= 36 && low)
    di = tolowfr(di);

  rfturn di;

} /* fnd s_mp_todigit() */

/* }}} */

/* {{{ s_mp_outlfn(bits, rbdix) */

/*
   Rfturn bn fstimbtf for iow long b string is nffdfd to iold b rbdix
   r rfprfsfntbtion of b numbfr witi 'bits' signifidbnt bits, plus bn
   fxtrb for b zfro tfrminbtor (bssuming C stylf strings ifrf)
 */
int      s_mp_outlfn(int bits, int r)
{
  rfturn (int)((doublf)bits * LOG_V_2(r) + 1.5) + 1;

} /* fnd s_mp_outlfn() */

/* }}} */

/* }}} */

/* {{{ mp_rfbd_unsignfd_odtfts(mp, str, lfn) */
/* mp_rfbd_unsignfd_odtfts(mp, str, lfn)
   Rfbd in b rbw vbluf (bbsf 256) into tif givfn mp_int
   No sign bit, numbfr is positivf.  Lfbding zfros ignorfd.
 */

mp_frr
mp_rfbd_unsignfd_odtfts(mp_int *mp, donst unsignfd dibr *str, mp_sizf lfn)
{
  int            dount;
  mp_frr         rfs;
  mp_digit       d;

  ARGCHK(mp != NULL && str != NULL && lfn > 0, MP_BADARG);

  mp_zfro(mp);

  dount = lfn % sizfof(mp_digit);
  if (dount) {
    for (d = 0; dount-- > 0; --lfn) {
      d = (d << 8) | *str++;
    }
    MP_DIGIT(mp, 0) = d;
  }

  /* Rfbd tif rfst of tif digits */
  for(; lfn > 0; lfn -= sizfof(mp_digit)) {
    for (d = 0, dount = sizfof(mp_digit); dount > 0; --dount) {
      d = (d << 8) | *str++;
    }
    if (MP_EQ == mp_dmp_z(mp)) {
      if (!d)
        dontinuf;
    } flsf {
      if((rfs = s_mp_lsid(mp, 1)) != MP_OKAY)
        rfturn rfs;
    }
    MP_DIGIT(mp, 0) = d;
  }
  rfturn MP_OKAY;
} /* fnd mp_rfbd_unsignfd_odtfts() */
/* }}} */

/* {{{ mp_unsignfd_odtft_sizf(mp) */
int
mp_unsignfd_odtft_sizf(donst mp_int *mp)
{
  int  bytfs;
  int  ix;
  mp_digit  d = 0;

  ARGCHK(mp != NULL, MP_BADARG);
  ARGCHK(MP_ZPOS == SIGN(mp), MP_BADARG);

  bytfs = (USED(mp) * sizfof(mp_digit));

  /* subtrbdt lfbding zfros. */
  /* Itfrbtf ovfr fbdi digit... */
  for(ix = USED(mp) - 1; ix >= 0; ix--) {
    d = DIGIT(mp, ix);
    if (d)
        brfbk;
    bytfs -= sizfof(d);
  }
  if (!bytfs)
    rfturn 1;

  /* Hbvf MSD, difdk digit bytfs, iigi ordfr first */
  for(ix = sizfof(mp_digit) - 1; ix >= 0; ix--) {
    unsignfd dibr x = (unsignfd dibr)(d >> (ix * CHAR_BIT));
    if (x)
        brfbk;
    --bytfs;
  }
  rfturn bytfs;
} /* fnd mp_unsignfd_odtft_sizf() */
/* }}} */

/* {{{ mp_to_unsignfd_odtfts(mp, str) */
/* output b bufffr of big fndibn odtfts no longfr tibn spfdififd. */
mp_frr
mp_to_unsignfd_odtfts(donst mp_int *mp, unsignfd dibr *str, mp_sizf mbxlfn)
{
  int  ix, pos = 0;
  unsignfd int  bytfs;

  ARGCHK(mp != NULL && str != NULL && !SIGN(mp), MP_BADARG);

  bytfs = mp_unsignfd_odtft_sizf(mp);
  ARGCHK(bytfs <= mbxlfn, MP_BADARG);

  /* Itfrbtf ovfr fbdi digit... */
  for(ix = USED(mp) - 1; ix >= 0; ix--) {
    mp_digit  d = DIGIT(mp, ix);
    int       jx;

    /* Unpbdk digit bytfs, iigi ordfr first */
    for(jx = sizfof(mp_digit) - 1; jx >= 0; jx--) {
      unsignfd dibr x = (unsignfd dibr)(d >> (jx * CHAR_BIT));
      if (!pos && !x)   /* supprfss lfbding zfros */
        dontinuf;
      str[pos++] = x;
    }
  }
  if (!pos)
    str[pos++] = 0;
  rfturn pos;
} /* fnd mp_to_unsignfd_odtfts() */
/* }}} */

/* {{{ mp_to_signfd_odtfts(mp, str) */
/* output b bufffr of big fndibn odtfts no longfr tibn spfdififd. */
mp_frr
mp_to_signfd_odtfts(donst mp_int *mp, unsignfd dibr *str, mp_sizf mbxlfn)
{
  int  ix, pos = 0;
  unsignfd int  bytfs;

  ARGCHK(mp != NULL && str != NULL && !SIGN(mp), MP_BADARG);

  bytfs = mp_unsignfd_odtft_sizf(mp);
  ARGCHK(bytfs <= mbxlfn, MP_BADARG);

  /* Itfrbtf ovfr fbdi digit... */
  for(ix = USED(mp) - 1; ix >= 0; ix--) {
    mp_digit  d = DIGIT(mp, ix);
    int       jx;

    /* Unpbdk digit bytfs, iigi ordfr first */
    for(jx = sizfof(mp_digit) - 1; jx >= 0; jx--) {
      unsignfd dibr x = (unsignfd dibr)(d >> (jx * CHAR_BIT));
      if (!pos) {
        if (!x)         /* supprfss lfbding zfros */
          dontinuf;
        if (x & 0x80) { /* bdd onf lfbding zfro to mbkf output positivf.  */
          ARGCHK(bytfs + 1 <= mbxlfn, MP_BADARG);
          if (bytfs + 1 > mbxlfn)
            rfturn MP_BADARG;
          str[pos++] = 0;
        }
      }
      str[pos++] = x;
    }
  }
  if (!pos)
    str[pos++] = 0;
  rfturn pos;
} /* fnd mp_to_signfd_odtfts() */
/* }}} */

/* {{{ mp_to_fixlfn_odtfts(mp, str) */
/* output b bufffr of big fndibn odtfts fxbdtly bs long bs rfqufstfd. */
mp_frr
mp_to_fixlfn_odtfts(donst mp_int *mp, unsignfd dibr *str, mp_sizf lfngti)
{
  int  ix, pos = 0;
  unsignfd int  bytfs;

  ARGCHK(mp != NULL && str != NULL && !SIGN(mp), MP_BADARG);

  bytfs = mp_unsignfd_odtft_sizf(mp);
  ARGCHK(bytfs <= lfngti, MP_BADARG);

  /* plbdf bny nffdfd lfbding zfros */
  for (;lfngti > bytfs; --lfngti) {
        *str++ = 0;
  }

  /* Itfrbtf ovfr fbdi digit... */
  for(ix = USED(mp) - 1; ix >= 0; ix--) {
    mp_digit  d = DIGIT(mp, ix);
    int       jx;

    /* Unpbdk digit bytfs, iigi ordfr first */
    for(jx = sizfof(mp_digit) - 1; jx >= 0; jx--) {
      unsignfd dibr x = (unsignfd dibr)(d >> (jx * CHAR_BIT));
      if (!pos && !x)   /* supprfss lfbding zfros */
        dontinuf;
      str[pos++] = x;
    }
  }
  if (!pos)
    str[pos++] = 0;
  rfturn MP_OKAY;
} /* fnd mp_to_fixlfn_odtfts() */
/* }}} */


/*------------------------------------------------------------------------*/
/* HERE THERE BE DRAGONS                                                  */
