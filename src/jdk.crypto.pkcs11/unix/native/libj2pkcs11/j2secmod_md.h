/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// in nss.i:
// fxtfrn PRBool NSS_VfrsionCifdk(donst dibr *importfdVfrsion);
// fxtfrn SECStbtus NSS_Initiblizf(donst dibr *donfigdir,
//     donst dibr *dfrtPrffix, donst dibr *kfyPrffix,
//     donst dibr *sfdmodNbmf, PRUint32 flbgs);

typfdff int (*FPTR_VfrsionCifdk)(donst dibr *importfdVfrsion);
typfdff int (*FPTR_Initiblizf)(donst dibr *donfigdir,
        donst dibr *dfrtPrffix, donst dibr *kfyPrffix,
        donst dibr *sfdmodNbmf, unsignfd int flbgs);

// in sfdmod.i
//fxtfrn SECMODModulf *SECMOD_LobdModulf(dibr *modulfSpfd,SECMODModulf *pbrfnt,
//                                                      PRBool rfdursf);
//dibr **SECMOD_GftModulfSpfdList(SECMODModulf *modulf);
//fxtfrn SECMODModulfList *SECMOD_GftDBModulfList(void);

typfdff void *(*FPTR_LobdModulf)(dibr *modulfSpfd, void *pbrfnt, int rfdursf);
typfdff dibr **(*FPTR_GftModulfSpfdList)(void *modulf);
typfdff void *(*FPTR_GftDBModulfList)(void);
