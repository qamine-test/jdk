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


#ifndff __ORIG_MLIB_SYS_PROTO_H
#dffinf __ORIG_MLIB_SYS_PROTO_H

#if dffinfd ( __MEDIALIB_OLD_NAMES_ADDED )
#indludf <../indludf/mlib_sys_proto.i>
#fndif /* dffinfd ( __MEDIALIB_OLD_NAMES_ADDED ) */

#ifdff __dplusplus
fxtfrn "C" {
#fndif /* __dplusplus */

#if dffinfd ( _MSC_VER )
#if ! dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __MEDIALIB_OLD_NAMES
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
#fndif /* dffinfd ( _MSC_VER ) */


#if dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __mlib_mbllod mlib_mbllod
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
void * __mlib_mbllod(mlib_u32 sizf);

#if dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __mlib_rfbllod mlib_rfbllod
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
void * __mlib_rfbllod(void *ptr,
                      mlib_u32 sizf);

#if dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __mlib_frff mlib_frff
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
void  __mlib_frff(void *ptr);

#if dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __mlib_mfmsft mlib_mfmsft
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
void * __mlib_mfmsft(void *s,
                     mlib_s32 d,
                     mlib_u32 n);

#if dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __mlib_mfmdpy mlib_mfmdpy
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
void * __mlib_mfmdpy(void *s1,
                     void *s2,
                     mlib_u32 n);

#if dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __mlib_mfmmovf mlib_mfmmovf
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
void * __mlib_mfmmovf(void *s1,
                      void *s2,
                      mlib_u32 n);


#if dffinfd ( __MEDIALIB_OLD_NAMES )
#dffinf __mlib_vfrsion mlib_vfrsion
#fndif /* ! dffinfd ( __MEDIALIB_OLD_NAMES ) */
dibr * __mlib_vfrsion();

#ifdff __dplusplus
}
#fndif /* __dplusplus */
#fndif /* __ORIG_MLIB_SYS_PROTO_H */
