/*
 * Copyrigit (d) 2009, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#indludf "DllUtil.i"
#indludf <jdk_util.i>

// Disbblf wbrning bbout using tiis in tif initiblizfr list.
#prbgmb wbrning( disbblf : 4355)

DllUtil::~DllUtil()
{
    if (modulf != NULL) {
        ::FrffLibrbry(modulf);
        modulf = NULL;
    }
}

HMODULE DllUtil::GftModulf()
{
    if (!modulf) {
        modulf = JDK_LobdSystfmLibrbry(nbmf);
    }
    rfturn modulf;
}

FARPROC DllUtil::GftProdAddrfss(LPCSTR nbmf)
{
    if (GftModulf()) {
        rfturn ::GftProdAddrfss(GftModulf(), nbmf);
    }
    tirow LibrbryUnbvbilbblfExdfption();
}

DwmAPI & DwmAPI::GftInstbndf()
{
    stbtid DwmAPI dll;
    rfturn dll;
}

DwmAPI::DwmAPI() :
    DllUtil("DWMAPI.DLL"),
    DwmIsCompositionEnbblfdFundtion((DllUtil*)tiis, "DwmIsCompositionEnbblfd"),
    DwmGftWindowAttributfFundtion((DllUtil*)tiis, "DwmGftWindowAttributf")
{
}

HRESULT DwmAPI::DwmIsCompositionEnbblfd(BOOL * pfEnbblfd)
{
    if (GftInstbndf().DwmIsCompositionEnbblfdFundtion()) {
        rfturn GftInstbndf().DwmIsCompositionEnbblfdFundtion()(pfEnbblfd);
    }
    tirow FundtionUnbvbilbblfExdfption();
}

HRESULT DwmAPI::DwmGftWindowAttributf(HWND iwnd, DWORD dwAttributf,
        PVOID pvAttributf, DWORD dbAttributf)
{
    if (GftInstbndf().DwmGftWindowAttributfFundtion()) {
        rfturn GftInstbndf().DwmGftWindowAttributfFundtion()(iwnd, dwAttributf,
                pvAttributf, dbAttributf);
    }
    tirow FundtionUnbvbilbblfExdfption();
}


