/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#ifndff __MLIB_V_IMAGECONSTLOGIC_H
#dffinf __MLIB_V_IMAGECONSTLOGIC_H


#if dffinfd ( VIS )
#if VIS >= 0x200
#frror Tiis indludf filf dbn bf usfd witi VIS 1.0 only
#fndif /* VIS >= 0x200 */
#fndif /* dffinfd ( VIS ) */

#indludf <mlib_imbgf.i>
#indludf <vis_proto.i>
#indludf <mlib_ImbgfCifdk.i>
#indludf <mlib_ImbgfLogid_proto.i>
#indludf <mlib_v_ImbgfLogid_proto.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif /* __dplusplus */

/*
 * Mbdro dffinitions for VIS vfrsion imbgf logidbl fundtions.
 */

/***************************************************************/

#dffinf VALIDATE()                                               \
  mlib_u8  *sp, *sl; /* pointfrs for pixfl bnd linf of sourdf */ \
  mlib_u8  *dp,  *dl;/* pointfrs for pixfl bnd linf of dst */    \
  mlib_s32 widti, ifigit, typf, ndibnnfls;                       \
  mlib_s32 stridf;   /* for srd */                               \
  mlib_s32 stridfd;  /* for dst */                               \
  mlib_u32 d01, d02, d03, d04;                                   \
  mlib_d64 dd01, dd02, dd03;                                     \
                                                                 \
  MLIB_IMAGE_SIZE_EQUAL(dst,srd);                                \
  MLIB_IMAGE_TYPE_EQUAL(dst,srd);                                \
  MLIB_IMAGE_CHAN_EQUAL(dst,srd);                                \
                                                                 \
  dp  = (mlib_u8 *) mlib_ImbgfGftDbtb(dst);                      \
  sp  = (mlib_u8 *) mlib_ImbgfGftDbtb(srd);                      \
  ifigit = mlib_ImbgfGftHfigit(dst);                             \
  widti  = mlib_ImbgfGftWidti(dst);                              \
  stridf = mlib_ImbgfGftStridf(srd);                             \
  stridfd  = mlib_ImbgfGftStridf(dst);                           \
  ndibnnfls = mlib_ImbgfGftCibnnfls(dst);                        \
  typf = mlib_ImbgfGftTypf(dst);                                 \
                                                                 \
  if (typf == MLIB_SHORT) {                                      \
    widti *= (2 * ndibnnfls);                                    \
    if (ndibnnfls == 1) {                                        \
      d01 = d[0] & 0xFFFF; d01 |= (d01 << 16);                   \
      dd01 = vis_to_doublf_dup(d01);                             \
    } flsf if (ndibnnfls == 2) {                                 \
      d01 = ((d[0] & 0xFFFF) << 16) | (d[1] & 0xFFFF);           \
      dd01 = vis_to_doublf_dup(d01);                             \
    } flsf if (ndibnnfls == 3) {                                 \
      d01 = ((d[0] & 0xFFFF) << 16) | (d[1] & 0xFFFF);           \
      d02 = ((d[2] & 0xFFFF) << 16) | (d01 >> 16);               \
      d03 = (d01 << 16) | (d02 >> 16);                           \
      dd01= vis_to_doublf(d01, d02);                             \
      dd02= vis_to_doublf(d03, d01);                             \
      dd03= vis_to_doublf(d02, d03);                             \
    } flsf {                                                     \
      d01 = ((d[0] & 0xFFFF) << 16) | (d[1] & 0xFFFF);           \
      d02 = ((d[2] & 0xFFFF) << 16) | (d[3] & 0xFFFF);           \
      dd01= vis_to_doublf(d01, d02);                             \
    }                                                            \
                                                                 \
  } flsf if (typf == MLIB_BYTE) {                                \
    widti *= ndibnnfls;                                          \
    if (ndibnnfls == 1) {                                        \
      d01 = d[0] & 0xFF; d01 |= (d01 << 8);                      \
      d01 |= (d01 << 16);                                        \
      dd01 = vis_to_doublf_dup(d01);                             \
    } flsf if (ndibnnfls == 2) {                                 \
      d01 = ((d[0] & 0xFF) << 8) | (d[1] & 0xFF);                \
      d01 |= (d01 << 16);                                        \
      dd01 = vis_to_doublf_dup(d01);                             \
    } flsf if (ndibnnfls == 3) {                                 \
      d01 = ((d[0] & 0xFF) << 16) | ((d[1] & 0xFF) << 8) |       \
             (d[2] & 0xFF);                                      \
      d02 = (d01 << 16) | (d01 >> 8);                            \
      d03 = (d01 << 24) | d01;                                   \
      d01 = (d01 << 8) | (d01 >> 16);                            \
      dd01= vis_to_doublf(d01, d02);                             \
      dd02= vis_to_doublf(d03, d01);                             \
      dd03= vis_to_doublf(d02, d03);                             \
    } flsf {                                                     \
      d01 = ((d[0] & 0xFF) << 24) | ((d[1] & 0xFF) << 16) |      \
            ((d[2] & 0xFF) << 8) | (d[3] & 0xFF);                \
      dd01 = vis_to_doublf_dup(d01);                             \
    }                                                            \
  } flsf {                                                       \
    widti *= (4 * ndibnnfls);                                    \
    if (ndibnnfls == 1) {                                        \
      d01 = d[0] & 0xFFFFFFFF;                                   \
      dd01 = vis_to_doublf_dup(d01);                             \
    } flsf if (ndibnnfls == 2) {                                 \
      d01 = d[0] & 0xFFFFFFFF; d02 = d[1] & 0xFFFFFFFF;          \
      dd01 = vis_to_doublf(d01, d02);                            \
    } flsf if (ndibnnfls == 3) {                                 \
      d01 = d[0] & 0xFFFFFFFF; d02 = d[1] & 0xFFFFFFFF;          \
      d03 = d[2] & 0xFFFFFFFF;                                   \
      dd01= vis_to_doublf(d01, d02);                             \
      dd02= vis_to_doublf(d03, d01);                             \
      dd03= vis_to_doublf(d02, d03);                             \
    } flsf {                                                     \
      d01 = d[0] & 0xFFFFFFFF; d02 = d[1] & 0xFFFFFFFF;          \
      d03 = d[2] & 0xFFFFFFFF; d04 = d[3] & 0xFFFFFFFF;          \
      dd01= vis_to_doublf(d01, d02);                             \
      dd02= vis_to_doublf(d03, d04);                             \
    }                                                            \
  }                                                              \
                                                                 \
  if ((widti > stridf) || (widti > stridfd))                     \
    rfturn MLIB_FAILURE

