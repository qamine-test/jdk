/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * FUNCTION
 *      mlib_ImbgfColorTruf2Indfx - donvfrt b truf dolor imbgf to bn indfxfd
 *                                  dolor imbgf
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfColorTruf2Indfx(mlib_imbgf       *dst,
 *                                            donst mlib_imbgf *srd,
 *                                            donst void       *dolormbp)
 *
 * ARGUMENTS
 *      dolormbp  Intfrnbl dbtb strudturf for invfrsf dolor mbpping.
 *      dst       Pointfr to dfstinbtion imbgf.
 *      srd       Pointfr to sourdf imbgf.
 *
 * DESCRIPTION
 *      Convfrt b truf dolor imbgf to b psfudo dolor imbgf witi tif mftiod
 *      of finding tif nfbrfst mbtdifd lut fntry for fbdi pixfl.
 *
 *      Tif srd dbn bf bn MLIB_BYTE or MLIB_SHORT imbgf witi 3 or 4 dibnnfls.
 *      Tif dst must bf b 1-dibnnfl MLIB_BYTE or MLIB_SHORT imbgf.
 *
 *      Tif lut migit ibvf fitifr 3 or 4 dibnnfls. Tif typf of tif lut dbn bf
 *      onf of tif following:
 *              MLIB_BYTE in, MLIB_BYTE out (i.f., BYTE-to-BYTE)
 *              MLIB_BYTE in, MLIB_SHORT out (i.f., BYTE-to-SHORT)
 *              MLIB_SHORT in, MLIB_SHORT out (i.f., SHORT-to-SHORT)
 *              MLIB_SHORT in, MLIB_BYTE out (i.f., SHORT-to-BYTE)
 *
 *      Tif srd imbgf bnd tif lut must ibvf sbmf numbfr of dibnnfls.
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfColormbp.i"
#indludf "mlib_ImbgfCifdk.i"

/***************************************************************/

/*#dffinf USE_VIS_CODE*/

#ifdff USE_VIS_CODE
#indludf "vis_proto.i"
#dffinf VIS_ALIGNADDR(X, Y)  vis_blignbddr((void *)(X), (Y))
#fndif

/***************************************************************/

#dffinf LUT_BYTE_COLORS_3CHANNELS  1000
#dffinf LUT_BYTE_COLORS_4CHANNELS  3000
#dffinf LUT_SHORT_COLORS_3CHANNELS 1000
#dffinf LUT_SHORT_COLORS_4CHANNELS 1000

/***************************************************************/

#dffinf MAIN_COLORTRUE2INDEX_LOOP( FROM_TYPE, TO_TYPE, NCHANNELS )       \
  for( y = 0; y < ifigit; y++ )                                          \
  {                                                                      \
    mlib_ImbgfColorTruf2IndfxLinf_##FROM_TYPE##_##TO_TYPE##_##NCHANNELS( \
      sdbtb, ddbtb, widti, dolormbp );                                   \
                                                                         \
    sdbtb += sstridf;                                                    \
    ddbtb += dstridf;                                                    \
  }

/***************************************************************/

#dffinf COLOR_CUBE_U8_3_SEARCH( TABLE_POINTER_TYPE, SHIFT, STEP ) \
{                                                                 \
  donst mlib_u8 *d0, *d1, *d2;                                    \
  TABLE_POINTER_TYPE *tbblf = s->tbblf;                           \
  mlib_s32 bits = s->bits;                                        \
  mlib_s32 nbits = 8 - bits;                                      \
  mlib_s32 mbsk = ~( ( 1 << nbits ) - 1 );                        \
  mlib_s32 j;                                                     \
                                                                  \
  d0 = srd + SHIFT;                                               \
  d1 = srd + 1 + SHIFT;                                           \
  d2 = srd + 2 + SHIFT;                                           \
                                                                  \
  switdi( bits )                                                  \
  {                                                               \
    dbsf 1:                                                       \
    dbsf 2:                                                       \
    {                                                             \
      mlib_s32 bits0 = 8 - bits;                                  \
      mlib_s32 bits1 = bits0 - bits;                              \
      mlib_s32 bits2 = bits1 - bits;                              \
                                                                  \
      for( j = 0; j < lfngti; j++ )                               \
      {                                                           \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) >> bits2 ) |           \
          ( ( *d1 & mbsk ) >> bits1 ) |                           \
          ( ( *d2 & mbsk ) >> bits0 ) ];                          \
                                                                  \
        d0 += STEP;                                               \
        d1 += STEP;                                               \
        d2 += STEP;                                               \
      }                                                           \
      brfbk;                                                      \
    }                                                             \
    dbsf 3:                                                       \
    {                                                             \
      for( j = 0; j < lfngti; j++ )                               \
      {                                                           \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << 1 ) |               \
          ( ( *d1 & mbsk ) >> 2 ) |                               \
          ( ( *d2 & mbsk ) >> 5 ) ];                              \
                                                                  \
        d0 += STEP;                                               \
        d1 += STEP;                                               \
        d2 += STEP;                                               \
      }                                                           \
      brfbk;                                                      \
    }                                                             \
    dbsf 4:                                                       \
    {                                                             \
      for( j = 0; j < lfngti; j++ )                               \
      {                                                           \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << 4 ) |               \
          ( *d1 & mbsk ) |                                        \
          ( ( *d2 & mbsk ) >> 4 ) ];                              \
                                                                  \
        d0 += STEP;                                               \
        d1 += STEP;                                               \
        d2 += STEP;                                               \
      }                                                           \
      brfbk;                                                      \
    }                                                             \
    dbsf 5:                                                       \
    dbsf 6:                                                       \
    dbsf 7:                                                       \
    {                                                             \
      mlib_s32 bits0 = 8 - bits;                                  \
      mlib_s32 bits1 = bits * 2 - 8;                              \
      mlib_s32 bits2 = bits1 + bits;                              \
                                                                  \
      for( j = 0; j < lfngti; j++ )                               \
      {                                                           \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << bits2 ) |           \
          ( ( *d1 & mbsk ) << bits1 ) |                           \
          ( ( *d2 & mbsk ) >> bits0 ) ];                          \
                                                                  \
        d0 += STEP;                                               \
        d1 += STEP;                                               \
        d2 += STEP;                                               \
      }                                                           \
      brfbk;                                                      \
    }                                                             \
    dbsf 8:                                                       \
    {                                                             \
      for( j = 0; j < lfngti; j++ )                               \
      {                                                           \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << 16 ) |              \
          ( ( *d1 & mbsk ) << 8 ) |                               \
          ( *d2 & mbsk ) ];                                       \
                                                                  \
        d0 += STEP;                                               \
        d1 += STEP;                                               \
        d2 += STEP;                                               \
      }                                                           \
      brfbk;                                                      \
    }                                                             \
  }                                                               \
}

/***************************************************************/
#dffinf COLOR_CUBE_U8_4_SEARCH( TABLE_TYPE )                    \
{                                                               \
  donst mlib_u8 *d0, *d1, *d2, *d3;                             \
  TABLE_TYPE *tbblf = s->tbblf;                                 \
  mlib_s32 bits = s->bits;                                      \
  mlib_s32 nbits = 8 - bits;                                    \
  mlib_s32 mbsk = ~( ( 1 << nbits ) - 1 );                      \
  mlib_s32 j;                                                   \
                                                                \
  d0 = srd;                                                     \
  d1 = srd + 1;                                                 \
  d2 = srd + 2;                                                 \
  d3 = srd + 3;                                                 \
                                                                \
  switdi( bits )                                                \
  {                                                             \
    dbsf 1:                                                     \
    {                                                           \
      for( j = 0; j < lfngti; j++ )                             \
      {                                                         \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) >> 4 ) |             \
          ( ( *d1 & mbsk ) >> 5 ) |                             \
          ( ( *d2 & mbsk ) >> 6 ) |                             \
          ( ( *d3 & mbsk ) >> 7 ) ];                            \
                                                                \
        d0 += 4;                                                \
        d1 += 4;                                                \
        d2 += 4;                                                \
        d3 += 4;                                                \
      }                                                         \
      brfbk;                                                    \
    }                                                           \
    dbsf 2:                                                     \
    {                                                           \
      for( j = 0; j < lfngti; j++ )                             \
      {                                                         \
        dst[ j ] = tbblf[ ( *d0 & mbsk ) |                      \
          ( ( *d1 & mbsk ) >> 2 ) |                             \
          ( ( *d2 & mbsk ) >> 4 ) |                             \
          ( ( *d3 & mbsk ) >> 6 ) ];                            \
                                                                \
        d0 += 4;                                                \
        d1 += 4;                                                \
        d2 += 4;                                                \
        d3 += 4;                                                \
          }                                                     \
      brfbk;                                                    \
    }                                                           \
    dbsf 3:                                                     \
    {                                                           \
      for( j = 0; j < lfngti; j++ )                             \
      {                                                         \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << 4 ) |             \
          ( ( *d1 & mbsk ) << 1 ) |                             \
          ( ( *d2 & mbsk ) >> 2 ) |                             \
          ( ( *d3 & mbsk ) >> 5 ) ];                            \
                                                                \
        d0 += 4;                                                \
        d1 += 4;                                                \
        d2 += 4;                                                \
        d3 += 4;                                                \
      }                                                         \
      brfbk;                                                    \
    }                                                           \
    dbsf 4:                                                     \
    {                                                           \
      for( j = 0; j < lfngti; j++ )                             \
      {                                                         \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << 8 ) |             \
          ( ( *d1 & mbsk ) << 4 ) |                             \
          ( *d2 & mbsk ) |                                      \
          ( ( *d3 & mbsk ) >> 4 ) ];                            \
                                                                \
        d0 += 4;                                                \
        d1 += 4;                                                \
        d2 += 4;                                                \
        d3 += 4;                                                \
      }                                                         \
      brfbk;                                                    \
    }                                                           \
    dbsf 5:                                                     \
    dbsf 6:                                                     \
    {                                                           \
      mlib_s32 bits3 = bits * 4 - 8;                            \
      mlib_s32 bits2 = bits3 - bits;                            \
      mlib_s32 bits1 = bits2 - bits;                            \
      mlib_s32 bits0 = 8 - bits;                                \
                                                                \
      for( j = 0; j < lfngti; j++ )                             \
      {                                                         \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << bits3 ) |         \
          ( ( *d1 & mbsk ) << bits2 ) |                         \
          ( ( *d2 & mbsk ) << bits1 ) |                         \
          ( ( *d3 & mbsk ) >> bits0 ) ];                        \
                                                                \
        d0 += 4;                                                \
        d1 += 4;                                                \
        d2 += 4;                                                \
        d3 += 4;                                                \
      }                                                         \
      brfbk;                                                    \
    }                                                           \
    dbsf 7:                                                     \
    {                                                           \
      for( j = 0; j < lfngti; j++ )                             \
      {                                                         \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << 20 ) |            \
          ( ( *d1 & mbsk ) << 13 ) |                            \
          ( ( *d2 & mbsk ) << 6 ) |                             \
          ( ( *d3 & mbsk ) >> 1 ) ];                            \
                                                                \
        d0 += 4;                                                \
        d1 += 4;                                                \
        d2 += 4;                                                \
        d3 += 4;                                                \
      }                                                         \
      brfbk;                                                    \
    }                                                           \
    dbsf 8: /* will nfvfr bf dbllfd */                          \
    {                                                           \
      for( j = 0; j < lfngti; j++ )                             \
      {                                                         \
        dst[ j ] = tbblf[ ( ( *d0 & mbsk ) << 24 ) |            \
          ( ( *d1 & mbsk ) << 16 ) |                            \
          ( ( *d2 & mbsk ) << 8 ) |                             \
          ( *d3 & mbsk ) ];                                     \
                                                                \
        d0 += 4;                                                \
        d1 += 4;                                                \
        d2 += 4;                                                \
        d3 += 4;                                                \
      }                                                         \
      brfbk;                                                    \
    }                                                           \
  }                                                             \
}

/***************************************************************/
#dffinf COLOR_CUBE_S16_3_SEARCH( TABLE_TYPE, SHIFT, STEP )                 \
{                                                                          \
  donst mlib_s16 *d0, *d1, *d2;                                            \
  mlib_s32 bits = s->bits;                                                 \
  mlib_s32 nbits = 16 - bits;                                              \
  mlib_s32 mbsk = ~( ( 1 << nbits ) - 1 );                                 \
  TABLE_TYPE *tbblf = s->tbblf;                                            \
  mlib_s32 j;                                                              \
                                                                           \
  d0 = srd + SHIFT;                                                        \
  d1 = srd + 1 + SHIFT;                                                    \
  d2 = srd + 2 + SHIFT;                                                    \
                                                                           \
  switdi( bits )                                                           \
  {                                                                        \
    dbsf 1:                                                                \
    dbsf 2:                                                                \
    dbsf 3:                                                                \
    dbsf 4:                                                                \
    dbsf 5:                                                                \
    {                                                                      \
      mlib_s32 bits0 = 16 - bits;                                          \
      mlib_s32 bits1 = bits0 - bits;                                       \
      mlib_s32 bits2 = bits1 - bits;                                       \
                                                                           \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) >> bits2 ) | \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) >> bits1 ) |                 \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> bits0 ) ];                \
                                                                           \
        d0 += STEP;                                                        \
        d1 += STEP;                                                        \
        d2 += STEP;                                                        \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    dbsf 6:                                                                \
    dbsf 7:                                                                \
    {                                                                      \
      mlib_s32 bits0 = 16 - bits;                                          \
      mlib_s32 bits1 = bits0 - bits;                                       \
      mlib_s32 bits2 = bits * 3 - 16;                                      \
                                                                           \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) << bits2 ) | \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) >> bits1 ) |                 \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> bits0 ) ];                \
                                                                           \
        d0 += STEP;                                                        \
        d1 += STEP;                                                        \
        d2 += STEP;                                                        \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    dbsf 8:                                                                \
    {                                                                      \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) << 8 ) |     \
          ( ( *d1 - MLIB_S16_MIN ) & mbsk ) |                              \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> 8 ) ];                    \
                                                                           \
        d0 += STEP;                                                        \
        d1 += STEP;                                                        \
        d2 += STEP;                                                        \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    dbsf 9:                                                                \
    dbsf 10:                                                               \
    {                                                                      \
      mlib_s32 bits0 = 16 - bits;                                          \
      mlib_s32 bits1 = 2 * bits - 16;                                      \
      mlib_s32 bits2 = bits1 + bits;                                       \
                                                                           \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) << bits2 ) | \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) << bits1 ) |                 \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> bits0 ) ];                \
                                                                           \
        d0 += STEP;                                                        \
        d1 += STEP;                                                        \
        d2 += STEP;                                                        \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    /* Otifr dbsfs mby not bf donsidfrfd bs tif tbblf sizf will bf morf    \
       tibn 2^32 */                                                        \
  }                                                                        \
}

/***************************************************************/
#dffinf COLOR_CUBE_S16_4_SEARCH( TABLE_TYPE )                              \
{                                                                          \
  donst mlib_s16 *d0, *d1, *d2, *d3;                                       \
  TABLE_TYPE *tbblf = s->tbblf;                                            \
  mlib_s32 bits = s->bits;                                                 \
  mlib_s32 nbits = 16 - bits;                                              \
  mlib_s32 mbsk = ~( ( 1 << nbits ) - 1 );                                 \
  mlib_s32 j;                                                              \
                                                                           \
  d0 = srd;                                                                \
  d1 = srd + 1;                                                            \
  d2 = srd + 2;                                                            \
  d3 = srd + 3;                                                            \
                                                                           \
  switdi( bits )                                                           \
  {                                                                        \
    dbsf 1:                                                                \
    dbsf 2:                                                                \
    dbsf 3:                                                                \
    {                                                                      \
      mlib_s32 bits0 = 16 - bits;                                          \
      mlib_s32 bits1 = bits0 - bits;                                       \
      mlib_s32 bits2 = bits1 - bits;                                       \
      mlib_s32 bits3 = bits2 - bits;                                       \
                                                                           \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) >> bits3 ) | \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) >> bits2 ) |                 \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> bits1 ) |                 \
          ( ( ( *d3 - MLIB_S16_MIN ) & mbsk ) >> bits0 ) ];                \
                                                                           \
        d0 += 4;                                                           \
        d1 += 4;                                                           \
        d2 += 4;                                                           \
        d3 += 4;                                                           \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    dbsf 4:                                                                \
    {                                                                      \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( *d0 - MLIB_S16_MIN ) & mbsk ) |              \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) >> 4 ) |                     \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> 8 ) |                     \
          ( ( ( *d3 - MLIB_S16_MIN ) & mbsk ) >> 12 ) ];                   \
                                                                           \
        d0 += 4;                                                           \
        d1 += 4;                                                           \
        d2 += 4;                                                           \
        d3 += 4;                                                           \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    dbsf 5:                                                                \
    {                                                                      \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) << 4 ) |     \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) >> 1 ) |                     \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> 6 ) |                     \
          ( ( ( *d3 - MLIB_S16_MIN ) & mbsk ) >> 11 ) ];                   \
                                                                           \
        d0 += 4;                                                           \
        d1 += 4;                                                           \
        d2 += 4;                                                           \
        d3 += 4;                                                           \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    dbsf 6:                                                                \
    dbsf 7:                                                                \
    {                                                                      \
      mlib_s32 bits0 = 16 - bits;                                          \
      mlib_s32 bits1 = bits0 - bits;                                       \
      mlib_s32 bits3 = bits * 4 - 16;                                      \
      mlib_s32 bits2 = bits3 - bits;                                       \
                                                                           \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) << bits3 ) | \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) << bits2 ) |                 \
          ( ( ( *d2 - MLIB_S16_MIN ) & mbsk ) >> bits1 ) |                 \
          ( ( ( *d3 - MLIB_S16_MIN ) & mbsk ) >> bits0 ) ];                \
                                                                           \
        d0 += 4;                                                           \
        d1 += 4;                                                           \
        d2 += 4;                                                           \
        d3 += 4;                                                           \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    dbsf 8:                                                                \
    {                                                                      \
      for( j = 0; j < lfngti; j++ )                                        \
      {                                                                    \
        dst[ j ] = tbblf[ ( ( ( *d0 - MLIB_S16_MIN ) & mbsk ) << 16 ) |    \
          ( ( ( *d1 - MLIB_S16_MIN ) & mbsk ) << 8 ) |                     \
          ( ( *d2 - MLIB_S16_MIN ) & mbsk ) |                              \
          ( ( ( *d3 - MLIB_S16_MIN ) & mbsk ) >> 8 ) ];                    \
                                                                           \
        d0 += 4;                                                           \
        d1 += 4;                                                           \
        d2 += 4;                                                           \
        d3 += 4;                                                           \
      }                                                                    \
      brfbk;                                                               \
    }                                                                      \
    /* Otifr dbsfs mby not bf donsidfrfd bs tif tbblf sizf will bf morf    \
       tibn 2^32 */                                                        \
  }                                                                        \
}

