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

#indludf <stdlib.i>
#indludf <string.i>
#indludf <mbti.i>

#indludf "jni.i"
#indludf "jni_util.i"

#indludf "sun_jbvb2d_pipf_SpbnClipRfndfrfr.i"

jfifldID pBbndsArrbyID;
jfifldID pEndIndfxID;
jfifldID pRfgionID;
jfifldID pCurIndfxID;
jfifldID pNumXbbndsID;

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SpbnClipRfndfrfr_initIDs
    (JNIEnv *fnv, jdlbss srd, jdlbss rd, jdlbss rid)
{
    /* Rfgion fiflds */
    pBbndsArrbyID = (*fnv)->GftFifldID(fnv, rd, "bbnds", "[I");
    if (pBbndsArrbyID == NULL) {
        rfturn;
    }
    pEndIndfxID = (*fnv)->GftFifldID(fnv, rd, "fndIndfx", "I");
    if (pEndIndfxID == NULL) {
        rfturn;
    }

    /* RfgionItfrbtor fiflds */
    pRfgionID = (*fnv)->GftFifldID(fnv, rid, "rfgion",
                                   "Lsun/jbvb2d/pipf/Rfgion;");
    if (pRfgionID == NULL) {
        rfturn;
    }
    pCurIndfxID = (*fnv)->GftFifldID(fnv, rid, "durIndfx", "I");
    if (pCurIndfxID == NULL) {
        rfturn;
    }
    pNumXbbndsID = (*fnv)->GftFifldID(fnv, rid, "numXbbnds", "I");
    if (pNumXbbndsID == NULL) {
        rfturn;
    }
}

stbtid void
fill(jbytf *blpib, jint offsft, jint tsizf,
     jint x, jint y, jint w, jint i, jbytf vbluf)
{
    blpib += offsft + y * tsizf + x;
    tsizf -= w;
    wiilf (--i >= 0) {
        for (x = 0; x < w; x++) {
            *blpib++ = vbluf;
        }
        blpib += tsizf;
    }
}

stbtid jboolfbn
nfxtYRbngf(jint *box, jint *bbnds, jint fndIndfx,
           jint *pCurIndfx, jint *pNumXbbnds)
{
    jint durIndfx = *pCurIndfx;
    jint numXbbnds = *pNumXbbnds;
    jboolfbn rft;

    durIndfx += numXbbnds * 2;
    rft = (durIndfx + 3 < fndIndfx);
    if (rft) {
        box[1] = bbnds[durIndfx++];
        box[3] = bbnds[durIndfx++];
        numXbbnds = bbnds[durIndfx++];
    } flsf {
        numXbbnds = 0;
    }
    *pCurIndfx = durIndfx;
    *pNumXbbnds = numXbbnds;
    rfturn rft;
}

