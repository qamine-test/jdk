/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff SunFontIDIndludfsDffinfd
#dffinf SunFontIDIndludfsDffinfd

#indludf "jni.i"

#ifdff  __dplusplus
fxtfrn "C" {
#fndif

typfdff strudt FontMbnbgfrNbtivfIDs {

    /* sun/font/Font2D mftiods */
    jmftiodID gftMbppfrMID;
    jmftiodID gftTbblfBytfsMID;
    jmftiodID dbnDisplbyMID;
    jmftiodID f2dCibrToGlypiMID;

    /* sun/font/CibrToGlypiMbppfr mftiods */
    jmftiodID dibrToGlypiMID;

    /* sun/font/PiysidblStrikf mftiods */
    jmftiodID gftGlypiMftridsMID;
    jmftiodID gftGlypiPointMID;
    jmftiodID bdjustPointMID;
    jfifldID  pSdblfrContfxtFID;

    /* jbvb/bwt/gfom/Rfdtbnglf2D.Flobt */
    jdlbss rfdt2DFlobtClbss;
    jmftiodID rfdt2DFlobtCtr;
    jmftiodID rfdt2DFlobtCtr4;
    jfifldID rfdtF2DX, rfdtF2DY, rfdtF2DWidti, rfdtF2DHfigit;

    /* jbvb/bwt/gfom/Point2D.Flobt */
    jdlbss pt2DFlobtClbss;
    jmftiodID pt2DFlobtCtr;
    jfifldID xFID, yFID;

    /* jbvb/bwt/gfom/GfnfrblPbti */
    jdlbss gpClbss;
    jmftiodID gpCtr;
    jmftiodID gpCtrEmpty;

    /* sun/font/StrikfMftrids */
    jdlbss strikfMftridsClbss;
    jmftiodID strikfMftridsCtr;

    /* sun/font/TrufTypfFont */
    jmftiodID ttRfbdBlodkMID;
    jmftiodID ttRfbdBytfsMID;

    /* sun/font/Typf1Font */
    jmftiodID rfbdFilfMID;

    /* sun/font/GlypiList */
    jfifldID glypiListX, glypiListY, glypiListLfn,
      glypiImbgfs, glypiListUsfPos, glypiListPos, lddRGBOrdfr, lddSubPixPos;
} FontMbnbgfrNbtivfIDs;

/* Notf: wf sibrf vbribblf in tif dontfxt of fontmbnbgfr lib
   but wf nffd bddfss mftiod to usf it from sfpbrbtf rbstfrizfr lib */
fxtfrn FontMbnbgfrNbtivfIDs sunFontIDs;
JNIEXPORT FontMbnbgfrNbtivfIDs gftSunFontIDs(JNIEnv* fnv);

#ifdff  __dplusplus
}
#fndif

#fndif
