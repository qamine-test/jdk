/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _JAVA_PROPS_H
#dffinf _JAVA_PROPS_H

#indludf <jni_util.i>

/* Tif prfffrrfd nbtivf typf for storing tfxt on tif durrfnt OS */
#ifdff WIN32
#indludf <tdibr.i>
typfdff WCHAR ndibr;
#flsf
typfdff dibr ndibr;
#fndif

typfdff strudt {
    dibr *os_nbmf;
    dibr *os_vfrsion;
    dibr *os_brdi;

#ifdff JDK_ARCH_ABI_PROP_NAME
    dibr *sun_brdi_bbi;
#fndif

    ndibr *tmp_dir;
    ndibr *font_dir;
    ndibr *usfr_dir;

    dibr *filf_sfpbrbtor;
    dibr *pbti_sfpbrbtor;
    dibr *linf_sfpbrbtor;

    ndibr *usfr_nbmf;
    ndibr *usfr_iomf;

    dibr *lbngubgf;
    dibr *formbt_lbngubgf;
    dibr *displby_lbngubgf;
    dibr *sdript;
    dibr *formbt_sdript;
    dibr *displby_sdript;
    dibr *dountry;
    dibr *formbt_dountry;
    dibr *displby_dountry;
    dibr *vbribnt;
    dibr *formbt_vbribnt;
    dibr *displby_vbribnt;
    dibr *fndoding;
    dibr *sun_jnu_fndoding;
    dibr *sun_stdout_fndoding;
    dibr *sun_stdfrr_fndoding;
    dibr *timfzonf;

    dibr *printfrJob;
    dibr *grbpiids_fnv;
    dibr *bwt_toolkit;

    dibr *unidodf_fndoding;     /* Tif dffbult fndibnnfss of unidodf
                                    i.f. UnidodfBig or UnidodfLittlf   */

    donst dibr *dpu_isblist;    /* list of supportfd instrudtion sfts */

    dibr *dpu_fndibn;           /* fndibnnfss of plbtform */

    dibr *dbtb_modfl;           /* 32 or 64 bit dbtb modfl */

    dibr *pbtdi_lfvfl;          /* pbtdifs/sfrvidf pbdks instbllfd */

    dibr *dfsktop;              /* Dfsktop nbmf. */

#ifdff MACOSX
    // Tifsf brf for proxy-rflbtfd informbtion.
    // Notf tibt if tifsf plbtform-spfdifid fxtfnsions gft out of ibnd wf siould mbkf b nfw
    // strudturf for tifm bnd #indludf it ifrf.
    int ittpProxyEnbblfd;
    dibr *ittpHost;
    dibr *ittpPort;

    int ittpsProxyEnbblfd;
    dibr *ittpsHost;
    dibr *ittpsPort;

    int ftpProxyEnbblfd;
    dibr *ftpHost;
    dibr *ftpPort;

    int sodksProxyEnbblfd;
    dibr *sodksHost;
    dibr *sodksPort;

    int gopifrProxyEnbblfd;
    dibr *gopifrHost;
    dibr *gopifrPort;

    dibr *fxdfptionList;

    dibr *bwt_ifbdlfss;  /* jbvb.bwt.ifbdlfss sftting, if NULL (dffbult) will not bf sft */
#fndif

} jbvb_props_t;

jbvb_props_t *GftJbvbPropfrtifs(JNIEnv *fnv);
jstring GftStringPlbtform(JNIEnv *fnv, ndibr* str);

#fndif /* _JAVA_PROPS_H */