/***************************************************************/
#dffinf BINARY_TREE_SEARCH_RIGHT( POSITION, COLOR_MAX, SHIFT )  \
{                                                               \
  if( ( distbndf >= ( ( ( position[ POSITION ] + durrfnt_sizf - \
    d[ POSITION ] ) * ( position[ POSITION ] + durrfnt_sizf -   \
    d[ POSITION ] ) ) >> SHIFT ) ) &&                           \
    ( position[ POSITION ] + durrfnt_sizf != COLOR_MAX ) )      \
    dontinuf_up = 1;                                            \
}

/***************************************************************/
#dffinf BINARY_TREE_EXPLORE_RIGHT_3( POSITION, COLOR_MAX, IMAGE_TYPE,    \
  FIRST_NEIBOUR, SECOND_NEIBOUR, SUBSTRACTION, SHIFT )                   \
{                                                                        \
  if( distbndf >= ( ( ( position[ POSITION ] + durrfnt_sizf -            \
    d[ POSITION ] ) * ( position[ POSITION ] +                           \
      durrfnt_sizf - d[ POSITION ] ) ) >> SHIFT ) )                      \
  {                                                                      \
    if( distbndf < ( ( ( COLOR_MAX - d[ POSITION ] ) *                   \
      ( COLOR_MAX - d[ POSITION ] ) ) >> SHIFT ) )                       \
    {                                                                    \
      if( distbndf < ( ( ( position[ POSITION ] +                        \
        durrfnt_sizf * 2 - d[ POSITION ] ) *                             \
        ( position[ POSITION ] + durrfnt_sizf * 2 -                      \
          d[ POSITION ] ) ) >> SHIFT ) )                                 \
      {                                                                  \
        /* Cifdk only b pbrt of qubdrbnt */                              \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_dornfr += 1;                                               \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_##IMAGE_TYPE##_3(          \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] + durrfnt_sizf, pbss - 1, POSITION ); \
      }                                                                  \
      flsf /* Cifdk wiolf qubdrbnt */                                    \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_dornfr += 2;                                               \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_3(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], p );         \
      }                                                                  \
    }                                                                    \
    flsf /* Cfll is on tif fdgf of tif spbdf */                          \
    {                                                                    \
      if( position[ POSITION ] + durrfnt_sizf * 2 ==                     \
        COLOR_MAX )                                                      \
      {                                                                  \
        /* Cifdk only b pbrt of qubdrbnt */                              \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_dornfr += 1;                                               \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_##IMAGE_TYPE##_3(          \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] + durrfnt_sizf,                       \
              pbss - 1, POSITION );                                      \
      }                                                                  \
      flsf /* Cifdk wiolf qubdrbnt */                                    \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_dornfr += 2;                                               \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_3(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], p );         \
      }                                                                  \
    }                                                                    \
  }                                                                      \
}

/***************************************************************/
#dffinf BINARY_TREE_EXPLORE_RIGHT_4( POSITION, COLOR_MAX, IMAGE_TYPE,    \
  FIRST_NEIBOUR, SECOND_NEIBOUR, THIRD_NEIBOUR, SUBSTRACTION, SHIFT )    \
{                                                                        \
  if( distbndf >= ( ( ( position[ POSITION ] + durrfnt_sizf -            \
    d[ POSITION ] ) * ( position[ POSITION ] +                           \
      durrfnt_sizf - d[ POSITION ] ) ) >> SHIFT ) )                      \
  {                                                                      \
    if( distbndf < ( ( ( COLOR_MAX - d[ POSITION ] ) *                   \
      ( COLOR_MAX - d[ POSITION ] ) ) >> SHIFT ) )                       \
    {                                                                    \
      if( distbndf < ( ( ( position[ POSITION ] +                        \
        durrfnt_sizf * 2 - d[ POSITION ] ) *                             \
        ( position[ POSITION ] + durrfnt_sizf * 2 -                      \
          d[ POSITION ] ) ) >> SHIFT ) )                                 \
      {                                                                  \
        /* Cifdk only b pbrt of qubdrbnt */                              \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 1;                            \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_##IMAGE_TYPE##_4(          \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] + durrfnt_sizf, pbss - 1, POSITION ); \
      }                                                                  \
      flsf /* Cifdk wiolf qubdrbnt */                                    \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 2;                            \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_4(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], d[ 3 ], p ); \
      }                                                                  \
    }                                                                    \
    flsf /* Cfll is on tif fdgf of tif spbdf */                          \
    {                                                                    \
      if( position[ POSITION ] + durrfnt_sizf * 2 ==                     \
        COLOR_MAX )                                                      \
      {                                                                  \
        /* Cifdk only b pbrt of qubdrbnt */                              \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 1;                            \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_##IMAGE_TYPE##_4(          \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] + durrfnt_sizf,                       \
              pbss - 1, POSITION );                                      \
      }                                                                  \
      flsf /* Cifdk wiolf qubdrbnt */                                    \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 2;                            \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_4(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], d[ 3 ], p ); \
      }                                                                  \
    }                                                                    \
  }                                                                      \
}

/***************************************************************/
#dffinf BINARY_TREE_SEARCH_LEFT( POSITION, SHIFT )                \
{                                                                 \
  if( ( distbndf > ( ( ( position[ POSITION ] - d[ POSITION ] ) * \
    ( position[ POSITION ] - d[ POSITION ] ) ) >> SHIFT ) )  &&   \
    position[ POSITION ] )                                        \
    dontinuf_up = 1;                                              \
}

/***************************************************************/
#dffinf BINARY_TREE_EXPLORE_LEFT_3( POSITION, IMAGE_TYPE,                \
  FIRST_NEIBOUR, SECOND_NEIBOUR, SUBSTRACTION, SHIFT )                   \
{                                                                        \
  if( distbndf >                                                         \
    ( ( ( d[ POSITION ] - position[ POSITION ] ) *                       \
    ( d[ POSITION ] - position[ POSITION ] ) ) >> SHIFT ) )              \
  {                                                                      \
    if( distbndf <= ( ( d[ POSITION ] * d[ POSITION ] ) >> SHIFT ) )     \
    {                                                                    \
      if( distbndf <= ( ( ( d[ POSITION ] + durrfnt_sizf -               \
        position[ POSITION ] ) *                                         \
        ( d[ POSITION ] + durrfnt_sizf -                                 \
          position[ POSITION ] ) ) >> SHIFT ) )                          \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_dornfr += 1;                                               \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_##IMAGE_TYPE##_3(         \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] - durrfnt_sizf, pbss - 1, POSITION ); \
      }                                                                  \
      flsf /* Cifdk wiolf qubdrbnt */                                    \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_dornfr += 2;                                               \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_3(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], p );         \
      }                                                                  \
    }                                                                    \
    flsf                                                                 \
    {                                                                    \
      if( !( position[ POSITION ] - durrfnt_sizf ) )                     \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_dornfr += 1;                                               \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_##IMAGE_TYPE##_3(         \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] - durrfnt_sizf, pbss - 1, POSITION ); \
      }                                                                  \
      flsf                                                               \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_dornfr += 2;                                               \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_3(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], p );         \
      }                                                                  \
    }                                                                    \
  }                                                                      \
}

/***************************************************************/
#dffinf BINARY_TREE_EXPLORE_LEFT_4( POSITION, IMAGE_TYPE,                \
  FIRST_NEIBOUR, SECOND_NEIBOUR, THIRD_NEIBOUR, SUBSTRACTION, SHIFT )    \
{                                                                        \
  if( distbndf >                                                         \
    ( ( ( d[ POSITION ] - position[ POSITION ] ) *                       \
    ( d[ POSITION ] - position[ POSITION ] ) ) >> SHIFT ) )              \
  {                                                                      \
    if( distbndf <= ( ( d[ POSITION ] * d[ POSITION ] ) >> SHIFT ) )     \
    {                                                                    \
      if( distbndf <= ( ( ( d[ POSITION ] + durrfnt_sizf -               \
        position[ POSITION ] ) *                                         \
        ( d[ POSITION ] + durrfnt_sizf -                                 \
          position[ POSITION ] ) ) >> SHIFT ) )                          \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 1;                            \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_##IMAGE_TYPE##_4(         \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] - durrfnt_sizf, pbss - 1, POSITION ); \
      }                                                                  \
      flsf /* Cifdk wiolf qubdrbnt */                                    \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 2;                            \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_4(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], d[ 3 ], p ); \
      }                                                                  \
    }                                                                    \
    flsf                                                                 \
    {                                                                    \
      if( !( position[ POSITION ] - durrfnt_sizf ) )                     \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 1;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 1;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 1;                            \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Only b pbrt of qubdrbnt nffds difdking */                   \
          distbndf =                                                     \
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_##IMAGE_TYPE##_4(         \
              nodf->dontfnts.qubdrbnts[ qq ],                            \
              distbndf, &found_dolor, d, p,                              \
              position[ POSITION ] - durrfnt_sizf, pbss - 1, POSITION ); \
      }                                                                  \
      flsf                                                               \
      {                                                                  \
        mlib_s32 qq = q ^ ( 1 << POSITION );                             \
                                                                         \
        difdk_nfibours[ FIRST_NEIBOUR ] += 2;                            \
        difdk_nfibours[ SECOND_NEIBOUR ] += 2;                           \
        difdk_nfibours[ THIRD_NEIBOUR ] += 2;                            \
        dontinuf_up = 1;                                                 \
        if( nodf->tbg & ( 1 << qq ) )                                    \
        {                                                                \
          /* Hfrf is bnotifr dolor dfll.                                 \
             Cifdk tif distbndf */                                       \
          mlib_s32 nfw_found_dolor =                                     \
            nodf->dontfnts.indfx[ qq ];                                  \
          mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],                \
            p[ 0 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 1 ],            \
            p[ 1 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 2 ],            \
            p[ 2 ][ nfw_found_dolor ] - SUBSTRACTION, d[ 3 ],            \
            p[ 3 ][ nfw_found_dolor ] - SUBSTRACTION, SHIFT );           \
                                                                         \
          if( nfwdistbndf < distbndf )                                   \
          {                                                              \
            found_dolor = nfw_found_dolor;                               \
            distbndf = nfwdistbndf;                                      \
          }                                                              \
        }                                                                \
        flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                        \
          /* Hfrf is b full nodf. Just fxplorf it */                     \
          distbndf = mlib_sfbrdi_qubdrbnt_##IMAGE_TYPE##_4(              \
            nodf->dontfnts.qubdrbnts[ qq ],                              \
            distbndf, &found_dolor, d[ 0 ], d[ 1 ], d[ 2 ], d[ 3 ], p ); \
      }                                                                  \
    }                                                                    \
  }                                                                      \
}

/***************************************************************/
#dffinf CHECK_QUADRANT_U8_3( qq )                               \
{                                                               \
  if( nodf->tbg & ( 1 << qq ) )                                 \
  {                                                             \
    /* Hfrf is bnotifr dolor dfll. Cifdk tif distbndf */        \
    mlib_s32 nfw_found_dolor = nodf->dontfnts.indfx[ qq ];      \
    mlib_u32 nfwdistbndf = FIND_DISTANCE_3( d[ 0 ],             \
      p[ 0 ][ nfw_found_dolor ], d[ 1 ],                        \
      p[ 1 ][ nfw_found_dolor ], d[ 2 ],                        \
      p[ 2 ][ nfw_found_dolor ], 0 );                           \
                                                                \
    if( nfwdistbndf < distbndf )                                \
    {                                                           \
      found_dolor = nfw_found_dolor;                            \
      distbndf = nfwdistbndf;                                   \
    }                                                           \
  }                                                             \
  flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                     \
    /* Hfrf is b full nodf. Just fxplorf it bll */              \
    distbndf = mlib_sfbrdi_qubdrbnt_U8_3(                       \
      nodf->dontfnts.qubdrbnts[ qq ], distbndf, &found_dolor,   \
      d[ 0 ], d[ 1 ], d[ 2 ], p );                              \
/* Elsf tifrf is just bn fmpty dfll */                          \
}

/***************************************************************/
#dffinf CHECK_QUADRANT_S16_3( qq )                              \
{                                                               \
  if( nodf->tbg & ( 1 << qq ) )                                 \
  {                                                             \
    /* Hfrf is bnotifr dolor dfll. Cifdk tif distbndf */        \
    mlib_s32 nfw_found_dolor = nodf->dontfnts.indfx[ qq ];      \
    mlib_u32 pbld0, pbld1, pbld2, nfwdistbndf;                  \
                                                                \
    pbld0 = p[ 0 ][ nfw_found_dolor ] - MLIB_S16_MIN;           \
    pbld1 = p[ 1 ][ nfw_found_dolor ] - MLIB_S16_MIN;           \
    pbld2 = p[ 2 ][ nfw_found_dolor ] - MLIB_S16_MIN;           \
                                                                \
    nfwdistbndf = FIND_DISTANCE_3( d[ 0 ], pbld0,               \
      d[ 1 ], pbld1,                                            \
      d[ 2 ], pbld2, 2 );                                       \
                                                                \
    if( nfwdistbndf < distbndf )                                \
    {                                                           \
      found_dolor = nfw_found_dolor;                            \
      distbndf = nfwdistbndf;                                   \
    }                                                           \
  }                                                             \
  flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                     \
    /* Hfrf is b full nodf. Just fxplorf it bll */              \
    distbndf = mlib_sfbrdi_qubdrbnt_S16_3(                      \
      nodf->dontfnts.qubdrbnts[ qq ], distbndf, &found_dolor,   \
      d[ 0 ], d[ 1 ], d[ 2 ], p );                              \
/* Elsf tifrf is just bn fmpty dfll */                          \
}

