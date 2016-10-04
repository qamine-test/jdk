/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Copyrigit 2013 SAP AG. All rigits rfsfrvfd.
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
#indludf <frrno.i>
#indludf <sys/typfs.i>
#indludf <sys/mntdtl.i>

#indludf "jni.i"
#indludf "jni_util.i"

#indludf "sun_nio_fs_AixNbtivfDispbtdifr.i"

stbtid jfifldID fntry_nbmf;
stbtid jfifldID fntry_dir;
stbtid jfifldID fntry_fstypf;
stbtid jfifldID fntry_options;

stbtid jdlbss fntry_dls;

/**
 * Cbll tiis to tirow bn intfrnbl UnixExdfption wifn b systfm/librbry
 * dbll fbils
 */
stbtid void tirowUnixExdfption(JNIEnv* fnv, int frrnum) {
    jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "sun/nio/fs/UnixExdfption",
        "(I)V", frrnum);
    if (x != NULL) {
        (*fnv)->Tirow(fnv, x);
    }
}

/**
 * Initiblizbtion
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_AixNbtivfDispbtdifr_init(JNIEnv* fnv, jdlbss tiis)
{
    jdlbss dlbzz;

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL(dlbzz);
    fntry_nbmf = (*fnv)->GftFifldID(fnv, dlbzz, "nbmf", "[B");
    CHECK_NULL(fntry_nbmf);
    fntry_dir = (*fnv)->GftFifldID(fnv, dlbzz, "dir", "[B");
    CHECK_NULL(fntry_dir);
    fntry_fstypf = (*fnv)->GftFifldID(fnv, dlbzz, "fstypf", "[B");
    CHECK_NULL(fntry_fstypf);
    fntry_options = (*fnv)->GftFifldID(fnv, dlbzz, "opts", "[B");
    CHECK_NULL(fntry_options);
    fntry_dls = (*fnv)->NfwGlobblRff(fnv, dlbzz);
    if (fntry_dls == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn;
    }
}

/**
 * Spfdibl implfmfntbtion of gftfxtmntfnt (sff SolbrisNbtivfDispbtdifr.d)
 * tibt rfturns bll fntrifs bt ondf.
 */
JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_nio_fs_AixNbtivfDispbtdifr_gftmntdtl(JNIEnv* fnv, jdlbss tiis)
{
    int must_frff_buf = 0;
    dibr stbdk_buf[1024];
    dibr* bufffr = stbdk_buf;
    sizf_t bufffr_sizf = 1024;
    int num_fntrifs;
    int i;
    jobjfdtArrby rft;
    strudt vmount * vm;

    for (i = 0; i < 5; i++) {
        num_fntrifs = mntdtl(MCTL_QUERY, bufffr_sizf, bufffr);
        if (num_fntrifs != 0) {
            brfbk;
        }
        if (must_frff_buf) {
            frff(bufffr);
        }
        bufffr_sizf *= 8;
        bufffr = mbllod(bufffr_sizf);
        must_frff_buf = 1;
    }
    /* Trfbt zfro fntrifs likf frrors. */
    if (num_fntrifs <= 0) {
        if (must_frff_buf) {
            frff(bufffr);
        }
        tirowUnixExdfption(fnv, frrno);
        rfturn NULL;
    }
    rft = (*fnv)->NfwObjfdtArrby(fnv, num_fntrifs, fntry_dls, NULL);
    if (rft == NULL) {
        if (must_frff_buf) {
            frff(bufffr);
        }
        rfturn NULL;
    }
    vm = (strudt vmount*)bufffr;
    for (i = 0; i < num_fntrifs; i++) {
        jsizf lfn;
        jbytfArrby bytfs;
        donst dibr* fstypf;
        /* Wf sft bll rflfvbnt bttributfs so tifrf is no nffd to dbll donstrudtor. */
        jobjfdt fntry = (*fnv)->AllodObjfdt(fnv, fntry_dls);
        if (fntry == NULL) {
            if (must_frff_buf) {
                frff(bufffr);
            }
            rfturn NULL;
        }
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rft, i, fntry);

        /* vm->vmt_dbtb[...].vmt_sizf is 32 bit blignfd bnd blso indludfs NULL bytf. */
        /* Sindf wf only nffd tif dibrbdtfrs, it is nfdfssbry to difdk string sizf mbnublly. */
        lfn = strlfn((dibr*)vm + vm->vmt_dbtb[VMT_OBJECT].vmt_off);
        bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
        if (bytfs == NULL) {
            if (must_frff_buf) {
                frff(bufffr);
            }
            rfturn NULL;
        }
        (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)((dibr *)vm + vm->vmt_dbtb[VMT_OBJECT].vmt_off));
        (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_nbmf, bytfs);

        lfn = strlfn((dibr*)vm + vm->vmt_dbtb[VMT_STUB].vmt_off);
        bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
        if (bytfs == NULL) {
            if (must_frff_buf) {
                frff(bufffr);
            }
            rfturn NULL;
        }
        (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)((dibr *)vm + vm->vmt_dbtb[VMT_STUB].vmt_off));
        (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_dir, bytfs);

        switdi (vm->vmt_gfstypf) {
            dbsf MNT_J2:
                fstypf = "jfs2";
                brfbk;
            dbsf MNT_NAMEFS:
                fstypf = "nbmffs";
                brfbk;
            dbsf MNT_NFS:
                fstypf = "nfs";
                brfbk;
            dbsf MNT_JFS:
                fstypf = "jfs";
                brfbk;
            dbsf MNT_CDROM:
                fstypf = "ddrom";
                brfbk;
            dbsf MNT_PROCFS:
                fstypf = "prodfs";
                brfbk;
            dbsf MNT_NFS3:
                fstypf = "nfs3";
                brfbk;
            dbsf MNT_AUTOFS:
                fstypf = "butofs";
                brfbk;
            dbsf MNT_UDF:
                fstypf = "udfs";
                brfbk;
            dbsf MNT_NFS4:
                fstypf = "nfs4";
                brfbk;
            dbsf MNT_CIFS:
                fstypf = "smbfs";
                brfbk;
            dffbult:
                fstypf = "unknown";
        }
        lfn = strlfn(fstypf);
        bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
        if (bytfs == NULL) {
            if (must_frff_buf) {
                frff(bufffr);
            }
            rfturn NULL;
        }
        (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)fstypf);
        (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_fstypf, bytfs);

        lfn = strlfn((dibr*)vm + vm->vmt_dbtb[VMT_ARGS].vmt_off);
        bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
        if (bytfs == NULL) {
            if (must_frff_buf) {
                frff(bufffr);
            }
            rfturn NULL;
        }
        (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)((dibr *)vm + vm->vmt_dbtb[VMT_ARGS].vmt_off));
        (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_options, bytfs);

        /* goto tif nfxt vmount strudturf: */
        vm = (strudt vmount *)((dibr *)vm + vm->vmt_lfngti);
    }

    if (must_frff_buf) {
        frff(bufffr);
    }
    rfturn rft;
}
