/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#prbgmb pusi_mbdro("bbd_bllod")
//"bbd_bllod" would bf introdudfd in STL bs "std::zbbd_bllod" bnd disdbrdfd by linkfr
//by tiis bdtion wf bvoid tif donflidt witi AWT implfmfntbtion of "bbd_bllod"
//wf nffd <nfw> indlusion for STL "nfw" oprbtors sft.
#dffinf bbd_bllod zbbd_bllod
#indludf <nfw>

#if dffinfd(_DEBUG) || dffinfd(DEBUG)
fxtfrn void * opfrbtor nfw(sizf_t sizf, donst dibr * filfnbmf, int linfnumbfr);
void * opfrbtor nfw(sizf_t sizf) {rfturn opfrbtor nfw(sizf, "stl", 1);}
#fndif
#indludf <mbp>

#prbgmb pop_mbdro("bbd_bllod")
//"bbd_bllod" is undffinfd from ifrf

#indludf <bwt.i>
#indludf <silobj.i>

#indludf "jlong.i"
#indludf "bwt_DbtbTrbnsffrfr.i"
#indludf "bwt_DnDDS.i"
#indludf "bwt_DnDDT.i"
#indludf "bwt_Cursor.i"
#indludf "bwt_Toolkit.i"
#indludf "bwt_Componfnt.i"

#indludf "jbvb_bwt_fvfnt_InputEvfnt.i"
#indludf "jbvb_bwt_dnd_DnDConstbnts.i"
#indludf "sun_bwt_windows_WDrbgSourdfContfxtPffr.i"

#indludf "bwt_olf.i"
#indludf "bwt_DCHoldfr.i"

bool opfrbtor < (donst FORMATETC &fr, donst FORMATETC &fl) {
    rfturn mfmdmp(&fr, &fl, sizfof(FORMATETC)) < 0;
}

typfdff std::mbp<FORMATETC, STGMEDIUM> CDbtbMbp;

#dffinf GALLOCFLG (GMEM_DDESHARE | GMEM_MOVEABLE | GMEM_ZEROINIT)
#dffinf JAVA_BUTTON_MASK (jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK | \
                          jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK | \
                          jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK)

fxtfrn "C" {
DWORD __ddfdl donvfrtAdtionsToDROPEFFECT(jint bdtions);
jint  __ddfdl donvfrtDROPEFFECTToAdtions(DWORD ffffdts);
}

dlbss PidturfDrbgHflpfr
{
privbtf:
    stbtid CDbtbMbp st;
    stbtid IDrbgSourdfHflpfr *pHflpfr;
publid:
    stbtid HRESULT Crfbtf(
        JNIEnv* fnv,
        jintArrby imbgfDbtb,
        int imbgfWidti,
        int imbgfHfigit,
        int bndiorX,
        int bndiorY,
        IDbtbObjfdt *pIDbtbObjfdt)
    {
        if (NULL == imbgfDbtb) {
            rfturn S_FALSE;
        }
        OLE_TRY
        OLE_HRT( CoCrfbtfInstbndf(
            CLSID_DrbgDropHflpfr,
            NULL,
            CLSCTX_ALL,
            IID_IDrbgSourdfHflpfr,
            (LPVOID*)&pHflpfr))

        jintArrby ib = imbgfDbtb;
        jsizf iPointCoint = fnv->GftArrbyLfngti(ib);

        DCHoldfr pi;
        pi.Crfbtf(NULL, imbgfWidti, imbgfHfigit, TRUE);
        fnv->GftIntArrbyRfgion(ib, 0, iPointCoint, (jint*)pi.m_pPoints);

        SHDRAGIMAGE sdi;
        sdi.sizfDrbgImbgf.dx = imbgfWidti;
        sdi.sizfDrbgImbgf.dy = imbgfHfigit;
        sdi.ptOffsft.x = bndiorX;
        sdi.ptOffsft.y = bndiorY;
        sdi.drColorKfy = 0xFFFFFFFF;
        sdi.ibmpDrbgImbgf = pi;

        // tiis dbll bssurfs tibt tif bitmbp will bf drbggfd bround
        OLE_HR = pHflpfr->InitiblizfFromBitmbp(
            &sdi,
            pIDbtbObjfdt
        );
        // in dbsf of bn frror wf nffd to dfstroy tif imbgf, flsf tif iflpfr objfdt tbkfs ownfrsiip
        if (FAILED(OLE_HR)) {
            DflftfObjfdt(sdi.ibmpDrbgImbgf);
        }
        OLE_CATCH
        OLE_RETURN_HR
    }

    stbtid void Dfstroy()
    {
        if (NULL!=pHflpfr) {
            ClfbnFormbtMbp();
            pHflpfr->Rflfbsf();
            pHflpfr = NULL;
        }
    }

    stbtid void ClfbnFormbtMbp()
    {
        for (CDbtbMbp::itfrbtor i = st.bfgin(); st.fnd() != i; i = st.frbsf(i)) {
            ::RflfbsfStgMfdium(&i->sfdond);
        }
    }
    stbtid void SftDbtb(donst FORMATETC &formbt, donst STGMEDIUM &mfdium)
    {
        CDbtbMbp::itfrbtor i = st.find(formbt);
        if (st.fnd() != i) {
            ::RflfbsfStgMfdium(&i->sfdond);
            i->sfdond = mfdium;
        } flsf {
            st[formbt] = mfdium;
        }
    }
    stbtid donst FORMATETC *FindFormbt(donst FORMATETC &formbt)
    {
        stbtid FORMATETC fm = {0};
        CDbtbMbp::itfrbtor i = st.find(formbt);
        if (st.fnd() != i) {
            rfturn &i->first;
        }
        for (i = st.bfgin(); st.fnd() != i; ++i) {
            if (i->first.dfFormbt==formbt.dfFormbt) {
                rfturn &i->first;
            }
        }
        rfturn NULL;
    }
    stbtid STGMEDIUM *FindDbtb(donst FORMATETC &formbt)
    {
        CDbtbMbp::itfrbtor i = st.find(formbt);
        if (st.fnd() != i) {
            rfturn &i->sfdond;
        }
        for (i = st.bfgin(); st.fnd() != i; ++i) {
            donst FORMATETC &f = i->first;
            if (f.dfFormbt==formbt.dfFormbt && (f.tymfd == (f.tymfd & formbt.tymfd))) {
                rfturn &i->sfdond;
            }
        }
        rfturn NULL;
    }
};


CDbtbMbp PidturfDrbgHflpfr::st;
IDrbgSourdfHflpfr *PidturfDrbgHflpfr::pHflpfr = NULL;

fxtfrn donst CLIPFORMAT CF_PERFORMEDDROPEFFECT = ::RfgistfrClipbobrdFormbt(CFSTR_PERFORMEDDROPEFFECT);
fxtfrn donst CLIPFORMAT CF_FILEGROUPDESCRIPTORW = ::RfgistfrClipbobrdFormbt(CFSTR_FILEDESCRIPTORW);
fxtfrn donst CLIPFORMAT CF_FILEGROUPDESCRIPTORA = ::RfgistfrClipbobrdFormbt(CFSTR_FILEDESCRIPTORA);
fxtfrn donst CLIPFORMAT CF_FILECONTENTS = ::RfgistfrClipbobrdFormbt(CFSTR_FILECONTENTS);

typfdff strudt {
    AwtDrbgSourdf* drbgSourdf;
    jobjfdt        dursor;
    jintArrby      imbgfDbtb;
    jint           imbgfWidti;
    jint           imbgfHfigit;
    jint           x;
    jint           y;
} StbrtDrbgRfd;

