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

#ifndff __MLIB_V_IMAGELOGIC_PROTO_H
#dffinf __MLIB_V_IMAGELOGIC_PROTO_H


#indludf <mlib_typfs.i>
#indludf <mlib_imbgf_typfs.i>
#indludf <mlib_stbtus.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif /* __dplusplus */

void mlib_v_ImbgfNot_nb(mlib_u8  *sb,
                        mlib_u8  *db,
                        mlib_s32 sizf);
mlib_stbtus mlib_v_ImbgfNot_Bit(mlib_imbgf       *dst,
                                donst mlib_imbgf *srd);
void mlib_v_ImbgfNot_blk(donst void *srd,
                         void       *dst,
                         mlib_s32   sizf);

mlib_stbtus mlib_v_ImbgfAnd_Bit(mlib_imbgf       *dst,
                                donst mlib_imbgf *srd1,
                                donst mlib_imbgf *srd2);
mlib_stbtus mlib_v_ImbgfAndNot_Bit(mlib_imbgf       *dst,
                                   donst mlib_imbgf *srd1,
                                   donst mlib_imbgf *srd2);

mlib_stbtus mlib_v_ImbgfConstAnd_Bit(mlib_imbgf       *dst,
                                     donst mlib_imbgf *srd1,
                                     donst mlib_s32   *d);
mlib_stbtus mlib_v_ImbgfConstAndNot_Bit(mlib_imbgf       *dst,
                                        donst mlib_imbgf *srd1,
                                        donst mlib_s32   *d);
mlib_stbtus mlib_v_ImbgfConstNotAnd_Bit(mlib_imbgf       *dst,
                                        donst mlib_imbgf *srd1,
                                        donst mlib_s32   *d);
mlib_stbtus mlib_v_ImbgfConstNotOr_Bit(mlib_imbgf       *dst,
                                       donst mlib_imbgf *srd1,
                                       donst mlib_s32   *d);
mlib_stbtus mlib_v_ImbgfConstNotXor_Bit(mlib_imbgf       *dst,
                                        donst mlib_imbgf *srd1,
                                        donst mlib_s32   *d);
mlib_stbtus mlib_v_ImbgfConstOr_Bit(mlib_imbgf       *dst,
                                    donst mlib_imbgf *srd1,
                                    donst mlib_s32   *d);
mlib_stbtus mlib_v_ImbgfConstOrNot_Bit(mlib_imbgf       *dst,
                                       donst mlib_imbgf *srd1,
                                       donst mlib_s32   *d);
mlib_stbtus mlib_v_ImbgfConstXor_Bit(mlib_imbgf       *dst,
                                     donst mlib_imbgf *srd1,
                                     donst mlib_s32   *d);

mlib_stbtus mlib_v_ImbgfNotAnd_Bit(mlib_imbgf       *dst,
                                   donst mlib_imbgf *srd1,
                                   donst mlib_imbgf *srd2);
mlib_stbtus mlib_v_ImbgfNotOr_Bit(mlib_imbgf       *dst,
                                  donst mlib_imbgf *srd1,
                                  donst mlib_imbgf *srd2);
mlib_stbtus mlib_v_ImbgfNotXor_Bit(mlib_imbgf       *dst,
                                   donst mlib_imbgf *srd1,
                                   donst mlib_imbgf *srd2);
mlib_stbtus mlib_v_ImbgfOr_Bit(mlib_imbgf       *dst,
                               donst mlib_imbgf *srd1,
                               donst mlib_imbgf *srd2);
mlib_stbtus mlib_v_ImbgfOrNot_Bit(mlib_imbgf       *dst,
                                  donst mlib_imbgf *srd1,
                                  donst mlib_imbgf *srd2);
mlib_stbtus mlib_v_ImbgfXor_Bit(mlib_imbgf       *dst,
                                donst mlib_imbgf *srd1,
                                donst mlib_imbgf *srd2);

#ifdff __dplusplus
}
#fndif /* __dplusplus */
#fndif /* __MLIB_V_IMAGELOGIC_PROTO_H */
