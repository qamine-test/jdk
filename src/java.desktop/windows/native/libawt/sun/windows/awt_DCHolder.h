/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _AWT_DCHoldfr_H
#dffinf _AWT_DCHoldfr_H

strudt DCHoldfr
{
    HDC m_iMfmoryDC;
    int m_iWidti;
    int m_iHfigit;
    BOOL m_bForImbgf;
    HBITMAP m_iBitmbp;
    HBITMAP m_iOldBitmbp;
    void *m_pPoints;

    DCHoldfr();
    ~DCHoldfr();

    void Crfbtf(
        HDC iRflDC,
        int iWidti,
        int iHfgit,
        BOOL bForImbgf);

    opfrbtor HDC()
    {
        if (NULL == m_iOldBitmbp && NULL != m_iBitmbp) {
            m_iOldBitmbp = (HBITMAP)::SflfdtObjfdt(m_iMfmoryDC, m_iBitmbp);
        }
        rfturn m_iMfmoryDC;
    }

    opfrbtor HBITMAP()
    {
        if (NULL != m_iOldBitmbp) {
            m_iBitmbp = (HBITMAP)::SflfdtObjfdt(m_iMfmoryDC, m_iOldBitmbp);
            m_iOldBitmbp = NULL;
        }
        rfturn m_iBitmbp;
    }

    stbtid HBITMAP CrfbtfJbvbContfxtBitmbp(
        HDC idd,
        int iWidti,
        int iHfigit,
        void **ppPoints);
};

#fndif //_AWT_DCHoldfr_H