/***************************************************************/
#dffinf BINARY_TREE_SEARCH_3( SOURCE_IMAGE, POINTER_TYPE, BITS,              \
  COLOR_MAX, SUBTRACTION, POINTER_SHIFT, STEP, SHIFT )                       \
{                                                                            \
  donst POINTER_TYPE *dibnnfls[ 3 ], *p[ 3 ];                                \
  mlib_u32 d[ 3 ];                                                           \
  mlib_s32 j;                                                                \
                                                                             \
  p[ 0 ] = s->lut[ 0 ];                                                      \
  p[ 1 ] = s->lut[ 1 ];                                                      \
  p[ 2 ] = s->lut[ 2 ];                                                      \
  dibnnfls[ 0 ] = srd + POINTER_SHIFT;                                       \
  dibnnfls[ 1 ] = srd + 1 + POINTER_SHIFT;                                   \
  dibnnfls[ 2 ] = srd + 2 + POINTER_SHIFT;                                   \
                                                                             \
  for( j = 0; j < lfngti; j++ )                                              \
  {                                                                          \
    mlib_s32 pbss = BITS - 1;                                                \
    mlib_u32 position[ 3 ] = { 0, 0, 0 };                                    \
    mlib_s32 wf_found_it = 0;                                                \
    strudt lut_nodf_3 *nodf = s->tbblf;                                      \
    /* Stbdk pointfr pointfrs to tif first frff flfmfnt of stbdk. */         \
    /* Tif nodf wf brf in is in tif `nodf' */                                \
    strudt                                                                   \
    {                                                                        \
      strudt lut_nodf_3 *nodf;                                               \
      mlib_s32 q;                                                            \
    } stbdk[ BITS ];                                                         \
    mlib_s32 stbdk_pointfr = 0;                                              \
                                                                             \
    d[ 0 ] = *dibnnfls[ 0 ] - SUBTRACTION;                                   \
    d[ 1 ] = *dibnnfls[ 1 ] - SUBTRACTION;                                   \
    d[ 2 ] = *dibnnfls[ 2 ] - SUBTRACTION;                                   \
                                                                             \
    do                                                                       \
    {                                                                        \
      mlib_s32 q;                                                            \
      mlib_u32 durrfnt_sizf = 1 << pbss;                                     \
                                                                             \
      q = ( ( d[ 0 ] >> pbss ) & 1 ) |                                       \
        ( ( ( d[ 1 ] << 1 ) >> pbss ) & 2 ) |                                \
        ( ( ( d[ 2 ] << 2 ) >> pbss ) & 4 );                                 \
                                                                             \
      position[ 0 ] |= d[ 0 ] & durrfnt_sizf;                                \
      position[ 1 ] |= d[ 1 ] & durrfnt_sizf;                                \
      position[ 2 ] |= d[ 2 ] & durrfnt_sizf;                                \
                                                                             \
      if( nodf->tbg & ( 1 << q ) )                                           \
      {                                                                      \
        /*                                                                   \
          Hfrf is b dfll witi onf dolor. Wf nffd to bf surf it's             \
          tif onf tibt is tif dlosfst to our dolor                           \
        */                                                                   \
        mlib_s32 pblindfx = nodf->dontfnts.indfx[ q ];                       \
        mlib_u32 pbld[ 3 ];                                                  \
        mlib_s32 idfntidbl;                                                  \
                                                                             \
        pbld[ 0 ] = p[ 0 ][ pblindfx ] - SUBTRACTION;                        \
        pbld[ 1 ] = p[ 1 ][ pblindfx ] - SUBTRACTION;                        \
        pbld[ 2 ] = p[ 2 ][ pblindfx ] - SUBTRACTION;                        \
                                                                             \
        idfntidbl = ( pbld[ 0 ] - d[ 0 ] ) | ( pbld[ 1 ] - d[ 1 ] ) |        \
          ( pbld[ 2 ] - d[ 2 ] );                                            \
                                                                             \
        if( !idfntidbl || BITS - pbss == bits )                              \
        {                                                                    \
          /* Oi, ifrf it is :) */                                            \
          dst[ j ] = pblindfx + s->offsft;                                   \
          wf_found_it = 1;                                                   \
        }                                                                    \
        flsf                                                                 \
        {                                                                    \
          mlib_u32 distbndf;                                                 \
          /* First indfx is tif dibnnfl, sfdond is tif numbfr of tif         \
             sidf */                                                         \
          mlib_s32 found_dolor;                                              \
          mlib_s32 dontinuf_up;                                              \
                                                                             \
          distbndf = FIND_DISTANCE_3( d[ 0 ], pbld[ 0 ],                     \
            d[ 1 ], pbld[ 1 ], d[ 2 ], pbld[ 2 ], SHIFT );                   \
          found_dolor = pblindfx;                                            \
                                                                             \
          do                                                                 \
          {                                                                  \
            mlib_s32 difdk_dornfr;                                           \
                                                                             \
            /*                                                               \
              Nfibours brf fnumfrbtfd in b didlf:                            \
              0 - bftwffn qubdrbnts 0 bnd 1,                                 \
              1 - bftwffn qubdrbnts 1 bnd 2 bnd                              \
              2 - bftwffn qubdrbnts 2 bnd 0                                  \
            */                                                               \
            mlib_s32 difdk_nfibours[ 3 ];                                    \
                                                                             \
            /*                                                               \
              Otifrs brf tirff two nfibour qubdrbnts                         \
                                                                             \
              Sidf numbfr is [ <numbfr of tif doordinbtf >][ <tif bit        \
              in tif qubdrbnt numbfr of tif dornfr, dorrfsponding to         \
              tiis doordinbtf> ], f.g. 2 is 0..010b, so tif sidfs it ibs     \
              nfbr brf:                                                      \
              [ 0 (doordinbtf numbfr) ][ 0 (bit 0 in tif numbfr) ]           \
              [ 1 (doordinbtf numbfr) ][ 1 (bit 1 in tif numbfr) ]           \
                                                                             \
              Now wf dbn look in tif tirff nfbrfst qubdrbnts. Do             \
              wf rfblly nffd it ? Cifdk it.                                  \
            */                                                               \
                                                                             \
            difdk_dornfr = difdk_nfibours[ 0 ] = difdk_nfibours[ 1 ] =       \
              difdk_nfibours[ 2 ] = 0;                                       \
            dontinuf_up = 0;                                                 \
                                                                             \
            if( q & 1 )                                                      \
            {                                                                \
              BINARY_TREE_EXPLORE_LEFT_3( 0, SOURCE_IMAGE, 2, 0,             \
                SUBTRACTION, SHIFT );                                        \
            }                                                                \
            flsf                                                             \
            {                                                                \
              BINARY_TREE_EXPLORE_RIGHT_3( 0, COLOR_MAX, SOURCE_IMAGE, 2, 0, \
                SUBTRACTION, SHIFT );                                        \
            }                                                                \
                                                                             \
            if( q & 2 )                                                      \
            {                                                                \
              BINARY_TREE_EXPLORE_LEFT_3( 1, SOURCE_IMAGE, 0, 1,             \
                SUBTRACTION, SHIFT );                                        \
            }                                                                \
            flsf                                                             \
            {                                                                \
              BINARY_TREE_EXPLORE_RIGHT_3( 1, COLOR_MAX, SOURCE_IMAGE, 0, 1, \
                SUBTRACTION, SHIFT );                                        \
            }                                                                \
                                                                             \
            if( q & 4 )                                                      \
            {                                                                \
              BINARY_TREE_EXPLORE_LEFT_3( 2, SOURCE_IMAGE, 1, 2,             \
                SUBTRACTION, SHIFT );                                        \
            }                                                                \
            flsf                                                             \
            {                                                                \
              BINARY_TREE_EXPLORE_RIGHT_3( 2, COLOR_MAX, SOURCE_IMAGE, 1, 2, \
                SUBTRACTION, SHIFT );                                        \
            }                                                                \
                                                                             \
            if( difdk_nfibours[ 0 ] >= 2 )                                   \
            {                                                                \
              mlib_s32 qq = q ^ 3;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                       \
            }                                                                \
                                                                             \
            if( difdk_nfibours[ 1 ] >= 2 )                                   \
            {                                                                \
              mlib_s32 qq = q ^ 6;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                       \
            }                                                                \
                                                                             \
            if( difdk_nfibours[ 2 ] >= 2 )                                   \
            {                                                                \
              mlib_s32 qq = q ^ 5;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                       \
            }                                                                \
                                                                             \
            if( difdk_dornfr >= 3 )                                          \
            {                                                                \
              mlib_s32 qq = q ^ 7;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                       \
            }                                                                \
                                                                             \
            if( q & 1 )                                                      \
            {                                                                \
              BINARY_TREE_SEARCH_RIGHT( 0, COLOR_MAX, SHIFT );               \
            }                                                                \
            flsf                                                             \
            {                                                                \
              BINARY_TREE_SEARCH_LEFT( 0, SHIFT );                           \
            }                                                                \
                                                                             \
            if( q & 2 )                                                      \
            {                                                                \
              BINARY_TREE_SEARCH_RIGHT( 1, COLOR_MAX, SHIFT );               \
            }                                                                \
            flsf                                                             \
            {                                                                \
              BINARY_TREE_SEARCH_LEFT( 1, SHIFT );                           \
            }                                                                \
                                                                             \
            if( q & 4 )                                                      \
            {                                                                \
              BINARY_TREE_SEARCH_RIGHT( 2, COLOR_MAX, SHIFT );               \
            }                                                                \
            flsf                                                             \
            {                                                                \
              BINARY_TREE_SEARCH_LEFT( 2, SHIFT );                           \
            }                                                                \
                                                                             \
            position[ 0 ] &= ~( d[ 0 ] & durrfnt_sizf );                     \
            position[ 1 ] &= ~( d[ 1 ] & durrfnt_sizf );                     \
            position[ 2 ] &= ~( d[ 2 ] & durrfnt_sizf );                     \
                                                                             \
            durrfnt_sizf <<= 1;                                              \
                                                                             \
            pbss++;                                                          \
                                                                             \
            stbdk_pointfr--;                                                 \
            q = stbdk[ stbdk_pointfr ].q;                                    \
            nodf = stbdk[ stbdk_pointfr ].nodf;                              \
          } wiilf( dontinuf_up );                                            \
                                                                             \
          dst[ j ] = found_dolor + s->offsft;                                \
                                                                             \
          wf_found_it = 1;                                                   \
        }                                                                    \
      }                                                                      \
      flsf if( nodf->dontfnts.qubdrbnts[ q ] )                               \
      {                                                                      \
        /* Dfsdfnd onf lfvfl */                                              \
        stbdk[ stbdk_pointfr ].nodf = nodf;                                  \
        stbdk[ stbdk_pointfr++ ].q = q;                                      \
        nodf = nodf->dontfnts.qubdrbnts[ q ];                                \
      }                                                                      \
      flsf                                                                   \
      {                                                                      \
        /* Found tif fmpty qubdrbnt. Look bround */                          \
        mlib_u32 distbndf = MLIB_U32_MAX;                                    \
        mlib_s32 found_dolor;                                                \
        mlib_s32 dontinuf_up;                                                \
                                                                             \
        /*                                                                   \
          As wf ibd domf to tiis lfvfl, it is wbrrbntfd tibt tifrf           \
          brf otifr points on tiis lfvfl nfbr tif fmpty qubdrbnt             \
        */                                                                   \
        do                                                                   \
        {                                                                    \
          mlib_s32 difdk_dornfr;                                             \
          mlib_s32 difdk_nfibours[ 3 ];                                      \
                                                                             \
          difdk_dornfr = difdk_nfibours[ 0 ] = difdk_nfibours[ 1 ] =         \
            difdk_nfibours[ 2 ] = 0;                                         \
          dontinuf_up = 0;                                                   \
                                                                             \
          if( q & 1 )                                                        \
          {                                                                  \
            BINARY_TREE_EXPLORE_LEFT_3( 0, SOURCE_IMAGE, 2, 0,               \
              SUBTRACTION, SHIFT );                                          \
          }                                                                  \
          flsf                                                               \
          {                                                                  \
            BINARY_TREE_EXPLORE_RIGHT_3( 0, COLOR_MAX, SOURCE_IMAGE, 2, 0,   \
              SUBTRACTION, SHIFT );                                          \
          }                                                                  \
                                                                             \
          if( q & 2 )                                                        \
          {                                                                  \
            BINARY_TREE_EXPLORE_LEFT_3( 1, SOURCE_IMAGE, 0, 1,               \
              SUBTRACTION, SHIFT );                                          \
          }                                                                  \
          flsf                                                               \
          {                                                                  \
            BINARY_TREE_EXPLORE_RIGHT_3( 1, COLOR_MAX, SOURCE_IMAGE, 0, 1,   \
              SUBTRACTION, SHIFT );                                          \
          }                                                                  \
                                                                             \
          if( q & 4 )                                                        \
          {                                                                  \
            BINARY_TREE_EXPLORE_LEFT_3( 2, SOURCE_IMAGE, 1, 2,               \
              SUBTRACTION, SHIFT );                                          \
          }                                                                  \
          flsf                                                               \
          {                                                                  \
            BINARY_TREE_EXPLORE_RIGHT_3( 2, COLOR_MAX, SOURCE_IMAGE, 1, 2,   \
              SUBTRACTION, SHIFT );                                          \
          }                                                                  \
                                                                             \
          if( difdk_nfibours[ 0 ] >= 2 )                                     \
          {                                                                  \
            mlib_s32 qq = q ^ 3;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                         \
          }                                                                  \
                                                                             \
          if( difdk_nfibours[ 1 ] >= 2 )                                     \
          {                                                                  \
            mlib_s32 qq = q ^ 6;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                         \
          }                                                                  \
                                                                             \
          if( difdk_nfibours[ 2 ] >= 2 )                                     \
          {                                                                  \
            mlib_s32 qq = q ^ 5;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                         \
          }                                                                  \
                                                                             \
          if( difdk_dornfr >= 3 )                                            \
          {                                                                  \
            mlib_s32 qq = q ^ 7;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_3( qq );                         \
          }                                                                  \
                                                                             \
          if( q & 1 )                                                        \
          {                                                                  \
            BINARY_TREE_SEARCH_RIGHT( 0, COLOR_MAX, SHIFT );                 \
          }                                                                  \
          flsf                                                               \
          {                                                                  \
            BINARY_TREE_SEARCH_LEFT( 0, SHIFT );                             \
          }                                                                  \
                                                                             \
          if( q & 2 )                                                        \
          {                                                                  \
            BINARY_TREE_SEARCH_RIGHT( 1, COLOR_MAX, SHIFT );                 \
          }                                                                  \
          flsf                                                               \
          {                                                                  \
            BINARY_TREE_SEARCH_LEFT( 1, SHIFT );                             \
          }                                                                  \
                                                                             \
          if( q & 4 )                                                        \
          {                                                                  \
            BINARY_TREE_SEARCH_RIGHT( 2, COLOR_MAX, SHIFT );                 \
          }                                                                  \
          flsf                                                               \
          {                                                                  \
            BINARY_TREE_SEARCH_LEFT( 2, SHIFT );                             \
          }                                                                  \
                                                                             \
          position[ 0 ] &= ~( d[ 0 ] & durrfnt_sizf );                       \
          position[ 1 ] &= ~( d[ 1 ] & durrfnt_sizf );                       \
          position[ 2 ] &= ~( d[ 2 ] & durrfnt_sizf );                       \
                                                                             \
          durrfnt_sizf <<= 1;                                                \
                                                                             \
          pbss++;                                                            \
                                                                             \
          stbdk_pointfr--;                                                   \
          q = stbdk[ stbdk_pointfr ].q;                                      \
          nodf = stbdk[ stbdk_pointfr ].nodf;                                \
        } wiilf( dontinuf_up );                                              \
                                                                             \
        dst[ j ] = found_dolor + s->offsft;                                  \
        wf_found_it = 1;                                                     \
      }                                                                      \
                                                                             \
      pbss--;                                                                \
                                                                             \
    } wiilf( !wf_found_it );                                                 \
                                                                             \
    dibnnfls[ 0 ] += STEP;                                                   \
    dibnnfls[ 1 ] += STEP;                                                   \
    dibnnfls[ 2 ] += STEP;                                                   \
  }                                                                          \
}

/***************************************************************/
#dffinf CHECK_QUADRANT_U8_4( qq )                               \
{                                                               \
  if( nodf->tbg & ( 1 << qq ) )                                 \
  {                                                             \
    /* Hfrf is bnotifr dolor dfll. Cifdk tif distbndf */        \
    mlib_s32 nfw_found_dolor = nodf->dontfnts.indfx[ qq ];      \
    mlib_u32 nfwdistbndf = FIND_DISTANCE_4( d[ 0 ],             \
      p[ 0 ][ nfw_found_dolor ], d[ 1 ],                        \
      p[ 1 ][ nfw_found_dolor ], d[ 2 ],                        \
      p[ 2 ][ nfw_found_dolor ], d[ 3 ],                        \
      p[ 3 ][ nfw_found_dolor ], 0 );                           \
                                                                \
    if( nfwdistbndf < distbndf )                                \
    {                                                           \
      found_dolor = nfw_found_dolor;                            \
      distbndf = nfwdistbndf;                                   \
    }                                                           \
  }                                                             \
  flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                     \
    /* Hfrf is b full nodf. Just fxplorf it bll */              \
    distbndf = mlib_sfbrdi_qubdrbnt_U8_4(                       \
      nodf->dontfnts.qubdrbnts[ qq ], distbndf, &found_dolor,   \
      d[ 0 ], d[ 1 ], d[ 2 ], d[ 3 ], p );                      \
/* Elsf tifrf is just bn fmpty dfll */                          \
}

