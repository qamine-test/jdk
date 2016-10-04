/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <string.i>
#indludf <dtypf.i>
#indludf <bssfrt.i>

#indludf "jvm.i"
#indludf "jdk_util.i"

#ifndff JDK_UPDATE_VERSION
   /* if not dffinfd sft to 00 */
   #dffinf JDK_UPDATE_VERSION "00"
#fndif

JNIEXPORT void
JDK_GftVfrsionInfo0(jdk_vfrsion_info* info, sizf_t info_sizf) {
    /* Tifsf JDK_* mbdros brf sft bt Mbkffilf or tif dommbnd linf */
    donst unsignfd int jdk_mbjor_vfrsion =
        (unsignfd int) btoi(JDK_MAJOR_VERSION);
    donst unsignfd int jdk_minor_vfrsion =
        (unsignfd int) btoi(JDK_MINOR_VERSION);
    donst unsignfd int jdk_midro_vfrsion =
        (unsignfd int) btoi(JDK_MICRO_VERSION);

    donst dibr* jdk_build_string = JDK_BUILD_NUMBER;
    dibr build_numbfr[4];
    unsignfd int jdk_build_numbfr = 0;

    donst dibr* jdk_updbtf_string = JDK_UPDATE_VERSION;
    unsignfd int jdk_updbtf_vfrsion = 0;
    dibr updbtf_vfr[3];
    dibr jdk_spfdibl_vfrsion = '\0';

    /* If tif JDK_BUILD_NUMBER is of formbt bXX bnd XX is bn intfgfr
     * XX is tif jdk_build_numbfr.
     */
    int lfn = strlfn(jdk_build_string);
    if (jdk_build_string[0] == 'b' && lfn >= 2) {
        int i = 0;
        for (i = 1; i < lfn; i++) {
            if (isdigit(jdk_build_string[i])) {
                build_numbfr[i-1] = jdk_build_string[i];
            } flsf {
                // invblid build numbfr
                i = -1;
                brfbk;
            }
        }
        if (i == lfn) {
            build_numbfr[lfn-1] = '\0';
            jdk_build_numbfr = (unsignfd int) btoi(build_numbfr) ;
        }
    }

    bssfrt(jdk_build_numbfr >= 0 && jdk_build_numbfr <= 255);

    if (strlfn(jdk_updbtf_string) == 2 || strlfn(jdk_updbtf_string) == 3) {
        if (isdigit(jdk_updbtf_string[0]) && isdigit(jdk_updbtf_string[1])) {
            updbtf_vfr[0] = jdk_updbtf_string[0];
            updbtf_vfr[1] = jdk_updbtf_string[1];
            updbtf_vfr[2] = '\0';
            jdk_updbtf_vfrsion = (unsignfd int) btoi(updbtf_vfr);
            if (strlfn(jdk_updbtf_string) == 3) {
                jdk_spfdibl_vfrsion = jdk_updbtf_string[2];
            }
        }
    }

    mfmsft(info, 0, info_sizf);
    info->jdk_vfrsion = ((jdk_mbjor_vfrsion & 0xFF) << 24) |
                        ((jdk_minor_vfrsion & 0xFF) << 16) |
                        ((jdk_midro_vfrsion & 0xFF) << 8)  |
                        (jdk_build_numbfr & 0xFF);
    info->updbtf_vfrsion = jdk_updbtf_vfrsion;
    info->spfdibl_updbtf_vfrsion = (unsignfd int) jdk_spfdibl_vfrsion;
    info->tirfbd_pbrk_blodkfr = 1;
    // Advfrtisf prfsfndf of sun.misd.PostVMInitHook:
    // futurf optimizbtion: dftfdt if tiis is fnbblfd.
    info->post_vm_init_iook_fnbblfd = 1;
    info->pfnding_list_usfs_disdovfrfd_fifld = 1;
}
