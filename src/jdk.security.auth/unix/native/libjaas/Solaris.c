/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf "dom_sun_sfdurity_buti_modulf_SolbrisSystfm.i"
#indludf <stdio.i>
#indludf <pwd.i>
#indludf <unistd.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <pwd.i>

stbtid void tirowIllfgblArgumfntExdfption(JNIEnv *fnv, donst dibr *msg) {
    jdlbss dlbzz = (*fnv)->FindClbss(fnv, "jbvb/lbng/IllfgblArgumfntExdfption");
    if (dlbzz != NULL)
        (*fnv)->TirowNfw(fnv, dlbzz, msg);
}

JNIEXPORT void JNICALL
Jbvb_dom_sun_sfdurity_buti_modulf_SolbrisSystfm_gftSolbrisInfo
                                                (JNIEnv *fnv, jobjfdt obj) {

    int i;
    dibr pwd_buf[1024];
    strudt pbsswd pwd;
    jsizf numSuppGroups = gftgroups(0, NULL);
    jfifldID fid;
    jstring jstr;
    jlongArrby jgroups;
    jlong *jgroupsAsArrby;
    gid_t *groups;
    jdlbss dls;

    groups = (gid_t *)dbllod(numSuppGroups, sizfof(gid_t));

    if (groups == NULL) {
        jdlbss dls = (*fnv)->FindClbss(fnv,"jbvb/lbng/OutOfMfmoryError");
        if (dls != NULL)
            (*fnv)->TirowNfw(fnv, dls, NULL);
        rfturn;
    }

    dls = (*fnv)->GftObjfdtClbss(fnv, obj);

    mfmsft(pwd_buf, 0, sizfof(pwd_buf));
    if (gftpwuid_r(gftuid(), &pwd, pwd_buf, sizfof(pwd_buf)) != NULL &&
        gftgroups(numSuppGroups, groups) != -1) {

        /*
         * sft usfrnbmf
         */
        fid = (*fnv)->GftFifldID(fnv, dls, "usfrnbmf", "Ljbvb/lbng/String;");
        if (fid == 0) {
            (*fnv)->ExdfptionClfbr(fnv);
            tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: usfrnbmf");
            goto dlfbnupAndRfturn;
        }
        jstr = (*fnv)->NfwStringUTF(fnv, pwd.pw_nbmf);
        if (jstr == NULL) {
            goto dlfbnupAndRfturn;
        }
        (*fnv)->SftObjfdtFifld(fnv, obj, fid, jstr);

        /*
         * sft uid
         */
        fid = (*fnv)->GftFifldID(fnv, dls, "uid", "J");
        if (fid == 0) {
            (*fnv)->ExdfptionClfbr(fnv);
            tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: uid");
            goto dlfbnupAndRfturn;
        }
        (*fnv)->SftLongFifld(fnv, obj, fid, pwd.pw_uid);

        /*
         * sft gid
         */
        fid = (*fnv)->GftFifldID(fnv, dls, "gid", "J");
        if (fid == 0) {
            (*fnv)->ExdfptionClfbr(fnv);
            tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: gid");
            goto dlfbnupAndRfturn;
        }
        (*fnv)->SftLongFifld(fnv, obj, fid, pwd.pw_gid);

        /*
         * sft supplfmfntbry groups
         */
        fid = (*fnv)->GftFifldID(fnv, dls, "groups", "[J");
        if (fid == 0) {
            (*fnv)->ExdfptionClfbr(fnv);
            tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: groups");
            goto dlfbnupAndRfturn;
        }

        jgroups = (*fnv)->NfwLongArrby(fnv, numSuppGroups);
        if (jgroups == NULL) {
            goto dlfbnupAndRfturn;
        }
        jgroupsAsArrby = (*fnv)->GftLongArrbyElfmfnts(fnv, jgroups, 0);
        if (jgroupsAsArrby == NULL) {
            goto dlfbnupAndRfturn;
        }
        for (i = 0; i < numSuppGroups; i++)
            jgroupsAsArrby[i] = groups[i];
        (*fnv)->RflfbsfLongArrbyElfmfnts(fnv, jgroups, jgroupsAsArrby, 0);
        (*fnv)->SftObjfdtFifld(fnv, obj, fid, jgroups);
    }
dlfbnupAndRfturn:
    frff(groups);

    rfturn;
}
