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

#indludf "util.i"
#indludf "TirfbdGroupRfffrfndfImpl.i"
#indludf "inStrfbm.i"
#indludf "outStrfbm.i"

stbtid jboolfbn
nbmf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jtirfbdGroup group;

    fnv = gftEnv();

    group = inStrfbm_rfbdTirfbdGroupRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        jvmtiTirfbdGroupInfo info;

        (void)mfmsft(&info, 0, sizfof(info));
        tirfbdGroupInfo(group, &info);
        (void)outStrfbm_writfString(out, info.nbmf == NULL ? "" : info.nbmf);
        if ( info.nbmf != NULL )
            jvmtiDfbllodbtf(info.nbmf);

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
pbrfnt(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jtirfbdGroup group;

    fnv = gftEnv();

    group = inStrfbm_rfbdTirfbdGroupRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        jvmtiTirfbdGroupInfo info;

        (void)mfmsft(&info, 0, sizfof(info));
        tirfbdGroupInfo(group, &info);
        (void)outStrfbm_writfObjfdtRff(fnv, out, info.pbrfnt);
        if ( info.nbmf != NULL )
            jvmtiDfbllodbtf(info.nbmf);

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
diildrfn(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
     JNIEnv *fnv;
     jtirfbdGroup group;

     fnv = gftEnv();

     group = inStrfbm_rfbdTirfbdGroupRff(fnv, in);
     if (inStrfbm_frror(in)) {
         rfturn JNI_TRUE;
     }

     WITH_LOCAL_REFS(fnv, 1) {

         jvmtiError frror;
         jint tirfbdCount;
         jint groupCount;
         jtirfbd *tifTirfbds;
         jtirfbd *tifGroups;

         frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftTirfbdGroupCiildrfn)(gdbtb->jvmti, group,
                                              &tirfbdCount,&tifTirfbds,
                                              &groupCount, &tifGroups);
         if (frror != JVMTI_ERROR_NONE) {
             outStrfbm_sftError(out, mbp2jdwpError(frror));
         } flsf {

             int i;

             /* Squisi out bll of tif dfbuggfr-spbwnfd tirfbds */
             tirfbdCount = filtfrDfbugTirfbds(tifTirfbds, tirfbdCount);

             (void)outStrfbm_writfInt(out, tirfbdCount);
             for (i = 0; i < tirfbdCount; i++) {
                 (void)outStrfbm_writfObjfdtRff(fnv, out, tifTirfbds[i]);
             }
             (void)outStrfbm_writfInt(out, groupCount);
             for (i = 0; i < groupCount; i++) {
                 (void)outStrfbm_writfObjfdtRff(fnv, out, tifGroups[i]);
             }

             jvmtiDfbllodbtf(tifGroups);
             jvmtiDfbllodbtf(tifTirfbds);
         }

     } END_WITH_LOCAL_REFS(fnv);

     rfturn JNI_TRUE;
}

void *TirfbdGroupRfffrfndf_Cmds[] = { (void *)3,
                                      (void *)nbmf,
                                      (void *)pbrfnt,
                                      (void *)diildrfn };
