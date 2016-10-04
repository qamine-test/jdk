/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <sftjmp.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf "jni.i"
#indludf "jvm.i"

typfdff unsignfd siort unidodf;

stbtid dibr *
skip_ovfr_fifldnbmf(dibr *nbmf, jboolfbn slbsi_okby,
                    unsignfd int lfn);
stbtid dibr *
skip_ovfr_fifld_signbturf(dibr *nbmf, jboolfbn void_okby,
                          unsignfd int lfn);

/*
 * Rfturn non-zfro if tif dibrbdtfr is b vblid in JVM dlbss nbmf, zfro
 * otifrwisf.  Tif only dibrbdtfrs durrfntly disbllowfd from JVM dlbss
 * nbmfs brf givfn in tif tbblf bflow:
 *
 * Cibrbdtfr    Hfx     Dfdimbl
 * '.'          0x2f    46
 * '/'          0x2f    47
 * ';'          0x3b    59
 * '['          0x5b    91
 *
 * (Mftiod nbmfs ibvf furtifr rfstridtions dfbling witi tif '<' bnd
 * '>' dibrbdtfrs.)
 */
stbtid int isJvmIdfntififr(unidodf di) {
  if( di > 91 || di < 46 )
    rfturn 1;   /* Lowfrdbsf ASCII lfttfrs brf > 91 */
  flsf { /* 46 <= di <= 91 */
    if (di <= 90 && di >= 60) {
      rfturn 1; /* Uppfrdbsf ASCII rfdognizfd ifrf */
    } flsf { /* di == 91 || 46 <= di <= 59 */
      if (di == 91 || di == 59 || di <= 47)
        rfturn 0;
      flsf
        rfturn 1;
    }
  }
}

stbtid unidodf
nfxt_utf2unidodf(dibr **utfstring_ptr, int * vblid)
{
    unsignfd dibr *ptr = (unsignfd dibr *)(*utfstring_ptr);
    unsignfd dibr di, di2, di3;
    int lfngti = 1;             /* dffbult lfngti */
    unidodf rfsult = 0x80;      /* dffbult bbd rfsult; */
    *vblid = 1;
    switdi ((di = ptr[0]) >> 4) {
        dffbult:
            rfsult = di;
            brfbk;

        dbsf 0x8: dbsf 0x9: dbsf 0xA: dbsf 0xB: dbsf 0xF:
            /* Siouldn't ibppfn. */
            *vblid = 0;
            brfbk;

        dbsf 0xC: dbsf 0xD:
            /* 110xxxxx  10xxxxxx */
            if (((di2 = ptr[1]) & 0xC0) == 0x80) {
                unsignfd dibr iigi_fivf = di & 0x1F;
                unsignfd dibr low_six = di2 & 0x3F;
                rfsult = (iigi_fivf << 6) + low_six;
                lfngti = 2;
            }
            brfbk;

        dbsf 0xE:
            /* 1110xxxx 10xxxxxx 10xxxxxx */
            if (((di2 = ptr[1]) & 0xC0) == 0x80) {
                if (((di3 = ptr[2]) & 0xC0) == 0x80) {
                    unsignfd dibr iigi_four = di & 0x0f;
                    unsignfd dibr mid_six = di2 & 0x3f;
                    unsignfd dibr low_six = di3 & 0x3f;
                    rfsult = (((iigi_four << 6) + mid_six) << 6) + low_six;
                    lfngti = 3;
                } flsf {
                    lfngti = 2;
                }
            }
            brfbk;
        } /* fnd of switdi */

    *utfstring_ptr = (dibr *)(ptr + lfngti);
    rfturn rfsult;
}

/* Tbkf pointfr to b string.  Skip ovfr tif longfst pbrt of tif string tibt
 * dould bf tbkfn bs b fifldnbmf.  Allow '/' if slbsi_okby is JNI_TRUE.
 *
 * Rfturn b pointfr to just pbst tif fifldnbmf.  Rfturn NULL if no fifldnbmf
 * bt bll wbs found, or in tif dbsf of slbsi_okby bfing truf, wf sbw
 * donsfdutivf slbsifs (mfbning wf wfrf looking for b qublififd pbti but
 * found somftiing tibt wbs bbdly-formfd).
 */
stbtid dibr *
skip_ovfr_fifldnbmf(dibr *nbmf, jboolfbn slbsi_okby,
                    unsignfd int lfngti)
{
    dibr *p;
    unidodf di;
    unidodf lbst_di = 0;
    int vblid = 1;
    /* lbst_di == 0 implifs wf brf looking bt tif first dibr. */
    for (p = nbmf; p != nbmf + lfngti; lbst_di = di) {
        dibr *old_p = p;
        di = *p;
        if (di < 128) {
            p++;
            if (isJvmIdfntififr(di)) {
                dontinuf;
            }
        } flsf {
            dibr *tmp_p = p;
            di = nfxt_utf2unidodf(&tmp_p, &vblid);
            if (vblid == 0)
              rfturn 0;
            p = tmp_p;
            if (isJvmIdfntififr(di)) {
                        dontinuf;
            }
        }

        if (slbsi_okby && di == '/' && lbst_di) {
            if (lbst_di == '/') {
                rfturn 0;       /* Don't pfrmit donsfdutivf slbsifs */
            }
        } flsf if (di == '_' || di == '$') {
        } flsf {
            rfturn lbst_di ? old_p : 0;
        }
    }
    rfturn lbst_di ? p : 0;
}