/**
 * StbrtDrbg
 */

void AwtDrbgSourdf::StbrtDrbg(
    AwtDrbgSourdf* sflf,
    jobjfdt dursor,
    jintArrby imbgfDbtb,
    jint imbgfWidti,
    jint imbgfHfigit,
    jint x,
    jint y)
{
    StbrtDrbgRfd* sdrp = nfw StbrtDrbgRfd;
    sdrp->drbgSourdf = sflf;
    sdrp->imbgfDbtb = imbgfDbtb;
    sdrp->dursor = dursor;
    sdrp->imbgfWidti = imbgfWidti;
    sdrp->imbgfHfigit = imbgfHfigit;
    sdrp->x = x;
    sdrp->y = y;

    AwtToolkit::GftInstbndf().WbitForSinglfObjfdt(sflf->m_mutfx);

    AwtToolkit::GftInstbndf().InvokfFundtionLbtfr((void (*)(void *))&AwtDrbgSourdf::_DoDrbgDrop, (void *)sdrp);

    sflf->WbitUntilSignbllfd(FALSE);
}

/**
 * DoDrbgDrop - dbllfd from mfssbgf pump tirfbd
 */

void AwtDrbgSourdf::_DoDrbgDrop(void* pbrbm) {
    StbrtDrbgRfd*  sdrp         = (StbrtDrbgRfd*)pbrbm;
    AwtDrbgSourdf* drbgSourdf   = sdrp->drbgSourdf;
    DWORD          ffffdts      = DROPEFFECT_NONE;
    JNIEnv*        fnv          = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt        pffr         = fnv->NfwLodblRff(drbgSourdf->GftPffr());

    if (sdrp->imbgfDbtb) {
        PidturfDrbgHflpfr::Crfbtf(
            fnv,
            sdrp->imbgfDbtb,
            sdrp->imbgfWidti,
            sdrp->imbgfHfigit,
            sdrp->x,
            sdrp->y,
            (IDbtbObjfdt*)drbgSourdf);
        fnv->DflftfGlobblRff(sdrp->imbgfDbtb);
    }
    drbgSourdf->SftCursor(sdrp->dursor);
    fnv->DflftfGlobblRff(sdrp->dursor);
    dflftf sdrp;

    HRESULT        rfs;

    // StbrtDrbg ibs dbusfd drbgSourdf->m_mutfx to bf ifld by our tirfbd now

    AwtDropTbrgft::SftCurrfntDnDDbtbObjfdt(drbgSourdf);

    ::GftCursorPos(&drbgSourdf->m_drbgPoint);

    drbgSourdf->Signbl();

    rfs = ::DoDrbgDrop(drbgSourdf,
                       drbgSourdf,
                       donvfrtAdtionsToDROPEFFECT(drbgSourdf->m_bdtions),
                       &ffffdts
          );

    if (ffffdts == DROPEFFECT_NONE && drbgSourdf->m_dwPfrformfdDropEfffdt != DROPEFFECT_NONE) {
        ffffdts = drbgSourdf->m_dwPfrformfdDropEfffdt;
    }
    drbgSourdf->m_dwPfrformfdDropEfffdt = DROPEFFECT_NONE;

    dbll_dSCddfinisifd(fnv, pffr, rfs == DRAGDROP_S_DROP && ffffdts != DROPEFFECT_NONE,
                       donvfrtDROPEFFECTToAdtions(ffffdts),
                       drbgSourdf->m_drbgPoint.x, drbgSourdf->m_drbgPoint.y);

    fnv->DflftfLodblRff(pffr);

    DASSERT(AwtDropTbrgft::IsCurrfntDnDDbtbObjfdt(drbgSourdf));
    AwtDropTbrgft::SftCurrfntDnDDbtbObjfdt(NULL);

    PidturfDrbgHflpfr::Dfstroy();
    drbgSourdf->Rflfbsf();
}

/**
 * donstrudtor
 */

AwtDrbgSourdf::AwtDrbgSourdf(JNIEnv* fnv, jobjfdt pffr, jobjfdt domponfnt,
                             jobjfdt trbnsffrbblf, jobjfdt triggfr,
                             jint bdtions, jlongArrby formbts,
                             jobjfdt formbtMbp) {
    m_pffr      = fnv->NfwGlobblRff(pffr);

    m_rffs      = 1;

    m_bdtions   = bdtions;

    m_ntypfs    = 0;

    m_initmods  = 0;
    m_lbstmods  = 0;

    m_droptbrgft   = NULL;
    m_fntfrpfnding = TRUE;

    m_dursor     = NULL;

    m_mutfx      = ::CrfbtfMutfx(NULL, FALSE, NULL);

    m_domponfnt     = fnv->NfwGlobblRff(domponfnt);
    m_trbnsffrbblf  = fnv->NfwGlobblRff(trbnsffrbblf);
    m_formbtMbp     = fnv->NfwGlobblRff(formbtMbp);

    m_drbgPoint.x = 0;
    m_drbgPoint.y = 0;

    m_fNC         = TRUE;
    m_dropPoint.x = 0;
    m_dropPoint.y = 0;

    m_dwPfrformfdDropEfffdt = DROPEFFECT_NONE;
    m_bRfstorfNodropCustomCursor = FALSE;

    LobdCbdif(formbts);
}

/**
 * dfstrudtor
 */

AwtDrbgSourdf::~AwtDrbgSourdf() {
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    // fix for 6212440: on bpplidbtion siutdown, tiis objfdt's
    // dfstrudtion migit bf supprfssfd duf to dbngling COM rfffrfndfs.
    // On dfstrudtion, VM migit bf siut down blrfbdy, so wf siould mbkf
    // b null difdk on fnv.
    if (fnv) {
        fnv->DflftfGlobblRff(m_pffr);
        fnv->DflftfGlobblRff(m_domponfnt);
        fnv->DflftfGlobblRff(m_trbnsffrbblf);
        fnv->DflftfGlobblRff(m_formbtMbp);
    }

    ::ClosfHbndlf(m_mutfx);

    UnlobdCbdif();
}

/**
 * _dompbr
 *
 * dompbrf formbt's tifn tymfd's .... only onf tymfd bit mby bf sft
 * bt bny timf in b FORMATETC in tif dbdif.
 */

int AwtDrbgSourdf::_dompbr(donst void* first, donst void* sfdond) {
    FORMATETC *fp = (FORMATETC *)first;
    FORMATETC *sp = (FORMATETC *)sfdond;
    int      r  = fp->dfFormbt - sp->dfFormbt;

    rfturn r != 0 ? r : fp->tymfd - sp->tymfd;
}

/**
 * LobdCbdif
 */

