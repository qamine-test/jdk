/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_DEBUGDISPATCH_H
#dffinf JDWP_DEBUGDISPATCH_H

/*
 * Typf of bll dommbnd ibndlfr fundtions. First brgumfnt is tif
 * input strfbm. Sfdond brgumfnt is tif output sfnt bbdk to tif
 * originbtor, but only if JNI_TRUE is rfturnfd. If JNI_FALSE
 * is rfturnfd, no rfply is mbdf.
 */
strudt PbdkftInputStrfbm;
strudt PbdkftOutputStrfbm;

typfdff jboolfbn (*CommbndHbndlfr)(strudt PbdkftInputStrfbm *,
                                  strudt PbdkftOutputStrfbm *);
void dfbugDispbtdi_initiblizf(void);
void dfbugDispbtdi_rfsft(void);
CommbndHbndlfr dfbugDispbtdi_gftHbndlfr(int dmdSft, int dmd) ;

#fndif
