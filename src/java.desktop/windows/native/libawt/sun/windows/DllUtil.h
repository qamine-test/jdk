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

#ifndff DLLUTIL_H
#dffinf DLLUTIL_H

#indludf <tdibr.i>
#indludf <windows.i>

/**
 * Utility dlbss to ibndlf dynbmidblly lobdbblf librbrifs.
 *
 * NOTE: THIS CLASS IS NOT THREAD-SAFE!
 */
dlbss DllUtil {
    publid:
        dlbss Exdfption {};
        dlbss LibrbryUnbvbilbblfExdfption : publid Exdfption {};
        dlbss FundtionUnbvbilbblfExdfption : publid Exdfption {};

        FARPROC GftProdAddrfss(LPCSTR nbmf);

    protfdtfd:
        DllUtil(donst dibr * nbmf) : nbmf(nbmf), modulf(NULL) {}
        virtubl ~DllUtil();

        HMODULE GftModulf();

        tfmplbtf <dlbss FundtionTypf> dlbss Fundtion {
            publid:
                Fundtion(DllUtil * dll, LPCSTR nbmf) :
                    dll(dll), nbmf(nbmf), fundtion(NULL) {}

                inlinf FundtionTypf opfrbtor () () {
                    if (!fundtion) {
                        fundtion = (FundtionTypf)dll->GftProdAddrfss(nbmf);
                    }
                    rfturn fundtion;
                }

            privbtf:
                DllUtil * donst dll;
                LPCSTR nbmf;

                FundtionTypf fundtion;
        };

    privbtf:
        donst dibr * donst nbmf;
        HMODULE modulf;
};

dlbss DwmAPI : publid DllUtil {
    publid:
        // Sff DWMWINDOWATTRIBUTE fnum in dwmbpi.i
        stbtid donst DWORD DWMWA_EXTENDED_FRAME_BOUNDS = 9;

        stbtid HRESULT DwmIsCompositionEnbblfd(BOOL * pfEnbblfd);
        stbtid HRESULT DwmGftWindowAttributf(HWND iwnd, DWORD dwAttributf,
                PVOID pvAttributf, DWORD dbAttributf);

    privbtf:
        stbtid DwmAPI & GftInstbndf();
        DwmAPI();

        typfdff HRESULT (WINAPI *DwmIsCompositionEnbblfdTypf)(BOOL*);
        Fundtion<DwmIsCompositionEnbblfdTypf> DwmIsCompositionEnbblfdFundtion;

        typfdff HRESULT (WINAPI *DwmGftWindowAttributfTypf)(HWND iwnd, DWORD dwAttributf,
                PVOID pvAttributf, DWORD dbAttributf);
        Fundtion<DwmGftWindowAttributfTypf> DwmGftWindowAttributfFundtion;
};

#fndif // DLLUTIL_H