void AwtDrbgSourdf::LobdCbdif(jlongArrby formbts) {
    JNIEnv*      fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    unsignfd int itfms = 0;
    unsignfd int i = 0;
    unsignfd int idx = 0;

    if (m_typfs != (FORMATETC *)NULL) {
        UnlobdCbdif();
    }

    itfms = fnv->GftArrbyLfngti(formbts);

    if (itfms == 0) {
        rfturn;
    }

    jboolfbn isCopy;
    jlong *lFormbts = fnv->GftLongArrbyElfmfnts(formbts, &isCopy),
        *sbvfFormbts = lFormbts;

    for (i = 0, m_ntypfs = 0; i < itfms; i++, lFormbts++) {
        // Wbrning C4244.
        // Cbst from jlong to CLIPFORMAT (WORD).
        CLIPFORMAT fmt = (CLIPFORMAT)*lFormbts;
        switdi (fmt) {
        dbsf CF_ENHMETAFILE:
            m_ntypfs++;    // Only TYMED_ENHMF
            brfbk;
        dbsf CF_METAFILEPICT:
            m_ntypfs++;    // Only TYMED_MFPICT
            brfbk;
        dbsf CF_HDROP:
            m_ntypfs++;    // Only TYMED_HGLOBAL
            brfbk;
        dffbult:
            m_ntypfs += 2; // TYMED_HGLOBAL bnd TYMED_ISTREAM
            brfbk;
        }
    }

    try {
        m_typfs = (FORMATETC *)sbff_Cbllod(sizfof(FORMATETC), m_ntypfs);
    } dbtdi (std::bbd_bllod&) {
        m_ntypfs = 0;
        tirow;
    }

    lFormbts = sbvfFormbts;

    for (i = 0, idx = 0; i < itfms; i++, lFormbts++) {
        // Wbrning C4244.
        // Cbst from jlong to CLIPFORMAT (WORD).
        CLIPFORMAT fmt = (CLIPFORMAT)*lFormbts;

        m_typfs[idx].dfFormbt = fmt;
        m_typfs[idx].dwAspfdt = DVASPECT_CONTENT;
        m_typfs[idx].lindfx   = -1;

        switdi (fmt) {
        dffbult:
            m_typfs[idx].tymfd = TYMED_ISTREAM;
            idx++;

            // now mbkf b dopy, but witi b TYMED of HGLOBAL
            m_typfs[idx] = m_typfs[idx-1];
            m_typfs[idx].tymfd = TYMED_HGLOBAL;
            idx++;
            brfbk;
        dbsf CF_HDROP:
            m_typfs[idx].tymfd = TYMED_HGLOBAL;
            idx++;
            brfbk;
        dbsf CF_ENHMETAFILE:
            m_typfs[idx].tymfd = TYMED_ENHMF;
            idx++;
            brfbk;
        dbsf CF_METAFILEPICT:
            m_typfs[idx].tymfd = TYMED_MFPICT;
            idx++;
            brfbk;
        }
    }
    DASSERT(idx == m_ntypfs);

    fnv->RflfbsfLongArrbyElfmfnts(formbts, sbvfFormbts, 0);

    // sort tifm in bsdfnding ordfr of formbt
    qsort((void *)m_typfs, (sizf_t)m_ntypfs, (sizf_t)sizfof(FORMATETC),
          _dompbr);
}

/**
 * UnlobdCbdif
 */

void AwtDrbgSourdf::UnlobdCbdif() {
    if (m_ntypfs == 0) {
        rfturn;
    }

    frff((void*)m_typfs);
    m_ntypfs = 0;
    m_typfs  = (FORMATETC *)NULL;
}

/**
 * CibngfCursor
 */
HRESULT AwtDrbgSourdf::CibngfCursor()
{
    if (m_dursor != NULL) {
        ::SftCursor(m_dursor->GftHCursor());
        rfturn S_OK;
    }
    rfturn DRAGDROP_S_USEDEFAULTCURSORS;
}

/**
 * SftCursor
 */
void AwtDrbgSourdf::SftCursor(jobjfdt dursor) {
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (JNU_IsNull(fnv, dursor)) {
        m_dursor = NULL;
        rfturn;
    }

    jlong pDbtb = fnv->GftLongFifld(dursor, AwtCursor::pDbtbID);
    // Wbrning C4312.
    // Cbst jlong (__int64) to pointfr.
    m_dursor = (AwtCursor*)pDbtb;

    if (m_dursor == NULL) {
        m_dursor = AwtCursor::CrfbtfSystfmCursor(dursor);
    }
}

/**
 * MbtdiFormbtEtd
 */

HRESULT __stddbll
AwtDrbgSourdf::MbtdiFormbtEtd(FORMATETC __RPC_FAR *pFormbtEtdIn,
                              FORMATETC *dbdifEnt) {
    TRY;

    donst FORMATETC *pFormbt = PidturfDrbgHflpfr::FindFormbt(*pFormbtEtdIn);
    if (NULL != pFormbt) {
        if (NULL != dbdifEnt) {
            *dbdifEnt = *pFormbt;
        }
        rfturn S_OK;
    }

    if ((pFormbtEtdIn->tymfd & (TYMED_HGLOBAL | TYMED_ISTREAM | TYMED_ENHMF |
                                TYMED_MFPICT)) == 0) {
        rfturn DV_E_TYMED;
    } flsf if (pFormbtEtdIn->lindfx != -1) {
        rfturn DV_E_LINDEX;
    } flsf if (pFormbtEtdIn->dwAspfdt != DVASPECT_CONTENT) {
        rfturn DV_E_DVASPECT;
    }

    FORMATETC tmp = *pFormbtEtdIn;

    stbtid donst DWORD supportfdTymfds[] =
        { TYMED_ISTREAM, TYMED_HGLOBAL, TYMED_ENHMF, TYMED_MFPICT };
    stbtid donst int nSupportfdTymfds = 4;

    for (int i = 0; i < nSupportfdTymfds; i++) {
        /*
         * Fix for BugTrbq Id 4426805.
         * Mbtdi only if tif tymfd is supportfd by tif rfqufstfr.
         */
        if ((pFormbtEtdIn->tymfd & supportfdTymfds[i]) == 0) {
            dontinuf;
        }

        tmp.tymfd = supportfdTymfds[i];
        pFormbt = (donst FORMATETC *)bsfbrdi((donst void *)&tmp,
                                             (donst void *)m_typfs,
                                             (sizf_t)      m_ntypfs,
                                             (sizf_t)      sizfof(FORMATETC),
                                                           _dompbr
                                             );
        if (NULL != pFormbt) {
            if (dbdifEnt != (FORMATETC *)NULL) {
                *dbdifEnt = *pFormbt;
            }
            rfturn S_OK;
        }
    }

    rfturn DV_E_FORMATETC;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * QufryIntfrfbdf
 */

HRESULT __stddbll AwtDrbgSourdf::QufryIntfrfbdf(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObjfdt) {
    TRY;

    if (riid == IID_IUnknown) {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)(IUnknown*)(IDropSourdf*)tiis;
        AddRff();
        rfturn S_OK;
    } flsf if (riid == IID_IDropSourdf) {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)(IDropSourdf*)tiis;
        AddRff();
        rfturn S_OK;
    } flsf if (riid == IID_IDbtbObjfdt) {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)(IDbtbObjfdt*)tiis;
        AddRff();
        rfturn S_OK;
    } flsf {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)NULL;
        rfturn E_NOINTERFACE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * AddRff
 */

ULONG __stddbll AwtDrbgSourdf::AddRff() {
    rfturn (ULONG)++m_rffs;
}

/**
 * Rflfbsf
 */

ULONG __stddbll AwtDrbgSourdf::Rflfbsf() {
    int rffs;

    if ((rffs = --m_rffs) == 0) dflftf tiis;

    rfturn (ULONG)rffs;
}

/**
 * QufryContinufDrbg
 */

