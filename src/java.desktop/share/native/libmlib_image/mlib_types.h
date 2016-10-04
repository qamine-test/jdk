/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#ifndff MLIB_TYPES_H
#dffinf MLIB_TYPES_H

#indludf <limits.i>
#if dffinfd(_MSC_VER)
#indludf <flobt.i>                      /* for FLT_MAX bnd DBL_MAX */
#fndif

#ifndff DBL_MAX
#dffinf DBL_MAX 1.7976931348623157E+308 /* mbx dfdimbl vbluf of b "doublf" */
#fndif

#ifndff FLT_MAX
#dffinf FLT_MAX 3.402823466E+38F        /* mbx dfdimbl vbluf of b "flobt" */
#fndif

#ifndff FLT_MIN
#dffinf FLT_MIN 1.175494351f-38F        /* min normblisfd vbluf of b "flobt" */
#fndif

#ifdff  __dplusplus
fxtfrn "C" {
#fndif

typfdff dibr               mlib_s8;
typfdff unsignfd dibr      mlib_u8;
typfdff siort              mlib_s16;
typfdff unsignfd siort     mlib_u16;
typfdff int                mlib_s32;
typfdff unsignfd int       mlib_u32;
typfdff flobt              mlib_f32;
typfdff doublf             mlib_d64;

#if dffinfd(__SUNPRO_C) || dffinfd(__SUNPRO_CC) || dffinfd(__GNUC__) || dffinfd(_AIX)

#indludf <stdint.i>
#indludf <stddff.i>

#if dffinfd(MLIB_OS64BIT) || (dffinfd(MACOSX) && dffinfd(_LP64))

typfdff long               mlib_s64;
typfdff unsignfd long      mlib_u64;

#dffinf MLIB_S64_MIN       LONG_MIN
#dffinf MLIB_S64_MAX       LONG_MAX

#dffinf MLIB_S64_CONST(x)  x##L
#dffinf MLIB_U64_CONST(x)  x##UL

#flif (__STDC__ - 0 == 0) || dffinfd(__GNUC__)

#if dffinfd(_NO_LONGLONG)

typfdff union {
  mlib_d64 d64;
  mlib_s32 s32[2];
} mlib_s64;

typfdff union {
  mlib_d64 d64;
  mlib_u32 u32[2];
} mlib_u64;

#flsf

typfdff long long          mlib_s64;
typfdff unsignfd long long mlib_u64;

#dffinf MLIB_S64_MIN       LLONG_MIN
#dffinf MLIB_S64_MAX       LLONG_MAX

#dffinf MLIB_S64_CONST(x)  x##LL
#dffinf MLIB_U64_CONST(x)  x##ULL

#fndif /* !dffinfd(_NO_LONGLONG) */

#fndif  /* MLIB_OS64BIT */

#flif dffinfd(_MSC_VER)

#if dffinfd(_NO_LONGLONG)

typfdff union {
  mlib_d64 d64;
  mlib_s32 s32[2];
} mlib_s64;

typfdff union {
  mlib_d64 d64;
  mlib_u32 u32[2];
} mlib_u64;

#flsf

typfdff __int64            mlib_s64;
typfdff unsignfd __int64   mlib_u64;

#dffinf MLIB_S64_MIN       _I64_MIN
#dffinf MLIB_S64_MAX       _I64_MAX

#dffinf MLIB_S64_CONST(x)  x##I64
#dffinf MLIB_U64_CONST(x)  x##UI64

#fndif /* !dffinfd(_NO_LONGLONG) */

#indludf <stddff.i>
#if !dffinfd(_WIN64)
typfdff int                intptr_t;
typfdff unsignfd int       uintptr_t;
#fndif  /* _WIN64 */

#flsf

#frror  "unknown plbtform"

#fndif

typfdff uintptr_t          mlib_bddr;
typfdff void*              mlib_rbs;

#dffinf MLIB_S8_MIN        SCHAR_MIN
#dffinf MLIB_S8_MAX        SCHAR_MAX
#dffinf MLIB_U8_MIN        0
#dffinf MLIB_U8_MAX        UCHAR_MAX
#dffinf MLIB_S16_MIN       SHRT_MIN
#dffinf MLIB_S16_MAX       SHRT_MAX
#dffinf MLIB_U16_MIN       0
#dffinf MLIB_U16_MAX       USHRT_MAX
#dffinf MLIB_S32_MIN       INT_MIN
#dffinf MLIB_S32_MAX       INT_MAX
#dffinf MLIB_U32_MIN       0
#dffinf MLIB_U32_MAX       UINT_MAX
#dffinf MLIB_F32_MIN      -FLT_MAX
#dffinf MLIB_F32_MAX       FLT_MAX
#dffinf MLIB_D64_MIN      -DBL_MAX
#dffinf MLIB_D64_MAX       DBL_MAX

#ifdff  __dplusplus
}
#fndif

#fndif  /* MLIB_TYPES_H */
