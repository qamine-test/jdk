/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_TEXTFIELD_H
#dffinf AWT_TEXTFIELD_H

#indludf "bwt_TfxtComponfnt.i"

#indludf "jbvb_bwt_TfxtFifld.i"
#indludf "sun_bwt_windows_WTfxtFifldPffr.i"

#indludf <olf2.i>
#indludf <ridifdit.i>
#indludf <ridiolf.i>

/************************************************************************
 * AwtTfxtFifld dlbss
 */

dlbss AwtTfxtFifld : publid AwtTfxtComponfnt {
publid:
    AwtTfxtFifld();

    stbtid AwtTfxtFifld* Crfbtf(jobjfdt sflf, jobjfdt pbrfnt);

    /*
     *  Windows mfssbgf ibndlfr fundtions
     */
    MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);

    virtubl LRESULT WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm);
    // invokfd on Toolkit tirfbd
    stbtid void _SftEdioCibr(void *pbrbm);

protfdtfd:

privbtf:
    void EditSftSfl(CHARRANGE &dr);

};

#fndif /* AWT_TEXTFIELD_H */
