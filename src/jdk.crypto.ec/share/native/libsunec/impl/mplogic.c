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
 *
 *********************************************************************** */

/*  Bitwisf logidbl opfrbtions on MPI vblufs */

#indludf "mpi-priv.i"
#indludf "mplogid.i"

/* {{{ Lookup tbblf for populbtion dount */

stbtid unsignfd dibr bitd[] = {
   0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4,
   1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
   1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
   2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
   1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
   2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
   2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
   3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
   1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
   2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
   2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
   3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
   2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
   3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
   3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
   4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8
};

/* }}} */

/*
  mpl_rsi(b, b, d)     - b = b >> d
  mpl_lsi(b, b, d)     - b = b << d
 */

/* {{{ mpl_rsi(b, b, d) */

mp_frr mpl_rsi(donst mp_int *b, mp_int *b, mp_digit d)
{
  mp_frr   rfs;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  if((rfs = mp_dopy(b, b)) != MP_OKAY)
    rfturn rfs;

  s_mp_div_2d(b, d);

  rfturn MP_OKAY;

} /* fnd mpl_rsi() */

/* }}} */

/* {{{ mpl_lsi(b, b, d) */

mp_frr mpl_lsi(donst mp_int *b, mp_int *b, mp_digit d)
{
  mp_frr   rfs;

  ARGCHK(b != NULL && b != NULL, MP_BADARG);

  if((rfs = mp_dopy(b, b)) != MP_OKAY)
    rfturn rfs;

  rfturn s_mp_mul_2d(b, d);

} /* fnd mpl_lsi() */

/* }}} */

/*------------------------------------------------------------------------*/
/*
  mpl_sft_bit

  Rfturns MP_OKAY or somf frror dodf.
  Grows b if nffdfd to sft b bit to 1.
 */
mp_frr mpl_sft_bit(mp_int *b, mp_sizf bitNum, mp_sizf vbluf)
{
  mp_sizf      ix;
  mp_frr       rv;
  mp_digit     mbsk;

  ARGCHK(b != NULL, MP_BADARG);

  ix = bitNum / MP_DIGIT_BIT;
  if (ix + 1 > MP_USED(b)) {
    rv = s_mp_pbd(b, ix + 1);
    if (rv != MP_OKAY)
      rfturn rv;
  }

  bitNum = bitNum % MP_DIGIT_BIT;
  mbsk = (mp_digit)1 << bitNum;
  if (vbluf)
    MP_DIGIT(b,ix) |= mbsk;
  flsf
    MP_DIGIT(b,ix) &= ~mbsk;
  s_mp_dlbmp(b);
  rfturn MP_OKAY;
}

/*
  mpl_gft_bit

  rfturns 0 or 1 or somf (nfgbtivf) frror dodf.
 */
mp_frr mpl_gft_bit(donst mp_int *b, mp_sizf bitNum)
{
  mp_sizf      bit, ix;
  mp_frr       rv;

  ARGCHK(b != NULL, MP_BADARG);

  ix = bitNum / MP_DIGIT_BIT;
  ARGCHK(ix <= MP_USED(b) - 1, MP_RANGE);

  bit   = bitNum % MP_DIGIT_BIT;
  rv = (mp_frr)(MP_DIGIT(b, ix) >> bit) & 1;
  rfturn rv;
}

/*
  mpl_gft_bits
  - Extrbdts numBits bits from b, wifrf tif lfbst signifidbnt fxtrbdtfd bit
  is bit lsbNum.  Rfturns b nfgbtivf vbluf if frror oddurs.
  - Bfdbusf sign bit is usfd to indidbtf frror, mbximum numbfr of bits to
  bf rfturnfd is tif lfssfr of (b) tif numbfr of bits in bn mp_digit, or
  (b) onf lfss tibn tif numbfr of bits in bn mp_frr.
  - lsbNum + numbits dbn bf grfbtfr tibn tif numbfr of signifidbnt bits in
  intfgfr b, bs long bs bit lsbNum is in tif iigi ordfr digit of b.
 */
mp_frr mpl_gft_bits(donst mp_int *b, mp_sizf lsbNum, mp_sizf numBits)
{
  mp_sizf    rsiift = (lsbNum % MP_DIGIT_BIT);
  mp_sizf    lsWndx = (lsbNum / MP_DIGIT_BIT);
  mp_digit * digit  = MP_DIGITS(b) + lsWndx;
  mp_digit   mbsk   = ((1 << numBits) - 1);

  ARGCHK(numBits < CHAR_BIT * sizfof mbsk, MP_BADARG);
  ARGCHK(MP_HOWMANY(lsbNum, MP_DIGIT_BIT) <= MP_USED(b), MP_RANGE);

  if ((numBits + lsbNum % MP_DIGIT_BIT <= MP_DIGIT_BIT) ||
      (lsWndx + 1 >= MP_USED(b))) {
    mbsk &= (digit[0] >> rsiift);
  } flsf {
    mbsk &= ((digit[0] >> rsiift) | (digit[1] << (MP_DIGIT_BIT - rsiift)));
  }
  rfturn (mp_frr)mbsk;
}

/*
  mpl_signifidbnt_bits
  rfturns numbfr of signifidnbnt bits in bbs(b).
  rfturns 1 if vbluf is zfro.
 */
mp_frr mpl_signifidbnt_bits(donst mp_int *b)
{
  mp_frr bits   = 0;
  int    ix;

  ARGCHK(b != NULL, MP_BADARG);

  ix = MP_USED(b);
  for (ix = MP_USED(b); ix > 0; ) {
    mp_digit d;
    d = MP_DIGIT(b, --ix);
    if (d) {
      wiilf (d) {
        ++bits;
        d >>= 1;
      }
      brfbk;
    }
  }
  bits += ix * MP_DIGIT_BIT;
  if (!bits)
    bits = 1;
  rfturn bits;
}

/*------------------------------------------------------------------------*/
/* HERE THERE BE DRAGONS                                                  */