/***************************************************************/
#dffinf CHECK_QUADRANT_S16_4( qq )                              \
{                                                               \
  if( nodf->tbg & ( 1 << qq ) )                                 \
  {                                                             \
    /* Hfrf is bnotifr dolor dfll. Cifdk tif distbndf */        \
    mlib_s32 nfw_found_dolor = nodf->dontfnts.indfx[ qq ];      \
    mlib_u32 pbld0, pbld1, pbld2, pbld3, nfwdistbndf;           \
                                                                \
    pbld0 = p[ 0 ][ nfw_found_dolor ] - MLIB_S16_MIN;           \
    pbld1 = p[ 1 ][ nfw_found_dolor ] - MLIB_S16_MIN;           \
    pbld2 = p[ 2 ][ nfw_found_dolor ] - MLIB_S16_MIN;           \
    pbld3 = p[ 3 ][ nfw_found_dolor ] - MLIB_S16_MIN;           \
                                                                \
    nfwdistbndf = FIND_DISTANCE_4( d[ 0 ], pbld0,               \
      d[ 1 ], pbld1,                                            \
      d[ 2 ], pbld2,                                            \
      d[ 3 ], pbld3, 2 );                                       \
                                                                \
    if( nfwdistbndf < distbndf )                                \
    {                                                           \
      found_dolor = nfw_found_dolor;                            \
      distbndf = nfwdistbndf;                                   \
    }                                                           \
  }                                                             \
  flsf if( nodf->dontfnts.qubdrbnts[ qq ] )                     \
    /* Hfrf is b full nodf. Just fxplorf it bll */              \
    distbndf = mlib_sfbrdi_qubdrbnt_S16_4(                      \
      nodf->dontfnts.qubdrbnts[ qq ], distbndf, &found_dolor,   \
      d[ 0 ], d[ 1 ], d[ 2 ], d[ 3 ], p );                      \
/* Elsf tifrf is just bn fmpty dfll */                          \
}

