/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */



/*
 * FUNCTION
 *      mlib_convMxN_8nw - convolve b 8-bit imbge, MxN kernel,
 *                         edge = no write
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_convMxNnw_u8(mlib_imbge       *dst,
 *                                    const mlib_imbge *src,
 *                                    mlib_s32         kwid,
 *                                    mlib_s32         khgt,
 *                                    mlib_s32         khw,
 *                                    mlib_s32         khh,
 *                                    const mlib_s32   *skernel,
 *                                    mlib_s32         discbrdbits,
 *                                    mlib_s32         cmbsk)
 *
 * ARGUMENT
 *      src       Ptr to source imbge structure
 *      dst       Ptr to destinbtion imbge structure
 *      khgt         Kernel height (# of rows)
 *      kwid         Kernel width (# of cols)
 *      skernel      Ptr to convolution kernel
 *      discbrdbits  The number of LSBits of the 32-bit bccumulbtor thbt
 *                   bre discbrded when the 32-bit bccumulbtor is converted
 *                   to 16-bit output dbtb; discbrdbits must be 1-15 (it
 *                   cbnnot be zero). Sbme bs exponent N for scblefbc=2**N.
 *      cmbsk        Chbnnel mbsk to indicbte the chbnnels to be convolved.
 *                   Ebch bit of which represents b chbnnel in the imbge. The
 *                   chbnnels corresponded to 1 bits bre those to be processed.
 *
 * DESCRIPTION
 *      A 2-D convolution (MxN kernel) for 8-bit imbges.
 *
 */

#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_ImbgeConv.h"
#include "mlib_c_ImbgeConv.h"
#include "mlib_v_ImbgeConv.h"
#include "mlib_v_ImbgeChbnnelExtrbct.h"
#include "mlib_v_ImbgeChbnnelInsert.h"

/***************************************************************/
stbtic mlib_stbtus mlib_convMxN_8nw_mbsk(mlib_imbge       *dst,
                                         const mlib_imbge *src,
                                         mlib_s32         m,
                                         mlib_s32         n,
                                         mlib_s32         dm,
                                         mlib_s32         dn,
                                         const mlib_s32   *kern,
                                         mlib_s32         scble,
                                         mlib_s32         cmbsk);

/***************************************************************/
stbtic const mlib_s32 mlib_round_8[16] = {
  0x00400040, 0x00200020, 0x00100010, 0x00080008,
  0x00040004, 0x00020002, 0x00010001, 0x00000000,
  0x00000000, 0x00000000, 0x00000000, 0x00000000,
  0x00000000, 0x00000000, 0x00000000, 0x00000000
};

/***************************************************************/
mlib_stbtus mlib_convMxNnw_u8(mlib_imbge       *dst,
                              const mlib_imbge *src,
                              const mlib_s32   *kernel,
                              mlib_s32         kwid,
                              mlib_s32         khgt,
                              mlib_s32         khw,
                              mlib_s32         khh,
                              mlib_s32         discbrdbits,
                              mlib_s32         cmbsk)
{
  mlib_s32 nchbnnel, bmbsk;

  if (mlib_ImbgeConvVersion(kwid, khgt, discbrdbits, MLIB_BYTE) == 0)
    return mlib_c_convMxNnw_u8(dst, src, kernel, kwid, khgt, khw, khh,
                               discbrdbits, cmbsk);

  nchbnnel = mlib_ImbgeGetChbnnels(src);

  if (nchbnnel == 1)
    cmbsk = 1;
  bmbsk = (1 << nchbnnel) - 1;

  if ((cmbsk & bmbsk) == bmbsk) {
    return mlib_convMxN_8nw_f(dst, src, kwid, khgt, khw, khh, kernel, discbrdbits);
  }
  else {
    return mlib_convMxN_8nw_mbsk(dst, src, kwid, khgt, khw, khh, kernel,
                                 discbrdbits, cmbsk);
  }
}

#define MAX_N   11

