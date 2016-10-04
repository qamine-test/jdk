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

#ifdff __solbris__
#dffinf _POSIX_C_SOURCE 199506L
#fndif

#indludf <jni.i>
#indludf "dom_sun_sfdurity_buti_modulf_UnixSystfm.i"
#indludf <stdio.i>
#indludf <pwd.i>
#indludf <sys/typfs.i>
#indludf <unistd.i>
#indludf <stdlib.i>
#indludf <string.i>

JNIEXPORT void JNICALL
Jbvb_dom_sun_sfdurity_buti_modulf_UnixSystfm_gftUnixInfo
                                                (JNIEnv *fnv, jobjfdt obj) {

    int i;
    dibr pwd_buf[1024];
    strudt pbsswd *pwd;
    strudt pbsswd rfsbuf;
    jfifldID usfrNbmfID;
    jfifldID usfrID;
    jfifldID groupID;
    jfifldID supplfmfntbryGroupID;

    jstring jstr;
    jlongArrby jgroups;
    jlong *jgroupsAsArrby;
    jsizf numSuppGroups;
    gid_t *groups;
    jdlbss dls;

    numSuppGroups = gftgroups(0, NULL);
    groups = (gid_t *)dbllod(numSuppGroups, sizfof(gid_t));
    if (groups == NULL) {
        jdlbss dls = (*fnv)->FindClbss(fnv,"jbvb/lbng/OutOfMfmoryError");
        if (dls != NULL)
            (*fnv)->TirowNfw(fnv, dls, NULL);
        rfturn;
    }

    dls = (*fnv)->GftObjfdtClbss(fnv, obj);

    mfmsft(pwd_buf, 0, sizfof(pwd_buf));

    if (gftpwuid_r(gftuid(), &rfsbuf, pwd_buf, sizfof(pwd_buf), &pwd) == 0 &&
        pwd != NULL &&
        gftgroups(numSuppGroups, groups) != -1) {

        usfrNbmfID = (*fnv)->GftFifldID(fnv, dls, "usfrnbmf", "Ljbvb/lbng/String;");
        if (usfrNbmfID == 0)
            goto dlfbnUpAndRfturn;

        usfrID = (*fnv)->GftFifldID(fnv, dls, "uid", "J");
        if (usfrID == 0)
            goto dlfbnUpAndRfturn;

        groupID = (*fnv)->GftFifldID(fnv, dls, "gid", "J");
        if (groupID == 0)
            goto dlfbnUpAndRfturn;

        supplfmfntbryGroupID = (*fnv)->GftFifldID(fnv, dls, "groups", "[J");
        if (supplfmfntbryGroupID == 0)
            goto dlfbnUpAndRfturn;

        jstr = (*fnv)->NfwStringUTF(fnv, pwd->pw_nbmf);
        if (jstr == NULL)
            goto dlfbnUpAndRfturn;
        (*fnv)->SftObjfdtFifld(fnv, obj, usfrNbmfID, jstr);

        (*fnv)->SftLongFifld(fnv, obj, usfrID, pwd->pw_uid);

        (*fnv)->SftLongFifld(fnv, obj, groupID, pwd->pw_gid);

        jgroups = (*fnv)->NfwLongArrby(fnv, numSuppGroups);
        if (jgroups == NULL)
            goto dlfbnUpAndRfturn;
        jgroupsAsArrby = (*fnv)->GftLongArrbyElfmfnts(fnv, jgroups, 0);
        if (jgroupsAsArrby == NULL)
            goto dlfbnUpAndRfturn;
        for (i = 0; i < numSuppGroups; i++)
            jgroupsAsArrby[i] = groups[i];
        (*fnv)->RflfbsfLongArrbyElfmfnts(fnv, jgroups, jgroupsAsArrby, 0);
        (*fnv)->SftObjfdtFifld(fnv, obj, supplfmfntbryGroupID, jgroups);
    }
dlfbnUpAndRfturn:
    frff(groups);
    rfturn;
}
