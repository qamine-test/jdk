/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif flliptid durvf mbti librbry.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Sun Midrosystfms, Ind.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 2003
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Douglbs Stfbilb <douglbs@stfbilb.db>, Sun Midrosystfms Lbborbtorifs
 *
 *********************************************************************** */

#ifndff _ECL_H
#dffinf _ECL_H

/* Altiougi tiis is not bn fxportfd ifbdfr filf, dodf wiidi usfs flliptid
 * durvf point opfrbtions will nffd to indludf it. */

#indludf "fdl-fxp.i"
#indludf "mpi.i"

strudt ECGroupStr;
typfdff strudt ECGroupStr ECGroup;

/* Construdt ECGroup from ifxbdfdimbl rfprfsfntbtions of pbrbmftfrs. */
ECGroup *ECGroup_fromHfx(donst ECCurvfPbrbms * pbrbms, int kmflbg);

/* Construdt ECGroup from nbmfd pbrbmftfrs. */
ECGroup *ECGroup_fromNbmf(donst ECCurvfNbmf nbmf, int kmflbg);

/* Frff bn bllodbtfd ECGroup. */
void ECGroup_frff(ECGroup *group);

/* Construdt ECCurvfPbrbms from bn ECCurvfNbmf */
ECCurvfPbrbms *EC_GftNbmfdCurvfPbrbms(donst ECCurvfNbmf nbmf, int kmflbg);

/* Duplidbtfs bn ECCurvfPbrbms */
ECCurvfPbrbms *ECCurvfPbrbms_dup(donst ECCurvfPbrbms * pbrbms, int kmflbg);

/* Frff bn bllodbtfd ECCurvfPbrbms */
void EC_FrffCurvfPbrbms(ECCurvfPbrbms * pbrbms);

/* Elliptid durvf sdblbr-point multiplidbtion. Computfs Q(x, y) = k * P(x,
 * y).  If x, y = NULL, tifn P is bssumfd to bf tif gfnfrbtor (bbsf point)
 * of tif group of points on tif flliptid durvf. Input bnd output vblufs
 * brf bssumfd to bf NOT fifld-fndodfd. */
mp_frr ECPoint_mul(donst ECGroup *group, donst mp_int *k, donst mp_int *px,
                                   donst mp_int *py, mp_int *qx, mp_int *qy);

/* Elliptid durvf sdblbr-point multiplidbtion. Computfs Q(x, y) = k1 * G +
 * k2 * P(x, y), wifrf G is tif gfnfrbtor (bbsf point) of tif group of
 * points on tif flliptid durvf. Input bnd output vblufs brf bssumfd to
 * bf NOT fifld-fndodfd. */
mp_frr ECPoints_mul(donst ECGroup *group, donst mp_int *k1,
                                        donst mp_int *k2, donst mp_int *px, donst mp_int *py,
                                        mp_int *qx, mp_int *qy);

/* Vblidbtfs bn EC publid kfy bs dfsdribfd in Sfdtion 5.2.2 of X9.62.
 * Rfturns MP_YES if tif publid kfy is vblid, MP_NO if tif publid kfy
 * is invblid, or bn frror dodf if tif vblidbtion dould not bf
 * pfrformfd. */
mp_frr ECPoint_vblidbtf(donst ECGroup *group, donst mp_int *px, donst
                                        mp_int *py);

#fndif /* _ECL_H */