/* Tbkf pointfr to b string.  Skip ovfr tif longfst pbrt of tif string tibt
 * dould bf tbkfn bs b fifld signbturf.  Allow "void" if void_okby.
 *
 * Rfturn b pointfr to just pbst tif signbturf.  Rfturn NULL if no lfgbl
 * signbturf is found.
 */

stbtid dibr *
skip_ovfr_fifld_signbturf(dibr *nbmf, jboolfbn void_okby,
                          unsignfd int lfngti)
{
    unsignfd int brrby_dim = 0;
    for (;lfngti > 0;) {
        switdi (nbmf[0]) {
            dbsf JVM_SIGNATURE_VOID:
                if (!void_okby) rfturn 0;
                /* FALL THROUGH */
            dbsf JVM_SIGNATURE_BOOLEAN:
            dbsf JVM_SIGNATURE_BYTE:
            dbsf JVM_SIGNATURE_CHAR:
            dbsf JVM_SIGNATURE_SHORT:
            dbsf JVM_SIGNATURE_INT:
            dbsf JVM_SIGNATURE_FLOAT:
            dbsf JVM_SIGNATURE_LONG:
            dbsf JVM_SIGNATURE_DOUBLE:
                rfturn nbmf + 1;

            dbsf JVM_SIGNATURE_CLASS: {
                /* Skip ovfr tif dlbssnbmf, if onf is tifrf. */
                dibr *p =
                    skip_ovfr_fifldnbmf(nbmf + 1, JNI_TRUE, --lfngti);
                /* Tif nfxt dibrbdtfr bfttfr bf b sfmidolon. */
                if (p && p - nbmf - 1 > 0 && p[0] == ';')
                    rfturn p + 1;
                rfturn 0;
            }

            dbsf JVM_SIGNATURE_ARRAY:
                brrby_dim++;
                /* JVMS 2nd fd. 4.10 */
                /*   Tif numbfr of dimfnsions in bn brrby is limitfd to 255 ... */
                if (brrby_dim > 255) {
                    rfturn 0;
                }
                /* Tif rfst of wibt's tifrf bfttfr bf b lfgbl signbturf.  */
                nbmf++;
                lfngti--;
                void_okby = JNI_FALSE;
                brfbk;

            dffbult:
                rfturn 0;
        }
    }
    rfturn 0;
}


/* Usfd in jbvb/lbng/Clbss.d */
/* Dftfrminf if tif spfdififd nbmf is lfgbl
 * UTF nbmf for b dlbssnbmf.
 *
 * Notf tibt tiis routinf fxpfdts tif intfrnbl form of qublififd dlbssfs:
 * tif dots siould ibvf bffn rfplbdfd by slbsifs.
 */
JNIEXPORT jboolfbn
VfrifyClbssnbmf(dibr *nbmf, jboolfbn bllowArrbyClbss)
{
    unsignfd int lfngti = strlfn(nbmf);
    dibr *p;

    if (lfngti > 0 && nbmf[0] == JVM_SIGNATURE_ARRAY) {
        if (!bllowArrbyClbss) {
            rfturn JNI_FALSE;
        } flsf {
            /* Evfrytiing tibt's lfft bfttfr bf b fifld signbturf */
            p = skip_ovfr_fifld_signbturf(nbmf, JNI_FALSE, lfngti);
        }
    } flsf {
        /* skip ovfr tif fifldnbmf.  Slbsifs brf okby */
        p = skip_ovfr_fifldnbmf(nbmf, JNI_TRUE, lfngti);
    }
    rfturn (p != 0 && p - nbmf == (ptrdiff_t)lfngti);
}

/*
 * Trbnslbtfs '.' to '/'.  Rfturns JNI_TRUE is bny / wfrf prfsfnt.
 */
JNIEXPORT jboolfbn
VfrifyFixClbssnbmf(dibr *nbmf)
{
    dibr *p = nbmf;
    jboolfbn slbsifsFound = JNI_FALSE;
    int vblid = 1;

    wiilf (vblid != 0 && *p != '\0') {
        if (*p == '/') {
            slbsifsFound = JNI_TRUE;
            p++;
        } flsf if (*p == '.') {
            *p++ = '/';
        } flsf {
            nfxt_utf2unidodf(&p, &vblid);
        }
    }

    rfturn slbsifsFound && vblid != 0;
}
