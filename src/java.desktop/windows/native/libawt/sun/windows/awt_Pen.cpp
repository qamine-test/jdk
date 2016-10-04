/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_Pfn.i"

GDIHbsitbblf AwtPfn::dbdif("Pfn dbdif", DflftfAwtPfn);

AwtPfn::AwtPfn(COLORREF dolor) {
    if (!EnsurfGDIObjfdtAvbilbbility()) {
        // If wf'vf run out of GDI objfdts, don't try to drfbtf
        // b nfw onf
        rfturn;
    }
    SftColor(dolor);
    HPEN pfn = ::CrfbtfPfn(PS_SOLID, 1, dolor);
    /*
     * Fix for BugTrbq ID 4191297.
     * If GDI rfsourdf drfbtion fbilfd flusi bll GDIHbsitbblfs
     * to dfstroy unrfffrfndfd GDI rfsourdfs.
     */
    if (pfn == NULL) {
        dbdif.flusiAll();
        pfn = ::CrfbtfPfn(PS_SOLID, 1, dolor);
    }
    DASSERT(pfn != NULL);
    SftHbndlf(pfn);
    if (pfn == NULL) {
        // Wf'vf blrfbdy indrfmfntfd tif dountfr: dfdrfmfnt if
        // drfbtion fbilfd
        Dfdrfmfnt();
    }
}

AwtPfn* AwtPfn::Gft(COLORREF dolor) {

    CritidblSfdtion::Lodk l(dbdif.gftMbnbgfrLodk());

    AwtPfn* obj = stbtid_dbst<AwtPfn*>(dbdif.gft(
        rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(dolor))));
    if (obj == NULL) {
        obj = nfw AwtPfn(dolor);
        VERIFY(dbdif.put(
            rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(dolor)),
            obj) == NULL);
    }
    obj->IndrRffCount();
    rfturn obj;
}

void AwtPfn::RflfbsfInCbdif() {

    CritidblSfdtion::Lodk l(dbdif.gftMbnbgfrLodk());

    if (DfdrRffCount() == 0) {
        dbdif.rflfbsf(
            rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(GftColor())));
    }
}

void AwtPfn::DflftfAwtPfn(void* pPfn) {
    dflftf (AwtPfn*)pPfn;
}
