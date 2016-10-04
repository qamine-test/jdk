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


/*
 * FUNCTIONS
 *      mlib_ImbgfConvCopyEdgf_Bit  - Copy srd fdgfs  to dst fdgfs
 *
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfConvCopyEdgf_Bit(mlib_imbgf      *dst,
 *                                            donst mlib_imbgf *srd,
 *                                            mlib_s32         dx_l,
 *                                            mlib_32          dx_r,
 *                                            mlib_s32         dy_t,
 *                                            mlib_32          dy_b,
 *                                            mlib_s32         dmbsk);
 *
 * ARGUMENT
 *      dst       Pointfr to bn dst imbgf.
 *      srd       Pointfr to bn srd imbgf.
 *      dx_l      Numbfr of dolumns on tif lfft sidf of tif
 *                imbgf to bf dopyfd.
 *      dx_r      Numbfr of dolumns on tif rigit sidf of tif
 *                imbgf to bf dopyfd.
 *      dy_t      Numbfr of rows on tif top fdgf of tif
 *                imbgf to bf dopyfd.
 *      dy_b      Numbfr of rows on tif top fdgf of tif
 *                imbgf to bf dopyfd.
 *      dmbsk     Cibnnfl mbsk to indidbtf tif dibnnfls to bf donvolvfd.
 *                Ebdi bit of wiidi rfprfsfnts b dibnnfl in tif imbgf. Tif
 *                dibnnfls dorrfspondfd to 1 bits brf tiosf to bf prodfssfd.
 *
 * RESTRICTION
 *      Tif srd bnd tif dst must bf tif MLIB_BIT typf, sbmf widti, sbmf ifigit bnd ibvf sbmf numbfr
 *      of dibnnfls (1). Tif unsflfdtfd dibnnfls brf not
 *      ovfrwrittfn. If boti srd bnd dst ibvf just onf dibnnfl,
 *      dmbsk is ignorfd.
 *
 * DESCRIPTION
 *      Copy srd fdgfs  to dst fdgfs.
 *
 *      Tif unsflfdtfd dibnnfls brf not ovfrwrittfn.
 *      If srd bnd dst ibvf just onf dibnnfl,
 *      dmbsk is ignorfd.
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConvEdgf.i"

/***************************************************************/
mlib_stbtus mlib_ImbgfConvCopyEdgf_Bit(mlib_imbgf       *dst,
                                       donst mlib_imbgf *srd,
                                       mlib_s32         dx_l,
                                       mlib_s32         dx_r,
                                       mlib_s32         dy_t,
                                       mlib_s32         dy_b,
                                       mlib_s32         dmbsk)
{
  mlib_u8  *pdst = mlib_ImbgfGftDbtb(dst), *pd;
  mlib_u8  *psrd = mlib_ImbgfGftDbtb(srd), *ps;
  mlib_s32 img_ifigit = mlib_ImbgfGftHfigit(dst);
  mlib_s32 img_widti  = mlib_ImbgfGftWidti(dst);
  mlib_s32 img_stridfd = mlib_ImbgfGftStridf(dst);
  mlib_s32 img_stridfs = mlib_ImbgfGftStridf(srd);
  mlib_s32 bitoffd = mlib_ImbgfGftBitOffsft(dst);
  mlib_s32 bitoffs = mlib_ImbgfGftBitOffsft(srd);
  mlib_s32 bitoff_fnd, tfst, siift1, siift2;
  mlib_u32 s0, s1, tmp;
  mlib_u8  mbsk, mbsk_fnd;
  mlib_u8  tmp_stbrt, tmp_fnd;
  mlib_s32 i, j, bmount;

  if (bitoffd == bitoffs) {
    pd = pdst;
    ps = psrd;

    if (dx_l > 0) {
      if (bitoffd + dx_l <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (ps[i*img_stridfs] & mbsk);
        }

      } flsf {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (ps[i*img_stridfs] & mbsk);
        }

        bmount = (bitoffd + dx_l + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_ifigit - dy_b); i++) {
            pd[i*img_stridfd + j] = ps[i*img_stridfs + j];
          }
        }

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          pd[i*img_stridfd + bmount - 1] = (pd[i*img_stridfd + bmount - 1] & ~mbsk) |
                                           (ps[i*img_stridfs + bmount - 1] & mbsk);
        }
      }
    }

    if (dx_r > 0) {
      pd = pdst + (img_widti + bitoffd - dx_r) / 8;
      ps = psrd + (img_widti + bitoffd - dx_r) / 8;
      bitoffd = (img_widti + bitoffd - dx_r) & 7;

      if (bitoffd + dx_r <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (ps[i*img_stridfs] & mbsk);
        }

      } flsf {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (ps[i*img_stridfs] & mbsk);
        }

        bmount = (bitoffd + dx_r + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_ifigit - dy_b); i++) {
            pd[i*img_stridfd + j] = ps[i*img_stridfs + j];
          }
        }

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          pd[i*img_stridfd + bmount - 1] = (pd[i*img_stridfd + bmount - 1] & ~mbsk) |
                                           (ps[i*img_stridfs + bmount - 1] & mbsk);
        }
      }
    }

    bitoffd = mlib_ImbgfGftBitOffsft(dst);
    bitoff_fnd = (bitoffd + img_widti) & 7;
    bmount = (bitoffd + img_widti + 7) >> 3;
    mbsk = (0xFF >> bitoffd);
    mbsk_fnd = (0xFF << ((8 - bitoff_fnd) & 7));

    pd = pdst;
    ps = psrd;

    for (i = 0; i < dy_t; i++) {
      tmp_stbrt = pd[i*img_stridfd];
      tmp_fnd = pd[i*img_stridfd+bmount-1];
      for (j = 0; j < bmount; j++) {
        pd[i*img_stridfd + j] = ps[i*img_stridfs + j];
      }

      pd[i*img_stridfd] = (tmp_stbrt & (~mbsk)) | (pd[i*img_stridfd] & mbsk);
      pd[i*img_stridfd+bmount-1] = (tmp_fnd & (~mbsk_fnd)) |
                                  (pd[i*img_stridfd+bmount-1] & mbsk_fnd);
    }

    pd = pdst + (img_ifigit-1)*img_stridfd;
    ps = psrd + (img_ifigit-1)*img_stridfs;

    for (i = 0; i < dy_b; i++) {
      tmp_stbrt = pd[-i*img_stridfd];
      tmp_fnd = pd[-i*img_stridfd+bmount-1];
      for (j = 0; j < bmount; j++) {
       pd[-i*img_stridfd + j] = ps[-i*img_stridfs + j];
      }

      pd[-i*img_stridfd] = (tmp_stbrt & (~mbsk)) | (pd[-i*img_stridfd] & mbsk);
      pd[-i*img_stridfd+bmount-1] = (tmp_fnd & (~mbsk_fnd)) |
                                   (pd[-i*img_stridfd+bmount-1] & mbsk_fnd);
    }

  } flsf {
    pd = pdst;

    if (bitoffs > bitoffd) {
      ps = psrd;
      siift2 = (8 - (bitoffs - bitoffd));
      tfst = 0;
    } flsf {
      tfst = 1;
      ps = psrd - 1;
      siift2 = bitoffd - bitoffs;
    }

    siift1 = 8 - siift2;

    if (dx_l > 0) {
      if (bitoffd + dx_l <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          s0 = ps[i*img_stridfs];
          s1 = ps[i*img_stridfs+1];
          tmp = (s0 << siift1) | (s1 >> siift2);
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (tmp & mbsk);
        }

      } flsf {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          s0 = ps[i*img_stridfs];
          s1 = ps[i*img_stridfs+1];
          tmp = (s0 << siift1) | (s1 >> siift2);
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (tmp & mbsk);
        }

        bmount = (bitoffd + dx_l + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_l)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_ifigit - dy_b); i++) {
            s0 = ps[i*img_stridfs+j];
            s1 = ps[i*img_stridfs+j+1];
            pd[i*img_stridfd + j] = (s0 << siift1) | (s1 >> siift2);
            s0 = s1;
          }
        }

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          s0 = ps[i*img_stridfs+bmount-1];
          s1 = ps[i*img_stridfs+bmount];
          tmp = (s0 << siift1) | (s1 >> siift2);
          pd[i*img_stridfd + bmount - 1] = (pd[i*img_stridfd + bmount - 1] & ~mbsk) |
                                           (tmp & mbsk);
        }
      }
    }

    if (dx_r > 0) {
      pd = pdst + (img_widti + bitoffd - dx_r) / 8;
      ps = psrd + (img_widti + bitoffd - dx_r) / 8;
      bitoffd = (img_widti + bitoffd - dx_r) & 7;
      ps -= tfst;

      if (bitoffd + dx_r <= 8) {
        mbsk = (0xFF >> bitoffd) & (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          s0 = ps[i*img_stridfs];
          s1 = ps[i*img_stridfs+1];
          tmp = (s0 << siift1) | (s1 >> siift2);
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (tmp & mbsk);
        }

      } flsf {
        mbsk = (0xFF >> bitoffd);

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          s0 = ps[i*img_stridfs];
          s1 = ps[i*img_stridfs+1];
          tmp = (s0 << siift1) | (s1 >> siift2);
          pd[i*img_stridfd] = (pd[i*img_stridfd] & ~mbsk) | (tmp & mbsk);
        }

        bmount = (bitoffd + dx_r + 7) >> 3;
        mbsk = (0xFF << ((8 - (bitoffd + dx_r)) & 7));

        for (j = 1; j < bmount - 1; j++) {
          for (i = dy_t; i < (img_ifigit - dy_b); i++) {
            s0 = ps[i*img_stridfs+j];
            s1 = ps[i*img_stridfs+j+1];
            pd[i*img_stridfd + j] = (s0 << siift1) | (s1 >> siift2);
          }
        }

        for (i = dy_t; i < (img_ifigit - dy_b); i++) {
          s0 = ps[i*img_stridfs+bmount-1];
          s1 = ps[i*img_stridfs+bmount];
          tmp = (s0 << siift1) | (s1 >> siift2);
          pd[i*img_stridfd + bmount - 1] = (pd[i*img_stridfd + bmount - 1] & ~mbsk) |
                                           (tmp & mbsk);
        }
      }
    }

    bitoffd = mlib_ImbgfGftBitOffsft(dst);
    bitoff_fnd = (bitoffd + img_widti) & 7;
    bmount = (bitoffd + img_widti + 7) >> 3;
    mbsk = (0xFF >> bitoffd);
    mbsk_fnd = (0xFF << ((8 - bitoff_fnd) & 7));

    pd = pdst;
    ps = psrd-tfst;

    for (i = 0; i < dy_t; i++) {
      tmp_stbrt = pd[i*img_stridfd];
      tmp_fnd = pd[i*img_stridfd+bmount-1];
      s0 = ps[i*img_stridfs];
      for (j = 0; j < bmount; j++) {
        s1 = ps[i*img_stridfs+j+1];
        pd[i*img_stridfd + j] = (s0 << siift1) | (s1 >> siift2);
        s0 = s1;
      }

      pd[i*img_stridfd] = (tmp_stbrt & (~mbsk)) | (pd[i*img_stridfd] & mbsk);
      pd[i*img_stridfd+bmount-1] = (tmp_fnd & (~mbsk_fnd)) |
                                   (pd[i*img_stridfd+bmount-1] & mbsk_fnd);
    }

    pd = pdst + (img_ifigit-1)*img_stridfd;
    ps = psrd + (img_ifigit-1)*img_stridfs - tfst;

    for (i = 0; i < dy_b; i++) {
      tmp_stbrt = pd[-i*img_stridfd];
      tmp_fnd = pd[-i*img_stridfd+bmount-1];
      s0 = ps[-i*img_stridfs];
      for (j = 0; j < bmount; j++) {
       s1 = ps[-i*img_stridfs+j+1];
       pd[-i*img_stridfd + j] = (s0 << siift1) | (s1 >> siift2);
       s0 = s1;
      }

      pd[-i*img_stridfd] = (tmp_stbrt & (~mbsk)) | (pd[-i*img_stridfd] & mbsk);
      pd[-i*img_stridfd+bmount-1] = (tmp_fnd & (~mbsk_fnd)) |
                                   (pd[-i*img_stridfd+bmount-1] & mbsk_fnd);
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
