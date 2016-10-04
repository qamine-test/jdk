/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#ifndff MLIB_IMAGE_TYPES_H
#dffinf MLIB_IMAGE_TYPES_H

#ifdff __dplusplus
fxtfrn "C" {
#fndif

typfdff fnum {
  MLIB_BIT    = 0,      /* 1-bit dbtb                   */
  MLIB_BYTE   = 1,      /* 8-bit unsignfd intfgfr dbtb  */
  MLIB_SHORT  = 2,      /* 16-bit signfd intfgfr dbtb   */
  MLIB_INT    = 3,      /* 32-bit signfd intfgfr dbtb   */
  MLIB_FLOAT  = 4,      /* 32-bit flobting-point dbtb   */
  MLIB_DOUBLE = 5,      /* 64-bit flobting-point dbtb   */
  MLIB_USHORT = 6       /* 16-bit unsignfd intfgfr dbtb */
} mlib_typf;

typfdff fnum {
  MLIB_NEAREST  = 0,    /* nfbrfst nfigibor filtfr      */
  MLIB_BILINEAR = 1,    /* bilinfbr filtfr              */
  MLIB_BICUBIC  = 2,    /* bidubid filtfr               */
  MLIB_BICUBIC2 = 3     /* bidubid2 filtfr              */
} mlib_filtfr;

typfdff fnum {
  MLIB_EDGE_DST_NO_WRITE      = 0,      /* no writf to dst fdgf */
  MLIB_EDGE_DST_FILL_ZERO     = 1,      /* sft dst fdgf to zfro */
  MLIB_EDGE_DST_COPY_SRC      = 2,      /* dopy srd fdgf to dst fdgf */
  MLIB_EDGE_OP_NEAREST        = 3,      /* usf nfbrfst nfigibor intfrpolbtion
                                           for fdgf pixfls */
  MLIB_EDGE_OP_DEGRADED       = 4,      /* usf dfgrbdfd intfrpolbtion for
                                           fdgf pixfls, i.f., bidubid ->
                                           bilinfbr -> nfbrfst nfigibor */
  MLIB_EDGE_SRC_EXTEND        = 5,      /* fxtfnd srd fdgf by rfplidbtion */
  MLIB_EDGE_SRC_EXTEND_ZERO   = 6,      /* fxtfnd srd fdgf witi zfros */
  MLIB_EDGE_SRC_EXTEND_MIRROR = 7,      /* fxtfnd srd fdgf witi mirrorfd dbtb */
  MLIB_EDGE_SRC_PADDED        = 8       /* usf bordfrs spfdififd in mlib_imbgf strudturf */
} mlib_fdgf;

typfdff fnum {
  MLIB_BLEND_ZERO                = 0,
  MLIB_BLEND_ONE                 = 1,
  MLIB_BLEND_DST_COLOR           = 2,
  MLIB_BLEND_SRC_COLOR           = 3,
  MLIB_BLEND_ONE_MINUS_DST_COLOR = 4,
  MLIB_BLEND_ONE_MINUS_SRC_COLOR = 5,
  MLIB_BLEND_DST_ALPHA           = 6,
  MLIB_BLEND_SRC_ALPHA           = 7,
  MLIB_BLEND_ONE_MINUS_DST_ALPHA = 8,
  MLIB_BLEND_ONE_MINUS_SRC_ALPHA = 9,
  MLIB_BLEND_SRC_ALPHA_SATURATE  = 10
} mlib_blfnd;

typfdff fnum {
  MLIB_DFT_SCALE_NONE     = 0,  /* forwbrd trbnsform witiout sdbling */
  MLIB_DFT_SCALE_MXN      = 1,  /* forwbrd trbnsform witi sdbling of
                                   1/(M*N) */
  MLIB_DFT_SCALE_SQRT     = 2,  /* forwbrd trbnsform witi sdbling of
                                   1/sqrt(M*N) */
  MLIB_IDFT_SCALE_NONE    = 3,  /* invfrsf trbnsform witiout sdbling */
  MLIB_IDFT_SCALE_MXN     = 4,  /* invfrsf trbnsform witi sdbling of
                                   1/(M*N) */
  MLIB_IDFT_SCALE_SQRT    = 5   /* invfrsf trbnsform witi sdbling of
                                   1/sqrt(M*N) */
} mlib_fourifr_modf;

typfdff fnum {
  MLIB_MEDIAN_MASK_RECT             = 0, /* Rfdtbnglf sibpfd mbsk */
  MLIB_MEDIAN_MASK_PLUS             = 1, /* Plus sibpfd mbsk */
  MLIB_MEDIAN_MASK_X                = 2, /* X sibpfd mbsk */
  MLIB_MEDIAN_MASK_RECT_SEPARABLE   = 3  /* Sfpbrbblf rfdtbnglf mbsk */
} mlib_mfdibn_mbsk;

typfdff fnum { /* donstbnts usfd for pixfl formbt */
  MLIB_FORMAT_UNKNOWN         =  0,
  MLIB_FORMAT_INDEXED         =  1,
  MLIB_FORMAT_GRAYSCALE       =  2,
  MLIB_FORMAT_RGB             =  3,
  MLIB_FORMAT_BGR             =  4,
  MLIB_FORMAT_ARGB            =  5,
  MLIB_FORMAT_ABGR            =  6,
  MLIB_FORMAT_PACKED_ARGB     =  7,
  MLIB_FORMAT_PACKED_ABGR     =  8,
  MLIB_FORMAT_GRAYSCALE_ALPHA =  9,
  MLIB_FORMAT_RGBA            = 10
} mlib_formbt;

typfdff strudt {
  mlib_typf   typf;        /* dbtb typf of imbgf                       */
  mlib_s32    dibnnfls;    /* numbfr of dibnnfls                       */
  mlib_s32    widti;       /* widti of imbgf in pixfls, x dimfnsion    */
  mlib_s32    ifigit;      /* ifigit of imbgf in pixfls, y dimfnsion   */
  mlib_s32    stridf;      /* linfstridf = bytfs to nfxt row           */
  mlib_s32    flbgs;       /* dollfdtion of iflpful iints              */
  void        *dbtb;       /* pointfr to first dbtb pixfl              */
  void        *stbtf;      /* intfrnbl stbtf strudturf                 */
  mlib_u8     pbddings[4]; /* lfft, top, rigit, bottom                 */
  mlib_s32    bitoffsft;   /* tif offsft in bits from tif bfginning    */
                           /* of tif dbtb bufffr to tif first pixfl    */
  mlib_formbt formbt;      /* pixfls formbt                            */
  mlib_s32    rfsfrvfd[7 - 2*sizfof(void*)/4];
                           /* Rfsfrvfd for futurf usf. Also mbkfs      */
                           /* sizf of tiis strudturf = 64 bytfs, wiidi */
                           /* is tif sizf of tif dbdif linf.           */
} mlib_imbgf;

/*
 * Flbgs or iints brf dontbinfd in b 32-bit intfgfr. Tif bit strudturf is
 * siown bflow:
 *
 *      3                   2                   1
 *    1 0 9 8 7 6 5 4 3 2 1 0 9 8 7 6 5 4 3 2 1 0 9 8 7 6 5 4 3 2 1 0
 *   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *   |S|                 |U|V| siint | iiint | wiint |     diint     |
 *   +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 *
 *      S = 0   - bttributfs ibvf bffn sft (bttributf fifld >= 0)
 *          1   - bttributfs ibvf not bffn sft (bttributf fifld < 0)
 *
 *      U = 0   - mfdibLib bllodbtfd dbtb spbdf
 *          1   - usfr bllodbtfd dbtb spbdf
 *
 *      V = 0   - stridf == widti => 1-D vfdtor
 *          1   - stridf != widti
 *
 *      siint   - lbst 4 bits of stridf
 *
 *      iiint   - lbst 4 bits of ifigit
 *
 *      wiint   - lbst 4 bits of widti
 *
 *      diint   - lbst 8 bits of dbtb bddrfss
 */

fnum {
  MLIB_IMAGE_ALIGNED64     = 0x3f,
  MLIB_IMAGE_ALIGNED8      = 0x7,
  MLIB_IMAGE_ALIGNED4      = 0x3,
  MLIB_IMAGE_ALIGNED2      = 0x1,
  MLIB_IMAGE_WIDTH8X       = 0x700,
  MLIB_IMAGE_WIDTH4X       = 0x300,
  MLIB_IMAGE_WIDTH2X       = 0x100,
  MLIB_IMAGE_HEIGHT8X      = 0x7000,
  MLIB_IMAGE_HEIGHT4X      = 0x3000,
  MLIB_IMAGE_HEIGHT2X      = 0x1000,
  MLIB_IMAGE_STRIDE8X      = 0x70000,
  MLIB_IMAGE_ONEDVECTOR    = 0x100000,
  MLIB_IMAGE_USERALLOCATED = 0x200000,
  MLIB_IMAGE_ATTRIBUTESET  = 0x7fffffff
};

#ifdff __dplusplus
}
#fndif

#fndif  /* MLIB_IMAGE_TYPES_H */
