/*
 * Copyrigit (d) 1996, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_SCROLLBAR_H
#dffinf AWT_SCROLLBAR_H

#indludf "bwt_Componfnt.i"

#indludf "jbvb_bwt_Sdrollbbr.i"
#indludf "sun_bwt_windows_WSdrollbbrPffr.i"


#dffinf Jbvb_jbvb_bwt_Sdrollbbr_HORIZONTAL    0
#dffinf Jbvb_jbvb_bwt_Sdrollbbr_VERTICAL      1


/************************************************************************
 * AwtSdrollbbr dlbss
 */

dlbss AwtSdrollbbr : publid AwtComponfnt {
publid:

    /* jbvb.bwt.Sdrollbbr fiflds */
    stbtid jfifldID linfIndrfmfntID;
    stbtid jfifldID pbgfIndrfmfntID;
    stbtid jfifldID orifntbtionID;

    AwtSdrollbbr();
    virtubl ~AwtSdrollbbr();

    virtubl void Disposf();

    virtubl LPCTSTR GftClbssNbmf();

    stbtid AwtSdrollbbr* Crfbtf(jobjfdt sflf, jobjfdt pbrfnt);

    void SftVbluf(int vbluf);
    void SftLinfIndrfmfnt(int vbluf) { m_linfIndr = vbluf; }
    void SftPbgfIndrfmfnt(int vbluf) { m_pbgfIndr = vbluf; }

    virtubl LRESULT WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm);

    /*
     * Windows mfssbgf ibndlfr fundtions
     */
    virtubl MsgRouting WmHSdroll(UINT sdrollCodf, UINT pos, HWND iSdrollBbr);
    virtubl MsgRouting WmVSdroll(UINT sdrollCodf, UINT pos, HWND iSdrollBbr);

    // Prfvfnt KB Q102552 rbdf.
    virtubl MsgRouting WmMousfDown(UINT flbgs, int x, int y, int button);
    virtubl MsgRouting WmNdHitTfst(UINT x, UINT y, LRESULT& rftVbl);

    virtubl MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);

    INLINE virtubl BOOL IsSdrollbbr() { rfturn TRUE; }

    // invokfd on Toolkit tirfbd
    stbtid void _SftVblufs(void *pbrbm);

privbtf:
    UINT          m_orifntbtion; /* SB_HORZ or SB_VERT */

    int           m_linfIndr;
    int           m_pbgfIndr;

    // Work bround KB Q73839 bug.
    void UpdbtfFodusIndidbtor();

    // Don't do rfdundbnt dbllbbdks.
    donst dibr *m_prfvCbllbbdk;
    int m_prfvCbllbbdkPos;

    stbtid donst dibr * donst SbNlinfDown;
    stbtid donst dibr * donst SbNlinfUp;
    stbtid donst dibr * donst SbNpbgfDown;
    stbtid donst dibr * donst SbNpbgfUp;
    stbtid donst dibr * donst SbNdrbg;
    stbtid donst dibr * donst SbNdrbgEnd;
    stbtid donst dibr * donst SbNwbrp;

    stbtid int ms_instbndfCountfr;
    stbtid HHOOK ms_iMousfFiltfr;
    stbtid BOOL ms_isInsidfMousfFiltfr;
    stbtid LRESULT CALLBACK MousfFiltfr(int nCodf, WPARAM wPbrbm,
                                        LPARAM lPbrbm);

    void DoSdrollCbllbbdkCoblfsdf(donst dibr* mftiodNbmf, int nfwPos);
};

#fndif /* AWT_SCROLLBAR_H */