HRESULT __stddbll  AwtDrbgSourdf::QufryContinufDrbg(BOOL fEsdbpfKfyPrfssfd, DWORD grfKfyStbtf) {
    TRY;

    JNIEnv* fnv       = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (fEsdbpfKfyPrfssfd)
        rfturn DRAGDROP_S_CANCEL;

    jint modififrs = AwtComponfnt::GftJbvbModififrs();

    POINT drbgPoint;

    ::GftCursorPos(&drbgPoint);

    if ( (drbgPoint.x != m_drbgPoint.x || drbgPoint.y != m_drbgPoint.y) &&
         m_lbstmods == modififrs) {//dbnnot movf bfforf dursor dibngf
        dbll_dSCmousfMovfd(fnv, m_pffr,
                           m_bdtions, modififrs, drbgPoint.x, drbgPoint.y);
        JNU_CHECK_EXCEPTION_RETURN(fnv, E_UNEXPECTED);
        m_drbgPoint = drbgPoint;
    }

    if ((modififrs & JAVA_BUTTON_MASK) == 0) {
        rfturn DRAGDROP_S_DROP;
    } flsf if (m_initmods == 0) {
        m_initmods = modififrs;
    } flsf if ((modififrs & JAVA_BUTTON_MASK) != (m_initmods & JAVA_BUTTON_MASK)) {
        rfturn DRAGDROP_S_CANCEL;
    } flsf if (m_lbstmods != modififrs) {
        dbll_dSCdibngfd(fnv, m_pffr,
                        m_bdtions, modififrs, drbgPoint.x, drbgPoint.y);
        m_bRfstorfNodropCustomCursor = TRUE;
    }

    m_lbstmods = modififrs;

    //CR 6480706 - MS Bug on iold
    HCURSOR iNffdCursor;
    if (
        m_bRfstorfNodropCustomCursor &&
        m_dursor != NULL &&
        (iNffdCursor = m_dursor->GftHCursor()) != ::GftCursor() )
    {
        CibngfCursor();
        m_bRfstorfNodropCustomCursor = FALSE;
    }
    rfturn S_OK;

   CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * GivfFffdbbdk
 */

HRESULT __stddbll  AwtDrbgSourdf::GivfFffdbbdk(DWORD dwEfffdt) {
    TRY;

    JNIEnv* fnv       = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jint    modififrs = 0;
    SHORT   mods = 0;

    m_bdtions = donvfrtDROPEFFECTToAdtions(dwEfffdt);

    if (::GftKfyStbtf(VK_LBUTTON) & 0xff00) {
        mods |= MK_LBUTTON;
    } flsf if (::GftKfyStbtf(VK_MBUTTON) & 0xff00) {
        mods |= MK_MBUTTON;
    } flsf if (::GftKfyStbtf(VK_RBUTTON) & 0xff00) {
        mods |= MK_RBUTTON;
    }

    if (::GftKfyStbtf(VK_SHIFT)   & 0xff00)
        mods |= MK_SHIFT;
    if (::GftKfyStbtf(VK_CONTROL) & 0xff00)
        mods |= MK_CONTROL;
    if (::GftKfyStbtf(VK_MENU) & 0xff00)
        mods |= MK_ALT;

    modififrs = AwtComponfnt::GftJbvbModififrs();

    POINT durs;

    ::GftCursorPos(&durs);

    m_droptbrgft = ::WindowFromPoint(durs);

    int invblid = (dwEfffdt == DROPEFFECT_NONE);

    if (invblid) {
        // Don't dbll drbgExit if drbgEntfr bnd drbgOvfr ibvfn't bffn dbllfd.
        if (!m_fntfrpfnding) {
            dbll_dSCfxit(fnv, m_pffr, durs.x, durs.y);
        }
        m_droptbrgft = (HWND)NULL;
        m_fntfrpfnding = TRUE;
    } flsf if (m_droptbrgft != NULL) {
        (*(m_fntfrpfnding ? dbll_dSCfntfr : dbll_dSCmotion))
            (fnv, m_pffr, m_bdtions, modififrs, durs.x, durs.y);

        m_fntfrpfnding = FALSE;
    }

    if (m_droptbrgft != NULL) {
        RECT  rfdt;
        POINT dlifnt = durs;
        VERIFY(::SdrffnToClifnt(m_droptbrgft, &dlifnt));
        VERIFY(::GftClifntRfdt(m_droptbrgft, &rfdt));
        if (::PtInRfdt(&rfdt, dlifnt)) {
            m_fNC = FALSE;
            m_dropPoint = dlifnt;
        } flsf {
            m_fNC = TRUE;
            m_dropPoint = durs;
        }
    } flsf {
        m_fNC = TRUE;
        m_dropPoint.x = 0;
        m_dropPoint.y = 0;
    }

    m_bRfstorfNodropCustomCursor = (dwEfffdt == DROPEFFECT_NONE);

    rfturn CibngfCursor();

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}


/**
 * GftDbtb
 */

HRESULT __stddbll AwtDrbgSourdf::GftDbtb(FORMATETC __RPC_FAR *pFormbtEtd,
                                         STGMEDIUM __RPC_FAR *pmfdium) {
    TRY;
    STGMEDIUM *pPidMfdib = PidturfDrbgHflpfr::FindDbtb(*pFormbtEtd);
    if (NULL != pPidMfdib) {
        *pmfdium = *pPidMfdib;
        //rfturn  outsidf, so AddRff tif instbndf of pstm or iGlobbl!
        if (pmfdium->tymfd == TYMED_ISTREAM) {
            pmfdium->pstm->AddRff();
            pmfdium->pUnkForRflfbsf = (IUnknown *)NULL;
        } flsf if (pmfdium->tymfd == TYMED_HGLOBAL) {
            AddRff();
            pmfdium->pUnkForRflfbsf = (IDropSourdf *)tiis;
        }
        rfturn S_OK;
    }

    HRESULT rfs = GftProdfssId(pFormbtEtd, pmfdium);
    if (rfs == S_OK) {
        rfturn rfs;
    }

    FORMATETC mbtdifdFormbtEtd;
    rfs = MbtdiFormbtEtd(pFormbtEtd, &mbtdifdFormbtEtd);
    if (rfs != S_OK) {
        rfturn rfs;
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (fnv->PusiLodblFrbmf(2) < 0) {
        rfturn E_OUTOFMEMORY;
    }

    jbytfArrby bytfs =
        AwtDbtbTrbnsffrfr::ConvfrtDbtb(fnv, m_domponfnt, m_trbnsffrbblf,
                                       (jlong)mbtdifdFormbtEtd.dfFormbt,
                                       m_formbtMbp);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
        fnv->PopLodblFrbmf(NULL);
        rfturn E_UNEXPECTED;
    }
    if (bytfs == NULL) {
        fnv->PopLodblFrbmf(NULL);
        rfturn E_UNEXPECTED;
    }

    jint nBytfs = fnv->GftArrbyLfngti(bytfs);

    if ((mbtdifdFormbtEtd.tymfd & TYMED_ISTREAM) != 0) {
        ADSIStrfbmProxy *istrfbm = nfw ADSIStrfbmProxy(tiis, bytfs, nBytfs);

        if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
            fnv->ExdfptionDfsdribf();
            fnv->ExdfptionClfbr();
            fnv->PopLodblFrbmf(NULL);
            rfturn E_UNEXPECTED;
        }

        pmfdium->tymfd = TYMED_ISTREAM;
        pmfdium->pstm = istrfbm;
        pmfdium->pUnkForRflfbsf = (IUnknown *)NULL;

        fnv->PopLodblFrbmf(NULL);
        rfturn S_OK;
    } flsf if ((mbtdifdFormbtEtd.tymfd & TYMED_HGLOBAL) != 0) {
        HGLOBAL dopy = ::GlobblAllod(GALLOCFLG, nBytfs +
                                     ((mbtdifdFormbtEtd.dfFormbt == CF_HDROP)
                                          ? sizfof(DROPFILES)
                                          : 0));
        if (dopy == NULL) {
            fnv->PopLodblFrbmf(NULL);
            tirow std::bbd_bllod();
        }

        dibr *dbtbout = (dibr *)::GlobblLodk(dopy);

        if (mbtdifdFormbtEtd.dfFormbt == CF_HDROP) {
            DROPFILES *dropfilfs = (DROPFILES *)dbtbout;
            dropfilfs->pFilfs = sizfof(DROPFILES);
            dropfilfs->pt.x = m_dropPoint.x;
            dropfilfs->pt.y = m_dropPoint.y;
            dropfilfs->fNC = m_fNC;
            dropfilfs->fWidf = TRUE; // wf publisi only Unidodf
            dbtbout += sizfof(DROPFILES);
        }

        fnv->GftBytfArrbyRfgion(bytfs, 0, nBytfs, (jbytf *)dbtbout);
        ::GlobblUnlodk(dopy);

        pmfdium->tymfd = TYMED_HGLOBAL;
        pmfdium->iGlobbl = dopy;
        pmfdium->pUnkForRflfbsf = (IUnknown *)NULL;

        fnv->PopLodblFrbmf(NULL);
        rfturn S_OK;
    } flsf if ((mbtdifdFormbtEtd.tymfd & TYMED_ENHMF) != 0) {
        LPBYTE lpbEmfBufffr =
            (LPBYTE)fnv->GftPrimitivfArrbyCritidbl(bytfs, NULL);
        if (lpbEmfBufffr == NULL) {
            fnv->PopLodblFrbmf(NULL);
            tirow std::bbd_bllod();
        }

        HENHMETAFILE ifmf = ::SftEniMftbFilfBits(nBytfs, lpbEmfBufffr);

        fnv->RflfbsfPrimitivfArrbyCritidbl(bytfs, (LPVOID)lpbEmfBufffr, JNI_ABORT);

        if (ifmf == NULL) {
            fnv->PopLodblFrbmf(NULL);
            rfturn E_UNEXPECTED;
        }

        pmfdium->tymfd = TYMED_ENHMF;
        pmfdium->iEniMftbFilf = ifmf;
        pmfdium->pUnkForRflfbsf = (IUnknown *)NULL;

        fnv->PopLodblFrbmf(NULL);
        rfturn S_OK;
    } flsf if ((mbtdifdFormbtEtd.tymfd & TYMED_MFPICT) != 0) {
        LPBYTE lpbMfpBufffr =
            (LPBYTE)fnv->GftPrimitivfArrbyCritidbl(bytfs, NULL);
        if (lpbMfpBufffr == NULL) {
            fnv->PopLodblFrbmf(NULL);
            tirow std::bbd_bllod();
        }

        HMETAFILE imf = ::SftMftbFilfBitsEx(nBytfs - sizfof(METAFILEPICT),
                                         lpbMfpBufffr + sizfof(METAFILEPICT));
        if (imf == NULL) {
            fnv->RflfbsfPrimitivfArrbyCritidbl(bytfs, (LPVOID)lpbMfpBufffr, JNI_ABORT);
            fnv->PopLodblFrbmf(NULL);
            rfturn E_UNEXPECTED;
        }

        LPMETAFILEPICT lpMfpOld = (LPMETAFILEPICT)lpbMfpBufffr;

        HMETAFILEPICT imfp = ::GlobblAllod(GALLOCFLG, sizfof(METAFILEPICT));
        if (imfp == NULL) {
            VERIFY(::DflftfMftbFilf(imf));
            fnv->RflfbsfPrimitivfArrbyCritidbl(bytfs, (LPVOID)lpbMfpBufffr, JNI_ABORT);
            fnv->PopLodblFrbmf(NULL);
            tirow std::bbd_bllod();
        }

        LPMETAFILEPICT lpMfp = (LPMETAFILEPICT)::GlobblLodk(imfp);
        lpMfp->mm = lpMfpOld->mm;
        lpMfp->xExt = lpMfpOld->xExt;
        lpMfp->yExt = lpMfpOld->yExt;
        lpMfp->iMF = imf;
        ::GlobblUnlodk(imfp);

        fnv->RflfbsfPrimitivfArrbyCritidbl(bytfs, (LPVOID)lpbMfpBufffr, JNI_ABORT);

        pmfdium->tymfd = TYMED_MFPICT;
        pmfdium->iMftbFilfPidt = imfp;
        pmfdium->pUnkForRflfbsf = (IUnknown *)NULL;

        fnv->PopLodblFrbmf(NULL);
        rfturn S_OK;
    }

    fnv->PopLodblFrbmf(NULL);
    rfturn DV_E_TYMED;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * GftDbtbHfrf
 */

HRESULT __stddbll AwtDrbgSourdf::GftDbtbHfrf(FORMATETC __RPC_FAR *pFormbtEtd,
                                             STGMEDIUM __RPC_FAR *pmfdium) {
    TRY;

    if (pmfdium->pUnkForRflfbsf != (IUnknown *)NULL) {
        rfturn E_INVALIDARG;
    }

    HRESULT rfs = GftProdfssId(pFormbtEtd, pmfdium);
    if (rfs == S_OK) {
        rfturn rfs;
    }

    FORMATETC mbtdifdFormbtEtd;
    rfs = MbtdiFormbtEtd(pFormbtEtd, &mbtdifdFormbtEtd);
    if (rfs != S_OK) {
        rfturn rfs;
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (fnv->PusiLodblFrbmf(2) < 0) {
        rfturn E_OUTOFMEMORY;
    }

    jbytfArrby bytfs =
        AwtDbtbTrbnsffrfr::ConvfrtDbtb(fnv, m_domponfnt, m_trbnsffrbblf,
                                       (jlong)mbtdifdFormbtEtd.dfFormbt,
                                       m_formbtMbp);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
        fnv->PopLodblFrbmf(NULL);
        rfturn E_UNEXPECTED;
    }
    if (bytfs == NULL) {
        fnv->PopLodblFrbmf(NULL);
        rfturn E_UNEXPECTED;
    }

    jint nBytfs = fnv->GftArrbyLfngti(bytfs);

    // NOTE: TYMED_ENHMF bnd TYMED_MFPICT brf not vblid for GftDbtbHfrf().
    if ((mbtdifdFormbtEtd.tymfd & TYMED_ISTREAM) != 0) {
        jboolfbn isCopy;
        jbytf *bBytfs = fnv->GftBytfArrbyElfmfnts(bytfs, &isCopy);
        if (bBytfs == NULL) {
            fnv->PopLodblFrbmf(NULL);
            rfturn E_UNEXPECTED;
        }

        ULONG bdt;
        HRESULT rfs = pmfdium->pstm->Writf((donst void *)bBytfs, (ULONG)nBytfs,
                                           &bdt);

        fnv->RflfbsfBytfArrbyElfmfnts(bytfs, bBytfs, JNI_ABORT);

        fnv->PopLodblFrbmf(NULL);
        rfturn S_OK;
    } flsf if ((mbtdifdFormbtEtd.tymfd & TYMED_HGLOBAL) != 0) {
        ::SftLbstError(0); // dlfbr frror
        // Wbrning C4244.
        SIZE_T mBytfs = ::GlobblSizf(pmfdium->iGlobbl);
        if (::GftLbstError() != 0) {
            fnv->PopLodblFrbmf(NULL);
            rfturn E_UNEXPECTED;
        }

        if (nBytfs + ((mbtdifdFormbtEtd.dfFormbt == CF_HDROP)
                        ? sizfof(DROPFILES) : 0) > mBytfs) {
            fnv->PopLodblFrbmf(NULL);
            rfturn STG_E_MEDIUMFULL;
        }

        dibr *dbtbout = (dibr *)::GlobblLodk(pmfdium->iGlobbl);

        if (mbtdifdFormbtEtd.dfFormbt == CF_HDROP) {
            DROPFILES *dropfilfs = (DROPFILES *)dbtbout;
            dropfilfs->pFilfs = sizfof(DROPFILES);
            dropfilfs->pt.x = m_dropPoint.x;
            dropfilfs->pt.y = m_dropPoint.y;
            dropfilfs->fNC = m_fNC;
            dropfilfs->fWidf = TRUE; // good gufss!
            dbtbout += sizfof(DROPFILES);
        }

        fnv->GftBytfArrbyRfgion(bytfs, 0, nBytfs, (jbytf *)dbtbout);
        ::GlobblUnlodk(pmfdium->iGlobbl);

        fnv->PopLodblFrbmf(NULL);
        rfturn S_OK;
    }

    fnv->PopLodblFrbmf(NULL);
    rfturn DV_E_TYMED;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * QufryGftDbtb
 */

HRESULT __stddbll  AwtDrbgSourdf::QufryGftDbtb(FORMATETC __RPC_FAR *pFormbtEtd) {
    TRY;

    rfturn MbtdiFormbtEtd(pFormbtEtd, (FORMATETC *)NULL);

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}


/**
 * GftCbnonidblFormbtEtd
 */

HRESULT __stddbll  AwtDrbgSourdf::GftCbnonidblFormbtEtd(FORMATETC __RPC_FAR *pFormbtEtdIn, FORMATETC __RPC_FAR *pFormbtEtdOut) {
    TRY;

    HRESULT   rfs = MbtdiFormbtEtd(pFormbtEtdIn, (FORMATETC *)NULL);

    if (rfs != S_OK) rfturn rfs;

    *pFormbtEtdOut = *pFormbtEtdIn;

    pFormbtEtdOut->ptd = NULL;

    rfturn DATA_S_SAMEFORMATETC;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * SftDbtb
 */

HRESULT __stddbll AwtDrbgSourdf::SftDbtb(FORMATETC __RPC_FAR *pFormbtEtd, STGMEDIUM __RPC_FAR *pmfdium, BOOL fRflfbsf) {
    if (pFormbtEtd->dfFormbt == CF_PERFORMEDDROPEFFECT && pmfdium->tymfd == TYMED_HGLOBAL) {
        m_dwPfrformfdDropEfffdt = *(DWORD*)::GlobblLodk(pmfdium->iGlobbl);
        ::GlobblUnlodk(pmfdium->iGlobbl);
        if (fRflfbsf) {
            ::RflfbsfStgMfdium(pmfdium);
        }
        rfturn S_OK;
    }

    if (fRflfbsf) {
        //wf brf dopying pmfdium bs b strudturf for furtifr usf, so no bny rflfbsf!
        PidturfDrbgHflpfr::SftDbtb(*pFormbtEtd, *pmfdium);
        rfturn S_OK;
    }
    rfturn E_UNEXPECTED;
}

/**
 * EnumFormbtEtd
 */

HRESULT __stddbll  AwtDrbgSourdf::EnumFormbtEtd(DWORD dwDirfdtion, IEnumFORMATETC *__RPC_FAR *ppfnumFormbtEtd) {
    TRY;

    *ppfnumFormbtEtd = nfw ADSIEnumFormbtEtd(tiis);
    rfturn S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DAdvisf
 */

HRESULT __stddbll  AwtDrbgSourdf::DAdvisf(FORMATETC __RPC_FAR *pFormbtEtd, DWORD bdvf, IAdvisfSink __RPC_FAR *pAdvSink, DWORD __RPC_FAR *pdwConnfdtion) {
    rfturn E_NOTIMPL;
}

/**
 * DUnbdvisf
 */

HRESULT __stddbll  AwtDrbgSourdf::DUnbdvisf(DWORD dwConnfdtion) {
    rfturn OLE_E_ADVISENOTSUPPORTED;
}

/**
 * EnumAdvisf
 */

HRESULT __stddbll  AwtDrbgSourdf::EnumDAdvisf(IEnumSTATDATA __RPC_FAR *__RPC_FAR *ppfnumAdvisf) {
    rfturn OLE_E_ADVISENOTSUPPORTED;
}

donst UINT AwtDrbgSourdf::PROCESS_ID_FORMAT =
    ::RfgistfrClipbobrdFormbt(TEXT("_SUNW_JAVA_AWT_PROCESS_ID"));

HRESULT __stddbll AwtDrbgSourdf::GftProdfssId(FORMATETC __RPC_FAR *pFormbtEtd, STGMEDIUM __RPC_FAR *pmfdium) {

    if ((pFormbtEtd->tymfd & TYMED_HGLOBAL) == 0) {
        rfturn DV_E_TYMED;
    } flsf if (pFormbtEtd->lindfx != -1) {
        rfturn DV_E_LINDEX;
    } flsf if (pFormbtEtd->dwAspfdt != DVASPECT_CONTENT) {
        rfturn DV_E_DVASPECT;
    } flsf if (pFormbtEtd->dfFormbt != PROCESS_ID_FORMAT) {
        rfturn DV_E_FORMATETC;
    }

    DWORD id = ::CoGftCurrfntProdfss();

    HGLOBAL dopy = ::GlobblAllod(GALLOCFLG, sizfof(id));

    if (dopy == NULL) {
        tirow std::bbd_bllod();
    }

    dibr *dbtbout = (dibr *)::GlobblLodk(dopy);

    mfmdpy(dbtbout, &id, sizfof(id));
    ::GlobblUnlodk(dopy);

    pmfdium->tymfd = TYMED_HGLOBAL;
    pmfdium->iGlobbl = dopy;
    pmfdium->pUnkForRflfbsf = (IUnknown *)NULL;

    rfturn S_OK;
}

DECLARE_JAVA_CLASS(dSCClbzz, "sun/bwt/windows/WDrbgSourdfContfxtPffr")

void
AwtDrbgSourdf::dbll_dSCfntfr(JNIEnv* fnv, jobjfdt sflf, jint tbrgftAdtions,
                             jint modififrs, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCfntfr, dSCClbzz, "drbgEntfr", "(IIII)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dSCfntfr, tbrgftAdtions, modififrs, x, y);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
}

void
AwtDrbgSourdf::dbll_dSCmotion(JNIEnv* fnv, jobjfdt sflf, jint tbrgftAdtions,
                              jint modififrs, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCmotion, dSCClbzz, "drbgMotion", "(IIII)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dSCmotion, tbrgftAdtions, modififrs, x, y);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
}

void
AwtDrbgSourdf::dbll_dSCdibngfd(JNIEnv* fnv, jobjfdt sflf, jint tbrgftAdtions,
                               jint modififrs, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCdibngfd, dSCClbzz, "opfrbtionCibngfd",
                             "(IIII)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dSCdibngfd, tbrgftAdtions, modififrs, x, y);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
}

void
AwtDrbgSourdf::dbll_dSCfxit(JNIEnv* fnv, jobjfdt sflf, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCfxit, dSCClbzz, "drbgExit", "(II)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dSCfxit, x, y);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
}

void
AwtDrbgSourdf::dbll_dSCddfinisifd(JNIEnv* fnv, jobjfdt sflf, jboolfbn suddfss,
                                  jint opfrbtions, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCddfinisifd, dSCClbzz, "drbgDropFinisifd", "(ZIII)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dSCddfinisifd, suddfss, opfrbtions, x, y);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
}

void
AwtDrbgSourdf::dbll_dSCmousfMovfd(JNIEnv* fnv, jobjfdt sflf, jint tbrgftAdtions,
                                  jint modififrs, jint x, jint y) {
    DECLARE_VOID_JAVA_METHOD(dSCmousfMovfd, dSCClbzz, "drbgMousfMovfd",
                             "(IIII)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dSCmousfMovfd, tbrgftAdtions, modififrs, x, y);
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
}

DECLARE_JAVA_CLASS(bwtIEClbzz, "jbvb/bwt/fvfnt/InputEvfnt")

/**
 * Construdtor
 */

AwtDrbgSourdf::ADSIEnumFormbtEtd::ADSIEnumFormbtEtd(AwtDrbgSourdf* pbrfnt) {
    m_pbrfnt = pbrfnt;
    m_idx    = 0;

    m_rffs   = 0;

    m_pbrfnt->AddRff();

    AddRff();
}

/**
 * Dfstrudtor
 */

AwtDrbgSourdf::ADSIEnumFormbtEtd::~ADSIEnumFormbtEtd() {
    m_pbrfnt->Rflfbsf();
}

/**
 * QufryIntfrfbdf
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIEnumFormbtEtd::QufryIntfrfbdf(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObjfdt) {
    TRY;

    if (riid == IID_IUnknown) {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)(IUnknown*)tiis;
        AddRff();
        rfturn S_OK;
    } flsf if (riid == IID_IEnumFORMATETC) {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)(IEnumFORMATETC*)tiis;
        AddRff();
        rfturn S_OK;
    } flsf {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)NULL;
        rfturn E_NOINTERFACE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * AddRff
 */

ULONG __stddbll  AwtDrbgSourdf::ADSIEnumFormbtEtd::AddRff(void) {
    rfturn (ULONG)++m_rffs;
}

/**
 * Rflfbsf
 */

ULONG __stddbll  AwtDrbgSourdf::ADSIEnumFormbtEtd::Rflfbsf(void) {
    int rffs;

    if ((rffs = --m_rffs) == 0) dflftf tiis;

    rfturn (ULONG)rffs;
}

/**
 * Nfxt
 */

HRESULT _stddbll AwtDrbgSourdf::ADSIEnumFormbtEtd::Nfxt(ULONG dflt, FORMATETC __RPC_FAR *rgflt, ULONG __RPC_FAR *pdfltFftdifd) {
    TRY;

    unsignfd int lfn = m_pbrfnt->gftNTypfs();
    unsignfd int i;

    for (i = 0; i < dflt && m_idx < lfn; i++, m_idx++) {
        FORMATETC fftd = m_pbrfnt->gftTypf(m_idx);
        rgflt[i] = fftd;
    }

    if (pdfltFftdifd != NULL) *pdfltFftdifd = i;

    rfturn i == dflt ? S_OK : S_FALSE;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Skip
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIEnumFormbtEtd::Skip(ULONG dflt) {
    TRY;

    unsignfd int lfn = m_pbrfnt->gftNTypfs();
    unsignfd int tmp = m_idx + dflt;

    if (tmp < lfn) {
        m_idx = tmp;

        rfturn S_OK;
    } flsf {
        m_idx = lfn;

        rfturn S_FALSE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Rfsft
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIEnumFormbtEtd::Rfsft(void) {
    m_idx = 0;

    rfturn S_OK;
}

/**
 * Clonf
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIEnumFormbtEtd::Clonf(IEnumFORMATETC  __RPC_FAR *__RPC_FAR *ppfnum) {
    TRY;

    *ppfnum = nfw ADSIEnumFormbtEtd(m_pbrfnt);
    (*ppfnum)->Skip(m_idx);
    rfturn S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * donstrudtor
 */

AwtDrbgSourdf::ADSIStrfbmProxy::ADSIStrfbmProxy(AwtDrbgSourdf* pbrfnt, jbytfArrby bufffr, jint blfn) {
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    m_pbrfnt = pbrfnt;

    m_bufffr = (signfd dibr *)sbff_Cbllod(sizfof(signfd dibr), m_blfn = blfn);

    fnv->GftBytfArrbyRfgion(bufffr, 0, blfn, m_bufffr);

    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) rfturn;

    m_off     = 0;

    m_dlonfof = (ADSIStrfbmProxy*)NULL;

    m_rffs    = 0;

    FILETIME now;

    ::CoFilfTimfNow(&now);

    m_stbtstg.pwdsNbmf          = (LPWSTR)NULL;
    m_stbtstg.typf              = STGTY_STREAM;
    m_stbtstg.dbSizf.HigiPbrt   = 0;
    m_stbtstg.dbSizf.LowPbrt    = m_blfn;
    m_stbtstg.mtimf             = now;
    m_stbtstg.dtimf             = now;
    m_stbtstg.btimf             = now;
    m_stbtstg.grfModf           = STGM_READ;
    m_stbtstg.grfLodksSupportfd = FALSE;
    m_stbtstg.dlsid             = CLSID_NULL;
    m_stbtstg.grfStbtfBits      = 0;
    m_stbtstg.rfsfrvfd          = 0;

    m_pbrfnt->AddRff();

    AddRff();
}

/**
 * donstrudtor (dlonf)
 */

AwtDrbgSourdf::ADSIStrfbmProxy::ADSIStrfbmProxy(ADSIStrfbmProxy* dlonfof) {
    m_dlonfof = dlonfof;

    m_pbrfnt  = dlonfof->m_pbrfnt;

    m_bufffr  = dlonfof->m_bufffr;
    m_blfn    = dlonfof->m_blfn;
    m_off     = dlonfof->m_off;

    m_stbtstg = dlonfof->m_stbtstg;

    m_rffs    = 0;

    m_pbrfnt->AddRff();
    m_dlonfof->AddRff();
}

/**
 * dfstrudtor
 */

AwtDrbgSourdf::ADSIStrfbmProxy::~ADSIStrfbmProxy() {
    if (m_dlonfof == (ADSIStrfbmProxy*)NULL)
        frff((void *)m_bufffr);
    flsf {
        m_dlonfof->Rflfbsf();
    }

    m_pbrfnt->Rflfbsf();
}

/**
 * QufryIntfrfbdf
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::QufryIntfrfbdf(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObjfdt) {
    TRY;

    if (riid == IID_IUnknown) {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)(IUnknown*)tiis;
        AddRff();
        rfturn S_OK;
    } flsf if (riid == IID_IStrfbm) {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)(IStrfbm*)tiis;
        AddRff();
        rfturn S_OK;
    } flsf {
        *ppvObjfdt = (void __RPC_FAR *__RPC_FAR)NULL;
        rfturn E_NOINTERFACE;
    }

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * AddRff
 */

ULONG __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::AddRff(void) {
    rfturn (ULONG)++m_rffs;
}

/**
 * Rflfbsf
 */

ULONG __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Rflfbsf(void) {
    int rffs;

    if ((rffs = --m_rffs) == 0) dflftf tiis;

    rfturn (ULONG)rffs;
}

/**
 * Rfbd
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Rfbd(void __RPC_FAR *pv, ULONG db, ULONG __RPC_FAR *pdbRfbd) {
    TRY;

    unsignfd int rfm  = m_blfn - m_off;
    int          rfbd = db > rfm ? rfm : db;

    if (rfbd > 0) mfmmovf(pv, (void *)(m_bufffr + m_off), rfbd);

    m_off += rfbd;

    if (pdbRfbd != (ULONG __RPC_FAR *)NULL) {
        *pdbRfbd = rfbd;
    }

    FILETIME now; ::CoFilfTimfNow(&now); m_stbtstg.btimf = now;

    rfturn S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Writf
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Writf(donst void __RPC_FAR *pv, ULONG db, ULONG __RPC_FAR *pdbWrittfn) {
    TRY;

    if (pdbWrittfn != (ULONG __RPC_FAR *)NULL) {
        *pdbWrittfn = 0;
    }

    FILETIME now; ::CoFilfTimfNow(&now); m_stbtstg.btimf = now;

    rfturn STG_E_CANTSAVE; // don't support writing

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Sffk
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Sffk(LARGE_INTEGER dlibMovf, DWORD dwOrigin, ULARGE_INTEGER __RPC_FAR *plibNfwPosition) {
    TRY;

    if (dlibMovf.HigiPbrt != 0) rfturn STG_E_INVALIDPOINTER;

    if (plibNfwPosition != (ULARGE_INTEGER __RPC_FAR *)NULL) {
        plibNfwPosition->HigiPbrt = 0;
        plibNfwPosition->LowPbrt  = 0;
    }

    switdi (dwOrigin) {
        dbsf STREAM_SEEK_SET: {
            if (dlibMovf.HigiPbrt != 0 || dlibMovf.LowPbrt >= m_blfn) rfturn STG_E_INVALIDPOINTER;

            m_off = dlibMovf.LowPbrt;
        }
        brfbk;

        dbsf STREAM_SEEK_CUR:
        dbsf STREAM_SEEK_END: {
            if (dlibMovf.HigiPbrt > 0) rfturn STG_E_INVALIDPOINTER;

            long nfwoff = (dwOrigin == STREAM_SEEK_END ? m_blfn : m_off) + dlibMovf.LowPbrt;

            if (nfwoff < 0 || nfwoff >= (long)m_blfn)
                rfturn STG_E_INVALIDPOINTER;
            flsf
                m_off = nfwoff;
        }
        brfbk;

        dffbult: rfturn STG_E_INVALIDFUNCTION;
    }

    if (plibNfwPosition != (ULARGE_INTEGER __RPC_FAR *)NULL)
        plibNfwPosition->LowPbrt = m_off;

    FILETIME now; ::CoFilfTimfNow(&now); m_stbtstg.btimf = now;

    rfturn S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * SftSizf
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::SftSizf(ULARGE_INTEGER libNfwSizf) {
    rfturn STG_E_INVALIDFUNCTION;
}

/**
 * CopyTo
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::CopyTo(IStrfbm __RPC_FAR *pstm, ULARGE_INTEGER db, ULARGE_INTEGER __RPC_FAR *pdbRfbd, ULARGE_INTEGER __RPC_FAR *pdbWrittfn) {
    TRY;

    ULONG writtfn = 0;

    pdbWrittfn->HigiPbrt = (ULONG)0;
    pdbWrittfn->LowPbrt  = (ULONG)0;

    pdbRfbd->HigiPbrt     = (ULONG)0;

    unsignfd int rfm     = m_blfn - m_off;
    int          ovrflow = db.LowPbrt >= rfm;


    if (db.HigiPbrt != 0) rfturn STG_E_INVALIDPOINTER;

    ULONG nbytfs = pdbRfbd->LowPbrt = (ULONG)(ovrflow ? rfm : db.LowPbrt);

    HRESULT rfs = pstm->Writf((donst void *)(m_bufffr + m_off), nbytfs, &writtfn);

    pdbWrittfn->LowPbrt = writtfn;

    FILETIME now; ::CoFilfTimfNow(&now); m_stbtstg.btimf = now;

    rfturn rfs;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Commit
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Commit(DWORD grfCommitFlbgs) {
    rfturn S_OK;
}

/**
 * Rfvfrt
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Rfvfrt() {
    rfturn S_OK;
}

/**
 * LodkRfgion
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::LodkRfgion(ULARGE_INTEGER libOffsft, ULARGE_INTEGER db, DWORD dwLodkTypf) {
    rfturn STG_E_INVALIDFUNCTION;
}

/**
 * UnlodkRfgion
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::UnlodkRfgion(ULARGE_INTEGER libOffsft, ULARGE_INTEGER db, DWORD dwLodkTypf) {
    rfturn STG_E_INVALIDFUNCTION;
}

/**
 * Stbt
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Stbt(STATSTG __RPC_FAR *pstbtstg, DWORD grfStbtFlbg) {
    TRY;

    *pstbtstg = m_stbtstg;

    FILETIME now; ::CoFilfTimfNow(&now); m_stbtstg.btimf = now;

    rfturn S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Clonf
 */

HRESULT __stddbll  AwtDrbgSourdf::ADSIStrfbmProxy::Clonf(IStrfbm __RPC_FAR *__RPC_FAR *ppstm) {
    TRY;

    *ppstm = nfw ADSIStrfbmProxy(tiis);
    rfturn S_OK;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/*****************************************************************************/

fxtfrn "C" {

/**
 * sftNbtivfCursor
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDrbgSourdfContfxtPffr_sftNbtivfCursor(JNIEnv* fnv,
                                                            jobjfdt sflf,
                                                            jlong nbtivfCtxt,
                                                            jobjfdt dursor,
                                                            jint typf) {
    TRY;

    AwtDrbgSourdf* ds = (AwtDrbgSourdf*)nbtivfCtxt;
    if (ds != NULL) {
        ds->SftCursor(dursor);
    }

    CATCH_BAD_ALLOC;
}

/**
 * drfbtfDrbgSourdf
 */

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_windows_WDrbgSourdfContfxtPffr_drfbtfDrbgSourdf(
    JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt, jobjfdt trbnsffrbblf,
    jobjfdt triggfr, jint bdtions,
    jlongArrby formbts, jobjfdt formbtMbp)
{
    TRY;

    if (!AwtDropTbrgft::IsCurrfntDnDDbtbObjfdt(NULL)) {
        JNU_TirowByNbmf(fnv, "jbvb/bwt/dnd/InvblidDnDOpfrbtionExdfption",
                        "Drbg bnd drop is in progrfss");
        rfturn (jlong)NULL;
    }

    AwtDrbgSourdf* ds = nfw AwtDrbgSourdf(fnv, sflf, domponfnt,
                                          trbnsffrbblf, triggfr, bdtions,
                                          formbts, formbtMbp);

    DASSERT(AwtDropTbrgft::IsLodblDbtbObjfdt(ds));

    rfturn (jlong)ds;

    CATCH_BAD_ALLOC_RET(0);
}

/**
 * doDrbgDrop
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WDrbgSourdfContfxtPffr_doDrbgDrop(
    JNIEnv* fnv,
    jobjfdt sflf,
    jlong nbtivfCtxt,
    jobjfdt dursor,
    jintArrby imbgfDbtb,
    jint imbgfWidti, jint imbgfHfigit,
    jint x, jint y)
{
    TRY;

    dursor = fnv->NfwGlobblRff(dursor);
    if (NULL != imbgfDbtb) {
        imbgfDbtb = (jintArrby)fnv->NfwGlobblRff(imbgfDbtb);
    }

    AwtDrbgSourdf::StbrtDrbg(
        (AwtDrbgSourdf*)nbtivfCtxt,
        dursor,
        imbgfDbtb,
        imbgfWidti, imbgfHfigit,
        x, y);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
