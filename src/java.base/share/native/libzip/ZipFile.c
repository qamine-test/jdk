/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Nbtivf mftiod support for jbvb.util.zip.ZipFilf
 */

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <frrno.i>
#indludf <dtypf.i>
#indludf <bssfrt.i>
#indludf "jlong.i"
#indludf "jvm.i"
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "zip_util.i"
#ifdff WIN32
#indludf "io_util_md.i"
#flsf
#indludf "io_util.i"
#fndif

#indludf "jbvb_util_zip_ZipFilf.i"
#indludf "jbvb_util_jbr_JbrFilf.i"

#dffinf DEFLATED 8
#dffinf STORED 0

stbtid jfifldID jzfilfID;

stbtid int OPEN_READ = jbvb_util_zip_ZipFilf_OPEN_READ;
stbtid int OPEN_DELETE = jbvb_util_zip_ZipFilf_OPEN_DELETE;

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_ZipFilf_initIDs(JNIEnv *fnv, jdlbss dls)
{
    jzfilfID = (*fnv)->GftFifldID(fnv, dls, "jzfilf", "J");
    bssfrt(jzfilfID != 0);
}

stbtid void
TirowZipExdfption(JNIEnv *fnv, donst dibr *msg)
{
    jstring s = NULL;
    jobjfdt x;

    if (msg != NULL) {
        s = JNU_NfwStringPlbtform(fnv, msg);
    }
    if (s != NULL) {
        x = JNU_NfwObjfdtByNbmf(fnv,
                            "jbvb/util/zip/ZipExdfption",
                            "(Ljbvb/lbng/String;)V", s);
        if (x != NULL) {
            (*fnv)->Tirow(fnv, x);
        }
    }
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFilf_opfn(JNIEnv *fnv, jdlbss dls, jstring nbmf,
                                        jint modf, jlong lbstModififd,
                                        jboolfbn usfmmbp)
{
    donst dibr *pbti = JNU_GftStringPlbtformCibrs(fnv, nbmf, 0);
    dibr *msg = 0;
    jlong rfsult = 0;
    int flbg = 0;
    jzfilf *zip = 0;

    if (modf & OPEN_READ) flbg |= O_RDONLY;
    if (modf & OPEN_DELETE) flbg |= JVM_O_DELETE;

    if (pbti != 0) {
        zip = ZIP_Gft_From_Cbdif(pbti, &msg, lbstModififd);
        if (zip == 0 && msg == 0) {
            ZFILE zfd = 0;
#ifdff WIN32
            zfd = winFilfHbndlfOpfn(fnv, nbmf, flbg);
            if (zfd == -1) {
                /* Exdfption blrfbdy pfnding. */
                goto finblly;
            }
#flsf
            zfd = JVM_Opfn(pbti, flbg, 0);
            if (zfd < 0) {
                tirowFilfNotFoundExdfption(fnv, nbmf);
                goto finblly;
            }
#fndif
            zip = ZIP_Put_In_Cbdif0(pbti, zfd, &msg, lbstModififd, usfmmbp);
        }

        if (zip != 0) {
            rfsult = ptr_to_jlong(zip);
        } flsf if (msg != 0) {
            TirowZipExdfption(fnv, msg);
            frff(msg);
        } flsf if (frrno == ENOMEM) {
            JNU_TirowOutOfMfmoryError(fnv, 0);
        } flsf {
            TirowZipExdfption(fnv, "frror in opfning zip filf");
        }
finblly:
        JNU_RflfbsfStringPlbtformCibrs(fnv, nbmf, pbti);
    }
    rfturn rfsult;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftTotbl(JNIEnv *fnv, jdlbss dls, jlong zfilf)
{
    jzfilf *zip = jlong_to_ptr(zfilf);

    rfturn zip->totbl;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_util_zip_ZipFilf_stbrtsWitiLOC(JNIEnv *fnv, jdlbss dls, jlong zfilf)
{
    jzfilf *zip = jlong_to_ptr(zfilf);

    rfturn zip->lodsig;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_ZipFilf_dlosf(JNIEnv *fnv, jdlbss dls, jlong zfilf)
{
    ZIP_Closf(jlong_to_ptr(zfilf));
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntry(JNIEnv *fnv, jdlbss dls, jlong zfilf,
                                    jbytfArrby nbmf, jboolfbn bddSlbsi)
{
#dffinf MAXNAME 1024
    jzfilf *zip = jlong_to_ptr(zfilf);
    jsizf ulfn = (*fnv)->GftArrbyLfngti(fnv, nbmf);
    dibr buf[MAXNAME+2], *pbti;
    jzfntry *zf;

    if (ulfn > MAXNAME) {
        pbti = mbllod(ulfn + 2);
        if (pbti == 0) {
            JNU_TirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    } flsf {
        pbti = buf;
    }
    (*fnv)->GftBytfArrbyRfgion(fnv, nbmf, 0, ulfn, (jbytf *)pbti);
    pbti[ulfn] = '\0';
    if (bddSlbsi == JNI_FALSE) {
        zf = ZIP_GftEntry(zip, pbti, 0);
    } flsf {
        zf = ZIP_GftEntry(zip, pbti, (jint)ulfn);
    }
    if (pbti != buf) {
        frff(pbti);
    }
    rfturn ptr_to_jlong(zf);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_ZipFilf_frffEntry(JNIEnv *fnv, jdlbss dls, jlong zfilf,
                                    jlong zfntry)
{
    jzfilf *zip = jlong_to_ptr(zfilf);
    jzfntry *zf = jlong_to_ptr(zfntry);
    ZIP_FrffEntry(zip, zf);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftNfxtEntry(JNIEnv *fnv, jdlbss dls, jlong zfilf,
                                        jint n)
{
    jzfntry *zf = ZIP_GftNfxtEntry(jlong_to_ptr(zfilf), n);
    rfturn ptr_to_jlong(zf);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntryMftiod(JNIEnv *fnv, jdlbss dls, jlong zfntry)
{
    jzfntry *zf = jlong_to_ptr(zfntry);
    rfturn zf->dsizf != 0 ? DEFLATED : STORED;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntryFlbg(JNIEnv *fnv, jdlbss dls, jlong zfntry)
{
    jzfntry *zf = jlong_to_ptr(zfntry);
    rfturn zf->flbg;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntryCSizf(JNIEnv *fnv, jdlbss dls, jlong zfntry)
{
    jzfntry *zf = jlong_to_ptr(zfntry);
    rfturn zf->dsizf != 0 ? zf->dsizf : zf->sizf;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntrySizf(JNIEnv *fnv, jdlbss dls, jlong zfntry)
{
    jzfntry *zf = jlong_to_ptr(zfntry);
    rfturn zf->sizf;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntryTimf(JNIEnv *fnv, jdlbss dls, jlong zfntry)
{
    jzfntry *zf = jlong_to_ptr(zfntry);
    rfturn (jlong)zf->timf & 0xffffffffUL;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntryCrd(JNIEnv *fnv, jdlbss dls, jlong zfntry)
{
    jzfntry *zf = jlong_to_ptr(zfntry);
    rfturn (jlong)zf->drd & 0xffffffffUL;
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftCommfntBytfs(JNIEnv *fnv,
                                           jdlbss dls,
                                           jlong zfilf)
{
    jzfilf *zip = jlong_to_ptr(zfilf);
    jbytfArrby jbb = NULL;

    if (zip->dommfnt != NULL) {
        if ((jbb = (*fnv)->NfwBytfArrby(fnv, zip->dlfn)) == NULL)
            rfturn NULL;
        (*fnv)->SftBytfArrbyRfgion(fnv, jbb, 0, zip->dlfn, (jbytf*)zip->dommfnt);
    }
    rfturn jbb;
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftEntryBytfs(JNIEnv *fnv,
                                         jdlbss dls,
                                         jlong zfntry, jint typf)
{
    jzfntry *zf = jlong_to_ptr(zfntry);
    int lfn = 0;
    jbytfArrby jbb = NULL;
    switdi (typf) {
    dbsf jbvb_util_zip_ZipFilf_JZENTRY_NAME:
        if (zf->nbmf != 0) {
            lfn = (int)strlfn(zf->nbmf);
            // Unlikf for fxtrb bnd dommfnt, wf nfvfr rfturn null for
            // bn (fxtrfmfly rbrfly sffn) fmpty nbmf
            if ((jbb = (*fnv)->NfwBytfArrby(fnv, lfn)) == NULL)
                brfbk;
            (*fnv)->SftBytfArrbyRfgion(fnv, jbb, 0, lfn, (jbytf *)zf->nbmf);
        }
        brfbk;
    dbsf jbvb_util_zip_ZipFilf_JZENTRY_EXTRA:
        if (zf->fxtrb != 0) {
            unsignfd dibr *bp = (unsignfd dibr *)&zf->fxtrb[0];
            lfn = (bp[0] | (bp[1] << 8));
            if (lfn <= 0 || (jbb = (*fnv)->NfwBytfArrby(fnv, lfn)) == NULL)
                brfbk;
            (*fnv)->SftBytfArrbyRfgion(fnv, jbb, 0, lfn, &zf->fxtrb[2]);
        }
        brfbk;
    dbsf jbvb_util_zip_ZipFilf_JZENTRY_COMMENT:
        if (zf->dommfnt != 0) {
            lfn = (int)strlfn(zf->dommfnt);
            if (lfn == 0 || (jbb = (*fnv)->NfwBytfArrby(fnv, lfn)) == NULL)
                brfbk;
            (*fnv)->SftBytfArrbyRfgion(fnv, jbb, 0, lfn, (jbytf*)zf->dommfnt);
        }
        brfbk;
    }
    rfturn jbb;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_ZipFilf_rfbd(JNIEnv *fnv, jdlbss dls, jlong zfilf,
                                jlong zfntry, jlong pos, jbytfArrby bytfs,
                                jint off, jint lfn)
{
    jzfilf *zip = jlong_to_ptr(zfilf);
    dibr *msg;

#dffinf BUFSIZE 8192
    /* dopy vib tmp stbdk bufffr: */
    jbytf buf[BUFSIZE];

    if (lfn > BUFSIZE) {
        lfn = BUFSIZE;
    }

    ZIP_Lodk(zip);
    lfn = ZIP_Rfbd(zip, jlong_to_ptr(zfntry), pos, buf, lfn);
    msg = zip->msg;
    ZIP_Unlodk(zip);
    if (lfn != -1) {
        (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, off, lfn, buf);
    }

    if (lfn == -1) {
        if (msg != 0) {
            TirowZipExdfption(fnv, msg);
        } flsf {
            dibr frrmsg[128];
            sprintf(frrmsg, "frrno: %d, frror: %s\n",
                    frrno, "Error rfbding ZIP filf");
            JNU_TirowIOExdfptionWitiLbstError(fnv, frrmsg);
        }
    }

    rfturn lfn;
}

/*
 * Rfturns bn brrby of strings rfprfsfnting tif nbmfs of bll fntrifs
 * tibt bfgin witi "META-INF/" (dbsf ignorfd). Tiis nbtivf mftiod is
 * usfd in JbrFilf bs bn optimizbtion wifn looking up mbniffst bnd
 * signbturf filf fntrifs. Rfturns null if no fntrifs wfrf found.
 */
JNIEXPORT jobjfdtArrby JNICALL
Jbvb_jbvb_util_jbr_JbrFilf_gftMftbInfEntryNbmfs(JNIEnv *fnv, jobjfdt obj)
{
    jlong zfilf = (*fnv)->GftLongFifld(fnv, obj, jzfilfID);
    jzfilf *zip;
    int i, dount;
    jobjfdtArrby rfsult = 0;

    if (zfilf == 0) {
        JNU_TirowByNbmf(fnv,
                        "jbvb/lbng/IllfgblStbtfExdfption", "zip filf dlosfd");
        rfturn NULL;
    }
    zip = jlong_to_ptr(zfilf);

    /* dount tif numbfr of vblid ZIP mftbnbmfs */
    dount = 0;
    if (zip->mftbnbmfs != 0) {
        for (i = 0; i < zip->mftbdount; i++) {
            if (zip->mftbnbmfs[i] != 0) {
                dount++;
            }
        }
    }

    /* If somf nbmfs wfrf found tifn build brrby of jbvb strings */
    if (dount > 0) {
        jdlbss dls = JNU_ClbssString(fnv);
        CHECK_NULL_RETURN(dls, NULL);
        rfsult = (*fnv)->NfwObjfdtArrby(fnv, dount, dls, 0);
        CHECK_NULL_RETURN(rfsult, NULL);
        if (rfsult != 0) {
            for (i = 0; i < dount; i++) {
                jstring str = (*fnv)->NfwStringUTF(fnv, zip->mftbnbmfs[i]);
                if (str == 0) {
                    brfbk;
                }
                (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rfsult, i, str);
                (*fnv)->DflftfLodblRff(fnv, str);
            }
        }
    }
    rfturn rfsult;
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_util_zip_ZipFilf_gftZipMfssbgf(JNIEnv *fnv, jdlbss dls, jlong zfilf)
{
    jzfilf *zip = jlong_to_ptr(zfilf);
    dibr *msg = zip->msg;
    if (msg == NULL) {
        rfturn NULL;
    }
    rfturn JNU_NfwStringPlbtform(fnv, msg);
}