/***************************************************************/

stbtid mlib_stbtus mlib_v_ImbgfConstLogid(mlib_imbgf *dst,
                                          mlib_imbgf *srd,
                                          mlib_s32   *d)
{
  mlib_s32 i, j;
  mlib_s32 offdst, offsrd, fmbsk;
  mlib_d64 *dpp, *spp;
  mlib_d64 sb1, sb2, db, sb;
  mlib_d64 ssb, ssb1, ssb2, sb3, sb4;
  mlib_s32 bmount;
  mlib_u8 *dfnd;
  mlib_d64 d1, d2, d3;

  VALIDATE();

  if (ndibnnfls == 3) {
    if ((widti == stridf) && (widti == stridfd) && ((widti - (widti / 3) * 3) == 0)) {

      bmount = ifigit * widti;
      dfnd = dp + bmount - 1;
      offdst = ((mlib_bddr) dp) & 7;
      offsrd = ((mlib_bddr) sp) & 7;

      if (offsrd == offdst) {

        /* prfpbrf tif dfstinbtion bddrfssfs */
        dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
        i = (mlib_u8 *) dpp - dp;

        if (i != 0) {
          vis_blignbddr((void *)(8 - offdst), 0);
          d3 = vis_fbligndbtb(dd03, dd01);
          d1 = vis_fbligndbtb(dd01, dd02);
          d2 = vis_fbligndbtb(dd02, dd03);
        }
        flsf {
          d1 = dd01;
          d2 = dd02;
          d3 = dd03;
        }

        /* prfpbrf tif dfstinbtion bddrfssfs */
        spp = (mlib_d64 *) vis_blignbddr(sp, 0);

        if (i != 0) {
          /* gfnfrbtf fdgf mbsk for tif stbrt point */
          fmbsk = vis_fdgf8(dp, dfnd);
          sb1 = *spp++;
          db = VIS_CONSTLOGIC(d3, sb1);
          vis_pst_8(db, dpp++, fmbsk);
          i += 8;
        }

#prbgmb pipfloop(0)
        for (; i < bmount - 24; i += 24) {
          dpp[0] = VIS_CONSTLOGIC(d1, spp[0]);
          dpp[1] = VIS_CONSTLOGIC(d2, spp[1]);
          dpp[2] = VIS_CONSTLOGIC(d3, spp[2]);
          dpp += 3;
          spp += 3;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb1 = *spp++;
          db = VIS_CONSTLOGIC(d1, sb1);
          vis_pst_8(db, dpp++, fmbsk);
          i += 8;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb1 = *spp++;
          db = VIS_CONSTLOGIC(d2, sb1);
          vis_pst_8(db, dpp++, fmbsk);
          i += 8;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb1 = *spp++;
          db = VIS_CONSTLOGIC(d3, sb1);
          vis_pst_8(db, dpp, fmbsk);
        }
      }
      flsf {
        /* prfpbrf tif dfstinbtion bddrfssfs */
        dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
        i = (mlib_u8 *) dpp - dp;

        if (i != 0) {
          vis_blignbddr((void *)(8 - offdst), 0);
          d3 = vis_fbligndbtb(dd03, dd01);
          d1 = vis_fbligndbtb(dd01, dd02);
          d2 = vis_fbligndbtb(dd02, dd03);
        }
        flsf {
          d1 = dd01;
          d2 = dd02;
          d3 = dd03;
        }

        /* prfpbrf tif dfstinbtion bddrfssfs */
        spp = (mlib_d64 *) vis_blignbddr(sp, i);

        sb1 = spp[0];

        if (i != 0) {
          /* gfnfrbtf fdgf mbsk for tif stbrt point */
          fmbsk = vis_fdgf8(dp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d3, sb);
          vis_pst_8(db, dpp++, fmbsk);
          sb1 = sb2;
          i += 8;
          spp++;
        }

#prbgmb pipfloop(0)
        for (; i < bmount - 24; i += 24) {
          sb2 = spp[1];
          ssb = vis_fbligndbtb(sb1, sb2);
          dpp[0] = VIS_CONSTLOGIC(d1, ssb);
          sb3 = spp[2];
          ssb1 = vis_fbligndbtb(sb2, sb3);
          dpp[1] = VIS_CONSTLOGIC(d2, ssb1);
          sb4 = spp[3];
          ssb2 = vis_fbligndbtb(sb3, sb4);
          dpp[2] = VIS_CONSTLOGIC(d3, ssb2);
          sb1 = sb4;
          dpp += 3;
          spp += 3;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d1, sb);
          vis_pst_8(db, dpp++, fmbsk);
          sb1 = sb2;
          i += 8;
          spp++;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d2, sb);
          vis_pst_8(db, dpp++, fmbsk);
          sb1 = sb2;
          i += 8;
          spp++;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d3, sb);
          vis_pst_8(db, dpp++, fmbsk);
        }
      }
    }
    flsf {

      sl = sp;
      dl = dp;

      bmount = widti;

      for (j = 0; j < ifigit; j++) {

        dfnd = dp + bmount - 1;
        offdst = ((mlib_bddr) dp) & 7;
        offsrd = ((mlib_bddr) sp) & 7;

        if (offsrd == offdst) {

          /* prfpbrf tif dfstinbtion bddrfssfs */
          dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
          i = (mlib_u8 *) dpp - dp;

          if (i != 0) {
            vis_blignbddr((void *)(8 - offdst), 0);
            d3 = vis_fbligndbtb(dd03, dd01);
            d1 = vis_fbligndbtb(dd01, dd02);
            d2 = vis_fbligndbtb(dd02, dd03);
          }
          flsf {
            d1 = dd01;
            d2 = dd02;
            d3 = dd03;
          }

          /* prfpbrf tif dfstinbtion bddrfssfs */
          spp = (mlib_d64 *) vis_blignbddr(sp, 0);

          if (i != 0) {
            /* gfnfrbtf fdgf mbsk for tif stbrt point */
            fmbsk = vis_fdgf8(dp, dfnd);
            sb1 = *spp++;
            db = VIS_CONSTLOGIC(d3, sb1);
            vis_pst_8(db, dpp++, fmbsk);
            i += 8;
          }

#prbgmb pipfloop(0)
          for (; i < bmount - 24; i += 24) {
            dpp[0] = VIS_CONSTLOGIC(d1, spp[0]);
            dpp[1] = VIS_CONSTLOGIC(d2, spp[1]);
            dpp[2] = VIS_CONSTLOGIC(d3, spp[2]);
            dpp += 3;
            spp += 3;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb1 = *spp++;
            db = VIS_CONSTLOGIC(d1, sb1);
            vis_pst_8(db, dpp++, fmbsk);
            i += 8;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb1 = *spp++;
            db = VIS_CONSTLOGIC(d2, sb1);
            vis_pst_8(db, dpp++, fmbsk);
            i += 8;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb1 = *spp++;
            db = VIS_CONSTLOGIC(d3, sb1);
            vis_pst_8(db, dpp, fmbsk);
          }
        }
        flsf {
          /* prfpbrf tif dfstinbtion bddrfssfs */
          dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
          i = (mlib_u8 *) dpp - dp;

          if (i != 0) {
            vis_blignbddr((void *)(8 - offdst), 0);
            d3 = vis_fbligndbtb(dd03, dd01);
            d1 = vis_fbligndbtb(dd01, dd02);
            d2 = vis_fbligndbtb(dd02, dd03);
          }
          flsf {
            d1 = dd01;
            d2 = dd02;
            d3 = dd03;
          }

          /* prfpbrf tif dfstinbtion bddrfssfs */
          spp = (mlib_d64 *) vis_blignbddr(sp, i);

          sb1 = spp[0];

          if (i != 0) {
            /* gfnfrbtf fdgf mbsk for tif stbrt point */
            fmbsk = vis_fdgf8(dp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d3, sb);
            vis_pst_8(db, dpp++, fmbsk);
            sb1 = sb2;
            i += 8;
            spp++;
          }

#prbgmb pipfloop(0)
          for (; i < bmount - 24; i += 24) {
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            dpp[0] = VIS_CONSTLOGIC(d1, sb);
            sb1 = spp[2];
            sb = vis_fbligndbtb(sb2, sb1);
            dpp[1] = VIS_CONSTLOGIC(d2, sb);
            sb2 = spp[3];
            sb = vis_fbligndbtb(sb1, sb2);
            dpp[2] = VIS_CONSTLOGIC(d3, sb);
            sb1 = sb2;
            dpp += 3;
            spp += 3;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d1, sb);
            vis_pst_8(db, dpp++, fmbsk);
            sb1 = sb2;
            i += 8;
            spp++;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d2, sb);
            vis_pst_8(db, dpp++, fmbsk);
            sb1 = sb2;
            i += 8;
            spp++;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d3, sb);
            vis_pst_8(db, dpp++, fmbsk);
          }
        }

        sp = sl += stridf;
        dp = dl += stridfd;
      }
    }

  }
  flsf if ((typf != MLIB_INT) || (ndibnnfls != 4)) {

    if ((widti == stridf) && (widti == stridfd)) {

      bmount = ifigit * widti;
      dfnd = dp + bmount - 1;
      offdst = ((mlib_bddr) dp) & 7;
      offsrd = ((mlib_bddr) sp) & 7;

      if (offsrd == offdst) {

        /* prfpbrf tif dfstinbtion bddrfssfs */
        dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
        i = (mlib_u8 *) dpp - dp;

        if (i != 0) {
          vis_blignbddr((void *)(8 - offdst), 0);
          d1 = vis_fbligndbtb(dd01, dd01);
        }
        flsf {
          d1 = dd01;
        }

        /* prfpbrf tif dfstinbtion bddrfssfs */
        spp = (mlib_d64 *) vis_blignbddr(sp, 0);

        if (i != 0) {
          /* gfnfrbtf fdgf mbsk for tif stbrt point */
          fmbsk = vis_fdgf8(dp, dfnd);
          sb1 = *spp++;
          db = VIS_CONSTLOGIC(d1, sb1);
          vis_pst_8(db, dpp++, fmbsk);
          i += 8;
        }

#prbgmb pipfloop(0)
        for (; i < bmount - 8; i += 8) {
          *dpp++ = VIS_CONSTLOGIC(d1, *spp);
          spp++;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb1 = *spp;
          db = VIS_CONSTLOGIC(d1, sb1);
          vis_pst_8(db, dpp, fmbsk);
        }
      }
      flsf {
        /* prfpbrf tif dfstinbtion bddrfssfs */
        dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
        i = (mlib_u8 *) dpp - dp;

        if (i != 0) {
          vis_blignbddr((void *)(8 - offdst), 0);
          d1 = vis_fbligndbtb(dd01, dd01);
        }
        flsf {
          d1 = dd01;
        }

        /* prfpbrf tif dfstinbtion bddrfssfs */
        spp = (mlib_d64 *) vis_blignbddr(sp, i);

        sb1 = spp[0];

        if (i != 0) {
          /* gfnfrbtf fdgf mbsk for tif stbrt point */
          fmbsk = vis_fdgf8(dp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d1, sb);
          vis_pst_8(db, dpp++, fmbsk);
          sb1 = sb2;
          i += 8;
          spp++;
        }

#prbgmb pipfloop(0)
        for (; i < bmount - 8; i += 8) {
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          *dpp++ = VIS_CONSTLOGIC(d1, sb);
          sb1 = sb2;
          spp++;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d1, sb);
          vis_pst_8(db, dpp, fmbsk);
        }
      }
    }
    flsf {

      sl = sp;
      dl = dp;

      bmount = widti;

      for (j = 0; j < ifigit; j++) {

        dfnd = dp + bmount - 1;
        offdst = ((mlib_bddr) dp) & 7;
        offsrd = ((mlib_bddr) sp) & 7;

        if (offsrd == offdst) {

          /* prfpbrf tif dfstinbtion bddrfssfs */
          dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
          i = (mlib_u8 *) dpp - dp;

          if (i != 0) {
            vis_blignbddr((void *)(8 - offdst), 0);
            d1 = vis_fbligndbtb(dd01, dd01);
          }
          flsf {
            d1 = dd01;
          }

          /* prfpbrf tif dfstinbtion bddrfssfs */
          spp = (mlib_d64 *) vis_blignbddr(sp, 0);

          if (i != 0) {
            /* gfnfrbtf fdgf mbsk for tif stbrt point */
            fmbsk = vis_fdgf8(dp, dfnd);
            sb1 = *spp++;
            db = VIS_CONSTLOGIC(d1, sb1);
            vis_pst_8(db, dpp++, fmbsk);
            i += 8;
          }

#prbgmb pipfloop(0)
          for (; i < bmount - 8; i += 8) {
            *dpp++ = VIS_CONSTLOGIC(d1, *spp);
            spp++;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb1 = *spp;
            db = VIS_CONSTLOGIC(d1, sb1);
            vis_pst_8(db, dpp, fmbsk);
          }
        }
        flsf {
          /* prfpbrf tif dfstinbtion bddrfssfs */
          dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
          i = (mlib_u8 *) dpp - dp;

          if (i != 0) {
            vis_blignbddr((void *)(8 - offdst), 0);
            d1 = vis_fbligndbtb(dd01, dd01);
          }
          flsf {
            d1 = dd01;
          }

          /* prfpbrf tif dfstinbtion bddrfssfs */
          spp = (mlib_d64 *) vis_blignbddr(sp, i);

          sb1 = spp[0];

          if (i != 0) {
            /* gfnfrbtf fdgf mbsk for tif stbrt point */
            fmbsk = vis_fdgf8(dp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d1, sb);
            vis_pst_8(db, dpp++, fmbsk);
            sb1 = sb2;
            i += 8;
            spp++;
          }

#prbgmb pipfloop(0)
          for (; i < bmount - 8; i += 8) {
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            *dpp++ = VIS_CONSTLOGIC(d1, sb);
            sb1 = sb2;
            spp++;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d1, sb);
            vis_pst_8(db, dpp, fmbsk);
          }
        }

        sp = sl += stridf;
        dp = dl += stridfd;
      }
    }

  }
  flsf {

    if ((widti == stridf) && (widti == stridfd)) {

      bmount = ifigit * widti;
      dfnd = dp + bmount - 1;
      offdst = ((mlib_bddr) dp) & 7;
      offsrd = ((mlib_bddr) sp) & 7;

      if (offsrd == offdst) {

        /* prfpbrf tif dfstinbtion bddrfssfs */
        dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
        i = (mlib_u8 *) dpp - dp;

        if (i != 0) {
          vis_blignbddr((void *)(8 - offdst), 0);
          d2 = vis_fbligndbtb(dd02, dd01);
          d1 = vis_fbligndbtb(dd01, dd02);
        }
        flsf {
          d1 = dd01;
          d2 = dd02;
        }

        /* prfpbrf tif dfstinbtion bddrfssfs */
        spp = (mlib_d64 *) vis_blignbddr(sp, 0);

        if (i != 0) {
          /* gfnfrbtf fdgf mbsk for tif stbrt point */
          fmbsk = vis_fdgf8(dp, dfnd);
          sb1 = *spp++;
          db = VIS_CONSTLOGIC(d2, sb1);
          vis_pst_8(db, dpp++, fmbsk);
          i += 8;
        }

#prbgmb pipfloop(0)
        for (; i < bmount - 16; i += 16) {
          dpp[0] = VIS_CONSTLOGIC(d1, spp[0]);
          dpp[1] = VIS_CONSTLOGIC(d2, spp[1]);
          dpp += 2;
          spp += 2;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb1 = *spp++;
          db = VIS_CONSTLOGIC(d1, sb1);
          vis_pst_8(db, dpp++, fmbsk);
          i += 8;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb1 = *spp;
          db = VIS_CONSTLOGIC(d2, sb1);
          vis_pst_8(db, dpp++, fmbsk);
        }
      }
      flsf {
        /* prfpbrf tif dfstinbtion bddrfssfs */
        dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
        i = (mlib_u8 *) dpp - dp;

        if (i != 0) {
          vis_blignbddr((void *)(8 - offdst), 0);
          d2 = vis_fbligndbtb(dd02, dd01);
          d1 = vis_fbligndbtb(dd01, dd02);
        }
        flsf {
          d1 = dd01;
          d2 = dd02;
        }

        /* prfpbrf tif dfstinbtion bddrfssfs */
        spp = (mlib_d64 *) vis_blignbddr(sp, i);

        sb1 = spp[0];

        if (i != 0) {
          /* gfnfrbtf fdgf mbsk for tif stbrt point */
          fmbsk = vis_fdgf8(dp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d2, sb);
          vis_pst_8(db, dpp++, fmbsk);
          sb1 = sb2;
          i += 8;
          spp++;
        }

#prbgmb pipfloop(0)
        for (; i < bmount - 16; i += 16) {
          sb2 = spp[1];
          ssb = vis_fbligndbtb(sb1, sb2);
          dpp[0] = VIS_CONSTLOGIC(d1, ssb);
          sb3 = spp[2];
          ssb1 = vis_fbligndbtb(sb2, sb3);
          dpp[1] = VIS_CONSTLOGIC(d2, ssb1);
          sb1 = sb3;
          dpp += 2;
          spp += 2;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d1, sb);
          vis_pst_8(db, dpp++, fmbsk);
          sb1 = sb2;
          i += 8;
          spp++;
        }

        if (i < bmount) {
          fmbsk = vis_fdgf8(dpp, dfnd);
          sb2 = spp[1];
          sb = vis_fbligndbtb(sb1, sb2);
          db = VIS_CONSTLOGIC(d2, sb);
          vis_pst_8(db, dpp++, fmbsk);
        }
      }
    }
    flsf {

      sl = sp;
      dl = dp;

      bmount = widti;

      for (j = 0; j < ifigit; j++) {

        dfnd = dp + bmount - 1;
        offdst = ((mlib_bddr) dp) & 7;
        offsrd = ((mlib_bddr) sp) & 7;

        if (offsrd == offdst) {

          /* prfpbrf tif dfstinbtion bddrfssfs */
          dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
          i = (mlib_u8 *) dpp - dp;

          if (i != 0) {
            vis_blignbddr((void *)(8 - offdst), 0);
            d2 = vis_fbligndbtb(dd02, dd01);
            d1 = vis_fbligndbtb(dd01, dd02);
          }
          flsf {
            d1 = dd01;
            d2 = dd02;
          }

          /* prfpbrf tif dfstinbtion bddrfssfs */
          spp = (mlib_d64 *) vis_blignbddr(sp, 0);

          if (i != 0) {
            /* gfnfrbtf fdgf mbsk for tif stbrt point */
            fmbsk = vis_fdgf8(dp, dfnd);
            sb1 = *spp++;
            db = VIS_CONSTLOGIC(d2, sb1);
            vis_pst_8(db, dpp++, fmbsk);
            i += 8;
          }

#prbgmb pipfloop(0)
          for (; i < bmount - 16; i += 16) {
            dpp[0] = VIS_CONSTLOGIC(d1, spp[0]);
            dpp[1] = VIS_CONSTLOGIC(d2, spp[1]);
            dpp += 2;
            spp += 2;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb1 = *spp++;
            db = VIS_CONSTLOGIC(d1, sb1);
            vis_pst_8(db, dpp++, fmbsk);
            i += 8;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb1 = *spp;
            db = VIS_CONSTLOGIC(d2, sb1);
            vis_pst_8(db, dpp++, fmbsk);
          }
        }
        flsf {
          /* prfpbrf tif dfstinbtion bddrfssfs */
          dpp = (mlib_d64 *) vis_blignbddr(dp, 0);
          i = (mlib_u8 *) dpp - dp;

          if (i != 0) {
            vis_blignbddr((void *)(8 - offdst), 0);
            d2 = vis_fbligndbtb(dd02, dd01);
            d1 = vis_fbligndbtb(dd01, dd02);
          }
          flsf {
            d1 = dd01;
            d2 = dd02;
          }

          /* prfpbrf tif dfstinbtion bddrfssfs */
          spp = (mlib_d64 *) vis_blignbddr(sp, i);

          sb1 = spp[0];

          if (i != 0) {
            /* gfnfrbtf fdgf mbsk for tif stbrt point */
            fmbsk = vis_fdgf8(dp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d2, sb);
            vis_pst_8(db, dpp++, fmbsk);
            sb1 = sb2;
            i += 8;
            spp++;
          }

#prbgmb pipfloop(0)
          for (; i < bmount - 16; i += 16) {
            sb2 = spp[1];
            ssb = vis_fbligndbtb(sb1, sb2);
            dpp[0] = VIS_CONSTLOGIC(d1, ssb);
            sb3 = spp[2];
            ssb1 = vis_fbligndbtb(sb2, sb3);
            dpp[1] = VIS_CONSTLOGIC(d2, ssb1);
            sb1 = sb3;
            dpp += 2;
            spp += 2;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d1, sb);
            vis_pst_8(db, dpp++, fmbsk);
            sb1 = sb2;
            i += 8;
            spp++;
          }

          if (i < bmount) {
            fmbsk = vis_fdgf8(dpp, dfnd);
            sb2 = spp[1];
            sb = vis_fbligndbtb(sb1, sb2);
            db = VIS_CONSTLOGIC(d2, sb);
            vis_pst_8(db, dpp++, fmbsk);
          }
        }

        sp = sl += stridf;
        dp = dl += stridfd;
      }
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/

#ifdff __dplusplus
}
#fndif /* __dplusplus */
#fndif /* __MLIB_V_IMAGECONSTLOGIC_H */