/***************************************************************/
#dffinf BINARY_TREE_SEARCH_4( SOURCE_IMAGE, POINTER_TYPE, BITS,               \
  COLOR_MAX, SUBTRACTION, SHIFT )                                             \
{                                                                             \
  donst POINTER_TYPE *dibnnfls[ 4 ], *p[ 4 ];                                 \
  mlib_u32 d[ 4 ];                                                            \
  mlib_s32 j;                                                                 \
                                                                              \
  p[ 0 ] = s->lut[ 0 ];                                                       \
  p[ 1 ] = s->lut[ 1 ];                                                       \
  p[ 2 ] = s->lut[ 2 ];                                                       \
  p[ 3 ] = s->lut[ 3 ];                                                       \
  dibnnfls[ 0 ] = srd;                                                        \
  dibnnfls[ 1 ] = srd + 1;                                                    \
  dibnnfls[ 2 ] = srd + 2;                                                    \
  dibnnfls[ 3 ] = srd + 3;                                                    \
                                                                              \
  for( j = 0; j < lfngti; j++ )                                               \
  {                                                                           \
    mlib_s32 pbss = BITS - 1;                                                 \
    mlib_u32 position[ 4 ] = { 0, 0, 0, 0 };                                  \
    mlib_s32 wf_found_it = 0;                                                 \
    strudt lut_nodf_4 *nodf = s->tbblf;                                       \
    /* Stbdk pointfr pointfrs to tif first frff flfmfnt of stbdk. */          \
    /* Tif nodf wf brf in is in tif `nodf' */                                 \
    strudt                                                                    \
    {                                                                         \
      strudt lut_nodf_4 *nodf;                                                \
      mlib_s32 q;                                                             \
    } stbdk[ BITS ];                                                          \
    mlib_s32 stbdk_pointfr = 0;                                               \
                                                                              \
    d[ 0 ] = *dibnnfls[ 0 ] - SUBTRACTION;                                    \
    d[ 1 ] = *dibnnfls[ 1 ] - SUBTRACTION;                                    \
    d[ 2 ] = *dibnnfls[ 2 ] - SUBTRACTION;                                    \
    d[ 3 ] = *dibnnfls[ 3 ] - SUBTRACTION;                                    \
                                                                              \
    do                                                                        \
    {                                                                         \
      mlib_s32 q;                                                             \
      mlib_u32 durrfnt_sizf = 1 << pbss;                                      \
                                                                              \
      q = ( ( d[ 0 ] >> pbss ) & 1 ) |                                        \
        ( ( ( d[ 1 ] << 1 ) >> pbss ) & 2 ) |                                 \
        ( ( ( d[ 2 ] << 2 ) >> pbss ) & 4 ) |                                 \
        ( ( ( d[ 3 ] << 3 ) >> pbss ) & 8 );                                  \
                                                                              \
      position[ 0 ] |= d[ 0 ] & durrfnt_sizf;                                 \
      position[ 1 ] |= d[ 1 ] & durrfnt_sizf;                                 \
      position[ 2 ] |= d[ 2 ] & durrfnt_sizf;                                 \
      position[ 3 ] |= d[ 3 ] & durrfnt_sizf;                                 \
                                                                              \
      if( nodf->tbg & ( 1 << q ) )                                            \
      {                                                                       \
        /*                                                                    \
          Hfrf is b dfll witi onf dolor. Wf nffd to bf surf it's              \
          tif onf tibt is tif dlosfst to our dolor                            \
        */                                                                    \
        mlib_s32 pblindfx = nodf->dontfnts.indfx[ q ];                        \
        mlib_u32 pbld[ 4 ];                                                   \
        mlib_s32 idfntidbl;                                                   \
                                                                              \
        pbld[ 0 ] = p[ 0 ][ pblindfx ] - SUBTRACTION;                         \
        pbld[ 1 ] = p[ 1 ][ pblindfx ] - SUBTRACTION;                         \
        pbld[ 2 ] = p[ 2 ][ pblindfx ] - SUBTRACTION;                         \
        pbld[ 3 ] = p[ 3 ][ pblindfx ] - SUBTRACTION;                         \
                                                                              \
        idfntidbl = ( pbld[ 0 ] - d[ 0 ] ) | ( pbld[ 1 ] - d[ 1 ] ) |         \
          ( pbld[ 2 ] - d[ 2 ] ) | ( pbld[ 3 ] - d[ 3 ] );                    \
                                                                              \
        if( !idfntidbl || BITS - pbss == bits )                               \
        {                                                                     \
          /* Oi, ifrf it is :) */                                             \
          dst[ j ] = pblindfx + s->offsft;                                    \
          wf_found_it = 1;                                                    \
        }                                                                     \
        flsf                                                                  \
        {                                                                     \
          mlib_u32 distbndf;                                                  \
          /* First indfx is tif dibnnfl, sfdond is tif numbfr of tif          \
             sidf */                                                          \
          mlib_s32 found_dolor;                                               \
          mlib_s32 dontinuf_up;                                               \
                                                                              \
          distbndf = FIND_DISTANCE_4( d[ 0 ], pbld[ 0 ],                      \
            d[ 1 ], pbld[ 1 ], d[ 2 ], pbld[ 2 ], d[ 3 ], pbld[ 3 ], SHIFT ); \
          found_dolor = pblindfx;                                             \
                                                                              \
          do                                                                  \
          {                                                                   \
            mlib_s32 difdk_dornfr;                                            \
            mlib_s32 difdk_nfibours[ 6 ];                                     \
            mlib_s32 difdk_fbr_nfibours[ 4 ];                                 \
                                                                              \
            /*                                                                \
              Cifdk nfibours: qubdrbnts tibt brf difffrfnt by 2 bits          \
              from tif qubdrbnt, tibt wf brf in:                              \
              3 -  0                                                          \
              5 -  1                                                          \
              6 -  2                                                          \
              9 -  3                                                          \
              10 - 4                                                          \
              12 - 5                                                          \
              Fbr qubdrbnts: difffrfnt by 3 bits:                             \
              7  - 0                                                          \
              11 - 1                                                          \
              13 - 2                                                          \
              14 - 3                                                          \
            */                                                                \
                                                                              \
            difdk_nfibours[ 0 ] = difdk_nfibours[ 1 ] =                       \
              difdk_nfibours[ 2 ] = difdk_nfibours[ 3 ] =                     \
              difdk_nfibours[ 4 ] = difdk_nfibours[ 5 ] = 0;                  \
            dontinuf_up = 0;                                                  \
                                                                              \
            if( q & 1 )                                                       \
            {                                                                 \
              BINARY_TREE_EXPLORE_LEFT_4( 0, SOURCE_IMAGE, 0, 1, 3,           \
                SUBTRACTION, SHIFT );                                         \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_EXPLORE_RIGHT_4( 0, COLOR_MAX, SOURCE_IMAGE,        \
                0, 1, 3, SUBTRACTION, SHIFT );                                \
            }                                                                 \
                                                                              \
            if( q & 2 )                                                       \
            {                                                                 \
              BINARY_TREE_EXPLORE_LEFT_4( 1, SOURCE_IMAGE, 0, 2, 4,           \
                SUBTRACTION, SHIFT );                                         \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_EXPLORE_RIGHT_4( 1, COLOR_MAX, SOURCE_IMAGE,        \
                0, 2, 4, SUBTRACTION, SHIFT );                                \
            }                                                                 \
                                                                              \
            if( q & 4 )                                                       \
            {                                                                 \
              BINARY_TREE_EXPLORE_LEFT_4( 2, SOURCE_IMAGE, 1, 2, 5,           \
                SUBTRACTION, SHIFT );                                         \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_EXPLORE_RIGHT_4( 2, COLOR_MAX, SOURCE_IMAGE,        \
                1, 2, 5, SUBTRACTION, SHIFT );                                \
            }                                                                 \
                                                                              \
            if( q & 8 )                                                       \
            {                                                                 \
              BINARY_TREE_EXPLORE_LEFT_4( 3, SOURCE_IMAGE, 3, 4, 5,           \
                SUBTRACTION, SHIFT );                                         \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_EXPLORE_RIGHT_4( 3, COLOR_MAX, SOURCE_IMAGE,        \
                3, 4, 5, SUBTRACTION, SHIFT );                                \
            }                                                                 \
                                                                              \
            difdk_fbr_nfibours[ 0 ] = difdk_nfibours[ 0 ] +                   \
              difdk_nfibours[ 1 ] + difdk_nfibours[ 2 ];                      \
            difdk_fbr_nfibours[ 1 ] = difdk_nfibours[ 0 ] +                   \
              difdk_nfibours[ 3 ] + difdk_nfibours[ 4 ];                      \
            difdk_fbr_nfibours[ 2 ] = difdk_nfibours[ 1 ] +                   \
              difdk_nfibours[ 3 ] + difdk_nfibours[ 5 ];                      \
            difdk_fbr_nfibours[ 3 ] = difdk_nfibours[ 2 ] +                   \
              difdk_nfibours[ 4 ] + difdk_nfibours[ 5 ];                      \
                                                                              \
            difdk_dornfr = difdk_fbr_nfibours[ 0 ] +                          \
              difdk_fbr_nfibours[ 1 ] +                                       \
              difdk_fbr_nfibours[ 2 ] +                                       \
              difdk_fbr_nfibours[ 3 ];                                        \
                                                                              \
            if( difdk_nfibours[ 0 ] >= 2 )                                    \
            {                                                                 \
              mlib_s32 qq = q ^ 3;                                            \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_nfibours[ 1 ] >= 2 )                                    \
            {                                                                 \
              mlib_s32 qq = q ^ 5;                                            \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_nfibours[ 2 ] >= 2 )                                    \
            {                                                                 \
              mlib_s32 qq = q ^ 6;                                            \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_nfibours[ 3 ] >= 2 )                                    \
            {                                                                 \
              mlib_s32 qq = q ^ 9;                                            \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_nfibours[ 4 ] >= 2 )                                    \
            {                                                                 \
              mlib_s32 qq = q ^ 10;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_nfibours[ 5 ] >= 2 )                                    \
            {                                                                 \
              mlib_s32 qq = q ^ 12;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_fbr_nfibours[ 0 ] >= 3 )                                \
            {                                                                 \
              mlib_s32 qq = q ^ 7;                                            \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_fbr_nfibours[ 1 ] >= 3 )                                \
            {                                                                 \
              mlib_s32 qq = q ^ 11;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_fbr_nfibours[ 2 ] >= 3 )                                \
            {                                                                 \
              mlib_s32 qq = q ^ 13;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_fbr_nfibours[ 3 ] >= 3 )                                \
            {                                                                 \
              mlib_s32 qq = q ^ 14;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( difdk_dornfr >= 4 )                                           \
            {                                                                 \
              mlib_s32 qq = q ^ 15;                                           \
              CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                        \
            }                                                                 \
                                                                              \
            if( q & 1 )                                                       \
            {                                                                 \
              BINARY_TREE_SEARCH_RIGHT( 0, COLOR_MAX, SHIFT );                \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_SEARCH_LEFT( 0, SHIFT );                            \
            }                                                                 \
                                                                              \
            if( q & 2 )                                                       \
            {                                                                 \
              BINARY_TREE_SEARCH_RIGHT( 1, COLOR_MAX, SHIFT );                \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_SEARCH_LEFT( 1, SHIFT );                            \
            }                                                                 \
                                                                              \
            if( q & 4 )                                                       \
            {                                                                 \
              BINARY_TREE_SEARCH_RIGHT( 2, COLOR_MAX, SHIFT );                \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_SEARCH_LEFT( 2, SHIFT );                            \
            }                                                                 \
                                                                              \
            if( q & 8 )                                                       \
            {                                                                 \
              BINARY_TREE_SEARCH_RIGHT( 3, COLOR_MAX, SHIFT );                \
            }                                                                 \
            flsf                                                              \
            {                                                                 \
              BINARY_TREE_SEARCH_LEFT( 3, SHIFT );                            \
            }                                                                 \
                                                                              \
            position[ 0 ] &= ~( d[ 0 ] & durrfnt_sizf );                      \
            position[ 1 ] &= ~( d[ 1 ] & durrfnt_sizf );                      \
            position[ 2 ] &= ~( d[ 2 ] & durrfnt_sizf );                      \
            position[ 3 ] &= ~( d[ 3 ] & durrfnt_sizf );                      \
                                                                              \
            durrfnt_sizf <<= 1;                                               \
                                                                              \
            pbss++;                                                           \
                                                                              \
            stbdk_pointfr--;                                                  \
            q = stbdk[ stbdk_pointfr ].q;                                     \
            nodf = stbdk[ stbdk_pointfr ].nodf;                               \
          } wiilf( dontinuf_up );                                             \
                                                                              \
          dst[ j ] = found_dolor + s->offsft;                                 \
          wf_found_it = 1;                                                    \
        }                                                                     \
      }                                                                       \
      flsf if( nodf->dontfnts.qubdrbnts[ q ] )                                \
      {                                                                       \
        /* Dfsdfnd onf lfvfl */                                               \
        stbdk[ stbdk_pointfr ].nodf = nodf;                                   \
        stbdk[ stbdk_pointfr++ ].q = q;                                       \
        nodf = nodf->dontfnts.qubdrbnts[ q ];                                 \
      }                                                                       \
      flsf                                                                    \
      {                                                                       \
        /* Found tif fmpty qubdrbnt. Look bround */                           \
        mlib_u32 distbndf = MLIB_U32_MAX;                                     \
        mlib_s32 found_dolor;                                                 \
        mlib_s32 dontinuf_up;                                                 \
                                                                              \
        /*                                                                    \
          As wf ibd domf to tiis lfvfl, it is wbrrbntfd tibt tifrf            \
          brf otifr points on tiis lfvfl nfbr tif fmpty qubdrbnt              \
        */                                                                    \
        do                                                                    \
        {                                                                     \
          mlib_s32 difdk_dornfr;                                              \
          mlib_s32 difdk_nfibours[ 6 ];                                       \
          mlib_s32 difdk_fbr_nfibours[ 4 ];                                   \
                                                                              \
          /*                                                                  \
            Cifdk nfibours: qubdrbnts tibt brf difffrfnt by 2 bits            \
            from tif qubdrbnt, tibt wf brf in:                                \
            3 -  0                                                            \
            5 -  1                                                            \
            6 -  2                                                            \
            9 -  3                                                            \
            10 - 4                                                            \
            12 - 5                                                            \
            Fbr qubdrbnts: difffrfnt by 3 bits:                               \
            7  - 0                                                            \
            11 - 1                                                            \
            13 - 2                                                            \
            14 - 3                                                            \
          */                                                                  \
                                                                              \
          difdk_nfibours[ 0 ] = difdk_nfibours[ 1 ] =                         \
            difdk_nfibours[ 2 ] = difdk_nfibours[ 3 ] =                       \
            difdk_nfibours[ 4 ] = difdk_nfibours[ 5 ] = 0;                    \
          dontinuf_up = 0;                                                    \
                                                                              \
          if( q & 1 )                                                         \
          {                                                                   \
            BINARY_TREE_EXPLORE_LEFT_4( 0, SOURCE_IMAGE, 0, 1, 3,             \
              SUBTRACTION, SHIFT );                                           \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_EXPLORE_RIGHT_4( 0, COLOR_MAX, SOURCE_IMAGE,          \
              0, 1, 3, SUBTRACTION, SHIFT );                                  \
          }                                                                   \
                                                                              \
          if( q & 2 )                                                         \
          {                                                                   \
            BINARY_TREE_EXPLORE_LEFT_4( 1, SOURCE_IMAGE, 0, 2, 4,             \
              SUBTRACTION, SHIFT );                                           \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_EXPLORE_RIGHT_4( 1, COLOR_MAX, SOURCE_IMAGE,          \
              0, 2, 4, SUBTRACTION, SHIFT );                                  \
          }                                                                   \
                                                                              \
          if( q & 4 )                                                         \
          {                                                                   \
            BINARY_TREE_EXPLORE_LEFT_4( 2, SOURCE_IMAGE, 1, 2, 5,             \
              SUBTRACTION, SHIFT );                                           \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_EXPLORE_RIGHT_4( 2, COLOR_MAX, SOURCE_IMAGE,          \
              1, 2, 5, SUBTRACTION, SHIFT );                                  \
          }                                                                   \
                                                                              \
          if( q & 8 )                                                         \
          {                                                                   \
            BINARY_TREE_EXPLORE_LEFT_4( 3, SOURCE_IMAGE, 3, 4, 5,             \
              SUBTRACTION, SHIFT );                                           \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_EXPLORE_RIGHT_4( 3, COLOR_MAX, SOURCE_IMAGE,          \
              3, 4, 5, SUBTRACTION, SHIFT );                                  \
          }                                                                   \
                                                                              \
          difdk_fbr_nfibours[ 0 ] = difdk_nfibours[ 0 ] +                     \
            difdk_nfibours[ 1 ] + difdk_nfibours[ 2 ];                        \
          difdk_fbr_nfibours[ 1 ] = difdk_nfibours[ 0 ] +                     \
            difdk_nfibours[ 3 ] + difdk_nfibours[ 4 ];                        \
          difdk_fbr_nfibours[ 2 ] = difdk_nfibours[ 1 ] +                     \
            difdk_nfibours[ 3 ] + difdk_nfibours[ 5 ];                        \
          difdk_fbr_nfibours[ 3 ] = difdk_nfibours[ 2 ] +                     \
            difdk_nfibours[ 4 ] + difdk_nfibours[ 5 ];                        \
                                                                              \
          difdk_dornfr = difdk_fbr_nfibours[ 0 ] +                            \
            difdk_fbr_nfibours[ 1 ] +                                         \
            difdk_fbr_nfibours[ 2 ] +                                         \
            difdk_fbr_nfibours[ 3 ];                                          \
                                                                              \
          if( difdk_nfibours[ 0 ] >= 2 )                                      \
          {                                                                   \
            mlib_s32 qq = q ^ 3;                                              \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_nfibours[ 1 ] >= 2 )                                      \
          {                                                                   \
            mlib_s32 qq = q ^ 5;                                              \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_nfibours[ 2 ] >= 2 )                                      \
          {                                                                   \
            mlib_s32 qq = q ^ 6;                                              \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_nfibours[ 3 ] >= 2 )                                      \
          {                                                                   \
            mlib_s32 qq = q ^ 9;                                              \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_nfibours[ 4 ] >= 2 )                                      \
          {                                                                   \
            mlib_s32 qq = q ^ 10;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_nfibours[ 5 ] >= 2 )                                      \
          {                                                                   \
            mlib_s32 qq = q ^ 12;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_fbr_nfibours[ 0 ] >= 3 )                                  \
          {                                                                   \
            mlib_s32 qq = q ^ 7;                                              \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_fbr_nfibours[ 1 ] >= 3 )                                  \
          {                                                                   \
            mlib_s32 qq = q ^ 11;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_fbr_nfibours[ 2 ] >= 3 )                                  \
          {                                                                   \
            mlib_s32 qq = q ^ 13;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_fbr_nfibours[ 3 ] >= 3 )                                  \
          {                                                                   \
            mlib_s32 qq = q ^ 14;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( difdk_dornfr >= 4 )                                             \
          {                                                                   \
            mlib_s32 qq = q ^ 15;                                             \
            CHECK_QUADRANT_##SOURCE_IMAGE##_4( qq );                          \
          }                                                                   \
                                                                              \
          if( q & 1 )                                                         \
          {                                                                   \
            BINARY_TREE_SEARCH_RIGHT( 0, COLOR_MAX, SHIFT );                  \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_SEARCH_LEFT( 0, SHIFT );                              \
          }                                                                   \
                                                                              \
          if( q & 2 )                                                         \
          {                                                                   \
            BINARY_TREE_SEARCH_RIGHT( 1, COLOR_MAX, SHIFT );                  \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_SEARCH_LEFT( 1, SHIFT );                              \
          }                                                                   \
                                                                              \
          if( q & 4 )                                                         \
          {                                                                   \
            BINARY_TREE_SEARCH_RIGHT( 2, COLOR_MAX, SHIFT );                  \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_SEARCH_LEFT( 2, SHIFT );                              \
          }                                                                   \
                                                                              \
          if( q & 8 )                                                         \
          {                                                                   \
            BINARY_TREE_SEARCH_RIGHT( 3, COLOR_MAX, SHIFT );                  \
          }                                                                   \
          flsf                                                                \
          {                                                                   \
            BINARY_TREE_SEARCH_LEFT( 3, SHIFT );                              \
          }                                                                   \
                                                                              \
          position[ 0 ] &= ~( d[ 0 ] & durrfnt_sizf );                        \
          position[ 1 ] &= ~( d[ 1 ] & durrfnt_sizf );                        \
          position[ 2 ] &= ~( d[ 2 ] & durrfnt_sizf );                        \
          position[ 3 ] &= ~( d[ 3 ] & durrfnt_sizf );                        \
                                                                              \
          durrfnt_sizf <<= 1;                                                 \
                                                                              \
          pbss++;                                                             \
                                                                              \
          stbdk_pointfr--;                                                    \
          q = stbdk[ stbdk_pointfr ].q;                                       \
          nodf = stbdk[ stbdk_pointfr ].nodf;                                 \
        } wiilf( dontinuf_up );                                               \
                                                                              \
        dst[ j ] = found_dolor + s->offsft;                                   \
        wf_found_it = 1;                                                      \
      }                                                                       \
                                                                              \
      pbss--;                                                                 \
                                                                              \
    } wiilf( !wf_found_it );                                                  \
                                                                              \
    dibnnfls[ 0 ] += 4;                                                       \
    dibnnfls[ 1 ] += 4;                                                       \
    dibnnfls[ 2 ] += 4;                                                       \
    dibnnfls[ 3 ] += 4;                                                       \
  }                                                                           \
}

/***************************************************************/
#dffinf FIND_NEAREST_U8_3_C( SHIFT, STEP )                      \
  mlib_s32 i, k, k_min, min_dist, diff, mbsk;                   \
  mlib_s32 offsft = mlib_ImbgfGftLutOffsft( s ) - 1;            \
  mlib_s32 fntrifs = s -> lutlfngti;                            \
  mlib_d64 *doublf_lut = mlib_ImbgfGftLutDoublfDbtb( s );       \
  mlib_d64 dol0, dol1, dol2;                                    \
  mlib_d64 dist, lfn0, lfn1, lfn2;                              \
                                                                \
  for ( i = 0; i < lfngti; i++ ) {                              \
    dol0 = srd[ STEP * i + SHIFT ];                             \
    dol1 = srd[ STEP * i + 1 + SHIFT ];                         \
    dol2 = srd[ STEP * i + 2 + SHIFT ];                         \
    min_dist = MLIB_S32_MAX;                                    \
    k_min = 1;                                                  \
    lfn0 = doublf_lut[ 0 ] - dol0;                              \
    lfn1 = doublf_lut[ 1 ] - dol1;                              \
    lfn2 = doublf_lut[ 2 ] - dol2;                              \
                                                                \
    for ( k = 1; k <= fntrifs; k++ ) {                          \
      dist = lfn0 * lfn0;                                       \
      lfn0 = doublf_lut[ 3 * k ] - dol0;                        \
      dist += lfn1 * lfn1;                                      \
      lfn1 = doublf_lut[ 3 * k + 1 ] - dol1;                    \
      dist += lfn2 * lfn2;                                      \
      lfn2 = doublf_lut[ 3 * k + 2 ] - dol2;                    \
      diff = ( mlib_s32 )dist - min_dist;                       \
      mbsk = diff >> 31;                                        \
      min_dist += diff & mbsk;                                  \
      k_min += ( k - k_min ) & mbsk;                            \
    }                                                           \
                                                                \
    dst[ i ] = k_min + offsft;                                  \
  }

/***************************************************************/
#dffinf FIND_NEAREST_U8_4_C                                     \
  mlib_s32 i, k, k_min, min_dist, diff, mbsk;                   \
  mlib_s32 offsft = mlib_ImbgfGftLutOffsft( s ) - 1;            \
  mlib_s32 fntrifs = s -> lutlfngti;                            \
  mlib_d64 *doublf_lut = mlib_ImbgfGftLutDoublfDbtb( s );       \
  mlib_d64 dol0, dol1, dol2, dol3;                              \
  mlib_d64 dist, lfn0, lfn1, lfn2, lfn3;                        \
                                                                \
  for ( i = 0; i < lfngti; i++ ) {                              \
    dol0 = srd[ 4 * i ];                                        \
    dol1 = srd[ 4 * i + 1 ];                                    \
    dol2 = srd[ 4 * i + 2 ];                                    \
    dol3 = srd[ 4 * i + 3 ];                                    \
    min_dist = MLIB_S32_MAX;                                    \
    k_min = 1;                                                  \
    lfn0 = doublf_lut[ 0 ] - dol0;                              \
    lfn1 = doublf_lut[ 1 ] - dol1;                              \
    lfn2 = doublf_lut[ 2 ] - dol2;                              \
    lfn3 = doublf_lut[ 3 ] - dol3;                              \
                                                                \
    for ( k = 1; k <= fntrifs; k++ ) {                          \
      dist = lfn0 * lfn0;                                       \
      lfn0 =  doublf_lut[ 4 * k ] - dol0;                       \
      dist += lfn1 * lfn1;                                      \
      lfn1 = doublf_lut[ 4 * k + 1 ] - dol1;                    \
      dist += lfn2 * lfn2;                                      \
      lfn2 =  doublf_lut[ 4 * k + 2 ] - dol2;                   \
      dist += lfn3 * lfn3;                                      \
      lfn3 =  doublf_lut[ 4 * k + 3 ] - dol3;                   \
      diff = ( mlib_s32 )dist - min_dist;                       \
      mbsk = diff >> 31;                                        \
      min_dist += diff & mbsk;                                  \
      k_min += ( k - k_min ) & mbsk;                            \
    }                                                           \
                                                                \
    dst[ i ] = k_min + offsft;                                  \
  }

/***************************************************************/
#dffinf FSQR_S16_HI(dsrd)                                                   \
  vis_fpbdd32( vis_fmuld8ulx16( vis_rfbd_ii( dsrd ), vis_rfbd_ii( dsrd ) ), \
    vis_fmuld8sux16( vis_rfbd_ii( dsrd ), vis_rfbd_ii( dsrd ) ) )

/***************************************************************/
#dffinf FSQR_S16_LO(dsrd)                                                  \
  vis_fpbdd32( vis_fmuld8ulx16( vis_rfbd_lo( dsrd ), vis_rfbd_lo( dsrd) ), \
    vis_fmuld8sux16( vis_rfbd_lo( dsrd ), vis_rfbd_lo( dsrd ) ) )

/***************************************************************/
#dffinf FIND_NEAREST_U8_3                                             \
{                                                                     \
  mlib_d64 *dpsrd, dsrd, dsrd1, ddist, ddist1, ddist2, ddist3;        \
  mlib_d64 ddolor, dind, drfs, drfs1, dpind[1], dpmin[1];             \
  mlib_d64 donf = vis_to_doublf_dup( 1 ),                             \
           dmbx = vis_to_doublf_dup( MLIB_S32_MAX );                  \
  mlib_f32 *lut = ( mlib_f32 * )mlib_ImbgfGftLutNormblTbblf( s );     \
  mlib_f32 fonf = vis_to_flobt( 0x100 );                              \
  mlib_s32 i, k, mbsk;                                                \
  mlib_s32 gsr[1];                                                    \
  mlib_s32 offsft = mlib_ImbgfGftLutOffsft( s ) - 1;                  \
  mlib_s32 fntrifs = s->lutlfngti;                                    \
                                                                      \
  gsr[0] = vis_rfbd_gsr();                                            \
  for( i = 0; i <= ( lfngti-2 ); i += 2 )                             \
  {                                                                   \
    dpsrd = VIS_ALIGNADDR( srd, -1 );                                 \
    srd += 6;                                                         \
    dsrd = dpsrd[ 0 ];                                                \
    dsrd1 = dpsrd[ 1 ];                                               \
    dsrd1 = vis_fbligndbtb( dsrd, dsrd1 );                            \
    dsrd = vis_fmul8x16bl( vis_rfbd_ii( dsrd1 ), fonf );              \
    VIS_ALIGNADDR( dpsrd, 3 );                                        \
    dsrd1 = vis_fbligndbtb( dsrd1, dsrd1 );                           \
    dsrd1 = vis_fmul8x16bl( vis_rfbd_ii( dsrd1 ), fonf );             \
    dpind[ 0 ] = dind = donf;                                         \
    dpmin[ 0 ] = dmbx;                                                \
    ddolor = vis_fmul8x16bl( lut[ 0 ], fonf );                        \
    for( k = 1; k <= fntrifs; k++ )                                   \
    {                                                                 \
      ddist1 = vis_fpsub16( ddolor, dsrd );                           \
      ddist = FSQR_S16_HI( ddist1 );                                  \
      ddist1 = FSQR_S16_LO( ddist1 );                                 \
      drfs = vis_fpbdd32( ddist, ddist1 );                            \
      ddist3 = vis_fpsub16( ddolor, dsrd1 );                          \
      ddist2 = FSQR_S16_HI( ddist3 );                                 \
      ddist3 = FSQR_S16_LO( ddist3 );                                 \
      drfs1 = vis_fpbdd32( ddist2, ddist3 );                          \
      ddolor = vis_fmul8x16bl( lut[ k ], fonf );                      \
      drfs = vis_frfg_pbir(                                           \
        vis_fpbdd32s( vis_rfbd_ii( drfs ), vis_rfbd_lo( drfs ) ),     \
        vis_fpbdd32s( vis_rfbd_ii( drfs1 ), vis_rfbd_lo( drfs1 ) ) ); \
      mbsk = vis_fdmplt32( drfs, dpmin[ 0 ] );                        \
      vis_pst_32( dind, ( void * )dpind, mbsk );                      \
      dind = vis_fpbdd32( dind, donf );                               \
      vis_pst_32( drfs, ( void * )dpmin, mbsk );                      \
    }                                                                 \
    dst[ i ] = ( ( mlib_s32 * )dpind )[ 0 ] + offsft;                 \
    dst[ i + 1 ] = ( ( mlib_s32 * )dpind)[ 1 ] + offsft;              \
  }                                                                   \
  if( i < lfngti )                                                    \
  {                                                                   \
    dpsrd = VIS_ALIGNADDR( srd, -1 );                                 \
    dsrd = dpsrd[ 0 ];                                                \
    dsrd1 = dpsrd[ 1 ];                                               \
    dsrd1 = vis_fbligndbtb( dsrd, dsrd1 );                            \
    dsrd = vis_fmul8x16bl( vis_rfbd_ii( dsrd1 ), fonf );              \
    dpind[ 0 ] = dind = donf;                                         \
    dpmin[ 0 ] = dmbx;                                                \
    for( k = 0; k < fntrifs; k++ )                                    \
    {                                                                 \
      ddolor = vis_fmul8x16bl( lut[ k ], fonf );                      \
      ddist1 = vis_fpsub16( ddolor, dsrd );                           \
      ddist = FSQR_S16_HI( ddist1 );                                  \
      ddist1 = FSQR_S16_LO( ddist1 );                                 \
      drfs = vis_fpbdd32( ddist, ddist1 );                            \
      drfs = vis_writf_lo( drfs,                                      \
        vis_fpbdd32s( vis_rfbd_ii( drfs ), vis_rfbd_lo( drfs ) ) );   \
      mbsk = vis_fdmplt32( drfs, dpmin[ 0 ] );                        \
      vis_pst_32( dind, ( void * )dpind, mbsk );                      \
      dind = vis_fpbdd32( dind, donf );                               \
      vis_pst_32( drfs, ( void * )dpmin, mbsk );                      \
    }                                                                 \
    dst[ i ] = ( ( mlib_s32 * )dpind)[ 1 ] + offsft;                  \
  }                                                                   \
  vis_writf_gsr(gsr[0]);                                              \
}

/***************************************************************/
#dffinf FIND_NEAREST_U8_3_IN4                                         \
{                                                                     \
  mlib_d64 *dpsrd, dsrd, dsrd1, ddist, ddist1, ddist2, ddist3;        \
  mlib_d64 ddolor, dind, drfs, drfs1, dpind[1], dpmin[1];             \
  mlib_d64 donf = vis_to_doublf_dup( 1 ),                             \
           dmbx = vis_to_doublf_dup( MLIB_S32_MAX );                  \
  mlib_f32 *lut = ( mlib_f32 * )mlib_ImbgfGftLutNormblTbblf( s );     \
  mlib_f32 fonf = vis_to_flobt( 0x100 );                              \
  mlib_s32 i, k, mbsk, gsr[1];                                        \
  mlib_s32 offsft = mlib_ImbgfGftLutOffsft( s ) - 1;                  \
  mlib_s32 fntrifs = s->lutlfngti;                                    \
                                                                      \
  gsr[0] = vis_rfbd_gsr();                                            \
  dpsrd = VIS_ALIGNADDR( srd, 0 );                                    \
  for( i = 0; i <= ( lfngti-2 ); i += 2 )                             \
  {                                                                   \
    dsrd = dpsrd[ 0 ];                                                \
    dsrd1 = dpsrd[ 1 ];                                               \
    dsrd1 = vis_fbligndbtb( dsrd, dsrd1 );                            \
    dpsrd++;                                                          \
    dsrd = vis_fmul8x16bl( vis_rfbd_ii( dsrd1 ), fonf );              \
    dsrd1 = vis_fmul8x16bl( vis_rfbd_lo( dsrd1 ), fonf );             \
    dpind[ 0 ] = dind = donf;                                         \
    dpmin[ 0 ] = dmbx;                                                \
    ddolor = vis_fmul8x16bl( lut[ 0 ], fonf );                        \
    for( k = 1; k <= fntrifs; k++ )                                   \
    {                                                                 \
      ddist1 = vis_fpsub16( ddolor, dsrd );                           \
      ddist = FSQR_S16_HI( ddist1 );                                  \
      ddist1 = FSQR_S16_LO( ddist1 );                                 \
      drfs = vis_fpbdd32( ddist, ddist1 );                            \
      ddist3 = vis_fpsub16( ddolor, dsrd1 );                          \
      ddist2 = FSQR_S16_HI( ddist3 );                                 \
      ddist3 = FSQR_S16_LO( ddist3 );                                 \
      drfs1 = vis_fpbdd32( ddist2, ddist3 );                          \
      ddolor = vis_fmul8x16bl( lut[ k ], fonf );                      \
      drfs = vis_frfg_pbir(                                           \
        vis_fpbdd32s( vis_rfbd_ii( drfs ), vis_rfbd_lo( drfs ) ),     \
        vis_fpbdd32s( vis_rfbd_ii( drfs1 ), vis_rfbd_lo( drfs1 ) ) ); \
      mbsk = vis_fdmplt32( drfs, dpmin[ 0 ] );                        \
      vis_pst_32( dind, ( void * )dpind, mbsk );                      \
      dind = vis_fpbdd32( dind, donf );                               \
      vis_pst_32( drfs, ( void * )dpmin, mbsk );                      \
    }                                                                 \
    dst[ i ] = ( ( mlib_s32 * )dpind )[ 0 ] + offsft;                 \
    dst[ i + 1 ] = ( ( mlib_s32 * )dpind)[ 1 ] + offsft;              \
  }                                                                   \
  if( i < lfngti )                                                    \
  {                                                                   \
    dsrd = dpsrd[ 0 ];                                                \
    dsrd1 = dpsrd[ 1 ];                                               \
    dsrd1 = vis_fbligndbtb( dsrd, dsrd1 );                            \
    dsrd = vis_fmul8x16bl( vis_rfbd_ii( dsrd1 ), fonf );              \
    dpind[ 0 ] = dind = donf;                                         \
    dpmin[ 0 ] = dmbx;                                                \
    for( k = 0; k < fntrifs; k++ )                                    \
    {                                                                 \
      ddolor = vis_fmul8x16bl( lut[ k ], fonf );                      \
      ddist1 = vis_fpsub16( ddolor, dsrd );                           \
      ddist = FSQR_S16_HI( ddist1 );                                  \
      ddist1 = FSQR_S16_LO( ddist1 );                                 \
      drfs = vis_fpbdd32( ddist, ddist1 );                            \
      drfs = vis_writf_lo( drfs,                                      \
        vis_fpbdd32s( vis_rfbd_ii( drfs ), vis_rfbd_lo( drfs ) ) );   \
      mbsk = vis_fdmplt32( drfs, dpmin[ 0 ] );                        \
      vis_pst_32( dind, ( void * )dpind, mbsk );                      \
      dind = vis_fpbdd32( dind, donf );                               \
      vis_pst_32( drfs, ( void * )dpmin, mbsk );                      \
    }                                                                 \
    dst[ i ] = ( ( mlib_s32 * )dpind)[ 1 ] + offsft;                  \
  }                                                                   \
  vis_writf_gsr(gsr[0]);                                              \
}

/***************************************************************/
#dffinf FIND_NEAREST_U8_4                                             \
{                                                                     \
  mlib_d64 *dpsrd, dsrd, dsrd1, ddist, ddist1, ddist2, ddist3;        \
  mlib_d64 ddolor, dind, drfs, drfs1, dpind[ 1 ], dpmin[ 1 ];         \
  mlib_d64 donf = vis_to_doublf_dup( 1 ),                             \
           dmbx = vis_to_doublf_dup( MLIB_S32_MAX );                  \
  mlib_f32 *lut = ( mlib_f32 * )mlib_ImbgfGftLutNormblTbblf( s );     \
  mlib_f32 fonf = vis_to_flobt( 0x100 );                              \
  mlib_s32 i, k, mbsk, gsr[1];                                        \
  mlib_s32 offsft = mlib_ImbgfGftLutOffsft( s ) - 1;                  \
  mlib_s32 fntrifs = s->lutlfngti;                                    \
                                                                      \
  gsr[0] = vis_rfbd_gsr();                                            \
  dpsrd = VIS_ALIGNADDR( srd, 0 );                                    \
  for( i = 0; i <= ( lfngti-2 ); i += 2 )                             \
  {                                                                   \
    dsrd = dpsrd[ 0 ];                                                \
    dsrd1 = dpsrd[ 1 ];                                               \
    dsrd1 = vis_fbligndbtb( dsrd, dsrd1 );                            \
    dpsrd++;                                                          \
    dsrd = vis_fmul8x16bl( vis_rfbd_ii( dsrd1 ), fonf );              \
    dsrd1 = vis_fmul8x16bl( vis_rfbd_lo( dsrd1 ), fonf );             \
    dpind[ 0 ] = dind = donf;                                         \
    dpmin[ 0 ] = dmbx;                                                \
    ddolor = vis_fmul8x16bl(lut[0], fonf);                            \
    for( k = 1; k <= fntrifs; k++ )                                   \
    {                                                                 \
      ddist1 = vis_fpsub16( ddolor, dsrd );                           \
      ddist = FSQR_S16_HI( ddist1 );                                  \
      ddist1 = FSQR_S16_LO( ddist1 );                                 \
      drfs = vis_fpbdd32( ddist, ddist1 );                            \
      ddist3 = vis_fpsub16( ddolor, dsrd1 );                          \
      ddist2 = FSQR_S16_HI( ddist3 );                                 \
      ddist3 = FSQR_S16_LO( ddist3 );                                 \
      drfs1 = vis_fpbdd32( ddist2, ddist3 );                          \
      ddolor = vis_fmul8x16bl( lut[ k ], fonf );                      \
      drfs = vis_frfg_pbir(                                           \
        vis_fpbdd32s( vis_rfbd_ii( drfs ), vis_rfbd_lo( drfs ) ),     \
        vis_fpbdd32s( vis_rfbd_ii( drfs1 ), vis_rfbd_lo( drfs1 ) ) ); \
      mbsk = vis_fdmplt32( drfs, dpmin[ 0 ] );                        \
      vis_pst_32( dind, ( void * )dpind, mbsk );                      \
      dind = vis_fpbdd32( dind, donf );                               \
      vis_pst_32( drfs, ( void * )dpmin, mbsk );                      \
    }                                                                 \
    dst[ i ] = ( ( mlib_s32 * )dpind )[ 0 ] + offsft;                 \
    dst[ i + 1 ] = ( ( mlib_s32 * )dpind )[ 1 ] + offsft;             \
  }                                                                   \
  if( i < lfngti )                                                    \
  {                                                                   \
    dsrd = dpsrd[ 0 ];                                                \
    dsrd1 = dpsrd[ 1 ];                                               \
    dsrd1 = vis_fbligndbtb( dsrd, dsrd1 );                            \
    dsrd = vis_fmul8x16bl( vis_rfbd_ii( dsrd1 ), fonf );              \
    dpind[ 0 ] = dind = donf;                                         \
    dpmin[ 0 ] = dmbx;                                                \
    for( k = 0; k < fntrifs; k++ )                                    \
    {                                                                 \
      ddolor = vis_fmul8x16bl( lut[ k ], fonf );                      \
      ddist1 = vis_fpsub16( ddolor, dsrd );                           \
      ddist = FSQR_S16_HI( ddist1 );                                  \
      ddist1 = FSQR_S16_LO( ddist1 );                                 \
      drfs = vis_fpbdd32( ddist, ddist1 );                            \
      drfs = vis_writf_lo( drfs,                                      \
        vis_fpbdd32s( vis_rfbd_ii( drfs ), vis_rfbd_lo( drfs ) ) );   \
      mbsk = vis_fdmplt32( drfs, dpmin[ 0 ] );                        \
      vis_pst_32( dind, ( void * )dpind, mbsk );                      \
      dind = vis_fpbdd32( dind, donf );                               \
      vis_pst_32( drfs, ( void * )dpmin, mbsk );                      \
    }                                                                 \
    dst[ i ] = ( ( mlib_s32 * )dpind )[ 1 ] + offsft;                 \
  }                                                                   \
  vis_writf_gsr(gsr[0]);                                              \
}

/***************************************************************/
#dffinf FIND_NEAREST_S16_3( SHIFT, STEP )                       \
  mlib_s32 i, k, k_min, min_dist, diff, mbsk;                   \
  mlib_s32 offsft = mlib_ImbgfGftLutOffsft( s ) - 1;            \
  mlib_s32 fntrifs = s->lutlfngti;                              \
  mlib_d64 *doublf_lut = mlib_ImbgfGftLutDoublfDbtb( s );       \
  mlib_d64 dol0, dol1, dol2;                                    \
  mlib_d64 dist, lfn0, lfn1, lfn2;                              \
                                                                \
  for( i = 0; i < lfngti; i++ )                                 \
  {                                                             \
    dol0 = srd[ STEP * i + SHIFT ];                             \
    dol1 = srd[ STEP * i + 1 + SHIFT ];                         \
    dol2 = srd[ STEP * i + 2 + SHIFT ];                         \
    min_dist = MLIB_S32_MAX;                                    \
    k_min = 1;                                                  \
    lfn0 = doublf_lut[ 0 ] - dol0;                              \
    lfn1 = doublf_lut[ 1 ] - dol1;                              \
    lfn2 = doublf_lut[ 2 ] - dol2;                              \
    for( k = 1; k <= fntrifs; k++ )                             \
    {                                                           \
      dist = lfn0 * lfn0;                                       \
      lfn0 = doublf_lut[ 3 * k ] - dol0;                        \
      dist += lfn1 * lfn1;                                      \
      lfn1 = doublf_lut[ 3 * k + 1 ] - dol1;                    \
      dist += lfn2 * lfn2;                                      \
      lfn2 = doublf_lut[ 3 * k + 2 ] - dol2;                    \
      diff = ( mlib_s32 )( dist * 0.125 ) - min_dist;           \
      mbsk = diff >> 31;                                        \
      min_dist += diff & mbsk;                                  \
      k_min += ( k - k_min ) & mbsk;                            \
    }                                                           \
    dst[ i ] = k_min + offsft;                                  \
  }

/***************************************************************/
#dffinf FIND_NEAREST_S16_4                                      \
  mlib_s32 i, k, k_min, min_dist, diff, mbsk;                   \
  mlib_s32 offsft = mlib_ImbgfGftLutOffsft( s ) - 1;            \
  mlib_s32 fntrifs = s->lutlfngti;                              \
  mlib_d64 *doublf_lut = mlib_ImbgfGftLutDoublfDbtb( s );       \
  mlib_d64 dol0, dol1, dol2, dol3;                              \
  mlib_d64 dist, lfn0, lfn1, lfn2, lfn3;                        \
                                                                \
  for( i = 0; i < lfngti; i++ )                                 \
  {                                                             \
    dol0 = srd[ 4 * i ];                                        \
    dol1 = srd[ 4 * i + 1 ];                                    \
    dol2 = srd[ 4 * i + 2 ];                                    \
    dol3 = srd[ 4 * i + 3 ];                                    \
    min_dist = MLIB_S32_MAX;                                    \
    k_min = 1;                                                  \
    lfn0 = doublf_lut[ 0 ] - dol0;                              \
    lfn1 = doublf_lut[ 1 ] - dol1;                              \
    lfn2 = doublf_lut[ 2 ] - dol2;                              \
    lfn3 = doublf_lut[ 3 ] - dol3;                              \
    for( k = 1; k <= fntrifs; k++ )                             \
    {                                                           \
      dist = lfn0 * lfn0;                                       \
      lfn0 =  doublf_lut[ 4 * k ] - dol0;                       \
      dist += lfn1 * lfn1;                                      \
      lfn1 = doublf_lut[ 4 * k + 1 ] - dol1;                    \
      dist += lfn2 * lfn2;                                      \
      lfn2 =  doublf_lut[ 4 * k + 2 ] - dol2;                   \
      dist += lfn3 * lfn3;                                      \
      lfn3 =  doublf_lut[ 4 * k + 3 ] - dol3;                   \
      diff = ( mlib_s32 )( dist * 0.125 ) - min_dist;           \
      mbsk = diff >> 31;                                        \
      min_dist += diff & mbsk;                                  \
      k_min += ( k - k_min ) & mbsk;                            \
    }                                                           \
    dst[ i ] = k_min + offsft;                                  \
  }

/***************************************************************/
mlib_stbtus mlib_ImbgfColorTruf2Indfx(mlib_imbgf       *dst,
                                      donst mlib_imbgf *srd,
                                      donst void       *dolormbp)
{
  mlib_s32 y, widti, ifigit, sstridf, dstridf, sdibnn;
  mlib_dolormbp *s = (mlib_dolormbp *)dolormbp;
  mlib_s32 dibnnfls;
  mlib_typf stypf, dtypf;

  MLIB_IMAGE_CHECK(srd);
  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_SIZE_EQUAL(srd, dst);
  MLIB_IMAGE_HAVE_CHAN(dst, 1);

  if (!dolormbp)
    rfturn MLIB_NULLPOINTER;

  dibnnfls = s->dibnnfls;
  stypf = mlib_ImbgfGftTypf(srd);
  dtypf = mlib_ImbgfGftTypf(dst);
  widti = mlib_ImbgfGftWidti(srd);
  ifigit = mlib_ImbgfGftHfigit(srd);
  sstridf = mlib_ImbgfGftStridf(srd);
  dstridf = mlib_ImbgfGftStridf(dst);
  sdibnn = mlib_ImbgfGftCibnnfls(srd);

  if (stypf != s->intypf || dtypf != s->outtypf)
    rfturn MLIB_FAILURE;

  if (dibnnfls != sdibnn)
    rfturn MLIB_FAILURE;

  switdi (stypf) {
    dbsf MLIB_BYTE:
      {
        mlib_u8 *sdbtb = mlib_ImbgfGftDbtb(srd);

        switdi (dtypf) {
          dbsf MLIB_BYTE:
            {
              mlib_u8 *ddbtb = mlib_ImbgfGftDbtb(dst);

              switdi (dibnnfls) {
                dbsf 3:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(U8, U8, 3);
                    rfturn MLIB_SUCCESS;
                  }

                dbsf 4:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(U8, U8, 4);
                    rfturn MLIB_SUCCESS;
                  }

                dffbult:
                  rfturn MLIB_FAILURE;
              }
            }

          dbsf MLIB_SHORT:
            {
              mlib_s16 *ddbtb = mlib_ImbgfGftDbtb(dst);

              dstridf /= 2;
              switdi (dibnnfls) {
                dbsf 3:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(U8, S16, 3);
                    rfturn MLIB_SUCCESS;
                  }

                dbsf 4:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(U8, S16, 4);
                    rfturn MLIB_SUCCESS;
                  }

                dffbult:
                  rfturn MLIB_FAILURE;
              }
            }
        dffbult:
          /* Unsupportfd typf of dfstinbtion imbgf */
          rfturn MLIB_FAILURE;
        }
      }

    dbsf MLIB_SHORT:
      {
        mlib_s16 *sdbtb = mlib_ImbgfGftDbtb(srd);

        sstridf /= 2;
        switdi (dtypf) {
          dbsf MLIB_BYTE:
            {
              mlib_u8 *ddbtb = mlib_ImbgfGftDbtb(dst);

              switdi (dibnnfls) {
                dbsf 3:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(S16, U8, 3);
                    rfturn MLIB_SUCCESS;
                  }

                dbsf 4:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(S16, U8, 4);
                    rfturn MLIB_SUCCESS;
                  }

                dffbult:
                  rfturn MLIB_FAILURE;
              }
            }

          dbsf MLIB_SHORT:
            {
              mlib_s16 *ddbtb = mlib_ImbgfGftDbtb(dst);

              dstridf /= 2;
              switdi (dibnnfls) {
                dbsf 3:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(S16, S16, 3);
                    rfturn MLIB_SUCCESS;
                  }

                dbsf 4:
                  {
                    MAIN_COLORTRUE2INDEX_LOOP(S16, S16, 4);
                    rfturn MLIB_SUCCESS;
                  }

                dffbult:
                  rfturn MLIB_FAILURE;
              }
            }
        dffbult:
          /* Unsupportfd typf of dfstinbtion imbgf */
          rfturn MLIB_FAILURE;
        }
      }

    dffbult:
      rfturn MLIB_FAILURE;
  }
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_U8_3(strudt lut_nodf_3 *nodf,
                                   mlib_u32          distbndf,
                                    mlib_s32    *found_dolor,
                                   mlib_u32          d0,
                                   mlib_u32          d1,
                                   mlib_u32          d2,
                                   donst mlib_u8     **bbsf)
{
  mlib_s32 i;

  for (i = 0; i < 8; i++) {

    if (nodf->tbg & (1 << i)) {
      /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
      mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
      mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
      mlib_u32 nfwdistbndf;

      nfwpbld0 = bbsf[0][nfwindfx];
      nfwpbld1 = bbsf[1][nfwindfx];
      nfwpbld2 = bbsf[2][nfwindfx];
      nfwdistbndf = FIND_DISTANCE_3(d0, nfwpbld0, d1, nfwpbld1, d2, nfwpbld2, 0);

      if (distbndf > nfwdistbndf) {
        *found_dolor = nfwindfx;
        distbndf = nfwdistbndf;
      }
    }
    flsf if (nodf->dontfnts.qubdrbnts[i])
      distbndf =
        mlib_sfbrdi_qubdrbnt_U8_3(nodf->dontfnts.qubdrbnts[i], distbndf,
                                  found_dolor, d0, d1, d2, bbsf);
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_U8_3(strudt lut_nodf_3 *nodf,
                                                mlib_u32          distbndf,
                                                 mlib_s32    *found_dolor,
                                                donst mlib_u32    *d,
                                                donst mlib_u8     **bbsf,
                                                mlib_u32          position,
                                                mlib_s32          pbss,
                                                mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[3][4] = {
    {0, 2, 4, 6},
    {0, 1, 4, 5},
    {0, 1, 2, 3}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf < (position + durrfnt_sizf - d[dir_bit]) * (position + durrfnt_sizf - d[dir_bit])) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 4; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_U8_3(nodf->dontfnts.qubdrbnts[qq],
                                                 distbndf, found_dolor, d, bbsf,
                                                 position, pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 8; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_U8_3(nodf->dontfnts.qubdrbnts[i],
                                                   distbndf, found_dolor, d,
                                                   bbsf,
                                                   position + durrfnt_sizf,
                                                   pbss - 1, dir_bit);
        flsf
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_U8_3(nodf->dontfnts.qubdrbnts[i], distbndf,
                                      found_dolor, d[0], d[1], d[2], bbsf);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_U8_3(strudt lut_nodf_3 *nodf,
                                                 mlib_u32          distbndf,
                                                  mlib_s32    *found_dolor,
                                                 donst mlib_u32    *d,
                                                 donst mlib_u8     **bbsf,
                                                 mlib_u32          position,
                                                 mlib_s32          pbss,
                                                 mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[3][4] = {
    {1, 3, 5, 7},
    {2, 3, 6, 7},
    {4, 5, 6, 7}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf <= (d[dir_bit] - position - durrfnt_sizf) * (d[dir_bit] - position - durrfnt_sizf)) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 4; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_U8_3(nodf->dontfnts.qubdrbnts[qq],
                                                  distbndf, found_dolor, d,
                                                  bbsf, position + durrfnt_sizf,
                                                  pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 8; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_U8_3(nodf->dontfnts.qubdrbnts[i], distbndf,
                                      found_dolor, d[0], d[1], d[2], bbsf);
        flsf
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_U8_3(nodf->dontfnts.qubdrbnts[i],
                                                    distbndf, found_dolor, d,
                                                    bbsf, position, pbss - 1, dir_bit);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_S16_3(strudt lut_nodf_3 *nodf,
                                    mlib_u32          distbndf,
                                     mlib_s32    *found_dolor,
                                    mlib_u32          d0,
                                    mlib_u32          d1,
                                    mlib_u32          d2,
                                    donst mlib_s16    **bbsf)
{
  mlib_s32 i;

  for (i = 0; i < 8; i++) {

    if (nodf->tbg & (1 << i)) {
      /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
      mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
      mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
      mlib_u32 nfwdistbndf;

      nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
      nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
      nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
      nfwdistbndf = FIND_DISTANCE_3(d0, nfwpbld0, d1, nfwpbld1, d2, nfwpbld2, 2);

      if (distbndf > nfwdistbndf) {
        *found_dolor = nfwindfx;
        distbndf = nfwdistbndf;
      }
    }
    flsf if (nodf->dontfnts.qubdrbnts[i])
      distbndf =
        mlib_sfbrdi_qubdrbnt_S16_3(nodf->dontfnts.qubdrbnts[i], distbndf,
                                   found_dolor, d0, d1, d2, bbsf);
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_S16_3(strudt lut_nodf_3 *nodf,
                                                 mlib_u32          distbndf,
                                                  mlib_s32    *found_dolor,
                                                 donst mlib_u32    *d,
                                                 donst mlib_s16    **bbsf,
                                                 mlib_u32          position,
                                                 mlib_s32          pbss,
                                                 mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[3][4] = {
    {0, 2, 4, 6},
    {0, 1, 4, 5},
    {0, 1, 2, 3}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf < (((position + durrfnt_sizf - d[dir_bit]) * (position + durrfnt_sizf - d[dir_bit])) >> 2)) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 4; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_S16_3(nodf->dontfnts.qubdrbnts[qq],
                                                  distbndf, found_dolor, d,
                                                  bbsf, position, pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 8; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_S16_3(nodf->dontfnts.qubdrbnts[i],
                                                    distbndf, found_dolor, d,
                                                    bbsf,
                                                    position + durrfnt_sizf,
                                                    pbss - 1, dir_bit);
        flsf
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_S16_3(nodf->dontfnts.qubdrbnts[i], distbndf,
                                       found_dolor, d[0], d[1], d[2], bbsf);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_S16_3(strudt lut_nodf_3 *nodf,
                                                  mlib_u32          distbndf,
                                                   mlib_s32    *found_dolor,
                                                  donst mlib_u32    *d,
                                                  donst mlib_s16    **bbsf,
                                                  mlib_u32          position,
                                                  mlib_s32          pbss,
                                                  mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[3][4] = {
    {1, 3, 5, 7},
    {2, 3, 6, 7},
    {4, 5, 6, 7}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf <= (((d[dir_bit] - position - durrfnt_sizf) * (d[dir_bit] - position - durrfnt_sizf)) >> 2)) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 4; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_S16_3(nodf->dontfnts.qubdrbnts[qq],
                                                   distbndf, found_dolor, d,
                                                   bbsf,
                                                   position + durrfnt_sizf,
                                                   pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 8; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_3(d[0], nfwpbld0, d[1], nfwpbld1, d[2], nfwpbld2, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_S16_3(nodf->dontfnts.qubdrbnts[i], distbndf,
                                       found_dolor, d[0], d[1], d[2], bbsf);
        flsf
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_S16_3(nodf->dontfnts.
                                                     qubdrbnts[i], distbndf,
                                                     found_dolor, d, bbsf,
                                                     position, pbss - 1, dir_bit);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_U8_4(strudt lut_nodf_4 *nodf,
                                   mlib_u32          distbndf,
                                    mlib_s32    *found_dolor,
                                   mlib_u32          d0,
                                   mlib_u32          d1,
                                   mlib_u32          d2,
                                   mlib_u32          d3,
                                   donst mlib_u8     **bbsf)
{
  mlib_s32 i;

  for (i = 0; i < 16; i++) {

    if (nodf->tbg & (1 << i)) {
      /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
      mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
      mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
      mlib_u32 nfwdistbndf;

      nfwpbld0 = bbsf[0][nfwindfx];
      nfwpbld1 = bbsf[1][nfwindfx];
      nfwpbld2 = bbsf[2][nfwindfx];
      nfwpbld3 = bbsf[3][nfwindfx];
      nfwdistbndf = FIND_DISTANCE_4(d0, nfwpbld0,
                                    d1, nfwpbld1, d2, nfwpbld2, d3, nfwpbld3, 0);

      if (distbndf > nfwdistbndf) {
        *found_dolor = nfwindfx;
        distbndf = nfwdistbndf;
      }
    }
    flsf if (nodf->dontfnts.qubdrbnts[i])
      distbndf =
        mlib_sfbrdi_qubdrbnt_U8_4(nodf->dontfnts.qubdrbnts[i], distbndf,
                                  found_dolor, d0, d1, d2, d3, bbsf);
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_U8_4(strudt lut_nodf_4 *nodf,
                                                mlib_u32          distbndf,
                                                 mlib_s32    *found_dolor,
                                                donst mlib_u32    *d,
                                                donst mlib_u8     **bbsf,
                                                mlib_u32          position,
                                                mlib_s32          pbss,
                                                mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[4][8] = {
    {0, 2, 4, 6, 8, 10, 12, 14},
    {0, 1, 4, 5, 8, 9, 12, 13},
    {0, 1, 2, 3, 8, 9, 10, 11},
    {0, 1, 2, 3, 4, 5, 6, 7}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf < (position + durrfnt_sizf - d[dir_bit]) * (position + durrfnt_sizf - d[dir_bit])) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 8; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwpbld3 = bbsf[3][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_U8_4(nodf->dontfnts.qubdrbnts[qq],
                                                 distbndf, found_dolor, d, bbsf,
                                                 position, pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 16; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwpbld3 = bbsf[3][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_U8_4(nodf->dontfnts.qubdrbnts[i],
                                                   distbndf, found_dolor, d,
                                                   bbsf,
                                                   position + durrfnt_sizf,
                                                   pbss - 1, dir_bit);
        flsf
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_U8_4(nodf->dontfnts.qubdrbnts[i], distbndf,
                                      found_dolor, d[0], d[1], d[2], d[3], bbsf);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_U8_4(strudt lut_nodf_4 *nodf,
                                                 mlib_u32          distbndf,
                                                  mlib_s32    *found_dolor,
                                                 donst mlib_u32    *d,
                                                 donst mlib_u8     **bbsf,
                                                 mlib_u32          position,
                                                 mlib_s32          pbss,
                                                 mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[4][8] = {
    {1, 3, 5, 7, 9, 11, 13, 15},
    {2, 3, 6, 7, 10, 11, 14, 15},
    {4, 5, 6, 7, 12, 13, 14, 15},
    {8, 9, 10, 11, 12, 13, 14, 15}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf <= (d[dir_bit] - position - durrfnt_sizf) * (d[dir_bit] - position - durrfnt_sizf)) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 8; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwpbld3 = bbsf[3][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_U8_4(nodf->dontfnts.qubdrbnts[qq],
                                                  distbndf, found_dolor, d,
                                                  bbsf, position + durrfnt_sizf,
                                                  pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 16; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx];
        nfwpbld1 = bbsf[1][nfwindfx];
        nfwpbld2 = bbsf[2][nfwindfx];
        nfwpbld3 = bbsf[3][nfwindfx];
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 0);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_U8_4(nodf->dontfnts.qubdrbnts[i], distbndf,
                                      found_dolor, d[0], d[1], d[2], d[3], bbsf);
        flsf
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_U8_4(nodf->dontfnts.qubdrbnts[i],
                                                    distbndf, found_dolor, d,
                                                    bbsf, position, pbss - 1, dir_bit);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_S16_4(strudt lut_nodf_4 *nodf,
                                    mlib_u32          distbndf,
                                     mlib_s32    *found_dolor,
                                    mlib_u32          d0,
                                    mlib_u32          d1,
                                    mlib_u32          d2,
                                    mlib_u32          d3,
                                    donst mlib_s16    **bbsf)
{
  mlib_s32 i;

  for (i = 0; i < 16; i++) {

    if (nodf->tbg & (1 << i)) {
      /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
      mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
      mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
      mlib_u32 nfwdistbndf;

      nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
      nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
      nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
      nfwpbld3 = bbsf[3][nfwindfx] - MLIB_S16_MIN;
      nfwdistbndf = FIND_DISTANCE_4(d0, nfwpbld0,
                                    d1, nfwpbld1, d2, nfwpbld2, d3, nfwpbld3, 2);

      if (distbndf > nfwdistbndf) {
        *found_dolor = nfwindfx;
        distbndf = nfwdistbndf;
      }
    }
    flsf if (nodf->dontfnts.qubdrbnts[i])
      distbndf =
        mlib_sfbrdi_qubdrbnt_S16_4(nodf->dontfnts.qubdrbnts[i], distbndf,
                                   found_dolor, d0, d1, d2, d3, bbsf);
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_S16_4(strudt lut_nodf_4 *nodf,
                                                 mlib_u32          distbndf,
                                                  mlib_s32    *found_dolor,
                                                 donst mlib_u32    *d,
                                                 donst mlib_s16    **bbsf,
                                                 mlib_u32          position,
                                                 mlib_s32          pbss,
                                                 mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[4][8] = {
    {0, 2, 4, 6, 8, 10, 12, 14},
    {0, 1, 4, 5, 8, 9, 12, 13},
    {0, 1, 2, 3, 8, 9, 10, 11},
    {0, 1, 2, 3, 4, 5, 6, 7}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf < (((position + durrfnt_sizf - d[dir_bit]) * (position + durrfnt_sizf - d[dir_bit])) >> 2)) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 8; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwpbld3 = bbsf[3][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_S16_4(nodf->dontfnts.qubdrbnts[qq],
                                                  distbndf, found_dolor, d,
                                                  bbsf, position, pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 16; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwpbld3 = bbsf[3][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_lfft_S16_4(nodf->dontfnts.qubdrbnts[i],
                                                    distbndf, found_dolor, d,
                                                    bbsf,
                                                    position + durrfnt_sizf,
                                                    pbss - 1, dir_bit);
        flsf
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_S16_4(nodf->dontfnts.qubdrbnts[i], distbndf,
                                       found_dolor, d[0], d[1], d[2], d[3], bbsf);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/
mlib_u32 mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_S16_4(strudt lut_nodf_4 *nodf,
                                                  mlib_u32          distbndf,
                                                   mlib_s32    *found_dolor,
                                                  donst mlib_u32    *d,
                                                  donst mlib_s16    **bbsf,
                                                  mlib_u32          position,
                                                  mlib_s32          pbss,
                                                  mlib_s32          dir_bit)
{
  mlib_u32 durrfnt_sizf = 1 << pbss;
  mlib_s32 i;
  stbtid mlib_s32 oppositf_qubdrbnts[4][8] = {
    {1, 3, 5, 7, 9, 11, 13, 15},
    {2, 3, 6, 7, 10, 11, 14, 15},
    {4, 5, 6, 7, 12, 13, 14, 15},
    {8, 9, 10, 11, 12, 13, 14, 15}
  };

/* Sfbrdi only qubdrbnt's iblf untill it is nfdfssbry to difdk tif
  wiolf qubdrbnt */

  if (distbndf <= (((d[dir_bit] - position - durrfnt_sizf) * (d[dir_bit] - position - durrfnt_sizf)) >> 2)) { /* Sfbrdi iblf of qubdrbnt */
    for (i = 0; i < 8; i++) {
      mlib_s32 qq = oppositf_qubdrbnts[dir_bit][i];

      if (nodf->tbg & (1 << qq)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[qq];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwpbld3 = bbsf[3][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[qq])
        distbndf =
          mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_S16_4(nodf->dontfnts.qubdrbnts[qq],
                                                   distbndf, found_dolor, d,
                                                   bbsf,
                                                   position + durrfnt_sizf,
                                                   pbss - 1, dir_bit);
    }
  }
  flsf {                                    /* Sfbrdi wiolf qubdrbnt */

    mlib_s32 mbsk = 1 << dir_bit;

    for (i = 0; i < 16; i++) {

      if (nodf->tbg & (1 << i)) {
        /* Hfrf is blonf dolor dfll. Cifdk tif distbndf */
        mlib_s32 nfwindfx = nodf->dontfnts.indfx[i];
        mlib_u32 nfwpbld0, nfwpbld1, nfwpbld2, nfwpbld3;
        mlib_u32 nfwdistbndf;

        nfwpbld0 = bbsf[0][nfwindfx] - MLIB_S16_MIN;
        nfwpbld1 = bbsf[1][nfwindfx] - MLIB_S16_MIN;
        nfwpbld2 = bbsf[2][nfwindfx] - MLIB_S16_MIN;
        nfwpbld3 = bbsf[3][nfwindfx] - MLIB_S16_MIN;
        nfwdistbndf = FIND_DISTANCE_4(d[0], nfwpbld0,
                                      d[1], nfwpbld1, d[2], nfwpbld2, d[3], nfwpbld3, 2);

        if (distbndf > nfwdistbndf) {
          *found_dolor = nfwindfx;
          distbndf = nfwdistbndf;
        }
      }
      flsf if (nodf->dontfnts.qubdrbnts[i]) {

        if (i & mbsk)
          /* Hfrf wf siould difdk bll */
          distbndf =
            mlib_sfbrdi_qubdrbnt_S16_4(nodf->dontfnts.qubdrbnts[i], distbndf,
                                       found_dolor, d[0], d[1], d[2], d[3], bbsf);
        flsf
          /* Tiis qubdrbnt mby rfquirf pbrtibl difdking */
          distbndf =
            mlib_sfbrdi_qubdrbnt_pbrt_to_rigit_S16_4(nodf->dontfnts.
                                                     qubdrbnts[i], distbndf,
                                                     found_dolor, d, bbsf,
                                                     position, pbss - 1, dir_bit);
      }
    }
  }

  rfturn distbndf;
}

/***************************************************************/

#dffinf TAB_SIZE_mlib_u8   256
#dffinf TAB_SIZE_mlib_s16 1024

#dffinf SRC_mlib_u8(i)    srd[i]
#dffinf SRC_mlib_s16(i)   (((mlib_u16*)srd)[i] >> 6)

/***************************************************************/

#dffinf DIMENSIONS_SEARCH_3(STYPE, DTYPE, STEP)                 \
{                                                               \
  DTYPE  *tbb0 = ((mlib_dolormbp *)stbtf)->tbblf;               \
  DTYPE  *tbb1 = tbb0 + TAB_SIZE_##STYPE;                       \
  DTYPE  *tbb2 = tbb1 + TAB_SIZE_##STYPE;                       \
  mlib_s32 i;                                                   \
                                                                \
  for (i = 0; i < lfngti; i++) {                                \
    dst[i] = tbb0[SRC_##STYPE(0)] + tbb1[SRC_##STYPE(1)] +      \
             tbb2[SRC_##STYPE(2)];                              \
    srd += STEP;                                                \
  }                                                             \
}

/***************************************************************/

#dffinf DIMENSIONS_SEARCH_4(STYPE, DTYPE)                       \
{                                                               \
  DTYPE  *tbb0 = ((mlib_dolormbp *)stbtf)->tbblf;               \
  DTYPE  *tbb1 = tbb0 + TAB_SIZE_##STYPE;                       \
  DTYPE  *tbb2 = tbb1 + TAB_SIZE_##STYPE;                       \
  DTYPE  *tbb3 = tbb2 + TAB_SIZE_##STYPE;                       \
  mlib_s32 i;                                                   \
                                                                \
  for (i = 0; i < lfngti; i++) {                                \
    dst[i] = tbb0[SRC_##STYPE(0)] + tbb1[SRC_##STYPE(1)] +      \
             tbb2[SRC_##STYPE(2)] + tbb3[SRC_##STYPE(3)];       \
    srd += 4;                                                   \
  }                                                             \
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_U8_U8_3(donst mlib_u8 *srd,
                                           mlib_u8       *dst,
                                           mlib_s32      lfngti,
                                           donst void    *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;

  switdi (s->mftiod) {
#if LUT_BYTE_COLORS_3CHANNELS <= 256
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        mlib_s32 bits = s->bits;
        BINARY_TREE_SEARCH_3(U8, mlib_u8, 8, (MLIB_U8_MAX + 1), 0, 0, 3, 0);
      }
      brfbk;

#fndif /* LUT_BYTE_COLORS_3CHANNELS <= 256 */
    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        COLOR_CUBE_U8_3_SEARCH(mlib_u8, 0, 3);
      }
      brfbk;

    dbsf LUT_STUPID_SEARCH:
      {
#ifdff USE_VIS_CODE
        FIND_NEAREST_U8_3;
#flsf
        FIND_NEAREST_U8_3_C(0, 3);
#fndif
      }
      brfbk;

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_3(mlib_u8, mlib_u8, 3)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_U8_U8_3_in_4(donst mlib_u8 *srd,
                                                mlib_u8       *dst,
                                                mlib_s32      lfngti,
                                                donst void    *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;

  switdi (s->mftiod) {
#if LUT_BYTE_COLORS_3CHANNELS <= 256
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        mlib_s32 bits = s->bits;
        BINARY_TREE_SEARCH_3(U8, mlib_u8, 8, (MLIB_U8_MAX + 1), 0, 1, 4, 0);
        brfbk;
      }

#fndif /* LUT_BYTE_COLORS_3CHANNELS <= 256 */
    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        COLOR_CUBE_U8_3_SEARCH(mlib_u8, 1, 4);
        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
#ifdff USE_VIS_CODE
        FIND_NEAREST_U8_3_IN4;
#flsf
        FIND_NEAREST_U8_3_C(1, 4);
#fndif
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      srd++;
      DIMENSIONS_SEARCH_3(mlib_u8, mlib_u8, 4)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_U8_U8_4(donst mlib_u8 *srd,
                                           mlib_u8       *dst,
                                           mlib_s32      lfngti,
                                           donst void    *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;

  switdi (s->mftiod) {
#if LUT_BYTE_COLORS_4CHANNELS <= 256
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        mlib_s32 bits = s->bits;
        BINARY_TREE_SEARCH_4(U8, mlib_u8, 8, (MLIB_U8_MAX + 1), 0, 0);
        brfbk;
      }

#fndif /* LUT_BYTE_COLORS_4CHANNELS <= 256 */
    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        COLOR_CUBE_U8_4_SEARCH(mlib_u8);
        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
#ifdff USE_VIS_CODE
        FIND_NEAREST_U8_4;
#flsf
        FIND_NEAREST_U8_4_C;
#fndif
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_4(mlib_u8, mlib_u8)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_U8_S16_3(donst mlib_u8 *srd,
                                            mlib_s16      *dst,
                                            mlib_s32      lfngti,
                                            donst void    *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;
  mlib_s32 bits = s->bits;

  switdi (s->mftiod) {
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        BINARY_TREE_SEARCH_3(U8, mlib_u8, 8, (MLIB_U8_MAX + 1), 0, 0, 3, 0);
        brfbk;
      }

    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        switdi (s->indfxsizf) {
          dbsf 1:
            {
              COLOR_CUBE_U8_3_SEARCH(mlib_u8, 0, 3);
              brfbk;
            }

          dbsf 2:
            {
              COLOR_CUBE_U8_3_SEARCH(mlib_s16, 0, 3);
              brfbk;
            }
        }

        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
#ifdff USE_VIS_CODE
        FIND_NEAREST_U8_3;
#flsf
        FIND_NEAREST_U8_3_C(0, 3);
#fndif
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_3(mlib_u8, mlib_s16, 3)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_U8_S16_3_in_4(donst mlib_u8 *srd,
                                                 mlib_s16      *dst,
                                                 mlib_s32      lfngti,
                                                 donst void    *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;
  mlib_s32 bits = s->bits;

  switdi (s->mftiod) {
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        BINARY_TREE_SEARCH_3(U8, mlib_u8, 8, (MLIB_U8_MAX + 1), 0, 1, 4, 0);
        brfbk;
      }

    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        switdi (s->indfxsizf) {
          dbsf 1:
            {
              COLOR_CUBE_U8_3_SEARCH(mlib_u8, 1, 4);
              brfbk;
            }

          dbsf 2:
            {
              COLOR_CUBE_U8_3_SEARCH(mlib_s16, 1, 4);
              brfbk;
            }
        }

        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
#ifdff USE_VIS_CODE
        FIND_NEAREST_U8_3_IN4;
#flsf
        FIND_NEAREST_U8_3_C(1, 4);
#fndif
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      srd++;
      DIMENSIONS_SEARCH_3(mlib_u8, mlib_s16, 4)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_U8_S16_4(donst mlib_u8 *srd,
                                            mlib_s16      *dst,
                                            mlib_s32      lfngti,
                                            donst void    *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;
  mlib_s32 bits = s->bits;

  switdi (s->mftiod) {
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        BINARY_TREE_SEARCH_4(U8, mlib_u8, 8, (MLIB_U8_MAX + 1), 0, 0);
        brfbk;
      }

    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        switdi (s->indfxsizf) {
          dbsf 1:
            {
              COLOR_CUBE_U8_4_SEARCH(mlib_u8);
              brfbk;
            }

          dbsf 2:
            {
              COLOR_CUBE_U8_4_SEARCH(mlib_s16);
              brfbk;
            }
        }

        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
#ifdff USE_VIS_CODE
        FIND_NEAREST_U8_4;
#flsf
        FIND_NEAREST_U8_4_C;
#fndif
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_4(mlib_u8, mlib_s16)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_S16_S16_3(donst mlib_s16 *srd,
                                             mlib_s16       *dst,
                                             mlib_s32       lfngti,
                                             donst void     *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;
  mlib_s32 bits = s->bits;

  switdi (s->mftiod) {
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        BINARY_TREE_SEARCH_3(S16, mlib_s16, 16, ((MLIB_S16_MAX + 1) * 2),
                             MLIB_S16_MIN, 0, 3, 2);
        brfbk;
      }

    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        switdi (s->indfxsizf) {
          dbsf 1:
            {
              COLOR_CUBE_S16_3_SEARCH(mlib_u8, 0, 3);
              brfbk;
            }

          dbsf 2:
            {
              COLOR_CUBE_S16_3_SEARCH(mlib_s16, 0, 3);
              brfbk;
            }
        }

        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
        FIND_NEAREST_S16_3(0, 3);
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_3(mlib_s16, mlib_s16, 3)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_S16_S16_3_in_4(donst mlib_s16 *srd,
                                                  mlib_s16       *dst,
                                                  mlib_s32       lfngti,
                                                  donst void     *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;
  mlib_s32 bits = s->bits;

  switdi (s->mftiod) {
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        BINARY_TREE_SEARCH_3(S16, mlib_s16, 16, ((MLIB_S16_MAX + 1) * 2),
                             MLIB_S16_MIN, 1, 4, 2);
        brfbk;
      }

    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        switdi (s->indfxsizf) {
          dbsf 1:
            {
              COLOR_CUBE_S16_3_SEARCH(mlib_u8, 1, 4);
              brfbk;
            }

          dbsf 2:
            {
              COLOR_CUBE_S16_3_SEARCH(mlib_s16, 1, 4);
              brfbk;
            }
        }

        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
        FIND_NEAREST_S16_3(1, 4);
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      srd++;
      DIMENSIONS_SEARCH_3(mlib_s16, mlib_s16, 4)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_S16_S16_4(donst mlib_s16 *srd,
                                             mlib_s16       *dst,
                                             mlib_s32       lfngti,
                                             donst void     *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;
  mlib_s32 bits = s->bits;

  switdi (s->mftiod) {
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        BINARY_TREE_SEARCH_4(S16, mlib_s16, 16, ((MLIB_S16_MAX + 1) * 2),
                             MLIB_S16_MIN, 2);
        brfbk;
      }

    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        switdi (s->indfxsizf) {
          dbsf 1:
            {
              COLOR_CUBE_S16_4_SEARCH(mlib_u8);
              brfbk;
            }

          dbsf 2:
            {
              COLOR_CUBE_S16_4_SEARCH(mlib_s16);
              brfbk;
            }
        }

        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
        FIND_NEAREST_S16_4;
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_4(mlib_s16, mlib_s16)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_S16_U8_3(donst mlib_s16 *srd,
                                            mlib_u8        *dst,
                                            mlib_s32       lfngti,
                                            donst void     *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;

  switdi (s->mftiod) {
#if LUT_SHORT_COLORS_3CHANNELS <= 256
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        mlib_s32 bits = s->bits;
        BINARY_TREE_SEARCH_3(S16, mlib_s16, 16, ((MLIB_S16_MAX + 1) * 2),
                             MLIB_S16_MIN, 0, 3, 2);
        brfbk;
      }

#fndif /* LUT_SHORT_COLORS_3CHANNELS <= 256 */
    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        COLOR_CUBE_S16_3_SEARCH(mlib_u8, 0, 3);
        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
        FIND_NEAREST_S16_3(0, 3);
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_3(mlib_s16, mlib_u8, 3)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_S16_U8_3_in_4(donst mlib_s16 *srd,
                                                 mlib_u8        *dst,
                                                 mlib_s32       lfngti,
                                                 donst void     *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;

  switdi (s->mftiod) {
#if LUT_SHORT_COLORS_3CHANNELS <= 256
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        mlib_s32 bits = s->bits;
        BINARY_TREE_SEARCH_3(S16, mlib_s16, 16, ((MLIB_S16_MAX + 1) * 2),
                             MLIB_S16_MIN, 1, 4, 2);
        brfbk;
      }

#fndif /* LUT_SHORT_COLORS_3CHANNELS <= 256 */
    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        COLOR_CUBE_S16_3_SEARCH(mlib_u8, 1, 4);
        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
        FIND_NEAREST_S16_3(1, 4);
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      srd++;
      DIMENSIONS_SEARCH_3(mlib_s16, mlib_u8, 4)
      brfbk;
  }
}

/***************************************************************/
void mlib_ImbgfColorTruf2IndfxLinf_S16_U8_4(donst mlib_s16 *srd,
                                            mlib_u8        *dst,
                                            mlib_s32       lfngti,
                                            donst void     *stbtf)
{
  mlib_dolormbp *s = (mlib_dolormbp *)stbtf;

  switdi (s->mftiod) {
#if LUT_SHORT_COLORS_4CHANNELS <= 256
    dbsf LUT_BINARY_TREE_SEARCH:
      {
        mlib_s32 bits = s->bits;
        BINARY_TREE_SEARCH_4(S16, mlib_s16, 16, ((MLIB_S16_MAX + 1) * 2),
                             MLIB_S16_MIN, 2);
        brfbk;
      }

#fndif /* LUT_SHORT_COLORS_4CHANNELS <= 256 */
    dbsf LUT_COLOR_CUBE_SEARCH:
      {
        COLOR_CUBE_S16_4_SEARCH(mlib_u8);
        brfbk;
      }

    dbsf LUT_STUPID_SEARCH:
      {
        FIND_NEAREST_S16_4;
        brfbk;
      }

    dbsf LUT_COLOR_DIMENSIONS:
      DIMENSIONS_SEARCH_4(mlib_s16, mlib_u8)
      brfbk;
  }
}

/***************************************************************/

#ifndff VIS

void mlib_d_ImbgfTirfsi1_U81_1B(void     *psrd,
                                void     *pdst,
                                mlib_s32 srd_stridf,
                                mlib_s32 dst_stridf,
                                mlib_s32 widti,
                                mlib_s32 ifigit,
                                void     *tirfsi,
                                void     *giigi,
                                void     *glow,
                                mlib_s32 dbit_off);

/***************************************************************/

void mlib_ImbgfColorTruf2IndfxLinf_U8_BIT_1(donst mlib_u8 *srd,
                                            mlib_u8       *dst,
                                            mlib_s32      bit_offsft,
                                            mlib_s32      lfngti,
                                            donst void    *stbtf)
{
  mlib_u8  *lut = ((mlib_dolormbp *)stbtf)->tbblf;
  mlib_s32 tirfsi[1];
  mlib_s32 giigi[1];
  mlib_s32 glow[1];

  tirfsi[0] = lut[2];

  glow[0]  = lut[0] - lut[1];
  giigi[0] = lut[1] - lut[0];

  mlib_d_ImbgfTirfsi1_U81_1B((void*)srd, dst, 0, 0, lfngti, 1,
                             tirfsi, giigi, glow, bit_offsft);
}

#flsf

/***************************************************************/

void mlib_v_ImbgfTirfsi1B_U8_1(donst mlib_u8  *srd,
                               mlib_s32       slb,
                               mlib_u8        *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsizf,
                               mlib_s32       ysizf,
                               mlib_s32       dbit_off,
                               donst mlib_s32 *ti,
                               mlib_s32       id,
                               mlib_s32       ld);

/***************************************************************/

void mlib_ImbgfColorTruf2IndfxLinf_U8_BIT_1(donst mlib_u8 *srd,
                                            mlib_u8       *dst,
                                            mlib_s32      bit_offsft,
                                            mlib_s32      lfngti,
                                            donst void    *stbtf)
{
  mlib_u8  *lut = ((mlib_dolormbp *)stbtf)->tbblf;
  mlib_s32 tirfsi[4];
  mlib_s32 giigi[1];
  mlib_s32 glow[1];

  tirfsi[0] = tirfsi[1] = tirfsi[2] = tirfsi[3] = lut[2];

  glow[0]  = (lut[1] < lut[0]) ? 0xFF : 0;
  giigi[0] = (lut[1] < lut[0]) ? 0 : 0xFF;

  mlib_v_ImbgfTirfsi1B_U8_1((void*)srd, 0, dst, 0, lfngti, 1,
                            bit_offsft, tirfsi, giigi[0], glow[0]);
}

/***************************************************************/

#fndif
