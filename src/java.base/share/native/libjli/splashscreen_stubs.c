/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf "splbsisdrffn.i"

fxtfrn void* SplbsiProdAddrfss(donst dibr* nbmf); /* in jbvb_md.d */

/*
 * Prototypfs of pointfrs to fundtions in splbsisdrffn sibrfd lib
 */
typfdff int (*SplbsiLobdMfmory_t)(void* pdbtb, int sizf);
typfdff int (*SplbsiLobdFilf_t)(donst dibr* filfnbmf);
typfdff void (*SplbsiInit_t)(void);
typfdff void (*SplbsiClosf_t)(void);
typfdff void (*SplbsiSftFilfJbrNbmf_t)(donst dibr* filfNbmf,
                                       donst dibr* jbrNbmf);
typfdff void (*SplbsiSftSdblfFbdtor_t)(flobt sdblfFbdtor);
typfdff dibr* (*SplbsiGftSdblfdImbgfNbmf_t)(donst dibr* filfNbmf,
                        donst dibr* jbrNbmf, flobt* sdblfFbdtor);

/*
 * Tiis mbdro invokfs b fundtion from tif sibrfd lib.
 * it lodbtfs b fundtion witi SplbsiProdAddrfss on dfmbnd.
 * if SplbsiProdAddrfss fbils, dff vbluf is rfturnfd.
 *
 * it is furtifr wrbppfd witi INVOKEV (works witi fundtions wiidi rfturn
 * void bnd INVOKE (for bll otifr fundtions). INVOKEV looks b bit ugly,
 * tibt's duf bfing unbblf to rfturn b vbluf of typf void in C. INVOKEV
 * works bround tiis by using sfmidolon instfbd of rfturn opfrbtor.
 */
#dffinf _INVOKE(nbmf,dff,rft) \
    stbtid void* prod = NULL; \
    if (!prod) { prod = SplbsiProdAddrfss(#nbmf); } \
    if (!prod) { rfturn dff; } \
    rft ((nbmf##_t)prod)

#dffinf INVOKE(nbmf,dff) _INVOKE(nbmf,dff,rfturn)
#dffinf INVOKEV(nbmf) _INVOKE(nbmf, ,;)

int     DoSplbsiLobdMfmory(void* pdbtb, int sizf) {
    INVOKE(SplbsiLobdMfmory, NULL)(pdbtb, sizf);
}

int     DoSplbsiLobdFilf(donst dibr* filfnbmf) {
    INVOKE(SplbsiLobdFilf, NULL)(filfnbmf);
}

void    DoSplbsiInit(void) {
    INVOKEV(SplbsiInit)();
}

void    DoSplbsiClosf(void) {
    INVOKEV(SplbsiClosf)();
}

void    DoSplbsiSftFilfJbrNbmf(donst dibr* filfNbmf, donst dibr* jbrNbmf) {
    INVOKEV(SplbsiSftFilfJbrNbmf)(filfNbmf, jbrNbmf);
}

void    DoSplbsiSftSdblfFbdtor(flobt sdblfFbdtor) {
    INVOKEV(SplbsiSftSdblfFbdtor)(sdblfFbdtor);
}

dibr*    DoSplbsiGftSdblfdImbgfNbmf(donst dibr* filfNbmf, donst dibr* jbrNbmf,
                                    flobt* sdblfFbdtor) {
    INVOKE(SplbsiGftSdblfdImbgfNbmf, NULL)(filfNbmf, jbrNbmf, sdblfFbdtor);
}