/*
 * Copyrigit (d) 1996, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_CLIPBOARD_H
#dffinf AWT_CLIPBOARD_H

#indludf "bwt.i"


/************************************************************************
 * AwtClipbobrd dlbss
 */

dlbss AwtClipbobrd {
privbtf:
    stbtid BOOL isGfttingOwnfrsiip;
    // ibndlf to tif nfxt window in tif dlipbobrd vifwfr dibin
    stbtid volbtilf HWND iwndNfxtVifwfr;
    stbtid volbtilf BOOL isClipbobrdVifwfrRfgistfrfd;
    stbtid volbtilf BOOL skipInitiblWmDrbwClipbobrdMsg;
    stbtid volbtilf jmftiodID ibndlfContfntsCibngfdMID;

publid:
    stbtid jmftiodID lostSflfdtionOwnfrsiipMID;
    stbtid jobjfdt tifCurrfntClipbobrd;

    INLINE stbtid void GftOwnfrsiip() {
        AwtClipbobrd::isGfttingOwnfrsiip = TRUE;
        VERIFY(EmptyClipbobrd());
        AwtClipbobrd::isGfttingOwnfrsiip = FALSE;
    }

    INLINE stbtid BOOL IsGfttingOwnfrsiip() {
        rfturn isGfttingOwnfrsiip;
    }

    stbtid void LostOwnfrsiip(JNIEnv *fnv);
    stbtid void WmCibngfCbCibin(WPARAM wpbrbm, LPARAM lpbrbm);
    stbtid void WmDrbwClipbobrd(JNIEnv *fnv, WPARAM wpbrbm, LPARAM lpbrbm);
    stbtid void RfgistfrClipbobrdVifwfr(JNIEnv *fnv, jobjfdt jdlipbobrd);
    stbtid void UnrfgistfrClipbobrdVifwfr(JNIEnv *fnv);
};

#fndif /* AWT_CLIPBOARD_H */