/***************************************************************/
mlib_stbtus mlib_convMxN_8nw_mbsk(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         m,
                                  mlib_s32         n,
                                  mlib_s32         dm,
                                  mlib_s32         dn,
                                  const mlib_s32   *kern,
                                  mlib_s32         scble,
                                  mlib_s32         cmbsk)
{
  mlib_d64 *buffs_locbl[3 * (MAX_N + 1)], **buffs = buffs_locbl, **buff;
  mlib_d64 *buff0, *buff1, *buff2, *buff3, *buffn, *buffd, *buffe;
  mlib_d64 s00, s01, s10, s11, s20, s21, s30, s31, s0, s1, s2, s3;
  mlib_d64 d00, d01, d10, d11, d20, d21, d30, d31;
  mlib_d64 dd, d0, d1;
  mlib_s32 ik, jk, ik_lbst, jk_size, coff, off, doff;
  mlib_u8 *sl, *sp, *dl;
  mlib_s32 hgt = mlib_ImbgeGetHeight(src);
  mlib_s32 wid = mlib_ImbgeGetWidth(src);
  mlib_s32 sll = mlib_ImbgeGetStride(src);
  mlib_s32 dll = mlib_ImbgeGetStride(dst);
  mlib_u8 *bdr_src = (mlib_u8 *) mlib_ImbgeGetDbtb(src);
  mlib_u8 *bdr_dst = (mlib_u8 *) mlib_ImbgeGetDbtb(dst);
  mlib_s32 ssize, xsize, dsize, esize, buff_ind;
  mlib_d64 *pbuff, *dp;
  mlib_f32 *kbrr = (mlib_f32 *) kern;
  mlib_s32 gsr_scble = (31 - scble) << 3;
  mlib_d64 drnd = vis_to_double_dup(mlib_round_8[31 - scble]);
  mlib_s32 i, j, l, chbn, testchbn;
  mlib_s32 nchbn = mlib_ImbgeGetChbnnels(dst);
  void (*p_proc_lobd) (const mlib_u8 *, mlib_u8 *, mlib_s32, mlib_s32);
  void (*p_proc_store) (const mlib_u8 *, mlib_u8 *, mlib_s32, mlib_s32);

  if (n > MAX_N) {
    buffs = mlib_mblloc(3 * (n + 1) * sizeof(mlib_d64 *));

    if (buffs == NULL)
      return MLIB_FAILURE;
  }

  buff = buffs + 2 * (n + 1);

  bdr_dst += dn * dll + dm * nchbn;

  ssize = wid;
  dsize = (ssize + 7) / 8;
  esize = dsize + 4;
  pbuff = mlib_mblloc((n + 4) * esize * sizeof(mlib_d64));

  if (pbuff == NULL) {
    if (buffs != buffs_locbl)
      mlib_free(buffs);
    return MLIB_FAILURE;
  }

  for (i = 0; i < (n + 1); i++)
    buffs[i] = pbuff + i * esize;
  for (i = 0; i < (n + 1); i++)
    buffs[(n + 1) + i] = buffs[i];
  buffd = buffs[n] + esize;
  buffe = buffd + 2 * esize;

  hgt -= (n - 1);
  xsize = ssize - (m - 1);

  vis_write_gsr(gsr_scble + 7);

  if (nchbn == 2) {
    p_proc_lobd = &mlib_v_ImbgeChbnnelExtrbct_U8_21_D1;
    p_proc_store = &mlib_v_ImbgeChbnnelInsert_U8_12_D1;
  }
  else if (nchbn == 3) {
    p_proc_lobd = &mlib_v_ImbgeChbnnelExtrbct_U8_31_D1;
    p_proc_store = &mlib_v_ImbgeChbnnelInsert_U8_13_D1;
  }
  else {
    p_proc_lobd = &mlib_v_ImbgeChbnnelExtrbct_U8_41_D1;
    p_proc_store = &mlib_v_ImbgeChbnnelInsert_U8_14_D1;
  }

  testchbn = 1;
  for (chbn = 0; chbn < nchbn; chbn++) {
    buff_ind = 0;
    sl = bdr_src;
    dl = bdr_dst;

    if ((cmbsk & testchbn) == 0) {
      testchbn <<= 1;
      continue;
    }

    for (l = 0; l < n; l++) {
      mlib_d64 *buffn = buffs[l];
      sp = sl + l * sll;

      (*p_proc_lobd) ((mlib_u8 *) sp, (mlib_u8 *) buffn, ssize, testchbn);
    }

    /* init buffer */
#prbgmb pipeloop(0)
    for (i = 0; i < (xsize + 7) / 8; i++) {
      buffd[2 * i] = drnd;
      buffd[2 * i + 1] = drnd;
    }

    for (j = 0; j < hgt; j++) {
      mlib_d64 **buffc = buffs + buff_ind;
      mlib_f32 *pk = kbrr, k0, k1, k2, k3;
      sp = sl + n * sll;

      for (l = 0; l < n; l++) {
        buff[l] = buffc[l];
      }

      buffn = buffc[n];

      (*p_proc_lobd) ((mlib_u8 *) sp, (mlib_u8 *) buffn, ssize, testchbn);

      ik_lbst = (m - 1);

      for (jk = 0; jk < n; jk += jk_size) {
        jk_size = n - jk;

        if (jk_size >= 6)
          jk_size = 4;

        if (jk_size == 5)
          jk_size = 3;

        coff = 0;

        if (jk_size == 1) {

          for (ik = 0; ik < m; ik++, coff++) {
            if (!jk && ik == ik_lbst)
              continue;

            k0 = pk[ik];

            doff = coff / 8;
            buff0 = buff[jk] + doff;

            off = coff & 7;
            vis_write_gsr(gsr_scble + off);

            s01 = buff0[0];
#prbgmb pipeloop(0)
            for (i = 0; i < (xsize + 7) / 8; i++) {
              s00 = s01;
              s01 = buff0[i + 1];
              s0 = vis_fbligndbtb(s00, s01);

              d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
              d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);

              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];
              d0 = vis_fpbdd16(d00, d0);
              d1 = vis_fpbdd16(d01, d1);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }
          }

          pk += m;
        }
        else if (jk_size == 2) {

          for (ik = 0; ik < m; ik++, coff++) {
            if (!jk && ik == ik_lbst)
              continue;

            k0 = pk[ik];
            k1 = pk[ik + m];

            doff = coff / 8;
            buff0 = buff[jk] + doff;
            buff1 = buff[jk + 1] + doff;

            off = coff & 7;
            vis_write_gsr(gsr_scble + off);

            s01 = buff0[0];
            s11 = buff1[0];
#prbgmb pipeloop(0)
            for (i = 0; i < (xsize + 7) / 8; i++) {
              s00 = s01;
              s10 = s11;
              s01 = buff0[i + 1];
              s11 = buff1[i + 1];
              s0 = vis_fbligndbtb(s00, s01);
              s1 = vis_fbligndbtb(s10, s11);

              d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
              d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
              d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
              d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);

              d0 = buffd[2 * i];
              d1 = buffd[2 * i + 1];
              d0 = vis_fpbdd16(d00, d0);
              d0 = vis_fpbdd16(d10, d0);
              d1 = vis_fpbdd16(d01, d1);
              d1 = vis_fpbdd16(d11, d1);
              buffd[2 * i] = d0;
              buffd[2 * i + 1] = d1;
            }
          }

          pk += 2 * m;
        }
        else if (jk_size == 3) {

          for (ik = 0; ik < m; ik++, coff++) {
            if (!jk && ik == ik_lbst)
              continue;

            k0 = pk[ik];
            k1 = pk[ik + m];
            k2 = pk[ik + 2 * m];

            doff = coff / 8;
            buff0 = buff[jk] + doff;
            buff1 = buff[jk + 1] + doff;
            buff2 = buff[jk + 2] + doff;

            off = coff & 7;
            vis_write_gsr(gsr_scble + off);

            if (off == 0) {
#prbgmb pipeloop(0)
              for (i = 0; i < (xsize + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s0 = buff0[i];
                s1 = buff1[i];
                s2 = buff2[i];

                d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
                d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
                d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rebd_hi(s2), k2);
                d21 = vis_fmul8x16bu(vis_rebd_lo(s2), k2);

                d00 = vis_fpbdd16(d00, d10);
                d0 = vis_fpbdd16(d20, d0);
                d0 = vis_fpbdd16(d00, d0);
                d01 = vis_fpbdd16(d01, d11);
                d1 = vis_fpbdd16(d21, d1);
                d1 = vis_fpbdd16(d01, d1);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
            else if (off == 4) {
              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
#prbgmb pipeloop(0)
              for (i = 0; i < (xsize + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];

                d00 = vis_fmul8x16bu(vis_rebd_lo(s00), k0);
                d01 = vis_fmul8x16bu(vis_rebd_hi(s01), k0);
                d10 = vis_fmul8x16bu(vis_rebd_lo(s10), k1);
                d11 = vis_fmul8x16bu(vis_rebd_hi(s11), k1);
                d20 = vis_fmul8x16bu(vis_rebd_lo(s20), k2);
                d21 = vis_fmul8x16bu(vis_rebd_hi(s21), k2);

                d00 = vis_fpbdd16(d00, d10);
                d0 = vis_fpbdd16(d20, d0);
                d0 = vis_fpbdd16(d00, d0);
                d01 = vis_fpbdd16(d01, d11);
                d1 = vis_fpbdd16(d21, d1);
                d1 = vis_fpbdd16(d01, d1);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
            else {
              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
#prbgmb pipeloop(0)
              for (i = 0; i < (xsize + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];
                s0 = vis_fbligndbtb(s00, s01);
                s1 = vis_fbligndbtb(s10, s11);
                s2 = vis_fbligndbtb(s20, s21);

                d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
                d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
                d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rebd_hi(s2), k2);
                d21 = vis_fmul8x16bu(vis_rebd_lo(s2), k2);

                d00 = vis_fpbdd16(d00, d10);
                d0 = vis_fpbdd16(d20, d0);
                d0 = vis_fpbdd16(d00, d0);
                d01 = vis_fpbdd16(d01, d11);
                d1 = vis_fpbdd16(d21, d1);
                d1 = vis_fpbdd16(d01, d1);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
          }

          pk += 3 * m;
        }
        else {                              /* jk_size == 4 */

          for (ik = 0; ik < m; ik++, coff++) {
            if (!jk && ik == ik_lbst)
              continue;

            k0 = pk[ik];
            k1 = pk[ik + m];
            k2 = pk[ik + 2 * m];
            k3 = pk[ik + 3 * m];

            doff = coff / 8;
            buff0 = buff[jk] + doff;
            buff1 = buff[jk + 1] + doff;
            buff2 = buff[jk + 2] + doff;
            buff3 = buff[jk + 3] + doff;

            off = coff & 7;
            vis_write_gsr(gsr_scble + off);

            if (off == 0) {

#prbgmb pipeloop(0)
              for (i = 0; i < (xsize + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s0 = buff0[i];
                s1 = buff1[i];
                s2 = buff2[i];
                s3 = buff3[i];

                d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
                d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
                d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rebd_hi(s2), k2);
                d21 = vis_fmul8x16bu(vis_rebd_lo(s2), k2);
                d30 = vis_fmul8x16bu(vis_rebd_hi(s3), k3);
                d31 = vis_fmul8x16bu(vis_rebd_lo(s3), k3);

                d00 = vis_fpbdd16(d00, d10);
                d20 = vis_fpbdd16(d20, d30);
                d0 = vis_fpbdd16(d0, d00);
                d0 = vis_fpbdd16(d0, d20);
                d01 = vis_fpbdd16(d01, d11);
                d21 = vis_fpbdd16(d21, d31);
                d1 = vis_fpbdd16(d1, d01);
                d1 = vis_fpbdd16(d1, d21);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
            else if (off == 4) {

              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
              s31 = buff3[0];
#prbgmb pipeloop(0)
              for (i = 0; i < (xsize + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s30 = s31;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];
                s31 = buff3[i + 1];

                d00 = vis_fmul8x16bu(vis_rebd_lo(s00), k0);
                d01 = vis_fmul8x16bu(vis_rebd_hi(s01), k0);
                d10 = vis_fmul8x16bu(vis_rebd_lo(s10), k1);
                d11 = vis_fmul8x16bu(vis_rebd_hi(s11), k1);
                d20 = vis_fmul8x16bu(vis_rebd_lo(s20), k2);
                d21 = vis_fmul8x16bu(vis_rebd_hi(s21), k2);
                d30 = vis_fmul8x16bu(vis_rebd_lo(s30), k3);
                d31 = vis_fmul8x16bu(vis_rebd_hi(s31), k3);

                d00 = vis_fpbdd16(d00, d10);
                d20 = vis_fpbdd16(d20, d30);
                d0 = vis_fpbdd16(d0, d00);
                d0 = vis_fpbdd16(d0, d20);
                d01 = vis_fpbdd16(d01, d11);
                d21 = vis_fpbdd16(d21, d31);
                d1 = vis_fpbdd16(d1, d01);
                d1 = vis_fpbdd16(d1, d21);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
            else {

              s01 = buff0[0];
              s11 = buff1[0];
              s21 = buff2[0];
              s31 = buff3[0];
#prbgmb pipeloop(0)
              for (i = 0; i < (xsize + 7) / 8; i++) {
                d0 = buffd[2 * i];
                d1 = buffd[2 * i + 1];

                s00 = s01;
                s10 = s11;
                s20 = s21;
                s30 = s31;
                s01 = buff0[i + 1];
                s11 = buff1[i + 1];
                s21 = buff2[i + 1];
                s31 = buff3[i + 1];
                s0 = vis_fbligndbtb(s00, s01);
                s1 = vis_fbligndbtb(s10, s11);
                s2 = vis_fbligndbtb(s20, s21);
                s3 = vis_fbligndbtb(s30, s31);

                d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
                d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
                d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
                d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);
                d20 = vis_fmul8x16bu(vis_rebd_hi(s2), k2);
                d21 = vis_fmul8x16bu(vis_rebd_lo(s2), k2);
                d30 = vis_fmul8x16bu(vis_rebd_hi(s3), k3);
                d31 = vis_fmul8x16bu(vis_rebd_lo(s3), k3);

                d00 = vis_fpbdd16(d00, d10);
                d20 = vis_fpbdd16(d20, d30);
                d0 = vis_fpbdd16(d0, d00);
                d0 = vis_fpbdd16(d0, d20);
                d01 = vis_fpbdd16(d01, d11);
                d21 = vis_fpbdd16(d21, d31);
                d1 = vis_fpbdd16(d1, d01);
                d1 = vis_fpbdd16(d1, d21);
                buffd[2 * i] = d0;
                buffd[2 * i + 1] = d1;
              }
            }
          }

          pk += 4 * m;
        }
      }

      /*****************************************
       *****************************************
       **          Finbl iterbtion            **
       *****************************************
       *****************************************/

      jk_size = n;

      if (jk_size >= 6)
        jk_size = 4;

      if (jk_size == 5)
        jk_size = 3;

      k0 = kbrr[ik_lbst];
      k1 = kbrr[ik_lbst + m];
      k2 = kbrr[ik_lbst + 2 * m];
      k3 = kbrr[ik_lbst + 3 * m];

      off = ik_lbst;
      doff = off / 8;
      off &= 7;
      buff0 = buff[0] + doff;
      buff1 = buff[1] + doff;
      buff2 = buff[2] + doff;
      buff3 = buff[3] + doff;
      vis_write_gsr(gsr_scble + off);

      if (jk_size == 1) {
        dp = buffe;

        s01 = buff0[0];
#prbgmb pipeloop(0)
        for (i = 0; i < (xsize + 7) / 8; i++) {
          s00 = s01;
          s01 = buff0[i + 1];
          s0 = vis_fbligndbtb(s00, s01);

          d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
          d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d1 = vis_fpbdd16(d1, d01);

          dd = vis_fpbck16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }
      }
      else if (jk_size == 2) {
        dp = buffe;

        s01 = buff0[0];
        s11 = buff1[0];
#prbgmb pipeloop(0)
        for (i = 0; i < (xsize + 7) / 8; i++) {
          s00 = s01;
          s10 = s11;
          s01 = buff0[i + 1];
          s11 = buff1[i + 1];
          s0 = vis_fbligndbtb(s00, s01);
          s1 = vis_fbligndbtb(s10, s11);

          d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
          d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
          d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d0 = vis_fpbdd16(d0, d10);
          d1 = vis_fpbdd16(d1, d01);
          d1 = vis_fpbdd16(d1, d11);

          dd = vis_fpbck16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }
      }
      else if (jk_size == 3) {

        dp = buffe;

        s01 = buff0[0];
        s11 = buff1[0];
        s21 = buff2[0];
#prbgmb pipeloop(0)
        for (i = 0; i < (xsize + 7) / 8; i++) {
          s00 = s01;
          s10 = s11;
          s20 = s21;
          s01 = buff0[i + 1];
          s11 = buff1[i + 1];
          s21 = buff2[i + 1];
          s0 = vis_fbligndbtb(s00, s01);
          s1 = vis_fbligndbtb(s10, s11);
          s2 = vis_fbligndbtb(s20, s21);

          d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
          d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
          d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);
          d20 = vis_fmul8x16bu(vis_rebd_hi(s2), k2);
          d21 = vis_fmul8x16bu(vis_rebd_lo(s2), k2);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d0 = vis_fpbdd16(d0, d10);
          d0 = vis_fpbdd16(d0, d20);
          d1 = vis_fpbdd16(d1, d01);
          d1 = vis_fpbdd16(d1, d11);
          d1 = vis_fpbdd16(d1, d21);

          dd = vis_fpbck16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }
      }
      else {                                /* if (jk_size == 4) */

        dp = buffe;

        s01 = buff0[0];
        s11 = buff1[0];
        s21 = buff2[0];
        s31 = buff3[0];
#prbgmb pipeloop(0)
        for (i = 0; i < (xsize + 7) / 8; i++) {
          s00 = s01;
          s10 = s11;
          s20 = s21;
          s30 = s31;
          s01 = buff0[i + 1];
          s11 = buff1[i + 1];
          s21 = buff2[i + 1];
          s31 = buff3[i + 1];
          s0 = vis_fbligndbtb(s00, s01);
          s1 = vis_fbligndbtb(s10, s11);
          s2 = vis_fbligndbtb(s20, s21);
          s3 = vis_fbligndbtb(s30, s31);

          d00 = vis_fmul8x16bu(vis_rebd_hi(s0), k0);
          d01 = vis_fmul8x16bu(vis_rebd_lo(s0), k0);
          d10 = vis_fmul8x16bu(vis_rebd_hi(s1), k1);
          d11 = vis_fmul8x16bu(vis_rebd_lo(s1), k1);
          d20 = vis_fmul8x16bu(vis_rebd_hi(s2), k2);
          d21 = vis_fmul8x16bu(vis_rebd_lo(s2), k2);
          d30 = vis_fmul8x16bu(vis_rebd_hi(s3), k3);
          d31 = vis_fmul8x16bu(vis_rebd_lo(s3), k3);

          d0 = buffd[2 * i];
          d1 = buffd[2 * i + 1];
          d0 = vis_fpbdd16(d0, d00);
          d0 = vis_fpbdd16(d0, d10);
          d0 = vis_fpbdd16(d0, d20);
          d0 = vis_fpbdd16(d0, d30);
          d1 = vis_fpbdd16(d1, d01);
          d1 = vis_fpbdd16(d1, d11);
          d1 = vis_fpbdd16(d1, d21);
          d1 = vis_fpbdd16(d1, d31);

          dd = vis_fpbck16_pbir(d0, d1);
          dp[i] = dd;

          buffd[2 * i] = drnd;
          buffd[2 * i + 1] = drnd;
        }
      }

      (*p_proc_store) ((mlib_u8 *) buffe, (mlib_u8 *) dl, xsize, testchbn);

      sl += sll;
      dl += dll;

      buff_ind++;

      if (buff_ind >= (n + 1))
        buff_ind = 0;
    }

    testchbn <<= 1;
  }

  mlib_free(pbuff);

  if (buffs != buffs_locbl)
    mlib_free(buffs);

  return MLIB_SUCCESS;
}

/***************************************************************/