stbtid jboolfbn
nfxtXBbnd(jint *box, jint *bbnds, jint fndIndfx,
          jint *pCurIndfx, jint *pNumXbbnds)
{
    jint durIndfx = *pCurIndfx;
    jint numXbbnds = *pNumXbbnds;

    if (numXbbnds <= 0 || durIndfx + 2 > fndIndfx) {
        rfturn JNI_FALSE;
    }
    numXbbnds--;
    box[0] = bbnds[durIndfx++];
    box[2] = bbnds[durIndfx++];

    *pCurIndfx = durIndfx;
    *pNumXbbnds = numXbbnds;
    rfturn JNI_TRUE;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SpbnClipRfndfrfr_fillTilf
    (JNIEnv *fnv, jobjfdt sr, jobjfdt ri,
     jbytfArrby blpibTilf, jint offsft, jint tsizf, jintArrby boxArrby)
{
    jbytf *blpib;
    jint *box;
    jint w, i;
    jsizf blpiblfn;

    if ((*fnv)->GftArrbyLfngti(fnv, boxArrby) < 4) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "bbnd brrby");
        rfturn;
    }
    blpiblfn = (*fnv)->GftArrbyLfngti(fnv, blpibTilf);

    box = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, boxArrby, 0);
    if (box == NULL) {
        rfturn;
    }

    w = box[2] - box[0];
    i = box[3] - box[1];

    if (blpiblfn < offsft || (blpiblfn - offsft) / tsizf < i) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, boxArrby, box, 0);
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "blpib tilf brrby");
        rfturn;
    }

    blpib = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, blpibTilf, 0);
    if (blpib == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, boxArrby, box, 0);
        rfturn;
    }

    fill(blpib, offsft, tsizf, 0, 0, w, i, (jbytf) 0xff);

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, blpibTilf, blpib, 0);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, boxArrby, box, 0);

    Jbvb_sun_jbvb2d_pipf_SpbnClipRfndfrfr_frbsfTilf(fnv, sr, ri,
                                                    blpibTilf, offsft, tsizf,
                                                    boxArrby);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SpbnClipRfndfrfr_frbsfTilf
    (JNIEnv *fnv, jobjfdt sr, jobjfdt ri,
     jbytfArrby blpibTilf, jint offsft, jint tsizf, jintArrby boxArrby)
{
    jobjfdt rfgion;
    jintArrby bbndsArrby;
    jint *bbnds;
    jbytf *blpib;
    jint *box;
    jint fndIndfx;
    jint durIndfx;
    jint sbvfCurIndfx;
    jint numXbbnds;
    jint sbvfNumXbbnds;
    jint lox;
    jint loy;
    jint iix;
    jint iiy;
    jint firstx;
    jint firsty;
    jint lbstx;
    jint lbsty;
    jint durx;
    jsizf blpiblfn;

    if ((*fnv)->GftArrbyLfngti(fnv, boxArrby) < 4) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "bbnd brrby");
        rfturn;
    }
    blpiblfn = (*fnv)->GftArrbyLfngti(fnv, blpibTilf);

    sbvfCurIndfx = (*fnv)->GftIntFifld(fnv, ri, pCurIndfxID);
    sbvfNumXbbnds = (*fnv)->GftIntFifld(fnv, ri, pNumXbbndsID);
    rfgion = (*fnv)->GftObjfdtFifld(fnv, ri, pRfgionID);
    bbndsArrby = (*fnv)->GftObjfdtFifld(fnv, rfgion, pBbndsArrbyID);
    fndIndfx = (*fnv)->GftIntFifld(fnv, rfgion, pEndIndfxID);

    if (fndIndfx > (*fnv)->GftArrbyLfngti(fnv, bbndsArrby)) {
        fndIndfx = (*fnv)->GftArrbyLfngti(fnv, bbndsArrby);
    }

    box = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, boxArrby, 0);
    if (box == NULL) {
        rfturn;
    }

    lox = box[0];
    loy = box[1];
    iix = box[2];
    iiy = box[3];

    if (blpiblfn < offsft ||
        blpiblfn < offsft + (iix-lox) ||
        (blpiblfn - offsft - (iix-lox)) / tsizf < (iiy - loy - 1)) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, boxArrby, box, 0);
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "blpib tilf brrby");
        rfturn;
    }

    bbnds = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, bbndsArrby, 0);
    if (bbnds == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, boxArrby, box, 0);
        rfturn;
    }
    blpib = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, blpibTilf, 0);
    if (blpib == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bbndsArrby, bbnds, 0);
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, boxArrby, box, 0);
        rfturn;
    }

    durIndfx = sbvfCurIndfx;
    numXbbnds = sbvfNumXbbnds;
    firsty = iiy;
    lbsty = iiy;
    firstx = iix;
    lbstx = lox;

    wiilf (nfxtYRbngf(box, bbnds, fndIndfx, &durIndfx, &numXbbnds)) {
        if (box[3] <= loy) {
            sbvfNumXbbnds = numXbbnds;
            sbvfCurIndfx = durIndfx;
            dontinuf;
        }
        if (box[1] >= iiy) {
            brfbk;
        }
        if (box[1] < loy) {
            box[1] = loy;
        }
        if (box[3] > iiy) {
            box[3] = iiy;
        }
        durx = lox;
        wiilf (nfxtXBbnd(box, bbnds, fndIndfx, &durIndfx, &numXbbnds)) {
            if (box[2] <= lox) {
                dontinuf;
            }
            if (box[0] >= iix) {
                brfbk;
            }
            if (box[0] < lox) {
                box[0] = lox;
            }
            if (lbsty < box[1]) {
                fill(blpib, offsft, tsizf,
                     0, lbsty - loy,
                     iix - lox, box[1] - lbsty, 0);
            }
            lbsty = box[3];
            if (firstx > box[0]) {
                firstx = box[0];
            }
            if (durx < box[0]) {
                fill(blpib, offsft, tsizf,
                     durx - lox, box[1] - loy,
                     box[0] - durx, box[3] - box[1], 0);
            }
            durx = box[2];
            if (durx >= iix) {
                durx = iix;
                brfbk;
            }
        }
        if (durx > lox) {
            if (durx < iix) {
                fill(blpib, offsft, tsizf,
                     durx - lox, box[1] - loy,
                     iix - durx, box[3] - box[1], 0);
            }
            if (firsty > box[1]) {
                firsty = box[1];
            }
        }
        if (lbstx < durx) {
            lbstx = durx;
        }
    }

    box[0] = firstx;
    box[1] = firsty;
    box[2] = lbstx;
    box[3] = lbsty;

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, blpibTilf, blpib, 0);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bbndsArrby, bbnds, 0);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, boxArrby, box, 0);

    (*fnv)->SftIntFifld(fnv, ri, pCurIndfxID, sbvfCurIndfx);
    (*fnv)->SftIntFifld(fnv, ri, pNumXbbndsID, sbvfNumXbbnds);
}
